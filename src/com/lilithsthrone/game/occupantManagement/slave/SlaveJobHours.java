package com.lilithsthrone.game.occupantManagement.slave;

/**
 * @since 0.1.87
 * @version 0.1.87
 * @author Innoxia
 */
public enum SlaveJobHours {

	NONE("Нет", "Не назначать этому персонажу рабочих часов.", 0, 0),
	
	DAY_NORMAL("Дневная смена", "Этот персонаж должен отработать восемь часов в течение дня.", 9, 8),
	DAY_LONG("Дневная смена +", "Этот персонаж должен отработать шестнадцать часов в течение дня.", 6, 16),
	
	NIGHT_NORMAL("Ночная смена", "Этот персонаж должен отработать восемь часов в течение ночи.", 20, 8),
	NIGHT_LONG("Ночная смена +", "Этот персонаж должен отработать шестнадцать часов в течение ночи.", 16, 16),

	TWENTY_FOUR_HOURS("24 часа", "Назначать каждый час как рабочий.", 0, 24);
	

	private String name;
	private String description;
	private int startHour;
	private int length;
	
	private SlaveJobHours(String name, String description, int startHour, int length) {
		this.name = name;
		this.description = description;
		this.startHour = startHour;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getStartHour() {
		return startHour;
	}

	public int getLength() {
		return length;
	}
}
