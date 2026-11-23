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
			return UtilText.parse(owner, "[npc.NameHasFull] возвысилась до положения матриарха стаи гарпий благодаря сочетанию [npc.her] природной красоты и готовности на всё ради власти.");
		}
	};

	public static AbstractPerk JOB_NPC_HARPY_FLOCK_MEMBER = new AbstractPerk(20,
			true,
			"иерархия",
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
			return UtilText.parse(owner, "[npc.NameIsFull] — член стаи гарпий, и как таковая обладает немалым опытом как в лести, так и в предательстве других.");
		}
	};

	
	// Enforcers:
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_INSPECTOR = new AbstractPerk(20,
			true,
			"Enforcer: Патрульный инспектор",
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
			return UtilText.parse(owner, "[npc.Name] носит звание «инспектора» в подразделении передового патруля Enforcer и прошла умеренную боевую подготовку.");
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
			"Enforcer: Патрульный сержант",
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
			return UtilText.parse(owner, "[npc.Name] носит звание «сержанта» в подразделении передового патруля Enforcer и прошла ограниченную боевую подготовку.");
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
			"Enforcer: Патрульный констебль",
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
			return UtilText.parse(owner, "[npc.Name] имеет звание «Констебля» в подразделении передового патруля Enforcer и прошел лишь базовую боевую подготовку.");
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
			"Enforcer: Суперинтендант SWORD",
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
			return UtilText.parse(owner, "[npc.Name] имеет звание «Суперинтенданта» в Департаменте специального вооружения и оперативного реагирования Enforcer и прошел обширную боевую подготовку.");
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
			return UtilText.parse(owner, "[npc.Name] имеет звание «Главного инспектора» в Департаменте специального вооружения и оперативного реагирования Enforcer и прошел обширную боевую подготовку.");
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
			return UtilText.parse(owner, "[npc.Name] имеет звание «Инспектора» в Департаменте специального вооружения и оперативного реагирования Enforcer и прошел обширную боевую подготовку.");
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
			return UtilText.parse(owner, "[npc.Name] имеет звание «Сержанта» в Департаменте специального вооружения и оперативного реагирования Enforcer и прошел значительную боевую подготовку.");
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
			return UtilText.parse(owner, "[npc.Name] носит звание «Констебля» в «Департаменте специального вооружения и оперативного реагирования» Enforcer и прошел серьезную боевую подготовку.");
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
			return UtilText.parse(owner, "[npc.Name] носит звание «Инспектора» в «Управлении разведки, контрразведки и логистики» ORICL службы Enforcer и знает множество способов заставить кого-то заговорить...");
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
			return UtilText.parse(owner, "[npc.Name] носит звание «Сержанта» в «Управлении разведки, контрразведки и логистики» ORICL службы Enforcer и знает множество способов заставить кого-то заговорить...");
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
			return UtilText.parse(owner, "[npc.Name] носит звание «Констебля» в «Управлении разведки, контрразведки и логистики» ORICL службы Enforcer и знает множество способов заставить кого-то заговорить...");
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
			"Почитательница Lilith",
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
					"[npc.NameIsFull] — полноправная участница «Культа Lilith», и чтобы должным образом поклоняться [npc.her] богине, [npc.sheHasFull] потратила невероятное количество времени на развитие как [npc.her] способностей к чтению заклинаний, так и своей соблазнительной силы.");
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
					"[npc.NameIsFull] — управляющая зданием «Рабской администрации» в Переулке Работорговцев, и хотя [npc.she] ведет себя довольно дружелюбно, наверняка для достижения такой должности ей пришлось отдать гораздо больше, чем просто капельку доброты...");
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
					"[npc.NameIsFull] — вышибала, и [npc.has] накопила значительный боевой опыт, работая над тем, чтобы не пускать всякий сброд в ночные клубы и бары.");
		}
	};

	public static AbstractPerk JOB_NPC_BARMAID = new AbstractPerk(20,
			true,
			"горничная в баре",
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
					"Благодаря [npc.her] опыту работы "+(owner.isFeminine()?"горничной в баре":"барменом")+", [npc.name] знает все приемы флирта, а также то, как противостоять напорам похотливых пьяниц.");
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
					"[npc.Name] знает всё о том, как заставить людей выглядеть наилучшим образом.");
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
					"[npc.NameIsFull] — очень богатая владелица одного из самых популярных ночных клубов Dominion, и у неё нет проблем с поиском компании на каждый вечер.");
		}
	};

	public static AbstractPerk JOB_NPC_ARCANE_RESEARCHER = new AbstractPerk(20,
			true,
			"секреты арканы",
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
					"[npc.NameHasFull] посвятил(а) большую часть [npc.her] жизни изучению арканы и раскрытию её секретов.");
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
					"Необходимость обеспечивать успех бизнеса [npc.her] заставляет [npc.name] работать на износ, что в процессе закаляет [npc.her].");
		}
	};
        
	public static AbstractPerk JOB_NPC_REBEL_FIGHTER = new AbstractPerk(20,
			true,
			"боец повстанцев",
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
			return UtilText.parse(owner, "[npc.NamePos] тренировки дали [npc.herHim] некоторую защиту от сил Lilith.");
		}
	};
	
	public static AbstractPerk JOB_NPC_FARMER = new AbstractPerk(20,
			true,
			"Кормилец мира",
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
					"#IF(!game.isSillyMode())Изнурительный труд, необходимый для того, чтобы поля цвели, сделал [npc.name] сильным и выносливым. [npc.She] также не боится ни пронизывающего холода, ни палящего солнца.#ELSE[npc.NameHasFull] новенький комбайн, и [npc.she] даст тебе ключ, но только после того, как прогонит [npc.her] трактор через твое стог сена, би-бип!#ENDIF");
		}
	};
	
	public static AbstractPerk JOB_NPC_JOURNALIST = new AbstractPerk(20,
			true,
			"Идти по тонкой грани",
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
			return UtilText.parse(owner, "В обществе, где разрешена лишь государственная версия новостей, журналистам приходится идти по очень тонкой грани...");
		}
	};
	
	public static AbstractPerk JOB_NPC_OFFICE_WORKER = new AbstractPerk(20,
			true,
			"Просто выгодный бизнес",
			PerkCategory.JOB,
			"perks/jobs/npc_office_worker",
			PresetColour.CURRENCY_GOLD,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Постоянное давление, связанное с выполнением планов и управлением трудными коллегами и клиентами, закалило выносливость [npc.namePos].");
		}
	};

	public static AbstractPerk JOB_NPC_REINDEER_OVERSEER = new AbstractPerk(20,
			true,
			"Морозоустойчивый",
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
					"[npc.NameIsFull] — надсмотрщик над стадом северных оленей, который наработал не только крепкое тело, но и впечатляющую устойчивость к холоду.");
		}
	};

	public static AbstractPerk JOB_ELDER_LILIN = new AbstractPerk(20,
			true,
			"Неприкасаемый",
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
					"[npc.NameIsFull] — одна из семи старших лилин, и [npc.verb(hold)] силами, о которых простые смертные могут только мечтать.");
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
					"[npc.NameIsFull] королева всех слаймов, способная [npc.her] использовать свои способности к смене облика, чтобы [npc.herself] выглядела поистине прекрасно.");
		}
	};

	public static AbstractPerk JOB_NPC_SLIME_QUEEN_GUARD = new AbstractPerk(20,
			true,
			"служанка-слайм",
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
					"[npc.NameIsFull] — один из личных стражей королевы слаймов, и он прекрасно обучен владению мечом.");
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
					"[npc.NameHasFull] посвятил(а) [npc.her] всю свою жизнь тому, чтобы помогать другим становиться матерями и отцами, и сейчас воплощает эту благородную цель, проводя игру «беременная рулетка» в игорном притоне Submission.");
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
					"[npc.NameHasFull] обладает всей силой и хитростью, ожидаемыми от лидера преступной банды, и, кроме того, [npc.has] пережила бесчисленное множество попыток оспорить [npc.her] власть.");
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
					"Будучи признанной одним из самых могущественных членов банды [npc.her], [npc.name] заняла пост телохранителя [npc.her] лидера.");
		}
	};
	
	public static AbstractPerk JOB_GANG_MEMBER = new AbstractPerk(20,
			true,
			"одна из нас",
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
					"[npc.NameIsFull] член преступной банды, и поэтому [npc.her] повидала немало драк.");
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
					"Благодаря упорному труду по дрессировке и уходу за огромным количеством рабов-кентавров, [npc.nameIsFull] очень подтянута и здорова, и точно знает, как совладать с любыми строптивыми личностями.");
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
					"[npc.NameIsFull] непризнанная дочь старейшей лилин, Lyssieth, которой поручено защищать материнский дворец от незваных гостей.");
		}
	};
	
	public static AbstractPerk JOB_TAUR_TRANSPORT = new AbstractPerk(20,
			true,
			"Тяни дальше!",
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
					"Проведя много времени, используя [npc.her] таврическое тело для запряжки повозок и перевозки грузов, [npc.nameHasFull] развила приличный уровень физической формы.");
		}
	};
	
	public static AbstractPerk JOB_NPC_MAYOR = new AbstractPerk(20,
			true,
			"Груз ответственности",
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
					"Приходилось принимать бесчисленное множество трудных решений, влияющих на жизни тысяч людей, поэтому [npc.nameHasFull] закалила [npc.her] разум и [npc.is] способна действовать с полной убежденностью.");
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
					"Работая личным помощником [npc.her] своего господина, [npc.nameHasFull] всегда поддерживать себя в отличной физической форме, чтобы всегда быть готовой протянуть руку помощи.");
		}
	};
	
	public static AbstractPerk JOB_LUNETTE_HERD = new AbstractPerk(20,
			true,
			"Беспощадный рейдер",
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
					"Проведя время за тренировками бок о бок с самой Лунетт, этот демон-кентавр стремится быть столь же разрушительным и беспощадным, как [npc.her] её мать!");
		}
	};

	public static AbstractPerk JOB_NPC_MUSHROOM_FORAGER = new AbstractPerk(20,
			true,
			"Мастер-миколог",
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
					"Благодаря изнурительному, а зачастую и тошнотворному процессу выяснения того, какие грибы съедобны, галлюциногенны или токсичны, тело [npc.NamePos] [npc.has] обрело высокую устойчивость к ядам.");
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
					"[npc.NameIsFull] признанная дочь самой Лунетт, и потому она значительно сильнее обычного демона.");
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
					"[npc.NameIsFull] верная и ценная пешка старшей лилин, благодаря чему ей была дарована часть [npc.her] силы её госпожи.");
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
					"[style.boldGood(Повинуется)] любой команде",
					"[style.boldExcellent(Приоритет)] командам владельца",
					"[style.boldBad(Игнорирует)] рабов и других кукол"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] — чрезвычайно реалистичная секс-кукла, созданная в 'Lovienne's Luxuries'. [npc.She] будет исполнять любую команду, данную [npc.herHim], если только она исходит не от раба или другой куклы, при этом команды [npc.her] владельца имеют приоритет.");
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
					"[npc.NameIsFull] — королева амазонок, и поэтому её жизнь была полна суровых физических нагрузок и боевых тренировок.");
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
					"[npc.NameIsFull] — амазонка, и поэтому она регулярно занимается физической активностью и боевой подготовкой.");
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
					"[npc.NameIsFull] — ёко из высокогорья Синрин. [npc.SheHasFull] провела большую часть [npc.her] жизни, практикуясь в аркане, и благодаря этому [npc.her] заклинания особенно мощны.");
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
					"[npc.NameIsFull] — профессиональный боксер, и потому является грозным противником в бою.");
		}
	};
	
	
	public static AbstractPerk JOB_SLAVE = new AbstractPerk(20,
			true,
			"Жизнь в служении",
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
			return UtilText.parse(owner, "[npc.NameHasFull] закалила [npc.her] тело и разум, чтобы помочь [npc.herHim] смириться с тем, что [npc.sheIs] — всего лишь чья-то собственность.");
		}
	};

	public static AbstractPerk JOB_CAPTIVE = new AbstractPerk(20,
			true,
			"Похищена",
			PerkCategory.JOB,
			"perks/jobs/npc_captive",
			PresetColour.BASE_RED,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5),
					new Value<>(Attribute.HEALTH_MAXIMUM, -25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] была похищена и удерживается в незаконном плену против [npc.her] воли.");
		}
	};
	
	public static AbstractPerk JOB_PROSTITUTE = new AbstractPerk(20,
			true,
			"Древнейшая профессия",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_CORRUPTION, 50),
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Удваивает)] весь доход от рабской и самостоятельной проституции")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] привыкла продавать [npc.her] тело незнакомцам, чтобы заработать на жизнь. После стольких половых актов, чтобы по-настоящему [npc.herHim] возбудить, нужно постараться.");
		}
	};
	
	public static AbstractPerk JOB_MUGGER = new AbstractPerk(20,
			true,
			"Разбойник",
			PerkCategory.JOB,
			"perks/jobs/mugger",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(Утроение)] дохода от грабежей")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(live)] преступный образ жизни, кража у богатых и бедных без разбора.");
		}
	};
	
	public static AbstractPerk JOB_BOUNTY_HUNTER = new AbstractPerk(20,
			true,
			"Без побега",
			PerkCategory.JOB,
			"perks/jobs/bounty_hunter",
			PresetColour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 25),
					new Value<>(Attribute.HEALTH_MAXIMUM, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(-50%)] шанс побега врага")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] эксперт по выслеживанию и поимке беглецов, благодаря чему врагам трудно сбежать от [npc.herHim] в бою.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] [npc.verb(spent)] некоторое время проработал в строительной индустрии, помогая возводить и ремонтировать инфраструктуру и недвижимость.");
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
			return UtilText.parse(owner, "Способность обуздать Аркану и, следовательно, телекинетически манипулировать материей, что [npc.nameIsFull] является очень ценным активом для строительных фирм.");
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
			Util.newArrayListOfValues("[style.boldExcellent(Увеличивает)] бонус «Выспавшийся»")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Имея столько свободного времени в [npc.herPos] руках, [npc.nameHas] удалось улучшить [npc.herself] несколькими разными способами. [npc.Name] также [npc.verb(benefit)] благодаря знанию того, как лучше всего расслабиться, что увеличивает бонус, который [npc.she] [npc.verb(get)] от сна.");
		}
	};
	
	public static AbstractPerk JOB_OFFICE_WORKER = new AbstractPerk(20,
			true,
			"Офисный клерк",
			PerkCategory.JOB,
			"perks/jobs/officeWorker",
			PresetColour.BASE_BROWN,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(+25%)] весь доход от рабов")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "Карьеристка";
			} else {
				return "Офисный клерк";
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря [npc.herPos] значительному офисному опыту, [npc.name] [npc.verb(know)] точно знает, как мотивировать тех, кто работает под [npc.herPro]. Стрессовая рабочая обстановка заставила [npc.herPro] накопить много разочарования, что проявляется в повышенной критической силе.");
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
			return UtilText.parse(owner, "[npc.NamePos] студенческая скидка еще никогда не подводила [npc.herPro]! Наряду с гарантированной 25% dскидкой во всех магазинах, [npc.she] может быть уверена в [npc.herPos] способности быстро усваивать новое.");
		}
	};
	
	public static AbstractPerk JOB_MUSICIAN = new AbstractPerk(20,
			true,
			"Аркана-состав",
			PerkCategory.JOB,
			"perks/jobs/musician",
			PresetColour.BASE_GREY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(Double)] длительность всех эффектов заклинаний")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(find)] то, что [npc.herPos] способности музыканта отлично переводятся в искусство соблазнения. [npc.She] [npc.verb(feel)] тот же самый ритм при чтении заклинаний, что и [npc.she] [npc.do] с музыкой, в результате чего все [npc.herPos] эффекты заклинаний длятся в два раза дольше обычного.");
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
			Util.newArrayListOfValues("[style.boldExcellent(Triple)] все бонусы к покорности рабыни")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(know)] именно то, как справляться с непослушными учениками. [npc.HerPos] способность ясно понимать и объяснять сложные предметы отражается в снижении стоимости произнесения заклинаний.");
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
			Util.newArrayListOfValues("[style.boldExcellent(+25%)] ко всем бонусам опыта")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(keep)] дневник [npc.herPos] личных мыслей и встреч, позволяющий [npc.herPro] размышлять и учиться на [npc.herPos] опыте. [npc.HerPos] живой интерес к книгам также позволяет [npc.herPro] быстро изучать наиболее эффективное применение заклинаний.");
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
					"[style.boldExcellent(Double)] длительность всех эффектов зелий",
					"[style.boldExcellent(Doubles)] лимит эффектов зелий")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря тому, что [npc.name] [npc.has] провел немало времени, пробуя еду на вкус, он обрел как значительную устойчивость к ядам, так и способность творить кулинарные шедевры из самых простых ингредиентов.");
		}
	};

	public static AbstractPerk JOB_PLAYER_CONSTRUCTION_WORKER = new AbstractPerk(20,
			true,
			"Менеджер проектов",
			PerkCategory.JOB,
			"perks/jobs/construction",
			Util.newArrayListOfValues(PresetColour.CLOTHING_YELLOW, PresetColour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<>(Attribute.HEALTH_MAXIMUM, 10),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues("[style.boldExcellent(Halves)] стоимость всех улучшений комнаты"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря [npc.her] огромному опыту в индустрии, [npc.name] [npc.verb(know)] знает, как эффективно управлять строительными проектами. [npc.Her] время, проведенное за физическим трудом, также подарило [npc.herHim] крепкое тело.");
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
			Util.newArrayListOfValues("Ваш урон [style.boldExcellent(удваивается)] в первый ход боя")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHas] провел немало времени, тренируясь в бою, и в результате, [npc.she] [npc.is] стал гораздо сильнее и здоровее обычного человека. Благодаря этим тренировкам, [npc.she] [npc.is] также способен направлять [npc.herPos] агрессию в [npc.herPos] атаки.");
		}
	};

	public static AbstractPerk JOB_ATHLETE = new AbstractPerk(20,
			true,
			"Десятисекундный барьер",
			PerkCategory.JOB,
			"perks/jobs/athlete",
			PresetColour.BASE_TEAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10)),
			Util.newArrayListOfValues("Все ненулевые шансы на побег в бою увеличиваются до [style.boldExcellent(100%)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] спринтер мирового класса, и [npc.has] гарантированный 100% sуспех побега из любой боевой ситуации, где есть возможность убежать.");
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
					"Все прибавки к [style.colourCorruption(порочности)] [style.colourBad(удваиваются)]",
					"[style.colourGood(+0.25)] к [style.colourExcellent(всем сопротивлениям)] за каждую единицу порочности")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] из древнего аристократического рода, и [npc.has] получила лучшее образование, которое только можно купить за деньги. Невыносимо высокомерная, [npc.name] [npc.verb(know)] что [npc.sheIsFull] лучше всех остальных, и не позволит никакому грязному крестьянину взять над [npc.herHim] верх!");
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
					"[style.boldExcellent(Усиленные)] бонусы комплектов горничных",
					"[style.boldExcellent(Двойной)] доход от рабынь-горничных и лакеев")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] идеальный пример трудолюбивой горничной, а при ношении полного комплекта одежды горничной бонус, который [npc.she] [npc.verb(receive)], значительно увеличивается. [npc.She] также [npc.verb(know)], как обучать лакеев и других горничных быть исключительными в своем деле.");
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
					"[style.boldExcellent(Усиленные)] Бонусы комплекта дворецкого",
					"[style.boldExcellent(Двойной)] доход от рабынь-горничных и лакеев")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] — идеальный пример трудолюбивого дворецкого, и при ношении полного комплекта одежды дворецкого бонус, который [npc.she] [npc.verb(receive)], значительно усиливается. [npc.She] также [npc.verb(know)], как обучать горничных и других дворецких быть исключительными в своем деле.");
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
					"Все неправильные английские написания [style.boldExcellent(исправляются автоматически)]",
					"Враги ослеплены твоей [style.colourFreedom(свободой)] ради [style.colourExcellent(-1)] <span style='color:"+Attribute.ACTION_POINTS.getColour().toWebHexString()+";'>"+Attribute.ACTION_POINTS.getName()+"</span>",
					"Эффекты бургеров [style.colourGood(удваиваются)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.speech(Простите! Я американец! Вы говорите по-английски?! Моя виза истекает через четыре дня, где посольство?!)]");
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
			return UtilText.parse(owner, "[npc.Name] [npc.has] естественный уровень физической формы.");
		}
	};

	public static AbstractPerk CRITICAL_BOOST = new AbstractPerk(20,
			false,
			"критическая мощь",
			PerkCategory.PHYSICAL,
			"perks/critical_power",
			PresetColour.BASE_ORANGE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] способен по-настоящему выжать максимум из любых критических приемов [npc.she] [npc.verb(make)].");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_LUST = new AbstractPerk(20,
			false,
			"критическая мощь",
			PerkCategory.LUST,
			"perks/critical_power",
			PresetColour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] способна по-настоящему выжать максимум из любых критических приемов [npc.she] [npc.verb(make)].");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_ARCANE = new AbstractPerk(20,
			false,
			"критическая мощь",
			PerkCategory.ARCANE,
			"perks/critical_power",
			PresetColour.BASE_PURPLE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.CRITICAL_DAMAGE, 5),
					new Value<>(Attribute.DAMAGE_SPELLS, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] способны по-настоящему выжать максимум из любых критических приемов [npc.she] [npc.verb(make)].");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST = new AbstractPerk(20,
			false,
			"в отличной физической форме",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил много времени на улучшение [npc.her] физической формы.");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST_ALT = new AbstractPerk(20,
			false,
			"в отличной физической форме",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратила много времени на улучшение [npc.her] физической формы.");
		}
	};

	public static AbstractPerk PHYSIQUE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"в отличной физической форме",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Благодаря упорному труду и настойчивости, [npc.nameHasFull] удалось значительно повысить [npc.her] уровень физической подготовки.");
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
			return UtilText.parse(owner, "Тренируясь без помощи Арканы, [npc.nameHasFull] удалось увеличить урон от [npc.her] физических атак.");
		}
	};

	public static AbstractPerk UNARMED_DAMAGE = new AbstractPerk(20,
			false,
			"рукопашный",
			PerkCategory.PHYSICAL,
			"perks/unarmed_damage",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_UNARMED, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] провел бесчисленное количество часов, тренируясь в рукопашном бою, что сделало [npc.herHim] опасным противником даже без оружия.");
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
			return UtilText.parse(owner, "Проведя много времени за тренировками с самыми разными видами холодного оружия, [npc.nameIsFull] теперь стал крайне опасным противником в ближнем бою.");
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
			return UtilText.parse(owner, "Владея всеми видами дистанционного оружия, [npc.nameIsFull] стал поистине ужасающим противником в сражениях на расстоянии.");
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
			return UtilText.parse(owner, "Не имея времени на стратегии, не подразумевающие демонстрацию сокрушительной силы, [npc.name] [npc.verb(throw)] [npc.herself] в бой, совершенно не заботясь о [npc.her] собственной безопасности.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] потратил немало времени на совершенствование [npc.her] способности отражать физические атаки.");
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
					"[style.colourExcellent(Разблокирует)] аутофелляцию и автокуннилингус [style.colourSex(половые акты)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] исключительно гибкий, что позволяет [npc.herHim] наносить удары под совершенно неожиданными углами. Поскольку [npc.sheIs] может наклониться так низко, что [npc.her] голова касается [npc.her] паха, [npc.sheIs] способен заниматься оральным сексом с [npc.herself].");
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
			return UtilText.parse(owner, "Тратя время на практику сотворения заклинаний, [npc.nameHasFull] открыл новые способы повышения их эффективности.");
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
			return UtilText.parse(owner, "Посвятив много времени сосредоточению на усилении мощи [npc.her] заклинаний, [npc.nameIsFull] теперь может творить их гораздо эффективнее, чем раньше.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] удалось повысить эффективность применения заклинаний [npc.her]!");
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
			return UtilText.parse(owner, "Проведя немало времени в медитации, [npc.nameHasFull] удалось увеличить запасы ауры [npc.her].");
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
			return UtilText.parse(owner, "[npc.NameHasFull] увеличил(а) запасы энергии [npc.her], что позволяет [npc.herHim] выдерживать атаки, которые раньше вывели бы [npc.herHim] из строя.");
		}
	};

	public static AbstractPerk ENERGY_BOOST_DRAIN_DAMAGE = new AbstractPerk(20,
			true,
			"ауральное щитование",
			PerkCategory.PHYSICAL,
			"perks/resource_boost_drain_aura",
			PresetColour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 20),
					new Value<>(Attribute.MANA_MAXIMUM, -25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Концентрируя [npc.her] арканическую ауру в защитный барьер, [npc.nameIsFull] может выдерживать больше урона, чем [npc.she] смог(ла) бы в противном случае.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] посвятил(а) [npc.herself] изучению того, как более эффективно управлять всеми арканическими стихиями.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] посвятил(а) [npc.herself] изучению того, как более эффективно управлять всеми арканическими стихиями.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] посвятил(а) [npc.herself] изучению того, как более эффективно управлять всеми арканическими стихиями.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] потратил(а) время, изучая, как лучше всего защищать [npc.herself] от арканных стихий.");
		}
	};
	
	
	public static AbstractPerk ARCANE_BASE = new AbstractPerk(20,
			false,
			"природная арканная мощь",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "В этой реальности у каждого есть арканная Аура, какой бы слабой она ни была, а значит, как минимум, каждому доступна крошечная искра арканной мощи.";
		}
	};
	
	public static AbstractPerk ARCANE_BOOST = new AbstractPerk(20,
			false,
			"арканное обучение",
			PerkCategory.ARCANE,
			"perks/attIntelligence1",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] сосредоточился(ась) на улучшении способности [npc.her] управлять арканой.");
		}
	};

	public static AbstractPerk ARCANE_BOOST_ALT = new AbstractPerk(20,
			false,
			"арканное обучение",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] сосредоточился(ась) на улучшении способности [npc.her] управлять арканой.");
		}
	};

	public static AbstractPerk ARCANE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"арканная близость",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] раскрыл(а) секреты арканы, которые немногим удается постичь.");
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
			return UtilText.parse(owner, "[npc.Name] [npc.verb(know)] точно знает, какими непристойными делами занимаются жители Dominion, и может использовать эти знания, чтобы [npc.her] себе на пользу...");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST = new AbstractPerk(20,
			false,
			"соблазнительная",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил(а) немало времени на отработку [npc.her] техник соблазнения, что позволяет [npc.herHim] наносить дополнительный урон, дразня других.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT = new AbstractPerk(20,
			false,
			"соблазнительная",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил(а) немало времени на отработку [npc.her] техник соблазнения, что позволяет [npc.herHim] наносить дополнительный урон, дразня других.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"соблазнительная",
			PerkCategory.LUST,
			"perks/attSeduction1",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] потратил(а) немало времени на отработку [npc.her] техник соблазнения, что позволяет [npc.herHim] наносить дополнительный урон, дразня других.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"соблазнительная",
			PerkCategory.LUST,
			"perks/attSeduction5",
			PresetColour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] — ходячая сексуальная бомба. [npc.Her] каждое движение пропитано соблазнительным языком тела, и люди не могут удержаться от сильнейшего возбуждения, просто глядя на [npc.herHim].");
		}
	};
	
	public static AbstractPerk SEDUCTION_DEFENCE_BOOST = new AbstractPerk(20,
			false,
			"сопротивляемость",
			PerkCategory.LUST,
			"perks/shield",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] закалил [npc.herself], чтобы противостоять любым искушениям, благодаря чему [npc.she] станет гораздо проще не возбуждаться, когда его дразнят.");
		}
	};
	
	public static AbstractPerk VIRILITY_BOOST = new AbstractPerk(20,
			false,
			"вирильность",
			PerkCategory.LUST,
			"perks/virile",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Тело [npc.NamePos] способно вырабатывать очень мощную сперму, что повышает шансы на зачатие любого сексуального партнера [npc.hers].");
		}
	};
	
	public static AbstractPerk VIRILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"вирильность",
			PerkCategory.LUST,
			"perks/virile",
			PresetColour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Тело [npc.NamePos] способно вырабатывать невероятно мощную сперму, что значительно повышает шансы на зачатие любого сексуального партнера [npc.hers].");
		}
	};

	public static AbstractPerk FERTILITY_BOOST = new AbstractPerk(20,
			false,
			"фертильность",
			PerkCategory.LUST,
			"perks/fertile",
			PresetColour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Тело [npc.NamePos] очень фертильно, что повышает шансы на то, что любой сексуальный партнер [npc.hers] сможет забеременеть [npc.herHim].");
		}
	};

	public static AbstractPerk FERTILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"фертильность",
			PerkCategory.LUST,
			"perks/fertile",
			PresetColour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Тело [npc.NamePos] невероятно фертильно, что значительно повышает шансы на то, что любой сексуальный партнер [npc.hers] сможет забеременеть [npc.herHim].");
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
			return UtilText.parse(owner, "[npc.NameIsFull] эксперт, использующий аркану в бою. [npc.She] [npc.verb(gain)] бонус к урону и эффективности [npc.her] заклинаний.");
		}
	};
	
	public static AbstractPerk SACRIFICIAL_SHIELDING = new AbstractPerk(20,
			true,
			"жертвенный щит",
			PerkCategory.ARCANE,
			"perks/sacrificial_shielding",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -10),
					new Value<>(Attribute.ENERGY_SHIELDING, 2)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Жертвуя частью [npc.her] физической силы, [npc.nameIsFull] способен призывать всеобъемлющий защитный щит.");
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
					"[style.colourExcellent(Поглощает 50%)] оставшейся [style.colourMana(маны) любого бойца"+Attribute.MANA_MAXIMUM.getName()+")]",
					 "после его поражения")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] обучил [npc.her] арканную ауру вампирическому типу, заставляя её меньше полагаться на собственные резервы и больше — на способность высасывать ауру из любого (друга или врага), кто будет повержен поблизости.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] обладает огромным боевым опытом, что делает [npc.herHim] опасным противником для любого, кто окажется достаточно глуп, чтобы бросить [npc.herHim] вызов.");
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
				return "Вы очень проницательны и способны замечать малейшие изменения в окружающем мире. Вы всегда можете определить пол человека, даже если понятия не имеете, как выглядит его пах.";
			} else {
				return UtilText.parse(owner, "[npc.Name] очень проницателен, и [npc.she] постоянно сканирует [npc.her] окружение на предмет признаков опасности.");
			}
		}
	};
	

	public static AbstractPerk HEAVY_SLEEPER = new AbstractPerk(60,
			true,
			"крепко спящий",
			PerkCategory.PHYSICAL,
			"statusEffects/sleeping",
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
						"[npc.Name] [npc.verb(sleep)] очень крепко, так что даже крики и физическая встряска [npc.herHim] часто недостаточно, чтобы разбудить [npc.herHim]. [npc.SheIs] настолько неощутим во сне, что ласковый партнер может спокойно заниматься сексом с [npc.herHim], не вызывая [npc.herHim]...");
			}
			return "";
		}
	};
	
	// Arcane:
	
	public static AbstractPerk ARCANE_CRITICALS = new AbstractPerk(60,
			false,
			"арканная точность",
			PerkCategory.ARCANE,
			"perks/physical_accurate",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 5)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_COMBAT.toWebHexString()+ ";'>Критические</span> заклинания накладывают <b style='color:"+PresetColour.ATTRIBUTE_LUST.toWebHexString()+";'>Уязвимость к Arcane</b>")) {
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
			return UtilText.parse(owner, "[npc.NamePos] заклинания особенно эффективны при ударах в уязвимые места цели. Любые критические попадания от [npc.her] заклинаний накладывают эффект 'Уязвимость к Arcane' на один ход (-10 ко всем щитам).");
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
			"огнеметный",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "У вас обостренная связь с Arcane-огнем. Вы точно знаете, как им манипулировать, чтобы максимизировать наносимый урон.";
			else
				return UtilText.parse(owner, "[npc.Name] обладает обостренной связью с Arcane-огнем. [npc.She] точно знает, как им манипулировать, чтобы максимизировать наносимый урон.");
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
				return "Вы эксперт в манипуляциях с Arcane-огнем. Вы не только способны максимизировать его урон, но и обладаете повышенной устойчивостью к его воздействию.";
			else
				return UtilText.parse(owner, "[npc.Name] — эксперт в манипуляциях с Arcane-огнем. Не только [npc.she] способен максимизировать его урон, но и [npc.she] обладает повышенной устойчивостью к его воздействию.");
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
				return "У вас обостренная связь с арканическим льдом. Вы точно знаете, как им манипулировать, чтобы нанести максимальный урон.";
			else
				return UtilText.parse(owner, "[npc.Name] обладает обостренной связью с арканическим льдом. [npc.She] точно знает, как им манипулировать, чтобы нанести максимальный урон.");
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
				return "Вы эксперт в манипуляциях с арканическим льдом. Вы не только способны максимизировать его урон, но и обладаете повышенной сопротивляемостью к его воздействию.";
			else
				return UtilText.parse(owner, "[npc.Name] — эксперт в манипуляциях с арканическим льдом. [npc.she] не только способен максимизировать его урон, но и [npc.she] обладает повышенной сопротивляемостью к его воздействию.");
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
				return "У вас обостренная связь с арканическим ядом. Вы точно знаете, как им манипулировать, чтобы нанести максимальный урон.";
			else
				return UtilText.parse(owner, "[npc.Name] обладает обостренной связью с арканическим ядом. [npc.She] точно знает, как им манипулировать, чтобы нанести максимальный урон.");
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
				return "Вы эксперт в манипуляциях с арканическим ядом. Вы не только способны максимизировать его урон, но и обладаете повышенной сопротивляемостью к его воздействию.";
			else
				return UtilText.parse(owner, "[npc.Name] — эксперт в манипуляциях с арканическим ядом. [npc.she] не только способен максимизировать его урон, но и [npc.she] обладает повышенной устойчивостью к его эффектам.");
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
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Улучшенный шанс побега</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Вы прирожденный бегун и обладаете хорошим уровнем выносливости.";
			else
				return UtilText.parse(owner, "[npc.Name] — прирожденный бегун и обладает хорошим уровнем выносливости.");
		}
	};

	public static AbstractPerk RUNNER_2 = new AbstractPerk(20,
			true,
			"чемпион кардио",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner_2",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Улучшенный шанс побега</span>")) {
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
			return UtilText.parse(owner, "[npc.NameIsFull] этот " + (owner.isFeminine() ? "королева" : "король") + " кардио, обладающая, кажется, бесконечным запасом энергии.");
		}
	};

	public static AbstractPerk COMBAT_REGENERATION = new AbstractPerk(20,
			true,
			"боевая регенерация",
			PerkCategory.PHYSICAL,
			"perks/regeneration",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, -25)),
			Util.newArrayListOfValues("[style.boldExcellent(Регенерация 5%)] всего [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")] за ход в бою")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "За счет некоторой части [npc.namePos] "+Attribute.MANA_MAXIMUM.getName()+", природные целебные свойства арканы теперь будут усиливаться в присутствии [npc.her] адреналина.");
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
			return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] в хорошей драке и [npc.is] грозный противник в рукопашном бою.");
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
			return UtilText.parse(owner, "[npc.NameIsFull] очень кокетлив, и хотя [npc.her] чары действуют на оба пола, [npc.she] [npc.verb(find)] что [npc.her] ухаживания особенно эффективны против женственных натур.");
		}

	};

	public static AbstractPerk MALE_ATTRACTION = new AbstractPerk(60,
			false,
			"кокетка",
			PerkCategory.LUST,
			"perks/fitness_male_attraction",
			PresetColour.MASCULINE,
			null, Util.newArrayListOfValues("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>урон похотью</span> против <span style='color:" + PresetColour.MASCULINE.toWebHexString()+ ";'>мужественных противников</span>")) {
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
			return UtilText.parse(owner, "[npc.NameIsFull] та еще дразнилка, и хотя [npc.her] чары действуют на оба пола, [npc.she] [npc.verb(find)] что [npc.her] ухаживания особенно эффективны против мужественных натур.");
		}
	};
	
	public static AbstractPerk CONVINCING_REQUESTS = new AbstractPerk(20,
			true,
			"неодолимое обаяние",
			PerkCategory.LUST,
			"perks/convincing_requests",
			Util.newArrayListOfValues(PresetColour.GENERIC_SEX, PresetColour.BASE_GOLD),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues(
					"Просьбы, сделанные во время обычных секс-сцен, всегда удовлетворяются",
					"Разблокирует действия по смене позиций во всех обычных секс-сценах"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] в совершенстве овладел(а) искусством сексуального убеждения, и [npc.is] способен(на) убедить даже самых жестоких доминантов делать всё так, как [npc.she] [npc.verb(ask)].");
		}
	};
	
	public static AbstractPerk OBJECT_OF_DESIRE = new AbstractPerk(20,
			true,
			"объект вожделения",
			PerkCategory.LUST,
			"perks/object_of_desire",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues("Все партнеры во время секса требуют [style.colourSex(+1 оргазм)] для полного удовлетворения")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] настолько поразительно "+(owner.getFemininity()==Femininity.ANDROGYNOUS?"привлекателен(на)":(owner.isFeminine()?"прекрасен(на)":"красив"))
					+" что [npc.her] сексуальные партнеры не могут не продолжать трахаться даже после своего первого оргазма.");
		}
	};

	public static AbstractPerk ORGASMIC_LEVEL_DRAIN = new AbstractPerk(20,
			true,
			"истощение уровня оргазма",
			PerkCategory.LUST,
			"perks/orgasmic_level_drain",
			Util.newArrayListOfValues(PresetColour.GENERIC_SEX, PresetColour.GENERIC_EXPERIENCE),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"[style.boldTerrible(-95% t всего)] [style.boldExperience(прирост опыта)]",
					"Во всех [style.boldSex(секс-сценах)]: ",
					"Можно выбрать [style.boldTerrible(выкачать 1 уровень)] у партнеров во время оргазма",
					"Вы получаете [style.boldExcellent(5)] [style.boldExperience(опыта)], умноженного на количество выкачанных уровней"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Благодаря сложному манипулированию [npc.her] arcane аурой, [npc.name] [npc.has] начинают гораздо медленнее обучаться на новом опыте, но взамен [npc.is] теперь может выкачивать опыт сексуальных партнеров, испытывающих оргазм в [npc.her] присутствии.");
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
					"[style.colourExcellent(x2)] [style.colourArcane(прирост эссенции)] при оргазме",
					"Получение эффектов статуса [style.colourSex(воздержание)] и [style.colourSex(девственность)] [style.colourTerrible(в 2 раза быстрее)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] полностью и безнадежно зависим(а) от секса.");
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
			Util.newArrayListOfValues("При оргазме делает [style.colourSex(ахегао)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Оргазмы [npc.NamePos] особенно интенсивны, и [npc.she] не может удержаться от преувеличенного выражения лица каждый раз, когда [npc.she] [npc.verb(climax)].");
		}
	};

	public static AbstractPerk LUSTPYRE = new AbstractPerk(20,
			false,
			"пламя похоти",
			PerkCategory.LUST,
			"perks/lustful_leech",
			PresetColour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Поглощает 2%)] от максимума цели "+Attribute.MANA_MAXIMUM.getName()+" при каждой атаке, наносящей [style.boldLust("+Attribute.DAMAGE_LUST.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Сама аркана реагирует на похотливые порывы [npc.namePos], понемногу высасывая [npc.her] врага, "+Attribute.MANA_MAXIMUM.getName()+" каждый раз, когда [npc.she] дразнит их.");
		}
	};

	public static AbstractPerk PURE_MIND = new AbstractPerk(20,
			false,
			"чистые мысли",
			PerkCategory.LUST,
			"perks/pure_mind",
			PresetColour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Восстанавливает 2%)] от вашего максимума "+Attribute.MANA_MAXIMUM.getName()+" при каждой атаке, во время которой вы получаете [style.boldLust("+Attribute.DAMAGE_LUST.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Закаляя [npc.her] разум против любых проявлений похоти, [npc.nameIsFull] способен входить в состояние медитации, реагируя на любой урон от похоти, который [npc.she] [npc.verb(receive)] путем регенерации части [npc.her] "+Attribute.MANA_MAXIMUM.getName()+".");
		}
	};
	
	
	public static AbstractPerk CLOTHING_ENCHANTER = new AbstractPerk(20,
			false,
			"арканный ткач",
			PerkCategory.ARCANE,
			"perks/arcaneWeaver",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Уменьшает стоимость всех зачарований одежды вдвое</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] природная склонность к вплетению арканных чар в предметы одежды, позволяющая [npc.herHim] тратить лишь половину обычной арканной эссенции при зачаровании одежды.");
		}
	};
	
	public static AbstractPerk WEAPON_ENCHANTER = new AbstractPerk(20,
			false,
			"арканный кузнец",
			PerkCategory.PHYSICAL,
			"perks/arcaneSmith",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Уменьшает стоимость всех зачарований оружия вдвое</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] природная склонность к наделению оружия арканными чарами, позволяющая [npc.herHim] тратить лишь половину обычной арканной эссенции при зачаровании оружия.");
		}
	};
	
	public static AbstractPerk ENCHANTMENT_STABILITY = new AbstractPerk(20,
			false,
			"стабильные зачарования",
			PerkCategory.PHYSICAL,
			"perks/enchantment_stability",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] тратил(а) время на тренировку тела и разума [npc.her], чтобы справляться с более высоким "+Attribute.ENCHANTMENT_LIMIT.getName()+"[npc.herHim], что позволяет экипировать больше зачарованного оружия, одежды и татуировок.");
		}
	};

	public static AbstractPerk ENCHANTMENT_STABILITY_ALT = new AbstractPerk(20,
			false,
			"стабильные зачарования",
			PerkCategory.ARCANE,
			"perks/enchantment_stability",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] тратил(а) время на тренировку тела и разума [npc.her], чтобы справляться с более высоким "+Attribute.ENCHANTMENT_LIMIT.getName()+"[npc.herHim], что позволяет экипировать больше зачарованного оружия, одежды и татуировок.");
		}
	};
	
	public static AbstractPerk BARREN = new AbstractPerk(20,
			true,
			"бесплодное",
			PerkCategory.LUST,
			"perks/barren",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, -200)),
			Util.newArrayListOfValues("Пока "+Attribute.FERTILITY.getName()+" значение равно 0 или меньше, беременность [style.colourTerrible(невозможна)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] крайне бесплоден(на), и в результате, [npc.is] крайне маловероятно когда-либо забеременеть.");
		}
	};
	
	public static AbstractPerk FIRING_BLANKS = new AbstractPerk(20,
			true,
			"стерильное",
			PerkCategory.LUST,
			"perks/firing_blanks",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, -200)),
			Util.newArrayListOfValues("Пока "+Attribute.VIRILITY.getName()+" значение 0 или меньше, оплодотворение [style.colourTerrible(невозможно)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Семя [npc.NamePos] невероятно слабое, и [npc.sheIs] крайне маловероятно, что он когда-либо кого-то забеременеет.");
		}
	};
	

	
	public static AbstractPerk FETISH_BROODMOTHER = new AbstractPerk(20,
			true,
			"мать выводка",
			PerkCategory.LUST,
			"fetishes/fetish_broodmother",
			PresetColour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 20)),
			Util.newArrayListOfValues("2x <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Максимальное количество потомства в пометах матери</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Ваше тело создано для одной цели: производить как можно больше детей. Будь то эффект вашей арканной ауры или просто природная плодовитость, кажется, вы всегда рождаете огромное количество детей за раз.";
			} else {
				return UtilText.parse(owner, "Тело [npc.NamePos] создано для одной цели: производить как можно больше детей. [npc.She], кажется, всегда рождает огромное количество детей за раз.");
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
			Util.newArrayListOfValues("2x <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Максимальное количество потомства в пометах отца</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Ваше семя обладает мощным эффектом: любой, кто будет им оплодотворен, будет рожать огромное количество детей.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] семя обладает мощным эффектом: любой, кто будет им оплодотворен, будет рожать огромное количество детей.");
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
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Смущающие диалоги заклинаний</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Приблизительно переводится с японского как «синдром второго года средней школы»; те, у кого есть «чунибьё», верят и ведут себя так, будто обладают особыми силами. Если раньше чуни могли быть чисто бредовыми мечтателями, то теперь Аркана придает их убеждениям долю истины...");
		}
	};

	public static AbstractPerk SPECIAL_PLAYER = new AbstractPerk(20,
			false,
			"аномальная Аура",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 18)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Вы обладаете удивительно большим количеством природной силы Аркана; гораздо большим, чем должно быть у обычного человека.";
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
			return UtilText.parse(owner, "Мераксис — не кто иная, как сама Темная Сирена! Будучи признанной дочерью старшей лилин Lyssieth, она обладает значительным мастерством владения Арканой.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_ARCANE_TATTOOIST = new AbstractPerk(20,
			false,
			"Татуировщик Арканы",
			PerkCategory.ARCANE,
			"perks/tattoo",
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 15),
					new Value<>(Attribute.ENCHANTMENT_LIMIT, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] прошла через изнурительные тренировки, чтобы научиться наделять татуировки арканными чарами.");
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
			return UtilText.parse(owner, "[npc.Name] всегда [npc.verb(feel)] комфортно носить женскую одежду, каким бы мужским ни было [npc.her] тело.");
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
			return UtilText.parse(owner, "[npc.NameIsFull] очень распущенная, и её [npc.her] мысли часто [npc.verb(find)] заняты сексом.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] имел(а) бесчисленное множество сексуальных партнеров, и [npc.has] совершал(а) с ними всевозможные развратные акты.");
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
			"развратный",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			PresetColour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25),
					new Value<>(Attribute.MAJOR_CORRUPTION, 75)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда [npc.nameIsFull] не занимается этим с совершенно незнакомыми людьми, она почти всегда фантазирует о сексе и мало о чем другом думает, кроме того, какой именно похотливый акт [npc.she] должна совершить со своим следующим партнером [npc.her].");
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
			"арканное обучение",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.DAMAGE_SPELLS, 20)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] прошел(ла) обширную подготовку, чтобы научиться обуздывать аркану.");
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
			return UtilText.parse(owner, "Благодаря необычайному событию в прошлом [npc.her], [npc.nameHasFull] обрел(а) не только огромный запас природной силы арканы, но и врожденное понимание того, как лучше всего ее обуздывать.");
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
			return UtilText.parse(owner, "[npc.Name] страдает от редкой аллергической реакции на аркану, и поэтому почти совершенно неспособен(на) обуздывать ее силу.");
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
			return UtilText.parse(owner, "[npc.NameIsFull] одержим(а) [npc.her] физической формой и проводит(а) часы каждый день, тренируясь и планируя [npc.her] рацион.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] потратил(а) много времени на тренировку [npc.her] тела для боя, и в результате, [npc.sheIs] гораздо сильнее среднестатистического человека.");
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
			"statusEffects/short_sighted",
			Util.newArrayListOfValues(
					PresetColour.BASE_BLACK,
					PresetColour.GENERIC_BAD,
					PresetColour.GENERIC_BAD),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Пока [style.colourMinorBad(не носишь очки для коррекции)], страдаешь от [style.colourTerrible(размытого зрения)]"),
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
						"Обычно [npc.Name] страдает(а) от нарушения зрения, но благодаря [npc.her] [npc.eyeRace] [npc.eyes], [npc.she] может(ет) видеть идеально и без очков.");
			}
			return UtilText.parse(owner,
					"[npc.Name] [npc.verb(suffer)] от нарушения зрения, и в результате [npc.she] [npc.verb(struggle)] видеть без очков. Однако, когда [npc.she] [npc.is] очки для коррекции, [npc.her] зрение становится просто безупречным.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] внушительный опыт владения холодным оружием, что делает [npc.herHim] грозным противником, когда в руках у него оказывается оружие на любой [npc.her] вкус.");
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
			"эксперт дальнего боя",
			PerkCategory.PHYSICAL,
			"perks/ranged_damage",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] обладает внушительным опытом владения метательным оружием, что делает [npc.herHim] грозным противником, когда [npc.her] вооружен оружием на свой вкус.");
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
			"Дитя Скал",
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
			return UtilText.parse(owner, "В жилах [npc.namePos] течет кровь Кимрита, и [npc.she] давно привыкла к холоду жизни высоко в горах. Однако из-за такой отдаленности от [npc.her] сородичей из пустыни, природная сопротивляемость [npc.her] огню снизилась.");
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ENFORCER_FIREARMS_TRAINING = new AbstractPerk(20,
			true,
			"Мастерство огнестрельного оружия",
			PerkCategory.PHYSICAL,
			"perks/enforcer_firearms",
			Util.newArrayListOfValues(
					PresetColour.DAMAGE_TYPE_FIRE,
					PresetColour.CLOTHING_BLACK,
					PresetColour.CLOTHING_GUNMETAL),
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_RANGED_WEAPON, 75)),
			Util.newArrayListOfValues("[style.colourExcellent(Удваивает)] весь урон от огнестрельного оружия"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] не только прошла печально известную изнурительную программу подготовки стрелков, обязательную для всех Enforcer из SWORD, но и закончила её с оценкой «Мастер». Без сомнения, это оставило в душе мрачные, затяжные воспоминания, но в результате [npc.sheIs] стала одной из самых искусных #IF(npc.isFeminine())стрелков#ELSEстрелков#ENDIF в королевстве.");
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
			return UtilText.parse(owner, "Пережив множество интенсивных тренировок с самым азартным и мотивированным физическим тренером Dominion, вы чувствуете заметный прилив сил.");
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
			return UtilText.parse(owner, "[npc.NameHasFull] прошел официальную подготовку в боевых искусствах, что делает [npc.herHim] чрезвычайно опасным противником даже без оружия.");
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
			return UtilText.parse(owner, "<i>Все по-старому. Люди думают, что могут со мной закуситься, но есть только один способ доказать им обратное!</i>");
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk HERO_OF_THEMISCYRA = new AbstractPerk(20,
			false,
			"Герой Фемисциры",
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
				return "Героиня Фемисциры";
			} else {
				return super.getName(owner);
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return "<i>Небеса, фонтаны, каждый уголок земли<br/>Словно слились в едином крике: никогда не слышал<br/>Столь музыкального разлада, столь сладкого грома.</i>";
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk AMAZONIAN_TRAINING = new AbstractPerk(20,
			false,
			"Амазонская подготовка",
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
			return UtilText.parse(owner, "Участвуя в нескольких спаррингах с Аврокарис, вы чувствуете, что узнали пару новых фишек в искусстве боя.");
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
					"[style.boldExcellent(Открывает)] [style.boldLightBlue(превращения в рыб)], если вы [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лиреции была впитана в вашу аркану ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Её сила дополнительно позволила вам превращать своё демоническое тело в тело любого водного существа!"
							:" Если бы вы были демоном, эта сила позволила бы вам превращать части своего тела в части любого водного существа!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LIRECEA_1_DEMON = new AbstractPerk(20,
			false,
			"Истинная сила Лиреции",
			PerkCategory.ARCANE,
			"perks/lilin1",
			PresetColour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldLightBlue(превращения в рыб)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лиреции была впитана в вашу аркану ауру. Её сила была усилена вашей демонической формой, что также позволило вам превращать своё демоническое тело в тело любого водного существа!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LOVIENNE_2 = new AbstractPerk(21,
			false,
			"Сила Ловьенны",
			PerkCategory.ARCANE,
			"perks/lilin2",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldFeral(дикие трансформации)], если вы [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ловьенны была влита в вашу арканную ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Её сила также позволила вам превращать своё демоническое тело в тела диких животных!"
							:" Если бы вы были демоном, эта сила позволила бы вам превращать части своего тела в части диких животных!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LOVIENNE_2_DEMON = new AbstractPerk(21,
			false,
			"Истинная сила Ловьенны",
			PerkCategory.ARCANE,
			"perks/lilin2",
			PresetColour.RACE_BESTIAL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldFeral(дикие трансформации)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ловьенны была влита в вашу арканную ауру. Её сила была усилена вашей демонической формой, что также позволило вам превращать своё демоническое тело в тела диких животных!");
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
					"[style.boldExcellent(Открывает)] способность превращать других в [style.boldDemon(демонов)], если вы [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ласиэль была впитана в вашу арканную ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Её сила дополнительно позволила вам превращать других в демонов!"
							:" Будь вы демоном, эта сила позволила бы вам превращать других в демонов!"));
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
					"[style.boldExcellent(Разблокирует)] способность превращать других в [style.boldDemon(демонов)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ласиэль была впитана в вашу арканную ауру. Её сила была усилена вашей демонической формой, что также позволило вам превращать других в демонов!");
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
					"[style.boldExcellent(Разблокирует)] [style.boldHuman(человеческие трансформации)], если вы [style.boldDemon(демон)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Lyssieth была впитана в вашу арканную ауру. Будь вы демоном, эта сила была бы усилена и позволила бы вам превращать свои части тела в человеческие!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LYSSIETH_4_DEMON = new AbstractPerk(23,
			false,
			"Истинная сила Lyssieth",
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
					"[style.boldExcellent(Открывает)] [style.boldHuman(человеческие трансформации)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Lyssieth пропитала вашу арканную ауру. Её мощь была усилена вашей демонической формой, что также позволило вам превращать части вашего демонического тела в человеческие!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LUNETTE_5 = new AbstractPerk(24,
			false,
			"Сила Lunette",
			PerkCategory.ARCANE,
			"perks/lilin5",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldHorse(млекопитающие трансформации)], если вы [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Lunette пропитала вашу арканную ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Её мощь дополнительно позволила вам превратить ваше демоническое тело в обычное человеческое!"
							:" Если бы вы были демоном, эта сила позволила бы вам превращать части вашего тела в человеческие!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LUNETTE_5_DEMON = new AbstractPerk(24,
			false,
			"Истинная сила Lunette",
			PerkCategory.ARCANE,
			"perks/lilin5",
			PresetColour.RACE_HORSE_MORPH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldHorse(млекопитающие трансформации)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Lunette пропитала вашу арканную ауру. Её мощь была усилена вашей демонической формой, что также позволило вам превращать части вашего демонического тела в части тел млекопитающих!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LYXIAS_6 = new AbstractPerk(25,
			false,
			"Сила Ликсиаса",
			PerkCategory.ARCANE,
			"perks/lilin6",
			PresetColour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldHarpy(птичьи трансформации)], если вы [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ликсиаса была влита в вашу аркану ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Её сила дополнительно позволила вам превращать своё демоническое тело в тела представителей птичьего вида!"
							:" Если бы вы были демоном, эта сила позволила бы вам превращать части вашего тела в части тел птичьего вида!"));
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LYXIAS_6_DEMON = new AbstractPerk(25,
			false,
			"Истинная сила Ликсиаса",
			PerkCategory.ARCANE,
			"perks/lilin6",
			PresetColour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_ARCANE, 10),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<>(Attribute.MAJOR_CORRUPTION, 15),
					new Value<>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Открывает)] [style.boldHarpy(птичьи трансформации)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Ликсиаса была влита в вашу аркану ауру. Её сила была усилена вашей демонической формой, что также позволило вам превращать части вашего демонического тела в части тел птичьего вида!");
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
					"[style.boldExcellent(Открывает)] [style.boldGreenLight(рептильные и амфибийные трансформации)], если вы [style.boldDemon(демон)]", //TODO placeholder TF unlock, might change in the future
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Lyssieth наполнила вашу арканную ауру."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Её сила дополнительно позволила вам превращать своё демоническое тело в тела рептилий и амфибий!"
							:" Если бы вы были демоном, эта сила позволила бы вам превращать части вашего тела в части рептилий и амфибий!"));
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
					"[style.boldExcellent(Разблокирует)] [style.boldGreenLight(превращения в рептилий и амфибий)]",
					"[style.boldExcellent(Иммунитет)] к [style.boldArcane(Команде Lilith)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Эссенция силы Лисофии наполнила вашу арканную ауру. Её сила была усилена вашей демонической формой, что также позволило вам превращать части вашего демонического тела в тела рептилий и амфибий!");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SINGLE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Однохвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail1",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность лис-морфов, наделенных арканой, которые обычно встречаются только на высокогорье Синрин. [npc.Her] служба ёко более высокого ранга даровала [npc.him] один арканный хвост.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk TWO_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Двуххвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail2",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность лис-морфа, наделенного силой Арканы, обычно встречающаяся только в высокогорье Синрин. [npc.Her] служба более высокоранговому ёко даровала [npc.him] два арканных хвоста.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk THREE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Треххвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail3",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность лис-морфа, наделенного силой Арканы, обычно встречающаяся только в высокогорье Синрин. [npc.Her] служба более высокоранговому ёко даровала [npc.him] три арканных хвоста.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk FOUR_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Четыреххвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail4",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность лис-морфа, наделенного силой Арканы, обычно встречающаяся только в высокогорье Синрин. [npc.Her] служба более высокоранговому ёко даровала [npc.him] четыре арканных хвоста.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk FIVE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Пятихвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail5",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность наделенного Арканой лис-морфа, обычно встречающегося только в высокогорье Синрин. [npc.Her] служба более высокоранговому ёко принесла [npc.him] пять арканных хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk SIX_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Шестихвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail6",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность наделенного Арканой лис-морфа, обычно встречающегося только в высокогорье Синрин. [npc.Her] служба более высокоранговому ёко принесла [npc.him] шесть арканных хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk SEVEN_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Семихвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail7",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность наделенного Арканой лис-морфа, обычно встречающегося только в высокогорье Синрин. [npc.Her] служба более высокоранговому ёко принесла [npc.him] семь арканных хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk EIGHT_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Восьмихвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail8",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность лис-морфа, наделенного силой арканы, который обычно встречается только в высокогорье Синрин. [npc.Her] служба ёко более высокого ранга даровала [npc.him] восемь арканных хвостов.");
		}

		@Override
		public boolean isHiddenPerk() {	return true; }
	};

	public static AbstractPerk NINE_TAILED_YOUKO = new AbstractPerk(20,
			false,
			"Девятихвостая ёко",
			PerkCategory.ARCANE,
			"statusEffects/race/raceFoxTail9",
			PresetColour.RACE_FOX_MORPH,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.boldExcellent(Разблокирует)] [style.boldFox(превращения в ёко!)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] ёко; разновидность лис-морфа, наделенного силой арканы, который обычно встречается только в высокогорье Синрин. [npc.Her] служба ёко более высокого ранга даровала [npc.him] девять арканных хвостов.");
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
			"элементаль",
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
					"Будучи существами из чистой арканной энергии, элементали способны без труда обуздывать аркану для сотворения заклинаний. Из-за похотливой природы арканы они также невероятно развратны и всегда готовы и жаждут заняться сексом...");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] сможет использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] сможет использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] сможет использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return UtilText.parse(owner, "Если получить указание сосредоточиться на способности [npc.her] обуздывать арканическое пламя и принимать его облик, [npc.name] сможет сделать первый шаг к раскрытию следующей тайны школы арканического Огня.");
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
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает идти по пути огненного элементаля, и, хотя [npc.she] еще предстоит пройти немалый путь до достижения новой вехи, [npc.her] сродство с арканическим огнем неуклонно растет.");
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
			return UtilText.parse(owner, "Сродство [npc.NamePos] с арканическим огнем колоссально возросло, и [npc.sheIs] находится на пороге открытия чего-то невероятно могущественного!");
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
			return UtilText.parse(owner, "Пылая прекрасной и ужасающей мощью самого солнца, [npc.name] обычно достаточно всего одного удара, чтобы вывести из строя любого несчастного, кто осмелится навлечь на себя [npc.her] гнев.");
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
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_BOOST_MINOR = new AbstractPerk(20,
			false,
			"влияние",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth1",
			PresetColour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, 1),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Если сосредоточиться на способности [npc.her] создавать мощные физические воплощения, [npc.name] сможет сделать первый шаг к разгадке следующей тайны школы Арканы Земли.");
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
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает идти по пути элементаля земли, и, хотя [npc.she] еще предстоит пройти немалый путь до достижения новой вехи, [npc.her] сродство с арканной силой неуклонно растет.");
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
			return UtilText.parse(owner, "Сродство [npc.NamePos] с арканной силой значительно возросло, и [npc.sheIs] находится на пороге открытия чего-то невероятно могущественного!");
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
			return UtilText.parse(owner, "Принимая почти неуязвимую форму, [npc.name] теперь способен игнорировать почти любые атаки, что позволяет [npc.herHim] подбираться вплотную к [npc.her] врагам и наносить сокрушительный удар кулаком или ногой.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return UtilText.parse(owner, "Если сосредоточиться на способности [npc.her] обуздывать арканный лед и принимать его форму, [npc.name] сможет сделать первый шаг к разгадке следующей тайны школы Арканы Воды.");
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
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает идти по пути водного элементаля, и, хотя [npc.she] еще предстоит пройти немалый путь до достижения новой вехи, [npc.her] сродство с арканным льдом неуклонно растет.");
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
			return UtilText.parse(owner, "[npc.NamePos] сродство с арканным льдом колоссально возросло, и [npc.sheIs] находится на пороге открытия чего-то невероятно могущественного!");
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
			return UtilText.parse(owner, "Скользя из места в место с неземной грацией, [npc.name] способен мгновенно изменять и перестраивать [npc.her] тело, что позволяет [npc.herHim] совершать самые невероятные физические подвиги.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return UtilText.parse(owner, "Если сосредоточиться на [npc.her] способности обуздывать силу арканного яда, [npc.name] сможет сделать первый шаг к раскрытию следующей тайны школы арканного Воздуха.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST = new AbstractPerk(20,
			false,
			"шторм",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air2",
			PresetColour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_POISON, 3),
					new Value<>(Attribute.RESISTANCE_POISON, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает идти по пути воздушного элементаля, и, хотя [npc.she] еще предстоит пройти немалый путь до достижения новой вехи, [npc.her] сродство с арканным ядом неуклонно растет.");
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
			return UtilText.parse(owner, "[npc.NamePos] сродство с арканным ядом массово возросло, и [npc.sheIs] на грани открытия чего-то невероятно могущественного!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"сверхъячейка",
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
			return UtilText.parse(owner, "Любой враг, достаточно глупый, чтобы навлечь на себя [npc.namePos] гнев, вскоре окажется в самом центре бури, где каждый из [npc.her] разрушительных снарядов идеально попадает в цель благодаря направляющим порывам ветра, которые [npc.she] призывает.");
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
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return Util.newArrayListOfValues("Получить заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Когда привязано к школе "+getSchool().getName()+", [npc.name] способен использовать заклинание '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Улучшить",
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
			return Util.newArrayListOfValues("Улучшает заклинание '<span style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Улучшить",
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
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] заклинание, "+getSpell().getName()+", будет улучшено и получит дополнительную силу '"+getSpellUpgrade().getName()+"'.");
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
			return UtilText.parse(owner, "Если дать указание сосредоточиться на [npc.her] способности обуздывать чистую мощь Arcane, [npc.name] сможет сделать первый шаг к раскрытию следующей тайны школы Arcane.");
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
			return UtilText.parse(owner, "[npc.NameIsFull] продолжает идти по пути арканного элементаля, и, хотя [npc.she] еще предстоит пройти немалый путь до достижения новой вехи, [npc.her] сродство с арканной похотью неуклонно растет.");
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
			return UtilText.parse(owner, "[npc.NamePos] сродство с Arcane массово возросло, и [npc.sheIs] на грани открытия чего-то невероятно могущественного!");
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
			return UtilText.parse(owner, "Те, кто взирают на [npc.namePos], "+(owner.isFeminine()?"прекрасное тело и потрясающее,":"точеное тело и красивое")
					+" лицо, обнаруживают, что попадают под [npc.her] заклинание, и через мгновения оказываются полностью поглощены дикой, животной похотью.");
		}
	};
	
	// Doll perks:
	
	public static AbstractPerk DOLL_PHYSICAL_CORE = new AbstractPerk(20,
			false,
			"наполненный арканой силикон",
			PerkCategory.PHYSICAL,
			"perks/doll/physical0",
			Util.newArrayListOfValues(PresetColour.BASE_WHITE, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<>(Attribute.RESISTANCE_FIRE, 10),
					new Value<>(Attribute.RESISTANCE_ICE, 10),
					new Value<>(Attribute.RESISTANCE_POISON, 10)),
			Util.newArrayListOfValues(
					"Отверстия медленно впитывают жидкости"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Тело [npc.NamePos] целиком состоит из подобного силикону полимера, наполненного арканой, состав которого является тщательно охраняемым секретом 'Lovienne's Luxuries'. Помимо способности медленно впитывать и преобразовывать накопленные жидкости в энергию, этот материал необычайно долговечен.");
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
					"[style.colourExcellent(Разблокирует)] аутофелляцию и аутокуннилингус")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Тело [npc.NamePos] исключительно гибкое, что позволяет [npc.herHim] изгибать [npc.her] тело в позах, которым позавидовал бы гимнаст.");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_2 = new AbstractPerk(20,
			false,
			"нет возможности размножения",
			PerkCategory.PHYSICAL,
			"perks/doll/physical2",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(
					new Value<>(Attribute.VIRILITY, -1000),
					new Value<>(Attribute.FERTILITY, -1000)),
			Util.newArrayListOfValues(
					"Оплодотворение [style.colourTerrible(невозможно)]",
					"Беременность [style.colourTerrible(невозможна)]",
					"[style.colourTerrible(Не может)] высиживать яйца")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NamePos] искусственное тело совершенно неспособно выносить потомство [npc.her]. [npc.Her] резиновые отверстия также непригодны для яиц.");
		}
	};
	
	public static AbstractPerk DOLL_PHYSICAL_3 = new AbstractPerk(20,
			false,
			"органы отсутствуют",
			PerkCategory.PHYSICAL,
			"perks/doll/physical3",
			PresetColour.BASE_RED_LIGHT,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 50)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Не подвержен)] воздействию жидкостей, психоактивных веществ и Алкоголя",
					"Отверстия имеют [style.colourExcellent(неограниченную)] глубину")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Внутренняя структура тела [npc.namePos] состоит исключительно из глубоких проходов, соединенных с отверстиями [npc.her]. Ввиду отсутствия каких-либо биологических систем, [npc.name] [npc.is] невосприимчив(а) к воздействию большинства веществ.");
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
					"[npc.NameHasFull] была создана и спроектирована как секс-кукла, и хотя [npc.she] может выполнять множество других задач, истинная цель [npc.her] — это получать еблом.");
		}
	};

	public static AbstractPerk DOLL_LUST_1 = new AbstractPerk(20,
			false,
			"жить, чтобы трахаться",
			PerkCategory.LUST,
			"perks/doll/lust1",
			PresetColour.RACE_DOLL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Может [style.colourTerrible(никогда)] <span style='color:"+SexPace.SUB_RESISTING.getColour().toWebHexString()+";'>сопротивляться</span> в [style.colourSex(секс-сценах)]",
					"Не возражает против <span style='color:"+SexPace.SUB_RESISTING.getColour().toWebHexString()+";'>садистские</span> сексуальные действия",
					"Ориентация всегда [style.colourAndrogynous(амбифильная)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name] только [npc.verb(want)] одно — быть трахаемой без остановки, неважно, кто именно трахает и насколько грубо.");
		}
	};

	public static AbstractPerk DOLL_LUST_2 = new AbstractPerk(20,
			false,
			"принадлежит",
			PerkCategory.LUST,
			"perks/doll/lust2",
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Всегда будет [style.colourSex(повиноваться)] владельцу",
					"[style.colourObedience(Покорность)] всегда [style.colourExcellent(100)]"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name] [npc.verb(know)], что [npc.sheIs] не более чем объектом, и будет делать абсолютно всё, что [npc.her] попросит [npc.herHim].");
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
					"[style.colourTerrible(Не может)] обретать [style.colourFetish(фетиши)]",
					"[style.colourFetish(Желания фетишей)] всегда [style.colourHuman(нейтральны)]",
					"Безразлична к эксгибиционизму, женственности в одежде, целомудрию и грязи"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Как автомат, лишенный свободы воли, [npc.name] [npc.do] не имеет собственного мнения о различных фетишах и [npc.is] доволен тем, чтобы потакать любым просьбам [npc.her] партнера к [npc.herHim].");
		}
	};
	
	public static AbstractPerk DOLL_ARCANE_CORE = new AbstractPerk(20,
			false,
			"автомат",
			PerkCategory.ARCANE,
			"perks/doll/arcane0",
			Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_PINK_LIGHT),
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -25),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -25)),
			Util.newArrayListOfValues(
					"Может оставаться неподвижным неограниченное время"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] — автомат, и хотя [npc.sheIs] способен двигаться очень реалистично, [npc.she] никогда не будет столь же ловким или отзывчивым, как живой человек. По приказу [npc.she] может оставаться совершенно неподвижным в течение неограниченного времени.");
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
					"Может [style.colourTerrible(никогда)] не генерировать эссенции во время [style.colourSex(оргазма)]",
					"Может [style.colourTerrible(никогда)] поглощать эссенции",
					"[style.colourExcellent(Иммунитет)] к [style.colourTfGeneric(стандартным трансформативам)]"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Поскольку [npc.sheIs] — всего лишь кукла, а не живой человек, [npc.name] [npc.verb(lack)] арканную ауру, и поэтому не может ни поглощать, ни генерировать эссенции. Обычные методы трансформации также не действуют на [npc.herHim], лишь специальные трансформативы для кукол могут иметь эффект.");
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
					"Может [style.colourTerrible(никогда)] получать [style.colourExperience(опыт)]",
					"[style.colourTerrible(Навсегда заблокирован)] на [style.colourExperience(1 уровне)]",
					"Может [style.colourTerrible(никогда)] приобретать черты личности",
					"[style.colourAffection(Привязанность)] к другим составляет <span style='color:"+PresetColour.AFFECTION_NEUTRAL.toWebHexString()+";'>всегда нейтрален</span>")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Лишенный воли и совести, [npc.nameIsFull] не способен учиться на [npc.her] опыте, и, следовательно, [npc.is] никогда не вырастет и не разовьется как полноценная личность.");
		}
	};

	public static AbstractPerk DOLL_ARCANE_3 = new AbstractPerk(20,
			false,
			"на базе арканы",
			PerkCategory.ARCANE,
			"perks/doll/arcane3",
			Util.newArrayListOfValues(PresetColour.BASE_PINK, PresetColour.GENERIC_ARCANE),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(
					"Не нужно есть, пить или спать",
					"Получает [style.colourExcellent(1000)] "+Attribute.HEALTH_MAXIMUM.getColouredName("протяженность")+" во время воздействия арканного шторма"),
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Вместо того чтобы получать калории из пищи или напитков для функционирования, [npc.name] пассивно [npc.verb(absorb)] фоновую арканную энергию, чтобы питать [npc.herself]. Благодаря этому арканные штормы даруют [npc.herHim] значительный запас жизненных сил.");
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
						return UtilText.parse(owner, "[npc.NameHasFull] глубокие знания в области "+(mainSubspecies?sub.getRace().getNamePlural(false):subToUse.getNamePlural(null))+", и поэтому может наносить повышенный урон в бою с ними.");
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
