package com.lilithsthrone.game.inventory.clothing;

/**
 * @since 0.1.0
 * @version 0.1.96
 * @author Innoxia
 */
public enum DisplacementType {
	
	REMOVE_OR_EQUIP("unequip", "", "", "", ""),
	
	OPEN("open", "opens", "opened", "close", "closes"),
	
	PULLS_UP("подтянуть", "подтягивает", "подтянула", "стянуть", "стягивает"),
	
	PULLS_DOWN("стянуть", "стягивает", "стянула", "подтянуть", "подтягивает"),
	
	PULLS_OUT("вытянуть", "вытягивает", "вытянул", "затолкнуть", "заталкивает"),
	
	PULLS_OFF("стянуть", "стягивает", "стянул", "натянуть обратно", "натягивает обратно"),
	
	SHIFTS_ASIDE("сдвинуть в сторону", "сдвигает в сторону", "сдвинул(а) в сторону", "replace", "заменяет"),
	
	UNZIPS("unzip", "unzips", "unzipped", "застегнуть молнию", "застегивает молнию"),
	
	UNTIE("развязать", "unties", "untied", "tie-up", "ties-up"),
	
	UNBUCKLE("unbuckle", "unbuckles", "расстегнул(а) пряжку", "застегнуть пряжку", "застегивает пряжку"),
	
	UNBUTTONS("unbutton", "расстегивает", "unbuttoned", "застегнуть", "застегивает");

	private String description, descriptionThirdPerson, descriptionPast, oppositeDescription, oppositeDescriptionThirdPerson;

	private DisplacementType(String description, String descriptionThirdPerson, String descriptionPast, String oppositeDescription, String oppositeDescriptionThirdPerson) {
		this.description = description;
		this.descriptionThirdPerson = descriptionThirdPerson;
		this.descriptionPast = descriptionPast;
		this.oppositeDescription = oppositeDescription;
		this.oppositeDescriptionThirdPerson = oppositeDescriptionThirdPerson;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionThirdPerson() {
		return descriptionThirdPerson;
	}

	public String getDescriptionPast() {
		return descriptionPast;
	}

	public String getOppositeDescription() {
		return oppositeDescription;
	}

	public String getOppositeDescriptionThirdPerson() {
		return oppositeDescriptionThirdPerson;
	}
}
