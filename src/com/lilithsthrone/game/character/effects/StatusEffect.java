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
					"Базовый [style.colourUnarmed(урон без оружия)] равен [style.colourMinorGood(20% of телосложению)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ZERO_WEAK.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] невероятно слаб(а). [npc.She] [npc.verb(struggle)] не может нанести много урона своим хилым маленьким [npc.her] [npc.arms], а [npc.her] хрупкое тело особенно уязвимо к физическому урону.");
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
					"Базовый [style.colourUnarmed(урон без оружия)] равен [style.colourMinorGood(20% of телосложению)]")) {
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
					"Базовый [style.colourUnarmed(урон без оружия)] равен [style.colourMinorGood(20% of телосложению)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.TWO_STRONG.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] сильнее и выносливее, чем можно было бы ожидать от [npc.her] размера тела, и [npc.is] способен наносить больше физического урона в результате.");
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
					"Базовый [style.colourUnarmed(урон без оружия)] равен [style.colourMinorGood(20% of телосложению)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.THREE_POWERFUL.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] значительно сильнее и выносливее, чем можно было бы ожидать от [npc.her] размера тела, и [npc.is] способен наносить значительный физический урон в результате.");
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
					"Базовый [style.colourUnarmed(урон без оружия)] равен [style.colourMinorGood(20% of телосложению)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FOUR_MIGHTY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] исключительный уровень подготовки, и лишь немногие могут надеяться соперничать с [npc.her] первобытной физической мощью.");
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
					"Базовый [style.colourUnarmed(урон без оружия)] равен [style.colourMinorGood(20% of телосложению)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FIVE_HERCULEAN.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Тело [npc.NamePos] — это легенда; простые смертные взирают на [npc.herHim] со страхом и благоговением!");
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
			"No Arcane Power",
			"attIntelligence0",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Из-за того, что в этом мире не существует Arcane, ваша способность творить заклинания практически равна нулю.";
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
					"[style.boldMana(Максимум "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldBad(ограничено 5)]",
					"[style.boldBad(Уязвимость)] к [style.boldArcane(арканным штормам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ZERO_AIRHEAD.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Несмотря на вашу природную связь с Arcane, вам каким-то образом удалось растерять большую часть своей силы...";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull] не способен значимым образом использовать Arcane. Это типичный уровень связи с Arcane для всех обычных рас этого мира.");
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
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(арканным штормам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ONE_AVERAGE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Вы обладаете исключительной природной способностью управлять Arcane, и в результате вы гораздо сильнее подавляющего большинства населения Dominion.";
			} else {
				return UtilText.parse(target, "[npc.Name] обладает достойными знаниями о том, как обуздать аркану; они сопоставимы с навыками представителей обычной расы, прошедших обширное обучение.");
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
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(арканным штормам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.TWO_SMART.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] искусно владеет арканой, благодаря чему [npc.her] заклинания не только легче произносить, но и они наносят больше урона.");
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
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(арканным штормам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.THREE_BRAINY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Вы обладаете высочайшим мастерством в управлении арканой. Ваши заклинания легче произносить и они наносят больше урона, а также вы обладаете небольшим сродством к стихийному урону.";
			} else {
				return UtilText.parse(target, "[npc.NameIsFull] обладает высочайшим мастерством в управлении арканой. [npc.Her] заклинания легче произносить и они наносят больше урона, а [npc.she] также обладает небольшим сродством к стихийному урону.");
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
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(арканным штормам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FOUR_GENIUS.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Вы обладаете чрезвычайным мастерством в управлении арканой. Ваши заклинания легче произносить и они наносят больше урона, а также вы обладаете значительным сродством к стихийному урону.";
			} else {
				return UtilText.parse(target, "[npc.NameIsFull] обладает чрезвычайным мастерством в управлении арканой. [npc.Her] заклинания легче произносить и они наносят больше урона, а [npc.she] также обладает значительным сродством к стихийному урону.");
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
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(арканным штормам)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FIVE_POLYMATH.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Ваша способность обуздать аркану может сравниться лишь с силой самой Lilith. Творить заклинания для вас так же естественно, как дышать.";
			} else {
				return UtilText.parse(owner, "Способность [npc.NamePos] обуздать аркану может сравниться лишь с силой самой Lilith. Творить заклинания для [npc.herHim] так же естественно, как дышать.");
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
				return "Вы совершенно не испорчены, и, помимо самых консервативных сексуальных актов с любимым человеком, секс вас в целом не особо интересует.";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull] совершенно не испорчен(а), и помимо самых консервативных сексуальных актов с тем, кого [npc.she] любит, [npc.sheIs] вообще не особо интересуется сексом.");
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
				return "Вы не против случайного секса, но всё ещё не готовы к каким-либо экстремальным ласкам.";
			} else {
				return UtilText.parse(owner, "[npc.NameIsFull] не против случайного секса, но не готов(а) к каким-либо экстремальным ласкам.");
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
				return "Сексуальные акты, которые раньше могли вызывать у вас дискомфорт, теперь стали центром ваших фантазий, и вам не терпится опробовать их на податливом партнере...";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a dirty look in [npc.her] eyes, and you often notice [npc.her] gaze lingering hungrily over your body.");
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
					return "Наполненная силой фантазий, постоянно проносящихся в вашем уме, Аркана начинает оказывать физическое воздействие на ваше тело, и вы чувствуете, что отныне забеременеть станет гораздо проще...";
				} else if (owner.hasPenis()) {
					return "Наполненный силой фантазий, постоянно проносящихся в вашем уме, Аркана начинает оказывать физическое воздействие на ваше тело, и вы чувствуете, что отныне оплодотворять своих сексуальных партнеров станет гораздо проще...";
				} else {
					return "Наполненная силой фантазий, постоянно проносящихся в вашем уме, Аркана начинает оказывать физическое воздействие на ваше тело, но, поскольку у вас нет половых органов, особо ничего и не произошло...";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"Напитанная фантазиями, что постоянно роятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], благодаря чему [npc.herHim] становится гораздо проще забеременеть.");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"Напитанная фантазиями, что постоянно роятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], благодаря чему [npc.herHim] становится гораздо проще оплодотворять других.");
				} else {
					return UtilText.parse(owner,
							"Напитанная фантазиями, что постоянно роятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], но поскольку у [npc.she] нет половых органов, особо заметных изменений пока не произошло.");
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
					return "Получив огромную силу благодаря похотливым фантазиям, что постоянно роятся в твоем уме, Аркана начинает оказывать физическое воздействие на твое тело, и ты чувствуешь, что отныне забеременеть станет гораздо проще...";
				} else if (owner.hasPenis()) {
					return "Получив огромную силу благодаря похотливым фантазиям, что постоянно роятся в твоем уме, Аркана начинает оказывать физическое воздействие на твое тело, и ты чувствуешь, что отныне оплодотворять своих сексуальных партнеров станет гораздо проще...";
				} else {
					return "Получив огромную силу благодаря похотливым фантазиям, что постоянно роятся в твоем уме, Аркана начинает оказывать физическое воздействие на твое тело, но поскольку у тебя нет половых органов, особо заметных изменений пока не произошло...";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"Благодаря огромному количеству силы, даруемой похотливыми фантазиями, что постоянно крутятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], из-за чего [npc.herHim] становится гораздо легче забеременеть.");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"Благодаря огромному количеству силы, даруемой похотливыми фантазиями, что постоянно крутятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], из-за чего [npc.herHim] становится гораздо легче оплодотворять других.");
				} else {
					return UtilText.parse(owner,
							"Благодаря огромному количеству силы, даруемой похотливыми фантазиями, что постоянно крутятся в уме [npc.her], Аркана начинает оказывать физическое воздействие на тело [npc.namePos], но так как у [npc.she] нет половых органов, особо ничего и не произошло.");
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
			Util.newArrayListOfValues("<b style='color: "+ PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+ "'>Демоническое мышление</b>")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FIVE_CORRUPT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] полностью и окончательно развращен(а),"
						+ (owner.getSubspeciesOverrideRace()==Race.DEMON
							?" как и подобает демону."
							:" и отчаянно [npc.verb(wish)], что [npc.she] [npc.was] демон.")
					+ " Похотливые мысли и фантазии, непрестанно проносящиеся в уме [npc.her], высвободили всю мощь Арканы, сделав тело [npc.her] гиперфертильным и полным сил.");
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
				return "Ты совсем не возбужден(а).";
			else
				return UtilText.parse(target, "[npc.NameIsFull] совсем не возбужден(а).");
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
					effects.add("[style.colourPinkLight(Прелюдия)]");
					effects.add("[style.colourMinorBad(-50%)] arousal gains");
				} else {
					effects.add("[style.colourPink(Основной секс)]");
					effects.add("[style.colourMinorGood(Full)] arousal gains");
					effects.add(UtilText.parse(target, "<i>Поскольку оргазм уже был, [npc.nameIsFull] на этом уровне возбуждения больше не прелюдия</i>"));
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
					"[style.colourPinkLight(Прелюдия)]",
					"[style.colourMinorBad(-50%)] arousal gains")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ONE_TURNED_ON.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Ты начинаешь довольно сильно возбуждаться.";
			else
				return UtilText.parse(target, "[npc.NameIsFull] начинаешь возбуждаться.");
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
					"[style.colourMinorGood(Full)] прирост возбуждения")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.TWO_EXCITED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Ты довольно сильно возбуждена, и твои мысли теперь сосредоточены на сексуальных желаниях.";
			else
				return UtilText.parse(target, "[npc.NameIsFull] довольно сильно возбуждается.");
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
					"[style.colourMinorGood(Full)] прирост возбуждения")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.THREE_HEATED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Обстановка накаляется. Ты не можешь думать ни о чем, кроме секса.";
			else
				return UtilText.parse(target, "[npc.Name] больше не может думать ни о чем, кроме секса.");
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
					"[style.colourPink(Основной секс)]",
					"[style.colourMinorGood(Full)] прирост возбуждения")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FOUR_PASSIONATE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Единственное, чего ты хочешь сейчас — это достичь оргазма.";
			else
				return UtilText.parse(target, "[npc.NameIsFull] думает только о том, чтобы достичь [npc.her] оргазма.");
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
					"[style.colourPink(Основной секс)]",
					"[style.colourMinorGood(Full)] прирост возбуждения")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FIVE_ORGASM_IMMINENT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Ты чувствуешь, как нарастает оргазм. Ты понимаешь, что до пика остались считанные секунды!";
			else
				return UtilText.parse(owner, "[npc.NameIsFull] вот-вот достигнет [npc.her]!");
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
			"Странная атмосфера",
			"weatherNightStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Повышенное либидо</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Этим вечером музей окутывает странная атмосфера, и ты необъяснимым образом чувствуешь невероятное возбуждение...";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInNewWorld();
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLEAR = new AbstractStatusEffect(100,
			"Ясное небо",
			"weatherDayClear",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			if(Main.game.isDayTime()) {
				sb.append("Солнце сияет в идеально чистом голубом небе.");
			} else {
				sb.append("Луна и звезды сияют в идеально чистом ночном небе.");
			}
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя признаков шторма пока нет, [npc.nameIsFull] все еще может поглощать фоновую энергию арканы для подпитки [npc.herself].");
			} else {
				sb.append(" Хотя признаков шторма пока нет, [npc.name] все еще ощущает проявления арканы в виде повышенного либидо.");
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
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(arcane storm)]: ");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLOUD = new AbstractStatusEffect(100,
			"Облачное небо",
			"weatherDayCloudy",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Погода, кажется, меняется в любой момент; сейчас пасмурно, возможен дождь.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя признаков шторма пока нет, [npc.nameIsFull] все еще может поглощать фоновую энергию арканы для подпитки [npc.herself].");
			} else {
				sb.append(" Хотя признаков шторма пока нет, [npc.name] все еще ощущает проявления арканы в виде повышенного либидо.");
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
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(arcane storm)]: ");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_RAIN = new AbstractStatusEffect(100,
			"Дождь",
			"weatherDayRain",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Тяжелые дождевые тучи над головой наконец лопнули, обрушив внезапный проливной ливень.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя признаков шторма пока нет, [npc.nameIsFull] все еще может поглощать фоновую энергию арканы для подпитки [npc.herself].");
			} else {
				sb.append(" Хотя признаков шторма пока нет, [npc.name] все еще ощущает проявления арканы в виде повышенного либидо.");
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
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(arcane storm)]: ");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_SNOW = new AbstractStatusEffect(100,
			"Снег",
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
				return "<p>Тяжелые темно-серые тучи, нависшие над Dominion последние несколько часов, наконец разразились ливнем. Сверху медленно опускаются крупные пушистые снежинки, и хотя первые кристаллы быстро тают при соприкосновении с землей, вскоре тонкий слой белоснежной пудры уже покрывает крыши и мостовые столицы.</p><p>То, что поначалу казалось лишь легким снегопадом, вскоре перерастает в неистовую метель, и менее чем за час улицы начинает удушать плотное белое одеяло снега. Еще через час снега наваливает столько, что любое передвижение по городу замедляется до черепашьего темпа. Несколько демонов пытаются использовать свое аркана-пламя, чтобы пробить путь сквозь ледяные сугробы, но снегопад настолько сильный, что даже их мощные заклинания кажутся малоэффективными.</p><p>Как раз в тот момент, когда кажется, что вся столица вот-вот окончательно застынет, слабый звон колокольчиков возвещает о прибытии спасителей города. Проделав долгий путь из своих замерзших тундровых краев, на Dominion прибывает целая орда оленеморфов. Их крупные раздвоенные копыта позволяют им с невероятной легкостью перемещаться по заснеженным улицам, и они быстро разделяются на многочисленные группы по расчистке снега, отправляясь в разные районы города. Под руководством особенно крупных и мускулистых особей оленеморфы принимаются за расчистку дорог.</p><p>Хотя погода и отказывается утихать, впечатляющим оленеморфам требуется всего несколько часов, чтобы полностью расчистить улицы от снега. Вскоре поползли слухи о том, что гости останутся в Dominion работать до конца февраля, чтобы город мог функционировать в эти холодные зимние месяцы.</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Тяжелые тучи над головой наконец лопнули, обрушив на землю лавину ослепительно белых снежинок.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя признаков шторма пока нет, [npc.nameIsFull] все еще может поглощать фоновую энергию арканы для подпитки [npc.herself].");
			} else {
				sb.append(" Хотя признаков шторма пока нет, [npc.name] все еще ощущает проявления арканы в виде повышенного либидо.");
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
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(arcane storm)]: ");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_GATHERING = new AbstractStatusEffect(100,
			"Надвигающийся шторм",
			"weatherDayStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Над [npc.name] тяжело нависает бурлящая масса густых черных грозовых туч. Прямо под их поверхностью видны вспышки розовой и пурпурной энергии, и [npc.she] [npc.verb(realise)], что в любой момент может разразиться арканный шторм.");
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
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("Время до следующей [style.colourArcane(arcane storm)]: ");
				exEff.add(Main.game.getNextStormTimeAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM = new AbstractStatusEffect(100,
			"Арканный шторм",
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
				
				sb.append("<p>Яркая розовая вспышка внезапно освещает весь город Dominion, заставляя немногих жителей, еще бродящих по улицам, поднять взор к небу. Высоко над ними грозные грозовые тучи наконец рассеялись, и бурлящая масса арканной энергии наконец оживает, потрескивая от разрядов.</p><p>В считанные мгновения по всему городу начинают разноситься призрачные стоны похоти и экстатические крики, а когда арканный гром проникает в разум тех, у кого нет сильной ауры, они обнаруживают, что не могут думать ни о чем, кроме секса.</p>");
				
				WorldRegion region = target.getWorldLocation().getWorldRegion();
				
				if(region!=WorldRegion.DOMINION && region!=WorldRegion.HARPY_NESTS) {
					sb.append("<p>Хотя шторм разражается высоко над Dominion, он не ограничивается только городом, а стремительно проносится по полям Фолой, уходя в окружающие леса и степные дикие земли. Подобно цепной реакции, вспышки пурпурных молний прорезают небо во всех направлениях, за которыми быстро следуют эротические стоны арканного грома.</p>");

					if(region==WorldRegion.FIELD_CITY
							|| region==WorldRegion.FIELDS
							|| region==WorldRegion.WOODLAND
							|| region==WorldRegion.RIVER) {
						
						if(target.getLocationPlace().isStormImmune()) {
							sb.append("<p>Хотя вы сами полностью невосприимчивы к его воздействию, вы чувствуете, что здесь, вдали от эпицентра, шторм значительно слабее. Хотя весьма вероятно, что любой встречный в этой области будет возбужденнее обычного, вы полагаете, что они смогут достаточно контролировать себя, чтобы противостоять возбуждающему эффекту шторма.</p>");
						} else {
							sb.append("<p>Хотя обычно вы уязвимы к его воздействию, сейчас вы чувствуете лишь легкое возбуждение; это доказывает, что здесь, вдали от эпицентра, шторм значительно слабее. Вы быстро понимаете, что любой встречный в этой области наверняка сможет достаточно контролировать себя, чтобы противостоять привычному возбуждающему эффекту, так же как и вы.</p>");
						}
						
					} else {
						sb.append("<p>Поскольку эпицентр шторма находится прямо над Dominion, вы находитесь достаточно далеко, чтобы слышать лишь едва уловимые арканные стоны, разлитые в воздухе. Хотя вероятно, что любой встречный здесь будет чуть более возбужденным, чем обычно, вы полагаете, что они легко смогут противостоять возбуждающему эффекту шторма.</p>");
					}
				}
				
				return sb.toString();
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Огромные всполохи розовых и фиолетовых молний прорезают небо, пока высоко над Dominion бушует арканный шторм.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Как секс-кукла на арканной тяге, [npc.nameIsFull] переполнена колоссальным количеством энергии!");
				
			} else {
				if(target.getWorldLocation().getWorldRegion()!=WorldRegion.DOMINION && target.getWorldLocation().getWorldRegion()!=WorldRegion.HARPY_NESTS) {
					sb.append(" [npc.NameIsFull] находится достаточно далеко от эпицентра шторма, чтобы стать почти полностью невосприимчивой к его возбуждающему эффекту.");
				} else {
					sb.append(" Благодаря [npc.NamePos] сродству с Arcane, [npc.herHim] почти полностью невосприимчив(а) к возбуждающему воздействию арканных штормов.");
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
				exEff.add("[style.colourGood(Получение)] [style.colourExcellent(огромного)] количества энергии от [style.colourArcane(арканного шторма)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
				exEff.add("[style.boldExcellent(Двойное)] [style.colourArcane(получение эссенции)] во время секса и боя");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("До [style.colourArcane(завершения шторма)] осталось:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_VULNERABLE = new AbstractStatusEffect(100,
			"Арканный шторм",
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
				
				return "<p>Яркая розовая вспышка внезапно освещает весь город Dominion, заставляя немногих жителей, всё еще бродящих по улицам, поднять взор к небу. Высоко над ними грозные штормовые тучи наконец разошлись, и бурлящая масса арканной энергии с треском ожила.</p><p>Спустя мгновение по всему городу начинают разноситься призрачные стоны похоти и экстатические крики. Хотя вы вошли в этот мир с достаточно высоким сродством к Arcane, чтобы стать невосприимчивым к эффектам шторма, вы потеряли большую часть своей силы и не можете не издать отчаянный [pc.moan], когда чувствуете, как вас начинает невероятно возбуждать.</p><p>Продолжая свой путь, вы ловите себя на мысли, что надеетесь встретить кого-нибудь, кто не прочь вас хорошенько оттрахать...</p>";
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Огромные всполохи розовых и фиолетовых молний прорезают небо, пока высоко над Dominion бушует арканный шторм.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Как секс-кукла на арканной тяге, [npc.nameIsFull] переполнена колоссальным количеством энергии!");
				
			} else {
				sb.append(" [npc.NameIsFull] сильно подвержен(а) воздействию текущего арканного шторма и не может думать ни о чем, кроме секса...");
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
				exEff.add("[style.colourGood(Получение)] [style.colourExcellent(огромного)] количества энергии от [style.colourArcane(арканного шторма)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
				exEff.add("[style.colourArcane(Всепоглощающая Похоть)]");
				exEff.add("[style.boldExcellent(Двойное)] [style.colourArcane(получение эссенции)] во время секса и боя");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("До [style.colourArcane(завершения шторма)] осталось:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_PROTECTED = new AbstractStatusEffect(100,
			"Arcane шторм (защищен)",
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
				
				return "<p>Яркая ярко-розовая вспышка внезапно освещает весь город Dominion, заставляя немногих жителей, всё еще бродящих по улицам, поднять взор к небесам. Высоко над ними грозные штормовые тучи наконец разошлись, и бурлящая масса arcane энергии наконец затрещала, оживая.</p><p>В считанные мгновения по всему городу начинают разноситься призрачные стоны похоти и экстатические крики, и когда arcane гром проникает в разум тех, у кого нет сильной ауры, они обнаруживают, что не могут думать ни о чем, кроме секса...</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Огромные всполохи розовых и фиолетовых молний прорезают небо, пока высоко над Dominion бушует арканный шторм.");
			if(target.hasPerkAnywhereInTree(Perk.DOLL_ARCANE_3)) {
				sb.append(" Хотя [npc.nameIsFull] защищен(а) от шторма, [npc.sheIs] всё еще может поглощать фоновую arcane энергию для подпитки [npc.herself].");
				
			} else {
				sb.append(" Хотя [npc.she] всё еще ощущает его воздействие в виде возросшего либидо, [npc.nameIsFull] в данный момент защищен(а) от большей части ярости шторма.");
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
				exEff.add("[style.colourGood(Получение)] энергии из фоновой [style.colourArcane(arcane)]");
			} else {
				exEff.add("[style.colourArcane(Повышенное либидо)]");
			}
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				exEff.add("До [style.colourArcane(завершения шторма)] осталось:");
				exEff.add(Main.game.getWeatherTimeRemainingAsTimeString());
			}
			return exEff;
		}
	};

	public static AbstractStatusEffect BLINDED = new AbstractStatusEffect(90,
			"Ослеплен(а)",
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
						"[npc.NameHasFull] фактически ослеплен(а), и в связи с этим [npc.she] [npc.is] с трудом ориентируется в [npc.her] окружении. В этом состоянии [npc.she] будет крайне неэффективен(на) в бою!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && !target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect BLINDED_NEGATED = new AbstractStatusEffect(90,
			"Ослеплен(а) (Эхолокация)",
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
						"Хотя [npc.name] должен(на) быть фактически ослеплен(а), [npc.she] [npc.is] способен(на) эффективно ориентироваться в [npc.her] окружении, используя способность [npc.her] эхолокации!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect DARKNESS = new AbstractStatusEffect(90,
			"Тьма",
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
						"Область, через которую [npc.name] [npc.verb(find)] [npc.herself], очень темная, и так как [npc.she] [npc.verb(lack)] каких-либо средств освещения, [npc.she] [npc.is] с трудом видит, куда [npc.sheIs] идет!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isInDarkness();
		}
	};

	public static AbstractStatusEffect DARKNESS_NEGATED = new AbstractStatusEffect(90,
			"Тьма (Снято)",
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
						"Область, через которую [npc.name] [npc.verb(find)] [npc.herself], очень темная, но, несмотря на это, [npc.she] [npc.is] видеть [#npc.getDescriptionInDarkness()].");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().isDark() && !target.isInDarkness();
		}
	};


	public static AbstractStatusEffect SHORT_SIGHTED = new AbstractStatusEffect(90,
			"нарушенное зрение",
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
						"[npc.Name] не очень хорошо видит без очков...");
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
			"улучшенное зрение",
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
							"Благодаря зачарованным линзам в очках [npc.her], не только [npc.namePos] нарушение зрения больше не проблема, но и [npc.she] [npc.is] получает преимущество от улучшенного зрения!");
				}
				return UtilText.parse(target,
						"Хотя [npc.she] [npc.do] не страдает от какого-либо нарушения зрения, зачарованные линзы в [npc.namePos] очках улучшают [npc.her] зрение!");
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
			"человек",
			null,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Ты человек, такой же, как и все остальные в этом мире.";
			else
				return "[npc.NameIsFull] — человек, такой же, как и все остальные в этом мире.";
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
			return (target.isFeral()?"[style.colourFeral(Дикий)] ":"")+target.getSubspecies().getName(target.getBody());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return UtilText.parse(target, "Хотя раса [npc.namePos] скрыта, они все еще получают бонусы от модификаторов атрибутов...");
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
										"[npc.NameIsFull] <span style='color:"+target.getSubspeciesOverride().getColour(target).toWebHexString()+";'>"+UtilText.addDeterminer(target.getSubspeciesOverride().getName(null))+"</span>"
										+ " but [npc.she] [npc.verb(appear)] to be "
										+"<span style='color:"+target.getSubspecies().getColour(target).toWebHexString()+";'>"+UtilText.addDeterminer(subspeciesName)+"</span>!")));
			}

			// Add body material modifiers:
			BodyMaterial material = target.getBodyMaterial();
			if(material.getAttributeModifiers(target)!=null
					|| material.getExtraEffects(target)!=null) {
				int lineHeight = 1;
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parse(target, "<i style='color:"+material.getColour().toWebHexString()+";'>[npc.NamePos] "+material.getName()+" тело дает [npc.herHim]:</i>"));
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
			"Водная гармония",
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
					"[style.boldBlueLight(Потеря ног)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "По мере того как вы приближаетесь к ближайшему водоему, вы чувствуете, как интенсивное покалывание начинает пробегать по вашим ногам. Прежде чем вы успеваете понять, что происходит, ваши мышцы непроизвольно сжимаются, прижимая ноги друг к другу и заставляя вас издать испуганный крик. Не успеваете вы среагировать, как ваши ноги стремительно срастаются и трансформируются, и через мгновение ваша нижняя часть тела принимает свой истинный, хвостообразный облик!<p style='text-align:center;'>[style.italicsMinorBad(Вы больше не можете надевать одежду в слоты для ног и стоп!)]</p>"
					+ target.postTransformationCalculation(); // To handle clothing removals
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Поскольку [npc.nameIsFull] [npc.a_race], и [npc.she] [npc.has] получили доступ к ближайшему водоему, [npc.her] нижняя часть тела трансформировалась в свой истинный, хвостообразный облик, благодаря чему [npc.herHim] чувствует себя очень комфортно!");
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
			"Слон в посудной лавке",
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
					"[style.boldTan(Отрастили две ноги)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "Оказавшись в месте, где поблизости нет больших водоемов, ты внезапно чувствуешь, как твоя нижняя часть тела с хвостом начинает покалывать. Прежде чем ты успеваешь понять, что происходит, твои мышцы непроизвольно сжимаются, заставляя тебя издать испуганный крик. Не успеваешь ты среагировать, как твой хвост стремительно разделяется и превращается в пару ног; они позволяют тебе ходить и бегать по суше, но кажутся тебе чем-то совершенно чужеродным.<p style='text-align:center;'>[style.italicsMinorGood(Теперь вы можете надевать одежду в слоты для ног и стоп!)]</p>"
					+ target.postTransformationCalculation(); // To handle clothing checks
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Поскольку [npc.nameIsFull] [npc.a_race], а поблизости нет водоемов, нижняя часть тела [npc.her] превратилась в пару ног, из-за чего [npc.herHim] [npc.verb(feel)] чувствует себя очень неуютно!");
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
			"Водная гармония",
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
						"Поскольку [npc.nameIsFull] [npc.a_race], а [npc.she] [npc.has] имеет доступ к ближайшему водоему, [npc.she] [npc.verb(feel)] чувствует себя очень комфортно!");
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
			"Слон в посудной лавке",
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
						"Поскольку [npc.nameIsFull] [npc.a_race], а поблизости нет водоемов, [npc.she] чувствует себя очень неуютно!");
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
			"андрофильный",
			"orientation_androphilic",
			PresetColour.MASCULINE,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(-50%)] [style.colourDmgLust(Урон похоти)] получен от [style.colourFeminine(женственных противниц)]",
					"[style.colourBad(-50%)] [style.colourDmgLust(Урон похоти)] нанесен [style.colourFeminine(женственным противницам)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] испытывает сексуальное влечение к маскулинным личностям, а [npc.verb(find)] женственных людей — это сплошное разочарование.");
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
			"гинефил",
			"orientation_gynephilic",
			PresetColour.FEMININE,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(-50%)] [style.colourDmgLust(Урон похоти)] получен от [style.colourMasculine(маскулинных противников)]",
					"[style.colourBad(-50%)] [style.colourDmgLust(Урон похоти)] нанесен [style.colourMasculine(маскулинным противникам)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] испытывает сексуальное влечение к женственным особям, а [npc.verb(find)] мужские персонажи вызывают у него сильное отвращение.");
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
			"андрогин (амбифил)",
			"orientation_ambiphilic",
			PresetColour.ANDROGYNOUS,
			true,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] испытывает сексуальное влечение как к маскулинным, так и к феминным особям, и [npc.do] не находит никакой степени женственности отталкивающей.");
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
			"слишком женственная одежда",
			"clothingFemininity",
			PresetColour.CLOTHING_PINK_LIGHT,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Некоторая одежда [npc.namePos] слишком женственна для маскулинной фигуры [npc.her]. [npc.She] [npc.verb(find)] [npc.herself] невероятно смущается из-за ношения такой одежды, и [npc.is] ему/ей трудно соображать ясно.");
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
			"слишком маскулинная одежда",
			"clothingMasculinity",
			PresetColour.CLOTHING_BLUE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Некоторые из [npc.namePos] одежды слишком маскулинны для [npc.her] женственной фигуры. [npc.She] [npc.verb(find)] [npc.herself] невероятно смущена тем, что носит такую одежду, и [npc.is] с трудом пытается соображать.");
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
			"грязная одежда",
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
						"Некоторые вещи [npc.namePos] покрыты спермой, молоком или другими сексуальными жидкостями. Ношение такой грязной одежды лишь подтверждает тот факт, что [npc.sheIs] всего лишь секс-игрушка.");
			}
			return UtilText.parse(target,
					"Некоторые вещи [npc.namePos] покрыты спермой, молоком или другими сексуальными жидкостями. [npc.SheIs] чувствует невероятное смущение, расхаживая в такой грязной одежде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Твоя вонючая одежда, кажется, притягивает неприятности.");
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
			"грязная одежда",
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
				return "Некоторые твои вещи покрыты спермой, молоком или другими сексуальными жидкостями. Ты чувствуешь невероятное возбуждение от того, что расхаживаешь в такой грязной одежде.";
			} else {
				return UtilText.parse(target, "Некоторые вещи [npc.namePos] покрыты спермой, молоком или другими сексуальными жидкостями. [npc.sheIs] чувствует невероятное возбуждение, расхаживая в такой грязной одежде.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Твоя вонючая одежда, кажется, притягивает неприятности.");
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
			"грязное тело",
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
						sb.append("Вы используете свой <b>"+clothing.getDisplayName(true)+"</b>, чтобы очистить свой "+clothing.getSlotEquippedTo().getName()
								+(seals
										?" пока вы надеваете "+(clothing.getClothingType().isPlural()?"их":"его")
										:" пока вы вставляете "+(clothing.getClothingType().isPlural()?"их в свои отверстия":"его в свое отверстие"))
								+".");
						
					} else {
						if(!clothing.isDirty()) {
							clothing.setDirty(target, true);
							if(sb.length()>0) {
								sb.append("<br/>");
							}
							sb.append("Вы используете свой <b>"+clothing.getDisplayName(true)+"</b>, чтобы очистить свой "+clothing.getSlotEquippedTo().getName()
									+", <b style='color:"+PresetColour.CUM.toWebHexString()+";'>загрязнение "+(clothing.getClothingType().isPlural()?"их":"его")+" в процессе</b>.");
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
								sb.append("Вы используете свой <b>"+clothing.getDisplayName(true)+"</b>, чтобы очистить свой "+clothing.getSlotEquippedTo().getName()
										+", <b style='color:"+PresetColour.CUM.toWebHexString()+";'>загрязнение "+(clothing.getClothingType().isPlural()?"их":"его")+" в процессе</b>.");
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
						"Некоторые части тела [npc.namePos] покрыты спермой, молоком или другими сексуальными выделениями. Такое грязное состояние лишь подчеркивает тот факт, что [npc.sheIs] не более чем секс-игрушка.");
			}
			return UtilText.parse(target, "Некоторые части тела [npc.namePos] покрыты спермой, молоком или другими сексуальными жидкостями. [npc.SheIs] чувствует невероятный стыд от того, что расхаживает в таком грязном виде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Твое вонючее тело, кажется, притягивает неприятности.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (!isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	
	public static AbstractStatusEffect BODY_CUM_MASOCHIST = new AbstractStatusEffect(80,
			"грязное тело",
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
				return "Некоторые части твоего тела покрыты спермой, молоком или другими сексуальными выделениями. Ты ловишь себя на мысли, что расхаживание в таком грязном виде невероятно тебя возбуждает.";
			} else {
				return UtilText.parse(target, "Некоторые части тела [npc.namePos] покрыты спермой, молоком или другими сексуальными жидкостями. [npc.sheIs] чувствует невероятное возбуждение, расхаживая в таком грязном состоянии.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Твое вонючее тело, кажется, притягивает неприятности.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	
	public static AbstractStatusEffect MARKED_BY_MUSK = new AbstractStatusEffect(80,
			"помеченный мускусом",
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
						"[npc.NameHasFull] был помечен дурманящим мускусом, и [npc.verb(find)] [npc.herself] возбуждается от этого характерного запаха, который [npc.she] теперь [npc.verb(carry)].");
			} else {
				return UtilText.parse(target,
						"[npc.NameHasFull] был помечен мускусом "+Util.charactersToStringListOfNames(target.getMuskMarkerCharacters(), true)
							+", и [npc.verb(find)] [npc.herself] возбуждается от этого характерного запаха, который [npc.she] теперь [npc.verb(carry)].");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			return new Value<>(1, "[style.italicsAqua(Только ванна, спа-процедуры или арканные влажные салфетки помогут избавиться от этого запаха.)]");
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
			"нестабильные чары",
			"unstable_enchantment_1",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues("[style.boldMinorBad(-10%)] [style.boldHealth(Максимум "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"[style.boldMinorBad(-10%)] [style.boldMana(Максимум "+Attribute.MANA_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] не может справиться с количеством магических чар, впитанных в [npc.her] оружие, одежду и татуировки, и поэтому [npc.is] страдает от некоторых побочных эффектов. Если [npc.she] продолжит экипировать зачарованные предметы, всё это определенно станет гораздо хуже...");
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
			"нестабильные чары",
			"unstable_enchantment_2",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			Util.newArrayListOfValues("[style.boldBad(-50%)] [style.boldHealth(Макс. "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"[style.boldBad(-50%)] [style.boldMana(Макс. "+Attribute.MANA_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] не может справиться с количеством магических чар, вложенных в [npc.her] оружие, одежду и татуировки, из-за чего [npc.is] страдает от тяжелых побочных эффектов. Если [npc.she] продолжит использовать зачарованные предметы, всё станет гораздо хуже...");
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
			"разрушенные чары",
			"unstable_enchantment_3",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 100f)),
			Util.newArrayListOfValues("[style.boldHealth(Макс. "+Attribute.HEALTH_MAXIMUM.getName()+")] [style.boldTerrible(установлено на 1)]",
					"[style.boldMana(Максимум "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldTerrible(установлено на 1)]",
					"[style.boldTerrible(Все щиты установлены на 0)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] не может справиться с количеством магических чар, вложенных в [npc.her] оружие, одежду и татуировки, из-за чего [npc.is] страдает от крайне тяжелых побочных эффектов.");
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
			"запечатанная одежда",
			"jinxed_clothing",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Чары на [npc.namePos] запечатанной одежде имеют вампирическую природу, и [npc.she] может чувствовать, как их сила вытягивает часть [npc.her] ауры...");
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
						return "<p>Когда вы заканчиваете надевать "+clothing.getName()+" на место, вы начинаете чувствовать странное тепло, исходящее от "+(clothing.getClothingType().isPlural()?"their":"its")+" поверхности. Чувствуя легкое беспокойство из-за способа арканического зачарования, которое "+(clothing.getClothingType().isPlural()?"they":"it")+" должно сдерживать, вы немедленно пытаетесь "
										+(clothing.getClothingType().isPlural()?"them":"it")+" снять.</p><p>Схватившись за "+clothing.getName()+", поначалу кажется, что всё в порядке, но когда вы пытаетесь стянуть "+(clothing.getClothingType().isPlural()?"them":"it")+" с себя, вы понимаете, что совершили большую ошибку. Вспышка арканической энергии внезапно пронзает ваше тело, и когда эта навязчивая сила врывается в ваш разум, вы обнаруживаете, что невольно разжимаете пальцы.</p><p>Стиснув зубы, вы пытаетесь еще раз снять этот злосчастный предмет одежды, но тут же обнаруживаете, что руки соскальзывают, стоит вам только попытаться стянуть "+(clothing.getClothingType().isPlural()?"them":"it")+" с себя. Как бы вы ни старались, вам удается лишь сдвинуть "+clothing.getName()
										+" слегка болтается, и всякий раз, когда кажется, что его вот-вот снимут, оно само собой возвращается на место.</p><p>В конце концов, сдавшись, вы решаете пойти и спросить у Lilaya, что происходит с "+(clothing.getClothingType().isPlural()?"these":"this")
										+" <b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>запечатанной</b> "+clothing.getName()+". Может, она знает способ разрушить печать?</p>"
								+(!((PlayerCharacter) target).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										?((PlayerCharacter) target).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										:"");
					
					} else {
						return "<p>Когда вы заканчиваете надевать "+clothing.getName()+" на место, вы начинаете чувствовать странное тепло, исходящее от "+(clothing.getClothingType().isPlural()?"their":"its")+" поверхности. Чувствуя легкое беспокойство из-за способа арканического зачарования, которое "+(clothing.getClothingType().isPlural()?"they":"it")+" должно сдерживать, вы немедленно пытаетесь "
										+(clothing.getClothingType().isPlural()?"them":"it")+" снять.</p><p>Схватившись за "+clothing.getName()+", поначалу кажется, что всё в порядке, но когда вы пытаетесь стянуть "+(clothing.getClothingType().isPlural()?"them":"it")+" с себя, вы понимаете, что совершили большую ошибку. Вспышка арканической энергии внезапно пронзает ваше тело, и когда эта навязчивая сила врывается в ваш разум, вы обнаруживаете, что невольно разжимаете пальцы.</p><p>Стиснув зубы, вы пытаетесь еще раз снять этот злосчастный предмет одежды, но тут же обнаруживаете, что руки соскальзывают, стоит вам только попытаться стянуть "+(clothing.getClothingType().isPlural()?"them":"it")+" с себя. Как бы вы ни старались, вам удается лишь сдвинуть "+clothing.getName()
										+" слегка болтается, и всякий раз, когда кажется, что его вот-вот снимут, оно само собой возвращается на место.</p><p>Предупреждение Lilaya о запечатанной одежде внезапно всплывает в вашей памяти, и вы издаете стон, осознавая, что "
										+(clothing.getClothingType().isPlural()?"these ":"this ")+clothing.getName()
										+(clothing.getClothingType().isPlural()?" are":" is")
										+" <b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>запечатана</b>. Вспомнив слова Lilaya, вы понимаете, что сможете снять печать, если направите часть поглощенных эссенций в нее...</p>";
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
			"спит",
			"sleeping",
			PresetColour.SLEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] спит...");
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
			"крепко спит",
			"sleeping_heavy",
			PresetColour.SLEEP_HEAVY,
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -5f)),
			Util.newArrayListOfValues("Не проснется во время нежного секса")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] спит настолько крепко, что даже крики и физическая встряска [npc.herHim] не смогут разбудить [npc.herHim]. [npc.SheIs] настолько безучастен, что даже самый нежный партнер может заняться сексом с [npc.herHim], не заставив [npc.herHim] проснуться...");
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
			"хорошо отдохнувший",
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
				return UtilText.parse(target, "После хорошего отдыха [npc.name] [npc.verb(feel)] полон энергии.");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED = new AbstractStatusEffect(80,
			"хорошо отдохнувший (усилено)",
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
					return UtilText.parse(target, "Благодаря использованию способности [npc.her] извлекать максимум из хорошего отдыха, [npc.name] в данный момент [npc.verb(feel)] полон энергии и бодрости.");
				} else {
					return UtilText.parse(target, "Благодаря обновленной кровати императорского размера в комнате [npc.her], [npc.name] [npc.has] удалось очень комфортно отдохнуть, и теперь [npc.verb(feel)] полон энергии и бодрости.");
				}
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED_EXTRA = new AbstractStatusEffect(80,
			"хорошо отдохнувший (максимально усилено)",
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
						"Благодаря обновленной кровати императорского размера в комнате [npc.her] в сочетании со способностью [npc.her] лучше всего отдыхать, [npc.name] теперь [npc.verb(feel)] так, словно [npc.sheIs] переполняет энергия и бодрость.");
				
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
			"слегка переутомлен",
			"overworked1",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<>(Attribute.MANA_MAXIMUM, -10f)),
			Util.newArrayListOfValues(
					"[style.colourMinorBad(Ниже вероятность)] использовать рабские лаунжи",
					"Во время работы:",
					"[style.boldBad(-50%)] [style.colourAffection(Прирост привязанности)]",
					"[style.boldBad(-0.5)] [style.colourAffection(Привязанность/час)]",
					"[style.boldBad(-25%)] шанс получения [style.colourExperience(опыта)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Из-за того, что работы каждый день слишком много, [npc.name] иногда [npc.verb(find)] [npc.herself] чувствует легкую усталость.<br/> <i>(Получено при ежедневной выносливости от -1 до -9. Текущая ежедневная выносливость: [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
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
			"переутомление",
			"overworked2",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<>(Attribute.MANA_MAXIMUM, -25f)),
			Util.newArrayListOfValues(
					"[style.colourBad(Маловероятно)] использовать рабские лаунжи",
					"Во время работы:",
					"[style.boldBad(-80%)] [style.colourAffection(Прирост привязанности)]",
					"[style.boldBad(-1)] [style.colourAffection(Привязанность/час)]",
					"[style.boldBad(-50%)] шанс получения [style.colourExperience(опыта)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Из-за того, что каждый день приходится делать слишком много дел, [npc.name] начинает чувствовать сильную усталость.<br/> <i>(Получено при ежедневной выносливости от -10 до -19. Текущая ежедневная выносливость: [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
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
			"сильно переутомлен(а)",
			"overworked3",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<>(Attribute.MANA_MAXIMUM, -50f)),
			Util.newArrayListOfValues(
					"Будет ли [style.colourTerrible(never)] использовать рабские лаунжи",
					"Во время работы:",
					"[style.boldBad(-100%)] [style.colourAffection(Привязанность растет)]",
					"[style.boldBad(-2)] [style.colourAffection(Привязанность/час)]",
					"[style.boldBad(-75%)] [style.colourExperience(experience)] шанс получения")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Из-за того, что каждый день приходится делать слишком много дел, [npc.name] чувствует невероятную усталость.<br/> <i>(Получено при ежедневной выносливости -20 или меньше. Текущая ежедневная выносливость [style.colourBad(\", \")"+target.getDailySlaveJobStamina()+")])</i>");
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
			"усталость после тренировки",
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
				return UtilText.parse(target, "Поскольку [npc.nameIsFull] недавно тренировался, он чувствует сильную усталость и не сможет заниматься в зале, пока [npc.sheHas] не восстановится.");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect FATIGUED = new AbstractStatusEffect(80,
			"утомленный",
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
				return UtilText.parse(target, "После периода тяжелой работы [npc.nameIsFull] чувствует сильную усталость и ему потребуется время на восстановление...");
			} else {
				return "";
			}
		}
	};
	
	// Utility status effect to display text of companions leaving:
	public static AbstractStatusEffect COMPANIONS_LEAVING = new AbstractStatusEffect(80,
			"Спутники уходят",
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
			"Психоделический трип",
			"psychoactive",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("Открыт для <b style='color: " + PresetColour.PSYCHOACTIVE.toWebHexString() + ";'>Гипнотическое внушение</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Math.random()<=Util.getModifiedDropoffValue((secondsPassed/60)*0.0075f, 0.5f)) {
				
				if(target.getPsychoactiveFluidsIngested().isEmpty()) {
					return "<p>Вы теряете связь с реальностью, пытаясь преодолеть действие психоактивных веществ...</p><p><i>"
							+ UtilText.returnStringAtRandom(
									"Внезапно вы обнаруживаете себя снова в музее вашей тети Лили. Один из сотрудников музея хватает вас за [pc.hand] и затаскивает в пустую комнату. Оказавшись внутри, он падает на колени и, широко улыбаясь, подается вперед, чтобы осыпать влажными поцелуями ваши обнаженные гениталии...",
									"Внезапно вы оказываетесь лицом к лицу со своей тетей Лили; вы оба стоите в ее старой квартире. Хихикнув, она внезапно наклоняется и хватает вас за [pc.hand], увлекая за собой в спальню. Оказавшись внутри, она скидывает халат и делает шаг вперед, прижимаясь своими губами к вашим...",
									"Внезапно вы обнаруживаете, что стоите перед потрясающе красивым демоном. Она делает шаг вперед, прижимая свои обнаженные груди к вашему телу, а ее хвост змеей скользит вверх, потираясь о вашу пах. Издав похотливый стон, она прижимается губами к вашим...")
							+"</i></p><p>С коротким вдохом вы внезапно вырываетесь из галлюцинации. Покраснев, вы чувствуете, как по паху разливается жаждущий жар...</p>"
							+target.incrementLust(25, false);
				} else {
					List<AbstractFluidType> list = new ArrayList<>(target.getPsychoactiveFluidsIngested());
					AbstractFluidType fluid = list.get(Util.random.nextInt(list.size()));
					String npcName = UtilText.generateSingularDeterminer(fluid.getRace().getName(false))+" "+fluid.getRace().getName(false);
					switch(fluid.getBaseType()) {
						case CUM:
							return "<p>Вы теряете связь с реальностью, пытаясь преодолеть действие психоактивных веществ...</p><p><i>"
									+ UtilText.returnStringAtRandom(
											"Внезапно вы обнаруживаете себя снова в музее вашей тети Лили. Вы стоите на коленях перед одним из сотрудников музея, "+npcName+", пока ты жадно сосешь их член. Спустя всего несколько мгновений их ствол начинает дергаться, и ты издаешь восторженный стон, когда густая струя спермы изливается тебе в рот...",
											"Внезапно ты обнаруживаешь себя рядом со своей тетей Лили: вы обе стоите на коленях в спальне ее старой квартиры. Она довольно ухмыляется, наблюдая, как ты сосешь член "+fluid.getRace().getName(false)+" перед тобой. Когда толстый ствол у тебя во рту начинает дергаться, Лили подается вперед и прижимает твою голову, следя за тем, чтобы горячая струя спермы хлынула тебе прямо в глотку...",
											"Внезапно ты обнаруживаешь, что стоишь на коленях перед совершенно голой "+fluid.getRace().getName(false)+". Они делают шаг вперед, наклоняются и тянут твою голову на себя, силой втискивая свой твердый член тебе в рот. С отчаянным стоном они мгновенно начинают кончать, наполняя твой рот горячей спермой...")
									+"</i></p><p>С коротким вдохом вы внезапно вырываетесь из галлюцинации. Покраснев, вы чувствуете, как по паху разливается жаждущий жар...</p>"
									+target.incrementLust(25, false);
						case GIRLCUM:
							return "<p>Вы теряете связь с реальностью, пытаясь преодолеть действие психоактивных веществ...</p><p><i>"
									+ UtilText.returnStringAtRandom(
											"Внезапно вы обнаруживаете себя снова в музее вашей тети Лили. Вы стоите на коленях перед одним из сотрудников музея, "+npcName+", пока ты жадно лижешь и целуешь их влажную, сочащуюся киску. Спустя всего несколько мгновений их щелка начинает спазмировать и дергаться, и ты издаешь восторженный стон, слизывая каждую каплю их восхитительного девичьего сока...",
											"Внезапно ты обнаруживаешь себя рядом со своей тетей Лили: вы обе стоите на коленях в спальне ее старой квартиры. Она довольно ухмыляется, наблюдая, как ты лижешь и целуешь киску "+fluid.getRace().getName(false)+" перед тобой. Когда их киска начинает спазмировать и подергиваться, Лили подается вперед и толкает твою голову вперед, заставляя тебя слизывать каждую каплю их восхитительной девичьей спермы...",
											"Внезапно ты обнаруживаешь, что стоишь на коленях перед совершенно голой "+fluid.getRace().getName(false)+". Они делают шаг вперед, наклоняются, чтобы притянуть твою голову, и с силой прижимают свою сочащуюся влагу к твоему [pc.lips+]. С отчаянным стоном они мгновенно начинают кончать, грубо вжимая твое лицо в свою промежность и заставляя тебя слизывать каждую каплю их восхитительной девичьей спермы...")
									+"</i></p><p>С коротким вдохом вы внезапно вырываетесь из галлюцинации. Покраснев, вы чувствуете, как по паху разливается жаждущий жар...</p>"
									+target.incrementLust(25, false);
						case MILK:
							return "<p>Вы теряете связь с реальностью, пытаясь преодолеть действие психоактивных веществ...</p><p><i>"
									+ UtilText.returnStringAtRandom(
											"Внезапно ты обнаруживаешь себя снова в музее своей тети Лили. Ты сидишь на коленях у одного из сотрудников музея, "+npcName+", жадно целуя и сося их раздутые соски. Спустя всего несколько мгновений в твой рот начинает течь ровная струя молока, и ты издаешь восторженный стон, заглатывая эту восхитительную жидкость...",
											"Внезапно ты обнаруживаешь себя на коленях у своей тети Лили; вы вдвоем сидите на ее кровати в ее старой квартире. Она с восторгом смотрит на тебя сверху вниз, пока ты целуешь и сосешь ее раздутые соски. Спустя всего несколько мгновений в твой рот начинает течь ровная струя молока, и ты издаешь восторженный стон, заглатывая эту восхитительную жидкость...",
											"Внезапно ты обнаруживаешь, что сидишь на коленях у совершенно голой "+fluid.getRace().getName(false)+". Они наклоняются, чтобы прижать твою голову к своей огромной груди, прижимая один из своих раздутых сосков к твоему [pc.lips+]. С отчаянным стоном ты начинаешь целовать и сосать их набухшие соски, и всего через несколько мгновений в твой рот начинает течь ровная струя молока...")
									+"</i></p><p>С коротким вдохом вы внезапно вырываетесь из галлюцинации. Покраснев, вы чувствуете, как по паху разливается жаждущий жар...</p>"
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
				return "В данный момент ты чувствуешь себя очень странно... Перед твоим [pc.eyes] то и дело мелькают странные видения, и ты не совсем уверен в том, что тебя окружает...";
			} else {
				return UtilText.parse(target, "[npc.name] сейчас чувствует себя очень странно... Перед глазами [npc.her] [npc.eyes] то и дело вспыхивают странные видения, а [npc.she], кажется, не совсем уверен(а) в [npc.her] окружении...");
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
			"Недавно сделан массаж",
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
					"После недавнего массажа [npc.nameIsFull] чувствует себя невероятно расслабленным и гибким!");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SHOWER = new AbstractStatusEffect(80,
			"Недавно принят душ",
			"cleaned_shower",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.boldMinorGood(Удваивает)] скорость регенерации [style.colourHealth(здоровья)] и [style.colourMana(ауры)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"После того как [npc.name] недавно нашел время принять душ, он [npc.verb(feel)] освежился!");
		}
	};
	
	public static AbstractStatusEffect CLEANED_BATH = new AbstractStatusEffect(80,
			"Недавно искупался",
			"cleaned_bath",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
					new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.boldGood(Утраивает)] скорость регенерации [style.colourHealth(здоровья)] и [style.colourMana(ауры)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.clearMuskMarkers();
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Недавно уделив время отдыху в [npc.her] ванне, [npc.name] [npc.verb(feel)] освежился и восстановил силы.");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SPA = new AbstractStatusEffect(80,
			"Спа-процедура",
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
					"[style.boldExcellent(Учетверение)] скорости регенерации [style.colourHealth(здоровья)] и [style.colourMana(ауры)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.clearMuskMarkers();
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Недавно отдохнув в спа, [npc.name] [npc.verb(feel)] словно [npc.sheHas] родился заново!");
		}
	};
	
	public static AbstractStatusEffect LOLLIPOP_SUCKING = new AbstractStatusEffect(80,
			"сосет леденец",
			"lollipop",
			PresetColour.CLOTHING_PINK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] игриво сосет леденец, и каждый раз, когда кто-то смотрит на [npc.herHim], [npc.she] [npc.verb(purse)] [npc.her] [npc.lips] и [npc.verb(make)] демонстрирует, как она его целует.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SMOKING = new AbstractStatusEffect(80,
			"курение",
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
					"[npc.NameIsFull] сейчас курит сигарету. Каждый раз, когда [npc.she] [npc.verb(exhale)], перед лицом [npc.her] образуется легкое белое облачко, из-за чего в непосредственной близости от [npc.her] начинает сильно пахнуть горелой растительностью.");
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
			"недавно курил",
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
					"[npc.NameHasFull] недавно выкурил сигарету, что очевидно любому, кто подойдет слишком близко к [npc.herHim], так как от [npc.she] сильно [npc.verb(smell)] запахом горелой растительности.");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN = new AbstractStatusEffect(80,
			"недавно поел(а)",
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
					"[npc.NameHasFull] недавно что-то съел(а). Поскольку голод [npc.her] временно утолен, [npc.sheIs] чувствует удовлетворение и полон энергии.");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN_POOR = new AbstractStatusEffect(80,
			"недавно поел(а) всякой дряни",
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
					"[npc.NameHasFull] недавно съел(а) что-то, что в лучшем случае можно назвать вредной едой. Поскольку голод [npc.her] временно утолен, [npc.sheIs] чувствует себя чуть бодрее.");
		}
	};

	public static AbstractStatusEffect RECENTLY_EATEN_QUALITY = new AbstractStatusEffect(80,
			"недавно поел(а) изысканную еду",
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
					"[npc.NameHasFull] недавно съел(а) что-то исключительного качества. Поскольку голод [npc.her] временно утолен, а воспоминания о столь прекрасной трапезе все еще свежи в памяти [npc.her], [npc.sheIs] чувствует удовлетворение и полон энергии.");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED = new AbstractStatusEffect(80,
			"жажда утолена",
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
					"[npc.NameHasFull] недавно что-то выпил(а). Поскольку жажда [npc.her] временно утолена, [npc.sheIs] может сосредоточиться на текущей задаче.");
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
					"[npc.NameHasFull] недавно утолил [npc.her] жажду чем-то, что едва ли можно было назвать напитком. Поскольку [npc.her] жажда была временно утолена, [npc.sheIs] может немного лучше сосредоточиться на текущей задаче.");
		}
	};

	public static AbstractStatusEffect THIRST_QUENCHED_QUALITY = new AbstractStatusEffect(80,
			"жажда утолена (качество)",
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
					"[npc.NameHasFull] недавно выпил нечто исключительного качества. Поскольку [npc.her] жажда была временно утолена, а воспоминание о столь изысканном напитке все еще свежо в [npc.her] памяти, [npc.sheIs] может сосредоточиться и сконцентрироваться на текущей задаче.");
		}
	};
	
	public static AbstractStatusEffect DRUNK_1 = new AbstractStatusEffect(80,
			"Опьянение I — Подпитый",
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
			return UtilText.parse(target, "После недавнего употребления алкогольного напитка, [npc.nameIsFull] чувствует легкое опьянение...<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
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
			"Опьянение II — Веселый",
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
			return UtilText.parse(target, "После недавнего употребления алкогольного напитка, [npc.nameIsFull] чувствует себя весьма весело...<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
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
			"Опьянение III — Пьяный",
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
			return UtilText.parse(target, "После недавнего употребления алкогольного напитка, [npc.nameIsFull] вы чувствуете сильное опьянение...<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Вы явно пьяны, и кто-то может принять вас за легкую добычу.");
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
			"Опьянение IV — В стельку",
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
			return UtilText.parse(target, "После недавнего употребления алкогольного напитка, [npc.nameIsFull] вы чувствуете себя совершенно пьяным в зюзю...<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Вы явно пьяны, и кто-то может принять вас за легкую добычу.");
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
			"Опьянение V — В хлам",
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
			return UtilText.parse(target, "После недавнего употребления алкогольного напитка, [npc.nameIsFull] вы чувствуете себя абсолютно невменяемым...<br/>Опьянение: [style.colourAlcohol("+Units.round(target.getIntoxicationPercentage(), 1)+")]%");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Вы явно пьяны, и кто-то может принять вас за легкую добычу.");
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
			"зависимости",
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
								:" [style.boldArcane(Ломка!)]"));
			}
			
			return extraEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "В данный момент вы зависимы от "+Util.intToString(target.getAddictions().size())+(target.getAddictions().size()==1?" типа жидкости":" типов жидкостей")+"! Если не принимать дозу более 24 часов, у вас начнутся симптомы абстиненции. <i>Зависимости можно устранить с помощью '"+ItemType.ADDICTION_REMOVAL.getName(false)+"'.</i>";
				} else {
					return UtilText.parse(target,
							"[npc.NameIsFull] текущая зависимость от "+Util.intToString(target.getAddictions().size())+(target.getAddictions().size()==1?" типа жидкости":" типов жидкостей")+"! Если не принимать дозу более 24 часов, [npc.she] начнет страдать от синдрома отмены. <i>Избавиться от зависимости можно с помощью '"+ItemType.ADDICTION_REMOVAL.getName(false)+"'.</i>");
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
			"Легкая ломка",
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
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытывает легкую ломку от:"));
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
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: [style.boldArcane(усиливается при)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
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
			"Заметная ломка",
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
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытывает заметную ломку от:"));
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
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: [style.boldArcane(усиливается при)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
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
			"Сильная ломка",
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
					sb.append("Вы страдаете от сильной ломки:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] страдает от сильной ломки:"));
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
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: [style.boldArcane(усиливается при)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
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
			"Мучительная ломка",
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
					sb.append("Вы страдаете от мучительной ломки:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] страдает от мучительной ломки:"));
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
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: [style.boldArcane(усиливается при)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
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
			"Невыносимая ломка",
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
					sb.append("Вы испытываете сильную ломку от:");
				} else {
					sb.append(UtilText.parse(target, "[npc.NameIsFull] испытывает сильную ломку от:"));
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
			"Менопауза",
			"menopause",
			PresetColour.BASE_CRIMSON,
			false,
			null,
			Util.newArrayListOfValues("[style.colourBad(Полное бесплодие)]")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Поскольку [npc.nameHasFull] перешагнула порог пятидесяти двух лет, у неё наступила менопауза, а так как [npc.sheIsFull] не является демоном, [npc.sheHasFull] стала совершенно неспособной к деторождению.");
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
						"После недавнего незащищенного секса существует риск, что [npc.name] забеременеет! Поскольку Аркана ускоряет процесс вынашивания, [npc.she] узнает, что [npc.sheIs] беременна, всего через несколько часов...");
			} else {
				return UtilText.parse(target,
						"После недавнего незащищенного секса существует риск, что [npc.name] забеременеет!");
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
							sb.append("Последние несколько часов вы ощущаете легкий дискомфорт в теле своей [pc.legRace], который сопровождается постепенным вздутием нижней части живота. Сначала вы пытались списать это на несварение желудка, но, оглянувшись назад и взглянув на свое таврическое тело, вы больше не можете отрицать то, что инстинктивно знаете как истину. Вы беременны.");
						} else {
							sb.append("Последние несколько часов ваш живот постепенно раздувался. Процесс шел настолько медленно, что поначалу вы не замечали ничего необычного, но, когда вы взглянули на свой живот, ошибку совершить было невозможно. Вы беременны. Вы экспериментально начинаете поглаживать свой живот, тихонько ахая, когда осознание происходящего начинает постепенно доходить до вас.");
						}
					sb.append("</p>");
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>[pc.thought(Я... я беременна?<br/>...<br/>О боже мой! Да! <b>Я беременна!</b>)]</p><p>Невероятное счастье от того, что вы не только обнаружили свою беременность, но и то, что физические признаки проявились всего через несколько часов, накрывает вас, словно сокрушительная волна чистого экстаза."
								+ (Main.game.getPlayer().isTaur()
									?" Вы чувствуете, как на глаза наворачиваются слезы радости, когда смотрите через плечо на то, что, несомненно, является раздутым животом беременной [pc.legRace]."
									:" Вы чувствуете, как на глаза наворачиваются слезы радости, когда с любовью баюкаете свой раздувшийся живот.")
							+ "</p><p>[pc.thought(Это лучшее чувство на свете!<br/>"
										+ (Main.game.isPlotDiscovered()
											?"Что же мне теперь делать?!"
											:"Если бы только тетушка Лили была здесь, бьюсь об заклад, она бы так мной гордилась!")
										+ "<br/>Погоди! Конечно! <b>Lilaya!</b> Она тоже захочет это увидеть!)]</p>");
						
						sb.append("<p>");
							if(target.getBodyMaterial()==BodyMaterial.SLIME) {
								sb.append("Присмотревшись к своему раздутому, склизкому животу, ты внезапно осознаешь, что видишь "
											+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленькое ядро слайма,"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растущее "
											+ (target.hasVagina()
												?"там, где должно быть твое чрево."
												:"твой живот.")
										+ " Ты не можешь удержаться от восторженного писка счастья, видя, как твой "
											+(target.getPregnantLitter().getTotalLitterCount()==1?"ребенок":"дети")+" растут внутри тебя, и проводишь следующие несколько минут"
													+ (Main.game.getPlayer().isTaur()
														?" протягивая руку назад, чтобы погладить и потереть"
														:" поглаживание и потирание")
													+" твоего чудесно раздутого живота в состоянии абсолютного блаженства. Однако в конце концов ты решаешь, что тебе, пожалуй, стоит пойти повидаться с Lilaya, чтобы она помогла тебе разобраться во всех тонкостях родов.");
							} else {
								sb.append("Спустя некоторое время "
										+(Main.game.getPlayer().isTaur()
											?" протягивая руку назад, чтобы погладить и потереть"
											:" поглаживание и потирание")
										+" твоего чудесно раздутого живота, ты начинаешь понемногу успокаиваться. Ты решаешь, что тебе, пожалуй, стоит пойти повидаться с Lilaya, чтобы она помогла тебе разобраться во всех тонкостях родов.");
							}
						sb.append("</p>");
						
					} else {
						sb.append("<p>[pc.thought(Я... я беременна?<br/>...<br/>О боже мой! <b>Я беременна!</b>)]</p><p>Внезапный шок от того, что ты не только обнаружила свою беременность, но и что физические признаки проявились всего через несколько часов, обрушивается на тебя, словно удар кувалдой. Несмотря на все усилия, ты чувствуешь, как начинаешь часто и прерывисто дышать, расхаживая кругами,"
									+ (Main.game.getPlayer().isTaur()
											?" сжимая голову руками в [pc.hands]."
											:", то обхватив голову, то прижимая руки к животу.")
								+ "</p><p>[pc.thought(Что мне делать? Что мне делать? Что мне делать?<br/>"
											+ (Main.game.isPlotDiscovered()
												?"Что же мне теперь делать?!"
												:"Если бы только тетушка Лили была здесь, бьюсь об заклад, она бы так мной гордилась!")
											+ "<br/>Погоди! Конечно! <b>Lilaya!</b> Она будет знать, что делать!)]</p>");
						
						sb.append("<p>");
							if(target.getBodyMaterial()==BodyMaterial.SLIME) {
								sb.append("В последний раз взглянув на свой раздутый, склизкий живот, ты внезапно понимаешь, что видишь "
												+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленькое ядро слайма,"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растущее "
												+ (target.hasVagina()?"там, где должно быть твое чрево.":"твой живот.")
											+ " Ты не можешь сдержать шокированного крика, когда видишь свой "+(target.getPregnantLitter().getTotalLitterCount()==1?"ребенок":"дети")
												+" растут внутри тебя, и проводишь следующие несколько минут "
													+ (Main.game.getPlayer().isTaur()
														?" протягивая руку назад, чтобы погладить и потереть"
														:" поглаживание и потирание")
												+" свой раздутый живот в состоянии оцепенелого панического ужаса. Однако со временем ты начинаешь понемногу успокаиваться и решаешь, что тебе, пожалуй, стоит как можно скорее отправиться к Lilaya.");
							} else {
								sb.append("Ты начинаешь понемногу успокаиваться, когда первый шок начинает проходить. Если кто и знает, что делать, так это Lilaya.");
							}
						sb.append("</p>");
					}
					
				} else {
					sb.append("<p>");
						if(Main.game.getPlayer().isTaur()) {
							sb.append("Последние несколько часов ты ощущаешь знакомое чувство легкого дискомфорта в теле своего [pc.legRace], которое сопровождается постепенным вздутием нижней части живота. Сначала ты не был до конца уверен, что вызывает этот дискомфорт, но, оглянувшись и взглянув на свое таурическое тело, ты инстинктивно понимаешь причину. Ты снова беременна.");
						} else {
							sb.append("Последние несколько часов твой живот постепенно раздувался. Процесс шел так медленно, что поначалу ты не замечал ничего необычного, но, взглянув на свой живот, ты не можешь ошибиться. Ты снова беременна. Ты начинаешь экспериментально поглаживать свой живот, тихонько ахая, когда осознание происходящего начинает доходить до тебя.");
						}
					sb.append("</p>");
					
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sb.append("<p>");
							sb.append("Присмотревшись повнимательнее к своему раздутому, склизкому животу, ты видишь "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленькое ядро слайма,"
											+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растущее "+ (target.hasVagina()?"там, где должно быть твое чрево.":"твой живот."));
							if(!target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
								sb.append(" Вы невольно издаете тихий счастливый вздох, глядя на свой "+(target.getPregnantLitter().getTotalLitterCount()==1?"ребенок":"дети")
											+" растут внутри тебя, и проводишь следующие несколько минут "
												+(Main.game.getPlayer().isTaur()
													?" протягивая руку назад, чтобы погладить и потереть"
													:" поглаживание и потирание")
											+" ваш раздутый живот, пребывающий в состоянии материнского блаженства.");
							}
						sb.append("</p>");
					}
					
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>[pc.thought(Ха-ха! Да! Я снова забеременела! Это лучшее чувство на свете...)]</p><p>Понимая, что вас ждет, вы издаете восторженный смешок и продолжаете свой путь.</p>");
					} else if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
						sb.append("<p>[pc.thought(Нет! Не могу поверить, что снова забеременела... Как же я это <b>ненавижу</b>...)]</p><p>Понимая, что вас ждет, вы раздраженно цокаете языком и проклинаете тот факт, что беременны, после чего продолжаете свой путь.</p>");
					} else {
						sb.append("<p>[pc.thought(Ммм... Похоже, я снова забеременела...)]</p><p>Понимая, что вас ждет, вы издаете удовлетворенный вздох и отправляетесь дальше.</p>");
					}
					
				}
				
				sb.append( "<p style='text-align:center;'>"
						+ "<b style='color:"+ PresetColour.GENERIC_SEX.toWebHexString() + ";'>Вы беременны!</b></p>");
				
				// Remove cum inflation:
				StringBuilder sbFluidDrain = new StringBuilder();
				float fluidAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
				boolean retention = target.hasCreampieRetentionArea(SexAreaOrifice.VAGINA);
				if(retention) {
					fluidAmount = (int)(fluidAmount-Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT);
					if(fluidAmount>0) {
						target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, fluidAmount);
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Растущий живот заставляет ваше тело преобразовывать [style.fluid("+fluidAmount+")] спермы в твоей киске в еще больше слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Растущий живот заставляет твое тело источать [style.fluid("+fluidAmount+")] спермы в твоей киске.)]");
						}
					}
				} else if(fluidAmount>0) {
					target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, -fluidAmount);
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sbFluidDrain.append("[style.italicsSex(Из-за раздувающегося живота твое тело превращает всю сперму в твоей киске в еще больше слайма.)]");
					} else {
						sbFluidDrain.append("[style.italicsSex(Из-за раздувающегося живота твое тело извергает всю сперму из твоей киски.)]");
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
							sbFluidDrain.append("[style.italicsSex(Твое тело также превращает [style.fluid("+fluidAmount+")] спермы в мочеиспускательный канал твоей киски в еще больше слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Your body also expels [style.fluid("+fluidAmount+")] спермы в уретре твоей киски.)]");
						}
					} else {
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Растущий живот заставляет ваше тело преобразовывать [style.fluid("+fluidAmount+")] спермы в мочеиспускательный канал твоей киски в еще больше слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Растущий живот заставляет твое тело источать [style.fluid("+fluidAmount+")] спермы в уретре твоей киски.)]");
						}
					}
					
				} else if(fluidAmountUrethra>0) {
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, -fluidAmountUrethra);
					if(sbFluidDrain.length()>0) {
						sbFluidDrain.append("<br/>");
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Твое тело также превращает всю сперму в мочеиспускательном канале твоей киски в еще большее количество слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Твое тело также извергает всю сперму из мочеиспускательного канала твоей киски.)]");
						}
					} else {
						if(target.getBodyMaterial()==BodyMaterial.SLIME) {
							sbFluidDrain.append("[style.italicsSex(Из-за раздувающегося живота твое тело превращает всю сперму в твоей киске в еще больше слайма.)]");
						} else {
							sbFluidDrain.append("[style.italicsSex(Из-за раздувающегося живота твое тело извергает всю сперму из твоей киски.)]");
						}
					}
				}
				if(sbFluidDrain.length()>0) {
					sb.append("<p style='text-align:center;'>");
						sb.append(sbFluidDrain.toString());
					sb.append("</p>");
				}
				sb.append("<p style='text-align:center;'>");
					sb.append("[style.italicsMinorBad(Твоя киска"+(Main.game.isUrethraEnabled()?" и её уретра":"")+" могут вместить лишь [style.fluid("+Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT+")] жидкости во время беременности!)]");
				sb.append("</p>");
				
			} else {
				target.endPregnancy(false);
				sb.append("<p>Прошло достаточно времени, чтобы ты могла убедиться, что всё позади. Твой живот не округлился,"+(target.getBodyMaterial()==BodyMaterial.SLIME?" внутри тебя не прощупываются ядра слаймов,":"")
								+" и ты понимаешь, что, несмотря на незащищенный секс, тебе удалось избежать беременности.</p><p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
								?"[pc.thought(Черт возьми...)]"
								:"[pc.thought(Ну, это облегчение...)]")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Ты не беременна!</b></p>");	
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
			"беременна",
			"pregnancy1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("-5% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из недавних сексуальных приключений, [npc.sheHas] забеременела! [npc.namePos]!"
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Сквозь [npc.skinColour] [npc.skin], составляющую [npc.her] тело, можно увидеть то, что "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленькое ядро слайма,"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растет внутри [npc.herHim]."
								:" Поскольку аркана ускоряет процесс вынашивания, [npc.she] перейдет к следующей стадии с пугающей скоростью."));
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
				sb.append("<p>Хотя изменения происходили постепенно, тебя внезапно настигает осознание того, что твой живот раздулся до огромных размеров."
							+ (target.isTaur()
								?" Ты оглядываешься через плечо и смотришь на огромную выпуклость в животе [pc.legRace], гадая, насколько же еще больше он станет."
								:" Ты не можешь удержаться от того, чтобы не погладить огромный живот, и гадаешь, насколько же еще больше он станет.")
							+ " Поскольку ты беременеешь впервые, ты не совсем понимаешь, чего ожидать, но тебя успокаивает мысль о том, что Lilaya всегда готова прийти на помощь.</p>");
			} else {
				sb.append("<p>Хотя изменения происходили постепенно, тебя внезапно настигает знакомое осознание: твой живот раздулся до огромных размеров.");
							if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
								sb.append((target.isTaur()
										?" Ты оглядываешься через плечо и с отвращением морщишься, глядя на огромный бугор в животе своей [pc.legRace], проклиная себя за то, что забеременела."
										:" С брезгливой гримасой на лице ты осторожно тычешь пальцем в огромный бугор в своем животе, проклиная себя за то, что забеременела."));
							} else {
								sb.append((target.isTaur()
										?" Ты оглядываешься через плечо и завороженно смотришь на огромный бугор в животе своей [pc.legRace], нежно улыбаясь утешительному чувству беременности."
										:" Ты не можешь удержаться от того, чтобы не погладить руками огромный бугор в своем животе, нежно улыбаясь этому приятному ощущению."));
							}
							sb.append(" Проходя через всё это раньше, ты знаешь, что тебе еще предстоит немало пройти, прежде чем ты будешь готова к родам.</p>");
			}

			if(target.getBodyMaterial()==BodyMaterial.SLIME) {
				sb.append("<p>Сквозь полупрозрачный слайм, из которого состоит твое тело, отчетливо видно, что "
						+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" ядро слайма"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растущее внутри тебя, стало намного больше...</p>");
			}
			sb.append("<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Теперь ты на позднем сроке беременности!</b></p>");
			
			if(breastGrowth) {
				sb.append("<p><i>Твоя грудь набухла и увеличилась в размере, так как твое тело готовится к лактации. Теперь у тебя [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-й":"") + " размер груди)]!</i></p>");
			}
			if(udderGrowth) {
				sb.append("<p><i>Ваши [pc.udders] набухли и увеличились в размерах, пока ваше тело готовится к началу лактации. Теперь у вас есть [style.boldSex([pc.udderSize]"  + (target.getBreastCrotchRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastCrotchSize().getCupSizeName()+"размер груди), ":"") + " [pc.udders])]!"
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
			"ты на позднем сроке беременности",
			"pregnancy2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues("-10% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NamePos] чрево заметно округлилось, ясно давая понять, что [npc.sheIs] на позднем сроке беременности."
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Сквозь [npc.skinColour] [npc.skin], составляющую [npc.her] тело, можно увидеть то, что "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" маленькое ядро слайма,"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растет внутри [npc.herHim]..."
								:" Из-за того, что аркана ускоряет процесс беременности, [npc.she] перейдет к финальной стадии с пугающей скоростью."));
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
					sb.append("К этому моменту беременный живот [pc.legRace] раздулся до огромных размеров, и ты отчетливо чувствуешь лишний вес в нижней части тела, когда [pc.walk] вокруг.");
				} else {
					sb.append("К этому моменту твой живот полностью раздулся перед тобой, и тебе приходится прогибать спину и опираться одной рукой, когда ты [pc.walk] вокруг.");
				}
				
				if(target.getBodyMaterial()==BodyMaterial.SLIME) {
					sb.append(" Отчетливо видимый сквозь полупрозрачный слайм, из которого состоит твое тело, ты замечаешь, что "
								+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" ядро слайма"
								+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" растущий внутри тебя "+(target.getPregnantLitter().getTotalLitterCount()==1?"имеет":"имеет")
								+" стал таким же огромным, как и твой собственный, и ты понимаешь, что теперь готова к родам.");
					
				} else if(target.isVaginaEggLayer()) {
					sb.append(" Хотя ты чувствуешь, как твердые скорлупки твоей кладки яиц давят на внутренние стенки матки, это ощущение не кажется тебе дискомфортным. Напротив, кажется, это лишь усиливает твои материнские инстинкты, и ты часто ловишь себя на том, что мечтаешь о том, как будешь откладывать и высиживать свои яйца.");
					
				} else {
					if(firstTimePregnancy) {
						sb.append(" В последние пару часов ты почувствовала странное шевеление в своем беременном животе, и, немного запаниковав, поняла, что это твое отпрыск копошится в утробе. Время от времени ты продолжаешь ощущать новые толчки и понимаешь, что уже готова к родам.");
					} else {
						sb.append(" В последние пару часов ты почувствовала знакомое шевеление в своем беременном животе, и, опираясь на опыт, мгновенно узнала, что это твой отпрыск копошится в утробе. Время от времени ты продолжаешь ощущать новые толчки и понимаешь, что уже готова к родам.");
					}
				}
			sb.append("</p>");
			
			sb.append("<p>");
				if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
					sb.append("[pc.thought(Мне действительно стоит пойти повидаться с Lilaya... Или, может, полежу так еще немного!)]");
				} else if(target.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					sb.append("[pc.thought(Наконец-то! Я смогу пойти к Lilaya и вытрясти "+(target.getPregnantLitter().getTotalLitterCount()==1?"этого мелкого":"этих мелких")+" из себя! Я <b>ненавижу</b> быть беременной!)]");
				} else {
					sb.append("[pc.thought(Мне действительно стоит пойти повидаться с Lilaya...)]");
				}
			sb.append("</p>");
			
			if(lactationIncrease) {
				sb.append("<p><i>");
					if(target.isTaur()) {
						sb.append("Твоя грудь заметно отяжелела, и когда ты экспериментально ощупываешь грудь, то чувствуешь, как на твоих распертых сосках проступают капли [pc.milk].");
					} else {
						sb.append("Твоя грудь заметно оттяжелела, и когда ты нежно гладишь округлый живот, то чувствуешь, как на твоих распертых сосках проступают капли [pc.milk].");
					}
					sb.append(" Теперь вы способны производить [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.milk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!");
				sb.append("</i></p>");
			}
			if(lactationUddersIncrease) {
				sb.append("<p><i>");
					sb.append("Ваши [pc.udders] заметно отяжелели, и при ходьбе вы чувствуете, как капли [pc.crotchMilk] выступают на ваших раздутых сосках. Теперь вы способны производить [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.crotchMilk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!");
				sb.append("</i></p>");
			}
			
			sb.append("<p style='text-align:center;'>"
						+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Вы готовы к родам!</b></p>");
			
			return sb.toString();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect PREGNANT_3 = new AbstractStatusEffect(80,
			"готова к родам",
			"pregnancy3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues("-15% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
							(target.isTaur()
								?"Живот [npc.NamePos] раздулся до колоссальных размеров, и любому, кто взглянет в сторону [npc.her], станет ясно, что [npc.sheIs] готова к родам."
								:"Живот [npc.NamePos] раздулся до колоссальных размеров, и [npc.sheIs] обнаруживает, что [npc.sheHasFull] приходится поддерживать [npc.her] спину одной рукой, пока [npc.she] [npc.verb(walk)].")
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Сквозь [npc.skinColour] [npc.skin], составляющую [npc.her] тело, можно увидеть то, что "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" полноценное ядро слайма"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+"."
								:"")
							+(target.isPlayer()
								?" Возможно, стоит навестить Lilaya..."
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
			"Инкубация яиц (Желудок)",
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
						"В результате одного из недавних сексуальных контактов [npc.namePos], желудок [npc.sheHas] наполнился [npc.her] яйцами! Благодаря эффектам арканы, эти яйца наверняка быстро созреют и перейдут на следующую стадию инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_STOMACH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>Тяжесть в животе стала заметно сильнее, и ты невольно задаешься вопросом: сколько еще времени пройдет, прежде чем ты будешь готова отложить яйца, которые созревают в твоем чреве...</p><p style='text-align:center;'>[style.boldSex(Яйца в твоем желудке увеличились в размерах!)]</p>");
			
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
			"Продвинутая инкубация яиц (Желудок)",
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
						"В результате одного из недавних сексуальных контактов [npc.namePos], [npc.sheHas] заполнила [npc.her] желудок яйцами! Благодаря эффектам арканы, эти яйца наверняка быстро созреют и перейдут к финальной стадии инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.ANUS, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>Судя по их значительному весу, вы уверены, что яйца в вашем желудке к этому моменту достигли полной зрелости и могут быть отложены и вылупиться в любое удобное для вас время.</p>");
			
			sb.append("<p style='text-align:center;'>[style.boldSex(Теперь вы готовы отложить яйца, которые инкубировались в вашем желудке!)]<br/>(Чтобы отложить яйца, откройте меню телефона и перейдите во вкладку «Яйца».)</p>");
			
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
			"Завершенная инкубация яиц (Желудок)",
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
			return UtilText.parse(target, "В результате одного из сексуальных контактов [npc.namePos], [npc.sheHas] заполнила [npc.her] желудок яйцами. Достигнув полной зрелости, они теперь готовы к откладыванию и вылуплению.");
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
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из недавних сексуальных контактов [npc.namePos], [npc.sheHas] заполнила [npc.her] [npc.breasts] яйцами! Благодаря эффектам арканы, эти яйца наверняка быстро созреют и перейдут к следующей стадии инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Тяжесть в груди стала заметно сильнее, и вы невольно задаетесь вопросом, сколько еще времени пройдет, прежде чем вы будете готовы отложить инкубирующиеся в них яйца...</p><p style='text-align:center;'>[style.boldSex(Яйца в вашей груди увеличились в размерах!)]</p>");
			
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
			"Продвинутая инкубация яиц (Грудь)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из недавних сексуальных контактов [npc.namePos], [npc.sheHas] [npc.her] [npc.breasts] наполнились яйцами! Благодаря эффектам арканы, эти яйца наверняка быстро созреют и перейдут на финальную стадию инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Судя по их значительному весу, вы уверены, что яйца в ваших [npc.breasts] уже достигли полной зрелости и могут быть отложены и вылуплены в любое удобное для вас время.</p>");
			
			sb.append("<p style='text-align:center;'>[style.boldSex(Теперь вы готовы отложить яйца, которые инкубировались в ваших [npc.breasts]!)]<br/>(Чтобы отложить яйца, откройте меню телефона и перейдите во вкладку «Яйца».)</p>");
			
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
			"Завершенная инкубация яиц (Грудь)",
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
			return UtilText.parse(target, "В результате одного из сексуальных контактов [npc.namePos], [npc.sheHas] [npc.her] [npc.breasts] наполнились яйцами. Достигнув полной зрелости, они теперь готовы к откладыванию и вылуплению.");
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
			"Инкубация яиц (Лобок-грудь)",
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
			return "Инкубация яиц (Лобок-грудь)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из недавних сексуальных контактов [npc.namePos], [npc.sheHas] [npc.her] [npc.crotchBoobs] наполнились яйцами! Благодаря эффектам арканы, эти яйца наверняка быстро созреют и перейдут на следующую стадию инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_CROTCH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Тяжесть в твоей [npc.crotchBoobs] стала заметно ощутимее, и ты невольно задаешься вопросом: как скоро ты будешь готова отложить яйца, которые там инкубируются...</p><p style='text-align:center;'>[style.boldSex(Яйца в твоей [npc.crotchBoobs] увеличились в размере!)]</p>");
			
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
			"Продвинутая инкубация яиц (Промежность-грудь)",
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
				return "Продвинутая инкубация яиц (Вымя)";
			}
			return "Продвинутая инкубация яиц (Промежность-грудь)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из недавних сексуальных контактов с [npc.namePos], [npc.sheHas] заполнила [npc.her] [npc.crotchBoobs] яйцами! Благодаря эффектам арканы, эти яйца наверняка быстро созреют и перейдут к финальной стадии инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE_CROTCH, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Судя по их значительному весу, ты уверена, что яйца в твоей [npc.crotchBoobs] к этому моменту полностью созрели и могут быть отложены и вылуплены в любое удобное для тебя время.</p>");
			
			sb.append("<p style='text-align:center;'>[style.boldSex(Теперь ты готова отложить яйца, которые инкубировались в твоей [npc.crotchBoobs]!)]<br/>(Чтобы отложить яйца, открой меню телефона и перейди во вкладку «Яйца».)</p>");
			
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
			"Завершенная инкубация яиц (Промежность-грудь)",
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
				return "Завершенная инкубация яиц (Вымя)";
			}
			return "Завершенная инкубация яиц (Промежность-грудь)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"В результате одного из сексуальных контактов с [npc.namePos], [npc.sheHas] получила [npc.her] [npc.crotchBoobs], наполненных яйцами. Достигнув полной зрелости, они теперь готовы к откладыванию и вылуплению.");
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
			"Инкубация яиц (Паутинный аппарат)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из недавних сексуальных контактов с [npc.namePos], [npc.sheHas] получила [npc.her] [npc.spinneret], наполненных яйцами! Благодаря эффектам Арканы, эти яйца наверняка быстро созреют и перейдут на следующую стадию инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_SPINNERET_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Тяжесть в твоих [npc.spinneret] стала заметно ощутимее, и ты невольно задаешься вопросом, сколько еще пройдет времени, прежде чем ты будешь готова отложить яйца, которые там инкубируются...</p><p style='text-align:center;'>[style.boldSex(Яйца в твоих [npc.spinneret] увеличились в размере!)]</p>");
			
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
			"Продвинутая инкубация яиц (Паутинный аппарат)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из недавних сексуальных контактов с [npc.namePos], [npc.sheHas] получила [npc.her] [npc.spinneret], наполненных яйцами! Благодаря эффектам Арканы, эти яйца наверняка быстро созреют и перейдут на финальную стадию инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.SPINNERET, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Судя по их значительному весу, ты уверена, что яйца в твоих [npc.spinneret] к этому моменту достигли полной зрелости и могут быть отложены и вылуплены в любое удобное для тебя время.</p>");
			
			sb.append("<p style='text-align:center;'>[style.boldSex(Теперь ты готова отложить яйца, которые созревали в твоей [npc.spinneret]!)]<br/>(Чтобы отложить яйца, открой меню телефона и перейди во вкладку «Яйца».)</p>");
			
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
			"Инкубация яиц завершена (Паутина)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"В результате одного из [npc.namePos] половых актов, [npc.sheHas] получила [npc.her] [npc.spinneret], наполненную яйцами. Достигнув полной зрелости, они теперь готовы к откладыванию и вылуплению.");
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
			"Инкубация яиц (Утроба)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Инкубация)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(яйцо"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"В результате одного из [npc.namePos] недавних половых актов, [npc.sheHas] получила [npc.her] утробу, наполненную яйцами! Благодаря эффектам Арканы, эти яйца наверняка быстро созреют и перейдут на следующую стадию инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_WOMB_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Тяжесть в твоей утробе стала заметно сильнее, и ты невольно задаешься вопросом, сколько еще времени пройдет, прежде чем ты будешь готова отложить созревающие там яйца...</p><p style='text-align:center;'>[style.boldSex(Яйца в твоей утробе увеличились в размерах!)]</p>");
			
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
			"Продвинутая инкубация яиц (Утроба)",
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
						"В результате одного из [npc.namePos] недавних половых актов, [npc.sheHas] получила [npc.her] утробу, наполненную яйцами! Благодаря эффектам Арканы, эти яйца наверняка быстро созреют и перейдут на финальную стадию инкубации всего за несколько дней.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.VAGINA, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>Судя по их значительному весу, ты уверена, что яйца в твоей утробе к этому моменту достигли полной зрелости и могут быть отложены и вылуплены в любое удобное для тебя время.</p>");
			
			sb.append("<p style='text-align:center;'>[style.boldSex(Теперь ты готова отложить яйца, которые созревали в твоей утробе!)]<br/>(Чтобы отложить яйца, открой меню телефона и перейди во вкладку «Яйца».)</p>");
			
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
			"Завершенная инкубация яиц (Утроба)",
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
			return UtilText.parse(target, "После одного из сексуальных контактов с [npc.namePos], чрево [npc.sheHas] наполнилось яйцами [npc.her]. Достигнув полной зрелости, теперь они готовы к тому, чтобы их отложили и они вылупились.");
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
			"фармацевтическая фертильность",
			"vixensVirility",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После приема «[#ITEM_innoxia_pills_fertility.getName(false)]» фертильность и либидо [npc.namePos] были временно повышены.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROMISCUITY_PILL = new AbstractStatusEffect(80,
			"фармацевтическая стерильность",
			"promiscuityPill",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, -100f),
					new Value<>(Attribute.VIRILITY, -100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "После приема «[#ITEM_innoxia_pills_sterility.getName(false)]» фертильность и либидо [npc.namePos] были значительно снижены. Это <b>профилактическая</b> мера, и она не изменит исход любого незащищенного секса, который [npc.she] имел(а) до приема таблетки!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROMISCUITY_PILL_PROLOGUE = new AbstractStatusEffect(80,
			"На таблетках",
			"promiscuityPill",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, -100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] находится под воздействием комбинированных оральных контрацептивов и поэтому не может забеременеть.");
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
					"[style.colourExcellent(Doubles)] зачатых потомков")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"После употребления '[#ITEM_innoxia_pills_broodmother.getName(false)]' фертильность и вирильность [npc.namePos] временно повышаются, и если [npc.she] оплодотворит кого-то или сам будет оплодотворен [npc.herself], [npc.she] зачать будет гораздо больше потомков, чем обычно!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect LUBE_PILL = new AbstractStatusEffect(80,
			"смазанное тело",
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
					"[style.colourWetness(Мгновенно выделяет)] предэякулят")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("[npc.NameHasFull] съел(а) '[#ITEM_innoxia_pills_lubrication.getName(false)]', и теперь во время секса ");
			if(target.hasPenisIgnoreDildo()) {
				sb.append("член [npc.her] начнет мгновенно выделять предэякулят, а отверстия [npc.her] будут сочиться естественной смазкой.");
			} else {
				sb.append("отверстия [npc.her] будут сочиться естественной смазкой.");
			}
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_PRODUCTION = new AbstractStatusEffect(80,
			"Выработка спермы",
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
			
			return UtilText.parse(target, "Яички [npc.NamePos] сейчас производят больше [npc.cum] со скоростью "+Units.fluid(cumRegenRate)+"/мин. В них накоплено "+Units.fluid(target.getPenisRawStoredCumValue())+"из максимума "+Units.fluid(target.getPenisRawCumStorageValue())+".");
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
			"Полные яички",
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
			
			return UtilText.parse(target, "Яички [npc.NamePos] полностью заполнены [npc.cum] ("+Units.fluid(target.getPenisRawCumStorageValue())+"), и [npc.she] не может дождаться, когда [npc.sheIs] сможет их опорожнить. [npc.She] эякулирует "+Units.fluid(target.getPenisRawOrgasmCumQuantity())+" во время оргазма, после чего [npc.cum] начнет восстанавливаться со скоростью "+Units.fluid(cumRegenRate)+"/мин.");
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
			"Выработка молока",
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
					"Грудь [npc.NamePos] вырабатывает [npc.milk] со скоростью "+Units.fluid(milkRegenRate)+"/мин., всего [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/минуту)] (как [npc.sheHasFull] [npc.totalBreasts] груди). Они накопили "+Units.fluid(target.getBreastRawStoredMilkValue())+"из максимума "+Units.fluid(target.getBreastRawMilkStorageValue())+".");
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
			"Наполненная грудь",
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
					"[npc.NamePos] [npc.breasts] наполнены "+Units.fluid(target.getBreastRawMilkStorageValue())+" [npc.milk].<br/>Они производят больше [npc.milk] со скоростью "+Units.fluid(milkRegenRate)+"/мин. на каждую, всего [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/минуту)] (как [npc.sheHasFull] [npc.totalBreasts] груди).");
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
			"Производство молока из лобка",
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
			return UtilText.parse(target, "Производство молока [npc.CrotchBoob]");
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
					"[npc.NamePos] [npc.crotchBoobs] производят [npc.crotchMilk] со скоростью "+Units.fluid(milkRegenRate)+"/мин. на каждую, всего [style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/мин)] (как [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs]). Они накопили "+Units.fluid(target.getBreastCrotchRawStoredMilkValue())+"из максимума "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+".");
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
			"Полные груди-молочницы",
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
			return UtilText.parse(target, "Полный [npc.CrotchBoobs]");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
		
			return UtilText.parse(target,
					"[npc.NamePos] [npc.crotchBoobs] наполнены "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+" [npc.crotchMilk].<br/> Они производят больше [npc.crotchMilk] с индивидуальной скоростью "+Units.fluid(milkRegenRate)+"/мин. на каждую, всего [style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/мин)] (как [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs]).");
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
			"Большие игрушки",
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
			
			Map<SexAreaOrifice, String> orifices = Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, "[style.boldVagina(Киска)]:"),
					new Value<>(SexAreaOrifice.ANUS, "[style.boldAsshole(Анус)]:"),
					new Value<>(SexAreaOrifice.MOUTH, "[style.boldMouth(Глотка)]:"),
					new Value<>(SexAreaOrifice.NIPPLE, "[style.boldNipple(Соски)]:"));
			List<SexAreaOrifice> orificesFound = new ArrayList<>();
			for(Entry<SexAreaOrifice, String> orifice : orifices.entrySet()) {
				if(target.getSexToyOrificeStretching().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeStretching().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					PenetrationGirth girth = PenetrationGirth.getGirthFromInt(clothing.getClothingType().getPenetrationSelfGirth());
					float diameter = Penis.getGenericDiameter(length, girth);
					
					effects.add(orifice.getValue());
					effects.add("[style.boldBad(Растяжение)] до [style.boldPinkLight("+Units.size(diameter)+")]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificePreventingStretchRecovery().containsKey(orifice.getKey())) {
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldMinorBad(Препятствует)] [style.boldPinkLight(восстановлению растяжения)]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificeTooDeep().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeTooDeep().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldTerrible(Слишком глубоко)] в [style.boldPinkLight("+Units.size(length)+")]");
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
						stretching.add("[style.colourAsshole(анус)]");
						break;
					case MOUTH:
						stretching.add("[style.colourMouth(глотку)]");
						break;
					case NIPPLE:
						stretching.add("[style.colourNipple(соски)]");
						break;
					case VAGINA:
						stretching.add("[style.colourVagina(вагину)]");
						break;
					default:
						break;
				}
			}
			List<String> preventingRecovery = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificePreventingStretchRecovery().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						preventingRecovery.add("[style.colourAsshole(анус)]");
						break;
					case MOUTH:
						preventingRecovery.add("[style.colourMouth(глотку)]");
						break;
					case NIPPLE:
						preventingRecovery.add("[style.colourNipple(соски)]");
						break;
					case VAGINA:
						preventingRecovery.add("[style.colourVagina(вагину)]");
						break;
					default:
						break;
				}
			}
			List<String> tooDeep = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeTooDeep().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						tooDeep.add("[style.colourAsshole(анус)]");
						break;
					case MOUTH:
						tooDeep.add("[style.colourMouth(глотку)]");
						break;
					case NIPPLE:
						tooDeep.add("[style.colourNipple(соски)]");
						break;
					case VAGINA:
						tooDeep.add("[style.colourVagina(вагину)]");
						break;
					default:
						break;
				}
			}
			
			boolean needBreak = false;
			if(!target.getSexToyOrificeStretching().isEmpty()) {
				boolean singular = target.getSexToyOrificeStretching().size()==1;
				descriptionSB.append("[npc.NamePos] "+(singular?"секс-игрушка":"секс-игрушки")+" слишком велики для [npc.herHim], и в результате, [npc.her] "
						+Util.stringsToStringList(stretching, false)+(singular?" является":" являются")+" [style.colourBad(растянутой)]!");
				needBreak = true;
			}
			
			if(!target.getSexToyOrificePreventingStretchRecovery().isEmpty()) {
				if(needBreak) {
					descriptionSB.append(" ");
				}
				boolean singular = target.getSexToyOrificePreventingStretchRecovery().size()==1;
				descriptionSB.append("[npc.NamePos] "+(singular?"секс-игрушка":"секс-игрушки")+" мешает [npc.her] [style.colourMinorBad(растянуться)] " +Util.stringsToStringList(preventingRecovery, false)+" и восстановиться!");
				needBreak = true;
			}
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				if(!target.getSexToyOrificeTooDeep().isEmpty()) {
					if(needBreak) {
						descriptionSB.append(" ");
					}
					boolean singular = target.getSexToyOrificeTooDeep().size()==1;
					descriptionSB.append((singular?"Секс-игрушка":"Секс-игрушки")+" вставленная в [npc.namePos] "
							+Util.stringsToStringList(tooDeep, false)+(singular?" является":" являются")+" [style.colourTerrible(неприятно глубоко)]!");
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
			"Восстановление влагалища",
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
				sb.append("Растянуто");
			} else {
				sb.append("Восстановление");
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				sb.append(" Влагалище");
				i++;
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				sb.append(" Анус");
				i++;
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				sb.append(" Соски");
				i++;
			}
			if (target.hasBreastsCrotch()
					&& target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				sb.append(" Соски на лобке");
				i++;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				sb.append(" Уретра пениса");
				i++;
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				sb.append(" Уретра влагалища");
				i++;
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				sb.append(" Глотка");
				i++;
			}
			
			if(i>1) {
				if(Main.game.isInSex()) {
					return "Растянутые отверстия";
				} else {
					return "Заживающие отверстия";
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
			
			return "Сужается "+Units.size(1)+" every "+(hours>=1
						?(hours)+" hour"+(hours==1?"":"s")+(minutes%60>0?" and "+(minutes%60)+" minute"+(minutes==1?"":"s"):"")
						:(minutes )+" minute"+(minutes==1?"":"s"));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> recoveringEffects = new ArrayList<>();

			Set<SexAreaOrifice> stretchRecoveryPrevention = new HashSet<>(target.getSexToyOrificePreventingStretchRecovery().keySet());
			stretchRecoveryPrevention.addAll(target.getSexToyOrificeStretching().keySet());
			
			String recoveringText = "recovering";
			String from1 = "От";
			String from2 = "to";
			
			if(Main.game.isInSex()) {
				recoveringText = "stretched";
				from1 = "До";
				from2 = "from";
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				recoveringEffects.add("[style.boldVagina(Влагалище "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getVaginaStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getVaginaRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.VAGINA)) {
					recoveringEffects.add("[style.boldBad(Секс-игрушка мешает восстановлению!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				recoveringEffects.add("[style.boldAsshole(Анус "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getAssStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getAssRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.ANUS)) {
					recoveringEffects.add("[style.boldBad(Секс-игрушка мешает восстановлению!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getAssPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				recoveringEffects.add("[style.boldNipples(Соски "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getNippleStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getNippleRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.NIPPLE)) {
					recoveringEffects.add("[style.boldBad(Секс-игрушка мешает восстановлению!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNipplePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				recoveringEffects.add("[style.boldMouth(Глотка "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getFaceStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getFaceRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.MOUTH)) {
					recoveringEffects.add("[style.boldBad(Секс-игрушка мешает восстановлению!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getFacePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.hasBreastsCrotch() && target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				recoveringEffects.add("[style.boldNipplesCrotch(Соски на лобке "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getNippleCrotchStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getNippleCrotchRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNippleCrotchPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				recoveringEffects.add("[style.boldPenisUrethra(Уретра члена "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getPenisStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getPenisRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getUrethraPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				recoveringEffects.add("[style.boldVaginaUrethra(Уретра влагалища "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getVaginaUrethraStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getVaginaUrethraRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaUrethraPlasticity().getRecoveryModifier())+")]");
			}
			
			
			return recoveringEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			
			StringBuilder descriptionSB = new StringBuilder("После того как её заставили принять");
			
			List<String> orificesRecovering = new ArrayList<>();
			boolean plural = false;
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				orificesRecovering.add("[style.boldVagina(вагину)]");
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				orificesRecovering.add("[style.boldAnus(анус)]");
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				orificesRecovering.add("[style.boldMouth(глотку)]");
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				orificesRecovering.add("[style.boldNipple(соски)]");
				plural = true;
			}
			if (target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				orificesRecovering.add("[style.boldNipplesCrotch(соски на лобке)]");
				plural = true;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				orificesRecovering.add("[style.boldPenisUrethra(уретру члена)]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				orificesRecovering.add("[style.boldVaginaUrethra(уретру влагалища)]");
			}
			if(orificesRecovering.size()==1) {
				descriptionSB.append(" объект, который был слишком велик для "+(plural?"их":"его")+", [npc.namePos] "+orificesRecovering.get(0)+" "+(plural?"имеет":"имеет")
						+" растянута, и "+(plural?"нужно":"нужно")+" некоторое время, чтобы восстановить всю "+(plural?"их":"его")+" естественную узкость.");
			} else {
				descriptionSB.append(" фаллические объекты, которые были для них слишком велики, [npc.namePos] "+Util.stringsToStringList(orificesRecovering, false)
						+" были растянуты, и им нужно некоторое время, чтобы восстановить всю их естественную узкость.");
			}

			if(Main.game.isInSex()) {
				descriptionSB.append(" [style.italicsBad(Слизистые не восстанавливаются во время секс-сцен!)]");
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
			"Залитая киской",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Пачкает одежду</b>")) {
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
					return "<p>Вся сперма из твоей залитой кремпи киски быстро впитывается твоей "+pump.getName()+"!"
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
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Кремпи)]", "[style.colourDirty(Пачкает)] "+clothingBlocking.getName()), false);
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
			String pregnancyText = (target.isVisiblyPregnant()?"<br/>[style.italicsSex(Из-за беременности максимальный объем составляет "+Units.fluid(Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.VAGINA)) {
				if(target.isPlayer()) {
					return "Когда ты идешь, ты чувствуешь сперму, запертую внутри твоей недавно использованной киски."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Закупоренная вагина:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.pussy] недавно была заполнена спермой, после чего была закупорена."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Закупоренная вагина:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "По мере того как ты идешь, ты чувствуешь, как сперма вытекает из твоей недавно использованной киски."
							+pregnancyText;
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] [npc.pussy] недавно была наполнена спермой."
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
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"Вагинальный уретральный кремпи",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Пачкает одежду</b>")) {
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
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Кремпи)]", "[style.colourDirty(Пачкает)] "+clothingBlocking.getName()), false);
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
			String pregnancyText = (target.isVisiblyPregnant()?"<br/>[style.italicsSex(Из-за беременности максимальный объем составляет "+Units.fluid(Body.MAXIMUM_CREAMPIE_WHILE_PREGNANT)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.URETHRA_VAGINA)) {
				if(target.isPlayer()) {
					return "По мере того как ты идешь, ты чувствуешь сперму, запертую внутри уретры твоей киски."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Забитая уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] вагинальная уретра недавно была наполнена спермой, после чего она оказалась закупорена."
							+pregnancyText
							+ "<br/>[style.boldTerrible(Забитая уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "На ходу ты чувствуешь, как сперма вытекает из мочеиспускательного канала твоей киски."
							+pregnancyText;
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] влагалищный мочеиспускательный канал недавно был наполнен спермой."
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
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"Крипи в уретру пениса",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Пачкает одежду</b>")) {
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
					return "<p>Вся сперма из уретры твоего члена, в которую залили крипи, быстро всасывается твоим "+pump.getName()+"!"
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
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Кремпи)]", "[style.colourDirty(Пачкает)] "+clothingBlocking.getName()), false);
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
					return "На ходу ты чувствуешь сперму, застрявшую в мочеиспускательном канале твоего члена.<br/>[style.boldTerrible(Закупоренная уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] мочеиспускательный канал пениса недавно был наполнен спермой, после чего был закупорен.<br/>[style.boldTerrible(Закупоренная уретра:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "На ходу ты чувствуешь, как сперма вытекает из мочеиспускательного канала твоего члена.";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] мочеиспускательный канал пениса недавно был наполнен спермой.");
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
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"Анальный крипи",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Пачкает одежду</b>")) {
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
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Кремпи)]", "[style.colourDirty(Пачкает)] "+clothingBlocking.getName()), false);
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
					return "На ходу ты чувствуешь сперму, застрявшую в твоем недавно использованном анусе.<br/>[style.boldTerrible(Закупоренный анус:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] анус недавно был наполнен спермой, а затем закупорен.<br/>[style.boldTerrible(Закупоренный анус:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "При ходьбе ты чувствуешь, как сперма вытекает из твоего недавно использованного ануса.";
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
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"Кримпи в соски",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Пачкает одежду</b>")) {
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
					return "<p>Вся сперма из твоих сосков после кримпи быстро всасывается твоим "+pump.getName()+"!"
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
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Кремпи)]", "[style.colourDirty(Пачкает)] "+clothingBlocking.getName()), false);
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
					return "При ходьбе ты чувствуешь сперму, запертую внутри твоего недавно использованного [pc.nipples].<br/>[style.boldTerrible(Закупоренные соски:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.nipples] недавно были наполнены спермой, а затем закупорены.<br/>[style.boldTerrible(Закупоренные соски:)] Сперма не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "При ходьбе ты чувствуешь, как сперма вытекает из твоего недавно использованного [pc.nipples].";
				} else {
					return UtilText.parse(target,
							"[npc.NamePos] [npc.nipples] недавно были наполнены спермой.");
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
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"Кримпи в соски",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Пачкает одежду</b>")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Кримпэй в соски вымени";
			} else {
				return "Кримпэй в соски промежности";
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
					return "<p>Вся сперма из твоих сосков после кримпи быстро всасывается твоим "+pump.getName()+"!"
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
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Кремпи)]", "[style.colourDirty(Пачкает)] "+clothingBlocking.getName()), false);
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
					return "На ходу ты чувствуешь, как сперма заперта внутри твоих недавно использованных [pc.crotchNipples].<br/>[style.boldTerrible(Забитые соски промежности:)] Ни капли не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!");
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.crotchNipples] недавно были наполнены спермой, а затем закупорены.<br/>[style.boldTerrible(Забитые соски промежности:)] Ни капли не вытекает"
							+ (retention?"!":", хотя она все еще впитывается!"));
				}
				
			} else {
				if(target.isPlayer()) {
					return "На ходу ты чувствуешь, как сперма вытекает из твоих недавно использованных [pc.crotchNipples].";
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
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"Спермоядный обед",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"Спермоядный обед"
					:"Вкусный обед";
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
						:"[npc.NameHasFull] недавно проглотил(а) немного сексуальной жидкости.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice area = SexAreaOrifice.MOUTH;
			float cumInArea = target.getTotalFluidInArea(area);
			float cumLost = area.getCharactersCumLossPerSecond(target) * 60;
			float absorption = area.getCumAbsorptionPerSecond() * 60;
			boolean retention = target.hasCreampieRetentionArea(area);
			
			return new Value<>(retention?3:2,
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"Кремпай из паутины",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Пачкает одежду</b>")) {
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
						Main.game.addEvent(new EventLogEntry("[style.colourCum(Кремпи)]", "[style.colourDirty(Пачкает)] "+clothingBlocking.getName()), false);
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
				return "На ходу ты чувствуешь, как слюнявая сперма вытекает из твоего недавно использованного паучьего аппарата.";
			} else {
				return UtilText.parse(target, 
						"Паучий аппарат [npc.NamePos] недавно был наполнен спермой.");
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
					"Текущий кремпи: [style.colourSex("+Units.fluid(cumInArea)+")]"
					+(target.isOrificePlugged(area) && !retention
							?"<br/>-"+Units.fluid(absorption, ValueType.PRECISE)+"/мин"
							:"<br/>-"+(retention?"[style.colourExcellent(":"")+Units.fluid(cumLost, ValueType.PRECISE)+(retention?")]":"")+"/мин")
					+(retention
							?"<br/>[style.colourExcellent(Удерживающийся)] кремпи благодаря зачарованию!"
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
			"вздутый живот",
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
				return "После того как тебя наполнили приличным количеством "+Util.stringsToStringList(fluidNames, false)+", твой живот теперь немного вздулся. Из-за лишнего веса тебе стало чуть труднее двигаться.";
			} else {
				return UtilText.parse(target,
							"После того как тебя наполнили приличным количеством "+Util.stringsToStringList(fluidNames, false)+", живот [npc.namePos] теперь немного вздулся.");
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
			"раздутый живот",
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
				return "После того как тебя наполнили огромным количеством "+Util.stringsToStringList(fluidNames, false)+", твой живот заметно раздулся. Из-за значительного лишнего веса в желудке тебе становится труднее передвигаться.";
			} else {
				return UtilText.parse(target,
							"После того как тебя наполнили огромным количеством "+Util.stringsToStringList(fluidNames, false)+", живот [npc.namePos] заметно раздулся. Значительный лишний вес в желудке [npc.her] мешает [npc.her] двигаться.");
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
			"перераздутый живот",
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
				return "После того как тебя наполнили колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", твой живот теперь массивно раздут. Огромный лишний вес в твоем желудке делает каждое движение невероятно трудным.";
			} else {
				return UtilText.parse(target,
							"После того как тебя наполнили колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", живот [npc.namePos] теперь массивно раздут. Огромный лишний вес в желудке [npc.her] делает [npc.herHim] передвижение невероятно трудным.");
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
			"распухшая грудь",
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
				return "После того как тебя наполнили приличным количеством "+Util.stringsToStringList(fluidNames, false)+", твои [pc.breasts] теперь слегка распухли. Из-за лишнего веса тебе стало немного труднее передвигаться.";
			} else {
				return UtilText.parse(target,
							"После того как тебя наполнили приличным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] теперь слегка распухли.");
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
			"раздутая грудь",
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
				return "После того как тебя наполнили огромным количеством "+Util.stringsToStringList(fluidNames, false)+", твои [pc.breasts] теперь заметно раздулись. Значительный лишний вес в верхней части тела мешает тебе свободно двигаться.";
			} else {
				return UtilText.parse(target,
							"После того как тебя наполнили огромным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] теперь заметно раздулись. Огромный лишний вес в верхней части тела [npc.her] мешает [npc.her] передвигаться.");
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
			"перераздутая грудь",
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
				return "После того как тебя наполнили колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", твои [pc.breasts] теперь массивно перераздуты. Огромный лишний вес в верхней части тела делает твои передвижения чрезвычайно трудными.";
			} else {
				return UtilText.parse(target,
							"После того как тебя наполнили колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] теперь массивно перераздуты. Огромный лишний вес в верхней части тела [npc.her] делает [npc.herHim] передвижения чрезвычайно трудными.");
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
			"распухший паучий аппарат",
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
							"После того как тебя наполнили приличным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"являются":"является")+"теперь слегка распух(ла). Лишний вес мешает [npc.herHim] передвигаться.");
				} else {
					return UtilText.parse(target,
							"После того как тебя наполнили приличным количеством "+Util.stringsToStringList(fluidNames, false)+", брюшко [npc.namePos] теперь слегка раздуто. Из-за лишнего веса [npc.herHim] стало немного трудно передвигаться.");
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
			"раздутое паутинное веретено",
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
							"После того как тебя наполнили огромным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"являются":"является")+"теперь заметно раздуто. Лишний вес мешает [npc.her] двигаться.");
				} else {
					return UtilText.parse(target,
							"После того как тебя наполнили огромным количеством "+Util.stringsToStringList(fluidNames, false)+", брюшко [npc.namePos] теперь заметно раздуто. Лишний вес мешает [npc.her] двигаться.");
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
			"перераздутое паутинное веретено",
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
							"После того как тебя наполнили колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"являются":"является")+"теперь массивно перераздуто. Огромный лишний вес заставляет [npc.herHim] с огромным трудом передвигаться.");
				} else {
					return UtilText.parse(target,
							"После того как тебя наполнили колоссальным количеством "+Util.stringsToStringList(fluidNames, false)+", брюшко [npc.namePos] теперь массивно перераздуто. Огромный лишний вес заставляет [npc.herHim] с огромным трудом передвигаться.");
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
			"Раздражение",
			"frustrated",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] недавно вступила в сексуальную связь, в которой [npc.she] не удалось кончить. В результате [npc.sheIs] чувствует себя невероятно возбужденной и разочарованной...");
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
			"Сдерживаемая",
			"frustrated",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
				return UtilText.parse(target, "Как [npc.sheIs] нимфоманка, [npc.name] [npc.is] чувствует невероятное напряжение после того, как у нее не было никакого сексуального контакта более четырех часов...");
			}
			return UtilText.parse(target, "[npc.Name] [npc.has] не получала сексуальной разрядки уже больше суток, и [npc.is] чувствует невероятное напряжение...");
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
			"Отчаянно похотливая",
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
					"[npc.NameIsFull] в данный момент невероятно похотлива. [npc.SheIs] с трудом сдерживает свою [npc.her] похоть, поэтому [npc.are] гораздо более уязвима для соблазнения, но при этом сама может легче соблазнять других.");
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
			"Принудительное воздержание (спокойное)",
			"chastity1",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.colourSex(Интенсивность нарастает...)]")) {
		@Override
		public String getDescription(GameCharacter target) {//TODO if one key-holder, mention them
			return UtilText.parse(target, "[npc.Name] [npc.has] заперта в девственном поясе и рано или поздно начнет чувствовать разочарование от невозможности испытать полноценный оргазм...");
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
			"Принудительное воздержание (неспокойное)",
			"chastity2",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			Util.newArrayListOfValues("[style.colourSex(Интенсивность нарастает...)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] уже какое-то время находится в девственной девственности, и [npc.is] уже начинает чувствовать разочарование от невозможности получить полноценный оргазм...");
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
			"Принудительная девственность (накопившаяся)",
			"chastity3",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			Util.newArrayListOfValues("[style.colourSex(Интенсивность нарастает...)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] находится в девственной девственности уже долгое время, и [npc.is] чувствует сильное разочарование и сексуальное напряжение из-за невозможности получить полноценный оргазм...");
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
			"Принудительная девственность (отчаянная)",
			"chastity4",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f)),
			Util.newArrayListOfValues("[style.colourSex(Максимальная интенсивность)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] находится в девственной девственности целую вечность, и [npc.is] чувствует запредельное разочарование и нестерпимое напряжение из-за невозможности получить полноценный оргазм...");
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
			"Освобождение от девственности (неугомонная)",
			"chastityRemoved2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.RESTING_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Оргазм)] снимет этот эффект!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Недавно освободившись от недолгого заточения в целомудрии, [npc.name] [npc.is] теперь чувствует сильное возбуждение!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(Если [npc.sheIsFull] снова закроют в целомудрии, пока действует этот эффект, [npc.she] останется на стадии «неспокойствия»!)]");
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
			"Освобождение из целомудрия (накопленное желание)",
			"chastityRemoved3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.RESTING_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Оргазм)] снимет этот эффект!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Недавно освободившись от долгого заточения в целомудрии, [npc.name] [npc.is] теперь чувствует невероятное возбуждение!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(Если [npc.sheIsFull] снова закроют в целомудрии, пока действует этот эффект, [npc.she] останется на стадии «накопленного желания»!)]");
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
			"Освобождение из целомудрия (отчаяние)",
			"chastityRemoved4",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.RESTING_LUST, 15f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Оргазм)] снимет этот эффект!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Недавно освободившись от мучительно долгого заточения в целомудрии, [npc.name] [npc.is] теперь чувствует запредельное возбуждение!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(Если [npc.sheIsFull] снова закроется в целомудрии, пока действует этот эффект, [npc.she] останется в стадии «отчаяния»!)]");
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
			"Усиленная аура",
			"recoveringAura",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Из-за недавнего оргазма ваша арканная аура временно усилилась. В этом состоянии вы больше не будете получать арканную эссенцию при оргазме!";
			} else {
				return UtilText.parse(target, "Из-за недавнего оргазма арканная аура [npc.namePos] временно усилилась. Пока [npc.she] находится в этом состоянии, вы не будете получать арканную эссенцию, если [npc.she] испытает оргазм в вашем присутствии!");
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
			"обнажённый",
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
			return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", и [npc.she] [npc.verb(feel)] ужасно смущается, расхаживая в таком неприкрытом виде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Ваши обнажённые части тела привлекают всевозможные похотливые взгляды.");
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
			"обнажённый (дикие части)",
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
				return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", но как [npc.she] [npc.has] дикое тело, [npc.she] [npc.verb(feel)] так, словно такая открытость естественна.");
			} else {
				return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", но [npc.her] дикое тело устроено так, что они не выставлены напоказ.");
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
			"обнаженная (кукла)",
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
			return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", но как [npc.sheIs] кукла, [npc.sheIs] неспособная чувствовать стыд или смущение.");
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
			"обнаженная грудь",
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
				return "обнаженные соски";
			} else {
				return super.getName(target);
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", и [npc.she] [npc.verb(feel)] ужасно смущается, расхаживая в таком неприкрытом виде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Авантюрные налетчики</b><br/>Твоя обнаженная грудь притягивает всевозможные похотливые взгляды.");
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
			"обнажённый",
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
			return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", и [npc.she] [npc.verb(feel)] ужасно смущается, расхаживая в таком неприкрытом виде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Ваши обнажённые части тела привлекают всевозможные похотливые взгляды.");
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
			"эксгибиционистка",
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
			return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", и [npc.she] [npc.verb(feel)] невероятно сексуальна и полна сил, разгуливая в таком открытом виде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Ваши обнажённые части тела привлекают всевозможные похотливые взгляды.");
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
			"эксгибиционистка",
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
			return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", и [npc.she] [npc.verb(feel)] невероятно сексуальна и полна сил, разгуливая в таком открытом виде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Ваши обнажённые части тела привлекают всевозможные похотливые взгляды.");
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
			"эксгибиционистка",
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
			return UtilText.parse(target, "Одежда [npc.NamePos] не скрывает [npc.her] "+getExposedPartsNamesList(target)+", и [npc.she] [npc.verb(feel)] невероятно сексуальна и полна сил, разгуливая в таком открытом виде.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Нападающие-опортунисты</b><br/>Ваши обнажённые части тела привлекают всевозможные похотливые взгляды.");
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
			"Чистая девственница",
			"virginPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(represent)] идеальным воплощением чистого, праведного существа. [npc.Her] благородная осанка и добродетельный характер делают [npc.herHim] образцом всего ангельского и доброго в этом мире.");
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
			"'Чистая' девственница",
			"virginPureNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 10f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] словно [npc.sheIs] благородный и добродетельный идеал чистоты, но [npc.sheIs] также подозрительно рьяно доказывает, что порванная девственная плева не лишает человека статуса чистой девственницы...");
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
			"Чистая 'девственница'",
			"virginPureRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] словно [npc.sheIs] благородный и добродетельный идеал чистоты, но [npc.sheIs] также подозрительно рьяно утверждает, что целой девственной плевы технически достаточно, чтобы считать любого девственником...");
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
			"Потерявшая девственность",
			"virginBroken",
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Потеря [npc.her] драгоценной девственности сильно ударила по [npc.name], и теперь [npc.she] [npc.verb(see)] [npc.herself] не иначе как грязной шлюхой. [npc.She] теперь часто [npc.verb(fantasise)] о больших толстых хлыстах, вбивающихся глубоко в [npc.her] никчемную щель, оскверняя [npc.her] лоно их вонючей спермой...");
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
			"Похотливая дева",
			"virginLustyMaidenPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIs] более чем готова использовать [npc.her] задницу, рот, грудь и даже обещание [npc.her] киски, чтобы угодить [npc.her] партнерам, но [npc.she] никогда не позволит по-настоящему проникнуть в [npc.her] женское лоно и лишить [npc.herHim] [npc.her] драгоценной девственности!");
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
			"Похотливая «Дева»",
			"virginLustyMaidenNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] так, словно [npc.her] киска совершенно нетронута и неприступна, но [npc.sheIs] также подозрительно страстно доказывает, что разорванная девственная плева не лишает человека статуса чистой девственницы...");
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
			"Похотливая «Дева»",
			"virginLustyMaidenRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] так, словно [npc.her] киска совершенно нетронута и неприступна, но [npc.sheIs] также подозрительно страстно доказывает, что наличия целой плевы достаточно, чтобы технически считаться девственницей...");
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
			"Опозоренная дева",
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
					"Потеря [npc.her] драгоценной девственности сильно ударила по [npc.name], и теперь [npc.she] [npc.verb(see)] [npc.herself] не иначе как грязной шлюхой. Все [npc.her] усилия сохранить чистоту [npc.her] киски, используя [npc.her] другие достоинства, были напрасны, и теперь всё, о чем [npc.she] мечтает — это использовать свою [npc.her] никчемную щелку как общественную свалку для спермы...");
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
			"Контролируемая агрессия",
			"res/perks/jobs/soldier",
			PresetColour.BASE_GREEN,
			true,
			null,
			Util.newArrayListOfValues("Ваш урон [style.boldExcellent(удвоен)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Благодаря [npc.her] военной подготовке, [npc.name] [npc.is] способна наносить удары с предельной агрессией в начале боя.");
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
				return UtilText.parse(target, "Надев полный наряд горничной, [npc.nameIsFUll], наполненная энергией [npc.she], [npc.verb(need)], чтобы стать сексуальной и трудолюбивой горничной.");
					
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
			"Профессиональная горничная",
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
				return UtilText.parse(target, "Полный наряд горничной напомнил [npc.nameIsFull] об истинном призвании [npc.her] — быть исключительно талантливой (и сексуальной) горничной!");
				
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
			"Молочница",
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
				return UtilText.parse(target, "Надев полный наряд молочной девы, [npc.nameIsFull] наполнилась энергией [npc.she] [npc.verb(need)], чтобы исполнить все [npc.her] обязанности молочной девы.");
				
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
			"Профессиональная молочница",
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
				return UtilText.parse(target, "Полный наряд молочницы напомнил [npc.nameIsFull] об истинном призвании [npc.her] — быть исключительно талантливой (и сексуальной) молочницей.");
				
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
			"Дворецкий",
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
				return UtilText.parse(target, "Надев полный костюм дворецкого, [npc.nameIsFull], наполненная энергией [npc.she], [npc.verb(need)], чтобы исполнять свои [npc.her] обязанности дворецкого.");
					
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
			"Профессиональный дворецкий",
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
				return UtilText.parse(target, "Надев полный костюм дворецкого, [npc.nameIsFull] напомнила о [npc.her] истинном призвании; о призвании исключительно талантливого дворецкого!");
					
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
			"Arcane Ведьма",
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
					return "Надев полный комплект ведьминской одежды, вы чувствуете, как ваша сила Arcane становится всё мощнее.";
					
				} else {
					return UtilText.parse(target, "Надев полный комплект ведьминской одежды, Arcane сила [npc.namePos] стала сильнее.");
					
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
			"Ученый",
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
					return "Надев лабораторный халат и защитные очки, вы уверены, что никакой пролив химикатов вам не страшен!";
					
				} else {
					return UtilText.parse(target, "Благодаря лабораторному халату и защитным очкам, [npc.name] отлично защищена от любых химических разливов.");
					
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
			"Шаловливый Enforcer",
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
				return UtilText.parse(target, "[npc.NameIsFull] одета в вызывающую праздничную версию униформы Enforcer, отчего [npc.herHim] чувствует себя невероятно сексуальной.");
					
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
			"Двойная радуга",
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
			"Темная сирена",
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
			return "<i>Тьма и хаос, пусть границы вечной пустоты будут сокрушены! Неосязаемое проявление божественной воли, да будет моя запечатанная мощь высвобождена!</i>";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_dark_siren").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_LYSSIETH_GUARD = new AbstractStatusEffect(70,
			"Страж Lyssieth",
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
			return UtilText.parse(target, "В форме стражницы Lyssieth, [npc.name] [npc.verb(feel)] так, словно [npc.sheIs] гораздо легче сохранять [npc.her] самообладание.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_lyssieth_guard").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_BDSM = new AbstractStatusEffect(70,
			"Связанная сучка",
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
					return UtilText.parse(target, "[npc.Name] [npc.has] была скована в БДСМ-атрибутике, и [npc.is] в полном восторге от того, что [npc.she] [npc.is] трудно двигаться.");
				} else {
					return UtilText.parse(target, "[npc.Name] [npc.has] была скована в БДСМ-атрибутике, и [npc.is] ей трудно двигаться.");
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
			"Скотина",
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
					return "На вас надет набор аксессуаров, которые обычно носят коровы или быки.";
					
				} else {
					return UtilText.parse(target, "[npc.NameIsFull] носит набор аксессуаров, которые обычно носят коровы или быки.");
					
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
			"Ледяная",
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
			return UtilText.parse(target, "Благодаря полному комплекту особых зачарованных украшений в виде снежинок, и аркана сила [npc.namePos], и атаки льдом стали гораздо мощнее!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_snowflake").isCharacterWearingCompleteSet(target);
		}
	};
		
	public static AbstractStatusEffect SET_SUN = new AbstractStatusEffect(70,
			"Сияющий",
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
			return UtilText.parse(target, "Благодаря драгоценностям, сверкающим подобно солнцу, [npc.nameIsFull] уверен, что [npc.her] заклинания и атаки огнем испепелят [npc.her] врагов!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_sun").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_GEISHA = new AbstractStatusEffect(70,
			"Гейша",
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
				return UtilText.parse(target, "[npc.NameIs] надевает церемониальный наряд ёко, который очень напоминает традиционную японскую одежду.");
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
			"Ронин",
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
					return "<i>Ты понимаешь, что существует нечто за пределами твоего «я», чему ты должен служить. И когда эта потребность исчезнет, когда вера умрет, кто ты тогда? [pc.A_man] без хозяина.</i>";
					
				} else {
					return UtilText.parse(target,
							"<i>[npc.Name] понимает, что существует нечто за пределами [npc.herself], чему нужно служить. А когда эта нужда исчезнет, когда вера умрет, чем будет [npc.she]? [npc.A_man] без господина.</i>");
					
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
			"Дайсё",
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
						"Японский термин «Дайсё», означающий «большое-малое», используется для описания одновременного ношения катаны («большой») и вакидзаси («малый»). Ношение обоих мечей сразу указывает на то, что владелец принадлежит к сословию самураев.");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_daisho").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_JOLNIR = new AbstractStatusEffect(70,
			"Йёльнир",
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
					return "Облачение в наряд «Йольского персонажа» значительно увеличивает как вашу мудрость, так и боевое мастерство!";
					
				} else {
					return UtilText.parse(target, "[npc.NameIsFull] носит наряд «Йольского персонажа», благодаря чему значительно повышает [npc.her] мудрость и боевое мастерство.");
					
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
			"Игривая кошечка",
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
				return UtilText.parse(target, "Надев полный комплект кошачьего белья, [npc.name] [npc.verb(find)] [npc.herself] желает всех дразнить [npc.she] [npc.verb(meet)]!");
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
			"Полная амуниция",
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
				return UtilText.parse(target, "Надев полный комплект амуниции, [npc.name] [npc.verb(find)] [npc.herself] чувствует себя сильнее обычного, а [npc.is] переполняет энергия!");
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
			"эффекты одежды",
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
			"эффекты зелий",
			"potionEffects",
			PresetColour.GENERIC_ARCANE,
			PresetColour.BASE_PINK_LIGHT,
			null,
			false,
			null,
			Util.newArrayListOfValues("[style.italicsMinorGood(Максимальные значения — 25)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasTrait(Perk.JOB_CHEF, true)) {
				return UtilText.parse(target, "Благодаря [npc.namePos] прошлому повара, [npc.sheIs] может в полной мере использовать потенциал множества наполненных Arcane расходников, встречающихся в этом мире...");
			}
			return UtilText.parse(target, "Наполненные Arcane расходники очень распространены в этом мире, и, как выяснил [npc.nameHasFull], они могут обладать довольно любопытными эффектами...");
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
				return Util.newArrayListOfValues("[style.italicsGood(Максимальные значения — 50)]");
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
			"счастье",
			"happinessFox",
			PresetColour.CLOTHING_SILVER,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f)),
			Util.newArrayListOfValues("[style.italicsGood(Счастье!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Серебристошерстная лисица, <i>Счастье</i>, следует за вами повсюду, куда бы вы ни пошли. Всякий раз, когда вы чувствуете усталость или уныние, это милое маленькое животное трется о ваши [pc.legs] и замирает, чтобы ему почесали ушки, что мгновенно наполняет вас радостью.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness);
		}
	};
	

	// COMBAT EFFECTS:

	public static AbstractStatusEffect SPECIAL_SILENCE_TRANCE = new AbstractStatusEffect(70,
			"Транс Сайленс",
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
			return "Увидев поражение своей любимой подруги, Сайленс призвала элементаля воздуха и впала в состояние глубокого транса, что дарует ей улучшенные боевые способности!";
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
			"Ярость Шэдоу",
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
			return "Увидев поражение своей любимой подруги, Шэдоу впала в состояние неистовой берсеркской ярости, что дарует ей колоссально улучшенные боевые способности!";
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
			"Ослепленная свободой",
			"american_freedom",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] изо всех сил пытается бороться с вашей предначертанной судьбой!");
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
			"скрыто",
			"combatHidden",
			PresetColour.GENERIC_BAD,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Вы не знаете, какие перки, эффекты статуса, заклинания или особые атаки доступны вашему противнику. Вам требуется "+Perk.OBSERVANT.getName(target)+" перк, позволяющий раскрывать такую информацию.";
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DESPERATE_FOR_SEX = new AbstractStatusEffect(70,
			"отчаянно жаждет секса",
			"desperateForSex",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			PresetColour.ATTRIBUTE_LUST,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(Получаемый урон игнорирует все щиты)]",
					"Получаемый [style.colourLust(урон Похотью)] преобразуется в:",
					"[style.colourHealth(2*"+Attribute.HEALTH_MAXIMUM.getName()+" урон)] и [style.colourMana(1*"+Attribute.MANA_MAXIMUM.getName()+" урон)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Ты просто отчаянно жаждешь секса, но благодаря силе своей арканной ауры тебе удается не сдаться прямо здесь и сейчас!";
			} else {
				return UtilText.parse(target,
						"[npc.NameIsFull] просто отчаянно жаждет секса, но благодаря силе [npc.her] арканной ауры, [npc.sheIs] удается не сдаться прямо здесь и сейчас!");
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
			"арканная слабость",
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
			return UtilText.parse(target, "У [npc.NamePos] голова идет кругом, и [npc.sheIs] изо всех сил пытается удержаться на ногах...");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DAZED = new AbstractStatusEffect(10,
			"ошеломление",
			"dazed",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "У [npc.NamePos] кружится голова, и [npc.sheIs] с трудом удерживается на ногах. [npc.SheIs] невероятно сложно попасть по врагу или уклониться от входящих атак.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CRIPPLE = new AbstractStatusEffect(10,
			"хромота",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameHasFull] временно подогнанная, и [npc.sheIs] с трудом наносит [npc.her] атаками столько урона, сколько [npc.sheIs] обычно может.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect VULNERABLE = new AbstractStatusEffect(10,
			"уязвимость",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] чувствует себя особенно уязвимой, и [npc.she] [npc.is] не может защищать [npc.herself] в меру [npc.her] способностей.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED = new AbstractStatusEffect(10,
			"отравление",
			"combat_poisoned",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			null,
			Util.newArrayListOfValues("<b>15</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Яд в теле [npc.namePos] берет свое, и [npc.sheIs] неуклонно теряет [npc.her] сил!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED_LUST = new AbstractStatusEffect(10,
			"отравление похотью",
			"combat_poisoned",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues(
					"<b>5</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" каждый ход</b>",
					"<b>10</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 5);
			Value<String, Integer> lustDamageValue = DamageType.LUST.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey()
					 + UtilText.parse(target, "<br/>[npc.Name] дополнительно [npc.verb(take)] <b>" + lustDamageValue.getValue() + "</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+"!")+lustDamageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Яд похоти в теле [npc.namePos] берет свое: [npc.sheIs] не только неуклонно теряет [npc.her] силы, но и в процессе становится совершенно неуправляемо возбужденным!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect INKY_ATTACK = new AbstractStatusEffect(10,
			"Любовник Инки",
			"inky_summon",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues(
					"<b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> lustDamageValue = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "<br/>[npc.Name] [npc.verb(take)] <b>" + lustDamageValue.getValue() + "</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+"!")+lustDamageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Инка ласкает тело [npc.namePos] своими щупальцами, и, кажется, наслаждается каждой секундой!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect WITCH_SEAL = new AbstractStatusEffect(10,
			"Печать Ведьмы",
			"combat_witch_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -3f)),
			Util.newArrayListOfValues("[style.colourTerrible(Попытка побега невозможна!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Мощная арканная печать крепко удерживает [npc.name] на месте, не давая [npc.herHim] предпринять никаких действий!");
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
			"Очарование",
			"combat_witch_charm",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if (target.isPlayer()) {
				return "The <b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Очаровательный шарм</b> делает вас непреодолимо привлекательным!";
				
			} else {
				return UtilText.parse(target,
						"The <b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Очаровательный шарм</b> делает [npc.name] непреодолимо привлекательным!");
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				if(target.isFeminine()) {
					return "Аркана-чары очаровывают любого, кто посмотрит на вас, заставляя их видеть в вас прекраснейшее существо на свете.";
				} else {
					return "Аркана-чары очаровывают любого, кто посмотрит на вас, заставляя их видеть в вас прекраснейшего мужчину на свете.";
				}
				
			} else {
				if(target.isFeminine()) {
					return UtilText.parse(target, "Аркана-чары очаровывают вас, заставляя видеть в [npc.name] прекраснейшее существо на свете.");
				} else {
					return UtilText.parse(target, "Аркана-чары очаровывают вас, заставляя видеть в [npc.name] прекраснейшего мужчину на свете.");
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
			"Связан(а) веревками",
			"immobilised_rope",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Крепкие веревки, стянувшие тело [npc.namePos], намертво удерживают [npc.herHim] на месте, не давая [npc.herHim] совершить какое-либо действие!");
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
			"Скован цепями",
			"immobilised_chains",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Крепкие цепи, обвивающие тело [npc.namePos], намертво удерживают [npc.herHim] на месте, не давая [npc.herHim] совершить ни одного действия!");
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
			"Зажат в колодах",
			"immobilised_stocks",
			PresetColour.CLOTHING_DESATURATED_BROWN,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] зажат в колодах, из-за чего [npc.sheIs] не может совершить ни одного действия!");
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
			"Опутана паутиной",
			"restrain_webbed_1",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Густая, липкая паутина облепила тело [npc.namePos], из-за чего движения [npc.her] несколько затруднены!<br/>[style.italicsMinorBad(Если [npc.she] снова [npc.verb(get)] паутиной, этот эффект станет серьезнее!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect WEBBED_2 = new AbstractStatusEffect(10,
			"Сильно опутана паутиной",
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
				return UtilText.parse(target, "Огромное количество густой, липкой паутины облепило тело [npc.namePos], из-за чего движения [npc.her] значительно затруднены!<br/>[style.italicsBad(Если [npc.she] снова [npc.verb(get)] паутиной, этот эффект станет крайне серьезным!)]");
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
			"Закутано в кокон",
			"restrain_webbed_3",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Попытка побега невозможна!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Огромное количество густой, липкой паутины облепило тело [npc.namePos], из-за чего [npc.herHim] практически невозможно двигать [npc.her] [npc.arms] и [npc.legs]!<br/>[style.italicsBad(Если [npc.she] снова [npc.verb(get)] паутиной, [npc.she] будет мгновенно повержен!)]");
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
			"Закутано в кокон",
			"immobilised_cocoon",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Огромное количество густой, липкой паутины облепило тело [npc.namePos], из-за чего [npc.herHim] почти невозможно двигаться!");
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
			"Печать Ведьмы",
			"immobilised_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Мощная арканная печать крепко удерживает [npc.name] на месте, не давая [npc.herHim] предпринять никаких действий!");
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
			"Захвачен щупальцами",
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
				return UtilText.parse(target, "Мощные кольца щупалец вцепились в тело [npc.namePos], из-за чего движения [npc.her] заметно затруднены!<br/>[style.italicsMinorBad(Если [npc.she] снова [npc.verb(get)] захвачен щупальцами, этот эффект станет серьезнее!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_2 = new AbstractStatusEffect(10,
			"Объятый щупальцами",
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
			return UtilText.parse(target, "Сильные щупальца крепко обвились вокруг [npc.namePos] [npc.arms] и [npc.legs], из-за чего движения [npc.her] значительно затруднены!<br/>[style.italicsBad(Если [npc.she] снова [npc.verb(get)] захвачен щупальцами, этот эффект станет крайне серьезным!)]");
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
			"Сдавленный щупальцами",
			"restrain_tentacles_3",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Попытка побега невозможна!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Мощные щупальца сжали [npc.namePos] мертвой хваткой, из-за чего [npc.herHim] почти невозможно двигать [npc.her] [npc.arms] и [npc.legs]!<br/>[style.italicsBad(Если [npc.she] снова [npc.verb(get)] захвачен щупальцами, [npc.she] будет мгновенно повержен!)]");
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
			"Скрученная щупальцами",
			"immobilised_tentacles",
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.TENTACLE_RESTRICTION);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameIsFull] использует четыре [npc2.her] [npc2.tentacles], чтобы прижать [npc.name] к земле и крепко не дать [npc.herHim] пошевелиться!");
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
			"Захваченная хвостом",
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
				return UtilText.parse(target, "Мощный змееподобный хвост обвивает тело [npc.namePos], из-за чего движения [npc.her] заметно затруднены!<br/>[style.italicsMinorBad(Если [npc.she] снова [npc.verb(get)] хвостом, эффект станет серьезнее!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_2 = new AbstractStatusEffect(10,
			"Объятая хвостом",
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
			return UtilText.parse(target, "Мощный змееподобный хвост крепко обвился вокруг [npc.namePos], [npc.arms] и [npc.legs], из-за чего движения [npc.her] значительно затруднены!<br/>[style.italicsBad(Если [npc.she] снова [npc.verb(get)] хвостом, эффект станет крайне серьезным!)]");
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
			"Скрученная хвостом",
			"restrain_tail_3",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Попытка побега невозможна!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Мощный змееподобный хвост сжал [npc.namePos] мертвой хваткой, из-за чего [npc.herHim] почти невозможно двигать [npc.her], [npc.arms] и [npc.legs]!<br/>[style.italicsBad(Если [npc.she] снова [npc.verb(get)] хвостом, [npc.she] будет мгновенно повержен!)]");
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
			"Скрученная",
			"immobilised_tail",
			PresetColour.BASE_GREEN_DARK,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.TAIL_CONSTRICTION);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameIsFull] использует длинный и сильный хвост [npc2.her], чтобы сдавить [npc.name] и крепко не дать [npc.herHim] пошевелиться!");
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
			"Неподвижность",
			"immobilised_command",
			PresetColour.BASE_WHITE,
			PresetColour.BASE_RED,
			PresetColour.BASE_WHITE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Невозможно двигаться!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			GameCharacter applier = Main.sex.getImmobilisationTypes(target).get(ImmobilisationType.COMMAND);
			if(applier==null) {
				return "";
			}
			return UtilText.parse(target, applier, "[npc2.NameHasFull] приказал(а) [npc.name] оставаться совершенно неподвижным(ой), и в результате [npc.sheIsFull] полностью обездвижен(а)!");
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
			"Сон",
			"immobilised_sleep",
			PresetColour.SLEEP_HEAVY,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(Невозможно двигаться!)]",
					"Пробудится [style.colourBad(в спокойном темпе)], если партнер не будет действовать плавно, или если во рту произойдет проникновение")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] крепко спит, и пока секс остается нежным, [npc.she] не проснется!");
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
			"Пары Разлома",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue()+ "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Разлом продолжает извергать ядовитые испарения, заставляя [npc.name] кашлять и давиться каждый раз, когда [npc.she] [npc.verb(breathe)].");
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
			"Ожог Ауры",
			"melee_fire",
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("[style.boldFire(Огненные заклинания)] можно накладывать ценой "+Attribute.HEALTH_MAXIMUM.getName()+" когда аура отсутствует",
					"[style.boldGood(Не опустится "+Attribute.HEALTH_MAXIMUM.getName()+" ниже 1)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Благодаря сродству [npc.her] со школой Огня, [npc.nameIsFull] может пожертвовать частью [npc.her] "+Attribute.HEALTH_MAXIMUM.getName()+" чтобы творить огненные заклинания, когда [npc.sheIs] вне ауры.");
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
			"Тлеющие огни",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("<b>5</b> [style.boldFire(Огненный урон)] за ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.FIRE.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldFire(огненный урон)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Невидимые арканные огни лижут ваши ноги, и хотя они не причиняют вам сильной боли, вы всё равно невольно подпрыгиваете от дискомфорта.";
			} else {
				return UtilText.parse(target,
						"Невидимые арканные огни лижут ноги [npc.namePos], и хотя они не причиняют [npc.herHim] сильной боли, [npc.she] всё равно невольно подпрыгивает от дискомфорта.");
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
			"Ослеплен(а)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Ослепительная вспышка света временно дезориентировала [npc.name], и теперь [npc.sheIsFull] с трудом пытается вернуть контроль над [npc.her] чувствами!");
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
			"Ослепление (Вторичные искры)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"После первоначальной ослепительной вспышки света серия вторичных искр продолжает слепить [npc.name], из-за чего [npc.herHim] невероятно трудно совершать какие-либо действия!");
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
			"Плащ Огней",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] окутан плащом из арканного пламени, который увеличивает сопротивление [npc.her] огню и холоду!");
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
			"Плащ Огней (Воспламеняющий)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("Атаки без оружия наносят +1 ед. урона за каждый уровень заклинателя",
					"Атаки без оружия наносят [style.boldFire(урон огнем)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] окутан плащом из арканного пламени, который не только увеличивает сопротивления [npc.her], но и наделяет [npc.her] атаки без оружия уроном огня!");
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
			"Плащ Огней (Инферно)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("Атаки без оружия наносят +1 ед. урона за каждый уровень заклинателя",
					"Атаки без оружия наносят [style.boldFire(урон огнем)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"[npc.NameHasFull] окутан плащом из арканного пламени, который не только увеличивает сопротивление [npc.her] к огню и наносит огненный урон, но и наделяет [npc.her] безоружные атаки уроном от огня!");
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
			"Плащ пламени (Огненное кольцо)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f),
					new Value<>(Attribute.RESISTANCE_ICE, 20f)),
			Util.newArrayListOfValues("Атаки без оружия наносят +1 ед. урона за каждый уровень заклинателя",
					"Атаки без оружия наносят [style.boldFire(урон огнем)]",
					"Атакующие получают <b>5</b> [style.colourFire(урон огнем)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] окутан плащом из арканного пламени, который не только увеличивает сопротивление [npc.her] к огню и наносит огненный урон, но и наделяет [npc.her] безоружные атаки уроном от огня! Любые враги, достаточно глупые, чтобы ударить [npc.herHim] в ближнем бою, получают ответный урон огнем!");
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
			"Дикий огонь",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Огненный элементаль в группе [npc.namePos] наделяет [npc.herHim] знаниями о том, как извлечь максимум из атак огнем!");
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
			"Жгучее желание",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Огненный элементаль в группе противника наполняет [npc.name] жгучей похотью!");
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
			"Слуга огня",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
					"Присягнув на верность [npc.her] школе Огня, [npc.namePos] огненный элементаль, [npc2.name], теперь высасывает столько [npc.namePos] энергии, сколько пожелает [npc2.she]!");
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
			"Поглощение энергии",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Несоблазнительный урон)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] высасывает столько [npc2.namePos] энергии, сколько пожелает [npc.she], позволяя [npc.herHim] наносить огромный урон!");
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
			"Узы Огня",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Огня к воле [npc.her], [npc.namePos] огненный элементаль, [npc2.name], вынужден раскрыть все свои [npc2.her] секреты перед [npc2.her] [npc.mistress].");
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
			"Ледяной туман",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Ледяной туман, оставшийся после удара заклинания 'Ледяной осколок', окутывает [npc.name]. Арканный холод проникает в тело [npc.her], замедляя движения [npc.her] и затуманивая разум [npc.her].");
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
			"Заморожен",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Ледяной туман, окутывающий [npc.name], взорвался, покрыв [npc.herHim] тонким слоем льда и замедлив движения [npc.her]!");
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
			"Дождевое облако",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] зависло маленькое, наполненное арканой дождевое облако. Холодный дождь истощает способность [npc.her] эффективно творить заклинания.");
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
			"Дождевое облако (Глубокий холод)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] зависло маленькое, наполненное арканой дождевое облако. Ледяной дождь не только лишает [npc.her] способности эффективно творить заклинания, но и снижает [npc.her] сопротивление холоду.");
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
			"Дождевое облако (Ливень)",
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
					"Над головой [npc.namePos] зависло пропитанное арканой дождевое облако. Ледяной ливень истощает способность [npc.her] эффективно творить заклинания, а также снижает [npc.her] сопротивление холоду, из-за чего [npc.herself] становится труднее защищаться.");
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
			"Дождевое облако (Ливень)",
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
					"Над головой [npc.namePos] зависло пропитанное арканой дождевое облако. Ледяной ливень истощает способность [npc.her] эффективно творить заклинания, а также снижает [npc.her] сопротивление холоду, из-за чего [npc.herself] становится труднее защищаться.");
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
			"Дождевое облако (Проливной дождь)",
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
					"Над головой [npc.namePos] зависло пропитанное арканой дождевое облако. Проливной ледяной ливень истощает способность [npc.her] эффективно использовать заклинания, а также снижает [npc.her] сопротивление холоду и мешает защищать [npc.herself].");
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
			"Бьющие волны",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Водный элементаль из группы [npc.namePos] наделяет [npc.herHim] знаниями о том, как извлечь максимум пользы из ледяных атак!");
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
			"Спокойные воды",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Водный элементаль из группы [npc.namePos] помогает унять сексуальное желание [npc.her].");
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
			Util.newArrayListOfValues("-50% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Присягнув на верность [npc.her] школе Воды, [npc.namePos] водный элементаль, [npc2.name], теперь высасывает столько [npc.namePos] энергии, сколько пожелает [npc2.she]!");
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
			"Поглощение энергии",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Несоблазнительный урон)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] высасывает столько [npc2.namePos] энергии, сколько пожелает [npc.she], позволяя [npc.herHim] наносить огромный урон!");
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
			"Узы воды",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 25f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Воды к воле [npc.her], водный элементаль [npc2.name] из группы [npc.namePos] вынужден раскрывать все секреты [npc2.her] перед [npc2.her] [npc.mistress].");
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
			"Ядовитые испарения",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Облако ядовитых испарений продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)].");
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
			"Ядовитые пары (Удушливая дымка)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" каждый ход</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Облако ядовитых испарений продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)].");
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
			"Ядовитые пары (Аркана-болезнь)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" каждый ход</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldTerrible(истощение)] за ход</b>")) {
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
			return UtilText.parse(target, "Облако ядовитых испарений продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)].");
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
			"Ядовитые пары (Ослабляющее облако)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" каждый ход</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldTerrible(истощение)] за ход</b>")) {
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
			return UtilText.parse(target, "Облако ядовитых испарений продолжает витать вокруг тела [npc.namePos], заставляя [npc.herHim] кашлять и хрипеть каждый раз, когда [npc.she] [npc.verb(breathe)].");
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
			"Вакуум",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Устойчивая пустота в воздухе, которая смещается, чтобы оставаться рядом с телом [npc.namePos], нарушает равновесие [npc.her]!");
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
						"Постоянная пустота в воздухе, которая смещается, чтобы оставаться рядом с телом [npc.namePos], сбивает [npc.her] с ног! Вокруг [npc.herHim] также непрерывно возникают и схлопываются мелкие вторичные пустоты!");
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
			"Вакуум (Всасывание)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>10%</b> шанс каждый ход [style.boldExcellent(срывания)] одежды")) {
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
						return "Твоя "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" срывается и разлетается по полу!";
					} else {
						return "[npc.NamePos] "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" срывается и разлетается по полу!";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Мощная, устойчивая пустота смещается, чтобы оставаться рядом с телом [npc.namePos], и сбивает [npc.her] с ног! Вокруг [npc.herHim] также непрерывно возникают и схлопываются мелкие вторичные пустоты!");
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
			Util.newArrayListOfValues("<b>25%</b> шанс каждый ход [style.boldExcellent(срывания)] одежды")) {
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
						return "Твоя "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" срывается и разлетается по полу!";
					} else {
						return "[npc.NamePos] "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" срывается и разлетается по полу!";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"Непрерывный, невероятно мощный разлом пустоты смещается, чтобы оставаться рядом с телом [npc.namePos], из-за чего [npc.her] теряет равновесие! Вокруг [npc.herHim] также постоянно возникают и схлопываются мелкие вторичные пустоты!");
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
			"Защитные порывы",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Блаженный ветер призван, чтобы защитить [npc.name] от любых ядовитых испарений и помочь отражать атаки, направленные на [npc.herHim].");
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
			"Защитные порывы (Направляющий ветер)",
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
					"Блаженный ветер призван, чтобы защитить [npc.name] от любых ядовитых испарений и помочь отражать атаки, направленные на [npc.herHim]. Он также помогает направлять атаки [npc.her].");
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
			"Защитные порывы (Сфокусированный взрыв)",
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
					"Блаженный ветер призван, чтобы защитить [npc.name] от любых ядовитых испарений и помочь отражать атаки, направленные на [npc.herHim]. Он также помогает направлять и увеличивать мощь атак [npc.her].");
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
			"Вихрь",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Воздушный элементаль в группе врагов окружает [npc.name] шквальными ветрами!");
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
			"Оживляющие ароматы",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Элементаль Воздуха в группе [npc.namePos] окружает [npc.herHim] бодрящими ароматами, что дает [npc.herHim] энергию, чтобы уклоняться от атак и наносить мощные удары!");
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
						"Присягнув на верность [npc.her] школе Воздуха, [npc.namePos] элементаль воздуха, [npc2.name], теперь высасывает столько [npc.namePos] энергии, сколько пожелает [npc2.she]!");
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
			"Поглощение энергии",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Несоблазнительный урон)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] высасывает столько [npc2.namePos] энергии, сколько пожелает [npc.she], позволяя [npc.herHim] наносить огромный урон!");
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
			"Узы Воздуха",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 25f),
					new Value<>(Attribute.RESISTANCE_POISON, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Воздуха к [npc.her], [npc.namePos], стихийный элементаль воздуха, [npc2.name], будет вынужден раскрыть все секреты [npc2.her] перед [npc2.her] [npc.mistress].");
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
			"Дрожь земли",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Земля под ногами [npc.namePos] [npc.feet] дрожит и качается, из-за чего [npc.herHim] становится очень трудно уклоняться от входящих ударов.");
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
			"Дрожь земли (После толчка)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Земля под ногами [npc.namePos] [npc.feet] дрожит и качается, из-за чего [npc.herHim] становится очень трудно уклоняться от входящих ударов. [npc.She] чувствует, что приближается афтершок, но [npc.she] почти ничего не может с этим поделать...");
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
			"Телекинетический душ",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] непрерывно засыпает градом камней и других мелких предметов.");
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
			"Телекинетический ливень (Точные удары)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] непрерывно засыпает высокоточный град из камней и других мелких предметов.");
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
			"Телекинетический ливень (Невидимая сила)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>50</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 50);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] непрерывно засыпает высокоточный град из камней и других мелких предметов. За каждым ударом сразу следует взрывная волна силы.");
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
			"Каменный панцирь",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом удерживается плотный каменный барьер, дающий [npc.herHim] значительно улучшенную физическую защиту.");
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
			"Каменный панцирь (Зыбучие пески)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом удерживается плотный каменный барьер, дающий [npc.herHim] значительно улучшенную физическую защиту. Время от времени каменный барьер внезапно смещается и превращается в песок, после чего восстанавливается в другом месте.");
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
			"Каменный панцирь (Закаленная карапакс)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом воздвигнута прочная, закаленная каменная преграда, дарующая [npc.herHim] значительно улучшенную физическую защиту. Время от времени каменный барьер внезапно смещается и рассыпается в песок, после чего восстанавливается в другом месте.");
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
			"Каменная оболочка (Взрывной финал)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("[style.colourExcellent(Все враги)] получают <b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" когда действие Каменной оболочки заканчивается")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Между [npc.name] и [npc.her] врагом воздвигнута прочная, закаленная каменная преграда, дарующая [npc.herHim] значительно улучшенную физическую защиту. Время от времени каменный барьер внезапно смещается и рассыпается в песок, после чего восстанавливается в другом месте.");
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			StringBuilder sb = new StringBuilder();
			
			boolean first=true;
			for(GameCharacter combatant : Main.combat.getEnemies(target)) {
				Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(target, combatant, 10);
				sb.append(UtilText.parse(combatant,
						target, (first?"":"<br/>")+"<br/>[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" так как Каменная оболочка [npc2.namePos] взрывается!")
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
			"Катящийся камень",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Элементаль земли в группе [npc.namePos] усиливает атаки [npc.her] волнами силы!");
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
			"Закалка",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Элементаль земли в группе [npc.namePos] использует телекинетическую силу, чтобы окружить [npc.herHim] защитными осколками скалы!");
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
			Util.newArrayListOfValues("-50% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Присягнув на [npc.her] верность школе Земли, [npc.namePos] элементаль земли, [npc2.name], теперь высасывает столько [npc.namePos] энергии, сколько пожелает [npc2.she]!");
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
			"Поглощение энергии",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Несоблазнительный урон)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] высасывает столько [npc2.namePos] энергии, сколько пожелает [npc.she], позволяя [npc.herHim] наносить огромный урон!");
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
			"Узы Земли",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Земли к воле [npc.her], [npc.namePos] элементаль земли, [npc2.name], вынужден раскрывать все свои [npc2.her] секреты перед [npc2.her] [npc.mistress].");
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
			"Похотливое отвлечение",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Возбуждающие образы и мысли то и дело лезут в разум [npc.namePos], заставляя [npc.herHim] терять концентрацию на том, по чему [npc.sheIs] пытается попасть.");
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
			"Похотливое отвлечение (Грязные обещания)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Возбуждающие образы продолжают проникать в разум [npc.namePos], заставляя [npc.herHim] терять концентрацию на том, по чему [npc.sheIs] пытается попасть. [npc.She] [npc.verb(hear)] изредка призрачным шепотом в [npc.her] [npc.ear], обещая, что [npc.she] отлично проведет время, если [npc.she] просто подчинится.");
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
			"Телепатическая связь",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] может проецировать свои [npc.her] соблазнительные насмешки и [npc.moans+] прямо в разум человека!");
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
			"Проекция касания",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] способен проецировать [npc.her] соблазнительные насмешки и [npc.moans+] прямо в разум человека! В дополнение к этому, [npc.she] может осыпать [npc.her] цель призрачными поцелуями и ласками.");
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
			"Сила внушения",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			Util.newArrayListOfValues("[style.boldLust(Дразнит)] [style.boldExcellent(применяет)] -25 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")+" к цели на [style.boldGood(2 хода)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] способен проецировать [npc.her] соблазнительные насмешки и внушающие фразы прямо в разум человека! В дополнение к этому, [npc.she] может осыпать [npc.her] цель призрачными поцелуями и ласками.");
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
			"Сила внушения",
			"telepathic_communication_power_of_suggestion_targeted",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Телепатические стоны и внушения, проецируемые в разум [npc.namePos], заставляют [npc.herHim] ослабить [npc.her] бдительность!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	
	public static AbstractStatusEffect ARCANE_CLOUD = new AbstractStatusEffect(10,
			"Облако Arcane",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] парит небольшое облако Arcane. Под его воздействием возбуждающие эффекты Arcane проникают в разум [npc.her].");
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
			"Облако Arcane (Arcane Lightning)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(урон похоти)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] парит маленькое, пробуждающее похоть arcane облако. Время от времени в сторону [npc.herHim] бьет разряд arcane молнии, наполняя [npc.her] разум возбуждающими мыслями.");
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
			"Облако Arcane (Arcane Thunder)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(урон похоти)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] парит небольшое облако арканы, пробуждающее похоть. Время от времени вспышка арканной молнии, сопровождаемая негромким раскатом арканного грома, наполняет разум [npc.her] возбуждающими мыслями.");
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
			"Облако Arcane (Локальный шторм)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("[style.boldTerrible(Все члены отряда)] получают <b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" каждый ход</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			StringBuilder sb = new StringBuilder();
			
			List<GameCharacter> affectedCombatants = new ArrayList<>();
			affectedCombatants.add(target);
			affectedCombatants.addAll(Main.combat.getAllies(target));
			
			for(GameCharacter combatant : affectedCombatants) {
				Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, combatant, 15);
				
				sb.append(UtilText.parse(combatant, "<br/>[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(урон похоти)]!")+damageValue.getKey());
			}
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Над головой [npc.namePos] завис локальный шторм Арканы. Время от времени вспышка арканной молнии, сопровождаемая негромким раскатом арканного грома, наполняет разум [npc.her] возбуждающими мыслями.");
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
			"Похотливое подбадривание",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Арканный элементаль из группы [npc.namePos] проецирует в разум [npc.her] похотливые слова поддержки!");
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
			"Ласкающее прикосновение",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Арканный элементаль из вражеской группы проецирует призрачные щупальца, чтобы ласкать и ощупывать тело [npc.namePos]!");
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
			"Слуга Arcane",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Максимальное "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Присягнув [npc.her] на верность школе Arcane, [npc.namePos] элементаль Arcane, [npc2.name], теперь высасывает столько [npc.namePos] энергии, сколько пожелает [npc2.she]!");
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
			"Поглощение энергии",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Несоблазнительный урон)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.NameIsFull] высасывает столько [npc2.namePos] энергии, сколько пожелает [npc.she], позволяя [npc.herHim] наносить огромный урон!");
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
			"Узы Arcane",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Привязав школу Arcane к [npc.her], [npc.namePos], стихийный маг Arcane, [npc2.name], будет вынужден раскрыть все секреты [npc2.her] перед [npc2.her] [npc.mistress].");
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
			"Дуальность Arcane (Защита)",
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
						"Защитный барьер из арканной энергии окружил [npc.name], оберегая [npc.herHim] от всех видов входящего урона.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_DUALITY_NEGATIVE = new AbstractStatusEffect(10,
			"Дуальность Arcane (Слабость)",
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
						"Ослабляющий барьер из энергии Arcane окружил [npc.name], из-за чего [npc.herHim] становится более уязвимым ко всем видам входящего урона.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPORT = new AbstractStatusEffect(10,
			"Телепортация",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] телепортировался за спины [npc.her] врагов, так что им будет крайне сложно попасть по [npc.herHim]!");
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
			"Телепортация (Arcane Прибытие)",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" за ход случайному врагу")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			GameCharacter randomEnemy = Main.combat.getEnemies(target).get(Util.random.nextInt(Main.combat.getEnemies(target).size()));
			
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, randomEnemy, 15);

			return UtilText.parse(randomEnemy, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(урон похоти)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] телепортировался за спины [npc.her] врагов, так что им будет крайне сложно попасть по [npc.herHim]! Прибытие [npc.her] сопровождается всплеском пробуждающей похоть энергии Arcane!");
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
			"Надеть презерватив",
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
			modifiersList.add("-0.5% <b style='color: " + PresetColour.GENERIC_SEX.toWebHexString() + "'>возбуждение/ход</b>");
			return modifiersList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Из-за того, что на тебе презерватив, секс ощущается не так ярко...";
			} else {
				return "Из-за того, что на [npc.sheIs] презерватив, секс ощущается не так ярко для [npc.name]...";
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
			"Текучая вода",
			"sexEffects/flowing_water",
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.BASE_AQUA,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourDirty(Грязные части тела)] [style.colourAqua(быстро очищаются)]")) {
		public EffectBenefit getBeneficialStatus() {
			return EffectBenefit.NEUTRAL;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Вода, стекающая по телу [npc.namePos], не дает [npc.herHim] испачкаться!");
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
			"Желания",
			"desires",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Твои фетиши и желания влияют на то, сколько возбуждения ты получаешь от соответствующих сексуальных действий. Выбор действия с подходящим тебе фетишем также не будет увеличивать твою порочность.";
				
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
								?"Благодаря скрытой силе твоей арканной ауры, ты можешь чувствовать [npc.namePos] не нейтральные предпочтения к сексуальным действиям."
								:"Каким-то образом вы инстинктивно способны определять, какие [npc.namePos] не нейтральные предпочтения есть в отношении сексуальных действий.")
						+ "<br/>[style.italicsSex(Вы можете определить, какие зоны [npc.name] хочет использовать во время секса.)]");
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
										"[style.italics"+(Main.sex.isInForeplay(target)?"PinkLight(<b>Прелюдия</b>: ":"Disabled(Прелюдия: ")
											+ (foreplayPreference!=null
													?"[npc.Her] "+foreplayPreference.getPerformingSexArea().getName(target, true)+" и [npc2.namePos] "+foreplayPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
													:"[npc.She] [npc.has] без предпочтений...")
											+ ")]"));
				modList.add(UtilText.parse(target, targetedCharacter,
										"[style.italics"+(!Main.sex.isInForeplay(target)?"Pink(<b>Секс</b>: ":"Disabled(Секс: ")
											+ (mainPreference!=null
													?"[npc.Her] "+mainPreference.getPerformingSexArea().getName(target, true)+" и [npc2.namePos] "+mainPreference.getTargetedSexArea().getName(targetedCharacter, true)+"."
													:"[npc.She] [npc.has] без предпочтений...")
											+ ")]"));
				modList.add(UtilText.parse(target, targetedCharacter,
										Main.sex.isCharacterObeyingTarget(target, Main.game.getPlayer())
										?"[style.italicsMinorGood([npc.She] будет прислушиваться к твоим просьбам.)]"
										:"[style.italicsMinorBad([npc.She] будет игнорировать все твои просьбы.)]"));
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
			"Оргазмы",
			"sexEffects/orgasms",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			if(target.isPlayer()) {
				sb.append("Любой, кто обладает сильной аурой арканы, подобной твоей, не страдает от какого-либо рефрактерного периода после оргазма...");
				
			} else {
				sb.append("Любой, кто находится рядом с сильной аурой арканы, подобной твоей, не страдает от какого-либо рефрактерного периода после оргазма...");
			}
			
			sb.append("<br/>[npc.Name] [npc.verb(need)] до оргазма [style.boldSex(" + Util.intToCount(target.getOrgasmsBeforeSatisfied()) + ")] прежде чем [npc.sheIs] будет удовлетворен(а).");

			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			
			if(Main.sex.getNumberOfOrgasms(target)>=target.getOrgasmsBeforeSatisfied()) {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull] [style.colourExcellent(удовлетворен(а))].")));
			} else {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull] [style.colourTerrible(еще не удовлетворен(а))]!")));
			}
			
			if(!target.isAbleToOrgasm()) {
				additionalDescriptions.add(new Value<>(1, UtilText.parse(target, "[npc.NameIsFull] [style.colourTerrible(не может достичь оргазма)]!")));
			}
			
			int bonus = Main.sex.getNumberOfAdditionalOrgasms(target);
			if(!target.isPlayer() && bonus != 0) {
				if(bonus>0) {
					additionalDescriptions.add(
							new Value<>(2,
									UtilText.parse(target,
											"[npc.Her] желание было [style.boldExcellent(усилено)], поэтому цель — [style.boldGood(оргазм" + Util.intToString(bonus) + " оргазм" + (bonus==1?"":"s") + ")] выше нормы!")));
				} else {
					additionalDescriptions.add(
							new Value<>(2,
									UtilText.parse(target,
											"[npc.Her] желание было [style.boldTerrible(подавлено)], поэтому цель — [style.boldBad(оргазм" + Util.intToString(-bonus) + " оргазм" + (bonus==-1?"":"s") + ")] ниже нормы!")));
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
			
			modList.add("<b style='color:"+orgasmColour.toWebHexString()+";'>"+orgasms+"</b> Оргазм"+(orgasms==1?"":"s"));

			int essences = Main.sex.getEssenceGeneration(target);
			if(target.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
				modList.add("Принесет [style.boldBad(0 эссенций)]");
				modList.add("Вызвано [style.boldBad('"+RECOVERING_AURA.getName(target)+"')] эффект");
				
			} else {
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					modList.add("Генерирует [style.boldArcane(, "+(essences)+" эссенций)] после секса");
					modList.add("[style.boldExcellent(Удвоенный)] от [style.colourTrait(, "+Perk.NYMPHOMANIAC.getName(target)+" черты)]");
					
				} else {
					modList.add("Произведет [style.boldArcane(, "+(essences)+" эссенций)]");
				}
				
				if(orgasms>=5) {
					modList.add("[style.boldBad(Максимум эссенций достигнут)]");
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
			"Статус пениса",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ласкает свой собственный [npc.cock] [npc.her])]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] делает [npc2.name] [style.boldSex(дрилдинг)]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трется)] о два [npc.cocks(true)] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трется)] об [npc2.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [npc.herself] [style.boldSex(хвостом)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] делает [npc2.name] [style.boldSex(хвостом)]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [npc.herself] [style.boldSex(щупальцем)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] делает [npc2.name] [style.boldSex(щупальцем)]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [npc.herself] [style.boldSex(минет)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] делает [npc2.name] [style.boldSex(минет)]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трет клитором)] о собственную [npc.cock] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трет клитором)] об [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [npc.herself] [style.boldSex(фут-джоб)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] делает [npc2.name] [style.boldSex(фут-джоб)]!"));
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
			"Состояние клитора",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает пальцами)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает пальцами)] [npc2.name]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(дразнит)] собственную [npc.cock] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(дразнит)] [npc2.namePos] [npc2.cock]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-teasing)] [npc.her] собственная киска!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-teasing)] киска [npc2.namePos]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-teasing)] [npc.her] собственная киска!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-teasing)] киска [npc2.namePos]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(performing cunnilingus)] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(performing cunnilingus)] на [npc2.name]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(триббинг)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tribbing)] с [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(foot-teasing)] [npc.her] собственная киска!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(foot-teasing)] киска [npc2.namePos]!"));
								}
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
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
			"Статус ануса",
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
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(пальцами)] [npc.her] собственную [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(пальцами)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ебаная)] [npc.her] собственность [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахнуть сзади)] [npc.her] владей [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает хвостом)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] владеет [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает щупальцем)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex(анилингус)] [npc.herself]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" делает [style.boldSex(анилингус)] [npc.name]!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает клитор)] своей [npc.asshole] [npc.her]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает клитор)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(запихивает [npc.her] [npc.toes])] в свою [npc.asshole] [npc.her]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(запихивает [npc.her] [npc.toes])]"):" запихивают [style.boldSex(свои пальцы ног)]")
										+"  в [npc.namePos] [npc.asshole]!"));
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
								descriptionSB.append("[npc.NameIsFull] делает [style.boldSex(анилингус)] [npc.herself]!");
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" делает [style.boldSex(анилингус)] на [npc2.name]!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ебаная)] [npc.her] собственность [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает)] [npc.namePos] [npc.asshole]!"));
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
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
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
			"Статус задницы",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] сама [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(ласки)] [npc2.namePos] [npc2.ass]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(щупальце-хотдог)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(щупальце-хотдог)] [npc2.name]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.her] сама [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.namePos] [npc2.ass]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(клитор-хотдоггинг)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(клитор-хотдогинг)] [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] ласкает свою [npc.ass] с помощью [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.ass] с помощью [npc.her] [npc.feet]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.her] сама [npc.ass]!"));
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
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
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
			"Состояние рта",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] сосет свою [npc.fingers]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(сосание)] [npc.namePos] [npc.fingers]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [npc.herself] [style.boldSex(минет)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] делает [npc.name] [style.boldSex(минет)]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(сосет)] собственный [npc.tail] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(сосет)] [npc.namePos] [npc.tail]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] собственным щупальцем!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(сосет)] щупалец [npc.namePos]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целует)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(целует)] [npc.name]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(сосет)] собственный [npc.clit] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(сосет)] [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(облизывая)] [npc.her] собственный [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(облизывая)] [npc.namePos] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(облизывая)] [npc.her] собственный [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(облизывая)] [npc.namePos] [npc.armpits]!"));
								}
								break;
							case ANUS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex(анилингус)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] делает [style.boldSex(анилингус)] [npc.name]!"));
								}
								break;
							case ASS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(облизывая)] [npc.her] собственный [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(облизывая)] [npc.namePos] [npc.ass]!"));
								}
								break;
							case BREAST:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целуя)] [npc.her] собственный [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(целуя)] [npc.namePos] [npc.breasts]!"));
								}
								break;
							case NIPPLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(сося)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(сосет)] [npc.namePos] [npc.nipples]!"));
								}
								break;
							case BREAST_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владей [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(целует)] [npc.namePos] [npc.crotchBoobs]!"));
								}
								break;
							case NIPPLE_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(сосет)] [npc.her] собственный [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(сосет)] [npc.namePos] [npc.crotchNipples]!"));
								}
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целует)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(целует)] [npc.name]!"));
								}
								break;
							case THIGHS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владей [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(целует)] [npc.namePos] [npc.legs]!"));
								}
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [npc.herself] [style.boldSex(минет)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] делает [npc.name] [style.boldSex(минет)]!"));
								}
								break;
							case URETHRA_VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex(куннилингус)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] делает [style.boldSex(куннилингус)] [npc.name]!"));
								}
								break;
							case VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex(куннилингус)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] делает [style.boldSex(куннилингус)] [npc.name]!"));
								}
								break;
							case SPINNERET:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(орально)] ласкает собственный паучий аппарат [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(орально)] ласкает паучий аппарат [npc.namePos]!"));
								}
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "Рот [npc.Her]"), descriptionSB);
			
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
			"Состояние груди",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.hasBreasts()) {
				return "Состояние груди";
			} else {
				return "Состояние грудной клетки";
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(щупает)] собственный [npc.breasts] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(щупает)] [npc2.breasts] [npc2.namePos]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex("+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex(наизури"+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] совершает [style.boldSex(хвосто"+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex(хвосто"+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.name]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] совершает [style.boldSex(щупальце-"+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex(щупальце-"+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.name]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целуя)] [npc.her] собственный [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex("+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] в собственной [npc.clit] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex(наизури"+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] в [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] владеть [npc.breasts] с помощью [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(ласкает)] [npc2.namePos] [npc2.breasts] с помощью [npc.her] [npc.feet]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целуя)] [npc.her] собственный [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex("+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex(наизури"+(target.isBreastFuckablePaizuri()?"пайзури":"наизури")+")] на [npc.name]!"));
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
			"Состояние сосков",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ласкает пальцами)] собственную [npc.nipples] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(пальцевание)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трах)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трах)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трах хвостом)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трах хвостом)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трах щупальцами)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трах щупальцами)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(поцелуи)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(поцелуи)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трах клитора)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает клитор)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(втискивает [npc.her] [npc.toes])] в собственные [npc.her] [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(втискивает [npc.her] [npc.toes])] в [npc2.namePos] [npc2.nipples]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(поцелуи)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(поцелуи)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трах)] [npc.her] собственный [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трах)] [npc2.namePos] [npc2.nipples]!"));
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
			"Статус: клитор-грудь",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Статус: вымя";
			} else {
				return "Статус: клитор-грудь";
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] владей [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(лапает)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] совершает [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на [npc.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] совершает [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на собственную [npc.tail] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на [npc.tail] [npc.namePos]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] совершает [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на собственную [npc.tentacle] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на [npc.tentacle] [npc.namePos]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владей [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] совершает [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] в собственной [npc.clit] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] в [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] собственноручно [npc.crotchBoobs] с помощью [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(ласкает)] [npc2.namePos] [npc2.crotchBoobs] с помощью [npc.her] [npc.feet]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владей [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] совершает [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] совершает [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"пайзури":"наизури")+")] на [npc.name]!"));
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
			"Состояние сосков",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Состояние сосков вымени";
			} else {
				return "Состояние сосков на лобке";
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] сама [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] сама [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(ебаный)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] сама [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трах в задницу)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] сама [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владеет [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(клиторный-трах)] [npc.her] владей [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трах клитора)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] в [npc.her] [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] в [npc2.namePos] [npc2.crotchNipples]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владеет [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] сама [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(ебаный)] [npc2.namePos] [npc2.crotchNipples]!"));
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
			"Состояние уретры пениса",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] собственная [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(пальцами)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc.her] свою [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает хвостом)] [npc.her] свою [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает хвостом)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает щупальцами)] [npc.her] свою [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает щупальцами)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целует)] [npc.her] свою [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает клитор)] [npc.her] свою [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахающий клитор)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(втискивая [npc.her] [npc.toes])] в собственную [npc.her] [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(втискивая [npc.her] [npc.toes])] в [npc2.namePos] [npc2.urethraPenis]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целует)] [npc.her] свою [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc.her] свою [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc2.namePos] [npc2.urethraPenis]!"));
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
			"Статус: вагинальный уретральный канал",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(лаская пальцами)] собственную [npc.her] [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(лаская пальцами)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахая)] собственную [npc.her] [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахая)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахая хвостом)] собственную [npc.her] [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахая хвостом)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] овладевает [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] целует [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] ласкает клитор [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трах клитора)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] в собственное [npc.her] [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] в [npc2.namePos] [npc2.urethraVagina]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] целует [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахая)] собственную [npc.her] [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахая)] [npc2.namePos] [npc2.urethraVagina]!"));
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
			"Статус киски",
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
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ласкает)] [npc.her] свою [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(ласкает)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc.her] свою [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает хвостом)] [npc.her] свою [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает хвостом)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает щупальцем)] [npc.her] свою [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает щупальцем)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex(куннилингус)] [npc.her] своей [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" занимается [style.boldSex(куннилингусом)] с [npc.name]!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает клитор)] своей [npc.pussy] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(триббингом)] своей [npc.pussy] [npc.her]!"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
											+" [style.boldSex(трахает клитор)] [npc.namePos] [npc.pussy]!"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
											+" [style.boldSex(триббинг)] с [npc2.name]!"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(втирая [npc.her] [npc.toes])] в свою [npc.pussy] [npc.her]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(запихивает [npc.her] [npc.toes])]"):" запихивают [style.boldSex(свои пальцы ног)]")
										+"  в [npc.namePos] [npc.pussy]!"));
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
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] делает [style.boldSex(куннилингус)] [npc.herself]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" занимается [style.boldSex(куннилингусом)] с [npc2.name]!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc.her] свою [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(триббингом)] своей [npc.pussy] [npc.her]!")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(триббинг)] с [npc2.name]!"));
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
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] киска"), descriptionSB);
			
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
			"Статус паутинного органа",
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
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ласкает пальцами)] [npc.her] собственный паутинный орган!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(ласкает пальцами)] [npc.namePos] паутинный орган!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc.her] собственный паутинный орган!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает)] [npc.namePos] паутинный орган!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает хвостом)] [npc.her] собственный паутинный орган!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает хвостом)] [npc.namePos] паутинный орган!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает щупальцем)] [npc.her] собственный паутинный орган!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(tentacle-fucking)] [npc.namePos] паутинка!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(oral)] [npc.her] собственной паутинкой!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" делает [style.boldSex(oral)] [npc.namePos] паутинке!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] собственной паутинкой!"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] собственной паутинкой!"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
											+" [style.boldSex(clit-fucking)] [npc.namePos] паутинка!"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
											+" [style.boldSex(tribbing)] с [npc2.namePos] паутинкой!"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] в [npc.her] собственную паутинку!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(запихивает [npc.her] [npc.toes])]"):" запихивают [style.boldSex(свои пальцы ног)]")
										+"  в [npc.namePos] паутинку!"));
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
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(орально)] ласкает собственный паучий аппарат [npc.her]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" делает [style.boldSex(oral)] [npc2.namePos] паутинке!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] [npc.her] собственный паутинный орган!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(трахает)] [npc.namePos] паутинный орган!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] собственной паутинкой!")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" являются")
										+" [style.boldSex(tribbing)] с [npc2.namePos] паутинкой!"));
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
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] паутинка"), descriptionSB);
			
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
			"Статус бедер",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] ласкает свои [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(ласки)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ебаные)] [npc.her] собственные бёдра!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] трахает [npc2.namePos] бедра!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахать сзади)] [npc.her] собственные бёдра!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] трахает [npc2.namePos] бедра хвостом!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] собственные бёдра!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] трахает [npc2.namePos] бедра щупальцами!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владей [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] собственные бёдра!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] бёдра!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] между собственных [npc.her] бёдер!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] между [npc2.namePos] бёдрами!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] владей [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(ебаные)] [npc.her] собственные бёдра!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] трахает [npc2.namePos] бедра!"));
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

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] бёдра"), descriptionSB);
			
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
			"Состояние подмышек",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] собственные [npc.her] [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(ласки)] [npc2.namePos] [npc2.armpits]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] свои собственные подмышки [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает)] подмышки [npc2.namePos]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает хвостом)] свои собственные подмышки [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает хвостом)] подмышки [npc2.namePos]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает щупальцами)] свои собственные подмышки [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает щупальцами)] подмышки [npc2.namePos]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целует)] свои собственные [npc.armpits] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.armpits] [npc2.namePos]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает клитором)] свои собственные подмышки [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает клитором)] подмышки [npc2.namePos]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(втискивая [npc.her] [npc.feet])] между собственных подмышек [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(втискивая [npc.her] [npc.feet])] между подмышек [npc2.namePos]!"));
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(целует)] свои собственные [npc.armpits] [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(целует)] [npc2.armpits] [npc2.namePos]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(трахает)] свои собственные подмышки [npc.her]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(трахает)] подмышки [npc2.namePos]!"));
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
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] подмышки"), descriptionSB);
			
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
			"Статус рук",
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
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(держит за руки)] с [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(держит за руки)] с [npc2.name]!"));
								}
								break;
							case FOOT:
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("[style.colourDisabled(Нет текущих действий...)]");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.hands]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
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
