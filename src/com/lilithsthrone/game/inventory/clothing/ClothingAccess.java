package com.lilithsthrone.game.inventory.clothing;

/**
 * The types of possible access you'd need in order to equip a piece of clothing.
 * 
 * @since 0.1.64
 * @version 0.2.10
 * @author Innoxia
 */
public enum ClothingAccess {

	/** Placing something onto your eyes, like glasses. */
	EYES("надеть на глаза"),

	/** Placing something into your mouth, like a ball-gag. */
	MOUTH("надеть на рот"),

	/** Pulling something down over your head, like a t-shirt. */
	HEAD("натянуть через голову"),

	/**
	 * Sliding something up your arms to rest on your shoulders, like the loops in a bra.
	 */
	ARMS_UP_TO_SHOULDER("продеть в рукава"),

	/** Sliding something up your wrists, like elbow-length gloves. */
	WRISTS("натянуть на запястья"),

	/** Sliding something down your fingers, like a ring. */
	FINGERS("натянуть на пальцы"),

	/** Sliding something over your chest, like the cups of a bra. */
	CHEST("надеть на грудь"),

	/** Sliding something over your waist, like a corset or swimsuit. */
	WAIST("надеть на талию"),

	/** Pulling something up your legs, like a pair of panties. */
	LEGS_UP_TO_GROIN_LOW_LEVEL("натянуть между ног"),

	/** Sliding something up your legs, like the holes in a pair of trousers. */
	LEGS_UP_TO_GROIN("натянуть на ноги"),

	/** Sliding something over your groin, like a pair of panties.*/
	GROIN("натянуть на пах"),

	/** Sliding something onto your penis, like a condom. */
	PENIS("натянуть на член"), // This gets automatically added to 'clothingAccessBlocked' values if clothing has 'PENIS' in 'blockedBodyParts'.

	/** Inserting something into your vagina, like a dildo. */
	VAGINA("вставить во влагалище"), // This gets automatically added to 'clothingAccessBlocked' values if clothing has 'VAGINA' in 'blockedBodyParts'.

	/** Inserting something into your anus, like a butt-plug. */
	ANUS("вставить в анус"),

	/** Sliding something over your calves, like a pair of knee-high socks. */
	CALVES("натянуть на икры"),

	/** Sliding something over your feet, like a pair of socks. */
	FEET("натянуть на стопы");

	String descriptor;

	private ClothingAccess(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
}
