package com.lilithsthrone.game.character.gender;

/**
 * @since 0.1.67
 * @version 0.1.86
 * @author Innoxia
 */
public enum GenderPronoun {

	NOUN("Существительное", "женщина", "мужчина", "человек"),
	YOUNG_NOUN("Молодое сущ.", "девушка", "парень", "человек"),
	
	SECOND_PERSON("Именительный", "она", "он", "они"),
	THIRD_PERSON("Винительный", "её", "его", "их"),
	POSSESSIVE_BEFORE_NOUN("Притяжательное (перед сущ.)", "её", "его", "их"),
	POSSESSIVE_ALONE("Притяжательное (самост.)", "её", "его", "их");
	
	private String name, feminine, masculine, neutral;
	
	private GenderPronoun(String name, String feminine, String masculine, String neutral){
		this.name = name;
		this.feminine = feminine;
		this.masculine = masculine;
		this.neutral = neutral;
	}

	public String getName() {
		return name;
	}
	
	public String getFeminine() {
		return feminine;
	}

	public String getMasculine() {
		return masculine;
	}

	public String getNeutral() {
		return neutral;
	}
}
