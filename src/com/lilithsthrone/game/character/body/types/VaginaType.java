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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.8.9
 * @author Innoxia
 */
public class VaginaType {

	public static AbstractVaginaType NONE = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HUMAN,
			Race.NONE,
			false,
			"[npc.She] стонет и ерзает, когда [npc.she] [npc.verb(feel)] [npc.her] киска внезапно сжимается и плотно смыкается, и с отчаянным криком странное давление проносится сквозь [npc.her] низ живота. К счастью, это ощущение исчезает почти так же быстро, как и появилось, и когда дискомфорт утихает, [npc.she] [npc.verb(gasp)], осознавая, что [npc.she] внезапно [npc.verb(realise)], будто [npc.her] вагина [npc.has] полностью исчезла!<br/>[npc.Name] [npc.has] [style.boldSex(потерянная [npc.her] вагина)].",
			"никакой киски.", // Shouldn't need a description of no vagina.
			null) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				owner.setHymen(true);
				owner.setPiercedVagina(false);
				if(owner.isPlayer() && owner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(!owner.isVaginaVirgin()) {
						return UtilText.parse(owner,
								"<p style='text-align:center;'>[style.boldGood(Непорочная дева)]<br/><i>Когда [npc.her] осознает реальность потери своей никчемной щели, [npc.name] [npc.verb(start)] перестает воспринимать [npc.herself] как нечто большее, чем просто грязную шлюху. В конце концов, если у [npc.she] [npc.do] нет киски, то технически [npc.herHim] не может была потерять [npc.her] девственность! Тяжело вздохнув, [npc.she] [npc.verb(manage)] пытается убедить [npc.herself], что [npc.she] больше не [npc.has] беспокоиться о том, что она — «испорченная» девственница.<br/>Несмотря на то, что она наконец-то может снова видеть в [npc.herself] достойную личность, тот факт, что [npc.sheIs] лишена привычного ощущения наличия киски, заставляет [npc.her] нервничать. Возможно, если бы [npc.she] [npc.was] удалось отрастить новую киску, [npc.she] снова обрела бы нетронутую плеву, и тем самым [npc.herHim] вновь стала бы чистой «девственницей»...</i><br/>[npc.NameIsFull] [style.boldGood(больше не испорченная девственница)]!</p>");
					} else {
						return UtilText.parse(owner,
								"<p style='text-align:center;'>[style.boldBad(Чистая дева?)]<br/><i>Когда осознание потери [npc.her] влагалища проникает в сознание, [npc.name] [npc.verb(start)] ощущать нарастающую панику в глубине [npc.her] мыслей. В конце концов, если у [npc.she] [npc.do]нет киски, то [npc.she] нельзя считать чистой девой!</i><br/>Пока [npc.name] не сможет [npc.verb(regain)] [npc.her] потерянное влагалище, [npc.she] будет [style.boldBad(больше не считаться чистой девой)]!</p>");
					}
				}
			}
			return "";
		}
	};
	
	public static AbstractVaginaType ONAHOLE = new AbstractVaginaType(BodyCoveringType.DILDO,
			FluidType.GIRL_CUM_HUMAN,
			Race.NONE,
			false,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("игрушка", "искусственный"),
			"Вы каким-то образом превратили свою киску в онахоле — это баг. (Пожалуйста, сообщите об этом Innoxia!)", // Onaholes are not a transformable option
			"[npc.she] [npc.has] онахоле, вставленный в [npc.her] киску, которая представляет собой [npc.vaginaFullDescription(true)].",
			null) {
	};

	public static AbstractVaginaType HUMAN = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HUMAN,
			Race.HUMAN,
			false,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.She] [npc.verb(let)] от восторга, когда волна удовольствия пробегает по [npc.her] паху, усиливаясь, пока [npc.she] [npc.verb(feel)] [npc.her] щель бесконтрольно сжимается и пульсирует. Спустя мгновение ощущение начинает угасать, и с удивленным вздохом [npc.she] [npc.verb(discover)], что [npc.her] киска [npc.has] приняла форму обычной человеческой вагины. Теплое, покалывающее чувство разливается по [npc.her] нижней части живота, и по завершении трансформации [npc.she] [npc.verb(realise)], что [npc.her] внутренние репродуктивные органы также трансформировались в человеческие.<br/>[npc.Name] теперь [npc.has] [style.boldHuman(человеческая вагина)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая,#ELSEобычная#ENDIF человеческая киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};

	public static AbstractVaginaType ANGEL = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_ANGEL,
			Race.ANGEL,
			false,
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues("ангельский-"),
			Util.newArrayListOfValues("неотразимый", "идеальный"),
			"[npc.She] [npc.verb(let)] с визгом восторга, когда волна удовольствия проходит сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель, которая неконтролируемо смещается и сокращается. Спустя мгновение ощущение начинает угасать, и с удивленным вздохом [npc.she] [npc.verb(discover)], что [npc.her] киска [npc.has] приняла форму абсолютно идеальной ангельской вагины. Теплое, покалывающее чувство разливается по нижней части живота [npc.her], и когда трансформация завершается, [npc.she] [npc.verb(realise)], что [npc.her] внутренние репродуктивные органы также превратились в ангельские.<br/>[npc.Name] теперь [npc.has] [style.boldAngel(ангельская вагина)], с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая,#ELSEангельская#ENDIF киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			Util.newArrayListOfValues()) {
	};

	public static AbstractVaginaType DEMON_COMMON = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_DEMON,
			Race.DEMON,
			false,
			Util.newArrayListOfValues("суккуб-"),
			Util.newArrayListOfValues("суккуб-"),
			Util.newArrayListOfValues("неотразимый", "идеальный"),
			"[npc.She] [npc.verb(let)] издает радостный визг, когда волна удовольствия пробегает по паху [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно содрогается и сжимается. Странное, бурлящее ощущение начинает спускаться глубоко в [npc.her] киску, и [npc.she] [npc.verb(let)] издает похотливый стон, когда [npc.she] [npc.verb(feel)] новый набор мышц формируется вдоль внутренних стенок [npc.her] киски. С экспериментальным сжатием [npc.she] быстро [npc.verb(discover)], что [npc.she] [npc.has] обладает невероятным контролем над новыми мышцами [npc.her] киски. С последней дрожью наслаждения [npc.her] киска меняет свою внешнюю форму, превращаясь в абсолютно идеальную вагину.</p><p>Как раз в тот момент, когда [npc.she] [npc.verb(start)] кажется, что трансформация [npc.has] подошла к концу, новые мышцы [npc.her] киски непроизвольно сжимаются, и отчаянный визг вырывается из глубин [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами проносятся образы толстых демонических членов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг превращается в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их горячее, мужское семя, вливающееся глубоко в [npc.her] демоническую утробу. Так же быстро, как и появились, образы исчезают из [npc.her] сознания, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>#IF(npc.isShortStature())[npc.Name] теперь [npc.has] [style.boldImp(impish vagina)], с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.#ELSE[npc.Name] теперь [npc.has] [style.boldDemon(demonic vagina)], с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.#ENDIF",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая,#ELSE#ENDIF демоническая киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			Util.newArrayListOfValues(
				OrificeModifier.MUSCLE_CONTROL)) {
	};

//	public static AbstractVaginaType DEMON_EGGS = new AbstractVaginaType(BodyCoveringType.VAGINA,
//			FluidType.GIRL_CUM_DEMON,
//			Race.DEMON,
//			true,
//			Util.newArrayListOfValues("succubus-"),
//			Util.newArrayListOfValues("succubus-"),
//			Util.newArrayListOfValues("irresistible", "perfect"),
//			"[npc.She] [npc.verb(let)] out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, which increases in intensity as [npc.she] [npc.verb(feel)] [npc.her] slit uncontrollably shifting and contracting."
//					+ " A strange, bubbling sensation starts running down deep into [npc.her] cunt,"
//						+ " and [npc.she] [npc.verb(let)] out a lewd moan as [npc.she] [npc.verb(feel)] a new set of muscles forming all along the inner-walls of [npc.her] pussy."
//					+ " With an experimental squeeze, [npc.she] quickly [npc.verb(discover)] that [npc.she] [npc.has] an incredible amount of control over [npc.her] pussy's new muscles."
//					+ " With one last shiver of pleasure, [npc.her] pussy reshapes its exterior into an absolutely perfect-looking vagina."
//				+ "</p>"
//				+ "<p>"
//					+ "Just as [npc.she] [npc.verb(start)] think that the transformation [npc.has] come to an end, [npc.her] pussy's new muscles involuntarily clench down,"
//						+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
//					+ " Images of fat demonic cocks slamming deep into [npc.her] new cunt flash before [npc.her] eyes,"
//						+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] [npc.verb(imagine)] them pumping their hot, virile seed deep into [npc.her] demonic womb."
//					+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
//						+ " [npc.she] [npc.verb(feel)] [npc.her] female reproductive organs finishing their transformation."
//					+ "<br/>"
//					+ "#IF(npc.isShortStature())"
//						+ "[npc.Name] now [npc.has] an [style.boldImp(impish, egg-laying vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
//					+ "#ELSE"
//						+ "[npc.Name] now [npc.has] a [style.boldDemon(demonic, egg-laying vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
//					+"#ENDIF",
//			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())a pierced,#ELSEa#ENDIF demonic, egg-laying pussy, with [npc.labiaSize], [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.",
//			Util.newArrayListOfValues(
//				OrificeModifier.MUSCLE_CONTROL)) {
//		@Override
//		public String getTransformName() {
//			return "demonic (egg-laying)";
//		}
//	};

	public static AbstractVaginaType DOG_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_DOG_MORPH,
			Race.DOG_MORPH,
			false,
			Util.newArrayListOfValues("собачий-", "сучья-"),
			Util.newArrayListOfValues("собачий-", "сучья-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно дергается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает отчетливую собачью форму, раздувшись и перестроившись.</p><p>Как раз в тот момент, когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами проносятся образы красных, узловатых собачьих членов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих свое мощное семя глубоко в [npc.her] собачью утробу. Так же быстро, как и появились, образы исчезают из [npc.her] мыслей, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldDogMorph(собачья киска)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая,#ELSE#ENDIF псина, с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};

	public static AbstractVaginaType WOLF_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_WOLF_MORPH,
			Race.WOLF_MORPH,
			false,
			Util.newArrayListOfValues("волчья-"),
			Util.newArrayListOfValues("волчья-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] щель [npc.her] беспорядочно дергается и сокращается. Спустя мгновение ощущение утихает, и [npc.she] [npc.verb(discover)] видит, что [npc.her] киска [npc.has] перестроилась и раздулась, приняв отчетливую волчью форму.</p><p>Как раз в тот момент, когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из глубин [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы красных, узловатых волчьих членов, с силой вбивающихся в [npc.her] новую киску, и [npc.her] визг переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] чувствует, как они впрыскивают свое мощное семя глубоко в [npc.her] волчью утробу. Так же быстро, как и появились, образы исчезают из [npc.her] мыслей, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] понимает, что [npc.her] женские репродуктивные органы завершили свое превращение.<br/>[npc.Name] теперь [npc.has] представляет собой [style.boldWolfMorph(волчью киску)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF волчья киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};
	
	public static AbstractVaginaType FOX_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_FOX_MORPH,
			Race.FOX_MORPH,
			false,
			Util.newArrayListOfValues("лисья-", "лисичья-"),
			Util.newArrayListOfValues("лисья-", "лисичья-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] исторгает радостный визг, когда волна удовольствия пробегает по паху [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно дергается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] понимает, что [npc.her] киска [npc.has] перестроилась и раздулась, приняв отчетливую лисью форму.</p><p>Только [npc.she] [npc.verb(start)] успевает подумать, что трансформация [npc.has] подошла к концу, как [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы красных, узловатых лисьих пенисов, с силой вонзающихся в [npc.her] новую киску, и [npc.her] визг переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] чувствует, как они вливают свое мощное семя глубоко в [npc.her] лисью утробу. Так же быстро, как и появились, образы исчезают из [npc.her] мыслей, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] осознает, что [npc.her] женские репродуктивные органы завершили свою трансформацию.<br/>[npc.Name] теперь [npc.has] представляет собой [style.boldFoxMorph(лисью киску)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF лисья киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};
	
	public static AbstractVaginaType CAT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_CAT_MORPH,
			Race.CAT_MORPH,
			false,
			Util.newArrayListOfValues("кошачья-"),
			Util.newArrayListOfValues("кошачья-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] с визгом восторга, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно дергается и сокращается. Спустя мгновение чувство угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает отчетливую кошачью форму.</p><p>Как раз когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы зазубренных кошачьих пенисов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг превращается в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих свое мощное семя глубоко в [npc.her] кошачью утробу. Так же быстро, как и появились, образы исчезают из [npc.her] сознания, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldCatMorph(кошачья киска)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF кошачья киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};

	public static AbstractVaginaType SQUIRREL_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_SQUIRREL_MORPH,
			Race.SQUIRREL_MORPH,
			false,
			Util.newArrayListOfValues("беличий-"),
			Util.newArrayListOfValues("беличий-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает радостный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно дергается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает отчетливую беличью форму.</p><p>Как раз когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы массивных беличьих членов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг превращается в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих свое мощное семя глубоко в [npc.her] новую утробу. Так же быстро, как и появились, образы исчезают из [npc.her] мыслей, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldSquirrelMorph(беличья киска)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF беличья киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};

	public static AbstractVaginaType RAT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_RAT_MORPH,
			Race.RAT_MORPH,
			false,
			Util.newArrayListOfValues("крысиный-"),
			Util.newArrayListOfValues("крысиный-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно смещается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает отчетливую крысиную форму.</p><p>Как раз когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы пульсирующих крысиных пенисов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг превращается в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих свое мощное семя глубоко в [npc.her] грызунью утробу. Так же быстро, как и появились, образы исчезают из [npc.her] сознания, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldRatMorph(крысиная киска)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF крысиная киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};

	public static AbstractVaginaType RABBIT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_RABBIT_MORPH,
			Race.RABBIT_MORPH,
			false,
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues("кроличий-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] щель [npc.her] беспорядочно смещается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] понимает, что [npc.her] киска [npc.has] приняла отчетливую заячью форму.</p><p>Как раз в тот момент, когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы толстых кроличьих членов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг превращается в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] чувствует, как они впрыскивают свое мощное семя глубоко в [npc.her] новую утробу. Так же быстро, как и появились, образы исчезают из [npc.her] мыслей, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] осознает, что [npc.her] женские репродуктивные органы завершили свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldRabbitMorph(заячья киска)], с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF кроличья киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};

	public static AbstractVaginaType BAT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_BAT_MORPH,
			Race.BAT_MORPH,
			false,
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues("летучий-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] с визгом восторга, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно смещается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает отчетливую форму летучей мыши.</p><p>Как раз когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы пульсирующих летучих пенисов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих горячее семя глубоко в [npc.her] новую утробу. Так же быстро, как и появились, образы исчезают из [npc.her] сознания, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldBatMorph(вагина летучей мыши)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF летучая киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};
	
	public static AbstractVaginaType ALLIGATOR_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_ALLIGATOR_MORPH,
			Race.ALLIGATOR_MORPH,
			true,
			Util.newArrayListOfValues("аллигаторийский-"),
			Util.newArrayListOfValues("аллигаторийский-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно смещается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает отчетливую форму аллигатора.</p><p>Как раз когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы жирных аллигачьих членов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг превращается в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих горячее семя глубоко в [npc.her] новую матку. Так же быстро, как и появились, образы исчезают из [npc.her] сознания, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldAlligatorMorph(alligator vagina)], с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSEобычная#ENDIF аллигаторова киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};

	public static AbstractVaginaType COW_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_COW_MORPH,
			Race.COW_MORPH,
			false,
			Util.newArrayListOfValues("коровий-"),
			Util.newArrayListOfValues("коровий-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает по паху [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно дергается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает отчетливую коровью форму.</p><p>Как раз когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-под [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами мелькают образы жирных бычьих членов, с силой вбивающихся в [npc.her] новую киску, и [npc.her] визг переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих горячее семя глубоко в [npc.her] новую утробу. Так же быстро, как и появились, образы исчезают из [npc.her] сознания, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldCowMorph(коровья киска)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF киска, с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};

	public static AbstractVaginaType HORSE_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HORSE_MORPH,
			Race.HORSE_MORPH,
			false,
			Util.newArrayListOfValues("#IF(npc.getRace()==npc.getVaginaRace())[npc.raceFeral]#ELSE[npc.pussyRaceFeral]#ENDIF-", "кобылий-"),
			Util.newArrayListOfValues("#IF(npc.getRace()==npc.getVaginaRace())[npc.raceFeral]#ELSE[npc.pussyRaceFeral]#ENDIF-", "кобылий-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] щель [npc.her] непроизвольно смещается и сокращается. Странное, бурлящее ощущение начинает спускаться глубоко в [npc.her] киску, и [npc.she] [npc.verb(let)] похотливый стон, когда [npc.she] [npc.verb(feel)] новый набор мышц, формирующийся вдоль внутренних стенок [npc.her] киски. С экспериментальным сжатием [npc.she] быстро [npc.verb(discover)], что [npc.she] [npc.has] невероятный контроль над новыми мышцами [npc.her] киски. Через мгновение чувство угасает, и [npc.she] [npc.verb(discover)], что [npc.her] киска [npc.has] перестроилась и раздулась, приняв отчетливую лошадиную форму.</p><p>Как раз в тот момент, когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-за [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами проносятся образы раздутых жеребцовых членов, глубоко вбивающихся в [npc.her] новую киску, и визг [npc.her] переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, вливающих горячее семя глубоко в [npc.her] новую матку. Так же быстро, как и появились, образы исчезают из [npc.her] сознания, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldHorseMorph(лошадиная киска)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF лошадиная киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY,
				OrificeModifier.MUSCLE_CONTROL)) {
	};

	public static AbstractVaginaType REINDEER_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_REINDEER_MORPH,
			Race.REINDEER_MORPH,
			false,
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues("олений-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] с визгом восторга, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно дергается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] то, что [npc.her] киска [npc.has], принимает новую форму, раздуваясь в отчетливый олений вид.</p><p>Как раз в тот момент, когда [npc.she] [npc.verb(start)] кажется, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-под [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами вспыхивают образы пульсирующих оленьих членов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] их, впрыскивающих горячее семя глубоко в [npc.her] новую матку. Так же быстро, как и появились, образы исчезают из [npc.her] мыслей, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] [npc.her] женские репродуктивные органы завершают свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldReindeerMorph(reindeer vagina)], с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF оленья киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};

	public static AbstractVaginaType HARPY = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HARPY,
			Race.HARPY,
			true,
			Util.newArrayListOfValues("птичья-"),
			Util.newArrayListOfValues("птичья-"),
			Util.newArrayListOfValues("горячая"),
			"[npc.She] [npc.verb(let)] издает восторженный визг, когда волна удовольствия пробегает сквозь пах [npc.her], интенсивность которой нарастает, пока [npc.she] [npc.verb(feel)] [npc.her] щель беспорядочно дергается и сокращается. Спустя мгновение ощущение угасает, и [npc.she] [npc.verb(discover)] понимает, что [npc.her] киска [npc.has] приняла отчетливую птичью форму.</p><p>Как раз в тот момент, когда [npc.she] [npc.verb(start)] думает, что трансформация [npc.has] подошла к концу, [npc.her] киска непроизвольно сжимается, и отчаянный визг вырывается из-под [npc.her] [npc.lips+], когда теплое, покалывающее чувство разливается по [npc.her] нижней части живота. Перед [npc.her] глазами проносятся образы пульсирующих птичьих пенисов, глубоко вбивающихся в [npc.her] новую киску, и [npc.her] визг переходит в удовлетворенный стон, когда [npc.she] [npc.verb(imagine)] чувствует, как они закачивают свое горячее семя глубоко в [npc.her] птичью утробу. Так же быстро, как и появились, образы исчезают из [npc.her] мыслей, и когда последняя волна покалывающего удовольствия омывает [npc.her] тело, [npc.she] [npc.verb(feel)] осознает, что [npc.her] женские репродуктивные органы завершили свою трансформацию.<br/>[npc.Name] теперь [npc.has] [style.boldHarpy(птичья киска)] с [npc.pussyColourPrimary(true)] половыми губами и [npc.pussyColourSecondary(true)] внутренними стенками.",
			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())проколотая#ELSE#ENDIF птичья киска с [npc.labiaSize], [npc.pussyPrimaryColour(true)] половыми губами и [npc.pussySecondaryColour(true)] внутренними стенками.",
			null) {
	};
	
	
	private static List<AbstractVaginaType> allVaginaTypes;
	private static Map<AbstractVaginaType, String> vaginaToIdMap = new HashMap<>();
	private static Map<String, AbstractVaginaType> idToVaginaMap = new HashMap<>();
	
	static {
		allVaginaTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("vagina")) {
					try {
						AbstractVaginaType type = new AbstractVaginaType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allVaginaTypes.add(type);
						vaginaToIdMap.put(type, id);
						idToVaginaMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("vagina")) {
					try {
						AbstractVaginaType type = new AbstractVaginaType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allVaginaTypes.add(type);
						vaginaToIdMap.put(type, id);
						idToVaginaMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded vagina types:
		
		Field[] fields = VaginaType.class.getFields();
		
		for(Field f : fields){
			if (AbstractVaginaType.class.isAssignableFrom(f.getType())) {
				
				AbstractVaginaType ct;
				try {
					ct = ((AbstractVaginaType) f.get(null));

					vaginaToIdMap.put(ct, f.getName());
					idToVaginaMap.put(f.getName(), ct);
					
					allVaginaTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allVaginaTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractVaginaType getVaginaTypeFromId(String id) {
		if(id.equals("IMP") || id.equals("DEMON_EGGS")) {
			return VaginaType.DEMON_COMMON;
		}
		if(id.equals("NoStepOnSnek_snake_vagina_e")) {
			id = "NoStepOnSnek_snake_vagina";
		}
		id = Util.getClosestStringMatch(id, idToVaginaMap.keySet());
		return idToVaginaMap.get(id);
	}
	
	public static String getIdFromVaginaType(AbstractVaginaType vaginaType) {
		return vaginaToIdMap.get(vaginaType);
	}
	
	public static List<AbstractVaginaType> getAllVaginaTypes() {
		return allVaginaTypes;
	}
	
	private static Map<AbstractRace, List<AbstractVaginaType>> typesMap = new HashMap<>();
	
	public static List<AbstractVaginaType> getVaginaTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractVaginaType> types = new ArrayList<>();
		for(AbstractVaginaType type : VaginaType.getAllVaginaTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}