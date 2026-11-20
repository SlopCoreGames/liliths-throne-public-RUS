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
public class TailNippleCrotch {

	private static String getNippleTitle(GameCharacter character) {
		if(character.getBreastCrotchShape()==BreastShape.UDDERS) {
			return "сосок-вымя";
		} else {
			return "сосок-промежность";
		}
	}
	
	public static final SexAction TEASE_TAIL_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Ласки хвостом "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc.tail+] по [npc2.her] [npc2.crotchNipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tail+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] медленно дразня [npc.tailTip+] вверх и вниз по [npc2.her] [npc2.crotchNipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"Мягким [npc.moan], [npc.name] [npc.verb(guide)] [npc.her] [npc.tail+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать нежно водить [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Мягко водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] издает тихий [npc.moan] при мысли о том, что может проникнуть в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tail+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] с нетерпением [npc.verb(start)] водит [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовый в любой момент проникнуть в [npc2.herHim].",

							"С помощью [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tail+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать с нетерпением водить [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"С жадностью водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Втирая [npc.her] [npc.tail+] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] слегка [npc.verb(pull)] назад, прежде чем начать водить [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовая начать трахать [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(line)] [npc.her] [npc.tail+] к [npc2.namePos] [npc2.crotchBoobs+], после чего начинает грубо [npc2.verb(grind)] [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Грубо водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет начать трахать [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Направляя [npc.her] [npc.tail+] к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] водит [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+], готовая проникнуть в [npc2.herHim] в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(guide)] [npc.her] [npc.tail+] до [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать водить [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.crotchNipples+].",

							"Водя [npc.tailTip+] [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+] при мысли о том, что сможет проникать в [npc2.herHim] всякий раз, когда [npc.she] [npc.verb(feel)] это."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягкий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], и [npc2.she] нежно [npc2.verb(push)] [npc2.her] грудь к [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], прежде чем осторожно вытолкнуть [npc2.her] [npc2.crotchNipple+] навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.crotchNipple+], и в ответ мягко [npc2.verb(push)] [npc.her] грудь."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируются [npc2.her] [npc2.crotchNipple+], и [npc2.she] жадно [npc2.verb(push)] [npc2.her] грудь к [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно выпятить [npc2.her] грудь против [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.crotchNipple+], и отчаянно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.crotchNipple+], и [npc2.she] с силой [npc2.verb(thrust)] [npc2.her] грудь, прижимая её к [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем грубо выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.crotchNipple+], и яростно [npc2.verb(thrust)] [npc.her] грудь в ответ."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] чувствует, как стимулируют [npc2.her] [npc2.crotchNipple+], и [npc2.she] [npc2.verb(push)] [npc2.her] грудь, прижимая её к [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.verb(let)] из себя [npc2.moan], прежде чем выпятить [npc2.her] грудь навстречу [npc.namePos] [npc.tail].",
	
								" [npc2.Name] [npc2.moanVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.tail], стимулируя [npc2.her] [npc2.crotchNipple+], и [npc2.verb(thrust)] [npc.her] грудь в ответ."));
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
										" [npc2.A_sob+] вырывается из-под [npc2.namePos] [npc2.lips+], когда [npc2.she] [npc2.verb(try)] оттянуть [npc2.her] [npc2.crotchNipple(true)] от [npc.namePos] [npc.tail].",
										" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], пока [npc2.she] [npc2.verb(struggle)] против [npc.name].",
										" [npc2.Name] [npc2.sobVerb] в смятении, пока [npc2.she] [npc2.verb(try)], пытаясь отстраниться от [npc.name]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.crotchNipple(true)] от [npc.namePos] [npc.tail], [npc2.speech(Нет! Не надо! Пожалуйста, отойди от меня!)]",
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
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static final SexAction FORCE_TAIL_OVER_NIPPLE_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дразнить хвостом ("+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+")";
		}
		@Override
		public String getActionDescription() {
			return "Дразнить [npc2.name], скользя [npc2.tailTip] [npc2.her] [npc2.tail] по вашим [npc.crotchNipple+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()) != SexPace.SUB_RESISTING
					&& Main.game.isNipplePenEnabled()
					&& Main.sex.getCharacterPerformingAction().isBreastFuckableNipplePenetration()
					&& !this.isForbiddenArea(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL);
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. Медленно толкая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С мягким [npc.moan], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], после чего начинает нежно водить [npc2.tailTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] нежно [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.crotchNipple+], издавая тихий [npc.moan], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении скорого проникновения."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. Жадно толкая [npc2.tailTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и [npc.verb(guide)], дело доходит до [npc.her] [npc.crotchBoobs+], после чего начинается нетерпеливое скольжение [npc2.tailTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] с нетерпением [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем резко дернуть его вверх к [npc.her] [npc.crotchBoobs+]. Грубо водя [npc2.tailTip+] вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name] в предвкушении того, что проникновение может случиться в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и резко дергает его к [npc.her] [npc.crotchBoobs+], после чего начинает грубо [npc.verb(force)] [npc2.tailTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] грубо [npc.verb(grind)] [npc2.tailTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim] в предвкушении проникновения."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+], прежде чем направить его к [npc.her] [npc.crotchBoobs+]. [npc2.tailTip+] ходит вверх и вниз по [npc.her] [npc.crotchNipple+], [npc.she] [npc.verb(tease)] [npc2.name], обещая проникновение в любой момент.",

							"С [npc.a_moan+], [npc.name] [npc.verb(grab)] [npc2.namePos] [npc2.tail+] и [npc.verb(guide)] его к [npc.her] [npc.crotchBoobs+], после чего начинается скольжение [npc2.tailTip] вверх и вниз по [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail+], [npc.name] [npc.verb(slide)] [npc2.tailTip+] по [npc.her] [npc.crotchNipple+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(tease)] [npc2.herHim], обещая скорое проникновение."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(start)] нежно потирает [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], прежде чем начать мягко водить [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируется, и, не нуждаясь в дальнейшем поощрении, [npc2.she] [npc2.verb(start)] мягко водит [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], грубо втирая [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем с силой начать втирать [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируются, и, желая напомнить [npc.name], кто здесь главный, [npc2.she] [npc2.verb(start)], с энтузиазмом втирая [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_EAGER:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)] с энтузиазмом растирая [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем жадно скользить [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, пока [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+], и [npc2.she] [npc2.verb(start)], потирая [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.moan], прежде чем скользить [npc2.her] [npc2.tail] вперед-назад по [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.moansVerb] в восторге, когда [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.tail] стимулируются, и, не нуждаясь в дальнейшем подстрекательстве, [npc2.she] [npc2.verb(start)], скользя [npc2.her] [npc2.her] [npc2.tail] вверх и вниз по [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.tail+] от [npc.namePos] [npc.crotchNipple+].",
	
								" [npc2.Name] [npc2.verb(let)] отчаянный [npc2.sob], прежде чем лихорадочно пытается оттянуть [npc2.her] [npc2.tail+] от [npc.namePos] [npc.labia+].",
	
								" [npc2.Name] [npc2.sobsVerb] в смятении, когда [npc2.she] [npc2.verb(beg)] [npc.name] отпустить [npc2.her] [npc2.tail]."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	
	public static final SexAction TAIL_FUCKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Хвостетрах "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getActionDescription() {
			return "Вонзи свой [npc.tail+] в [npc2.namePos] [npc2.crotchNipple+] и начни трахать [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tailTip+] [npc.her] [npc.tail] по [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуть вперед, погружая [npc.her] [npc.tail+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] по [npc2.namePos] [npc2.crotchBoobs+], и с медленным, ровным давлением, [npc.she] осторожно [npc.verb(sink)] его глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.tail+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] ее глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tailTip+] [npc.her] [npc.tail] в [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануться вперед, с силой вбивая [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.crotchBoobs+], и мощным толчком [npc.she] грубо [npc.verb(slam)] ее глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануться вперед, жадно вонзая [npc.her] [npc.tail+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.crotchBoobs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] ее глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, вжимая [npc.her] [npc.tail+] в [npc2.her] [npc2.crotchNipple+].",

							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail] над [npc2.namePos] [npc2.crotchBoobs+], и коротким толчком [npc.she] [npc.verb(sink)] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.tail+] входит в [npc2.herHim], после чего нежно выталкивая [npc2.her] грудь, чтобы вогнать её еще глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С мягким [npc2.moan], [npc2.name] плавно [npc2.verb(push)] [npc2.her] грудь, вжимая [npc.namePos] [npc.tail+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.tail+] входит в [npc2.herHim], после чего яростно выталкивая [npc2.her] грудь, чтобы вогнать её еще глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С [npc2.a_moan+], [npc2.name] яростно [npc2.verb(push)] [npc2.her] грудь, грубо заставляя [npc.name] вжимать [npc.her] [npc.tail+] еще глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.tail+] входит в [npc2.herHim], после чего она жадно выпячивает [npc2.her] грудь, чтобы вогнать её ещё глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С [npc2.a_moan+], [npc2.name] жадно [npc2.verb(push)] [npc2.her] грудь, отчаянно помогая вогнать [npc.namePos] [npc.tail+] ещё глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.tail+] входит в [npc2.herHim], после чего она выпячивает [npc2.her] грудь, чтобы вогнать её ещё глубже в [npc2.her] [npc2.crotchBoob+(true)].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] грудь, помогая вогнать [npc.namePos] [npc.tail+] ещё глубже в [npc2.her] [npc2.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], когда [npc.tail+] входит в [npc2.herHim], и, пока с [npc2.her] [npc2.face] текут слёзы, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] мог её вытянуть.",
	
								" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от нежелательного проникновения, слёзы текут по [npc2.her] [npc2.face], пока [npc.namePos] незваное [npc.tail] глубоко вжимается в [npc2.her] [npc2.crotchNipple+]."));
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
							" [npc2.Name] в ответ жадно [npc2.verb(push)] [npc2.her] грудь, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tail+] глубоко в [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] грудь, умоляя [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока движения [npc2.her] помогают погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.tail], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] вытащил его из [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] вытащил его из [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] вытащил из [npc2.her] [npc2.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(push)] [npc2.her] грудь, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(help)], чтобы [npc.namePos] [npc.tail+] погрузились глубоко в [npc2.her] [npc2.crotchNipple+].",
	
							" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+], и, выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] не прекращать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] грудь назад в ответ, издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], прося [npc.name] продолжать трахать [npc2.her] [npc2.crotchBoobs+].",
	
							" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], и, медленно выпячивая [npc2.her] грудь, [npc2.she] [npc2.verb(implore)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] не прекращать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения помогают погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] грудь вперед в ответ, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(demand)], что [npc.name] [npc.verb(continue)] трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips+], и, грубо прижимая [npc2.her] грудь к [npc.her] паху, [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал трахать [npc2.her] [npc2.crotchBoobs].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] в [npc.namePos] пах, приказывая [npc.herHim] продолжать трахать [npc2.her] [npc2.crotchBoobs], пока [npc2.her] движения заставляют [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fuck";
		}
		@Override
		public String getActionDescription() {
			return "Плавно вводите свой [npc.tail] в [npc2.namePos] [npc2.crotchNipple+] и выводите его обратно.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] его туда-сюда, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Медленно втискивая [npc.her] [npc.tail+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] мягко [npc.verb(thrust)] его вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Втискивая [npc.her] [npc.tail+] в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)] плавно качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжайте вводить свой [npc.tail+] в [npc2.namePos] [npc2.crotchNipple+] и выводить его обратно.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] с энтузиазмом [npc.verb(start)], то и дело вгоняя его внутрь и вынимая обратно, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"С энтузиазмом вгоняя [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Вколачивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с жадностью двигает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Грубо вгоняй свой [npc.tail+] в [npc2.namePos] [npc2.crotchNipple+] туда-сюда.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(start)] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жестоко [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Яростно вгоняя [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] грубо [npc.verb(start)] туда-сюда, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Силой вонзая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] счастливо [npc.verb(start)], совершая резкие толчки туда-сюда и жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить трахать [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+] при каждом движении, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Втискивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)], толкая его туда-сюда и издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs].",

					"Вколачивая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] с упоением [npc.verb(start)], чтобы качать его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.crotchBoobs]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жаждущий "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Жадно вколачивай свой [npc.tail+] в [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] счастливо [npc.verb(fuck)] [npc2.namePos] [npc2.crotchBoobs].",

					"С энтузиазмом вгоняя [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] неистово [npc.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.herHim].",

					"[npc.her] [npc.tail+] глубоко входит в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с жадностью качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(fuck)] [npc2.herHim]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tail] из [npc2.namePos] [npc2.crotchNipple+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tail] из [npc2.namePos] [npc2.crotchNipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его, нежно удерживая, прежде чем мягко загнать его обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его, прежде чем мягко загнать его обратно внутрь [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.tail+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы вытянуть [npc.her] [npc.tail] из [npc2.namePos] [npc2.crotchNipple(true)], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] грубым захватом, прежде чем агрессивно затолкнуть его обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] это, прежде чем грубо затолкнуть обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.tail+]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.tail] из [npc2.namePos] [npc2.crotchNipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепко сжимает её, прежде чем жадно затолкнуть обратно в [npc2.her] [npc2.crotchNipple+].",
		
								"При [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] её, прежде чем жадно затолкнуть обратно внутрь [npc2.her] [npc2.crotchNipple+].",
		
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.crotchNipple+] вниз на [npc.her] [npc.tail+]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] попытаться вытянуть [npc.her] [npc.tail] из [npc2.namePos] [npc2.crotchNipple(true)], но [npc.her] усилия оказываются напрасными, так как [npc2.name] [npc2.verb(move)] вперед, удерживая её глубоко засунутой в [npc2.her] [npc2.crotchNipple+].",

						"При [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail] от [npc2.name], но [npc2.she] быстро [npc2.verb(move)] вперед, удерживая её глубоко внутри [npc2.her] [npc2.crotchNipple+].",

						"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tail] от [npc2.namePos] [npc2.crotchNipple+], но [npc2.name] быстро [npc2.verb(shift)] положение, удерживая [npc2.her] [npc2.crotchNipple+] прижатыми к [npc.her] [npc.tail+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_FUCKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fuck";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tail+] из [npc2.namePos] [npc2.crotchNipple+] и перестань трахать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо вырывая [npc.her] [npc.tail+] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] властно [npc.verb(slide)] [npc.tailTip+] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.tail+] обратно из [npc2.her] [npc2.crotchNipple+], кладя конец грубому траху."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.tail] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(rub)] [npc.tailTip] вверх и вниз по одному из [npc2.her] [npc2.crotchBoobs+] в последний раз, прежде чем отстраниться.",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tail+] обратно из [npc2.her] [npc2.crotchNipple+], кладя конец траху."));
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
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.tail+] из [npc2.her] [npc2.crotchNipple+], жаждая еще большего внимания [npc.her].",
	
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Вставь [npc2.namePos] [npc2.tail+] в свою [npc.crotchNipple+] и трахайся.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] медленно [npc.verb(guide)] ее до [npc.her] [npc.crotchBoobs+], издавая легкий [npc.moan], прежде чем осторожно выпятить [npc.her] грудь и заставить [npc2.herHim] проникнуть в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] ее до [npc.her] [npc.crotchNipple+], прежде чем медленно толкнуть [npc.her] [npc.crotchBoobs] вперед и издать тихий [npc.moan], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] жадно [npc.verb(guide)] ее до [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем отчаянно выпятить [npc.her] грудь и заставить [npc2.herHim] проникнуть в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] её до [npc.her] [npc.crotchNipple+], прежде чем жадно толкнуть [npc.her] [npc.crotchBoobs] вперёд, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] грубо дергает её вверх до [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем яростно выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] её до [npc.her] [npc.crotchNipple+], прежде чем властно толкнуть [npc.her] [npc.crotchBoobs] вперёд, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(guide)] её до [npc.her] [npc.crotchBoobs+], издавая [npc.a_moan+], прежде чем выпятить [npc.her] грудь и заставить [npc2.herHim] пронзить [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.tail], [npc.name] [npc.verb(line)] её до [npc.her] [npc.crotchNipple+], прежде чем толкнуть [npc.her] [npc.crotchBoobs] вперёд, издавая [npc.a_moan+], пока [npc.she] [npc.verb(penetrate)] [npc.herself] на [npc2.her] [npc2.tail+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], мягко подталкивая [npc2.her] [npc2.tail] вперед, в то время как [npc2.she] [npc2.verb(start)] страстно трахает [npc.namePos] [npc.crotchNipple+].",
	
								" С мягким [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.tail] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] в виде [npc2.she] [npc2.verb(enter)] [npc.herHim], жадно толкая [npc2.her] [npc2.tail] вперед, пока [npc2.she] [npc2.verb(start)] с энтузиазмом трахает [npc.namePos] [npc.crotchNipple+].",
	
								" Со [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.tail] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] энергично трахает [npc.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо вбивает [npc2.her] [npc2.tail] вперед и [npc2.verb(start)] безжалостно трахать [npc.her] [npc.crotchNipple+].",
	
								" Со [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tail] вперед, желая напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] безжалостно трахает [npc.namePos] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], толкая [npc2.her] [npc2.tail] вперед как [npc2.she] [npc2.verb(start)] ебаный [npc.namePos] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.tail] вперед, глубоко погружая его в [npc.namePos] [npc.crotchNipple+], пока [npc2.she] [npc2.verb(start)] ебаный [npc.herHim]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.tail] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вырвать [npc2.her] [npc2.tail+] из [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.tail] глубоко в [npc.her] [npc.crotchNipple+]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchNipple+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tail+] как можно глубже в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттянуть [npc2.her] [npc2.tail] от [npc.namePos] [npc.crotchNipple(true)], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заставляя [npc2.her] [npc2.tail+] глубоко войти в [npc.her] [npc.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.tail] от [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" Легкий [npc2.moan] вырывается из рта [npc2.namePos], прежде чем [npc2.she] медленно [npc2.verb(start)], задвигая [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] яростно вонзает [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tail+] так глубоко, как только возможно, в [npc.namePos] [npc.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchBoob+(true)], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(penetrate)] [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchNipple+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.tail+] глубоко в [npc.namePos] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Позвольте [npc2.namePos] [npc2.tail+] нежно трахнуть вашу [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягким толчком груди [npc.her], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погружает [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(push)] [npc.her] грудь вперед, заставляя [npc2.namePos] [npc2.tail+] проникать все глубже в [npc.her] [npc.crotchNipple+].",

					"Медленно выпячивая грудь [npc.her], мягкий [npc.moan] доносится из-между [npc.namePos] [npc.lips+], пока движения [npc.her] вдавливают [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахайте свою [npc.crotchNipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадным толчком [npc.her] грудью вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], вонзаясь [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудью вперед, заставляя [npc2.namePos] [npc2.tail+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] грудь, [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Грубо трахай свою [npc.crotchNipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С яростным толчком [npc.her] грудью вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(thrust)] [npc.her] грудью вперед, заставляя [npc2.namePos] [npc2.tail+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Сильно выпячивая [npc.her] грудь, [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.her] движения грубо вгоняют [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RIDING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Трахни свою [npc.crotchNipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Резким движением [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], заставляя [npc2.namePos] [npc2.tail+] глубоко погружаться в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+] проникать все глубже в [npc.her] [npc.crotchNipple+].",

					"Выпятив [npc.her], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RIDING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью трахайте свою [npc.crotchNipple+] на [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадным толчком [npc.her] грудью вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], вонзаясь [npc2.namePos] [npc2.tail+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(push)] [npc.her] грудью вперед, заставляя [npc2.namePos] [npc2.tail+] погружаться всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энергично выпячивая [npc.her] грудь, [npc.a_moan+] вырывается из-под [npc.namePos] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.tail+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свои [npc.crotchNipple+] от [npc2.namePos] [npc2.tail+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.crotchNipple(true)] от нежелательного проникновения [npc2.namePos], в отчаянной борьбе, пока [npc2.her] [npc2.tail+] медленно [npc2.verb(continue)] входит и выходит из [npc.her] [npc.crotchBoob+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь уберечь [npc.her] [npc.crotchNipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.tail+] [npc2.verb(continue)] неистово вбивается в [npc.her] [npc.crotchBoob+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] [npc2.verb(continue)] жадно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail] из [npc.her] [npc.crotchNipple+].",
	
								"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаяся уберечь [npc.her] [npc.crotchNipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, пока [npc2.her] [npc2.tail+] [npc2.verb(continue)] грубо вколачивается в [npc.her] [npc.crotchBoob+(true)].",
	
								"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] [npc2.verb(continue)] яростно вонзается глубоко в [npc.her] [npc.crotchNipple+]."));
						break;
					default:
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(feel)] заставляет [npc.verb(start)] набухнуть в [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail] из [npc.her] [npc.crotchNipple+].",

						"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.crotchNipple(true)] от нежелательного проникновения [npc2.namePos], отчаянно борясь, когда [npc2.her] [npc2.tail+] [npc2.verb(remain)] глубоко внутри [npc.her] [npc.crotchBoob+(true)].",

						"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoobs], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+] [npc2.verb(remain)] глубоко в [npc.her] [npc.crotchNipple+]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Остановиться "+getNippleTitle(Main.sex.getCharacterTargetedForSexAction(this))+" tail-fucked";
		}

		@Override
		public String getActionDescription() {
			return "Заставь [npc2.name] вытащить [npc2.her] [npc2.tail] из твоих [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.tail] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.tail] из [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.tail] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(tell)] [npc2.name], чтобы прекратить трах [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.tail] из [npc.her] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getNippleTitle(Main.sex.getCharacterPerformingAction()))+" control";
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
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] [npc.crotchNipple(true)], обхватывая [npc2.namePos] [npc2.tail+].",

					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(focus)], контролируя дополнительные мышцы, выстилающие внутреннюю часть [npc.her] [npc.crotchNipple(true)]. Сжимая и обхватывая их вокруг [npc2.namePos] [npc2.tail+], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик удовольствия.",

					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)], сжимая дополнительные мышцы внутри [npc.her] [npc.crotchNipple+] вокруг [npc2.namePos] [npc2.tail+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(focus)], контролируя дополнительные мышцы глубоко внутри [npc.her] [npc.crotchNipple(true)], сжимая их и массируя [npc2.namePos] [npc2.tail+], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
