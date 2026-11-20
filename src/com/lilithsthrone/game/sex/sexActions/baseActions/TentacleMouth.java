package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class TentacleMouth {
	
	// Character performing action is receiving the face-fucking:
	
	public static final SexAction TENTACLE_THROAT_FUCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дать сосать щупальцу";
		}
		@Override
		public String getActionDescription() {
			return "Просунуть свой [npc.tentacle+(true)] в [npc2.namePos] горло и заставить [npc2.herHim] отыграть оральный секс на нем.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно толкнуться вперед, погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.lips+], и медленным, уверенным движением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.lips+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно вонзиться вперед, заталкивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.lips+], и резким толчком [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] горло."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tentacle+(true)] в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.lips+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] по поверхности [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], прежде чем рвануться вперед, вонзая [npc.her] [npc.tentacle+(true)] в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tentacleTip+] [npc.her] [npc.tentacle(true)] между [npc2.namePos] [npc2.lips+], и легким толчком [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], когда [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tentacle+(true)], а затем плавно подталкивая [npc2.her] голову вперед, чтобы вогнать его еще глубже в [npc2.her] горло.",
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно подталкивая [npc2.her] голову вперед, погружая [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] горло."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], когда [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tentacle+(true)], а затем яростно толкая [npc2.her] голову вперед, чтобы загнать его еще глубже в [npc2.her] горло.",
								" Издавая булькающий [npc2.moan], [npc2.name] [npc2.verb(push)] [npc2.her] вперед, заставляя [npc.namePos] [npc.tentacle+(true)] проникать еще глубже в [npc2.her] горло."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tentacle+(true)], а затем жадно толкая [npc2.her] вперед, чтобы вонзить его еще глубже в [npc2.her] горло.",
								" Издавая булькающий [npc2.moan], [npc2.name] с жадностью [npc2.verb(bob)] [npc2.her] вперед, проталкивая [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] горло."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tentacle+(true)], а затем толкая [npc2.her] вперед, чтобы вонзить его еще глубже в [npc2.her] горло.",
								" Издавая булькающий [npc2.moan], [npc2.name] [npc2.verb(bob)] [npc2.her] вперед, проталкивая [npc.namePos] [npc.tentacle+(true)] еще глубже в [npc2.her] горло."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tentacle(true)], и, пока слезы текут по [npc2.her] [npc2.face], [npc2.she] тщетно [npc2.verb(try)], пытаясь отстраниться от этого нежеланного проникновения.",
								" [npc2.Name] тщетно [npc2.verb(try)], пытаясь вырваться из этого нежеланного проникновения, и со слезами, катящимися по [npc2.her], [npc2.face] [npc2.she] [npc2.verb(let)] издает булькающий, полный отчаяния крик."));
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
							" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" Булькающий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] вперед, [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" [npc2.Moaning] от восторга, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] вперед, чтобы помочь протолкнуть [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь — и безуспешно — вывернуться от [npc.namePos] [npc.tentacle(true)], [npc2.name] [npc2.verb(let)] булькающий [npc2.sob], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(pull)] [npc2.her] голову назад.",
							" Приглушенный [npc2.sob] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], когда [npc2.her] горло насилуется [npc.namePos] [npc.tentacle+(true)].",
							" [npc2.Sobbing] от мучения, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], пытаясь вытянуть [npc.her] [npc.tentacle(true)] из [npc2.her] горла."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" Булькающий [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] голову вперед, [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" [npc2.Moaning] от восторга, [npc2.name] [npc2.verb(thrust)] [npc2.her] вперед, чтобы помочь протолкнуть [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] нежно [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" Тихий, булькающий [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips+], и, медленно толкая [npc2.her] голову вперед, [npc2.she] [npc2.verb(help)], чтобы погрузить [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" [npc2.Moaning] от восторга, [npc2.name] медленно [npc2.verb(push)] [npc2.her] вперед, чтобы помочь погрузить [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(push)] [npc2.her] вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] властно [npc2.verb(force)] [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" Булькающий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо толкая [npc2.her] вперед, [npc2.she] с силой [npc2.verb(drive)] [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло.",
							" От восторга [npc2.Moaning], [npc2.name] грубо [npc2.verb(thrust)] [npc2.her] вперед, чтобы загнать [npc.namePos] [npc.tentacle+(true)] глубоко в [npc2.her] горло."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TENTACLE_THROAT_FUCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное посасывание щупальцем";
		}
		@Override
		public String getActionDescription() {
			return "Мягко вводите и выводите свой [npc.tentacle(true)] из [npc2.namePos] горла.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко проталкивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(start)] двигает им туда-сюда, издавая легкий [npc.moan] при каждом из [npc.her] нежных толчков.",
					"Медленно проталкивая [npc.her] [npc.tentacle+(true)] вниз по [npc2.namePos] горлу, [npc.name] мягко [npc2.verb(thrust)] его туда-сюда, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(receive)] рот [npc2.herHim].",
					"Скользя [npc.her] [npc.tentacle+(true)] вниз по [npc2.namePos] горлу, [npc.name] [npc.verb(let)] немного [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(tentacle-peg)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TENTACLE_THROAT_FUCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Щупальце всосано";
		}
		@Override
		public String getActionDescription() {
			return "Продолжайте вбивать свой [npc.tentacle+(true)] в [npc2.namePos] горло.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] горло, [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом из [npc.her] энергичных толчков.",
					"С энтузиазмом проталкивая [npc.her] [npc.tentacle+(true)] вниз по [npc2.namePos] горлу, [npc.name] с азартом [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] оральный секс от [npc2.herHim].",
					"Вбивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tentacle-fuck)] [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TENTACLE_THROAT_FUCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубое щупальце всосано";
		}
		@Override
		public String getActionDescription() {
			return "Грубо вбивайте свой [npc.tentacle+(true)] в [npc2.namePos] горло туда-сюда.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вбивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(start)] грубо толкая его туда-сюда, издавая [npc.a_moan+] при каждом из [npc.her] жестоких толчков.",
					"Яростно вонзая [npc.her] [npc.tentacle+(true)] в [npc2.namePos] горло, [npc.name] грубо [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(receive)] оральный секс от [npc2.herHim].",
					"С силой вонзая [npc.her] [npc.tentacle+(true)] глубоко в глотку [npc2.namePos], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she] [npc.verb(start)], чтобы грубо вбивать его туда и обратно, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(tentacle-fuck)] [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TENTACLE_THROAT_FUCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Щупальце всосано";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать трахать [npc2.namePos] в горло.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(start)] толкая его туда-сюда, издавая [npc.a_moan+] при каждом из [npc.her] толчков.",
					"Заталкивая [npc.her] [npc.tentacle+(true)] в [npc2.namePos] горло, [npc.name] грубо [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] [npc.verb(receive)] оральный секс от [npc2.herHim].",
					"[npc.her] [npc.tentacle+(true)] вонзается глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] качает его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] [npc.verb(tentacle-fuck)] [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TENTACLE_THROAT_FUCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно сосет щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Жадно вгоняй и выгоняй свой [npc.tentacle+(true)] в глотку [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] горло, [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом из [npc.her] энергичных толчков.",
					"С энтузиазмом проталкивая [npc.her] [npc.tentacle+(true)] вниз по [npc2.namePos] горлу, [npc.name] с азартом [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] оральный секс от [npc2.herHim].",
					"Вбивая [npc.her] [npc.tentacle+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы жадно качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tentacle-fuck)] [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TENTACLE_THROAT_FUCK_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться сосанию щупальца";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tentacle(true)] из глотки [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tentacle(true)] изо рта [npc2.namePos], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его, нежно удерживая, прежде чем мягко протолкнуть его обратно в глотку [npc2.her].",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его и осторожно [npc2.verb(force)] обратно в глотку [npc2.her].",
							"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tentacle(true)] из [npc2.namePos] [npc2.lips+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] нежно [npc2.verb(force)] [npc.her] [npc.tentacle+(true)] обратно в глотку [npc2.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tentacle(true)] изо рта [npc2.namePos], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] его, грубо удерживая, прежде чем агрессивно протолкнуть его обратно в глотку [npc2.her].",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его и грубо [npc2.verb(force)] обратно в [npc2.her] горло.",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.namePos] [npc2.lips+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.tentacle+(true)] обратно в [npc2.her] горло."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.tentacle(true)] изо рта [npc2.namePos], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепкую хватку, прежде чем жадно затолкнуть его обратно в [npc2.her] горло.",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его и с жадностью [npc2.verb(force)] обратно в [npc2.her] горло.",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tentacle(true)] от [npc2.namePos] [npc2.lips+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc.her] [npc.tentacle+(true)] обратно в [npc2.her] горло."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TENTACLE_THROAT_FUCK_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Вытащить свой [npc.tentacle+(true)] из горла [npc2.namePos] и прекратить оральный секс с [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо вырывая [npc.tentacle+(true)] [npc.her] изо рта [npc2.namePos], [npc.name] властно [npc.verb(slide)] [npc.tentacleTip+] вверх и вниз по [npc2.her] [npc2.lips+] в последний раз, прежде чем отстраниться.",
							"В последний раз глубоко вонзившись в глотку [npc2.name], [npc.name] затем [npc.verb(yank)] [npc.her] [npc.tentacle+(true)] обратно изо рта [npc2.her], завершая этот грубый минет щупальцем."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.tentacle(true)] изо рта [npc2.namePos], [npc.name] [npc.verb(rub)] [npc.tentacleTip] вверх и вниз по [npc2.her] [npc2.lips+] в последний раз, прежде чем отстраниться.",
							"В последний раз загнав [npc2.name] глубоко в глотку, [npc.name] затем [npc.verb(slide)] [npc.her] [npc.tentacle+(true)] обратно изо рта [npc2.her], завершая это фейсфакинг-безумие."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может не [npc2.verb(help)], но [npc2.verb(let)] [npc2.sob+], пока [npc.name] [npc.verb(pull)] из [npc2.her] горла, и [npc2.she] [npc2.verb(continue)] плачет и протестует, пока [npc2.she] слабо [npc2.verb(struggle)] против [npc.herHim].",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться и протестовать, слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(pull)] [npc2.her] от [npc.name]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.tentacle+(true)] из горла [npc2.her], жаждая еще большего внимания со стороны [npc.her].",
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Character performing action is doing the face-fucking:
	
	public static final SexAction USING_TENTACLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Засунуть [npc2.namePos] [npc2.tentacle+(true)] себе в горло и отсосать его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] медленно [npc.verb(guide)] его к [npc.her] [npc.lips+], издавая легкий [npc.moan], прежде чем широко раскрыть рот и мягко заглотить его глубоко в [npc.her] горло.",
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] его к приоткрытому [npc.her] [npc.lips], после чего медленно подавая [npc.her] голову вперед и осторожно заглатывая его глубоко в [npc.her] горло."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] жадно [npc.verb(guide)] его к приоткрытому [npc.her] [npc.lips+], издавая [npc.a_moan+], прежде чем широко раскрыть рот и алчно заглотить его глубоко в [npc.her] горло.",
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] нетерпеливо [npc.verb(line)] его к приоткрытому [npc.her] [npc.lips], после чего резко подавая [npc.her] голову вперед и с жадностью заглатывая его глубоко в [npc.her] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] грубо [npc.verb(yank)] его к [npc.her] [npc.lips+], издавая [npc.a_moan+], прежде чем широко раскрыть рот и с силой заглотить его глубоко в [npc.her] горло.",
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] неистово [npc.verb(yank)] его к приоткрытому [npc.her] [npc.lips], после чего резко подавая [npc.her] голову вперед и грубо втискивая его глубоко в [npc.her] горло."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(guide)] его к [npc.her] [npc.lips+], издавая [npc.a_moan+], прежде чем широко раскрыть рот и заглотить его глубоко в [npc.her] горло.",
							"Схватив [npc2.namePos] [npc2.tentacle(true)], [npc.name] [npc.verb(line)] его до [npc.her] приоткрытого [npc.lips], затем дернув [npc.her] головой вперед и заглотив его глубоко в [npc.her] горло."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ мягкий [npc2.moan], прежде чем осторожно протолкнуть [npc2.her] [npc2.tentacle(true)] вперед и начать ритмично трахать щупальцем [npc.namePos] горло.",
								" С мягким [npc2.moan], [npc2.name] нежно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, погружая его глубоко в [npc.namePos] горло, пока [npc2.she] [npc2.verb(start)] ритмично трахает [npc.herHim] щупальцем."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], прежде чем жадно протолкнуть [npc2.her] [npc2.tentacle(true)] вперед и начать ритмично трахать щупальцем [npc.namePos] горло.",
								" С [npc2.a_moan+], [npc2.name] счастливо [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, погружая его глубоко в [npc.namePos] горло, пока [npc2.she] [npc2.verb(start)] ритмично трахает [npc.herHim] щупальцем."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], прежде чем попытаться напомнить [npc.name], кто здесь главный, яростно толкнув [npc2.her] [npc2.tentacle(true)] вперед и начав грубо трахать щупальцем [npc.her] горло.",
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tentacle(true)], заталкивая его глубоко в [npc.namePos] горло, пока [npc2.she] с силой [npc2.verb(start)] щупальцевым трахом [npc.her] лицо."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ [npc2.verb(let)] [npc2.a_moan+], прежде чем толкнуть [npc2.her] [npc2.tentacle(true)] вперед и начать ритмично щупальцевым трахом [npc.namePos] горло.",
								" С [npc2.a_moan+], [npc2.name] счастливо [npc2.verb(thrust)] [npc2.her] [npc2.tentacle(true)] вперед, погружая его глубоко в [npc.namePos] горло, пока [npc2.she] [npc2.verb(start)] ритмично трахает [npc.herHim] щупальцем."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] с силой [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.tentacle+(true)] из [npc.her] горла.",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] с силой [npc.verb(force)] [npc2.her] [npc2.tentacle(true)] глубоко в [npc.her] горло."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(tentacle-fuck)] [npc.her] лицо.",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], когда [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло.",
							" В восторге от [npc2.Moaning], [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tentacle+(true)] как можно глубже в [npc.namePos] горло."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттащить [npc2.her] [npc2.tentacle(true)] от [npc.namePos] [npc.lips], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заталкивая [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.her] горло.",
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] оттащить [npc2.her] [npc2.tentacle(true)] от [npc.namePos] горла."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло, издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(tentacle-fuck)] [npc.her] лицо.",
							" [npc2.A_moan+] выплывает изо рта [npc2.namePos], пока [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло.",
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tentacle+(true)] как можно глубже в [npc.namePos] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(tentacle-fuck)] [npc.her] лицо.",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], пока [npc2.she] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло.",
							" В восторге от [npc2.Moaning], [npc2.name] с силой [npc2.verb(drive)] [npc2.her] [npc2.tentacle+(true)], заталкивая его как можно глубже в [npc.namePos] горло."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(tentacle-fuck)] на [npc.her] лице.",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], когда [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tentacle+(true)] глубоко в [npc.namePos] горло.",
							" В восторге от [npc2.Moaning], [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.tentacle+(true)], заталкивая его как можно глубже в [npc.namePos] горло."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction SUCKING_TENTACLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежно сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Нежно посасывать [npc2.namePos] [npc2.tentacle+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подталкивая голову [npc.her] вперед, [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] горло.",
					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(start)] подталкивает голову [npc.her] вперед, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her] горло.",
					"Медленно подталкивая голову [npc.her] вперед, приглушенный [npc.moan] вырывается сквозь [npc.namePos] [npc.lips+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her] горло."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TENTACLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Посасывать [npc2.namePos] [npc2.tentacle+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы загнать [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her].",
					"С помощью [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкает [npc.her] вперед, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her].",
					"Энергично толкая [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] счастливо [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TENTACLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубо сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Грубо сосать [npc2.namePos] [npc2.tentacle+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her].",
					"С помощью [npc.a_moan+], [npc.name] [npc.verb(start)] грубо толкает [npc.her] вперед, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her].",
					"Сильно толкая [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] яростно [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SUCKING_TENTACLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Посасывать [npc2.namePos] [npc2.tentacle+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы загнать [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her].",
					"С помощью [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкает [npc.her] вперед, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her].",
					"Энергично толкая [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] счастливо [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TENTACLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Жадно трахать тебе горло [npc2.namePos] [npc2.tentacle+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы загнать [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her].",
					"С помощью [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкает [npc.her] вперед, заставляя [npc2.namePos] [npc2.tentacle+(true)] проникать всё глубже в [npc.her].",
					"Энергично толкая [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] счастливо [npc.verb(force)] [npc2.namePos] [npc2.tentacle+(true)] глубоко в [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться сосанию щупальца";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться отстраниться от [npc2.namePos] [npc2.tentacle+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)] на глазах у [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] горла.",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] голову от нежелательного проникновения [npc2.namePos], тщетно борясь с тем, как [npc2.her] [npc2.tentacle+(true)] медленно [npc2.verb(continue)] входит и выходит из [npc.her] горла.",
							"Отчаянно пытаясь отстранить голову [npc.her], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] горло."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)] на глазах у [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] горла.",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести голову [npc.her] от нежелательного проникновения [npc2.namePos], тщетно борясь, пока [npc2.her] [npc2.tentacle+(true)] [npc2.verb(continue)] неистово вбивается в глотку [npc.her].",
							"Отчаянно пытаясь увести голову [npc.her], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] жадно [npc2.verb(continue)] глубоко в глотку [npc.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы [npc.verb(start)] на глазах у [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tentacle(true)] из [npc.her] горла.",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь увести голову [npc.her] от нежелательного проникновения [npc2.namePos], тщетно борясь, пока [npc2.her] [npc2.tentacle+(true)] грубо [npc2.verb(continue)] в глотку [npc.her].",
							"Отчаянно пытаясь увести голову [npc.her], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tentacle+(true)] яростно [npc2.verb(continue)] глубоко в глотку [npc.her]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FUCKED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Перестать сосать щупальце";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tentacle(true)] из твоей глотки.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.tentacle(true)] из глотки [npc.her], [npc.name] [npc.verb(let)] угрожающее рычание, чтобы [npc.verb(remind)] [npc2.name] поняла, что [npc.sheIs] здесь главный.",
							"[npc.Name] [npc.verb(lean)] в [npc2.name], на мгновение вдыхая [npc2.her] [npc2.scent], прежде чем резко вырвать [npc2.her] [npc2.tentacle(true)] из [npc.her] горла."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выдвигая [npc2.namePos] [npc2.tentacle(true)] из [npc.her] горла, [npc.name] [npc.verb(let)] из [npc.a_moan+], чтобы дать [npc2.name] понять, что [npc.she] получила удовольствие.",
							"[npc.Name] [npc.verb(lean)] в [npc2.name], на мгновение вдыхая [npc2.her] [npc2.scent], прежде чем плавно вытянуть [npc2.her] [npc2.tentacle(true)] из [npc.her] горла."));
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
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim] из щупальцевого траха в [npc.her] горле.",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжить щупальцевый трах в [npc.namePos] горло."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction THROAT_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TENTACLE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Контроль горла";
		}
		@Override
		public String getActionDescription() {
			return "Сжать внутренние мышцы горла вокруг [npc2.namePos] [npc2.tentacle(true)].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Издавая [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] горла вокруг [npc2.namePos] [npc2.tentacle+(true)].",
					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(focus)], управляя дополнительными мышцами, выстилающими внутреннюю часть [npc.her] горла. Сжимая их вокруг [npc2.namePos] [npc2.tentacle+(true)], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик наслаждения."
						:""),
					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], постепенно входя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)], сжимая дополнительные мышцы внутри [npc.her] горла вокруг [npc2.namePos] [npc2.tentacle+(true)].",
					"С [npc.a_moan+], [npc.name] [npc.verb(focus)], контролируя дополнительные мышцы глубоко внутри [npc.her] горла, сжимает их и массирует [npc2.namePos] [npc2.tentacle+(true)], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
