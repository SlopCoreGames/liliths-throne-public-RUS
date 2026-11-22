package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class AssType {
	
	public static AbstractAssType HUMAN = new AbstractAssType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			AnusType.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldHuman(человеком)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] человек, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType ANGEL = new AbstractAssType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			AnusType.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues("ангельский", "идеальный"),
			"[npc.She] теперь [npc.has] [style.boldAngel(ангельским)] существом, [npc.assholeFullDescription].",
			"[npc.SheHasFull] ангельское существо, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType DEMON_COMMON = new AbstractAssType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			AnusType.DEMON_COMMON,
			null,
			null,
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический", "идеальный"),
			"[npc.She] теперь [npc.has] [style.boldDemon(демоническим)] существом, [npc.assholeFullDescription].",
			"[npc.SheHasFull] демоническое существо, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType DOG_MORPH = new AbstractAssType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			AnusType.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldDogMorph(собачьим)] существом, [npc.assholeFullDescription].",
			"[npc.SheHasFull] собачье существо, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType COW_MORPH = new AbstractAssType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			AnusType.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldCowMorph(бычьим)] существом, [npc.assholeFullDescription].",
			"[npc.SheHasFull] бычье существо, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType SQUIRREL_MORPH = new AbstractAssType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			AnusType.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldSquirrelMorph(беличья)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] беличья, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType RAT_MORPH = new AbstractAssType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			AnusType.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldRatMorph(крысиная)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] крысиная, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType RABBIT_MORPH = new AbstractAssType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			AnusType.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldRabbitMorph(кроличья)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] кроличья, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType BAT_MORPH = new AbstractAssType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			AnusType.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldBatMorph(летучая мышь)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] похожая на летучую мышь, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType ALLIGATOR_MORPH = new AbstractAssType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			AnusType.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldGatorMorph(аллигаторовая)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] аллигаторовая, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType WOLF_MORPH = new AbstractAssType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			AnusType.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldWolfMorph(волчья)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] волчья, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType FOX_MORPH = new AbstractAssType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			AnusType.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldFoxMorph(лисья)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] лисья, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType CAT_MORPH = new AbstractAssType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			AnusType.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldCatMorph(кошачья)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] кошачья, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType HORSE_MORPH = new AbstractAssType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			AnusType.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldHorseMorph(лошадиная)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] лошадиная, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType REINDEER_MORPH = new AbstractAssType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			AnusType.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldReindeerMorph(оленья)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] оленья, [npc.anusFullDescription(true)]"){
	};
	
	public static AbstractAssType HARPY = new AbstractAssType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			AnusType.HARPY,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] теперь [npc.has] [style.boldHarpy(птицеподобной)], [npc.assholeFullDescription].",
			"[npc.SheHasFull] птицеподобная, [npc.anusFullDescription(true)]"){
	};
	
	
	private static List<AbstractAssType> allAssTypes;
	private static Map<AbstractAssType, String> assToIdMap = new HashMap<>();
	private static Map<String, AbstractAssType> idToAssMap = new HashMap<>();
	
	static {
		allAssTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ass")) {
					try {
						AbstractAssType type = new AbstractAssType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAssTypes.add(type);
						assToIdMap.put(type, id);
						idToAssMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ass")) {
					try {
						AbstractAssType type = new AbstractAssType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allAssTypes.add(type);
						assToIdMap.put(type, id);
						idToAssMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded ass types:
		
		Field[] fields = AssType.class.getFields();
		
		for(Field f : fields){
			if (AbstractAssType.class.isAssignableFrom(f.getType())) {
				
				AbstractAssType ct;
				try {
					ct = ((AbstractAssType) f.get(null));

					assToIdMap.put(ct, f.getName());
					idToAssMap.put(f.getName(), ct);
					
					allAssTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		Collections.sort(allAssTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractAssType getAssTypeFromId(String id) {
		if(id.equals("IMP")) {
			return AssType.DEMON_COMMON;
		}
		
		id = Util.getClosestStringMatch(id, idToAssMap.keySet());
		return idToAssMap.get(id);
	}
	
	public static String getIdFromAssType(AbstractAssType assType) {
		return assToIdMap.get(assType);
	}
	
	public static List<AbstractAssType> getAllAssTypes() {
		return allAssTypes;
	}
	
	private static Map<AbstractRace, List<AbstractAssType>> typesMap = new HashMap<>();
	public static List<AbstractAssType> getAssTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractAssType> types = new ArrayList<>();
		for(AbstractAssType type : AssType.getAllAssTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}