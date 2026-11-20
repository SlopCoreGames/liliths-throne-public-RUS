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
 * @since 0.4.7.11
 * @version 0.4.7.11
 * @author Innoxia
 */
public class ClitNipple {
	
	public static final SexAction TEASE_CLIT_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласки клитора и сосков";
		}

		@Override
		public String getActionDescription() {
			return "Дразните [npc2.name], скользя [npc.clit+] по [npc2.her] [npc2.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)
					&& Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.clit+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] медленно ласкает [npc.clitTip+] вверх и вниз по одной из [npc2.her] [npc2.nipples+], готовая в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.clit+] к [npc2.namePos] [npc2.breasts+], после чего начинает нежно водить [npc.clitTip] вверх и вниз по одной из [npc2.her] [npc2.nipples+].",

							"Мягко водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о том, что может проникнуть в [npc2.herHim], когда ей [npc.she] [npc.verb(feel)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.clit+] к [npc2.namePos] [npc2.breasts+], [npc.name] с нетерпением [npc.verb(start)] водит [npc.clitTip+] вверх и вниз по одной из [npc2.her] [npc2.nipples+], готовая в любой момент проникнуть в [npc2.herHim].",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.clit+] к [npc2.namePos] [npc2.breasts+], после чего начинает с нетерпением водить [npc.clitTip] вверх и вниз по одной из [npc2.her] [npc2.nipples+].",

							"С жадностью водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вжимая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(pull)] немного назад, прежде чем начать водить [npc.clitTip+] вверх и вниз по одной из [npc2.her] [npc2.nipples+], готовая начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.clit+] вверх к [npc2.namePos] [npc2.breasts+], прежде чем начать грубо [npc2.verb(grind)] [npc.clitTip] вверх и вниз по одной из [npc2.her] [npc2.nipples+].",

							"Грубо водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.clit+] к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] водит [npc.clitTip+] вверх и вниз по одной из [npc2.her] [npc2.nipples+], готовая проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.clit+] до [npc2.namePos] [npc2.breasts+], прежде чем начать водить [npc.clitTip] вверх и вниз по одной из [npc2.her] [npc2.nipples+].",

							"Водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.nipple+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко вытолкнуть [npc2.her] [npc2.nipple+] навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.nipple+], и нежно [npc2.verb(push)] [npc.her] грудь в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.nipple+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.nipple+], и отчаянно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируют, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] грудь навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.nipple+], и яростно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.nipple+] стимулируют, и [npc2.she] [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] из себя [npc2.moan], прежде чем выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.nipple+], и [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleVirgin()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] внезапно вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот потеряет девственность своих [npc2.her] сосков.",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(realise)], что [npc2.she] вот-вот потеряет девственность своих [npc2.her] сосков.",
										" [npc2.Name] [npc2.sobVerb] в смятении при мысли о потере девственности своих [npc2.her] сосков перед [npc.namePos] [npc.clit+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] внезапно вырывается из между [npc2.namePos] [npc2.lips+] при мысли о том, что вот-вот случится, [npc2.speech(Нет! Стой! Моим соскам еще никогда не трахали!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не надо! Моим соскам еще никогда не трахали!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении при мысли о том, что вот-вот случится, прежде чем отчаянно взмолиться: [npc2.speech(Нет! Стой! Я не хочу терять девственность своих сосков!)]"));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] внезапно вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.clit].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] внезапно вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.nipple(true)] от [npc.namePos] [npc.clit], [npc2.speech(Нет! Не надо! Пожалуйста, убирайся от меня!)]",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем взмолиться: [npc2.speech(Пожалуйста! Не делай этого! Оставь меня в покое!)]",
										" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)], [npc2.speech(Нет! Стой! Уйди оттуда!)]"));
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE);
		}
	};
	
	public static final SexAction FORCE_CLIT_OVER_NIPPLE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласкать клитор (сосок)";
		}

		@Override
		public String getActionDescription() {
			return "Ласкать [npc2.name], скользя [npc2.clitTip] [npc2.her] [npc2.clit] по твоей [npc.nipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить её к [npc.her] [npc.breasts+]. Медленно толкая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] её к [npc.her] [npc.breasts+], прежде чем начать нежно водить [npc2.clitTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] нежно [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.nipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить её к [npc.her] [npc.breasts+]. Жадно толкая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)], дело доходит до [npc.her] [npc.breasts+], прежде чем он начинает жадно водить [npc2.clitTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] жадно [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем резко дернуть ее вверх к [npc.her] [npc.breasts+]. Грубо водя [npc2.clitTip+] вверх и вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении того, что проникновение может случиться в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и резко дергает ее к [npc.her] [npc.breasts+], после чего начинает грубо [npc.verb(force)] [npc2.clitTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] грубо [npc.verb(grind)] [npc2.clitTip+] по [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить его к [npc.her] [npc.breasts+]. Подрагивающий [npc2.clitTip+] ходит вверх-вниз по [npc.her] [npc.nipple+], [npc.she] [npc.verb(tease)] [npc2.name] с обещанием проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] его к [npc.her] [npc.breasts+], прежде чем начать скользить [npc2.clitTip] вверх и вниз по [npc.her] [npc.nipple+].",

							"Сжимая [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(slide)] [npc2.clitTip+] поверх [npc.her] [npc.nipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] с обещанием проникновения."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем осторожно скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем поощрении, [npc2.she] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой начать втирать [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируются, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] с энтузиазмом втирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.her] [npc.nipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом растирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользя [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], потирая [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем начать скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] чувствует, как [npc2.her] [npc2.clit] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)] пытается оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.nipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)] [npc.name], чтобы отпустить [npc2.her] [npc2.clit]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE);
		}
	};
	
	
	public static final SexAction CLIT_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		
		@Override
		public String getActionTitle() {
			return "Клитор-трах соском";
		}

		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.clit+] в [npc2.namePos] [npc2.nipple+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуть вперед, погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], и медленным, уверенным давлением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] с жадностью [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно врезаться вперед, вбивая [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо дразня [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.breasts+], и решительным толчком [npc.she] с жадностью [npc.verb(sink)] его глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.clitTip+] [npc.her], [npc.clit] ласкает [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко податься вперед, вонзая [npc.her] [npc.clit+] в [npc2.her] [npc2.nipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.breasts+], и коротким толчком [npc.she] [npc.verb(sink)] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] тихо [npc2.verb(let)] мягкий [npc2.moan], когда [npc.clit+] входит в [npc2.herHim], после чего она плавно подает [npc2.her] грудь вперед, чтобы вогнать его еще глубже в [npc2.her] [npc2.breast+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь, погружая [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] громко [npc2.verb(let)] [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она яростно выпячивает [npc2.her] грудь, чтобы вогнать его еще глубже в [npc2.her] [npc2.breast+].",
	
								" С [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] [npc2.her] грудь, грубо заставляя [npc.name] погружать [npc.her] [npc.clit+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] тихо [npc2.verb(let)] [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], после чего она с предвкушением выпячивает [npc2.her] грудь, чтобы погрузить его еще глубже в [npc2.her] [npc2.breast+].",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] из [npc2.her] груди, отчаянно помогая погрузить [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.clit+] входит в [npc2.herHim], прежде чем вытолкнуть [npc2.her] грудь, чтобы погрузить ее еще глубже в [npc2.her] [npc2.breast+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] из [npc2.her] груди, помогая погрузить [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.clit+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, слезы текут по [npc2.her] [npc2.face], пока [npc.namePos] незваное [npc.clit] глубоко вжимается в [npc2.her] [npc2.nipple+]."));
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
							" В ответ [npc2.Name] с нетерпением [npc2.verb(push)] из [npc2.her] груди, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.clit+] глубоко в [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(thrust)], выпятив [npc2.her] грудь, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.clit] [npc.namePos], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил из [npc2.her] [npc2.nipple+].",
		
							" [npc2.lips] вырывается из между [npc2.namePos] [npc2.A_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил из [npc2.her] [npc2.nipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, бегущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил из [npc2.her] [npc2.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(push)] из [npc2.her] груди, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.clit+] глубоко в [npc2.her] [npc2.nipple+].",
	
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и, выпятив [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] мог и дальше трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] не прекращать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] грудь назад, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], прося [npc.name] продолжать трахать [npc2.her] [npc2.breasts+].",
	
							" Тихий [npc2.moan] вырывается из меж [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] не прекращать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] грудь вперед в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.her] [npc2.breasts].",
	
							" [npc2.A_moan+] вырывается из меж [npc2.namePos] [npc2.lips+], и, грубо прижимая [npc2.her] грудь к [npc.her] паху, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.breasts].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] грудь в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.breasts], пока [npc2.her] движения заставляют [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный клиторно-сосковый секс";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводите свой [npc.clit] в [npc2.namePos] и выводите из [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] раскачивая [npc.her] [npc.hips] взад-вперед, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Медленно толкая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.nipple+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Скользя [npc.her] [npc.clit+] в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)] мягко качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сосковый клиторно-секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжайте вводить свой [npc.clit+] в [npc2.namePos] и выводить из [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С энтузиазмом вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вколачивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый клиторно-сосковый секс";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.clit+] в [npc2.namePos] [npc2.nipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вбивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] грубо раскачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Яростно вонзая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] грубо толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"С силой вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] извергая [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо вбивает [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сосковый клиторно-секс";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] раскачивая [npc.her] [npc.hips] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Втискивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.breasts].",

					"Вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] извергая [npc.a_moan+], пока [npc.she] [npc.verb(start)] качает [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.breasts]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадное проникновение соском в клитор";
		}

		@Override
		public String getActionDescription() {
			return "Жадно двигай своим [npc.clit+] туда-сюда в [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] с энтузиазмом [npc.verb(start)] [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.breasts].",

					"С энтузиазмом втискивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно качать [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться соском в клитор";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.clit] из [npc2.namePos] [npc2.nipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] нежно удерживает его, прежде чем мягко вернуть обратно в [npc2.her] [npc2.nipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем осторожно затолкнуть обратно в [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] плавно [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытянуть [npc.her] [npc.clit] из [npc2.namePos] [npc2.nipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] грубым захватом, прежде чем агрессивно затолкнуть её обратно в [npc2.her] [npc2.nipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем грубо затолкнуть обратно в [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.clit+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit] из [npc2.namePos] [npc2.nipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепкую хватку, прежде чем жадно затолкнуть её обратно в [npc2.her] [npc2.nipple+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем жадно затолкнуть обратно внутрь [npc2.her] [npc2.nipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.nipple+] вниз на [npc.her] [npc.clit+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.clit] из [npc2.namePos] [npc2.nipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] [npc2.verb(move)] вперед, удерживая её глубоко зажатой в [npc2.her] [npc2.nipple+].",

						"При [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(move)] вперед, удерживая её вошедшей внутрь [npc2.her] [npc2.nipple+].",

						"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.nipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, удерживая [npc2.her] [npc2.nipple+] прижатыми к [npc.her] [npc.clit+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить клиторный секс сосков";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.clit+] из [npc2.namePos] [npc2.nipple+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.clit+] из [npc2.namePos] [npc2.nipple+], [npc.name] властно [npc.verb(slide)] [npc.clitTip+] вверх и вниз по одному из [npc2.her] [npc2.breasts+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.clit+] обратно из [npc2.her] [npc2.nipple+], завершая этот грубый трах."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув [npc.her] [npc.clit] из [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(rub)] [npc.clitTip] вверх и вниз по одному из [npc2.her] [npc2.breasts+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.clit+] обратно из [npc2.her] [npc2.nipple+], завершая трах."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.nipple(true)], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] слабо барахтаясь против [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.nipple+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.clit+] из [npc2.her] [npc2.nipple+], жаждая еще большего внимания [npc.her].",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}

		@Override
		public String getActionTitle() {
			return "Клиторный секс соском";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.clit+] в свою [npc.nipple+] и трахайся.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] медленно [npc.verb(guide)] ее к [npc.her] [npc.breasts+], издавая легкий [npc.moan], прежде чем осторожно вытолкнуть [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] ее к [npc.her] [npc.nipple+], прежде чем медленно толкнуть [npc.her] [npc.breasts] вперед и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] с жадностью [npc.verb(guide)] её к [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её к [npc.her] [npc.nipple+], а затем с жадностью толкнув [npc.her] [npc.breasts] вперед и издав [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] грубо дергает её к [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем яростно выпятить [npc.her] грудь вперед и заставить [npc2.herHim] пронзить [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её к [npc.her] [npc.nipple+], а затем властно толкнув [npc.her] [npc.breasts] вперед и издав [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(guide)] её к [npc.her] [npc.breasts+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.nipple+], а затем толкнув [npc.her] [npc.breasts] вперед и издав [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], осторожно толкая [npc2.her] [npc2.clit] вперед, а [npc2.she] [npc2.verb(start)], чтобы трахать [npc.namePos] [npc.nipple+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая её в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.clit] вперед, а [npc2.she] [npc2.verb(start)] с энтузиазмом трахая [npc.namePos] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая её в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вжимает [npc2.her] [npc2.clit] вперед и [npc2.verb(start)], чтобы беспощадно трахать [npc.her] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] беспощадно трахает [npc.namePos] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.clit] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая его в [npc.namePos] [npc.nipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.clit] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытянуть [npc2.her] [npc2.clit+] из [npc.her] [npc.nipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.clit] глубоко в [npc.her] [npc.nipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.breast+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.nipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.clit+] как можно глубже в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.clit] от [npc.namePos] [npc.nipple(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.clit+] глубоко в [npc.her] [npc.nipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] оттащить [npc2.her] [npc2.clit] от [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.breast+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" Легкий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно вводит [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.breast+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] яростно [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit+] так глубоко, как только возможно, в [npc.namePos] [npc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.breast+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.nipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.nipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно трахнуть клитор соском";
		}

		@Override
		public String getActionDescription() {
			return "Пусть [npc2.namePos] [npc2.clit+] нежно трахает твой [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягким толчком груди [npc.her] вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.clit+] проникать все глубже в [npc.her] [npc.nipple+].",

					"Медленно выпячивая [npc.her] грудь, мягкий [npc.moan] выплывает из меж[npc.namePos] [npc.lips+], пока [npc.her] движения вдавливают [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клиторный секс соском";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать твой [npc.nipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким вперед подавшись [npc.her] грудью, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, [npc.a_moan+] вырывается из меж[npc.namePos] [npc.lips+], пока [npc.her] движения вдавливают [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо трахать клитор соском";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахать твой [npc.nipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростным рывком [npc.her] груди вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Сильно выпятив [npc.her] грудь, [npc.a_moan+] вырывается из меж[npc.namePos] [npc.lips+], пока [npc.her] движения грубо вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_CLIT_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Клиторный секс соском";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твою [npc.nipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] груди вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Выпятив [npc.her] грудь, [npc.a_moan+] вырывается из меж[npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно трахаемый клитором в сосок";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахать твой [npc.nipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким вперед подавшись [npc.her] грудью, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.clit+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] грудь, [npc.a_moan+] вырывается из меж[npc.namePos] [npc.lips+], пока [npc.her] движения вдавливают [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляясь траху клитором в сосок";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть твою [npc.nipple+] от [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes] скапливаются слезы, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.clit] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], тщетно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.breast+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.nipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes] скапливаются слезы, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.clit] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], тщетно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] неистово вбивается туда-сюда в [npc.her] [npc.breast+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] жадно врывается глубоко в [npc.her] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes] скапливаются слезы, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.clit] из [npc.her] [npc.nipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.nipple(true)] от нежелательного проникновения [npc2.namePos], тщетно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] грубо вколачивается туда-сюда в [npc.her] [npc.breast+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] яростно вбивается глубоко в [npc.her] [npc.nipple+]."));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] разрывает [npc.verb(start)], из-за чего в [npc.her] [npc.eyes] скапливаются слезы, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.clit] из [npc.her] [npc.nipple+].",

						"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.nipple(true)] от нежеланного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(remain)] глубоко внутри [npc.her] [npc.breast+].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.breasts], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(remain)] глубоко упирается в [npc.her] [npc.nipple+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Прекратить клиторно-сосковый секс";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.clit] из твоих [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко вырывая [npc2.namePos] [npc2.clit] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] с угрожающим рычанием, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.clit] из [npc.her] [npc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.clit] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем выскользнуть [npc2.her] [npc2.clit] из [npc.her] [npc.nipple+]."));
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
								" [npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] [npc2.herHim] из ебучего [npc.her] [npc.nipple+].",
	
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.nipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль сосков";
		}

		@Override
		public String getActionDescription() {
			return "Сжимайте сосок внутренними мышцами вокруг [npc2.namePos] [npc2.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Выпуская [npc.a_moan+], [npc.name] [npc.verb(concentrate)] при сжатии дополнительных внутренних мышц внутри [npc.her] [npc.nipple(true)] вокруг [npc2.namePos] [npc2.clit+].",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(focus)] на управлении дополнительными мышцами, выстилающими внутреннюю часть [npc.her] [npc.nipple(true)]. Сжимая и вдавливая их вокруг [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(cause)] [npc2.herHim], заставляя издать непроизвольный крик удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри [npc.her] [npc.nipple+] вокруг [npc2.namePos] [npc2.clit+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на управлении дополнительными мышцами глубоко внутри [npc.her] [npc.nipple(true)], сжимая их и массируя [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
