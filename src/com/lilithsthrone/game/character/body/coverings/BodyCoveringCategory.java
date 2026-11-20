package com.lilithsthrone.game.character.body.coverings;

/**
 * @since 0.1.0
 * @version 0.4.0
 * @author Innoxia
 */
public enum BodyCoveringCategory {

	// Main covering types
	MAIN_SKIN("skin"),
	MAIN_HAIR("hair"),
	MAIN_FUR("fur"),
	MAIN_SCALES("scales"),
	MAIN_FEATHER("feathers"),
	MAIN_CHITIN("chitin"),
	
	// Eyes
	EYE_IRIS("irises"),
	EYE_PUPIL("pupils"),
	EYE_SCLERA("склеры"),

	// Head
	ANTENNAE("antennae"),
	HORN("рога"),
	ANTLER("рога"),
	HAIR("hair"),

	// Orifices
	ANUS("anus"),
	MOUTH("рот"),
	TONGUE("язык"),
	NIPPLE("nipples"),
	NIPPLE_CROTCH("crotch-nipples"),
	VAGINA("вагина"),
	PENIS("пенис"),
	SPINNERET("spinneret"),

	// Other
	BODY_HAIR("волосы на теле"),
	
	// Specials
	ARTIFICIAL("dildo") { public boolean isInfluencedByMaterialType() { return false; } },
	FLUID("fluid") { public boolean isInfluencedByMaterialType() { return false; } },
	MAKEUP("makeup") { public boolean isInfluencedByMaterialType() { return false; } };
	
	private String name;

	private BodyCoveringCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * @return true if this BodyCoveringCategory changes based on the material which the character's body is made out of.
	 */
	public boolean isInfluencedByMaterialType() {
		return true;
	}
}
