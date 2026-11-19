"""
Pass over all XML files under res/ to translate any remaining English fragments,
inline #IF(...) tails, non-CDATA leaf tags, <combatant>, <tab>, and .speech(...) wrappers.
"""
import argparse
import json
import sys
import time
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import translate_lt as base

ROOT = base.ROOT
SKIP_PREFIXES = ("res/images/", "res/characters/")


def collect_candidate_xmls() -> list[Path]:
    out = []
    for p in sorted((ROOT / "res").rglob("*.xml")):
        rel = p.relative_to(ROOT).as_posix()
        if rel.startswith(SKIP_PREFIXES):
            continue
        out.append(p)
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=base.DEFAULT_API)
    ap.add_argument("--files", nargs="*", help="Optional explicit relative file paths")
    args = ap.parse_args()

    glossary = base.load_glossary()
    targets = [ROOT / f for f in args.files] if args.files else collect_candidate_xmls()
    print(f"[XML-TAILS] Scanning {len(targets)} XML files...", flush=True)

    changed_cnt = 0
    err_cnt = 0
    failures = []
    for i, p in enumerate(targets, 1):
        rel = p.relative_to(ROOT).as_posix()
        try:
            t0 = time.monotonic()
            ch = base.translate_xml_file(p, args.api, glossary)
            if ch:
                changed_cnt += 1
                print(f"  [{i}/{len(targets)}] UPDATED ({time.monotonic()-t0:.1f}s): {rel}", flush=True)
                if changed_cnt % 10 == 0:
                    base.flush_energy_stats()
                    base._SESSION_ENERGY.update(
                        {"gpu_wh": 0.0, "sys_wh": 0.0, "seconds": 0.0, "requests": 0, "peak_temp": 0.0, "peak_w": 0.0}
                    )
        except Exception as e:
            err_cnt += 1
            failures.append({"path": rel, "error": str(e)})
            print(f"  [{i}/{len(targets)}] ERR {rel}: {e}", file=sys.stderr, flush=True)

    base.flush_energy_stats()
    if failures:
        fail_path = Path(__file__).resolve().parent / "failed_xml_tails.json"
        fail_path.write_text(json.dumps(failures, ensure_ascii=False, indent=2), encoding="utf-8")
    print(f"[XML-TAILS] Done! scanned={len(targets)} updated={changed_cnt} errors={err_cnt}", flush=True)


if __name__ == "__main__":
    main()
