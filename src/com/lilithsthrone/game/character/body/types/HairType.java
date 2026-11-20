package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.9.1
 * @author Innoxia
 */
public class HairType {

	public static AbstractHairType HUMAN = new AbstractHairType(BodyCoveringType.HAIR_HUMAN,
			Race.HUMAN,
			"человеческими",
			"волосы",
			"волосы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("мягкий", "женственная"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с человеческими волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour], [style.boldHuman(человеческие волосы)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], человеческие волосы",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType ANGEL = new AbstractHairType(BodyCoveringType.HAIR_ANGEL,
			Race.ANGEL,
			"ангельский",
			"волосы",
			"волосы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("шелковистые", "мягкий", "женственная"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с шелковистыми ангельскими волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour], [style.boldAngel(ангельские волосы)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], ангельские волосы",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};
	
	public static AbstractHairType DEMON = new AbstractHairType(BodyCoveringType.HAIR_DEMON,
			Race.DEMON,
			"демонический",
			"волосы",
			"волосы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("шелковистые", "мягкий", "женственная"),
			"#IF(npc.isShortStature())Трансформация длится лишь мгновения, оставляя [npc.herHim] с шелковистыми бесовскими волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldImp(бесовские волосы)].#ELSEТрансформация длится лишь мгновения, оставляя [npc.herHim] с шелковистыми демоническими волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour], [style.boldDemon(демонические волосы)].#ENDIF",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], #IF(npc.isShortStature())бесовские#ELSEдемонические#ENDIF волосы",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType DOG_MORPH = new AbstractHairType(BodyCoveringType.HAIR_CANINE_FUR,
			Race.DOG_MORPH,
			"коровьи",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с волосами, похожими на мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldDogMorph(собачья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], собачья шерсть",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};
	
	public static AbstractHairType WOLF_MORPH = new AbstractHairType(BodyCoveringType.HAIR_LYCAN_FUR, //TODO rename
			Race.WOLF_MORPH,
			"волчьи",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с волосами, похожими на мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldWolfMorph(волчья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], волчья шерсть",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
		@Override
		public boolean isNeckFluffRequiresGreater() {
			return true;
		}
		@Override
		public double getNeckFluffChance() {
			return 0.25f;
		}
	};

	public static AbstractHairType FOX_MORPH = new AbstractHairType(BodyCoveringType.HAIR_FOX_FUR,
			Race.FOX_MORPH,
			"лисий",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с волосами, похожими на мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldFoxMorph(лисья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], лисья шерсть",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
		@Override
		public boolean isNeckFluffRequiresGreater() {
			return true;
		}
		@Override
		public double getNeckFluffChance() {
			return 0.25f;
		}
	};

	public static AbstractHairType CAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_FELINE_FUR, //TODO change to cat
			Race.CAT_MORPH,
			"кошачьи",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с волосами, похожими на мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldCatMorph(кошачья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], кошачья шерсть",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	//TODO should be PANTHER
	public static AbstractHairType CAT_MORPH_SIDEFLUFF = new AbstractHairType(BodyCoveringType.HAIR_FELINE_FUR,
			Race.CAT_MORPH,
			"кошка (боковая пушистость)",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с волосами, похожими на мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldCatMorph(кошачья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], кошачья шерсть, дополненная мягким, пушистым мехом по бокам лица [npc.her]",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType COW_MORPH = new AbstractHairType(BodyCoveringType.HAIR_BOVINE_FUR, //TODO change to cow
			Race.COW_MORPH,
			"корова",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с волосами, похожими на мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldCowMorph(коровья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], коровьи волосы",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType ALLIGATOR_MORPH = new AbstractHairType(BodyCoveringType.HAIR_SCALES_ALLIGATOR, //TODO change to hair
			Race.ALLIGATOR_MORPH,
			"аллигатора",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("жесткими"),
			Util.newArrayListOfValues("жесткими"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с грубыми волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldAlligatorMorph(аллигаторова шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], грубая аллигаторова шерсть",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType SQUIRREL_MORPH = new AbstractHairType(BodyCoveringType.HAIR_SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			"беличьи",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с мехоподобными волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldSquirrelMorph(беличья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], беличьи волосы",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType RAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_RAT_FUR,
			Race.RAT_MORPH,
			"крыса",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с мехоподобными волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldRatMorph(крысиная шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], крысиные волосы",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType RABBIT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_RABBIT_FUR,
			Race.RABBIT_MORPH,
			"кроличьи",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с мехоподобными волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldRabbitMorph(кроличий мех)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], кроличьи волосы",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType BAT_MORPH = new AbstractHairType(BodyCoveringType.HAIR_BAT_FUR,
			Race.BAT_MORPH,
			"летучая мышь",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится лишь мгновения, оставляя [npc.herHim] с мехоподобными волосами.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldBatMorph(летучая мышь шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], волосы, похожие на перепонки летучей мыши",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType HORSE_MORPH = new AbstractHairType(BodyCoveringType.HAIR_HORSE_HAIR,
			Race.HORSE_MORPH,
			"лошади",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится всего мгновение, оставляя [npc.herHim] с волосами, напоминающими мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldHorseMorph(лошадиная грива)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], волосы, похожие на конскую гриву",
			Util.newArrayListOfValues(
					BodyPartTag.HAIR_HANDLES_IN_SEX,
					BodyPartTag.HAIR_NATURAL_MANE)) {
	};

	public static AbstractHairType REINDEER_MORPH = new AbstractHairType(BodyCoveringType.HAIR_REINDEER_FUR,
			Race.REINDEER_MORPH,
			"оленьей шерсти",
			"волосы",
			"волосы",
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			Util.newArrayListOfValues("пушистая", "похожими на мех"),
			"Трансформация длится всего мгновение, оставляя [npc.herHim] с волосами, напоминающими мех.<br/>[npc.Name] теперь [npc.has] [npc.hairColour] [style.boldReindeerMorph(оленья шерсть)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], волосы, похожие на оленью шерсть",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
	};

	public static AbstractHairType HARPY = new AbstractHairType(BodyCoveringType.HAIR_HARPY,
			Race.HARPY,
			"гарпия",
			"головное перо",
			"головные перья",
			Util.newArrayListOfValues("прекрасные", "птичьи"),
			Util.newArrayListOfValues("прекрасные", "птичьи"),
			"Трансформация длится всего мгновение, оставляя [npc.herHim] вместо волос целый веер перьев.<br/>[npc.Name] теперь [npc.has] [npc.hairColour], птичьи [style.boldHarpy(перья гарпии)].",
			"[npc.SheHasFull] [npc.hairDeterminer] [npc.hairLength], [npc.hairColour(true)], птичьи перья гарпии",
			Util.newArrayListOfValues(BodyPartTag.HAIR_HANDLES_IN_SEX)) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
		@Override
		public String getDeterminer(GameCharacter gc) {
			return "пучок";
		}
	};

	private static List<AbstractHairType> allHairTypes;
	private static Map<AbstractHairType, String> hairToIdMap = new HashMap<>();
	private static Map<String, AbstractHairType> idToHairMap = new HashMap<>();
	
	static {
		allHairTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("hair")) {
					try {
						AbstractHairType type = new AbstractHairType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHairTypes.add(type);
						hairToIdMap.put(type, id);
						idToHairMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("hair")) {
					try {
						AbstractHairType type = new AbstractHairType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHairTypes.add(type);
						hairToIdMap.put(type, id);
						idToHairMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		// Add in hard-coded hair types:
		
		Field[] fields = HairType.class.getFields();
		
		for(Field f : fields){
			if (AbstractHairType.class.isAssignableFrom(f.getType())) {
				
				AbstractHairType ct;
				try {
					ct = ((AbstractHairType) f.get(null));

					hairToIdMap.put(ct, f.getName());
					idToHairMap.put(f.getName(), ct);
					
					allHairTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		Collections.sort(allHairTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractHairType getHairTypeFromId(String id) {
		if(id.equals("IMP") || id.equals("DEMON_COMMON")) {
			return HairType.DEMON;
		}
		if(id.equals("LYCAN")) {
			return HairType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToHairMap.keySet());
		return idToHairMap.get(id);
	}
	
	public static String getIdFromHairType(AbstractHairType hairType) {
		return hairToIdMap.get(hairType);
	}
	
	public static List<AbstractHairType> getAllHairTypes() {
		return allHairTypes;
	}
	
	private static Map<AbstractRace, List<AbstractHairType>> typesMap = new HashMap<>();
	
	public static List<AbstractHairType> getHairTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractHairType> types = new ArrayList<>();
		for(AbstractHairType type : HairType.getAllHairTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}