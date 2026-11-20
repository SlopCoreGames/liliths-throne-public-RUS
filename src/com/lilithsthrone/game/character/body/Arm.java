package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Arm implements BodyPartInterface {

	
	public static final int MAXIMUM_ROWS = 3;
	
	protected AbstractArmType type;
	protected int armRows;
	protected BodyHair underarmHair;

	public Arm(AbstractArmType type, int armRows) {
		this.type = type;
		this.armRows = armRows;
		underarmHair = BodyHair.ZERO_NONE;
	}

	public Arm(Arm armToCopy) {
		this.type = armToCopy.type;
		this.armRows = armToCopy.armRows;
		this.underarmHair = armToCopy.underarmHair;
	}
	
	@Override
	public AbstractArmType getType() {
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
		List<String> descriptorList = new ArrayList<>();
		
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(Util.randomItemFrom(gc.getBodyShape().getLimbDescriptors()));

		return Util.randomItemFrom(descriptorList);
	}

	public String setType(GameCharacter owner, AbstractArmType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.arms] [npc.a_armRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (owner.isPlayer()) {
			sb.append(
					"<p>Ваши [pc.arms+] внезапно становятся горячими и зудящими; вы ахаете, осознавая, что они начинают трансформироваться. Вы издаете болезненный крик, когда ваши верхние конечности скручиваются и меняются, оставляя вас беспомощными — вам остается лишь наблюдать за тем, как они принимают новую форму.");
		} else {
			sb.append(
					"<p>[npc.Name] ерзает, так как [npc.she] чувствует, как [npc.her] [npc.arms+] становится горячим и зудящим, и [npc.she] ахает, когда [npc.she] понимает, что они начинают трансформироваться. [npc.She] издает болезненный крик, когда [npc.her] верхние конечности скручиваются и меняются, оставляя [npc.her] беспомощным(ой), способным(ой) лишь наблюдать за тем, как они принимают новую форму.");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		
		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getArmRows() {
		return armRows;
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			armRows = Math.max(1, Math.min(armRows, MAXIMUM_ROWS));
			this.armRows = armRows;
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		int currentArmRows = getArmRows();
		armRows = Math.max(1, Math.min(armRows, MAXIMUM_ROWS));
		if (armRows == currentArmRows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (armRows < currentArmRows) {
			boolean losesTwoPairs = (armRows + 2) == currentArmRows;
			if (owner.isPlayer()) {
				sb.append(
						"<p>Вы чувствуете странное давление, нарастающее у основания "
							+ (losesTwoPairs
								? "ваших двух дополнительных пар"
								: (armRows == 2 
									? "самой нижней из ваших дополнительных пар" 
									: "вашей дополнительной пары"))
							+ " [pc.arms], и прежде чем вы успеваете среагировать, они стремительно втягиваются в [pc.skin] вашего торса.<br/>Теперь у вас есть [style.boldTfLesser(" + Util.intToString(armRows) + " пара"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], [pc.materialDescriptor] [pc.armFullDescriptionColour].</p>");
			} else {
				sb.append(UtilText.parse(owner,
						"<p>[npc.Name] обеспокоенно смотрит вниз на "
							+ (losesTwoPairs
								? "[npc.her] две дополнительные пары"
								: (armRows == 2 
									? "самая нижняя из [npc.her] дополнительных пар" 
									: "[npc.her] дополнительная пара"))
							+ " [npc.arms], и прежде чем [npc.she] успеет среагировать, они стремительно втягиваются в [npc.skin] [npc.her] торса.<br/>[npc.She] теперь обладает [style.boldTfLesser(" + Util.intToString(armRows) + " пара"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], [npc.materialDescriptor] [npc.armFullDescriptionColour].</p>"));
			}
			
		} else {
			boolean gainsTwoPairs = (armRows - 2) == currentArmRows;
			if (owner.isPlayer()) {
				sb.append(
						"<p>Вы чувствуете странное давление, нарастающее по бокам вашего торса, и прежде чем вы успеваете среагировать, "
								+ (gainsTwoPairs
									? "две дополнительные пары"
									: "дополнительная пара")
							+ " [pc.arms] стремительно вырастает из [pc.skin] вашего нижнего торса.<br/>Теперь у вас есть [style.boldTfLesser(" + Util.intToString(armRows) + " пара"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], [pc.materialDescriptor] [pc.armFullDescriptionColour].</p>");
			} else {
				sb.append(UtilText.parse(owner,
						"<p>[npc.Name] с беспокойством смотрит на торс [npc.her], и прежде чем [npc.she] успевает среагировать, "
								+ (gainsTwoPairs
									? "две дополнительные пары"
									: "дополнительная пара")
							+ " [npc.arms] стремительно прорастают из [npc.skin] [npc.her] нижней части торса.<br/>[npc.She] теперь имеет [style.boldTfLesser(, " + Util.intToString(armRows) + " пара"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], [npc.materialDescriptor] [npc.armFullDescriptionColour].</p>"));
			}
		}
		
		this.armRows = armRows;
		
		sb.append(UtilText.parse(owner,
				"<p>"
					+ owner.postTransformationCalculation()
				+ "</p>"));
		
		return sb.toString();
	}

	public BodyHair getUnderarmHair() {
		return underarmHair;
	}

	public Covering getUnderarmHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getArmType().getRace()));
	}

	public String setUnderarmHair(GameCharacter owner, BodyHair underarmHair) {
		if(!isCharacterInitialised(owner)) {
			this.underarmHair = underarmHair;
			return "";
		}

		if(!this.getType().isUnderarmHairAllowed()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Так как тип рук [npc.namePos] не позволяет [npc.herHim] отращивать волосы под мышками, ничего не происходит...)]</p>");
		}
		
		if(getUnderarmHair() == underarmHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		switch(underarmHair) {
			case ZERO_NONE:
				sb.append(UtilText.parse(owner, "<p>От <p> больше не осталось и следа "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
			case ONE_STUBBLE:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] колючие участки "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
			case TWO_MANICURED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] обладает ухоженной полоской волос "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
			case THREE_TRIMMED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] аккуратно подстриженные участки "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
			case FOUR_NATURAL:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] естественное количество "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
			case FIVE_UNKEMPT:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] неопрятная масса "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
			case SIX_BUSHY:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] густые, пушистые заросли "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
			case SEVEN_WILD:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] дикие, пушистые заросли "+getUnderarmHairType(owner).getFullDescription(owner, true)+" в подмышках [npc.her].</p>"));
				break;
		}
		
		this.underarmHair = underarmHair;

		return sb.toString();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Arm.class) && getType().getRace().isFeralPartsAvailable());
	}
}
