package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.7.2
 * @author Innoxia
 */
public enum OrificeModifier {
	
	PUFFY("puffy", ""),
	
	RIBBED("internally-ribbed", ""),
	
	TENTACLED("tentacled", ""),
	
	MUSCLE_CONTROL("internally-muscled", "Предотвращает превращение этого отверстия в «слишком свободное», независимо от того, насколько тонким будет проникновение или насколько растянутым станет его просвет.");
	
	
	private String name;
	private String description;

	private OrificeModifier(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "Не влияет на игровой процесс.";
		}
		return description;
	}
}
