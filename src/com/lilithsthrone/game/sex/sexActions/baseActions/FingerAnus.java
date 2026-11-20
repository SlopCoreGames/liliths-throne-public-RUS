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
 * @version 0.3.4
 * @author Innoxia
 */
public class FingerAnus {
	
	public static final SexAction ANAL_FINGERING_PROSTATE_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasVagina();
		}
		
		@Override
		public String getActionTitle() {
			return "Массаж простаты";
		}

		@Override
		public String getActionDescription() {
			return "Сверни свои [npc.fingers] внутри [npc2.namePos] [npc2.asshole+] и начни ласкать [npc2.her] простату.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко прижимая ладонь [npc.her] [npc.hand] к [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.asshole], прежде чем согнуть их и начать нежно поглаживать и массировать [npc2.her] простату.",
							"С мягким [npc.moan], [npc.name] осторожно [npc.verb(slide)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], прежде чем согнуть их и начать ритмично поглаживать и массировать [npc2.her] простату.",
							"Медленно проталкивая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] нежный [npc.moan], когда [npc.she] затем [npc.verb(curl)] их и [npc.verb(start)] нежно массировать [npc2.her] простату."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо вбивая ладонь [npc.her] [npc.hand] в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(force)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.asshole], прежде чем согнуть их и начать интенсивно поглаживать и массировать [npc2.her] простату.",
							"С помощью [npc.a_moan+], [npc.name] яростно [npc.verb(slam)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], прежде чем согнуть их и начать агрессивно поглаживать и массировать [npc2.her] простату.",
							"Сильно вдавливая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she], затем [npc.verb(curl)] их вверх и [npc.verb(start)], чтобы примерно массировать [npc2.her] простату."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вдавливая ладонь [npc.her] [npc.hand] в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.asshole], прежде чем поджать их и начать поглаживать и массировать [npc2.her] простату.",
							"С помощью [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], прежде чем поджать их и начать поглаживать и массировать [npc2.her] простату.",
							"Вдавливая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she], затем [npc.verb(curl)] их вверх и [npc.verb(start)], чтобы массировать [npc2.her] простату."));
					break;
				default: // Normal dom and eager sub:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью вдавливая ладонь [npc.her] [npc.hand] в [npc2.namePos] [npc2.assCloaca+], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.asshole], прежде чем поджать их и начать быстро поглаживать и массировать [npc2.her] простату.",
							"С [npc.a_moan+], [npc.name] отчаянно [npc.verb(push)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], прежде чем подогнуть их и начать жадно ласкать и массировать [npc2.her] простату.",
							"Счастливо проталкивая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(let)] [npc.a_moan+], когда [npc.she] затем [npc.verb(curl)] их и [npc.verb(start)] энергично массировать [npc2.her] простату."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем мягко прижать [npc2.her] [npc2.assCloaca+] к [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем мягко подать [npc2.her] бедра навстречу [npc.namePos].",
								" Отвечая на этот жест стоном [npc2.a_moan+], [npc2.name] мягко [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] наружу, прижимая их к [npc.namePos] [npc.hand]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем грубо вдавить [npc2.her] [npc2.assCloaca+] в [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой впечатать [npc2.her] [npc2.assCloaca+] в [npc.namePos].",
								" В ответ на этот маневр, издавая [npc2.a_moan+], [npc2.name] агрессивно [npc2.verb(slam)] [npc2.her] [npc2.assCloaca+] против [npc.namePos] [npc.hand]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем прижать [npc2.her] [npc2.assCloaca+] к [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], после чего она дергает бедрами [npc2.her] навстречу [npc.namePos] прикосновению.",
								" В ответ на этот маневр, издавая [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] против [npc.namePos] [npc.hand]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это нежелательное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем отчаянно попытаться оттянуть [npc2.her] [npc2.assCloaca+] от [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], после чего она отчаянно пытается убрать бедра [npc2.her] от [npc.namePos] прикосновения.",
								" В ответ на этот маневр, издавая [npc2.a_moan+], [npc2.name] отчаянно [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.assCloaca+] от [npc.namePos] [npc.name]."));
						break;
					default: // Normal dom and eager sub:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем прижать [npc2.her] [npc2.assCloaca+] к [npc.namePos] [npc.hand].",
								" Тут же, [npc2.name] [npc2.verb(let)] [npc2.a_moan+] в ответ, отчаянно выгибая [npc2.her] бедра навстречу [npc.namePos].",
								" Отвечая на этот жест стоном [npc2.a_moan+], [npc2.name] отчаянно [npc2.verb(push)] [npc2.her] [npc2.assCloaca+] против [npc.namePos] [npc.hand]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Пальцы в [npc2.her] анус";
		}

		@Override
		public String getActionDescription() {
			return "Погрузить [npc.namePos] [npc.fingers] в [npc2.namePos] [npc2.asshole+] и начать играть пальцами в [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.her] [npc.fingers+] над [npc2.namePos] [npc2.assCloaca], [npc.name] слегка [npc.verb(let)] наружу [npc.moan], прежде чем медленно погрузить [npc.her] пальцев в [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] между ягодицами [npc2.namePos], и медленным, уверенным движением [npc.she] нежно [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С предвкушением дразня [npc.her] [npc.fingers+] над [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], прежде чем жадно погрузить [npc.her] пальцев в [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] с предвкушением [npc.verb(press)] [npc.her] [npc.fingers+] между ягодицами [npc2.namePos], и решительным толчком [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем с силой вогнать [npc.her] пальцев глубоко в [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] грубо растирает [npc.her] [npc.fingers+] между ягодицами [npc2.namePos], и резким толчком [npc.she] жадно вбивает [npc.her] пальцев глубоко в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С предвкушением дразня [npc.her] [npc.fingers+] над [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], прежде чем жадно погрузить [npc.her] пальцев в [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] с предвкушением [npc.verb(press)] [npc.her] [npc.fingers+] между ягодицами [npc2.namePos], и решительным толчком [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразняще проводя [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.assCloaca], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем погрузить [npc.her] пальцев в [npc2.her] [npc2.asshole+].",

							
							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] между ягодицами [npc2.namePos], и легким толчком [npc.she] [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.asshole+]."));
					break;
				default:
					break;
			}
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], когда [npc.namePos] [npc.fingers] входят в [npc2.herHim], а затем мягко прижимая [npc2.her] [npc2.assCloaca] к [npc.namePos] [npc.hand], пока [npc2.name] [npc2.verb(help)] к [npc2.verb(sink)] [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+].",
	
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] плавно прижимая [npc2.her] [npc2.assCloaca] к [npc.namePos] [npc.hand], побуждая [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim], before eagerly bucking [npc2.her] [npc2.assCloaca] back against [npc.her] [npc.hand] as [npc2.she] enthusiastically [npc2.verb(help)] to [npc2.verb(sink)] [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+].",
	
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно выгибает [npc2.her] [npc2.assCloaca] назад к [npc.namePos] [npc.hand], отчаянно подталкивая [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.namePos] [npc.fingers] входит [npc2.herHim], прежде чем яростно выгнуть [npc2.her] [npc2.assCloaca] назад к [npc.her] [npc.hand] и грубо втиснуть [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+].",
	
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно выгибает [npc2.her] [npc2.assCloaca] назад к [npc.namePos] [npc.hand], грубо заставляя [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.namePos] [npc.fingers] enter [npc2.herHim], eagerly bucking [npc2.her] [npc2.assCloaca] back against [npc.her] [npc.hand] as [npc2.she] enthusiastically [npc2.verb(help)] to [npc2.verb(sink)] [npc.her] [npc.fingers] even deeper into [npc2.her] [npc2.asshole+].",
	
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно выгибая [npc2.her] [npc2.assCloaca] назад на [npc.namePos] [npc.hand], отчаянно подталкивая [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], когда [npc.namePos] [npc.fingers] входит в [npc2.herHim], после чего она выгибается [npc2.her] [npc2.assCloaca] назад на [npc.her] [npc.hand], помогая погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+].",
	
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] выгибая [npc2.her] [npc2.assCloaca] назад на [npc.namePos] [npc.hand], подталкивая [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], когда [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.fingers] входит в [npc2.herHim], после чего она тщетно пытается оттянуть [npc2.her] [npc2.assCloaca] от нежелательного проникновения, содрогаясь и [npc2.sobbing] все это время.",
	
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно пытаясь [npc2.verb(pull)] от нежелательного проникновения, [npc2.sobbing] борется с [npc.name], пока [npc.her] нежелательное [npc.fingers] [npc2.verb(push)] глубоко в [npc2.her] [npc2.asshole+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] назад, прижимаясь к [npc.namePos] [npc.hand], издавая восторженный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(start)] умоляет [npc.name] продолжать ласкать пальцами [npc2.her] [npc2.asshole+].",
		
							" Восторженный [npc2.moan] вырывается из меж [npc2.namePos] [npc2.lips+], и, жадно подаваясь [npc2.her] [npc2.assCloaca] назад навстречу прикосновению [npc.her], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) пальцами исследовать [npc2.her] [npc2.asshole+].",
		
							" В восторге от [npc2.Moaning], [npc2.name] с нетерпением [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] против [npc.namePos] [npc.hand], жадно умоляя, чтобы [npc.herHim] продолжал(а), пока движения [npc2.her] заставляют [npc.her] [npc.fingers] проникать всё глубже в [npc2.her] [npc2.ass+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах отпрянуть [npc2.her] [npc2.ass+] от нежелательного прикосновения [npc.namePos], [npc2.name] [npc2.verb(let)] из-за [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться от навязчивого [npc.namePos] [npc.fingers].",
		
							" [npc2.A_sob+] внезапно вырывается из-за [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.she] [npc.verb(continue)], проникая пальцами в [npc2.namePos] [npc2.asshole+].",
		
							" В смятении [npc2.Sobbing], [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от прикосновений [npc.namePos], борясь с [npc.herHim], пока [npc.her] [npc.fingers+] [npc.verb(continue)] глубоко скользит в [npc2.namePos] [npc2.ass+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] медленно [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] обратно к [npc.namePos] [npc.hand], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(start)] мягко умоляет [npc.name] продолжать ласкать [npc2.her] [npc2.asshole+].",
	
						" Тихий [npc2.moan] доносится из-за [npc2.namePos] [npc2.lips+], и, мягко толкая [npc2.her] [npc2.hips] на [npc.her] [npc.hand], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал ласкать [npc2.her] [npc2.asshole+].",
	
						" В восторге [npc2.Moaning], [npc2.name] медленно [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] к [npc.namePos] [npc.hand], тихо [npc2.moaning], чтобы [npc.herHim] продолжал, пока движения [npc2.her] заставляют [npc.her] [npc.fingers] скользить всё глубже в [npc2.her] [npc2.ass+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] назад на [npc.namePos] [npc.hand], издавая [npc2.a_moan+], пока [npc2.name] грубо [npc2.verb(order)] [npc.name], чтобы продолжить пальцевать [npc2.her] [npc2.asshole+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо втирая [npc2.her] [npc2.hips] в [npc.her] [npc.hand], [npc2.name] властно [npc.verb(command)] [npc.name], чтобы продолжить пальцевать [npc2.her] [npc2.asshole+].",
	
							" [npc2.Name] грубо [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] против [npc.namePos] [npc.hand], приказывая [npc.herHim] продолжать, пока от [npc2.her] резких движений [npc.her] [npc.fingers] все глубже погружается в [npc2.her] [npc2.ass+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.assCloaca] назад на [npc.namePos] [npc.hand], издавая [npc2.a_moan+], пока [npc2.name] [npc2.verb(beg)] ради того, чтобы [npc.name] продолжал пальцевать [npc2.her] [npc2.asshole+].",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, выталкивая [npc2.her] [npc2.hips] навстречу прикосновению [npc.her], [npc2.name] [npc2.verb(beg)], чтобы [npc.name] продолжал пальцевать [npc2.her] [npc2.asshole+].",
		
							" [npc2.Moaning+] и [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.assCloaca] против [npc.namePos] [npc.hand], умоляя [npc.herHim] продолжить, пока [npc2.her] движения вгоняют [npc.her] [npc.fingers] всё глубже в [npc2.her] [npc2.ass+]."));
					break;
			}
		}
		return "";
	}
	
	
	public static final SexAction ANAL_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежное проникновение пальцами в анус";
		}

		@Override
		public String getActionDescription() {
			return "Нежно ласкать пальцами [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно погружая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] пальцев вверх, мягко поглаживая внутренние стенки [npc2.her], пока [npc.she] медленно [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Медленно втискивая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] осторожно [npc.verb(pump)] их туда-сюда, издавая [npc.a_moan+], пока [npc.she] мягко [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Прижимая [npc.her] [npc.hand] к [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] короткий [npc.moan], пока [npc.she] нежно [npc.verb(slide)] [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Анальное проникновение пальцами";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить ласкать пальцами [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] пальцев вверх, с энтузиазмом поглаживая внутренние стенки [npc2.her], пока [npc.she] жадно [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Крепко втискивая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] стремительно [npc.verb(pump)] их туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"С жадностью прижимая [npc.her] [npc.hand] к [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем с энтузиазмом задвинуть [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый анальный фингерстинг";
		}

		@Override
		public String getActionDescription() {
			return "Грубо пальцевать [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Алчно погружая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] пальцев внутрь, грубо лаская [npc2.her] внутренние стенки, пока [npc.she] с силой [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Грубо втискивая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] неистово [npc.verb(pump)] их туда-сюда, издавая [npc.a_moan+], пока [npc.she] мощно [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"С силой прижимая [npc.her] [npc.hand] к [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем грубо вогнать [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANAL_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Анальное проникновение пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить ласкать пальцами [npc2.namePos] [npc2.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] пальцев внутрь, лаская [npc2.her] внутренние стенки, пока [npc.she] [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Втискивая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(pump)] их туда-сюда, издавая [npc.a_moan+], пока [npc.she] [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Прижимая [npc.her] [npc.hand] к [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем задвинуть [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction ANAL_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадная анальная стимуляция пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Жадно перебирать пальцами [npc2.namePos] [npc2.asshole+].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(curl)] [npc.her] пальцев вверх, с энтузиазмом поглаживая внутренние стенки [npc2.her], пока [npc.she] жадно [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"Крепко втискивая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+], [npc.name] стремительно [npc.verb(pump)] их туда-сюда, издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(finger)] [npc2.her] [npc2.ass].",

					"С жадностью прижимая [npc.her] [npc.hand] к [npc2.namePos] [npc2.ass], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем с энтузиазмом задвинуть [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.asshole+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться анальной стимуляции";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.fingers] из [npc2.namePos] [npc2.asshole+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(feel)], в [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.asshole+].",

					"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] неистово [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.asshole+].",

					"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.asshole+], [npc.name] [npc.sob] в смятении, пока [npc.she] [npc.verb(fail)], чтобы вытащить их из [npc2.her] [npc2.assCloaca+]."));
			
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить анальную стимуляцию";
		}
		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.fingers] из [npc2.namePos] [npc2.asshole+] и прекрати ласкать [npc2.herHim] пальцами.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдернув [npc.her] [npc.fingers] из [npc2.namePos] [npc2.asshole+], [npc.name] быстро уносит [npc.her] [npc.hand] от [npc2.namePos] [npc2.assCloaca].",

							"В последний раз глубоко вонзившись в [npc2.name], [npc.name] затем резко выдергивает [npc.her] [npc.fingers] из [npc2.namePos] [npc2.asshole+], завершая это грубое проникновение пальцами [npc.her]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув, [npc.her] [npc.fingers] покидает [npc2.namePos] [npc2.asshole+], и [npc.name] быстро уносит [npc.her] [npc.hand] от [npc2.namePos] [npc2.assCloaca].",

							"В последний раз глубоко толкнувшись в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.fingers] обратно из [npc2.namePos] [npc2.asshole+], кладя конец [npc.her] ласкам пальцами."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] ахает, когда [npc.name] выходит из [npc2.namePos] [npc2.asshole], а затем издает [npc2.a_sob+], пока [npc2.name] [npc2.verb(continue)] сопротивляться [npc.herHim].",


							" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] сопротивляться [npc.name], пока [npc.she] выходит из [npc2.namePos] [npc2.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.asshole+].",


							" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.namePos] желание получить еще больше внимания от [npc.namePos]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction ANAL_FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Получить анальный фингерстинг";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать ласкать твой [npc.assCloaca+] пальцами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко, но уверенно сжав [npc2.namePos] [npc2.hand], [npc.name] медленно [npc.verb(guide)] [npc2.her] [npc2.fingers] по [npc.her] [npc.assCloaca], издавая легкий [npc.moan], прежде чем втиснуть [npc2.her] пальцев в [npc.her] [npc.asshole+].",
							
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] между [npc.her] ягодицами, и с медленным, ровным нажимом, [npc.she] нежно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] по [npc.her] [npc.assCloaca], издавая [npc.a_moan+], прежде чем жадно втиснуть [npc2.her] пальцев в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] между [npc.her] ягодицами, и с решительным напором, [npc.she] жадно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мертвой хваткой вцепившись в [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] по [npc.her] [npc.assCloaca], издавая [npc.a_moan+], прежде чем грубо впихнуть [npc2.her] пальцев в [npc.her] [npc.asshole+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с силой [npc.verb(push)] [npc2.namePos] [npc2.fingers] между [npc.her] ягодиц, и доминирующим, резким движением, [npc.she] грубо [npc.verb(stuff)] [npc2.her] пальцев в [npc.her] [npc.asshole+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] по [npc.her] [npc.assCloaca], издавая [npc.a_moan+], прежде чем жадно втиснуть [npc2.her] пальцев в [npc.her] [npc.asshole+].",

							"Ухватив [npc2.namePos] [npc2.hand], [npc.name] с жадностью [npc.verb(guide)] [npc2.namePos] [npc2.fingers] между [npc.her] ягодиц, и с решительным напором, [npc.she] алчно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.asshole+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] поверх [npc.her] [npc.assCloaca], издавая [npc.a_moan+], прежде чем впихнуть [npc2.her] пальцев в [npc.her] [npc.asshole+].",

							"Ухватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] между [npc.her] ягодиц, и с решительным напором, [npc.she] [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], поджимая [npc2.her] [npc2.fingers], прежде чем мягко начать пальцевать [npc.her] [npc.asshole+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], осторожно вдавливая [npc2.her] [npc2.hand] в [npc.her] [npc.assCloaca], пока [npc2.she] [npc2.verb(set)] о пальцевании [npc.her] [npc.asshole+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], подгибая [npc2.her] [npc2.fingers], прежде чем с нетерпением приняться пальцевать [npc.her] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно вдавливая [npc2.her] [npc2.hand] в [npc.her] [npc.assCloaca], пока [npc2.she] [npc2.verb(set)] о пальцевании [npc.her] [npc.asshole+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim] и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо [npc2.verb(curl)] [npc2.her] [npc2.fingers], прежде чем начать безжалостно пальцем трахать [npc.her] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], стремясь напомнить [npc.name], кто здесь главный, пока [npc2.she] [npc2.verb(start)] грубо пальцевать [npc.her] [npc.asshole+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], подгибая [npc2.her] [npc2.fingers], прежде чем с нетерпением приняться пальцевать [npc.her] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно вдавливая [npc2.her] [npc2.hand] в [npc.her] [npc.assCloaca], пока [npc2.she] [npc2.verb(set)] о пальцевании [npc.her] [npc.asshole+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], поджимая [npc2.her] [npc2.fingers] перед тем как начать пальцевать [npc.her] [npc.asshole+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], втискивая [npc2.her] [npc2.hand] в [npc.her] [npc.assCloaca], пока [npc2.she] [npc2.verb(set)] о пальцевании [npc.her] [npc.asshole+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.fingers] внутри [npc.herHim], пытаясь вырваться из [npc.namePos] крепкой хватки на [npc2.her] [npc2.hand], пока [npc2.she] [npc2.verb(try)] чтобы вытащить [npc2.herself].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] тщетно сопротивляется [npc.namePos] тугой хватке на [npc2.her] [npc2.hand], умоляя [npc.name] остановиться, пока [npc.she] [npc2.verb(force)] [npc2.her] [npc2.fingers] глубоко в [npc.her] [npc.asshole+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно пальцевать анус";
		}

		@Override
		public String getActionDescription() {
			return "Нежно наслаждайся [npc2.namePos] [npc2.fingers+] в своих [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко прижимая [npc.her] [npc.assCloaca] назад к [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(help)] чтобы погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.asshole+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] вращает [npc.her] [npc.assCloaca] назад, прижимая [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Медленно втискивая [npc.her] [npc.assCloaca] назад против [npc2.namePos] [npc2.hand], мягкий [npc.moan] вытекает из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко входить в [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Наслаждаться пальцами в анусе";
		}

		@Override
		public String getActionDescription() {
			return "Наслаждаться [npc2.namePos] [npc2.fingers+] в твоей [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью прижимая [npc.her] [npc.assCloaca] назад к [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] вращает [npc.her] [npc.assCloaca] назад, прижимая [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"С энтузиазмом втискивая [npc.her] [npc.assCloaca] назад против [npc2.namePos] [npc2.hand], [npc.a_moan+] вытекает из между [npc.her] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко входить в [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубые ласки пальцами в анусе";
		}

		@Override
		public String getActionDescription() {
			return "Грубо втиснуть [npc2.namePos] [npc2.fingers+] глубоко внутрь твоей [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вжимая [npc.her] [npc.assCloaca] назад в [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(force)] [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] агрессивно [npc.verb(start)] вращая [npc.her] [npc.assCloaca] назад в [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"Грубо толкая [npc.her] [npc.assCloaca] назад в [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.her] [npc.lips+], пока мощные движения [npc.her] вгоняют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Наслаждаться анальным фингеррингом";
		}

		@Override
		public String getActionDescription() {
			return "Наслаждаться [npc2.namePos] [npc2.fingers+] в [npc.her] [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Прижимая [npc.her] [npc.assCloaca] назад к [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] вращая [npc.her] [npc.assCloaca] назад в [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"[npc.her] [npc.assCloaca] толчками вжимается в [npc2.namePos] [npc2.hand], и [npc.a_moan+] выскальзывает из между [npc.her] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] проникать глубоко в [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANALLY_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью исследуемый пальцами анус";
		}

		@Override
		public String getActionDescription() {
			return "С энтузиазмом прижимай свой [npc.assCloaca+] к [npc2.namePos] [npc2.hand], пока [npc2.she] ласкает пальцами твою [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью прижимая [npc.her] [npc.assCloaca] назад к [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.asshole+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] вращает [npc.her] [npc.assCloaca] назад, прижимая [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.asshole+].",

					"С энтузиазмом втискивая [npc.her] [npc.assCloaca] назад против [npc2.namePos] [npc2.hand], [npc.a_moan+] вытекает из между [npc.her] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко входить в [npc.her] [npc.asshole+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться анальной стимуляции";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить [npc2.namePos] [npc2.fingers] из твоей [npc.asshole+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают подступать к [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)], чтобы нежно вытягивать [npc2.namePos], мерно качая [npc2.fingers] из [npc.her] [npc.asshole+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь увести [npc.her] [npc.assCloaca] от нежелательных прикосновений [npc2.namePos], отчаянно извиваясь, пока [npc2.namePos] [npc2.fingers+] продолжают мягко скользить туда-сюда в [npc.her] [npc.asshole+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.assCloaca] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] пребывает в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают мягко скользить глубоко в [npc.her] [npc.asshole+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.namePos] [npc.eyes], и, больше не в силах их сдерживать, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь жадно вытащить [npc2.namePos] жадно вонзающиеся [npc2.fingers] из [npc.her] [npc.asshole+].",


							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь убрать [npc.her] [npc.assCloaca] от нежеланного прикосновения [npc2.namePos], отчаянно извиваясь, пока [npc2.namePos] [npc2.fingers+] продолжают жадно входить и выходить из [npc.her] [npc.asshole+].",


							"Отчаянно пытаясь оттянуть [npc.her] [npc.assCloaca] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают жадно вбиваться глубоко в [npc.her] [npc.asshole+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.namePos] [npc.eyes], и, больше не в силах их сдерживать, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь грубо вытащить [npc2.namePos] грубо вонзающиеся [npc2.fingers] из [npc.her] [npc.asshole+].",


							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь убрать [npc.her] [npc.assCloaca] от нежеланного прикосновения [npc2.namePos], отчаянно извиваясь, пока [npc2.namePos] [npc2.fingers+] продолжают грубо вбиваться в [npc.her] [npc.asshole+].",


							"Отчаянно пытаясь оттянуть [npc.her] [npc.assCloaca] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают грубо вбиваться глубоко в [npc.her] [npc.asshole+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction ANAL_FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить анальный фингерринг";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.fingers] из твоей [npc.asshole+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вырывая [npc2.namePos] [npc2.fingers] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(growl)] на [npc2.name], пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы прекратить фингерринг [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(yank)] [npc2.her] [npc2.fingers] из [npc.her] [npc.asshole+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc2.namePos] [npc2.fingers] из [npc.her] [npc.asshole+], [npc.name] [npc.verb(let)] из [npc.a_moan+], прежде чем сказать [npc2.herHim] прекратить фингерринг [npc.herHim].",

							"[npc.Name] прижимаются к [npc2.name], вдыхая [npc2.her] [npc2.scent], пока [npc.name] [npc.verb(slide)] [npc2.her] [npc2.fingers] из [npc.her] [npc.asshole+]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] вздох облегчения, который вскоре сменяется [npc2.a_sob+], когда [npc2.she] [npc2.verb(realise)], что [npc.nameIsFull] еще не закончил с [npc2.herHim].",

							" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и сопротивляясь [npc.name], пока [npc.she] [npc.verb(hold)] [npc2.herHim] на месте."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim] от стимуляции [npc.her] [npc.asshole+].",

							" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.her] еще больше [npc.asshole+] [npc2.her]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
