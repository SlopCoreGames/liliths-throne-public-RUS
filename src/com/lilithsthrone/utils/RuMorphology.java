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

	/**
	 * Converts a Russian character name or noun phrase to genitive case (for [npc.namePos]).
	 * Avoids mangling indeclinable names ending in vowels (-и, -о, -у, -э, -е, -ю) or short abbreviations.
	 */
	public static String toGenitive(String phrase) {
		if (phrase == null || phrase.isEmpty()) {
			return phrase;
		}
		String trimmed = phrase.trim();
		if (trimmed.isEmpty()) {
			return phrase;
		}
		// Multi-word titles/names: inflect last token or adjective+noun pair safely
		String[] words = trimmed.split("\\s+");
		if (words.length == 1) {
			return wordToGenitive(words[0]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (i > 0) {
				sb.append(' ');
			}
			// Inflect lowercase adjectives/nouns in phrase, and last word
			String w = words[i];
			if (i == words.length - 1 || Character.isLowerCase(w.charAt(0))) {
				sb.append(wordToGenitive(w));
			} else {
				sb.append(w);
			}
		}
		return sb.toString();
	}

	private static String wordToGenitive(String w) {
		if (w.length() < 2) {
			return w;
		}
		// Preserve HTML tags inside name strings if any
		if (w.contains("<") || w.contains(">")) {
			return w;
		}
		char last = w.charAt(w.length() - 1);
		// Indeclinable endings: и, о, у, э, е, ю, ы
		if ("иоуэеюыИОУЭЕЮЫ".indexOf(last) != -1) {
			return w;
		}
		String low = w.toLowerCase();
		// Adjectives -ая / -яя / -ый / -ий / -ой
		if (low.endsWith("ая")) {
			return w.substring(0, w.length() - 2) + "ой";
		}
		if (low.endsWith("яя")) {
			return w.substring(0, w.length() - 2) + "ей";
		}
		if (low.endsWith("ый") || low.endsWith("ой")) {
			return w.substring(0, w.length() - 2) + "ого";
		}
		if (low.endsWith("ий") && w.length() >= 4) {
			return w.substring(0, w.length() - 2) + "его";
		}
		// Feminine/common -а -> -и (after г,к,х,ж,ч,ш,щ) or -ы
		if (low.endsWith("а")) {
			char prev = low.charAt(low.length() - 2);
			String end = ("гкхжчшщ".indexOf(prev) != -1) ? "и" : "ы";
			return w.substring(0, w.length() - 1) + end;
		}
		if (low.endsWith("я")) {
			return w.substring(0, w.length() - 1) + "и";
		}
		// Masculine -й / -ь -> -я / -и? Most character names/nouns ending in -ь or -й are masc (-я), leave if unclear
		if (low.endsWith("й") || low.endsWith("ь")) {
			return w.substring(0, w.length() - 1) + "я";
		}
		// Russian consonants
		if ("бвгджзклмнпрстфхцчшщ".indexOf(Character.toLowerCase(last)) != -1) {
			return w + "а";
		}
		return w;
	}
}
