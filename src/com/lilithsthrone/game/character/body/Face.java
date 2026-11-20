package com.lilithsthrone.game.character.body;

import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Face implements BodyPartInterface {
	
	protected AbstractFaceType type;
	protected boolean piercedNose;
	protected BodyHair facialHair;
	
	protected Mouth mouth;
	protected Tongue tongue;

	public Face(AbstractFaceType type, int lipSize) {
		this.type = type;
		piercedNose = false;
		facialHair = BodyHair.ZERO_NONE;
		
		mouth = new Mouth(type.getMouthType(),
				lipSize,
				Wetness.THREE_WET.getValue(),
				Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(),
				OrificeDepth.TWO_AVERAGE.getValue(),
				OrificeElasticity.FOUR_LIMBER.getValue(),
				OrificePlasticity.THREE_RESILIENT.getValue(),
				true);
		
		tongue = new Tongue(type.getMouthType().getTongueType());
	}

	public Face(Face faceToCopy) {
		this.type = faceToCopy.type;
		this.piercedNose = faceToCopy.piercedNose;
		this.facialHair = faceToCopy.facialHair;
		
		this.mouth = new Mouth(faceToCopy.mouth);
		
		this.tongue = new Tongue(faceToCopy.tongue);
	}
	
	public Mouth getMouth() {
		return mouth;
	}

	public Tongue getTongue() {
		return tongue;
	}

	@Override
	public AbstractFaceType getType() {
		return type;
	}

	public List<BodyPartTag> getTypeTags() {
		return type.getTags();
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
	
	public String getNoseNameSingular(GameCharacter gc) {
		return type.getNoseNameSingular(gc);
	}
	
	public String getNoseNamePlural(GameCharacter gc) {
		return type.getNoseNamePlural(gc);
	}

	public String getNoseDescriptor(GameCharacter gc) {
		return type.getNoseDescriptor(gc);
	}
	
	public String setType(GameCharacter owner, AbstractFaceType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			mouth.setType(type.getMouthType());
			tongue.setType(type.getMouthType().getTongueType());
			if(owner!=null) {
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.face] [npc.a_faceRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		sb.append(
				 "Внезапное интенсивное покалывание проносится по [npc.namePos] [npc.face], и [npc.she] [npc.verb(scrunch)] [npc.her] [npc.eyes], пока [npc.she] [npc.verb(feel)], начиная трансформироваться. С отчетливым хрустом [npc.her] лицевые кости начинают перестраиваться, и хотя это чувство не болезненно, его достаточно, чтобы вызвать у [npc.herHim] непроизвольный крик. ");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;

		mouth.setType(type.getMouthType());
		tongue.setType(type.getMouthType().getTongueType());
		
		sb.append(type.getTransformationDescription(owner));
		sb.append("</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public boolean isPiercedNose() {
		return piercedNose;
	}

	public String setPiercedNose(GameCharacter owner, boolean piercedNose) {
		if(owner.isPiercedNose() == piercedNose) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		this.piercedNose = piercedNose;
		
		if(piercedNose) {
			return UtilText.parse(owner,
					"<p>[npc.NamePos] [npc.nose] теперь [style.boldGrow(проколот)]!</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_NOSE);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>[npc.NamePos] [npc.nose] [style.boldShrink(больше не проколот)]!</p>"
					+piercingUnequip);
		}
	}
	
	public BodyHair getFacialHair() {
		return facialHair;
	}

	public Covering getFacialHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getFaceType().getRace()));
	}

	public String setFacialHair(GameCharacter owner, BodyHair facialHair) {
		if(!isCharacterInitialised(owner)) {
			this.facialHair = facialHair;
			return "";
		}

		if(!this.getType().isFacialHairAllowed()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Так как тип лица [npc.namePos] не позволяет [npc.herHim] отрастить бороду, ничего не происходит...)]</p>");
		}
		
		if(owner.getFemininityValue()>=Femininity.ANDROGYNOUS.getMinimumFemininity() && facialHair!=BodyHair.ZERO_NONE && !Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Ты слишком женственна, чтобы отрастить бороду...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.sheIs] слишком женственна, чтобы отрастить бороду...)]</p>");
			}
		}
		
		if(getFacialHair() == facialHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		StringBuilder sb = new StringBuilder();
		
		switch(facialHair) {
			case ZERO_NONE:
				sb.append(UtilText.parse(owner, "<p>От <p> больше не осталось и следа "+getFacialHairType(owner).getFullDescription(owner, true)+" на лице [npc.her].</p>"));
				break;
			case ONE_STUBBLE:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] щетинистый слой "+getFacialHairType(owner).getFullDescription(owner, true)+" на лице [npc.her].</p>"));
				break;
			case TWO_MANICURED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] немного "+getFacialHairType(owner).getFullDescription(owner, true)+" на лице [npc.her].</p>"));
				break;
			case THREE_TRIMMED:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] аккуратно подстриженную бороду из "+getFacialHairType(owner).getFullDescription(owner, true)+" на лице [npc.her].</p>"));
				break;
			case FOUR_NATURAL:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] бороду из "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
				break;
			case FIVE_UNKEMPT:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] неопрятную, густую бороду из "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
				break;
			case SIX_BUSHY:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] большую, густую бороду из "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
				break;
			case SEVEN_WILD:
				sb.append(UtilText.parse(owner, "<p>[npc.Name] теперь [npc.has] дикую, густую бороду из "+getFacialHairType(owner).getFullDescription(owner, true)+".</p>"));
				break;
		}
		
		this.facialHair = facialHair;
		
		return sb.toString();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Face.class) && getType().getRace().isFeralPartsAvailable());
	}

	public boolean isBaldnessNatural() {
		return this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_AVIAN)
				|| this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_FURRY)
				|| this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_SCALY)
				|| this.getTypeTags().contains(BodyPartTag.FACE_NATURAL_BALDNESS_SKIN);
	}
	
	public boolean isMuzzle() {
		return this.getTypeTags().contains(BodyPartTag.FACE_MUZZLE);
	}
	
	public boolean isHasFangs() {
		return this.getTypeTags().contains(BodyPartTag.FACE_FANGS);
	}
	
	public boolean isBeak() {
		return this.getTypeTags().contains(BodyPartTag.FACE_BEAK);
	}
	
	public boolean isSharkTeeth() {
		return this.getTypeTags().contains(BodyPartTag.FACE_SHARK_TEETH);
	}
}
