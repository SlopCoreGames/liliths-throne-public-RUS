package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Horn implements BodyPartInterface {

	public static final int MAXIMUM_ROWS = 3;
	public static final int MAXIMUM_HORNS_PER_ROW = 4;
	
	protected AbstractHornType type;
	protected int rows;
	protected int hornsPerRow;
	protected int length;
	
	public Horn(AbstractHornType type, int length) {
		if(length<=0) {
			this.length = 0;
			this.type = HornType.NONE;
			
		} else {
			this.type = type;
			this.length = length;
		}
		
		rows = 1;
		hornsPerRow = type.getDefaultHornsPerRow();
	}

	public Horn(Horn hornToCopy) {
		this.type = hornToCopy.type;
		this.length = hornToCopy.length;
		this.rows = hornToCopy.rows;
		this.hornsPerRow = hornToCopy.hornsPerRow;
	}
	
	@Override
	public AbstractHornType getType() {
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

	public void setType(AbstractHornType type) {
		this.type = type;
	}

	public String setType(GameCharacter owner, AbstractHornType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type.equals(HornType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.verb(lack)] рога, так что ничего не происходит...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.horns] [npc.a_hornRace], так что ничего не происходит...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (type == HornType.HORSE_STRAIGHT) {
			this.setHornsPerRow(owner, 1);
			this.setHornRows(owner, 1);
		}

		if(this.type.equals(HornType.NONE)) {
			sb.append(UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] с удивленным вздохом и [npc.verb(rub)] на лоб [npc.her], когда [npc.she] [npc.verb(feel)], как он становится горячим и чувствительным. Спустя мгновение, [npc.her] [npc.eyes] широко распахиваются от шока, когда что-то начинает пробиваться из-под [npc.faceSkin] [npc.her] лба."));
		} else {
			sb.append(UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] от неожиданного вздоха, когда [npc.she] [npc.verb(feel)] странное покалывание у основания [npc.her] "+(owner.getTotalHorns()==1?"[npc.horn]":"[npc.horns]")+". Прежде чем [npc.she] [npc.has] успеет среагировать, "+(owner.getTotalHorns()==1?"оно быстро рассыпается, и через мгновение оно":"они быстро рассыпаются, и через мгновение они")+" полностью исчезают. "));
		}

		if(type!=HornType.NONE) {
			sb.append(UtilText.parse(owner, (owner.getTotalHorns()==1
					?" Твердый нарост внезапно пробивается из середины лба [npc.her], и [npc.she] [npc.verb(gasp)], когда [npc.she] [npc.verb(feel)], как он быстро вырастает в "
					:" Из боков головы [npc.her] внезапно пробиваются твердые бугорки, и [npc.she] [npc.verb(gasp)], когда [npc.she] [npc.verb(feel)], они быстро превращаются в ")));
		}
		
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		
		if(this.length==0) {
			length = HornLength.ONE_SMALL.getMinimumValue();
		}
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getHornRows() {
		return rows;
	}

	public String setHornRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_ROWS));
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(owner.getHornRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		boolean removingHorns = owner.getHornRows() > rows;
		this.rows = rows;
		
		owner.postTransformationCalculation();
		
		if (owner.getHornType().equals(HornType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		if(removingHorns) {
			return UtilText.parse(owner,
					"<p>A tingling feeling spreads over [npc.namePos] [npc.horns], before moving down and concentrating in [npc.her] forehead. [npc.She] can't help but let out a cry as [npc.she] [npc.verb(feel)] some of them [style.boldShrink(crumbling away)] and disappearing back down into [npc.her] [npc.faceSkin].<br/>After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_horns])].</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>По [npc.namePos] [npc.horns] разливается покалывающее чувство, которое затем спускается ниже и концентрируется на лбу [npc.her]. [npc.She] не может сдержать крика, когда у [npc.she] [npc.verb(feel)] новые [npc.horns] [style.boldGrow(пробиваются вверх)] и вырастают из [npc.her] [npc.faceSkin].<br/>Спустя несколько мгновений, у [npc.sheIs] остается [style.boldTfGeneric([npc.a_horns])].</p>");
		}
	}
	
	public int getHornsPerRow() {
		return hornsPerRow;
	}

	public String setHornsPerRow(GameCharacter owner, int hornsPerRow) {
		hornsPerRow = Math.max(1, Math.min(hornsPerRow, MAXIMUM_HORNS_PER_ROW));
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.hornsPerRow = hornsPerRow;
			return "";
		}
		
		if(owner.getHornsPerRow() == hornsPerRow) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		boolean removingHorns = owner.getHornsPerRow() > hornsPerRow;
		this.hornsPerRow = hornsPerRow;
		
		owner.postTransformationCalculation();
		
		if (owner.getHornType().equals(HornType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		if(removingHorns) {
			return UtilText.parse(owner,
					"<p>Странное бурлящее ощущение начинает зарождаться у основания [npc.namePos] [npc.horns]. Прежде чем [npc.she] успевает среагировать, они начинают [style.boldShrink(крошиться и менять форму)].<br/>Спустя несколько мгновений, у [npc.sheIs] остается [style.boldTfGeneric("+Util.intToString(hornsPerRow)+" "+(hornsPerRow==1?"[npc.horn]":"[npc.horns]")+(rows==1?"":" в каждой строке")+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>Странное бурлящее ощущение начинает подниматься в лбу [npc.namePos]. Прежде чем [npc.she] успевает среагировать, новые [npc.horns] внезапно [style.boldGrow(пробиваются вверх)] и вырастают из [npc.her] [npc.faceSkin].<br/>Спустя несколько мгновений, у [npc.sheIs] остается [style.boldTfGeneric("+Util.intToString(hornsPerRow)+" "+(hornsPerRow==1?"[npc.horn]":"[npc.horns]")+(rows==1?"":" в каждой строке")+")]!"
					+ "</p>");
		}
	}

	public int getTotalHorns() {
		return getHornRows() * getHornsPerRow();
	}
	
	public HornLength getHornLength() {
		return HornLength.getLengthFromInt(length);
	}
	
	public int getHornLengthValue() {
		return length;
	}

	public void setHornLength(int length) {
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
	}

	public void setTypeAndLength(AbstractHornType type, int length) {
		setType(type);
		setHornLength(length);
	}

	public String setHornLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			return "";
		}
		
		if(owner.getHornType().equals(HornType.NONE)) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(У [npc.Name] [npc.do] нет рогов, так что, кажется, ничего не происходит...)]</p>");
		}
		
		if(sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Длина [npc.namePos] [npc.horns] не меняется...)]</p>");
		}
		
		if(sizeChange < 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] крик, так как [npc.she] [npc.verb(feel)] странное покалывающее ощущение, пробегающее сквозь [npc.her] [npc.face] и уходящее в [npc.her] [npc.horns], прежде чем "+(getTotalHorns()==1?"оно внезапно уменьшается":"они внезапно уменьшаются")+" вниз и [style.boldShrink("+(getTotalHorns()==1?"получает":"стать")+" заметно короче)].<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric("+(getTotalHorns()==1?"[npc.a_hornSize]":"[npc.hornSize]")+" [npc.horns])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] издает короткий вскрик, когда [npc.she] [npc.verb(feel)] теплое пульсирующее ощущение, пробегающее сквозь [npc.her] [npc.face] и уходящее в [npc.her] [npc.horns], прежде чем "+(getTotalHorns()==1?"оно внезапно увеличивается":"они внезапно увеличиваются")+" наружу и [style.boldGrow("+(getTotalHorns()==1?"получает":"стать")+" заметно длиннее)].<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric("+(getTotalHorns()==1?"[npc.a_hornSize]":"[npc.hornSize]")+" [npc.horns])]!"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Horn.class) && getType().getRace().isFeralPartsAvailable());
	}

}