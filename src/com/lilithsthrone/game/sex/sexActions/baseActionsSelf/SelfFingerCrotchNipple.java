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
 * @since 0.3.1
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerCrotchNipple {
	
	public static final SexAction PINCH_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}
		
		@Override
		public String getActionTitle() {
			return "Сжимать соски в паху";
		}

		@Override
		public String getActionDescription() {
			return "Играть с [npc.her] [npc.crotchNipples].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(reach)] вниз и [npc.verb(start)], играя с твердыми [npc.her] [npc.crotchNipples], щипая и потирая их, пока [npc.she] [npc.moans] с возбуждением.",
					"Кончики [npc.NamePos] дразнят [npc.her] [npc.crotchBoobs], останавливаясь, чтобы ущипнуть и потянуть за [npc.her] [npc.crotchNipples], пока [npc.she] [npc.verb(moan)] и [npc.verb(sigh)] в восторге.",
					"[npc.Name] [npc.verb(reach)] вниз к [npc.her] [npc.crotchBoobs], и нетерпеливыми пальцами [npc.verb(start)], чтобы щипать и тереть обнаженные [npc.her] [npc.crotchNipples].",
					"[npc.NamePos] [npc.crotchNipples] просто умоляют о внимании, и [npc.she] [npc.verb(whine)] в восторге, когда [npc.she] [npc.verb(reach)] вниз, начиная их щипать."));
			
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" Тонкая струйка [npc.crotchMilk] вытекает на кончики [npc.her] пальцев.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" Небольшая порция [npc.crotchMilk] выплескивается на кончики [npc.her] пальцев.");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" Струйка [npc.crotchMilk] стекает по кончикам [npc.her] пальцев.");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает вытекать сквозь [npc.her] пальцы и стекать по [npc.her] [npc.crotchBoobs+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает сочиться тонкой струйкой по [npc.her] пальцам.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает литься непрерывным потоком, стекая по [npc.her] [npc.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает хлестать густым потоком, быстро пропитывая [npc.her] [npc.crotchBoobs+].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction SELF_FINGER_NIPPLE_CROTCH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs();
		}

		@Override
		public String getActionTitle() {
			return "Ласкать соски в паху";
		}

		@Override
		public String getActionDescription() {
			return "Погрузить пальцы [npc.her] в [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(reach)] вниз, издавая похотливый [npc.moan], пока [npc.she] жадно [npc.verb(sink)] [npc.her] пальцы в [npc.her] похотливые [npc.crotchNipples].",
					"Кончики пальцев [npc.NamePos] [npc.verb(tease)] по [npc.her] [npc.crotchBoobs], описывая круги вокруг [npc.her] [npc.crotchNipples], прежде чем жадно погрузиться внутрь.",
					"[npc.Name] [npc.moan] и [npc.verb(squeal)], пока [npc.she] [npc.verb(start)] жадно ласкает пальцами [npc.her] соски-киски.",
					"С похотливым криком [npc.name] погружает [npc.her] пальцев в манящие соски-киски [npc.her], тяжело дыша, пока [npc.she] [npc.verb(start)] жадно ласкает [npc.herself]."));
		
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" Тонкая струйка [npc.crotchMilk] просачивается сквозь кончики пальцев [npc.her].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" Небольшая струйка [npc.crotchMilk] вырывается сквозь кончики пальцев [npc.her].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" Струйка [npc.crotchMilk] стекает сквозь кончики пальцев [npc.her].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает вытекать сквозь [npc.her] пальцев и стекать по [npc.her] [npc.crotchBoobs+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает сочиться тонкой струйкой сквозь [npc.her] пальцев.");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает литься непрерывным потоком, стекая по [npc.her] [npc.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.crotchMilk] начинает хлестать густым потоком, быстро пропитывая [npc.her] [npc.crotchBoobs+].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежное ласкание сосков пальцами (самой)";
		}

		@Override
		public String getActionDescription() {
			return "Нежно перебирать пальцами [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.her] [npc.lips+], пока [npc.she] медленно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.crotchNipple+].",
					"Мягко водя [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(find)] [npc.herself], издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Подгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.crotchNipple], [npc.she] [npc.verb(let)] тихий всхлип, пока [npc.she] [npc.verb(start)] водит [npc.her] пальцами туда-сюда в [npc.her] [npc.crotchBoob+].",
					"Сосредоточившись на ласках [npc.her] похотливой [npc.crotchBoobs], [npc.she] [npc.verb(start)] мягко водит [npc.her] [npc.fingers] в одном из [npc.her] [npc.crotchNipples+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласкание сосков пальцами (самой)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на ласках [npc.her] [npc.crotchNipples+] пальцами.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.her] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.crotchNipple+].",
					"Вгоняя [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.crotchNipple+], [npc.she] замечает, как [npc.herself] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Подкручивая [npc.her] [npc.fingers] внутри [npc.her] податливых [npc.crotchNipple], [npc.she] [npc.verb(let)] извлекает [npc.a_moan], пока [npc.she] [npc.verb(start)] вгоняет [npc.her] пальцы туда-сюда в [npc.her] [npc.crotchBoob+].",
					"Сосредоточившись на ласках [npc.her] [npc.crotchBoobs+], [npc.she] [npc.verb(start)] вгоняет [npc.her] [npc.fingers] туда-сюда в одну из [npc.her] [npc.crotchNipples+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_NIPPLE_CROTCH_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубые ласки сосков (самостоятельно)";
		}

		@Override
		public String getActionDescription() {
			return "Грубо перебирать пальцами [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.her] [npc.lips+], пока [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.crotchNipple+], прежде чем начать стремительно перебирать пальцами [npc.her] [npc.crotchBoob].",
					"Грубо вгоняя [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.crotchNipple+], [npc.she] замечает, как [npc.herself] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Сильно сгибая [npc.her] [npc.fingers] внутри [npc.her] похотливого [npc.crotchNipple], [npc.she] [npc.verb(let)] [npc.a_moan], пока [npc.she] [npc.verb(start)] грубо водит [npc.her] пальцами туда-сюда в [npc.her] [npc.crotchBoob+].",
					"Сосредоточившись на ласках [npc.her] похотливого [npc.crotchBoobs+], [npc.she] [npc.verb(start)] грубо вбивает [npc.her] [npc.fingers] туда-сюда в один из [npc.her] [npc.crotchNipples+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_NIPPLE_CROTCH_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласка сосков пальцами (самостоятельно)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на ласках [npc.her] [npc.crotchNipples+] пальцами.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.her] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.crotchNipple+].",
					"Вколачивая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(find)] [npc.herself], издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Подкручивая [npc.her] [npc.fingers] внутри [npc.her] податливых [npc.crotchNipple], [npc.she] [npc.verb(let)] извлекает [npc.a_moan], пока [npc.she] [npc.verb(start)] вгоняет [npc.her] пальцы туда-сюда в [npc.her] [npc.crotchBoob+].",
					"Сосредоточившись на ласках [npc.her] [npc.crotchBoobs+], [npc.she] [npc.verb(start)] вгоняет [npc.her] [npc.fingers] туда-сюда в одну из [npc.her] [npc.crotchNipples+].");
		}
		
	};
	
	public static final SexAction SUB_SELF_FINGER_NIPPLE_CROTCH_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Нетерпеливая ласка сосков пальцами (самостоятельно)";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью ласкать [npc.her] [npc.crotchNipples+] пальцами.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.her] [npc.lips+], когда [npc.she] с жадностью [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.crotchNipple+], после чего она начинает отчаянно перебирать пальцами [npc.her] [npc.crotchBoob].",
					"Энтузиазмом вколачивая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.crotchNipple+], [npc.she] обнаруживает, что [npc.herself] издает серию восторженных [npc.moans], пока [npc.she] неистово [npc.verb(finger)] [npc.her] [npc.crotchBoob+].",
					"Отчаянно сжимая [npc.her] [npc.fingers] внутри [npc.her] похотливой [npc.crotchNipple], [npc.she] [npc.verb(let)] [npc.a_moan], пока [npc.she] [npc.verb(start)] с жадностью водит [npc.her] пальцами туда-сюда в [npc.her] [npc.crotchBoob+].",
					"Сосредоточившись на удовольствии [npc.her] похотливой [npc.crotchBoobs+], [npc.she] с нетерпением [npc.verb(start)] вколачивает [npc.her] [npc.fingers] туда-сюда в один из [npc.her] [npc.crotchNipples+].");
		}

	};
	
	public static final SexAction SELF_FINGER_NIPPLE_CROTCH_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Перестать ласкать соски (самой)";
		}

		@Override
		public String getActionDescription() {
			return "Перестать перебирать пальцами [npc.her] [npc.crotchNipples+].";
		}

		@Override
		public String getDescription() {
			return "Издавая удовлетворенный [npc.moan], [npc.she] [npc.verb(slide)] [npc.her] пальцы из своей [npc.her] [npc.crotchNipples+].";
		}
	};
}
