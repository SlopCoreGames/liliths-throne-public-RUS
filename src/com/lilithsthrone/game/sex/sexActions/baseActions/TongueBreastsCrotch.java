package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
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
public class TongueBreastsCrotch {
	
	public static final SexAction KISS_BREAST_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Целовать вымя";
			} else {
				return "Целовать грудь в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Осыпать поцелуями обнаженные [npc2.crotchBoobs] [npc2.namePos].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] медленно [npc.verb(lean)] в область [npc2.namePos] нижнего живота, прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать нежными поцелуями [npc2.her] [npc2.crotchBoobs+].",

							"Мягко прижимая [npc.her] [npc.lips+] к [npc2.namePos] нижнему животу, [npc.name] [npc.verb(start)] осыпает любящими поцелуями обнаженные [npc2.crotchBoobs+] [npc2.her].",

							"[npc.Name] [npc.verb(start)] нежно целуя обнаженные [npc2.crotchBoobs] [npc2.namePos], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] отчаянно [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.crotchBoobsSkin+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] с нетерпением [npc.verb(lean)] в область [npc2.namePos] нижнего живота, жадно прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать страстными поцелуями [npc2.her] [npc2.crotchBoobs+].",

							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] нижнему животу, [npc.name] [npc.verb(start)] осыпает восторженными поцелуями обнаженные [npc2.crotchBoobs+] [npc2.her].",

							"[npc.Name] [npc.verb(start)] страстно целуя обнаженные [npc2.crotchBoobs] [npc2.namePos], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] в отчаянии [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.crotchBoobsSkin+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] жадно [npc.verb(lean)] в [npc2.namePos] низ живота, грубо прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем приняться осыпать [npc2.her] [npc2.crotchBoobs+] серией властных поцелуев.",

							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] низу живота, [npc.name] [npc.verb(start)] осыпает [npc2.her] обнаженные [npc2.crotchBoobs+] серией грубых поцелуев.",

							"[npc.Name] [npc.verb(start)] грубо целуя обнаженные [npc2.namePos] [npc2.crotchBoobs], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] с силой [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.crotchBoobsSkin+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] с нетерпением [npc.verb(lean)] в область [npc2.namePos] нижнего живота, жадно прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать страстными поцелуями [npc2.her] [npc2.crotchBoobs+].",

							"Жадно прижимая [npc.her] [npc.lips+] к [npc2.namePos] нижнему животу, [npc.name] [npc.verb(start)] осыпает восторженными поцелуями обнаженные [npc2.crotchBoobs+] [npc2.her].",

							"[npc.Name] [npc.verb(start)] страстно целуя обнаженные [npc2.crotchBoobs] [npc2.namePos], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] в отчаянии [npc.verb(press)] [npc.her] [npc.lips+] о [npc2.her] [npc2.crotchBoobsSkin+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] низ живота, прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.breastSkin+], прежде чем начать осыпать [npc2.her] [npc2.crotchBoobs+] серией поцелуев.",

							"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] низу живота, [npc.name] [npc.verb(start)] осыпает [npc2.her] обнаженные [npc2.crotchBoobs+] серией поцелуев.",

							"[npc.Name] [npc.verb(start)] целуя обнаженные [npc2.namePos] [npc2.crotchBoobs], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.crotchBoobsSkin+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan] в ответ и мягко [npc2.verb(pull)] [npc.namePos] [npc.face] в [npc2.her] живот, пока [npc2.she] [npc2.verb(cry)] для того, чтобы [npc.herHim] продолжил(а).",
	
								" Легкий [npc2.moan] вырывается из-под [npc2.namePos] [npc2.lips], когда [npc2.she] [npc2.verb(push)] [npc2.her] живот в ответ.",
	
								" Мягко выпячивая [npc2.her] живот навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжил(а)."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ и жадно [npc2.verb(pull)] [npc.namePos] [npc.face] в [npc2.her] живот, пока [npc2.she] [npc2.verb(cry)] для того, чтобы [npc.herHim] продолжил(а).",
	
								" [npc.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips], когда [npc2.she] с энтузиазмом [npc2.verb(push)] [npc2.her] живот в ответ.",
	
								" Жадно выпячивая [npc2.her] живот навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжил(а)."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ и грубо [npc2.verb(yank)] [npc.namePos] [npc.face] в [npc2.her] живот, пока [npc2.she] [npc2.verb(order)], чтобы [npc.herHim] продолжил(а).",
	
								" [npc.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips], когда [npc2.she] грубо [npc2.verb(push)] [npc2.her] живот в ответ.",
	
								" Сильно выпячивая живот [npc2.her] в [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжить."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ и жадно [npc2.verb(pull)] [npc.namePos] [npc.face] в [npc2.her] живот, пока [npc2.she] [npc2.verb(cry)] для того, чтобы [npc.herHim] продолжил(а).",
	
								" [npc.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips], когда [npc2.she] с энтузиазмом [npc2.verb(push)] [npc2.her] живот в ответ.",
	
								" Жадно выпячивая [npc2.her] живот навстречу [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжил(а)."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ [npc2.verb(let)] [npc2.a_moan+], и [npc2.verb(pull)] [npc.namePos] [npc.face] в живот [npc2.her], пока [npc2.she] [npc2.verb(cry)] для [npc.herHim], чтобы продолжить.",
	
								" [npc.A_moan+] вырывается из между [npc2.namePos] [npc2.lips], когда [npc2.she] в ответ [npc2.verb(push)] [npc2.her] живота.",
	
								" Выпячивая живот [npc2.her] в [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] мог(ла) продолжить."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.crotchBoobs] от [npc.namePos] [npc.face], издавая [npc2.a_sob+], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое.",
	
								" Слезы текут по [npc2.her] [npc2.face], [npc2.name] [npc2.verb(struggle)] против [npc.name], громко выкрикивая [npc2.sobbing], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.crotchBoobs] от незваного [npc.lips] [npc.her].",
	
								" [npc2.Sobbing] громко вскрикивает, и со слезами на [npc2.namePos] [npc2.eyes], [npc2.name] [npc2.verb(beg)], чтобы [npc.name] оставил(а) [npc2.herHim] в покое, пока [npc2.she] лихорадочно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.crotchBoobs]."));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
