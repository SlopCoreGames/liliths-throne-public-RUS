package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Penis implements BodyPartInterface {
	
	public static final float TWO_PENIS_SIZE_MULTIPLIER = 1.6f;

	protected AbstractPenisType type;
	protected AbstractPenisType previousType;
	protected int length;
	protected int girth;
	protected boolean pierced;
	protected boolean virgin;
	protected Set<PenetrationModifier> penisModifiers;
	
	protected Testicle testicle;
	protected OrificePenisUrethra orificeUrethra;

	public Penis(AbstractPenisType type, int length, boolean usePenisSizePreference, int girth, int testicleSize, int cumProduction, int testicleCount) {
		this.type = type;
		if(usePenisSizePreference) {
			this.length = Math.max(1, Math.min(PenisLength.SEVEN_STALLION.getMaximumValue(), length)+Main.getProperties().penisSizePreference);
		} else {
			this.length = Math.min(PenisLength.SEVEN_STALLION.getMaximumValue(), length);
		}
		this.girth = Math.min(PenetrationGirth.getMaximum(), girth);
		pierced = false;
		virgin = true;
		
		testicle = new Testicle(type.getTesticleType(), testicleSize, cumProduction, testicleCount);
		
		orificeUrethra = new OrificePenisUrethra(testicle.getCumStorage().getAssociatedWetness().getValue(), 0, 2, OrificeElasticity.ZERO_UNYIELDING.getValue(), OrificePlasticity.THREE_RESILIENT.getValue(), true, new ArrayList<>());
		
		this.penisModifiers = new HashSet<>();
		this.penisModifiers.addAll(type.getDefaultRacialPenetrationModifiers());
	}

	public Penis(Penis penisToCopy) {
		this.type = penisToCopy.type;
		this.length = penisToCopy.length;
		this.girth = penisToCopy.girth;
		this.pierced = penisToCopy.pierced;
		this.virgin = penisToCopy.virgin;
		
		this.testicle = new Testicle(penisToCopy.testicle);
		
		this.orificeUrethra = new OrificePenisUrethra(penisToCopy.orificeUrethra);
		
		this.penisModifiers = new HashSet<>(penisToCopy.penisModifiers);
	}
	
	@Override
	public AbstractPenisType getType() {
		return type;
	}
	
	public Testicle getTesticle() {
		return testicle;
	}
	
	public OrificePenisUrethra getOrificeUrethra() {
		return orificeUrethra;
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
	public String getDescriptor(GameCharacter owner) {
		List<String> list = new ArrayList<>();
        
		if(owner.getPenisSize()!=PenisLength.TWO_AVERAGE) {
			list.add(owner.getPenisSize().getDescriptor());
		}
		
		if(owner.getPenisGirth()!=PenetrationGirth.THREE_AVERAGE) {
			list.add(owner.getPenisGirth().getName());
		}
		
		for(PenetrationModifier pm : penisModifiers) {
			if(!Main.game.isInSex() && pm!=PenetrationModifier.SHEATHED) {
				list.add(pm.getName());
			}
		}
		
		if(owner.getPenisCovering()!=null) {
			list.add(owner.getCovering(owner.getPenisCovering()).getColourDescriptor(owner, false, false));
		}
		
		if(owner.isPenisFeral()) {
			list.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"feral",
					"bestial",
					"animalistic")));
		} else {
			list.add(type.getDescriptor(owner));
		}
		
		if(Main.game.isInSex() && Main.sex.getAllParticipants().contains(owner)) {
			if(owner.hasErection()) {
				list.add("hard");
				if(this.getType()!=PenisType.DILDO) {
					list.add("throbbing");
				}
			} else {
				list.add("soft");
				list.add("flaccid");
				if(owner.isErectionPreventedPhysically()) {
					list.add("caged");
					list.add("imprisoned");
				}
			}
		}
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		list.removeIf(d->d.isEmpty());
		if(list.isEmpty()) {
			return "";
		}
		return Util.randomItemFrom(list);
	}
	
	public String getUrethraDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<>();
		
		for(OrificeModifier om : orificeUrethra.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		descriptorList.add(type.getDescriptor(owner));
		
		descriptorList.add(Capacity.getCapacityFromValue(orificeUrethra.getStretchedCapacity()).getDescriptor().replaceAll(" ", "-"));

		return Util.randomItemFrom(descriptorList);
	}
	
	public String getPenisHeadName(GameCharacter gc) {
		List<String> list = new ArrayList<>();
		list.add("head");
        
		if(penisModifiers.contains(PenetrationModifier.TAPERED)) {
			list.add("tip");
		}

		return Util.randomItemFrom(list);
	}
	
	public String getPenisHeadDescriptor(GameCharacter gc) {
		List<String> list = new ArrayList<>();
        
		if(penisModifiers.contains(PenetrationModifier.TAPERED)) {
			list.add("tapered");
			list.add("pointed");
		}
		if(penisModifiers.contains(PenetrationModifier.FLARED)) {
			list.add("wide");
			list.add("flared");
			list.add("flat");
		}

		return Util.randomItemFrom(list);
	}

	public String setType(GameCharacter owner, AbstractPenisType type) {
		return setType(owner, type, true);
	}
	
	/**
	 * @param owner The GameCharacter whose penis this is.
	 * @param type The type of penis which should be applied.
	 * @param applyExtraEffects true if you want the default behaviour of the penis type applying its default appearance.
	 * @return A description of the transformation.
	 */
	public String setType(GameCharacter owner, AbstractPenisType type, boolean applyExtraEffects) {
		if(type==PenisType.NONE) {
			setPreviousType(this.type);
		}
		if(this.type==PenisType.NONE) {
			this.orificeUrethra.setStretchedCapacity(this.orificeUrethra.getRawCapacityValue());
		}
		
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			testicle.setType(owner, type.getTesticleType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
				owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			if(type==PenisType.NONE) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.verb(lack)] член, так что ничего не происходит...)]</p>");
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.a_cockRace]'s член, так что ничего не происходит...)]</p>");
			}
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
			if(!owner.hasPenisIgnoreDildo()) {
				if(length<1 && applyExtraEffects) {
					length = 1;
				}
				sb.append(
					"[npc.Name] [npc.verb(feel)] сильный жар, разгорающийся в паху [npc.her], и [npc.she] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] [npc.skin] "
								+ (owner.hasVagina()
										? (!owner.isTaur()
											?"над [npc.her] киской"
											:"под [npc.her] киской")
										: "между ног [npc.her]")
							+ " напрягается и начинает выпирать. Спустя мгновение образуется большая выпуклость "
							+ (owner.hasVagina()
									? (!owner.isTaur()
										?"над женской щелью [npc.her]"
										:"под женской щелью [npc.her]")
									: "посреди паха [npc.her],")
							+ " и с внезапным ощущением расщепления бугорок выпячивается, превращаясь в член.");
			
				if(owner.isInternalTesticles()) {
					sb.append(
							" Когда новый член [npc.her] обвисает, "
								+ (owner.hasVagina()
									? (!owner.isTaur()
											?"ударяясь о [npc.her] киску, [npc.she] [npc.verb(feel)] [npc.a_balls] растет внутри [npc.her] паха,"
											:"под [npc.her] киской, [npc.she] [npc.verb(feel)] [npc.a_balls] растет внутри [npc.her] паха,")
									: "между ног [npc.her], [npc.she] [npc.verb(feel)] [npc.a_balls] растет внутри [npc.her] паха,")
							+ " и [npc.she] [npc.verb(let)] наружу невольный [npc.moan], когда [npc.her] новый половой орган завершает свое формирование.<br/>");
				} else {
					sb.append(
							" Когда новый член [npc.her] обвисает, "
								+ (owner.hasVagina()
									? (!owner.isTaur()
											?"ударяясь о [npc.her] киску, [npc.she] [npc.verb(feel)] [npc.a_balls] выпирает между [npc.her] двумя полами,"
											:"под [npc.her] киской, [npc.she] [npc.verb(feel)] [npc.a_balls] выпирает между [npc.her] двумя полами,")
									: "между ног [npc.her], [npc.she] [npc.verb(feel)] [npc.a_balls] выпирает под основанием [npc.her] нового ствола,")
							+ " и [npc.she] [npc.verb(let)] наружу невольный [npc.moan], когда [npc.her] новый половой орган завершает свое формирование.<br/>");
				}
				
			} else {
				sb.append(
						"[npc.Name] [npc.verb(let)] из неожиданности, когда [npc.she] [npc.verb(feel)] [npc.her] [npc.cock] внезапно встает дыбом, и прежде чем [npc.sheIs] успевает попытаться взять [npc.her] неожиданную эрекцию под контроль, [npc.her] вздох превращается в [npc.a_moan+], когда [npc.her] член начинает трансформироваться.<br/>");
			}
		sb.append("</p>");
		
		if(applyExtraEffects) {
			// Parse existing content before transformation:
			String s = UtilText.parse(owner, sb.toString());
			sb.setLength(0);
			sb.append(s);
			sb.append("<p>");
				sb.append(this.type.applyAdditionalTransformationEffects(owner, false));
				this.type = type;
				testicle.setType(owner, type.getTesticleType());
				owner.resetAreaKnownByCharacters(CoverableArea.PENIS);
				owner.resetAreaKnownByCharacters(CoverableArea.TESTICLES);
				sb.append(this.type.getTransformationDescription(owner));
				sb.append(this.type.applyAdditionalTransformationEffects(owner, true));
			sb.append("</p>");
			
			if(type!=PenisType.NONE) { // Don't remove modifiers for NONE so that they can be 'restored' if applyExtraEffects==false
				penisModifiers.clear();
				penisModifiers.addAll(type.getDefaultRacialPenetrationModifiers());
				
				sb.append("<p>");
					sb.append("Любые старые модификаторы, которые могли быть у [npc.her] пениса, [style.boldShrink(исчезли при трансформации)]!");
					if(!penisModifiers.isEmpty()) {
						sb.append("<br/>Instead, [npc.her] new cock is:");
						for(PenetrationModifier pm : penisModifiers) {
							sb.append("<br/>[style.boldGrow("+Util.capitaliseSentence(pm.getName())+")]");
						}
					}
				sb.append("</p>");
			}
			
		} else {
			this.type = type;
			testicle.setType(owner, type.getTesticleType());
			AbstractRace penisRace = type.getRace();
			
			sb.append("<p>");
				sb.append("[npc.She] теперь [npc.has] "+UtilText.generateSingularDeterminer(penisRace.getName(true))+" <b style='color:"+penisRace.getColour().toWebHexString()+";'>"+penisRace.getName(true)+"-подобный член</b>, [npc.materialDescriptor] [npc.penisFullDescription(true)].");
				sb.append("<br/>[npc.She] [npc.has] <b style='color:"+penisRace.getColour().toWebHexString()+";'>[npc.ballsCount]#IF(npc.isInternalTesticles()) внутренние#ENDIF яички</b>, [npc.materialDescriptor] [npc.ballsFullDescription(true)], которые производят [npc.cumColour(true)] <b style='color:"+penisRace.getColour().toWebHexString()+";'>"+penisRace.getName(true)+" сперму</b>.");
			sb.append("</p>");
		}
		
		String postTF = owner.postTransformationCalculation(false); // Calculate this before parsing, as it updates covering colours
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
					+ postTF
				+ "</p>";
	}

	public AbstractPenisType getPreviousType() {
		return previousType;
	}

	public void setPreviousType(AbstractPenisType previousType) {
		this.previousType = previousType;
	}
	
	// Girth:

	public PenetrationGirth getGirth() {
		return PenetrationGirth.getGirthFromInt(girth);
	}

	public int getRawGirthValue() {
		return girth;
	}

	/**
	 * Sets the girth. Value is bound to >=0 && <=PenisGirth.FOUR_FAT.getValue()
	 */
	public String setPenisGirth(GameCharacter owner, int girth) {
		if(!isCharacterInitialised(owner)) {
			this.girth = Math.max(0, Math.min(girth, PenetrationGirth.getMaximum()));
			return "";
		}
		
		if(!owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		int girthChange = 0;
		
		if (girth <= 0) {
			if (this.girth != 0) {
				girthChange = 0 - this.girth;
				this.girth = 0;
			}
		} else if (girth >= PenetrationGirth.getMaximum()) {
			if (this.girth != PenetrationGirth.getMaximum()) {
				girthChange = PenetrationGirth.getMaximum() - this.girth;
				this.girth = PenetrationGirth.getMaximum();
			}
		} else {
			if (this.girth != girth) {
				girthChange = girth - this.girth;
				this.girth = girth;
			}
		}
		
		if(girthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Обхват [npc.namePos] [npc.cock] не меняется...)]</p>");
		}
		
		if (girthChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] из-за того, что [npc.a_moan+] ощущает, как [npc.she] [npc.verb(feel)] глубокая пульсация нарастает у основания [npc.her] члена. [npc.Her] щеки краснеют, когда это ощущение поднимается вверх по [npc.her] стволу, и когда капля предэякулята вытекает из головки [npc.her] теперь уже твердого органа, [npc.she] [npc.verb(realise)] понимает, что [npc.her] член [style.boldGrow(стал толще)].<br/>[npc.She] теперь [npc.has] [style.boldSex([npc.a_penisGirth] [npc.cock])]!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] из-за того, что [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокая пульсация нарастает у основания [npc.her] члена. [npc.Her] щеки краснеют, когда это ощущение поднимается вверх по [npc.her] стволу, и когда капля предэякулята вытекает из головки [npc.her] теперь уже твердого члена, [npc.she] [npc.verb(realise)] понимает, что [npc.her] член [style.boldShrink(стал тоньше)].<br/>[npc.She] теперь [npc.has] [style.boldSex([npc.a_penisGirth] [npc.cock])]!</p>");
		}
	}
	
	// Length:

	public PenisLength getLength() {
		return PenisLength.getPenisLengthFromInt(length);
	}

	public int getRawLengthValue() {
		return length;
	}

	/**
	 * Sets the length. Value is bound to >=0 && <=PenisLength.SEVEN_STALLION.getMaximumValue()
	 */
	public String setPenisLength(GameCharacter owner, int length) {
		if(owner!=null && !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		int lengthChange = 0;
		
		if (length <= 0) {
			if (this.length != 0) {
				lengthChange = 0 - this.length;
				this.length = 0;
			}
		} else if (length >= PenisLength.SEVEN_STALLION.getMaximumValue()) {
			if (this.length != PenisLength.SEVEN_STALLION.getMaximumValue()) {
				lengthChange = PenisLength.SEVEN_STALLION.getMaximumValue() - this.length;
				this.length = PenisLength.SEVEN_STALLION.getMaximumValue();
			}
		} else {
			if (this.length != length) {
				lengthChange = length - this.length;
				this.length = length;
			}
		}
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(lengthChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Длина [npc.namePos] [npc.cock] не меняется...)]</p>");
		}
		
		if (lengthChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] из-за того, что [npc.a_moan] как [npc.she] [npc.verb(feel)] глубокая пульсация нарастает у основания [npc.her] члена. [npc.Her] щеки краснеют, когда это ощущение поднимается вверх по [npc.her] стволу, и когда капля предэякулята вытекает из головки [npc.her] теперь уже твердого члена, [npc.she] [npc.verb(realise)] понимает, что [npc.her] член [style.boldGrow(стал больше)].<br/>[npc.She] теперь [npc.has] [style.boldSex([npc.a_penisSize] [npc.cock])]!</p>");
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] чувствует, как [npc.a_moan] в виде [npc.she] [npc.verb(feel)] ощущает интенсивное сжатие у основания [npc.her] члена. [npc.Her] щеки краснеют, когда это чувство поднимается вверх по [npc.her] стволу, и когда капля предэякулята вытекает из головки [npc.her] теперь уже твердого органа, [npc.she] [npc.verb(realise)] понимает, что [npc.her] член [style.boldShrink(уменьшился)].<br/>[npc.She] теперь [npc.has] [style.boldSex([npc.a_penisSize] [npc.cock])]!</p>");
		}
	}
	
	// Diameter:

	public static float getGenericDiameter(int length, PenetrationGirth girth) {
		return getGenericDiameter(length, girth, new HashSet<>());
	}
	
	public static float getGenericDiameter(int length, PenetrationGirth girth, Set<PenetrationModifier> mods) {
		float baseDiameterModifier = 0.2f;
		baseDiameterModifier = Math.max(0.15f, baseDiameterModifier - (Math.max(length-15, 0) * 0.0025f)); // Every cm over 15 (6 inches) reduces the base diameter modifier by 0.25%
		
		return Units.round((length * baseDiameterModifier) * (1f + girth.getDiameterPercentageModifier() + (mods.contains(PenetrationModifier.FLARED)?0.05f:0) + (mods.contains(PenetrationModifier.TAPERED)?-0.05f:0)), 2);
	}
	
	public float getDiameter() {
		return getGenericDiameter(length, getGirth(), penisModifiers);
	}
	
	public boolean isPierced() {
		return pierced;
	}

	public String setPierced(GameCharacter owner, boolean pierced) {
		if(this.pierced == pierced || !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		this.pierced = pierced;
		
		if(pierced) {
			return UtilText.parse(owner, "<p>[npc.NamePos] [npc.cock] теперь [style.boldGrow(проколот)]!</p>");
			
		} else {
			AbstractClothing c = owner.getClothingInSlot(InventorySlot.PIERCING_PENIS);
			String piercingUnequip = "";
			if(c!=null) {
				owner.forceUnequipClothingIntoVoid(owner, c);
				piercingUnequip = owner.addClothing(c, false);
			}
			
			return UtilText.parse(owner,
					"<p>[npc.NamePos] [npc.cock] [style.boldShrink(больше не проколот)]!</p>"
					+piercingUnequip);
		}
	}
	
	public boolean isVirgin() {
		return virgin;
	}

	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}
	
	public Set<PenetrationModifier> getPenisModifiers() {
		return penisModifiers;
	}
	
	public boolean hasPenisModifier(PenetrationModifier modifier) {
		return penisModifiers.contains(modifier);
	}

	public String addPenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(hasPenisModifier(modifier)) {
			return owner == null ? "" : "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		if(!isCharacterInitialised(owner) || owner.getBody()==null) {
			penisModifiers.add(modifier);
			return "";
		}
		
		if(!owner.hasPenisIgnoreDildo()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] у [npc.do] нет члена, так что ничего не происходит...)]</p>");
		}
		
		penisModifiers.add(modifier);

		List<String> pmsRemoved = new ArrayList<>();
		
		for(PenetrationModifier pm : modifier.getMutuallyExclusivePenetrationModifiers()) {
			if(hasPenisModifier(pm)) {
				pmsRemoved.add(pm.getName());
				penisModifiers.remove(pm);
			}
		}
		String removedText = "";
		if(!pmsRemoved.isEmpty()) {
			removedText = "<br/>[style.italicsMinorBad(Due to being mutually exclusive with the '"+modifier.getName()+"' модификатор, [npc.namePos] член больше не "+Util.stringsToStringList(pmsRemoved, false)+".)]";
		}
		
		String returnText = "";
		
		switch(modifier) {
			case RIBBED:
				returnText = "Внутри [npc.namePos] [npc.cock] нарастает сильное давление, но прежде чем [npc.she] [npc.has] успевает среагировать, по всей его длине вырастает ряд [style.boldGrow(твердых, мясистых ребер)].<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь ребристый!)]";
				break;
			case TENTACLED:
				returnText = "Внутри [npc.namePos] [npc.cock] нарастает пульсирующее тепло, но прежде чем [npc.she] [npc.has] успевает среагировать, по всей его длине вырастает ряд [style.boldGrow(маленьких извивающихся щупалец)].<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь покрыт маленькими щупальцами, которые извиваются сами по себе!)]";
				break;
			case BARBED:
				returnText = "Внутри [npc.namePos] [npc.cock] нарастает сильный жар, но прежде чем [npc.she] [npc.has] успевает среагировать, по всей его длине вырастает ряд [style.boldGrow(маленьких мясистых шипов)].<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь усажен мясистыми шипами, направленными назад!)]";
				break;
			case FLARED:
				returnText = "В самой головке [npc.namePos] [npc.cock] нарастает сильный жар, и прежде чем [npc.she] [npc.has] успевает среагировать, [style.boldGrow(головка расширяется)], становясь похожей на лошадиную.<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь обладает широкой, расширенной головкой!)]";
				break;
			case BLUNT:
				returnText = "В самой головке [npc.namePos] [npc.cock] нарастает сильный жар, и прежде чем [npc.she] [npc.has] успевает среагировать, [style.boldGrow(головка сглаживается)], становясь похожей на рептилийскую.<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь обладает гладкой, тупой головкой!)]";
				break;
			case KNOTTED:
				returnText = "В основании [npc.namePos] [npc.cock] нарастает сильный жар, и прежде чем [npc.she] [npc.has] успевает среагировать, там быстро вырастает [style.boldGrow(жирная луковица)].<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь имеет жирную луковицу у основания!)]";
				break;
			case PREHENSILE:
				returnText = "Странное покалывание пробегает по всей длине [npc.namePos] [npc.cock], и [npc.she] внезапно осознает, что он превратился в [style.boldGrow(хватательный)] орган, позволяющий [npc.herHim] извиваться и двигаться, словно хвост примата.<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь стал хватательным!)]";
				break;
			case SHEATHED:
				returnText = "В основании [npc.namePos] [npc.cock] нарастает сильное давление, и прежде чем [npc.she] [npc.has] успеет среагировать, он втягивается в совершенно новую [style.boldGrow(оболочку)], которая только что там выросла.<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь в оболочке!)]";
				break;
			case TAPERED:
				returnText = "Внутри [npc.namePos] [npc.cock] разливается сильный жар, но прежде чем [npc.she] [npc.has] успеет среагировать, ствол внезапно [style.boldGrow(сужается)].<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь сужен!)]";
				break;
			case VEINY:
				returnText = "Внутри [npc.namePos] [npc.cock] разливается сильный жар, но прежде чем [npc.she] [npc.has] успеет среагировать, по всей его длине проступают [style.boldGrow(заметные вены)].<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь в венах!)]";
				break;
			case OVIPOSITOR:
				returnText = "По [npc.namePos] [npc.cock] пробегает интенсивное покалывание, и [npc.she] не может сдержать [npc.a_moan+], когда [npc.she] чувствует, как он превращается в [style.boldGrow(яйцеклад)].<br/>[style.boldSex([npc.NamePos] [npc.cock] теперь может откладывать яйца!)]<br/><i>(Чтобы стать полностью функциональным, [npc.name] [npc.verb(require)] [npc.her] яиц для оплодотворения перед тем, как начнется процесс откладывания.)</i>";
				break;
		}
		
		if(returnText.isEmpty()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		return UtilText.parse(owner,
				"<p>"
					+returnText
					+removedText
				+"</p>");
	}

	public String removePenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		if(!hasPenisModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		penisModifiers.remove(modifier);
		
		switch(modifier) {
			case RIBBED:
				return "<p>Внутри [npc.namePos] [npc.penis] разливается приятная прохлада, но прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] твердые плотские ребра внезапно [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не ребристый!)]</p>";
			case TENTACLED:
				return "<p>Внутри [npc.namePos] [npc.penis] разливается приятная прохлада, и прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] маленькие извивающиеся щупальца [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не покрыт маленькими щупальцами!)]</p>";
			case BARBED:
				return "<p>Внутри [npc.namePos] [npc.penis] разливается приятная прохлада, но прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] маленькие плотские шипы [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не шипованный!)]</p>";
			case FLARED:
				return "<p>В кончике [npc.namePos] [npc.penis] разливается приятная прохлада, и прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] расширенная головка [style.boldShrink(уменьшается)] до обычной, человеческой формы.<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не имеет расширенной головки!)]</p>";
			case BLUNT:
				return "<p>В кончике [npc.namePos] [npc.penis] разливается приятная прохлада, и прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] тупая головка [style.boldShrink(уменьшается)] до обычной, человеческой формы.<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не имеет тупой головки!)]</p>";
			case KNOTTED:
				return "<p>В основании [npc.namePos] [npc.penis] разливается приятная прохлада, и прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] жирный узел [style.boldShrink(уменьшается)] и исчезает.<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не узловатый!)]</p>";
			case PREHENSILE:
				return "<p>Странное покалывание пробегает по всей длине [npc.namePos] [npc.penis], и [npc.she] внезапно осознает, что он [style.boldShrink(больше не цепкий)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не цепкий!)]</p>";
			case SHEATHED:
				return "<p>В основании [npc.namePos] [npc.penis] разливается приятная прохлада, и прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] крайняя плоть [style.boldShrink(исчезает)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не скрыт в складке!)]</p>";
			case TAPERED:
				return "<p>Внутри [npc.namePos] [npc.penis] нарастает сильный жар, но прежде чем [npc.she] [npc.has] успевает среагировать, ствол внезапно [style.boldShrink(расширяется)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не сужается к концу!)]</p>";
			case VEINY:
				return "<p>Внутри [npc.namePos] [npc.penis] нарастает сильный жар, но прежде чем [npc.she] [npc.has] успевает среагировать, [npc.her] выступающие вены [style.boldShrink(исчезают)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не в венах!)]</p>";
			case OVIPOSITOR:
				return "<p>Странное покалывание пробегает по всей длине [npc.namePos] [npc.penis], и [npc.she] не может не издать [npc.a_moan+], когда [npc.she] чувствует, как он превращается в [style.boldShrink(больше не яйцеклад)].<br/>[style.boldSex([npc.NamePos] [npc.penis] больше не яйцеклад!)]</p>";
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
	}
	
	public void clearPenisModifiers() {
		penisModifiers.clear();
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner) || getType()==PenisType.NONE) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Penis.class) && getType().getRace().isFeralPartsAvailable());
	}
}
