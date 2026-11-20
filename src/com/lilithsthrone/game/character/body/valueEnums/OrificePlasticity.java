package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.83
 * @version 0.3.1
 * @author Innoxia
 */
public enum OrificePlasticity {

	/*-------MOULDABLE:---------*/

	/**Instantly returns to starting value after sex.*/
	ZERO_RUBBERY("rubbery",
			"[style.colourPlasticity(мгновенно восстанавливает весь свой первоначальный объем)]",
			"[style.colourPlasticity(мгновенно восстанавливают весь свой первоначальный объем)]",
			0, 0, 100, PresetColour.GENERIC_SIZE_ONE),

	/**Quickly returns to starting value after sex, at a rate of 2 cm per 30 minutes.*/
	ONE_SPRINGY("springy",
			"[style.colourPlasticity(очень быстро восстанавливает весь свой первоначальный объем)]",
			"[style.colourPlasticity(очень быстро восстанавливают весь свой первоначальный объем)]",
			1, 0, 2/(60*30f), PresetColour.GENERIC_SIZE_TWO),

	/**Returns to starting value after sex, at a rate of 2 cm per hour.*/
	TWO_TENSILE("tensile",
			"[style.colourPlasticity(восстанавливает весь свой первоначальный объем с умеренной скоростью)]",
			"[style.colourPlasticity(восстанавливают весь свой первоначальный объем с умеренной скоростью)]",
			2, 0, 2/(60*60f), PresetColour.GENERIC_SIZE_THREE),

	/*-------NORMAL:---------*/

	/**Normal value, along with FOUR_ACCOMMODATING. Will slowly return to starting value after sex, at a rate of 2 cm per 3 hours.*/
	THREE_RESILIENT("resilient",
			"[style.colourPlasticity(медленно восстанавливает весь свой первоначальный объем)]",
			"[style.colourPlasticity(медленно восстанавливают весь свой первоначальный объем)]",
			3, 0, 2/(60*60f*4), PresetColour.GENERIC_SIZE_FOUR),

	/**Normal value, along with THREE_RESILIENT. Will recover about 98% of its original size after sex, at a rate of 2 cm per 12 hours.*/
	FOUR_ACCOMMODATING("accommodating",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsMinorBad(почти весь, но не весь)], свой первоначальный объем)]",
			"[style.colourPlasticity(очень медленно восстанавливают [style.italicsMinorBad(почти весь, но не весь)], свой первоначальный объем)]",
			4, 0.2f, 2/(60*60f*12), PresetColour.GENERIC_SIZE_FIVE),

	/*-------STRETCHY:---------*/

	/**Will recover about 80% of its original size after sex, at a rate of 2 cm per 24 hours.*/
	FIVE_YIELDING("yielding",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsMinorBad(большую часть, но не всю)], от своего первоначального объема)]",
			"[style.colourPlasticity(очень медленно восстанавливают [style.italicsMinorBad(большую часть, но не всю)], от своего первоначального объема)]",
			5, 0.2f, 2/(60*60f*24), PresetColour.GENERIC_SIZE_SIX),

	/**Will only recover about 40% of its original size after sex, at a rate of 2 cm per 24 hours.*/
	SIX_MALLEABLE("malleable",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsBad(лишь малую часть)] своего первоначального объема)]",
			"[style.colourPlasticity(очень медленно восстанавливают [style.italicsBad(лишь малую часть)] своего первоначального объема)]",
			6, 0.6f, 2/(60*60f*24), PresetColour.GENERIC_SIZE_SEVEN),

	/**Will recover none of its original size after sex.*/
	SEVEN_MOULDABLE("mouldable",
			"остается [style.italicsTerrible(растянутым навсегда)] [style.colourPlasticity(и не восстанавливает ни капли своего первоначального объема)]",
			"остаются [style.italicsTerrible(растянутыми навсегда)] [style.colourPlasticity(и не восстанавливают ни капли своего первоначального объема)]",
			7, 1, 2/(60*60f*24), PresetColour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private String description;
	private String descriptionPlural;
	private int value;
	private float capacityIncreaseModifier;
	private float recoveryModifier;
	private Colour colour;

	private OrificePlasticity(String descriptor, String description, String descriptionPlural, int value, float capacityIncreaseModifier, float recoveryModifier, Colour colour) {
		this.descriptor = descriptor;
		this.description = description;
		this.descriptionPlural = descriptionPlural;
		this.value = value;
		this.capacityIncreaseModifier = capacityIncreaseModifier;
		this.recoveryModifier = recoveryModifier;
		this.colour = colour;
	}

	public static OrificePlasticity getElasticityFromInt(int value) {
		for(OrificePlasticity oe : OrificePlasticity.values()) {
			if(value == oe.getValue()) {
				return oe;
			}
		}
		return SEVEN_MOULDABLE;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}
	public String getDescription() {
		return description;
	}
	public String getDescriptionPlural() {
		return descriptionPlural;
	}

	public int getValue() {
		return value;
	}

	public float getRecoveryModifier() {
		return recoveryModifier;
	}
	
	public float getCapacityIncreaseModifier() {
		return capacityIncreaseModifier;
	}

	public Colour getColour() {
		return colour;
	}
}
