package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.84
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisBreasts {
	
	public static final SexAction FORCE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Втолкнуть в рот";
		}

		@Override
		public String getActionDescription() {
			return "Резко подать [npc.hips] вперед, втискивая [npc.cockHead] своих [npc.cock+] прямо в [npc2.namePos] рот.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Special check for NPCs, as this action can hit penis, breast, and oral fetishes.
			// Positive penis + breast desires can outweigh a negative oral one and thus make NPCs use this action, even though it makes no sense if they hate the oral fetish
			if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
					return false;
				}
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().getPenisRawSizeValue()>=6
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)
//					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко толкая вперед между [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] до самого [npc2.her] рта, заставляя [npc.cockHead] пройти сквозь [npc2.her] [npc2.lips].",

							"Медленно толкая [npc.her] [npc.hips] вперед, [npc.name] втискивает [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], продвигаясь до тех пор, пока [npc.cockHead+] не проходит сквозь [npc2.her] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Жадно толкая вперед между [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] до самого [npc2.her] рта, заставляя [npc.cockHead] пройти сквозь [npc2.her] [npc2.lips].",

							"Жадно толкая [npc.her] [npc.hips] вперед, [npc.name] втискивает [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], продвигаясь до тех пор, пока [npc.cockHead+] не прорывается сквозь [npc2.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо толкая вперед между [npc2.namePos] [npc2.breasts], [npc.name] с силой вжимает [npc.her] [npc.cock+] в [npc2.her] рот, проталкивая [npc.cockHead] сквозь [npc2.her] [npc2.lips].",

							"Яростно вбивая [npc.her] [npc.hips] вперед, [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], продвигаясь до тех пор, пока [npc.cockHead+] не врежется сквозь [npc2.her] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(push)] [npc.her] [npc.cock+] прямо до самого рта [npc2.her], силой проталкивая [npc.cockHead] сквозь [npc2.her] [npc2.lips].",

							"Толкая [npc.her] [npc.hips] вперед, [npc.name] с силой втискивает [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], продвигаясь до тех пор, пока [npc.cockHead+] не прорывается сквозь [npc2.her] [npc2.lips+]."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(grin)] с [npc.her] энтузиазмом, широко открывая [npc2.her] рот, чтобы подарить [npc.cockHead] [npc.her] [npc.cock] нежное посасывание, а затем [npc2.name] [npc2.verb(draw)] назад, но прежде успев запечатлеть поцелуй на самом кончике.",
	
								" [npc2.Name] [npc2.verb(open)] [npc2.her] ртом, чтобы принять [npc.her] [npc.cock+], обдавая [npc.cockHead] горячим влажным посасыванием, а затем отстраняясь, чтобы нежно поцеловать самый кончик."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] радостный [npc2.moan], и, жадно открывая [npc2.her] рот, чтобы с любовью облизать/отсосать [npc.cockHead] [npc.her] [npc.cock], [npc2.name] затем [npc2.verb(draw)] назад, но прежде успевает оставить влажный поцелуй на самом кончике.",
	
								" [npc2.Name] с нетерпением [npc2.verb(open)] [npc2.her], чтобы принять [npc.her] [npc.cock+], горячо и влажно посасывая [npc.cockHead], а затем отстраняясь, чтобы страстно поцеловать самый кончик."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], и, быстро открыв [npc2.her] рот, чтобы с силой отсосать [npc.cockHead] [npc.her] [npc.cock], [npc2.name] затем [npc2.verb(draw)] назад, но не прежде чем запечатлеть грубый поцелуй на самом кончике.",
	
								" [npc2.Name] быстро [npc2.verb(open)] [npc2.her] рот, чтобы принять [npc.her] [npc.cock+], с силой отсасывая [npc.cockHead], прежде чем отстраниться и нанести грубый поцелуй на самый кончик."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] немного [npc2.moan], и, открыв [npc2.her] рот, чтобы послушно отсосать [npc.cockHead] [npc.her] [npc.cock], [npc2.name] затем [npc2.verb(draw)] назад, но не прежде чем быстро поцеловать самый кончик.",
	
								" [npc2.Name] [npc2.verb(open)] [npc2.her] рот, чтобы принять [npc.her] [npc.cock+], послушно отсасывая [npc.cockHead], прежде чем отстраниться и быстро поцеловать самый кончик."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], и, пытаясь оттянуть [npc2.her] рот от [npc.cockHead] [npc.her] [npc.cock], [npc2.name] отчаянно [npc2.verb(plead)], чтобы [npc.herHim] оставил(а) [npc2.name] в покое.",
	
								" [npc2.Name] [npc2.verb(jerk)] [npc2.her] голову назад, пытаясь оттолкнуть [npc.her] [npc.cock+] от [npc2.her] рта, пока на [npc2.her] [npc2.eyes] [npc2.verb(start)] наворачиваются слезы."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
			return null;
		}
		
	};
	
	public static final SexAction TAKE_COCK_INTO_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Пайзури в рот";
			} else {
				return "Найзури в рот";
			}
		}

		@Override
		public String getActionDescription() {
			return "Подавшись вперед, ты берешь [npc2.cockHead] [npc2.namePos] [npc2.cock+] в свой рот.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// Special check for NPCs, as this action can hit penis, breast, and oral fetishes.
			// Positive penis + breast desires can outweigh a negative oral one and thus make NPCs use this action, even though it makes no sense if they hate the oral fetish
			if(!Main.sex.getCharacterPerformingAction().isPlayer()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isNegative()) {
					return false;
				}
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).getPenisRawSizeValue()>=6
					&& Main.sex.isOrificeFree(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)
//					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.SIXTY_NINE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко прижимая [npc.her] [npc.face] к [npc2.namePos] [npc2.cock], пока он скользит между [npc.her] [npc.breasts], [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] и [npc.verb(take)] [npc2.cockHead] в [npc.her] рот.",

							"Медленно прижимая [npc.her] [npc.face] вниз, [npc.name] [npc.verb(take)] [npc2.cockHead] [npc2.namePos] [npc2.cock+] в [npc.her] рот, пока [npc2.she] [npc2.verb(thrust)] между [npc.her] [npc.breasts+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью прижимая [npc.her] [npc.face] к [npc2.namePos] [npc2.cock], пока он скользит между [npc.her] [npc.breasts], [npc.name] алчно [npc.verb(part)] [npc.her] [npc.lips+] и [npc.verb(take)] [npc2.cockHead] в [npc.her] рот.",

							"С жадностью прижимая [npc.her] [npc.face] вниз, [npc.name] алчно [npc.verb(take)] [npc2.cockHead] [npc2.namePos] [npc2.cock+] в [npc.her] рот, пока [npc2.she] [npc2.verb(thrust)] между [npc.her] [npc.breasts+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.face] к [npc2.namePos] [npc2.cock], пока он скользит между [npc.her] [npc.breasts], [npc.name] алчно [npc.verb(part)] [npc.her] [npc.lips+] и [npc.verb(take)] [npc2.cockHead] в [npc.her] рот.",

							"Прижимая [npc.her] [npc.face] вниз, [npc.name] жадно [npc.verb(take)] [npc2.cockHead] [npc2.namePos] [npc2.cock+] в [npc.her] рот, пока [npc2.she] [npc2.verb(thrust)] между [npc.her] [npc.breasts+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.face] вниз к [npc2.namePos] [npc2.cock], пока он скользит между [npc.her] [npc.breasts], [npc.name] [npc.verb(part)] [npc.her] [npc.lips+] и [npc.verb(take)] [npc2.cockHead] в [npc.her] рот.",

							"Прижимая [npc.her] [npc.face] вниз, [npc.name] жадно [npc.verb(take)] [npc2.cockHead] [npc2.namePos] [npc2.cock+] в [npc.her] рот, пока [npc2.she] [npc2.verb(thrust)] между [npc.her] [npc.breasts+]."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(smile)] с [npc.her] энтузиазмом, и, мягко вдавливая [npc2.her] [npc2.cock] в [npc.her] рот, [npc2.she] [npc2.verb(let)] [npc.herHim] сосать и целовать [npc2.cockHead] мгновение, прежде чем отстраниться и продолжить трахать [npc.her] [npc.breasts+].",
	
								" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] [npc2.cock+] в [npc.her] рот, позволяя [npc.herHim] горячо и влажно отсосать [npc2.cockHead], прежде чем отстраниться и продолжить трахать [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(grin)] с [npc2.her] энтузиазмом и, жадно заталкивая [npc2.her] [npc2.cock] в [npc.namePos] рот, [npc2.she] [npc2.verb(let)] [npc.herHim] сосет и целует [npc2.cockHead] мгновение, прежде чем отстраниться и продолжить трахать [npc.her] [npc.breasts+].",
	
								" [npc2.Name] жадно [npc2.verb(push)] [npc2.her] [npc2.cock+] в [npc.her] рот, позволяя [npc.herHim] горячо и влажно посасывать [npc2.cockHead], прежде чем отстраниться и продолжить агрессивно трахать [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(grin)] с [npc2.her] энтузиазмом и, грубо заталкивая [npc2.her] [npc2.cock] глубже в [npc.namePos] рот, [npc2.she] позволяет [npc.herHim] сосать и целовать [npc2.cockHead] мгновение, прежде чем отстраниться и продолжить агрессивно трахать [npc.her] [npc.breasts+].",
	
								" [npc2.Name] грубо [npc2.verb(force)] [npc2.her] [npc2.cock+] в [npc.her] рот, позволяя [npc.herHim] горячо и влажно посасывать [npc2.cockHead], прежде чем отстраниться и продолжить агрессивно трахать [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] слегка [npc2.verb(let)] [npc2.moan] и, заталкивая [npc2.her] [npc2.cock] в [npc.namePos] рот, [npc2.she] ахает, пока [npc.name] сосет и целует [npc2.cockHead] мгновение, прежде чем позволить [npc2.herHim] отстраниться и [npc2.verb(continue)] трахать [npc.her] [npc.breasts+].",
	
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.cock+] в [npc.her] рот, тяжело дыша, пока [npc.she] [npc.verb(give)] горячее, влажное сосание [npc2.cockHead], прежде чем позволить [npc2.herHim] отстраниться и [npc2.verb(continue)] трахать [npc.her] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc.a_moan+] наружу, и, пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] рта, [npc2.she] отчаянно [npc.verb(plead)], чтобы [npc.herHim] оставили [npc2.herHim] в покое.",
	
								" [npc2.Name] [npc2.verb(try)] попытаться оттянуть [npc2.her] [npc2.hips] назад, но [npc.name] [npc.verb(hold)] [npc2.herHim] на месте, сосая [npc2.cockHead] [npc2.her] [npc2.cock+] мгновение, прежде чем наконец позволить [npc2.herHim] отстраниться."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
			return null;
		}
		
	};
	
	
	public static final SexAction FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Начать пайзури";
			} else {
				return "Начать найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Просунь свой [npc.cock+] между [npc2.namePos] [npc2.breasts+] и начни трахать их.";
			} else {
				return "Начни тереться своим [npc.cock+] о грудь [npc2.namePos].";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы обхватить [npc2.namePos] [npc2.breasts+], [npc.name] осторожно [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, прежде чем скользнуть вперед и начать трахать [npc2.her] [npc2.breasts]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы жадно вжать [npc.her] [npc.fingers] в [npc2.namePos] [npc2.breasts+], [npc.name] с нетерпением [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, прежде чем скользнуть вперед и начать с энтузиазмом трахать [npc2.her] [npc2.breasts]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы грубо вдавить [npc.her] [npc.fingers] в [npc2.namePos] [npc2.breasts+], [npc.name] с силой [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, прежде чем резко податься вперед и начать стремительно трахать [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы схватить [npc2.namePos] [npc2.breasts+], [npc.name] затем [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, прежде чем скользнуть вперед и начать трахать [npc2.her] [npc2.breasts]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы жадно вжать [npc.her] [npc.fingers] в [npc2.namePos] [npc2.breasts+], [npc.name] с нетерпением [npc.verb(push)] их вместе, выравнивая [npc.her] [npc.cock] по [npc2.her] ложбинке, прежде чем скользнуть вперед и начать с энтузиазмом трахать [npc2.her] [npc2.breasts]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] в ответ [npc2.verb(let)] издает радостный маленький [npc2.moan], протягивая руки вверх, чтобы помочь прижать [npc2.her] [npc2.breasts] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] в ответ [npc2.verb(let)] издает [npc2.a_moan+], быстро протягивая руки вверх, чтобы помочь прижать [npc2.her] [npc2.breasts] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] в ответ [npc2.verb(let)] издает [npc2.a_moan+], протягивая руки вверх, чтобы с силой прижать [npc2.her] [npc2.breasts] друг к другу, пока [npc2.she] властно [npc2.verb(order)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] в ответ [npc2.verb(let)] издает [npc2.a_moan+], быстро протягивая руки вверх, чтобы помочь прижать [npc2.her] [npc2.breasts] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] слегка [npc2.verb(let)] [npc2.moan] в ответ, прежде чем потянуться вверх, чтобы помочь сжать [npc2.her] [npc2.breasts] вместе, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] слегка [npc2.verb(let)] [npc2.a_moan+] в ответ, слабо пытаясь оттолкнуть [npc.herHim] от [npc2.her], пока [npc2.breasts] [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] остановился."));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы обхватить [npc2.namePos] [npc2.breasts+], [npc.name] мягко [npc.verb(try)], чтобы прижать их друг к другу, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, которую [npc2.she] [npc2.has], прежде чем скользнуть вперед и начать тереться о [npc2.her] грудь."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы сжимать и ласкать [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] изо всех сил, пытаясь прижать их друг к другу, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, которую [npc2.she] [npc2.has], прежде чем скользнуть вперед и начать жадно тереться о [npc2.her] грудь."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы грубо сжимать и хватать [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] изо всех сил, пытаясь прижать их друг к другу, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, которую [npc2.she] [npc2.has], прежде чем скользнуть вперед и начать напористо тереться о [npc2.her] грудь."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руку вниз, чтобы сжать и ощупать [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] лучше всего попытаться прижать их друг к другу, выравнивая [npc.her] [npc.cock] так, чтобы образовалась хоть какая-то ложбинка [npc2.she] [npc2.has], прежде чем скользнуть вперед и начать тереться о [npc2.her] грудь."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы сжимать и ласкать [npc2.namePos] [npc2.breasts+], [npc.name] [npc.do] [npc.her] изо всех сил, пытаясь прижать их друг к другу, выравнивая [npc.her] [npc.cock] по той небольшой ложбинке, которую [npc2.she] [npc2.has], прежде чем скользнуть вперед и начать жадно тереться о [npc2.her] грудь."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] радостный маленький [npc2.moan] в ответ, потянувшись вверх, чтобы помочь прижать [npc2.her] [npc2.breastSize] [npc2.breasts] друг к другу, пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы трахать [npc2.her] груди."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, быстро потянувшись вверх, чтобы помочь прижать [npc2.her] [npc2.breastSize] [npc2.breasts] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы трахать [npc2.her] груди."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, потянувшись вверх, чтобы с силой попытаться [npc.verb(press)] [npc2.her] [npc2.breastSize] [npc2.breasts] вместе, пока [npc2.she] доминантно [npc2.verb(order)] [npc.herHim], чтобы трахать [npc2.her] груди."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, быстро потянувшись вверх, чтобы помочь прижать [npc2.her] [npc2.breastSize] [npc2.breasts] друг к другу, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы трахать [npc2.her] груди."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] немного [npc2.moan] в ответ, а затем потянулась вверх, пытаясь помочь сжать [npc2.her] [npc2.breastSize] [npc2.breasts] вместе, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы трахать [npc2.her] грудь."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] немного [npc2.a_moan+] в ответ, слабо потянувшись вверх, чтобы попытаться оттолкнуть [npc.herHim] от [npc2.her] [npc2.breastSize] [npc2.breasts], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] остановилась."));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к [npc2.namePos] торсу, [npc.name] перехватывает [npc.herself], чтобы выровнять [npc.her] [npc.cock] над [npc2.her] грудью, а затем скользит вперед, начиная жадно тереться о [npc2.her] тело."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к [npc2.namePos] торсу, [npc.name] перехватывает [npc.herself], чтобы выровнять [npc.her] [npc.cock] над [npc2.her] грудью, а затем скользит вперед, начиная с нетерпением тереться о [npc2.her] тело."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку вниз, чтобы грубо [npc.verb(press)] [npc.her] [npc.hands] по [npc2.namePos] торсу, [npc.name] перехватывает [npc.herself], чтобы выровнять [npc.her] [npc.cock] над [npc2.her] грудью, а затем подается вперед, начиная с силой втираться в [npc2.her] тело."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к [npc2.namePos] торсу, [npc.name] перехватывает [npc.herself], чтобы выровнять [npc.her] [npc.cock] над [npc2.her] грудью, а затем скользит вперед, начиная жадно тереться о [npc2.her] тело."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы прижать [npc.her] [npc.hands] к [npc2.namePos] торсу, [npc.name] перехватывает [npc.herself], чтобы выровнять [npc.her] [npc.cock] над [npc2.her] грудью, а затем скользит вперед, начиная с нетерпением тереться о [npc2.her] тело."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] в ответ весело [npc2.verb(let)] [npc2.moan], выпячивая [npc2.her] грудь, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы всё продолжалось."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, quickly pushing [npc2.her] chest out as [npc2.she] happily [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] в ответ мощно [npc2.verb(let)] [npc2.a_moan+], с силой выпячивая [npc2.her] грудь, пока [npc2.she] властно [npc2.verb(order)] [npc.herHim], чтобы всё продолжалось."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] в ответ [npc2.verb(let)] [npc2.a_moan+], выпячивая [npc2.her] грудь, пока [npc2.she] счастливо [npc2.verb(encourage)] [npc.herHim], чтобы всё продолжалось."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a little [npc2.moan] in response, pushing [npc2.her] chest out as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep going."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, при этом она слабо пытается дотянуться и [npc2.verb(push)] [npc.name] подальше от [npc2.herHim], пока [npc2.she] [npc2.verb(beg)], чтобы её оставили в покое."));
							break;
						default:
							break;
					}
				}
			}
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterTargetedForSexAction(action).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] [npc2.breasts+] вместе в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать трахать [npc2.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+], и, жадно сжимая [npc2.her] [npc2.breastSize] [npc2.breasts] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз между [npc2.her].",
			
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] вокруг [npc.namePos] [npc.cock+], прежде чем умоляя [npc.herHim] продолжить трахать [npc2.her] мягкие полусферы."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.cock], [npc2.name] слабо [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] оставил [npc2.her] [npc2.breasts] в покое.",
			
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил [npc2.her] [npc2.breasts] в покое.",
			
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрался от [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.breasts+] вместе в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать трахать [npc2.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.breastSize] [npc2.breasts] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз между [npc2.her] ложбинкой.",
			
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] вокруг [npc.namePos] [npc.cock+], прежде чем умолять [npc.herHim] продолжать трахать [npc2.her] мягкие полусферы."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(push)] [npc2.her] [npc2.breasts+] вместе в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать трахать [npc2.her] ложбинку.",
			
								" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, мягко толкая [npc2.her] [npc2.breastSize] [npc2.breasts] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз между [npc2.her] ложбинкой.",
			
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] вокруг [npc.namePos] [npc.cock+], прежде чем умолять [npc.herHim] продолжать трахать [npc2.her] мягкие полусферы."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] грубо [npc2.verb(press)] [npc2.her] [npc2.breasts+] вместе в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать трахать [npc2.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, с силой прижимая [npc2.her] [npc2.breastSize] [npc2.breasts] друг к другу, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать вколачивать [npc.her] [npc.cock+] вверх и вниз между [npc2.her] ложбинкой.",
			
								" [npc2.Moaning] в восторге, [npc2.name] властно [npc2.verb(wrap)] [npc2.her] [npc2.breasts+] вокруг [npc.namePos] [npc.cock+], прежде чем приказать [npc.herHim] продолжать трахать [npc2.her] мягкие холмики."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(action).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с нетерпением [npc2.verb(attempt)], чтобы сжать крошечные [npc2.her] [npc2.breasts] вместе в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать трахать ту малую ложбинку, что есть у [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, с нетерпением пытаясь сжать [npc2.her] [npc2.breastSize] [npc2.breasts] вместе, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз между крошечной ложбинкой [npc2.her].",
			
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.breasts+] между боков [npc.namePos] [npc.cock+], прежде чем умолять [npc.herHim] продолжить трахать [npc2.her] крошечную ложбинку."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.cock], [npc2.name] слабо [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] оставил [npc2.her] [npc2.breasts] в покое.",
			
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил [npc2.her] [npc2.breasts] в покое.",
			
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрался от [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] в ответ сжать [npc2.her] [npc2.breasts+] вместе, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name] продолжать трахать ту малую ложбинку, что [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, сжимая [npc2.her] [npc2.breastSize] [npc2.breasts] вместе, [npc2.she] [npc2.verb(encourage)], чтобы [npc.name] продолжал скользить [npc.her] [npc.cock+] вверх и вниз между крошечной ложбинкой [npc2.her].",
			
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.breasts+] между боков [npc.namePos] [npc.cock+], прежде чем умолять [npc.herHim] продолжить трахать [npc2.her] крошечную ложбинку."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] в ответ мягко сжать [npc2.her] [npc2.breasts+] вместе, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(encourage)] [npc.name] продолжать трахать ту малую ложбинку, что [npc2.she] [npc2.has].",
			
								" Мягкий [npc2.moan] вырывается из ложбинки между [npc2.namePos] [npc2.lips+], и, нежно прижимая [npc2.her] [npc2.breastSize] [npc2.breasts] друг к другу, [npc2.she] [npc2.verb(encourage)] [npc.name], продолжая скользить [npc.her] [npc.cock+] вверх и вниз в крошечной ложбинке [npc2.her].",
			
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.breasts+] о края [npc.namePos] [npc.cock+], прежде чем умоляя [npc.herHim] продолжать трахать [npc2.her] крошечную ложбинку."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(attempt)] грубо прижать [npc2.verb(press)] [npc2.her] [npc2.breasts+] друг к другу в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.name], продолжая трахать ту малую ложбинку, что [npc2.she] [npc2.has].",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+], и, с силой прижимая [npc2.her] [npc2.breastSize] [npc2.breasts] друг к другу, [npc2.she] [npc2.verb(order)] [npc.name], продолжая мощно вбиваться [npc.her] [npc.cock+] вверх и вниз в крошечной ложбинке [npc2.her].",
			
								" [npc2.Moaning] в восторге, [npc2.name] властно [npc2.verb(press)] [npc2.her] [npc2.breasts+] о края [npc.namePos] [npc.cock+], прежде чем приказывая [npc.herHim] продолжать трахать [npc2.her] крошечную ложбинку."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] плоскую грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать втираться [npc2.her] торс.",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] плоскую грудь, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
			
								" От восторга [npc2.Moaning], [npc2.name] с нетерпением [npc2.does] [npc2.her] изо всех сил, пытаясь [npc2.verb(wrap)] [npc2.her] плоскую грудь вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и умолять [npc.herHim] продолжать втираться в [npc2.her] торс."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.cock], [npc2.name] слабо [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] оставил [npc2.her] [npc2.breasts] в покое.",
			
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил [npc2.her] [npc2.breasts] в покое.",
			
								" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрался от [npc2.her] [npc2.breasts]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] плоскую грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать втираться в [npc2.her] торс.",
			
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, выпячивая [npc2.her] плоскую грудь, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
	
								" От восторга [npc2.Moaning], [npc2.name] [npc2.does] [npc2.her] изо всех сил, пытаясь [npc2.verb(wrap)] [npc2.her] плоскую грудь вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и умолять [npc.herHim] продолжать втираться в [npc2.her] торс."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] мягко [npc2.verb(push)] [npc2.her] плоскую грудь в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать вжиматься в [npc2.her] торс.",
			
								" Мягкий [npc2.moan] доносится из ложбинки между [npc2.namePos] [npc2.lips+], и, осторожно выпячивая [npc2.her] плоскую грудь, [npc2.she] [npc2.verb(encourage)] [npc.name], чтобы продолжать скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
	
								" От восторга [npc2.Moaning], [npc2.name] [npc2.does] [npc2.her] лучше всего попытаться осторожно [npc2.verb(wrap)] [npc2.her] плоскую грудь вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и умолять [npc.herHim] просто продолжать вжиматься в [npc2.her] торс."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] грубо [npc2.verb(thrust)] [npc2.her] плоскую грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать вжиматься в [npc2.her] торс.",
			
								" [npc2.A_moan+] вырывается из ложбинки между [npc2.namePos] [npc2.lips+], и, с силой выпячивая [npc2.her] плоскую грудь, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать мощно вжимать [npc.her] [npc.cock+] вверх и вниз по [npc2.her] торсу.",
	
								" От восторга [npc2.Moaning], [npc2.name] [npc2.does] [npc2.her] лучше всего попытаться грубо [npc2.verb(wrap)] [npc2.her] плоскую грудь вокруг [npc.namePos] [npc.cock+], прежде чем сдаться и приказать [npc.herHim] просто продолжать вжиматься в [npc2.her] торс."));
						break;
				}
			}
		}
		return "";
	}
	

	public static final SexAction FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Нежный паизури";
			} else {
				return "Нежный наизури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Нежно трахать [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Нежно тереться [npc.cock+] о плоскую грудь [npc2.namePos].";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко проскальзывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] размеренно толкая [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Мягко втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] плавно толкая [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Мягко сжимая [npc2.namePos] [npc2.breasts+] вместе, [npc.name] [npc.verb(let)] издает легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко проскальзывая [npc.her] [npc.cock+] между крошечными [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] размеренно толкая [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] крошечную ложбинку [npc2.her].",

						"Мягко толкая [npc.her] [npc.cock+] в крошечную ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] плавно вгоняя [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(grind)] вверх и вниз по [npc2.her] груди.",

						"Мягко пытаясь сжать [npc2.namePos] [npc2.breastSize] [npc2.breasts] вместе, [npc.name] [npc.verb(let)] издает легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] груди."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко скользя [npc.her] [npc.cock+] по плоской груди [npc2.namePos], [npc.name] [npc.verb(start)] размеренно дергается [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(grind)] по [npc2.her] торсу.",

						"Мягко вдавливая [npc.her] [npc.cock+] в плоскую [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] плавно вгоняя [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(grind)] вверх и вниз по [npc2.her] груди.",

						"Мягко ощупывая плоскую грудь [npc2.namePos], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)] плавно качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] торсу."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Пайзури";
			} else {
				return "Найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Продолжить трахать [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Продолжить тереться о грудь [npc2.namePos].";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] энергично толкая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Жадно сжимая [npc2.namePos] [npc2.breasts+] вместе, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с жадностью [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью проскальзывая [npc.her] [npc.cock+] между крошечными [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] неистово дрыгая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] маленькую ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в крошечную ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] энергично толкая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх-вниз по [npc2.her] груди.",

						"С жадностью пытаясь сжать [npc2.namePos] [npc2.breastSize] [npc2.breasts] вместе, [npc.name] [npc.verb(let)] вырывая [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с нетерпением [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] груди."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью скользя [npc.her] [npc.cock+] по плоской груди [npc2.namePos], [npc.name] [npc.verb(start)] неистово дрыгая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(grind)] по [npc2.her] торсу.",

						"Отчаянно прижимая [npc.her] [npc.cock+] к плоской [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] энергично вколачивая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх и вниз по [npc2.her] груди.",

						"Алчно ощупывая плоскую грудь [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(start)], чтобы неистово качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] жадно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] торсу."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Грубый паизури";
			} else {
				return "Грубый найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Грубо трахать [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Грубо тереться своим [npc.cock+] о плоскую грудь [npc2.namePos].";
			}
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо вбивая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] яростно дергая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Яростно втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] грубо вколачивая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] властно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Алчно сжимая [npc2.namePos] [npc2.breasts+] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] доминантно вгоняет [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо вбивая [npc.her] [npc.cock+] между крошечными [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] миниатюрную ложбинку.",

						"Яростно втискивая [npc.her] [npc.cock+] в узкую ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] грубо вгоняя [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] властно [npc.verb(grind)] вверх-вниз по [npc2.her] груди.",

						"Алчно пытаясь сжать [npc2.namePos] [npc2.breastSize] [npc2.breasts] вместе, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] доминантно вгоняет [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] груди."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо прижимая [npc.her] [npc.cock+] к плоской груди [npc2.namePos], [npc.name] [npc.verb(start)] неистово дергает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(grind)] по торсу [npc2.her].",

						"Яростно вдавливая [npc.her] [npc.cock+] в плоскую грудь [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] грубо вгоняет [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] властно [npc.verb(grind)] вверх-вниз по [npc2.her] груди.",

						"Алчно ощупывая плоскую грудь [npc2.namePos], [npc.name] [npc.verb(let)] извлекает из [npc.a_moan+] [npc.she], который [npc.verb(start)] доминирующе качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.cock+] по торсу [npc2.her]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Пайзури";
			} else {
				return "Найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Продолжить трахать [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Продолжить тереться о плоскую грудь [npc2.namePos].";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] дергает [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] вгоняет [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Сжимая [npc2.namePos] [npc2.breasts+] вместе, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], словно [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Проскальзывая [npc.her] [npc.cock+] между крошечными [npc2.breasts] [npc2.namePos], [npc.name] [npc.verb(start)] дергается, [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] [npc.verb(fuck)] [npc2.her] миниатюрную ложбинку.",

						"Втискивая [npc.her] [npc.cock+] в крошечную ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] вгоняет [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх-вниз по [npc2.her] груди.",

						"Пытаясь сжать [npc2.namePos] [npc2.breastSize] [npc2.breasts] вместе, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], словно [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] груди."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Скользя [npc.her] [npc.cock+] по плоской груди [npc2.namePos], [npc.name] [npc.verb(start)] дергается, [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] [npc.verb(grind)] по [npc2.her] торсу.",

						"Прижимая [npc.her] [npc.cock+] к плоской [npc2.breasts] [npc2.namePos], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх и вниз по [npc2.her] груди.",

						"Ощупывая плоскую грудь [npc2.namePos], [npc.name] [npc.verb(let)] выпячивает [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(grind)] [npc.her] [npc.cock+] по торсу [npc2.her]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Жадный пайзури";
			} else {
				return "Жадный найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Жадно трахать [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Жадно тереться о плоскую грудь [npc2.namePos].";
			}
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью просовывая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] неистово толкая [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] энергично толкая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

						"Жадно сжимая [npc2.namePos] [npc2.breasts+] вместе, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с жадностью [npc.verb(fuck)] [npc2.her] ложбинку."));
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью проскальзывая [npc.her] [npc.cock+] между крошечными [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] неистово дрыгая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] маленькую ложбинку.",

						"Отчаянно втискивая [npc.her] [npc.cock+] в крошечную ложбинку между [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] энергично толкая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх-вниз по [npc2.her] груди.",

						"С жадностью пытаясь сжать [npc2.namePos] [npc2.breastSize] [npc2.breasts] вместе, [npc.name] [npc.verb(let)] вырывая [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] с нетерпением [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] груди."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С жадностью скользя [npc.her] [npc.cock+] по плоской груди [npc2.namePos], [npc.name] [npc.verb(start)] неистово дрыгая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(grind)] по [npc2.her] торсу.",

						"Отчаянно прижимая [npc.her] [npc.cock+] к плоской [npc2.namePos] [npc2.breasts], [npc.name] [npc.verb(start)] энергично вколачивая [npc.her] [npc.hips] вперед, издавая [npc.moans+], пока [npc.she] счастливо [npc.verb(grind)] вверх и вниз по [npc2.her] груди.",

						"Алчно ощупывая плоскую грудь [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(start)], чтобы неистово качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] жадно [npc.verb(grind)] [npc.her] [npc.cock+] по [npc2.her] торсу."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Сопротивляться пайзури";
			} else {
				return "Сопротивляться найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Попытаться оттянуть свой [npc.cock] от [npc2.namePos] [npc2.breasts+].";
			} else {
				return "Попытаться оттянуть свой [npc.cock] от груди [npc2.namePos].";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] вытащить [npc.her] [npc.cock+] из [npc2.namePos] декольте, но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, сдавливая [npc2.her] [npc2.breasts+] вместе и мягко напоминая [npc.herHim], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.breasts+], но [npc2.she] решительно [npc2.verb(hold)] [npc.herHim] на месте, тихо [npc2.moaning], пока [npc2.she] [npc2.verb(ignore)] [npc.her] отчаянные протесты.",

								"На [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] вытащить из [npc2.namePos] декольте, но хватка [npc2.her] слишком сильна, и [npc2.she] тихо [npc2.verb(continue)] [npc2.moaning], пока [npc2.she] решительно [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] вытащить [npc.her] [npc.cock+] из [npc2.namePos] декольте, но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, сдавливая [npc2.her] [npc2.breasts+] вместе и рыча, что [npc2.she] будет использовать [npc.herHim] так, как [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.breasts+], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, [npc.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"На глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] из ложбинки [npc2.namePos], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.breasts+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock+] из ложбинки [npc2.namePos], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, сдавливая [npc2.her] [npc2.breasts+] вместе, пока [npc2.moaning], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.breasts+], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, [npc2.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"На глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] из ложбинки [npc2.namePos], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] с нетерпением [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.breasts+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] вытащить [npc.her] [npc.cock+] из той узкой ложбинки, что [npc2.name] [npc2.has], но [npc2.she] решительно [npc2.verb(hold)] [npc.herHim] на месте, пытаясь сжать [npc2.her] [npc2.breasts+] вместе и мягко напоминая [npc.herHim], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.breastSize] [npc2.breasts], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, тихо [npc2.moaning], пока [npc2.she] [npc2.verb(ignore)] отчаянные протесты [npc.her].",

								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] вытащить из той крошечной ложбинки, что есть у [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] тихо [npc2.verb(continue)] [npc2.moaning], пока [npc2.she] решительно [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] вытащить [npc.her] [npc.cock+] из той узкой ложбинки, что [npc2.name] [npc2.has], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, пытаясь сжать [npc2.her] [npc2.breasts+] вместе и рыча, что [npc2.she] использует [npc.herHim] так, как [npc2.she] [npc2.verb(want)].",

								"[npc.Name] отчаянно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.breastSize] [npc2.breasts], но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, [npc.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"В [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)], пытаясь вырваться из той крошечной ложбинки, что [npc2.name] могут предложить, но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.breasts+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] из той небольшой ложбинки, которой [npc2.name] [npc2.has], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, пытаясь сдавить [npc2.her] [npc2.breasts+] вместе, при этом [npc2.moaning], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] [npc2.breastSize] [npc2.breasts], но [npc2.she] твердо [npc2.verb(hold)] [npc.herHim] на месте, [npc.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"На глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)], пытаясь вырваться из той крошечной ложбинки, что есть у [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] жадно [npc2.verb(force)] [npc.her] [npc.cock+] между [npc2.her] [npc2.breasts+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] оттянуть [npc.her] [npc.cock+] от плоской груди [npc2.namePos], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, вжимаясь в [npc.herHim], пока [npc2.she] мягко [npc2.moanVerb], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от груди [npc2.namePos], но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, тихо [npc2.moaning], пока [npc2.she] [npc2.verb(ignore)] отчаянные протесты [npc.her].",

								"На глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)], пытаясь отстраниться от [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] тихо [npc2.verb(continue)] [npc2.moaning], пока [npc2.she] решительно [npc2.verb(force)] [npc.her] [npc.cock+] против груди [npc2.her]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] оттянуть [npc.her] [npc.cock+] от [npc2.namePos] груди, но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, с силой втираясь в [npc.herHim], пока [npc2.she] [npc2.verb(growl)], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] груди, но [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, [npc2.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"На [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] отстраниться от [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] грубо [npc2.verb(force)] [npc.namePos] [npc.cock+] о [npc2.her] грудь."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отчаянно [npc.verb(try)] оттянуть [npc.her] [npc.cock+] от [npc2.namePos] груди, но [npc2.name] крепко [npc2.verb(hold)] [npc.herHim] на месте, втираясь в [npc.herHim], пока [npc2.she] [npc2.moanVerb], что [npc2.she] сделает всё, что [npc2.she] [npc2.verb(want)].",

								"[npc.Name] лихорадочно [npc.verb(try)], пытаясь отстраниться от [npc2.namePos] груди, но [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, [npc2.moaning+], пока [npc2.she] [npc2.verb(ignore)] [npc.her] тщетные протесты.",

								"В [npc.eyes] [npc.namePos] начинают наворачиваться слезы, когда [npc.she] [npc.verb(try)] пытается отстраниться от [npc2.name], но хватка [npc2.her] слишком сильна, и [npc2.she] [npc2.verb(continue)] [npc2.moaning+], пока [npc2.she] жадно [npc2.verb(force)] [npc.her] [npc.cock+] о [npc2.her] грудь."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Прекратить пайзури";
			} else {
				return "Прекратить наизури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				return "Отстранить свой [npc.cock+] от [npc2.namePos] [npc2.breasts+] и перестать трахать их.";
			} else {
				return "Отстранить свой [npc.cock+] от груди [npc2.namePos] и перестать тереться о [npc2.herHim].";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо оттолкнув [npc2.name], [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] ложбинки и [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] с него хватит трахать [npc2.her] [npc2.breasts+].",

								"Грубо вытаскивая [npc.her] [npc.cock+] из ложбинки [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватит трахать [npc2.her] [npc2.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.namePos] ложбинки, и [npc.verb(tell)] [npc2.name], что с [npc.sheHas] хватит трахать [npc2.her] [npc2.breasts+].",

								"Вытаскивая [npc.her] [npc.cock+] из ложбинки [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.name], что [npc.sheHas] хватит трахать [npc2.her] [npc2.breasts+]."));
						break;
				}
			} else if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо отталкивая [npc2.name], [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из крошечной ложбинки [npc2.her] и [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватило ебли [npc2.her] [npc2.breastSize] [npc2.breasts].",

								"Грубо вытягивая [npc.her] [npc.cock+] из крошечной ложбинки [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim], так как [npc.sheHas] хватило ебли [npc2.her] [npc2.breastSize] [npc2.breasts]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из крошечной ложбинки [npc2.namePos] и [npc.verb(tell)] [npc2.herHim], так как [npc.sheHas] хватило ебли [npc2.her] [npc2.breastSize] [npc2.breasts].",

								"Вытягивая [npc.her] [npc.cock+] из крошечной ложбинки [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.name], так как [npc.sheHas] хватило ебли [npc2.her] [npc2.breastSize] [npc2.breasts]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо отталкивая [npc2.name], [npc.name] убирает [npc.her] [npc.cock+] от груди [npc2.her] и [npc.verb(tell)] [npc2.herHim], так как [npc.sheHas] хватило трения об [npc2.herHim].",

								"Грубо оттягивая [npc.her] [npc.cock+] от груди [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim], так как [npc.sheHas] хватило трения об [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] отрывает [npc.her] [npc.cock+] от груди [npc2.namePos] и [npc.verb(tell)] [npc2.herHim], словно [npc.sheHas] уже хватило трения о [npc2.herHim].",

								"Оттягивая [npc.her] [npc.cock+] от груди [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim], что [npc.sheHas] хватило трения об [npc2.herHim]."));
						break;
				}
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)] борется с [npc.herHim], [npc2.moaning+], пока [npc2.she] [npc2.verb(beg)] [npc.herHim], чтобы оставить [npc2.herHim] в покое.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] [npc.herHim] оставить [npc2.herHim] в покое, слезы наворачиваются на [npc2.her] [npc2.eyes], пока [npc2.she] слабо [npc2.verb(try)] оттолкнуть [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], предавая желание [npc2.her], чтобы [npc.herHim] продолжал.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(beg)], чтобы [npc.herHim] продолжала использовать [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Partner actions:
	
	public static final SexAction USING_COCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Сделать пайзури";
			} else {
				return "Сделать найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Использовать [npc2.namePos] [npc2.cock+], чтобы трахать [npc.namePos] [npc.breasts+].";
			} else {
				return "Использовать [npc2.namePos] [npc2.cock+], чтобы тереться о твою грудь.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] вместе, [npc.verb(start)] устраивая [npc2.herHim] титфак."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] вместе, [npc.verb(start)] устраивая [npc2.herHim] страстный титфак."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(pull)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] вместе, [npc.verb(start)] устраивая [npc2.herHim] мощный титфак."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] вместе, [npc.verb(start)] устраивая [npc2.herHim] титфак."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] её к [npc.her] ложбинке, и, скользя вперед, [npc.she] [npc.verb(press)] [npc.her] [npc.breasts+] вместе, [npc.verb(start)] устраивая [npc2.herHim] страстный титфак."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ радостный маленький [npc2.moan], помогая прижимать [npc.namePos] [npc.breasts] друг к другу, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], с энтузиазмом помогая прижимать [npc.namePos] [npc.breasts] друг к другу, пока [npc2.she] восторженно [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], грубо прижимая [npc.namePos] [npc.breasts] друг к другу, пока [npc2.she] [npc2.verb(demand)], чтобы [npc.she] продолжались."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], с энтузиазмом помогая прижимать [npc.namePos] [npc.breasts] друг к другу, пока [npc2.she] восторженно [npc2.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan], помогая прижимать [npc.namePos] [npc.breasts] друг к другу, пока [npc2.she] робко [npc2.verb(ask)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], слабо пытаясь оттолкнуть [npc.name], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] остановился."));
							break;
						default:
							break;
					}
				}
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] их вверх, насколько позволяет небольшое декольте [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her], стараясь изо всех сил прижать [npc.her] [npc.breastSize] [npc.breasts] друг к другу, чтобы устроить [npc2.herHim] титфак."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью хватая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к тому небольшому декольте, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her], стараясь прижать [npc.her] [npc.breastSize] [npc.breasts] друг к другу, чтобы устроить [npc2.herHim] страстный титфак."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо хватая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к тому небольшому декольте, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her], стараясь прижать [npc.her] [npc.breastSize] [npc.breasts] друг к другу, чтобы устроить [npc2.herHim] мощный титфак."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к тому небольшому декольте, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her], стараясь прижать [npc.her] [npc.breastSize] [npc.breasts] друг к другу, чтобы устроить [npc2.herHim] титфак."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью хватая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к тому небольшому декольте, что [npc.she] [npc.has], и, скользя вперед, [npc.she] [npc.verb(try)] [npc.her], стараясь прижать [npc.her] [npc.breastSize] [npc.breasts] друг к другу, чтобы устроить [npc2.herHim] страстный титфак."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ маленький счастливый [npc2.moan], мягко толкаясь в [npc.namePos] грудь, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim] продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, отчаянно втискивая его в [npc.namePos] грудь, пока [npc2.she] с энтузиазмом [npc.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, грубо втискивая его в [npc.namePos] грудь, пока [npc2.she] [npc2.verb(demand)], чтобы [npc.herHim] не прекращалось."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, жадно втискивая его в [npc.namePos] грудь, пока [npc2.she] с энтузиазмом [npc.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan] в ответ, втискивая его в [npc.namePos] грудь, пока [npc2.she] кротко [npc2.verb(ask)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, слабо пытаясь оттолкнуть [npc.name], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] прекратилось."));
							break;
						default:
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к плоской груди [npc.her] и, скользя вперед, [npc.she] с энтузиазмом [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к плоской груди [npc.her] и, скользя вперед, [npc.she] с энтузиазмом [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к [npc.her] плоской груди, и, скользя вперед, [npc.she] с силой [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к [npc.her] плоской груди, и, скользя вперед, [npc.she] [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Жадно обхватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(guide)] его к плоской груди [npc.her] и, скользя вперед, [npc.she] с энтузиазмом [npc.verb(grind)] [npc.her] торс против [npc2.her] [npc2.cock+]."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] в ответ маленький счастливый [npc2.moan], мягко толкаясь в [npc.namePos] грудь, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim] продолжать."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, отчаянно втискивая его в [npc.namePos] грудь, пока [npc2.she] с энтузиазмом [npc.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, грубо втискивая его в [npc.namePos] грудь, пока [npc2.she] [npc2.verb(demand)], чтобы [npc.herHim] не прекращалось."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, жадно втискивая его в [npc.namePos] грудь, пока [npc2.she] с энтузиазмом [npc.verb(encourage)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan] в ответ, втискивая его в [npc.namePos] грудь, пока [npc2.she] кротко [npc2.verb(ask)] [npc.herHim], чтобы продолжать."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, слабо пытаясь оттолкнуть [npc.name], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] прекратилось."));
							break;
						default:
							break;
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.breasts+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда между [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Не сумев оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.breasts+], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
			
								" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.cock+] двигаться вперед-назад между [npc.her] [npc.breasts+].",
			
								" [npc2.Sobbing] в смятении, [npc2.name] [npc2.verb(try)], тщетно пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] между [npc.namePos] [npc.breasts+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" Тихий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] плавно вгоняет [npc2.her] в [npc2.cock+] между [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] вперед-назад между [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.breasts+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо вбивает [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] вперед-назад между [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.breasts+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(fuck)] [npc.her] ложбинку.",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко между [npc.namePos] [npc.breasts+].",
										
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] взад-вперед между [npc.namePos] [npc.breasts+]."));
						break;
				}
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] в ту малую ложбинку, что [npc.name] [npc.has] может предложить, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает толчками вгонять [npc2.her] [npc2.cock+] между [npc.namePos] [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке, образовавшейся между [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Не сумев оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.breasts+], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
			
								" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.cock+] двигаться вперед-назад между [npc.her] [npc.breasts+].",
			
								" [npc2.Sobbing] в смятении, [npc2.name] [npc2.verb(try)], тщетно пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] в ту малую ложбинку, что [npc.name] [npc.has] может предложить, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" Тихий [npc2.moan] доносится из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает мягко вгонять [npc2.her] [npc2.cock+] между [npc.namePos] [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке, образовавшейся между [npc.namePos] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] в ту крошечную ложбинку, которую [npc.name] [npc.has] может предложить, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо впечатывает [npc2.her] [npc2.cock+] между [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке, образовавшейся между [npc.namePos] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] в ту крошечную ложбинку, которую [npc.name] [npc.has] может предложить, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(fuck)] [npc.her] [npc.breasts+].",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] между [npc.breastSize] [npc.breasts].",
										
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.cock+] туда-сюда в крошечной ложбинке, образовавшейся между [npc.namePos] [npc.breasts+]."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по плоской груди [npc.namePos], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(grind)] по [npc.her] торсу.",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает двигать [npc2.her] [npc2.cock+] вверх и вниз по плоской груди [npc.namePos].",
										
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(grind)] [npc2.her] [npc2.cock+] по плоской груди [npc.namePos]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Не в силах оттащить [npc2.her] [npc2.cock] от плоской груди [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
			
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.cock+] двигаться вперед-назад по плоской груди [npc.her].",
			
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.cock] от плоской груди [npc.namePos]."));
						break;
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по плоской груди [npc.namePos], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(grind)] против торса [npc.her].",
			
								" Тихий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает нежно двигать [npc2.her] [npc2.cock+] вверх и вниз по плоской груди [npc.namePos].",
										
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(grind)] [npc2.her] [npc2.cock+] по плоской груди [npc.namePos]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] неистово [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по [npc.namePos] плоской груди, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(grind)] об [npc.her] торс.",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо вколачивает [npc2.her] [npc2.cock+] вверх и вниз по [npc.namePos] плоской груди.",
										
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(grind)] [npc2.her] [npc2.cock+] по [npc.namePos] плоской груди."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] по [npc.namePos] плоской груди, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(grind)] об [npc.her] торс.",
			
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает двигать [npc2.her] [npc2.cock+] вверх и вниз по плоской груди [npc.namePos].",
										
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.cock+] по [npc.namePos] плоской груди."));
						break;
				}
			}
		}
		return "";
	}
	
	public static final SexAction PERFORMING_COCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Мягко делать пайзури";
			} else {
				return "Мягко делать найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Мягко ласкать [npc2.namePos] [npc2.cock+] своим [npc.breasts+].";
			} else {
				return "Мягко ласкать [npc2.namePos] [npc2.cock+] своей грудью.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Протягивая руки вверх, чтобы сжать [npc.her] [npc.breasts+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, тихо [npc.moaning], пока [npc.she] [npc.verb(use)] [npc.her] ложбинку.",

						"Мягко обхватив [npc.her] [npc.breasts+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] медленно [npc.verb(lift)] их вверх и вниз, издавая тихий [npc.moan], пока [npc.she] с любовью [npc.verb(give)] [npc2.herHim] титфак.",

						"Издавая тихий [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] вместе, окутывая [npc2.namePos] [npc2.cock+] [npc.her] подушкообразными холмиками, пока [npc.she] [npc.verb(give)] [npc2.herHim] нежный титфак."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы прижать [npc.her] [npc.breasts+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, тихо [npc.moaning], пока [npc.she] [npc.verb(try)] [npc.her] изо всех сил использовать то немногое декольте, что есть у [npc.she] [npc.has].",

							"Мягко прижимая [npc.her] [npc.breastSize] [npc.breasts] к бокам [npc2.namePos] [npc2.cock+], [npc.name] медленно [npc.verb(lift)] их вверх и вниз, издавая тихий [npc.moan], пока [npc.she] с любовью [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Издавая тихий [npc.moan], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] вместе, стараясь [npc.her] изо всех сил доставить удовольствие [npc2.namePos] [npc2.cock+] с помощью того малого количества декольте, что есть у [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки, чтобы обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, тихо [npc.moaning], пока [npc.she] [npc.verb(thrust)] [npc.her] плоскую грудь и [npc.verb(grind)] против [npc2.herHim].",

							"Мягко обхватывая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] торс вверх и вниз, притирая [npc.her] плоскую грудь к [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя титфак для [npc2.herHim].",

							"Издавая тихий [npc.moan], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], прежде чем выпятить [npc.her] плоскую грудь и устроить [npc2.herHim] имитацию титфака."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Сделать пайзури";
			} else {
				return "Сделать найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Дари [npc2.namePos] [npc2.cock+] своей [npc.breasts+].";
			} else {
				return "Дари [npc2.namePos] [npc2.cock+] своей плоской грудью.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С радостью протянув руки, чтобы сжать [npc.her] [npc.breasts+] вместе вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] ложбинку, чтобы устроить [npc2.herHim] жадный титфак.",

						"С энтузиазмом обхватывая [npc.her] [npc.breasts+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] страстный титфак.",

						"Издавая [npc.a_moan+], [npc.name] счастливо [npc2.verb(push)] [npc.her] [npc.breasts+] вместе, окутывая [npc2.namePos] [npc2.cock+] [npc.her] мягкими холмиками, пока [npc.she] [npc.verb(give)] [npc2.herHim] в жадном титфаке."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Подняв руки, чтобы с радостью прижать [npc.her] [npc.breasts+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], стараясь максимально использовать ту небольшую ложбинку, что [npc.she] [npc.has], пока [npc.she] [npc.verb(try)] [npc.her].",

							"С жадностью прижимая [npc.her] [npc.breastSize] [npc.breasts] к бокам [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] с энтузиазмом [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Издавая [npc.a_moan+], [npc.name] счастливо [npc2.verb(push)] [npc.her] [npc.breasts+] вместе, стараясь изо всех [npc.her] сил доставить удовольствие [npc2.namePos] [npc2.cock+] тем крошечным количеством декольте, что [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Подняв руки, чтобы счастливо обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(thrust)] из [npc.her] плоской груди отчаянно прижимается к [npc2.herHim].",

							"С жадностью обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] [npc.her] торс вверх и вниз, притирая [npc.her] плоскую грудь к [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] счастливо [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], после чего выпячивая [npc.her] плоскую грудь и с жадностью устраивая [npc2.herHim] имитацию титфака."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Грубо делать пайзури";
			} else {
				return "Грубо делать найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Грубо доставлять [npc2.namePos] [npc2.cock+] с помощью твоих [npc.breasts+].";
			} else {
				return "Грубо доставлять [npc2.namePos] [npc2.cock+] своей плоской грудью.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Протягивая руки вверх, чтобы грубо сжать [npc.her] [npc.breasts+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] стремительно [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] ложбинку, чтобы устроить [npc2.herHim] доминантный титфак.",

						"Доминантно обхватив [npc.her] [npc.breasts+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(bounce)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] мощный титфак.",

						"Издавая [npc.a_moan+], [npc.name] с силой [npc2.verb(push)] [npc.her] [npc.breasts+] вместе, окутывая [npc2.namePos] [npc2.cock+] [npc.her] мягкими холмиками, пока [npc.she] [npc.verb(give)] [npc2.herHim] доминантный титфак."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы грубо прижать [npc.her] [npc.breasts+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] стремительно [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торса, пока [npc.moaning+] изо всех сил [npc.she] [npc.verb(try)] [npc.her], используя ту малую ложбинку, что [npc.she] [npc.has].",

							"Доминантно прижимая [npc.her] [npc.breastSize] [npc.breasts] к бокам [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(attempt)], чтобы устроить [npc2.herHim] мощный титфак.",

							"Letting out [npc.a_moan+], [npc.name] forcefully [npc2.verb(push)] [npc.her] [npc.breasts+] together, trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руки вверх, чтобы грубо обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] неистово [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торса, пока [npc.moaning+] [npc.she] [npc.verb(thrust)] из [npc.her] плоской груди, чтобы доминантно тереться об [npc2.herHim].",

							"Доминантно обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(lift)] [npc.her] торс вверх и вниз, втирая [npc.her] плоскую грудь в [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя титфак [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] с силой [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], прежде чем выпятить [npc.her] плоскую грудь и грубо имитировать титфак [npc2.herHim]."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Сделать пайзури";
			} else {
				return "Сделать найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Дари [npc2.namePos] [npc2.cock+] своей [npc.breasts+].";
			} else {
				return "Дари [npc2.namePos] [npc2.cock+] своей плоской грудью.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Протягивая руки, чтобы сжать [npc.her] [npc.breasts+] вместе вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] декольте, чтобы устроить [npc2.herHim] титфак.",

						"Обхватив [npc.her] [npc.breasts+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] титфак.",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] вместе, окутывая [npc2.namePos] [npc2.cock+] в [npc.her] мягкие холмики, пока [npc.she] [npc.verb(give)] [npc2.herHim] титфак."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С радостью потянувшись вверх, чтобы прижать [npc.her] [npc.breasts+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торса, [npc.moaning+], пока [npc.she] [npc.verb(try)] [npc.her], стараясь использовать ту малую ложбинку, что [npc.she] [npc.has].",

							"Прижимая [npc.her] [npc.breastSize] [npc.breasts] к бокам [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Letting out [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.breasts+] together, trying [npc.her] best to pleasure [npc2.namePos] [npc2.cock+] with the tiny amount of cleavage [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С радостью потянувшись вверх, чтобы обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торса, [npc.moaning+], пока [npc.she] [npc.verb(thrust)] вытягивает [npc.her] плоскую грудь, чтобы тереться об [npc2.herHim].",

							"Оборачивая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(lift)] [npc.her] торса вверх и вниз, втирая [npc.her] плоскую грудь в [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя титфак для [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], а затем выпячивая [npc.her] плоскую грудь и устраивая [npc2.herHim] имитацию титфака"));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PERFORMING_COCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "С энтузиазмом заняться пайзури";
			} else {
				return "С энтузиазмом заняться найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "С энтузиазмом ласкать [npc2.namePos] [npc2.cock+] своей [npc.breasts+].";
			} else {
				return "С энтузиазмом ласкать [npc2.namePos] [npc2.cock+] своей плоской грудью.";
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С радостью протянув руки, чтобы сжать [npc.her] [npc.breasts+] вместе вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] ложбинку, чтобы устроить [npc2.herHim] жадный титфак.",

						"С энтузиазмом обхватывая [npc.her] [npc.breasts+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(give)] [npc2.herHim] страстный титфак.",

						"Издавая [npc.a_moan+], [npc.name] счастливо [npc2.verb(push)] [npc.her] [npc.breasts+] вместе, окутывая [npc2.namePos] [npc2.cock+] [npc.her] мягкими холмиками, пока [npc.she] [npc.verb(give)] [npc2.herHim] в жадном титфаке."));
				
			} else {
				if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Подняв руки, чтобы с радостью прижать [npc.her] [npc.breasts+] к бокам [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], стараясь максимально использовать ту небольшую ложбинку, что [npc.she] [npc.has], пока [npc.she] [npc.verb(try)] [npc.her].",

							"С жадностью прижимая [npc.her] [npc.breastSize] [npc.breasts] к бокам [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] их вверх и вниз, издавая [npc.a_moan+], пока [npc.she] с энтузиазмом [npc.verb(attempt)], чтобы устроить [npc2.herHim] титфак.",

							"Издавая [npc.a_moan+], [npc.name] счастливо [npc2.verb(push)] [npc.her] [npc.breasts+] вместе, стараясь изо всех [npc.her] сил доставить удовольствие [npc2.namePos] [npc2.cock+] тем крошечным количеством декольте, что [npc.she] [npc.has]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Подняв руки, чтобы счастливо обхватить [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] с энтузиазмом [npc.verb(raise)] и [npc.verb(lower)] [npc.her] торс, [npc.moaning+], пока [npc.she] [npc.verb(thrust)] из [npc.her] плоской груди отчаянно прижимается к [npc2.herHim].",

							"С жадностью обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] энергично [npc.verb(lift)] [npc.her] торс вверх и вниз, притирая [npc.her] плоскую грудь к [npc2.herHim], пока [npc.she] [npc.verb(try)], имитируя [npc2.herHim].",

							"Издавая [npc.a_moan+], [npc.name] счастливо [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], после чего выпячивая [npc.her] плоскую грудь и с жадностью устраивая [npc2.herHim] имитацию титфака."));
				}
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Сопротивляться пайзури";
			} else {
				return "Сопротивляться найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Попытаться оттянуть свою [npc.breasts+] от [npc2.namePos] [npc2.cock+].";
			} else {
				return "Попытаться оттянуть свою плоскую грудь от [npc2.namePos] [npc2.cock+].";
			}
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] [npc.moan+], пока [npc.she] [npc.verb(try)], чтобы оттащить [npc.her] [npc.breasts+] от [npc2.namePos] [npc2.cock+], а затем умоляя [npc2.herHim] оставить [npc.herHim] в покое.",

						"С [npc.a_moan+], [npc.name] слабо [npc.verb(try)], пытаясь отстраниться от [npc2.name] и всхлипывая от горя, пока [npc2.her] [npc2.cock+] продолжает вжиматься между [npc.her] [npc.breasts+].",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim]; слезы текут по щекам [npc.her], пока [npc2.she] [npc2.verb(continue)] втискивает [npc2.her] [npc2.cock+] в ложбинку [npc.her]."));
				
			} else if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] из [npc.moan+], пока [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.breastSize] [npc.breasts] от [npc2.namePos] [npc2.cock+], прежде чем умолять [npc.herHim] оставить [npc2.herHim] в покое.",

						"С [npc.a_moan+], [npc.name] слабо [npc.verb(try)], пытаясь отстраниться от [npc2.name] и всхлипывая от горя, пока [npc2.her] [npc2.cock+] продолжают вжиматься между [npc.her] [npc.breastSize] [npc.breasts+].",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim]; слезы текут по щекам [npc.her], пока [npc2.she] [npc2.verb(continue)] втискивает [npc2.her] [npc2.cock+] в маленькую ложбинку [npc.her]."));
						
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] из [npc.moan+], пока [npc.she] [npc.verb(try)], пытаясь оттянуть плоскую грудь [npc.her] от [npc2.namePos] [npc2.cock+], прежде чем умолять [npc.herHim] оставить [npc2.herHim] в покое.",

						"С [npc.a_moan+], [npc.name] слабо [npc.verb(try)], пытаясь отстраниться от [npc2.name], всхлипывая от горя, пока [npc2.her] [npc2.cock+] продолжает тереться о плоскую грудь [npc.her].",

						"Издавая [npc.a_moan+], [npc.name] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim]; слезы текут по щекам [npc.her], пока [npc2.she] [npc2.verb(continue)] вгоняет [npc2.her] [npc2.cock+] в торс [npc.her]."));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Прекратить пайзури";
			} else {
				return "Прекратить найзури";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
				return "Оттолкнуть [npc2.namePos] [npc2.cock] от своей [npc.breasts+].";
			} else {
				return "Оттолкнуть [npc2.namePos] [npc2.cock] от своей плоской груди.";
			}
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().hasBreasts()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] грубо [npc.verb(push)] [npc2.name] от [npc.herHim] и угрожающим тоном [npc.verb(order)] [npc2.herHim], чтобы тот перестал трахать [npc.her] [npc.breasts+].",

								"С угрожающим рычанием, [npc.name] грубо [npc.verb(push)] [npc2.name] прочь, и [npc.verb(order)] [npc2.herHim], чтобы тот перестал трахать [npc.her] [npc.breasts+]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] от [npc.herHim], и [npc.verb(tell)] [npc2.herHim], чтобы тот перестал трахать [npc.her] [npc.breasts+].",

								"С последним [npc.moan], [npc.name] [npc.verb(push)] [npc2.name] прочь, и [npc.verb(tell)] [npc2.herHim], чтобы тот перестал трахать [npc.her] [npc.breasts+]."));
						break;
				}
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] грубо [npc.verb(push)] [npc2.name] от [npc.herHim] и угрожающим тоном [npc.verb(order)] [npc2.herHim], чтобы перестать тереться о грудь [npc.her].",

								"С угрожающим рычанием [npc.name] грубо [npc.verb(push)] [npc2.name], и [npc.verb(order)] [npc2.herHim], чтобы перестать тереться о грудь [npc.her]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(push)] [npc2.name] от [npc.herHim] и [npc.verb(tell)] [npc2.herHim], чтобы перестать тереться о грудь [npc.her].",

								"С последним [npc.moan], [npc.name] [npc.verb(push)] [npc2.name], и [npc.verb(tell)] [npc2.herHim], чтобы перестать тереться о грудь [npc.her]."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)], борясь с [npc.herHim], [npc2.moaning+], пока [npc2.she] [npc2.verb(beg)] [npc.name], чтобы оставить [npc2.herHim] в покое.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(beg)] [npc.name], чтобы оставить [npc2.herHim] в покое; в [npc2.her] [npc2.eyes] наворачиваются слезы, пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может не [npc2.verb(help)], лишь издавая [npc2.a_moan+], выдающий [npc2.her] желание получить еще больше внимания от [npc.namePos].",
	
								" С [npc2.a_moan+], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал использовать [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
