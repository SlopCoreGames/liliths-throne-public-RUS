package com.lilithsthrone.game.character.race;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.LegConfigurationAffinity;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.91
 * @version 0.4.0
 * @author tukaima, Innoxia
 */
public class Subspecies {
	
	// ---- TODO planned races ---- //
	
	//LIZARD_MORPH(Race.LIZARD_MORPH.getName(), Race.LIZARD_MORPH, RacialBody.LIZARD_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.LIZARD_MORPH.getName()),
	
	// AQUATIC:
	//TIGER_SHARK(Race.TIGER_SHARK.getName(), Race.TIGER_SHARK, RacialBody.TIGER_SHARK, SubspeciesPreference.FIVE_ABUNDANT,
	//		"An extremely aggressive variety of "+Race.SHARK_MORPH.getName()),
	
	// INSECTS:
	//BEE_MORPH(Race.BEE_MORPH.getName(), Race.BEE_MORPH, RacialBody.BEE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.BEE_MORPH.getName()),
	//ROYAL_BEE(Race.ROYAL_BEE.getName(), Race.BEE_MORPH, RacialBody.ROYAL_BEE, SubspeciesPreference.ZERO_NONE,
	//		"A bipedal "+Race.BEE_MORPH.getName()+" at the top of the bee-morph hierarchy"),
	//WASP_MORPH(Race.WASP_MORPH.getName(), Race.WASP_MORPH, RacialBody.WASP_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.WASP_MORPH.getName()),
	
//	FOX_TAILED("statusEffects/race/raceFoxMorph",
//			"pipefox",
//			"pipefoxes",
//			"pipefox-boy",
//			"pipefox-girl",
//			"pipefox-boys",
//			"pipefox-girls",
//			Race.FOX_MORPH,
//			PresetColour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph with a serpentine lower body, devoid of legs.",
//			Util.newHashMapOfValues(WorldType.DOMINION)) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			//apply fox coloring
//		}
//	};
	
//	FOX_TAUR("statusEffects/race/raceFoxMorph",
//			"yegan",
//			"yegans",
//			"yegan-boy",
//			"yegan-girl",
//			"yegan-boys",
//			"yegan-girls",
//			Race.FOX_MORPH,
//			PresetColour.RACE_FOX_MORPH,
//			SubspeciesPreference.FOUR_ABUNDANT,
//			"A fox-morph a bestial lower body that walks on four legs.",
//			Util.newHashMapOfValues(WorldType.DOMINION)) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			//apply fox coloring
//		}
//	};
	
	// ---- ---- //
	
	// HUMAN:
	public static AbstractSubspecies HUMAN = new AbstractSubspecies(true,
			4000,
			"innoxia_race_human_vanilla_water",
			"innoxia_race_human_bread_roll",
			"statusEffects/race/raceHuman",
			"statusEffects/race/raceBackground",
			"человеческими",
			"люди",
			"мужчина",
			"женщина",
			"мужчины",
			"женщины",
			null,
			Nocturnality.DIURNAL,
			"Люди обладают гораздо более высокой устойчивостью к возбуждающему воздействию арканы, чем любая другая раса.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null,
			"О людях",
			"О людях",
			"HUMAN_BASIC",
			"HUMAN_ADVANCED",
			Race.HUMAN,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_HUMAN,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Типичный человек.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HUMAN) {
				return 100;
			}
			return 0;
		}
	};

	// ANGEL:
	public static AbstractSubspecies ANGEL = new AbstractSubspecies(true,
			80000,
			"innoxia_race_angel_angels_tears",
			null,
			"statusEffects/race/raceAngel",
			"statusEffects/race/raceBackground",
			"ангел",
			"ангелы",
			"ангел",
			"ангел",
			"ангелы",
			"ангелы",
			null,
			Nocturnality.DIURNAL,
			"Будучи ангелом, [npc.nameIsFull] обладает высокой устойчивостью к возбуждающему воздействию арканы, а [npc.is] особенно искусна в сражениях с демонами. Однако [npc.Her] природный инстинкт защищать людей делает [npc.her] весьма уязвимой перед ними...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -100f),
					new Value<>(Attribute.RESISTANCE_LUST, 50f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DEMON), 50f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HUMAN), -50f)),
			null,
			"Защитники",
			"Защитники",
			"ANGEL_BASIC",
			"ANGEL_ADVANCED",
			Race.ANGEL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 0),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 0),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_ANGEL,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Типичный ангел.",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 10;
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"падший ангел",
					"падшие ангелы",
					"падший ангел",
					"падший ангел",
					"падшие ангелы",
					"падшие ангелы"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "падший ангел", false, false),
					applyNonBipedNameChange(body, "падший ангел", false, true),
					applyNonBipedNameChange(body, "падший ангел", false, false),
					applyNonBipedNameChange(body, "падший ангел", true, false),
					applyNonBipedNameChange(body, "падший ангел", false, true),
					applyNonBipedNameChange(body, "падший ангел", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ANGEL) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	// DEMON:
	public static AbstractSubspecies ELDER_LILIN = new AbstractSubspecies(false,
			1000000000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElderLilin",
			"statusEffects/race/raceBackground",
			"старшая лилин",
			"старшая лилин",
			"старшая лилин",
			"старшая лилин",
			"старшая лилин",
			"старшая лилин",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NameIsFull] одна из семи старших лилин и [npc.is] одно из самых могущественных существ в бытии. [npc.She] может превращать [npc.her] тело в любую форму, какую только [npc.she] [npc.verb(desire)], и [npc.has] обладает абсолютным мастерством над арканой.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 1000f),
					new Value<>(Attribute.MAJOR_ARCANE, 1000f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 1000f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 1000f),
					new Value<>(Attribute.MANA_MAXIMUM, 1000f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Безграничный)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к расовым трансформациям</b>"),
			"Отпрыск Lilith",
			"Отпрыски Lilith",
			"ELDER_LILIN_BASIC",
			"ELDER_LILIN_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_LILIN,
			SubspeciesPreference.ONE_LOW,
			"Одна из семи старших лилин.",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 10_000;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setSubspeciesOverride(ELDER_LILIN);
			return "";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			// As Elder Lilin will always have a SubspeciesOverride, there is no need to set any conditional weighting for it.
			return 0;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_ELDER_LILIN;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies LILIN = new AbstractSubspecies(false,
			500000000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceLilin",
			"statusEffects/race/raceBackground",
			"лилин",
			"лилин",
			"лилин",
			"лилин",
			"лилин",
			"лилин",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.Name] — лилин, а значит, она куда могущественнее обычного демона. [npc.She] может превращать [npc.her] тело в любую форму, какую пожелает [npc.she], и обладает огромным запасом арканной силы.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 500f),
					new Value<>(Attribute.MAJOR_ARCANE, 500f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 500f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 500f),
					new Value<>(Attribute.MANA_MAXIMUM, 500f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Безграничный)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к расовым трансформациям</b>"),
			"Отродья Lilith",
			"Отродья Lilith",
			"LILIN_BASIC",
			"LILIN_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_LILIN,
			SubspeciesPreference.ONE_LOW,
			"Лилин.",
			null,
			null, null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 5_000;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setSubspeciesOverride(LILIN);
			return "";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			// As Elder Lilin will always have a SubspeciesOverride, there is no need to set any conditional weighting for it.
			return 0;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_LILIN;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies DEMON = new AbstractSubspecies(true,
			120000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceДемон",
			"statusEffects/race/raceBackground",
			"демон",
			"демоны",
			"инкуб",
			"суккуб",
			"инкубы",
			"суккубы",
			null,
			Nocturnality.CATHEMERAL,
			"Благодаря тому, что демоны с легкостью подчиняют себе силу арканы, [npc.namePos] способности к чтению заклинаний представляют собой поистине ужасающую мощь!",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 30f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 100f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.DAMAGE_SPELLS, 75f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Демонический)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к расовым трансформациям</b>"),
			"Демоническое происхождение",
			"Демоническое происхождение'",
			"DEMON_BASIC",
			"DEMON_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Типичный демон.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB,  SubspeciesSpawnRarity.THREE)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 20;
		}
		@Override
		public String getFeralName(Body body) {
			if(body!=null) {
				AbstractRace r = body.getLegType().getRace();
				LegConfiguration legConfiguration = body.getLegConfiguration();

				switch(legConfiguration) {
					case BIPEDAL:
						return "демон";
					case ARACHNID:
					case CEPHALOPOD:
					case QUADRUPEDAL:
					case TAIL:
					case TAIL_LONG:
					case AVIAN:
					case WINGED_BIPED:
						return r==Race.HUMAN || r==Race.DEMON
								?Race.DEMON.getFeralName(new LegConfigurationAffinity(legConfiguration, getAffinity()), false)
								:"demonic-"+r.getName(body, true);
				}
			}
			return "демон";
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(Math.random()<0.25f && body.getLeg().getType().equals(LegType.DEMON_COMMON)) {
				sb.append(body.getLeg().setType(target, LegType.DEMON_HOOFED));
			}
			if(Math.random()<0.2f && body.getPenis().getType()!=PenisType.NONE) {
				sb.append(body.getPenis().getTesticle().setTesticleCount(target, 4));
			}
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}
			return sb.toString();
		}
		
		@Override
		public String getName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[0];
				}
				return super.getName(body);
			}
			return HALF_DEMON.getName(body);
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[1];
				}
				return super.getNamePlural(body);
			}
			return HALF_DEMON.getNamePlural(body);
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[2];
				}
				return super.getSingularMaleName(body);
			}
			return HALF_DEMON.getSingularMaleName(body);
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[3];
				}
				return super.getSingularFemaleName(body);
			}
			return HALF_DEMON.getSingularFemaleName(body);
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[4];
				}
				return super.getPluralMaleName(body);
			}
			return HALF_DEMON.getPluralMaleName(body);
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null || body.getRaceStage()==RaceStage.GREATER) {
				if(body !=null) {
					return demonLegConfigurationNames.get(body.getLegConfiguration())[5];
				}
				return super.getPluralFemaleName(body);
			}
			return HALF_DEMON.getPluralFemaleName(body);
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null || character.getRaceStage()==RaceStage.GREATER) {
				return super.getSVGString(character);
			}
			return HALF_DEMON.getSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null || character.getRaceStage()==RaceStage.GREATER) {
				return super.getSVGStringDesaturated(character);
			}
			return HALF_DEMON.getSVGStringDesaturated(character);
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies HALF_DEMON = new AbstractSubspecies(false,
			50000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceДемон",
			"statusEffects/race/raceBackground",
			"полудемон",
			"полудемоны",
			"полуинкуб",
			"полусуккуб",
			"полуинкубы",
			"полусуккубы",
			null,
			Nocturnality.CATHEMERAL,
			"Полудемоны почти так же способны, как и обычные демоны, обуздывать силу Арканы, и в результате, [npc.namePos] способности к чтению Заклинаний исключительно мощны!",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 20f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<>(Attribute.DAMAGE_LUST, 20f),
					new Value<>(Attribute.DAMAGE_SPELLS, 60f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'>Ограниченная самотрансформация</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к расовым трансформациям</b>"),
			"Демонические полукровки",
			"Демонических полукровок",
			"HALF_DEMON_BASIC",
			"HALF_DEMON_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 2)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_HALF_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Результат совокупления демона и партнера, не являющегося демоном.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 5;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}
			return sb.toString();
		}

		@Override
		public String getFeralName(Body body) {
			if(body!=null) {
				if(body.getHalfDemonSubspecies()!=null) {
					return body.getHalfDemonSubspecies().getFeralName(body);
				}
			}
			return DEMON.getFeralName(body);
		}
		
		@Override
		public String getName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[0];
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getNamePlural(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[1];
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getSingularMaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[2];
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getSingularFemaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[3];
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getPluralMaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[4];
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null || body.getHalfDemonSubspecies()==null) {
				return super.getPluralFemaleName(body);
			}
			return body.getHalfDemonSubspecies().getHalfDemonName(body)[5];
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null || character.getHalfDemonSubspecies()==null) {
				return Subspecies.HUMAN.getHalfDemonSVGString(character);
			}
			AbstractSubspecies coreSubspecies = character.getHalfDemonSubspecies();
			if(coreSubspecies==Subspecies.HALF_DEMON) {
				coreSubspecies = Subspecies.HUMAN;
			}
			return coreSubspecies.getHalfDemonSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null || character.getHalfDemonSubspecies()==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(character);
			}
			AbstractSubspecies coreSubspecies = character.getHalfDemonSubspecies();
			if(coreSubspecies==Subspecies.HALF_DEMON) {
				coreSubspecies = Subspecies.HUMAN;
			}
			return coreSubspecies.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				if(body.getRaceWeightMap().size()>1) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies IMP = new AbstractSubspecies(false,
			1000,
			"innoxia_race_imp_impish_brew",
			null,
			"statusEffects/race/raceБес",
			"statusEffects/race/raceBackground",
			"бес",
			"бесы",
			"бес",
			"бес",
			"бесы",
			"бесы",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NamePos] бесовское тело обладает глубокой, ненасытной жаждой секса. Из-за неотесанных манер бесов и их репутации обитателей самого дна общества, [npc.she] [npc.verb(find)] их трудно соблазнить...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 200f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f),
					new Value<>(Attribute.DAMAGE_LUST, -75f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Демонический)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к расовым трансформациям</b>"),
			"Бесовские исчадия",
			"Бесовских исчадий",
			"IMP_BASIC",
			"IMP_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 8),
					new Value<>(PerkCategory.LUST, 4),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_IMP,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Типичный бес.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 1;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setHeight(Height.NEGATIVE_TWO_MINIMUM.getRandomValue()));
			} else {
				body.setHeight(Height.NEGATIVE_TWO_MINIMUM.getRandomValue());
			}
			sb.append(body.getPenis().setPenisLength(target, 8+Util.random.nextInt(8))); // 3-7 inches
			sb.append(body.getWing().setSize(target,  WingSize.THREE_LARGE.getValue()));
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}
			
			return sb.toString();
		}
		@Override
		public boolean isShortStature() {
			return true;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_IMP;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				if(body.getHeight()==Height.NEGATIVE_TWO_MINIMUM) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies IMP_ALPHA = new AbstractSubspecies(false,
			1000,
			"innoxia_race_imp_impish_brew",
			null,
			"statusEffects/race/raceImpAlpha",
			"statusEffects/race/raceBackground",
			"альфа-бес",
			"альфа-бесы",
			"альфа-бес",
			"альфа-бес",
			"альфа-бесы",
			"альфа-бесы",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NamePos] бесовское тело обладает глубокой, ненасытной жаждой секса. Из-за неотесанных манер бесов и их репутации обитателей самого дна общества, [npc.she] [npc.verb(find)] их трудно соблазнить...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 200f),
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.DAMAGE_LUST, -50f)),
			Util.newArrayListOfValues(
					"[style.boldDemon(Демонический)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>",
					"<b style='color: "+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к расовым трансформациям</b>"),
			"Бесовские исчадия",
			"Бесовских исчадий",
			"IMP_BASIC",
			"IMP_ADVANCED",
			Race.DEMON,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 8),
					new Value<>(PerkCategory.LUST, 4),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_IMP,
			SubspeciesPreference.ONE_LOW,
			"Более мощная форма беса, ростом более [style.sizes(107)].",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 2;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();

			if(target!=null && target.getBody()!=null) {
				sb.append(target.setHeight(Height.NEGATIVE_ONE_TINY.getRandomValue()));
			} else {
				body.setHeight(Height.NEGATIVE_ONE_TINY.getRandomValue());
			}
			sb.append(body.getPenis().setPenisLength(target, 8+Util.random.nextInt(12))); // 3-8 inches
			sb.append(body.getWing().setSize(target, WingSize.THREE_LARGE.getValue()));
			if(body.getLegConfiguration()==LegConfiguration.BIPEDAL && body.getTailType()==TailType.DEMON_HORSE) {
				sb.append(body.setTailType(target, TailType.DEMON_COMMON));
			}
			
			return sb.toString();
		}
		@Override
		public boolean isShortStature() {
			return true;
		}
		@Override
		public AbstractAttribute getDamageMultiplier() {
			return Attribute.DAMAGE_IMP;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DEMON) {
				if(body.getHeight()==Height.NEGATIVE_ONE_TINY) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	// BOVINES:
	public static AbstractSubspecies COW_MORPH = new AbstractSubspecies(true,
			15000,
			"innoxia_race_cow_bubble_milk",
			"innoxia_race_cow_bubble_cream",
			"statusEffects/race/raceCowMorph",
			"statusEffects/race/raceBackground",
			"скотоморф",
			"скотоморфы",
			"бык-мальчик",
			"корова-девушка",
			"быки-мальчики",
			"коровы-девушки",
			new FeralAttributes(
					"скот",
					"скот",
					"бык",
					"корова",
					"быки",
					"коровы",
					LegConfiguration.QUADRUPEDAL,
					160,
					0,
					1,
					1,
					4, false),
			Nocturnality.DIURNAL,
			"Хотя тело [npc.namePos] обладает огромной силой и выносливостью, [npc.her] ум не самый сообразительный...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2f)),
			null,
			"Коровы-доярки",
			"Коров-доярок",
			"COW_MORPH_BASIC",
			"COW_MORPH_ADVANCED",
			Race.COW_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 10),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_COW_MORPH, SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная корова, известная как «скотоморф», когда передвигается на двух ногах, и «короватавр» или «быкотавр», когда её нижняя часть тела представляет собой дикую корову или быка.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"minotaur",
					"minotaurs",
					"minotaur",
					"minotaur",
					"minotaurs",
					"minotaurs"};
		}
		@Override
		protected String applyNonBipedNameChange(Body body, String baseName, boolean applyFeminineForm, boolean plural) {
			if(body.getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				return applyFeminineForm
						?("cowtaur"+(plural?"s":""))
						:("bulltaur"+(plural?"s":""));
			}
			return super.applyNonBipedNameChange(body, baseName, applyFeminineForm, plural);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.COW_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// CANIDS:
	public static AbstractSubspecies DOG_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"пёс-морф",
			"пёсы-морфы",
			"пес-мальчик",
			"собачья дева",
			"псы-морфы",
			"собачьи девы",
			new FeralAttributes(
					"коровьи",
					"псы",
					"коровьи",
					"сучка",
					"псы",
					"сучки",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.Name] всегда [npc.has] кучу энергии, и [npc.she] [npc.verb(get)] легко возбуждается от всего нового. [npc.She] также [npc.has] инстинктивное желание демонстрировать [npc.her] доминирование над невинными кот-морфами...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.CAT_MORPH), 5f)),
			null,
			"Собачья культура",
			"Культуры псовых",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_DOG_MORPH, SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная собака, известная как «пёс-морф», когда передвигается на двух ногах, и «собакотавр», когда её нижняя часть тела представляет собой туловище огромного дикого пса.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_BORDER_COLLIE = new AbstractSubspecies(false,
			24000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"бордер-колли-морф",
			"бордер-колли-морфы",
			"бордер-колли-парень",
			"бордер-колли-девушка",
			"бордер-колли-парни",
			"бордер-колли-девушки",
			new FeralAttributes(
					"бордер-колли",
					"бордер-колли",
					"собака бордер-колли",
					"сука бордер-колли",
					"собаки бордер-колли",
					"суки бордер-колли",
					LegConfiguration.QUADRUPEDAL,
					65,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] умнее среднего пса-морфа, и [npc.has] сильное желание пытаться пасти людей. [npc.She] также [npc.has] инстинктивное стремление проявлять [npc.her] доминирование над невинными овце-морфами...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.getRaceFromId("innoxia_sheep")), 25f)),
			null,
			"Собачья культура",
			"Культуры псовых",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOG_MORPH, SubspeciesPreference.TWO_AVERAGE,
			"Особенно энергичный и умный пёс-морф, напоминающий антропоморфную бордер-колли. Когда они передвигаются на двух ногах, их называют «бордер-колли-морфами», а когда их нижняя часть тела представляет собой туловище крупной дикой бордер-колли — «бордер-колли-таврами». Чтобы считаться бордер-колли-морфом, персонаж должен быть псом-морфом с прямыми или висячими ушами и пушистым чёрным мехом с белыми отметинами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.setCovering(target, BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false)));
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				if(Math.random()<0.5f) {
					sb.append(body.getEar().setType(target, EarType.DOG_MORPH_POINTED));
				} else {
					sb.append(body.getEar().setType(target, EarType.DOG_MORPH_FOLDED));
				}
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				if(body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						&& body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_WHITE
						&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.FLUFFY
						&& (body.getEar().getType()==EarType.DOG_MORPH_FOLDED || body.getEar().getType()==EarType.DOG_MORPH_POINTED)) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public String getPathName() {
			return "res/race/neverLucky/dog/border_collie";
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_DOBERMANN = new AbstractSubspecies(false,
			18000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorphDobermann",
			"statusEffects/race/raceBackground",
			"доберман",
			"доберманы",
			"доберман-парень",
			"доберман-девушка",
			"доберманы-парни",
			"доберман-девушки",
			new FeralAttributes(
					"доберман",
					"доберманы",
					"доберман-пёс",
					"доберман-сука",
					"доберман-псы",
					"доберман-суки",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] всегда готовы защитить своих [npc.she] [npc.verb(call)] [npc.her] друзей, и благодаря [npc.her] мощному телу, [npc.sheIs] способны на это. [npc.She] также [npc.has] инстинктивное желание демонстрировать [npc.her] доминирование над невинными кот-морфами...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.CAT_MORPH), 5f)),
			null,
			"Собачья культура",
			"Культуры псовых",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
			"Пёс-морф, напоминающий антропоморфного добермана. Когда они передвигаются на двух ногах, их называют «доберманами», а когда их нижняя часть тела представляет собой массивного дикого добермана — «добермантаврами». Чтобы считаться доберманом, персонаж должен быть пёсом-морфом с короткой чёрной шерстью и коричневыми, темно-коричневыми или рыжеватыми отметинами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour secondaryColour = PresetColour.COVERING_BROWN;
			double rand = Math.random();
			
			if(rand<0.3f) {
				secondaryColour = PresetColour.COVERING_TAN;
			} else if(rand<0.6f) {
				secondaryColour = PresetColour.COVERING_BROWN_DARK;
			}
			sb.append(body.setCovering(target, BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BLACK, false, secondaryColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, secondaryColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.DOG_MORPH) {
				sb.append(body.setCovering(target, BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED)));
			}
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				sb.append(body.getEar().setType(target, EarType.DOG_MORPH_POINTED));
			}
			if(body.getTail().getType()==TailType.DOG_MORPH) {
				sb.append(body.getTail().setType(target, TailType.DOG_MORPH_STUBBY));
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				
				if((body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						|| body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_JET_BLACK)
					&& (body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_BROWN
							|| body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_BROWN_DARK
							|| body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_TAN)
					&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
					&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.SHORT) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies DOG_MORPH_GERMAN_SHEPHERD = new AbstractSubspecies(false,
			18000,
			"innoxia_race_dog_canine_crush",
			"innoxia_race_dog_canine_crunch",
			"statusEffects/race/raceDogMorph",
			"statusEffects/race/raceBackground",
			"немецкая-овчарка-морф",
			"морфы немецких овчарок",
			"мальчик немецкая овчарка",
			"девочка немецкая овчарка",
			"мальчики немецкие овчарки",
			"девочки немецкие овчарки",
			new FeralAttributes(
					"немецкая овчарка",
					"немецкие овчарки",
					"пёс немецкой породы",
					"сука немецкой породы",
					"псы немецкой породы",
					"суки немецких овчарок",
					LegConfiguration.QUADRUPEDAL,
					70,
					0,
					1,
					5,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameHasFull] примитивная, волчья внешность, а также уровни силы и Интеллекта выше, чем у большинства других пёс-морфов.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f)),
			null,
			"Собачья культура",
			"Культуры псовых",
			"DOG_MORPH_BASIC",
			"DOG_MORPH_ADVANCED",
			Race.DOG_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
				"Сильный, умный и преданный подвид пёс-морфов, напоминающий антропоморфную немецкую овчарку. Когда они передвигаются на двух ногах, их называют «немецко-овчароморфами», а когда их нижняя часть тела представляет собой тело огромной дикой овчарки — «немецко-овчаротаврами». Чтобы считаться немецко-овчароморфом, персонаж должен быть пёс-морфом с торчащими ушами и пушистым чёрным мехом с рыжими подпалинами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
			new Value<>(WorldType.DOMINION, SubspeciesSpawnRarity.TEN),
			new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.setCovering(target, BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_TAN, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_TAN, false)));

			if(body.getEar().getType()==EarType.DOG_MORPH) {
				sb.append(body.getEar().setType(target, EarType.DOG_MORPH_POINTED));
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"hellhound",
					"hellhounds",
					"hellhound",
					"hellhound",
					"hellhounds",
					"hellhounds"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", false, false),
					applyNonBipedNameChange(body, "hellhound", true, false),
					applyNonBipedNameChange(body, "hellhound", false, true),
					applyNonBipedNameChange(body, "hellhound", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOG_MORPH) {
				AbstractBodyCoveringType canineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.CANINE_FUR;
				
				if(body.getCoverings().get(canineFur).getPrimaryColour()==PresetColour.COVERING_BLACK
						&& body.getCoverings().get(canineFur).getSecondaryColour()==PresetColour.COVERING_TAN
						&& body.getCoverings().get(canineFur).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(canineFur).getModifier() == CoveringModifier.FLUFFY
						&& body.getEar().getType()==EarType.DOG_MORPH_POINTED) {
						return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies WOLF_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_wolf_wolf_whiskey",
			"innoxia_race_wolf_meat_and_marrow",
			"statusEffects/race/raceWolfMorph",
			"statusEffects/race/raceBackground",
			"волк-морф",
			"волк-морфы",
			"волк-мальчик",
			"волк-девочка",
			"волки-мальчики",
			"волчихи",
			new FeralAttributes(
					"волчьи",
					"волки",
					"волчьи",
					"волчьи",
					"волки",
					"волки",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos] волчье тело очень сильное, и [npc.she] часто [npc.verb(get)] мощные порывы попытаться доминировать над людьми [npc.she] [npc.verb(meet)].",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.DAMAGE_UNARMED, 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.getRaceFromId("innoxia_sheep")), 25f)),
			null,
			"Затаившиеся люпины",
			"Рыщущих люпинов",
			"WOLF_MORPH_BASIC",
			"WOLF_MORPH_ADVANCED",
			Race.WOLF_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_WOLF_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфный волк, известный как «волк-морф», когда он передвигается на двух ногах, и «волктовр», когда его нижняя часть тела представляет собой типично огромного дикого волка.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "ауу-морф";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "ауу-морфы";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "ауу-бой";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "ауу-гёрл";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			List<Colour> naturalWolfFurColours = Util.newArrayListOfValues(
					PresetColour.COVERING_GREY,
					PresetColour.COVERING_BLACK,
					PresetColour.COVERING_JET_BLACK);
			
			Colour c = Util.randomItemFrom(naturalWolfFurColours);
			
			sb.append(body.setCovering(target, BodyCoveringType.LYCAN_FUR, new Covering(BodyCoveringType.LYCAN_FUR, c)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_LYCAN_FUR, new Covering(BodyCoveringType.HAIR_LYCAN_FUR, c)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_LYCAN_FUR, new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, c)));
			
			return sb.toString();
		}
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"vargr",
					"vargar",
					"vargr",
					"vargr",
					"vargar",
					"vargar"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "vargr", false, false),
					applyNonBipedNameChange(body, "vargr", false, true),
					applyNonBipedNameChange(body, "vargr", false, false),
					applyNonBipedNameChange(body, "vargr", true, false),
					applyNonBipedNameChange(body, "vargr", false, true),
					applyNonBipedNameChange(body, "vargr", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.WOLF_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies FOX_MORPH = new AbstractSubspecies(true,
			16000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"лис-морф",
			"лис-морфы",
			"лис-парень",
			"лис-девушка",
			"лисы-парни",
			"лисы-девушки",
			new FeralAttributes(
					"лисий",
					"лисы",
					"лисий",
					"лисица",
					"лисы",
					"лисицы",
					LegConfiguration.QUADRUPEDAL,
					45,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] очень хитрые и проворные, и [npc.sheIs] способны использовать [npc.her] обостренные чувства, чтобы находить подходящие моменты для атаки.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Скрытные вульпины",
			"Скрытные вульпины",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 6),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная лиса, известная как «лис-морф», когда она передвигается на двух ногах, и «фокставр», когда её нижняя часть тела представляет собой типично крупную дикую лису.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			AbstractSubspecies.applyFoxColoring(body);
			return "";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies FOX_MORPH_ARCTIC = new AbstractSubspecies(false,
			20000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"арктический лис-морф",
			"арктические лис-морфы",
			"мальчик-песец",
			"девочка-песец",
			"мальчики-песцы",
			"девочки-песцы",
			new FeralAttributes(
					"песец",
					"песцы",
					"песец",
					"песница",
					"песцы",
					"песницы",
					LegConfiguration.QUADRUPEDAL,
					30,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NameIsFull] очень хитрые и ловкие, а также [npc.sheIs] способны использовать [npc.her] обостренные чувства, чтобы находить подходящие моменты для атаки. Поскольку они [npc.sheIsFull] адаптированы к арктическим условиям, [npc.sheIs] также лучше сопротивляются входящему урону холодом.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null,
			"Скрытные вульпины",
			"Скрытные вульпины",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH_ARCTIC,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфная лиса с белым мехом, известная как «арктический лис-морф», когда передвигается на двух ногах, и «арктический лисотавр», когда нижняя часть тела представляет собой типично крупную дикую лису.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_PALE, false, PresetColour.SKIN_PALE, true)));
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_FOX_FUR, new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			body.updateCoverings(false, false, true, true);
			return sb.toString();
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)
							?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR)
							:BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				
				if(fox_fur.getPrimaryColour()==PresetColour.COVERING_WHITE
						&& (body.getEar().getType()!=EarType.FOX_MORPH_BIG)
						&& body.getTail().getType()!=TailType.FOX_MORPH_MAGIC) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies FOX_MORPH_FENNEC = new AbstractSubspecies(false,
			16000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"фенек-морф",
			"фенек-морфы",
			"фенек-мальчик",
			"фенек-девочка",
			"фенек-мальчики",
			"фенек-девочки",
			new FeralAttributes(
					"фенек-лис",
					"фенек-лисы",
					"фенек-лис",
					"фенек-лисица",
					"фенек-лисы",
					"фенек-лисицы",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] очень хитрые и проворные, и [npc.sheIs] способны использовать [npc.her] обостренные чувства, чтобы находить подходящие моменты для атаки.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Скрытные вульпины",
			"Скрытные вульпины",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_FOX_MORPH_FENNEC,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная лиса с характерными огромными ушами и мехом песочного, грязно-блондинистого или обесцвеченного светлого оттенка. Когда они ходят на двух ногах, их называют «фенек-морфами», а когда их нижняя часть тела представляет собой типично крупную дикую лисицу фенек — «фенектаврами».",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.DESERT, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			Colour fennecColour = Util.randomItemFrom(Util.newArrayListOfValues(PresetColour.COVERING_DIRTY_BLONDE, PresetColour.COVERING_BLEACH_BLONDE, PresetColour.COVERING_TAN));
			
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_OLIVE, false, PresetColour.SKIN_OLIVE, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.FOX_MORPH) {
				sb.append(body.setCovering(target, BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED)));
			}
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				sb.append(body.getEar().setType(target, EarType.FOX_MORPH_BIG));
			}
			
			return sb.toString();
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				if ((body.getEar().getType()==EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() != TailType.FOX_MORPH_MAGIC) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public String getPathName() {
			return "res/race/neverLucky/fox/fennec";
		}
		@Override
		public int getIconSize() {
			return 70;
		}
	};
	
	public static AbstractSubspecies FOX_ASCENDANT = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"ёко",
			"ёко",
			"парень-ёко",
			"девушка-ёко",
			"парни-ёко",
			"девушки-ёко",
			new FeralAttributes(
					"лиса-ёко",
					"лисы-ёко",
					"лиса-ёко",
					"лисица-ёко",
					"лисы-ёко",
					"лисицы-ёко",
					LegConfiguration.QUADRUPEDAL,
					50,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null,
			"Девятихвостая",
			"Девятихвостая",
			"YOUKO_BASIC",
			"YOUKO_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная лиса, одна из двух лилин-ёко, которая трансформировалась, чтобы обрести хвосты, наполненные арканой.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public float getChanceForMaleOffspring() {
			return 0.1f; // same as demons
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			AbstractSubspecies.applyFoxColoring(body);
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				sb.append(body.getTail().setTailCount(target, 1, true)); // Youko should always spawn with 1 tail
				sb.append(body.getTail().setType(target, TailType.FOX_MORPH_MAGIC));
			}
			return sb.toString();
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()==1) {
				return UtilText.parse(character, "[npc.NameIsFull] ёко, и хотя [npc.she] обладает лишь [npc.has] одним арканным хвостом, [npc.sheIsFull] всё же остается способным заклинателем.");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] ёко, которая, служа одной из лилин-ёко, [npc.she] [npc.has] обрела [npc.tailMaxCount] арканный хвост"+(character.getMaxTailCount()==1?"":"s")+".");
			}
		}

		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			//TODO move the variable racial bonuses out of Subspecies and put them in the special youko perks
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return youkoIconMap.get(9);
			}
			return getBipedBackground(youkoIconMap.get(character.getMaxTailCount()), character, this.getColour(character));
		}

		@Override
		public String getBookSVGString() {
			return youkoIconMapNoBackground.get(9);
		}
		
		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return youkoDesaturatedIconMap.get(9);
			}
			return getBipedBackground(youkoDesaturatedIconMap.get(character.getMaxTailCount()), character, PresetColour.BASE_GREY);
		}
		
		@Override
		public String getHalfDemonSVGString(GameCharacter character) {
			if(character!=null && character.getSubspeciesOverride()!=null && character.getSubspeciesOverride().equals(Subspecies.DEMON)) {
				return super.getHalfDemonSVGString(character);
			} else {
				return getBipedBackground(youkoHalfDemonIconMap.get(character.getMaxTailCount()), character, PresetColour.RACE_HALF_DEMON);
			}
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				if(body.getTail().getType()==TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT);
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies FOX_ASCENDANT_ARCTIC = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"арктическая ёко",
			"арктическая ёко",
			"арктический ёко-мальчик",
			"арктическая девочка-ёко",
			"арктические мальчики-ёко",
			"арктические девочки-ёко",
			new FeralAttributes(
					"арктическая лисица-ёко",
					"арктические лисицы-ёко",
					"арктическая лисица-ёко",
					"арктическая лисица-ёко",
					"арктические лисицы-ёко",
					"арктические лисицы-ёко",
					LegConfiguration.QUADRUPEDAL,
					40,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null, "Девятихвостая",
			"Девятихвостая",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная арктическая лисица, одна из двух лилин-ёко, которая трансформировалась, чтобы получить пропитанные Арканой хвосты.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public float getChanceForMaleOffspring() {
			return 0.1f; // same as demons
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_PALE, false, PresetColour.SKIN_PALE, true)));
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_FOX_FUR, new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				sb.append(body.getTail().setTailCount(target, 1, true)); // Youko should always spawn with 1 tail
				sb.append(body.getTail().setType(target, TailType.FOX_MORPH_MAGIC));
			}
			body.updateCoverings(false, false, true, true);
			return sb.toString();
		}
		
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull] арктический лис-морф, [npc.his] служащая определенной лилин, что даровало ей [npc.him] [npc.tailMaxCount] аркана хвостов"+(character.getMaxTailCount()==1?"":"s")+".");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] арктический лис-морф, [npc.his] огромное количество аркана хвостов — знак [npc.her] бесконечной преданности определенной лилин.");
			}
		}

		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}
		
		@Override
		public String getSVGString(GameCharacter character) {
			return FOX_ASCENDANT.getSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			return FOX_ASCENDANT.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				AbstractBodyCoveringType foxFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FOX_FUR;
				Covering fox_fur = body.getCoverings().get(foxFur);
				
				if(fox_fur.getPrimaryColour()==PresetColour.COVERING_WHITE
						&& (body.getEar().getType()!=EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT_ARCTIC);
					return 250;
				}
			}
			return 0;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies FOX_ASCENDANT_FENNEC = new AbstractSubspecies(false,
			15000,
			"innoxia_race_fox_vulpines_vineyard",
			"innoxia_race_fox_chicken_pot_pie",
			"statusEffects/race/raceFoxMorph",
			"statusEffects/race/raceBackground",
			"фенек-кицунэ",
			"фенек-кицунэ",
			"фенек-лисица-мальчик",
			"девушка-фенек-ёкай",
			"фенек-лисы-парни",
			"девушки-фенек-ёкаи",
			new FeralAttributes(
					"фенек-ёко-лис",
					"фенек-ёко-лисы",
					"фенек-ёко-лис",
					"фенек-ёко-лисица",
					"фенек-ёко-лисы",
					"фенек-ёко-лисицы",
					LegConfiguration.QUADRUPEDAL,
					30,
					0,
					1,
					4,
					1, false),
			Nocturnality.CATHEMERAL,
			"",
			null,
			null, "Девятихвостая",
			"Девятихвостая",
			"FOX_MORPH_BASIC",
			"FOX_MORPH_ADVANCED",
			Race.FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 10)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфный фенек, один из двух лилин ёко, который трансформировался, чтобы обрести наделенные Арканой хвосты.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.YOUKO_FOREST, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_SPAWN_PREFERENCE)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 40;
		}
		@Override
		public float getChanceForMaleOffspring() {
			return 0.1f; // same as demons
		}
		@Override
		public int getBaseSlaveValue(GameCharacter character) {
			if(character==null) {
				return 15000;
			} else {
				return 15000 * character.getMaxTailCount();
			}
		}
		@Override
		public boolean isAbleToSelfTransform() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour fennecColour = PresetColour.COVERING_BLEACH_BLONDE;
			double rand = Math.random();
			if(rand<0.5f) {
				fennecColour = PresetColour.COVERING_DIRTY_BLONDE;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_OLIVE, false, PresetColour.SKIN_OLIVE, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getPenis().getType()==PenisType.FOX_MORPH) {
				sb.append(body.setCovering(target, BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED)));
			}
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				sb.append(body.getEar().setType(target, EarType.FOX_MORPH_BIG));
			}
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				sb.append(body.getTail().setTailCount(target, 1, true)); // Youko should always spawn with 1 tail
				sb.append(body.getTail().setType(target, TailType.FOX_MORPH_MAGIC));
			}
			return sb.toString();
		}
		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character.getMaxTailCount()<9) {
				return UtilText.parse(character, "[npc.NameIsFull] лис-морф, [npc.his] служащая определенной лилин, получившая [npc.him] [npc.tailMaxCount] арканный хвост"+(character.getMaxTailCount()==1?"":"s")+".");
			} else {
				return UtilText.parse(character, "[npc.NameIsFull] лис-морф, [npc.his] огромное количество арканных хвостов — знак [npc.her] бесконечной преданности определенной лилин.");
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
			if(character!=null && character.getMaxTailCount()<9) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, (float) (10*character.getMaxTailCount())),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, (float) (10 + 5*character.getMaxTailCount())),
						new Value<>(Attribute.CRITICAL_DAMAGE, (float) (20 + 5*character.getMaxTailCount())));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
						new Value<>(Attribute.MAJOR_ARCANE, 100f),
//						new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
						new Value<>(Attribute.SPELL_COST_MODIFIER, 60f),
						new Value<>(Attribute.CRITICAL_DAMAGE, 100f));
			}
		}
		@Override
		public String getSVGString(GameCharacter character) {
			return FOX_ASCENDANT.getSVGString(character);
		}
		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			return FOX_ASCENDANT.getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.FOX_MORPH) {
				if ((body.getEar().getType()==EarType.FOX_MORPH_BIG)
						&& body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					body.setSubspeciesOverride(Subspecies.FOX_ASCENDANT_FENNEC);
					return 250;
				}
			}
			return 0;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	// FELINES:
	public static AbstractSubspecies CAT_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"кот-морф",
			"кот-морфы",
			"кот-мальчик",
			"кот-девочка",
			"кот-мальчики",
			"кот-девочки",
			new FeralAttributes(
					"кошачьи",
					"кошки",
					"кот",
					"кошачьи",
					"коты",
					"кошки",
					LegConfiguration.QUADRUPEDAL,
					25,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos] обладает невероятной ловкостью, а [npc.she] [npc.verb(possess)] молниеносными рефлексами. [npc.She] также [npc.has] инстинктивное желание демонстрировать [npc.her] доминирование над невинными гарпиями и грызуноморфами...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"Любопытные кошечки",
			"Любопытные кошечки",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная кошка, известная как «кот-морф», когда передвигается на двух ногах, и «кот-тавр», когда её нижняя часть тела представляет собой типично крупного дикого кота.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "кошечка-морф";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "кошечка-морфы";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "котте-бой";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "кошкодевочка";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies CAT_MORPH_LYNX = new AbstractSubspecies(false,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"рысь-морф",
			"рысь-морфы",
			"рысь-мальчик",
			"рысь-девочка",
			"рыси-мальчики",
			"рыси-девочки",
			new FeralAttributes(
					"рысь",
					"рыси",
					LegConfiguration.QUADRUPEDAL,
					60,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos] обладает невероятной ловкостью, а [npc.she] [npc.verb(possess)] молниеносными рефлексами. [npc.She] также [npc.has] инстинктивное желание демонстрировать [npc.her] доминирование над невинными гарпиями и грызуноморфами...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"Любопытные кошечки",
			"Любопытные кошечки",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_LYNX,
			SubspeciesPreference.TWO_AVERAGE,
			"Антропоморфная рысь; когда она передвигается на двух ногах, её называют «рысь-морфом», а когда её нижняя часть тела представляет собой типично крупную дикую рысь — «рысь-тавром». Чтобы считаться рысь-морфом, персонаж должен быть кот-морфом с пушистым мехом, кисточками на ушах, коротким хвостом и типом волос с боковой пушистостью.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour primaryColor = PresetColour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				primaryColor = PresetColour.COVERING_TAN;
			} else if(rand<0.6f) {
				primaryColor = PresetColour.COVERING_BROWN_DARK;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, PresetColour.COVERING_BLACK, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, PresetColour.COVERING_BLACK, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getEar().setType(target, EarType.CAT_MORPH_TUFTED));
			}
			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getTail().setType(target, TailType.CAT_MORPH_SHORT));
			}
			if(body.getHair().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getHair().setType(target, HairType.CAT_MORPH_SIDEFLUFF));
			}
			return sb.toString();
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
				
				if((faceType == FaceType.CAT_MORPH || faceType == FaceType.HUMAN)
						&& body.getHair().getType() == HairType.CAT_MORPH_SIDEFLUFF
						&& body.getEar().getType()==EarType.CAT_MORPH_TUFTED
						&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.FLUFFY
						&& body.getTail().getType()==TailType.CAT_MORPH_SHORT) {
					return 150;
				}
			}
			return 0;
		}
	};

	public static AbstractSubspecies CAT_MORPH_CHEETAH = new AbstractSubspecies(false,
			16000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackgroundCheetah",
			"гепард-морф",
			"гепард-морфы",
			"гепард-мальчик",
			"гепард-девочка",
			"гепарды-мальчики",
			"гепарды-девочки",
			new FeralAttributes(
					"гепард",
					"гепарды",
					"гепард",
					"гепард",
					"гепарды",
					"гепарды",
					LegConfiguration.QUADRUPEDAL,
					80,
					0,
					1,
					4,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] чрезвычайно быстр и короткими рывками способен развивать скорость, намного превышающую скорость любой другой расы.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			Util.newArrayListOfValues("[style.boldExcellent(100%)] шанс на побег против не-гепард-морфов"),
			"Любопытные кошечки",
			"Любопытные кошечки",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_CHEETAH,
			SubspeciesPreference.TWO_AVERAGE,
				"Антропоморфный гепард, известный как «кот-морф», когда он передвигается на двух ногах, и «гепартавр», когда его нижняя часть тела представляет собой типично крупного дикого гепарда. Чтобы считаться кот-морфом, персонаж должен быть кот-морфом с короткой пятнистой шерстью и не относиться к другим видам кошачьих морфов.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			Colour primaryColor = PresetColour.COVERING_ORANGE;
			double rand = Math.random();
			if(rand<0.35f) {
				primaryColor = PresetColour.COVERING_TAN;
			}
			Colour secondaryColor = PresetColour.COVERING_BLACK;
			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false)));
			body.updateCoverings(true, true, true, true);
			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getTail().setType(target, TailType.CAT_MORPH));
			}
			
			// Body size adjustment
			if(body.getBreast().getRawSizeValue()>CupSize.B.getMeasurement()) {
				rand = Math.random();
				if(rand<0.35f) {
					sb.append(body.getBreast().setSize(target, CupSize.B.getMeasurement()));
				} else if(rand<0.70f) {
					sb.append(body.getBreast().setSize(target, CupSize.A.getMeasurement()));
				} else {
					sb.append(body.getBreast().setSize(target, CupSize.AA.getMeasurement()));
				}
			}
			
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setBodySize(BodySize.ZERO_SKINNY.getMedianValue()));
				sb.append(target.setMuscle(Muscle.FOUR_RIPPED.getMedianValue()));
				
			} else {
				body.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
				body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			}
			
			return sb.toString();
		}

		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				AbstractFaceType faceType = body.getFace().getType();
				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
				
				if((faceType == FaceType.CAT_MORPH || faceType == FaceType.HUMAN)
						&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
						&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
						&& body.getTail().getType()==TailType.CAT_MORPH) {
					return 150;
				}
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies CAT_MORPH_CARACAL = new AbstractSubspecies(false,
			12000,
			"innoxia_race_cat_felines_fancy",
			"innoxia_race_cat_kittys_reward",
			"statusEffects/race/raceCatMorph",
			"statusEffects/race/raceBackground",
			"каракал-морф",
			"каракал-морфы",
			"каракал-мальчик",
			"каракал-девочка",
			"каракал-мальчики",
			"каракал-девочки",
			new FeralAttributes(
					"каракал",
					"каракалы",
					"каракал",
					"каракал",
					"каракалы",
					"каракалы",
					LegConfiguration.QUADRUPEDAL,
					50,
					0,
					1,
					4,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos] обладает невероятной ловкостью, а [npc.she] [npc.verb(possess)] молниеносными рефлексами. [npc.She] также [npc.has] инстинктивное желание демонстрировать [npc.her] доминирование над невинными гарпиями и грызуноморфами...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HARPY), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.RAT_MORPH), 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.SQUIRREL_MORPH), 5f)),
			null,
			"Любопытные кошечки",
			"Любопытные кошечки",
			"CAT_MORPH_BASIC",
			"CAT_MORPH_ADVANCED",
			Race.CAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CAT_MORPH_CARACAL,
			SubspeciesPreference.TWO_AVERAGE,
				"Антропоморфный каракал, известный как «каракал-морф», когда он передвигается на двух ногах, и «каракалтавр», когда его нижняя часть тела представляет собой типично крупного дикого каракала. Чтобы считаться каракал-морфом, персонаж должен быть кот-морфом с кисточками на ушах.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(body.getEar().getType().getRace()==Race.CAT_MORPH) {
				sb.append(body.getEar().setType(target, EarType.CAT_MORPH_TUFTED));
			}
			return sb.toString();
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.CAT_MORPH) {
				if(body.getEar().getType()==EarType.CAT_MORPH_TUFTED) {
					return 140;
				}
			}
			return 0;
		}
	};

//	public static AbstractSubspecies CAT_MORPH_LEOPARD_SNOW = new AbstractSubspecies(false,
//			18000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundSnowLeopard",
//			"snow leopard-morph",
//			"snow leopard-morphs",
//			"snow leopard-boy",
//			"snow leopard-girl",
//			"snow leopard-boys",
//			"snow leopard-girls",
//			new FeralAttributes(
//					"snow leopard",
//					"snow leopards",
//					"snow leopard",
//					"snow leopardess",
//					"snow leopards",
//					"snow leopardesses",
//					LegConfiguration.QUADRUPEDAL,
//					60,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike."
//					+ " [npc.She] also [npc.has] a very high resistance to both natural and arcane cold.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 5f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
//					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LEOPARD_SNOW,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic snow leopard, known as a 'snow leopard-morph' when bipedal, and a 'snow leopardtaur' when the lower body is that of a typically-oversized feral snow leopard."
//				+ " To be identified as a snow leopard-morph, a character must be a cat-morph that has fluffy spotted fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE),
//					new Value<>(WorldRegion.MOUNTAINS, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE),
//					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.ZERO_EXTREMELY_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_WHITE;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.3f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			} else if(rand<0.6f) {
//				primaryColor = PresetColour.COVERING_GREY;
//			} else if(rand<0.65f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//				body.getTail().setTailGirth(null, PenetrationGirth.FOUR_THICK.getValue());
//			}
//			
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.FLUFFY
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_LEOPARD = new AbstractSubspecies(false,
//			16000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundLeopard",
//			"leopard-morph",
//			"leopard-morphs",
//			"leopard-boy",
//			"leopard-girl",
//			"leopard-boys",
//			"leopard-girls",
//			new FeralAttributes(
//					"leopard",
//					"leopards",
//					"leopard",
//					"leopardess",
//					"leopard",
//					"leopardesses",
//					LegConfiguration.QUADRUPEDAL,
//					70,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is very strong and agile, and [npc.sheIsFull] capable of great feats of strength and stealth alike. [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 5f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
//					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 10),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LEOPARD,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic leopard, known as a 'leopard-morph' when bipedal, and a 'leopardtaur' when the lower body is that of a typically-oversized feral leopard."
//			+ " To be identified as a leopard-morph, a character must be a cat-morph that has short spotted fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_SANDY;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.05f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.SPOTTED
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_LION = new AbstractSubspecies(false,
//			24000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackground",
//			"lion-morph",
//			"lion-morphs",
//			"lion-boy",
//			"lion-girl",
//			"lion-boys",
//			"lion-girls",
//			new FeralAttributes(
//					"lion",
//					"lions",
//					"lion",
//					"lioness",
//					"lions",
//					"lionesses",
//					LegConfiguration.QUADRUPEDAL,
//					100,
//					0,
//					1,
//					4,
//					1,
//					true),
//			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength. [npc.She] also [npc.has] a high resistance to both natural and arcane heat.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 15f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
//					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_LION,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic lion, known as a 'lion-morph' when bipedal, and a 'liontaur' when the lower body is that of a feral lion."
//				+ " To be identified as a lion-morph, a character must be a cat-morph that has short fur, tufted tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_TAN;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.05f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			else if(rand<0.1f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH_TUFTED);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getModifier() == CoveringModifier.SHORT
//					&& body.getTail().getType()==TailType.CAT_MORPH_TUFTED) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//	};
//	
//	public static AbstractSubspecies CAT_MORPH_TIGER = new AbstractSubspecies(false,
//			30000,
//			"innoxia_race_cat_felines_fancy",
//			"innoxia_race_cat_kittys_reward",
//			"statusEffects/race/raceCatMorph",
//			"statusEffects/race/raceBackgroundTiger",
//			"tiger-morph",
//			"tiger-morphs",
//			"tiger-boy",
//			"tiger-girl",
//			"tiger-boys",
//			"tiger-girls",
//			new FeralAttributes(
//					"tiger",
//					"tigers",
//					"tiger",
//					"tigress",
//					"tigers",
//					"tigresses",
//					LegConfiguration.QUADRUPEDAL,
//					100,
//					0,
//					1,
//					4,
//					1, false),
//			"[npc.NamePos] body is extremely strong, and [npc.sheIsFull] capable of great feats of strength.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
//					new Value<>(Attribute.DAMAGE_UNARMED, 25f),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f)),
//			null,
//			"Curious Kitties",
//			"Curious Kitties",
//			"CAT_MORPH_BASIC",
//			"CAT_MORPH_ADVANCED",
//			Race.CAT_MORPH,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 20),
//					new Value<>(PerkCategory.LUST, 2),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.RACE_CAT_MORPH_TIGER,
//			SubspeciesPreference.TWO_AVERAGE,
//			"An anthropomorphic tiger, known as a 'tiger-morph' when bipedal, and a 'tigertaur' when the lower body is that of a feral tiger."
//				+ " To be identified as a tiger-morph, a character must be a cat-morph that has striped fur, normal tail and panther face.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.TWO_RARE),
//					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			Colour primaryColor = PresetColour.COVERING_ORANGE;
//			Colour secondaryColor = PresetColour.COVERING_BLACK;
//			double rand = Math.random();
//			if(rand<0.6f) {
//				primaryColor = Util.randomItemFrom(Util.newArrayListOfValues(PresetColour.COVERING_TAN, PresetColour.COVERING_AUBURN, PresetColour.COVERING_AMBER));
//			} else if(rand<0.12f) {
//				primaryColor = PresetColour.COVERING_WHITE;
//			} else if(rand<0.16f) {
//				primaryColor = PresetColour.COVERING_BLACK;
//			}
//			sb.append(body.setCovering(target, BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.STRIPED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
//			body.updateCoverings(true, true, true, true);
//			if(body.getFace().getType()==FaceType.CAT_MORPH) {
//				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
//			}
//			if(body.getTail().getType().getRace()==Race.CAT_MORPH) {
//				body.getTail().setType(null, TailType.CAT_MORPH);
//			}
//			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
//			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
//		}
//
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.CAT_MORPH) {
//				AbstractFaceType faceType = body.getFace().getType();
//				AbstractBodyCoveringType felineFur = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FUR):BodyCoveringType.FELINE_FUR;
//				
//				if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
//					&& body.getCoverings().get(felineFur).getPattern() == CoveringPattern.STRIPED
//					&& body.getTail().getType()==TailType.CAT_MORPH) {
//					return 150;
//					
//				}
//			}
//			return 0;
//		}
//	};
	
	// EQUINES:
	public static AbstractSubspecies HORSE_MORPH = new AbstractSubspecies(true,
			18000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"конь-морф",
			"конь-морфы",
			"жеребёнок-мальчик",
			"жеребёнок-девочка",
			"жеребята-мальчики",
			"жеребята-девочки",
			new FeralAttributes(
					"лошади",
					"лошади",
					"жеребец",
					"кобыла",
					"жеребцы",
					"мары",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Хотя тело [npc.namePos] обладает поразительной силой и скоростью, [npc.sheIs] не самая сообразительная натура, и [npc.verb(struggle)] куда больше остальных, когда дело доходит до управления арканой.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null,
			"Энциклопедия лошадей",
			"Энциклопедии лошадей",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_HORSE_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная двуногая лошадь.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"nightmare",
					"nightmares",
					"nightmare",
					"nightmare",
					"nightmares",
					"nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "nightmare", false, false),
					applyNonBipedNameChange(body, "nightmare", false, true),
					applyNonBipedNameChange(body, "nightmare", false, false),
					applyNonBipedNameChange(body, "nightmare", true, false),
					applyNonBipedNameChange(body, "nightmare", false, true),
					applyNonBipedNameChange(body, "nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_UNICORN = new AbstractSubspecies(false,
			30000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"морф-единорога",
			"морфы-единороги",
			"мальчик-единорог",
			"девушка-единорог",
			"мальчики-единороги",
			"девушки-единороги",
			new FeralAttributes(
					"единорожьи",
					"единороги",
					"жеребец-единорог",
					"кобыла-единорог",
					"жеребцы-единороги",
					"кобылы-единороги",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Хотя физически они слабее обычного конь-морфа, [npc.nameHasFull] обладают особой связью с арканой, и [npc.is] способны творить множество заклинаний, прежде чем истощится [npc.her] аура.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Энциклопедия лошадей",
			"Энциклопедии лошадей",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 3),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 3)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 4),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.RACE_UNICORN,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфная двуногая лошадь, у которой из лба растет единственный магический рог.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.DOMINION, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.getWing().setType(target, WingType.NONE));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"unicorn-nightmare",
					"unicorn-nightmares",
					"unicorn-nightmare",
					"unicorn-nightmare",
					"unicorn-nightmares",
					"unicorn-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "unicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "unicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "unicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "unicorn-nightmare", true, false),
					applyNonBipedNameChange(body, "unicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "unicorn-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(!body.getHorn().getType().equals(HornType.NONE) && body.getHorn().getHornRows()==1 && body.getHorn().getHornsPerRow()==1) {
					return 150;
				}
			}
			return 0;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_PEGASUS = new AbstractSubspecies(false,
			24000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"морф пегаса",
			"морфы пегаса",
			"пегас-мальчик",
			"пегас-девочка",
			"пегасы-мальчики",
			"пегасы-девочки",
			new FeralAttributes(
					"пегас",
					"пегасы",
					"пегас-жеребец",
					"пегас-кобыла",
					"пегасы-жеребцы",
					"пегасы-кобылы",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Хотя физически они слабее обычного конь-морфа, [npc.nameIsFull] гораздо более ловкие, что позволяет [npc.herHim] наносить удары по [npc.her] уязвимым точкам врагов.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 30f)),
			null,
			"Энциклопедия лошадей",
			"Энциклопедии лошадей",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_PEGASUS,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфная двуногая лошадь с парой пернатых крыльев, растущих из спины.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.THREE_LARGE.getValue()));
			
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"pegasus-nightmare",
					"pegasus-nightmares",
					"pegasus-nightmare",
					"pegasus-nightmare",
					"pegasus-nightmares",
					"pegasus-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "pegasus-nightmare", false, false),
					applyNonBipedNameChange(body, "pegasus-nightmare", false, true),
					applyNonBipedNameChange(body, "pegasus-nightmare", false, false),
					applyNonBipedNameChange(body, "pegasus-nightmare", true, false),
					applyNonBipedNameChange(body, "pegasus-nightmare", false, true),
					applyNonBipedNameChange(body, "pegasus-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getWing().getType()==WingType.FEATHERED) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	public static AbstractSubspecies HORSE_MORPH_ALICORN = new AbstractSubspecies(false,
			60000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"аликорн-морф",
			"аликорн-морфы",
			"мальчик-аликорн",
			"девочка-аликорн",
			"мальчики-аликорны",
			"девочки-аликорны",
			new FeralAttributes(
					"аликорн",
					"аликорны",
					"жеребец-аликорн",
					"кобыла-аликорн",
					"жеребцы-аликорны",
					"кобылы-аликорны",
					LegConfiguration.QUADRUPEDAL,
					175,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Обладая как пернатыми крыльями, так и рогом единорога, [npc.nameIsFull] классифицируется как могущественный аликорн, и [npc.verb(find)] ему почти без труда дается использование заклинаний.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 75f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"Энциклопедия лошадей",
			"Энциклопедии лошадей",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 3)),
			PresetColour.RACE_ALICORN,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфная двуногая лошадь, у которой из спины растут пара пернатых крыльев, а из лба — единственный магический рог.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.THREE_LARGE.getValue()));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"alicorn-nightmare",
					"alicorn-nightmares",
					"alicorn-nightmare",
					"alicorn-nightmare",
					"alicorn-nightmares",
					"alicorn-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "alicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "alicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "alicorn-nightmare", false, false),
					applyNonBipedNameChange(body, "alicorn-nightmare", true, false),
					applyNonBipedNameChange(body, "alicorn-nightmare", false, true),
					applyNonBipedNameChange(body, "alicorn-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getWing().getType()==WingType.FEATHERED
						&& !body.getHorn().getType().equals(HornType.NONE)
						&& body.getHorn().getHornRows()==1
						&& body.getHorn().getHornsPerRow()==1) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies CENTAUR = new AbstractSubspecies(false,
			25000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"кентавр",
			"кентавры",
			"кентавр",
			"кентаврка",
			"кентавры",
			"кентаврки",
			null,
			Nocturnality.DIURNAL,
			"Благодаря нижней части тела в виде лошади, [npc.nameIsFull] способная развивать огромную скорость, и [npc.is] также способная наносить значительный физический урон.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 35f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"Кентавры и прочие",
			"Кентавры и прочие",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_CENTAUR,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Любое существо, обладающее диким четырехногим нижним телом лошади, классифицируется как кентавр.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-centaur",
					"demonic-centaurs",
					"demonic-centaur",
					"demonic-centauress",
					"demonic-centaurs",
					"demonic-centauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {	
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				return 1000;
			}
			return 0;
		}
	};
	public static AbstractSubspecies PEGATAUR = new AbstractSubspecies(false,
			35000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"пегатавр",
			"пегатавры",
			"пегатавр",
			"пегатаврка",
			"пегатавры",
			"пегатавры",
			null,
			Nocturnality.DIURNAL,
			"Хотя физически они слабее обычных кентавров, [npc.nameIsFull] гораздо более ловкие, что позволяет [npc.herHim] избегать входящего урона.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Кентавры и прочие",
			"Кентавры и прочие",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_PEGATAUR,
			SubspeciesPreference.ONE_LOW,
			"Любая особь с диким крылатым четырехногим нижним телом лошади классифицируется как пегатавр.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}
			
			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.FOUR_HUGE.getValue()));
			
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-pegataur",
					"demonic-pegataurs",
					"demonic-pegataur",
					"demonic-pegatauress",
					"demonic-pegataurs",
					"demonic-pegatauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(body.getWing().getType()==WingType.FEATHERED) {
					return 1150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	public static AbstractSubspecies UNITAUR = new AbstractSubspecies(false,
			50000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"унитавр",
			"унитавры",
			"унитавр",
			"унитаврка",
			"унитавры",
			"юнитавры",
			null,
			Nocturnality.DIURNAL,
			"Хотя физически они слабее обычного конь-морфа, [npc.nameHasFull] обладают особой связью с арканой, и [npc.is] способны творить множество заклинаний, прежде чем истощится [npc.her] аура.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Кентавры и прочие",
			"Кентавры и прочие",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_UNICORN,
			SubspeciesPreference.ONE_LOW,
			"Любое существо, обладающее диким четвероногим нижним телом лошади и единственным рогом единорога, классифицируется как юнитавр.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}
			
			sb.append(body.getWing().setType(target, WingType.NONE));
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-unitaur",
					"demonic-unitaurs",
					"demonic-unitaur",
					"demonic-unitauress",
					"demonic-unitaurs",
					"demonic-unitauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(!body.getHorn().getType().equals(HornType.NONE) && body.getHorn().getHornRows()==1 && body.getHorn().getHornsPerRow()==1) {
					return 1150;
				}
			}
			return 0;
		}
	};
	public static AbstractSubspecies ALITAUR = new AbstractSubspecies(false,
			100000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"алитавр",
			"алитавры",
			"алитавр",
			"алитаврка",
			"алитавры",
			"алитаврки",
			null,
			Nocturnality.DIURNAL,
			"Обладая как пернатыми крыльями, так и рогом единорога, [npc.nameIsFull] классифицируется как могущественный аликорн, и [npc.verb(find)] ему почти без труда дается использование заклинаний.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 75f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			null,
			"Кентавры и прочие",
			"Кентавры и прочие",
			"CENTAUR_BASIC",
			"CENTAUR_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 2)),
			PresetColour.RACE_ALICORN,
			SubspeciesPreference.ONE_LOW,
			"Любой, кто обладает диким крылатым четырехногим нижним телом лошади и единственным рогом единорога, классифицируется как алитавр.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.ONE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public boolean isNonBiped() {
			return true;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.HORSE_STRAIGHT));
			sb.append(body.getHorn().setHornRows(target, 1));
			sb.append(body.getHorn().setHornsPerRow(target, 1));
			sb.append(body.getHorn().setHornLength(target, HornLength.TWO_LONG.getMedianValue()));
			sb.append(body.getLeg().setType(target, LegType.HORSE_MORPH));
			if(target!=null && target.getBody()!=null) {
				sb.append(target.setLegConfiguration(LegType.HORSE_MORPH, LegConfiguration.QUADRUPEDAL, true));
			} else {
				LegType.HORSE_MORPH.applyLegConfigurationTransformation(body, LegConfiguration.QUADRUPEDAL, true);
			}
			
			sb.append(body.setWingType(target, WingType.FEATHERED));
			sb.append(body.setWingSize(target, WingSize.FOUR_HUGE.getValue()));
			
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			sb.append(body.getPenis().getTesticle().getCum().addFluidModifier(target, FluidModifier.MUSKY));
			sb.append(body.getVagina().getGirlcum().addFluidModifier(target, FluidModifier.MUSKY));
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			return new String[] {
					"demonic-alitaur",
					"demonic-alitaurs",
					"demonic-alitaur",
					"demonic-alitauress",
					"demonic-alitaurs",
					"demonic-alitauresses"};
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH && body.getLeg().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
				if(body.getWing().getType()==WingType.FEATHERED
						&& !body.getHorn().getType().equals(HornType.NONE)
						&& body.getHorn().getHornRows()==1
						&& body.getHorn().getHornsPerRow()==1) {
					return 1200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HORSE_MORPH_ZEBRA = new AbstractSubspecies(false,
			18000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorphZebra",
			"statusEffects/race/raceBackgroundZebra",
			"зебра-морф",
			"зебра-морфы",
			"зебра-мальчик",
			"зебра-девочка",
			"зебры-мальчики",
			"зебры-девочки",
			new FeralAttributes(
					"зебра",
					"зебры",
					"зебра-жеребец",
					"зебра-кобыла",
					"зебры-жеребцы",
					"зебры-кобылы",
					LegConfiguration.QUADRUPEDAL,
					130,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Хотя тело [npc.namePos] обладает впечатляющим уровнем силы и скорости, [npc.sheIs] не самый сообразительный малый, и [npc.verb(struggle)] больше большинства, когда дело доходит до использования арканы. [npc.She] также [npc.has] высокую сопротивляемость как природному, так и арканическому жару.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f)),
			null,
			"Энциклопедия лошадей",
			"Энциклопедии лошадей",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфная лошадь с черно-белой полосатой шерстью, известная как «зебра-морф», когда передвигается на двух ногах, и «зебратавр», когда нижняя часть тела представляет собой дикую зебру. Чтобы считаться зебра-морфом, персонаж должен быть конь-морфом с черно-белыми полосатыми волосами и хвостом зебры.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));
			
			sb.append(body.setCovering(target, BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.STRIPED, CoveringModifier.SHORT, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));
			body.updateCoverings(true, true, true, true);
			
			if(body.getTail().getType()==TailType.HORSE_MORPH) {
				sb.append(body.getTail().setType(target, TailType.HORSE_MORPH_ZEBRA));
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"zebra-nightmare",
					"zebra-nightmares",
					"zebra-nightmare",
					"zebra-nightmare",
					"zebra-nightmares",
					"zebra-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "zebra-nightmare", false, false),
					applyNonBipedNameChange(body, "zebra-nightmare", false, true),
					applyNonBipedNameChange(body, "zebra-nightmare", false, false),
					applyNonBipedNameChange(body, "zebra-nightmare", true, false),
					applyNonBipedNameChange(body, "zebra-nightmare", false, true),
					applyNonBipedNameChange(body, "zebra-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				AbstractBodyCoveringType horseHair = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_HAIR):BodyCoveringType.HORSE_HAIR;
				Colour zebraPrimary = body.getCoverings().get(horseHair).getPrimaryColour();
				Colour zebraSecondary = body.getCoverings().get(horseHair).getSecondaryColour();
				if((((zebraPrimary==PresetColour.COVERING_BLACK || zebraPrimary==PresetColour.COVERING_JET_BLACK) && zebraSecondary==PresetColour.COVERING_WHITE)
						|| (zebraPrimary==PresetColour.COVERING_WHITE && (zebraSecondary==PresetColour.COVERING_BLACK || zebraSecondary==PresetColour.COVERING_JET_BLACK)))
					&& body.getTail().getType()==TailType.HORSE_MORPH_ZEBRA) {
//						return 125;
						return 1500;// Zebra-morphs should override centaur types
					}
			}
			return 0;
		}
	};

	public static AbstractSubspecies HORSE_MORPH_DONKEY = new AbstractSubspecies(false,
			12000,
			"innoxia_race_horse_equine_cider",
			"innoxia_race_horse_sugar_carrot_cube",
			"statusEffects/race/raceHorseMorph",
			"statusEffects/race/raceBackground",
			"осел-морф",
			"осел-морфы",
			"ослик-мальчик",
			"ослик-девочка",
			"ослики-мальчики",
			"ослики-девочки",
			new FeralAttributes(
					"осёл",
					"ослы",
					"осёл-жеребец",
					"ослица",
					"ослы-жеребцы",
					"ослицы",
					LegConfiguration.QUADRUPEDAL,
					120,
					0,
					1,
					1,
					1,
					true),
			Nocturnality.DIURNAL,
			"Хотя тело [npc.namePos] обладает впечатляющим уровнем силы и выносливости, [npc.she] [npc.verb(struggle)] больше, чем большинство, когда дело доходит до использования арканы. [npc.She] также [npc.has] врожденное знание того, как лучше всего сражаться с псовыми.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DOG_MORPH), 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.WOLF_MORPH), 25f),
					new Value<>(Attribute.getRacialDamageAttribute(Race.FOX_MORPH), 25f)),
			null,
			"Энциклопедия лошадей",
			"Энциклопедии лошадей",
			"HORSE_MORPH_BASIC",
			"HORSE_MORPH_ADVANCED",
			Race.HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 15),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.CLOTHING_DESATURATED_BROWN,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфный осел, известный как «осел-морф», когда он передвигается на двух ногах, и «ослитавр», когда его нижняя часть тела представляет собой дикого осла. Чтобы считаться осел-морфом, персонаж должен быть конь-морфом с высокими стоячими ушами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(body.getHorn().setType(target, HornType.NONE));
			sb.append(body.getWing().setType(target, WingType.NONE));
			
			if(Math.random()<0.75f) { // 75% of donkey morphs are the classic brown with white markings:
				sb.append(body.setCovering(target, BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false)));
				sb.append(body.setCovering(target, BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false)));
				sb.append(body.setCovering(target, BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, PresetColour.SKIN_DARK, false, PresetColour.SKIN_DARK, false)));
			}
			body.updateCoverings(true, true, true, true);
			
			if(body.getEar().getType()==EarType.HORSE_MORPH) {
				sb.append(body.getEar().setType(target, EarType.HORSE_MORPH_UPRIGHT));
			}
			if(body.getFace().getType()==FaceType.HORSE_MORPH && (!body.isFeminine() || Math.random()<0.5f)) {
				sb.append(body.getHair().setStyle(target, HairStyle.NONE)); // Sets hair style to mane
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"donkey-nightmare",
					"donkey-nightmares",
					"donkey-nightmare",
					"donkey-nightmare",
					"donkey-nightmares",
					"donkey-nightmares"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "donkey-nightmare", false, false),
					applyNonBipedNameChange(body, "donkey-nightmare", false, true),
					applyNonBipedNameChange(body, "donkey-nightmare", false, false),
					applyNonBipedNameChange(body, "donkey-nightmare", true, false),
					applyNonBipedNameChange(body, "donkey-nightmare", false, true),
					applyNonBipedNameChange(body, "donkey-nightmare", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HORSE_MORPH) {
				if(body.getEar().getType()==EarType.HORSE_MORPH_UPRIGHT) {
//					return 110; // Less than zebra
					return 1400;// Donkey-morphs should override centaur types
				}
			}
			return 0;
		}
		@Override
		public Map<PersonalityTrait, Float> getPersonalityTraitChances() {
			Map<PersonalityTrait, Float> map = super.getPersonalityTraitChances();
			map.put(PersonalityTrait.BRAVE, 0.5f);// Donkeys do not act like typical prey animals
			return map;
		}
	};
	
	public static AbstractSubspecies REINDEER_MORPH = new AbstractSubspecies(true,
			18000,
			"innoxia_race_reindeer_rudolphs_egg_nog",
			"innoxia_race_reindeer_sugar_cookie",
			"statusEffects/race/raceReindeerMorph",
			"statusEffects/race/raceBackground",
			"олене-морф",
			"олени-морфы",
			"олень-мальчик",
			"олень-девочка",
			"олени-мальчики",
			"олени-девочки",
			new FeralAttributes(
					"оленьей шерсти",
					"олени",
					"олень-самец",
					"олень-самка",
					"олени-быки",
					"оленихи",
					LegConfiguration.QUADRUPEDAL,
					130,
					0,
					1,
					1,
					4, false),
			Nocturnality.DIURNAL,
			"[npc.NamePos] тело отлично приспособлено к сопротивлению как природному, так и арканическому холоду, а также отличается особой силой и выносливостью.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null,
			"Миграции северных оленей",
			"Миграции северных оленей",
			"REINDEER_MORPH_BASIC",
			"REINDEER_MORPH_ADVANCED",
			Race.REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 2),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_REINDEER_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфный северный олень, известный как «олень-морф», когда передвигается на двух ногах, и «оленетавр», когда его нижняя часть тела представляет собой туловище дикого оленя.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.ONE),
					new Value<>(WorldRegion.SNOW, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.THREE)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.REINDEER_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// REPTILE:
	public static AbstractSubspecies ALLIGATOR_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_alligator_swamp_water",
			"innoxia_race_alligator_gators_gumbo",
			"statusEffects/race/raceGatorMorph",
			"statusEffects/race/raceBackground",
			"аллигатор-морф",
			"аллигаторы-морфы",
			"аллигатор-мальчик",
			"аллигатор-девочка",
			"аллигаторы-парни",
			"аллигаторы-девушки",
			new FeralAttributes(
					"аллигатора",
					"аллигаторы",
					LegConfiguration.QUADRUPEDAL,
					false,
					180,
					0,
					1,
					0,
					1, false),
			Nocturnality.NOCTURNAL,
			"[npc.NamePos] тело невероятно прочное, а [npc.she] [npc.verb(possess)] молниеносные рефлексы, а также сила, необходимая для того, чтобы максимально эффективно проводить любые внезапные атаки.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 30f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null,
			"Расселинские аллигаторы",
			"Расселинские аллигаторы",
			"ALLIGATOR_MORPH_BASIC",
			"ALLIGATOR_MORPH_ADVANCED",
			Race.ALLIGATOR_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 10),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_ALLIGATOR_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфный аллигатор, известный как «аллигатор-морф», когда передвигается на двух ногах, и «аллигатотавр», когда его нижняя часть тела представляет собой типично огромного дикого аллигатора.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.RIVER, SubspeciesSpawnRarity.TWO)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ALLIGATOR_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	// RODENTS:
	public static AbstractSubspecies SQUIRREL_MORPH = new AbstractSubspecies(true,
			6000,
			"innoxia_race_squirrel_squirrel_java",
			"innoxia_race_squirrel_round_nuts",
			"statusEffects/race/raceSquirrelMorph",
			"statusEffects/race/raceBackground",
			"белка-морф",
			"белки-морфы",
			"белочка-парень",
			"белочка-девушка",
			"беличьи парни",
			"беличьи девушки",
			new FeralAttributes(
					"беличьи",
					"белки",
					LegConfiguration.QUADRUPEDAL,
					15,
					0,
					1,
					4,
					1, false),
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] очень ловкие и бдительные, а также [npc.is] способные совершать огромные прыжки благодаря [npc.her] мощным [npc.legs].",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			null,
			"Погоня за белками",
			"Погоня за белками'",
			"SQUIRREL_MORPH_BASIC",
			"SQUIRREL_MORPH_ADVANCED",
			Race.SQUIRREL_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_SQUIRREL_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная белка, известная как «беломорф», когда передвигается на двух ногах, и «белкотавр», когда её нижняя часть тела представляет собой туловище огромной дикой белки.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.SQUIRREL_MORPH) {
				return 100;
			}
			return 0;
		}
	};
	
	//MOUSE_MORPH(Race.MOUSE_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.MOUSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.MOUSE_MORPH.getName()),
	
	public static AbstractSubspecies RAT_MORPH = new AbstractSubspecies(true,
			6000,
			"innoxia_race_rat_black_rats_rum",
			"innoxia_race_rat_brown_rats_burger",
			"statusEffects/race/raceRatMorph",
			"statusEffects/race/raceBackground",
			"крыс-морф",
			"крыс-морфы",
			"крысиный парень",
			"крысиная девушка",
			"крыс-мальчики",
			"крыс-девочки",
			new FeralAttributes(
					"крыса",
					"крысы",
					LegConfiguration.QUADRUPEDAL,
					15,
					0,
					1,
					6,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NamePos] тело очень выносливое, а [npc.she] [npc.has] обладает как высокой устойчивостью к арканическому яду, так и родством с ним.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.DAMAGE_POISON, 15f),
					new Value<>(Attribute.RESISTANCE_POISON, 5f)),
			null,
			"Проказничество",
			"Проказничества",
			"RAT_MORPH_BASIC",
			"RAT_MORPH_ADVANCED",
			Race.RAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная крыса, известная как «крыс-морф», когда передвигается на двух ногах, и «крыс-тавр», когда её нижняя часть тела представляет собой туловище огромной дикой крысы.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.FIVE)), Util.newHashMapOfValues(
							new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TWO)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RAT_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies RABBIT_MORPH = new AbstractSubspecies(true,
			12000,
			"innoxia_race_rabbit_bunny_juice",
			"innoxia_race_rabbit_bunny_carrot_cake",
			"statusEffects/race/raceRabbitMorph",
			"statusEffects/race/raceBackground",
			"кролик-морф",
			"кролики-морфы",
			"кролик-мальчик",
			"кролик-девочка",
			"кроликомальчики",
			"кроликодевочки",
			new FeralAttributes(
					"кроличьи",
					"кролики",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					5,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] очень ловкие и бдительные, и [npc.is] способны на короткие рывки невероятной скорости. [npc.Her] тело, будь оно [npc.she] [npc.verb(like)] или нет, также приспособлено для производства как можно большего количества потомства.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null,
			"Кроличьи выводки",
			"Кроличьих выводков",
			"RABBIT_MORPH_BASIC",
			"RABBIT_MORPH_ADVANCED",
			Race.RABBIT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфный кролик, известный как «кроликоморф», когда он передвигается на двух ногах, и «кроликотавр», когда его нижняя часть тела представляет собой туловище огромного дикого кролика.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			if(body.isFeminine() && body.getRaceStage()==RaceStage.GREATER && Math.random()<0.1f) {
				sb.append(body.getHair().setNeckFluff(target, true));
			}
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"jackalope",
					"jackalopes",
					"jackalope",
					"jackalope",
					"jackalopes",
					"jackalopes"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", true, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RABBIT_MORPH) {
				return 100;
			}
			return 0;
		}
	};

	public static AbstractSubspecies RABBIT_MORPH_LOP = new AbstractSubspecies(false,
			12000,
			"innoxia_race_rabbit_bunny_juice",
			"innoxia_race_rabbit_bunny_carrot_cake",
			"statusEffects/race/raceRabbitLopMorph",
			"statusEffects/race/raceBackground",
			"вислоухий-кроликоморф",
			"вислоухие-кроликоморфы",
			"кролик-мальчик (вислоухий)",
			"кролик-девушка (вислоухая)",
			"кролики-мальчики (вислоухие)",
			"кролики-девушки (вислоухие)",
			new FeralAttributes(
					"вислоухий кролик",
					"вислоухие кролики",
					LegConfiguration.QUADRUPEDAL,
					20,
					0,
					1,
					5,
					1, false),
			Nocturnality.CREPUSCULAR,
			"[npc.NameIsFull] очень ловкие и бдительные, и [npc.is] способны на короткие рывки невероятной скорости. [npc.Her] тело, будь оно [npc.she] [npc.verb(like)] или нет, также приспособлено для производства как можно большего количества потомства.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null,
			"Кроличьи выводки",
			"Кроличьих выводков",
			"RABBIT_MORPH_BASIC",
			"RABBIT_MORPH_ADVANCED",
			Race.RABBIT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфный кролик, у которого вместо привычных стоячих ушей — свисающие. Когда он передвигается на двух ногах, его называют «вислоухим морфом», а когда его нижняя часть тела представляет собой туловище огромного дикого кролика — «вислоухим тавром».",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.DOMINION, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TEN),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, null) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			
			if(body.getEar().getType()==EarType.RABBIT_MORPH) {
				sb.append(body.getEar().setType(target, EarType.RABBIT_MORPH_FLOPPY));
			}
			if(body.isFeminine() && body.getRaceStage()==RaceStage.GREATER && Math.random()<0.1f) {
				sb.append(body.getHair().setNeckFluff(target, true));
			}
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"jackalope",
					"jackalopes",
					"jackalope",
					"jackalope",
					"jackalopes",
					"jackalopes"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", false, false),
					applyNonBipedNameChange(body, "jackalope", true, false),
					applyNonBipedNameChange(body, "jackalope", false, true),
					applyNonBipedNameChange(body, "jackalope", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.RABBIT_MORPH && body.getEar().getType()==EarType.RABBIT_MORPH_FLOPPY) {
				return 150;
			}
			return 0;
		}
	};
	
	public static AbstractSubspecies BAT_MORPH = new AbstractSubspecies(true,
			10000,
			"innoxia_race_bat_fruit_bats_juice_box",
			"innoxia_race_bat_fruit_bats_salad",
			"statusEffects/race/raceBatMorph",
			"statusEffects/race/raceBackground",
			"летучая мышь-морф",
			"летучие мыши-морфы",
			"летучая мышь-мальчик",
			"девушка-летучая мышь",
			"парни-летучие мыши",
			"девушки-летучие мыши",
			new FeralAttributes(
					"летучая мышь",
					"летучие мыши",
					LegConfiguration.WINGED_BIPED,
					30,
					0,
					1,
					1,
					1, false) {
				@Override
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.NOCTURNAL,
			"Благодаря уникальной способности к эхолокации, все летучие мыши-морфы испытывают естественное желание болтать как можно больше. Из-за этого [npc.name] постоянно [npc.verb(play)] разговоры в [npc.her] голове, что позволяет [npc.herHim] придумывать новые и захватывающие способы соблазнения людей еще до первой встречи с ними.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 0f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"Летучие лисицы",
			"Летучих лисиц",
			"BAT_MORPH_BASIC",
			"BAT_MORPH_ADVANCED",
			Race.BAT_MORPH,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_BAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная двуногая летучая мышь.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TWO),
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.SUBMISSION, SubspeciesSpawnRarity.TWO),
				new Value<>(WorldType.BAT_CAVERNS, SubspeciesSpawnRarity.TEN),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.ONE)), null, null) {
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.BAT_MORPH) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	// AVIAN:
	public static AbstractSubspecies HARPY = new AbstractSubspecies(true,
			12000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceГарпия",
			"statusEffects/race/raceBackground",
			"гарпия",
			"гарпии",
			"гарпия",
			"гарпия",
			"гарпии",
			"гарпии",
			new FeralAttributes(
					"райская птица",
					"райские птицы",
					LegConfiguration.AVIAN,
					30,
					0,
					1,
					0,
					1,
					true) {
				@Override
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] одержимы [npc.her] внешностью и не сочтут странным, если кто-то захочет проводить как минимум половину времени бодрствования, чистя перья, чтобы выглядеть как можно привлекательнее.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"Всё о гарпиях",
			"Всё о гарпиях",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_HARPY,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Антропоморфная двуногая птица. Обычно обладает нечеловеческими руками, ногами, глазами, ушами и волосами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.SAVANNAH, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.DESERT_CITY, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE, SubspeciesSpawnRarity.THREE),
					new Value<>(WorldRegion.JUNGLE_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.TEN),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_SPAWN_PREFERENCE,
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String getName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "птица";
			}
			return super.getName(body);
		}
		@Override
		public String getNamePlural(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && (body ==null || (!body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL))) {
				return "птицы";
			}
			return super.getNamePlural(body);
		}
		@Override
		public String getSingularMaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "птица";
			}
			return super.getSingularMaleName(body);
		}
		@Override
		public String getSingularFemaleName(Body body) {
			if(Main.game!=null && Main.game.isSillyModeEnabled() && body !=null && !body.isFeral() && body.getLegConfiguration()==LegConfiguration.BIPEDAL) {
				return "птица";
			}
			return super.getSingularFemaleName(body);
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"fury",
					"furies",
					"fury",
					"fury",
					"furies",
					"furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", true, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public String getNonBipedRaceName(Body body) {
			return "гарпия";
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HARPY_RAVEN = new AbstractSubspecies(false,
			14000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceГарпия",
			"statusEffects/race/raceBackground",
			"воронья-гарпия",
			"вороньи-гарпии",
			"воронья-гарпия",
			"воронья-гарпия",
			"вороньи-гарпии",
			"вороньи-гарпии",
			new FeralAttributes(
					"ворон",
					"вороны",
					LegConfiguration.AVIAN,
					60,
					0,
					1,
					0,
					1, false) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] одержимы [npc.her] внешностью и не сочтут странным, если кто-то захочет проводить как минимум половину времени бодрствования, чистя перья, чтобы выглядеть как можно привлекательнее.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"Всё о гарпиях",
			"Всё о гарпиях",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфный двуногий ворон с иссиня-черными перьями. Обычно обладает нечеловеческими руками, ногами, глазами, ушами и волосами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			Colour ravenColour = PresetColour.COVERING_BLACK;
			if(Math.random()<0.5f) {
				ravenColour = PresetColour.COVERING_JET_BLACK;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, ravenColour, false, ravenColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, ravenColour, false, ravenColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, ravenColour, false, ravenColour, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"fury",
					"furies",
					"fury",
					"fury",
					"furies",
					"furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", true, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BLACK
						|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_JET_BLACK) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
	public static AbstractSubspecies HARPY_SWAN = new AbstractSubspecies(false,
			22000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceГарпия",
			"statusEffects/race/raceBackground",
			"лебединая гарпия",
			"лебединые гарпии",
			"лебединая гарпия",
			"лебединая гарпия",
			"лебединые гарпии",
			"лебединые гарпии",
			new FeralAttributes(
					"лебедь",
					"лебеди",
					LegConfiguration.AVIAN,
					100,
					0,
					1,
					0,
					1, false) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] одержимы [npc.her] внешностью и не сочтут странным, если кто-то захочет проводить как минимум половину времени бодрствования, чистя перья, чтобы выглядеть как можно привлекательнее.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"Всё о гарпиях",
			"Всё о гарпиях",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.BASE_WHITE,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфный двуногий лебедь с белыми перьями и кожей ног черного или серого цвета. Обычно обладает нечеловеческими руками, ногами, глазами, ушами и волосами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.RIVER, SubspeciesSpawnRarity.FIVE),
					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
				new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.THREE),
				new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
				SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false)));
			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_EBONY, false, PresetColour.SKIN_EBONY, false)));
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"fury",
					"furies",
					"fury",
					"fury",
					"furies",
					"furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", false, false),
					applyNonBipedNameChange(body, "fury", true, false),
					applyNonBipedNameChange(body, "fury", false, true),
					applyNonBipedNameChange(body, "fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				AbstractBodyCoveringType legSkin = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_SKIN):BodyCoveringType.HARPY_SKIN;
				Colour legColour = body.getCoverings().get(legSkin).getPrimaryColour();
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_WHITE
						&& (legColour==PresetColour.SKIN_GREY || legColour==PresetColour.SKIN_EBONY)) {
					return 150;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};
	
//	public static AbstractSubspecies HARPY_BALD_EAGLE = new AbstractSubspecies(false,
//			16000,
//			"innoxia_race_harpy_harpy_perfume",
//			"innoxia_race_harpy_bubblegum_lollipop",
//			"statusEffects/race/raceHarpy",
//			"statusEffects/race/raceBackground",
//			"bald-eagle-harpy",
//			"bald-eagle-harpies",
//			"bald-eagle-harpy",
//			"bald-eagle-harpy",
//			"bald-eagle-harpies",
//			"bald-eagle-harpies",
//			new FeralAttributes(
//					"bald-eagle",
//					"bald-eagles",
//					LegConfiguration.AVIAN,
//					90,
//					0,
//					1,
//					0,
//					1,
//					true) {
//				public boolean isArmsOrWingsPresent() {
//					return true;
//				}
//			},
//			"[npc.NameIsFull] obsessed with [npc.her] appearance, and wouldn't think it unusual for someone to want to spend at least half of their waking hours preening themselves in order to look as attractive as possible.",
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
//					new Value<>(Attribute.MAJOR_ARCANE, 0f),
//					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
//					new Value<>(Attribute.DAMAGE_LUST, 15f)),
//			null,
//			"All About Harpies",
//			"All About Harpies'",
//			"HARPY_BASIC",
//			"HARPY_ADVANCED",
//			Race.HARPY,
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 2),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			Util.newHashMapOfValues(
//					new Value<>(PerkCategory.PHYSICAL, 2),
//					new Value<>(PerkCategory.LUST, 5),
//					new Value<>(PerkCategory.ARCANE, 0)),
//			PresetColour.BASE_GREY_LIGHT,
//			SubspeciesPreference.ONE_LOW,
//			"An anthropomorphic, bipedal bald eagle, dark brown feathers covering their body and white feathers on their head. Typically only possessing non-human arms, legs, eyes, ears, and hair.",
//			Util.newHashMapOfValues(
//					new Value<>(WorldRegion.FIELDS, SubspeciesSpawnRarity.THREE_UNCOMMON),
//					new Value<>(WorldRegion.WOODLAND, SubspeciesSpawnRarity.THREE_UNCOMMON),
//					new Value<>(WorldRegion.FIELD_CITY, SubspeciesSpawnRarity.TWO_RARE)),
//			Util.newHashMapOfValues(
//					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.ONE_VERY_RARE),
//					new Value<>(WorldType.NIGHTLIFE_CLUB, SubspeciesSpawnRarity.FOUR_COMMON)),
//			null, Util.newArrayListOfValues(
//					SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
//		@Override
//		public String applySpeciesChanges(GameCharacter target, Body body) {
//			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_BROWN_DARK, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false));
//			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_BROWN_DARK, false));
//			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_YELLOW, false, PresetColour.SKIN_YELLOW, false));
//		}
//		@Override
//		public String[] getHalfDemonName(GameCharacter character) {
//			String[] names = new String[] {
//					"fury",
//					"furies",
//					"fury",
//					"fury",
//					"furies",
//					"furies"};
//			
//			if(character!=null && !character.getHalfDemonSubspecies().isNonBiped()) {
//				names = new String[] {
//					applyNonBipedNameChange(character, "fury", false, false),
//					applyNonBipedNameChange(character, "fury", false, true),
//					applyNonBipedNameChange(character, "fury", false, false),
//					applyNonBipedNameChange(character, "fury", true, false),
//					applyNonBipedNameChange(character, "fury", false, true),
//					applyNonBipedNameChange(character, "fury", true, true)
//				};
//			}
//			
//			return names;
//		}
//		@Override
//		public int getSubspeciesWeighting(Body body, AbstractRace race) {
//			if(race==Race.HARPY) {
//				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
//				AbstractBodyCoveringType headFeathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.HAIR):BodyCoveringType.HAIR_HARPY;
//				
//				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BROWN_DARK
//						&& body.getCoverings().get(headFeathers).getPrimaryColour()==PresetColour.COVERING_WHITE) {
//					return 150;
//				}
//			}
//			return 0;
//		}
//		@Override
//		public String getPathName() {
//			return "res/race/neverLucky/harpy/bald_eagle";
//		}
//		@Override
//		public Colour getSecondaryColour() {
//			return PresetColour.BASE_PITCH_BLACK;
//		}
//		@Override
//		public Colour getTertiaryColour() {
//			return PresetColour.BASE_YELLOW;
//		}
//	};
	
	public static AbstractSubspecies HARPY_PHOENIX = new AbstractSubspecies(false,
			50000,
			"innoxia_race_harpy_harpy_perfume",
			"innoxia_race_harpy_bubblegum_lollipop",
			"statusEffects/race/raceГарпия",
			"statusEffects/race/raceBackgroundPhoenix",
			"феникс-гарпия",
			"феникс-гарпии",
			"феникс-гарпия",
			"феникс-гарпия",
			"феникс-гарпии",
			"феникс-гарпии",
			new FeralAttributes(
					"феникс",
					"фениксы",
					LegConfiguration.AVIAN,
					90,
					0,
					1,
					0,
					1,
					true) {
				public boolean isArmsOrWingsPresent() {
					return true;
				}
			},
			Nocturnality.DIURNAL,
			"Хотя [npc.nameIsFull] так же одержимы [npc.her], как и другие гарпии, они также обладают природным талантом к управлению арканой, что позволяет [npc.herHim] изучать и применять заклинания с относительной легкостью. В частности, [npc.she] [npc.has] исключительно высокую близость с арканическим огнем...",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, 10f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.DAMAGE_FIRE, 75f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null,
			"Всё о гарпиях",
			"Всё о гарпиях",
			"HARPY_BASIC",
			"HARPY_ADVANCED",
			Race.HARPY,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.BASE_ORANGE,
			SubspeciesPreference.ONE_LOW,
			"Антропоморфная двуногая мифическая птица, чьи перья либо светятся красным, оранжевым или желтым цветом, либо на самом деле состоят из арканового огня. Они встречаются крайне редко и обычно обладают лишь нечеловеческими руками, ногами, глазами, ушами и волосами.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.VOLCANO, SubspeciesSpawnRarity.THREE)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.HARPY_NEST, SubspeciesSpawnRarity.ONE)), null, Util.newArrayListOfValues(
					SubspeciesFlag.DISABLE_FURRY_PREFERENCE)) {
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			StringBuilder sb = new StringBuilder();
			CoveringPattern pattern = CoveringPattern.OMBRE;
			if(Math.random()<0.5f) {
				pattern = CoveringPattern.HIGHLIGHTS;
			}
			sb.append(body.setCovering(target, BodyCoveringType.FEATHERS, new Covering(BodyCoveringType.FEATHERS, pattern, PresetColour.COVERING_ORANGE, true, PresetColour.COVERING_YELLOW, true)));
			sb.append(body.setCovering(target, BodyCoveringType.HAIR_HARPY, new Covering(BodyCoveringType.HAIR_HARPY, pattern, PresetColour.COVERING_RED, true, PresetColour.COVERING_ORANGE, true)));
			sb.append(body.setCovering(target, BodyCoveringType.BODY_HAIR_HARPY, new Covering(BodyCoveringType.BODY_HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_RED, true, PresetColour.COVERING_RED, true)));
			sb.append(body.setCovering(target, BodyCoveringType.HARPY_SKIN, new Covering(BodyCoveringType.HARPY_SKIN, CoveringPattern.NONE, PresetColour.SKIN_ORANGE, false, PresetColour.SKIN_ORANGE, false)));
			
			return sb.toString();
		}
		@Override
		public String[] getHalfDemonName(Body body) {
			String[] names = new String[] {
					"phoenix-fury",
					"phoenix-furies",
					"phoenix-fury",
					"phoenix-fury",
					"phoenix-furies",
					"phoenix-furies"};
			
			if(body !=null && !body.getHalfDemonSubspecies().isNonBiped()) {
				names = new String[] {
					applyNonBipedNameChange(body, "phoenix-fury", false, false),
					applyNonBipedNameChange(body, "phoenix-fury", false, true),
					applyNonBipedNameChange(body, "phoenix-fury", false, false),
					applyNonBipedNameChange(body, "phoenix-fury", true, false),
					applyNonBipedNameChange(body, "phoenix-fury", false, true),
					applyNonBipedNameChange(body, "phoenix-fury", true, true)
				};
			}
			
			return names;
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.HARPY) {
				AbstractBodyCoveringType feathers = (body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)?BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				
				if((body.getCoverings().get(feathers).isPrimaryGlowing()
						&& (body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_RED
							|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_ORANGE
							|| body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_YELLOW))
					|| body.getBodyMaterial()==BodyMaterial.FIRE) {
					return 200;
				}
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
	};

	// ********** SPECIAL SUBSPECIES ********** //
	
	// SLIMES:
	public static AbstractSubspecies SLIME = new AbstractSubspecies(true,
			10000,
			"innoxia_race_slime_slime_quencher",
			"innoxia_race_slime_biojuice_canister",
			"statusEffects/race/raceSlime",
			"statusEffects/race/raceBackgroundSlime",
			"слайм",
			"слаймы",
			"слайм-парень",
			"слайм-девушка",
			"слайм-парни",
			"слайм-девушки",
			null,
			Nocturnality.DIURNAL,
			"Благодаря мягкому, слизистому телу [npc.her], [npc.nameIsFull] почти полностью невосприимчив(а) к физическому урону, но [npc.sheIs] также не способен(на) нанести значительный урон без оружия. [npc.She] также может по своему желанию менять форму [npc.her] тела, что позволяет [npc.herHim] принимать любой облик, который [npc.she] [npc.verb(desire)].",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 0f),
					new Value<>(Attribute.MAJOR_ARCANE, 0f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues(),
			"Слизистое веселье",
			"Слизистые развлечения",
			"SLIME_BASIC",
			"SLIME_ADVANCED",
			Race.SLIME,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 2),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 0)),
			PresetColour.RACE_SLIME,
			SubspeciesPreference.FOUR_ABUNDANT,
			"Существо, полностью состоящее из слизи, с твердым ядром, расположенным там, где должно находиться сердце.",
			Util.newHashMapOfValues(
					new Value<>(WorldRegion.SUBMISSION, SubspeciesSpawnRarity.TEN)),
			Util.newHashMapOfValues(
					new Value<>(WorldType.BAT_CAVERNS, SubspeciesSpawnRarity.TEN)), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public AbstractItemType getTransformativeItem(GameCharacter owner) {
			if(getTransformativeItemId()==null || getTransformativeItemId().isEmpty()) {
				return null;	
			}
			if(owner!=null && !owner.hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				return ItemType.getItemTypeFromId("innoxia_race_slime_slime_quencher");
			}
			return ItemType.getItemTypeFromId(getTransformativeItemId());
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			// Slime subspecies are set in the Main.game.getCharacterUtils().generateBody() method
			body.setBodyMaterial(BodyMaterial.SLIME);
			return ""; // Don't append the slime change as that will surely be handled elsewhere...
		}

		@Override
		public String getStatusEffectDescription(GameCharacter character) {
			if(character!=null) {
				AbstractSubspecies coreSubspecies = character.getBody().getFleshSubspecies();
				if(character.getSubspeciesOverrideRace()==Race.DEMON) {
					return UtilText.parse(character,
							"Благодаря мягкому, слизистому телу [npc.her], [npc.nameIsFull] почти полностью невосприимчив(а) к физическому урону, но [npc.she] также не способен(на) нанести серьезный урон без оружия. Слизистое ядро [npc.Her] пульсирует огромной мощью, выдавая тот факт, что [npc.sheIs] — истинный демонический слайм.");
				} else if(coreSubspecies==Subspecies.DEMON) {
					return UtilText.parse(character,
							"Благодаря мягкому, слизистому телу [npc.her], [npc.nameIsFull] почти полностью невосприимчив(а) к физическому урону, но [npc.she] также не способен(на) нанести серьезный урон без оружия. Хотя [npc.she] [npc.verb(appear)] демоном, [npc.sheIs] лишь имитирует их облик...");
				}
			}
			return super.getStatusEffectDescription(character);
		}
		
		@Override
		public String getName(Body body) {
			if(body == null) {
				return super.getName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getName(body)+"-mimic-slime";
			}
			return coreSubspecies.getName(body)+"-slime";
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null) {
				return super.getNamePlural(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getNamePlural(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getName(body)+"-mimic-slimes";
			}
			return coreSubspecies.getName(body)+"-slimes";
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null) {
				return super.getSingularMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularMaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularMaleName(body)+"-mimic-slime";
			}
			return coreSubspecies.getSingularMaleName(body)+"-slime";
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null) {
				return super.getSingularFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularFemaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularFemaleName(body)+"-mimic-slime";
			}
			return coreSubspecies.getSingularFemaleName(body)+"-slime";
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null) {
				return super.getPluralMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralMaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularMaleName(body)+"-mimic-slimes";
			}
			return coreSubspecies.getSingularMaleName(body)+"-slimes";
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null) {
				return super.getPluralFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralFemaleName(body);
			} else if(coreSubspecies==Subspecies.DEMON && body.getSubspeciesOverride()==null) {
				return coreSubspecies.getSingularFemaleName(body)+"-mimic-slimes";
			}
			return coreSubspecies.getSingularFemaleName(body)+"-slimes";
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSlimeSVGString(null);
			}
			return character.getBody().getFleshSubspecies().getSlimeSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.SLIME) {
				return 10_000; // Slimes should always be slime, no matter their underlying subspecies
			}
			return 0;
		}
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	// DOLLS:
	public static AbstractSubspecies DOLL = new AbstractSubspecies(true,
			10000,
			"innoxia_race_doll_silic_oil",
			null,
			"statusEffects/race/raceDoll",
			"statusEffects/race/raceBackgroundDoll",
			"кукла",
			"куклы",
			"кукла",
			"кукла",
			"куклы",
			"куклы",
			null,
			Nocturnality.CATHEMERAL,
			"[npc.NameIsFull] — чрезвычайно реалистичная автономная секс-кукла, созданная из пропитанного Арканой силикона в лавке «Роскошь Ловьенн». Искусственное тело [npc.Her] и природа [npc.her], работающей на магической энергии, наделяют [npc.herHim] многочисленными особенностями.",
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Древо перков, специфичное для кукол)]",
					"[style.boldGood(Перки дают множество эффектов)]"),
			"Абсолютная игрушка",
			"Абсолютные игрушки",
			"DOLL_BASIC",
			"DOLL_ADVANCED",
			Race.DOLL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 1),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 1)),
			PresetColour.RACE_DOLL,
			SubspeciesPreference.ZERO_NONE,
			"Похожая на живую силиконовая кукла, на которую наложили чары, чтобы она могла двигаться, говорить и подчиняться командам.",
			null,
			Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public AbstractItemType getTransformativeItem(GameCharacter owner) {
			return null;
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			// Doll subspecies are set in the Main.game.getCharacterUtils().generateBody() method
			body.setBodyMaterial(BodyMaterial.SILICONE);
			body.getBreast().setMilk(new FluidMilk(FluidType.MILK_DOLL, false));
			body.getBreastCrotch().setMilk(new FluidMilk(FluidType.MILK_DOLL, true));
			body.getPenis().getTesticle().setCum(new FluidCum(FluidType.CUM_DOLL));
			body.getVagina().setGirlcum(new FluidGirlCum(FluidType.GIRL_CUM_DOLL));
			
			return "";
		}
		@Override
		public String getName(Body body) {
			if(body == null) {
				return super.getName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getName(body);
			}
			return coreSubspecies.getName(body)+"-doll";
		}
		
		@Override
		public String getNamePlural(Body body) {
			if(body ==null) {
				return super.getNamePlural(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getNamePlural(body);
			}
			return coreSubspecies.getName(body)+"-dolls";
		}

		@Override
		public String getSingularMaleName(Body body) {
			if(body ==null) {
				return super.getSingularMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularMaleName(body);
			}
			return coreSubspecies.getSingularMaleName(body)+"-doll";
		}

		@Override
		public String getSingularFemaleName(Body body) {
			if(body ==null) {
				return super.getSingularFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getSingularFemaleName(body);
			}
			return coreSubspecies.getSingularFemaleName(body)+"-doll";
		}

		@Override
		public String getPluralMaleName(Body body) {
			if(body ==null) {
				return super.getPluralMaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralMaleName(body);
			}
			return coreSubspecies.getSingularMaleName(body)+"-dolls";
		}

		@Override
		public String getPluralFemaleName(Body body) {
			if(body ==null) {
				return super.getPluralFemaleName(body);
			}
			AbstractSubspecies coreSubspecies = body.getFleshSubspecies();
			if(coreSubspecies==Subspecies.HUMAN) {
				return super.getPluralFemaleName(body);
			}
			return coreSubspecies.getSingularFemaleName(body)+"-dolls";
		}

		@Override
		public String getSVGString(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getDollSVGString(null);
			}
			return character.getBody().getFleshSubspecies().getDollSVGString(character);
		}

		@Override
		public String getSVGStringDesaturated(GameCharacter character) {
			if(character==null) {
				return Subspecies.HUMAN.getDollSVGStringDesaturated(null);
			}
			return character.getBody().getFleshSubspecies().getDollSVGStringDesaturated(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.DOLL) {
				return 20_000; // Dolls should always be dolls, no matter their underlying subspecies
			}
			return 0;
		}
		public FeralAttributes getFeralAttributes(Body body) {
			if(body==null) {
				return super.getFeralAttributes(body);
			}
			return body.getFleshSubspecies().getFeralAttributes(body);
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	// ELEMENTALS:
	
	public static AbstractSubspecies ELEMENTAL_FIRE = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundFire",
			"огненный элементаль",
			"огненные элементали",
			"огненный элементаль",
			"огненный элементаль",
			"огненные элементали",
			"огненные элементали",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] призванный элементаль, ныне привязанный к школе Огня.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_FIRE, 50f),
					new Value<>(Attribute.RESISTANCE_FIRE, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Безграничный)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>"),
			"Огненные элементали",
			"Огненных элементалей",
			"ELEMENTAL_FIRE_BASIC",
			"ELEMENTAL_FIRE_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_FIRE,
			SubspeciesPreference.FOUR_ABUNDANT, "Арканный элементаль, привязанный к школе Огня.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return 50_000;
		}
		public AbstractItemType getBook() {
			return ItemType.getLoreBook(this);
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.FIRE);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_FIRE),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_FIRE),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.FIRE) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};
	
	public static AbstractSubspecies ELEMENTAL_EARTH = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundEarth",
			"земляной элементаль",
			"земляные элементали",
			"земляной элементаль",
			"земляной элементаль",
			"земляные элементали",
			"земляные элементали",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] призванный элементаль, ныне привязанный к школе Земли.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 50f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Безграничный)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>"),
			"Земляные элементали",
			"Земляных элементалей",
			"ELEMENTAL_EARTH_BASIC",
			"ELEMENTAL_EARTH_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 1),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_EARTH,
			SubspeciesPreference.FOUR_ABUNDANT, "Арканный элементаль, привязанный к школе Земли.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.STONE);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_EARTH),
							this.getColour(character),
							this.getColour(character),
							this.getColour(character),
							"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_EARTH),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL
					&& (body.getBodyMaterial()==BodyMaterial.STONE || body.getBodyMaterial()==BodyMaterial.RUBBER || body.getBodyMaterial()==BodyMaterial.FLESH || body.getBodyMaterial()==BodyMaterial.SLIME || body.getBodyMaterial()==BodyMaterial.SILICONE)) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies ELEMENTAL_WATER = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundWater",
			"водный элементаль",
			"водные элементали",
			"водный элементаль",
			"водный элементаль",
			"водные элементали",
			"водные элементали",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] призванный элементаль, ныне привязанный к стихии Воды.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 50f),
					new Value<>(Attribute.RESISTANCE_ICE, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Безграничный)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>"),
			"Водяные элементали",
			"Водяных элементалей",
			"ELEMENTAL_WATER_BASIC",
			"ELEMENTAL_WATER_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 3),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_WATER,
			SubspeciesPreference.FOUR_ABUNDANT, "Арканический элементаль, привязанный к стихии Воды.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.WATER);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_WATER),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_WATER),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && (body.getBodyMaterial()==BodyMaterial.WATER || body.getBodyMaterial()==BodyMaterial.ICE)) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies ELEMENTAL_AIR = new AbstractSubspecies(false,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundAir",
			"воздушный элементаль",
			"воздушные элементали",
			"воздушный элементаль",
			"воздушный элементаль",
			"воздушные элементали",
			"воздушные элементали",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] призванный элементаль, ныне привязанный к школе Воздуха.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_POISON, 50f),
					new Value<>(Attribute.RESISTANCE_POISON, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Безграничный)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>"),
			"Элементали Воздуха",
			"Элементалей Воздуха",
			"ELEMENTAL_AIR_BASIC",
			"ELEMENTAL_AIR_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_AIR,
			SubspeciesPreference.FOUR_ABUNDANT, "Arcane элементаль, привязанный к школе Воздуха.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.AIR);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_AIR),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_AIR),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.AIR) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};

	public static AbstractSubspecies ELEMENTAL_ARCANE = new AbstractSubspecies(true,
			100000,
			"innoxia_race_demon_liliths_gift",
			null,
			"statusEffects/race/raceElemental",
			"statusEffects/race/raceBackgroundArcane",
			"arcane элементаль",
			"arcane элементали",
			"arcane элементаль",
			"arcane элементаль",
			"arcane элементали",
			"arcane элементали",
			null,
			Nocturnality.DIURNAL,
			"[npc.NameIsFull] призванный элементаль, ныне привязанный к школе Arcane.",
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 50f),
					new Value<>(Attribute.RESISTANCE_LUST, 50f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Безграничный)] <b style='color: "+ PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+ ";'> собственные трансформации</b>"),
			"Элементали Arcane",
			"Элементалей Arcane",
			"ELEMENTAL_ARCANE_BASIC",
			"ELEMENTAL_ARCANE_ADVANCED",
			Race.ELEMENTAL,
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			Util.newHashMapOfValues(
					new Value<>(PerkCategory.PHYSICAL, 5),
					new Value<>(PerkCategory.LUST, 5),
					new Value<>(PerkCategory.ARCANE, 5)),
			PresetColour.SPELL_SCHOOL_ARCANE,
			SubspeciesPreference.FOUR_ABUNDANT, "Арканный элементаль, привязанный к школе Arcane.", null, Util.newHashMapOfValues(), null, Util.newArrayListOfValues(
					SubspeciesFlag.HIDDEN_FROM_PREFERENCES)) {
		@Override
		public int getSubspeciesOverridePriority() {
			return ELEMENTAL_FIRE.getSubspeciesOverridePriority();
		}
		@Override
		public String applySpeciesChanges(GameCharacter target, Body body) {
			body.setBodyMaterial(BodyMaterial.ARCANE);
			return "";
		}
		@Override
		public String getSVGString(GameCharacter character) {
			if(character!=null && (character instanceof Elemental) && ((Elemental)character).getSummoner()!=null && !((Elemental)character).getSummoner().isElementalActive()) {
				if(((Elemental)character).getPassiveForm()==null) {
					String wispSVG = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_ARCANE),
									this.getColour(character),
									this.getColour(character),
									this.getColour(character),
									"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getRaceWisp()+"</div>");
					return wispSVG;
				}
				AbstractSubspecies passiveForm = ((Elemental)character).getPassiveForm();
				if (passiveForm.SVGString == null) {
					passiveForm.initSVGStrings();
				}
				String raceSvg = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(ELEMENTAL_ARCANE),
						this.getColour(character),
						this.getColour(character),
						this.getColour(character),
						passiveForm.SVGStringUncoloured);
				return getBipedBackground(raceSvg, character, this.getColour(character));
			}
			return super.getSVGString(character);
		}
		@Override
		public int getSubspeciesWeighting(Body body, AbstractRace race) {
			if(race==Race.ELEMENTAL && body.getBodyMaterial()==BodyMaterial.ARCANE) {
				return 100;
			}
			return 0;
		}
		@Override
		public boolean isWinged() {
			return true;
		}
		@Override
		public boolean isDoesNotAge() {
			return true;
		}
	};


	public static List<AbstractSubspecies> allSubspecies;
	
	public static Map<AbstractSubspecies, String> subspeciesToIdMap = new HashMap<>();
	public static Map<String, AbstractSubspecies> idToSubspeciesMap = new HashMap<>();

	private static Map<WorldRegion, Map<AbstractSubspecies, SubspeciesSpawnRarity>> regionSpecies;
	private static Map<AbstractWorldType, Map<AbstractSubspecies, SubspeciesSpawnRarity>> worldSpecies;
	private static Map<AbstractPlaceType, Map<AbstractSubspecies, SubspeciesSpawnRarity>> placeSpecies;
	
	protected static Map<AbstractSubspecies, SubspeciesSpawnRarity> dominionStormImmuneSpecies;
	protected static Map<AbstractRace, List<AbstractSubspecies>> subspeciesFromRace;
	
	
	public static AbstractSubspecies getSubspeciesFromId(String id) {
		if(id.equalsIgnoreCase("CAT_MORPH_LEOPARD_SNOW")) {
			id = "innoxia_panther_subspecies_snow_leopard";
		} else if(id.equalsIgnoreCase("CAT_MORPH_LEOPARD")) {
			id = "innoxia_panther_subspecies_leopard";
		} else if(id.equalsIgnoreCase("CAT_MORPH_LION")) {
			id = "innoxia_panther_subspecies_lion";
		} else if(id.equalsIgnoreCase("CAT_MORPH_TIGER")) {
			id = "innoxia_panther_subspecies_tiger";
		} else if(id.equalsIgnoreCase("HARPY_BALD_EAGLE")) {
			id = "innoxia_raptor_subspecies_bald_eagle";
		}
		id = Util.getClosestStringMatch(id, idToSubspeciesMap.keySet());
		return idToSubspeciesMap.get(id);
	}
	
	public static String getIdFromSubspecies(AbstractSubspecies subspecies) {
		return subspeciesToIdMap.get(subspecies);
	}

	static {
		allSubspecies = new ArrayList<>();

		// Modded subspecies:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "subspecies", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
					try {
						AbstractSubspecies subspecies = new AbstractSubspecies(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("_race", "");
						allSubspecies.add(subspecies);
						subspeciesToIdMap.put(subspecies, id);
						idToSubspeciesMap.put(id, subspecies);
//						System.out.println("subspecies: "+id);
					} catch(Exception ex) {
						System.err.println("Loading modded subspecies failed at 'Subspecies'. File path: "+innerEntry.getValue().getAbsolutePath());
						System.err.println("Actual exception: ");
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res subspecies:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "subspecies", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
					try {
						AbstractSubspecies subspecies = new AbstractSubspecies(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("_race", "");
						allSubspecies.add(subspecies);
						subspeciesToIdMap.put(subspecies, id);
						idToSubspeciesMap.put(id, subspecies);
					} catch(Exception ex) {
						System.err.println("Loading subspecies failed at 'Subspecies'. File path: "+innerEntry.getValue().getAbsolutePath());
						System.err.println("Actual exception: ");
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Hard-coded:
		
		Field[] fields = Subspecies.class.getFields();
		
		for(Field f : fields){
			if (AbstractSubspecies.class.isAssignableFrom(f.getType())) {
				
				AbstractSubspecies subspecies;
				
				try {
					subspecies = ((AbstractSubspecies) f.get(null));

					subspeciesToIdMap.put(subspecies, f.getName());
					idToSubspeciesMap.put(f.getName(), subspecies);
					allSubspecies.add(subspecies);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		worldSpecies = new HashMap<>();
		regionSpecies = new HashMap<>();
		placeSpecies = new HashMap<>();
		dominionStormImmuneSpecies = new HashMap<>();
		subspeciesFromRace = new HashMap<>();
		
		for(AbstractSubspecies species : Subspecies.getAllSubspecies()) {
			subspeciesFromRace.putIfAbsent(species.getRace(), new ArrayList<>());
			subspeciesFromRace.get(species.getRace()).add(species);
			
			for(Entry<WorldRegion, SubspeciesSpawnRarity> type : species.getRegionLocations().entrySet()) {
				regionSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				regionSpecies.get(type.getKey()).put(species, type.getValue());
				try {
					if(type.getKey()==WorldRegion.DOMINION
							&& (species.getRace()==Race.DEMON || species.getStatusEffectAttributeModifiers(null).get(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue())) {
						dominionStormImmuneSpecies.put(species, type.getValue());
					}
				} catch(Exception ex) {	
				}
			}
			
			for(Entry<AbstractWorldType, SubspeciesSpawnRarity> type : species.getWorldLocations().entrySet()) {
				worldSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				worldSpecies.get(type.getKey()).put(species, type.getValue());
				
				try {
					if(type.getKey()==WorldType.DOMINION
							&& (species.getRace()==Race.DEMON || species.getStatusEffectAttributeModifiers(null).get(Attribute.MAJOR_ARCANE)>=IntelligenceLevel.TWO_SMART.getMinimumValue())) {
						dominionStormImmuneSpecies.put(species, type.getValue());
					}
				} catch(Exception ex) {	
				}
			}
			
			for(Entry<AbstractPlaceType, SubspeciesSpawnRarity> type : species.getPlaceLocations().entrySet()) {
				placeSpecies.putIfAbsent(type.getKey(), new HashMap<>());
				placeSpecies.get(type.getKey()).put(species, type.getValue());
			}
		}
		
//		for(AbstractSubspecies s : dominionStormImmuneSpecies.keySet()) {
//			System.out.println(s.getName(null));
//		}
		
		for(List<AbstractSubspecies> e : subspeciesFromRace.values()) {
			e.sort((s1, s2) -> s1.getName(null).compareTo(s2.getName(null)));
		}

		allSubspecies.sort((s1, s2) -> s1.getRace().getName(false).compareTo(s2.getRace().getName(false)));
	}
	
	public static List<AbstractSubspecies> getAllSubspecies() {
		return allSubspecies;
	}

	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, AbstractSubspecies... subspeciesToExclude) {
		return getWorldSpecies(worldType, placeType, onlyCoreRaceSpecies, true, subspeciesToExclude);
	}

	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, boolean includeRegionSpecies, AbstractSubspecies... subspeciesToExclude) {
		return getWorldSpecies(worldType, placeType, onlyCoreRaceSpecies, includeRegionSpecies, Arrays.asList(subspeciesToExclude));
	}
	
	/**
	 * @param worldType The WorldType from which to fetch Subspecies present.
	 * @param placeType The PlaceType from which to fetch Subspecies present. Can be passed in as null to ignore.
	 * @param onlyCoreRaceSpecies true if only core Subspecies should be returned. (e.g. Cat-morph would be returned, but not Lion-morph, Tiger-morph, etc.)
	 * @param includeRegionSpecies true if the species of the WorldRegion should be included.
	 * @param subspeciesToExclude Any Subspecies that should be excluded from the returned map.
	 * @return A weighted map of subspecies that can spawn in that world, region and/or place.
	 */
	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getWorldSpecies(AbstractWorldType worldType, AbstractPlaceType placeType, boolean onlyCoreRaceSpecies, boolean includeRegionSpecies, List<AbstractSubspecies> subspeciesToExclude) {
		worldSpecies.putIfAbsent(worldType, new HashMap<>());
		regionSpecies.putIfAbsent(worldType.getWorldRegion(), new HashMap<>());
		
		Map<AbstractSubspecies, SubspeciesSpawnRarity> map = new HashMap<>(worldSpecies.get(worldType));
		if (includeRegionSpecies) {
			for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> regionEntry : regionSpecies.get(worldType.getWorldRegion()).entrySet()) {
				if(!map.containsKey(regionEntry.getKey())) {
					map.put(regionEntry.getKey(), regionEntry.getValue());
				}
			}
		}
		if(placeType!=null) {
			placeSpecies.putIfAbsent(placeType, new HashMap<>());
			regionSpecies.putIfAbsent(placeType.getWorldRegion(), new HashMap<>());
		    for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> placeEntry : placeSpecies.get(placeType).entrySet()) {
		        if(!map.containsKey(placeEntry.getKey())) {
		            map.put(placeEntry.getKey(), placeEntry.getValue());
		        }
		    }
			if (includeRegionSpecies && regionSpecies.get(placeType.getWorldRegion())!=null) {
			    for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> regionEntry : regionSpecies.get(placeType.getWorldRegion()).entrySet()) {
			        if(!map.containsKey(regionEntry.getKey())) {
			            map.put(regionEntry.getKey(), regionEntry.getValue());
			        }
			    }
			}
		}
		
		Map<AbstractSubspecies, SubspeciesSpawnRarity> filteredMap = new HashMap<>(map);
		if(onlyCoreRaceSpecies) {
			for(AbstractSubspecies sub : map.keySet()) {
				if(AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())!=sub) {
					filteredMap.remove(sub);
				}
			}
		}
		
		for(AbstractSubspecies sub : subspeciesToExclude) {
			filteredMap.remove(sub);
		}
		
		return filteredMap;
	}

	/**
	 * @param onlyCoreRaceSpecies true if only core Subspecies should be returned. (e.g. Cat-morph would be returned, but not caracal-morph, lynx-morph, etc.)
	 * @param subspeciesToExclude Any Subspecies that should be excluded from the returned map.
	 */
	public static Map<AbstractSubspecies, SubspeciesSpawnRarity> getDominionStormImmuneSpecies(boolean onlyCoreRaceSpecies, AbstractSubspecies... subspeciesToExclude) {
		Map<AbstractSubspecies, SubspeciesSpawnRarity> map = new HashMap<>(dominionStormImmuneSpecies);
		
		if(onlyCoreRaceSpecies) {
			for(AbstractSubspecies sub : dominionStormImmuneSpecies.keySet()) {
				if(AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())!=sub) {
					map.remove(sub);
				}
			}
		}
		
		for(AbstractSubspecies sub : subspeciesToExclude) {
			map.remove(sub);
		}
		
		return map;
	}

	/**
	 * @return All Subspecies which do not have an override, cannot self transform, and which spawn in Dominion, Submission, or Elis.
	 * <br/><b>NOTE:</b> This should be updated in the future to include common races for all areas of the game.
	 */
	public static List<AbstractSubspecies> getCommonSubspecies(Collection<AbstractSubspecies> subspeciesToExclude) {
		List<AbstractSubspecies> commonSubspecies = new ArrayList<>();
		
		for(AbstractSubspecies subspecies : getAllSubspecies()) {
			if(!subspeciesToExclude.contains(subspecies)
				&& subspecies.getSubspeciesOverridePriority()==0
				&& !subspecies.isAbleToSelfTransform()
				&& (subspecies.isAbleToNaturallySpawnInLocation(WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS)
					|| subspecies.isAbleToNaturallySpawnInLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS)
					|| subspecies.isAbleToNaturallySpawnInLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_alley")))) {
				commonSubspecies.add(subspecies);
			}
		}
		
		return commonSubspecies;
	}
	
	public static List<AbstractSubspecies> getSubspeciesOfRace(AbstractRace race) {
		return subspeciesFromRace.get(race);
	}
}