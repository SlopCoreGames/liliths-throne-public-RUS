package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * @since 0.3.1
 * @version 0.3.8.1
 * @author Innoxia
 */
public class GenericPositioning {

	private static String getRequestTooltipText() {
		return "[style.italicsSex(Это просьба, от которой [npc2.name] может отказаться!)]";
	}
	
	public static final SexAction POSITION_SWAP = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isPositionSwap() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().equals(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getInitialSexManager().isSwapPositionAllowed(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration()==Main.sex.getCharacterTargetedForSexAction(this).getLegConfiguration() // Can only swap if have same body type
					&& (Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Поменяться местами с [npc2.name]";
		}

		@Override
		public String getActionDescription() {
			return "Поменяться позицией с [npc2.name].";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return "Мягко обхватив [npc2.name], вы стараетесь не разбудить [npc2.herHim], пока перемещаете [npc2.herHim] и меняетесь местами с [npc2.herHim].";
			}
			return "Обхватив [npc2.name], вы перемещаете [npc2.herHim] и меняетесь местами с [npc2.herHim], после чего [npc.moaning] [npc.speech(Так будет куда веселее!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.swapSexPositionSlots(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static boolean checkBaseRequirements(PositioningData data, boolean request) {
		for(SexSlot slot : data.getPartnerSlots()) {
			if(!Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), slot)) {
				return false;
			}
		}
		for(SexSlot slot : data.getPerformerSlots()) {
			if(!Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterPerformingAction(), slot)) {
				return false;
			}
		}
		return Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
				&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
				&& !(Main.sex.getPosition() == data.getPosition()
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
				&& Main.sex.getTotalParticipantCount(false)<=(data.getPerformerSlots().size()+data.getPartnerSlots().size())
				&& (request
					?(Main.sex.getCharacterPerformingAction().isPlayer()
							&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
							&& !Main.sex.isPositioningRequestBlocked(Main.sex.getCharacterPerformingAction(), data.getPosition()))
					:(Main.sex.getCharacterPerformingAction().isPlayer()
						?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL || Main.sex.getCharacterPerformingAction().hasTraitActivated(Perk.CONVINCING_REQUESTS)
						:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())
							&& ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))));
	}

	public static void setNewSexManager(PositioningData data, boolean requestAccepted) {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		List<GameCharacter> dominantSpectators = new ArrayList<>(Main.sex.getDominantSpectators());
		List<GameCharacter> submissiveSpectators = new ArrayList<>(Main.sex.getSubmissiveSpectators());
		
		GameCharacter performer = Main.sex.getCharacterPerformingAction();
		GameCharacter target = Main.sex.getTargetedPartner(performer);
		if(requestAccepted) {
			target = Main.sex.getCharacterPerformingAction();
			performer = Main.sex.getTargetedPartner(target);
		}
		
		if(Main.sex.isDom(performer)) {
			doms.remove(performer);
			dominants.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				if(i+1<data.getPerformerSlots().size()) {
					dominants.put(doms.get(i), data.getPerformerSlots().get(i+1));
				} else {
					dominantSpectators.add(doms.get(i));
				}
			}
			subs.remove(target);
			submissives.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				if(i+1<data.getPartnerSlots().size()) {
					submissives.put(subs.get(i), data.getPartnerSlots().get(i+1));
				} else {
					submissiveSpectators.add(subs.get(i));
				}
			}
			
		} else {
			doms.remove(target);
			dominants.put(target, data.getPartnerSlots().get(0));
			for(int i=0; i<doms.size(); i++) {
				if(i+1<data.getPartnerSlots().size()) {
					dominants.put(doms.get(i), data.getPartnerSlots().get(i+1));
				} else {
					dominantSpectators.add(doms.get(i));
				}
			}
			subs.remove(performer);
			submissives.put(performer, data.getPerformerSlots().get(0));
			for(int i=0; i<subs.size(); i++) {
				if(i+1<data.getPerformerSlots().size()) {
					submissives.put(subs.get(i), data.getPerformerSlots().get(i+1));
				} else {
					submissiveSpectators.add(subs.get(i));
				}
			}
		}
		Main.sex.setSexManager(new SexManagerDefault(
						data.getPosition(),
						dominants,
						submissives){
				},
				dominantSpectators,
				submissiveSpectators);
		Main.sex.setPositionRequest(null);
	}

	
	
	
	//--------------- ORAL ---------------//
	
	private static List<SexSlot> generatePerformerOralData(GameCharacter performer, GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		subs.remove(receiver);

		boolean bipedalOral1 = !receiver.isTaur();
		boolean doubleReceiving = false;
		GameCharacter receiver2 = null;
		if(Main.sex.isDom(receiver)) {
			doubleReceiving = doms.size()>=1;
			if(doubleReceiving) {
				receiver2 = doms.get(0);
			}
		} else {
			doubleReceiving = subs.size()>=1;
			if(doubleReceiving) {
				receiver2 = subs.get(0);
			}
		}
		boolean bipedalOral2 = receiver2!=null?!receiver2.isTaur():false;
		
		List<SexSlot> performerSlots = new ArrayList<>();
		if(bipedalOral1) {
			performerSlots.add(SexSlotStanding.PERFORMING_ORAL);
		} else {
			if(receiver.hasPenis()
					&& (!Main.game.isInSex() || ((performer instanceof NPC) && ((NPC)performer).getCurrentSexPreference(receiver)!=null && ((NPC)performer).getCurrentSexPreference(receiver).getTargetedSexArea()==SexAreaPenetration.PENIS))) {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL);
			} else {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}
		}
		if(doubleReceiving) {
			if(bipedalOral2) {
				performerSlots.add(SexSlotStanding.PERFORMING_ORAL_TWO);
			} else {
				if(receiver2.hasPenis()) {
					performerSlots.add(SexSlotStanding.PERFORMING_ORAL_TWO);
				} else {
					performerSlots.add(SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO);
				}
			}
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL_TWO,
				SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingOralData(GameCharacter performer, GameCharacter receiver) {
		return new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO),
				generatePerformerOralData(performer, receiver));
	}

	private static PositioningData generatePerformingOralData(GameCharacter performer, GameCharacter receiver) {
		return new PositioningData(
				SexPosition.STANDING,
				generatePerformerOralData(performer, receiver),
				Util.newArrayListOfValues(
						SexSlotStanding.STANDING_DOMINANT,
						SexSlotStanding.STANDING_DOMINANT_TWO));
	}
	
	public static final SexAction POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public String getActionTitle() {
			return "Принимать минет стоя";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Заставить [npc2.name] отсосать вам. Как только [npc2.sheHasFull] начнется, вы сможете заставить [npc2.herHim] переходить от вашего лица к вашему заду.";
			} else {
				return "Заставить [npc2.name] отсосать вам. Как только [npc2.sheHasFull] начнется, вы сможете заставить [npc2.herHim] переходить от коленопреклонения перед вами к позиции позади вашего звериного тела.";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Желая, чтобы [npc2.name] отсосала [npc.herHim], [npc.name] [npc.verb(position)] [npc2.herHim] так, чтобы [npc2.sheIs] стояла перед [npc.herHim]. Усмехаясь на [npc2.herHim], [npc.name] [npc.verb(order)], [npc.speech(Давай, используй свой ротик!)]";
					} else {
						return "Желая, чтобы [npc2.name] отсосала [npc.herHim], [npc.name] [npc.verb(push)] [npc2.herHim] вниз так, чтобы [npc2.sheIs] стояла на коленях перед [npc.herHim]. Усмехаясь на [npc2.herHim], [npc.name] [npc.verb(order)], [npc.speech(Давай, используй свой ротик!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Желая, чтобы [npc2.name] сделал анилингус, [npc.name] [npc.verb(position)] [npc2.herHim] так, чтобы [npc2.sheIs] стоял позади [npc.herHim]. Оглядываясь через [npc.her] плечо, [npc.she] [npc.verb(grin)] на [npc2.herHim], прежде чем приказать: [npc.speech(Давай, используй свой ротик!)]";
					} else {
						return "Желая, чтобы [npc2.name] сделал анилингус, [npc.name] [npc.verb(push)] [npc2.herHim] вниз так, чтобы [npc2.sheIs] стоял на коленях позади [npc.herHim]. Оглядываясь через [npc.her] плечо, [npc.she] [npc.verb(grin)] на [npc2.herHim], прежде чем приказать: [npc.speech(Давай, используй свой ротик!)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Желая, чтобы [npc2.name] сделал оральный секс [npc.herHim], [npc.name] [npc.verb(position)] [npc2.herHim] так, чтобы [npc2.sheIs] стоял под [npc.her], пока [npc.legRace] наклоняется. Шагнув вперед и прижав [npc.herself] к [npc2.herHim], [npc.name] [npc.verb(call)] наружу, [npc.speech(Давай, используй свой ротик!)]";
					} else {
						return "Желая, чтобы [npc2.name] сделал оральный секс [npc.herHim], [npc.name] [npc.verb(push)] [npc2.herHim] вниз так, чтобы [npc2.sheIs] стоял на коленях под [npc.her], пока [npc.legRace] наклоняется. Шагнув вперед и прижав [npc.herself] к [npc2.herHim], [npc.name] [npc.verb(call)] наружу, [npc.speech(Давай, используй свой ротик!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
						return "Желая, чтобы [npc2.name] отсосал(а) с задней части нижней части тела [npc.her] [npc.legRace], [npc.name] [npc.verb(position)] [npc2.herHim] так, чтобы [npc2.sheIs] стоял(а) позади [npc.herHim]. Оглянувшись через плечо [npc.her], [npc.she] [npc.verb(grin)] на [npc2.herHim], прежде чем приказать: [npc.speech(Давай, используй свой ротик!)]";
					} else {
						return "Желая, чтобы [npc2.name] отсосал(а) заднюю часть нижней части тела [npc.her] [npc.legRace], [npc.name] [npc.verb(push)] [npc2.herHim] так, чтобы [npc2.sheIs] опустился(ась) на колени позади [npc.herHim]. Оглянувшись через [npc.her] плечо, [npc.she] [npc.verb(grin)] на [npc2.herHim], прежде чем приказать: [npc.speech(Давай, используй свой ротик!)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isFeral()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_RECEIVING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO;
		}
		@Override
		public String getActionTitle() {
			return "Стоя принимать оральный секс (R)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Попытаться заставить [npc2.name] отсосать тебе. Если [npc2.she] согласится, ты сможешь попросить [npc2.herHim] переключаться между твоим передом и задом.<br/>"
						+ getRequestTooltipText();
			} else {
				return "Попытаться заставить [npc2.name] отсосать тебе. Если [npc2.she] согласится, ты сможешь попросить [npc2.herHim] переключаться между тем, чтобы стоять перед твоим звериным телом или позади него.<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					return "Желая, чтобы [npc2.name] отсосала [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] стояла перед [npc2.herHim]. Вглядываясь в [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, я хочу, чтобы ты использовала свой ротик!)]";
				} else {
					return "Желая, чтобы [npc2.name] сделал анилингус [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] стоял(ла) спиной [npc.her] к [npc2.herHim]. Оглядываясь через [npc.her] плечо, [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, я хочу, чтобы ты использовал(а) свой рот!)]";
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					return "Желая, чтобы [npc2.name] сделал оральный секс [npc.herHim], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] демонстрировал нижнюю часть [npc.her] тела [npc.legRace] для [npc2.herHim]. Вглядываясь в [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, я хочу, чтобы ты использовал свой рот!)]";
				} else {
					return "Желая, чтобы [npc2.name] отсосал(а) с задней части нижней части тела [npc.her] [npc.legRace], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] демонстрировал(а) [npc.her] задницу [npc2.herHim]. Оглядываясь через [npc.her] плечо, [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, я хочу, чтобы ты использовал(а) свой рот!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generateReceivingOralData(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction()));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ORAL_RECEIVING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStanding.PERFORMING_ORAL_BEHIND);
		}
		@Override
		public String getActionTitle() {
			return "Переместиться [npc2.herHim] назад";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Заставить [npc2.name] сменить позицию так, чтобы [npc2.sheIsFull] оказался(ась) позади тебя и смог(ла) заняться оральным сексом с твоим [npc.asshole].";
			} else {
				return "Заставить [npc2.name] сменить позицию так, чтобы [npc2.sheIsFull] оказался(ась) позади тебя и смог(ла) заняться оральным сексом с твоим [npc.asshole]"+(Main.sex.getCharacterPerformingAction().hasVagina()?" и [npc.pussy]":"")+".";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Желая, чтобы [npc2.name] отлизала [npc.herHim], [npc.name] [npc.verb(reposition)] [npc2.herHim] так, чтобы [npc2.sheIs] стояла позади [npc.herHim]. Прижимая [npc.her] [npc.ass+] назад к [npc2.her] [npc2.face], [npc.she] [npc.verb(order)], [npc.speech(Давай, используй свой ротик!)]";
				} else {
					return "Желая, чтобы [npc2.name] отлизала [npc.herHim], [npc.name] [npc.verb(reposition)] и [npc.verb(push)] [npc2.herHim] вниз так, чтобы [npc2.sheIs] стояла на коленях позади [npc.herHim]. Прижимая [npc.her] [npc.ass+] назад к [npc2.her] [npc2.face], [npc.she] [npc.verb(order)], [npc.speech(Давай, используй свой ротик!)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Желая, чтобы [npc2.name] отсосал(а) заднюю часть нижней части тела [npc.her] [npc.legRace], [npc.name] [npc.verb(reposition)] [npc2.herHim] так, чтобы [npc2.sheIs] стоял(а) позади [npc.herHim]. Прижимая [npc.her] [npc.ass+] спиной к [npc2.her] [npc2.face], [npc.she] [npc.verb(order)], [npc.speech(Давай, используй свой рот!)]";
				} else {
					return "Желая, чтобы [npc2.name] отсосал в заднюю часть нижней части тела [npc.her] [npc.legRace], [npc.name] [npc.verb(reposition)] и [npc.verb(push)] [npc2.herHim] вниз, так чтобы [npc2.sheIs] стоял на коленях позади [npc.herHim]. Прижимая [npc.her] [npc.ass+] спиной к [npc2.her] [npc2.face], [npc.she] [npc.verb(order)], [npc.speech(Давай, используй свой рот!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))!=SexSlotStanding.PERFORMING_ORAL_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterTargetedForSexAction(this), SexSlotStanding.PERFORMING_ORAL);
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Переместить [npc2.herHim] вперед";
			} else {
				return "Переместить [npc2.herHim] под";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Заставить [npc2.name] сменить позу так, чтобы [npc2.sheIsFull] оказалась перед вами и смогла отсосать ваши гениталии.";
			} else {
				if(Main.sex.getCharacterPerformingAction().hasPenis()) {
					return "Заставить [npc2.name] сменить позу так, чтобы [npc2.sheIsFull] стояла перед вами на коленях и смогла отсосать ваши [npc.cock]"+(Main.sex.getCharacterPerformingAction().hasBreastsCrotch()?" и [npc.crotchBoobs]":"")+".";
				} else {
					return "Заставить [npc2.name] сменить позу так, чтобы [npc2.sheIsFull] стояла перед вами на коленях"+(Main.sex.getCharacterPerformingAction().hasBreastsCrotch()?", и смогла отсосать ваши [npc.crotchBoobs]":"")+".";
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Желая, чтобы [npc2.name] отсосала [npc.herHim], [npc.name] [npc.verb(reposition)] [npc2.herHim] так, чтобы [npc2.sheIs] стояла перед [npc.herHim]. Усмехаясь на [npc2.herHim], [npc.name] [npc.verb(order)], [npc.speech(Давай, используй свой ротик!)]";
				} else {
					return "Желая, чтобы [npc2.name] отсосала [npc.herHim], [npc.name] [npc.verb(reposition)] и [npc.verb(push)] [npc2.herHim] вниз так, чтобы [npc2.sheIs] стояла на коленях перед [npc.herHim]. Усмехаясь на [npc2.herHim], [npc.name] [npc.verb(order)], [npc.speech(Давай, используй свой ротик!)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterTargetedForSexAction(this))) {
					return "Желая, чтобы [npc2.name] отсосала нижнюю часть [npc.her] ниже по телу [npc.legRace], [npc.name] [npc.verb(reposition)] [npc2.herHim] так, чтобы [npc2.sheIs] стояла под [npc.herHim]. Сделав шаг вперед и прижимая [npc.herself] к [npc2.herHim], [npc.name] [npc.verb(call)] наружу, [npc.speech(Давай, используй свой ротик!)]";
				} else {
					return "Желая, чтобы [npc2.name] отсосал(а) снизу [npc.legRace] тела [npc.her], [npc.name] [npc.verb(reposition)] и [npc.verb(push)] [npc2.herHim] вниз, так чтобы [npc2.sheIs] стоял(а) на коленях под [npc.herHim]. Сделав шаг вперед и прижав [npc.herself] к [npc2.herHim], [npc.name] [npc.verb(call)] наружу, [npc.speech(Давай, используй свой ротик!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public String getActionTitle() {
			return "Сделать минет";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Опуститься на колени и отсосать [npc2.name]. Как только начнете, можно будет переключаться между передней и задней стороной [npc2.her].";
			} else {
				return "Опуститься на колени и отсосать [npc2.name]. Как только начнете, можно будет переключаться между положением под или позади звериного тела [npc2.her].";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая отсосать [npc2.name], [npc.name] [npc.verb(move)] вокруг так, чтобы [npc.sheIs] стоял(а) перед [npc2.herHim]. Глядя снизу вверх в [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb], [npc.speech(Вот так, дай мне использовать мой ротик!)]";
					} else {
						return "Желая отсосать [npc2.name], [npc.name] [npc.verb(move)] вокруг и [npc.verb(kneel)] перед [npc2.herHim]. Глядя снизу вверх в [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb], [npc.speech(Вот так, дай мне использовать мой ротик!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая отсосать анус [npc2.name], [npc.name] [npc.verb(move)] вокруг так, чтобы [npc.sheIs] стоял(а) позади [npc2.herHim]. Схватив [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb], [npc.speech(О да, дай мне сожрать твою задницу!)]";
					} else {
						return "Желая, чтобы [npc2.name] сделал анилингус, [npc.name] [npc.verb(move)] вокруг и [npc.verb(kneel)] вниз позади [npc2.herHim]. Схватив [npc2.her] за [npc2.hips+], [npc.she] [npc.moansVerb], [npc.speech(О да, дай мне отсосать твою задницу!)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая сделать оральный секс [npc2.name], [npc.name] [npc.verb(move)] вокруг так, чтобы [npc.sheIs], стоящий под [npc2.her], опустил тело [npc2.legRace]. Проводя [npc.a_hand] вверх по одной из задних [npc2.legs] [npc2.her], [npc.she] [npc.moansVerb], [npc.speech(О да, пора пустить в дело свой ротик!)]";
					} else {
						return "Желая сделать оральный секс [npc2.name], [npc.name] [npc.verb(move)] вокруг и [npc.verb(kneel)] вниз под тело [npc2.her], опущенное [npc2.legRace]. Проводя [npc.a_hand] вверх по одной из задних [npc2.legs] [npc2.her], [npc.she] [npc.moansVerb], [npc.speech(О да, пора пустить в дело свой ротик!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая сделать оральный секс задней части тела [npc2.namePos], опущенного [npc2.legRace], [npc.name] [npc.verb(move)] вокруг так, чтобы [npc.sheIs] стоял позади [npc2.herHim]. Проводя [npc.a_hand] вверх и через [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb], [npc.speech(О да, пора пустить в дело свой ротик!)]";
					} else {
						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]. Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb], [npc.speech(Oh yes, time to put my mouth to use!)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isFeral()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ORAL_PERFORMING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO;
		}
		@Override
		public String getActionTitle() {
			return "Заняться оралом (R)";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Опуститься на колени и попытаться убедить [npc2.name] позволить вам заняться оралом с [npc2.herHim]. Если [npc2.she] согласится, вы сможете дополнительно попросить переключиться между передней и задней стороной [npc2.her].<br/>"
						+ getRequestTooltipText();
			} else {
				return "Опуститься на колени и попытаться убедить [npc2.name] позволить вам заняться оралом с [npc2.herHim]. Если [npc2.she] согласится, вы сможете дополнительно попросить переключиться между коленопреклонением под или позади звериного тела [npc2.her].<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая заняться оралом с [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] стояла перед [npc2.herHim]. Глядя снизу вверх в [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, позволь мне пустить мой ротик в дело!)]";
					} else {
						return "Желая заняться оральным сексом с [npc2.name], [npc.name] [npc.verb(drop)] на колени перед [npc.her] на глазах у [npc2.herHim]. Заглядывая в [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, позволь мне использовать свой ротик!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая заняться анилингусом с [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] стояла позади [npc2.herHim]. Схватив [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, дай мне использовать мой ротик!)]";
					} else {
						return "Желая заняться анилингусом с [npc2.name], [npc.name] [npc.verb(drop)] на колени [npc.her] позади [npc2.herHim]. Схватив [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, дай мне использовать мой ротик!)]";
					}
				}
				
			} else { // Taur body:
				if(generatePerformerOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction()).get(0)==SexSlotStanding.PERFORMING_ORAL) {
					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая заняться оральным сексом с [npc2.name], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] стояла под [npc2.her], наклонившей тело [npc2.legRace]. Проводя [npc.a_hand] по одной из задних [npc2.her] [npc2.legs], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, дай мне использовать мой ротик!)]";
					} else {
						return "Желая заняться оральным сексом с [npc2.name], [npc.name] [npc.verb(drop)] на колени [npc.her] под [npc2.her], наклонившей тело [npc2.legRace]. Проводя [npc.a_hand] по одной из задних [npc2.her] [npc2.legs], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, дай мне использовать мой ротик!)]";
					}
				} else {
					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
						return "Желая заняться оральным сексом с задней частью [npc2.namePos], наклонив тело [npc2.legRace], [npc.name] [npc.verb(position)] [npc.herself] так, чтобы [npc.sheIs] стояла позади [npc2.herHim]. Схватив [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, дай мне использовать мой ротик!)]";
					} else {
						return "Желая заняться оралом с задней части тела [npc2.namePos] снизу [npc2.legRace], [npc.name] [npc.verb(drop)] на колени [npc.her] позади [npc2.herHim]. Схватив [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, позволь мне применить мой рот!)]";
					}
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generatePerformingOralData(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)));
//			System.out.println(Main.sex.getPositionRequest().getPosition().getName()+", "+Main.sex.getPositionRequest().getPerformerSlots().get(0).getName(null)+", "+Main.sex.getPositionRequest().getPartnerSlots().get(0).getName(null));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ORAL_PERFORMING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
//	public static final SexAction POSITION_ORAL_PERFORMING_BEHIND = new SexAction(
//			SexActionType.POSITIONING,
//			ArousalIncrease.ONE_MINIMUM,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ONE_VANILLA,
//			null,
//			SexParticipantType.NORMAL) {
//		
//		@Override
//		public boolean isBaseRequirementsMet() {
//			return checkBaseRequirements(generatePerformingOralData(Main.sex.getCharacterTargetedForSexAction(this)), false);
//		}
//		@Override
//		public String getActionTitle() {
//			return "Perform oral (behind)";
//		}
//		@Override
//		public String getActionDescription() {
//			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
//				return "Get down behind [npc2.name], so that you're able to perform oral on [npc2.her] [npc2.asshole].";
//			} else {
//				return "Get down behind [npc2.name], so that you're able to perform oral on [npc2.her] [npc2.asshole]"+(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()?" and [npc2.pussy]":"")+".";
//			}
//		}
//		@Override
//		public String getDescription() {
//			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
//				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
//					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing before [npc2.herHim]."
//								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(That's right, let me put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down before [npc2.herHim]."
//								+ " Looking up into [npc2.her] [npc2.eyes+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(That's right, let me put my mouth to use!)]";
//					}
//				} else {
//					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform anilingus on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
//								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, let me eat your ass!)]";
//					} else {
//						return "Wanting [npc2.name] to perform anilingus, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
//								+ " Taking hold of [npc2.her] [npc2.hips+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, let me eat your ass!)]";
//					}
//				}
//				
//			} else { // Taur body:
//				if(generatePerformerOralData(Main.sex.getCharacterTargetedForSexAction(this)).get(0)==SexSlotStanding.PERFORMING_ORAL) {
//					if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing beneath [npc2.her] lower [npc2.legRace]'s body."
//								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on [npc2.name], [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down beneath [npc2.her] lower [npc2.legRace]'s body."
//								+ " Running [npc.a_hand] up one of [npc2.her] rear [npc2.legs], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					}
//				} else {
//					if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
//						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around so that [npc.sheIs] standing behind [npc2.herHim]."
//								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					} else {
//						return "Wanting to perform oral on the rear part of [npc2.namePos] lower [npc2.legRace]'s body, [npc.name] [npc.verb(move)] around and [npc.verb(kneel)] down behind [npc2.herHim]."
//								+ " Running [npc.a_hand] up and over [npc2.her] [npc2.ass+], [npc.she] [npc.moansVerb],"
//								+ " [npc.speech(Oh yes, time to put my mouth to use!)]";
//					}
//				}
//			}
//		}
//		@Override
//		public void applyEffects() {
//			setNewSexManager(generatePerformingOralData(Main.sex.getCharacterTargetedForSexAction(this)), false);
//		}
//		@Override
//		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
//			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
//			if(characterPerformingActionFetishes) {
//				fetishes.add(Fetish.FETISH_ORAL_GIVING);
//			} else {
//				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
//			}
//			return new ArrayList<>(fetishes);
//		}
//	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterPerformingAction(), SexSlotStanding.PERFORMING_ORAL_BEHIND);
		}
		@Override
		public String getActionTitle() {
			return "Встать позади [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "Сменить позицию так, чтобы оказаться позади [npc2.name] и иметь возможность отсосать у [npc2.her] [npc2.asshole].";
			} else {
				return "Сменить позицию так, чтобы оказаться позади [npc2.name] и иметь возможность отсосать у [npc2.her] [npc2.asshole]"+(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()?" и [npc2.pussy]":"")+".";
			}
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
					sb.append("Желая заняться анилингусом с [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.sheIs] стояла позади [npc2.herHim]. Обхватив [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],");
				} else {
					sb.append("Желая заняться анилинггусом с [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] и [npc.verb(drop)] на колени перед [npc.her] позади [npc2.herHim]. Схватив [npc2.her] [npc2.hips+], [npc.she] [npc.verb(plead)],");
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction())) {
					sb.append("Желая заняться оралом с задней части тела [npc2.namePos], находящегося ниже [npc2.legRace], [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.sheIs] стоял позади [npc2.herHim]. Проводя [npc.a_hand] вверх и через [npc2.her] [npc2.ass+], [npc.she] [npc.verb(plead)],");
				} else {
					sb.append("Желая отсосать с задней части нижней [npc2.legRace] тела [npc2.namePos], [npc.name] [npc.verb(reposition)] [npc.herself] и [npc.verb(drop)] вниз на колени [npc.her] позади [npc2.herHim]. Пробираясь [npc.a_hand] вверх и через [npc2.her] [npc2.ass+], [npc.she] [npc.verb(plead)],");
				}
			}
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
				sb.append(" [npc.speech(А теперь пора попробовать твою задницу на вкус!)]");
			} else {
				sb.append(" [npc.speech(Пожалуйста, дай мне применить мой рот!)]");
			}
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL_BEHIND));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL_BEHIND);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	
	public static final SexAction POSITION_PERFORMING_ORAL_MOVE_IN_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPosition()==SexPosition.STANDING
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.STANDING_DOMINANT_TWO
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL
					&& (Main.sex.getCharacterInPosition(SexSlotStanding.STANDING_DOMINANT_TWO)!=null
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotStanding.PERFORMING_ORAL_TWO)
					
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isSlotAvailable(Main.sex.getCharacterPerformingAction(), SexSlotStanding.PERFORMING_ORAL);
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "Переместиться вперед";
			} else {
				return "Переместиться под [npc2.herHim]";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				return "Переместиться перед [npc2.name] так, чтобы ты мог отсосать [npc2.her] гениталии.";
			} else {
				if(Main.sex.getCharacterTargetedForSexAction(this).hasPenis()) {
					return "Опуститься на колени и подползти вперед под дикое [npc2.legRaceFeral] тело [npc2.namePos], чтобы ты мог отсосать [npc2.her] [npc2.cock]"
								+(Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?" и [npc2.crotchBoobs]":"")+".";
				} else {
					return "Опуститься на колени и подползти вперед под дикое [npc2.legRaceFeral] тело [npc2.namePos]"
								+(Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()?" чтобы ты мог отсосать [npc2.her] [npc2.crotchBoobs]":"")+".";
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) { // Biped body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
					return "Желая отсосать у [npc2.name], [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.sheIs] стоял(а) перед [npc2.herHim]. Заглядывая в [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, дай мне воспользоваться моим ртом!)]";
				} else {
					return "Желая сделать [npc2.name] минет, [npc.name] [npc.verb(reposition)] [npc.herself] и [npc.verb(drop)] опускается на [npc.her] колени перед [npc2.herHim]. Взирая в [npc2.her] [npc2.eyes+], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, позволь мне использовать мой ротик!)]";
				}
				
			} else { // Taur body:
				if(SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction())) {
					return "Желая заняться оралом под нижней частью тела [npc2.legRace] [npc2.namePos], [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.sheIs] стоял(ла) под [npc2.herHim]. Поместив [npc.a_hand] на одну из задних [npc2.legs] [npc2.her], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, дай мне применить мой рот к делу!)]";
				} else {
					return "Желая заняться оралом под нижней частью тела [npc2.legRace] [npc2.namePos], [npc.name] [npc.verb(reposition)] [npc.herself] и [npc.verb(drop)] вниз на колени [npc.her] под [npc2.herHim]. Положив [npc.a_hand] на одну из задних [npc2.legs] [npc2.her], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, позволь мне применить мой рот!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL)!=null) {
				Main.sex.swapSexPositionSlots(target, Main.sex.getCharacterInPosition(SexSlotStanding.PERFORMING_ORAL));
			}

			Map<GameCharacter, SexSlot> dominants = new HashMap<>(Main.sex.getDominantParticipants(true));
			Map<GameCharacter, SexSlot> submissives = new HashMap<>(Main.sex.getSubmissiveParticipants(true));
			
			if(Main.sex.isDom(target)) {
				dominants.put(target, SexSlotStanding.PERFORMING_ORAL);
			} else {
				submissives.put(target, SexSlotStanding.PERFORMING_ORAL);
			}

			Main.sex.setSexManager(new SexManagerDefault(
					SexPosition.STANDING,
					dominants,
					submissives){
			});
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				fetishes.add(Fetish.FETISH_ORAL_GIVING);
			} else {
				fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
			}
			return new ArrayList<>(fetishes);
		}
	};
	

	
	
	//--------------- AGAINST WALL ---------------//

	public static final SexAction POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Лицом к [pc.wall]";
		}
		@Override
		public String getActionDescription() {
			return "Прижать [npc2.name] к ближайшему [pc.wall].";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] спиной к ближайшей [pc.wall]. Втираясь [npc.her] телом в [npc2.her] со спины, [npc.she] [npc.moansVerb] в [npc2.her] [npc2.ear], [npc.speech(Хорошая [npc2.girl]! А теперь не дергайся, пока я тебя трахаю!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_FACE_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Лицом к [pc.wall] (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться занять положение так, чтобы оказаться лицом к ближайшей [pc.wall].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Прежде чем [npc2.name] успевает среагировать, ты быстро прижимаешься к ближайшей [pc.wall]. Упершись руками в твердую поверхность перед собой, ты выпячиваешь свою [npc.ass+], покачивая ею перед [npc2.name], пытаясь подтолкнуть [npc2.herHim] к тому, чтобы она трахала тебя именно так.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Спиной к [pc.wall]";
		}
		@Override
		public String getActionDescription() {
			return "Прижать [npc2.name] спиной к ближайшей [pc.wall].";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] спиной к ближайшей [pc.wall]. Втираясь [npc.her] телом в [npc2.hers], [npc.she] [npc.moansVerb] в [npc2.her] [npc2.ear], [npc.speech(Хорошая [npc2.girl]! А теперь не дергайся, пока я тебя трахаю!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_BACK_TO_WALL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.AGAINST_WALL,
				Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL),
				Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Спиной к [pc.wall] (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться занять положение так, чтобы твоя спина была прижата к ближайшей [pc.wall].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Прежде чем [npc2.name] успевает среагировать, ты быстро прижимаешься к ближайшей [pc.wall]. Откинувшись на твердую поверхность позади себя, ты бросаешь на [npc2.name] самый соблазнительный взгляд, пытаясь подтолкнуть [npc2.herHim] к тому, чтобы она трахала тебя именно так.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	
	
	
	
	//--------------- LYING DOWN ---------------//
	
	public static final SexAction POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Миссионерская";
		}
		@Override
		public String getActionDescription() {
			return "Повалить [npc2.name] на [npc2.her] спину и опуститься на колени между [npc2.her] [npc2.legs], готовясь к сексу в миссионерской позе.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return "Осторожно двигаясь, чтобы не разбудить [npc2.name], [npc.name]"
						+ (Main.sex.getCharacterTargetedForSexAction(this).hasLegs()
								?" [npc.verb(position)] [npc.herself] между [npc2.her] [npc2.legs]."
								:" [npc.verb(kneel)] вниз, к паху [npc2.her].")
						+ " С похотливой ухмылкой на [npc.her] [npc.face+], [npc.she] тихо [npc.verb(whisper)], <i>[npc.speech(Вот так, спи дальше и дай мне трахнуть тебя...)]</i>";
			}
			
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] на спину [npc2.her]. Опустившись на колени между [npc2.her] [npc2.legs], [npc.she] [npc.moansVerb], пока [npc.she] [npc.verb(look)] в [npc2.her] [npc2.eyes+], [npc.speech(Вот так, раздвигай ноги для меня...)]";
			} else {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] на спину [npc2.her]. Опустившись на колени над пахом [npc2.her], [npc.she] [npc.moansVerb], пока [npc.she] [npc.verb(look)] в [npc2.her] [npc2.eyes+], [npc.speech(Вот так, представься мне...)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Миссионерская (R)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Попытаться уложить [npc2.name] на [npc2.her] спину и раздвинуть [npc2.her] [npc2.legs], чтобы заняться сексом с [npc2.herHim] в миссионерской позе.<br/>"
							+ getRequestTooltipText();
			} else {
				return "Попытаться уложить [npc2.name] на [npc2.her] спину и подставить тебе [npc2.her] пах, чтобы заняться сексом с [npc2.herHim] в миссионерской позе.<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			return "Ты тянешься вверх, чтобы обхватить [npc2.namePos] за плечи, и, надавливая, пытаешься заставить [npc2.herHim] лечь на [npc2.her] спину.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_PRONE_BONE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Поза «коленно-локтевая на животе»";
		}
		@Override
		public String getActionDescription() {
			return "Прижать [npc2.name] лицом вниз к [npc2.her] и опуститься на колени между [npc2.her] [npc2.legs], готовый трахать [npc2.herHim] в позе на животе.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = !Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "Осторожно двигаясь, чтобы не разбудить [npc2.name], [npc.name]"
						+(rollsOver
								?" медленно [npc.verb(roll)] [npc2.herHim] на [npc2.her] живот, а затем"
								:"")
						+ (Main.sex.getCharacterTargetedForSexAction(this).hasLegs()
								?" [npc.verb(position)] [npc.herself] между [npc2.her] [npc2.legs]."
								:" [npc.verb(lift)] [npc2.her] вверх, [npc2.legs], чтобы получить доступ к [npc2.her] паху.")
						+ " С похотливой ухмылкой на [npc.her] [npc.face+], [npc.she] тихо [npc.verb(whisper)], <i>[npc.speech(Не буди...)]</i>";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] вниз так, чтобы [npc2.sheIs] лежала на [npc2.her] животе. Опустившись на колени между [npc2.her] [npc2.legs], [npc.she] [npc.moansVerb], пока [npc.she] [npc.verb(prepare)] трахать [npc2.herHim], [npc.speech(Лежи смирно и наслаждайся...)]";
			} else {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] вниз так, чтобы [npc2.sheIs] лежала на [npc2.her] животе. Подтянув [npc2.her] [npc2.legs], [npc.she] [npc.verb(gain)] доступ к [npc2.her] паху и [npc.moansVerb], [npc.speech(Лежи смирно и наслаждайся...)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_PRONE_BONE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Поза «коленно-локтевая на животе» (R)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Попытаться заставить [npc2.name] лечь на [npc2.her] живот и развести [npc2.her] [npc2.legs] так, чтобы ты мог трахать [npc2.herHim] в позе на животе.<br/>"
							+ getRequestTooltipText();
			} else {
				return "Попытаться заставить [npc2.name] лечь на [npc2.her] живот и подставить [npc2.her] пах, чтобы вы могли трахнуть [npc2.herHim] в позе «лежа на животе».<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			return "Вы тянетесь вверх, чтобы обхватить [npc2.namePos] за плечи, и, надавливая, пытаетесь заставить [npc2.herHim] лечь на [npc2.her] живот.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Миссионерская (на спине)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Ляг на спину и разведи [npc.legs], готовясь к сексу с [npc2.name] в миссионерской позе.";
			} else {
				return "Ляг на спину и подставь свой пах, готовясь к сексу с [npc2.name] в миссионерской позе.";
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] на [npc2.her] колени. Опустившись на колени перед [npc2.herHim], [npc.she] затем [npc.verb(lie)] на [npc.her] спину, раздвигая [npc.her] [npc.legs] и глядя снизу вверх в [npc2.namePos] [npc2.eyes+], пока [npc.she] [npc.moanVerb], [npc.speech(Иди и возьми меня!)]";
			} else {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] на [npc2.her] колени. Опустившись на колени перед [npc2.herHim], [npc.she] затем [npc.verb(lie)] на [npc.her] спину, подставляя [npc.her] пах и глядя снизу вверх в [npc2.namePos] [npc2.eyes+], пока [npc.she] [npc.moanVerb], [npc.speech(Иди и возьми меня!)]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_MISSIONARY_ON_BACK = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Миссионерская (на спине) (R)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Ляг на спину и разведи [npc.legs], пытаясь побудить [npc2.name] заняться с тобой сексом в миссионерской позе.<br/>"
							+ getRequestTooltipText();
			} else {
				return "Ляг на спину и выставь свое паховое отверстие, чтобы попытаться побудить [npc2.name] заняться с тобой сексом в миссионерской позе.<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "[npc.Name] [npc.verb(lie)] на [npc.her] спину перед [npc2.name], издавая легкий [npc.moan], когда [npc.she] [npc.verb(spread)] [npc.her] [npc.legs], пытаясь побудить [npc2.name] заняться сексом с [npc.herHim] в миссионерской позе.";
			} else {
				return "[npc.Name] [npc.verb(lie)] на [npc.her] спину перед [npc2.name], издавая легкий [npc.moan], когда [npc.she] [npc.verb(present)] пах [npc.her], пытаясь побудить [npc2.name] заняться сексом с [npc.herHim] в миссионерской позе.";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_PRONE_BONE_ON_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Поза «лежа на животе» (Prone bone)";
		}
		@Override
		public String getActionDescription() {
			return "Ляг на живот и выставь свою [npc.ass+] и пах перед [npc2.name], чтобы [npc2.she] мог трахать тебя в позе лежа на животе.";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] в положение на коленях. Затем [npc.She] [npc.verb(lie)] на [npc.her] живот перед [npc2.name], и когда [npc.she] [npc.verb(present)] [npc.her] [npc.ass+] и пах, [npc.she] [npc.moanVerb], [npc.speech(Иди и возьми меня!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_PRONE_BONE_ON_FRONT = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FRONT),
				Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY));
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Поза «лежа на животе» (Prone bone) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Ляг на живот и выставь свою [npc.ass+] и пах, чтобы попытаться побудить [npc2.name] заняться с тобой сексом в позе лежа на животе.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "[npc.Name] [npc.verb(lie)] down on [npc.her] front before [npc2.name], letting out a little [npc.moan] as [npc.she] [npc.verb(present)] [npc.her] [npc.ass+] and groin to try and encourage [npc2.name] to have sex with [npc.herHim] in the prone bone position.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Шестьдесят девять (сверху)";
		}
		@Override
		public String getActionDescription() {
			return "Прижать [npc2.name] к [npc2.her] на спину и сесть верхом на лицо [npc2.her] в позиции шестьдесят девять.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "Осторожно двигаясь, чтобы не разбудить [npc2.name], [npc.name]"
						+(rollsOver
								?" медленно [npc.verb(roll)] [npc2.herHim] на спину [npc2.her], а затем"
								:"")
						+ " [npc.verb(lower)] [npc.herself] через [npc2.herHim], чтобы принять позу шестьдесят девять. Жадно облизав [npc.her] [npc.lips+], [npc.she] мягко [npc.verb(whisper)], <i>[npc.speech(Позволь мне подарить тебе сладкие сны...)]</i>";
			}
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back. Quickly lowering [npc.herself] down onto all fours over the top of [npc2.herHim], [npc.she] [npc.verb(drop)] [npc.her] crotch down over [npc2.her] face as [npc.she] similarly [npc.verb(position)] [npc.her] own head over [npc2.her] groin. Looking back beneath [npc.herHim], [npc.name] [npc.moansVerb], [npc.speech(Good [npc2.girl]! Now let's have some fun!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Шестьдесят девять (сверху) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться прижать [npc2.name] к [npc2.her] на спину, чтобы заняться сексом в позиции шестьдесят девять с [npc2.herHim].<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Протянув руки к плечам [npc2.namePos], вы принимаете самый соблазнительный вид, какой только можете, пытаясь прижать [npc2.herHim] к себе и умоляя: [npc.speech(Я хочу заняться с тобой шестьдесят девять... Пожалуйста!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
						|| !Main.sex.isDom(Main.sex.getCharacterPerformingAction())
						|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_SUBMISSIVE).isPositive());
		}
		@Override
		public String getActionTitle() {
			return "Шестьдесят девять (снизу)";
		}
		@Override
		public String getActionDescription() {
			return "Лягте на спину и позвольте [npc2.name] расположиться у вас на лице в позе шестьдесят девять.";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(tug)] [npc2.herHim] на [npc.herHim], пока [npc.she] [npc.verb(lie)] на [npc.her] спине. Потянув [npc2.her] [npc2.hips] назад так, чтобы [npc2.sheIs] оказалась в позе «обратной четвереньки» поверх [npc.herHim], [npc.name] [npc.verb(look)] на [npc2.her] промежность, зависшую над [npc.her] лицом, и [npc.moansVerb], [npc.speech(Хороший [npc2.girl]! Пора немного развлечься ртом!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_SIXTY_NINE_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.SIXTY_NINE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Шестьдесят девять (снизу) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Лягте на спину и попросите [npc2.name] заняться с вами шестьдесят девять.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Опускание на спину с самым соблазнительным видом, какой только можете изобразить, умоляя: [npc.speech(Я хочу заняться с тобой шестьдесят девять... Пожалуйста!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_COW_GIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Наездница (верхом)";
		}
		@Override
		public String getActionDescription() {
			return "Прижмите [npc2.name] к спине [npc2.her] и расположитесь в паху у [npc2.her] в позе наездницы.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "Осторожно двигаясь, чтобы не разбудить [npc2.name], [npc.name]"
						+(rollsOver
								?" медленно [npc.verb(roll)] [npc2.herHim] на спину [npc2.her], а затем"
								:"")
						+ " [npc.verb(straddle)] [npc2.herHim] в позе наездницы. Как только [npc.sheHas] устроит [npc.herself], [npc.she] [npc.verb(grin)] на [npc2.name] и мягко [npc.verb(whisper)], <i>[npc.speech(Спи, пока я скачу на тебе...)]</i>";
			}
			return "Taking hold of [npc2.namePos] shoulders, [npc.name] [npc.verb(push)] [npc2.herHim] down onto [npc2.her] back. [npc.She] then lowers [npc.herself] down on top of [npc2.herHim], bringing [npc.her] crotch down to bump against [npc2.hers] as [npc.she] [npc.verb(straddle)] [npc2.herHim] in the cowgirl position. Once [npc.sheHas] made [npc.herself] comfortable, [npc.she] [npc.verb(grin)] down at [npc2.name] and [npc.moansVerb], [npc.speech(Good [npc2.girl]! It's time to give you a ride!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_RIDING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Наездница (верхом) (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться прижать [npc2.name] к [npc2.her] спине, чтобы сесть на [npc2.herHim] верхом.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Приподняв свой [npc.arms], ты хватаешь [npc2.namePos] за плечи и, слегка надавливая, пытаешься заставить [npc2.herHim] опуститься на [npc2.her] спину, умоляя: [npc.speech(Дай мне оседлать тебя... Пожалуйста!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};

	public static final SexAction POSITION_COW_GIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Наездница (снизу)";
		}
		@Override
		public String getActionDescription() {
			return "Лечь на спину и заставить [npc2.name] сесть тебе на пах в позе наездницы.";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(pull)] [npc2.herHim] вниз, прижимая [npc.herHim], пока [npc.she] [npc.verb(lie)] на [npc.her] назад. Крепко сжав [npc2.namePos] [npc2.hips], [npc.she] [npc.verb(push)] [npc2.herHim] назад так, чтобы [npc2.sheIs] сидела верхом на [npc.herHim] в позе наездницы. Как только [npc.sheHas] устроила [npc.herself] поудобнее, [npc.she] [npc.verb(grin)] на [npc2.name] и [npc.moansVerb], [npc.speech(Хорошая [npc2.girl]! Пора тебе прокатиться!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};

	public static final SexAction REQUEST_POSITION_COWGIRL_BOTTOM = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Наездница (снизу) (П)";
		}
		@Override
		public String getActionDescription() {
			return "Ляг на спину и попроси [npc2.name] сесть на тебя верхом в позе наездницы.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Опускаясь и быстро ложась на спину, ты принимаешь самый соблазнительный вид, какой только можешь изобразить, и умоляешь: [npc.speech(Приди и оседлай меня... Пожалуйста!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Сесть на лицо";
		}
		@Override
		public String getActionDescription() {
			return "Прижать [npc2.name] к спине [npc2.her] и сесть на [npc2.her] лицо.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				boolean rollsOver = Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT);
				return "Осторожно двигаясь, чтобы не разбудить [npc2.name], [npc.name]"
						+(rollsOver
								?" медленно [npc.verb(roll)] [npc2.herHim] на спину [npc2.her], а затем"
								:"")
						+ " [npc.verb(lower)] [npc.herself] на лицо [npc2.her]. Затем [npc.She] постепенно [npc.verb(allow)] [npc.her], чтобы [npc.legs] поддалась, и крепко [npc.verb(plant)] [npc.her] пах к [npc2.namePos] рту. С широкой ухмылкой на лице, [npc.her] [npc.face+], [npc.she] тихо шепчет, <i>[npc.speech(Ты такой соня... А теперь позволь мне оседлать твое лицо...)]</i>";
			}
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] на [npc2.her] спину. Затем [npc.She] [npc.verb(lower)] [npc.herself] поверх [npc2.herHim] так, что [npc.her] промежность зависает прямо над [npc2.her] [npc2.face]. Как только [npc.sheHas] устроился/устроилась [npc.herself] поудобнее, [npc.she] [npc.verb(allow)] [npc.her] [npc.legs], чтобы освободить место, плотно прижимая [npc.her] пах к [npc2.namePos] рту.";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_SITTING_ON_FACE = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Сесть на лицо (П)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться прижать [npc2.name] к [npc2.her] на спину, чтобы можно было сесть на [npc2.her] лицо.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Потянувшись вверх, чтобы схватить [npc2.namePos] за плечи, вы пытаетесь прижать [npc2.herHim] к [npc2.her] на спину, умоляя: [npc.speech(Пожалуйста, дай мне сесть тебе на лицо!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Фейсситтинг";
		}
		@Override
		public String getActionDescription() {
			return "Лечь на спину и заставить [npc2.name] сесть вам на лицо.";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(pull)] [npc2.herHim] вниз с [npc.herHim], пока [npc.she] [npc.verb(lie)] на [npc.her] назад. Обхватив руками [npc2.her] бедра, [npc.she] затем [npc.verb(pull)] [npc2.herHim] сверху на [npc.herHim] так, что [npc2.her] пах зависает прямо над [npc.her] [npc.face]. В этот момент [npc2.namePos] [npc2.legs] внезапно поддаются, заставляя [npc2.herHim] плотно прижать [npc2.her] пах к [npc.namePos] рту.";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction REQUEST_POSITION_FACESITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Фейсситтинг (П)";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться лечь на спину так, чтобы [npc2.name] мог/могла сесть вам на лицо.<br/>"
						+ getRequestTooltipText();
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] [npc2.hands], вы ложитесь на спину, умоляя: [npc.speech(Пожалуйста, сядь мне на лицо!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};

	
	public static final SexAction POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS),
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasPenis()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.NORMAL;
			}
		}
		@Override
		public String getActionTitle() {
			return "Прессинг при спаривании";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Прижать [npc2.name] спиной к [npc2.her], раздвинуть [npc2.her] [npc2.legs] и подтянуть их к [npc2.her] голове, а затем навалиться сверху на [npc2.herHim] в позе «mating press».";
			} else {
				return "Прижать [npc2.name] спиной к [npc2.her], а затем навалиться сверху на [npc2.herHim] в позе «mating press».";
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasLegs()) {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] на землю, заставляя [npc2.herHim] лечь на [npc2.her] спину. Схватив [npc2.her] [npc2.legs], [npc.name] [npc.verb(push)] их в стороны и вверх к [npc2.her] голове, вы ложитесь сверху на [npc2.herHim], прижимая [npc.her] пах к [npc2.hers]. Пригвоздив [npc2.namePos] запястья к полу по обе стороны от [npc2.her] головы, [npc.name] [npc.moansVerb], [npc.speech(Пора)"
						+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this),
								"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN хорошенько тебя оттрахать!#ELSE осеменить тебя!#ENDIF")
						+ ")]";
			} else {
				return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] на землю, заставляя [npc2.herHim] лечь на [npc2.her] спину. Быстро навалившись сверху на [npc2.herHim], [npc.she] [npc.verb(bump)] [npc.her] пах к [npc2.hers], прежде чем пригвоздить [npc2.namePos] запястья к полу по обе стороны от [npc2.her] головы и [npc.moaning], [npc.speech(Пора"
						+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this),
								"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN хорошенько тебя оттрахать!#ELSE осеменить тебя!#ENDIF")
						+ ")]";
			}
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction REQUEST_POSITION_MATING_PRESS = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.LYING_DOWN,
				Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN),
				Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Поза коитуса (R)";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Ложишься на спину, разводишь свои [npc.legs] и тянешь их вверх к голове, подставляясь под секс в позе «mating press».<br/>"
							+ getRequestTooltipText();
			} else {
				return "Ложишься на спину и подставляешь свою промежность [npc2.name], чтобы быть готовой к тому, чтобы тебя оттрахали в позе «mating press».<br/>"
							+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().hasLegs()) {
				return "Прежде чем [npc2.name] успевает среагировать, ты быстро падаешь на спину, разводишь свои [npc.legs] и тянешь их вверх к голове. Подставляя промежность [npc2.name], ты [npc.moansVerb], [npc.speech(Прижми меня и"
						+ UtilText.parse(Main.sex.getCharacterPerformingAction(), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN трахай меня!#ELSE осеменяй меня!#ENDIF")
						+ ")]";
			} else {
				return "Прежде чем [npc2.name] успевает среагировать, ты быстро падаешь на спину, подставляя промежность [npc2.name] и [npc.moaning], [npc.speech(Прижми меня и"
						+ UtilText.parse(Main.sex.getCharacterPerformingAction(), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN трахай меня!#ELSE осеменяй меня!#ENDIF")
						+ ")]";
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	
	
	//--------------- ALL FOURS ---------------//
	
	private static List<SexSlot> generatePerformerAllFoursData(GameCharacter receiver) {
		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		doms.remove(receiver);
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		subs.remove(receiver);

		boolean doubleReceiving = false;
		if(Main.sex.isDom(receiver)) {
			doubleReceiving = doms.size()>=1;
		} else {
			doubleReceiving = subs.size()>=1;
		}
		List<SexSlot> performerSlots = new ArrayList<>();
		performerSlots.add(SexSlotAllFours.BEHIND);
		if(doubleReceiving) {
			performerSlots.add(SexSlotAllFours.BEHIND_TWO);
		}
		SexSlot[] slots = new SexSlot[] {
				SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
				SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.IN_FRONT_TWO};
		for(SexSlot slot : slots) {
			if(!performerSlots.contains(slot)) {
				performerSlots.add(slot);
			}
		}
		
		return performerSlots;
	}
	
	private static PositioningData generateReceivingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPosition.ALL_FOURS,
				Util.newArrayListOfValues(
						SexSlotAllFours.ALL_FOURS,
						SexSlotAllFours.ALL_FOURS_TWO),
				generatePerformerAllFoursData(receiver));
	}

	private static PositioningData generatePerformingAllFoursData(GameCharacter receiver) {
		return new PositioningData(
				SexPosition.ALL_FOURS,
				generatePerformerAllFoursData(receiver),
				Util.newArrayListOfValues(
						SexSlotAllFours.ALL_FOURS,
						SexSlotAllFours.ALL_FOURS_TWO));
	}
	
	private static boolean isAllFoursAvailable(GameCharacter gettingFucked) {
		switch(gettingFucked.getLegConfiguration()) {
			case ARACHNID:
			case TAIL:
			case TAIL_LONG:
				return false;
			case BIPEDAL:
			case CEPHALOPOD:
			case QUADRUPEDAL:
			case AVIAN:
			case WINGED_BIPED:
				return true;
		}
		return true;
	}
	
	public static final SexAction POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), false)
					&& isAllFoursAvailable(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Оттрахана в позе догги-стайл";
				} else {
					return "Трахаться";
				}
			} else {
				return "Сесть сверху";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Встаешь на четвереньки, чтобы [npc2.name] могла оттрахать тебя в позе догги-стайл.";
				} else {
					return "Встаешь на четвереньки, чтобы [npc2.name] могла запрыгнуть на тебя и начать спаривание.";
				}
			} else {
				return "Представь свои задницу перед [npc2.name] и позволь [npc2.herHim] оседлать твое звериное тело.";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Желая, чтобы [npc2.name] трахала её в позе догги-стайл, [npc.name] [npc.verb(drop)] на четвереньки перед [npc2.herHim]. Подползая назад и прижимая [npc.her] [npc.ass+] к паху [npc2.namePos], [npc.she] [npc.verb(look)] через плечо [npc.her] и [npc.moansVerb], [npc.speech(Давай же, трахай меня как животную!)]";
				} else {
					return "Желая, чтобы [npc2.name] оседлал [npc.herHim] и начал неистово [npc.herHim] словно зверь, [npc.name] [npc.verb(drop)] на четвереньки перед [npc2.herHim]. Подползая назад так, чтобы [npc.sheIs] оказался под диким телом [npc2.namePos] [npc2.legRace], [npc.she] [npc.verb(raise)] [npc.her] [npc.hips+] и [npc.moansVerb], [npc.speech(Давай же, оседлай меня уже!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Желая, чтобы [npc2.name] трахала [npc.herHim], [npc.name] [npc.verb(turn)] вокруг и [npc.verb(present)] задницу дикого тела [npc.her] [npc.legRace] к [npc2.herHim]. Отступая назад и прижимая [npc.her] [npc.ass+] к паху [npc2.namePos], [npc.she] [npc.verb(look)] через [npc.her] плечо и [npc.moansVerb], [npc.speech(Давай же, трахай меня как животное!)]";
				} else {
					return "Желая, чтобы [npc2.name] оседлал [npc.herHim] и начал жестко трахать [npc.herHim], [npc.name] [npc.verb(present)] заднюю часть тела дикого [npc.legRace] [npc.her] к [npc2.herHim]. Отступая назад и прижимая [npc.her] [npc.ass+] к [npc2.name], [npc.she] [npc.verb(force)] [npc2.herHim], чтобы тот встал на дыбы и оседлал [npc.herHim]. Оглядываясь через [npc.her] плечо, [npc.she] [npc.moansVerb], [npc.speech(Вот так, трахай меня как животное!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				if(characterPerformingAction.hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				fetishes.add(Fetish.FETISH_SUBMISSIVE);
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
				}
			} else {
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
				fetishes.add(Fetish.FETISH_DOMINANT);
				if(characterPerformingAction.hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
				}
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isBeingPenetrated()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_GETTING_FUCKED = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.ALL_FOURS
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.ALL_FOURS_TWO
					&& isAllFoursAvailable(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Трахнули в позе собачки (R)";
				} else {
					return "Получить трах (R)";
				}
			} else {
				return "Быть оседланной (R)";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Опуститься на четвереньки и попросить [npc2.name] трахнуть тебя в позе собачки.<br/>"
						+ getRequestTooltipText();
				} else {
					return "Опуститься на четвереньки и подставить себя [npc2.name] в надежде, что [npc2.she] захочет оседлать и трахнуть тебя.<br/>"
						+ getRequestTooltipText();
				}
			} else {
				return "Подставить свою задницу [npc2.name] в надежде, что [npc2.she] захочет оседлать твое звериное тело и трахнуть тебя.<br/>"
						+ getRequestTooltipText();
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Желая, чтобы [npc2.name] трахнул ее в позе собачки, [npc.name] [npc.verb(drop)] на четвереньки перед [npc2.herHim]. Подползая назад и прижимая [npc.her] [npc.ass+] к паху [npc2.namePos], [npc.she] [npc.verb(look)] через [npc.her] плечо и [npc.verb(plead)], [npc.speech(Пожалуйста, трахай меня как животное!)]";
				} else {
					return "Желая, чтобы [npc2.name] оседлал [npc.herHim] и начал неистово трахать [npc.herHim], словно зверь, [npc.name] [npc.verb(drop)] на четвереньки перед [npc2.herHim]. Подползая назад так, чтобы [npc.sheIs] оказался под диким [npc2.legRace] телом [npc2.namePos], [npc.she] [npc.verb(raise)] [npc.her] [npc.hips+] и [npc.verb(plead)], [npc.speech(Пожалуйста, трахай меня как животное!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Желая, чтобы [npc2.name] трахал [npc.herHim], [npc.name] [npc.verb(turn)] вокруг и [npc.verb(present)] заднюю часть дикого тела [npc.legRace] [npc.her] к [npc2.herHim]. Отступая назад и прижимая [npc.her] [npc.ass+] к паху [npc2.namePos], [npc.she] [npc.verb(look)] обратно через [npc.her] плечо и [npc.verb(plead)], [npc.speech(Пожалуйста, трахай меня как животное!)]";
				} else {
					return "Желая, чтобы [npc2.name] оседлал [npc.herHim] и начал неистово трахать [npc.herHim], словно зверь, [npc.name] [npc.verb(present)] заднюю часть дикого тела [npc.legRace] [npc.her] к [npc2.herHim]. Отступая назад и прижимая [npc.her] [npc.ass+] к [npc2.name], [npc.she] [npc.verb(look)] обратно через [npc.her] плечо и [npc.verb(plead)], [npc.speech(Пожалуйста, трахай меня как животное!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generateReceivingAllFoursData(Main.sex.getCharacterPerformingAction()));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ALL_FOURS_GETTING_FUCKED.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};
	
	public static final SexAction POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), false)
					&& isAllFoursAvailable(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Поза собачкой [npc2.herHim]";
				} else {
					return "Трахнуть [npc2.herHim]";
				}
			} else {
				return "Оседлать [npc2.herHim]";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Заставить [npc2.name] встать на четвереньки, чтобы ты мог трахать [npc2.herHim] в позе собачкой.";
				} else {
					return "Заставить [npc2.name] подставить [npc2.herself] тебе, чтобы ты мог трахать [npc2.herHim].";
				}
			} else {
				return "[npc2.name] подставляет тебе свои [npc2.her], чтобы ты мог оседлать [npc2.herHim].";
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Желая трахнуть [npc2.name] в позе догги-стайл, [npc.name] [npc.verb(push)] [npc2.herHim] на четвереньки перед [npc.herHim]. Опустившись на колени позади [npc2.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips+] и [npc.verb(pull)] [npc2.her] [npc2.ass+] задницей к [npc.her] паху, [npc.moaning], [npc.speech(Пора трахнуть тебя как животное!)]";
				} else {
					return "Желая оседлать [npc2.name] и начать спариваться с [npc2.herHim] словно животное, [npc.name] [npc.verb(push)] [npc2.herHim] на четвереньки перед [npc.herHim]. Перешагнув через [npc2.herHim] так, чтобы [npc2.sheIs] оказался под диким [npc.legRace] телом [npc.her], [npc.she] [npc.verb(bend)] [npc.her] [npc.legs] немного и [npc.moansVerb], [npc.speech(О да, теперь пора трахнуть тебя как животное!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Желая трахнуть [npc2.name], [npc.name] [npc.verb(move)] вокруг дикого [npc2.legRace] тела [npc2.her]. Когда задница [npc2.her] теперь представлена [npc.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips] и [npc.verb(push)] [npc.her] пахом к [npc2.her] [npc2.ass+], [npc.moaning], [npc.speech(Пора трахнуть тебя как животное!)]";
				} else {
					return "Желая оседлать [npc2.name] и начать трахать [npc2.herHim] словно дикий зверь, [npc.name] [npc.verb(move)] вокруг дикого тела [npc2.legRace] со спины [npc2.her]. Теперь, когда [npc2.her] подставила зад [npc.herHim], [npc.she] [npc.verb(jolt)] вперед и [npc.verb(rear)] вверх, одним стремительным движением оседлав [npc2.herHim]. [npc.speech(О да, сейчас я буду трахать тебя как животное!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			setNewSexManager(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), false);
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			Set<AbstractFetish> fetishes = new HashSet<>(super.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes));
			if(characterPerformingActionFetishes) {
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_GIVING);
				fetishes.add(Fetish.FETISH_DOMINANT);
				if(characterPerformingAction.hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
				}
			} else {
				if(characterPerformingAction.hasVagina()) {
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				fetishes.add(Fetish.FETISH_SUBMISSIVE);
				if(Main.sex.getTargetedPartner(characterPerformingAction).hasPenis()) {
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
				}
			}
			return new ArrayList<>(fetishes);
		}
		@Override
		public SexActionPriority getPriority() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this))!=null
					&& ((NPC)Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).isPenetrating()) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
	};
	
	public static final SexAction REQUEST_POSITION_ALL_FOURS_FUCKING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)), true)
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.BEHIND
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())!=SexSlotAllFours.BEHIND_TWO
					&& isAllFoursAvailable(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Догги-стайл [npc2.herHim] (R)";
				} else {
					return "Трахать [npc2.herHim] (R)";
				}
			} else {
				return "Оседлать [npc2.herHim] (R)";
			}
		}
		@Override
		public String getActionDescription() {
			if(!Main.sex.getCharacterPerformingAction().isTaur()) {
				return "Попытаться поставить [npc2.name] на четвереньки, чтобы можно было трахать [npc2.herHim] в позе догги-стайл.<br/>"
						+ getRequestTooltipText();
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
					return "Попытаться поставить [npc2.name] на четвереньки, чтобы можно было оседлать [npc2.herHim].<br/>"
						+ getRequestTooltipText();
				} else {
					return "Попытаться заставить [npc2.name] подставить [npc2.her] задницу, чтобы можно было оседлать [npc2.herHim].<br/>"
						+ getRequestTooltipText();
				}
			}
		}
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isTaur()) {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Желая трахать [npc2.name] в позе догги-стайл, [npc.name] [npc.verb(try)], чтобы поставить [npc2.herHim] на четвереньки перед собой, прежде чем [npc.herHim]. Схватив [npc2.her] за плечи и надавив сверху, [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, встань на четвереньки и дай мне трахать тебя как животное!)]";
				} else {
					return "Желая оседлать [npc2.name] и начать трахать [npc2.herHim] словно дикий зверь, [npc.name] [npc.verb(try)], чтобы поставить [npc2.herHim] на четвереньки перед собой, прежде чем [npc.herHim]. Схватив [npc2.her] за плечи и надавив сверху, [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, встань на четвереньки и дай мне трахать тебя как животное!)]";
				}
				
			} else {
				if(!Main.sex.getCharacterPerformingAction().isTaur()) {
					return "Желая трахнуть [npc2.name], [npc.name] [npc.verb(move)] вокруг дикого тела [npc2.her] [npc2.legRace]. Когда [npc2.her] задница теперь выставлена перед [npc.herHim], [npc.she] [npc.verb(grip)] [npc2.her] [npc2.hips] и [npc.verb(plead)], [npc.speech(Пожалуйста, позволь мне трахнуть тебя как животное!)]";
				} else {
					return "Желая оседлать [npc2.name] и начать спариваться с [npc2.herHim] словно животное, [npc.name] [npc.verb(move)] вокруг дикого тела [npc2.her] [npc2.legRace]. Когда [npc2.her] задница теперь выставлена перед [npc.herHim], [npc.she] [npc.verb(plead)], [npc.speech(Пожалуйста, позволь мне оседлать тебя!)]";
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(generatePerformingAllFoursData(Main.sex.getCharacterTargetedForSexAction(this)));
		}
		@Override
		public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
			return POSITION_ALL_FOURS_FUCKING.getFetishesForEitherPartner(characterPerformingAction, characterPerformingActionFetishes);
		}
	};

	
	public static final SexAction SWITCH_TO_SITTING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING),
				Util.newArrayListOfValues(
						SexSlotSitting.PERFORMING_ORAL,
						SexSlotSitting.PERFORMING_ORAL_TWO,
						SexSlotSitting.PERFORMING_ORAL_THREE));

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Перейти в сидячее положение";
		}
		@Override
		public String getActionDescription() {
			return "Сесть на ближайшую поверхность, пока [npc2.name] стоит перед тобой на коленях, готовая заняться оральным сексом.";
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] сменить позу, [npc.name] [npc.verb(get)] [npc2.name] опуститься на колени перед ближайшей возвышенностью. Сев перед [npc2.herHim], [npc.name] [npc.moansVerb], [npc.speech(Да... Вот это уже лучше...)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction SWITCH_TO_SITTING_TAUR = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.SITTING,
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING_BETWEEN_LEGS),
				Util.newArrayListOfValues(
						SexSlotSitting.SITTING,
						SexSlotSitting.PERFORMING_ORAL_TWO,
						SexSlotSitting.PERFORMING_ORAL_THREE));

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isTaur()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isTaur()
					&& checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Перейти в сидячее положение";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] сесть на ближайшую поверхность, после чего перешагнуть через [npc2.herHim] своим нижним звериным телом, готовым начать трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc.she] [npc.verb(want)] сменить позу, [npc.name] [npc.verb(get)] [npc2.name] на ближайшую возвышенность. Перешагнув через [npc.her] партнера, [npc.name] [npc.moansVerb], [npc.speech(Да... Будет чертовски весело трахать тебя вот так...)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

//	public static final SexAction SWITCH_DESK_ORAL_TO_SEX = new SexAction(
//			SexActionType.POSITIONING,
//			ArousalIncrease.ONE_MINIMUM,
//			ArousalIncrease.ONE_MINIMUM,
//			CorruptionLevel.ZERO_PURE,
//			null,
//			SexParticipantType.NORMAL) {
//		
//		private PositioningData data = new PositioningData(
//				SexPosition.OVER_DESK,
//				Util.newArrayListOfValues(
//						SexSlotDesk.BETWEEN_LEGS),
//				Util.newArrayListOfValues(
//						SexSlotDesk.OVER_DESK_ON_FRONT));
//
//		@Override
//		public boolean isBaseRequirementsMet() {
//			return checkBaseRequirements(data, false);
//		}
//		@Override
//		public String getActionTitle() {
//			return "Switch to sex";
//		}
//		@Override
//		public String getActionDescription() {
//			return "Get [npc2.name] to sit down on a nearby surface, before stepping over [npc2.herHim] with your lower animalistic body, ready to start fucking [npc2.herHim].";
//		}
//		@Override
//		public String getDescription() {
//			return "Switch test!";
//		}
//		@Override
//		public void applyEffects() {
//			GenericPositioning.setNewSexManager(data, false);
//		}
//	};
	
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
			return "Ответить на запрос о смене позы";
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
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL) {
				if(isHappy) {
					boolean standing = SexSlotStanding.PERFORMING_ORAL.isStanding(Main.sex.getCharacterPerformingAction());
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(Не думай, что это значит, будто ты здесь главная!)] [npc.name] рычит, прежде чем "+(standing?"сделать шаг вперед":"опуститься на колени")+" и прижать [npc.her] [npc.face] к твоему паху. Грубо схватив твою [pc.hips], [npc.she] задирает взгляд, глядя тебе в [pc.eyes], и скалится: [npc.speech(Просто не дергайся и радуйся, что я сама этого захотела!)]";
						default:
							return "[npc.speech(О, да, это будет весело!)] — радостно отвечает [npc.name], прежде чем "+(standing?"сделать шаг вперед":"опуститься на колени")+" и прижать [npc.her] [npc.face] к твоему паху. Схватив твою [pc.hips], [npc.she] задирает взгляд, глядя тебе в [pc.eyes] и [npc.moansVerb], [npc.speech(А теперь замри и наслаждайся!)]";
					}
					
				} else {
					return "Наотрез отказываясь делать то, что ты просишь, [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Я тебе, блять, <i>не</i> собираюсь делать минет! Не <i>смей</i> больше так пробовать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL_BEHIND) {
				if(isHappy) {
					boolean standing = SexSlotStanding.PERFORMING_ORAL_BEHIND.isStanding(Main.sex.getCharacterPerformingAction());
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(Не думай, что это значит, будто ты здесь главная!)] [npc.name] рычит, прежде чем "+(standing?"сделать шаг вперед":"опуститься на колени")+" и прижимая [npc.her] [npc.face] к твоим [pc.ass+]. Грубо схватив тебя за [pc.hips], [npc.she] рычит: [npc.speech(Просто замри и радуйся, что я сама этого захотела!)]";
						default:
							return "[npc.speech(О, да, это будет весело!)] — радостно отвечает [npc.name], прежде чем "+(standing?"сделать шаг вперед":"опуститься на колени")+" и прижимая [npc.her] [npc.face] к твоим [pc.ass+]. Схватив тебя за [pc.hips], [npc.she] [npc.moansVerb], [npc.speech(А теперь не дергайся и наслаждайся!)]";
					}
					
				} else {
					return "Наотрез отказываясь делать то, что ты просишь, [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Я тебе, блять, <i>не</i> собираюсь делать минет! Не <i>смей</i> больше так пробовать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.STANDING_DOMINANT
					&& (Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL || Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL_BEHIND)) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.speech(Не думай, что это значит, будто ты здесь главный!)] — рычит [npc.name], прежде чем грубо прижать "+(!biped?"дикое тело [npc.her] [npc.legRace]":"[npc.herself]")+" к твоим [pc.face]. Вздрагивая [npc.her] [pc.hips], [npc.she] рычит: [npc.speech(Давай уже, приступай, и радуйся, что я сама этого захотела!)]";
						default:
							return "[npc.speech(О, да, было бы весело!)] — радостно отвечает [npc.name], прежде чем прижать "+(!biped?"дикое тело [npc.her] [npc.legRace]":"[npc.herself]")+" к твоим [pc.face]. Вздрагивая [npc.her] [pc.hips], [npc.she] [npc.moansVerb], [npc.speech(Ну же, давай, приступай!)]";
					}
					
				} else {
					return "Наотрез отказываясь делать то, что ты просишь, [npc.name] тянет тебя назад в прежнюю позу, пока [npc.she] гневно отчитывает тебя: [npc.speech(Мне <i>совсем</i> не интересно, чтобы ты отсасывал мне! Не <i>смей</i> больше так делать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAllFours.ALL_FOURS) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					boolean bipedPlayer = !Main.game.getPlayer().isTaur();
					boolean standingPlayer = SexSlotAllFours.BEHIND.isStanding(Main.game.getPlayer());
					StringBuilder sb = new StringBuilder();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							sb.append("[npc.speech(Не думай, что это значит, будто ты здесь главная!)] [npc.name] рычит, прежде чем "+(!biped?"грубо толкая дикое тело [npc.her] [npc.legRace]":"опускаясь на четвереньки и толкая [npc.herself]")+" спиной к тебе. Вздрагивая, [npc.her] [pc.hips], [npc.she] рычит: [npc.speech(Просто начни уже, "+(!bipedPlayer?"и оседлай меня,":"и трахай меня,")+" давай же, и еще благодари, что я сама этого захотела!)]");
							break;
						default:
							sb.append("[npc.speech(О, да, это будет весело!)] — радостно отвечает [npc.name], прежде чем "+(biped?"жадно толкая дикое тело [npc.her] [npc.legRace]":"опускаясь на четвереньки и толкая [npc.herself]")+" спиной к тебе. Вздрагивая, [npc.her] [pc.hips], [npc.she] [npc.moansVerb], [npc.speech(Давай же, "+(!bipedPlayer?"оседлай меня":"трахай меня")+" уже!)]");
							break;
					}
					sb.append((!bipedPlayer
										?(!biped
											?"<br/>Повинуясь просьбе [npc.she], ты встаешь на задние лапы и, резко подавшись вперед, оседлываешь [npc.herHim]. [npc.She] в ответ на твой порыв издает отчаянный [npc.moan] и, позволяя тебе опираться на [npc.her] тело, чтобы удержать вес, [npc.she] готовится к тому, что ты начнешь спариваться с [npc.herHim] словно животное."
											:"<br/>Повинуясь просьбе [npc.she], ты делаешь шаг вперед и встаешь над [npc.herHim], а затем слегка опускаешься, чтобы оседлать [npc.herHim]. [npc.She] в ответ на твой порыв издает отчаянный [npc.moan] и готовится к тому, что ты начнешь спариваться с [npc.herHim] словно животное.")
										:"<br/>Повинуясь просьбе [npc.she], ты "+(standingPlayer?"шаг":"падаешь на колени и подползаешь")+" вперед так, что твой пах прижимается к [npc.her] [npc.ass+]. [npc.She] в ответ на твое прикосновение издает отчаянный [npc.moan] и готовится к тому, что ты начнешь трахать [npc.herHim] словно животное."));
					return sb.toString();
					
				} else {
					return "Наотрез отказываясь выполнять твою просьбу, [npc.name] тянет тебя назад в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Я тебе <i>не</i> позволю так меня трахать! Только <i>смей</i> еще раз попробовать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAllFours.BEHIND) {
				if(isHappy) {
					boolean biped = !Main.sex.getCharacterPerformingAction().isTaur();
					boolean bipedPlayer = !Main.game.getPlayer().isTaur();
					boolean standing = SexSlotAllFours.BEHIND.isStanding(Main.sex.getCharacterPerformingAction());
					StringBuilder sb = new StringBuilder();
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							sb.append("[npc.speech(Не думай, что это значит, будто ты здесь главная!)] [npc.name] рычит, прежде чем "
										+(!bipedPlayer
											?(!biped
												?" вскакивая и резко подаваясь вперед, оседлывая тебя своим [npc.her] диким [npc.legRace] телом."
												:(standing?"сделать шаг вперед":"опускаясь на [npc.her] колени и подползая вперед,")+" прижимая [npc.her] пах к твоему дикому [pc.legRace] телу.")
											:(!biped
												?" шагая вперед, нависая над тобой и оседлывая тебя своим диким [npc.her] [npc.legRace] телом."
												:(standing?"сделать шаг вперед":"опускаясь на [npc.her] колени и подползая вперед,")+" и прижимая [npc.her] пах к твоему [pc.ass+]."))
									+ " Дергая [npc.her] [pc.hips], [npc.she] рычит: [npc.speech(Благодари судьбу, что я захотела трахнуть тебя, как грязное животное!)]");
							break;
						default:
							sb.append("[npc.speech(О да, звучит весело!)] — радостно отвечает [npc.name], прежде чем "
										+(!bipedPlayer
											?(!biped
												?" вскакивая и резко подаваясь вперед, оседлывая тебя своим [npc.her] диким [npc.legRace] телом."
												:(standing?"сделать шаг вперед":"опуститься на [npc.her] колени, подползти вперед,")+" и прижать [npc.her] пах к твоему дикому [pc.legRace] телу.")
											:(!biped
												?" шагнуть вперед, нависая над тобой и оседлывая тебя своим диким [npc.her] [npc.legRace] телом."
												:(standing?"сделать шаг вперед":"опуститься на [npc.her] колени, подползти вперед,")+" и прижимая [npc.her] пах к твоему [pc.ass+]."))
									+ " [npc.her] [pc.hips], [npc.she] [npc.moansVerb], [npc.speech(А теперь будь хорошей [pc.girl] и наслаждайся своим трахом!)]");
							break;
					}
					return sb.toString();
					
				} else {
					return "Наотрез отказываясь делать то, что ты просишь, [npc.name] тянет тебя назад в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Я тебе <i>не</i> так трахаю! Не <i>даже</i> смей так делать снова!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.SIXTY_NINE) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Спрыгнув на четвереньки, [npc.name] опускается [npc.herself] поверх тебя, прижимая [npc.her] промежность к твоему лицу, пока [npc.she] склоняет [npc.her] голову между твоих [pc.legs]. Повернув [npc.her] голову, чтобы взглянуть на тебя, [npc.she] рычит: [npc.speech(Хорошая идея, сучка! А теперь <i>замри</i>, чтобы я могла использовать тебя как следует!)]";
						default:
							return "Спрыгнув на четвереньки, [npc.name] опускается [npc.herself] поверх тебя, прижимая [npc.her] промежность к твоему лицу, пока [npc.she] склоняет [npc.her] голову между твоих [pc.legs]. Повернув [npc.her] голову, чтобы взглянуть на тебя, [npc.she] ухмыляется: [npc.speech(Хорошая [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Потянувшись вниз, чтобы схватить тебя за [pc.arm], [npc.name] тянет тебя назад в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, блять, творишь?! Не <i>смей</i> больше так делать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.SIXTY_NINE) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Позволив тебе прижать [npc.herHim] к земле, [npc.name] тянется вверх и крепко хватает тебя за [pc.hips], после чего грубо тянет тебя вниз, так что ты оказываешься сверху на [npc.herHim] в перевернутой позе на четвереньках. Прижимая твой пах к лицу [npc.her], [npc.she] рычит: [npc.speech(Хорошая идея, сучка! А теперь используй свой рот по назначению!)]";
						default:
							return "Позволив тебе прижать [npc.herHim] к земле, [npc.name] тянется вверх и крепко хватает тебя за [pc.hips], после чего тянет тебя вниз, так что ты оказываешься сверху на [npc.herHim] в перевернутой позе на четвереньках. Прижимая твой пах к лицу [npc.her], [npc.she] [npc.moansVerb], [npc.speech(Хороший [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Откинув твои [pc.arms] от плеч [npc.her], [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, блядь, творишь?! Только <i>посмей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.COWGIRL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Запрыгнув на тебя сверху, [npc.name] опускает [npc.herself] на твою паховую область, прижимая [npc.her] к твоему лобку, пока [npc.she] усаживается на тебя верхом. Слегка наклонившись вперед, [npc.she] рычит тебе в лицо: [npc.speech(Хорошая идея, шлюшка! А теперь <i>не двигайся</i>, чтобы я мог использовать тебя как следует!)]";
						default:
							return "Перепрыгнув через тебя, [npc.name] опускает [npc.herself] на твой пах, прижимая [npc.her] к твоему, пока [npc.she] усаживается на тебя верхом. Слегка наклонившись вперед, [npc.she] ухмыляется тебе сверху, [npc.speech(Хороший [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Потянувшись вниз, чтобы схватить тебя за [pc.arm], [npc.name] тянет тебя назад в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, блять, творишь?! Не <i>смей</i> больше так делать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.COWGIRL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Позволяя тебе прижать [npc.herHim] ниже, [npc.name] тянется вверх и крепко хватает тебя за [pc.hips], а затем грубо натягивает тебя на себя, так что ты оказываешься верхом на [npc.herHim]. Глядя снизу вверх в твои [pc.eyes], [npc.she] рычит: [npc.speech(Хорошая идея, сучка! А теперь постарайся получше!)]";
						default:
							return "Позволяя тебе прижать [npc.herHim] ниже, [npc.name] тянется вверх и крепко хватает тебя за [pc.hips], а затем притягивает тебя так, что ты оказываешься верхом на [npc.herHim]. Глядя снизу вверх в твои [pc.eyes], [npc.she] [npc.moansVerb], [npc.speech(Хороший [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Откинув твои [pc.arms] от плеч [npc.her], [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, блядь, творишь?! Только <i>посмей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.FACE_SITTING) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Отшлепав твои [pc.hands] от [npc.herHim], [npc.name] хватает тебя за запястья и дергает на себя, рыча: [npc.speech(Не смей, <i>черт возьми</i> пытаться здесь командовать! Я позволю тебе сесть мне на лицо, но только потому, что <i>я</i> так хочу, понятно?!)]<br/>С этими словами [npc.she] быстро ложится на [npc.her] спину, увлекая тебя за собой вместе с [npc.herHim]. Несмотря на [npc.namePos] гневные слова, ты получил именно то, чего хотел, и счастливо прижимаешься пахом к [npc.her] лицу.";
						default:
							return "Улыбаясь тебе, [npc.name] отвечает: [npc.speech(Отличная идея! О, это будет круто!)]<br/>С этими словами [npc.she] быстро ложится на спину, [npc.her] увлекая тебя за собой с помощью [npc.herHim]. Ты издаешь радостный [pc.moan], понимая, что получаешь именно то, чего хотел, и жадно прижимаешься пахом к [npc.her] лицу.";
					}
					
				} else {
					return "Отшвыривая твои [pc.hands] от [npc.herHim], [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, совсем страх потеряла?! Только <i>посмей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.FACE_SITTING) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Перепрыгнув через тебя, [npc.name] нависает над тобой, прижимая [npc.herself] к твоему лицу, а затем впечатывает [npc.her] в твой рот, прижимая [npc.her] к губам. Грубо вжимаясь в твое лицо, [npc.she] рычит: [npc.speech(Хорошая идея, сучка! А теперь <i>не двигайся</i>, чтобы я мог использовать тебя как следует!)]";
						default:
							return "Запрыгнув на тебя сверху, [npc.name] опускается на тебя, [npc.herself] прижимая [npc.her] своей промежностью к твоему лицу, а затем впечатывает [npc.her] пахом тебе в рот. Втираясь в твое лицо, [npc.she] [npc.moans], [npc.speech(Хорошая [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Потянувшись вниз, чтобы схватить тебя за [pc.arm], [npc.name] тянет тебя назад в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, блять, творишь?! Не <i>смей</i> больше так делать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.MATING_PRESS) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "С жадной, похотливой ухмылкой на лице [npc.her], [npc.name] делает именно то, что ты говоришь, и всем весом [npc.her] наваливается на тебя. Грубо притирая [npc.her] к твоему паху, [npc.she] прижимает твои запястья к полу по обе стороны от твоей головы и рычит: [npc.speech(Грязная сучка!\", \"1\": \"#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN я тебя сейчас как следует оттрахаю!#ELSE я тебя сейчас как следует осеменю!#ENDIF\""
									+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN Я тебя сейчас как следует оттрахаю!#ELSE Я тебя сейчас как следует осеменю!#ENDIF")
									+ ")]";
						default:
							return "С жадной, похотливой улыбкой на лице [npc.her], [npc.name] делает именно то, что ты говоришь, и ложится поверх тебя. Отчаянно притирая [npc.her] к твоему паху, [npc.she] прижимает твои запястья к полу по обе стороны от твоей головы и [npc.moansVerb], [npc.speech(Хороший [pc.girl]!\""
									+ UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), "#IFnpc.isVisiblyPregnant() || !npc.hasVagina()#THEN я тебя сейчас как следует оттрахаю!#ELSE я тебя сейчас как следует осеменю!#ENDIF")
									+ ")]";
					}
					
				} else {
					return "Потянувшись вниз, чтобы схватить тебя за [pc.arm], [npc.name] тянет тебя назад в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, блять, творишь?! Не <i>смей</i> больше так делать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							if(Main.sex.getCharacterPerformingAction().hasLegs()) {
								return "К твоему огромному удовольствию, [npc.name] позволяет [npc.herself] прижаться спиной к [npc.her], но когда [npc.she] раздвигает [npc.her] [npc.legs] для тебя, [npc.she] угрожающе рычит: [npc.speech(Не слишком-то раззадоривайся, сучка! Здесь всё еще главная я!)]";
							} else {
								return "К твоему огромному удовольствию, [npc.name] позволяет [npc.herself] прижаться спиной к [npc.her], но когда [npc.she] подставляет тебе [npc.her] пах, [npc.she] угрожающе рычит: [npc.speech(Не слишком-то раззадоривайся, сучка! Здесь всё еще главная я!)]";
							}
						default:
							if(Main.sex.getCharacterPerformingAction().hasLegs()) {
								return "К твоему огромному удовольствию, [npc.name] позволяет [npc.herself] прижаться к [npc.her] спине, и пока [npc.she] разводит [npc.her] [npc.legs] для тебя, [npc.she] [npc.moansVerb], [npc.speech(Мне нравится, когда партнер проявляет инициативу! Иди и возьми меня!)]";
							} else {
								return "К твоему огромному удовольствию, [npc.name] позволяет [npc.herself] прижаться к [npc.her] спине, и пока [npc.she] подставляет тебе [npc.her] пах, [npc.she] [npc.moansVerb], [npc.speech(Мне нравится, когда партнер проявляет инициативу! Иди и возьми меня!)]";
							}
					}
				} else {
					return "Оттолкнув твои [pc.hands], [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя, [npc.speech(Ты что себе позволяешь?! Только <i>смей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "К твоему огромному удовольствию, [npc.name] опускается на колени между твоих [pc.legs], и когда [npc.she] хватает твои [npc.legs], чтобы раздвинуть их, [npc.she] рычит, [npc.speech(Вот так, сучка! Раздвигай ноги, как настоящая шлюха!)]";
						default:
							return "К твоему огромному удовольствию, [npc.name] опускается на колени между твоих [pc.legs], и когда [npc.she] берется за твои [npc.legs], чтобы помочь раздвинуть их, [npc.she] [npc.moansVerb], [npc.speech(Отличная идея! А теперь раздвигай ноги пошире!)]";
					}
				} else {
					return "Схватив одну из твоих [pc.arms], [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя, [npc.speech(Ты что себе позволяешь?! Только <i>смей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotLyingDown.LYING_DOWN_FRONT
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotLyingDown.MISSIONARY) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "К твоему огромному восторгу, [npc.name] позволяет [npc.herself] прижаться к [npc.her] спереди, но когда [npc.she] подставляет тебе [npc.her] [npc.ass+] и пах, [npc.she] угрожающе рычит: [npc.speech(Не слишком разгоняйся, сучка! Здесь всё еще главный я!)]";
						default:
							return "К твоему огромному восторгу, [npc.name] позволяет [npc.herself] прижаться к [npc.her] спереди, и когда [npc.she] подставляет тебе [npc.her] [npc.ass+] и пах, [npc.she] [npc.moansVerb]: [npc.speech(Мне нравится, когда мой партнер проявляет инициативу! Иди и возьми меня!)]";
					}
				} else {
					return "Оттолкнув твои [pc.hands], [npc.name] возвращает тебя в прежнее положение, пока [npc.she] гневно отчитывает тебя, [npc.speech(Ты что себе позволяешь?! Только <i>смей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotAgainstWall.FACE_TO_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "К твоему огромному восторгу, ты чувствуешь, как [npc.name] тянется вниз и грубо хватает тебя за бедра, и, втираясь [npc.herself] в твою спину, [npc.she] рычит тебе в ухо: [npc.speech(Обожаю трахать таких сучек, как ты, сзади! А теперь <i>не двигайся</i>, как хорошая шлюха!)]";
						default:
							return "К твоему огромному восторгу, ты чувствуешь, как [npc.name] тянется вниз, чтобы обхватить твои бедра, и когда [npc.she] наклоняется над твоим плечом, [npc.she] [npc.moans] тебе в ухо: [npc.speech(Хорошая [pc.girl]! Это будет весело!)]";
					}
				} else {
					return "Схватив тебя за плечи, [npc.name] оттягивает тебя от [pc.wall], возвращая в прежнее положение, пока [npc.she] сердито отчитывает тебя: [npc.speech(Ты что, блядь, творишь?! Только <i>смей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL
					&& Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotAgainstWall.BACK_TO_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name] ухмыляется, когда вы пытаетесь завлечь [npc.herHim], чтобы он подошел и оттрахал вас прямо у [pc.wall]. Подвинувшись ближе, чтобы плотно прижаться [npc.her] телом к вашему, [npc.she] наклоняется к вашему плечу и рычит вам в ухо: [npc.speech(Хорошая сучка! А теперь <i>не шевелись</i>, и я как следует тебя оттрахаю!)]";
						default:
							return "[npc.Name] ухмыляется, когда вы пытаетесь завлечь [npc.herHim], чтобы он подошел и оттрахал вас прямо у [pc.wall]. Подвинувшись ближе, чтобы прижать [npc.her] телом к вашему, [npc.she] наклоняется к вашему плечу и [npc.moans] вам в ухо: [npc.speech(Хорошая [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Схватив тебя за плечи, [npc.name] оттягивает тебя от [pc.wall], возвращая в прежнее положение, пока [npc.she] сердито отчитывает тебя: [npc.speech(Ты что, блядь, творишь?! Только <i>смей</i> еще раз так сделать!)]";
				}
				
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if((Main.sex.getCharacterPerformingAction() instanceof NPC)
					&& ((NPC)Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(
						Main.sex.getPositionRequest().getPosition(),
						Main.sex.getPositionRequest().getPartnerSlots().get(0),
						Main.sex.getPositionRequest().getPerformerSlots().get(0),
						Main.game.getPlayer())) {
				GenericPositioning.setNewSexManager(Main.sex.getPositionRequest(), true);
			} else {
				Main.sex.addPositioningRequestsBlocked(Main.game.getPlayer(), Main.sex.getPositionRequest().getPosition());
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
