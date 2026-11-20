package com.lilithsthrone.game.character.persona;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Irbynx
 */
public enum MoralityValue {
	
	SURRENDER("Уступчивость", "Склонность избегать сражений до самого конца."),
	
	VIOLENCE("Жестокость", "Склонность к избыточному насилию."),
	
	DISHONESTY("Бесчестие", "Склонность к лжи, полуправде и намеренному сокрытию информации."),
	
	SLAVERY("Рабство", "Отношение к владению рабами.");
	
	private String name;
	private String description;
	
	private MoralityValue(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
