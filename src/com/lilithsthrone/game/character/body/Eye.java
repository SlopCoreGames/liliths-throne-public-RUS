package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Eye implements BodyPartInterface {

	public static final int MAXIMUM_PAIRS = 4;
	
	protected AbstractEyeType type;
	protected int eyePairs;
	protected EyeShape irisShape;
	protected EyeShape pupilShape;
	
	public Eye(AbstractEyeType type) {
		this.type = type;
		eyePairs = type.getDefaultPairCount();
		irisShape = type.getDefaultIrisShape();
		pupilShape = type.getDefaultPupilShape();
	}

	public Eye(Eye eyeToCopy) {
		this.type = eyeToCopy.type;
		this.eyePairs = eyeToCopy.eyePairs;
		this.irisShape = eyeToCopy.irisShape;
		this.pupilShape = eyeToCopy.pupilShape;
	}
	
	@Override
	public AbstractEyeType getType() {
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
	
	public String setType(GameCharacter owner, AbstractEyeType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			irisShape = type.getDefaultIrisShape();
			pupilShape = type.getDefaultPupilShape();
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.EYES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.eyes] [npc.a_eyeRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		if(owner.isArmMovementHindered()) {
			sb.append("[npc.NamePos] [npc.eyes] внезапно становятся горячими и зудящими, заставляя [npc.herHim] инстинктивно крепко зажмуриться. ");
		} else {
			sb.append("[npc.NamePos] [npc.eyes] внезапно становятся горячими и зудящими, и [npc.she] инстинктивно [npc.verb(scrunch)] их крепко, пока [npc.she] [npc.verb(reach)], чтобы почесать их. ");
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		
		this.type = type;
		irisShape = type.getDefaultIrisShape();
		pupilShape = type.getDefaultPupilShape();
		owner.resetAreaKnownByCharacters(CoverableArea.EYES);

		sb.append(type.getTransformationDescription(owner));
		
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getEyePairs() {
		return eyePairs;
	}

	public String setEyePairs(GameCharacter owner, int eyePairs) {
		eyePairs = Math.max(1, Math.min(eyePairs, MAXIMUM_PAIRS));
		
		if(owner.getEyePairs() == eyePairs) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		boolean removingExtraEyes = owner.getEyePairs() > eyePairs;
		this.eyePairs = eyePairs;
		
		if(removingExtraEyes) {
			return UtilText.parse(owner,
					"<p>По [npc.namePos] [npc.face] разливается покалывающее чувство, которое затем поднимается выше и концентрируется в [npc.her] [npc.eyes]. [npc.She] [npc.verb(scrunch)] их, и [npc.verb(let)] невольный крик, когда [npc.she] [npc.verb(feel)] некоторые из них [style.boldShrink(исчезают)] в [npc.faceSkin] над [npc.her] основной парой.<br/>Спустя несколько мгновений, [npc.sheIs] остаются с [style.boldTfGeneric([npc.a_eyes])].</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>По [npc.namePos] [npc.face] разливается покалывающее чувство, которое затем поднимается выше и концентрируется в [npc.her] [npc.eyes]. [npc.She] [npc.verb(scrunch)] их, и [npc.verb(let)] непроизвольный крик, когда [npc.she] [npc.verb(feel)] тревожное ощущение новых [style.boldGrow(растущих)] [npc.eyes] из [npc.faceSkin] над основной парой [npc.her].<br/>Спустя несколько мгновений, [npc.sheIs] остались с [style.boldTfGeneric([npc.a_eyes])].</p>");
		}
	}

	public EyeShape getIrisShape() {
		return irisShape;
	}

	public String setIrisShape(GameCharacter owner, EyeShape irisShape) {
		if(!isCharacterInitialised(owner)) {
			this.irisShape = irisShape;
			return "";
		}
		if(owner.getIrisShape() == irisShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}

		this.irisShape = irisShape;
		
		return UtilText.parse(owner,
				"<p>В области [npc.namePos] [npc.eyes] внезапно вспыхивает раздражающий зуд, но он проходит так быстро, что [npc.she] [npc.do] даже не успевает поднять руку, чтобы почесать их.<br/>Теперь [npc.Name] [npc.has] [style.boldTfGeneric([npc.irisShape] радужки)]!</p>");
	}

	public EyeShape getPupilShape() {
		return pupilShape;
	}

	public String setPupilShape(GameCharacter owner, EyeShape pupilShape) {
		if(!isCharacterInitialised(owner)) {
			this.pupilShape = pupilShape;
			return "";
		}
		if(owner.getPupilShape() == pupilShape) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}

		this.pupilShape = pupilShape;
		
		return UtilText.parse(owner,
				"<p>В области [npc.namePos] [npc.eyes] внезапно вспыхивает раздражающий зуд, но он проходит так быстро, что [npc.she] [npc.do] даже не успевает поднять руку, чтобы почесать их.<br/>Теперь [npc.Name] [npc.has] [style.boldTfGeneric([npc.pupilShape] зрачки)]!</p>");
	}
	
	public String setEyeCovering(GameCharacter owner, Covering covering) {
		if(owner.getCovering(owner.getEyeCovering()).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_PUPILS).equals(covering)
				|| owner.getCovering(BodyCoveringType.EYE_SCLERA).equals(covering)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		owner.getBody().getCoverings().put(covering.getType(), covering);
		
		sb.append(
				"<p>Зрение [npc.NamePos] на мгновение затуманивается, и после пары морганий [npc.her] [npc.eyes] внезапно смещаются и меняют цвет.<br/>[npc.She] теперь [npc.has] ");
		
		if(covering.getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
			sb.append("гетерохромные [npc.irisPrimaryColour(true)]-и-[npc.irisSecondaryColour(true)] [npc.irisShape] радужки, ");
		} else {
			sb.append("[npc.irisPrimaryColour(true)] [npc.irisShape] радужки ");
		}
		if(covering.getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
			sb.append("с гетерохромными [npc.pupilPrimaryColour(true)]-и-[npc.pupilSecondaryColour(true)] [npc.pupilShape] зрачками ");
		} else {
			sb.append("с [npc.pupilPrimaryColour(true)] [npc.pupilShape] зрачками ");
		}
		if(covering.getPattern() == CoveringPattern.EYE_SCLERA_HETEROCHROMATIC) {
			sb.append("и гетерохромными [npc.scleraPrimaryColour(true)]-и-[npc.scleraSecondaryColour(true)] склерами.</p>");
		} else {
			sb.append("и [npc.scleraPrimaryColour(true)] склерами.</p>");
		}
		
		return UtilText.parse(owner, sb.toString());
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Eye.class) && getType().getRace().isFeralPartsAvailable());
	}
}
