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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.8.8
 * @author Innoxia
 */
public class TorsoType {
	
	public static AbstractTorsoType HUMAN = new AbstractTorsoType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубоко вздыхает, когда зуд наконец утихает, оставляя [npc.her] торс, покрытый человеческой кожей.<br/>[npc.Name] теперь [npc.has] [style.boldHuman(человеческой)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType DEMON_COMMON = new AbstractTorsoType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубоко вздыхает, когда зуд наконец утихает, оставляя [npc.her] торс, покрытый#IF(npc.isShortStature()) бесовской#ELSE демонической#ENDIF кожей. Она гораздо нежнее обычной человеческой кожи, а цветовые тона по всему [npc.her] телу стали идеально сбалансированными, чтобы подчеркнуть [npc.her] фигуру.<br/>[npc.Name] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp(бесовской)]#ELSE [style.boldDemon(демонической)]#ENDIF, [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType ANGEL = new AbstractTorsoType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубоко вздыхает, когда зуд наконец утихает, оставляя [npc.her] торс покрытым ангельской кожей. Она гораздо нежнее обычной человеческой, а цветовые тона по всему [npc.her] телу стали идеально сбалансированными, чтобы подчеркнуть [npc.her] фигуру.<br/>[npc.Name] теперь [npc.has] [style.boldAngel(ангельская)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType COW_MORPH = new AbstractTorsoType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубоко вздыхает, когда зуд наконец утихает, оставляя [npc.her] торс покрытым короткой коровьей шерстью. [npc.Her] новая шерсть выглядит очень гладкой и помогает подчеркнуть [npc.her] фигуру, хотя на ощупь она немного грубовата.<br/>[npc.Name] теперь [npc.has] [style.boldCowMorph(коровья)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType DOG_MORPH = new AbstractTorsoType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубоко вздыхает, когда зуд наконец утихает, оставляя [npc.her] торс покрытым собачьим мехом. [npc.Her] новый мех повторяет изгибы [npc.her] фигуры, он довольно гладкий и приятный на ощупь.<br/>[npc.Name] теперь [npc.has] [style.boldDogMorph(собачья)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType WOLF_MORPH = new AbstractTorsoType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох, когда зуд наконец прекращается, оставляя [npc.her] торс покрытым волчьим мехом. [npc.Her] новый мех немного лохматый вокруг [npc.her] суставов и довольно густой.<br/>[npc.Name] теперь [npc.has] [style.boldWolfMorph(lupine)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};
	
	public static AbstractTorsoType FOX_MORPH = new AbstractTorsoType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох, когда зуд наконец прекращается, оставляя [npc.her] торс покрытым лисьим мехом. [npc.Her] новый мех немного лохматый вокруг [npc.her] суставов и довольно густой.</br>[npc.Name] теперь [npc.has] [style.boldFoxMorph(vulpine)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType CAT_MORPH = new AbstractTorsoType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох, когда зуд наконец прекращается, оставляя [npc.her] торс покрытым кошачьим мехом. [npc.Her] новый мех повторяет контуры [npc.her] фигуры, он невероятно гладкий и мягкий.<br/>[npc.Name] теперь [npc.has] [style.boldCatMorph(feline)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType SQUIRREL_MORPH = new AbstractTorsoType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец прекращается, оставляя [npc.her] торс покрытым беличьим мехом. [npc.Her] новый мех повторяет контуры [npc.her] фигуры, он невероятно гладкий и мягкий.<br/>[npc.Name] теперь [npc.has] [style.boldSquirrelMorph(беличья)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType RAT_MORPH = new AbstractTorsoType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец прекращается, оставляя [npc.her] торс покрытым крысиным мехом. [npc.Her] новый мех повторяет контуры [npc.her] фигуры, он немного грубый на ощупь.<br/>[npc.Name] теперь [npc.has] [style.boldRatMorph(крысиная)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType RABBIT_MORPH = new AbstractTorsoType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец прекращается, оставляя [npc.her] торс покрытым кроличьим мехом. [npc.Her] новый мех повторяет контуры [npc.her] фигуры, он невероятно гладкий и мягкий.<br/>[npc.Name] теперь [npc.has] [style.boldRabbitMorph(кроличья)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType BAT_MORPH = new AbstractTorsoType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец утихает, оставляя [npc.her] торс покрытым похожим на летучий мышиным мехом. [npc.Her] новый мех повторяет контуры [npc.her] фигуры; он довольно гладкий и приятный на ощупь.<br/>[npc.Name] теперь [npc.has] [style.boldBatMorph(похожий на летучую мышь)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType ALLIGATOR_MORPH = new AbstractTorsoType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец утихает, оставляя [npc.her] торс покрытым жесткой, перекрывающей друг друга чешуей. [npc.Her] новая чешуя повторяет контуры [npc.her] фигуры; хоть она и довольно твердая на ощупь, она очень гладкая, если проводить по ней в правильном направлении.<br/>[npc.Name] теперь [npc.has] [style.boldGatorMorph(рептилия)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
	};

	public static AbstractTorsoType HORSE_MORPH = new AbstractTorsoType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец утихает, оставляя [npc.her] торс покрытым короткой лошадиной шерстью. [npc.Her] новая шерсть выглядит очень лоснящейся и подчеркивает [npc.her] фигуру, хотя она немного грубовата на ощупь.<br/>[npc.Name] теперь [npc.has] [style.boldHorseMorph(лошадиный)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType REINDEER_MORPH = new AbstractTorsoType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец утихает, оставляя [npc.her] торс покрытым коротким, похожим на олений мехом. [npc.Her] новый мех выглядит очень гладким и выгодно подчеркивает [npc.her] фигуру, хотя на ощупь он немного жестковат.<br/>[npc.Name] теперь [npc.has] [style.boldReindeerMorph(олень)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
	};

	public static AbstractTorsoType HARPY = new AbstractTorsoType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Спустя всего несколько мгновений трансформация завершается, и [npc.she] [npc.verb(let)] глубокий вздох облегчения, когда зуд наконец утихает, оставляя [npc.her] торс покрытым прекрасным, переливающимся оперением. [npc.Her] новые перья повторяют изгибы [npc.her] фигуры, они невероятно гладкие и мягкие на ощупь.<br/>[npc.Name] теперь [npc.has] [style.boldHarpy(птица)], [npc.skinFullDescription].",
			"Торс [npc.Her] имеет [npc.a_femininity(женственный)] вид и является [npc.materialCompositionDescriptor] [npc.skinFullDescription(гладким)].") {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return true;
		}
	};
	
	
	private static List<AbstractTorsoType> allTorsoTypes;
	private static Map<AbstractTorsoType, String> torsoToIdMap = new HashMap<>();
	private static Map<String, AbstractTorsoType> idToTorsoMap = new HashMap<>();
	
	static {
		allTorsoTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("torso")) {
					try {
						AbstractTorsoType type = new AbstractTorsoType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTorsoTypes.add(type);
						torsoToIdMap.put(type, id);
						idToTorsoMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("torso")) {
					try {
						AbstractTorsoType type = new AbstractTorsoType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTorsoTypes.add(type);
						torsoToIdMap.put(type, id);
						idToTorsoMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded torso types:
		
		Field[] fields = TorsoType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTorsoType.class.isAssignableFrom(f.getType())) {
				
				AbstractTorsoType ct;
				try {
					ct = ((AbstractTorsoType) f.get(null));

					torsoToIdMap.put(ct, f.getName());
					idToTorsoMap.put(f.getName(), ct);
					
					allTorsoTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTorsoTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTorsoType getTorsoTypeFromId(String id) {
		Map<String, String> torsoTypeConverterMap = new HashMap<>();
		torsoTypeConverterMap.put("IMP", "DEMON_COMMON");
		torsoTypeConverterMap.put("CANINE_FUR", "DOG_MORPH");
		torsoTypeConverterMap.put("LYCAN_FUR", "LYCAN");
		torsoTypeConverterMap.put("LYCAN", "WOLF_MORPH");
		torsoTypeConverterMap.put("FELINE_FUR", "CAT_MORPH");
		torsoTypeConverterMap.put("SQUIRREL_FUR", "SQUIRREL_MORPH");
		torsoTypeConverterMap.put("HORSE_HAIR", "HORSE_MORPH");
		torsoTypeConverterMap.put("SLIME", "SLIME");
		torsoTypeConverterMap.put("FEATHERS", "HARPY");
		if(torsoTypeConverterMap.containsKey(id)) {
			id = torsoTypeConverterMap.get(id);
		}
		
		id = Util.getClosestStringMatch(id, idToTorsoMap.keySet());
		return idToTorsoMap.get(id);
	}
	
	public static String getIdFromTorsoType(AbstractTorsoType torsoType) {
		return torsoToIdMap.get(torsoType);
	}
	
	public static List<AbstractTorsoType> getAllTorsoTypes() {
		return allTorsoTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTorsoType>> typesMap = new HashMap<>();
	
	public static List<AbstractTorsoType> getTorsoTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTorsoType> types = new ArrayList<>();
		for(AbstractTorsoType type : TorsoType.getAllTorsoTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}