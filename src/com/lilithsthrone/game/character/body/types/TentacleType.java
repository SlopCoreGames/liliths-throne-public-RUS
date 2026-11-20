package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.3.8.9
 * @author Innoxia
 */
public class TentacleType {
	
	public static final AbstractTentacleType NONE = new AbstractTentacleType(
			null,
			Race.NONE,
			PenetrationGirth.THREE_AVERAGE,
			0f,
			"нет",
			"",
			"",
			"щупальце",
			"щупальца",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"",
			"",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"#IF(npc.getTentacleCount()==1) [npc.She] ахает, когда [npc.she] чувствует, как [npc.her] [npc.tentacle] сжимается и исчезает в [npc.her] теле.#ELSE [npc.She] ахает, когда [npc.she] чувствует, как [npc.her] [npc.tentacles] сжимается и исчезает в [npc.her] теле.#ENDIF<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(без щупалец)].",
			"[style.colourDisabled([npc.She] у [npc.do] нет никаких щупалец.)]",
			Util.newArrayListOfValues()) {
	};
	
	public static final AbstractTentacleType DEMON_COMMON = new AbstractTentacleType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.ONE_SLENDER,
			1f,
			"демонический",
			"",
			"",
			"щупальце",
			"щупальца",
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("закругленный"),
			Util.newArrayListOfValues("закругленный"),
			"#IF(npc.getTentacleCount()==1) Из [npc.her] спины прорастает демонический щупальце, стремительно увеличиваясь в размерах, пока не достигнет около [npc.tentacleLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует направление его движения, позволяя [npc.herHim] использовать его как третью конечность.<br/>[npc.Name] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp(бесовское щупальце)]#ELSE [style.boldDemon(демоническое щупальце)]#ENDIF, [npc.materialDescriptor] [npc.tentacleFullDescription(true)].#ELSE [npc.TentacleCount] демонические щупальца прорастают из [npc.her] спины, стремительно увеличиваясь в размерах, пока каждое из них не достигнет около [npc.tentacleLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует направление их движения, позволяя [npc.herHim] использовать их как дополнительные конечности.<br/>[npc.Name] теперь [npc.has] [npc.tentacleCount]#IF(npc.isShortStature()) [style.boldImp(бесовские щупальца)]#ELSE [style.boldDemon(демонические щупальца)]#ENDIF, [npc.materialDescriptor] [npc.tentacleFullDescription(true)].#ENDIF",
			"Growing out from [npc.her] back, [npc.sheHasFull]#IF(npc.getTentacleCount()==1) a spaded, [npc.tentacleColour(true)] #IF(npc.isShortStature())impish#ELSEdemonic#ENDIF tentacle, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use it to grip and hold objects.#ELSE [npc.tentacleCount] spaded, [npc.tentacleColour(true)] #IF(npc.isShortStature())impish#ELSEdemonic#ENDIF tentacles, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use them to grip and hold objects.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TAPERING_NONE)) {
	};
	
	public static final AbstractTentacleType LEG_DEMON_OCTOPUS = new AbstractTentacleType(
			BodyCoveringType.OCTOPUS_SKIN,
			Race.DEMON,
			PenetrationGirth.FOUR_GIRTHY,
			2.5f,
			"демонический-осьминог",
			"",
			"",
			"щупальце",
			"щупальца",
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("закругленный"),
			Util.newArrayListOfValues("закругленный"),
			"",
			"Вместо ног у [npc.sheHasFull] [npc.tentacleCount] [npc.tentacleColour(true)], осьминогоподобные щупальца, над которыми [npc.sheHasFull] имеет полный контроль, что позволяет [npc.herHim] использовать их, чтобы хватать и удерживать предметы.",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TAPERING_NONE)) {
	};
	

	private static List<AbstractTentacleType> allTentacleTypes;
	private static Map<AbstractTentacleType, String> tentacleToIdMap = new HashMap<>();
	private static Map<String, AbstractTentacleType> idToTentacleMap = new HashMap<>();
	
	static {
		allTentacleTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tentacle")) {
					try {
						AbstractTentacleType type = new AbstractTentacleType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTentacleTypes.add(type);
						tentacleToIdMap.put(type, id);
						idToTentacleMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tentacle")) {
					try {
						AbstractTentacleType type = new AbstractTentacleType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTentacleTypes.add(type);
						tentacleToIdMap.put(type, id);
						idToTentacleMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded tentacle types:
		
		Field[] fields = TentacleType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTentacleType.class.isAssignableFrom(f.getType())) {
				
				AbstractTentacleType ct;
				try {
					ct = ((AbstractTentacleType) f.get(null));

					tentacleToIdMap.put(ct, f.getName());
					idToTentacleMap.put(f.getName(), ct);
					
					allTentacleTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTentacleTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTentacleType getTentacleTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToTentacleMap.keySet());
		return idToTentacleMap.get(id);
	}
	
	public static String getIdFromTentacleType(AbstractTentacleType tentacleType) {
		return tentacleToIdMap.get(tentacleType);
	}
	
	public static List<AbstractTentacleType> getAllTentacleTypes() {
		return allTentacleTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTentacleType>> typesMap = new HashMap<>();
	
	public static List<AbstractTentacleType> getTentacleTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTentacleType> types = new ArrayList<>();
		for(AbstractTentacleType type : TentacleType.getAllTentacleTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			types.add(TentacleType.NONE);
		}
		typesMap.put(r, types);
		return types;
	}
	
	public static List<AbstractTentacleType> getTentacleTypesSuitableForTransformation(List<AbstractTentacleType> options) {
		if (!options.contains(TentacleType.NONE)) {
			return options;
		}
		
		List<AbstractTentacleType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(TentacleType.NONE);
		return duplicatedOptions;
	}
}
