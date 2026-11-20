package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.4
 * @version 0.4.10.7
 * @author Innoxia
 */
public class SadisticActions {
	
	
	// ASS ACTIONS:
	
	
	/**
	 * A non-sadistic version of 'slap ass'.
	 */
	public static final SexAction SPANK_ASS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return SLAP_ASS.isBaseRequirementsMet()
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST);
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getActionTitle() {
			return "Отшлепать [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			return "Начать игриво шлепать [npc2.namePos] [npc2.ass+].";
		}
		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "[npc.her] [npc.cock+] глубоко внутри [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] вниз и игриво [npc.verb(grope)] [npc2.her] [npc2.ass+], после чего приняться наносить серию крепких шлепков по обнаженным щечкам [npc2.her].";
				} else {
					tailSpecial1 = "[npc.her] [npc.cock+] глубоко внутри [npc2.namePos] [npc2.pussy+], [npc.name] игриво [npc.verb(grab)] основание [npc2.her] [npc2.tail+] и [npc.verb(pull)] вверх, высоко задирая [npc2.her] [npc2.ass+] в воздух, прежде чем начать наносить серию крепких шлепков по обнаженным щечкам [npc2.her].";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Все еще усердно впахивает в [npc2.her] [npc2.pussy+], [npc.name] [npc.moansVerb], что [npc.sheIs] собирается занять [npc2.name] на месте [npc2.her], прежде чем начать воплощать [npc.her] угрозу, игриво шлепая по обнаженным ягодицам [npc2.her].";
				} else {
					tailSpecial2 = "Все еще вколачивая [npc2.pussy+] в [npc2.her], [npc.name] [npc.verb(grab)] основание [npc2.namePos] [npc2.tail+] одним [npc.hand], после чего игриво задирая [npc2.her] [npc2.ass+] высоко в воздух и начиная крепко шлепать по обнаженным щечкам [npc2.her].";
				}

				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Вгоняя [npc.her] [npc.cock+] глубоко внутрь [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(use)] одну [npc.hand], чтобы удерживать [npc2.herHim] неподвижно, а другой [npc.her] наносит серию звонких шлепков по [npc2.her] обнаженным ягодицам.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc.name] [npc.verb(start)] игриво шлепая [npc2.her] [npc2.ass+] в такт [npc.her] толчкам в [npc2.her] [npc2.pussy+].",
							isTargetedCharacterInanimate()?null
								:"Пока [npc.name] [npc.verb(continue)] долбит [npc2.namePos] [npc2.pussy+], [npc.she] [npc.verb(reach)] вниз и [npc.verb(start)], чтобы игриво шлепнуть [npc2.her] [npc2.ass+], от чего [npc2.herHim] начинает [npc2.verb(squirm)] и [npc2.verb(squeal)] в ответ.");
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Вгоняя [npc.her] [npc.cock+] глубоко внутрь [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] вниз и игриво [npc.verb(grope)] [npc2.her] [npc2.ass+], прежде чем начать наносить серию крепких шлепков по обнаженным ягодицам [npc2.her].";
				} else {
					tailSpecial1 = "Вгоняя [npc.her] [npc.cock+] глубоко внутрь [npc2.namePos] [npc2.asshole+], [npc.name] игриво [npc.verb(grab)] основание [npc2.her] [npc2.tail+] и [npc.verb(pull)] вверх, высоко задирая [npc2.her] [npc2.ass+] в воздух, прежде чем начать наносить серию крепких шлепков по обнаженным ягодицам [npc2.her].";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Все еще впахивая в [npc2.her] [npc2.asshole+], [npc.name] [npc.moansVerb], что [npc.sheIs] заставит [npc2.name] встать на [npc2.her] место, прежде чем начать воплощать в жизнь [npc.her] угрозу, игриво шлепая по обнаженным ягодицам [npc2.her].";
				} else {
					tailSpecial2 = "Все еще усердно [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(grab)] основание [npc2.namePos] [npc2.tail+] одним [npc.hand], после чего игриво задирая [npc2.her] [npc2.ass+] высоко в воздух и начиная крепко шлепать по [npc2.her] обнаженным ягодицам.";
				}
				
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Вгоняя [npc.her] [npc.cock+] глубоко внутрь [npc2.namePos] [npc2.asshole], [npc.name] [npc.verb(use)] одним [npc.hand], чтобы удержать [npc2.herHim] на месте, в то время как другой [npc.her] наносит серию крепких шлепков по [npc2.her] обнаженным ягодицам.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+], пока [npc.name] [npc.verb(start)] игриво шлепает [npc2.her] [npc2.ass+] в такт [npc.her] толчкам в [npc2.her] [npc2.asshole+].",
							isTargetedCharacterInanimate()?null
								:"Пока [npc.name] [npc.verb(continue)] долбит по [npc2.namePos] [npc2.asshole+], [npc.she] [npc.verb(reach)] вниз и [npc.verb(start)], чтобы игриво шлепать по [npc2.her] [npc2.ass+], отчего [npc2.herHim] начинает [npc2.verb(squirm)] и [npc2.verb(squeal)] в ответ.");
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Издавая [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз и [npc.verb(grab)] за талию [npc2.namePos], используя одну [npc.hand(true)], чтобы удержать [npc2.herHim] на месте, а другую [npc.her], чтобы нанести серию крепких шлепков по [npc2.her] [npc2.ass+].";
				} else {
					tailSpecial1 = "Издавая [npc.a_moan+], [npc.name] игриво [npc.verb(grab)] основание [npc2.her] [npc2.tail+] и [npc.verb(pull)] вверх, высоко задирая [npc2.her] [npc2.ass+] в воздух, прежде чем начать отвешивать серию звонких шлепков по [npc2.her] обнаженным ягодицам.";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name] [npc.verb(reach)] вниз и одной рукой [npc.verb(grab)] за талию [npc2.namePos], крепко сжимая [npc2.herHim] в [npc.her] хватке, пока [npc.she] [npc.verb(start)], чтобы игриво шлепать по [npc2.her] обнаженным ягодицам.";
				} else {
					tailSpecial2 = isTargetedCharacterInanimate()?null
									:"[npc.Name] [npc.verb(reach)] вниз и [npc.verb(grab)] основание [npc2.namePos] [npc2.tail+], заставляя [npc2.herHim] удивленно вскрикнуть, когда [npc.she] игриво [npc.verb(pull)] вверх, вынуждая [npc2.herHim] подбросить [npc2.her] [npc2.ass+] высоко в воздух, пока [npc.name] [npc.verb(start)], чтобы крепко шлепать по [npc2.her] обнаженным ягодицам.";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name] [npc.verb(use)] одной [npc.hand], чтобы удержать [npc2.name] на месте, в то время как другой [npc.her] наносит серию звонких шлепков по [npc2.her] обнаженным ягодицам.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] игриво [npc.verb(start)] шлепает по [npc2.her] [npc2.ass+].",
							isTargetedCharacterInanimate()?null
								:"[npc.Name] [npc.verb(reach)] вниз и [npc.verb(start)], чтобы игриво шлепать по [npc2.her] [npc2.ass+], отчего [npc2.herHim] в ответ начинает [npc2.verb(squirm)] и [npc2.verb(squeal)].");
			}
		}
	};
	
	public static final SexAction SLAP_ASS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean assFinger = false;
			boolean assFingerReversed = false;
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.LYING_DOWN)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.BACK_TO_WALL)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.OVER_DESK_BACK)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.SITTING)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.SITTING_IN_LAP)) {
				return false;
			}
			try {
				assFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(target))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.ASS);
			} catch(Exception ex) {
				// No available finger-ass actions, so can't reach ass
			}
			try {
				assFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(target)).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.ASS).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-ass actions, so can't reach ass
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(target)
					&& (assFinger || assFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public String getActionTitle() {
			return "Шлепать по заднице";
		}

		@Override
		public String getActionDescription() {
			return "Начать шлепать [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Херача [npc.her] [npc.cock+] глубоко внутри [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] вниз и грубо [npc.verb(grope)] [npc2.her] [npc2.ass+], после чего начинает отвешивать серию звонких пощечин по обнаженным щекам [npc2.her].";
				} else {
					tailSpecial1 = "Херача [npc.her] [npc.cock+] глубоко внутри [npc2.namePos] [npc2.pussy+], [npc.name] грубо [npc.verb(grab)] основание [npc2.her] [npc2.tail+] и [npc.verb(yank)] вверх, высоко задирая [npc2.her] [npc2.ass+] в воздух, прежде чем приняться отвешивать серию звонких пощечин по обнаженным щекам [npc2.her].";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Все еще усердно впахивая в [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(growl)] этот [npc.sheIs], собираясь поместить [npc2.name] на [npc2.her] место, прежде чем начать воплощать в жизнь [npc.her] угрозу, агрессивно шлепая по обнаженным ягодицам [npc2.her].";
				} else {
					tailSpecial2 = "Все еще вгрызаясь в [npc2.her] [npc2.pussy+], [npc.name] [npc.verb(grab)] основание [npc2.namePos] [npc2.tail+] одним [npc.hand], грубо задирая [npc2.her] [npc2.ass+] высоко в воздух, после чего начинает агрессивно шлепать по обнаженным щекам [npc2.her].";
				}
				
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Вгоняя [npc.her] [npc.cock+] глубоко внутрь [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(use)] одну [npc.hand], чтобы удерживать [npc2.herHim] неподвижно, а другой [npc.her] наносит серию хлестких шлепков по [npc2.her] обнаженным ягодицам.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] извлекает из [npc2.a_moan+] [npc.name], [npc.verb(start)] шлепая [npc2.her] [npc2.ass+] в такт [npc.her] мощным толчкам в [npc2.her] [npc2.pussy+].",
							isTargetedCharacterInanimate()?null
								:"Пока [npc.name] [npc.verb(continue)] долбит в [npc2.namePos] [npc2.pussy+], [npc.she] [npc.verb(reach)] вниз и [npc.verb(start)], чтобы грубо шлепать [npc2.her] [npc2.ass+], довольно рыча, пока [npc2.she] [npc2.verb(squirm)] и [npc2.verb(squeal)] под градом жалящих ударов [npc.her].");
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] вниз и грубо [npc.verb(grope)] [npc2.her] [npc2.ass+], прежде чем начать наносить серию жалящих шлепков по обнаженным щекам [npc2.her].";
				} else {
					tailSpecial1 = "Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], [npc.name] грубо [npc.verb(grab)] основание [npc2.her] [npc2.tail+] и [npc.verb(yank)] вверх, высоко задирая [npc2.her] [npc2.ass+] в воздух, прежде чем начать наносить серию жалящих шлепков по обнаженным щекам [npc2.her].";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "Все еще вгрызаясь в [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(growl)] вниз так, что [npc.sheIs] поставит [npc2.name] на [npc2.her] место, прежде чем начать воплощать [npc.her] угрозу, агрессивно шлепая по обнаженным ягодицам [npc2.her].";
				} else {
					tailSpecial2 = "Все так же вколачивая [npc2.her] [npc2.asshole+], [npc.name] [npc.verb(grab)] основание [npc2.namePos] [npc2.tail+] одним [npc.hand], грубо задирая [npc2.her] [npc2.ass+] высоко в воздух, прежде чем начать агрессивно шлепать по [npc2.her] обнаженным щекам.";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"Вбивая [npc.her] [npc.cock+] глубоко внутрь [npc2.namePos] [npc2.asshole], [npc.name] [npc.verb(use)] одним [npc.hand], чтобы удержать [npc2.herHim] на месте, в то время как другой [npc.her] наносит серию жалящих шлепков по [npc2.her] обнаженным ягодицам.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] выбивает [npc2.a_moan+] из [npc.name], [npc.verb(start)] шлепая [npc2.her] [npc2.ass+] в такт [npc.her] мощным толчкам в [npc2.her] [npc2.asshole+].",
							isTargetedCharacterInanimate()?null
								:"Пока [npc.name] [npc.verb(continue)] долбит по [npc2.namePos] [npc2.asshole+], [npc.she] [npc.verb(reach)] вниз и [npc.verb(start)], чтобы грубо шлепнуть [npc2.her] [npc2.ass+], довольно рыча, когда [npc2.she] [npc2.verb(squirm)] и [npc2.verb(squeal)] под [npc.her] жалящими ударами.");
			
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "Рыча прямо в [npc2.namePos] [npc2.ear+], [npc.name] [npc.verb(reach)] вниз и [npc.verb(grab)] [npc2.her] талию, используя одну руку, чтобы удержать [npc2.herHim] на месте, а другой [npc.her] нанося серию жалящих шлепков по [npc2.her] [npc2.ass+].";
				} else {
					tailSpecial1 = "Рыча, [npc2.namePos] впивается в [npc2.ear], [npc.name] грубо [npc.verb(grab)] основание [npc2.her] [npc2.tail+] и [npc.verb(yank)] вверх, высоко задирая [npc2.her] [npc2.ass+] в воздух, прежде чем приняться отвешивать серию жалящих шлепков по обнаженным щекам [npc2.her].";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name] [npc.verb(reach)] вниз и одной рукой [npc.verb(grab)] [npc2.namePos] талию, крепко сжимая [npc2.herHim] в [npc.her] хватке, пока [npc.she] [npc.verb(start)] агрессивно шлепать по [npc2.her] обнаженным щекам.";
				} else {
					tailSpecial2 = isTargetedCharacterInanimate()?null
							:"[npc.Name] [npc.verb(reach)] вниз и [npc.verb(grab)] основание [npc2.namePos] [npc2.tail+], заставляя [npc2.herHim] издать удивленный вскрик, когда [npc.she] грубо [npc.verb(yank)] вверх, вынуждая [npc2.herHim] задирать [npc2.her] [npc2.ass+] высоко в воздух, пока [npc.name] [npc.verb(start)] агрессивно шлепать по [npc2.her] обнаженным щекам.";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(start)] грубо шлепает по [npc2.her] [npc2.ass+], а [npc.name] [npc.verb(find)] [npc.herself], довольно ухмыляясь, пока [npc.she] [npc.verb(watch)] [npc2.herHim] корчится и вскрикивает под [npc.her] хлесткими ударами.",
							isTargetedCharacterInanimate()?null
								:"[npc2.Name] [npc2.verb(let)] out a startled wail as [npc.name] [npc.verb(start)] to roughly slap [npc2.her] [npc2.ass+], and [npc.name] quickly [npc.verb(find)] [npc.herself] grinning in glee as [npc.she] [npc.verb(watch)] [npc2.herHim] squirm and wail beneath [npc.her] relentless blows.",
							isTargetedCharacterInanimate()?null
								:"[npc.Name] [npc.verb(growl)] на эту [npc.sheIs], собираясь занять [npc2.name] на [npc2.her] месте, прежде чем начать агрессивно шлепать [npc2.her] [npc2.ass+], ухмыляясь сверху вниз на покорную форму [npc2.her], пока та визжит и стонет под [npc.her] беспощадными ударами [npc2.she].");
			}
		}
	};
	
	
	// FACE ACTIONS:
	
	
	public static final SexAction SLAP_FACE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public String getActionTitle() {
			return "Шлепнуть по лицу";
		}

		@Override
		public String getActionDescription() {
			return "Шлепнуть [npc2.namePos] по лицу, чтобы поставить [npc2.herHim] на [npc2.her] место.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Желая поставить [npc2.name] на [npc2.her] место, [npc.name] [npc.verb(lift)] [npc.her] [npc.hand], прежде чем замахнуться и нанести резкую, жгучую пощечину по [npc2.her] лицу.",
					"Подняв [npc.her] [npc.hand], [npc.name] [npc.verb(swing)] вниз, нанося резкий шлепок по [npc2.namePos] лицу, чтобы поставить [npc2.herHim] на [npc2.her] место.",
					"Желая напомнить [npc2.name], кто здесь главный, [npc.name] [npc.verb(raise)] [npc.her] [npc.hand], прежде чем резко опуститься и нанести звонкую пощечину по [npc2.her] лицу."));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" В момент контакта из рта [npc2.namePos] вырывается явно возбужденный вскрик, давая [npc.name] понять, что [npc2.sheIs] получает мазохистское удовольствие от подобного обращения.",
							" Похотливый визг, вырывающийся из рта [npc2.namePos], дает понять любому, что [npc2.sheIs] заводится от такого унизительного обращения.",
							" Вместо болезненного крика [npc2.name] [npc2.verb(let)] похотливый стон, давая [npc.name] понять, что [npc2.sheIs] — мазохистка, которую возбуждает подобное насилие."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" В момент контакта из рта [npc2.namePos] вырывается протестующий визг,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" что является именно той реакцией, которую ты искал."
												:" и по зловещей ухмылке, застывшей на лице [npc.namePos], становится ясно, что это была именно та реакция, которую [npc.she] [npc.was] искала."),
								" Болезненный визг, который тут же срывается с губ [npc2.namePos], — это именно то, что [npc.name] [npc.was] искал, и [npc.she] не может не ухмыляться, когда [npc.she] [npc.verb(see)] слезы наворачиваются на"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" твоя [npc2.eyes+]."
												:" [npc2.eyes+] подчиненной сучки [npc.her]."),
								" Издав шокированный крик, [npc2.namePos] [npc2.eyes] наполняются слезами, давая [npc.name] понять, что [npc.her] насилие производит именно тот эффект, которого [npc.she] [npc.was] ищет."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" В момент контакта из рта [npc2.namePos] вырывается протестующий крик,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" что является именно той реакцией, которую ты искал."
												:" и по зловещей ухмылке, застывшей на лице [npc.namePos], становится ясно, что это была именно та реакция, которую [npc.she] [npc.was] искала."),
								" Болезненный возглас, мгновенно сорвавшийся с губ [npc2.namePos], — это именно то, что [npc.name] [npc.was], и [npc.she] не может сдержать ухмылки, видя, как [npc.she] [npc.verb(see)] шокированный взгляд в"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" твоя [npc2.eyes+]."
												:" [npc2.eyes+] подчиненной сучки [npc.her]."),
								" [npc2.Name] мгновенно [npc2.verb(let)] шокированный крик, давая [npc.name] понять, что [npc.her] приносит именно тот эффект, который [npc.she] [npc.was]."));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SPIT_FACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			Map<InventorySlot, List<AbstractClothing>> concealedMap = Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction());
			if(concealedMap.containsKey(InventorySlot.MOUTH) && concealedMap.containsKey(InventorySlot.EYES)) {
				return false; // If mouth and eyes are concealed, treat face as being concealed and so unavailable
			}
			
			boolean mouthTongue = false;
			boolean mouthTongueReversed = false;
			try {
				mouthTongue = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.TONGUE).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthTongueReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.TONGUE);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthTongue || mouthTongueReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		
		@Override
		public String getActionTitle() {
			return "Плюнуть в лицо";
		}

		@Override
		public String getActionDescription() {
			return "Плюнуть [npc2.namePos] в лицо, чтобы показать [npc2.herHim], что [npc2.sheIs] ничтожна.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Желая показать [npc2.name], что [npc2.sheIs] — никчемная сучка, [npc.name] [npc.verb(lean)] к [npc2.herHim], прежде чем плюнуть [npc2.her] в лицо.",
					"Наклонившись к [npc2.name], [npc.name] [npc.verb(purse)] [npc.her] [npc.lips+], прежде чем плюнуть прямо в лицо [npc2.her].",
					"Желая напомнить [npc2.name], насколько ничтожна [npc2.sheIsFull], [npc.name] [npc.verb(lean)] к [npc2.herHim], прежде чем сжать [npc.her] [npc.lips+] и плюнуть прямо в лицо [npc2.her]."));

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" Очевидно возбужденный [npc2.moan] срывается с губ [npc2.namePos], когда сгусток слюны разлетается по щеке [npc2.her], давая [npc.name] понять, что [npc2.sheIs] получает мазохистское удовольствие от подобного обращения.",
							" Похотливый визг, вырывающийся из рта [npc2.namePos], дает понять любому, что [npc2.sheIs] заводится от такого унизительного обращения.",
							" Вместо брезгливого крика [npc2.name] [npc2.verb(let)] возбужденный [npc2.moan], давая [npc.name] понять, что [npc2.sheIs] — мазохистка, которую заводит такое обращение."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается брезгливый крик, когда сгусток слюны разлетается по щеке [npc2.her],"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" что является именно той реакцией, которую ты искал."
												:" и по зловещей ухмылке, застывшей на лице [npc.namePos], становится ясно, что это была именно та реакция, которую [npc.she] [npc.was] искала."),
								" Ужаснейшие протесты, которые [npc2.name] тут же [npc2.verb(make)], — это именно то, что [npc.name] [npc.was] ищет, и [npc.she] не может сдержать ухмылки, глядя, как в глазах [npc.she] [npc.verb(see)] слезы,"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" твоя [npc2.eyes+]."
												:" [npc2.eyes+] подчиненной сучки [npc.her]."),
								" Издав шокированный крик, [npc2.namePos] [npc2.eyes] наполняются слезами, давая [npc.name] понять, что [npc.her] насилие производит именно тот эффект, которого [npc.she] [npc.was] ищет."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается брезгливый крик, когда сгусток слюны разлетается по щеке [npc2.her],"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" что является именно той реакцией, которую ты искал."
												:" и по зловещей ухмылке, застывшей на лице [npc.namePos], становится ясно, что это была именно та реакция, которую [npc.she] [npc.was] искала."),
								" Ужаснейшие протесты, которые [npc2.name] тут же [npc2.verb(make)], — это именно то, что [npc.name] [npc.was] ищет, и [npc.she] не может сдержать ухмылки, глядя на шокированный взгляд [npc.she], когда тот [npc.verb(see)]"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" твоя [npc2.eyes+]."
												:" [npc2.eyes+] подчиненной сучки [npc.her]."),
								" [npc2.Name] мгновенно [npc2.verb(let)] шокированный крик, давая [npc.name] понять, что [npc.her] приносит именно тот эффект, который [npc.she] [npc.was]."));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction CHOKE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}

			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public String getActionTitle() {
			return "Душить";
		}

		@Override
		public String getActionDescription() {
			return "Схватить и сжать шею [npc2.namePos], чтобы задушить [npc2.herHim].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterPerformingAction())) {
				sb.append(UtilText.returnStringAtRandom(
						"Продолжая вбивать [npc.her] [npc.cock+] туда-сюда в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] вверх, чтобы схватить [npc2.her] за шею, а затем сжать её и задушить [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] вверх, чтобы схватить [npc2.namePos] за шею, и, всё еще мощно вколачивая [npc2.her] в [npc2.pussy+], [npc.she] грубо [npc.verb(squeeze)] вниз, чтобы задушить [npc2.herHim].",
						"Slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.her] neck, squeezing down and choking [npc2.herHim] as [npc.she] [npc.verb(pull)] back and [npc.verb(continue)] roughly fucking [npc2.herHim]."));
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterPerformingAction())) {
				sb.append(UtilText.returnStringAtRandom(
						"Продолжая с силой вбивать [npc.her] [npc.cock+] в [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] вверх, чтобы схватить [npc2.her] за шею, а затем сжать её и начать душить [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] вверх, чтобы схватить [npc2.namePos] за шею, и, продолжая яростно вколачивать [npc2.her] [npc2.asshole+], [npc.she] грубо [npc.verb(squeeze)] вниз, чтобы задушить [npc2.herHim].",
						"Slamming [npc.her] [npc.cock+] deep into [npc2.namePos] [npc2.asshole+], [npc.name] [npc.verb(reach)] up and [npc.verb(grab)] [npc2.her] neck, squeezing down and choking [npc2.herHim] as [npc.she] [npc.verb(pull)] back and [npc.verb(continue)] roughly fucking [npc2.herHim]."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"С садистской ухмылкой на [npc.her] лице, [npc.name] [npc.verb(reach)] вверх, чтобы схватить [npc2.name] за [npc2.her] шею, а затем сжать её и начать душить [npc2.herHim].",
						"[npc.Name] [npc.verb(reach)] вверх, чтобы схватить [npc2.namePos] за шею, и, издав садистский смешок, [npc.she] грубо [npc.verb(squeeze)] вниз, чтобы задушить [npc2.herHim].",
						"С жестоким смехом, [npc.name] [npc.verb(reach)] вверх и [npc.verb(grab)] [npc2.name] за [npc2.her] шею, прежде чем садистски сжать её и начать душить [npc2.herHim]."));
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" Булькающие, захлебывающиеся звуки, которые [npc2.name] [npc2.verb(start)] издавать в ответ, звучат отчетливо похотливо, давая [npc.name] понять, что [npc2.sheIs] возбуждается от этого насилия.",
							" Похотливые, булькающие [npc2.moans], которые [npc2.name] тут же [npc2.verb(start)], дают более чем достаточно поводов понять, что [npc2.sheIs] — мазохистка, и такое обращение лишь сильнее ее возбуждает.",
							" Прерывистые всхлипы, которые [npc2.name] [npc2.verb(begin)], перемешиваются с несколькими похотливыми [npc2.moans], давая [npc.name] понять, что [npc2.sheIs] получает мазохистское удовольствие от подобного насилия."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается серия булькающих, прерывистых звуков,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" что является именно той реакцией, которую ты искал."
												:" и по зловещей ухмылке, застывшей на лице [npc.namePos], становится ясно, что это была именно та реакция, которую [npc.she] [npc.was] искала."),
								" Мучительные, булькающие крики, которые [npc2.name] [npc2.verb(start)], — это именно то, что [npc.name] [npc.was], и [npc.she] не может сдержать ухмылки, видя, как в [npc.she] [npc.verb(see)] наворачиваются слезы,"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" твоя [npc2.eyes+]."
												:" [npc2.eyes+] подчиненной сучки [npc.her]."),
								" Издавая серию судорожных всхлипов, [npc2.namePos] [npc2.eyes] быстро наполняются слезами, давая [npc.name] понять, что [npc.her] насилие производит именно тот эффект, которого [npc.she] [npc.was] и добивается."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается серия булькающих, прерывистых звуков,"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?" что является именно той реакцией, которую ты искал."
												:" и по зловещей ухмылке, застывшей на лице [npc.namePos], становится ясно, что это была именно та реакция, которую [npc.she] [npc.was] искала."),
								" Мучительные, булькающие крики, которые [npc2.name] немедленно [npc2.verb(make)], — это именно то, что [npc.name] [npc.was], и [npc.she] не может сдержать ухмылки, видя, как [npc.she] [npc.verb(see)] шокированный взгляд в"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?" твоя [npc2.eyes+]."
												:" [npc2.eyes+] подчиненной сучки [npc.her]."),
								" [npc2.Name] тут же [npc2.verb(let)] прерывистый всхлип, давая [npc.name] понять, что [npc.her] издевательства производят именно тот эффект, который [npc.she] [npc.was] искала."));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	
	// PUSSY ACTIONS:
	
	
	public static final SexAction SLAP_PUSSY = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean fingerPussy = false;
			boolean fingerPussyReversed = false;
			try {
				fingerPussy = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
				// No available finger-pussy actions, so unavailable
			}
			try {
				fingerPussyReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.VAGINA).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available pussy-finger actions, so unavailable
			}
			return SexAreaOrifice.VAGINA.isFree(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (fingerPussy || fingerPussyReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		@Override
		public String getActionTitle() {
			return "Шлепнуть по киске";
		}
		@Override
		public String getActionDescription() {
			return "Нанести [npc2.namePos] киске несколько резких шлепков.";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(tease)] [npc.her] [npc.fingers+] над [npc2.namePos] [npc2.pussy+], прежде чем внезапно нанести серию резких, болезненных шлепков по женскому лону [npc2.her].",
					"Опустив [npc.her] [npc.hand] к паху [npc2.namePos], [npc.name] [npc.verb(start)] грубо шлепает [npc2.her] [npc2.pussy+].",
					"Протянув руку к паху [npc2.namePos], [npc.name] на мгновение с силой [npc.verb(grope)] [npc2.herHim], прежде чем внезапно нанести серию резких шлепков по [npc2.her] [npc2.pussy+]."));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" В момент контакта из рта [npc2.namePos] вырывается отчетливый возбужденный вскрик, давая [npc.name] понять, что [npc2.sheIs] получает мазохистское удовольствие от того, что [npc2.her] [npc2.pussy] шлепают.",
							" Возбужденного визга, вырывающегося из уст [npc2.namePos], достаточно, чтобы любой понял: [npc2.sheIs] заводится от того, что [npc2.her] [npc2.pussy] подвергают грубому обращению.",
							" Вместо болезненного крика [npc2.name] [npc2.verb(let)] возбужденный стон, давая [npc.name] понять, что [npc2.sheIs] — мазохистка, которую заводит, когда [npc.her] [npc2.pussy] шлепают."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается протестующий вскрик, и [npc2.she] [npc2.verb(squirm)] и [npc2.verb(squeal)] от дискомфорта, пока [npc2.her] [npc2.pussy] непрерывно шлепают.",
								" [npc2.Name] [npc2.verb(writhe)] под болезненными ударами и [npc2.verb(let)] серию полных боли воплей, пока [npc2.her] [npc2.pussy] подвергается [npc.namePos] издевательствам.",
								" Издав шокированный крик, [npc2.namePos] [npc2.eyes] наполняются слезами, пока [npc2.she] [npc2.verb(struggle)] и [npc2.verb(cry)], умоляя [npc.name] перестать шлепать [npc2.her] [npc2.pussy]."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается протестующее рычание, и [npc2.she] [npc2.verb(squirm)] и [npc2.verb(grunt)] от дискомфорта, пока [npc2.her] [npc2.pussy] непрерывно шлепают.",
								" [npc2.Name] [npc2.verb(writhe)] под болезненными ударами и [npc2.verb(let)] серию полных боли стонов, пока [npc2.her] [npc2.pussy] подвергается [npc.namePos] издевательствам.",
								" Издав шокированный крик, [npc2.name] [npc2.verb(struggle)] и [npc2.verb(shout)], чтобы [npc.name] оставил(а) [npc2.her] [npc2.pussy] в покое."));
					}
				}
			}
			
			return sb.toString();
		}
	};

	public static final SexAction PINCH_CLIT = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean fingerPussy = false;
			boolean fingerPussyReversed = false;
			try {
				fingerPussy = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.CLIT);
			} catch(Exception ex) {
				// No available finger-pussy actions, so unavailable
			}
			try {
				fingerPussyReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.CLIT).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available pussy-finger actions, so unavailable
			}
			return SexAreaPenetration.CLIT.isFree(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (fingerPussy || fingerPussyReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		@Override
		public String getActionTitle() {
			return "Ущипнуть клитор";
		}
		@Override
		public String getActionDescription() {
			return "Жестоко ущипнуть [npc2.namePos] [npc.clit+].";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"[npc.NamePos] [npc.fingers+] нацеливается на [npc2.namePos] [npc2.pussy+] и без предупреждения [npc.she] [npc.verb(start)], чтобы болезненно ущипнуть и жестоко щелкнуть по [npc2.her] [npc2.clit+].",
					"Доминантно прижав [npc.her] [npc.hand] к паху [npc2.namePos], [npc.name] внезапно [npc.verb(pinch)] [npc2.her] [npc2.clit+] изо всех сил, после чего несколько раз резко и быстро щелкает по нему.",
					"Протянув руку к паху [npc2.namePos], [npc.name] [npc.verb(give)] [npc2.her] [npc2.clit+] несколько раз грубо щелкнув, а затем болезненно ущипнув и потянув за него."));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" Похотливый [npc.moan] вырывается из рта [npc.namePos], давая [npc2.name] понять, что [npc.sheIs] получает мазохистское удовольствие от того, что [npc2.her] [npc2.clit] подвергается насилию.",
							" Похотливый [npc.moan], вырвавшийся из уст [npc2.namePos], позволяет любому понять, что [npc2.sheIs] возбуждается от того, что [npc2.her] [npc2.clit] наказывают.",
							" Вместо болезненного крика [npc2.name] [npc2.verb(let)] похотливый стон, ясно давая понять, что [npc2.sheIs] — мазохист, который возбуждается от того, что [npc.her] [npc2.clit] подвергается насилию."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" Из уст [npc2.namePos] вырывается протестующий крик, и [npc2.she] [npc2.verb(squirm)] и [npc2.verb(squeal)] от боли, пока [npc2.her] [npc2.clit] грубо истязают.",
								" [npc2.Name] [npc2.verb(writhe)] под этим болезненным воздействием, и [npc2.verb(let)] серию протестующих визгов, пока [npc2.her] [npc2.clit] подвергается [npc.namePos] насилию.",
								" После шокированного вскрика на глазах [npc2.namePos] [npc2.eyes] наворачиваются слезы, пока [npc2.she] [npc2.verb(struggle)] и [npc2.verb(cry)], чтобы [npc.name] прекратил наказывать [npc2.her] [npc2.clit]."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" Из уст [npc2.namePos] вырывается протестующее рычание, и [npc2.she] [npc2.verb(squirm)] и [npc2.verb(grunt)] от дискомфорта, пока [npc2.her] [npc2.clit] грубо истязают.",
								" [npc2.Name] [npc2.verb(writhe)] под этим болезненным воздействием, и [npc2.verb(let)] серию протестующих стонов, пока [npc2.her] [npc2.clit] подвергается [npc.namePos] издевательствам.",
								" Издав шокированный крик, [npc2.name] [npc2.verb(struggle)] и [npc2.verb(shout)], чтобы [npc.name] оставил [npc2.her] [npc2.clit] в покое."));
					}
				}
			}
			
			return sb.toString();
		}
	};

	
	// COCK ACTIONS:
	
	
	public static final SexAction SLAP_COCK = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean fingerPenis = false;
			boolean fingerPenisReversed = false;
			try {
				fingerPenis = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.PENIS);
			} catch(Exception ex) {
				// No available finger-penis actions, so unavailable
			}
			try {
				fingerPenisReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.PENIS).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available penis-finger actions, so unavailable
			}
			return SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (fingerPenis || fingerPenisReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		@Override
		public String getActionTitle() {
			return "Шлепать член";
		}
		@Override
		public String getActionDescription() {
			return "Наказать [npc2.namePos] член, нанося ему несколько резких шлепков.";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()) {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(tease)] [npc.her] [npc.fingers+] над [npc2.namePos] [npc2.cock+], прежде чем внезапно нанести серию резких, болезненных шлепков.",
						"Опустив [npc.her] [npc.hand] к [npc2.namePos] паху, [npc.name] [npc.verb(start)] грубо шлепает [npc2.her] [npc2.cock+].",
						"Потянувшись к [npc2.namePos] паху, [npc.name] на мгновение с силой [npc.verb(grope)] [npc2.her] [npc2.cock+], а затем внезапно наносит серию резких шлепков."));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(tease)] [npc.her] [npc.fingers+] над [npc2.namePos] [npc2.balls+], прежде чем внезапно нанести серию резких, болезненных шлепков по [npc2.her] [npc2.cock+].",
						"Опустив [npc.her] [npc.hand] к [npc2.namePos] паху, [npc.name] [npc.verb(start)] грубо шлепает [npc2.her] [npc2.cock+].",
						"Потянувшись к [npc2.namePos] паху, [npc.name] на мгновение с силой [npc.verb(grope)] [npc2.her] [npc2.balls+], а затем внезапно наносит серию резких шлепков по [npc2.her] [npc2.cock+]."));
			}
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" В момент контакта из уст [npc2.namePos] вырывается отчетливый, возбужденный вскрик, дающий [npc.name] понять, что [npc2.sheIs] получает мазохистское удовольствие от того, что [npc2.her] [npc2.cock] шлепает.",
							" Похотливый визг, вырывающийся из уст [npc2.namePos], дает понять любому, что [npc2.sheIs] получает возбуждение от того, что [npc2.her] [npc2.cock] подвергается насилию.",
							" Вместо болезненного крика [npc2.name] [npc2.verb(let)] похотливый стон, давая [npc.name] понять, что [npc2.sheIs] — мазохистка, которую возбуждает, когда [npc.her] [npc2.cock] шлепают."));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается протестующий визг, и [npc2.she] [npc2.verb(squirm)] и [npc2.verb(squeal)] от дискомфорта, пока [npc2.her] непрерывно [npc2.cock].",
								" [npc2.Name] [npc2.verb(writhe)] под болезненными ударами, и [npc2.verb(let)] серия мучительных воплей, пока [npc2.her] [npc2.cock] подвергается [npc.namePos] насилию.",
								" После шокированного крика на глазах у [npc2.namePos] [npc2.eyes] наворачиваются слезы, пока [npc2.she] [npc2.verb(struggle)] и [npc2.verb(cry)], чтобы [npc.name] прекратил шлепать [npc2.her] [npc2.cock]."));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								" Из рта [npc2.namePos] вырывается протестующее рычание, и [npc2.she] [npc2.verb(squirm)] и [npc2.verb(grunt)] от дискомфорта, пока [npc2.her] непрерывно [npc2.cock].",
								" [npc2.Name] [npc2.verb(writhe)] под болезненными ударами, и [npc2.verb(let)] серию стонущих звуков, пока [npc2.her] [npc2.cock] подвергается [npc.namePos] издевательствам.",
								" Издав шокированный крик, [npc2.name] [npc2.verb(struggle)] и [npc2.verb(shout)], умоляя [npc.name] оставить [npc2.her] [npc2.cock] в покое."));
					}
				}
			}
			
			return sb.toString();
		}
	};
}
