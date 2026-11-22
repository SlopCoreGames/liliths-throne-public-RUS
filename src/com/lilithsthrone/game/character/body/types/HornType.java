package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * 
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class HornType {
	
	// If any more horn types are added, check to see that the potion TFs still work. (5 types is currently the maximum.)
	
	public static final AbstractHornType NONE = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"нет",
			"рог",
			"рога",
			new ArrayList<>(),
			new ArrayList<>(),
			"<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(без рогов)].",
			"") {
	};

//	// Cows:
//	
//	public static final AbstractHornType BOVINE_CURVED = new AbstractHornType(
//			BodyCoveringType.HORN,
//			Race.COW_MORPH,
//			2,
//			"curved",
//			"horn",
//			"horns",
//			Util.newArrayListOfValues("curved", "bovine"),
//			Util.newArrayListOfValues("curved", "bovine", "smooth"),
//			"slightly-curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
//					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(curved #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
//			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], curved #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
//	};
//
//	public static final AbstractHornType BOVINE_STRAIGHT = new AbstractHornType(
//			BodyCoveringType.HORN,
//			Race.COW_MORPH,
//			2,
//			"straight",
//			"horn",
//			"horns",
//			Util.newArrayListOfValues("straight", "bovine"),
//			Util.newArrayListOfValues("straight", "bovine", "smooth"),
//			"sleek, straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF."
//					+ "<br/>[npc.Name] now [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(straight #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
//			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], straight #IFnpc.getTotalHorns()==1#THEN[npc.horn] grows#ELSE[npc.horns] grow#ENDIF out of the #IFnpc.getHornsPerRow()==1#THENmiddle#ELSEupper sides#ENDIF of [npc.her] forehead.") {
//	};

	// Reindeer:
	
	public static final AbstractHornType REINDEER_RACK = new AbstractHornType(
			BodyCoveringType.ANTLER,
			Race.REINDEER_MORPH,
			2,
			"разветвленные",
			"рог",
			"рога",
			Util.newArrayListOfValues("разветвленные", "оленьей шерсти"),
			Util.newArrayListOfValues("разветвленные", "оленьей шерсти"),
			"разветвленные, многозубчатые #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(похожие на оленьи #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], разветвленные #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THEN самой середины#ELSE верхних частей#ENDIF лба [npc.her].") {
	};
	
	// Horse:
	
	public static final AbstractHornType HORSE_STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.HORSE_MORPH,
			1,
			"единорожьи",
			"рог",
			"рога",
			Util.newArrayListOfValues("прямые", "закрученные", "единорожьи"),
			Util.newArrayListOfValues("прямые", "закрученные", "единорожьи"),
			"прямой, закрученный единорожий #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(единорожий #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], единорожий #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THENсередины#ELSEверхних сторон#ENDIF лба [npc.her].") {
	};

	// Generic:

	public static final AbstractHornType ANTLERS = new AbstractHornType(
			BodyCoveringType.ANTLER,
			Race.NONE,
			2,
			"разветвленный",
			"рог",
			"рога",
			Util.newArrayListOfValues("разветвленный"),
			Util.newArrayListOfValues("разветвленный"),
			" многозубчатый #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(разветвленный #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], разветвленный #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THENсередины#ELSEверхних сторон#ENDIF лба [npc.her].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType CURLED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"закрученный",
			"рог",
			"рога",
			Util.newArrayListOfValues("закрученный"),
			Util.newArrayListOfValues("закрученный", "гладкая"),
			"закрученный спиралью #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(закрученный #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], закрученные по кругу #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THENцентра#ELSEверхних частей#ENDIF лба [npc.her].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType SPIRAL = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"спиралевидные", //Rasen no Chikara
			"рог",
			"рога",
			Util.newArrayListOfValues("спиралевидные"),
			Util.newArrayListOfValues("спиралевидные", "гладкая"),
			"скрученные, спиралевидные #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(спиралевидные #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], спиралевидные #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THENцентра#ELSEверхних частей#ENDIF лба [npc.her].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType CURVED = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"изогнутые",
			"рог",
			"рога",
			Util.newArrayListOfValues("изогнутые"),
			Util.newArrayListOfValues("изогнутые", "гладкая"),
			"слегка изогнутые #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(изогнутые #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], изогнутые #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THENцентра#ELSEверхних частей#ENDIF лба [npc.her].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType SWEPT_BACK = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"откинутые назад",
			"рог",
			"рога",
			Util.newArrayListOfValues("откинутые назад"),
			Util.newArrayListOfValues("откинутые назад", "гладкая"),
			"гладкие #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF, отходящие назад и изгибающиеся над [npc.her] головой.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(загнутые назад #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], загнутые назад #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THENцентра#ELSEверхних частей#ENDIF лба [npc.her].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	public static final AbstractHornType STRAIGHT = new AbstractHornType(
			BodyCoveringType.HORN,
			Race.NONE,
			2,
			"прямые",
			"рог",
			"рога",
			Util.newArrayListOfValues("прямые"),
			Util.newArrayListOfValues("прямые", "гладкая"),
			"гладкие, прямые рога.<br/>[npc.Name] теперь [npc.has] [npc.hornsDeterminer] [style.boldTfGeneric(прямые #IFnpc.getTotalHorns()==1#THEN[npc.horn]#ELSE[npc.horns]#ENDIF)].",
			"[npc.HornsDeterminer] [npc.hornSize], [npc.hornColour(true)], прямые #IFnpc.getTotalHorns()==1#THEN[npc.horn] растет#ELSE[npc.horns] растут#ENDIF из #IFnpc.getHornsPerRow()==1#THENцентра#ELSEверхних частей#ENDIF лба [npc.her].") {
		@Override
		public boolean isGeneric() {
			return true;
		}
	};
	
	
	private static List<AbstractHornType> allHornTypes;
	private static Map<AbstractHornType, String> hornToIdMap = new HashMap<>();
	private static Map<String, AbstractHornType> idToHornMap = new HashMap<>();
	
	static {
		allHornTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("horn")) {
					try {
						AbstractHornType type = new AbstractHornType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHornTypes.add(type);
						hornToIdMap.put(type, id);
						idToHornMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("horn")) {
					try {
						AbstractHornType type = new AbstractHornType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allHornTypes.add(type);
						hornToIdMap.put(type, id);
						idToHornMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded horn types:
		
		Field[] fields = HornType.class.getFields();
		
		for(Field f : fields){
			if (AbstractHornType.class.isAssignableFrom(f.getType())) {
				
				AbstractHornType ct;
				try {
					ct = ((AbstractHornType) f.get(null));

					hornToIdMap.put(ct, f.getName());
					idToHornMap.put(f.getName(), ct);
					
					allHornTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allHornTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractHornType getHornTypeFromId(String id) {
		if(id.equals("BOVINE_CURVED")) {
			return CURVED;
		} else if(id.equals("BOVINE_STRAIGHT")) {
			return STRAIGHT;
		}
		id = Util.getClosestStringMatch(id, idToHornMap.keySet());
		return idToHornMap.get(id);
	}
	
	public static String getIdFromHornType(AbstractHornType hornType) {
		return hornToIdMap.get(hornType);
	}
	
	public static List<AbstractHornType> getAllHornTypes() {
		return allHornTypes;
	}
	
	private static Map<AbstractRace, List<AbstractHornType>> typesMap = new HashMap<>();
	
	/**
	 * 
	 * @param race The race whose available horn types are to be returned.
	 * @param retainNone Whether to leave HornType.NONE in the list (true) or remove it if it's present (false).
	 * @return A list of HornTypes which are available for this race to have <b>via transformation, not by default</b>. If you want to find out what HornTypes a race has by default, use their RacialBody's getHornTypes() method.
	 */
	public static List<AbstractHornType> getHornTypes(AbstractRace race, boolean retainNone) {
		if(!typesMap.containsKey(race)) {
			List<AbstractHornType> allTypes = new ArrayList<>();
			
			for(AbstractHornType type : HornType.getAllHornTypes()) {
				if(type.getRace()==race) {
					allTypes.add(type);
				}
			}
			if(allTypes.isEmpty()) {
				allTypes.add(HornType.NONE);
				for(AbstractHornType type : HornType.getAllHornTypes()) {
					if(type.isGeneric()) {
						allTypes.add(type);
					}
				}
			}
			
			typesMap.put(race, allTypes);
		}
		
		List<AbstractHornType> types = new ArrayList<>(typesMap.get(race));
		if(!retainNone) {
			types.remove(HornType.NONE);
		}
		return types;
	}
}
