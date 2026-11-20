package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.2.8
 * @version 0.4
 * @author Innoxia
 */
public enum FootStructure {

	/**no feet*/
	NONE("none", "[npc.She] [npc.do] не имеет ног."),
	
	/**walk with feet flat on the ground*/
	PLANTIGRADE("plantigrade", "[npc.She] [npc.verb(walk)] с [npc.her] плоскими ступнями на земле."),

	/**walk on toes with the heel permanently raised*/
	DIGITIGRADE("пагообразные", "[npc.She] [npc.verb(walk)] на [npc.her] [npc.toes], при этом [npc.her] пятка постоянно приподнята."),

	/**walk on hoof with the rest of the foot permanently raised*/
	UNGULIGRADE("unguligrade", "[npc.She] [npc.verb(walk)] на [npc.her] [npc.toes], при этом остальная часть [npc.her] стопы постоянно приподнята."),

	/**have segmented legs like a spider, so foot is the 'tarsus' segment.*/
	ARACHNOID("arachnoid", "[npc.She] [npc.verb(walk)] на концах [npc.her] сегментированных паукообразных ног."),
	
	/**use tentacle-legs to walk around on*/
	TENTACLED("щупальцевидные", "[npc.She] [npc.verb(use)] нижние части [npc.her] щупалец, чтобы передвигаться.");
	
	private String name;
	private String description;

	private FootStructure(String name, String description) {
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
