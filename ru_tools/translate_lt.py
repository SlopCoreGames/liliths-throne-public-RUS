"""
2026-grade batched EN -> RU localization pipeline for Lilith's Throne.
Features:
- Multi-chunk JSON batching (3-5x faster) with automatic per-item fallback on validation mismatch
- Strict per-directory CDATA parent-tag whitelist so game logic/Nashorn/enums/IDs are never corrupted
- Balanced Nashorn [#...]/[##...], HTML tag, [obj.token], and formattedDamageInflicted masking
- Strict @@ leak detector + XML ElementTree validation + atomic TM cache writes
- Adaptive GPU thermal guard (RTX 3090 HotSpot NVAPI pad protection)
- Cumulative GPU & estimated system energy meter (energy_stats.json)
"""
import argparse
import json
import os
import re
import subprocess
import sys
import time
import urllib.request
import xml.etree.ElementTree as ET
from pathlib import Path
from xml.sax.saxutils import escape as xml_escape

ROOT = Path(__file__).resolve().parent.parent
GLOSSARY_PATH = Path(__file__).resolve().parent / "GLOSSARY_RU.json"
PROGRESS_PATH = Path(__file__).resolve().parent / "progress.json"
ENERGY_PATH = Path(__file__).resolve().parent / "energy_stats.json"
TM_CACHE_PATH = Path(__file__).resolve().parent / "tm_cache.json"
DEFAULT_API = "http://127.0.0.1:8080/v1/chat/completions"
DEFAULT_MODEL = "gemma-4-26b-abliterated"

_TM_CACHE: dict | None = None

HTML_TAG_RE = re.compile(r"</?[a-zA-Z][^>]*?>")
STYLE_MONEY_RE = re.compile(r"\[style\.moneyFormat[a-zA-Z]*\([^\]]*\)\]")
VERB_TAG_RE = re.compile(r"\[[a-zA-Z0-9_]+\.verb(?:Person|Past|Ing)?\([^)\]]*\)\]")
BRACKET_ATOMIC_RE = re.compile(r"\[[a-zA-Z0-9_]+\.[a-zA-Z0-9_+\-]+\]")
LITERAL_TOKEN_RE = re.compile(r"\bformattedDamageInflicted\b")
MASK_TOKEN_RE = re.compile(r"@@[A-Z]\d+@@")
WRAPPER_PREFIX_RE = re.compile(r"\[[a-zA-Z0-9_]+\.[a-zA-Z0-9_]+\(")
EN_PROSE_RUN_RE = re.compile(r"\b[A-Za-z][A-Za-z'\-]*(?:\s+[A-Za-z][A-Za-z'\-]{1,}){2,}\b")
UNTR_SPEECH_RE = re.compile(r"\.(?:speech|speechNoEffects|speechNoExtraEffects|thought)\(([^)]+)\)")
CODE_OR_ENUM_RE = re.compile(r"^(?:true|false|null|[A-Z0-9_]+|[\d.\-+*/\s]+)$")

# Strict whitelist of CDATA parent tags eligible for translation per res/<subdir>.
# Any tag NOT in this map (effects, conditional, weighting, booleans, enums, IDs, determiner, svg names)
# is preserved verbatim.
SAFE_CDATA_TAGS: dict[str, set[str]] = {
    "res/txt": {"htmlContent"},
    "res/dialogue": {"responseTitle", "responseTooltip", "title", "tooltip", "combatant", "tab"},
    "res/clothing": {
        "name",
        "namePlural",
        "description",
        "self",
        "other",
        "otherRough",
        "unavailabilityText",
        "descriptionModification",
        "authorTag",
        "clothingAuthorTag",
        "namePrefix",
        "namePostfix",
        "categoryName",
        "stickerName",
        "availabilityText",
    },
    "res/items": {
        "name",
        "namePlural",
        "description",
        "line",
        "applyEffects",
        "selfUse",
        "otherUse",
        "authorTag",
    },
    "res/weapons": {
        "name",
        "namePlural",
        "description",
        "hitText",
        "missText",
        "equipText",
        "unequipText",
        "attackTooltipDescription",
        "attackDescriptionPrefix",
        "authorTag",
        "clothingAuthorTag",
        "effect",
        "onCriticalHitEffect",
        "onHitEffect",
        "criticalHitText",
        "recoveryText",
    },
    "res/combatMove": {
        "name",
        "description",
        "availabilityDescription",
        "criticalDescription",
        "movePredictionDescriptionWithTarget",
        "movePredictionDescriptionNoTarget",
        "execute",
        "critDescription",
        "critEffectDescription",
    },
    "res/statusEffects": {"name", "description", "effect"},
    "res/outfits": {"name", "description"},
    "res/setBonuses": {"name"},
    "res/tattoos": {"name", "description", "bodyOverviewDescription"},
    "res/race": {
        "name",
        "namePlural",
        "transformationName",
        "bodyDescription",
        "transformationDescription",
        "descriptor",
        "singularMaleName",
        "singularFemaleName",
        "pluralMaleName",
        "pluralFemaleName",
        "htmlContent",
        "description",
        "statusEffectDescription",
        "feralName",
        "feralNamePlural",
        "feralSingularMaleName",
        "feralSingularFemaleName",
        "feralPluralMaleName",
        "feralPluralFemaleName",
        "tipName",
        "tipNamePlural",
        "handName",
        "handNamePlural",
        "fingerName",
        "fingerNamePlural",
        "noseName",
        "noseNamePlural",
        "bodyDescriptionFeral",
        "crotchBoobsTransformationDescription",
        "crotchBoobsBodyDescription",
        "defaultTransformName",
        "effect",
        "bookName",
        "nameTransformation",
        "nameSillyMode",
        "namePluralSillyMode",
    },
    "res/randomEnchantments": {
        "name",
        "namePrefix",
        "namePostfix",
        "nameOverride",
        "applicationSpeechStart",
        "applicationSpeechEnd",
        "effect",
    },
    "res/sex": {
        "description",
        "text",
        "deskName",
        "line",
        "title",
        "tooltip",
        "wallName",
        "startingDescription",
    },
    "res/encounters": {"name"},
    "res/patterns": {"name"},
    "res/colours": {"name"},
    "res/maps": {"name", "tooltipDescription", "virginityLossDescription", "sexBlockedReason"},
}

LEAF_TAG_RE = re.compile(r"(<([a-zA-Z0-9_:-]+)(?:\s+[^>]*)?>)([^<]+)(</\2>)")
CDATA_TAG_RE = re.compile(
    r"(<([a-zA-Z0-9_:-]+)(?:\s+[^>]*)?>\s*)<!\[CDATA\[(.*?)\]\]>(\s*</\2>)",
    re.S,
)
FORMATTING_NAMES_RE = re.compile(r"<formattingNames\b[^>]*>.*?</formattingNames>", re.S)

_SESSION_ENERGY = {
    "gpu_wh": 0.0,
    "sys_wh": 0.0,
    "seconds": 0.0,
    "requests": 0,
    "peak_temp": 0.0,
    "peak_w": 0.0,
}


def load_tm_cache() -> dict:
    global _TM_CACHE
    if _TM_CACHE is None:
        if TM_CACHE_PATH.exists():
            _TM_CACHE = json.loads(TM_CACHE_PATH.read_text(encoding="utf-8"))
        else:
            _TM_CACHE = {}
    return _TM_CACHE


def save_tm_cache():
    if _TM_CACHE is not None:
        tmp = TM_CACHE_PATH.with_suffix(".tmp")
        tmp.write_text(json.dumps(_TM_CACHE, ensure_ascii=False), encoding="utf-8")
        os.replace(tmp, TM_CACHE_PATH)


def load_glossary() -> dict:
    with open(GLOSSARY_PATH, "r", encoding="utf-8") as f:
        return json.load(f)


def load_progress() -> dict:
    if PROGRESS_PATH.exists():
        with open(PROGRESS_PATH, "r", encoding="utf-8") as f:
            return json.load(f)
    return {"translated_xml": [], "translated_java": []}


def save_progress(prog: dict):
    tmp = PROGRESS_PATH.with_suffix(".tmp")
    with open(tmp, "w", encoding="utf-8") as f:
        json.dump(prog, f, ensure_ascii=False, indent=2)
    os.replace(tmp, PROGRESS_PATH)


def load_energy_stats() -> dict:
    if ENERGY_PATH.exists():
        with open(ENERGY_PATH, "r", encoding="utf-8") as f:
            return json.load(f)
    return {
        "total_active_seconds": 0.0,
        "gpu_wh": 0.0,
        "gpu_kwh": 0.0,
        "estimated_system_wh": 0.0,
        "estimated_system_kwh": 0.0,
        "peak_gpu_temp_c": 0.0,
        "peak_gpu_watts": 0.0,
        "total_llm_requests": 0,
        "note": "gpu_wh measured via nvidia-smi power.draw; estimated_system_wh adds ~85W CPU/RAM/board and ~12% PSU loss.",
    }


def save_energy_stats(stats: dict):
    stats["gpu_kwh"] = round(stats["gpu_wh"] / 1000.0, 6)
    stats["estimated_system_kwh"] = round(stats["estimated_system_wh"] / 1000.0, 6)
    tmp = ENERGY_PATH.with_suffix(".tmp")
    with open(tmp, "w", encoding="utf-8") as f:
        json.dump(stats, f, ensure_ascii=False, indent=2)
    os.replace(tmp, ENERGY_PATH)


_NVAPI_INIT = False
_NVAPI_GPU = None
_NVAPI_GET_SENSORS = None


def _init_nvapi_sensors():
    global _NVAPI_INIT, _NVAPI_GPU, _NVAPI_GET_SENSORS
    if _NVAPI_INIT:
        return _NVAPI_GET_SENSORS is not None and _NVAPI_GPU is not None
    _NVAPI_INIT = True
    try:
        import ctypes

        nv = ctypes.WinDLL(r"C:\Windows\System32\nvapi64.dll")
        nv.nvapi_QueryInterface.restype = ctypes.c_void_p
        nv.nvapi_QueryInterface.argtypes = [ctypes.c_uint32]

        def get_fn(fid, restype, argtypes):
            addr = nv.nvapi_QueryInterface(fid)
            return ctypes.CFUNCTYPE(restype, *argtypes)(addr) if addr else None

        init_fn = get_fn(0x0150E828, ctypes.c_int, [])
        enum_fn = get_fn(
            0xE5AC921F,
            ctypes.c_int,
            [ctypes.POINTER(ctypes.c_void_p * 64), ctypes.POINTER(ctypes.c_uint32)],
        )
        sens_fn = get_fn(0x65FE3AAD, ctypes.c_int, [ctypes.c_void_p, ctypes.c_void_p])
        if not (init_fn and enum_fn and sens_fn) or init_fn() != 0:
            return False
        gpus = (ctypes.c_void_p * 64)()
        cnt = ctypes.c_uint32(0)
        if enum_fn(ctypes.byref(gpus), ctypes.byref(cnt)) != 0 or cnt.value == 0:
            return False
        _NVAPI_GPU = gpus[0]
        _NVAPI_GET_SENSORS = sens_fn
        return True
    except Exception:
        return False


def get_nvapi_hotspot_temp() -> tuple[float, float, float]:
    """Return (max_hotspot_c, core_c, vram_c) via NVAPI thermal sensors (mask 0x3FF)."""
    if not _init_nvapi_sensors():
        return 0.0, 0.0, 0.0
    try:
        import ctypes

        class NV_GPU_THERMAL_SENSORS_V2(ctypes.Structure):
            _fields_ = [
                ("version", ctypes.c_uint32),
                ("mask", ctypes.c_uint32),
                ("reserved", ctypes.c_uint32 * 8),
                ("temperatures", ctypes.c_int32 * 32),
            ]

        s = NV_GPU_THERMAL_SENSORS_V2()
        s.version = ctypes.sizeof(s) | (2 << 16)
        s.mask = 0x3FF
        if _NVAPI_GET_SENSORS(_NVAPI_GPU, ctypes.byref(s)) != 0:
            return 0.0, 0.0, 0.0
        temps = [t / 256.0 for t in s.temperatures[:10] if 0 < t < 50000]
        if not temps:
            return 0.0, 0.0, 0.0
        core_c = s.temperatures[0] / 256.0
        hotspot_c = max(temps)
        vram_c = s.temperatures[4] / 256.0
        return hotspot_c, core_c, vram_c
    except Exception:
        return 0.0, 0.0, 0.0


def get_gpu_telemetry() -> tuple[float, float]:
    try:
        out = subprocess.check_output(
            ["nvidia-smi", "--query-gpu=temperature.gpu,power.draw", "--format=csv,noheader,nounits"],
            timeout=5,
            text=True,
        ).strip()
        parts = [p.strip() for p in out.splitlines()[0].split(",")]
        core_c, gpu_w = float(parts[0]), float(parts[1])
        hotspot_c, _nv_core, _vram = get_nvapi_hotspot_temp()
        return max(core_c, hotspot_c), gpu_w
    except Exception:
        return 0.0, 0.0


def record_energy_sample(dt_sec: float, temp_c: float, gpu_w: float):
    if dt_sec <= 0:
        return
    dt_h = dt_sec / 3600.0
    gpu_wh = gpu_w * dt_h
    sys_w = (gpu_w + 85.0) / 0.88
    sys_wh = sys_w * dt_h
    _SESSION_ENERGY["gpu_wh"] += gpu_wh
    _SESSION_ENERGY["sys_wh"] += sys_wh
    _SESSION_ENERGY["seconds"] += dt_sec
    _SESSION_ENERGY["peak_temp"] = max(_SESSION_ENERGY["peak_temp"], temp_c)
    _SESSION_ENERGY["peak_w"] = max(_SESSION_ENERGY["peak_w"], gpu_w)


def adaptive_thermal_guard(max_temp: float = 98.0, resume_temp: float = 86.0):
    t, _w = get_gpu_telemetry()
    if t >= max_temp:
        _hs, core, vram = get_nvapi_hotspot_temp()
        print(
            f"  [THERMAL GUARD] HotSpot/Max {t:.1f}C (Core {core:.1f}C, VRAM {vram:.1f}C) >= {max_temp}C, cooling down to <= {resume_temp}C...",
            flush=True,
        )
        while t > resume_temp:
            time.sleep(10)
            t, _w = get_gpu_telemetry()
            _hs, core, vram = get_nvapi_hotspot_temp()
            print(
                f"  [THERMAL GUARD] HotSpot/Max now {t:.1f}C (Core {core:.1f}C, VRAM {vram:.1f}C)",
                flush=True,
            )
    elif t >= 92.0:
        time.sleep(0.5)
    else:
        time.sleep(0.15)


def _find_matching_bracket(text: str, start: int) -> int:
    """Find matching ']' for '[' at `start`, respecting nested [...] and quoted strings."""
    depth = 0
    in_quote = None
    i = start
    n = len(text)
    while i < n:
        ch = text[i]
        if in_quote:
            if ch == "\\" and i + 1 < n:
                i += 2
                continue
            if ch == in_quote:
                in_quote = None
        else:
            if ch in ("'", '"'):
                in_quote = ch
            elif ch == "[":
                depth += 1
            elif ch == "]":
                depth -= 1
                if depth == 0:
                    return i
        i += 1
    return -1


def _mask_nashorn_blocks(text: str, masks: dict, counter_ref: list[int]) -> str:
    """Mask `[#...]` and `[##...]` blocks including nested brackets safely."""
    out = []
    i = 0
    n = len(text)
    while i < n:
        if text[i] == "[" and i + 1 < n and text[i + 1] == "#":
            end = _find_matching_bracket(text, i)
            if end != -1:
                key = f"@@S{counter_ref[0]}@@"
                counter_ref[0] += 1
                masks[key] = text[i : end + 1]
                out.append(key)
                i = end + 1
                continue
        out.append(text[i])
        i += 1
    return "".join(out)


def mask_text(text: str) -> tuple[str, dict]:
    masks = {}
    counter = [0]

    def repl(prefix):
        def _inner(m):
            key = f"@@{prefix}{counter[0]}@@"
            masks[key] = m.group(0)
            counter[0] += 1
            return key

        return _inner

    masked = _mask_nashorn_blocks(text, masks, counter)
    masked = STYLE_MONEY_RE.sub(repl("M"), masked)
    masked = VERB_TAG_RE.sub(repl("V"), masked)
    masked = HTML_TAG_RE.sub(repl("H"), masked)
    masked = BRACKET_ATOMIC_RE.sub(repl("T"), masked)
    masked = LITERAL_TOKEN_RE.sub(repl("K"), masked)
    return masked, masks


def unmask_text(text: str, masks: dict) -> str:
    for key, val in reversed(list(masks.items())):
        text = text.replace(key, val)
    return text


_IF_OPEN_RE = re.compile(r"#(?:ELSE\s*IF|IF)(?!END)")
_ELSE_KW_RE = re.compile(r"#ELSE(?!\s*IF)")
_ENDIF_KW_RE = re.compile(r"#ENDIF")
_OPT_THEN_RE = re.compile(r"\s*#THEN")


def _skip_conditional_header(text: str, pos: int) -> int:
    """If text[pos:] is a UtilText #IF/#ELSEIF/#ELSE IF header (bracketed or #THEN-terminated), return end index, else -1."""
    m = _IF_OPEN_RE.match(text, pos)
    if not m:
        return -1
    k = m.end()
    n = len(text)
    while k < n and text[k].isspace():
        k += 1
    if k < n and text[k] == "(":
        end_p = _find_matching_paren(text, k)
        if end_p == -1:
            return -1
        end_hdr = end_p + 1
        mt = _OPT_THEN_RE.match(text, end_hdr)
        if mt:
            end_hdr = mt.end()
        return end_hdr
    t_pos = text.find("#THEN", m.end())
    if t_pos != -1:
        return t_pos + 5
    return -1


def _bracket_paren_signature(text: str) -> tuple[tuple[int, ...], tuple[int, ...]]:
    """Return (remaining_open_bracket_paren_deltas, closed_bracket_paren_deltas) outside Nashorn [#...] / #IF headers."""
    # Strip [#...] blocks first so JS syntax inside them does not interfere
    dummy_masks: dict[str, str] = {}
    cleaned = _mask_nashorn_blocks(text, dummy_masks, [0])
    # First pass: compute minimum '[' balance to infer initial open '[' depth from previous chunk
    sq = 0
    min_sq = 0
    i = 0
    n = len(cleaned)
    while i < n:
        if cleaned[i] == "#":
            hdr_end = _skip_conditional_header(cleaned, i)
            if hdr_end != -1:
                i = hdr_end
                continue
        if cleaned[i] == "[":
            sq += 1
        elif cleaned[i] == "]":
            sq -= 1
            if sq < min_sq:
                min_sq = sq
        i += 1
    init_sq = -min_sq
    stack = [0] * init_sq
    closed_deltas: list[int] = []
    i = 0
    while i < n:
        if cleaned[i] == "#":
            hdr_end = _skip_conditional_header(cleaned, i)
            if hdr_end != -1:
                i = hdr_end
                continue
        ch = cleaned[i]
        if ch == "[":
            stack.append(0)
        elif ch == "]":
            if stack:
                closed_deltas.append(stack.pop())
        elif ch == "(" and stack:
            stack[-1] += 1
        elif ch == ")" and stack:
            stack[-1] -= 1
        i += 1
    return tuple(stack), tuple(closed_deltas)


def validate_masks(
    translated: str, masks: dict, require_cyr: bool = False, src_text: str = "", required_keys: list[str] | None = None
) -> bool:
    if not translated or not translated.strip():
        return False
    if "Контекст:" in translated or "Context:" in translated:
        return False
    if any(tok not in masks for tok in MASK_TOKEN_RE.findall(translated)):
        return False
    keys_to_check = required_keys if required_keys is not None else list(masks.keys())
    if not all(key in translated for key in keys_to_check):
        return False
    unmasked = unmask_text(translated, masks)
    if "@@" in unmasked:
        return False
    if src_text:
        if "->" in unmasked and "->" not in src_text:
            return False
        if src_text.count("[") != unmasked.count("[") or src_text.count("]") != unmasked.count("]"):
            return False
        if sorted(WRAPPER_PREFIX_RE.findall(unmasked)) != sorted(WRAPPER_PREFIX_RE.findall(src_text)):
            return False
        if _bracket_paren_signature(unmasked) != _bracket_paren_signature(src_text):
            return False
    if require_cyr and not re.search(r"[А-Яа-яЁё]", unmasked):
        return False
    return True


def build_glossary_hint(text: str, glossary: dict) -> str:
    low = text.lower()
    hits = [f'"{en}"->"{ru}"' for en, ru in glossary["terms"].items() if en.lower() in low]
    dnt = [n for n in glossary["do_not_translate"] if n in text]
    parts = []
    if hits:
        parts.append("Glossary: " + "; ".join(hits[:20]))
    if dnt:
        parts.append("Do not translate names: " + ", ".join(dnt))
    return "\n".join(parts)


def call_llm_raw(api_url: str, messages: list, json_mode: bool = False, max_tokens: int = 3072, temperature: float = 0.25) -> str:
    adaptive_thermal_guard()
    t0 = time.monotonic()
    temp_b, w_b = get_gpu_telemetry()

    body = {
        "model": DEFAULT_MODEL,
        "messages": messages,
        "temperature": temperature,
        "max_tokens": max_tokens,
        "chat_template_kwargs": {"enable_thinking": False},
    }
    if json_mode:
        body["response_format"] = {"type": "json_object"}

    req = urllib.request.Request(
        api_url,
        data=json.dumps(body).encode("utf-8"),
        headers={"Content-Type": "application/json"},
        method="POST",
    )
    with urllib.request.urlopen(req, timeout=600) as r:
        data = json.loads(r.read().decode("utf-8"))

    dt = time.monotonic() - t0
    temp_a, w_a = get_gpu_telemetry()
    avg_w = (w_b + w_a) / 2.0 if (w_b > 0 or w_a > 0) else 0.0
    record_energy_sample(dt, max(temp_b, temp_a), avg_w)
    _SESSION_ENERGY["requests"] += 1
    return data["choices"][0]["message"]["content"].strip()


def _strip_wrappers_and_masks(text: str) -> str:
    """Strip Nashorn/HTML/atomic/verb masks and `[obj.func(` prefixes leaving only visible prose."""
    masked, _ = mask_text(text)
    s = MASK_TOKEN_RE.sub(" ", masked)
    # Remove wrapper prefixes like `[npc.speech(` or `[style.colourSex(` and closing `)]` or `]`
    s = WRAPPER_PREFIX_RE.sub(" ", s)
    s = s.replace(")]", " ")
    # Also remove XML character entities so &nbsp; / &amp; don't trigger false positives
    s = re.sub(r"&(?:amp|lt|gt|quot|apos|nbsp|#\d+|#x[0-9a-fA-F]+);", " ", s)
    return s


def has_translatable_words(text: str) -> bool:
    """Return True only if text contains actual English prose outside masks/wrappers/enums."""
    s = text.strip()
    if not s or CODE_OR_ENUM_RE.match(s):
        return False
    for m in UNTR_SPEECH_RE.finditer(text):
        inner = m.group(1)
        if re.search(r"[a-zA-Z]{2,}", inner) and not re.search(r"[А-Яа-яЁё]", inner):
            return True
    stripped = _strip_wrappers_and_masks(text)
    if re.search(r"[А-Яа-яЁё]", text):
        # Already has Russian: only treat as needing translation if there is still an English phrase run (>=2 words)
        return bool(EN_PROSE_RUN_RE.search(stripped))
    return bool(re.search(r"[a-zA-Z]{2,}", stripped))


SPEECH_WRAPPER_START_RE = re.compile(
    r"\[([a-zA-Z0-9_]+\.(?:speech|speechNoEffects|speechNoExtraEffects|thought))\("
)


def _translate_with_wrapper_fallback(api_url: str, core: str, glossary: dict, context_note: str) -> str | None:
    """Translate speech/thought wrappers first, mask them as @@W*@@, then translate outer prose."""
    spans = []
    pos = 0
    n = len(core)
    while pos < n:
        m = SPEECH_WRAPPER_START_RE.search(core, pos)
        if not m:
            break
        open_p = m.end() - 1
        end_p = _find_matching_paren(core, open_p)
        if end_p == -1 or end_p + 1 >= n or core[end_p + 1] != "]":
            pos = m.end()
            continue
        inner = core[m.end() : end_p]
        spans.append((m.start(), end_p + 2, f"[{m.group(1)}(", inner, ")]"))
        pos = end_p + 2
    if not spans:
        return None

    w_masks: dict[str, str] = {}
    parts = []
    last = 0
    for idx, (st, en, pref, inner, suff) in enumerate(spans):
        parts.append(core[last:st])
        inner_ru = translate_single(api_url, inner, glossary, context_note, allow_wrapper_fallback=False)
        key = f"@@W{idx}@@"
        w_masks[key] = f"{pref}{inner_ru}{suff}"
        parts.append(key)
        last = en
    parts.append(core[last:])
    outer_src = "".join(parts)

    if not has_translatable_words(outer_src):
        return unmask_text(outer_src, w_masks)

    masked_outer, outer_masks = mask_text(outer_src)
    # Keep w_masks LAST so reversed() unmasks W* only if needed after outer masks, or vice versa:
    # actually outer_masks may wrap W* inside #IF/#ELSE? No, split_directives already split #IF.
    # Put outer_masks then w_masks so reversed() unmasks W* FIRST, or outer_masks AFTER W*?
    # W* contains NO @@ tokens, while HTML/S blocks aren't inside W*; ordering doesn't conflict, and
    # putting outer_masks BEFORE w_masks unmasks w_masks first then outer_masks cleanly.
    all_masks = {**outer_masks, **w_masks}
    hint = build_glossary_hint(outer_src, glossary)
    require_cyr = not bool(re.search(r"[А-Яа-яЁё]", outer_src)) and bool(
        re.search(r"[a-zA-Z]{2,}", _strip_wrappers_and_masks(outer_src))
    )
    sys_p = (
        "You are an expert Russian literary translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'. "
        "Translate ALL English prose into natural, idiomatic, uncensored Russian. "
        "Treat @@W0@@/@@W1@@ tokens as complete quoted character speech clauses inside the sentence. "
        "CRITICAL: Keep EVERY @@...@@ token in your translation — NEVER omit a @@T*@@ token even if English grammar seems redundant in Russian. "
        "Output ONLY the Russian translation without notes."
    )
    req_keys = [k for k in all_masks if k in masked_outer]
    usr_p = f"Context: {context_note}\n" + (hint + "\n" if hint else "") + f"Text:\n{masked_outer}"
    for attempt in range(3):
        out = call_llm_raw(
            api_url,
            [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}],
            json_mode=False,
            max_tokens=2048,
            temperature=0.25 + 0.1 * attempt,
        )
        if validate_masks(
            out, all_masks, require_cyr=require_cyr, src_text=unmask_text(outer_src, w_masks), required_keys=req_keys
        ):
            return unmask_text(out, all_masks)
    return None


def _split_sentences_outside_brackets(text: str) -> list[str]:
    """Split text into sentence chunks preserving spacing, only splitting outside [...] and (...)."""
    chunks = []
    start = 0
    b_depth = 0
    p_depth = 0
    i = 0
    n = len(text)
    while i < n:
        c = text[i]
        if c == "[":
            b_depth += 1
        elif c == "]" and b_depth > 0:
            b_depth -= 1
        elif c == "(":
            p_depth += 1
        elif c == ")" and p_depth > 0:
            p_depth -= 1
        elif b_depth == 0 and p_depth == 0 and c in ".!?" and i + 1 < n and text[i + 1].isspace():
            j = i + 1
            while j < n and text[j].isspace():
                j += 1
            chunks.append(text[start:j])
            start = j
            i = j
            continue
        i += 1
    if start < n:
        chunks.append(text[start:])
    return chunks


def translate_single(
    api_url: str,
    text: str,
    glossary: dict,
    context_note: str,
    allow_wrapper_fallback: bool = True,
    allow_sentence_split: bool = True,
) -> str:
    if not text or not text.strip() or not has_translatable_words(text):
        return text
    lead = text[: len(text) - len(text.lstrip())]
    trail = text[len(text.rstrip()) :]
    core = text.strip()
    masked, masks = mask_text(core)
    req_keys = [k for k in masks if k in masked]
    hint = build_glossary_hint(core, glossary)
    require_cyr = not bool(re.search(r"[А-Яа-яЁё]", core)) and bool(re.search(r"[a-zA-Z]{2,}", _strip_wrappers_and_masks(core)))
    sys_p = (
        "You are an expert Russian literary translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'. "
        "Translate ALL English prose into natural, idiomatic, uncensored Russian (avoid stiff literal English calques). "
        "If the text ALREADY contains wrappers like [npc.speech(...)] or [style.colour(...)], translate ONLY the English prose inside the parentheses while keeping those wrapper prefixes intact; do NOT invent new bracket tags, arrows ('->'), or repeat the English original. "
        "Treat @@V0@@/@@V1@@ tokens as verbs already present in the sentence (do NOT insert a second verb right next to a @@V*@@ token). "
        "Prefer present tense for 'you' ('ты' — e.g. 'ты видишь', 'ты берёшь') so it fits any player gender naturally. "
        "CRITICAL: Preserve EVERY exact @@*@@ token present in the input text — NEVER drop or merge a @@T*@@ pronoun token! "
        "Output ONLY the Russian translation without notes."
    )
    usr_p = f"Context: {context_note}\n" + (hint + "\n" if hint else "") + f"Text:\n{masked}"
    for attempt in range(4):
        temp = 0.25 if attempt == 0 else min(0.55, 0.25 + 0.1 * attempt)
        out = call_llm_raw(
            api_url,
            [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}],
            json_mode=False,
            max_tokens=2048,
            temperature=temp,
        )
        if validate_masks(out, masks, require_cyr=require_cyr, src_text=core, required_keys=req_keys):
            return lead + unmask_text(out, masks) + trail
    if allow_wrapper_fallback:
        fallback = _translate_with_wrapper_fallback(api_url, core, glossary, context_note)
        if fallback is not None:
            return lead + fallback + trail
    if allow_sentence_split:
        sents = _split_sentences_outside_brackets(core)
        if len(sents) > 1:
            translated_sents = [
                translate_single(
                    api_url, s, glossary, context_note, allow_wrapper_fallback=allow_wrapper_fallback, allow_sentence_split=False
                )
                for s in sents
            ]
            joined = "".join(translated_sents)
            if joined != core:
                return lead + joined + trail
    return text


def translate_batch(api_url: str, items: list[str], glossary: dict, context_note: str) -> list[str]:
    if not items:
        return []
    tm = load_tm_cache()
    if len(items) == 1:
        s0 = items[0]
        if not has_translatable_words(s0):
            return [s0]
        if s0 in tm and "@@" not in tm[s0] and not has_translatable_words(tm[s0]):
            return [tm[s0]]
        res0 = translate_single(api_url, s0, glossary, context_note)
        if res0 != s0 and "@@" not in res0 and not has_translatable_words(res0):
            tm[s0] = res0
            save_tm_cache()
        return [res0]

    results = list(items)
    idx = 0
    while idx < len(items):
        batch_indices = []
        char_sum = 0
        while idx < len(items) and len(batch_indices) < 10:
            s = items[idx]
            if not s or not s.strip() or not has_translatable_words(s):
                idx += 1
                continue
            cached = tm.get(s)
            if cached and "@@" not in cached and not has_translatable_words(cached):
                results[idx] = cached
                idx += 1
                continue
            if batch_indices and char_sum + len(s) > 1400:
                break
            batch_indices.append(idx)
            char_sum += len(s)
            idx += 1

        if not batch_indices:
            continue
        if len(batch_indices) == 1:
            i = batch_indices[0]
            res_i = translate_single(api_url, items[i], glossary, context_note)
            results[i] = res_i
            if res_i != items[i] and "@@" not in res_i and not has_translatable_words(res_i):
                tm[items[i]] = res_i
                save_tm_cache()
            continue

        masked_map = {}
        masks_map = {}
        meta_map = {}
        combined_src = []
        for pos, orig_i in enumerate(batch_indices):
            orig = items[orig_i]
            lead = orig[: len(orig) - len(orig.lstrip())]
            trail = orig[len(orig.rstrip()) :]
            core = orig.strip()
            m_txt, m_dict = mask_text(core)
            req_cyr = not bool(re.search(r"[А-Яа-яЁё]", core)) and bool(re.search(r"[a-zA-Z]{2,}", _strip_wrappers_and_masks(core)))
            req_keys = [k for k in m_dict if k in m_txt]
            masked_map[str(pos)] = m_txt
            masks_map[str(pos)] = m_dict
            meta_map[str(pos)] = (lead, core, trail, req_cyr, req_keys)
            combined_src.append(core)

        hint = build_glossary_hint("\n".join(combined_src), glossary)
        sys_p = (
            "You are an expert Russian literary translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'. "
            "Translate ONLY the English prose in each JSON string value into natural, idiomatic, uncensored Russian. "
            "If a value contains wrappers like [npc.speech(...)] or [style.colour(...)], translate only the English words inside the parentheses while keeping those wrapper prefixes unchanged; NEVER invent new bracket tags, arrows ('->'), or repeat the English source. "
            "Treat @@V0@@/@@V1@@ tokens as verbs already present in the sentence (do NOT add an extra verb right next to a @@V*@@ token). "
            "Prefer present tense for 'you' ('ты') where natural so it fits any player gender. "
            "CRITICAL: Keep exact JSON keys and preserve EVERY exact placeholder token (@@H*@@, @@T*@@, @@V*@@, @@S*@@, @@M*@@, @@K*@@) without altering or inventing any tokens. "
            "Return ONLY a valid JSON object."
        )
        usr_p = (
            f"Context: {context_note}\n"
            + (hint + "\n" if hint else "")
            + "Input JSON:\n"
            + json.dumps(masked_map, ensure_ascii=False)
        )

        parsed = None
        try:
            raw_out = call_llm_raw(
                api_url,
                [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}],
                json_mode=True,
                max_tokens=3072,
            )
            raw_out = re.sub(r"^```(?:json)?\s*|\s*```$", "", raw_out, flags=re.S).strip()
            parsed = json.loads(raw_out)
        except Exception:
            parsed = None

        for pos, orig_i in enumerate(batch_indices):
            k = str(pos)
            lead, core, trail, req_cyr, req_keys = meta_map[k]
            if (
                isinstance(parsed, dict)
                and isinstance(parsed.get(k), str)
                and validate_masks(parsed[k], masks_map[k], require_cyr=req_cyr, src_text=core, required_keys=req_keys)
            ):
                tr_val = lead + unmask_text(parsed[k], masks_map[k]) + trail
            else:
                tr_val = translate_single(api_url, items[orig_i], glossary, context_note)
            results[orig_i] = tr_val
            if tr_val != items[orig_i] and "@@" not in tr_val and not has_translatable_words(tr_val):
                tm[items[orig_i]] = tr_val
        save_tm_cache()

    return results


def _find_matching_paren(text: str, open_idx: int) -> int:
    """Find matching ')' for '(' at `open_idx`, respecting nested () and quotes."""
    depth = 0
    in_quote = None
    i = open_idx
    n = len(text)
    while i < n:
        ch = text[i]
        if in_quote:
            if ch == "\\" and i + 1 < n:
                i += 2
                continue
            if ch == in_quote:
                in_quote = None
        else:
            if ch in ("'", '"'):
                in_quote = ch
            elif ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
                if depth == 0:
                    return i
        i += 1
    return -1


def split_long_prose(text: str, max_len: int = 850) -> list[str]:
    """Split oversized prose blocks into <= max_len sub-chunks along blank lines, <br/>, newlines, or sentences."""
    if len(text) <= max_len:
        return [text]
    # Split on newlines or single <br/> preserving separators as standalone items or attached ends
    parts = re.split(r"(<br\s*/?>|\r?\n)", text)
    out = []
    buf = ""
    for p in parts:
        if not p:
            continue
        if len(buf) + len(p) > max_len and buf.strip():
            out.append(buf)
            buf = p
        else:
            buf += p
    if buf:
        out.append(buf)
    final_out = []
    for chunk in out:
        if len(chunk) <= max_len:
            final_out.append(chunk)
        else:
            # Sentence boundary split outside [...] brackets
            sub_start = 0
            depth = 0
            last_split = 0
            i = 0
            while i < len(chunk):
                c = chunk[i]
                if c == "[":
                    depth += 1
                elif c == "]" and depth > 0:
                    depth -= 1
                elif depth == 0 and c in ".!?" and i + 1 < len(chunk) and chunk[i + 1].isspace():
                    if i + 1 - sub_start >= max_len * 0.7:
                        final_out.append(chunk[sub_start : i + 1])
                        sub_start = i + 1
                    else:
                        last_split = i + 1
                if i - sub_start >= max_len and last_split > sub_start:
                    final_out.append(chunk[sub_start:last_split])
                    sub_start = last_split
                i += 1
            if sub_start < len(chunk):
                final_out.append(chunk[sub_start:])
    return final_out


def split_directives(inner: str) -> list[tuple[bool, str]]:
    """Split CDATA prose strictly on `#IF(...)`, `#ELSEIF(...)`, `#ELSE`, `#ENDIF`, `</p>`, and double `<br/>`."""
    tokens: list[tuple[bool, str]] = []
    buf_start = 0
    i = 0
    n = len(inner)
    while i < n:
        # Check </p>
        if inner.startswith("</p>", i):
            if i > buf_start:
                for sub in split_long_prose(inner[buf_start:i]):
                    tokens.append((False, sub))
            tokens.append((True, "</p>"))
            i += 4
            buf_start = i
            continue
        # Check double/multi <br/><br/> paragraph break
        m_br = re.match(r"(?:\s*<br\s*/?>\s*){2,}", inner[i:])
        if m_br:
            if i > buf_start:
                for sub in split_long_prose(inner[buf_start:i]):
                    tokens.append((False, sub))
            tokens.append((True, m_br.group(0)))
            i += len(m_br.group(0))
            buf_start = i
            continue
        # Check #IF(...) / #IF...#THEN / #ELSEIF / #ELSE / #ENDIF
        if inner[i] == "#":
            hdr_end = _skip_conditional_header(inner, i)
            if hdr_end != -1:
                if i > buf_start:
                    for sub in split_long_prose(inner[buf_start:i]):
                        tokens.append((False, sub))
                tokens.append((True, inner[i:hdr_end]))
                i = hdr_end
                buf_start = i
                continue
            m_kw = _ELSE_KW_RE.match(inner, i) or _ENDIF_KW_RE.match(inner, i)
            if m_kw:
                if i > buf_start:
                    for sub in split_long_prose(inner[buf_start:i]):
                        tokens.append((False, sub))
                tokens.append((True, m_kw.group(0)))
                i = m_kw.end()
                buf_start = i
                continue
        i += 1
    if buf_start < n:
        for sub in split_long_prose(inner[buf_start:n]):
            tokens.append((False, sub))
    return tokens


def _safe_xml_text(s: str) -> str:
    """Escape raw XML metacharacters in non-CDATA leaf elements while preserving valid entities."""
    s = re.sub(r"&(?!amp;|lt;|gt;|quot;|apos;|#\d+;|#x[0-9a-fA-F]+;)", "&amp;", s)
    return s.replace("<", "&lt;").replace(">", "&gt;")


def translate_xml_file(path: Path, api_url: str, glossary: dict) -> bool:
    raw = path.read_text(encoding="utf-8")
    rel = path.relative_to(ROOT).as_posix()
    ET.fromstring(raw)

    parts_top = rel.split("/")
    top_dir = "/".join(parts_top[:2]) if len(parts_top) >= 2 else rel
    if top_dir == "res/mods" and len(parts_top) >= 4:
        mod_cat = parts_top[3]
        if mod_cat == "items" and len(parts_top) >= 5:
            item_sub = parts_top[4]
            top_dir = f"res/{item_sub}" if item_sub in {"clothing", "items", "tattoos", "weapons"} else "res/items"
        else:
            top_dir = f"res/{mod_cat}"
    allowed_cdata = SAFE_CDATA_TAGS.get(top_dir, set())
    if not allowed_cdata:
        return False

    # Protect <formattingNames>...</formattingNames> (lookup identifiers in res/colours) verbatim
    fmt_blocks = []

    def _mask_fmt(m):
        idx = len(fmt_blocks)
        fmt_blocks.append(m.group(0))
        return f"__LT_FMT_NAMES_{idx}__"

    working = FORMATTING_NAMES_RE.sub(_mask_fmt, raw)

    # Mask XML comments verbatim so nothing inside comments gets altered
    comment_blocks = []

    def _mask_comment(m):
        idx = len(comment_blocks)
        comment_blocks.append(m.group(0))
        return f"__LT_COMMENT_{idx}__"

    working = re.sub(r"<!--.*?-->", _mask_comment, working, flags=re.S)

    cdata_blocks: list[tuple[str, str]] = []

    def collect_cdata(m):
        open_part, tag, inner, close_part = m.group(1), m.group(2), m.group(3), m.group(4)
        idx = len(cdata_blocks)
        cdata_blocks.append((tag, inner))
        return f"{open_part}__CDATA_BLOCK_{idx}__{close_part}"

    skeleton = CDATA_TAG_RE.sub(collect_cdata, working)

    cdata_paras = []
    block_splits: list[list[str]] = []
    for b_idx, (tag, inner) in enumerate(cdata_blocks):
        if tag not in allowed_cdata:
            block_splits.append([inner])
            continue
        toks = split_directives(inner)
        chunks = [txt for _, txt in toks]
        block_splits.append(chunks)
        for p_idx, (is_dir, ch) in enumerate(toks):
            if not is_dir and ch.strip() and has_translatable_words(ch):
                cdata_paras.append((b_idx, p_idx, ch))

    leaf_matches = list(LEAF_TAG_RE.finditer(skeleton))
    leaf_texts = []
    valid_leaf_indices = []
    for m_idx, m in enumerate(leaf_matches):
        tag_name = m.group(2)
        txt = m.group(3)
        if (
            tag_name in allowed_cdata
            and txt.strip()
            and "__CDATA_BLOCK_" not in txt
            and "__LT_" not in txt
            and has_translatable_words(txt)
        ):
            valid_leaf_indices.append(m_idx)
            leaf_texts.append(txt)

    all_inputs = [x[2] for x in cdata_paras] + leaf_texts
    if not all_inputs:
        return False

    all_outputs = translate_batch(api_url, all_inputs, glossary, f"XML {rel}")

    n_cdata = len(cdata_paras)
    for k, (b_idx, p_idx, _) in enumerate(cdata_paras):
        block_splits[b_idx][p_idx] = all_outputs[k]

    leaf_out_map = {
        valid_leaf_indices[i]: _safe_xml_text(all_outputs[n_cdata + i])
        for i in range(len(valid_leaf_indices))
    }
    if leaf_out_map:
        out_parts = []
        last = 0
        for m_idx, m in enumerate(leaf_matches):
            out_parts.append(skeleton[last : m.start()])
            if m_idx in leaf_out_map:
                out_parts.append(f"{m.group(1)}{leaf_out_map[m_idx]}{m.group(4)}")
            else:
                out_parts.append(m.group(0))
            last = m.end()
        out_parts.append(skeleton[last:])
        skeleton = "".join(out_parts)

    for b_idx, chunks in enumerate(block_splits):
        new_inner = "".join(chunks)
        skeleton = skeleton.replace(f"__CDATA_BLOCK_{b_idx}__", f"<![CDATA[{new_inner}]]>", 1)

    for c_idx, c_val in enumerate(comment_blocks):
        skeleton = skeleton.replace(f"__LT_COMMENT_{c_idx}__", c_val, 1)

    for f_idx, f_val in enumerate(fmt_blocks):
        skeleton = skeleton.replace(f"__LT_FMT_NAMES_{f_idx}__", f_val, 1)

    if skeleton != raw:
        ET.fromstring(skeleton)
        path.write_text(skeleton, encoding="utf-8")
        return True
    return False


def flush_energy_stats():
    if _SESSION_ENERGY["seconds"] > 0:
        stats = load_energy_stats()
        stats["total_active_seconds"] = round(stats["total_active_seconds"] + _SESSION_ENERGY["seconds"], 2)
        stats["gpu_wh"] = round(stats["gpu_wh"] + _SESSION_ENERGY["gpu_wh"], 4)
        stats["estimated_system_wh"] = round(stats["estimated_system_wh"] + _SESSION_ENERGY["sys_wh"], 4)
        stats["peak_gpu_temp_c"] = max(stats["peak_gpu_temp_c"], _SESSION_ENERGY["peak_temp"])
        stats["peak_gpu_watts"] = max(stats["peak_gpu_watts"], _SESSION_ENERGY["peak_w"])
        stats["total_llm_requests"] += _SESSION_ENERGY["requests"]
        save_energy_stats(stats)
        print(
            f"[ENERGY] Session: GPU {_SESSION_ENERGY['gpu_wh']:.2f} Wh | Est. System {_SESSION_ENERGY['sys_wh']:.2f} Wh "
            f"| Peak GPU {_SESSION_ENERGY['peak_temp']:.1f}C / {_SESSION_ENERGY['peak_w']:.1f}W "
            f"|| Cumulative: GPU {stats['gpu_kwh']:.4f} kWh | Est. System {stats['estimated_system_kwh']:.4f} kWh",
            flush=True,
        )


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=DEFAULT_API)
    ap.add_argument("--xml-file", help="Translate a single XML file")
    ap.add_argument("--xml-dir", help="Translate XML files under directory")
    ap.add_argument("--limit", type=int, default=20)
    args = ap.parse_args()

    glossary = load_glossary()
    prog = load_progress()

    targets = []
    if args.xml_file:
        targets = [ROOT / args.xml_file]
    elif args.xml_dir:
        base = ROOT / args.xml_dir
        for p in sorted(base.rglob("*.xml")):
            rel = p.relative_to(ROOT).as_posix()
            if rel not in prog["translated_xml"]:
                targets.append(p)
            if len(targets) >= args.limit:
                break

    for p in targets:
        rel = p.relative_to(ROOT).as_posix()
        t0 = time.monotonic()
        print(f"Translating {rel}...", flush=True)
        try:
            changed = translate_xml_file(p, args.api, glossary)
            if rel not in prog["translated_xml"]:
                prog["translated_xml"].append(rel)
                save_progress(prog)
            print(f"  OK ({time.monotonic()-t0:.1f}s, changed={changed}): {rel}", flush=True)
        except Exception as e:
            print(f"  ERR {rel}: {e}", file=sys.stderr, flush=True)

    flush_energy_stats()


if __name__ == "__main__":
    main()
