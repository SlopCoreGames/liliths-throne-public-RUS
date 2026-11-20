"""
Continuous GPU Java translation worker for Lilith's Throne (Phase 3).
Builds a priority-ordered queue of all remaining untranslated Java files,
translates them with translate_java_v2, runs incremental `mvn compile -DskipTests`
every N files (auto-reverting any breaking file into failed_java.json),
and updates progress.json + energy_stats.json atomically.
"""
import argparse
import json
import os
import re
import subprocess
import sys
import time
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import translate_lt as base
import translate_java_v2 as jv2

ROOT = base.ROOT
QUEUE_PATH = Path(__file__).resolve().parent / "queue_java.json"
FAILED_PATH = Path(__file__).resolve().parent / "failed_java.json"
JAVA_HOME = r"G:\Projects\tools\jdk-11.0.33+10"
MVN_CMD = r"G:\Projects\tools\apache-maven-3.10.1\bin\mvn.cmd"

SKIP_EXACT = {
    "src/com/lilithsthrone/Launcher.java",
    "src/com/lilithsthrone/game/dialogue/utils/UtilText.java",
    "src/com/lilithsthrone/utils/Util.java",
    "src/com/lilithsthrone/utils/Units.java",
    "src/com/lilithsthrone/utils/RuMorphology.java",
    "src/com/lilithsthrone/utils/RuVerbTable.java",
    "src/com/lilithsthrone/utils/XMLSaving.java",
    "src/com/lilithsthrone/utils/Vector2i.java",
    "src/com/lilithsthrone/utils/SizedStack.java",
    "src/com/lilithsthrone/rendering/SVGImages.java",
    "src/com/lilithsthrone/rendering/CachedImage.java",
    "src/com/lilithsthrone/rendering/Artwork.java",
    "src/com/lilithsthrone/game/settings/KeyCodeWithModifiers.java",
}

SKIP_PREFIXES = (
    "src/com/lilithsthrone/controller/xmlParsing/",
    "src/com/lilithsthrone/utils/comparators/",
)

PRIORITY_DIRS = [
    "src/com/lilithsthrone/game/character/body",
    "src/com/lilithsthrone/game/character/race",
    "src/com/lilithsthrone/game/character/fetishes",
    "src/com/lilithsthrone/game/character/persona",
    "src/com/lilithsthrone/game/character/effects",
    "src/com/lilithsthrone/game/character/attributes",
    "src/com/lilithsthrone/game/character/npc",
    "src/com/lilithsthrone/game/character",
    "src/com/lilithsthrone/game/inventory",
    "src/com/lilithsthrone/world",
    "src/com/lilithsthrone/game/combat",
    "src/com/lilithsthrone/game/occupantManagement",
    "src/com/lilithsthrone/game/settings",
    "src/com/lilithsthrone/controller",
    "src/com/lilithsthrone/main",
    "src/com/lilithsthrone/game",
]


def _atomic_write(path: Path, data):
    tmp = path.with_suffix(".tmp")
    tmp.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")
    os.replace(tmp, path)


def _record_failure(rel: str, err: str):
    cur = []
    if FAILED_PATH.exists():
        try:
            cur = json.loads(FAILED_PATH.read_text(encoding="utf-8"))
        except Exception:
            cur = []
    if not any(x.get("path") == rel for x in cur):
        cur.append({"path": rel, "error": err})
        _atomic_write(FAILED_PATH, cur)


def build_queue(prog: dict, include_existing_cyr: bool = False) -> list[str]:
    done = set(prog.get("translated_java_v2", []))
    seen = set()
    q = []

    def maybe_add(p: Path):
        rel = p.relative_to(ROOT).as_posix()
        if rel in seen or rel in done or rel in SKIP_EXACT or rel.startswith(SKIP_PREFIXES):
            return
        seen.add(rel)
        src = p.read_text(encoding="utf-8", errors="ignore")
        if not include_existing_cyr and rel in prog.get("translated_java", []):
            # Still include if collect_string_units finds leftover English strings
            pass
        units = jv2.collect_string_units(src)
        if units:
            q.append(rel)

    for d in PRIORITY_DIRS:
        base_d = ROOT / d
        if not base_d.exists():
            continue
        for p in sorted(base_d.rglob("*.java")):
            maybe_add(p)

    for p in sorted((ROOT / "src").rglob("*.java")):
        maybe_add(p)

    return q


def run_mvn_compile() -> tuple[bool, str]:
    env = os.environ.copy()
    env["JAVA_HOME"] = JAVA_HOME
    proc = subprocess.run(
        ["cmd", "/c", MVN_CMD, "compiler:compile", "-DskipTests"],
        cwd=str(ROOT),
        env=env,
        capture_output=True,
        text=True,
        encoding="utf-8",
        errors="ignore",
    )
    return proc.returncode == 0, proc.stdout + "\n" + proc.stderr


def revert_offending_files(mvn_out: str, recent_batch: list[str]):
    reverted = []
    for rel in recent_batch:
        win_rel = rel.replace("/", "\\")
        if win_rel in mvn_out or rel in mvn_out or Path(rel).name in mvn_out:
            subprocess.run(["git", "-C", str(ROOT), "checkout", "--", rel], check=False)
            _record_failure(rel, "mvn compile failure (reverted)")
            reverted.append(rel)
    if not reverted:
        # Fallback: revert entire batch cleanly so build never stays broken
        for rel in recent_batch:
            subprocess.run(["git", "-C", str(ROOT), "checkout", "--", rel], check=False)
            _record_failure(rel, "mvn compile failure (batch reverted)")
            reverted.append(rel)
    print(f"  [MVN-GATE] Reverted offending files: {reverted}", file=sys.stderr, flush=True)


def git_checkpoint_push(done_cnt: int, total_cnt: int):
    try:
        subprocess.run(["git", "-C", str(ROOT), "add", "src/", "ru_tools/"], check=False)
        res = subprocess.run(
            ["git", "-C", str(ROOT), "commit", "-m", f"Phase 3 progress: {done_cnt}/{total_cnt} Java files"],
            capture_output=True,
            text=True,
            encoding="utf-8",
            errors="ignore",
        )
        if res.returncode == 0:
            push = subprocess.run(
                ["git", "-C", str(ROOT), "push", "origin", "dev"],
                capture_output=True,
                text=True,
                encoding="utf-8",
                errors="ignore",
            )
            print(f"[GIT-PUSH] rc={push.returncode}: {(push.stdout + push.stderr).strip()[:200]}", flush=True)
        else:
            print("[GIT-PUSH] nothing new to commit.", flush=True)
    except Exception as e:
        print(f"[GIT-PUSH] skipped due to error: {e}", file=sys.stderr, flush=True)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=base.DEFAULT_API)
    ap.add_argument("--rebuild-queue", action="store_true")
    ap.add_argument("--compile-every", type=int, default=15)
    ap.add_argument("--push-every", type=int, default=45)
    args = ap.parse_args()

    glossary = base.load_glossary()
    prog = base.load_progress()
    prog.setdefault("translated_java_v2", [])

    if args.rebuild_queue or not QUEUE_PATH.exists():
        queue = build_queue(prog)
        _atomic_write(QUEUE_PATH, queue)
        print(f"[JAVA-QUEUE] Built queue with {len(queue)} files needing translation.", flush=True)
    else:
        queue = json.loads(QUEUE_PATH.read_text(encoding="utf-8"))

    total_target = len(prog.get("translated_java_v2", [])) + len(queue)
    recent_batch: list[str] = []
    since_push = 0
    energy_counter = 0

    while queue:
        rel = queue[0]
        p = ROOT / rel
        if not p.exists():
            queue.pop(0)
            _atomic_write(QUEUE_PATH, queue)
            continue

        t0 = time.monotonic()
        print(f"[JAVA-WORKER] ({len(queue)} left) {rel}", flush=True)
        try:
            changed, cnt = jv2.translate_java_file(p, args.api, glossary)
            if changed:
                recent_batch.append(rel)
            prog = base.load_progress()
            prog.setdefault("translated_java_v2", [])
            if rel not in prog["translated_java_v2"]:
                prog["translated_java_v2"].append(rel)
            base.save_progress(prog)
            print(f"  OK ({time.monotonic()-t0:.1f}s, units={cnt}, changed={changed}): {rel}", flush=True)
        except Exception as e:
            print(f"  ERR {rel}: {e}", file=sys.stderr, flush=True)
            _record_failure(rel, str(e))

        queue.pop(0)
        _atomic_write(QUEUE_PATH, queue)

        energy_counter += 1
        if energy_counter >= 3:
            base.flush_energy_stats()
            base._SESSION_ENERGY.update(
                {"gpu_wh": 0.0, "sys_wh": 0.0, "seconds": 0.0, "requests": 0, "peak_temp": 0.0, "peak_w": 0.0}
            )
            energy_counter = 0

        if len(recent_batch) >= args.compile_every:
            print(f"[MVN-GATE] Checking compile after {len(recent_batch)} modified files...", flush=True)
            ok, out = run_mvn_compile()
            if ok:
                print("[MVN-GATE] BUILD SUCCESS", flush=True)
                since_push += len(recent_batch)
                recent_batch.clear()
                if args.push_every > 0 and since_push >= args.push_every:
                    git_checkpoint_push(len(prog.get("translated_java_v2", [])), total_target)
                    since_push = 0
            else:
                revert_offending_files(out, recent_batch)
                recent_batch.clear()
                ok2, _ = run_mvn_compile()
                print(f"[MVN-GATE] Post-revert compile ok={ok2}", flush=True)

    if recent_batch:
        print(f"[MVN-GATE] Final compile check ({len(recent_batch)} files)...", flush=True)
        ok, out = run_mvn_compile()
        if not ok:
            revert_offending_files(out, recent_batch)
            ok2, _ = run_mvn_compile()
            print(f"[MVN-GATE] Final post-revert compile ok={ok2}", flush=True)
        else:
            print("[MVN-GATE] FINAL BUILD SUCCESS", flush=True)

    ok_final, _ = run_mvn_compile()
    if ok_final:
        git_checkpoint_push(len(prog.get("translated_java_v2", [])), total_target)

    base.flush_energy_stats()
    print("[JAVA-WORKER] Queue complete!", flush=True)


if __name__ == "__main__":
    main()
