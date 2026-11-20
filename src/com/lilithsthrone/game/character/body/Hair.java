package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Hair implements BodyPartInterface {

	protected AbstractHairType type;
	protected int length;
	protected HairStyle style;
	protected boolean neckFluff;

	public Hair(AbstractHairType type, int length, HairStyle style, RaceStage ownerRaceStage) {
		this.type = type;
		this.length = length;
		this.style = style;
		
		neckFluff = false;
		if((!type.isNeckFluffRequiresGreater() || ownerRaceStage!=RaceStage.GREATER) && Math.random()<type.getNeckFluffChance()) {
			neckFluff = true;
		}
	}

	public Hair(Hair hairToCopy) {
		this.type = hairToCopy.type;
		this.length = hairToCopy.length;
		this.style = hairToCopy.style;
		this.neckFluff = hairToCopy.neckFluff;
	}
	
	@Override
	public AbstractHairType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}
	
	public String getDescriptor(GameCharacter owner) {
		return type.getDescriptor(owner);
	}
	
	public String setType(GameCharacter owner, AbstractHairType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.hair(true)] [npc.a_hairRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<p>[npc.NamePos] кожа головы покалывает и чешется, и [npc.she] [npc.verb(rub)] макушку [npc.her], пока [npc.she] [npc.verb(feel)] [npc.her] [npc.hair(true)] начинают трансформироваться. ");

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public HairLength getLength() {
		return HairLength.getHairLengthFromInt(length);
	}

	public int getRawLengthValue() {
		return length;
	}

	/**
	 * Sets the length value. Value is bound to >=0 && <=HairLength.SEVEN_TO_FLOOR.getMaximumValue()
	 */
	public String setLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HairLength.SEVEN_TO_FLOOR.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		String styleChange = "";
		if(this.length < owner.getHairStyle().getMinimumLengthRequired()) {
			styleChange = "<p>[npc.Her] [npc.hair(true)] "+(owner.getHairType().isDefaultPlural(owner)?"are":"is")+" слишком коротко для текущей прически [npc.her]!</p>"
						+ owner.setHairStyle(HairStyle.NONE);
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Длина [npc.hair(true)] [npc.namePos] не меняется...)]</p>");
			
		} else if (sizeChange < 0) {
			String hairChangedText;
			if (this.length == 0 && owner.isFaceBaldnessNatural()) {
				hairChangedText = "нет [npc.hair(true)]";
			} else {
				hairChangedText = "[npc.hairLength], " + Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [npc.hair(true)]";
			}
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] непроизвольный крик и [npc.verb(rub)] в [npc.her] кожу головы, пока [npc.she] [npc.verb(feel)] [npc.her] [npc.hair(true)] [style.boldShrink(становится короче)].<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric(" + hairChangedText + ")]!"
					+ "</p>"
					+ styleChange);
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] непроизвольный крик и [npc.verb(rub)] в [npc.her] кожу головы, пока [npc.she] [npc.verb(feel)] [npc.her] [npc.hair(true)] [style.boldGrow(становится длиннее)].<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.hairLength], "+ Units.size(this.length, Units.UnitType.LONG_SINGULAR) +" [npc.hair(true)])]!</p>"
					+ styleChange);
		}
	}

	public HairStyle getStyle() {
		return style;
	}
	
	public String setStyle(GameCharacter owner, HairStyle style) {
		this.style = style;
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
			sb.append("[npc.NamePos] [npc.hair(true)] "+(type.isDefaultPlural(owner)?"являются":"есть")+" сейчас ");
			switch(style) { //TODO This should be handled in the style itself
				case BRAIDED:
					sb.append("заплетено в косы.");
					break;
				case CURLY:
					sb.append("кудрявые.");
					break;
				case LOOSE:
					sb.append("распущенные.");
					break;
				case NONE:
					sb.append("не уложены и примут "+(type.isDefaultPlural(owner)?"их":"её")+" естественную форму.");
					break;
				case PONYTAIL:
					sb.append("собраны в хвост.");
					break;
				case STRAIGHT:
					sb.append("выпрямлены.");
					break;
				case TWIN_TAILS:
					sb.append("собраны в два хвостика.");
					break;
				case WAVY:
					sb.append("волнистые.");
					break;
				case MOHAWK:
					sb.append("уложенные в ирокез.");
					break;
				case AFRO:
					sb.append("уложенные в афро.");
					break;
				case SIDECUT:
					sb.append("уложенные в андеркат.");
					break;
				case BOB_CUT:
					sb.append("уложенные в стрижку боб.");
					break;
				case PIXIE:
					sb.append("уложенные в стрижку пикси.");
					break;
				case SLICKED_BACK:
					sb.append("зачесанные назад.");
					break;
				case MESSY:
					sb.append("неуложенные, совершенно растрепанные.");
					break;
				case HIME_CUT:
					sb.append("выпрямленные и уложенные в стрижку химе.");
					break;
				case CHONMAGE:
					sb.append("выпрямленные, маслянистые и уложенные в чонмаге.");
					break;
				case DREADLOCKS:
					sb.append("уложены в дреды.");
					break;
				case TOPKNOT:
					sb.append("собраны в пучок на макушке.");
					break;
				case BIRD_CAGE:
					sb.append("уложены в замысловатую прическу «птичья клетка».");
					break;
				case TWIN_BRAIDS:
					sb.append("уложены в две косы.");
					break;
				case DRILLS:
					sb.append("уложены в локоны в стиле одзё.");
					break;
				case LOW_PONYTAIL:
					sb.append("уложены в низкий хвост.");
					break;
				case CROWN_BRAID:
					sb.append("уложены в виде венка из косы.");
					break;
				case BUN:
					sb.append("уложены в пучок.");
					break;
				case CHIGNON:
					sb.append("собраны и уложены в шиньон.");
					break;
				case SIDE_BRAIDS:
					sb.append("уложены в две косы, спадающие по обе стороны лица [npc.her].");
					break;
				case SIDE_PARTED:
					sb.append("уложены с боковым пробором.");
					break;
			}
		sb.append("</p>");
		return UtilText.parse(owner, sb.toString());
	}

	public boolean isNeckFluff() {
		return neckFluff;
	}

	public String setNeckFluff(GameCharacter owner, boolean neckFluff) {
		if(owner!=null && this.neckFluff == neckFluff) {
			if(this.neckFluff) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] на шее уже [npc.has] [npc.hair(true)], так что ничего не происходит...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] на шее уже [npc.verb(lack)] [npc.hair(true)], так что ничего не происходит...)]</p>");
			}
		}
		this.neckFluff = neckFluff;
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		
		if (neckFluff) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] прерывисто вздыхает, когда [npc.she] [npc.verb(feel)] значительное количество [npc.hair(true)] [style.boldGrow(отрастает вокруг [npc.her] шеи и верхней части груди)]!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] прерывисто вздыхает, когда [npc.she] [npc.verb(feel)] [npc.hair(true)] на [npc.her] шее [style.boldShrink(уменьшается и исчезает)].</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Hair.class) && getType().getRace().isFeralPartsAvailable());
	}
}
