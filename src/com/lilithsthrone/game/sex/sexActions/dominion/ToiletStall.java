package com.lilithsthrone.game.sex.sexActions.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * Contains all positional changes for both sub and dom.
 * 
 * If sub, positional change is just a suggestion, which the NPC may refuse if they have other preferences.
 * 
 * @since 0.1.79
 * @version 0.4.9.5
 * @author Innoxia
 */
public class ToiletStall {
	
	private static boolean checkBaseRequirements(PositioningData data, boolean request) {
		return GenericPositioning.checkBaseRequirements(data, request);
//		return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
//				&& Main.sex.getInitialSexManager().getAllowedSexPositions().contains(data.getPosition())
//				&& !(Main.sex.getPosition() == data.getPosition()
//					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==data.getPerformerSlots().get(0)
//					&& Main.sex.getSexPositionSlot(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==data.getPartnerSlots().get(0))
//				&& data.getPosition().getMaximumSlots()>=Main.sex.getTotalParticipantCount(false)
//				&& Main.sex.getTotalParticipantCount(false)==(data.getPerformerSlots().size()+data.getPartnerSlots().size())
//				&& (request
//						?Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())!=SexControl.FULL
//						:(Main.sex.getCharacterPerformingAction().isPlayer()
//							?Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
//							:!Main.sex.isCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterPerformingAction())))
//				&& (!request && !Main.sex.getCharacterPerformingAction().isPlayer()
//						?((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(data.getPosition(), data.getPerformerSlots().get(0), data.getPartnerSlots().get(0), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
//						:true);
	}

	public static final SexAction PLAYER_POSITION_SWAP = new SexAction(
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
//					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(Main.sex.getCharacterPerformingAction()) // Should be covered in the method above
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
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
			return "Обхватив [npc2.name], вы перемещаете [npc2.herHim] и меняетесь местами с [npc2.herHim], после чего [npc.moaning] [npc.speech(Так будет куда веселее!)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.swapSexPositionSlots(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction PLAYER_POSITION_FACE_TO_WALL = new SexAction(
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
			return "Лицом к стене";
		}
		@Override
		public String getActionDescription() {
			return "Прижать [npc2.name] к ближайшей стене.";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] к стене кабинки туалета. Втирая [npc.her] тело в [npc2.her] со спины, [npc.she] [npc.moansVerb] в [npc2.her] [npc2.ear], [npc.speech(Хороший [npc2.girl]! А теперь не дергайся, пока я тебя трахаю!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_FACE_TO_WALL_REQUEST = new SexAction(
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
			return "Лицом к стене";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться занять положение лицом к стене туалетной кабинки.";
		}
		@Override
		public String getDescription() {
			return "Прежде чем [npc2.name] успеет среагировать, вы быстро прижимаетесь к одной из стен туалетной кабинки. Упершись руками в покрытую граффити поверхность перед собой, вы выставляете свой [npc.ass+], покачивая им перед [npc2.name], пытаясь подтолкнуть [npc2.herHim] к тому, чтобы она трахала вас в такой позе.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL = new SexAction(
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
			return "Спиной к стене";
		}
		@Override
		public String getActionDescription() {
			return "Прижать [npc2.name] спиной к одной из стен туалетной кабинки.";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] [npc.verb(push)] [npc2.herHim] спиной к одной из стенок кабинки. Втираясь [npc.her] телом в [npc2.hers], [npc.she] [npc.moansVerb] в [npc2.her] [npc2.ear], [npc.speech(Хороший [npc2.girl]! А теперь не дергайся, пока я тебя трахаю!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_BACK_TO_WALL_REQUEST = new SexAction(
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
			return "Спиной к стене";
		}
		@Override
		public String getActionDescription() {
			return "Попробуй встать так, чтобы твоя спина прижалась к одной из стенок кабинки.";
		}
		@Override
		public String getDescription() {
			return "Прежде чем [npc2.name] успеет среагировать, ты быстро прижимаешься к стенке кабинки. Опершись спиной о покрытую граффити поверхность, ты бросаешь на [npc2.name] самый соблазнительный взгляд, пытаясь побудить [npc2.herHim] трахать тебя в таком положении.";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT),
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Встать на колени (принять оральный секс)";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] [npc2.her] на колени.";
		}
		@Override
		public String getDescription() {
			return "Схватив [npc2.namePos] за плечи, [npc.name] быстро [npc.verb(force)] [npc2.herHim] на [npc2.her] колени перед [npc.herHim]. Подняв взгляд, [npc2.name] [npc2.verb(see)] [npc.herHim], ухмыляющегося над [npc2.her] покорной фигуркой, и с легким смешком [npc.name] [npc.moansVerb], [npc.speech(Пора пустить ротик в дело!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};
	
	public static final SexAction PLAYER_POSITION_KNEELING_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Встать на колени";
		}
		@Override
		public String getActionDescription() {
			return "Рухнуть на колени в надежде, что [npc2.name] захочет, чтобы ты отсосал(а) [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			return "Ты быстро опускаешься на колени перед [npc2.name], немного подаваясь вперед, чтобы поднести свое лицо ближе к паху [npc2.her].";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
	};
	
	public static final SexAction PLAYER_FORCE_POSITION_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL),
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, false);
		}
		@Override
		public String getActionTitle() {
			return "Встать на колени (отдать оральный секс)";
		}
		@Override
		public String getActionDescription() {
			return "Встать на колени перед [npc2.name], готовый(ая) обслужить [npc2.herHim] своим ртом или [npc.hands].";
		}
		@Override
		public String getDescription() {
			return "Проводя [npc.her] [npc.hands] по телу [npc2.namePos], [npc.name] [npc.verb(drop)] на [npc.her] колени перед [npc2.herHim], после чего поднимает взгляд и одаривает [npc2.herHim] соблазнительной улыбкой. С легким смешком [npc.she] затем [npc.moansVerb], [npc.speech(Замри и наслаждайся!)]";
		}
		@Override
		public void applyEffects() {
			GenericPositioning.setNewSexManager(data, false);
		}
	};

	public static final SexAction PLAYER_POSITION_REQUEST_SELF_KNEELING = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private PositioningData data = new PositioningData(
				SexPosition.STANDING,
				Util.newArrayListOfValues(SexSlotStanding.STANDING_DOMINANT),
				Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL));

		@Override
		public boolean isBaseRequirementsMet() {
			return checkBaseRequirements(data, true);
		}
		@Override
		public String getActionTitle() {
			return "Принять минет на коленях";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться прижать [npc2.name] к [npc2.her] на колени, чтобы [npc2.she] отсосала тебе.";
		}
		@Override
		public String getDescription() {
			return "Приподняв твою [npc.arms], ты берешь [npc2.namePos] за плечи и с небольшим усилием пытаешься заставить [npc2.herHim] опуститься перед тобой на колени.";
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
			
			if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "К твоему огромному восторгу, ты чувствуешь, как [npc.name] тянется вниз и грубо хватает тебя за бедра, и, втираясь [npc.herself] в твою спину, [npc.she] рычит тебе в ухо: [npc.speech(Обожаю трахать таких сучек, как ты, сзади! А теперь <i>не двигайся</i>, как хорошая шлюха!)]";
						default:
							return "К твоему огромному восторгу, ты чувствуешь, как [npc.name] тянется вниз, чтобы обхватить твои бедра, и когда [npc.she] наклоняется над твоим плечом, [npc.she] [npc.moans] тебе в ухо: [npc.speech(Хорошая [pc.girl]! Это будет весело!)]";
					}
				} else {
					return "Схватив тебя за плечи, [npc.name] оттаскивает тебя от стены, возвращая в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, совсем страх потеряла?! Только <i>смей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotAgainstWall.STANDING_WALL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name] ухмыляется, когда ты пытаешься соблазнить [npc.herHim] подойти и трахнуть тебя у стены. Подойдя ближе, чтобы грубо прижаться [npc.her] телом к твоему, [npc.she] наклоняется к твоему плечу и рычит тебе в ухо: [npc.speech(Хорошая сучка! А теперь <i>не шевелись</i>, и я хорошенько тебя оттрахаю!)]";
						default:
							return "[npc.Name] ухмыляется, когда ты пытаешься соблазнить [npc.herHim] подойти и трахнуть тебя у стены. Подойдя ближе, чтобы прижать [npc.her] телом к твоему, [npc.she] наклоняется к твоему плечу и [npc.moans] тебе в ухо: [npc.speech(Хорошая [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Схватив тебя за плечи, [npc.name] оттаскивает тебя от стены, возвращая в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, совсем страх потеряла?! Только <i>смей</i> еще раз так сделать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.STANDING_DOMINANT) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "[npc.Name] ухмыляется, глядя сверху вниз на твою покорную, стоящую на коленях фигуру. С легким смешком [npc.she] хватает тебя за голову одной [npc.hand], дергая вперед, прямо в [npc.her] промежность, пока [npc.she] рычит на тебя: [npc.speech(Тебе лучше постараться, сучка! А теперь <i>не двигайся</i>, пока я использую твой рот!)]";
						default:
							return "[npc.Name] ухмыляется, глядя сверху вниз на твою покорную, стоящую на коленях фигуру. С помощью [npc.a_moan+], [npc.she] хватает тебя за голову одной [npc.hand], притягивая тебя вперед к [npc.her] промежности, пока [npc.she] [npc.moansVerb] на тебя: [npc.speech(Хорошая [pc.girl]! Это будет весело!)]";
					}
					
				} else {
					return "Потянувшись вниз, чтобы схватить тебя за [pc.arm], [npc.name] тянет тебя назад в прежнее положение, пока [npc.she] гневно отчитывает тебя: [npc.speech(Ты что, блять, творишь?! Не <i>смей</i> больше так делать!)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPartnerSlots().get(0)==SexSlotStanding.PERFORMING_ORAL) {
				if(isHappy) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_ROUGH:
							return "Протянув руки и сбрасывая твой [pc.arms] с [npc.her], [npc.name] издает яростный рык. К твоему удивлению, [npc.she] внезапно опускается на [npc.her] колени, и ты видишь, как [npc.herHim] ухмыляется тебе снизу вверх: [npc.speech(К счастью для тебя, именно это я и планировала всё это время! А теперь не двигайся, сучка, и не вздумай не оценить это!)]";
						default:
							return "Протянув руку, чтобы схватить твой [pc.arms], [npc.name] издает короткий смешок, пока [npc.she] позволяет тебе прижать [npc.herHim] к [npc.her] коленям. Посмотрев вниз, ты видишь, как [npc.herHim] ухмыляется тебе, [npc.speech(Это твой счастливый день! Обожаю делать минет! Тебе стоит это ценить!)]";
					}
					
				} else {
					return "Протянув руку и стягивая с [npc.her] твою [pc.arms], [npc.name] гневно отчитывает тебя: [npc.speech(Ты что творишь?! Ты реально думаешь, что я буду делать тебе минет?! Только <i>посмей</i> попробовать еще раз!)]";
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
				GenericPositioning.setNewSexManager(Main.sex.getPositionRequest(), true);
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
	
}
