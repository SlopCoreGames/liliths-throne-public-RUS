package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTesticleType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Testicle implements BodyPartInterface {

	
	public static final int MIN_TESTICLE_COUNT = 2;
	public static final int MAX_TESTICLE_COUNT = 8;

	public static final int MINIMUM_VALUE_FOR_ALL_CUM_TO_BE_EXPELLED = 5; //ml
	
	
	protected AbstractTesticleType type;
	protected int testicleSize;
	protected int cumStorage;
	protected float cumStored;
	/** Measured in mL/day */
	protected int cumRegeneration;
	protected int testicleCount;
	protected int cumExpulsion;
	protected boolean internal;
	
	protected FluidCum cum;

	public Testicle(AbstractTesticleType type, int testicleSize, int cumStorage, int testicleCount) {
		this.type = type;
		this.testicleSize = Math.max(0, Math.min(testicleSize, TesticleSize.SEVEN_ABSURD.getValue()));
		this.cumStorage = cumStorage;
		cumStored = cumStorage;
		cumRegeneration = FluidRegeneration.CUM_REGEN_DEFAULT;
		cumExpulsion = FluidExpulsion.THREE_LARGE.getMinimumValue();
		
		this.testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		internal = type.isInternal();
		
		cum = new FluidCum(type.getFluidType());
	}

	public Testicle(Testicle testicleToCopy) {
		this.type = testicleToCopy.type;
		this.testicleSize = testicleToCopy.testicleSize;
		this.cumStorage = testicleToCopy.cumStorage;
		this.cumStored = testicleToCopy.cumStored;
		this.cumRegeneration = testicleToCopy.cumRegeneration;
		this.cumExpulsion = testicleToCopy.cumExpulsion;
		
		this.testicleCount = testicleToCopy.testicleCount;
		
		this.internal = testicleToCopy.internal;
		
		this.cum = new FluidCum(testicleToCopy.cum);
	}

	public void setCum(FluidCum cum) {
		this.cum = cum;
	}
	
	public FluidCum getCum() {
		return cum;
	}

	@Override
	public AbstractTesticleType getType() {
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
	public String getDescriptor(GameCharacter owner) {
		List<String> list = new ArrayList<>();
		
		list.add(owner.getTesticleSize().getDescriptor());
		
		list.add(type.getDescriptor(owner));
		
		return Util.randomItemFrom(list);
	}
	
	public void setType(GameCharacter owner, AbstractTesticleType type) {
		this.type = type;
		cum.setType(type.getFluidType());
	}
	
	public TesticleSize getTesticleSize() {
		return TesticleSize.getTesticleSizeFromInt(testicleSize);
	}

	public String setTesticleSize(GameCharacter owner, int testicleSize) {
		if(owner!=null && !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		int oldSize = this.testicleSize;
		this.testicleSize = Math.max(0, Math.min(testicleSize, TesticleSize.SEVEN_ABSURD.getValue()));
		int sizeChange = this.testicleSize - oldSize;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Размер [npc.namePos] [npc.balls] [npc.do] не изменится...)]</p>");
			
		} else if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] похотливый стон, когда [npc.she] [npc.verb(feel)] [npc.her] [npc.balls] внезапно раздуваются и [style.boldGrow(растут)].<br/>[npc.She] теперь [npc.has] [style.boldSex(" + owner.getTesticleSize().getDescriptor() + " [pc.balls])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(let)] вырывается удивленный вздох, когда [npc.she] [npc.verb(feel)] [npc.her] [npc.balls] внезапно [style.boldShrink(уменьшаются)].<br/>[npc.She] теперь имеет [style.boldSex(" +owner.getTesticleSize().getDescriptor()+ " [npc.balls])]!"
					+ "</p>");
		}
	}

	public int getTesticleCount() {
		return testicleCount;
	}

	public String setTesticleCount(GameCharacter owner, int testicleCount) {
		testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		if(!isCharacterInitialised(owner) || owner.getBody()==null) {
			this.testicleCount = testicleCount;
			return "";
		}
		
		if(owner.getTesticleCount() == testicleCount || !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}
		
		boolean removingTesticles = this.testicleCount > testicleCount;
		this.testicleCount = testicleCount;
		
		if(removingTesticles) {
			return UtilText.parse(owner,
					"<p>Покалывающее чувство разливается вниз к [npc.namePos] [npc.balls], и [npc.she] [npc.verb(let)] потрясенный крик, когда [npc.she] [npc.verb(feel)] некоторые из них уменьшаются и [style.boldShrink(исчезают)].<br/>Спустя несколько мгновений, у [npc.sheIs] остались [style.boldTfGeneric([npc.a_balls])].</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>Покалывающее чувство разливается вниз к [npc.namePos] [npc.balls], и [npc.she] [npc.verb(let)] тихий вскрик, когда [npc.she] [npc.verb(feel)] они [style.boldGrow(размножаются)].<br/>Спустя несколько мгновений, у [npc.sheIs] остались [style.boldTfGeneric([npc.a_balls])].</p>");
		}
	}

	public boolean isInternal(GameCharacter owner) {
		if(!Main.game.isFutanariTesticlesEnabled() && owner!=null && owner.hasVagina()) {
			return true;
		}
		return internal;
	}

	public String setInternal(GameCharacter owner, boolean internal) {
		if(!isCharacterInitialised(owner)) {
			this.internal = internal;
			return "";
		}
		
		if(owner.isInternalTesticles() == internal || !owner.hasPenisIgnoreDildo()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Ничего не происходит...)]</p>";
		}

		this.internal = internal;
		
		if(internal) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] [npc.her] [npc.balls] сжимаются и [style.boldShrink(втягиваются)] вверх в пах [npc.her], и [npc.she] [npc.verb(let)] потрясенный крик, когда [npc.she] [npc.verb(feel)] они смещаются вверх, чтобы оказаться внутри тела [npc.her].<br/>[npc.Her] [npc.balls+] [style.boldTfGeneric(теперь находятся внутри)].</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] [npc.her] внутренние [npc.balls] расслабляются и [style.boldGrow(отвисают)], и [npc.she] [npc.verb(let)] глубокий [npc.moan], когда они опускаются во внешнюю мошонку.<br/>[npc.Her] [npc.balls+] [style.boldTfGeneric(теперь снаружи)].</p>");
		}
	}
	
	// CumProduction:

	public CumProduction getCumStorage() {
		return CumProduction.getCumProductionFromInt(cumStorage);
	}

	public int getRawCumStorageValue() {
		return cumStorage;
	}

	/**
	 * Sets the cumStorage. Value is bound to >=0 && <=CumProduction.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 */
	public String setCumStorage(GameCharacter owner, int cumStorage) {
		int oldCumProduction = this.cumStorage;
		this.cumStorage = Math.max(0, Math.min(cumStorage, CumProduction.SEVEN_MONSTROUS.getMaximumValue()));
		int cumChange = this.cumStorage - oldCumProduction;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if(cumChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(Количество [npc.cum], которое [npc.name] [npc.is] способен производить, не меняется...)]</p>");
		}
		
		String cumDescriptor = getCumStorage().getDescriptor();
		if(cumChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странное бурление и копошение глубоко внутри [npc.her] [npc.balls], и [npc.she] не может не издать [npc.a_moan+], когда маленькая струйка предэякулята внезапно вытекает из [npc.her] [npc.cock]; явное свидетельство того, что производство [npc.her] [npc.cum] [style.boldGrow(увеличилось)].<br/>[npc.SheIsFull] теперь способен производить [style.boldSex(\", \"" + cumDescriptor + " [npc.cum])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странное сосащее ощущение глубоко внутри [npc.her] [npc.balls], и [npc.she] не может не ахнуть от шока, когда [npc.she] [npc.verb(realise)], что [npc.sheIs] ощущение [npc.her] [npc.cum] производства [style.boldShrink(иссякает)].<br/>[npc.SheIsFull] теперь способен производить [style.boldSex(\", \"" + cumDescriptor + " [npc.cum])]!"
					+ "</p>");
		}
	}
	
	// Stored cum:

	public CumProduction getStoredCum() {
		return CumProduction.getCumProductionFromInt((int)cumStored);
	}
	
	public float getRawStoredCumValue() {
		return cumStored;
	}

	/**
	 * Sets the cumStorage. Value is bound to >=0 && <=getRawCumStorageValue()
	 */
	public String setStoredCum(GameCharacter owner, float cumStored, boolean withFormatting) {
		float oldStoredCum = this.cumStored;
		this.cumStored = Math.max(0, (Math.min(cumStored, getRawCumStorageValue())));
		float cumChange = oldStoredCum - this.cumStored;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (cumChange <= 0) {
			return "";
		} else {
			StringBuilder storedCumSB = new StringBuilder();
			if(withFormatting) {
				storedCumSB.append("<p style='text-align:center;'>[style.italicsCum(");
			}
			storedCumSB.append(UtilText.returnStringAtRandom(
					Units.fluid(cumChange, Units.UnitType.LONG)+" [npc.cum+] выплескивается из [npc.her] [npc.cock+].",
					Units.fluid(cumChange, Units.UnitType.LONG)+" [npc.cum+] выстреливает из [npc.her] [npc.cock+].",
					Units.fluid(cumChange, Units.UnitType.LONG)+" [npc.cum+] брызжет из [npc.her] [npc.cock+]."));
			if(withFormatting) {
				storedCumSB.append(")]");
			}
			if(this.cumStored==0) {
				storedCumSB.append("<br/><i>[npc.Name] теперь [npc.has] больше нет ни одной [npc.cum], хранящейся в [npc.her] [npc.balls]!</i>");
			}
			if(withFormatting) {
				storedCumSB.append("</p>");
			}
			return UtilText.parse(owner, storedCumSB.toString());
//			return UtilText.parse(owner, "<p style='text-align:center;'><i style='color:"+PresetColour.CUM.toWebHexString()+";'>"
//					+ UtilText.returnStringAtRandom(
//							Units.fluid(cumChange, Units.UnitType.LONG)+" of [npc.cum+] squirts out of [npc.her] [npc.cock+].",
//							Units.fluid(cumChange, Units.UnitType.LONG)+" of [npc.cum+] shoots out of [npc.her] [npc.cock+].",
//							Units.fluid(cumChange, Units.UnitType.LONG)+" of [npc.cum+] spurts out of [npc.her] [npc.cock+].")
//				+ "</i>"
//				+ (this.cumStored==0
//					?"<br/><i>[npc.Name] now [npc.has] no more [npc.cum] stored in [npc.her] [npc.balls]!</i>"
//					:"")
//				+ "</p>");
		}
	}
	public String setStoredCum(GameCharacter owner, float cumStored) {
		return setStoredCum(owner, cumStored, true);
	}

	// Regeneration:

	public FluidRegeneration getCumProductionRegeneration() {
		return FluidRegeneration.getFluidRegenerationFromInt(cumRegeneration);
	}

	public int getRawCumProductionRegenerationValue() {
		return cumRegeneration;
	}

	/**
	 * Sets the cumRegeneration. Value is bound to >=0 && <=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()
	 */
	public String setCumProductionRegeneration(GameCharacter owner, int cumRegeneration) {
		int oldRegeneration = this.cumRegeneration;
		this.cumRegeneration = Math.max(0, Math.min(cumRegeneration, FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay()));
		int regenerationChange = this.cumRegeneration - oldRegeneration;
		
		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (regenerationChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] rate of [npc.cum] regeneration doesn't change...)]</p>");
		}
		
		String regenerationDescriptor = getCumProductionRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] an alarming bubbling and churning taking place deep within [npc.her] [npc.balls], and [npc.she] can't help but let out [npc.a_moan+] as a small squirt of precum suddenly drools out from [npc.her] [npc.cock]; clear evidence that [npc.her] [npc.cum] regeneration has [style.boldGrow(increased)].<br/>[npc.Her] rate of [npc.cum] regeneration is now [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(cumRegeneration)+"/день)!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] [npc.verb(feel)] странное сосущее ощущение глубоко внутри [npc.her] [npc.balls], и [npc.she] не может не издать шокированный вздох, когда [npc.she] [npc.verb(realise)], что [npc.sheIs] чувство [npc.her] [npc.cum] регенерация [style.boldShrink(замедляется)].<br/>[npc.Her] скорость [npc.cum] регенерации теперь [style.boldSex(" + regenerationDescriptor + ")] ("+Units.fluid(cumRegeneration)+"/день)!</p>");
		}
	}

	// Expulsion:

	public FluidExpulsion getCumExpulsion() {
		return FluidExpulsion.getFluidExpulsionFromInt(cumExpulsion);
	}

	public int getRawCumExpulsionValue() {
		return cumExpulsion;
	}

	public String setCumExpulsion(GameCharacter owner, int cumExpulsion) {
		int oldExpulsion = this.cumExpulsion;
		this.cumExpulsion = Math.max(0, Math.min(cumExpulsion, FluidExpulsion.FOUR_HUGE.getMaximumValue()));
		int expulsionChange = this.cumExpulsion - oldExpulsion;

		if(!isCharacterInitialised(owner)) {
			return "";
		}
		
		if (expulsionChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] rate of [npc.cum] expulsion doesn't change...)]</p>");
		}
		
		String expulsionDescriptor = getCumExpulsion().getDescriptor();
		if (expulsionChange > 0) {
			return UtilText.parse(owner,
					"<p>[npc.Name] внезапно [npc.verb(feel)] ощущает странное сжатие и нарастающее давление глубоко внутри [npc.her] [npc.balls], и [npc.she] не может не издать [npc.a_moan+], когда небольшое количество предэякулята мощно вырывается из [npc.her] [npc.cock]; явное свидетельство того, что выброс [npc.her] [npc.cum] [style.boldGrow(усилился)].<br/>[npc.She] теперь извергнет [style.boldSex(" + UtilText.generateSingularDeterminer(expulsionDescriptor) + " "+expulsionDescriptor+")] количества накопленной спермы при каждом оргазме!</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>[npc.Name] внезапно [npc.verb(feel)] странное ослабление и падение давления глубоко внутри [npc.her] [npc.balls], и [npc.she] не может не издать [npc.a_moan+], когда небольшое количество предэякулята слабо вытекает из [npc.her] [npc.cock]; явное свидетельство того, что выброс [npc.her] [npc.cum] [style.boldShrink(уменьшился)].<br/>[npc.She] теперь будет извергать [style.boldSex(" + UtilText.generateSingularDeterminer(expulsionDescriptor) + " "+expulsionDescriptor+")] количества накопленной спермы при каждом оргазме!</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(!isCharacterInitialised(owner)) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Testicle.class) && getType().getRace().isFeralPartsAvailable());
	}
}