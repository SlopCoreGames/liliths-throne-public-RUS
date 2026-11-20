package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.8.5
 * @version 0.4.10.10
 * @author Sightglass
 */
public class PenisPenis {

	private static boolean hasSmallPenis(GameCharacter gc) {
		PenisLength pl = gc.getPenisSize();
		return pl == PenisLength.ZERO_MICROSCOPIC || pl == PenisLength.ONE_TINY;
	}
	
	private static String getHersWithDildoHandling(String parserTarget) {
		GameCharacter performer = Main.sex.getCharacterPerformingAction();
		GameCharacter target = Main.sex.getTargetedPartner(performer);
		
		if(performer.hasPenisIgnoreDildo()==target.hasPenisIgnoreDildo()) {
			return "["+parserTarget+".её]";
		} else {
			return "["+parserTarget+".её] ["+parserTarget+".член]";
		}
	}
	
	public static final SexAction FROTTING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать фроттинг";
		}

		@Override
		public String getActionDescription() {
			return "Начать тереться своим [npc.cock] вверх и вниз о [npc2.namePos] [npc2.cock].";
		}

//		@Override
//		public boolean isBaseRequirementsMet() {
//			GameCharacter actor = Main.sex.getCharacterPerformingAction();
//			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
//			return actor.hasPenisIgnoreDildo() && target.hasPenisIgnoreDildo();
//		}
		
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							"Протянув руку вниз, [npc.name] [npc.verb(adjust)] [npc.her] [npc.cock], чтобы выровнять его с [npc2.nameHers], а затем медленно начать двигаться, потирая ствол [npc.her] о "+getHersWithDildoHandling("npc2")+".",
							"Осторожно поправляя [npc.her] [npc.hips+], [npc.name] [npc.verb(press)] [npc.her] [npc.cock] против [npc2.namePos] [npc2.cock], прежде чем начать ритмично тереться вперед-назад.",
							"С [npc.a_moan+], [npc.name] медленно [npc.verb(press)] [npc.her] пахом о [npc2.nameHers], прежде чем начать дразняще водить [npc.her] [npc.cock] вверх и вниз по "+getHersWithDildoHandling("npc2")+"."));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"Резким толчком [npc.her] [npc.hips+], [npc.name] с силой [npc.verb(grind)] [npc.her] [npc.cock] против [npc2.namePos] [npc2.cock].",
							"С рычанием, [npc.name] грубо [npc.verb(grab)] [npc2.name] и [npc.verb(start)], чтобы с силой вжимать [npc.her] [npc.cock+] в [npc2.her] [npc2.cock+].",
							"С [npc.a_moan+], [npc.name] грубо [npc.verb(grind)] [npc.her] пах против [npc2.nameHers], прежде чем начать неистово тереть [npc.her] [npc.cock] вверх и вниз по "+getHersWithDildoHandling("npc2")+"."));
					break;
				case SUB_EAGER:
					sb.append(UtilText.returnStringAtRandom(
							(Main.sex.getCharacterPerformingAction().hasLegs()
									?"[npc.Name] [npc.verb(draw)] [npc2.name] вплотную, разводя [npc.her] ноги, чтобы совместить [npc.her] пах с [npc2.hers]."
									:"[npc.Name] [npc.verb(draw)] [npc2.name] вплотную, пристраивая [npc.herself] так, чтобы [npc.her] пах прижимался к [npc2.hers].")
								+" [npc.Name] затем с жадностью [npc.verb(pull)] [npc2.name], подстрекая [npc2.herHim] совершать толчки и тереть [npc2.her] [npc2.cock] о "+getHersWithDildoHandling("npc")+".",
							"С [npc.a_moan+], [npc.name] с нетерпением [npc.verb(press)] [npc.her] пах против [npc2.nameHers], прежде чем начать неистово тереть [npc.her] [npc.cock] о "+getHersWithDildoHandling("npc2")+"."));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							(Main.sex.getCharacterPerformingAction().hasLegs()
									?"[npc.Name] [npc.verb(draw)] [npc2.name] вплотную, разводя [npc.her] ноги, чтобы совместить [npc.her] пах с [npc2.hers]."
									:"[npc.Name] [npc.verb(draw)] [npc2.name] вплотную, пристраивая [npc.herself] так, чтобы [npc.her] пах прижимался к [npc2.hers].")
								+" [npc.Name] затем [npc.verb(pull)] [npc2.name] кокетливо, подзадоривая [npc2.herHim] совершать толчки и движения [npc2.her] [npc2.cock] против "+getHersWithDildoHandling("npc")+".",
							"С [npc.a_moan+], [npc.name] с нетерпением [npc.verb(press)] пах [npc.her] против [npc2.nameHers], прежде чем начать дразняще прижимать [npc.her] [npc.cock] к "+getHersWithDildoHandling("npc2")+"."));
					break;
				case SUB_RESISTING:
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					sb.append(UtilText.returnStringAtRandom(
							" Мягко подаваясь [npc2.her] собственным [npc2.hips] в ответ, [npc2.name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(mirror)] движения [npc.name] и мягко [npc2.verb(rub)] [npc2.her] [npc2.cock] против "+getHersWithDildoHandling("npc")+".",
							" Отвечая мягким движением [npc2.her] собственного [npc2.hips], [npc2.name] [npc2.verb(start)] зеркально повторяя движения [npc.namePos], помогая мягко тереть [npc2.her] [npc2.cock] вверх и вниз по "+getHersWithDildoHandling("npc")+"."));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							" Грубо подаваясь [npc2.her] собственным [npc2.hips] в ответ, [npc2.name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(mirror)] движения [npc.name] иforcefully [npc2.verb(grind)] [npc2.her] [npc2.cock] против "+getHersWithDildoHandling("npc")+".",
							" Responding with a violent buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to roughly grind [npc2.her] [npc2.cock] up and down over "+getHersWithDildoHandling("npc")+"."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					sb.append(UtilText.returnStringAtRandom(
							" Счастливая, [npc2.her] в ответ начинает бурно двигать своим [npc2.hips], пока [npc2.name] [npc2.verb(let)] [npc2.a_moan+], когда [npc2.she] [npc2.verb(mirror)] движения [npc.name], и охотно [npc2.verb(rub)] [npc2.her] [npc2.cock] на "+getHersWithDildoHandling("npc")+".",
							" В ответ на это [npc2.her] радостно дергает своим [npc2.hips], а [npc2.name] [npc2.verb(start)], повторяя движения [npc.namePos], помогая неистово тереть [npc2.her] [npc2.cock] вверх и вниз по "+getHersWithDildoHandling("npc")+"."));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							" [npc2.her] в ответ выгибает свой [npc2.hips], пока [npc2.name] [npc2.verb(let)] [npc2.a_moan+], когда [npc2.she] [npc2.verb(mirror)] движения [npc.name] и [npc2.verb(rub)] [npc2.her] [npc2.cock] против "+getHersWithDildoHandling("npc")+".",
							" Отвечая мощным [npc2.her] своего собственного [npc2.hips], [npc2.name] [npc2.verb(start)], зеркально повторяя движения [npc.namePos], помогая тереться [npc2.her] [npc2.cock] вверх и вниз по "+getHersWithDildoHandling("npc")+"."));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь отстраниться, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(plead)] за [npc.name], чтобы уйти от [npc2.herHim] и оставить [npc2.her] [npc2.cock] в покое.",
							" Отвечая на это неистовым отстранением от нежелательного напора [npc.namePos], [npc2.name] [npc2.verb(start)], умоляя, чтобы её оставили в покое, и одновременно пытаясь оттянуть [npc2.her] [npc2.cock] от [npc.herHim]."));
					break;
			}
			
			return sb.toString();
		}
		
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		StringBuilder sb = new StringBuilder();
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] с энтузиазмом [npc2.verb(respond)] в ответ, и [npc2.verb(let)] вытаскивает [npc2.a_moan+], чтобы [npc2.she] [npc2.verb(rub)] [npc2.her] собственный [npc2.cock] вверх и вниз по [npc.nameHers].",
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выталкивая [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] против [npc.nameHers].",
						" [npc2.Moaning] в восторге, [npc2.name] с жадностью [npc2.verb(thrust)] [npc2.her] [npc2.hips+], используя движения [npc.namePos], чтобы помочь втирать [npc2.her] собственный [npc2.cock] в "+getHersWithDildoHandling("npc")+"."));
				break;
			case SUB_RESISTING:
				sb.append(UtilText.returnStringAtRandom(
						" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] неистово [npc2.verb(beg)] за [npc.name], чтобы уйти от [npc2.him].",
						" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name] от паха [npc2.her].",
						" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрал(а) от [npc2.her] паха."));
				break;
			case SUB_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] отвечает тем же [npc2.verb(respond)], и [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] мягко [npc2.verb(rub)] [npc2.her] собственный(ую) [npc2.cock] вверх и вниз по "+getHersWithDildoHandling("npc")+".",
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, толкая вперед [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] против "+getHersWithDildoHandling("npc")+".",
						" [npc2.Moaning] от удовольствия, [npc2.name] [npc2.verb(thrust)] [npc2.her] [npc2.hips+], используя [npc.namePos] движения, чтобы помочь втирать [npc2.her] собственный(ую) [npc2.cock+] против "+getHersWithDildoHandling("npc")+"."));
				break;
			case DOM_GENTLE:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] счастливо отвечает тем же [npc2.verb(respond)], и [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] нежно [npc2.verb(rub)] [npc2.her] собственный(ую) [npc2.cock] вверх и вниз по паху [npc.namePos].",
						" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], и, осторожно толкая вперед [npc2.her] [npc2.hips], [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.cock] против [npc.nameHers].",
						" [npc2.Moaning] от удовольствия, [npc2.name] нежно [npc2.verb(push)] [npc2.her] [npc2.hips+], используя [npc.namePos] движения, чтобы помочь втирать [npc2.her] собственный(ую) [npc2.cock+] против "+getHersWithDildoHandling("npc")+"."));
				break;
			case DOM_ROUGH:
				sb.append(UtilText.returnStringAtRandom(
						" [npc2.Name] с силой [npc2.verb(respond)] навстречу, и [npc2.verb(let)] [npc2.a_moan+] как [npc2.she] грубо [npc2.verb(grind)] [npc2.her] собственный [npc2.cock] вверх и вниз по [npc.namePos] [npc.cock+].",
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо выталкивая [npc2.her] [npc2.hips], [npc2.she] с силой [npc2.verb(grind)] [npc2.her] [npc2.cock] против "+getHersWithDildoHandling("npc")+"",
						" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(thrust)] [npc2.her] [npc2.hips+] наружу, используя [npc.namePos] движения, чтобы помочь вжать [npc2.her] собственный [npc2.cock+] в [npc.her] пах."));
				break;
		}
		return sb.toString();
	}
	
	public static final SexAction FROTTING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное трение";
		}
		@Override
		public String getActionDescription() {
			return "Плавно скользите своим [npc.cock] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.returnStringAtRandom(
					"Издавая легкий [npc.moan] при каждом толчке [npc.her] [npc.hips], [npc.name] нежно [npc.verb(rub)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] серию тихих вздохов, пока [npc.she] мягко [npc.verb(rub)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"Скользя [npc.her] [npc.cock+] по [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] легкий [npc.moan] с каждым движением [npc.her] [npc.hips]."));

			sb.append(getTargetedCharacterResponse(this));
					
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Фроттинг";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать с энтузиазмом тереть свой [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] с каждым жадным толчком [npc.her] [npc.hips], [npc.name] страстно [npc.verb(rub)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] отчаянно [npc.verb(grind)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"С энтузиазмом растирая [npc.her] [npc.cock+] о [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+] с каждым неистовым движением [npc.her] [npc.hips]."));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый фроттинг";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать грубо тереть свой [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] с каждым мощным толчком [npc.her] [npc.hips], [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"Яростное растирание [npc.her] [npc.cock+] о [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+] с каждым мощным движением [npc.her] [npc.hips]."));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Фроттинг";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать тереть свой [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] при каждом толчке [npc.her] [npc.hips], [npc.name] [npc.verb(continue)] тереть [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] [npc.verb(rub)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"Тереть [npc.her] [npc.cock+] по [npc2.namePos] [npc2.cock+], при этом [npc.name] [npc.verb(let)] издавать [npc.a_moan+] при каждом движении [npc.her] [npc.hips]."));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction FROTTING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно тереться";
		}
		@Override
		public String getActionDescription() {
			return "Жадно три свой [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+] с каждым жадным толчком [npc.her] [npc.hips], [npc.name] страстно [npc.verb(rub)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"[npc.Name] [npc.verb(let)] серию похотливых [npc.moans], пока [npc.she] отчаянно [npc.verb(grind)] [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"С энтузиазмом растирая [npc.her] [npc.cock+] о [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+] с каждым неистовым движением [npc.her] [npc.hips]."));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
		
	};
	
	public static final SexAction FROTTING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться трению";
		}
		@Override
		public String getActionDescription() {
			return "Пытаться отстраниться от [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться оттянуть [npc.her] пах от [npc2.nameHers], но усилия [npc.her] оказываются напрасными, так как [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, удерживая [npc2.her] [npc2.cock+] мягко прижатыми к [npc.her] [npc.cock+].",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, после чего продолжает мягко тереть [npc2.her] [npc2.cock+] о [npc.her] [npc.cock+].",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] нежно [npc2.verb(rub)] [npc2.her] [npc2.cock+] друг о друга "+getHersWithDildoHandling("npc")+"."));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] пытается оттянуть [npc.her] от [npc2.nameHers], но усилия [npc.her] оказываются напрасными, так как [npc2.she] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] вперед, с силой прижимая [npc2.her] [npc2.cock+] к [npc.her] [npc.cock+].",
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, после чего продолжает грубо тереть [npc2.her] [npc2.cock+] о [npc.her] [npc.cock+].",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] с силой не [npc2.verb(grind)] [npc2.her] [npc2.cock+] друг о друга "+getHersWithDildoHandling("npc")+"."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					sb.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] пытается оттянуть [npc.her] от [npc2.nameHers], но усилия [npc.her] оказываются напрасными, так как [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, удерживая [npc2.her] [npc2.cock+] прижатым к [npc.her] [npc.cock+].",
							"При [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, после чего продолжает жадно тереть [npc2.her] [npc2.cock+] о [npc.her] [npc.cock+].",
							"В [npc.eyes] [npc.namePos] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.cock] от [npc2.nameHers], но [npc2.she] быстро [npc2.verb(shift)] позицию, игнорируя протесты [npc.her], пока [npc2.she] с энтузиазмом [npc2.verb(rub)] [npc2.her] [npc2.cock+] прижатый к "+getHersWithDildoHandling("npc")+"."));
					break;
			}
			
			return sb.toString();
		}
		
	};
	
	public static final SexAction FROTTING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Хватит тереться";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.cock+], оттяни свой [npc.cock+] от [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"Последним грубым толчком [npc.name] [npc.verb(pull)] [npc.her] пах от [npc2.namePos] [npc2.cock+], кладя конец фроттингу.",
							"Грубо перетирая [npc.her] [npc.cock+] по [npc2.namePos] [npc2.cock] в последний раз, [npc.name] затем [npc.verb(pull)] назад, завершая это грубое трение."));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							"С последним рывком [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] [npc.her] пах от [npc2.namePos] [npc2.cock+], кладя конец фроттингу.",
							"В последний раз потерев [npc.her] [npc.cock+] о [npc2.namePos] [npc2.cock], [npc.name] затем [npc.verb(pull)] назад, завершая фроттинг."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							" Хотя и радуясь тому, что [npc2.her] [npc2.cock] освободился, [npc2.name] [npc2.verb(continue)] плачет и слабо сопротивляется [npc.name], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил [npc2.herHim] в покое.",
							" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], чтобы бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.cock+] прочь от [npc.name]."));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] назад, сигнализируя о [npc2.her] желании получить больше внимания.",
							" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
					break;
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction PREHENSILE_FROTTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Скрученный фроттинг";
		}
		@Override
		public String getActionDescription() {
			return "Используй свой цепкий член, чтобы обхватить и помассировать [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE);
		}
		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.DOM_ROUGH) {
				return UtilText.returnStringAtRandom(
						"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], используя цепкость [npc.her] [npc.cock], чтобы плотно обвиться вокруг [npc2.namePos] [npc2.cock+], прежде чем скрутиться кольцами, чтобы грубо массировать и сдавливать его.",
						"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(focus)], контролируя цепкий [npc.cock] [npc.her]. Плотно обвивая его вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(grin)], когда [npc.she] затем [npc.verb(coil)] его, чтобы грубо массировать [npc2.her] [npc2.cock+], заставляя [npc2.herHim] издавать [npc2.a_moan+].",
						"[npc.Name] [npc.verb(grin)] в восторге, пока [npc.she] [npc.verb(use)] [npc.her] цепкий член, чтобы грубо массировать и ласкать [npc2.namePos] [npc2.cock+], с силой дергая им ритмичными волнообразными движениями.",
						"С помощью [npc.a_moan+], [npc.name] [npc.verb(focus)] на управлении цепким [npc.cock] [npc.her]. Сильно обхватывая [npc2.namePos] [npc2.cock+], [npc.she] [npc.verb(trap)] и [npc.verb(milk)] его в кольцах сжимающего [npc.cock] [npc.her].");
			}
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)] на использовании цепкости [npc.her] [npc.cock], чтобы обвиться вокруг [npc2.namePos] [npc2.cock+], прежде чем свернуться в кольца, чтобы массировать и сдавливать его.",
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(focus)] на управлении цепким [npc.cock] [npc.her]. Обволакивая [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(grin)], когда [npc.she] затем [npc.verb(coil)] его вверх, чтобы массировать [npc2.her] [npc2.cock+], заставляя [npc2.herHim] издавать [npc2.a_moan+].",
					"[npc.Name] [npc.verb(grin)] в восторге, пока [npc.she] [npc.verb(use)] [npc.her] цепкий член, чтобы массировать и ласкать [npc2.namePos] [npc2.cock+], дергая им ритмичными волнообразными движениями.",
					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] в управлении цепким [npc.cock] [npc.her]. Оборачивая его вокруг [npc2.namePos] [npc2.cock+], [npc.she] [npc.verb(trap)] и [npc.verb(milk)] его в кольцах [npc.her], сжимающих [npc.cock].");
		}
	};

	public static final SexAction SHEATH_FROTTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Пристыковка препуция";
		}
		@Override
		public String getActionDescription() {
			return "Используй свой член, чтобы дразнить и проникать в препуций, удерживающий [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasPenisModifier(PenetrationModifier.SHEATHED);
		}
		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.DOM_ROUGH) {
				String start = UtilText.returnStringAtRandom(
						"Выпуская [npc.a_moan+], [npc.name] [npc.verb(ram)] кончик [npc.her] [npc.cock] в препуций, окружающий [npc2.namePos] [npc2.cock+], обвивая его и дразня чувствительную кожу внутри препуция.",
						"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reach)] down to [npc2.namePos] [npc2.cock+]. Guiding it to [npc.her] own [npc.cock+], [npc.name] [npc.verb(grin)] as [npc.she] then forcefully [npc.verb(thrust)] it past [npc2.her] [npc2.cock+] and stuffs it into [npc2.her] sheath, causing [npc2.name] to let out a startled [npc2.moan].",
						"[npc.Name] [npc.verb(grin)] от восторга, когда [npc.she] [npc.verb(guide)] [npc.her] член к отверстию препуция [npc2.namePos], прежде чем вогнать его внутрь. [npc.NamePos] [npc.cock] плотно прилегает к "+getHersWithDildoHandling("npc2")+", и [npc.she] [npc.verb(start)], чтобы с силой массировать и ласкать оба члена, ритмично подергивая препуций.",
						"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(ram)] [npc.her] [npc.cock] в [npc2.namePos] чехол, на мгновение дразня его резкими круговыми движениями, прежде чем войти глубже.");
				String middle1 = UtilText.returnStringAtRandom(
					"[npc.Name] доминантно [npc.verb(work)] [npc.her] [npc.cock] глубже в чехол, скользя им вокруг основания [npc2.her] [npc2.cock+].",
//					"Rather than thrust deeply into [npc2.her] sheath, [npc.name] instead circle [npc.her] cock around "+getHersWithDildoHandling("npc2")+".",
					"Потянувшись вниз, [npc.name] крепко [npc.verb(grasp)] [npc2.her] чехол, после чего использует его как рукав, чтобы оттянуть [npc.herself].",
					"Потянувшись вниз, [npc.name] крепко [npc.verb(grasp)] [npc2.her] чехол, прежде чем натянуть его еще дальше на [npc.her] [npc.cock]."
				);
				String middle2 = UtilText.returnStringAtRandom(
					"Ошеломленный стимуляцией от чувствительной оболочки [npc2.her], весь [npc2.name] [npc2.is] только и может, что содрогаться и [npc2.moan].",
					"Внутренняя поверхность чехла [npc2.namePos] невероятно чувствительна, и [npc.name] [npc.verb(feel)] каждое содрогание и подергивание, когда [npc2.namePos] [npc2.cock] отзывается на это ощущение.",
					"[npc2.Name] [npc2.verb(shudder)] и [npc2.moansVerb], пока [npc2.she] [npc2.verb(feel)] всё больше и больше [npc.namePos] [npc.cock+], втискиваемого в [npc2.her] чехол."
				);
				String end = UtilText.returnStringAtRandom(
					"После нескольких последних толчков, [npc.name] небрежно [npc.verb(yank)] из [npc2.namePos] чехла и грубо [npc.verb(slap)] [npc.her] [npc.cock] против "+getHersWithDildoHandling("npc2")+" прежде чем снова начать фроттинг и толчки против него.",
					"Издав [npc.a_moan+] и совершив последний [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] из [npc2.namePos] препуция и [npc.verb(resume)] фроттинг.",
					"Спустя некоторое время ощущения становятся слишком интенсивными. С [npc.moan+], [npc.name] [npc.verb(yank)] [npc.her], [npc.cock] выходит обратно из препуция, окружающего [npc2.namePos] [npc.cock+], и после нескольких движений, [npc.she] [npc.verb(line)] [npc.her] [npc.cock+], чтобы снова тереться и трахаться об него.",
					"Сделав несколько последних движений по [npc2.namePos] препуцию поверх [npc.her] [npc.cock], [npc.she] небрежно [npc.verb(pull)] из тесного препуция и [npc.verb(realign)] [npc.her] [npc.cock+], чтобы возобновить фроттинг."
				);
				return String.join(" ",start, middle1, middle2,end);
				
			} else {
				String start = UtilText.returnStringAtRandom(
						"Издавая [npc.a_moan+], [npc.name] [npc.verb(slip)] кончик [npc.her] [npc.cock] в препуций, окружающий [npc2.namePos] [npc2.cock+], описывая круги и дразня чувствительную кожу внутри препуция.",
						"[npc.Name] [npc.verb(let)] извлекает [npc.a_moan+], как [npc.she], и [npc.verb(reach)] опускает его до [npc2.namePos] [npc2.cock+]. Направляя его в собственную [npc.cock+] [npc.her], [npc.name] [npc.verb(grin)], как [npc.she], а затем [npc.verb(slip)] его сквозь [npc2.her] [npc2.cock+] прямо в [npc2.her], заставляя [npc2.herHim] издать [npc2.a_moan+].",
						"[npc.Name] [npc.verb(grin)] в восторге, когда [npc.she] [npc.verb(guide)] [npc.her] член к отверстию [npc2.namePos] препуция, прежде чем скользнуть внутрь. [npc.NamePos] [npc.cock] плотно прилегает к "+getHersWithDildoHandling("npc2")+", и [npc.name] [npc.verb(start)], чтобы массировать и ласкать оба члена, ритмично подергивая препуцией.",
						"[npc.Name] [npc.verb(let)] извлекает [npc.a_moan+] как [npc.she] [npc.verb(slip)] [npc.her] [npc.cock] в [npc2.namePos], дразня его медленными круговыми движениями, прежде чем скользнуть глубже.");
				String middle1 = UtilText.returnStringAtRandom(
					"[npc.Name] медленно [npc.verb(work)] [npc.her] [npc.cock] глубже в препуций, скользя им вокруг основания [npc2.her] [npc2.cock+].",
					"Вместо того чтобы глубоко входить в [npc2.her] препуций, [npc.name] вместо этого вращает [npc.her] член вокруг "+getHersWithDildoHandling("npc2")+".",
					"Потянувшись вниз, [npc.name] крепко [npc.verb(grasp)] [npc2.her] чехол, после чего использует его как рукав, чтобы оттянуть [npc.herself].",
					"Потянувшись вниз, [npc.name] крепко [npc.verb(grasp)] [npc2.her] препуций, прежде чем скользнуть им глубже по [npc.her] [npc.cock]. Вместо толчков, [npc.name] замирает, медленно растирая и массируя [npc2.namePos] член."
				);
				String middle2 = UtilText.returnStringAtRandom(
					"Ошеломленный стимуляцией от чувствительной оболочки [npc2.her], весь [npc2.name] [npc2.is] только и может, что содрогаться и [npc2.moan].",
					"Внутренняя поверхность чехла [npc2.namePos] невероятно чувствительна, и [npc.name] [npc.verb(feel)] каждое содрогание и подергивание, когда [npc2.namePos] [npc2.cock] отзывается на это ощущение.",
					(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
						?"С похотливым [npc2.moan], [npc2.name] [npc2.verb(press)] ближе к [npc.name], мягко толкаясь, чтобы загнать как можно больше [npc.her] [npc.cock+] внутрь [npc2.her] препуция."
						:"")
				);
				String end = UtilText.returnStringAtRandom(
					"После нескольких последних толчков, [npc.name] осторожно [npc.verb(pull)] из [npc2.namePos] препуция и игриво [npc.verb(slap)] [npc.her] [npc.cock] о "+getHersWithDildoHandling("npc2")+" прежде чем снова начать фроттинг и толчки против него.",
					"Со вздохом и еще несколькими движениями внутри чехла [npc2.her], [npc.name] [npc.verb(pull)] из чехла [npc2.namePos] и [npc.verb(resume)], трусь об него.",
					"Спустя некоторое время ощущения становятся слишком интенсивными. С [npc.moan], [npc.name] [npc.verb(pull)] [npc.her] [npc.cock] обратно из чехла, окружающего [npc2.namePos] [npc.cock+], и сделав несколько движений, [npc.she] [npc.verb(line)] [npc.her] [npc.cock+], чтобы снова тереться и насаживаться на него.",
					"Сделав несколько последних движений чехлом [npc2.namePos] по [npc.her] [npc.cock], [npc.she] осторожно [npc.verb(tug)] из тесного чехла и [npc.verb(realign)] [npc.her] [npc.cock+], чтобы возобновить фроттинг."
				);
				return String.join(" ",start, middle1, middle2,end);
			}
		}
	};
	
	public static final SexAction PREHENSILE_FROTTING_DOUBLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Двойной фроттинг";
		}
		@Override
		public String getActionDescription() {
			return "Переплети свой хватательный член с [npc2.hers].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasPenisModifier(PenetrationModifier.PREHENSILE)
					&& Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.PREHENSILE)
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Выпуская [npc.a_moan+], [npc.name] [npc.verb(concentrate)], используя хватательность [npc.her] [npc.cock], чтобы обвиться вокруг [npc2.namePos] [npc2.cock+]. [npc2.Name] [npc2.verb(match)] [npc.name], сворачивая [npc2.her] [npc2.cock] в соответствии с [npc.hers], прежде чем [npc.name] [npc.verb(start)], чтобы медленно массировать и тереться о него.",
							
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(focus)] on controlling [npc.her] prehensile [npc.cock]. Wrapping it around [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(grin)] as [npc.she] then [npc.verb(entice)] [npc2.namePos] [npc2.cock] by stroking and curling around it with [npc.her] own. [npc2.Name] eagerly [npc2.verb(wrap)] [npc2.her] own prehensile [npc2.cock] around [npc.hers], returning the favour with teasing squeezes of [npc2.her] own.",
							
					"[npc.Name] [npc.verb(grin)] in delight as [npc.she] [npc.verb(use)] [npc.her] prehensile cock to massage and stroke [npc2.namePos] [npc2.cock+], while [npc2.name] [npc2.verb(use)] [npc2.her] own prehensile shaft to mimic [npc.her] movements. Tugging at it with [npc.her] undulating [npc.cock], [npc.name] [npc.verb(settle)] on a comfortable rhythm with [npc2.name].",
							
					"С [npc.a_moan+], [npc.name] [npc.verb(focus)] на управлении хватательным [npc.cock] [npc.her], обвивая им [npc2.namePos] [npc2.cock+]. Обладая хватательными пенисами, [npc.name] и [npc2.name] пытаются захватить и окутать член другого, сжимая и выжимая его в стягивающих кольцах.");
		}
	};
	
	public static final SexAction FROTTING_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeCreampied() {
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				new Exception().printStackTrace();
				return null;
			}
			
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return SexAreaPenetration.PENIS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			if(Main.sex.getCharactersHavingOngoingActionWith(performer, SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			GameCharacter target = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(!performer.hasPenisIgnoreDildo()) {
				return false;
			}
			
			boolean dicksTouching = Main.sex.getOngoingSexAreas(performer, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			if (!dicksTouching) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)!=OrgasmBehaviour.CREAMPIE
					&& !performer.isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(performer) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(performer)>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if((Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0)
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Принудительный фроттинг!";
					
				} else if(bodypart == Arm.class) {
					return "Фроттинг в объятиях!";
					
				} else if(bodypart == Leg.class) {
					return "Фроттинг, зажатый ногами!";
					
				} else if(bodypart == Tail.class) {
					return "Фроттинг, зажатый хвостом!";
					
				} else if(bodypart == Wing.class) {
					return "Фроттинг, зажатый крыльями!";
					
				} else if(bodypart == Tentacle.class) {
					return "Фроттинг, зажатый щупальцами!";
				}
			}
			return "Оргазм от фроттинга";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] использует [npc.her] выгодное положение, заставляя тебя тереться своим членом о [npc.herHim] в момент оргазма! Когда ты уже на самом пределе, у тебя нет времени попытаться оттолкнуть [npc.herHim]!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] крепко обвивает [npc.her] [npc.arms+] вокруг твоего низа, заставляя тебя тереться о [npc.herHim] в момент оргазма! Когда ты уже на самом пределе, у тебя нет времени попытаться вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] крепко обвивает [npc.her] [npc.legs+] вокруг твоего низа, заставляя тебя тереться о [npc.herHim] в момент оргазма! Когда ты уже на самом пределе, у тебя нет времени попытаться вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] крепко обвивает [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" вокруг твоего низа, заставляя тебя тереться о [npc.herHim] в момент оргазма! Когда ты уже на самом пределе, у тебя нет времени попытаться вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] крепко обвивает [npc.her] [npc.wingSize] [npc.wings] вокруг твоего тела, заставляя тебя тереться о [npc.herHim] в момент оргазма! Когда ты уже на самом пределе, у тебя нет времени попытаться вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] плотно обхватывает [npc.her] [npc.tentacles+] вокруг твоего низа, заставляя тебя тереться об [npc.herHim] в момент оргазма! Когда ты находишься на самом пике, у тебя нет времени попытаться вырваться из хватки [npc.her]!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			String returnString = "Ты достиг кульминации и больше не можешь сдерживать оргазм. Три свой член об [npc2.namePos], когда кончаешь.";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(getCharacterToBeCreampied())) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA || cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
//		@Override
//		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
//			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
//					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
//						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {
//
//				return Util.newArrayListOfValues(
//						CoverableArea.PENIS,
//						CoverableArea.VAGINA);
//			}
//			return null; 
//		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex();
		}
	};
	
	public static final SexAction FROTTING_SPH_SMALL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Унижение маленького члена";
		}
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionDescription() {
			return "Насмехаться над [npc2.name] из-за жалкого размера [npc2.her], прижимая свой крупный член к [npc2.hers].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getOngoingSexAreas(actor, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			return hasSmallPenis(target) && target.hasPenisIgnoreDildo() && !hasSmallPenis(actor) && dicksTouching;
		}
		@Override
		public String getDescription() {
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"Прижимая [npc.her] [npc.cock+] к [npc2.hers], [npc2.namePos] [npc2.cock+] оказывается явно короче.",
					"[npc.Name] [npc.verb(pause)], чтобы прижать [npc.her] [npc.cock+] к гораздо более маленькому [npc2.cock] [npc2.namePos].",
					"[npc.Name] [npc.verb(take)] сделать сверхдлинный толчок, растирая всю длину [npc.her] [npc.cock+] по [npc2.namePos] [npc2.cock+]"
			);
			String mid = UtilText.returnStringAtRandom(
					"Презрительно глядя на жалкий [npc2.cock] [npc2.her], [npc.name] [npc.verb(growl)], ",
					"Продолжая тереть [npc.her] [npc.cock] о жалкий [npc2.cock] [npc2.her], [npc.name] [npc.verb(decide)], чтобы по-настоящему [npc.verb(rub)] его, говоря: ",
					"[npc.Name] [npc.verb(give)] [npc2.him] с насмешливым смешком, говоря: "
			);
			String quote;
			switch (target.getPenisSize()) {
				case ZERO_MICROSCOPIC:
					quote =  UtilText.returnStringAtRandom(
						"Эта штука — практически клитор!",
						"Я не ожидала многого, но это просто жалко!",
						"Каково это — чувствовать, как твой крошечный клитор раздавливает настоящий член?",
						"Я едва чувствую этот жалкий бугорок.",
						"Тебе должно повезти, что ты хоть раз почувствовала <i>настоящий</i> член."
					);
					break;
				case ONE_TINY:
				default:
					quote =  UtilText.returnStringAtRandom(
						"Хорошая попытка, малютка, "+ (target.isFeminine()?"леди,":"парень,") + "но эта штука просто не дотягивает.",
						"<i>Это?</i> Это просто разочарование.",
						"Тебе должно повезти, что ты хоть раз почувствовала <i>настоящий</i> член.",
						"И это всё?",
						"Я не ожидала многого, но это просто жалко!",
						"«Миниатюрный», значит?", 
						"Спорим, тебе нравится смотреть, как твоим крошечным членом помыкают!"
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")]";
			return String.join(" ", intro, mid, quote);
		}
	};
	
	public static final SexAction FROTTING_SPH_NORMAL = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Унижение размером";
		}

		@Override
		public boolean isSadisticAction() {
			return true;
		}

		@Override
		public String getActionDescription() {
			return "Унизь [npc2.name] своим огромным членом.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getOngoingSexAreas(actor, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			
			float penisSizeRatio = (float)actor.getPenisRawSizeValue() / (float)target.getPenisRawSizeValue();
			boolean actorPenisSizeLarger = actor.getPenisSize().getMinimumValue() > target.getPenisSize().getMinimumValue();
			return (actorPenisSizeLarger || penisSizeRatio > 1.25)
				&& penisSizeRatio > 1.15
				&& !hasSmallPenis(target) && !hasSmallPenis(actor) 
				&& target.hasPenisIgnoreDildo() && dicksTouching;
		}

		@Override
		public String getDescription() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"Когда [npc.her] [npc.cock+] приставляют к [npc2.hers], [npc2.namePos] [npc2.cock+] кажется совсем коротким.",
					"[npc.Name] [npc.verb(pause)], чтобы прижать [npc.her] [npc.cock+] к [npc2.namePos] [npc2.cock], наглядно демонстрируя разницу в размерах.",
					"[npc.Name] [npc.verb(take)] сделать сверхдлинный толчок, растирая всю длину [npc.her] [npc.cock+] по [npc2.namePos] [npc2.cock+]"
			);
			String mid = UtilText.returnStringAtRandom(
					"Видя разницу в размерах между [npc.her] [npc.cock+] и [npc2.her], [npc.name] [npc.verb(laughs)], говоря ",
					"Продолжая тереть [npc.her] [npc.cock] о [npc2.her] [npc2.cock], [npc.name] [npc.verb(decide)], чтобы [npc.verb(rub)] его, говоря ",
					"[npc.Name] [npc.verb(give)] [npc2.him] с усмешкой, говоря "
			);
			String quote;
			switch (target.getPenisSize()) {
				case ZERO_MICROSCOPIC:
					quote =  UtilText.returnStringAtRandom(
						"Эта штука — практически клитор!",
						"Я не ожидала многого, но это просто жалко!",
						"Каково это — чувствовать, как твой крошечный клитор раздавливает настоящий член?",
						"Я едва чувствую этот жалкий бугорок.",
						"Тебе должно повезти, что ты хоть раз почувствовала <i>настоящий</i> член."
					);
					break;
				case ONE_TINY:
					quote =  UtilText.returnStringAtRandom(
						"Хорошая попытка, малютка, "+ (target.isFeminine()?"леди,":"парень,") + "но эта штука просто не дотягивает.",
						"<i>Это?</i> Это просто разочарование.",
						"Тебе должно повезти, что ты хоть раз почувствовала <i>настоящий</i> член.",
						"И это всё?",
						"Я не ожидала многого, но это просто жалко!",
						"«Миниатюрный», значит?", 
						"Спорим, тебе нравится смотреть, как твоим крошечным членом помыкают!"
					);
					break;
				case TWO_AVERAGE:
					quote =  UtilText.returnStringAtRandom(
						"Неплохо, но теперь ты играешь с крупными "+(actor.isFeminine()?"девчонками":"пацанами")+" настоящими.",
						"Это еще ничего, взгляни хорошенько на <i>это</i>!",
						"Взгляни хорошенько на настоящего "+(actor.isFeminine()?"женщину":"мужчину")+" член!",
						"Тебе должно повезти, что ты хоть раз почувствовала <i>настоящий</i> член.",
						"'Средний' — это не повод для гордости.",
						"И это всё?",
						"'Средний'? У бесов члены и то больше."
					);
					break;
				case FIVE_ENORMOUS:
				case SIX_GIGANTIC:
				case SEVEN_STALLION:
					quote = UtilText.returnStringAtRandom(
						"Неплохо, но теперь ты играешь с крупными "+(actor.isFeminine()?"девчонками":"пацанами")+" настоящими.",
						"Каким бы огромным ты ни был, всегда найдется кто-то покрупнее!",
						"Каким бы огромным ты ни был, всегда найдется кто-то получше!",
						"Каким бы огромным ты ни был, я все равно больше!",
						"Неплохо, но я видел и побольше.",
						"Нечасто тебе попадаются те, кто крупнее тебя, верно?"
					);
					break;
				case THREE_LARGE:
				case FOUR_HUGE:
				default:
					quote =  UtilText.returnStringAtRandom(
						"Неплохо, но теперь ты играешь с крупными "+(actor.isFeminine()?"девчонками":"пацанами")+" настоящими.",
						"Ты довольно крупный, но всегда найдется кто-то покрупнее!",
						"Ты довольно крупный, но всегда найдется кто-то получше!",
						"Ты довольно крупный, но я все равно больше!",
						"Неплохо, но я видел и побольше.",
						"Хороший член, но тебе еще расти и расти."
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")].";
			return String.join(" ", intro, mid, quote);
		}
	};
	
	public static final SexAction FROTTING_COMPLIMENT = new SexAction(
			SexActionType.SPEECH,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Восхищение членом";
		}
		@Override
		public String getActionDescription() {
			return "Комплимент [npc2.namePos] [npc2.cock], пока вы третесь об него своим.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			boolean dicksTouching = Main.sex.getOngoingSexAreas(actor, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);
			return !hasSmallPenis(target) && !hasSmallPenis(actor) 
				&& target.hasPenisIgnoreDildo() && dicksTouching;
		}
		@Override
		public String getDescription() {
			GameCharacter actor = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			String intro = UtilText.returnStringAtRandom(
					"Прижимая [npc.her] [npc.cock+] к [npc2.hers], [npc.name] [npc.verb(take)] мгновение, чтобы полюбоваться обоими членами.",
					"[npc.Name] [npc.verb(pause)], чтобы прижать [npc.her] [npc.cock+] к [npc2.namePos] [npc2.cock], делая несколько ласкающих движений.",
					"[npc.Name] [npc.verb(take)], чтобы сделать сверхдлинный толчок, проглаживая всю длину [npc.her] [npc.cock+] по [npc2.namePos] [npc2.cock+], смакуя скользкое удовольствие."
			);
			String mid = UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(compliment)] [npc2.him] с игривым смехом, говоря, ",
					"Продолжая тереть [npc.her] [npc.cock] о [npc2.her] [npc2.cock], [npc.name] [npc.verb(decide)], чтобы [npc.verb(butter)] [npc2.him] вверх, говоря, ",
					"[npc.Name] [npc.verb(give)] [npc2.him] с усмешкой, говоря, ",
					"С дерзкой ухмылкой, [npc.name] [npc.verb(give)] слегка постучать [npc2.her] [npc2.cock+] своим [npc.her], говоря, "
			);
			String quote;
			switch (target.getPenisSize()) {
				case FIVE_ENORMOUS:
				case SIX_GIGANTIC:
				case SEVEN_STALLION:
					quote = UtilText.returnStringAtRandom(
						"Хороший член, "+(actor.isFeminine()?"промах":"бро")+"!",
						"Ого! Он просто огромный!",
						"Я хочу натереть всё своё тело об этот монструозный член!",
						"Обожаю тереться о гигантские члены!",
						"Я отполирую каждый дюйм этого монстра!"
					);
					break;
				case THREE_LARGE:
				case FOUR_HUGE:
					quote =  UtilText.returnStringAtRandom(
						"Хороший член, "+(actor.isFeminine()?"промах":"бро")+"!",
						"Довольно крупный! Класс!",
						"Мясистый, отличный член!",
						"У тебя pretty большой член!",
						"Класс!",
						"Обожаю смотреть на огромные члены!",
						"Обожаю тереться об огромный член!"
					);
					break;
				case TWO_AVERAGE:
				default:
					quote =  UtilText.returnStringAtRandom(
						"Хороший член, "+(target.isFeminine()?"промах":"бро")+"!",
						"Отличный размер!",
						"В самый раз!",
						"Наконец-то! Член нормального размера!",
						"Просто отличный член!",
						"Этот — <i>проооосто</i> в самый раз!",
						"Так приятно тереться о классный член!"
					);
					break;
			}
			quote = "[npc.speech(" + quote + ")]";
			String response;
			if(Main.sex.getSexPace(target)==SexPace.SUB_RESISTING) {
				response = UtilText.returnStringAtRandom(
						"<br/>Отчаянно пытается, но не может оторваться от [npc.namePos] [npc.cock], [npc2.name] лихорадочно [npc2.verb(beg)], чтобы [npc.name] мог(ла) уйти от [npc2.him].",
						"<br/>[npc2.A_sob+] вырывается из между [npc2.namePos], [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name] от паха [npc2.her].",
						"<br/>[npc2.Sobbing] в смятении, со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] убрал(а) его от [npc2.her] паха.");
				
			} else {
				response = UtilText.returnStringAtRandom(
						"<br/>[npc2.Name] [npc2.verb(continue)], толкаясь и втираясь в [npc.name], с небольшим дополнительным рвением от подбадривания [npc.her].",
						"<br/>Подгоняемый подбадриванием [npc.her], [npc2.name] с энтузиазмом [npc2.verb(buck)] против [npc.her] [npc.cock+]",
						"<br/>Воодушевленный дразнящим комплиментом [npc.her], [npc2.name] [npc2.verb(give)] [npc.him] несколькими особенно сильными толчками.",
						"<br/>[npc2.Name] немного [npc2.verb(blush)] от похвалы [npc.her], и [npc2.verb(press)] ближе к [npc.him].",
						"<br/>[npc2.Name] [npc2.verb(answer)] [npc.her] похвалу с [npc2.moan], прижимаясь ближе, чтобы тереть [npc2.her] член о "+getHersWithDildoHandling("npc")+" немного сильнее.");
			}
			return String.join(" ", intro, mid, quote, response);
		}
	};
}
