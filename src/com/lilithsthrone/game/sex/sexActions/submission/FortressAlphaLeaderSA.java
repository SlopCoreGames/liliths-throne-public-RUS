package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public class FortressAlphaLeaderSA {
	
	public static boolean isBothTargetsUsed() {
		try {
			return Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0
					&& Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_TWO), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0;
		} catch(Exception ex) {
			return true;
		}
	}
	
	public static GameCharacter getBlowjobTarget() {
		return Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressAlphaLeader.class), SexAreaPenetration.PENIS).isEmpty()
				?null
				:Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressAlphaLeader.class), SexAreaPenetration.PENIS).get(0);
	}
	
	private static GameCharacter getOtherTarget() {
		try {
			if(getBlowjobTarget()==null) {
				if(Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))>0) {
					return Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_TWO);
				} else {
					return Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL);
				}
			}
			return Main.sex.getSexPositionSlot(getBlowjobTarget())==SexSlotStanding.PERFORMING_ORAL
					?Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_TWO)
					:Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL);
		} catch(Exception ex) {
			return null;
		}
	}
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Раунд второй";
		}

		@Override
		public String getActionDescription() {
			return "Скажи [npc2.name], что ты еще не закончил!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за затылок, [npc.name] грубо толкает [npc.her] бедрами вперед, забивая [npc.her] [npc.cock+] по самую рукоять в [npc2.her] горло. Издав глубокий стон, когда [npc.she] чувствует, как [npc2.namePos] губы упираются в основание [npc.her] члена, [npc.she] рычит: [npc.speechNoEffects(Я еще не закончил, сучка! Надеюсь, ты готова ко второму раунду!)]";
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING_SWITCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Второй раунд (смена)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Переключиться на трах в [npc.namePos] лицо!");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"Grabbing the sides of [npc2.namePos] head, [npc.name] pushes [npc2.herHim] back, sliding [npc.her] [npc.cock+] out from [npc2.her] mouth and leaving a thick trail of cummy saliva drooling from [npc2.her] [npc2.lips].<br/>Focusing [npc.her] attention on [npc3.name], [npc.name] pushes the cum-coated tip of [npc.her] [npc.cock+] up against [npc3.her] [npc3.lips+], before roughly slamming [npc.her] hips forwards and hilting it fully down [npc3.her] throat. Letting out a deep groan as [npc.she] feels [npc3.namePos] [npc3.lips] bump up against the base of [npc.her] cock, [npc.she] growls, [npc.speechNoEffects(It's your turn, bitch! Hope you're ready for my second load!)]");
		}
		
		@Override
		public void applyEffects(){
			GameCharacter otherTarget = getOtherTarget();
			
			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.MOUTH, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.MOUTH, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBlowjobTarget(),
					SexAreaOrifice.MOUTH);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.MOUTH,
					true);
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Раунд второй";
		}

		@Override
		public String getActionDescription() {
			return "Скажи [npc2.name], что ты еще не закончил!";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за затылок, [npc.name] прижимает покрытый спермой кончик [npc.her] [npc.cock+] к губам [npc2.namePos], а затем резко толкает [npc.her] бедрами вперед, до самого основания вонзая его в [npc2.her] горло. Издавая глубокий стон, когда [npc.she] чувствует, как [npc2.namePos] губы упираются в основание [npc.her] члена, [npc.she] рычит: [npc.speechNoEffects(Я еще не закончил, сучка! Надеюсь, ты готова ко второму раунду!)]";
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START_SWITCH = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Второй раунд (смена)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "Переключиться на трах в [npc.namePos] лицо!");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
							|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressAlphaLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"Focusing [npc.her] attention on [npc3.name], [npc.name] pushes the cum-coated tip of [npc.her] [npc.cock+] up against [npc3.her] [npc3.lips+], before roughly slamming [npc.her] hips forwards and hilting it fully down [npc3.her] throat. Letting out a deep groan as [npc.she] feels [npc3.namePos] [npc3.lips] bump up against the base of [npc.her] cock, [npc.she] growls, [npc.speechNoEffects(It's your turn, bitch! Hope you're ready for my second load!)]");
		}
		
		@Override
		public void applyEffects(){
			GameCharacter otherTarget = getOtherTarget();

			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.MOUTH, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.MOUTH, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBlowjobTarget(),
					SexAreaOrifice.MOUTH);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.MOUTH,
					true);
		}
	};


	
}
