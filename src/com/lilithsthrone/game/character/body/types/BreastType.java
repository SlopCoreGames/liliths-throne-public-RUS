package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class BreastType {

	// Only used for when lacking crotch breasts:
	public static AbstractBreastType NONE = new AbstractBreastType(BodyCoveringType.HUMAN,
			Race.NONE,
			NippleType.HUMAN,
			FluidType.MILK_HUMAN,
			"грудь",
			"груди",
			"[npc.She] больше не [npc.has] [style.boldShrink([npc.crotchBoobs])]!",
			""){
	};
	
	public static AbstractBreastType HUMAN = new AbstractBreastType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			NippleType.HUMAN,
			FluidType.MILK_HUMAN,
			"[npc.She] теперь [npc.has] [style.boldHuman(человеческими)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldHuman(человеческое молоко)].",
			"При каждом [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] человека, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldHuman(человеческими)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldHuman(человеческое молоко)].",
			"При каждом [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] человека, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType ANGEL = new AbstractBreastType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			NippleType.ANGEL,
			FluidType.MILK_ANGEL,
			"[npc.She] теперь [npc.has] [style.boldAngel(ангельскими)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldAngel(ангельское молоко)].",
			"При каждом [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] ангельских, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldAngel(ангельскими)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldAngel(ангельское молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] ангельских, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType DEMON_COMMON = new AbstractBreastType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			NippleType.DEMON,
			FluidType.MILK_DEMON,
			"[npc.She] теперь [npc.has] [style.boldDemon(демонических)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldDemon(демоническое молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] демонических, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldDemon(демонических)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldDemon(демоническое молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] демонических, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType DOG_MORPH = new AbstractBreastType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			NippleType.DOG_MORPH,
			FluidType.MILK_DOG_MORPH, // Emergency backup supply. We're on the dog's milk.
			"[npc.She] теперь [npc.has] [style.boldDogMorph(собачьих)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldDogMorph(собачье молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] собачьих, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldDogMorph(собачьих)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldDogMorph(собачье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] собачьих, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType WOLF_MORPH = new AbstractBreastType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			NippleType.WOLF_MORPH,
			FluidType.MILK_WOLF_MORPH,
			"Теперь [npc.She] [npc.has] [style.boldWolfMorph(волчьи)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldWolfMorph(волчье молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] волчьих, [npc.nipplesFullDescription].",
			"Теперь [npc.She] [npc.has] [style.boldWolfMorph(волчьи)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldWolfMorph(волчье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] волчьих, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType FOX_MORPH = new AbstractBreastType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			NippleType.FOX_MORPH,
			FluidType.MILK_FOX_MORPH,
			"Теперь [npc.She] [npc.has] [style.boldFoxMorph(лисьи)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldFoxMorph(лисье молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] лисьих, [npc.nipplesFullDescription].",
			"Теперь [npc.She] [npc.has] [style.boldFoxMorph(лисьи)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldFoxMorph(лисье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] лисьих, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType COW_MORPH = new AbstractBreastType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			NippleType.COW_MORPH,
			FluidType.MILK_COW_MORPH,
			"Теперь [npc.She] [npc.has] [style.boldCowMorph(коровьи)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldCowMorph(коровье молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] коровьи, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldCowMorph(коровьи)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldCowMorph(коровье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] коровьи, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType CAT_MORPH = new AbstractBreastType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			NippleType.CAT_MORPH,
			FluidType.MILK_CAT_MORPH,
			"[npc.She] теперь [npc.has] [style.boldCatMorph(кошачьи)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldCatMorph(кошачье молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] кошачьи, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldCatMorph(кошачьи)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldCatMorph(кошачье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] кошачьи, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType SQUIRREL_MORPH = new AbstractBreastType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			NippleType.SQUIRREL_MORPH,
			FluidType.MILK_SQUIRREL_MORPH,
			"[npc.She] теперь [npc.has] [style.boldSquirrelMorph(беличьи)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет производить [style.boldSquirrelMorph(беличье молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] беличьи, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldSquirrelMorph(беличьи)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldSquirrelMorph(беличье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] беличьи, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType RAT_MORPH = new AbstractBreastType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			NippleType.RAT_MORPH,
			FluidType.MILK_RAT_MORPH, // Rats?! I'm outraged! You promised me dog or higher!
			"[npc.She] теперь [npc.has] [style.boldRatMorph(крысиные)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldRatMorph(крысиное молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] крысиные, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldRatMorph(крысиные)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldRatMorph(крысиное молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] крысиные, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType RABBIT_MORPH = new AbstractBreastType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			NippleType.RABBIT_MORPH,
			FluidType.MILK_RABBIT_MORPH,
			"[npc.She] теперь [npc.has] [style.boldRabbitMorph(кроличьи)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldRabbitMorph(кроличий молока)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] кроличьи, [npc.nipplesFullDescription].",
			"Теперь [npc.She] [npc.has] [style.boldRabbitMorph(кроличьи)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldRabbitMorph(кроличий молока)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] кроличьи, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType BAT_MORPH = new AbstractBreastType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			NippleType.BAT_MORPH,
			FluidType.MILK_BAT_MORPH,
			"Теперь [npc.She] [npc.has] [style.boldBatMorph(летучие)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldBatMorph(летучее молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] летучие, [npc.nipplesFullDescription].",
			"Теперь [npc.She] [npc.has] [style.boldBatMorph(летучие)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldBatMorph(летучее молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] летучие, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType ALLIGATOR_MORPH = new AbstractBreastType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			NippleType.ALLIGATOR_MORPH,
			FluidType.MILK_ALLIGATOR_MORPH, // Hmm
			"Теперь [npc.She] [npc.has] [style.boldGatorMorph(рептилоидные)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldGatorMorph(аллигаторово молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] рептилоидные, [npc.nipplesFullDescription].",
			"Теперь [npc.She] [npc.has] [style.boldGatorMorph(рептилоидными)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldGatorMorph(аллигаторово молоко)].",
			"При каждом [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] рептилоидными, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType HORSE_MORPH = new AbstractBreastType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			NippleType.HORSE_MORPH,
			FluidType.MILK_HORSE_MORPH,
			"Теперь [npc.She] [npc.has] [style.boldHorseMorph(лошадиными)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldHorseMorph(лошадиное молоко)].",
			"При каждом [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] лошадиными, [npc.nipplesFullDescription].",
			"Теперь [npc.She] [npc.has] [style.boldHorseMorph(лошадиными)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldHorseMorph(лошадиное молоко)].",
			"При каждом [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] лошадиными, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType REINDEER_MORPH = new AbstractBreastType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			NippleType.REINDEER_MORPH,
			FluidType.MILK_REINDEER_MORPH,
			"Теперь [npc.She] [npc.has] [style.boldReindeerMorph(оленьими)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldReindeerMorph(оленье молоко)].",
			"При каждом [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] оленьими, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldReindeerMorph(оленьего типа)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldReindeerMorph(оленье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] оленьего типа, [npc.crotchNipplesFullDescription]."){
	};
	
	public static AbstractBreastType HARPY = new AbstractBreastType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			NippleType.HARPY,
			FluidType.MILK_HARPY, // hmm
			"[npc.She] теперь [npc.has] [style.boldHarpy(птичьего типа)], [npc.nipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldHarpy(гарпийье молоко)].",
			"На каждой из [npc.her] [npc.breastSize] [npc.breasts], [npc.she] [npc.has] [npc.nipplesPerBreast] птичьего типа, [npc.nipplesFullDescription].",
			"[npc.She] теперь [npc.has] [style.boldHarpy(птичьего типа)], [npc.crotchNipplesFullDescription], а во время лактации [npc.she] будет вырабатывать [style.boldHarpy(гарпийье молоко)].",
			"На каждой из [npc.her] [npc.crotchBoobSize] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchNipplesPerBreast] птичьего типа, [npc.crotchNipplesFullDescription]."){
	};
	
	
	private static List<AbstractBreastType> allBreastTypes;
	private static Map<AbstractBreastType, String> breastToIdMap = new HashMap<>();
	private static Map<String, AbstractBreastType> idToBreastMap = new HashMap<>();
	
	static {
		allBreastTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("breast")) {
					try {
						AbstractBreastType type = new AbstractBreastType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allBreastTypes.add(type);
						breastToIdMap.put(type, id);
						idToBreastMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("breast")) {
					try {
						AbstractBreastType type = new AbstractBreastType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allBreastTypes.add(type);
						breastToIdMap.put(type, id);
						idToBreastMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded breast types:
		
		Field[] fields = BreastType.class.getFields();
		
		for(Field f : fields){
			if (AbstractBreastType.class.isAssignableFrom(f.getType())) {
				
				AbstractBreastType ct;
				try {
					ct = ((AbstractBreastType) f.get(null));

					breastToIdMap.put(ct, f.getName());
					idToBreastMap.put(f.getName(), ct);
					
					allBreastTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allBreastTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractBreastType getBreastTypeFromId(String id) {
		if(id.equals("IMP")) {
			return BreastType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return BreastType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToBreastMap.keySet());
		return idToBreastMap.get(id);
	}
	
	public static String getIdFromBreastType(AbstractBreastType breastType) {
		return breastToIdMap.get(breastType);
	}
	
	public static List<AbstractBreastType> getAllBreastTypes() {
		return allBreastTypes;
	}
	
	private static Map<AbstractRace, List<AbstractBreastType>> typesMap = new HashMap<>();
	public static List<AbstractBreastType> getBreastTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractBreastType> types = new ArrayList<>();
		for(AbstractBreastType type : BreastType.getAllBreastTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}