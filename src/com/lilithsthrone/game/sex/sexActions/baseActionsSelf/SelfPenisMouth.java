package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
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
 * @version 0.4.8.5
 * @author Innoxia
 */
public class SelfPenisMouth {
	
	public static final SexAction SELF_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition().isSelfOralAvailable(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.HYPERMOBILITY)
							|| Main.sex.getCharacterPerformingAction().hasPerkAnywhereInTree(Perk.DOLL_PHYSICAL_1));
		}
		@Override
		public String getActionTitle() {
			return "Начать автофелляцию";
		}
		@Override
		public String getActionDescription() {
			return "Наклониться и взять свой [npc.cock+] в рот, прежде чем начать отсасывать самому себе.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Демонстрируя впечатляющую гибкость, [npc.name] [npc.verb(bend)] вниз и ",
					"Показывая, насколько гибким является [npc.sheIsFull], [npc.name] [npc.verb(double)] наклон и ",
					"Испытывая гибкость [npc.her] на прочность, [npc.name] [npc.verb(bend)] вниз и "));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] к [npc.cockHead+] [npc.her] [npc.cock]. Медленно принимая [npc.her] [npc.cock] в [npc.her] рот, [npc.she] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] делает себе [npc.herself].",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.cockHead+] [npc.her] [npc.cock]. Издавая приглушенный [npc.moan], [npc.she] [npc.verb(start)] нежно отсасывает [npc.herself]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] к [npc.cockHead+] [npc.her] [npc.cock]. Жадно принимая [npc.her] [npc.cock] в [npc.her] рот, [npc.she] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] счастливо [npc.verb(start)] делает себе [npc.herself].",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.cockHead+] [npc.her] [npc.cock]. Издавая приглушенный [npc.moan], [npc.she] с нетерпением [npc.verb(start)], с энтузиазмом делая [npc.herself] минет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] к [npc.cockHead+] [npc.her] [npc.cock]. Жадно заглатывая [npc.her] [npc.cock] в [npc.her] рот, [npc.she] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] делает [npc.herself] минет.",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.cockHead+] [npc.her] [npc.cock]. Издавая приглушенный [npc.moan], [npc.she] жадно [npc.verb(start)], влажно и грубо отсасывая [npc.herself]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.verb(bring)] [npc.her] [npc.lips+] к [npc.cockHead+] [npc.her] [npc.cock]. Заглатывая [npc.her] [npc.cock] в [npc.her] рот, [npc.she] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] делает [npc.herself] минет.",

							"[npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.cockHead+] [npc.her] [npc.cock]. Издавая приглушенный [npc.moan], [npc.she] [npc.verb(start)] делает [npc.herself] минет."));
					break;
				default:
					break;
			}
		
			return UtilText.nodeContentSB.toString();
		}
	};
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction SELF_BLOWJOB_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Глубокий горловой";
		}
		@Override
		public String getActionDescription() {
			return "Заглоти свой [npc.cock+] как можно глубже в глотку.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко обхватив [npc.her] [npc.lips+] вокруг собственного [npc.cock] [npc.her], [npc.name] [npc.verb(push)] [npc.her] вперед, заглатывая его так глубоко в [npc.her], насколько только может [npc.she].",

							"С мягким, приглушенным [npc.moan], [npc.name] осторожно [npc.verb(bend)] вниз настолько глубоко, насколько позволяет [npc.she], раздвигая [npc.her] [npc.lips+], чтобы [npc.she] [npc.verb(take)] как можно больше [npc.her] [npc.cock+] в [npc.her] глотку.",

							"Медленно продвигая головку [npc.her] вперед, [npc.name] нежно [npc.verb(part)] [npc.her] [npc.lips+], прежде чем заглотить [npc.her] [npc.cock+] глубоко в [npc.her] горло."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью обхватив [npc.her] [npc.lips+] вокруг собственного [npc.cock] [npc.her], [npc.name] быстро [npc.verb(push)] [npc.her] вперед, жадно заглатывая его так глубоко в [npc.her], насколько только может [npc.she].",

							"С приглушенным, [npc.moan+] звуком, [npc.name] жадно [npc.verb(bend)] вниз настолько глубоко, насколько позволяет [npc.she], раздвигая [npc.her] [npc.lips+], пока [npc.she] отчаянно [npc.verb(take)] как можно больше [npc.her] [npc.cock+] в [npc.her] горло.",

							"Жадно продвигая головку [npc.her] вперед, [npc.name] охотно [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] [npc.her] [npc.cock+] глубоко в [npc.her] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Сильно обхватив [npc.her] [npc.lips+] вокруг собственного [npc.cock] [npc.her], [npc.name] грубо [npc.verb(slam)] [npc.her] вперед, заталкивая его так глубоко в [npc.her], насколько только может [npc.she].",

							"С приглушенным, [npc.moan+] звуком, [npc.name] быстро [npc.verb(bend)] вниз настолько глубоко, насколько позволяет [npc.she], раздвигая [npc.her] [npc.lips+], пока [npc.she] грубо [npc.verb(force)] как можно больше [npc.her] [npc.cock+] в [npc.her] горло.",

							"Агрессивно толкая головку [npc.her] вперед, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(force)] [npc.her] [npc.cock+] глубоко в [npc.her] горло."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Облепив [npc.her] [npc.lips+] вокруг собственного [npc.her] [npc.cock], [npc.name] быстро [npc.verb(push)] [npc.her] вперед, заглатывая его так глубоко в [npc.her], насколько [npc.she] только может.",

							"С приглушенным, [npc.moan+] звуком, [npc.name] [npc.verb(bend)] так глубоко, как только позволяет [npc.she], раздвигая [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] как можно больше [npc.her] [npc.cock+] в [npc.her] горло.",

							"Проталкивая [npc.her] головку вперед, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] [npc.her] [npc.cock+] глубоко в [npc.her] горло."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SELF_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное аутофелляцио";
		}
		@Override
		public String getActionDescription() {
			return "Нежно посасывай свой [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко обхватив [npc.her] [npc.lips+] вокруг собственного [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] покачивает [npc.her] головой вверх и вниз, пока [npc.she] [npc.verb(give)] [npc.herself] с любовью.",
					"С мягким, приглушенным [npc.moan], [npc.name] осторожно [npc.verb(start)] покачивает [npc.her] головкой вверх и вниз, обхватывая [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] головку.",
					"Медленно покачивая [npc.her] головкой вверх и вниз, [npc.name] осторожно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] минет."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Аутофелляцио";
		}
		@Override
		public String getActionDescription() {
			return "Жадно посасывай свой [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно обхватив [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] стремительно двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc.herself] страстный минет.",
					"С приглушенным, [npc.moan+], [npc.name] быстро [npc.verb(start)] двигает [npc.her] головой вверх-вниз, жадно обхватывая [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] голову.",
					"Стремительно двигая [npc.her] головой вверх-вниз, [npc.name] отчаянно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] нетерпеливый минет."));
		
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубое аутофелляцио";
		}
		@Override
		public String getActionDescription() {
			return "Грубо сосать твой [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Сильно сжимая [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], [npc.name] агрессивно [npc.verb(start)] двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc.herself] грубый минет.",
					"С приглушенным, [npc.moan+], [npc.name] неистово [npc.verb(start)] двигает [npc.her] головой вверх-вниз, грубо обхватывая [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] голову.",
					"Грубо двигая [npc.her] головой вверх-вниз, [npc.name] доминантно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] напористый минет."));
		
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Аутофелляцио";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить сосать твой [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Оборачивая [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] быстро двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc.herself].",
					"Приглушенно, [npc.moan+], [npc.name] быстро [npc.verb(start)] покачивает [npc.her] головой вверх-вниз, обхватывая [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] голову.",
					"Быстро двигая [npc.her] головой вверх-вниз, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадное аутофелляцио";
		}
		@Override
		public String getActionDescription() {
			return "Жадно посасывай свой [npc.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно обхватив [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] стремительно двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc.herself] страстный минет.",
					"С приглушенным, [npc.moan+], [npc.name] быстро [npc.verb(start)] двигает [npc.her] головой вверх-вниз, жадно обхватывая [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] голову.",
					"Стремительно двигая [npc.her] головой вверх-вниз, [npc.name] отчаянно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc.her] [npc.cock+], пока [npc.she] [npc.verb(give)] [npc.herself] нетерпеливый минет."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SELF_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Прекратить аутофелляцио";
		}
		@Override
		public String getActionDescription() {
			return "Вынь свой [npc.cock+] изо рта и перестань отсасывать самому себе.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо протолкнув [npc.her] [npc.cock+] в [npc.her] горло в последний раз, [npc.name] затем [npc.verb(pull)] [npc.her] голову назад, быстро завершая [npc.her] аутофелляцио.",

							"Slamming [npc.her] [npc.face] into [npc.her] own groin, [npc.name] [npc.verb(force)] [npc.her] [npc.cock+] deep down [npc.her] throat, before pulling completely back and putting an end to [npc.her] autofellatio."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Высунув [npc.her] [npc.cock+] изо рта [npc.her], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(put)] завершение [npc.her].",

							"С [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] голову назад, полностью вытягивая [npc.her] [npc.cock+] изо рта [npc.her]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
