package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerBreasts {
	
	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Тискать грудь";
		}

		@Override
		public String getActionDescription() {
			return "Сжать [npc2.namePos] [npc2.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()
					&& Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.HANDS)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руки к груди [npc2.namePos], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(start)] ласкает и тискает [npc2.namePos] [npc2.breastRows] [npc2.breasts+], мягко прижимая [npc2.her] [npc2.lowClothing(NIPPLES)] к [npc2.her] [npc2.nipples+] в процессе.",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы нежно прижать [npc.her] [npc.hands+] к [npc2.her] [npc2.topClothing(NIPPLES)], прежде чем начать мягко тискать и сжимать грудь [npc2.her].",

								"Дразня [npc.her] [npc.fingers] через [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)], чтобы нежно ласкать и тискать [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руки к груди [npc2.namePos], [npc.name] [npc.verb(let)] нежный [npc.a_moan+], пока [npc.she] с жадностью [npc.verb(start)] ласкает и тискает [npc2.her] [npc2.breastRows] [npc2.breasts+], прижимая [npc2.her] [npc2.lowClothing(NIPPLES)] к [npc2.her] [npc2.nipples+] в процессе.",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы с жадностью прижать [npc.her] [npc.hands+] к [npc2.her] [npc2.topClothing(NIPPLES)], прежде чем начать тискать и сжимать грудь [npc2.her].",

								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)], чтобы с жадностью ласкать и тискать [npc2.her] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руку к груди [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ласкает и тискает [npc2.her] [npc2.breastRows] [npc2.breasts+], с силой прижимая [npc2.her] [npc2.lowClothing(NIPPLES)] к [npc2.her] [npc2.nipples+] в процессе.",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы с силой прижать [npc.her] [npc.hands+] к [npc2.her] [npc2.topClothing(NIPPLES)], прежде чем начать грубо тискать и сжимать грудь [npc2.her].",

								"Втискивая [npc.her] [npc.fingers] в [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)], чтобы грубо ласкать и тискать [npc2.her] [npc2.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руки к груди [npc2.namePos], [npc.name] [npc.verb(let)] нежный [npc.a_moan+], пока [npc.she] с жадностью [npc.verb(start)] ласкает и тискает [npc2.her] [npc2.breastRows] [npc2.breasts+], прижимая [npc2.her] [npc2.lowClothing(NIPPLES)] к [npc2.her] [npc2.nipples+] в процессе.",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы с жадностью прижать [npc.her] [npc.hands+] к [npc2.namePos] [npc2.topClothing(NIPPLES)], прежде чем начать тискать и сжимать грудь [npc2.her].",

								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.topClothing(NIPPLES)], [npc.name] [npc.verb(start)], чтобы с жадностью ласкать и тискать [npc2.her] [npc2.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку к груди [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] ласкает и ощупывает [npc2.her] [npc2.breastRows] [npc2.breasts+], прижимая [npc2.her] [npc2.lowClothing(соски)] к [npc2.her] [npc2.nipples+] в процессе.",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)] вверх, чтобы прижать [npc.her] [npc.hands+] к [npc2.her] [npc2.topClothing(соски)], прежде чем начать ощупывать и сжимать грудь [npc2.her].",

								"Дразня [npc.her] [npc.fingers] поверх [npc2.namePos] [npc2.topClothing(соски)], [npc.name] [npc.verb(start)] ласкать и ощупывать [npc2.her] [npc2.breasts+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] издает мягкий [npc2.moan] при [npc.namePos] прикосновении, прежде чем нежно побудить [npc.herHim] продолжать уделять [npc2.her] [npc2.breasts+] [npc.her] все свое внимание.",
	
									" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] грудь [npc2.her] наружу, умоляя [npc.name] продолжать.",
	
									" Тихо [npc2.moaning] при прикосновении [npc.namePos], [npc2.name] нежно [npc2.verb(encourage)] [npc.herHim] продолжать играть с [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] при [npc.namePos] прикосновении, прежде чем с нетерпением подгонять [npc.herHim] продолжать дарить [npc2.her] [npc2.breasts+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)], выпятив [npc2.her] грудь и умоляя [npc.name] продолжить.",
	
									" [npc2.Moaning+] при [npc.namePos] прикосновении, [npc2.name] с нетерпением [npc2.verb(encourage)], прося [npc.herHim] не прекращать игры с [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)], когда [npc2.a_moan+] касается [npc.namePos], после чего грубо рычит, требуя от [npc.herHim] продолжать уделять [npc2.her] [npc2.breasts+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выпятив [npc2.her] грудь, и твердым тоном [npc2.she] [npc2.verb(order)], чтобы [npc.name] продолжал уделять [npc2.her] [npc2.breasts+] [npc.her] всё своё внимание.",
	
									" Издавая [npc2.a_moan+] при [npc.namePos] прикосновении, [npc2.name] [npc2.verb(demand)], что [npc.she] [npc.verb(carry)] в игре с [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] при [npc.namePos] прикосновении, прежде чем с нетерпением подгонять [npc.herHim] продолжать дарить [npc2.her] [npc2.breasts+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)], выпятив [npc2.her] грудь и умоляя [npc.name] продолжить.",
	
									" [npc2.Moaning+] при [npc.namePos] прикосновении, [npc2.name] с нетерпением [npc2.verb(encourage)], прося [npc.herHim] не прекращать игры с [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] при [npc2.a_moan+] прикосновении [npc.namePos], после чего подбадривает [npc.herHim] продолжать уделять [npc2.her] [npc2.breasts+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выпятив [npc2.her] грудь и умоляя [npc.name] продолжить.",
	
									" [npc2.Moaning+] при прикосновении [npc.namePos], [npc2.name] [npc2.verb(encourage)] [npc.herHim], чтобы продолжить ласкать [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] инстинктивно [npc2.verb(try)], пытаясь отстраниться, [npc2.sobbing] и сопротивляясь [npc.namePos] прикосновению, пока [npc2.she] [npc2.verb(try)] оттолкнуть [npc.her] [npc.fingers] от [npc2.her] [npc2.breasts+].",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] корчится от дискомфорта, умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc.verb(continue)] играет с [npc2.namePos] [npc2.breasts+].",
	
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] от прикосновения [npc.namePos], и пока [npc.she] [npc.verb(carry)] играет с [npc2.her] [npc2.breasts+], [npc2.name] [npc2.verb(continue)] сопротивляться [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc2.milk] вытекает на [npc2.namePos] [npc2.lowClothing(соски)], пока [npc.name] [npc.verb(squeeze)] на [npc2.her] [npc2.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая струя [npc2.milk] вырывается на [npc2.namePos] [npc2.lowClothing(соски)], пока [npc.name] [npc.verb(squeeze)] на [npc2.her] [npc2.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Струйка [npc2.milk] стекает на [npc2.namePos] [npc2.lowClothing(соски)], пока [npc.name] [npc.verb(squeeze)] на [npc2.her] [npc2.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], стекая в [npc2.her] [npc2.lowClothing(NIPPLES)], и [npc2.she] [npc2.moanVerb], пока [npc2.she] [npc2.verb(feel)] по [npc2.her] [npc2.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] тонкой струйкой стекает в [npc2.her] [npc2.lowClothing(NIPPLES)], пока [npc.name] [npc.verb(squeeze)] по [npc2.her] [npc2.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] непрерывной струей, быстро пропитывая [npc2.her] [npc2.lowClothing(NIPPLES)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] густым потоком, быстро пропитывая [npc2.her] [npc2.lowClothing(NIPPLES)].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руку к [npc2.namePos] груди, [npc.name] [npc.verb(let)] мягкий [npc.moan], пока [npc.she] [npc.verb(start)] нежно ласкает и ощупывает [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы начать нежно ощупывать и сжимать [npc2.her] обнаженную грудь.",

								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] обнаженной грудью, [npc.name] [npc.verb(start)] нежно ласкать и ощупывать [npc2.namePos] [npc2.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку к груди [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением ласкает и ощупывает [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы с жадностью начать ощупывать и сжимать обнаженную грудь [npc2.her].",

								"Дразня [npc.her] [npc.fingers] над обнаженной грудью [npc2.namePos], [npc.name] [npc.verb(start)], чтобы с нетерпением ласкать и ощупывать [npc2.namePos] [npc2.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку к груди [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ласкает и ощупывает [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы грубо начать ощупывать и сжимать обнаженную грудь [npc2.her].",

								"Вжимая [npc.her] [npc.fingers] в обнаженную грудь [npc2.namePos], [npc.name] [npc.verb(start)], чтобы грубо ласкать и ощупывать [npc2.namePos] [npc2.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку к груди [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением ласкает и ощупывает [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], чтобы с жадностью начать ощупывать и сжимать обнаженную грудь [npc2.her].",

								"Дразня [npc.her] [npc.fingers] над обнаженной грудью [npc2.namePos], [npc.name] [npc.verb(start)], чтобы с нетерпением ласкать и ощупывать [npc2.namePos] [npc2.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протянув руку к груди [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] ласкает и ощупывает [npc2.her] [npc2.breastRows] [npc2.breasts+].",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.breasts+], и [npc.she] [npc.verb(reach)], начиная ощупывать и сжимать обнаженную грудь [npc2.her].",

								"Дразня [npc.her] [npc.fingers] над обнаженной грудью [npc2.namePos], [npc.name] [npc.verb(start)] ласкать и ощупывать [npc2.namePos] [npc2.breasts+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] out a soft [npc2.moan] in response to [npc.namePos] touch, before gently encouraging [npc.herHim] to continue giving [npc2.her] [npc2.breasts+] [npc.her] full attention.",
	
									" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] грудь [npc2.her] наружу, умоляя [npc.name] продолжать.",
	
									" Тихо [npc2.moaning] при прикосновении [npc.namePos], [npc2.name] нежно [npc2.verb(encourage)] [npc.herHim] продолжать играть с [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из-за [npc2.a_moan+] в ответ на прикосновение [npc.namePos], прежде чем с нетерпением подтолкнуть [npc.herHim] продолжить дарить [npc2.her] [npc2.breasts+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] грудь [npc2.her], умоляя [npc.herHim] продолжать.",
	
									" [npc2.Moaning+] при [npc.namePos] прикосновении, [npc2.name] с нетерпением [npc2.verb(encourage)], прося [npc.herHim] не прекращать игры с [npc2.her] [npc2.breasts+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на прикосновение [npc.namePos], после чего грубо рычит, чтобы [npc.herHim] продолжал(а) уделять [npc2.namePos] [npc2.breasts+] все свое [npc.her] внимание.",
	
									" [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выпятив [npc2.her] грудь, твердым тоном [npc2.name] приказывает [npc.herHim] продолжать, прежде чем снова начать издавать похотливые звуки.",
	
									" Издавая [npc2.a_moan+] при [npc.namePos] прикосновении, [npc2.name] [npc2.verb(demand)], что [npc.she] [npc.verb(carry)] в игре с [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из-за [npc2.a_moan+] в ответ на прикосновение [npc.namePos], прежде чем с нетерпением подтолкнуть [npc.herHim] продолжить дарить [npc2.her] [npc2.breasts+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] грудь [npc2.her], умоляя [npc.herHim] продолжать.",
	
									" [npc2.Moaning+] при [npc.namePos] прикосновении, [npc2.name] с нетерпением [npc2.verb(encourage)], прося [npc.herHim] не прекращать игры с [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)], выставляя [npc2.a_moan+] в ответ на прикосновение [npc.namePos], подзадоривая [npc.herHim] продолжать, уделяя [npc2.her] всё свое [npc2.breasts+] [npc.her] внимание.",
	
									" [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выпятив [npc2.her] грудь, умоляя [npc.herHim] продолжать.",
	
									" [npc2.Moaning+] при прикосновении [npc.namePos], [npc2.name] [npc2.verb(encourage)] [npc.herHim], чтобы продолжить ласкать [npc2.her] [npc2.breasts+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] инстинктивно [npc2.verb(try)], пытаясь отстраниться, [npc2.sobbing] и борясь с прикосновением [npc.namePos], пока [npc2.she] [npc2.verb(try)] сбивать [npc.namePos] [npc.fingers] от [npc2.her] [npc2.breasts+].",
	
									" [npc2.a_sob+], [npc2.name] [npc2.verb(start)], корчась от дискомфорта и умоляя [npc.namePos] оставить [npc2.herHim] в покое, пока [npc.she] [npc.verb(continue)] играет с [npc2.her] [npc2.breasts+].",
	
									" [npc2.A_sob+] вырывается из меж [npc2.namePos] [npc2.lips+] в ответ на прикосновение [npc.namePos], и пока [npc.she] [npc.verb(carry)] продолжает играть с [npc2.her] [npc2.breasts+], [npc2.name] [npc2.verb(continue)] сопротивляться [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc2.milk] вытекает на [npc.namePos] [npc.fingers], пока [npc.she] [npc.verb(squeeze)] на [npc2.namePos] [npc2.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая струйка [npc2.milk] вытекает на [npc.namePos] [npc.fingers], пока [npc.she] [npc.verb(squeeze)] на [npc2.namePos] [npc2.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc2.milk] стекает по [npc.namePos] [npc.fingers], пока [npc.she] [npc.verb(squeeze)] на [npc2.namePos] [npc2.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] начинает вытекать по [npc.namePos] [npc.fingers], и [npc2.name] [npc2.moanVerb], пока [npc2.she] [npc2.verb(feel)] её по [npc2.her] [npc2.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] тонкой струйкой стекает по [npc.namePos] [npc.fingers], пока [npc.she] сжимает [npc2.her] [npc2.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] льется непрерывным потоком, быстро пропитывая [npc2.her] [npc2.breasts+] и капая на пол под [npc2.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)] льется густым потоком, быстро пропитывая [npc2.her] [npc2.breasts+] и стекая на пол под [npc2.herHim], образуя большую лужу.");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES)
					&& Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
				Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(Main.sex.getCharacterTargetedForSexAction(this), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction FORCE_FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дать ощупать грудь";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] хорошенько сжать твои [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreasts()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.HANDS)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue();
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Захватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.her] груди, издавая тихий [npc.moan], пока [npc.she] [npc.verb(press)] их в [npc.her] [npc.breasts+], при этом [npc.her] [npc.lowClothing(NIPPLES)] прижимаются к [npc.her] [npc.nipples+].",

								"[npc.Name] [npc.verb(take)] захватывает [npc2.namePos] [npc2.hands], прежде чем направить их вверх, чтобы мягко прижать к [npc.her] [npc.topClothing(NIPPLES)], и удерживает их там на мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim] ощупывать и сжимать [npc.namePos] [npc.breasts].",

								"Захватив [npc2.namePos] [npc2.hands], [npc.name] осторожно [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх к [npc.her] [npc.topClothing(NIPPLES)], а затем мягко прижимая их к [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вверх к [npc.her] груди, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(press)] их в [npc.her] [npc.breasts+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES)] прижаться к [npc.her] [npc.nipples+].",

								"[npc.Name] [npc.verb(take)] захватывает [npc2.namePos] [npc2.hands], прежде чем с нетерпением направить их вверх, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES)], и удерживает их там на мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim] ощупывать и сжимать [npc.namePos] [npc.breasts].",

								"Захватив [npc2.namePos] [npc2.hands], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх к [npc.her] [npc.topClothing(NIPPLES)], а затем с энтузиазмом прижимая их к [npc.her] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] резко дергает их вверх к [npc.her] груди, издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(press)] их в [npc.her] [npc.breasts+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES)] вжаться в [npc.her] [npc.nipples+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], прежде чем резко дернуть их вверх, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES)], и удерживает их там на мгновение, пока [npc.name] приказывает [npc2.herHim] ощупывать и сжимать [npc.namePos] [npc.breasts].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] яростно [npc.verb(pull)] их вверх к [npc.her] груди, заставляя [npc2.her] [npc2.fingers] прижаться к [npc.topClothing(NIPPLES)], прикрывающему [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вверх к [npc.her] груди, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(press)] их в [npc.her] [npc.breasts+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES)] прижаться к [npc.her] [npc.nipples+].",

								"[npc.Name] [npc.verb(take)] захватывает [npc2.namePos] [npc2.hands], прежде чем с нетерпением направить их вверх, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES)], и удерживает их там на мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim] ощупывать и сжимать [npc.namePos] [npc.breasts].",

								"Захватив [npc2.namePos] [npc2.hands], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх к [npc.her] [npc.topClothing(NIPPLES)], а затем с энтузиазмом прижимая их к [npc.her] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вверх к [npc.her] груди, издавая [npc.a_moan+], пока [npc.she] [npc.verb(press)] их в [npc.her] [npc.breasts+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES)] вжаться в [npc.her] [npc.nipples+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], прежде чем направить их вверх, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES)], и удерживает их там на мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim] ощупывать и сжимать [npc.namePos] [npc.breasts].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] до [npc.her] [npc.topClothing(NIPPLES)], прежде чем вжать их в [npc.her] [npc.breasts+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan] в ответ на нетерпение [npc.namePos], прежде чем мягко вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+].",
	
									" С тихим [npc2.moan], [npc2.name] с нетерпением отвечает на движение [npc.namePos], мягко погружая [npc2.her] [npc2.fingers] в нежную плоть [npc.her] [npc.breasts].",
	
									" Мягко [npc2.moaning], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], извлекая приятные [npc.moans] из складок [npc.her] [npc.lips], пока [npc2.she] нежно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+] в ответ на нетерпение [npc.namePos], прежде чем жадно вдавить [npc2.her] [npc2.hands] в плоть [npc.namePos] [npc.breasts+].",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в нежную плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из меж[npc.her] [npc.lips], пока [npc2.she] жадно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на нетерпение [npc.namePos], грубо вдавливая [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+], пока [npc2.she] рычит, что [npc2.sheIs] всё ещё здесь главный.",
	
									" С [npc2.a_moan+], [npc2.name] жадно отвечает на движение [npc.namePos], грубо вжимая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из меж[npc.her] [npc.lips], пока [npc2.she] грубо [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на нетерпение [npc.namePos], прежде чем жадно вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+].",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в нежную плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из меж[npc.her] [npc.lips], пока [npc2.she] жадно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из-за [npc2.a_moan+] в ответ на [npc.namePos] нетерпение, прежде чем вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+].",
	
									" С [npc2.a_moan+], [npc2.name] отвечает на движение [npc.namePos], погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(try)] отпрянуть, [npc2.sobbing] и борясь против прикосновений [npc.namePos], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.hands] в [npc.her] [npc.breasts+].",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] корчится от дискомфорта, умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc2.verb(continue)] втискивает [npc2.namePos] [npc2.hands] в [npc.her] [npc.breasts+].",
	
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], и пока [npc.name] [npc.verb(carry)] продолжает втискивать [npc2.her] [npc2.hands] в [npc.her] [npc.breasts+], [npc2.she] [npc2.verb(continue)] сопротивляться прикосновениям [npc.her]."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] вытекает на [npc.namePos] [npc.lowClothing(соски)], пока [npc2.name] сжимает [npc.her] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая струйка [npc.milk] вытекает на [npc.namePos] [npc.lowClothing(NIPPLES)], пока [npc2.name] сжимает [npc.her] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] стекает на [npc.namePos] [npc.lowClothing(NIPPLES)], пока [npc2.name] сжимает [npc.her] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает вытекать на [npc.her] [npc.lowClothing(NIPPLES)], и [npc.she] [npc.moanVerb], пока [npc.she] [npc.verb(feel)] она стекает по [npc.her] [npc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает сочиться тонкой струйкой на [npc.her] [npc.lowClothing(NIPPLES)], пока [npc2.name] сжимает [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает литься непрерывным потоком, быстро пропитывая [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает литься густым потоком, быстро пропитывая [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Захватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.namePos] груди, издавая тихий [npc.moan], пока [npc.name] [npc.verb(press)] их в [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] берет [npc2.namePos] [npc2.hands], направляя их вверх, чтобы нежно [npc.verb(press)] в мягкую плоть [npc.her] [npc.breasts].",

								"Захватив [npc2.namePos] [npc2.hands], [npc.name] осторожно [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх, чтобы мягко [npc.verb(press)] в [npc.her] [npc.breasts+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Сжимая [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.namePos] груди, издавая [npc.a_moan+], пока [npc.name] с жадностью [npc.verb(press)] их в [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], с нетерпением направляя их вверх, чтобы прижать к мягкой плоти [npc.her] [npc.breasts].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] с жадностью [npc.verb(guide)] [npc2.her] [npc2.fingers] к [npc.her] [npc.breasts+], с энтузиазмом прижимая их."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Сжимая [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] резко дергает их вверх к [npc.namePos] груди, издавая [npc.a_moan+], пока [npc.name] грубо [npc.verb(press)] их в [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], грубо дергая их вверх, чтобы прижать к мягкой плоти [npc.her] [npc.breasts].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] яростно [npc.verb(pull)] их к [npc.namePos] груди, заставляя [npc2.her] [npc2.fingers] прижаться к мягкой плоти [npc.her] [npc.breasts+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Сжимая [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.namePos] груди, издавая [npc.a_moan+], пока [npc.name] с жадностью [npc.verb(press)] их в [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], с нетерпением направляя их вверх, чтобы прижать к мягкой плоти [npc.her] [npc.breasts].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] с жадностью [npc.verb(guide)] [npc2.her] [npc2.fingers] к [npc.her] [npc.breasts+], с энтузиазмом прижимая их."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.namePos] груди, издавая [npc.a_moan+], пока [npc.name] [npc.verb(press)] их в [npc.her] [npc.breasts+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], направляя их вверх, чтобы прижать к мягкой плоти [npc.her] [npc.breasts].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх, чтобы прижать их к [npc.her] [npc.breasts+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan] в ответ на нетерпение [npc.namePos], прежде чем мягко вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+].",
	
									" С тихим [npc2.moan], [npc2.name] с нетерпением отвечает на движение [npc.namePos], мягко погружая [npc2.her] [npc2.fingers] в нежную плоть [npc.her] [npc.breasts].",
	
									" Мягко [npc2.moaning], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], извлекая приятные [npc.moans] из складок [npc.her] [npc.lips], пока [npc2.she] нежно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на нетерпение [npc.namePos], прежде чем жадно вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+].",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в нежную плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из меж[npc.her] [npc.lips], пока [npc2.she] жадно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на нетерпение [npc.namePos], грубо вдавливая [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+], пока [npc2.she] рычит, что [npc2.sheIs] всё ещё здесь главный.",
	
									" С [npc2.a_moan+], [npc2.name] жадно отвечает на движение [npc.namePos], грубо вжимая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из меж[npc.her] [npc.lips], пока [npc2.she] грубо [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на нетерпение [npc.namePos], прежде чем жадно вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+].",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в нежную плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из меж[npc.her] [npc.lips], пока [npc2.she] жадно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] из-за [npc2.a_moan+] в ответ на [npc.namePos] нетерпение, прежде чем вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.breasts+].",
	
									" С [npc2.a_moan+], [npc2.name] отвечает на движение [npc.namePos], погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.breasts].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.breasts+], вырывая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.breasts]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] пытается отстраниться, [npc2.sobbing] и борясь с прикосновением [npc.namePos], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.hands] в [npc.her] [npc.breasts+].",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] корчится от дискомфорта, умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc2.verb(continue)] втискивает [npc2.her] [npc2.hands] в [npc.her] [npc.breasts+].",
	
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], и пока [npc.name] [npc.verb(carry)] продолжает втискивать [npc2.her] [npc2.hands] в [npc.her] [npc.breasts+], [npc2.namePos] [npc2.verb(continue)] сопротивляться прикосновению [npc.her]."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] вытекает на кончики пальцев [npc2.namePos], пока [npc2.she] сжимает [npc.namePos] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая порция [npc.milk] выплескивается на кончики пальцев [npc2.namePos], пока [npc2.she] сжимает [npc.namePos] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] стекает по кончикам пальцев [npc2.namePos], пока [npc2.she] сжимает [npc.namePos] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает стекать по [npc2.namePos] пальцам, и [npc.she] [npc.moanVerb], пока [npc.she] [npc.verb(feel)] оно струится по [npc.her] [npc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает вытекать тонкой струйкой по пальцам [npc2.namePos], пока [npc2.she] сжимает [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает литься непрерывным потоком, быстро пропитывая [npc.her] грудь и капая на пол под [npc.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.milk] начинает литься густым потоком, быстро пропитывая [npc.her] грудь и стекая на пол, образуя под [npc.herHim] большую лужу.");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)
					&& Main.sex.getCharacterPerformingAction().getBreastStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-10);
		}
		
	};
}
