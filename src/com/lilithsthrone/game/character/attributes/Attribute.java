package com.lilithsthrone.game.character.attributes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * NOTE: Racial attributes are added at the bottom of the static block in Race.java!
 * 
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class Attribute {

	public static AbstractAttribute MAJOR_PHYSIQUE = new AbstractAttribute(false,
			0,
			0,
			100,
			"телосложение",
			"Телосложение",
			"strengthIcon",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			"сила",
			"слабость",
			Util.newArrayListOfValues(
					"<b>+2</b> <b style='color: " + PresetColour.ATTRIBUTE_HEALTH.toWebHexString() + "'></b> энергии на 1 ед. телосложения")) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Показатель физического здоровья [npc.name] [npc.is], телосложение <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>пассивно увеличивает</b> [npc.her] <b style='color:" + PresetColour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>максимальное здоровье</b>.");
		}
		@Override
		public int getOrderPriority() {
			return 10;
		}
	};

	public static AbstractAttribute MAJOR_ARCANE = new AbstractAttribute(false,
			0,
			0,
			100,
			"аркана",
			"Arcane",
			"intelligenceIcon",
			PresetColour.ATTRIBUTE_ARCANE,
			"аркана-буст",
			"поглощение арканы",
			Util.newArrayListOfValues(
					"<b>+2</b> <b style='color: " + PresetColour.ATTRIBUTE_MANA.toWebHexString() + "'>Аура</b> за 1 ед. арканы")) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
						"Показатель сродства с [npc.namePos] арканой. Это <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>пассивно увеличивает</b> [npc.her] <b style='color:" + PresetColour.ATTRIBUTE_MANA.toWebHexString() + ";'>максимальная аура</b>.");
		}
		@Override
		public int getOrderPriority() {
			return 20;
		}
	};

	public static AbstractAttribute MAJOR_CORRUPTION = new AbstractAttribute(false,
			0,
			0,
			100,
			"порочность",
			"Порочность",
			"corruptionIcon",
			PresetColour.ATTRIBUTE_CORRUPTION,
			"порочность",
			"чистота",
			Util.newArrayListOfValues(
					"<b>-0.5</b> <b style='color: " + PresetColour.ATTRIBUTE_MANA.toWebHexString() + "'>сопротивление возбуждению</b> за 1 ед. физической порочности",
					"<b>+0.5</b> <b style='color: " + PresetColour.DAMAGE_TYPE_MANA.toWebHexString() + "'>урон возбуждением</b> за 1 ед. физической порочности")) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPlayer()) {
				return "Порочность — это мера вашей извращенности и разврата, которая влияет на <b style='color:" + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>то, насколько комфортно вам выполнять те или иные сексуальные действия</b>.";
			} else {
				return UtilText.parse(owner,
						"Порочность — это мера [npc.namePos] извращенности и разврата. Она <i>не</i> отражает, насколько добрым или злым является [npc.she].");
			}
		}
		@Override
		public int getOrderPriority() {
			return 30;
		}
	};

	public static AbstractAttribute HEALTH_MAXIMUM = new AbstractAttribute(false,
			1,
			1,
			999,
			"здоровье",
			"Здоровье",
			"healthIcon",
			PresetColour.ATTRIBUTE_HEALTH,
			"здоровье",
			"болезнь",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Количество выносливости и решимости [npc.name] [npc.has]. [npc.She] будет повержен в бою, если этот показатель достигнет 0.<br/>Дополнительное здоровье добавляется к значению «бонус» за счет:<br/><b>"+ GameCharacter.HEALTH_CALCULATION + "</b>");
		}

		@Override
		public int getOrderPriority() {
			return 40;
		}
	};

	public static AbstractAttribute MANA_MAXIMUM = new AbstractAttribute(false,
			1,
			1,
			999,
			"аура",
			"Аура",
			"manaIcon",
			PresetColour.ATTRIBUTE_MANA,
			"усиление ауры",
			"поглощение ауры",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Показатель количества арканной энергии, [npc.name] [npc.has] в процессе [npc.her] ауры.<br/>Дополнительная аура добавляется к значению «бонус» за счет:<br/><b>" + GameCharacter.MANA_CALCULATION + "</b>");
		}

		@Override
		public int getOrderPriority() {
			return 50;
		}
	};
	
	public static AbstractAttribute EXPERIENCE = new AbstractAttribute(false,
			0,
			0,
			1000000,
			"опыта",
			"Опыт",
			"experienceIcon",
			PresetColour.GENERIC_EXPERIENCE,
			"изучения",
			"забывчивости",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Сколько прогресса [npc.name] [npc.has] накоплено до следующего уровня.");
		}

		@Override
		public int getOrderPriority() {
			return 60;
		}
	};

	public static AbstractAttribute ACTION_POINTS = new AbstractAttribute(false,
			0,
			0,
			10,
			"очки действия",
			"Очки действия",
			"action_points",
			PresetColour.GENERIC_ACTION_POINTS,
			"инициатива",
			"вялость",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Количество очков действия [npc.nameHasFull], доступных для использования в бою.");
		}

		@Override
		public int getOrderPriority() {
			return 70;
		}
	};

	public static AbstractAttribute ENCHANTMENT_LIMIT = new AbstractAttribute(false,
			0,
			0,
			1000,
			"нестабильность чар",
			"Нестабильность чар",
			"enchantmentLimitIcon",
			PresetColour.GENERIC_ENCHANTMENT,
			"обуздание",
			"неуклюжесть",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
						"Общее количество зачарований одежды и татуировок, которые [npc.nameIsFull] может выдержать без получения огромных штрафов.");
		}
		@Override
		public boolean isAffectedByEnchantmentCost() {
			return false;
		}
		@Override
		public int getOrderPriority() {
			return 80;
		}
	};

	// Sexual attributes:
	
	public static AbstractAttribute LUST = new AbstractAttribute(false,
			0,
			0,
			100,
			"похоть",
			"Похоть",
			"arousalIcon",
			PresetColour.ATTRIBUTE_LUST,
			"страсть",
			"безразличие",
			null) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPlayer()) {
				return "Насколько сильно вы жаждете сексуального контакта. Со временем ваша похоть будет стремиться к значению покоя.<br/><b>Покой похоти = " + GameCharacter.RESTING_LUST_CALCULATION + "</b>";
			} else {
				return UtilText.parse(owner,
						"Насколько сильно [npc.name] жаждет сексуального контакта.");
			}
		}
		@Override
		public int getOrderPriority() {
			return 100;
		}
	};
	
	public static AbstractAttribute RESTING_LUST = new AbstractAttribute(false,
			0,
			-100,
			80,
			"покой похоти",
			"Покой похоти",
			"arousalIcon",
			PresetColour.ATTRIBUTE_LUST,
			"страсть",
			"безразличие",
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Уровень похоти, к которому [npc.name] естественным образом стремится в течение некоторого времени.");
		}

		@Override
		public int getOrderPriority() {
			return 110;
		}
	};
	
	public static AbstractAttribute AROUSAL = new AbstractAttribute(false,
			0,
			0,
			100,
			"возбуждение",
			"Возбуждение",
			"arousalIcon",
			PresetColour.ATTRIBUTE_AROUSAL,
			"длительный",
			"преждевременность",
			null) {
		@Override
		public boolean hasStatusEffect() {
			return true;
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPlayer())
				return "Ваш текущий уровень возбуждения. Вы испытаете оргазм, когда уровень возбуждения достигнет максимума.";
			else
				return UtilText.parse(owner,
						"Насколько возбужден(а) [npc.name]. [npc.She] достигнет оргазма, когда [npc.her] возбуждение достигнет максимума.");
		}
		@Override
		public int getOrderPriority() {
			return 120;
		}
	};
	
	public static AbstractAttribute VIRILITY = new AbstractAttribute(true, 10, -100, 100, "вирильность", "Вирильность", "shieldIcon", PresetColour.GENERIC_SEX, "вирильность", "бесплодие", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Повышает вероятность оплодотворения.";
		}

		@Override
		public int getOrderPriority() {
			return 130;
		}
	};
	
	public static AbstractAttribute FERTILITY = new AbstractAttribute(true, 10, -100, 100, "фертильность", "Фертильность", "shieldIcon", PresetColour.GENERIC_SEX, "фертильность", "бесплодие", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Повышает вероятность забеременеть.";
		}

		@Override
		public int getOrderPriority() {
			return 140;
		}
	};

	// Combat attributes:

	public static AbstractAttribute SPELL_COST_MODIFIER = new AbstractAttribute(true, 0, 0, 80, "эффективность заклинаний", "Эффективность заклинаний", "shieldIcon", PresetColour.ATTRIBUTE_MANA, "мастерство", "некомпетентность", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Снижает стоимость применения заклинаний.";
		}

		@Override
		public int getOrderPriority() {
			return 200;
		}
	};
	
	public static AbstractAttribute CRITICAL_DAMAGE = new AbstractAttribute(true, 150, 100, 500, "критический удар", "Критическая мощь", "shieldIcon", PresetColour.ATTRIBUTE_HEALTH, "удар", "неудача", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Каждый пункт дает % e дополнительной критической мощи.";
		}

		@Override
		public int getOrderPriority() {
			return 210;
		}
	};
	
	// Damages:

	public static AbstractAttribute DAMAGE_UNARMED = new AbstractAttribute(true, 0, -80, 100, "урон без оружия", "Урон без оружия", "swordIcon", PresetColour.DAMAGE_TYPE_UNARMED, "боевые искусства", "беспомощность в ближнем бою", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон от атак голыми руками, включая особые атаки, полученные от частей тела нечеловеческих существ.";
		}

		@Override
		public int getOrderPriority() {
			return 220;
		}
	};
	
	public static AbstractAttribute DAMAGE_MELEE_WEAPON = new AbstractAttribute(true, 0, -80, 100, "урон холодным оружием", "Урон холодным оружием", "swordIcon", PresetColour.DAMAGE_TYPE_MELEE, "мастерство ближнего боя", "неумение в ближнем бою", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон, наносимый холодным оружием.";
		}

		@Override
		public int getOrderPriority() {
			return 230;
		}
	};
	
	public static AbstractAttribute DAMAGE_RANGED_WEAPON = new AbstractAttribute(true, 0, -80, 100, "урон дистанционным оружием", "Урон дистанционным оружием", "swordIcon", PresetColour.DAMAGE_TYPE_RANGED, "мастерство дальнего боя", "неумение в дальнем бою", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон от атак из дистанционного оружия.";
		}

		@Override
		public int getOrderPriority() {
			return 240;
		}
	};
	
	public static AbstractAttribute DAMAGE_SPELLS = new AbstractAttribute(true, 0, -80, 100, "урон заклинаниями", "Урон заклинаниями", "swordIcon", PresetColour.ATTRIBUTE_MANA, "сила арканы", "затухание арканы", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон заклинаний.";
		}

		@Override
		public int getOrderPriority() {
			return 250;
		}
	};

	public static AbstractAttribute DAMAGE_PHYSICAL = new AbstractAttribute(true, 0, -80, 100, "физический урон", "Физический урон", "swordIcon", PresetColour.DAMAGE_TYPE_PHYSICAL, "сила", "мягкость", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает физический урон.";
		}

		@Override
		public int getOrderPriority() {
			return 260;
		}
	};
	
	public static AbstractAttribute DAMAGE_LUST = new AbstractAttribute(true, 0, -80, 100, "урон похотью", "Урон похотью", "swordIcon", PresetColour.GENERIC_SEX, "соблазнение", "отвращение", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон похотью.";
		}

		@Override
		public int getOrderPriority() {
			return 270;
		}
	};
	
	public static AbstractAttribute DAMAGE_FIRE = new AbstractAttribute(true, 0, -80, 100, "урон огнем", "Урон огнем", "swordIcon", PresetColour.DAMAGE_TYPE_FIRE, "инферно", "угасающие угли", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон огнем.";
		}

		@Override
		public int getOrderPriority() {
			return 280;
		}
	};
	
	public static AbstractAttribute DAMAGE_ICE = new AbstractAttribute(true, 0, -80, 100, "урон холодом", "Урон холодом", "swordIcon", PresetColour.DAMAGE_TYPE_COLD, "метель", "слякоть", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон холодом.";
		}

		@Override
		public int getOrderPriority() {
			return 290;
		}
	};
	
	public static AbstractAttribute DAMAGE_POISON = new AbstractAttribute(true, 0, -80, 100, "урон ядом", "Урон ядом", "swordIcon", PresetColour.DAMAGE_TYPE_POISON, "яд", "разбавление", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон от яда.";
		}

		@Override
		public int getOrderPriority() {
			return 300;
		}
	};
	

	public static AbstractAttribute ENERGY_SHIELDING = new AbstractAttribute(false, 0, -100, 500, "щит здоровья", "Щит здоровья", "shieldIcon", PresetColour.ATTRIBUTE_HEALTH, "выносливость", "уязвимость", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Это значение применяется к щиту здоровья в начале каждого боевого хода.";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(Иммунитет)] ко [style.colourHealth(всему урону)]";
		}
		@Override
		public int getOrderPriority() {
			return 400;
		}
	};

	
	// Resistances:

	public static AbstractAttribute RESISTANCE_PHYSICAL = new AbstractAttribute(false, 0, -100, 500, "физический щит", "Физическая защита", "shieldIcon", PresetColour.DAMAGE_TYPE_PHYSICAL, "живучесть", "мягкость", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Снижает получаемый физический урон.";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(Иммунитет)] к [style.colourPhysical(физическому урону)]";
		}
		@Override
		public int getOrderPriority() {
			return 410;
		}
	};
	
	public static AbstractAttribute RESISTANCE_LUST = new AbstractAttribute(false, 0, -100, 500, "защита от похоти", "Защита от похоти", "shieldIcon", PresetColour.GENERIC_SEX, "целомудрие", "искушение", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Снижает получаемый урон похоти.";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(Иммунитет)] к [style.colourLust(урону похоти)]";
		}
		@Override
		public int getOrderPriority() {
			return 420;
		}
	};
	
	public static AbstractAttribute RESISTANCE_FIRE = new AbstractAttribute(false, 0, -100, 500, "огнезащита", "Огнезащита", "shieldIcon", PresetColour.DAMAGE_TYPE_FIRE, "тушение", "воспламеняемость", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Снижает получаемый урон от огня.";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(Иммунитет)] к [style.colourFire(огненному урону)]";
		}
		@Override
		public int getOrderPriority() {
			return 430;
		}
	};
	
	public static AbstractAttribute RESISTANCE_ICE = new AbstractAttribute(false, 0, -100, 500, "холодозащита", "Холодозащита", "shieldIcon", PresetColour.DAMAGE_TYPE_COLD, "тепло", "обморожение", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Снижает получаемый урон от холода.";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(Иммунитет)] к [style.colourIce(урону льдом)]";
		}
		@Override
		public int getOrderPriority() {
			return 440;
		}
	};
	
	public static AbstractAttribute RESISTANCE_POISON = new AbstractAttribute(false, 0, -100, 500, "защита от яда", "Защита от яда", "shieldIcon", PresetColour.DAMAGE_TYPE_POISON, "антидот", "болезнь", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Снижает получаемый урон от яда.";
		}
		@Override
		public boolean isInfiniteAtUpperLimit() {
			return true;
		}
		@Override
		public String getInfiniteDescription() {
			return "[style.colourExcellent(Иммунитет)] к [style.colourPoison(урону ядом)]";
		}
		@Override
		public int getOrderPriority() {
			return 450;
		}
	};
	
	// From v0.4, these are automatically generated in the static block at the end of the Race.java class!
//	// Racial:
//	
//	public static AbstractAttribute DAMAGE_ANGEL = new AbstractAttribute(true, 0, -100, 100, "angelic damage", "Angelic damage", "swordIcon", PresetColour.RACE_ANGEL, "angelic-obliteration", "angelic-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs angels.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_CAT_MORPH = new AbstractAttribute(true, 0, -100, 100, "cat-morph damage", "Cat-morph damage", "swordIcon", PresetColour.RACE_CAT_MORPH, "cat-morph-obliteration", "cat-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs cat-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_COW_MORPH = new AbstractAttribute(true, 0, -100, 100, "cow-morph damage", "Cow-morph damage", "swordIcon", PresetColour.RACE_COW_MORPH, "cow-morph-obliteration", "cow-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs cow-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_DEMON = new AbstractAttribute(true, 0, -100, 100, "demonic damage", "Demonic damage", "swordIcon", PresetColour.RACE_DEMON, "demonic-obliteration", "demonic-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs demons.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_DOG_MORPH = new AbstractAttribute(true, 0, -100, 100, "dog-morph damage", "Dog-morph damage", "swordIcon", PresetColour.RACE_DOG_MORPH, "dog-morph-obliteration", "dog-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs dog-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_HARPY = new AbstractAttribute(true, 0, -100, 100, "harpy damage", "Harpy damage", "swordIcon", PresetColour.RACE_HARPY, "harpy-obliteration", "harpy-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs harpies.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_HORSE_MORPH = new AbstractAttribute(true, 0, -100, 100, "horse-morph damage", "Horse-morph damage", "swordIcon", PresetColour.RACE_HORSE_MORPH, "horse-morph-obliteration", "horse-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs horse-morphs.";
//		}
//	};
	
	public static AbstractAttribute DAMAGE_ELDER_LILIN = new AbstractAttribute(true, 0, -100, 100, "урон старших лилин", "Урон старших лилин", "swordIcon", PresetColour.RACE_LILIN, "истребление-старших-лилин", "старшая-лилин-милосердие", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон по старшим лилин.";
		}

		@Override
		public int getOrderPriority() {
			return 1000;
		}
	};

	public static AbstractAttribute DAMAGE_LILIN = new AbstractAttribute(true, 0, -100, 100, "урон лилин", "Урон лилин", "swordIcon", PresetColour.RACE_LILIN, "уничтожение лилин", "милосердие лилин", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон по лилин.";
		}

		@Override
		public int getOrderPriority() {
			return 1100;
		}
	};
	
	public static AbstractAttribute DAMAGE_IMP = new AbstractAttribute(true, 0, -100, 100, "урон бесов", "Урон бесов", "swordIcon", PresetColour.RACE_IMP, "бесноватое уничтожение", "бесовское милосердие", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Увеличивает урон по бесам.";
		}

		@Override
		public int getOrderPriority() {
			return 1200;
		}
	};

//	public static AbstractAttribute DAMAGE_REINDEER_MORPH = new AbstractAttribute(true, 0, -100, 100, "reindeer-morph damage", "Reindeer-morph damage", "swordIcon", PresetColour.RACE_REINDEER_MORPH, "reindeer-morph-obliteration", "reindeer-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs reindeer-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_HUMAN = new AbstractAttribute(true, 0, -100, 100, "human damage", "Human damage", "swordIcon", PresetColour.RACE_HUMAN, "human-obliteration", "human-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs humans.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_SQUIRREL_MORPH = new AbstractAttribute(true, 0, -100, 100, "squirrel-morph damage", "Squirrel-morph damage", "swordIcon", PresetColour.RACE_SQUIRREL_MORPH, "squirrel-morph-obliteration", "squirrel-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs squirrel-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_RAT_MORPH = new AbstractAttribute(true, 0, -100, 100, "rat-morph damage", "Rat-morph damage", "swordIcon", PresetColour.RACE_RAT_MORPH, "rat-morph-obliteration", "rat-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs rat-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_RABBIT_MORPH = new AbstractAttribute(true, 0, -100, 100, "rabbit-morph damage", "Rabbit-morph damage", "swordIcon", PresetColour.RACE_RABBIT_MORPH, "rabbit-morph-obliteration", "rabbit-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs rabbit-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_BAT_MORPH = new AbstractAttribute(true, 0, -100, 100, "bat-morph damage", "Bat-morph damage", "swordIcon", PresetColour.RACE_BAT_MORPH, "bat-morph-obliteration", "bat-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs bat-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_ALLIGATOR_MORPH = new AbstractAttribute(true, 0, -100, 100, "alligator-morph damage", "Alligator-morph damage", "swordIcon", PresetColour.RACE_ALLIGATOR_MORPH, "alligator-morph-obliteration", "alligator-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs alligator-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_WOLF_MORPH = new AbstractAttribute(true, 0, -100, 100, "wolf-morph damage", "Wolf-morph damage", "swordIcon", PresetColour.RACE_WOLF_MORPH, "wolf-morph-obliteration", "wolf-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs wolf-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_FOX_MORPH = new AbstractAttribute(true, 0, -100, 100, "fox-morph damage", "Fox-morph damage", "swordIcon", PresetColour.RACE_FOX_MORPH, "fox-morph-obliteration", "fox-morph-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs fox-morphs.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_SLIME = new AbstractAttribute(true, 0, -100, 100, "slime damage", "Slime damage", "swordIcon", PresetColour.RACE_SLIME, "slime-obliteration", "slime-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs slimes.";
//		}
//	};
//	public static AbstractAttribute DAMAGE_ELEMENTAL = new AbstractAttribute(true, 0, -100, 100, "elemental damage", "Elemental damage", "swordIcon", PresetColour.SPELL_SCHOOL_ARCANE, "elemental-obliteration", "elemental-mercy", null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return "Increases damage vs elementals.";
//		}
//	};
	
	
	public static Map<AbstractAttribute, String> attributeToIdMap = new HashMap<>();
	public static Map<String, AbstractAttribute> idToAttributeMap = new HashMap<>();
	public static List<AbstractAttribute> allAttributes;
	
	public static Map<AbstractRace, AbstractAttribute> racialAttributes = new HashMap<>();

	private static Map<String, AbstractAttribute> oldConversionMapping = new HashMap<>();
	static {
		oldConversionMapping.put("CORRUPTION", Attribute.MAJOR_CORRUPTION);
		oldConversionMapping.put("STRENGTH", Attribute.MAJOR_PHYSIQUE);
		oldConversionMapping.put("MAJOR_STRENGTH", Attribute.MAJOR_PHYSIQUE);
		oldConversionMapping.put("INTELLIGENCE", Attribute.MAJOR_ARCANE);
		oldConversionMapping.put("RESISTANCE_ATTACK", Attribute.RESISTANCE_PHYSICAL);
		oldConversionMapping.put("RESISTANCE_MANA", Attribute.RESISTANCE_LUST);
		oldConversionMapping.put("RESISTANCE_PURE", Attribute.ENERGY_SHIELDING);
	}

	/**
	 * @return The Attribute that has an id closest to the supplied attributeId.
	 *  <b>Will return null</b> if the matching distance is greater than 3 (which typically will be more than enough to catch spelling errors, indicating that the flag has been removed).
	 */
	public static AbstractAttribute getAttributeFromId(String attributeId) {
		if(attributeId.startsWith("RESISTANCE_ELEMENTAL")) {
			attributeId = "RESISTANCE_ELEMENTAL";
		} else if(attributeId.startsWith("DAMAGE_ELEMENTAL")) {
			attributeId = "DAMAGE_ELEMENTAL";
		} else if(attributeId.startsWith("CRITICAL_CHANCE")) { // Critical chance was removed, so return damage instead as a replacement for old saves
			attributeId = "CRITICAL_DAMAGE";
		}
		
		if(oldConversionMapping.containsKey(attributeId)) {
			return oldConversionMapping.get(attributeId);
		}

		attributeId = Util.getClosestStringMatch(attributeId, idToAttributeMap.keySet(), 3);
		
		return idToAttributeMap.get(attributeId);
	}

	public static String getIdFromAttribute(AbstractAttribute attribute) {
		return attributeToIdMap.get(attribute);
	}

	public static List<AbstractAttribute> getAllAttributes() {
		return allAttributes;
	}
	
	public static AbstractAttribute getRacialDamageAttribute(AbstractRace race) {
		return racialAttributes.get(race);
	}
	
	static {
		allAttributes = new ArrayList<>();
		
		// Hard-coded attributes (all those up above):
		
		Field[] fields = Attribute.class.getFields();
		
		for(Field f : fields) {
			if (AbstractAttribute.class.isAssignableFrom(f.getType())) {
				AbstractAttribute attribute;
				try {
					attribute = ((AbstractAttribute) f.get(null));

					attributeToIdMap.put(attribute, f.getName());
					idToAttributeMap.put(f.getName(), attribute);
					allAttributes.add(attribute);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		// NOTE: Racial attributes are added at the bottom of the static block in Race.java!
	}
	
}
