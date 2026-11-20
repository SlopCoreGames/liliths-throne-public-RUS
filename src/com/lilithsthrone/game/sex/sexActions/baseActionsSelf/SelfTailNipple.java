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
public class SelfTailNipple {
	
	public static final SexAction SELF_TAIL_NIPPLE_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Задница-фаллос сосков (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Использовать [npc.her] [npc.tail+], чтобы трахать свои собственные [npc.nipples+] [npc.her].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Извивающийся [npc.her] [npc.tail] ползет вверх к [npc.her] [npc.breasts+], [npc.name] [npc.verb(tease)] кончик над [npc.her] [npc.nipples], [npc.moaning] в восторге, прежде чем вонзиться глубоко внутрь.",
					"[npc.Name] [npc.verb(snake)] [npc.her] [npc.tail] вверх к [npc.her] [npc.breasts+], [npc.moaning] в восторге, пока [npc.she] [npc.verb(force)] его глубоко в манящий [npc.nipple(true)].",
					"Скользя кончиком [npc.her] [npc.tail+] вверх к [npc.her] [npc.breasts+], [npc.name] [npc.verb(thrust)] его глубоко внутрь манящего [npc.nipple(true)], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] трахает хвостом свои собственные [npc.breasts] [npc.her].",
					"[npc.Name] жадно [npc.verb(thrust)] [npc.her] [npc.tail+] глубоко в жаждущий [npc.nipple(true)], издавая серию [npc.moans+], пока [npc.she] [npc.verb(start)] трахает хвостом свои собственные [npc.breasts] [npc.her]."));
		
			switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] сочится вокруг [npc.her] [npc.tail].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" Небольшая порция [npc.milk] выплескивается вокруг [npc.her] [npc.tail].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" Струйка [npc.milk] стекает вокруг [npc.her] [npc.tail].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] начинает вытекать вокруг [npc.her] [npc.tail] и стекать по [npc.her] [npc.breasts].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc.Milk] начинает пускать слюни тонкой струйкой по [npc.her] [npc.tail].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc.Milk] начинает изливаться непрерывной струей, стекающей по [npc.her] [npc.breasts].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc.Milk] начинает изливаться мощным потоком, быстро пропитывая [npc.her] [npc.breasts].");
					break;
				default:
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный секс соском-хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Нежно трахать [npc.her] [npc.nipples+] с помощью [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] медленно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.nipple+].",
					"Нежные покачивания [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.nipple+], при этом [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Медленное вбивание [npc.her] [npc.tail] глубоко в [npc.her] [npc.nipple(true)], [npc.name] [npc.verb(let)] тихо скулит, пока [npc.she] [npc.verb(start)] нежно водит им туда-сюда в [npc.her] [npc.breast+].",
					"Сосредоточившись на ласках [npc.her], пригодного для траха [npc.breasts], [npc.name] [npc.verb(start)] нежно качает [npc.her] [npc.tail] туда-сюда в одном из [npc.her] [npc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Секс соском-хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на трахтинге [npc.her] [npc.nipples+] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.nipple+].",
					"Вгоняя [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Вбивая [npc.her] [npc.tail] глубоко внутрь податливого [npc.nipple(true)] [npc.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan], пока [npc.she] [npc.verb(start)] качает его туда-сюда в [npc.her] [npc.breast+].",
					"Сосредоточиться на доставлении удовольствия [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] качая [npc.her] [npc.tail] туда-сюда в одном из [npc.her] [npc.nipples+].");
		}
		
	};
	
	public static final SexAction DOM_SELF_TAIL_NIPPLE_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый трах хвостом в сосок (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахнуть [npc.her] [npc.nipples+] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.nipple+], прежде чем начать стремительно трахать [npc.her] [npc.breast(true)].",
					"Грубо вгоняя [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"С силой вонзая [npc.her] [npc.tail] глубоко внутрь [npc.her] возбужденного [npc.nipple(true)], [npc.name] [npc.verb(let)] наружу [npc.a_moan], пока [npc.she] [npc.verb(start)] грубо втирая его туда-сюда в [npc.her] [npc.breast+].",
					"Сосредоточившись на удовольствии [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] грубо вколачивает [npc.her] [npc.tail] туда-сюда в один из [npc.her] [npc.nipples+].");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_NIPPLE_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Секс соском-хвостом (самоудовлетворение)";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на трахтинге [npc.her] [npc.nipples+] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.nipple+].",
					"Вгоняя [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Вбивая [npc.her] [npc.tail] глубоко внутрь податливого [npc.nipple(true)] [npc.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan], пока [npc.she] [npc.verb(start)] качает его туда-сюда в [npc.her] [npc.breast+].",
					"Сосредоточиться на доставлении удовольствия [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] качая [npc.her] [npc.tail] туда-сюда в одном из [npc.her] [npc.nipples+].");
		}
	};
	
	public static final SexAction SUB_SELF_TAIL_NIPPLE_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахать сосок хвостом (самой себе)";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать [npc.her] [npc.nipples+] с помощью [npc.her] [npc.tail+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] с жадностью [npc.verb(slam)] [npc.her] [npc.tail] глубоко внутри [npc.her] [npc.nipple+], прежде чем начать отчаянно трахать [npc.her] [npc.breast(true)].",
					"Энтузиазмом качая [npc.her] [npc.tail] туда-сюда в [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] неистово [npc.verb(fuck)] [npc.her] [npc.breast+].",
					"Отчаянно вгоняя [npc.her] [npc.tail] глубоко внутрь [npc.her] похотливых [npc.nipple(true)], [npc.name] [npc.verb(let)] [npc.a_moan], пока [npc.she] [npc.verb(start)] с жадностью водит им туда-сюда в [npc.her] [npc.breast+].",
					"Сосредоточившись на удовольствии [npc.her] [npc.breasts+], [npc.name] с жадностью [npc.verb(start)] вколачивает [npc.her] [npc.tail] туда-сюда в один из [npc.her] [npc.nipples+].");
		}
	};
	
	public static final SexAction SELF_TAIL_NIPPLE_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Прекратить трахать сосок хвостом (самой себе)";
		}

		@Override
		public String getActionDescription() {
			return "Хватит, блядь, [npc.her] [npc.nipple+] своими [npc.her] [npc.tail].";
		}

		@Override
		public String getDescription() {
			return "Издавая удовлетворенный [npc.moan], [npc.name] [npc.verb(slide)] [npc.her] [npc.tail+] из своих [npc.her] [npc.nipple+].";
		}
	};
}
