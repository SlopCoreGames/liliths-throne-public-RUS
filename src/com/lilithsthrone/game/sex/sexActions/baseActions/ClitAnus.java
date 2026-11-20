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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class ClitAnus {
	
	public static final SexAction TEASE_CLIT_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Анальная стимуляция клитора";
		}
		@Override
		public String getActionDescription() {
			return "Скользите вашим [npc.clit] по [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Двигая [npc.her] [npc.clit+] вверх до [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] медленно лаская [npc.clitTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С мягким [npc.moan], [npc.name] [npc.verb(move)] [npc.her] [npc.clit+] вверх до [npc2.namePos] [npc2.assCloaca+], прежде чем начать нежно скользить [npc.clitTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Мягко скользя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о возможности проникнуть в [npc2.herHim], когда [npc.she] [npc.verb(feel)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Двигая [npc.her] [npc.clit+] вверх до [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] жадно скользя [npc.clitTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С [npc.a_moan+], [npc.name] [npc.verb(move)] [npc.her] [npc.clit+] вверх до [npc2.namePos] [npc2.assCloaca+], прежде чем начать жадно скользить [npc.clitTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Жадно водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Втирая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.assCloaca+], [npc.name] немного [npc.verb(pull)] назад, прежде чем начать водить [npc.clitTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовая начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.clit+] вверх к [npc2.namePos] [npc2.assCloaca+], прежде чем начать грубо [npc2.verb(grind)] [npc.clitTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Грубо водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Двигая [npc.her] [npc.clit+] вверх к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(start)] водя [npc.clitTip+] вверх и вниз по [npc2.her] [npc2.asshole+], готовая проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(move)] [npc.her] [npc.clit+] до [npc2.namePos] [npc2.assCloaca+], прежде чем начать водить [npc.clitTip] вверх и вниз по [npc2.her] [npc2.asshole+].",

							"Водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.asshole+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] обратно к [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко толкнуть [npc2.her] [npc2.assCloaca+] обратно к [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.asshole+], и мягко [npc2.verb(push)] [npc.her] [npc.hips+] в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.asshole+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] обратно к [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно толкнуть [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.asshole+], и отчаянно [npc2.verb(buck)] [npc.her] [npc.hips+] назад в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо толкнуть [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.asshole+], и неистово [npc2.verb(buck)] [npc.her] [npc.hips+] назад в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.asshole+] стимулируется, и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.moan], прежде чем толкнуть [npc2.her] [npc2.assCloaca+] назад против [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.asshole+], а [npc2.verb(buck)] [npc.her] [npc.hips+] в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isAssVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] может вот-вот потерять свою анальную девственность [npc2.her].",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере анальной девственности [npc2.her] из-за [npc.namePos] [npc.clit+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Я никогда раньше не занималась анальным сексом!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Я никогда не делала анально!)]",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять свою анальную девственность!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.clit].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.assCloaca] от [npc.namePos] [npc.clit], [npc2.speech(Нет! Не надо! Пожалуйста, уйди от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS);
		}
	};
	
	public static final SexAction FORCE_CLIT_OVER_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Ласкать клитор (анально)";
		}
		@Override
		public String getActionDescription() {
			return "Проведи [npc2.clitTip] [npc2.namePos] [npc2.clit] по своей [npc.asshole+].";
		}
		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить её к [npc.her] [npc.assCloaca+]. Медленно толкая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] её к [npc.her] [npc.assCloaca+], прежде чем начать нежно водить [npc2.clitTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] осторожно [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.asshole+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении скорого проникновения."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем подвести её к [npc.her] [npc.assCloaca+]. Жадно толкая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] её к [npc.her] [npc.assCloaca+], после чего она начинает жадно скользить [npc2.clitTip] вверх и вниз по [npc.her], [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] жадно [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем резко дернуть её к [npc.her] [npc.assCloaca+]. Грубо толкая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и резким рывком вверх к [npc.her] [npc.assCloaca+], после чего она начинает грубо [npc.verb(force)] [npc2.clitTip] вверх и вниз по [npc.her], [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] грубо [npc.verb(grind)] [npc2.clitTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить его к [npc.her] [npc.assCloaca+]. Двигая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.asshole+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] его к [npc.her] [npc.assCloaca+], прежде чем начать скользить [npc2.clitTip] вверх и вниз по [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] грубо [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.asshole+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(start)] потирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем нежно водить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой втирая [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом растирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользя [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем начать скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)] пытается оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.asshole+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.assCloaca+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)] [npc.name], чтобы отпустить [npc2.her] [npc2.clit]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS);
		}
	};
	
	
	public static final SexAction CLIT_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Начать клиторный пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.clit+] в [npc2.namePos] [npc2.asshole+] и начни трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.clitTip+] [npc.her] [npc.clit] поверх [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] ягодицами, и медленным, уверенным давлением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.clitTip+] [npc.her] [npc.clit] поверх [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] ягодицами, и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.clitTip+] [npc.her] [npc.clit] поверх [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, вбивая [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] ягодицами, и мощным толчком [npc.she] грубо [npc.verb(slam)] её глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.clitTip+] [npc.her] [npc.clit] поверх [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] ягодицами, и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем толкнуться вперед, погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.asshole+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] между [npc2.namePos] ягодицами, и легким толчком [npc.she] [npc.verb(sink)] её глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], когда [npc.clit+] входит в [npc2.herHim], после чего она плавно выгибает [npc2.her] [npc2.hips] назад, чтобы вогнать её еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно выгибает [npc2.her] [npc2.hips] назад, погружая [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она яростно выгибает [npc2.her] [npc2.hips] назад, чтобы вогнать её еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно выгибает [npc2.her] [npc2.hips] назад, грубо заставляя [npc.name] погружаться [npc.her] [npc.clit+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она жадно выгибает [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] с жадностью [npc2.verb(start)] выгибает [npc2.her] [npc2.hips] назад, отчаянно помогая погрузить [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она выгибает [npc2.her] [npc2.hips] назад, чтобы вогнать его еще глубже в [npc2.her] [npc2.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] выгибает [npc2.her] [npc2.hips] назад, помогая погрузить [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.clit+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, пока слезы текут по [npc2.her] [npc2.face], а незваный [npc.namePos] [npc.clit] глубоко вжимается в [npc2.her] [npc2.asshole+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], позволяя [npc.namePos] [npc.clit+] глубоко погрузиться в [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал клитор-пеггинг [npc2.herHim].",
		
							" От восторга [npc2.Moaning], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.hips+] в ответ, умоляя о [npc.name], чтобы [npc2.herHim] продолжал трахать [npc2.her], пока движения [npc.her] помогают погрузить [npc.clit+] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь (и не сумев) отстраниться от [npc.namePos] [npc.clit], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил его из [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.asshole+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ слабо [npc2.verb(thrust)] [npc2.her] [npc2.hips], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], заставляя [npc.namePos] [npc.clit+] глубоко погружаться в [npc2.her] [npc2.asshole+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, с силой отбрасывая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал клитор-пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] [npc2.hips+] в ответ, умоляя, чтобы [npc.name] продолжал трахать [npc2.herHim], пока движения [npc2.her] помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)] умоляет [npc.name] продолжать клиторный пеггинг [npc2.her] [npc2.asshole+].",
	
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выгибая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал клитор-пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] назад, умоляя [npc.name] продолжать трахать [npc2.herHim], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(buck)] [npc2.her] [npc2.hips] назад в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.herHim].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо толкая [npc2.her] [npc2.hips] назад, [npc2.she] [npc2.verb(order)] [npc.name] продолжать клитор-пеггинг [npc2.herHim].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips+] назад, приказывая [npc.name] продолжать трахать [npc2.herHim], пока [npc2.her] движения вбивают [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Плавно вводите и выводите свой [npc.clit] из [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Плавно погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] двигая им туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.name].",

					"Медленно проталкивая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.asshole+], [npc.name] мягко [npc2.verb(thrust)] его туда-сюда, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.herHim].",

					"Скользя [npc.her] [npc.clit+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно двигать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Продолжай входить своим [npc.clit+] в [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом проталкивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc2.verb(start)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вбивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно двигать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Грубо вгонять и выгонять свой [npc.clit+] в [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вбивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] грубо толкая его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] брутально [npc.verb(fuck)] [npc2.name].",

					"Яростно вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] грубо толкая его туда-сюда, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.herHim].",

					"Силой вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] наружу, пока [npc.a_moan+] [npc.verb(start)] [npc.she], грубо вбивая его туда-сюда и вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] толкая его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"Вталкивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] толкая его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"[npc.her] [npc.clit+] глубоко входит в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы вгонять и выгонять его, вдыхая [npc2.namePos] [npc2.scent], когда [npc.she] [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадное клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Энергично вгоняй свой [npc.clit+] туда-сюда в [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.name].",

					"С энтузиазмом проталкивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc2.verb(start)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вбивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно двигать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться клитор-пеггингу";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.clit] из [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.assCloaca], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] нежно сжимает его, прежде чем мягко втиснуть обратно в [npc2.her] [npc2.asshole+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем мягко втирать [npc2.her] [npc2.assCloaca] обратно и силой вгонять внутрь [npc2.her] [npc2.asshole+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] мягко [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.assCloaca], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] крепко сжимает его, прежде чем агрессивно втиснуть обратно в [npc2.her] [npc2.asshole+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо впечатать [npc2.her] [npc2.assCloaca] обратно и затолкнуть внутрь [npc2.her] [npc2.asshole+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.clit+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.assCloaca], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(reach)] обратно и [npc2.verb(take)] крепко сжимает его, прежде чем с жадностью втиснуть обратно в [npc2.her] [npc2.asshole+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем жадно втиснуть [npc2.her] [npc2.assCloaca] обратно, заставляя её войти внутрь [npc2.her] [npc2.asshole+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.clit+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.assCloaca], но усилия [npc.her] оказываются напрасными, так как [npc2.name] [npc2.verb(push)] обратно, удерживая [npc.her] [npc.clit+] застрявшим в [npc2.her] [npc2.asshole+].",

						"При [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(push)] обратно и [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] против [npc.herHim].",

						"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(react)] и [npc2.verb(force)] [npc2.her] [npc2.asshole+] вниз на [npc.her] [npc.clit+]."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.clit+] из [npc2.namePos] [npc2.asshole+] и хватит трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.clit+] из [npc2.namePos] [npc2.assCloaca+], [npc.name] властно [npc.verb(slide)] [npc.clitTip+] вверх и вниз по [npc2.her] [npc2.asshole+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.clit+] обратно из [npc2.her] [npc2.asshole+], завершая этот грубый анальный секс."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.clit] из [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(rub)] [npc.clitTip] вверх и вниз по [npc2.her] [npc2.asshole+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.clit+] обратно из [npc2.her] [npc2.asshole+], завершая анальный секс."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.asshole], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)], слабо сопротивляясь [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.asshole+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.clit+] из [npc2.her] [npc2.asshole+], жаждая еще большего внимания [npc.her].",
	
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания [npc.namePos]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Получить клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.clit+] в свою [npc.asshole+] и позволь отъебать себя в анус.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] медленно [npc.verb(guide)] ее к [npc.her] [npc.assCloaca+], издавая легкий [npc.moan], прежде чем мягко отбросить [npc.her] [npc.hips] назад и заставляя [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] ее к [npc.her] [npc.asshole+], прежде чем медленно толкнуть [npc.her] [npc.hips] назад и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] в [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] жадно [npc.verb(guide)] ее к [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], прежде чем отчаянно отбросить [npc.her] [npc.hips] назад и заставляя [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.asshole+], она жадно вбивает [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] грубо дергает её вверх до [npc.her] [npc.assCloaca+], издавая [npc.a_moan+], прежде чем яростно впечатать [npc.her] [npc.hips] назад, заставляя [npc2.herHim] проникать в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.asshole+], она жадно вбивает [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(guide)] её до [npc.her] [npc.assCloaca+], она издает [npc.a_moan+], прежде чем резко дернуть [npc.her] [npc.hips] назад, заставляя [npc2.herHim] проникать в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.asshole+], она толкает [npc.her] [npc.hips] назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко толкая [npc2.her] [npc2.clit] вперед, так что [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] анально трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.clit] вперед, так что [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.clit] вперед и [npc2.verb(start)], чтобы беспощадно трахать [npc.her] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit] вперед, желая напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] беспощадно трахает [npc.namePos] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.clit] вперед как [npc2.she] [npc2.verb(start)] чертов [npc.namePos] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая его в [npc.namePos] [npc.asshole+], пока [npc2.she] [npc2.verb(start)] чертов [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.clit] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.clit+] из [npc.her] [npc.asshole+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.clit] глубоко в [npc.her] [npc.asshole+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.assCloaca+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+].",
		
							" От восторга [npc2.Moaning], [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.clit+] как можно глубже в [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттянуть [npc2.her] [npc2.clit] от [npc.namePos] [npc.assCloaca], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] с треском вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.clit+] глубоко в [npc.her] [npc.asshole+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.clit] от [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+], издавая тихий, приглушенный [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.assCloaca+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)], задвигая [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.assCloaca+].",
		
							" Приглушенный [npc2.moan] срывается с [npc2.namePos] рта, прежде чем [npc2.she] [npc2.verb(start)] яростно вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit+] так глубоко, как только возможно, в [npc.namePos] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.assCloaca+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Мягкий клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Мягко трахать твой [npc.asshole+] на [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко отодвигая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] вырывается тихий [npc.moan], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+].",

					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(start)] отодвигает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Медленно вгоняя [npc.namePos] [npc.hips] назад, тихий [npc.moan] срывается с [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.clit+] глубоко входить в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Страстно трахать твой [npc.asshole+] на [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] вбивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Энергично толкая [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо трахая клитором";
		}
		@Override
		public String getActionDescription() {
			return "Страстно трахать твой [npc.asshole+] на [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(start)] вбивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Сильно толкая [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_CLIT_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.asshole+] на [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Отталкивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] вколачивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Вколачивая [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.clit+] глубоко уходить в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью принять клитор-пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Страстно трахать твой [npc.asshole+] на [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично толкая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] вбивает [npc.her] [npc.hips] назад, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Энергично толкая [npc.namePos] [npc.hips] назад, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться клитор-пеггингу";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.asshole+] от [npc2.namePos] [npc2.clit+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего [npc.her] [npc.eyes] наполняется, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+] наружу, прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.asshole+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.assCloaca] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] медленно входит и выходит из [npc.her] [npc.asshole+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего [npc.her] [npc.eyes] наполняется, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+] наружу, прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.asshole+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca] от нежелательного проникновения в [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.asshole+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего [npc.her] [npc.eyes] наполняется, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+] наружу, прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.asshole+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca] от нежелательного проникновения в [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] грубо вбивается в [npc.her] [npc.asshole+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
			} else  {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего [npc.her] [npc.eyes] наполняется, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+] наружу, прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.asshole+].",

						"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.assCloaca] подальше от нежелательного проникновения [npc2.namePos], в то время как [npc2.her] [npc2.clit+] [npc2.verb(remain)] глубоко в [npc.her] [npc.asshole+].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.hips], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(remain)] глубоко врываются в [npc.her] [npc.asshole+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить клиторный пеггинг";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.clit] из твоей [npc.asshole+].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.clit] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] с угрожающим рычанием, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.clit] из [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.clit] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытащить [npc2.her] [npc2.clit] из [npc.her] [npc.asshole+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] с облегчением, но вскоре это переходит в [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
								" При [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], чтобы протестовать и вырываться против [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] плотно на месте."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.name] [npc.verb(stop)] [npc2.herHim] от трахания [npc.her] [npc.asshole+].",
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.asshole+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getAssOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		@Override
		public String getActionTitle() {
			return "Контроль ануса";
		}
		@Override
		public String getActionDescription() {
			return "Сжать мышцами ануса [npc2.namePos] [npc2.clit].";
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] [npc.asshole] вокруг [npc2.namePos] [npc2.clit+].",

					isTargetedCharacterInanimate()?null
						:"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(focus)], контролируя дополнительные мышцы, выстилающие внутреннюю часть [npc.her] [npc.asshole]. Сжимая их вокруг [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(cause)] [npc2.herHim], невольно вскрикивая от удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя на размеренный ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.asshole+] вокруг [npc2.namePos] [npc2.clit+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.asshole], сжимая их и массируя [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
