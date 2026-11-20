package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FootType;
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
public class FootMouth {

	// Actions related to someone worshipping feet:
	
	public static final SexAction FOOT_ORAL_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Поклоняться [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Прижать свой [npc.lips] к [npc2.namePos] [npc2.feet] и начать целовать и лизать их.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			boolean foundFootType = false;
			if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Eagerly(bringing)] [npc.her] склоняет голову к твердым копытам [npc2.namePos], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к ним и [npc.verb(start)] страстно целует и лижет их.",
							"[npc.Name] [npc.verb(drop)] [npc.her] склоняет голову к [npc2.namePos] [npc2.feet], прежде чем жадно прижать [npc.her] [npc.lips+] к [npc2.namePos] твердым копытам и начать [npc.gently] целовать и лизать их."));
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Eagerly(bringing)] [npc.her] склоняет голову к птичьим лапам [npc2.namePos], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.her] когтям и [npc.verb(start)] страстно целует и лижет их.",
							"[npc.Name] [npc.verb(drop)] [npc.her] склоняет голову к птичьим лапам [npc2.namePos], прежде чем жадно прижать [npc.her] [npc.lips+] к [npc2.namePos] когтям и начать [npc.gently] целовать и лизать их."));
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Eagerly(bringing)] [npc.her] склоняет голову к щупальцам [npc2.namePos], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к ним и [npc.verb(start)] страстно целует и лижет их.",
							"[npc.Name] [npc.verb(drop)] [npc.her] склоняет голову к щупальцам [npc2.namePos], прежде чем жадно прижать [npc.her] [npc.lips+] к ним и начать [npc.gently] целовать и лизать их."));
					
				}
			}
			if(!foundFootType) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(bringing)] [npc.her] наклоняет голову к [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] против них и [npc.verb(start)] страстно целует и лижет их.",
						"[npc.Name] [npc.verb(drop)] [npc.her] голову к [npc2.namePos] [npc2.feet+], прежде чем жадно прижать [npc.her] [npc.lips+] к ним и начать страстно [npc.gently] целовать и облизывать их."));
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.do] это, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.she] отчаянно [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а).",
							" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)] отстраниться от [npc.name], но, не в силах этого сделать, всё, что может делать [npc2.she] — это всхлипывать, пока [npc.name] [npc.verb(continue)] поклоняется [npc2.her] [npc2.feet]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)] поклоняется [npc2.her] [npc2.feet], прежде чем [npc2.eagerly] подтолкнет их к [npc.her] [npc.face], чтобы побудить [npc.herHim] продолжать.",
							" В ответ на это [npc2.name] [npc2.verb(start)] [npc2.eagerly], толкая [npc2.her] [npc2.feet] в [npc.namePos] [npc.face], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(focus)] от восхитительного чувства того, что [npc2.her] [npc2.feet(true)] боготворят."));
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
				case SUB_NORMAL:
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] [npc2.eagerly] скользящие [npc2.her] [npc2.feet+] вверх и вниз по [npc.namePos] [npc.face] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(get)] [npc.herHim], чтобы поклоняться [npc2.her] [npc2.feet(true)].",
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.eagerly] [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз по [npc.namePos] [npc.face+].",
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] [npc2.feet+] о [npc.namePos] рот, пока [npc2.she] [npc2.has] их боготворят."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.name], [npc2.name] [npc2.verb(let)] [npc2.a_sob+] и слабо [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а) поклоняться [npc2.her] [npc2.feet(true)].",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.namePos] [npc.face] от [npc2.her] [npc2.feet].",
							" [npc2.Sobbing] в смятении, и со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.feet] от [npc.namePos] [npc.face]."));
					break;
				case DOM_ROUGH:
					boolean foundFootType = false;
					if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
							foundFootType = true;
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(start)] [npc2.roughly] с яростным трением [npc2.her] [npc2.feet+] вверх и вниз по [npc.namePos] [npc.face] в ответ, совершенно не заботясь о том, что использование [npc2.her] твердых копыт в столь грубой манере доставляет [npc.herHim] крайний дискомфорт.",
									" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.roughly] [npc2.verb(rub)] [npc2.her] твердыми копытами вверх и вниз по [npc.namePos] [npc.face+], ни капли не заботясь о том, насколько неудобно это должно быть для [npc.herHim].",
									" [npc2.Moaning] в восторге, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] твердыми копытами о [npc.namePos] рот, насмешливо хохоча над [npc.herHim], пока [npc2.she] [npc2.has] [npc2.her] [npc2.feet] обожаемый."));
							
						} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
							foundFootType = true;
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(start)] [npc2.roughly] с яростным трением [npc2.her] [npc2.feet+] вверх и вниз по [npc.namePos] [npc.face] в ответ, не обращая внимания на то, что [npc2.her] острые когти то и дело опасно подбираются к тому, чтобы полоснуть [npc.herHim].",
									" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.roughly] [npc2.verb(rub)] [npc2.her] птичьими когтями вверх и вниз по [npc.namePos] [npc.face+], ни капли не заботясь о том, насколько близко [npc2.her] острые когти подходят к тому, чтобы располосовать [npc.herHim].",
									" [npc2.Moaning] в восторге, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] скребла птичьими лапками по рту [npc.namePos], насмешливо хохоча над [npc.herHim], пока [npc2.she] [npc2.verb(draw)] опасно близко к тому, чтобы полоснуть [npc.herHim] своими [npc2.her] острыми когтями."));
							
						}
					}
					if(!foundFootType) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)] [npc2.eagerly] скользящие [npc2.her] [npc2.feet+] вверх и вниз по [npc.namePos] [npc.face] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(get)] [npc.herHim], чтобы поклоняться [npc2.her] [npc2.feet(true)].",
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] [npc2.eagerly] [npc2.verb(slide)] [npc2.her] [npc2.feet+] вверх и вниз по [npc.namePos] [npc.face+].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] [npc2.feet+] о [npc.namePos] рот, пока [npc2.she] [npc2.has] их боготворят."));
					}
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежно боготворить [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Нежно целовать и лизать [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Осыпав [npc2.namePos] [npc2.feet] серией нежных поцелуев, [npc.name] [npc.verb(proceed)] с любовью приняться их вылизывать, издавая при этом тихие [npc.moans].",
					"Нежно целуя и лизая [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] слегка [npc.moan], пока [npc.she] с любовью [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] слегка [npc.moan], пока [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.namePos] [npc2.feet], прежде чем начать с любовью их целовать и лизать."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Поклоняться [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить лизать и целовать [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Осыпав [npc2.namePos] [npc2.feet] серией страстных поцелуев, [npc.name] [npc.verb(proceed)] с энтузиазмом приняться их вылизывать, издавая при этом серию [npc.moans+].",
					"Страстно целуя и лизая [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] слегка [npc.a_moan+], пока [npc.she] счастливо [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.namePos] [npc2.feet], прежде чем начать жадно целовать и лизать их."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубо боготворить [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Грубо целовать и лизать [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Осыпав [npc2.namePos] [npc2.feet] серией властных поцелуев, [npc.name] [npc.verb(proceed)], чтобы начать грубо их лизать, издавая при этом серию [npc.moans+].",
					"Грубо целуя и лизая [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] из [npc.a_moan+], доминантно [npc.verb(worship)] [npc.she] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.namePos] [npc2.feet], прежде чем начать властно целовать и лизать их."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Поклоняться [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить целовать и лизать [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Осыпав [npc2.namePos] [npc2.feet] серией поцелуев, [npc.name] [npc.verb(proceed)], чтобы начать их лизать, издавая при этом серию [npc.moans+].",
					"Целуя и лизая [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] из [npc.a_moan+], как [npc.she] [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.namePos] [npc2.feet], прежде чем начать целовать и лизать их."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "С жадностью боготворить [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "С жадностью целовать и лизать [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Осыпав [npc2.namePos] [npc2.feet] серией страстных поцелуев, [npc.name] [npc.verb(proceed)] с энтузиазмом приняться их вылизывать, издавая при этом серию [npc.moans+].",
					"Страстно целуя и лизая [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] слегка [npc.a_moan+], пока [npc.she] счастливо [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.namePos] [npc2.feet], прежде чем начать жадно целовать и лизать их."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться поклонению [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свои [npc.face] от [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] отчаянно [npc.verb(try)] и [npc.verb(fail)], чтобы оттащить [npc.her] [npc.face+] от [npc2.namePos] [npc2.feet+], издавая при этом [npc.a_sob+].",
					"[npc.A_sob+] вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.namePos] [npc2.feet+] от [npc.her] [npc.face].",
					"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.herHim] забрал(а) [npc2.her] [npc2.feet] от [npc.her] [npc.face]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Полностью игнорируя протесты [npc.namePos], [npc2.name] мягко, но решительно [npc2.verb(continue)], чтобы прижать [npc2.her] [npc2.feet] к [npc.her] рту, заставляя [npc.herHim] боготворить [npc2.her] [npc2.feet+].",
							" Тихо [npc2.moaning] в восторге, [npc2.name] полностью [npc2.verb(ignore)] протесты [npc.namePos] и [npc2.verb(continue)], чтобы втиснуть [npc2.her] [npc2.feet+] в [npc.her] [npc.face].",
							" Не обращая внимания на попытки [npc.namePos] вырваться, [npc2.name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.feet+] против [npc.namePos] рта."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Полностью игнорируя протесты [npc.namePos], [npc2.name] властно [npc2.verb(push)] [npc2.her] [npc2.feet] в рот [npc.her], грубо заставляя [npc.herHim] поклоняться [npc2.her] [npc2.feet+].",
							" В восторге [npc2.Moaning+], [npc2.name] полностью [npc2.verb(ignore)] [npc.namePos] протесты и [npc2.verb(continue)] грубо втирать [npc2.her] [npc2.feet+] в [npc.her] [npc.face].",
							" Не обращая никакого внимания на попытки [npc.namePos] вырваться, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] силой [npc2.verb(push)] [npc2.her] [npc2.feet+] в рот [npc.namePos]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Полностью игнорируя протесты [npc.namePos], [npc2.name] решительно [npc2.verb(continue)], чтобы прижать [npc2.her] [npc2.feet] к рту [npc.her], заставляя [npc.herHim] поклоняться [npc2.her] [npc2.feet+].",
							" В восторге [npc2.Moaning+], [npc2.name] полностью [npc2.verb(ignore)] [npc.namePos] протесты и [npc2.verb(continue)] решительно вжимать [npc2.her] [npc2.feet+] в [npc.her] [npc.face].",
							" Не обращая внимания на попытки [npc.namePos] вырваться, [npc2.name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.feet+] против [npc.namePos] рта."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Хватит поклоняться [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Убери свой [npc.face] от [npc2.namePos] [npc2.feet] и перестань им поклоняться.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Оставив последний грубый поцелуй на [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(lift)] голову [npc.her] и [npc.verb(bring)] поклонению [npc.her].",
							"В последний раз властно вылизав [npc2.namePos] [npc2.toes+], [npc.name] затем [npc2.verb(pull)] назад, кланясь в завершение [npc.her] поклонения [npc2.namePos] [npc2.feet+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Оставив последний поцелуй на [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(lift)] голову [npc.her] и [npc.verb(bring)] поклонению [npc.her].",
							"В последний раз жадно вылизав [npc2.namePos] [npc2.toes+], [npc.name] затем [npc2.verb(pull)] назад, кланясь в завершение [npc.her] поклонения [npc2.namePos] [npc2.feet+]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] не может сдержать облегченного всхлипа, когда [npc.name] отстраняется, прежде чем с надеждой спросить, закончилось ли теперь испытание [npc2.her].",
							" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] бороться против [npc.name], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(beg)] остаться в одиночестве."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] назад, жаждая еще большего внимания [npc.her].",
							" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] похоть к [npc.namePos] [npc.tongue+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Actions related to someone receiving foot worship:
	
	public static final SexAction FOOT_ORAL_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Заставить поклоняться [npc.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] опустить [npc2.her] голову к твоим [npc.feet] и начать орально поклоняться им.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean foundFootType = false;
			if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
				if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] голову к твердым копытам [npc.her], прежде чем заставить [npc2.herHim] поцеловать их и начать орально поклоняться [npc.her] [npc.feet+].",
							"Прижимая голову [npc2.namePos] к твердым копытам [npc.her], [npc.name] не может не [npc.moanVerb+], пока [npc.she] [npc.verb(make)] [npc2.herHim] целует и орально боготворит [npc.her] [npc.feet+]."));
					
				} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] голову к птичьим лапам [npc.her], прежде чем заставить [npc2.herHim] целовать когти [npc.her] и начать орально боготворить [npc.her] [npc.feet+].",
							"Прижимая голову [npc2.namePos] к птичьим лапам [npc.her], [npc.name] не может не [npc.moanVerb+], пока [npc.she] [npc.verb(make)] [npc2.herHim] целует когти [npc.her] и орально боготворит [npc.her] [npc.feet+]."));
					
				} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] голову к щупальцам [npc.her], прежде чем заставить [npc2.herHim] целовать их и начать орально боготворить.",
							"Прижимая голову [npc2.namePos] к щупальцам [npc.her], [npc.name] не может не [npc.moanVerb+], пока [npc.she] [npc.verb(make)] [npc2.herHim] целует и орально боготворит их."));
				}
			}
			if(!foundFootType) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] голову к [npc.her] [npc.feet], прежде чем заставить [npc2.herHim] целовать их и начать орально боготворить.",
						"Прижимая голову [npc2.namePos] к [npc.her] [npc.feet], [npc.name] не может не [npc.moanVerb+], пока [npc.she] [npc.verb(make)] [npc2.herHim] целует и орально боготворит их."));
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet] против [npc2.her] рта, отчаянно [npc2.verb(try)], чтобы отстраниться.",
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] [npc.eagerly] [npc.verb(press)] [npc.her] [npc.feet+] против [npc2.her] рта."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.eagerly] [npc2.verb(plant)] серию поцелуев и облизываний [npc.namePos] [npc.feet].",
								" С [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(start)], целуя и облизывая [npc.namePos] [npc.feet+]."));
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
				case SUB_NORMAL:
				case DOM_GENTLE:
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc.eagerly] [npc2.verb(lick)] и [npc2.verb(suck)] на [npc.namePos] [npc.toes+], издавая [npc2.a_moan+], пока [npc2.she] [npc2.eagerly] [npc2.verb(worship)] [npc.her] [npc.feet+].",
							" [npc2.A_moan+] вырывается из [npc2.namePos] рта, пока [npc2.she] [npc2.verb(continue)] [npc.eagerly], облизывая и целуя [npc.namePos] [npc.feet+].",
							" [npc2.Moaning] в восторге, [npc2.name] [npc.eagerly] [npc2.verb(worship)] [npc.namePos] [npc.feet+], осыпая их серией страстных поцелуев."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттащить [npc2.her] [npc2.face] от [npc.namePos] [npc.feet], [npc2.name] [npc2.verb(let)] из [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], чтобы вырваться на свободу.",
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.namePos] [npc.feet] от [npc2.her] лица.",
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.face] от [npc.namePos] [npc.feet+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное [npc.feet(true)] поклонение";
		}
		@Override
		public String getActionDescription() {
			return "Мягко продолжай втискивать свои [npc.feet+] в [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко вдавливая [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] тихо [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.has] их в оральном поклонении.",
					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(push)] [npc.her] [npc.feet+] против [npc2.namePos] [npc2.lips+], не оставляя [npc2.herHim] иного выбора, кроме как продолжать поклоняться им.",
					"Медленно вдавливая [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] не может не издать тихий [npc.moan], пока [npc.she] [npc.has] их в поклонении."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "[npc.Feet(true)] поклонение";
		}
		@Override
		public String getActionDescription() {
			return "Продолжай втискивать свои [npc.feet+] в [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly] вдавливает [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] тихо [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.has] их в оральном поклонении.",
					"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet+] против [npc2.namePos] [npc2.lips+], не оставляя [npc2.herHim] иного выбора, кроме как продолжать им поклоняться.",
					"[npc.Eagerly], вдавливая [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] не может не издать [npc.a_moan+], пока [npc.she] [npc.has] ими в поклонении."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубое [npc.feet(true)] поклонение";
		}
		@Override
		public String getActionDescription() {
			return "Продолжай грубо вжимать свои [npc.feet+] в [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly] вдавливает [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] тихо [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.has] их в оральном поклонении.",
					"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet+] против [npc2.namePos] [npc2.lips+], не оставляя [npc2.herHim] иного выбора, кроме как продолжать им поклоняться.",
					"[npc.Eagerly], вдавливая [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] не может не издать [npc.a_moan+], пока [npc.she] [npc.has] ими в поклонении."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "[npc.Feet(true)] поклонение";
		}
		@Override
		public String getActionDescription() {
			return "Продолжай втискивать свои [npc.feet+] в [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вдавливая [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.has] ими через оральное поклонение.",
					"С [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.feet+] против [npc2.namePos] [npc2.lips+], не оставляя [npc2.herHim] иного выбора, кроме как продолжать им поклоняться.",
					"Вдавливая [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] не может не издать [npc.a_moan+], пока [npc.she] [npc.has] ими в поклонении."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадное [npc.feet(true)] поклонение";
		}
		@Override
		public String getActionDescription() {
			return "С жадностью продолжай впихивать свой [npc.feet+] в [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly] вдавливает [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] тихо [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.has] их в оральном поклонении.",
					"С [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet+] против [npc2.namePos] [npc2.lips+], не оставляя [npc2.herHim] иного выбора, кроме как продолжать им поклоняться.",
					"[npc.Eagerly], вдавливая [npc.her] [npc.feet+] в [npc2.namePos] [npc2.face], [npc.name] не может не издать [npc.a_moan+], пока [npc.she] [npc.has] ими в поклонении."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться [npc.feet(true)] поклонению";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.feet+] от [npc2.namePos] [npc2.face+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] отчаянно [npc.verb(try)] и [npc.verb(fail)], чтобы оттащить [npc.her] [npc.feet] от [npc2.namePos] [npc2.face], издавая при этом [npc.a_sob+].",
					"[npc.A_sob+] вырывается из между [npc.namePos] [npc.lips], пока [npc.she] слабо пытается оттолкнуть [npc2.namePos] [npc2.face+] от [npc.her] [npc.feet].",
					"[npc.Sobbing] в смятении, [npc.name] слабо [npc.verb(struggle)] против [npc2.name], пока [npc.she] [npc.verb(plead)], чтобы [npc2.herHim] убрал(а) [npc2.her] рот от [npc.her] [npc.feet]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Полностью игнорируя протесты [npc.namePos], [npc2.name] мягко, но уверенно [npc2.verb(continue)], чтобы поклоняться [npc.her] [npc.feet+], осыпая их серией облизываний и поцелуев.",
							" Тихо [npc2.moaning] от восторга, [npc2.name] полностью [npc2.verb(ignore)] протесты [npc.namePos] и [npc2.verb(continue)], чтобы орально поклоняться [npc.her] [npc.feet+].",
							" Не обращая внимания на попытки [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.feet+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Полностью игнорируя протесты [npc.namePos], [npc2.name] властно [npc2.verb(continue)], чтобы поклоняться [npc.her] [npc.feet+], осыпая их серией грубых облизываний и поцелуев.",
							" [npc2.Moaning+] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] [npc.namePos] протесты и [npc2.verb(continue)] с силой поклоняться [npc.her] [npc.feet+].",
							" Совершенно не обращая внимания на борьбу [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] с силой [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.feet+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Полностью игнорируя протесты [npc.namePos], [npc2.name] [npc.eagerly] [npc2.verb(continue)], чтобы поклоняться [npc.her] [npc.feet+], осыпая их серией лизьев и поцелуев.",
							" [npc2.Moaning+] в восторге, [npc2.name] совершенно [npc2.verb(ignore)] [npc.namePos] протесты и [npc2.verb(continue)], чтобы орально поклоняться [npc.her] [npc.feet+].",
							" Не обращая внимания на попытки [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.feet+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить [npc.feet(true)] поклонение";
		}
		@Override
		public String getActionDescription() {
			return "Убери свои [npc.feet+] от [npc2.namePos] [npc2.face+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Грубо ткнув [npc.her] [npc.feet+] в [npc2.namePos] лицо в последний раз, [npc.name] [npc.verb(pull)] их прочь и [npc.verb(put)] оральному поклонению [npc2.her].",
						"[npc.Name] [npc.verb(grind)] [npc.her] [npc.feet+] в [npc2.namePos] лицо в последний раз, после чего издает забавный [npc.moan] и внезапно отрывает их от [npc2.her] рта."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"После того как [npc.eagerly] [npc.her] [npc.feet+] в [npc2.namePos] лицо в последний раз, [npc.name] [npc.verb(pull)] их прочь и [npc.verb(put)] оральному поклонению [npc2.her].",
						"[npc.Name] [npc.verb(push)] [npc.her] [npc.feet+] в [npc2.namePos] лицо в последний раз, прежде чем выдохнуть [npc.a_moan+] и решительно отстранить их от [npc2.her] рта."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] облегченно вздыхает, но вскоре этот вздох переходит в [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)] понимает, что [npc.name] [npc.has] еще не закончил с [npc2.herHim].",
						" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание продолжать лизать и целовать [npc.her] [npc.feet]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
