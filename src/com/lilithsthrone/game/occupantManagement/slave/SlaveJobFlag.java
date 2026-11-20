package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.6
 * @version 0.4.10.7
 * @author Innoxia
 */
public enum SlaveJobFlag {

	EXPERIENCE_GAINS(PresetColour.GENERIC_EXPERIENCE, "Прирост опыта", "У рабов есть 25% c шанс получать 5 единиц опыта каждый час."),
	
	INTERACTION_SEX(PresetColour.GENERIC_SEX, "Секс рабов", "Рабы, назначенные на эту работу, могут заниматься сексом с другими рабами."),
	
	INTERACTION_BONDING(PresetColour.AFFECTION, "Связь рабов", "Рабы, назначенные на эту работу, могут общаться с другими рабами."),
	
	GUEST_CAN_WORK(PresetColour.GENERIC_NEUTRAL, "Гость-работник", "Гостей можно назначать на эту работу."),

	CLEANING_UNAVAILABLE(PresetColour.DIRTY, "Уборка недоступна", "Во время выполнения этой работы рабы не смогут убирать себя сами."),

	SPECIAL_UNIFORM(PresetColour.GENERIC_MINOR_BAD, "Особая униформа", "Во время выполнения этой работы рабов нельзя переодевать через гардеробную.");
	
	private Colour colour;
	private String name;
	private String description;
	
	private SlaveJobFlag(Colour colour, String name, String description) {
		this.colour = colour;
		this.name = name;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
