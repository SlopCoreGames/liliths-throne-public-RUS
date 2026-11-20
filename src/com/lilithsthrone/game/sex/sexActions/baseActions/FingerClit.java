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
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerClit {

	public static final SexAction CLIT_PLAY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Игры с клитором";
		}

		@Override
		public String getActionDescription() {
			return "Протянуть руку вниз к [npc2.namePos] [npc2.pussy+] и начать ласкать [npc2.her] [npc2.clit].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaOrifice.VAGINA)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Медленно погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] поднимает [npc.her] большой палец к [npc2.her] [npc2.clit], прежде чем мягко надавить и потереться о него, пока [npc.she] [npc.verb(continue)] сгибает [npc.her] пальцы внутри [npc2.herHim].",
								"Мягко скользя [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] прижимает [npc.her] большой палец к [npc2.her] [npc2.clit+], нежно потирая его, пока [npc.she] слегка [npc.verb(let)] [npc.moan].",
								"Мягко проталкивая [npc.her] [npc.fingers] глубоко в [npc2.namePos] [npc2.pussy+], немного [npc.moan] вытекает между [npc.namePos] [npc.lips], пока [npc.she] поднимает [npc.her] большой палец, чтобы начать мягко тереть и надавливать на [npc2.her] [npc2.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо втирая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] поднимает [npc.her] большой палец к [npc2.her] [npc2.clit], прежде чем с силой надавить и начать растирать его, пока [npc.she] властно [npc.verb(continue)] сгибает [npc.her] пальцы внутри [npc2.herHim].",
								"Силой вгоняя [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] прижимает [npc.her] большой палец к [npc2.her] [npc2.clit+], грубо растирая его, пока [npc.she] [npc.verb(let)] [npc.a_moan+].",
								"С силой вгоняя [npc.her] [npc.fingers] глубоко в [npc2.namePos] [npc2.pussy+], [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips], пока [npc.she] поднимает [npc.her] большой палец, чтобы начать грубо тереть и растирать [npc2.her] [npc2.clit+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] поднимает [npc.her] большой палец к [npc2.her] [npc2.clit], прежде чем с нажимом прижаться и тереться о него, пока [npc.she] [npc.verb(continue)] подгибает [npc.her] пальцы внутри [npc2.herHim].",
								"Втискивая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] прижимает [npc.her] большой палец к [npc2.her] [npc2.clit+], растирая его, пока [npc.she] [npc.verb(let)] [npc.a_moan+].",
								"Вталкивая [npc.her] [npc.fingers] глубоко в [npc2.namePos] [npc2.pussy+], [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips], пока [npc.she] поднимает [npc.her] большой палец, чтобы начать тереть и надавливать на [npc2.her] [npc2.clit+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] поднимает [npc.her] большой палец к [npc2.her] [npc2.clit], прежде чем алчно прижаться и тереться о него, пока [npc.she] [npc.verb(continue)] подгибает [npc.her] пальцы внутри [npc2.herHim].",
								"Алчно просовывая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(press)] [npc.her] большим пальцем вниз по [npc2.her] [npc2.clit+], жадно потирая его, пока [npc.she] [npc.verb(let)] [npc.a_moan+].",
								"Втискивая [npc.her] [npc.fingers] глубоко в [npc2.namePos] [npc2.pussy+], [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips], пока [npc.she] поднимает [npc.her] большой палец, чтобы начать жадно тереть и надавливать на [npc2.her] [npc2.clit+]."));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и при мягком выгибании [npc2.her] [npc2.hips], [npc2.she] мягко [npc2.verb(encourage)] [npc.name], продолжая ласкать [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(start)] в ответ мягко выгибает [npc2.her] [npc2.hips], нежно прижимая [npc2.her] [npc2.clit+] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
									" [npc2.Name] мягко [npc2.verb(start)] толчками возвращает [npc2.her] [npc2.hips] к [npc.namePos] [npc.fingers], тихо [npc2.moaning], пока [npc2.she] [npc2.verb(focus)] наслаждается удовольствием в [npc2.her] [npc2.clit+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из рта [npc2.namePos], когда [npc2.name] грубо [npc2.verb(push)] [npc2.her] [npc2.hips] назад под прикосновением [npc.her].",
									" [npc2.Name] грубо [npc2.verb(grind)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.hand], [npc2.moaning] в восторге, пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжать ласкать [npc2.her] [npc2.clit+].",
									" [npc2.Name] с силой [npc2.verb(thrust)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.fingers], [npc2.moaning] и выгибая [npc2.her] назад, пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] [npc2.clit+] под напором [npc.her] касаний."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из рта [npc2.namePos], когда [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.hips] назад под воздействием [npc.her] касаний.",
									" [npc2.Name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] на [npc.namePos] [npc.hand], [npc2.moaning] в восторге, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать ласкать [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.fingers], [npc2.moaning] и выгибая [npc2.her] назад, пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.clit+] под напором [npc.her] касаний."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] вырывается из рта [npc2.namePos], когда [npc2.she] [npc2.verb(try)] оттащить [npc2.her] [npc2.pussy] от [npc.namePos] касаний, умоляя [npc.herHim] остановиться, пока [npc2.she] борется с [npc.herHim].",
									" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] назад, издавая [npc2.a_sob+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.name] перестал(а) трогать [npc2.herHim].",
									" [npc2.Name] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] [npc.fingers], [npc2.sobbing+], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.name] оставил(а) [npc2.name] в покое."));
							break;
						default: // Dom normal and sub eager:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из рта [npc2.namePos], пока [npc2.name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips] назад под прикосновением [npc.her].",
									" [npc2.Name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], [npc2.moaning] в восторге, пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.herHim] продолжать играть с [npc2.her] [npc2.clit+].",
									" [npc2.Name] жадно [npc2.verb(push)] [npc2.her] [npc2.hips] наружу против [npc.namePos] [npc.fingers], [npc2.moaning] и выгибая [npc2.her] назад, пока [npc2.she] отчаянно [npc2.verb(press)] [npc2.her] [npc2.clit+] вниз под прикосновением [npc.her]."));
							break;
					}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Медленно скользя [npc.her] [npc.fingers+] вдоль [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] внимание на [npc2.her] [npc2.clit+], нежно сжимая и потирая её, пока [npc.she] [npc.verb(lean)] в [npc2.herHim] и [npc.verb(let)] немного [npc.moan].",
								"Мягко проводя [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(home)] на [npc2.her] [npc2.clit+], прежде чем начать нежно растирать и щипать её.",
								"Дразняще водя [npc.her] [npc.fingers] по [npc2.namePos] [npc2.pussy+], немного [npc.moan] вырывается между [npc.namePos] [npc.lips], пока [npc.she] [npc.verb(start)], чтобы нежно растирать и щипать [npc2.her] [npc2.clit+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо втирая [npc.her] [npc.fingers+] по всей длине [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] на [npc2.her] [npc2.clit+], жестоко сжимая и щипая её, пока [npc.she] [npc.verb(lean)] против [npc2.herHim] и [npc.verb(let)] [npc.a_moan+].",
								"Алчно проводя [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(home)] на [npc2.her] [npc2.clit+], прежде чем начать грубо растирать и щипать её.",
								"Втирая [npc.her] [npc.fingers] по [npc2.namePos] [npc2.pussy+], [npc.a_moan+] вырывается между [npc.namePos] [npc.lips], пока [npc.she] [npc.verb(start)], чтобы грубо растирать и щипать [npc2.her] [npc2.clit+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Скользя [npc.her] [npc.fingers+] вдоль всей длины [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] на [npc2.her] [npc2.clit+], сжимая и потирая её, пока [npc.she] [npc.verb(lean)] против [npc2.herHim] и [npc.verb(let)] [npc.a_moan+].",
								"Проводя [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(home)] на [npc2.her] [npc2.clit+], прежде чем начать её тереть и щипать.",
								"Дразня [npc.her] [npc.fingers] по [npc2.namePos] [npc2.pussy+], [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips], пока [npc.she] [npc.verb(start)], чтобы тереть и щипать [npc2.her] [npc2.clit+]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Алчно скользя [npc.her] [npc.fingers+] вдоль всей длины [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(focus)] [npc.her] на [npc2.her] [npc2.clit+], жадно сжимая и потирая её, пока [npc.she] [npc.verb(lean)] против [npc2.herHim] и [npc.verb(let)] [npc.a_moan+].",
								"Уверенно проводя [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(home)] на [npc2.her] [npc2.clit+], прежде чем начать жадно тереть и щипать её.",
								"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.pussy+], [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips], пока [npc.she] [npc.verb(start)] с нетерпением теребить и щипать [npc2.her] [npc2.clit+]."));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Тихий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и при мягком выгибании [npc2.her] [npc2.hips], [npc2.she] мягко [npc2.verb(encourage)] [npc.name], продолжая ласкать [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(start)] в ответ мягко выгибает [npc2.her] [npc2.hips], нежно прижимая [npc2.her] [npc2.clit+] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
									" [npc2.Name] мягко [npc2.verb(start)] толчками возвращает [npc2.her] [npc2.hips] к [npc.namePos] [npc.fingers], тихо [npc2.moaning], пока [npc2.she] [npc2.verb(focus)] наслаждается удовольствием в [npc2.her] [npc2.clit+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается изо рта [npc2.namePos], пока [npc2.she] грубо [npc2.verb(push)] [npc2.her] [npc2.hips], выгибаясь навстречу [npc.namePos] прикосновениям.",
									" [npc2.Name] грубо [npc2.verb(grind)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.hand], [npc2.moaning] в восторге, пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжать ласкать [npc2.her] [npc2.clit+].",
									" [npc2.Name] с силой [npc2.verb(thrust)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.fingers], [npc2.moaning], выгибая [npc2.her] назад, пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] [npc2.clit+] навстречу [npc.namePos] прикосновениям."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается изо рта [npc2.namePos], пока [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.hips], выгибаясь навстречу [npc.namePos] прикосновениям.",
									" [npc2.Name] [npc2.verb(push)] [npc2.namePos] [npc2.hips] на [npc.namePos] [npc.hand], [npc2.moaning] в восторге, пока [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать ласкать [npc2.her] [npc2.clit+].",
									" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.fingers], [npc2.moaning], выгибая [npc2.her] назад, пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.clit+] навстречу [npc.namePos] прикосновениям."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] вырывается изо рта [npc2.namePos], пока [npc2.she] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.pussy] от [npc.namePos] прикосновений, умоляя [npc.herHim] остановиться, пока [npc2.she] [npc2.verb(struggle)] против [npc.herHim].",
									" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] назад, издавая [npc2.a_sob+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.name] перестал(а) трогать [npc2.herHim].",
									" [npc2.Name] [npc2.verb(try)] убрать [npc2.her] [npc2.pussy] от [npc.namePos] [npc.fingers], [npc2.sobbing+], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил [npc2.herHim] в покое."));
							break;
						default: // Dom normal and sub eager:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вырывается из рта [npc2.namePos], пока [npc2.she] с нетерпением [npc2.verb(push)] [npc2.her] [npc2.hips] навстречу прикосновению [npc.namePos].",
									" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.hand], [npc2.moaning] от восторга, пока [npc2.she] с энтузиазмом [npc2.verb(encourage)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.clit+].",
									" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] [npc2.hips] на [npc.namePos] [npc.fingers], [npc2.moaning] и выгибая [npc2.her] назад, пока [npc2.she] отчаянно [npc2.verb(press)] [npc2.her] [npc2.clit+] навстречу прикосновению [npc.namePos]."));
							break;
					}
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
