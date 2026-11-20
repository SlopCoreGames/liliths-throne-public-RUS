package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class TongueType {
	
	public static AbstractTongueType HUMAN = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HUMAN,
			3,
			"язык",
			"языки",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType ANGEL = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.ANGEL,
			3,
			"язык",
			"языки",
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues("ангельский"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType DEMON_COMMON = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.DEMON,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType DOG_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.DOG_MORPH,
			8,
			"язык",
			"языки",
			Util.newArrayListOfValues("собачьи"),
			Util.newArrayListOfValues("собачьи"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)], собачий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.WIDE,
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType WOLF_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.WOLF_MORPH,
			8,
			"язык",
			"языки",
			Util.newArrayListOfValues("волчьими"),
			Util.newArrayListOfValues("волчьими"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)], волчий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.WIDE,
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType FOX_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.FOX_MORPH,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("лисоподобные"),
			Util.newArrayListOfValues("лисоподобные"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)], лисий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType CAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.CAT_MORPH,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("кошачьими"),
			Util.newArrayListOfValues("кошачьими"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)], кошачий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType COW_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.COW_MORPH,
			12,
			"язык",
			"языки",
			Util.newArrayListOfValues("коровьей формы"),
			Util.newArrayListOfValues("коровьей формы"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)], коровьи [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType ALLIGATOR_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.ALLIGATOR_MORPH,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("аллигатороподобного"),
			Util.newArrayListOfValues("аллигатороподобного"),
			" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)], аллигаторовий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType HORSE_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HORSE_MORPH,
			8,
			"язык",
			"языки",
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues("лошадиный"),
			" Во рту [npc.Her] [npc.a_tongueLength], [npc.tongueColour(true)], лошадиный [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType REINDEER_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.REINDEER_MORPH,
			8,
			"язык",
			"языки",
			Util.newArrayListOfValues("похожих на оленьи"),
			Util.newArrayListOfValues("похожих на оленьи"),
			" Во рту [npc.Her] [npc.a_tongueLength], [npc.tongueColour(true)], олений [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.STRONG)) {
	};
	
	public static AbstractTongueType HARPY = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.HARPY,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("птичьи"),
			Util.newArrayListOfValues("птичьи"),
			" Во рту [npc.Her] [npc.a_tongueLength], [npc.tongueColour(true)], птичий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues(
					TongueModifier.FLAT)) {
	};
	
	public static AbstractTongueType SQUIRREL_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.SQUIRREL_MORPH,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues("беличьи"),
			" Во рту [npc.Her] [npc.a_tongueLength], [npc.tongueColour(true)], беличий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType RAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.RAT_MORPH,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("крысиные"),
			Util.newArrayListOfValues("крысиные"),
			" Во рту [npc.Her] [npc.a_tongueLength], [npc.tongueColour(true)], крысиный [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType RABBIT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.RABBIT_MORPH,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("кроличьими"),
			Util.newArrayListOfValues("кроличьими"),
			" Во рту [npc.Her] [npc.a_tongueLength], [npc.tongueColour(true)], заячий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractTongueType BAT_MORPH = new AbstractTongueType(BodyCoveringType.TONGUE,
			Race.BAT_MORPH,
			6,
			"язык",
			"языки",
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			" Во рту [npc.Her] [npc.a_tongueLength], [npc.tongueColour(true)], летучий [npc.tongue]#IF(npc.isPiercedTongue()), проколотый#ENDIF.",
			Util.newArrayListOfValues()) {
	};
	
	private static List<AbstractTongueType> allTongueTypes;
	private static Map<AbstractTongueType, String> tongueToIdMap = new HashMap<>();
	private static Map<String, AbstractTongueType> idToTongueMap = new HashMap<>();
	
	static {
		allTongueTypes = new ArrayList<>();
		
		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tongue")) {
					try {
						AbstractTongueType type = new AbstractTongueType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTongueTypes.add(type);
						tongueToIdMap.put(type, id);
						idToTongueMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tongue")) {
					try {
						AbstractTongueType type = new AbstractTongueType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTongueTypes.add(type);
						tongueToIdMap.put(type, id);
						idToTongueMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded tongue types:
		
		Field[] fields = TongueType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTongueType.class.isAssignableFrom(f.getType())) {
				
				AbstractTongueType ct;
				try {
					ct = ((AbstractTongueType) f.get(null));

					tongueToIdMap.put(ct, f.getName());
					idToTongueMap.put(f.getName(), ct);
					
					allTongueTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTongueTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTongueType getTongueTypeFromId(String id) {
		if(id.equals("IMP")) {
			return TongueType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return TongueType.WOLF_MORPH;
		}
		if(id.equals("TENGU")) {
			return TongueType.HARPY;
		}
		
		id = Util.getClosestStringMatch(id, idToTongueMap.keySet());
		return idToTongueMap.get(id);
	}
	
	public static String getIdFromTongueType(AbstractTongueType tongueType) {
		return tongueToIdMap.get(tongueType);
	}
	
	public static List<AbstractTongueType> getAllTongueTypes() {
		return allTongueTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTongueType>> typesMap = new HashMap<>();
	
	public static List<AbstractTongueType> getTongueTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTongueType> types = new ArrayList<>();
		for(AbstractTongueType type : TongueType.getAllTongueTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}