package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.4.2
 * @author Innoxia
 */
public class KneelingOral {
	
	public static final SexAction ORGASM_THIGH_SQUEEZE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)
					&& Main.sex.getCharacterPerformingAction().hasLegs()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			return super.getPriority();
		}
		
		@Override
		public String getActionTitle() {
			return "Сжимание бедер";
		}

		@Override
		public String getActionDescription() {
			return "Сжать бедра вокруг головы [npc2.namePos] и рухнуть на лицо [npc2.her] в момент оргазма.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"[npc.Name] [npc.verb(feel)] непреодолимая волна обжигающего возбуждения начинает нарастать глубоко внутри паха [npc.her], и с [npc.a_moan+], [npc.she] [npc.verb(clamp)] [npc.her] бедра, сильно сжимая голову [npc2.namePos]. [npc.Her] [npc.legs] начинают дрожать и подкашиваться под [npc.herHim], и с еще одним [npc.a_moan+], [npc.she] [npc.verb(start)] вперед. [npc2.NameIsFull] быстро прижимается к полу, и внезапно [npc2.verb(find)] [npc.name] сидит на [npc2.her], крича от экстаза, пока [npc.her] [npc.pussy+] спазмирует и сжимается вокруг [npc2.tongue+], который с силой втиснут в мягкие складки [npc.her].");
			
			if(Main.sex.getCharacterPerformingAction().hasPenis()) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ GenericOrgasms.getGenericPenisOrgasmDescription(
							this,
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this),
							OrgasmCumTarget.HAIR,
							this.getCondomFailure(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)),
							false));
			}
			
			UtilText.nodeContentSB.append("<br/><br/>Спустя несколько мгновений после того, как [npc2.namePos] терлась лицом о [npc.namePos], ее всепоглощающий оргазм начинает утихать, и [npc.she] [npc.verb(stand)] на дрожащих [npc.legs], ухмыляясь на [npc2.name], пока [npc.she] [npc.verb(feel)] скользкую струю слюны и [npc.girlCum], стекающую из [npc.her] [npc.pussy+].");
			
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false, UtilText.nodeContentSB.toString()).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), true).applyEffects();
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).isEndsSex();
		}
	};
	
}
