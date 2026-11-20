package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
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
public class SelfFingerAnus {

    public static final SexAction SELF_FINGER_ANUS_SPREAD_ASS = new SexAction(
            SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
            ArousalIncrease.TWO_LOW,
            ArousalIncrease.TWO_LOW,
            CorruptionLevel.ONE_VANILLA,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF) {

        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
        }

        @Override
        public String getActionTitle() {
            return "Развести ягодицы";
        }

        @Override
        public String getActionDescription() {
            return "Используй свои [npc.hands], чтобы развести ягодицы.";
        }

        @Override
        public String getDescription() {
            if (Main.sex.isMasturbation()) {
                if (Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.ALL_FOURS)) {
                    return UtilText.returnStringAtRandom(
                            "Протянув одну [pc.hand] назад, ты хватаешь свою [pc.ass+] и оттягиваешь её в сторону, издавая [pc.a_moan+], пока разводишь свои [pc.asshole+].",
                            "Ты тянешься назад одной [pc.hand], тихо стоная, когда берешься за свою [pc.ass+], а затем соблазнительно оттягиваешь её в сторону, открывая свои [pc.asshole+].",
                            "Проводя кончиками пальцев по своей [pc.asshole+], ты издаешь [pc.a_moan+], хватая одну из своих [pc.assSize] ягодиц и оттягивая её в сторону, чтобы продемонстрировать свои [pc.asshole+].",
                            "Ты жадно ведешь своей [pc.fingers] по своим нетерпеливым [pc.asshole], [pc.moaning+], используя свою [pc.hand], чтобы оттянуть ягодицу в сторону и открыться для анального проникновения.");
                } else {
                    return UtilText.returnStringAtRandom(
                            "Протянув обе [pc.hands] назад, ты хватаешь свои [pc.assSize] ягодицы и разводишь их в стороны, издавая [pc.a_moan+], пока разводишь свои [pc.asshole+].",
                            "Ты тянешься назад обеими [pc.hands], тихо стоная, когда соблазнительно разводишь свои [pc.assSize] ягодицы, открывая свои [pc.asshole+].",
                            "Проводя кончиками пальцев по своей [pc.asshole+], ты издаешь [pc.a_moan+], хватая свои [pc.assSize] ягодицы и разводя их в стороны, чтобы продемонстрировать свои [pc.asshole+].",
                            "Ты жадно ведешь своей [pc.fingers] по своим нетерпеливым [pc.asshole], [pc.moaning+], используя свою [pc.hands], чтобы развести ягодицы в стороны и открыться для анального проникновения.");
                }
            } else if (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
                return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
                        UtilText.returnStringAtRandom(
                                "Протягивая назад одну [npc.hand], [npc.name] [npc.verb(grab)] [npc.her] [npc.ass+] и [npc.verb(pull)] в сторону, издавая [npc.a_moan+], пока [npc.she] [npc.verb(present)] [npc.her] [npc.asshole+] к [npc2.name].",
                                "[npc.Name] [npc.verb(reach)] назад одной [npc.hand], тихо стоная, пока [npc.she] [npc.verb(grab)] удерживает [npc.her] [npc.ass+], а затем соблазнительно оттягивает в сторону, демонстрируя [npc.her] [npc.asshole+] для [npc2.name].",
                                "Скользя кончиками [npc.her] по [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(grab)] одну из [npc.her] [npc.assSize] ягодиц и [npc.verb(pull)] в сторону, чтобы представить [npc.her] [npc.asshole+] для [npc2.name].",
                                "[npc.Name] жадно [npc.verb(slide)] [npc.her] [npc.fingers] над [npc.her] нетерпеливой [npc.asshole], [npc.moaning+], как [npc.she] [npc.verb(use)] [npc.her] [npc.hand], чтобы оттянуть [npc.her] ягодицу в сторону и [npc.verb(present)] [npc.herself] для анального проникновения."));
            } else {
                return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()),
                        UtilText.returnStringAtRandom(
                                "Протягивая назад обе [npc.hands], [npc.name] [npc.verb(grab)] [npc.her] [npc.assSize] ягодиц и [npc.verb(pull)] их в стороны, издавая [npc.a_moan+], пока [npc.she] [npc.verb(present)] [npc.her] [npc.asshole+] к [npc2.name].",
                                "[npc.Name] [npc.verb(reach)] назад обеими [npc.hands], тихо стоная, пока [npc.she] соблазнительно [npc.verb(pull)] [npc.her] [npc.assSize] ягодицы в стороны и [npc.verb(present)] [npc.her] [npc.asshole+] к [npc2.name].",
                                "Скользя кончиками [npc.her] по [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(grab)] [npc.her] [npc.assSize] ягодицы и [npc.verb(pull)] раздвигает их, чтобы представить [npc.her] [npc.asshole+] для [npc2.name].",
                                "[npc.Name] жадно [npc.verb(slide)] [npc.her] [npc.fingers] по [npc.her] нетерпеливым [npc.asshole], [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] [npc.hands], чтобы отвести [npc.her] ягодицы в стороны и [npc.verb(present)] [npc.herself] для анального проникновения."));
            }
        }
    };

    public static final SexAction SELF_FINGER_ANUS_PENETRATION = new SexAction(
            SexActionType.START_ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF) {
        @Override
        public String getActionTitle() {
            return "Анальный фингерстинг (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Начать ласкать анус [npc.her].";
        }

        @Override
        public boolean isBaseRequirementsMet() {
            return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "Протянув руку назад, чтобы [npc.her] [npc.ass], [npc.name] [npc.verb(tease)] [npc.her] пальцев по входу в [npc.her] [npc.asshole+], прежде чем протолкнуть их внутрь и издать [npc.a_moan+].",
                    "[npc.Name] [npc.verb(probe)] [npc.her] пальцами по [npc.her] [npc.ass], [npc.moaning+], пока [npc.she] [npc.verb(push)] два [npc.her] пальца в [npc.her] соблазнительное [npc.asshole].",
                    "Скользя кончиками [npc.her] по забытому [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] наружу [npc.groan+], пока [npc.she] [npc.verb(push)] [npc.her] пальцев внутрь.",
                    "[npc.Name] с нетерпением [npc.verb(push)] [npc.her] пальцами в [npc.her] жаждущую [npc.asshole], [npc.moaning+] пока [npc.she] [npc.verb(start)] вгоняет [npc.her] пальцы в [npc.her] [npc.ass].");
        }
    };

    public static final SexAction DOM_SELF_FINGER_ANUS_GENTLE = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.DOM_GENTLE) {
        @Override
        public String getActionTitle() {
            return "Нежное проникновение пальцами в анус (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Нежно ласкать пальцами [npc.her] [npc.asshole].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] медленно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.asshole+].",
                    "Мягко двигая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.ass].",
                    "Сгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] тихонько поскуливая, пока [npc.she] [npc.verb(start)] "
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "нежно поглаживая простату [npc.her]."
                            : "нежно лаская пальцами [npc.her] [npc.ass+]."),
                    "Сосредоточившись на доставлении удовольствия [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] мягко двигает [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.asshole+].");
        }
    };

    public static final SexAction DOM_SELF_FINGER_ANUS_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.DOM_NORMAL) {
        @Override
        public String getActionTitle() {
            return "Анальный фингерстинг (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Сосредоточиться на ласках пальцами [npc.her] [npc.ass].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.asshole+].",
                    "Вгоняя [npc.her] [npc.fingers] то входит, то выходит из [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.ass].",
                    "Подгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] извлекает [npc.a_moan], пока [npc.she] [npc.verb(start)] "
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "ласкает [npc.her] простату."
                            : "пальцами исследует [npc.her] [npc.ass+]."),
                    "Сосредоточившись на доставлении удовольствия [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] вгоняет [npc.her] [npc.fingers] то входит, то выходит из [npc.her] [npc.asshole+].");
        }
    };

    public static final SexAction DOM_SELF_FINGER_ANUS_ROUGH = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.THREE_DIRTY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.DOM_ROUGH) {
        @Override
        public String getActionTitle() {
            return "Грубый анальный фингерстинг (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Грубо ласкает пальцами [npc.her] [npc.ass].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], когда [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.asshole+], после чего начинает грубо перебирать пальцами [npc.her] [npc.ass].",
                    "Грубо толкая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)], издавая серию восторженных [npc.moans], пока [npc.she] безжалостно [npc.verb(finger)] свои собственные [npc.ass] [npc.her].",
                    "Сильно сгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] [npc.a_moan], пока [npc.she] [npc.verb(start)] "
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "грубо втирая кончики [npc.her] в простату [npc.her]."
                            : "грубо водя пальцами [npc.her] туда-сюда в [npc.her] [npc.ass+]."),
                    "Сосредоточившись на ласках [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] грубо вколачивая [npc.her] [npc.fingers] в [npc.her] [npc.asshole+].");
        }
    };

    public static final SexAction SUB_SELF_FINGER_ANUS_NORMAL = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.TWO_HORNY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.SUB_NORMAL) {
        @Override
        public String getActionTitle() {
            return "Анальный фингерстинг (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Сосредоточиться на ласках пальцами [npc.her] [npc.ass].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.asshole+].",
                    "Вгоняя [npc.her] [npc.fingers] то входит, то выходит из [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издает серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.her] [npc.ass].",
                    "Подгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] извлекает [npc.a_moan], пока [npc.she] [npc.verb(start)] "
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "ласкает [npc.her] простату."
                            : "пальцами исследует [npc.her] [npc.ass+]."),
                    "Сосредоточившись на доставлении удовольствия [npc.her] [npc.ass+], [npc.name] [npc.verb(start)] вгоняет [npc.her] [npc.fingers] то входит, то выходит из [npc.her] [npc.asshole+].");
        }
    };

    public static final SexAction SUB_SELF_FINGER_ANUS_EAGER = new SexAction(
            SexActionType.ONGOING,
            ArousalIncrease.THREE_NORMAL,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.THREE_DIRTY,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF,
            SexPace.SUB_EAGER) {
        @Override
        public String getActionTitle() {
            return "Жадное анальное фингердинг (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Жадно перебирая пальцами [npc.her] [npc.ass].";
        }

        @Override
        public String getDescription() {
            return UtilText.returnStringAtRandom(
                    "[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.asshole+], прежде чем начать отчаянно перебирать пальцами [npc.her] [npc.ass].",
                    "Энергично толкая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.asshole+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] неистово [npc.verb(finger)] свои [npc.her] [npc.ass].",
                    "Отчаянно сгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.asshole], [npc.name] [npc.verb(let)] [npc.a_moan], пока [npc.she] [npc.verb(start)] "
                            + (Main.sex.getCharacterPerformingAction().hasPenis() && !Main.sex.getCharacterPerformingAction().hasVagina()
                            ? "жадно втирая кончики [npc.her] в простату [npc.her]."
                            : "жадно водя [npc.her] пальцами туда-сюда в [npc.her] [npc.ass+]."),
                    "Сосредоточившись на ласках [npc.her] [npc.ass+], [npc.name] с жадностью [npc.verb(start)] вгоняет [npc.her] [npc.fingers] в [npc.her] [npc.asshole+].");
        }
    };

    public static final SexAction SELF_FINGER_ANUS_STOP_PENETRATION = new SexAction(
            SexActionType.STOP_ONGOING,
            ArousalIncrease.ONE_MINIMUM,
            ArousalIncrease.ONE_MINIMUM,
            CorruptionLevel.ZERO_PURE,
            Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
            SexParticipantType.SELF) {
        @Override
        public String getActionTitle() {
            return "Прекратить анальный фингерстинг (самостоятельно)";
        }

        @Override
        public String getActionDescription() {
            return "Прекратить фингерстинг [npc.her] задницы.";
        }

        @Override
        public String getDescription() {
            return "С [npc.a_groan+], [npc.name] [npc.verb(slide)] вынимает [npc.her] пальцы из [npc.her] [npc.asshole+].";
        }
    };
}
