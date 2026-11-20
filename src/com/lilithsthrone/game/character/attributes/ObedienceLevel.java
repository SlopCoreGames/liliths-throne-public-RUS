package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.82
 * @version 0.3.8.9
 * @author Innoxia
 */
public enum ObedienceLevel {
	
	
	NEGATIVE_FIVE_REBELLIOUS("rebellious", -100, -90, PresetColour.AFFECTION_NEGATIVE_FIVE),
	
	NEGATIVE_FOUR_DEFIANT("defiant", -90, -70, PresetColour.AFFECTION_NEGATIVE_FOUR),
	
	NEGATIVE_THREE_STRONG_INSUBORDINATE("insubordinate", -70, -50, PresetColour.AFFECTION_NEGATIVE_THREE),
	
	NEGATIVE_TWO_UNRULY("unruly", -50, -30, PresetColour.AFFECTION_NEGATIVE_TWO),
	
	NEGATIVE_ONE_DISOBEDIENT("disobedient", -30, -10, PresetColour.AFFECTION_NEGATIVE_ONE),

	ZERO_FREE_WILLED("free-willed", -10, 10, PresetColour.AFFECTION_NEUTRAL),

	POSITIVE_ONE_AGREEABLE("agreeable", 10, 30, PresetColour.AFFECTION_POSITIVE_ONE),

	POSITIVE_TWO_OBEDIENT("obedient", 30, 50, PresetColour.AFFECTION_POSITIVE_TWO),

	POSITIVE_THREE_DISCIPLINED("disciplined", 50, 70, PresetColour.AFFECTION_POSITIVE_THREE),

	POSITIVE_FOUR_DUTIFUL("dutiful", 70, 90, PresetColour.AFFECTION_POSITIVE_FOUR),

	POSITIVE_FIVE_SUBSERVIENT("subservient", 90, 100, PresetColour.AFFECTION_POSITIVE_FIVE);
	
	
	private String name;
	private int minimumValue;
	private int maximumValue;
	private Colour colour;

	private ObedienceLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	private static String getObedienceName(ObedienceLevel affectionLevel, boolean withColour) {
		if(withColour) {
			return "<span style='color:"+affectionLevel.getColour().toWebHexString()+";'>"+affectionLevel.getName()+"</span>";
		} else {
			return affectionLevel.getName();
		}
	}

	public String getDescription(GameCharacter character, boolean withColour, boolean longDescription) {
		return ObedienceLevel.getDescription(character, this, withColour, longDescription);
	}
	
	public static String getDescription(GameCharacter character, ObedienceLevel affectionLevel, boolean withColour, boolean longDescription) {
		StringBuilder sb = new StringBuilder();
		
		String obedienceName = getObedienceName(affectionLevel, withColour);
		
		switch(affectionLevel) {
			case NEGATIVE_FIVE_REBELLIOUS:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и совершенно [npc.verb(refuse)] выполнять любые приказы.":".")));
				break;
			case NEGATIVE_FOUR_DEFIANT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и [npc.verb(obey)] команды только когда "+(character.isRaceConcealed()?"их":"[npc.sheIs]")+" заставляют физически.":".")));
				break;
			case NEGATIVE_THREE_STRONG_INSUBORDINATE:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и иногда [npc.verb(refuse)] выполнять приказы.":".")));
				break;
			case NEGATIVE_TWO_UNRULY:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и будут отказываться выполнять приказы, если"
						+(character.isRaceConcealed()?" подумают, что они":" [npc.she] [npc.verb(think)], что [npc.she]")
						+ " могут сойти с рук.":".")));
				break;
			case NEGATIVE_ONE_DISOBEDIENT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и часто будут жаловаться на необходимость подчиняться приказам.":".")));
				break;
			case ZERO_FREE_WILLED:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?" и будет делать всё, что "+(character.isRaceConcealed()?"захотят.":"[npc.she] [npc.verb(want)]."):".")));
				break;
			case POSITIVE_ONE_AGREEABLE:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и будет беспрекословно выполнять большинство приказов.":".")));
				break;
			case POSITIVE_TWO_OBEDIENT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и будет готова на почти всё, что "+(character.isRaceConcealed()?"их":"[npc.sheIs]")+" велят.":".")));
				break;
			case POSITIVE_THREE_DISCIPLINED:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и будет беспрекословно выполнять любой приказ.":".")));
				break;
			case POSITIVE_FOUR_DUTIFUL:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и зачастую будет делать даже больше, чем просят у [npc.herHim].":".")));
				break;
			case POSITIVE_FIVE_SUBSERVIENT:
				sb.append(UtilText.parse(character, "[npc.Name] [npc.is] "+obedienceName+(longDescription?", и будет готова на абсолютно всё, что "+(character.isRaceConcealed()?"их":"[npc.sheIs]")+" ей прикажут.":".")));
				break;
		}
		
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public Colour getColour() {
		return colour;
	}

	public static ObedienceLevel getObedienceLevelFromValue(float value){
		for(ObedienceLevel al : ObedienceLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return POSITIVE_FIVE_SUBSERVIENT;
	}
	
	public boolean isGreaterThan(ObedienceLevel levelComparison) {
		return this.getMaximumValue() > levelComparison.getMaximumValue();
	}
	
	public boolean isLessThan(ObedienceLevel levelComparison) {
		return this.getMaximumValue() < levelComparison.getMaximumValue();
	}
}
