package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class EarType {
	
	public static AbstractEarType HUMAN = new AbstractEarType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			"человеческими",
			"ухо",
			"уши",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("мягкий", "женственная"),
			"Через несколько мгновений жаркий зуд проходит, оставляя [npc.herHim] с обычными человеческими ушами.<br/>[npc.Name] теперь [npc.has] [style.boldHuman(человеческие уши)], которые [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару обычных человеческих ушей, которые [npc.materialCompositionDescriptor] [npc.earFullDescription(true)]#IF(npc.isPiercedEar()), и которые были проколоты#ENDIF.") {
	};

	public static AbstractEarType ANGEL = new AbstractEarType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			"ангел",
			"ухо",
			"уши",
			Util.newArrayListOfValues("заостренное", "хрупкая", "ангельский"),
			Util.newArrayListOfValues("мягкий", "женственная", "заостренное", "хрупкая", "ангельский"),
			"Через несколько мгновений жаркий зуд проходит, оставляя [npc.herHim] с изящными человекоподобными ушами с длинными заостренными кончиками.<br/>[npc.Name] теперь [npc.has] [style.boldAngel(заостренные, ангельские уши)], которые [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару заостренных ангельских ушей, которые [npc.materialCompositionDescriptor] [npc.earFullDescription(true)]#IF(npc.isPiercedEar()), и которые были проколоты#ENDIF.") {
	};

	public static AbstractEarType DEMON_COMMON = new AbstractEarType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			"демонический",
			"ухо",
			"уши",
			Util.newArrayListOfValues("заостренное", "демонический"),
			Util.newArrayListOfValues("мягкий", "женственная", "заостренное", "демонический"),
			"Через несколько мгновений жаркий зуд проходит, оставляя [npc.herHim] с изящными человекоподобными ушами с длинными заостренными кончиками.<br/>[npc.Name] теперь [npc.has] [style.boldDemon(заостренные, демонические уши)], которые [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару заостренных демонических ушей, которые [npc.materialCompositionDescriptor] [npc.earFullDescription(true)]#IF(npc.isPiercedEar()), и которые были проколоты#ENDIF.") {
	};
	
	public static AbstractEarType DOG_MORPH = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"висячие собачьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("повисшие", "пушистая", "покрытая мехом", "собачьи"),
			Util.newArrayListOfValues("женственная", "повисшие", "пушистая", "покрытая мехом", "собачьи"),
			"Они быстро увеличиваются в размерах и смещаются выше на голове [npc.her], чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё тело [npc.her], они сделаны из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новые собачьи уши взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldDog(висячие, собачьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пара висячих,#IF(npc.isPiercedEar()) проколотых,#ENDIF собачьих ушей, расположенных высоко на голове [npc.her] и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType DOG_MORPH_POINTED = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"заостренные собачьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("заостренное", "пушистая", "покрытая мехом", "собачьи"),
			Util.newArrayListOfValues("женственная", "заостренное", "пушистая", "покрытая мехом", "собачьи"),
			"Они быстро превращаются в стоячие заостренные кончики и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро покрывает их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми собачьими ушами взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldDog(заостренные, собачьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару заостренных,#IF(npc.isPiercedEar()) проколотых,#ENDIF собачьих ушей, расположенных высоко на [npc.her] голове и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType DOG_MORPH_FOLDED = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"висячие собачьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("висячие", "пушистая", "покрытая мехом", "собачьи"),
			Util.newArrayListOfValues("женственная", "висячие", "пушистая", "покрытая мехом", "собачьи"),
			"Они быстро превращаются в стоячие заостренные ушки, которые затем загибаются сверху и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Сверху быстро нарастает слой [npc.earFullDescriptionColour],#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми собачьими ушами туда-сюда.<br/>[npc.Name] теперь [npc.has] [style.boldDog(сложенные, собачьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару сложенных,#IF(npc.isPiercedEar()) проколотых,#ENDIF собачьих ушей, расположенных высоко на [npc.her] голове и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType WOLF_MORPH = new AbstractEarType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			"волчьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистая", "покрытая мехом", "волчьими"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "волчьими"),
			"Они быстро превращаются в крупные, стоячие ушки и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новые волчьи уши туда-сюда.<br/>[npc.Name] теперь [npc.has] [style.boldWolf(крупные, волчьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару стоячих,#IF(npc.isPiercedEar()) проколотых,#ENDIF волчьих ушей, расположенных высоко на [npc.her] голове и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType FOX_MORPH = new AbstractEarType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			"лисий",
			"ухо",
			"уши",
			Util.newArrayListOfValues("заостренное", "пушистая", "покрытая мехом", "лисоподобные"),
			Util.newArrayListOfValues("женственная", "заостренное", "пушистая", "покрытая мехом", "лисоподобные"),
			"Они быстро превращаются в крупные, стоячие ушки и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми лисьими ушками взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldFox(заостренные, лисьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару заостренных,#IF(npc.isPiercedEar()) проколотых,#ENDIF лисьих ушей, расположенных высоко на [npc.her] голове и представляющих собой [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType FOX_MORPH_BIG = new AbstractEarType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			"фенек",
			"ухо",
			"уши",
			Util.newArrayListOfValues("заостренное", "пушистая", "покрытая мехом", "крупные", "подобный фенеку"),
			Util.newArrayListOfValues("женственная", "заостренное", "пушистая", "покрытая мехом", "крупные", "подобный фенеку"),
			"Они быстро превращаются в массивные, стоячие ушки и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро покрывает их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми лисьими ушами туда-сюда.<br/>[npc.Name] теперь [npc.has] [style.boldFox(массивные, похожие на уши фенека уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару массивных,#IF(npc.isPiercedEar()) проколотых,#ENDIF ушей, похожих на уши фенека, которые расположены высоко на [npc.her] голове и являются [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType COW_MORPH = new AbstractEarType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			"корова",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистая", "покрытая мехом", "коровьей формы"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "коровьей формы"),
			"Они быстро принимают отчетливую бычью форму, вытягиваясь и сужаясь в длинные, слегка надорванные овалы.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и все остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации, [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новые коровьи ушки взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldCow(коровьи ушки)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару #IF(npc.isPiercedEar()) проколотых,#ENDIF коровьих ушек, которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType CAT_MORPH = new AbstractEarType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			"кошачьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистая", "покрытая мехом", "кошачьими"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "кошачьими"),
			"Они быстро превращаются в стоячие острые кончики и смещаются выше на [npc.her] голове, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и все остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации, [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новые кошачьи ушки взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldCat(кошачьи ушки)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пара #IF(npc.isPiercedEar()) проколотых,#ENDIF кошачьих ушей, расположенных высоко на [npc.her] голове и представляющих собой [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType CAT_MORPH_TUFTED = new AbstractEarType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			"пушистых",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистый", "пушистая", "покрытая мехом", "кошачьими"),
			Util.newArrayListOfValues("женственная", "пушистый", "пушистая", "покрытая мехом", "кошачьими"),
			"Они быстро превращаются в стоячие кисточки, увенчанные небольшим пучком чувствительного меха, и смещаются выше на [npc.her] голове, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро разрастается, покрывая их,#ELSE Как и все остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новые кошачьи уши туда-сюда.<br/>[npc.Name] теперь [npc.has] [style.boldCat(пушистые, кошачьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пара пушистых,#IF(npc.isPiercedEar()) проколотых,#ENDIF кошачьих ушей, расположенных высоко на [npc.her] голове и представляющих собой [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType SQUIRREL_MORPH = new AbstractEarType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			"беличьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистая", "покрытая мехом", "беличьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "покрытая мехом", "беличьи"),
			"Они быстро превращаются в маленькие, стоячие овалы и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новые беличьи ушки туда-сюда.<br/>[npc.Name] теперь [npc.has] [style.boldSquirrel(беличьи ушки)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару#IF(npc.isPiercedEar()) проколотых,#ENDIF беличьих ушек, расположенных высоко на [npc.her] голове и представляющих собой [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType RAT_MORPH = new AbstractEarType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			"крыса",
			"ухо",
			"уши",
			Util.newArrayListOfValues("крысиные"),
			Util.newArrayListOfValues("женственная", "крысиные"),
			"Они быстро превращаются в маленькие, стоячие овалы и располагаются выше на [npc.her] голове, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они сделаны из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми крысиными ушками взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldRat(крысиные ушки)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару #IF(npc.isPiercedEar()) проколотых,#ENDIF крысиных ушек, расположенных высоко на [npc.her] голове и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType RABBIT_MORPH = new AbstractEarType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			"стоячие заячьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("торчащими", "пушистая", "покрытая мехом", "кроличьими"),
			Util.newArrayListOfValues("женственная", "торчащими", "пушистая", "покрытая мехом", "кроличьими"),
			"Они быстро превращаются в крупные, стоячие ушки и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми кроличьими ушками взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldRabbit(стоячие, кроличьи ушки)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару стоячих,#IF(npc.isPiercedEar()) проколотых,#ENDIF кроличьих ушек, расположенных высоко на [npc.her] голове и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType RABBIT_MORPH_FLOPPY = new AbstractEarType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			"висячие кроличьи",
			"ухо",
			"уши",
			Util.newArrayListOfValues("повисшие", "пушистая", "покрытая мехом", "кроличьими"),
			Util.newArrayListOfValues("женственная", "повисшие", "пушистая", "покрытая мехом", "кроличьими"),
			"Они быстро превращаются в крупные, стоячие заостренные ушки, которые располагаются выше, чем обычные человеческие уши, а затем внезапно опадают и свисают по обе стороны головы [npc.her].#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Сверху быстро нарастает слой [npc.earFullDescriptionColour],#ELSE Как и все остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми кроличьими ушками взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldRabbit(свисающие, кроличьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару свисающих,#IF(npc.isPiercedEar()) проколотых,#ENDIF кроличьих ушек, расположенных высоко на голове [npc.her] и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType BAT_MORPH = new AbstractEarType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			"летучая мышь",
			"ухо",
			"уши",
			Util.newArrayListOfValues("крупные", "похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("женственная", "крупные", "похожими на крылья летучей мыши"),
			"Они быстро превращаются в крупные, стоячие заостренные ушки и располагаются выше на [npc.her] голове, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] [npc.verb(discover)], что [npc.she] может легко дергать [npc.her] новыми летучими ушками взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldBat(крупные, похожие на уши летучей мыши уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару крупных,#IF(npc.isPiercedEar()) проколотых,#ENDIF похожих на уши летучей мыши ушек, расположенных высоко на [npc.her] голове и представляющих собой [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.ECHO_LOCATION);
		}
	};

	public static AbstractEarType HORSE_MORPH = new AbstractEarType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			"лошади",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистая", "торчащими", "лошадиный"),
			Util.newArrayListOfValues("женственная", "пушистая", "торчащими", "лошадиный"),
			"Они быстро превращаются в крепкие маленькие стоячие ушки и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми лошадиными ушами взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldHorse(лошадиные уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару стоячих,#IF(npc.isPiercedEar()) проколотых,#ENDIF лошадиных ушей, расположенных высоко на [npc.her] голове и представляющих собой [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType HORSE_MORPH_UPRIGHT = new AbstractEarType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			"высокая лошадь",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистая", "высокая", "торчащими", "лошадиный"),
			Util.newArrayListOfValues("женственная", "высокая", "пушистая", "торчащими", "лошадиный"),
			"Они быстро превращаются в высокие, стоячие ушки и располагаются на [npc.her] голове выше, чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] быстро нарастает, покрывая их,#ELSE Как и всё остальное тело [npc.her], они состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новыми лошадиными ушами взад-вперед.<br/>[npc.Name] теперь [npc.has] [style.boldHorse(высокие, лошадиные уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пару высоких, стоячих,#IF(npc.isPiercedEar()) проколотых,#ENDIF лошадиных ушей, расположенных высоко на [npc.her] голове и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};
	
	public static AbstractEarType REINDEER_MORPH = new AbstractEarType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			"оленьей шерсти",
			"ухо",
			"уши",
			Util.newArrayListOfValues("пушистая", "похожих на оленьи"),
			Util.newArrayListOfValues("женственная", "пушистая", "похожих на оленьи"),
			"Они быстро принимают отчетливую оленью форму, вытягиваясь и сужаясь в длинные, слегка надогнутые овалы, а затем смещаются выше на голове [npc.her], чем обычные человеческие уши.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Сверху быстро нарастает слой [npc.earFullDescriptionColour],#ELSE Как и все остальное тело [npc.her], они сделаны из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.she] экспериментально [npc.verb(twitch)] [npc.her] новые оленьи уши туда-сюда.<br/>[npc.Name] теперь [npc.has] [style.boldReindeer(оленьи уши)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"[npc.She] [npc.has] пара#IF(npc.isPiercedEar()) проколотых,#ENDIF оленьих ушей, расположенных высоко на голове [npc.her] и являющихся [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].") {
	};

	public static AbstractEarType ALLIGATOR_MORPH = new AbstractEarType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			"аллигатора",
			"ухо",
			"уши",
			Util.newArrayListOfValues("твои", "покрытый чешуей", "аллигатороподобными"),
			Util.newArrayListOfValues("женственная", "твои", "покрытый чешуей", "аллигатороподобного"),
			"Они быстро уменьшаются, превращаясь в крошечные бугорки, так как большая часть внешнего хряща смещается в бока головы [npc.her].#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] нарастает, чтобы покрыть теперь уже полностью скрытые внутри уши [npc.her],#ELSE Подобно остальному телу [npc.her], теперь полностью скрытые внутри уши [npc.her] сделаны из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.sheIs] остается с ушами, как у аллигатора. <br/>[npc.Name] теперь [npc.has] [style.boldAlligator(внутренние, покрытые чешуей уши аллигатора)], которые являются [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"Уши [npc.Her] — это внутренняя часть головы [npc.her], прикрытая веером [npc.earFullDescription(true)].#IF(npc.isPiercedEar()) Они были искусно проколоты, чтобы [npc.herHim] могла носить специальные украшения для ушей.#ENDIF") {
	};
	
	public static AbstractEarType HARPY = new AbstractEarType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			"гарпия",
			"ухо",
			"уши",
			Util.newArrayListOfValues("оперенные", "покрытые перьями", "птичьи"),
			Util.newArrayListOfValues("женственная", "оперенные", "покрытые перьями", "птичьи"),
			"Они быстро уменьшаются, превращаясь в крошечные бугорки, так как большая часть внешнего хряща смещается в стороны головы [npc.her].#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.earFullDescriptionColour] нарастает, чтобы покрыть теперь уже полностью скрытые внутри уши [npc.her],#ELSE Подобно остальному телу [npc.her], теперь уже полностью скрытые внутри уши [npc.her] состоят из [npc.earFullDescription],#ENDIF и по завершении трансформации [npc.sheIs] остается с парой прекрасных пернатых ушей гарпии.<br/>[npc.Name] теперь [npc.has] [style.boldHarpy(внутренние, покрытые перьями уши гарпии)], которые [npc.materialCompositionDescriptor] [npc.earFullDescription].",
			"Уши [npc.Her] — это внутренняя часть головы [npc.her], прикрытая веером [npc.earFullDescription(true)].#IF(npc.isPiercedEar()) Они были искусно проколоты, чтобы [npc.herHim] могла носить специальные украшения для ушей.#ENDIF") {
	};
	
	private static List<AbstractEarType> allEarTypes;
	private static Map<AbstractEarType, String> earToIdMap = new HashMap<>();
	private static Map<String, AbstractEarType> idToEarMap = new HashMap<>();
	
	static {
		allEarTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ear")) {
					try {
						AbstractEarType type = new AbstractEarType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEarTypes.add(type);
						earToIdMap.put(type, id);
						idToEarMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ear")) {
					try {
						AbstractEarType type = new AbstractEarType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEarTypes.add(type);
						earToIdMap.put(type, id);
						idToEarMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded ear types:
		
		Field[] fields = EarType.class.getFields();
		
		for(Field f : fields){
			if (AbstractEarType.class.isAssignableFrom(f.getType())) {
				
				AbstractEarType ct;
				try {
					ct = ((AbstractEarType) f.get(null));

					earToIdMap.put(ct, f.getName());
					idToEarMap.put(f.getName(), ct);
					
					allEarTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allEarTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractEarType getEarTypeFromId(String id) {
		if(id.equals("IMP")) {
			return EarType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return EarType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToEarMap.keySet());
		return idToEarMap.get(id);
	}
	
	public static String getIdFromEarType(AbstractEarType earType) {
		return earToIdMap.get(earType);
	}
	
	public static List<AbstractEarType> getAllEarTypes() {
		return allEarTypes;
	}
	
	private static Map<AbstractRace, List<AbstractEarType>> typesMap = new HashMap<>();
	
	public static List<AbstractEarType> getEarTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractEarType> types = new ArrayList<>();
		for(AbstractEarType type : EarType.getAllEarTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}