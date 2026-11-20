package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
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
 * @version 0.4
 * @author Innoxia
 */
public class TentacleAnus {
	
	public static final SexAction TEASE_TENTACLE_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласки анального щупальца";
		}

		@Override
		public String getActionDescription() {
			return "Проведи своим [npc.tentacle(true)] по [npc2.namePos] [npc2.asshole+].";
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
							"Извиваясь, [npc.her] [npc.tentacle+(true)] тянется к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] медленно дразня [npc.tentacleTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовое в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(true)] к [npc2.namePos] [npc2.assCloaca+], прежде чем начать нежно скользить [npc.tentacleTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Мягко скользя [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] издает тихий [npc.moan] при мысли о том, что может проникнуть в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Извиваясь, [npc.her] [npc.tentacle+(true)] тянется к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] жадно скользя [npc.tentacleTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовое в любой момент проникнуть в [npc2.herHim].",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(true)] к [npc2.namePos] [npc2.assCloaca+], прежде чем начать жадно скользить [npc.tentacleTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"С жадностью скользя [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что он сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Стирая [npc.her] [npc.tentacle+(true)] о [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(pull)] немного назад, прежде чем начать скользить [npc.tentacleTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовый начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tentacle+(true)] до [npc2.namePos] [npc2.assCloaca+], прежде чем начать грубо [npc2.verb(grind)] [npc.tentacleTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Грубо втирая [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что он сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Проскальзывая [npc.her] [npc.tentacle+(true)] к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] скользя [npc.tentacleTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tentacle+(true)] до [npc2.namePos] [npc2.assCloaca+], прежде чем начать скользить [npc.tentacleTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Скользя [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о возможности проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] обратно к [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко толкнуть [npc2.her] [npc2.assCloaca+] обратно к [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc2.her] [npc2.asshole+], и нежно [npc2.verb(push)] [npc.her] [npc.hips+] в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] жадно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] обратно к [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно вонзить [npc2.her] [npc2.assCloaca+] назад в [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc2.her] [npc2.asshole+], и отчаянно [npc2.verb(buck)] [npc.her] [npc.hips+] назад в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо вгонять [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc2.her] [npc2.asshole+], и яростно [npc2.verb(buck)] [npc.her] [npc.hips+] назад в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.moan], прежде чем вгонять [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tentacle(true)].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle(true)], стимулируя [npc2.her] [npc2.asshole+], и [npc2.verb(buck)] [npc.her] [npc.hips+] в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], когда [npc2.she] [npc2.verb(realise)], что [npc2.she] вот-вот может потерять свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о том, что потеряет свою анальную девственность перед [npc.namePos] [npc.tentacle+] [npc2.her]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Я еще никогда не занималась анальным!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! У меня никогда раньше не было анального секса!)]",
										" [npc2.Name] [npc2.sobVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять свою анальную девственность!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.tentacle].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, когда [npc2.she] [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.tentacle]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.tentacle(true)], [npc2.speech(Нет! Не надо! Пожалуйста, убирайся от меня!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.name] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
							}
						}
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction FORCE_TENTACLE_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнить щупальце (анус)";
		}

		@Override
		public String getActionDescription() {
			return "Проведи [npc2.tentacleTip] [npc2.namePos] [npc2.tentacle(true)] по своей [npc.asshole+].";
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
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем направить его к [npc.her] [npc.assCloaca+]. Медленно толкая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] и [npc.verb(guide)] его до [npc.her] [npc.assCloaca+], прежде чем начать плавно водить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tentacle+(true)], [npc.name] нежно [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.asshole+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем направить его к [npc.her] [npc.assCloaca+]. Жадно водя [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name], предвещая проникновение в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] и [npc.verb(guide)] его до [npc.her] [npc.assCloaca+], после чего начинает жадно водить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tentacle+(true)], [npc.name] жадно [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем резко дернуть его вверх до [npc.her] [npc.assCloaca+]. Грубо водя [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] и дергая его вверх до [npc.her] [npc.assCloaca+], прежде чем начать грубо [npc.verb(force)] [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tentacle+(true)], [npc.name] грубо [npc.verb(grind)] [npc2.tentacleTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)], прежде чем направить его вверх до [npc.her] [npc.assCloaca+]. Проталкивая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+(true)] и [npc.verb(guide)] его вверх до [npc.her] [npc.assCloaca+], прежде чем начать скользить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Сжимая [npc2.namePos] [npc2.tentacle+(true)], [npc.name] [npc.verb(slide)] [npc2.tentacleTip+] поверх [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], а [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно скользить [npc2.her] [npc2.tentacle(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко скользит [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой втирать [npc2.her] [npc2.tentacle(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] стимулируется, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], а [npc2.she] [npc2.verb(start)] с энтузиазмом трет [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.tentacle(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], а [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.tentacle(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle(true)], и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.tentacle(true)] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.tentacle+(true)] от [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.tentacle+(true)] от [npc.namePos] [npc.assCloaca+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)] [npc.name], чтобы отпустить [npc2.her] [npc2.tentacle(true)]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TENTACLE, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS);
		}
	};
	
	
	public static final SexAction TENTACLE_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать тентакль-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.tentacle+(true)] в [npc2.namePos] [npc2.asshole+] и начни тентакль-пеггинг [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуть вперед, погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] ягодиц и с медленным, ровным давлением, [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по поверхности [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] ягодицами и решительным толчком, [npc.she] с нетерпением [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по поверхности [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, с силой вонзая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] ягодицами и мощным толчком, [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по поверхности [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] ягодицами и решительным толчком, [npc.she] с нетерпением [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Лаская [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, вонзая [npc.her] [npc.tentacle+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] ягодицами, и коротким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], когда [npc.tentacle+(true)] входит в [npc2.herHim], а затем плавно выталкивает [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] тихо [npc2.verb(start)], подбрасывая [npc2.her] [npc2.hips] назад, погружая [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tentacle+(true)] входит в [npc2.herHim], а затем яростно вгоняет [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] яростно [npc2.verb(start)], подбрасывая [npc2.her] [npc2.hips] назад и грубо заставляя [npc.name] погрузить [npc.her] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc.tentacle+(true)] входит в [npc2.herHim], а затем жадно дергает [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно дергает [npc2.her] [npc2.hips] назад, отчаянно помогая вогнать [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc.tentacle+(true)] входит в [npc2.herHim], а затем дергает [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)] дергает [npc2.her] [npc2.hips] назад, помогая вогнать [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+], пока [npc.tentacle+(true)] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно пытаясь отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока незваный [npc.tentacle(true)] [npc.namePos] глубоко входит в [npc2.her] [npc2.asshole+]."));
						break;
					default:
						break;
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
							" [npc2.Name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], позволяя [npc.namePos] [npc.tentacle+(true)] глубоко погрузиться в [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал тентакль-пеггинг [npc2.herHim].",
		
							" В восторге от [npc2.Moaning], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips+] в ответ, умоляя о [npc.name], чтобы продолжался тентакль-пеггинг [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.tentacle(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащился из [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_sob+] с шумом вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.asshole+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, бегущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], позволяя [npc.namePos] [npc.tentacle+(true)] глубоко погрузиться в [npc2.her] [npc2.asshole+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, с силой толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал тентакль-пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать тентакль-пеггинг [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] назад в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(start)] мягко умоляет [npc.name] продолжать тентакль-пеггинг [npc2.her] [npc2.asshole+].",
	
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал тентакль-пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать тентакль-пеггинг [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] назад, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.herHim].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо вбивая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал тентакль-пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] назад, приказывая [npc.name] продолжать тентакль-пеггинг [npc2.herHim], пока движения [npc2.her] вдавливают [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] [npc2.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный тентакль-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Плавно вводите и выводите свой [npc.tentacle(true)] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Плавно погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] его туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(tentacle-peg)] [npc2.name].",

					"Медленно проталкивая [npc.her] [npc.tentacle+(true)] в [npc2.namePos] [npc2.asshole+], [npc.name] мягко [npc2.verb(thrust)] его туда-сюда, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(tentacle-peg)] [npc2.herHim].",

					"Вдвигая [npc.her] [npc.tentacle+(true)] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тентакль-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжайте вводить и выводить свой [npc.tentacle+(true)] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)], энергично входя и выходя, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(tentacle-peg)] [npc2.name].",

					"С энтузиазмом вгоняя [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc2.verb(start)], судорожно входя и выходя, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tentacle-peg)] [npc2.herHim].",

					"[npc.her] [npc.tentacle+(true)] глубоко входит в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с жадностью качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый тентакль-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.tentacle+(true)] в [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] резко [npc.verb(start)], грубо входя и выходя, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(tentacle-peg)] [npc2.name].",

					"Яростно вонзая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] грубо двигая им туда-сюда, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(tentacle-peg)] [npc2.herHim].",

					"С силой вбивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] наружу, пока [npc.a_moan+] [npc.verb(start)] [npc.she], чтобы грубо вколачивать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тентакль-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] качая его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(tentacle-peg)] [npc2.name].",

					"Втискивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] толкая его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tentacle-peg)] [npc2.herHim].",

					"[npc.her] [npc.tentacle+(true)] глубоко входит в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], когда [npc.she] [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный тентакль-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Жадно качай своим [npc.tentacle+(true)] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)], энергично входя и выходя, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(tentacle-peg)] [npc2.name].",

					"С энтузиазмом вгоняя [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc2.verb(start)], судорожно входя и выходя, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tentacle-peg)] [npc2.herHim].",

					"[npc.her] [npc.tentacle+(true)] глубоко входит в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с жадностью качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tentacle-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться тентакль-пеггингу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tentacle(true)] из [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.assCloaca], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] его нежно, прежде чем мягко вдавить его обратно в [npc2.her] [npc2.asshole+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем нежно втиснуть [npc2.her] [npc2.assCloaca] обратно и загнать внутрь [npc2.her] [npc2.asshole+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle(true)] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.tentacle+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.assCloaca], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] его грубо, прежде чем агрессивно вдавить его обратно в [npc2.her] [npc2.asshole+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо вжать [npc2.her] [npc2.assCloaca] назад и заставить его войти внутрь [npc2.her] [npc2.asshole+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tentacle(true)] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.tentacle+(true)]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.assCloaca], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко сжимает его, прежде чем жадно втиснуть его обратно в [npc2.her] [npc2.asshole+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем с жадностью втирать [npc2.her] [npc2.assCloaca] назад и заставлять его войти внутрь [npc2.her] [npc2.asshole+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] на [npc.her] [npc.tentacle+(true)]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить тентакль-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Вытащить свой [npc.tentacle+(true)] из [npc2.namePos] [npc2.asshole+] и прекратить тентакль-пеггинг [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.tentacle+(true)] из [npc2.namePos] [npc2.assCloaca+], [npc.name] властно [npc.verb(slide)] [npc.tentacleTip+] вверх и вниз по [npc2.her] [npc2.asshole+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.tentacle+(true)] обратно из [npc2.her] [npc2.asshole+], завершая грубый тентакль-пеггинг."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(rub)] [npc.tentacleTip] вверх и вниз по [npc2.her] [npc2.asshole+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tentacle+(true)] обратно из [npc2.her] [npc2.asshole+], завершая тентакль-пеггинг."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.asshole], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] на слабо сопротивляющегося [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.asshole+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.tentacle+(true)] из [npc2.her] [npc2.asshole+], жаждая еще большего внимания [npc.her].",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Подставиться под щупальце";
		}

		@Override
		public String getActionDescription() {
			return "Ввести [npc2.namePos] [npc2.tentacle+(true)] в свою [npc.asshole+] и подставиться под щупальце.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] медленно [npc.verb(guide)] ее до [npc.her] [npc.assCloaca+], издавая легкий [npc.moan], прежде чем осторожно отбросить [npc.her] [npc.hips] назад и заставить [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].",

							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] ее до [npc.her] [npc.asshole+], прежде чем медленно оттолкнуть [npc.her] [npc.hips] назад и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] жадно [npc.verb(guide)] ее до [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], прежде чем отчаянно отбросить [npc.her] [npc.hips] назад и заставить [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].",

							"Сжимая [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] ее до [npc.her] [npc.asshole+], прежде чем жадно толкнуть [npc.her] [npc.hips] назад и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] грубо дергает его вверх до [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], а затем с силой вбивает [npc.her] [npc.hips] назад, заставляя [npc2.herHim] проникать в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.asshole+], а затем жадно вбивает [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(guide)] его вверх до [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], а затем резко дергает [npc.her] [npc.hips] назад, заставляя [npc2.herHim] проникать в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.asshole+], а затем толкая [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+(true)]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.tentacle(true)] вперед, пока [npc2.she] [npc2.verb(start)], чтобы щупальцево трахнуть [npc.namePos] [npc.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] энергично занимается тентакль-пеггингом [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], с нетерпением толкая [npc2.her] [npc2.tentacle(true)] вперед, пока [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.asshole+] тентаклями.",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] энергично занимается тентакль-пеггингом [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вгоняет [npc2.her] [npc2.tentacle(true)] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.asshole+] тентаклями.",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle(true)] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.asshole+] тентаклями."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.tentacle(true)] вперед как [npc2.she] [npc2.verb(start)] щупальце-трахающий [npc.namePos] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, погружая его глубоко в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] щупальце-пеггинг [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.tentacle+(true)] из [npc.her] [npc.asshole+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name] как [npc.she] [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] глубоко в [npc.her] [npc.asshole+]."));
						break;
					default:
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tentacle+(true)], заталкивая его как можно глубже в [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттянуть [npc2.her] [npc2.tentacle(true)] от [npc.namePos] [npc.assCloaca], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] с треском вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.tentacle+(true)] глубоко войти в [npc.her] [npc.asshole+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] оттянуть [npc2.her] [npc2.tentacle(true)] от [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вылетает изо рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)], проталкивая [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вылетает изо рта [npc2.namePos], прежде чем [npc2.she] яростно [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle+(true)], насколько это возможно глубоко в [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] [npc.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Мягкий тентакл-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Мягко трахать твой [npc.asshole+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] издает тихий [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С тихим [npc.moan], [npc.name] мягко [npc.verb(start)] подталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Медленно толкая [npc.her] [npc.hips] назад, из-за [npc.namePos] [npc.lips+] со свистом вырывается мягкая [npc.moan], пока [npc.her] движения заталкивают [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Пеггинг щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Страстно трахай свою [npc.asshole+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] из-за [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Энергично толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока [npc.her] движения заталкивают [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый пеггинг щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свою [npc.asshole+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] из-за [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вбивая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Сильно толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения грубо вгоняют [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Пеггинг щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твою [npc.asshole+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью принять щупальце в анус";
		}

		@Override
		public String getActionDescription() {
			return "Страстно трахай свою [npc.asshole+] на [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] из-за [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Энергично толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока [npc.her] движения заталкивают [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться щупальцу в анусе";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свою [npc.asshole+] от [npc2.namePos] [npc2.tentacle+(true)].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.asshole+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.asshole+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.asshole+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.asshole+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.asshole+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] грубо вколачивается в [npc.her] [npc.asshole+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] яростно вбивается глубоко в [npc.her] [npc.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить затыкание щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tentacle(true)] из твоей [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.tentacle(true)] из [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.tentacle(true)] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытащить [npc2.her] [npc2.tentacle(true)] из [npc.her] [npc.asshole+]."));
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
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы перестать трахать [npc.her] [npc.asshole+].",
	
								" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.asshole+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Анальный контроль";
		}

		@Override
		public String getActionDescription() {
			return "Сжать мышцы своего ануса вокруг [npc2.namePos] [npc2.tentacle(true)].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getAssOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая внутренние мышцы внутри [npc.her] [npc.asshole] вокруг [npc2.namePos] [npc2.tentacle+(true)].",

					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] извлекает из [npc.a_moan+] [npc.she], [npc.verb(focus)] контролируя дополнительные мышцы, выстилающие внутренности [npc.her] [npc.asshole]. Сжимая и сдавливая их вокруг [npc2.namePos] [npc2.tentacle+(true)], [npc.name] [npc.verb(cause)] [npc2.herHim], заставляя издать непроизвольный крик удовольствия."
						:""),

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.asshole+] вокруг [npc2.namePos] [npc2.tentacle+(true)].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.asshole], сжимая их и массируя [npc2.namePos] [npc2.tentacle+(true)], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
