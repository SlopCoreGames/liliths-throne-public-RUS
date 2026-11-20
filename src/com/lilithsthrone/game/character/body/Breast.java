package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Breast implements BodyPartInterface {
	
	public static final int MAXIMUM_BREAST_ROWS = 6;
	public static final int MAXIMUM_NIPPLES_PER_BREAST = 4;
	
	protected AbstractBreastType type;
	protected BreastShape shape;
	protected int size;
	protected int rows;
	protected int milkStorage;
	protected float milkStored;
	/** Measured in mL/day */
	protected int milkRegeneration;
	protected int nippleCountPerBreast;
	
	protected Nipples nipples;
	protected FluidMilk milk;
	
	/**
	 * @param size in inches from bust to underbust using the UK system.
	 * @param lactation in mL.
	 */
	public Breast(AbstractBreastType type, BreastShape shape, int size, int milkStorage, int rows,
			int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int nippleCountPerBreast, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.shape = shape;
		this.size = size;
		this.milkStorage = milkStorage;
		this.milkStored = milkStorage;
		this.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		this.nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, areolaeShape, Lactation.getLactationFromInt(milkStorage).getAssociatedWetness().getValue(), capacity, depth, elasticity, plasticity, virgin, false);
		
		this.milk = new FluidMilk(type.getFluidType(), false);
	}

	public Breast(Breast breastToCopy) {
		this.type = breastToCopy.type;
		this.shape = breastToCopy.shape;
		this.size = breastToCopy.size;
		this.milkStorage = breastToCopy.milkStorage;
		this.milkStored = breastToCopy.milkStored;
		this.milkRegeneration = breastToCopy.milkRegeneration;
		this.rows = breastToCopy.rows;
		this.nippleCountPerBreast = breastToCopy.nippleCountPerBreast;
		
		this.nipples = new Nipples(breastToCopy.nipples);
		
		this.milk = new FluidMilk(breastToCopy.milk);
	}
	
	@Override
	public AbstractBreastType getType() {
		return type;
	}

	public BreastShape getShape() {
		return shape;
	}

	public String setShape(GameCharacter owner, BreastShape shape) {
		if (shape == getShape()) {
			if(!isCharacterInitialised(owner)) {
				return "";
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(у [npc.Name] уже [npc.has] "+shape.getDescriptor()+" груди, так что ничего не происходит...)]</p>");
		}
		
		this.shape = shape;
		
		if(!owner.hasBreasts()) {
			return UtilText.parse(owner,
					"<p>Странное покалывание поднимается к [npc.namePos] [npc.breasts+], но так как у [npc.she] [npc.do] нет груди, кажется, ничего не происходит...<br/>Если [npc.she] когда-нибудь [npc.verb(grow)], у [npc.name] теперь будут [style.boldSex("+shape.getDescriptor()+" грудь)]!</p>");
		}
		
		return UtilText.parse(owner,
				"<p>Странное покалывание поднимается к [npc.namePos] [npc.breasts+], и прежде чем [npc.she] [npc.verb(know)] поймет, что происходит, они уже изменили свою форму...<br/>[npc.Name] теперь [npc.has] [style.boldSex("+shape.getDescriptor()+" грудь)]!</p>");
		
	}

	public Nipples getNipples() {
		return nipples;
	}

	public void setMilk(FluidMilk milk) {
		this.milk = milk;
	}

	public FluidMilk getMilk() {
		return milk;
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
		List<String> list = new ArrayList<>();
		
		if(nippleCountPerBreast == 4) {
			list.add("quad-nippled");
		} else if(nippleCountPerBreast == 3) {
			list.add("tri-nippled");
		} else if(nippleCountPerBreast == 2) {
			list.add("dual-nippled");
		} 
		
		list.add(type.getDescriptor(owner));
		list.add(this.getSize().getDescriptor());
		list.add(this.getShape().getDescriptor());
		
		if(owner.getBodyMaterial().getPartDescriptors()!=null && !owner.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(owner.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(list);
	}

	public boolean hasBreasts() {
		return size>=CupSize.getMinimumCupSizeForBreasts().getMeasurement();
	}

	public String setType(GameCharacter owner, AbstractBreastType type) {
		if(!Main.game.isStarted() || !isCharacterInitialised(owner)) {
			this.type = type;
			nipples.setType(owner, type.getNippleType());
			milk.setType(type.getFluidType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
				owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] грудь [npc.a_breastRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>Передняя часть [npc.namePos] торса внезапно становится невероятно мягкой и чувствительной, и [npc.she] не может сдержать [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинается трансформация. [npc.Her] соски и ареолы покалывает и твердеет, заставляя [npc.herHim] тяжело дышать и издавать похотливый [npc.moan], пока интенсивная трансформация идет своим чередом. Хотя [npc.breastFullDescription], покрывающий [npc.her] [npc.breasts], не меняется, [npc.she] [npc.verb(feel)] #IFnpc.hasBreasts()#THENих#ELSEеё#ENDIF внутренняя структура смещается и принимает новую форму. Спустя мгновение трансформация завершается, оставляя [npc.herHim] с [npc.totalNipples] новыми сосками.<br/>");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
		owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);

		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}


	// Size:

	public CupSize getSize() {
		return CupSize.getCupSizeFromInt(size);
	}

	public int getRawSizeValue() {
		return size;
	}

	/**
	 * Sets the raw size value. Value is bound to >=0 && <=CupSize.MAXIMUM.getMeasurement()
	 * 
	 * @param size Value to set size to.
	 * @return description of size change
	 */
	public String setSize(GameCharacter owner, int size) {
		boolean hadBreasts = hasBreasts();
		
		int oldSize = this.size;
		this.size = Math.max(0, Math.min(size, CupSize.getMaximumCupSize().getMeasurement()));
		int sizeChange = this.size - oldSize;
		if(!isCharacterInitialised(owner)) {
			this.size = size;
			return "";
		}
		
		if(!isAbleToIncubateEggs() && owner.getIncubationLitter(SexAreaOrifice.NIPPLE)!=null) {
			this.size = CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
			return UtilText.parse(owner, "<p style='text-align:center;'>Из-за того, что [npc.namePos] грудь инкубирует яйца, [style.colourMinorBad(их размер не может быть меньше чем "+CupSize.getMinimumCupSizeForEggIncubation().getCupSizeName()+"-й размер)]!</p>");
		}

		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер [npc.namePos] [npc.breasts] не меняется...)]</p>");
		}
		
		String sizeDescriptor = getSize().getDescriptor();
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] покалывающий жар быстро распространяется по [npc.her] торсу, и [npc.she] не может не выдать [npc.a_moan+], когда [npc.her] "
						+ (hadBreasts
								? "[npc.breasts] набухают и [style.boldGrow(растут)].<br/>"
								: "грудь набухает, и прежде чем [npc.she] [npc.verb(know)] осознает, что происходит, пара грудей уже [style.boldGrow(выросла)] из ранее плоского [npc.her] торса.<br/>")
						+ "[npc.Name] теперь [npc.has] [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-го размера":"") + " грудь)]!</p>");
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] ощущает, как покалывающий жар быстро распространяется по торсу [npc.her], и [npc.she] не может сдержать разочарованного [npc.moan], пока [npc.her] [npc.breasts] [style.boldShrink(уменьшаются)].<br/>"
						+ (this.size==0
							? "[npc.Name] теперь [npc.has] [style.boldSex(совершенно плоская грудь)]!"
							: "[npc.Name] теперь [npc.has] [style.boldSex(" + sizeDescriptor + (getSize().getMeasurement()>CupSize.AA.getMeasurement()?", "+getSize().getCupSizeName()+"-го размера":"") + " груди)]!")
					+ "</p>");
		}
	}

	// Lactation:

	public Lactation getMilkStorage() {
		return Lactation.getLactationFromInt(milkStorage);
	}

	public int getRawMilkStorageValue() {
		return milkStorage;
	}

	/**
	 * Sets the milkStorage. Value is bound to >=0 && <=Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 */
	public String setMilkStorage(GameCharacter owner, int milkStorage) {
		int oldLactation = this.milkStorage;
		this.milkStorage = Math.max(0, Math.min(milkStorage, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()));
		int lactationChange = this.milkStorage - oldLactation;
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (lactationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Количество [npc.milk], которое [npc.nameIsFull] способна производить, не меняется...)]</p>");
		}
		
		String lactationDescriptor = getMilkStorage().getDescriptor();
		if (lactationChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] ощущает странное бурление и клокотание глубоко внутри [npc.her] [npc.breasts], и [npc.a_moan+] вырывается из между [npc.her] [npc.lips], когда несколько капель [npc.milk] внезапно просачиваются из [npc.her] [npc.nipples]; явное свидетельство того, что производство [npc.her] [npc.milk] [style.boldGrow(увеличилось)].<br/>[npc.NameIsFull] теперь способна производить [style.boldSex(" + lactationDescriptor + " [npc.milk])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] ощущает странное сосательное чувство глубоко внутри [npc.her] [npc.breasts], и разочарованный вздох вырывается из между [npc.her] [npc.lips], когда [npc.she] [npc.verb(realise)], что [npc.sheIs] чувство [npc.her] [npc.milk] производства [style.boldShrink(иссушает)].<br/>[npc.NameIsFull] теперь способна производить [style.boldSex(" + lactationDescriptor + " [npc.milk])]."
					+ "</p>");
		}
	}
	
	// Stored milk:

	public Lactation getStoredMilk() {
		return Lactation.getLactationFromInt((int) milkStored);
	}
	
	public float getRawStoredMilkValue() {
		return milkStored;
	}

	/**
	 * Sets the milkStorage. Value is bound to >=0 && <=getRawMilkStorageValue()
	 */
	public String setStoredMilk(GameCharacter owner, float milkStored) {
		float oldStoredMilk = this.milkStored;
		this.milkStored = Math.max(0, (Math.min(milkStored, getRawMilkStorageValue())));
		float lactationChange = oldStoredMilk - this.milkStored;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (lactationChange <= 0) {
			return "";
		} else {
			return UtilText.parse(owner,
					"<p style='text-align:center;'><i style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+";'>"
							+ UtilText.returnStringAtRandom(
									Units.fluid(lactationChange, Units.UnitType.LONG)+" [npc.namePos] [npc.milk] брызгает из [npc.her] [npc.nipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" [npc.milk+] брызгает из [npc.namePos] [npc.nipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" [npc.namePos] [npc.milk] вытекает из [npc.her] [npc.nipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" из [npc.namePos] [npc.nipples+] вытекает [npc.milk+].")
					+ "</i>"
					+ (this.milkStored==0
						?"<br/><i>[npc.Name] теперь [npc.has] больше нет [npc.milk], запасенных в [npc.her] груди!</i>"
						:"")
					+ "</p>");
		}
	}

	// Regeneration:

	public FluidRegeneration getLactationRegeneration() {
		return FluidRegeneration.getFluidRegenerationFromInt(milkRegeneration);
	}

	public int getRawLactationRegenerationValue() {
		return milkRegeneration;
	}

	/**
	 * Sets the milkRegeneration. Value is bound to >=0 && <=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()
	 */
	public String setLactationRegeneration(GameCharacter owner, int milkRegeneration) {
		int oldRegeneration = this.milkRegeneration;
		this.milkRegeneration = Math.max(0, Math.min(milkRegeneration, FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()));
		int regenerationChange = this.milkRegeneration - oldRegeneration;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (regenerationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(скорость регенерации [npc.milk] [npc.namePos] не меняется...)]</p>");
		}
		
		String regenerationDescriptor = getLactationRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] an alarming bubbling and churning taking place deep within [npc.her] [npc.breasts], and [npc.a_moan+] drifts out from between [npc.her] [npc.lips] as a few drops of [npc.milk] suddenly leak from [npc.her] [npc.nipples]; clear evidence that [npc.her] [npc.milk] regeneration has [style.boldGrow(increased)].<br/>[npc.NamePos] rate of [npc.milk] regeneration is now [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/день)!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странное сосательное ощущение глубоко внутри [npc.her] [npc.breasts], и разочарованный вздох вырывается из-за [npc.her] [npc.lips], когда [npc.she] осознает, что [npc.sheIs] чувство [npc.her] [npc.milk] регенерации [style.boldShrink(замедляется)].<br/>[npc.NamePos] скорость [npc.milk] регенерации теперь [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/день)!</p>");
		}
	}
	
	
	
	// Rows:
	
	public int getRows() {
		return rows;
	}

	public String setRows(GameCharacter owner, int rows) {
		rows = Math.max(1, Math.min(rows, MAXIMUM_BREAST_ROWS));
		
		if(!isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(rows == getRows()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Количество рядов груди [npc.nameHasFull] не меняется...)]</p>");
		}
		
		String transformation = "";
		
		int rowsDifference = Math.abs(rows - getRows());
		
		if (rows < getRows()) {
			transformation = UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(glance)] обеспокоенно вниз, когда [npc.she] [npc.verb(feel)] странное чувство стягивания в [npc.her] торсе, и прежде чем [npc.sheHasFull] успевает среагировать, [npc.her] "
							+ (rowsDifference==1
								?"нижняя пара [npc.breasts]"
								:"самые нижние "+Util.intToString(rowsDifference)+" пар [npc.breasts]")
							+ " быстро уменьшаются и [style.boldShrink(исчезают)] в [npc.skin] [npc.her] торса.<br/>[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(rows) + " пара"+ (rows > 1 ? "s" : "") + " из " + (hasBreasts() ? "груди" : "грудные мышцы") +")]!" 
						+ "</p>");
			
		} else if (rows > getRows()) {
			transformation = UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(glance)] от неожиданности, так как [npc.she] [npc.verb(feel)] странное чувство распирания в [npc.her] торсе, и прежде чем [npc.sheHasFull] успевает среагировать, "
							+ (rowsDifference==1
								?"дополнительная пара [npc.breasts]"
								:Util.intToString(rowsDifference)+" дополнительные пары [npc.breasts]")
							+ " быстро [style.boldGrow(вырастают)] из [npc.skin] [npc.her] торса.<br/>[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(rows) + " пара"+ (rows > 1 ? "s" : "") + " из " + (hasBreasts() ? "груди" : "грудные мышцы") +")]!" 
						+ "</p>");
		}

		this.rows = rows;

		return transformation;
	}

	public boolean isFuckable() {
		return nipples.getOrificeNipples().getCapacity() != Capacity.ZERO_IMPENETRABLE
				&& size >= CupSize.getMinimumCupSizeForPenetration().getMeasurement()
				&& Main.game.isNipplePenEnabled();
	}

	public boolean isAbleToIncubateEggs() {
		return this.size>=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
	}

	public int getNippleCountPerBreast() {
		return nippleCountPerBreast;
	}

	/**
	 * Minimum 1, maximum MAXIMUM_NIPPLES_PER_BREAST
	 */
	public String setNippleCountPerBreast(GameCharacter owner, int nippleCountPerBreast) {
		nippleCountPerBreast = Math.max(1, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));

		if(!isCharacterInitialised(owner)) {
			this.nippleCountPerBreast = nippleCountPerBreast;
			return "";
		}
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Количество [npc.nipples] [npc.nameHasFull] не меняется...)]</p>");
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
			transformation = UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странное покалывание прямо под поверхностью [npc.breastSkin], покрывающей [npc.her] [npc.breasts]. Изумленный вздох вырывается из [npc.her] рта, когда сила устремляется вверх в [npc.her] [npc.nipples], и [npc.she] [npc.verb(continue)] [npc.moaning], пока некоторые из них [style.boldShrink(втягиваются)] в плоть [npc.her] [npc.breasts].<br/>[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple(true)]") + " на каждой из [npc.her] " + (hasBreasts() ? "груди" : "грудные мышцы") +")]!" 
					+ "</p>");
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
			transformation = UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странное покалывание, пробегающее прямо под поверхностью [npc.breastSkin], покрывающей [npc.her] [npc.breasts]. С [npc.her] рта срывается потрясенный вздох, когда сила устремляется вверх в [npc.her] [npc.nipples], и [npc.she] [npc.verb(continue)] [npc.moaning], пока [npc.she] чувствует, как новые [style.boldGrow(растут)] прямо из плоти [npc.her] [npc.breasts].<br/>[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.nipples]" : "[npc.nipple(true)]") + " на каждой из [npc.her] " + (hasBreasts() ? "груди" : "грудные мышцы") +")]!" 
					+ "</p>");
			
		}
		
		this.nippleCountPerBreast = nippleCountPerBreast;

		return transformation;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Breast.class) && getType().getRace().isFeralPartsAvailable());
	}

}
