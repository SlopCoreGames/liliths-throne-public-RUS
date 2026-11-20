package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Antenna implements BodyPartInterface {

	public static final int MAXIMUM_ROWS = 3;
	public static final int MAXIMUM_ANTENNAE_PER_ROW = 4;
	
	protected AbstractAntennaType type;
	protected int rows;
	protected int antennaePerRow;
	protected int length;
	
	public Antenna(AbstractAntennaType type, int length) {
		this.type = type;
		this.length = length;
		rows = 1;
		antennaePerRow = type.getDefaultAntennaePerRow();
	}

	public Antenna(Antenna antennaToCopy) {
		this.type = antennaToCopy.type;
		this.length = antennaToCopy.length;
		this.rows = antennaToCopy.rows;
		this.antennaePerRow = antennaToCopy.antennaePerRow;
	}
	
	@Override
	public AbstractAntennaType getType() {
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

	public String setType(GameCharacter owner, AbstractAntennaType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type.equals(AntennaType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.verb(lack)] антенны, так что ничего не происходит...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.antenna] [npc.a_antennaRace], так что ничего не происходит...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(this.type.equals(AntennaType.NONE)) {
			sb.append(UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] с удивленным вздохом и [npc.verb(rub)] на лоб [npc.her], когда [npc.she] [npc.verb(feel)], как он становится горячим и чувствительным. Спустя мгновение, [npc.her] [npc.eyes] широко распахиваются от шока, когда что-то начинает пробиваться из-под [npc.faceSkin] [npc.her] лба."));
		} else {
			sb.append(UtilText.parse(owner, 
					"<p>[npc.Name] [npc.verb(let)] с удивленным вздохом, когда [npc.she] [npc.verb(feel)] странное покалывание у основания [npc.her] [npc.antennae]. Прежде чем [npc.she] успеют [npc.has] на реакцию, они стремительно рассыпаются в прах, и через мгновение полностью исчезают. "));
		}
		
		if(type!=AntennaType.NONE) {
			sb.append(UtilText.parse(owner, 
					" Из боков головы [npc.her] внезапно пробиваются твердые бугорки, и [npc.she] [npc.verb(gasp)], когда [npc.she] [npc.verb(feel)], они быстро превращаются в "));
		}
		
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner));
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}
	
	public int getAntennaRows() {
		return rows;
	}

	public String setAntennaRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_ROWS));
		if(!isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(owner.getAntennaRows() == rows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		boolean removingAntennae = owner.getAntennaRows() > rows;
		this.rows = rows;
		
		owner.postTransformationCalculation();
		
		if (owner.getAntennaType().equals(AntennaType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		if(removingAntennae) {
			return UtilText.parse(owner,
					"<p>По всему [npc.namePos] [npc.antennae] разливается покалывающее чувство, которое затем спускается ниже и концентрируется на [npc.her] лбу. [npc.She] не может сдержать крика, когда некоторые из них [npc.she] [npc.verb(feel)] [style.boldShrink(осыпаясь и исчезая)] обратно в [npc.her] [npc.faceSkin].<br/>Спустя несколько мгновений, у [npc.sheIs] остались [style.boldTfGeneric([npc.a_antennae])].</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>По всему [npc.namePos] [npc.antennae] разливается покалывающее чувство, которое затем спускается ниже и концентрируется на [npc.her] лбу. [npc.She] не может сдержать крика, когда новые [npc.antennae] [npc.she] [npc.verb(feel)] [style.boldGrow(выталкиваются наружу)] и вырастают из [npc.her] [npc.faceSkin].<br/>Спустя несколько мгновений, у [npc.sheIs] остались [style.boldTfGeneric([npc.a_antennae])].</p>");
		}
	}
	
	public int getAntennaePerRow() {
		return antennaePerRow;
	}

	public String setAntennaePerRow(GameCharacter owner, int antennaePerRow) {
		antennaePerRow = Math.max(1, Math.min(antennaePerRow, MAXIMUM_ANTENNAE_PER_ROW));
		if(!isCharacterInitialised(owner)) {
			this.antennaePerRow = antennaePerRow;
			return "";
		}
		
		if(owner.getAntennaePerRow() == antennaePerRow) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		boolean removingAntennae = owner.getAntennaePerRow() > antennaePerRow;
		this.antennaePerRow = antennaePerRow;
		
		owner.postTransformationCalculation();
		
		if (owner.getAntennaType().equals(AntennaType.NONE)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		if(removingAntennae) {
			return UtilText.parse(owner,
					"<p>Странное бурлящее ощущение начинает зарождаться у основания [npc.namePos] [npc.antennae]. Прежде чем [npc.she] успевает среагировать, они начинают [style.boldShrink(осыпаться и менять форму)].<br/>Спустя несколько мгновений, у [npc.sheIs] остались [style.boldTfGeneric("+Util.intToString(antennaePerRow)+" "+(antennaePerRow==1?"[npc.antenna]":"[npc.antennae]")+(rows==1?"":" в каждой строке")+")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>Странное бурлящее ощущение начинает подниматься в [npc.namePos] лбу. Прежде чем [npc.she] успевает среагировать, новые [npc.antennae] внезапно [style.boldGrow(выталкиваются)] и вырастают из [npc.her] [npc.faceSkin].<br/>Спустя несколько мгновений, у [npc.sheIs] остались [style.boldTfGeneric("+Util.intToString(antennaePerRow)+" "+(antennaePerRow==1?"[npc.antenna]":"[npc.antennae]")+(rows==1?"":" в каждой строке")+")]!"
					+ "</p>");
		}
	}

	public int getTotalAntennae() {
		return getAntennaRows() * getAntennaePerRow();
	}
	
	public HornLength getAntennaLength() {
		return HornLength.getLengthFromInt(length);
	}
	
	public int getAntennaLengthValue() {
		return length;
	}

	public String setAntennaLength(GameCharacter owner, int length) {
		int oldLength = this.length;
		this.length = Math.max(0, Math.min(length, HornLength.FOUR_MASSIVE.getMaximumValue()));
		int sizeChange = this.length - oldLength;
		
		if (!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(owner.getAntennaType().equals(AntennaType.NONE)) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(У [npc.Name] [npc.do] нет антенн, так что, кажется, ничего не происходит...)]</p>");
		}
		
		if(sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Длина [npc.namePos] [npc.antennae] не меняется...)]</p>");
		}
		
		if(sizeChange < 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] издает крик, когда [npc.she] [npc.verb(feel)] странное покалывание, пробегающее сквозь [npc.her] [npc.face] и уходящее в [npc.her] [npc.antennae], после чего они внезапно уменьшаются и [style.boldShrink(становятся заметно короче)].<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric([npc.antennaSize] [npc.antennae])]!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] издает тихий вскрик, когда [npc.she] [npc.verb(feel)] теплое пульсирующее ощущение, пробегающее сквозь [npc.her] [npc.face] и уходящее в [npc.her] [npc.antennae], после чего они внезапно вырастают и [style.boldGrow(становятся заметно длиннее)].<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric([npc.antennaSize] [npc.antennae])]!</p>");
		}
	}
	
	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Antenna.class) && getType().getRace().isFeralPartsAvailable());
	}
}
