package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
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
public class ClitNippleCrotch {

	private static String getNippleTitle(GameCharacter character) {
		if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
			return "сосок на вымени";
		} else {
			return "сосок в промежности";
		}
	}
	
	public static final SexAction TEASE_CLIT_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)))+" ласка клитора";
		}

		@Override
		public String getActionDescription() {
			return "Дразните [npc2.name], скользя [npc.clit+] по [npc2.her] [npc2.crotchNipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)
					&& Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.clit+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] медленно ласкает [npc.clitTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.clit+] к [npc2.namePos] [npc2.crotchBoobs+], после чего начинает нежно скользить [npc.clitTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Мягко скользя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] тихий [npc.moan] при мысли о том, что можно будет проникнуть в [npc2.herHim], когда [npc.she] [npc.verb(feel)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.clit+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] с нетерпением [npc.verb(start)] скользит [npc.clitTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.clit+] к [npc2.namePos] [npc2.crotchBoobs+], после чего начинает с нетерпением скользить [npc.clitTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"С жадностью водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вжимая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.clitTip+] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+], готовая начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.clit+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать грубо [npc2.verb(grind)] [npc.clitTip] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+].",

							"Грубо водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.clit+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] водит [npc.clitTip+] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+], готовая проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.clit+] до [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать водить [npc.clitTip] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+].",

							"Водя [npc.clitTip+] [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.crotchNipple+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] живота к [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко вытолкнуть [npc2.her] [npc2.crotchNipple+] навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.crotchNipple+], и в ответ нежно [npc2.verb(push)] [npc.her] живота."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.crotchNipple+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] живота к [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], а затем с жадностью выпячивая [npc2.her] живот навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.crotchNipple+], и отчаянно [npc2.verb(thrust)] [npc.her] живот в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], а [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] живот навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] живот навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.crotchNipple+], и яростно [npc2.verb(thrust)] [npc.her] живот в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], а [npc2.she] [npc2.verb(push)] [npc2.her] живот навстречу [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.verb(let)] выпячивает [npc2.moan], прежде чем прижаться [npc2.her] животом к [npc.namePos] [npc.clit].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.clit], стимулируя [npc2.her] [npc2.crotchNipple+], и [npc2.verb(thrust)] [npc.her] живот в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleCrotchVirgin()) {
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
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.crotchNipple(true)] от [npc.namePos] [npc.clit].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.crotchNipple(true)] от [npc.namePos] [npc.clit], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static final SexAction FORCE_CLIT_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласкать клитор ("+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+")";
		}

		@Override
		public String getActionDescription() {
			return "Ласкать [npc2.name], скользя [npc2.clitTip] [npc2.her] [npc2.clit] по твоей [npc.crotchNipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().isBreastCrotchFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}

		@Override
		public String getDescription() {
				
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить её к [npc.her] [npc.crotchBoobs+]. Медленно толкая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] её к [npc.her] [npc.crotchBoobs+], прежде чем начать нежно водить [npc2.clitTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Сжимая [npc2.namePos] [npc2.clit+], [npc.name] нежно [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.crotchNipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. Жадно двигая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении того, что проникновение может случиться в любой момент.",

							"Сжимая [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], после чего начинает жадно скользить [npc2.clitTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Сжимая [npc2.namePos] [npc2.clit+], [npc.name] с жадностью [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем резко дернуть его к [npc.her] [npc.crotchBoobs+]. Грубо толкая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении того, что проникновение может случиться в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и резко дернув его вверх к [npc.her] [npc.crotchBoobs+], прежде чем начать грубо [npc.verb(force)] [npc2.clitTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] грубо [npc.verb(grind)] [npc2.clitTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+], прежде чем направить его вверх к [npc.her] [npc.crotchBoobs+]. Проталкивая [npc2.clitTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.clit+] и [npc.verb(guide)] его вверх к [npc.her] [npc.crotchBoobs+], прежде чем начать скользить [npc2.clitTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.clit+], [npc.name] грубо [npc.verb(slide)] [npc2.clitTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем нежно скользить [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой втирать [npc2.her] [npc2.clit] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируются, и, стремясь напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом растирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.clit] взад-вперед по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] растирает [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.clit] взад-вперед по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.clit] стимулируется, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.clit] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] внезапно вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.clit+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)] [npc.name], чтобы отпустить [npc2.her] [npc2.clit]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	
	public static final SexAction CLIT_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().isClitorisPseudoPenis();
		}
		
		@Override
		public String getActionTitle() {
			return "Клиторный секс "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.clit+] в [npc2.namePos] [npc2.crotchNipple+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.crotchBoobs+], и медленным, уверенным давлением [npc.she] нежно [npc.verb(sink)] её глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] её глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], а затем яростно подаваясь вперед, вбивая [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.crotchBoobs+], и резким толчком [npc.she] грубо [npc.verb(slam)] её глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] её глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем толкнуться вперед, погружая [npc.her] [npc.clit+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.clitTip+] [npc.her] [npc.clit] по [npc2.namePos] [npc2.crotchBoobs+], и коротким толчком [npc.she] слегка [npc.verb(sink)] её глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], когда [npc.clit+] входит в [npc2.herHim], а затем мягко выпячивая [npc2.her] живот, чтобы погрузить её еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(push)] [npc2.her] живот, погружая [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, когда [npc2.a_moan+] выходит, пока [npc.clit+] входит в [npc2.herHim], а затем яростно выталкивает [npc2.her] живот, чтобы вогнать его еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С помощью [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] наружу [npc2.her] живот, грубо заставляя [npc.name] погружать [npc.her] [npc.clit+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, когда [npc2.a_moan+] выходит, а [npc.clit+] входит в [npc2.herHim], после чего он жадно прижимает [npc2.her] к животу, чтобы вогнать его еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С помощью [npc2.a_moan+], [npc2.name] жадно [npc2.verb(push)] наружу [npc2.her] живот, отчаянно помогая погрузить [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, когда [npc2.a_moan+] выходит, пока [npc.clit+] входит в [npc2.herHim], после чего выталкивая [npc2.her] живот, чтобы погрузить его еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С помощью [npc2.a_moan+], [npc2.name] [npc2.verb(push)] наружу [npc2.her] живот, помогая погрузить [npc.namePos] [npc.clit+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.clit+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С помощью [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, пока слезы текут по [npc2.her] [npc2.face], а [npc.namePos] незваный [npc.clit] вжимается глубоко в [npc2.her] [npc2.crotchNipple+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] живот в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], позволяя [npc.namePos] [npc.clit+] глубоко погрузиться в [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] живот, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] живот, умоляя, чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.clit], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил из [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(push)] [npc2.her] живот, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], позволяя [npc.namePos] [npc.clit+] глубоко погрузиться в [npc2.her] [npc2.crotchNipple+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, выпячивая [npc2.her] живот, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] живот, умоляя, чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ медленно [npc2.verb(push)] [npc2.her] живот, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs+].",
	
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] живот, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] живот, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ яростно [npc2.verb(thrust)] [npc2.her] живот, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо вжимая [npc2.her] живот в [npc.her] пах, [npc2.she] [npc2.verb(order)] [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] живот в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения заставляют [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" clit-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Нежно вводите свой [npc.clit] в [npc2.namePos] [npc2.crotchNipple+] и выводите его.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] покачивает [npc.her] [npc.hips] взад-вперед, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Медленно толкая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Скользя [npc.her] [npc.clit+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно покачивать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)))+" clit-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать входить своим [npc.clit+] в [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] с энтузиазмом [npc.verb(start)] [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"С энтузиазмом толкая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"[npc.her] [npc.clit+] глубоко входит в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением качает [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" clit-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.clit+] в [npc2.namePos] [npc2.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] грубо швыряет [npc.her] [npc.hips] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Яростно вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] грубо швыряет [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Сильно вбивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо швыряет [npc.her] [npc.hips] туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)))+" clit-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] раскачивает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Вгоняя [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] толчками подает [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"[npc.her] мощно вбивается [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] разгоняет [npc.her] [npc.hips] взад-вперед, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жаждущий "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" clit-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Жадно двигай своим [npc.clit+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивает [npc.her] [npc.crotchBoobs] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.crotchBoobs].",

					"С энтузиазмом втискивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.crotchBoobs] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вколачивая [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы с жадностью качать [npc.her] [npc.crotchBoobs] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" clit-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.clit] из [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.crotchNipple], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] нежное удержание, прежде чем мягко загнать его обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] это, прежде чем мягко загнать его обратно внутрь [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] оттянуть [npc.her] [npc.clit] из [npc2.namePos] [npc2.crotchNipple], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] грубо сжимает её, прежде чем агрессивно затолкнуть обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)] оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем грубо затолкнуть обратно внутрь [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.clit+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.crotchNipple], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепко сжимает его, прежде чем жадно затолкнуть обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно затолкнуть обратно внутрь [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.clit+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.clit] из [npc2.namePos] [npc2.crotchNipple], но усилия [npc.her] оказываются напрасными, так как [npc2.name] [npc2.verb(move)] вперед, удерживая его глубоко засунутым в [npc2.her] [npc2.crotchNipple+].",

						"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.clit] от [npc2.name], но [npc2.she] быстро [npc2.verb(move)] вперед, удерживая его вбитым внутрь [npc2.her] [npc2.crotchNipple+].",

						"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.clit] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, удерживая [npc2.her] [npc2.crotchNipple+] прижатыми к [npc.her] [npc.clit+]."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction CLIT_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" clit-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.clit+] из [npc2.namePos] [npc2.crotchNipple+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.clit+] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] властно [npc.verb(slide)] [npc.clitTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.clit+] обратно из [npc2.her] [npc2.crotchNipple+], кладя конец грубому траху."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.clit] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(rub)] [npc.clitTip] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.clit+] обратно из [npc2.her] [npc2.crotchNipple+], кладя конец траху."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.crotchNipple], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] слабо борясь против [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.crotchNipple+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.clit+] из [npc2.her] [npc2.crotchNipple+], жаждая еще большего внимания [npc.her].",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterTargetedForSexAction(this).isClitorisPseudoPenis();
		}
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.clit+] в свою [npc.crotchNipple+] и трахайся.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] медленно [npc.verb(guide)] ее к [npc.her] [npc.crotchBoobs+], издавая легкий [npc.moan], прежде чем мягко вытолкнуть [npc.her] живот и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] ее к [npc.her] [npc.crotchNipple+], прежде чем медленно толкнуть [npc.her] [npc.crotchBoobs] вперед и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] жадно [npc.verb(guide)] её до [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] живот и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.crotchNipple+], прежде чем с нетерпением толкнуть [npc.her] [npc.crotchBoobs] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] грубо дергает её вверх к [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем яростно выпятить [npc.her] живот и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] властно [npc.verb(line)] её до [npc.her] [npc.crotchNipple+], прежде чем доминантно толкнуть [npc.her] [npc.crotchBoobs] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(guide)] её до [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] живот и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.clit], [npc.name] [npc.verb(line)] её до [npc.her] [npc.crotchNipple+], а затем толкнув [npc.her] [npc.crotchBoobs] вперед и издав [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.clit+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], осторожно толкая [npc2.her] [npc2.clit] вперед, а [npc2.she] [npc2.verb(start)] с энтузиазмом трахая [npc.namePos] [npc.crotchNipple+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая её в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] увлеченно трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.clit] вперед, а [npc2.she] [npc2.verb(start)] с энтузиазмом трахая [npc.namePos] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая её в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.clit] вперед и [npc2.verb(start)], чтобы беспощадно трахать [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] беспощадно трахает [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.clit] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.clit] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.clit] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.clit+] из [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.clit] глубоко в [npc.her] [npc.crotchNipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchNipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.clit+] как можно глубже в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.clit] от [npc.namePos] [npc.crotchNipple], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.clit] от [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" Нежный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно вгоняет [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается из рта [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] с силой вбивает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.clit+] как можно глубже в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.clit+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.clit+] deep into [npc.namePos] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterPerformingAction())+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Пусть [npc2.namePos] [npc2.clit+] нежно оттрахает твой [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подав [npc.her] живот вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С мягким [npc.moan], [npc.name] плавно [npc.verb(push)] [npc.her] живот вперед, заставляя [npc2.namePos] [npc2.clit+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Медленно выпячивая [npc.her] живот, тихий [npc.moan] доносится из-за [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свои [npc.crotchNipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично подав [npc.her] живот вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] живот вперед, заставляя [npc2.namePos] [npc2.clit+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] живот, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterPerformingAction())+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свои [npc.crotchNipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] живота, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С помощью [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] живот [npc.her] вперед, заставляя [npc2.namePos] [npc2.clit+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Сильно выпячивая живот [npc.her], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_CLIT_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твой [npc.crotchNipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Быстрым толчком живота [npc.her], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(push)] живот [npc.her] вперед, заставляя [npc2.namePos] [npc2.clit+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Выпячивая живот [npc.her], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_CLIT_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью "+getNippleTitle(Main.sex.getCharacterPerformingAction())+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свои [npc.crotchNipple+] на [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично подав [npc.her] живот вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] живот вперед, заставляя [npc2.namePos] [npc2.clit+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] живот, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.clit+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterPerformingAction())+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Попробуй оттянуть свой [npc.crotchNipple+] от [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.crotchNipple] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] медленно скользит туда-сюда в [npc.her] [npc.crotchBoob+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] нежно и глубоко [npc2.verb(continue)] в [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.crotchNipple] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.clit+] неистово [npc2.verb(continue)] входит и выходит из [npc.her] [npc.crotchBoob+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.crotchNipple] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(continue)] грубо вбивается туда-сюда в [npc.her] [npc.crotchBoob+].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.clit] из [npc.her] [npc.crotchNipple+].",

						"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.crotchNipple] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.clit+] [npc2.verb(remain)] глубоко входит внутрь [npc.her] [npc.crotchBoob+].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.clit+] [npc2.verb(remain)] глубоко упирается в [npc.her] [npc.crotchNipple+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterPerformingAction())+" clit-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.clit] из твоих [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.clit] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] с угрожающим рычанием, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы перестать трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем резко выдернуть [npc2.her] [npc2.clit] из [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc2.namePos] [npc2.clit] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы перестать трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно выскользнуть [npc2.her] [npc2.clit] из [npc.her] [npc.crotchNipple+]."));
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
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim] от траханья [npc.her] [npc.crotchNipple+].",
	
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] [npc.crotchNipple+]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_CROTCH_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" control";
		}

		@Override
		public String getActionDescription() {
			return "Стисните свои внутренние мышцы [npc.crotchNipple] вокруг [npc2.namePos] [npc2.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleCrotchOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] [npc.crotchNipple] вокруг [npc2.namePos] [npc2.clit+].",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] вовсю [npc.a_moan+] как [npc.she] [npc.verb(focus)] в управлении дополнительными мышцами, выстилающими внутреннюю часть [npc.her] [npc.crotchNipple]. Сжимая и сдавливая их вокруг [npc2.namePos] [npc2.clit+], [npc.name] [npc.verb(cause)] [npc2.herHim], невольно вскрикивая от удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], погружаясь в размеренный ритм, пока [npc.she] [npc.verb(concentrate)] в сжимании дополнительных мышц внутри [npc.her] [npc.crotchNipple+] вокруг [npc2.namePos] [npc2.clit+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] в управлении дополнительными мышцами глубоко внутри [npc.her] [npc.crotchNipple], сжимая их и массируя [npc2.namePos] [npc2.clit+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
