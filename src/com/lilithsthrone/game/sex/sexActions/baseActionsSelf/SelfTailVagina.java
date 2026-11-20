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
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfTailVagina {
	
	public static final SexAction SELF_TAIL_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Трахание хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Начать трахать [npc.her] [npc.pussy+] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"[npc.her] [npc.tail] извивается между [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] кончик над входом в [npc.her] [npc.pussy], [npc.moaning] в восторге, прежде чем вонзиться глубоко в [npc.herself].",
					"[npc.Name] [npc.verb(snake)] [npc.her] [npc.tail] между [npc.her] ног, [npc.moaning] в восторге, пока [npc.she] [npc.verb(force)] его глубоко в [npc.her], приглашая [npc.pussy].",
					"Скользя кончиком [npc.her] [npc.tail+] между складок заброшенных [npc.pussy] [npc.her], [npc.name] внезапно [npc.verb(thrust)] его глубоко внутрь, издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] трахает [npc.herself] хвостом.",
					"[npc.Name] с нетерпением [npc.verb(thrust)] [npc.her] [npc.tail+] глубоко в жаждущие [npc.pussy] [npc.her], издавая серию [npc.moans+], пока [npc.she] [npc.verb(start)] трахает [npc.herself] хвостом."));
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "Нежное трахание хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Нежно трахать [npc.her] [npc.pussy+] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] медленно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.pussy+].",
					"Мягко двигая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.herself].",
					"Медленно вонзая [npc.her] [npc.tail] глубоко в [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] тихий всхлип, пока [npc.she] [npc.verb(start)] нежно трахает [npc.herself].",
					"Сосредоточившись на удовольствии [npc.herself], [npc.name] [npc.verb(start)] мягко качает [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахание хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на том, чтобы трахать [npc.herself] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.pussy+].",
					"Вгоняя [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.herself].",
					"Вгоняя [npc.her] [npc.tail] глубоко в [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] вырывается [npc.a_moan], пока [npc.she] счастливо [npc.verb(start)] [npc.herself].",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] [npc.verb(start)] качает [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_TAIL_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый секс хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать [npc.herself] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.pussy+], прежде чем начать грубо трахать [npc.herself].",
					"Грубо толкая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] безжалостно [npc.verb(fuck)] [npc.herself].",
					"Вбивая [npc.her] [npc.tail] глубоко внутрь [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] [npc.a_moan], пока [npc.she] [npc.verb(start)], грубо перетирая его внутри [npc.herself].",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] [npc.verb(start)] грубо вколачивает [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахание хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на том, чтобы трахать [npc.herself] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.pussy+].",
					"Вгоняя [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.herself].",
					"Вгоняя [npc.her] [npc.tail] глубоко в [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] вырывается [npc.a_moan], пока [npc.she] счастливо [npc.verb(start)] [npc.herself].",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] [npc.verb(start)] качает [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+].");
		}
		
	};
	
	public static final SexAction SUB_SELF_TAIL_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный хвостотрах (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать [npc.herself] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], когда [npc.she] с жадностью [npc.verb(slam)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.pussy+], прежде чем начать отчаянно трахать [npc.herself].",
					"Энтузиазмом толкая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] неистово [npc.verb(fuck)] [npc.herself].",
					"Отчаянно вгоняя [npc.her] [npc.tail] глубоко внутрь [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] [npc.a_moan], пока [npc.she] [npc.verb(start)], жадно растирая его внутри [npc.herself].",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] жадно [npc.verb(start)] вколачивает [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SELF_TAIL_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить самокуннилингусом хвоста";
		}

		@Override
		public String getActionDescription() {
			return "Прекратить трахать [npc.herself] с помощью [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return "Выпуская [npc.a_moan], [npc.name] [npc.verb(slide)] [npc.her] [npc.tail+] из [npc.her] [npc.pussy+].";
		}
	};
}
