package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.1
 * @version 0.4.9.7
 * @author Innoxia
 */
public class BreastCrotch implements BodyPartInterface {
	
	public static final int MAXIMUM_BREAST_ROWS = 6;
	public static final int MAXIMUM_NIPPLES_PER_BREAST = 4;
	
	protected AbstractBreastType type;
	protected BreastShape shape;
	protected int size;
	protected int rows;
	protected int milkStorage;
	protected float milkStored;
	protected int milkRegeneration;
	protected int nippleCountPerBreast;
	
	protected Nipples nipples;
	protected FluidMilk milk;
	
	public BreastCrotch(AbstractBreastType type, BreastShape shape, int size, int milkStorage, int rows,
			int nippleSize, NippleShape nippleShape, int areolaeSize, AreolaeShape areolaeShape, int nippleCountPerBreast, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.shape = shape;
		this.size = size;
		this.milkStorage = milkStorage;
		milkStored = milkStorage;
		milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
		this.rows = rows;
		this.nippleCountPerBreast = nippleCountPerBreast;
		
		nipples = new Nipples(type.getNippleType(), nippleSize, nippleShape, areolaeSize, areolaeShape, Lactation.getLactationFromInt(milkStorage).getAssociatedWetness().getValue(), capacity, depth, elasticity, plasticity, virgin, true);
		
		milk = new FluidMilk(type.getFluidType(), true);
	}

	public BreastCrotch(BreastCrotch breastCrotchToCopy) {
		this.type = breastCrotchToCopy.type;
		this.shape = breastCrotchToCopy.shape;
		this.size = breastCrotchToCopy.size;
		this.milkStorage = breastCrotchToCopy.milkStorage;
		this.milkStored = breastCrotchToCopy.milkStored;
		this.milkRegeneration = breastCrotchToCopy.milkRegeneration;
		this.rows = breastCrotchToCopy.rows;
		this.nippleCountPerBreast = breastCrotchToCopy.nippleCountPerBreast;
		
		this.nipples = new Nipples(breastCrotchToCopy.nipples);
		
		this.milk = new FluidMilk(breastCrotchToCopy.milk);
	}
	
	@Override
	public AbstractBreastType getType() {
		return type;
	}

	public boolean isVisibleThroughClothing(GameCharacter owner) {
		if(owner.getLegConfiguration()==LegConfiguration.BIPEDAL) { // Non-bipedal clothing is assumed to conceal the body so that crotch-boobs are not visible.
			return size>=CupSize.A.getMeasurement();
		} else {
			return false;
		}
	}
	
	public BreastShape getShape() {
		return shape;
	}
	
	public String setShape(GameCharacter owner, BreastShape shape) {
		if (shape == getShape()) {
			if(!isCharacterInitialised(owner)) {
				return "";
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже имеет "+shape.getDescriptor()+" [npc.crotchBoobs], так что ничего не происходит...)]</p>");
		}
		
		this.shape = shape;
		if(shape!=BreastShape.UDDERS && this.getRows()==0) {
			this.rows = 1;
		}
		if(shape==BreastShape.UDDERS && this.getRows()==0 && this.getNippleCountPerBreast()==1) {
			this.nippleCountPerBreast = 2;
		}
		
		if(!owner.hasBreastsCrotch()) {
			return UtilText.parse(owner,
					"<p>Странное покалывание поднимается к паху [npc.namePos], но так как у [npc.she] [npc.do] нет вымени, кажется, ничего не происходит...<br/>Если [npc.she] когда-нибудь [npc.verb(grow)], у [npc.name] теперь будут [style.boldSex("+shape.getDescriptor()+" вымена)]!</p>");
		}
		return UtilText.parse(owner,
				"<p>Странное покалывание поднимается к паху [npc.namePos], и прежде чем [npc.she] [npc.verb(know)] поймет, что происходит, [npc.her] [npc.crotchBoobs] принимает новую форму...<br/>[npc.Name] теперь [npc.has] [style.boldSex("+shape.getDescriptor()+" [npc.crotchBoobs])]!"
				+ "</p>");
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
		if(getShape()==BreastShape.UDDERS && this.getRows()==0) {
			return getNameSingular(gc);
		}
		return getNamePlural(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		if(getShape()==BreastShape.UDDERS) {
			return "вымя";
		}
		return type.getCrotchNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		if(getShape()==BreastShape.UDDERS) {
			return "вымена";
		}
		return type.getCrotchNamePlural(gc);
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
		return type!=BreastType.NONE;
	}

	public String setType(GameCharacter owner, AbstractBreastType type) {
		this.shape = Util.randomItemFrom(RacialBody.valueOfRace(type.getRace()).getBreastCrotchShapes());
		
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
		
		if(type==BreastType.NONE && owner.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
			return UtilText.parse(owner, "<p style='text-align:center;'>Из-за того, что [npc.nameIsFull] инкубирует яйца в [npc.her] [npc.crotchBoobs], [style.colourMinorBad("+(getShape()==BreastShape.UDDERS && this.getRows()==0?"это":"они")+" нельзя убрать)]!</p>");
		}
		
		if (type == getType()) {
			if(type.equals(BreastType.NONE)) {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.verb(lack)] [npc.crotchBoobs], так что ничего не происходит...)]</p>");
			}
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] уже [npc.has] [npc.crotchBoobs] [npc.a_breastRace], так что ничего не происходит...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(type.equals(BreastType.NONE)) { // Removal:
			sb.append(
					"<p>Область над [npc.namePos] промежностью внезапно становится невероятно мягкой и чувствительной, и [npc.she] не может сдержать [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинается трансформация. [npc.Her] [npc.crotchBoobs] покалывают и размягчаются, и, ведомые [npc.her] сосками и ареолами, они быстро уменьшаются и исчезают под [npc.skin], покрывающим [npc.her] низ живота.<br/>");
			
		} else if(this.getType().equals(BreastType.NONE)) { // New addition:
			sb.append(
					"<p>Область над [npc.namePos] пахом внезапно становится невероятно мягкой и чувствительной, и [npc.she] невольно издает [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает происходить трансформация. [npc.CrotchBoobsRows] твердые маленькие бугорки начинают прорастать сквозь [npc.skin], прикрывающие [npc.her] низ живота, и [npc.she] невольно ахает от неожиданности, когда они стремительно раздуваются, превращаясь в [npc.crotchBoobsSize] [npc.crotchBoobs].<br/>");
			
			
		} else {
			sb.append(
					"<p>Область над [npc.namePos] пахом внезапно становится невероятно мягкой и чувствительной, и [npc.she] не может сдержать [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинается трансформация. Соски и ареолы на [npc.her] [npc.crotchBoobs] покалывает и твердеют, заставляя [npc.herHim] тяжело дышать и издавать похотливый [npc.moan], пока интенсивная трансформация подходит к концу. Хотя внешне ничего не меняется, [npc.she] [npc.verb(feel)] внутренняя структура [npc.her] [npc.crotchBoobs] смещается и меняется, принимая новую форму. Спустя мгновение трансформация завершается, оставляя [npc.herHim] с [npc.totalNipples] новыми сосками.<br/>");
		}
		
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		nipples.setType(owner, type.getNippleType());
		milk.setType(type.getFluidType());
		owner.resetAreaKnownByCharacters(CoverableArea.BREASTS);
		owner.resetAreaKnownByCharacters(CoverableArea.NIPPLES);

		sb.append(type.getTransformationCrotchDescription(owner)+"</p>");
		
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
		int oldSize = this.size;
		this.size = Math.max(0, Math.min(size, CupSize.getMaximumCupSize().getMeasurement()));
		int sizeChange = this.size - oldSize;
		if(!isCharacterInitialised(owner)) {
			this.size = size;
			return "";
		}

		if(!isAbleToIncubateEggs() && owner.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
			this.size = CupSize.getMinimumCupSizeForEggIncubation().getMeasurement();
			return UtilText.parse(owner, "<p style='text-align:center;'>Из-за того, что [npc.namePos] [npc.crotchBoobs] вынашивают яйца, [style.colourMinorBad(\""+(getShape()==BreastShape.UDDERS && this.getRows()==0?"её":"их")+" размер нельзя уменьшить ниже "+CupSize.getMinimumCupSizeForEggIncubation().getCupSizeName()+"-й размер)]!</p>");
		}

		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер [npc.namePos] [npc.crotchBoobs] не меняется...)]</p>");
		}
		
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] покалывающий жар быстро распространяется по нижней части туловища [npc.her], и [npc.she] не может не издать [npc.a_moan+], когда [npc.her] [npc.crotchBoobs] набухают и [style.boldGrow(растут)].<br/>[npc.Name] теперь [npc.has] [style.boldSex([npc.crotchBoobsSize] [npc.crotchBoobs])]!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] ощущает, как покалывающий жар быстро разливается по нижней части [npc.her], и [npc.she] не может сдержать разочарованного [npc.moan], пока [npc.her] [npc.crotchBoobs] [style.boldShrink(уменьшаются)].<br/>[npc.Name] теперь [npc.has] [style.boldSex([npc.crotchBoobsSize] [npc.crotchBoobs])]!</p>");
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Количество [npc.crotchMilk], которое [npc.nameIsFull] способна производить, не меняется...)]</p>");
		}
		
		String lactationDescriptor = getMilkStorage().getDescriptor();
		if (lactationChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] ощущает странное бурление и клокотание глубоко внутри [npc.her] [npc.crotchBoobs], и [npc.a_moan+] вырывается из между [npc.her] [npc.lips], когда несколько капель [npc.crotchMilk] внезапно просачиваются из [npc.her] [npc.crotchNipples]; явное свидетельство того, что производство [npc.her] [npc.crotchMilk] [style.boldGrow(увеличилось)].<br/>[npc.NameIsFull] теперь способна производить [style.boldSex(" + lactationDescriptor + " [npc.crotchMilk])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] ощущает странное сосательное чувство глубоко внутри [npc.her] [npc.crotchBoobs], и разочарованный вздох вырывается из между [npc.her] [npc.lips], когда [npc.she] [npc.verb(realise)], что [npc.sheIs] чувство [npc.her] [npc.crotchMilk] производства [style.boldShrink(иссушает)].<br/>[npc.NameIsFull] теперь способна производить [style.boldSex(" + lactationDescriptor + " [npc.crotchMilk])]."
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
									Units.fluid(lactationChange, Units.UnitType.LONG)+" [npc.namePos] [npc.crotchMilk] выплескивается из [npc.her] [npc.crotchNipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" [npc.crotchMilk+] выплескивается из [npc.namePos] [npc.crotchNipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" [npc.namePos] [npc.crotchMilk] вытекает из [npc.her] [npc.crotchNipples+].",
									Units.fluid(lactationChange, Units.UnitType.LONG)+" [npc.crotchMilk+] вытекает из [npc.namePos] [npc.crotchNipples+].")
					+ "</i>"
					+ (this.milkStored==0
						?"<br/><i>[npc.Name] теперь [npc.has] больше нет [npc.crotchMilk], хранящихся в [npc.her] [npc.crotchBoobs]!</i>"
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
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(скорость [npc.crotchMilk] регенерации [npc.namePos] не меняется...)]</p>");
		}
		
		String regenerationDescriptor = getLactationRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] слышится тревожное бурление и клокотание глубоко внутри [npc.her] [npc.crotchBoobs], и [npc.a_moan+] вырывается из меж [npc.her] [npc.lips], когда несколько капель [npc.crotchMilk] внезапно просачиваются из [npc.her] [npc.crotchNipples]; явное свидетельство того, что регенерация [npc.her] [npc.crotchMilk] [style.boldGrow(ускорилась)].<br/>Скорость регенерации [npc.NamePos] [npc.crotchMilk] теперь [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/день)!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странное сосательное ощущение глубоко внутри [npc.her] [npc.crotchBoobs], и разочарованный вздох срывается с [npc.her] [npc.lips], когда [npc.she] осознает, что [npc.sheIs] чувство [npc.her] [npc.crotchMilk] регенерации [style.boldShrink(уменьшается)].<br/>[npc.NamePos] скорость [npc.crotchMilk] регенерации теперь [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(milkRegeneration)+"/день)!</p>");
		}
	}
	
	
	
	// Rows:
	
	public int getRows() {
		return rows;
	}

	public String setRows(GameCharacter owner, int rows) {
		if(this.getShape()==BreastShape.UDDERS) { // Udders can be configured into one single udder (as '0' rows):
			rows = Math.max(0, Math.min(rows, MAXIMUM_BREAST_ROWS));
		} else {
			rows = Math.max(1, Math.min(rows, MAXIMUM_BREAST_ROWS));
		}
		
		if(rows==0 && this.nippleCountPerBreast==1) {
			this.nippleCountPerBreast = 2;
		}
		
		if(!isCharacterInitialised(owner)) {
			this.rows = rows;
			return "";
		}
		
		if(rows == getRows()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Количество рядов груди у [npc.name] не меняется...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		int rowsDifference = Math.abs(rows - getRows());
		
		if (rows < getRows()) {
			sb.append("<p>[npc.Name] [npc.verb(let)] с испуганным вздохом, когда [npc.she] [npc.verb(feel)] странное чувство стягивания прямо над [npc.her] пахом, и прежде чем [npc.sheHasFull] успеет среагировать, [npc.her] ");
			
			if(this.getShape()==BreastShape.UDDERS) {
				sb.append((rowsDifference==1
						?"верхняя пара [npc.crotchNipples]"
						:"верхняя "+Util.intToString(rowsDifference)+" пары [npc.crotchNipples]"));
				sb.append(" стремительно уменьшаются и [style.boldShrink(исчезают)], превращаясь в [npc.her] вымя.<br/>");
			} else {
				sb.append((rowsDifference==1
						?"верхняя пара [npc.crotchBoobs]"
						:"верхняя "+Util.intToString(rowsDifference)+" пары [npc.crotchBoobs]"));
				sb.append(" стремительно уменьшаются и [style.boldShrink(исчезают)], втягиваясь в [npc.skin] [npc.her] живота.<br/>");
			}
			
			
			
		} else if (rows > getRows()) {
			sb.append("<p>[npc.Name] [npc.verb(let)] с удивленным вздохом, когда [npc.she] [npc.verb(feel)] странное чувство распирания прямо над [npc.her] пахом, и прежде чем [npc.sheHasFull] успеет среагировать, ");
			
			if(this.getShape()==BreastShape.UDDERS) {
				sb.append((rowsDifference==1
						?"еще одна пара [npc.crotchNipples]"
						:Util.intToString(rowsDifference)+" еще несколько пар [npc.crotchNipples]"));
				sb.append(" стремительно [style.boldGrow(разрастаются)] сверху, образуя [npc.her] вымени.<br/>");
			} else {
				sb.append((rowsDifference==1
						?"еще одна пара [npc.crotchBoobs]"
						:"еще несколько пар [npc.crotchBoobs]"));
				sb.append(" стремительно [style.boldGrow(разрастаются)] из [npc.skin] [npc.her] живота.<br/>");
			}
		}

		if(this.getShape()==BreastShape.UDDERS) {
			if(rows==0) {
				sb.append("[npc.Name] теперь превращается в [npc.has] [style.boldSex(одиночное вымя)]!</p>");
			} else {
				sb.append("[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(rows) + " пара"+(rows > 1 ? "s" : "")+" вымени)]!</p>");
			}
		} else {
			sb.append("[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(rows) + " пара"+ (rows > 1 ? "s" : "") + " of [npc.crotchBoobs])]!</p>");
		}

		this.rows = rows;

		return UtilText.parse(owner,sb.toString());
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
		if(this.getShape()==BreastShape.UDDERS && this.getRows()==0) {
			nippleCountPerBreast = Math.max(2, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));
		} else {
			nippleCountPerBreast = Math.max(1, Math.min(nippleCountPerBreast, MAXIMUM_NIPPLES_PER_BREAST));
		}
		
		if(!isCharacterInitialised(owner)) {
			this.nippleCountPerBreast = nippleCountPerBreast;
			return "";
		}
		
		if (this.nippleCountPerBreast == nippleCountPerBreast) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Количество [npc.crotchNipples] [npc.nameHasFull] не меняется...)]</p>");
		}
		
		String transformation = "";
		
		if (nippleCountPerBreast < getNippleCountPerBreast()) {
				transformation = UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(feel)] странное покалывание, пробегающее прямо под поверхностью [npc.breastCrotchSkin], покрывающего [npc.her] [npc.crotchBoobs]. Потрясенный вздох вырывается из рта [npc.her], когда сила устремляется вверх в [npc.her] [npc.crotchNipples], и [npc.she] [npc.verb(continue)] [npc.moaning], когда некоторые из них [style.boldShrink(сжимаются)] в плоти [npc.her] [npc.crotchBoobs].<br/>[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.crotchNipples]" : "[npc.crotchNipple]") + " на каждой из [npc.her] [npc.crotchBoobs])]!</p>");
			
			
		} else if (nippleCountPerBreast > getNippleCountPerBreast()) {
				transformation = UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(feel)] странное покалывание, пробегающее прямо под поверхностью [npc.breastCrotchSkin], покрывающей [npc.her] [npc.crotchBoobs]. С [npc.her] рта срывается шокированный вздох, когда сила устремляется вверх в [npc.her] [npc.crotchNipples], и [npc.she] [npc.verb(continue)] [npc.moaning], пока [npc.she] чувствует, как новые [style.boldGrow(растут)] прямо из плоти [npc.her] [npc.crotchBoobs].<br/>[npc.Name] теперь [npc.has] [style.boldSex("+ Util.intToString(nippleCountPerBreast) + " "+ (nippleCountPerBreast > 1 ? "[npc.crotchNipples]" : "[npc.crotchNipple]") + " на каждой из [npc.her] [npc.crotchBoobs])]!</p>");
			
		}
		
		this.nippleCountPerBreast = nippleCountPerBreast;

		return transformation;
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(BreastCrotch.class) && getType().getRace().isFeralPartsAvailable());
	}

}
