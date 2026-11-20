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
public class TentacleNipple {
	
	public static final SexAction TEASE_TENTACLE_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дразнящие щупальца-соски";
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.tentacle+] по [npc2.her] [npc2.nipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] медленно дразня [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.breasts+], прежде чем начать нежно скользить [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Мягко скользя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] издает тихий [npc.moan] при мысли о том, что может проникнуть в [npc2.herHim], когда [npc.she] [npc.verb(feel)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.breasts+], [npc.name] с нетерпением [npc.verb(start)] скользит [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.breasts+], прежде чем начать с нетерпением скользить [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Жадно водя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Втирая [npc.her] [npc.tentacle+] в [npc2.namePos] [npc2.breasts+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовая начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.breasts+], прежде чем начать грубо [npc2.verb(grind)] [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Грубо водя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] водит [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовая проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tentacle+] до [npc2.namePos] [npc2.breasts+], прежде чем начать скользить [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Скользя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, и [npc2.she] нежно [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.nipple+] навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.nipple+], и нежно [npc2.verb(push)] [npc.her] грудь в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, и [npc2.she] жадно [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.nipple+], и отчаянно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, а [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.nipple+], и неистово [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, а [npc2.she] [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.moan], прежде чем выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.nipple+], и [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет [npc2.her] сосковую девственность.",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] вот-вот потеряет [npc2.her] сосковую девственность.",
										" [npc2.Name] [npc2.sobVerb] в смятении от мысли о потере девственности [npc2.her] соска перед [npc.namePos] [npc.tentacle+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Мне еще никогда не трахали соски!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Моим соскам еще никогда не трахали!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении при мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять свою сосковую девственность!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.tentacle].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.tentacle], [npc2.speech(Нет! Не надо! Пожалуйста, убирайся от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE);
		}
	};
	
	public static final SexAction FORCE_TENTACLE_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Щекочущий щупальце (сосок)";
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.tentacleTip] [npc2.her] [npc2.tentacle] по твоей [npc.nipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем направить его вверх к [npc.her] [npc.breasts+]. Медленно толкая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], прежде чем начать нежно водить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Сжимая [npc2.namePos] [npc2.tentacle+], [npc.name] нежно [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.nipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем направить его к [npc.her] [npc.breasts+]. Нетерпеливо толкая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], после чего начинает жадно водить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Сжимая [npc2.namePos] [npc2.tentacle+], [npc.name] с нетерпением [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем резко дернуть его к [npc.her] [npc.breasts+]. Грубо толкая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"Схватив [npc.a_moan+] [npc.name], [npc2.namePos] [npc.verb(grab)] [npc2.tentacle+] и резко дернув его вверх до [npc.her] [npc.breasts+], прежде чем начать грубо [npc.verb(force)] [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Сжимая [npc2.namePos] [npc2.tentacle+], [npc.name] грубо [npc.verb(grind)] [npc2.tentacleTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем направить его вверх к [npc.her] [npc.breasts+]. Проталкивая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+] и [npc.verb(guide)] его вверх до [npc.her] [npc.breasts+], прежде чем начать скользить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Сжимая [npc2.namePos] [npc2.tentacle+], [npc.name] [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно скользить [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко скользя [npc2.her] [npc2.her] [npc2.tentacle] вверх-вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.tentacle] вверх-вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], прежде чем с силой втирая [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, стремясь напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.tentacle] вверх-вниз по [npc.her] [npc.nipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом растирая [npc2.her] [npc2.her] [npc2.tentacle] вверх-вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем с нетерпением начать скользить [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] с жадностью скользит [npc2.her] [npc2.her] [npc2.tentacle] вверх-вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her], [npc2.tentacle] вверх-вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.tentacle] вверх-вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)] пытается оттянуть [npc2.her] [npc2.tentacle+] от [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.tentacle+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)] [npc.name], не давая отпустить [npc2.her] [npc2.tentacle]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TENTACLE, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE);
		}
	};
	
	
	public static final SexAction TENTACLE_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Тентаклевый секс сосков";
		}
		@Override
		public String getActionDescription() {
			return "Погрузи свой [npc.tentacle+] в [npc2.namePos] [npc2.nipple+] и начни трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуть вперед, погружая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.breasts+], и медленным, уверенным давлением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуть вперед, жадно погружая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.tentacleTip+] [npc.her] [npc.tentacle] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], а затем яростно подаваясь вперед, вбивая [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] по [npc2.namePos] [npc2.breasts+] и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуть вперед, жадно погружая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, вонзая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] по [npc2.namePos] [npc2.breasts+] и легким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.tentacle+] входит в [npc2.herHim], а затем плавно выталкивая [npc2.her] грудь, чтобы вонзить его еще глубже в [npc2.her] [npc2.breast+(true)].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь, погружая [npc.namePos] [npc.tentacle+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tentacle+] входит в [npc2.herHim], а затем яростно выпячивая [npc2.her] грудь, чтобы вогнать её ещё глубже в [npc2.her] [npc2.breast+(true)].",
	
								" С [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] из [npc2.her] груди, грубо заставляя [npc.name] погружаться, [npc.her] [npc.tentacle+] ещё глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tentacle+] входит в [npc2.herHim], а затем жадно выпячивая [npc2.her] грудь, чтобы погрузить её ещё глубже в [npc2.her] [npc2.breast+(true)].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(push)] из [npc2.her] груди, отчаянно помогая погрузить [npc.namePos] [npc.tentacle+] ещё глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tentacle+] входит в [npc2.herHim], а затем выпячивая [npc2.her] грудь, чтобы погрузить её ещё глубже в [npc2.her] [npc2.breast+(true)].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] из [npc2.her] груди, помогая погрузить [npc.namePos] [npc.tentacle+] ещё глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], когда [npc.tentacle+] входит в [npc2.herHim], и, со слезами, текущими по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] мог её вытащить.",
	
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока [npc.namePos] незваное [npc.tentacle] глубоко вжимается в [npc2.her] [npc2.nipple+]."));
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
							" [npc2.Name] в ответ жадно [npc2.verb(push)] [npc2.her] грудь, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], заставляя [npc.namePos] [npc.tentacle+] глубоко погружаться в [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] грудь, умоляя о [npc.name], чтобы [npc2.her] продолжал трахать [npc2.breasts], пока движения [npc2.her] помогают загнать [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.tentacle], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил его из [npc2.her] [npc2.nipple+].",
		
							" [npc2.lips] вырывается из между [npc2.namePos] [npc2.A_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.nipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ жадно [npc2.verb(push)] [npc2.her] грудь, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], заставляя [npc.namePos] [npc.tentacle+] глубоко погружаться в [npc2.her] [npc2.nipple+].",
	
							" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], и, выпятив [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] грудь в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts+].",
	
							" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.her] [npc2.breasts].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо прижимая [npc2.her] грудь к [npc.her] паху, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] грудью в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения вгоняют [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежный секс щупальцем-соском";
		}
		@Override
		public String getActionDescription() {
			return "Нежно вводите и выводите свой [npc.tentacle] в [npc2.namePos] [npc2.nipple+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] плавно [npc.verb(start)] его туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Медленно втискивая [npc.her] [npc.tentacle+] в [npc2.namePos] [npc2.nipple+], [npc.name] мягко [npc.verb(thrust)] его вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Скользя [npc.her] [npc.tentacle+] в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко проталкивать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Секс соском-щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай входить своим [npc.tentacle+] в [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С энтузиазмом втискивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] неистово [npc.verb(thrust)] его вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вколачивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно проталкивать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый секс соском-щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи своим [npc.tentacle+] в [npc2.namePos] [npc2.nipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вбивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] резко толкая его туда-сюда, издавая [npc.a_moan+] при каждом рывке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Яростно вонзая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] грубо вбивая его туда-сюда, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С силой вгоняя [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] из-за [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы грубо вбивать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Секс соском-щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] толкая его туда-сюда, издавая [npc.a_moan+] при каждом движении, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вталкивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] толкая его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"[npc.her] [npc.tentacle+] глубоко входит в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], когда [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахаться соском-щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Энергично вгоняй свой [npc.tentacle+] в [npc2.namePos] [npc2.nipple+] и вынимай его.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.breasts].",

					"С энтузиазмом вталкивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] лихорадочно [npc.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"[npc.her] [npc.tentacle+] глубоко входит в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться соском-щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tentacle] из [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытянуть [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.nipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его нежным захватом, прежде чем мягко вдавить его обратно в [npc2.her] [npc2.nipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно вдавить обратно в [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.tentacle+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытянуть [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.nipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его грубым захватом, прежде чем агрессивно вдавить его обратно в [npc2.her] [npc2.nipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо вдавить обратно в [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.tentacle+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепкую хватку, прежде чем жадно затолкнуть его обратно в [npc2.her] [npc2.nipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] это, прежде чем жадно затолкнуть его обратно внутрь [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.tentacle+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] [npc2.verb(move)] вперед, удерживая его глубоко внутри [npc2.her] [npc2.nipple+].",

						"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(move)] вперед, удерживая его глубоко внутри [npc2.her] [npc2.nipple+].",

						"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, удерживая [npc2.her] [npc2.nipple+] прижатым к [npc.her] [npc.tentacle+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить щупальцевый секс сосков";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tentacle+] из [npc2.namePos] [npc2.nipple+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.tentacle+] из [npc2.namePos] [npc2.nipple+], [npc.name] властно [npc.verb(slide)] водит [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.breasts+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] вытаскивает [npc.her] [npc.tentacle+] из [npc2.her] [npc2.nipple+], завершая грубый секс."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"В последний раз [npc.name] [npc.verb(rub)] [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.breasts+], скользя [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.nipple+], прежде чем отстраниться.",

							"В последний раз глубоко втиснувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tentacle+] обратно из [npc2.her] [npc2.nipple+], кладя конец этому ебаному траху."));
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
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.tentacle+] из [npc2.her] [npc2.nipple+], жаждая еще большего внимания [npc.her].",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Трахание сосков щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.tentacle+] в свою [npc.nipple+] и получай еблю.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] медленно [npc.verb(guide)] его вверх к [npc.her] [npc.breasts+], издавая легкий [npc.moan], прежде чем осторожно вытолкнуть [npc.her] из груди и заставить [npc2.herHim] проникнуть в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] его вверх к [npc.her] [npc.nipple+], прежде чем медленно толкнуть [npc.her] [npc.breasts] вперед, издавая тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] с жадностью [npc.verb(guide)] её к [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] её к [npc.her] [npc.nipple+], прежде чем с жадностью толкнуть [npc.her] [npc.breasts] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] грубо дергает её к [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем яростно вытолкнуть [npc.her] грудь вперед и заставить [npc2.herHim] пронзить [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] её к [npc.her] [npc.nipple+], прежде чем властно толкнуть [npc.her] [npc.breasts] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(guide)] её к [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] его до [npc.her] [npc.nipple+], затем толкая [npc.her] [npc.breasts] вперед и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко толкая [npc2.her] [npc2.tentacle] вперёд, а [npc2.she] [npc2.verb(start)], чтобы трахать [npc.namePos] [npc.nipple+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle] вперёд, глубоко погружая её в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)], трахая [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.tentacle] вперёд, а [npc2.she] [npc2.verb(start)], с энтузиазмом трахая [npc.namePos] [npc.nipple+].",
	
								" Со [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle] вперёд, глубоко погружая её в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)], энергично трахая [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, превращая [npc2.a_moan+] в [npc2.she], что [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вжимает [npc2.her] [npc2.tentacle] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, превращая [npc2.a_moan+] в [npc2.she], что [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.tentacle] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle] вперед, глубоко погружая его в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, превращая [npc2.a_sob+] в [npc.name], что [npc.verb(force)] [npc2.her] [npc2.tentacle] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.tentacle+] из [npc.her] [npc.nipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.tentacle] глубоко в [npc.her] [npc.nipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.breast+(true)], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.nipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(drive)] [npc2.her] [npc2.tentacle+] как можно глубже в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.tentacle] от [npc.namePos] [npc.nipple(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.tentacle] от [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.breast+(true)], издавая мягкий [npc2.moan], пока [npc2.she] нежно [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" Нежный [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно проталкивает [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" От восторга [npc2.Moaning], [npc2.name] мягко [npc2.verb(slide)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.breast+(true)], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] неистово вбивает [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" От восторга [npc2.Moaning], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle+] настолько глубоко, насколько это возможно, в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.breast+(true)], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" От восторга [npc2.Moaning], [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно оттрахан сосок щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Пусть [npc2.namePos] [npc2.tentacle+] нежно трахает ваши [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подав [npc.her] грудь вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+].",

					"С мягким [npc.moan], [npc.name] плавно [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Медленно выпячивая [npc.her] грудь, из межгрудных [npc.namePos] [npc.lips+] вырывается мягкий [npc.moan], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tentacle+] глубоко входить в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахание сосков щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свои [npc.nipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резко подав [npc.her] грудь вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, из межгрудных [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tentacle+] глубоко входить в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо оттраханная щупальцами в соски";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свои [npc.nipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] [npc.her] груди вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать все глубже в [npc.her] [npc.nipple+].",

					"Сильно выпятив [npc.her] грудь, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахание сосков щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твои [npc.nipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Быстрым толчком [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Выпятив [npc.her] грудь, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахаться соском-щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свои [npc.nipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резко подав [npc.her] грудь вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, из межгрудных [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tentacle+] глубоко входить в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться соском-щупальцем";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свои [npc.nipple+] от [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], заставляя их набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.breast+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.nipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], заставляя их набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.breast+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], заставляя их набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.tentacle+] [npc2.verb(continue)] грубо вбивается туда-сюда в [npc.her] [npc.breast+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(continue)] неистово вонзается глубоко в [npc.her] [npc.nipple+]."));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], заставляя их набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.nipple+].",

						"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.tentacle+] [npc2.verb(remain)] глубоко входит внутрь [npc.her] [npc.breast+(true)].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(remain)] глубоко застревает в [npc.her] [npc.nipple+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить секс щупальцами-сосками";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tentacle] из твоих [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем резко выдернуть [npc2.her] [npc2.tentacle] из [npc.her] [npc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Плавно вытягивая [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.tentacle] из [npc.her] [npc.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль соска";
		}

		@Override
		public String getActionDescription() {
			return "Сжимай свой наполненный внутренними мышцами сосок вокруг [npc2.namePos] [npc2.tentacle].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], напрягая дополнительные внутренние мышцы внутри [npc.her] [npc.nipple(true)], обхватывая ими [npc2.namePos] [npc2.tentacle+].",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(focus)], контролируя дополнительные мышцы, выстилающие внутреннюю часть [npc.her] [npc.nipple(true)]. Сжимая и обхватывая их вокруг [npc2.namePos] [npc2.tentacle+], [npc.name] [npc.verb(cause)] [npc2.herHim], невольно вскрикивая от удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя на размеренный ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.nipple+] вокруг [npc2.namePos] [npc2.tentacle+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на управлении дополнительными мышцами глубоко внутри [npc.her] [npc.nipple(true)], прижимая их и массируя [npc2.namePos], пока [npc2.tentacle+] [npc.verb(squeal)] от удовольствия [npc.she].");
		}
	};
}
