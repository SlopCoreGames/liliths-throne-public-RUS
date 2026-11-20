package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.4.11.2
 * @author Innoxia
 */
public class PartnerTalk {
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					default:
						return Main.sex.getCharacterPerformingAction().isFeminine()?"Похотливый стон":"Похотливый стон";
					case DOM_ROUGH:
						return "Грубое рычание";
					case SUB_RESISTING:
						return "Протестующий крик";
				}
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				default:
					return "Грязные разговоры";
				case DOM_ROUGH:
					return "Грубые ласки словами";
				case SUB_RESISTING:
					return "Умолять остановиться";
			}
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					default:
						return UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] забит, всё, что [npc.she] может вместо грязных слов для [npc2.name] — это издавать серию похотливых, приглушенных [npc.moans].",
								"Поскольку рот [npc.her] сейчас забит, [npc.nameIsFull] может лишь издавать серию приглушенных, похотливых [npc.moans], чтобы дать [npc2.name] понять, что [npc.sheIs] наслаждается [npc.herself].",
								"Желая дать [npc2.name] понять, что [npc.sheIs] наслаждается [npc.herself], но из-за того, что рот [npc.her] забит, [npc.name] [npc.verb(make)] лишь издает серию невероятно похотливых, приглушенных [npc.moans].",
								"Хотя рот [npc.her] забит, [npc.nameIsFull] всё же может издавать серию похотливых, приглушенных [npc.moans], давая [npc2.name] понять, что [npc.sheIs] наслаждается [npc.herself].");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] забит, всё, что [npc.she] может вместо грубых слов сказать [npc2.name], — это издать серию агрессивных, приглушенных рыков.",
								"Поскольку рот [npc.her] сейчас забит, [npc.nameIsFull] остается только издавать серию приглушенных, грубых рыков, чтобы дать [npc2.name] понять, что [npc.sheIs] всё еще здесь главный.",
								"Желая дать [npc2.name] понять, что [npc.sheIs] всё еще главный, но при этом имея забитый рот [npc.her], [npc.name] [npc.verb(make)] лишь издает серию угрожающих, приглушенных рыков.",
								"Хотя рот [npc.her] забит, [npc.nameIsFull] всё же может издать серию глубоких, приглушенных рыков, давая [npc2.name] понять, что [npc.sheIs] всё еще главный.");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] забит, всё, что [npc.she] может вместо мольбы о пощаде, — это издавать серию жалких скулящих звуков и приглушенных всхлипов.",
								"Поскольку рот [npc.her] сейчас забит, [npc.nameIsFull] может лишь издавать серию приглушенных рыданий и страдальческого скулежа, чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] нужно отпустить.",
								"Желая дать [npc2.name] понять, что [npc.she] хочет, чтобы его освободили, но при этом имея забитый рот [npc.her], [npc.name] [npc.verb(make)] лишь издает серию полных страдания рыданий и приглушенных всхлипов.",
								"Хотя рот [npc.her] заблокирован, [npc.nameIsFull] все же удается издавать серию жалобных стонов и приглушенных всхлипов, давая [npc2.name] понять, что [npc.she] [npc.verb(want)], чтобы это прекратилось.");
				}
			}

			if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return UtilText.returnStringAtRandom(
						"Не желая будить [npc2.name], [npc.name] тихо [npc.verb(tease)], ",
						"Стараясь говорить [npc.her] тише, чтобы не разбудить [npc2.name], [npc.name] [npc.verb(whisper)], ")
						+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
			}
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Повернув [npc.her] голову, чтобы оглянуться на [npc2.name], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], ",
								"[npc.Name] поворачивает [npc.her] голову, чтобы оглянуться на [npc2.name], издавая [npc.a_moan+], прежде чем позвать, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Отчаянно пытаясь уползти от [npc2.name], [npc.name] [npc.sobsVerb+], пока [npc2.she] [npc2.verb(grab)] [npc.her] [npc.hips+] и тянет [npc.herHim] обратно, ",
								"Пытаясь уползти от [npc2.name], [npc.name] издает [npc.a_sob+], пока [npc2.she] [npc2.verb(hold)] [npc.herHim] на месте, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"Повернув [npc.her] голову, чтобы оглянуться на [npc2.name], [npc.a_moan] вырывается из между [npc.namePos] [npc.lips+], ",
								"[npc.Name] поворачивает [npc.her] голову, чтобы оглянуться на [npc2.name], издавая [npc.a_moan], прежде чем позвать, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				if(Main.sex.getCharacterPerformingAction().isTaur()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							return UtilText.returnStringAtRandom(
									"[npc.Name] тихо [npc.verb(call)] [npc2.name], пока [npc2.she] [npc2.verb(kneel)] под [npc.herHim], ",
									"Когда [npc2.name] опускается на колени перед диким телом [npc.her] ([npc.legRace]), [npc.name] тихо вздыхает, ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						case DOM_ROUGH:
							return UtilText.returnStringAtRandom(
									"[npc.Name] [npc.moansVerb+] [npc2.name], пока [npc2.she] [npc2.verb(kneel)] под [npc.herHim], ",
									"Когда [npc2.name] опускается на колени перед диким телом [npc.her] ([npc.legRace]), [npc.name] [npc.moansVerb+], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						default: 
							return UtilText.returnStringAtRandom(
									"[npc.Name] [npc.moansVerb+] [npc2.name], пока [npc2.she] [npc2.verb(kneel)] под [npc.herHim], ",
									"Когда [npc2.name] опускается на колени перед диким телом [npc.her] ([npc.legRace]), [npc.name] [npc.moansVerb+], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					}
				
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
						case DOM_GENTLE:
							return UtilText.returnStringAtRandom(
									"[npc.Name] смотрит вниз на [npc2.name], пока [npc2.she] [npc2.verb(kneel)] под [npc.herHim], ",
									"Смотря на [npc2.name] сверху вниз, [npc.name] вздыхает, ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						case DOM_ROUGH:
							return UtilText.returnStringAtRandom(
									"[npc.Name] ухмыляется, глядя на [npc2.name], пока [npc2.she] [npc2.verb(kneel)] под [npc.herHim], ",
									"Ухмыляясь, глядя на [npc2.name] сверху вниз, [npc.name] [npc.moansVerb+], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
						default: 
							return UtilText.returnStringAtRandom(
									"[npc.Name] смотрит вниз на [npc2.name], пока [npc2.she] [npc2.verb(kneel)] под [npc.herHim], ",
									"Смотря на [npc2.name], [npc.name] [npc.moansVerb], ")
									+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					}
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"[npc.Name] бросает взгляд на [npc2.name], пока [npc.she] [npc.moansVerb], ",
								"Смотря на [npc2.name] снизу вверх, [npc.name] [npc.moansVerb], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"[npc.Name] бросает взгляд на [npc2.name], прежде чем издать [npc.a_sob+], ",
								"Смотря на [npc2.name] снизу вверх, [npc.name] издает [npc.a_sob+], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] бросает взгляд на [npc2.name], пока [npc.she] говорит, ",
								"Смотря на [npc2.name] снизу вверх, [npc.name] говорит, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] смотрит на [npc2.name], пока [npc2.she] [npc2.verb(lie)] под [npc.herHim], и говорит сверху вниз на [npc2.herHim], ",
								"Смотря на [npc2.name] снизу вверх, пока [npc2.she] [npc2.verb(lie)] под [npc.herHim], [npc.name] говорит сверху вниз на [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] смотрит на [npc2.name], пока [npc2.she] [npc2.verb(lie)] под [npc.herHim], и рычит сверху вниз на [npc2.herHim], ",
								"Глядя вниз на [npc2.name], пока [npc2.she] [npc2.verb(lie)] под [npc.herHim], [npc.name] рычит на [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: 
						return UtilText.returnStringAtRandom(
								"[npc.Name] смотрит вниз на [npc2.name], пока [npc2.she] [npc2.verb(lie)] под [npc.herHim], и [npc.moansVerb] на [npc2.herHim], ",
								"Глядя вниз на [npc2.name], пока [npc2.she] [npc2.verb(lie)] под [npc.herHim], [npc.name] [npc.moansVerb] на [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"[npc.Name] издает тихий [npc.moan], ",
								"[npc.Name] нежно вздыхает, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"[npc.Name] издает хриплый рык, прежде чем заговорить, ",
								"[npc.Name] издает угрожающее рычание, прежде чем заговорить, ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Отчаянный [npc.namePos] вырывается из-за [npc.lips+] [npc.moan], ",
								"[npc.Name] издает отчаянный [npc.moan], прежде чем обратиться к [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Протестующий скулеж вырывается из [npc.lips+] [npc.namePos], пока [npc.she] пытается вырваться из [npc2.name], ",
								"[npc.Name] издает жалобный скулящий звук, когда [npc.she] пытается отстраниться от [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.A_moan] вырывается из объятий [npc.namePos] и [npc.lips+], ",
								"[npc.Name] издает [npc.a_moan], прежде чем обратиться к [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.sex.getCharacterPerformingAction());
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
				}
				
			} else {
				if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			}

			return null;
		}
	};
	
}
