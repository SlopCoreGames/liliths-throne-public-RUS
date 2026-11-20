package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.4.3.2
 * @version 0.4.4.1
 * @author Innoxia
 */
public class FingerFinger {
	
	public static final SexAction HAND_HOLDING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Начать удерживать рукой";
		}
		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.hands].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Желая еще большего физического контакта с [npc2.name], [npc.name] [npc.verb(reach)] и нежно [npc.verb(take)] [npc2.her] [npc2.hands].",
							"Выпустив [npc.a_moan+], [npc.name] [npc.verb(reach)] и нежно [npc.verb(take)] [npc2.namePos] [npc2.hands]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Желая лучше контролировать движения [npc2.namePos], [npc.name] [npc.verb(reach)] и грубо [npc.verb(grab)] [npc2.her] [npc2.hands].",
							"Выпустив [npc.a_moan+], [npc.name] [npc.verb(reach)] и грубо [npc.verb(grab)] [npc2.namePos] [npc2.hands]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Желая еще большего физического контакта с [npc2.name], [npc.name] [npc.verb(reach)] и жадно [npc.verb(take)] [npc2.her] [npc2.hands].",
							"Выпустив [npc.a_moan+], [npc.name] [npc.verb(reach)] и жадно [npc.verb(take)] [npc2.namePos] [npc2.hands]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Желая еще большего физического контакта с [npc2.name], [npc.name] [npc.verb(reach)] и [npc.verb(take)] [npc2.her] [npc2.hands].",
							"Выпустив [npc.a_moan+], [npc.name] [npc.verb(reach)] и [npc.verb(take)] [npc2.namePos] [npc2.hands]."));
					break;
				case SUB_RESISTING:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Счастливая, в ответ обхватив [npc.namePos] [npc.hand] своими [npc2.her] [npc2.fingers+], [npc2.name] [npc2.verb(let)] нежный вздох.",
								" В ответ на это [npc2.name] нежно обвивает [npc2.her] [npc2.fingers+] вокруг [npc.namePos] [npc.hand], счастливо [npc2.verb(engage)] в [npc.namePos] руке."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Грубо сжимая в ответ [npc.namePos] [npc.hand] своими [npc2.her] [npc2.fingers+], [npc2.name] [npc2.verb(let)] доминантный рык.",
								" В ответ [npc2.name] крепко сжимает [npc2.her] [npc2.fingers+] вокруг [npc.namePos] [npc.hand], что примерно [npc2.verb(engage)] в [npc.namePos] руке."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Счастливая, в ответ обхватив [npc.namePos] [npc.hand] своими [npc2.her] [npc2.fingers+], [npc2.name] [npc2.verb(let)] радостный [npc2.moan].",
								" В ответ на это [npc2.name] с восторгом обхватывает [npc2.her] [npc2.fingers+] вокруг [npc.namePos] [npc.hand], счастливо [npc2.verb(engage)] в [npc.namePos] руке, которая их держит."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" В ответ обхватив [npc.namePos] [npc.hand] своими [npc2.her] [npc2.fingers+], [npc2.name] [npc2.verb(let)] [npc2.a_moan+].",
								" В ответ обхватив [npc.namePos] [npc.hand] своими [npc2.her] [npc2.fingers+], [npc2.name] [npc2.verb(engage)] в [npc.namePos] руке."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь оттянуть [npc2.her] [npc2.hand] от [npc.nameHers], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(beg)] на свободу.",
								" В ответ на это, отчаянно отпрянув от нежелательного напора [npc.namePos], [npc2.name] [npc2.verb(start)], умоляя оставить её в покое и одновременно пытаясь оттянуть [npc2.her] [npc2.hand] от [npc.nameHers]."));
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
							" [npc2.Name] с энтузиазмом [npc2.verb(respond)] в ответ, и [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(grip)] [npc.namePos] [npc.hand+] в [npc2.her] руках.",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] жадно [npc2.verb(grip)] [npc.namePos] [npc.hand+].",
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(grip)] [npc.namePos] [npc.hand+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах оттянуть [npc2.her] [npc2.hand] от [npc.nameHers], [npc2.name] в отчаянии [npc2.verb(beg)], чтобы [npc.name] поскорее выбралась из рук [npc2.herHim].",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.hand] от [npc.nameHers].",
							" [npc2.Sobbing] в смятении, со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] отпустил [npc2.her] [npc2.hand]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(respond)] в ответ, и [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(grip)] [npc.namePos] [npc.hand+] в [npc2.her] руках.",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(grip)] [npc.namePos] [npc.hand+].",
							" Издавая [npc2.a_moan], [npc2.name] [npc2.verb(respond)], сжимая [npc.namePos] [npc.hand+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с удовольствием [npc2.verb(respond)] в ответ, и [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] мягко [npc2.verb(hold)] [npc.namePos] [npc.hand+] в [npc2.her] руках.",
							" [npc2.A_moan+] медленно вытекает из между [npc2.namePos] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(hold)] [npc.namePos] [npc.hand+].",
							" Издавая [npc2.a_moan], [npc2.name] [npc2.verb(respond)], мягко сжимая [npc.namePos] [npc.hand+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] властно [npc2.verb(respond)] в ответ, и [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(squeeze)] [npc.namePos] [npc.hand+] в [npc2.her] руках.",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] грубо [npc2.verb(squeeze)] [npc.namePos] [npc.hand+].",
							" Издавая [npc2.a_moan], [npc2.name] [npc2.verb(respond)], с силой сжимая [npc.namePos] [npc.hand+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction HAND_HOLDING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежное удерживание рукой";
		}

		@Override
		public String getActionDescription() {
			return "Нежно возьми [npc2.namePos] [npc2.hand+] в свои руки.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая легкий [npc.moan], [npc.name] нежно [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] серию тихих вздохов, пока [npc.she] мягко [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+].",
					"Мягко обхватывая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] легкий [npc.moan]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Нежное удержание рукой";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью возьми [npc2.namePos] [npc2.hand+] в свою руку.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] счастливо [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] серию [npc.moans+], пока [npc.she] с нетерпением [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+].",
					"С жадностью обхватывая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] [npc.moan+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Грубое удержание рукой";
		}

		@Override
		public String getActionDescription() {
			return "Грубо схвати [npc2.namePos] [npc2.hand+] в свою руку.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] с силой [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] серию [npc.moans+], пока [npc.she] грубо [npc.verb(clench)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+].",
					"Грубо обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] [npc.moan+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Нежное удержание рукой";
		}

		@Override
		public String getActionDescription() {
			return "Удерживать [npc2.namePos] [npc2.hand+] в своих.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] серию [npc.moans+], пока [npc.she] [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+].",
					"Обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] [npc.moan+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction HAND_HOLDING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадно сжимающая рука";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью возьми [npc2.namePos] [npc2.hand+] в свою руку.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] счастливо [npc.verb(squeeze)] [npc2.namePos] [npc2.hand+].",
					"[npc.Name] [npc.verb(let)] серию [npc.moans+], пока [npc.she] с нетерпением [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+].",
					"С жадностью обхватывая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand+], [npc.name] [npc.verb(let)] [npc.moan+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction HAND_HOLDING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляющаяся рука";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.hand] от [npc2.nameHers].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.hand] от [npc2.nameHers], но [npc.she] [npc.verb(fail)] вырваться из [npc2.her] грубой хватки.",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.hand] от [npc2.nameHers], но [npc.she] [npc.do] не удается вырваться из [npc2.her] грубой хватки.",
							"На глазах у [npc.namePos] [npc.eyes] начинают наворачиваться слезы, пока [npc.she] [npc.verb(fail)] попытаться оттянуть [npc.her] [npc.hand] от [npc2.nameHers]."));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.hand] от [npc2.nameHers], но [npc.she] [npc.verb(fail)] вырваться из [npc2.her] хватки.",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.hand] от [npc2.nameHers], но [npc.she] [npc.do] не удается вырваться из [npc2.her] хватки.",
							"На глазах у [npc.namePos] [npc.eyes] начинают наворачиваться слезы, пока [npc.she] [npc.verb(fail)] попытаться оттянуть [npc.her] [npc.hand] от [npc2.nameHers]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction HAND_HOLDING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Перестать держать рукой";
		}

		@Override
		public String getActionDescription() {
			return "Оттянуть свой [npc.hand+] от [npc2.nameHers].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С последним грубым сжатием, [npc.name] [npc.verb(release)] [npc.her] хватку на [npc2.namePos] [npc2.hand+], кладя конец этому удержанию рукой.",
							"Грубо сжав [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand] в последний раз, [npc.name] затем [npc.verb(release)] [npc.her] хватку, кладя конец этому грубому удержанию рукой."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С последним сжатием, [npc.name] [npc.verb(release)] [npc.her] хватка на [npc2.namePos] [npc2.hand+], кладя конец ручному ласканию.",
							"В последний раз сжав [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.hand], [npc.name] затем [npc.verb(release)] [npc.her] хватка, кладя конец ручному ласканию."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Хотя и радуясь тому, что [npc2.her] [npc2.hand] освободились, [npc2.name] [npc2.verb(continue)] плачет и слабо сопротивляется [npc.name], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое.",
							" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] в попытках сопротивляться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] от [npc.name]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.hand] назад, сигнализируя о [npc2.her] желании получить еще больше внимания.",
							" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
