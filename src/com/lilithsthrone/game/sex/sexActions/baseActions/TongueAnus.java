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
 * @since 0.1.88
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueAnus {

	public static final SexAction ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Начать анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Просунуть свой [npc.tongue] в [npc2.namePos] [npc2.asshole+] и начать анилингус.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] серию мягких поцелуев в [npc2.her] щеки, прежде чем медленно, но уверенно вогнать [npc.her] [npc.tongue+] в [npc2.her] [npc2.asshole+].",

							"Осыпая [npc2.namePos] [npc2.assCloaca+] серией мягких поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] долгим влажным языком, прежде чем осторожно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] щеки, прежде чем отчаянно вогнать [npc.her] [npc.tongue+] в [npc2.her] [npc2.asshole+].",

							"Осыпая [npc2.namePos] [npc2.assCloaca+] серией страстных поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] жадным облизыванием, прежде чем алчно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо втирая [npc.her] [npc.lips+] в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] серию напористых поцелуев в [npc2.her] щеки, прежде чем жадно вогнать [npc.her] [npc.tongue+] в [npc2.her] [npc2.asshole+].",

							"Осыпая [npc2.namePos] [npc2.assCloaca+] серией властных поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] грубым языком, а затем жадно проталкивая [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] щеки, прежде чем отчаянно вогнать [npc.her] [npc.tongue+] в [npc2.her] [npc2.asshole+].",

							"Осыпая [npc2.namePos] [npc2.assCloaca+] серией страстных поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] жадным облизыванием, прежде чем алчно протолкнуть [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.lips+] к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(plant)] серию поцелуев в [npc2.her] щеки, прежде чем скользнуть [npc.her] [npc.tongue+] в [npc2.her] [npc2.asshole+].",

							"Осыпая [npc2.namePos] [npc2.assCloaca+] серией поцелуев, [npc.name] [npc.verb(give)] [npc2.her] [npc2.asshole+] влажным языком, а затем проталкивая [npc.her] [npc.tongue+] глубоко внутрь."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ тихий [npc2.moan], мягко выгибая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] продолжал.",
	
								" Мягко выгибая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное внимание, [npc2.name] громко [npc2.moanVerb], умоляя [npc.herHim] продолжать."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], жадно выгибая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] продолжал.",
	
								" Отчаянно выталкивая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное внимание, [npc2.name] [npc2.moanVerb] вслух, умоляя [npc.herHim] продолжить."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, грубо выталкивая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы не останавливаться.",
	
								" Грубо выталкивая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face] в жадном ответе на [npc.her] оральное внимание, [npc2.name] [npc2.moanVerb] вслух, приказывая [npc.herHim] продолжить."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], жадно выгибая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] продолжал.",
	
								" Отчаянно выталкивая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное внимание, [npc2.name] [npc2.moanVerb] вслух, умоляя [npc.herHim] продолжить."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, выталкивая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] продолжал(а).",
	
								" Выталкивая [npc2.her] [npc2.assCloaca] навстречу [npc.namePos] [npc.face] в ответ на [npc.her] оральное внимание, [npc2.name] [npc2.moanVerb] вслух, умоляя [npc.herHim] продолжить."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(try)], пытаясь вырваться из-под нежелательного орального внимания [npc.namePos], [npc2.sobbing] и извиваясь, пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое.",
	
								" [npc2.A_sob+] вырывается изо рта [npc2.namePos] и, борясь с [npc.name], [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] убрал(а) [npc.her] [npc.tongue] от [npc2.her] ануса."));
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
							" [npc2.Name] с жадностью [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно выгибаясь, [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) обслуживать [npc2.her] [npc2.assCloaca].",
		
							" В восторге от [npc2.Moaning], [npc2.name] жадно [npc2.verb(grind)] [npc2.her] [npc2.assCloaca+] вниз на [npc.namePos] [npc.face], прежде чем умоляюще просить [npc.herHim] вогнать [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттолкнуть [npc2.her] [npc2.assCloaca] от нежелательного [npc.tongue] [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться на свободу.",
		
							" [npc2.A_sob+] внезапно вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.she] [npc.verb(continue)] лизать и целовать [npc2.her] [npc2.asshole+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь отпрянуть от [npc.namePos] [npc.tongue], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)] орально обслуживать [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] против [npc.namePos] [npc.lips+].",
		
							" Мягкий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, мягко подталкивая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал обслуживать [npc2.her] [npc2.assCloaca].",
		
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(press)] [npc2.her] [npc2.assCloaca+] вниз на [npc.namePos] [npc.face], прежде чем жадно умолять [npc.herHim] вогнать [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.asshole+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо вжимая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать обслуживать [npc2.her] [npc2.assCloaca].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с силой [npc2.verb(press)] [npc2.her] [npc2.assCloaca+] вниз на [npc.namePos] [npc.face], после чего агрессивно приказывает [npc.herHim] вгонять [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] против [npc.namePos] [npc.lips+].",
		
							" [npc2.A_moan] выскальзывает из между [npc2.namePos] [npc2.lips+], и, подбрасывая [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал обслуживать [npc2.her] [npc2.assCloaca].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.assCloaca+] вниз на [npc.namePos] [npc.face], после чего умоляя [npc.herHim] вгонять [npc.her] [npc.tongue+] как можно глубже в [npc2.her] [npc2.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Нежно лизать [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко вонзая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] вплотную к [npc2.her] [npc2.asshole+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] чтобы нежно поцеловать и облизать [npc2.namePos] [npc2.assCloaca+], прежде чем снова податься вперед и медленно скользнуть [npc.her] [npc.tongue] в [npc2.her] [npc2.asshole+].",

					"Вытягивая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] медленно целуя и прижимаясь к [npc2.namePos] [npc2.assCloaca+], прежде чем наклониться вперед и мягко вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать вбивать свой [npc.tongue] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Страстно вонзая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] вплотную к [npc2.her] [npc2.asshole+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] чтобы жадно поцеловать и облизать [npc2.namePos] [npc2.assCloaca+], прежде чем снова податься вперед и алчно скользнуть [npc.her] [npc.tongue] в [npc2.her] [npc2.asshole+].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc2.namePos] [npc2.assCloaca+], прежде чем наклониться вперед и с энтузиазмом вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Грубо вонзи свой язык в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо проталкивая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(grind)] [npc.her] [npc.lips+] к [npc2.her] [npc2.asshole+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], чтобы грубо поцеловать и облизать [npc2.namePos] [npc2.assCloaca+], прежде чем податься вперед и снова яростно вонзить [npc.her] [npc.tongue] в [npc2.her] [npc2.asshole+].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], властно целуя и облизывая [npc2.namePos] [npc2.assCloaca+], прежде чем наклониться вперед и грубо вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANILINGUS_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться анилингусу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.tongue] из [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] попыталась оттянуть [npc.her] [npc.face] назад, но [npc2.name] [npc2.verb(continue)] мягко прижимая [npc2.her] [npc2.asshole+] к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] яростно [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"С [npc.a_sob+], [npc.name] [npc.verb(try)] пытается отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.face] обратно в [npc2.her] [npc2.assCloaca+], нежно растирая [npc2.herself] о [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и из-за [npc.a_sob+], [npc.she] [npc.verb(try)] пытается оттянуть [npc.her] рот от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя протесты [npc.her], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.asshole+] против [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] пытается оттянуть [npc.her] [npc.face] назад, но [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.asshole+] вниз к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] неистово [npc2.verb(force)] [npc2.herself] на [npc.herHim].",

							"[npc.a_sob+], [npc.name] [npc.verb(try)] отстраниться от [npc2.name], но [npc2.she] яростно [npc2.verb(force)] [npc.her] [npc.face] обратно в [npc2.her] [npc2.assCloaca+], грубо втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+] [npc.she] [npc.verb(try)] отстранить рот [npc.her] от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] положение, игнорируя протесты [npc.her], пока [npc2.she] грубо [npc2.verb(grind)] [npc2.her] [npc2.asshole+] о [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] оттянуть [npc.her] [npc.face] назад, но [npc2.name] [npc2.verb(continue)], жадно вжимая [npc2.her] [npc2.asshole+] вниз к [npc.her] [npc.lips+], удерживая [npc.herHim] на месте, пока [npc2.she] [npc2.verb(force)] [npc2.herself] о [npc.herHim].",

							"[npc.a_sob+], [npc.name] [npc.verb(try)] отстраниться от [npc2.name], но [npc2.she] быстро [npc2.verb(force)] [npc.her] [npc.face] обратно в [npc2.her] [npc2.assCloaca+], жадно втирая [npc2.herself] в [npc.herHim], пока [npc2.she] [npc2.verb(ignore)] [npc.her] извивается.",

							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы оттянуть [npc.her] рот от [npc2.namePos] [npc2.assCloaca+], но [npc2.name] быстро [npc2.verb(shift)] позицию, игнорируя [npc.her] протесты, пока [npc2.she] жадно [npc2.verb(press)] [npc2.her] [npc2.asshole+] против [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать вбивать свой [npc.tongue] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Вгоняя [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] вплотную к [npc2.her], пока [npc2.asshole+] [npc.verb(let)] приглушенный [npc.moan].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], чтобы поцеловать и облизать [npc2.namePos] [npc2.assCloaca+], прежде чем снова податься вперед и скользнуть [npc.her] [npc.tongue] в [npc2.her] [npc2.asshole+].",

					"Вытягивая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], целуя и прижимаясь к [npc2.namePos] [npc2.assCloaca+], прежде чем наклониться вперед и вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Жадно вонзи свой [npc.tongue] в [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Страстно вонзая [npc.her] [npc.tongue+] как можно глубже в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] вплотную к [npc2.her] [npc2.asshole+] и [npc.verb(let)] приглушенный [npc.moan].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)] чтобы жадно поцеловать и облизать [npc2.namePos] [npc2.assCloaca+], прежде чем снова податься вперед и алчно скользнуть [npc.her] [npc.tongue] в [npc2.her] [npc2.asshole+].",

					"Вынимая [npc.her] [npc.tongue+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(start)], счастливо целуя и прижимаясь к [npc2.namePos] [npc2.assCloaca+], прежде чем наклониться вперед и с энтузиазмом вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.asshole+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.tongue] из [npc2.namePos] [npc2.asshole+] и прекратить анилингус.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним грубым лизком [npc.name] [npc.verb(pull)] [npc.her] [npc.face] от [npc2.namePos] [npc2.asshole+].",
	
							"Дав [npc2.namePos] [npc2.asshole+] последний грубый поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc2.her] [npc2.assCloaca+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Последним лизком [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc2.namePos] [npc2.asshole+].",
	
							"Дав [npc2.namePos] [npc2.asshole+] последний влажный поцелуй, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] прочь от [npc2.her] [npc2.assCloaca+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)], пытаясь сопротивляться [npc.name], [npc2.sobbing] и ерзая от дискомфорта, когда [npc2.she] понимает, что [npc.name] еще не совсем закончил с [npc2.herHim].",
		
								" Realising that [npc.sheHasFull]n't completely finished with [npc2.herHim] just yet, [npc2.name] [npc2.verb(continue)] struggling and [npc2.sobbing], tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.name] to let [npc2.herHim] go."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] уводит [npc.her] [npc.tongue+] прочь от [npc2.her] [npc2.assCloaca+].",
								
								" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], пока [npc.name] [npc.verb(stop)], доставляя [npc2.her] [npc2.asshole+], выдавая [npc2.her] желание получить еще больше [npc.namePos] внимания."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction RECEIVING_ANILINGUS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Получить анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать лизать твою [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(start)] медленно растирает [npc.her] [npc.asshole+] по [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] втиснулся в [npc.her] [npc.assCloaca+], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(start)] мягко прижимает [npc.her] [npc.asshole+] к [npc2.her] [npc2.lips+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово растирает [npc.her] [npc.asshole+] по [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] втиснулся в [npc.her] [npc.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно прижимает [npc.her] [npc.asshole+] к [npc2.her] [npc2.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо впечатывая [npc.her] [npc.assCloaca+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] с силой растирает [npc.her] [npc.asshole+] по [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] втиснулся в [npc.her] [npc.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо втирает [npc.her] [npc.asshole+] в [npc2.her] [npc2.lips+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Нетерпеливо прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово растирает [npc.her] [npc.asshole+] по [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] втиснулся в [npc.her] [npc.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно прижимает [npc.her] [npc.asshole+] к [npc2.her] [npc2.lips+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] втирает [npc.her] [npc.asshole+] в [npc2.her] [npc2.lips+].",

							"Сдвигая [npc.her] [npc.hips] так, чтобы [npc2.namePos] [npc2.face] втиснулся в [npc.her] [npc.assCloaca+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] прижимает [npc.her] [npc.asshole+] к [npc2.her] [npc2.lips+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] медленно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] нежно лижет и целует [npc.her] [npc.assCloaca+].",
	
								" Мягко вытягивая [npc2.her] [npc2.tongue], чтобы сделать долгий, медленный лизь по [npc.assCloaca+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.asshole+] нежными поцелуями."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] с наслаждением лижет и целует [npc.her] [npc.assCloaca+].",
	
								" Жадно высунув [npc2.her] [npc2.tongue], чтобы провести долгий влажный язык по [npc.assCloaca+], прижатой к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.asshole+] серией страстных поцелуев."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] с силой [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] грубо лижет и целует [npc.her] [npc.assCloaca+].",
	
								" Жадно выталкивая [npc2.her] [npc2.tongue], чтобы провести грубый влажный язык по [npc.assCloaca+], прижатой к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.asshole+] серией напористых поцелуев."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] с наслаждением лижет и целует [npc.her] [npc.assCloaca+].",
	
								" Жадно высунув [npc2.her] [npc2.tongue], чтобы провести долгий влажный язык по [npc.assCloaca+], прижатой к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.asshole+] серией страстных поцелуев."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(slide)] [npc2.her] [npc2.tongue+] в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] облизывает и целует [npc.her] [npc.assCloaca+].",
	
								" Высунув [npc2.her] [npc2.tongue], чтобы провести долгий, влажный языком по [npc.assCloaca+], прижимаясь к [npc2.her] [npc2.lips+], [npc2.name] [npc2.verb(let)] приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(start)] осыпает [npc.namePos] [npc.asshole+] поцелуями."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Отчаянно пытаясь вырваться, [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc.her] [npc.asshole+] на [npc2.her] [npc2.lips+].",
	
								" [npc2.Sobbing] и тщетно пытаясь сопротивляться, протесты [npc2.namePos] оказываются напрасными, когда [npc.name] [npc.verb(press)] [npc.her] [npc.assCloaca+] на [npc2.her] [npc2.face]."));
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
							" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] на [npc.namePos] [npc.assCloaca+].",
		
							" Из рта [npc2.namePos] вырывается приглушенный [npc2.moan], и, жадно прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(grind)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.assCloaca], прежде чем жадно вогнать [npc.her] [npc.tongue+] как можно глубже в [npc.her] [npc.asshole+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не сумев оттянуть [npc2.her] [npc2.face] от [npc.namePos] [npc.assCloaca], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, когда [npc.she] [npc.verb(continue)], чтобы прижать [npc.her] [npc.assCloaca+] вниз к [npc2.her] [npc2.face+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] отпрянуть от [npc.namePos] [npc.assCloaca], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)], чтобы вдавить [npc.her] [npc.asshole+] в [npc2.her] [npc2.lips+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+], издавая тихий, приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.assCloaca+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos]; мягко прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] медленно проталкивает [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+].",
		
							" От восторга [npc2.Moaning], [npc2.name] нежно [npc2.verb(press)] [npc2.her] [npc2.lips+] к [npc.namePos] [npc.assCloaca], а затем плавно проталкивает [npc2.her] [npc2.tongue+] как можно глубже в [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] о [npc.namePos] [npc.assCloaca+].",
		
							" Приглушенный [npc2.moan] доносится из рта [npc2.namePos]; грубо втирая [npc2.her] [npc2.lips+] в [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] неистово вгоняет [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+].",
		
							" От восторга [npc2.Moaning], [npc2.name] с силой [npc2.verb(press)] [npc2.her] [npc2.lips+] к [npc.namePos] [npc.assCloaca], а затем агрессивно вгоняет [npc2.her] [npc2.tongue+] как можно глубже в [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+], издавая приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.assCloaca+].",
		
							" Приглушенный [npc2.moan] вырывается из рта [npc2.namePos]; прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.assCloaca+], [npc2.she] [npc2.verb(start)] вгоняет [npc2.her] [npc2.tongue+] глубоко в [npc.namePos] [npc.asshole+].",
		
							" Восторженно [npc2.Moaning], [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.lips+] против [npc.namePos] [npc.assCloaca], прежде чем вонзить [npc2.her] [npc2.tongue+] как можно глубже в [npc.her] [npc.asshole+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Мягко принимать анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Мягко прижать свою [npc.assCloaca+] к лицу [npc2.namePos], чтобы вогнать [npc2.her] [npc2.tongue+] в твою [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] уверенно [npc.verb(plant)] [npc.her] [npc.asshole+] поверх [npc2.her] [npc2.lips+].",

					"С мягким [npc.moan], [npc.name] [npc.verb(press)] [npc.her] [npc.assCloaca+] против [npc2.namePos] [npc2.face+], а затем нежно растирая [npc.her] [npc.asshole+] о [npc2.her] [npc2.lips+].",

					"Издавая мягкий [npc.moan], [npc.name] нежно [npc.verb(grind)] [npc.her] [npc.asshole+] вниз, к [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Получить анилингус";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью прижать свой [npc.assCloaca+] к лицу [npc2.namePos], чтобы вжать [npc2.her] [npc2.tongue+] в свою [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] решительно [npc.verb(plant)] [npc.her] [npc.asshole+] поверх [npc2.her] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] с жадностью [npc.verb(press)] [npc.her] [npc.assCloaca+] о [npc2.namePos] [npc2.face+], а затем алчно втирая [npc.her] [npc.asshole+] в [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] с жадностью [npc.verb(grind)] [npc.her] [npc.asshole+] о [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо принимать анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Грубо втирать свой [npc.asshole+] в [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Грубо прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] неистово [npc.verb(slam)] [npc.her] [npc.asshole+] поверх [npc2.her] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] грубо [npc.verb(slam)] [npc.her] [npc.assCloaca+] о [npc2.namePos] [npc2.face+], а затем с силой втирая [npc.her] [npc.asshole+] в [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.asshole+] о [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться анилингусу";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.asshole+] от [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)], чтобы оттянуть [npc.her] [npc.assCloaca] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] осторожно [npc2.verb(slide)] [npc2.her] [npc2.tongue] глубоко в [npc.her] [npc.asshole+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.asshole+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] нежный поцелуй в [npc.her] [npc.assCloaca+], прежде чем мягко проскользнуть [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.asshole+].",

							"С [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca+] от [npc2.namePos] [npc2.lips+], но [npc2.she] удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] осторожно втискивает [npc2.her] [npc2.tongue] в [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)], чтобы оттянуть [npc.her] [npc.assCloaca] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] грубо [npc2.verb(slide)] [npc2.her] [npc2.tongue] глубоко в [npc.her] [npc.asshole+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.asshole+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] влажный поцелуй в [npc.her] [npc.assCloaca+], прежде чем грубо вонзить [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.asshole+].",

							"При [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca+] от [npc2.namePos] [npc2.lips+], но [npc2.she] крепко удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] [npc2.verb(continue)] грубо втискивая [npc2.her] [npc2.tongue] в [npc.her] [npc.asshole+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca] от [npc2.namePos] [npc2.face], издавая [npc.a_sob+], пока [npc2.name] жадно [npc2.verb(slide)] [npc2.her] [npc2.tongue] глубоко в [npc.her] [npc.asshole+].",

							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.asshole+] от [npc2.namePos] [npc2.lips+]. Игнорируя [npc.her] протесты, [npc2.name] [npc2.verb(hold)] [npc.name] на месте, пока [npc2.she] [npc2.verb(plant)] страстный поцелуй в [npc.her] [npc.assCloaca+], прежде чем жадно проталкивая [npc2.her] [npc2.tongue+] глубоко в [npc.her] [npc.asshole+].",

							"Пока [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.assCloaca+] от [npc2.namePos] [npc2.lips+], [npc2.she] крепко удерживает [npc.herHim] на месте, игнорируя [npc.her] протесты, пока [npc2.she] с жадностью [npc2.verb(continue)] [npc2.her] [npc2.tongue] в [npc.her] [npc.asshole+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Получить анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Прижми свой [npc.asshole+] к [npc2.namePos] [npc2.face+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] уверенно [npc.verb(plant)] [npc.her] [npc.asshole+] поверх [npc2.her] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.assCloaca+] против [npc2.namePos] [npc2.face+], прежде чем втирать [npc.her] [npc.asshole+] в [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.asshole+] вниз к [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью принимать анилингус";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью втирай свой [npc.asshole+] в [npc2.namePos] [npc2.tongue+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью прижимая [npc.her] [npc.assCloaca+] к [npc2.namePos] [npc2.face+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] решительно [npc.verb(plant)] [npc.her] [npc.asshole+] поверх [npc2.her] [npc2.lips+].",

					"С [npc.a_moan+], [npc.name] с жадностью [npc.verb(press)] [npc.her] [npc.assCloaca+] о [npc2.namePos] [npc2.face+], а затем алчно втирая [npc.her] [npc.asshole+] в [npc2.her] [npc2.lips+].",

					"Издавая [npc.a_moan+], [npc.name] с жадностью [npc.verb(grind)] [npc.her] [npc.asshole+] о [npc2.namePos] [npc2.lips]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction RECEIVING_ANILINGUS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить анилингус";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.tongue+] из твоей [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо отрывая голову [npc2.namePos] от [npc.her] [npc.asshole+], [npc.name] [npc.verb(order)] [npc2.herHim], чтобы прекратить анилингус на [npc.herHim].",

							"Грубо втирая [npc.her] [npc.asshole+] в [npc2.namePos] [npc2.face] в последний раз, [npc.name], а затем [npc.verb(pull)] [npc.her] [npc.assCloaca] прочь, завершая анилингус."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Отрывая голову [npc2.namePos] от [npc.her] [npc.asshole+], [npc.name] [npc.verb(tell)] [npc2.herHim], чтобы прекратить анилингус на [npc.herHim].",

							"Вжимая [npc.her] [npc.asshole+] в [npc2.namePos] [npc2.face] в последний раз, [npc.name], а затем [npc.verb(pull)] [npc.her] [npc.assCloaca] прочь, завершая анилингус."));
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
								" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.namePos] еще больше [npc.asshole+] [npc2.her] орального внимания.",
	
								" Все еще жаждая добавки, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(move)] от [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
