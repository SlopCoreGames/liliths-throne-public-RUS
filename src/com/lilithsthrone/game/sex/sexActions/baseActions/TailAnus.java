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
public class TailAnus {
	
	public static final SexAction TEASE_TAIL_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласки хвостом у ануса";
		}

		@Override
		public String getActionDescription() {
			return "Проведи своим [npc.tail(true)] по [npc2.namePos] [npc2.asshole+].";
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
							"Извиваясь, [npc.her] [npc.tail+(true)] тянется к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] медленно дразня [npc.tailTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовый в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(snake)] [npc.her] [npc.tail+(true)] к [npc2.namePos] [npc2.assCloaca+], прежде чем начать нежно водить [npc.tailTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Мягко водя [npc.tailTip+] [npc.her] [npc.tail(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о том, что можно проникнуть в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Извиваясь, [npc.her] [npc.tail+(true)] тянется к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] жадно водя [npc.tailTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tail+(true)] к [npc2.namePos] [npc2.assCloaca+], прежде чем начать жадно водить [npc.tailTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"С жадностью водя [npc.tailTip+] [npc.her] [npc.tail(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Втирая [npc.her] [npc.tail+(true)] в [npc2.namePos] [npc2.assCloaca+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.tailTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовая в любой момент начать трахать [npc2.herHim].",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tail+(true)] до [npc2.namePos] [npc2.assCloaca+], прежде чем начать грубо [npc2.verb(grind)] [npc.tailTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Грубо водя [npc.tailTip+] [npc.her] [npc.tail(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что можно будет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Проскальзывая [npc.her] [npc.tail+(true)] к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] водя [npc.tailTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовая в любой момент проникнуть в [npc2.herHim].",

							"С [npc.a_moan+], [npc.name] [npc.verb(snake)] [npc.her] [npc.tail+(true)] до [npc2.namePos] [npc2.assCloaca+], прежде чем начать водить [npc.tailTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Водя [npc.tailTip+] [npc.her] [npc.tail(true)] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] из [npc.a_moan+] при мысли о том, что можно будет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] назад на [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем нежно толкнуть [npc2.her] [npc2.assCloaca+] назад на [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] [npc2.moanVerb] от восторга, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail(true)], стимулируя [npc2.her] [npc2.asshole+], и мягко [npc2.verb(push)] [npc.her] [npc.hips+] в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] жадно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] назад на [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] отчаянно [npc2.verb(let)] [npc2.moan], прежде чем жадно толкнуть [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail(true)], стимулируя [npc2.her] [npc2.asshole+], и отчаянно [npc2.verb(buck)] [npc.her] [npc.hips+] назад в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] отчаянно [npc2.verb(let)] [npc2.moan], прежде чем грубо толкнуть [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail(true)], стимулируя [npc2.her] [npc2.asshole+], и неистово [npc2.verb(buck)] [npc.her] [npc.hips+] назад в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] отчаянно [npc2.verb(let)] [npc2.moan], прежде чем толкнуть [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.tail(true)].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail(true)], стимулируя [npc2.her] [npc2.asshole+], и [npc2.verb(buck)] [npc.her] [npc.hips+] в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], когда [npc2.she] [npc2.verb(realise)], что [npc2.she] вот-вот может потерять свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере [npc2.her] анальной девственности перед [npc.namePos] [npc.tail+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Я еще никогда не занималась анальным!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! У меня никогда раньше не было анального секса!)]",
										" [npc2.Name] [npc2.sobVerb] в смятении от мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять свою анальную девственность!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.tail].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.tail(true)], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction FORCE_TAIL_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнить хвостом (анал)";
		}

		@Override
		public String getActionDescription() {
			return "Проведи [npc2.tailTip] [npc2.namePos] [npc2.tail(true)] по своей [npc.asshole+].";
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
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)], прежде чем направить его к [npc.her] [npc.assCloaca+]. Медленно двигая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)] и [npc.verb(guide)] его до [npc.her] [npc.assCloaca+], после чего начинает нежно водить [npc2.tailTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tail+(true)], [npc.name] плавно [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.asshole+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)], прежде чем направить его к [npc.her] [npc.assCloaca+]. Нетерпеливо толкая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name], предвещая проникновение в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)] и [npc.verb(guide)] его до [npc.her] [npc.assCloaca+], после чего начинает жадно водить [npc2.tailTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tail+(true)], [npc.name] жадно [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)], прежде чем резко дернуть его вверх до [npc.her] [npc.assCloaca+]. Грубо водя [npc2.tailTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)] и дергая его вверх до [npc.her] [npc.assCloaca+], прежде чем начать грубо [npc.verb(force)] [npc2.tailTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tail+(true)], [npc.name] грубо [npc.verb(grind)] [npc2.tailTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)], прежде чем направить его вверх до [npc.her] [npc.assCloaca+]. Проталкивая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+(true)] и [npc.verb(guide)] его вверх до [npc.her] [npc.assCloaca+], прежде чем начать скользить [npc2.tailTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Сжимая [npc2.namePos] [npc2.tail+(true)], [npc.name] [npc.verb(slide)] [npc2.tailTip+] поверх [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из-под [npc2.namePos] [npc2.lips+], а [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], прежде чем мягко водить [npc2.her] [npc2.tail(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail(true)] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко водит [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой водить [npc2.her] [npc2.tail(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail(true)], получая стимуляцию, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом потирает [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользнуть [npc2.her] [npc2.tail(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail(true)], получая стимуляцию, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] потирает [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользнуть [npc2.her] [npc2.tail(true)] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail(true)], испытывая стимуляцию, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.tail(true)] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.tail+(true)] от [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.tail+(true)] от [npc.namePos] [npc.assCloaca+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)] [npc.name], чтобы отпустить [npc2.her] [npc2.tail(true)]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS);
		}
	};
	
	
	public static final SexAction TAIL_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать тейл-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.tail+(true)] в [npc2.namePos] [npc2.asshole+] и начни тейл-пеггинг [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tailTip+] [npc.her] [npc.tail(true)] по [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуть вперед, погружая [npc.her] [npc.tail+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между ягодицами [npc2.namePos], и с медленным, уверенным напором, [npc.she] осторожно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.her] [npc.tail(true)] по поверхности [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.tail+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] ягодицами и решительным толчком [npc.she] с азартом [npc.verb(sink)] её глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tailTip+] [npc.her] [npc.tail(true)] по [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, с силой вбивая [npc.her] [npc.tail+(true)] глубоко в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] ягодицами и мощным толчком [npc.she] грубо [npc.verb(slam)] её глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.her] [npc.tail(true)] по поверхности [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.tail+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] ягодицами и решительным толчком [npc.she] с азартом [npc.verb(sink)] её глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tailTip+] [npc.her] [npc.tail(true)] над [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+] перед тем как толкнуться вперед, погружая [npc.her] [npc.tail+(true)] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] ягодицами, и коротким толчком [npc.she] [npc.verb(sink)] её глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], когда [npc.tail+(true)] входит в [npc2.herHim], после чего мягко подбрасывая [npc2.her] [npc2.hips] назад, чтобы погрузить её еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] плавно подбрасывая [npc2.her] [npc2.hips] назад, погружая [npc.namePos] [npc.tail+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.tail+(true)] входит в [npc2.herHim], после чего яростно толкая [npc2.her] [npc2.hips] назад, чтобы вогнать её еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно подбрасывая [npc2.her] [npc2.hips] назад, грубо заставляя [npc.name] погружать [npc.her] [npc.tail+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tail+(true)] входит в [npc2.herHim], после чего жадно выгибая [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно выгибая [npc2.her] [npc2.hips] назад, отчаянно помогая вогнать [npc.namePos] [npc.tail+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tail+(true)] входит в [npc2.herHim], после чего выгибая [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] выгибая [npc2.her] [npc2.hips] назад, помогая вогнать [npc.namePos] [npc.tail+(true)] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc.tail+(true)] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно пытаясь отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока незваный [npc.tail(true)] [npc.namePos] глубоко вжимается в [npc2.her] [npc2.asshole+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], позволяя [npc.namePos] [npc.tail+(true)] глубоко погрузиться в [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал хвостом трахать [npc2.herHim].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips+] назад, жадно умоляя [npc.name] продолжать хвостом входить в [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.tail+(true)] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но так и не сумев отстраниться от [npc.namePos] [npc.tail(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащить из [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_sob+] с шумом вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.asshole+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, бегущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], позволяя [npc.namePos] [npc.tail+(true)] глубоко погрузиться в [npc2.her] [npc2.asshole+].",
	
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал хвостом трахать [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать хвостом пеггинг [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tail+(true)] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] назад, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], прося [npc.name] продолжать хвостом пеггинг [npc2.her] [npc2.asshole+].",
	
							" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, медленно выгибая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал хвостом пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать хвостом пеггинг [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tail+(true)] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] назад, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.herHim].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо вбивая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал хвостом пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] назад, приказывая [npc.name] продолжать хвостом трахать [npc2.herHim], пока движения [npc2.her] заставляют [npc.her] [npc.tail+(true)] глубоко входить в [npc2.her] [npc2.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TAIL_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный хвост-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводи и выводи свой [npc.tail(true)] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] его туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(tail-peg)] [npc2.name].",

					"Медленно проталкивая [npc.her] [npc.tail+(true)] в [npc2.namePos] [npc2.asshole+], [npc.name] мягко [npc2.verb(thrust)] его туда-сюда, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(tail-peg)] [npc2.herHim].",

					"Вдвигая [npc.her] [npc.tail+(true)] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(tail-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Хвост-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай вводить и выводить свой [npc.tail+(true)] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)], энергично двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(tail-peg)] [npc2.name].",

					"С энтузиазмом вгоняя [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc2.verb(start)], толкая его туда-сюда, и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tail-peg)] [npc2.herHim].",

					"[npc.her] [npc.tail+(true)] глубоко входит в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением вгоняет и выгоняет его, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tail-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый хвостовый пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Грубо двигай своим [npc.tail+(true)] туда-сюда в [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с силой [npc.verb(start)], энергично двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(tail-peg)] [npc2.name].",

					"Яростно вгоняя [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] грубо [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] напористо [npc.verb(tail-peg)] [npc2.herHim].",

					"Силой вонзая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], грубо вбивая его туда-сюда и жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(tail-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Хвост-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(tail-peg)] [npc2.name].",

					"Втискивая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], совершая толчки туда-сюда и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tail-peg)] [npc2.herHim].",

					"Вбивая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(tail-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный хвостопеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Энергично вбивай свой [npc.tail+(true)] в [npc2.namePos] [npc2.asshole+] туда-сюда.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)], энергично двигая им туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(tail-peg)] [npc2.name].",

					"С энтузиазмом вгоняя [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc2.verb(start)], толкая его туда-сюда, и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(tail-peg)] [npc2.herHim].",

					"[npc.her] [npc.tail+(true)] глубоко входит в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением вгоняет и выгоняет его, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tail-peg)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться хвостопеггингу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tail(true)] из [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.tail(true)] из [npc2.namePos] [npc2.assCloaca], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] нежно сжимает его, прежде чем мягко втиснуть обратно в [npc2.her] [npc2.asshole+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tail(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем плавно втирая [npc2.her] [npc2.assCloaca] назад и вгоняя внутрь [npc2.her] [npc2.asshole+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tail(true)] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.tail+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.tail(true)] из [npc2.namePos] [npc2.assCloaca], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] грубо сжимает его, прежде чем агрессивно втиснуть обратно в [npc2.her] [npc2.asshole+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо вжать [npc2.her] [npc2.assCloaca] назад и заставить его войти внутрь [npc2.her] [npc2.asshole+].",
	
							"В [npc.eyes] [npc.namePos] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail(true)] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.tail+(true)]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tail(true)] из [npc2.namePos] [npc2.assCloaca], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] назад и [npc2.verb(take)] крепко хватает его, прежде чем жадно затолкнуть его обратно в [npc2.her] [npc2.asshole+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем с жадностью втирая [npc2.her] [npc2.assCloaca] назад и заставляя его войти внутрь [npc2.her] [npc2.asshole+].",
	
							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail(true)] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.tail+(true)]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить тейл-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tail+(true)] из [npc2.namePos] [npc2.asshole+] и прекратить тейл-пеггинг [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.tail+(true)] из [npc2.namePos] [npc2.assCloaca+], [npc.name] властно [npc.verb(slide)] [npc.tailTip+] вверх и вниз по [npc2.her] [npc2.asshole+] в последний раз перед тем, как отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.tail+(true)] обратно из [npc2.her] [npc2.asshole+], завершая грубый тейл-пеггинг."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.tail(true)] из [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(rub)] [npc.tailTip] вверх и вниз по [npc2.her] [npc2.asshole+] в последний раз перед тем, как отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tail+(true)] обратно из [npc2.her] [npc2.asshole+], завершая тейл-пеггинг."));
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
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.tail+(true)] из [npc2.her] [npc2.asshole+], жаждая еще большего внимания [npc.her].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_TAIL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Получить хвостом в анус";
		}

		@Override
		public String getActionDescription() {
			return "Вставить [npc2.namePos] [npc2.tail+(true)] в свою [npc.asshole+] и получить хвостом в анус.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] медленно [npc.verb(guide)] ее до [npc.her] [npc.assCloaca+], издавая легкий [npc.moan], прежде чем осторожно толкнуть [npc.her] [npc.hips] назад и заставить [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] ее до [npc.her] [npc.asshole+], прежде чем медленно толкнуть [npc.her] [npc.hips] назад и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+(true)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] жадно [npc.verb(guide)] ее до [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], прежде чем отчаянно толкнуть [npc.her] [npc.hips] назад и заставить [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] ее до [npc.her] [npc.asshole+], прежде чем жадно толкнуть [npc.her] [npc.hips] назад и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] грубо дергает его вверх до [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], а затем с силой вбивает [npc.her] [npc.hips] назад, заставляя [npc2.herHim] проникать в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.asshole+], а затем жадно вбивает [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] в [npc2.her] [npc2.tail+(true)]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(guide)] его вверх до [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], а затем резко дергая [npc.her] [npc.hips] назад и заставляя [npc2.herHim] проникать в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] его вверх до [npc.her] [npc.asshole+], а затем толкая [npc.her] [npc.hips] назад и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] в [npc2.her] [npc2.tail+(true)]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.tail(true)] вперед, чтобы [npc2.she] [npc2.verb(start)] в хвост-трах [npc.namePos] [npc.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] энергично занимается хвостовым пеггингом [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.tail(true)] вперед, пока [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.asshole+] хвостом.",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] энергично занимается хвостовым пеггингом [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.tail(true)] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.asshole+] хвостом.",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tail(true)] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.asshole+] хвостом."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.tail(true)] вперед, словно это [npc2.she] [npc2.verb(start)] хвостом-пеггинг [npc.namePos] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] хвостом-пеггинг [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.tail(true)] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.tail+(true)] из [npc.her] [npc.asshole+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.tail(true)] глубоко в [npc.her] [npc.asshole+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tail+(true)], заталкивая его как можно глубже в [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттянуть [npc2.her] [npc2.tail(true)] от [npc.namePos] [npc.assCloaca], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь вытащить [npc2.her] [npc2.tail(true)] из [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)], заталкивая [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] яростно [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tail+(true)], насколько это возможно глубоко в [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc.her] [npc.assCloaca+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] [npc.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_TAIL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно трахать хвостом";
		}

		@Override
		public String getActionDescription() {
			return "Нежно трахать твою [npc.asshole+] на [npc2.namePos] [npc2.tail+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] тихонько [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(start)] подталкивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Медленно толкая [npc.her] [npc.hips] назад, мягкий [npc.moan] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+(true)] глубоко входить в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тейл-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свою [npc.asshole+] на [npc2.namePos] [npc2.tail+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Энергично толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+(true)] глубоко входить в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый тейл-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свою [npc.asshole+] на [npc2.namePos] [npc2.tail+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вбивая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Сильно толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тейл-пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.asshole+] на [npc2.namePos] [npc2.tail+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tail+(true)] погружаться всё глубже в [npc.her] [npc.asshole+].",

					"Толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью принять хвост";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свою [npc.asshole+] на [npc2.namePos] [npc2.tail+(true)].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкая [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Энергично толкая [npc.her] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+(true)] глубоко входить в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться хвосту";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.asshole+] от [npc2.namePos] [npc2.tail+(true)].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail(true)] из [npc.her] [npc.asshole+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.assCloaca] подальше от нежеланного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.asshole+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+(true)] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail(true)] из [npc.her] [npc.asshole+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.assCloaca] подальше от нежеланного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] неистово вколачивается в [npc.her] [npc.asshole+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+(true)] [npc2.verb(continue)] жадно вбивается глубоко в [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail(true)] из [npc.her] [npc.asshole+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.assCloaca] подальше от нежеланного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] грубо вбивается в [npc.her] [npc.asshole+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+(true)] [npc2.verb(continue)] яростно вбивается глубоко в [npc.her] [npc.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить хвостовой пеггинг";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tail(true)] из твоей [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.tail(true)] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] угрожающе рычит, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.tail(true)] из [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc2.namePos] [npc2.tail(true)] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытащить [npc2.her] [npc2.tail(true)] из [npc.her] [npc.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Анальный контроль";
		}

		@Override
		public String getActionDescription() {
			return "Сжать мышцы своего ануса вокруг [npc2.namePos] [npc2.tail(true)].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getAssOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая внутренние мышцы внутри [npc.her] [npc.asshole] вокруг [npc2.namePos] [npc2.tail+(true)].",

					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] снаружи [npc.a_moan+] как [npc.she] [npc.verb(focus)] на управлении дополнительными мышцами, выстилающими внутренности [npc.her] [npc.asshole]. Сжимая и сдавливая их вокруг [npc2.namePos] [npc2.tail+(true)], [npc.name] [npc.verb(cause)] [npc2.herHim], заставляя невольно вскрикнуть от удовольствия."
						:""),

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], погружаясь в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.asshole+] вокруг [npc2.namePos] [npc2.tail+(true)].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на управлении дополнительными мышцами глубоко внутри [npc.her] [npc.asshole], сжимая их и массируя [npc2.namePos] [npc2.tail+(true)], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
