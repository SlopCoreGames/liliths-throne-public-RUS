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
public class TongueArmpit {
	
	public static final SexAction ARMPIT_LICKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Лизать подмышку";
		}
		@Override
		public String getActionDescription() {
			return "Проведи своим [npc.tongue] по [npc2.namePos] [npc2.armpit+] и начни её вылизывать.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко приподнимая [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.her] [npc2.armpit+], и, засыпав её нежными поцелуями, [npc.she] [npc.verb(start)] жадно водя [npc.her] [npc.tongue+] по открытой подмышке [npc2.her].",

							"Приподнимая [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] серию мягких поцелуев в [npc2.namePos] [npc2.armpit+], прежде чем [npc.moaning+], когда [npc.she] [npc.verb(start)] осторожно ведет [npc.her] [npc.tongue+] по открытой подмышке [npc2.her]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо приподнимая [npc2.namePos] [npc2.arm+(true)], [npc.name] отчаянно [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.her] [npc2.armpit+], и, засыпав её страстными поцелуями, [npc.she] [npc.verb(start)] жадно водя [npc.her] [npc.tongue+] по открытой подмышке [npc2.her].",

							"Нетерпеливо приподнимая [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.namePos] [npc2.armpit+], прежде чем [npc.moaning+], когда [npc.she] [npc.verb(start)] жадно ведет [npc.her] [npc.tongue+] по открытой подмышке [npc2.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо задрав [npc2.namePos] [npc2.arm+(true)], [npc.name] с силой [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.armpit+], а затем осыпав это серией властных поцелуев, [npc.she] [npc.verb(start)] жадно скользя [npc.her] [npc.tongue+] по обнаженной подмышке [npc2.her].",

							"Яростно задрав [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] серию напористых поцелуев в [npc2.namePos] [npc2.armpit+], после чего [npc.moaning+], пока [npc.she] [npc.verb(start)] властно скользит [npc.her] [npc.tongue+] по обнаженной подмышке [npc2.her]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Подняв [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.her] [npc2.armpit+], и после серии поцелуев, [npc.she] [npc.verb(start)] скользит [npc.her] [npc.tongue+] по обнаженной подмышке [npc2.her].",

							"Подняв [npc2.namePos] [npc2.arm+(true)], [npc.name] [npc.verb(plant)] серию поцелуев в [npc2.namePos] [npc2.armpit+], после чего [npc.moaning+], пока [npc.she] [npc.verb(start)] скользит [npc.her] [npc.tongue+] по обнаженной подмышке [npc2.her]."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ мягкий [npc2.moan], осторожно выталкивая [npc2.her] [npc2.underarm] против [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] продолжал(а).",
	
								" Мягко выталкивая [npc2.her] [npc2.underarm] навстречу [npc.namePos] [npc.face] в ответ на оральное внимание [npc.her], [npc2.name] громко [npc2.moanVerb], умоляя [npc.herHim] продолжить."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, с жадностью выталкивая [npc2.her] [npc2.underarm] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] не останавливалась.",
	
								" Отчаянно выталкивая [npc2.her] [npc2.underarm] навстречу [npc.namePos] [npc.face] в ответ на оральное внимание [npc.her], [npc2.name] громко [npc2.moanVerb], умоляя [npc.herHim] продолжить."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, грубо выталкивая [npc2.her] [npc2.underarm] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(order)], чтобы [npc.herHim] не останавливалась.",
	
								" Грубо выталкивая [npc2.her] [npc2.underarm] навстречу [npc.namePos] [npc.face] в нетерпеливом ответе на оральное внимание [npc.her], [npc2.name] громко [npc2.moanVerb], приказывая [npc.herHim] продолжить."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, с жадностью выталкивая [npc2.her] [npc2.underarm] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] не останавливалась.",
	
								" Отчаянно выталкивая [npc2.her] [npc2.underarm] навстречу [npc.namePos] [npc.face] в ответ на оральное внимание [npc.her], [npc2.name] громко [npc2.moanVerb], умоляя [npc.herHim] продолжить."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, выталкивая [npc2.her] [npc2.underarm] на [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] продолжал(а).",
	
								" [npc2.her] дергается, [npc2.underarm] выталкивая [npc.namePos] [npc.face] в ответ на [npc.her] оральное внимание, громко [npc2.name] [npc2.moanVerb], умоляя [npc.herHim] продолжать."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(try)], пытаясь вырваться из-под нежелательного орального внимания [npc.namePos], [npc2.sobbing] и извиваясь, пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое.",
	
								" [npc2.A_sob+] вырывается изо рта [npc2.namePos] и, борясь с [npc.name], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] убрал(а) [npc.her] [npc.tongue] из-под [npc2.her] подмышки."));
						break;
					default:
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
							" [npc2.Name] с жадностью [npc2.verb(push)] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.armpit+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+] и, с жадностью толкая в ответ [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) обслуживать [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(grind)] [npc2.her] [npc2.underarm+] вниз на [npc.namePos] [npc.face], прежде чем с нетерпением умолять [npc.herHim] продолжать скользить [npc.her] [npc.tongue+] по [npc2.her] [npc2.armpit+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах отстраниться от нежеланного [npc.tongue] [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name]; она извивается и протестует, пока [npc.she] [npc.verb(continue)] лизать и целовать [npc2.her] [npc2.armpit+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от [npc.namePos] [npc.tongue] и борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)] орально обслуживать [npc2.her] [npc2.armpit+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] мягко [npc2.verb(push)] в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.armpit+] против [npc.namePos] [npc.lips+].",
		
							" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, мягко отталкивая [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал обслуживать [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(press)] [npc2.her] [npc2.underarm+] вниз на [npc.namePos] [npc.face], прежде чем жадно умолять [npc.herHim] продолжать скользить [npc.her] [npc.tongue+] по [npc2.her] [npc2.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(push)] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.armpit+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+] и, грубо втираясь в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжить ласкать [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с силой [npc2.verb(press)] [npc2.her] [npc2.underarm+] вниз, прижимая к [npc.namePos] [npc.face], после чего агрессивно приказывает [npc.herHim] продолжать скользить [npc.her] [npc.tongue+] по [npc2.her] [npc2.armpit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] в ответ, издавая [npc2.a_moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.armpit+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan] выскальзывает из между [npc2.namePos] [npc2.lips+] и, налегая на [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжала обслуживать [npc2.her] [npc2.underarm].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.underarm+] вниз, прижимая к [npc.namePos] [npc.face], после чего умоляя [npc.herHim] продолжать скользить [npc.her] [npc.tongue+] по [npc2.her] [npc2.armpit+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ARMPIT_LICKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное облизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Нежно лижи [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Плавно скользя [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.underarm+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.her] [npc2.armpit+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)], чтобы нежно поцеловать [npc2.namePos] [npc2.underarm+], а затем подавшись вперед и снова медленно проводя [npc.her] [npc.tongue] по [npc2.her] [npc2.armpit+].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)], медленно целуя и прижимаясь к [npc2.namePos] [npc2.underarm+], а затем наклоняясь вперед и с энтузиазмом продолжая лизать [npc2.her] подмышку."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Облизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать водить [npc.tongue] по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью проводя [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.underarm+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] вплотную к [npc2.her] [npc2.armpit+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)], чтобы жадно поцеловать [npc2.namePos] [npc2.underarm+], а затем подавшись вперед и с жадностью снова проводя [npc.her] [npc.tongue] по [npc2.her] [npc2.armpit+].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc2.namePos] [npc2.underarm+], а затем наклоняясь вперед и с восторгом продолжая лизать [npc2.her] подмышку."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубое облизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Грубо провести языком по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо скользя [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.underarm+], [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] вплотную к [npc2.her] [npc2.armpit+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)] чтобы грубо поцеловать [npc2.namePos] [npc2.underarm+], прежде чем податься вперед и с силой снова скользнуть [npc.her] [npc.tongue] по [npc2.her] [npc2.armpit+].",

					"Засовывая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)] с силой целуя и облизывая [npc2.namePos] [npc2.underarm+], прежде чем наклониться вперед и продолжать грубо лизать [npc2.her] подмышку."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться облизыванию подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.tongue] от [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] чтобы оттянуть [npc.her] [npc.face] назад, но [npc2.name] [npc2.verb(continue)] мягко прижимая [npc2.her] [npc2.armpit+] к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"С [npc.a_sob+], [npc.name] [npc.verb(try)] чтобы отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.face] прямо обратно в [npc2.her] [npc2.underarm+], мягко растирая [npc2.herself] о [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] изо всех сил старается вырваться.",

							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] рот от [npc2.namePos] [npc2.underarm+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] грубо [npc2.verb(press)] [npc2.her] [npc2.armpit+] о [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)], чтобы оттянуть [npc.her] [npc.face] назад, но [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.armpit+] вниз на [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] неистово [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"Из-за [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь отстраниться от [npc2.name], но [npc2.she] яростно [npc2.verb(force)] [npc.her] [npc.face] прямо обратно в [npc2.her] [npc2.underarm+], грубо втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В [npc.namePos] [npc.eyes] начинают скапливаться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] рот от [npc2.namePos] [npc2.underarm+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] [npc2.armpit+] о [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попыталась оттянуть [npc.her] [npc.face] назад, но [npc2.name] [npc2.verb(continue)] с нетерпением втиснул [npc2.her] [npc2.armpit+] вниз, прижимая к [npc.her] [npc.lips+] и удерживая [npc.herHim] на месте, пока [npc2.she] [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"С [npc.a_sob+], [npc.name] [npc.verb(try)] попыталась отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.face] обратно в [npc2.her] [npc2.underarm+], жадно втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)] попыталась отстранить [npc.her] рот от [npc2.namePos] [npc2.underarm+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя [npc.her] протесты, пока [npc2.she] с жадностью [npc2.verb(press)] [npc2.her] [npc2.armpit+] против [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Облизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Продолжать водить [npc.tongue] по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Проводя [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.underarm+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] вверх к [npc2.her] [npc2.armpit+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)] попыталась поцеловать [npc2.namePos] [npc2.underarm+], прежде чем податься вперед и снова скользнуть [npc.her] [npc.tongue] по [npc2.her] [npc2.armpit+].",

					"Втискивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)] целует и прижимается к [npc2.namePos] [npc2.underarm+], прежде чем наклониться вперед и продолжить лизать [npc2.her]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадное вылизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Жадно проведи своим [npc.tongue] по [npc2.namePos] [npc2.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью проводя [npc.her] [npc.tongue+] по [npc2.namePos] [npc2.underarm+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] вплотную к [npc2.her] [npc2.armpit+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)], чтобы жадно поцеловать [npc2.namePos] [npc2.underarm+], а затем подавшись вперед и с жадностью снова проводя [npc.her] [npc.tongue] по [npc2.her] [npc2.armpit+].",

					"Втягивая [npc.her] [npc.tongue+] обратно в [npc.her] рот, [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc2.namePos] [npc2.underarm+], а затем наклоняясь вперед и с восторгом продолжая лизать [npc2.her] подмышку."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ARMPIT_LICKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ARMPITS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить лизать подмышки";
		}
		@Override
		public String getActionDescription() {
			return "Оторвать свой [npc.tongue] от [npc2.namePos] [npc2.armpit+] и перестать его лизать.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним грубым мазком языка, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.namePos] [npc2.armpit+].",
	
							"Запечатлев на [npc2.namePos] [npc2.armpit+] последний грубый поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.her] подмышки."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним лизком, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.namePos] [npc2.armpit+].",
	
							"Запечатлев на [npc2.namePos] [npc2.armpit+] последний влажный поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.her] подмышки."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)], пытаясь сопротивляться [npc.name], [npc2.sobbing] и ерзая от дискомфорта, когда [npc2.she] понимает, что [npc.name] еще не совсем закончил с [npc2.herHim].",
		
								" Осознав, что [npc.sheHasFull] еще не совсем закончил с [npc2.herHim], [npc2.name] [npc2.verb(continue)] изо всех сил борется и [npc2.sobbing], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] [npc2.verb(plead)], ожидая, когда [npc.name] отпустит [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, пока [npc2.a_moan+] как [npc.name] забирает [npc.her] [npc.tongue+] у [npc2.her] [npc2.underarm+].",
								
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], пока [npc.name] [npc.verb(stop)] доставляет удовольствие [npc2.her] [npc2.armpit+], выдавая [npc2.her] жажду еще большего [npc.namePos] внимания."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Облизывать подмышку";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать лизать твою [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко приподнимая [npc.her] [npc.arm(true)] и прижимая [npc.her] [npc.underarm+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(start)] медленно растирает [npc.her] [npc.armpit+] по [npc2.her] [npc2.lips+].",

							"Приподнимая [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(start)] мягко прижимает [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Жадно приподнимая [npc.her] [npc.arm(true)] и прижимая [npc.her] [npc.underarm+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово растирает [npc.her] [npc.armpit+] по [npc2.her] [npc2.lips+].",

							"Приподнимая [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно прижимает [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо приподнимая [npc.her] [npc.arm(true)] и с силой вжимая [npc.her] [npc.underarm+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] с силой втирает [npc.her] [npc.armpit+] в [npc2.her] [npc2.lips+].",
									
							"Приподнимая [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо втирает [npc.her] [npc.armpit+] в [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Жадно приподнимая [npc.her] [npc.arm(true)] и прижимая [npc.her] [npc.underarm+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово растирает [npc.her] [npc.armpit+] по [npc2.her] [npc2.lips+].",

							"Приподнимая [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно прижимает [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Приподнимая [npc.her] [npc.arm(true)] и прижимая [npc.her] [npc.underarm+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] втирает [npc.her] [npc.armpit+] в [npc2.her] [npc2.lips+].",

							"Приподнимая [npc.her] [npc.arm(true)], [npc.name] [npc.verb(let)] наружу [npc.a_moan], пока [npc.she] [npc.verb(start)] прижимает [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.lips+]."));
					break;
				case SUB_RESISTING:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] медленно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] нежно лижет и целует [npc.her] [npc.underarm+].",
	
								" [npc2.her] плавно высунув [npc2.tongue], чтобы провести долгий, медленный языком по [npc.underarm+], прижатой к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] нежно целуя [npc.namePos] [npc.armpit+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] счастливо лижет и целует [npc.her] [npc.underarm+].",
	
								" Жадно высунув [npc2.her] [npc2.tongue], чтобы провести долгий, влажный языком по [npc.underarm+], прижатой к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.armpit+] страстными поцелуями."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с силой [npc2.verb(slide)] [npc2.her] [npc2.tongue+] поверх [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] грубо лижет и целует [npc.her] [npc.underarm+].",
	
								" Жадно высунув [npc2.her] [npc2.tongue], чтобы провести грубый, влажный языком по [npc.underarm+], прижатой к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.armpit+] напористыми поцелуями."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] счастливо лижет и целует [npc.her] [npc.underarm+].",
	
								" Жадно высунув [npc2.her] [npc2.tongue], чтобы провести долгий, влажный языком по [npc.underarm+], прижатой к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.armpit+] страстными поцелуями."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(slide)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] облизывает и целует [npc.her] [npc.underarm+].",
	
								" Высунув [npc2.her] [npc2.tongue], чтобы провести долгий, влажный языком по [npc.underarm+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает поцелуями [npc.namePos] [npc.armpit+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь вырваться, [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.armpit+] вниз на [npc2.her] [npc2.lips+].",
	
								" [npc2.Sobbing] и тщетно пытаясь сопротивляться в смятении, протесты [npc2.namePos] оказываются напрасными, так как [npc.name] [npc.verb(press)] [npc.her] [npc.underarm+] вниз на [npc2.her] [npc2.face]."));
						break;
					default:
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
							" [npc2.Name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] в подмышке [npc.her].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], и, с нетерпением прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.underarm+], [npc2.she] [npc2.verb(start)] жадно скользит [npc2.her] [npc2.tongue+] по впадине [npc.her].",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.underarm], после чего с энтузиазмом скользя [npc.her] [npc.tongue+] по [npc.her] [npc.armpit+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттащить [npc2.her] [npc2.face] от [npc.namePos] [npc.underarm], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.she] [npc.verb(continue)], чтобы прижать [npc.her] [npc.underarm+] к [npc2.her] [npc2.face+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] отпрянуть от [npc.namePos] [npc.underarm], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)], чтобы вдавить [npc.her] [npc.armpit+] в [npc2.her] [npc2.lips+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая мягкий, приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.underarm+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos], и, мягко прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.underarm+], [npc2.she] медленно [npc2.verb(start)] скользя [npc2.her] [npc2.tongue+] по ямке [npc.her].",
		
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.underarm], прежде чем плавно провести [npc2.her] [npc2.tongue+] по [npc.her] [npc.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с силой [npc2.verb(run)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.underarm+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos], и, грубо втирая [npc2.her] [npc2.lips+] в [npc.namePos] [npc.underarm+], [npc2.she] [npc2.verb(start)] яростно скользит [npc2.her] [npc2.tongue+] по [npc.her] подмышке.",
		
							" [npc2.Moaning] в восторге, [npc2.name] с силой [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.underarm], прежде чем агрессивно провести [npc2.her] [npc2.tongue+] по [npc.her] [npc.armpit+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(slide)] [npc2.her] [npc2.tongue+] по [npc.namePos] [npc.armpit+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.underarm+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos], и, прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.underarm+], [npc2.she] [npc2.verb(start)] скользит [npc2.her] [npc2.tongue+] по [npc.her] подмышке.",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.underarm], прежде чем скользнуть [npc2.her] [npc2.tongue+] по [npc.her] [npc.armpit+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежное вылизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Мягко прижми свой [npc.underarm+] к лицу [npc2.namePos], чтобы заставить [npc2.her] [npc2.tongue+] по твоей [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] мягко, но уверенно, [npc.verb(plant)] [npc.her] [npc.armpit+] вниз по [npc2.namePos] [npc2.lips+].",

					"С тихим [npc.moan], [npc.name] [npc.verb(press)] [npc.her] [npc.underarm+] против [npc2.namePos] [npc2.face+], прежде чем мягко растирать [npc.her] [npc.armpit+] о [npc2.her] [npc2.lips+].",

					"Издавая тихий [npc.moan], [npc.name] мягко [npc.verb(grind)] [npc.her] [npc.armpit+] вниз против [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Вылизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Жадно прижми свой [npc.underarm+] к лицу [npc2.namePos], чтобы заставить [npc2.her] [npc2.tongue+] по твоей [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(plant)] [npc.her] [npc.armpit+] вниз по [npc2.namePos] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.underarm+] против [npc2.namePos] [npc2.face+], прежде чем алчно растирать [npc.her] [npc.armpit+] о [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] с нетерпением [npc.verb(grind)] [npc.her] [npc.armpit+] вниз, прижимаясь к [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубо лизать подмышку";
		}
		@Override
		public String getActionDescription() {
			return "Грубо прижимай свой [npc.armpit+] к [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.armpit+] вниз по поверхности [npc2.namePos] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(force)] [npc.her] [npc.underarm+] против [npc2.namePos] [npc2.face+], после чего властно втирая [npc.her] [npc.armpit+] в [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.armpit+] вниз, прижимаясь к [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться, когда лижут подмышку";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.armpit+] от [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)], чтобы оттянуть [npc.her] [npc.underarm] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tongue] по поверхности [npc.her] [npc.armpit+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], чтобы оттянуть [npc.her] [npc.armpit+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] мягкий поцелуй в [npc.her] [npc.underarm+], прежде чем нежно скользнуть [npc2.her] [npc2.tongue+] по [npc.her] [npc.armpit+].",

							"Пока [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.underarm+] от [npc2.namePos] [npc2.lips+], [npc2.she] удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] нежно скользит [npc2.her] [npc2.tongue] по [npc.her] [npc.armpit+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.underarm] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] грубо [npc2.verb(slide)] [npc2.her] [npc2.tongue] по [npc.her] [npc.armpit+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.armpit+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] влажный поцелуй в [npc.her] [npc.underarm+], прежде чем грубо скользнуть [npc2.her] [npc2.tongue+] по [npc.her] [npc.armpit+].",

							"Пока [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь оттащить [npc.her] [npc.underarm+] от [npc2.namePos] [npc2.lips+], [npc2.she] крепко удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] грубо скользит [npc2.her] [npc2.tongue] по [npc.her] [npc.armpit+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.underarm] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue] по [npc.her] [npc.armpit+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)] оттащить [npc.her] [npc.armpit+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] страстный поцелуй в [npc.her] [npc.underarm+], прежде чем жадно скользнуть [npc2.her] [npc2.tongue+] по [npc.her] [npc.armpit+].",

							"С [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)] оттащить [npc.her] [npc.underarm+] от [npc2.namePos] [npc2.lips+], но [npc2.she] удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] с нетерпением скользит [npc2.her] [npc2.tongue] по [npc.her] [npc.armpit+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Вылизывание подмышек";
		}
		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.armpit+] к [npc2.namePos] [npc2.face+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] крепко [npc.verb(plant)] [npc.her] [npc.armpit+] вниз на [npc2.namePos] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.underarm+] против [npc2.namePos] [npc2.face+], прежде чем тереть [npc.her] [npc.armpit+] о [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.armpit+] на [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно вылизанная подмышка";
		}
		@Override
		public String getActionDescription() {
			return "Жадно прижимай свой [npc.armpit+] к [npc2.namePos] [npc2.tongue+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(plant)] [npc.her] [npc.armpit+] вниз по [npc2.namePos] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.underarm+] против [npc2.namePos] [npc2.face+], прежде чем алчно растирать [npc.her] [npc.armpit+] о [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] с нетерпением [npc.verb(grind)] [npc.her] [npc.armpit+] вниз, прижимаясь к [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction RECEIVING_ARMPIT_LICKING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ARMPITS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить лизать подмышку";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] убрать [npc2.her] [npc2.tongue+] от твоей [npc.armpit+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо отрывая голову [npc2.namePos] от [npc.her] [npc.armpit+], [npc.name] [npc.verb(order)] [npc2.herHim], чтобы прекратить оральную стимуляцию [npc.herHim].",

							"Грубо в последний раз втирая [npc.her] [npc.armpit+] в [npc2.namePos] [npc2.face], [npc.name], а затем [npc.verb(pull)] уводя [npc.her] [npc.underarm]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отрывая голову [npc2.namePos] от [npc.her] [npc.armpit+], [npc.name] [npc.verb(tell)] [npc2.herHim], чтобы прекратить оральную стимуляцию [npc.herHim].",

							"В последний раз прижимая [npc.her] [npc.armpit+] к [npc2.namePos] [npc2.face], [npc.name], а затем [npc.verb(pull)] уводя [npc.her] [npc.underarm]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Пока слезы катились по [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], так как [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",
	
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] [npc2.verb(continue)], сопротивляясь [npc.name] и умоляя [npc.herHim] отпустить [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.namePos] еще больше [npc.armpit+] [npc2.her].",
	
								" Все еще жаждая добавки, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(move)] от [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
