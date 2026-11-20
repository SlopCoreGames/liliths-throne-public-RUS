package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.1.88
 * @version 0.3.1
 * @author Innoxia
 */
public class TongueBreasts {
	
	public static final SexAction KISS_BREAST = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				return "Целовать грудь";
			} else {
				return "Целовать грудь";
			}
		}

		@Override
		public String getActionDescription() {
			return "Осыпать поцелуями обнаженную [npc2.breasts] [npc2.namePos].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] медленно [npc.verb(lean)] в [npc2.namePos] грудь, жадно прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать нежными поцелуями [npc2.her] [npc2.breasts+].",

							"Мягко прижимая [npc.her] [npc.lips+] к [npc2.namePos] груди, [npc.name] [npc.verb(start)] осыпает любящими поцелуями обнаженную [npc2.breasts+] [npc2.her].",

							"[npc.Name] [npc.verb(start)], нежно целуя обнаженную [npc2.breasts] [npc2.namePos], вдыхает аромат [npc2.her] [npc2.scent+], пока [npc.she] отчаянно [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.skin+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] с жадностью [npc.verb(lean)] в [npc2.namePos] грудь, ненасытно прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать страстными поцелуями [npc2.her] [npc2.breasts+].",

							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] груди, [npc.name] [npc.verb(start)] осыпает восторженными поцелуями обнаженную [npc2.breasts+] [npc2.her].",

							"[npc.Name] [npc.verb(start)], страстно целуя обнаженную [npc2.breasts] [npc2.namePos], вдыхает аромат [npc2.her] [npc2.scent+], пока [npc.she] в отчаянии [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.skin+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] жадно [npc.verb(lean)] в [npc2.namePos] грудь, грубо прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать [npc2.her] [npc2.breasts+] серией властных поцелуев.",

							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] груди, [npc.name] [npc.verb(start)] осыпает [npc2.her] обнаженную [npc2.breasts+] серией грубых поцелуев.",

							"[npc.Name] [npc.verb(start)], грубо целуя обнаженную [npc2.breasts] [npc2.namePos], вдыхает [npc2.her] [npc2.scent+], пока [npc.she] с силой [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.skin+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] с жадностью [npc.verb(lean)] в [npc2.namePos] грудь, ненасытно прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать страстными поцелуями [npc2.her] [npc2.breasts+].",

							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] груди, [npc.name] [npc.verb(start)] осыпает восторженными поцелуями обнаженную [npc2.breasts+] [npc2.her].",

							"[npc.Name] [npc.verb(start)], страстно целуя обнаженную [npc2.breasts] [npc2.namePos], вдыхает аромат [npc2.her] [npc2.scent+], пока [npc.she] в отчаянии [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.skin+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] грудь, прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать [npc2.her] [npc2.breasts+] серией поцелуев.",

							"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] груди, [npc.name] [npc.verb(start)] осыпает [npc2.her] обнаженную [npc2.breasts+] серией поцелуев.",

							"[npc.Name] [npc.verb(start)], целуя обнаженную [npc2.breasts] [npc2.namePos], вдыхает [npc2.her] [npc2.scent+], пока [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.skin+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan] в ответ и мягко [npc2.verb(pull)] [npc.namePos] [npc.face] в [npc2.her] грудь, пока [npc2.she] [npc2.verb(cry)], чтобы [npc.herHim] мог(ла) продолжать.",
	
								" Нежный [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips], когда [npc2.she] [npc2.verb(push)] [npc2.her] грудь в ответ.",
	
								" Мягко выпячивая [npc2.her] грудь навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] мог(ла) продолжить."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, и с нетерпением [npc2.verb(pull)] [npc.namePos] [npc.face] в [npc2.her] грудь, пока [npc2.she] [npc2.verb(cry)], чтобы [npc.herHim] мог(ла) продолжить.",
	
								" [npc.A_moan+] доносится из-за [npc2.namePos] [npc2.lips], когда [npc2.she] с энтузиазмом [npc2.verb(push)] [npc2.her] грудь в ответ.",
	
								" С нетерпением выпячивая [npc2.her] грудь навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] мог(ла) продолжить."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, и грубо [npc2.verb(yank)] [npc.namePos] [npc.face] в [npc2.her] грудь, пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжить.",
	
								" [npc.A_moan+] доносится из-за [npc2.namePos] [npc2.lips], когда [npc2.she] грубо [npc2.verb(push)] [npc2.her] грудь в ответ.",
	
								" Сильно выпячивая [npc2.her] грудь навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжить."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, и с нетерпением [npc2.verb(pull)] [npc.namePos] [npc.face] в [npc2.her] грудь, пока [npc2.she] [npc2.verb(cry)], чтобы [npc.herHim] мог(ла) продолжить.",
	
								" [npc.A_moan+] доносится из-за [npc2.namePos] [npc2.lips], когда [npc2.she] с энтузиазмом [npc2.verb(push)] [npc2.her] грудь в ответ.",
	
								" С нетерпением выпячивая [npc2.her] грудь навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] мог(ла) продолжить."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу в ответ на [npc2.a_moan+], и [npc2.verb(pull)] [npc.namePos] [npc.face] в [npc2.her] грудь, пока [npc2.she] [npc2.verb(cry)] наружу, чтобы [npc.herHim] мог(ла) продолжать.",
	
								" [npc.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips], когда [npc2.she] [npc2.verb(push)] наружу [npc2.her] грудь в ответ.",
	
								" Выпячивая [npc2.her] грудь навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] мог(ла) продолжать."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.breasts] от [npc.namePos] [npc.face], издавая [npc2.a_sob+], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое.",
	
								" Со слезами, катящимися по [npc2.her] [npc2.face], [npc2.name] [npc2.verb(struggle)] против [npc.name], [npc2.sobbing] вслух, пока [npc2.she] [npc2.verb(try)] отталкивать [npc2.her] [npc2.breasts] от нежелательного [npc.lips] [npc.her].",
	
								" [npc2.Sobbing] вслух, и со слезами на [npc2.namePos] [npc2.eyes], [npc2.name] [npc2.verb(beg)], чтобы [npc.name] оставил(а) [npc2.herHim] в покое, пока [npc2.she] лихорадочно [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.breasts]."));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
