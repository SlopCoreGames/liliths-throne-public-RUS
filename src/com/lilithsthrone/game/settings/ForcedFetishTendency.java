package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.2.3
 * @author FeiFongWong
 */
public enum ForcedFetishTendency {
	
	BOTTOM_HEAVY("Приоритет низа+", "Принудительные фетиши почти всегда будут добавлять практики в роли принимающего и убирать практики в роли ведущего.", PresetColour.BASE_PINK_LIGHT),
	BOTTOM("Приоритет низа", "Хотя вкусы NPC всё еще имеют значение, принудительные фетиши обычно будут добавлять практики в роли принимающего и убирать практики в роли ведущего.", PresetColour.BASE_PINK),
	NEUTRAL("Нейтрально", "Принудительные фетиши будут определяться исключительно вкусами и прихотями контролирующего NPC, а также присущей вселенной случайностью.", PresetColour.ANDROGYNOUS),
	TOP ("Приоритет верха", "Хотя вкусы NPC всё еще имеют значение, принудительные фетиши обычно будут добавлять практики в роли ведущего и убирать практики в роли принимающего.", PresetColour.BASE_PURPLE_LIGHT),
	TOP_HEAVY("Приоритет верха+", "Принудительные фетиши почти всегда будут добавлять практики в роли ведущего и убирать практики в роли принимающего.", PresetColour.BASE_PURPLE);

	private final String name;
	private final String description;
	private final Colour colour;

	ForcedFetishTendency(String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Colour getColour() {
		return colour;
	}

}
