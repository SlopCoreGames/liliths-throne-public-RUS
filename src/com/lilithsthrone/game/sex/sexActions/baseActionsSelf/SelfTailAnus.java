package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfTailAnus {
	
	public static final SexAction SELF_TAIL_ANUS_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Хвостом в анус (самой себе)";
		}

		@Override
		public String getActionDescription() {
			return "Начать трахать собственную [npc.ass] своим [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Проскальзывая [npc.tail] [npc.her] вверх к [npc.her] [npc.ass], [npc.name] [npc.verb(tease)] кончик над [npc.her] [npc.asshole+], [npc.moaning] в восторге, прежде чем вонзить его глубоко внутрь.",
					"[npc.Name] [npc.verb(snake)] [npc.her] [npc.tail] вверх к [npc.her] [npc.ass], [npc.moaning] в восторге, пока [npc.she] [npc.verb(force)] его глубоко в [npc.her], приглашая [npc.asshole].",
					"Скользя кончиком [npc.her] [npc.tail+] к заброшенному [npc.her] [npc.asshole], [npc.name] внезапно [npc.verb(thrust)] его глубоко внутрь, издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] трахает хвостом собственный [npc.ass] [npc.her].",
					"[npc.Name] жадно [npc.verb(thrust)] [npc.her] [npc.tail+] глубоко в неистовые [npc.asshole] [npc.her], издавая серию [npc.moans+], пока [npc.she] [npc.verb(start)] трахает хвостом собственный [npc.ass] [npc.her].");
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_ANUS_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный само-пеггинг хвостом";
		}

		@Override
		public String getActionDescription() {
			return "Мягко трахай [npc.her] собственную [npc.ass] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], когда [npc.she] медленно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.asshole+].",
					"Мягко покачивая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] медленно [npc.verb(fuck)] собственный [npc.ass] [npc.her].",
					"Медленно вонзая [npc.her] [npc.tail] глубоко в [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] тихий всхлип, когда [npc.she] "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "[npc.verb(use)] это, чтобы начать нежно массировать простату [npc.her]."
							: "нежно [npc.verb(fuck)] собственный [npc.her] [npc.ass+]."),
					"Сосредоточившись на удовольствии [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] плавно качая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тейл-пеггинг (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на траханьи [npc.her] собственного [npc.ass] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.asshole+].",
					"Вгоняя [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.her] собственный [npc.ass].",
					"Вгоняя [npc.her] [npc.tail] глубоко в [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] [npc.a_moan], как [npc.she] "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "[npc.verb(use)] это, чтобы начать массировать простату [npc.her]."
							: "размеренно [npc.verb(fuck)] [npc.her] собственный [npc.ass+]."),
					"Сосредоточившись на ласках [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] ритмично [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+].");
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_ANUS_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый само-хвостопеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать [npc.her] собственный [npc.ass] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.asshole+], после чего начинает грубо трахать собственный [npc.ass] [npc.her].",
					"Грубо двигая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] безжалостно [npc.verb(fuck)] [npc.her] собственный [npc.ass].",
					"Силовым движением вгоняя [npc.her] [npc.tail] глубоко в [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] наружу [npc.a_moan] как [npc.she] "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "[npc.verb(start)] грубо втирая его в [npc.her] простату."
							: "грубо [npc.verb(fuck)] [npc.her] собственный [npc.ass+]."),
					"Сосредоточившись на ласках [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] грубо вколачивая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+].");
		}

	};
	
	public static final SexAction SUB_SELF_TAIL_ANUS_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тейл-пеггинг (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на траханьи [npc.her] собственного [npc.ass] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.asshole+].",
					"Вгоняя [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.her] собственный [npc.ass].",
					"Вгоняя [npc.her] [npc.tail] глубоко в [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] [npc.a_moan], как [npc.she] "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "[npc.verb(use)] это, чтобы начать массировать простату [npc.her]."
							: "размеренно [npc.verb(fuck)] [npc.her] собственный [npc.ass+]."),
					"Сосредоточившись на ласках [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] ритмично [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+].");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_ANUS_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадное само-тейлпеггинг";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью начинаешь ебать свой [npc.ass] с помощью [npc.her] [npc.tail+] [npc.her].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(slam)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.asshole+], прежде чем начать отчаянно трахать собственный [npc.ass] [npc.her].",
					"Энтузиастично вгоняя и выгоняя [npc.her] [npc.tail] в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] неистово [npc.verb(fuck)] свои собственные [npc.ass] [npc.her].",
					"Отчаянно вбивая [npc.her] [npc.tail] глубоко в [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] издавая [npc.a_moan] как [npc.she] "
							+(Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
							? "[npc.verb(start)] с жадностью втирая его в простату [npc.her]."
							: "жадно [npc.verb(fuck)] собственный [npc.her] [npc.ass+]."),
					"Сосредоточившись на ласках [npc.her] [npc.ass+], [npc.name] с жадностью [npc.verb(start)] вколачивает [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.asshole+].");
		}
	};
	
	public static final SexAction SELF_TAIL_ANUS_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Прекратить тейлпеггинг (самостоятельно)";
		}

		@Override
		public String getActionDescription() {
			return "Хватит трахать [npc.her]саму себя [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return "С [npc.a_groan+], [npc.name] [npc.verb(slide)] [npc.her] [npc.tail+] из [npc.her] [npc.asshole+].";
		}
	};
}
