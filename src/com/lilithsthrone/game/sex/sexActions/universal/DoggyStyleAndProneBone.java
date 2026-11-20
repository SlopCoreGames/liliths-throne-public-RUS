package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.4.11.2
 * @author Innoxia
 */
public class DoggyStyleAndProneBone {
	
	private static boolean suitablePositionsForPullingAndGrabbing(GameCharacter performer, GameCharacter target) {
		boolean suitableSlot = false;
		
		// Doggy-style check:
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS_TWO) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND_TWO
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING_TWO;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS_THREE) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND_THREE
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING_THREE;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotAllFours.ALL_FOURS_FOUR) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.BEHIND_FOUR
					|| Main.sex.getSexPositionSlot(performer)==SexSlotAllFours.HUMPING_FOUR;
		}
		
		// Prone bone check:
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT_TWO) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY_TWO;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT_THREE) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY_THREE;
		}
		if(Main.sex.getSexPositionSlot(target)==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) {
			suitableSlot = Main.sex.getSexPositionSlot(performer)==SexSlotLyingDown.MISSIONARY_FOUR;
		}
		
		return suitableSlot;
	}
	
	public static final SexAction PULL_HAIR = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тянуть за волосы";
		}

		@Override
		public String getActionDescription() {
			return "Схватить волосы [npc2.namePos] и оттянуть [npc2.her] голову назад.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
			
			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] осторожно [npc.verb(take)] берет [npc2.namePos] [npc2.hair(true)], и, продолжая ритмично вводить и выводить [npc.her] [npc.cock] в [npc2.her] [npc2."+tag+"+], [npc.she] медленно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Мягко обхватив волосы [npc2.namePos] [npc2.hair(true)] одной [npc.hand], [npc.name] одновременно [npc.verb(pull)] [npc2.her] голову назад, глубоко вбивая [npc.her] [npc.cock+] в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" тихо хихикая":" ухмыляясь")+" [npc.herself], пока [npc2.name] [npc2.verb(let)] извлекает [npc2.moan+].",
								"Продолжая медленно вводить и выводить [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед и [npc.verb(take)] мягко берет [npc2.her] [npc2.hair(true)], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] осторожно [npc.verb(take)] хватает [npc2.namePos] за [npc2.hair(true)], а затем медленно оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Аккуратно взяв [npc2.namePos] [npc2.hair(true)] в одну [npc.hand], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" мягко [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" к [npc.herself], пока [npc.she] [npc.verb(pull)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль всей спины [npc2.namePos], [npc.name] [npc.verb(reach)] вперед и [npc.verb(take)] нежно хватает [npc2.her] [npc2.hair(true)], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] хватает горсть [npc2.namePos] [npc2.hair(true)], и, продолжая с силой вколачивать [npc.her] [npc.cock] туда-сюда в [npc2.her] [npc2."+tag+"+], [npc.she] резко [npc.verb(yank)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив горсть [npc2.namePos] [npc2.hair(true)], [npc.name] одновременно [npc.verb(yank)] оттягивает [npc2.her] голову назад, с силой вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально хихикая":" глубоко рыча")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая грубо вбивать [npc.her] [npc.cock+] туда-сюда в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед и [npc.verb(grab)] пригоршню [npc2.her] [npc2.hair(true)], прежде чем с силой дернуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] горсть [npc2.namePos] [npc2.hair(true)], после чего резко дернул [npc2.her] голову назад, заставив [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив пригоршню [npc2.namePos] [npc2.hair(true)], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально [npc.verb(giggle)]":" глубоко [npc.verb(grunt)]")
									+" когда [npc.she] яростно [npc.verb(yank)] голову [npc2.her] назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Ощупывая и поглаживая [npc.her] вдоль всей спины [npc2.namePos], [npc.name] [npc.verb(reach)] вперед и [npc.verb(grab)] пригоршню [npc2.her] [npc2.hair(true)], прежде чем грубо дернуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] крепко [npc.verb(take)] за [npc2.namePos] [npc2.hair(true)], и, продолжая ритмично вбивать [npc.her] [npc.cock] туда-сюда в [npc2.her] [npc2."+tag+"+], [npc.she] уверенно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Крепко сжимая [npc2.namePos] [npc2.hair(true)] в одной [npc.hand], [npc.name] одновременно [npc.verb(pull)] [npc2.her] голову назад, мощно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо хихикая":" кряхтя")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая энергично вгонять и вынимать [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед и [npc.verb(take)] крепко сжимает [npc2.her] [npc2.hair(true)], после чего оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] крепко [npc.verb(take)] захватывает [npc2.namePos] [npc2.hair(true)], после чего уверенно тянет [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Крепко сжимая [npc2.namePos] [npc2.hair(true)] в одной [npc.hand], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" от удовольствия, пока [npc.she] [npc.verb(pull)] [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль [npc2.namePos] спины, [npc.name] [npc.verb(reach)] вперед и [npc.verb(take)] крепко сжимая [npc2.her] [npc2.hair(true)], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
			}
		}
		
	};
	
	public static final SexAction PULL_EARS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тянуть за уши";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.ears+] и оттянуть [npc2.her] голову назад.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
			
			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] нежно [npc.verb(take)] берет [npc2.namePos] [npc2.ears+] за каждую [npc.hand], и, продолжая ритмично вводить и выводить [npc.her] [npc.cock] в [npc2.her] [npc2."+tag+"+], [npc.she] медленно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Мягко обхватив [npc2.namePos] [npc2.ears+] обеими [npc.hands], [npc.name] одновременно [npc.verb(pull)] [npc2.her] голову назад, глубоко вжимая [npc.her] [npc.cock+] в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" тихо хихикая":" ухмыляясь")+" [npc.herself], пока [npc2.name] [npc2.verb(let)] извлекает [npc2.moan+].",
								"Продолжая медленно вводить и выводить [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед двумя [npc.hands] и [npc.verb(take)] мягко сжимая [npc2.her] [npc2.ears+], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] осторожно [npc.verb(take)] берет [npc2.namePos] [npc2.ears+] в каждую [npc.hand], прежде чем медленно оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Мягко обхватив [npc2.namePos] [npc2.ears+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" мягко [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" к [npc.herself], пока [npc.she] [npc.verb(pull)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль всей спины [npc2.namePos], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] нежным захватом [npc2.her] [npc2.ears+], а затем резко оттягивая [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] хватает [npc2.namePos] [npc2.ears+] и, продолжая с силой вбивать [npc.her] [npc.cock] туда-сюда в [npc2.her] [npc2."+tag+"+], [npc.she] резко [npc.verb(yank)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив [npc2.namePos] за [npc2.ears+] обеими [npc.hands], [npc.name] одновременно [npc.verb(yank)] [npc2.her] голову назад, с силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально хихикая":" глубоко рыча")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая грубо вбивать [npc.her] [npc.cock+] туда-сюда в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(grab)] [npc2.her] [npc2.ears+], после чего яростно дергает [npc2.her] за голову, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] хватает [npc2.namePos] [npc2.ears+] каждой своей [npc.hand], а затем резко дергает [npc2.her] за голову, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив [npc2.namePos] [npc2.ears+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально [npc.verb(giggle)]":" глубоко [npc.verb(grunt)]")
									+" когда [npc.she] яростно [npc.verb(yank)] голову [npc2.her] назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Грубые ласки и ощупывания [npc.her] вверх по всей длине [npc2.namePos] спины, [npc.name] [npc.verb(reach)] вперед двумя [npc.hands] и [npc.verb(grab)] [npc2.her] [npc2.ears+], после чего резким движением [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Протянув руки вперед, [npc.name] крепко [npc.verb(take)] хватает [npc2.namePos] [npc2.ears+] каждой своей [npc.hand] и, продолжая ритмично вгонять [npc.her] [npc.cock] в [npc2.her] [npc2."+tag+"+], [npc.she] уверенно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Крепко обхватив [npc2.namePos] [npc2.ears+] обеими [npc.hands], [npc.name] одновременно [npc.verb(pull)] отклоняет [npc2.her] голову назад, глубоко вгоняя [npc.her] в [npc.cock+] [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо хихикая":" кряхтя")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая энергично вгонять и вынимать [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед двумя [npc.hands] и [npc.verb(take)] крепко хватает [npc2.her] [npc2.ears+], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Протянув руки вперед, [npc.name] крепко [npc.verb(take)] хватает [npc2.namePos] [npc2.ears+] каждой своей [npc.hand], после чего уверенно тянет [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Крепко обхватив [npc2.namePos] [npc2.ears+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" от удовольствия, пока [npc.she] [npc.verb(pull)] [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль [npc2.namePos] спины, [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] крепко сжимая [npc2.her] [npc2.ears+], а затем оттягивая [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
			}
		}
	};
	
	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.horns+] и оттянуть [npc2.her] голову назад.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));

			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] нежно [npc.verb(take)] берет [npc2.namePos] [npc2.horns+] за каждую [npc.hand], и, продолжая ритмично вгонять [npc.her] [npc.cock] в [npc2.her] [npc2."+tag+"+], [npc.she] медленно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Мягко обхватив [npc2.namePos] [npc2.horns+] обеими [npc.hands], [npc.name] одновременно [npc.verb(pull)] [npc2.her] голову назад, глубоко вгоняя [npc.her] [npc.cock+] в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" тихо хихикая":" ухмыляясь")+" [npc.herself], пока [npc2.name] [npc2.verb(let)] извлекает [npc2.moan+].",
								"Продолжая медленно вводить и выводить [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] мягко сжимая [npc2.her] [npc2.horns+], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] осторожно [npc.verb(take)] берет [npc2.namePos] [npc2.horns+] в каждую [npc.hand], прежде чем медленно оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Мягко обхватив [npc2.namePos] [npc2.horns+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" мягко [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" к [npc.herself], пока [npc.she] [npc.verb(pull)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль всей спины [npc2.namePos], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] нежным захватом [npc2.her] [npc2.horns+], а затем резко оттягивая [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] хватает [npc2.namePos] за [npc2.horns+] и, продолжая с силой вбивать [npc.her] [npc.cock] туда-сюда в [npc2.her] [npc2."+tag+"+], [npc.she] резко [npc.verb(yank)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив [npc2.namePos] за [npc2.horns+] обеими [npc.hands], [npc.name] одновременно [npc.verb(yank)] [npc2.her] голову назад, с силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально хихикая":" глубоко рыча")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая грубо вбивать [npc.her] [npc.cock+] туда-сюда в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(grab)] [npc2.her] [npc2.horns+], прежде чем яростно дернуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] хватает [npc2.namePos] за [npc2.horns+] каждой своей [npc.hand], а затем резко дергает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив [npc2.namePos] [npc2.horns+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально [npc.verb(giggle)]":" глубоко [npc.verb(grunt)]")
									+" когда [npc.she] яростно [npc.verb(yank)] голову [npc2.her] назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Грубые ласки и ощупывания [npc.her] вверх по всей длине [npc2.namePos] спины, [npc.name] [npc.verb(reach)] вперед двумя [npc.hands] и [npc.verb(grab)] [npc2.her] [npc2.horns+], после чего резким движением [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Протянув руки вперед, [npc.name] крепко [npc.verb(take)] хватает [npc2.namePos] [npc2.horns+] каждой [npc.hand] и, продолжая ритмично [npc.her] [npc.cock] туда-сюда в [npc2.her] [npc2."+tag+"+], [npc.she] уверенно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Крепко обхватив [npc2.namePos] [npc2.horns+] обеими [npc.hands], [npc.name] одновременно [npc.verb(pull)] [npc2.her] голову назад, с силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо хихикая":" кряхтя")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая энергично вгонять и вынимать [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед двумя [npc.hands] и [npc.verb(take)] крепко хватает [npc2.her] [npc2.horns+], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Протянув руки вперед, [npc.name] крепко [npc.verb(take)] хватает [npc2.namePos] [npc2.horns+] каждой [npc.hand], а затем уверенно тянет [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Крепко обхватив [npc2.namePos] [npc2.horns+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" от удовольствия, пока [npc.she] [npc.verb(pull)] [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль всей спины [npc2.namePos], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] крепко сжимая [npc2.her] [npc2.horns+], а затем оттягивая [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
			}
		}
		
	};
	

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.antennae+] и оттянуть [npc2.her] голову назад.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean suitableSlot = false;

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			suitableSlot = suitablePositionsForPullingAndGrabbing(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));

			if(!suitableSlot) {
				return false;
			}
			
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getDescription() {
			
			boolean vaginalSex = false;
			try {
				vaginalSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
			}
			boolean analSex = false;
			try {
				analSex = Main.sex.getOngoingActionsMap(Main.sex.getCharacterPerformingAction()).get(SexAreaPenetration.PENIS).get(Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.ANUS);
			} catch(Exception ex) {
			} 

			String tag = "pussy";
			if(analSex) {
				tag = "asshole";
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] нежно [npc.verb(take)] берет [npc2.namePos] [npc2.antennae+] за каждую [npc.hand], и, продолжая ритмично вводить и выводить [npc.her] [npc.cock] в [npc2.her] [npc2."+tag+"+], [npc.she] медленно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Мягко обхватив [npc2.namePos] [npc2.antennae+] обеими [npc.hands], [npc.name] одновременно [npc.verb(pull)] [npc2.her] голову назад, глубоко вбивая [npc.her] [npc.cock+] в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" тихо хихикая":" ухмыляясь")+" [npc.herself], пока [npc2.name] [npc2.verb(let)] извлекает [npc2.moan+].",
								"Продолжая медленно вводить и выводить [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] мягко сжимая [npc2.her] [npc2.antennae+], прежде чем оттянуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] осторожно [npc.verb(take)] берет [npc2.namePos] [npc2.antennae+] в каждую [npc.hand], а затем медленно оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Мягко обхватив [npc2.namePos] [npc2.antennae+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" мягко [npc.verb(giggle)]":" [npc.verb(smirk)]")
									+" к [npc.herself], пока [npc.she] [npc.verb(pull)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль всей спины [npc2.namePos], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] нежно сжимая [npc2.her] [npc2.antennae+], а затем резко оттягивая [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				case DOM_ROUGH:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] хватает [npc2.namePos] за [npc2.antennae+] и, продолжая с силой вбивать [npc.her] [npc.cock] туда-сюда в [npc2.her] [npc2."+tag+"+], [npc.she] резко [npc.verb(yank)] оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив [npc2.namePos] за [npc2.antennae+] обеими [npc.hands], [npc.name] одновременно [npc.verb(yank)] [npc2.her] голову назад, с силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально хихикая":" глубоко рыча")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая грубо вбивать [npc.her] [npc.cock+] туда-сюда в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(grab)] [npc2.her] [npc2.antennae+], прежде чем яростно дернуть [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Потянувшись вперед, [npc.name] грубо [npc.verb(grab)] хватает [npc2.namePos] за [npc2.antennae+] каждой своей [npc.hand], а затем резко дергает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Схватив [npc2.namePos] [npc2.antennae+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" маниакально [npc.verb(giggle)]":" глубоко [npc.verb(grunt)]")
									+" когда [npc.she] яростно [npc.verb(yank)] голову [npc2.her] назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Грубые ласки и ощупывания [npc.her] вверх по всей длине [npc2.namePos] спины, [npc.name] [npc.verb(reach)] вперед двумя [npc.hands] и [npc.verb(grab)] [npc2.her] [npc2.antennae+], после чего резким движением [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
				default: // For dom normal, sub normal, and sub eager:
					if(vaginalSex || analSex) {
						return UtilText.returnStringAtRandom(
								"Протянув руки вперед, [npc.name] крепко [npc.verb(take)] хватает [npc2.namePos] [npc2.antennae+] каждой своей [npc.hand] и, продолжая ритмично [npc.her] [npc.cock] туда-сюда в [npc2.her] [npc2."+tag+"+], [npc.she] уверенно [npc.verb(pull)] назад, заставляя [npc2.name] закинуть [npc2.her] голову и издать [npc2.a_moan+].",
								"Крепко обхватив [npc2.namePos] [npc2.antennae+] обеими [npc.hands], [npc.name] одновременно [npc.verb(pull)] [npc2.her] голову назад, мощно вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2."+tag+"],"
										+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо хихикая":" кряхтя")+" в экстазе, пока [npc2.name] [npc2.verb(let)] [npc2.moan+].",
								"Продолжая энергично вгонять и вынимать [npc.her] [npc.cock+] в [npc2.namePos] [npc2."+tag+"+], [npc.name] [npc.verb(reach)] вперед двумя [npc.hands] и [npc.verb(take)] крепко сжимает [npc2.her] [npc2.antennae+], после чего оттягивает [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
						
					} else {
						return UtilText.returnStringAtRandom(
								"Протянув руки вперед, [npc.name] крепко [npc.verb(take)] хватает [npc2.namePos] [npc2.antennae+] каждой своей [npc.hand], после чего уверенно тянет [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].",
								"Крепко обхватив [npc2.namePos] [npc2.antennae+] обеими [npc.hands], [npc.name] "+(Main.sex.getCharacterPerformingAction().isFeminine()?" счастливо [npc.verb(giggle)]":" [npc.verb(chuckle)]")
									+" от удовольствия, пока [npc.she] [npc.verb(pull)] [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.moan+].",
								"Проводя [npc.her] [npc.fingers+] вдоль всей спины [npc2.namePos], [npc.name] [npc.verb(reach)] вперед с двумя [npc.hands] и [npc.verb(take)] крепко сжимая [npc2.her] [npc2.antennae+], а затем оттягивая [npc2.her] голову назад, заставляя [npc2.herHim] издать [npc2.a_moan+].");
					}
			}
		}
		
	};

	public static final SexAction LOOK_BACK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this));
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& ((slot.hasTag(SexSlotTag.ALL_FOURS) && targetedSlot.hasTag(SexSlotTag.BEHIND_ALL_FOURS))
							|| (slot.hasTag(SexSlotTag.LYING_DOWN_ON_FRONT) && targetedSlot.hasTag(SexSlotTag.MISSIONARY)))
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Соблазнительный взгляд";
		}
		@Override
		public String getActionDescription() {
			return "Поверни голову назад и брось на [npc2.name] соблазнительный взгляд.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().getSubspecies()==Subspecies.getSubspeciesFromId("innoxia_raptor_subspecies_owl")
					&& Main.sex.getCharacterPerformingAction().getFaceType().getRace()==Race.getRaceFromId("innoxia_raptor")) {
				return UtilText.returnStringAtRandom(
						"Довольно жутковатым и тревожным движением [npc.name] [npc.verb(use)] подвижность совиной шеи [npc.her], чтобы повернуть голову [npc.her] на целых сто восемьдесят градусов, после чего взглянуть на [npc2.name] и принять [npc.her] самый соблазнительный вид, пока [npc.she] [npc.verb(entice)] [npc2.herHim], чтобы использовать [npc.herHim].",
						"Полностью вывернув совиную шею [npc.her], [npc.name] [npc.verb(end)] вверх, глядя прямо на [npc2.name], пока [npc2.she] [npc2.verb(tower)] над [npc.her] [npc.ass+]. Надеясь, что этот пугающий и слегка жуткий маневр не оттолкнет партнера, [npc.name] [npc.verb(put)] на соблазнительный взгляд, [npc.moaning] в восторге от того, как [npc.she] [npc.verb(entice)] [npc2.name], используя тело [npc.her].",
						"Используя широкую амплитуду движений, которую [npc.her] совиная шея дарует [npc.herHim], [npc.name] [npc.verb(turn)] [npc.her] голову полностью вокруг, так что [npc.sheIs] смотрит на [npc2.name], не двигая [npc.her] телом, после чего стараясь [npc.her] изо всех сил выглядеть как можно более соблазнительно.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"Поворачивая [npc.her] голову назад, [npc.name] [npc.verb(look)] на [npc2.name] и [npc.verb(bite)] [npc.her] [npc.lip], она принимает [npc.her] самый соблазнительный вид, пока [npc.she] [npc.verb(entice)] [npc2.herHim], чтобы использовать [npc.herHim].",
						"Оглядываясь на [npc2.name], пока [npc2.she] [npc2.verb(tower)] через [npc.her] [npc.ass+], [npc.name] [npc.verb(put)] соблазнительный взгляд, [npc.moaning] в восторге от того, как [npc.she] [npc.verb(entice)] [npc2.herHim], используя тело [npc.her].",
						"[npc.Name] [npc.verb(turn)] [npc.her] головой и [npc.verb(bite)] [npc.her] [npc.lip] в [npc2.name], стараясь [npc.her] изо всех сил выглядеть как можно соблазнительнее.",
						"Оглядываясь назад, [npc.name] [npc.verb(put)] соблазнительный взгляд для [npc2.name], чувствуя крайнее удовольствие от [npc.herself], пока [npc.she] [npc.verb(see)] [npc2.herHim], жадно смотря на [npc.herHim] в ответ.");
			}
		}
	};
	
	// Orgasms:
	
	public static final SexAction DOGGY_DOMINANT_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this));
			
			return Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getDominantParticipants(false).size()==1
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& (slot.hasTag(SexSlotTag.BEHIND_ALL_FOURS)
							|| (slot.hasTag(SexSlotTag.MISSIONARY) && targetedSlot.hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<=0)
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
//					&& (Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Math.random()<0.75f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Задница в рот";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
				return "Жестко трахай [npc2.name] в пол, прежде чем наполнить свой презерватив своей спермой. Затем сними его и используй рот [npc2.her], чтобы очиститься.";
			}
			return "Жестко трахай [npc2.name] в пол, прежде чем наполнить [npc2.her] [npc2.ass] своей спермой. Затем используй рот [npc2.her], чтобы очиститься.";
		}
		@Override
		public String getDescription() {
			boolean immobile = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COMMAND);
			boolean immobileSleep = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.SLEEP);

			StringBuilder sb = new StringBuilder();
			
			sb.append("Пока [npc.name] [npc.verb(feel)] [npc2.namePos] [npc2.asshole+] сжимается вокруг [npc.her] [npc.cock+], [npc.she] [npc.verb(decide)], чтобы показать [npc2.herHim], как"
						+ (Main.sex.getCharacterPerformingAction().getRace()==Race.WOLF_MORPH || Main.sex.getCharacterPerformingAction().getRace()==Race.DOG_MORPH
							?" альфа обращается со своей покорной маленькой бетой. "
							:" настоящий доминант обращается со своей покорной сучкой. "));
			
			if(immobileSleep) {
				sb.append("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.ass+], дьявольски ухмыляясь, пока [npc2.she] [npc2.verb(wake)] вверх и [npc2.verb(let)] наружу [npc2.a_moan+].");
			} else if(immobile) {
				sb.append("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.ass+], дьявольски ухмыляясь, пока [npc2.she] [npc2.verb(continue)], ведя себя как неодушевленная секс-кукла.");
			} else {
				sb.append("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.ass+], дьявольски ухмыляясь, пока [npc2.she] [npc2.verb(let)] наружу [npc2.a_moan+].");
			}
			
			sb.append("<br/><br/>");

			boolean isDoggyStyle = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_ALL_FOURS);
			if(immobile) {
				sb.append("Потянувшись вниз, [npc.she] затем [npc.verb(grab)] [npc2.namePos] плечи, прежде чем придавить [npc.her] весом на [npc2.her] спину, пока [npc.she] грубо [npc.verb(mount)] [npc2.herHim]. Теперь, когда [npc.namePos] вес на [npc2.herHim], [npc2.name]"
						+ (isDoggyStyle
								?" [npc2.verb(collapse)] вниз на [npc2.her] переднюю часть"
								:" была раздавлена под [npc.herHim]")
						+ " и [npc2.verb(remain)] молчалива и неподвижна, оказавшись в столь покорном положении, как [npc2.sheIs]. Наклонившись, при этом [npc.her] пульсирующий [npc.cock] все еще торчит в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(growl)] угрожающе в [npc2.her] [npc2.ear], [npc.speech(Ты тупая кукла! Всё, на что ты годна — это быть моей секс-игрушкой, чехлом для члена!)]");
			} else {
				sb.append("Потянувшись вниз, [npc.she] затем [npc.verb(grab)] [npc2.namePos] плечи, прежде чем придавить [npc.her] весом на [npc2.her] спину, пока [npc.she] грубо [npc.verb(mount)] [npc2.herHim]. Теперь, когда [npc.namePos] вес на [npc2.herHim], [npc2.name]"
						+ (isDoggyStyle
								?" [npc2.verb(collapse)] на [npc2.her] животом, с [npc2.a_moan+]."
								:" прижат под [npc.herHim], пока [npc2.verb(let)] выплескивает [npc2.a_moan+].")
						+ " Наклонившись, с [npc.her], чья пульсирующая [npc.cock] все еще торчит в [npc2.namePos] [npc2.asshole+], [npc.name] угрожающе [npc.verb(growl)] в [npc2.her] [npc2.ear], [npc.speech(Ах ты мелкая сучка! Всё, на что ты годна — это быть моей шлюхоподобной залупой!)]");
			}
			
			sb.append("<br/><br/>");

			if(immobile) {
				sb.append("Даже этих унизительных слов недостаточно, чтобы сбить [npc2.namePos] темп, и [npc2.she] остается совершенно неподвижной, пока волна оргазма от [npc.namePos] накрывает [npc.herHim].");
			} else {
				sb.append("Услышав эти унизительные слова, [npc2.Name] [npc2.verb(let)] еще одну порцию [npc2.moan+], чего достаточно, чтобы отправить [npc.name] в настоящий экстаз.");
			}
			sb.append(" Пока [npc.she] [npc.verb(grind)] [npc2.namePos] [npc2.face+] в пол, [npc.name] [npc.verb(reach)] [npc.her] кульминацию, а [npc.her] [npc.balls+] напрягаются");
			
			switch (Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					sb.append(", [npc.she] [npc.verb(realise)], что [npc.she] [npc.is] не может выдавить даже каплю спермы, что немного смягчает эффект от доминирующего напора [npc.her].");
					break;
				case ONE_TRICKLE:
					sb.append(", выплескивается тонкая струйка [npc.cum+]");
					break;
				case TWO_SMALL_AMOUNT:
					sb.append(", выплескивается немного [npc.cum+]");
					break;
				case THREE_AVERAGE:
					sb.append(", [npc.cum+] выпускает струю [npc.her]");
					break;
				case FOUR_LARGE:
					sb.append(", [npc.cum+] выпускает струю [npc.her]");
					break;
				case FIVE_HUGE:
					sb.append(", наружу вырывается [npc.her] огромная порция [npc.cum+]");
					break;
				case SIX_EXTREME:
					sb.append(", наружу вырывается [npc.her] массивная порция [npc.cum+]");
					break;
				case SEVEN_MONSTROUS:
					sb.append(", наружу с брызгами вырывается [npc.her] колоссальная порция [npc.cum+]");
					break;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					sb.append(" в презерватив, который [npc.sheIs] носит.");
				} else {
					sb.append(" глубоко в [npc2.namePos] [npc2.asshole+].");
				}
			}

			if (Main.sex.getCharacterPerformingAction().getVaginaType() != VaginaType.NONE) {
				sb.append("<br/><br/>");
				sb.append("Пока [npc.name] [npc.verb(slide)] [npc.her] все еще пульсирующий член из [npc2.namePos] изрядно поработанной [npc2.ass], [npc.she] [npc.verb(feel)] второй оргазм, зародившийся глубоко в [npc.her] паху. Схватив [npc2.namePos] за бедра, чтобы удержать [npc.herself], [npc.name] [npc.verb(clench)] [npc.her] бедра вместе, пока [npc.her] [npc.pussy+] содрогается и дрожит. Ослепляющий оргазм проносится сквозь [npc.herHim], и [npc.name] [npc.moanVerb] от восторга, когда [npc.her] женское лоно присоединяется к этому веселью.");
				
			} else {
				sb.append("<br/><br/>");
				sb.append("Пока [npc.name] [npc.verb(slide)] [npc.her] все еще пульсирующий член из [npc2.namePos] изрядно поработанной [npc2.ass], [npc.she] [npc.verb(grin)] в ту мешанину, что [npc.sheHas] наделал из [npc2.herHim].");
			}

			sb.append("<br/><br/>");
			
			sb.append("Тяжело дыша, [npc.name] внезапно [npc.verb(remember)] то, что [npc.she] планировал, и [npc.verb(shuffle)] туда, где лицо [npc2.namePos] все еще прижато к полу."
					+ (Main.sex.getCharacterTargetedForSexAction(this).hasHair()
							?" Потянувшись вниз, [npc.she] грубо [npc.verb(grab)] горсть [npc2.namePos] [npc2.hair+],"
							:" Потянувшись вниз, [npc.she] грубо [npc.verb(grab)] шею [npc2.namePos],")
					+(immobile?" и ":" и прежде чем [npc2.she] [npc2.has] успеет среагировать, [npc.she]")
					+" [npc.verb(shove)] [npc2.her] [npc2.face+] вниз на [npc.her] [npc.cock+].");
			
			if(immobile) {
				sb.append(" [npc2.Name] [npc2.do] даже не реагирует, пока "+(Main.sex.getCharacterPerformingAction().isPlayer()?"вы":"[npc2.her] доминантный партнер")+" [npc.verb(give)] [npc2.herHim] вкушает [npc2.her] собственной [npc2.ass], и, крепко удерживая [npc2.herHim] на месте, [npc.she] [npc.moansVerb+], пока [npc.she] [npc.verb(use)] [npc2.namePos] рот, чтобы слизать [npc.herself].<br/><br/>Спустя минуту использования [npc2.name] таким образом, [npc.name] наконец [npc.verb(release)] [npc2.herHim], прежде чем ухмыльнуться на [npc2.herHim], гадая, что же сделать с [npc2.herHim] дальше...");
			} else {
				sb.append(" [npc2.Name] [npc2.moansVerb] и [npc2.verb(squirm)], пока "+(Main.sex.getCharacterPerformingAction().isPlayer()?"вы":"[npc2.her] доминантный партнер")+" [npc.verb(give)] [npc2.herHim] вкушает вкус собственной [npc2.her] [npc2.ass], и, крепко удерживая [npc2.herHim] в нужном положении, [npc.she] [npc.moansVerb+], пока [npc2.her] неистово [npc2.tongue] счищает с [npc.herHim].<br/><br/>После минуты использования [npc2.name] подобным образом, [npc.name] наконец [npc.verb(release)] [npc2.herHim], и с глубоким вздохом [npc2.she] [npc2.verb(collapse)] на пол, совершенно измотанная от такого доминирующего обращения.");
			}

			return sb.toString();
		}
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
			} else {
				return null;
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
		
	};
	
	public static final SexAction DOGGY_DOMINANT_ORGASM_PUSSY = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isBaseRequirementsMet() {
			SexSlot slot = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction());
			SexSlot targetedSlot = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this));
			
			return Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getDominantParticipants(false).size()==1
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& (slot.hasTag(SexSlotTag.BEHIND_ALL_FOURS)
							|| (slot.hasTag(SexSlotTag.MISSIONARY) && targetedSlot.hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<=0)
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
//					&& (Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SADIST).isNegative());
		}
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Math.random()<0.75f) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Из киски в рот";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
				return "Жестко трахай [npc2.name] в пол, прежде чем наполнить свой презерватив своей спермой. Затем сними его и используй рот [npc2.her], чтобы очиститься.";
			}
			return "Грубо трахни [npc2.name] в пол, прежде чем заполнить [npc2.her] [npc2.pussy] своей спермой. Затем используй [npc2.her] рот, чтобы очиститься.";
		}
		@Override
		public String getDescription() {
			boolean immobile = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COMMAND);
			boolean immobileSleep = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.SLEEP);

			StringBuilder sb = new StringBuilder();
			
			sb.append("Пока [npc.name] [npc.verb(feel)] [npc2.namePos] [npc2.pussy+] сжимается вокруг [npc.her] [npc.cock+], [npc.she] [npc.verb(decide)], чтобы показать [npc2.herHim], как"
						+ (Main.sex.getCharacterPerformingAction().getRace()==Race.WOLF_MORPH || Main.sex.getCharacterPerformingAction().getRace()==Race.DOG_MORPH
							?" альфа обращается со своей покорной маленькой бетой. "
							:" настоящий доминант обращается со своей покорной сучкой. "));
			
			if(immobileSleep) {
				sb.append("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], дьявольски ухмыляясь, пока [npc2.she] [npc2.verb(wake)] вверх и [npc2.verb(let)] наружу [npc2.a_moan+].");
			} else if(immobile) {
				sb.append("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], дьявольски ухмыляясь, пока [npc2.she] [npc2.verb(continue)], словно неодушевленная секс-кукла.");
			} else {
				sb.append("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+], дьявольски ухмыляясь, пока [npc2.she] [npc2.verb(let)] наружу [npc2.a_moan+].");
			}
			
			sb.append("<br/><br/>");

			boolean isDoggyStyle = Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_ALL_FOURS);
			if(immobile) {
				sb.append("Потянувшись вниз, [npc.she] затем [npc.verb(grab)] [npc2.namePos] плечи, прежде чем придавить [npc.her] весом на [npc2.her] спину, пока [npc.she] грубо [npc.verb(mount)] [npc2.herHim]. Теперь, когда [npc.namePos] вес на [npc2.herHim], [npc2.name]"
						+ (isDoggyStyle
								?" [npc2.verb(collapse)] вниз на [npc2.her] переднюю часть"
								:" была раздавлена под [npc.herHim]")
						+" и [npc2.verb(remain)] тихо и неподвижно, пока [npc2.sheIs] поместили в такую покорную позу. Наклонившись, при этом [npc.her] пульсирующий [npc.cock] все еще торчит в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(growl)] угрожающе в [npc2.her] [npc2.ear], [npc.speech(Тупая кукла! Всё, на что ты годна — это быть моей секс-игрушкой, разъёбом для члена!)]");
			} else {
				sb.append("Потянувшись вниз, [npc.she] затем [npc.verb(grab)] [npc2.namePos] плечи, прежде чем придавить [npc.her] весом на [npc2.her] спину, пока [npc.she] грубо [npc.verb(mount)] [npc2.herHim]. Теперь, когда [npc.namePos] вес на [npc2.herHim], [npc2.name] "
						+ (isDoggyStyle
								?" [npc2.verb(collapse)] на [npc2.her] животом, с [npc2.a_moan+]."
								:" прижат под [npc.herHim], пока [npc2.verb(let)] выплескивает [npc2.a_moan+].")
						+ " Наклонившись, при этом [npc.her] пульсирующий [npc.cock] все еще торчит в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(growl)] угрожающе в [npc2.her] [npc2.ear], [npc.speech(Мелкая сучка! Всё, на что ты годна — это быть моей шлюхой-разъёбом для члена!)]");
			}
			
			sb.append("<br/><br/>");

			if(immobile) {
				sb.append("Даже этих унизительных слов недостаточно, чтобы сбить [npc2.namePos] темп, и [npc2.she] остается совершенно неподвижной, пока волна оргазма от [npc.namePos] накрывает [npc.herHim].");
			} else {
				sb.append("Услышав эти унизительные слова, [npc2.Name] [npc2.verb(let)] еще одну порцию [npc2.moan+], чего достаточно, чтобы отправить [npc.name] в настоящий экстаз.");
			}
			sb.append(" Пока [npc.she] [npc.verb(grind)] [npc2.namePos] [npc2.face+] в пол, [npc.name] [npc.verb(reach)] [npc.her] кульминацию, а [npc.her] [npc.balls+] напрягаются");
			
			switch (Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					sb.append(", [npc.she] [npc.verb(realise)], что [npc.she] [npc.is] не может выдавить даже каплю спермы, что немного смягчает эффект от доминирующего напора [npc.her].");
					break;
				case ONE_TRICKLE:
					sb.append(", выплескивается тонкая струйка [npc.cum+]");
					break;
				case TWO_SMALL_AMOUNT:
					sb.append(", выплескивается немного [npc.cum+]");
					break;
				case THREE_AVERAGE:
					sb.append(", [npc.cum+] выпускает струю [npc.her]");
					break;
				case FOUR_LARGE:
					sb.append(", [npc.cum+] выпускает струю [npc.her]");
					break;
				case FIVE_HUGE:
					sb.append(", наружу вырывается [npc.her] огромная порция [npc.cum+]");
					break;
				case SIX_EXTREME:
					sb.append(", наружу вырывается [npc.her] массивная порция [npc.cum+]");
					break;
				case SEVEN_MONSTROUS:
					sb.append(", наружу с брызгами вырывается [npc.her] колоссальная порция [npc.cum+]");
					break;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE) {
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					sb.append(" в презерватив, который [npc.sheIs] носит.");
				} else {
					sb.append(" глубоко в [npc2.namePos] [npc2.pussy+].");
				}
			}

			if (Main.sex.getCharacterPerformingAction().getVaginaType() != VaginaType.NONE) {
				sb.append("<br/><br/>");
				sb.append("Пока [npc.name] [npc.verb(slide)] [npc.her] все еще пульсирующий член из [npc2.namePos] заезженной [npc2.pussy], [npc.she] [npc.verb(feel)] второй оргазм, нарастающий глубоко в [npc.her] паху. Сжимая [npc2.namePos] бедра, чтобы удержать [npc.herself], [npc.name] [npc.verb(clench)] [npc.her] бедра вместе, пока [npc.her] [npc.pussy+] содрогается и дрожит. Раскалывающий разум оргазм накрывает [npc.herHim], и [npc.name] [npc.moanVerb] от восторга, когда [npc.her] женское лоно присоединяется к этому веселью.");
				
			} else {
				sb.append("<br/><br/>");
				sb.append("Пока [npc.name] [npc.verb(slide)] [npc.her] все еще пульсирующий член из [npc2.namePos] заезженной [npc2.pussy], [npc.she] [npc.verb(grin)] на ту мешанину, что [npc.sheHas] сделала из [npc2.herHim].");
			}

			sb.append("<br/><br/>");
			
			sb.append("Тяжело дыша, [npc.name] внезапно [npc.verb(remember)] то, что [npc.she] планировал, и [npc.verb(shuffle)] туда, где лицо [npc2.namePos] все еще прижато к полу."
					+ (Main.sex.getCharacterTargetedForSexAction(this).hasHair()
							?" Потянувшись вниз, [npc.she] грубо [npc.verb(grab)] горсть [npc2.namePos] [npc2.hair+],"
							:" Потянувшись вниз, [npc.she] грубо [npc.verb(grab)] шею [npc2.namePos],")
					+(immobile?" и ":" и прежде чем [npc2.she] [npc2.has] успеет среагировать, [npc.she]")
					+" [npc.verb(shove)] [npc2.her] [npc2.face+] вниз на [npc.her] [npc.cock+].");
			
			if(immobile) {
				sb.append(" [npc2.Name] [npc2.do] даже не реагирует, пока "+(Main.sex.getCharacterPerformingAction().isPlayer()?"вы":"[npc2.her] доминантный партнер")+" [npc.verb(give)] [npc2.herHim] вкус собственной [npc2.her] [npc2.pussy], и, крепко удерживая [npc2.herHim] на месте, [npc.she] [npc.moansVerb+], пока [npc.she] [npc.verb(use)] [npc2.namePos] рот, чтобы слизать [npc.herself].<br/><br/>После минуты использования [npc2.name] таким образом, [npc.name] наконец [npc.verb(release)] [npc2.herHim], прежде чем ухмыльнуться на [npc2.herHim] и задуматься, что сделать с [npc2.herHim] дальше...");
			} else {
				sb.append(" [npc2.Name] [npc2.moansVerb] и [npc2.verb(squirm)], пока "+(Main.sex.getCharacterPerformingAction().isPlayer()?"вы":"[npc2.her] доминантный партнер")+" [npc.verb(give)] [npc2.herHim] вкушает вкус собственной [npc2.her] [npc2.pussy], и, крепко удерживая [npc2.herHim] в нужном положении, [npc.she] [npc.moansVerb+], пока [npc2.her] неистово [npc2.tongue] счищает с [npc.herHim].<br/><br/>Спустя минуту использования [npc2.name] таким образом, [npc.name] наконец [npc.verb(release)] [npc2.herHim], и с глубоким вздохом [npc2.she] [npc2.verb(collapse)] на пол, совершенно измотанная от доминирующего обращения.");
			}
			
			return sb.toString();
		}
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
			} else {
				return null;
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
	};

}
