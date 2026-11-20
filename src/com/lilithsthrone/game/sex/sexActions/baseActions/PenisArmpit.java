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
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class PenisArmpit {

	public static final SexAction ARMPIT_SEX_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Трахать в подмышку";
		}
		@Override
		public String getActionDescription() {
			return "Тереби свой [npc.cock] вверх и вниз о [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly(с энтузиазмом, потягивая)] [npc2.namePos] [npc2.arm(true)] вверх, [npc.name] [npc.verb(press)] [npc.cockHead+] [npc.her] [npc.cock+] о теперь открытую подмышку [npc2.her], прежде чем [npc.eagerly] начнет тереть [npc.her] [npc.cock+] вверх и вниз по ней.",

					"[npc.Name] [npc.verb(pull)] [npc2.namePos] [npc2.arm(true)] вверх, прежде чем начать тереть [npc.cockHead+] [npc.her] [npc.cock] о [npc2.her] [npc2.armpit+]."));

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.armpit], а по [npc2.her] [npc2.face] текут слезы, и [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратил.",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], чтобы отстраниться от [npc.name]; слезы текут по [npc2.her] [npc2.face], пока незваный [npc.cock] скользит по [npc2.her] [npc2.armpit]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], используя [npc2.her] [npc2.armpit], прежде чем [npc2.eagerly] толкнет в ответ, помогая тереть [npc.her] [npc.cock+] о нее.",
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)], когда [npc2.eagerly] [npc2.her] [npc2.armpit] скользит вверх и вниз по [npc.namePos] [npc.cock+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(start)], толкая [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.do].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] с жадностью [npc2.verb(slide)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(slide)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах оттянуть [npc2.her] [npc2.arm(true)] от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(beg)], чтобы [npc.name] перестал использовать [npc2.her] [npc2.armpit].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] поскорее ушел от [npc2.her] [npc2.armpit].",
		
							" [npc2.Sobbing] в смятении, со слезами, бегущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], умоляя и плача, чтобы [npc.herHim] ушел от [npc2.her] [npc2.armpit]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] скользит [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.do] так.",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.verb(slide)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] нежно скользит [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.do] так.",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(slide)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(slide)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] грубо скользит [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.do] так.",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] с силой [npc2.verb(slide)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] яростно [npc2.verb(rub)] [npc2.her] [npc2.armpit+] вверх и вниз по [npc.namePos] [npc.cock+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежный секс в подмышках";
		}
		@Override
		public String getActionDescription() {
			return "Продолжайте мягко водить своим [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко водя [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit], [npc.name] steadily [npc.verb(start)] [npc.her] [npc.hips] вперед-назад, издавая легкий [npc.moan] при каждом толчке, пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Мягко потирая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit], [npc.name] softly [npc.verb(start)] [npc.her] [npc.hips] вперед, издавая легкий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Мягко прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] легкий [npc.moan], пока [npc.she] [npc.verb(start)] плавно качать [npc.her] [npc.hips] вперед-назад, вдыхая [npc2.her] [npc2.scent], пока [npc.she] медленно [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Подмышечный секс";
		}
		@Override
		public String getActionDescription() {
			return "Продолжайте мягко водить своим [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] энергично дергает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Отчаянно толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] неистово вбивает [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Жадно прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] отчаянно качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый секс в подмышке";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить грубо водить своим [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] властно качает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Сильно толкая [npc.her] [npc.cock+] вверх и вниз о [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] яростно вгоняя [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Доминантно прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы с силой вбивать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.her] [npc2.scent], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Подмышечный секс";
		}
		@Override
		public String getActionDescription() {
			return "Продолжайте мягко водить своим [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Толкая [npc.her] [npc.cock+] вверх и вниз о [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] дергая [npc.her] [npc.hips] туда-сюда, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Толкая [npc.her] [npc.cock+] вверх и вниз о [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] вгоняя [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] с наслаждением [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] с [npc.a_moan+], пока [npc.she] [npc.verb(start)], чтобы качать [npc.her] [npc.hips] туда-сюда, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадный секс в подмышках";
		}
		@Override
		public String getActionDescription() {
			return "С энтузиазмом продолжать водить своим [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] энергично дергает [npc.her] [npc.hips] взад-вперед, издавая [npc.a_moan+] при каждом толчке, пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Отчаянно толкая [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(start)] неистово вбивает [npc.her] [npc.hips] вперед, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(fuck)] [npc2.her] [npc2.armpit+].",

					"Жадно прижимая [npc.her] [npc.cock+] к [npc2.namePos] [npc2.armpit], [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] отчаянно качает [npc.her] [npc.hips] взад-вперед, вдыхая [npc2.her] [npc2.scent], пока [npc.she] энергично [npc.verb(fuck)] [npc2.her] [npc2.armpit+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться сексу в подмышках";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.cock] от [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но так и не сумев оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], игнорируя протесты [npc.her], [npc2.she] медленно, но уверенно [npc2.verb(push)] [npc2.her] [npc2.armpit] против [npc.her] [npc.cock+].",

							"[npc.A_sob+] вырывается из-под [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя [npc.her] протесты, [npc2.name] уверенно [npc2.verb(hold)] [npc.herHim] на месте, мягко прижимая [npc2.her] [npc2.armpit] к [npc.her] [npc.cock+].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.herHim] отпустила [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] протесты [npc.her], медленно растирая [npc2.her] [npc2.armpit] о [npc.her] [npc.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь, но так и не сумев оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], игнорируя протесты [npc.her], [npc2.she] яростно [npc2.verb(grind)] [npc2.her] [npc2.armpit] против [npc.her] [npc.cock+].",

							"[npc.A_sob+] вырывается из-под [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] властно удерживает [npc.herHim] на месте, яростно вжимая [npc2.her] [npc2.armpit] в [npc.her] [npc.cock+].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.name] отпустила [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] протесты [npc.her], грубо втирая [npc2.her] [npc2.armpit] в [npc.her] [npc.cock+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отчаянно пытаясь — и безуспешно — оттянуть [npc.her] [npc.cock+] от [npc2.name], [npc.name] [npc.verb(let)] из-под [npc.a_sob+], в то время как, игнорируя протесты [npc.her], [npc2.she] с жадностью [npc2.verb(press)] [npc2.her] [npc2.armpit] против [npc.her] [npc.cock+].",

							"[npc.A_sob+] вырывается из-под [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.name], но, полностью игнорируя протесты [npc.her], [npc2.she] крепко удерживает [npc.herHim] на месте, с азартом вжимая [npc2.her] [npc2.armpit] в [npc.her] [npc.cock+].",

							"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.name] отпустила [npc.her] [npc.cock]. [npc2.Moaning] в восторге, [npc2.name] полностью [npc2.verb(ignore)] протесты [npc.her], жадно втирая [npc2.her] [npc2.armpit] в [npc.her] [npc.cock+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_SEX_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить секс в подмышках";
		}
		@Override
		public String getActionDescription() {
			return "Оттяни свой [npc.cock+] от [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо вырывая [npc.her] [npc.cock+] от [npc2.namePos] [npc2.armpit+], [npc.name] властно [npc.verb(slide)] [npc.cockHead] [npc.her] [npc.cock] вверх и вниз по [npc2.her] [npc2.arm+(true)] в последний раз, прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз мощно толкнувшись в [npc2.namePos] [npc2.armpit+], [npc.name] затем [npc2.verb(pull)] назад, завершая секс в подмышках."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.cock+] из [npc2.namePos] [npc2.armpit+], [npc.name] в последний раз шлепает [npc.cockHead] [npc.her] [npc.cock] о [npc2.her] [npc2.arm+(true)], прежде чем оттянуть [npc.her] [npc.hips] назад.",

							"В последний раз толкнувшись вверх в [npc2.namePos] [npc2.armpit+], [npc.name] затем [npc2.verb(pull)] назад, завершая секс в подмышках."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] не может не издать [npc2.sob+], когда [npc.name] отстраняется, а [npc2.she] [npc2.verb(continue)], плача и протестуя, пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] оставила [npc.herHim] в покое.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться против [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(beg)] остаться в одиночестве."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.name] [npc.verb(pull)] [npc.her] [npc.cock+] назад, жаждая еще большего [npc.her] внимания.",
	
								" [npc2.A_moan+] выскальзывает из-под [npc2.namePos] [npc2.lips+], выдавая [npc2.her] похоть к [npc.namePos] [npc.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction ARMPIT_SEX_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Трахнуть в подмышке";
		}
		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.armpit] к [npc2.namePos] [npc2.cock+], чтобы заставить [npc2.herHim] начать трахать его.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С помощью [npc.a_moan+], [npc.name] [npc.verb(lift)] [npc.her] [npc.arm(true)] и [npc.eagerly] [npc.verb(press)] [npc.her] [npc.armpit+] против [npc2.namePos] [npc2.cock+], прежде чем [npc.eagerly] начнет тереть его вверх и вниз по [npc2.her] [npc2.cockHead+].",

					"Подняв [npc.her] [npc.arm(true)], [npc.name] [npc.verb(bring)] [npc.her] [npc.armpit+] вниз к [npc2.namePos] паху, прежде чем [npc.eagerly] прижмет [npc.her] [npc.armpit+] к [npc2.her] [npc2.cock+]."));

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.armpit] против [npc2.her] [npc2.cock+], и, сопротивляясь [npc.herHim], [npc2.she] отчаянно [npc2.verb(try)], чтобы отстраниться.",
	
								" С помощью [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.verb(force)] [npc.her] [npc.armpit] против [npc2.her] [npc2.cock]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], [npc2.eagerly] дергаясь, [npc2.her] [npc2.hips] вперед, пока [npc2.she] [npc2.verb(start)] [npc2.eagerly], трусь о [npc.her] [npc.armpit].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(thrust)] [npc2.her] [npc2.hips] вперед и [npc2.verb(press)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.armpit+]."));
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
							" [npc2.Name] жадно [npc2.verb(grind)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.armpit+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.do] так.",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] с нетерпением вгоняя [npc2.her] [npc2.cock+] против [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] в восторге, [npc2.name] с жадностью [npc2.verb(press)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.armpit+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.armpit], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы втиснуть [npc2.her] [npc2.cock+] против [npc.her] [npc.armpit+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], чтобы оттащить [npc2.her] [npc2.cock] от [npc.namePos] [npc.armpit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.armpit+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.do] так.",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] с силой вгоняет [npc2.her] [npc2.cock+] в [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(press)] [npc2.her] [npc2.cock+] вплотную к [npc.namePos] [npc.armpit+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] мягко [npc2.verb(thrust)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.armpit+], издавая тихий [npc2.moan], пока [npc2.she] [npc2.do] так.",
		
							" Тихий [npc2.moan] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] плавно вгоняет [npc2.her] [npc2.cock+] в [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(thrust)] [npc2.her] [npc2.cock+] вплотную к [npc.namePos] [npc.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] против [npc.namePos] [npc.armpit+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.do] так.",
		
							" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо втирает [npc2.her] [npc2.cock+] в [npc.namePos] [npc.armpit].",
									
							" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(thrust)] [npc2.her] [npc2.cock+] вплотную к [npc.namePos] [npc.armpit+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежный секс в подмышках";
		}
		@Override
		public String getActionDescription() {
			return "Мягко продолжай водить своим [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] нежно [npc.verb(slide)] [npc.her] [npc.armpit] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] скользит [npc.her] [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"Медленно прижимая [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы трахнуть это."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Трахнуть в подмышке";
		}
		@Override
		public String getActionDescription() {
			return "Продолжай водить своим [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(slide)] [npc.her] [npc.armpit] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользит [npc.her] [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"Жадно прижимая [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы трахнуть это."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый секс в подмышке";
		}
		@Override
		public String getActionDescription() {
			return "Грубо продолжай водить своим [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.armpit] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"С [npc.a_moan+], [npc.name] [npc.verb(start)] с силой скользит [npc.her] [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].",
					"Грубо прижимая [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(get)] [npc2.herHim], чтобы трахнуть это."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Трахнуть в подмышке";
		}
		@Override
		public String getActionDescription() {
			return "Продолжай водить своим [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(slide)] [npc.her] [npc.armpit] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользит [npc.her] [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"Прижимая [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(get)] [npc2.herHim], чтобы трахнуть это."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно трахаемая подмышка";
		}
		@Override
		public String getActionDescription() {
			return "С энтузиазмом продолжай скользить своим [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(slide)] [npc.her] [npc.armpit] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"С [npc.a_moan+], [npc.name] счастливо [npc.verb(start)] скользит [npc.her] [npc.armpit+] вверх и вниз по [npc2.namePos] [npc2.cock+].",

					"Жадно прижимая [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы трахнуть это."));

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляющаяся подмышка";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.armpit+] от [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наворачиваться на [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] из [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] [npc2.verb(continue)] нежно вжимается в [npc.her] [npc.armpit].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.armpit] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] [npc2.verb(continue)] медленно вбивается в [npc.her] [npc.armpit].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.armpit] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] нежно скользит по [npc.her] [npc.armpit]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"В [npc.her] [npc.eyes] [npc.verb(feel)] Y[npc.Name] начинают подступают слезы, и, не в силах больше сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] с жадностью [npc2.verb(continue)] [npc.her] [npc.armpit].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.armpit] от [npc2.namePos] [npc2.cock], в безнадежной борьбе, пока [npc2.she] с жадностью [npc2.verb(continue)] вбивается в [npc.her] [npc.armpit].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.armpit] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] с жадностью [npc2.verb(continue)] скользит по [npc.her] [npc.armpit]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.her] [npc.eyes] начинают наполняться [npc.Name] [npc.verb(feel)], и, больше не в силах сдерживаться, [npc.she] внезапно [npc.verb(let)] [npc.a_sob+], слабо пытаясь отстраниться от [npc2.namePos] [npc2.cock], пока [npc2.she] [npc2.verb(continue)] грубо трахает [npc.her] [npc.armpit].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.armpit] от [npc2.namePos] [npc2.cock], в отчаянной борьбе, пока [npc2.she] [npc2.verb(continue)] грубо трахает [npc.her] [npc.armpit].",

							"Отчаянно пытаясь оттащить [npc.her] [npc.armpit] от [npc2.name], [npc.name] [npc.sobVerb] в смятении, пока [npc2.her] [npc2.cock+] [npc2.verb(continue)] грубо втираясь в [npc.her] [npc.armpit]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ARMPIT_SEX_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить секс в подмышках";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] оттащить [npc2.her] [npc2.cock+] от твоей [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Забирая [npc.her] [npc.armpit] от [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] угрожающее рычание, пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы оставить [npc.her] [npc.arm(true)] в покое.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем быстро увести [npc.her] [npc.armpit] от [npc2.her] [npc2.cock+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Убирая [npc.her] [npc.armpit] от [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(tell)] [npc2.herHim], чтобы оставить [npc.her] [npc.arm(true)] в покое.",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем убрать [npc.her] [npc.armpit] от [npc2.her] [npc2.cock+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] облегченно вздыхает, но вскоре этот вздох переходит в [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)] понимает, что [npc.name] [npc.has] еще не закончил с [npc2.herHim].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать трахать [npc.her] [npc.armpit]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}
	};
	
}
