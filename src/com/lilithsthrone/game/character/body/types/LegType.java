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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class LegType {
	
	public static AbstractLegType HUMAN = new AbstractLegType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской"),
			Util.newArrayListOfValues("женственная"),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"Они стремительно превращаются в обычные человеческие ноги с человеческими ступнями.<br/>[npc.She] теперь [npc.has] [style.boldHuman(человеческие ноги и ступни)], которые [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги и ступни человеческие и [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};
	
	public static AbstractLegType ANGEL = new AbstractLegType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "сияющие", "ангельский"),
			Util.newArrayListOfValues("женственная", "сияющие", "ангельский"),
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues("ангельский"),
			Util.newArrayListOfValues("ангельский"),
			"Они быстро превращаются в пару гладких, стройных ног, и [npc.she] [npc.verb(let)] прерывисто вздыхают, пока слой безупречной ангельской кожи стремительно покрывает их. По завершении трансформации, [npc.she] почти [npc.verb(lose)] [npc.her] равновесие, так как кости в [npc.her] ступнях начинают смещаться и перестраиваться. Спустя мгновение они превращаются в стройные, похожие на человеческие ступни с мягкими, нежными пальцами.<br/>[npc.Name] теперь [npc.has] [style.boldAngel(ангельские ноги и ступни)], которые [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги и ступни имеют человеческую форму, но они [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};

	public static AbstractLegType DEMON_COMMON = new AbstractLegType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "демонический"),
			Util.newArrayListOfValues("женственная", "безупречны", "демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] ноги и ступни имеют человеческую форму, но они [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			if (!owner.isShortStature()) {
				return UtilText.parse(owner,
						" Они быстро превращаются в пару гладких, стройных ног, и [npc.she] [npc.verb(let)] прерывистый вздох, когда слой безупречной демонической кожи стремительно покрывает их. По завершении трансформации [npc.she] почти [npc.verb(lose)] [npc.her] равновесие, пока кости в [npc.her] ступнях начинают смещаться и перестраиваться. Спустя мгновение они превращаются в стройные человекоподобные стопы с мягкими, нежными пальцами.<br/>[npc.Name] теперь [npc.has] [style.boldDemon(демонические ноги и ступни)], которые [npc.materialDescriptor] [npc.legFullDescription].</p>");
			} else {
				return UtilText.parse(owner,
						" Они быстро превращаются в пару гладких, стройных ног, и [npc.she] [npc.verb(let)] прерывистый вздох, когда слой безупречной бесовской кожи стремительно покрывает их. По завершении трансформации [npc.she] почти [npc.verb(lose)] [npc.her] равновесие, пока кости в [npc.her] ступнях начинают смещаться и перестраиваться. Спустя мгновение они превращаются в стройные человекоподобные стопы с мягкими, нежными пальцами.<br/>[npc.Name] теперь [npc.has] [style.boldImp(бесовские ноги и ступни)], которые [npc.materialDescriptor] [npc.legFullDescription].</p>");
			}
		}
	};

	public static AbstractLegType DEMON_HOOFED = new AbstractLegType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "демонический"),
			Util.newArrayListOfValues("женственная", "безупречны", "демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] демонические ноги [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращаются в твердые копыта.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			if (!owner.isShortStature()) {
				return UtilText.parse(owner,
							" Они быстро превращаются в пару гладких, стройных ног, и [npc.name] [npc.verb(let)] сдавленный вздох, когда слой безупречной демонической кожи стремительно разрастается, покрывая их. По мере того как [npc.her] новая кожа спускается до самых кончиков [npc.her] пальцев, они внезапно сжимаются, и [npc.she] [npc.verb(let)] крик, когда на их месте вырастает толстый, похожий на копыто коготь, быстро трансформируясь и превращая [npc.her] ступни в твердые демонические копыта. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новая кожа плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.NameIsFull] оставляя [style.boldDemon(демонические ноги и копыта)], которые [npc.materialDescriptor] [npc.legFullDescription].</p>");
			} else {
				return UtilText.parse(owner,
						" Они быстро превращаются в пару гладких, стройных ног, и [npc.name] [npc.verb(let)] прерывистый вздох, когда слой безупречной демонической кожи стремительно разрастается, покрывая их. Когда [npc.her] новая кожа спускается до самых кончиков [npc.her] пальцев, они внезапно сжимаются, и [npc.she] [npc.verb(let)] вскрикивает, когда на их месте вырастает толстый, копытообразный коготь, быстро превращая [npc.her] ступни в твердые демонические копыта. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новая кожа плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.NameIsFull] остается с [style.boldImp(бесстыдными ножками и копытами)], которые [npc.materialDescriptor] [npc.legFullDescription].</p>");
			}
		}
		@Override
		public String getTransformName() {
			return "демонические копыта";
		}
	};

	public static AbstractLegType DEMON_HORSE_HOOFED = new AbstractLegType(BodyCoveringType.HORSE_HAIR,
			Race.DEMON,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "поросший волосами", "демоническо-лошадиные"),
			Util.newArrayListOfValues("женственная", "поросший волосами", "демоническо-лошадиные"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] демонические, лошадиные ноги [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращены в твердые копыта.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Слой короткой, похожей на конскую шерсть, быстро прорастает на [npc.her] демонических ногах, когда те принимают новую форму. По мере того как эта шерсть распространяется до самых кончиков [npc.her] пальцев, они внезапно сжимаются, и [npc.she] [npc.verb(let)] крик, когда на их месте вырастает толстый, похожий на копыто коготь, который быстро трансформируется, превращая [npc.her] ступни в твердые демонические копыта. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новая шерсть плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(животные, демонические ноги с копытами)], которые являются [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(животные, бесовские ноги с копытами)], которые являются [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "демоническо-лошадиные";
		}
	};

	public static AbstractLegType DEMON_SNAKE = new AbstractLegType(BodyCoveringType.SNAKE_SCALES,
			Race.DEMON,
			FootStructure.NONE,
			FootType.NONE,
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("мужской", "чешуйчатая", "демоническо-змеиные"),
			Util.newArrayListOfValues("женственная", "чешуйчатая", "демоническо-змеиные"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] демоническое, змееподобное нижнее тело является [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.TAIL_LONG),
			false) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return false;
		}
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Слой гладкой чешуи быстро прорастает на [npc.her] демонических ногах, когда они принимают змееподобный вид. Трансформация быстро завершается, оставляя [npc.name] с [npc.her] новой чешуей, которая плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(демонические, змееподобные ноги)], которые являются [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(бесовские, змееподобные ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "демоническо-змеиные";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LYXIAS_6); //TODO?
		}
	};

	public static AbstractLegType DEMON_SPIDER = new AbstractLegType(BodyCoveringType.SPIDER_CHITIN,
			Race.DEMON,
			FootStructure.DIGITIGRADE,
			FootType.ARACHNID,
			"",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "паукообразные", "демонический-паук"),
			Util.newArrayListOfValues("женственная", "паукообразные", "демонический-паук"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] демоническое, паукообразное нижнее тело [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.ARACHNID),
			true) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Слой гладкого хитина быстро покрывает [npc.her] демонические [npc.legs], когда они приобретают вид паучьих ног. Трансформация быстро завершается, оставляя [npc.name] с [npc.her] новыми хитиновыми покровами, плавно переходящими в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(демонические, паукообразные ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(бесовские, паукообразные ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "демонический-паук";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LUNETTE_5); //TODO?
		}
	};

	public static AbstractLegType DEMON_OCTOPUS = new AbstractLegType(BodyCoveringType.OCTOPUS_SKIN,
			Race.DEMON,
			FootStructure.TENTACLED,
			FootType.TENTACLE,
			"",
			"щупальце",
			"щупальца",
			Util.newArrayListOfValues("мужской", "головоногий", "демонический-осьминог"),
			Util.newArrayListOfValues("женственная", "головоногий", "демонический-осьминог"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] демоническое, похожее на осьминога нижнее тело [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.CEPHALOPOD),
			false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Слой гладкой кожи быстро покрывает [npc.her] демонические [npc.legs], когда они пугающим образом превращаются в щупальца. Эта стремительная трансформация завершается тем, что [npc.name] получает [npc.her] новую кожу, плавно переходящую в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедра.<br/>[npc.Name] теперь [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(демонические, осьминожьи ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(бесовские, осьминожьи ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "демонический-осьминог";
		}
		@Override
		public AbstractTentacleType getTentacleType() {
			return TentacleType.LEG_DEMON_OCTOPUS;
		}
		public int getTentacleCount() {
			return 8;
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1); //TODO?
		}
	};

	public static AbstractLegType DEMON_FISH = new AbstractLegType(BodyCoveringType.FISH_SCALES,
			Race.DEMON,
			FootStructure.NONE,
			FootType.NONE,
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("мужской", "чешуйчатая", "демоническо-рыбий"),
			Util.newArrayListOfValues("женственная", "чешуйчатая", "демоническо-рыбий"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] демоническое, похожее на рыбу нижнее тело [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.TAIL),
			false) {
		@Override
		public boolean isDefaultPlural(GameCharacter gc) {
			return gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE);
		}
		@Override
		public String getNameSingular(GameCharacter gc) {
			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
				return "нога";
			} else {
				return "хвост";
			}
		}
		@Override
		public String getNamePlural(GameCharacter gc) {
			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
				return "ноги";
			} else {
				return "хвосты";
			}
		}
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Слой гладкой чешуи быстро покрывает [npc.her] демонические ноги, когда они приобретают рыбье подобие. Эта стремительная трансформация завершается тем, что [npc.name] получает [npc.her] новую чешую, плавно переходящую в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедра.<br/>[npc.Name] теперь [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(демонические, рыбьи ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(бесовские, рыбьи ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "демоническо-рыбий";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1); //TODO?
		}
	};

	public static AbstractLegType DEMON_EAGLE = new AbstractLegType(BodyCoveringType.FEATHERS,
			Race.DEMON,
			FootStructure.DIGITIGRADE,
			FootType.TALONS,
			"",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "когтистые", "антропоморфные, птичьи"),
			Util.newArrayListOfValues("женственная", "когтистые", "антропоморфные, птичьи"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			Util.newArrayListOfValues("демонический"),
			"-",
			"[npc.Her] демоническое, птичье нижнее тело [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
			Util.newArrayListOfValues(
					LegConfiguration.AVIAN),
			false) {
		@Override
		public String getTransformationDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Слой перьев быстро покрывает [npc.her] демонический [npc.legs], когда они принимают птичий облик. Трансформация завершается быстро, оставляя [npc.name] с [npc.her] новыми перьями, плавно переходящими в [npc.skin], покрывающие остальную часть [npc.her] тела на уровне [npc.her] бедра.<br/>[npc.Name] теперь [npc.has]"
						+ (!owner.isShortStature()
							?" [style.boldDemon(демонические, птичьи ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."
							:"[style.boldImp(бесовские, птичьи ноги)], которые [npc.materialDescriptor] [npc.legFullDescription]."));
		}
		@Override
		public String getTransformName() {
			return "демоническо-орлиные";
		}
		@Override
		public boolean isAvailableForSelfTransformMenu(GameCharacter gc) {
			return gc.hasPerkAnywhereInTree(Perk.POWER_OF_LISOPHIA_7); //TODO?
		}
	};
	
	public static AbstractLegType COW_MORPH = new AbstractLegType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "копытные", "пушистая", "покрытая мехом", "антропоморфные, коровьи"),
			Util.newArrayListOfValues("женственная", "копытные", "пушистая", "покрытая мехом", "антропоморфные, коровьи"),
			Util.newArrayListOfValues("коровьей формы", "бычий"),
			Util.newArrayListOfValues("коровьей формы", "бычий"),
			Util.newArrayListOfValues("коровьей формы", "бычий"),
			Util.newArrayListOfValues("коровьей формы", "бычий"),
			"Слой короткой коровьей шерсти быстро прорастает на [npc.her] ногах, пока те принимают новую форму. Когда [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, они внезапно сжимаются, и [npc.she] [npc.verb(let)] крик, когда на их месте вырастает толстый, похожий на копыто коготь, прежде чем быстро трансформироваться и превратить [npc.her] ступни в коровьи копыта. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldCowMorph(коровьи ноги и копыта)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращены в антропоморфные коровьи копыта.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType DOG_MORPH = new AbstractLegType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфные, собачьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфные, собачьи"),
			Util.newArrayListOfValues("собачьи", "пёсьи"),
			Util.newArrayListOfValues("собачьи", "пёсьи"),
			Util.newArrayListOfValues("собачьи", "пёсьи"),
			Util.newArrayListOfValues("собачьи", "пёсьи"),
			"Слой собачьего меха быстро прорастает на [npc.her] ногах, когда те принимают новую форму. По мере того как [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в маленькие тупые когти, а кожаные подушечки покрывают [npc.her] подошвы, оставляя [npc.herHim] с лапами вместо ступней. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] верхней части бедра.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldDogMorph(собачьи ноги и лапы)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"Ноги [npc.Her] являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а ступни [npc.her] превращаются в антропоморфные собачьи лапы с маленькими тупыми когтями и кожаными подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType WOLF_MORPH = new AbstractLegType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфные, волчьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфные, волчьи"),
			Util.newArrayListOfValues("волчьими", "волчий"),
			Util.newArrayListOfValues("волчьими", "волчий"),
			Util.newArrayListOfValues("волчьими", "волчий"),
			Util.newArrayListOfValues("волчьими", "волчий"),
			"Слой волчьего меха быстро покрывает [npc.her] ноги, когда те принимают новую форму. По мере того как [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в острые когти, а подошвы [npc.her] покрываются грубыми кожаными подушечками, оставляя [npc.herHim] с лапами вместо ступней. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела до [npc.her] верхней части бедер.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldWolfMorph(волчьи ноги и лапы)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги — это [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращаются в антропоморфные волчьи лапы с острыми когтями и грубыми кожаными подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType FOX_MORPH = new AbstractLegType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфные, лисьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфные, лисьи"),
			Util.newArrayListOfValues("лисоподобные", "лисьи"),
			Util.newArrayListOfValues("лисоподобные", "лисьи"),
			Util.newArrayListOfValues("лисоподобные", "лисьи"),
			Util.newArrayListOfValues("лисоподобные", "лисьи"),
			"Слой лисьего меха быстро покрывает [npc.her] ноги, когда те принимают новую форму. По мере того как [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в острые когти, а подошвы [npc.her] покрываются маленькими подушечками, оставляя [npc.herHim] с лапами вместо ступней. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] бедер.</br>[npc.Name] теперь [npc.has] антропоморфные, [style.boldFoxMorph(лисьи ноги и ступни)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги — это [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращаются в антропоморфные лисьи лапы с острыми когтями и грубыми кожаными подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType SQUIRREL_MORPH = new AbstractLegType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфные, беличьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфные, беличьи"),
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues("беличьи"),
			Util.newArrayListOfValues("беличьи"),
			"Слой беличьего меха быстро покрывает [npc.her] ноги, пока те принимают новую форму. По мере того как [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в острые когти, а маленькие розовые подушечки покрывают [npc.her] подошвы, оставляя [npc.herHim] с лапами вместо ступней. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] верхней части бедер.<br/>[npc.Name] теперь [npc.has] антропоморфная, [style.boldSquirrelMorph(беличьи ноги и ступни)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"Ноги [npc.Her] являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а ступни [npc.her] превращаются в антропоморфные беличьи лапки с когтями и розовыми подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType RAT_MORPH = new AbstractLegType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфная, крысоподобная"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфная, крысоподобная"),
			Util.newArrayListOfValues("крысиные"),
			Util.newArrayListOfValues("крысиные"),
			Util.newArrayListOfValues("крысиные"),
			Util.newArrayListOfValues("крысиные"),
			"Слой крысиного меха быстро покрывает [npc.her] ноги, когда те принимают новую форму. По мере того как [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в острые когти, а маленькие розовые подушечки покрывают [npc.her] подошвы, оставляя [npc.herHim] с лапами вместо ступней. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has] антропоморфная, [style.boldRatMorph(крысиные ноги и лапы)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращаются в антропоморфные крысиные лапы с когтями и кожистыми подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType RABBIT_MORPH = new AbstractLegType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфные, кроличьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфные, кроличьи"),
			Util.newArrayListOfValues("кроличьими"),
			Util.newArrayListOfValues("кроличьими"),
			Util.newArrayListOfValues("кроличьими"),
			Util.newArrayListOfValues("кроличьими"),
			"Слой кроличьего меха быстро покрывает [npc.her] ноги, когда те принимают новую форму. По мере того как [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в тупые когти, а мягкие подушечки покрывают [npc.her] подошвы, оставляя [npc.herHim] с длинными лапами. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] верхней части бедер.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldRabbitMorph(кроличьи ноги и ступни)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"Ноги [npc.Her] — это [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а ступни [npc.her] превращаются в длинные антропоморфные кроличьи лапы с тупыми когтями и мягкими подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType BAT_MORPH = new AbstractLegType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфные, похожие на летучие мыши"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфные, похожие на летучие мыши"),
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			"Слой похожей на летучую мышь шерсти быстро покрывает [npc.her] ноги, когда те принимают новую форму. По мере того как [npc.her] новая шерсть спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в острые когти, а маленькие розовые подушечки покрывают [npc.her] подошвы, оставляя [npc.herHim] с лапами, подобными звериным. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новая шерсть плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] верхней части бедра.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldBatMorph(похожие на лапы летучей мыши ноги и ступни)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращаются в антропоморфные лапы летучей мыши, снабженные когтями и кожистыми подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL), false) {
	};
	
	public static AbstractLegType CAT_MORPH = new AbstractLegType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			FootStructure.DIGITIGRADE,
			FootType.PAWS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "пушистая", "покрытая мехом", "антропоморфные, кошачьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "антропоморфные, кошачьи"),
			Util.newArrayListOfValues("кошачьими", "кошачьими"),
			Util.newArrayListOfValues("кошачьими", "кошачьими"),
			Util.newArrayListOfValues("кошачьими", "кошачьими"),
			Util.newArrayListOfValues("кошачьими", "кошачьими"),
			"Слой кошачьего меха быстро покрывает [npc.her] ноги, когда те принимают новую форму. По мере того как [npc.her] новый мех спускается до самых кончиков [npc.her] пальцев, [npc.her] ногти утолщаются, превращаясь в острые втягивающиеся когти, а маленькие розовые подушечки покрывают [npc.her] подошвы, оставляя [npc.herHim] с лапами вместо ступней. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела на уровне [npc.her] верхней части бедра.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldCatMorph(кошачьи ноги и ступни)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] ноги являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращаются в антропоморфные кошачьи лапы с втягивающимися когтями и розовыми подушечками.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType ALLIGATOR_MORPH = new AbstractLegType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			FootStructure.PLANTIGRADE,
			FootType.HUMANOID,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "чешуйчатая", "рептилоидные", "антропоморфные, аллигатороподобные"),
			Util.newArrayListOfValues("женственная", "чешуйчатая", "рептилоидные", "антропоморфные, аллигатороподобные"),
			Util.newArrayListOfValues("аллигатороподобного", "чешуйчатая", "рептилоидные"),
			Util.newArrayListOfValues("аллигатороподобного", "чешуйчатая", "рептилоидные"),
			Util.newArrayListOfValues("аллигатороподобного", "чешуйчатая", "рептилоидные"),
			Util.newArrayListOfValues("аллигатороподобного", "чешуйчатая", "рептилоидные"),
			"Слой аллигатороподобной чешуи быстро покрывает ноги [npc.her], когда они принимают новую форму. По мере того как новые чешуйки [npc.her] распространяются до самых кончиков пальцев [npc.her], ногти [npc.her] утолщаются, превращаясь в острые когти, а подошвы [npc.her] покрываются мелкой чешуей, оставляя [npc.herHim] с лапами, похожими на аллигаторовые. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новые чешуйки плавно переходят в [npc.skin], покрывающие остальную часть тела [npc.her] на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldGatorMorph(аллигатороподобные ноги и ступни)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"Ноги [npc.Her] являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а ступни [npc.her] превращаются в антропоморфные аллигатороподобные лапы с острыми когтями.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType HORSE_MORPH = new AbstractLegType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "поросший волосами", "антропоморфные, лошадиного типа"),
			Util.newArrayListOfValues("женственная", "поросший волосами", "антропоморфные, лошадиного типа"),
			Util.newArrayListOfValues("лошадиный", "лошадиный"),
			Util.newArrayListOfValues("лошадиный", "лошадиный"),
			Util.newArrayListOfValues("лошадиный", "лошадиный"),
			Util.newArrayListOfValues("лошадиный", "лошадиный"),
			"Слой короткой конской шерсти быстро прорастает на ногах [npc.her], когда те принимают новую форму. По мере того как новый мех [npc.her] спускается до самых кончиков [npc.her] пальцев, они внезапно сжимаются, и [npc.she] [npc.verb(let)] крик, когда на их месте вырастает толстый копытный коготь, который быстро трансформируется, превращая [npc.her] ступни в лошадиные копыта. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новая шерсть плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldHorseMorph(лошадиные ноги и копыта)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"Ноги [npc.Her] являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а [npc.her] ступни превращаются в антропоморфные лошадиные копыта.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};

//	public static AbstractLegType HORSE_FISH = new AbstractLegType(BodyCoveringType.FISH_SCALES,
//			Race.HORSE_MORPH,
//			FootStructure.PLANTIGRADE, // FootStructure and Type is for when legs are grown on land.
//			FootType.HUMANOID,
//			"a",
//			"tail",
//			"tails",
//			Util.newArrayListOfValues("masculine", "scaly", "fish"),
//			Util.newArrayListOfValues("feminine", "scaly", "fish"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			Util.newArrayListOfValues("horse"),
//			"-",
//			"[npc.Her] fish-like lower body is [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].",
//			Util.newArrayListOfValues(
//					LegConfiguration.TAIL),
//			false) {
//		@Override
//		public boolean isDefaultPlural(GameCharacter gc) {
//			return gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE);
//		}
//		@Override
//		public String getNameSingular(GameCharacter gc) {
//			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
//				return "leg";
//			} else {
//				return "tail";
//			}
//		}
//		@Override
//		public String getNamePlural(GameCharacter gc) {
//			if(gc.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
//				return "legs";
//			} else {
//				return "tails";
//			}
//		}
//		@Override
//		public String getTransformationDescription(GameCharacter owner) {
//			return UtilText.parse(owner,
//					"A layer of smooth scales quickly grow over [npc.her] legs as they take on a fish-like appearance."
//					+ " Quickly coming to an end, the transformation leaves [npc.name] with [npc.her] new scales smoothly transitioning into the [npc.skin] covering the rest of [npc.her] body at [npc.her] upper-thigh."
//					+ "<br/>[npc.Name] now [npc.has] [style.boldHorseMorph(fish-like legs)], which are [npc.materialDescriptor] [npc.legFullDescription].");
//		}
//		@Override
//		public String getTransformName() {
//			return "hippocampus";
//		}
//	};
	
	public static AbstractLegType REINDEER_MORPH = new AbstractLegType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			FootStructure.UNGULIGRADE,
			FootType.HOOFS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "покрытая мехом", "антропоморфные, оленьи"),
			Util.newArrayListOfValues("женственная", "покрытая мехом", "антропоморфные, оленьи"),
			Util.newArrayListOfValues("похожих на оленьи"),
			Util.newArrayListOfValues("похожих на оленьи"),
			Util.newArrayListOfValues("похожих на оленьи"),
			Util.newArrayListOfValues("похожих на оленьи"),
			"Слой густой, похожей на оленью шерсти быстро покрывает ноги [npc.her], пока они принимают новую форму. Когда [npc.her] новая шерсть спускается до самых кончиков [npc.her] пальцев, они внезапно сжимаются, и [npc.she] [npc.verb(let)] крик, когда на их месте вырастает серповидное раздвоенное копыто, которое быстро трансформируется, превращая [npc.her] ступни в оленьи копыта. Когда трансформация завершается, [npc.she] [npc.verb(see)], что [npc.her] новая шерсть плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела на уровне [npc.her] бедер.<br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldReindeerMorph(оленьи ноги и копыта)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"Ноги [npc.Her] являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], а ступни [npc.her] превратились в антропоморфные оленьи копыта.",
			Util.newArrayListOfValues(
					LegConfiguration.BIPEDAL,
					LegConfiguration.QUADRUPEDAL), false) {
	};
	
	public static AbstractLegType HARPY = new AbstractLegType(BodyCoveringType.HARPY_SKIN,
			Race.HARPY,
			FootStructure.DIGITIGRADE,
			FootType.TALONS,
			"пара",
			"нога",
			"ноги",
			Util.newArrayListOfValues("мужской", "когтистые", "антропоморфные, птичьи"),
			Util.newArrayListOfValues("женственная", "когтистые", "антропоморфные, птичьи"),
			Util.newArrayListOfValues("птичьи"),
			Util.newArrayListOfValues("птичьи"),
			Util.newArrayListOfValues("птичьи"),
			Util.newArrayListOfValues("птичьи"),
			"Слой чешуйчатой, птичьей кожи быстро покрывает ноги [npc.her], когда те принимают новую форму. По мере того как новая кожистая кожа [npc.her] распространяется до самых кончиков пальцев [npc.her], ступни [npc.her] начинают подвергаться экстремальной трансформации. Пальцы [npc.Her] сливаются и перестраиваются в три направленных вперед когтя, в то время как четвертый, похожий на большой палец коготь, ответвляется сзади них. Когда трансформация завершается, эта кожистая кожа резко переходит в [npc.skin] [npc.skinColour] [npc.her] тела на уровне [npc.her] верхней части бедра. <br/>[npc.Name] теперь [npc.has] антропоморфные, [style.boldHarpy(птичьи ноги и когти вместо ступней)], которые являются [npc.materialDescriptor] [npc.legFullDescription].",
			"[npc.Her] птичьи ноги являются [npc.materialCompositionDescriptor] [npc.legFullDescription(true)], которые резко переходят в [npc.skin] [npc.skinColour] [npc.her] тела на уровне [npc.her] верхней части бедра. На концах каждой из [npc.her] [npc.legs] расположены [npc.she] [npc.has] острые птичьи когти.",
				Util.newArrayListOfValues(
						LegConfiguration.BIPEDAL,
						LegConfiguration.AVIAN), false) {
	};
	
	private static List<AbstractLegType> allLegTypes;
	private static Map<AbstractLegType, String> legToIdMap = new HashMap<>();
	private static Map<String, AbstractLegType> idToLegMap = new HashMap<>();
	
	static {
		allLegTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("leg")) {
					try {
						AbstractLegType type = new AbstractLegType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allLegTypes.add(type);
						legToIdMap.put(type, id);
						idToLegMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("leg")) {
					try {
						AbstractLegType type = new AbstractLegType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allLegTypes.add(type);
						legToIdMap.put(type, id);
						idToLegMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded leg types:
		
		Field[] fields = LegType.class.getFields();
		
		for(Field f : fields){
			if (AbstractLegType.class.isAssignableFrom(f.getType())) {
				
				AbstractLegType ct;
				try {
					ct = ((AbstractLegType) f.get(null));

					legToIdMap.put(ct, f.getName());
					idToLegMap.put(f.getName(), ct);
					
					allLegTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allLegTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractLegType getLegTypeFromId(String id) {
		if(id.equals("IMP")) {
			return LegType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return LegType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToLegMap.keySet());
		return idToLegMap.get(id);
	}
	
	public static String getIdFromLegType(AbstractLegType legType) {
		return legToIdMap.get(legType);
	}
	
	public static List<AbstractLegType> getAllLegTypes() {
		return allLegTypes;
	}
	
	private static Map<AbstractRace, List<AbstractLegType>> typesMap = new HashMap<>();
	public static List<AbstractLegType> getLegTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractLegType> types = new ArrayList<>();
		for(AbstractLegType type : LegType.getAllLegTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}