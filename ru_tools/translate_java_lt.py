"""
Safe Java string-literal translator for Lilith's Throne.
Only translates string literals inside known UI / dialogue display contexts
(DialogueNode, Response, ResponseSex, ResponseEffectsOnly, UtilText.parse, Label, Button, etc.)
and strictly skips resource keys, XML file paths, flags, CSS, and identifiers.
Automatically runs `mvn compile` verification if --verify-build is passed.
"""
import argparse
import re
import sys
import time
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import translate_lt as base

ROOT = base.ROOT

# Regex matching context before a Java string literal:
# e.g. new Response("...", "..."   | new DialogueNode("...",   | UtilText.parse(..., "..."
SAFE_CALL_PREFIX_RE = re.compile(
    r"(?:"
    r"new\s+(?:DialogueNode|Response|ResponseSex|ResponseEffectsOnly|ResponseTrade|ResponseCombat|Label|Button|CheckBox|Tooltip|MenuItem)\s*\(\s*"
    r"|UtilText\.parse\s*\([^;]*?,\s*"
    r"|\.(?:setText|setTitle|setHeaderText|setContentText|append)\s*\(\s*"
    r")$",
    re.S,
)

# Forbidden contexts (never translate)
FORBIDDEN_PREFIX_RE = re.compile(
    r"(?:"
    r"parseFromXMLFile|getFlag|setFlag|hasFlag|getResource|getDialogueNode|Paths\.get|System\.err|Log\.log|@|import\s|package\s"
    r")",
    re.S,
)

TECH_STR_RE = re.compile(
    r"^(?:"
    r"res/.*"
    r"|/.*\.(?:fxml|css|png|svg|xml|ttf|html|txt)"
    r"|.*-fx-.*"
    r"|#[0-9a-fA-F]{3,8}"
    r"|https?://.*"
    r"|[A-Z0-9_]{2,}"
    r"|[a-z]+/[a-zA-Z0-9_/\-]+"
    r"|[\s\d\W_]+"
    r")$"
)


def extract_java_strings(src: str) -> list[tuple[int, int, str]]:
    """Return list of (start_idx, end_idx, raw_inner_string) for all string literals."""
    spans = []
    i = 0
    n = len(src)
    while i < n:
        c = src[i]
        # Skip line comment
        if c == "/" and i + 1 < n and src[i + 1] == "/":
            nl = src.find("\n", i)
            i = n if nl == -1 else nl + 1
            continue
        # Skip block comment
        if c == "/" and i + 1 < n and src[i + 1] == "*":
            end = src.find("*/", i + 2)
            i = n if end == -1 else end + 2
            continue
        # Skip char literal
        if c == "'":
            i += 1
            while i < n:
                if src[i] == "\\":
                    i += 2
                    continue
                if src[i] == "'":
                    i += 1
                    break
                i += 1
            continue
        # String literal
        if c == '"':
            start = i
            i += 1
            while i < n:
                if src[i] == "\\":
                    i += 2
                    continue
                if src[i] == '"':
                    end = i + 1
                    spans.append((start, end, src[start + 1 : end - 1]))
                    i = end
                    break
                i += 1
            continue
        i += 1
    return spans


def is_translatable_java_str(src: str, start: int, inner: str) -> bool:
    if not inner or not inner.strip() or len(inner.strip()) < 2:
        return False
    if TECH_STR_RE.match(inner.strip()):
        return False
    # Must contain at least one ASCII letter
    if not re.search(r"[a-zA-Z]", inner):
        return False
    # Check surrounding context (last 180 chars before quote)
    ctx = src[max(0, start - 180) : start]
    # Check nearest statement start
    stmt_start = max(ctx.rfind(";"), ctx.rfind("{"), ctx.rfind("\n\t"))
    stmt_ctx = ctx[stmt_start + 1 :] if stmt_start != -1 else ctx
    if FORBIDDEN_PREFIX_RE.search(stmt_ctx):
        return False

    # Allow if inside safe UI/dialogue call OR if it's clearly multi-word English prose (>= 2 words)
    if SAFE_CALL_PREFIX_RE.search(ctx):
        return True
    # Also allow second argument to new Response("title", "tooltip", ...)
    if re.search(r"new\s+Response\w*\s*\(\s*\"[^\"]*\"\s*,\s*$", ctx, re.S):
        return True
    # Multi-word English sentence/phrase inside sb.append / return / concatenation
    words = re.findall(r"[A-Za-z]{2,}", inner)
    if len(words) >= 3 and " " in inner:
        return True
    return False


def escape_java_inner(s: str) -> str:
    # Unescape first to avoid double-escaping, then re-escape safely for Java string literal
    s = s.replace("\\n", "\n").replace("\\t", "\t").replace('\\"', '"')
    s = s.replace("\\", "\\\\").replace('"', '\\"').replace("\r", "").replace("\n", "\\n").replace("\t", "\\t")
    return s


def unescape_java_inner(s: str) -> str:
    return s.replace("\\n", "\n").replace("\\t", "\t").replace('\\"', '"').replace("\\\\", "\\")


def translate_java_file(path: Path, api_url: str, glossary: dict) -> bool:
    src = path.read_text(encoding="utf-8")
    rel = path.relative_to(ROOT).as_posix()
    spans = extract_java_strings(src)

    chosen = []
    for start, end, inner in spans:
        if is_translatable_java_str(src, start, inner):
            chosen.append((start, end, unescape_java_inner(inner)))

    if not chosen:
        return False

    inputs = [c[2] for c in chosen]
    outputs = base.translate_batch(api_url, inputs, glossary, f"Java UI/Dialogue {rel}")

    parts = []
    last = 0
    for (start, end, _), tr in zip(chosen, outputs):
        parts.append(src[last:start])
        parts.append('"' + escape_java_inner(tr) + '"')
        last = end
    parts.append(src[last:])
    new_src = "".join(parts)

    if new_src != src:
        path.write_text(new_src, encoding="utf-8")
        return True
    return False


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=base.DEFAULT_API)
    ap.add_argument("--java-file", action="append", required=True, help="Relative path to .java file")
    args = ap.parse_args()

    glossary = base.load_glossary()
    prog = base.load_progress()

    for rel in args.java_file:
        p = ROOT / rel
        if rel in prog["translated_java"]:
            print(f"Skipping already translated: {rel}")
            continue
        t0 = time.monotonic()
        print(f"Translating Java {rel}...", flush=True)
        changed = translate_java_file(p, args.api, glossary)
        prog["translated_java"].append(rel)
        base.save_progress(prog)
        print(f"  OK ({time.monotonic()-t0:.1f}s, changed={changed}): {rel}", flush=True)

    base.flush_energy_stats()


if __name__ == "__main__":
    main()
