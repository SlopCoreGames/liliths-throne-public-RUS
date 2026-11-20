package com.lilithsthrone.game.occupantManagement.slaveEvent;

/**
 * @since 0.3.9.2
 * @version 0.3.9.2
 * @author Innoxia
 */
public enum SlaveEventType {
	
	BONDING("Сближение", "События, в которых две рабыни проводят время, либо узнавая друг друга получше, либо споря о чем-то."),
	
	JOB("Работа", "События, связанные с работой рабыни. Сюда входят сексуальные события, если работа рабыни подразумевает секс."),
	
	SEX("Секс", "События, в которых рабыня совершает что-то сексуальное или испытывает последствия секса (например, роды)."),
	
	MISCELLANEOUS("Разное", "Различные повседневные события, такие как то, как рабыни моются.");
	
	private String name;
	private String description;
	
	private SlaveEventType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
