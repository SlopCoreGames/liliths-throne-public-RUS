package com.lilithsthrone.game.character.effects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class Perk {
	
	// NPC Histories:
	
	public static AbstractPerk JOB_MISC = new AbstractPerk(20,
			true,
			"Разное",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return ".";//TODO
		}
	};

	public static AbstractPerk JOB_NPC_HARPY_MATRIARCH = new AbstractPerk(20,
			true,
			"королева небес",
			PerkCategory.JOB,
			"perks/jobs/npc_harpy_matriarch",
			PresetColour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] поднялась на позицию матриарха стаи гарпий благодаря сочетанию [npc.her] природной красоты и готовности делать все, что нужно, чтобы править.");
		}
	};

	public static AbstractPerk JOB_NPC_HARPY_FLOCK_MEMBER = new AbstractPerk(20,
			true,
			"Иерархия",
			PerkCategory.JOB,
			"perks/jobs/npc_harpy_flock_member",
			PresetColour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] член стаи гарпий, и как таковая имеет значительный опыт как в лести, так и в подкупе.");
		}
	};

	
	// Enforcers:
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_INSPECTOR = new AbstractPerk(20,
			true,
			"Инспектор: Инспектор",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.MAJOR_ARCANE, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает звание 'Инспектор' в дивизионе 'Фронтовой Патруль' Инспектора, и получила умеренное количество боевой подготовки.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_SERGEANT = new AbstractPerk(20,
			true,
			"Инспектор: Сержант",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает звание 'Сержант' в дивизионе 'Фронтовой Патруль' Инспектора, и получила ограниченное количество боевой подготовки.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_CONSTABLE = new AbstractPerk(20,
			true,
			"Инспектор: Констебль",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 1),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает звание 'Констебль' в дивизионе 'Фронтовой Патруль' Инспектора, и получила очень ограниченное количество боевой подготовки.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_SUPER = new AbstractPerk(20,
			true,
			"Инспектор: Суперинтендант",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_superintendent",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_UNARMED, 35),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 35),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 35)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Супервайзера' в 'Отделе специального оружия и операций', и прошел обширную боевую подготовку.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_CHIEF_INSPECTOR = new AbstractPerk(20,
			true,
			"Enforcer: Главный инспектор SWORD",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_chief_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 20),
					new Value<>(Attribute.MAJOR_ARCANE, 20),
					new Value<>(Attribute.DAMAGE_UNARMED, 30),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 30),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 30)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Главного инспектора' в 'Отделе специального оружия и операций', и прошел обширную боевую подготовку.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_INSPECTOR = new AbstractPerk(20,
			true,
			"Enforcer: Инспектор SWORD",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_UNARMED, 25),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Инспектора' в 'Отделе специального оружия и операций', и прошел обширную боевую подготовку.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_SERGEANT = new AbstractPerk(20,
			true,
			"Enforcer: Сержант SWORD",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.DAMAGE_UNARMED, 20),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 20),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 20)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Сержанта' в 'Отделе специального оружия и операций', и прошел значительную боевую подготовку.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_CONSTABLE = new AbstractPerk(20,
			true,
			"Enforcer: Констебль SWORD",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.MAJOR_ARCANE, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 15),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 15),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 15)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Констебля' в 'Отделе специального оружия и операций', и прошел хорошую боевую подготовку.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_INSPECTOR = new AbstractPerk(20,
			true,
			"Enforcer: Инспектор ORICL",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25),
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.DAMAGE_UNARMED, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Инспектора' в 'Управлении разведки, контрразведки и логистики' и знает множество способов заставить кого-то говорить...");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_SERGEANT = new AbstractPerk(20,
			true,
			"Enforcer: Сержант ORICL",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_ARCANE, 20),
					new Value<>(Attribute.DAMAGE_POISON, 20),
					new Value<>(Attribute.DAMAGE_LUST, 20),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Сержанта' в 'Управлении разведки, контрразведки и логистики' и знает множество способов заставить кого-то говорить...");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_CONSTABLE = new AbstractPerk(20,
			true,
			"Enforcer: Констебль ORICL",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			PresetColour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_POISON, 15),
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] занимает должность 'Констебля' в 'Управлении разведки, контрразведки и логистики' и знает множество способов заставить кого-то говорить...");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_CULTIST = new AbstractPerk(20,
			true,
			"Поклонник Лилит",
			PerkCategory.JOB,
			"perks/jobs/npc_cultist",
			PresetColour.CLOTHING_BLACK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_SPELLS, 25),
					new Value<>(Attribute.DAMAGE_LUST, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] — штатный член «Культа Lilith», и чтобы должным образом поклоняться [npc.her] богине, [npc.sheHasFull] потратила невероятное количество времени, оттачивая как свои способности к [npc.her] заклинаниям, так и искусство соблазнения.");
		}
	};

	public static AbstractPerk JOB_NPC_SLAVER_ADMIN = new AbstractPerk(20,
			true,
			"подозрительный надзиратель",
			PerkCategory.JOB,
			"perks/jobs/npc_slave_admin",
			PresetColour.CLOTHING_BLACK_STEEL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 100),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_LUST, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] является менеджером здания 'Администрация рабов' в Славер-Аллее, и хотя [npc.she] ведет себя достаточно дружелюбно, наверняка, чтобы занять эту должность, понадобилось гораздо больше, чем просто быть милым...");
		}
	};

	public static AbstractPerk JOB_NPC_BOUNCER = new AbstractPerk(20,
			true,
			"вышибала",
			PerkCategory.JOB,
			"perks/jobs/npc_bouncer",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 15),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] является швейцаром, и [npc.has] накопил значительный опыт в боях, работая, чтобы не пускать нежелательных людей в ночные клубы и бары.");
		}
	};

	public static AbstractPerk JOB_NPC_BARMAID = new AbstractPerk(20,
			true,
			"горничная",
			PerkCategory.JOB,
			"perks/jobs/npc_barmaid",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner==null) {
				return "бармен";
			}
			if(owner.isFeminine()) {
				return super.getName(owner);
			}
			return "бармен";
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Благодаря [npc.her] опыту работы в качестве "+(owner.isFeminine()?"горничная":"бармен")+", [npc.name] знает все хитрости флирта, а также как сопротивляться настойчивым ухаживаниям пьяных.");
		}
	};

	public static AbstractPerk JOB_NPC_BEAUTICIAN = new AbstractPerk(20,
			true,
			"идеал красоты",
			PerkCategory.JOB,
			"perks/jobs/npc_beautician",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name] знает все, что нужно, чтобы люди выглядели наилучшим образом.");
		}
	};
	
	public static AbstractPerk JOB_NPC_NIGHTCLUB_OWNER = new AbstractPerk(20,
			true,
			"босс",
			PerkCategory.JOB,
			"perks/jobs/npc_nightclub_owner",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] - очень богатый владелец одного из самых популярных ночных клубов Dominion, и у него нет проблем с тем, чтобы найти компанию на каждый вечер.");
		}
	};

	public static AbstractPerk JOB_NPC_ARCANE_RESEARCHER = new AbstractPerk(20,
			true,
			"@@T0@@ посвятил большую часть своей жизни изучению Арканы и разгадыванию ее секретов.",
			PerkCategory.JOB,
			"perks/jobs/npc_arcane_researcher",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_SPELLS, 50),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] посвятил(а) большую часть [npc.her] жизни изучению арканы и раскрытию её тайн.");
		}
	};

	public static AbstractPerk JOB_NPC_SHOP_MANAGER = new AbstractPerk(20,
			true,
			"менеджер",
			PerkCategory.JOB,
			"perks/jobs/npc_shop_manager",
			PresetColour.CURRENCY_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.ENERGY_SHIELDING, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Давление, связанное с необходимостью добиться успеха в бизнесе [npc.her], заставило [npc.name] работать очень много, в процессе чего он развил [npc.her] устойчивость.");
		}
	};
        
	public static AbstractPerk JOB_NPC_REBEL_FIGHTER = new AbstractPerk(20,
			true,
			"повстанка",
			PerkCategory.JOB,
			"perks/jobs/npc_rebel_fighter",
			PresetColour.CLOTHING_RED_DARK,
			Util.newHashMapOfValues(
				new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
				new Value<>(Attribute.RESISTANCE_LUST, 20),
				new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] тренировки дали [npc.herHim] некоторую защиту от сил Лилит.");
		}
	};
	
	public static AbstractPerk JOB_NPC_FARMER = new AbstractPerk(20,
			true,
			"Накормить мир",
			PerkCategory.JOB,
			"perks/jobs/npc_farmer",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.RESISTANCE_FIRE, 1),
					new Value<>(Attribute.RESISTANCE_ICE, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"#IF(!game.isSillyMode())"
						+ "Утомительная работа, необходимая для того, чтобы заставить поля цвести, сделала [npc.name] сильным и выносливым. [npc.She] также не чурается холода или жары."
					+ "#ELSE"
						+ "[npc.NameHasFull] новый комбайн, и [npc.she] дам вам ключ, но только после того, как вы проедете [npc.her] трактором через ваш стог сена, уар-уар!"
					+ "#ENDIF");
		}
	};
	
	public static AbstractPerk JOB_NPC_JOURNALIST = new AbstractPerk(20,
			true,
			"Идти по краю",
			PerkCategory.JOB,
			"perks/jobs/npc_journalist",
			Util.newArrayListOfValues(PresetColour.CLOTHING_DESATURATED_BROWN, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_WHITE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 5),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "В обществе, где разрешена только версия новостей, предоставленная государством, журналисты должны ходить по очень тонкой линии...");
		}
	};
	
	public static AbstractPerk JOB_NPC_OFFICE_WORKER = new AbstractPerk(20,
			true,
			"Это просто хороший бизнес",
			PerkCategory.JOB,
			"perks/jobs/npc_office_worker",
			PresetColour.CURRENCY_GOLD,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Постоянное давление, связанное с выполнением задач и управлением трудными коллегами и клиентами, выработало у [npc.namePos] выносливость.");
		}
	};

	public static AbstractPerk JOB_NPC_REINDEER_OVERSEER = new AbstractPerk(20,
			true,
			"морозостойкость",
			PerkCategory.JOB,
			"perks/jobs/npc_reindeer_overseer",
			PresetColour.RACE_REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.RESISTANCE_ICE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] смотритель стада оленей, и выработала не только очень сильное тело, но и впечатляющую устойчивость к холоду.");
		}
	};

	public static AbstractPerk JOB_ELDER_LILIN = new AbstractPerk(20,
			true,
			"неприкасаемая",
			PerkCategory.JOB,
			"perks/jobs/elder_lilin",
			PresetColour.RACE_LILIN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 100),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 100),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 100),
					new Value<>(Attribute.DAMAGE_SPELLS, 100),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 100),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 100),
					new Value<>(Attribute.DAMAGE_FIRE, 100),
					new Value<>(Attribute.DAMAGE_ICE, 100),
					new Value<>(Attribute.DAMAGE_POISON, 100),
					new Value<>(Attribute.DAMAGE_LUST, 100),
					new Value<>(Attribute.ENERGY_SHIELDING, 250),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 250),
					new Value<>(Attribute.RESISTANCE_FIRE, 250),
					new Value<>(Attribute.RESISTANCE_ICE, 250),
					new Value<>(Attribute.RESISTANCE_POISON, 250),
					new Value<>(Attribute.RESISTANCE_LUST, 250)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] один из семи старших лилин, и [npc.verb(hold)] силы, о которых простые смертные могут только мечтать.");
		}
	};

	public static AbstractPerk JOB_NPC_SLIME_QUEEN = new AbstractPerk(20,
			true,
			"королевское желе",
			PerkCategory.JOB,
			"perks/jobs/npc_slime_queen",
			Util.newArrayListOfValues(PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 50)),
			null,
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] королева всех слаймов, и может использовать [npc.her] способности к изменению формы, чтобы сделать [npc.herself] выглядеть действительно красиво.");
		}
	};

	public static AbstractPerk JOB_NPC_SLIME_QUEEN_GUARD = new AbstractPerk(20,
			true,
			"слуга слаймов",
			PerkCategory.JOB,
			"perks/jobs/npc_slime_queen_guard",
			PresetColour.RACE_REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] один из личных стражников королевы слаймов, и хорошо обучен в обращении с мечом.");
		}
	};

	public static AbstractPerk JOB_EPONA = new AbstractPerk(20,
			true,
			"королева плодородия",
			PerkCategory.JOB,
			"perks/jobs/npc_epona",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 100),
					new Value<>(Attribute.VIRILITY, 100)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] посвятила [npc.her] всю свою жизнь тому, чтобы помогать другим становиться матерями и отцами, и сейчас она преследует эту благородную цель, проводя игру «беременная рулетка» в игорном доме Submission.");
		}
	};
	
	public static AbstractPerk JOB_GANG_LEADER = new AbstractPerk(20,
			true,
			"безжалостное лидерство",
			PerkCategory.JOB,
			"perks/jobs/npc_rat_gang",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 50),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 50),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] вся сила и хитрость, ожидаемые от того, кто поднялся на должность лидера преступной банды, и [npc.has] к тому же пережил бесчисленное количество попыток оспорить [npc.her] позицию власти.");
		}
	};
	
	public static AbstractPerk JOB_GANG_BODY_GUARD = new AbstractPerk(20,
			true,
			"острейшие клыки",
			PerkCategory.JOB,
			"perks/jobs/npc_rat_gang",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_SILVER),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Будучи признанным одним из самых могущественных членов [npc.her] банды, [npc.name] заработал позицию телохранителя [npc.her] лидера.");
		}
	};
	
	public static AbstractPerk JOB_GANG_MEMBER = new AbstractPerk(20,
			true,
			"один из нас",
			PerkCategory.JOB,
			"perks/jobs/npc_rat_gang",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_COPPER),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.RESISTANCE_POISON, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] член преступной банды, и как таковой, видел [npc.her] свою долю боев.");
		}
	};

	public static AbstractPerk JOB_NPC_STABLE_MISTRESS = new AbstractPerk(20,
			true,
			"тренер лошадей",
			PerkCategory.JOB,
			"perks/jobs/npc_stable_mistress",
			Util.newArrayListOfValues(
					PresetColour.RACE_HORSE_MORPH),
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"В результате упорной работы по тренировке и уходу за огромным количеством рабов-цетавров, [npc.nameIsFull] очень сильный и здоровый, и знает, как справляться с любыми непослушными людьми.");
		}
	};
	
	public static AbstractPerk JOB_LYSSIETH_GUARD = new AbstractPerk(20,
			true,
			"послушная дочь",
			PerkCategory.JOB,
			"perks/jobs/npc_lyssieth_guard",
			Util.newArrayListOfValues(
					PresetColour.DAMAGE_TYPE_PHYSICAL,
					PresetColour.CLOTHING_STEEL,
					PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] не признанная дочь старшего лилина, Лиссит, и ей было поручено защищать дворец своей матери от нежелательных посетителей.");
		}
	};
	
	public static AbstractPerk JOB_TAUR_TRANSPORT = new AbstractPerk(20,
			true,
			"Продолжай тянуть!",
			PerkCategory.JOB,
			"perks/jobs/taur_transport",
			PresetColour.BASE_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Проведя значительное количество времени, используя [npc.her] таврическое тело для тяги телег и перевозки грузов, [npc.nameHasFull] набрал значительный уровень физической подготовки.");
		}
	};
	
	public static AbstractPerk JOB_NPC_MAYOR = new AbstractPerk(20,
			true,
			"Вес ответственности",
			PerkCategory.JOB,
			"perks/jobs/mayor",
			Util.newArrayListOfValues(
					PresetColour.BASE_GOLD,
					PresetColour.BASE_AQUA,
					PresetColour.BASE_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 25),
					new Value<>(Attribute.RESISTANCE_LUST, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Будучи вынужденным принимать бесчисленное количество трудных решений, которые влияют на жизни тысяч людей, [npc.nameHasFull] закалило [npc.her] ум и [npc.is] способно действовать с полной убежденностью.");
		}
	};
	
	public static AbstractPerk JOB_NPC_ASSISTANT = new AbstractPerk(20,
			true,
			"Рука помощи",
			PerkCategory.JOB,
			"perks/jobs/assistant",
			PresetColour.BASE_GREEN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Действуя в качестве личного помощника [npc.her], [npc.nameHasFull] всегда должен быть в отличной физической форме, чтобы всегда быть готовым предложить помощь.");
		}
	};
	
	public static AbstractPerk JOB_LUNETTE_HERD = new AbstractPerk(20,
			true,
			"Беспощадный налётчик",
			PerkCategory.JOB,
			"perks/jobs/lunette_raider",
			Util.newArrayListOfValues(
					PresetColour.BASE_PURPLE,
					PresetColour.BASE_CRIMSON),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Проведя время, тренируясь вместе с самой Лунетт, этот демонический кентавр стремится быть таким же разрушительным и безжалостным, как и мать [npc.her]!");
		}
	};

	public static AbstractPerk JOB_NPC_MUSHROOM_FORAGER = new AbstractPerk(20,
			true,
			"Мастер микологии",
			PerkCategory.JOB,
			"perks/jobs/mushroom_forager",//TODO
			Util.newArrayListOfValues(
					PresetColour.BASE_BLUE_LIGHT,
					PresetColour.BASE_PINK,
					PresetColour.BASE_GREEN),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.RESISTANCE_POISON, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos] тело [npc.has] становится высоко устойчивым к ядам благодаря сложному и часто тошнотворному процессу выявления съедобных, психоделических или токсичных грибов.");
		}
	};

	public static AbstractPerk JOB_LUNETTE_RECOGNISED_DAUGHTER = new AbstractPerk(20,
			true,
			"Любимица Лунетт",
			PerkCategory.JOB,
			"perks/jobs/lunette_raider",
			Util.newArrayListOfValues(
					PresetColour.BASE_GOLD,
					PresetColour.BASE_CRIMSON),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.MAJOR_CORRUPTION, 100),
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] признанная дочь Лунетт, и как таковая, значительно мощнее обычного демона.");
		}
	};

	public static AbstractPerk JOB_ELDER_LILIN_PAWN = new AbstractPerk(20,
			true,
			"Верная пешка",
			PerkCategory.JOB,
			"perks/jobs/elder_lilin_pawn",
			Util.newArrayListOfValues(PresetColour.RACE_LILIN),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.MAJOR_CORRUPTION, 25),
					new Value<>(Attribute.DAMAGE_LUST, 15)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] верная и ценная пешка старшей лилин, благодаря чему ей была дарована часть [npc.her] власти госпожи.");
		}
	};

	public static AbstractPerk JOB_SEX_DOLL = new AbstractPerk(20,
			true,
			"послушная кукла",
			PerkCategory.JOB,
			"perks/jobs/sex_doll",
			Util.newArrayListOfValues(PresetColour.RACE_DOLL),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldGood(Послушен)] любым командам",
					"[style.boldExcellent(Приоритет)] командам владельца",
					"[style.boldBad(Игнорирует)] рабов и других кукол"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] чрезвычайно реалистичная секс-кукла, созданная в 'Lovienne's Luxuries'."
					+ " [npc.She] будет подчиняться любым командам, данным [npc.herHim], если они не от раба или другой куклы, при этом команды от владельца [npc.her] имеют приоритет.");
		}
	};
	
	public static AbstractPerk JOB_AMAZONIAN_QUEEN = new AbstractPerk(20,
			true,
			"Королева Королев",
			PerkCategory.JOB,
			"perks/jobs/npc_amazonian_queen",
			Util.newArrayListOfValues(
					PresetColour.CLOTHING_SILVER),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] королева амазонок, и как таковая, провела жизнь, полную интенсивных физических упражнений и боевой подготовки.");
		}
	};
	
	public static AbstractPerk JOB_AMAZONIAN = new AbstractPerk(20,
			true,
			"Женская сила",
			PerkCategory.JOB,
			"perks/jobs/npc_amazonian",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] амазонка, и как таковая, регулярно занимается физическими упражнениями и боевой подготовкой.");
		}
	};

	public static AbstractPerk JOB_YOUKO = new AbstractPerk(20,
			true,
			"Очаровательная лисица",
			PerkCategory.JOB,
			"perks/jobs/npc_youko",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.DAMAGE_SPELLS, 25),
					new Value<>(Attribute.CRITICAL_DAMAGE, 5)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] юко, родом из Шинринских Высот. [npc.SheHasFull] провела большую часть [npc.her] жизни, практикуясь с арканами, и как таковая, [npc.her] заклинания особенно сильны.");
		}
	};
	
	public static AbstractPerk JOB_PUGILIST = new AbstractPerk(20,
			true,
			"пугалист",
			PerkCategory.JOB,
			"perks/jobs/npc_pugilist",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 50),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] профессиональный боксер, и как таковой, является грозным противником в бою.");
		}
	};
	
	
	public static AbstractPerk JOB_SLAVE = new AbstractPerk(20,
			true,
			"Жизнь в рабстве",
			PerkCategory.JOB,
			"perks/jobs/slave",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] закалила [npc.her] тело и разум, чтобы помочь [npc.herHim] справиться с тем, что [npc.sheIs] просто чья-то собственность.");
		}
	};

	public static AbstractPerk JOB_CAPTIVE = new AbstractPerk(20,
			true,
			"Похищенный",
			PerkCategory.JOB,
			"perks/jobs/npc_captive",
			PresetColour.BASE_RED,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5),
					new Value<>(Attribute.HEALTH_MAXIMUM, -25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] была похищена и незаконно удерживается в плену против [npc.her] воли.");
		}
	};
	
	public static AbstractPerk JOB_PROSTITUTE = new AbstractPerk(20,
			true,
			"Самая древняя профессия",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 50),
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Doubles)] все доходы от рабства и самопроституции")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] имеет опыт продажи [npc.her] тела незнакомцам, чтобы заработать на жизнь. После стольких раз занятий сексом, нужно много, чтобы [npc.herHim] действительно возбудиться.");
		}
	};
	
	public static AbstractPerk JOB_MUGGER = new AbstractPerk(20,
			true,
			"Вне закона",
			PerkCategory.JOB,
			"perks/jobs/mugger",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(Triples)] весь доход от грабежей")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(live)] жизнь преступника, крадущего у богатых и бедных.");
		}
	};
	
	public static AbstractPerk JOB_BOUNTY_HUNTER = new AbstractPerk(20,
			true,
			"Без выхода",
			PerkCategory.JOB,
			"perks/jobs/bounty_hunter",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(-50%)] шанс на побег врага")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] мастер по отслеживанию и поимке беглецов, и поэтому враги с трудом могут сбежать от [npc.herHim] в бою.");
		}
	};
	
	public static AbstractPerk JOB_CONSTRUCTION_WORKER = new AbstractPerk(20,
			true,
			"Строитель",
			PerkCategory.JOB,
			"perks/jobs/npc_construction",
			Util.newArrayListOfValues(PresetColour.CLOTHING_YELLOW, PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] [npc.verb(spent)] некоторое время, работая в строительной отрасли, помогая строить и ремонтировать как инфраструктуру, так и недвижимость.");
		}
	};
	
	public static AbstractPerk JOB_CONSTRUCTION_WORKER_ARCANE = new AbstractPerk(20,
			true,
			"Манипуляция материей",
			PerkCategory.JOB,
			"perks/jobs/npc_construction",
			Util.newArrayListOfValues(PresetColour.CLOTHING_PURPLE, PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MANA_MAXIMUM, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Умение использовать Аркана, и, следовательно, способность телекинетически манипулировать вещами, [npc.nameIsFull] очень ценный актив для строительных фирм иметь в своем штате.");
		}
	};
	
	
	
	
	// Player Histories:
	
	public static AbstractPerk JOB_UNEMPLOYED = new AbstractPerk(20,
			true,
			"NEET",
			PerkCategory.JOB,
			"perks/jobs/unemployed",
			PresetColour.BASE_RED,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 2),
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues("[style.boldExcellent(Boosts)] бонус 'Хорошо отдохнувший'")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "У [npc.herPos] было так много свободного времени, что [npc.nameHas] смог улучшить [npc.herself] несколькими разными способами."
					+ " [npc.Name] также [npc.verb(benefit)] от знания того, как лучше всего расслабиться, увеличивая бонус [npc.she] [npc.verb(get)] от сна.");
		}
	};
	
	public static AbstractPerk JOB_OFFICE_WORKER = new AbstractPerk(20,
			true,
			"Служащий",
			PerkCategory.JOB,
			"perks/jobs/officeWorker",
			PresetColour.BASE_BROWN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(+25%)] все доходы рабыни")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "Карьера Женщины";
			} else {
				return "Служащий";
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Из [npc.herPos] значительного опыта работы в офисе, [npc.name] [npc.verb(know)] точно, как мотивировать тех, кто работает под [npc.herPro]."
					+ "Стрессовая рабочая среда заставила [npc.herPro] затаить много разочарований, что проявляется в увеличении критического урона.");
		}
	};
	
	public static AbstractPerk JOB_STUDENT = new AbstractPerk(20,
			true,
			"Студенческая скидка",
			PerkCategory.JOB,
			"perks/jobs/student",
			PresetColour.BASE_YELLOW,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10)),
			Util.newArrayListOfValues("[style.boldExcellent(25%)] скидка во всех магазинах")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] студенческая скидка никогда не подводила [npc.herPro]!"
					+ "Наряду с гарантированной 25% скидкой во всех магазинах, [npc.she] может быть уверен в способности [npc.herPos] быстро осваивать новые вещи.");
		}
	};
	
	public static AbstractPerk JOB_MUSICIAN = new AbstractPerk(20,
			true,
			"Arcane Состав",
			PerkCategory.JOB,
			"perks/jobs/musician",
			PresetColour.BASE_GREY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(Double)] продолжительность всех эффектов заклинаний")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(find)] что [npc.herPos] способности как музыканта довольно хорошо переводятся на искусство соблазнения. [npc.She] [npc.verb(feel)] тот же самый ритм при чтении заклинаний, что и [npc.she] [npc.do] с музыкой, в результате чего все [npc.herPos] эффекты заклинаний длятся в два раза дольше обычного.");
		}
	};
	
	public static AbstractPerk JOB_TEACHER = new AbstractPerk(20,
			true,
			"Контроль",
			PerkCategory.JOB,
			"perks/jobs/teacher",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10)),
			Util.newArrayListOfValues("[style.boldExcellent(Triple)] все награды за покорность")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, " [npc.Name] [npc.verb(know)] как именно обращаться с непослушными учениками."
					+ " [npc.HerPos] способность четко понимать и объяснять сложные предметы отражается в снижении стоимости заклинаний.");
		}
	};
	
	public static AbstractPerk JOB_WRITER = new AbstractPerk(20,
			true,
			"Медитации",
			PerkCategory.JOB,
			"perks/jobs/writer",
			PresetColour.BASE_PURPLE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(+25%)] ко всем наградам за опыт")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, " [npc.Name] [npc.verb(keep)] дневник [npc.herPos] личных мыслей и встреч, позволяющий [npc.herPro] размышлять и учиться на [npc.herPos] опыте."
					+ " [npc.HerPos] живой интерес к книгам также позволяет [npc.herPro] быстро изучать наиболее эффективные способы применения заклинаний.");
		}
	};

	public static AbstractPerk JOB_CHEF = new AbstractPerk(20,
			true,
			"Изысканный вкус",
			PerkCategory.JOB,
			"perks/jobs/chef",
			PresetColour.BASE_ORANGE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 25)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Double)] продолжительность всех эффектов зелий",
					"[style.boldExcellent(Doubles)] предел эффекта зелий")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря тому, что [npc.name] [npc.has] проводит значительное время, пробуя еду, [npc.name] [npc.has] обладает как значительной устойчивостью к яду, так и способностью создавать кулинарные чудеса из простых ингредиентов.");
		}
	};

	public static AbstractPerk JOB_PLAYER_CONSTRUCTION_WORKER = new AbstractPerk(20,
			true,
			"Проджект-менеджер",
			PerkCategory.JOB,
			"perks/jobs/construction",
			Util.newArrayListOfValues(PresetColour.CLOTHING_YELLOW, PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.HEALTH_MAXIMUM, 10),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues("[style.boldExcellent(Halves)] стоимость всех улучшений комнат"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря [npc.her] большому опыту в этой области, [npc.name] [npc.verb(know)] как эффективно управлять строительными проектами."
					+ " [npc.Her] время, проведенное в физическом труде, также дало [npc.herHim] крепкое здоровье.");
		}
	};
	

	public static AbstractPerk JOB_SOLDIER = new AbstractPerk(20,
			true,
			"Контролируемая агрессия",
			PerkCategory.JOB,
			"perks/jobs/soldier",
			PresetColour.BASE_GREEN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.HEALTH_MAXIMUM, 25),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10)),
			Util.newArrayListOfValues("Ваш урон [style.boldExcellent(удваивается)] в первой атаке.")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHas] потратил много времени на тренировки, и в результате, [npc.she] [npc.is] намного сильнее и здоровее, чем обычный человек."
					+ "Благодаря этим тренировкам, [npc.she] [npc.is] также может направлять [npc.herPos] агрессию в [npc.herPos] атаки.");
		}
	};

	public static AbstractPerk JOB_ATHLETE = new AbstractPerk(20,
			true,
			"Десятисекундная Барьер",
			PerkCategory.JOB,
			"perks/jobs/athlete",
			PresetColour.BASE_TEAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
			Util.newArrayListOfValues("Все шансы на побег в бою, отличные от нуля, увеличиваются до [style.boldExcellent(100%)].")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] sprinter мирового класса, и [npc.has] гарантированно 100% шанс на побег из любой боевой ситуации, где есть возможность убежать.");
		}
	};

	public static AbstractPerk JOB_ARISTOCRAT = new AbstractPerk(20,
			true,
			"Голубая кровь",
			PerkCategory.JOB,
			"perks/jobs/aristocrat",
			PresetColour.BASE_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 25)),
			Util.newArrayListOfValues(
					"Все [style.colourCorruption(corruption)] бонусы [style.colourBad(удваиваются)]",
					"[style.colourGood(+0.25)] до [style.colourExcellent(всех сопротивлений)] за 1 единицу порочности")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] из древней аристократической семьи, и [npc.has] получил лучшее образование, на которое можно потратить деньги."
					+ "Невыносимо высокомерный, [npc.name] [npc.verb(know)] что [npc.sheIsFull] лучше всех остальных, и не позволит ни одному грязному крестьянину превзойти [npc.herHim]!");
		}
	};
	
	
	public static AbstractPerk JOB_MAID = new AbstractPerk(20,
			true,
			"Домохозяйка",
			PerkCategory.JOB,
			"perks/jobs/maid",
			PresetColour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 5),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Boosted)] Бонус за комплект слуги",
					"[style.boldExcellent(Double)] Двойной доход от слуг")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] идеальный пример трудолюбивой слуги, и, надев полный комплект служебной одежды, бонус, который [npc.she] [npc.verb(receive)] значительно усиливается."
					+ " [npc.She] также [npc.verb(know)] как тренировать слуг и других слуг, чтобы они были исключительными в своей работе.");
		}
	};

	public static AbstractPerk JOB_BUTLER = new AbstractPerk(20,
			true,
			"Наследие Дживса",
			PerkCategory.JOB,
			"perks/jobs/butler",
			PresetColour.BASE_BLUE_STEEL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.RESISTANCE_LUST, 25)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Boosted)] Бонус за комплект слуги",
					"[style.boldExcellent(Double)] Двойной доход от слуг")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] идеальный пример трудолюбивого слуги, и, надев полный комплект служебной одежды, бонус, который [npc.she] [npc.verb(receive)] значительно усиливается."
					+ " [npc.She] также [npc.verb(know)] как тренировать девушек и других слуг, чтобы они были исключительны в своей работе.");
		}
	};

	public static AbstractPerk JOB_TOURIST = new AbstractPerk(20,
			true,
			"Я американец!",
			PerkCategory.JOB,
			"perks/jobs/tourist",
			PresetColour.BASE_BLUE_DARK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25)),
			Util.newArrayListOfValues(
					"Все неправильные английские орфографии [style.boldExcellent(автоматически исправлены)]",
					"Враги ослеплены вашим [style.colourFreedom(freedom)] на [style.colourExcellent(-1)] <span style='color:"+Attribute.ACTION_POINTS.getColour().toWebHexString()+";'>"+Attribute.ACTION_POINTS.getName()+"</span>",
					"Эффекты бургеров [style.colourGood(удвоены)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.speech(Извините! Я американец! Вы говорите по-английски?! Моя виза истекает через четыре дня, где посольство?!)]");
		}
	};
	
	// Physical:
	
	public static AbstractPerk PHYSICAL_BASE = new AbstractPerk(20,
			false,
			"природная форма",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.has] естественное количество физической подготовки.");
		}
	};

	public static AbstractPerk CRITICAL_BOOST = new AbstractPerk(20,
			false,
			"критический удар",
			PerkCategory.PHYSICAL,
			"perks/critical_power",
			PresetColour.BASE_ORANGE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] способен действительно максимально использовать любые критические удары [npc.she] [npc.verb(make)].");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_LUST = new AbstractPerk(20,
			false,
			"критический удар",
			PerkCategory.LUST,
			"perks/critical_power",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] способен действительно максимально использовать любые критические удары [npc.she] [npc.verb(make)].");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_ARCANE = new AbstractPerk(20,
			false,
			"критический удар",
			PerkCategory.ARCANE,
			"perks/critical_power",
			PresetColour.BASE_PURPLE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_SPELLS, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] способен действительно максимально использовать любые критические удары [npc.she] [npc.verb(make)].");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST = new AbstractPerk(20,
			false,
			"в отличной форме",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил много времени на улучшение [npc.her] физической подготовки.");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST_ALT = new AbstractPerk(20,
			false,
			"в отличной форме",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил много времени на улучшение физической подготовки [npc.her].");
		}
	};

	public static AbstractPerk PHYSIQUE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"в отличной форме",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря упорному труду и настойчивости, [npc.nameHasFull] смог значительно улучшить уровень физической подготовки [npc.her].");
		}
	};
	
	public static AbstractPerk PHYSICAL_DAMAGE = new AbstractPerk(20,
			false,
			"ударник",
			PerkCategory.PHYSICAL,
			"perks/strike",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Тренируясь без помощи Аркана, [npc.nameHasFull] смог улучшить урон от физических атак [npc.her].");
		}
	};

	public static AbstractPerk UNARMED_DAMAGE = new AbstractPerk(20,
			false,
			"рукопашный бой",
			PerkCategory.PHYSICAL,
			"perks/unarmed_damage",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_UNARMED, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] провел много часов, тренируясь в рукопашном бою, что сделало [npc.herHim] опасным противником даже без оружия.");
		}
	};

	public static AbstractPerk MELEE_DAMAGE = new AbstractPerk(20,
			false,
			"эксперт по ближнему бою",
			PerkCategory.PHYSICAL,
			"perks/melee_damage",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Проведя много времени, тренируясь с различными видами холодного оружия, [npc.nameIsFull] теперь очень опасный противник в ближнем бою.");
		}
	};

	public static AbstractPerk RANGED_DAMAGE = new AbstractPerk(20,
			false,
			"меткий стрелок",
			PerkCategory.PHYSICAL,
			"perks/ranged_damage",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Умелый в использовании всех видов дистанционного оружия, [npc.nameIsFull] действительно ужасный противник в дальнем бою.");
		}
	};

	public static AbstractPerk BESERK = new AbstractPerk(20,
			true,
			"берсерк",
			PerkCategory.PHYSICAL,
			"perks/beserk",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 20),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -2),
					new Value<>(Attribute.DAMAGE_SPELLS, -15),
					new Value<>(Attribute.DAMAGE_LUST, -15)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Поскольку у него мало времени на стратегии, не подразумевающие демонстрацию сокрушительной мощи, [npc.name] [npc.verb(throw)] [npc.herself] в бой, совершенно не заботясь о [npc.her] собственной безопасности.");
		}
	};
	
	public static AbstractPerk PHYSICAL_DEFENCE = new AbstractPerk(20,
			false,
			"защитник",
			PerkCategory.PHYSICAL,
			"perks/shield",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил много времени на улучшение способности [npc.her] парировать физические атаки.");
		}
	};

	public static AbstractPerk HYPERMOBILITY = new AbstractPerk(20,
			false,
			"гипермобильность",
			PerkCategory.PHYSICAL,
			"perks/hypermobility",
			PresetColour.BASE_WHITE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 15)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Разблокирует)] авто fellatio и autocunnilingus [style.colourSex(сексуальные действия)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] исключительно гибкий, что позволяет [npc.herHim] наносить удары с совершенно неожиданных углов."
					+ "Как [npc.sheIs], способный согнуться до такой степени, что [npc.her] голова касается [npc.her] паха, [npc.sheIs] может заниматься оральным сексом с [npc.herself].");
		}
	};
	
	public static AbstractPerk SPELL_DAMAGE = new AbstractPerk(20,
			false,
			"сила заклинаний",
			PerkCategory.ARCANE,
			"perks/arcane_power_1",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Проведя время, практикуя заклинания, [npc.nameHasFull] открыл новые способы повышения их эффективности.");
		}
	};
	
	public static AbstractPerk SPELL_DAMAGE_MAJOR = new AbstractPerk(20,
			false,
			"мастерство заклинаний",
			PerkCategory.ARCANE,
			"perks/arcane_power_3",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Проведя много времени, сосредоточившись на улучшении силы заклинаний [npc.her], [npc.nameIsFull] теперь может использовать их гораздо эффективнее, чем раньше.");
		}
	};
	
	public static AbstractPerk SPELL_EFFICIENCY = new AbstractPerk(20,
			false,
			"эффективность заклинаний",
			PerkCategory.ARCANE,
			"perks/spell_efficiency",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] смог улучшить эффективность заклинаний [npc.her]!");
		}
	};
	
	public static AbstractPerk AURA_BOOST = new AbstractPerk(20,
			false,
			"запасы ауры",
			PerkCategory.ARCANE,
			"perks/resource_boost",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "После того, как [npc.nameHasFull] провел довольно много времени, сосредотачиваясь на себе, [npc.nameHasFull] смог увеличить запасы ауры [npc.her].");
		}
	};
	
	public static AbstractPerk ENERGY_BOOST = new AbstractPerk(20,
			false,
			"запасы энергии",
			PerkCategory.PHYSICAL,
			"perks/resource_boost",
			PresetColour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] увеличил запасы энергии [npc.her], что позволяет [npc.herHim] выдерживать удары, которые раньше могли бы вывести [npc.herHim] из боя.");
		}
	};

	public static AbstractPerk ENERGY_BOOST_DRAIN_DAMAGE = new AbstractPerk(20,
			true,
			"аура-щит",
			PerkCategory.PHYSICAL,
			"perks/resource_boost_drain_aura",
			PresetColour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 20),
					new Value<>(Attribute.MANA_MAXIMUM, -25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Концентрируя [npc.her] арканную ауру в защитный барьер, [npc.nameIsFull] может выдержать больше урона, чем [npc.she] смог бы в противном случае.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST = new AbstractPerk(20,
			false,
			"стихийный боец",
			PerkCategory.LUST,
			"perks/elemental_damage",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 25),
					new Value<>(Attribute.DAMAGE_ICE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] посвятил [npc.herself] изучению того, как более эффективно использовать все арканические элементы.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST_ALT = new AbstractPerk(20,
			false,
			"стихийный боец",
			PerkCategory.LUST,
			"perks/elemental_damage",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 25),
					new Value<>(Attribute.DAMAGE_ICE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] посвятил [npc.herself] изучению того, как более эффективно использовать все арканические элементы.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"стихийный боец",
			PerkCategory.LUST,
			"perks/elemental_damage",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 25),
					new Value<>(Attribute.DAMAGE_ICE, 25),
					new Value<>(Attribute.DAMAGE_POISON, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] посвятил(а) [npc.herself] изучению того, как более эффективно обуздать все стихии Арканы.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_DEFENCE_BOOST = new AbstractPerk(20,
			false,
			"стихийный защитник",
			PerkCategory.LUST,
			"perks/elemental_defence",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_FIRE, 10),
					new Value<>(Attribute.RESISTANCE_ICE, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил время на изучение того, как лучше всего защитить [npc.herself] от арканов.");
		}
	};
	
	
	public static AbstractPerk ARCANE_BASE = new AbstractPerk(20,
			false,
			"естественная аркана",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "У каждого в этой реальности есть аркана, независимо от того, насколько она слабая, и поэтому у каждого есть хотя бы немного арканы.";
		}
	};
	
	public static AbstractPerk ARCANE_BOOST = new AbstractPerk(20,
			false,
			"обучение Аркане",
			PerkCategory.ARCANE,
			"perks/attIntelligence1",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] сосредоточился на улучшении [npc.her] способности использовать аркана.");
		}
	};

	public static AbstractPerk ARCANE_BOOST_ALT = new AbstractPerk(20,
			false,
			"обучение Аркане",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] сосредоточился на улучшении [npc.her] способности использовать аркана.");
		}
	};

	public static AbstractPerk ARCANE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"родство с Арканой",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] раскрыл секреты арканы, о которых мало кто знает.");
		}
	};
	
	
	public static AbstractPerk LEWD_KNOWLEDGE = new AbstractPerk(20,
			false,
			"похотливые знания",
			PerkCategory.LUST,
			"perks/lewd_knowledge",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 1),
					new Value<>(Attribute.DAMAGE_LUST, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(know)] точно знает, какими непристойными делами занимаются жители Dominion, и может использовать эти знания, чтобы [npc.her] в свою пользу...");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST = new AbstractPerk(20,
			false,
			"соблазнительный",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил много времени на изучение техник соблазнения [npc.her], что позволяет [npc.herHim] наносить дополнительный урон при подтрунивании над другими.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT = new AbstractPerk(20,
			false,
			"соблазнительный",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил много времени на изучение техник соблазнения [npc.her], что позволяет [npc.herHim] наносить дополнительный урон при подтрунивании над другими.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"соблазнительный",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил много времени на изучение техник соблазнения [npc.her], что позволило [npc.herHim] наносить дополнительный урон при teasing других.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"соблазнительный",
			PerkCategory.LUST,
			"perks/attSeduction5",
			PresetColour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ходячая сексуальная бомба. [npc.Her] каждое движение пропитано сексуальным подтекстом, и люди не могут не чувствовать сильное возбуждение, просто глядя на [npc.herHim].");
		}
	};
	
	public static AbstractPerk SEDUCTION_DEFENCE_BOOST = new AbstractPerk(20,
			false,
			"сопротивление",
			PerkCategory.LUST,
			"perks/shield",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] закаленный [npc.herself], способный противостоять любому искушению, что значительно повышает шансы на то, что [npc.she] удастся не возбудиться во время дразнилок.");
		}
	};
	
	public static AbstractPerk VIRILITY_BOOST = new AbstractPerk(20,
			false,
			"витальный",
			PerkCategory.LUST,
			"perks/virile",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] тело способно производить очень мощную сперму, увеличивая вероятность того, что любой сексуальный партнер [npc.hers] забеременеет.");
		}
	};
	
	public static AbstractPerk VIRILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"витальный",
			PerkCategory.LUST,
			"perks/virile",
			PresetColour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] тело способно производить невероятно мощную сперму, значительно увеличивая вероятность того, что любой сексуальный партнер [npc.hers] забеременеет.");
		}
	};

	public static AbstractPerk FERTILITY_BOOST = new AbstractPerk(20,
			false,
			"плодовитый",
			PerkCategory.LUST,
			"perks/fertile",
			PresetColour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] тело очень плодородное, увеличивая шансы того, что любой сексуальный партнер [npc.hers] сможет забеременеть [npc.herHim].");
		}
	};

	public static AbstractPerk FERTILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"плодовитый",
			PerkCategory.LUST,
			"perks/fertile",
			PresetColour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] тело невероятно плодородное, что увеличивает вероятность того, что любой сексуальный партнер [npc.hers] сможет забеременеть [npc.herHim].");
		}
	};
	
	
	public static AbstractPerk ARCANE_COMBATANT = new AbstractPerk(20,
			false,
			"арканный боец",
			PerkCategory.ARCANE,
			"perks/physical_brawler",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 10),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] мастер в использовании Аркана для боевых целей. [npc.She] [npc.verb(gain)] бонус к урону и эффективности заклинаний [npc.her].");
		}
	};
	
	public static AbstractPerk SACRIFICIAL_SHIELDING = new AbstractPerk(20,
			true,
			"жертвенная защита",
			PerkCategory.ARCANE,
			"perks/sacrificial_shielding",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -10),
					new Value<>(Attribute.ENERGY_SHIELDING, 2)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Жертвуя частью своей физической силы [npc.her], [npc.nameIsFull] получает способность призывать всеобъемлющий защитный щит.");
		}
	};

	public static AbstractPerk ARCANE_VAMPYRISM = new AbstractPerk(20,
			true,
			"арканный вампиризм",
			PerkCategory.ARCANE,
			"perks/arcane_vampyrism",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, -25)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Поглощение 50%)] от любого бойца оставшейся [style.colourMana("+Attribute.MANA_MAXIMUM.getName()+")]",
					 "когда они повержены")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] натренировал(а) свою арканную ауру [npc.her] так, чтобы она обладала вампирической природой, меньше полагаясь на собственные резервы и больше на способность высасывать ауру из любого (друга или врага), кто будет повержен поблизости.");
		}
	};
	
	
	public static AbstractPerk FEROCIOUS_WARRIOR = new AbstractPerk(20,
			false,
			"свирепый воин",
			PerkCategory.PHYSICAL,
			"perks/physical_brawler",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 5),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 2)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] обладает огромным боевым опытом, что делает [npc.herHim] опасным противником для любого, кто окажется достаточно глуп, чтобы бросить вызов [npc.herHim].");
		}
	};
	
	
	public static AbstractPerk OBSERVANT = new AbstractPerk(60,
			true,
			"наблюдательный",
			PerkCategory.PHYSICAL,
			"perks/misc_observant",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Определение пола</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner!=null && owner.isPlayer()) {
				return "Вы очень проницательны и способны заметить малейшие изменения в окружающем мире. Вы всегда можете определить пол человека, даже если понятия не имеете, как выглядит его пах.";
			} else {
				return UtilText.parse(owner, "[npc.Name] очень проницателен/на, и [npc.she] постоянно сканирует [npc.her] окружение на предмет признаков опасности.");
			}
		}
	};
	

	public static AbstractPerk HEAVY_SLEEPER = new AbstractPerk(60,
			true,
			"крепко спящий",
			PerkCategory.PHYSICAL,
			"statusEffects/спячка",
			PresetColour.SLEEP_HEAVY,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Не просыпается во время [style.colourSex(нежного)] секса",
					"[style.boldExcellent(10x)] скорость регенерации [style.colourHealth(здоровья)] и [style.colourMana(ауры)]")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner!=null) {
				return UtilText.parse(owner,
						"[npc.Name] [npc.verb(sleep)] очень глубоко, причём даже крики и физические потряхивания [npc.herHim] часто не помогают разбудить [npc.herHim]."
							+" [npc.SheIs] настолько нечувствителен во сне, что нежный партнер может заниматься сексом с [npc.herHim], не боясь разбудить [npc.herHim]...");
			}
			return "";
		}
	};
	
	// Arcane:
	
	public static AbstractPerk ARCANE_CRITICALS = new AbstractPerk(60,
			false,
			"arcane точность",
			PerkCategory.ARCANE,
			"perks/physical_accurate",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 5)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_COMBAT.toWebHexString()+ ";'>Критические</span> заклинания наносят <b style='color:"+PresetColour.ATTRIBUTE_LUST.toWebHexString()+";'>Слабость к Arcane</b>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинания особенно эффективны, когда поражают слабые места цели."
					+ " Любые критические попадания от [npc.her] заклинаний накладывают 'Аркана слабость' на один ход (-10 к всем щитам).");
		}
	};
	
//	
//	TELEPATHY(60,
//			true,
//			"arcane telepathy",
//			PerkCategory.ARCANE,
//			"perks/misc_observant",
//			PresetColour.GENERIC_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5)),
//			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Telepathic seduction</span>")) {
//		@Override
//		public String applyPerkGained(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String applyPerkLost(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "By concentrating your arcane power into the mind of others, you're able to deliver a .";
//			} else {
//				return UtilText.parse(owner, "[npc.Name] is very perceptive, and [npc.she] continuously scans [npc.her] surroundings for signs of danger.");
//			}
//		}
//	};
	
//	SPELL_POWER_1(20,
//			true,
//			"arcane power",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_1",
//			PresetColour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 5)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused on improving your ability to harness the arcane and cast spells.";
//			else
//				return UtilText.parse(owner, "[npc.Name] seems reasonably competent at casting spells.");
//		}
//	};
//	SPELL_POWER_2(20,
//			true,
//			"arcane conduit",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_2",
//			PresetColour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 10)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused your ability to harness the arcane to the point where you can greatly enhance the effects of any spell.";
//			else
//				return UtilText.parse(owner, "[npc.Name] is highly competent at harnessing the arcane and improving [npc.her] spells.");
//		}
//	};
//	SPELL_POWER_3(20,
//			true,
//			"arcane mastery",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_3",
//			PresetColour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 15)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "You are a master at harnessing the arcane. Even if you didn't have an aura as strong as a demon's, you'd still be one of the greatest arcane users in Dominion.";
//			} else
//				return UtilText.parse(owner, "[npc.Name] is a master of harnessing the arcane and improving [npc.her] spells.");
//		}
//	};

	public static AbstractPerk FIRE_ENHANCEMENT = new AbstractPerk(20,
			false,
			"огненный знак",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "У вас повышенная склонность к аркана огню. Вы знаете, как им управлять, чтобы максимизировать наносимый урон.";
			else
				return UtilText.parse(owner, "[npc.Name] имеет повышенную склонность к аркана огню. [npc.She] знает, как им управлять, чтобы максимизировать наносимый урон.");
		}
	};

	public static AbstractPerk FIRE_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"зажигательный",
			PerkCategory.ARCANE,
			"perks/arcane_fire_1",
			PresetColour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 10),
					new Value<>(Attribute.RESISTANCE_FIRE, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Вы мастер в управлении аркана огнем. Вы не только можете максимизировать его урон, но и обладаете повышенной устойчивостью к его эффектам.";
			else
				return UtilText.parse(owner, "[npc.Name] - мастер по манипуляции аркана огня. Не только [npc.she] способен максимизировать его повреждения, но и [npc.she] обладает повышенной устойчивостью к его эффектам.");
		}
	};

	public static AbstractPerk COLD_ENHANCEMENT = new AbstractPerk(20,
			false,
			"морозный",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "У вас повышенная склонность к арканизму льда. Вы знаете, как манипулировать им, чтобы максимизировать наносимый урон.";
			else
				return UtilText.parse(owner, "[npc.Name] имеет повышенную склонность к арканизму льда. [npc.She] знает, как манипулировать им, чтобы максимизировать наносимый урон.");
		}
	};

	public static AbstractPerk COLD_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"ледяной",
			PerkCategory.ARCANE,
			"perks/arcane_ice_1",
			PresetColour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 10),
					new Value<>(Attribute.RESISTANCE_ICE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Вы мастер в манипуляции арканизмом льда. Вы не только можете максимизировать его урон, но и иметь повышенную устойчивость к его эффектам.";
			else
				return UtilText.parse(owner, "[npc.Name] — эксперт в манипуляциях с арканическим льдом. [npc.she] не только способен максимизировать его урон, но и обладает повышенной сопротивляемостью к его эффектам, что также свойственно [npc.she].");
		}
	};

	public static AbstractPerk POISON_ENHANCEMENT = new AbstractPerk(20,
			false,
			"ядовитый",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "У вас повышенная склонность к арканизму яда. Вы знаете, как манипулировать им, чтобы максимизировать наносимый урон.";
			else
				return UtilText.parse(owner, "[npc.Name] имеет повышенную склонность к арканизму яда. [npc.She] знает, как манипулировать им, чтобы максимизировать наносимый урон.");
		}
	};

	public static AbstractPerk POISON_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"токсичный",
			PerkCategory.ARCANE,
			"perks/arcane_poison_1",
			PresetColour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Вы мастер в манипуляции арканизмом яда. Вы не только можете максимизировать его урон, но и иметь повышенную устойчивость к его эффектам.";
			else
				return UtilText.parse(owner, "[npc.Name] - мастер по обращению с аркана-яд. [npc.she] не только умеет максимизировать его вред, но и [npc.she] обладает повышенной устойчивостью к его действию.");
		}
	};

	// Fitness:
	public static AbstractPerk RUNNER = new AbstractPerk(20,
			true,
			"бегун",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 3)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Улучшенная вероятность побега</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Ты прирожденный бегун и обладаешь хорошим уровнем выносливости.";
			else
				return UtilText.parse(owner, "[npc.Name] прирожденный бегун и обладает хорошим уровнем выносливости.");
		}
	};

	public static AbstractPerk RUNNER_2 = new AbstractPerk(20,
			true,
			"чемпион кардио",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner_2",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Улучшенная вероятность побега</span>")) {
		@Override
		public String getName(GameCharacter character) {
			if (character!=null && character.isFeminine()) {
				return "Королева кардио";
			} else {
				return "Король кардио";
			}
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] " + (owner.isFeminine() ? "королева" : "король") + " кардио, обладающий, казалось бы, бесконечным запасом энергии.");
		}
	};

	public static AbstractPerk COMBAT_REGENERATION = new AbstractPerk(20,
			true,
			"боевая регенерация",
			PerkCategory.PHYSICAL,
			"perks/regeneration",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, -25)),
			Util.newArrayListOfValues("[style.boldExcellent(Восстанавливать 5%)] общего [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")] за ход в бою")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "За счет некоторого [npc.namePos] "+Attribute.MANA_MAXIMUM.getName()+", природные исцеляющие свойства аркана теперь усиливаются при присутствии [npc.her] адреналина.");
		}
	};
	
	public static AbstractPerk UNARMED_TRAINING = new AbstractPerk(20,
			false,
			"драчун",
			PerkCategory.PHYSICAL,
			"perks/natural_fighter",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5),
					new Value<>(Attribute.DAMAGE_UNARMED, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] хорошую драку, и [npc.is] страшного противника в рукопашной схватке.");
		}
	};

	public static AbstractPerk FEMALE_ATTRACTION = new AbstractPerk(60,
			false,
			"ловелас",
			PerkCategory.LUST,
			"perks/fitness_female_attraction",
			PresetColour.FEMININE,
			null, Util.newArrayListOfValues("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>урон похотью</span> против <span style='color:" + PresetColour.FEMININE.toWebHexString()+ ";'>женственных противников</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] очень кокетливый, и хотя [npc.her] чары работают хорошо на обоих полах, [npc.she] [npc.verb(find)] что [npc.her] ухаживания особенно эффективны против женщин.");
		}

	};

	public static AbstractPerk MALE_ATTRACTION = new AbstractPerk(60,
			false,
			"кокетка",
			PerkCategory.LUST,
			"perks/fitness_male_attraction",
			PresetColour.MASCULINE,
			null, Util.newArrayListOfValues("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>урон похотью</span> против <span style='color:" + PresetColour.MASCULINE.toWebHexString()+ ";'>мужественные противники</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] довольно провокационно, и хотя [npc.her] чары работают на обоих полах, [npc.she] [npc.verb(find)] что [npc.her] ухаживания особенно эффективны против мужчин.");
		}
	};
	
	public static AbstractPerk CONVINCING_REQUESTS = new AbstractPerk(20,
			true,
			"непреодолимо манят",
			PerkCategory.LUST,
			"perks/convincing_requests",
			Util.newArrayListOfValues(PresetColour.GENERIC_SEX, PresetColour.BASE_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues(
					"Запросы, сделанные во время обычных сцен секса, всегда удовлетворяются",
					"Разблокирует позиции в всех обычных сценах секса"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] освоила искусство сексуального обольщения, и [npc.is] может убедить даже самых жестоких доминирующих партнеров сделать то, что [npc.she] [npc.verb(ask)].");
		}
	};
	
	public static AbstractPerk OBJECT_OF_DESIRE = new AbstractPerk(20,
			true,
			"объект желания",
			PerkCategory.LUST,
			"perks/object_of_desire",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues("Все партнеры по сексу требуют [style.colourSex(+1 оргазм)] перед тем, как они будут удовлетворены")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] настолько поразительно "+(owner.getFemininity()==Femininity.ANDROGYNOUS?"привлекательный":(owner.isFeminine()?"прекрасные":"красивый"))
					+" что [npc.her] сексуальные партнеры не могут не продолжать трахаться, даже после того, как они получили свой первый оргазм.");
		}
	};

	public static AbstractPerk ORGASMIC_LEVEL_DRAIN = new AbstractPerk(20,
			true,
			"уровень оргазма",
			PerkCategory.LUST,
			"perks/orgasmic_level_drain",
			Util.newArrayListOfValues(PresetColour.GENERIC_SEX, PresetColour.GENERIC_EXPERIENCE),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldУжасно(-95% ко всем)] [style.boldОпыт(приобретение опыта)]",
					"Во всех [style.boldSex(сценах секса)]:",
					"Может выбрать [style.boldTerrible(выкачивать 1 уровень)] из партнеров по оргазму",
					"Вы получаете [style.boldExcellent(5)] [style.boldExperience(опыта)] умноженного на уровень, выкачанный из партнера"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Путем сложного манипулирования [npc.her] арканной аурой, [npc.name] [npc.has] начинают гораздо медленнее усваивать новый опыт, но взамен [npc.is] теперь способен вытягивать опыт сексуальных партнеров, достигающих оргазма в [npc.her] присутствии.");
		}
	};
	
	public static AbstractPerk NYMPHOMANIAC = new AbstractPerk(20,
			true,
			"нимфоманка",
			PerkCategory.LUST,
			"perks/fitness_nymphomaniac",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 5),
					new Value<>(Attribute.RESISTANCE_LUST, -2)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(x2)] [style.colourArcane(Эссенция)] от оргазма",
					"Получаете [style.colourSex(накопление)] и [style.colourSex(девственность)] статусные эффекты [style.colourTerrible(2x быстрее)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] полностью и безнадежно зависим от секса.");
		}
	};
	
	public static AbstractPerk AHEGAO = new AbstractPerk(20,
			true,
			"ахегао",
			PerkCategory.LUST,
			"perks/ahegao",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.RESISTANCE_LUST, -5)),
			Util.newArrayListOfValues("@@T0@@ полностью и безнадежно одержим сексом")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Оргазмы [npc.NamePos] особенно интенсивны, и [npc.she] не может не делать преувеличенное выражение лица каждый раз, когда [npc.she] [npc.verb(климакс)].");
		}
	};

	public static AbstractPerk LUSTPYRE = new AbstractPerk(20,
			false,
			"огненная похоть",
			PerkCategory.LUST,
			"perks/lustful_leech",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Поглощение 2%)] от максимального "+Attribute.MANA_MAXIMUM.getName()+" на каждом ударе, который наносит [style.boldПохоть("+Attribute.DAMAGE_LUST.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Сама аркана реагирует на [npc.namePos] похотливые ухаживания, поглощая небольшое количество [npc.her] вражеской "+Attribute.MANA_MAXIMUM.getName()+" каждый раз, когда [npc.she] дразнит их.");
		}
	};

	public static AbstractPerk PURE_MIND = new AbstractPerk(20,
			false,
			"чистые мысли",
			PerkCategory.LUST,
			"perks/pure_mind",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Восстановление 2%)] от вашего максимального "+Attribute.MANA_MAXIMUM.getName()+" на каждом ударе, в котором вы испытываете [style.boldПохоть("+Attribute.DAMAGE_LUST.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Защита [npc.her] от любых похотливых попыток, [npc.nameIsFull] может входить в состояние медитации,"
					+ " реагируя на любой урон от похоти [npc.she] [npc.verb(receive)] путем восстановления некоторого количества [npc.her] "+Attribute.MANA_MAXIMUM.getName()+".");
		}
	};
	
	
	public static AbstractPerk CLOTHING_ENCHANTER = new AbstractPerk(20,
			false,
			"ткач арканы",
			PerkCategory.ARCANE,
			"perks/arcaneWeaver",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Половина стоимости всех заклинаний на одежду</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] природная склонность к созданию арканных заклинаний на предметах одежды, позволяющая [npc.herHim] тратить только половину обычной арканной эссенции при создании заклинаний на одежде.");
		}
	};
	
	public static AbstractPerk WEAPON_ENCHANTER = new AbstractPerk(20,
			false,
			"кузнец арканы",
			PerkCategory.PHYSICAL,
			"perks/arcaneSmith",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Половина стоимости всех арканных заклинаний</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] природная склонность к созданию арканных заклинаний на оружии, позволяющая [npc.herHim] тратить только половину обычной арканной эссенции при создании заклинаний на оружии.");
		}
	};
	
	public static AbstractPerk ENCHANTMENT_STABILITY = new AbstractPerk(20,
			false,
			"стабильные чары",
			PerkCategory.PHYSICAL,
			"perks/enchantment_stability",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потраченное время на тренировку [npc.her] тела и ума, чтобы справиться с более высоким "+Attribute.ENCHANTMENT_LIMIT.getName()+", позволяющая [npc.herHim] надевать больше арканных заклинаний на оружие, одежду и татуировки.");
		}
	};

	public static AbstractPerk ENCHANTMENT_STABILITY_ALT = new AbstractPerk(20,
			false,
			"стабильные чары",
			PerkCategory.ARCANE,
			"perks/enchantment_stability",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потраченное время на тренировку [npc.her] тела и ума, чтобы справиться с более высоким "+Attribute.ENCHANTMENT_LIMIT.getName()+", позволяющая [npc.herHim] надевать больше арканных заклинаний на оружие, одежду и татуировки.");
		}
	};
	
	public static AbstractPerk BARREN = new AbstractPerk(20,
			true,
			"бесплодная",
			PerkCategory.LUST,
			"perks/barren",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, -200)),
			Util.newArrayListOfValues("Пока "+Attribute.FERTILITY.getName()+" значение равно 0 или меньше, беременность [style.colourTerrible(impossible)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] очень бесплодна, и как следствие, [npc.is] крайне маловероятно, что она когда-нибудь забеременеет.");
		}
	};
	
	public static AbstractPerk FIRING_BLANKS = new AbstractPerk(20,
			true,
			"стерильная",
			PerkCategory.LUST,
			"perks/firing_blanks",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, -200)),
			Util.newArrayListOfValues("Пока "+Attribute.VIRILITY.getName()+" значение равно 0 или меньше, оплодотворение [style.colourTerrible(impossible)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] семя невероятно слабое, и [npc.sheIs] маловероятно, что оно когда-либо забеременеет.");
		}
	};
	

	
	public static AbstractPerk FETISH_BROODMOTHER = new AbstractPerk(20,
			true,
			"матерь выводка",
			PerkCategory.LUST,
			"fetishes/fetish_broodmother",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 20)),
			Util.newArrayListOfValues("2x <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Максимальное количество потомства в материнских пометах</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Ваше тело создано для одной вещи; выжимать как можно больше детей."
							+ " Будь то из-за эффекта вашей арканой ауры, или, возможно, из-за естественной фертильности вашего тела, вы всегда рождаете огромное количество детей за один раз.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] тело создано для одной вещи; выжимать как можно больше детей."
						+ " [npc.She] всегда рождаете огромное количество детей за один раз.");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	public static AbstractPerk FETISH_SEEDER = new AbstractPerk(20,
			true,
			"сеятель",
			PerkCategory.LUST,
			"fetishes/fetish_seeder",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 20)),
			Util.newArrayListOfValues("2x <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Максимальное количество потомства в отцовских пометах</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Ваше семя имеет мощное действие, вызывающее у любого, кто им забеременеет, рождение огромного количества детей.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] семя имеет мощное действие, вызывающее у любого, кто им забеременеет, рождение огромного количества детей.");
			}
		}

		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	public static AbstractPerk CHUUNI = new AbstractPerk(20,
			true,
			"чуни",
			PerkCategory.ARCANE,
			"perks/misc_chuuni",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 20),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Затруднительный диалог заклинания</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Приблизительно переведенное с японского как 'Синдром второго года средней школы', те, кто страдает 'чуунибиё', верят и действуют так, как будто они обладают особыми силами."
					+ "Хотя чууни когда-то могли быть чисто бредовыми, Аркана теперь придает некоторую правду их убеждениям...");
		}
	};

	public static AbstractPerk SPECIAL_PLAYER = new AbstractPerk(20,
			false,
			"аномальная аура",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 18)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "У вас удивительно большое количество природной Арканы; гораздо больше, чем у обычного человека.";
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MERAXIS = new AbstractPerk(20,
			false,
			"Темная Сирена",
			PerkCategory.ARCANE,
			"perks/dark_siren",
			PresetColour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 100),
					new Value<>(Attribute.MANA_MAXIMUM, 100),
					new Value<>(Attribute.ENCHANTMENT_LIMIT, 100)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Мераксис - это ни кто иной, как сама Темная Сирена! Будучи признанной дочерью старшего лилина Лиссита, она обладает значительным мастерством в использовании Арканы.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_ARCANE_TATTOOIST = new AbstractPerk(20,
			false,
			"Татуировщик Arcane",
			PerkCategory.ARCANE,
			"perks/tattoo",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.ENCHANTMENT_LIMIT, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] прошла обширную подготовку, чтобы научиться наносить татуировки с Арканическими заклинаниями.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE = new AbstractPerk(20,
			false,
			"безразличие к мужской одежде",
			PerkCategory.PHYSICAL,
			"perks/clothingIndifferenceMasculinity",
			Util.newArrayListOfValues(PresetColour.MASCULINE, PresetColour.CLOTHING_GREY),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к эффекту статуса 'слишком мужская одежда'</span>"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] всегда [npc.verb(feel)] комфортно носить мужскую одежду, какой бы женственной ни была [npc.her] тело.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE = new AbstractPerk(20,
			false,
			"безразличие к женской одежде",
			PerkCategory.PHYSICAL,
			"perks/clothingIndifferenceFemininity",
			Util.newArrayListOfValues(PresetColour.FEMININE, PresetColour.CLOTHING_GREY),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к эффекту статуса 'слишком женская одежда'</span>"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] всегда [npc.verb(feel)] комфортно в женской одежде, каким бы маскулинным ни был корпус [npc.her].");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	// HIDDEN PERKS:

	public static AbstractPerk SPECIAL_DIRTY_MINDED = new AbstractPerk(20,
			false,
			"распущенная",
			PerkCategory.ARCANE,
			"statusEffects/attCorruption5",
			PresetColour.ATTRIBUTE_CORRUPTION,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] очень распущенная, и её мысли часто [npc.verb(find)] [npc.her], полные секса.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_SLUT = new AbstractPerk(20,
			false,
			"шлюха",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			PresetColour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15),
					new Value<>(Attribute.MAJOR_CORRUPTION, 40)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] имела бесчисленное множество сексуальных партнеров, и [npc.has] совершала с ними всевозможные похотливые акты.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MEGA_SLUT = new AbstractPerk(20,
			false,
			"развратная",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			PresetColour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.MAJOR_CORRUPTION, 75)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда не занимается сексом с совершенно незнакомыми людьми, [npc.nameIsFull] почти всегда фантазирует о сексе и думает о том, какой следующий непристойный акт [npc.she] должен совершить с [npc.her].");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ARCANE_TRAINING = new AbstractPerk(20,
			false,
			"обучение Аркане",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.DAMAGE_SPELLS, 20)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] прошла обширную подготовку, чтобы научиться использовать Аркана.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ARCANE_LIGHTNING = new AbstractPerk(20,
			false,
			"исключительный арканист",
			PerkCategory.ARCANE,
			"perks/special_arcane_lightning",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 25),
					new Value<>(Attribute.MANA_MAXIMUM, 100),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25),
					new Value<>(Attribute.DAMAGE_SPELLS, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря исключительным событиям в прошлом [npc.her], [npc.nameHasFull] не только получила огромную природную силу Аркана, но и врожденное понимание того, как лучше всего использовать Аркана.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_ARCANE_ALLERGY = new AbstractPerk(20,
			false,
			"аллергия на аркану",
			PerkCategory.ARCANE,
			"perks/arcane_allergy",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, -50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] страдает от редкой аллергической реакции на Аркана, и поэтому почти полностью неспособна использовать его силу.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_HEALTH_FANATIC = new AbstractPerk(20,
			false,
			"фанатик здоровья",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] одержим(а) [npc.her] личной физической формой и проводит(а) часы каждый день, тренируясь и планируя [npc.her] рацион.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MARTIAL_BACKGROUND = new AbstractPerk(20,
			false,
			"боевое прошлое",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратила много времени на тренировку тела [npc.her] для боя, и в результате, [npc.sheIs] намного сильнее, чем среднестатистический человек.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_SHORT_SIGHTED = new AbstractPerk(60,
			false,
			"нарушение зрения",
			PerkCategory.PHYSICAL,
			"statusEffects/близорукость",
			Util.newArrayListOfValues(
					PresetColour.BASE_BLACK,
					PresetColour.GENERIC_BAD,
					PresetColour.GENERIC_BAD),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Когда [style.colourMinorBad(не носит очки)] страдает от [style.colourTerrible(расплывчатого зрения)]"),
			null, null, null) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPerfectVision()) {
				return UtilText.parse(owner,
						"[npc.Name] обычно страдает от нарушения зрения, но благодаря [npc.her] [npc.eyeRace] [npc.eyes], [npc.she] может видеть идеально, не нуждаясь в очках.");
			}
			return UtilText.parse(owner,
					"[npc.Name] [npc.verb(страдает)] от нарушения зрения, и в результате [npc.she] [npc.verb(борется)] за то, чтобы видеть без ношения очков."
					+ "Когда [npc.she] [npc.is] носит корректирующую оптику, однако, зрение [npc.her] не менее чем идеально.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MELEE_EXPERT = new AbstractPerk(20,
			false,
			"мастер ближнего боя",
			PerkCategory.PHYSICAL,
			"perks/melee_damage",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] a considerable amount of experience with wielding melee weapons, making [npc.herHim] a fearsome foe to face when armed with a weapon of [npc.her] choice.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_RANGED_EXPERT = new AbstractPerk(20,
			false,
			"мастер дальнего боя",
			PerkCategory.PHYSICAL,
			"perks/ranged_damage",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] значительный опыт владения метательным оружием, что делает [npc.herHim] грозным противником, когда в его руках оказывается оружие на [npc.her] вкус.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CHILD_OF_THE_CRAG = new AbstractPerk(20,
			true,
			"Сын Крага",
			PerkCategory.PHYSICAL_WATER,
			"perks/cragchild",
			PresetColour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 30),
					new Value<>(Attribute.RESISTANCE_ICE, 50),
					new Value<>(Attribute.RESISTANCE_FIRE, -15)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Кровь Цимеры течет в жилах [npc.namePos], и [npc.she] давно привык(ла) к холоду жизни высоко в горах. Однако из-за такой отдаленности от [npc.her] сородичей из пустыни, [npc.her] естественная сопротивляемость огню ослабла.");
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ENFORCER_FIREARMS_TRAINING = new AbstractPerk(20,
			true,
			"Мастерство владения огнестрельным оружием",
			PerkCategory.PHYSICAL,
			"perks/enforcer_firearms",
			Util.newArrayListOfValues(
					PresetColour.DAMAGE_TYPE_FIRE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_GUNMETAL),
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 75)),
			Util.newArrayListOfValues("[style.colourExcellent(Doubles)] весь урон от огнестрельного оружия"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] не только успешно завершил программу подготовки по обращению с огнестрельным оружием, требуемую от всех SWORD Enforcers, но и получил оценку 'Мастер'."
					+ "Без сомнения, это оставило некоторые темные, затянувшиеся воспоминания, но в результате [npc.sheIs] стал одним из самых искусных #IF(npc.isFeminine())markswomen#ELSEmarksmen#ENDIF в королевстве.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	//**** Special perks which can be gained from in-game events: ****//

	public static AbstractPerk PIX_TRAINING = new AbstractPerk(20,
			false,
			"Тренировка Пикса",
			PerkCategory.PHYSICAL,
			"perks/pix_trained",
			Util.newArrayListOfValues(
				PresetColour.ATTRIBUTE_PHYSIQUE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 2),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1)),
			null,
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Вы пережили несколько интенсивных тренировок с самым возбужденным и мотивированным физическим тренером Dominion, и теперь чувствуете себя заметно сильнее.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk MARTIAL_ARTIST = new AbstractPerk(20,
			false,
			"мастер боевых искусств",
			PerkCategory.PHYSICAL,
			"perks/unarmed_training",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.CRITICAL_DAMAGE, 25)),
			Util.newArrayListOfValues("[style.colourUnarmed(Базовый урон без оружия)] [style.colourExcellent(удвоенный)]"),
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] прошел официальную подготовку по боевым искусствам, что делает [npc.herHim] крайне опасным человеком даже без оружия.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk IMP_SLAYER = new AbstractPerk(20,
			false,
			"думгай",
			PerkCategory.ARCANE,
			"perks/imp_slayer",
			PresetColour.RACE_IMP,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_IMP, 100)),
			null) {

		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "девочка-рок";
			} else {
				return super.getName(owner);
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "<i>Это всё та же старая хрень. Люди думают, что могут со мной связаться, и есть только один способ доказать им обратное!</i>");
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk HERO_OF_THEMISCYRA = new AbstractPerk(20,
			false,
			"Герой Темискиры",
			PerkCategory.ARCANE,
			"perks/hero_of_themiscyra",
			PresetColour.BASE_GOLD,
			Util.newHashMapOfValues(
					new Value<>(Attribute.getRacialDamageAttribute(Race.HORSE_MORPH), 25),
					new Value<>(Attribute.getRacialDamageAttribute(Race.DEMON), 25)),
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "Героиня Темискиры";
			} else {
				return super.getName(owner);
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return "<i>Небо, фонтаны, каждый регион поблизости"
					+ "<br/>Казалось, все вокруг кричали одно и то же: Я никогда не слышал"
					+ "<br/>Такой музыкальный дисгармоничный звук, такой сладкий гром.</i>";
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk AMAZONIAN_TRAINING = new AbstractPerk(20,
			false,
			"Амазонская тренировка",
			PerkCategory.ARCANE,
			"perks/amazonian_training",
			Util.newArrayListOfValues(
				PresetColour.DAMAGE_TYPE_PHYSICAL,
				PresetColour.CLOTHING_DESATURATED_BROWN,
				PresetColour.CLOTHING_KHAKI),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, 1),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 1),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 1)),
			null,
			null,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Участвуя в нескольких спаррингах с Аурокарисом, вы чувствуете, что узнали кое-что новое о том, как сражаться.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	
	//**** Elder lilin perks: ****//
	
	public static AbstractPerk POWER_OF_LIRECEA_1 = new AbstractPerk(20,
			false,
			"Сила Лиреции",
			PerkCategory.ARCANE,
			"perks/lilin1",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] [style.boldLightBlue(превращения в рыб)] если персонаж — [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лирецы была впитана в вашу арканическую ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"Её сила также позволила вам превратить своё демоническое тело в тело любой водной сущности!"
							:"Если бы вы были демоном, эта сила позволила бы вам превратить части своего тела в части любой водной сущности!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LIRECEA_1_DEMON = new AbstractPerk(20,
			false,
			"Истинная сила Лиреа",
			PerkCategory.ARCANE,
			"perks/lilin1",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] [style.boldLightBlue(превращения в рыб)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лиреа была впитана в вашу арканическую ауру."
					+ "Её сила была усилена вашей демонической формой, которая также позволила вам превратить своё демоническое тело в тело любой водной сущности!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LOVIENNE_2 = new AbstractPerk(21,
			false,
			"Сила Ловьенн",
			PerkCategory.ARCANE,
			"perks/lilin2",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] [style.boldFeral(дикие трансформации)], если [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ловиенны была впитана в вашу арканическую ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"Её сила также позволила вам превратить своё демоническое тело в тело диких животных!"
							:"Если бы вы были демоном, эта сила позволила бы вам превратить части своего тела в части диких животных!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LOVIENNE_2_DEMON = new AbstractPerk(21,
			false,
			"Истинная сила Ловиенн",
			PerkCategory.ARCANE,
			"perks/lilin2",
			PresetColour.RACE_BESTIAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] [style.boldFeral(дикие трансформации)]",
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команде Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ловиенн была впитана в вашу арканическую ауру."
					+ "Её сила была усилена вашей демонической формой, которая также позволила вам превратить своё демоническое тело в тело диких животных!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LASIELLE_3 = new AbstractPerk(22,
			false,
			"Сила Ласьель",
			PerkCategory.ARCANE,
			"perks/lilin3",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] способность превращать других в [style.boldDemon(демонов)] если демон", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команде Лилит)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ласиэль была впитана в вашу арканическую ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"Её сила также позволила вам превращать других в демонов!"
							:"Если бы вы были демоном, эта сила позволила бы вам превращать других в демонов!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LASIELLE_3_DEMON = new AbstractPerk(22,
			false,
			"Истинная сила Ласиэль",
			PerkCategory.ARCANE,
			"perks/lilin3",
			PresetColour.RACE_DEMON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] способность превращать других в [style.boldDemon(демонов)]",
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команде Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ласиэль была впитана в вашу арканическую ауру."
					+ "Её сила была усилена вашей демонической формой, что также позволило вам превращать других в демонов!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LYSSIETH_4 = new AbstractPerk(23,
			false,
			"Сила Lyssieth",
			PerkCategory.ARCANE,
			"perks/lilin4",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 25)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] [style.boldHuman(человеческие превращения)] если демон",
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команде Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лисиэли была впитана в вашу арканическую ауру."
					+ "Если бы ты был демоном, эта сила усилилась бы, и также позволила бы тебе превратить свои части тела в части тела обычного человека!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LYSSIETH_4_DEMON = new AbstractPerk(23,
			false,
			"Истинная сила Лиссит",
			PerkCategory.ARCANE,
			"perks/lilin4",
			PresetColour.RACE_HUMAN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50),
					new Value<>(Attribute.getRacialDamageAttribute(Race.HUMAN), 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] [style.boldHuman(человеческие трансформации)]",
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команды Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лиссит была впитана в твою арканическую ауру."
					+ "Ее сила была усилена твоей демонической формой, которая также позволила тебе превратить свои демонические части тела в части тела обычного человека!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LUNETTE_5 = new AbstractPerk(24,
			false,
			"Сила Лунетт",
			PerkCategory.ARCANE,
			"perks/lilin5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] [style.boldHorse(трансформации млекопитающих)] если [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команды Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лунетт была впитана в твою арканическую ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"Ее сила дополнительно позволила тебе превратить свое демоническое тело в тело обычного человека!"
							:"Если бы ты был демоном, эта сила позволила бы тебе превратить части своего тела в части обычного человека!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LUNETTE_5_DEMON = new AbstractPerk(24,
			false,
			"Истинная сила Лунетт",
			PerkCategory.ARCANE,
			"perks/lilin5",
			PresetColour.RACE_HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] [style.boldHorse(млекопитающие трансформации)]",
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команда Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лунетт была впитана в твою арканическую ауру."
					+ "Ее сила была усилена твоей демонической формой, что также позволило тебе превратить свои демонические части тела в части тела млекопитающих!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LYXIAS_6 = new AbstractPerk(25,
			false,
			"Сила Ликсиас",
			PerkCategory.ARCANE,
			"perks/lilin6",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] [style.boldHarpy(птичьи трансформации)], если [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команда Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ликсиас была впитана в твою арканическую ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"Ее сила дополнительно позволила тебе превратить свое демоническое тело в тело птицы!"
							:"Если бы ты был демоном, эта сила позволила бы тебе превращать свои части тела в части тела птиц!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LYXIAS_6_DEMON = new AbstractPerk(25,
			false,
			"Истинная сила Ликсиас",
			PerkCategory.ARCANE,
			"perks/lilin6",
			PresetColour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] [style.boldHarpy(птичьи трансформации)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ликсиас была впитана в твою арканическую ауру."
					+ "Ее сила была усилена твоей демонической формой, что также позволило тебе превращать свои демонические части тела в части тела птиц!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LISOPHIA_7 = new AbstractPerk(26,
			false,
			"Сила Лисофии",
			PerkCategory.ARCANE,
			"perks/lilin7",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldGreenLight(рептильные и амфибийные трансформации)], если [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лиссит был впитана в твою арканическую ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?"Ее сила дополнительно позволила тебе превращать свое демоническое тело в тело рептилий и амфибий!"
							:"Если бы ты был демоном, эта сила позволила бы тебе превращать свои части тела в рептилий и амфибий!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LISOPHIA_7_DEMON = new AbstractPerk(26,
			false,
			"Истинная сила Лисофии",
			PerkCategory.ARCANE,
			"perks/lilin7",
			PresetColour.BASE_GREEN_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] [style.boldGreenLight(рептилий и амфибий трансформации)]",
					"[style.boldExcellent(Immunity)] к [style.boldArcane(Команде Лилит)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лисофии была впитана в твою арканическую ауру."
					+ "Ее сила была усилена твоей демонической формой, что также позволило тебе превращать свои демонические части тела в рептилий и амфибий!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SINGLE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Однохвостая Юко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail1",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldFox(трансформации Юко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] юко; тип арканически-усиленной лис-морф, обычно встречающийся только в Шинринских Высотах. [npc.Her] служба более высокоранговой юко позволила [npc.him] одной арканической хвосте.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk TWO_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Двуххвостая Юко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail2",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Открывает)] [style.boldFox(трансформации ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] лис-морф; тип аркана-усиленного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] служба более высокоранговому лис-морфу позволила [npc.him] иметь два аркана-хвоста.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk THREE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Треххвостый лис-морф",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail3",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Открывает)] [style.boldFox(трансформации ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] лис-морф; тип аркана-усиленного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] служба более высокоранговому лис-морфу позволила [npc.him] иметь три аркана-хвоста.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk FOUR_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Четыреххвостый лис-морф",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail4",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Открывает)] [style.boldFox(трансформации ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] лис-морф; тип аркана-усиленного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] служба более высокоранговому лис-морфу позволила [npc.him] иметь четыре аркана-хвоста.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk FIVE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Пятихвостый лис-морф",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail5",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Открывает)] [style.boldFox(трансформации ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] лис-морф; тип аркано-мощного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] Служба у более высокорангового лис-морфа позволила [npc.him] иметь пять аркановых хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk SIX_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Шестихвостый лис-морф",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail6",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldFox(трансформации лис-морфа!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] лис-морф; тип аркано-мощного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] Служба у более высокорангового лис-морфа позволила [npc.him] иметь шесть аркановых хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk SEVEN_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Семихвостый лис-морф",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail7",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldFox(трансформации лис-морфа!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] лис-морф; тип аркано-мощного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] Служба у более высокорангового лис-морфа позволила [npc.him] иметь семь аркановых хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk EIGHT_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Восьмихвостый лис-морф",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail8",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldFox(трансформации лис-морфа!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] лис-морф; тип аркано-мощного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] Служба у более высокорангового лис-морфа позволила [npc.him] иметь восемь аркановых хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk NINE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Девятихвостая Юко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail9",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(трансформации Юко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] Юко; это тип аркано-мощного лис-морфа, обычно встречающийся только в Шинринских Высотах. [npc.Her] Служба у более высокоранговой Юко позволила [npc.him] обрести девять аркановых хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	// SPECIFIC TO ELEMENTAL PERK TREE:
	
//	public static AbstractPerk ELEMENTAL_BOUND_EARTH = new AbstractPerk(20,
//			true,
//			"Bound to Earth",
//			PerkCategory.JOB,
//			"combat/spell/elemental_earth",
//			PresetColour.SPELL_SCHOOL_EARTH,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 50),
//					new Value<>(Attribute.DAMAGE_PHYSICAL, 50),
//					new Value<>(Attribute.RESISTANCE_PHYSICAL, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Earth, [npc.name] has gained a strong, tough body that is extremely resilient to physical damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using physical attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_FIRE = new AbstractPerk(20,
//			true,
//			"Bound to Fire",
//			PerkCategory.JOB,
//			"combat/spell/elemental_fire",
//			PresetColour.SPELL_SCHOOL_FIRE,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
//					new Value<>(Attribute.DAMAGE_FIRE, 50),
//					new Value<>(Attribute.RESISTANCE_FIRE, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Fire, [npc.name] has gained an ethereal body that is extremely resilient to fire damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using fire-based attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_WATER = new AbstractPerk(20,
//			true,
//			"Bound to Water",
//			PerkCategory.JOB,
//			"combat/spell/elemental_water",
//			PresetColour.SPELL_SCHOOL_WATER,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 20),
//					new Value<>(Attribute.DAMAGE_ICE, 50),
//					new Value<>(Attribute.RESISTANCE_ICE, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Water, [npc.name] has gained "+(owner.getBodyMaterial()==BodyMaterial.WATER?"a liquid-like ":"an ice-like ")+"body that is extremely resilient to ice damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using ice-based attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_AIR = new AbstractPerk(20,
//			true,
//			"Bound to Air",
//			PerkCategory.JOB,
//			"combat/spell/elemental_air",
//			PresetColour.SPELL_SCHOOL_AIR,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
//					new Value<>(Attribute.DAMAGE_POISON, 50),
//					new Value<>(Attribute.RESISTANCE_POISON, 50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Air, [npc.name] has gained an ethereal body that is extremely resilient to poison damage."
//					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using poison-based attacks.");
//		}
//	};
//
//	public static AbstractPerk ELEMENTAL_BOUND_ARCANE = new AbstractPerk(20,
//			true,
//			"Bound to Arcane",
//			PerkCategory.JOB,
//			"combat/spell/elemental_arcane",
//			PresetColour.SPELL_SCHOOL_AIR,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
//					new Value<>(Attribute.DAMAGE_LUST, 50),
//					new Value<>(Attribute.DAMAGE_SPELLS, 25),
//					new Value<>(Attribute.SPELL_COST_MODIFIER, 25),
//					new Value<>(Attribute.RESISTANCE_LUST, -50)),
//			null) {
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "By being bound to the school of Arcane, [npc.name] has gained an ethereal body that capable of inflicting great damage by using lust-based attacks."
//					+ " [npc.She] has also become more adept at casting spells, but the arcane's arousing power has left [npc.herHim] more susceptible to lust-based attacks.");
//		}
//	};

	public static AbstractPerk ELEMENTAL_CORE_OCCUPATION = new AbstractPerk(20,
			true,
			"стихийный",
			PerkCategory.JOB,
			"perks/elemental/coreOccupation",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 50),
					new Value<>(Attribute.DAMAGE_SPELLS, 25),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 100),
					new Value<>(Attribute.MAJOR_CORRUPTION, 100)
					), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Как существа из чистой аркановой энергии, элементали способны без усилий использовать аркану для заклинаний."
					+ "Из-за похотливой природы арканы, они также невероятно развращены и всегда готовы к сексу...");
		}
	};

//	public static AbstractPerk ELEMENTAL_CORRUPTION = new AbstractPerk(20,
//			false,
//			"elemental",
//			PerkCategory.LUST,
//			"perks/elemental/coreCorruption",
//			PresetColour.GENERIC_ARCANE,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.MAJOR_CORRUPTION, 100)
//					), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			return UtilText.parse(owner, "Even if their summoner is completely pure and innocent, the lustful nature of the arcane causes all elementals to be incredibly perverted."
//					+ " If nothing else, they can always be relied upon to be willing and ready to have sex with anyone or anything...");
//		}
//	};
	
	// ELEMENTAL FIRE

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получите заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " привязанность к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Привязанность к школе "+getSchool().getName()+", [npc.name] сможет использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшите заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшен, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] сможет использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшен, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] сможет использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_FIRE,
			null,
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшен, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_MINOR = new AbstractPerk(20,
			false,
			"возгорание",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire1",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 1),
					new Value<>(Attribute.RESISTANCE_FIRE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Если инструктировать [npc.her] способность к управлению и принятию формы аркана огня, [npc.name] может сделать первый шаг к разблокировке следующей тайны школы аркана Огня.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST = new AbstractPerk(20,
			false,
			"пылающий",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire2",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 3),
					new Value<>(Attribute.RESISTANCE_FIRE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает следовать по пути элемента огня, и,"
					+ " хотя [npc.she] еще предстоит пройти путь до достижения нового этапа, [npc.her] склонность к аркану огня неуклонно растет.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"пожарище",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire3",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 6),
					new Value<>(Attribute.RESISTANCE_FIRE, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] склонность к аркану огня значительно увеличилась, и [npc.sheIs] на пороге открытия чего-то невероятно мощного!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"испепеление",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire4",
			PresetColour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_FIRE, 20),
					new Value<>(Attribute.RESISTANCE_FIRE, 20),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Горящий красивой и ужасающей силой самого солнца, один удар от [npc.name] обычно достаточно, чтобы вывести из строя любого, кто был настолько неfortunен, чтобы вызвать [npc.her] гнев.");
		}
	};
	
	// ELEMENTAL EARTH

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " когда привязан к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда заклинание привязано к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+" будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, получив дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_EARTH,
			null,
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_BOOST_MINOR = new AbstractPerk(20,
			false,
			"удар",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth1",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Если инструктировать [npc.her] способность создавать мощные физические проявления, [npc.name] может сделать первый шаг к разгадке следующей тайны школы Аркана Земли.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST = new AbstractPerk(20,
			false,
			"нарастающее давление",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth2",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 3),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает следовать по пути земного элементаля, и,"
					+ " хотя [npc.she] еще предстоит пройти долгий путь, прежде чем достичь нового рубежа, [npc.her] аффинитет с Арканой Силой неуклонно растет.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"сейсмическая активность",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth3",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 6),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] аффинитет с Арканой Силой значительно увеличился, и [npc.sheIs] на пороге открытия чего-то невероятно мощного!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"эпицентр",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth4",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 20),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 20),
					new Value<>(Attribute.DAMAGE_UNARMED, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Принимая форму, почти неуязвимую для атак, [npc.name] теперь может отмахнуться от почти любого удара, что позволяет [npc.herHim] подойти ближе к врагам [npc.her] и нанести им нокаутирующий удар или удар ногой.");
		}
	};
	
	// ELEMENTAL WATER

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_SPELL_2 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшен, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] сможет использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшен, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшен, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.PHYSICAL_WATER,
			null,
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшен, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_BOOST_MINOR = new AbstractPerk(20,
			false,
			"озноб",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water1",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 1),
					new Value<>(Attribute.RESISTANCE_ICE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Если инструктировать [npc.her] способность к управлению и принятию формы аркана льда, [npc.name] может сделать первый шаг к разблокировке следующей тайны школы аркана Вода.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST = new AbstractPerk(20,
			false,
			"мороз",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water2",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 3),
					new Value<>(Attribute.RESISTANCE_ICE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает идти по пути водного элемента, и,"
					+ "[npc.she] еще далеко до нового этапа, но [npc.her] связь с аркана льда неуклонно растет.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"заморозка",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water3",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 6),
					new Value<>(Attribute.RESISTANCE_ICE, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] связь с аркана льда значительно усилилась, и [npc.sheIs] на пороге открытия чего-то невероятно мощного!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"ледниковый период",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water4",
			PresetColour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_ICE, 20),
					new Value<>(Attribute.RESISTANCE_ICE, 20),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Летая с места на место с эфирной грацией, [npc.name] может мгновенно менять и восстанавливать [npc.her] тело, что позволяет [npc.herHim] совершать самые невероятные физические подвиги.");
		}
	};
	
	// ELEMENTAL AIR

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, получив дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинанием, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.ARCANE_AIR,
			null,
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_BOOST_MINOR = new AbstractPerk(20,
			false,
			"бриз",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air1",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 1),
					new Value<>(Attribute.RESISTANCE_POISON, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Если направить внимание на способность [npc.her] к обузданию силы арканального яда, [npc.name] сможет сделать первый шаг к раскрытию следующей тайны школы арканального Воздуха.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST = new AbstractPerk(20,
			false,
			"шквальный ветер",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air2",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 3),
					new Value<>(Attribute.RESISTANCE_POISON, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] продолжая идти по пути воздушного элементаля, и,"
					+ " в то время как [npc.she] еще предстоит пройти долгий путь, прежде чем достичь нового рубежа, [npc.her] аффинитет с арканическим ядом неуклонно растет.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"шторм",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air3",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 6),
					new Value<>(Attribute.RESISTANCE_POISON, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] аффинитет с арканическим ядом значительно увеличился, и [npc.sheIs] на грани открытия чего-то невероятно мощного!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"суперъячейка",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air4",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 20),
					new Value<>(Attribute.RESISTANCE_POISON, 20),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Любой враг, достаточно глупый, чтобы вызвать гнев [npc.namePos], вскоре сам столкнется с бурей,"
					+ " с каждым из [npc.her] разрушительных снарядов, попадающих точно в цель благодаря направляющим порывам, которые [npc.she] вызывает.");
		}
	};
	
	// ELEMENTAL ARCANE

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " когда привязан к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда заклинание привязано к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+" будет улучшено, чтобы иметь дополнительную силу "+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3 = new AbstractPerk(20,
			false,
			"Заклинание",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Заклинание: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Получение заклинания '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " при привязанности к <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "При привязанности к школе "+getSchool().getName()+", [npc.name] может использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, получив дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшение заклинания '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшение",
			PerkCategory.LUST,
			null,
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Улучшение: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " с <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено, чтобы иметь дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_MINOR = new AbstractPerk(20,
			false,
			"возбуждение",
			PerkCategory.LUST,
			"perks/elemental/arcane1",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1),
					new Value<>(Attribute.RESISTANCE_LUST, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Если инструктировать [npc.her] способность к использованию чистой силы Аркана, [npc.name] может сделать первый шаг к разблокированию следующей тайны школы Аркана.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST = new AbstractPerk(20,
			false,
			"страсть",
			PerkCategory.LUST,
			"perks/elemental/arcane2",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 3),
					new Value<>(Attribute.RESISTANCE_LUST, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает следовать по пути аркана, и,"
					+ " хотя [npc.she] еще предстоит пройти долгий путь, прежде чем достичь нового рубежа, [npc.her] аффинити с арканой похотью неуклонно растет.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"увлеченность",
			PerkCategory.LUST,
			"perks/elemental/arcane3",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 6),
					new Value<>(Attribute.RESISTANCE_LUST, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] аффинити с арканой магии значительно увеличилось, и [npc.sheIs] на пороге открытия чего-то невероятно мощного!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"нимфолепсия",
			PerkCategory.LUST,
			"perks/elemental/arcane4",
			PresetColour.SPELL_SCHOOL_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 20),
					new Value<>(Attribute.RESISTANCE_LUST, 20),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Те, кто смотрит на [npc.namePos] "+(owner.isFeminine()?"прекрасное тело и потрясающую":"скульпторированное тело и красивую")
					+"лицо, оказываются под влиянием [npc.her] заклинания, и в течение нескольких мгновений, полностью поглощены дикой, животной похотью.");
		}
	};
	
	// Doll perks:
	
	public static AbstractPerk DOLL_PHYSICAL_CORE = new AbstractPerk(20,
			false,
			"аркано-инфузированный силикон",
			PerkCategory.PHYSICAL,
			"perks/doll/physical0",
			Util.newArrayListOfValues(PresetColour.BASE_WHITE, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_FIRE, 10),
					new Value<>(Attribute.RESISTANCE_ICE, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			Util.newArrayListOfValues(
					"Органы медленно поглощают жидкости"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos] тело полностью состоит из аркано-инфузированного, силиконоподобного полимера, состав которого является тщательно охраняемой тайной 'Lovienne's Luxuries'."
						+ "Помимо того, что он может медленно поглощать и преобразовывать хранящиеся жидкости в энергию, этот материал чрезвычайно прочный.");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_1 = new AbstractPerk(20,
			false,
			"гипермобильность",
			PerkCategory.PHYSICAL,
			"perks/doll/physical1",
			PresetColour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Unlocks)] авто fellatio и автоcunnilingus")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos] тело чрезвычайно гибкое, что позволяет [npc.herHim] скручивать [npc.her] тело в позы, которые заставят любого гимнаста позавидовать.");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_2 = new AbstractPerk(20,
			false,
			"без возможности размножения",
			PerkCategory.PHYSICAL,
			"perks/doll/physical2",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.VIRILITY, -1000),
					new Value<>(Attribute.FERTILITY, -1000)),
			Util.newArrayListOfValues(
					"Оплодотворение [style.colourTerrible(невозможно)]",
					"Беременность [style.colourTerrible(невозможна)]",
					"[style.colourTerrible(Нельзя)] высиживать яйца")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos] искусственное тело совершенно не способно выносить потомство [npc.her]"
					+ " [npc.Her] резиновые отверстия также являются непригодными местами для яиц.");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_3 = new AbstractPerk(20,
			false,
			"органы не включены",
			PerkCategory.PHYSICAL,
			"perks/doll/physical3",
			PresetColour.BASE_RED_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 50)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Невосприимчив)] к жидкостным зависимостям, психоактивным веществам и алкоголю",
					"Отверстия имеют [style.colourExcellent(неограниченную)] глубину")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Внутренняя структура тела [npc.namePos] состоит исключительно из глубоких проходов, которые связаны с отверстиями [npc.her]."
					+ "[npc.name] [npc.is] не имеют биологических систем, поэтому @@T2@@ невосприимчив к воздействию большинства веществ.");
		}
	};

	public static AbstractPerk DOLL_LUST_CORE = new AbstractPerk(20,
			false,
			"секс-игрушка",
			PerkCategory.LUST,
			"perks/doll/lust0",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 100),
					new Value<>(Attribute.RESISTANCE_LUST, -100)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] был создан и сделан для того, чтобы быть секс-куклой, и хотя [npc.she] может выполнять широкий спектр других задач, [npc.her] его истинная цель - это быть ебаной.");
		}
	};

	public static AbstractPerk DOLL_LUST_1 = new AbstractPerk(20,
			false,
			"жить, чтобы ебаться",
			PerkCategory.LUST,
			"perks/doll/lust1",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Может [style.colourTerrible(never)] <span style='color:"+SexPace.SUB_RESISTING.getColour().toWebHexString()+";'>сопротивляйся</span> в [style.colourSex(секс-сценах)]",
					"Не возражает против <span style='color:"+SexPace.SUB_RESISTING.getColour().toWebHexString()+";'>садистские</span> сексуальные действия",
					"Ориентация всегда [style.colourAndrogynous(ambiphilic)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name] хочет только одного - быть ебаной без перерывов, независимо от того, кто ебет, и как грубо это происходит.");
		}
	};

	public static AbstractPerk DOLL_LUST_2 = new AbstractPerk(20,
			false,
			"принадлежащая",
			PerkCategory.LUST,
			"perks/doll/lust2",
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Всегда [style.colourSex(obey)] хозяина",
					"[style.colourObedience(Покорность)] является [style.colourExcellent(всегда 100)]"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name] [npc.verb(знать)] что [npc.sheIs] не более чем объект, и будет делать абсолютно всё, что [npc.her] попросит от [npc.herHim].");
		}
	};

	public static AbstractPerk DOLL_LUST_3 = new AbstractPerk(20,
			false,
			"все дозволено",
			PerkCategory.LUST,
			"perks/doll/lust3",
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLUE_GREY, PresetColour.BASE_GREY_LIGHT),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.colourTerrible(Не может)] получить [style.colourFetish(фетиши)]",
					"[style.colourFetish(Фетиши)] всегда [style.colourHuman(нейтральные)]",
					"Безразлична к экспозиционизму, женственности одежды, девственности и грязи"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Как автомат, который не имеет свободной воли, [npc.name] [npc.do] не имеет своего мнения о различных фетишах, и [npc.is] довольна тем, что [npc.her] делает то, что [npc.herHim] просит от @@T5@@.");
		}
	};
	
	public static AbstractPerk DOLL_ARCANE_CORE = new AbstractPerk(20,
			false,
			"автоматон",
			PerkCategory.ARCANE,
			"perks/doll/arcane0",
			Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_PINK_LIGHT),
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -25)),
			Util.newArrayListOfValues(
					"Может оставаться неподвижной неограниченное время"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] автомат, и хотя [npc.sheIs] может двигаться как живой человек, [npc.she] никогда не будет такой же гибкой или отзывчивой, как настоящий человек."
					+ "Если прикажут, [npc.she] может оставаться совершенно неподвижной неограниченное время.");
		}
	};
	
	public static AbstractPerk DOLL_ARCANE_1 = new AbstractPerk(20,
			false,
			"нет ауры",
			PerkCategory.ARCANE,
			"perks/doll/arcane1",
			Util.newArrayListOfValues(PresetColour.BASE_BLACK, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, -1000)),
			Util.newArrayListOfValues(
					"Может [style.colourTerrible(никогда)] генерировать эссенции из [style.colourSex(оргазм)]",
					"Может [style.colourTerrible(never)] поглощать эссенции",
					"[style.colourExcellent(Иммунный)] к [style.colourTfGeneric(стандартным трансформациям)]"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Как [npc.sheIs] просто кукла, а не настоящий человек, [npc.name] [npc.verb(lack)] аркана ауры, и как таковой не может поглощать ни генерировать эссенции."
						+ "Нормальные методы трансформации также не имеют эффекта на [npc.herHim], с только специальными куклами-специфичными трансформациями, имеющими какой-либо эффект.");
		}
	};

	public static AbstractPerk DOLL_ARCANE_2 = new AbstractPerk(20,
			false,
			"ограничения объекта",
			PerkCategory.ARCANE,
			"perks/doll/arcane2",
			PresetColour.GENERIC_EXPERIENCE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Может [style.colourTerrible(never)] получать опыт",
					"[style.colourTerrible(Постоянно заблокирован)] до [style.colourExperience(уровень 1)]",
					"Может [style.colourTerrible(never)] получать черты личности",
					"[style.colourAffection(Привязанность)] к другим всегда нейтральна@@H0@@"+PresetColour.AFFECTION_NEUTRAL.toWebHexString()+";'>всегда нейтральна</span>")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Без свободной воли или совести, [npc.nameIsFull] не может учиться на опыте [npc.her], и [npc.is] поэтому никогда не будет расти или развиваться, как настоящий человек.");
		}
	};

	public static AbstractPerk DOLL_ARCANE_3 = new AbstractPerk(20,
			false,
			"на базе Арканы",
			PerkCategory.ARCANE,
			"perks/doll/arcane3",
			Util.newArrayListOfValues(PresetColour.BASE_PINK, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Не нужно есть, пить или спать",
					"Получаете [style.colourExcellent(1000)] "+Attribute.HEALTH_MAXIMUM.getColouredName("охват")+"при воздействии аркана"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Вместо того, чтобы получать калории из пищи или напитков для функционирования, [npc.name] пассивно [npc.verb(absorb)] фоновую арканическую энергию для питания [npc.herself]."
						+ "Благодаря этому, арканические бури предоставляют [npc.herHim] значительное количество жизненной силы.");
		}
	};
	
	public static List<AbstractPerk> hiddenPerks;
	public static List<AbstractPerk> allPerks;
	public static List<AbstractPerk> subspeciesKnowledgePerks;
	
	public static Map<AbstractPerk, String> perkToIdMap = new HashMap<>();
	public static Map<String, AbstractPerk> idToPerkMap = new HashMap<>();
	
	private static boolean subspeciesPerksGenerated = false;
	
	public static AbstractPerk getPerkFromId(String id) {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
//		System.out.print("ID: "+id);
		if(id.equalsIgnoreCase("MERAXIS")
				|| id.equalsIgnoreCase("ARCANE_TATTOOIST")
				|| id.equalsIgnoreCase("SLUT")
				|| id.equalsIgnoreCase("ARCANE_TRAINING")
				|| id.equalsIgnoreCase("ARCANE_ALLERGY")
				|| id.equalsIgnoreCase("HEALTH_FANATIC")
				|| id.equalsIgnoreCase("MARTIAL_BACKGROUND")) {
			id = "SPECIAL_"+id;
		} else if(id.equalsIgnoreCase("BRAWLER")) {
			id = "FEROCIOUS_WARRIOR";
		} else if(id.equalsIgnoreCase("ARCANE_BASE_NPC")) {
			id = "ARCANE_BASE";
		} else if(id.equalsIgnoreCase("PHYSIQUE_5")) {
			id = "PHYSIQUE_BOOST_MAJOR";
		} else if(id.equalsIgnoreCase("ARCANE_5")) {
			id = "ARCANE_BOOST_MAJOR";
		} else if(id.equalsIgnoreCase("SPELL_DAMAGE_5")) {
			id = "SPELL_DAMAGE_MAJOR";
		} else if(id.equalsIgnoreCase("ELEMENTALIST_5")) {
			id = "ELEMENTAL_BOOST";
		} else if(id.equalsIgnoreCase("CRITICAL_BOOST_ALT")) {
			id = "CRITICAL_BOOST_LUST";
		} else if(id.equalsIgnoreCase("CRITICAL_BOOST_ALT_2")) {
			id = "CRITICAL_BOOST_ARCANE";
		}
		
		
		id = Util.getClosestStringMatch(id, idToPerkMap.keySet());
//		System.out.println("  set to: "+id);
		return idToPerkMap.get(id);
	}
	
	public static String getIdFromPerk(AbstractPerk perk) {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return perkToIdMap.get(perk);
	}

	static {
		hiddenPerks = new ArrayList<>();
		allPerks = new ArrayList<>();
		subspeciesKnowledgePerks = new ArrayList<>();
		
		Field[] fields = Perk.class.getFields();
		
		for(Field f : fields){
			if (AbstractPerk.class.isAssignableFrom(f.getType())) {
				
				AbstractPerk perk;
				
				try {
					perk = ((AbstractPerk) f.get(null));

					// I feel like this is stupid :thinking:
					perkToIdMap.put(perk, f.getName());
					idToPerkMap.put(f.getName(), perk);
					
					allPerks.add(perk);
					if(perk.isHiddenPerk()) {
						hiddenPerks.add(perk);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		hiddenPerks.sort((p1, p2) -> p1.getRenderingPriority()-p2.getRenderingPriority());
	}
	
	private static void generateSubspeciesPerks() {
		List<AbstractAttribute> resistancesAdded = new ArrayList<>();
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			if(!resistancesAdded.contains(sub.getDamageMultiplier())) {
				resistancesAdded.add(sub.getDamageMultiplier());
				boolean mainSubspecies = sub.getDamageMultiplier()==AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace()).getDamageMultiplier();
				AbstractSubspecies subToUse = mainSubspecies
												?AbstractSubspecies.getMainSubspeciesOfRace(sub.getRace())
												:sub;
				
				AbstractPerk racePerk = new AbstractPerk(20,
						false,
						Util.capitaliseSentence(mainSubspecies?sub.getRace().getName(false):subToUse.getName(null))+" знания",
						PerkCategory.LUST,
						null,
						sub.getColour(null),
						Util.newHashMapOfValues(
								new Value<>(subToUse.getDamageMultiplier(), 10)),
						null) {
					@Override
					public String getDescription(GameCharacter owner) {
						return UtilText.parse(owner, "[npc.NameHasFull] продвинутые знания о "+(mainSubspecies?sub.getRace().getNamePlural(false):subToUse.getNamePlural(null))+", и поэтому может наносить больший урон при борьбе с ними.");
					}
					@Override
					public String getSVGString(GameCharacter owner) {
						return subToUse.getSVGString(null);
					}
					@Override
					public Colour getColour() {
						return subToUse.getColour(null);
					}
					@Override
					public boolean isHiddenPerk() {
						return true;
					}
				};
//				System.out.println("Added perk: "+Subspecies.getIdFromSubspecies(subToUse)+" "+racePerk.getName(null)+" "+racePerk.hashCode());
				perkToIdMap.put(racePerk, Subspecies.getIdFromSubspecies(subToUse));
				idToPerkMap.put(Subspecies.getIdFromSubspecies(subToUse), racePerk);
				allPerks.add(racePerk);
				hiddenPerks.add(racePerk);
				subspeciesKnowledgePerks.add(racePerk);
			}
		}
		subspeciesPerksGenerated = true;
		hiddenPerks.sort((p1, p2) -> p1.getRenderingPriority()-p2.getRenderingPriority());
	}
	
	public static AbstractPerk getSubspeciesRelatedPerk(AbstractSubspecies subspecies) {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		
		AbstractSubspecies subToUse = 
				subspecies.getDamageMultiplier()==AbstractSubspecies.getMainSubspeciesOfRace(subspecies.getRace()).getDamageMultiplier()
					?AbstractSubspecies.getMainSubspeciesOfRace(subspecies.getRace())
					:subspecies;
		
		return Perk.getPerkFromId(Subspecies.getIdFromSubspecies(subToUse));
	}
	
	public static List<AbstractPerk> getAllPerks() {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return allPerks;
	}
	
	public static List<AbstractPerk> getHiddenPerks() {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return hiddenPerks;
	}

	public static List<AbstractPerk> getSubspeciesKnowledgePerks() {
		if(!subspeciesPerksGenerated) {
			generateSubspeciesPerks();
		}
		return subspeciesKnowledgePerks;
	}
}
