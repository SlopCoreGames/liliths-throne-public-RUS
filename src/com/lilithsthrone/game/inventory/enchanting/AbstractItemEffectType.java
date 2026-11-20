package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.EffectBenefit;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.4
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractItemEffectType {
	
	private List<String> effectsDescriptions;
	private Colour colour;
	
	public AbstractItemEffectType(
			List<String> effectsDescriptions,
			Colour colour) {
		this.effectsDescriptions = effectsDescriptions;
		this.colour = colour;
	}
	
	public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		if(effectsDescriptions==null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(effectsDescriptions);
	}
	
	public Colour getColour() {
		return colour;
	}
	
	/**
	 * @return Usually null, but if this ItemEffectType has an associated Race, this is how to access it.
	 */
	public AbstractRace getAssociatedRace() {
		return null;
	}
	
	public abstract String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer);

	public final String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
		StringBuilder sb = new StringBuilder();
		sb.append(itemEffectOverride(primaryModifier, secondaryModifier, potency, limit, user, target, timer));
		
		for(Entry<AbstractStatusEffect, Integer> entry : getAppliedStatusEffects().entrySet()) {
			AbstractStatusEffect se = entry.getKey();
			int time = entry.getValue();
			boolean added = target.addStatusEffect(se, time);
			if(!added) {
				continue;
			}
			String timeDesc = time+" turns";
			if(!se.isCombatEffect()) {
				int timeMinutes = (time/60);
				if(timeMinutes > 3*60) {
					timeDesc = timeMinutes/60+" hours";
				} else {
					timeDesc = timeMinutes+" minutes";
				}
			}
			sb.append(UtilText.parse(target,
					"<p style='text-align:center; padding-top:0; margin-top:0;'>[npc.NameIsFull] сейчас "
					+(se.getBeneficialStatus()==EffectBenefit.DETRIMENTAL?"страдает от [style.italicsBad(":(se.getBeneficialStatus()==EffectBenefit.BENEFICIAL?"получает выгоду от [style.italicsGood(":"подвержен "))
					+se.getName(target)
					+(se.getBeneficialStatus()==EffectBenefit.NEUTRAL?"":")]")
					+ " на "+timeDesc+"!"
					+ "</p>"));
		}
		
		return sb.toString();
	}
	
	public String getPotionDescriptor() {
		return "";
	}
	
	/**
	 * <b>This disables use in sex or combat automatically.</b>
	 * @return true if the use of this item should exit inventory management. i.e. If it's meant to set the content to a specific scene.
	 */
	public boolean isBreakOutOfInventory() {
		return false;
	}
	
	/**
	 * @return A Map of status effects to be applied to the target, mapped to how long that status effect should be applied for, <b>in seconds</b>.
	 */
	public Map<AbstractStatusEffect, Integer> getAppliedStatusEffects() {
		return new HashMap<>();
	}
	
	public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
		return new ArrayList<>();
	}
	
	public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
		return new ArrayList<>();
	}
	
	public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return new ArrayList<>();
	}
	
	public int getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return 0;
	}

	public int getSmallLimitChange(TFModifier primaryModifier, TFModifier secondaryModifier) {
		if (secondaryModifier == TFModifier.TF_MOD_WETNESS
				&& (primaryModifier == TFModifier.TF_BREASTS
						|| primaryModifier == TFModifier.TF_BREASTS_CROTCH
						|| primaryModifier == TFModifier.TF_PENIS)) {
			// Increase small change for fluids
			return 10;
		}
		return 1;
	}

	public int getLargeLimitChange(TFModifier primaryModifier, TFModifier secondaryModifier) {
		if (secondaryModifier == TFModifier.TF_MOD_WETNESS
				&& (primaryModifier == TFModifier.TF_BREASTS
						|| primaryModifier == TFModifier.TF_BREASTS_CROTCH
						|| primaryModifier == TFModifier.TF_PENIS)) {
			// Decrease large change for fluids
			return 500;
		}
		return Math.max(5, getMaximumLimit(primaryModifier, secondaryModifier)/10);
	}

	public int getMaximumLimit(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return getLimits(primaryModifier, secondaryModifier);
	}
	
	public static String getBookEffect(GameCharacter reader, AbstractSubspecies mainSubspecies, List<AbstractSubspecies> additionalUnlockSubspecies, boolean withDescription) {
		List<AbstractSubspecies> subsPlusMain = new ArrayList<>();
		subsPlusMain.add(mainSubspecies);
		if(additionalUnlockSubspecies!=null) {
			subsPlusMain.addAll(additionalUnlockSubspecies);
		}
		
		String descriptionToReturn = "";
		AbstractPerk perk = Perk.getSubspeciesRelatedPerk(mainSubspecies);
		if(!reader.isPlayer() || ((PlayerCharacter) reader).addRaceDiscoveredFromBook(mainSubspecies) || !reader.hasPerkAnywhereInTree(perk)) {
			descriptionToReturn = (withDescription
						?("<p style='text-align:center; font-size:110%;margin-bottom:0;padding-bottom:0;'><b>"+mainSubspecies.getBookName()+"</b></p>"
							+ (mainSubspecies.getBookAuthor().isEmpty()?"":"<p style='text-align:center;margin-top:0;padding-top:0;'><b><i> от "+mainSubspecies.getBookAuthor()+"</i></b></p>")
							+ mainSubspecies.getBasicDescription(null)
							+ mainSubspecies.getAdvancedDescription(null))
						:"")
					+reader.addSpecialPerk(perk);
			
		} else {
			descriptionToReturn = "<p style='text-align:center; font-size:110%;margin-bottom:0;padding-bottom:0;'><b>"+mainSubspecies.getBookName()+"</b></p>"
					+ (mainSubspecies.getBookAuthor().isEmpty()?"":"<p style='text-align:center;margin-top:0;padding-top:0;'><b><i> от "+mainSubspecies.getBookAuthor()+"</i></b></p>")
					+ mainSubspecies.getBasicDescription(null)
					+ mainSubspecies.getAdvancedDescription(null)
					+"<p style='text-align:center; color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>Больше из этой книги ничего не извлечь...</p>";
		}
		
		for(AbstractSubspecies subspecies : subsPlusMain) {
			Main.getProperties().addRaceDiscovered(subspecies);
			if(Main.getProperties().addAdvancedRaceKnowledge(subspecies) && ItemType.getLoreBook(subspecies)!=null) {
				Main.game.addEvent(new EventLogEntryBookAddedToLibrary(ItemType.getLoreBook(subspecies)), true);
			}
		}
		
		return descriptionToReturn;
	}
	
	protected static List<TFModifier> getClothingTFSecondaryModifiers(TFModifier primaryModifier) {
		switch(primaryModifier) {
			case TF_ASS:
				List<TFModifier> assMods = Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// ass size
						TFModifier.TF_MOD_SIZE_SECONDARY,// hip size
						Main.game.isAssHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null);
				if(Main.game.isAnalContentEnabled()) {
					assMods.add(TFModifier.TF_MOD_CAPACITY);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						assMods.add(TFModifier.TF_MOD_DEPTH);
					}
					assMods.add(TFModifier.TF_MOD_ELASTICITY);
					assMods.add(TFModifier.TF_MOD_PLASTICITY);
					assMods.add(TFModifier.TF_MOD_WETNESS);
					assMods.add(TFModifier.TF_MOD_ORIFICE_PUFFY);
					assMods.add(TFModifier.TF_MOD_ORIFICE_RIBBED);
					assMods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED);
					assMods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED);
				}
				return assMods;
			case TF_ARMS:
				return Util.newArrayListOfValues(
						Main.game.isBodyHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null);
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// breast size
						TFModifier.TF_MOD_SIZE_SECONDARY,// nipple size
						TFModifier.TF_MOD_SIZE_TERTIARY,// areolae size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						Main.game.isLactationContentEnabled()
							?TFModifier.TF_MOD_WETNESS
							:null,
						Main.game.isLactationContentEnabled()
							?TFModifier.TF_MOD_REGENERATION
							:null,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED
						);
			case TF_CORE:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// height
						TFModifier.TF_MOD_SIZE_SECONDARY,// muscle mass
						TFModifier.TF_MOD_SIZE_TERTIARY,// body size
						TFModifier.TF_MOD_FEMININITY,
						Main.game.isPubicHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);
			case TF_FACE:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE, // lip size
						TFModifier.TF_MOD_SIZE_SECONDARY, // tongue size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,

						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED,
						
						TFModifier.TF_MOD_TONGUE_RIBBED,
						TFModifier.TF_MOD_TONGUE_TENTACLED,
						TFModifier.TF_MOD_TONGUE_BIFURCATED,
						TFModifier.TF_MOD_TONGUE_WIDE,
						TFModifier.TF_MOD_TONGUE_FLAT,
						TFModifier.TF_MOD_TONGUE_STRONG,
						
						Main.game.isFacialHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);
			case TF_HAIR:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// hair length
						TFModifier.TF_MOD_SIZE_SECONDARY// neck floof
						);
			case TF_PENIS:
				List<TFModifier> penisMods = Util.newArrayListOfValues(
						TFModifier.TF_TYPE_1,
						TFModifier.REMOVAL,
						TFModifier.TF_MOD_SIZE,
						TFModifier.TF_MOD_SIZE_SECONDARY,
						TFModifier.TF_MOD_SIZE_TERTIARY);
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					penisMods.add(TFModifier.TF_MOD_CAPACITY);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						penisMods.add(TFModifier.TF_MOD_DEPTH);
					}
					penisMods.add(TFModifier.TF_MOD_ELASTICITY);
					penisMods.add(TFModifier.TF_MOD_PLASTICITY);
				}
				penisMods.add(TFModifier.TF_MOD_WETNESS);
				penisMods.add(TFModifier.TF_MOD_CUM_EXPULSION);
				penisMods.add(TFModifier.TF_MOD_REGENERATION);
				if(Main.game.isPubicHairEnabled()) {
					penisMods.add(TFModifier.TF_MOD_BODY_HAIR);
				}

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					penisMods.add(TFModifier.TF_MOD_ORIFICE_PUFFY);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_RIBBED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED);
				}
				return penisMods;
				
			case TF_VAGINA:
				List<TFModifier> mods = Util.newArrayListOfValues(
						TFModifier.TF_TYPE_1,
						TFModifier.REMOVAL,
						TFModifier.TF_MOD_SIZE,// clit size
						TFModifier.TF_MOD_SIZE_SECONDARY,// labia size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED,
						Main.game.isPubicHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					mods.add(TFModifier.TF_MOD_CAPACITY_2);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						mods.add(TFModifier.TF_MOD_DEPTH_2);
					}
					mods.add(TFModifier.TF_MOD_ELASTICITY_2);
					mods.add(TFModifier.TF_MOD_PLASTICITY_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_PUFFY_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_RIBBED_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED_2);
				}
				return mods;
			default:
				break;
		}
		return new ArrayList<>();
	}
	
	protected static int getClothingTFLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {

		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				return (int) Capacity.SEVEN_GAPING.getMaximumValue(false);
			case TF_MOD_DEPTH:
				return OrificeDepth.SEVEN_FATHOMLESS.getValue();
			case TF_MOD_ELASTICITY:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_CAPACITY_2:
				return (int) Capacity.SEVEN_GAPING.getMaximumValue(false);
			case TF_MOD_DEPTH_2:
				return OrificeDepth.SEVEN_FATHOMLESS.getValue();
			case TF_MOD_ELASTICITY_2:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY_2:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
					&& primaryModifier!=TFModifier.TF_BREASTS
					&& primaryModifier!=TFModifier.TF_BREASTS_CROTCH) {
					return Wetness.SEVEN_DROOLING.getValue();
				}
				break;
			case TF_MOD_ORIFICE_PUFFY:
			case TF_MOD_ORIFICE_RIBBED:
			case TF_MOD_ORIFICE_MUSCLED:
			case TF_MOD_ORIFICE_TENTACLED:
				return 0;
			default:
				break;
		}
		
		switch(primaryModifier) {
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return AssSize.SEVEN_GIGANTIC.getValue();
					case TF_MOD_SIZE_SECONDARY:
						return HipSize.SEVEN_ABSURDLY_WIDE.getValue();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return CupSize.getMaximumCupSize().getMeasurement();
					case TF_MOD_SIZE_SECONDARY:
						return NippleSize.FOUR_MASSIVE.getValue();
					case TF_MOD_SIZE_TERTIARY:
						return  AreolaeSize.FOUR_MASSIVE.getValue();
					case TF_MOD_WETNESS:
						return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
					case TF_MOD_REGENERATION:
						return FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay();
					default:
						break;
				}
				break;
			case TF_CORE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return Height.SEVEN_COLOSSAL.getMaximumValue() - Height.ZERO_TINY.getMinimumValue();
					case TF_MOD_SIZE_SECONDARY:
						return Muscle.FOUR_RIPPED.getMaximumValue();
					case TF_MOD_SIZE_TERTIARY:
						return BodySize.FOUR_HUGE.getMaximumValue();
					case TF_MOD_FEMININITY:
						return Femininity.FEMININE_STRONG.getMaximumFemininity();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return LipSize.FOUR_HUGE.getValue();
					case TF_MOD_SIZE_SECONDARY:
						return TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return HairLength.SEVEN_TO_FLOOR.getMaximumValue();
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return PenisLength.SEVEN_STALLION.getMaximumValue();
					case TF_MOD_SIZE_SECONDARY:
						return PenetrationGirth.SEVEN_FAT.getValue();
					case TF_MOD_SIZE_TERTIARY:
						return TesticleSize.SEVEN_ABSURD.getValue();
					case TF_MOD_WETNESS:
						return CumProduction.SEVEN_MONSTROUS.getMaximumValue();
					case TF_MOD_CUM_EXPULSION:
						return FluidExpulsion.FOUR_HUGE.getMaximumValue();
					case TF_MOD_REGENERATION:
						return FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return ClitorisSize.SEVEN_STALLION.getMaximumValue();
					case TF_MOD_SIZE_SECONDARY:
						return LabiaSize.FOUR_MASSIVE.getValue();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			default:
				break;
		}
		return 0;
	}
	
	protected static List<String> getClothingTFDescriptions(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		List<String> descriptions = new ArrayList<>();
		
		String orificeName = "";

		if(primaryModifier==TFModifier.TF_PENIS) {
			orificeName = "urethra";
		} else if(primaryModifier==TFModifier.TF_VAGINA) {
			orificeName = "vagina";
		} else if(primaryModifier==TFModifier.TF_BREASTS) {
			orificeName = "nipple";
		} else if(primaryModifier==TFModifier.TF_BREASTS_CROTCH) {
			orificeName = "теат (промежность)";
		} else if(primaryModifier==TFModifier.TF_ASS) {
			orificeName = "anus";
		} else if(primaryModifier==TFModifier.TF_FACE) {
			orificeName = "throat";
		}
		
		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" capacity", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
				break;
			case TF_MOD_DEPTH:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" depth", OrificeDepth.getDepthFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_ELASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" elasticity", OrificeElasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_PLASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" plasticity", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_CAPACITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"вместимость уретры", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
				break;
			case TF_MOD_DEPTH_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"глубина уретры", OrificeDepth.getDepthFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_ELASTICITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"эластичность уретры", OrificeElasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_PLASTICITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"пластичность уретры", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
						&& primaryModifier!=TFModifier.TF_BREASTS
						&& primaryModifier!=TFModifier.TF_BREASTS_CROTCH) {
					descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+" wetness", Wetness.valueOf(limit).getDescriptor()));
				}
				break;
			default:
				break;
		}
		
		switch(primaryModifier) {
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер задницы", AssSize.getAssSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер бедер", HipSize.getHipSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "пухлость ануса", "размер ануса"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "рельеф ануса",  "анальные складки"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "мускулистость ануса", "дополнительные мышцы ануса"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "анус с щупальцами", "анальные щупальца"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "волосатость задницы", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "волосатость подмышек", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						CupSize cupSize = CupSize.getCupSizeFromInt(limit);
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер груди", cupSize.getCupSizeName()+(cupSize==CupSize.FLAT?"":"-cup")));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер сосков", NippleSize.getNippleSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер ареол", AreolaeSize.getAreolaeSizeFromInt(limit).getName()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "lactation", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "регенерация молока", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)+" в день"));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "вздутые соски", "вздутость сосков"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "соски с внутренними ребрами",  "рельеф сосков"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "соски с внутренними мышцами", "внутренние мышцы сосков"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "соски с внутренними щупальцами", "щупальца сосков"));
						break;
					default:
						break;
				}
				break;
			case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер чашечки (промежность)", CupSize.getCupSizeFromInt(limit).getCupSizeName()+"-cup"));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер сосков (промежность)", NippleSize.getNippleSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер ареол (промежность)", AreolaeSize.getAreolaeSizeFromInt(limit).getName()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "лактация (промежность)", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "регенерация молока (промежность)", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)+" в день"));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "вздутые соски (промежность)", "вздутость сосков"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "ребристые соски внутри (промежность)",  "рельеф сосков"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "мускулистые соски внутри (промежность)", "внутренние мышцы сосков"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "щупальцевидные соски внутри (промежность)", "щупальца сосков"));
						break;
					default:
						break;
				}
				break;
			case TF_CORE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "height", Units.size(Height.ZERO_TINY.getMinimumValue() + limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер мышц", String.valueOf(limit)));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер тела", String.valueOf(limit)));
						break;
					case TF_MOD_FEMININITY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "femininity", String.valueOf(limit)));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "волосатость в паху", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер губ", LipSize.getLipSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "длина языка", Units.size(limit)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "припухшие губы", "пухлые губы"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "внутренняя ребристость горла",  "ребристость горла"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "внутренняя мускулистость горла", "дополнительные мышцы горла"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "внутренние щупальца в горле", "щупальца в горле"));
						break;
					case TF_MOD_TONGUE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "ребристый язык",  "ребристость языка"));
						break;
					case TF_MOD_TONGUE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "язык с щупальцами", "щупальца на языке"));
						break;
					case TF_MOD_TONGUE_BIFURCATED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "раздвоенный язык", "раздвоение языка"));
						break;
					case TF_MOD_TONGUE_WIDE:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "широкий язык", "расширение языка"));
						break;
					case TF_MOD_TONGUE_FLAT:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "плоский язык", "уплощение языка"));
						break;
					case TF_MOD_TONGUE_STRONG:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "сильный язык", "укрепление языка"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "длина бороды", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "длина волос", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("Через неделю вырастает пушок на шее.");
								break;
							case BOOST:
								descriptions.add("Через день вырастает пушок на шее.");
								break;
							case MAJOR_BOOST:
								descriptions.add("Через час вырастает пушок на шее.");
								break;
							case MINOR_DRAIN:
								descriptions.add("Через неделю убирает пушок на шее.");
								break;
							case DRAIN:
								descriptions.add("Через день убирает пушок на шее.");
								break;
							case MAJOR_DRAIN:
								descriptions.add("Через час убирает пушок на шее.");
								break;
							default:
								break;
						}
						break;
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("Через неделю вырастет член.");
								break;
							case BOOST:
								descriptions.add("Через день вырастет член.");
								break;
							case MAJOR_BOOST:
								descriptions.add("Через час вырастет член.");
								break;
							default:
								break;
						}
						break;
					case REMOVAL:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("Через неделю исчезнет член.");
								break;
							case BOOST:
								descriptions.add("Через день исчезнет член.");
								break;
							case MAJOR_BOOST:
								descriptions.add("Через час исчезнет член.");
								break;
							default:
								break;
						}
						break;
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "длина члена", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "обхват члена", PenetrationGirth.getGirthFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер яичек", TesticleSize.getTesticleSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "объем спермы", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_CUM_EXPULSION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "извержение спермы", limit+"%"));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "регенерация спермы", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)+" в день"));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "опухшая уретра", "припухшая уретра"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "уретра с внутренними ребрами",  "ребристость уретры"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "уретра с внутренними мышцами", "мышцы уретры"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "уретра с внутренними щупальцами", "щупальца уретры"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "волосатость в паху", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("Через неделю вырастет влагалище.");
								break;
							case BOOST:
								descriptions.add("Через день вырастет влагалище.");
								break;
							case MAJOR_BOOST:
								descriptions.add("Через час вырастет влагалище.");
								break;
							default:
								break;
						}
						break;
					case REMOVAL:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("Через неделю исчезнет влагалище.");
								break;
							case BOOST:
								descriptions.add("Через день исчезнет влагалище.");
								break;
							case MAJOR_BOOST:
								descriptions.add("Через час исчезнет влагалище.");
								break;
							default:
								break;
						}
						break;
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер клитора", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "размер половых губ", LabiaSize.getLabiaSizeFromInt(limit).getName()));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "вздутое влагалище", "пухлое влагалище"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "внутренние ребра влагалища",  "рельеф влагалища"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "внутренние мышцы влагалища", "дополнительные мышцы влагалища"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "внутренние щупальца влагалища", "щупальца во влагалище"));
						break;
					case TF_MOD_ORIFICE_PUFFY_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "опухшая уретра", "припухшая уретра"));
						break;
					case TF_MOD_ORIFICE_RIBBED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "уретра с внутренними ребрами",  "ребристость уретры"));
						break;
					case TF_MOD_ORIFICE_MUSCLED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "уретра с внутренними мышцами", "мышцы уретры"));
						break;
					case TF_MOD_ORIFICE_TENTACLED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "уретра с внутренними щупальцами", "щупальца уретры"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "волосатость в паху", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
		
		return descriptions;
	}
	
	private static String getClothingOrificeTFChangeDescriptionEntry(TFPotency potency, String changeAdd, String changeRemove) {
		switch(potency) {
			case MINOR_BOOST:
				return ("Через неделю дает "+changeAdd+".");
			case BOOST:
				return ("Через день дает "+changeAdd+".");
			case SPECIAL:
			case MAJOR_BOOST:
				return ("Через час дает "+changeAdd+".");
			case MINOR_DRAIN:
				return ("Через неделю убирает "+changeRemove+".");
			case DRAIN:
				return ("В день снимает "+changeRemove+".");
			case MAJOR_DRAIN:
				return ("В час снимает "+changeRemove+".");
		}
		return "";
	}
	
	private static String getClothingTFChangeDescriptionEntry(TFPotency potency, String subject, String limit) {
		switch(potency) {
			case MINOR_BOOST:
				return ("Еженедельно "+subject+" увеличивает. (Лимит: "+limit+")");
			case BOOST:
				return ("Ежедневно "+subject+" увеличивает. (Лимит: "+limit+")");
			case SPECIAL:
			case MAJOR_BOOST:
				return ("Ежечасно "+subject+" увеличивает. (Лимит: "+limit+")");
			case MINOR_DRAIN:
				return ("Еженедельно "+subject+" уменьшает. (Лимит: "+limit+")");
			case DRAIN:
				return ("Ежедневно "+subject+" уменьшает. (Лимит: "+limit+")");
			case MAJOR_DRAIN:
				return ("Ежечасно "+subject+" уменьшает. (Лимит: "+limit+")");
		}
		return "";
	}
	
	protected static String applyClothingTF(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
		int depthIncrement = (potency.isNegative()?-1:1);
		int capacityIncrement = (potency.isNegative()?-2:2);
		int elasticityIncrement = (potency.isNegative()?-1:1);
		int plasticityIncrement = (potency.isNegative()?-1:1);
		int wetnessIncrement = (potency.isNegative()?-1:1);
		int assSizeIncrement = (potency.isNegative()?-1:1);
		int hipSizeIncrement = (potency.isNegative()?-1:1);

		int breastSizeIncrement = (potency.isNegative()?-1:1);
		int nippleSizeIncrement = (potency.isNegative()?-1:1);
		int areolaeSizeIncrement = (potency.isNegative()?-1:1);
		int lactationIncrement = (potency.isNegative()?-50:50);

		int fluidRegenerationIncrement = (potency.isNegative()?-250:250);
		
		int heightIncrement = (potency.isNegative()?-1:1);
		int muscleIncrement = (potency.isNegative()?-1:1);
		int bodySizeIncrement = (potency.isNegative()?-1:1);
		int femininityIncrement = (potency.isNegative()?-1:1);

		int lipSizeIncrement = (potency.isNegative()?-1:1);

		int hairLengthIncrement = (potency.isNegative()?-1:1);

		int penisSizeIncrement = (potency.isNegative()?-1:1);
		int testicleSizeIncrement = (potency.isNegative()?-1:1);
		int cumStorageIncrement = (potency.isNegative()?-5:5);
		int cumExpulsionIncrement = (potency.isNegative()?-5:5);

		int clitorisSizeIncrement = (potency.isNegative()?-1:1);
		int labiaSizeIncrement = (potency.isNegative()?-1:1);

		int bodyHairIncrement = (potency.isNegative()?-1:1);
		
		int TFCount = 0;
		int secondsRequired = 60*60;
		
		switch(potency) {
			case MINOR_BOOST:
				secondsRequired = 7 * 24 * 60 * 60;
				break;
			case BOOST:
				secondsRequired = 24 * 60 * 60;
				break;
			case SPECIAL:
			case MAJOR_BOOST:
				secondsRequired = 60 * 60;
				break;
			case MINOR_DRAIN:
				secondsRequired = 7 * 24 * 60 * 60;
				break;
			case DRAIN:
				secondsRequired = 24 * 60 * 60;
				break;
			case MAJOR_DRAIN:
				secondsRequired = 60 * 60;
				break;
		}
		
		TFCount = timer.getSecondsPassed()/secondsRequired;
		if(TFCount>=1) {
			timer.setSecondsPassed(timer.getSecondsPassed()%secondsRequired);
		}
//		System.out.println(timer.getTimePassed() + ", " + minutesRequired + ": " +TFCount);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<TFCount; i++) {
			switch(primaryModifier) {
				case TF_ASS:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(assSizeIncrement, target.getAssSize().getValue(), limit)) {
								sb.append(target.incrementAssSize(assSizeIncrement));
							} else if(isSetToLimit(assSizeIncrement, target.getAssSize().getValue(), limit)) {
								sb.append(target.setAssSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(hipSizeIncrement, target.getHipSize().getValue(), limit)) {
								sb.append(target.incrementHipSize(assSizeIncrement));
							} else if(isSetToLimit(hipSizeIncrement, target.getHipSize().getValue(), limit)) {
								sb.append(target.setHipSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getAssRawCapacityValue(), limit)) {
								sb.append(target.incrementAssCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getAssRawCapacityValue(), limit)) {
								sb.append(target.setAssCapacity(limit, true));
							}
							break;
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getAssDepth().getValue(), limit)) {
								sb.append(target.incrementAssDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getAssDepth().getValue(), limit)) {
								sb.append(target.setAssDepth(limit));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getAssElasticity().getValue(), limit)) {
								sb.append(target.incrementAssElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getAssElasticity().getValue(), limit)) {
								sb.append(target.setAssElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getAssPlasticity().getValue(), limit)) {
								sb.append(target.incrementAssPlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getAssPlasticity().getValue(), limit)) {
								sb.append(target.setAssPlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getAssWetness().getValue(), limit)) {
								sb.append(target.incrementAssWetness(wetnessIncrement));
							} else if(isSetToLimit(wetnessIncrement, target.getAssWetness().getValue(), limit)) {
								sb.append(target.setAssWetness(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.TENTACLED));
								}
							}
							break;
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getAssHair().getValue(), limit)) {
								sb.append(target.incrementAssHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getAssHair().getValue(), limit)) {
								sb.append(target.setAssHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_ARMS:
					switch(secondaryModifier) {
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getUnderarmHair().getValue(), limit)) {
								sb.append(target.incrementUnderarmHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getUnderarmHair().getValue(), limit)) {
								sb.append(target.setUnderarmHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_BREASTS:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(breastSizeIncrement, target.getBreastRawSizeValue(), limit)) {
								sb.append(target.incrementBreastSize(breastSizeIncrement));
							} else if(isSetToLimit(breastSizeIncrement, target.getBreastRawSizeValue(), limit)) {
								sb.append(target.setBreastSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(nippleSizeIncrement, target.getNippleSize().getValue(), limit)) {
								sb.append(target.incrementNippleSize(nippleSizeIncrement));
							} else if(isSetToLimit(nippleSizeIncrement, target.getNippleSize().getValue(), limit)) {
								sb.append(target.incrementNippleSize(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(areolaeSizeIncrement, target.getAreolaeSize().getValue(), limit)) {
								sb.append(target.incrementAreolaeSize(areolaeSizeIncrement));
							} else if(isSetToLimit(areolaeSizeIncrement, target.getAreolaeSize().getValue(), limit)) {
								sb.append(target.incrementAreolaeSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getNippleRawCapacityValue(), limit)) {
								sb.append(target.incrementNippleCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getNippleRawCapacityValue(), limit)) {
								sb.append(target.setNippleCapacity(limit, true));
							}
							break;
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getNippleDepth().getValue(), limit)) {
								sb.append(target.incrementNippleDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getNippleDepth().getValue(), limit)) {
								sb.append(target.setNippleDepth(limit));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getNippleElasticity().getValue(), limit)) {
								sb.append(target.incrementNippleElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getNippleElasticity().getValue(), limit)) {
								sb.append(target.setNippleElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getNipplePlasticity().getValue(), limit)) {
								sb.append(target.incrementNipplePlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getNipplePlasticity().getValue(), limit)) {
								sb.append(target.setNipplePlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(lactationIncrement, target.getBreastRawMilkStorageValue(), limit)) {
								sb.append(target.incrementBreastMilkStorage(lactationIncrement));
							} else if(isSetToLimit(lactationIncrement, target.getBreastRawMilkStorageValue(), limit)) {
								sb.append(target.setBreastMilkStorage(limit));
							}
							break;
						case TF_MOD_REGENERATION:
							if(isWithinLimits(fluidRegenerationIncrement, target.getBreastRawLactationRegenerationValue(), limit)) {
								sb.append(target.incrementBreastLactationRegeneration(fluidRegenerationIncrement));
							} else if(isSetToLimit(fluidRegenerationIncrement, target.getBreastRawLactationRegenerationValue(), limit)) {
								sb.append(target.setBreastLactationRegeneration(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.TENTACLED));
								}
							}
							break;
						default:
							break;
					}
					break;
				case TF_BREASTS_CROTCH:
					if(target.hasBreastsCrotch()) {
						switch(secondaryModifier) {
							case TF_MOD_SIZE:
								if(isWithinLimits(breastSizeIncrement, target.getBreastCrotchRawSizeValue(), limit)) {
									sb.append(target.incrementBreastCrotchSize(breastSizeIncrement));
								} else if(isSetToLimit(breastSizeIncrement, target.getBreastCrotchRawSizeValue(), limit)) {
									sb.append(target.setBreastCrotchSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(nippleSizeIncrement, target.getNippleCrotchSize().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchSize(nippleSizeIncrement));
								} else if(isSetToLimit(nippleSizeIncrement, target.getNippleCrotchSize().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchSize(limit));
								}
								break;
							case TF_MOD_SIZE_TERTIARY:
								if(isWithinLimits(areolaeSizeIncrement, target.getAreolaeCrotchSize().getValue(), limit)) {
									sb.append(target.incrementAreolaeCrotchSize(areolaeSizeIncrement));
								} else if(isSetToLimit(areolaeSizeIncrement, target.getAreolaeCrotchSize().getValue(), limit)) {
									sb.append(target.incrementAreolaeCrotchSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getNippleCrotchRawCapacityValue(), limit)) {
									sb.append(target.incrementNippleCrotchCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getNippleCrotchRawCapacityValue(), limit)) {
									sb.append(target.setNippleCrotchCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getNippleCrotchDepth().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getNippleCrotchDepth().getValue(), limit)) {
									sb.append(target.setNippleCrotchDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getNippleCrotchElasticity().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getNippleCrotchElasticity().getValue(), limit)) {
									sb.append(target.setNippleCrotchElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getNippleCrotchPlasticity().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getNippleCrotchPlasticity().getValue(), limit)) {
									sb.append(target.setNippleCrotchPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(lactationIncrement, target.getBreastCrotchRawMilkStorageValue(), limit)) {
									sb.append(target.incrementBreastCrotchMilkStorage(lactationIncrement));
								} else if(isSetToLimit(lactationIncrement, target.getBreastCrotchRawMilkStorageValue(), limit)) {
									sb.append(target.setBreastCrotchMilkStorage(limit));
								}
								break;
							case TF_MOD_REGENERATION:
								if(isWithinLimits(fluidRegenerationIncrement, target.getBreastCrotchRawLactationRegenerationValue(), limit)) {
									sb.append(target.incrementBreastCrotchLactationRegeneration(fluidRegenerationIncrement));
								} else if(isSetToLimit(fluidRegenerationIncrement, target.getBreastCrotchRawLactationRegenerationValue(), limit)) {
									sb.append(target.setBreastCrotchLactationRegeneration(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							default:
								break;
						}
					}
					break;
				case TF_CORE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.incrementHeight(heightIncrement, false));
							} else if(isSetToLimit(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.setHeight(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(muscleIncrement, target.getMuscleValue(), limit)) {
								sb.append(target.incrementMuscle(muscleIncrement));
							} else if(isSetToLimit(muscleIncrement, target.getMuscleValue(), limit)) {
								sb.append(target.setMuscle(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(bodySizeIncrement, target.getBodySizeValue(), limit)) {
								sb.append(target.incrementBodySize(bodySizeIncrement));
							} else if(isSetToLimit(bodySizeIncrement, target.getBodySizeValue(), limit)) {
								sb.append(target.setBodySize(limit));
							}
							break;
						case TF_MOD_FEMININITY:
							if(isWithinLimits(femininityIncrement, target.getFemininityValue(), limit)) {
								sb.append(target.incrementFemininity(femininityIncrement));
							} else if(isSetToLimit(femininityIncrement, target.getFemininityValue(), limit)) {
								sb.append(target.setFemininity(limit));
							}
							break;
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
								sb.append(target.incrementPubicHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
								sb.append(target.setPubicHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_FACE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(lipSizeIncrement, target.getLipSizeValue(), limit)) {
								sb.append(target.incrementLipSize(lipSizeIncrement));
							} else if(isSetToLimit(lipSizeIncrement, target.getLipSizeValue(), limit)) {
								sb.append(target.setLipSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(lipSizeIncrement, target.getTongueLengthValue(), limit)) {
								sb.append(target.incrementTongueLength(lipSizeIncrement));
							} else if(isSetToLimit(lipSizeIncrement, target.getTongueLengthValue(), limit)) {
								sb.append(target.setTongueLength(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getFaceRawCapacityValue(), limit)) {
								sb.append(target.incrementFaceCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getFaceRawCapacityValue(), limit)) {
								sb.append(target.setFaceCapacity(limit, true));
							}
							break;
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getFaceDepth().getValue(), limit)) {
								sb.append(target.incrementFaceDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getFaceDepth().getValue(), limit)) {
								sb.append(target.setFaceDepth(limit));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getFaceElasticity().getValue(), limit)) {
								sb.append(target.incrementFaceElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getFaceElasticity().getValue(), limit)) {
								sb.append(target.setFaceElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getFacePlasticity().getValue(), limit)) {
								sb.append(target.incrementFacePlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getFacePlasticity().getValue(), limit)) {
								sb.append(target.setFacePlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getFaceWetness().getValue(), limit)) {
								sb.append(target.incrementFaceWetness(wetnessIncrement));
							} else if(isSetToLimit(wetnessIncrement, target.getFaceWetness().getValue(), limit)) {
								sb.append(target.setFaceWetness(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.TENTACLED));
								}
							}
							break;
						case TF_MOD_TONGUE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.RIBBED)) {
									sb.append(target.addTongueModifier(TongueModifier.RIBBED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.RIBBED)) {
									sb.append(target.removeTongueModifier(TongueModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_TONGUE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.TENTACLED)) {
									sb.append(target.addTongueModifier(TongueModifier.TENTACLED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.TENTACLED)) {
									sb.append(target.removeTongueModifier(TongueModifier.TENTACLED));
								}
							}
							break;
						case TF_MOD_TONGUE_BIFURCATED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.BIFURCATED)) {
									sb.append(target.addTongueModifier(TongueModifier.BIFURCATED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.BIFURCATED)) {
									sb.append(target.removeTongueModifier(TongueModifier.BIFURCATED));
								}
							}
							break;
						case TF_MOD_TONGUE_WIDE:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.WIDE)) {
									sb.append(target.addTongueModifier(TongueModifier.WIDE));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.WIDE)) {
									sb.append(target.removeTongueModifier(TongueModifier.WIDE));
								}
							}
							break;
						case TF_MOD_TONGUE_FLAT:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.FLAT)) {
									sb.append(target.addTongueModifier(TongueModifier.FLAT));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.FLAT)) {
									sb.append(target.removeTongueModifier(TongueModifier.FLAT));
								}
							}
							break;
						case TF_MOD_TONGUE_STRONG:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.STRONG)) {
									sb.append(target.addTongueModifier(TongueModifier.STRONG));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.STRONG)) {
									sb.append(target.removeTongueModifier(TongueModifier.STRONG));
								}
							}
							break;
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getFacialHair().getValue(), limit)) {
								sb.append(target.incrementFacialHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getFacialHair().getValue(), limit)) {
								sb.append(target.setFacialHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_HAIR:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(hairLengthIncrement, target.getHairRawLengthValue(), limit)) {
								sb.append(target.incrementHairLength(hairLengthIncrement));
							} else if(isSetToLimit(hairLengthIncrement, target.getHairRawLengthValue(), limit)) {
								sb.append(target.setHairLength(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(potency.isNegative()) {
								if(target.isNeckFluff()) {
									sb.append(target.setNeckFluff(false));
								}
							} else {
								if(!target.isNeckFluff()) {
									sb.append(target.setNeckFluff(true));
								}
							}
							break;
						default:
							break;
					}
					break;
				case TF_PENIS:
					if(target.hasPenisIgnoreDildo()) {
						switch(secondaryModifier) {
							case REMOVAL:
								sb.append(target.setPenisType(PenisType.NONE));
								break;
							case TF_MOD_SIZE:
								if(isWithinLimits(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.incrementPenisSize(penisSizeIncrement));
								} else if(isSetToLimit(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.setPenisSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(penisSizeIncrement, target.getPenisGirth().getValue(), limit)) {
									sb.append(target.incrementPenisGirth(penisSizeIncrement));
								} else if(isSetToLimit(penisSizeIncrement, target.getPenisGirth().getValue(), limit)) {
									sb.append(target.setPenisGirth(limit));
								}
								break;
							case TF_MOD_SIZE_TERTIARY:
								if(isWithinLimits(testicleSizeIncrement, target.getTesticleSize().getValue(), limit)) {
									sb.append(target.incrementTesticleSize(testicleSizeIncrement));
								} else if(isSetToLimit(testicleSizeIncrement, target.getTesticleSize().getValue(), limit)) {
									sb.append(target.setTesticleSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getPenisRawCapacityValue(), limit)) {
									sb.append(target.incrementPenisCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getPenisRawCapacityValue(), limit)) {
									sb.append(target.setPenisCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getUrethraDepth().getValue(), limit)) {
									sb.append(target.incrementUrethraDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getUrethraDepth().getValue(), limit)) {
									sb.append(target.setUrethraDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getUrethraElasticity().getValue(), limit)) {
									sb.append(target.incrementUrethraElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getUrethraElasticity().getValue(), limit)) {
									sb.append(target.setUrethraElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getUrethraPlasticity().getValue(), limit)) {
									sb.append(target.incrementUrethraPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getUrethraPlasticity().getValue(), limit)) {
									sb.append(target.setUrethraPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(cumStorageIncrement, target.getPenisRawCumStorageValue(), limit)) {
									sb.append(target.incrementPenisCumStorage(cumStorageIncrement));
								} else if(isSetToLimit(cumStorageIncrement, target.getPenisRawCumStorageValue(), limit)) {
									sb.append(target.setPenisCumStorage(limit));
								}
								break;
							case TF_MOD_REGENERATION:
								if(isWithinLimits(fluidRegenerationIncrement, target.getPenisRawCumProductionRegenerationValue(), limit)) {
									sb.append(target.incrementPenisCumProductionRegeneration(fluidRegenerationIncrement));
								} else if(isSetToLimit(fluidRegenerationIncrement, target.getPenisRawCumProductionRegenerationValue(), limit)) {
									sb.append(target.setPenisCumProductionRegeneration(limit));
								}
								break;
							case TF_MOD_CUM_EXPULSION:
								if(isWithinLimits(cumExpulsionIncrement, target.getPenisRawCumExpulsionValue(), limit)) {
									sb.append(target.incrementPenisCumExpulsion(cumExpulsionIncrement));
								} else if(isSetToLimit(cumExpulsionIncrement, target.getPenisRawCumExpulsionValue(), limit)) {
									sb.append(target.setPenisCumExpulsion(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							case TF_MOD_BODY_HAIR:
								if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.incrementPubicHair(bodyHairIncrement));
								} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.setPubicHair(limit));
								}
								break;
							default:
								break;
						}
						
					} else { // Do not have penis:
						switch(secondaryModifier) {
							case TF_TYPE_1:
								if(target.getSubspeciesOverride()!=null) {
									sb.append(target.setPenisType(PenisType.getPenisTypes(target.getSubspeciesOverrideRace()).get(0)));
								} else {
									sb.append(target.setPenisType(PenisType.getPenisTypes(target.getRace()).get(0)));
								}
								break;
							default:
								break;
						}
					}
					break;
				case TF_VAGINA:
					if(target.hasVagina()) {
						switch(secondaryModifier) {
							case REMOVAL:
								sb.append(target.setVaginaType(VaginaType.NONE));
								break;
							case TF_MOD_SIZE:
								if(isWithinLimits(clitorisSizeIncrement, target.getVaginaRawClitorisSizeValue(), limit)) {
									sb.append(target.incrementVaginaClitorisSize(clitorisSizeIncrement));
								} else if(isSetToLimit(clitorisSizeIncrement, target.getVaginaRawClitorisSizeValue(), limit)) {
									sb.append(target.setVaginaClitorisSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(labiaSizeIncrement, target.getVaginaRawLabiaSizeValue(), limit)) {
									sb.append(target.incrementVaginaLabiaSize(labiaSizeIncrement));
								} else if(isSetToLimit(labiaSizeIncrement, target.getVaginaRawLabiaSizeValue(), limit)) {
									sb.append(target.setVaginaLabiaSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getVaginaRawCapacityValue(), limit)) {
									sb.append(target.incrementVaginaCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getVaginaRawCapacityValue(), limit)) {
									sb.append(target.setVaginaCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getVaginaDepth().getValue(), limit)) {
									sb.append(target.incrementVaginaDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getVaginaDepth().getValue(), limit)) {
									sb.append(target.setVaginaDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getVaginaElasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getVaginaElasticity().getValue(), limit)) {
									sb.append(target.setVaginaElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getVaginaPlasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getVaginaPlasticity().getValue(), limit)) {
									sb.append(target.setVaginaPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(wetnessIncrement, target.getVaginaWetness().getValue(), limit)) {
									sb.append(target.incrementVaginaWetness(wetnessIncrement));
								} else if(isSetToLimit(wetnessIncrement, target.getVaginaWetness().getValue(), limit)) {
									sb.append(target.setVaginaWetness(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							case TF_MOD_CAPACITY_2:
								if(isWithinLimits(capacityIncrement, target.getVaginaUrethraRawCapacityValue(), limit)) {
									sb.append(target.incrementVaginaUrethraCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getVaginaUrethraRawCapacityValue(), limit)) {
									sb.append(target.setVaginaUrethraCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH_2:
								if(isWithinLimits(depthIncrement, target.getVaginaUrethraDepth().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getVaginaUrethraDepth().getValue(), limit)) {
									sb.append(target.setVaginaUrethraDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY_2:
								if(isWithinLimits(elasticityIncrement, target.getVaginaUrethraElasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getVaginaUrethraElasticity().getValue(), limit)) {
									sb.append(target.setVaginaUrethraElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY_2:
								if(isWithinLimits(plasticityIncrement, target.getVaginaUrethraPlasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getVaginaUrethraPlasticity().getValue(), limit)) {
									sb.append(target.setVaginaUrethraPlasticity(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							case TF_MOD_BODY_HAIR:
								if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.incrementPubicHair(bodyHairIncrement));
								} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.setPubicHair(limit));
								}
								break;
							default:
								break;
						}
						
					} else { // Does not have vagina:
						switch(secondaryModifier) {
							case TF_TYPE_1:
								if(target.getSubspeciesOverride()!=null) {
									sb.append(target.setVaginaType(VaginaType.getVaginaTypes(target.getSubspeciesOverrideRace()).get(0)));
								} else {
									sb.append(target.setVaginaType(VaginaType.getVaginaTypes(target.getRace()).get(0)));
								}
								break;
							default:
								break;
						}
					}
					break;
				default:
					break;
			}
		}
		
		return sb.toString();
	}
	
	private static boolean isWithinLimits(int increment, float currentValue, int limit) {
		if(increment<0) {
			if(increment + currentValue < limit) {
				return false;
			}
		} else if(increment + currentValue > limit) {
			return false;
		}
		return true;
	}
	
	private static boolean isSetToLimit(int increment, float currentValue, int limit) {
		if(increment<0) {
			if(currentValue <= limit) {
				return false;
			}
		} else if(currentValue >= limit) {
			return false;
		}
		return true;
	}
	
	protected static List<String> descriptions = new ArrayList<>();
	protected static List<String> genericAttributeEffectDescription(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
		descriptions.clear();
		
		switch(secondaryModifier) {
			default:
				switch(potency) {
					case MAJOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-15, "b")+" к «эффектам зелий»");
							}
						}
						break;
					case DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-10, "b")+" к «эффектам зелий»");
							}
						}
						break;
					case MINOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-5, "b")+" к «эффектам зелий»");
							}
						}
						break;
					case MINOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(5, "b")+" к «эффектам зелий»");
							}
						}
						break;
					case BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(10, "b")+" к «эффектам зелий»");
							}
						}
						break;
					case SPECIAL:
					case MAJOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(15, "b")+" к «эффектам зелий»");
							}
						}
						break;
				}
				break;
		}
		
		return descriptions;
	}
	
	private static void addResourceDescriptionsRestore(int value, ResourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldGood(Восстанавливает)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
				break;
			case MANA:
					descriptions.add("[style.boldGood(Восстанавливает)] "+value+"% [style.boldAura(ауры)]");
				break;
			case ALL:
					descriptions.add("[style.boldGood(Восстанавливает)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
					descriptions.add("[style.boldGood(Восстанавливает)] "+value+"% [style.boldAura(ауры)]");
				break;
		}
	}
	private static void addResourceDescriptionsDrain(int value, ResourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldBad(Вытягивает)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
				break;
			case MANA:
					descriptions.add("[style.boldBad(Вытягивает)] "+value+"% [style.boldAura(ауры)]");
				break;
			case ALL:
					descriptions.add("[style.boldBad(Вытягивает)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
					descriptions.add("[style.boldBad(Вытягивает)] "+value+"% [style.boldAura(ауры)]");
				break;
		}
	}
	
	private static String applyRestoration(GameCharacter target, ResourceRestoration restorationType, float percentage) {
		switch(restorationType) {
			case ALL:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*percentage);
				break;
			case HEALTH:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				break;
			case MANA:
				target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*percentage);
				break;
		}
		
		if(percentage > 0) {
			if(target.isPlayer()) {
				return "Успокаивающее тепло разливается по всему телу, и с глубоким удовлетворенным вздохом вы понимаете, что чувствуете себя гораздо здоровее, чем мгновение назад.";
			} else {
				return UtilText.parse(target, "[npc.Name] удовлетворенно вздыхает, и вы замечаете, что [npc.sheIs] внезапно выглядит гораздо здоровее, чем [npc.she] мгновение назад.");
			}
		} else {
			if(target.isPlayer()) {
				return "Болезненное тепло просачивается сквозь всё ваше тело, и после слабого кашля вы понимаете, что чувствуете себя слабее, чем мгновение назад.";
			} else {
				return UtilText.parse(target, "[npc.Name] слабо кашляет, и вы замечаете, что [npc.sheIs] внезапно выглядит немного слабее, чем [npc.she] мгновение назад.");
			}
		}
	}
	
	protected static String genericAttributeEffect(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
			switch(potency) {
				case MAJOR_DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.6f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "Болезненная волна арканы накрывает [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -15));
						}
					}
					break;
				case DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.4f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "Болезненная волна арканы накрывает [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -10));
						}
					}
					break;
				case MINOR_DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.2f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "Болезненная волна арканы накрывает [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -5));
						}
					}
					break;
				case MINOR_BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.2f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "Успокаивающая волна арканической энергии омывает [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 5));
						}
					}
					break;
				case BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.4f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "Успокаивающая волна арканической энергии омывает [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 10));
						}
					}
					break;
				case SPECIAL:
				case MAJOR_BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.6f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "Успокаивающая волна арканической энергии омывает [npc.name]...")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 15));
						}
					}
					break;
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	// Caching:
	private static Map<AbstractRace, Map<TFModifier, LinkedHashMap<TFModifier, List<TFPotency>>>> racialPrimaryModSecondaryModPotencyGrid = new HashMap<>();
	
	protected static List<TFModifier> getRacialSecondaryModifiers(AbstractRace race, TFModifier primaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(race) && racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		}
	}
	
	protected static List<TFPotency> getRacialPotencyModifiers(AbstractRace race, TFModifier primaryModifier, TFModifier secondaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
		}
	}
	
	private static void populateGrid(AbstractRace race, TFModifier primaryModifier) {
		LinkedHashMap<TFModifier, List<TFPotency>> secondaryModPotencyMap = new LinkedHashMap<>();
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< RacialBody.valueOfRace(race).getAntennaTypes(true).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, TFPotency.getAllPotencies());
				break;
			
			case TF_ARMS:
				for(int i=0; i< ArmType.getArmTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				if(Main.game.isBodyHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_ASS:
				for(int i=0; i< AssType.getAssTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				if(Main.game.isAssHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				if(Main.game.isAnalContentEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				for(int i=0; i< BreastType.getBreastTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());

				if(primaryModifier==TFModifier.TF_BREASTS_CROTCH) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_UDDERS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_ROUND, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_POINTY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_PERKY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_SIDESET, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_WIDE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_NARROW, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_NORMAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_INVERTED, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_VAGINA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_LIPS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));

				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				}
				if(Main.game.isLactationContentEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_CORE:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FEMININITY, TFPotency.getAllPotencies());
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST, TFPotency.BOOST));
				break;
				
			case TF_EARS:
				for(int i=0; i< EarType.getEarTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_EYES:
				for(int i=0; i< EyeType.getEyeTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_VERTICAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HORIZONTAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_VERTICAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HORIZONTAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
				
			case TF_FACE:
				for(int i=0; i< FaceType.getFaceTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				if(Main.game.isPenetrationLimitationsEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_BIFURCATED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_WIDE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_FLAT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_STRONG, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.game.isFacialHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_HAIR:
				for(int i=0; i< HairType.getHairTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
				
			case TF_HORNS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
//				for(int i=0; i< RacialBody.valueOfRace(race).getHornTypes(true).size();i++) {
				for(int i=0; i< HornType.getHornTypes(race, false).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, TFPotency.getAllPotencies());
				break;
				
			case TF_LEGS:
				for(int i=0; i< LegType.getLegTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.BIPEDAL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAUR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.TAIL_LONG))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.TAIL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.ARACHNID))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_ARACHNID, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.CEPHALOPOD))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.AVIAN))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_AVIAN, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.PLANTIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_PLANTIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.DIGITIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_DIGITIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.UNGULIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_UNGULIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.hasSpinneret())) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_PENIS:
				for(int i=0; i< PenisType.getPenisTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BARBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_FLARED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BLUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_KNOTTED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_PREHENSILE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_SHEATHED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TAPERED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_VEINY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_OVIPOSITOR, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				if(Main.game.isCumRegenerationEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CUM_EXPULSION, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_SKIN:
				for(int i=0; i< TorsoType.getTorsoTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_TAIL:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< TailType.getTailTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

				if(RacialBody.valueOfRace(race).getTailType().stream().anyMatch(tt->tt.hasSpinneret())) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_TENTACLE:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				break;
				
			case TF_VAGINA:
				for(int i=0; i< VaginaType.getVaginaTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_SQUIRTER, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_EGG_LAYER, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				if(Main.game.isPenetrationLimitationsEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY_2, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH_2, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY_2, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY_2, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}

				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BARBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_FLARED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BLUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_KNOTTED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_PREHENSILE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_SHEATHED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TAPERED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_VEINY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_OVIPOSITOR, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				break;
				
			case TF_WINGS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< WingType.getWingTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				break;
				
			case TF_CUM: case TF_MILK: case TF_MILK_CROTCH: case TF_GIRLCUM:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ADDICTIVE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ALCOHOLIC, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ALCOHOLIC_WEAK, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_BUBBLING, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_HALLUCINOGENIC, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_MINERAL_OIL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_MUSKY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_SLIMY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_STICKY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_VISCOUS, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				

				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GIRLCUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MILK, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_FLAVOURLESS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BEER, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHOCOLATE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_HONEY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MINT, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_PINEAPPLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BUBBLEGUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_STRAWBERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_VANILLA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_COFFEE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_TEA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MAPLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CINNAMON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_LEMON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_ORANGE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GRAPE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MELON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_COCONUT, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BLUEBERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BANANA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
				
			default:
				secondaryModPotencyMap.put(TFModifier.NONE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
		}
		
		racialPrimaryModSecondaryModPotencyGrid.put(race, Util.newHashMapOfValues(new Value<>(primaryModifier, secondaryModPotencyMap)));
	}
	
	// And in the comments these words appear: 'My name is Innoxia, creator of smut: Look on my methods, ye Modders, and despair!'
	// Contributor's comment: OH GOD WHY
	// Innoxia's comment: Because you must suffer!
	
	private static int smallChangeMajorDrain = -3;
	private static int smallChangeDrain = -2;
	private static int smallChangeMinorDrain = -1;
	private static int smallChangeMinorBoost = 1;
	private static int smallChangeBoost = 2;
	private static int smallChangeMajorBoost = 3;
	
	private static int mediumChangeMajorDrain = -15;
	private static int mediumChangeDrain = -5;
	private static int mediumChangeMinorDrain = -1;
	private static int mediumChangeMinorBoost = 1;
	private static int mediumChangeBoost = 5;
	private static int mediumChangeMajorBoost = 15;
	
	private static int largeChangeMajorDrain = -50;
	private static int largeChangeDrain = -15;
	private static int largeChangeMinorDrain = -5;
	private static int largeChangeMinorBoost = 5;
	private static int largeChangeBoost = 15;
	private static int largeChangeMajorBoost = 50;

	private static int hugeChangeMajorDrain = -500;
	private static int hugeChangeDrain = -100;
	private static int hugeChangeMinorDrain = -25;
	private static int hugeChangeMinorBoost = 25;
	private static int hugeChangeBoost = 100;
	private static int hugeChangeMajorBoost = 500;
	
	private static int singleDrain = -1;
	private static int singleBoost = 1;
	
	private static int modifierTypeToInt(TFModifier modifier) {
		List<TFModifier> modifierList = Util.newArrayListOfValues(
				TFModifier.TF_TYPE_1,
				TFModifier.TF_TYPE_2,
				TFModifier.TF_TYPE_3,
				TFModifier.TF_TYPE_4,
				TFModifier.TF_TYPE_5,
				TFModifier.TF_TYPE_6,
				TFModifier.TF_TYPE_7,
				TFModifier.TF_TYPE_8,
				TFModifier.TF_TYPE_9,
				TFModifier.TF_TYPE_10);
		
		if(modifierList.contains(modifier)) {
			return modifierList.indexOf(modifier);
		}
		return 0;
	}
	
	protected static RacialEffectUtil getRacialEffect(AbstractRace race, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, GameCharacter user, GameCharacter target) {
		
		boolean revealTransformedPart = user!=null && target!=null && !user.equals(target);
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина антенн (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина антенн (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина антенн (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина антенн (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина антенн (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина антенн (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Добавляет еще одну пару антенн.") { @Override public String applyEffect() { return target.incrementAntennaRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет дополнительную пару усиков.") { @Override public String applyEffect() {
									List<AbstractAntennaType> antennaTypesSuitableForTransformation = RacialBody.valueOfRace(race).getAntennaTypes(true);
									if(target.getAntennaType().equals(AntennaType.NONE) && !antennaTypesSuitableForTransformation.isEmpty()) {
										return target.setAntennaType(antennaTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementAntennaRows(singleBoost);
									} } };
						}
						
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Усиков в ряд (" + smallChangeMajorDrain + " усиков в ряд)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Усиков в ряд (" + smallChangeDrain + " усиков в ряд)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Усиков в ряд (" + smallChangeMinorDrain + " усиков в ряд)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Усиков в ряд (+" + smallChangeMinorBoost + " усиков в ряд)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Усиков в ряд (+" + smallChangeBoost + " усиков в ряд)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Усиков в ряд (+" + smallChangeMajorBoost + " усиков в ряд)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMajorBoost); } };
						}
						
					case REMOVAL:
						return new RacialEffectUtil("Убирает усики.") { @Override public String applyEffect() { return target.setAntennaType(AntennaType.NONE); } };
	
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = modifierTypeToInt(secondaryModifier);
						return getAntennaTypeRacialEffectUtil(race, target, index);
							
					default:
						List<AbstractAntennaType> antennaTypes = RacialBody.valueOfRace(race).getAntennaTypes(true);
						AbstractAntennaType antennaType = antennaTypes.isEmpty()?AntennaType.NONE:Util.randomItemFrom(antennaTypes);
						return new RacialEffectUtil(antennaType.equals(AntennaType.NONE)?"Убирает усики.":Util.capitaliseSentence(race.getName(false))+" трансформация усиков.") {
							@Override public String applyEffect() { return target.setAntennaType(antennaType); } };
				}
			
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(ArmType.getArmTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(index).getTransformName())+" трансформация рук.") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(index)); } };
	
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает дополнительную пару рук.") { @Override public String applyEffect() { return target.incrementArmRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет дополнительную пару рук.") { @Override public String applyEffect() { return target.incrementArmRows(singleBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Убирает огромное количество волос под мышками. (" + smallChangeMajorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Убирает большое количество волос под мышками. (" + smallChangeDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Удаляет часть волос под мышками. (" + smallChangeMinorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет немного волос под мышками. (+" + smallChangeMinorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Добавляет много волос под мышками. (+" + smallChangeBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Добавляет огромное количество волос под мышками. (+" + smallChangeMajorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorBoost); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация рук.") { @Override public String applyEffect() { return target.setArmType(RacialBody.valueOfRace(race).getArmType()); } };
				}
				
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(AssType.getAssTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(index).getTransformName())+" трансформацию задницы.") {
							@Override public String applyEffect() {
								String tfText = target.setAssType(AssType.getAssTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.ASS, target, true);
									user.setKnowsCharacterArea(CoverableArea.ANUS, target, true);
								}
								return tfText;
							} };
	
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер задницы (" + smallChangeMajorDrain + " задницы)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер задницы (" + smallChangeDrain + " задницы)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер задницы (" + smallChangeMinorDrain + " задницы)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер задницы (+" + smallChangeMinorBoost + " задницы)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер задницы (+" + smallChangeBoost + " задницы)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер задницы (+" + smallChangeMajorBoost + " задницы)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер бедер (" + smallChangeMajorDrain + " размер бедер)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер бедер (" + smallChangeDrain + " размер бедер)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер бедер (" + smallChangeMinorDrain + " размер бедер)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер бедер (+" + smallChangeMinorBoost + " размер бедер)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер бедер (+" + smallChangeBoost + " размер бедер)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер бедер (+" + smallChangeMajorBoost + " размер бедер)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Убирает огромное количество волос на заднице. (" + smallChangeMajorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Убирает много волос на заднице. (" + smallChangeDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает немного волос на заднице. (" + smallChangeMinorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет немного волос на заднице. (+" + smallChangeMinorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Добавляет много волос на заднице. (+" + smallChangeBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Добавляет огромное количество волос на заднице. (+" + smallChangeMajorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Анальная емкость (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Анальная емкость (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость ануса (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость ануса (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость ануса (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость ануса (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина ануса (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина ануса (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина ануса (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина анального отверстия (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина анального отверстия (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина анального отверстия (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность ануса (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность ануса (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность ануса (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность ануса (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Анальная эластичность (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Анальная эластичность (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Анальная пластичность (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Анальная пластичность (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Анальная пластичность (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Анальная пластичность (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Анальная пластичность (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Анальная пластичность (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Анальная смазка (" + smallChangeMajorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Анальная смазка (" + smallChangeDrain + " влажность)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Анальная смазка (" + smallChangeMinorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Анальная смазка (+" + smallChangeMinorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Анальная смазка (+" + smallChangeBoost + " влажность)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Анальная смазка (+" + smallChangeMajorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость ануса.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает анус припухлым.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние ребра в анусе.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние ребра в анус.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает лишние внутренние мышцы в анусе.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет лишние внутренние мышцы в анус.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из ануса.") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в анус.") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформацию задницы.") { @Override public String applyEffect() {
							String tfText = target.setAssType(RacialBody.valueOfRace(race).getAssType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.ASS, target, true);
								user.setKnowsCharacterArea(CoverableArea.ANUS, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(BreastType.getBreastTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(index).getTransformName())+" трансформация груди.") {
							@Override public String applyEffect() {
								String tfText = target.setBreastType(BreastType.getBreastTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.BREASTS, target, true);
									user.setKnowsCharacterArea(CoverableArea.NIPPLES, target, true);
								}
								return tfText;
							} };
	
					case REMOVAL:
						return new RacialEffectUtil("Полностью уплощает грудь.") {
							@Override public String applyEffect() { return target.setBreastSize(0); } };
							
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает лишнюю пару грудей.") { @Override public String applyEffect() { return target.incrementBreastRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет лишнюю пару грудей.") { @Override public String applyEffect() { return target.incrementBreastRows(singleBoost); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает лишний сосок с каждой груди.") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет лишний сосок к каждой груди.") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleBoost); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер груди (" + smallChangeMajorDrain + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер груди (" + smallChangeDrain + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер груди (" + smallChangeMinorDrain + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер груди (+" + smallChangeMinorBoost + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер груди (+" + smallChangeBoost + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер груди (+" + smallChangeMajorBoost + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер сосков (" + smallChangeMajorDrain + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер сосков (" + smallChangeDrain + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер сосков (" + smallChangeMinorDrain + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер сосков (+" + smallChangeMinorBoost + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер сосков (+" + smallChangeBoost + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер сосков (+" + smallChangeMajorBoost + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorBoost); } };
						}

					case TF_MOD_BREAST_SHAPE_ROUND:
						return new RacialEffectUtil("Придает груди округлую форму.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.ROUND); } };
					case TF_MOD_BREAST_SHAPE_PERKY:
						return new RacialEffectUtil("Придает груди упругую форму.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.PERKY); } };
					case TF_MOD_BREAST_SHAPE_POINTY:
						return new RacialEffectUtil("Придает груди заостренную форму.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.POINTY); } };
					case TF_MOD_BREAST_SHAPE_SIDESET:
						return new RacialEffectUtil("Придает груди форму с разведенными сосками.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.SIDE_SET); } };
					case TF_MOD_BREAST_SHAPE_WIDE:
						return new RacialEffectUtil("Придает груди широкую форму.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.WIDE); } };
					case TF_MOD_BREAST_SHAPE_NARROW:
						return new RacialEffectUtil("Придает груди узкую форму.") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.NARROW); } };
						
					case TF_MOD_NIPPLE_NORMAL:
						return new RacialEffectUtil("Придает соскам нормальную форму.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_INVERTED:
						return new RacialEffectUtil("Превращает соски в обычную, хотя и вывернутую форму.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.INVERTED); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("Превращает соски в вагины.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("Превращает соски в пары губ.") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер ареол (" + smallChangeMajorDrain + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер ареол (" + smallChangeDrain + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер ареол (" + smallChangeMinorDrain + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер ареол (+" + smallChangeMinorBoost + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер ареол (+" + smallChangeBoost + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер ареол (+" + smallChangeMajorBoost + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_AREOLAE_CIRCLE:
						return new RacialEffectUtil("Придает ареолам форму обычных кругов.") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.NORMAL); } };
					case TF_MOD_AREOLAE_HEART:
						return new RacialEffectUtil("Придает ареолам форму сердечек.") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.HEART); } };
					case TF_MOD_AREOLAE_STAR:
						return new RacialEffectUtil("Придает ареолам форму звезд.") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.STAR); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость сосков (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость сосков (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость сосков (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость сосков (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость сосков (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость сосков (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина сосков (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина сосков (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина сосков (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина сосков (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина сосков (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина сосков (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность сосков (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность сосков (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность сосков (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность сосков (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность сосков (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность сосков (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность сосков (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность сосков (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность сосков (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность сосков (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность сосков (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность сосков (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость молока (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Запас молока (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Запас молока (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Запас молока (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Запас молока (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Запас молока (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость сосков.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает соски очень припухлыми.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутреннюю ребристость сосков.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутреннюю ребристость соскам.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы из сосков.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы в соски.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из сосков.") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в соски.") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация груди.") { @Override public String applyEffect() {
							String tfText = target.setBreastType(RacialBody.valueOfRace(race).getBreastType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.BREASTS, target, true);
								user.setKnowsCharacterArea(CoverableArea.NIPPLES, target, true);
							}
							return tfText;
						} };
				}

			case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(BreastType.getBreastTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(index).getTransformName())+" трансформация промежности в грудь.") { @Override public String applyEffect() {
							String tfText = target.setBreastCrotchType(BreastType.getBreastTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.BREASTS_CROTCH, target, true);
									user.setKnowsCharacterArea(CoverableArea.NIPPLES_CROTCH, target, true);
								}
								return tfText;
							} };
	
					case REMOVAL:
						return new RacialEffectUtil("Убирает лобковые сиськи.") {
							@Override public String applyEffect() {
								String tfText = target.setBreastCrotchType(BreastType.NONE);
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.BREASTS_CROTCH, target, true);
									user.setKnowsCharacterArea(CoverableArea.NIPPLES_CROTCH, target, true);
								}
								return tfText;
							} };
							
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает дополнительную пару лобковых сисек.") { @Override public String applyEffect() { return target.incrementBreastCrotchRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет дополнительную пару лобковых сисек.") { @Override public String applyEffect() { return target.incrementBreastCrotchRows(singleBoost); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает по одному лишнему соску с каждой лобковой сиськи.") { @Override public String applyEffect() {
									return target.incrementNippleCrotchCountPerBreast(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет по одному лишнему соску к каждой лобковой сиське.") { @Override public String applyEffect() {
									return target.incrementNippleCrotchCountPerBreast(singleBoost); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер лобковых сисек (" + smallChangeMajorDrain + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер лобковых сисек (" + smallChangeDrain + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер лобковых сисек (" + smallChangeMinorDrain + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер лобковых сисек (" + smallChangeMinorBoost + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер лобковых сисек (" + smallChangeBoost + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер лобковых грудей (" + smallChangeMajorBoost + " размер груди)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер сосков (" + smallChangeMajorDrain + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер сосков (" + smallChangeDrain + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер сосков (" + smallChangeMinorDrain + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер сосков (" + smallChangeMinorBoost + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер сосков (" + smallChangeBoost + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер сосков (" + smallChangeMajorBoost + " размер сосков)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorBoost); } };
						}

					case TF_MOD_BREAST_SHAPE_UDDERS:
						return new RacialEffectUtil("Превращает лобковые груди в вымя.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.UDDERS); } };
					case TF_MOD_BREAST_SHAPE_ROUND:
						return new RacialEffectUtil("Придает лобковым грудям округлую форму.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.ROUND); } };
					case TF_MOD_BREAST_SHAPE_PERKY:
						return new RacialEffectUtil("Придает лобковым грудям упругую форму.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.PERKY); } };
					case TF_MOD_BREAST_SHAPE_POINTY:
						return new RacialEffectUtil("Придает лобковым грудям заостренную форму.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.POINTY); } };
					case TF_MOD_BREAST_SHAPE_SIDESET:
						return new RacialEffectUtil("Придает лобковым грудям форму, направленную в стороны.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.SIDE_SET); } };
					case TF_MOD_BREAST_SHAPE_WIDE:
						return new RacialEffectUtil("Придает лобковым грудям широкую форму.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.WIDE); } };
					case TF_MOD_BREAST_SHAPE_NARROW:
						return new RacialEffectUtil("Делает форму лобка узкой.") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.NARROW); } };
						
					case TF_MOD_NIPPLE_NORMAL:
						return new RacialEffectUtil("Придает соскам нормальную форму.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_INVERTED:
						return new RacialEffectUtil("Превращает соски в обычную, хотя и вывернутую форму.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.INVERTED); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("Превращает соски в вагины.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("Превращает соски в пары губ.") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер ареол (" + smallChangeMajorDrain + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер ареол (" + smallChangeDrain + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер ареол (" + smallChangeMinorDrain + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер ареол (" + smallChangeMinorBoost + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер ареол (" + smallChangeBoost + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер ареол (" + smallChangeMajorBoost + " размер ареол)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_AREOLAE_CIRCLE:
						return new RacialEffectUtil("Придает ареолам форму обычных кругов.") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.NORMAL); } };
					case TF_MOD_AREOLAE_HEART:
						return new RacialEffectUtil("Придает ареолам форму сердечек.") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.HEART); } };
					case TF_MOD_AREOLAE_STAR:
						return new RacialEffectUtil("Придает ареолам форму звезд.") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.STAR); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость сосков (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость сосков (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость сосков (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость сосков (" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость сосков (" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость сосков (" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина сосков (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина сосков (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина сосков (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина сосков (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина сосков (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина сосков (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность сосков (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность сосков (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность сосков (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность сосков (" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность сосков (" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность сосков (" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность сосков (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность сосков (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность сосков (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность сосков (" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность сосков (" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность сосков (" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость молока (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Запас молока (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Запас молока (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость молока (" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость молока (" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость молока (" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Регенерация молока") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость сосков.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает соски очень припухлыми.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутреннюю ребристость сосков.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутреннюю ребристость соскам.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы из сосков.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы в соски.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из сосков.") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в соски.") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация промежности в грудь.") { @Override public String applyEffect() {
							String tfText = target.setBreastCrotchType(RacialBody.valueOfRace(race).getBreastType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.BREASTS_CROTCH, target, true);
								user.setKnowsCharacterArea(CoverableArea.NIPPLES_CROTCH, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_CORE: 
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Рост (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorDrain, false); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Рост (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeDrain, false); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Рост (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorDrain, false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Рост (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorBoost, false); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Рост (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeBoost, false); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Рост (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorBoost, false); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Мышечная масса (" + mediumChangeMajorDrain + " мышцы)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Мышечная масса (" + mediumChangeDrain + " мышцы)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Мышечная масса (" + mediumChangeMinorDrain + " мышцы)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Мышечная масса (+" + mediumChangeMinorBoost + " мышцы)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Мышечная масса (+" + mediumChangeBoost + " мышцы)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Мышечная масса (+" + mediumChangeMajorBoost + " мышцы)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер тела (" + mediumChangeMajorDrain + " размер тела)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер тела (" + mediumChangeDrain + " размер тела)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер тела (" + mediumChangeMinorDrain + " размер тела)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер тела (+" + mediumChangeMinorBoost + " размер тела)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер тела (+" + mediumChangeBoost + " размер тела)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер тела (+" + mediumChangeMajorBoost + " размер тела)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_FEMININITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Женственность (" + mediumChangeMajorDrain + " женственность)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Женственность (" + mediumChangeDrain + " женственность)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Женственность (" + mediumChangeMinorDrain + " женственность)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Женственность (+" + mediumChangeMinorBoost + " женственность)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Женственность (+" + mediumChangeBoost + " женственность)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Женственность (+" + mediumChangeMajorBoost + " женственность)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Убирает огромное количество лобковых волос. (" + smallChangeMajorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Убирает большое количество лобковых волос. (" + smallChangeDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает немного лобковых волос. (" + smallChangeMinorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет немного лобковых волос. (+" + smallChangeMinorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Добавляет большое количество лобковых волос. (+" + smallChangeBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Добавляет огромное количество лобковых волос. (+" + smallChangeMajorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}
					case TF_MOD_INTERNAL:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает клоаку, делая гениталии и анус внешними.") { @Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.NORMAL); } };
							case BOOST:
								return new RacialEffectUtil("Переносит гениталии и анус внутрь клоаки (в место расположения ануса).") {
									@Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.CLOACA_BEHIND); }
								};
							case MINOR_BOOST:
								default:
								return new RacialEffectUtil("Перемещает гениталии и анус в клоаку (в местоположение гениталий).") {
									@Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.CLOACA); }
								};
						}
						
					default:
						return new RacialEffectUtil("Случайный "+race.getName(false)+" transformation") {
							@Override
							public String applyEffect() {
								List<TFModifier> availableModifiers = new ArrayList<>();
								
								// Only add TFModifiers which will do something:
								for(TFModifier tfMod : TFModifier.getTFRacialBodyPartsList()) {
									boolean add = false;
									switch(tfMod) {
										case TF_ANTENNA:
											add = target.getAntennaType() != race.getRacialBody().getAntennaTypes(false).get(0);
											break;
										case TF_ARMS:
											add = target.getArmType() != race.getRacialBody().getArmType();
											break;
										case TF_ASS:
											add = target.getAssType() != race.getRacialBody().getAssType();
											break;
										case TF_BREASTS:
											add = target.getBreastType() != race.getRacialBody().getBreastType();
											break;
										case TF_BREASTS_CROTCH:
											add = target.hasBreastsCrotch() && target.getBreastCrotchType() != race.getRacialBody().getBreastCrotchType();
											break;
										case TF_CORE:
											break;
										case TF_EARS:
											add = target.getEarType() != race.getRacialBody().getEarType();
											break;
										case TF_EYES:
											add = target.getEyeType() != race.getRacialBody().getEyeType();
											break;
										case TF_FACE:
											add = target.getFaceType() != race.getRacialBody().getFaceType();
											break;
										case TF_HAIR:
											add = target.getHairType() != race.getRacialBody().getHairType();
											break;
										case TF_HORNS:
											add = target.getHornType() != race.getRacialBody().getHornTypes(false).get(0);
											break;
										case TF_LEGS:
											add = target.getLegType() != race.getRacialBody().getLegType();
											break;
										case TF_PENIS:
											add = target.hasPenisIgnoreDildo() && target.getPenisType() != race.getRacialBody().getPenisType();
											break;
										case TF_SKIN:
											add = target.getTorsoType() != race.getRacialBody().getTorsoType();
											break;
										case TF_TAIL:
											add = target.getTailType() != race.getRacialBody().getTailType().get(0);
											break;
										case TF_TENTACLE:
											break;
										case TF_VAGINA:
											add = target.hasVagina() && target.getVaginaType() != race.getRacialBody().getVaginaType();
											break;
										case TF_WINGS:
											add = target.getWingType() != race.getRacialBody().getWingTypes().get(0);
											break;
										default:
											break;
									}
									if(add) {
										availableModifiers.add(tfMod);
									}
								}
								
								if(availableModifiers.isEmpty()) {
									return UtilText.parse(target, "<p style='text-align:center'>[style.italicsDisabled([npc.NameHasFull] больше нет случайных "+race.getName(true)+" доступных трансформаций, поэтому ничего не происходит...)]</p>");
								}
								
								
								TFModifier mod = availableModifiers.get(Util.random.nextInt(availableModifiers.size()));
								
								// If race does not have antenna, horns, tail, wings, or crotch-boobs, make sure that the TF is to remove:
								if((mod==TFModifier.TF_ANTENNA && race.getRacialBody().getAntennaTypes(false).size()==1 && race.getRacialBody().getAntennaTypes(false).contains(AntennaType.NONE))
										|| (mod==TFModifier.TF_HORNS && race.getRacialBody().getHornTypes(false).size()==1 && race.getRacialBody().getHornTypes(false).contains(HornType.NONE))
										|| (mod==TFModifier.TF_TAIL && race.getRacialBody().getTailType().size()==1 && race.getRacialBody().getTailType().contains(TailType.NONE))
										|| (mod==TFModifier.TF_WINGS && race.getRacialBody().getWingTypes().size()==1 && race.getRacialBody().getWingTypes().contains(WingType.NONE))
										|| ((mod==TFModifier.TF_BREASTS_CROTCH && race.getRacialBody().getBreastCrotchType()==BreastType.NONE))) {
									return getRacialEffect(race, mod, TFModifier.REMOVAL, potency, user, target).applyEffect();
								}
								
								return getRacialEffect(race, mod, secondaryModifier, potency, user, target).applyEffect();
							}
						};
				}
				
			case TF_EARS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(EarType.getEarTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(index).getTransformName())+" трансформация ушей.") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(index)); } };
	
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация ушей.") { @Override public String applyEffect() { return target.setEarType(RacialBody.valueOfRace(race).getEarType()); } };
				}
				
			case TF_EYES:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(EyeType.getEyeTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(index).getTransformName())+" трансформация глаз.") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(index)); } };
						
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Удаляет лишнюю пару глаз.") { @Override public String applyEffect() { return target.incrementEyePairs(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет лишнюю пару глаз.") { @Override public String applyEffect() { return target.incrementEyePairs(singleBoost); } };
						}

					case TF_MOD_EYE_IRIS_CIRCLE:
						return new RacialEffectUtil("Придает радужке глаз круглую форму.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_IRIS_VERTICAL:
						return new RacialEffectUtil("Придает радужке вертикальную форму.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_IRIS_HORIZONTAL:
						return new RacialEffectUtil("Придает радужке горизонтальную форму.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_IRIS_HEART:
						return new RacialEffectUtil("Придает радужке форму сердца.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HEART); } };
					case TF_MOD_EYE_IRIS_STAR:
						return new RacialEffectUtil("Придает радужке форму звезды.") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.STAR); } };
						
					case TF_MOD_EYE_PUPIL_CIRCLE:
						return new RacialEffectUtil("Придает зрачкам круглую форму.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_PUPIL_VERTICAL:
						return new RacialEffectUtil("Придает зрачкам вертикальную форму.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_PUPIL_HORIZONTAL:
						return new RacialEffectUtil("Придает зрачкам горизонтальную форму.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_PUPIL_HEART:
						return new RacialEffectUtil("Придает зрачкам форму сердца.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HEART); } };
					case TF_MOD_EYE_PUPIL_STAR:
						return new RacialEffectUtil("Придает зрачкам форму звезды.") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.STAR); } };
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация глаз.") { @Override public String applyEffect() { return target.setEyeType(RacialBody.valueOfRace(race).getEyeType()); } };
				}
				
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(FaceType.getFaceTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(index).getTransformName())+" трансформация лица.") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(index)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер губ (" + smallChangeMajorDrain + " размер губ)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер губ (" + smallChangeDrain + " размер губ)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер губ (" + smallChangeMinorDrain + " размер губ)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер губ (+" + smallChangeMinorBoost + " размер губ)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер губ (+" + smallChangeBoost + " размер губ)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер губ (+" + smallChangeMajorBoost + " размер губ)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость губ.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает губы экстремально пухлыми.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние ребра в горле.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние ребра в горло.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы в горле.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы в горло.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца в горле.") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в горло.") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость горла (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость горла (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость горла (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость горла (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость горла (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость горла (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина горла (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина горла (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина горла (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина горла (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина горла (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина горла (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность горла (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность горла (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность горла (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность горла (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность горла (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность горла (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность горла (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность горла (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность горла (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность горла (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность горла (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность горла (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Выделение слюны (" + smallChangeMajorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Выделение слюны (" + smallChangeDrain + " влажность)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Выделение слюны (" + smallChangeMinorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Выделение слюны (+" + smallChangeMinorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Выделение слюны (+" + smallChangeBoost + " влажность)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Выделение слюны (+" + smallChangeMajorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина языка (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина языка (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина языка (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина языка (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина языка (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина языка (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorBoost); } };
						}
					case TF_MOD_TONGUE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает пупырышки с языка.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет пупырышки на язык.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.RIBBED); } };
						}
					case TF_MOD_TONGUE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает щупальца с языка.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет щупальца на язык.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.TENTACLED); } };
						}
					case TF_MOD_TONGUE_BIFURCATED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает раздвоенность языка.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.BIFURCATED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Разделяет язык на две части.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.BIFURCATED); } };
						}
					case TF_MOD_TONGUE_WIDE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Возвращает языку прежнюю ширину.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.WIDE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Расширяет язык.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.WIDE); } };
						}
					case TF_MOD_TONGUE_FLAT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Возвращает языку прежнюю форму.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.FLAT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает язык плоским.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.FLAT); } };
						}
					case TF_MOD_TONGUE_STRONG:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает избыточную силу языка.") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.STRONG); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает язык невероятно сильным.") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.STRONG); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Убирает огромное количество растительности на лице. (" + smallChangeMajorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Убирает большое количество растительности на лице. (" + smallChangeDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает немного растительности на лице. (" + smallChangeMinorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет немного растительности на лице. (+" + smallChangeMinorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Добавляет много растительности на лице. (+" + smallChangeBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Добавляет огромное количество растительности на лице. (+" + smallChangeMajorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorBoost); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация лица.") { @Override public String applyEffect() { return target.setFaceType(RacialBody.valueOfRace(race).getFaceType()); } };
				}
				
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(HairType.getHairTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(index).getTransformName())+" преобразование волос.") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(index)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина волос (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина волос (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина волос (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина волос (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина волос (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина волос (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorBoost); } };
						}

					case TF_MOD_SIZE_SECONDARY:
						if(potency.isNegative()) {
							return new RacialEffectUtil("Убирает пушок на шее.") { @Override public String applyEffect() { return target.setNeckFluff(false); } };
						} else {
							return new RacialEffectUtil("Добавляет пушок на шее.") { @Override public String applyEffect() { return target.setNeckFluff(true); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" преобразование волос.") { @Override public String applyEffect() { return target.setHairType(RacialBody.valueOfRace(race).getHairType()); } };
				}
				
			case TF_HORNS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("Убирает рога.") { @Override public String applyEffect() { return target.setHornType(HornType.NONE); } };
						
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = modifierTypeToInt(secondaryModifier);
						List<AbstractHornType> hornTypes = HornType.getHornTypes(race, false);
						AbstractHornType selectedHornType = index >= hornTypes.size() ? HornType.NONE : hornTypes.get(index);
						return new RacialEffectUtil(selectedHornType.equals(HornType.NONE)?"Убирает рога.":"Растет "+selectedHornType.getTransformName()+" horn"+(selectedHornType==HornType.HORSE_STRAIGHT?"":"s")+".") {
							@Override public String applyEffect() { return target.setHornType(selectedHornType); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина рогов (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина рогов (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина рогов (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина рогов (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина рогов (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина рогов (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Добавляет лишнюю пару рогов.") { @Override public String applyEffect() { return target.incrementHornRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет лишнюю пару рогов.") { @Override public String applyEffect() {
									List<AbstractHornType> hornTypesSuitableForTransformation = RacialBody.valueOfRace(race).getHornTypes(true);
									if(target.getHornType().equals(HornType.NONE) && !hornTypesSuitableForTransformation.isEmpty()) {
										return target.setHornType(hornTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementHornRows(singleBoost);
									} } };
						}
						
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Рогов в ряду (" + smallChangeMajorDrain + " рогов в ряду)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Рогов в ряду (" + smallChangeDrain + " рогов в ряду)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Рогов в ряду (" + smallChangeMinorDrain + " рогов в ряду)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Рогов в ряду (+" + smallChangeMinorBoost + " рогов в ряду)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Рогов в ряду (+" + smallChangeBoost + " рогов в ряду)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Рогов в ряду (+" + smallChangeMajorBoost + " рогов в ряду)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorBoost); } };
						}
						
					default:
						List<AbstractHornType> defaultHornTypes = RacialBody.valueOfRace(race).getHornTypes(true);
						AbstractHornType hornType = defaultHornTypes.isEmpty()?HornType.NONE:Util.randomItemFrom(defaultHornTypes);
						return new RacialEffectUtil(hornType.equals(HornType.NONE)?"Убирает рога.":Util.capitaliseSentence(race.getName(false))+" трансформация рогов.") {
							@Override public String applyEffect() { return target.setHornType(hornType); } };
				}
				
			case TF_LEGS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(LegType.getLegTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(index).getTransformName())+" трансформация ног.") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(index)); } };

					case TF_MOD_LEG_CONFIG_BIPEDAL:
						return new RacialEffectUtil(" Превращает нижнюю часть тела в пару двуногих ног.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.BIPEDAL).applyLegConfigurationTransformation(target, LegConfiguration.BIPEDAL, true, true); } };

					case TF_MOD_LEG_CONFIG_TAUR:
						return new RacialEffectUtil(" Превращает нижнюю часть тела в четвероногое существо.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.QUADRUPEDAL).applyLegConfigurationTransformation(target, LegConfiguration.QUADRUPEDAL, true, true); } };

					case TF_MOD_LEG_CONFIG_TAIL_LONG:
						return new RacialEffectUtil(" Превращает нижнюю часть тела в длинный змееподобный хвост.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.TAIL_LONG).applyLegConfigurationTransformation(target, LegConfiguration.TAIL_LONG, true, true); } };

					case TF_MOD_LEG_CONFIG_TAIL:
						return new RacialEffectUtil(" Превращает нижнюю часть тела в рыбий хвост.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.TAIL).applyLegConfigurationTransformation(target, LegConfiguration.TAIL, true, true); } };

					case TF_MOD_LEG_CONFIG_ARACHNID:
						return new RacialEffectUtil(" Превращает нижнюю часть тела в восьминогое паукообразное существо.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.ARACHNID).applyLegConfigurationTransformation(target, LegConfiguration.ARACHNID, true, true); } };

					case TF_MOD_LEG_CONFIG_CEPHALOPOD:
						return new RacialEffectUtil(" Превращает нижнюю часть тела в восьмищупальцевого головоногого моллюска.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.CEPHALOPOD).applyLegConfigurationTransformation(target, LegConfiguration.CEPHALOPOD, true, true); } };

					case TF_MOD_LEG_CONFIG_AVIAN:
						return new RacialEffectUtil(" Превращает нижнюю часть тела в облик дикой птицы.") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.AVIAN).applyLegConfigurationTransformation(target, LegConfiguration.AVIAN, true, true); } };
							

					case TF_MOD_FOOT_STRUCTURE_PLANTIGRADE:
						return new RacialEffectUtil(" Превращает структуру стопы в пятипалую (плантиградную).") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.PLANTIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_DIGITIGRADE:
						return new RacialEffectUtil(" Превращает структуру стопы в пальцеходящую (дигитиградную).") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.DIGITIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_UNGULIGRADE:
						return new RacialEffectUtil(" Превращает структуру стопы в копытную (унгвиградиную).") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.UNGULIGRADE); } };

					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина змеиного хвоста (" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина змеиного хвоста (" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина змеиного хвоста (" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина змеиного хвоста (+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина змеиного хвоста (+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина змеиного хвоста (+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает паутину дополнительно припухлой.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние ребра из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние ребра в паутину.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы в паутину.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в паутину.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость паутины (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость паутины (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость паутинного аппарата (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость паутинного аппарата (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость паутинного аппарата (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость паутинного аппарата (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина паутинного аппарата (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина паутинного аппарата (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина паутинного аппарата (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина паутинного аппарата (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина паутинного аппарата (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина паутинного аппарата (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность паутинки (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность паутинки (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность паутинки (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность паутинки (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность паутинки (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность паутинки (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность паутинки (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность паутинки (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность паутинки (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность паутинки (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность паутинки (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность паутинки (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Влажность паутинки (" + smallChangeMajorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Влажность паутинки (" + smallChangeDrain + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Влажность паутинки (" + smallChangeMinorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Влажность паутинки (+" + smallChangeMinorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Влажность паутинки (+" + smallChangeBoost + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Влажность паутинки (+" + smallChangeMajorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorBoost); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация ног.") { @Override public String applyEffect() { return target.setLegType(RacialBody.valueOfRace(race).getLegType()); } };
				}
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина члена (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина члена (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина члена (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина члена (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина члена (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина члена (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Обхват члена (" + smallChangeMajorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Обхват члена (" + smallChangeDrain + " размер)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Обхват члена (" + smallChangeMinorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Обхват члена (+" + smallChangeMinorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Обхват члена (+" + smallChangeBoost + " обхват)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Обхват члена (+" + smallChangeMajorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Удаляет член.") { @Override public String applyEffect() {
								String tfText = target.setPenisType(PenisType.NONE);
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.PENIS, target, true);
									user.setKnowsCharacterArea(CoverableArea.TESTICLES, target, true);
								}
								return tfText;
							} };

					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Убирает огромное количество лобковых волос. (" + smallChangeMajorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Убирает большое количество лобковых волос. (" + smallChangeDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает немного лобковых волос. (" + smallChangeMinorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет немного лобковых волос. (" + smallChangeMinorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Добавляет много лобковых волос. (" + smallChangeBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Добавляет огромное количество лобковых волос. (" + smallChangeMajorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает зазубрины с члена.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет зазубрины на член.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает расширение на головке члена.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет головке пениса яркость.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.FLARED); } };
						}
					case TF_MOD_PENIS_BLUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает тупость головки пениса.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.BLUNT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает головку пениса тупой.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.BLUNT); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает узел у основания пениса.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет узел у основания пениса.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает цепкость у пениса.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет цепкость пенису.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает ребристость у пениса.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет пенису ребристость.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает препуций у пениса.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет чехол на член.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает сужение члена.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет сужение члена.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает щупальца с члена.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет щупальца на член.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает вздутые вены с члена.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет вздутые вены на член.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.VEINY); } };
						}
					case TF_MOD_PENIS_OVIPOSITOR:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает функцию яйцеклада у члена.") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.OVIPOSITOR); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет функцию яйцеклада члену.") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.OVIPOSITOR); } };
						}

					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер яичек (" + smallChangeMajorDrain + " размер)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер яичек (" + smallChangeDrain + " размер)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер яичек (" + smallChangeMinorDrain + " размер)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер яичек (+" + smallChangeMinorBoost + " размер)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер яичек (+" + smallChangeBoost + " размер)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер яичек (+" + smallChangeMajorBoost + " размер)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Удаляет дополнительную пару яичек.") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет дополнительную пару яичек.") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость спермы (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorDrain);
									target.fillCumToMaxStorage();
									return s;} };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость спермы (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость спермы (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Общий объем (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Общий объем (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Общий объем (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorBoost);
									target.fillCumToMaxStorage();
									return s; } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Общая регенерация") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Общая регенерация") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Общая регенерация") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Общая регенерация") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Общая регенерация") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Общая регенерация") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorBoost); } };
						}
					case TF_MOD_CUM_EXPULSION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Общее количество при оргазме (" + mediumChangeMajorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Количество спермы при оргазме (" + mediumChangeDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Количество спермы при оргазме (" + mediumChangeMinorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Количество спермы при оргазме (+" + mediumChangeMinorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Количество спермы при оргазме (+" + mediumChangeBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Количество спермы при оргазме (+" + mediumChangeMajorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorBoost); } };
						}
					case TF_MOD_INTERNAL:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Делает яички внешними.") { @Override public String applyEffect() { return target.setInternalTesticles(false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает яички внутренними.") { @Override public String applyEffect() { return target.setInternalTesticles(true); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость уретры (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость уретры (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость уретры (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость уретры (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость уретры (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость уретры (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина уретры (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина уретры (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина уретры (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина уретры (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина уретры (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина уретры (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность уретры (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность уретры (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность уретры (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность уретры (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность уретры (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность уретры (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность уретры (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность уретры (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность уретры (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность уретры (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность уретры (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность уретры (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость уретры.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает уретру экстремально припухлой.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние ребра уретры.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние ребра уретре.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы из уретры.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы в уретру.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из уретры.") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в уретру.") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(PenisType.getPenisTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								PenisType.getPenisTypes(race).get(index)==PenisType.NONE
									?"Удаляет член."
									:Util.capitaliseSentence(PenisType.getPenisTypes(race).get(index).getTransformName())+" трансформация пениса.") { @Override public String applyEffect() {
								String tfText = target.setPenisType(PenisType.getPenisTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.PENIS, target, true);
									user.setKnowsCharacterArea(CoverableArea.TESTICLES, target, true);
								}
								return tfText;
							} };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация пениса.") { @Override public String applyEffect() {
							String tfText = target.setPenisType(RacialBody.valueOfRace(race).getPenisType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.PENIS, target, true);
								user.setKnowsCharacterArea(CoverableArea.TESTICLES, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_SKIN:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(TorsoType.getTorsoTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(TorsoType.getTorsoTypes(race).get(index).getTransformName())+" трансформация торса.") {
							@Override public String applyEffect() { return target.setTorsoType(TorsoType.getTorsoTypes(race).get(index)); } };
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация торса.") { @Override public String applyEffect() { return target.setTorsoType(RacialBody.valueOfRace(race).getTorsoType()); } };
				}
				
			case TF_TAIL:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает лишний хвост.") { @Override public String applyEffect() { return target.incrementTailCount(singleDrain, false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет лишний хвост.") { @Override public String applyEffect() {
									List<AbstractTailType> tailTypesSuitableForTransformation = TailType.getTailTypesSuitableForTransformation(RacialBody.valueOfRace(race).getTailType());
									if(target.getTailType()==TailType.NONE && !tailTypesSuitableForTransformation.isEmpty()) {
										return target.setTailType(tailTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementTailCount(singleBoost, false);
									} } };
						}

					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина хвоста (" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина хвоста (" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина хвоста (" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина хвоста (+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина хвоста (+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина хвоста (+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Обхват хвоста (" + smallChangeMajorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Обхват хвоста (" + smallChangeDrain + " обхват)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Обхват хвоста (" + smallChangeMinorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Обхват хвоста (+" + smallChangeMinorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Обхват хвоста (+" + smallChangeBoost + " обхват)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Обхват хвоста (+" + smallChangeMajorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMajorBoost); } };
						}
						
					case REMOVAL:
						return new RacialEffectUtil("Удаляет хвост.") { @Override public String applyEffect() { return target.setTailType(TailType.NONE); } };

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(TailType.getTailTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								TailType.getTailTypes(race).get(index)==TailType.NONE
									?"Удаляет хвост."
									:Util.capitaliseSentence(TailType.getTailTypes(race).get(index).getTransformName())+" трансформация хвоста.") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(index)); } };

					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает паутину дополнительно припухлой.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние ребра из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние ребра в паутину.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы в паутину.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из паутины.") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в паутину.") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость паутины (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость паутины (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость паутинного аппарата (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость паутинного аппарата (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость паутинного аппарата (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость паутинного аппарата (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина паутинного аппарата (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина паутинного аппарата (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина паутинного аппарата (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина паутинного аппарата (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина паутинного аппарата (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина паутинного аппарата (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность паутинки (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность паутинки (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность паутинки (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность паутинки (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность паутинки (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность паутинки (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность паутинки (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность паутинки (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность паутинки (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность паутинки (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность паутинки (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность паутинки (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Влажность паутинки (" + smallChangeMajorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Влажность паутинки (" + smallChangeDrain + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Влажность паутинки (" + smallChangeMinorDrain + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Влажность паутинки (+" + smallChangeMinorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Влажность паутинки (+" + smallChangeBoost + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Влажность паутинки (+" + smallChangeMajorBoost + " влажность)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorBoost); } };
						}
							
					default:
						AbstractTailType tailType = RacialBody.valueOfRace(race).getRandomTailType(false);
						return new RacialEffectUtil(tailType==TailType.NONE?"Удаляет хвост.":Util.capitaliseSentence(race.getName(false))+" трансформация хвоста.") {
							@Override public String applyEffect() { return target.setTailType(tailType); } };
				}
				
			case TF_TENTACLE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина щупальца (" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина щупальца (" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина щупальца (" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина щупальца (+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина щупальца (+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина щупальца (+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					default://case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Толщина щупальца (" + smallChangeMajorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Толщина щупальца (" + smallChangeDrain + " обхват)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Толщина щупальца (" + smallChangeMinorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Толщина щупальца (+" + smallChangeMinorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Толщина щупальца (+" + smallChangeBoost + " обхват)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Толщина щупальца (+" + smallChangeMajorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMajorBoost); } };
						}
				}
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Длина клитора (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Длина клитора (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Длина клитора (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Длина клитора (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Длина клитора (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Длина клитора (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Толщина клитора (" + smallChangeMajorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Толщина клитора (" + smallChangeDrain + " обхват)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Толщина клитора (" + smallChangeMinorDrain + " обхват)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Толщина клитора (+" + smallChangeMinorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Толщина клитора (+" + smallChangeBoost + " обхват)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Толщина клитора (+" + smallChangeMajorBoost + " обхват)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер половых губ (" + smallChangeMajorDrain + " размер)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер половых губ (" + smallChangeDrain + " размер)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер половых губ (" + smallChangeMinorDrain + " размер)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер половых губ (+" + smallChangeMinorBoost + " размер)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер половых губ (+" + smallChangeBoost + " размер)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер половых губ (+" + smallChangeMajorBoost + " размер)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("Убирает влагалище.") { @Override public String applyEffect() {
								String tfText = target.setVaginaType(VaginaType.NONE);
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.VAGINA, target, true);
								}
								return tfText;
							} };
							
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("Убирает огромное количество лобковых волос. (" + smallChangeMajorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("Убирает большое количество лобковых волос. (" + smallChangeDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает немного лобковых волос. (" + smallChangeMinorDrain + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет немного лобковых волос. (" + smallChangeMinorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("Добавляет много лобковых волос. (" + smallChangeBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("Добавляет огромное количество лобковых волос. (" + smallChangeMajorBoost + " волосатость)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}

					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает шипы с клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет шипы на клитор.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает расширение головки клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет расширение головке клитора.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.FLARED); } };
						}
					case TF_MOD_PENIS_BLUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает затупленный кончик клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.BLUNT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает кончик клитора затупленным.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.BLUNT); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает утолщение у основания клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет утолщение у основания клитора.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает подвижность клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет клиторусу цепкость.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает ребристость клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет клитору рельефность.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает лишнюю складку клиторальной капюшона.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет лишнюю складку клиторального капюшона.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает сужение клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет сужение клитора.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает щупальца с клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет щупальца на клитор.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает вздутые вены на клиторе.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет вздутые вены на клитор.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.VEINY); } };
						}
					case TF_MOD_PENIS_OVIPOSITOR:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает функцию яйцеклада у клитора.") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.OVIPOSITOR); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет функцию яйцеклада клитору.") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.OVIPOSITOR); } };
						}
							
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость влагалища (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость влагалища (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость влагалища (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость влагалища (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость влагалища (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость влагалища (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина влагалища (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина влагалища (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина влагалища (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина влагалища (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина влагалища (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина влагалища (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность влагалища (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность влагалища (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность влагалища (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность влагалища (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность влагалища (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность влагалища (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность влагалища (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность влагалища (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность влагалища (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность влагалища (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность влагалища (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность влагалища (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Смазка влагалища (" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Смазка влагалища (" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Смазка влагалища (" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вагинальная смазка (+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вагинальная смазка (+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вагинальная смазка (+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}

					case TF_MOD_VAGINA_SQUIRTER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("Заставляет влагалище сквиртовать во время оргазма.") { @Override public String applyEffect() { return target.setVaginaSquirter(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("Предотвращает сквирт во время оргазма.") { @Override public String applyEffect() { return target.setVaginaSquirter(false); } };
						}

					case TF_MOD_VAGINA_EGG_LAYER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("Заставляет влагалище откладывать яйца.") { @Override public String applyEffect() { return target.setVaginaEggLayer(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("Заставляет влагалище рожать живых детенышей.") { @Override public String applyEffect() { return target.setVaginaEggLayer(false); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость с половых губ.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает половые губы экстремально припухлыми.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние ребра во влагалище.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние ребристые структуры во влагалище.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы из влагалища.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы во влагалище.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из влагалища.") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца во влагалище.") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					// Urethral stuff:
					case TF_MOD_CAPACITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость уретры (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость уретры (" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость уретры (" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вместимость уретры (+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вместимость уретры (+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вместимость уретры (+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Глубина уретры (" + smallChangeMajorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Глубина уретры (" + smallChangeDrain + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Глубина уретры (" + smallChangeMinorDrain + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Глубина уретры (+" + smallChangeMinorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Глубина уретры (+" + smallChangeBoost + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Глубина уретры (+" + smallChangeMajorBoost + " глубина)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Эластичность уретры (" + smallChangeMajorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Эластичность уретры (" + smallChangeDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Эластичность уретры (" + smallChangeMinorDrain + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Эластичность уретры (+" + smallChangeMinorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Эластичность уретры (+" + smallChangeBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Эластичность уретры (+" + smallChangeMajorBoost + " эластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Пластичность уретры (" + smallChangeMajorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Пластичность уретры (" + smallChangeDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Пластичность уретры (" + smallChangeMinorDrain + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Пластичность уретры (+" + smallChangeMinorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Пластичность уретры (+" + smallChangeBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Пластичность уретры (+" + smallChangeMajorBoost + " пластичность)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает припухлость уретры.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Делает уретру экстремально припухлой.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние ребра уретры.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние ребра уретре.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние мышцы из уретры.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние мышцы в уретру.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("Убирает внутренние щупальца из уретры.") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("Добавляет внутренние щупальца в уретру.") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(VaginaType.getVaginaTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								VaginaType.getVaginaTypes(race).get(index)==VaginaType.NONE
									?"Удаляет член."
									:Util.capitaliseSentence(VaginaType.getVaginaTypes(race).get(index).getTransformName())+" трансформация влагалища.") { @Override public String applyEffect() {
								String tfText = target.setVaginaType(VaginaType.getVaginaTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.VAGINA, target, true);
								}
								return tfText;
							} };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+" трансформация влагалища.") { @Override public String applyEffect() {
							String tfText = target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.VAGINA, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_WINGS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("Убирает крылья.") { @Override public String applyEffect() { return target.setWingType(WingType.NONE); } };

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(WingType.getWingTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(WingType.getWingTypes(race).get(index)==WingType.NONE?"Убирает крылья.":Util.capitaliseSentence(WingType.getWingTypes(race).get(index).getTransformName())+" трансформация крыльев.") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(index)); } };
							
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Размер крыльев (" + smallChangeMajorDrain + " размер крыльев)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Размер крыльев (" + smallChangeDrain + " размер крыльев)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Размер крыльев (" + smallChangeMinorDrain + " размер крыльев)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Размер крыльев (+" + smallChangeMinorBoost + " размер крыльев)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Размер крыльев (+" + smallChangeBoost + " размер крыльев)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Размер крыльев (+" + smallChangeMajorBoost + " размер крыльев)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorBoost); } };
						}
					default:
						AbstractWingType wingType = RacialBody.valueOfRace(race).getRandomWingType(false);
						return new RacialEffectUtil(wingType==WingType.NONE?"Убирает крылья.":Util.capitaliseSentence(race.getName(false))+" трансформация крыльев.") {
							@Override public String applyEffect() { return target.setWingType(wingType); } };
				}
				
			case TF_CUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Придает сперме вкус пива.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Придает сперме вкус шоколада.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Придает сперме вкус спермы.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Придает сперме вкус женских выделений.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Придает сперме вкус молока.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("Делает сперму безвкусной.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Придает сперме вкус меда.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Придает сперме вкус мяты.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Придает сперме вкус вишни.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Придает сперме вкус ананаса.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Придает сперме вкус баблгама.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Придает сперме вкус клубники.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Придает сперме вкус ванили.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Придает сперме вкус кофе.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Придает сперме вкус чая.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Придает сперме вкус кленового сиропа.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Придает сперме вкус корицы.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Придает сперме вкус лимона.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Придает сперме вкус апельсина.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Придает сперме вкус винограда.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Придает сперме вкус дыни.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Придает сперме вкус кокоса.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Придает сперме вкус черники.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("Придает сперме вкус банана.") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BANANA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект привыкания у спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Добавляет эффект привыкания к сперме.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект сильного содержания алкоголя из спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Делает сперму сильно алкогольной.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект алкоголя из спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("Делает сперму алкогольной.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект пузырьков из спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Делает сперму шипучей и пузырящейся.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает психоактивный эффект из спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Добавляет сперме психоактивный эффект.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект минерального масла из спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Добавляет сперме эффект минерального масла.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает мускусный эффект из спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Наделяет сперму сильным мускусным запахом.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект слизи из спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Делает сперму слизистой.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект липкости у спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Делает сперму липкой.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект вязкости у спермы.") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Делает сперму густой и вязкой.") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость спермы (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Вместимость спермы (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Вместимость спермы (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Общий объем (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Общий объем (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Общий объем (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorBoost); } };
						}
				}
				break;
				
			case TF_MILK:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Придает молоку вкус пива.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Придает молоку вкус шоколада.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Придает молоку вкус спермы.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Придает молоку вкус женской спермы.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Придает молоку вкус молока.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("Делает молоко безвкусным.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Придает молоку вкус меда.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Придает молоку вкус мяты.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Придает молоку вкус вишни.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Придает молоку вкус ананаса.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Придает молоку вкус баблгама.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Придает молоку вкус клубники.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Придает молоку вкус ванили.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Придает молоку вкус кофе.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Придает молоку вкус чая.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Придает молоку вкус кленового сиропа.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Придает молоку вкус корицы.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Придает молоку вкус лимона.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Придает молоку вкус апельсина.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Придает молоку вкус винограда.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Придает молоку вкус дыни.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Придает молоку вкус кокоса.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Придает молоку вкус черники.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("Придает молоку вкус банана.") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BANANA); } };
						
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект привыкания у молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Добавляет эффект привыкания к молоку.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект сильного содержания алкоголя из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Делает молоко сильно алкогольным.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект алкоголя из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("Делает молоко алкогольным.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект пузырьков из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Делает молоко шипучим и пузырящимся.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает психоактивный эффект из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Добавляет психоактивный эффект к молоку.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект минерального масла из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Добавляет эффект минерального масла к молоку.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект мускуса из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Наделяет молоко сильным мускусным запахом.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект слизи из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Делает молоко слизистым.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект липкости из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Делает молоко липким.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект вязкости из молока.") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Делает молоко густым и вязким.") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Вместимость молока (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Запас молока (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Запас молока (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Запас молока (+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Запас молока (+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Запас молока (+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
				}
				break;

			case TF_MILK_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Придает молоку из вымени вкус пива.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Придает вымени вкуса шоколада.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Придает вымени вкус спермы.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Придает вымени вкус женских соков.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Придает вымени вкус молока.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("Делает вымя безвкусным.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Придает вымени вкус меда.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Придает вымени вкус мяты.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Придает вымени вкус вишни.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Придает вымени вкус ананаса.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Придает вымени вкус жвачки.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Придает вымени вкус клубники.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Придает вымени вкус ванили.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Придает вымени вкус кофе.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Придает вымени вкус чая.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Придает вымени вкус кленового сиропа.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Придает вымени вкус корицы.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Придает вымени вкус лимона.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Придает вымени вкус апельсина.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Придает вымени вкус винограда.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Придает вымени вкус дыни.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Придает вымени молоку вкус кокоса.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Придает вымени молоку вкус черники.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("Придает вымени молоку вкус банана.") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BANANA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает вызывающий привыкание эффект у вымени молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Добавляет вызывающий привыкание эффект вымени молоку.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект сильного содержания алкоголя из вымени молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Делает вымя молоко сильно алкогольным.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект алкоголя из вымени молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("Делает вымя молоко алкогольным.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект пузырьков из вымени молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Делает выменное молоко шипучим и пузырящимся.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает психоактивный эффект из выменного молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Добавляет психоактивный эффект в выменное молоко.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект минерального масла из выменного молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Добавляет эффект минерального масла в выменное молоко.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает мускусный эффект из выменного молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Наделяет выменное молоко сильным мускусным запахом.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект слизистости из выменного молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Делает выменное молоко слизистым.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект липкости из выменного молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Делает выменное молоко липким.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект вязкости у выменного молока.") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Делает выменное молоко густым и вязким.") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Запас выменного молока (" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Запас выменного молока (" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Запас выменного молока (" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Запас выменного молока (" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Запас выменного молока (" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Запас выменного молока (" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
						}
				}
				break;
					
			case TF_GIRLCUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("Придает девичьей кончить вкус пива.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("Придает девичьей смазке вкус шоколада.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("Придает девичьей смазке вкус спермы.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("Придает девичьей смазке вкус девичьей смазки.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("Придает девичьей смазке вкус молока.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("Девичья смазка становится безвкусной.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("Придает девичьей смазке вкус меда.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("Придает девичьей смазке вкус мяты.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("Придает девичьей смазке вкус вишни.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("Придает девичьей смазке вкус ананаса.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("Придает девичьей смазке вкус жвачки.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("Придает женским сокам вкус клубники.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("Придает женским сокам вкус ванили.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("Придает женским сокам вкус кофе.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("Придает женским сокам вкус чая.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("Придает женским сокам вкус кленового сиропа.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("Придает женским сокам вкус корицы.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("Придает женским сокам вкус лимона.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("Придает женским сокам вкус апельсина.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("Придает женским сокам вкус винограда.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("Придает женским сокам вкус дыни.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("Придает женской смазке вкус кокоса.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("Придает женской смазке вкус черники.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("Придает женской смазке вкус банана.") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BANANA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект привыкания у женской смазки.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("Добавляет эффект привыкания женской смазке.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект сильного содержания алкоголя из женской смазки.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("Делает женскую смазку сильно алкогольной.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект алкоголя из женской смазки.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("Делает женскую смазку алкогольной.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект пузырения у женской смазки.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("Делает девичью сперму шипучей и пузырящейся.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает психоактивный эффект из девичьей спермы.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("Добавляет психоактивный эффект в девичью сперму.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект минерального масла из девичьей спермы.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("Добавляет эффект минерального масла в девичью сперму.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает мускусный эффект из девичьей спермы.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("Наделяет девичью сперму сильным мускусным запахом.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект слизистости из девичьей спермы.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("Делает девичью сперму слизистой.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект липкости из девичьей спермы.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("Делает девичью сперму липкой.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("Убирает эффект вязкости у девичьей спермы.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("Делает девичью сперму густой и вязкой.") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)] Смазка влагалища (" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)] Смазка влагалища (" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)] Смазка влагалища (" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)] Вагинальная смазка (+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)] Вагинальная смазка (+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)] Вагинальная смазка (+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}
				}
				break;
			default:
				break;
		}

		return new RacialEffectUtil("Случайная нерасовая трансформация") {
			@Override
			public String applyEffect() {
				TFModifier mod = TFModifier.NONE, modSecondary = TFModifier.NONE;

				while (mod == TFModifier.NONE || modSecondary == TFModifier.NONE) {
					mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
					modSecondary = getRacialSecondaryModifiers(race, mod).get(Util.random.nextInt(getRacialSecondaryModifiers(race, mod).size()));
				}

				TFPotency pot = getRacialPotencyModifiers(race, mod, modSecondary).get(Util.random.nextInt(getRacialPotencyModifiers(race, mod, modSecondary).size()));

				return getRacialEffect(race, mod, modSecondary, pot, user, target).applyEffect();
			}
		};
	}

	private static RacialEffectUtil getAntennaTypeRacialEffectUtil(AbstractRace race, GameCharacter target, int index) {
		List<AbstractAntennaType> antennaTypes = RacialBody.valueOfRace(race).getAntennaTypes(true);
		AbstractAntennaType selectedAntennaType = index >= antennaTypes.size() ? AntennaType.NONE : antennaTypes.get(index);
		
		return new RacialEffectUtil("Растет "+selectedAntennaType.getTransformName()+" антенны.") {
			@Override public String applyEffect() { return target.setAntennaType(selectedAntennaType); } };
	}

}
