package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
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
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class FingerBreastsCrotch {

	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Тискать вымя";
			} else {
				return "Тискать лобки-сиськи";
			}
		}

		@Override
		public String getActionDescription() {
			return "Сжать [npc2.namePos] [npc2.crotchBoobs+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] мягкую [npc.moan], пока [npc.she] [npc.verb(start)] ласкает и тискает [npc2.namePos] [npc2.crotchBoobsRows] [npc2.crotchBoobs+], при этом мягко прижимая [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] к [npc2.her] [npc2.crotchNipples+].",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)], чтобы нежно прижать [npc.her] [npc.hands+] к [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], прежде чем начать мягко тискать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] поверх [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)], чтобы нежно ласкать и тискать [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] мягкую [npc.a_moan+], пока [npc.she] [npc.verb(start)] с жадностью ласкает и тискает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+], при этом прижимая [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] к [npc2.her] [npc2.crotchNipples+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы с жадностью прижать [npc.her] [npc.hands+] к [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], прежде чем приняться ощупывать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] поверх [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] с жадностью ласкать и тискать [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] достает [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ласкает и тискает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+], при этом с силой втирая [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] в [npc2.her] [npc2.crotchNipples+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы с силой втирать [npc.her] [npc.hands+] в [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], прежде чем приняться грубо ощупывать и сжимать [npc2.her] низ живота.",

								"Втискивая [npc.her] [npc.fingers] в [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] с грубой страстью ласкать и тискать [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] мягкую [npc.a_moan+], пока [npc.she] [npc.verb(start)] с жадностью ласкает и тискает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+], при этом прижимая [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] к [npc2.her] [npc2.crotchNipples+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы жадно прижать [npc.her] [npc.hands+] к [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], прежде чем начать ощупывать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] поверх [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] с жадностью ласкать и тискать [npc2.her] [npc2.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] ласкает и ощупывает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+], прижимая [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)] к [npc2.her] [npc2.crotchNipples+] в процессе.",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы прижать [npc.her] [npc.hands+] к [npc2.her] [npc2.topClothing(NIPPLES_CROTCH)], прежде чем начать ощупывать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] поверх [npc2.namePos] [npc2.topClothing(NIPPLES_CROTCH)], [npc.name] [npc.verb(start)] ласкать и ощупывать [npc2.her] [npc2.crotchBoobs+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] издает тихий [npc2.moan] при [npc.namePos] прикосновении, прежде чем мягко побудить [npc.herHim] продолжить, отдавая [npc2.her] [npc2.crotchBoobs+] все свое [npc.her] внимание.",
	
									" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] [npc2.her] живот, умоляя [npc.name] продолжить.",
	
									" Мягко [npc2.moaning] при прикосновении [npc.namePos], [npc2.name] нежно [npc2.verb(encourage)] [npc.herHim], чтобы та продолжала играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] на прикосновение [npc2.a_moan+] при [npc.namePos], прежде чем с нетерпением подгонять [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchBoobs+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] живот, умоляя [npc.name] продолжить.",
	
									" [npc2.Moaning+] при прикосновении [npc.namePos], [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.herHim], чтобы та продолжала играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] на прикосновение [npc2.a_moan+] при [npc.namePos], прежде чем грубо прорычать, чтобы [npc.herHim] продолжала уделять [npc2.her] [npc2.crotchBoobs+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] живот, и твердым тоном, [npc2.she] [npc2.verb(order)] [npc.name] продолжать уделять [npc2.her] [npc2.crotchBoobs+] [npc.her] всё своё внимание.",
	
									" Издавая [npc2.a_moan+] при прикосновении [npc.namePos], [npc2.name] [npc2.verb(demand)], что [npc.she] [npc.verb(carry)] в игре с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] на прикосновение [npc2.a_moan+] при [npc.namePos], прежде чем с нетерпением подгонять [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchBoobs+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] живот, умоляя [npc.name] продолжить.",
	
									" [npc2.Moaning+] при прикосновении [npc.namePos], [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.herHim], чтобы та продолжала играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] при [npc.namePos] касании, прежде чем подбадривать [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchBoobs+] все свое [npc.her] внимание.",
	
									" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] выпятив [npc2.her] живот, умоляя [npc.name] продолжать.",
	
									" [npc2.Moaning+] при [npc.namePos] касании, [npc2.name] [npc2.verb(encourage)] [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] инстинктивно [npc2.verb(try)] отпрянуть, [npc2.sobbing] и сопротивляясь [npc.namePos] касанию, пока [npc2.she] [npc2.verb(try)] оттолкнуть [npc.her] [npc.fingers] от [npc2.her] [npc2.crotchBoobs+].",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] корчась от дискомфорта, умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc.verb(continue)] играет с [npc2.namePos] [npc2.crotchBoobs+].",
	
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] при [npc.namePos] касании, и пока [npc.she] [npc.verb(carry)] продолжает играть с [npc2.her] [npc2.crotchBoobs+], [npc2.name] [npc2.verb(continue)] сопротивляться [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc2.crotchMilk] вытекает в [npc2.namePos] [npc2.lowClothing(NIPPLES_CROTCH)], пока [npc.name] сжимает [npc2.her] [npc2.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая струйка [npc2.crotchMilk] вытекает в [npc2.namePos] [npc2.lowClothing(NIPPLES_CROTCH)], пока [npc.name] сжимает [npc2.her] [npc2.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc2.crotchMilk] стекает в [npc2.namePos] [npc2.lowClothing(NIPPLES_CROTCH)], пока [npc.name] сжимает [npc2.her] [npc2.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)], вытекая в [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)], а [npc2.she] [npc2.moanVerb], пока [npc2.she] [npc2.verb(feel)] ее по [npc2.her] [npc2.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] тонкой струйкой, стекающей в [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)], пока [npc.name] [npc.verb(squeeze)] на [npc2.her] [npc2.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] непрерывной струей, быстро пропитывая [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] густым потоком, быстро пропитывая [npc2.her] [npc2.lowClothing(NIPPLES_CROTCH)].");
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
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] мягкое [npc.moan], пока [npc.she] [npc.verb(start)] нежно лаская и ощупывая [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы нежно начать ощупывать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] низ живота, [npc.name] [npc.verb(start)] нежно ласкать и ощупывать [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно ласкает и ощупывает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы жадно начать ощупывать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] низ живота, [npc.name] [npc.verb(start)] жадно ласкать и ощупывать [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ласкает и ощупывает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы грубо начать ощупывать и сжимать [npc2.her] низ живота.",

								"Погружая [npc.her] [npc.fingers] в [npc2.namePos] низ живота, [npc.name] [npc.verb(start)], чтобы грубо ласкать и тискать [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно ласкает и ощупывает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself] не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)] вниз, чтобы жадно начать ощупывать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] низ живота, [npc.name] [npc.verb(start)] жадно ласкать и ощупывать [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] ласкает и тискает [npc2.her] [npc2.crotchBoobsRows] [npc2.crotchBoobs+].",

								"[npc.Name] [npc.verb(find)] [npc.herself], не в силах устоять перед искушением [npc2.namePos] [npc2.crotchBoobs+], и [npc.she] [npc.verb(reach)], начиная ласкать и сжимать [npc2.her] низ живота.",

								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] низом живота, [npc.name] [npc.verb(start)], чтобы ласкать и тискать [npc2.namePos] [npc2.crotchBoobs+]."));
						break;
					default:
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan] в ответ на прикосновение [npc.namePos], прежде чем нежно подталкивать [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchBoobs+] [npc.her] всё своё внимание.",
	
									" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] [npc2.her] живот, умоляя [npc.name] продолжить.",
	
									" Мягко [npc2.moaning] при прикосновении [npc.namePos], [npc2.name] нежно [npc2.verb(encourage)] [npc.herHim], чтобы та продолжала играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на прикосновение [npc.namePos], прежде чем жадно подталкивать [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchBoobs+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] живот, умоляя [npc.herHim] продолжать.",
	
									" [npc2.Moaning+] при прикосновении [npc.namePos], [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.herHim], чтобы та продолжала играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на прикосновение [npc.namePos], после чего грубо зарычав, требует от [npc.herHim] продолжать уделять [npc2.namePos] [npc2.crotchBoobs+] все свое [npc.her] внимание.",
	
									" При [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выставив [npc2.her] живот, и твердым тоном [npc2.name] приказывает [npc.herHim] продолжать, прежде чем снова издавать похотливые звуки.",
	
									" Издавая [npc2.a_moan+] при прикосновении [npc.namePos], [npc2.name] [npc2.verb(demand)], что [npc.she] [npc.verb(carry)] в игре с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на прикосновение [npc.namePos], прежде чем жадно подталкивать [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchBoobs+] [npc.her] всё своё внимание.",
	
									" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] живот, умоляя [npc.herHim] продолжать.",
	
									" [npc2.Moaning+] при прикосновении [npc.namePos], [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.herHim], чтобы та продолжала играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на прикосновение [npc.namePos], подбадривая [npc.herHim] продолжать уделять [npc2.her] все свое [npc2.crotchBoobs+] [npc.her] внимание.",
	
									" При [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выставив [npc2.her] живот, умоляя [npc.herHim] продолжать.",
	
									" [npc2.Moaning+] при [npc.namePos] касании, [npc2.name] [npc2.verb(encourage)] [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchBoobs+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] инстинктивно [npc2.verb(try)], пытаясь отстраниться, [npc2.sobbing] и борясь с прикосновением [npc.namePos], пока [npc2.she] [npc2.verb(try)] оттолкнуть [npc.namePos] [npc.fingers] от [npc2.her] [npc2.crotchBoobs+].",
	
									" При [npc2.a_sob+], [npc2.name] [npc2.verb(start)], корчась от дискомфорта, умоляя [npc.namePos] оставить [npc2.herHim] в покое, пока [npc.she] [npc.verb(continue)] играть с [npc2.her] [npc2.crotchBoobs+].",
	
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] в ответ на прикосновение [npc.namePos], и пока [npc.she] [npc.verb(carry)] играть с [npc2.her] [npc2.crotchBoobs+], [npc2.name] [npc2.verb(continue)] сопротивляться [npc.herHim]."));
							break;
						default:
							break;
					}
				}
				switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc2.crotchMilk] вытекает на кончики пальцев [npc.namePos], пока [npc.she] сжимает [npc2.namePos] [npc2.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая порция [npc2.crotchMilk] выплескивается на кончики пальцев [npc.namePos], пока [npc.she] сжимает [npc2.namePos] [npc2.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Струйка [npc2.crotchMilk] стекает по кончикам пальцев [npc.namePos], пока [npc.she] сжимает [npc2.namePos] [npc2.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] начинает вытекать сквозь пальцы [npc.namePos], и [npc2.name] [npc2.moanVerb], пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] вытекает тонкой струйкой по пальцам [npc.namePos], пока [npc.she] сжимает [npc2.her] [npc2.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] льется непрерывной струей, быстро пропитывая [npc2.her] [npc2.crotchBoobs+] и капая на пол под [npc2.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.crotchMilk] [npc.verb(start)] хлещет мощным потоком, быстро пропитывая [npc2.her] [npc2.crotchBoobs+] и стекая на пол под [npc2.herHim], образуя там большую лужу.");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)
					&& Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH)!=null) {
				Main.sex.getCharacterTargetedForSexAction(this).getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).setDirty(Main.sex.getCharacterTargetedForSexAction(this), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction FORCE_FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.BREAST_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Позволить ощупать вымя";
			} else {
				return "Позволить ощупать лобковые груди";
			}
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] хорошенько сжать твои [npc.crotchBoobs+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch()
					&& Main.sex.getCharacterPerformingAction().getLegConfiguration().isBipedalPositionedCrotchBoobs()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue();
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.her] нижней части живота, она издает тихий [npc.moan], пока [npc.she] [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] вжиматься в [npc.her] [npc.crotchNipples+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], прежде чем направить их вниз, чтобы мягко прижать к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], и удерживать их там мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim], чтобы ощупать и сжать [npc.namePos] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] осторожно [npc.verb(guide)] [npc2.her] [npc2.fingers] к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], а затем мягко прижимая их к [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.her] нижней части живота, она издает [npc.a_moan+], пока [npc.she] с жадностью [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] вжиматься в [npc.her] [npc.crotchNipples+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], а затем жадно направляет их вниз, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], и удерживает их там на мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim], чтобы ощупывать и сжимать [npc.namePos] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], а затем с энтузиазмом прижимает их к [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] резко дергает их вниз к [npc.her] нижней части живота, издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+], попутно с силой прижимая [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] к [npc.her] [npc.crotchNipples+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], а затем грубо дергает их вниз, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], и удерживает их там на мгновение, пока [npc.name] приказывает [npc2.herHim] ощупывать и сжимать [npc.namePos] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] яростно [npc.verb(pull)] их вниз к [npc.her] нижней части живота, заставляя [npc2.her] [npc2.fingers] прижаться к [npc.topClothing(NIPPLES_CROTCH)], который прикрывает [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их к [npc.her] нижней части живота, она издает [npc.a_moan+], пока [npc.she] с жадностью [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] вжиматься в [npc.her] [npc.crotchNipples+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], а затем жадно направляет их вниз, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], и удерживает их там на мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim], чтобы ощупывать и сжимать [npc.namePos] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], а затем с энтузиазмом прижимает их к [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вниз к [npc.her] нижней части живота, издавая [npc.a_moan+], пока [npc.she] [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+], при этом заставляя [npc.her] [npc.lowClothing(NIPPLES_CROTCH)] прижаться к [npc.her] [npc.crotchNipples+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], прежде чем направить их вниз, чтобы прижать к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], и удерживает их там на мгновение, пока [npc.name] [npc.verb(encourage)] [npc2.herHim], чтобы ощупывать и сжимать [npc.namePos] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз к [npc.her] [npc.topClothing(NIPPLES_CROTCH)], прежде чем прижать их к [npc.her] [npc.crotchBoobs+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихое [npc2.moan] в ответ на жадность [npc.namePos], прежде чем мягко вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.crotchBoobs+].",
	
									" С мягким [npc2.moan], [npc2.name] с нетерпением отвечает на движение [npc.namePos], плавно погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" Мягко [npc2.moaning], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], извлекая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] нежно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] в ответ на жадность [npc.namePos], прежде чем с нетерпением прижать [npc2.her] [npc2.hands] к плоти [npc.namePos] [npc.crotchBoobs+].",
	
									" С [npc2.a_moan+], [npc2.name] с жадностью отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], извлекая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] с жадностью [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] в ответ на жадность [npc.namePos], грубо прижимая [npc2.her] [npc2.hands] к плоти [npc.her] [npc.crotchBoobs+], пока [npc2.she] рычит, что [npc2.sheIs] всё еще здесь главный.",
	
									" С [npc2.a_moan+], [npc2.name] с жадностью отвечает на движение [npc.namePos], грубо погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], вызывая приятные [npc.moans] между [npc.her] [npc.lips], пока [npc2.she] грубо [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на жадность [npc.namePos], прежде чем с нетерпением прижать [npc2.her] [npc2.hands] к плоти [npc.her] [npc.crotchBoobs+].",
	
									" С [npc2.a_moan+], [npc2.name] с жадностью отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], извлекая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] с жадностью [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на жадность [npc.namePos], прежде чем прижать [npc2.her] [npc2.hands] к плоти [npc.her] [npc.crotchBoobs+].",
	
									" С [npc2.a_moan+], [npc2.name] отвечает на движение [npc.namePos], погружая свои [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], вызывая приятные [npc.moans] между [npc.her] [npc.lips], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(try)] отстраниться, [npc2.sobbing] и сопротивляясь прикосновению [npc.namePos], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.hands] в [npc.her] [npc.crotchBoobs+].",
	
									" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] корчится от дискомфорта, умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc2.verb(continue)] втискивает [npc2.namePos] [npc2.hands] в [npc.her] [npc.crotchBoobs+].",
	
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], и пока [npc.name] [npc.verb(carry)] заставляет [npc2.her] [npc2.hands] входить в [npc.her] [npc.crotchBoobs+], [npc2.she] [npc2.verb(continue)] сопротивляться прикосновениям [npc.her]."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.crotchMilk] вытекает в [npc.namePos] [npc.lowClothing(NIPPLES_CROTCH)], пока [npc2.name] сдавливает [npc.her] [npc.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая порция [npc.crotchMilk] выплескивается в [npc.namePos] [npc.lowClothing(NIPPLES_CROTCH)], пока [npc2.name] сдавливает [npc.her] [npc.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Струйка [npc.crotchMilk] стекает в [npc.namePos] [npc.lowClothing(NIPPLES_CROTCH)], пока [npc2.name] сдавливает [npc.her] [npc.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] начинает вытекать в [npc.her] [npc.lowClothing(NIPPLES_CROTCH)], и [npc.she] [npc.moanVerb], пока [npc.she] [npc.verb(feel)] стекает по [npc.her] [npc.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] начинает сочиться тонкой струйкой в [npc.her] [npc.lowClothing(NIPPLES_CROTCH)], пока [npc2.name] сдавливает [npc.her] [npc.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] начинает литься непрерывным потоком, быстро пропитывая [npc.her] [npc.lowClothing(NIPPLES_CROTCH)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.NamePos] [npc.crotchMilk] начинает хлестать густым потоком, быстро пропитывая [npc.her] [npc.lowClothing(NIPPLES_CROTCH)].");
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
								"Сжимая [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вниз к [npc.her] нижней части живота, издавая тихий [npc.moan], пока [npc.name] [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] берет [npc2.namePos] [npc2.hands], направляя их вниз, чтобы нежно [npc.verb(press)] в мягкую плоть [npc.her] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] осторожно [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз, чтобы мягко прижать их к [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Сжимая [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вниз к [npc.her] нижней части живота, издавая [npc.a_moan+], пока [npc.name] с нетерпением [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] берет [npc2.namePos] [npc2.hands], с нетерпением направляя их вниз, чтобы вдавить в мягкую плоть [npc.her] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз, чтобы с энтузиазмом прижать их к [npc.her] [npc.crotchBoobs+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] резко дергает их вниз к [npc.her] нижней части живота, издавая [npc.a_moan+], пока [npc.name] грубо [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], грубо дергая их вниз, чтобы прижать к мягкой плоти [npc.her] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] яростно [npc.verb(pull)] их вниз к [npc.her] нижней части живота, заставляя [npc2.her] [npc2.fingers] прижаться к мягкой плоти [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Сжимая [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вниз к [npc.her] нижней части живота, издавая [npc.a_moan+], пока [npc.name] с нетерпением [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] берет [npc2.namePos] [npc2.hands], с нетерпением направляя их вниз, чтобы вдавить в мягкую плоть [npc.her] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз, чтобы с энтузиазмом прижать их к [npc.her] [npc.crotchBoobs+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.hands] в [npc.hers], [npc.name] [npc.verb(guide)] их вниз к [npc.her] нижней части живота, издавая [npc.a_moan+], пока [npc.name] [npc.verb(press)] их в [npc.her] [npc.crotchBoobs+].",

								"[npc.Name] [npc.verb(take)] хватает [npc2.namePos] [npc2.hands], направляя их вниз, чтобы [npc.verb(press)] в мягкую плоть [npc.her] [npc.crotchBoobs].",

								"Схватив [npc2.namePos] [npc2.hands], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз, чтобы прижать их к [npc.her] [npc.crotchBoobs+]."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] тихое [npc2.moan] в ответ на жадность [npc.namePos], прежде чем мягко вдавить [npc2.her] [npc2.hands] в плоть [npc.her] [npc.crotchBoobs+].",
	
									" С мягким [npc2.moan], [npc2.name] с нетерпением отвечает на движение [npc.namePos], плавно погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" Мягко [npc2.moaning], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], извлекая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] нежно [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на жадность [npc.namePos], прежде чем с нетерпением прижать [npc2.her] [npc2.hands] к плоти [npc.her] [npc.crotchBoobs+].",
	
									" С [npc2.a_moan+], [npc2.name] с жадностью отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], извлекая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] с жадностью [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] в ответ на жадность [npc.namePos], грубо прижимая [npc2.her] [npc2.hands] к плоти [npc.her] [npc.crotchBoobs+], пока [npc2.she] рычит, что [npc2.sheIs] всё еще здесь главный.",
	
									" С [npc2.a_moan+], [npc2.name] с жадностью отвечает на движение [npc.namePos], грубо погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], вызывая приятные [npc.moans] между [npc.her] [npc.lips], пока [npc2.she] грубо [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на жадность [npc.namePos], прежде чем с нетерпением прижать [npc2.her] [npc2.hands] к плоти [npc.her] [npc.crotchBoobs+].",
	
									" С [npc2.a_moan+], [npc2.name] с жадностью отвечает на движение [npc.namePos], с энтузиазмом погружая [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], извлекая приятные [npc.moans] из между [npc.her] [npc.lips], пока [npc2.she] с жадностью [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ на жадность [npc.namePos], прежде чем прижать [npc2.her] [npc2.hands] к плоти [npc.her] [npc.crotchBoobs+].",
	
									" С [npc2.a_moan+], [npc2.name] отвечает на движение [npc.namePos], погружая свои [npc2.her] [npc2.fingers] в мягкую плоть [npc.her] [npc.crotchBoobs].",
	
									" [npc2.Moaning+], [npc2.name] [npc2.verb(start)] играет с [npc.namePos] [npc.crotchBoobs+], вызывая приятные [npc.moans] между [npc.her] [npc.lips], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.fingers] в [npc.her] [npc.crotchBoobs]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] пытается отстраниться, [npc2.sobbing] и борется с прикосновениями [npc.namePos], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.hands] в [npc.her] [npc.crotchBoobs+].",
	
									" Из-за [npc2.a_sob+], [npc2.name] [npc2.verb(start)] корчится от дискомфорта, умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc2.verb(continue)] втискивает [npc2.her] [npc2.hands] в [npc.her] [npc.crotchBoobs+].",
	
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], и пока [npc.name] [npc.verb(carry)] продолжает втискивать [npc2.her] [npc2.hands] в [npc.her] [npc.crotchBoobs+], [npc2.namePos] [npc2.verb(continue)] сопротивляться прикосновениям [npc.her]."));
							break;
						default:
							break;
					}
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.crotchMilk] вытекает на кончики пальцев [npc2.namePos], пока [npc2.she] сжимает [npc.namePos] [npc.crotchNipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая струйка [npc.crotchMilk] вытекает на кончики пальцев [npc2.namePos], пока [npc2.she] сжимает [npc.namePos] [npc.crotchNipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Струйка [npc.crotchMilk] стекает по кончикам пальцев [npc2.namePos], пока [npc2.she] сжимает [npc.namePos] [npc.crotchNipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] начинает течь по пальцам [npc2.namePos], и [npc.she] [npc.moanVerb], пока [npc.she] [npc.verb(feel)] течет по [npc.her] [npc.crotchBoobs+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] начинает течь тонкой струйкой по [npc2.namePos] пальцам, пока [npc2.she] сжимает [npc.her] [npc.crotchNipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] начинает литься непрерывным потоком, быстро пропитывая [npc.her] грудь и стекая на пол под [npc.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.namePos] [npc.crotchMilk] начинает литься густым потоком, быстро пропитывая [npc.her] грудь и стекая на пол, образуя под [npc.herHim] огромную лужу.");
						break;
					default:
						break;
				}
		
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects(){
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES_CROTCH)
					&& Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk().getMinimumValue()>=Lactation.ONE_TRICKLE.getMinimumValue()
					&& Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH)!=null) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-10);
		}
		
	};
}
