package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.4.5
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerBreasts {
	
	public static final SexAction FEEL_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.BREAST)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Тискать грудь (самой себе)";
		}

		@Override
		public String getActionDescription() {
			return "Сжимать и тискать свою собственную [npc.breasts+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreasts()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.NIPPLES)){
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руки к груди [npc.her], [npc.name] [npc.verb(let)] мягкую [npc.moan], пока [npc.she] [npc.verb(start)] ласкает и тискает [npc.her] [npc.breastRows] [npc.breasts+], мягко прижимая [npc.her] [npc.lowClothing(NIPPLES)] к [npc.her] [npc.nipples+] в процессе.",
								"Возбужденная и жаждущая, чтобы [npc.her] [npc.breasts+] ласкали, [npc.name] [npc.verb(reach)] вверх, чтобы нежно прижать [npc.her] [npc.hands+] к [npc.her] [npc.topClothing(NIPPLES)], прежде чем начать мягко тискать и сжимать грудь [npc.her].",
								"Дразня [npc.her] [npc.fingers] над [npc.her] [npc.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] мягко [npc.moaning] и вздыхая, пока [npc.she] нежно [npc.verb(fondle)] и [npc.verb(grope)] [npc.her] собственные [npc.breastRows] [npc.breasts+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руки к груди [npc.her], [npc.name] [npc.verb(let)] мягкую [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ласкает и тискает [npc.her] [npc.breastRows] [npc.breasts+], с силой втирая [npc.her] [npc.lowClothing(NIPPLES)] в [npc.her] [npc.nipples+] в процессе.",
								"Возбужденная и жаждущая, чтобы [npc.her] [npc.breasts+] ласкали, [npc.name] [npc.verb(reach)] вверх, чтобы с силой втереть [npc.her] [npc.hands+] в [npc.her] [npc.topClothing(NIPPLES)], прежде чем начать грубо тискать и сжимать грудь [npc.her].",
								"Проводя [npc.her] [npc.fingers] по [npc.her] [npc.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] громко тяжело дышит и [npc.moaning], пока [npc.she] грубо [npc.verb(fondle)] и [npc.verb(grope)] [npc.her] собственные [npc.breastRows] [npc.breasts+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руки к [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(start)] ласкает и ощупывает [npc.her] [npc.breastRows] [npc.breasts+], прижимая при этом [npc.her] [npc.lowClothing(NIPPLES)] к [npc.her] [npc.nipples+].",
								"Возбужденная и отчаянно желающая, чтобы [npc.her] [npc.breasts+] поиграла с ней, [npc.name] [npc.verb(reach)] прижимает [npc.her] [npc.hands+] к [npc.her] [npc.topClothing(NIPPLES)], прежде чем начать ощупывать и сжимать [npc.her] грудь.",
								"Проводя [npc.her] [npc.fingers] по [npc.her] [npc.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] тяжело дышит и [npc.moaning], пока [npc.she] [npc.verb(fondle)] и [npc.verb(grope)] [npc.her] собственные [npc.breastRows] [npc.breasts+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руки к [npc.her] груди, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(start)] ласкает и ощупывает [npc.her] [npc.breastRows] [npc.breasts+], отчаянно прижимая при этом [npc.her] [npc.lowClothing(NIPPLES)] к [npc.her] [npc.nipples+].",
								"Возбужденная и жаждущая, чтобы [npc.her] [npc.breasts+] ласкали, [npc.name] [npc.verb(reach)] до нетерпеливого прижимания [npc.her] [npc.hands+] к [npc.her] [npc.topClothing(NIPPLES)], прежде чем начать неистово ощупывать и сжимать [npc.her] грудь.",
								"Водя [npc.her] [npc.fingers] по [npc.her] [npc.topClothing(NIPPLES)], [npc.name] [npc.verb(start)] отчаянно тяжело дыша и [npc.moaning], пока [npc.she] жадно [npc.verb(fondle)] и [npc.verb(grope)] [npc.her] собственные [npc.breastRows] [npc.breasts+]."));
						break;
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] вытекает в [npc.her] [npc.lowClothing(NIPPLES)], пока [npc.she] [npc.verb(squeeze)] на [npc.her] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Небольшая порция [npc.milk] выплескивается в [npc.her] [npc.lowClothing(NIPPLES)], пока [npc.she] [npc.verb(squeeze)] на [npc.her] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Струйка [npc.milk] стекает в [npc.her] [npc.lowClothing(NIPPLES)], пока [npc.she] [npc.verb(squeeze)] на [npc.her] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Почти мгновенно [npc.her] [npc.milk] [npc.verb(start)] наружу из [npc.her] [npc.nipples+] прямо в [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] быстро [npc.verb(start)] струей в [npc.her] [npc.lowClothing(NIPPLES)], пока [npc.she] [npc.verb(squeeze)] на [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] немедленно [npc.verb(start)] сплошным потоком, быстро пропитывая [npc.her] [npc.lowClothing(NIPPLES)].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] тут же [npc.verb(start)], обильно выливаясь и быстро пропитывая [npc.her] [npc.lowClothing(NIPPLES)].");
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
								"Протягивая руки к обнаженной груди [npc.her], [npc.name] [npc.verb(let)] с мягким [npc.moan], пока [npc.she] [npc.verb(start)] ласкает и тискает [npc.her] [npc.breastRows] [npc.breasts+], мягко прижимая [npc.her] [npc.fingers+] к [npc.her] [npc.nipples+] в процессе.",
								"Возбужденная и жаждущая, чтобы [npc.her] [npc.breasts+] поиграла с ней, [npc.name] [npc.verb(reach)] вверх, чтобы нежно прижать [npc.her] [npc.hands+] к обнаженной груди [npc.her], прежде чем начать мягко ощупывать и сжимать [npc.her] [npc.breastRows] [npc.breasts].",
								"Дразня [npc.her] [npc.fingers] над обнаженной [npc.her] [npc.breastRows] [npc.breasts+], [npc.name] [npc.verb(start)] нежно [npc.moaning] и вздыхая, пока [npc.she] [npc.verb(begin)] мягко ласкает и тискает их."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руки к обнаженной груди [npc.her], [npc.name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ласкает и тискает [npc.her] [npc.breastRows] [npc.breasts+], с силой втирая [npc.her] [npc.fingers+] в [npc.her] [npc.nipples+] в процессе.",
								"Возбужденная и жаждущая, чтобы [npc.her] [npc.breasts+] поиграли с ней, [npc.name] [npc.verb(reach)], чтобы с силой прижать [npc.her] [npc.hands+] к [npc.her] обнаженной груди, после чего она начинает грубо ощупывать и сжимать [npc.her] [npc.breastRows] [npc.breasts].",
								"Проводя [npc.her] [npc.fingers] по [npc.her] обнаженной [npc.breastRows] [npc.breasts+], [npc.name] [npc.verb(start)], громко тяжело дыша и [npc.moaning], пока [npc.she] [npc.verb(begin)], чтобы грубо ласкать и тискать их."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руки к [npc.her] обнаженной груди, [npc.name] [npc.verb(let)], выставляя [npc.a_moan+] напоказ, пока [npc.she] [npc.verb(start)] ласкает и ощупывает [npc.her] [npc.breastRows] [npc.breasts+], прижимая при этом [npc.her] [npc.fingers+] к [npc.her] [npc.nipples+].",
								"Возбужденная и жаждущая, чтобы [npc.her] [npc.breasts+] поиграли с ней, [npc.name] [npc.verb(reach)], чтобы прижать [npc.her] [npc.hands+] к [npc.her] обнаженной груди, после чего она начинает ощупывать и сжимать [npc.her] [npc.breastRows] [npc.breasts].",
								"Проводя [npc.her] [npc.fingers] по [npc.her] обнаженной [npc.breastRows] [npc.breasts+], [npc.name] [npc.verb(start)], тяжело дыша и [npc.moaning], пока [npc.she] [npc.verb(begin)], чтобы ласкать и ощупывать их."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Протягивая руки к обнаженной груди [npc.her], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] с жадностью [npc.verb(start)] и ощупывает [npc.her] [npc.breastRows] [npc.breasts+], отчаянно прижимая [npc.her] [npc.fingers+] к [npc.her] [npc.nipples+] в процессе.",
								"Возбужденная и жаждущая, чтобы ее [npc.her] [npc.breasts+] ласкали, [npc.name] [npc.verb(reach)] вверх, чтобы с жадностью прижать [npc.her] [npc.hands+] к обнаженной груди [npc.her], прежде чем начать неистово ощупывать и сжимать [npc.her] [npc.breastRows] [npc.breasts].",
								"[npc.her] [npc.fingers] скользят по [npc.her] обнаженным [npc.breastRows] [npc.breasts+], [npc.name] [npc.verb(start)] отчаянно тяжело дыша и [npc.moaning], пока [npc.she] [npc.verb(begin)] с нетерпением ласкать и тискать их."));
						break;
				}
				
				switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" Тонкая струйка [npc.milk] вытекает на [npc.her] [npc.fingers], пока [npc.she] [npc.verb(squeeze)] на [npc.namePos] [npc.nipples+].");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" Маленькая струйка [npc.milk] вырывается на [npc.her] [npc.fingers], пока [npc.she] [npc.verb(squeeze)] на [npc.namePos] [npc.nipples+].");
						break;
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(" Струйка [npc.milk] стекает по [npc.her] [npc.fingers], пока [npc.she] [npc.verb(squeeze)] на [npc.namePos] [npc.nipples+].");
						break;
					case FOUR_LARGE_AMOUNT:
						UtilText.nodeContentSB.append(" Почти мгновенно [npc.her] [npc.milk] начинает вытекать по [npc.her] [npc.fingers], и [npc.she] [npc.moansVerb], пока [npc.she] [npc.verb(feel)] ее, стекающую по [npc.her] [npc.breasts+].");
						break;
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] быстро [npc.verb(start)] стекает тонкой струйкой по [npc.her] [npc.fingers], пока [npc.she] [npc.verb(squeeze)] на [npc.her] [npc.nipples+].");
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] мгновенно [npc.verb(start)] льется непрерывным потоком, быстро пропитывая [npc.her] [npc.breasts+] и капая на пол под [npc.herHim].");
						break;
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(" [npc.Her] [npc.milk] [npc.verb(start)] хлещет мощным потоком, быстро пропитывая [npc.her] [npc.breasts+] и стекая на пол под [npc.herHim], образуя там огромную лужу.");
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
