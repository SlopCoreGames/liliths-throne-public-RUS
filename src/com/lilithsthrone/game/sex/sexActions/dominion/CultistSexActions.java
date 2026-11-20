package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.3.4
 * @author Innoxia
 */
public class CultistSexActions {
	
	public static final SexAction FORCE_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Миссионерская";
		}

		@Override
		public String getActionDescription() {
			return "Встань так, чтобы оказаться между [npc2.namePos] и [npc2.legs].";
		}

		@Override
		public String getDescription() {
			return "Насладившись [npc2.name] своим [npc.her] ртом, [npc.name] [npc.verb(stand)] и #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF вперед, прижимая [npc.her] к [npc2.hers]. Схватив [npc2.her] [npc2.legs+], [npc.she] [npc.verb(push)] их, ухмыляясь, пока [npc.she] [npc.moanVerb], [npc.speech(Пора по-настоящему повеселиться!)]";
		}

		@Override
		public void applyEffects() {
			boolean performingSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.WITCH_SEAL);
			boolean targetedSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.WITCH_SEAL);
			
			if((performingSealed) || (targetedSealed)) {
				Main.sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Main.sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
				
			}
		}
	};
	
	public static final SexAction FORCE_POSITION_MISSIONARY_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()) != SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "Миссионерский оральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Опустись на колени и расположись лицом между ног [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			return "Опустившись на [npc.her] колени, [npc.name] [npc.verb(position)] [npc.her] голову между [npc2.namePos] [npc2.legs], готовая доставить [npc2.herHim] удовольствие своим [npc.her] ртом. Когда [npc2.namePos] [npc2.scent+] накрывает [npc.herHim], [npc.name] [npc.moanVerb], [npc.speech(О, это будет <i>так</i> весело!)]";
		}

		@Override
		public void applyEffects() {
			boolean performingSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.WITCH_SEAL);
			boolean targetedSealed = Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.WITCH_SEAL);
			
			if((performingSealed) || (targetedSealed)) {
				Main.sex.setSexManager(new SMAltarMissionarySealed(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))));
				
			} else {
				Main.sex.setSexManager(new SMAltarMissionary(
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR))));
			}
		}
	};
	
}
