package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class GloryHole {
	
	private static void applyChangeSlotEffects() {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();

		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		
		if(Main.sex.isDom(Main.game.getPlayer())) {
			dominants.put(Main.game.getPlayer(), Main.sex.getPositionRequest().getPartnerSlots().get(0));
			if(Main.sex.getDominantParticipants(false).size()==2) {
				doms.remove(Main.game.getPlayer());
				dominants.put(doms.get(0), Main.sex.getPositionRequest().getPartnerSlots().get(1));
			}
			submissives.put(subs.get(0), Main.sex.getPositionRequest().getPerformerSlots().get(0));
			
		} else {
			submissives.put(Main.game.getPlayer(), Main.sex.getPositionRequest().getPartnerSlots().get(0));
			if(Main.sex.getDominantParticipants(false).size()==2) {
				subs.remove(Main.game.getPlayer());
				submissives.put(subs.get(0), Main.sex.getPositionRequest().getPartnerSlots().get(1));
			}
			dominants.put(doms.get(0), Main.sex.getPositionRequest().getPerformerSlots().get(0));
		}
		
		Main.sex.setSexManager(new SexManagerDefault(
				Main.sex.getPositionRequest().getPosition(),
				dominants,
				submissives){
		});
	}

	public static final SexAction POSITION_DOUBLE_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPosition.GLORY_HOLE, SexSlotUnique.GLORY_HOLE_KNEELING, Main.game.getPlayer()));
		}
		
		@Override
		public String getActionTitle() {
			return "Встать на колени";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				return "Опустись на колени на пол, готовая обслуживать «дыры славы» своим ртом.";
			} else {
				return "Опустись на колени на пол, готовая обслуживать «дыру славы» своим ртом.";
			}
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				return "Отстранившись от отверстий, [npc.name] [npc.verb(take)] на шаг назад, а затем опустившись на колени на пол между ними. Переводя взгляд с одного выставленного напоказ мужского достоинства на другое, [npc.she] [npc.verb(let)] [npc.a_moan+], прежде чем приготовиться использовать [npc.her] рот.";
				
			} else {
				return "Отстранившись от отверстия, [npc.name] [npc.verb(take)] на шаг назад, а затем опустившись на колени на пол перед ним. Голодно ухмыляясь выставленному напоказ мужскому достоинству, [npc.she] [npc.verb(let)] [npc.a_moan+], прежде чем наклониться вперед и приготовиться использовать [npc.her] рот.";
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));

				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE,
						Util.newHashMapOfValues(
								new Value<>(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING))));
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING))));
			}
		}
	};
	
	public static final SexAction POSITION_ANAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaOrifice.ANUS));
		}
		
		@Override
		public String getActionTitle() {
			return "Показать задницу";
		}

		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.ass+] к [npc2.namePos] отверстию, готовая к тому, что [npc2.herHim] будет использовать твой [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Встав с места, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF наклоняется к [npc2.name], а затем, присев, прижимает [npc.her] [npc.ass+] к отверстию. Подавшись вперед, [npc.she] [npc.verb(position)] [npc.herself] так, что [npc.her] рот оказывается всего в [unit.sizes] от отверстия на противоположной стороне кабинки, и, издав [npc.a_moan+], [npc.she] [npc.verb(prepare)], чтобы [npc.her] [npc.asshole+] и рот использовались одновременно.";
					
				} else {
					return "Отстранившись от отверстий, [npc.name] [npc.verb(reposition)] [npc.herself] так, что [npc.sheIs] наклоняется и прижимает [npc.her] [npc.ass+] к [npc2.namePos] отверстию. Наклонившись вперед, [npc.she] [npc.verb(position)] [npc.herself] так, что [npc.her] рот находится всего в [unit.sizes] от отверстия на противоположной стороне кабинки, и, издав [npc.a_moan+], [npc.she] [npc.verb(prepare)], чтобы [npc.her] [npc.asshole+] и рот использовались одновременно.";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Вставая, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF, наклоняется к [npc2.name], прежде чем согнуться и прижать [npc.her] [npc.ass+] к отверстию. Издав [npc.a_moan+], [npc.she] [npc.verb(press)] спиной к стене, готовясь к тому, что [npc.her] [npc.asshole+] будет использовано.";
					
				} else {
					return "Отстранившись от отверстий, [npc.name] [npc.verb(reposition)] [npc.herself] так, что [npc.sheIs] наклоняется и прижимает [npc.her] [npc.ass+] к отверстию. Издав [npc.a_moan+], [npc.she] [npc.verb(press)] спиной к стене, готовясь к тому, что [npc.her] [npc.asshole+] будет использовано.";
				}
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(
								new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED))));
				
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED))));
			}
		}
	};

	public static final SexAction POSITION_VAGINAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasVagina()
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isNegative()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaOrifice.VAGINA));
		}
		
		@Override
		public String getActionTitle() {
			return "Показать киску";
		}

		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.ass+] к [npc2.namePos] отверстию, готовая к тому, что [npc2.herHim] будет использовать твой [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Встав с места, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF наклоняется к [npc2.name], прежде чем пригнуться и прижать [npc.her] [npc.ass+] к отверстию. Подавшись вперед, [npc.she] [npc.verb(position)] [npc.herself] так, что рот [npc.her] находится всего в [unit.sizes] от отверстия на противоположной стороне кабинки, и, издав [npc.a_moan+], [npc.she] [npc.verb(prepare)], чтобы [npc.her] [npc.pussy+] и рот использовались одновременно.";
					
				} else {
					return "Отстранившись от дырок в стене, [npc.name] [npc.verb(reposition)] [npc.herself] так, что [npc.sheIs] наклоняется и прижимает [npc.her] [npc.ass+] к [npc2.namePos] дырке в стене. Наклонившись вперед, [npc.she] [npc.verb(position)] [npc.herself] так, что рот [npc.her] находится всего в [unit.sizes] от отверстия на противоположной стороне кабинки, и, издав [npc.a_moan+], [npc.she] [npc.verb(prepare)], чтобы одновременно использовать [npc.her] [npc.pussy+] и рот.";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "Встав с места, [npc.name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF наклоняется к [npc2.name], прежде чем пригнуться и прижать [npc.her] [npc.ass+] к «позорному отверстию». Издавая [npc.a_moan+], [npc.she] [npc.verb(press)] к стене, готовясь к тому, что [npc.her] [npc.pussy+] будут использовать.";
					
				} else {
					return "Отходя от отверстий, [npc.name] [npc.verb(reposition)] [npc.herself] так, что [npc.sheIs] наклоняется и прижимает [npc.her] [npc.ass+] к отверстию. Издавая [npc.a_moan+], [npc.she] [npc.verb(press)] спиной к стене, готовясь к тому, что [npc.her] [npc.pussy+] будет использована.";
				}
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(
								new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_FUCKED))));
				
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_FUCKED))));
			}
		}
	};
	
	public static final SexAction POSITION_VAGINAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE_SEX,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKED),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
		}
		@Override
		public String getActionTitle() {
			return "Трахать киску (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попросить [npc2.name] прижать [npc2.her] киску к отверстию, чтобы ты мог её оттрахать.";
		}
		@Override
		public String getDescription() {
			return "Желая оттрахать [npc2.namePos] в киску, ты кричишь достаточно громко, чтобы [npc2.herHim] услышала: [npc.speech(Прижми свою киску к отверстию! Я хочу трахнуть тебя!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_VAGINAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_VAGINAL_RECEIVING);
			}
		}
	};

	public static final SexAction POSITION_ANAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE_SEX,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_FUCKED)
					&& Main.game.isAnalContentEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
		}
		@Override
		public String getActionTitle() {
			return "Трахать задницу (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попросить [npc2.name] прижать [npc2.her] задницу к отверстию, чтобы ты мог её оттрахать.";
		}
		@Override
		public String getDescription() {
			return "Желая оттрахать [npc2.namePos] в задницу, ты кричишь достаточно громко, чтобы [npc2.herHim] услышала: [npc.speech(Прижми свою жопу к отверстию! Я хочу оттрахать твою задницу!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};

	public static final SexAction POSITION_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_KNEELING),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			return "Оральный секс (R)";
		}
		@Override
		public String getActionDescription() {
			return "Попросить [npc2.name] опуститься на колени перед отверстием, чтобы [npc2.she] могла заняться оральным сексом.";
		}
		@Override
		public String getDescription() {
			return "Желая, чтобы [npc2.name] сделала ей оральный секс, ты кричишь достаточно громко, чтобы [npc2.herHim] услышала: [npc.speech(Встань на колени и используй свой ротик!)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
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
			if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
				if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
					return "Встав и отступив назад, чтобы прижать [npc.her] [npc.pussy+] к скважине, [npc.name] [npc.moansVerb], [npc.speech(Ну же, трахни мою киску!)]";
					
				} else {
					return "Оставаясь на [npc.her] текущем месте, [npc.name] гневно выкрикивает: [npc.speech(Я не дам тебе трахать мою киску! Просто довольствуйся тем, что есть!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
				if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
					return "Встав и отступив назад, чтобы прижать [npc.her] [npc.asshole+] к скважине, [npc.name] [npc.moansVerb], [npc.speech(Ну же, трахни мою задницу!)]";
					
				} else {
					return "Оставаясь на [npc.her] текущем месте, [npc.name] гневно выкрикивает: [npc.speech(Я не дам тебе трахать мою киску! Просто довольствуйся тем, что есть!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_KNEELING) {
				return "Опустившись на [npc.her] колени, [npc.name] [npc.moansVerb],"
							+ (Main.game.getPlayer().hasPenis()
								?" [npc.speech(Дай мне отсосать твой член!)]"
								:" [npc.speech(Дай мне использовать мой рот!)]");
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if((Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()
					|| (Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()))
					|| Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_KNEELING) {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)));
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)));
					
				} else {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null)));
				}
				applyChangeSlotEffects();
				
			} else {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
					Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
					Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
				}
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
}
