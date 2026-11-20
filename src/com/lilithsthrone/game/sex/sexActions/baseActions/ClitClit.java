package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.3
 * @version 0.3.3
 * @author Innoxia
 */
public class ClitClit {
	
	public static final SexAction TRIBBING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать триббинг";
		}

		@Override
		public String getActionDescription() {
			return "Начать ритмично тереться [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно толкнув [npc.her] [npc.hips+], [npc.name] [npc.verb(press)] [npc.her] [npc.clit+] о [npc2.namePos] [npc2.pussy+], она начинает ритмично тереться вверх и вниз о [npc2.her] [npc2.labia+].",
							"С [npc.a_moan+], [npc.name] медленно [npc.verb(press)] [npc.her] пах между [npc2.namePos] [npc2.legs], прежде чем начать тереться [npc.her] [npc.clit+] вверх и вниз по [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко толкнув [npc.her] [npc.hips+], [npc.name] [npc.verb(slam)] [npc.her] [npc.clit+] о [npc2.namePos] [npc2.pussy+], она начинает с силой тереться вверх и вниз о [npc2.her] [npc2.labia+].",
							"С [npc.a_moan+], [npc.name] грубо [npc.verb(slam)] [npc.her] пах между [npc2.namePos] [npc2.legs], прежде чем начать с силой тереться [npc.her] [npc.clit+] вверх и вниз по [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Страстно толкнув [npc.her] [npc.hips+], [npc.name] [npc.verb(bump)] [npc.her] [npc.clit+] о [npc2.namePos] [npc2.pussy+], она начинает с энтузиазмом тереться вверх и вниз о [npc2.her] [npc2.labia+].",
							"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] пах между [npc2.namePos] [npc2.legs], прежде чем начать неистово тереться [npc.her] [npc.clit+] вверх и вниз по [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резким толчком [npc.her] [npc.hips+], [npc.name] [npc.verb(bump)] [npc.her] [npc.clit+] против [npc2.namePos] [npc2.pussy+], после чего она начинает тереться вверх-вниз об [npc2.her] [npc2.labia+].",
							"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] пахом между [npc2.namePos] [npc2.legs], прежде чем начать двигаться вверх-вниз об [npc.her] [npc.clit+] поверх [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Мягко подаваясь навстречу [npc2.her] собственным [npc2.hips], [npc2.name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(mirror)] движения [npc.name], нежно [npc2.verb(rub)] [npc2.her] [npc2.clit+] против [npc.her] [npc.pussy+].",
								" Отвечая мягким движением [npc2.her] собственного [npc2.hips], [npc2.name] [npc2.verb(start)] зеркально повторяя движения [npc.namePos], помогая мягко тереться [npc2.her] [npc2.clit+] вверх-вниз по [npc.her] [npc.labia+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Грубо подаваясь навстречу [npc2.her] собственным [npc2.hips], [npc2.name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(mirror)] движения [npc.name], и с силой [npc2.verb(grind)] [npc2.her] [npc2.clit+] против [npc.her] [npc.pussy+].",
								" Отвечая резким движением [npc2.her] собственного [npc2.hips], [npc2.name] [npc2.verb(start)] зеркально повторяя движения [npc.namePos], помогая грубо тереться [npc2.her] [npc2.clit+] вверх-вниз по [npc.her] [npc.labia+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Счастливая, [npc2.her] отвечает вздрагиваниями своего [npc2.hips], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(mirror)] движения [npc.name], и охотно [npc2.verb(rub)] [npc2.her] [npc2.clit+] против [npc.her] [npc.pussy+].",
								" В ответ на это [npc2.her] радостно выгибает свой [npc2.hips], а [npc2.name] [npc2.verb(start)], повторяя движения [npc.namePos], помогает неистово тереть [npc2.her] [npc2.clit+] вверх и вниз по [npc.her] [npc.labia+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Подаваясь своими [npc2.hips] в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], когда [npc2.she] [npc2.verb(mirror)] движения [npc.name] и [npc2.verb(rub)] свой [npc2.clit+] о [npc.her] [npc.pussy+].",
								" В ответ на это [npc2.her] дергает своим [npc2.hips], [npc2.name] [npc2.verb(start)], зеркально повторяя движения [npc.namePos], помогая тереться [npc2.her] [npc2.clit+] вверх и вниз по [npc.her] [npc.labia+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь отстраниться, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.name] мог уйти от [npc2.herHim] и оставить [npc2.her] [npc2.pussy] в покое.",
								" Отвечая неистовым отстранением от нежелательного напора [npc.namePos], [npc2.name] [npc2.verb(start)], умоляя, чтобы её оставили в покое, и при этом пытаясь оттянуть [npc2.her] [npc2.pussy] от [npc.nameHers]."));
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
							" [npc2.Name] с энтузиазмом [npc2.verb(respond)] в ответ, и [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(rub)] [npc2.her] собственный [npc2.clit] вверх и вниз по [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy] против [npc.nameHers].",
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, используя [npc.namePos] движения, чтобы помочь втирать [npc2.her] собственный [npc2.clit+] в [npc.her] [npc.vagina+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.pussy], [npc2.name] отчаянно [npc2.verb(beg)], чтобы [npc.name] выбраться из-под [npc2.her] [npc2.vagina+].",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name] от [npc2.her] [npc2.pussy].",
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] ушел от [npc2.her] [npc2.vagina+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(respond)] в ответ, и [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(rub)] [npc2.her] собственный [npc2.clit] вверх и вниз по [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+] и, мощно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy] об [npc.nameHers].",
							" [npc2.Moaning] в экстазе, [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, используя [npc.namePos] движения, чтобы помогать втирать [npc2.her] собственную [npc2.clit+] в [npc.her] [npc.vagina+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с радостью [npc2.verb(respond)] в ответ, и [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] мягко [npc2.verb(rub)] [npc2.her] собственную [npc2.clit] вверх и вниз по [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] плавно выскальзывает из между [npc2.namePos] [npc2.lips+] и, нежно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.pussy] об [npc.nameHers].",
							" [npc2.Moaning] в наслаждении, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+] наружу, используя [npc.namePos] движения, чтобы помогать втирать [npc2.her] собственную [npc2.clit+] в [npc.her] [npc.vagina+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с силой [npc2.verb(respond)] в ответ, и [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] собственную [npc2.clit] вверх и вниз по [npc.namePos] [npc.pussy+].",
							" [npc2.A_moan+] резко вырывается из между [npc2.namePos] [npc2.lips+] и, грубо выталкивая [npc2.her] [npc2.hips], [npc2.she] с силой [npc2.verb(grind)] [npc2.her] [npc2.pussy] об [npc.nameHers].",
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, используя [npc.namePos] движения, чтобы помочь вжать [npc2.her] собственную [npc2.clit+] против [npc.her] [npc.vagina+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TRIBBING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежное триббинг";
		}

		@Override
		public String getActionDescription() {
			return "Мягко двигай своей [npc.clit] вверх и вниз по [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая легкий [npc.moan] при каждом толчке [npc.her] [npc.hips], [npc.name] нежно [npc.verb(rub)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] серию мягких вздохов, пока [npc.she] нежно [npc.verb(rub)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"Скользя [npc.her] [npc.clit+] по [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] легкий [npc.moan] при каждом движении [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Триббинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжай тереться своей [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] при каждом жадном толчке [npc.her] [npc.hips], [npc.name] страстно [npc.verb(rub)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] отчаянно [npc.verb(grind)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"С жадностью втирая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] с каждым неистовым толчком [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубый триббинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать грубо тереть свой [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] при каждом мощном толчке [npc.her] [npc.hips], [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"Яростно втирая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] с каждым мощным толчком [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Триббинг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать тереть свой [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] при каждом толчке [npc.her] [npc.hips], [npc.name] [npc.verb(continue)], чтобы тереть [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] [npc.verb(rub)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"Тереть [npc.her] [npc.clit+] о [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] с каждым толчком [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TRIBBING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадное триббинг";
		}

		@Override
		public String getActionDescription() {
			return "Энергично двигай своим [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] при каждом жадном толчке [npc.her] [npc.hips], [npc.name] страстно [npc.verb(rub)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] отчаянно [npc.verb(grind)] [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] [npc2.vagina+].",
					"С жадностью втирая [npc.her] [npc.clit+] в [npc2.namePos] [npc2.vagina+], [npc.name] [npc.verb(let)] [npc.a_moan+] с каждым неистовым толчком [npc.her] [npc.hips]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TRIBBING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться триббингу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться отстранить свой [npc.pussy] от [npc2.namePos] [npc2.clit+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы оттянуть пах [npc.her] от [npc2.nameHers], но [npc.her] усилия оказываются напрасными, так как [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, удерживая [npc2.her] [npc2.clit+] мягко прижатым к [npc.her] [npc.pussy+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, после чего продолжает нежно тереть [npc2.her] [npc2.clit+] о [npc.her] [npc.vagina+].",
								"В [npc.eyes] [npc.namePos] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] нежно [npc2.verb(rub)] [npc2.her] [npc2.vagina+] вверх к [npc2.hers]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], чтобы оттянуть пах [npc.her] от [npc2.nameHers], но [npc.her] усилия оказываются напрасными, так как [npc2.she] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] вперед, с силой втирая [npc2.her] [npc2.clit+] в [npc.her] [npc.pussy+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, после чего продолжает грубо тереть [npc2.her] [npc2.clit+] о [npc.her] [npc.vagina+].",
								"В [npc.eyes] [npc.namePos] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] с силой [npc2.verb(grind)] [npc2.her] [npc2.vagina+] к [npc2.hers]."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(try)], пытаясь оттянуть пах [npc.her] от [npc2.nameHers], но усилия [npc.her] оказываются напрасными, так как [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, удерживая [npc2.her] [npc2.clit+] прижатым к [npc.her] [npc.pussy+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, после чего продолжает жадно тереть [npc2.her] [npc2.clit+] о [npc.her] [npc.vagina+].",
								"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] с энтузиазмом [npc2.verb(rub)] [npc2.her] [npc2.vagina+], прижатые к [npc2.hers]."));
						break;
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)], чтобы оттянуть пах [npc.her] от [npc2.nameHers], но усилия [npc.her] оказываются напрасными, так как [npc2.she] [npc2.verb(move)] вперед, удерживая [npc2.her] [npc2.clit+] прижатыми к [npc.her] [npc.pussy+].",
						"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, заставляя [npc2.her] [npc2.clit+] тереться о [npc.her] [npc.vagina+].",
						"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.pussy] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(keep)] [npc2.her] [npc2.vagina+], прижатые к [npc2.hers]."));
			}
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TRIBBING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить триббинг";
		}

		@Override
		public String getActionDescription() {
			return "Оттяни свой [npc.clit+] от [npc2.namePos] [npc2.vagina+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним грубым толчком [npc.name] [npc.verb(pull)] [npc.her] пах от [npc2.namePos] [npc2.pussy+], кладя конец триббингу.",
							"В последний раз грубо растирая [npc.her] [npc.clit+] по [npc2.namePos] [npc2.pussy], [npc.name] затем [npc.verb(pull)] обратно, завершая это неистовое триббинг."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С последним резким движением [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] [npc.her] в сторону от [npc2.namePos] [npc2.pussy+], кладя конец триббингу.",
							"В последний раз растирая [npc.her] [npc.clit+] по [npc2.namePos] [npc2.pussy], [npc.name] затем [npc.verb(pull)] обратно, завершая триббинг."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Хотя она и рада, что [npc2.her] [npc2.pussy] освободились, [npc2.name] [npc2.verb(continue)] плачет и слабо сопротивляется [npc.name], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое.",
								" Пока [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.vagina+] прочь от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] обратно, сигнализируя о желании [npc2.her] получить еще больше внимания.",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PUSSY_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.CLIT, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Триббинг-массаж";
		}

		@Override
		public String getActionDescription() {
			return "Используй свой цепкий клитор, чтобы массировать и тереть [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.PREHENSILE) && Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.DOM_ROUGH;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], используя цепкость [npc.her] [npc.clit], чтобы тереться о [npc2.namePos] [npc2.pussy+], прежде чем подняться выше, чтобы массировать и сжимать собственную [npc2.clit+] [npc2.her].",
					isTargetedCharacterInanimate()
						?null
						:"[npc.Name] [npc.verb(let)] извлекает [npc.a_moan+] как [npc.she] [npc.verb(focus)], управляя [npc.her] цепким [npc.clit]. Потирая и лаская [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(grin)] как [npc.she], а затем [npc.verb(move)] вверх, чтобы помассировать [npc2.her] собственную [npc2.clit+], заставляя [npc2.herHim] издать [npc2.a_moan+].",
					"[npc.Name] [npc.verb(grin)] в восторге, пока [npc.she] [npc.verb(use)] [npc.her] цепким клитором, чтобы массировать и ласкать [npc2.namePos] [npc2.labia+], прежде чем подняться выше, чтобы сжимать и тереть [npc2.her] собственную [npc2.clit+].",
					"С [npc.a_moan+], [npc.name] [npc.verb(focus)], управляя [npc.her] цепким [npc.clit], надавливая и массируя [npc2.namePos] [npc2.labia+], прежде чем подняться выше, чтобы тереть [npc2.her] [npc2.clit+].");
		}
	};
}
