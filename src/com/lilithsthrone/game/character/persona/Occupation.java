package com.lilithsthrone.game.character.persona;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.3.9.4
 * @author Innoxia
 */
public enum Occupation {
	
	// Unique:
	
	ELEMENTAL(Perk.ELEMENTAL_CORE_OCCUPATION, "elemental", "[npc.NameIsFull] — это конструкт из чистой арканной энергии, физическое воплощение ауры [npc.her] призывателя.", OccupationTag.HAS_PREREQUISITES),

	NPC_ENFORCER_PATROL_INSPECTOR(Perk.JOB_NPC_ENFORCER_PATROL_INSPECTOR, "Enforcer", "[npc.NameIs] — член отряда Enforcer.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_PATROL),
	NPC_ENFORCER_PATROL_SERGEANT(Perk.JOB_NPC_ENFORCER_PATROL_SERGEANT, "Enforcer", "[npc.NameIs] — член отряда Enforcer.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_PATROL),
	NPC_ENFORCER_PATROL_CONSTABLE(Perk.JOB_NPC_ENFORCER_PATROL_CONSTABLE, "Enforcer", "[npc.NameIs] — член отряда Enforcer.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_PATROL),

	NPC_ENFORCER_SWORD_SUPER(Perk.JOB_NPC_ENFORCER_SWORD_SUPER, "МЕЧ Enforcer", "[npc.NameIs] — Enforcer и член организации SWORD.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_CHIEF_INSPECTOR(Perk.JOB_NPC_ENFORCER_SWORD_CHIEF_INSPECTOR, "МЕЧ Enforcer", "[npc.NameIs] — Enforcer и член организации SWORD.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_INSPECTOR(Perk.JOB_NPC_ENFORCER_SWORD_INSPECTOR, "МЕЧ Enforcer", "[npc.NameIs] — Enforcer и член организации SWORD.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_SERGEANT(Perk.JOB_NPC_ENFORCER_SWORD_SERGEANT, "МЕЧ Enforcer", "[npc.NameIs] — Enforcer и член организации SWORD.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),
	NPC_ENFORCER_SWORD_CONSTABLE(Perk.JOB_NPC_ENFORCER_SWORD_CONSTABLE, "МЕЧ Enforcer", "[npc.NameIs] — Enforcer и член организации SWORD.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_SWORD),

	NPC_ENFORCER_ORICL_INSPECTOR(Perk.JOB_NPC_ENFORCER_ORICL_INSPECTOR, "ORICL Enforcer", "[npc.NameIs] — Enforcer и состоит в ORICL.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_ORICL),
	NPC_ENFORCER_ORICL_SERGEANT(Perk.JOB_NPC_ENFORCER_ORICL_SERGEANT, "ORICL Enforcer", "[npc.NameIs] — Enforcer и состоит в ORICL.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_ORICL),
	NPC_ENFORCER_ORICL_CONSTABLE(Perk.JOB_NPC_ENFORCER_ORICL_CONSTABLE, "ORICL Enforcer", "[npc.NameIs] — Enforcer и состоит в ORICL.", OccupationTag.HAS_PREREQUISITES, OccupationTag.ENFORCER_ORICL),

	
	NPC_HARPY_MATRIARCH(Perk.JOB_NPC_HARPY_MATRIARCH, "матриарх гарпий", "[npc.NameIsFull] — матриарх стаи гарпий.", OccupationTag.HAS_PREREQUISITES),
	NPC_HARPY_FLOCK_MEMBER(Perk.JOB_NPC_HARPY_FLOCK_MEMBER, "член стаи гарпий", "[npc.NameIsFull] — член стаи гарпий", OccupationTag.HAS_PREREQUISITES),

	NPC_CULTIST(Perk.JOB_NPC_CULTIST, "Культист", "[npc.NameIs] штатный член «Культа Lilith».", OccupationTag.HAS_PREREQUISITES),

	NPC_SLAVER_ADMIN(Perk.JOB_NPC_SLAVER_ADMIN, "надзиратель работорговли", "[npc.NameIsFull] надзиратель администрации работорговцев.", OccupationTag.HAS_PREREQUISITES),

	NPC_NIGHTCLUB_OWNER(Perk.JOB_NPC_NIGHTCLUB_OWNER, "владелец ночного клуба", "[npc.Name] [npc.verb(own)] и [npc.verb(manage)] популярный ночной клуб.", OccupationTag.HAS_PREREQUISITES),
	NPC_BAR_TENDER(Perk.JOB_NPC_BARMAID, "bartender", "[npc.Name] [npc.verb(work)] барменом.", OccupationTag.EVENING_SHIFT),
	NPC_BOUNCER(Perk.JOB_NPC_BOUNCER, "вышибала", "[npc.NameIsFull] вышибала, отвечающий за то, чтобы отсеивать сброд из ночных клубов и баров."),

	NPC_BEAUTICIAN(Perk.JOB_NPC_BEAUTICIAN, "beautician", "[npc.Name] [npc.verb(work)] косметологом."),
	
	NPC_ARCANE_RESEARCHER(Perk.JOB_NPC_ARCANE_RESEARCHER, "исследователь Арканы", "[npc.Name] тратит всё своё [npc.her] время на изучение Арканы.", OccupationTag.HAS_PREREQUISITES),

	NPC_CLOTHING_STORE_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "владелец магазина одежды", "[npc.NameIs], владелец магазина одежды.", OccupationTag.HAS_PREREQUISITES),
	NPC_GYM_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "владелец спортзала", "[npc.NameIs], владелец спортзала.", OccupationTag.HAS_PREREQUISITES),
	NPC_STORE_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "владелец магазина", "[npc.NameIs], владелец магазина.", OccupationTag.HAS_PREREQUISITES),
	NPC_CASINO_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "владелец казино", "[npc.NameIs], владелец казино.", OccupationTag.HAS_PREREQUISITES),
	NPC_BUSINESS_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "владелец бизнеса", "[npc.NameIs] владелец собственного бизнеса.", OccupationTag.HAS_PREREQUISITES),
	NPC_TAVERN_OWNER(Perk.JOB_NPC_SHOP_MANAGER, "владелец таверны", "[npc.NameIs] владелец таверны.", OccupationTag.HAS_PREREQUISITES),
	NPC_FARMER(Perk.JOB_NPC_FARMER, "фермер", "[npc.NameIs] фермер.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_JOURNALIST(Perk.JOB_NPC_JOURNALIST, "journalist", "[npc.NameIs] журналист.", OccupationTag.HAS_PREREQUISITES),
	
	REINDEER_OVERSEER(Perk.JOB_NPC_REINDEER_OVERSEER, "надсмотрщик", "[npc.NameIs] надсмотрщик одной из бригад оленеводов, которые мигрируют в Dominion зимой.", OccupationTag.HAS_PREREQUISITES),

	NPC_SLIME_QUEEN(Perk.JOB_NPC_SLIME_QUEEN, "королева слаймов", "[npc.NameHas] провозгласила себя «королевой слаймов» и отвечает за превращение множества жителей Submission в слаймов.", OccupationTag.HAS_PREREQUISITES),
	NPC_SLIME_QUEEN_GUARD(Perk.JOB_NPC_SLIME_QUEEN_GUARD, "стражник королевы слаймов", "[npc.NameIs] одна из трёх слаймов, охраняющих башню [slimeQueen.namePos].", OccupationTag.HAS_PREREQUISITES),
	
	NPC_EPONA(Perk.JOB_EPONA, "менеджер в игре «рулетка беременности»", "[npc.NameIs] заведует игрой «рулетка беременности» в игорном притоне Submission.", OccupationTag.HAS_PREREQUISITES),

	NPC_GANG_LEADER(Perk.JOB_GANG_LEADER, "главарь банды", "[npc.NameIsFull] предводительница пресловутой преступной банды.", OccupationTag.HAS_PREREQUISITES),
	NPC_GANG_BODY_GUARD(Perk.JOB_GANG_BODY_GUARD, "телохранитель банды", "[npc.NameIsFull] личный телохранитель главаря своей банды.", OccupationTag.HAS_PREREQUISITES),
	NPC_GANG_MEMBER(Perk.JOB_GANG_MEMBER, "член банды", "[npc.NameIsFull] член печально известной преступной банды.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_STABLE_MISTRESS(Perk.JOB_NPC_STABLE_MISTRESS, "конюшенная хозяйка", "[npc.NameIsFull] отвечает за обучение и уход за огромным количеством рабов-кентавров.", OccupationTag.HAS_PREREQUISITES) {
		@Override
		public String getName(GameCharacter character) {
			if(character==null) {
				return "управляющий конюшней";
			} else if(character.isFeminine()) {
				return "конюшенная хозяйка";
			}
			return "конюх";
		}
	},
	
	NPC_LYSSIETH_GUARD(Perk.JOB_LYSSIETH_GUARD, "страж Lyssieth", "[npc.NameIsFull] одна из непризнанных дочерей Lyssieth, которой поручено охранять дворец матери.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_ELDER_LILIN(Perk.JOB_ELDER_LILIN, "старшая лилин", "[npc.NameIsFull] одна из семи старших лилин; признанные дочери самой Lilith.", OccupationTag.HAS_PREREQUISITES),

	NPC_TAUR_TRANSPORT(Perk.JOB_TAUR_TRANSPORT, "перевозчик тавров", "[npc.Name] использует [npc.her] бычье тело на полную мощь, запрягая его в телеги и перевозя товары.", OccupationTag.HAS_PREREQUISITES),

	NPC_ELIS_MAYOR(Perk.JOB_NPC_MAYOR, "мэр Элиса", "[npc.NameIs] — мэр Элиса, отвечающая за благополучие и безопасность не только самого города, но и окрестных полей Фолой.", OccupationTag.HAS_PREREQUISITES),
	NPC_ASSISTANT(Perk.JOB_NPC_ASSISTANT, "личная помощница", "[npc.NameIs] — личная помощница, которая помогает [npc.her] боссу в самых разных повседневных делах.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_LUNETTE_HERD(Perk.JOB_LUNETTE_HERD, "Дочь Лунетт", "[npc.NameIsFull] — одна из дочерей Лунетт, унаследовавшая от [npc.her] матери страсть к сеянию хаоса и разрушения.", OccupationTag.HAS_PREREQUISITES),

	NPC_MUSHROOM_FORAGER(Perk.JOB_NPC_MUSHROOM_FORAGER, "собирательница грибов", "[npc.Name] [npc.verb(forage)] психоделические грибы в пещерах летучих мышей под Submission, а затем продает их.", OccupationTag.HAS_PREREQUISITES),

	NPC_LUNETTE_RECOGNISED_DAUGHTER(Perk.JOB_LUNETTE_RECOGNISED_DAUGHTER, "признанная дочь Лунетт", "[npc.NameIsFull] признанная дочь самой Лунетт, и потому она значительно сильнее обычного демона.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_AMAZONIAN_QUEEN(Perk.JOB_AMAZONIAN_QUEEN, "Королева амазонок", "[npc.NameIsFull] королева амазонок, и потому [npc.has] обладает значительной силой и боевыми навыками.", OccupationTag.HAS_PREREQUISITES),
	NPC_AMAZONIAN(Perk.JOB_AMAZONIAN, "Амазонка", "[npc.NameIsFull] амазонка, и потому [npc.has] провела много времени, тренируясь для боя.", OccupationTag.HAS_PREREQUISITES),

	NPC_PUGILIST(Perk.JOB_PUGILIST, "pugilist", "[npc.NameIsFull] профессиональный боксер, и потому она — грозный противник в бою.", OccupationTag.HAS_PREREQUISITES),

	NPC_LILIN_PAWN(Perk.JOB_ELDER_LILIN_PAWN, "помощница старшей лилин", "[npc.NameIsFull] выполнение приказов от имени лилин.", OccupationTag.HAS_PREREQUISITES),

	NPC_SEX_DOLL(Perk.JOB_SEX_DOLL, "секс-кукла", "[npc.NameIsFull] очень реалистичная секс-кукла.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_YOUKO(Perk.JOB_YOUKO, "ёко", "[npc.NameIsFull] — ёко из высокогорья Синрин, и большую часть [npc.her] жизни посвятила практике искусств Арканы.", OccupationTag.HAS_PREREQUISITES),
	
	// NPC generic histories:

	NPC_UNEMPLOYED(Perk.JOB_UNEMPLOYED, "unemployed", "[npc.NameIsFull] безработная.", OccupationTag.HAS_PREREQUISITES),

	NPC_SLAVE(Perk.JOB_SLAVE, "slave", "[npc.NameIsFull] рабыня, которая должна исполнять желания [npc.her] хозяина.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_CAPTIVE(Perk.JOB_CAPTIVE, "captive", "[npc.NameHasFull] была похищена и удерживается против [npc.her] воли.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_REBEL_FIGHTER(Perk.JOB_NPC_REBEL_FIGHTER, "повстанка", "[npc.NameIsFull] боец, сражающийся за дело повстанцев.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_PROSTITUTE(Perk.JOB_PROSTITUTE, "prostitute", "[npc.NameIsFull] проститутка, зарабатывающая на жизнь продажей [npc.her] своего тела.", OccupationTag.LOWLIFE),
	
	NPC_STRIPPER(Perk.JOB_MISC, "stripper", "[npc.Name] [npc.verb(work)] в качестве стриптизерши.", OccupationTag.EVENING_SHIFT) {
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.TUESDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},
	
	NPC_MASSAGE_THERAPIST(Perk.JOB_MISC, "массажистка", "[npc.Name] [npc.verb(work)] в спа-салоне массажисткой."),
	
	NPC_WAITRESS(Perk.JOB_MISC, "waitress", "[npc.Name] [npc.verb(work)] официанткой в ресторане.") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	NPC_MUSICIAN(Perk.JOB_MISC, "musician", "[npc.Name] [npc.verb(work)] музыкантом.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_FITNESS_INSTRUCTOR(Perk.JOB_MISC, "инструктор по фитнесу", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_MUGGER(Perk.JOB_MUGGER, "mugger", "[npc.NameIsFull] грабителем, и [npc.verb(make)] зарабатывает на жизнь кражей чужих вещей.", OccupationTag.LOWLIFE),

	NPC_BOUNTY_HUNTER(Perk.JOB_BOUNTY_HUNTER, "охотник за головами", "[npc.NameIsFull] охотник за головами, который получает плату за выслеживание и поимку разыскиваемых преступников.", OccupationTag.HAS_PREREQUISITES),
	
	NPC_CONSTRUCTION_WORKER(Perk.JOB_CONSTRUCTION_WORKER, "строитель", "-"),
	NPC_CONSTRUCTION_WORKER_ARCANE(Perk.JOB_CONSTRUCTION_WORKER_ARCANE, "арканный строитель", "-", OccupationTag.HAS_PREREQUISITES),

	NPC_MECHANIC(Perk.JOB_MISC, "механик", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_TEACHER(Perk.JOB_MISC, "teacher", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_LIBRARIAN(Perk.JOB_MISC, "librarian", "-"),
	
	NPC_UNIVERSITY_STUDENT(Perk.JOB_MISC, "студент университета", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_WRITER(Perk.JOB_MISC, "writer", "-"),
	
	NPC_ENGINEER(Perk.JOB_MISC, "инженер", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_ARCHITECT(Perk.JOB_MISC, "архитектор", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_DOCTOR(Perk.JOB_MISC, "doctor", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_MAID(Perk.JOB_MISC, "maid", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},

	NPC_BUTLER(Perk.JOB_MISC, "butler", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},

	NPC_OFFICE_WORKER(Perk.JOB_NPC_OFFICE_WORKER, "офисный работник", "[npc.Name] работает в крупной компании, и [npc.has] ежедневно управляет [npc.her] подчиненными, финансами компании и сложными клиентами.") {
		@Override
		public String getName(GameCharacter character) {
			if(character==null) {
				return "офисный работник";
			} else if(character.isFeminine()) {
				return "бизнесвумен";
			}
			return "бизнесмен";
		}
	},
	
	NPC_RECEPTIONIST(Perk.JOB_MISC, "receptionist", "[npc.Name] работает администратором на ресепшене и каждый день имеет дело с множеством посетителей и клиентов."),
	
	NPC_SHOP_ASSISTANT(Perk.JOB_MISC, "продавец-консультант", "-"),
	
	NPC_ARTIST(Perk.JOB_MISC, "artist", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_NURSE(Perk.JOB_MISC, "медсестра", "-") {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
		@Override
		public DayOfWeek getStartDay() {
			return DayOfWeek.MONDAY;
		}
		@Override
		public DayOfWeek getEndDay() {
			return DayOfWeek.SATURDAY;
		}
	},
	
	NPC_CHEF(Perk.JOB_MISC, "chef", "-"),
	
	NPC_ATHLETE(Perk.JOB_MISC, "athlete", "-", OccupationTag.HAS_PREREQUISITES),
	
	NPC_MODEL(Perk.JOB_MISC, "model", "-"),

	NPC_TRADER(Perk.JOB_NPC_SHOP_MANAGER, "торговец", "[npc.Name] — торговец, который зарабатывает на жизнь покупкой и продажей различных товаров.", OccupationTag.HAS_PREREQUISITES),
	
	
	
	// Player histories:

	UNEMPLOYED(Perk.JOB_UNEMPLOYED,
			"unemployed",
			"Ты какое-то время был без работы.",
			OccupationTag.PLAYER_ONLY),
	
	OFFICE_WORKER(Perk.JOB_OFFICE_WORKER,
			"офисный работник",
			"Ты работаешь в местном офисе: занимаешься бумажной волокитой, отвечаешь на звонки и электронные письма, в общем, делаешь всё понемногу.",
			OccupationTag.PLAYER_ONLY),
	
	STUDENT(Perk.JOB_STUDENT,
			"student",
			"Ты студент городского университета, но пока ещё не решил, на каком факультете хочешь специализироваться.",
			OccupationTag.PLAYER_ONLY),

	MUSICIAN(Perk.JOB_MUSICIAN,
			"musician",
			"Ты музыкант, и помимо умения играть на самых разных инструментах, ты ещё и очень хорошо поёшь.",
			OccupationTag.PLAYER_ONLY),
	
	TEACHER(Perk.JOB_TEACHER,
			"teacher",
			"Ты учитель и уже несколько лет работаешь в местной школе.",
			OccupationTag.PLAYER_ONLY),
	
	WRITER(Perk.JOB_WRITER,
			"writer",
			"Ты писатель и последние несколько месяцев работаешь над своим новым романом.",
			OccupationTag.PLAYER_ONLY),
	
	CHEF(Perk.JOB_CHEF,
			"chef",
			"Ты шеф-повар в местном ресторане.",
			OccupationTag.PLAYER_ONLY),

	CONSTRUCTION_WORKER(Perk.JOB_PLAYER_CONSTRUCTION_WORKER,
			"строитель",
			"Ты опытный и высококвалифицированный строитель.",
			OccupationTag.PLAYER_ONLY),
	
	SOLDIER(Perk.JOB_SOLDIER,
			"soldier",
			"Вы — солдат, и сейчас вовсю наслаждаетесь своим отпуском.",
			OccupationTag.PLAYER_ONLY),

	ATHLETE(Perk.JOB_ATHLETE,
			"athlete",
			"Вы — атлет, и сейчас усиленно тренируетесь перед следующим крупным соревнованием.",
			OccupationTag.PLAYER_ONLY),

	ARISTOCRAT(Perk.JOB_ARISTOCRAT,
			"aristocrat",
			"Вам никогда не приходилось работать ни дня в жизни — и всё благодаря тому, что вы член старинной и невероятно богатой аристократической семьи.",
			OccupationTag.PLAYER_ONLY),
	
	MAID(Perk.JOB_MAID,
		"maid",
		"Вы — горничная, нанятая богатой семьей для поддержания чистоты в их особняке.",
		OccupationTag.PLAYER_ONLY) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.isFeminine();
		}
	},
	
	BUTLER(Perk.JOB_BUTLER,
			"butler",
			"Вы — дворецкий, нанятый богатой семьей для надзора за горничными и приема посетителей.",
			OccupationTag.PLAYER_ONLY) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return !character.isFeminine();
		}
	},
	
	TOURIST(Perk.JOB_TOURIST,
			"Американский турист",
			"В свой отпуск в этом году вы решили отправиться в Англию.",
			OccupationTag.PLAYER_ONLY) {
		@Override
		public boolean isAvailable(GameCharacter character) {
			return Main.game.isSillyModeEnabled();
		}
	};
	
	
	private static List<Occupation> historiesList;
	
	public static List<Occupation> getAvailableHistories(GameCharacter character) {
		historiesList = new ArrayList<>();

		for(Occupation history : Occupation.values()) {
			if(history.isAvailable(character) && (character.isPlayer()?history.isAvailableToPlayer():!history.isAvailableToPlayer())) {
				historiesList.add(history);
			}
		}
		
		return historiesList;
	}


	protected static boolean[] noWorkHours = new boolean[24];
	protected static boolean[] daylightWorkHours = new boolean[24];
	protected static boolean[] eveningWorkHours = new boolean[24];
	protected static boolean[] nightWorkHours = new boolean[24];
	
	static {
		for(int i=0; i<8; i++) {
			daylightWorkHours[9+i] = true;
			
			int hour = (18+i)%24;
			eveningWorkHours[hour] = true;
			
			hour = (21+i)%24;
			nightWorkHours[hour] = true;
		}
	}

	private String name;
	private String description;
	private AbstractPerk associatedPerk;
	private List<OccupationTag> occupationTags;

	private Occupation(AbstractPerk associatedPerk,
			String name,
			String description,
			OccupationTag... occupationTags) {
		
		this.associatedPerk = associatedPerk;
		this.name = name;
		this.description = description;
		
		this.occupationTags = new ArrayList<>();
		for(OccupationTag tag : occupationTags) {
			this.occupationTags.add(tag);
		}
	}
	
	public boolean isAvailable(GameCharacter character) {
		return !occupationTags.contains(OccupationTag.HAS_PREREQUISITES);
	}

	public void applyExtraEffects(GameCharacter character) {
	}

	public void revertExtraEffects(GameCharacter character) {
	}

	public boolean isAvailableToPlayer() {
		return occupationTags.contains(OccupationTag.PLAYER_ONLY);
	}

	public AbstractPerk getAssociatedPerk() {
		return associatedPerk;
	}
	
	public String getName(GameCharacter character) {
		return name;
	}

	public String getDescription(GameCharacter character) {
		return UtilText.parse(character, description);
	}

	public boolean isLowlife() {
		return occupationTags.contains(OccupationTag.LOWLIFE);
	}

	public List<OccupationTag> getOccupationTags() {
		return occupationTags;
	}
	
	public boolean isAtWork(int hour) {
		return Main.game.getDateNow().getDayOfWeek().getValue()>=getStartDay().getValue()
				&& Main.game.getDateNow().getDayOfWeek().getValue()<=getEndDay().getValue()
				&& getWorkHours()[hour];
	}
	
	public boolean[] getWorkHours() {
		if(this.getOccupationTags().contains(OccupationTag.LOWLIFE) || this==NPC_UNEMPLOYED || this==UNEMPLOYED) {
			return noWorkHours;
		}
		if(this.getOccupationTags().contains(OccupationTag.EVENING_SHIFT)) {
			return eveningWorkHours;
		}
		if(this.getOccupationTags().contains(OccupationTag.NIGHT_SHIFT)) {
			return nightWorkHours;
		}
		return daylightWorkHours;
	}
	
	public int getWorkHourStart() {
		for(int i=0; i<24; i++) {
			int hour = (6+i)%24;
			if(getWorkHours()[hour]) {
				return 6+i;
			}
		}
		return 0;
	}
	
	public int getWorkHourEnd() {
		return (getWorkHourStart()+8)%24;
	}
	
	public DayOfWeek getStartDay() {
		return DayOfWeek.MONDAY;
	}

	public DayOfWeek getEndDay() {
		return DayOfWeek.FRIDAY;
	}
}
