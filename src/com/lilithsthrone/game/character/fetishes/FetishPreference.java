package com.lilithsthrone.game.character.fetishes;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Maxis, Innoxia
 */
public enum FetishPreference {
	
	ZERO_DISABLED(PresetColour.TEXT_GREY, "disabled", 0, 0, 0, 0, 0, "Этот фетиш не будет назначен ни одному NPC, если не соблюдены особые условия."),
	ONE_HATE(PresetColour.GENERIC_BAD, "hate", 1, 10, 5, 0, 0, "NPC будут только недолюбливать или ненавидеть этот фетиш, если не соблюдены особые условия."),
	TWO_DISLIKE(PresetColour.GENERIC_MINOR_BAD, "dislike", 2, 5, 10, 3, 1, "NPC будут скорее недолюбливать/ненавидеть этот фетиш, но всё же могут его любить/обожать."),
	THREE_NEUTRAL(PresetColour.TEXT, "neutral", 3, 3, 3, 3, 3, "Никаких предпочтений."),
	FOUR_LIKE(PresetColour.GENERIC_MINOR_GOOD, "like", 4, 1, 3, 10, 5, "NPC будут скорее любить/обожать этот фетиш, но всё же могут его недолюбливать/ненавидеть."),
	FIVE_LOVE(PresetColour.GENERIC_GOOD, "love", 5, 0, 0, 3, 5, "NPC будут только любить или обожать этот фетиш."),
	SIX_ALWAYS(PresetColour.GENERIC_EXCELLENT, "always", 6, 0, 0, 0, 1, "У NPC всегда будет этот фетиш.");
	
	private Colour colour;
	private String name;
	private int value;
	private int hate;
	private int dislike;
	private int like;
	private int love;
	private String tooltip;
	
	private FetishPreference(Colour colour, String name, int value, int hate, int dislike, int like, int love, String tooltip) {
		this.colour = colour;
		this.name=name;
		this.value=value;
		this.hate=hate;
		this.dislike=dislike;
		this.like=like;
		this.love=love;
		this.tooltip=tooltip;
	}
	
	public static FetishPreference valueOf(Integer i) {
		for(FetishPreference f: FetishPreference.values()) {
			if(f.getValue() == i) {
				return f;
			}
		}
		return null;
	}
	
	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getHate() {
		return hate;
	}
	
	public int getDislike() {
		return dislike;
	}
	
	public int getLike() {
		return like;
	}
	
	public int getLove() {
		return love;
	}

	public String getTooltip() {
		return tooltip;
	}
}
