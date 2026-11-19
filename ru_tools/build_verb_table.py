"""
Generate G:/Projects/Liliths_Throne_RU/src/com/lilithsthrone/utils/RuVerbTable.java
from ru_tools/unique_verbs.json using local llama-server (Gemma 4 26B).
Each English verb maps to [2nd_person_sg_ty, 3rd_person_sg_on_ona] in present tense.
"""
import argparse
import json
import re
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import translate_lt as base

ROOT = base.ROOT
VERBS_JSON = Path(__file__).resolve().parent / "unique_verbs.json"
CACHE_JSON = Path(__file__).resolve().parent / "verbs_ru_cache.json"
OUT_JAVA = ROOT / "src" / "com" / "lilithsthrone" / "utils" / "RuVerbTable.java"

CYR_RE = re.compile(r"^[А-Яа-яЁё][А-Яа-яЁё\s\-]*$")

# High-frequency / tricky RPG verb overrides to guarantee clean literary Russian forms
OVERRIDES = {
    "let": ["позволяешь", "позволяет"],
    "start": ["начинаешь", "начинает"],
    "pull": ["тянешь", "тянет"],
    "try": ["пытаешься", "пытается"],
    "feel": ["чувствуешь", "чувствует"],
    "push": ["толкаешь", "толкает"],
    "slide": ["проводишь", "проводит"],
    "continue": ["продолжаешь", "продолжает"],
    "force": ["вталкиваешь", "вталкивает"],
    "take": ["берёшь", "берёт"],
    "thrust": ["вбиваешь", "вбивает"],
    "fuck": ["трахаешь", "трахает"],
    "grab": ["хватаешь", "хватает"],
    "press": ["прижимаешь", "прижимает"],
    "guide": ["направляешь", "направляет"],
    "reach": ["тянешься", "тянется"],
    "realise": ["понимаешь", "понимает"],
    "realize": ["понимаешь", "понимает"],
    "beg": ["умоляешь", "умоляет"],
    "help": ["помогаешь", "помогает"],
    "grind": ["трёшься", "трётся"],
    "yank": ["выдёргиваешь", "выдёргивает"],
    "buck": ["выгибаешься", "выгибается"],
    "lean": ["наклоняешься", "наклоняется"],
    "slam": ["вколачиваешь", "вколачивает"],
    "hold": ["держишь", "держит"],
    "tease": ["дразнишь", "дразнит"],
    "penetrate": ["проникаешь", "проникает"],
    "unfasten": ["расстёгиваешь", "расстёгивает"],
    "prepare": ["готовишься", "готовится"],
    "undo": ["расстёгиваешь", "расстёгивает"],
    "wrap": ["обвиваешь", "обвивает"],
    "give": ["даёшь", "даёт"],
    "make": ["делаешь", "делает"],
    "keep": ["продолжаешь", "продолжает"],
    "moan": ["стонешь", "стонет"],
    "groan": ["стонешь", "стонет"],
    "gasp": ["вздыхаешь", "вздыхает"],
    "sigh": ["вздыхаешь", "вздыхает"],
    "smile": ["улыбаешься", "улыбается"],
    "grin": ["ухмыляешься", "ухмыляется"],
    "laugh": ["смеёшься", "смеётся"],
    "nod": ["киваешь", "кивает"],
    "look": ["смотришь", "смотрит"],
    "turn": ["поворачиваешься", "поворачивается"],
    "walk": ["идёшь", "идёт"],
    "step": ["шагаешь", "шагает"],
    "move": ["двигаешься", "двигается"],
    "drop": ["роняешь", "роняет"],
    "wear": ["носишь", "носит"],
    "has": ["имеешь", "имеет"],
    "have": ["имеешь", "имеет"],
    "is": ["являешься", "является"],
    "are": ["являешься", "является"],
    "do": ["делаешь", "делает"],
    "does": ["делает", "делает"],
}


def translate_verb_batch(api_url: str, verbs: list[str]) -> dict[str, list[str]]:
    sys_p = (
        "You conjugate English or Russian RPG action verbs for 'Lilith's Throne' into Russian present tense (uncensored 18+ literary Russian). "
        "For EACH input verb key, return a JSON array of 2 lowercase Cyrillic strings: "
        "[2nd_person_singular_ты, 3rd_person_singular_он_она]. "
        "If the input key is already a Russian verb form or short verb phrase (e.g. 'подносит', 'начинаешь', 'расстегнуть', 'хвастливо показать'), normalize it to its exact [ты_form, он_она_form] pair in present tense. "
        'Examples: {"pull": ["тянешь", "тянет"], "подносит": ["подносишь", "подносит"], "начинаешь": ["начинаешь", "начинает"], "хвастливо показать": ["хвастливо показываешь", "хвастливо показывает"]}. '
        "Return ONLY valid JSON mapping every input key to a 2-string array in Cyrillic."
    )
    usr_p = "Verbs:\n" + json.dumps(verbs, ensure_ascii=False)
    for attempt in range(3):
        try:
            raw = base.call_llm_raw(
                api_url,
                [{"role": "system", "content": sys_p}, {"role": "user", "content": usr_p}],
                json_mode=True,
                max_tokens=2048,
                temperature=0.15 + 0.1 * attempt,
            )
            raw = re.sub(r"^```(?:json)?\s*|\s*```$", "", raw, flags=re.S).strip()
            parsed = json.loads(raw)
            out = {}
            for v in verbs:
                val = parsed.get(v)
                if (
                    isinstance(val, list)
                    and len(val) == 2
                    and isinstance(val[0], str)
                    and isinstance(val[1], str)
                    and CYR_RE.match(val[0].strip())
                    and CYR_RE.match(val[1].strip())
                ):
                    out[v] = [val[0].strip().lower(), val[1].strip().lower()]
            if len(out) == len(verbs):
                return out
            # Partial salvage
            for k, val in parsed.items():
                if (
                    k in verbs
                    and k not in out
                    and isinstance(val, list)
                    and len(val) == 2
                    and all(isinstance(x, str) and CYR_RE.match(x.strip()) for x in val)
                ):
                    out[k] = [val[0].strip().lower(), val[1].strip().lower()]
            if out and len(verbs) > 5:
                # fill missing recursively
                missing = [v for v in verbs if v not in out]
                if missing:
                    for m in missing:
                        out.update(translate_verb_batch(api_url, [m]))
                return out
        except Exception as e:
            print(f"  retry verb batch ({attempt}): {e}", file=sys.stderr, flush=True)
    # Fallback individual
    if len(verbs) > 1:
        out = {}
        for v in verbs:
            out.update(translate_verb_batch(api_url, [v]))
        return out
    return {}


def emit_java(mapping: dict[str, list[str]]):
    lines = [
        "package com.lilithsthrone.utils;",
        "",
        "import java.util.HashMap;",
        "import java.util.Locale;",
        "import java.util.Map;",
        "",
        "/**",
        " * Russian verb conjugation table for UtilText [npc.verb(...)] tags.",
        " * Maps English base verbs to [2nd_person_sg (ты), 3rd_person_sg (он/она)].",
        " */",
        "public final class RuVerbTable {",
        "",
        f"    private static final Map<String, String[]> VERBS = new HashMap<>({len(mapping) + 64});",
        "",
        "    static {",
    ]
    for k in sorted(mapping.keys()):
        v2, v3 = mapping[k]
        ke = k.replace("\\", "\\\\").replace('"', '\\"')
        e2 = v2.replace("\\", "\\\\").replace('"', '\\"')
        e3 = v3.replace("\\", "\\\\").replace('"', '\\"')
        lines.append(f'        VERBS.put("{ke}", new String[]{{"{e2}", "{e3}"}});')
    lines.extend([
        "    }",
        "",
        "    private RuVerbTable() {",
        "    }",
        "",
        "    public static String conjugate(String rawVerb, boolean isPlayer) {",
        "        if (rawVerb == null || rawVerb.isEmpty()) {",
        "            return rawVerb;",
        "        }",
        "        String trimmed = rawVerb.trim();",
        "        if (trimmed.isEmpty()) {",
        "            return rawVerb;",
        "        }",
        "        boolean cap = Character.isUpperCase(trimmed.charAt(0));",
        "        String key = trimmed.toLowerCase(Locale.ROOT);",
        "        String[] pair = VERBS.get(key);",
        "        if (pair != null) {",
        "            String res = isPlayer ? pair[0] : pair[1];",
        "            return cap ? Util.capitaliseSentence(res) : res;",
        "        }",
        "        // Handle simple Russian infinitives passed directly inside [npc.verb(...)]",
        "        if (key.endsWith(\"ть\") && key.length() > 3) {",
        "            String stem = key.substring(0, key.length() - 2);",
        "            String res = stem + (isPlayer ? \"ешь\" : \"ет\");",
        "            return cap ? Util.capitaliseSentence(res) : res;",
        "        }",
        "        // Return cleaned trimmed verb rather than falling back to English suffixes like '+s'",
        "        return trimmed;",
        "    }",
        "}",
    ])
    OUT_JAVA.write_text("\n".join(lines) + "\n", encoding="utf-8")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--api", default=base.DEFAULT_API)
    args = ap.parse_args()

    raw_list = json.loads(VERBS_JSON.read_text(encoding="utf-8"))
    all_keys = sorted({item["en"].strip().lower() for item in raw_list if item["en"].strip()} | set(OVERRIDES.keys()))

    cache = json.loads(CACHE_JSON.read_text(encoding="utf-8")) if CACHE_JSON.exists() else {}
    for k, v in OVERRIDES.items():
        cache[k] = v

    todo = [k for k in all_keys if k not in cache or not all(CYR_RE.match(x) for x in cache[k])]
    print(f"[VERBS] total={len(all_keys)} cached={len(all_keys)-len(todo)} todo={len(todo)}", flush=True)

    for i in range(0, len(todo), 30):
        chunk = todo[i : i + 30]
        res = translate_verb_batch(args.api, chunk)
        cache.update(res)
        CACHE_JSON.write_text(json.dumps(cache, ensure_ascii=False, indent=2), encoding="utf-8")
        print(f"  translated {min(i+len(chunk), len(todo))}/{len(todo)}", flush=True)

    missing = [k for k in all_keys if k not in cache]
    if missing:
        raise RuntimeError(f"Missing verbs after translation: {missing}")

    emit_java({k: cache[k] for k in all_keys})
    base.flush_energy_stats()
    print(f"[VERBS] Wrote {OUT_JAVA} ({len(all_keys)} verbs)", flush=True)


if __name__ == "__main__":
    main()
