package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.2
 * @author Innoxia
 */
public class FluidType {
	
	// Cum:
	
	public static AbstractFluidType CUM_HUMAN = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};
	
	public static AbstractFluidType CUM_DOLL = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.DOLL,
			Util.newArrayListOfValues("кукольный-"),
			Util.newArrayListOfValues("кукольный-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY,
					FluidModifier.MINERAL_OIL)) {
		@Override
		public float getValueModifier() {
			return 0.25f;
		}
	};

	public static AbstractFluidType CUM_ANGEL = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.ANGEL,
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_DEMON = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.DEMON,
			Util.newArrayListOfValues("демонический-", "демонический-"),
			Util.newArrayListOfValues("демонический-", "демонический-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_DOG_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.DOG_MORPH,
			Util.newArrayListOfValues("собачий-", "пёсий-"),
			Util.newArrayListOfValues("сучья-", "пёсий-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_WOLF_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.WOLF_MORPH,
			Util.newArrayListOfValues("волчья-", "люпинная-"),
			Util.newArrayListOfValues("волчья-", "люпинная-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_FOX_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.FOX_MORPH,
			Util.newArrayListOfValues("лисья-", "вульпинная-"),
			Util.newArrayListOfValues("лисичья-", "вульпинная-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_CAT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.CAT_MORPH,
			Util.newArrayListOfValues("кошачья-", "кошачий-"),
			Util.newArrayListOfValues("кошачья-", "кошачий-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_SQUIRREL_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.SQUIRREL_MORPH,
			Util.newArrayListOfValues("беличий-", "грызуний-"),
			Util.newArrayListOfValues("беличий-", "грызуний-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_RAT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.RAT_MORPH,
			Util.newArrayListOfValues("крысиный-", "грызуний-"),
			Util.newArrayListOfValues("крысиный-", "грызуний-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_RABBIT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.RABBIT_MORPH,
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_BAT_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.BAT_MORPH,
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_ALLIGATOR_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.ALLIGATOR_MORPH,
			Util.newArrayListOfValues("аллигаторийский-", "рептилоидный-"),
			Util.newArrayListOfValues("аллигаторийский-", "рептилоидный-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_HORSE_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.HORSE_MORPH,
			Util.newArrayListOfValues("жеребцовый-", "лошадиный-", "конский-"),
			Util.newArrayListOfValues("кобылий-", "лошадиный-", "конский-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_REINDEER_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.REINDEER_MORPH,
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_COW_MORPH = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.COW_MORPH,
			Util.newArrayListOfValues("бычий-", "бычий-"),
			Util.newArrayListOfValues("коровий-", "бычий-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.MUSKY,
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType CUM_HARPY = new AbstractFluidType(FluidTypeBase.CUM,
			FluidFlavour.CUM,
			Race.HARPY,
			Util.newArrayListOfValues("гарпии-", "птичий-"),
			Util.newArrayListOfValues("гарпии-", "птичий-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.STICKY,
					FluidModifier.SLIMY)) {
	};

	// Girl cum:
	
	public static AbstractFluidType GIRL_CUM_HUMAN = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};
	
	public static AbstractFluidType GIRL_CUM_DOLL = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.DOLL,
			Util.newArrayListOfValues("кукольный-"),
			Util.newArrayListOfValues("кукольный-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY,
					FluidModifier.MINERAL_OIL)) {
		@Override
		public float getValueModifier() {
			return 0.25f;
		}
	};

	public static AbstractFluidType GIRL_CUM_ANGEL = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_DEMON = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.DEMON,
			null,
			null,
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_DOG_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues("пёсьи"),
			Util.newArrayListOfValues("пёсьи"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_WOLF_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues("волчий"),
			Util.newArrayListOfValues("волчий"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_FOX_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues("лисьи"),
			Util.newArrayListOfValues("лисьи"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_CAT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("кошачьими"),
			Util.newArrayListOfValues("кошачьими"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_SQUIRREL_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_RAT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("крыса"),
			Util.newArrayListOfValues("крыса"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_RABBIT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("кроличьи"),
			Util.newArrayListOfValues("кроличьи"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_BAT_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("летучая мышь"),
			Util.newArrayListOfValues("летучая мышь"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_ALLIGATOR_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues("аллигатора"),
			Util.newArrayListOfValues("аллигатора"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_HORSE_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_REINDEER_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues("оленьей шерсти"),
			Util.newArrayListOfValues("оленьей шерсти"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_COW_MORPH = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues("бычий"),
			Util.newArrayListOfValues("бычий"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	public static AbstractFluidType GIRL_CUM_HARPY = new AbstractFluidType(FluidTypeBase.GIRLCUM,
			FluidFlavour.GIRL_CUM,
			Race.HARPY,
			null,
			null,
			Util.newArrayListOfValues("птичий"),
			Util.newArrayListOfValues("птичий"),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY)) {
	};

	// Milks:
	
	public static AbstractFluidType MILK_HUMAN = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues()) {
	};
	
	public static AbstractFluidType MILK_DOLL = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.DOLL,
			Util.newArrayListOfValues("кукольный-"),
			Util.newArrayListOfValues("кукольный-"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(
					FluidModifier.SLIMY,
					FluidModifier.MINERAL_OIL)) {
		@Override
		public float getValueModifier() {
			return 0.25f;
		}
	};

	public static AbstractFluidType MILK_ANGEL = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_COW_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues("бычий"),
			Util.newArrayListOfValues("бычий"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_DEMON = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.DEMON,
			null,
			null,
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_DOG_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues("пёсьи"),
			Util.newArrayListOfValues("пёсьи"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_WOLF_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues("волчий"),
			Util.newArrayListOfValues("волчий"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_FOX_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues("лисьи"),
			Util.newArrayListOfValues("лисьи"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_CAT_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("кошачьими"),
			Util.newArrayListOfValues("кошачьими"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_SQUIRREL_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_RAT_MORPH = new AbstractFluidType(FluidTypeBase.MILK, // I don't get it. Everyone loves rats, but they don't wanna drink the rats' milk?
			FluidFlavour.MILK,
			Race.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("крыса"),
			Util.newArrayListOfValues("крыса"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_RABBIT_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("кроличьи"),
			Util.newArrayListOfValues("кроличьи"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_BAT_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("летучая мышь"),
			Util.newArrayListOfValues("летучая мышь"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_ALLIGATOR_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues("аллигатора"),
			Util.newArrayListOfValues("аллигатора"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_HORSE_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_REINDEER_MORPH = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues("оленьей шерсти"),
			Util.newArrayListOfValues("оленьей шерсти"),
			Util.newArrayListOfValues()) {
	};

	public static AbstractFluidType MILK_HARPY = new AbstractFluidType(FluidTypeBase.MILK,
			FluidFlavour.MILK,
			Race.HARPY,
			null,
			null,
			Util.newArrayListOfValues("птичий"),
			Util.newArrayListOfValues("птичий"),
			Util.newArrayListOfValues()) {
	};
	
	
	private static List<AbstractFluidType> allFluidTypes;
	private static Map<AbstractFluidType, String> fluidToIdMap = new HashMap<>();
	private static Map<String, AbstractFluidType> idToFluidMap = new HashMap<>();
	
	static {
		allFluidTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("fluid")) {
					try {
						AbstractFluidType type = new AbstractFluidType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFluidTypes.add(type);
						fluidToIdMap.put(type, id);
						idToFluidMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("fluid")) {
					try {
						AbstractFluidType type = new AbstractFluidType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFluidTypes.add(type);
						fluidToIdMap.put(type, id);
						idToFluidMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded fluid types:
		
		Field[] fields = FluidType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFluidType.class.isAssignableFrom(f.getType())) {
				
				AbstractFluidType ct;
				try {
					ct = ((AbstractFluidType) f.get(null));

					fluidToIdMap.put(ct, f.getName());
					idToFluidMap.put(f.getName(), ct);
					
					allFluidTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractFluidType getFluidTypeFromId(String id) {
		if(id.equals("CUM_IMP")) {
			id = "CUM_DEMON";
			
		} else if(id.equals("GIRL_CUM_IMP")) {
			id = "GIRL_CUM_DEMON";
			
		} else if(id.equals("MILK_IMP")) {
			id = "MILK_DEMON";
			
		} else if(id.equals("MILK_DEMON_COMMON")) {
			id = "MILK_DEMON";
		}
		
		id = Util.getClosestStringMatch(id, idToFluidMap.keySet());
		return idToFluidMap.get(id);
	}
	
	public static String getIdFromFluidType(AbstractFluidType fluidType) {
		return fluidToIdMap.get(fluidType);
	}
	
	public static List<AbstractFluidType> getAllFluidTypes() {
		return allFluidTypes;
	}
	
	private static Map<AbstractRace, List<AbstractFluidType>> typesMap = new HashMap<>();
	public static List<AbstractFluidType> getFluidTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractFluidType> types = new ArrayList<>();
		for(AbstractFluidType type : FluidType.getAllFluidTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}