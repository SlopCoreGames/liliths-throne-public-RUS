package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public class EyeType {

	public static AbstractEyeType HUMAN = new AbstractEyeType(BodyCoveringType.EYE_HUMAN,
			Race.HUMAN,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"человеческими",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			" К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в человеческие глаза с пропорциональными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldHuman(человеческие глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] обычные человеческие глаза с [npc.irisShape], [npc.irisColour(true)] радужками, [npc.pupilShape], [npc.pupilColour(true)] зрачками и [npc.scleraColour(true)] склерами.") {
	};

	public static AbstractEyeType ANGEL = new AbstractEyeType(BodyCoveringType.EYE_ANGEL,
			Race.ANGEL,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"ангел",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			" К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в ангельские глаза с пропорциональными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldAngel(ангельские глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] ангельские глаза с [npc.irisShape], [npc.irisColour(true)] радужками, [npc.pupilShape], [npc.pupilColour(true)] зрачками и [npc.scleraColour(true)] склерами.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EYE_PERFECT_VISION);
		}
	};

	public static AbstractEyeType DEMON_COMMON = new AbstractEyeType(BodyCoveringType.EYE_DEMON_COMMON,
			Race.DEMON,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"демонический",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.isShortStature())К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в бесовские глаза с вертикальными зрачками и огромными радужками.<br/>[npc.Name] теперь [npc.has] [style.boldImp(бесовские глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].#ELSEК тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в демонические глаза с вертикальными зрачками и огромными радужками.<br/>[npc.Name] теперь [npc.has] [style.boldDemon(демонические глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].#ENDIF",
			"[npc.SheHasFull] [npc.eyePairs] #IF(npc.isShortStature())бесовские#ELSEдемонические#ENDIF глаза с [npc.irisShape], [npc.irisColour(true)] радужками, [npc.pupilShape], [npc.pupilColour(true)] зрачками и [npc.scleraColour(true)] склерами.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(
					BodyPartTag.NIGHT_VISION,
					BodyPartTag.EYE_PERFECT_VISION);
		}
	};

	public static AbstractEyeType DEMON_OWL = new AbstractEyeType(BodyCoveringType.EYE_DEMON_COMMON,
			Race.DEMON,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"демоническо-совиные",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в демонические, похожие на совиные глаза, которые обеспечивают [npc.herHim] превосходное ночное зрение.<br/>[npc.Name] теперь [npc.has] [style.boldDemon(демоническо-совиные глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] демонически-совиные глаза с [npc.irisShape], [npc.irisColour(true)] радужками, [npc.pupilShape], [npc.pupilColour(true)] зрачками и [npc.scleraColour(true)] склерами.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(
					BodyPartTag.NIGHT_VISION,
					BodyPartTag.EYE_PERFECT_VISION);
		}
	};

	public static AbstractEyeType CAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_FELINE,
			Race.CAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"кошачьи",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в кошачьи глаза с огромными радужками и вертикальными зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldCatMorph(кошачьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] кошачьи глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType COW_MORPH = new AbstractEyeType(BodyCoveringType.EYE_COW_MORPH,
			Race.COW_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"корова",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в коровьи глаза с огромными зрачками и горизонтальными радужками.<br/>[npc.Name] теперь [npc.has] [style.boldCowMorph(коровьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] коровьи глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType DOG_MORPH = new AbstractEyeType(BodyCoveringType.EYE_DOG_MORPH,
			Race.DOG_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"коровьи",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в собачьи глаза с огромными зрачками и радужками.<br/>[npc.Name] теперь [npc.has] [style.boldDogMorph(собачьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] собачьи глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType FOX_MORPH = new AbstractEyeType(BodyCoveringType.EYE_FOX_MORPH,
			Race.FOX_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"лисий",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в лисьи глаза с широкими радужками и вертикальными зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldFoxMorph(лисьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] лисьи глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType WOLF_MORPH = new AbstractEyeType(BodyCoveringType.EYE_LYCAN,
			Race.WOLF_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"волчьи",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в волчьи глаза с огромными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldWolfMorph(волчьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] волчьи глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType SQUIRREL_MORPH = new AbstractEyeType(BodyCoveringType.EYE_SQUIRREL,
			Race.SQUIRREL_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"беличьи",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в беличьи глаза с огромными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldSquirrelMorph(беличьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] беличьи глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
	};

	public static AbstractEyeType RAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_RAT,
			Race.RAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"крыса",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в крысиные глаза с огромными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldRatMorph(крысиные глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] крысиные глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
	};

	public static AbstractEyeType RABBIT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_RABBIT,
			Race.RABBIT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"кроличьи",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в кроличьи глаза с огромными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldRabbitMorph(кроличьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] кроличьи глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
	};

	public static AbstractEyeType BAT_MORPH = new AbstractEyeType(BodyCoveringType.EYE_BAT,
			Race.BAT_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"летучая мышь",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в глаза, подобные глазам летучей мыши, с огромными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldBatMorph(глаза летучей мыши)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] глаза, подобные глазам летучей мыши, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужки, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType ALLIGATOR_MORPH = new AbstractEyeType(BodyCoveringType.EYE_ALLIGATOR_MORPH,
			Race.ALLIGATOR_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.VERTICAL,
			"аллигатора",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в глаза, подобные глазам аллигатора, с крупными радужками и вертикальными зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldAlligatorMorph(глаза аллигатора)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] аллигатороподобные глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(истинные)] радужки, [npc.pupilShape], [npc.pupilColour(истинные)] зрачки, и [npc.scleraColour(истинные)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType HORSE_MORPH = new AbstractEyeType(BodyCoveringType.EYE_HORSE_MORPH,
			Race.HORSE_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"лошади",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в лошадиные глаза с широкими радужками и горизонтальными зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldHorseMorph(лошадиные глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(истинные)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(истинные)].",
			"[npc.SheHasFull] [npc.eyePairs] лошадиные глаза, радужки и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(истинные)] радужки, [npc.pupilShape], [npc.pupilColour(истинные)] зрачки, и [npc.scleraColour(истинные)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType REINDEER_MORPH = new AbstractEyeType(BodyCoveringType.EYE_REINDEER_MORPH,
			Race.REINDEER_MORPH,
			1,
			EyeShape.ROUND,
			EyeShape.HORIZONTAL,
			"оленьей шерсти",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превратились в оленьи глаза с широкими радужками и горизонтальными зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldReindeerMorph(оленьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(истинные)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(истинные)].",
			"[npc.SheHasFull] [npc.eyePairs] глаза, подобные оленьим, радужка и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужка, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.NIGHT_VISION);
		}
	};

	public static AbstractEyeType HARPY = new AbstractEyeType(BodyCoveringType.EYE_HARPY,
			Race.HARPY,
			1,
			EyeShape.ROUND,
			EyeShape.ROUND,
			"гарпия",
			"глаз",
			"глаза",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К тому времени, как [npc.she] нерешительно [npc.verb(open)] их снова, они превращаются в птичьи глаза с крупными радужками и зрачками.<br/>[npc.Name] теперь [npc.has] [style.boldHarpy(птичьи глаза)] с [style.boldGenericTF([npc.irisShape])], [npc.irisFullDescription(true)] и [style.boldGenericTF([npc.pupilShape])], [npc.pupilFullDescription(true)].",
			"[npc.SheHasFull] [npc.eyePairs] птичьи глаза, радужка и зрачки которых крупнее, чем у обычного человека. У них [npc.irisShape], [npc.irisColour(true)] радужка, [npc.pupilShape], [npc.pupilColour(true)] зрачки и [npc.scleraColour(true)] склеры.") {
	};
	

	private static List<AbstractEyeType> allEyeTypes;
	private static Map<AbstractEyeType, String> eyeToIdMap = new HashMap<>();
	private static Map<String, AbstractEyeType> idToEyeMap = new HashMap<>();
	
	static {
		allEyeTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("eye")) {
					try {
						AbstractEyeType type = new AbstractEyeType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEyeTypes.add(type);
						eyeToIdMap.put(type, id);
						idToEyeMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("eye")) {
					try {
						AbstractEyeType type = new AbstractEyeType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEyeTypes.add(type);
						eyeToIdMap.put(type, id);
						idToEyeMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded eye types:
		
		Field[] fields = EyeType.class.getFields();
		
		for(Field f : fields){
			if (AbstractEyeType.class.isAssignableFrom(f.getType())) {
				
				AbstractEyeType ct;
				try {
					ct = ((AbstractEyeType) f.get(null));

					eyeToIdMap.put(ct, f.getName());
					idToEyeMap.put(f.getName(), ct);
					
					allEyeTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allEyeTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractEyeType getEyeTypeFromId(String id) {
		if(id.equals("IMP")) {
			return EyeType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return EyeType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToEyeMap.keySet());
		return idToEyeMap.get(id);
	}
	
	public static String getIdFromEyeType(AbstractEyeType eyeType) {
		return eyeToIdMap.get(eyeType);
	}
	
	public static List<AbstractEyeType> getAllEyeTypes() {
		return allEyeTypes;
	}
	
	private static Map<AbstractRace, List<AbstractEyeType>> typesMap = new HashMap<>();
	
	public static List<AbstractEyeType> getEyeTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractEyeType> types = new ArrayList<>();
		for(AbstractEyeType type : EyeType.getAllEyeTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}