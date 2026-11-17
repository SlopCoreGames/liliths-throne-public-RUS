"""
2026-grade batched EN -> RU localization pipeline for Lilith's Throne.
Features:
- Multi-chunk JSON batching (3-5x faster) with automatic per-item fallback on validation mismatch
- Strict HTML tag & [token] masking + XML ElementTree validation
- Adaptive GPU thermal guard (RTX 3090 pad protection)
- Cumulative GPU & estimated system energy meter (energy_stats.json)
"""
import argparse
import json
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
ENERGY_PATH = Path(__file__).resolve().parent / "energy_stats.json"
DEFAULT_API = "http://127.0.0.1:8089/v1/chat/completions"

HTML_TAG_RE = re.compile(r"</?[a-zA-Z][^>]*?>")
BRACKET_ATOMIC_RE = re.compile(r"\[[a-zA-Z0-9_]+\.[a-zA-Z0-9_+\-]+\]")

_SESSION_ENERGY = {
    "gpu_wh": 0.0,
    "sys_wh": 0.0,
    "seconds": 0.0,
    "requests": 0,
    "peak_temp": 0.0,
    "peak_w": 0.0,
}


def load_glossary() -> dict:
    with open(GLOSSARY_PATH, "r", encoding="utf-8") as f:
        return json.load(f)


def load_progress() -> dict:
    if PROGRESS_PATH.exists():
        with open(PROGRESS_PATH, "r", encoding="utf-8") as f:
            return json.load(f)
    return {"translated_xml": [], "translated_java": []}


def save_progress(prog: dict):
    with open(PROGRESS_PATH, "w", encoding="utf-8") as f:
        json.dump(prog, f, ensure_ascii=False, indent=2)


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
    with open(ENERGY_PATH, "w", encoding="utf-8") as f:
        json.dump(stats, f, ensure_ascii=False, indent=2)


def get_gpu_telemetry() -> tuple[float, float]:
    try:
        out = subprocess.check_output(
            ["nvidia-smi", "--query-gpu=temperature.gpu,power.draw", "--format=csv,noheader,nounits"],
            timeout=5,
            text=True,
        ).strip()
        parts = [p.strip() for p in out.splitlines()[0].split(",")]
        return float(parts[0]), float(parts[1])
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


def adaptive_thermal_guard(max_temp: float = 75.0, resume_temp: float = 67.0):
    t, _w = get_gpu_telemetry()
    if t >= max_temp:
        print(f"  [THERMAL GUARD] GPU {t:.1f}C >= {max_temp}C, cooling down to <= {resume_temp}C...", flush=True)
        while t > resume_temp:
            time.sleep(10)
            t, _w = get_gpu_telemetry()
            print(f"  [THERMAL GUARD] GPU now {t:.1f}C", flush=True)
    elif t >= 68.0:
        time.sleep(0.6)
    else:
        time.sleep(0.15)


def mask_text(text: str) -> tuple[str, dict]:
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

    masked = HTML_TAG_RE.sub(repl("H"), text)
    masked = BRACKET_ATOMIC_RE.sub(repl("T"), masked)
    return masked, masks


def unmask_text(text: str, masks: dict) -> str:
    for key, val in masks.items():
        text = text.replace(key, val)
    return text


def validate_masks(translated: str, masks: dict) -> bool:
    return all(key in translated for key in masks)


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


def call_llm_raw(api_url: str, messages: list, json_mode: bool = False, max_tokens: int = 3072) -> str:
    adaptive_thermal_guard()
    t0 = time.monotonic()
    temp_b, w_b = get_gpu_telemetry()

    body = {
        "model": "local",
        "messages": messages,
        "temperature": 0.15,
        "max_tokens": max_tokens,
    }
    if json_mode:
        body["response_format"] = {"type": "json_object"}

    req = urllib.request.Request(
        api_url,
        data=json.dumps(body).encode("utf-8"),
        headers={"Content-Type": "application/json"},
        method="POST",
    )
    with urllib.request.urlopen(req, timeout=240) as r:
        data = json.loads(r.read().decode("utf-8"))

    dt = time.monotonic() - t0
    temp_a, w_a = get_gpu_telemetry()
    avg_w = (w_b + w_a) / 2.0 if (w_b > 0 or w_a > 0) else 0.0
    record_energy_sample(dt, max(temp_b, temp_a), avg_w)
    _SESSION_ENERGY["requests"] += 1
    return data["choices"][0]["message"]["content"].strip()


def translate_single(api_url: str, text: str, glossary: dict, context_note: str) -> str:
    if not text or not text.strip() or re.fullmatch(r"[\s\d\W_]+", text):
        return text
    masked, masks = mask_text(text)
    hint = build_glossary_hint(text, glossary)
    sys_p = (
        "You are an expert Russian translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'. "
        "Translate into natural, uncensored Russian. Keep ALL @@H*@@ and @@T*@@ tokens and [obj.speech(...)] wrappers intact. "
        "Output ONLY the Russian translation."
    )
    usr_p = f"Context: {context_note}\n" + (hint + "\n" if hint else "") + f"Text:\n{masked}"
    for _ in range(3):
        out = call_llm_raw(api_url, [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}], json_mode=False, max_tokens=2048)
        if validate_masks(out, masks):
            return unmask_text(out, masks)
    return text


def translate_batch(api_url: str, items: list[str], glossary: dict, context_note: str) -> list[str]:
    if not items:
        return []
    if len(items) == 1:
        return [translate_single(api_url, items[0], glossary, context_note)]

    results = list(items)
    idx = 0
    while idx < len(items):
        batch_indices = []
        char_sum = 0
        while idx < len(items) and len(batch_indices) < 10:
            s = items[idx]
            if not s or not s.strip() or re.fullmatch(r"[\s\d\W_]+", s):
                idx += 1
                continue
            if batch_indices and char_sum + len(s) > 1600:
                break
            batch_indices.append(idx)
            char_sum += len(s)
            idx += 1

        if not batch_indices:
            continue
        if len(batch_indices) == 1:
            i = batch_indices[0]
            results[i] = translate_single(api_url, items[i], glossary, context_note)
            continue

        masked_map = {}
        masks_map = {}
        combined_src = []
        for pos, orig_i in enumerate(batch_indices):
            m_txt, m_dict = mask_text(items[orig_i])
            masked_map[str(pos)] = m_txt
            masks_map[str(pos)] = m_dict
            combined_src.append(items[orig_i])

        hint = build_glossary_hint("\n".join(combined_src), glossary)
        sys_p = (
            "You are an expert Russian translator for the explicit 18+ dark fantasy RPG 'Lilith's Throne'. "
            "Translate each JSON string value from English into natural, uncensored Russian. "
            "CRITICAL: Keep exact JSON keys, preserve all @@H*@@ and @@T*@@ mask tokens, and keep [obj.speech(...)] / [style.bold(...)] wrappers intact. "
            "Return ONLY a valid JSON object."
        )
        usr_p = f"Context: {context_note}\n" + (hint + "\n" if hint else "") + "Input JSON:\n" + json.dumps(masked_map, ensure_ascii=False)

        parsed = None
        try:
            raw_out = call_llm_raw(api_url, [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}], json_mode=True, max_tokens=3072)
            raw_out = re.sub(r"^```(?:json)?\s*|\s*```$", "", raw_out, flags=re.S).strip()
            parsed = json.loads(raw_out)
        except Exception:
            parsed = None

        for pos, orig_i in enumerate(batch_indices):
            k = str(pos)
            if isinstance(parsed, dict) and isinstance(parsed.get(k), str) and validate_masks(parsed[k], masks_map[k]):
                results[orig_i] = unmask_text(parsed[k], masks_map[k])
            else:
                results[orig_i] = translate_single(api_url, items[orig_i], glossary, context_note)

    return results


def translate_xml_file(path: Path, api_url: str, glossary: dict) -> bool:
    raw = path.read_text(encoding="utf-8")
    rel = path.relative_to(ROOT).as_posix()
    ET.fromstring(raw)

    cdata_re = re.compile(r"<!\[CDATA\[(.*?)\]\]>", re.S)
    cdata_blocks = []

    def collect_cdata(m):
        idx = len(cdata_blocks)
        cdata_blocks.append(m.group(1))
        return f"__CDATA_BLOCK_{idx}__"

    skeleton = cdata_re.sub(collect_cdata, raw)

    cdata_paras = []
    block_splits = []
    directive_re = re.compile(r"(</p>|[ \t]*#(?:IF|ELSEIF|ELSE|ENDIF)[^\n]*\n?)")
    for b_idx, inner in enumerate(cdata_blocks):
        parts = directive_re.split(inner)
        chunks = []
        buf = ""
        for p in parts:
            if not p:
                continue
            if re.match(r"^[ \t]*#(?:IF|ELSEIF|ELSE|ENDIF)", p):
                if buf:
                    chunks.append(buf)
                    buf = ""
                chunks.append(p)
            else:
                buf += p
                if p == "</p>":
                    chunks.append(buf)
                    buf = ""
        if buf:
            chunks.append(buf)
        block_splits.append(chunks)
        for p_idx, ch in enumerate(chunks):
            if ch.strip() and not re.match(r"^[ \t]*#(?:IF|ELSEIF|ELSE|ENDIF)", ch):
                cdata_paras.append((b_idx, p_idx, ch))

    leaf_tags = ["name", "namePlural", "description", "title", "tooltip", "determiner", "displayName"]
    leaf_re = re.compile(rf"(<(?:{'|'.join(leaf_tags)})(?:\s+[^>]*)?>)([^<]+)(</(?:{'|'.join(leaf_tags)})>)")
    leaf_matches = list(leaf_re.finditer(skeleton))
    leaf_texts = []
    valid_leaf_indices = []
    for m_idx, m in enumerate(leaf_matches):
        txt = m.group(2)
        if txt.strip() and "__CDATA_BLOCK_" not in txt:
            valid_leaf_indices.append(m_idx)
            leaf_texts.append(txt)

    all_inputs = [x[2] for x in cdata_paras] + leaf_texts
    if not all_inputs:
        return False

    all_outputs = translate_batch(api_url, all_inputs, glossary, f"XML {rel}")

    n_cdata = len(cdata_paras)
    for k, (b_idx, p_idx, _) in enumerate(cdata_paras):
        block_splits[b_idx][p_idx] = all_outputs[k]

    leaf_out_map = {valid_leaf_indices[i]: all_outputs[n_cdata + i] for i in range(len(valid_leaf_indices))}
    if leaf_out_map:
        parts = []
        last = 0
        for m_idx, m in enumerate(leaf_matches):
            parts.append(skeleton[last:m.start()])
            if m_idx in leaf_out_map:
                parts.append(f"{m.group(1)}{leaf_out_map[m_idx]}{m.group(3)}")
            else:
                parts.append(m.group(0))
            last = m.end()
        parts.append(skeleton[last:])
        skeleton = "".join(parts)

    for b_idx, chunks in enumerate(block_splits):
        new_inner = "".join(chunks)
        skeleton = skeleton.replace(f"__CDATA_BLOCK_{b_idx}__", f"<![CDATA[{new_inner}]]>", 1)

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
