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
 * @since 0.1.82
 * @version 0.3.8.8
 * @author Innoxia
 */
public class TentacleVagina {
	
	public static final SexAction TEASE_TENTACLE_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнящие щупальца";
		}

		@Override
		public String getActionDescription() {
			return "Проведи своим [npc.tentacle(истинный)] по [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Извиваясь, [npc.her] [npc.tentacle+(истинный)] ползет к [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] медленно дразня [npc.tentacleTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(истинный)] к [npc2.namePos] [npc2.pussy+], прежде чем начать нежно водить [npc.tentacleTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"Мягко водя [npc.tentacleTip+] от [npc.her] [npc.tentacle(истинный)] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] издает тихий [npc.moan] при мысли о том, что сможет проникнуть в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Извиваясь, [npc.her] [npc.tentacle+(истинный)] ползет к [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] жадно водит [npc.tentacleTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(истинный)] к [npc2.namePos] [npc2.pussy+], прежде чем начать жадно водить [npc.tentacleTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"С жадностью скользя [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Стирая [npc.her] [npc.tentacle+(true)] о [npc2.namePos] [npc2.pussy+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать скользить [npc.tentacleTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tentacle+(true)] к [npc2.namePos] [npc2.pussy+], прежде чем начать грубо [npc2.verb(grind)] [npc.tentacleTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"Грубо втирая [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Проскальзывая [npc.her] [npc.tentacle+(true)] к [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(start)] скользя [npc.tentacleTip+] вверх и вниз между [npc2.her] [npc2.labia+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(true)] до [npc2.namePos] [npc2.pussy+], прежде чем начать скользить [npc.tentacleTip] вверх и вниз между [npc2.her] [npc2.labia+].",

							"Скользя [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] вверх и вниз по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] из [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
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
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере [npc2.her] девственности из-за [npc.namePos] [npc.tentacle+]."));
								break;
							default:
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" Лелея [npc2.her] девственность превыше всего, [npc2.name] [npc2.verb(pull)] [npc2.her] [npc2.pussy] от [npc.namePos] [npc.tentacle+].",
										" В ужасе от перспективы потери драгоценной [npc2.her] девственности, [npc2.name] [npc2.verb(pull)] [npc2.her] [npc2.pussy] от [npc.namePos] [npc.tentacle+].",
										" Отпрянув от прикосновения [npc.namePos], [npc2.name] [npc2.verb(make)] ясно дает понять, что [npc2.she] [npc2.do] не хочет терять [npc2.her] девственность, оттягивая [npc2.her] [npc2.pussy] от [npc.namePos] [npc.tentacle+]."));
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
									" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируется, и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.vagina+] на [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.vagina+] навстречу [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc.her] [npc.vagina+], и мягко [npc2.verb(push)] [npc2.her] [npc2.hips+] в ответ."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируется, а [npc2.she] жадно [npc2.verb(push)] [npc2.her] [npc2.labia+] навстречу [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем с жадностью вытолкнуть [npc2.her] [npc2.vagina+] навстречу [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc.her] [npc.vagina+], и отчаянно [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируется, а [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] [npc2.labia+] навстречу [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо вытолкнуть [npc2.her] [npc2.vagina+] навстречу [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc.her] [npc.vagina+], и яростно [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.vagina+] стимулируется, и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.labia+] на [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.moan], прежде чем вытолкнуть [npc2.her] [npc2.vagina+] на [npc.namePos] [npc.tentacle(true)].",
		
									" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc.her] [npc.vagina+], и яростно [npc2.verb(buck)] [npc2.her] [npc2.hips+] в ответ."));
							break;
						case SUB_RESISTING:
							if(Main.sex.getCharacterTargetedForSexAction(this).isVaginaVirgin()) {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет [npc2.her] девственность.",
											" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] может вот-вот потерять [npc2.her] девственность.",
											" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере [npc2.her] девственности из-за [npc.namePos] [npc.tentacle+]."));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] с силой вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что сейчас произойдет, [npc2.speech(Нет! Не надо! Я еще девственница!)]",
											" [npc2.Name] [npc2.verb(let)] отчаянно [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Я ведь еще девственница!)]",
											" [npc2.Name] [npc2.sobsVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу лишаться девственности!)]"));
								}
								
							} else {
								if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.tentacle].",
											" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
											" [npc2.Name] [npc2.sobVerb] в смятении, когда [npc2.she] [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.tentacle+]."));
									
								} else {
									UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
											" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.tentacle(true)], [npc2.speech(Нет! Не надо! Пожалуйста, убирайся от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction FORCE_TENTACLE_OVER_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнить щупальце [npc2.her]";
		}

		@Override
		public String getActionDescription() {
			return "Проведи [npc2.tentacleTip] [npc2.namePos] [npc2.tentacle(true)] по твоей [npc.vagina+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем направить её к [npc.her] [npc.pussy+]. Медленно толкая [npc2.tentacleTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] и [npc.verb(guide)] её к [npc.her] [npc.pussy+], прежде чем начать нежно водить [npc2.tentacleTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Схватив [npc2.namePos] [npc2.tentacle+(true)], [npc.name] осторожно [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.vagina+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем направить её к [npc.her] [npc.pussy+]. Жадно толкая [npc2.tentacleTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] и [npc.verb(guide)] её к [npc.her] [npc.pussy+], прежде чем начать жадно водить [npc2.tentacleTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Схватив [npc2.namePos] [npc2.tentacle+(true)], [npc.name] с нетерпением [npc.verb(slide)] [npc2.tentacleTip+] поверх [npc.her] [npc.vagina+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем резко дернуть его вверх к [npc.her] [npc.pussy+]. Грубо толкая [npc2.tentacleTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] и дергает его вверх к [npc.her] [npc.pussy+], после чего начинает грубо [npc.verb(force)] [npc2.tentacleTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Схватив [npc2.namePos] [npc2.tentacle+(true)], [npc.name] грубо [npc.verb(grind)] [npc2.tentacleTip+] поверх [npc.her] [npc.vagina+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем направить его к [npc.her] [npc.pussy+]. Проталкивая [npc2.tentacleTip+] вверх и вниз между [npc.her] [npc.labia+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], и [npc.verb(guide)] до [npc.her] [npc.pussy+], после чего начинает скользить [npc2.tentacleTip] вверх и вниз между [npc.her] [npc.labia+].",

							"Сжимая [npc2.namePos] [npc2.tentacle+(true)], [npc.name] [npc.verb(slide)] [npc2.tentacleTip+] через [npc.her] [npc.vagina+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], а [npc2.she] [npc2.verb(start)] нежно потирая [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем нежно скользить [npc2.her] [npc2.tentacle(true)] вперед и назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко скользит [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.vagina+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой начать тереть [npc2.her] [npc2.tentacle(true)] вперед-назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] стимулируется, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо растирая [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.her] [npc.vagina+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом растирает [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.tentacle(true)] вперед-назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.vagina+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] растирает [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем начать скользить [npc2.her] [npc2.tentacle(true)] вперед-назад по [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.vagina+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.tentacle+(true)] от [npc.namePos] [npc.vagina+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.tentacle+(true)] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)] [npc.name], чтобы отпустить [npc2.her] [npc2.tentacle(true)]."));
						break;
				}
				if((Main.sex.getCharacterPerformingAction().isVaginaVirgin() || Main.sex.getCharacterPerformingAction().hasHymen()) && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Лелея [npc.her] девственность превыше всего, [npc.name] затем быстро [npc.verb(pull)] [npc.her] [npc.pussy] от [npc2.namePos] [npc2.tentacle+].",
								" Совсем не желая терять [npc.her] драгоценную девственность, [npc.name] затем быстро [npc.verb(pull)] [npc.her] [npc.pussy] от [npc2.namePos] [npc2.tentacle+].",
								" Пока [npc.she] [npc.verb(treasure)] [npc.her] драгоценную девственность превыше всего, [npc.name] [npc.verb(make)] ясно, что [npc.she] [npc.do]n't на самом деле не хочет, чтобы ее проникали, оттягивая [npc.her] [npc.pussy] от [npc2.namePos] [npc2.tentacle+]."));
							
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
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TENTACLE, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	
	public static final SexAction TENTACLE_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать щупальцевый секс";
		}

		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.tentacle+(true)] в [npc2.namePos] [npc2.vagina+] и начни щупальцевый секс с [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуть вперед, погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], и с медленным, уверенным напором, [npc.she] плавно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] с нетерпением [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануть вперед, вбивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], и решительным толчком [npc.she] с нетерпением [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+] перед тем как рвануться вперед, вонзая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.vagina+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.labia+], и легким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.vagina+]."));
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
						UtilText.nodeContentSB.append(" Поскольку девственная плева [npc2.her] еще цела, [npc2.name] не может не издать неистовый вопль, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle+(true)], разрывая её.");
						
					} else {
						UtilText.nodeContentSB.append(" Поскольку девственная плева [npc2.her] еще цела, [npc2.name] не может не издать шокированный [npc2.moan], когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle+(true)], разрывая её.");
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], когда [npc.tentacle+(true)] входит в [npc2.herHim], после чего мягко подбрасывая [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] плавно выгибает [npc2.her] [npc2.hips] назад, погружая [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], когда [npc.tentacle+(true)] входит в [npc2.herHim], а затем яростно вгоняет [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] неистово выгибает [npc2.her] [npc2.hips] назад, грубо заставляя [npc.name] погружать [npc.her] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tentacle+(true)] входит в [npc2.herHim], а затем жадно выталкивает [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] с жадностью выгибает [npc2.her] [npc2.hips] назад, отчаянно помогая погрузить [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу, пока [npc2.a_moan+] выходит, когда [npc.tentacle+(true)] входит в [npc2.herHim], после чего [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.vagina+].",
		
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)], выгибая [npc2.her] [npc2.hips] назад, помогая погрузить [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.vagina+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc.tentacle+(true)] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
		
									" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.tentacle(true)] [npc.namePos] глубоко впивается в [npc2.her] [npc2.vagina+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.vagina+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал щупальцевый секс в [npc2.herHim].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips+] назад, умоляя о [npc.name], чтобы продолжался щупальцевый секс в [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах вырваться из [npc.namePos] [npc.tentacle(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил(а) его из [npc2.her] [npc2.vagina+].",
		
							" [npc2.A_sob+] с шумом вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her], пока [npc2.face] [npc2.verb(plead)], чтобы [npc2.she] вытащил(а) из [npc2.her] [npc2.vagina+] [npc.herHim].",
		
							" [npc2.Sobbing] в смятении, слезы текут по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил(а) из [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" В ответ [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], позволяя [npc.namePos] [npc.tentacle+(true)] глубоко погрузиться в [npc2.her] [npc2.vagina+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) щупальцевый секс с [npc2.herHim].",
	
							" В восторге от [npc2.Moaning], [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя о [npc.name], чтобы продолжался щупальцевый секс с [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" В ответ [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips], издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать щупальцевый секс с [npc2.her] [npc2.vagina+].",
	
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал(а) щупальцевый секс с [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать щупальцевый трах [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] чертовски [npc2.herHim].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(order)] [npc.name] продолжать щупальцевый трах [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] назад, приказывая [npc.name] продолжать щупальцевый трах [npc2.herHim], пока [npc2.her] движения вгоняют [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.vagina+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TENTACLE_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный щупальцевый трах";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводите и выводите свой [npc.tentacle(true)] в [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)] вводит и выводит его, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(tentacle-fuck)] [npc2.name].",

					"Медленно проталкивая [npc.her] [npc.tentacle+(true)] в [npc2.namePos] [npc2.vagina+], [npc.name] мягко [npc2.verb(thrust)] его туда-сюда, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(tentacle-fuck)] [npc2.herHim].",

					"Скользящий [npc.her] [npc.tentacle+(true)] входит в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] наружу на небольшую [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко входить и выходить, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(tentacle-fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тентакль-трах";
		}

		@Override
		public String getActionDescription() {
			return "Продолжайте входить и выходить своим [npc.tentacle+(true)] в [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] с энтузиазмом [npc.verb(start)], совершая толчки, и издавая [npc.a_moan+] при каждом движении, пока [npc.she] счастливо [npc.verb(tentacle-fuck)] [npc2.name].",

					"С энтузиазмом вталкивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] неистово [npc2.verb(start)], совершая толчки, и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tentacle-fuck)] [npc2.herHim].",

					"Вбивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно совершать толчки, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tentacle-fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый тентакль-трах";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входите и выходите своим [npc.tentacle+(true)] в [npc2.namePos] [npc2.vagina+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)], резко двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(tentacle-fuck)] [npc2.name].",

					"Яростно вонзая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)], резко толкая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(tentacle-fuck)] [npc2.herHim].",

					"С силой вгоняя [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)], пока [npc.a_moan+] [npc.verb(start)] [npc.she], грубо вколачивая его туда-сюда и жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(tentacle-fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тентакль-трах";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.vagina+] своим [npc.tentacle(true)].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)], двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(tentacle-fuck)] [npc2.name].",

					"Вталкивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tentacle-fuck)] [npc2.herHim].",

					"[npc.her] [npc.tentacle+(true)] глубоко входит в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], когда [npc.she] [npc.verb(tentacle-fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадное щупальцевое трахание";
		}

		@Override
		public String getActionDescription() {
			return "Жадно вгоняй свой [npc.tentacle+(true)] в [npc2.namePos] [npc2.vagina+] и вынимай его.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] с энтузиазмом [npc.verb(start)], совершая толчки, и издавая [npc.a_moan+] при каждом движении, пока [npc.she] счастливо [npc.verb(tentacle-fuck)] [npc2.name].",

					"С энтузиазмом вталкивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] неистово [npc2.verb(start)], совершая толчки, и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tentacle-fuck)] [npc2.herHim].",

					"Вбивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно совершать толчки, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tentacle-fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться щупальцевому траханию";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tentacle(true)] из [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] вниз и [npc2.verb(take)] нежно удерживает его, прежде чем мягко вдавить обратно в [npc2.her] [npc2.vagina+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно вдавить обратно внутрь [npc2.her] [npc2.vagina+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle(true)] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.vagina+] на [npc.her] [npc.tentacle+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко сжимает его, прежде чем агрессивно вогнать обратно в [npc2.her] [npc2.vagina+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо затолкнуть обратно в [npc2.her] [npc2.vagina+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.vagina+] вниз на [npc.her] [npc.tentacle+(true)]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.pussy], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко сжимает его, прежде чем с жадностью вогнать обратно в [npc2.her] [npc2.vagina+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем с жадностью затолкнуть обратно в [npc2.her] [npc2.vagina+].",
	
							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.namePos] [npc2.pussy+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.vagina+] на [npc.her] [npc.tentacle+(true)]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить щупальцевый трах";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tentacle+(true)] из [npc2.namePos] [npc2.vagina+] и прекрати щупальцевый трах с [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.tentacle+(true)] из [npc2.namePos] [npc2.pussy+], [npc.name] властно [npc.verb(slide)] [npc.tentacleTip+] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.tentacle+(true)] обратно из [npc2.her] [npc2.vagina+], кладя конец грубому щупальцевому траху."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(rub)] [npc.tentacleTip] вверх и вниз по [npc2.her] [npc2.labia+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tentacle+(true)] обратно из [npc2.her] [npc2.vagina+], кладя конец щупальцевому траху."));
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
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.tentacle+(true)] из [npc2.her] [npc2.vagina+], жаждая еще большего внимания [npc.her].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_TENTACLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Подчиниться щупальцам";
		}

		@Override
		public String getActionDescription() {
			return "Вставить [npc2.namePos] [npc2.tentacle+(true)] в свою [npc.vagina+] и позволить щупальцам трахать тебя.";
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
							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] медленно [npc.verb(guide)] ее до [npc.her] [npc.labia+], издавая легкий [npc.moan], прежде чем нежно подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.vagina+].",

							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] ее до [npc.her] [npc.vagina+], а затем медленно отталкивая [npc.her] [npc.hips] назад и издавая тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] жадно [npc.verb(guide)] ее до [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем отчаянно подбросить [npc.her] [npc.hips] и заставить [npc2.herHim] проникнуть в [npc.her] [npc.vagina+].",

							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] ее до [npc.her] [npc.vagina+], а затем жадно толкая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] грубо дергает его вверх до [npc.her] [npc.labia+], издавая [npc.a_moan+], а затем с силой вбивает [npc.her] [npc.hips], заставляя [npc2.herHim] проникать в [npc.her] [npc.vagina+].",

							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.vagina+], а затем жадно вбивает [npc.her] [npc.hips] обратно, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(guide)] его вверх до [npc.her] [npc.labia+], издавая [npc.a_moan+], а затем резко вбивает [npc.her] [npc.hips], заставляя [npc2.herHim] проникать в [npc.her] [npc.vagina+].",

							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.vagina+], а затем толкая [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.tentacle(true)] вперед, пока [npc2.she] [npc2.verb(start)], чтобы щупальцево трахнуть [npc.namePos] [npc.vagina+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, глубоко погружая его в [npc.namePos] [npc.vagina+], пока [npc2.she] [npc2.verb(start)] щупальцевой трах [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.tentacle(true)] вперед, а [npc2.she] с энтузиазмом [npc2.verb(start)] трахает щупальцем [npc.namePos] [npc.vagina+].",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, глубоко погружая его в [npc.namePos] [npc.vagina+], пока [npc2.she] [npc2.verb(start)] энергично трахает щупальцем [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.tentacle(true)] вперед и [npc2.verb(start)], чтобы безжалостно трахать щупальцем [npc.her] [npc.vagina+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle(true)] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает щупальцем [npc.namePos] [npc.vagina+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.tentacle(true)] вперед, словно это [npc2.she] [npc2.verb(start)] щупальце-трахающий [npc.namePos] [npc.vagina+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, глубоко погружая его в [npc.namePos] [npc.vagina+], пока [npc2.she] [npc2.verb(start)] щупальце-трахающий [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.tentacle+(true)] из [npc.her] [npc.vagina+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] глубоко в [npc.her] [npc.vagina+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tentacle+(true)], проникая как можно глубже в [npc.namePos] [npc.vagina+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттянуть [npc2.her] [npc2.tentacle(true)] от [npc.namePos] [npc.pussy], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.tentacle+(true)] глубоко войти в [npc.her] [npc.vagina+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.tentacle(true)] от [npc.namePos] [npc.vagina+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] вылетает изо рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)], проталкивая [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] вылетает изо рта [npc2.namePos], прежде чем [npc2.she] яростно [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle+(true)], насколько это возможно глубоко в [npc.namePos] [npc.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.pussy+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.vagina+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_TENTACLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно трахаемый щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Нежно трахнуть твою [npc.vagina+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко отталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] издает тихий [npc.moan], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.vagina+].",

					"С тихим [npc.moan], [npc.name] мягко [npc.verb(start)] отталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Медленно толкая [npc.her] [npc.hips] назад, мягкий [npc.moan] вытекает из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.tentacle+(true)] глубоко входить в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахаемая щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свою [npc.vagina+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Энергично толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.tentacle+(true)] глубоко входить в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо трахаемая щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свою [npc.vagina+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вбивая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Сильно толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахаемая щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твою [npc.vagina+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] с силой вталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью трахаемый щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свою [npc.vagina+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.vagina+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.vagina+].",

					"Энергично толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.tentacle+(true)] глубоко входить в [npc.her] [npc.vagina+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляясь траху щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свою [npc.vagina+] от [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает, чтобы [npc.verb(start)] в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.vagina+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.vagina+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.vagina+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает, чтобы [npc.verb(start)] в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.vagina+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.vagina+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает, чтобы [npc.verb(start)] в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.vagina+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] грубо вколачивается в [npc.her] [npc.vagina+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.vagina+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить секс щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tentacle(true)] из твоей [npc.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.vagina+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.tentacle(true)] из [npc.her] [npc.vagina+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.vagina+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытащить [npc2.her] [npc2.tentacle(true)] из [npc.her] [npc.vagina+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль киски";
		}

		@Override
		public String getActionDescription() {
			return "Сжать внутренние мышцы своей киски вокруг [npc2.namePos] [npc2.tentacle(true)].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getVaginaOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] [npc.pussy] вокруг [npc2.namePos] [npc2.tentacle+(true)].",

					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] извлекает из [npc.a_moan+] [npc.she], [npc.verb(focus)] контролируя дополнительные мышцы, выстилающие внутренности [npc.her] [npc.pussy]. Сжимая и сдавливая их вокруг [npc2.namePos] [npc2.tentacle+(true)], [npc.name] [npc.verb(cause)] [npc2.herHim], заставляя её непроизвольно вскрикнуть от удовольствия."
						:""),

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], погружаясь в размеренный ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.pussy+] вокруг [npc2.namePos] [npc2.tentacle+(true)].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.pussy], сжимая их и массируя [npc2.namePos] [npc2.tentacle+(true)], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
