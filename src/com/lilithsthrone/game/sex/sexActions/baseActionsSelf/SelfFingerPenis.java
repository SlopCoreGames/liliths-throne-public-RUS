package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class SelfFingerPenis {
	
	public static final SexAction STROKE_PENIS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Ласкать член (самой)";
		}
		@Override
		public String getActionDescription() {
			return "Получать удовольствие, поглаживая и дразня свой [npc.cock].";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				return UtilText.returnStringAtRandom(
						"Просунув руку между [npc.her] [npc.legs], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers+] вверх и вниз по всей длине [npc.her] [npc.cock+] и [npc.verb(let)] [npc.a_moan+].",
						"[npc.Name] [npc.groansVerb+], пока [npc.she] [npc.verb(tease)] [npc.her] [npc.fingers] по [npc.cockHead+] [npc.her] [npc.cock+].",
						"Поглаживая и дразня [npc.her], [npc.fingers+] вверх и вниз по всей длине [npc.her] [npc.cock+], [npc.name] не может удержаться от серии [npc.moans+].",
						"Скользя [npc.her] [npc.fingers] вверх и вниз по [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] серию [npc.groans+].");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(run)] [npc.her] [npc.hand] по [npc.her] паху, прежде чем прижать [npc.her] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+" плотно к [npc.her] [npc.cock+] и издать [npc.a_moan+].",
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.fingers+] вниз между [npc.her] [npc.legs], прежде чем издать [npc.a_moan+], когда [npc.she] [npc.verb(press)] [npc.her] "
							+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+" плотно к [npc.her] [npc.cock+].",
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.fingers] поверх [npc.her] "+Main.sex.getCharacterPerformingAction().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+", прежде чем надавить и попытаться стимулировать [npc.her] [npc.cock+] сквозь [npc.her] одежду.",
						"Нажимая ладонью [npc.her] между [npc.her] и [npc.legs], [npc.name] [npc.verb(rub)] [npc.her] [npc.hand] "+Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()
							+" вниз на скрытый [npc.her] [npc.cock+].");
			}
		}
		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
			}
		}
	};
	
	public static final SexAction START_COCK_STROKING = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Начать ласкать член";
		}
		@Override
		public String getActionDescription() {
			return "Обхвати свой [npc.cock+] и начни дрочить.";
		}
		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Просунув руку между [npc.her] [npc.legs], [npc.name] [npc.verb(grab)] берет [npc.her] [npc.cock+] и [npc.verb(start)], чтобы мастурбировать.",
					"Сжимая [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] раз за разом водит рукой вверх и вниз.",
					"Протянув руку и обхватив [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] вытаскивает [npc.a_moan+], пока [npc.she] [npc.verb(start)] дрочит [npc.herself]."));
		}
	};
	
	public static final SexAction DOM_GENTLE_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежные ласки члена";
		}
		@Override
		public String getActionDescription() {
			return "Сосредоточьтесь на нежных ласках своего [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] медленно [npc.verb(slide)] [npc.her] [npc.fingers] вверх и вниз по всей длине [npc.her] [npc.cock+].",
					"Мягко скользя [npc.her] [npc.fingers+] вверх и вниз вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(jerk)] [npc.herself].",
					"Окутывая [npc.her] [npc.fingers] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] вырывается [npc.a_moan+], пока [npc.she] мягко [npc.verb(stroke)] вверх и вниз.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] приносит [npc.herself], [npc.name] [npc.verb(focus)] на нежных ласках [npc.her] [npc.fingers] вверх и вниз вокруг [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction DOM_NORMAL_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Ласки члена";
		}
		@Override
		public String getActionDescription() {
			return "Сосредоточьтесь на ласках своего [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(slide)] [npc.her] [npc.fingers] вверх и вниз по всей длине [npc.her] [npc.cock+].",
					"Отчаянно скользя [npc.her] [npc.fingers+] вверх и вниз вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] издает серию [npc.moans+], пока [npc.she] ритмично [npc.verb(jerk)] [npc.herself].",
					"Окутывая [npc.her] [npc.fingers] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] вырывается [npc.a_moan+], пока [npc.she] неистово [npc.verb(stroke)] вверх и вниз.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] доставляет [npc.herself], [npc.name] [npc.verb(focus)] на неистовом поглаживании [npc.her] [npc.fingers] вверх и вниз вокруг [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction DOM_ROUGH_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубые движения по члену";
		}
		@Override
		public String getActionDescription() {
			return "Сосредоточься на грубых движениях по своему [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] яростно [npc.verb(jerk)] [npc.her] [npc.fingers] вверх и вниз по всей длине [npc.her] [npc.cock+].",
					"Грубо двигая [npc.her] [npc.fingers+] вверх и вниз вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] издавая серию [npc.moans+], пока [npc.she] ритмично [npc.verb(jerk)] [npc.herself].",
					"Окутывая [npc.her] [npc.fingers] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] вырывается [npc.a_moan+], пока [npc.she] неистово [npc.verb(stroke)] вверх и вниз.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] доставляет [npc.herself], [npc.name] [npc.verb(focus)] на бешеном поглаживании [npc.her] [npc.fingers] вверх и вниз вокруг [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction SUB_NORMAL_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Ласки члена";
		}
		@Override
		public String getActionDescription() {
			return "Сосредоточьтесь на нежных ласках своего [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] [npc.verb(slide)] [npc.her] [npc.fingers] вверх и вниз по всей длине [npc.her] [npc.cock+].",
					"Скользя [npc.her] [npc.fingers+] вверх и вниз вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] издавая серию [npc.moans+], пока [npc.she] ритмично [npc.verb(jerk)] [npc.herself].",
					"Обернув [npc.her] [npc.fingers] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] издавая [npc.a_moan+], пока [npc.she] [npc.verb(stroke)] вверх и вниз.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] доставляет [npc.herself], [npc.name] [npc.verb(focus)] на поглаживании [npc.her] [npc.fingers] вверх и вниз вокруг [npc.her] [npc.cock+].");
		}
		
	};
	
	public static final SexAction SUB_EAGER_COCK_STROKING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Нетерпеливое ласкание члена";
		}
		@Override
		public String getActionDescription() {
			return "Сосредоточься на нетерпеливом ласкании своего [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(slide)] [npc.her] [npc.fingers] вверх и вниз по всей длине [npc.her] [npc.cock+].",
					"Отчаянно скользя [npc.her] [npc.fingers+] вверх и вниз вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(start)] издает серию [npc.moans+], пока [npc.she] ритмично [npc.verb(jerk)] [npc.herself].",
					"Окутывая [npc.her] [npc.fingers] вокруг [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] вырывается [npc.a_moan+], пока [npc.she] неистово [npc.verb(stroke)] вверх и вниз.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] доставляет [npc.herself], [npc.name] [npc.verb(focus)] на неистовом поглаживании [npc.her] [npc.fingers] вверх и вниз вокруг [npc.her] [npc.cock+].");
		}
	};
	
	public static final SexAction STOP_COCK_STROKING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Прекратить ласкать член";
		}
		@Override
		public String getActionDescription() {
			return "Перестать ласкать свой [npc.cock+].";
		}
		@Override
		public String getDescription() {
			return "С [npc.a_moan+], [npc.name] [npc.verb(take)] [npc.her] [npc.hand] в сторону от [npc.her] [npc.cock+] и [npc.verb(stop)] дрочит.";
		}
	};
}
