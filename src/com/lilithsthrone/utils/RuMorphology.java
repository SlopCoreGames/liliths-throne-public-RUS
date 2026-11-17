package com.lilithsthrone.utils;

/**
 * Lightweight Russian case-inflection helper for dynamic item/clothing/character names
 * (fixes issues like "Ты надел юбка" -> "Ты надел юбку").
 */
public final class RuMorphology {

	private RuMorphology() {
	}

	/**
	 * Converts a Russian nominative noun phrase (e.g. "чёрная юбка", "кожаный ошейник")
	 * to accusative inanimate form ("чёрную юбку", "кожаный ошейник").
	 */
	public static String toAccusative(String phrase) {
		if (phrase == null || phrase.isEmpty()) {
			return phrase;
		}
		String[] words = phrase.split("\\s+");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (i > 0) {
				sb.append(' ');
			}
			sb.append(wordToAccusative(words[i]));
		}
		return sb.toString();
	}

	private static String wordToAccusative(String w) {
		if (w.length() < 3) {
			return w;
		}
		// Feminine adjectives: -ая -> -ую, -яя -> -юю
		if (w.endsWith("ая")) {
			return w.substring(0, w.length() - 2) + "ую";
		}
		if (w.endsWith("яя")) {
			return w.substring(0, w.length() - 2) + "юю";
		}
		// Feminine nouns: -а -> -у, -я -> -ю (skip indeclinable/foreign if needed)
		if (w.endsWith("а")) {
			return w.substring(0, w.length() - 1) + "у";
		}
		if (w.endsWith("я")) {
			return w.substring(0, w.length() - 1) + "ю";
		}
		return w;
	}
}
