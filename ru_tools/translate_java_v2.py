"""
Safe AST/tokenizer-aware Java string-literal translator for Lilith's Throne (v2).
Features:
- Tokenizes Java source properly (skipping // comments, /* */ comments, char literals, annotations, import/package)
- Tracks enclosing call/constructor name + argument index via paren/brace/comma stack
- Respects exact CONSTRUCTOR_SKIP_ARGS map (pathName, attributeItemId, formattingNames, SVGPath, etc.)
- Skips switch `case "..."`, XML DOM/flag/ID/resource/regex/ScriptEngine/log/CSS calls
- Merges pure consecutive string literal concatenations (`"a" + "b"`) into single translation units
- Preserves exact leading/trailing spaces around Java `+ expr +` concatenations
- Translates both multi-word prose and safe single-word UI labels/body descriptors
- Masks `%s`/`%d`/`%.1f`, `[*.verb(...)]` (@@V*@@), Nashorn `[#...]` (@@S*@@), HTML (@@H*@@), atomic tags (@@T*@@)
- Reuses shared Translation Memory (tm_cache.json) and validates masks/brackets/Cyrillic progress
"""
import argparse
import json
import re
import sys
import time
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import translate_lt as base

ROOT = base.ROOT

# Exact (ConstructorOrMethodName, TotalTopLevelArgCount) -> {0-based arg indices to NEVER translate}
# Derived directly from inspecting all Abstract*.java & Colour.java & PlaceType.java signatures.
CONSTRUCTOR_SKIP_ARGS: dict[tuple[str, int], set[int]] = {
    ("AbstractAttribute", 11): {6},
    ("AbstractPerk", 9): {4},
    ("AbstractPerk", 12): {4},
    ("AbstractStatusEffect", 8): {2},
    ("AbstractStatusEffect", 9): {2},
    ("AbstractStatusEffect", 10): {2},
    ("AbstractStatusEffect", 11): {3},
    ("AbstractFetish", 10): {3},
    ("AbstractTattooType", 8): {0},
    ("AbstractSubspecies", 37): {2, 3, 4, 5},
    ("AbstractCombatMove", 12): {6},
    ("AbstractCombatMove", 13): {6, 7},
    ("AbstractCombatMove", 17): {8},
    ("AbstractParserTarget", 2): {0, 1},
    ("ParserCommand", 5): {0, 3, 4},
    ("ParserCommand", 6): {0, 3, 4},
    ("AbstractCoreItem", 7): {2},
    ("AbstractCoreItem", 8): {2},
    ("AbstractClothingType", 22): {11},
    ("AbstractClothingType", 27): {11, 13},
    ("AbstractFilledBreastPump", 5): {2},
    ("AbstractFilledCondom", 6): {2},
    ("AbstractItemType", 11): {6},
    ("AbstractItemType", 13): {6},
    ("AbstractWorldType", 11): {6},
    ("AbstractGlobalPlaceType", 8): {2},
    ("AbstractGlobalPlaceType", 9): {3},
    ("AbstractPlaceType", 9): {3},
    ("AbstractPlaceType", 4): {1, 2},
    ("Colour", 4): {3},
    ("Colour", 5): {4},
    ("LegConfiguration", 14): {13},
    ("FetishDesire", 6): {4},
    ("FetishLevel", 9): {3},
    ("Spell", 17): {6},
    ("SpellUpgrade", 8): {2},
    ("SpellUpgrade", 9): {3},
    ("TFModifier", 5): {3},
    ("TFModifier", 6): {3},
    ("TFModifier", 2): {1},
    ("TFModifier", 3): {1},
    ("SlaveJobSetting", 4): {2},
    ("WorldRegion", 1): {0},
}

# Call names whose string arguments are internal IDs, flags, XML tags, regexes, resources, scripts, or logs
FORBIDDEN_CALLS = {
    "parseFromXMLFile",
    "parseFromFile",
    "runXmlTest",
    "getDialogueNode",
    "getDialogueNodeFromId",
    "getDialogueFlag",
    "setDialogueFlag",
    "hasDialogueFlag",
    "getFlag",
    "setFlag",
    "hasFlag",
    "addFlag",
    "removeFlag",
    "getId",
    "setId",
    "getItemTypeFromId",
    "getClothingTypeFromId",
    "getWeaponTypeFromId",
    "getSubspeciesFromId",
    "getRaceFromId",
    "getStatusEffectFromId",
    "getTattooTypeFromId",
    "getPlaceTypeFromId",
    "getWorldTypeFromId",
    "getEncounterFromId",
    "getCombatMoveFromId",
    "getOutfitFromId",
    "getColourFromString",
    "getNpc",
    "getNPCById",
    "getCharacterById",
    "addNPC",
    "valueOf",
    "forName",
    "getResource",
    "getResourceAsStream",
    "SvgInformation",
    "getSvg",
    "compile",
    "matches",
    "split",
    "equals",
    "equalsIgnoreCase",
    "startsWith",
    "endsWith",
    "contains",
    "indexOf",
    "lastIndexOf",
    "replace",
    "replaceAll",
    "replaceFirst",
    "getAttribute",
    "setAttribute",
    "createElement",
    "getElementsByTagName",
    "getMandatoryFirstOf",
    "getMandatoryFirstOfOptional",
    "getOptionalFirstOf",
    "getAllOf",
    "XMLUtil",
    "put",
    "eval",
    "println",
    "print",
    "printf",
    "log",
    "RuntimeException",
    "IllegalArgumentException",
    "NullPointerException",
    "XMLLoadException",
    "File",
    "Paths",
    "get",
    "URI",
    "URL",
    "FXMLLoader",
    "getStylesheets",
    "getStyleClass",
    "setStyle",
    "addStyleClass",
    "parseInt",
    "parseFloat",
    "parseDouble",
    "parseBoolean",
    "TooltipInformationEventListener",
    "getStickers",
    "setSticker",
    "Pattern",
    "getMethod",
    "getDeclaredField",
    "getField",
}

FORBIDDEN_MEMBER_PREFIX_RE = re.compile(
    r"(?:Flag|Id|SVG|Svg|Xml|XML|Path|Resource|Logger|System\.err|System\.out|engine|Pattern)\b"
)

SAFE_DISPLAY_CALLS = {
    "DialogueNode",
    "Response",
    "ResponseSex",
    "ResponseEffectsOnly",
    "ResponseTrade",
    "ResponseCombat",
    "ResponseTag",
    "Label",
    "Button",
    "CheckBox",
    "ToggleButton",
    "RadioButton",
    "MenuItem",
    "Tooltip",
    "TextField",
    "TextArea",
    "Alert",
    "setText",
    "setTitle",
    "setHeaderText",
    "setContentText",
    "setPromptText",
    "returnStringAtRandom",
    "parse",
    "append",
    "flashMessage",
    "addEventLogEntry",
    "getActionTitle",
    "getActionDescription",
    "getDescription",
    "getTooltip",
    "getName",
    "getNamePlural",
    "getUseName",
}

TECH_STR_RE = re.compile(
    r"^(?:"
    r"res/.*"
    r"|txt/.*"
    r"|/com/.*"
    r"|/.*\.(?:fxml|css|png|svg|xml|ttf|otf|html|txt|js)"
    r"|.*\.(?:fxml|css|png|svg|xml|ttf|otf)"
    r"|.*-fx-.*"
    r"|#[0-9a-fA-F]{3,8}"
    r"|https?://.*"
    r"|mailto:.*"
    r"|[A-Z0-9_]{2,}"
    r"|[a-z0-9_]+_[a-zA-Z0-9_]+"
    r"|[a-z]+[A-Z][a-zA-Z0-9]*"
    r"|[a-z]+/[a-zA-Z0-9_/\-]+"
    r"|[\s\d\W_]+"
    r")$"
)

PURE_HTML_TAG_RE = re.compile(r"^(?:\s*(?:</?[a-zA-Z][^>]*>|;['\"]>|['\"]>)\s*)+$")
HTML_EDGE_RE = re.compile(r"^(?:[;\s]*['\"]>|;>|['\"]>)|<[a-zA-Z][^<>]*=['\"][^\s<]*$")
PRINTF_SPEC_RE = re.compile(r"%(?:\d+\$)?[-+# 0,(]*\d*(?:\.\d+)?[bBhHsScCdoxXeEfgGaAtTn%]")

DETERMINER_MAP = {
    "a": "",
    "an": "",
    "the": "",
    "a pair of": "пара",
    "a bottle of": "бутылочка",
    "a vial of": "флакон",
    "a box of": "коробка",
    "a packet of": "пакетик",
    "a pack of": "пачка",
    "a cup of": "чашка",
    "a glass of": "бокал",
    "a mug of": "кружка",
    "a slice of": "кусочек",
    "a piece of": "кусок",
    "a handful of": "горсть",
    "a layer of": "слой",
    "a set of": "набор",
    "some": "немного",
}


def unescape_java(raw_inner: str) -> str:
    out = []
    i = 0
    n = len(raw_inner)
    while i < n:
        c = raw_inner[i]
        if c == "\\" and i + 1 < n:
            nxt = raw_inner[i + 1]
            if nxt == "n":
                out.append("\n")
                i += 2
            elif nxt == "r":
                out.append("\r")
                i += 2
            elif nxt == "t":
                out.append("\t")
                i += 2
            elif nxt == '"':
                out.append('"')
                i += 2
            elif nxt == "'":
                out.append("'")
                i += 2
            elif nxt == "\\":
                out.append("\\")
                i += 2
            elif nxt == "u" and i + 5 < n:
                try:
                    out.append(chr(int(raw_inner[i + 2 : i + 6], 16)))
                    i += 6
                    continue
                except ValueError:
                    out.append(c)
                    i += 1
            else:
                out.append(nxt)
                i += 2
        else:
            out.append(c)
            i += 1
    return "".join(out)


def escape_java(s: str) -> str:
    s = (
        s.replace("\\", "\\\\")
        .replace('"', '\\"')
        .replace("\r", "")
        .replace("\n", "\\n")
        .replace("\t", "\\t")
    )
    return s


def tokenize_java(src: str) -> list[dict]:
    """Tokenize Java source preserving exact character offsets."""
    tokens = []
    i = 0
    n = len(src)
    while i < n:
        c = src[i]
        if c.isspace():
            i += 1
            continue
        if c == "/" and i + 1 < n and src[i + 1] == "/":
            nl = src.find("\n", i)
            i = n if nl == -1 else nl + 1
            continue
        if c == "/" and i + 1 < n and src[i + 1] == "*":
            end = src.find("*/", i + 2)
            i = n if end == -1 else end + 2
            continue
        if c == "'":
            start = i
            i += 1
            while i < n:
                if src[i] == "\\":
                    i += 2
                    continue
                if src[i] == "'":
                    i += 1
                    break
                i += 1
            tokens.append({"kind": "CHAR", "val": src[start:i], "start": start, "end": i})
            continue
        if c == '"':
            start = i
            i += 1
            while i < n:
                if src[i] == "\\":
                    i += 2
                    continue
                if src[i] == '"':
                    i += 1
                    break
                i += 1
            raw_inner = src[start + 1 : i - 1]
            tokens.append({"kind": "STR", "val": raw_inner, "start": start, "end": i})
            continue
        if c.isalpha() or c in "_$@":
            start = i
            i += 1
            while i < n and (src[i].isalnum() or src[i] in "_$"):
                i += 1
            tokens.append({"kind": "ID", "val": src[start:i], "start": start, "end": i})
            continue
        if c.isdigit():
            start = i
            i += 1
            while i < n and (src[i].isalnum() or src[i] in "._"):
                i += 1
            tokens.append({"kind": "NUM", "val": src[start:i], "start": start, "end": i})
            continue
        tokens.append({"kind": "SYM", "val": c, "start": i, "end": i + 1})
        i += 1
    return tokens


def _count_args_in_parens(tokens: list[dict], open_idx: int) -> int:
    """Given token index of '(' returning 0 if empty `()` else 1 + top-level comma count."""
    depth = 0
    commas = 0
    has_content = False
    for k in range(open_idx, len(tokens)):
        tok = tokens[k]
        v = tok["val"]
        if tok["kind"] == "SYM":
            if v == "(":
                depth += 1
            elif v == ")":
                depth -= 1
                if depth == 0:
                    return (commas + 1) if has_content else 0
            elif v in ("{", "["):
                has_content = True
                depth += 1
            elif v in ("}", "]"):
                depth -= 1
            elif v == "," and depth == 1:
                commas += 1
            elif depth >= 1 and v != "(":
                has_content = True
        else:
            if depth >= 1:
                has_content = True
    return 0


def collect_string_units(src: str) -> list[dict]:
    """
    Walk tokenized Java, merge pure `+` string literal chains, attach call/constructor context,
    and filter to user-visible translatable English display strings.
    """
    tokens = tokenize_java(src)
    units = []
    # Stack entries: {'kind': 'CALL'|'PAREN'|'BRACE', 'name': str, 'arg_idx': int, 'total_args': int, 'is_new': bool, 'after_case': bool, 'in_annot': bool, 'in_header': bool}
    stack: list[dict] = []
    after_case = False
    in_header_stmt = False  # package / import
    in_annot = False

    n = len(tokens)
    t_idx = 0
    while t_idx < n:
        tok = tokens[t_idx]
        kind, val = tok["kind"], tok["val"]

        if kind == "ID":
            if val in ("import", "package") and not stack:
                in_header_stmt = True
            elif val.startswith("@") and t_idx + 1 < n and tokens[t_idx + 1]["val"] == "(":
                in_annot = True
            elif val == "case":
                after_case = True
            t_idx += 1
            continue

        if kind == "SYM":
            if val == ";":
                in_header_stmt = False
                in_annot = False
                after_case = False
            elif val == ":":
                after_case = False
            elif val == "(":
                prev_id = ""
                is_new = False
                if t_idx >= 1 and tokens[t_idx - 1]["kind"] == "ID":
                    prev_id = tokens[t_idx - 1]["val"]
                    if t_idx >= 2 and tokens[t_idx - 2]["kind"] == "ID" and tokens[t_idx - 2]["val"] == "new":
                        is_new = True
                total_args = _count_args_in_parens(tokens, t_idx)
                if in_annot:
                    stack.append({"kind": "ANNOT", "name": prev_id, "arg_idx": 0, "total_args": total_args})
                    in_annot = False
                elif prev_id and prev_id not in ("if", "for", "while", "switch", "catch", "synchronized", "return"):
                    stack.append({"kind": "CALL", "name": prev_id, "arg_idx": 0, "total_args": total_args, "is_new": is_new})
                else:
                    stack.append({"kind": "PAREN", "name": "", "arg_idx": 0, "total_args": 0})
            elif val == "{":
                stack.append({"kind": "BRACE", "name": "", "arg_idx": 0, "total_args": 0})
            elif val == ",":
                for frame in reversed(stack):
                    if frame["kind"] in ("CALL", "BRACE"):
                        if frame["kind"] == "CALL":
                            frame["arg_idx"] += 1
                        break
            elif val in (")", "}"):
                if stack:
                    stack.pop()
            t_idx += 1
            continue

        if kind == "STR":
            # Merge pure adjacent `"a" + "b" + "c"` literals
            chain_parts = [unescape_java(val)]
            start_off = tok["start"]
            end_off = tok["end"]
            prev_tok_idx = t_idx - 1
            k = t_idx
            while k + 2 < n and tokens[k + 1]["kind"] == "SYM" and tokens[k + 1]["val"] == "+" and tokens[k + 2]["kind"] == "STR":
                chain_parts.append(unescape_java(tokens[k + 2]["val"]))
                end_off = tokens[k + 2]["end"]
                k += 2
            next_tok_idx = k + 1

            combined = "".join(chain_parts)
            # Determine if adjacent to `+ expr`
            has_left_plus = prev_tok_idx >= 0 and tokens[prev_tok_idx]["kind"] == "SYM" and tokens[prev_tok_idx]["val"] == "+"
            has_right_plus = next_tok_idx < n and tokens[next_tok_idx]["kind"] == "SYM" and tokens[next_tok_idx]["val"] == "+"

            if not in_header_stmt and not in_annot and not after_case:
                # Inspect nearest enclosing CALL frames inside current BRACE scope
                enclosing_calls = []
                for frame in reversed(stack):
                    if frame["kind"] == "BRACE":
                        break
                    if frame["kind"] in ("CALL", "ANNOT"):
                        enclosing_calls.append(frame)

                skip_reason = _check_skip_context(src, start_off, end_off, combined, enclosing_calls, stack)
                if not skip_reason:
                    units.append(
                        {
                            "start": start_off,
                            "end": end_off,
                            "text": combined,
                            "has_left_plus": has_left_plus,
                            "has_right_plus": has_right_plus,
                            "calls": [(f["name"], f["arg_idx"], f["total_args"]) for f in enclosing_calls],
                        }
                    )
            t_idx = k + 1
            continue

        t_idx += 1

    return units


def _check_skip_context(
    src: str, start_off: int, end_off: int, text: str, enclosing_calls: list[dict], full_stack: list[dict]
) -> str | None:
    s = text.strip()
    if not s or len(s) < 2 and s.lower() != "a":
        return "empty_or_char"
    if any(f["kind"] == "ANNOT" for f in enclosing_calls):
        return "annotation"
    if not re.search(r"[a-zA-Z]", text):
        return "no_ascii"
    if PURE_HTML_TAG_RE.match(text):
        return "pure_html"
    # Skip if determiner == "a" and not inside constructor / return / display concat
    low = s.lower()
    if low not in DETERMINER_MAP and TECH_STR_RE.match(s):
        return "tech_str"
    # If already translated Russian with no leftover multi-word English run outside masks/wrappers -> skip!
    if re.search(r"[А-Яа-яЁё]", text) and not base.has_translatable_words(text):
        return "already_ru"

    # Check direct enclosing calls for forbidden functions or skip arg indices
    for f in enclosing_calls:
        cname = f["name"]
        if cname in FORBIDDEN_CALLS:
            return f"forbidden_call:{cname}"
        skip_set = CONSTRUCTOR_SKIP_ARGS.get((cname, f["total_args"]))
        if skip_set and f["arg_idx"] in skip_set:
            return f"skip_arg:{cname}#{f['arg_idx']}"

    # Special rule for Colour formattingNames: Util.newArrayListOfValues(...) inside Colour constructor!
    call_names = [f["name"] for f in enclosing_calls]
    if "Colour" in call_names and ("newArrayListOfValues" in call_names or "Arrays" in call_names or "asList" in call_names):
        return "colour_formatting_names"

    # Check immediate statement prefix (up to `;` or `{`) for XML / Flag / ID / CSS patterns
    stmt_cut = max(src.rfind(";", max(0, start_off - 240), start_off), src.rfind("{", max(0, start_off - 240), start_off))
    stmt_prefix = src[(stmt_cut + 1) if stmt_cut != -1 else max(0, start_off - 180) : start_off]
    if FORBIDDEN_MEMBER_PREFIX_RE.search(stmt_prefix) and not any(c in SAFE_DISPLAY_CALLS for c in call_names):
        # Allow if clearly multi-word English prose (>=3 words) unless explicit parseFromXMLFile/getFlag/saveAsXML/loadFromXML
        if re.search(r"(?:parseFromXMLFile|getDialogueFlag|setDialogueFlag|saveAsXML|loadFromXML|createElement|getAttribute|setAttribute|System\.err|Log\.)", stmt_prefix):
            return "stmt_xml_or_flag"

    # For single-word / short lowercase literals (e.g. "sword", "cut"), only allow if inside SAFE_DISPLAY_CALLS, enums, or Abstract* constructors, or determiner map, or return statement
    if " " not in s and len(s) < 25 and re.match(r"^[a-z\-]+$", s):
        if low in DETERMINER_MAP:
            return None
        in_safe_ctx = (
            any(c in SAFE_DISPLAY_CALLS or c.startswith("Abstract") or c == "Colour" or c == "PlaceUpgrade" for c in call_names)
            or bool(re.search(r"\breturn\s*$", src[max(0, start_off - 30) : start_off]))
            or bool(re.match(r"^\s*[A-Z][A-Z0-9_]*\s*\(", src[max(0, start_off - 160) : start_off]))  # Enum constant arg
        )
        if not in_safe_ctx:
            return "single_lower_unsure"

    # Skip pure CSS inline style attribute string or HTML tag attribute glue like `"<span style='color:"`
    if s.startswith("<") and (s.endswith("'") or s.endswith(":") or s.endswith(";") or s.endswith("='") or s.endswith('="')):
        if not re.search(r">[A-Za-z]{2,}", base._strip_wrappers_and_masks(s)):
            return "html_open_frag"
    if s in ("</span>", "</p>", "</i>", "</b>", "<p>", "<i>", "<b>", "<br/>"):
        return "html_tag"

    # Skip pure tag/nashorn/format string with no prose outside masks
    tmp = PRINTF_SPEC_RE.sub(" ", s)
    if low not in DETERMINER_MAP and not base.has_translatable_words(tmp):
        return "no_prose_outside_masks"

    return None


def mask_java_text(text: str) -> tuple[str, dict]:
    """Extend base.mask_text with leading/trailing partial HTML tag fragments and printf %s/%d masking (`@@P*@@`)."""
    masked, masks = base.mask_text(text)
    p_idx = 0

    def next_key(prefix: str) -> str:
        nonlocal p_idx
        key = f"@@{prefix}{p_idx}@@"
        while key in masks:
            p_idx += 1
            key = f"@@{prefix}{p_idx}@@"
        p_idx += 1
        return key

    def repl_edge(m):
        key = next_key("P")
        masks[key] = m.group(0)
        return key

    def repl_p(m):
        key = next_key("P")
        masks[key] = m.group(0)
        return key

    masked = HTML_EDGE_RE.sub(repl_edge, masked)
    masked = PRINTF_SPEC_RE.sub(repl_p, masked)
    return masked, masks


def translate_java_unit_batch(api_url: str, units: list[dict], glossary: dict, rel: str) -> list[str]:
    results = [u["text"] for u in units]
    tm = base.load_tm_cache()

    llm_indices = []
    for idx, u in enumerate(units):
        raw = u["text"]
        lead = raw[: len(raw) - len(raw.lstrip())]
        trail = raw[len(raw.rstrip()) :]
        core = raw.strip()
        low = core.lower()
        # Fast exact determiner handling (`"a"` -> `""`, `"a pair of"` -> `"пара"`, `"a bottle of"` -> `"бутылочка"`)
        if low in DETERMINER_MAP:
            repl = DETERMINER_MAP[low]
            if core and core[0].isupper() and repl:
                repl = repl[0].upper() + repl[1:]
            results[idx] = (lead + repl + trail) if repl else ""
            continue
        llm_indices.append(idx)

    # Translate non-determiner strings via TM + Gemma 4
    sub_idx = 0
    while sub_idx < len(llm_indices):
        batch = []
        char_sum = 0
        while sub_idx < len(llm_indices) and len(batch) < 10:
            ui = llm_indices[sub_idx]
            raw = units[ui]["text"]
            core = raw.strip()
            lead = raw[: len(raw) - len(raw.lstrip())]
            trail = raw[len(raw.rstrip()) :]
            cached = tm.get(core)
            if cached and "@@" not in cached and not base.has_translatable_words(cached):
                results[ui] = lead + cached + trail
                sub_idx += 1
                continue
            if batch and char_sum + len(core) > 1400:
                break
            batch.append(ui)
            char_sum += len(core)
            sub_idx += 1

        if not batch:
            continue

        if len(batch) == 1:
            ui = batch[0]
            results[ui] = _translate_single_java(api_url, units[ui]["text"], glossary, f"Java {rel}", tm)
            base.save_tm_cache()
            continue

        masked_map = {}
        masks_map = {}
        meta_map = {}
        comb_src = []
        for pos, ui in enumerate(batch):
            orig = units[ui]["text"]
            lead = orig[: len(orig) - len(orig.lstrip())]
            trail = orig[len(orig.rstrip()) :]
            core = orig.strip()
            m_txt, m_dict = mask_java_text(core)
            req_cyr = not bool(re.search(r"[А-Яа-яЁё]", core)) and bool(
                re.search(r"[a-zA-Z]{2,}", base._strip_wrappers_and_masks(PRINTF_SPEC_RE.sub(" ", core)))
            )
            req_keys = [mk for mk in m_dict if mk in m_txt]
            k = str(pos)
            masked_map[k] = m_txt
            masks_map[k] = m_dict
            meta_map[k] = (lead, core, trail, req_cyr, req_keys)
            comb_src.append(core)

        hint = base.build_glossary_hint("\n".join(comb_src), glossary)
        sys_p = (
            "You are an expert Russian literary translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'. "
            "Translate ALL English prose/UI text in each JSON string value into natural, idiomatic, uncensored Russian. "
            "If a value contains wrappers like [npc.speech(...)] or [style.colour(...)], translate ONLY the English words inside parentheses keeping wrapper names unchanged; NEVER invent new tags, arrows ('->'), or repeat English source. "
            "Treat @@V0@@/@@V1@@ as verbs already present in the sentence. "
            "CRITICAL: Keep exact JSON keys and preserve EVERY placeholder token (@@H*@@, @@T*@@, @@V*@@, @@S*@@, @@M*@@, @@K*@@, @@P*@@) verbatim. "
            "Return ONLY a valid JSON object."
        )
        usr_p = f"Context: Java UI/Dialogue {rel}\n" + (hint + "\n" if hint else "") + "Input JSON:\n" + json.dumps(masked_map, ensure_ascii=False)

        parsed = None
        try:
            raw_out = base.call_llm_raw(
                api_url,
                [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}],
                json_mode=True,
                max_tokens=3072,
            )
            raw_out = re.sub(r"^```(?:json)?\s*|\s*```$", "", raw_out, flags=re.S).strip()
            parsed = json.loads(raw_out)
        except Exception:
            parsed = None

        for pos, ui in enumerate(batch):
            k = str(pos)
            lead, core, trail, req_cyr, req_keys = meta_map[k]
            if (
                isinstance(parsed, dict)
                and isinstance(parsed.get(k), str)
                and base.validate_masks(parsed[k], masks_map[k], require_cyr=req_cyr, src_text=core, required_keys=req_keys)
            ):
                unm = base.unmask_text(parsed[k], masks_map[k])
                results[ui] = lead + unm + trail
                if unm != core and "@@" not in unm and not base.has_translatable_words(unm):
                    tm[core] = unm
            else:
                results[ui] = _translate_single_java(api_url, units[ui]["text"], glossary, f"Java {rel}", tm)
        base.save_tm_cache()

    return results


def _translate_single_java(api_url: str, raw: str, glossary: dict, ctx: str, tm: dict) -> str:
    lead = raw[: len(raw) - len(raw.lstrip())]
    trail = raw[len(raw.rstrip()) :]
    core = raw.strip()
    if core in tm and "@@" not in tm[core] and not base.has_translatable_words(tm[core]):
        return lead + tm[core] + trail
    masked, masks = mask_java_text(core)
    req_keys = [mk for mk in masks if mk in masked]
    hint = base.build_glossary_hint(core, glossary)
    req_cyr = not bool(re.search(r"[А-Яа-яЁё]", core)) and bool(
        re.search(r"[a-zA-Z]{2,}", base._strip_wrappers_and_masks(PRINTF_SPEC_RE.sub(" ", core)))
    )
    sys_p = (
        "You are an expert Russian literary translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'. "
        "Translate ALL English prose/UI text into natural, idiomatic, uncensored Russian. "
        "Keep wrappers like [npc.speech(...)] or [style.colour(...)] intact while translating English words inside parentheses; NEVER invent new bracket tags, arrows ('->'), or repeat English source. "
        "Treat @@V0@@/@@V1@@ as verbs already present in the sentence. "
        "CRITICAL: Preserve EVERY placeholder token (@@H*@@, @@T*@@, @@V*@@, @@S*@@, @@M*@@, @@K*@@, @@P*@@) verbatim; NEVER omit one. "
        "Output ONLY the Russian translation."
    )
    usr_p = f"Context: {ctx}\n" + (hint + "\n" if hint else "") + f"Text:\n{masked}"
    for attempt in range(4):
        temp = 0.25 if attempt == 0 else min(0.55, 0.25 + 0.1 * attempt)
        out = base.call_llm_raw(
            api_url,
            [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}],
            json_mode=False,
            max_tokens=2048,
            temperature=temp,
        )
        if base.validate_masks(out, masks, require_cyr=req_cyr, src_text=core, required_keys=req_keys):
            unm = base.unmask_text(out, masks)
            if unm != core and "@@" not in unm and not base.has_translatable_words(unm):
                tm[core] = unm
            return lead + unm + trail
    # Fallback 1: split on #IF/#ELSE/#ENDIF, </p>, <br/><br/> directives if present
    toks = base.split_directives(core)
    if len(toks) > 1:
        rebuilt = []
        changed_any = False
        for is_dir, seg in toks:
            if is_dir or not seg.strip() or not base.has_translatable_words(seg):
                rebuilt.append(seg)
            else:
                tr_seg = base.translate_single(api_url, seg, glossary, ctx)
                if tr_seg != seg:
                    changed_any = True
                rebuilt.append(tr_seg)
        if changed_any:
            unm = "".join(rebuilt)
            if "@@" not in unm:
                tm[core] = unm
                return lead + unm + trail
    # Fallback 2: wrapper + sentence-split fallback from translate_lt
    tr_fb = base.translate_single(api_url, core, glossary, ctx)
    if tr_fb != core and "@@" not in tr_fb:
        tm[core] = tr_fb
        return lead + tr_fb + trail
    return raw


def translate_java_file(path: Path, api_url: str, glossary: dict, dry_run: bool = False) -> tuple[bool, int]:
    src = path.read_text(encoding="utf-8")
    rel = path.relative_to(ROOT).as_posix()
    units = collect_string_units(src)
    if not units:
        return False, 0

    if dry_run:
        return False, len(units)

    translated = translate_java_unit_batch(api_url, units, glossary, rel)
    parts = []
    last = 0
    for u, tr in zip(units, translated):
        parts.append(src[last : u["start"]])
        parts.append('"' + escape_java(tr) + '"')
        last = u["end"]
    parts.append(src[last:])
    new_src = "".join(parts)

    if new_src != src:
        path.write_text(new_src, encoding="utf-8")
        return True, len(units)
    return False, len(units)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=base.DEFAULT_API)
    ap.add_argument("--java-file", action="append", required=True)
    ap.add_argument("--dry-run", action="store_true")
    args = ap.parse_args()

    glossary = base.load_glossary()
    for rel in args.java_file:
        p = ROOT / rel
        t0 = time.monotonic()
        changed, count = translate_java_file(p, args.api, glossary, dry_run=args.dry_run)
        print(f"  {rel}: units={count} changed={changed} ({time.monotonic()-t0:.1f}s)", flush=True)
    base.flush_energy_stats()


if __name__ == "__main__":
    main()
