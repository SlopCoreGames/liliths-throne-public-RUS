package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.8
 * @version 0.4.9.5
 * @author Innoxia
 */
public class ChairSex {

	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return GenericPositioning.checkBaseRequirements(data, request);
//		return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
//				&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
//				&& !(Main.sex.getPosition() == data.getPosition()
//					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
//					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
//				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
//				&& Main.sex.getTotalParticipantCount(false)<=(data.getPerformerSlots().size()+data.getPartnerSlots().size())
//				&& (request
//						?Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
//						:(Main.sex.getCharacterPerformingAction().isPlayer()
//							?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
//							:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())))
//				&& (!request && !Main.sex.getCharacterPerformingAction().isPlayer()
//						?((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
//						:true);
	}
	
	private static boolean isSittingAvailable(GameCharacter gettingFucked) {
		return true;
//		return !gettingFucked.isTaur();
	}
	
	private static void applyChangeSlotEffects(GameCharacter mover, SexSlot moverSlot, GameCharacter partner, SexSlot partnerSlot) {
		if(Main.sex.getCharacterInPosition(moverSlot)!=null) {
			Main.sex.swapSexPositionSlots(mover, Main.sex.getCharacterInPosition(moverSlot));
		}
		if(Main.sex.getCharacterInPosition(partnerSlot)!=null && !Main.sex.getCharacterInPosition(partnerSlot).equals(partner)) {
			Main.sex.swapSexPositionSlots(partner, Main.sex.getCharacterInPosition(partnerSlot));
		}
		
		Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
		Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
		
		if(Main.sex.isDom(mover)) {
			dominants.put(mover, moverSlot);
		} else {
			submissives.put(mover, moverSlot);
		}
		if(Main.sex.isDom(partner)) {
			dominants.put(partner, partnerSlot);
		} else {
			submissives.put(partner, partnerSlot);
		}

		Main.sex.setSexManager(new SexManagerDefault(
				SexPosition.SITTING,
				dominants,
				submissives){
		});
	}
	
	public static final SexAction SWITCH_TO_STANDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO,
						SexSlotStanding.STANDING_DOMINANT_THREE,
						SexSlotStanding.STANDING_DOMINANT_FOUR),
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_SUBMISSIVE,
						SexSlotStanding.STANDING_SUBMISSIVE_TWO,
						SexSlotStanding.STANDING_SUBMISSIVE_THREE,
						SexSlotStanding.STANDING_SUBMISSIVE_FOUR));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Перейти в положение стоя";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] встать вместе с вами, готовясь к смене позы.";
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] сменить позу, [npc.name] [npc.verb(get)] [npc2.name], чтобы встать вместе с [npc.herHim]. Теперь вы лицом к лицу с [npc.her] партнером, [npc.name] [npc.moansVerb], [npc.speech(Давай попробуем что-нибудь другое...)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Сделать минет";
		}
		@Override
		public String getActionDescription() {
			return "Опуститься на колени перед [npc2.name] и отсосать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] отсосать [npc2.name], [npc.name] [npc.verb(kneel)] перед [npc2.herHim]. Поднося [npc.her] рот к [npc2.namePos] паху, [npc.she] [npc.moansVerb], [npc.speech(Просто расслабься и наслаждайся!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.PERFORMING_ORAL,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_GIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Заняться оралом (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться опуститься на колени перед [npc2.name], чтобы отсосать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Вы пытаетесь маневрировать так, чтобы иметь возможность опуститься на колени перед [npc2.name]. Делая это, вы [npc.moan], [npc.speech(Пожалуйста, я хочу использовать свой рот...)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL_TO_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Сделать минет";
		}
		@Override
		public String getActionDescription() {
			return "Сидя на месте, заставить [npc2.name] повернуться спиной к вам, демонстрируя свои [npc.her] животные гениталии, чтобы вы могли отсосать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] отсосать у [npc2.name], [npc.name] [npc.verb(sit)] вниз, заставив [npc2.name] развернуться задом наперед так, чтобы [npc2.her] звериные гениталии оказались прямо перед [npc.her] [npc.face]. Поднося [npc.her] рот к [npc2.namePos] паху, [npc.she] [npc.moansVerb], [npc.speech(Не терпится попробовать тебя на вкус!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_GIVING_ORAL_TO_TAUR_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Заняться оралом (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться сесть и заставить [npc2.name] развернуться к тебе задом, подставив [npc.her] звериные гениталии, чтобы ты мог отсосать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Ты пытаешься маневрировать так, чтобы иметь возможность сесть и заставить [npc2.name] подставить тебе [npc2.her] звериные гениталии. Пока ты это делаешь, ты [npc.moan], [npc.speech(Пожалуйста, я хочу использовать свой рот...)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Принять минет";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] опуститься на колени перед тобой и отсосать.";
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] получить минет, [npc.name] [npc.verb(get)] [npc2.name] опуститься на колени перед [npc.herHim]. Глядя вниз на [npc2.her] [npc2.eyes], [npc.name] [npc.moansVerb], [npc.speech(Пора пустить рот в дело!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.PERFORMING_ORAL);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_RECEIVING_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.PERFORMING_ORAL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Получить минет (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться заставить [npc2.name] опуститься на колени перед тобой, чтобы [npc2.she] мог отсосать тебе.";
		}
		@Override
		public String getDescription() {
			return "Ты пытаешься маневрировать так, чтобы иметь возможность заставить [npc2.name] опуститься на колени перед тобой. Пока ты это делаешь, ты [npc.moan], [npc.speech(Пожалуйста, я хочу, чтобы ты использовал свой рот...)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_TO_RECEIVING_ORAL_AS_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Принять минет";
		}
		@Override
		public String getActionDescription() {
			return "Развернись и выставь свои звериные гениталии перед [npc2.name], чтобы [npc2.she] могла заняться с тобой оральным сексом.";
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] получить немного орального секса, [npc.name] [npc.verb(stand)] [npc.verb(turn)] вокруг, прежде чем отступить назад и продемонстрировать [npc.her] животные гениталии [npc2.name]. Оглянувшись через [npc.her] плечо, [npc.name] [npc.moansVerb], [npc.speech(Пора пустить ротик в дело!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_RECEIVING_ORAL_AS_TAUR_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isTaur()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Получить минет (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться развернуться и выставить свои звериные гениталии перед [npc2.name], чтобы [npc2.she] могла заняться с тобой оральным сексом.";
		}
		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(try)], чтобы встать и развернуться, подставляя свои [npc.her] животные гениталии к [npc2.namePos] рту. Пока [npc.she] [npc.does] это, [npc.she] [npc.moans], [npc.speech(Пожалуйста, я хочу, чтобы ты использовала свой рот...)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_SITTING_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP));

		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Сесть (снизу)";
		}
		@Override
		public String getActionDescription() {
			return "Смените позицию так, чтобы сидели вы, а [npc2.name] сидела у вас на коленях.";
		}
		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(decide)], чтобы сменить позы: заставив [npc2.name] встать, [npc.she] [npc.verb(sit)] вниз, а затем потянув [npc2.herHim] на [npc.her] колени. Глядя снизу вверх в [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], [npc.speech(Хороший [npc2.girl]!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING_IN_LAP);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_SITTING_BOTTOM_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING),
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterPerformingAction())
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Сесть (снизу) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться заставить [npc2.name] сесть к вам на колени.";
		}
		@Override
		public String getDescription() {
			return "Вы пытаетесь маневрировать так, чтобы усадить [npc2.name] к себе на колени. Пока вы это делаете, вы [npc.moan], [npc.speech(Пожалуйста, я хочу быть снизу...)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_SITTING_TOP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Сесть (сверху)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Поменяться местами так, чтобы [npc2.name] сидела, а ты сидел(а) у нее на [npc2.her]";
			} else {
				return "Поменяться местами так, чтобы [npc2.name] сидела, а ты развернулся (развернулась) и опустил(а) свои звериные гениталии ей на [npc2.her]";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "[npc.Name] [npc.verb(decide)] поменяться местами: заставив [npc2.name] сесть, [npc.she] [npc.verb(sit)] на [npc2.her]. Глядя в [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], [npc.speech(Хороший [npc2.girl]!)]";
			} else {
				return "[npc.Name] [npc.verb(decide)] чтобы сменить позы, и, заставив [npc2.name] сесть, [npc.she] [npc.verb(stand)] вверх, [npc.verb(turn)] вокруг, и [npc.verb(lower)] [npc.her] животный зад в [npc2.namePos] колени. Оглянувшись через [npc.her] плечо, [npc.name] затем [npc.moansVerb], [npc.speech(Пора прокатиться!)]";
			}
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING_IN_LAP,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction POSITION_SITTING_TOP_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Сесть (сверху) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться сесть на [npc2.namePos]";
		}
		@Override
		public String getDescription() {
			return "Ты пытаешься маневрировать так, чтобы сесть на [npc2.namePos]. В процессе ты [npc.moan], [npc.speech(Пожалуйста, я хочу быть сверху...)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction SWITCH_STANDING_BETWEEN_LEGS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));

		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterTargetedForSexAction(this))
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Между ног";
		}
		@Override
		public String getActionDescription() {
			return "Поменяться местами так, чтобы [npc2.name] сидела, а ты "
					+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"standing":"kneeling")+" между [npc2.her] [npc2.legs], готовый трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(decide)] сменить позицию, и, когда [npc2.name] сядет, [npc.she] [npc.verb(move)], чтобы "
						+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"stand":"kneel")+" между [npc2.her] [npc2.legs]. Глядя в [npc2.her] [npc2.eyes], [npc.she] [npc.moansVerb], [npc.speech(Пора хорошенько тебя оттрахать!)]";
		}
		@Override
		public void applyEffects() {
			applyChangeSlotEffects(
					Main.sex.getCharacterPerformingAction(),
					SexSlotSitting.SITTING_BETWEEN_LEGS,
					Main.sex.getCharacterTargetedForSexAction(this),
					SexSlotSitting.SITTING);
//			GenericPositioningNew.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_STANDING_BETWEEN_LEGS_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(SexSlotSitting.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(SexSlotSitting.SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isSittingAvailable(Main.sex.getCharacterTargetedForSexAction(this))
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Между ног (П)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться сменить позицию так, чтобы [npc2.name] сидела, а ты был "
						+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"standing":"kneeling")+" между [npc2.her] [npc2.legs], готовый трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Ты пытаешься маневрировать так, чтобы [npc2.nameIsFull] сидела, а ты был "
						+(SexSlotSitting.SITTING_BETWEEN_LEGS.isStanding(Main.sex.getCharacterPerformingAction())?"standing":"kneeling")+" между [npc2.her] [npc2.legs]. Пока ты это делаешь, ты [npc.moan], [npc.speech(Пожалуйста, я хочу трахаться с тобой вот так...)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PARTNER_POSITION_RESPONSE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPositionRequest()!=null
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			boolean isHappy = ((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Main.sex.getPositionRequest().getPosition(),
					Main.sex.getPositionRequest().getPartnerSlots().get(0),
					Main.sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer());
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotSitting.SITTING_IN_LAP) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Грубо толкнув [npc2.name] вниз, [npc.name] [npc.verb(straddle)] на [npc2.her], наклоняясь вперед, чтобы свирепо смотреть в [npc2.her] глаза, пока [npc.she] [npc.moansVerb], [npc.speech(Ладно, сучка, сейчас я тебя прокачу!)]";
						default:
							return "Прижимая [npc2.name], [npc.name] наклоняется вперед на [npc.steps] и [npc.verb(straddle)] на [npc2.her], пристально глядя в [npc2.her] глаза, пока [npc.she] [npc.moansVerb], [npc.speech(Конечно, я тебя прокачу...)]";
					}
					
				} else {
					return "Потянувшись вниз, чтобы схватить [npc2.name] за [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] обратно в прежнее положение [npc2.her], пока [npc.she] гневно отчитывает [npc2.herHim], [npc.speech(Ты что творишь?! Только <i>посмей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotSitting.SITTING) {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotSitting.SITTING_IN_LAP) {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								return "Садясь, [npc.name] [npc.verb(grab)] хватает [npc2.namePos] за [npc2.arm] и резким рывком [npc.she] [npc.verb(pull)] [npc2.herHim] вниз, так что [npc2.sheIs] оказывается верхом на [npc.her]. Наклонившись вперед, чтобы впиться взглядом в глаза [npc2.her], [npc.name] [npc.moansVerb], [npc.speech(Ладно, шлюха, лучше сделай это хорошо!)]";
							default:
								return "Садясь, [npc.name] [npc.verb(take)] хватает [npc2.namePos] за [npc2.arm] и уверенным рывком [npc.she] [npc.verb(pull)] [npc2.herHim] вниз, так что [npc2.sheIs] оказывается верхом на [npc.her]. Наклонившись вперед, чтобы заглянуть в глаза [npc2.her], [npc.name] [npc.moansVerb], [npc.speech(Хорошая [npc2.girl]...)]";
						}
						
					} else {
						return "Потянувшись вниз, чтобы схватить [npc2.name] за [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] обратно в прежнее положение [npc2.her], пока [npc.she] гневно отчитывает [npc2.herHim], [npc.speech(Ты что творишь?! Только <i>посмей</i> еще раз так сделать!)]";
					}
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL) {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								return "Позволив [npc2.name] завершить [npc2.her] маневр, [npc.name] тянется, чтобы схватить [npc2.her] за задние лапы, а затем подтягивает [npc2.her] животное пах к [npc.her] лицу. Дав [npc2.her] звонкую пощечину по заднице, [npc.name] затем [npc.moansVerb], [npc.speech(Ладно, шлюшка, постарайся, чтобы всё было на высшем уровне!)]";
							default:
								return "Позволив [npc2.name] завершить [npc2.her] маневр, [npc.name] тянется, чтобы схватить [npc2.her] за задние лапы, а затем подтягивает [npc2.her] животное пах к [npc.her] лицу. Игриво шлепнув [npc2.her] по заднице, [npc.name] затем [npc.moansVerb], [npc.speech(Хорошая [npc2.girl]...)]";
						}
						
					} else {
						return "Потянувшись вниз, чтобы схватить [npc2.name] за [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] обратно в прежнее положение [npc2.her], пока [npc.she] гневно отчитывает [npc2.herHim], [npc.speech(Ты что творишь?! Только <i>посмей</i> еще раз так сделать!)]";
					}
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotSitting.SITTING_BETWEEN_LEGS) {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								if(Main.sex.getCharacterPerformingAction().hasLegs()) {
									return "Позволив [npc2.name] завершить [npc2.her] маневр, [npc.name] садится, прежде чем поднять [npc.her] [npc.legs], обхватывая ими [npc2.namePos] поясницу и грубо толкая [npc2.herHim] вперед. Глядя исподлобья на [npc2.herHim], [npc.name] затем разводит [npc.her] [npc.legs] и [npc.moansVerb], [npc.speech(Ладно, шлюшка, постарайся, чтобы всё было на высшем уровне!)]";
								} else {
									return "Позволив [npc2.name] завершить [npc2.her] маневр, [npc.name] садится, подставляя [npc.her] пах [npc2.name]. Глядя исподлобья на [npc2.herHim], [npc.she] [npc.moansVerb], [npc.speech(Ладно, шлюшка, постарайся, чтобы всё было на высшем уровне!)]";
								}
							default:
								if(Main.sex.getCharacterPerformingAction().hasLegs()) {
									return "Позволив [npc2.name] завершить [npc2.her] маневр, [npc.name] садится, после чего приподнимает [npc.her] [npc.legs], обвивая ими [npc2.namePos] поясницу и заставляя [npc2.herHim] податься вперед. Улыбаясь [npc2.herHim] снизу вверх, [npc.name] широко разводит [npc.her] [npc.legs] и [npc.moansVerb], [npc.speech(Ну же, трахни меня!)]";
								} else {
									return "Позволив [npc2.name] завершить [npc2.her] маневр, [npc.name] садится, после чего подставляет [npc.her] пах [npc2.name]. Улыбаясь [npc2.herHim] снизу вверх, [npc.she] [npc.moansVerb], [npc.speech(Ну же, трахни меня!)]";
								}
						}
						
					} else {
						return "Потянувшись вниз, чтобы схватить [npc2.name] за [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] обратно в прежнее положение [npc2.her], пока [npc.she] гневно отчитывает [npc2.herHim], [npc.speech(Ты что творишь?! Только <i>посмей</i> еще раз так сделать!)]";
					}
					
				} else {
					if(isHappy) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
							case DOM_ROUGH:
								return "Сев, [npc.name] [npc.verb(grab)] хватает [npc2.namePos] за [npc2.arm] и резким рывком [npc.she] [npc.verb(pull)] опускает [npc2.herHim] на [npc2.her] колени перед [npc.herHim]. Наклонившись вперед, чтобы впиться взглядом в [npc2.her] глаза, [npc.name] [npc.moansVerb], [npc.speech(Ну же, шлюшка, используй свой ротик!)]";
							default:
								return "Садясь, [npc.name] [npc.verb(take)] хватает [npc2.namePos] за [npc2.arm] и резким рывком [npc.she] [npc.verb(pull)] [npc2.herHim] на [npc2.her] колени перед [npc.herHim]. Наклонившись вперед, чтобы заглянуть в [npc2.her] глаза, [npc.name] [npc.moansVerb], [npc.speech(Хороший [npc2.girl]. Давай заставим твой ротик поработать...)]";
						}
						
					} else {
						return "Потянувшись вниз, чтобы схватить [npc2.name] за [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] обратно в прежнее положение [npc2.her], пока [npc.she] гневно отчитывает [npc2.herHim], [npc.speech(Ты что творишь?! Только <i>посмей</i> еще раз так сделать!)]";
					}
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotSitting.PERFORMING_ORAL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Грубо толкнув [npc2.name] вниз, [npc.name] опускается на колени перед [npc2.herHim] и, подавшись вперед, свирепо смотрит [npc2.her] прямо в глаза, пока [npc.she] [npc.moansVerb], [npc.speech(Ладно, шлюха, тебе лучше радоваться тому, что мне нравится делать минет!)]";
						default:
							return "Прижав [npc2.name] к сиденью, [npc.name] опускается на колени перед [npc2.herHim] и, подавшись вперед, заглядывает [npc2.her] в глаза, пока [npc.she] [npc.moansVerb], [npc.speech(Обожаю делать минет...)]";
					}
					
				} else {
					return "Потянувшись вниз, чтобы схватить [npc2.name] за [npc2.arm], [npc.name] [npc.verb(pull)] [npc2.herHim] обратно в прежнее положение [npc2.her], пока [npc.she] гневно отчитывает [npc2.herHim], [npc.speech(Ты что творишь?! Только <i>посмей</i> еще раз так сделать!)]";
				}
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if(((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
					Main.sex.getPositionRequest().getPosition(),
					Main.sex.getPositionRequest().getPartnerSlots().get(0),
					Main.sex.getPositionRequest().getPerformerSlots().get(0),
					Main.game.getPlayer())) {

				applyChangeSlotEffects(
						Main.sex.getCharacterPerformingAction(),
						Main.sex.getPositionRequest().getPartnerSlots().get(0),
						Main.game.getPlayer(),
						Main.sex.getPositionRequest().getPerformerSlots().get(0));
				
//				GenericPositioningNew.setNewSexManager(Main.sex.getPositionRequest(), true);
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
