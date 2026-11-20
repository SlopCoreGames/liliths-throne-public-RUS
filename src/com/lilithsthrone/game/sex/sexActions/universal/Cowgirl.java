package com.lilithsthrone.game.sex.sexActions.universal;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.main.Main;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class Cowgirl {
	
	//TODO These need proper formatting before inclusion into sex actions
	
	public static String getTongueMouthDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(sink)] на [npc2.namePos] [npc2.penis+], издавая [npc.a_moan+] [npc.she], пока [npc.verb(lean)] вниз и [npc.verb(grab)] [npc2.namePos] голову обеими руками, прежде чем притянуть [npc2.name] для страстного поцелуя.",
					"С [npc.a_moan+], [npc.name] [npc.verb(slide)] на [npc2.namePos] [npc2.penis+], наклоняясь к [npc2.namePos] [npc2.breasts] и вдыхая [npc2.namePos] [npc2.scent], прежде чем прижать [npc.her] [npc.lips+] к [npc2.namePos].",
					"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] скользят еще глубже в [npc.her] [npc.asshole+], пока [npc.she] [npc.verb(lean)] вниз и притягивает [npc2.name] для отчаянного поцелуя.",
					"Наклонившись, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] зарывается [npc.herself] в [npc2.namePos] [npc2.penis+], прежде чем податься вперед и прижать [npc.her] [npc.lips+] к [npc2.namePos]."));
			
		} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(sink)] на [npc2.namePos] [npc2.penis+], издавая радостный визг, когда [npc.she] [npc.verb(feel)] [npc2.name] глубоко погружаются в [npc.her] [npc.pussy+]. Достигнув [npc2.namePos] [npc2.cock], [npc.she] [npc.verb(lean)] вниз и [npc.verb(grab)] [npc2.namePos] голову обеими руками, прежде чем притянуть [npc2.name] для страстного поцелуя.",
					"С [npc.a_moan+], [npc.name] [npc.verb(slide)] на [npc2.namePos] [npc2.penis+], наклоняясь к [npc2.namePos] [npc2.breasts] и вдыхая [npc2.namePos] [npc2.scent], прежде чем прижать [npc.her] [npc.lips+] к [npc2.namePos].",
					"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] скользит еще глубже в [npc.her] [npc.pussy+], пока [npc.she] [npc.verb(lean)] вниз и притягивает [npc2.name] для отчаянного поцелуя.",
					"Наклонившись, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] зарывается [npc.herself] в [npc2.namePos] [npc2.penis+], прежде чем податься вперед и прижать [npc.her] [npc.lips+] к [npc2.namePos]."));
			
		} else {
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(lean)] вниз, обхватив [npc2.namePos] голову обеими руками и притягивая [npc2.name] для страстного поцелуя.",
					"С ухмылкой, [npc.name] [npc.verb(lean)] вниз в [npc2.namePos] [npc2.breasts], вдыхая [npc2.namePos] [npc2.scent], прежде чем прижать [npc.her] [npc.lips+] к [npc2.namePos].",
					"Наклоняясь, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] прижимает [npc.her] [npc.lips+] к [npc2.namePos] и начинает жадно целовать [npc2.name]."));
		}

		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] счастливо проталкивает [npc2.namePos] [npc2.tongue] глубоко в рот [npc.her], с восторгом прижимая [npc2.namePos] [npc2.lips+] обратно к [npc.hers] и [npc2.moaning], пока [npc2.name] жадно отвечает на этот проявление привязанности [npc.her].",
						" С жадным [npc2.moan], [npc2.name] отчаянно вжимается [npc.herHim] обратно, заглушая [npc2.namePos] [npc2.moans] в [npc.her] рот, пока [npc2.name] жадно вгоняет [npc2.namePos] [npc2.tongue] за пределы [npc.her] [npc.lips+].",
						" [npc2.Moaning] в восторге, [npc2.name] отчаянно вжимается [npc2.namePos] задом в [npc.herHim], жадно прижимая [npc2.namePos] [npc2.lips+] к [npc.hers], пока [npc2.name] счастливо заталкивает [npc2.namePos] [npc2.tongue] в [npc.her] рот."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] толкает [npc2.namePos] [npc2.tongue] в рот [npc.her], прижимая [npc2.namePos] [npc2.lips+] к [npc.hers], пока [npc2.name] жадно отвечает на проявление [npc.her].",
						" С [npc2.a_moan], [npc2.name] откидывается на [npc.herHim], заглушая [npc2.namePos] [npc2.moans] во рту [npc.her], пока [npc2.name] жадно вгоняет [npc2.namePos] [npc2.tongue] сквозь [npc.her] [npc.lips+].",
						" В восторге от [npc2.Moaning], [npc2.name] откидывается на [npc.herHim], прижимая [npc2.namePos] [npc2.lips+] к [npc.hers], пока [npc2.name] счастливо вводит [npc2.namePos] [npc2.tongue] в рот [npc.her]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] пытается отстраниться, [npc2.sobbing] и корчится от дискомфорта, пока [npc.she] навязывает [npc.herself] на [npc2.name].",
						" [npc2.NamePos] [npc2.sob+] приглушенно звучит во рту [npc.her], пока [npc2.name] пытается отстраниться, корчась от дискомфорта, когда [npc.name] навязывает [npc.herself] на [npc2.name].",
						" С [npc2.a_sob+], [npc2.name] тщетно пытаются отстраниться от [npc.herHim], протестуя и извиваясь от дискомфорта, пока [npc.she] силой [npc.tongue] проталкивает [npc.her] сквозь [npc2.namePos] неохотное [npc2.lips]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getStartingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] наклоняется, чтобы обхватить [npc2.namePos] [npc2.cock+], а затем устраивается поудобнее. Немного поиграв с [npc2.cockHead+] между внешними половыми губами [npc.her], [npc.she] [npc.verb(let)] немного [npc.moan], пока [npc.she] медленно опускается и проникает в [npc.her] [npc.pussy+] на [npc2.namePos] [npc2.cock+].",
						"Устроившись поудобнее, [npc.name] совмещает [npc2.cockHead+] [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+], и плавным, размеренным движением [npc.she] мягко опускается, проникая в [npc.her] [npc.pussy+] в [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] наклоняется, чтобы крепко сжать [npc2.namePos] [npc2.cock+], а затем устраивается поудобнее. Немного поиграв с [npc2.cockHead+] между внешними половыми губами [npc.her], [npc.she] [npc.verb(let)] [npc.a_moan+], пока [npc.she] с нетерпением опускается и проникает в [npc.her] [npc.pussy+] на [npc2.namePos] [npc2.cock+].",
						"Устроившись поудобнее, [npc.name] совмещает [npc2.cockHead+] [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+], и одним резким движением [npc.she] с нетерпением опускается, проникая в [npc.her] [npc.pussy+] в [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] тянется вниз и грубо [npc.verb(grab)] [npc2.namePos] [npc2.cock+], а затем ерзает, чтобы устроиться поудобнее. Немного потеребив [npc2.cockHead+] между внешними [npc.her] губами, [npc.she] [npc.verb(let)] [npc.a_moan+], пока [npc.she] с силой опускается и проникает в [npc.her] [npc.pussy+] на [npc2.namePos] [npc2.cock+].",
						"Устроившись поудобнее, [npc.name] выравнивает [npc2.cockHead+] [npc2.namePos] [npc2.cock+] между [npc.her] [npc.legs+], и одним резким движением [npc.she] грубо опускается, проникая в [npc.her] [npc.pussy+] в [npc2.namePos] [npc2.cock+]."));
				break;
			default:
				break;
		}
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_moan+], когда [npc2.name] входит в [npc.herHim], жадно выгибая [npc2.namePos] [npc2.hips], пока [npc2.name] начинает трахать [npc.her] [npc.pussy+].",
						" С [npc2.a_moan+], [npc2.name] жадно вжимает [npc2.namePos] [npc2.hips] в пах [npc.her], погружая [npc2.namePos] [npc2.cock+] в [npc.her] [npc.pussy+], пока [npc2.name] начинает энергично трахать [npc.herHim]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_moan+], когда [npc2.name] входит в [npc.herHim], выгибая [npc2.namePos] [npc2.hips], пока [npc2.name] начинает трахать [npc.her] [npc.pussy+].",
						" С [npc2.a_moan+], [npc2.name] вжимает [npc2.namePos] [npc2.hips] в пах [npc.her], погружая [npc2.namePos] [npc2.cock+] в [npc.her] [npc.pussy+], пока [npc2.name] начинает трахать [npc.herHim]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_sob+], пока [npc.she] с силой [npc2.namePos] [npc2.cock] внутрь [npc.herHim], и, тщетно пытаясь сопротивляться [npc.herHim], [npc2.name] отчаянно пытается оттолкнуть [npc.herHim] от [npc2.name].",
						" С этим [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] с силой вгоняет [npc2.namePos] [npc2.cock] глубоко в [npc.her] [npc.pussy+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getRidingCockGentle() {
		return UtilText.returnStringAtRandom(
				"Используя [npc.her] ноги, [npc.name] медленно [npc.verb(lift)] [npc.herself] вверх и вниз, скользя [npc2.namePos] [npc2.penis+] туда-сюда в [npc.her] [npc.pussy+], пока [npc.she] громко [npc.moansVerb+].",
				"Наклонившись вперед, [npc.name] использует [npc.her] [npc.hands], чтобы поддержать часть веса [npc.her], пока [npc.she] медленно [npc.verb(slide)] вверх и вниз на [npc2.namePos] [npc2.penis+]. Когда [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] к [npc2.name], [npc.she] ощущает аромат [npc2.namePos] [npc2.scent], и [npc.she] кусает [npc.her] [npc.lip], пока [npc.she] вдыхает этот пьянящий запах.",
				"Опуская [npc.her] [npc.hands] на [npc.her] колени, [npc.name] использует [npc.her] [npc.legs], чтобы медленно двигать [npc.herself] вверх и вниз, издавая тихие [npc.moans], пока [npc.she] вонзает [npc.herself] в [npc2.namePos] [npc2.cock+].",
				"[npc.Name] медленно [npc.verb(lift)] [npc.herself] вверх, прежде чем скользнуть обратно вниз, издавая тихий [npc.moan], когда [npc.she] пронзает [npc.her] [npc.pussy+] на [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockNormal() {
		return UtilText.returnStringAtRandom(
				"Используя [npc.her] ноги, [npc.name] [npc.verb(lift)] [npc.herself] вверх и вниз, скользя [npc2.namePos] [npc2.penis+] внутрь и наружу из [npc.her] [npc.pussy+], пока [npc.she] [npc.moansVerb+] вслух.",
				"Наклонившись вперед, [npc.name] использует [npc.her] [npc.hands], чтобы удерживать часть [npc.her] веса, пока [npc.she] с нетерпением [npc.verb(slide)] вверх и вниз на [npc2.namePos] [npc2.penis+]. Когда [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] к [npc2.name], [npc.she] ощущает аромат [npc2.namePos] [npc2.scent], и [npc.she] кусает [npc.her] [npc.lip], пока [npc.she] вдыхает этот пьянящий запах.",
				"Опуская [npc.her] [npc.hands] на [npc.her] коленях, [npc.name] использует [npc.her] [npc.legs], чтобы с энтузиазмом двигать [npc.herself] вверх и вниз, издавая [npc.moans+], пока [npc.she] вонзается [npc.herself] в [npc2.namePos] [npc2.cock+].",
				"[npc.Name] [npc.verb(lift)] [npc.herself] вверх, прежде чем жадно скользнуть вниз, издавая [npc.a_moan+], когда [npc.she] пронзает [npc.her] [npc.pussy+] на [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockRough() {
		return UtilText.returnStringAtRandom(
				"Используя [npc.her] ноги, [npc.name] стремительно подпрыгивает [npc.herself] вверх и вниз, вколачивая [npc2.namePos] [npc2.penis+] внутрь и наружу из [npc.her] [npc.pussy+], пока [npc.she] [npc.moansVerb+] вслух.",
				"Наклонившись вперед, [npc.name] опирается на [npc.her] [npc.hands], чтобы поддержать часть веса [npc.her], пока [npc.she] начинает стремительно подпрыгивать вверх-вниз на [npc2.namePos] [npc2.penis+]. Когда [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] к [npc2.name], [npc.she] ощущает аромат [npc2.namePos] [npc2.scent], и [npc.she] прикусывает [npc.her] [npc.lip], пока [npc.she] вдыхает этот пьянящий запах.",
				"Положив [npc.her] [npc.hands] на пол позади [npc.herHim] для опоры, [npc.name] начинает стремительно подпрыгивать на [npc.herself] вверх-вниз, издавая дрожащие, [npc.moans+] звуки, пока [npc.she] вонзает [npc.herself] в [npc2.namePos] [npc2.cock+].",
				"Усмехаясь, глядя на [npc2.name] сверху вниз, [npc.name] начинает с энтузиазмом подпрыгивать вверх и вниз, издавая [npc.a_moan+], пока [npc.she] пронзает [npc.her] [npc.pussy+] на [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getStoppingVaginalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Потянувшись вниз, [npc.name] грубо [npc.verb(grab)] основание [npc2.namePos] [npc2.cock], прежде чем поднять [npc.herself] и позволить [npc2.namePos] [npc2.cock+] выскользнуть из [npc.her] [npc.pussy+].");
				break;
			default:
				UtilText.nodeContentSB.append("Потянувшись вниз, [npc.name] берется за основание [npc2.namePos] [npc2.cock], прежде чем поднять [npc.herself] и позволить [npc2.namePos] [npc2.cock+] выскользнуть из [npc.her] [npc.pussy+].");
				break;
		}
		
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает облегченный вздох, который вскоре сменяется на [npc2.a_sob+], когда [npc2.name] понимает, что [npc.she] еще не закончил с [npc2.name].",
						" [npc2.a_sob+] и [npc2.name] продолжают протестовать и вырываться против [npc.herHim], пока [npc.she] крепко удерживает [npc2.name] на месте."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_moan+], когда [npc.she] перестает скакать на [npc2.name].",
						" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], предавая желание [npc2.namePos] продолжать трахать [npc.her] [npc.pussy+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getStartingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_GENTLE:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] тянется вниз, чтобы обхватить [npc2.namePos] [npc2.cock+], а затем ерзает, чтобы устроиться поудобнее. Немного поразвлекавшись с [npc2.cockHead+] над [npc.her] [npc.asshole+], [npc.she] [npc.verb(let)] немного [npc.moan], пока [npc.she] медленно опускается и проникает в [npc.her] [npc.ass+] на [npc2.namePos] [npc2.cock+].",
						"Устроившись поудобнее, [npc.name] выравнивает [npc2.cockHead+] [npc2.namePos] [npc2.cock+] между ягодиц [npc.her], и медленным, уверенным движением [npc.she] плавно опускается, проникая в [npc.her] [npc.asshole+] на [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] тянется вниз, чтобы крепко обхватить [npc2.namePos] [npc2.cock+], а затем ерзает, чтобы устроиться поудобнее. Немного поразвлекавшись с [npc2.cockHead+] над [npc.her] [npc.asshole+], [npc.she] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно опускается и проникает в [npc.her] [npc.ass+] на [npc2.namePos] [npc2.cock+].",
						"Подвинувшись поудобнее, [npc.name] выравнивает [npc2.cockHead+] [npc2.namePos] [npc2.cock+] между [npc.her] ягодицами и одним резким движением [npc.she] с жадностью опускается вниз, проникая в [npc.her] [npc.asshole+] на [npc2.namePos] [npc2.cock+]."));
				break;
			case DOM_ROUGH:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] тянется вниз и грубо [npc.verb(grab)] [npc2.namePos] [npc2.cock+], после чего устраивается поудобнее. Немного поманя [npc2.cockHead+] над [npc.her] [npc.asshole+], [npc.she] [npc.verb(let)] [npc.a_moan+], в то время как [npc.she] с силой опускается вниз, проникая в [npc.her] [npc.ass+] на [npc2.namePos] [npc2.cock+].",
						"Подвинувшись поудобнее, [npc.name] выравнивает [npc2.cockHead+] [npc2.namePos] [npc2.cock+] между [npc.her] ягодицами и одним резким движением [npc.she] грубо опускается вниз, проникая в [npc.her] [npc.asshole+] на [npc2.namePos] [npc2.cock+]."));
				break;
			default:
				break;
		}
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_EAGER:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_moan+], когда [npc2.name] входит в [npc.herHim], жадно выгибая [npc2.namePos] [npc2.hips], пока [npc2.name] начинают трахать [npc.her] [npc.asshole+].",
						" В случае с [npc2.a_moan+], [npc2.name] с жадностью вгоняет [npc2.namePos] [npc2.hips] в [npc.her] [npc.ass], погружая [npc2.namePos] [npc2.cock+] в [npc.her] [npc.asshole+], пока [npc2.name] начинают энергично трахать [npc.herHim]."));
				break;
			case SUB_NORMAL:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_moan+], когда [npc2.name] входит в [npc.herHim], выгибая [npc2.namePos] [npc2.hips], пока [npc2.name] начинает трахать [npc.her] [npc.asshole+].",
						" С [npc2.a_moan+], [npc2.name] вгоняет [npc2.namePos] [npc2.hips] вверх в [npc.her] [npc.ass], погружая [npc2.namePos] [npc2.cock+] в [npc.her] [npc.asshole+], пока [npc2.name] начинает трахать [npc.herHim]."));
				break;
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_sob+], пока [npc.she] с силой [npc2.namePos] [npc2.cock] внутрь [npc.herHim], и, тщетно пытаясь сопротивляться [npc.herHim], [npc2.name] отчаянно пытается оттолкнуть [npc.herHim] от [npc2.name].",
						" С [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] против [npc.name], пока [npc.she] втискивает [npc2.namePos] [npc2.cock] глубоко в [npc.her] [npc.asshole+]."));
				break;
			default:
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}
	
	public static String getRidingCockAnallyGentle() {
		return UtilText.returnStringAtRandom(
				"Используя ноги [npc.her], [npc.name] медленно [npc.verb(lift)] [npc.herself] вверх и вниз, скользя [npc2.namePos] [npc2.penis+] туда-сюда в [npc.her] [npc.asshole+], пока [npc.she] громко [npc.moansVerb+].",
				"Наклонившись вперед, [npc.name] использует [npc.her] [npc.hands], чтобы поддержать часть веса [npc.her], пока [npc.she] медленно [npc.verb(slide)] вверх и вниз на [npc2.namePos] [npc2.penis+]. Когда [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] к [npc2.name], [npc.she] ощущает аромат [npc2.namePos] [npc2.scent], и [npc.she] кусает [npc.her] [npc.lip], пока [npc.she] вдыхает этот пьянящий запах.",
				"Опуская [npc.her] [npc.hands] на [npc.her] колени, [npc.name] использует [npc.her] [npc.legs], чтобы медленно двигать [npc.herself] вверх и вниз, издавая тихие [npc.moans], пока [npc.she] вонзает [npc.herself] в [npc2.namePos] [npc2.cock+].",
				"[npc.Name] медленно [npc.verb(lift)] [npc.herself] вверх, прежде чем скользнуть обратно вниз, издавая тихий [npc.moan], когда [npc.she] вонзает [npc.her] [npc.asshole+] в [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockAnallyNormal() {
		return UtilText.returnStringAtRandom(
				"Используя ноги [npc.her], [npc.name] [npc.verb(lift)] [npc.herself] вверх и вниз, скользя [npc2.namePos] [npc2.penis+] туда-сюда в [npc.her] [npc.asshole+], пока [npc.she] громко [npc.moansVerb+].",
				"Наклонившись вперед, [npc.name] использует [npc.her] [npc.hands], чтобы удерживать часть [npc.her] веса, пока [npc.she] с нетерпением [npc.verb(slide)] вверх и вниз на [npc2.namePos] [npc2.penis+]. Когда [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] к [npc2.name], [npc.she] ощущает аромат [npc2.namePos] [npc2.scent], и [npc.she] кусает [npc.her] [npc.lip], пока [npc.she] вдыхает этот пьянящий запах.",
				"Опуская [npc.her] [npc.hands] на [npc.her] коленях, [npc.name] использует [npc.her] [npc.legs], чтобы с энтузиазмом двигать [npc.herself] вверх и вниз, издавая [npc.moans+], пока [npc.she] вонзается [npc.herself] в [npc2.namePos] [npc2.cock+].",
				"[npc.Name] [npc.verb(lift)] [npc.herself] вверх, прежде чем жадно скользнуть обратно вниз, издавая [npc.a_moan+], когда [npc.she] вонзает [npc.her] [npc.asshole+] в [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getRidingCockAnallyRough() {
		return UtilText.returnStringAtRandom(
				"Используя [npc.her] ноги, [npc.name] стремительно подпрыгивает на [npc.herself] вверх и вниз, с силой вгоняя [npc2.namePos] [npc2.penis+] в [npc.her] [npc.asshole+], пока [npc.she] громко [npc.moansVerb+].",
				"Наклонившись вперед, [npc.name] опирается на [npc.her] [npc.hands], чтобы поддержать часть веса [npc.her], пока [npc.she] начинает стремительно подпрыгивать вверх-вниз на [npc2.namePos] [npc2.penis+]. Когда [npc.she] [npc.verb(lower)] [npc.her] [npc.face+] к [npc2.name], [npc.she] ощущает аромат [npc2.namePos] [npc2.scent], и [npc.she] прикусывает [npc.her] [npc.lip], пока [npc.she] вдыхает этот пьянящий запах.",
				"Положив [npc.her] [npc.hands] на пол позади [npc.herHim] для опоры, [npc.name] начинает стремительно подпрыгивать на [npc.herself] вверх-вниз, издавая дрожащие, [npc.moans+] звуки, пока [npc.she] вонзает [npc.herself] в [npc2.namePos] [npc2.cock+].",
				"Усмехаясь на [npc2.name], [npc.name] с энтузиазмом начинает прыгать вверх и вниз, издавая [npc.a_moan+], пока [npc.she] вонзается в [npc.her] [npc.asshole+] на [npc2.namePos] [npc2.penis+].");
	}
	
	public static String getStoppingAnalPenetrationDescription() {
		
		UtilText.nodeContentSB.setLength(0);
		
		switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
			case DOM_ROUGH:
				UtilText.nodeContentSB.append("Просунув руку под [npc.herHim], [npc.name] грубо [npc.verb(grab)] основание [npc2.namePos] [npc2.cock], а затем приподнимает [npc.herself], позволяя [npc2.namePos] [npc2.cock+] выскользнуть из [npc.her] [npc.asshole+].");
				break;
			default:
				UtilText.nodeContentSB.append("Просунув руку под [npc.herHim], [npc.name] обхватывает основание [npc2.namePos] [npc2.cock], а затем приподнимает [npc.herself], позволяя [npc2.namePos] [npc2.cock+] выскользнуть из [npc.her] [npc.asshole+].");
				break;
		}
		
		switch(Main.sex.getSexPace(Main.game.getPlayer())) {
			case SUB_RESISTING:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает облегченный вздох, который вскоре сменяется на [npc2.a_sob+], когда [npc2.name] понимает, что [npc.she] еще не закончил с [npc2.name].",
						" [npc2.a_sob+] и [npc2.name] продолжают протестовать и вырываться против [npc.herHim], пока [npc.she] крепко удерживает [npc2.name] на месте."));
				break;
			default:
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] издает [npc2.a_moan+], когда [npc.she] перестает скакать на [npc2.name].",
						" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.namePos] желание продолжать трахать [npc.her] [npc.ass+]."));
				break;
		}
		
		return UtilText.nodeContentSB.toString();
	}



}
