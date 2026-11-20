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
public class TailMouth {
	
	// Character performing action is receiving the face-fucking:
	
	public static final SexAction TAIL_THROAT_FUCK_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Дать отсосать хвосту";
		}
		@Override
		public String getActionDescription() {
			return "Просунуть свой [npc.tail+(true)] в [npc2.namePos] горло, чтобы [npc2.herHim] отсосала его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.tailTip+] [npc.tail(true)] [npc.her] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно продвинуться вперед, погружая [npc.her] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] [npc2.lips+], и медленным, уверенным движением [npc.she] нежно [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.tail(true)] [npc.her] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tail+(true)] в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] [npc2.lips+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.tailTip+] [npc.tail(true)] [npc.her] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно рвануть вперед, вбивая [npc.her] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] [npc2.lips+], и резким толчком, [npc.she] грубо [npc.verb(slam)] его глубоко в [npc2.her] горло."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью дразня [npc.tailTip+] [npc.tail(true)] [npc.her] по [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем резко толкнуться вперед, жадно погружая [npc.her] [npc.tail+(true)] в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] [npc2.lips+], и решительным толчком [npc.she] жадно [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.tailTip+] [npc.her] [npc.tail(true)] над [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем рвануть вперед, вонзая [npc.her] [npc.tail+(true)] в [npc2.her] горло.",
							"[npc.Name] [npc.verb(position)] [npc.tailTip+] [npc.her] [npc.tail(true)] между [npc2.namePos] [npc2.lips+], и легким толчком, [npc.she] [npc.verb(sink)] его глубоко в [npc2.her] горло."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tail+(true)], прежде чем мягко толкнуть [npc2.her] голову вперед, чтобы вогнать его еще глубже в [npc2.her] горло.",
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно толкая [npc2.her] голову вперед, погружая [npc.namePos] [npc.tail+(true)] еще глубже в [npc2.her] горло."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tail+(true)], прежде чем яростно толкнуть [npc2.her] голову вперед, чтобы загнать его еще глубже в [npc2.her] горло.",
								" Издавая булькающий [npc2.moan], [npc2.name] [npc2.verb(push)] [npc2.her] вперед, заставляя [npc.namePos] [npc.tail+(true)] проникать еще глубже в [npc2.her] горло."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tail+(true)], после чего с жадностью толкая [npc2.her] вперед, чтобы вогнать ее еще глубже в [npc2.her] горло.",
								" Издавая булькающий [npc2.moan], [npc2.name] с жадностью [npc2.verb(bob)] [npc2.her] вперед, проталкивая [npc.namePos] [npc.tail+(true)] еще глубже в [npc2.her] горло."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tail+(true)], после чего толкая [npc2.her] вперед, чтобы вогнать ее еще глубже в [npc2.her] горло.",
								" Издавая булькающий [npc2.moan], [npc2.name] [npc2.verb(bob)] [npc2.her] вперед, проталкивая [npc.namePos] [npc.tail+(true)] еще глубже в [npc2.her] горло."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] [npc2.verb(get)] вкус [npc.namePos] [npc.tail(true)], и, со слезами, текущими по [npc2.her] [npc2.face], [npc2.she] тщетно [npc2.verb(try)] от этого нежеланного проникновения.",
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
							" [npc2.Name] с нетерпением [npc2.verb(push)] [npc2.her] вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" Приглушенный [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно толкая [npc2.her] вперед, [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" [npc2.Moaning] от восторга, [npc2.name] с нетерпением [npc2.verb(thrust)] [npc2.her] вперед, чтобы помочь протолкнуть [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь — и не сумев — вывернуться от [npc.namePos] [npc.tail(true)], [npc2.name] [npc2.verb(let)] булькающий [npc2.sob], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(pull)] [npc2.her] голову назад.",
							" Приглушенный [npc2.sob] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.her] горло насилуется [npc.namePos] [npc.tail+(true)].",
							" [npc2.Sobbing] от мучения, со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name] в попытке вытащить [npc.her] [npc.tail(true)] из [npc2.her] горла."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] подает голову вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" Глотательный [npc2.moan] вырывается из меж [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] голову вперед, [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" [npc2.Moaning] от восторга, [npc2.name] [npc2.verb(thrust)] [npc2.her] подает голову вперед, чтобы помочь вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] подает голову вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] осторожно [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" Тихий, глотательный [npc2.moan] вырывается из меж [npc2.namePos] [npc2.lips+], и, медленно толкая [npc2.her] голову вперед, [npc2.she] [npc2.verb(help)], чтобы вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" [npc2.Moaning] от восторга, [npc2.name] медленно [npc2.verb(push)] [npc2.her] подает голову вперед, чтобы помочь вогнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(push)] [npc2.her] голову вперед в ответ, издавая приглушенный [npc2.moan], пока [npc2.she] властно [npc2.verb(force)] [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" Булькающий [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо толкая [npc2.her] голову вперед, [npc2.she] с силой [npc2.verb(drive)] [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло.",
							" От восторга [npc2.Moaning], [npc2.name] грубо [npc2.verb(thrust)] [npc2.her] голову вперед, чтобы загнать [npc.namePos] [npc.tail+(true)] глубоко в [npc2.her] горло."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное посасывание хвоста";
		}
		@Override
		public String getActionDescription() {
			return "Плавно вводите и выводите свой [npc.tail(true)] из [npc2.namePos] горла.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко проталкивая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(start)] двигает им туда-сюда, издавая легкий [npc.moan] при каждом из [npc.her] нежных толчков.",
					"Медленно проталкивая [npc.her] [npc.tail+(true)] вниз по [npc2.namePos] горлу, [npc.name] мягко [npc2.verb(thrust)] его туда-сюда, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(receive)] рот [npc2.herHim].",
					"Скользя [npc.her] [npc.tail+(true)] вниз по [npc2.namePos] горлу, [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)], чтобы мягко качать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] медленно [npc.verb(tail-peg)] [npc2.herHim]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Хвост сосет";
		}
		@Override
		public String getActionDescription() {
			return "Продолжайте вгонять свой [npc.tail+(true)] в глотку [npc2.namePos] и вынимать его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tail+(true)] глубоко в глотку [npc2.namePos], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом из [npc.her] энергичных толчков.",
					"С энтузиазмом проталкивая [npc.her] [npc.tail+(true)] глубоко в глотку [npc2.namePos], [npc.name] с восторгом [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] оральный секс от [npc2.herHim].",
					"Вгоняя [npc.her] [npc.tail+(true)] глубоко в глотку [npc2.namePos], [npc.name] [npc.verb(let)] его, пока [npc.a_moan+] с жадностью [npc.verb(start)] оральный секс от [npc.she], чтобы усердно двигать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tail-fuck)] [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Хвост грубо сосет";
		}
		@Override
		public String getActionDescription() {
			return "Грубо вгоняйте свой [npc.tail+(true)] в глотку [npc2.namePos] и вынимайте его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо вколачивая [npc.her] [npc.tail+(true)] глубоко в глотку [npc2.namePos], [npc.name] грубо [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом из [npc.her] жестоких толчков.",
					"Яростно вгоняя [npc.her] [npc.tail+(true)] в глотку [npc2.namePos], [npc.name] грубо [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(receive)] оральный секс от [npc2.herHim].",
					"С силой вонзая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо вбивает его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(tail-fuck)] в [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Хвост сосет";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать трахать [npc2.namePos] в горло.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(start)] толчками входит и выходит, издавая [npc.a_moan+] при каждом [npc.her] толчке.",
					"Заталкивая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] [npc.verb(receive)] орально из [npc2.herHim].",
					"Вбивая [npc.her] [npc.tail+(true)] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] качает его туда-сюда, жадно вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] неистово [npc.verb(tail-fuck)] в [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Жадно качать свой [npc.tail+(true)] в горле [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.tail+(true)] глубоко в глотку [npc2.namePos], [npc.name] с энтузиазмом [npc.verb(start)] его туда-сюда, издавая [npc.a_moan+] при каждом из [npc.her] энергичных толчков.",
					"С энтузиазмом проталкивая [npc.her] [npc.tail+(true)] глубоко в глотку [npc2.namePos], [npc.name] с восторгом [npc2.verb(thrust)] его туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(receive)] оральный секс от [npc2.herHim].",
					"Вгоняя [npc.her] [npc.tail+(true)] глубоко в глотку [npc2.namePos], [npc.name] [npc.verb(let)] его, пока [npc.a_moan+] с жадностью [npc.verb(start)] оральный секс от [npc.she], чтобы усердно двигать его туда-сюда, вдыхая [npc2.namePos] [npc2.scent], пока [npc.she] отчаянно [npc.verb(tail-fuck)] [npc2.her] лицо."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться сосанию хвоста";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tail(true)] из горла [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.tail(true)] изо рта [npc2.namePos], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] нежно удерживает его, прежде чем мягко протолкнуть обратно в [npc2.her] горло.",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tail(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его и нежно [npc2.verb(force)] обратно в [npc2.her] горло.",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и из-за [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tail(true)] от [npc2.namePos] [npc2.lips+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.tail+(true)] обратно в [npc2.her] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.tail(true)] изо рта [npc2.namePos], но усилия [npc.her] оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепко сжимает его, прежде чем агрессивно затолкать обратно в [npc2.her] горло.",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tail(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его и грубо [npc2.verb(force)] обратно в [npc2.her] горло.",
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и из-за [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.tail(true)] от [npc2.namePos] [npc2.lips+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.tail+(true)] обратно в [npc2.her] горло."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попытаться вытащить [npc.her] [npc.tail(true)] изо рта [npc2.namePos], но [npc.her] усилия оказываются напрасными, так как [npc2.name] быстро [npc2.verb(take)] крепко сжимает его, прежде чем жадно затолкнуть обратно в [npc2.her] горло.",
							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail(true)] от [npc2.name], но [npc2.she] быстро [npc2.verb(grab)] его и жадно [npc2.verb(force)] обратно в [npc2.her] горло.",
							"В глазах [npc.eyes] [npc.namePos] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)] попытаться оттянуть [npc.her] [npc.tail(true)] от [npc2.namePos] [npc2.lips+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(force)] [npc.her] [npc.tail+(true)] обратно в [npc2.her] горло."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction TAIL_THROAT_FUCK_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TAIL, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Перестать сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tail+(true)] из [npc2.namePos] горла и прекрати оральный секс для [npc2.herHim].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо вырывая [npc.her] [npc.tail+(true)] изо рта [npc2.namePos], [npc.name] властно [npc.verb(slide)] водит [npc.tailTip+] вверх и вниз по [npc2.her] [npc2.lips+] в последний раз, прежде чем отстраниться.",
							"В последний раз глубоко вонзаясь в [npc2.name] горло, [npc.name] затем [npc.verb(yank)] вытаскивает [npc.her] [npc.tail+(true)] изо рта [npc2.her], завершая этот грубый фасфейс."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выдвигая [npc.her] [npc.tail(true)] из [npc2.namePos] рта, [npc.name] [npc.verb(rub)] [npc.tailTip] вверх и вниз по [npc2.her] [npc2.lips+] в последний раз, прежде чем оттянуть его назад.",
							"В последний раз заталкивая [npc2.name] глубоко в [npc.name] глотку, а затем [npc.verb(slide)] [npc.her] [npc.tail+(true)] обратно через [npc2.her] рот, завершая этот минет хвостом."));
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
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.tail+(true)] из [npc2.her] горла, жаждая еще большего [npc.her] внимания.",
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая отчаянное желание [npc2.her] получить еще больше внимания от [npc.namePos]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Character performing action is doing the face-fucking:
	
	public static final SexAction USING_TAIL_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Засунь [npc2.namePos] [npc2.tail+(true)] себе в глотку и отсоси его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] медленно [npc.verb(guide)] его к [npc.her] [npc.lips+], издавая легкий [npc.moan], прежде чем широко открыть рот и осторожно заглотить его глубоко в [npc.her] горло.",
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] его к приоткрытому [npc.her] [npc.lips], а затем медленно подавая [npc.her] голову вперед и осторожно заглатывая его глубоко в [npc.her] глотку."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] жадно [npc.verb(guide)] его к приоткрытому [npc.her] [npc.lips+], издавая [npc.a_moan+], прежде чем широко раскрыть рот и с жадностью заглотить его глубоко в [npc.her] глотку.",
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] нетерпеливо [npc.verb(line)] его к приоткрытому [npc.her] [npc.lips], а затем резко подавая [npc.her] голову вперед и с нетерпением заглатывая его глубоко в [npc.her] глотку."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] грубо [npc.verb(yank)] его к [npc.her] [npc.lips+], издавая [npc.a_moan+], прежде чем широко раскрыть рот и с силой заглотить его глубоко в [npc.her] глотку.",
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] неистово [npc.verb(yank)] его к приоткрытому [npc.her] [npc.lips], а затем резко подавая [npc.her] голову вперед и грубо втискивая его глубоко в [npc.her] глотку."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(guide)] его к [npc.her] [npc.lips+], издавая [npc.a_moan+], прежде чем широко раскрыть рот и заглотить его глубоко в [npc.her] глотку.",
							"Схватив [npc2.namePos] [npc2.tail(true)], [npc.name] [npc.verb(line)] его до [npc.her] приоткрытого [npc.lips], после чего [npc.her] подает голову вперед и заглатывает его глубоко в [npc.her] горло."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ тихий [npc2.moan], прежде чем мягко подтолкнуть [npc2.her] [npc2.tail(true)] вперед и начать ритмично трахать [npc.namePos] хвостом в горло.",
								" С тихим [npc2.moan], [npc2.name] осторожно [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] вперед, погружая его глубоко в [npc.namePos] горло, пока [npc2.she] [npc2.verb(start)] ритмично трахает [npc.herHim] хвостом."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], после чего с нетерпением подталкивает [npc2.her] [npc2.tail(true)] вперед и начинает ритмично трахать [npc.namePos] хвостом в горло.",
								" С [npc2.a_moan+], [npc2.name] счастливо [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] вперед, погружая его глубоко в [npc.namePos] горло, пока [npc2.she] [npc2.verb(start)] ритмично трахает [npc.herHim] хвостом."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], прежде чем попытаться напомнить [npc.name], кто здесь главный, яростно толкая [npc2.her] [npc2.tail(true)] вперед и начиная грубо трахать [npc.her] хвостом в горло.",
								" С [npc2.a_moan+], [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.tail(true)] вперед, заталкивая его глубоко в [npc.namePos] горло, пока [npc2.she] с силой [npc2.verb(start)] хвостом-трахает [npc.her] лицо."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] в ответ [npc2.verb(let)] [npc2.a_moan+], прежде чем толкнуть [npc2.her] [npc2.tail(true)] вперед и начать ритмично трахать хвостом [npc.namePos] горло.",
								" С [npc2.a_moan+], [npc2.name] счастливо [npc2.verb(thrust)] [npc2.her] [npc2.tail(true)] вперед, погружая его глубоко в [npc.namePos] горло, пока [npc2.she] [npc2.verb(start)] ритмично трахает [npc.herHim] хвостом."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.tail(true)] внутри [npc.herHim], и, борясь с [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы вытащить [npc2.her] [npc2.tail+(true)] из [npc.her] горла.",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.tail(true)] глубоко в [npc.her] горло."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(tail-fuck)] [npc.her] лицо.",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], когда [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло.",
							" От восторга [npc2.Moaning], [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.tail+(true)], заталкивая его как можно глубже в [npc.namePos] горло."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттянуть [npc2.her] [npc2.tail(true)] от [npc.namePos] [npc.lips], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], заталкивая [npc2.her] [npc2.tail+(true)] глубоко в [npc.her] горло.",
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] оттянуть [npc2.her] [npc2.tail(true)] от [npc.namePos] горла."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло, издавая тихий [npc2.moan], пока [npc2.she] мягко [npc2.verb(tail-fuck)] [npc.her] лицо.",
							" [npc2.A_moan+] вылетает изо рта [npc2.namePos], пока [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло.",
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tail+(true)], насколько это возможно глубоко в [npc.namePos] горло."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] грубо [npc2.verb(tail-fuck)] [npc.her] лицо.",
							" [npc2.A_moan+] вылетает изо рта [npc2.namePos], пока [npc2.she] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло.",
							" [npc2.Moaning] в восторге, [npc2.name] с силой [npc2.verb(drive)] [npc2.her] [npc2.tail+(true)] как можно глубже в [npc.namePos] горло."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(tail-fuck)] [npc.her] лицо.",
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], когда [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tail+(true)] глубоко в [npc.namePos] горло.",
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(drive)] [npc2.her] [npc2.tail+(true)] как можно глубже в [npc.namePos] горло."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction SUCKING_TAIL_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежно сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Нежно посасывать [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко подталкивая голову [npc.her] вперед, [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] горло.",
					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(start)] подталкивает голову [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в [npc.her] горло.",
					"Медленно подталкивая голову [npc.her] вперед, приглушенный [npc.moan] вырывается сквозь [npc.namePos] [npc.lips+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] горло."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Сосать [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно подталкивая голову [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] горло.",
					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкает голову [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в глотку [npc.her].",
					"Энергично толкая голову [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] счастливо [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] глубоко в глотку [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубо сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Грубо сосать [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая голову [npc.her] вперед, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] глубоко в глотку [npc.her].",
					"С [npc.a_moan+], [npc.name] [npc.verb(start)] грубо толкает голову [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в глотку [npc.her].",
					"Сильно толкая голову [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] неистово [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] глубоко в глотку [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SUCKING_TAIL_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Сосать [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно подталкивая голову [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] горло.",
					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкает голову [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в глотку [npc.her].",
					"Энергично толкая голову [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] счастливо [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] глубоко в глотку [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction SUCKING_TAIL_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Жадно трахать тебе горло [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно подталкивая голову [npc.her] вперед, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.tail+(true)] глубоко в [npc.her] горло.",
					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] толкает голову [npc.her] вперед, заставляя [npc2.namePos] [npc2.tail+(true)] проникать всё глубже в глотку [npc.her].",
					"Энергично толкая голову [npc.her] вперед, [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] счастливо [npc.verb(force)] [npc2.namePos] [npc2.tail+(true)] глубоко в глотку [npc.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FUCKED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться сосанию хвоста";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться отстраниться от [npc2.namePos] [npc2.tail+(true)].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)] наворачиваются на [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail(true)] из [npc.her] горла.",
							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь убрать [npc.her] голову от нежеланного проникновения [npc2.namePos], в отчаянии борясь, пока [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] медленно входит и выходит из [npc.her] горла.",
							"Отчаянно пытаясь отстранить [npc.her] голову, [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+(true)] [npc2.verb(continue)] плавно скользит глубоко в [npc.her] горло."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)] наворачиваются на [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail(true)] из [npc.her] горла.",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть голову [npc.her] от нежеланного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tail+(true)] [npc2.verb(continue)] неистово входит и выходит из горла [npc.her].",
							"Отчаянно пытаясь оттянуть голову [npc.her], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+(true)] жадно [npc2.verb(continue)] глубоко в горло [npc.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слёзы [npc.verb(start)] наворачиваются на [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], прежде чем слабо попытаться вытащить [npc2.namePos] [npc2.tail(true)] из [npc.her] горла.",
							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть голову [npc.her] от нежеланного проникновения [npc2.namePos], в безнадежной борьбе, пока [npc2.her] [npc2.tail+(true)] грубо [npc2.verb(continue)] в горло [npc.her].",
							"Отчаянно пытаясь оттянуть голову [npc.her], [npc.name] [npc.sobVerb] в смятении, пока [npc2.namePos] [npc2.tail+(true)] яростно [npc2.verb(continue)] глубоко в горло [npc.her]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Перестать сосать хвост";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tail(true)] из твоего горла.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.tail(true)] из горла [npc.her], [npc.name] [npc.verb(let)] угрожающее рычание, чтобы [npc.verb(remind)] [npc2.name] понял, что [npc.sheIs] здесь главный.",
							"[npc.Name] [npc.verb(lean)] в [npc2.name], на мгновение вдыхая [npc2.her] [npc2.scent], прежде чем вырвать [npc2.her] [npc2.tail(true)] из горла [npc.her]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вытягивая [npc2.namePos] [npc2.tail(true)] из [npc.her] горла, [npc.name] [npc.verb(let)] из [npc.a_moan+], чтобы дать [npc2.name] понять, что [npc.she] наслаждается процессом.",
							"[npc.Name] [npc.verb(lean)] в [npc2.name], на мгновение вдыхая [npc2.her] [npc2.scent], прежде чем вытянуть [npc2.her] [npc2.tail(true)] из [npc.her] горла."));
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
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim] из [npc.her] горла во время траха хвостом.",
								" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.namePos] хвостом в горло."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TAIL)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Контроль горла";
		}
		@Override
		public String getActionDescription() {
			return "Сжать внутренние мышцы горла вокруг [npc2.namePos] [npc2.tail(true)].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Выпуская [npc.a_moan+], [npc.name] [npc.verb(concentrate)], сжимая дополнительные внутренние мышцы внутри [npc.her] горла вокруг [npc2.namePos] [npc2.tail+(true)].",
					(!isTargetedCharacterInanimate()
						?"[npc.Name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(focus)], контролируя дополнительные мышцы, выстилающие внутренности [npc.her] горла. Сжимая и обхватывая их вокруг [npc2.namePos] [npc2.tail+(true)], [npc.name] [npc.verb(cause)] [npc2.herHim], издавая непроизвольный крик удовольствия."
						:""),
					"[npc.Name] [npc.verb(find)] [npc.her] [npc.moans], переходя в устойчивый ритм, пока [npc.she] [npc.verb(concentrate)], сжимая дополнительные мышцы внутри [npc.her] горла вокруг [npc2.namePos] [npc2.tail+(true)].",
					"С [npc.a_moan+], [npc.name] [npc.verb(focus)], контролируя дополнительные мышцы глубоко в глотке [npc.her], сжимая их и массируя [npc2.namePos] [npc2.tail+(true)], пока [npc.she] [npc.verb(squeal)] от удовольствия.");
		}
	};
}
