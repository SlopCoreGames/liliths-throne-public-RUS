package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
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

public class FingerNippleCrotch {
	
	public static final SexAction PINCH_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Сжимать соски-вымя";
			} else {
				return "Сжимать соски в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Сжимать [npc2.namePos] [npc2.crotchNipples+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протягивая руку к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(start)] осторожно сжимает и растирает [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] могла их игнорировать, и с тихим маленьким [npc.moan], [npc.she] [npc.verb(start)] нежно тянет и щиплет [npc2.her] [npc2.crotchNipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] осторожно тянет и щиплет [npc2.her] [npc2.crotchNipples+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протягивая руку к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] тихий [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением сжимает и растирает [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] могла их игнорировать, и с [npc.a_moan+], [npc.she] [npc.verb(start)] с нетерпением тянет и щиплет [npc2.her] [npc2.crotchNipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] с нетерпением тянет и щиплет [npc2.her] [npc2.crotchNipples+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руку к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо ощупывает [npc2.namePos] грудь, а затем переходит к тому, чтобы с силой ущипнуть и сжать [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] мог их игнорировать, и при [npc.a_moan+], [npc.she] [npc.verb(start)] грубо щипает и сжимает [npc2.her] [npc2.crotchNipples+].",

							"Втискивая [npc.her] [npc.fingers] в [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем начать грубо щипать и сжимать [npc2.her] [npc2.crotchNipples+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протягивая руку к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] тихий [npc.a_moan+], пока [npc.she] [npc.verb(start)] с нетерпением сжимает и растирает [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] могла их игнорировать, и с [npc.a_moan+], [npc.she] [npc.verb(start)] с нетерпением тянет и щиплет [npc2.her] [npc2.crotchNipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] с нетерпением тянет и щиплет [npc2.her] [npc2.crotchNipples+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руку к [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] щипать и теребить [npc2.her] [npc2.crotchNipples+].",

							"[npc2.NamePos] [npc2.crotchBoobs+], выставленные напоказ, оказываются слишком соблазнительной целью, чтобы [npc.name] мог их игнорировать, и при [npc.a_moan+], [npc.she] [npc.verb(start)] тянет и щиплет [npc2.her] [npc2.crotchNipples+].",

							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.crotchBoobRows] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] тянет и щиплет [npc2.her] [npc2.crotchNipples+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan] от [npc.namePos] прикосновения, прежде чем мягко подгонять [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchNipples+] [npc.her] всё своё внимание.",
	
								" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] [npc2.her] вперед, умоляя [npc.name] продолжать, пока [npc2.she] [npc2.verb(carry)] издает похотливые звуки.",
	
								" Мягко [npc2.moaning] от [npc.her] прикосновения, [npc2.name] нежно [npc2.verb(encourage)] [npc.name] продолжать стимулировать [npc2.her] [npc2.crotchNipples+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+] от [npc.namePos] прикосновения, прежде чем с нетерпением подгонять [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchNipples+] [npc.her] всё своё внимание.",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] [npc2.her] вперед, умоляя [npc.name] продолжать, пока [npc2.she] [npc2.verb(carry)] издает похотливые звуки.",
	
								" [npc2.Moaning+] от [npc.her] прикосновения, [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.name] продолжать стимулировать [npc2.her] [npc2.crotchNipples+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+] от [npc.namePos] прикосновения, прежде чем грубо прорычать, чтобы [npc.herHim] продолжал уделять [npc2.her] [npc2.crotchNipples+] [npc.her] всё своё внимание.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)] грудь [npc2.her] вперед и твердым тоном, [npc2.she] [npc2.verb(order)] [npc.name] продолжать уделять [npc2.her] [npc2.crotchNipples+] [npc.her] всё своё внимание.",
	
								" Издавая [npc2.a_moan+] от [npc.her] прикосновения, [npc2.name] [npc2.verb(demand)], что [npc.she] продолжает стимулировать [npc2.her] [npc2.crotchNipples+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.a_moan+] от [npc.namePos] прикосновения, прежде чем с нетерпением подгонять [npc.herHim] продолжать уделять [npc2.her] [npc2.crotchNipples+] [npc.her] всё своё внимание.",
	
								" С [npc2.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)], выпятив [npc2.her] грудь и умоляя [npc.herHim] продолжать, пока [npc2.she] [npc2.verb(carry)], издавая похотливые звуки.",
	
								" [npc2.Moaning+] от [npc.her] прикосновения, [npc2.name] с нетерпением [npc2.verb(encourage)] [npc.name] продолжать стимулировать [npc2.her] [npc2.crotchNipples+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из-за [npc2.a_moan+] при [npc.namePos] касании, прежде чем подтолкнуть [npc.herHim] продолжить уделять [npc2.her] [npc2.crotchNipples+] всё свое [npc.her] внимание.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(push)], выпятив [npc2.her] грудь и умоляя [npc.herHim] продолжать, пока [npc2.she] [npc2.verb(carry)], издавая похотливые звуки.",
	
								" [npc2.Moaning+] при [npc.her] касании, [npc2.name] [npc2.verb(encourage)] [npc.name] продолжать стимулировать [npc2.her] [npc2.crotchNipples+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] инстинктивно [npc2.verb(try)] отпрянуть, [npc2.sobbing] и сопротивляться [npc.namePos] касанию, пока [npc2.she] [npc2.verb(try)] оттолкнуть [npc.her] [npc.fingers] от [npc2.her] [npc2.crotchNipples+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] корчится от дискомфорта, умоляя [npc.name] оставить [npc2.herHim] в покое, пока [npc.she] [npc.verb(continue)] стимулирует [npc2.her] [npc2.crotchNipples+].",
	
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+] в ответ на [npc.namePos] касание, и пока [npc.name] продолжает стимулировать [npc2.her] [npc2.crotchNipples+], [npc2.name] [npc2.verb(continue)] сопротивляется [npc.herHim]."));
						break;
					default:
						break;
				}
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(" Пока [npc.name] [npc.verb(start)] щиплет [npc2.namePos] [npc2.crotchNipples], тонкая струйка [npc2.milk] вытекает и стекает по [npc2.her] [npc2.crotchBoobs+].");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(" Пока [npc.name] [npc.verb(start)] щиплет [npc2.namePos] [npc2.crotchNipples], небольшая порция [npc2.milk] выплескивается и стекает по [npc2.her] [npc2.crotchBoobs+].");
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(" Пока [npc.name] [npc.verb(start)] щипает [npc2.namePos] [npc2.crotchNipples], тонкая струйка [npc2.milk] стекает по [npc2.her] [npc2.crotchBoobs+].");
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], вытекая на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], выплескиваясь на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], изливаясь на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" [npc2.NamePos] [npc2.milk] [npc.verb(start)], изливаясь на [npc.namePos] кончики пальцев, пока [npc.she] жадно [npc.verb(milk)] [npc2.her] [npc2.crotchBoobs+].");
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-10);
		}
		
	};
	
	
	public static final SexAction MILK_TARGET = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Доить вымя";
			} else {
				return "Доить лобковые бугорки";
			}
		}

		@Override
		public String getActionDescription() {
			return "Щипать и тянуть за [npc2.namePos] [npc2.crotchNipples+], чтобы выманить [npc2.her] [npc2.milk+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(trace)] [npc.her] [npc.fingers+] вверх и по поверхности [npc2.namePos] [npc2.crotchBoobs+], прежде чем сосредоточиться на [npc2.her] [npc2.crotchNipples+]. Мягко потягивая и сжимая [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] тихий крик восторга, пока [npc.she] [npc.verb(manage)], чтобы вытянуть",

							"С мягким [npc.moan], [npc.name] нежно [npc.verb(press)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.crotchBoobs+], прежде чем спуститься ниже, чтобы дразнить [npc2.her] [npc2.crotchNipples+]. Деликатно щипая и сжимая [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc.she] [npc.verb(manage)], чтобы вытянуть"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] жадно [npc.verb(trace)] [npc.her] [npc.fingers+] вверх и через [npc2.namePos] [npc2.crotchBoobs+], прежде чем быстро сосредоточиться на [npc2.her] [npc2.crotchNipples+]. Алчно щипая и сжимая [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc.she] [npc.verb(manage)], чтобы вытянуть",

							"С [npc.a_moan+], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.fingers] в [npc2.namePos] [npc2.crotchBoobs+], прежде чем быстро спуститься ниже, чтобы дразнить [npc2.her] [npc2.crotchNipples+]. Алчно щипая и сжимая [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc.she] [npc.verb(manage)], чтобы вытянуть"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] впивается [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.crotchBoobs+], прежде чем сменить [npc.her] грубое прикосновение на [npc2.her] [npc2.crotchNipples+]. Сильно щипая и сжимая [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] победный рык, пока [npc.she] [npc.verb(manage)], чтобы вытянуть",

							"С помощью [npc.a_moan+], [npc.name] грубо втыкает [npc.her] [npc.fingers] в [npc2.namePos] [npc2.crotchBoobs+], после чего быстро опускается ниже, чтобы грубо пощекотать [npc2.her] [npc2.crotchNipples+]. Доминантно щипая и сжимая [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(manage)], чтобы выжать"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
				case ZERO_NONE://Shouldn't be able to be reached
					UtilText.nodeContentSB.append(" нисколько [npc2.milk] из [npc2.namePos] [npc2.crotchBoobs+].");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" тонкую струйку [npc2.milk] из [npc2.her] [npc2.crotchBoobs+].",

							" небольшую струйку [npc2.milk] из [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" маленькую струю [npc2.milk] из [npc2.her] [npc2.crotchBoobs+].",

							" тонкую струю [npc2.milk] из [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" брызги [npc2.milk] из [npc2.her] [npc2.crotchBoobs+].",

							" тонкую струйку [npc2.milk] из [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" поток [npc2.milk] из [npc2.her] [npc2.crotchBoobs+].",

							" струю [npc2.milk] из [npc2.her] [npc2.crotchBoobs+]."));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" обильный поток [npc2.milk] из [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" значительное количество [npc2.milk] из [npc2.her] [npc2.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" целое море [npc2.milk] из [npc2.her] [npc2.crotchBoobs+].");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc.name] [npc.verb(start)], чтобы подоить [npc2.herHim], и, с силой выталкивая [npc2.her] [npc2.crotchBoobs] в [npc.her] [npc.hands], [npc2.name] нежно [npc2.verb(encourage)] [npc.name], чтобы продолжить.",
	
								" [npc2.Name] не может не [npc2.moanVerb] от восхитительного чувства, когда ее [npc2.her] [npc2.crotchBoobs] доят, и, прикусывая [npc2.her] [npc2.lip], [npc2.she] слегка [npc2.verb(plead)] с [npc.name], чтобы продолжить."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] восторженный [npc2.moan], пока [npc.name] [npc.verb(start)], чтобы подоить [npc2.herHim], и, лихорадочно выталкивая [npc2.her] [npc2.crotchBoobs] в [npc.her] [npc.hands], [npc2.name] счастливо [npc2.verb(encourage)] [npc.name], чтобы продолжить.",
	
								" [npc2.Name] не может не [npc2.moanVerb] от восхитительного чувства, когда ее [npc2.her] [npc2.crotchBoobs] доят, и, прикусывая [npc2.her] [npc2.lip], [npc2.she] жадно [npc2.verb(plead)] с [npc.name], чтобы продолжить."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] глубоко удовлетворенный рык, пока [npc.name] [npc.verb(start)], чтобы подоить [npc2.herHim], и, выталкивая [npc2.her] [npc2.crotchBoobs] в [npc.her] [npc.hands], [npc2.name] грубо [npc2.verb(order)] [npc.name], чтобы продолжить.",
	
								" [npc2.Name] не может не [npc2.moanVerb] от восхитительного чувства, когда [npc2.her] [npc2.crotchBoobs] доят, и, прикусывая [npc2.her] [npc2.lip], [npc2.she] грубо [npc2.verb(order)] [npc.name], чтобы продолжить."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)], чтобы доить [npc2.herHim], и, пытаясь оттянуть [npc2.her] [npc2.crotchBoobs] от [npc.her] [npc.hands], [npc2.name] отчаянно [npc2.verb(plead)] с [npc.name], чтобы остановиться.",
	
								" Отчаянно пытаясь оттянуть [npc2.her] [npc2.crotchBoobs+] от нежелательного доения, [npc2.name] [npc2.verb(plead)] с [npc.name], чтобы оставить [npc2.herHim] в покое."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawMilkStorageValue()/5);
		}
		
	};
	
	public static final SexAction GET_MILKED = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Дать подоить вымя";
			} else {
				return "Дать подоить лобковую грудь";
			}
		}

		@Override
		public String getActionDescription() {
			return "Направь [npc2.namePos] [npc2.hands] к своим [npc.crotchBoobs+] и заставь [npc2.herHim] подоить тебя.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getBreastRawStoredMilkValue()>0
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!= SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(encourage)] [npc2.herHim], чтобы провести [npc2.her] [npc2.fingers+] вверх и через [npc.her] [npc.crotchBoobs+], а затем мягко направляя [npc2.herHim] вниз к [npc.her] [npc.crotchNipples+]. Приказывая [npc2.herHim] дергать и сжимать [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] тихий крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы выжать",

							"С мягким [npc.moan], [npc.name] нежно [npc.verb(guide)] [npc2.namePos] [npc2.fingers] в [npc.her] [npc.crotchBoobs+], прежде чем переместить [npc2.her] прикосновение ниже, чтобы дразнить [npc.her] [npc.crotchNipples+]. Приказывая [npc2.herHim] щипать и сжимать [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы вытянуть"));
					break;
				case SUB_NORMAL: case DOM_NORMAL: case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] направляет [npc2.her] [npc2.fingers+] вверх и через [npc.her] [npc.crotchBoobs+], прежде чем жадно вести [npc2.herHim] вниз к [npc.her] [npc.crotchNipples+]. Веля [npc2.herHim] тянуть и сжимать [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.namePos] [npc2.verb(manage)], чтобы вытянуть",

							"С [npc.a_moan+], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] в [npc.her] [npc.crotchBoobs+], прежде чем переместить [npc2.her] прикосновение ниже, чтобы дразнить [npc.her] [npc.crotchNipples+]. Приказывая [npc2.herHim] щипать и сжимать [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы вытянуть"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] грубо [npc.verb(force)] [npc2.her] [npc2.fingers+] вверх и через [npc.her] [npc.crotchBoobs+], а затем властно направляя [npc2.her] касание к [npc.her] [npc.crotchNipples+]. Приказывая [npc2.herHim] дергать и сжимать [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.namePos] [npc2.verb(manage)], чтобы выжать",

							"Схватив [npc.a_moan+], [npc.name] с силой [npc.verb(guide)] [npc2.namePos] [npc2.fingers] в [npc.her] [npc.crotchBoobs+], после чего перемещая [npc2.her] касание, чтобы дразнить [npc.her] [npc.crotchNipples+]. Приказывая [npc2.herHim] щипать и сжимать [npc.her] [npc.crotchNipples+], [npc.name] [npc.verb(let)] восторженный крик, пока [npc2.namePos] [npc2.verb(manage)], чтобы выжать"));
					break;
				case SUB_RESISTING:
					break;
			}
			
			switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(" никаких [npc.milk] из [npc.namePos] [npc.crotchBoobs+]."); //Shouldn't be able to be reached
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" тонкая струйка [npc.milk] из [npc.her] [npc.crotchBoobs+].",

							" небольшая струйка [npc.milk] из [npc.her] [npc.crotchBoobs+]."));
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" маленький брызг [npc.milk] из [npc.her] [npc.crotchBoobs+].",

							" небольшая струя [npc.milk] из [npc.her] [npc.crotchBoobs+]."));
					break;
				case THREE_DECENT_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" брызг [npc.milk] из [npc.her] [npc.crotchBoobs+].",

							" струйка [npc.milk] из [npc.her] [npc.crotchBoobs+]."));
					break;
				case FOUR_LARGE_AMOUNT:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" поток [npc.milk] из [npc.her] [npc.crotchBoobs+].",

							" тонкая струйка [npc.milk] из [npc.her] [npc.crotchBoobs+]."));
					break;
				case FIVE_VERY_LARGE_DROOLING:
					UtilText.nodeContentSB.append(" густая струя [npc.milk] из [npc.her] [npc.crotchBoobs+].");
					break;
				case SIX_EXTREME_AMOUNT_DRIPPING:
					UtilText.nodeContentSB.append(" приличное количество [npc.milk] из [npc.her] [npc.crotchBoobs+].");
					break;
				case SEVEN_MONSTROUS_AMOUNT_POURING:
					UtilText.nodeContentSB.append(" огромное количество [npc.milk] из [npc.her] [npc.crotchBoobs+].");
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] с тихим [npc2.moan], пока [npc2.she] [npc2.verb(start)], чтобы подоить [npc.name], и когда [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs] вытекает в [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)] нежно вытягивая [npc.milk+] из [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] не может не издать [npc2.a_moan+] от восхитительного ощущения, когда [npc.crotchBoobs] доят [npc.namePos], и, воодушевленный похожими звуками, которые издает [npc.sheIs], [npc2.she] [npc2.verb(continue)] нежно дразнит [npc.milk+] из [npc.namePos] [npc.crotchNipples]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] с [npc2.a_moan+], пока [npc2.she] [npc2.verb(start)], чтобы подоить [npc.name], и когда [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs] вытекает в [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)] жадно вытягивая [npc.milk+] из [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] не может сдержать [npc2.a_moan+] от восхитительного ощущения, когда [npc.namePos] [npc.crotchBoobs] доится, а, воодушевленная похожими звуками, которые издает [npc.sheIs], [npc2.she] [npc2.verb(continue)] жадно выжимает [npc.milk+] из [npc.namePos] [npc.crotchNipples]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] рычание, пока [npc2.she] [npc2.verb(start)], чтобы доить [npc.name], и когда [npc.name] [npc.verb(push)] [npc.her] [npc.crotchBoobs] в [npc2.her] [npc2.hands], [npc2.name] [npc2.verb(continue)] грубо вытягивает [npc.milk+] из [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] не может сдержать [npc2.a_moan+] от ощущения, когда доится [npc.namePos] [npc.crotchBoobs], и, воодушевленная похожими звуками, которые издает [npc.sheIs], [npc2.she] [npc2.verb(continue)] грубо выжимает [npc.milk+] из [npc.namePos] [npc.crotchNipples]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] и [npc2.verb(try)], чтобы отстраниться, но резким рывком [npc.name] [npc.verb(pull)] [npc2.her] [npc2.hands] обратно в [npc.her] [npc.crotchBoobs], ясно давая понять, что у [npc2.sheHasFull] нет другого выбора, кроме как выжимать [npc.milk+] из [npc.her] [npc.crotchNipples].",
	
								" [npc2.Name] отчаянно пытается вырваться из рук [npc.namePos] [npc.crotchBoobs], но, вцепившись в [npc2.her] [npc2.hand], [npc.name] [npc.verb(force)] [npc2.herHim], чтобы продолжить выжимать [npc.milk+] из [npc.her] [npc.crotchNipples]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-Main.sex.getCharacterPerformingAction().getBreastCrotchRawMilkStorageValue()/5);
		}
	};
	
	
	
	public static final SexAction NIPPLE_FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Ласкать соски на вымени";
			} else {
				return "Ласкать соски в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Погрузите свой [npc.fingers] в одну из похотливых [npc2.crotchNipples] [npc2.namePos] и [npc2.verb(start)] перебирайте пальцами [npc2.her] груди.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.crotchBoobs+], [npc2.name] [npc2.verb(let)] прерывисто вздыхает, пока [npc.name] описывает круги вокруг одного из [npc2.her] [npc2.crotchNipples+], прежде чем медленно втиснуть [npc.her] пальцев в манящее отверстие [npc2.her].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] о одну из [npc2.namePos] [npc2.crotchNipples+], и медленным, уверенным давлением [npc.she] осторожно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.crotchBoobs+], [npc2.name] [npc2.verb(let)] прерывисто вздыхает, пока [npc.name] описывает круги вокруг одного из [npc2.her] [npc2.crotchNipples+], прежде чем жадно втиснуть [npc.her] пальцев в манящее отверстие [npc2.her].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] о одну из [npc2.namePos] [npc2.crotchNipples+], и уверенным давлением [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Ощупывая и сжимая [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)], чтобы обвести [npc.her] [npc.fingers] вокруг одного из [npc2.her] [npc2.crotchNipples+], а затем грубо втискивая [npc.her] пальцев в манящее отверстие [npc2.namePos].",

							"Алчно прижимая [npc.her] [npc.fingers] к одному из [npc2.namePos] [npc2.crotchNipples+], [npc.name] [npc.verb(let)] тихий рык, когда [npc.she] грубо [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] по поверхности [npc2.namePos] [npc2.crotchBoobs+], [npc2.name] [npc2.verb(let)] вздох, пока [npc.name] жадно обводит один из [npc2.her] [npc2.crotchNipples+], прежде чем отчаянно втиснуть [npc.her] пальцев в манящее отверстие [npc2.her].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] к одному из [npc2.namePos] [npc2.crotchNipples+], и с нарастающим давлением [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers] над [npc2.namePos] [npc2.crotchBoobs+], [npc2.name] [npc2.verb(let)] прерывисто вздыхает, пока [npc.name] описывает круги вокруг одного из [npc2.her] [npc2.crotchNipples+], прежде чем жадно втиснуть [npc.her] пальцев в манящее отверстие [npc2.her].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers] о одну из [npc2.namePos] [npc2.crotchNipples+], и уверенным давлением [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в плоть [npc2.namePos] груди."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc.name] [npc.verb(start)] пальцами [npc2.namePos] [npc2.crotchBoobs], мягко выпячивая [npc2.her] грудь, когда [npc2.she] [npc2.verb(help)] [npc.name] погружают [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.crotchNipple+].",
	
								" С мягким [npc2.moan], [npc2.name] медленно [npc2.verb(push)] [npc2.her] грудь вперед, умоляя [npc.name] погрузить [npc.her] [npc.fingers+] еще глубже в [npc2.her] грудь."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, пока [npc2.a_moan+] как [npc.name] [npc.verb(start)] пальцами ласкает [npc2.namePos] [npc2.crotchBoob], с нетерпением выпячивая [npc2.her] грудь, пока [npc2.she] [npc2.verb(help)] [npc.name] погружаются [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.crotchNipple+].",
	
								" С [npc.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] выпячивает [npc2.her] грудь, умоляя [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+] как [npc.name] [npc.verb(start)] перебирая [npc2.namePos] [npc2.crotchBoobs], яростно выпячивая [npc2.her] грудь навстречу [npc.her] касаниям, пока [npc2.she] [npc2.verb(command)] [npc.name], чтобы погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] отвечает яростным выпячиванием [npc2.her] груди, приказывая [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+] как [npc.name], [npc.verb(start)] лаская [npc2.namePos] [npc2.crotchBoobs], с нетерпением выпячивая [npc2.her] грудь, пока [npc2.name] [npc2.verb(help)] [npc.name] погружаются [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.crotchNipple+].",
	
								" С [npc.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] выпячивает [npc2.her] грудь, умоляя [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+] как [npc.name] [npc.verb(start)] пальцами лаская [npc2.namePos] [npc2.crotchBoob], жадно выпячивая [npc2.her] грудь, пока [npc2.name] [npc2.verb(help)] [npc.name] погружаются [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.crotchNipple+].",
	
								" С [npc.a_moan+], [npc2.name] с нетерпением [npc2.verb(push)] выпячивает [npc2.her] грудь, умоляя [npc.name] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] грудь."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)], чтобы отпрянуть, [npc2.sobbing] и корчась от дискомфорта, пока [npc.name] [npc.verb(start)] чтобы качать [npc.her] [npc.fingers] туда-сюда в [npc2.her] [npc2.crotchNipple+].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно пытаясь отстраниться от нежеланного проникновения, протестуя и борясь с прикосновениями [npc.namePos], пока [npc.she] [npc.verb(pump)] [npc.her] [npc.fingers+] туда-сюда в [npc2.her] [npc2.crotchNipple+]."));
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
							" [npc2.Name] [npc2.verb(push)] [npc2.her] грудью в ответ, издавая восторженный [npc2.moan], пока [npc2.she] [npc2.verb(start)] с энтузиазмом умоляя [npc.name] продолжать ласкать пальцами [npc2.her] [npc2.crotchBoobs].",
		
							" Восторженный [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(start)], жадно выпячивая [npc2.her] грудь навстречу прикосновениям [npc.namePos], пока [npc2.she] [npc2.verb(beg)] [npc.herHim] продолжать пальцевую стимуляцию [npc2.her] [npc2.crotchNipples+].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(push)] [npc2.her] грудь, жадно умоляя [npc.name] продолжать ласкать пальцами [npc2.her] [npc2.crotchBoobs], пока движения [npc2.her] заставляют [npc.name] погружаться [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.crotchNipple+]."));
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Не в силах отстранить [npc2.her] грудь от прикосновений [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name] от [npc2.herHim].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], прежде чем [npc2.she] [npc2.verb(start)] слабо пытается оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)] нежно ласкает пальцами [npc2.her] [npc2.crotchNipple+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] [npc2.verb(try)], тщетно пытаясь отпрянуть [npc2.her] грудью от [npc.namePos] прикосновений, борясь с [npc.herHim], пока [npc.her] [npc.fingers] [npc.verb(continue)] плавно скользит глубоко в [npc2.her] [npc2.crotchNipple+]."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] выпячивает [npc2.her] грудь в ответ, издавая восторженный [npc2.moan], прежде чем начать с энтузиазмом умолять [npc.name] продолжать ласкать пальцами [npc2.her] [npc2.crotchBoobs].",
	
							" Восторженный [npc2.moan] вырывается из глубин [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(start)] мягко подает [npc2.her] грудь навстречу [npc.namePos] прикосновениям, пока [npc2.she] [npc2.verb(beg)] [npc.herHim], чтобы продолжать пальцевать [npc2.her] [npc2.crotchNipples+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(push)] выставляет вперед [npc2.her] грудь, умоляя [npc.name] продолжать ласкать пальцами [npc2.her] [npc2.crotchBoobs], пока движения [npc2.her] заставляют [npc.name] погружаться [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.crotchNipple+]."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] выпячивает [npc2.her] грудь в ответ, издавая восторженный [npc2.moan], пока [npc2.she] [npc2.verb(start)] приказывает [npc.name] продолжать пальцевать [npc2.her] [npc2.crotchBoobs].",
	
							" Восторженный [npc2.moan] вырывается из-под [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(start)], грубо выпячивая [npc2.her] грудь навстречу [npc.namePos], пока [npc2.she] [npc2.verb(order)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.crotchNipples+].",
	
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(thrust)], выпятив [npc2.her] грудь, приказывает [npc.name] продолжать ласкать [npc2.her] [npc2.crotchBoobs], пока внезапное движение [npc2.her] заставляет [npc.name] погрузить [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.crotchNipple+]."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)], выпячивая [npc2.her] грудь в ответ, издает [npc2.a_moan], пока [npc2.she] [npc2.verb(implore)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.crotchBoobs].",
		
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(push)] выпячивает [npc2.her] грудь навстречу [npc.namePos], пока [npc2.she] [npc2.verb(implore)] [npc.herHim], чтобы продолжать ласкать [npc2.her] [npc2.crotchNipples+].",
		
							" [npc2.Moaning+], [npc2.name] [npc2.verb(push)], выпятив [npc2.her] грудь, умоляет [npc.name] продолжать ласкать [npc2.her] [npc2.crotchBoobs], пока движения [npc2.her] заставляют [npc.name] погрузить [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.crotchNipple+]."));
			}
		}
		return "";
	}
	
	public static final SexAction NIPPLE_FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Нежные ласки сосков вымени";
			} else {
				return "Нежные ласки сосков в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Нежно ласкать [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] медленно [npc.verb(start)], чтобы проскальзывать [npc.her] пальцами туда-сюда в [npc2.her] [npc2.crotchBoob].",

					"[npc.Name] мягко [npc.verb(lean)] в [npc2.name], заставляя [npc2.herHim] судорожно вдыхать [npc.her] [npc.scent+], пока [npc.she] плавно [npc.verb(pump)] [npc.her] [npc.fingers+] туда-сюда в [npc2.her] [npc2.crotchNipple+].",

					"Мягко прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] нежно [npc.verb(pump)] [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Ласкать соски на вымени";
			} else {
				return "Ласкать соски в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Продолжить пальцевать [npc2.namePos] [npc2.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] жадно [npc.verb(start)], пальцуя [npc2.her] [npc2.crotchBoobs] и издавая [npc.a_moan+], пока [npc.she] [npc.verb(press)] [npc.herself] о [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] в [npc2.herHim], [npc.her] [npc.scent+] переполняет чувства [npc2.namePos], пока [npc.she] жадно [npc.verb(start)] [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+].",

					"Прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(start)] [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Грубые ласки сосков пальцами";
			} else {
				return "Грубые ласки паха пальцами";
			}
		}

		@Override
		public String getActionDescription() {
			return "Грубо перебирать пальцами [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Алчно вонзая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] грубо вбивая [npc.her] пальцы туда-сюда, стремительно лаская [npc2.namePos] [npc2.crotchBoob], пока [npc.she] вжимает [npc.herself] в [npc2.herHim].",

					"[npc.Name] вжимает [npc.herself] в [npc2.name], заставляя [npc2.herHim] судорожно вдыхать [npc.her] [npc.scent+], пока [npc.she] [npc.verb(start)] грубо вбивает [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+].",

					"Вжимая [npc.herself] в [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] грубо вбивает [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Ласкать соски на вымени";
			} else {
				return "Ласкать соски в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Продолжить ласкать пальцами [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] скользит [npc.her] пальцами туда-сюда, лаская [npc2.namePos] [npc2.crotchBoob], пока [npc.she] [npc.verb(press)] [npc.herself] прижимается к [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] в [npc2.name], заставляя [npc2.herHim] судорожно вдыхать [npc.her] [npc.scent+], пока [npc.she] [npc.verb(start)] качает [npc.her] [npc.fingers+] туда-сюда в [npc2.her] [npc2.crotchNipple+].",

					"Прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_NIPPLE_FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Жадно ласкать соски груди пальцами";
			} else {
				return "Жадно ласкать соски промежности пальцами";
			}
		}

		@Override
		public String getActionDescription() {
			return "Жадно ласкать пальцами [npc2.namePos] [npc2.crotchNipple].";
		}
		
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(start)] проталкивает [npc.her] пальцы туда-сюда, жадно лаская [npc2.her] [npc2.crotchBoob], пока [npc.she] [npc.verb(press)] [npc.herself] прижимается к [npc2.herHim].",

					"[npc.Name] [npc.verb(lean)] в [npc2.name], заставляя [npc2.herHim] вдыхать [npc.her] [npc.scent+], пока [npc.she] с нетерпением [npc.verb(start)], энергично двигая [npc.her] [npc.fingers+] туда-сюда в [npc2.her] [npc2.crotchNipple+].",

					"Прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] жадно [npc.verb(start)] [npc.her] [npc.fingers+] туда-сюда в [npc2.namePos] [npc2.crotchNipple+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Сопротивляться ласкам сосков груди";
			} else {
				return "Сопротивляться ласкам сосков промежности";
			}
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.fingers] из [npc2.namePos] [npc2.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)], в [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.crotchNipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] неистово [npc.verb(try)], чтобы [npc.verb(pull)] [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.crotchNipple+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] в позиции, прежде чем нежно выпятить [npc2.her] грудь и с силой вогнать [npc.namePos] [npc.fingers] глубоко в [npc2.her] [npc2.crotchBoob]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)], в [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.crotchNipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] неистово [npc.verb(try)], чтобы [npc.verb(pull)] [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.crotchNipple+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] в позиции, прежде чем жадно выпятить [npc2.her] грудь и с силой вогнать [npc.namePos] [npc.fingers] глубоко в [npc2.her] [npc2.crotchBoob]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)], в [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.crotchNipple+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] неистово [npc.verb(try)], чтобы [npc.verb(pull)] [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.crotchNipple+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] в позиции, прежде чем грубо выпятить [npc2.her] грудь и с силой вогнать [npc.namePos] [npc.fingers] глубоко в [npc2.her] [npc2.crotchBoob]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Прекратить ласкать соски вымени пальцами";
			} else {
				return "Прекратить ласкать соски в паху пальцами";
			}
		}

		@Override
		public String getActionDescription() {
			return "Вытащить свой [npc.fingers] из [npc2.namePos] [npc2.crotchNipple] и перестать ласкать [npc2.her] [npc2.crotchBoob] пальцами.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc.her] [npc.fingers] из [npc2.namePos] [npc2.crotchNipple+], [npc.name] [npc.verb(give)] [npc2.namePos] [npc2.crotchBoob] напоследок, пока [npc.she] перестает ласкать пальцами [npc2.her] грудь.",

							"[npc.Name] резко [npc.verb(inhale)], вдыхая [npc2.namePos] [npc2.scent+], прежде чем грубо выдернуть [npc.her] [npc.fingers] из [npc2.her] [npc2.crotchNipple]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.fingers] из [npc2.namePos] [npc2.crotchNipple], [npc.name] [npc.verb(give)] [npc2.namePos] [npc2.crotchBoob] напоследок, пока [npc.she] перестает ласкать пальцами [npc2.her] грудь.",

							"[npc.Name] резко [npc.verb(inhale)], вдыхая [npc2.namePos] [npc2.scent+], прежде чем плавно выскользнуть [npc.her] [npc.fingers] из [npc2.her] [npc2.crotchNipple]."));
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
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] перестает стимулировать [npc2.her] [npc2.crotchBoobs+].",

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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Получать ласки пальцами в соски вымени";
			} else {
				return "Позволить ласкать соски на лобке";
			}
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать ласкать твои [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко, но уверенно сжав [npc2.namePos] [npc2.hand], [npc.name] медленно [npc.verb(guide)] [npc2.her] [npc2.fingers] вверх и через [npc.her] [npc.crotchBoobs], издавая легкий [npc.moan], прежде чем втиснуть [npc2.her] пальцев в [npc.her] [npc.crotchNipple+].",
							
							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] до [npc.her] [npc.crotchBoobs], и с медленным, ровным нажимом, [npc.she] нежно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.crotchBoobs], издавая [npc.a_moan+], прежде чем жадно втиснуть [npc2.her] пальцев в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] до [npc.her] [npc.crotchBoobs], и с решительным напором, [npc.she] жадно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мертвой хваткой вцепившись в [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.crotchBoobs], издавая [npc.a_moan+], прежде чем грубо впихнуть [npc2.her] пальцев в [npc.her] [npc.crotchNipple+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] властно [npc.verb(push)] [npc2.namePos] [npc2.fingers] до [npc.her] [npc.crotchBoobs], и доминирующим, дерганым движением, [npc.she] грубо [npc.verb(stuff)] [npc2.her] пальцев в [npc.her] [npc.crotchNipple+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.crotchBoobs], издавая [npc.a_moan+], прежде чем жадно втиснуть [npc2.her] пальцев в [npc.her] [npc.crotchNipple+].",

							"Ухватив [npc2.namePos] [npc2.hand], [npc.name] жадно [npc.verb(guide)] [npc2.namePos] [npc2.fingers] до [npc.her] [npc.crotchBoobs], и с решительным напором, [npc.she] алчно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.crotchNipple+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вверх и через [npc.her] [npc.crotchBoobs], издавая [npc.a_moan+], прежде чем втиснуть [npc2.her] пальцев в [npc.her] [npc.crotchNipple+].",

							"Ухватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] до [npc.her] [npc.crotchBoobs], и с решительным напором, [npc.she] [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], подгибая [npc2.her] [npc2.fingers], прежде чем мягко начать ласкать [npc.her] [npc.crotchNipple+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], осторожно вдавливая [npc2.her] [npc2.hand] в нежную плоть [npc.her] [npc.crotchBoob], пока [npc2.she] [npc2.verb(set)], лаская [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], подгибая [npc2.her] [npc2.fingers], прежде чем с нетерпением приняться пальцами ласкать [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно вдавливая [npc2.her] [npc2.hand] в мягкую плоть [npc.her] [npc.crotchBoob], пока [npc2.she] [npc2.verb(set)], исследуя пальцами [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо [npc2.verb(curl)] [npc2.her] [npc2.fingers], прежде чем начать безжалостно трахать [npc.her] [npc.crotchNipple+] пальцами.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], стремясь напомнить [npc.herHim], кто здесь главный, пока [npc2.she] [npc2.verb(start)] грубо ласкает [npc.her] [npc.crotchNipple+] пальцами."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] наружу, [npc2.a_moan+], пока [npc2.she] [npc2.verb(enter)] [npc.herHim], подгибая [npc2.her] [npc2.fingers], прежде чем с нетерпением приняться пальцами ласкать [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно вдавливая [npc2.her] [npc2.hand] в мягкую плоть [npc.her] [npc.crotchBoob], пока [npc2.she] [npc2.verb(set)], исследуя пальцами [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.herHim], подгибая [npc2.her] [npc2.fingers] перед тем, как начать ласкать пальцами [npc.her] [npc.crotchNipple+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], вжимая [npc2.her] [npc2.hand] в мягкую плоть [npc.her] [npc.crotchBoob], пока [npc2.she] [npc2.verb(set)] ласкать пальцами [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.her] [npc2.fingers] внутри [npc.herHim], пытаясь вырваться из [npc.namePos] крепкой хватки на [npc2.her] [npc2.hand], пока [npc2.she] пытается вытащить [npc2.herself].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] отчаянно борется с [npc.namePos] тугой хваткой на [npc2.her] [npc2.hand], умоляя [npc.name] остановиться, пока [npc.she] [npc2.verb(force)] [npc2.her] [npc2.fingers] глубоко в [npc.her] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Мягкие ласки сосков и паха пальцами";
			} else {
				return "Мягкие ласки сосков и промежности пальцами";
			}
		}

		@Override
		public String getActionDescription() {
			return "Мягко наслаждайся [npc2.namePos] [npc2.fingers+] в своих [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко надавливая [npc.her] [npc.crotchBoob] на [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С мягким [npc.moan], [npc.name] нежно [npc.verb(start)] вгоняет [npc.her] [npc.crotchBoob] в [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Медленно выталкивая [npc.her] [npc.crotchBoob] против [npc2.namePos] [npc2.hand], мягкий [npc.moan] соскальзывает между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Насладиться ласками сосков на вымени";
			} else {
				return "Насладиться ласками сосков в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Насладиться [npc2.namePos] [npc2.fingers+] в твоих [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью выталкивая [npc.her] [npc.crotchBoob] против [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] вгоняет [npc.her] [npc.crotchBoob] против [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энтузиазмом выталкивая [npc.her] [npc.crotchBoob] против [npc2.namePos] [npc2.hand], [npc.a_moan+] соскальзывает между [npc.her] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Грубые ласки сосков на вымени";
			} else {
				return "Грубые ласки сосков в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Грубо вгонять [npc2.namePos] [npc2.fingers+] глубоко внутрь твоей [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] [npc.crotchBoob] о [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(force)] [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(start)] агрессивно толкая [npc.her] [npc.crotchBoob] о [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Грубо толкая [npc.her] [npc.crotchBoob] о [npc2.namePos] [npc2.hand], так что [npc.a_moan+] вырывается из между [npc.her] [npc.lips+], пока мощные движения [npc.her] вгоняют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Насладиться ласками сосков на вымени";
			} else {
				return "Насладиться ласками сосков в паху";
			}
		}

		@Override
		public String getActionDescription() {
			return "Наслаждайся [npc2.namePos] [npc2.fingers+] в [npc.her] [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Прижимая [npc.her] [npc.crotchBoob] к [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С помощью [npc.a_moan+], [npc.name] [npc.verb(start)] толкая [npc.her] [npc.crotchBoob] о [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"[npc.her] [npc.crotchBoob] толчками упираются в [npc2.namePos] [npc2.hand], а [npc.a_moan+] вырывается из меж[npc.her] [npc.lips+], пока [npc.her] движения заставляют [npc2.namePos] [npc2.fingers+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction NIPPLE_FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Жадно ласкать соски пальцами, прижимая их к вымени";
			} else {
				return "Жадно ласкать соски пальцами, прижимая их к промежности";
			}
		}

		@Override
		public String getActionDescription() {
			return "Попытаться с энтузиазмом прижать свой [npc.breast+(true)] к [npc2.namePos] [npc2.hand], пока [npc2.she] ласкает твои [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью выталкивая [npc.her] [npc.crotchBoob] против [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(help)] погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.crotchNipple+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] вгоняет [npc.her] [npc.crotchBoob] против [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.crotchNipple+].",

					"Энтузиазмом выталкивая [npc.her] [npc.crotchBoob] против [npc2.namePos] [npc2.hand], [npc.a_moan+] соскальзывает между [npc.her] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко входить в [npc.her] [npc.crotchNipple+]."));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction NIPPLE_FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Сопротивляться ласкам сосков пальцами, как вымени";
			} else {
				return "Сопротивляться ласкам сосков пальцами, как в промежности";
			}
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить [npc2.namePos] [npc2.fingers] из своих [npc.crotchNipple+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"В [npc.namePos] [npc.eyes] начинают подступать [npc.Name] [npc.verb(feel)], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.her], пока [npc.she] слабо [npc.verb(try)], чтобы мягко вытягивать [npc2.namePos] [npc2.fingers] из [npc.her] [npc.crotchNipple+].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], чтобы [npc.verb(pull)] [npc.her] [npc.crotchBoob] подальше от [npc2.namePos] нежелательных прикосновений, в отчаянной попытке спастись, пока [npc2.namePos] [npc2.fingers+] продолжают мягко скользить туда-сюда в [npc.her] [npc.crotchNipple+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoob] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают мягко скользить глубоко в [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают подступать к [npc.namePos] [npc.eyes], и, больше не в силах сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)], чтобы жадно вытянуть [npc2.namePos], жадно вонзающийся [npc2.fingers] из [npc.her] [npc.crotchNipple+].",


							"[npc.A_sob+] вырывается из [npc.namePos] рта, пока [npc.she] лихорадочно [npc.verb(try)], чтобы [npc.verb(pull)] [npc.her] [npc.crotchBoob] от нежелательного прикосновения [npc2.namePos], отчаянно извиваясь, пока [npc2.namePos] [npc2.fingers+] продолжают с энтузиазмом входить и выходить из [npc.her] [npc.crotchNipple+].",


							"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoob] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают с энтузиазмом глубоко входить в [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают подступать к [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc2.namePos] грубо вбивающийся [npc2.fingers] из [npc.her] [npc.crotchNipple+].",


							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь [npc.verb(pull)] [npc.her] [npc.crotchBoob] от нежелательных прикосновений [npc2.namePos] и тщетно борясь, пока [npc2.namePos] [npc2.fingers+] продолжают грубо входить и выходить из [npc.her] [npc.crotchNipple+].",


							"Отчаянно пытаясь оттянуть [npc.her] [npc.crotchBoob] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] продолжают грубо вбиваться глубоко в [npc.her] [npc.crotchNipple+]."));
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
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchShape()== BreastShape.UDDERS) {
				return "Прекратить ласки сосков пальцами у вымени";
			} else {
				return "Прекратить ласки сосков пальцами в промежности";
			}
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.fingers] из твоих [npc.crotchNipple] и перестать теребить твои [npc.crotchBoobs].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.fingers] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(growl)] на [npc2.name], пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы прекратить ласки [npc.herHim].",
	
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем резко выдернуть [npc2.her] [npc2.fingers] из [npc.her] [npc.crotchNipple+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно вытягивая [npc2.namePos] [npc2.fingers] из [npc.her] [npc.crotchNipple+], [npc.name] [npc.verb(let)] из [npc.a_moan+], прежде чем велеть [npc2.herHim] перестать теребить [npc.herHim].",
	
							"[npc.Name] наклоняется к [npc2.name], вдыхая [npc2.her] [npc2.scent], пока [npc.name] [npc.verb(slide)] [npc2.her] [npc2.fingers] из [npc.her] [npc.crotchNipple+]."));
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
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim] от стимуляции [npc.her] [npc.crotchNipple+].",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.her] [npc.crotchNipple+] больше [npc2.her] внимания."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
