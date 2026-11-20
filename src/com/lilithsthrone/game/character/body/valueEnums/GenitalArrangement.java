package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.4
 * @author Innoxia
 */
public enum GenitalArrangement {
	
	NORMAL("normal", "[npc.NameHasFull] внешние половые органы расположены отдельно от [npc.her] ануса."),
	
	CLOACA("cloaca", "[npc.NamePos] половые органы и анус расположены внутри щелевидной клоаки, которая находится на том месте, где обычно располагаются [npc.her] половые органы."),
	
	CLOACA_BEHIND("клоака, обращенная назад", "[npc.NamePos] половые органы и анус расположены внутри щелевидной клоаки, которая находится на том месте, где обычно располагается [npc.her] анус.");
	
	
	private String name;
	private String description;

	private GenitalArrangement(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
