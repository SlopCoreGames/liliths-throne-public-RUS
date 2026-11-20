package com.lilithsthrone.game.combat.spells;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public enum SpellUpgrade {

	// Fire:
	
	FIREBALL_1(true,
			1,
			SpellSchool.FIRE,
			"fireball_lingering_flames",
			"Тлеющие Огни",
			"Arcane огни продолжают выжигать цель еще некоторое время после попадания Огненного шара.",
			null, Util.newArrayListOfValues(
					"<b>5</b> [style.colourFire(Огненный урон)] за ход в течение [style.colourGood(2 ходов)]")),
	FIREBALL_2(2,
			SpellSchool.FIRE,
			"fireball_twin_comets",
			"Близнецы-кометы",
			"Огненный шар сразу после сотворения разделяется на две кометы, которые, кажется, обретают собственный разум и стараются выбирать разные цели, если это возможно.",
			null, Util.newArrayListOfValues(
					"Выпускает [style.colourExcellent(два)] Огненных шара, каждый из которых наносит <b>15</b> [style.colourFire(Огненный урон)]")),
	FIREBALL_3(5,
			SpellSchool.FIRE,
			"fireball_burning_fury",
			"Пылающая ярость",
			"Теперь кометы-близнецы Огненного шара пылают с неистовой яростью, нанося значительно больше урона.",
			null, Util.newArrayListOfValues(
					"Близнецы-кометы теперь наносят по <b>30</b> [style.colourFire(Огненный урон)] каждая")),
	
	
	FLASH_1(true,
			2,
			SpellSchool.FIRE,
			"flash_secondary_sparks",
			"Вторичные искры",
			"После первой вспышки перед лицом цели с треском разлетается целая серия ослепительных искр.",
			null, Util.newArrayListOfValues(
					"Теперь [style.colourExcellent(ослепляет)] цель на [style.colourTerrible(-2)] [style.colourActionPoints(очков действия)]")),
	FLASH_2(5,
			SpellSchool.FIRE,
			"flash_arcing_flash",
			"Дуговой Всплеск",
			"От первого всплеска отлетает дуга, ищущая другую цель, чтобы ослепить её.",
			null, Util.newArrayListOfValues(
					"Выпускает [style.colourExcellent(два)] Всплеска")),
	FLASH_3(3,
			SpellSchool.FIRE,
			"flash_efficient_burn",
			"Эффективное Выжигание",
			"Концентрируя свою мощь в более узком и плотном импульсе, заклинатель способен снизить стоимость Всплеска, не теряя при этом его эффективности.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Снижает)] базовую стоимость до [style.boldMana(25)] ауры")),
	
	
	CLOAK_OF_FLAMES_1(true,
			2,
			SpellSchool.FIRE,
			"cloak_of_flames_incendiary",
			"Зажигательный",
			"С каждым ударом струя огня из Плаща Огней вырывается вперед, чтобы сжечь врага. Наносимый урон зависит от урона огнем владельца и огненного щита цели.",
			null, Util.newArrayListOfValues(
					"Атаки без оружия наносят +1 ед. урона за каждый уровень заклинателя",
					"Безоружные атаки наносят [style.boldFire(урон огнем)]")),
	CLOAK_OF_FLAMES_2(1,
			SpellSchool.FIRE,
			"cloak_of_flames_inferno",
			"Инферно",
			"Плащ Огней наделяет владельца знанием того, как наносить максимально возможный урон огнем.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Получите)] +25 [style.boldFire(Урона огнем)]")),
	CLOAK_OF_FLAMES_3(2,
			SpellSchool.FIRE,
			"cloak_of_flames_ring_of_fire",
			"Кольцо Огня",
			"Любой враг, приближающийся к Плащу Огней, встречает ответную вспышку пламени. Наносимый урон зависит от урона огнем владельца и огненного щита атакующего.",
			null, Util.newArrayListOfValues(
					"<b>Ближние</b> или <b>безоружные</b> атакующие получают <b>5</b> [style.colourFire(Урона огнем)]")),

	
	ELEMENTAL_FIRE_1(true,
			5,
			SpellSchool.FIRE,
			"elemental_fire_wildfire",
			"Стихийный огонь",
			"Призванный элементаль наделяет всех союзников знанием того, как лучше всего обуздать арканное пламя.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все союзники получают)] +20 [style.boldFire(Урона огнем)]")),
	ELEMENTAL_FIRE_2(5,
			SpellSchool.FIRE,
			"elemental_fire_burning_desire",
			"Жгучее желание",
			"Огненный элементаль способен использовать похотливые свойства арканы, чтобы разжечь жгучую жажду секса в сердцах и умах своих врагов.",
			null, Util.newArrayListOfValues(
					"[style.colourTerrible(Все враги получают)] -25 [style.boldLust(, "+Attribute.RESISTANCE_LUST.getName()+")]")),
	ELEMENTAL_FIRE_3A(5,
			SpellSchool.FIRE,
			"elemental_fire_servant_of_fire",
			"Слуга Огня",
			"Призыватель клянется быть покорным школе Огня, и пока их элементаль привязан к этой форме, элементаль может черпать из призывателя столько энергии, сколько пожелает.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourFire(Элементаль)]: +100% [style.colourExcellent(Урон без соблазнения)]",
					"[style.colourArcane(Заклинатель)]: -50% [style.colourHealth(макс. "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_FIRE_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает «Связь Огня»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает «Связь Огня»!)]";
			}
		}
	},
	ELEMENTAL_FIRE_3B(10,
			SpellSchool.FIRE,
			"elemental_fire_binding_of_fire",
			"Связь Огня",
			"Призыватель устанавливает полный контроль над школой Огня, и пока их элементаль привязан к этой форме, тот вынужден делиться всеми своими секретами.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourArcane(Caster)]: +25 "+Attribute.DAMAGE_FIRE.getColouredName("b"),
					"[style.colourArcane(Caster)]: +10 "+Attribute.RESISTANCE_FIRE.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_FIRE_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Слугой Огня»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Слугой Огня»!)]";
			}
		}
	},
	
	// Water:

	ICE_SHARD_1(true,
			1,
			SpellSchool.WATER,
			"ice_shard_freezing_fog",
			"Ледяной туман",
			"Ледяной осколок взрывается при попадании, насыщая воздух вокруг цели кристаллами Arcane. Эти кристаллы быстро конденсируются в ледяной туман, который лишает цель способности творить заклинания.",
			null, Util.newArrayListOfValues(
					"<b>-20</b> "+Attribute.SPELL_COST_MODIFIER.getColouredName("b")+" на [style.colourGood(3 хода)]")),
	ICE_SHARD_2(2,
			SpellSchool.WATER,
			"ice_shard_cold_snap",
			"Ледяной порыв",
			"Когда Ледяной осколок пролетает сквозь слой ледяного тумана, оставленного предыдущим попаданием, кристаллы в воздухе взрываются, нанося дополнительный урон.",
			null, Util.newArrayListOfValues(
					"Ледяной осколок [style.boldExcellent(критически поражает)] цели, находящиеся под воздействием Ледяного тумана")),
	ICE_SHARD_3(5,
			SpellSchool.WATER,
			"ice_shard_deep_freeze",
			"Глубокая заморозка",
			"Когда кристаллы в морозном тумане детонируют, они мгновенно заключают любые объекты поблизости в корку тонкого льда, на мгновение обездвиживая их.",
			null, Util.newArrayListOfValues(
					"Когда Ледяной осколок [style.boldExcellent(критически поражает)] цель под воздействием Морозного тумана,",
					"эта цель теряет [style.colourTerrible(-1)] [style.colourActionPoints(очки действия)]")),

	RAIN_CLOUD_1(true,
			1,
			SpellSchool.WATER,
			"rain_cloud_deep_chill",
			"Глубокий холод",
			"Арканальный дождь просачивается в кости цели, пробирая её до самого мозга костей.",
			null, Util.newArrayListOfValues(
					"Добавляет <b>-25</b> "+Attribute.RESISTANCE_ICE.getColouredName("b")+" к эффектам Дождевого облака")),
	RAIN_CLOUD_2(3,
			SpellSchool.WATER,
			"rain_cloud_downpour",
			"Ливень",
			"Потоки проливного арканального дождя бьют цели в глаза, заставляя её время от времени промахиваться при атаке.",
			null, Util.newArrayListOfValues(
					"Добавляет <b>-5</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b")+" к эффектам Дождевого облака")),
	RAIN_CLOUD_3(5,
			SpellSchool.WATER,
			"rain_cloud_cloud_burst",
			"Облачный взрыв",
			"Гнев и раздражение цели дождевого облака преобразуются в энергию: каждый раз, когда цель получает критический удар, облако становится сильнее и больше.",
			null, Util.newArrayListOfValues(
					"Если цель Дождевого облака получает любой критический удар:",
					"Длительность сбрасывается до [style.colourGood(6 ходов)]",
					"<b>и</b> "+Attribute.SPELL_COST_MODIFIER.getColouredName("b")+" дебафф увеличивается до -50")),

	SOOTHING_WATERS_1_CLEAN(true,
			2,
			SpellSchool.WATER,
			"soothing_waters_clean",
			"Очищающие воды",
			"Сила Успокаивающих вод теперь распространяется по телу цели и пропитывает её одежду, очищая её от любых грязных жидкостей.",
			null, Util.newArrayListOfValues(
					"Успокаивающие воды [style.boldAqua(очищают всю надетую одежду)]",
					"Успокаивающие воды [style.boldAqua(очищают тело от всех жидкостей)]")),
	SOOTHING_WATERS_2_CLEAN(true,
			5,
			SpellSchool.WATER,
			"soothing_waters_clean_orifices",
			"Глубокая очистка",
			"«Успокаивающие воды» теперь проникают глубоко в тело цели, вымывая грязь из всех её отверстий.",
			null, Util.newArrayListOfValues(
					"«Успокаивающие воды» [style.boldAqua(вымывают [style.fluid(250)] жидкости из всех отверстий)]")),
	SOOTHING_WATERS_1(true,
			5,
			SpellSchool.WATER,
			"soothing_waters_arcane_springs",
			"Аркана Springs",
			"Аркана-сила, вложенная в «Успокаивающие воды», значительно возрастает, позволяя заклинанию дополнительно восстанавливать ауру цели.",
			null, Util.newArrayListOfValues(
					"«Успокаивающие воды» [style.boldExcellent(дополнительно)] восстанавливают <b>20%</b> "+Attribute.MANA_MAXIMUM.getColouredName("b"))),
	SOOTHING_WATERS_2(10,
			SpellSchool.WATER,
			"soothing_waters_rejuvenation",
			"Омоложение",
			"Живительные свойства воды полностью подчинены аркане, восстанавливая огромное количество энергии цели «Успокаивающих вод».",
			null, Util.newArrayListOfValues(
					"Восстановление «Успокаивающих вод» [style.boldExcellent(увеличено)] до <b>40%</b> "+Attribute.HEALTH_MAXIMUM.getColouredName("b"))),
	SOOTHING_WATERS_3(10,
			SpellSchool.WATER,
			"soothing_waters_bouncing_orb",
			"Прыгающие сферы",
			"Теперь после сотворения Успокаивающие воды разделяются на несколько сфер, каждая из которых ищет союзника для исцеления.",
			null, Util.newArrayListOfValues(
					"Успокаивающие воды исцеляют [style.boldExcellent(всех союзников)] на <b>10%</b> "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" и <b>10%</b> "+Attribute.MANA_MAXIMUM.getColouredName("b"))),

	ELEMENTAL_WATER_1(true,
			5,
			SpellSchool.WATER,
			"elemental_water_crashing_waves",
			"Штормовые волны",
			"Водный элементаль непрерывно посылает волны ледяной воды, обрушивающиеся на врагов, что делает их гораздо более уязвимыми к атакам льдом.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все союзники получают)] +20 [style.boldIce(Урон льдом)]")),
	ELEMENTAL_WATER_2(5,
			SpellSchool.WATER,
			"elemental_water_calm_waters",
			"Спокойные воды",
			"Водный элементаль проецирует образ спокойных, ровных вод в разум любого союзника, который начинает возбуждаться, помогая ему контролировать свою похоть.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все союзники получают)] +5 "+Attribute.RESISTANCE_LUST.getColouredName("b"))),
	ELEMENTAL_WATER_3A(5,
			SpellSchool.WATER,
			"elemental_water_servant_of_water",
			"Слуга Воды",
			"Призыватель клянется быть покорным стихии Воды, и пока его элементаль связан с этой формой, элементаль может черпать из призывателя столько энергии, сколько пожелает.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourIce(Элементаль)]: +100% [style.colourExcellent(Не-соблазняющий урон)]",
					"[style.colourArcane(Заклинатель)]: -50% [style.colourHealth(макс. "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_WATER_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Узами Воды»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Узами Воды»!)]";
			}
		}
	},
	ELEMENTAL_WATER_3B(10,
			SpellSchool.WATER,
			"elemental_water_binding_of_water",
			"Узы Воды",
			"Призыватель обретает полное господство над школой Воды, и пока его элементаль привязан к этой форме, он вынужден делиться всеми своими секретами.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourArcane(Заклинатель)]: +25 "+Attribute.DAMAGE_ICE.getColouredName("b"),
					"[style.colourArcane(Заклинатель)]: +10 "+Attribute.RESISTANCE_ICE.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_WATER_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Слугой Воды»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Слугой Воды»!)]";
			}
		}
	},
	
	// Air:

	POISON_VAPOURS_1(true,
			2,
			SpellSchool.AIR,
			"poison_vapours_choking_haze",
			"Удушающая дымка",
			"Облака ядовитых испарений становятся гораздо плотнее, превращаясь в удушающую, сжимающую горло дымку, из-за которой цель периодически промахивается при атаках.",
			null, Util.newArrayListOfValues(
					"Ядовитые пары дополнительно накладывают <b>-5</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	POISON_VAPOURS_2(5,
			SpellSchool.AIR,
			"poison_vapours_arcane_sickness",
			"Arcane Болезнь",
			"Ядовитые пары наполняются мощной arcane болезнью, которая неуклонно истощает арканную ауру цели.",
			null, Util.newArrayListOfValues(
					"Ядовитые пары дополнительно истощают <b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" за ход")),
	POISON_VAPOURS_3(2,
			SpellSchool.AIR,
			"poison_vapours_weakening_cloud",
			"Ослабляющее облако",
			"Ядовитые пары начинают просачиваться в тело цели, значительно ослабляя её атаки.",
			null, Util.newArrayListOfValues(
					"Ядовитые пары дополнительно накладывают <b>-15</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"Ядовитые пары дополнительно накладывают <b>-25</b> "+Attribute.CRITICAL_DAMAGE.getColouredName("b"))),

	VACUUM_1(true,
			5,
			SpellSchool.AIR,
			"vacuum_secondary_voids",
			"Вторичные пустоты",
			"Вакуум становится сильнее, заставляя цель спотыкаться и с трудом нанося удары.",
			null,
			Util.newArrayListOfValues(
					"Вакуум теперь накладывает:",
					"<b>-15</b> "+Attribute.CRITICAL_DAMAGE.getColouredName("b"),
					"<b>-10</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	VACUUM_2(2,
			SpellSchool.AIR,
			"vacuum_suction",
			"Всасывание",
			"Вакуум стал настолько мощным, что существует шанс, при котором случайный верхний слой одежды цели будет сдернут на пол.<br/>[style.italicsMinorBad(Срывание одежды влияет только на обычных NPC.)]",
			null,
			Util.newArrayListOfValues(
					"Вакуум теперь накладывает:",
					"<b>-25</b> "+Attribute.CRITICAL_DAMAGE.getColouredName("b"),
					"<b>-20</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"<b>10%</b> шанс [style.boldExcellent(срывания)] одежды каждый ход")),
	VACUUM_3(4,
			SpellSchool.AIR,
			"vacuum_total_void",
			"Полная пустота",
			"Сила Вакуума становится сокрушительной, и теперь велик шанс, что случайный верхний слой одежды цели будет сдернут на пол.<br/>[style.italicsMinorBad(Срывание одежды влияет только на обычных NPC.)]",
			null, Util.newArrayListOfValues(
					"Вакуум теперь накладывает:",
					"<b>-25</b> "+Attribute.CRITICAL_DAMAGE.getColouredName("b"),
					"<b>-20</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"Шанс срывания при Всасывании [style.boldExcellent(увеличен)] до <b>25%</b>")),

	PROTECTIVE_GUSTS_1(true,
			1,
			SpellSchool.AIR,
			"protective_gusts_guiding_wind",
			"Направляющий ветер",
			"Ведомые силами арканы, призванные ветры в ключевые моменты оказывают давление на цель, помогая ей уклоняться от входящих атак, а также наносить мощные ответные удары.",
			null, Util.newArrayListOfValues(
					"Бонус «Защитных порывов» увеличен до <b>+2</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"«Защитные порывы» дополнительно накладывают <b>+10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b"))),
	PROTECTIVE_GUSTS_2(1,
			SpellSchool.AIR,
			"protective_gusts_focused_blast",
			"Сосредоточенный взрыв",
			"С каждым ударом арканные ветры толкают цель вперед, помогая ей наносить максимально возможный урон.",
			null, Util.newArrayListOfValues(
					"Бонус «Защитных порывов» увеличен до <b>+3</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b"),
					"«Защитные порывы» дополнительно накладывают <b>+25</b> "+Attribute.CRITICAL_DAMAGE.getColouredName("b"))),
	PROTECTIVE_GUSTS_3(2,
			SpellSchool.AIR,
			"protective_gusts_lingering_presence",
			"Стойкое присутствие",
			"Экономя энергию для тех моментов, когда она нужнее всего, «Защитные порывы» способны поддерживать цель в течение более длительного времени.",
			null, Util.newArrayListOfValues(
					"Защитные порывы теперь длятся [style.boldGood(5 ходов)]")),

	ELEMENTAL_AIR_1(true,
			5,
			SpellSchool.AIR,
			"elemental_air_whirlwind",
			"Вихрь",
			"Элементаль Воздуха призывает кружащийся вихрь, который сбивает с ног и оглушает всех врагов.",
			null, Util.newArrayListOfValues(
					"[style.colourTerrible(Все враги получают)] -5 "+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	ELEMENTAL_AIR_2(5,
			SpellSchool.AIR,
			"elemental_air_vitalising_scents",
			"Оживляющие ароматы",
			"Элементаль Воздуха окружает союзников бодрящими ароматами, наделяя их энергией, необходимой для уклонения от атак и нанесения мощных ответных ударов.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все союзники получают)] +10 "+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"[style.colourExcellent(Все союзники получают)] +5 "+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	ELEMENTAL_AIR_3A(5,
			SpellSchool.AIR,
			"elemental_air_servant_of_air",
			"Слуга Воздуха",
			"Призыватель клянется быть преданным школе Воздуха, и пока его элементаль связан с этой формой, элементаль может черпать из призывателя столько энергии, сколько пожелает.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourAir(Элементаль)]: +100% [style.colourExcellent(Урон без соблазнения)]",
					"[style.colourArcane(Заклинатель)]: -50% [style.colourHealth(макс. "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_AIR_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Узами Воздуха»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Узами Воздуха»!)]";
			}
		}
	},
	ELEMENTAL_AIR_3B(10,
			SpellSchool.AIR,
			"elemental_air_binding_of_air",
			"Узы Воздуха",
			"Призыватель обретает полный контроль над стихией Воздуха, и пока его элементаль привязан к этой форме, он вынужден раскрывать все свои секреты.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourArcane(Заклинатель)]: +25 "+Attribute.DAMAGE_POISON.getColouredName("b"),
					"[style.colourArcane(Заклинатель)]: +10 "+Attribute.RESISTANCE_POISON.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_AIR_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Слугой Воздуха»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Слугой Воздуха»!)]";
			}
		}
	},

	// Earth:

	SLAM_1(true,
			2,
			SpellSchool.EARTH,
			"slam_ground_shake",
			"Дрожь земли",
			"После нанесения урона удар продолжает уходить в землю, заставляя почву под ногами цели содрогаться.",
			null, Util.newArrayListOfValues(
					"<b>-10</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b")+" на [style.colourGood(2 хода)]")),
	SLAM_2(2,
			SpellSchool.EARTH,
			"slam_aftershock",
			"Отголосок",
			"Как только дрожь начинает утихать, из земли вырывается мощный поток силы, поражающий цель.",
			null, Util.newArrayListOfValues(
					"Применяет <b>5</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" когда заканчивается Дрожь земли")),
	SLAM_3(5,
			SpellSchool.EARTH,
			"slam_earthquake",
			"Землетрясение",
			"Удар настолько сильно бьет по земле, что все враги испытывают эффект Дрожи земли.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все враги)] подвергаются эффекту Дрожи земли")),

	TELEKENETIC_SHOWER_1(true,
			5,
			SpellSchool.EARTH,
			"telekinetic_shower_mind_over_matter",
			"Сила мысли",
			"Используя телекинетическую силу, чтобы подхватывать и повторно использовать уже запущенные объекты, заклинатель может поддерживать Телекинетический дождь в течение значительного времени.",
			null, Util.newArrayListOfValues(
					"Телекинетический дождь теперь длится [style.boldGood(6 ходов)]")),
	TELEKENETIC_SHOWER_2(1,
			SpellSchool.EARTH,
			"telekinetic_shower_precision_strikes",
			"Точные удары",
			"Каждый удар Телекинетического дождя наносится с предельной точностью, позволяя обходить и ослаблять физическую защиту цели.",
			null, Util.newArrayListOfValues(
					"Телекинетический дождь дополнительно накладывает <b>-20</b> "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))),
	TELEKENETIC_SHOWER_3(10,
			SpellSchool.EARTH,
			"telekinetic_shower_unseen_force",
			"Невидимая сила",
			"Каждый раз, когда объект из «Телекинетического дождя» поражает цель, высвобождается взрывная волна силы, наносящая значительный урон.",
			null, Util.newArrayListOfValues(
					"Урон «Телекинетического дождя» [style.colourExcellent(удвоен)] для <b>50</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" за ход")),

	STONE_SHELL_1(true,
			1,
			SpellSchool.EARTH,
			"stone_shell_shifting_sands",
			"Зыбучие пески",
			"Теперь «Каменная оболочка» время от времени смещается и рассыпается в текучий песок, а затем быстро восстанавливается в другом месте, чтобы сбить врагов с толку.",
			null, Util.newArrayListOfValues(
					"«Каменная оболочка» дополнительно дает <b>+2</b> "+Attribute.ENERGY_SHIELDING.getColouredName("b"))),
	STONE_SHELL_2(2,
			SpellSchool.EARTH,
			"stone_shell_hardened_carapace",
			"Закаленный панцирь",
			"За «Каменной оболочкой» создается второй слой закаленного камня, что колоссально увеличивает физическую защиту цели.",
			null, Util.newArrayListOfValues(
					"Бонус «Каменной оболочки» увеличен до <b>+10</b> "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))),
	STONE_SHELL_3(1,
			SpellSchool.EARTH,
			"stone_shell_explosive_finish",
			"Взрывной финал",
			"Внутри «Каменной оболочки» накапливается запас телекинетической энергии, которая при окончании действия эффекта высвобождается мощным взрывом.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все враги)] получают <b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" когда действие Каменной оболочки заканчивается")),

	ELEMENTAL_EARTH_1(true,
			5,
			SpellSchool.EARTH,
			"elemental_earth_rolling_stone",
			"Катящийся камень",
			"Земляной элементаль посылает волны силы, чтобы усилить урон, наносимый его союзниками.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все союзники получают)] +15 "+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"[style.colourExcellent(Все союзники получают)] +25 "+Attribute.CRITICAL_DAMAGE.getColouredName("b"))),
	ELEMENTAL_EARTH_2(5,
			SpellSchool.EARTH,
			"elemental_earth_hardening",
			"Закалка",
			"Телекинетические способности земляного элементаля используются для того, чтобы окружить всех союзников защитными осколками камня.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все союзники получают)] +10 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))),
	ELEMENTAL_EARTH_3A(5,
			SpellSchool.EARTH,
			"elemental_earth_servant_of_earth",
			"Слуга Земли",
			"Призыватель клянется быть преданным школе Земли, и пока его элементаль связан с этой формой, элементаль может черпать из призывателя столько энергии, сколько пожелает.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourEarth(Элементаль)]: +100% [style.colourExcellent(Не-соблазняющий урон)]",
					"[style.colourArcane(Заклинатель)]: -50% [style.colourHealth(макс. "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_EARTH_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Узами Земли»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Узами Земли»!)]";
			}
		}
	},
	ELEMENTAL_EARTH_3B(10,
			SpellSchool.EARTH,
			"elemental_earth_binding_of_earth",
			"Узы Земли",
			"Призыватель обретает полный контроль над школой Земли, и пока их элементаль связан с этой формой, он вынужден делиться всеми своими секретами.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourArcane(Заклинатель)]: +25 "+Attribute.DAMAGE_PHYSICAL.getColouredName("b"),
					"[style.colourArcane(Заклинатель)]: +10 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_EARTH_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Слугой Земли»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Слугой Земли»!)]";
			}
		}
	},

	// Arcane:

	ARCANE_AROUSAL_1(true,
			5,
			SpellSchool.ARCANE,
			"arcane_arousal_overwhelming_lust",
			"Всепоглощающая Похоть",
			"Образы, проецируемые в разум цели, становятся особенно похотливыми и порочными.",
			null, Util.newArrayListOfValues(
					"Урон Arcane Возбуждением [style.colourExcellent(удвоен)] по <b>30</b> "+Attribute.DAMAGE_LUST.getColouredName("b"))),
	ARCANE_AROUSAL_2(2,
			SpellSchool.ARCANE,
			"arcane_arousal_lustful_distraction",
			"Похотливое отвлечение",
			"Похотливые образы, проникающие в разум цели, задерживаются там на некоторое время, заставляя её отвлекаться.",
			null, Util.newArrayListOfValues(
					"Цель [style.boldBad(получает)] -15 "+Attribute.ENERGY_SHIELDING.getColouredName("b")+" на [style.boldGood(2 хода)]")),
	ARCANE_AROUSAL_3(2,
			SpellSchool.ARCANE,
			"arcane_arousal_dirty_promises",
			"Грязные обещания",
			"Вместе с похотливыми образами проносятся призрачные шепоты, обещающие цели, что ее ждет незабываемое удовольствие, стоит ей только подчиниться...",
			null, Util.newArrayListOfValues(
					"Цель [style.boldBad(дополнительно получает)] -25 "+Attribute.RESISTANCE_LUST.getColouredName("b"),
					"Длительность увеличена до [style.boldGood(3 ходов)]")),

	TELEPATHIC_COMMUNICATION_1(true,
			5,
			SpellSchool.ARCANE,
			"telepathic_communication_echoing_moans",
			"Эхо стонов",
			"Эффект Телепатической связи теперь можно поддерживать в течение значительного времени. Кроме того, заклинатель всегда готов применить его при первых признаках опасности.",
			null, Util.newArrayListOfValues(
					"Начинать бой с [style.boldExcellent(уже активированной)] Телепатической связью",
					"Длительность увеличена до [style.boldGood(10 ходов)]")),
	TELEPATHIC_COMMUNICATION_2(3,
			SpellSchool.ARCANE,
			"telepathic_communication_projected_touch",
			"Проекция прикосновения",
			"Заклинатель Телепатической связи теперь способен проецировать призрачные силы, заставляя цель ощущать призрачные поцелуи, пока ее разум наполнен стонами.",
			null, Util.newArrayListOfValues(
					"Усиление [style.colourExcellent(doubled)] для <b>30</b> "+Attribute.DAMAGE_LUST.getColouredName("b"))),
	TELEPATHIC_COMMUNICATION_3(3,
			SpellSchool.ARCANE,
			"telepathic_communication_power_of_suggestion",
			"Сила внушения",
			"Заклинатель Телепатического общения способен проецировать мощные внушения в разум любого человека, которого он желает соблазнить, заставляя того ослабить бдительность.",
			null, Util.newArrayListOfValues(
					"[style.boldLust(Дразнение)] [style.boldExcellent(накладывает)] -25 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")+" на цель на [style.boldGood(2 хода)]")),

	ARCANE_CLOUD_1(true,
			3,
			SpellSchool.ARCANE,
			"arcane_cloud_lightning",
			"Arcane молния",
			"Вспышки розовых и пурпурных разрядов Arcane-молний вырываются из Arcane-облака, наполняя разум цели мыслями, полными похоти.",
			null, Util.newArrayListOfValues(
					"<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" за ход")),
	ARCANE_CLOUD_2(5,
			SpellSchool.ARCANE,
			"arcane_cloud_thunder",
			"Arcane-гром",
			"В ушах цели звучат короткие раскаты Arcane-грома, значительно увеличивая получаемый ими урон похоти.",
			null, Util.newArrayListOfValues(
					Attribute.DAMAGE_LUST.getColouredName("b")+" [style.boldExcellent(увеличение)] до <b>15</b> за ход")),
	ARCANE_CLOUD_3(10,
			SpellSchool.ARCANE,
			"arcane_cloud_localised_storm",
			"Локальный шторм",
			"Arcane-облако разрастается в локальный шторм, поражая всех врагов эффектами, наносящими урон похоти.",
			null, Util.newArrayListOfValues(
					"[style.boldExcellent(Все враги)] получают <b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" за ход")),

	CLEANSE_1(true,
			5,
			SpellSchool.ARCANE,
			"cleanse_selective_cleanse",
			"Избирательное очищение",
			"Очищающая энергия теперь способна распознавать природу текущих эффектов состояния, позволяя снимать только отрицательные эффекты с союзников и только положительные — с врагов.",
			null, Util.newArrayListOfValues(
					"С [style.boldExcellent(союзников)] снимаются только [style.boldTerrible(отрицательные)] эффекты",
					"С [style.boldTerrible(врагов)] снимаются только [style.boldExcellent(положительные)] эффекты")),
	CLEANSE_2(5,
			SpellSchool.ARCANE,
			"cleanse_arcane_duality",
			"Дуальность Arcane",
			"Вокруг цели заклинания «Очищение» призывается мерцающий щит из энергии Arcane. Эта энергия способна определить, друг перед ней или враг, и выбирает либо защитить цель, либо ослабить её.",
			null, Util.newArrayListOfValues(
					"+/-5 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b"),
					"+/-5 "+Attribute.RESISTANCE_LUST.getColouredName("b"),
					"+/-5 "+Attribute.RESISTANCE_FIRE.getColouredName("b"),
					"+/-5 "+Attribute.RESISTANCE_ICE.getColouredName("b"),
					"+/-5 "+Attribute.RESISTANCE_POISON.getColouredName("b"),
					"Длится [style.boldGood(3 хода)]")),
	CLEANSE_3(2,
			SpellSchool.ARCANE,
			"cleanse_arcane_will",
			"Воля Arcane",
			"Железная воля заклинателя проецируется на эффект «Дуальности Arcane», значительно увеличивая длительность щита.",
			null, Util.newArrayListOfValues(
					"Длительность «Дуальности Arcane» увеличена до [style.boldGood(6 ходов)]")),

	STEAL_1(true,
			5,
			SpellSchool.ARCANE,
			"steal_stripper",
			"Стриптизерша",
			"Мощь «Кражи» возросла настолько, что теперь она способна срывать одежду, надетую на цель.",
			null, Util.newArrayListOfValues(
					"«Кража» [style.boldExcellent(дополнительно)] может быть направлена на верхние слои одежды")),
	STEAL_2(10,
			SpellSchool.ARCANE,
			"steal_disarm",
			"Разоружение",
			"«Кража» стала достаточно мощной, чтобы телепортировать оружие прямо из рук цели.",
			null, Util.newArrayListOfValues(
					"«Кража» [style.boldExcellent(дополнительно)] может быть направлена на экипированное оружие")),
	STEAL_3A(5,
			SpellSchool.ARCANE,
			"steal_deep_reach",
			"Глубокое проникновение",
			"Мощь «Кражи» возросла до такой степени, что теперь она может поражать любой слой одежды, даже если он не виден заклинателю.",
			null, Util.newArrayListOfValues(
					"«Кража» [style.boldExcellent(дополнительно)] может быть направлена на любой слой одежды")),
	STEAL_3B(10,
			SpellSchool.ARCANE,
			"steal_panty_snatcher",
			"Похититель трусиков",
			"Заклинатель отточил это заклинание до совершенства, так что теперь оно всегда отдает приоритет краже нижнего белья цели перед всем остальным.",
			null, Util.newArrayListOfValues(
					"Украсть [style.boldExcellent(приоритетную)] одежду цели в слоте паха")),

	TELEPORT_1(true,
			10,
			SpellSchool.ARCANE,
			"teleport_arcane_arrival",
			"Прибытие Arcane",
			"По прибытии цель Телепорта выпускает всплеск вызывающей похоть энергии Arcane.",
			null, Util.newArrayListOfValues(
					"Наносит <b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" случайному врагу")),
	TELEPORT_2(15,
			SpellSchool.ARCANE,
			"teleport_mass_teleportation",
			"Массовая телепортация",
			"Теперь заклинатель может применять Телепорт к любому количеству ближайших людей по своему выбору.",
			null, Util.newArrayListOfValues(
					"Телепорт применен ко [style.colourExcellent(всем союзникам)] цели",
					"Телепортация по карте [style.colourExcellent(больше не блокируется)] спутниками")),
	TELEPORT_3(10,
			SpellSchool.ARCANE,
			"teleport_rebounding_teleportation",
			"Рикошетная телепортация",
			"Создание аркана-якоря перед чтением заклинания позволяет целям «Телепорта» вернуться на исходную позицию через несколько секунд.",
			null, Util.newArrayListOfValues(
					"Длительность «Телепорта» увеличена до [style.boldGood(2 ходов)]",
					"<b>5</b> «Аркана-прибытия» "+Attribute.DAMAGE_LUST.getColouredName("b")+" применяется каждый ход")),

	LILITHS_COMMAND_1(true,
			10,
			SpellSchool.ARCANE,
			"liliths_command_overpowering_presence",
			"Подавляющее присутствие",
			"В разум цели проецируется величественный образ самой Lilith, что значительно повышает шансы на эффективность этого заклинания.",
			null, Util.newArrayListOfValues(
					"Шанс успеха [style.colourExcellent(увеличен)] до 50%")),
	LILITHS_COMMAND_2(15,
			SpellSchool.ARCANE,
			"liliths_command_demonic_servants",
			"Демонические слуги",
			"Цель видит видение самой Lilith, повелевающей ей подчиниться. Видение настолько реалистично, что даже демоны теперь могут стать жертвами этого заклинания.",
			null, Util.newArrayListOfValues(
					"«Команда Lilith» теперь [style.colourExcellent(поражает демонов)]",
					"Шанс успеха [style.colourExcellent(увеличен)] до 75%")),
	LILITHS_COMMAND_3(20,
			SpellSchool.ARCANE,
			"liliths_command_ultimate_power",
			"Ультимативная мощь",
			"Видение Lilith, проецируемое в разум цели, настолько реалистично и мощно, что у жертвы не остается надежды противостоять его воздействию.",
			null, Util.newArrayListOfValues(
					"Шанс успеха [style.colourExcellent(увеличен)] до 100%")),

	ELEMENTAL_ARCANE_1(true,
			5,
			SpellSchool.ARCANE,
			"elemental_arcane_lewd_encouragements",
			"Похотливое подстрекательство",
			"Arcane элементаль транслирует похотливые призывы в умы всех своих союзников, вдохновляя их на порочные и соблазнительные действия.",
			null, Util.newArrayListOfValues(
					"[style.colourExcellent(Все союзники получают)] +15 "+Attribute.DAMAGE_LUST.getColouredName("b"))),
	ELEMENTAL_ARCANE_2(5,
			SpellSchool.ARCANE,
			"elemental_arcane_caressing_touch",
			"Ласкающее прикосновение",
			"Arcane элементаль выпускает призрачные щупальца, чтобы ощупывать и ласкать всех ближайших врагов.",
			null, Util.newArrayListOfValues(
					"[style.boldTerrible(Все враги получают)] -15 "+Attribute.RESISTANCE_LUST.getColouredName("b"))),
	ELEMENTAL_ARCANE_3A(5,
			SpellSchool.ARCANE,
			"elemental_arcane_servant_of_arcane",
			"Слуга Arcane",
			"Призыватель клянется быть покорным школе Arcane, и пока их элементаль привязан к этой форме, элементаль может черпать из призывателя столько энергии, сколько пожелает.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourArcane(Элементаль)]: +100% [style.colourExcellent(Не-соблазняющий урон)]",
					"[style.colourArcane(Заклинатель)]: -50% [style.colourHealth(макс. "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_ARCANE_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Связью Arcane»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Связью Arcane»!)]";
			}
		}
	},
	ELEMENTAL_ARCANE_3B(10,
			SpellSchool.ARCANE,
			"elemental_arcane_binding_of_arcane",
			"Связь Arcane",
			"Призыватель обретает полный контроль над школой Arcane, но пока его элементаль привязан к этой форме, он вынужден делиться всеми своими секретами.",
			null, Util.newArrayListOfValues(
					"Пока призван:",
					"[style.colourArcane(Заклинатель)]: +25 "+Attribute.DAMAGE_LUST.getColouredName("b"),
					"[style.colourArcane(Заклинатель)]: +10 "+Attribute.RESISTANCE_LUST.getColouredName("b"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_ARCANE_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Взаимоисключает с «Слугой Arcane»!)]";
			} else {
				return "[style.boldBad(Взаимоисключает с «Слугой Arcane»!)]";
			}
		}
	};

	
	private boolean isAlwaysAvailable;
	private int pointCost;
	private SpellSchool spellSchool;
	private String name;
	private String description;

	private HashMap<AbstractAttribute, Integer> attributeModifiers;
	private List<String> extraEffects;
	private List<String> modifiersList;
	
	private String SVGString;
	
	private SpellUpgrade(int pointCost,
			SpellSchool spellSchool,
			String pathName,
			String name,
			String description,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		this(false, pointCost, spellSchool, pathName, name, description, attributeModifiers, extraEffects);
	}
	
	private SpellUpgrade(boolean isAlwaysAvailable,
			int pointCost,
			SpellSchool spellSchool,
			String pathName,
			String name,
			String description,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		
		this.isAlwaysAvailable = isAlwaysAvailable;
		this.pointCost = pointCost;
		this.spellSchool = spellSchool;
		this.name = name;
		this.description = description;
		
		this.attributeModifiers = attributeModifiers;
		this.extraEffects = extraEffects;
		
		modifiersList = new ArrayList<>();
		
		if (attributeModifiers != null) {
			for (Entry<AbstractAttribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>"
						+ " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
		}
		
		if (extraEffects != null) {
			modifiersList.addAll(extraEffects);
		}
		
		try {
			if(!pathName.isEmpty()) {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/upgrade/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! SpellUpgrade icon file does not exist (Trying to read from '"+pathName+"')!");
				}
				SVGString = Util.inputStreamToString(is);
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlwaysAvailable() {
		return isAlwaysAvailable;
	}
	
	public int getPointCost() {
		return pointCost;
	}

	public boolean isAvailable(GameCharacter caster) {
		return true;
	}
	
	public String getUnavailableReason(GameCharacter caster) {
		return "";
	}
	
	public SpellSchool getSpellSchool() {
		return spellSchool;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

	public HashMap<AbstractAttribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString() {
		return SVGString;
	}
	
}
