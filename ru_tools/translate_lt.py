#!/usr/bin/env python3
"""
2026-grade automated EN -> RU localization pipeline for Lilith's Throne.
Uses a local OpenAI-compatible llama.cpp server (e.g. Tower-Plus-9B on RTX 3090)
with glossary injection, placeholder/tag masking, XML/Java syntax preservation,
and incremental progress tracking.
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

ROOT = Path(__file__).resolve().parent.parent
GLOSSARY_PATH = Path(__file__).resolve().parent / "GLOSSARY_RU.json"
PROGRESS_PATH = Path(__file__).resolve().parent / "progress.json"
DEFAULT_API = "http://127.0.0.1:8089/v1/chat/completions"

HTML_TAG_RE = re.compile(r"</?[a-zA-Z][^>]*?>")
BRACKET_ATOMIC_RE = re.compile(r"\[[a-zA-Z0-9_]+\.[a-zA-Z0-9_+\-]+\]")
WRAP_OPEN_RE = re.compile(r"\[([a-zA-Z0-9_]+\.[a-zA-Z0-9_]+)\(")


def load_glossary():
    with open(GLOSSARY_PATH, "r", encoding="utf-8") as f:
        return json.load(f)


def load_progress():
    if PROGRESS_PATH.exists():
        with open(PROGRESS_PATH, "r", encoding="utf-8") as f:
            return json.load(f)
    return {"translated_xml": [], "translated_java": []}


def save_progress(prog):
    with open(PROGRESS_PATH, "w", encoding="utf-8") as f:
        json.dump(prog, f, ensure_ascii=False, indent=2)


def mask_text(text: str):
    """Mask HTML tags and bracket tokens so the LLM cannot corrupt syntax."""
    masks = {}
    counter = 0

    def repl(prefix):
        def _inner(m):
            nonlocal counter
            key = f"@@{prefix}{counter}@@"
            masks[key] = m.group(0)
            counter += 1
            return key
        return _inner

    # Mask HTML tags first
    masked = HTML_TAG_RE.sub(repl("H"), text)
    # Mask atomic [obj.prop] tokens
    masked = BRACKET_ATOMIC_RE.sub(repl("T"), masked)
    return masked, masks


def unmask_text(text: str, masks: dict):
    for key, val in masks.items():
        text = text.replace(key, val)
    return text


def validate_masks(translated: str, masks: dict) -> bool:
    for key in masks:
        if key not in translated:
            return False
    return True


def build_glossary_hint(text: str, glossary: dict) -> str:
    low = text.lower()
    hits = []
    for en, ru in glossary["terms"].items():
        if en.lower() in low:
            hits.append(f'"{en}" -> "{ru}"')
    dnt = [n for n in glossary["do_not_translate"] if n in text]
    parts = []
    if hits:
        parts.append("Glossary: " + "; ".join(hits[:25]))
    if dnt:
        parts.append("Keep unchanged: " + ", ".join(dnt))
    return "\n".join(parts)


def get_gpu_temp() -> float:
    try:
        out = subprocess.check_output(
            ["nvidia-smi", "--query-gpu=temperature.gpu", "--format=csv,noheader,nounits"],
            timeout=5,
            text=True,
        ).strip()
        return float(out.splitlines()[0])
    except Exception:
        return 0.0


def thermal_guard(max_temp: float = 75.0, resume_temp: float = 68.0, step_delay: float = 0.4):
    if step_delay > 0:
        time.sleep(step_delay)
    t = get_gpu_temp()
    if t >= max_temp:
        print(f"  [THERMAL GUARD] GPU {t:.1f}C >= {max_temp}C, cooling down to <= {resume_temp}C...", flush=True)
        while t > resume_temp:
            time.sleep(10)
            t = get_gpu_temp()
            print(f"  [THERMAL GUARD] GPU now {t:.1f}C", flush=True)


def call_llm(api_url: str, source_masked: str, glossary_hint: str, context_note: str) -> str:
    thermal_guard()
    sys_prompt = (
        "You are an expert Russian video game translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'.\n"
        "Translate the English text into natural, literary, uncensored Russian.\n"
        "CRITICAL RULES:\n"
        "1. Preserve ALL mask tokens like @@H0@@, @@T1@@, and bracket wrappers like [pc.speech(...)] or [style.bold(...)] EXACTLY as written.\n"
        "2. Translate text INSIDE [obj.speech(...)] or [style.bold(...)] while keeping '[obj.speech(' and ')]' intact.\n"
        "3. Output ONLY the Russian translation, with NO explanations, notes, or markdown fences."
    )
    user_msg = f"Context: {context_note}\n"
    if glossary_hint:
        user_msg += glossary_hint + "\n"
    user_msg += f"Text:\n{source_masked}"

    payload = json.dumps({
        "model": "local",
        "messages": [
            {"role": "system", "content": sys_prompt},
            {"role": "user", "content": user_msg},
        ],
        "temperature": 0.15,
        "max_tokens": 2048,
    }).encode("utf-8")

    req = urllib.request.Request(
        api_url,
        data=payload,
        headers={"Content-Type": "application/json"},
        method="POST",
    )
    with urllib.request.urlopen(req, timeout=180) as r:
        data = json.loads(r.read().decode("utf-8"))
    out = data["choices"][0]["message"]["content"].strip()
    return out


def translate_chunk(api_url: str, text: str, glossary: dict, context_note: str) -> str:
    if not text or not text.strip():
        return text
    # Skip purely technical tokens/numbers
    if re.fullmatch(r"[\s\d\W_]+", text):
        return text

    masked, masks = mask_text(text)
    hint = build_glossary_hint(text, glossary)

    for attempt in range(3):
        out = call_llm(api_url, masked, hint, context_note)
        if validate_masks(out, masks):
            return unmask_text(out, masks)
    # Fallback to original if placeholder integrity failed twice
    return text


def translate_xml_file(path: Path, api_url: str, glossary: dict) -> bool:
    raw = path.read_text(encoding="utf-8")
    # Preserve XML declaration and CDATA sections safely
    # Strategy: translate CDATA blocks (split by </p> paragraphs) and safe leaf text tags
    rel = path.relative_to(ROOT).as_posix()

    # Validate original XML parses
    ET.fromstring(raw)

    cdata_re = re.compile(r"<!\[CDATA\[(.*?)\]\]>", re.S)
    changed = False

    def repl_cdata(m):
        nonlocal changed
        inner = m.group(1)
        if not inner.strip():
            return m.group(0)
        # Split into paragraphs for context window & accuracy
        paras = re.split(r"(</p>)", inner)
        out_parts = []
        buf = ""
        for part in paras:
            buf += part
            if part == "</p>":
                tr = translate_chunk(api_url, buf, glossary, f"XML {rel}")
                out_parts.append(tr)
                buf = ""
        if buf.strip():
            out_parts.append(translate_chunk(api_url, buf, glossary, f"XML {rel}"))
        else:
            out_parts.append(buf)
        new_inner = "".join(out_parts)
        if new_inner != inner:
            changed = True
        return f"<![CDATA[{new_inner}]]>"

    new_raw = cdata_re.sub(repl_cdata, raw)

    # Also translate common short leaf tags if not CDATA
    for tag in ["name", "namePlural", "description", "title", "tooltip", "determiner"]:
        tag_re = re.compile(rf"(<{tag}(?:\s+[^>]*)?>)([^<]+)(</{tag}>)")
        def repl_tag(m):
            nonlocal changed
            txt = m.group(2)
            if not txt.strip() or txt.strip().startswith("<![CDATA["):
                return m.group(0)
            tr = translate_chunk(api_url, txt, glossary, f"XML {rel} <{tag}>")
            if tr != txt:
                changed = True
            return f"{m.group(1)}{tr}{m.group(3)}"
        new_raw = tag_re.sub(repl_tag, new_raw)

    if changed:
        # Validate resulting XML before writing
        ET.fromstring(new_raw)
        path.write_text(new_raw, encoding="utf-8")
    return changed


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=DEFAULT_API)
    ap.add_argument("--xml-file", help="Translate a single XML file (relative to repo root)")
    ap.add_argument("--xml-dir", help="Translate all XML files under directory (relative to repo root)")
    ap.add_argument("--limit", type=int, default=10, help="Max files per batch")
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
        print(f"Translating {rel}...", flush=True)
        try:
            translate_xml_file(p, args.api, glossary)
            if rel not in prog["translated_xml"]:
                prog["translated_xml"].append(rel)
                save_progress(prog)
            print(f"  OK: {rel}", flush=True)
        except Exception as e:
            print(f"  ERR {rel}: {e}", file=sys.stderr, flush=True)


if __name__ == "__main__":
    main()
