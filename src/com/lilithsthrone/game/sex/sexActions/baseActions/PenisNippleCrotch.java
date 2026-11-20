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
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class PenisNippleCrotch {

	private static String getNippleTitle(GameCharacter character) {
		if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
			return "сосок-вымя";
		} else {
			return "сосок-промежность";
		}
	}
	
	public static final SexAction TEASE_PENIS_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дразнить член "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.cockHead] своего [npc.cock] по [npc2.her] [npc2.crotchNipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] медленно дразня [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"Мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать нежно скользить [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Мягко скользя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] издает тихий [npc.moan] при мысли о том, что сможет проникнуть в [npc2.herHim], когда [npc.she] [npc.verb(feel)] захочет."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] жадно скользя [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать жадно скользить [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Жадно водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вжимая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать грубо [npc2.verb(grind)] [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Грубо водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] этого захочет."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.cock+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] водит [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.cock+] до [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать водить [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Водя [npc.cockTip+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], и [npc2.she] мягко [npc2.verb(push)] [npc2.her] живота против [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем мягко вытолкнуть [npc2.her] [npc2.crotchNipple+] против [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.crotchNipple+], и в ответ мягко [npc2.verb(push)] [npc.her] живота."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] живота против [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно выпятить [npc2.her] живот навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.crotchNipple+], и отчаянно [npc2.verb(thrust)] [npc.her] живот в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.crotchNipple+] стимулируется, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] живот навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] живот навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.crotchNipple+], и яростно [npc2.verb(thrust)] [npc.her] живот в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.crotchNipple+] стимулируется, и [npc2.she] [npc2.verb(push)] [npc2.her] живот навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.verb(let)] от [npc2.moan], прежде чем выпятить [npc2.her] живот навстречу [npc.namePos] [npc.cock].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cock], стимулируя [npc2.her] [npc2.crotchNipple+], и [npc2.verb(thrust)] [npc.her] живот в ответ."));
						break;
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isNippleCrotchVirgin()) {
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
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.crotchNipple(true)] от [npc.namePos] [npc.cock].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, когда [npc2.she] [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.cock+]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.crotchNipple] от [npc.namePos] [npc.cock], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static final SexAction FORCE_PENIS_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)))+" tease";
		}

		@Override
		public String getActionDescription() {
			return "Дразните [npc2.name], скользя [npc2.cockTip] [npc2.her] [npc2.cock] по вашей [npc.crotchNipple+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().isBreastCrotchFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его вверх к [npc.her] [npc.crotchBoobs+]. Медленно толкая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] обещанием проникновения в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], прежде чем начать осторожно водить [npc2.cockTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Сжимая [npc2.namePos] [npc2.cock+], [npc.name] нежно [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.crotchNipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. Жадно двигая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"Сжимая [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], после чего начинает жадно скользить [npc2.cockTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Сжимая [npc2.namePos] [npc2.cock+], [npc.name] жадно [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем резко дернуть его к [npc.her] [npc.crotchBoobs+]. Грубо толкая [npc2.cockTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении проникновения в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и резко дернув его вверх к [npc.her] [npc.crotchBoobs+], прежде чем начать грубо [npc.verb(force)] [npc2.cockTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Сжимая [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(grind)] [npc2.cockTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+], прежде чем направить его вверх к [npc.her] [npc.crotchBoobs+]. Водя [npc2.cockTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая, что проникновение может случиться в любой момент.",

							"Схватив [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.cock+] и [npc.verb(guide)] его вверх к [npc.her] [npc.crotchBoobs+], прежде чем начать скользить [npc2.cockTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Сжимая [npc2.namePos] [npc2.cock+], [npc.name] грубо [npc.verb(slide)] [npc2.cockTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], нежно потирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем нежно скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)], плавно скользя [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой втирая [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируются, и, стремясь напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)], грубо втирая [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] с силой вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом трет [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] с силой вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.cock] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.cock] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.cock] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] резко вырывается из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.cock+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)], заставляя [npc.name] отпустить [npc2.her] [npc2.cock]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
				
		}
		
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	
	public static final SexAction PENIS_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch();
		}
		
		@Override
		public String getActionTitle() {
			return "Трахать [npc2.her] "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.cock+] в [npc2.namePos] [npc2.crotchNipple+] и начни трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.crotchBoobs+], и с медленным, уверенным напором [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], а затем яростно толкаясь вперед, заставляя [npc.her] [npc.cock+] глубоко войти в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.crotchBoobs+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем толкнуться вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.cockTip+] [npc.her] [npc.cock] по [npc2.namePos] [npc2.crotchBoobs+], и коротким толчком [npc.she] слегка [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.cock+] входит в [npc2.herHim], а затем он плавно выпячивает [npc2.her] живот, чтобы вогнать его еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(push)] [npc2.her] животом, погружая [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, когда [npc2.a_moan+] вылетает, а [npc.cock+] входит в [npc2.herHim], после чего он с силой вбивает [npc2.her] в живот, чтобы вогнать его еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] наружу [npc2.her] живот, грубо заставляя [npc.name] погружать [npc.her] [npc.cock+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, пока [npc2.a_moan+], когда [npc.cock+] входит в [npc2.herHim], после чего он жадно вжимает [npc2.her] в живот, чтобы вогнать его еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С [npc2.a_moan+], [npc2.name] с жадностью [npc2.verb(push)] наружу [npc2.her] живот, отчаянно помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, когда [npc2.a_moan+] выходит, а [npc.cock+] входит в [npc2.herHim], после чего [npc2.her] выпячивается, чтобы погрузить его еще глубже в [npc2.her] [npc2.crotchBoob+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] наружу [npc2.her] живот, помогая погрузить [npc.namePos] [npc.cock+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.cock+] входит в [npc2.herHim], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] вытащил его.",
	
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения, пока слезы текут по [npc2.her] [npc2.face], а [npc.namePos] незваный [npc.cock] вжимается глубоко в [npc2.her] [npc2.crotchNipple+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] живот в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], позволяя [npc.namePos] [npc.cock+] глубоко погружаться в [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] живот, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] живот, умоляя, чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают [npc.her] [npc.cock+] глубоко погружаться в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил его из [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил из [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(push)] наружу [npc2.her] живот, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.crotchNipple+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, выпячивая [npc2.her] живот, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] живот наружу, умоляя, чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ медленно [npc2.verb(push)] наружу [npc2.her] живот, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs+].",
	
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] живот, [npc2.she] [npc2.verb(implore)] [npc.name], чтобы он продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] живот, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ яростно [npc2.verb(thrust)] [npc2.her] живот, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахает [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+] и, грубо вжимая [npc2.her] живот в [npc.her] пах, [npc2.she] [npc2.verb(order)] [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] живот в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения заставляют [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Мягко вводите свой [npc.cock] в [npc2.namePos] [npc2.crotchNipple+] и выводите его.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] покачивает [npc.her] [npc.hips] взад-вперед, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Медленно втискивая [npc.her] [npc.cock+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] мягко [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Скользя [npc.her] [npc.cock+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)))+"-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать входить своим [npc.cock+] в [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] с энтузиазмом [npc.verb(start)] [npc.her] [npc.hips] вперед-назад, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"С энтузиазмом втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"[npc.her] [npc.cock+] глубоко входит в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Грубо входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] грубо вколачивая [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Яростно вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] грубо вколачивая [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Силой вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо вколачивает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this)))+"-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] раскачивает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] толчками подает [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"[npc.her] [npc.cock+] глубоко входит в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.namePos] [npc2.scent], когда [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жаждущий "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Жадно входи и выходи своим [npc.cock+] в [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] с энтузиазмом раскачивает [npc.her] [npc.crotchBoobs] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.crotchBoobs].",

					"Энергично втискивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] [npc.her] [npc.crotchBoobs] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать [npc.her] [npc.crotchBoobs] туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction PENIS_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.cock] из [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.crotchNipple], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его легкой хваткой, прежде чем мягко втиснуть его обратно в [npc2.her] [npc2.crotchNipple+].",
	
							"С помощью [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем мягко втиснуть обратно внутрь [npc2.her] [npc2.crotchNipple+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.crotchNipple], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] грубым захватом, прежде чем агрессивно затолкнуть его обратно в [npc2.her] [npc2.crotchNipple+].",
	
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] это, прежде чем грубо затолкнуть его обратно внутрь [npc2.her] [npc2.crotchNipple+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.cock] из [npc2.namePos] [npc2.crotchNipple], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепкую хватку, прежде чем жадно затолкнуть его обратно в [npc2.her] [npc2.crotchNipple+].",
	
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.cock] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем жадно затолкнуть обратно внутрь [npc2.her] [npc2.crotchNipple+].",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.cock] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.cock+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+"-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.cock+] из [npc2.namePos] [npc2.crotchNipple+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.cock+] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] властно [npc.verb(slide)] [npc.cockTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.crotchNipple+], кладя конец грубому траху."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc.her] [npc.cock] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(rub)] [npc.cockTip] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнув [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.cock+] обратно из [npc2.her] [npc2.crotchNipple+], завершая этот трах."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.crotchNipple], и [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] на слабо сопротивляющегося [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.crotchNipple+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] из [npc2.her] [npc2.crotchNipple+], жаждая еще большего внимания [npc.her].",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch();
		}
		
		@Override
		public String getActionTitle() {
			return "Взять "+getNippleTitle(Main.sex.getCharacterPerformingAction())+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.cock+] в свою [npc.crotchNipple+] и трахайся.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(guide)] его вверх к [npc.her] [npc.crotchBoobs+], издав легкий [npc.moan], прежде чем осторожно надавить на [npc.her] живот и заставить [npc2.herHim] проникнуть в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.crotchNipple+], а затем медленно толкая [npc.her] [npc.crotchBoobs] вперед и издавая тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] с нетерпением [npc.verb(guide)] его до [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] живот и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.crotchNipple+], а затем жадно толкая [npc.her] [npc.crotchBoobs] вперед и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] грубо дергает его к [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем яростно вытолкнуть [npc.her] живот вперед и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.crotchNipple+], а затем властно толкая [npc.her] [npc.crotchBoobs] вперед и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.cock+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(guide)] его до [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] живот и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(line)] его до [npc.her] [npc.crotchNipple+], прежде чем толкнуть [npc.her] [npc.crotchBoobs] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] в [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.cock] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.crotchNipple+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.cock] вперед, пока [npc2.she] с энтузиазмом [npc2.verb(start)] трахает [npc.namePos] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] энергично [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.cock] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock] вперед, желая напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] беспощадно трахает [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.cock] вперед как [npc2.she] [npc2.verb(start)] ебаный [npc.namePos] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.cock] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.cock+] из [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock] глубоко в [npc.her] [npc.crotchNipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchNipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] как можно глубже в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.crotchNipple], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" Нежный [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно вгоняет [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" От восторга [npc2.Moaning], [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] начинает неистово вбивать [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" От восторга [npc2.Moaning], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] так глубоко, как только возможно, в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchBoob+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" От восторга [npc2.Moaning], [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterPerformingAction())+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Нежно позвольте [npc2.namePos] [npc2.cock+] оттрахать ваш [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подав [npc.her] живот вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С мягким [npc.moan], [npc.name] плавно [npc.verb(push)] [npc.her] живот вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Медленно выпячивая [npc.her] живот, из-за [npc.namePos] [npc.lips+] вырывается тихий [npc.moan], пока [npc.her] движения заставляют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свой [npc.crotchNipple+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично подав [npc.her] живот вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] живот вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] живот, из-за [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterPerformingAction())+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свой [npc.crotchNipple+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] живота, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С помощью [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] [npc.her] живота вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Сильно выпятив [npc.her] живота, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока [npc.her] движения грубо вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_PENIS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Трахни свой [npc.crotchNipple+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Быстрым толчком [npc.her] живота, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] живота вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Выпятив [npc.her] живота, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_PENIS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью "+getNippleTitle(Main.sex.getCharacterPerformingAction())+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Жадно трахай свой [npc.crotchNipple+] на [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично подав [npc.her] живот вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] живот вперед, заставляя [npc2.namePos] [npc2.cock+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] живот, из-за [npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterPerformingAction())+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Попробуй оттянуть свой [npc.crotchNipple+] от [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)], наполняя [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.crotchNipple+].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.crotchNipple] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] медленно входит и выходит из [npc.her] [npc.crotchBoob+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)], наполняя [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.crotchNipple+].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести [npc.her] [npc.crotchNipple] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.cock+] неистово [npc2.verb(continue)] входит и выходит из [npc.her] [npc.crotchBoob+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в мучении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)], наполняя [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.cock] из [npc.her] [npc.crotchNipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.crotchNipple] от нежеланного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо вбивается туда-сюда в [npc.her] [npc.crotchBoob+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в мучении, пока [npc2.namePos] [npc2.cock+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterPerformingAction())+"-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Заставь [npc2.name] вытащить [npc2.her] [npc2.cock] из твоей [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.cock] из [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.cock] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent] перед тем как выскользнуть [npc2.her] [npc2.cock] из [npc.her] [npc.crotchNipple+]."));
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
								" [npc2.Name] [npc2.verb(let)] выталкивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] [npc2.herHim] из ебаного [npc.her] [npc.crotchNipple+].",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" control";
		}

		@Override
		public String getActionDescription() {
			return "Сжать свои внутренние мышцы [npc.crotchNipple] вокруг [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getNippleCrotchOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] [npc.crotchNipple] вокруг [npc2.namePos] [npc2.cock+].",

					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] извлекает [npc.a_moan+] как [npc.she] [npc.verb(focus)], контролируя дополнительные мышцы, выстилающие внутренности [npc.her] [npc.crotchNipple]. Сжимая и вдавливая их вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(cause)] [npc2.herHim], заставляя издать непроизвольный крик удовольствия."
						:""),

					"[npc.Name] [npc.verb(find)], [npc.her] [npc.moans] входят в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)], сжимая дополнительные мышцы внутри [npc.her] [npc.crotchNipple+] вокруг [npc2.namePos] [npc2.cock+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри [npc.her] [npc.crotchNipple], сжимая их и массируя [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
