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
 * @author Innoxia
 * @version 0.4.0.0
 * @since 0.1.79
 */
public class SelfFingerNipple {
    public static final SexAction PINCH_NIPPLES = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.THREE_NORMAL,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "Сжимать соски (самой себе)";
        }

        @Override
        public String getActionDescription() {
            return "Поиграть со своими сосками.";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
                    "[npc.Name] [npc.verb(reach)] вверх и [npc.verb(start)], играя с [npc.her] твердыми [npc.nipples], сжимая и потирая их, пока [npc.she] [npc.moans] от возбуждения.",
                    "[npc.NamePos] кончики пальцев дразнят [npc.her] [npc.breasts+], останавливаясь, чтобы сжать и потянуть за [npc.her] [npc.nipples+], пока [npc.she] [npc.moan] и вздыхает от удовольствия.",
                    "[npc.Name] тянется к [npc.her] [npc.breasts+] и нетерпеливыми пальцами [npc.verb(start)] сжимать и теребить обнаженные [npc.nipples] [npc.her].",
                    "С [npc.a_moan+], [npc.name] [npc.verb(reach)] вверх к [npc.her] [npc.nipples+], сжимая и щелкая ими, пока [npc.she] продолжает вскрикивать от удовольствия."));

            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] просачивается сквозь [npc.her] кончики пальцев.");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append(" Маленькая струйка [npc.milk] вырывается сквозь [npc.her] кончики пальцев.");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append(" Струйка [npc.milk] стекает сквозь [npc.her] кончики пальцев.");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] начинает вытекать сквозь [npc.her] пальцы и стекать по [npc.her] [npc.breasts+].");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append(" [npc.Milk] начинает пускать слюни тонкой струйкой вокруг пальцев [npc.her].");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append(" [npc.Milk] начинает изливаться непрерывной струей, стекающей по [npc.her] [npc.breasts+].");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append(" [npc.Milk] начинает изливаться мощным потоком, быстро пропитывая [npc.her] [npc.breasts+].");
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


    public static final SexAction SELF_FINGER_NIPPLE_PENETRATION = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {
        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "Ласкать соски (самой себе)";
        }

        @Override
        public String getActionDescription() {
            return "Погрузить пальцы [npc.her] в [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
                    "[npc.Name] [npc.verb(reach)], издавая похотливый [npc.moan], пока [npc.she] жадно [npc.verb(sink)] [npc.her] пальцы в [npc.her] похотливые [npc.nipples].",
                    "Кончики пальцев [npc.NamePos] дразнят [npc.her] грудь, описывая круги вокруг [npc.her] [npc.nipples], прежде чем жадно погрузиться внутрь.",
                    "[npc.Name] [npc.moans] и [npc.verb(squeal)], пока [npc.she] жадно [npc.verb(start)] соски-киски [npc.her].",
                    "С похотливым криком, [npc.name] [npc.verb(sink)] [npc.her] пальцами в манящие соски-киски [npc.her], тяжело дыша, пока [npc.she] жадно [npc.verb(start)] [npc.herself]."));
            switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
                case ONE_TRICKLE:
                    UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] просачивается сквозь [npc.her] кончики пальцев.");
                    break;
                case TWO_SMALL_AMOUNT:
                    UtilText.nodeContentSB.append(" Маленькая струйка [npc.milk] вырывается сквозь [npc.her] кончики пальцев.");
                    break;
                case THREE_DECENT_AMOUNT:
                    UtilText.nodeContentSB.append(" Струйка [npc.milk] стекает сквозь [npc.her] кончики пальцев.");
                    break;
                case FOUR_LARGE_AMOUNT:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] начинает вытекать сквозь [npc.her] пальцы и стекать по [npc.her] [npc.breasts+].");
                    break;
                case FIVE_VERY_LARGE_DROOLING:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] начинает пускать слюни тонкой струйкой вокруг пальцев [npc.her].");
                    break;
                case SIX_EXTREME_AMOUNT_DRIPPING:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] начинает непрерывным потоком стекать по [npc.her] [npc.breasts+].");
                    break;
                case SEVEN_MONSTROUS_AMOUNT_POURING:
                    UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] начинает хлестать густым потоком, быстро пропитывая [npc.her] [npc.breasts+].");
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

    public static final SexAction DOM_SELF_FINGER_NIPPLE_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_GENTLE) {

        @Override
        public String getActionTitle() {
            return "Нежное ласкание сосков пальцами (самой)";
        }

        @Override
        public String getActionDescription() {
            return "Нежно ласкать пальцами [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] вытекает сквозь [npc.namePos] [npc.lips+], пока [npc.she] медленно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.nipple+].",
                    "Мягко вводя и выводя [npc.her] [npc.fingers] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Сворачивая [npc.her] [npc.fingers] внутри [npc.her] [npc.nipple(true)], [npc.name] [npc.verb(let)] издает тихий скулеж, пока [npc.she] [npc.verb(start)] водит [npc.her] пальцами туда-сюда в [npc.her] [npc.breast+].",
                    "Сосредоточившись на доставлении удовольствия [npc.her] похотливой [npc.breasts], [npc.name] [npc.verb(start)] мягко водит [npc.her] [npc.fingers] туда-сюда в одном из [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_NORMAL) {

        @Override
        public String getActionTitle() {
            return "Ласкание сосков пальцами (самой)";
        }

        @Override
        public String getActionDescription() {
            return "Сосредоточиться на ласках пальцами [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] вытекает сквозь [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.nipple+].",
                    "[npc.her] [npc.fingers] ритмично входят и выходят из [npc.her] [npc.nipple+], при этом [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Подкручивая [npc.her] [npc.fingers] внутри [npc.her] охренительного [npc.nipple(true)], [npc.name] [npc.verb(let)] наружу, [npc.a_moan], пока [npc.she] [npc.verb(start)] ритмично водит [npc.her] пальцами туда-сюда в [npc.her] [npc.breast+].",
                    "Сосредоточившись на доставлении удовольствия [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] качает [npc.her] [npc.fingers] в одном из [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction DOM_SELF_FINGER_NIPPLE_ROUGH = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.DOM_ROUGH) {

        @Override
        public String getActionTitle() {
            return "Грубые ласки сосков (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Грубо перебирая пальцами [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.nipple+], после чего начинает стремительно ласкать пальцами [npc.her] [npc.breast(true)].",
                    "Грубо качая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Сильно подкручивая [npc.her] [npc.fingers] внутри [npc.her] охренительного [npc.nipple(true)], [npc.name] [npc.verb(let)] наружу, [npc.a_moan], пока [npc.she] [npc.verb(start)] грубо втирает [npc.her] пальцы туда-сюда в [npc.her] [npc.breast+].",
                    "Сосредоточившись на ласках похотливой [npc.breasts+], [npc.name] [npc.verb(start)] грубо вколачивает [npc.her] [npc.fingers] туда-сюда в один из [npc.her] [npc.nipples+] [npc.her].");
        }

    };

    public static final SexAction SUB_SELF_FINGER_NIPPLE_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.SUB_NORMAL) {

        @Override
        public String getActionTitle() {
            return "Ласка сосков пальцами (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Сосредоточиться на ласках пальцами [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] вытекает сквозь [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.nipple+].",
                    "[npc.her] [npc.fingers] ритмично входят и выходят из [npc.her] [npc.nipple+], при этом [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Подкручивая [npc.her] [npc.fingers] внутри [npc.her] охренительного [npc.nipple(true)], [npc.name] [npc.verb(let)] наружу, [npc.a_moan], пока [npc.she] [npc.verb(start)] ритмично водит [npc.her] пальцами туда-сюда в [npc.her] [npc.breast+].",
                    "Сосредоточившись на доставлении удовольствия [npc.her] [npc.breasts+], [npc.name] [npc.verb(start)] качает [npc.her] [npc.fingers] в одном из [npc.her] [npc.nipples+].");
        }

    };

    public static final SexAction SUB_SELF_FINGER_NIPPLE_EAGER = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF,
            SexPace.SUB_EAGER) {

        @Override
        public String getActionTitle() {
            return "Нетерпеливая ласка сосков пальцами (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "С жадностью перебирая пальцами [npc.her] [npc.nipples+].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] с нетерпением [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.nipple+], прежде чем начать отчаянно перебирать пальцами [npc.her] [npc.breast(true)].",
                    "С энтузиазмом заставляя [npc.her] [npc.fingers] двигаться туда-сюда в [npc.her] [npc.nipple+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] неистово [npc.verb(finger)] [npc.her] [npc.breast+].",
                    "Отчаянно сжимая [npc.her] [npc.fingers] внутри [npc.her] податливого [npc.nipple(true)], [npc.name] [npc.verb(let)] наружу [npc.a_moan], пока [npc.she] [npc.verb(start)] с жадностью водит [npc.her] пальцами туда-сюда в [npc.her] [npc.breast+].",
                    "Сосредоточившись на ласках похотливой [npc.breasts+], [npc.name] с энтузиазмом [npc.verb(start)] вколачивает [npc.her] [npc.fingers] туда-сюда в один из [npc.her] [npc.nipples+] [npc.her].");
        }

    };

    public static final SexAction SELF_FINGER_NIPPLE_STOP_PENETRATION = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
            SexParticipantType.SELF) {

        @Override
        public String getActionTitle() {
            return "Перестать ласкать соски (самой)";
        }

        @Override
        public String getActionDescription() {
            return "Перестать ласкать [npc.her] [npc.nipples+] пальцами.";
        }

        @Override
        public String getDescription() {
            return "Издавая удовлетворенный [npc.moan], [npc.name] [npc.verb(slide)] вынимает [npc.her] пальцы из [npc.her] [npc.nipples+].";
        }
    };
}
