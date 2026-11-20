package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public enum DifficultyLevel {
	
	NORMAL("Человек", "Так игра задумывалась изначально. Без масштабирования уровней и модификаций урона.", PresetColour.RACE_HUMAN, false, 1, 1),

	LEVEL_SCALING("Морф", "Враги повышают уровень вместе с вашим персонажем, но наносят обычный урон.", PresetColour.RACE_CAT_MORPH, true, 1, 1),
	
	HARD("Демон", "Враги повышают уровень вместе с вашим персонажем и наносят 200% dный урон.", PresetColour.RACE_DEMON, true, 2, 1),
	
	NIGHTMARE("Лилин", "Враги повышают уровень вместе с вашим персонажем, наносят 200% dный урон и получают лишь 50% dный урон из всех источников.", PresetColour.BASE_PURPLE, true, 2, 0.5f),
	
	HELL("Lilith", "Уровень врагов всегда в 2 раза выше вашего, они наносят 400% dный урон и получают лишь 25% dный урон из всех источников. Будьте готовы проигрывать. Много.", PresetColour.BASE_CRIMSON, true, 4, 0.25f);

	private String name;
	private String description;
	private Colour colour;
	private boolean isNPCLevelScaling;
	private float damageModifierNPC;
	private float damageModifierPlayer;
	
	private DifficultyLevel(String name, String description, Colour colour, boolean isNPCLevelScaling, float damageModifierNPC, float damageModifierPlayer) {
		this.name = name;
		this.description = description;
		this.colour = colour;
		this.isNPCLevelScaling = isNPCLevelScaling;
		this.damageModifierNPC = damageModifierNPC;
		this.damageModifierPlayer = damageModifierPlayer;
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

	public boolean isNPCLevelScaling() {
		return isNPCLevelScaling;
	}

	public float getDamageModifierNPC() {
		return damageModifierNPC;
	}

	public float getDamageModifierPlayer() {
		return damageModifierPlayer;
	}
}
