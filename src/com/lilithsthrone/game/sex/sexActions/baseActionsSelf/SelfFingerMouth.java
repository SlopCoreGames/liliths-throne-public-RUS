package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
public class SelfFingerMouth {
	
	public static final SexAction SELF_FINGER_MOUTH_LUBRICATION = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)
							&& Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())
							&& (Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))!=null
								&& Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaPenetration.FINGER));
		}
		
		@Override
		public String getActionTitle() {
			return "Смазать пальцы";
		}

		@Override
		public String getActionDescription() {
			return "Смазать свои пальцы слюной.";
		}

		@Override
		public String getDescription() {
			return "Подняв [npc.her] [npc.hand] к [npc.her] рту, [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] мимо [npc.her] [npc.lips+], а затем похотливо посасывая их некоторое время, чтобы хорошенько смазать их [npc.her] слюной.";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
	};
	
	public static final SexAction SELF_FINGER_MOUTH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getActionTitle() {
			return "Сосать пальцы (самой себе)";
		}

		@Override
		public String getActionDescription() {
			return "Засунь свои [npc.fingers] в [npc.mouth] и начни их сосать.";
		}

		@Override
		public String getDescription() {
			return "Подняв [npc.her] [npc.hand] к [npc.her] [npc.mouth], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] мимо [npc.her] [npc.lips+], прежде чем начать похотливо посасывать [npc.her] вошедшие пальцы.";
		}
	};
	
	public static final SexAction SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return "Перестать сосать пальцы (самой себе)";
		}

		@Override
		public String getActionDescription() {
			return "Перестать сосать свои [npc.fingers].";
		}

		@Override
		public String getDescription() {
			return "С легким вздохом, [npc.name] [npc.verb(slide)] [npc.her] покрытые слюной [npc.fingers] из [npc.her] [npc.mouth].";
		}
	};
	
}
