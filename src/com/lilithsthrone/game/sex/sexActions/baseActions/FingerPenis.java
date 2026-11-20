package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
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
public class FingerPenis {
	
	private static boolean isCharacterPrecumming(GameCharacter character) {
		return character.getArousal() >= character.getPenisCumStorage().getArousalNeededToStartPreCumming();
	}
	
	//TODO grope cock
	
	public static final SexAction FONDLE_BALLS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "Ласкать яички";
		}
		@Override
		public String getActionDescription() {
			return "Ласкать и играть с [npc2.namePos] [npc2.balls+].";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(prop)] [npc.herself] на одной [npc.arm], прежде чем использовать свободную [npc.her] [npc.hand], чтобы поглаживать и сжимать [npc2.namePos] [npc2.balls+].",
						"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prop)] [npc.herself] на одной [npc.arm], прежде чем потянуться вниз и начать ласкать и играть с [npc2.namePos] [npc2.balls+].",
						"Приподняв [npc.herself] на одной [npc.arm], [npc.name] [npc.verb(run)] [npc.her] [npc.fingers+] над [npc2.namePos] пахом, ухмыляясь, пока [npc.she] [npc.verb(start)] ласкать и обхватывать ладонью [npc2.namePos] [npc2.balls+].",
						"Приподняв [npc.herself] на одной [npc.arm], [npc.name] использует свободную [npc.her] [npc.hand], чтобы обхватить и ласкать [npc2.namePos] [npc2.balls+], при этом [npc.moaning] счастливо [npc.herself], пока [npc2.namePos] [npc2.cock+] дергается в ответ.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs] одной [npc.hand] и [npc.verb(start)] ласкать и сжимать [npc2.her] [npc2.balls+].",
						"[npc2.Name] [npc2.verb(find)] [npc2.herself], издавая [npc2.a_moan+], пока [npc.name] [npc.verb(reach)] по ней одной [npc.hand], чтобы начать ласкать и играть с [npc2.her] [npc2.balls+].",
						"Проводя [npc.her] [npc.fingers+] по [npc2.namePos] [npc2.balls+], [npc.name] [npc.verb(start)] ласкать и обхватывать их, издавая [npc.a_moan+], пока [npc2.namePos] [npc2.cock+] дергается в ответ.");
			}
		}
	};
	

	public static final SexAction COCK_MASTURBATING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		@Override
		public String getActionTitle() {
			return "Начать мастурбацию рукой";
		}
		@Override
		public String getActionDescription() {
			return "Просунуть руку вниз и начать поглаживать [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers] вокруг [npc2.her] [npc2.cock+], она издает тихий [npc.moan], пока [npc.she] [npc.verb(start)] медленно скользит вверх и вниз по всей длине.",

							"[npc.Name] [npc.verb(drop)] одним из [npc.her] [npc.hands] между [npc2.namePos] [npc2.legs], и, обхватив [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] медленно сжимает и натягивает [npc2.herHim].",

							"Лаская [npc.her] [npc.fingers] над [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(start)] нежно поглаживает пульсирующую длину."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] с нетерпением [npc.verb(wrap)] [npc.her] [npc.fingers] вокруг [npc2.her] [npc2.cock+], она издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] быстро скользит вверх и вниз по всей длине.",

							"[npc.Name] [npc.verb(drop)] одним из [npc.her] [npc.hands] между [npc2.namePos] [npc2.legs], и, обхватив [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] жадно натягивает [npc2.herHim].",

							"Лаская [npc.her] [npc.fingers] над [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.a_moan+], пока [npc.she] [npc.verb(start)] быстро скользит вверх и вниз по пульсирующей длине."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] с силой [npc.verb(wrap)] [npc.her] [npc.fingers] вокруг [npc2.her] [npc2.cock+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово двигался вверх и вниз по всей его длине.",

							"[npc.Name] [npc.verb(drop)] один из [npc.her] [npc.hands] между [npc2.namePos] [npc2.legs] и, грубо обхватив [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] с силой стягивал [npc2.herHim].",

							"С силой обхватив [npc.her] [npc.fingers] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово двигался вверх и вниз по его пульсирующей длине."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] с нетерпением [npc.verb(wrap)] [npc.her] [npc.fingers] вокруг [npc2.her] [npc2.cock+], она издает [npc.a_moan+], пока [npc.she] [npc.verb(start)] быстро скользит вверх и вниз по всей длине.",

							"[npc.Name] [npc.verb(drop)] одним из [npc.her] [npc.hands] между [npc2.namePos] [npc2.legs], и, обхватив [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] жадно натягивает [npc2.herHim].",

							"Лаская [npc.her] [npc.fingers] над [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] тихий [npc.a_moan+], пока [npc.she] [npc.verb(start)] быстро скользит вверх и вниз по пульсирующей длине."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers] вокруг [npc2.her] [npc2.cock+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] двигался вверх и вниз по всей его длине.",

							"[npc.Name] [npc.verb(drop)] один из [npc.her] [npc.hands] между [npc2.namePos] [npc2.legs] и, обхватив [npc2.her] [npc2.cock+], [npc.she] [npc.verb(start)] стягивал [npc2.herHim].",

							"Лаская [npc.her] [npc.fingers] по поверхности [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] двигался вверх и вниз по его пульсирующей длине."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Издавая тихий [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], сосредоточившись на ощущении [npc2.her] [npc2.cock+], которая пульсирует в ответ на [npc.her] прикосновение.",
	
								" С тихим [npc2.moan], [npc2.name] [npc2.verb(start)] медленно втирая [npc2.her] [npc2.hips] в [npc.namePos], наслаждаясь ощущением того, как [npc.her] [npc.fingers+] скользит вверх и вниз по [npc2.her] [npc2.cock+].",
	
								" [npc2.Name] [npc2.verb(start)] медленно толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], [npc2.moaning], пока [npc2.she] [npc2.verb(focus)] на ощущении [npc.her] [npc.fingers+], скользящей вверх и вниз по [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Издавая [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], сосредоточившись на ощущении [npc2.her] [npc2.cock+], которая пульсирует в ответ на [npc.her] прикосновение.",
	
								" Со [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно втирая [npc2.her] [npc2.hips] в [npc.namePos], наслаждаясь ощущением того, как [npc.her] [npc.fingers+] скользит вверх и вниз по [npc2.her] [npc2.cock+].",
								
								" [npc2.Name] [npc2.verb(start)] жадно толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], [npc2.moaning+], пока [npc2.she] [npc2.verb(focus)] на ощущении [npc.her] [npc.fingers+], скользящей вверх и вниз по [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Издавая [npc2.a_moan+], [npc2.name] [npc2.verb(start)] яростно толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], рыча, пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжать доставлять удовольствие [npc2.her] [npc2.cock+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] грубо втискивая [npc2.her] [npc2.hips] в [npc.namePos], наполняя [npc.her] [npc.hand] [npc2.her] [npc2.cock+], пока [npc2.she] [npc2.verb(order)] [npc.herHim] не останавливаться.",
	
								" [npc2.Name] [npc2.verb(start)] с силой толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], [npc2.moaning+], пока [npc2.she] [npc2.verb(order)] [npc.herHim], чтобы продолжать обслуживать [npc2.her] [npc2.cock+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Издавая [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], сосредоточившись на ощущении [npc2.her] [npc2.cock+], которая пульсирует в ответ на [npc.her] прикосновение.",
								
								" Со [npc2.a_moan+], [npc2.name] [npc2.verb(start)] жадно втирая [npc2.her] [npc2.hips] в [npc.namePos], наслаждаясь ощущением того, как [npc.her] [npc.fingers+] скользит вверх и вниз по [npc2.her] [npc2.cock+].",
	
								" [npc2.Name] [npc2.verb(start)] жадно толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], [npc2.moaning+], пока [npc2.she] [npc2.verb(focus)] на ощущении [npc.her] [npc.fingers+], скользящей вверх и вниз по [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Издавая [npc2.a_moan+], [npc2.name] [npc2.verb(start)] толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], сосредоточившись на ощущении [npc2.her] [npc2.cock+], пульсирующего в ответ на [npc.her] прикосновение.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)] втискивая [npc2.her] [npc2.hips] в [npc.namePos], наслаждаясь ощущением того, как [npc.her] [npc.fingers+] скользит вверх и вниз по [npc2.her] [npc2.cock+].",
	
								" [npc2.Name] [npc2.verb(start)] толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], [npc2.moaning+], пока [npc2.she] [npc2.verb(focus)] на ощущении [npc.her] [npc.fingers+], скользящего вверх и вниз по [npc2.her] [npc2.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.hips] назад от [npc.namePos] нежелательного прикосновения, [npc2.sobbing] и борясь, пока [npc2.her] пульсирующий [npc2.cock] выдает [npc2.her] Возбуждение.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], пытаясь отстраниться от [npc.name], борясь и протестуя, пока [npc2.her] твердый [npc2.cock] выдает [npc2.her] Возбуждение.",
	
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], пока [npc2.she] пытается отстраниться от [npc.namePos] нежелательного прикосновения."));
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
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая восторженный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(start)], умоляя [npc.name] продолжать делать [npc2.herHim] мастурбацию рукой.",
		
							" Восторженный [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(start)] с нетерпением выталкивает [npc2.her] [npc2.hips] навстречу [npc.namePos], пока [npc2.she] [npc2.verb(beg)] [npc.herHim], продолжая делать [npc2.herHim] ручной минет.",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips] вперед, жадно умоляя [npc.name] продолжать делать [npc2.herHim] мастурбацию рукой."));
				case SUB_RESISTING:
					return (UtilText.returnStringAtRandom(
							" Не сумев отпрянуть [npc2.her] [npc2.hips] от прикосновения [npc.namePos], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], когда [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name] от [npc2.herHim].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], прежде чем [npc2.she] [npc2.verb(start)] слабо пытается оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы сделать [npc2.herHim] ручной онанизм.",
		
							" [npc2.Sobbing] в смятении, [npc2.name] [npc2.verb(try)], тщетно пытаясь оттянуть [npc2.her] [npc2.hips] от прикосновений [npc.namePos], борясь с [npc.herHim], пока [npc.she] [npc.verb(continue)], навязывая [npc2.herHim] нежелательный ручной онанизм."));
				case DOM_GENTLE:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая восторженный [npc2.moan], прежде чем начать с энтузиазмом умолять [npc.name] продолжать делать [npc2.herHim] ручной онанизм.",
	
							" Восторженный [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(start)] мягко отталкивает [npc2.her] [npc2.hips] от прикосновений [npc.namePos], пока [npc2.she] [npc2.verb(beg)] [npc.herHim] продолжать делать [npc2.herHim] ручной онанизм.",
	
							" [npc2.Moaning] в восторге, [npc2.name] мягко [npc2.verb(push)] извлекает [npc2.her] [npc2.hips], пока [npc2.she] [npc2.verb(implore)] [npc.name] продолжать делать [npc2.herHim] ручной онанизм."));
				case DOM_ROUGH:
					return (UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в ответ, издавая восторженный [npc2.moan], пока [npc2.she] [npc2.verb(start)] приказывает [npc.name] продолжать делать [npc2.herHim] ручной онанизм.",
	
							" Восторженный [npc2.moan] вырывается из-под [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(start)] грубо выталкивает [npc2.her] [npc2.hips] навстречу [npc.namePos], пока [npc2.she] [npc2.verb(order)] [npc.name], продолжая делать [npc2.herHim] мастурбацию рукой.",
	
							" От восторга [npc2.Moaning], [npc2.name] агрессивно [npc2.verb(thrust)] извлекает [npc2.her] [npc2.hips], пока [npc2.she] [npc2.verb(command)] [npc.name], чтобы продолжать делать [npc2.herHim] мастурбацию рукой."));
				case SUB_NORMAL:
					return (UtilText.returnStringAtRandom(
							" В ответ [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] наружу, издавая [npc2.a_moan], пока [npc2.she] [npc2.verb(implore)] [npc.name], чтобы продолжать делать [npc2.herHim] мастурбацию рукой.",
		
							" [npc2.A_moan+] вырывается из-под [npc2.namePos] [npc2.lips], и [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.hips] навстречу [npc.namePos], пока [npc2.she] [npc2.verb(implore)] [npc.herHim], продолжая делать [npc2.herHim] мастурбацию рукой.",
		
							" [npc2.Moaning+], [npc2.name] [npc2.verb(push)] извлекает [npc2.her] [npc2.hips], умоляя [npc.name] продолжать делать [npc2.herHim] мастурбацию рукой."));
			}
		}
		return "";
	}
	
	public static final SexAction COCK_MASTURBATING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Нежная мастурбация рукой";
		}
		@Override
		public String getActionDescription() {
			return "Нежно продолжать делать [npc2.name] мастурбацию рукой.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] мягко [npc.verb(slide)] [npc.her] [npc.hand] вверх и вниз вокруг [npc2.namePos] [npc2.penisGirth] члена, размазывая при этом [npc2.her] [npc2.precum+] по всему [npc2.her] стволу.",
						
						"Используя [npc2.namePos] [npc2.precum+] в качестве дополнительной смазки, [npc.name] нежно [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.her] [npc2.cock+] и [npc.verb(jerk)] [npc2.herHim].",
						
						"[npc.Name] [npc.verb(let)] ободряющий [npc.moan], пока [npc.she] мягко [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.precum]-сочащегося [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Мягко обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] медленно [npc.verb(start)], чтобы скользить [npc.her] [npc.hand] вверх и вниз по [npc2.her] [npc2.penisGirth] стволу.",

						"[npc.Name] нежно [npc.verb(wrap)] [npc.her] [npc.hand] вокруг [npc2.namePos] [npc2.cock+], наклоняясь вперед и вдыхая [npc2.her] [npc2.scent+], пока [npc.she] мягко [npc.verb(slide)] [npc.her] [npc.fingers+] вверх и вниз по всей длине [npc2.her] [npc2.penisGirth].",

						"Мягко прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] мягко [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Ручная стимуляция";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить делать [npc2.name] минет рукой.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] жадно [npc.verb(slide)] [npc.her] [npc.hand] вверх и вниз вокруг [npc2.namePos] [npc2.penisGirth] члена, попутно размазывая [npc2.her] [npc2.precum+] по всему [npc2.her] стволу.",
						
						"Используя [npc2.namePos] [npc2.precum+] в качестве дополнительной смазки, [npc.name] с энтузиазмом [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.her] [npc2.cock+] и жадно [npc.verb(jerk)] [npc2.herHim].",
						
						"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] стремительно [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.precum]-сочащегося [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Жадно обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] быстро [npc.verb(start)], чтобы скользить [npc.her] [npc.hand] вверх и вниз по [npc2.her] [npc2.penisGirth] стержню.",
	
						"[npc.Name] с энтузиазмом [npc.verb(wrap)] [npc.her] [npc.hand] вокруг [npc2.namePos] [npc2.cock+], наклоняясь вперед и вдыхая [npc2.her] [npc2.scent+], пока [npc.she] жадно [npc.verb(slide)] [npc.her] [npc.fingers+] вверх и вниз вдоль всей [npc2.her] [npc2.penisGirth] длины.",
	
						"Счастливо прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] стремительно [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубый мастурбатор";
		}
		@Override
		public String getActionDescription() {
			return "Устроить [npc2.name] грубый мастурбатор.";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] грубо [npc.verb(pump)] [npc.her] [npc.hand] вверх и вниз вокруг [npc2.namePos] [npc2.penisGirth] члена, размазывая при этом [npc2.her] [npc2.precum+] по всему [npc2.her] стержню.",
						
						"Используя [npc2.namePos] [npc2.precum+] в качестве дополнительной смазки, [npc.name] с силой [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.her] [npc2.cock+] и агрессивно [npc.verb(jerk)] [npc2.herHim].",
						
						"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(pump)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.precum]-сочащегося [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Сильно сжимая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)], чтобы грубо качать [npc.her] [npc.hand] вверх и вниз по всей длине [npc2.her] [npc2.penisGirth] ствола.",
	
						"[npc.Name] агрессивно [npc.verb(wrap)] [npc.her] [npc.hand] вокруг [npc2.namePos] [npc2.cock+], наклоняясь вперед и вдыхая [npc2.her] [npc2.scent+], пока [npc.she] грубо [npc.verb(pump)] [npc.her] [npc.fingers+] вверх и вниз по всей длине [npc2.her] [npc2.penisGirth].",
	
						"Грубо растирая [npc.herself] о [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] агрессивно [npc.verb(pump)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Ручная стимуляция";
		}
		@Override
		public String getActionDescription() {
			return "Продолжить делать [npc2.namePos] мастурбацию рукой.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.hand] вверх и вниз вокруг [npc2.namePos] [npc2.penisGirth] члена, размазывая при этом [npc2.her] [npc2.precum+] по всему [npc2.her] стволу.",
						
						"Используя [npc2.namePos] [npc2.precum+] в качестве дополнительной смазки, [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.her] [npc2.cock+], и [npc.verb(jerk)] [npc2.herHim] прочь.",
						
						"[npc.Name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.precum]-сочного [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Окутывая [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)], чтобы скользить [npc.her] [npc.hand] вверх и вниз по [npc2.her] [npc2.penisGirth] стержню.",
	
						"[npc.Name] [npc.verb(wrap)] [npc.her] [npc.hand] вокруг [npc2.namePos] [npc2.cock+], наклоняясь вперед и вдыхая [npc2.her] [npc2.scent+], пока [npc.she] [npc.verb(slide)] [npc.her] [npc.fingers+] вверх и вниз по всей длине [npc2.her] [npc2.penisGirth].",
	
						"Прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PARTNER_COCK_MASTURBATING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадный ручной секс";
		}
		@Override
		public String getActionDescription() {
			return "С энтузиазмом делать [npc2.name] ручной секс.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			if(isCharacterPrecumming(Main.sex.getCharacterTargetedForSexAction(this))) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Name] жадно [npc.verb(slide)] [npc.her] [npc.hand] вверх и вниз вокруг [npc2.namePos] [npc2.penisGirth] члена, попутно размазывая [npc2.her] [npc2.precum+] по всему [npc2.her] стволу.",
						
						"Используя [npc2.namePos] [npc2.precum+] в качестве дополнительной смазки, [npc.name] с энтузиазмом [npc.verb(wrap)] [npc.her] [npc.fingers+] вокруг [npc2.her] [npc2.cock+] и жадно [npc.verb(jerk)] [npc2.herHim].",
						
						"[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] стремительно [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.precum]-сочащегося [npc2.cock]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Жадно обхватив [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] быстро [npc.verb(start)], чтобы скользить [npc.her] [npc.hand] вверх и вниз по [npc2.her] [npc2.penisGirth] стержню.",
	
						"[npc.Name] с энтузиазмом [npc.verb(wrap)] [npc.her] [npc.hand] вокруг [npc2.namePos] [npc2.cock+], наклоняясь вперед и вдыхая [npc2.her] [npc2.scent+], пока [npc.she] жадно [npc.verb(slide)] [npc.her] [npc.fingers+] вверх и вниз вдоль всей [npc2.her] [npc2.penisGirth] длины.",
	
						"Счастливо прижимая [npc.herself] к [npc2.name], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] стремительно [npc.verb(slide)] [npc.her] [npc.hand+] вверх и вниз по всей длине [npc2.namePos] [npc2.cock+]."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться ручному сексу";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.hand] от [npc2.namePos] [npc2.cock+].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(feel)], слезы начинают наворачиваться на [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)], чтобы оттянуть [npc.her] [npc.hand] от [npc2.namePos] [npc2.cock+].",

					"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.hand] от [npc2.namePos] [npc2.cock+].",

					"[npc.Name] [npc.sobsVerb] в смятении, пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.hand] от [npc2.namePos] [npc2.cock+]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Предвосхищая движение [npc.her], [npc2.name] [npc2.verb(reach)] вниз и мягко, но уверенно [npc2.verb(force)] [npc.name], чтобы продолжить делать [npc2.herHim] мастурбацию рукой.",

							" [npc2.Name] быстро [npc2.verb(reach)] вниз и крепко [npc2.verb(grab)] [npc.namePos] [npc.hand], прежде чем осторожно направить её обратно к [npc2.her] [npc2.cock+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Предвосхищая движение [npc.her], [npc2.name] [npc2.verb(reach)] вниз и [npc2.verb(grab)] [npc.namePos] [npc.hand], прежде чем заставить [npc.herHim] продолжить делать [npc2.herHim] мастурбацию рукой.",

							" [npc2.Name] быстро [npc2.verb(reach)] вниз и [npc2.verb(grab)] [npc.namePos] [npc.hand], прежде чем оттянуть её обратно к [npc2.her] [npc2.cock+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Предвосхищая движение [npc.her], [npc2.name] [npc2.verb(reach)] вниз и грубо [npc2.verb(grab)] [npc.namePos] [npc.hand], прежде чем заставить [npc.herHim] продолжить делать [npc2.herHim] мастурбацию рукой.",

							" [npc2.Name] быстро [npc2.verb(reach)] вниз и грубо [npc2.verb(grab)] [npc.namePos] [npc.hand], прежде чем резко дернуть её обратно к [npc2.her] [npc2.cock+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction COCK_MASTURBATING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить мастурбацию рукой";
		}
		@Override
		public String getActionDescription() {
			return "Отпустить [npc2.namePos] [npc2.cock+] и перестать делать [npc2.herHim] мастурбацию рукой.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Забирая [npc.her] [npc.hand] у [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.cockHead] в последний раз грубо сжав, пока [npc.she] перестает делать [npc2.herHim] хендджобом.",

							"[npc.Name] резко [npc.verb(inhale)], вдыхая [npc2.namePos] [npc2.scent+], прежде чем убрать [npc.her] [npc.fingers] от [npc2.her] [npc2.cock+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Забирая [npc.her] [npc.hand] у [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.cockHead] одним последним движением, пока [npc.she] перестает делать [npc2.herHim] хендджобом.",

							"[npc.Name] резко [npc.verb(inhale)], вдыхая [npc2.namePos] [npc2.scent+], прежде чем убрать [npc.her] [npc.fingers] от [npc2.her] [npc2.cock+]."));
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
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.name] перестает стимулировать [npc2.her] [npc2.cock+].",

							" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] жажду еще большего внимания к [npc.namePos]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction COCK_MASTURBATED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Получить хендджобом";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать делать тебе хендджобом.";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко, но уверенно обхватив [npc2.namePos] [npc2.hand], [npc.name] медленно [npc.verb(guide)] [npc2.her] [npc2.fingers] вокруг [npc.her] [npc.cock+], тихонько [npc.moan], прежде чем заставить [npc2.herHim] начать делать [npc.herHim] хендджобом.",
							
							"Обхватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] вокруг [npc.her] [npc.cock+], и медленным, уверенным движением [npc.she] нежно [npc.verb(make)], заставляя [npc2.herHim] начать делать [npc.herHim] хендджобом."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вокруг [npc.her] [npc.cock+], издавая [npc.a_moan+], прежде чем жадно заставить [npc2.herHim] начать делать [npc.herHim] мастурбацию рукой.",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] вокруг [npc.her] [npc.cock+], и с решительным напором, [npc.she] жадно [npc.verb(make)], заставляя [npc2.herHim] начать делать [npc.herHim] мастурбацию рукой."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мертвой хваткой вцепившись в [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] вокруг [npc.her] [npc.cock+], издавая [npc.a_moan+], прежде чем грубо заставить [npc2.herHim] начать делать [npc.herHim] мастурбацию рукой.",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] властно [npc.verb(push)] [npc2.namePos] [npc2.fingers] вокруг [npc.her] [npc.cock+], и доминирующими, резкими движениями, [npc.she] грубо [npc.verb(force)], заставляя [npc2.herHim] начать делать [npc.herHim] мастурбацию рукой."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вокруг [npc.her] [npc.cock+], издавая [npc.a_moan+], прежде чем жадно заставить [npc2.herHim] начать делать [npc.herHim] мастурбацию рукой.",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вокруг [npc.her] [npc.cock+], и с решительным напором, [npc.she] жадно [npc.verb(make)] [npc2.herHim] начинают делать [npc.herHim] мастурбацию пальцами."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вокруг [npc.her] [npc.cock+], издавая [npc.a_moan+], прежде чем [npc2.herHim] начинают делать [npc.herHim] мастурбацию пальцами.",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] вокруг [npc.her] [npc.cock+], и с решительным напором, [npc.she] [npc.verb(make)] [npc2.herHim] начинают делать [npc.herHim] мастурбацию пальцами."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(start)] нежно поглаживает [npc.her] [npc.cock+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)] медленно водит [npc2.her] [npc2.hand] вверх и вниз по всей длине [npc.namePos] [npc.cock+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] с нетерпением [npc2.verb(start)] [npc.her] [npc.cock+].",
	
								" С [npc2.a_moan+], [npc2.name] с удовольствием [npc2.verb(start)] водит [npc2.her] [npc2.hand] вверх и вниз по всей длине [npc.namePos] [npc.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(start)] с силой поглаживая [npc.her] [npc.cock], и, желая напомнить [npc.name], кто здесь главный, [npc2.she] грубо [npc2.verb(squeeze)] [npc2.her] пульсирующий член.",
	
								" С помощью [npc2.a_moan+], [npc2.name] [npc2.verb(start)] грубо качает [npc2.her] [npc2.hand] вверх и вниз по всей длине [npc.namePos] [npc.cock+], стремясь напомнить [npc.herHim], кто здесь главный, пока [npc2.she] с силой [npc2.verb(squeeze)] [npc2.her] пульсирующий член."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] с нетерпением [npc2.verb(start)] [npc.her] [npc.cock+].",
	
								" С [npc2.a_moan+], [npc2.name] с удовольствием [npc2.verb(start)] водит [npc2.her] [npc2.hand] вверх и вниз по всей длине [npc.namePos] [npc.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_moan+], пока [npc2.she] [npc2.verb(start)] поглаживает [npc.her] [npc.cock+].",
	
								" С помощью [npc2.a_moan+], [npc2.name] [npc2.verb(start)] скользит [npc2.her] [npc2.hand] вверх и вниз по всей длине [npc.namePos] [npc.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] извлекает [npc2.a_sob+], пока [npc.name] [npc.verb(force)] [npc2.herHim], чтобы сделать мастурбацию, борясь с [npc.namePos] крепкой хваткой на [npc2.her] [npc2.hand], пока [npc2.she] [npc2.verb(try)], чтобы освободить [npc2.herself].",
	
								" С помощью [npc2.a_sob+], [npc2.name] [npc2.verb(start)] борется с [npc.namePos] тугой хваткой на [npc2.her] [npc2.hand], умоляя [npc.name] остановиться, пока [npc.she] [npc2.verb(force)] [npc2.herHim], чтобы сделать мастурбацию."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Мягко принимать мастурбацию";
		}
		@Override
		public String getActionDescription() {
			return "Мягко наслаждаться мастурбацией [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] нежный [npc.moan], пока [npc2.name] [npc2.verb(continue)] делает [npc.herHim] мастурбацию рукой.",

					"С мягким [npc.moan], [npc.name] осторожно [npc.verb(start)] выталкивает [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], наслаждаясь мастурбацией, которую получает [npc.sheIs].",

					"Медленно выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], тихий [npc.moan] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] усиливают удовольствие, которое [npc.sheIs] получает от мастурбации [npc2.namePos] рукой."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Получать мастурбацию рукой";
		}
		@Override
		public String getActionDescription() {
			return "Наслаждаться мастурбацией [npc2.namePos] рукой.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.name] [npc2.verb(continue)] делает [npc.herHim] мастурбацию рукой.",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] выталкивает [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], наслаждаясь мастурбацией, которую получает [npc.sheIs].",

					"Нетерпеливо выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] усиливают удовольствие, которое [npc.sheIs] получает от мастурбации [npc2.namePos] рукой."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Грубо получать мастурбацию рукой";
		}
		@Override
		public String getActionDescription() {
			return "Грубо наслаждаться мастурбацией [npc2.namePos] рукой.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вколачивая [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.name] [npc2.verb(continue)] делает [npc.herHim] мастурбацию.",

					"С [npc.a_moan+], [npc.name] агрессивно [npc.verb(start)] вколачивает [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], наслаждаясь мастурбацией, которую получает [npc.sheIs].",

					"Грубо выталкивая [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] усиливают удовольствие, которое [npc.sheIs] получает от мастурбации [npc2.namePos]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Получать мастурбацию рукой";
		}
		@Override
		public String getActionDescription() {
			return "Наслаждаться мастурбацией [npc2.namePos] рукой.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Выталкивая [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.name] [npc2.verb(continue)] делает [npc.herHim] мастурбацию.",

					"С [npc.a_moan+], [npc.name] [npc.verb(start)] вколачивает [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], наслаждаясь мастурбацией, которую получает [npc.sheIs].",

					"Выталкивая [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], [npc.a_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока движения [npc.her] усиливают удовольствие, которое [npc.sheIs] получает от мастурбации [npc2.namePos]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Жадно принимать мастурбацию";
		}
		@Override
		public String getActionDescription() {
			return "С жадностью наслаждаться мастурбацией [npc2.namePos].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc2.name] [npc2.verb(continue)] делает [npc.herHim] мастурбацию рукой.",

					"С [npc.a_moan+], [npc.name] с энтузиазмом [npc.verb(start)] выталкивает [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], наслаждаясь мастурбацией, которую получает [npc.sheIs].",

					"Нетерпеливо выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] усиливают удовольствие, которое [npc.sheIs] получает от мастурбации [npc2.namePos] рукой."));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction COCK_MASTURBATED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Сопротивляться мастурбации";
		}
		@Override
		public String getActionDescription() {
			return "Попытаться оттянуть свой [npc.cock+] от [npc2.namePos] [npc2.hand].";
		}
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(feel)] начинают подступать к [npc.namePos] [npc.eyes], и, больше не в силах сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.hips] и [npc.cock] от [npc2.namePos] [npc2.hand].",

					"[npc.A_sob+] вырывается из [npc.namePos] рта, пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock+] от [npc2.namePos] [npc2.hand].",

					"[npc.Name] [npc.sobsVerb] в смятении, пока [npc.she] лихорадочно [npc.verb(try)], пытаясь оттянуть [npc.her] [npc.cock+] от [npc2.namePos] [npc2.hand]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Предвосхищая движение [npc.her], [npc2.name] мягко, но уверенно [npc2.verb(grip)] [npc.namePos] [npc.cock+], не оставляя [npc.herHim] иного выбора, кроме как замереть и попытаться насладиться мастурбацией, которую [npc2.sheIs] оказывает [npc2.herHim].",

							" [npc2.Name] мягко, но уверенно [npc2.verb(grip)] [npc.namePos] [npc.cock+] в [npc2.her] [npc2.hand], не давая [npc2.her] сбежать и продолжая текущую мастурбацию."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Предвосхищая движение [npc.her], [npc2.name] уверенно [npc2.verb(grip)] [npc.namePos] [npc.cock+], не оставляя [npc.herHim] иного выбора, кроме как замереть и попытаться насладиться мастурбацией, которую [npc2.sheIs] оказывает [npc2.herHim].",

							" [npc2.Name] уверенно [npc2.verb(grip)] [npc.namePos] [npc.cock+] в [npc2.her] [npc2.hand], не давая [npc2.her] сбежать и продолжая текущую мастурбацию."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Предвкушая [npc.her] движение, [npc2.name] грубо [npc2.verb(grip)] [npc.namePos] [npc.cock+], не оставляя [npc.herHim] иного выбора, кроме как замереть и пытаться наслаждаться ручной стимуляцией, которую [npc2.sheIs] оказывает [npc2.herHim].",

							" [npc2.Name] с силой [npc2.verb(grip)] [npc.namePos] [npc.cock+] в [npc2.her] [npc2.hand], преграждая [npc2.her] путь к отступлению и продолжая текущую мастурбацию."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction COCK_MASTURBATED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Прекратить получать ручную стимуляцию";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] убрать [npc2.her] [npc2.hand] от твоего [npc.cock].";
		}
		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко отдергивая [npc2.namePos] [npc2.fingers] от [npc.her] [npc.cock+], [npc.name] [npc.verb(growl)] на [npc2.name], пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы прекратить [npc2.her] мастурбацию.",
	
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем резко отдернуть [npc2.her] [npc2.fingers] от [npc.her] [npc.cock+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc2.namePos] [npc2.fingers] прочь от [npc.her] [npc.cock+], [npc.name] [npc.verb(let)] из [npc.a_moan+], прежде чем велеть [npc2.herHim] прекратить [npc2.her] мастурбацию.",
	
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent], прежде чем оттолкнуть [npc2.her] [npc2.hand] от [npc.her] [npc.cock+]."));
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
							" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы не касаться [npc.her] [npc.cock+].",
	
							" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.her] еще больше [npc.cock+] внимания [npc2.her]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
