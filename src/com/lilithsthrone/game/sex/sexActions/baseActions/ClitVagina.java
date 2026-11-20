package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class ClitVagina {
	
	public static final SexAction TEASE_CLIT_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Ласки клитора";
		}
		@Override
		public String getActionDescription() {
			return "Води своим [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].";
		}
		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Двигая [npc.her] [npc.clit+] до [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] медленно дразня [npc.clitTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С мягким [npc.moan], [npc.name] [npc.verb(move)] [npc.her] [npc.clit+] до [npc2.namePos] [npc2.pussy+], прежде чем начать нежно водить [npc.clitTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"Мягко водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о том, что можно будет проникнуть в [npc2.herHim], как только [npc.she] [npc.verb(feel)] этим."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Двигая [npc.her] [npc.clit+] до [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] жадно водит [npc.clitTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С [npc.a_moan+], [npc.name] [npc.verb(move)] [npc.her] [npc.clit+] до [npc2.namePos] [npc2.pussy+], прежде чем начать жадно водить [npc.clitTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"Нетерпеливо водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вжимая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.pussy+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.clitTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовая начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.clit+] вверх к [npc2.namePos] [npc2.pussy+], прежде чем начать грубо [npc2.verb(grind)] [npc.clitTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"Грубо водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Поднимая [npc.her] [npc.clit+] к [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] водит [npc.clitTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовая проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(move)] [npc.her] [npc.clit+] вверх к [npc2.namePos] [npc2.pussy+], прежде чем начать водить [npc.clitTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"Скользя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				if((Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin() || Main.sex.getCharacterTargetedForSexAction(this).hasHymen()) && Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
						switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
							case SUB_RESISTING:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет [npc2.her] девственность.",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] может вот-вот потерять [npc2.her] девственность.",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере [npc2.her] девственности перед [npc.namePos] [npc.clit+]."));
								break;
							default:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" Лелея [npc2.her] девственность превыше всего, [npc2.name] [npc2.verb(pull)] [npc2.her] [npc2.pussy] прочь от [npc.namePos] [npc.clit+].",
										" В ужасе от перспективы потери [npc2.her] драгоценной девственности, [npc2.name] [npc2.verb(pull)] [npc2.her] [npc2.pussy] прочь от [npc.namePos] [npc.clit+].",
										" Отпрянув от [npc.namePos] прикосновения, [npc2.name] [npc2.verb(make)] ясно дает понять, что [npc2.she] [npc2.do] не хочет терять [npc2.her] девственность, убирая [npc2.her] [npc2.pussy] от [npc.namePos] [npc.clit+]."));
								break;
						}
						
					} else {
						switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
							case SUB_RESISTING:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Не надо! Пожалуйста! Я... я девственница! Вы не можете этого сделать!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянно [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Я ведь еще девственница!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу лишаться девственности!)]"));
								break;
							default:
								if(Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" Лелея [npc2.her] девственность превыше всего, [npc2.name] [npc2.verb(order)], [npc2.speech(Не заходи дальше этого! Я еще не готова лишиться девственности!)]",
											" В ужасе от перспективы потери своей драгоценной [npc2.her] девственности, [npc2.name] [npc2.verb(snap)], [npc2.speech(Не делай этого! Я не хочу терять девственность вот так!)]",
											" Отпрянув от [npc.namePos] прикосновения, [npc2.name] [npc2.verb(warn)], [npc2.speech(Только посмей зайти дальше! Я не собираюсь терять девственность прямо здесь!)]"));
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" Лелея [npc2.her] девственность превыше всего, [npc2.name] лихорадочно [npc2.verb(plead)], [npc2.speech(Не иди дальше! Пожалуйста, я не хочу терять девственность!)]",
											" В ужасе от перспективы потери своей драгоценной [npc2.her] девственности, [npc2.name] отчаянно [npc2.verb(cry)], [npc2.speech(Стой! Не надо! Я не хочу терять девственность!)]",
											" Отпрянув от [npc.namePos] прикосновения, [npc2.name] отчаянно [npc2.verb(beg)], [npc2.speech(Нет! Стой! Я не хочу терять девственность!)]"));
								}
								break;
						}
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируются, и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.vagina+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.vagina+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc.her] [npc.vagina+], и в ответ нежно [npc2.verb(push)] [npc2.her] [npc2.hips+]."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируются, и [npc2.she] жадно [npc2.verb(push)] [npc2.her] [npc2.labia+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно вытолкнуть [npc2.her] [npc2.vagina+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc.her] [npc.vagina+], и в ответ отчаянно [npc2.verb(buck)] [npc2.her] [npc2.hips+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] с силой вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируется, и [npc2.she] мощно [npc2.verb(thrust)] [npc2.her] [npc2.labia+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо вытолкнуть [npc2.her] [npc2.vagina+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc.her] [npc.vagina+], и в ответ яростно [npc2.verb(buck)] [npc2.her] [npc2.hips+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] с силой вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируется, и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.labia+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.verb(let)] [npc2.moan], прежде чем вытолкнуть [npc2.her] [npc2.vagina+] на [npc.namePos] [npc.clit].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc.her] [npc.vagina+], и в ответ [npc2.verb(buck)] [npc2.her] [npc2.hips+]."));
							break;
						case SUB_RESISTING:
							if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет [npc2.her] девственность.",
											" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] может вот-вот потерять [npc2.her] девственность.",
											" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере [npc2.her] девственности перед [npc.namePos] [npc.clit+]."));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что сейчас произойдет, [npc2.speech(Нет! Прекрати! Я еще девственница!)]",
											" [npc2.Name] [npc2.verb(let)] отчаянно [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Я ведь еще девственница!)]",
											" [npc2.Name] [npc2.sobsVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу лишаться девственности!)]"));
								}
								
							} else {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] с силой вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.clit].",
											" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
											" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] с силой вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.clit], [npc2.speech(Нет! Не надо! Пожалуйста, уйди от меня!)]",
											" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
											" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
								}
							}
							break;
						default:
							break;
					}
				}
			}
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction FORCE_CLIT_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Ласкать [npc2.her] клитор";
		}
		@Override
		public String getActionDescription() {
			return "Скользить [npc2.clitTip] [npc2.namePos] [npc2.clit] по твоей [npc.vagina+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить его вверх к [npc.her] [npc.pussy+]. Медленно толкая [npc2.clitTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] ее до [npc.her] [npc.pussy+], после чего начинает нежно водить [npc2.clitTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] осторожно [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.vagina+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить ее к [npc.her] [npc.pussy+]. Жадно двигая [npc2.clitTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name], предвкушая, что проникновение может случиться в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] ее до [npc.her] [npc.pussy+], после чего начинает жадно водить [npc2.clitTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] с жадностью [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.vagina+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем резко дернуть его вверх к [npc.her] [npc.pussy+]. Грубо толкая [npc2.clitTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и резким рывком вверх к [npc.her] [npc.pussy+], прежде чем начать грубо [npc.verb(force)] [npc2.clitTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] грубо [npc.verb(grind)] [npc2.clitTip+] поверх [npc.her] [npc.vagina+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить его вверх к [npc.her] [npc.pussy+]. Проталкивая [npc2.clitTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] его вверх к [npc.her] [npc.pussy+], прежде чем начать скользить [npc2.clitTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(slide)] [npc2.clitTip+] поверх [npc.her] [npc.vagina+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] доносится из меж [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], прежде чем мягко скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.vagina+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой втирать [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.her] [npc.vagina+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом трет [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.vagina+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.vagina+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)] [npc.name], чтобы отпустить [npc2.her] [npc2.clit]."));
						break;
				}
			}
			if((Main.sex.getCharacterPerformingAction().isVaginaVirgin() || Main.sex.getCharacterPerformingAction().hasHymen()) && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				if(Main.sex.getCharacterPerformingAction().isMute()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Лелея [npc.her] девственность превыше всего, [npc.name] затем быстро [npc.verb(pull)] [npc.her] [npc.pussy] от [npc2.namePos] [npc2.clit+].",
							" Совсем не готова потерять [npc.her] драгоценную девственность, [npc.name], а затем быстро [npc.verb(pull)] [npc.her] [npc.pussy] от [npc2.namePos] [npc2.clit+].",
							" Поскольку [npc.she] [npc.verb(treasure)] [npc.her] драгоценную девственность превыше всего, [npc.name] [npc.verb(make)] дает понять, что [npc.she] [npc.do] на самом деле не хочет, чтобы ее проникали, оттягивая [npc.her] [npc.pussy] от [npc2.namePos] [npc2.clit+]."));
						
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Поскольку [npc.she] [npc.verb(treasure)] [npc.her] свою драгоценную девственность превыше всего, [npc.name] [npc.verb(make)] совершенно ясно, что [npc.she] [npc.do]n't want [npc2.name], чтобы [npc.herHim] действительно проникал в [npc.moaning],",
							" Совсем не готовая потерять [npc.her] драгоценную девственность, [npc.name] [npc.verb(make)] совершенно ясно, что [npc.she] [npc.do]n't want, чтобы в нее проникал [npc.moaning],"));
					if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.speech(Тебе нравится это ощущение? Ну, это всё, что ты получишь; я не собираюсь так просто лишиться девственности!)]",
								" [npc.speech(Это всё, что ты получишь! Я не позволю тебе по-настоящему забрать мою девственность!)]",
								" [npc.speech(Тебе придется довольствоваться только этим! Я не позволю тебе по-настоящему забрать мою девственность!)]"));
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc.speech(Тебя ведь вполне устраивает просто ласка моей киски, а?! Я не хочу на самом деле терять девственность!)]",
								" [npc.speech(Этого тебе ведь достаточно, правда? Пожалуйста, не забирай мою девственность...)]",
								" [npc.speech(Ты ведь можешь довольствоваться только этим! Я не хочу, чтобы ты по-настоящему забрал мою девственность!)]"));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	
	public static final SexAction CLIT_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Начать клиторный секс";
		}
		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.clit+] в [npc2.namePos] [npc2.vagina+] и начни клиторный секс с [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], и медленным, уверенным движением [npc.she] нежно [npc.verb(sink)] её глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] с нетерпением [npc.verb(sink)] её глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, вбивая [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], и мощным толчком [npc.she] грубо [npc.verb(slam)] её глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] с нетерпением [npc.verb(sink)] её глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Лаская [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+] перед тем как толкнуться вперед, погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] [npc2.labia+], и коротким толчком [npc.she] [npc.verb(sink)] её глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
					if(Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						UtilText.nodeContentSB.append(" Когда [npc2.she] [npc2.verb(prize)] [npc2.her] девственность превыше всего, [npc2.name] не может не издать отчаянный, шокированный крик, когда [npc2.she] [npc2.verb(experience)] ощущение первого проникновения.");
						
					} else if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
						UtilText.nodeContentSB.append(" Будучи девственницей, [npc2.sheIs] не может сдержать отчаянного вопля, когда [npc2.name] [npc2.verb(experience)] ощущение первого проникновения, которое испытывает [npc2.she].");
						
					} else {
						UtilText.nodeContentSB.append(" Будучи [npc2.sheIs] девственницей, [npc2.name] не может сдержать шокированного [npc2.moan], когда [npc2.she] [npc2.verb(experience)] ощущение первого проникновения.");
					}
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).hasHymen()) {
					if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
						UtilText.nodeContentSB.append(" Поскольку девственная плева [npc2.her] еще цела, [npc2.name] не может сдержать отчаянного вопля, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit+] разрывает её.");
						
					} else {
						UtilText.nodeContentSB.append(" Поскольку девственная плева [npc2.her] еще цела, [npc2.name] не может сдержать шокированного [npc2.moan], когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit+] разрывает её.");
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], когда [npc.clit+] входит в [npc2.herHim], после чего она плавно выгибает [npc2.her] [npc2.hips] назад, чтобы погрузить его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С тихим [npc2.moan], [npc2.name] [npc2.verb(start)] плавно выгибает [npc2.her] [npc2.hips] назад, погружая [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она резко толкает [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно выгибает [npc2.her] [npc2.hips] назад, грубо заставляя [npc.name] погружать [npc.her] [npc.clit+] еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она жадно выгибает [npc2.her] [npc2.hips] назад, чтобы погрузить его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно выгибает [npc2.her] [npc2.hips] назад, отчаянно помогая погрузить [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она выгибает [npc2.her] [npc2.hips] назад, чтобы погрузить его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)], выгибая [npc2.her] [npc2.hips] назад, помогая [npc.namePos] [npc.clit+] погрузиться еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.clit+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил её.",
		
									" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока незваный [npc.clit] [npc.namePos] глубоко вжимается в [npc2.her] [npc2.vagina+]."));
							break;
					}
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с нетерпением [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы [npc.namePos] [npc.clit+] погрузились глубоко в [npc2.her] [npc2.vagina+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал использовать [npc.her] [npc.clit], чтобы трахать [npc2.herHim].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать трахать [npc2.herHim], пока движения [npc2.her] помогают [npc.her] [npc.clit+] погрузиться глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.clit], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил(а) из [npc2.her] [npc2.vagina+].",
		
							" [npc2.A_sob+] с шумом вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her], пока [npc2.face] [npc2.verb(plead)], чтобы [npc2.she] вытащил(а) из [npc2.her] [npc2.vagina+] [npc.herHim].",
		
							" [npc2.Sobbing] в смятении, слезы текут по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил(а) из [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.clit+] глубоко в [npc2.her] [npc2.vagina+].",
	
							" [npc2.A_moan+] с шумом вырывается из между [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) использовать [npc.her] [npc.clit], чтобы трахать [npc2.herHim].",
	
							" [npc2.Moaning] от восторга, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] не прекращать трахать [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], прося [npc.name] продолжать использовать [npc.her] [npc.clit], чтобы трахать [npc2.her] [npc2.vagina+].",
	
							" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, медленно выгибая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(implore)] [npc.name] продолжать использовать [npc.her] [npc.clit], чтобы трахать [npc2.herHim].",
	
							" [npc2.Moaning] от восторга, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] не прекращать трахать [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] использовать [npc.her] [npc.clit], чтобы трахать [npc2.herHim].",
	
							" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+] и, грубо вжимая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжить использовать [npc.her] [npc.clit] для траха [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] назад, приказывая [npc.name] продолжать трахать [npc2.herHim], пока движения [npc2.her] заставляют [npc.her] [npc.clit+] глубоко проникать в [npc2.her] [npc2.vagina+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction CLIT_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежный клиторотрах";
		}
		@Override
		public String getActionDescription() {
			return "Мягко вводите свой [npc.clit] в [npc2.namePos] [npc2.vagina+] и выводите его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)] его туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.name].",

					"Медленно проталкивая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.vagina+], [npc.name] мягко [npc2.verb(thrust)] его туда-сюда, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",

					"Скользя [npc.her] [npc.clit+] в [npc2.namePos], [npc2.vagina+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction CLIT_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клиторотрах";
		}
		@Override
		public String getActionDescription() {
			return "Продолжайте вводить свой [npc.clit+] в [npc2.namePos] [npc2.vagina+] и выводить его.";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] с энтузиазмом [npc.verb(start)], двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом втискивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] неистово [npc2.verb(start)], двигая им туда-сюда и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"[npc2.vagina+] глубоко вгоняет [npc.her] [npc.clit+] в [npc2.namePos], [npc.name] [npc.verb(let)], пока [npc.a_moan+] [npc.verb(start)] [npc.she], чтобы жадно двигать им туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый клиторный секс";
		}
		@Override
		public String getActionDescription() {
			return "Грубо двигай своим [npc.clit+] туда-сюда в [npc2.namePos] [npc2.vagina+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С силой вколачивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] грубо [npc.verb(start)], двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.name].",

					"Яростно вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] грубо [npc.verb(start)], двигая им туда-сюда и издавая [npc.a_moan+], пока [npc.she] напористо [npc.verb(fuck)] [npc2.herHim].",

					"С силой вонзая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(start)], чтобы с силой вбивать его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клиторотрах";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.vagina+] своим [npc.clit].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"Вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)] туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вбивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(start)], чтобы качать его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахать клитор";
		}
		@Override
		public String getActionDescription() {
			return "Жадно качай своим [npc.clit+] туда-сюда в [npc2.namePos] [npc2.vagina+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] с энтузиазмом [npc.verb(start)], двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом втискивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] неистово [npc2.verb(start)], двигая им туда-сюда и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"[npc2.vagina+] глубоко вгоняет [npc.her] [npc.clit+] в [npc2.namePos], [npc.name] [npc.verb(let)], пока [npc.a_moan+] [npc.verb(start)] [npc.she], чтобы жадно двигать им туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться траху клитора";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.clit] из [npc2.namePos] [npc2.vagina+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] нежно сжимает её, прежде чем мягко вдавить её обратно в [npc2.her] [npc2.vagina+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем осторожно вдавить её обратно внутрь [npc2.her] [npc2.vagina+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.vagina+] вниз на [npc.her] [npc.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] грубо сжимает её, прежде чем агрессивно вдавить её обратно в [npc2.her] [npc2.vagina+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем грубо вдавить её обратно внутрь [npc2.her] [npc2.vagina+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.vagina+] вниз на [npc.her] [npc.clit+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.pussy], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко сжимает её, прежде чем жадно затолкнуть обратно в [npc2.her] [npc2.vagina+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем жадно затолкнуть обратно внутрь [npc2.her] [npc2.vagina+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.vagina+] вниз на [npc.her] [npc.clit+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] [npc2.verb(move)], удерживая его глубоко в [npc2.her] [npc2.vagina+].",

						"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(move)] вперед, удерживая его глубоко внутри [npc2.her] [npc2.vagina+].",

						"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(react)] и [npc2.verb(force)] [npc2.her] [npc2.pussy+] вниз на [npc.her] [npc.clit+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить клиторофутнинг";
		}
		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.clit+] из [npc2.namePos] [npc2.vagina+] и прекрати клиторофутнинг [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдернув [npc.her] [npc.clit+] из [npc2.namePos] [npc2.pussy+], [npc.name] властно [npc.verb(slide)] [npc.clitTip+] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.clit+] назад из [npc2.her] [npc2.vagina+], завершая этот грубый клиторный секс."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc.her] [npc.clit] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(rub)] [npc.clitTip] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.clit+] обратно из [npc2.her] [npc2.vagina+], завершая клиторный секс."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.vagina], а [npc2.she] [npc2.verb(continue)] плачет и протестует, пока [npc2.she] [npc2.verb(carry)], слабо сопротивляясь [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.vagina+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.name] [npc.verb(pull)] [npc.her] [npc.clit+] из [npc2.her] [npc2.vagina+], жаждая еще большего внимания от [npc.her].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_CLIT_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Трахнуть клитор";
		}
		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.clit+] в свою [npc.vagina+] и трахнись.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterPerformingAction().isVaginaVirgin()) {
				if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					UtilText.nodeContentSB.append("Несмотря на то, что [npc.she] [npc.verb(prize)] [npc.her] превыше всего, [npc.name] [npc.verb(decide)], что настало время наконец-то ее потерять. ");
				} else {
					UtilText.nodeContentSB.append("Хотя [npc.sheHas] удавалось сохранять [npc.her] девственность до этого самого момента, [npc.name] [npc.verb(decide)], что пришло время наконец её потерять. ");
				}
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.clit] [npc2.namePos], [npc.name] медленно [npc.verb(guide)] её до [npc.her] [npc.labia+], издавая легкий [npc.moan], прежде чем мягко выгнуть [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.vagina+].",

							"Схватив [npc2.clit] [npc2.namePos], [npc.name] [npc.verb(line)] её до [npc.her] [npc.vagina+], прежде чем медленно оттолкнуть [npc.her] [npc.hips] назад и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.clit] [npc2.namePos], [npc.name] жадно [npc.verb(guide)] её до [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем отчаянно выгнуть [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.vagina+].",

							"Схватив [npc2.clit] [npc2.namePos], [npc.name] [npc.verb(line)] её до [npc.her] [npc.vagina+], прежде чем с азартом толкнуть [npc.her] [npc.hips] назад и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.clit] [npc2.namePos], [npc.name] грубо дергает её до [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем яростно впечатать [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.vagina+].",

							"Сжимая [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.vagina+], а затем жадно вбивая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Сжимая [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(guide)] её до [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем дернуть [npc.her] [npc.hips] и заставляя [npc2.herHim] проникать в [npc.her] [npc.vagina+].",

							"Сжимая [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.vagina+], а затем толкая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], осторожно подталкивая [npc2.her] [npc2.clit] вперед, пока [npc2.she] [npc2.verb(start)] трахать [npc.namePos] [npc.vagina+].",
	
								" С тихим [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая её в [npc.namePos] [npc.vagina+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.clit] вперед, пока [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.vagina+].",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая его в [npc.namePos] [npc.vagina+], пока [npc2.she] энергично [npc2.verb(start)] [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.clit] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.vagina+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] безжалостно [npc2.verb(start)] [npc.namePos] [npc.vagina+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.clit] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.vagina+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая его в [npc.namePos] [npc.vagina+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.clit] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.clit+] из [npc.her] [npc.vagina+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.clit] глубоко в [npc.her] [npc.vagina+]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+], издавая приглушенный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.pussy+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+].",
		
							" Восторженно [npc2.Moaning], [npc2.name] с нетерпением [npc2.verb(drive)] [npc2.her] [npc2.clit+] как можно глубже в [npc.namePos] [npc.vagina+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.clit] от [npc.namePos] [npc.pussy], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.clit+] глубоко в [npc.her] [npc.vagina+].",
		
							" В мучительном [npc2.Sobbing], [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.clit] от [npc.namePos] [npc.vagina+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+], издавая тихий, приглушенный [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.pussy+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)] задвигает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+].",
	
							" [npc2.Moaning] от восторга, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+], издавая приглушенный [npc2.moan], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.pussy+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos], прежде чем [npc2.she] яростно [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+].",
	
							" [npc2.Moaning] от восторга, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit+] настолько глубоко, насколько это возможно, в [npc.namePos] [npc.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.pussy+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+].",
	
							" [npc2.Moaning] от восторга, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.vagina+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_CLIT_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный клитор-секс";
		}
		@Override
		public String getActionDescription() {
			return "Нежно трахай свой [npc.vagina+] о [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] издает тихий [npc.moan], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+].",

					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Медленно вгоняя [npc.namePos] [npc.hips] назад, тихий [npc.moan] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клиторный секс";
		}
		@Override
		public String getActionDescription() {
			return "Жадно трахай свой [npc.vagina+] о [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Страстно толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Энергично вгоняя [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый клиторный секс";
		}
		@Override
		public String getActionDescription() {
			return "Грубо трахать твой [npc.vagina+] на [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вколачивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вколачивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Сильно толкая [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_CLIT_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клиторный секс";
		}
		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.vagina+] на [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] [npc.a_moan+], чтобы [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] вколачивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Толкая [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахаемый клитором";
		}
		@Override
		public String getActionDescription() {
			return "Жадно трахай свой [npc.vagina+] о [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Страстно толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Энергично вгоняя [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться траху клитором";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.vagina+] от [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.vagina+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в отчаянии борясь, пока [npc2.her] [npc2.clit+] медленно [npc2.verb(continue)] входит и выходит из [npc.her] [npc.vagina+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] нежно [npc2.verb(continue)] глубоко в [npc.her] [npc.vagina+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.vagina+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] неистово входит и выходит из [npc.her] [npc.vagina+].",
	
								"Отчаянно пытаясь оттащить [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.vagina+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.vagina+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] грубо вбивается в [npc.her] [npc.vagina+].",
	
								"Отчаянно пытаясь оттащить [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.vagina+]."));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.vagina+].",

						"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.clit+] [npc2.verb(remain)] глубоко входит в [npc.her] [npc.vagina+].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(remain)] глубоко в [npc.her] [npc.vagina+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить клиторный секс";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.clit] из твоей [npc.vagina+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко вырывая [npc2.namePos] [npc2.clit] из [npc.her] [npc.vagina+], [npc.name] [npc.verb(let)] с угрожающим рычанием, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы перестать трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.clit] из [npc.her] [npc.vagina+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно вытягивая [npc2.namePos] [npc2.clit] из [npc.her] [npc.vagina+], [npc.name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.clit] из [npc.her] [npc.vagina+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] с облегченным вздохом, который вскоре сменяется [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.name] [npc.verb(stop)] [npc2.herHim] из трахания [npc.her] [npc.vagina+].",
	
								" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжить трахать [npc.namePos] [npc.vagina+]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getVaginaOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Контроль киски";
		}
		@Override
		public String getActionDescription() {
			return "Стисните свою киску, наполненную внутренними мышцами, вокруг [npc2.namePos] [npc2.clit].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Выпуская [npc.a_moan+], [npc.name] [npc.verb(concentrate)] на сжатии дополнительных внутренних мышц внутри [npc.her] [npc.pussy] вокруг [npc2.namePos] [npc2.clit+].",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(focus)] на контроле дополнительных мышц, выстилающих внутренности [npc.her] [npc.pussy]. Сжимая и сдавливая их вокруг [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.pussy+] вокруг [npc2.namePos] [npc2.clit+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.pussy], сжимая их и массируя [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
