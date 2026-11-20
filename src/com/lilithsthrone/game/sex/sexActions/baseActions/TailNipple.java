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
 * @since 0.4.9.13
 * @version 0.4.9.13
 * @author Innoxia
 */
public class TailNipple {
	
	public static final SexAction TEASE_TAIL_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дразнящие соски на хвосте";
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.tail+] по [npc2.her] [npc2.nipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tail+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] медленно дразня [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.tail+] к [npc2.namePos] [npc2.breasts+], прежде чем начать нежно водить [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Мягко водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] издает тихий [npc.moan] при мысли о том, что может проникнуть в [npc2.herHim], когда [npc.she] [npc.verb(feel)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tail+] к [npc2.namePos] [npc2.breasts+], [npc.name] с нетерпением [npc.verb(start)] водит [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tail+] к [npc2.namePos] [npc2.breasts+], прежде чем начать с нетерпением водить [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"С жадностью водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вжимая [npc.her] [npc.tail+] в [npc2.namePos] [npc2.breasts+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовая начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tail+] вверх к [npc2.namePos] [npc2.breasts+], после чего она начинает грубо [npc2.verb(grind)] [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Грубо водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tail+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] водит [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовая проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tail+] до [npc2.namePos] [npc2.breasts+], прежде чем начать водить [npc.tailTip] вверх и вниз по одной из [npc2.her] [npc2.nipples+].",

							"Водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.nipple+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] грудь против [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.nipple+] навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.nipple+], и мягко [npc2.verb(push)] [npc.her] грудь в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.nipple+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] грудь против [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно выпятить [npc2.her] грудь против [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.nipple+], и отчаянно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] грудь навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.nipple+], и яростно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, и [npc2.she] [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] из себя [npc2.moan], прежде чем выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.nipple+], и [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет [npc2.her] сосковую девственность.",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] вот-вот потеряет [npc2.her] сосковую девственность.",
										" [npc2.Name] [npc2.sobVerb] в смятении от мысли о том, что [npc2.her] потеряет девственность своего соска перед [npc.namePos] [npc.tail+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Мне еще никогда не трахали соски!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Моим соскам еще никогда не трахали!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении при мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять свою сосковую девственность!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.tail].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.tail], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Убери оттуда руки!)]"));
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE);
		}
	};
	
	public static final SexAction FORCE_TAIL_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дразнить хвостом (сосок)";
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.tailTip] [npc2.her] [npc2.tail] по твоей [npc.nipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем направить его вверх к [npc.her] [npc.breasts+]. Медленно толкая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], прежде чем начать осторожно водить [npc2.tailTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] нежно [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.nipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] под обещание скорого проникновения."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем направить его вверх к [npc.her] [npc.breasts+]. Жадно толкая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], прежде чем начать жадно скользить [npc2.tailTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] жадно [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем резко дернуть его вверх к [npc.her] [npc.breasts+]. Грубо заставляя [npc2.tailTip+] двигаться вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и дергая его к [npc.her] [npc.breasts+], прежде чем начать грубо [npc.verb(force)] [npc2.tailTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] грубо [npc.verb(grind)] [npc2.tailTip+] по [npc.her] [npc.nipple+], вырывая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем направить его к [npc.her] [npc.breasts+]. Двигая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], прежде чем начать скользить [npc2.tailTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] грубо [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.nipple+], вырывая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(start)] потирает [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем нежно водить [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой начать тереть [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге от того, как [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируются, и, стремясь напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.her] [npc.nipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом трет [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно начать скользить [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)] пытается оттянуть [npc2.her] [npc2.tail+] от [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно пытается оттянуть [npc2.her] [npc2.tail+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)] [npc.name] отпустить [npc2.her] [npc2.tail]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE);
		}
	};
	
	
	public static final SexAction TAIL_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Хвостовой секс сосков";
		}
		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.tail+] в [npc2.namePos] [npc2.nipple+] и начни трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, вонзая [npc.her] [npc.tail+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], и медленным, уверенным движением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно вонзая [npc.her] [npc.tail+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] с азартом [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tailTip+] [npc.her] [npc.tail] в [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, вбивая [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], и резким толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно вонзая [npc.her] [npc.tail+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] с азартом [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, вонзая [npc.her] [npc.tail+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.breasts+], и легким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] тихо [npc2.verb(let)] мягкий [npc2.moan], когда [npc.tail+] входит в [npc2.herHim], а затем плавно выпячивая [npc2.her] грудь, чтобы вогнать его еще глубже в [npc2.her] [npc2.breast+(true)].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь, погружая [npc.namePos] [npc.tail+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] громко [npc2.verb(let)] [npc2.a_moan+], когда [npc.tail+] входит в [npc2.herHim], а затем яростно выпячивая [npc2.her] грудь, чтобы вогнать его еще глубже в [npc2.her] [npc2.breast+(true)].",
	
								" С [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] [npc2.her] грудь, грубо заставляя [npc.name] погружать [npc.her] [npc.tail+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.tail+] входит в [npc2.herHim], после чего она жадно выпячивает [npc2.her] грудь, чтобы вогнать её ещё глубже в [npc2.her] [npc2.breast+(true)].",
	
								" При [npc2.a_moan+], [npc2.name] жадно [npc2.verb(push)] [npc2.her] грудь, отчаянно помогая погрузить [npc.namePos] [npc.tail+] ещё глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.tail+] входит в [npc2.herHim], после чего она выпячивает [npc2.her] грудь, чтобы вогнать её ещё глубже в [npc2.her] [npc2.breast+(true)].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] грудь, помогая погрузить [npc.namePos] [npc.tail+] ещё глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.tail+] входит в [npc2.herHim], и, пока с [npc2.her] [npc2.face] текут слёзы, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] мог её вытянуть.",
	
								" При [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, пока с [npc2.her] [npc2.face] текут слёзы, а незваный [npc.namePos] [npc.tail] глубоко вжимается в [npc2.her] [npc2.nipple+]."));
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
							" [npc2.Name] в ответ жадно [npc2.verb(push)] [npc2.her] грудь, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.tail+] глубоко в [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] грудь вперед, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.tail], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил из [npc2.her] [npc2.nipple+].",
		
							" [npc2.lips] вырывается из между [npc2.namePos] [npc2.A_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.nipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(push)] [npc2.her] грудь вперед, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], чтобы [npc.namePos] [npc.tail+] погрузились глубоко в [npc2.her] [npc2.nipple+].",
	
							" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], и, выпятив [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] грудь в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts+].",
	
							" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.her] [npc2.breasts].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо прижимая [npc2.her] грудь к [npc.her] паху, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения заставляют [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежный секс сосков-хвостов";
		}
		@Override
		public String getActionDescription() {
			return "Мягко вводите свой [npc.tail] в [npc2.namePos] [npc2.nipple+] и выводите его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] его туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Медленно втискивая [npc.her] [npc.tail+] в [npc2.namePos] [npc2.nipple+], [npc.name] мягко [npc.verb(thrust)] его вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вдвигая [npc.her] [npc.tail+] в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)] мягко качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Секс сосков-хвостов";
		}

		@Override
		public String getActionDescription() {
			return "Продолжайте вводить свой [npc.tail+] в [npc2.namePos] [npc2.nipple+] туда и обратно.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] с энтузиазмом [npc.verb(start)], совершая толчки туда-сюда и издавая [npc.a_moan+] при каждом движении, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С энтузиазмом втискивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] неистово [npc.verb(thrust)] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"[npc.her] [npc.tail+] глубоко входит в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый секс хвостом-соском";
		}

		@Override
		public String getActionDescription() {
			return "Грубо вгоняй свой [npc.tail+] в [npc2.namePos] [npc2.nipple+] туда-сюда.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С силой вбивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] грубо [npc.verb(start)], совершая толчки туда-сюда и издавая [npc.a_moan+] при каждом движении, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Яростно вгоняя [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] грубо [npc.verb(start)], с силой вбивая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] напористо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С силой вонзая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы грубо вбивать его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Секс сосков-хвостов";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Втискивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вбивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный секс хвостом-соском";
		}

		@Override
		public String getActionDescription() {
			return "Жадно входи и выходи своим [npc.tail+] в [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С энтузиазмом погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)], с восторгом толкая его туда-сюда и издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.breasts].",

					"С энтузиазмом вталкивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] неистово [npc.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"[npc.her] [npc.tail+] глубоко входит в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться траху в сосок";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tail] из [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tail] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его нежно удерживает, прежде чем мягко вдавить его обратно в [npc2.her] [npc2.nipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем мягко вдавить его обратно внутрь [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.tail+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.tail] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] грубым захватом, прежде чем агрессивно затолкнуть его обратно в [npc2.her] [npc2.nipple+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)], прежде чем грубо затолкнуть его обратно в [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.tail+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.tail] из [npc2.namePos] [npc2.nipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепко сжимает его, прежде чем жадно затолкнуть обратно в [npc2.her] [npc2.nipple+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно затолкнуть обратно внутрь [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.tail+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.tail] из [npc2.namePos] [npc2.nipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] [npc2.verb(move)] вперед, удерживая его глубоко засунутым в [npc2.her] [npc2.nipple+].",

						"При [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(move)] вперед, удерживая его вбитым внутрь [npc2.her] [npc2.nipple+].",

						"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, удерживая [npc2.her] [npc2.nipple+] прижатыми к [npc.her] [npc.tail+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить трах в сосок-хвост";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tail+] из [npc2.namePos] [npc2.nipple+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.tail+] из [npc2.namePos] [npc2.nipple+], [npc.name] властно [npc.verb(slide)] [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.breasts+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.tail+] обратно из [npc2.her] [npc2.nipple+], завершая этот грубый трах."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.tail] из [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(rub)] [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.breasts+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tail+] обратно из [npc2.her] [npc2.nipple+], завершая трах."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.nipple(true)], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] на слабо сопротивляющегося [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], чтобы бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.nipple+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.tail+] из [npc2.her] [npc2.nipple+], жаждая еще большего [npc.her] внимания.",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Трах в сосок-хвост";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.tail+] в свою [npc.nipple+] и получись трахнутой.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] медленно [npc.verb(guide)] её до [npc.her] [npc.breasts+], издавая легкий [npc.moan], прежде чем осторожно выпятить [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] её до [npc.her] [npc.nipple+], прежде чем медленно вытолкнуть [npc.her] [npc.breasts] вперед и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] жадно [npc.verb(guide)] её до [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] её до [npc.her] [npc.nipple+], прежде чем с нетерпением вытолкнуть [npc.her] [npc.breasts] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] грубо дергает её до [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем яростно вытолкнуть [npc.her] грудь вперед и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] его до [npc.her] [npc.nipple+], он властно толкает [npc.her] [npc.breasts] вперед, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(guide)] его до [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] его до [npc.her] [npc.nipple+], затем толкая [npc.her] [npc.breasts] вперед и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко толкая [npc2.her] [npc2.tail] вперед, чтобы [npc2.she] [npc2.verb(start)] трахать [npc.namePos] [npc.nipple+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.tail] вперед, глубоко погружая ее в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.tail] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] трахает [npc.namePos] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.tail] вперед, глубоко погружая его в [npc.namePos] [npc.nipple+], пока [npc2.she] энергично [npc2.verb(start)] [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вколачивает [npc2.her] [npc2.tail] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tail] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] безжалостно [npc2.verb(start)] [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.tail] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tail] вперед, глубоко погружая его в [npc.namePos] [npc.nipple+], пока [npc2.she] трахает [npc.herHim] [npc2.verb(start)]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.tail] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.tail+] из [npc.her] [npc.nipple+].",
	
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.verb(force)] [npc2.her] [npc2.tail] deep into [npc.her] [npc.nipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.breast+(true)], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.nipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tail+] как можно глубже в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.tail] от [npc.namePos] [npc.nipple(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.tail+] глубоко в [npc.her] [npc.nipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.tail] от [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.breast+(true)], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" Легкий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)] задвигает [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] от восторга, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.breast+(true)], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается из рта [npc2.namePos], прежде чем [npc2.she] яростно [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] от восторга, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tail+] так глубоко, как только возможно, в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.breast+(true)], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] от восторга, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный секс хвостом-соском";
		}

		@Override
		public String getActionDescription() {
			return "Пусть [npc2.namePos] [npc2.tail+] нежно трахает твой [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подавшись грудью [npc.her] вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(push)] грудью [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Медленно выпячивая [npc.her] грудь, из между [npc.namePos] [npc.lips+] вырывается мягкий [npc.moan], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трах в сосок-хвост";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свой [npc.nipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резко подавшись грудью [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] грудью [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, из между [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый секс в хвост-сосок";
		}

		@Override
		public String getActionDescription() {
			return "Яростно трахай свой [npc.nipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tail+] проникать все глубже в [npc.her] [npc.nipple+].",

					"Сильно выпятив [npc.her] грудь, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трах в сосок-хвост";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.nipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Быстрым толчком [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+] всё глубже в [npc.her] [npc.nipple+].",

					"Выпятив [npc.her] грудь, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно подставить сосок для хвоста";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свой [npc.nipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резко подавшись грудью [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] грудью [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, из между [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться соску-хвосту";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.nipple+] от [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)] наполняют [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tail] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tail+] медленно [npc2.verb(continue)] входит и выходит из [npc.her] [npc.breast+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] нежно [npc2.verb(continue)] скользит глубоко в [npc.her] [npc.nipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)] наполняют [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tail] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tail+] неистово [npc2.verb(continue)] входит и выходит из [npc.her] [npc.breast+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)] наполняют [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tail] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.tail+] [npc2.verb(continue)] грубо вбивается туда-сюда в [npc.her] [npc.breast+(true)].",
	
								"Отчаянно пытаяся оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] [npc2.verb(continue)] неистово вонзается глубоко в [npc.her] [npc.nipple+]."));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)] наполняют [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tail] из [npc.her] [npc.nipple+].",

						"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.tail+] [npc2.verb(remain)] глубоко вклинивается в [npc.her] [npc.breast+(true)].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] [npc2.verb(remain)] глубоко входит в [npc.her] [npc.nipple+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить секс хвостом в сосок";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tail] из твоих [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.tail] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем выдернуть [npc2.her] [npc2.tail] из [npc.her] [npc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Плавно вытягивая [npc2.namePos] [npc2.tail] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.tail] из [npc.her] [npc.nipple+]."));
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
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.name] [npc.verb(stop)] [npc2.herHim] из ебучего [npc.her] [npc.nipple+].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.nipple+]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль соска";
		}

		@Override
		public String getActionDescription() {
			return "Сжимай свой наполненный внутренними мышцами сосок вокруг [npc2.namePos] [npc2.tail].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], напрягая дополнительные внутренние мышцы внутри [npc.her] [npc.nipple(true)], обхватывая ими [npc2.namePos] [npc2.tail+].",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(focus)], управляя дополнительными мышцами, выстилающими внутреннюю часть [npc.her] [npc.nipple(true)]. Сжимая и обхватывая их вокруг [npc2.namePos] [npc2.tail+], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя на размеренный ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.nipple+] вокруг [npc2.namePos] [npc2.tail+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.nipple(true)], сжимая их и массируя [npc2.namePos] [npc2.tail+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
