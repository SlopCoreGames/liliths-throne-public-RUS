package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
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
 * @version 0.3.5
 * @author Innoxia
 */
public class TongueMouth {
	
	public static final SexAction KISS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isOrificeNonSelfOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
		}
		
		@Override
		public String getActionTitle() {
			return "Начать целоваться";
		}

		@Override
		public String getActionDescription() {
			return "Прижать свой [npc.lips] к губам [npc2.namePos] и начать страстно целоваться с [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL_REVERSE)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING_IN_LAP)) {
				
				if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(sink)] на [npc2.namePos] [npc2.penis], издавая [npc.a_moan+], пока [npc.her] [npc.asshole+] сжимает [npc2.her] [npc2.penisGirth] [npc2.penis+]. Когда [npc.her] [npc.moan] затихает, [npc.name] [npc.verb(lean)] и [npc.verb(grab)] [npc2.namePos] голову обеими руками, прежде чем прижать [npc.her] [npc.lips+] к [npc2.hers] и одарить страстным поцелуем.",
									
							"С [npc.a_moan+], [npc.name] [npc.verb(slide)] на [npc2.namePos] [npc2.penis+], прежде чем наклониться и прижать [npc.herself] к [npc2.her] [npc2.breasts]. Вдыхая [npc2.namePos] [npc2.scent+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.hers] и [npc.verb(start)], чтобы страстно поцеловать [npc2.herHim].",
							
							"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] проникают еще глубже в [npc.her] [npc.asshole+], пока [npc.she] [npc.verb(lean)] вниз и [npc.verb(pull)] [npc2.herHim] в отчаянном поцелуе.",
							
							"Опустившись ниже, [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(bury)] [npc2.namePos] [npc2.penis+] в [npc.her] [npc.asshole+], прежде чем наклониться и прижать [npc.her] [npc.lips+] к [npc2.hers]."));
					
				} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(sink)] на [npc2.namePos] [npc2.penis], издавая [npc.a_moan+], пока [npc.her] [npc.vagina+] сжимает [npc2.her] [npc2.penisGirth] [npc2.penis+]. Когда [npc.her] [npc.moan] затихает, [npc.name] [npc.verb(lean)] вниз и [npc.verb(grab)] [npc2.namePos] голову обеими руками, прежде чем прижать [npc.her] [npc.lips+] к [npc2.hers] и одарить страстным поцелуем.",
									
							"С [npc.a_moan+], [npc.name] [npc.verb(slide)] на [npc2.namePos] [npc2.penis+], прежде чем наклониться и прижать [npc.herself] к [npc2.her] [npc2.breasts]. Вдыхая [npc2.namePos] [npc2.scent+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.hers] и [npc.verb(start)], чтобы страстно поцеловать [npc2.herHim].",
							
							"[npc.Name] [npc.verb(let)] [npc2.namePos] [npc2.penis+] [npc2.verb(slide)] еще глубже в [npc.her] [npc.vagina+], пока [npc.she] [npc.verb(lean)] вниз и [npc.verb(pull)] [npc2.herHim] в отчаянном поцелуе.",
							
							"Опустившись, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(bury)] [npc2.namePos] [npc2.penis+] в [npc.her] [npc.vagina+], прежде чем наклониться и прижать [npc.her] [npc.lips+] к [npc2.hers]."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(lean)] вниз, прижимая [npc.her] [npc.lips+] к [npc2.namePos] рту, пока [npc.she] [npc.verb(deliver)] страстный поцелуй.",
							
							"С ухмылкой, [npc.name] [npc.verb(lean)] в [npc2.namePos] [npc2.breasts], вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.hers].",
							
							"Наклоняясь, [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.namePos] и [npc.verb(start)], чтобы жадно поцеловать [npc2.herHim]."));
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] в [npc.namePos] рот, прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] с нетерпением [npc2.verb(return)] [npc.namePos] знак привязанности.",
								
								" С [npc2.a_moan], [npc2.name] [npc2.verb(lean)] в [npc.name], заглушая [npc2.her] [npc2.moans] в [npc.her] рот, пока [npc2.she] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.tongue] мимо [npc.her] [npc.lips+].",
								
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(lean)] в [npc.name], прижимая [npc2.her] [npc2.lips+] к [npc.namePos], пока [npc2.she] счастливо [npc2.verb(slide)] [npc2.her] [npc2.tongue] в [npc.her] рот."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)] попытаться отстраниться, [npc2.sobbing] и ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.herself] на [npc2.herHim].",
								
								" [npc2.NamePos] [npc2.sob+] заглушен в [npc.namePos] рот, пока [npc2.she] [npc2.verb(try)] попытаться отстраниться, ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.herself] на [npc2.herHim].",
								
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно, попытаться отстраниться от [npc.name], протестуя и ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.her] [npc.tongue] мимо неохотного [npc2.lips] [npc2.her]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] happily [npc2.verb(push)] [npc2.her] [npc2.tongue] deep into [npc.namePos] mouth, eagerly pressing [npc2.her] [npc2.lips+] back against [npc.hers] and [npc2.moaning] in delight as [npc2.she] greedily [npc2.verb(return)] [npc.her] display of affection.",
								
								" С нетерпеливым [npc2.moan], [npc2.name] отчаянно [npc2.verb(grind)] назад к [npc.name], заглушая [npc2.her] [npc2.moans] в [npc.her] рот, пока [npc2.she] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tongue] сквозь [npc.her] [npc.lips+].",
								
								" [npc2.Moaning] в восторге, [npc2.name] с нетерпением [npc2.verb(press)] [npc2.her] [npc2.lips+] крепко к [npc.namePos], пока [npc2.she] счастливо [npc2.verb(push)] [npc2.her] [npc2.tongue] в [npc.her] рот."));
							break;
					}
				}
			
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {// Face-to-wall penetration descriptions:
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руку, чтобы обхватить подбородок [npc2.namePos], [npc.name] [npc.verb(tilt)] наклоняет [npc2.her] голову набок, пока [npc.she] [npc.verb(lean)] вперед, прижимая [npc.her] [npc.lips+] к [npc2.hers], пока [npc.she] [npc.verb(pull)] [npc2.herHim] в нежный поцелуй.",
							
							"[npc.Name] [npc.verb(lean)] вперед, прижимаясь к спине [npc2.namePos] и вдыхая [npc2.her] [npc2.scent+]. Протянув руку, [npc.she] [npc.verb(turn)] наклоняет [npc2.namePos] голову набок и нежно [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] в спину [npc2.namePos], слегка поворачивая голову [npc2.her] набок, прежде чем мягко притянуть [npc2.herHim] для нежного поцелуя."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руку, чтобы грубо схватить [npc2.namePos] за подбородок, [npc.name] [npc.verb(yank)] [npc2.her] набок, а затем прижимая [npc.her] [npc.lips+] к [npc2.hers], пока [npc.she] [npc.verb(pull)] [npc2.herHim] в напористый поцелуй.",

							"[npc.Name] [npc.verb(lean)] вперед, прижимаясь к [npc2.namePos] со спины и вдыхая [npc2.her] [npc2.scent+]. Протянув руку, [npc.she] грубо [npc.verb(yank)] [npc2.namePos] набок, а затем с силой [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.hers].",
											
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] со спины, дергая [npc2.her] за голову набок, прежде чем грубо загнать [npc.her] [npc.tongue+] в [npc2.her] горло."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Протянув руку, чтобы обхватить [npc2.her] за подбородок, [npc.name] [npc.verb(lean)] вперед, жадно прижимая [npc.her] [npc.lips+] к [npc2.hers], пока [npc.she] [npc.verb(pull)] [npc2.herHim] в страстный поцелуй.",

							"[npc.Name] [npc.verb(lean)] вперед, прижимаясь к [npc2.namePos] со спины и вдыхая [npc2.her] [npc2.scent+]. Протянув руку, [npc.she] [npc.verb(turn)] [npc2.namePos] набок и страстно [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.hers].",
									
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] со спины, слегка поворачивая [npc2.her] голову набок, прежде чем жадно вовлечь [npc2.herHim] в страстный поцелуй."));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] в [npc.name] рот, крепко прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] с жадностью [npc2.verb(return)] [npc.her] знак привязанности.",
								
								" С [npc2.a_moan], [npc2.name] [npc2.verb(lean)] спиной к [npc.name], заглушая [npc2.her] [npc2.moans] в [npc.her] рот, пока [npc2.she] с жадностью [npc2.verb(thrust)] [npc2.her] [npc2.tongue] сквозь [npc.her] [npc.lips+].",
								
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(lean)] спиной к [npc.name], прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] счастливо [npc2.verb(slide)] [npc2.her] [npc2.tongue] в [npc.her] рот."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)] попытаться отстраниться, [npc2.sobbing] и ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.herself] на [npc2.herHim].",
								
								" [npc2.NamePos] [npc2.sob+] заглушен в [npc.namePos] рот, пока [npc2.she] [npc2.verb(try)] попытаться отстраниться, ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.herself] на [npc2.herHim].",
								
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно, попытаться отстраниться от [npc.name], протестуя и ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.her] [npc.tongue] мимо неохотного [npc2.lips] [npc2.her]."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] счастливо [npc2.verb(push)] [npc2.her] [npc2.tongue] глубоко в [npc.namePos] рот, жадно прижимая [npc2.her] [npc2.lips+] к [npc.hers], а [npc2.moaning] в восторге, пока [npc2.she] жадно [npc2.verb(return)] [npc.her] знак привязанности.",
								
								" С нетерпеливым [npc2.moan], [npc2.name] отчаянно [npc2.verb(grind)] назад к [npc.name], заглушая [npc2.her] [npc2.moans] в [npc.her] рот, пока [npc2.she] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tongue] сквозь [npc.her] [npc.lips+].",
							
								" [npc2.Moaning] в восторге, [npc2.name] отчаянно [npc2.verb(grind)] [npc2.herself] спиной к [npc.name], жадно прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] счастливо [npc2.verb(push)] [npc2.her] [npc2.tongue] в [npc.her] рот."));
							break;
					}
				}
				
			} else { // Default penetration descriptions:
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вглядываясь в [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] вперед, прижимая [npc.her] [npc.lips+] к [npc2.hers], пока [npc.she] [npc.verb(pull)] [npc2.herHim] в нежный поцелуй.",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] нежно [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] [npc2.breasts+], слегка наклоняя [npc.her] голову набок, прежде чем мягко притянуть [npc2.herHim] для нежного поцелуя."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вглядываясь в [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] вперед, грубо прижимая [npc.her] [npc.lips+] к [npc2.hers], пока [npc.she] [npc.verb(pull)] [npc2.herHim] в страстном поцелуе.",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] жадно [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] [npc2.breasts+], слегка наклоняя [npc.her] голову набок, прежде чем жадно притянуть [npc2.herHim] для страстного поцелуя."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вглядываясь в [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] вперед, грубо прижимая [npc.her] [npc.lips+] к [npc2.hers], пока [npc.she] [npc.verb(pull)] [npc2.herHim] в напористом поцелуе.",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] грубо [npc.verb(grind)] [npc.her] [npc.lips+] против [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] [npc2.breasts+], наклоняя [npc.her] голову набок, прежде чем резко притянуть [npc2.herHim] для грубого поцелуя."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вглядываясь в [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] вперед, жадно прижимая [npc.her] [npc.lips+] к [npc2.hers] и даря страстный поцелуй.",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] жадно [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] [npc2.breasts+], слегка наклонив [npc.her] голову набок, прежде чем запечатлеть отчаянный, страстный поцелуй на [npc2.her] [npc2.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вглядываясь в [npc2.namePos] [npc2.eyes], [npc.name] [npc.verb(lean)] вперед, прежде чем прижать [npc.her] [npc.lips+] к [npc2.hers] и дарить страстный поцелуй.",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] счастливо [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.hers].",
							
							"[npc.Name] [npc.verb(lean)] в [npc2.namePos] [npc2.breasts+], слегка наклонив [npc.her] голову набок, прежде чем запечатлеть влажный поцелуй на [npc2.her] [npc2.lips+]."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] медленно [npc2.verb(push)] [npc2.her] [npc2.tongue] в рот [npc.namePos], протягивая руку, чтобы нежно ласкать [npc.her] [npc.face], пока [npc2.she] счастливо [npc2.verb(return)] [npc.her] в знак привязанности.",
								
								" Одобрительно хмыкнув, [npc2.name] [npc2.verb(lean)] в [npc.name], заглушая [npc.her] [npc.moans] с помощью [npc2.her] [npc2.lips+], пока [npc2.she] нежно [npc2.verb(push)] [npc2.her] [npc2.tongue] в рот [npc.her].",
								
								" [npc2.Moaning] в знак одобрения, [npc2.name] [npc2.verb(lean)] в [npc.name], нежно прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] медленно [npc2.verb(slide)] [npc2.her] [npc2.tongue] в рот [npc.her]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] в [npc.namePos] рот, жадно прижимая [npc2.her] [npc2.lips+] [npc.hers], пока [npc2.she] с нетерпением [npc2.verb(return)] [npc.her] проявление привязанности.",
								
								" С одобряющим [npc2.moan], [npc2.name] [npc2.verb(lean)] в [npc.name], заглушая [npc.her] [npc.moans] с помощью [npc2.her] [npc2.lips+], пока [npc2.she] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tongue] в рот [npc.her].",
										
								" [npc2.Moaning] в знак одобрения, [npc2.name] [npc2.verb(lean)] в [npc.name], крепко прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] с нетерпением [npc2.verb(slide)] [npc2.her] [npc2.tongue] в рот [npc.her]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(force)] [npc2.her] [npc2.tongue] глубоко в [npc.namePos] рот, грубо прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] жадно [npc2.verb(return)] [npc.her] проявление привязанности.",
								
								" С одобряющим [npc2.moan], [npc2.name] с силой [npc2.verb(grind)] вверх к [npc.herHim], заглушая [npc.her] [npc.moans] с помощью [npc2.her] [npc2.lips+], пока [npc2.she] грубо [npc2.verb(thrust)] [npc2.her] [npc2.tongue] глубоко в рот [npc.her].",
								
								" [npc2.Moaning] с одобрением, [npc2.name] [npc2.verb(grind)] [npc2.herself] прижавшись к [npc.name], с силой вдавливая [npc2.her] [npc2.lips+] в [npc.hers], пока [npc2.she] грубо [npc2.verb(push)] [npc2.her] [npc2.tongue] глубоко в [npc.her] рот."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] счастливо [npc2.verb(push)] [npc2.her] [npc2.tongue] глубоко в [npc.namePos] рот, жадно прижимая [npc2.her] [npc2.lips+] к [npc.hers] и с наслаждением [npc2.moaning], пока [npc2.she] алчно [npc2.verb(return)] [npc.her] проявление привязанности.",
								
								" С нетерпеливым [npc2.moan], [npc2.name] отчаянно [npc2.verb(grind)] прижавшись к [npc.name], заглушая [npc2.her] [npc2.moans] в [npc.her] рот, пока [npc2.she] жадно [npc2.verb(thrust)] [npc2.her] [npc2.tongue] сквозь [npc.her] [npc.lips+].",
								
								" [npc2.Moaning] в восторге, [npc2.name] отчаянно [npc2.verb(grind)] [npc2.herself] прижавшись к [npc.name], с нетерпением плотно прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] счастливо [npc2.verb(push)] [npc2.her] [npc2.tongue] в [npc.her] рот."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.tongue] в [npc.namePos] рот, прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] с нетерпением [npc2.verb(return)] [npc.her] проявление привязанности.",
								
								" С [npc2.a_moan], [npc2.name] [npc2.verb(lean)] в [npc.name], заглушая [npc2.her] [npc2.moans] в [npc.her] рот, пока [npc2.she] с нетерпением [npc2.verb(thrust)] [npc2.her] [npc2.tongue] мимо [npc.her] [npc.lips+].",
								
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(lean)] в [npc.name], прижимая [npc2.her] [npc2.lips+] к [npc.hers], пока [npc2.she] счастливо [npc2.verb(slide)] [npc2.her] [npc2.tongue] в рот [npc.her]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(try)] попытаться отстраниться, [npc2.sobbing] и ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.herself] на [npc2.herHim].",
								
								" [npc2.NamePos] [npc2.sob+] заглушен в [npc.namePos] рот, пока [npc2.she] [npc2.verb(try)] попытаться отстраниться, ерзая от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.herself] на [npc2.herHim].",
								
								" С [npc2.a_sob+], [npc2.she] тщетно [npc2.verb(try)], пытаясь отстраниться от [npc.name], протестуя и извиваясь от дискомфорта, пока [npc.name] [npc.verb(force)] [npc.her] [npc.tongue] мимо неохотного [npc2.her] [npc2.lips]."));
							break;
						default:
							break;
					}
				}
			
			}
			
			//TODO describe tongue modifiers
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
					return(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] в ответ жадный [npc2.moan], с энтузиазмом прижимая [npc2.herself] к [npc.name] и втискивая [npc2.her] [npc2.tongue] в рот [npc.her], пока [npc2.she] жадно [npc2.verb(return)] этот жест [npc.her].",
						
						" С восторженным [npc2.moan], [npc2.name] жадно [npc2.verb(lean)] в [npc.name], проталкивая [npc2.her] [npc2.tongue] сквозь [npc.her] [npc.lips], пока [npc2.she] отчаянно тянет [npc.herHim] в неистовый поцелуй.",
					
						" Издавая [npc2.a_moan] от восторга, [npc2.name] прижимает [npc2.herself] к [npc.name], с энтузиазмом отвечая на [npc.her] поцелуй, пока [npc2.she] [npc2.verb(grind)] [npc2.her] [npc2.lips+] о [npc.her] рот."));
				case SUB_RESISTING:
					return(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_sob+], изо всех сил пытаясь [npc2.her] [npc2.verb(push)] [npc.name], пока [npc2.she] дискомфортно [npc2.verb(writhe)].",
						
						" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], пытаясь отстраниться от [npc2.verb(push)] [npc.name], тщетно борясь, пока [npc.she] [npc.verb(continue)], чтобы осыпать нежными поцелуями неохотные [npc2.lips] [npc2.her].",
						
						" [npc2.Name] отчаянно [npc2.verb(try)], пытаясь вырваться, борясь и отталкивая, пока [npc.name] [npc.verb(continue)], чтобы ласкать [npc2.herHim], вызывая приглушенный [npc2.sob] из-за [npc2.her] [npc2.lips] от каждого из нежеланных [npc.her] поцелуев."));
				case DOM_GENTLE:
					return(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan] в ответ, на мгновение замирая, чтобы осыпать серию нежных поцелуев на [npc.namePos] [npc.lips+], прежде чем осторожно просунуть [npc2.her] [npc2.tongue] в [npc.her] рот.",
						
						" С мягким [npc2.moan], [npc2.name] [npc2.verb(lean)] в [npc.name], осторожно проталкивая [npc2.her] [npc2.tongue] сквозь [npc.her] [npc.lips+], как [npc2.she] [npc2.verb(return)] [npc.herHis] проявление привязанности.",
						
						" Издавая тихий [npc2.moan], [npc2.name] [npc2.verb(press)] [npc2.herself] против [npc.name], прижимая [npc2.her] [npc2.moans] к [npc.her] [npc.lips+], пока [npc2.she] медленно [npc2.verb(slide)] [npc2.her] [npc2.tongue] в [npc.her] рот."));
				case DOM_ROUGH:
					return(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] рычание, грубо втирая [npc2.her] [npc2.lips] в рот [npc.her], пока [npc2.she] яростно [npc2.verb(thrust)] [npc2.her] язык глубоко в [npc.her] горло.",
						
						" With a menacing growl, [npc2.name] violently [npc2.verb(grind)] up against [npc.name], concerned solely with [npc2.her] own pleasure as [npc2.she] greedily [npc2.verb(thrust)] [npc2.her] [npc2.tongue] deep down [npc.her] throat.",
						
						" [npc2.Name] ухмыляются в ответ на проявление [npc.namePos] привязанности, и с яростным рычанием, [npc2.she] с силой [npc2.verb(grind)] [npc2.her] [npc2.lips] против [npc.hers], заталкивая [npc2.moaning] в рот [npc.her], пока [npc2.she] жадно языком [npc2.verb(fuck)] [npc.her] горло."));
				default:
					return(UtilText.returnStringAtRandom(
						" [npc2.Name] в ответ [npc2.verb(let)] [npc2.a_moan], прижимая [npc2.herself] к [npc.name] и счастливо проталкивая [npc2.her] [npc2.tongue] в рот [npc.her], когда [npc2.she] отвечает на проявление [npc.herHis] привязанности.",
						
						" С [npc2.a_moan], [npc2.name] [npc2.verb(lean)] в [npc.name], проталкивая [npc2.her] [npc2.tongue] мимо [npc.her] [npc.lips], пока [npc2.she] [npc2.verb(pull)] [npc.herHim] в жадном поцелуе.",
						
						" Издавая [npc2.a_moan], [npc2.name] [npc2.verb(lean)] в [npc.name], отвечая на [npc.her] поцелуй, пока [npc2.she] прижимает [npc2.her] [npc2.lips+] к [npc.her] рту."));
			}
		}
		return "";
	}
	
	public static final SexAction KISS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный поцелуй";
		}

		@Override
		public String getActionDescription() {
			return "Нежно поцеловать [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Все еще прижимаясь к спине [npc2.namePos], [npc.name] мягко [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.nameHers], прежде чем осыпать [npc2.her] рот серией мягких поцелуев.",
					
					"[npc.Name] нежно [npc.verb(lean)] в [npc2.namePos] со спины, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(plant)] серию мягких поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] медленно [npc.verb(press)] в [npc2.namePos] со спины, осторожно прижимая [npc2.herHim] к стене, пока [npc.she] [npc.verb(lean)] через [npc2.her] плечо, чтобы нежно поцеловать [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нежно прижимая [npc.her] [npc.lips+] к [npc2.nameHers], [npc.name] [npc.verb(plant)] серию мягких поцелуев в [npc2.her] рот.",
					
					"[npc.Name] нежно [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] [npc.verb(plant)] серию мягких поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] нежно [npc.verb(press)] против [npc2.namePos] [npc2.breasts+], прежде чем слегка наклонить [npc.her] голову набок, пока [npc.she] мягко [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Поцеловать [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать целовать [npc2.name].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Все еще прижимаясь к спине [npc2.namePos], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.nameHers], прежде чем осыпать [npc2.her] рот серией страстных поцелуев.",
					
					"[npc.Name] жадно [npc.verb(lean)] в [npc2.namePos] со спины, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] жадно [npc.verb(press)] в [npc2.namePos] спину, прижимая [npc2.herHim] к стене, пока [npc.she] [npc.verb(lean)] через [npc2.her] плечо, чтобы страстно поцеловать [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно прижимая [npc.her] [npc.lips+] к [npc2.nameHers], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] рот.",
					
					"[npc.Name] жадно [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] [npc.verb(plant)] серию мягких поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] с нетерпением [npc.verb(press)] против [npc2.namePos] [npc2.breasts+], прежде чем слегка наклонить [npc.her] голову набок, пока [npc.she] страстно [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый поцелуй";
		}

		@Override
		public String getActionDescription() {
			return "Грубо поцеловать [npc2.name].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вжимаясь в [npc2.namePos] спину, [npc.name] с силой [npc.verb(press)] [npc.her] [npc.lips+] к [npc2.nameHers], прежде чем жадно вогнать [npc.her] [npc.tongue] глубоко в [npc2.her] горло.",
					
					"[npc.Name] грубо [npc.verb(press)] в [npc2.namePos] спину, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] грубо [npc.verb(press)] в [npc2.namePos] спину, с силой прижимая [npc2.herHim] к стене, пока [npc.she] [npc.verb(lean)] через [npc2.her] плечо, чтобы вогнать [npc.her] [npc.tongue+] глубоко в [npc2.her] горло."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Сильно прижимая [npc.her] [npc.lips+] к [npc2.nameHers], [npc.name] жадно [npc.verb(thrust)] [npc.her] глубоко в [npc2.her] горло [npc.tongue].",
					
					"[npc.Name] грубо [npc.verb(grind)] о [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] жадно языком [npc.verb(fuck)] [npc2.her] рот.",
					
					"[npc.Name] грубо [npc.verb(press)] о [npc2.namePos] [npc2.breasts+], прежде чем вонзить [npc.her] [npc.tongue] глубоко в [npc2.her] горло."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction KISS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Поцеловать [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать целовать [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Все еще прижимаясь к спине [npc2.namePos], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.nameHers], после чего осыпает [npc2.her] серией страстных поцелуев в рот.",
					
					"[npc.Name] [npc.verb(lean)] в спину [npc2.namePos], вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] [npc.verb(press)] в спину [npc2.namePos], прижимая [npc2.herHim] к стене, пока [npc.she] [npc.verb(lean)] через [npc2.her] плечо, чтобы страстно поцеловать [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Прижимая [npc.her] [npc.lips+] к [npc2.nameHers], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] рот.",
					
					"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] [npc.verb(plant)] серию нежных поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] [npc.verb(press)] против [npc2.namePos] [npc2.breasts+], прежде чем слегка наклонить [npc.her] голову набок, пока [npc.she] страстно [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Страстный поцелуй";
		}

		@Override
		public String getActionDescription() {
			return "Жадно поцеловать [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Все еще прижимаясь к спине [npc2.namePos], [npc.name] жадно [npc.verb(press)] [npc.her] [npc.lips+] против [npc2.nameHers], прежде чем осыпать [npc2.her] рот серией страстных поцелуев.",
					
					"[npc.Name] жадно [npc.verb(lean)] в [npc2.namePos] со спины, вдыхая [npc2.her] [npc2.scent], пока [npc.she] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] жадно [npc.verb(press)] в [npc2.namePos] спину, прижимая [npc2.herHim] к стене, пока [npc.she] [npc.verb(lean)] через [npc2.her] плечо, чтобы страстно поцеловать [npc2.her] [npc2.lips+]."));
				
			} else {
			
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Жадно прижимая [npc.her] [npc.lips+] к [npc2.nameHers], [npc.name] [npc.verb(plant)] серию страстных поцелуев в [npc2.her] рот.",
					
					"[npc.Name] жадно [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], пока [npc.she] [npc.verb(plant)] серию мягких поцелуев в [npc2.her] [npc2.lips+].",
					
					"[npc.Name] с нетерпением [npc.verb(press)] против [npc2.namePos] [npc2.breasts+], прежде чем слегка наклонить [npc.her] голову набок, пока [npc.she] страстно [npc.verb(kiss)] [npc2.her] [npc2.lips+]."));
			
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться поцелую";
		}

		@Override
		public String getActionDescription() {
			return "Сопротивляться поцелую [npc2.namePos].";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Чувствуя, как в [npc.her] наворачиваются слезы, [npc.name] [npc.verb(let)] из-за [npc.a_sob+], пытаясь оттолкнуть [npc2.name] в ответ на нежелательные поцелуи [npc2.her].",
					
					"Пока [npc2.namePos] [npc2.scent+] переполняют чувства [npc.her], [npc.name] [npc.verb(let)] приглушенный [npc.sob], прежде чем отчаянно попытаться оттолкнуть [npc2.herHim] от [npc.herHim] в тщетной попытке остановить непрекращающийся натиск [npc2.her] на рот [npc.her].",
					
					"[npc.Name] отчаянно [npc.verb(try)], чтобы оттолкнуть [npc2.name], в то время как [npc.sobbing] в смятении от того, как [npc2.she] [npc2.verb(continue)] целует и прижимается к [npc.herHim]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] в ответ успокаивающее [npc2.moan], игнорируя протесты [npc.namePos], пока [npc2.she] пытается успокоить [npc.herHim], продолжая нежно целовать [npc.her] [npc.lips+].",
						
						" С мягким [npc2.moan], [npc2.name] [npc2.verb(lean)] в [npc.name], игнорируя [npc.her] [npc.sobs], пока [npc2.she] мягко, но уверенно проталкивает [npc2.her] [npc2.tongue] сквозь нежелательное [npc.lips] [npc.her] прямо в [npc.her] рот.",
						
						" Издавая успокаивающий [npc2.moan], [npc2.name] [npc2.verb(press)] [npc2.herself] против [npc.name], заглушая [npc.her] [npc.sobs] о [npc2.her] [npc2.lips+], пока [npc2.she] [npc2.verb(continue)], чтобы поцеловать [npc.herHim]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] яростный рык в ответ на протесты [npc.her], грубо втирая [npc2.her] [npc2.lips] в рот [npc.her], прежде чем с силой вогнать [npc2.her] язык глубоко в [npc.her] горло.",
						
						" С яростным рыком, [npc2.name] с силой прижимает [npc2.herself] к [npc.name], игнорируя [npc.her] [npc.sobs], пока [npc2.she] [npc2.verb(thrust)] [npc2.her] [npc2.tongue] глубоко в [npc.her] горло.",
						
						" [npc2.Name] игнорирует протесты [npc.her] и с грубым рыком [npc2.she] [npc2.verb(continue)] яростно насилует языком [npc.her] неохотное горло."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] [npc2.a_moan] в ответ, игнорируя протесты [npc.namePos], пока [npc2.she] [npc2.verb(continue)], чтобы осыпать [npc.her] [npc.lips+] страстными поцелуями.",
						
						" С [npc2.a_moan+], [npc2.name] [npc2.verb(lean)] в [npc.name], игнорируя [npc.her] [npc.sobs], пока [npc2.she] решительно втискивает [npc2.her] [npc2.tongue] мимо неохотного [npc.lips] [npc.her] прямо в рот [npc.her].",
						
						" Издавая [npc2.a_moan+], [npc2.name] [npc2.verb(press)] [npc2.herself] о [npc.name], продолжая страстно целовать [npc.herHim]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction KISS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Перестать целовать";
		}

		@Override
		public String getActionDescription() {
			return "Отстраниться от [npc2.namePos] и перестать целовать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Отстраняясь, [npc.name] грубо отталкивает [npc2.name] от [npc.herHim], завершая [npc.her] поцелуй [npc.she].",
						
						"[npc.Name] внезапно и грубо [npc.verb(push)] от [npc.herHim], прерывая [npc.her] поцелуй [npc2.name].",
						
						"[npc.Name] [npc.verb(pull)] назад от [npc2.name], после чего грубо отталкивает [npc2.herHim] от [npc.herHim], разрывая [npc.her] поцелуй."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Вглядываясь в [npc2.namePos], [npc2.eyes] [npc.verb(grin)], пока [npc.she] [npc.verb(pull)] назад, завершая [npc.her] поцелуй [npc.name].",
						
						"[npc.Name] внезапно [npc.verb(pull)] назад, завершая [npc.her] поцелуй.",
						
						"[npc.Name] [npc.verb(pull)] назад от [npc2.name], уводя [npc.her] [npc.lips+] от [npc2.hers], пока [npc.she] прерывает [npc.her] поцелуй."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(breathe)] тихий вздох облегчения, после чего она быстро возвращается к [npc2.sob] и начинает бороться с [npc.name], пока [npc2.she] [npc2.verb(try)] вырваться из [npc.her] хватки.",
							
							" С [npc2.a_sob+], [npc2.name] тщетно [npc2.verb(try)], чтобы [npc2.verb(push)] [npc.name] еще дальше, протестуя и извиваясь от дискомфорта, пока [npc.she] [npc.verb(hold)] [npc2.herHim] крепко на месте."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] тихий скулеж, когда [npc.name] [npc.verb(pull)] назад, сигнализируя о отчаянной потребности [npc2.her] в большем внимании.",
							
							" Отчаянный стон вырывается из меж [npc2.namePos] [npc2.lips], выдавая [npc2.her] жажду еще большего [npc.namePos] внимания."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
