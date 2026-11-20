package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.9.1
 * @author Innoxia
 */
public class FaceType {
	
	public static AbstractFaceType HUMAN = new AbstractFaceType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			MouthType.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К счастью#IF(!npc.isPlayer())для [npc.herHim]#ENDIF, трансформация длится всего мгновение, оставляя [npc.herHim] с обычным человеческим лицом, [npc.materialDescriptor] [npc.faceSkin+].<br/>[npc.Name] теперь [npc.has] [style.boldHuman(человеческое лицо)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldHuman(человеческий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], человеческое лицо, [npc.materialDescriptor] [npc.faceFullDescription(true)].",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType ANGEL = new AbstractFaceType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			MouthType.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("идеальный", "безупречны", "ангельский"),
			Util.newArrayListOfValues("идеальный", "безупречны", "ангельский"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"К счастью#IF(!npc.isPlayer())для [npc.herHim]#ENDIF, трансформация длится всего мгновение, оставляя [npc.herHim] с ангельским, человекоподобным лицом, [npc.materialDescriptor] [npc.faceSkin+].<br/>[npc.Name] теперь [npc.has] [style.boldAngel(ангельское лицо)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldAngel(ангельский язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], ангельское лицо, [npc.materialDescriptor] [npc.faceFullDescription(true)].",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType DEMON_COMMON = new AbstractFaceType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			MouthType.DEMON_COMMON,
			null,
			null,
			Util.newArrayListOfValues("идеальный", "безупречны", "демонический"),
			Util.newArrayListOfValues("идеальный", "безупречны", "демонический"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.isShortStature())К счастью#IF(!npc.isPlayer()) для [npc.herHim]#ENDIF, трансформация длится всего мгновение, оставляя [npc.herHim] с бесовским, человекоподобным лицом, [npc.materialDescriptor] [npc.faceSkin+].<br/>[npc.Name] теперь [npc.has] [style.boldImp(бесовское лицо)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldImp(бесовский язык)].#ELSEК счастью#IF(!npc.isPlayer()) для [npc.herHim]#ENDIF, трансформация длится всего мгновение, оставляя [npc.herHim] с демоническим, человекоподобным лицом, [npc.materialDescriptor] [npc.faceSkin+].<br/>[npc.Name] теперь [npc.has] [style.boldDemon(демоническое лицо)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldDemon(демонический язык)].#ENDIF",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], #IF(npc.isShortStature())бесовское#ELSEдемоническое#ENDIF лицо, [npc.materialDescriptor] [npc.faceFullDescription(true)].",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType ALLIGATOR_MORPH = new AbstractFaceType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			MouthType.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное, похожее на аллигатора", "аллигатороподобного", "рептилия"),
			Util.newArrayListOfValues("антропоморфное, похожее на аллигатора", "аллигатороподобного", "рептилия"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную рептильную морду, а [npc.her] язык превращается в мощный, похожий на аллигаторийский язык.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается, тяжело дыша и вздыхая, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфная [style.boldAlligatorMorph(морда, похожая на аллигаторийскую)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldAlligatorMorph(мощный, похожий на аллигаторийский язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфная, аллигаторийская морда, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] с длинной плоской мордой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое представляет собой [npc.materialDescriptor] [npc.faceFullDescription(true)] с длинной плоской мордой.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_SCALY
				)){
	};

	public static AbstractFaceType BAT_MORPH = new AbstractFaceType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			MouthType.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфный, похожий на летучую мышь", "похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("антропоморфный, похожий на летучую мышь", "похожими на крылья летучей мыши"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную мордочку, похожую на летучью мышь, а [npc.her] язык превращается в тонкий, как у летучей мыши. #IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо #ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+] #ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфным [style.boldBatMorph(лицом, похожим на морду летучей мыши)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldBatMorph(тонкий, как у летучей мыши язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное лицо, похожее на морду летучей мыши, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] с короткой мордочкой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и имеет короткую мордочку.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType CAT_MORPH = new AbstractFaceType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			MouthType.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное, кошачье", "кошачьими", "кошачьими"),
			Util.newArrayListOfValues("антропоморфное, кошачье", "кошачьими", "кошачьими"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную кошачью морду, а [npc.her] язык уплощается и превращается в кошачий.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро разрастается, чтобы покрыть [npc.her] новое лицо#ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается, тяжело дыша и вздыхая, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфным [style.boldCatMorph(кошачьим лицом)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldCatMorph(плоский, кошачий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное, кошачье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)], дополненное милой маленькой кошачьей мордочкой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и дополнено милой маленькой кошачьей мордочкой.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

//	public static AbstractFaceType CAT_MORPH_PANTHER = new AbstractFaceType(BodyCoveringType.FELINE_FUR,
//			Race.CAT_MORPH,
//			MouthType.CAT_MORPH,
//			null,
//			null,
//			Util.newArrayListOfValues("anthropomorphic panther-like", "panther-like", "panther"),
//			Util.newArrayListOfValues("anthropomorphic panther-like", "panther-like", "panther"),
//			"nose",
//			"noses",
//			Util.newArrayListOfValues(""),
//			Util.newArrayListOfValues(""),
//			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic panther-like muzzle, and [npc.her] tongue flattens and transforms into a cat-like one."
//				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
//					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
//				+ "#ELSE"
//					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
//				+ "#ENDIF"
//					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
//				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldCatMorph(panther-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
//				+ " Within [npc.her] [npc.mouth], [npc.sheHasFull] a [style.boldCatMorph(flat, cat-like tongue)].",
//			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, panther-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a powerful, toothy muzzle, big nose, and strong jawline.",
//			"[npc.SheHasFull] the [npc.feminineDescriptor(true)] face of a feral [npc.legRace], which is [npc.materialDescriptor] [npc.faceFullDescription(true)] and complete with a powerful, toothy muzzle, big nose, and strong jawline.",
//			Util.newArrayListOfValues(
//					BodyPartTag.FACE_MUZZLE,
//					BodyPartTag.FACE_FANGS,
//					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
//				)){
//	};

	public static AbstractFaceType COW_MORPH = new AbstractFaceType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			MouthType.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное коровье", "коровьей формы", "бычий"),
			Util.newArrayListOfValues("антропоморфное коровье", "коровьей формы", "бычий"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную бычью морду, а [npc.her] язык превращается в мощный, коровьи язык.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Как и все остальное тело [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфным [style.boldCowMorph(бычьим лицом)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldCowMorph(сильный, коровьи язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное, бычье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] и с бычьей мордой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и с бычьей мордой.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType DOG_MORPH = new AbstractFaceType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			MouthType.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное, собакоподобное", "собачьи", "пёсьи"),
			Util.newArrayListOfValues("антропоморфное, собакоподобное", "собачьи", "пёсьи"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную собачью морду, а [npc.her] язык расплющивается и становится шире, превращаясь в собачий.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Как и все остальное тело [npc.her], [npc.her] новое лицо — это [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфным [style.boldDogMorph(собачьим лицом)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldDogMorph(плоский, собачий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное, собачье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] и с полностью сформированной собачьей мордой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое является [npc.materialDescriptor] [npc.faceFullDescription(true)] и имеет полностью сформированную собачью морду.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType FOX_MORPH = new AbstractFaceType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			MouthType.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное лисье", "лисоподобные"),
			Util.newArrayListOfValues("антропоморфное лисье", "лисоподобные"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную лисью морду, а [npc.her] язык расплющивается и превращается в лисий.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфное [style.boldFoxMorph(лисье лицо)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldFoxMorph(плоский, лисий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное лисье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)], дополненное изящной лисьей мордочкой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикой [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и дополнено изящной лисьей мордочкой.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType HARPY = new AbstractFaceType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			MouthType.HARPY,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное птицеподобное", "птичьи"),
			Util.newArrayListOfValues("антропоморфное птицеподобное", "птичьи"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, сливаясь воедино и вытягиваясь в короткий клюв, а [npc.her] язык истончается, становясь птичьим.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо,#ELSE Подобно остальному телу [npc.her], новое лицо [npc.her] [npc.materialDescriptor] [npc.faceSkin+],#ENDIF и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух. [npc.She] [npc.verb(find)], к огромному облегчению [npc.her], что [npc.sheIs] может по своему желанию делать края [npc.her] клюва тверже или мягче, что позволяет [npc.herHim] выражать мимику, а также обхватывать краями [npc.her] клюва всё, что [npc.she] захочет засунуть в [npc.her] рот.<br/>[npc.Name] теперь [npc.has] антропоморфная [style.boldHarpy(птичье лицо)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldHarpy(тонкий, птичий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное, птичье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)], дополненное клювом.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] морда дикого [npc.legRace], которая является [npc.materialDescriptor] [npc.faceFullDescription(true)] и снабжена клювом.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_BEAK,
					BodyPartTag.FACE_NATURAL_BALDNESS_AVIAN
				)){
	};

	public static AbstractFaceType HORSE_MORPH = new AbstractFaceType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			MouthType.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфно-лошадиной", "лошадиный", "лошадиный"),
			Util.newArrayListOfValues("антропоморфно-лошадиной", "лошадиный", "лошадиный"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, выпячиваясь в антропоморфную лошадиную морду, а [npc.her] язык превращается в мощный, лошадиный.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Подобно остальной части тела [npc.her], [npc.her] новое лицо является [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфной [style.boldHorseMorph(лошадиной мордой)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldHorseMorph(сильный, лошадиный язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфная, лошадиная морда [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] и снабженная длинной лошадиной мордой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] морда дикого [npc.legRace], которая является [npc.materialDescriptor] [npc.faceFullDescription(true)] и снабжена длинной лошадиной мордой.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY // Note: Some horse races only have hair on the neck aka a mane so its not totally unnatural to have a bald face
				)){
	};

	public static AbstractFaceType RABBIT_MORPH = new AbstractFaceType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			MouthType.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфная заячья", "кроличьими"),
			Util.newArrayListOfValues("антропоморфная заячья", "кроличьими"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную заячью мордочку, а [npc.her] язык превращается в тонкий, заячий.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо — [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфным [style.boldRabbitMorph(заячьим лицом)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldRabbitMorph(тонкий, заячий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное заячье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)], дополненное короткой мордочкой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и имеет короткую мордочку.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType RAT_MORPH = new AbstractFaceType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			MouthType.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфная крысиная", "крысиные", "грызуний"),
			Util.newArrayListOfValues("антропоморфная крысиная", "крысиные", "грызуний"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную крысиную морду, а [npc.her] язык превращается в тонкий, крысиный язык.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается, тяжело дыша и вздыхая, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфная [style.boldRatMorph(крысиная морда)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldRatMorph(тонкий, крысиный язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное крысиное лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] с длинной грызуньей мордой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и представляет собой длинную грызунью морду.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType REINDEER_MORPH = new AbstractFaceType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			MouthType.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное, похожее на оленье", "похожих на оленьи", "оленьей шерсти"),
			Util.newArrayListOfValues("антропоморфное, похожее на оленье", "похожих на оленьи", "оленьей шерсти"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную оленью морду, а [npc.her] язык превращается в мощный, похожий на олений язык.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфная [style.boldReindeerMorph(лошадино-оленья морда)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldReindeerMorph(сильный, олений язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное, оленье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] и с длинной мордой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и с длинной мордой.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType SQUIRREL_MORPH = new AbstractFaceType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			MouthType.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфное, похожее на беличье", "беличьи", "грызуний"),
			Util.newArrayListOfValues("антропоморфное, похожее на беличье", "беличьи", "грызуний"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную беличью мордочку, а [npc.her] язык превращается в тонкий, как у белки.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Как и все остальное тело [npc.her], [npc.her] новое лицо — [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышащей и вздыхающей, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфная [style.boldSquirrelMorph(беличья мордочка)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] [style.boldSquirrelMorph(тонкий, беличий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфная беличья мордочка, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)], дополненная короткой мордочкой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и имеет короткую мордочку.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType WOLF_MORPH = new AbstractFaceType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			MouthType.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues("антропоморфная волчья", "волчьими"),
			Util.newArrayListOfValues("антропоморфная волчья", "волчьими"),
			"нос",
			"носы",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her] нос и рот дергаются и трансформируются, вытягиваясь в антропоморфную волчью морду, а [npc.her] язык расплющивается и становится шире, превращаясь в волчий.#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH) Слой [npc.faceSkin+] быстро нарастает, покрывая [npc.her] новое лицо#ELSE Подобно остальному телу [npc.her], [npc.her] новое лицо [npc.materialDescriptor] [npc.faceSkin+]#ENDIF, и когда трансформация наконец завершается, [npc.sheIs] остается тяжело дышать и вздыхать, пока [npc.she] [npc.verb(try)], чтобы перевести [npc.her] дух.<br/>[npc.Name] теперь [npc.has] антропоморфным [style.boldWolfMorph(волчьим лицом)], [npc.materialDescriptor] [npc.faceFullDescription]. Внутри [npc.her] [npc.mouth], [npc.sheHasFull] виден [style.boldWolfMorph(плоский, волчий язык)].",
			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], антропоморфное волчье лицо, [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)], дополненное длинной мордой.",
			"[npc.SheHasFull] [npc.feminineDescriptor(true)] лицо дикого [npc.legRace], которое [npc.materialDescriptor] [npc.faceFullDescription(true)] и с длинной мордой.",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};
	

	private static List<AbstractFaceType> allFaceTypes;
	private static Map<AbstractFaceType, String> faceToIdMap = new HashMap<>();
	private static Map<String, AbstractFaceType> idToFaceMap = new HashMap<>();
	
	static {
		allFaceTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("face")) {
					try {
						AbstractFaceType type = new AbstractFaceType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFaceTypes.add(type);
						faceToIdMap.put(type, id);
						idToFaceMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("face")) {
					try {
						AbstractFaceType type = new AbstractFaceType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFaceTypes.add(type);
						faceToIdMap.put(type, id);
						idToFaceMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded face types:
		
		Field[] fields = FaceType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFaceType.class.isAssignableFrom(f.getType())) {
				
				AbstractFaceType ct;
				try {
					ct = ((AbstractFaceType) f.get(null));

					faceToIdMap.put(ct, f.getName());
					idToFaceMap.put(f.getName(), ct);
					
					allFaceTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allFaceTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractFaceType getFaceTypeFromId(String id) {
		if(id.equals("IMP")) {
			return FaceType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return FaceType.WOLF_MORPH;
		}
		if(id.equals("TENGU")) {
			return FaceType.HARPY;
		}
		if(id.equals("CAT_MORPH_PANTHER")) {
			id = "innoxia_panther_face";
		}
		
		id = Util.getClosestStringMatch(id, idToFaceMap.keySet());
		return idToFaceMap.get(id);
	}
	
	public static String getIdFromFaceType(AbstractFaceType faceType) {
		return faceToIdMap.get(faceType);
	}
	
	public static List<AbstractFaceType> getAllFaceTypes() {
		return allFaceTypes;
	}
	
	private static Map<AbstractRace, List<AbstractFaceType>> typesMap = new HashMap<>();
	
	public static List<AbstractFaceType> getFaceTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractFaceType> types = new ArrayList<>();
		for(AbstractFaceType type : FaceType.getAllFaceTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}