package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
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
public class SelfTailMouth {
	public static final SexAction SELF_TAIL_MOUTH_LUBRICATION = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL)
							&& Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())
							&& (Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))!=null
								&& Main.sex.getForeplayPreference(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaPenetration.TAIL));
		}
		
		@Override
		public String getActionTitle() {
			return "Смазать хвост";
		}

		@Override
		public String getActionDescription() {
			return "Смазать свой хвост слюной.";
		}

		@Override
		public String getDescription() {
			return "Подняв [npc.her] [npc.tail+] к [npc.her] рту, [npc.name] [npc.verb(slide)] [npc.her] [npc.tail+] сквозь [npc.her] [npc.lips+], а затем похотливо посасывая его мгновение, чтобы хорошенько смазать [npc.her] слюной.";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
	};
	
	public static final SexAction PARTNER_SELF_TAIL_MOUTH_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Сосать хвост (самой)";
		}

		@Override
		public String getActionDescription() {
			return "Засунуть свой [pc.tail] в рот и начать сосать его.";
		}

		@Override
		public String getDescription() {
			return "Просовывая [npc.her] [npc.tail] к [npc.her] рту, [npc.name] [npc.verb(slide)] кончик за [npc.her] [npc.lips+], прежде чем начать похотливо его посасывать.";
		}
	};
	
	public static final SexAction SELF_FINGER_MOUTH_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Перестать сосать хвост (самой)";
		}

		@Override
		public String getActionDescription() {
			return "Перестать сосать свой [pc.tail].";
		}

		@Override
		public String getDescription() {
			return "С легким вздохом, [npc.name] [npc.verb(slide)] [npc.her] покрытый слюной [npc.tail] из [npc.her] рта.";
		}
	};
}
