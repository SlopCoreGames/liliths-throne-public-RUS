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
 * @since 0.4.9.13
 * @version 0.4.9.13
 * @author Innoxia
 */
public class TentacleNippleCrotch {

	private static String getNippleTitle(GameCharacter character) {
		if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
			return "сосок-вымя";
		} else {
			return "сосок-промежность";
		}
	}
	
	public static final SexAction TEASE_TENTACLE_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дразнящие щупальца "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя своим [npc.tentacle+] по [npc2.her] [npc2.crotchNipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] медленно дразня [npc.tentacleTip+] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать нежно водить [npc.tentacleTip] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+].",

							"Мягко водя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] издает тихий [npc.moan] при мысли о том, что может проникнуть в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] так захочет."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] жадно водит [npc.tentacleTip+] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать жадно водить [npc.tentacleTip] вверх и вниз по одной из [npc2.her] [npc2.crotchNipples+].",

							"Нетерпеливо водя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вжимая [npc.her] [npc.tentacle+] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать грубо [npc2.verb(grind)] [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Грубо водя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] водит [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tentacle+] до [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать водить [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Водя [npc.tentacleTip+] [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] грудь против [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.crotchNipple+] против [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.crotchNipple+], и мягко [npc2.verb(push)] [npc.her] грудь в ответ."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] грудь против [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.crotchNipple+], и отчаянно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.crotchNipple+] стимулируются, и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] грудь, прижимая её к [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.crotchNipple+], и яростно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как [npc2.her] [npc2.crotchNipple+] стимулируются, и [npc2.she] [npc2.verb(push)] [npc2.her] грудь, прижимая её к [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.moan], прежде чем выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tentacle].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tentacle], стимулируя [npc2.her] [npc2.crotchNipple+], и [npc2.verb(thrust)] [npc.her] грудь в ответ."));
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
										" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)] оттянуть [npc2.her] [npc2.crotchNipple(true)] от [npc.namePos] [npc.tentacle].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.crotchNipple(true)] от [npc.namePos] [npc.tentacle], [npc2.speech(Нет! Не надо! Пожалуйста, уходи от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static final SexAction FORCE_TENTACLE_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дразнить щупальцем ("+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+")";
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.tentacleTip] [npc2.her] [npc2.tentacle] по твоим [npc.crotchNipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. Медленно толкая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], после чего начинает плавно скользить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] нежно [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.crotchNipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая проникновение в любой момент."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. Жадно толкая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] предвкушением проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], после чего он начинает жадно скользить [npc2.tentacleTip] вверх и вниз по [npc.her], [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] жадно [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем резко дернуть его к [npc.her] [npc.crotchBoobs+]. Грубо толкая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] предвкушением проникновения в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+] и резким рывком вверх к [npc.her] [npc.crotchBoobs+], после чего он начинает грубо [npc.verb(force)] [npc2.tentacleTip] вверх и вниз по [npc.her], [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] грубо [npc.verb(grind)] [npc2.tentacleTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. Проталкивая [npc2.tentacleTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая, что проникновение может случиться в любой момент.",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tentacle+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], после чего начинает скользить [npc2.tentacleTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] грубо [npc.verb(slide)] [npc2.tentacleTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(start)] потирая [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем нежно водить [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой втирать [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, стремясь напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом трет [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] трет [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.tentacle] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tentacle] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.her] [npc2.tentacle] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)] оттягивает [npc2.her] [npc2.tentacle+] от [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно попытаться оттянуть [npc2.her] [npc2.tentacle+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, пока [npc2.she] [npc2.verb(beg)] [npc.name], не давая отпустить [npc2.her] [npc2.tentacle]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TENTACLE, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	
	public static final SexAction TENTACLE_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Секс щупальцами "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.tentacle+] в [npc2.namePos] [npc2.crotchNipple+] и начни трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно податься вперед, погружая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], и медленным, уверенным давлением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tentacleTip+] [npc.her] [npc.tentacle] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, вбивая [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], и мощным толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, вонзая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle] над [npc2.namePos] [npc2.crotchBoobs+], и легким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.tentacle+] входит в [npc2.herHim], а затем плавно выпячивает [npc2.her] грудь, чтобы вогнать ее еще глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(push)] [npc2.her] грудь, погружая [npc.namePos] [npc.tentacle+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.tentacle+] входит в [npc2.herHim], а затем яростно вбивается в [npc2.her] грудь, чтобы вогнать его еще глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С помощью [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] из [npc2.her] груди, грубо заставляя [npc.name] погружаться [npc.her] [npc.tentacle+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tentacle+] входит в [npc2.herHim], а затем жадно выталкивает [npc2.her] грудь, чтобы погрузить ее еще глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С помощью [npc2.a_moan+], [npc2.name] с жадностью [npc2.verb(push)] из [npc2.her] груди, отчаянно помогая погрузить [npc.namePos] [npc.tentacle+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.tentacle+] входит в [npc2.herHim], а затем выталкивает [npc2.her] грудь, чтобы погрузить ее еще глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С помощью [npc2.a_moan+], [npc2.name] [npc2.verb(push)] из [npc2.her] груди, помогая погрузить [npc.namePos] [npc.tentacle+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], когда [npc.tentacle+] входит в [npc2.herHim], и, со слезами, текущими по [npc2.her] [npc2.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] мог её вытащить.",
	
								" С помощью [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежеланного проникновения; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.namePos] [npc.tentacle] глубоко вжимается в [npc2.her] [npc2.crotchNipple+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], позволяя [npc.namePos] [npc.tentacle+] глубоко погрузиться в [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(thrust)] вперед [npc2.her] грудь, жадно умоляя, чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь — и безуспешно — отстраниться от [npc.namePos] [npc.tentacle], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил его из [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил из [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с нетерпением [npc2.verb(push)] вперед [npc2.her] грудь в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], позволяя [npc.namePos] [npc.tentacle+] глубоко погрузиться в [npc2.her] [npc2.crotchNipple+].",
	
							" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+], и, выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(push)] вперед [npc2.her] грудь, умоляя, чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] грудь назад в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], прося [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs+].",
	
							" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] грудь вперед в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], и, грубо прижимая [npc2.her] грудь к [npc.her] паху, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] грудь в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения вгоняют [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fuck";
		}
		@Override
		public String getActionDescription() {
			return "Мягко вводите свой [npc.tentacle] в [npc2.namePos] [npc2.crotchNipple+] и выводите его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] вгоняет его туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Медленно толкая [npc.her] [npc.tentacle+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] мягко [npc.verb(thrust)] его вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Скользя [npc.her] [npc.tentacle+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко вгонять и выгонять его, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tentacle-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай вгонять свой [npc.tentacle+] в [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"С энтузиазмом вталкивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] его вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Вгоняя [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно двигать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Грубо вгоняй свой [npc.tentacle+] в [npc2.namePos] [npc2.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вбивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)], резко толкая его туда-сюда и издавая [npc.a_moan+] при каждом рывке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Яростно вонзая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)], грубо вколачивая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"С силой вонзая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(start)], чтобы грубо вбивать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tentacle-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+] при каждом движении, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Втискивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"[npc.her] [npc.tentacle+] глубоко входит в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы вгонять и выгонять его, вдыхая [npc2.namePos] [npc2.scent], когда [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жаждущий "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fuck";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью качай свой [npc.tentacle+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.crotchBoobs].",

					"С энтузиазмом втискивая [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"[npc.her] [npc.tentacle+] глубоко входит в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tentacle] из [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытянуть [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.crotchNipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] нежно сжимает его, прежде чем мягко вдавить обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем осторожно вдавить обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] мягко [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.tentacle+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь вытянуть [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.crotchNipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] грубо сжимает его, прежде чем агрессивно вдавить обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем грубо вдавить обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.tentacle+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.crotchNipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепкую хватку, прежде чем жадно затолкнуть его обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] это, прежде чем жадно затолкнуть его обратно внутрь [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.tentacle+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.crotchNipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] [npc2.verb(move)] вперед, удерживая его глубоко в [npc2.her] [npc2.crotchNipple+].",

						"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle] от [npc2.name], но [npc2.she] быстро [npc2.verb(move)] вперед, удерживая его глубоко внутри [npc2.her] [npc2.crotchNipple+].",

						"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, удерживая [npc2.her] [npc2.crotchNipple+] прижатым к [npc.her] [npc.tentacle+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tentacle+] из [npc2.namePos] [npc2.crotchNipple+] и прекрати трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.tentacle+] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] властно [npc.verb(slide)] [npc.tentacleTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.tentacle+] обратно из [npc2.her] [npc2.crotchNipple+], кладя конец грубому траху."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc.her] [npc.tentacle] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(rub)] по [npc.tentacleTip] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tentacle+] обратно из [npc2.her] [npc2.crotchNipple+], кладя конец этому ебаному сексу."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.crotchNipple(true)], а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(carry)] на слабо сопротивляющейся [npc.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.crotchNipple+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.tentacle+] из [npc2.her] [npc2.crotchNipple+], жаждая еще большего [npc.her] внимания.",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.tentacle+] в свою [npc.crotchNipple+] и получай еблю.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] медленно [npc.verb(guide)] его вверх к [npc.her] [npc.crotchBoobs+], издавая легкий [npc.moan], прежде чем мягко толкнуть [npc.her] грудь и заставить [npc2.herHim] проникать в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] его вверх к [npc.her] [npc.crotchNipple+], прежде чем медленно толкнуть [npc.her] [npc.crotchBoobs] вперед, издавая тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] жадно [npc.verb(guide)] её к [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] её к [npc.her] [npc.crotchNipple+], прежде чем с жадностью толкнуть [npc.her] [npc.crotchBoobs] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] грубо дергает её к [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем яростно выпятить [npc.her] грудь вперед и заставить [npc2.herHim] пронзить [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] её к [npc.her] [npc.crotchNipple+], прежде чем властно толкнуть [npc.her] [npc.crotchBoobs] вперед и издать [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(guide)] её к [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tentacle], [npc.name] [npc.verb(line)] его до [npc.her] [npc.crotchNipple+], затем толкая [npc.her] [npc.crotchBoobs] вперед и издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tentacle+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко толкая [npc2.her] [npc2.tentacle] вперед, а [npc2.she] [npc2.verb(start)], чтобы трахать [npc.namePos] [npc.crotchNipple+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.tentacle] вперед, а [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.tentacle] вперед и [npc2.verb(start)], чтобы безжалостно трахать [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle] вперед, стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.tentacle] вперед, пока [npc2.she] [npc2.verb(start)] трахает [npc.namePos] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] трахает [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.tentacle] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.tentacle+] из [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.tentacle] глубоко в [npc.her] [npc.crotchNipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchNipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tentacle+] как можно глубже в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.tentacle] от [npc.namePos] [npc.crotchNipple(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттащить [npc2.her] [npc2.tentacle] от [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" Нежный [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно вгоняет [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] срывается с губ [npc2.namePos], после чего [npc2.she] [npc2.verb(start)] неистово вбивает [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle+] как можно глубже в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tentacle+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tentacle+] deep into [npc.namePos] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Позвольте [npc2.namePos] [npc2.tentacle+] нежно оттрахать ваш [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подав [npc.her] грудь вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С мягким [npc.moan], [npc.name] плавно [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Медленно выпячивая [npc.her] грудь, из меж[npc.namePos] [npc.lips+] доносится тихий [npc.moan], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tentacle+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свои [npc.crotchNipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично подав [npc.her] грудь вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] грудь, из меж[npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tentacle+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свои [npc.crotchNipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким толчком [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать все глубже в [npc.her] [npc.crotchNipple+].",

					"Сильно выпятив [npc.her] грудь, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока движения [npc.her] грубо вгоняют [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Трахать твою [npc.crotchNipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Быстрым толчком [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] вперед, заставляя [npc2.namePos] [npc2.tentacle+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Выпятив [npc.her] грудь, [npc.a_moan+] вырывается из-за [npc.namePos] [npc.lips+], пока движения [npc.her] вгоняют [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахай свои [npc.crotchNipple+] на [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Энергично подав [npc.her] грудь вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.tentacle+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tentacle+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] грудь, из меж[npc.namePos] [npc.lips+] вырывается [npc.a_moan+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tentacle+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Попробуй оттянуть свою [npc.crotchNipple+] от [npc2.namePos] [npc2.tentacle+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] наполняются [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.crotchNipple(true)] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.tentacle+] медленно [npc2.verb(continue)] скользит туда-сюда в [npc.her] [npc.crotchBoob+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] наполняются [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.crotchNipple(true)] подальше от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+] [npc2.verb(continue)] неистово вбивается то входит, то выходит из [npc.her] [npc.crotchBoob+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] наполняются [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.crotchNipple(true)] подальше от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+] [npc2.verb(continue)] грубо вбивается то входит, то выходит из [npc.her] [npc.crotchBoob+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] наполняются [npc.verb(start)], скапливаясь в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] наружу [npc.a_sob+], прежде чем слабо попытаться вытянуть [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.crotchNipple+].",

						"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь утянуть [npc.her] [npc.crotchNipple(true)] подальше от нежелательного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tentacle+] [npc2.verb(remain)] глубоко вклинивается в [npc.her] [npc.crotchBoob+(true)].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+] [npc2.verb(remain)] глубоко в [npc.her] [npc.crotchNipple+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tentacle-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытянуть [npc2.her] [npc2.tentacle] из твоих [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко вырывая [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.tentacle] из [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.tentacle] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трахать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.tentacle] из [npc.her] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" control";
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
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)] при сжимании дополнительных внутренних мышц внутри [npc.her] [npc.crotchNipple(true)] вокруг [npc2.namePos] [npc2.tentacle+].",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(focus)] в управлении дополнительными мышцами, выстилающими внутреннюю часть [npc.her] [npc.crotchNipple(true)]. Сжимая и стягивая их вокруг [npc2.namePos] [npc2.tentacle+], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходящие в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)] в сжатии дополнительных мышц внутри [npc.her] [npc.crotchNipple+] вокруг [npc2.namePos] [npc2.tentacle+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] в управлении дополнительными мышцами глубоко внутри [npc.her] [npc.crotchNipple(true)], сжимая их и массируя [npc2.namePos] [npc2.tentacle+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
