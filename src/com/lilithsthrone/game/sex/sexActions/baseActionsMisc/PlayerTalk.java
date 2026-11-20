package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
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
public class PlayerTalk {
	
	public static final SexAction DIRTY_TALK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
				return CorruptionLevel.ZERO_PURE;
			} else {
				return CorruptionLevel.ONE_VANILLA;
			}
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					default:
						return Main.sex.getCharacterPerformingAction().isFeminine()?"Похотливый стон":"Похотливый стон";
					case DOM_ROUGH:
						return "Грубое рычание";
					case SUB_RESISTING:
						return "Протестующий крик";
				}
			}
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
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
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					default:
						return "Поскольку твой рот занят, ты не можешь шептать грязности [npc2.name], но ты все еще <i>можешь</i> издавать похотливые звуки "+(Main.sex.getCharacterPerformingAction().isFeminine()?"moan":"groan")+" для [npc2.herHim].";
					case DOM_ROUGH:
						return "Поскольку твой рот занят, ты не можешь отпускать грубые сексуальные комментарии в адрес [npc2.name], но ты все еще <i>можешь</i> рычать на [npc2.herHim].";
					case SUB_RESISTING:
						return "Поскольку твой рот занят, ты не можешь умолять [npc2.herHim] перестать использовать тебя, но ты все еще <i>можешь</i> издавать протестующие стоны дискомфорта.";
				}
			}
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				default:
					return "Шептать грязности [npc2.name].";
				case DOM_ROUGH:
					return "Грубо разговаривать с [npc2.name].";
				case SUB_RESISTING:
					return "Умолять [npc2.herHim] перестать использовать тебя.";
			}
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
						+ Main.sex.getDirtyTalk(Main.game.getPlayer());
			}
			
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.ALL_FOURS)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Повернув голову, чтобы оглянуться на [npc2.name], ты чувствуешь, как [npc.a_moan+] вырывается из твоих [npc.lips+], ",
								"Ты поворачиваешь голову, чтобы оглянуться на [npc2.name], издавая [npc.a_moan+], прежде чем позвать, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Отчаянно пытаясь уползти от [npc2.name], [npc2.she] хватает тебя за [npc.hips] и тянет назад, пока ты [npc.sob], ",
								"Вы пытаетесь уползти от [npc2.name], но [npc2.she] крепко удерживает вас на месте, заставляя издать [npc.a_sob+], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"Повернув голову, чтобы оглянуться на [npc2.name], [npc.a_moan] выскальзывает из ваших [npc.lips+], ",
								"Вы поворачиваете голову, чтобы оглянуться на [npc2.name], издавая [npc.a_moan], прежде чем окликнуть, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"Вы смотрите вниз на [npc2.name], пока [npc2.she] стоит перед вами на коленях, ",
								"Глядя вниз на [npc2.name], вы вздыхаете, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"Вы ухмыляетесь, глядя на [npc2.name], пока [npc2.she] стоит перед вами на коленях, ",
								"Глядя вниз на [npc2.name], вы рычите, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"Вы смотрите вниз на [npc2.name], пока [npc2.she] стоит перед вами на коленях, ",
								"Глядя вниз на [npc2.name], вы [npc.moan], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Вы бросаете взгляд на [npc2.name], пока [npc.moan] смотрите вверх на [npc2.herHim], ",
								"Глядя снизу вверх на возвышающуюся над тобой [npc2.name], ты [npc.moan] на [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Ты бросаешь взгляд на [npc2.name], прежде чем издать [npc.a_sob+], ",
								"Глядя снизу вверх на возвышающуюся над тобой [npc2.name], ты издаешь [npc.a_sob+], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"Ты бросаешь взгляд на [npc2.name], когда обращаешься к [npc2.herHim], ",
								"Глядя снизу вверх на возвышающуюся над тобой [npc2.name], ты обращаешься к [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.game.getPlayer()).hasTag(SexSlotTag.SIXTY_NINE)) {
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"Ты оглядываешься на [npc2.name], пока [npc2.she] лежит под тобой, и говоришь снизу вверх [npc2.herHim], ",
								"Оглядываясь на [npc2.name], пока [npc2.she] лежит под тобой, ты говоришь снизу вверх [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"Ты оглядываешься на [npc2.name], пока [npc2.she] лежит под тобой, и рычишь снизу вверх [npc2.herHim], ",
								"Оглядываясь на [npc2.name], пока [npc2.she] лежит под тобой, ты рычишь снизу вверх [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: 
						return UtilText.returnStringAtRandom(
								"Ты оглядываешься на [npc2.name], пока [npc2.she] лежит под тобой, и [npc.moanVerb] снизу вверх на [npc2.herHim], ",
								"Оглядываясь на [npc2.name], пока [npc2.she] лежит под тобой, ты [npc.moanVerb] вниз, чтобы [npc2.herHim], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.game.getPlayer())) {
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								"Ты издаешь тихий [npc.moan], ",
								"Ты тихо вздыхаешь, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								"Ты издаешь грубое рычание, прежде чем заговорить вслух, ",
								"Ты издаешь угрожающее рычание, прежде чем заговорить, ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_EAGER:
						return UtilText.returnStringAtRandom(
								"Отчаянный [npc.moan] вырывается из твоих [npc.lips+], ",
								"Ты издаешь отчаянный [npc.moan], прежде чем обратиться к [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								"Протестующий скулеж вырывается из твоих [npc.lips+], пока ты борешься с [npc2.name], ",
								"Ты издаешь страдальческий скулящий звук, пытаясь отползти от [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
					default: // DOM_NORMAL, SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								"[npc.A_moan] вырывается из твоих [npc.lips+], ",
								"Вы издаете [npc.a_moan], прежде чем обратиться к [npc2.name], ")
								+ Main.sex.getDirtyTalk(Main.game.getPlayer());
				}
			
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
				}
			} else {
				if(Main.sex.getSexPace(Main.game.getPlayer())==SexPace.SUB_RESISTING) {
					return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
				}
			}
			
			return null;
		}
	};
	
	private static String getOfferResponse(boolean denied, SexPace sexPace, String areaDescription) {
		if (denied) {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] хмурится, когда вы умоляете [npc2.herHim] использовать ваш "+areaDescription+", и твердо отвечает, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Не думаю. И не проси об этом больше.)]",
											"[npc2.speech(Не смей велеть мне использовать твой "+areaDescription+", и не проси меня об этом больше!)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] хмурится, когда вы умоляете [npc2.herHim] использовать ваш "+areaDescription+", и угрожающим тоном, которым обладает [npc2.her], [npc2.she] рычит на тебя, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Не пытайся велеть мне использовать твой "+areaDescription+"! В следующий раз оставь свои предложения при себе!)]",
											"[npc2.speech(Я буду использовать любую твою часть, какую захочу! И не смей больше об этом спрашивать!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] хмурится, когда вы умоляете [npc2.herHim] использовать ваш "+areaDescription+", и с угрожающим рычанием [npc2.she] огрызается, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Заткнись, грязная шлюха! Я не собираюсь слушать какую-то сучку, которая умоляет, чтобы её [npc.her( )] "+areaDescription+" использовано!)]",
											"[npc2.speech(Я буду использовать любую твою часть, какую захочу, чертова сучка! И не смей больше об этом спрашивать!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] хмурится, когда вы умоляете [npc2.herHim] использовать ваш "+areaDescription+", и с разочарованным скулением [npc2.she] отвечает, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(На самом деле я не особо хочу использовать твою "+areaDescription+" хотя...)]",
											"[npc2.speech(Но я не хочу использовать твою "+areaDescription+"...)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] издает [npc.a_sob], пока ты умоляешь [npc2.herHim] использовать твою "+areaDescription+", и, извиваясь под тобой, [npc2.she] вскрикивает, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Пожалуйста, я не хочу! Отпусти меня уже!)]",
											"[npc2.speech(Нет! Убери свои "+areaDescription+" от меня!)]"));
					
			}
			
		} else {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь [npc2.herHim] использовать твой "+areaDescription+", прежде чем мягко [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Ну, если ты этого действительно хочешь, я использую твой "+areaDescription+", конечно...)]",
											"[npc2.speech(Ты хочешь, чтобы я использовала твой "+areaDescription+", мм? Ну, если ты настаиваешь...)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь [npc2.herHim] использовать твой "+areaDescription+", перед тем как [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Ммм, если тебе так хочется, я использую твою "+areaDescription+"!)]",
											"[npc2.speech(Ты хочешь, чтобы я использовала твой "+areaDescription+", а?! Меня это вполне устраивает!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь [npc2.herHim] использовать твой "+areaDescription+", прежде чем зарычать, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Какая грязная шлюха! Так отчаянно хочешь, чтобы тебя "+areaDescription+" трахнули, да?! Что ж, я не против подыграть!)]",
											"[npc2.speech(Грязная блядь, умоляющая о том, чтобы её "+areaDescription+" использовали! Я дам тебе то, что ты хочешь, сучка!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь [npc2.herHim] использовать твой "+areaDescription+", перед тем как [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Ммм, да, я использую твою "+areaDescription+"...)]",
											"[npc2.speech(Конечно, я буду использовать твои "+areaDescription+"!)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] издает [npc.a_sob], пока ты умоляешь [npc2.herHim] использовать твою "+areaDescription+", и, извиваясь под тобой, [npc2.she] вскрикивает, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Пожалуйста, я не хочу! Отпусти меня уже!)]",
											"[npc2.speech(Нет! Убери свои "+areaDescription+" от меня!)]"));
					
			}
		}
		return "";
	}
	
	private static String getRequestResponse(boolean denied, SexPace sexPace, String areaDescription) {
		if (denied) {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] хмурится, когда ты умоляешь использовать [npc2.her]) "+areaDescription+", и твердо отвечает, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Не думаю. И не проси об этом больше.)]",
											"[npc2.speech(Не проси меня использовать мои "+areaDescription+", и не проси меня об этом больше!)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] хмурится, когда ты умоляешь использовать [npc2.her]) "+areaDescription+", и угрожающим тоном, которым обладает [npc2.her], [npc2.she] рычит на тебя, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Только попробуй заставить меня использовать мои "+areaDescription+"! В следующий раз оставь свои предложения при себе!)]",
											"[npc2.speech(Я буду использовать те части тела, которые захочу! Не смей больше об этом просить!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] хмурится, когда ты умоляешь использовать [npc2.her]) "+areaDescription+", и с угрожающим рычанием [npc2.she] огрызается, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Заткнись, грязная шлюха! Я не собираюсь слушать какую-то сучку, которая умоляет, чтобы ее "+areaDescription+" действие!)]",
											"[npc2.speech(Я буду использовать те части тела, которые захочу, чертова ты сучка! Не смей больше об этом просить!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] хмурится, когда ты умоляешь использовать [npc2.her]) "+areaDescription+", и с разочарованным скулением [npc2.she] отвечает, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Я не особо хочу использовать свои "+areaDescription+" хотя...)]",
											"[npc2.speech(Но я не хочу использовать свои "+areaDescription+"...)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] издает [npc.a_sob], пока ты умоляешь использовать [npc2.her] "+areaDescription+", и, извиваясь под тобой, [npc2.she] вскрикивает, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Пожалуйста, я не хочу! Отпусти меня уже!)]",
											"[npc2.speech(Нет! Убери свои "+areaDescription+" от меня!)]"));
					
			}
			
		} else {
			switch(sexPace) {
				case DOM_GENTLE:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь использовать [npc2.her] "+areaDescription+", прежде чем мягко [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Конечно, я использую свои "+areaDescription+"!)]",
											"[npc2.speech(Разумеется, я использую свои "+areaDescription+"! Иди сюда...)]"));
					
				case DOM_NORMAL:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь использовать [npc2.her] "+areaDescription+", перед тем как [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Конечно, я использую свои "+areaDescription+"!)]",
											"[npc2.speech(Разумеется, я использую свои "+areaDescription+"!)]"));
					
				case DOM_ROUGH:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь использовать [npc2.her] "+areaDescription+", прежде чем зарычать, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Какая грязная шлюха! Так отчаянно жаждешь попробовать мой "+areaDescription+", а?! Что ж, я не против подыграть тебе!)]",
											"[npc2.speech(Грязная блядь, умоляющая попробовать мой "+areaDescription+"! Я дам тебе то, что ты хочешь, шлюха!)]"));
					
				case SUB_NORMAL:
				case SUB_EAGER:
					return(
							"[npc2.Name] ухмыляется, пока ты умоляешь использовать [npc2.her] "+areaDescription+", перед тем как [npc2.moaning], "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Конечно, я использую свои "+areaDescription+"!)]",
											"[npc2.speech(Разумеется, я использую свои "+areaDescription+"!)]"));
					
				case SUB_RESISTING:
					return(
							"[npc2.Name] издает [npc.a_sob], пока ты умоляешь использовать [npc2.her] "+areaDescription+", и, борясь с тобой, [npc2.she] умоляет, "
									+UtilText.returnStringAtRandom(
											"[npc2.speech(Пожалуйста, я не хочу! Отпусти меня уже!)]",
											"[npc2.speech(Нет! Убери свои "+areaDescription+" от меня!)]"));
					
			}
		}
		return "";
	}
	
	public static final SexAction PLAYER_OFFER_VAGINAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Предложить киску";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Выпятить бедра, пытаясь соблазнить [npc2.name] использовать твою киску.";
			}
			return "Сказать [npc2.name], что ты хочешь, чтобы [npc2.herHim] использовал твою киску.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] заблокирован, [npc.she] [npc.verb(resort)], выставляя [npc.her] [npc.hips] навстречу [npc2.name], пытаясь заставить [npc2.herHim] использовать [npc.her] [npc.pussy+].",
								"Поскольку рот [npc.her] заблокирован, [npc.name] [npc.verb(fall)], просто делая соблазнительное движение [npc.her] [npc.hips], чтобы завлечь [npc2.name] к использованию [npc.her] [npc.pussy+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, используй мою киску!)]",
										"[npc.speech(Используй мою маленькую киску, пожалуйста!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"киска"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_VAGINAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Попросить киску";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Придвинуться ближе к киске [npc2.namePos], пытаясь показать [npc2.herHim], что именно этого ты хочешь.";
			}
			return "Сказать [npc2.name], что ты хотел бы использовать её киску [npc2.her].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] заблокирован, [npc.she] [npc.verb(resort)] попытаться придвинуться ближе к [npc2.namePos] [npc2.pussy+], чтобы показать [npc2.herHim], что именно это интересует [npc.sheIs].",
								"Поскольку рот [npc.her] заблокирован, [npc.name] [npc.verb(fall)] возвращается к тому, чтобы просто придвинуться ближе к [npc2.namePos] [npc2.pussy+], пока [npc.she] [npc.verb(try)] указывает на то, что именно это [npc.she] [npc.verb(want)].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, я хочу использовать твою киску!)]",
										"[npc.speech(Я хочу использовать твою киску, пожалуйста!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"киска"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null));
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ANAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Предложить задницу";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Подвинуть таз назад, пытаясь соблазнить [npc2.name] использовать твою задницу.";
			}
			return "Сказать [npc2.name], что ты хочешь, чтобы [npc2.herHim] использовал(а) твою задницу.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] занят, [npc.she] [npc.verb(resort)], толкая [npc.her] [npc.hips] назад к [npc2.name], пытаясь заставить [npc2.herHim] использовать [npc.her] [npc.ass+].",
								"Поскольку рот [npc.her] занят, [npc.name] [npc.verb(fall)], просто соблазнительно подавшись назад с [npc.her] [npc.hips], чтобы завлечь [npc2.name] к использованию [npc.her] [npc.ass+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, используй мою задницу!)]",
										"[npc.speech(Используй мою задницу, пожалуйста!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"задница"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
					return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_PURE_VIRGIN);
					
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
				}
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ANAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Попросить анальный секс";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Придвинуться ближе к заднице [npc2.namePos], пытаясь показать [npc2.herHim], чего именно ты хочешь.";
			}
			return "Сказать [npc2.name], что ты хочешь, чтобы [npc2.her] использовал(а) твой анус.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] занят, [npc.she] [npc.verb(resort)], пытаясь придвинуться ближе с [npc2.namePos] [npc2.ass+], чтобы показать [npc2.herHim], что именно это интересует [npc.sheIs].",
								"Поскольку рот [npc.her] забит, [npc.name] [npc.verb(fall)] назад, просто придвигаясь ближе к [npc2.namePos] [npc2.ass+], как если бы [npc.she] [npc.verb(try)], показывая, что именно это [npc.she] [npc.verb(want)].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, я хочу использовать твою задницу!)]",
										"[npc.speech(Я хочу использовать твой анус, пожалуйста!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"задница"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null));
			}
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_ORAL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Предложить оральный секс";
		}

		@Override
		public String getActionDescription() {
			return "Скажи [npc2.name], что ты хотел бы, чтобы [npc2.herHim] использовал(а) твой рот.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
							+UtilText.returnStringAtRandom(
									"[npc.speech(Пожалуйста, используй мой рот!)]",
									"[npc.speech(Используй мой рот, пожалуйста!)]")
							+"<br/><br/>");
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"рот"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(
						new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH),
						new SexType(SexParticipantType.NORMAL, null, SexAreaPenetration.TONGUE)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.MOUTH));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_REQUEST_ORAL = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Попросить о минете";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Придвинуться ближе к рту [npc2.namePos], пытаясь показать [npc2.herHim], что именно этого ты хочешь.";
			}
			return "Скажи [npc2.name], что хочешь использовать рот [npc2.her].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.isDom(Main.game.getPlayer());
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] забит, [npc.she] [npc.verb(resort)], пытаясь придвинуться ближе к [npc2.namePos] [npc2.face], чтобы показать [npc2.herHim], что [npc.sheIs] заинтересован(а) в использовании [npc2.her] рта.",
								"Поскольку рот [npc.her] заблокирован, [npc.name] [npc.verb(fall)], просто придвигаясь ближе к [npc2.namePos] [npc2.face], пока [npc.she] [npc.verb(try)], показывая, что [npc.sheIs] [npc.verb(want)] использовать [npc2.her] рот.")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, я хочу, чтобы ты использовал свой рот!)]",
										"[npc.speech(Используй свой рот, пожалуйста!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getRequestResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"рот"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null),
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, null)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NIPPLE = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Предложить соски-секс";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Выпятить грудь, пытаясь соблазнить [npc2.name] использовать твой [npc.nipples+].";
			}
			return "Сказать [npc2.name], что ты хочешь, чтобы [npc2.herHim] использовал(а) твои [npc.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.game.getPlayer().isBreastFuckableNipplePenetration()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] заблокирован, [npc.she] [npc.verb(resort)], выпячивая [npc.her] грудь перед [npc2.name], пытаясь заставить [npc2.herHim] использовать [npc.her] [npc.nipples+].",
								"Поскольку рот [npc.her] заблокирован, [npc.name] [npc.verb(fall)], просто делая соблазнительное движение [npc.her] [npc.chest], чтобы завлечь [npc2.name] к использованию [npc.her] [npc.nipples+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, оттрахай мои соски!)]",
										"[npc.speech(Оттрахай мои соски, пожалуйста!)]")
								+"<br/><br/>");
			}
									
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"[npc.nipples]"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.NIPPLE));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_PAIZURI = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Предложить пайзури";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Выпятить грудь, пытаясь соблазнить [npc2.name] оттрахать ваши [npc.breasts+].";
			}
			return "Сказать [npc2.name], что вы хотите, чтобы [npc2.herHim] оттрахала ваши [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] занят, [npc.she] [npc.verb(resort)] выпячивать [npc.her] грудь перед [npc2.name], пытаясь заставить [npc2.herHim] использовать [npc.her] [npc.breasts+].",
								"Поскольку рот [npc.her] занят, [npc.name] [npc.verb(fall)] ограничивается лишь соблазнительным движением [npc.her] [npc.chest], чтобы завлечь [npc2.name] к использованию [npc.her] [npc.breasts+].")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, оттрахай мои сиськи!)]",
										"[npc.speech(Оттрахай мои груди, пожалуйста!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"груди"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
	
	public static final SexAction PLAYER_OFFER_NAIZURI = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Предложить наизури";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Выпятить грудь, пытаясь соблазнить [npc2.name], чтобы она прижимала [npc2.her] [npc2.cock] к вашей груди.";
			}
			return "Сказать [npc2.name], что вы хотите, чтобы [npc2.herHim] прижимала [npc2.her] [npc2.cock] к вашей груди.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& !Main.game.getPlayer().isBreastFuckablePaizuri()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.BREASTS, true)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"Поскольку рот [npc.namePos] заблокирован, [npc.she] [npc.verb(resort)], выпячивая [npc.her] грудь навстречу [npc2.name], пытаясь заставить [npc2.herHim] использовать её.",
								"Поскольку рот [npc.her] заблокирован, [npc.name] [npc.verb(fall)], просто делая соблазнительный жест [npc.her] [npc.chest], чтобы соблазнить [npc2.name] использовать её.")
						+"<br/><br/>");
			} else {
				UtilText.nodeContentSB.append("Приняв самый умоляющий вид, какой только смогла изобразить, ты вскрикиваешь, обращаясь к [npc2.name], "
								+UtilText.returnStringAtRandom(
										"[npc.speech(Пожалуйста, потри свой член о мою грудь!)]",
										"[npc.speech(Потрись о мою грудь, пожалуйста!)]")
								+"<br/><br/>");
			}
			
			UtilText.nodeContentSB.append(getOfferResponse(
					((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)),
					Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this)),
					"грудь"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(!((NPC)Main.sex.getCharacterTargetedForSexAction(this)).getSexBehaviourDeniesRequests(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST))) {
				((NPC)Main.sex.getCharacterTargetedForSexAction(this)).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST)));
				
			} else if(!Main.sex.isDom(Main.game.getPlayer())) {
				Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.BREAST));
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
			}
		}
	};
}
