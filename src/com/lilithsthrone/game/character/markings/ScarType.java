package com.lilithsthrone.game.character.markings;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum ScarType {

	CLAW_MARKS("след от когтя", "следы от когтей", true),
	
	BURNS("ожог", "ожоги", false),
	
	STRAIGHT_SCAR("прямой шрам", "прямые шрамы", false),
	
	JAGGED_SCAR("зубчатый шрам", "зубчатые шрамы", false),

	BRUISE("синяк", "bruises", false);
	
	private String name;
	private String namePlural;
	private boolean alwaysPlural;
	
	private ScarType(String name, String namePlural, boolean alwaysPlural) {
		this.name = name;
		this.namePlural = namePlural;
		this.alwaysPlural = alwaysPlural;
	}

	public String getName() {
		return name;
	}

	public String getNamePlural() {
		return namePlural;
	}
	
	public boolean isAlwaysPlural() {
		return alwaysPlural;
	}
	
	public static ScarType getScarTypeFromString(String value) {
		if(value=="BRUIS") {
			return BRUISE;
		}
		return ScarType.valueOf(value);
	}
}
