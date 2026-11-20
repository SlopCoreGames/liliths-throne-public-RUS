package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class TailType {
	
	public static final AbstractTailType NONE = new AbstractTailType(
			null,
			Race.NONE,
			PenetrationGirth.THREE_AVERAGE,
			0f,
			"нет",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"",
			"",
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(),
			"#IF(npc.getTailCount()==1) [npc.She] ахает, когда [npc.she] чувствует, как [npc.her] [npc.tail] уменьшается и исчезает в [npc.her] пояснице.@ELSE [npc.She] ахает, когда [npc.she] чувствует, как [npc.her] [npc.tails] уменьшается и исчезает в [npc.her] пояснице.#ENDIF<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(без хвоста)].",
			"[style.colourDisabled([npc.She] [npc.do] нет хвоста.)]",
			Util.newArrayListOfValues(), false) {
	};
	
	public static final AbstractTailType DEMON_COMMON = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.ONE_SLENDER,
			1f,
			"демонический лопатообразный",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("чешуйчатый", "демонический"),
			Util.newArrayListOfValues("чешуйчатый", "демонический"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("чешуйчатый"),
			Util.newArrayListOfValues("чешуйчатый"),
			"#IF(npc.getTailCount()==1) Из области чуть выше [npc.her] задницы прорастает демонический хвост с кисточкой-лопастью, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует каждое его движение, позволяя [npc.herHim] использовать его как третью конечность.<br/>[npc.Name] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp(бесовский хвост)]#ELSE [style.boldDemon(демонический хвост)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Из области чуть выше [npc.her] задницы прорастают демонические хвосты с кисточками-лопастями, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует каждое их движение, позволяя [npc.herHim] использовать их как дополнительные конечности.<br/>[npc.Name] теперь [npc.has] [npc.tailCount]#IF(npc.isShortStature()) [style.boldImp(бесовские хвосты)]#ELSE [style.boldDemon(демонические хвосты)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) лопатообразный, [npc.tailColour(true)] #IF(npc.isShortStature())бесовский#ELSEдемонический#ENDIF хвост, над которым [npc.sheHasFull] полный контроль, позволяющий [npc.herHim] использовать его, чтобы хватать и удерживать предметы.#ELSE [npc.tailCount] лопатообразных, [npc.tailColour(true)] #IF(npc.isShortStature())бесовских#ELSEдемонических#ENDIF хвоста, над которыми [npc.sheHasFull] полный контроль, позволяющий [npc.herHim] использовать их, чтобы хватать и удерживать предметы.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_EXPONENTIAL), false) {
	};
	
	public static final AbstractTailType DEMON_HAIR_TIP = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.ONE_SLENDER,
			0.5f,
			"демонический с кисточкой на конце",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("с кисточкой на конце", "демонический"),
			Util.newArrayListOfValues("с кисточкой на конце", "демонический"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("с пушистым кончиком"),
			Util.newArrayListOfValues("с пушистыми кончиками"),
			"#IF(npc.getTailCount()==1) Демонический хвост с пучком волос на конце прорастает чуть выше [npc.her] задницы, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует его движения, позволяя [npc.herHim] использовать его как третью конечность.<br/>[npc.Name] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp(бесовский хвост)]#ELSE [style.boldDemon(демонический хвост)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)] и с кончиком из [#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)].#ELSE [npc.TailCount] демонических хвоста, с пучками волос на концах, прорастают чуть выше [npc.her] задницы, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует их движения, позволяя [npc.herHim] использовать их как дополнительные конечности.<br/>[npc.Name] теперь [npc.has] [npc.tailCount]#IF(npc.isShortStature()) [style.boldImp(бесовские хвосты)]#ELSE [style.boldDemon(демонические хвосты)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)] и с кончиками из [#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) [npc.tailColour(true)] #IF(npc.isShortStature())бесовский#ELSEдемонический#ENDIF хвост с кончиком [#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)], над которым [npc.sheHasFull] имеет полный контроль, что позволяет [npc.herHim] использовать его, чтобы хватать и удерживать предметы.#ELSE [npc.tailCount] [npc.tailColour(true)] #IF(npc.isShortStature())бесовских#ELSEдемонических#ENDIF хвостов с кончиками [#npc.getCovering(BODY_COVERING_TYPE_HAIR_DEMON).getFullDescription(npc, true)], над которыми [npc.sheHasFull] имеет полный контроль, что позволяет [npc.herHim] использовать их, чтобы хватать и удерживать предметы.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};

	public static final AbstractTailType DEMON_TAPERED = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.THREE_AVERAGE,
			0.75f,
			"демонический, сужающийся",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("заостренный", "демонический"),
			Util.newArrayListOfValues("заостренный", "демонический"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("заостренный"),
			Util.newArrayListOfValues("заостренный"),
			"#IF(npc.getTailCount()==1) Из-под самой верхней части [npc.her] задницы прорастает демонический сужающийся хвост, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует каждое его движение, позволяя [npc.herHim] использовать его как третью конечность.<br/>[npc.Name] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp(бесовский, сужающийся хвост)]#ELSE [style.boldDemon(демонический, сужающийся хвост)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Из-под самой верхней части [npc.her] задницы прорастают демонические сужающиеся хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] полностью контролирует каждое их движение, позволяя [npc.herHim] использовать их как дополнительные конечности.<br/>[npc.Name] теперь [npc.has] [npc.tailCount]#IF(npc.isShortStature()) [style.boldImp(бесовские, сужающиеся хвосты)]#ELSE [style.boldDemon(демонические, сужающиеся хвосты)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) сужающийся, [npc.tailColour(true)] #IF(npc.isShortStature())бесовский#ELSEдемонический#ENDIF хвост, над которым [npc.sheHasFull] полный контроль, позволяющий [npc.herHim] использовать его, чтобы хватать и удерживать предметы.#ELSE [npc.tailCount] сужающиеся, [npc.tailColour(true)] #IF(npc.isShortStature())бесовские#ELSEдемонические#ENDIF хвоста, над которыми [npc.sheHasFull] полный контроль, позволяющий [npc.herHim] использовать их, чтобы хватать и удерживать предметы.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_LINEAR), false) {
	};
	
	public static final AbstractTailType DEMON_HORSE = new AbstractTailType(
			BodyCoveringType.HORSE_HAIR,
			Race.DEMON,
			PenetrationGirth.THREE_AVERAGE,
			0.3f,
			"демонический конь",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues("лошадиный"),
			"конец",
			"заканчивается",
			Util.newArrayListOfValues("свободный"),
			Util.newArrayListOfValues("свободный"),
			"#IF(npc.getTailCount()==1) Прямо над задницей [npc.her] вырастает лошадиный хвост, стремительно удлиняясь, пока не будет свисать примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ним ограничен лишь тем, что им можно вилять из стороны в сторону.<br/>[npc.Name] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp(бесовский, лошадиный хвост)]#ELSE [style.boldDemon(демонический, лошадиный хвост)]#ENDIF, состоящий из [npc.tailFullDescription(true)].#ELSE Из-под задницы [npc.her] вырастают [npc.TailCount] лошадиных хвоста, стремительно удлиняясь, пока не будут свисать примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ними ограничен лишь тем, что ими можно вилять из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [npc.tailCount]#IF(npc.isShortStature()) [style.boldImp(бесовские, лошадиные хвосты)]#ELSE [style.boldDemon(демонические, лошадиные хвосты)]#ENDIF, состоящие из [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] #IF(npc.isShortStature())бесовский#ELSEдемонический#ENDIF конский хвост, которым [npc.she] может вилять из стороны в сторону, но в остальном [npc.she] [npc.does] почти не управляет им.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] #IF(npc.isShortStature())бесовские#ELSEдемонические#ENDIF конские хвосты, которыми [npc.she] может вилять из стороны в сторону, но в остальном [npc.she] [npc.does] почти не управляет ими.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_HAIR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};

	public static final AbstractTailType DEMON_OVIPOSITOR = new AbstractTailType(
			BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			PenetrationGirth.THREE_AVERAGE,
			0.75f,
			"демонический яйцеклад",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("яйцеклад", "демонический"),
			Util.newArrayListOfValues("яйцеклад", "демонический"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("яйцеклад"),
			Util.newArrayListOfValues("яйцеклад"),
			"#IF(npc.getTailCount()==1) Из-под самой верхней части [npc.her] задницы прорастает демонический сужающийся хвост, стремительно увеличиваясь в размерах, пока не достигнет около [npc.tailLength] в длину. Его конец слегка раздувается, образуя небольшое округлое утолщение, после чего на самом кончике прорезается тугой крестообразный разрез. Издавая [npc.a_moan], [npc.name] инстинктивно [npc.verb(feel)], что этот новый хвост [npc.hers] можно использовать как яйцеклад. Поворачивая его то так, то эдак, [npc.she] быстро [npc.verb(discover)], что [npc.she] [npc.has] полностью контролирует каждое его движение, что позволяет [npc.herHim] использовать его как третью конечность.<br/>[npc.Name] теперь [npc.has]#IF(npc.isShortStature()) [style.boldImp(бесовский яйцеклад)]#ELSE [style.boldDemon(демонический яйцеклад)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] демонические сужающиеся хвосты прорастают чуть выше [npc.her] задницы, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. Кончики каждого хвоста слегка раздуваются, образуя небольшие луковичные утолщения, после чего на их концах прорезаются туго сжатые крестообразные щели. Издавая [npc.a_moan], [npc.name] инстинктивно [npc.verb(feel)], что эти новые хвосты [npc.hers] можно использовать как яйцеклады. Поворачивая их то так, то эдак, [npc.she] быстро [npc.verb(discover)], что [npc.she] [npc.has] полностью контролирует их движения, что позволяет [npc.herHim] использовать их словно дополнительные конечности.<br/>[npc.Name] теперь [npc.has] [npc.tailCount]#IF(npc.isShortStature()) [style.boldImp(бесовские хвосты-яйцеклады)]#ELSE [style.boldDemon(демонические хвосты-яйцеклады)]#ENDIF, [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) сужающийся, [npc.tailColour(true)] #IF(npc.isShortStature())бесовский#ELSEдемонический#ENDIF хвост, над которым [npc.sheHasFull] обладает полным контролем, что позволяет [npc.herHim] использовать его, чтобы захватывать и удерживать предметы. Его кончик слегка раздулся, превратившись в небольшое округлое утолщение, а на самом конце образовалась плотно сжатая крестообразная щель, позволяющая [npc.herHim] использовать его как яйцеклад.#ELSE [npc.tailCount] сужающиеся, [npc.tailColour(true)] #IF(npc.isShortStature())бесовские#ELSEдемонические#ENDIF хвоста, над которыми [npc.sheHasFull] обладает полным контролем, что позволяет [npc.herHim] использовать их, чтобы захватывать и удерживать предметы. Кончики каждого из них слегка раздулись, превратившись в небольшие округлые утолщения, а на их концах образовались плотно сжатые крестообразные щели, позволяющие [npc.herHim] использовать их как яйцеклады.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_LINEAR,
					BodyPartTag.TAIL_OVIPOSITOR),
			false) {
	};
	
	public static final AbstractTailType ALLIGATOR_MORPH = new AbstractTailType(
			BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			PenetrationGirth.FIVE_THICK,
			0.6f,
			"аллигатора",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("аллигатороподобного"),
			Util.newArrayListOfValues("аллигатороподобного"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей вырастает чешуйчатый, похожий на аллигаторовый хвост, стремительно увеличиваясь в размерах, пока не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(discover)], что [npc.she] может с приличной силой вилять им из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [style.boldAlligatorMorph(аллигаторовый хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над [npc.her] задницей вырастают чешуйчатые, похожие на аллигаторовые хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(discover)], что [npc.she] может с приличной силой вилять ими из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldAlligatorMorph(аллигаторовые хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий прямо над [npc.her] задницей, [npc.sheHasFull]#IF(npc.getTailCount()==1) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)] аллигаторовый хвост, которым [npc.she] может с большой силой вилять из стороны в сторону.#ELSE [npc.tailCount] [style.colourFeral(дикие)],#ENDIF [npc.tailColour(true)] аллигаторовые хвосты, которыми [npc.she] может с большой силой вилять из стороны в сторону.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SCALES,
					BodyPartTag.TAIL_TAPERING_LINEAR,
					BodyPartTag.TAIL_ATTACK), false) {
	};
	
	public static final AbstractTailType BAT_MORPH = new AbstractTailType(
			BodyCoveringType.BAT_SKIN,
			Race.BAT_MORPH,
			PenetrationGirth.ONE_SLENDER,
			0.2f,
			"летучая мышь",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			Util.newArrayListOfValues("похожими на крылья летучей мыши"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей прорастает маленький, похожий на летучий хвост, который стремительно растет, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо ими управляет, и может изгибать его почти в любом направлении, [npc.she] [npc.verb(please)].<br/>[npc.Name] теперь [npc.has] [style.boldBatMorph(похожий на летучий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] прямо над [npc.her] задницей прорастают маленькие, похожие на летучий хвосты, которые стремительно растут, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо ими управляет, и может изгибать их почти в любом направлении, [npc.she] [npc.verb(please)].<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldBatMorph(похожие на летучий хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] летучий хвост, которым [npc.she] может быстро двигать вверх и вниз, чтобы помогать [npc.herHim] сохранять [npc.her] равновесие и управлять [npc.her] траекторией в полете.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] летучие хвосты, которыми [npc.she] может быстро двигать вверх и вниз, чтобы помогать [npc.herHim] сохранять [npc.her] равновесие и управлять [npc.her] траекторией в полете.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_GENERIC,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType CAT_MORPH = new AbstractTailType(
			BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			PenetrationGirth.TWO_NARROW,
			0.8f,
			"кошачьими",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("кошачьими", "пушистая"),
			Util.newArrayListOfValues("кошачьими", "пушистая"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над задницей [npc.her] вырастает пушистый кошачий хвост, стремительно увеличиваясь в размерах, пока не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо им управляет и может изгибать его почти как угодно [npc.she] [npc.verb(please)].<br/>[npc.Name] теперь [npc.has] [style.boldCatMorph(пушистый кошачий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над задницей [npc.her] вырастают пушистые кошачьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо ими управляет и может изгибать их почти как угодно [npc.she] [npc.verb(please)].<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldCatMorph(пушистые кошачьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий прямо над задницей [npc.her], [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)] кошачий хвост, которым [npc.she] может управлять достаточно хорошо, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(дикие)],#ENDIF [npc.tailColour(true)] кошачьи хвосты, которыми [npc.she] может управлять достаточно хорошо, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType CAT_MORPH_SHORT = new AbstractTailType(
			BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.2f,
			"короткий кошачий",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("кошачьими", "короткие", "пушистая"),
			Util.newArrayListOfValues("кошачьими", "короткие", "пушистая"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над задницей [npc.her] вырастает короткий, пушистый, кошачий хвост, который стремительно увеличивается в размерах, пока не достигнет примерно [npc.tailLength] в длину. Хотя [npc.she] [npc.has] вполне неплохо им управляет, он слишком короток, чтобы толком что-то делать.<br/>[npc.Name] теперь [npc.has] [style.boldCatMorph(короткий, кошачий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над задницей [npc.her] вырастают короткие, пушистые, кошачьи хвосты, которые стремительно увеличиваются в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. Хотя [npc.she] [npc.has] вполне неплохо ими управляет, они слишком коротки, чтобы толком что-то делать.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldCatMorph(короткие, кошачьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущие прямо над задницей [npc.her], [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)], короткий кошачий хвост.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)], короткие кошачьи хвосты.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType CAT_MORPH_TUFTED = new AbstractTailType(
			BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.4f,
			"пушистый кошачий",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("кошачьими", "пушистый", "пушистая"),
			Util.newArrayListOfValues("кошачьими", "пушистый", "пушистая"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистый"),
			Util.newArrayListOfValues("пушистый"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей вырастает короткий, пушистый, кошачий хвост, стремительно увеличивающийся в размерах, пока не достигнет примерно [npc.tailLength] в длину. На его конце красуется пушистый кончик, и [npc.she] может управлять им достаточно ловко, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.<br/>[npc.Name] теперь [npc.has] [style.boldCatMorph(tufted, cat-like tail)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] короткие, пушистые, кошачьи хвосты вырастают прямо над [npc.her] задницей, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. На конце каждого из них красуется пушистый кончик, и [npc.she] может управлять ими достаточно ловко, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldCatMorph(tufted, cat-like tails)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растет чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] кошачий хвост с пушистой кисточкой на конце. [npc.She] может достаточно хорошо им управлять, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] кошачьи хвосты, каждый с пушистой кисточкой на конце. [npc.She] может достаточно хорошо ими управлять, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType COW_MORPH = new AbstractTailType(
			BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			PenetrationGirth.TWO_NARROW,
			0.35f,
			"корова",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("коровьей формы"),
			Util.newArrayListOfValues("коровьей формы"),
			"конец",
			"заканчивается",
			Util.newArrayListOfValues("с пушистыми кончиками"),
			Util.newArrayListOfValues("с пушистыми кончиками"),
			"#IF(npc.getTailCount()==1) Из области чуть выше [npc.her] задницы прорастает коровьи хвост, стремительно удлиняясь, пока не свисает примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ним ограничен лишь покачиванием из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [style.boldCowMorph(коровий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] коровьи хвосты прорастают из области чуть выше [npc.her] задницы, стремительно удлиняясь, пока не свисают примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ними ограничен лишь покачиванием из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldCowMorph(коровьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] коровьи хвост, которым [npc.she] может вилять из стороны в сторону, но в остальном [npc.she] [npc.does] не особо управляет им.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] коровьи хвосты, которыми [npc.she] может вилять из стороны в сторону, но в остальном [npc.she] [npc.does] не особо управляет ими.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType DOG_MORPH = new AbstractTailType(
			BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.4f,
			"коровьи",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("собачьи"),
			Util.newArrayListOfValues("собачьи"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей вырастает пушистый собачий хвост, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] почти не может его контролировать, и он живет своей жизнью, виляя всякий раз, когда [npc.she] [npc.verb(get)] возбуждается.<br/>[npc.Name] теперь [npc.has] [style.boldDogMorph(собачий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над [npc.her] задницей вырастают пушистые собачьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] почти не может ими управлять, и они живут своей жизнью, виляя всякий раз, когда [npc.she] [npc.verb(get)] возбуждается.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldDogMorph(собачьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull] #IF(npc.getTailCount()==1) [style.colourFeral(feral)]#IFEND [npc.tailColour(true)] собачий хвост, который неудержимо виляет, когда [npc.she] [npc.verb(get)] возбуждается.#ELSE [npc.tailCount] [style.colourFeral(feral)]#IFEND [npc.tailColour(true)] собачьих хвоста, которые неудержимо виляют, когда [npc.she] [npc.verb(get)] возбуждается.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType DOG_MORPH_STUBBY = new AbstractTailType(
			BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.1f,
			"короткий собачий",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("короткий", "собачьи"),
			Util.newArrayListOfValues("короткий", "собачьи"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("короткий"),
			Util.newArrayListOfValues("короткий"),
			"#IF(npc.getTailCount()==1) Прямо над задницей [npc.her] вырастает пушистый собачий хвост, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] почти не может его контролировать, и он живет своей собственной жизнью, виляя всякий раз, когда [npc.she] [npc.verb(get)] возбуждается.<br/>[npc.Name] теперь [npc.has] [style.boldDogMorph(stubby, dog-like tail)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над задницей [npc.her] вырастают пушистые собачьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] почти не может ими управлять, и они живут своей собственной жизнью, виляя всякий раз, когда [npc.she] [npc.verb(get)] возбуждается.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldDogMorph(stubby, dog-like tails)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull] #IF(npc.getTailCount()==1) [style.colourFeral(feral)]#IF(npc.isTailFeral()),#ENDIF короткий, [npc.tailColour(true)] собачий хвост, который неудержимо виляет, когда [npc.she] [npc.verb(get)] возбужден.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF короткие [npc.tailColour(true)] собачьи хвосты, которые неудержимо виляют, когда [npc.she] [npc.verb(get)] возбуждены.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType FOX_MORPH = new AbstractTailType(
			BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			0.6f,
			"лисий",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("лисоподобные", "пушистый", "пушистый"),
			Util.newArrayListOfValues("лисоподобные", "пушистый", "пушистый"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей вырастает пушистый лисий хвост, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо им управляет, и [npc.is] может обернуть его пушистую длину вокруг [npc.her] нижней части тела.<br/>[npc.Name] теперь [npc.has] [style.boldFoxMorph(лисий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над [npc.her] задницей вырастают пушистые лисьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо ими управляет, и [npc.is] может обернуть их пушистые длины вокруг [npc.her] нижней части тела.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldFoxMorph(лисьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий прямо над [npc.her] задницей, [npc.sheHasFull]#IF(npc.getTailCount()==1) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)] лисий хвост, которым [npc.she] может свободно вилять туда-сюда.#ELSE [npc.tailCount] [style.colourFeral(дикие)],#ENDIF [npc.tailColour(true)] лисьи хвосты, которыми [npc.she] может свободно вилять туда-сюда.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType FOX_MORPH_MAGIC = new AbstractTailType(
			BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			1f,
			"аркана лиса",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("аркана", "лисоподобные", "пушистый"),
			Util.newArrayListOfValues("аркана", "лисоподобные", "пушистый"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей вырастает пушистый лисий хвост, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо им управляет, и [npc.is] может обернуть его пушистую длину вокруг [npc.her] нижней части тела. [npc.SheIsFull] также прекрасно осознает, что это дарует [npc.herHim] [style.italicsArcane(аркана силы)], и что чем больше хвостов [npc.sheIsFull] сможет обрести, тем мощнее [npc.she] станет!<br/>[npc.Name] теперь [npc.has] [style.boldArcane(аркана)] [style.boldFoxMorph(лисий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] прямо над [npc.her] задницей вырастают пушистые лисьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо ими управляет, и [npc.is] может обернуть их пушистую длину вокруг [npc.her] нижней части тела.#IF(npc.getTailCount()==9) [npc.SheIsFull] также прекрасно осознает, что они даруют [npc.herHim] [style.italicsExcellent(огромные)] [style.italicsArcane(аркана силы)]!#ELSE [npc.SheIsFull] также прекрасно осознает, что они даруют [npc.herHim] [style.italicsArcane(аркана силы)], и что чем больше хвостов [npc.sheIsFull] сможет обрести, тем мощнее [npc.she] станет!#ENDIF<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldArcane(аркана)] [style.boldFoxMorph(лисьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)] [style.boldArcane(магический)] лисий хвост, дарующий [npc.herHim] [style.italicsArcane(магические силы)]!#ELSE#IF(npc.getTailCount()==9) [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(диких)],#ENDIF [npc.tailColour(true)] [style.boldArcane(магических)] лисьих хвостов, дарующих [npc.herHim] [style.italicsExcellent(огромные)] [style.italicsArcane(магические силы)]!#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(диких)],#ENDIF [npc.tailColour(true)] [style.boldArcane(магических)] лисьих хвостов, дарующих [npc.herHim] [style.italicsArcane(магические силы)]!#ENDIF#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType HARPY = new AbstractTailType(
			BodyCoveringType.FEATHERS,
			Race.HARPY,
			PenetrationGirth.FOUR_GIRTHY,
			0.5f,
			"плюмаж гарпии",
			"плюмаж",
			"пучки",
			"хвостовые перья",
			"хвостовые перья",
			Util.newArrayListOfValues("великолепные", "птичьи"),
			Util.newArrayListOfValues("великолепные", "птичьи"),
			"конец",
			"заканчивается",
			Util.newArrayListOfValues("оперенные"),
			Util.newArrayListOfValues("оперенные"),
			"#IF(npc.getTailCount()==1) Прямо над задницей [npc.her] пробивается красивый пучок хвостовых перьев, каждое из которых быстро вырастает до [npc.tailLength] в длину. [npc.She] [npc.verb(discover)] то, что [npc.she] может быстро поднимать и опускать [npc.her] новый птичий хвост, который помогает [npc.herHim] сохранять [npc.her] равновесие.<br/>[npc.Name] теперь [npc.has] [style.boldHarpy(птичий хвост гарпии)], состоящий из [npc.tailFullDescription(true)].#ELSE [npc.TailCount] красивые пучки хвостовых перьев пробиваются прямо над задницей [npc.her], каждое из которых быстро вырастает до [npc.tailLength] в длину. [npc.She] [npc.verb(discover)] то, что [npc.she] может быстро поднимать и опускать [npc.her] новые птичьи хвосты, которые помогают [npc.herHim] сохранять [npc.her] равновесие.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldHarpy(птичьи хвосты гарпии)], состоящие из [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) a #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF пышный веер прекрасных, [npc.tailColour(true)] перьев хвоста, которыми [npc.she] может быстро двигать вверх и вниз, чтобы помогать [npc.herHim] удерживать [npc.her] равновесие и управлять [npc.her] траекторией в полете.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF пушистые перья прекрасного, [npc.tailColour(true)] хвоста, которыми [npc.she] может быстро двигать вверх и вниз, чтобы помочь [npc.herHim] удерживать [npc.her] равновесие и управлять [npc.her] траекторией во время полета.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FEATHER,
					BodyPartTag.TAIL_TAPERING_NONE,
					BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION), false) {
	};
	
	public static final AbstractTailType HORSE_MORPH = new AbstractTailType(
			BodyCoveringType.HAIR_HORSE_HAIR,
			Race.HORSE_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.3f,
			"лошади",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("лошадиный"),
			Util.newArrayListOfValues("лошадиный"),
			"конец",
			"заканчивается",
			Util.newArrayListOfValues("свободный"),
			Util.newArrayListOfValues("свободный"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей прорастает лошадиный хвост, стремительно удлиняясь, пока не свисает примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ним ограничен лишь тем, что им можно вилять из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [style.boldHorseMorph(лошадиный хвост)], состоящий из [npc.tailFullDescription(true)].#ELSE [npc.TailCount] лошадиных хвостов прорастают прямо над [npc.her] задницей, стремительно удлиняясь, пока не свисают примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ними ограничен лишь тем, что ими можно вилять из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldHorseMorph(лошадиные хвосты)], состоящие из [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] лошадиный хвост, которым [npc.she] может махать из стороны в сторону, но в остальном [npc.she] [npc.does] нет особого контроля.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] лошадиные хвосты, которыми [npc.she] может махать из стороны в сторону, но в остальном [npc.she] [npc.does] нет особого контроля.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_HAIR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType HORSE_MORPH_ZEBRA = new AbstractTailType(
			BodyCoveringType.HAIR_HORSE_HAIR,
			Race.HORSE_MORPH,
			PenetrationGirth.TWO_NARROW,
			0.3f,
			"зебра",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("зеброподобный"),
			Util.newArrayListOfValues("зеброподобный"),
			"конец",
			"заканчивается",
			Util.newArrayListOfValues("с кисточкой на конце"),
			Util.newArrayListOfValues("с кисточкой на конце"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей вырастает полосатый хвост, стремительно удлиняясь, пока не будет свисать примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ним ограничен лишь тем, что им можно вилять из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [style.boldHorseMorph(полосатый хвост)], состоящий из [npc.tailFullDescription(true)].#ELSE [npc.TailCount] полосатые хвосты вырастают прямо над [npc.her] задницей, стремительно удлиняясь, пока не будут свисать примерно на [npc.tailLength] позади [npc.herHim]. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ними ограничен лишь тем, что ими можно вилять из стороны в сторону.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldHorseMorph(полосатые хвосты)], состоящие из [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) a #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)], полосатый хвост, которым [npc.she] может вилять из стороны в сторону, но в остальном [npc.she] [npc.does] не особо управляет.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)] хвосты зебры, которыми [npc.she] может вилять из стороны в сторону, но в остальном [npc.she] [npc.does] не особо умеешь управлять.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_HAIR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType RAT_MORPH = new AbstractTailType(
			BodyCoveringType.RAT_SKIN,
			Race.RAT_MORPH,
			PenetrationGirth.THREE_AVERAGE,
			0.75f,
			"крыса",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("крысиные"),
			Util.newArrayListOfValues("крысиные"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей прорастает толстый крысиный хвост, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо им управляет и может изгибать его почти как угодно [npc.she] [npc.verb(please)].<br/>[npc.Name] теперь [npc.has] [style.boldRatMorph(крысиный хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над [npc.her] задницей прорастают толстые крысиные хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне неплохо ими управляет и может изгибать их почти как угодно [npc.she] [npc.verb(please)].<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldRatMorph(крысиные хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull] #IF(npc.getTailCount()==1) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] крысиный хвост, над которым [npc.she] [npc.has] полный контроль, позволяющий [npc.herHim] использовать его, чтобы хватать и удерживать предметы.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] крысиных хвостов, над которыми [npc.she] [npc.has] полный контроль, позволяющий [npc.herHim] использовать их, чтобы хватать и удерживать предметы.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_PREHENSILE,
					BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION,
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_SKIN,
					BodyPartTag.TAIL_TAPERING_LINEAR), false) {
	};
	
	public static final AbstractTailType RABBIT_MORPH = new AbstractTailType(
			BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			PenetrationGirth.FIVE_THICK,
			0.075f,
			"кроличьи",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("кроличьими", "пушистый"),
			Util.newArrayListOfValues("кроличьими", "пушистый"),
			"куст",
			"кусты",
			Util.newArrayListOfValues("пушистый"),
			Util.newArrayListOfValues("пушистый"),
			"#IF(npc.getTailCount()==1) Прямо над задницей [npc.her] вырастает пушистый, круглый, кроличий хвост, стремительно увеличиваясь в размерах, пока не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] совершенно не может его контролировать, ведь это всего лишь комок пушистой шерсти.<br/>[npc.Name] теперь [npc.has] [style.boldRabbitMorph(кроличий хвост)], состоящий из [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над задницей [npc.her] вырастают пушистые, круглые, кроличьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] совершенно не может ими контролировать, ведь это всего лишь комки пушистой шерсти.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldRabbitMorph(кроличьи хвосты)], состоящие из [npc.tailFullDescription(true)].#ENDIF",
			"Растущий прямо над задницей [npc.her], [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)] кроличий хвост, который на самом деле является не более чем большим комком пушистой шерсти.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(дикие)],#ENDIF [npc.tailColour(true)] кроличьи хвосты, которые на самом деле являются не более чем большими комками пушистой шерсти.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE,
					BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION), false) {
	};
	
	public static final AbstractTailType REINDEER_MORPH = new AbstractTailType(
			BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			0.05f,
			"оленьей шерсти",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("похожих на оленьи"),
			Util.newArrayListOfValues("похожих на оленьи"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над [npc.her] задницей прорастает олений хвост, стремительно увеличиваясь в размерах, пока не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ним ограничен лишь простым подергиванием вверх-вниз.<br/>[npc.Name] теперь [npc.has] [style.boldReindeerMorph(олений хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над [npc.her] задницей прорастают оленьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет около [npc.tailLength] в длину. [npc.She] быстро [npc.verb(discover)], что [npc.her] контроль над ними ограничен лишь простым подергиванием вверх-вниз.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldReindeerMorph(оленьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий прямо над [npc.her] задницей, [npc.sheHasFull]#IF(npc.getTailCount()==1) [style.colourFeral(дикий)],#ENDIF [npc.tailColour(true)] олений хвост, который [npc.she] можно подергивать вверх-вниз.#ELSE [npc.tailCount] [style.colourFeral(дикие)],#ENDIF [npc.tailColour(true)] оленьи хвосты, которые [npc.she] можно подергивать вверх-вниз.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType SQUIRREL_MORPH = new AbstractTailType(
			BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			PenetrationGirth.FIVE_THICK,
			1f,
			"беличьи",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("беличьи", "пушистый", "пушистый"),
			Util.newArrayListOfValues("беличьи", "пушистый", "пушистый"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Прямо над задницей [npc.her] вырастает пушистый беличий хвост, стремительно увеличиваясь в размерах, пока не достигнет внушительной длины в [npc.tailLength]. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне разумно управляет им, и может использовать его, чтобы помогать [npc.herHim] сохранять равновесие при быстром движении.<br/>[npc.Name] теперь [npc.has] [style.boldSquirrelMorph(беличий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Прямо над задницей [npc.her] вырастают пушистые беличьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет внушительной длины в [npc.tailLength]. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] вполне разумно управляет ими, и может использовать их, чтобы помогать [npc.herHim] сохранять равновесие при быстром движении.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldSquirrelMorph(беличьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull]#IF(npc.getTailCount()==1) #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] беличий хвост, которым [npc.she] может управлять достаточно хорошо, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.#ELSE [npc.tailCount] #IF(npc.isTailFeral()) [style.colourFeral(feral)],#ENDIF [npc.tailColour(true)] беличьи хвосты, которыми [npc.she] может управлять достаточно хорошо, чтобы обеспечить [npc.herHim] значительно улучшенное равновесие.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	public static final AbstractTailType WOLF_MORPH = new AbstractTailType(
			BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			PenetrationGirth.FOUR_GIRTHY,
			0.4f,
			"волчьи",
			"",
			"",
			"хвост",
			"хвосты",
			Util.newArrayListOfValues("волчьими", "пушистый"),
			Util.newArrayListOfValues("волчьими", "пушистый"),
			"кончик",
			"кончики",
			Util.newArrayListOfValues("пушистая"),
			Util.newArrayListOfValues("пушистая"),
			"#IF(npc.getTailCount()==1) Из-под самой верхней части [npc.her] задницы прорастает пушистый волчий хвост, стремительно увеличиваясь в размерах, пока не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] лишь частично управляет им, и ей приходится прилагать немало усилий, чтобы он не выдавал [npc.her] эмоции.<br/>[npc.Name] теперь [npc.has] [style.boldWolfMorph(волчий хвост)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ELSE [npc.TailCount] Из-под самой верхней части [npc.her] задницы прорастают пушистые волчьи хвосты, стремительно увеличиваясь в размерах, пока каждый из них не достигнет примерно [npc.tailLength] в длину. [npc.She] быстро [npc.verb(realise)], что [npc.she] [npc.has] лишь частично управляет ими, и ей приходится прилагать немало усилий, чтобы они не выдавали [npc.her] эмоции.<br/>[npc.Name] теперь [npc.has] [npc.tailCount] [style.boldWolfMorph(волчьи хвосты)], [npc.materialDescriptor] [npc.tailFullDescription(true)].#ENDIF",
			"Растущий чуть выше [npc.her] задницы, [npc.sheHasFull] #IF(npc.getTailCount()==1) [style.colourFeral(feral)]#IFEND [npc.tailColour(true)] волчий хвост, который виляет из стороны в сторону, когда [npc.she] [npc.verb(get)] возбужден.#ELSE [npc.tailCount] [style.colourFeral(feral)]#IFEND [npc.tailColour(true)] волчьих хвоста, которые виляют из стороны в сторону, когда [npc.she] [npc.verb(get)] возбуждены.#ENDIF",
			Util.newArrayListOfValues(
					BodyPartTag.TAIL_SLEEP_HUGGING,
					BodyPartTag.TAIL_TYPE_FUR,
					BodyPartTag.TAIL_TAPERING_NONE), false) {
	};
	
	
	private static List<AbstractTailType> allTailTypes;
	private static Map<AbstractTailType, String> tailToIdMap = new HashMap<>();
	private static Map<String, AbstractTailType> idToTailMap = new HashMap<>();
	
	static {
		allTailTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/раса", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tail")) {
					try {
						AbstractTailType type = new AbstractTailType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTailTypes.add(type);
						tailToIdMap.put(type, id);
						idToTailMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("tail")) {
					try {
						AbstractTailType type = new AbstractTailType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allTailTypes.add(type);
						tailToIdMap.put(type, id);
						idToTailMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded tail types:
		
		Field[] fields = TailType.class.getFields();
		
		for(Field f : fields){
			if (AbstractTailType.class.isAssignableFrom(f.getType())) {
				
				AbstractTailType ct;
				try {
					ct = ((AbstractTailType) f.get(null));

					tailToIdMap.put(ct, f.getName());
					idToTailMap.put(f.getName(), ct);
					
					allTailTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allTailTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractTailType getTailTypeFromId(String id) {
		if(id.equals("IMP")) {
			return TailType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return TailType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToTailMap.keySet());
		return idToTailMap.get(id);
	}
	
	public static String getIdFromTailType(AbstractTailType tailType) {
		return tailToIdMap.get(tailType);
	}
	
	public static List<AbstractTailType> getAllTailTypes() {
		return allTailTypes;
	}
	
	private static Map<AbstractRace, List<AbstractTailType>> typesMap = new HashMap<>();
	
	public static List<AbstractTailType> getTailTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractTailType> types = new ArrayList<>();
		for(AbstractTailType type : TailType.getAllTailTypes()) {
			if(type.getRace()==r && type!=TailType.FOX_MORPH_MAGIC) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			types.add(TailType.NONE);
		}
		typesMap.put(r, types);
		return types;
	}
	
	public static List<AbstractTailType> getTailTypesSuitableForTransformation(List<AbstractTailType> options) {
		if (!options.contains(TailType.NONE)) {
			return options;
		}
		
		List<AbstractTailType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(TailType.NONE);
		return duplicatedOptions;
	}
}