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
 * @version 0.2.9
 * @author Innoxia
 */
public class PenisNipple {
	
	public static final SexAction TEASE_PENIS_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнить сосок [npc2.her]";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.cockHead] своего [npc.cock] по [npc2.her] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] медленно дразня [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.breasts+], прежде чем начать осторожно скользить [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Осторожно скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о том, что он сможет проникнуть в [npc2.herHim], когда [npc.she] [npc.verb(feel)] захочет."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.breasts+], [npc.name] с нетерпением [npc.verb(start)] скользит [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.breasts+], прежде чем начать с нетерпением скользить [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Жадно водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Втирая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.breasts+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.breasts+], после чего начинается грубое [npc2.verb(grind)] [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.nipples+].",

							"Грубо водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] водит [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.nipples+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] до [npc2.namePos] [npc2.breasts+], прежде чем начать водить [npc.cockTip] вверх и вниз по одной из [npc2.her] [npc2.nipples+].",

							"Водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.nipple+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] грудь к [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко вытолкнуть [npc2.her] [npc2.nipple+] навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.nipple+], и она нежно [npc2.verb(push)] [npc.her] грудь в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.nipple+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] грудь к [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.nipple+], и отчаянно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] грудь навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.nipple+], и яростно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируются, и [npc2.she] [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] из себя [npc2.moan], прежде чем выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.nipple+], и [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет [npc2.her] сосковую девственность.",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] вот-вот потеряет [npc2.her] сосковую девственность.",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере [npc2.her] сосковой девственности перед [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот произойдет, [npc2.speech(Нет! Стой! Мне еще никогда не трахали соски!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Моим соскам еще никогда не трахали!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении при мысли о том, что вот-вот произойдет, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять свою сосковую девственность!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.cock].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, когда [npc2.she] [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.cock], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE);
		}
	};
	
	public static final SexAction FORCE_PENIS_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласки сосков";
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.cockTip] [npc2.her] [npc2.cock] по твоим [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.breasts+]. Медленно толкая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], после чего начинает нежно водить [npc2.cockTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.nipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.breasts+]. Жадно толкая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)], дело за [npc.her] [npc.breasts+], прежде чем он с нетерпением начнет скользить [npc2.cockTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock+], [npc.name] с нетерпением [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем резко дернуть его/ее вверх до [npc.her] [npc.breasts+]. Грубо водя [npc2.cockTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и резко дернув его/ее вверх до [npc.her] [npc.breasts+], он/она/оно начинает грубо [npc.verb(force)] [npc2.cockTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(grind)] [npc2.cockTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.breasts+]. Водя [npc2.cockTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], прежде чем начать скользить [npc2.cockTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием скорого проникновения."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко водить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] мягко водит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], грубо толкая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой возить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] от восторга, пока [npc2.she] [npc2.verb(feel)], ощущая, как стимулируются [npc2.her] [npc2.cock], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)], с энтузиазмом толкая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.her] [npc.nipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом растирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользя [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] от восторга, пока [npc2.she] [npc2.verb(feel)], ощущая, как стимулируются [npc2.her] [npc2.cock], и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)], с жадностью скользя [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], потирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем начать скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)], чувствуя, как [npc2.her] [npc2.cock] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)], скользя [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)], заставляя [npc.name] отпустить [npc2.her] [npc2.cock]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE);
		}
	};
	
	
	public static final SexAction PENIS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Трахать [npc2.her] сосок";
		}

		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.cock+] в [npc2.namePos] [npc2.nipple+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуть вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], и медленным, уверенным движением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Азартно дразня [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно вонзиться вперед, вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Азартно дразня [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Лаская [npc.cockTip+] [npc.her], [npc.cock] нежно поглаживает [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко податься вперед, вонзая [npc.her] [npc.cock+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.breasts+], и коротким толчком [npc.she] [npc.verb(sink)] ее глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.cock+] входит в [npc2.herHim], а затем плавно подается вперед, выпячивая [npc2.her] грудь, чтобы вогнать его еще глубже в [npc2.her] [npc2.breast+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(push)] [npc2.her] грудь, погружая [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], а затем яростно подается вперед, выпячивая [npc2.her] грудь, чтобы вогнать его еще глубже в [npc2.her] [npc2.breast+].",
	
								" С [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] [npc2.her] грудь, грубо заставляя [npc.name] погружать [npc.her] [npc.cock+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], а затем с нетерпением подается вперед, выпячивая [npc2.her] грудь, чтобы вогнать его еще глубже в [npc2.her] [npc2.breast+].",
	
								" Благодаря [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] из [npc2.her] груди, отчаянно помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего выталкивает [npc2.her] грудь, чтобы погрузить ее еще глубже в [npc2.her] [npc2.breast+].",
	
								" Благодаря [npc2.a_moan+], [npc2.name] [npc2.verb(push)] из [npc2.her] груди, помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.cock+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" Благодаря [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежелательного проникновения, слезы текут по [npc2.her] [npc2.face], пока [npc.namePos] незваное [npc.cock] глубоко вжимается в [npc2.her] [npc2.nipple+]."));
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
							" В ответ [npc2.Name] с нетерпением [npc2.verb(push)] из [npc2.her] груди, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
		
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(thrust)] [npc2.her] chest out, eagerly begging for [npc.name] to continue fucking [npc2.her] [npc2.breasts] as [npc2.her] movements help to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.cock] [npc.namePos], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил [npc2.her] из [npc2.nipple+].",
		
							" [npc2.lips] вырывается из между [npc2.namePos] [npc2.A_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.nipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(push)], выпятив [npc2.her] грудь, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], чтобы [npc.namePos] [npc.cock+] погрузился глубоко в [npc2.her] [npc2.nipple+].",
	
							" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], и, выпятив [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] грудь, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] грудь в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts+].",
	
							" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.her] [npc2.breasts].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо прижимая [npc2.her] грудь к [npc.her] паху, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения вгоняют [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction PENIS_FUCKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежное ласкание сосков членом";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводи и выводи свой [npc.cock] в [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] покачивая [npc.her] [npc.hips] взад-вперед, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Медленно втискивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.nipple+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Скользя [npc.her] [npc.cock+] в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)] плавно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ниппелинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай входить и выходить своим [npc.cock+] в [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С энтузиазмом вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый ниппельный секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.nipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вбивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] грубо вколачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Яростно вонзая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] грубо толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С силой вбивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы грубо двигать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ниппелинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] покачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахать сосок";
		}

		@Override
		public String getActionDescription() {
			return "Энергично вгонять свой [npc.cock+] в [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С энтузиазмом вонзая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] с азартом [npc.verb(start)] раскачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.breasts].",

					"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos], [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы энергично качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться траху соском";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.cock] из [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] нежно удерживает его, прежде чем мягко вдавить обратно в [npc2.her] [npc2.nipple+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно затолкнуть обратно в [npc2.her] [npc2.nipple+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] плавно [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытянуть [npc.her] [npc.cock] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его грубым захватом, прежде чем агрессивно затолкнуть обратно в [npc2.her] [npc2.nipple+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо затолкнуть обратно в [npc2.her] [npc2.nipple+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.cock] из [npc2.namePos] [npc2.nipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепко сжимает его, прежде чем жадно затолкнуть обратно в [npc2.her] [npc2.nipple+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно затолкнуть обратно внутрь [npc2.her] [npc2.nipple+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить ниппель-трах";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из [npc2.namePos] [npc2.nipple+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо вырывая [npc.her] [npc.cock+] из [npc2.namePos] [npc2.nipple+], [npc.name] властно [npc.verb(slide)] водит [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.breasts+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] вытаскивает [npc.her] [npc.cock+] из [npc2.her] [npc2.nipple+], кладя конец грубому траху."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув из [npc2.namePos] [npc2.nipple+], [npc.name] в последний раз [npc.verb(rub)] [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.breasts+], [npc.her] [npc.cock] отстраняется.",

							"В последний раз глубоко толкнув [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.nipple+], ставя точку в этом трахе."));
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
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.nipple+], жаждая еще большего внимания [npc.her].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction USING_PENIS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Заняться ниппель-трахом";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.cock+] в свою [npc.nipple+] и трахайся.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(guide)] его вверх к [npc.her] [npc.breasts+], издавая легкий [npc.moan], прежде чем осторожно вытолкнуть [npc.her] из груди и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.nipple+], затем медленно толкая [npc.her] [npc.breasts] вперед и издавая тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] жадно [npc.verb(guide)] его до [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.nipple+], затем с нетерпением толкая [npc.her] [npc.breasts] вперед и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] грубо дергает его вверх к [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем яростно выпятить [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.nipple+], затем властно толкая [npc.her] [npc.breasts] вперед и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] его до [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] её до [npc.her] [npc.nipple+], прежде чем толкнуть [npc.her] [npc.breasts] вперед, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], осторожно толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахать [npc.namePos] [npc.nipple+].",
	
								" С тихим [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая её в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] трахает [npc.namePos] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая её в [npc.namePos] [npc.nipple+], пока [npc2.she] энергично [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вгоняет [npc2.her] [npc2.cock] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.cock+] из [npc.her] [npc.nipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.nipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.breast+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.nipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.nipple(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.nipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.breast+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" Нежный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно вгоняет [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.breast+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] неистово вколачивает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.breast+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] deep into [npc.namePos] [npc.nipple+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RIDING_PENIS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Мягкий ниппель-секс";
		}

		@Override
		public String getActionDescription() {
			return "Мягкий ниппель-секс с [npc2.namePos] [npc2.cock+], используя твой [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подав [npc.her] грудь вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+].",

					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Медленно выпячивая [npc.her] грудь, из между [npc.namePos] [npc.lips+] вырывается мягкий [npc.moan], пока [npc.her] движения заставляют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ниппель-трах";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свои [npc.nipple+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С нетерпеливым толчком [npc.her] груди вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, из между [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый ниппель-трах";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свои [npc.nipple+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] [npc.her] вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Сильно выпятив [npc.her] грудь, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_PENIS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ниппель-трах";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.nipple+] об [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], заставляя [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Выпятив [npc.her] грудь, [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно сосать сосок";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свои [npc.nipple+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С нетерпеливым толчком [npc.her] груди вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, из между [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться сосанию соска";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.nipple+] от [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)] наполняют [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.nipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.breast+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)] наполняют [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.nipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.breast+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в мучении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] жадно вколачивается глубоко в [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)] наполняют [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.cock] из [npc.her] [npc.nipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вбивается туда-сюда в [npc.her] [npc.breast+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в мучении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] неистово вколачивается глубоко в [npc.her] [npc.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить ниппель-трах";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.cock] из твоих [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко вырывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] с угрожающим рыком, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем выскользнуть [npc2.her] [npc2.cock] из [npc.her] [npc.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль соска";
		}

		@Override
		public String getActionDescription() {
			return "Сжимайте свой сосок с внутренними мышцами вокруг [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Выпуская [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] [npc.nipple(true)] вокруг [npc2.namePos] [npc2.cock+].",

					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(focus)] на управлении дополнительными мышцами, выстилающими внутреннюю часть [npc.her] [npc.nipple(true)]. Сжимая и вдавливая их вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(cause)] [npc2.herHim], заставляя издать непроизвольный крик удовольствия."
						:""),

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], входя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)], сжимая дополнительные мышцы внутри [npc.her] [npc.nipple+] вокруг [npc2.namePos] [npc2.cock+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.nipple(true)], сжимая их и массируя [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
