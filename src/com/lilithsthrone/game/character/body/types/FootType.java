package com.lilithsthrone.game.character.body.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFootType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.10
 * @version 0.4
 * @author Innoxia
 */
public class FootType {

	public static AbstractFootType NONE = new AbstractFootType("нет",
			"нет",
			"нет",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"нет",
			"нет",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"футджоб",
			"[npc.SheHasFull] без ног.",
			Util.newArrayListOfValues(FootStructure.NONE)) {
				@Override
				public String getFootNailPolishDescription(GameCharacter owner) {
					return "";
				}
	};
	
	public static AbstractFootType HUMANOID = new AbstractFootType("гуманоидные",
			"ступня",
			"ступни",
			Util.newArrayListOfValues("мужской"),
			Util.newArrayListOfValues("женственная", "мягкий", "хрупкая", "тонкие"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("мужской"),
			Util.newArrayListOfValues("женственная", "мягкий", "хрупкая", "тонкие"),
			"футджоб",
			"[npc.SheHasFull] человекоподобные ступни.",
			Util.newArrayListOfValues(FootStructure.PLANTIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " [npc.Her] ногти на ногах покрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};

	public static AbstractFootType PAWS = new AbstractFootType("похожая на лапу",
			"лапа",
			"лапы",
			Util.newArrayListOfValues("мужской","пышный"),
			Util.newArrayListOfValues("женственная", "мягкий", "пышный", "хрупкая", "тонкие"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("мужской", "пышный"),
			Util.newArrayListOfValues("женственная", "мягкий", "пышный", "хрупкая", "тонкие"),
			"футджоб",
			"[npc.SheHasFull] лапообразные ступни.",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " [npc.Her] ногти на ногах покрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};

	public static AbstractFootType HOOFS = new AbstractFootType("копытообразные",
			"копыто",
			"копыта",
			Util.newArrayListOfValues("мужской","твёрдая"),
			Util.newArrayListOfValues("женственная", "хрупкая", "твёрдая"),
			"копыто",
			"копыта",
			Util.newArrayListOfValues("мужской", "твёрдая"),
			Util.newArrayListOfValues("женственная", "твёрдая", "хрупкая"),
			"копытотрах",
			"[npc.SheHasFull] копыта вместо ступней.",
			Util.newArrayListOfValues(
					FootStructure.UNGULIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " [npc.Her] копыта окрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};

	public static AbstractFootType REPTILIAN = new AbstractFootType("рептилоидные",
			"ступня",
			"ступни",
			Util.newArrayListOfValues("мужской","когтистые"),
			Util.newArrayListOfValues("женственная", "когтистые", "тонкие"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("мужской", "когтистые"),
			Util.newArrayListOfValues("женственная", "когтистые", "тонкие"),
			"футджоб",
			"[npc.SheHasFull] рептилоидные лапы.",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " Когти на концах [npc.her] мощных пальцев окрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};

	public static AbstractFootType AMPHIBIAN = new AbstractFootType("земноводный",
			"ступня",
			"ступни",
			Util.newArrayListOfValues("мужской", "перепончатый"),
			Util.newArrayListOfValues("женственная", "перепончатый", "тонкие"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("мужской", "перепончатый"),
			Util.newArrayListOfValues("женственная", "перепончатый", "тонкие"),
			"футджоб",
			"[npc.SheHasFull] амфибийные лапы.",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " Кончики [npc.her] пальцев окрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};

	public static AbstractFootType TALONS = new AbstractFootType("птичьи",
			"коготь",
			"когти",
			Util.newArrayListOfValues("мужской","когтистые"),
			Util.newArrayListOfValues("женственная", "когтистые", "тонкие"),
			"коготь",
			"когти",
			Util.newArrayListOfValues("мужской", "острая"),
			Util.newArrayListOfValues("женственная", "острая", "тонкие"),
			"клешнетрах",
			"[npc.SheHasFull] вместо ступней — птичьи когти.",
			Util.newArrayListOfValues(
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " Когти на [npc.her] когтях окрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};

	public static AbstractFootType ARACHNID = new AbstractFootType("паукообразные",
			"ступня",
			"ступни",
			Util.newArrayListOfValues("мужской","сегментированный"),
			Util.newArrayListOfValues("женственная", "сегментированный", "тонкие"),
			"коготь",
			"когти",
			Util.newArrayListOfValues("мужской", "острая"),
			Util.newArrayListOfValues("женственная", "острая", "тонкие"),
			"клешнетрах",
			"[npc.SheHasFull] вместо ступней — паучьи клешни.",
			Util.newArrayListOfValues(
					FootStructure.PLANTIGRADE,
					FootStructure.DIGITIGRADE)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " Когти на [npc.her] паучьих лапах окрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};

	public static AbstractFootType TENTACLE = new AbstractFootType("щупальце",
			"щупальце",
			"щупальца",
			Util.newArrayListOfValues("мужской","сильный"),
			Util.newArrayListOfValues("женственная", "сильный", "тонкие"),
			"присоску",
			"присоски",
			Util.newArrayListOfValues("сильный"),
			Util.newArrayListOfValues("сильный"),
			"щупальцетрах",
			"Кончики [npc.her] щупалец используются почти так же, как ступни.",
			Util.newArrayListOfValues(
					FootStructure.TENTACLED)) {
		@Override
		public String getFootNailPolishDescription(GameCharacter owner) {
			return " Кончики [npc.her] щупалец были раскрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET).getFullDescription(owner, true)+".";
		}
	};
	
	

	private static List<AbstractFootType> allFootTypes;
	private static Map<AbstractFootType, String> footToIdMap = new HashMap<>();
	private static Map<String, AbstractFootType> idToFootMap = new HashMap<>();
	
	static {
		allFootTypes = new ArrayList<>();
		
		// Add in hard-coded foot types:
		Field[] fields = FootType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFootType.class.isAssignableFrom(f.getType())) {
				
				AbstractFootType ct;
				try {
					ct = ((AbstractFootType) f.get(null));

					footToIdMap.put(ct, f.getName());
					idToFootMap.put(f.getName(), ct);
					
					allFootTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static AbstractFootType getFootTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToFootMap.keySet());
		return idToFootMap.get(id);
	}
	
	public static String getIdFromFootType(AbstractFootType footType) {
		return footToIdMap.get(footType);
	}
	
	public static List<AbstractFootType> getAllFootTypes() {
		return allFootTypes;
	}
}
