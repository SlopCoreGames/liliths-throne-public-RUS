"""
Continuous GPU translation queue worker for Lilith's Throne.
Builds a priority-ordered queue (early game -> quests -> characters -> places -> items/race)
and keeps the RTX 3090 busy continuously with thermal guard & energy metering.
"""
import argparse
import json
import sys
import time
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import translate_lt as xml_tr
import translate_java_lt as java_tr

ROOT = xml_tr.ROOT
QUEUE_PATH = Path(__file__).resolve().parent / "queue.json"
LOG_PATH = Path(__file__).resolve().parent / "worker.log"

PRIORITY_XML_DIRS = [
    "res/txt/quests",
    "res/txt/characters",
    "res/txt/places",
    "res/txt/encounters",
    "res/txt/badEnds",
    "res/dialogue",
    "res/statusEffects",
    "res/combatMove",
    "res/items",
    "res/weapons",
    "res/clothing",
    "res/outfits",
    "res/setBonuses",
    "res/tattoos",
    "res/race",
    "res/txt/sex",
    "res/sex",
]

PRIORITY_JAVA_FILES = [
    "src/com/lilithsthrone/game/character/quests/Quest.java",
    "src/com/lilithsthrone/game/character/quests/QuestLine.java",
    "src/com/lilithsthrone/game/character/effects/Perk.java",
    "src/com/lilithsthrone/game/character/effects/StatusEffect.java",
    "src/com/lilithsthrone/game/combat/spells/Spell.java",
    "src/com/lilithsthrone/game/combat/DamageType.java",
    "src/com/lilithsthrone/controller/MainController.java",
    "src/com/lilithsthrone/controller/OptionsController.java",
    "src/com/lilithsthrone/controller/CreationController.java",
    "src/com/lilithsthrone/controller/InventoryController.java",
]


def build_queue(prog: dict) -> list[dict]:
    q = []
    done_xml = set(prog.get("translated_xml", []))
    done_java = set(prog.get("translated_java", []))

    for rel in PRIORITY_JAVA_FILES:
        if rel not in done_java and (ROOT / rel).exists():
            q.append({"type": "java", "path": rel})

    seen_xml = set(done_xml)
    for d in PRIORITY_XML_DIRS:
        base = ROOT / d
        if not base.exists():
            continue
        for p in sorted(base.rglob("*.xml")):
            rel = p.relative_to(ROOT).as_posix()
            if rel not in seen_xml:
                seen_xml.add(rel)
                q.append({"type": "xml", "path": rel})

    # Remaining XMLs under res/ (excluding res/maps/ coordinate-only tiles unless they have htmlContent)
    res_dir = ROOT / "res"
    for p in sorted(res_dir.rglob("*.xml")):
        rel = p.relative_to(ROOT).as_posix()
        if rel in seen_xml:
            continue
        if rel.startswith("res/maps/"):
            continue
        seen_xml.add(rel)
        q.append({"type": "xml", "path": rel})

    return q


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=xml_tr.DEFAULT_API)
    ap.add_argument("--rebuild-queue", action="store_true")
    args = ap.parse_args()

    glossary = xml_tr.load_glossary()
    prog = xml_tr.load_progress()

    if args.rebuild_queue or not QUEUE_PATH.exists():
        queue = build_queue(prog)
        QUEUE_PATH.write_text(json.dumps(queue, ensure_ascii=False, indent=2), encoding="utf-8")
        print(f"[QUEUE] Built queue with {len(queue)} items.", flush=True)
    else:
        queue = json.loads(QUEUE_PATH.read_text(encoding="utf-8"))

    processed_since_flush = 0
    while queue:
        item = queue[0]
        kind = item["type"]
        rel = item["path"]
        p = ROOT / rel

        prog = xml_tr.load_progress()
        if (kind == "xml" and rel in prog["translated_xml"]) or (kind == "java" and rel in prog["translated_java"]):
            queue.pop(0)
            QUEUE_PATH.write_text(json.dumps(queue, ensure_ascii=False, indent=2), encoding="utf-8")
            continue

        t0 = time.monotonic()
        print(f"[WORKER] ({len(queue)} left) {kind.upper()}: {rel}", flush=True)
        try:
            if kind == "xml":
                changed = xml_tr.translate_xml_file(p, args.api, glossary)
                prog["translated_xml"].append(rel)
            else:
                changed = java_tr.translate_java_file(p, args.api, glossary)
                prog["translated_java"].append(rel)
            xml_tr.save_progress(prog)
            print(f"  OK ({time.monotonic()-t0:.1f}s, changed={changed}): {rel}", flush=True)
        except Exception as e:
            print(f"  ERR {rel}: {e}", file=sys.stderr, flush=True)

        queue.pop(0)
        QUEUE_PATH.write_text(json.dumps(queue, ensure_ascii=False, indent=2), encoding="utf-8")
        processed_since_flush += 1
        if processed_since_flush >= 3:
            xml_tr.flush_energy_stats()
            xml_tr._SESSION_ENERGY.update({"gpu_wh": 0.0, "sys_wh": 0.0, "seconds": 0.0, "requests": 0, "peak_temp": 0.0, "peak_w": 0.0})
            processed_since_flush = 0

    xml_tr.flush_energy_stats()
    print("[WORKER] Queue complete!", flush=True)


if __name__ == "__main__":
    main()
