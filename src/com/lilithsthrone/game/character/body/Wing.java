package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Wing implements BodyPartInterface {

	protected AbstractWingType type;
	protected int size;
	
	public Wing(AbstractWingType type, int size) {
		this.type = type;
		this.size = size;
	}

	public Wing(Wing wingToCopy) {
		this.type = wingToCopy.type;
		this.size = wingToCopy.size;
	}

	@Override
	public AbstractWingType getType() {
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
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}

	public void setType(AbstractWingType type) {
		this.type = type;
	}

	public String setType(GameCharacter owner, AbstractWingType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			this.setSize(owner, this.getSizeValue());
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type == WingType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(у [npc.Name] уже [npc.verb(lack)] крылья, так что ничего не происходит...)]</p>");
				
			} else if(type.getRace()!=Race.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(у [npc.Name] уже [npc.has] есть [npc.wings] [npc.a_wingRace], так что ничего не происходит...)]</p>");
				
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(у [npc.Name] уже [npc.has] "+type.getTransformName()+" [npc.wings], так что ничего не происходит...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();

		if(type != WingType.NONE) {
			sb.append(
					"<p>[npc.Name] [npc.verb(try)] оглянуться [npc.herHim], когда [npc.she] [npc.verb(feel)] странное бурление, поднимающееся вверх по [npc.her] спине, прежде чем что-то начинает проталкиваться из-под [npc.her] [npc.skin].");
			
		} else {
			sb.append(
					"<p>[npc.NamePos] [npc.wings] внезапно начинают дергаться и хлопать сами по себе, и [npc.she] [npc.verb(let)] от неожиданности, когда [npc.she] [npc.verb(feel)] они начинают трансформироваться.");
		}
		
		sb.append(" "+type.getTransformationDescription(owner));
		
		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
				sb.append("</br>Попробовав ими взмахнуть, [npc.sheIs] с восторгом обнаруживает, что они [style.colourGood(достаточно сильны, чтобы позволить [npc.herHim] летать)]!<br/>[style.italics(\", "+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" телам нужны крылья размером не меньше '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"', чтобы обеспечить полет.)]");
			} else {
				sb.append("</br>Попробовав ими взмахнуть, [npc.sheIs] с разочарованием обнаруживает, что они [style.colourBad(недостаточно сильны, чтобы позволить [npc.herHim] летать)]...<br/>[style.italics(\", "+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" телам нужны крылья размером не меньше '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"', чтобы обеспечить полет.)]");
			}
		}
		sb.append("</p>");

		this.type = type;
		this.setSize(owner, this.getSizeValue());
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public WingSize getSize() {
		return WingSize.getWingSizeFromInt(size);
	}

	public int getSizeValue() {
		return size;
	}

	public void setSize(int wingSize) {
		this.size = Math.max(0, Math.min(wingSize, WingSize.getLargest()));
	}

	public void setTypeAndSize(AbstractWingType type, int wingSize) {
		setType(type);
		setSize(wingSize);
	}
	
	public String setSize(GameCharacter owner, int wingSize) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			int effectiveSize = Math.max(this.getType().getMinimumSize().getValue(), Math.min(wingSize, this.getType().getMaximumSize().getValue()));
			this.size = effectiveSize;
			return "";
		}
		
		if(this.getType()==WingType.NONE) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(У [npc.NamePos] [npc.do] нет крыльев, так что ничего не происходит...)]</p>");
		}
		
		int effectiveSize = Math.max(this.getType().getMinimumSize().getValue(), Math.min(wingSize, this.getType().getMaximumSize().getValue()));
		if(owner.getWingSizeValue() == effectiveSize) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер [npc.namePos] [npc.wings] не меняется...)]</p>");
		}

		StringBuilder sb = new StringBuilder();
		
		if(this.size > effectiveSize) {
			sb.append(
					UtilText.parse(owner, "<p>[npc.Name] [npc.verb(let)] испуганно вскрикивает, когда [npc.she] [npc.verb(feel)] успокаивающая прохлада разливается по [npc.her] [npc.wings+], после чего они внезапно [style.boldShrink(сжимаются)].<br/>"));
			
		} else {
			sb.append(
					UtilText.parse(owner, "<p>[npc.Name] [npc.verb(let)] испуганно ахает, когда [npc.she] [npc.verb(feel)] пульсирующее тепло разливается по [npc.her] [npc.wings+], после чего они внезапно [style.boldGrow(увеличиваются)].<br/>"));
		}
		
		this.size = effectiveSize;

		sb.append(UtilText.parse(owner, "[npc.Name] теперь [npc.has] [style.boldSex([npc.wingSize] [npc.wings])]!"));

		if(type!=WingType.NONE) {
			if(this.getSize().getValue() >= owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
				sb.append("</br>Попробовав ими взмахнуть, [npc.sheIs] с восторгом обнаруживает, что они [style.colourGood(достаточно сильны, чтобы позволить [npc.herHim] летать)]!<br/>[style.italics(\", "+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" телам нужны крылья размером не меньше '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"', чтобы обеспечить полет.)]");
			} else {
				sb.append("</br>Попробовав ими взмахнуть, [npc.sheIs] с разочарованием обнаруживает, что они [style.colourBad(недостаточно сильны, чтобы позволить [npc.herHim] летать)]...<br/>[style.italics(\", "+Util.capitaliseSentence(owner.getLegConfiguration().getName())+" телам нужны крылья размером не меньше '"+owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getName()+"', чтобы обеспечить полет.)]");
			}
		}
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Wing.class) && getType().getRace().isFeralPartsAvailable());
	}
}
