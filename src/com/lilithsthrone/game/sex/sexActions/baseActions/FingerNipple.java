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
 * @version 0.2.8
 * @author Innoxia
 */
public class FingerNipple {
	
	public static final SexAction PINCH_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сжимать соски";
		}

		@Override
		public String getActionDescription() {
			return "Сжимать [npc2.namePos] [npc2.nipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протягивая руку к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(start)] осторожно сжимает и растирает [npc2.her] [npc2.nipples+].",

							"[npc2.NamePos] [npc2.breasts+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] мог их игнорировать, и с тихим маленьким [npc.moan], [npc.she] [npc.verb(start)] нежно тянет и щиплет [npc2.her] [npc2.nipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breastRows] [npc2.breasts+], [npc.name] [npc.verb(start)] осторожно тянет и щиплет [npc2.her] [npc2.nipples+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протягивая руку к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] нежный [npc.a_moan+], пока [npc.she] [npc.verb(start)] с азартом сжимает и растирает [npc2.her] [npc2.nipples+].",

							"[npc2.NamePos] [npc2.breasts+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] мог их игнорировать, и с [npc.a_moan+], [npc.she] [npc.verb(start)] с азартом тянет и щиплет [npc2.her] [npc2.nipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breastRows] [npc2.breasts+], [npc.name] [npc.verb(start)] с азартом тянет и щиплет [npc2.her] [npc2.nipples+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руку к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ощупывает [npc2.namePos] грудь, а затем переходит к тому, чтобы с силой ущипнуть и сжать [npc2.her] [npc2.nipples+].",

							"[npc2.NamePos] [npc2.breasts+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] мог их игнорировать, и при [npc.a_moan+], [npc.she] [npc.verb(start)] грубо щипает и сжимает [npc2.her] [npc2.nipples+].",

							"Погружая [npc.her] [npc.fingers] в [npc2.namePos] [npc2.breastRows] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем начать грубо щипать и сжимать [npc2.her] [npc2.nipples+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протягивая руку к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] нежный [npc.a_moan+], пока [npc.she] [npc.verb(start)] с азартом сжимает и растирает [npc2.her] [npc2.nipples+].",

							"[npc2.NamePos] [npc2.breasts+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] мог их игнорировать, и с [npc.a_moan+], [npc.she] [npc.verb(start)] с азартом тянет и щиплет [npc2.her] [npc2.nipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breastRows] [npc2.breasts+], [npc.name] [npc.verb(start)] с азартом тянет и щиплет [npc2.her] [npc2.nipples+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руку к [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] щипает и растирает [npc2.her] [npc2.nipples+].",

							"[npc2.NamePos] [npc2.breasts+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] мог их игнорировать, и при [npc.a_moan+], [npc.she] [npc.verb(start)] тянет и щиплет [npc2.her] [npc2.nipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breastRows] [npc2.breasts+], [npc.name] [npc.verb(start)] тянет и щиплет [npc2.her] [npc2.nipples+]."));
					break;
				default:
					break;
			}
			
			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan] при прикосновении [npc.namePos], прежде чем мягко подталкивая [npc.herHim] продолжать уделять [npc2.her] [npc2.nipples+] [npc.her] всё своё внимание.",
	
								" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] [npc2.her] вперед, умоляя [npc.name] продолжать, пока [npc2.she] [npc2.verb(carry)] издает похотливые звуки.",
	
								" Тихо [npc2.moaning] от [npc.her] прикосновения, [npc2.name] нежно [npc2.verb(encourage)] [npc.name], чтобы продолжать стимулировать [npc2.her] [npc2.nipples+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] от [npc2.a_moan+] при прикосновении [npc.namePos], прежде чем с нетерпением подгонять [npc.herHim] продолжать уделять [npc2.her] [npc2.nipples+] все свое [npc.her] внимание.",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] вперед, умоляя [npc.name] продолжать, пока [npc2.she] [npc2.verb(carry)] издает похотливые звуки.",
	
								" [npc2.Moaning+] от [npc.her] при прикосновении, [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.name], чтобы продолжать стимулировать [npc2.her] [npc2.nipples+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] от [npc2.a_moan+] при прикосновении [npc.namePos], прежде чем грубо зарычать, требуя от [npc.herHim] продолжать уделять [npc2.her] [npc2.nipples+] все свое [npc.her] внимание.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] вперед и твердым тоном велит [npc2.she] [npc2.verb(order)] [npc.name] продолжать уделять [npc2.her] [npc2.nipples+] все свое [npc.her] внимание.",
	
								" Издавая [npc2.a_moan+] от [npc.her] при прикосновении, [npc2.name] [npc2.verb(demand)], что [npc.she] продолжает стимулировать [npc2.her] [npc2.nipples+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] от [npc2.a_moan+] при прикосновении [npc.namePos], прежде чем с нетерпением подгонять [npc.herHim] продолжать уделять [npc2.her] [npc2.nipples+] все свое [npc.her] внимание.",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)], выпятив [npc2.her] грудь и умоляя [npc.herHim] продолжать, пока [npc2.she] [npc2.verb(carry)], издавая похотливые звуки.",
	
								" [npc2.Moaning+] от [npc.her] при прикосновении, [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.name], чтобы продолжать стимулировать [npc2.her] [npc2.nipples+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] от прикосновения [npc.namePos], после чего подстрекая [npc.herHim] продолжать уделять [npc2.her] [npc2.nipples+] все свое [npc.her] внимание.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выпятив [npc2.her] грудь и умоляя [npc.herHim] продолжать, пока [npc2.she] [npc2.verb(carry)], издавая похотливые звуки.",
	
								" [npc2.Moaning+] при прикосновении [npc.her], [npc2.name] [npc2.verb(encourage)] [npc.name], чтобы продолжать стимулировать [npc2.her] [npc2.nipples+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] инстинктивно [npc2.verb(try)], чтобы отпрянуть, [npc2.sobbing] и сопротивляясь прикосновению [npc.namePos], пока [npc2.she] [npc2.verb(try)], пытаясь отбросить [npc.her] [npc.fingers] от [npc2.her] [npc2.nipples+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)], корчась от дискомфорта и умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc.verb(continue)], стимулируя [npc2.her] [npc2.nipples+].",
	
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] в ответ на прикосновение [npc.namePos], и пока [npc.name] продолжает стимулировать [npc2.her] [npc2.nipples+], [npc2.name] [npc2.verb(continue)], сопротивляясь [npc.herHim]."));
						break;
					default:
						break;
				}
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" Когда [npc.name] [npc.verb(start)] щипает [npc2.namePos] [npc2.nipples], тонкая струйка [npc2.milk] вытекает и стекает по [npc2.her] [npc2.breasts+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" Когда [npc.name] [npc.verb(start)] щипает [npc2.namePos] [npc2.nipples], небольшая порция [npc2.milk] вырывается наружу и стекает по [npc2.her] [npc2.breasts+].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" Когда [npc.name] [npc.verb(start)] щипает [npc2.namePos] [npc2.nipples], струйка [npc2.milk] стекает по [npc2.her] [npc2.breasts+].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], вытекая на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.breasts+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], выплескиваясь на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], изливаясь на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], изливаясь на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.breasts+].");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction MILK_TARGET = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Молоко [npc2.name]";
		}

		@Override
		public String getActionDescription() {
			return "Щипать и тянуть за [npc2.namePos] [npc2.nipples+], чтобы выжать [npc2.her] [npc2.milk+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(trace)] [npc.her] [npc.fingers+] вверх и через [npc2.namePos] [npc2.breasts+], прежде чем сосредоточиться на [npc2.her] [npc2.nipples+]. Мягко потягивая и сжимая [npc2.namePos] [npc2.nipples+], [npc.name] [npc.verb(let)] нежный крик восторга, пока [npc.she] [npc.verb(manage)] чтобы вытянуть",

							"С мягким [npc.moan], [npc.name] осторожно [npc.verb(press)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.breasts+], прежде чем спуститься ниже, чтобы дразнить [npc2.her] [npc2.nipples+]. Деликатно щипая и сжимая [npc2.namePos] [npc2.nipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc.she] [npc.verb(manage)] чтобы вытянуть"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] жадно [npc.verb(trace)] [npc.her] [npc.fingers+] вверх и через [npc2.namePos] [npc2.breasts+], прежде чем быстро сосредоточиться на [npc2.her] [npc2.nipples+]. Алчно потягивая и сжимая [npc2.namePos] [npc2.nipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc.she] [npc.verb(manage)] чтобы вытянуть",

							"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.breasts+], прежде чем быстро спуститься ниже, чтобы дразнить [npc2.her] [npc2.nipples+]. Алчно щипая и сжимая [npc2.namePos] [npc2.nipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc.she] [npc.verb(manage)] чтобы вытянуть"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(dig)] [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.breasts+], прежде чем перейти к [npc.her] грубым касаниям [npc2.her] [npc2.nipples+]. Сильно щипая и сжимая [npc2.namePos] [npc2.nipples+], [npc.name] [npc.verb(let)] победный рык, пока [npc.she] [npc.verb(manage)], чтобы выжать",

							"С помощью [npc.a_moan+], [npc.name] грубо [npc.verb(dig)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.breasts+], прежде чем быстро спуститься и грубо щелкнуть по [npc2.her] [npc2.nipples+]. Доминантно щипая и сжимая [npc2.namePos] [npc2.nipples+], [npc.name] [npc.verb(let)] вырывается [npc.a_moan+], пока [npc.she] [npc.verb(manage)], чтобы выжать"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastStoredMilk()) {
				case ZERO_NONE://Shouldn't be able to be reached
					UtilText.nodeContentSB.append(" никаких [npc2.milk] из [npc2.namePos] [npc2.breasts+]."); 
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" тонкая струйка [npc2.milk] из [npc2.her] [npc2.breasts+].",

							" небольшая струйка [npc2.milk] из [npc2.her] [npc2.breasts+]."));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" маленькая порция [npc2.milk] из [npc2.her] [npc2.breasts+].",

							" небольшой поток [npc2.milk] из [npc2.her] [npc2.breasts+]."));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" струя [npc2.milk] из [npc2.her] [npc2.breasts+].",

							" струйка [npc2.milk] из [npc2.her] [npc2.breasts+]."));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" поток [npc2.milk] из [npc2.her] [npc2.breasts+].",

							" тонкая струйка [npc2.milk] из [npc2.her] [npc2.breasts+]."));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" густая струя [npc2.milk] из [npc2.her] [npc2.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" приличное количество [npc2.milk] из [npc2.her] [npc2.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" огромное количество [npc2.milk] из [npc2.her] [npc2.breasts+].");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], пока [npc.name] [npc.verb(start)], чтобы подоить [npc2.herHim], и, выталкивая [npc2.her] [npc2.breasts] в [npc.her] [npc.hands], [npc2.name] мягко [npc2.verb(encourage)] [npc.name], чтобы продолжить.",
	
								" [npc2.Name] не может не [npc2.moanVerb] от восхитительного чувства, когда [npc2.her] [npc2.breasts] подоены, и, прикусывая [npc2.her] [npc2.lip], [npc2.she] нежно [npc2.verb(plead)] с [npc.name], чтобы продолжить."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] восторженный [npc2.moan], пока [npc.name] [npc.verb(start)], чтобы подоить [npc2.herHim], и, лихорадочно выталкивая [npc2.her] [npc2.breasts] в [npc.her] [npc.hands], [npc2.name] счастливо [npc2.verb(encourage)] [npc.name], чтобы продолжить.",
	
								" [npc2.Name] не может не [npc2.moanVerb] от восхитительного чувства, когда [npc2.her] [npc2.breasts] подоены, и, прикусывая [npc2.her] [npc2.lip], [npc2.she] жадно [npc2.verb(plead)] с [npc.name], чтобы продолжить."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] глубоко и удовлетворенно зарычал, когда [npc.name] [npc.verb(start)], чтобы подоить [npc2.herHim], и, выталкивая [npc2.her] [npc2.breasts] в [npc.her] [npc.hands], [npc2.name] грубо [npc2.verb(order)] [npc.name], чтобы продолжить.",
	
								" [npc2.Name] не может не [npc2.moanVerb] от восхитительного чувства того, что [npc2.her] [npc2.breasts] подоены, и, прикусывая [npc2.her] [npc2.lip], [npc2.she] грубо [npc2.verb(order)] [npc.name], чтобы продолжить."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], словно [npc.name], [npc.verb(start)], чтобы подоить [npc2.herHim], и, пытаясь оттянуть [npc2.her] [npc2.breasts] от [npc.her] [npc.hands], [npc2.name] отчаянно [npc2.verb(plead)] с [npc.name], чтобы остановиться.",
	
								" Отчаянно пытаясь оттянуть [npc2.her] [npc2.breasts+] от нежелательного доения, [npc2.name] [npc2.verb(plead)] с помощью [npc.name], чтобы оставить [npc2.herHim] в покое."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastStoredMilk(-Main.sex.getCharacterTargetedForSexAction(this).getBreastRawMilkStorageValue()/5);
		}
		
	};
	
	public static final SexAction GET_MILKED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Дать себя подоить";
		}

		@Override
		public String getActionDescription() {
			return "Направь [npc2.namePos] [npc2.hands] к своим [npc.breasts+], чтобы заставить [npc2.herHim] подоить тебя.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(encourage)] [npc2.herHim], чтобы провести [npc2.her] [npc2.fingers+] вверх и через [npc.her] [npc.breasts+], прежде чем мягко направить [npc2.herHim] вниз к [npc.her] [npc.nipples+]. Приказывая [npc2.herHim] дергать и сжимать [npc.her] [npc.nipples+], [npc.name] [npc.verb(let)] тихий крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы выжать",

							"С мягким [npc.moan], [npc.name] нежно [npc.verb(guide)] [npc2.namePos] [npc2.fingers] в [npc.her] [npc.breasts+], прежде чем переместить [npc2.her] касание вниз, чтобы дразнить [npc.her] [npc.nipples+]. Приказывая [npc2.herHim] щипать и сжимать [npc.her] [npc.nipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы вытянуть"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] направляет [npc2.her] [npc2.fingers+] вверх и через [npc.her] [npc.breasts+], прежде чем жадно вести [npc2.herHim] вниз к [npc.her] [npc.nipples+]. Веля [npc2.herHim] тянуть и сжимать [npc.her] [npc.nipples+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.namePos] [npc2.verb(manage)], чтобы вытянуть",

							"С [npc.a_moan+], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] в [npc.her] [npc.breasts+], прежде чем переместить [npc2.her] касание вниз, чтобы дразнить [npc.her] [npc.nipples+]. Приказывая [npc2.herHim] щипать и сжимать [npc.her] [npc.nipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы вытянуть"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] грубо [npc.verb(force)] [npc2.her] [npc2.fingers+] вверх и через [npc.her] [npc.breasts+], а затем властно направляя [npc2.her] касание к [npc.her] [npc.nipples+]. Приказывая [npc2.herHim] дергать и сжимать [npc.her] [npc.nipples+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.namePos] [npc2.verb(manage)], чтобы выжать",

							"Схватив [npc.a_moan+], [npc.name] с силой [npc.verb(guide)] [npc2.namePos] [npc2.fingers] в [npc.her] [npc.breasts+], прежде чем переместить [npc2.her] касание, чтобы дразнить [npc.her] [npc.nipples+]. Приказывая [npc2.herHim] щипать и сжимать [npc.her] [npc.nipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы выжать"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterPerformingAction().getBreastStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(" никаких [npc.milk] из [npc.namePos] [npc.breasts+]."); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" тонкая струйка [npc.milk] из [npc.her] [npc.breasts+].",

							" небольшая струйка [npc.milk] из [npc.her] [npc.breasts+]."));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" маленькая порция [npc.milk] из [npc.her] [npc.breasts+].",

							" небольшая струя [npc.milk] из [npc.her] [npc.breasts+]."));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" струя [npc.milk] из [npc.her] [npc.breasts+].",

							" струйка [npc.milk] из [npc.her] [npc.breasts+]."));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" поток [npc.milk] из [npc.her] [npc.breasts+].",

							" тонкая струйка [npc.milk] из [npc.her] [npc.breasts+]."));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" густая струя [npc.milk] из [npc.her] [npc.breasts+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" приличное количество [npc.milk] из [npc.her] [npc.breasts+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" огромное количество [npc.milk] из [npc.her] [npc.breasts+].");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(start)], чтобы подоить [npc.name], и когда [npc.name] [npc.verb(push)] [npc.her] [npc.breasts] в [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)], нежно вытягивая [npc.milk+] из [npc.her] [npc.nipples].",
	
								" [npc2.Name] не может не издать [npc2.a_moan+] от восхитительного чувства, когда [npc.namePos] [npc.breasts], и, воодушевленный похожими звуками, которые издает [npc.sheIs], [npc2.she] [npc2.verb(continue)], нежно дразня [npc.milk+] из [npc.namePos] [npc.nipples]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(start)], чтобы подоить [npc.name], и когда [npc.name] [npc.verb(push)] [npc.her] [npc.breasts] в [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)], жадно вытягивая [npc.milk+] из [npc.her] [npc.nipples].",
	
								" [npc2.Name] не может не издать [npc2.a_moan+] от восхитительного чувства, когда [npc.namePos] [npc.breasts], и, воодушевленный похожими звуками, которые издает [npc.sheIs], [npc2.she] [npc2.verb(continue)], жадно выжимая [npc.milk+] из [npc.namePos] [npc.nipples]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] низким рыком, пока [npc2.she] [npc2.verb(start)] сцеживает [npc.name], а когда [npc.name] [npc.verb(push)] [npc.her] [npc.breasts] наружу в [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)] грубо вытягивает [npc.milk+] из [npc.her] [npc.nipples].",
	
								" [npc2.Name] не может не издать [npc2.a_moan+] от ощущения, когда [npc.breasts] сцеживают [npc.namePos], и, воодушевленная похожими звуками, которые издает [npc.sheIs], [npc2.she] [npc2.verb(continue)] грубо сжимает [npc.milk+] из [npc.namePos] [npc.nipples]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] и [npc2.verb(try)] отстраниться, но резким рывком [npc.name] [npc.verb(pull)] [npc2.her] [npc2.hands] обратно в [npc.her] [npc.breasts], ясно давая понять, что у [npc2.sheHasFull] нет другого выбора, кроме как выдавливать [npc.milk+] из [npc.her] [npc.nipples].",
	
								" [npc2.Name] отчаянно [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.breasts], но, схватив [npc2.her] за [npc2.hand], [npc.name] [npc.verb(force)] [npc2.herHim], чтобы продолжать сжимать [npc.milk+] из [npc.her] [npc.nipples]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastStoredMilk(-Main.sex.getCharacterPerformingAction().getBreastRawMilkStorageValue()/5);
		}
	};
	
	
	
	public static final SexAction NIPPLE_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласки сосков пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Погрузи свои [npc.fingers] в одну из [npc2.namePos] доступных для траха [npc2.nipples] и [npc2.verb(start)] ласкай пальцами [npc2.her] груди.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breasts+], [npc2.name] [npc2.verb(let)] судорожный вздох, пока [npc.name] [npc.verb(circle)] вокруг одного из [npc2.her] [npc2.nipples+], прежде чем медленно втиснуть [npc.her] пальцев в [npc2.her] манящее отверстие.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] на одном из [npc2.namePos] [npc2.nipples+], и с медленным, ровным нажимом, [npc.she] нежно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breasts+], [npc2.name] [npc2.verb(let)] судорожный вздох, пока [npc.name] [npc.verb(circle)] вокруг одного из [npc2.her] [npc2.nipples+], прежде чем жадно втиснуть [npc.her] пальцев в [npc2.her] манящее отверстие.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] на одном из [npc2.namePos] [npc2.nipples+], и с уверенным нажимом, [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Ощупывая и сжимая [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(start)] обводить [npc.her] [npc.fingers] вокруг одного из [npc2.her] [npc2.nipples+], прежде чем грубо втиснуть [npc.her] пальцев в [npc2.namePos] манящее отверстие.",

							"Алчно прижимая [npc.her] [npc.fingers] к одной из [npc2.namePos] [npc2.nipples+], [npc.name] [npc.verb(let)] негромкий рык, пока [npc.she] грубо [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breasts+], [npc2.name] [npc2.verb(let)] вздох, пока [npc.name] жадно [npc.verb(circle)] вокруг одного из [npc2.her] [npc2.nipples+], прежде чем отчаянно вогнать [npc.her] пальцев в [npc2.her] манящее отверстие.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] к одной из [npc2.namePos] [npc2.nipples+], и с настойчивым давлением [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.breasts+], [npc2.name] [npc2.verb(let)] судорожный вздох, пока [npc.name] [npc.verb(circle)] вокруг одного из [npc2.her] [npc2.nipples+], прежде чем жадно втиснуть [npc.her] пальцев в [npc2.her] манящее отверстие.",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] на одном из [npc2.namePos] [npc2.nipples+], и с уверенным нажимом, [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc.name] [npc.verb(start)] перебирает [npc2.namePos] [npc2.breasts], мягко выталкивая [npc2.her] грудь, когда [npc2.she] [npc2.verb(help)] [npc.name] погружают [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.nipple+].",
	
								" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] погрузить [npc.her] [npc.fingers+] еще глубже в [npc2.her] грудь."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+] как [npc.name] [npc.verb(start)] ласкает пальцами [npc2.namePos] [npc2.breast(true)], нетерпеливо выпячивая [npc2.her] грудь, пока [npc2.she] [npc2.verb(help)] [npc.name] погружает [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.nipple+].",
	
								" С [npc.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] выпячивает [npc2.her] грудь, умоляя [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+] как [npc.name], [npc.verb(start)] перебирая пальцами [npc2.namePos] [npc2.breasts], яростно выпячивая [npc2.her] грудь навстречу [npc.her], пока [npc2.she] [npc2.verb(command)] [npc.name], чтобы погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] отвечает яростным выпячиванием [npc2.her] груди, приказывая [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+] как [npc.name] [npc.verb(start)] перебирая пальцами [npc2.namePos] [npc2.breasts], жадно выпячивая [npc2.her] грудь, пока [npc2.name] [npc2.verb(help)] [npc.name] погружаются [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.nipple+].",
	
								" С [npc.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] выпячивает [npc2.her] грудь, умоляя [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает из [npc2.a_moan+] [npc.name], пока [npc.verb(start)] перебирает пальцами [npc2.namePos] [npc2.breast(true)], с нетерпением выпячивая [npc2.her] грудь, пока [npc2.name] [npc2.verb(help)] [npc.name] погружаются [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.nipple+].",
	
								" С [npc.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] выпячивает [npc2.her] грудь, умоляя [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)], чтобы отпрянуть, [npc2.sobbing] и корчась от дискомфорта, пока [npc.name] [npc.verb(start)] заставляет [npc.her] [npc.fingers] входить и выходить из [npc2.her] [npc2.nipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно, пытаясь отстраниться от нежелательного проникновения, протестуя и борясь против [npc.namePos] прикосновений, пока [npc.she] [npc.verb(pump)] [npc.her] [npc.fingers+] входит и выходит из [npc2.her] [npc2.nipple+]."));
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
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] грудь в ответ, издавая восторженный [npc2.moan], пока [npc2.she] [npc2.verb(start)] с энтузиазмом умоляет [npc.name] продолжать ласкать пальцами [npc2.her] [npc2.breasts].",
		
							" Восторженный [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(start)] с нетерпением выпячивает [npc2.her] грудь навстречу [npc.namePos] прикосновениям, пока [npc2.she] [npc2.verb(beg)] [npc.herHim] продолжать перебирать пальцами [npc2.her] [npc2.nipples+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(push)] из [npc2.her] груди, жадно умоляя [npc.name] продолжать ласкать [npc2.her] [npc2.breasts], пока движения [npc2.her] заставляют [npc.name] погружаться [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.nipple+]."));
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Не в силах отстранить [npc2.her] грудь от прикосновений [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name] от [npc2.herHim].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], прежде чем [npc2.she] [npc2.verb(start)] слабо пытается оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)] нежно перебирает [npc2.her] [npc2.nipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)] отстранить [npc2.her] грудь от прикосновений [npc.namePos], борясь с [npc.herHim], пока [npc.her] [npc.fingers] [npc.verb(continue)] плавно скользит глубоко в [npc2.her] [npc2.nipple+]."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] грудь в ответ, издавая восторженный [npc2.moan], прежде чем с энтузиазмом начать умолять [npc.name] продолжать ласкать [npc2.her] [npc2.breasts].",
	
							" Восторженный [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips], а [npc2.she] [npc2.verb(start)], мягко выпячивая [npc2.her] грудь навстречу [npc.namePos], пока [npc2.she] [npc2.verb(beg)] [npc.herHim], чтобы продолжить ласкать [npc2.her] [npc2.nipples+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(push)] вперед [npc2.her] грудь, умоляя [npc.name] продолжать ласкать [npc2.her] [npc2.breasts], пока движения [npc2.her] заставляют [npc.name] погружать [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.nipple+]."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] навстречу выпячивает [npc2.her] грудь, издавая восторженный [npc2.moan], пока [npc2.she] [npc2.verb(start)] приказывает [npc.name] продолжать ласкать [npc2.her] [npc2.breasts].",
	
							" Восторженный [npc2.moan] вырывается из-за [npc2.namePos] [npc2.lips], а [npc2.she] [npc2.verb(start)], грубо выпячивая [npc2.her] грудь навстречу [npc.namePos], пока [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжить ласкать [npc2.her] [npc2.nipples+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(thrust)] вперед [npc2.her] грудь, приказывая [npc.name] продолжать ласкать [npc2.her] [npc2.breasts], пока внезапное движение [npc2.her] заставляет [npc.name] погружать [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.nipple+]."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] вперед в ответ, издавая [npc2.a_moan], пока [npc2.she] [npc2.verb(implore)] [npc.name], чтобы продолжить ласкать [npc2.her] [npc2.breasts].",
		
							" [npc2.A_moan+] вырывается из-за [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(push)] [npc2.her] грудь навстречу [npc.namePos] прикосновениям, пока [npc2.she] [npc2.verb(implore)] [npc.herHim], чтобы продолжить ласкать [npc2.her] [npc2.nipples+].",
		
							" [npc2.Moaning+], [npc2.name] [npc2.verb(push)] [npc2.her] вперед, умоляя [npc.name] продолжать ласкать [npc2.her] [npc2.breasts], пока движения [npc2.her] заставляют [npc.name] погружаться [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.nipple+]."));
			}
		}
		return "";
	}
	
	public static final SexAction NIPPLE_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежные ласки сосков пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Нежно помассируй сосок [npc2.namePos] пальцами.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] медленно [npc.verb(start)], чтобы скользить [npc.her] пальцами внутрь и наружу из [npc2.her] [npc2.breast(true)].",

					"[npc.Name] нежно [npc.verb(lean)] в [npc2.name], заставляя [npc2.herHim] вдохнуть [npc.her] [npc.scent+], пока [npc.she] мягко [npc.verb(pump)] [npc.her] [npc.fingers+] внутрь и наружу из [npc2.her] [npc2.nipple+].",

					"Нежно прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] мягко [npc.verb(pump)] [npc.her] [npc.fingers+] внутрь и наружу из [npc2.namePos] [npc2.nipple+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласкать сосок пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать ласкать пальцами [npc2.namePos] [npc2.nipple+].";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] жадно перебирает [npc2.her] [npc2.breasts], издавая [npc.a_moan+], пока [npc.she] [npc.verb(press)] [npc.herself] прижимается к [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] против [npc2.herHim], [npc.her] [npc.scent+] захлестывают чувства [npc2.namePos], пока [npc.she] [npc.verb(start)] жадно водит [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.nipple+].",

					"Прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно водит [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубая стимуляция сосков пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Грубо ласкать сосок [npc2.namePos] пальцами.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Алчно погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(start)] грубо вколачивает [npc.her] пальцы туда-сюда, стремительно лаская [npc2.namePos] [npc2.breast(true)], пока [npc.she] трется [npc.herself] о [npc2.herHim].",

					"[npc.Name] трется [npc.herself] о [npc2.name], заставляя [npc2.herHim] вдыхать [npc.her] [npc.scent+], пока [npc.she] [npc.verb(start)] грубо вколачивает [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.nipple+].",

					"[npc.herself] с силой прижимается к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо вбивает [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.nipple+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласкать сосок пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить ласкать сосок [npc2.namePos] пальцами.";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.her] [npc.fingers+] глубоко погружается в [npc2.namePos] [npc2.nipple+], пока [npc.name] [npc.verb(start)] водит [npc.her] пальцами туда-сюда, неистово лаская [npc2.namePos] [npc2.breast(true)], пока [npc.she] [npc.verb(press)] [npc.herself] прижимается к [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] в [npc2.name], заставляя [npc2.herHim] вдыхать [npc.her] [npc.scent+], пока [npc.she] [npc.verb(start)] качает [npc.her] [npc.fingers+] туда-сюда в [npc2.her] [npc2.nipple+].",

					"Прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] качает [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Неистовая ласка сосков";
		}

		@Override
		public String getActionDescription() {
			return "Неистово ласкать сосок [npc2.namePos] пальцами.";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.her] [npc.fingers+] глубоко погружается в [npc2.namePos] [npc2.nipple+], пока [npc.name] [npc.verb(start)] водит [npc.her] пальцами туда-сюда, жадно лаская [npc2.her] [npc2.breast(true)], пока [npc.she] [npc.verb(press)] [npc.herself] прижимается к [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] в [npc2.name], заставляя [npc2.herHim] прерывисто [npc.her] [npc.scent+], пока [npc.she] с нетерпением [npc.verb(start)] [npc.her] [npc.fingers+] туда-сюда в [npc2.her] [npc2.nipple+].",

					"Прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно водит [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.nipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться ласкам сосков пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.fingers] из [npc2.namePos] [npc2.nipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.nipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.nipple+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.nipple+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] на месте, прежде чем мягко выпятить [npc2.her] грудь и с силой вдавить [npc.namePos] [npc.fingers] глубоко в [npc2.her] [npc2.breast(true)]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.nipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.nipple+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.nipple+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] на месте, прежде чем жадно выпятить [npc2.her] грудь и с силой вжать [npc.namePos] [npc.fingers] глубоко в [npc2.her] [npc2.breast(true)]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.nipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.nipple+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.nipple+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] на месте, прежде чем грубо выпятить [npc2.her] грудь и с силой вжать [npc.namePos] [npc.fingers] глубоко в [npc2.her] [npc2.breast(true)]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Перестать ласкать сосок пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи [npc.fingers] из [npc2.namePos] соска и перестань ласкать [npc2.her] грудь.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc.her] [npc.fingers] из [npc2.namePos] [npc2.nipple+], [npc.name] [npc.verb(give)] [npc2.namePos] [npc2.breast(true)] напоследок грубым сжатием, когда [npc.she] перестает ласкать [npc2.her] грудь.",

							"[npc.Name] резко [npc.verb(inhale)], вдыхая [npc2.namePos] [npc2.scent+], прежде чем грубо выдернуть [npc.her] [npc.fingers] из [npc2.her] [npc2.nipple(true)]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывание [npc.her] [npc.fingers] из [npc2.namePos] соска, [npc.name] [npc.verb(give)] [npc2.namePos] [npc2.breast(true)] напоследок сжатием, когда [npc.she] перестает ласкать [npc2.her] грудь.",

							"[npc.Name] резко [npc.verb(inhale)], вдыхая [npc2.namePos] [npc2.scent+], прежде чем выскользнуть [npc.her] [npc.fingers] из [npc2.her] [npc2.nipple(true)]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] с облегчением, что вскоре переходит в [npc2.a_sob+], когда [npc2.she] [npc2.verb(continue)], пытаясь вырваться из [npc.name].",

							" Из-за [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], протестуя и дергаясь от дискомфорта, пока [npc.name] удерживает [npc2.herHim] на месте."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.name] перестает стимулировать [npc2.her] [npc2.breasts+].",

							" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] жажду еще большего внимания к [npc.namePos]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction NIPPLE_FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Подставить сосок для пальцев";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать ласкать ваши [npc.nipple+] пальцами.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко, но уверенно обхватив [npc2.namePos] [npc2.hand], [npc.name] медленно [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх и через [npc.her] [npc.breasts], издавая легкий [npc.moan], прежде чем вогнать [npc2.her] пальцев в [npc.her] [npc.nipple+].",
							
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх к [npc.her] [npc.breasts], и с медленным, уверенным нажимом, [npc.she] нежно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.breasts], издавая [npc.a_moan+], прежде чем жадно вогнать [npc2.her] пальцев в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] до самого [npc.her] [npc.breasts], и с решительным напором [npc.she] жадно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мертвой хваткой вцепившись в [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.breasts], издавая [npc.a_moan+], прежде чем грубо втиснуть [npc2.her] пальцев в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] властно [npc.verb(push)] [npc2.namePos] [npc2.fingers] до самого [npc.her] [npc.breasts], и доминирующим, дерганым движением [npc.she] грубо [npc.verb(stuff)] [npc2.her] пальцев в [npc.her] [npc.nipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.breasts], издавая [npc.a_moan+], прежде чем жадно вогнать [npc2.her] пальцев в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] до [npc.her] [npc.breasts], и с решительным напором, [npc.she] жадно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.nipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.breasts], издавая [npc.a_moan+], прежде чем вогнать [npc2.her] пальцев в [npc.her] [npc.nipple+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] до [npc.her] [npc.breasts], и с решительным напором, [npc.she] [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], когда [npc2.she] [npc2.verb(enter)] [npc.herHim], поджимая [npc2.her] [npc2.fingers], прежде чем мягко начать ласкать пальцами [npc.her] [npc.nipple+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], осторожно вдавливая [npc2.her] [npc2.hand] в мягкую плоть [npc.her] [npc.breast(true)], пока [npc2.she] [npc2.verb(set)] о том, как ласкать пальцами [npc.her] [npc.nipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+], когда [npc2.she] [npc2.verb(enter)] [npc.herHim], поджимая [npc2.her] [npc2.fingers], прежде чем с нетерпением начать ласкать пальцами [npc.her] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно вдавливая [npc2.her] [npc2.hand] в мягкую плоть [npc.her] [npc.breast(true)], пока [npc2.she] [npc2.verb(set)] о том, как пальцы [npc.her] [npc.nipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо [npc2.verb(curl)] [npc2.her] [npc2.fingers], прежде чем начать беспощадно трахать [npc.her] [npc.nipple+] пальцами.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], стремясь напомнить [npc.herHim], кто здесь главный, пока [npc2.she] [npc2.verb(start)] грубо перебирая пальцами [npc.her] [npc.nipple+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+], когда [npc2.she] [npc2.verb(enter)] [npc.herHim], поджимая [npc2.her] [npc2.fingers], прежде чем с нетерпением начать ласкать пальцами [npc.her] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно вдавливая [npc2.her] [npc2.hand] в мягкую плоть [npc.her] [npc.breast(true)], пока [npc2.she] [npc2.verb(set)] о том, как пальцы [npc.her] [npc.nipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], подкручивая [npc2.her] [npc2.fingers], прежде чем начать ласкать пальцами [npc.her] [npc.nipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], вдавливая [npc2.her] [npc2.hand] в мягкую плоть [npc.her] [npc.breast(true)], пока [npc2.she] [npc2.verb(set)], перебирая [npc.her] [npc.nipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.fingers] внутри [npc.herHim], пытаясь вырваться из [npc.namePos] крепкой хватки на [npc2.her] [npc2.hand], пока [npc2.she] [npc2.verb(try)] чтобы вытащить [npc2.herself].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] отчаянно пытается вырваться из [npc.namePos] тугой хватки на [npc2.her] [npc2.hand], умоляя [npc.name] остановиться, пока [npc.she] [npc2.verb(force)] [npc2.her] [npc2.fingers] глубоко в [npc.her] [npc.nipple+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно ласкать соски пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Нежно наслаждайся [npc2.namePos] [npc2.fingers+] в своих [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] издает тихий [npc.moan], пока [npc.she] [npc.verb(help)], погружая [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] выпячивает [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Медленно выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], тихий [npc.moan] срывается с [npc.namePos] [npc.lips+], пока движения [npc.her] вдавливают [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Наслаждаться пальцами в анусе";
		}

		@Override
		public String getActionDescription() {
			return "Наслаждайся [npc2.namePos] [npc2.fingers+] в своих [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] выпячивает [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] погружаться всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.a_moan+] выскальзывает из между [npc.her] [npc.lips+], пока движения [npc.her] вжимают [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубо ласкать соски пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Грубо втиснуть [npc2.namePos] [npc2.fingers+] глубоко внутрь твоей [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно впечатывая [npc.her] [npc.breast(true)] в [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(force)] [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] агрессивно [npc.verb(start)] выпячивает [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] погружаться всё глубже в [npc.her] [npc.nipple+].",

					"Грубо выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из меж [npc.her] [npc.lips+], пока [npc.her] резкие движения вгоняют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Наслаждаться ласками сосков пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Наслаждаться [npc2.namePos] [npc2.fingers+] в [npc.her] [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] под напором [npc.a_moan+], пока [npc.she] [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] выпячивает [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.nipple+].",

					"Выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.a_moan+] выскальзывает из меж [npc.her] [npc.lips+], пока [npc.her] движения вгоняют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "С жадностью ласкать соски пальцами";
		}

		@Override
		public String getActionDescription() {
			return "С жадностью прижимай свои [npc.breast+(true)] к [npc2.namePos] [npc2.hand], пока [npc2.she] перебирает твои [npc.nipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] выпячивает [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] погружаться всё глубже в [npc.her] [npc.nipple+].",

					"Энергично выпячивая [npc.her] [npc.breast(true)] навстречу [npc2.namePos] [npc2.hand], [npc.a_moan+] выскальзывает из между [npc.her] [npc.lips+], пока движения [npc.her] вжимают [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.nipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction NIPPLE_FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться ласкам сосков пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытянуть [npc2.namePos] [npc2.fingers] из своих [npc.nipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.namePos] [npc.eyes], и, больше не в силах сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], чтобы осторожно вытягивать [npc2.namePos], нежно выкачивая [npc2.fingers] из [npc.her] [npc.nipple+].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь убрать [npc.her] [npc.breast(true)] от нежелательных прикосновений [npc2.namePos], отчаянно извиваясь, пока [npc2.namePos] [npc2.fingers+] продолжают мягко скользить туда-сюда в [npc.her] [npc.nipple+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.breast(true)] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают мягко скользить глубоко в [npc.her] [npc.nipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наполнять [npc.namePos] [npc.eyes], и, больше не в силах сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], чтобы жадно вытягивать [npc2.namePos], с силой выкачивая [npc2.fingers] из [npc.her] [npc.nipple+].",


							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь убрать [npc.her] [npc.breast(true)] от нежелательных прикосновений [npc2.namePos], отчаянно извиваясь, пока [npc2.namePos] [npc2.fingers+] продолжают жадно скользить туда-сюда в [npc.her] [npc.nipple+].",


							"Отчаянно пытаясь оттянуть [npc.her] [npc.breast(true)] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают жадно вбиваться глубоко в [npc.her] [npc.nipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)], и, когда в [npc.namePos] [npc.eyes] начинают скапливаться слезы, [npc.a_sob+], больше не в силах их сдерживать, вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc2.namePos] грубо вбивающийся [npc2.fingers] из [npc.her] [npc.nipple+].",


							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь убрать [npc.her] [npc.breast(true)] от нежелательных прикосновений [npc2.namePos], отчаянно борясь, пока [npc2.namePos] [npc2.fingers+] продолжают грубо входить и выходить из [npc.her] [npc.nipple+].",


							"Отчаянно пытаясь оттянуть [npc.her] [npc.breast(true)] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают грубо вбиваться глубоко в [npc.her] [npc.nipple+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Перестать ласкать сосок пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.fingers] из твоего соска и перестать ласкать грудь пальцами.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.fingers] из [npc.her] [npc.nipple+], [npc.name] [npc.verb(growl)] на [npc2.name], пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы прекратить ласки [npc.herHim].",
	
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем резко выдернуть [npc2.her] [npc2.fingers] из [npc.her] [npc.nipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскользнув, [npc2.namePos] [npc2.fingers] покидает [npc.her] [npc.nipple+], [npc.name] [npc.verb(let)] из [npc.a_moan+], прежде чем велеть [npc2.herHim] перестать ласкать [npc.herHim] пальцами.",
	
							"[npc.Name] прижимается к [npc2.name], вдыхая [npc2.her] [npc2.scent], пока [npc.name] [npc.verb(slide)] [npc2.her] [npc2.fingers] из [npc.her] [npc.nipple+]."));
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
							" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim] от стимуляции [npc.her] [npc.nipple+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.her] [npc.nipple+] еще больше [npc2.her] внимания."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
