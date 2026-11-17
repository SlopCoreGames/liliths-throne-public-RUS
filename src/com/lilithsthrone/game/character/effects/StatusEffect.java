package com.lilithsthrone.game.character.effects;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Units.ValueType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class StatusEffect {

	// Attribute-related status effects:
	// Strength:
	public static AbstractStatusEffect PHYSIQUE_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"sissy",
			"attStrength0",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f)),
			Util.newArrayListOfValues(
					"Базовый [style.colourUnarmed(unarmed damage)] равен [style.colourMinorGood(20% от Телосложение)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ZERO_WEAK.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] невероятно слабый. [npc.She] [npc.verb(struggle)] нанести значительный урон с помощью [npc.her] слабых маленьких [npc.arms], и [npc.her] хрупкое тело особенно уязвимо к физическому урону.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ZERO_WEAK;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"average",
			"attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			null,
			Util.newArrayListOfValues(
					"Базовый [style.colourUnarmed(unarmed damage)] равен [style.colourMinorGood(20% от Телосложение)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ONE_AVERAGE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameHasFull] средний уровень физической подготовки для [npc.her] размера тела.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ONE_AVERAGE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"strong",
			"attStrength2",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			Util.newArrayListOfValues(
					"Базовый [style.colourUnarmed(unarmed damage)] равен [style.colourMinorGood(20% от Телосложение)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.TWO_STRONG.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] сильнее и выносливее, чем предполагает [npc.her] размер тела, и [npc.is] способен нанести больше физического урона.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.TWO_STRONG;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"powerful",
			"attStrength3",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f)),
			Util.newArrayListOfValues(
					"Базовый [style.colourUnarmed(unarmed damage)] равен [style.colourMinorGood(20% от Телосложение)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.THREE_POWERFUL.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] значительно сильнее и выносливее, чем предполагает [npc.her] размер тела, и [npc.is] способен нанести значительный физический урон.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.THREE_POWERFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"mighty",
			"attStrength4",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 30f)),
			Util.newArrayListOfValues(
					"Базовый [style.colourUnarmed(unarmed damage)] равен [style.colourMinorGood(20% от Телосложение)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FOUR_MIGHTY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] исключительный уровень физической подготовки, и мало кто может сравниться с [npc.her] силой.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FOUR_MIGHTY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Herculean",
			"attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			Util.newArrayListOfValues(
					"Базовый [style.colourUnarmed(урон от ближнего боя)] равен [style.colourMinorGood(20% от Телосложения)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FIVE_HERCULEAN.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] тело - это легенда; простые смертные смотрят на [npc.herHim] с страхом и благоговением!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FIVE_HERCULEAN;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};

	// Intelligence:
	public static AbstractStatusEffect INTELLIGENCE_PERK_0_OLD_WORLD = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Нет Аркана",
			"attIntelligence0",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Из-за того, что Аркана не существует в этом мире, ваша способность к заклинаниям отсутствует.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && !Main.game.isInNewWorld();
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane impotence",
			"attIntelligence0",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, -75f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -75f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_TERRIBLE.toWebHexString() + "'>Сдаться в бою при максимальной похоти</b>",
					"[style.boldMana(Максимальная "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldBad(ограничена до 5)]",
					"[style.boldBad(Уязвимый)] к [style.boldArcane(арканам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ZERO_AIRHEAD.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Несмотря на вашу природную связь с Арканой, вы каким-то образом потеряли большую часть своей силы...";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull] не может использовать Аркану в значительной степени. Это типичный уровень Арканой связи для всех обычных рас этого мира.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && Main.game.isInNewWorld();
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane potential",
			"attIntelligence1",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 5f)),
			Util.newArrayListOfValues(
					"[style.boldОтличный(Устойчивость)] к [style.boldАркана(арканам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ONE_AVERAGE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "У вас есть исключительная природная способность использовать арканы, и в результате вы гораздо сильнее, чем большинство населения Доминиона.";
			} else {
				return UtilText.parse(target, "[npc.Name] имеет приличное знание того, как использовать арканы; равное знанию обычного человека, прошедшего обширную подготовку.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ONE_AVERAGE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane proficiency",
			"attIntelligence2",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 15f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			Util.newArrayListOfValues(
					"[style.boldОтличный(Устойчивость)] к [style.boldАркана(арканам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.TWO_SMART.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] умеет использовать арканы, и [npc.her] заклинания не только легче в использовании, но и наносят больше урона.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.TWO_SMART;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane prowess",
			"attIntelligence3",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 15f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 15f),
					new Value<>(Attribute.DAMAGE_FIRE, 5f),
					new Value<>(Attribute.DAMAGE_ICE, 5f),
					new Value<>(Attribute.DAMAGE_POISON, 5f)),
			Util.newArrayListOfValues(
					"[style.boldОтличный(Устойчивость)] к [style.boldАркана(арканам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.THREE_BRAINY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Вы очень умеете использовать арканы. Ваши заклинания легче в использовании и наносят больше урона, а также у вас есть небольшая склонность к элементарному урону.";
			} else {
				return UtilText.parse(target, "[npc.NameIsFull] очень умеет использовать арканы. [npc.Her] заклинания легче в использовании и наносят больше урона, а [npc.she] также имеет небольшую склонность к элементарному урону.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.THREE_BRAINY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane mastery",
			"attIntelligence4",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 20f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<>(Attribute.DAMAGE_FIRE, 10f),
					new Value<>(Attribute.DAMAGE_ICE, 10f),
					new Value<>(Attribute.DAMAGE_POISON, 10f)),
			Util.newArrayListOfValues(
					"[style.boldОтличный(Устойчивость)] к [style.boldАркана(арканам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FOUR_GENIUS.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Вы очень умеете использовать арканы. Ваши заклинания легче в использовании и наносят больше урона, а также у вас есть значительная склонность к элементарному урону.";
			} else {
				return UtilText.parse(target, "[npc.NameIsFull] чрезвычайно опытен в использовании аркана."
						+ " [npc.Her] заклинания легче вводить в действие и наносят больше урона, а [npc.she] также обладает значительной способностью к нанесению урона с помощью элементов.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FOUR_GENIUS;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane brilliance",
			"attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 25f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<>(Attribute.DAMAGE_FIRE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 15f),
					new Value<>(Attribute.DAMAGE_POISON, 15f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Immune)] к [style.boldArcane(arcane storms)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FIVE_POLYMATH.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Ваша способность использовать аркана соперничает только с самой Лилит. Введение в действие заклинаний происходит так же естественно, как и дыхание.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] способность к использованию аркана соперничает только с самой Лилит. Введение в действие заклинаний происходит так же естественно для [npc.herHim], как и дыхание.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FIVE_POLYMATH;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};

	// Corruption:
	public static AbstractStatusEffect CORRUPTION_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Pure",
			"attCorruption0",
			PresetColour.CORRUPTION_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 25f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ZERO_PURE.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Вы полностью некоррумпированы, и, помимо выполнения самых консервативных сексуальных актов с человеком, которого вы любите, вы вообще не интересуетесь сексом.";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull] полностью некоррумпированы, и, помимо выполнения самых консервативных сексуальных актов с человеком, которого [npc.she] любит, [npc.sheIs] вообще не интересуется сексом.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ZERO_PURE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Vanilla",
			"attCorruption1",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ONE_VANILLA.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Вы открыты к идее случайного секса, но все еще не готовы к выполнению любых экстремальных сексуальных актов.";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull] открыты к идее случайного секса, но не готовы к выполнению любых экстремальных сексуальных актов.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ONE_VANILLA;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"dirty",
			"attCorruption2",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.TWO_HORNY.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Сексуальные акты, которые когда-то могли заставить вас чувствовать себя некомфортно, теперь являются фокусом ваших фантазий, и вы не можете дождаться, чтобы попробовать их на волевом партнере...";
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет грязный взгляд в глазах [npc.her], и вы часто замечаете, как [npc.her] взгляд задерживается на вашем теле.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.TWO_HORNY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Lewd",
			"attCorruption3",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 30f),
					new Value<>(Attribute.FERTILITY, 25f),
					new Value<>(Attribute.VIRILITY, 25f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.THREE_DIRTY.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.hasVagina()) {
					return "Получив силу от фантазий, которые постоянно крутятся в вашем уме, Аркана начинает оказывать физическое воздействие на ваше тело, и вы чувствуете, что с этого момента вам будет гораздо легче забеременеть...";
				} else if (owner.hasPenis()) {
					return "Получив силу от фантазий, которые постоянно крутятся в вашем уме, Аркана начинает оказывать физическое воздействие на ваше тело,"
							+ " и вы чувствуете, что с этого момента вам будет гораздо легче забеременеть...";
				} else {
					return "Получив силу от фантазий, которые постоянно крутятся в вашем уме, Аркана начинает оказывать физическое воздействие на ваше тело, но поскольку у вас нет половых органов, то и не произошло ничего особенного...";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"Получив силу от фантазий, которые постоянно крутятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], делая это гораздо проще для [npc.herHim] забеременеть.");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"Получив силу от фантазий, которые постоянно крутятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], делая это гораздо проще для [npc.herHim] забеременеть других.");
				} else {
					return UtilText.parse(owner,
							"Получив силу от фантазий, которые постоянно крутятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos],"
							+ " но поскольку [npc.she] не имеет половых органов, то и не произошло ничего особенного.");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Lustful",
			"attCorruption4",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.DAMAGE_LUST, 40f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FOUR_LUSTFUL.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.hasVagina()) {
					return "Получив огромную силу от непристойных фантазий, которые постоянно крутятся в вашем уме, Аркана начинает оказывать физическое воздействие на ваше тело,"
							+ " и ты чувствуешь, что тебе будет гораздо легче забеременеть с этого момента...";
				} else if (owner.hasPenis()) {
					return "Получив огромную силу от непристойных фантазий, которые постоянно крутятся в твоей голове, Аркана начинает оказывать физическое воздействие на твое тело,"
							+ " и ты чувствуешь, что тебе будет гораздо легче забеременеть от своих сексуальных партнеров с этого момента...";
				} else {
					return "Получив огромную силу от непристойных фантазий, которые постоянно крутятся в твоей голове, Аркана начинает оказывать физическое воздействие на твое тело,"
							+ " но поскольку у тебя нет половых органов, то и не произошло ничего...";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"Получив огромную силу от непристойных фантазий, которые постоянно крутятся в голове [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos],"
							+ " что делает это гораздо проще для [npc.herHim] забеременеть.");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"Получив огромную силу от непристойных фантазий, которые постоянно крутятся в голове [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos],"
							+ " что делает это гораздо проще для [npc.herHim] забеременеть других.");
				} else {
					return UtilText.parse(owner,
							"Получив огромную силу от непристойных фантазий, которые постоянно крутятся в голове [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos],"
							+ "но поскольку [npc.she] не имеет половых органов, то и ничего особенного не произошло.");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FOUR_LUSTFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
			
	public static AbstractStatusEffect CORRUPTION_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Corrupt",
			"attCorruption5",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f),
					new Value<>(Attribute.DAMAGE_LUST, 50f),
					new Value<>(Attribute.FERTILITY, 75f),
					new Value<>(Attribute.VIRILITY, 75f)),
			Util.newArrayListOfValues("<b style='color: "+ PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+ "'>Demonic mindset</b>")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FIVE_CORRUPT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] полностью и бесповоротно развращен,"
						+ (owner.getSubspeciesOverrideRace()==Race.DEMON
							?"как и подобает демону."
							:"и отчаянно [npc.verb(wish)] чтобы [npc.she] [npc.was] демоном.")
					+ "Развратные мысли и фантазии, которые постоянно крутятся в голове у [npc.her], разблокировали всю силу аркана, сделав [npc.her] тело гиперфертильным и вирильным.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FIVE_CORRUPT;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	// Arousal:
	public static AbstractStatusEffect AROUSAL_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"none",
			"attArousal0",
			PresetColour.AROUSAL_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ZERO_NONE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Ты совсем не возбужден.";
			else
				return UtilText.parse(target, "[npc.NameIsFull] совсем не возбужден.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ZERO_NONE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> effects = new ArrayList<>();
			if(Main.game.isInSex()) {
				if(Main.sex.isInForeplay(target)) {
					effects.add("[style.colourPinkLight(Foreplay)]");
					effects.add("[style.colourMinorBad(-50%)] возбуждение");
				} else {
					effects.add("[style.colourPink(Основной Секс)]");
					effects.add("[style.colourMinorGood(Полный)] возбуждение");
					effects.add(UtilText.parse(target, "<i>Уже достигнув оргазма, [npc.nameIsFull] больше не в предсмертном поцелуе на этом уровне возбуждения</i>"));
				}
			}
			return effects;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"turned on",
			"attArousal1",
			PresetColour.AROUSAL_STAGE_ONE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPinkLight(Foreplay)]",
					"[style.colourMinorBad(-50%)] возбуждение растет")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ONE_TURNED_ON.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Ты начинаешь сильно возбуждаться.";
			else
				return UtilText.parse(target, "[npc.NameIsFull] начинаешь сильно возбуждаться.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ONE_TURNED_ON;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return AROUSAL_PERK_0.getExtraEffects(target);
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"excited",
			"attArousal2",
			PresetColour.AROUSAL_STAGE_TWO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Основной секс)]",
					"[style.colourMinorGood(Полный)] возбуждение растет")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.TWO_EXCITED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Ты начинаешь сильно возбуждаться, и твои мысли теперь сосредоточены на твоих сексуальных желаниях.";
			else
				return UtilText.parse(target, "[npc.NameIsFull] начинаешь сильно возбуждаться.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.TWO_EXCITED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"heated",
			"attArousal3",
			PresetColour.AROUSAL_STAGE_THREE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Основной секс)]",
					"[style.colourMinorGood(Полный)] возбуждение растет")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.THREE_HEATED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Всё начинает становиться довольно горячим. Ты можешь сосредоточиться только на мысли о сексе.";
			else
				return UtilText.parse(target, "[npc.Name] больше не может сосредоточиться ни на чём, кроме секса.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.THREE_HEATED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"passionate",
			"attArousal4",
			PresetColour.AROUSAL_STAGE_FOUR,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Основной Секс)]",
					"[style.colourMinorGood(Полный)] Возбуждение растёт")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FOUR_PASSIONATE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Единственное, о чём ты сейчас думаешь, это как бы достичь оргазма.";
			else
				return UtilText.parse(target, "[npc.NameIsFull] думает только о том, как бы достичь [npc.her] оргазма.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FOUR_PASSIONATE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"imminent orgasm",
			"attArousal5",
			PresetColour.AROUSAL_STAGE_FIVE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Основной Секс)]",
					"[style.colourMinorGood(Полный)] Возбуждение растёт")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FIVE_ORGASM_IMMINENT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Ты чувствуешь, как твой оргазм нарастает. Ты знаешь, что это всего лишь вопрос секунд, прежде чем ты достигнешь оргазма!";
			else
				return UtilText.parse(owner, "[npc.NameIsFull] вот-вот достигнет [npc.her] оргазма!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FIVE_ORGASM_IMMINENT;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	
	// Lust:
	public static AbstractStatusEffect LUST_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"none",
			"attLust0",
			PresetColour.LUST_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ZERO_COLD.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.ZERO_COLD.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ZERO_COLD.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ZERO_COLD;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"turned on",
			"attLust1",
			PresetColour.LUST_STAGE_ONE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ONE_HORNY.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.ONE_HORNY.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ONE_HORNY.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ONE_HORNY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"excited",
			"attLust2",
			PresetColour.LUST_STAGE_TWO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.TWO_AMOROUS.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.TWO_AMOROUS.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.TWO_AMOROUS.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.TWO_AMOROUS;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"heated",
			"attLust3",
			PresetColour.LUST_STAGE_THREE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.THREE_LUSTFUL.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.THREE_LUSTFUL.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.THREE_LUSTFUL.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.THREE_LUSTFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"passionate",
			"attLust4",
			PresetColour.LUST_STAGE_FOUR,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FOUR_IMPASSIONED.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.FOUR_IMPASSIONED.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FOUR_IMPASSIONED.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FOUR_IMPASSIONED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"passionate",
			"attLust5",
			PresetColour.LUST_STAGE_FIVE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FIVE_BURNING.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.FIVE_BURNING.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FIVE_BURNING.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FIVE_BURNING;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	
	
	
	// WEATHER & LOCATION EFFECTS:
	
	public static AbstractStatusEffect WEATHER_PROLOGUE = new AbstractStatusEffect(100,
			"Strange Atmosphere",
			"weatherNightStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Сегодня вечером в музее царит странная атмосфера, и ты невольно чувствуешь себя невероятно возбужденным...";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInNewWorld();
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLEAR = new AbstractStatusEffect(100,
			"Clear skies",
			"weatherDayClear",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			if(Main.game.isDayTime()) {
				sb.append("Солнце светит с абсолютно чистого голубого неба.");
			} else {
				sb.append("Луна и звезды светят с абсолютно чистого ночного неба.");
			}
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя в данный момент нет никаких признаков грозы, [npc.nameIsFull] все равно может поглощать фоновую арканическую энергию для питания [npc.herself].");
			} else {
				sb.append(" Хотя в данный момент нет никаких признаков грозы, [npc.name] все равно может чувствовать эффекты арканы, проявляющиеся в виде повышенного либидо.");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLEAR && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayClear();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightClear();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(арканы)]");
			} else {
				exEff.add("[style.colourArcane(Усиленное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(арканической бури)]:");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLOUD = new AbstractStatusEffect(100,
			"Cloudy skies",
			"weatherDayCloudy",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Погода меняется в одночасье, и в данный момент она облачная, с вероятностью дождя.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя в данный момент нет никаких признаков грозы, [npc.nameIsFull] все равно может поглощать фоновую арканическую энергию для питания [npc.herself].");
			} else {
				sb.append(" Хотя в данный момент нет признаков грозы, [npc.name] все еще чувствует влияние аркана, проявляющегося в виде повышенного либидо.");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLOUD && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayCloud();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightCloud();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Gaining)] энергия из фона [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Enhanced libido)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(arcane storm)]:");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_RAIN = new AbstractStatusEffect(100,
			"Rain",
			"weatherDayRain",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append(" Тяжелые дождевые облака над головой наконец-то разорвались, выпустив внезапный, ливень.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя в данный момент нет признаков грозы, [npc.nameIsFull] все еще может поглощать энергию аркана из фона для питания [npc.herself].");
			} else {
				sb.append(" Хотя в данный момент нет признаков грозы, [npc.name] все еще чувствует влияние аркана, проявляющегося в виде повышенного либидо.");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.RAIN && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayRain();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightRain();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Gaining)] энергия из фона [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Enhanced libido)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(arcane storm)]:");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_SNOW = new AbstractStatusEffect(100,
			"Snow",
			"weatherDaySnow",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasSnowedThisWinter)) {
				Main.game.getDialogueFlags().values.add(DialogueFlagValue.hasSnowedThisWinter);
				
				if(Main.game.getReindeerOverseers().isEmpty()) {
					try {
						for(int i=0; i<2; i++) {
							Main.game.addNPC(new ReindeerOverseer(Gender.M_P_MALE), false);
						}
						for(int i=0; i<2; i++) {
							Main.game.addNPC(new ReindeerOverseer(Gender.F_V_B_FEMALE), false);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					for(NPC npc : Main.game.getReindeerOverseers()) {
						npc.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_STREET, true);
					}
				}
				return "<p>"
							+ "Отесняющие, темно-серые облака, которые висели над Доминионом последние несколько часов, наконец, разорвались."
							+ "Крупные, пушистые снежинки медленно опускаются сверху, и хотя первые несколько кристаллов быстро тают при контакте с землей ниже,"
								+ "не проходит и нескольких минут, как тонкий белый слой порошкообразного снега оседает на крышах и дорожках столицы."
						+ "</p>"
						+ "<p>"
							+ "То, что сначала казалось не более чем быстрой метелью, быстро превращается в дикую снежную бурю, и менее чем через час толстый белый слой снега начинает покрывать улицы."
							+ "Еще через час снег накапливается в таких количествах, что все передвижения по городу замедляются до ползущего темпа."
							+ "Несколько бесов пытаются использовать свое аркановое пламя, чтобы пробить путь сквозь замерзшие снежные заносы, но снегопад настолько сильный, что даже их мощные заклинания кажутся малоэффективными."
						+ "</p>"
						+ "<p>"
							+ "Как раз когда кажется, что вся столица вот-вот остановится, слабый звон колокольчиков возвещает о прибытии спасителей города."
							+ "Путешествуя на большом расстоянии от своей замерзшей тундровой родины, множество бесов в форме оленей спускается на Доминион."
							+ "Их большие, копытные копыта позволяют им с легкостью передвигаться по заснеженным улицам, и они быстро делятся на многочисленные группы, занимающиеся уборкой снега,"
								+ "перед тем как отправиться в разные районы города."
							+ "Под руководством особенно крупных, мускулистых людей, олени-мутанты быстро приступили к расчистке пути через снег."
						+ "</p>"
						+ "<p>"
							+ "Хотя погода отказывается утихать, всего за несколько часов впечатляющие олени-мутанты расчищают весь снег с улиц."
							+ "Скоро распространяется слух о том, что посетители будут оставаться в Dominion, чтобы работать до конца февраля, обеспечивая функционирование города в течение этих холодных зимних месяцев."
						+ "</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Тяжелые облака над головой наконец-то разорвались, выпустив на землю ниже шквал блестящих белых снежинок.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("Хотя в данный момент нет признаков шторма, [npc.nameIsFull] все еще может поглощать фоновую арканическую энергию для питания [npc.herself].");
			} else {
				sb.append("Хотя в данный момент нет признаков шторма, [npc.name] все еще может чувствовать эффекты арканы, проявляющиеся в виде повышенного либидо.");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.SNOW && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDaySnow();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightSnow();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(арканы)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(арканической бури)]:");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_GATHERING = new AbstractStatusEffect(100,
			"Gathering storm",
			"weatherDayStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Над [npc.name] висит бушующая масса густых черных штормовых облаков."
					+ " Всплески розовой и фиолетовой энергии видны прямо под их поверхностью, и [npc.she] [npc.verb(realise)] что аркана буря вот-вот разразится.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM_GATHERING && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormIncoming();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormIncoming();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Gaining)] энергия из фона [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Enhanced libido)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей аркана бури:");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM = new AbstractStatusEffect(100,
			"Arcane storm",
			"weatherDayStorm",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || (Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS)) {
					return "";
				}
				
				StringBuilder sb = new StringBuilder();
				
				sb.append("<p>"
							+ "Яркая розовая вспышка внезапно освещает весь город Dominion, заставляя тех немногих жителей, которые все еще бродят по улицам, смотреть в небо."
							+ " Высоко над ними, угрожающие бури наконец-то разразились, и бурлящая масса аркана энергии наконец-то оживает."
						+ "</p>"
						+ "<p>"
							+ "В течение нескольких мгновений, призрачная серия непристойных стонов и экстатических криков начинает эхом разноситься по всему городу, и поскольку аркана гром проникает в сознание тех, кто не обладает сильной аурой,"
							+ "они не могут думать ни о чем, кроме секса."
						+ "</p>");
				
				WorldRegion region = target.getWorldLocation().getWorldRegion();
				
				if(region!=WorldRegion.DOMINION && region!=WorldRegion.HARPY_NESTS) {
					sb.append("<p>"
							+ "Хотя он и проходит высоко над Доминионом, шторм не ограничивается только этим городом, и быстро распространяется по Полям Фолои и в окружающие леса и степные дикие места."
							+ "Как цепная реакция, вспышки фиолетовой молнии проносятся по небу во всех направлениях, за которыми быстро следуют эротические стоны аркана."
						+ "</p>");

					if(region==WorldRegion.FIELD_CITY
							|| region==WorldRegion.FIELDS
							|| region==WorldRegion.WOODLAND
							|| region==WorldRegion.RIVER) {
						
						if(target.getLocationPlace().isStormImmune()) {
							sb.append("<p>"
										+ "Хотя вы и невосприимчивы к его эффектам, вы можете почувствовать, что шторм значительно слабее здесь, далеко от эпицентра."
										+ "Хотя очень вероятно, что любой, кого вы встретите в этой области, будет более возбужденным, чем обычно, вы представляете, что они смогут контролировать себя достаточно, чтобы сопротивляться возбуждающим эффектам шторма."
									+ "</p>");
						} else {
							sb.append("<p>"
										+ "Хотя обычно вы уязвимы к его эффектам, вы чувствуете себя только немного более возбужденным, чем обычно; доказательство того, что шторм значительно слабее здесь, далеко от эпицентра."
										+ "Вы быстро понимаете, что любой, кого вы встретите в этой области, наверняка сможет контролировать себя достаточно, чтобы сопротивляться обычным возбуждающим эффектам, так же как и вы."
									+ "</p>");
						}
						
					} else {
						sb.append("<p>"
									+ "Поскольку эпицентр шторма находится прямо над Доминионом, вы находитесь достаточно далеко, чтобы слышать только самые слабые арканы стоны в воздухе."
									+ " Хотя, вероятно, любой, кого вы встретите здесь, будет немного более возбужденным, чем обычно, вы представляете, что они легко смогут сопротивляться возбуждающим эффектам шторма."
								+ "</p>");
					}
				}
				
				return sb.toString();
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Огромные полосы розового и фиолетового молнии дугой через небо, когда арканный шторм бушует высоко над Доминионом.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Арканный секс-кукла, [npc.nameIsFull], наполнена колоссальным количеством энергии!");
				
			} else {
				if(target.getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && target.getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS) {
					sb.append(" [npc.NameIsFull] достаточно далеко от эпицентра шторма, чтобы быть почти полностью иммунным к его возбуждающим эффектам.");
				} else {
					sb.append(" Арканная аффинити [npc.NamePos] сделала [npc.herHim] почти полностью иммунным к возбуждающим эффектам арканных штормов.");
				}
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GenericPlace targetPlace =
					(target.isElemental() && ((Elemental)target).getSummoner()!=null)
						?((Elemental)target).getSummoner().getLocationPlace()
						:target.getLocationPlace();
			
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& !targetPlace.isStormImmune()
					&& (!target.isVulnerableToArcaneStorm() || (target.getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && target.getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				return Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 1000f));
			} else {
				return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f));
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Получение)] [style.colourExcellent(массивная)] энергия из [style.colourArcane(арканный шторм)]");
			} else {
				exEff.add("[style.colourArcane(Усиленная либидо)]");
				exEff.add("[style.boldExcellent(Двойная)] [style.colourArcane(эссенция)] от секса & боя");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до [style.colourArcane(заканчивается шторм)]:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_VULNERABLE = new AbstractStatusEffect(100,
			"Arcane storm",
			"weatherDayStorm",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || (Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS)) {
					return "";
				}
				
				return "<p>"
							+ "Яркая розовая вспышка внезапно освещает весь город Доминион, заставляя тех немногих жителей, которые все еще бродят по улицам, смотреть в небо."
							+ "Высоко над ними, угрожающие грозовые облака наконец разорвались, и бурлящая масса арканой энергии наконец ожила."
						+ "</p>"
						+ "<p>"
							+ "В течение нескольких мгновений, призрачная серия непристойных стонов и экстатических криков начинает эхом разноситься по всему городу."
							+ "Хотя вы вошли в этот мир с достаточно высокой арканой аффинити, чтобы быть защищенным от эффектов шторма, вы в итоге потеряли большую часть своей силы,"
								+ "и вы не можете не выпустить отчаянный [pc.moan], когда начинаете чувствовать себя невероятно возбужденной."
						+ "</p>"
						+ "<p>"
							+ "Продолжая свой путь, вы обнаруживаете, что надеетесь, что встретите кого-то, кто захочет дать вам хорошую ебу..."
						+ "</p>";
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Огромные полосы розового и фиолетового молнии пронзают небо, когда арканой шторм бушует высоко над Доминионом.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append("Как арканая секс-кукла, [npc.nameIsFull], наполненная колоссальным количеством энергии!");
				
			} else {
				sb.append("[npc.NameIsFull] сильно подвержена влиянию продолжающегося арканого шторма, и не может думать ни о чем, кроме секса...");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GenericPlace targetPlace =
					(target.isElemental() && ((Elemental)target).getSummoner()!=null)
						?((Elemental)target).getSummoner().getLocationPlace()
						:target.getLocationPlace();
			
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& target.isVulnerableToArcaneStorm()
					&& (!targetPlace.isStormImmune() && !target.isProtectedFromArcaneStorm())
					&& (target.getWorldLocation().getWorldRegion()==WorldRegion.DOMINION || target.getWorldLocation().getWorldRegion()==WorldRegion.HARPY_NESTS);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.HEALTH_MAXIMUM, 1000f));
			} else {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.RESISTANCE_LUST, -100f),
						new Value<>(Attribute.RESTING_LUST, 50f));
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Получение)] [style.colourExcellent(массивная)] энергия от [style.colourArcane(арканого шторма)]");
			} else {
				exEff.add("[style.colourArcane(Усиленная либидо)]");
				exEff.add("[style.colourArcane(Overwhelming Lust)]");
				exEff.add("[style.boldExcellent(Double)] [style.colourArcane(essence gains)] from sex & combat");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Time until [style.colourArcane(storm ends)]:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_PROTECTED = new AbstractStatusEffect(100,
			"Arcane storm (protected)",
			"weatherDayStorm",
			PresetColour.GENERIC_GOOD,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || (Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && Main.game.getPlayer().getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS)) {
					return "";
				}
				
				return "<p>"
							+ "A bright-pink flash suddenly illuminates the entire city of Dominion, causing those few residents still prowling the streets to look skywards."
							+ " High up above them, the threatening storm clouds have finally broken, and a roiling mass of arcane energy finally crackles into life."
						+ "</p>"
						+ "<p>"
							+ "Within moments, a ghostly series of lewd moans and ecstatic screams start echoing throughout the city, and as the arcane thunder penetrates into the minds of those without a strong aura,"
							+ " they find themselves unable to think of anything but sex..."
						+ "</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Huge streaks of pink and purple lightning arc through the sky as an arcane storm rages high above Dominion.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Although [npc.nameIsFull] protected from the storm, [npc.sheIs] still able to absorb background arcane energy to power [npc.herself].");
				
			} else {
				sb.append(" Хотя [npc.she] всё ещё может чувствовать его эффекты, принимающие форму повышенного либидо, [npc.nameIsFull] в настоящее время защищён от большей части гнева бури.");
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GenericPlace targetPlace =
					(target.isElemental() && ((Elemental)target).getSummoner()!=null)
						?((Elemental)target).getSummoner().getLocationPlace()
						:target.getLocationPlace();
			
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& (targetPlace.isStormImmune() || target.isProtectedFromArcaneStorm());
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormProtected();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormProtected();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> exEff = new ArrayList<>();
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				exEff.add("[style.colourGood(Получение)] энергии из фона [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Усиленное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до [style.colourArcane(конца бури)]:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};

	public static AbstractStatusEffect BLINDED = new AbstractStatusEffect(90,
			"Blinded",
			"blinded",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_RED,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -50f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -50f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -50f),
					new Value<>(Attribute.DAMAGE_SPELLS, -50f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"[npc.NameHasFull] был фактически ослеплён, и как таковой [npc.she] [npc.is] с трудом навигирует через [npc.her] окружение. В этом состоянии [npc.she] будет крайне неэффективен в бою!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && !target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect BLINDED_NEGATED = new AbstractStatusEffect(90,
			"Ослеплён (Эхо-локация)",
			"blinded_negated",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -5f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -5f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -5f),
					new Value<>(Attribute.DAMAGE_SPELLS, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Хотя [npc.name] должен быть фактически ослеплён, [npc.she] [npc.is] способен эффективно навигировать через [npc.her] окружение, используя [npc.her] способность эхо-локации!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect DARKNESS = new AbstractStatusEffect(90,
			"Darkness",
			"darkness",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_RED,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -25f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -25f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -25f),
					new Value<>(Attribute.DAMAGE_SPELLS, -25f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Область, в которой [npc.name] [npc.verb(find)] [npc.herself] путешествует, очень тёмная, и поскольку [npc.she] [npc.verb(lack)] любых средств освещения области, [npc.she] [npc.is] с трудом видит, куда [npc.sheIs] идёт!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isInDarkness();
		}
	};

	public static AbstractStatusEffect DARKNESS_NEGATED = new AbstractStatusEffect(90,
			"Darkness (Negated)",
			"darkness_negated",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREY_LIGHT,
			true,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues()) {
		@Override
		public EffectBenefit getBeneficialStatus() {
			return EffectBenefit.NEUTRAL;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Область, в которой [npc.name] [npc.verb(find)] [npc.herself] путешествует, очень тёмная, но несмотря на это, [npc.she] [npc.is] способен видеть [#npc.getDescriptionInDarkness()].");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().isDark() && !target.isInDarkness();
		}
	};


	public static AbstractStatusEffect SHORT_SIGHTED = new AbstractStatusEffect(90,
			"impaired vision",
			"short_sighted",
			PresetColour.BASE_BLACK,
			PresetColour.GENERIC_TERRIBLE,
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -50f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -50f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"[npc.Name] не видит очень хорошо без ношения очков по рецепту...");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasPerkAnywhereInTree(Perk.SPECIAL_SHORT_SIGHTED)
					&& !target.isDoll()
					&& !target.isPerfectVision()
					&& !target.hasClothingWithTag(ItemTag.PRESCRIPTION_GLASSES, true, false);
		}
	};
	public static AbstractStatusEffect ENCHANCED_VISION = new AbstractStatusEffect(90,
			"enhanced vision",
			"perfect_vision",
			PresetColour.BASE_BLACK,
			PresetColour.GENERIC_EXCELLENT,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasPerkAnywhereInTree(Perk.SPECIAL_SHORT_SIGHTED)) {
					return UtilText.parse(target,
							"Благодаря волшебным рецептурным линзам в очках [npc.her], не только [npc.namePos] зрительное нарушение больше не является проблемой, но и [npc.she] [npc.is] также получают пользу от улучшенного зрения!");
				}
				return UtilText.parse(target,
						"Хотя [npc.she] [npc.do] не страдает от какого-либо зрительного нарушения, волшебные очки в [npc.namePos] очках улучшают зрение [npc.her]!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasClothingWithTag(ItemTag.PRESCRIPTION_GLASSES, true, false);
		}
	};
	
	// RACES:
	// HUMAN:
	public static AbstractStatusEffect PURE_HUMAN_PROLOGUE = new AbstractStatusEffect(1000,
			"human",
			null,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Ты человек, как и все остальные в этом мире.";
			else
				return "[npc.NameIsFull] человек, как и все остальные в этом мире.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HUMAN
					&& target.getRaceStage() == RaceStage.HUMAN
					&& !Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	};
	
	public static AbstractStatusEffect SUBSPECIES_BONUS = new AbstractStatusEffect(1000,
			"",
			null,
			PresetColour.CLOTHING_WHITE,
			true,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return "Скрытый бонус подрасы";
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getName(null)+" ("+target.getSubspecies().getName(target.getBody())+")";
			}
			return (target.isFeral()?"[style.colourFeral(Feral)] ":"")+target.getSubspecies().getName(target.getBody());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return UtilText.parse(target, "Хотя [npc.namePos] раса скрыта, они все равно получают бонусы к атрибутам...");
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getStatusEffectDescription(target);
			} else {
				return target.getSubspecies().getStatusEffectDescription(target);	
			}
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			
			// Add subspecies appearance change:
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				String subspeciesName = target.getSubspecies().getName(target.getBody());
				additionalDescriptions.add(
						new Value<>(1,
								UtilText.parse(target,
										"[npc.NameIsFull] "
										+"<span style='color:"+target.getSubspeciesOverride().getColour(target).toWebHexString()+";'>"+UtilText.addDeterminer(target.getSubspeciesOverride().getName(null))+"</span>"
										+ " но [npc.she] [npc.verb(appear)] быть "
										+"<span style='color:"+target.getSubspecies().getColour(target).toWebHexString()+";'>"+UtilText.addDeterminer(subspeciesName)+"</span>!")));
			}

			// Add body material modifiers:
			BodyMaterial material = target.getBodyMaterial();
			if(material.getAttributeModifiers(target)!=null
					|| material.getExtraEffects(target)!=null) {
				int lineHeight = 1;
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parse(target, "<i style='color:"+material.getColour().toWebHexString()+";'>[npc.NamePos] "+material.getName()+" body grants [npc.herHim]:</i>"));
				if(material.getAttributeModifiers(target)!=null) {
					for(String s : attributeModifiersToStringList(material.getAttributeModifiers(target))) {
						sb.append("<br/>"+s);
						lineHeight++;
					}
				}
				if(material.getExtraEffects(target)!=null) {
					for(String s : material.getExtraEffects(target)) {
						sb.append("<br/>"+s);
						lineHeight++;
					}
				}
				additionalDescriptions.add(new Value<>(lineHeight, sb.toString()));
			}
			
			return additionalDescriptions;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInNewWorld();
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			LinkedHashMap<AbstractAttribute, Float> attMods;
			
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				attMods = new LinkedHashMap<>(target.getSubspeciesOverride().getStatusEffectAttributeModifiers(target));
			} else {
				attMods = new LinkedHashMap<>(target.getSubspecies().getStatusEffectAttributeModifiers(target));
			}
			
			BodyMaterial material = target.getBodyMaterial();
			if(material.getAttributeModifiers(target)!=null) {
				for(Entry<AbstractAttribute, Float> entry : material.getAttributeModifiers(target).entrySet()) {
					attMods.putIfAbsent(entry.getKey(), 0f);
					attMods.put(entry.getKey(), attMods.get(entry.getKey())+entry.getValue());
				}
			}
			
			return attMods;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getExtraEffects(target);
			}
			return target.getSubspecies().getExtraEffects(target);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			LinkedHashMap<AbstractAttribute, Float> attMods;

			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				attMods = new LinkedHashMap<>(target.getSubspeciesOverride().getStatusEffectAttributeModifiers(target));
			} else {
				attMods = new LinkedHashMap<>(target.getSubspecies().getStatusEffectAttributeModifiers(target));
			}
			
			ArrayList<String> fullModList = new ArrayList<>(attributeModifiersToStringList(attMods));
			fullModList.addAll(getExtraEffects(target));
			
			return fullModList;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isRaceConcealed()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown();
			}
			return owner.getSubspecies().getSVGString(owner);
		}
	};

	public static AbstractStatusEffect AQUATIC_TAIL_POSITIVE = new AbstractStatusEffect(90,
			"Aquatic harmony",
			"aquatic_positive",
			PresetColour.GENERIC_GOOD,
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, 1f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			Util.newArrayListOfValues(
					"[style.boldBlueLight(Lost legs)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "As you increase your proximity to the nearby body of water, you feel an intense tingling start to run up the length of your legs."
					+ " Without any further warning of what's about to happen, your muscles involuntarily clench, pressing your legs together and causing you to let out a startled cry."
					+ " Before you're able to react, your legs rapidly fuse together and transform, and within moments your lower body has assumed its true, tailed form!"
					+ "<p style='text-align:center;'>"
						+ "[style.italicsMinorBad(Вы больше не можете надевать одежду в слоты для ног и стоп!)]"
					+ "</p>"
					+ target.postTransformationCalculation(); // To handle clothing removals
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Как [npc.nameIsFull] [npc.a_race], и [npc.she] [npc.has] доступ к ближайшему водоему, нижняя часть тела [npc.her] превратилась в свою истинную форму, похожую на хвост, что заставляет [npc.herHim] чувствовать себя очень комфортно!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()==LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

	public static AbstractStatusEffect AQUATIC_TAIL_NEGATIVE = new AbstractStatusEffect(90,
			"Рыба вне воды",
			"aquatic_negative",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues(
					"[style.boldTan(Выросли две ноги)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "Находясь в районе, где нет большого водоема, вы вдруг чувствуете, как ваша хвостатая нижняя часть тела начинает покалывать."
					+ "Без какого-либо предупреждения о том, что вот-вот произойдет, ваши мышцы непроизвольно сжимаются, заставляя вас вскрикнуть от удивления."
					+ "Прежде чем вы успеваете среагировать, ваш хвост быстро разделяется и превращается в пару ног, которые, хотя и позволяют вам ходить и бегать по суше, чувствуются для вас очень непривычно."
					+ "<p style='text-align:center;'>"
						+ "[style.italicsMinorGood(Вы теперь можете надевать одежду в слоты для ног и стоп!)]"
					+ "</p>"
					+ target.postTransformationCalculation(); // To handle clothing checks
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Как [npc.nameIsFull] [npc.a_race], и нет водоема рядом, нижняя часть тела [npc.her] превратилась в пару ног, что заставляет [npc.herHim] [npc.verb(feel)] себя очень некомфортно!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()==LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};
	
	public static AbstractStatusEffect AQUATIC_POSITIVE = new AbstractStatusEffect(90,
			"Aquatic harmony",
			"aquatic_positive",
			PresetColour.GENERIC_GOOD,
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Как [npc.nameIsFull] [npc.a_race], и [npc.she] [npc.has] доступ к ближайшему водоему, [npc.she] [npc.verb(feel)] очень комфортно!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()!=LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

	public static AbstractStatusEffect AQUATIC_NEGATIVE = new AbstractStatusEffect(90,
			"Рыба вне воды",
			"aquatic_negative",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Как [npc.nameIsFull] [npc.a_race], и нет водоема рядом, [npc.she] чувствует себя очень некомфортно!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()!=LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

//	public static AbstractStatusEffect OCCUPATION_PERK = new AbstractStatusEffect(1000,
//			"",
//			null,
//			PresetColour.CLOTHING_WHITE,
//			true,
//			null,
//			null) {
//		@Override
//		public String getName(GameCharacter target) {
//			return target.getOccupation().getAssociatedPerk().getName(target);
//		}
//		@Override
//		public String getDescription(GameCharacter target) {
//			return target.getOccupation().getAssociatedPerk().getDescription(target);
//		}
////		@Override
////		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
////			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
////			
////			return additionalDescriptions;
////		}
//		@Override
//		public boolean isConditionsMet(GameCharacter target) {
//			return !target.isPlayer() || Main.game.isInNewWorld(); //TODO
//		}
//		@Override
//		public List<String> getModifiersAsStringList(GameCharacter target) {
//			List<String> extraModifiersList = new ArrayList<>();
//			return extraModifiersList;
//		}
//		@Override
//		public String getSVGString(GameCharacter owner) {
//			return owner.getOccupation().getAssociatedPerk().getSVGString(owner);
//		}
//	};
	
	// SEXUAL ORIENTATIONS:
	
	public static AbstractStatusEffect ORIENTATION_ANDROPHILIC = new AbstractStatusEffect(90,
			"androphilic",
			"orientation_androphilic",
			PresetColour.MASCULINE,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(-50%)] [style.colourDmgLust(Похоть damage)] получен от [style.colourFeminine(женских оппонентов)]",
					"[style.colourBad(-50%)] [style.colourDmgLust(Похоть damage)] нанесен [style.colourFeminine(женским оппонентам)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] сексуально привлекает к себе мужчин, и [npc.verb(find)] женщин, что является огромным отталкивающим фактором.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.ANDROPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ORIENTATION_GYNEPHILIC = new AbstractStatusEffect(90,
			"gynephilic",
			"orientation_gynephilic",
			PresetColour.FEMININE,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(-50%)] [style.colourDmgLust(Похоть damage)] получен от [style.colourMasculine(мужских оппонентов)]",
					"[style.colourBad(-50%)] [style.colourDmgLust(Похоть damage)] нанесен [style.colourMasculine(мужским оппонентам)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] сексуально привлекает к себе женщин, и [npc.verb(find)] мужчин, что является огромным отталкивающим фактором.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.GYNEPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect ORIENTATION_AMBIPHILIC = new AbstractStatusEffect(90,
			"ambiphilic",
			"orientation_ambiphilic",
			PresetColour.ANDROGYNOUS,
			true,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] сексуально привлекает к себе как мужчин, так и женщин, и [npc.do]н't находит никакого уровня женственности, который бы отталкивал.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.AMBIPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	

	// CLOTHING:

	public static AbstractStatusEffect CLOTHING_FEMININITY = new AbstractStatusEffect(85,
			"clothing too feminine",
			"clothingFemininity",
			PresetColour.CLOTHING_PINK_LIGHT,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Some of [npc.namePos] clothes are too feminine for [npc.her] masculine figure."
						+ " [npc.She] [npc.verb(find)] [npc.herself] incredibly embarrassed by wearing such clothing and [npc.is] struggling to think clearly.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)
					|| target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE)
					|| target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return false;
			}
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.getClothingType().getFemininityMinimum() > target.getFemininityValue()) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_MASCULINITY = new AbstractStatusEffect(85,
			"clothing too masculine",
			"clothingMasculinity",
			PresetColour.CLOTHING_BLUE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Some of [npc.namePos] clothes are too masculine for [npc.her] feminine figure."
						+ " [npc.She] [npc.verb(find)] [npc.herself] incredibly embarrassed by wearing such clothing and [npc.is] struggling to think clearly.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)
					|| target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE)
					|| target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return false;
			}
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.getClothingType().getFemininityMaximum() < target.getFemininityValue()) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_CUM = new AbstractStatusEffect(80,
			"dirty clothing",
			"clothingCummedIn",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return UtilText.parse(target,
						"Some of [npc.namePos] clothes have been covered in cum, milk or other sexual fluids."
							+ " Wearing such filthy clothing reinforces the fact that [npc.sheIs] nothing more than a sex toy.");
			}
			return UtilText.parse(target,
					"Some of [npc.namePos] clothes have been covered in cum, milk or other sexual fluids."
						+ " [npc.SheIs] feeling incredibly embarrassed to be walking around in such filthy clothing.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Your smelly clothes seem to attract trouble.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getItemTags(),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_CUM_MASOCHIST = new AbstractStatusEffect(80,
			"dirty clothing",
			"clothingCummedInMasochist",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Some of your clothes have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly turned on to be walking around in such filthy clothing.";
			} else {
				return UtilText.parse(target, "Some of [npc.namePos] clothes have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly turned on to be walking around in such filthy clothing.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Your smelly clothes seem to attract trouble.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getItemTags(),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect BODY_CUM = new AbstractStatusEffect(80,
			"dirty body",
			"dirtyBody",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			List<InventorySlot> slotsToClean = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			for(AbstractClothing clothing : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
				if(target.getDirtySlots().contains(clothing.getSlotEquippedTo())) {
					InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
					Set<ItemTag> tags = clothing.getItemTags();
					slotsToClean.add(slotEquippedTo);
					
					boolean seals = tags.contains(ItemTag.SEALS_ANUS)
										|| tags.contains(ItemTag.SEALS_VAGINA)
										|| tags.contains(ItemTag.SEALS_NIPPLES);
					
					if(clothing.getSlotEquippedTo()==InventorySlot.ANUS && (tags.contains(ItemTag.SEALS_ANUS) || tags.contains(ItemTag.PLUGS_ANUS))
							|| clothing.getSlotEquippedTo()==InventorySlot.VAGINA && (tags.contains(ItemTag.SEALS_VAGINA) || tags.contains(ItemTag.PLUGS_VAGINA))
							|| clothing.getSlotEquippedTo()==InventorySlot.NIPPLE && (tags.contains(ItemTag.SEALS_NIPPLES) || tags.contains(ItemTag.PLUGS_NIPPLES))) {
						if(sb.length()>0) {
							sb.append("<br/>");
						}
						sb.append("You use your <b>"+clothing.getDisplayName(true)+"</b> to clean your "+clothing.getSlotEquippedTo().getName()
								+(seals
										?" as you equip "+(clothing.getClothingType().isPlural()?"them":"it")
										:" как вы вставляете "+(clothing.getClothingType().isPlural()?" их в ваши отверстия":" её в ваше отверстие"))
								+".");
						
					} else {
						if(!clothing.isDirty()) {
							clothing.setDirty(target, true);
							if(sb.length()>0) {
								sb.append("<br/>");
							}
							sb.append(" Вы используете свой <b>"+clothing.getDisplayName(true)+"</b> для очистки вашего "+clothing.getSlotEquippedTo().getName()
									+", <b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" в процессе</b>.");
						}
					}
					
				} else {
					for(InventorySlot blockedSlot : clothing.getIncompatibleSlots(target, clothing.getSlotEquippedTo())) {
						if(target.getDirtySlots().contains(blockedSlot)) {
							slotsToClean.add(blockedSlot);
							if(!clothing.isDirty()) {
								clothing.setDirty(target, true);
								if(sb.length()>0) {
									sb.append("<br/>");
								}
								sb.append(" Вы используете свой <b>"+clothing.getDisplayName(true)+"</b> для очистки вашего "+clothing.getSlotEquippedTo().getName()
										+", <b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" в процессе</b>.");
							}
						}
					}
				}
			}
			for(InventorySlot slotToClean : slotsToClean) {
				target.removeDirtySlot(slotToClean, false);
			}
			
			if(target.isPlayer()) {
				return sb.toString();
			}
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
				return UtilText.parse(target,
						"Некоторые части тела [npc.namePos] были покрыты спермой, молоком или другими сексуальными жидкостями."
							+ "Нахождение в таком грязном состоянии подчёркивает, что [npc.sheIs] не более чем секс-игрушка.");
			}
			return UtilText.parse(target, "Некоторые части тела [npc.namePos] были покрыты спермой, молоком или другими сексуальными жидкостями."
					+ " [npc.SheIs] чувствует себя невероятно смущённым, ходя в таком грязном состоянии.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Опортунистические Нападающие</b><br/>Твоё вонючее тело привлекает неприятности.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (!isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	
	public static AbstractStatusEffect BODY_CUM_MASOCHIST = new AbstractStatusEffect(80,
			"dirty body",
			"dirtyBodyMasochist",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return StatusEffect.BODY_CUM.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Некоторые части твоего тела были покрыты спермой, молоком или другими сексуальными жидкостями."
						+ "Ты чувствуешь себя невероятно возбуждённой, ходя в таком грязном состоянии.";
			} else {
				return UtilText.parse(target, "Некоторые части тела [npc.namePos] были покрыты спермой, молоком или другими сексуальными жидкостями."
						+ " [npc.sheIs] чувствует себя невероятно возбуждённой, ходя в таком грязном состоянии.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Твоё зловонное тело, похоже, привлекает неприятности.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	
	public static AbstractStatusEffect MARKED_BY_MUSK = new AbstractStatusEffect(80,
			"запах мускуса",
			"marked_by_musk",
			PresetColour.BASE_YELLOW_LIGHT,
			PresetColour.BASE_ORANGE_LIGHT,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESTING_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.getMuskMarkerCharacters().isEmpty()) {
				return UtilText.parse(target,
						"[npc.NameHasFull] был отмечен мускусом, и [npc.verb(find)] [npc.herself] возбуждается от этого запаха [npc.she] теперь [npc.verb(carry)].");
			} else {
				return UtilText.parse(target,
						"[npc.NameHasFull] был отмечен мускусом, и [npc.verb(find)] [npc.NameHasFull] возбуждается от этого запаха @@T1@@ теперь [npc.verb(carry)]."+Util.charactersToStringListOfNames(target.getMuskMarkerCharacters(), true)
							+", и [npc.verb(find)] [npc.herself], которая возбуждается от характерного запаха [npc.she], теперь [npc.verb(carry)].");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			return new Value<>(1, "[style.boldMinorBad(-10%)] [style.boldHealth(Максимальное ");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isMuskContentEnabled() && !target.getMuskMarkers().isEmpty();
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT = new AbstractStatusEffect(80,
			"unstable enchantments",
			"unstable_enchantment_1",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues("[style.boldMinorBad(-10%)] [style.boldMana(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"@@T0@@ не может справиться с количеством арканических заклинаний, наложенных на @@T1@@ оружие, одежду и татуировки, и поэтому @@T2@@ страдает от некоторых негативных побочных эффектов."+Attribute.MANA_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] не может справиться с количеством атрибутов, вложенных в [npc.her] оружие, одежду и татуировки, и поэтому [npc.is] страдает от некоторых негативных побочных эффектов."
							+ "Если [npc.she] продолжит надевать зачарованные предметы, это наверняка станет намного хуже...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>0 && overBy<10;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT_2 = new AbstractStatusEffect(80,
			"volatile enchantments",
			"unstable_enchantment_2",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			Util.newArrayListOfValues("[style.boldBad(-50%)] [style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"[style.boldBad(-50%)] [style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] не может справиться с количеством атрибутных заклинаний, введенных в [npc.her] оружие, одежду и татуировки, и поэтому [npc.is] страдает от некоторых серьезных негативных побочных эффектов."
							+ " Если [npc.she] продолжает использовать зачарованные предметы, это наверняка станет намного хуже...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>=10 && overBy<20;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT_3 = new AbstractStatusEffect(80,
			"shattered enchantments",
			"unstable_enchantment_3",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 100f)),
			Util.newArrayListOfValues("[style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")] [style.boldTerrible(set to 1)]",
					"[style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldTerrible(set to 1)]",
					"[style.boldTerrible(All shielding set to 0)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] не может справиться с количеством атрибутных заклинаний, введенных в [npc.her] оружие, одежду и татуировки, и поэтому [npc.is] страдает от некоторых крайне серьезных негативных побочных эффектов.");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>=20;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_JINXED = new AbstractStatusEffect(80,
			"sealed clothing",
			"jinxed_clothing",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.namePos] заклинание на [npc.she] одежде имеет вампирский характер, и [npc.her] может почувствовать, как его аура поглощается...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer()) {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.jinxedClothingDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.jinxedClothingDiscovered);
					AbstractClothing clothing = null;
					for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
						if(c.isSealed()) {
							clothing = c;
							break;
						}
					}
					if(target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
						return "<p>"
									+ "Закончив надевать "+clothing.getName()+" на место, вы начинаете чувствовать странное тепло, исходящее от "+(clothing.getClothingType().isPlural()?"their":"its")+" surface."
									+ " Чувствуя легкое беспокойство по поводу способа аркана, который "+(clothing.getClothingType().isPlural()?"they":"it")+" должен содержать, вы немедленно пытаетесь снять "
										+(clothing.getClothingType().isPlural()?"them":"it")+" off."
								+ "</p>"
								+ "<p>"
									+ "Схватив за "+clothing.getName()+", на первый взгляд, ничего не кажется неправильным, но когда вы пытаетесь снять "+(clothing.getClothingType().isPlural()?"them":"it")+" с себя, вы обнаруживаете, что совершили большую ошибку."
									+ " Внезапно вспышка арканой энергии проходит через ваше тело, и когда навязчивая сила проникает в ваш разум, вы невольно отпускаете хватку."
								+ "</p>"
								+ "<p>"
									+ " Сжав зубы, вы снова пытаетесь снять этот предмет одежды, но в тот момент, когда вы пытаетесь его снять, вы немедленно отпускаете его."+(clothing.getClothingType().isPlural()?"them":"it")+" off."
									+ "Независимо от того, как сильно вы стараетесь, все, что вы можете сделать, это немного пошевелить "+clothing.getName()
										+" вокруг, и каждый раз, когда кажется, что он вот-вот будет удален, он сам возвращается на свое место."
								+ "</p>"
								+ "<p>"
									+ "В конце концов, сдавшись, вы решаете подойти к Лилайе и спросить, что происходит с "+(clothing.getClothingType().isPlural()?"these":"this")
										+" <b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>sealed</b> "+clothing.getName()+"."
									+ "Может, она знает способ снять заклятие?"
								+ "</p>"
								+(!((PlayerCharacter) target).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										?((PlayerCharacter) target).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										:"");
					
					} else {
						return "<p>"
									+ "Когда вы закончите с установкой "+clothing.getName()+" на место, вы почувствуете странное тепло, исходящее от "+(clothing.getClothingType().isPlural()?"their":"its")+" surface."
									+ "Чувствуя себя немного неловко из-за способа арканического заклинания, которое "+(clothing.getClothingType().isPlural()?"they":"it")+"должно содержать, вы немедленно пытаетесь взять "
										+(clothing.getClothingType().isPlural()?"them":"it")+" off."
								+ "</p>"
								+ "<p>"
									+ "Схватив за "+clothing.getName()+", на первый взгляд, ничего не кажется неправильным, но когда вы пытаетесь потянуть "+(clothing.getClothingType().isPlural()?"them":"it")+"Выключаешь свет, и тут понимаешь, что совершил большую ошибку."
									+ "В теле вспыхивает вспышка арканой энергии, и когда навязчивая сила проникает в твой разум, ты невольно отпускаешь хватку."
								+ "</p>"
								+ "<p>"
									+ "Скрежеща зубами, ты снова пытаешься снять предмет одежды, но в итоге отпускаешь его, когда пытаешься потянуть "+(clothing.getClothingType().isPlural()?"them":"it")+" off."
									+ "Независимо от того, как сильно ты стараешься, всё, что ты можешь сделать, это немного пошевелить "+clothing.getName()
										+"вниз, и каждый раз, когда кажется, что он вот-вот будет снят, он сам возвращается на место."
								+ "</p>"
								+ "<p>"
									+ "Предупреждение Лилайи о запечатанной одежде внезапно всплывает в твоей памяти, и ты стогнешь, понимая, что "
										+(clothing.getClothingType().isPlural()?"these ":"this ")+clothing.getName()
										+(clothing.getClothingType().isPlural()?" are":" is")
										+" <b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>sealed</b>."
									+ "Помня, что сказала Лилайя, ты должна быть в состоянии снять печать, если сосредоточишь часть своей поглощенной эссенции на ней..."
								+ "</p>";
					}
					
				} else {
					return "";
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.isSealed()) {
					return true;
				}
			}
			return false;
		}
	};
	
	// OTHER:

	public static AbstractStatusEffect SLEEPING = new AbstractStatusEffect(80,
			"sleeping",
			"sleeping",
			PresetColour.SLEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] спишь...");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.isPlayer()) {
				return target.hasStatusEffect(SLEEPING);
			}
			return (Main.game.isStarted()
						&& target.isSleepingAtHour()
						&& target.isAtHome()
						&& target.isAffectedBySleepingStatusEffect()
						&& (target.hasStatusEffect(SLEEPING) || !Main.game.getCharactersPresent().contains(target))
						&& !target.hasTrait(Perk.HEAVY_SLEEPER, true));
		}
	};

	public static AbstractStatusEffect SLEEPING_HEAVY = new AbstractStatusEffect(80,
			"heavily sleeping",
			"sleeping_heavy",
			PresetColour.SLEEP_HEAVY,
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -5f)),
			Util.newArrayListOfValues("Не проснется во время нежного секса")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] спишь очень глубоко, и даже крики и физическое потряхивание [npc.herHim] не будет достаточно, чтобы разбудить [npc.herHim]."
							+" [npc.SheIs] настолько нечувствителен, что любая нежная партнерша может заниматься сексом с [npc.herHim], не пробуждая [npc.herHim]...");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.isPlayer()) {
				return target.hasStatusEffect(SLEEPING_HEAVY);
			}
			return (Main.game.isStarted()
						&& target.isSleepingAtHour()
						&& target.isAtHome()
						&& target.isAffectedBySleepingStatusEffect()
						&& (target.hasStatusEffect(SLEEPING_HEAVY) || !Main.game.getCharactersPresent().contains(target))
						&& target.hasTrait(Perk.HEAVY_SLEEPER, true));
		}
		@Override
		public boolean isSexEffect() {
			return false;
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED = new AbstractStatusEffect(80,
			"well rested",
			"wellRested",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
					new Value<>(Attribute.MANA_MAXIMUM, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "После хорошего отдыха, [npc.name] [npc.verb(feel)] полный энергии.");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED = new AbstractStatusEffect(80,
			"хорошо отдохнувший (усиленный)",
			"wellRestedBoosted",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 30f),
					new Value<>(Attribute.MANA_MAXIMUM, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasTrait(Perk.JOB_UNEMPLOYED, true)) {
					return UtilText.parse(target, "Благодаря использованию способности [npc.her] знать, как получить максимум от хорошего отдыха, [npc.name] в настоящее время [npc.verb(feel)] полный энергии и бодрости.");
				} else {
					return UtilText.parse(target, "Благодаря улучшенной императорской кровати в комнате [npc.her], [npc.name] [npc.has] удалось получить очень комфортный отдых, и теперь [npc.verb(feel)] полный энергии и бодрости.");
				}
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED_EXTRA = new AbstractStatusEffect(80,
			"хорошо отдохнувший (усиленный)",
			"wellRestedBoostedExtra",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 60f),
					new Value<>(Attribute.MANA_MAXIMUM, 60f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Благодаря улучшенной императорской кровати в комнате [npc.her], в сочетании с способностью [npc.her] знать, как лучше всего получить хороший отдых, [npc.name] теперь [npc.verb(feel)] как будто [npc.sheIs] переполнен энергией и бодростью.");
				
			} else {
				return "";
			}
		}
	};

//	public static AbstractStatusEffect SHOWER = new AbstractStatusEffect(80,
//			"recently showered",
//			"bath_minor",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
//					new Value<>(Attribute.MANA_MAXIMUM, 5f),
//					new Value<>(Attribute.DAMAGE_LUST, 5f)),
//			Util.newArrayListOfValues("[style.boldMinorGood(Doubles)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to have a shower, [npc.name] [npc.verb(feel)] refreshed.");
//			} else {
//				return "";
//			}
//		}
//	};
	
//	public static AbstractStatusEffect BATH = new AbstractStatusEffect(80,
//			"recently bathed",
//			"bath",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
//					new Value<>(Attribute.MANA_MAXIMUM, 10f),
//					new Value<>(Attribute.DAMAGE_LUST, 10f)),
//			Util.newArrayListOfValues("[style.boldGood(Triples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to relax in [npc.her] bath, [npc.name] [npc.verb(feel)] refreshed and rejuvenated.");
//			} else {
//				return "";
//			}
//		}
//	};

//	public static AbstractStatusEffect BATH_BOOSTED = new AbstractStatusEffect(80,
//			"recently bathed (spa)",
//			"bath_boosted",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			PresetColour.GENERIC_EXCELLENT,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
//					new Value<>(Attribute.MANA_MAXIMUM, 25f),
//					new Value<>(Attribute.DAMAGE_LUST, 15f)),
//			Util.newArrayListOfValues("[style.boldExcellent(Quadruples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to relax in [npc.her] spa, [npc.name] [npc.verb(feel)] like [npc.sheHas] been born anew!");
//			} else {
//				return "";
//			}
//		}
//	};
	
	public static AbstractStatusEffect OVERWORKED_1 = new AbstractStatusEffect(80,
			"slightly overworked",
			"overworked1",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<>(Attribute.MANA_MAXIMUM, -10f)),
			Util.newArrayListOfValues(
					"[style.colourMinorBad(Менее вероятно)] использовать комнаты для отдыха",
					"While working:",
					"[style.boldBad(-50%)] [style.colourAffection(Привязанность)]",
					"[style.boldBad(-0.5)] [style.colourAffection(Привязанность/час)]",
					"[style.boldBad(-25%)] [style.colourExperience(experience)] gain chance")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"В результате того, что у [npc.name] слишком много работы каждый день, [npc.name] иногда [npc.verb(find)] [npc.herself] чувствуя себя немного уставшим.<br/>"
						+ " <i>(Получено от ежедневной усталости от -1 до -9. Текущая ежедневная усталость - [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<0
					&& target.getDailySlaveJobStamina()>=-9;
		}
	};
	
	public static AbstractStatusEffect OVERWORKED_2 = new AbstractStatusEffect(80,
			"overworked",
			"overworked2",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<>(Attribute.MANA_MAXIMUM, -25f)),
			Util.newArrayListOfValues(
					"[style.colourBad(Unlikely)] to use slave lounges",
					"While working:",
					"[style.boldBad(-80%)] [style.colourAffection(Affection gains)]",
					"[style.boldBad(-1)] [style.colourAffection(Affection/hour)]",
					"[style.boldBad(-50%)] [style.colourExperience(experience)] gain chance")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"В результате того, что у [npc.name] слишком много работы каждый день, [npc.name] начинает чувствовать себя очень уставшим.<br/>"
						+ " <i>(Получено от ежедневной усталости от -10 до -19. Текущая ежедневная усталость - [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<-9
					&& target.getDailySlaveJobStamina()>=-19;
		}
	};
	
	public static AbstractStatusEffect OVERWORKED_3 = new AbstractStatusEffect(80,
			"severely overworked",
			"overworked3",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<>(Attribute.MANA_MAXIMUM, -50f)),
			Util.newArrayListOfValues(
					"Will [style.colourTerrible(never)] use slave lounges",
					"While working:",
					"[style.boldBad(-100%)] [style.colourAffection(Привязанность)]",
					"[style.boldBad(-2)] [style.colourAffection(Привязанность/час)]",
					"[style.boldBad(-75%)] [style.colourExperience(опыт)] шанс")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"В результате того, что у <br/> слишком много работы каждый день, [npc.name] чувствует себя невероятно уставшим.<br/>"
						+ " <i>(Получено из-за того, что у вас осталось -20 или меньше ежедневной выносливости. Текущая ежедневная выносливость [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<-19;
		}
	};
	
	public static AbstractStatusEffect GYM_FATIGUE = new AbstractStatusEffect(80,
			"посттренировочная усталость",
			"gym_fatigue",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "После недавней тренировки, [npc.nameIsFull] чувствует себя очень уставшим, и не сможет заниматься в тренажерном зале до тех пор, пока [npc.sheHas] не восстановится.");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect FATIGUED = new AbstractStatusEffect(80,
			"fatigued",
			"fatigued",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "После периода тяжелой работы, [npc.nameIsFull] чувствует себя очень уставшим, и ему нужно немного времени, чтобы восстановиться...");
			} else {
				return "";
			}
		}
	};
	
	// Utility status effect to display text of companions leaving:
	public static AbstractStatusEffect COMPANIONS_LEAVING = new AbstractStatusEffect(80,
			"Companions Leaving",
			"",
			PresetColour.BASE_MAGENTA,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "";
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer();
		}
	};
	
	public static AbstractStatusEffect PSYCHOACTIVE = new AbstractStatusEffect(80,
			"Psychoactive Trip",
			"psychoactive",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("Открыт для <b style='color: " + PresetColour.PSYCHOACTIVE.toWebHexString() + ";'>Hypnotic Suggestion</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Math.random()<=Util.getModifiedDropoffValue((secondsPassed/60)*0.0075f, 0.5f)) {
				
				if(target.getPsychoactiveFluidsIngested().isEmpty()) {
					return "<p>"
								+ "Вы теряете ощущение того, где вы находитесь, когда пытаетесь преодолеть психоактивные эффекты..."
							+ "</p>"
							+ "<p><i>"
							+ UtilText.returnStringAtRandom(
									"Вдруг, ты оказываешься обратно в музее твоей тети Лили."
										+ " Один из сотрудников музея держит тебя за [pc.hand] и тянет тебя в пустую комнату."
										+ " Оказавшись внутри, он опускается на колени, улыбаясь тебе, когда он наклоняется вперед и целует тебя в обнаженные гениталии...",
									"Вдруг, ты оказываешься лицом к лицу с твоей тетей Лили; вы оба стоите в ее старой квартире."
										+ " С хихиканьем, она внезапно наклоняется и хватает тебя за [pc.hand], прежде чем тянуть тебя в свою спальню."
										+ " Оказавшись внутри, она снимает свою пижаму и делает шаг вперед, прижимая свои губы к твоим...",
									"Вдруг, ты оказываешься перед потрясающе красивой демон."
										+ " Она делает шаг вперед, прижимая свои обнаженные груди к твоему телу, в то время как ее хвост поднимается, чтобы потереться о твои гениталии."
										+ " Выдавая хриплый стон, она прижимает свои губы к твоим...")
							+"</i></p>"
							+ "<p>"
								+ "С хрипом, ты внезапно вырываешься из галлюцинации."
								+ "Краснея, ты чувствуешь, как жажда распространяется по твоему влагалищу..."
							+ "</p>"
							+target.incrementLust(25, false);
				} else {
					List<AbstractFluidType> list = new ArrayList<>(target.getPsychoactiveFluidsIngested());
					AbstractFluidType fluid = list.get(Util.random.nextInt(list.size()));
					String npcName = UtilText.generateSingularDeterminer(fluid.getRace().getName(false))+" "+fluid.getRace().getName(false);
					switch(fluid.getBaseType()) {
						case CUM:
							return "<p>"
										+ "Ты теряешь ориентацию в пространстве, пытаясь преодолеть психоактивные эффекты..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"Внезапно, ты оказываешься в музее твоей тети Лили."
												+ " Ты на коленях перед одним из сотрудников музея, "+npcName+", в то время как ты жадно лижешь его член."
												+ "Уже через несколько мгновений его член начинает подергиваться, и ты издает восхищенный стон, когда густая струя спермы выливается в твою рот...",
											"Внезапно, ты оказываешься рядом с твоей тетей Лили, и вы обе на коленях друг возле друга в спальне ее старой квартиры."
												+ " Она улыбается в восторге, наблюдая, как ты лижешь член "+fluid.getRace().getName(false)+" before you."
												+ " Когда толстый член в твоем рту начинает подергиваться, Лили протягивает руку и придерживает твою голову, чтобы горячая струя спермы попала тебе в горло...",
											"Внезапно, ты оказываешься на коленях перед полностью обнаженным "+fluid.getRace().getName(false)+"."
												+ " Они шагнули вперед, наклонившись, чтобы потянуть твою голову вперед, в то время как они впихивают свой жесткий член в твою рот."
												+ " С отчаянным стоном, они тут же начинают спермотечь, наполняя твой рот горячей спермой...")
									+"</i></p>"
									+ "<p>"
										+ " С изумлением, ты внезапно вырываешься из галлюцинации."
										+ " Краснее, ты чувствуешь жаждущее тепло, распространяющееся по всему твоему паху..."
									+ "</p>"
									+target.incrementLust(25, false);
						case GIRLCUM:
							return "<p>"
										+ " Ты теряешь счет времени, пытаясь преодолеть психоактивные эффекты..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											" Внезапно, ты оказываешься обратно в музее твоей тети Лили."
												+ " Ты стоишь на коленях перед одним из сотрудников музея, "+npcName+", в то время как ты жадно лижешь и целуешь их мокрую киску."
												+ " Уже через несколько мгновений, их киска начинает подергиваться и сжиматься, и ты издает восхищенный стон, когда ты лижешь каждую каплю их вкусной девчачьей спермы...",
											" Внезапно, ты оказываешься рядом с твоей тетей Лили, и вы обе стоите на коленях друг возле друга в спальне ее старой квартиры."
												+ " Она улыбается в восторге, наблюдая, как ты лижешь и целуешь киску "+fluid.getRace().getName(false)+" before you."
												+ " Когда их киска начинает подергиваться и сжиматься, Лили тянется вперед и толкает твою голову вперед, убедившись, что ты лижешь каждую каплю их вкусного girlcuma...",
											" Вдруг, ты находишься на коленях перед полностью обнаженной "+fluid.getRace().getName(false)+"."
												+ " Они шагают вперед, наклоняясь, чтобы потянуть твою голову вперед, когда они прижимают свою мокрую киску к твоему [pc.lips+]."
												+ " С отчаянным стоном, они тут же начинают cumming, грубо заставляя тебя лизать каждую каплю их вкусного girlcuma...")
									+"</i></p>"
									+ "<p>"
										+ " С вздохом, ты внезапно вырываешься из галлюцинации."
										+ " Краснея, ты чувствуешь жажду, которая распространяется по твоему влагалищу..."
									+ "</p>"
									+target.incrementLust(25, false);
						case MILK:
							return "<p>"
										+ " Ты теряешь ориентацию, пытаясь преодолеть психоактивные эффекты..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											" Вдруг, ты снова находишься в музее твоей тети Лили."
												+ " Ты сидишь на коленях у одного из сотрудников музея, "+npcName+", как ты жадно целуешь и сосёшь их набухшие соски."
												+ " Через несколько мгновений из твоего рта начинает течь постоянный поток молока, и ты издает восхищенный стон, глотая этот вкусный напиток...",
											" Вдруг ты оказываешься на коленях у твоей тети Лили; вы обе сидите на её кровати в её старой квартире."
												+ " Она с улыбкой смотрит на тебя, и ты целуешь и сосёшь её набухшие соски."
												+ " Через несколько мгновений из твоего рта начинает течь постоянный поток молока, и ты издает восхищенный стон, глотая этот вкусный напиток...",
											" Вдруг ты оказываешься на коленях у совершенно голой "+fluid.getRace().getName(false)+"."
												+ " Они тянутся вниз, чтобы прижать твою голову к своим огромным грудям, и прижимают один из своих набухших сосков к твоему [pc.lips+]."
												+ " С отчаянием ты начинаешь целоваться и сосать их пухлые соски, и через несколько мгновений из твоего рта начинает течь постоянный поток молока...")
									+"</i></p>"
									+ "<p>"
										+ " С изумлением ты внезапно выходишь из галлюцинации."
										+ " Краснеешь, ты чувствуешь, как в твоих половых органах распространяется жажда..."
									+ "</p>"
									+target.incrementLust(25, false);
					}
				}
				return "";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Ты чувствуешь себя очень странно в данный момент... Странные видения продолжают мелькать перед твоим [pc.eyes], и ты не совсем уверен в своем окружении...";
			} else {
				return UtilText.parse(target, "[npc.name] is feeling very peculiar at the moment... Strange visions keep flashing before [npc.her] [npc.eyes], and [npc.she] doesn't seem to be quite sure of [npc.her] surroundings...");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.removePsychoactiveEffects();
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLEANED_MASSAGED = new AbstractStatusEffect(80,
			"Recently massaged",
			"cleaned_massage",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Недавно получив массаж, [npc.nameIsFull] чувствует себя очень расслабленно и гибко!");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SHOWER = new AbstractStatusEffect(80,
			"Recently showered",
			"cleaned_shower",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.boldMinorGood(Doubles)] [style.colourHealth(health)] и [style.colourMana(aura)] скорость восстановления")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Недавно приняв душ, [npc.name] [npc.verb(feel)] себя освеженным!");
		}
	};
	
	public static AbstractStatusEffect CLEANED_BATH = new AbstractStatusEffect(80,
			"Recently bathed",
			"cleaned_bath",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
					new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.boldGood(Triples)] [style.colourHealth(health)] и [style.colourMana(aura)] скорость восстановления")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.clearMuskMarkers();
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Недавно [npc.her] уделил время, чтобы расслабиться в [npc.name], и [npc.name] [npc.verb(feel)] себя обновленным и омоложенным.");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SPA = new AbstractStatusEffect(80,
			"Spa soak",
			"cleaned_spa",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.MANA_MAXIMUM, 25f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Quadruples)] [style.colourHealth(health)] и [style.colourMana(aura)] скорость восстановления")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.clearMuskMarkers();
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Недавно я уделил время, чтобы расслабиться в спа, и [npc.name] [npc.verb(feel)] как будто [npc.sheHas] родился заново!");
		}
	};
	
	public static AbstractStatusEffect LOLLIPOP_SUCKING = new AbstractStatusEffect(80,
			"sucking lollipop",
			"lollipop",
			PresetColour.CLOTHING_PINK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] игриво сосет леденец, и каждый раз, когда кто-то смотрит на [npc.herHim], [npc.she] [npc.verb(purse)] [npc.her] [npc.lips] и [npc.verb(make)] шоу из поцелуев.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SMOKING = new AbstractStatusEffect(80,
			"smoking",
			"smoking",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] в настоящее время курит сигарету. Каждый раз, когда [npc.she] [npc.verb(exhale)], перед лицом [npc.her] образуется легкое белое облако, заставляя [npc.her] непосредственное окружение сильно пахнуть горящей растительной массой.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_SMOKED);
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(RECENTLY_SMOKED, 4 * 60 * 60);
			return "";
		}
	};
	
	public static AbstractStatusEffect RECENTLY_SMOKED = new AbstractStatusEffect(80,
			"recently smoked",
			"recentlySmoked",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] недавно выкурил сигарету, что очевидно для любого, кто приближается к [npc.herHim], так как [npc.she] [npc.verb(smell)] сильно пахнет горящей растительной массой.");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN = new AbstractStatusEffect(80,
			"recently eaten",
			"recentlyEaten",
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREEN_LIGHT,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_EATEN_POOR);
			target.removeStatusEffect(RECENTLY_EATEN_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] недавно поел. С [npc.her] голодом временно удовлетворенным, [npc.sheIs] чувствует себя довольным и полным энергии.");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN_POOR = new AbstractStatusEffect(80,
			"недавно поел (мусор)",
			"recentlyEaten",
			PresetColour.BASE_GREEN_LIGHT,
			PresetColour.BASE_YELLOW_PALE,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 1f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_EATEN);
			target.removeStatusEffect(RECENTLY_EATEN_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] недавно поел что-то, что в лучшем случае можно классифицировать как мусорную еду. С [npc.her] голодом временно удовлетворенным, [npc.sheIs] чувствует себя немного более энергичным.");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN_QUALITY = new AbstractStatusEffect(80,
			"недавно поел (качество)",
			"recentlyEaten",
			PresetColour.BASE_GREEN_DARK,
			PresetColour.BASE_GREEN,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 10f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(RECENTLY_EATEN_POOR);
			target.removeStatusEffect(RECENTLY_EATEN);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] недавно поел что-то, что было исключительного качества."
					+ "С [npc.her] голодом временно удовлетворенным, и с воспоминанием о таком прекрасном обеде, все еще свежем в памяти [npc.her], [npc.sheIs] чувствует себя довольным и полным энергии.");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED = new AbstractStatusEffect(80,
			"thirst quenched",
			"recentlyDrank",
			PresetColour.BASE_BLUE,
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(THIRST_QUENCHED_POOR);
			target.removeStatusEffect(THIRST_QUENCHED_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] недавно попил. С [npc.her] жаждой временно утоленной, [npc.sheIs] способен сосредоточиться и сконцентрироваться на поставленной задаче.");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED_POOR = new AbstractStatusEffect(80,
			"жажда утолена (базовая)",
			"recentlyDrank",
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.BASE_YELLOW_PALE,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 1f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(THIRST_QUENCHED);
			target.removeStatusEffect(THIRST_QUENCHED_QUALITY);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] недавно утолил [npc.her] жажду чем-то, что едва могло считаться питьевым. С [npc.her] жаждой временно утоленной, [npc.sheIs] смог сосредоточиться немного больше на стоящей перед ним задаче.");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED_QUALITY = new AbstractStatusEffect(80,
			"утоление жажды (качество)",
			"recentlyDrank",
			PresetColour.BASE_BLUE_DARK,
			PresetColour.BASE_BLUE,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f)),
			null) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(THIRST_QUENCHED_POOR);
			target.removeStatusEffect(THIRST_QUENCHED);
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] недавно выпил что-то, что было исключительного качества."
					+ " С [npc.her] жаждой временно утоленной, и с воспоминанием о таком прекрасном напитке, все еще свежем в [npc.her] памяти, [npc.sheIs] смог сосредоточиться и сконцентрироваться на стоящей перед ним задаче.");
		}
	};
	
	public static AbstractStatusEffect DRUNK_1 = new AbstractStatusEffect(80,
			"Intoxicated I - Tipsy",
			"drunk1",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 2f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<>(Attribute.MAJOR_ARCANE, -2f),
					new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -1f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementAlcoholLevel(-((secondsPassed/60)*(1f/(60f*6)))); // alcohol level will completely go after 6 hours
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После того, как недавно выпил алкогольный напиток, [npc.nameIsFull] чувствует себя немного опьяневшим..."
					+ "<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.ONE_TIPSY;
		}
	};
	
	public static AbstractStatusEffect DRUNK_2 = new AbstractStatusEffect(80,
			"Опьянение II - Веселый",
			"drunk2",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После того, как недавно выпил алкогольный напиток, [npc.nameIsFull] чувствует себя довольно веселым..."
					+ "<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.TWO_MERRY;
		}
	};
	
	public static AbstractStatusEffect DRUNK_3 = new AbstractStatusEffect(80,
			"Опьянение III - Пьяный",
			"drunk3",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 10f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -10f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После недавнего употребления алкоголя, [npc.nameIsFull] чувствую себя довольно пьяным..."
					+ "<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Оportunistic Attackers</b><br/>Ты явно пьяный, и некоторые могут подумать, что ты легкая добыча.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.THREE_DRUNK;
		}
	};
	
	public static AbstractStatusEffect DRUNK_4 = new AbstractStatusEffect(80,
			"Опьянение IV - Забитый",
			"drunk4",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 15f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, -10f),
					new Value<>(Attribute.DAMAGE_LUST, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После недавнего употребления алкоголя, [npc.nameIsFull] чувствую себя абсолютно пьяным..."
					+ "<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Оportunistic Attackers</b><br/>Ты явно пьяный, и некоторые могут подумать, что ты легкая добыча.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FOUR_HAMMERED;
		}
	};
	
	public static AbstractStatusEffect DRUNK_5 = new AbstractStatusEffect(80,
			"Intoxicated V - Wasted",
			"drunk5",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 20f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.MAJOR_ARCANE, -15f),
					new Value<>(Attribute.DAMAGE_LUST, -10f),
					new Value<>(Attribute.RESISTANCE_LUST, -20f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После недавнего употребления алкоголя, [npc.nameIsFull] чувствую себя совершенно пьяным..."
					+ "<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Оportunistic Attackers</b><br/>Ты явно пьяный, и некоторые могут подумать, что ты легкая добыча.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FIVE_WASTED;
		}
	};
	
	public static AbstractStatusEffect ADDICTIONS = new AbstractStatusEffect(80,
			"addictions",
			"addictions",
			PresetColour.BASE_CRIMSON,
			false,
			null,
			null) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			extraEffects.clear();
			
			for(Addiction addiction : target.getAddictions()) {
				long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
				long now = Main.game.getMinutesPassed();
				long timeLeft = oneDayLater - now;
				long hoursLeft = timeLeft / 60;
				long minutesLeft = timeLeft % 60;
				AbstractRace fluidRace = addiction.getFluid().getRace();
				extraEffects.add("<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
						+ (timeLeft > 0
								?" [style.colourGood("+hoursLeft+":"+String.format("%02d", minutesLeft)+")]"
								:" [style.boldАркана(Withdrawal!)]"));
			}
			
			return extraEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return " Вы в настоящее время зависимы от "+Util.intToString(target.getAddictions().size())+(target.getAddictions().size()==1?" типа жидкости":" типов жидкостей")+"!"
							+ " После более чем 24 часов без употребления, вы начнете испытывать симптомы отмены."
							+ " <i>Зависимости могут быть сняты с помощью '"+ItemType.ADDICTION_REMOVAL.getName(false)+"'.</i>";
				} else {
					return UtilText.parse(target,
							"[npc.NameIsFull] в настоящее время зависимы от "+Util.intToString(target.getAddictions().size())+(target.getAddictions().size()==1?" типа жидкости":" типов жидкостей")+"!"
									+ " После более чем 24 часов без употребления, [npc.she] начнете испытывать симптомы отмены."
									+ " <i>Зависимости могут быть сняты с помощью '"+ItemType.ADDICTION_REMOVAL.getName(false)+"'.</i>");
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getAddictions().isEmpty();
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_1 = new AbstractStatusEffect(80,
			"Mild Withdrawal",
			"withdrawal1",
			PresetColour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -2f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -2f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -2f),
					new Value<>(Attribute.MANA_MAXIMUM, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("Вы испытываете легкую ломку от:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытываете легкую ломку от:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
					long twoDaysLater = oneDayLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if(oneDayLater <= now && now < twoDaysLater) {
						long timeLeft = twoDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(усугубляется)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
				long twoDaysLater = oneDayLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (oneDayLater <= now && now < twoDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_2 = new AbstractStatusEffect(80,
			"Noticeable Withdrawal",
			"withdrawal2",
			PresetColour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f),
					new Value<>(Attribute.MANA_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("Вы испытываете заметную ломку от:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытываете заметную ломку от:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long twoDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 2);
					long threeDaysLater = twoDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (twoDaysLater <= now && now < threeDaysLater) {
						long timeLeft = threeDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(усугубляется)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long twoDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 2);
				long threeDaysLater = twoDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (twoDaysLater <= now && now < threeDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_3 = new AbstractStatusEffect(80,
			"Strong Withdrawal",
			"withdrawal3",
			PresetColour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -10f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<>(Attribute.MANA_MAXIMUM, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("Вы испытываете сильную ломку от:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытывает сильный синдром отмены:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long threeDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 3);
					long fourDaysLater = threeDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (threeDaysLater <= now && now < fourDaysLater) {
						long timeLeft = fourDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(усугубляется)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long threeDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 3);
				long fourDaysLater = threeDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (threeDaysLater <= now && now < fourDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_4 = new AbstractStatusEffect(80,
			"Severe Withdrawal",
			"withdrawal4",
			PresetColour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -25f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -25f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<>(Attribute.MANA_MAXIMUM, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("Вы испытываете сильный синдром отмены:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытывает сильный синдром отмены:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long fourDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 4);
					long fiveDaysLater = fourDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (fourDaysLater <= now && now < fiveDaysLater) {
						long timeLeft = fiveDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(усугубляется)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long fourDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 4);
				long fiveDaysLater = fourDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (fourDaysLater <= now && now < fiveDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_5 = new AbstractStatusEffect(80,
			"Intense Withdrawal",
			"withdrawal5",
			PresetColour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -50f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -50f),
					new Value<>(Attribute.MANA_MAXIMUM, -50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("Вы испытываете сильный синдром отмены:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытывает сильный синдром отмены:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long fiveDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 5);
					long now = Main.game.getMinutesPassed();
					
					if (fiveDaysLater <= now) {
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>.");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long fiveDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 5);
				long now = Main.game.getMinutesPassed();
				
				if (fiveDaysLater <= now) {
					return true;
				}
			}
			return false;
		}
	};

	public static AbstractStatusEffect MENOPAUSE = new AbstractStatusEffect(80,
			"Menopause",
			"menopause",
			PresetColour.BASE_CRIMSON,
			false,
			null,
			Util.newArrayListOfValues("[style.colourBad(Total infertility)]")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Из-за того, что [npc.nameHasFull] старше пятидесяти двух лет, [npc.nameHasFull] прошла менопаузу, и, поскольку [npc.sheIsFull] не демон, [npc.sheHasFull] была полностью лишена возможности иметь детей.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (Main.getProperties().hasValue(PropertyValue.ageContent) || target.isUnique())
					&& target.hasVagina()
					&& (target.isPlayer()
							?target.getAgeValue()>=52+Game.TIME_SKIP_YEARS
							:target.getAgeValue()>=52)
					&& (target.getSubspecies()!=Subspecies.ANGEL && target.getSubspeciesOverride()==null) // Angels and demons are immune
					&& !target.isElemental()
					&& !target.isDoll()
					&& !target.hasStatusEffect(StatusEffect.VIXENS_VIRILITY)
					&& !target.hasStatusEffect(StatusEffect.BROODMOTHER_PILL);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_0 = new AbstractStatusEffect(80,
			"риск беременности",
			"pregnancy0",
			PresetColour.GENERIC_ARCANE,
			true,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(Main.game.isInNewWorld()) {
				return UtilText.parse(target,
						"После недавнего незащищенного секса, есть риск, что [npc.name] забеременеет!"
							+ " Из-за того, что аркана ускоряет беременность людей, [npc.she] узнает, беременна ли [npc.sheIs] в течение нескольких часов...");
			} else {
				return UtilText.parse(target,
						"После недавнего незащищенного секса, есть риск, что [npc.name] забеременеет!");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			if (target.isPregnant()) {
				int maxHourLength = (int)((Main.getProperties().pregnancyDuration * 7 * 24) / 2f);
				target.addStatusEffect(PREGNANT_1, 60 * 60 * ((maxHourLength-12) + Util.random.nextInt(13)));
				target.loadImages(true); // Reload images for pregnant versions
				
				if (target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
					sb.append("<p>");
						if(Main.game.getPlayer().isTaur()) {
							sb.append("За последние несколько часов, вы чувствовали легкий дискомфорт в теле [pc.legRace], который сопровождался постепенным увеличением живота в нижней части."
								+ " Вы сначала попытались списать это на несварение желудка, но, глядя назад и оглядывая свое тело, вы больше не можете отрицать то, что инстинктивно знаете, что это правда."
								+ " Вы беременны.");
						} else {
							sb.append("За последние несколько часов ваш живот постепенно набухает."
									+ "Процесс был настолько медленным, что вы сначала не заметили, что происходит что-то необычное, но когда вы оглядываетесь на свой живот, то не можете не заметить, что он набух."
									+ "Вы беременны."
									+ "Вы начинаете гладить свой живот, делая мягкие вздохи, когда осознание того, что происходит, начинает проникать в вас.");
						}
					sb.append("</p>");
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>"
								+ "[pc.thought(Я-Я беременна?"
									+ "<br/>"
									+ "..."
									+ "<br/>"
									+ "О боже! Да! <b>Я беременна!</b>)]"
							+ "</p>"
							+ "<p>"
								+ "Огромная радость, которую вы испытываете, не только от того, что вы беременны, но и от того, что физические признаки уже видны через несколько часов, накрывает вас, как волна чистой экстази."
								+ (Main.game.getPlayer().isTaur()
									?"Вы чувствуете, как слезы радости наворачиваются на глаза, когда вы оглядываетесь на свой живот, который, без сомнения, набух от беременности [pc.legRace]."
									:" Вы чувствуете, как слезы радости наполняют ваши глаза, когда вы нежно обнимаете свой вздут живот.")
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(Это самое лучшее чувство в мире!"
										+ "<br/>"
										+ (Main.game.isPlotDiscovered()
											?"Что мне делать теперь?!"
											:"Если бы тетя Лилия была здесь, я уверена, она бы гордилась!")
										+ "<br/>"
										+ "Подожди! Конечно! <b>Лилая!</b> Она тоже захочет увидеть это!)]"
							+ "</p>");
						
						sb.append("<p>");
							if(target.getBodyMaterial()==BodyMaterial.SLIME) {
								sb.append("Присмотревшись к своему вздутому, слизистому животу, вы вдруг понимаете, что можете видеть "
											+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленький слизистый ядро"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in "
											+ (target.hasVagina()
												?"место, где должен быть ваш живот."
												:"your belly.")
										+ " Вы не можете сдержать радостный визг, когда видите свое "
											+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")+"растущая внутри тебя,"
													+ "и провести следующие несколько минут"
													+ (Main.game.getPlayer().isTaur()
														?"дотянувшись назад, чтобы погладить и потереть"
														:"поглаживая и потерев")
													+"твою замечательно вздутую живот в состоянии абсолютного блаженства."
										+ "В конце концов, однако, ты решаешь, что, вероятно, стоит сходить к Лилае, чтобы она могла помочь тебе разобраться во всех деталях родов.");
							} else {
								sb.append("После немного по"
										+(Main.game.getPlayer().isTaur()
											?"дотянувшись назад, чтобы погладить и потереть"
											:"поглаживая и потерев")
										+"твою замечательно вздутую живот, ты начинаешь немного успокоиться."
										+ " Вы решаете, что вам, вероятно, следует пойти и повидаться с Лилайей, чтобы она могла помочь вам разобраться во всех деталях родов.");
							}
						sb.append("</p>");
						
					} else {
						sb.append("<p>"
									+ "[pc.thought(Я-Я беременна?"
										+ "<br/>"
										+ "..."
										+ "<br/>"
										+ "О боже! <b>Я беременна!</b>)]"
								+ "</p>"
								+ "<p>"
									+ "Внезапный шок от того, что вы не только беременны, но и физически проявляете признаки беременности уже через несколько часов, поражает вас как молот."
									+ " Несмотря на все ваши усилия, вы чувствуете, как начинаете задыхаться, ходя по кругу"
									+ (Main.game.getPlayer().isTaur()
											?" с головой в ваших [pc.hands]."
											:", чередуя между поглаживанием головы и живота.")
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(Что мне делать? Что мне делать? Что мне делать?"
											+ "<br/>"
											+ (Main.game.isPlotDiscovered()
												?"Что мне делать теперь?!"
												:"Если бы только тетя Лилия была здесь, я уверен, она бы гордилась!")
											+ "<br/>"
											+ "Подожди! Конечно! <b>Лилая!</b> Она знает, что делать!)]"
								+ "</p>");
						
						sb.append("<p>");
							if(target.getBodyMaterial()==BodyMaterial.SLIME) {
								sb.append("Когда ты в последний раз смотришь на свой раздутый, слизистый живот, ты вдруг понимаешь, что можешь видеть "
												+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленький слизистый комок"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in "
												+ (target.hasVagina()?"место, где должен быть твой живот.":"your belly.")
											+ "Ты не можешь сдержать крик удивления, когда видишь, как твой "+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")
												+"растет внутри тебя, и проводишь следующие несколько минут "
													+ (Main.game.getPlayer().isTaur()
														?"простясь назад, чтобы погладить и потереть"
														:" поглаживание и потирание")
												+" вашего вздувшегося живота в состоянии оцепенения от паники."
											+ " В конце концов, однако, вы начинаете немного успокаиваться и решаете, что вам следует как можно скорее отправиться к Лилае.");
							} else {
								sb.append(" Вы начинаете немного успокаиваться, поскольку первоначальный шок начинает проходить."
											+ " Если кто-то и знает, что делать, то это Лилая.");
							}
						sb.append("</p>");
					}
					
				} else {
					sb.append("<p>");
						if(Main.game.getPlayer().isTaur()) {
							sb.append(" За последние несколько часов вы чувствовали знакомое ощущение легкого дискомфорта в теле вашего [pc.legRace], которое сопровождалось постепенным вздутием живота в нижней части."
								+ " Сначала вы не были полностью уверены, что вызывает этот дискомфорт, но, оглянувшись и взглянув вниз на свое тело, вы инстинктивно знаете, что это за причина."
								+ " Вы снова беременны.");
						} else {
							sb.append("За последние несколько часов ваш живот постепенно надулся."
									+ "Процесс был настолько медленным, что вы сначала не заметили, что происходит, но когда вы посмотрели на свой живот, то не могли не заметить."
									+ "Вы снова беременны."
									+ "Вы начинаете поглаживать живот, делая мягкие маленькие вздохи, когда осознание того, что происходит, начинает проникать в вас.");
						}
					sb.append("</p>");
					
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sb.append("<p>");
							sb.append("Взглянув на свой вздутый, слизистый живот, вы видите "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+"маленький слизистый ядро"
											+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in "+ (target.hasVagina()?"место, где должен быть ваш живот.":"your belly."));
							if(!target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
								sb.append("Вы не можете не выпустить счастливый маленький вздох, когда видите свой "+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")
											+"растущая внутри тебя, и провести следующие несколько минут "
												+(Main.game.getPlayer().isTaur()
													?"дотянувшись назад, чтобы погладить и потереть"
													:"гладя и потирая")
											+"твою вздутую живот в состоянии материнской блаженства.");
							}
						sb.append("</p>");
					}
					
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>"
								+ "[pc.thought(Ха! Да! Я снова забеременела! Это лучшее чувство в мире...)]"
							+ "</p>"
							+ "<p>"
								+ "Зная, что тебя ждет, ты выпустишь радостный смешок, прежде чем продолжить свой путь."
							+ "</p>");
					} else if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
						sb.append("<p>"
								+ "[pc.thought(Нет! Я не могу поверить, что снова забеременела... Я <b>ненавидею</b> это...)]"
							+ "</p>"
							+ "<p>"
								+ "Зная, что тебя ждёт, ты выпустила раздражённый вздох и проклинаешь тот факт, что ты беременна, прежде чем продолжить свой путь."
							+ "</p>");
					} else {
						sb.append("<p>"
								+ "[pc.thought(Ммм... Видимо, я снова забеременела...)]"
							+ "</p>"
							+ "<p>"
								+ "Зная, что тебя ждёт, ты выпустила удовлетворённый вздох и начала продолжать свой путь."
							+ "</p>");
					}
					
				}
				
				sb.append( "<p style='text-align:center;'>"
						+ "<b style='color:"+ PresetColour.GENERIC_SEX.toWebHexString() + ";'>Ты беременна!</b>"
					+ "</p>");
				
				// Remove cum inflation:
				StringBuilder sbFluidDrain = new StringBuilder();
				float fluidAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
				boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.VAGINA);
				if(retention) {
					fluidAmount = (int)(fluidAmount-Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT);
					if(fluidAmount>0) {
						target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, fluidAmount);
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Набухание твоего беременного животика заставляет твое тело превратить [style.fluid("+fluidAmount+")] спермы в твоей пизде в большее количество слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Набухание твоего беременного животика заставляет твое тело выпустить [style.fluid("+fluidAmount+")] спермы в твоей пизде.)]");
						}
					}
				} else if(fluidAmount>0) {
					target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, -fluidAmount);
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sbFluidDrain.append("[style.italicsSex(Набухание твоего беременного животика заставляет твое тело превращать всю сперму в твоей пизде в большее количество слайма.)]");
					} else {
						sbFluidDrain.append("[style.italicsSex(Набухание твоего беременного животика заставляет твое тело выталкивать всю сперму в твоей пизде.)]");
					}
				}
				
				float fluidAmountUrethra = target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA);
				boolean retentionUrethra = target.hasCreampieRetentionArea(SexAreaOrifice.VAGINA);
				if(retentionUrethra) {
					fluidAmountUrethra = (int)(fluidAmountUrethra-Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT);
					if(fluidAmountUrethra>0) {
						target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, fluidAmountUrethra);
					}
					if(sbFluidDrain.length()>0) {
						sbFluidDrain.append("<br/>");
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Твое тело также превращает [style.fluid("+fluidAmount+")] спермы в твоей уретре в большее количество слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Твое тело также выталкивает [style.fluid("+fluidAmount+")] спермы в твоей уретре.)]");
						}
					} else {
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Набухание твоего беременного животика заставляет твое тело превращать [style.fluid("+fluidAmount+")] спермы в твоей уретре в большее количество слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Набухание твоего беременного животика заставляет твое тело выталкивать [style.fluid("+fluidAmount+")] слайма в уретре твоей пуси.)]");
						}
					}
					
				} else if(fluidAmountUrethra>0) {
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, -fluidAmountUrethra);
					if(sbFluidDrain.length()>0) {
						sbFluidDrain.append("<br/>");
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Твоё тело также превращает весь слайм в твоей пуси в ещё больше слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Твоё тело также выталкивает весь слайм в твоей пуси.)]");
						}
					} else {
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Опухание твоего беременного животика заставляет твоё тело превращать весь слайм в твоей пуси в ещё больше слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Опухание твоего беременного животика заставляет твоё тело выталкивать весь слайм в твоей пуси.)]");
						}
					}
				}
				if(sbFluidDrain.length()>0) {
					sb.append("<p style='text-align:center;'>");
						sb.append(sbFluidDrain.toString());
					sb.append("</p>");
				}
				sb.append("<p style='text-align:center;'>");
					sb.append("[style.italicsMinorBad(Твоя пуси"+(Main.game.isUrethraEnabled()?" и его уретра":"")+" может вмещать только [style.fluid("+Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT+")] жидкости во время беременности!)]");
				sb.append("</p>");
				
			} else {
				target.endPregnancy(false);
				sb.append("<p>"
							+ "Прошло достаточно времени, чтобы вы могли быть уверены, что вы в безопасности."
							+ " Нет никаких признаков вздутия живота,"+(target.getBodyMaterial()==BodyMaterial.SLIME?" или каких-либо слизистых ядер, растущих внутри вас,":"")
								+" и вы понимаете, что, несмотря на незащищённый секс, вам удалось избежать беременности."
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
								?"[pc.thought(Чёрт...)]"
								:"[pc.thought(Ну, это облегчение...)]")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Ты не беременна!</b>"
						+ "</p>");	
			}

			if(target.isPlayer()) {
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public String applyPostRemovalStatusEffect(GameCharacter target) {
			if(!target.isPregnant()) {
				target.performImpregnationCheck(false);
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_1 = new AbstractStatusEffect(80,
			"pregnant",
			"pregnancy1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("-5% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"От одного из [npc.namePos] недавних сексуальных контактов, [npc.sheHas] была зачата!"
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Через [npc.skinColour] [npc.skin], который составляет [npc.her] тело, можно увидеть "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленький слайм-ядро"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растет внутри [npc.herHim]."
								:" Из-за того, что аркана ускоряет беременность людей, [npc.she] перейдет к следующей стадии с тревожной скоростью."));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			int maxHourLength = (int)((Main.getProperties().pregnancyDuration * 7 * 24) / 2f);
			target.addStatusEffect(PREGNANT_2, 60 * 60 * ((maxHourLength-12) + Util.random.nextInt(13)));
			
			boolean breastGrowth = false;
			if(Main.getProperties().pregnancyBreastGrowth>0 && target.getBreastRawSizeValue()<Main.getProperties().pregnancyBreastGrowthLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyBreastGrowth - Main.getProperties().pregnancyBreastGrowthVariance + Util.random.nextInt(Main.getProperties().pregnancyBreastGrowthVariance*2 + 1));
				
				if(target.getBreastRawSizeValue() + valueIncrease > Main.getProperties().pregnancyBreastGrowthLimit) {
					breastGrowth = true;
					target.setBreastSize(Main.getProperties().pregnancyBreastGrowthLimit);
				} else {
					breastGrowth = true;
					target.incrementBreastSize(valueIncrease);
				}
			}
			
			boolean udderGrowth = false;
			if(target.hasBreastsCrotch() && Main.getProperties().pregnancyUdderGrowth>0 && target.getBreastCrotchRawSizeValue()<Main.getProperties().pregnancyUdderGrowthLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyUdderGrowth - Main.getProperties().pregnancyBreastGrowthVariance + Util.random.nextInt(Main.getProperties().pregnancyBreastGrowthVariance*2 + 1));
				
				if(target.getBreastCrotchRawSizeValue() + valueIncrease > Main.getProperties().pregnancyUdderGrowthLimit) {
					udderGrowth = true;
					target.setBreastCrotchSize(Main.getProperties().pregnancyUdderGrowthLimit);
				} else {
					udderGrowth = true;
					target.incrementBreastCrotchSize(valueIncrease);
				}
			}
			
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			if (!((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				sb.append("<p>"
							+ "Несмотря на то, что изменения происходили постепенно, ты вдруг осознаешь, что твой живот раздулся до огромных размеров."
							+ (target.isTaur()
								?"Ты оглядываешься через плечо и смотрю на огромный живот в животе твоей [pc.legRace], задаваясь вопросом, насколько большим он станет."
								:"Ты не можешь удержаться от желания погладить огромный живот в твоем животе, задаваясь вопросом, насколько большим он станет.")
							+ "Поскольку это твой первый опыт беременности, ты не совсем знаешь, чего ожидать, но успокаиваешься, вспомнив, что Лилая всегда рядом, чтобы помочь."
						+ "</p>");
			} else {
				sb.append("<p>"
							+ "Несмотря на то, что изменения происходили постепенно, ты вдруг осознаешь, что твой живот раздулся до огромных размеров.");
							if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
								sb.append((target.isTaur()
										?"Ты оглядываешься через плечо и с отвращением смотришь на огромный живот в животе твоей [pc.legRace], ругая себя за то, что забеременела."
										:"С отвращением на лице ты осторожно тыкаешь в огромный живот в твоем животе, ругая себя за то, что забеременела."));
							} else {
								sb.append((target.isTaur()
										?"Ты оглядываешься через плечо и смотришь на огромный живот в животе твоей [pc.legRace], улыбаясь с теплотой от ощущения беременности."
										:"Ты не можешь удержаться от желания погладить огромный живот в твоем животе, улыбаясь с теплотой от ощущения беременности."));
							}
							sb.append("Пройдя через все это раньше, вы знаете, что вам еще предстоит пройти долгий путь, прежде чем вы будете готовы к родам."
						+ "</p>");
			}

			if(target.getBodyMaterial()==BodyMaterial.SLIME) {
				sb.append("<p>"
						+ "Просвечивая сквозь слайм, из которого состоит ваше тело, вы видите, что "
						+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" внутри вас выросли..."
				+ "</p>");
			}
			sb.append("<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Вы теперь сильно беременны!</b>"
						+ "</p>");
			
			if(breastGrowth) {
				sb.append("<p><i>"
							+"Ваши груди опухли и увеличились, поскольку ваше тело готовится к началу лактации."
							+ " У вас теперь есть [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-cup":"") + " breasts)]!"
						+ "</i></p>");
			}
			if(udderGrowth) {
				sb.append("<p><i>"
							+"Ваши [pc.udders] опухли и увеличились в размерах, поскольку ваше тело готовится к началу лактации."
							+ " У вас теперь есть [style.boldSex([pc.udderSize]"  + (target.getBreastCrotchRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastCrotchSize().getCupSizeName()+"-cup":"") + " [pc.udders])]!"
						+ "</i></p>");
			}
			
			return sb.toString();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_2 = new AbstractStatusEffect(80,
			"heavily pregnant",
			"pregnancy2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues("-10% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NamePos] живот значительно увеличился, что делает очевидным, что [npc.sheIs] сильно беременна."
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Через [npc.skinColour] [npc.skin], которое составляет тело [npc.her], вы можете увидеть "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленький слизистый ядро"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растет внутри [npc.herHim]..."
								:" Из-за того, что аркана ускоряет беременность людей, [npc.she] перейдет на финальную стадию с тревожной скоростью."));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.setTimeProgressedToFinalPregnancyStage(Main.game.getSecondsPassed());

			boolean lactationIncrease = false;
			if(Main.getProperties().pregnancyLactationIncrease>0 && target.getBreastRawMilkStorageValue()<Main.getProperties().pregnancyLactationLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyLactationIncrease - Main.getProperties().pregnancyLactationIncreaseVariance + Util.random.nextInt(Main.getProperties().pregnancyLactationIncreaseVariance*2 + 1));
				
				if(target.getBreastRawMilkStorageValue() + valueIncrease > Main.getProperties().pregnancyLactationLimit) {
					lactationIncrease = true;
					target.setBreastMilkStorage(Main.getProperties().pregnancyLactationLimit);
				} else {
					lactationIncrease = true;
					target.incrementBreastMilkStorage(valueIncrease);
				}
			}

			boolean lactationUddersIncrease = false;
			if(target.hasBreastsCrotch() && Main.getProperties().pregnancyUdderLactationIncrease>0 && target.getBreastCrotchRawMilkStorageValue()<Main.getProperties().pregnancyUdderLactationLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyUdderLactationIncrease - Main.getProperties().pregnancyLactationIncreaseVariance + Util.random.nextInt(Main.getProperties().pregnancyLactationIncreaseVariance*2 + 1));
				
				if(target.getBreastCrotchRawMilkStorageValue() + valueIncrease > Main.getProperties().pregnancyUdderLactationLimit) {
					lactationUddersIncrease = true;
					target.setBreastCrotchMilkStorage(Main.getProperties().pregnancyUdderLactationLimit);
				} else {
					lactationUddersIncrease = true;
					target.incrementBreastCrotchMilkStorage(valueIncrease);
				}
			}
			
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			boolean firstTimePregnancy = !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
			sb.append("<p>");
				if(target.isTaur()) {
					sb.append("К настоящему моменту ваш [pc.legRace] беременный живот вырос до огромного размера, и вы действительно можете почувствовать дополнительный вес в нижней части тела, когда вы [pc.walk].");
				} else {
					sb.append("К этому моменту ваш живот полностью выпячивается перед вами, и вам приходится сгибаться назад и поддерживать себя одной рукой, когда вы [pc.walk] вокруг.");
				}
				
				if(target.getBodyMaterial()==BodyMaterial.SLIME) {
					sb.append(" Видя сквозь полупрозрачный слайм, из которого состоит ваше тело, вы видите, что "
								+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"
								+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растущий внутри вас "+(target.getPregnantLitter().getTotalLitterCount()==1?"has":"have")
								+" вырос до размеров вашего собственного, и вы знаете, что теперь готовы к родам.");
					
				} else if(target.isVaginaEggLayer()) {
					sb.append(" Хотя вы можете чувствовать, как твердые скорлупы ваших яиц давят на внутренние стенки вашей матки, вы не находите это ощущение каким-либо образом неудобным."
							+ " Если что, то это ощущение только усиливает ваши материнские инстинкты, и вы часто ловите себя на том, что мечтаете о том, чтобы отложить и высиживать свои яйца.");
					
				} else {
					if(firstTimePregnancy) {
						sb.append(" Несколько часов назад вы почувствовали странный гул в своем беременном животе, и после немного понервничав, вы поняли, что это ваш ребенок шевелится в вашей матке."
								+ " Вы продолжаете чувствовать еще один толчок время от времени, и вы знаете, что вы готовы к родам.");
					} else {
						sb.append(" Несколько часов назад вы почувствовали знакомый гул в своем беременном животе, и по опыту, вы сразу узнали, что это ваш ребенок шевелится в вашей матке."
									+ " Вы продолжаете чувствовать еще один толчок время от времени, и вы знаете, что вы готовы к родам.");
					}
				}
			sb.append("</p>");
			
			sb.append("<p>");
				if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
					sb.append("[pc.thought(I really should go and see Lilaya... Or maybe I'll stay like this for a little while!)]");
				} else if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					sb.append("[pc.thought(Finally! I can go and see Lilaya and get "+(target.getPregnantLitter().getTotalLitterCount()==1?"this brat":"these brats")+" out of me! I <b>hate</b> being pregnant!)]");
				} else {
					sb.append("[pc.thought(I really should go and see Lilaya...)]");
				}
			sb.append("</p>");
			
			if(lactationIncrease) {
				sb.append("<p><i>");
					if(target.isTaur()) {
						sb.append("Your breasts have gotten noticeably heavier, and as you experimentally grope your chest, you feel droplets of [pc.milk] beading up on your engorged teats.");
					} else {
						sb.append("Your breasts have gotten noticeably heavier, and as you softly stroke the round bump in your belly, you feel droplets of [pc.milk] beading up on your engorged teats.");
					}
					sb.append(" Вы теперь можете производить [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.milk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!");
				sb.append("</i></p>");
			}
			if(lactationUddersIncrease) {
				sb.append("<p><i>");
					sb.append("Ваши [pc.udders] стали заметно тяжелее, и когда вы идете, вы чувствуете, как капли [pc.crotchMilk] скапливаются на ваших набухших сосках."
							+ " Вы теперь можете производить [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.crotchMilk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!");
				sb.append("</i></p>");
			}
			
			sb.append("<p style='text-align:center;'>"
						+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Вы теперь готовы к родам!</b>" 
					+ "</p>");
			
			return sb.toString();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect PREGNANT_3 = new AbstractStatusEffect(80,
			"готов к родам",
			"pregnancy3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues("-15% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
							(target.isTaur()
								?"[npc.NamePos] живот раздулся до колоссальных размеров, что ясно видно каждому, кто взглянет [npc.her] твою сторону, что [npc.sheIs] готова к родам."
								:"[npc.NamePos] живот раздулся до колоссальных размеров, и [npc.sheIs] обнаруживает, что [npc.sheHasFull] не может поддерживать [npc.her] спину одной рукой, пока [npc.she] [npc.verb(walk)].")
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Через [npc.skinColour] [npc.skin], которое составляет [npc.her] тело, можно увидеть "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" полностью развитый слайм-ядро"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+"."
								:"")
							+(target.isPlayer()
								?" Возможно, стоит навестить Лилайу..."
								:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPregnant()
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_0)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_1)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_1 = new AbstractStatusEffect(80,
			" Инкубация яиц (Живот)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] stomach filled with eggs!"
							+ " Благодаря эффектам аркана, эти яйца наверняка быстро созреют и перейдут к следующей стадии инкубации в течение нескольких дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_STOMACH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "Вес в твоем животе стал заметно тяжелее, и ты не можешь не задаться вопросом, как долго это продлится, прежде чем ты будешь готова отложить яйца, которые инкубируются в твоем животе..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(Яйца в твоем животе выросли в размерах!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_2 = new AbstractStatusEffect(80,
			"Продвинутая инкубация яиц (живот)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] stomach filled with eggs!"
							+ "@@H0@@");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.ANUS, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("Контекст: Java UI/Dialogue src/com/lilithsthrone/game/character/effects/StatusEffect.java\nТекст:\n<p>"
						+ "Из-за значительного веса, вы уверены, что яйца в вашем желудке уже достигли полной зрелости и могут быть снесены и вылуплены в любое время по вашему выбору."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(Вы теперь готовы снести яйца, которые инкубировались в вашем желудке!)]"
						+ "<br/>(Чтобы снести яйца, откройте меню своего телефона и перейдите на вкладку 'Яйца.')"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_3 = new AbstractStatusEffect(80,
			"Завершена инкубация яиц (Желудок)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Из одного из [npc.namePos] сексуальных контактов, [npc.sheHas] имел [npc.her] желудок, наполненный яйцами. Достигнув полной зрелости, они теперь готовы быть снесены и вылуплены.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.ANUS)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 3);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_1 = new AbstractStatusEffect(80,
			"Инкубация яиц (Грудь)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Из одного из недавних сексуальных контактов, [npc.namePos] у [npc.sheHas] была [npc.her] [npc.breasts], наполненная яйцами!"
							+ "Благодаря эффектам аркана, эти яйца наверняка быстро созреют и перейдут к следующей стадии инкубации в течение нескольких дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "Вес в твоем теле стал заметно тяжелее, и ты не можешь не задаться вопросом, как долго это продлится, прежде чем ты будешь готова отложить яйца, которые инкубируются в них..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(Яйца в твоих грудях увеличились в размерах!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_2 = new AbstractStatusEffect(80,
			"Расширенная инкубация яиц (Грудь)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Из одного из недавних сексуальных контактов, [npc.namePos] у [npc.sheHas] была [npc.her] [npc.breasts], наполненная яйцами!"
							+ "Благодаря эффектам Аркана, эти яйца наверняка быстро созреют и перейдут к последней стадии инкубации в течение нескольких дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "Из-за их значительного веса, вы уверены, что яйца в вашем [npc.breasts] к настоящему моменту достигли полной зрелости, и могут быть снесены и вылуплены в любое время по вашему выбору."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(Вы теперь готовы снести яйца, которые инкубировались в вашем [npc.breasts]!)]"
						+ "<br/>(Чтобы снести яйца, откройте меню своего телефона и перейдите на вкладку 'Яйца')."
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_3 = new AbstractStatusEffect(80,
			"Завершена инкубация яиц (Грудь)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Из одного из [npc.namePos] сексуальных контактов, [npc.sheHas] имела [npc.her] [npc.breasts], заполненные яйцами. Достигнув полной зрелости, они теперь готовы к откладыванию и вылуплению.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.NIPPLE)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 3);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_1 = new AbstractStatusEffect(80,
			"Инкубация яиц (Грудь-соски)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Инкубация яиц (Вымя)";
			}
			return "Инкубация яиц (Грудь-соски)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Из одного из [npc.namePos] недавних сексуальных контактов, [npc.sheHas] имела [npc.her] [npc.crotchBoobs], заполненные яйцами!"
							+ "Благодаря эффектам аркана, эти яйца наверняка быстро созреют и перейдут к следующей стадии инкубации в течение нескольких дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_CROTCH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "Вес в твоем [npc.crotchBoobs] заметно увеличился, и ты не можешь не задаться вопросом, как долго это продлится, прежде чем ты будешь готова отложить яйца, которые инкубируются там..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(Яйца в твоих [npc.crotchBoobs] увеличились в размерах!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_2 = new AbstractStatusEffect(80,
			"Расширенная инкубация яиц (Грудь-соски)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Расширенная инкубация яиц (Вымя)";
			}
			return "Расширенная инкубация яиц (Грудь-соски)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Из одного из недавних сексуальных контактов, [npc.namePos] у [npc.sheHas] была [npc.her] [npc.crotchBoobs], наполненная яйцами!"
							+ "Благодаря эффектам аркана, эти яйца наверняка быстро созреют и перейдут к финальной стадии инкубации в течение нескольких дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE_CROTCH, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "Из-за значительного веса, вы уверены, что яйца в вашем [npc.crotchBoobs] уже достигли полной зрелости и могут быть снесены и вылуплены в любое время по вашему выбору."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(Вы теперь готовы снести яйца, которые инкубировались в вашем [npc.crotchBoobs]!)]"
						+ "<br/>(Чтобы снести яйца, откройте меню своего телефона и перейдите в раздел 'Яйца.')"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_3 = new AbstractStatusEffect(80,
			"Завершена инкубация яиц (Грудь-соски)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Завершена инкубация яиц (Вымя)";
			}
			return "Завершена инкубация яиц (Грудь-соски)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Из одного из [npc.namePos] сексуальных контактов, [npc.sheHas] имела [npc.her] [npc.crotchBoobs], наполненные яйцами. Достигнув полной зрелости, они теперь готовы к снесению и вылуплению.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 3);
		}
	};

	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_1 = new AbstractStatusEffect(80,
			"Инкубация яиц (Spinneret)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйца"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Из одного из недавних сексуальных контактов, [npc.namePos] у [npc.sheHas] была [npc.her] [npc.spinneret], наполненная яйцами!"
							+ "Благодаря эффектам аркана, эти яйца наверняка быстро созреют и перейдут к следующей стадии инкубации в течение нескольких дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_SPINNERET_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "Вес в твоем [npc.spinneret] стал заметно тяжелее, и ты не можешь не задаться вопросом, как долго это продлится, прежде чем ты будешь готова отложить яйца, которые инкубируются там..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(Яйца в твоем [npc.spinneret] увеличились в размерах!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_2 = new AbstractStatusEffect(80,
			"Расширенная инкубация яиц (Spinneret)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] [npc.spinneret] filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the final stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.SPINNERET, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "From their significant weight, you're sure that the eggs in your [npc.spinneret] have by now reached full maturity, and could be laid and hatched at any time of your choosing."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(You're now ready to lay the eggs that have been incubating in your [npc.spinneret]!)]"
						+ "<br/>(To lay your eggs, open your phone's menu and access the 'Eggs' tab.)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_3 = new AbstractStatusEffect(80,
			"Completed Egg Incubation (Spinneret)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Из одного из сексуальных контактов, [npc.namePos] у [npc.sheHas] была [npc.her] [npc.spinneret], наполненная яйцами. Достигнув полной зрелости, они теперь готовы к откладыванию и вылуплению.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.SPINNERET)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 3);
		}
	};

	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_1 = new AbstractStatusEffect(80,
			"Egg Incubation (Womb)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] womb filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the next stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_WOMB_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "The weight in your womb has gotten noticeably heavier, and you can't help but wonder how long it will be before you're ready to lay the eggs which are incubating in there..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(Яйца в твоем чреве выросли в размерах!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_2 = new AbstractStatusEffect(80,
			"Продвинутая инкубация яиц (Черево)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Из одного из [npc.namePos] недавних сексуальных контактов, [npc.sheHas] имела [npc.her] чрево, наполненное яйцами!"
							+ "Благодаря эффектам аркана, эти яйца наверняка быстро созреют и перейдут к последней стадии инкубации в течение нескольких дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.VAGINA, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "Из-за их значительного веса, ты можешь быть уверен, что яйца в твоем чреве к настоящему моменту достигли полной зрелости и могут быть снесены и вылуплены в любое время по твоему выбору."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(Ты теперь готова отложить яйца, которые инкубировались в твоем чреве!)]"
						+ "<br/>(Чтобы отложить яйца, открой меню своего телефона и перейди во вкладку 'Яйца.')"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_3 = new AbstractStatusEffect(80,
			"Завершена инкубация яиц (Черево)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Из одного из [npc.namePos] сексуальных контактов, [npc.sheHas] имела [npc.her] чрево, наполненное яйцами. Достигнув полной зрелости, они теперь готовы к откладыванию и вылуплению.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.VAGINA)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 3);
		}
	};
	
	public static AbstractStatusEffect VIXENS_VIRILITY = new AbstractStatusEffect(80,
			"pharmaceutical fertility",
			"vixensVirility",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После употребления '[#ITEM_innoxia_pills_fertility.getName(false)]', фертильность и вирильность [npc.namePos] были временно усилены.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROMISCUITY_PILL = new AbstractStatusEffect(80,
			"pharmaceutical sterility",
			"promiscuityPill",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, -100f),
					new Value<>(Attribute.VIRILITY, -100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После употребления '[#ITEM_innoxia_pills_sterility.getName(false)]', фертильность и вирильность [npc.namePos] были значительно снижены."
							+ " Это является <b>превентивной</b> мерой, и не изменит результат любого незащищенного секса, который [npc.she] имел до того, как приняла таблетку!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROMISCUITY_PILL_PROLOGUE = new AbstractStatusEffect(80,
			"На таблетке",
			"promiscuityPill",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, -100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] находится под воздействием комбинированной оральной контрацептивной таблетки и поэтому не может забеременеть.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect BROODMOTHER_PILL = new AbstractStatusEffect(80,
			"фармацевтическая гиперфертильность",
			"broodmother_pill",
			PresetColour.CLOTHING_PINK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 100f),
					new Value<>(Attribute.VIRILITY, 100f)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Doubles)] потомство, зачатое")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"После употребления '[#ITEM_innoxia_pills_broodmother.getName(false)]', [npc.namePos] фертильность и вирильность были временно усилены,"
							+ " и если [npc.she] забеременеет кого-то или будет зачат [npc.herself], [npc.she] забеременеет гораздо больше потомства, чем обычно!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect LUBE_PILL = new AbstractStatusEffect(80,
			"lubricated body",
			"lube_pill",
			PresetColour.WETNESS,
			PresetColour.GENERIC_EXCELLENT,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESTING_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Во время секса:)]",
					"[style.colourWetness(Мгновенно смазывает)] отверстия",
					"[style.colourWetness(Мгновенно производит)] предэякулят")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("[npc.NameHasFull] употребил '[#ITEM_innoxia_pills_lubrication.getName(false)]', и теперь во время сексуальных ситуаций ");
			if(target.hasPenisIgnoreDildo()) {
				sb.append("[npc.her] член мгновенно начнет производить предэякулят, в то время как [npc.her] отверстия будут капать естественной смазкой.");
			} else {
				sb.append("[npc.her] отверстия будут выделять естественную смазку.");
			}
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_PRODUCTION = new AbstractStatusEffect(80,
			"Cum Production",
			"cumProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementPenisStoredCum(secondsPassed * target.getCumRegenerationPerSecond());
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = target.getCumRegenerationPerSecond()*60;
			
			return UtilText.parse(target, "[npc.NamePos] яички в настоящее время производят больше [npc.cum], со скоростью "+Units.fluid(cumRegenRate)+"/minute."
					+ " Они накопили "+Units.fluid(target.getPenisRawStoredCumValue())+", из максимального "+Units.fluid(target.getPenisRawCumStorageValue())+".");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()!=target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect CUM_FULL = new AbstractStatusEffect(80,
			"Full Balls",
			"cumFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = target.getCumRegenerationPerSecond()*60;
			
			return UtilText.parse(target, "[npc.NamePos] яички полностью заполнены [npc.cum] ("+Units.fluid(target.getPenisRawCumStorageValue())+"),"
					+ " и [npc.she] не может дождаться следующего раза, когда [npc.sheIs] сможет их опорожнить."
					+ " [npc.She] будет эякулировать "+Units.fluid(target.getPenisRawOrgasmCumQuantity())+" при оргазме, и затем будет восстанавливать [npc.cum] со скоростью "+Units.fluid(cumRegenRate)+"/minute.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()==target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_PRODUCTION = new AbstractStatusEffect(80,
			"Milk Production",
			"milkProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementBreastStoredMilk(secondsPassed * target.getLactationRegenerationPerSecond(true));
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getLactationRegenerationPerSecond(false) * 60;

			return UtilText.parse(target,
					"[npc.NamePos] груди производят [npc.milk] с индивидуальной скоростью "+Units.fluid(milkRegenRate)+"/minute,"
							+ " в общей сложности [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/minute)] (as [npc.sheHasFull] [npc.totalBreasts] breasts)."
					+ " They have stored "+Units.fluid(target.getBreastRawStoredMilkValue())+", out of a maximum of "+Units.fluid(target.getBreastRawMilkStorageValue())+".");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0 && target.getBreastRawStoredMilkValue()<target.getBreastRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_FULL = new AbstractStatusEffect(80,
			"Full Breasts",
			"milkFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getLactationRegenerationPerSecond(false) * 60;
			//milkRegenRate+" |"+
			return UtilText.parse(target,
					"[npc.NamePos] [npc.breasts] are filled with "+Units.fluid(target.getBreastRawMilkStorageValue())+" of [npc.milk].<br/>"
						+ "They produce more [npc.milk] at an individual rate of "+Units.fluid(milkRegenRate)+"/minute,"
						+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/minute)] (as [npc.sheHasFull] [npc.totalBreasts] breasts).");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0
					&& target.getBreastRawStoredMilkValue()==target.getBreastRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_CROTCH_PRODUCTION = new AbstractStatusEffect(80,
			"Crotch-boob Milk Production",
			"milkCrotchProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target==null) {
				return super.getName(target);
			}
			return UtilText.parse(target, "[npc.CrotchBoob] Milk Production");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementBreastCrotchStoredMilk((secondsPassed) * target.getCrotchLactationRegenerationPerSecond(true));
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
			
			return UtilText.parse(target,
					"[npc.NamePos] [npc.crotchBoobs] производят [npc.crotchMilk] с индивидуальной скоростью "+Units.fluid(milkRegenRate)+"/minute,"
							+ " в общей сложности [style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/минуту] (как [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs])."
					+ " У них хранится "+Units.fluid(target.getBreastCrotchRawStoredMilkValue())+", из максимального "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+".");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()!=target.getBreastCrotchRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_CROTCH_FULL = new AbstractStatusEffect(80,
			"Полные @@T0@@",
			"milkCrotchFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target==null) {
				return super.getName(target);
			}
			return UtilText.parse(target, "Полные [npc.CrotchBoobs]");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
		
			return UtilText.parse(target,
					" [npc.NamePos] [npc.crotchBoobs] наполнены "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+" [npc.crotchMilk].<br/>"
							+ " Они производят больше [npc.crotchMilk] с индивидуальной скоростью "+Units.fluid(milkRegenRate)+"/minute,"
							+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/minute)] (as [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs]).");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()==target.getBreastCrotchRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect STRETCHING_ORIFICE = new AbstractStatusEffect(80,
			"Big Toys",
			"sexEffects/combinationStretching",
			PresetColour.BASE_MAGENTA,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			float minimumStretchPercentage = 0.00_05f;
			
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeStretching().entrySet()) {
				AbstractClothing clothing = entry.getValue();
				int length = clothing.getClothingType().getPenetrationSelfLength();
				PenetrationGirth girth = PenetrationGirth.getGirthFromInt(clothing.getClothingType().getPenetrationSelfGirth());
				float diameter = Penis.getGenericDiameter(length, girth);
				
				// Stretch out the orifice by a factor of elasticity's modifier:
				float stretchModifier = 600f;
				float stretch = 0f;
				switch(entry.getKey()) {
					case ANUS:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getAssStretchedCapacity())*target.getAssElasticity().getStretchModifier());
						target.incrementAssStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getAssStretchedCapacity()>diameter) {
							target.setAssStretchedCapacity(diameter);
						}
						break;
					case MOUTH:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getFaceStretchedCapacity())*target.getFaceElasticity().getStretchModifier());
						target.incrementFaceStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getFaceStretchedCapacity()>diameter) {
							target.setFaceStretchedCapacity(diameter);
						}
						break;
					case NIPPLE:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getNippleStretchedCapacity())*target.getNippleElasticity().getStretchModifier());
						target.incrementNippleStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getNippleStretchedCapacity()>diameter) {
							target.setNippleStretchedCapacity(diameter);
						}
						break;
					case VAGINA:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getVaginaStretchedCapacity())*target.getVaginaElasticity().getStretchModifier());
						target.incrementVaginaStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getVaginaStretchedCapacity()>diameter) {
							target.setVaginaStretchedCapacity(diameter);
						}
						break;
					default:
						break;
				}
			}
			
			return "";
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> effects = new ArrayList<>();
			
			Map<SexAreaOrifice, String> orifices = Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, "[style.boldVagina(Vagina)]:"),
					new Value<>(SexAreaOrifice.ANUS, "[style.boldAsshole(Asshole)]:"),
					new Value<>(SexAreaOrifice.MOUTH, "[style.boldMouth(Throat)]:"),
					new Value<>(SexAreaOrifice.NIPPLE, "[style.boldNipple(Nipples)]:"));
			List<SexAreaOrifice> orificesFound = new ArrayList<>();
			for(Entry<SexAreaOrifice, String> orifice : orifices.entrySet()) {
				if(target.getSexToyOrificeStretching().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeStretching().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					PenetrationGirth girth = PenetrationGirth.getGirthFromInt(clothing.getClothingType().getPenetrationSelfGirth());
					float diameter = Penis.getGenericDiameter(length, girth);
					
					effects.add(orifice.getValue());
					effects.add("[style.boldBad(Stretching)] up to [style.boldPinkLight("+Units.size(diameter)+")]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificePreventingStretchRecovery().containsKey(orifice.getKey())) {
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldMinorBad(Preventing)] [style.boldPinkLight(stretch recovery)]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificeTooDeep().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeTooDeep().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldTerrible(Too deep)] at [style.boldPinkLight("+Units.size(length)+")]");
				}
			}
			
			return effects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			
			List<String> stretching = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeStretching().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						stretching.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						stretching.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						stretching.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						stretching.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			List<String> preventingRecovery = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificePreventingStretchRecovery().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						preventingRecovery.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						preventingRecovery.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						preventingRecovery.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						preventingRecovery.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			List<String> tooDeep = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeTooDeep().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						tooDeep.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						tooDeep.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						tooDeep.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						tooDeep.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			
			boolean needBreak = false;
			if(!target.getSexToyOrificeStretching().isEmpty()) {
				boolean singular = target.getSexToyOrificeStretching().size()==1;
				descriptionSB.append("[npc.NamePos] "+(singular?"sex toy is":"sex toys are")+" too big for [npc.herHim], and as a result, [npc.her] "
						+Util.stringsToStringList(stretching, false)+(singular?" is":" are")+" being [style.colourBad(stretched out)]!");
				needBreak = true;
			}
			
			if(!target.getSexToyOrificePreventingStretchRecovery().isEmpty()) {
				if(needBreak) {
					descriptionSB.append(" ");
				}
				boolean singular = target.getSexToyOrificePreventingStretchRecovery().size()==1;
				descriptionSB.append("[npc.NamePos] "+(singular?"sex toy is":"sex toys are")+" preventing [npc.her] [style.colourMinorBad(stretched)] " +Util.stringsToStringList(preventingRecovery, false)+" from recovering!");
				needBreak = true;
			}
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				if(!target.getSexToyOrificeTooDeep().isEmpty()) {
					if(needBreak) {
						descriptionSB.append(" ");
					}
					boolean singular = target.getSexToyOrificeTooDeep().size()==1;
					descriptionSB.append((singular?"The sex toy":"The sex toys")+" inserted in [npc.namePos] "
							+Util.stringsToStringList(tooDeep, false)+(singular?" is":" are")+" [style.colourTerrible(uncomfortably deep)]!");
				}
			}
			
			return UtilText.parse(target, descriptionSB.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getSexToyOrificeTooDeep().isEmpty() || !target.getSexToyOrificeStretching().isEmpty() || !target.getSexToyOrificePreventingStretchRecovery().isEmpty();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter target) {
			Set<SexAreaOrifice> orifices = new HashSet<>(target.getSexToyOrificeStretching().keySet());
			orifices.addAll(target.getSexToyOrificePreventingStretchRecovery().keySet());
			orifices.addAll(target.getSexToyOrificeTooDeep().keySet());
			return getStretchingOrificeStatus(target, !target.getSexToyOrificeStretching().isEmpty(), !target.getSexToyOrificePreventingStretchRecovery().isEmpty(), !target.getSexToyOrificeTooDeep().isEmpty(), orifices);
		}
	};
	
	public static AbstractStatusEffect RECOVERING_ORIFICE = new AbstractStatusEffect(80,
			"Recovering Vagina",
			"recoveringOrifice",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f), new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			int i=0;
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.isInSex()) {
				sb.append("Stretched");
			} else {
				sb.append("Recovering");
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				sb.append(" Vagina");
				i++;
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				sb.append(" Anus");
				i++;
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				sb.append(" Nipples");
				i++;
			}
			if (target.hasBreastsCrotch()
					&& target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				sb.append(" Crotch Nipples");
				i++;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				sb.append(" Penile Urethra");
				i++;
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				sb.append(" Vaginal Urethra");
				i++;
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				sb.append(" Throat");
				i++;
			}
			
			if(i>1) {
				if(Main.game.isInSex()) {
					return "Stretched Orifices";
				} else {
					return "Recovering Orifices";
				}
			} else {
				return sb.toString();
			}
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			
			Set<SexAreaOrifice> stretchRecoveryPrevention = new HashSet<>(target.getSexToyOrificePreventingStretchRecovery().keySet());
			stretchRecoveryPrevention.addAll(target.getSexToyOrificeStretching().keySet());
			
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.VAGINA) && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				target.incrementVaginaStretchedCapacity(-target.getVaginaPlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getVaginaStretchedCapacity()<target.getVaginaRawCapacityValue()) {
					target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.ANUS) && target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				target.incrementAssStretchedCapacity(-target.getAssPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getAssStretchedCapacity()<target.getAssRawCapacityValue()) {
					target.setAssStretchedCapacity(target.getAssRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.NIPPLE) && target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				target.incrementNippleStretchedCapacity(-target.getNipplePlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getNippleStretchedCapacity()<target.getNippleRawCapacityValue()) {
					target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.MOUTH) && target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				target.incrementFaceStretchedCapacity(-target.getFacePlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getFaceStretchedCapacity()<target.getFaceRawCapacityValue()) {
					target.setFaceStretchedCapacity(target.getFaceRawCapacityValue());
				}
			}
			if(target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				target.incrementNippleCrotchStretchedCapacity(-target.getNippleCrotchPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getNippleCrotchStretchedCapacity()<target.getNippleCrotchRawCapacityValue()) {
					target.setNippleCrotchStretchedCapacity(target.getNippleCrotchRawCapacityValue());
				}
			}
			if(target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				target.incrementPenisStretchedCapacity(-target.getUrethraPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getPenisStretchedCapacity()<target.getPenisRawCapacityValue()) {
					target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
				}
			}
			if(target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				target.incrementVaginaUrethraStretchedCapacity(-target.getVaginaUrethraPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getVaginaUrethraStretchedCapacity()<target.getVaginaUrethraRawCapacityValue()) {
					target.setVaginaUrethraStretchedCapacity(target.getVaginaUrethraRawCapacityValue());
				}
			}
			
			return "";
		}
		
		private String getRecoveryText(float recoveryModifier) {
			int minutes = (int) (1/recoveryModifier)/60;
			int hours = minutes /60;
			
			return "Tightens "+Units.size(1)+" every "+(hours>=1
						?(hours)+" hour"+(hours==1?"":"s")+(minutes%60>0?" and "+(minutes%60)+" minute"+(minutes==1?"":"s"):"")
						:(minutes )+" minute"+(minutes==1?"":"s"));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> recoveringEffects = new ArrayList<>();

			Set<SexAreaOrifice> stretchRecoveryPrevention = new HashSet<>(target.getSexToyOrificePreventingStretchRecovery().keySet());
			stretchRecoveryPrevention.addAll(target.getSexToyOrificeStretching().keySet());
			
			String recoveringText = "recovering";
			String from1 = "From";
			String from2 = "to";
			
			if(Main.game.isInSex()) {
				recoveringText = "stretched";
				from1 = "To";
				from2 = "from";
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				recoveringEffects.add("[style.boldVagina(Vagina "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getVaginaStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getVaginaRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.VAGINA)) {
					recoveringEffects.add("[style.boldBad(Sex toy preventing recovery!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				recoveringEffects.add("[style.boldAsshole(Asshole "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getAssStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getAssRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.ANUS)) {
					recoveringEffects.add("[style.boldBad(Секс-игрушка предотвращает восстановление!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getAssPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				recoveringEffects.add("[style.boldNipples(Соски "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getNippleStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getNippleRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.NIPPLE)) {
					recoveringEffects.add("[style.boldBad(Секс-игрушка предотвращает восстановление!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNipplePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				recoveringEffects.add("[style.boldMouth(Горло "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getFaceStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getFaceRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.MOUTH)) {
					recoveringEffects.add("[style.boldBad(Секс-игрушка предотвращает восстановление!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getFacePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.hasBreastsCrotch() && target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				recoveringEffects.add("[style.boldNipplesCrotch(Соски промежности "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getNippleCrotchStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getNippleCrotchRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNippleCrotchPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				recoveringEffects.add("[style.boldPenisUrethra(Мочеиспускательный канал полового члена "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getPenisStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getPenisRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getUrethraPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				recoveringEffects.add("[style.boldVaginaUrethra(Мочеиспускательный канал влагалища "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getVaginaUrethraStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getVaginaUrethraRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaUrethraPlasticity().getRecoveryModifier())+")]");
			}
			
			
			return recoveringEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			
			StringBuilder descriptionSB = new StringBuilder("После того, как тебя заставили принять");
			
			List<String> orificesRecovering = new ArrayList<>();
			boolean plural = false;
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				orificesRecovering.add("[style.boldVagina(vagina)]");
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				orificesRecovering.add("[style.boldAnus(asshole)]");
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				orificesRecovering.add("[style.boldMouth(throat)]");
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				orificesRecovering.add("[style.boldNipple(nipples)]");
				plural = true;
			}
			if (target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				orificesRecovering.add("[style.boldNipplesCrotch(соски промежности)]");
				plural = true;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				orificesRecovering.add("[style.boldPenisUrethra(пенис уретра)]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				orificesRecovering.add("[style.boldVaginaUrethra(вагинальная уретра)]");
			}
			if(orificesRecovering.size()==1) {
				descriptionSB.append(" объект, который был слишком большим для "+(plural?"them":"it")+", [npc.namePos] "+orificesRecovering.get(0)+" "+(plural?"have":"has")
						+" был растянут, и "+(plural?"need":"needs")+" некоторое время, в течение которого можно восстановить "+(plural?"their":"its")+" natural tightness.");
			} else {
				descriptionSB.append(" фаллические объекты, которые были слишком большими для них, [npc.namePos] "+Util.stringsToStringList(orificesRecovering, false)
						+" были растянуты, и им нужно некоторое время, чтобы восстановить свою естественную плотность.");
			}

			if(Main.game.isInSex()) {
				descriptionSB.append(" [style.italicsBad(Организмы не восстанавливаются во время секса!)]");
			}
			
			return UtilText.parse(target, descriptionSB.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ((target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity())
					|| (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity())
					|| (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity())
					|| (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity())
					|| (target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity())
					|| (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity())
					|| (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getRecoveringOrificeStatus(owner, super.getSVGString(owner));
		}
	};
	
	
	
	public static AbstractStatusEffect CREAMPIE_VAGINA = new AbstractStatusEffect(80,
			"Pussy Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.VAGINA);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.VAGINA);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ " Вся сперма из вашей проколотой кишки быстро всасывается вашим "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.VAGINA.getCharactersCumLossPerSecond(target)*secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.VAGINA);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null
					&& !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_VAGINA)
					&& !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Creampie)]", "[style.colourDirty(Брудно)] "+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
					target.addDirtySlot(InventorySlot.VAGINA);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.VAGINA);
			String pregnancyText = (target.isVisiblyPregnant()?"<br/>[style.italicsSex(Из-за беременности, максимальное хранилище "+Units.fluid(Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.VAGINA)) {
				if(target.isPlayer()) {
					return "Когда ты ходишь, ты можешь чувствовать, как сперма застряла в твоей недавно использованной пизде."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Забитая пизда:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.pussy] недавно была заполнена спермой."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Забитая пизда:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "Когда ты ходишь, ты можешь чувствовать, как сперма вытекает из твоей недавно использованной пизды."
							+pregnancyText;
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] [npc.pussy] недавно была заполнена спермой."
							+pregnancyText);
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.VAGINA;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпай: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Retaining)] кремпай из-за заклинания!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.VAGINA)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.VAGINA);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_VAGINA_URETHRA = new AbstractStatusEffect(80,
			"Вагинальное уретральное кремпай",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.URETHRA_VAGINA.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			List<AbstractClothing> blockingList = target.getBlockingCoverableAreaClothingList(CoverableArea.VAGINA, false);
			boolean pluggedFound = blockingList.removeIf((c) -> c.getItemTags().contains(ItemTag.PLUGS_VAGINA));
			blockingList.sort((c1, c2) -> c1.getSlotEquippedTo().getZLayer()-c2.getSlotEquippedTo().getZLayer());
			AbstractClothing clothingBlocking = blockingList.isEmpty()?null:blockingList.get(0);
//			if(clothingBlocking!=null) {
//				System.out.println(clothingBlocking.getName());
//			}
			boolean dirtyArea = clothingBlocking==null;

			// 'PLUGS_VAGINA' check doesn't entirely make sense, but if not included, and the character has a urethral and normal creampie, then the game enters a loop of cleaning-dirtying if a dildo is inserted into the vagina
			if(clothingBlocking!=null
//					&& !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_VAGINA)
					&& !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Creampie)]", "[style.colourDirty(Загрязнения)] "+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea && !pluggedFound && (clothingBlocking==null || !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA))) {
				if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
					target.addDirtySlot(InventorySlot.VAGINA);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.URETHRA_VAGINA);
			String pregnancyText = (target.isVisiblyPregnant()?"<br/>[style.italicsSex(Из-за беременности, максимальное хранилище - "+Units.fluid(Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.URETHRA_VAGINA)) {
				if(target.isPlayer()) {
					return "Когда вы идете, вы можете почувствовать сперму, застрявшую в уретре вашей влагалища."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Забитая уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] Вагинальная уретра недавно была заполнена спермой, прежде чем была забита."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Забитая уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "Когда ты ходишь, ты чувствуешь, как сперма вытекает из уретры твоей киски."
							+pregnancyText;
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] Уретра твоей киски недавно была заполнена спермой."
							+pregnancyText);
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.URETHRA_VAGINA;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпай: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Сохраняющий)] кремпай из-за заклинания!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_VAGINA);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_PENIS_URETHRA = new AbstractStatusEffect(80,
			"Уретра пениса кремпай",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.PENIS);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.URETHRA_PENIS);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "Вся сперма из уретры твоего пениса быстро всасывается твоей "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.URETHRA_PENIS.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.PENIS);
			
			if(clothingBlocking!=null){
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Creampie)]", "[style.colourDirty(Загрязняет)] "+clothingBlocking.getName()), false);
					}
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.PENIS)) {
				target.addDirtySlot(InventorySlot.PENIS);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.URETHRA_PENIS);
			
			if(target.isOrificePlugged(SexAreaOrifice.URETHRA_PENIS)) {
				if(target.isPlayer()) {
					return "Когда ты ходишь, ты чувствуешь, как сперма застряла в уретры твоего пениса."
							+ "<br/>[style.boldTerrible(Забитая уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще всасывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] уретра пениса недавно была заполнена спермой, а затем заткнута."
							+ "<br/>[style.boldУжасное(Заткнутая уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "Когда вы идете, вы можете почувствовать, как сперма вытекает из уретры вашего члена.";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] уретра пениса недавно была заполнена спермой.");
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.URETHRA_PENIS;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпай: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Сохранение)] кремпай благодаря заклинанию!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_PENIS)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_PENIS);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_ANUS = new AbstractStatusEffect(80,
			"Anal Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.ANUS.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.ANUS);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_ANUS) && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_ANUS)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Creampie)]", "[style.colourDirty(Загрязняет)] "+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.ANUS)) {
					target.addDirtySlot(InventorySlot.ANUS);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.ANUS, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.ANUS);
			
			if(target.isOrificePlugged(SexAreaOrifice.ANUS)) {
				if(target.isPlayer()) {
					return "Когда вы идете, вы можете почувствовать, как сперма застряла в вашей недавно использованной анусе."
							+ "<br/>[style.boldУжасное(Заткнутая анус:)] Сперма не вытекает"
							+ (retention?"!":", хотя оно все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] ублюдок недавно был наполнен спермой, прежде чем был заткнут."
							+ "<br/>[style.boldУжасный(Заткнутый анус:)] Сперма не вытекает"
							+ (retention?"!":", хотя оно все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "Когда ты ходишь, ты можешь чувствовать, как сперма вытекает из твоего недавно использованного ануса.";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] анус недавно был наполнен спермой.");
				}
			}
		
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.ANUS;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпай: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Сохранение)] кремпай благодаря заклинанию!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.ANUS)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.ANUS);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_NIPPLES = new AbstractStatusEffect(80,
			"Nipple Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.NIPPLE);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.NIPPLE);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "Вся сперма из твоих кремпайных сосков быстро всасывается твоим "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.NIPPLE.getCharactersCumLossPerSecond(target) * secondsPassed;

			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_NIPPLES) && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_NIPPLES)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Creampie)]", "[style.colourDirty(Загрязняет)] "+clothingBlocking.getName()), false);
					}
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.NIPPLE)) {
					target.addDirtySlot(InventorySlot.NIPPLE);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.NIPPLE);
			
			if(target.isOrificePlugged(SexAreaOrifice.NIPPLE)) {
				if(target.isPlayer()) {
					return "[pc.nipples] @@T1@@ have recently been filled with cum, before being plugged."
							+ "<br/>[style.boldTerrible(Plugged Nipples:)] No cum is leaking out"
							+ (retention?"!":", although it is still being absorbed!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.nipples] have recently been filled with cum."
							+ "<br/>[style.boldTerrible(Plugged Nipples:)] No cum is leaking out"
							+ (retention?"!":", although it is still being absorbed!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your recently-used [pc.nipples].";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] [npc.nipples] have recently been filled with cum.");
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.NIPPLE;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Retaining)] creampie due to enchantment!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.NIPPLE);
		}
	};

	public static AbstractStatusEffect CREAMPIE_NIPPLES_CROTCH = new AbstractStatusEffect(80,
			"Nipple Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Udder-nipple creampie";
			} else {
				return "Crotch-nipple creampie";
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.STOMACH);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.NIPPLE_CROTCH);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "All of the cum from your creampied nipples is quickly sucked away by your "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.NIPPLE_CROTCH.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH);
			
			if(clothingBlocking!=null){
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Creampie)]", "[style.colourDirty(Dirties)] "+clothingBlocking.getName()), false);
					}
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.STOMACH)) {
				target.addDirtySlot(InventorySlot.STOMACH);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.NIPPLE_CROTCH);
			
			if(target.isOrificePlugged(SexAreaOrifice.NIPPLE_CROTCH)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used [pc.crotchNipples]."
							+ "<br/>[style.boldTerrible(Plugged Crotch-nipples:)] No cum is leaking out"
							+ (retention?"!":", although it is still being absorbed!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.crotchNipples] have recently been filled with cum, before being plugged."
							+ "<br/>[style.boldTerrible(Plugged Crotch-nipples:)] No cum is leaking out"
							+ (retention?"!":", although it is still being absorbed!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "Как ты идешь, ты можешь почувствовать, как сперма вытекает из твоего недавно использованного [pc.crotchNipples].";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] [npc.crotchNipples] недавно были наполнены спермой.");
				}
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.NIPPLE_CROTCH;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпай: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Retaining)] кремпай из-за заклинания!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.NIPPLE_CROTCH)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_MOUTH = new AbstractStatusEffect(80,
			"Cummy Meal",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"Cummy meal"
					:"Yummy meal";
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			if(this.getExtraEffects(target)!=null) {
				attributeModifiersList.addAll(this.getExtraEffects(target));
			}
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.MOUTH.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			target.drainTotalFluidsStored(SexAreaOrifice.MOUTH, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, 
					target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
						?"[npc.NameHasFull] недавно проглотил порцию спермы."
						:"[npc.NameHasFull] недавно проглотил некоторое количество спермы.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.MOUTH;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпай: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Retaining)] кремпай из-за заклинания!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.MOUTH)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.MOUTH);
		}
	};

	public static AbstractStatusEffect CREAMPIE_SPINNERET = new AbstractStatusEffect(80,
			"Spinneret Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.SPINNERET.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(SexAreaOrifice.SPINNERET.getRelatedCoverableArea(target));
			boolean dirtyArea = clothingBlocking==null;

			if(clothingBlocking!=null){
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					if(target.isPlayer()) {
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Creampie)]", "[style.colourDirty(Dirties)] "+clothingBlocking.getName()), false);
					}
				}
			}
			
			if(dirtyArea) {
				InventorySlot spinneretSlot = SexAreaOrifice.SPINNERET.getRelatedInventorySlot(target);
				if(!target.getDirtySlots().contains(spinneretSlot)) {
					target.addDirtySlot(spinneretSlot);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.SPINNERET, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Как ты идешь, ты можешь почувствовать, как сперма вытекает из твоего недавно использованного spinneret.";
			} else {
				return UtilText.parse(target, 
						"[npc.NamePos] spinneret недавно была наполнена спермой.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.SPINNERET;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпай: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/minute"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/minute")
					+(retention
							?"<br/>[style.colourExcellent(Retaining)] кремпай из-за заклинания!"
							:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.SPINNERET)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.SPINNERET);
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"swollen belly",
			"inflation_stomach_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "После того, как ты был наполнен значительным количеством "+Util.stringsToStringList(fluidNames, false)+", твой живот теперь немного вздут."
						+ "Твой дополнительный вес делает его немного труднее двигаться.";
			} else {
				return UtilText.parse(target,
							"После того, как ты был наполнен значительным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] живот теперь немного вздут.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"inflated belly",
			"inflation_stomach_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "После того, как ты был наполнен огромным количеством "+Util.stringsToStringList(fluidNames, false)+", твой живот теперь заметно вздут."
						+ "Из-за значительного количества лишнего веса в вашем желудке вам становится труднее передвигаться.";
			} else {
				return UtilText.parse(target,
							"После того как вы набрали огромный вес в "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] живот теперь заметно вздут."
							+ "Из-за значительного количества лишнего веса в [npc.her] желудке [npc.her] способность двигаться.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"переполненный живот",
			"inflation_stomach_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "После того как вы набрали огромный вес в "+Util.stringsToStringList(fluidNames, false)+", ваш живот теперь значительно переполнен."
						+ "Из-за огромного количества лишнего веса в вашем желудке вам становится крайне трудно передвигаться.";
			} else {
				return UtilText.parse(target,
							"После того как вы набрали огромный вес в "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] живот теперь значительно переполнен."
									+ "Огромное количество лишнего веса в желудке [npc.her] делает крайне затруднительным для [npc.herHim] передвижение.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"swollen breasts",
			"inflation_breasts_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "После того как вы набрали значительное количество "+Util.stringsToStringList(fluidNames, false)+", ваши [pc.breasts] теперь немного опухли."
						+ "Ваш лишний вес делает передвижение немного более затруднительным.";
			} else {
				return UtilText.parse(target,
							"После того как вы набрали значительное количество "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] теперь немного опухли.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"inflated breasts",
			"inflation_breasts_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "После того как вы набрали огромное количество "+Util.stringsToStringList(fluidNames, false)+", ваши [pc.breasts] теперь заметно вздулись."
						+ "Значительное количество лишнего веса в верхней части тела делает передвижение более затруднительным.";
			} else {
				return UtilText.parse(target,
							"После того как вы набрали огромное количество "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] теперь заметно надуты."
							+ "Значительное количество лишнего веса в верхней части тела [npc.her] мешает [npc.her] двигаться.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"Чрезмерно надутые груди",
			"inflation_breasts_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "После того, как @@T0@@ надули колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", ваши [pc.breasts] теперь значительно надуты."
						+ "Огромное количество лишнего веса в верхней части тела мешает вам двигаться.";
			} else {
				return UtilText.parse(target,
							"После того, как @@T0@@ надули колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] теперь значительно надуты."
									+ "Огромное количество лишнего веса в верхней части тела [npc.her] мешает [npc.herHim] двигаться.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"swollen spinneret",
			"inflation_spinneret_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"После того, как @@T0@@ надули значительным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"are":"is")+"now a little swollen."
							+ " The extra weight is making it a little difficult for [npc.herHim] to move around.");
				} else {
					return UtilText.parse(target,
							"After being filled with a considerable amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] abdomen is now a little swollen."
							+ " The extra weight is making it a little difficult for [npc.herHim] to move around.");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"inflated spinneret",
			"inflation_spinneret_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"After being filled with a huge amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"are":"is")+"now noticeably inflated."
							+ " The extra weight is hindering [npc.her] ability to move.");
				} else {
					return UtilText.parse(target,
							"После того, как @@T0@@ брюхо было наполнено огромным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] брюхо теперь заметно вздулось."
							+ "Дополнительный вес мешает [npc.her] двигаться.");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"Чрезмерно вздутая паутинная железа",
			"inflation_spinneret_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"После того, как @@T0@@ брюхо было наполнено огромным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"are":"is")+"теперь сильно вздулось."
							+ "Огромный дополнительный вес делает для [npc.herHim] крайне затруднительным передвижение.");
				} else {
					return UtilText.parse(target,
							"После того, как @@T0@@ брюхо было наполнено огромным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] брюхо теперь сильно вздулось."
							+ "Огромное количество лишнего веса делает для [npc.herHim] крайне сложно передвигаться.");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FRUSTRATED_NO_ORGASM = new AbstractStatusEffect(80,
			"Frustrated",
			"frustrated",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] недавно имел сексуальный контакт, в котором [npc.she] не смог получить оргазм."
					+ "В результате, [npc.sheIs] чувствует себя крайне похотливым и расстроенным...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
//				target.removeStatusEffect(FRUSTRATED_NO_ORGASM); // Remove this effect if orgasmed within the last day
//			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PENT_UP_SLAVE = new AbstractStatusEffect(80,
			"Pent-up",
			"frustrated",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
				return UtilText.parse(target, "Поскольку [npc.sheIs] является нимфоманкой, [npc.name] [npc.is] чувствует себя крайне подавленной после того, как не имела сексуального контакта более четырех часов...");
			}
			return UtilText.parse(target, "[npc.Name] [npc.has] не имела сексуального удовлетворения более суток, и [npc.is] чувствует себя крайне подавленной...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int hoursToPentUp = 24;
			if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
				hoursToPentUp = 4;
			}
			
			return !target.isPlayer()
					&& target.isSlave()
					&& !target.isDoll()
					&& ((NPC)target).getLastTimeOrgasmedSeconds()+(60*60*hoursToPentUp)<Main.game.getSecondsPassed();
		}
	};

	/**
	 * This status effect is automatically removed from a character when orgasming in sex.
	 */
	public static AbstractStatusEffect DESPERATELY_HORNY = new AbstractStatusEffect(80,
			"Desperately horny",
			"desperately_horny",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESTING_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] чувствует себя невероятно похотливой в данный момент."
					+ " [npc.SheIs] с трудом справляется с похотью [npc.her], и поэтому [npc.are] становится гораздо более уязвимой для соблазнения, а также может более легко соблазнять других.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_1 = new AbstractStatusEffect(80,
			"Принудительная целомудренность (спокойно)",
			"chastity1",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.colourSex(Усиливается...)]")) {
		@Override
		public String getDescription(GameCharacter target) {//TODO if one key-holder, mention them
			return UtilText.parse(target, "[npc.Name] [npc.has] была заперта в целомудренности, и рано или поздно начнет чувствовать себя расстроенной из-за невозможности получить полноценный оргазм...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				int denominator = 1;
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					denominator = 2;
				}
					
				if(target.hasStatusEffect(CHASTITY_REMOVED_2)) {
					target.addStatusEffect(CHASTITY_2, (60*60*24*2)/denominator); // 2 instead of 5
					target.removeStatusEffect(CHASTITY_REMOVED_2);
					
				} else if(target.hasStatusEffect(CHASTITY_REMOVED_3)) {
					target.addStatusEffect(CHASTITY_3, (60*60*24*3)/denominator); // 3 instead of 7
					target.removeStatusEffect(CHASTITY_REMOVED_3);
					
				} else if(target.hasStatusEffect(CHASTITY_REMOVED_4)) {
					target.addStatusEffect(CHASTITY_4, -1);
					target.removeStatusEffect(CHASTITY_REMOVED_4);
					
				} else {
					target.addStatusEffect(CHASTITY_2, (60*60*24*5)/denominator); // 5 days
				}
			}
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.hasStatusEffect(CHASTITY_REMOVED_2) || target.hasStatusEffect(CHASTITY_REMOVED_3) || target.hasStatusEffect(CHASTITY_REMOVED_4)) {
				target.removeStatusEffect(CHASTITY_1);
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingChastity()
					&& !target.isDoll()
					&& !target.hasStatusEffect(CHASTITY_2)
					&& !target.hasStatusEffect(CHASTITY_3)
					&& !target.hasStatusEffect(CHASTITY_4);
		}
		@Override
		public int getApplicationLength(GameCharacter target) {
			int denominator = 1;
			if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
				denominator = 2;
			}
			return (60*60*24*2)/denominator; // 2 days
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_2 = new AbstractStatusEffect(80,
			"Принудительная девственность (беспокойство)",
			"chastity2",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			Util.newArrayListOfValues("[style.colourSex(Усиливается...)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] уже некоторое время находится в состоянии принудительной девственности, и [npc.is] уже начинает чувствовать себя раздраженной из-за невозможности получить полноценный оргазм...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				int denominator = 1;
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					denominator = 2;
				}
				target.addStatusEffect(CHASTITY_3, (60*60*24*7)/denominator); // 7 days
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_3 = new AbstractStatusEffect(80,
			"Принудительная девственность (напряжение)",
			"chastity3",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			Util.newArrayListOfValues("[style.colourSex(Усиливается...)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] уже давно находится в состоянии принудительной девственности, и [npc.is] чувствует себя очень раздраженной и напряженной из-за невозможности получить полноценный оргазм...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				target.addStatusEffect(CHASTITY_4, -1); // This should be enough to prevent the effect from looping back to CHASTITY_1, as once CHASTITY_4 is applied, CHASTITY_1's conditions are now false, and therefore CHASTITY_4's are true
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_4 = new AbstractStatusEffect(80,
			"Принудительная девственность (отчаяние)",
			"chastity4",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f)),
			Util.newArrayListOfValues("[style.colourSex(Максимальная интенсивность)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] уже вечность находится в состоянии принудительной девственности, и [npc.is] чувствует себя крайне раздраженной и напряженной из-за невозможности получить полноценный оргазм...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(!target.isWearingChastity()) {
				target.addStatusEffect(CHASTITY_REMOVED_4, 60*60*24*1); // 1 day (This effect needs this while the others don't due to the use of the isConditionsMet() method)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingChastity()
					&& !target.isDoll()
					&& !target.hasStatusEffect(CHASTITY_1)
					&& !target.hasStatusEffect(CHASTITY_2)
					&& !target.hasStatusEffect(CHASTITY_3);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean forceLoad() {
			return true;
		}
	};

	public static AbstractStatusEffect CHASTITY_REMOVED_2 = new AbstractStatusEffect(80,
			"Освобождена от девственности (беспокойство)",
			"chastityRemoved2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.RESTING_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Оргазм)] убирает этот эффект!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Недавно освобожденная от короткого заклинания, [npc.name] [npc.is] теперь чувствует себя очень возбужденной!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(Если [npc.sheIsFull] снова заперта в целомудрии, пока все еще под этим эффектом, [npc.she] останется в стадии 'возбужденной'!)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(CHASTITY_2);
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_2);
//			}
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_2); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_2);
		}
		@Override
		public int getApplicationLength(GameCharacter target) {
			return 60*60*24*1; // 1 day
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_REMOVED_3 = new AbstractStatusEffect(80,
			"Освобожденная от целомудрия (напряженная)",
			"chastityRemoved3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.RESTING_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Оргазм)] убирает этот эффект!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Недавно освобожденная от долгого заклинания, [npc.name] [npc.is] теперь чувствует себя очень возбужденной!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(Если [npc.sheIsFull] снова заперта в целомудрии, пока все еще под этим эффектом, [npc.she] останется в стадии 'напряженной'!)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(CHASTITY_3);
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_3);
//			}
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_3); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_3);
		}
		@Override
		public int getApplicationLength(GameCharacter target) {
			return 60*60*24*1; // 1 day
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_REMOVED_4 = new AbstractStatusEffect(80,
			"Освобожденная от целомудрия (отчаянная)",
			"chastityRemoved4",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.RESTING_LUST, 15f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Orgasming)] will remove this effect!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Recently released from an excruciatingly-long spell of being locked in chastity, [npc.name] [npc.is] now feeling unbelievably horny!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(If [npc.sheIsFull] locked back into chastity while still under this effect, [npc.she] will remain in the 'desperate' stage!)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_4); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
//		@Override
//		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_4);
//			}
//			return "";
//		}
//		@Override
//		public boolean isConditionsMet(GameCharacter target) {
//			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_4);
//		}
//		@Override
//		public int getApplicationLength(GameCharacter target) {
//			return 60*60*24*1; // 1 day
//		}
//		@Override
//		public boolean isConstantRefresh() {
//			return false;
//		}
	};
	
	public static AbstractStatusEffect RECOVERING_AURA = new AbstractStatusEffect(80,
			"Strengthened aura",
			"recoveringAura",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Due to a recent orgasm, your arcane aura has been temporarily strengthened."
						+ " While in this state, you will no longer receive an arcane essence if you orgasm!";
			} else {
				return UtilText.parse(target, "Due to a recent orgasm, [npc.namePos] arcane aura has been temporarily strengthened."
						+ " While [npc.she] remains in this state, you will not receive an arcane essence if [npc.she] orgasms in your presence!");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect EXPOSED = new AbstractStatusEffect(80,
			"exposed",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& !target.isFeral()
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, false, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect EXPOSED_ANIMAL = new AbstractStatusEffect(80,
			"exposed (feral parts)",
			"exposedFeral",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_TAN,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			if(target.getLegConfiguration().isGenitalsExposed(target)) {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", but as [npc.she] [npc.has] a feral body, [npc.she] [npc.verb(feel)] as though it's natural to be so exposed.");
			} else {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", but [npc.her] feral body is shaped in such a way that they aren't on public display.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& (target.getLegConfiguration()!=LegConfiguration.BIPEDAL || target.isFeral())
					&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES))
					&& ((target.hasBreastsCrotch() && target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH))
						|| target.isCoverableAreaVisible(CoverableArea.ANUS)
						|| (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis())
						|| (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect EXPOSED_DOLL = new AbstractStatusEffect(80,
			"exposed (doll)",
			"exposedDoll",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_GREY,
			PresetColour.BASE_GREY,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", but as [npc.sheIs] a doll, [npc.sheIs] incapable of feeling ashamed or embarrassed.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& ((target.hasBreastsCrotch() && target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH))
						|| (target.hasBreasts() && target.isFeminine() && target.isCoverableAreaVisible(CoverableArea.NIPPLES))
						|| target.isCoverableAreaVisible(CoverableArea.ANUS)
						|| (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis())
						|| (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect EXPOSED_BREASTS = new AbstractStatusEffect(80,
			"exposed breasts",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -2f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			if (! target.hasBreasts()) {
				return "exposed nipples";
			} else {
				return super.getName(target);
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Your exposed breasts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.getLegConfiguration()==LegConfiguration.BIPEDAL || ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES)))
					&& !target.isFeral()
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, false);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect EXPOSED_PLUS_BREASTS = new AbstractStatusEffect(80,
			"exposed",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& !target.isFeral()
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST = new AbstractStatusEffect(80,
			"exhibitionist",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, false, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST_BREASTS = new AbstractStatusEffect(80,
			"exhibitionist",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br/>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, false);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST_PLUS_BREASTS = new AbstractStatusEffect(80,
			"exhibitionist",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] одежда не скрывает [npc.her] "+getExposedPartsNamesList(target)+", и [npc.she] [npc.verb(feel)] невероятно сексуально и уверенно, ходя в такой откровенной одежде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-воспользовавшиеся-ситуацией</b><br/>Твоё оголённое тело привлекает всевозможные похотливые взгляды.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !target.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)
					&& isExposedParts(target, true, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect FETISH_PURE_VIRGIN = new AbstractStatusEffect(80,
			"Pure Virgin",
			"virginPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(represent)] идеальный образ чистой, праведной сущности. [npc.Her] благородное поведение и добродетельная личность маркируют [npc.herHim] как образец всего, что есть ангельского и доброго в этом мире.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_PURE_VIRGIN_NO_HYMEN = new AbstractStatusEffect(80,
			"'Pure' Virgin",
			"virginPureNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 10f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] как будто [npc.sheIs] благородная и добродетельная сущность, но [npc.sheIs] также подозрительно увлечена спорами о том, что порванная девственная плева не дисквалифицирует человека из категории чистых дев.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_PURE_VIRGIN_ONLY_HYMEN = new AbstractStatusEffect(80,
			"Pure 'Virgin'",
			"virginPureRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] как будто [npc.sheIs] благородная и добродетельная сущность, но [npc.sheIs] также подозрительно увлечена спорами о том, что наличие неповреждённой девственной плевы достаточно, чтобы технически считать кого угодно девственницей...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_BROKEN_VIRGIN = new AbstractStatusEffect(80,
			"Broken Virgin",
			"virginBroken",
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Потеря [npc.her] драгоценной девственности сильно ударила по [npc.name], и [npc.she] теперь [npc.verb(see)] [npc.herself] как ничто иное, кроме грязной шлюхи."
						+ " [npc.She] теперь часто [npc.verb(fantasise)] о больших, толстых членах, глубоко вставляемых в [npc.her] бесполезную киску, прежде чем осквернять [npc.her] матку своей грязной спермой...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN = new AbstractStatusEffect(80,
			"Lusty Maiden",
			"virginLustyMaidenPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIs] более чем готова использовать [npc.her] жопу, рот, грудь и даже обещание [npc.her] киски, чтобы угодить [npc.her] партнёрам,"
							+ " но [npc.she] никогда не позволит кому-либо действительно проникнуть в [npc.her] женскую сексуальность и лишить [npc.herHim] [npc.her] драгоценной девственности!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_NO_HYMEN = new AbstractStatusEffect(80,
			"Lusty 'Maiden'",
			"virginLustyMaidenNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] как будто [npc.her] pussy совершенно нетронута и недоступна,"
							+ " но [npc.sheIs] также подозрительно увлечен спорами о том, что порванная девственная плева не лишает человека статуса чистой девственницы...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_ONLY_HYMEN = new AbstractStatusEffect(80,
			"Lusty 'Maiden'",
			"virginLustyMaidenRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] как будто [npc.her] pussy совершенно нетронута и недоступна,"
							+ " но [npc.sheIs] также подозрительно увлечен спорами о том, что наличие неповрежденной девственной плевы достаточно, чтобы технически считать любого девственницей...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_BROKEN = new AbstractStatusEffect(80,
			"Broken Maiden",
			"virginLustyMaidenBroken",
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -25f),
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Потеря [npc.her] драгоценной девственности сильно ударила по [npc.name], и [npc.she] теперь [npc.verb(see)] [npc.herself] как ничто иное, кроме грязной шлюхи."
						+ " Все усилия [npc.her] по сохранению [npc.her] pussy в чистоте, используя [npc.her] другие активы, были напрасны, и теперь все, о чем [npc.she] фантазирует, это использование [npc.her] бесполезной шлюхи в качестве общественного места для спермы...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	
	// JOB/OCCUPATION EFFECTS:
	
	public static AbstractStatusEffect COMBAT_JOB_SOLDIER = new AbstractStatusEffect(10,
			"Controlled Aggression",
			"res/perks/jobs/soldier",
			PresetColour.BASE_GREEN,
			true,
			null,
			Util.newArrayListOfValues("Ваш урон [style.boldExcellent(doubled)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Благодаря военной подготовке [npc.her], [npc.name] [npc.is] смог нанести удар с крайней агрессией в начале боя.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	
	// CLOTHING SETS:
	
	public static AbstractStatusEffect SET_MAID = new AbstractStatusEffect(70,
			"Трудолюбивая горничная",
			"clothingSets/maid",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Надев весь костюм горничной, [npc.nameIsFUll] наполнилась энергией, в которой [npc.she] [npc.verb(need)] для того, чтобы быть сексуальной трудолюбивой горничной.");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_maid").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MAID_BOOSTED = new AbstractStatusEffect(70,
			"Professional Maid",
			"clothingSets/maid_boosted",
			PresetColour.CLOTHING_BLACK,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Надевая всю одежду служанки, [npc.nameIsFull] вспомнил о настоящей профессии [npc.her]; о том, что она была исключительно талантливой (и сексуальной) служанкой!");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_maid").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MILK_MAID = new AbstractStatusEffect(70,
			"Milk Maid",
			"clothingSets/milk_maid",
			PresetColour.BASE_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Milk Maid's outfit, [npc.nameIsFull] filled with the energy [npc.she] [npc.verb(need)] in order to perform all of [npc.her] milk maid's duties.");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_milk_maid").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MILK_MAID_BOOSTED = new AbstractStatusEffect(70,
			"Профессиональная молочная служанка",
			"clothingSets/milk_maid_boosted",
			PresetColour.BASE_WHITE,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Надевая всю одежду молочной служанки, [npc.nameIsFull] вспомнил о настоящей профессии [npc.her]; о том, что она была исключительно талантливой (и сексуальной) молочной служанкой.");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_milk_maid").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_BUTLER = new AbstractStatusEffect(70,
			"Butler",
			"clothingSets/butler",
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Butler's outfit, [npc.nameIsFull] filled with the energy [npc.she] [npc.verb(need)] in order to carry out [npc.her] duties as a butler.");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_butler").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_BUTLER, true);
		}
	};
	
	public static AbstractStatusEffect SET_BUTLER_BOOSTED = new AbstractStatusEffect(70,
			"Professional Butler",
			"clothingSets/butler_boosted",
			PresetColour.CLOTHING_WHITE,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Надевая всю одежду дворецкого, [npc.nameIsFull] вспомнил о настоящей профессии [npc.her]; о том, что он был исключительно талантливым дворцом!");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_butler").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_BUTLER, true);
		}
	};
	
	public static AbstractStatusEffect SET_WITCH = new AbstractStatusEffect(70,
			"Arcane Witch",
			"clothingSets/witch",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "Надевая полный комплект одежды ведьмы, вы чувствуете, как ваша аркана становится сильнее.";
					
				} else {
					return UtilText.parse(target, "Надевая полный комплект одежды ведьмы, [npc.namePos] аркана стала сильнее.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_witch").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SCIENTIST = new AbstractStatusEffect(70,
			"Scientist",
			"clothingSets/scientist",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.RESISTANCE_FIRE, 2f),
					new Value<>(Attribute.RESISTANCE_POISON, 2f),
					new Value<>(Attribute.RESISTANCE_ICE, 2f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "Надевая как халат, так и защитные очки, вы уверены, что никакое химическое пятно не повредит вам!";
					
				} else {
					return UtilText.parse(target, "Надевая как халат, так и защитные очки, [npc.name] хорошо защищен от любых химических пятен.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_scientist").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SLUTTY_ENFORCER = new AbstractStatusEffect(70,
			"Slutty Enforcer",
			"clothingSets/slutty_enforcer",
			PresetColour.BASE_PINK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] в сексуальном костюме, напоминающем форму Энфорсера, заставляя [npc.herHim] чувствовать себя очень сексуально.");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_slutty_enforcer").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_RAINBOW = new AbstractStatusEffect(70,
			"Double Rainbow",
			"clothingSets/rainbow",
			PresetColour.CLOTHING_RED,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "Двойная радуга... Что это значит?!";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_rainbow").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_DARK_SIREN = new AbstractStatusEffect(70,
			"Dark Siren",
			"clothingSets/dark_siren",
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_BLACK_STEEL,
			PresetColour.CLOTHING_RED_DARK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "<i>Тьма и хаос, пусть границы вечной пустоты будут разрушены! Нематериальное проявление божественной воли, теперь пусть моя запечатанная сила будет развязана!</i>";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_dark_siren").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_LYSSIETH_GUARD = new AbstractStatusEffect(70,
			"Lyssieth's Guard",
			"clothingSets/lyssieth_guard",
			PresetColour.CLOTHING_OLIVE,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_OLIVE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {//British Auxiliary Territorial Service
			return UtilText.parse(target, "[npc.name] [npc.sheIs] связана в бондаже и [npc.her] абсолютно наслаждается тем, что @@T3@@ @@T4@@ с трудом двигается.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_lyssieth_guard").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_BDSM = new AbstractStatusEffect(70,
			"Bound Bitch",
			"clothingSets/bdsm",
			PresetColour.CLOTHING_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -15f)),
			null) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(target!=null && target.hasFetish(Fetish.FETISH_BONDAGE_VICTIM)) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
						new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
						new Value<>(Attribute.DAMAGE_LUST, 10f));
			}
			return super.getAttributeModifiers(target);
		}
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasFetish(Fetish.FETISH_BONDAGE_VICTIM)) {
					return UtilText.parse(target, "Контекст: Java UI/Dialogue src/com/lilithsthrone/game/character/effects/StatusEffect.java\nТекст:\n[npc.Name] [npc.has] была связана в бондаже и [npc.is] безумно наслаждалась тем, что [npc.she] [npc.is] с трудом двигалась.");
				} else {
					return UtilText.parse(target, "[npc.Name] [npc.has] связана в бондаже и [npc.is] с трудом двигается.");
				}
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_bdsm").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_CATTLE = new AbstractStatusEffect(70,
			"Cattle",
			"clothingSets/cattle",
			PresetColour.BASE_TAN,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "Вы носите набор украшений, обычно найденных на коровах или быках.";
					
				} else {
					return UtilText.parse(target, "[npc.NameIsFull] носит набор украшений, обычно найденных на коровах или быках.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_cattle").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SNOWFLAKE = new AbstractStatusEffect(70,
			"Glacial",
			"clothingSets/snowflake",
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_ICE, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "Надев полный набор специально зачарованных снежинок, как [npc.namePos] аркана, так и ледяные атаки стали намного более мощными!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_snowflake").isCharacterWearingCompleteSet(target);
		}
	};
		
	public static AbstractStatusEffect SET_SUN = new AbstractStatusEffect(70,
			"Radiant",
			"clothingSets/sun",
			PresetColour.CLOTHING_COPPER,
			PresetColour.CLOTHING_YELLOW,
			PresetColour.CLOTHING_ORANGE,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_FIRE, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "Надев украшения, которые сверкают как солнце, [npc.nameIsFull] уверен, что [npc.her] заклинания и огненные атаки сожгут [npc.her] врагов!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_sun").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_GEISHA = new AbstractStatusEffect(70,
			"Geisha",
			"clothingSets/geisha",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIs] носит церемониальное платье юко, которое очень похоже на традиционную японскую одежду.");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_geisha").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_RONIN = new AbstractStatusEffect(70,
			"Ronin",
			"clothingSets/ronin",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<i>Ты понимаешь, что есть что-то вне тебя, что нужно обслуживать. И когда эта потребность исчезнет, когда вера умрет, что ты? [pc.A_man] без хозяина.</i>";
					
				} else {
					return UtilText.parse(target,
							"<i>[npc.Name] понимает, что есть что-то вне [npc.herself], что нужно обслуживать. И когда эта потребность исчезнет, когда вера умрет, что это? [npc.she]? [npc.A_man] без хозяина.</i>");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_ronin").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_DAISHO = new AbstractStatusEffect(70,
			"Daisho",
			"clothingSets/daisho",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Японский термин 'Даисё', означающий 'большой-маленький', используется для описания одновременного ношения катаны (большой) и вакидзаси (маленький)."
						+ "Ношение этих двух мечей одновременно обозначает, что владелец является членом самурайского класса.");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_daisho").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_JOLNIR = new AbstractStatusEffect(70,
			"J&oacute;lnir",
			"clothingSets/jolnir",
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 15f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "Нося платье 'Юле', как 'Юле', твоя мудрость и умение в бою значительно увеличиваются!";
					
				} else {
					return UtilText.parse(target, "[npc.NameIsFull] носит платье 'Юле', тем самым значительно увеличивая [npc.her] мудрость и умение в бою.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_jolnir").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_KITTY = new AbstractStatusEffect(70,
			"Playful Kitty",
			"clothingSets/kitty",
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Нося полный комплект кошачьей нижнего белья, [npc.name] [npc.verb(find)] [npc.herself] хочет подразнить всех [npc.she] [npc.verb(meet)]!");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_kitty").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_STURDY_STEED = new AbstractStatusEffect(70,
			"Все в порядке",
			"clothingSets/sturdy_steed",
			PresetColour.CLOTHING_STEEL,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Нося полный комплект tack, [npc.name] [npc.verb(find)] [npc.herself] чувствует себя сильнее, чем обычно, и [npc.is] наполнена энергией!");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_sturdy_steed").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect CLOTHING_EFFECT = new AbstractStatusEffect(70,
			"clothing effects",
			"combatHidden",
			PresetColour.TRANSFORMATION_GENERIC,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "-";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "-";
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect POTION_EFFECTS = new AbstractStatusEffect(80,
			"potion effects",
			"potionEffects",
			PresetColour.GENERIC_ARCANE,
			PresetColour.BASE_PINK_LIGHT,
			null,
			false,
			null,
			Util.newArrayListOfValues("[style.italicsMinorGood(Максимальные значения - 25)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasTrait(Perk.JOB_CHEF, true)) {
				return UtilText.parse(target, "Thanks to [npc.namePos] background as a chef, [npc.sheIs] able to harness the full potential of the many arcane-infused consumables found throughout this world...");
			}
			return UtilText.parse(target, "Аркана-инфузированные продукты очень распространены в этом мире, и, как [npc.nameHasFull] обнаружил, они могут иметь довольно любопытные эффекты...");
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return target.getPotionAttributes();
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modifiersList = new ArrayList<>();

			modifiersList.addAll(getExtraEffects(target));
			
			if (getAttributeModifiers(target) != null) {
				for (Entry<AbstractAttribute, Float> e : getAttributeModifiers(target).entrySet()) {
					modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>" + " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
				}
			}
					
			return modifiersList;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(target.hasTrait(Perk.JOB_CHEF, true)) {
				return Util.newArrayListOfValues("[style.italicsGood(Максимальные значения - 50)]");
			}
			return super.getExtraEffects(target);
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearPotionAttributes();
			return "";
		}
	};
	
	public static AbstractStatusEffect HAPPINESS = new AbstractStatusEffect(70,
			"happiness",
			"happinessFox",
			PresetColour.CLOTHING_SILVER,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f)),
			Util.newArrayListOfValues("[style.italicsGood(Happiness!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Серебристо-меховая лисица, <i>Счастье</i>, следует за тобой, куда бы ты ни пошел."
					+ "Когда ты чувствуешь себя уставшим или подавленным, милая маленькая зверушка трется о твою [pc.legs] и сидит неподвижно, чтобы ее уши почесали, что мгновенно наполняет тебя счастьем.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness);
		}
	};
	

	// COMBAT EFFECTS:

	public static AbstractStatusEffect SPECIAL_SILENCE_TRANCE = new AbstractStatusEffect(70,
			"Silence's Trance",
			"glowingEyes",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, 1f),
					new Value<>(Attribute.MANA_MAXIMUM, 100f),
					new Value<>(Attribute.DAMAGE_SPELLS, 50f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "После того, как она стала свидетелем поражения своего любимого друга, Тень, Тишина призвала воздушного элемента и вошла в глубокий транс, что дало ей улучшенные боевые способности!";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInCombat()
					&& target.equals(Main.game.getNpc(Silence.class))
					&& !Main.combat.getEnemies(Main.game.getPlayer()).contains(Main.game.getNpc(Shadow.class));
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SPECIAL_SHADOW_BESERK = new AbstractStatusEffect(70,
			"Shadow's Rage",
			"glowingEyes",
			PresetColour.BASE_CRIMSON,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, 3f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 150f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 50f),
					new Value<>(Attribute.ENERGY_SHIELDING, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "После того, как она стала свидетелем поражения своего любимого друга, Тишина, Тень вошла в состояние безумной ярости, что дало ей значительно улучшенные боевые способности!";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInCombat()
					&& target.equals(Main.game.getNpc(Shadow.class))
					&& Main.combat.getEnemies(Main.game.getPlayer()).size()==1;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SPECIAL_AMERICAN_FREEDOM = new AbstractStatusEffect(70,
			"Ослепленный Свободой",
			"american_freedom",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] борется с твоей явной судьбой!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isStarted()
					&& Main.game.getPlayer().getOccupation()==Occupation.TOURIST
					&& !target.isPlayer()
					&& Main.game.isInCombat()
					&& Main.combat.getEnemies(Main.game.getPlayer()).contains(target);
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect COMBAT_HIDDEN = new AbstractStatusEffect(70,
			"hidden",
			"combatHidden",
			PresetColour.GENERIC_BAD,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Вы не знаете, какие перки, эффекты статуса, заклинания или специальные атаки доступны вашему противнику. Вам нужен перк, чтобы раскрыть такую информацию."+Perk.OBSERVANT.getName(target)+"отчаянно нуждаетесь в сексе";
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DESPERATE_FOR_SEX = new AbstractStatusEffect(70,
			"[style.colourTerrible(Входящий урон игнорирует все щиты)]",
			"desperateForSex",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			PresetColour.ATTRIBUTE_LUST,
			false,
			null,
			Util.newArrayListOfValues(
					"Входящий [style.colourLust(Урон Похоти)] преобразуется в:",
					"урон)] и [style.colourMana(1*",
					"[style.colourHealth(2*"+Attribute.HEALTH_MAXIMUM.getName()+"Вы абсолютно отчаянно нуждаетесь в сексе, но благодаря силе вашей арканой ауры, вы можете сопротивляться и не сдаваться прямо здесь и сейчас!"+Attribute.MANA_MAXIMUM.getName()+" damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "@@T0@@ абсолютно отчаянно нуждаетесь в сексе, но благодаря силе @@T1@@ арканой ауры, @@T2@@ можете сопротивляться и не сдаваться прямо здесь и сейчас!";
			} else {
				return UtilText.parse(target,
						"[npc.NameIsFull] отчаянно хочет секса, но благодаря силе [npc.her] аркана, [npc.sheIs] может устоять и не сдаться прямо здесь и сейчас!");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getLust()>=100 && !target.isVulnerableToLustLoss();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	// From spells or combat moves (still in combat):
	
	public static AbstractStatusEffect ARCANE_WEAKNESS = new AbstractStatusEffect(10,
			"arcane weakness",
			"negativeCombatEffect",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f),
					new Value<>(Attribute.RESISTANCE_LUST, -2f),
					new Value<>(Attribute.RESISTANCE_FIRE, -2f),
					new Value<>(Attribute.RESISTANCE_ICE, -2f),
					new Value<>(Attribute.RESISTANCE_POISON, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NamePos] голова кружится и [npc.sheIs] с трудом держится на ногах. @@T2@@ испытывает невероятные трудности с нанесением ударов или уклонением от любых входящих атак.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DAZED = new AbstractStatusEffect(10,
			"dazed",
			"dazed",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "[npc.NamePos] голова кружится, и [npc.sheIs] с трудом удерживается на ногах. [npc.SheIs] испытывает невероятные трудности с нанесением ударов или уклоном от атак.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CRIPPLE = new AbstractStatusEffect(10,
			"crippled",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameHasFull] временно парализована, и [npc.sheIs] с трудом наносит столько же урона с помощью [npc.her] атак, сколько [npc.sheIs] обычно может.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect VULNERABLE = new AbstractStatusEffect(10,
			"vulnerable",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] чувствует себя особенно уязвимой, и [npc.she] [npc.is]н't в состоянии защитить [npc.herself] в меру своих [npc.her] способностей.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED = new AbstractStatusEffect(10,
			"poisoned",
			"combat_poisoned",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			null,
			Util.newArrayListOfValues("<b>15</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Яд в теле [npc.namePos] оказывает свое действие, и [npc.sheIs] постепенно теряет [npc.her] силу!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED_LUST = new AbstractStatusEffect(10,
			"lust-poisoned",
			"combat_poisoned",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues(
					"<b>5</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 5);
			Value<String, Integer> lustDamageValue = DamageType.LUST.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey()
					 + UtilText.parse(target, "<br/>[npc.Name] дополнительно [npc.verb(take)] <b>" + lustDamageValue.getValue() + "</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+"!")+lustDamageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Яд похоти в теле [npc.namePos] оказывает свое действие, и [npc.sheIs] не только постепенно теряет [npc.her] силу, но и становится неконтролируемо возбужденной в процессе!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect INKY_ATTACK = new AbstractStatusEffect(10,
			"Inky's lover",
			"inky_summon",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues(
					"<b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> lustDamageValue = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "<br/>[npc.Name] [npc.verb(take)] <b>" + lustDamageValue.getValue() + "</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+"!")+lustDamageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Инки касается тела [npc.namePos] своими щупальцами и, похоже, наслаждается каждым моментом!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect WITCH_SEAL = new AbstractStatusEffect(10,
			"Witch's Seal",
			"combat_witch_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -3f)),
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно попытаться сбежать!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Мощная аркана печать крепко удерживает [npc.name] на месте, не позволяя [npc.herHim] совершать какие-либо действия!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};
	
	public static AbstractStatusEffect WITCH_CHARM = new AbstractStatusEffect(10,
			"Bewitching Charm",
			"combat_witch_charm",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if (target.isPlayer()) {
				return "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Очаровывающий шарм</b> делает вас неотразимо привлекательным!";
				
			} else {
				return UtilText.parse(target,
						"<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Очаровывающий шарм</b> делает [npc.name] неотразимо привлекательным!");
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				if(target.isFeminine()) {
					return "Аркана заклятие очаровывает любого, кто смотрит на вас, заставляя его видеть вас как самого красивого человека, которого он когда-либо видел.";
				} else {
					return "Аркана заклятие очаровывает любого, кто смотрит на вас, заставляя его видеть вас как самого красивого человека, которого он когда-либо видел.";
				}
				
			} else {
				if(target.isFeminine()) {
					return UtilText.parse(target, "Аркана заклятие очаровывает вас, заставляя видеть [npc.name] как самого красивого человека, которого вы когда-либо видели.");
				} else {
					return UtilText.parse(target, "Аркана заклятие очаровывает вас, заставляя видеть [npc.name] как самого красивого человека, которого вы когда-либо видели.");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ROPE_BOUND_SEX = new AbstractStatusEffect(10,
			"Привязанный веревкой",
			"immobilised_rope",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Не может двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Сильные веревки, связанные вокруг тела [npc.namePos], крепко держат [npc.herHim] на месте, не позволяя [npc.herHim] совершать какие-либо действия!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.ROPE);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect CHAINS_BOUND_SEX = new AbstractStatusEffect(10,
			"Прикован к столбу",
			"immobilised_chains",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Не может двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Сильные цепи, связанные вокруг тела [npc.namePos], крепко держат [npc.herHim] на месте, не позволяя [npc.herHim] совершать какие-либо действия!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.CHAINS);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect STOCKS_BOUND_SEX = new AbstractStatusEffect(10,
			"Заключен в stocks",
			"immobilised_stocks",
			PresetColour.CLOTHING_DESATURATED_BROWN,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Не может двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] был прикован к stocks, и поэтому [npc.sheIs] не может совершать какие-либо действия!");
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
//			if(Main.game.isInSex()) {
//				Main.sex.addCharacterImmobilised(ImmobilisationType.STOCKS, Main.sex.getDominantParticipants(false).keySet().iterator().next(), target);
//			}
			return "";
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			if(Main.game.isInSex()) {
				Main.sex.removeCharacterImmobilised(target, ImmobilisationType.STOCKS);
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.STOCKS)
					&& Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.LOCKED_IN_STOCKS);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect WEBBED_1 = new AbstractStatusEffect(10,
			"Webbed",
			"restrain_webbed_1",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Густая, липкая паутина прилипает к телу [npc.namePos], что несколько затрудняет [npc.her] движения!"
						+ "<br/>[style.italicsMinorBad(Если [npc.she] [npc.verb(get)] паутиной снова, этот эффект станет более серьезным!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect WEBBED_2 = new AbstractStatusEffect(10,
			"Seriously webbed",
			"restrain_webbed_2",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Большое количество густой, липкой паутины прилипает к телу [npc.namePos], что значительно затрудняет движения [npc.her]!"
						+ "<br/>[style.italicsBad(Если [npc.she] [npc.verb(get)] паутиной снова, этот эффект станет крайне серьезным!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect WEBBED_3 = new AbstractStatusEffect(10,
			"Cocooned",
			"restrain_webbed_3",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя пытаться сбежать!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Огромное количество густой, липкой паутины прилипает к телу [npc.namePos], что делает практически невозможным для [npc.herHim] двигать [npc.her] [npc.arms] и [npc.legs]!"
						+ "<br/>[style.italicsBad(Если [npc.she] [npc.verb(get)] паутиной снова, [npc.she] будет мгновенно побежден!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};

	public static AbstractStatusEffect WEBBED_SEX = new AbstractStatusEffect(10,
			"Cocooned",
			"immobilised_cocoon",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Огромное количество густой, липкой паутины прилипает к телу [npc.namePos], что делает практически невозможным для [npc.herHim] двигаться!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.COCOON);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect WITCH_SEAL_SEX = new AbstractStatusEffect(10,
			"Witch's Seal",
			"immobilised_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Мощная аркана печать крепко удерживает [npc.name] на месте, не позволяя [npc.herHim] предпринимать какие-либо действия!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.WITCH_SEAL);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_1 = new AbstractStatusEffect(10,
			"Tentacle-grabbed",
			"restrain_tentacles_1",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Ковры сильных щупалец хватают за тело [npc.namePos], что несколько затрудняет движения [npc.her]!"
						+ "<br/>[style.italicsMinorBad(Если [npc.she] [npc.verb(get)] tentacle-grabbed снова, этот эффект станет более серьезным!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_2 = new AbstractStatusEffect(10,
			"Tentacle-embraced",
			"restrain_tentacles_2",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Сильные щупальца крепко обвились вокруг [npc.namePos] [npc.arms] и [npc.legs], что значительно затрудняет движения [npc.her]!"
						+ "<br/>[style.italicsBad(Если [npc.she] [npc.verb(get)] tentacle-grabbed снова, этот эффект станет крайне серьезным!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_3 = new AbstractStatusEffect(10,
			"Tentacle-constricted",
			"restrain_tentacles_3",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя попытаться сбежать!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Сильные щупальца крепко сжали [npc.namePos] в тисках, что делает практически невозможным для [npc.herHim] двигать [npc.her] [npc.arms] и [npc.legs]!"
						+ "<br/>[style.italicsBad(Если [npc.she] [npc.verb(get)] tentacle-grabbed снова, [npc.she] будет мгновенно побежден!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRICTION_SEX = new AbstractStatusEffect(10,
			"Tentacle-bound",
			"immobilised_tentacles",
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.TENTACLE_RESTRICTION);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameIsFull] использует четырех [npc2.her] [npc2.tentacles], чтобы удерживать [npc.name] вниз и крепко предотвратить движение [npc.herHim]!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.TENTACLE_RESTRICTION);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_1 = new AbstractStatusEffect(10,
			"Tail-grabbed",
			"restrain_tail_1",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Сильный, змееподобный хвост обвивается вокруг тела [npc.namePos], что затрудняет движения [npc.her]!"
						+ "<br/>[style.italicsMinorBad(Если [npc.she] [npc.verb(get)] tail-constricted снова, этот эффект станет более серьезным!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_2 = new AbstractStatusEffect(10,
			"Tail-embraced",
			"restrain_tail_2",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Сильный, змееподобный хвост крепко обвился вокруг [npc.namePos] [npc.arms] и [npc.legs], что значительно затрудняет [npc.her] движения!"
						+ "<br/>[style.italicsBad(Если [npc.she] [npc.verb(get)] хвост снова сжат, этот эффект станет крайне серьезным!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_3 = new AbstractStatusEffect(10,
			"Tail-constricted",
			"restrain_tail_3",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя пытаться сбежать!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Сильный, змееподобный хвост сжал [npc.namePos] в тисках, делая практически невозможным для [npc.herHim] двигать [npc.her] [npc.arms] и [npc.legs]!"
						+ "<br/>[style.italicsBad(Если [npc.she] [npc.verb(get)] хвост снова сжат, [npc.she] будет мгновенно побежден!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};
	
	public static AbstractStatusEffect TAIL_CONSTRICTION_SEX = new AbstractStatusEffect(10,
			"Constricted",
			"immobilised_tail",
			PresetColour.BASE_GREEN_DARK,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.TAIL_CONSTRICTION);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameIsFull] использует [npc2.her] длинный, сильный хвост, чтобы сжать [npc.name] и твердо предотвратить [npc.herHim] от движения!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.TAIL_CONSTRICTION);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect COMMAND_IMMOBILE_SEX = new AbstractStatusEffect(10,
			"Immobile",
			"immobilised_command",
			PresetColour.BASE_WHITE,
			PresetColour.BASE_RED,
			PresetColour.BASE_WHITE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Нельзя двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.COMMAND);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameHasFull] приказал [npc.name] оставаться совершенно неподвижным, и как таковой [npc.sheIsFull] полностью неподвижен!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.COMMAND);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SLEEP_SEX = new AbstractStatusEffect(10,
			"Asleep",
			"immobilised_sleep",
			PresetColour.SLEEP_HEAVY,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(Нельзя двигаться!)]",
					"Пробудится [style.colourBad(wake)], если партнер не в нежном темпе или если рот проникнут")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] глубоко спит, и пока секс остается нежным, [npc.she] не проснется!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			return Main.sex.getImmobilisationTypes(target).containsKey(ImmobilisationType.SLEEP);
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BANEFUL_FISSURE = new AbstractStatusEffect(10,
			"Fissure's Fumes",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue()+ "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Трещина продолжает выпускать ядовитые пары, заставляя [npc.name] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)] в.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.DARK_SIREN_SIRENS_CALL.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FIRE_MANA_BURN = new AbstractStatusEffect(10,
			"Aura Burn",
			"melee_fire",
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("[style.boldFire(Fire Spells)] можно использовать за счет "+Attribute.HEALTH_MAXIMUM.getName()+" когда нет ауры",
					"[style.boldGood(Не уменьшает "+Attribute.HEALTH_MAXIMUM.getName()+" below 1)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Благодаря [npc.her] аффинитету со школой Огня, [npc.nameIsFull] может жертвовать частью [npc.her] "+Attribute.HEALTH_MAXIMUM.getName()+" для того, чтобы использовать огненные заклинания, когда [npc.sheIs] не имеет ауры.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE);
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect LINGERING_FLAMES = new AbstractStatusEffect(10,
			"Lingering Flames",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("<b>5</b> [style.boldFire(Fire Damage)] за ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.FIRE.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldFire(fire damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Невидимые арканальные огни лижут твою стопу, и хотя они не причиняют тебе никакой реальной боли, ты все равно прыгаешь вокруг в дискомфорте.";
			} else {
				return UtilText.parse(target,
						"Невидимые арканальные огни лижут [npc.namePos] стопу, и хотя они не причиняют [npc.herHim] никакой реальной боли, [npc.she] все равно прыгает вокруг в дискомфорте.");
			}
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.FIREBALL_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FLASH = new AbstractStatusEffect(10,
			"Blinded",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Ослепительная вспышка света оставила [npc.name] временно ослепленным, и [npc.sheIsFull] борется за восстановление контроля над [npc.her] чувствами!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.FLASH.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FLASH_1 = new AbstractStatusEffect(10,
			"Ослепление (Вторичные Искры)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"После начальной, ослепительной вспышки света, серия вторичных искр продолжает ослеплять [npc.name], что делает невероятно трудным для [npc.herHim] выполнение любых действий!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.FLASH_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect CLOAK_OF_FLAMES = new AbstractStatusEffect(10,
			"Плащ Огня",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] был окутан плащом арканальных огней, который увеличивает [npc.her] сопротивление огню и холоду!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_1 = new AbstractStatusEffect(10,
			"Плащ Огня (Поджигатель)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("Невооруженные атаки наносят +1 урон за уровень заклинателя",
					"Невооруженные атаки наносят [style.boldFire(Fire Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] был окутан пламенем аркана, что не только увеличивает [npc.her] сопротивление, но и наделяет [npc.her] невооруженные атаки огненным уроном!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_2 = new AbstractStatusEffect(10,
			"Плащ Огня (Инферно)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("Невооруженные атаки наносят +1 урон за уровень заклинателя",
					"Невооруженные атаки наносят [style.boldFire(Fire Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"[npc.NameHasFull] был окутан пламенем аркана, что не только увеличивает [npc.her] сопротивление и огненный урон, но и наделяет [npc.her] невооруженные атаки огненным уроном!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_3 = new AbstractStatusEffect(10,
			"Плащ Огня (Кольцо Огня)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f),
					new Value<>(Attribute.RESISTANCE_ICE, 20f)),
			Util.newArrayListOfValues("Невооруженные атаки наносят +1 урон за уровень заклинателя",
					"Невооруженные атаки наносят [style.boldFire(Fire Damage)]",
					"Нападающие получают <b>5</b> [style.colourFire(Fire Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] окутана пламенем, что не только увеличивает [npc.her] сопротивление и урон от огня, но и наделяет [npc.her] рукопашные атаки огненным уроном!"
						+ "Любой враг, который осмеливается атаковать [npc.herHim] в ближнем бою, получает ответный огненный урон!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_WILDFIRE = new AbstractStatusEffect(10,
			"Wildfire",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Огненный элементаль в команде [npc.namePos] наделяет [npc.herHim] знаниями о том, как максимально эффективно использовать огненные атаки!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.FIRE) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_BURNING_DESIRE = new AbstractStatusEffect(10,
			"Burning Desire",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Огненный элементаль в команде врага наполняет [npc.name] жгучим желанием к сексу!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_2)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.FIRE) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_SERVANT_OF_FIRE = new AbstractStatusEffect(10,
			"Слуга Огня",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
					"Having sworn [npc.her] subservience to the school of Fire, [npc.namePos] Fire elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_SERVANT_OF_FIRE_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] отсасывает столько энергии [npc2.namePos], сколько хочет [npc.she], что позволяет [npc.herHim] наносить огромный урон!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_BINDING_OF_FIRE = new AbstractStatusEffect(10,
			"Привязка Огня",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Огня к воле [npc.her], [npc.namePos], Огненный элемент, [npc2.name], вынужден раскрыть все секреты [npc2.her] перед [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect FREEZING_FOG = new AbstractStatusEffect(10,
			"Freezing Fog",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Остывающий туман, оставшийся после удара заклинания 'Осколок Льда', витает вокруг [npc.name]. Аркана холод проникает в тело [npc.her], замедляя [npc.her] движения и туманируя [npc.her] разум.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FROZEN = new AbstractStatusEffect(10,
			"Frozen",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Остывающий туман, витающий вокруг [npc.name], взорвался, покрыв [npc.herHim] тонким слоем льда и замедляя [npc.her] движения!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect RAIN_CLOUD = new AbstractStatusEffect(10,
			"Rain Cloud",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] висит маленький, пропитанный арканой туман. Холодный дождь лишает [npc.her] способности эффективно использовать заклинания.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.RAIN_CLOUD.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DEEP_CHILL = new AbstractStatusEffect(10,
			"Туман (Глубокий Холод)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] висит маленький, пропитанный арканой туман. Замерзший дождь лишает [npc.her] способности эффективно использовать заклинания, а также снижает [npc.her] сопротивление холоду.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DOWNPOUR = new AbstractStatusEffect(10,
			"Туман (Сильный Дождь)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Над головой [npc.namePos] висит пропитанный арканой туман."
							+ "Замерзший дождь лишает [npc.her] способности эффективно использовать заклинания, а также снижает [npc.her] сопротивление холоду, и затрудняет защиту [npc.herself].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST = new AbstractStatusEffect(10,
			"Дождь (Ливень)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Над головой [npc.namePos] висит облако, пропитанное арканой силой."
							+ "Ледяной дождь лишает [npc.her] способности эффективно использовать заклинания, а также снижает сопротивление [npc.her] холоду, что затрудняет защиту [npc.herself].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect RAIN_CLOUD_CLOUDBURST = new AbstractStatusEffect(10,
			"Облако (Гроза)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -50f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Над головой [npc.namePos] висит облако, пропитанное арканой силой."
							+ "Проливной, ледяной дождь лишает [npc.her] способности эффективно использовать заклинания, а также снижает сопротивление [npc.her] холоду, что затрудняет защиту [npc.herself].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_CRASHING_WAVES = new AbstractStatusEffect(10,
			"Crashing Waves",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Существо воды в группе [npc.namePos] передает [npc.herHim] знания о том, как максимально эффективно использовать атаки льдом!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.WATER) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_CALM_WATERS = new AbstractStatusEffect(10,
			"Calm Waters",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Существо воды в группе [npc.namePos] помогает успокоить желание [npc.her] заниматься сексом.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.WATER) {
					return true;
				}
			}
			
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_SERVANT_OF_WATER = new AbstractStatusEffect(10,
			"Слуга воды",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Water, [npc.namePos] Water elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_SERVANT_OF_WATER_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] отсасывает столько энергии [npc2.namePos], сколько хочет [npc.she], позволяя [npc.herHim] нанести огромный урон!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_BINDING_OF_WATER = new AbstractStatusEffect(10,
			"Привязка к Воде",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 25f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Воды к воле [npc.her], [npc.namePos] Водяной дух, [npc2.name], вынужден раскрыть все секреты [npc2.her] перед [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect POISON_VAPOURS = new AbstractStatusEffect(10,
			"Poison Vapours",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Облако ядовитых паров продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)] в.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.POISON_VAPOURS.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_CHOKING_HAZE = new AbstractStatusEffect(10,
			"Ядовитые Пары (Задушливый Туман)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Облако ядовитых паров продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)] в.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_ARCANE_SICKNESS = new AbstractStatusEffect(10,
			"Яд в воздухе (Аркана)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldУжасный(drained)] в ход</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" и [npc.verb(lose)] <b>" + lustDamage + "</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!")
					+ damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Облако яда продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)] в.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_WEAKENING_CLOUD = new AbstractStatusEffect(10,
			"Яд в воздухе (Ослабляющий туман)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldУжасный(drained)] в ход</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" и [npc.verb(lose)] <b>" + lustDamage + "</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!")
					+ damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Облако яда продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)] в.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect VACUUM = new AbstractStatusEffect(10,
			"Vacuum",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Постоянное существование пустоты в воздухе, которая смещается, чтобы оставаться рядом с телом [npc.namePos], вызывает у [npc.her] потерю равновесия!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.VACUUM.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_SECONDARY_VOIDS = new AbstractStatusEffect(10,
			"Вакуум (Вторичные пустоты)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Постоянное существование пустоты в воздухе, которая смещается, чтобы оставаться рядом с телом [npc.namePos], вызывает у [npc.her] потерю равновесия!"
						+ "Постоянное существование пустоты в воздухе, которая смещается, чтобы оставаться рядом с телом [npc.herHim], вызывает у @@T1@@ потерю равновесия!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_SUCTION = new AbstractStatusEffect(10,
			"Vacuum (Suction)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>10%</b> шанс в ход на [style.boldExcellent(stripping)] одежду")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(!target.isUnique() && (Math.random()<(target.isPlayer()?0.1f:0.166f))) { // I purposefully boost the chance in secret to make the player feel better about the RNG
				List<AbstractClothing> suitableClothing = new ArrayList<>();
				for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
					if(target.isAbleToUnequip(c, false, target)
							&& !c.getSlotEquippedTo().isJewellery()) {
						suitableClothing.add(c);
					}
				}
				if(!suitableClothing.isEmpty()) {
					AbstractClothing clothingBlownOff = suitableClothing.get(Util.random.nextInt(suitableClothing.size()));
					target.unequipClothingOntoFloor(clothingBlownOff, true, target);
					if(target.isPlayer()) {
						return "Your "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+"@@H0@@10%@@H1@@ шанс в ход на [style.boldExcellent(stripping)] одежду";
					} else {
						return "[npc.NamePos] "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+"@@H0@@10%@@H1@@ шанс в ход на [style.boldExcellent(stripping)] одежду";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Постоянное существование пустоты в воздухе, которая смещается, чтобы оставаться рядом с телом [npc.namePos], вызывает у [npc.her] потерю равновесия!"
						+ "Постоянное существование пустоты в воздухе, которая смещается, чтобы оставаться рядом с телом [npc.herHim], вызывает у @@T1@@ потерю равновесия!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_TOTAL_VOID = new AbstractStatusEffect(10,
			"Вакуум (Полная пустота)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>25%</b> шанс в турове на [style.boldExcellent(stripping)] одежду")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(!target.isUnique() && (Math.random()<(target.isPlayer()?0.25f:0.33f))) { // I purposefully boost the chance in secret to make the player feel better about the RNG
				List<AbstractClothing> suitableClothing = new ArrayList<>();
				for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
					if(target.isAbleToUnequip(c, false, target)
							&& !c.getSlotEquippedTo().isJewellery()) {
						suitableClothing.add(c);
					}
				}
				if(!suitableClothing.isEmpty()) {
					AbstractClothing clothingBlownOff = suitableClothing.get(Util.random.nextInt(suitableClothing.size()));
					target.unequipClothingOntoFloor(clothingBlownOff, true, target);
					if(target.isPlayer()) {
						return "Your "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" высосана и выброшена на пол!";
					} else {
						return "[npc.NamePos] "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" высосана и выброшена на пол!";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"Устойчивый, невероятно мощный вакуум смещается, чтобы оставаться рядом с телом [npc.namePos], и вызывает сбой в равновесии [npc.her]!"
						+ "Также постоянно создаются и коллапсируют меньшие вторичные вакуумы вокруг [npc.herHim]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PROTECTIVE_GUSTS = new AbstractStatusEffect(10,
			"Protective Gusts",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Призван благоприятный ветер, чтобы защитить [npc.name] от любых ядовитых паров и помочь отбросить любые атаки, направленные на [npc.herHim].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.PROTECTIVE_GUSTS.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROTECTIVE_GUSTS_GUIDING_WIND = new AbstractStatusEffect(10,
			"Защитные порывы (Направляемый ветер)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Призван благоприятный ветер, чтобы защитить [npc.name] от любых ядовитых паров и помочь отбросить любые атаки, направленные на [npc.herHim]."
							+ "Он также помогает направлять атаки [npc.her].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.PROTECTIVE_GUSTS_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROTECTIVE_GUSTS_FOCUSED_BLAST = new AbstractStatusEffect(10,
			"Защитные порывы (Фокусированный взрыв)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 3f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Добросердечный ветер призван защитить [npc.name] от любых ядовитых испарений и помочь отбросить любые атаки, направленные против [npc.herHim]."
							+ "Он также помогает направлять и усиливать силу атак [npc.her].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.PROTECTIVE_GUSTS_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_WHIRLWIND = new AbstractStatusEffect(10,
			"Whirlwind",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Элементаль Воздуха в команде противника окружает [npc.name] бушующими ветрами!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_1)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.AIR) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_VITALISING_SCENTS = new AbstractStatusEffect(10,
			"Vitalising Scents",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Элементаль Воздуха в команде [npc.namePos] окружает [npc.herHim] жизненно важными ароматами, что дает [npc.herHim] энергию, чтобы уклоняться от атак и наносить мощные удары!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.AIR) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_SERVANT_OF_AIR = new AbstractStatusEffect(10,
			"Слуга Воздуха",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Air, [npc.namePos] Air elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_SERVANT_OF_AIR_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Не-Соблазнительный Ущерб)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] отсасывает столько энергии [npc2.namePos], сколько хочет [npc.she], что позволяет [npc.herHim] наносить огромный урон!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_BINDING_OF_AIR = new AbstractStatusEffect(10,
			"Связь Воздуха",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 25f),
					new Value<>(Attribute.RESISTANCE_POISON, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязывание школы Воздуха к воле [npc.her], [npc.namePos] Воздушный элемент, [npc2.name], вынуждает раскрыть все секреты [npc2.her] [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect SLAM_GROUND_SHAKE = new AbstractStatusEffect(10,
			"Ground Shake",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Земля под ногами [npc.namePos] [npc.feet] дрожит и раскачивается, что затрудняет [npc.herHim] уклонение от ударов.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SLAM_AFTER_SHOCK = new AbstractStatusEffect(10,
			"Землетрясение (Последействие)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Земля под ногами [npc.namePos] [npc.feet] дрожит и раскачивается, что затрудняет [npc.herHim] уклонение от ударов."
						+ " [npc.She] чувствует, что грядет Последействие, но [npc.she] мало что может сделать, чтобы избежать его...");
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER = new AbstractStatusEffect(10,
			"Telekinetic Shower",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] постоянно подвергается обстрелу камнями и другими мелкими предметами.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEKENETIC_SHOWER.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER_PRECISION_STRIKES = new AbstractStatusEffect(10,
			"Телекинетический дождь (Точные удары)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] постоянно подвергается обстрелу из высокоточного оружия, стреляющего камнями и другими мелкими предметами.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER_UNSEEN_FORCE = new AbstractStatusEffect(10,
			"Телекинетический душ (Невидимая сила)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>50</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 50);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] постоянно подвергается обстрелу из высокоточного оружия, стреляющего камнями и другими мелкими предметами. Каждый удар немедленно сопровождается взрывной волной силы.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL = new AbstractStatusEffect(10,
			"Stone Shell",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом возникает твердая каменная стена, значительно улучшающая физическую защиту [npc.herHim].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.STONE_SHELL.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect STONE_SHELL_SHIFTING_SANDS = new AbstractStatusEffect(10,
			"Каменный панцирь (Перемещающиеся пески)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом возникает твердая каменная стена, значительно улучшающая физическую защиту [npc.herHim]."
								+ "Периодически каменная стена внезапно исчезает и превращается в песок, после чего снова появляется в другом месте.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL_HARDENED_CARAPACE = new AbstractStatusEffect(10,
			"Каменный панцирь (Утвержденный панцирь)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом возникает твердая, уплотненная каменная стена, значительно улучшающая физическую защиту [npc.herHim]."
								+ " Время от времени, каменная стена внезапно смещается и превращается в песок, прежде чем снова сформироваться в другом месте.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL_EXPLOSIVE_FINISH = new AbstractStatusEffect(10,
			"Каменный панцирь (Взрывная финальная атака)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("[style.colourExcellent(Все враги)] получают <b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" когда Каменный панцирь заканчивается")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом появляется прочная, затвердевшая каменная стена, значительно улучшающая физическую защиту [npc.herHim]."
								+ " Время от времени, каменная стена внезапно смещается и превращается в песок, прежде чем снова сформироваться в другом месте.");
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			StringBuilder sb = new StringBuilder();
			
			boolean first=true;
			for(GameCharacter combatant : Main.combat.getEnemies(target)) {
				Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(target, combatant, 10);
				sb.append(UtilText.parse(combatant,
						target, (first?"":"<br/>")+"<br/>[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"Как [npc2.namePos] Stone Shell взрывается!")
						+damageValue.getKey());
				first=false;
			}
			
			return sb.toString();
			
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_ROLLING_STONE = new AbstractStatusEffect(10,
			"Rolling Stone",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Земной элементаль в команде [npc.namePos] усиливает атаки [npc.her] волнами силы!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_HARDENING = new AbstractStatusEffect(10,
			"Hardening",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Земной элементаль в команде [npc.namePos] использует телекинетические способности, чтобы окружить [npc.herHim] защитными осколками камня!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_SERVANT_OF_EARTH = new AbstractStatusEffect(10,
			"Слуга Земли",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Максимум "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Earth, [npc.namePos] Earth elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_SERVANT_OF_EARTH_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Не-Соблазнительный Урон)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] отсасывает столько энергии [npc2.namePos], сколько хочет [npc.she], позволяя [npc.herHim] нанести огромный урон!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_BINDING_OF_EARTH = new AbstractStatusEffect(10,
			"Привязка Земли",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязывание школы Земли к воле [npc.her], [npc.namePos] стихии Земли, [npc2.name], вынуждает раскрыть все секреты [npc2.her] перед [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	

	public static AbstractStatusEffect ARCANE_AROUSAL_LUSTFUL_DISTRACTION = new AbstractStatusEffect(10,
			"Lustful Distraction",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Внезапно, [npc.namePos] начинает испытывать сильное сексуальное возбуждение, и его мысли постоянно возвращаются к [npc.herHim]. Это отвлекает [npc.sheIs] от цели, и он не может сосредоточиться на том, что он пытается сделать.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_AROUSAL_DIRTY_PROMISES = new AbstractStatusEffect(10,
			"Похотливая Отвлеченность (Грязные Обещания)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Arousing images keep on pushing their way into [npc.namePos] mind, causing [npc.herHim] to lose focus on what it is [npc.sheIs] trying to hit."
								+ " [npc.She] [npc.verb(hear)] the occasional phantasmal whisper in [npc.her] [npc.ear], promising that [npc.she]'ll have a good time if [npc.she] simply submits.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION = new AbstractStatusEffect(10,
			"Telepathic Communication",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] able to project [npc.her] seductive taunts and [npc.moans+] directly into a person's mind!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPATHIC_COMMUNICATION.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH = new AbstractStatusEffect(10,
			"Projected Touch",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] able to project [npc.her] seductive taunts and [npc.moans+] directly into a person's mind!"
								+ " In addition to this, [npc.she] can deliver phantasmal kisses and gropes to [npc.her] target.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION = new AbstractStatusEffect(10,
			"Power of Suggestion",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			Util.newArrayListOfValues("[style.boldLust(Tease)] [style.boldExcellent(applies)] -25 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")+" to the target for [style.boldGood(2 turns)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] able to project [npc.her] seductive taunts and suggestive phrases directly into a person's mind!"
								+ " In addition to this, [npc.she] can deliver phantasmal kisses and gropes to [npc.her] target.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED = new AbstractStatusEffect(10,
			"Power of Suggestion",
			"telepathic_communication_power_of_suggestion_targeted",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Телепатические стоны и внушения, которые были проецированы в разум [npc.namePos], заставляют [npc.herHim] опустить [npc.her] бдительность!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	
	public static AbstractStatusEffect ARCANE_CLOUD = new AbstractStatusEffect(10,
			"Arcane Cloud",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] висит небольшое облако Аркана. Подвергаясь его воздействию, возбуждающие эффекты Аркана проникают в разум [npc.her].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.ARCANE_CLOUD.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ARCANE_CLOUD_ARCANE_LIGHTNING = new AbstractStatusEffect(10,
			"Облако Аркана (Арканический Молнии)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldПохоть(lust damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] висит небольшое облако, возбуждающее похоть. Время от времени, молния Аркана падает на [npc.herHim], наполняя разум [npc.her] возбуждающими мыслями.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ARCANE_CLOUD_ARCANE_THUNDER = new AbstractStatusEffect(10,
			"Облако Аркана (Арканический Гром)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldПохоть(lust damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] висит небольшое облако, возбуждающее похоть. Время от времени, молния Аркана, сопровождаемая небольшим громом Аркана, наполняет разум [npc.her] возбуждающими мыслями.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_CLOUD_LOCALISED_STORM = new AbstractStatusEffect(10,
			"Облако Аркана (Локализованная Буря)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("[style.boldTerrible(Все члены партии)] получают <b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			StringBuilder sb = new StringBuilder();
			
			List<GameCharacter> affectedCombatants = new ArrayList<>();
			affectedCombatants.add(target);
			affectedCombatants.addAll(Main.combat.getAllies(target));
			
			for(GameCharacter combatant : affectedCombatants) {
				Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, combatant, 15);
				
				sb.append(UtilText.parse(combatant, "<br/>[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(lust damage)]!")+damageValue.getKey());
			}
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Локализованная арканальная буря витает над головой [npc.namePos]. Время от времени, молния аркана, сопровождаемая небольшим громом аркана, наполняет разум [npc.her] возбуждающими мыслями.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_LEWD_ENCOURAGEMENTS = new AbstractStatusEffect(10,
			"Lewd Encouragements",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Аркана elemental в партии [npc.namePos] проецирует непристойные слова поддержки в разум [npc.her]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.ARCANE) {
					return true;
				}
			}
			
			return false;
		}
	};

	public static AbstractStatusEffect ELEMENTAL_ARCANE_CARESSING_TOUCH = new AbstractStatusEffect(10,
			"Caressing Touch",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Аркана elemental в партии противника проецирует фантомные щупальца, чтобы ласкать и трогать тело [npc.namePos]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_2)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.ARCANE) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_SERVANT_OF_ARCANE = new AbstractStatusEffect(10,
			"Слуга Аркана",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Arcane, [npc.namePos] Arcane elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_SERVANT_OF_ARCANE_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] забирает столько [npc2.namePos] энергии, сколько хочет [npc.she], позволяя [npc.herHim] нанести огромный урон!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_BINDING_OF_ARCANE = new AbstractStatusEffect(10,
			"Привязка Аркана",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Аркана к воле [npc.her], [npc.namePos] Арканический элемент, [npc2.name], вынужден раскрыть все секреты [npc2.her] перед [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ARCANE_DUALITY_POSITIVE = new AbstractStatusEffect(10,
			"Арканическая Дуальность (Защита)",
			"cleanse_positive",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f),
					new Value<>(Attribute.RESISTANCE_POISON, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Вокруг [npc.name] образовалась защитная барьер из арканической энергии, защищающая [npc.herHim] от всех типов входящего урона.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_DUALITY_NEGATIVE = new AbstractStatusEffect(10,
			"Арканическая Дуальность (Уязвимость)",
			"cleanse_negative",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.RESISTANCE_FIRE, -5f),
					new Value<>(Attribute.RESISTANCE_ICE, -5f),
					new Value<>(Attribute.RESISTANCE_POISON, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Вокруг [npc.name] образовалась ослабляющая барьер из арканической энергии, делающая [npc.herHim] более уязвимым ко всем типам входящего урона.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPORT = new AbstractStatusEffect(10,
			"Teleport",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] телепортировался за спину врагов [npc.her], делая крайне маловероятным, что они смогут попасть в [npc.herHim]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPORT.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPORT_ARCANE_ARRIVAL = new AbstractStatusEffect(10,
			"Телепортация (Арканический Прибытие",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+"в ход по очереди к случайному врагу")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			GameCharacter randomEnemy = Main.combat.getEnemies(target).get(Util.random.nextInt(Main.combat.getEnemies(target).size()));
			
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, randomEnemy, 15);

			return UtilText.parse(randomEnemy, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(lust damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] телепортировался за [npc.her] врагов, что делает крайне маловероятным, что они смогут попасть в [npc.herHim]! Вспышка похотливой арканой энергии сопровождает прибытие [npc.her]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPORT_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	// SEX EFFECTS:
	
	public static AbstractStatusEffect CONDOM_WORN = new AbstractStatusEffect(80,
			"Wearing a condom",
			"condom",
			PresetColour.CLOTHING_PINK_LIGHT,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return -0.5f;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modifiersList = new ArrayList<>();
			modifiersList.add("-0.5% <b style='color: " + PresetColour.GENERIC_SEX.toWebHexString() + "'>arousal/turn</b>");
			return modifiersList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Поскольку ты носишь презерватив, секс не чувствуется так же хорошо...";
			} else {
				return "Поскольку [npc.sheIs] носит презерватив, секс не чувствуется так же хорошо для [npc.name]...";
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingCondom();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FLOWING_WATER = new AbstractStatusEffect(80,
			"Flowing Water",
			"sexEffects/flowing_water",
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.BASE_AQUA,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourDirty(грязные части тела)] быстро [style.colourAqua(очищаются)]")) {
		public EffectBenefit getBeneficialStatus() {
			return EffectBenefit.NEUTRAL;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Вода, стекающая по телу [npc.namePos], предотвращает [npc.herHim] от загрязнения!");
			}
			return null;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex() && Main.sex.getInitialSexManager().isWashingScene();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DESIRES = new AbstractStatusEffect(80,
			"Desires",
			"desires",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Твои фетиши и желания влияют на то, сколько возбуждения ты получаешь от выполнения связанных с сексом действий. Выбор действия с связанным фетишем, которым ты обладаешь, также не увеличивает твою порочность.";
				
//			} else if(Main.game.isInSex()) {
//				GameCharacter targetedCharacter = Main.sex.getTargetedPartner(target);
//				SexType foreplayPreference = Main.sex.getForeplayPreference((NPC) target, targetedCharacter);
//				SexType mainPreference = Main.sex.getMainSexPreference((NPC) target, targetedCharacter);
//				
//				return UtilText.parse(target, targetedCharacter,
//						(Main.game.isInNewWorld()
//								?"The power of your arcane aura allows you to sense [npc.namePos] sexual preferences:"
//								:"Somehow, you're able to instinctively sense what [npc.namePos] sexual preferences are:")
//						+ "<br/>[style.italics"+(Main.sex.isInForeplay(target)?"PinkLight(<b>Foreplay</b>: ":"Disabled(Foreplay: ")
//							+ (foreplayPreference!=null
//									?"[npc.Her] "+foreplayPreference.getPerformingSexArea().getName(target, true)+" and [npc2.namePos] "+foreplayPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
//									:"[npc.She] [npc.has] no preference...")
//							+ ")]"
//						+ "<br/>[style.italics"+(!Main.sex.isInForeplay(target)?"Pink(<b>Sex</b>: ":"Disabled(Sex: ")
//						+ (mainPreference!=null
//								?"[npc.Her] "+mainPreference.getPerformingSexArea().getName(target, true)+" and [npc2.namePos] "+mainPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
//								:"[npc.She] [npc.has] no preference...")
//						+ ")]")
//						+ (Main.sex.isCharacterObeyingTarget(target, Main.game.getPlayer())
//							?"<br/>[style.italicsMinorGood([npc.She] will listen to your requests.)]"
//							:"<br/>[style.italicsMinorBad([npc.She] will ignore all of your requests.)]");
//				
			} else {
				return UtilText.parse(target,
						(Main.game.isInNewWorld()
								?"Благодаря скрытой силе твоей арканой ауры, ты можешь чувствовать [npc.namePos] ненейтральные предпочтения к сексуальным действиям."
								:"Как-то вы можете инстинктивно понять, какие [npc.namePos] ненейтральные предпочтения в сексуальных действиях.")
						+ "<br/>"
						+ "[style.italicsSex(Вы можете определить, какие области [npc.name] хочет использовать во время секса.)]");
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();
			
			if(Main.game.isInSex()) {
				GameCharacter targetedCharacter = Main.sex.getTargetedPartner(target);
				SexType foreplayPreference = Main.sex.getForeplayPreference(target, targetedCharacter);
				SexType mainPreference = Main.sex.getMainSexPreference(target, targetedCharacter);
				
//				modList.add(UtilText.parse(target, "<b>[npc.NamePos] Preferences:</b>"));
				
				modList.add(UtilText.parse(target, targetedCharacter,
										"[style.italics"+(Main.sex.isInForeplay(target)?"PinkLight(<b>Foreplay</b>: ":"Disabled(Foreplay: ")
											+ (foreplayPreference!=null
													?"[npc.Her] "+foreplayPreference.getPerformingSexArea().getName(target, true)+" и [npc2.namePos] "+foreplayPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
													:"[npc.She] [npc.has] нет предпочтений...")
											+ ")]"));
				modList.add(UtilText.parse(target, targetedCharacter,
										"[style.italics"+(!Main.sex.isInForeplay(target)?"Pink(<b>Sex</b>: ":"Disabled(Sex: ")
											+ (mainPreference!=null
													?"[npc.Her] "+mainPreference.getPerformingSexArea().getName(target, true)+" и [npc2.namePos] "+mainPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
													:"[npc.She] [npc.has] нет предпочтений...")
											+ ")]"));
				modList.add(UtilText.parse(target, targetedCharacter,
										Main.sex.isCharacterObeyingTarget(target, Main.game.getPlayer())
										?"[style.italicsMinorGood([npc.She] будет прислушиваться к вашим просьбам.)]"
										:"[style.italicsMinorBad([npc.She] будет игнорировать все ваши просьбы.)]"));
			}
			
			return modList;
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			List<AbstractFetish> orderedFetishList = new ArrayList<>();
			
			for(AbstractFetish f : Fetish.getAllFetishes()) {
				FetishDesire desire = target.getFetishDesire(f);
				if(desire!=FetishDesire.TWO_NEUTRAL) {
					orderedFetishList.add(f);
				}
			}
			orderedFetishList.sort((e1, e2) -> target.getFetishDesire(e2).compareTo(target.getFetishDesire(e1)));

			for(AbstractFetish f : orderedFetishList) {
				FetishDesire desire = target.getFetishDesire(f);
				additionalDescriptions.add(new Value<>(1, "<b style='color:"+desire.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(desire.getNameAsVerb())+"</b>: "+Util.capitaliseSentence(f.getShortDescriptor(target))));
			}
			
			return additionalDescriptions;
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isPlayer() || Main.game.isInSex();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ORGASM_COUNTER = new AbstractStatusEffect(80,
			"Orgasms",
			"sexEffects/orgasms",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			if(target.isPlayer()) {
				sb.append("Любой, кто обладает сильной арканой аурой, такой как твоя, не страдает от какого-либо рефрактерного периода после оргазма...");
				
			} else {
				sb.append("Любой, кто находится в присутствии сильной арканой ауры, такой как твоя, не страдает от какого-либо рефрактерного периода после оргазма...");
			}
			
			sb.append("<br/>[npc.Name] [npc.verb(need)] to orgasm [style.boldSex(" + Util.intToCount(target.getOrgasmsBeforeSatisfied()) + ")] before [npc.sheIs] satisfied.");

			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			
			if(Main.sex.getNumberOfOrgasms(target)>=target.getOrgasmsBeforeSatisfied()) {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull] [style.colourExcellent(satisfied)].")));
			} else {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull] [style.colourTerrible(not satisfied yet)]!")));
			}
			
			if(!target.isAbleToOrgasm()) {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull] [style.colourTerrible(not able to orgasm)]!")));
			}
			
			int bonus = Main.sex.getNumberOfAdditionalOrgasms(target);
			if(!target.isPlayer() && bonus != 0) {
				if(bonus>0) {
					additionalDescriptions.add(
							new Value<>(2,
									UtilText.parse(target,
											"[npc.Her] desire has been [style.boldExcellent(boosted)], and so this goal is [style.boldGood(" + Util.intToString(bonus) + " orgasm" + (bonus==1?"":"s") + ")] higher than normal!")));
				} else {
					additionalDescriptions.add(
							new Value<>(2,
									UtilText.parse(target,
											"[npc.Her] desire has been [style.boldTerrible(suppressed)], and so this goal is [style.boldBad(" + Util.intToString(-bonus) + " orgasm" + (bonus==-1?"":"s") + ")] ниже нормы!")));
				}
			}

			return additionalDescriptions;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();

			Colour orgasmColour = PresetColour.GENERIC_ARCANE;
			int orgasms = Main.sex.getNumberOfOrgasms(target);
			if(orgasms<RenderingEngine.orgasmColours.length) {
				orgasmColour = RenderingEngine.orgasmColours[orgasms];
			}
			
			modList.add("<b style='color:"+orgasmColour.toWebHexString()+";'>"+orgasms+"</b> Orgasm"+(orgasms==1?"":"s"));

			int essences = Main.sex.getEssenceGeneration(target);
			if(target.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
				modList.add("Выработает [style.boldBad(0 эссенций)]");
				modList.add("Вызвана [style.boldBad('"+RECOVERING_AURA.getName(target)+"')] effect");
				
			} else {
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					modList.add("Генерирует [style.boldArcane("+(essences)+" эссенций)] после секса");
					modList.add("[style.boldExcellent(Удвоенная)] от [style.colourTrait("+Perk.NYMPHOMANIAC.getName(target)+" trait)]");
					
				} else {
					modList.add("Выработает [style.boldArcane("+(essences)+" essences)]");
				}
				
				if(orgasms>=5) {
					modList.add("[style.boldBad(Максимальное количество эссенций достигнуто)]");
				}
			}
			return modList;
		}
		@Override
		public String getPathName(GameCharacter owner) {
			if(Main.sex.getNumberOfOrgasms(owner)>=owner.getOrgasmsBeforeSatisfied()) {
				return "sexEffects/orgasmsSatisfied";
			}
			return super.getPathName(owner);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			StringBuilder SVGImageSB = new StringBuilder();

			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+super.getSVGString(owner)+"</div>");
			
			int orgasms = Main.sex.getNumberOfOrgasms(owner);
			
			SVGImageSB.append("<div style='width:40%;height:40%;position:absolute; top:0; right:4px;'>");
				if(orgasms == 0) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterZero());
				} else if(orgasms == 1) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterOne());
				} else if(orgasms == 2) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterTwo());
				} else if(orgasms == 3) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterThree());
				} else if(orgasms == 4) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFour());
				} else if(orgasms == 5) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFive());
				} else {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFivePlus());
				}
			SVGImageSB.append("</div>");
			
			return SVGImageSB.toString();
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PENIS_STATUS = new AbstractStatusEffect(95,
			"Penis status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.PENIS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.PENIS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(stroking [npc.her] own [npc.cock])]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a handjob)]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(frotting)] with [npc.her] two [npc.cocks(true)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(frotting)] with [npc2.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] [style.boldSex(a tail-job)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a tail-job)]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] [style.boldSex(a tentacle-job)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a tentacle-job)]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] давай [npc.herself] [style.boldSex(минет)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] давай [npc2.name] [style.boldSex(минет)]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(клитор-фроттинг)] с [npc.her] собственной [npc.cock]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(клитор-фроттинг)] с [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] давай [npc.herself] [style.boldSex(фотье)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] давай [npc2.name] [style.boldSex(фотье)]!"));
								}
								break;
						}
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.penis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							target,
//							SexAreaPenetration.PENIS,
//							partner,
//							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).get(partner).iterator().next())));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.sex.getOngoingActionsMap(target)!=null
					&& !Main.sex.getOngoingSexAreas(target, SexAreaPenetration.PENIS).isEmpty()
					&& !Collections.disjoint(
							Main.sex.getOngoingSexAreas(target, SexAreaPenetration.PENIS).get(Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.PENIS).get(0)),
							Util.newArrayListOfValues(SexAreaPenetration.values()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.PENIS, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis());
		}
	};
	
	public static AbstractStatusEffect CLIT_STATUS = new AbstractStatusEffect(95,
			"Clitoris status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.CLIT);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.CLIT);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.CLIT;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.name]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(teasing)] [npc.her] own [npc.cock]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(teasing)] [npc2.namePos] [npc2.cock]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-teasing)] [npc.her] own pussy!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-teasing)] [npc2.namePos] pussy!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-teasing)] [npc.her] own pussy!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-teasing)] [npc2.namePos] pussy!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(performing cunnilingus)] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(performing cunnilingus)] on [npc2.name]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tribbing)] with [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(foot-teasing)] [npc.her] own pussy!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(foot-teasing)] [npc2.namePos] pussy!"));
								}
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.clit]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							target,
//							SexAreaPenetration.CLIT,
//							partner,
//							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.CLIT).get(partner).iterator().next())));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.sex.getOngoingActionsMap(target)!=null
					&& !Main.sex.getOngoingSexAreas(target, SexAreaPenetration.CLIT).isEmpty()
					&& !Collections.disjoint(
							Main.sex.getOngoingSexAreas(target, SexAreaPenetration.CLIT).get(Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.CLIT).get(0)),
							Util.newArrayListOfValues(SexAreaPenetration.values()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.CLIT, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeClit());
		}
	};
	
	public static AbstractStatusEffect ANUS_STATUS = new AbstractStatusEffect(96,
			"Anus status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ANUS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ANUS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ANUS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ANUS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");

			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fingering)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tail-fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tentacle-fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(anilingus)] [npc.herself]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(anilingus)] on [npc.name]!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(clit-fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):"are [style.boldSex(pushing their toes)]")
										+"  into [npc.namePos] [npc.asshole]!"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					boolean selfAction = target.equals(main);
					descriptionAdded = true;
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append("[npc.NameIsFull] performing [style.boldSex(anilingus)] on [npc.herself]!");
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(anilingus)] on [npc2.name]!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+"[style.boldSex(fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.asshole]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.ANUS);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.game.isAnalContentEnabled();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ANUS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus());
		}
	};

	public static AbstractStatusEffect ASS_STATUS = new AbstractStatusEffect(96,
			"Ass status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ASS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ASS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ASS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ASS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.ass]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc2.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-hotdogging)] [npc2.name]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-hotdogging)] [npc2.name]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.namePos] [npc2.ass]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-hotdogging)] [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.ass] with [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.ass] with [npc.her] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.namePos] [npc2.ass]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc2.name]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.ass]"), descriptionSB);
			
			descriptionSB.append("</p>");

			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.ASS;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ASS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAss());
		}
	};
	
	public static AbstractStatusEffect MOUTH_STATUS = new AbstractStatusEffect(99,
			"Mouth status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.MOUTH);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.MOUTH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own [npc.fingers]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] [npc.fingers]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] a [style.boldSex(blowjob)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a [style.boldSex(blowjob)]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own [npc.tail]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] [npc.tail]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own tentacle!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] tentacle!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.name]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own [npc.clit]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc.her] own [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(licking)] [npc.namePos] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(licking)] [npc.namePos] [npc.armpits]!"));
								}
								break;
							case ANUS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(anilingus)] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(anilingus)] on [npc.name]!"));
								}
								break;
							case ASS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(licking)] [npc.namePos] [npc.ass]!"));
								}
								break;
							case BREAST:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.namePos] [npc.breasts]!"));
								}
								break;
							case NIPPLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking on)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking on)] [npc.namePos] [npc.nipples]!"));
								}
								break;
							case BREAST_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.namePos] [npc.crotchBoobs]!"));
								}
								break;
							case NIPPLE_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking on)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking on)] [npc.namePos] [npc.crotchNipples]!"));
								}
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.name]!"));
								}
								break;
							case THIGHS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.namePos] [npc.legs]!"));
								}
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] a [style.boldSex(blowjob)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a [style.boldSex(blowjob)]!"));
								}
								break;
							case URETHRA_VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex(cunnilingus)] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex(cunnilingus)] на [npc.name]!"));
								}
								break;
							case VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex(cunnilingus)] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex(cunnilingus)] на [npc.name]!"));
								}
								break;
							case SPINNERET:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex(oral)] на собственной паутине [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex(oral)] на паутине [npc.namePos]!"));
								}
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] рот"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.MOUTH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth());
		}
	};
	
	public static AbstractStatusEffect BREAST_STATUS = new AbstractStatusEffect(98,
			"Breast status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.hasBreasts()) {
				return "Breast status";
			} else {
				return "Chest status";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.BREAST);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.BREAST);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.BREAST);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(tail-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(tail-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex(tentacle-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex(tentacle-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] на [npc.name]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] на [npc.her] own [npc.clit]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] на [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.breasts] with [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.breasts] with [npc.her] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.breasts]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.BREAST;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& (!target.isFeral() || target.getFeralAttributes().isBreastsPresent())
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST, owner.hasBreasts()?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts():SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat());
		}
	};
	
	public static AbstractStatusEffect NIPPLE_STATUS = new AbstractStatusEffect(97,
			"Nipple status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.NIPPLE);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.nipples]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.nipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
//					&& target.isBreastFuckableNipplePenetration()
//					&& Main.getProperties().hasValue(PropertyValue.nipplePenContent)
					;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	};
	
	public static AbstractStatusEffect BREAST_CROTCH_STATUS = new AbstractStatusEffect(98,
			"Crotch-boob status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Udders status";
			} else {
				return "Crotch-boob status";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.BREAST_CROTCH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.BREAST_CROTCH);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.BREAST_CROTCH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] на [npc.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] на [npc.her] собственной [npc.tail]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] на [npc.namePos] [npc.tail]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] на [npc.her] собственной [npc.tentacle]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] на [npc.namePos] [npc.tentacle]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.her] own [npc.clit]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.crotchBoobs] with [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.crotchBoobs] with [npc.her] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] выполняет [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] на [npc.name]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.crotchBoobs]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.BREAST_CROTCH;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST_CROTCH,
					owner.getBreastCrotchShape()==BreastShape.UDDERS
						?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUdders()
						:SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsCrotch());
		}
	};
	
	public static AbstractStatusEffect NIPPLE_CROTCH_STATUS = new AbstractStatusEffect(97,
			"Nipple status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Статус вымени";
			} else {
				return "Статус сосков";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.NIPPLE_CROTCH);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.crotchNipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch()
					&& target.isBreastCrotchFuckableNipplePenetration()
					&& Main.getProperties().hasValue(PropertyValue.nipplePenContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE_CROTCH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	};
	
	public static AbstractStatusEffect URETHRA_PENIS_STATUS = new AbstractStatusEffect(97,
			"Penis Urethra status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_PENIS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.urethraPenis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasPenisIgnoreDildo()
					&& target.isUrethraFuckable()
					&& Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_PENIS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis());
		}
	};
	
	public static AbstractStatusEffect URETHRA_VAGINA_STATUS = new AbstractStatusEffect(97,
			"Vaginal Urethra status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_VAGINA);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] [npc.urethraVagina]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasVagina()
					&& target.isVaginaUrethraFuckable()
					&& Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina());
		}
	};
	
	public static AbstractStatusEffect VAGINA_STATUS = new AbstractStatusEffect(95,
			"Pussy status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.VAGINA);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			
			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fingering)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tail-fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tentacle-fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(cunnilingus)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(cunnilingus)] on [npc.name]!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.pussy]!"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.pussy]!"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(clit-fucking)] [npc.namePos] [npc.pussy]!"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(tribbing)] с [npc2.name]!"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] в [npc.her] собственную [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):"  в @@T0@@ @@T1@@!")
										+"в [npc.namePos] [npc.pussy]!"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] выполняет [style.boldSex(cunnilingus)] на [npc.herself]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" выполняет [style.boldSex(cunnilingus)] на [npc2.name]!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.pussy]!")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tribbing)] with [npc2.name]!"));
							}
							break;
						case SPINNERET:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.boldDisabled(No ongoing actions.)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] pussy"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasVagina();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina());
		}
	};
	
	public static AbstractStatusEffect SPINNERET_STATUS = new AbstractStatusEffect(95,
			"Spinneret status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.SPINNERET;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			
			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] собственная паутинная нить!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fingering)] [npc.namePos] spinneret!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tail-fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tentacle-fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(oral)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(oral)] on [npc.namePos] spinneret!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own spinneret!"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own spinneret!"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(clit-fucking)] [npc.namePos] spinneret!"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(tribbing)] with [npc2.namePos] spinneret!"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):" are [style.boldSex(pushing their toes)]")
										+"  into [npc.namePos] spinneret!"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(oral)] on [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" выполняет [style.boldSex(oral)] на [npc2.namePos] spinneret!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own spinneret!")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tribbing)] с [npc2.namePos] spinneret!"));
							}
							break;
						case SPINNERET:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.boldDisabled(Нет текущих действий.)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] spinneret"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasSpinneret();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.SPINNERET, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaSpinneret());
		}
	};
	
	public static AbstractStatusEffect THIGH_STATUS = new AbstractStatusEffect(95,
			"Thigh status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.THIGHS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.THIGHS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.THIGHS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.THIGHS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc2.namePos] thighs!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] thighs"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.THIGHS;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.THIGHS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs());
		}
	};
	
	public static AbstractStatusEffect ARMPIT_STATUS = new AbstractStatusEffect(95,
			"Armpit status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ARMPITS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.armpits]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.armpits]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc2.namePos] armpits!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.armpits]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case URETHRA_VAGINA:
							case VAGINA:
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] armpits"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			SexAreaOrifice orifice = SexAreaOrifice.ARMPITS;
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							partner,
//							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
//							target,
//							orifice)));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.game.isArmpitContentEnabled();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ARMPITS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaArmpits());
		}
	};

	public static AbstractStatusEffect HAND_STATUS = new AbstractStatusEffect(95,
			"Hand status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.FINGER);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.FINGER);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.FINGER;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case CLIT:
								break;
							case PENIS:
								break;
							case TAIL:
								break;
							case TENTACLE:
								break;
							case TONGUE:
								break;
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(holding hands)] with [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(holding hands)] with [npc2.name]!"));
								}
								break;
							case FOOT:
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(No ongoing action...)]");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.hands]"), descriptionSB);
			
			descriptionSB.append("Контекст: Java UI/Dialogue src/com/lilithsthrone/game/character/effects/StatusEffect.java\nТекст:\n</p>");
			
			return descriptionSB.toString();
		}
//		@Override
//		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
//			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).isEmpty()) {
//				return null;
//			}
//			
//			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).get(0);
//			
//			return new Value<>(3,
//					Main.sex.formatPenetration(
//					target.getPenetrationDescription(false,
//							target,
//							SexAreaPenetration.FINGER,
//							partner,
//							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.FINGER).get(partner).iterator().next())));
//		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex() || !Main.sex.getAllParticipants(true).contains(target) || Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).isEmpty()) {
				return false;
			}
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).get(0);
			return Main.sex.getOngoingSexAreas(target, SexAreaPenetration.FINGER).get(partner).contains(SexAreaPenetration.FINGER);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.FINGER, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger(), Util.newArrayListOfValues(SexAreaPenetration.FINGER));
		}
	};
	
	
	public static List<AbstractStatusEffect> allStatusEffects;
	public static List<AbstractStatusEffect> allStatusEffectsRequiringApplicationCheck;
	public static List<AbstractStatusEffect> allStatusEffectsRequiringApplicationCheckNonCombat;
	public static List<AbstractStatusEffect> preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted;
	
	public static Map<AbstractStatusEffect, String> statusEffectToIdMap = new HashMap<>();
	public static Map<String, AbstractStatusEffect> idToStatusEffectMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractStatusEffect getStatusEffectFromId(String id) {
		if(id.equals("innoxia_massaged")) {
			return CLEANED_MASSAGED;
		} else if(id.equals("BATH_BOOSTED") || id.equals("innoxia_cleaned_spa")) {
			return CLEANED_SPA;
		} else if(id.equals("BATH") || id.equals("innoxia_cleaned_bath")) {
			return CLEANED_BATH;
		} else if(id.equals("SHOWER") || id.equals("innoxia_cleaned_shower")) {
			return CLEANED_SHOWER;
		}
		
		if(id.equals("innoxia_item_broodmother_pill")) {
			return BROODMOTHER_PILL;
		}
		
		id = Util.getClosestStringMatch(id, idToStatusEffectMap.keySet());
		
		return idToStatusEffectMap.get(id);
	}
	
	public static String getIdFromStatusEffect(AbstractStatusEffect perk) {
		return statusEffectToIdMap.get(perk);
	}

	static {
		allStatusEffects = new ArrayList<>();
		allStatusEffectsRequiringApplicationCheck = new ArrayList<>();
		allStatusEffectsRequiringApplicationCheckNonCombat = new ArrayList<>();
		
		// Modded status effects:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/statusEffects");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractStatusEffect statusEffect = new AbstractStatusEffect(innerEntry.getValue(), entry.getKey(), true) {};
					allStatusEffects.add(statusEffect);
					statusEffectToIdMap.put(statusEffect, innerEntry.getKey());
					idToStatusEffectMap.put(innerEntry.getKey(), statusEffect);
//					System.out.println("modded SE: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded status effect failed at 'StatusEffect'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res status effects:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/statusEffects");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractStatusEffect statusEffect = new AbstractStatusEffect(innerEntry.getValue(), entry.getKey(), false) {};
					allStatusEffects.add(statusEffect);
					statusEffectToIdMap.put(statusEffect, innerEntry.getKey());
					idToStatusEffectMap.put(innerEntry.getKey(), statusEffect);
//					System.out.println("res SE: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading status effect failed at 'StatusEffect'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// Hard-coded status effects (all those up above):
		
		Field[] fields = StatusEffect.class.getFields();
		
		for(Field f : fields){
			if (AbstractStatusEffect.class.isAssignableFrom(f.getType())) {
				
				AbstractStatusEffect statusEffect;
				
				try {
					statusEffect = ((AbstractStatusEffect) f.get(null));

					statusEffectToIdMap.put(statusEffect, f.getName());
					idToStatusEffectMap.put(f.getName(), statusEffect);
					allStatusEffects.add(statusEffect);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractStatusEffect> getAllStatusEffects() {
		return allStatusEffects;
	}
	
	public static List<AbstractStatusEffect> getAllStatusEffectsRequiringApplicationCheck() {
		if(!Main.game.isStarted()) {
			if(preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted==null) {
				preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted = new ArrayList<>(allStatusEffects);
				preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted.removeIf(se->se.isCombatEffect());
			}
			return preGameNonCombatStatusEffectsAsDiscussedInDevelopersChannelAfterFindingTheBugWhichWasCausingManaBurnStacksToThrowErrorsBecauseCombatStatusEffectsWereAddedBeforeTheGameWasStarted;
		}
		if(allStatusEffectsRequiringApplicationCheck.isEmpty()) { // Initialise on first call
			for(AbstractStatusEffect se : allStatusEffects) {
				se.isConditionsMet(Main.game.getPlayer()); // To initialise the variable
				if(se.isRequiresApplicationCheck()) {
					allStatusEffectsRequiringApplicationCheck.add(se);
					if(!se.isCombatEffect()) {
						allStatusEffectsRequiringApplicationCheckNonCombat.add(se);
					}
				}
			}
//			System.out.println("ASE/SE: "+allStatusEffectsRequiringApplicationCheck.size()+"/"+allStatusEffects.size());
		}
		if(Main.game.isInCombat()) {
			return allStatusEffectsRequiringApplicationCheck;
		} else {
			return allStatusEffectsRequiringApplicationCheckNonCombat;
		}
	}
}
