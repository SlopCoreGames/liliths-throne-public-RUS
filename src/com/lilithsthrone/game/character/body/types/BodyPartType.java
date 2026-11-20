package com.lilithsthrone.game.character.body.types;

/**
 * @since 0.1.69.9
 * @version 0.4
 * @author Innoxia
 */
public enum BodyPartType {
	
	GENERIC("generic"),
	
	// Limbs/body:
	ARM("arms"),
	LEG("legs"),
	SKIN("skin"),
	TAIL("tail"),
	TENTACLE("tentacle"),
	WING("wings"),
	
	// Ass:
	ASS("ass"),
	ANUS("анус"),

	// Breasts:
	BREAST("breasts"),
	NIPPLES("nipples"),
	MILK("milk"),
	
	// Crotch-boobs:
	BREAST_CROTCH("грудь на лобке"),
	NIPPLES_CROTCH("crotch-nipples"),
	MILK_CROTCH("crotch-breast-milk"),
	
	// Head:
	ANTENNA("антенна"),
	EAR("ears"),
	EYE("eyes"),
	FACE("лицо"),
	MOUTH("mouth"),
	TONGUE("язык"),
	HAIR("hair"),
	HORN("рога"),
	
	// Penis:
	PENIS("penis"),
	TESTICLES("testicles"),
	CUM("cum"),
	
	// Vagina:
	CLIT("clitoris"),
	VAGINA("вагина"),
	GIRL_CUM("девичья сперма"),
	
	// Spinneret:

	SPINNERET("паутинная железа");
	
	private String name;
	private BodyPartType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
}
