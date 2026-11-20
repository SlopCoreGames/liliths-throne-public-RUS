package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * Contains static instances of AbstractArmType.
 * 
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class ArmType {

	public static AbstractArmType HUMAN = new AbstractArmType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			"рука",
			"руки",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"кисть",
			"кисти",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("мягкий", "женственная"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("мягкий", "женственная"),
			"К счастью, трансформация длится всего мгновение, оставляя [npc.herHim] с обычными на вид человеческими руками и кистями.<br/>[npc.Name] теперь [npc.has] [style.boldHuman(человеческие руки и кисти)], которые [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] обычные человеческие руки и кисти, которые [npc.materialCompositionDescriptor] [npc.armFullDescription(true)].") {
	};

	public static AbstractArmType ANGEL = new AbstractArmType(BodyCoveringType.HUMAN,
			Race.ANGEL,
			"рука",
			"руки",
			Util.newArrayListOfValues("хрупкая"),
			Util.newArrayListOfValues("хрупкая"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("хрупкая", "мягкий"),
			Util.newArrayListOfValues("хрупкая", "мягкий", "женственная"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("хрупкая", "мягкий"),
			Util.newArrayListOfValues("хрупкая", "мягкий", "женственная"),
			"Всего за мгновение они превратились в изящные, похожие на человеческие руки с человеческими кистями. Несмотря на их почти обычный вид, в них чувствуется едва уловимое, манящее очарование, выдающее их истинную ангельскую природу.<br/>[npc.Name] теперь [npc.has] [style.boldAngel(ангельские руки и кисти)], которые [npc.materialDescriptor] [npc.armFullDescription]",
			"[npc.She] [npc.has] [npc.armRows] похожие на человеческие руки и кисти, которые [npc.materialCompositionDescriptor] [npc.armFullDescription(true)].") {
	};

	public static AbstractArmType DEMON_COMMON = new AbstractArmType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			"рука",
			"руки",
			Util.newArrayListOfValues("безупречны"),
			Util.newArrayListOfValues("безупречны"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("хрупкая", "мягкий", "женственная"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("хрупкая", "мягкий", "женственная"),
			"Всего за мгновение они превратились в изящные, похожие на человеческие руки с человеческими кистями. Несмотря на их почти обычный вид, в них чувствуется едва уловимое, манящее очарование, выдающее их истинную демоническую природу.<br/>[npc.Name] теперь [npc.has] [style.boldDemon(демонические руки и кисти)], которые [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] стройные человекоподобные руки и кисти, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)].") {
	};

	public static AbstractArmType COW_MORPH = new AbstractArmType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("бычий"),
			Util.newArrayListOfValues("женственная", "бычий"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("бычий"),
			Util.newArrayListOfValues("женственная", "бычий"),
			"Всего за несколько мгновений их покрыл слой [npc.armFullDescription], и, взглянув вниз, [npc.she] [npc.verb(see)] [npc.her] новые волосы, растущие на тыльной стороне [npc.her] рук, в то время как вместо обычных человеческих ногтей пробиваются жесткие, похожие на копыта. Несмотря на их внешний вид, [npc.sheIsFull] с облегчением обнаружили, что [npc.her] руки не утратили своей ловкости. По мере завершения трансформации, [npc.she] [npc.verb(see)], что у [npc.her] верхней части бицепса [npc.her] волосы плавно переходят в [npc.skin], покрывающий остальную часть [npc.her] тела.<br/>[npc.NameIsFull] остались с антропоморфными, [style.boldCowMorph(коровьими руками и кистями)], которые были [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти, хотя и сохраняют человеческую форму, имеют жесткие маленькие копытообразные ногти.") {
	};

	public static AbstractArmType DOG_MORPH = new AbstractArmType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("собачьи", "похожая на лапу", "пушистая", "пёсьи"),
			Util.newArrayListOfValues("мягкий", "женственная", "собачьи", "похожая на лапу", "пушистая", "пёсьи"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("пышный", "пёсьи"),
			Util.newArrayListOfValues("мягкий", "женственная", "пышный", "пёсьи"),
			"Всего за мгновение их покрыл слой [npc.armFullDescription], и, взглянув вниз, [npc.she] [npc.verb(see)] [npc.her] новый мех на спинах [npc.her] рук, пока тупые собачьи когти прорываются наружу, заменяя [npc.her] ногти. [npc.Her] ладони стремительно превращаются в [npc.materialDescriptor] маленькие кожистые подушечки, а на [npc.her] верхней части бицепса [npc.her] мех плавно переходит в [npc.skin], покрывающий остальное тело [npc.her].<br/>Когда трансформация завершается, [npc.nameIsFull] остаются с антропоморфными, [style.boldDogMorph(собачьими руками и кистями)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти превратились в антропоморфные, собачьи лапы, снабженные маленькими тупыми когтями и кожистыми подушечками.") {
	};

	public static AbstractArmType WOLF_MORPH = new AbstractArmType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("волчьими", "пушистая", "похожая на лапу"),
			Util.newArrayListOfValues("мягкий", "женственная", "волчьими", "пушистая", "похожая на лапу"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("пышный", "волчьими"),
			Util.newArrayListOfValues("мягкий", "женственная", "пышный", "волчьими"),
			"Всего за мгновение их [npc.armFullDescription] быстро покрылся слоем шерсти, и, глядя вниз, можно было увидеть, как [npc.she] [npc.verb(see)] [npc.her] новый мех на спинах [npc.her] рук, а острые когти пробиваются наружу, заменяя [npc.her] ногти. [npc.Her] ладони стремительно превращаются в [npc.materialDescriptor] грубые кожистые подушечки, а на [npc.her] верхней части бицепсов [npc.her] мех плавно переходит в [npc.skin], покрывающий остальное тело [npc.her].<br/>Когда трансформация завершается, [npc.nameIsFull] остаются антропоморфными, [style.boldWolfMorph(волчьими руками и кистями)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти превратились в антропоморфные, волчьи лапы с острыми когтями и грубыми кожистыми подушечками.") {
	};

	public static AbstractArmType FOX_MORPH = new AbstractArmType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("лисоподобные", "пушистая", "похожая на лапу"),
			Util.newArrayListOfValues("мягкий", "женственная", "лисоподобные", "пушистая", "похожая на лапу"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("пышный", "лисоподобные"),
			Util.newArrayListOfValues("мягкий", "женственная", "пышный", "лисоподобные"),
			"Всего за мгновение их [npc.armFullDescription] покрылся слоем [npc.she], который [npc.verb(see)] [npc.her] новый мех на спинах [npc.her] рук, а острые когти прорезают [npc.her] ногти. [npc.Her] ладони стремительно превращаются в [npc.materialDescriptor] небольшие подушечки, а на [npc.her] верхней части бицепса [npc.her] мех плавно переходит в [npc.skin], покрывающий остальное [npc.her] тела.</br>Когда трансформация завершается, [npc.nameIsFull] остаются антропоморфными, [style.boldFoxMorph(лисоподобными руками и кистями)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти превращаются в антропоморфные, лисоподобные лапы с острыми когтями и жесткими кожаными подушечками.") {
	};

	public static AbstractArmType CAT_MORPH = new AbstractArmType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("мягкий", "хрупкая", "кошачьими", "похожая на лапу", "пушистая", "кошачьими"),
			Util.newArrayListOfValues("мягкий", "женственная", "кошачьими", "похожая на лапу", "пушистая", "кошачьими"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("мягкий", "хрупкая", "пышный", "кошачьими"),
			Util.newArrayListOfValues("мягкий", "женственная", "пышный", "кошачьими"),
			"Всего за мгновение их [npc.armFullDescription] быстро покрылся слоем [npc.she], [npc.verb(see)] [npc.her] мехом на спинах [npc.her] рук, в то время как острые втягивающиеся когти пробивались сквозь [npc.her] ногти. [npc.Her] ладони стремительно превращались в [npc.materialDescriptor] маленькие розовые подушечки, а на [npc.her] верхней части бицепса [npc.her] мех плавно переходил в [npc.skin], покрывающий остальное тело [npc.her].<br/>Когда трансформация завершается, [npc.nameIsFull] остаются с антропоморфными, [style.boldCatMorph(кошачьими руками и кистями)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти превратились в антропоморфные, кошачьи лапы, снабженные втягивающимися когтями и розовыми подушечками.") {
	};

	public static AbstractArmType HORSE_MORPH = new AbstractArmType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("поросший волосами"),
			Util.newArrayListOfValues("поросший волосами"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues("женственная", "лошадиный"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues("женственная", "лошадиный"),
			"Всего за несколько мгновений их покрыл слой [npc.armFullDescription], и, взглянув вниз, [npc.she] [npc.verb(see)] [npc.her] новый волос, растущий на тыльной стороне [npc.her] рук, в то время как вместо обычных человеческих ногтей пробиваются жесткие, похожие на копыта. Несмотря на их внешний вид, [npc.sheIs] с облегчением обнаружили, что [npc.her] руки не утратили своей ловкости. По мере завершения трансформации, [npc.she] [npc.verb(see)], что у [npc.her] верхней части бицепса [npc.her] волос плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] тела.<br/>[npc.NameIsFull] остались с антропоморфными, [style.boldHorseMorph(лошадиными руками и кистями)], которые были [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти, хотя и сохраняют человеческую форму, имеют жесткие маленькие копытообразные ногти.") {
	};

	public static AbstractArmType REINDEER_MORPH = new AbstractArmType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("поросший волосами"),
			Util.newArrayListOfValues("поросший волосами"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("оленьи"),
			Util.newArrayListOfValues("женственная", "оленьи"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("оленьей шерсти"),
			Util.newArrayListOfValues("женственная", "оленьей шерсти"),
			"Всего за мгновение их покрыл слой [npc.armFullDescription], и, взглянув вниз, [npc.she] [npc.verb(see)] [npc.her] новый мех на спинах [npc.her] рук, в то время как вместо обычных человеческих ногтей прорезались жесткие, похожие на копыта. Несмотря на их вид, [npc.sheIs] с облегчением обнаружили, что [npc.her] руки не утратили своей ловкости. Когда трансформация подходит к концу, [npc.she] [npc.verb(see)], что у [npc.her] верхней части бицепсов [npc.her] мех плавно переходит в [npc.skin], покрывающий остальное тело [npc.her].<br/>[npc.NameIsFull] остались с антропоморфными, [style.boldReindeerMorph(оленьими руками и кистями)], которые были [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти, хотя и сохраняют человеческую форму, имеют жесткие маленькие копытообразные ногти.") {
	};

	public static AbstractArmType ALLIGATOR_MORPH = new AbstractArmType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("твои", "рептилоидный"),
			Util.newArrayListOfValues("твои", "рептилоподобными"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("твои"),
			Util.newArrayListOfValues("женственная", "твои"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("твои"),
			Util.newArrayListOfValues("женственная", "твои"),
			"Всего за мгновение их покрыл слой [npc.armFullDescription], и, если посмотреть вниз, [npc.she] [npc.verb(see)] [npc.her] новые чешуйки, растущие на спинах [npc.her] рук, пока острые когти прорываются наружу, заменяя [npc.her] ногти. [npc.Her] ладони стремительно превращаются в [npc.materialDescriptor] мелкую чешую, а на [npc.her] верхней части бицепсов [npc.her] чешуя плавно переходит в [npc.skin], покрывающую остальную часть [npc.her] тела.<br/>Когда трансформация завершается, [npc.nameIsFull] остаются антропоморфными, [style.boldGatorMorph(аллигатороподобными руками и кистями)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти превратились в антропоморфные, аллигатороподобные ладони с маленькими когтями.") {
	};

	public static AbstractArmType SQUIRREL_MORPH = new AbstractArmType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("мягкий", "беличьи", "когтеобразный", "пушистая", "грызуний"),
			Util.newArrayListOfValues("мягкий", "женственная", "беличьи", "когтеобразный", "пушистая", "грызуний"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("мягкий", "когтистые", "грызуний"),
			Util.newArrayListOfValues("мягкий", "женственная", "когтистые", "грызуний"),
			"Всего за мгновение их [npc.armFullDescription] быстро покрылся слоем шерсти, и, глядя вниз, можно было увидеть, как [npc.she] [npc.verb(see)] [npc.her] новый мех на спинах [npc.her] рук, а острые маленькие коготки пробиваются наружу, заменяя [npc.her] ногти. [npc.Her] ладони стремительно превращаются в [npc.materialDescriptor] маленькие розовые подушечки, а на [npc.her] верхней части бицепса [npc.her] мех плавно переходит в [npc.skin], покрывающий остальное тело [npc.her].<br/>Когда трансформация завершается, [npc.nameIsFull] остаются антропоморфными, [style.boldSquirrelMorph(беличьими руками и кистями)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти превратились в антропоморфные, беличьи лапки с когтями.") {
	};

	public static AbstractArmType RAT_MORPH = new AbstractArmType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("мягкий", "крысиные", "когтеобразный", "пушистая", "грызуний"),
			Util.newArrayListOfValues("мягкий", "женственная", "крысиные", "когтеобразный", "пушистая", "грызуний"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("мягкий", "крысиные", "когтеобразный", "пушистая", "грызуний"),
			Util.newArrayListOfValues("мягкий", "женственная", "крысиные", "когтеобразный", "пушистая", "грызуний"),
			"Всего за мгновение их [npc.armFullDescription] быстро покрылся слоем шерсти, и, глядя вниз, можно было увидеть, как [npc.she] [npc.verb(see)] [npc.her] мех прорастает на спинах [npc.her] рук, а острые маленькие когти пробиваются наружу, заменяя [npc.her] ногти. [npc.Her] ладони стремительно превращаются в [npc.materialDescriptor] маленькие розовые подушечки, а у [npc.her] верхней части бицепсов [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальное тело [npc.her].<br/>Когда трансформация завершается, [npc.nameIsFull] остаются антропоморфными, [style.boldRatMorph(крысоподобными руками и кистями)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] кисти превратились в антропоморфные, крысоподобные лапы с когтями.") {
	};

	public static AbstractArmType RABBIT_MORPH = new AbstractArmType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			"рука",
			"руки",
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			Util.newArrayListOfValues("пушистая", "покрытая мехом"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("кроличьими", "похожая на лапу", "пушистая"),
			Util.newArrayListOfValues("мягкий", "женственная", "кроличьими", "похожая на лапу", "пушистая"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("кроличьими", "похожая на лапу", "пушистая"),
			Util.newArrayListOfValues("мягкий", "женственная", "кроличьими", "похожая на лапу", "пушистая"),
			"Всего за мгновение их покрыл слой [npc.armFullDescription], и, глядя вниз, можно было увидеть, как [npc.she] [npc.verb(see)] [npc.her] новый мех, растущий на спинах [npc.her] рук, пока тупые, кроличьи когти пробиваются наружу, заменяя [npc.her] ногти. [npc.Her] ладони стремительно превращаются в [npc.materialDescriptor] мягкие подушечки, а на [npc.her] верхней части бицепсов [npc.her] новый мех плавно переходит в [npc.skin], покрывающий остальное тело [npc.her].<br/>Когда трансформация завершается, [npc.nameIsFull] остаются антропоморфными, [style.boldRabbitMorph(кроличьими руками и лапами)], которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"[npc.She] [npc.has] [npc.armRows] руки, которые являются [npc.materialCompositionDescriptor] [npc.armFullDescription(true)]. [npc.Her] руки превратились в антропоморфные, кроличьи ладони с тупыми маленькими когтями.") {
	};

	public static AbstractArmType BAT_MORPH = new AbstractArmType(BodyCoveringType.BAT_SKIN,
			Race.BAT_MORPH,
			"крыло",
			"крылья",
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("женственная", "похожими на крылья летучей мыши"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("мягкий", "женственная", "похожими на крылья летучей мыши"),
			"Всего за мгновение их покрыл слой [npc.armFullDescription], и, в шоке глядя на [npc.her] руки, [npc.name] [npc.verb(watch)], как [npc.her] пальцы сужаются и становятся все длиннее и длиннее, пока между ними не начинает нарастать плотная кожная перепонка. [npc.She] [npc.verb(cry)] от тревоги, когда [npc.she] [npc.verb(feel)] [npc.her] кости, растущие и переламывающиеся, принимая новую форму, и через мгновение руки и предплечья [npc.her] полностью превращаются в огромные, похожие на крылышки летучей мыши крылья. Там, где раньше были [npc.her] руки, два из [npc.her] пальцев уменьшились до уровня среднего сустава [npc.her] новых придатков, оставив [npc.herHim] с двумя маленькими указательными пальцами и противопоставленным большим пальцем, каждый из которых заканчивается маленьким когтем. Там, где [npc.her] новые крылья соединяются с [npc.her] телом в районе плеча, [npc.her] [npc.armFullDescription] плавно переходит в [npc.skin], покрывающий остальную часть [npc.her] торса.<br/>[npc.Name] теперь [npc.has] огромные [style.boldBatMorph(крылья, подобные крыльям летучей мыши)] вместо рук, которые [npc.materialDescriptor] [npc.armFullDescription].",
			"Вместо рук и кистей у [npc.she] [npc.has] [npc.armRows] огромные перепончатые крылья, [npc.materialCompositionDescriptor] [npc.armFullDescription(true)].#IF(!npc.isFeral()) Там, где должны быть [npc.her] руки, [npc.she] [npc.has] два указательных пальца и большой палец, каждый из которых заканчивается маленьким тупым когтем. Хотя они чуть менее ловкие, чем человеческая кисть, [npc.sheIs] все же способны использовать [npc.her] пальцы, чтобы имитировать хват руки.#ENDIF") {
		@Override
		public boolean allowsFlight() {
			return true;
		}
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.ARM_WINGS, BodyPartTag.ARM_WINGS_LEATHERY);
		}
	};

	public static AbstractArmType HARPY = new AbstractArmType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			"крыло",
			"крылья",
			Util.newArrayListOfValues("оперенные", "птичьи"),
			Util.newArrayListOfValues("оперенные", "птичьи"),
			"кисть",
			"кисти",
			Util.newArrayListOfValues("оперенные"),
			Util.newArrayListOfValues("женственная", "оперенные"),
			"палец",
			"пальцы",
			Util.newArrayListOfValues("оперенные"),
			Util.newArrayListOfValues("женственная", "оперенные"),
			"Всего за мгновение на них быстро прорастает густой слой [npc.armFullDescription], и, взглянув вниз, [npc.she] [npc.verb(see)] [npc.her] новые перья, растущие также на спинах [npc.her] рук. Как только [npc.she] думает, что трансформация завершена, [npc.she] [npc.verb(cry)] от шока, пока [npc.her] кости растут и с хрустом принимают новую форму. К счастью, трансформация быстро заканчивается, оставляя [npc.herHim] с парой огромных пернатых крыльев вместо рук. Там, где раньше были [npc.her] руки, два из [npc.her] пальцев [npc.her] уменьшились до среднего сустава, оставляя [npc.herHim] с двумя пернатыми указательными пальцами и противопоставленным большим пальцем, каждый из которых заканчивается тупым когтем. Там, где [npc.her] новые крылья соединяются с [npc.her] телом в районе плеча, [npc.her] перья плавно покрывают переход в [npc.skin], который покрывает остальную часть [npc.her] торса.<br/>[npc.Name] теперь [npc.has] огромные [style.boldHarpy(крылья гарпии)] вместо рук, которые являются [npc.materialDescriptor] [npc.armFullDescription].",
			"Вместо рук и кистей у [npc.she] [npc.has] [npc.armRows] огромные крылья, которые [npc.materialCompositionDescriptor] прекрасны [npc.armFullDescription(true)].#IF(!npc.isFeral()) Там, где должны быть [npc.her] руки, [npc.she] [npc.has] два пернатых указательных пальца и большой палец, каждый из которых заканчивается маленьким тупым когтем. Хотя они чуть менее ловкие, чем человеческая рука, [npc.sheIs] все же способны использовать [npc.her] пальцы, чтобы имитировать хват руки.#ENDIF") {
		@Override
		public boolean allowsFlight() {
			return true;
		}
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.ARM_WINGS, BodyPartTag.ARM_WINGS_FEATHERED);
		}
	};
	
	
	private static List<AbstractArmType> allArmTypes;
	private static Map<AbstractArmType, String> armToIdMap = new HashMap<>();
	private static Map<String, AbstractArmType> idToArmMap = new HashMap<>();
	
	static {
		allArmTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("arm")) {
					try {
						AbstractArmType type = new AbstractArmType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allArmTypes.add(type);
						armToIdMap.put(type, id);
						idToArmMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("arm")) {
					try {
						AbstractArmType type = new AbstractArmType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allArmTypes.add(type);
						armToIdMap.put(type, id);
						idToArmMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded arm types:
		
		Field[] fields = ArmType.class.getFields();
		
		for(Field f : fields){
			if (AbstractArmType.class.isAssignableFrom(f.getType())) {
				
				AbstractArmType ct;
				try {
					ct = ((AbstractArmType) f.get(null));

					armToIdMap.put(ct, f.getName());
					idToArmMap.put(f.getName(), ct);
					
					allArmTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allArmTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractArmType getArmTypeFromId(String id) {
		if(id.equals("IMP")) {
			return ArmType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return ArmType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToArmMap.keySet());
		return idToArmMap.get(id);
	}
	
	public static String getIdFromArmType(AbstractArmType armType) {
		return armToIdMap.get(armType);
	}
	
	public static List<AbstractArmType> getAllArmTypes() {
		return allArmTypes;
	}
	
	private static Map<AbstractRace, List<AbstractArmType>> typesMap = new HashMap<>();
	
	public static List<AbstractArmType> getArmTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractArmType> types = new ArrayList<>();
		for(AbstractArmType type : ArmType.getAllArmTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}
