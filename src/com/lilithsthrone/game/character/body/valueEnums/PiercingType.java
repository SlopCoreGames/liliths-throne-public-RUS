package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.3.7
 * @author Innoxia
 */
public enum PiercingType {
	
	EAR("ear",
			"Уши — самое популярное место для пирсинга, позволяющее носить серьги и другие украшения для ушей."),
	
	NOSE("нос",
			"Пирсинг носа позволяет надевать такие украшения, как кольца или гвоздики."),
	
	LIP("lip",
			"Пирсинг губ позволяет носить кольца в губах."),
	
	TONGUE("tongue",
			"Пирсинг языка позволит вам использовать языковые штанги."),
	
	NAVEL("пупок",
			"Пирсинг пупка позволяет надевать различные украшения для живота."),
	
	NIPPLE("nipple",
			"Пирсинг сосков позволит вам использовать сосковые штанги."),
	
	VAGINA("vagina",
			"Пирсинг влагалища позволит вам надевать украшения для вульвы."),
	
	PENIS("penis",
			"Пирсинг пениса позволит вам надевать украшения для члена.");
	
	private String name;
	private String description;

	private PiercingType(String name, String description) {
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
