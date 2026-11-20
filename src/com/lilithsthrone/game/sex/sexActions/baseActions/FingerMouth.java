package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
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

/**
 * @since 0.3.2
 * @version 0.3.4
 * @author Innoxia
 */
public class FingerMouth {

	// The world isn't ready for finger sucking just yet
	
//	public static final SexAction FINGER_MOUTH_PENETRATION = new SexAction(
//			SexActionType.START_ONGOING,
//			ArousalIncrease.ZERO_NONE,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ZERO_PURE,
//			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
//			SexParticipantType.NORMAL) {
//		@Override
//		public String getActionTitle() {
//			return "Finger sucking";
//		}
//
//		@Override
//		public String getActionDescription() {
//			return "Push your [npc.fingers] into [npc2.namePos] mouth.";
//		}
//
//		@Override
//		public String getDescription() {
//			return "Lifting [npc.her] [npc.hand] up to [npc2.namePos] mouth, [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] past [npc2.her] [npc2.lips+], forcing [npc2.herHim] to start lewdly sucking on [npc.her] intruding digits.";
//		}
//	};
//	
//	public static final SexAction FINGER_MOUTH_STOP_PENETRATION = new SexAction(
//			SexActionType.STOP_ONGOING,
//			ArousalIncrease.ONE_MINIMUM,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ZERO_PURE,
//			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
//			SexParticipantType.NORMAL) {
//		@Override
//		public String getActionTitle() {
//			return "Stop finger sucking";
//		}
//
//		@Override
//		public String getActionDescription() {
//			return "Pull your [npc.fingers] out of [npc2.namePos] mouth.";
//		}
//
//		@Override
//		public String getDescription() {
//			return "With a little sigh, [npc.name] [npc.verb(slide)] [npc.her] saliva-coated [npc.fingers] out of [npc2.namePos] mouth.";
//		}
//	};
	

	public static final SexAction PARTNER_ASSIST_BLOWJOB = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return Util.newArrayListOfValues(
					Main.sex.getCharacterPerformingAction(),
					Main.sex.getCharacterTargetedForSexAction(this),
					getBlowjobReceiver());
		}
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), getBlowjobReceiver(), SexAreaPenetration.PENIS);
		}
		
		private GameCharacter getBlowjobReceiver() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH);
			if(characters.isEmpty()) {
				return null;
			}
			return Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).get(0);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
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
			
			boolean available = getBlowjobReceiver()!=null
					&& getBlowjobReceiver()!=Main.sex.getCharacterPerformingAction()
					&& (mouthFinger || mouthFingerReversed)
					&& Main.sex.getFirstOngoingSexAreaPenetration(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH) == SexAreaPenetration.PENIS
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
			
//			System.out.println(Main.sex.getCharacterPerformingAction().getName()+" "+Main.sex.getCharacterTargetedForSexAction(this).getName()+" "+available);
			
			return available;
		}
		
		@Override
		public String getActionTitle() {
			return "Помогать в минете";
		}

		@Override
		public String getActionDescription() {
			return "Помогать усилиям [npc2.namePos], когда тот делает [npc3.name] минет.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.DOM_ROUGH) {
				return UtilText.returnStringAtRandom(
								(Main.sex.getCharacterTargetedForSexAction(this).hasHair() && Main.sex.getCharacterTargetedForSexAction(this).getHairRawLengthValue()>HairLength.THREE_SHOULDER_LENGTH.getMaximumValue()
									?"Протянув руку и грубо схватив горсть [npc2.namePos] [npc2.hair+], [npc.name] [npc.verb(force)] [npc2.her] голову вниз, к паху [npc3.namePos], приказывая [npc2.herHim] подавиться [npc3.her] [npc3.cock+]."
									:""),
								"Грубо обхватив голову [npc2.namePos] по бокам, [npc.name] неистово [npc.verb(slam)] [npc2.namePos] голову вверх и вниз, заставляя [npc2.herHim] [npc2.verb(continue)] сосать [npc3.namePos] [npc3.cock+].",
								"Выплевывая серию похотливых, унизительных замечаний прямо в [npc2.her] [npc2.ear], [npc.name] [npc.verb(slam)] [npc2.namePos] голову в пах [npc3.namePos], тем самым заставляя [npc2.herHim] заглотить [npc3.her] [npc3.cock+] целиком в [npc2.her] рот.");
				
			} else {
				return UtilText.returnStringAtRandom(
								(Main.sex.getCharacterTargetedForSexAction(this).hasHair() && Main.sex.getCharacterTargetedForSexAction(this).getHairRawLengthValue()>HairLength.THREE_SHOULDER_LENGTH.getMaximumValue()
									?"Протянув руку вверх и проводя [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.hair+], [npc.name] [npc.verb(gather)] её и [npc.verb(hold)] "+(Main.sex.getCharacterTargetedForSexAction(this).getHairType().isDefaultPlural(Main.sex.getCharacterTargetedForSexAction(this))?"их":"это")
											+" с дороги, пока [npc2.name] [npc2.verb(continue)] делать [npc3.name] минет."
									:""),
								"Обхватив голову [npc2.namePos] по бокам, [npc.name] [npc.verb(help)], чтобы поднимать и толкать [npc2.her] голову вверх и вниз, помогая [npc2.herHim] продолжать сосать [npc3.namePos] [npc3.cock+].",
								"Шепча [npc2.herHim] грязные слова поддержки, [npc.name] [npc.verb(help)], толкая голову [npc2.namePos] в пах [npc3.namePos], чтобы [npc2.she] [npc2.verb(take)] [npc3.her] [npc3.cock+] глубоко в [npc2.her] рот.");
			}
		}
	};
	
	//TODO assist cunnilingus
	
}
