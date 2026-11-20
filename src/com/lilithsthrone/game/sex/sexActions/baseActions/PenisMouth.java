package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
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
 * @since 0.1.79
 * @version 0.3.4.5
 * @author Innoxia
 */
public class PenisMouth {
	
	// -- Methods for multiple ongoing characters:
	
	private static List<GameCharacter> getOngoingCharacters(GameCharacter characterReceivingBlowjob) {
		return new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterReceivingBlowjob, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
	}

	private static List<GameCharacter> getMultipleOngoingCharactersForParsing(GameCharacter characterReceivingBlowjob) {
		List<GameCharacter> characters = Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		for(GameCharacter c : getOngoingCharacters(characterReceivingBlowjob)) {
			if(!characters.contains(c)) {
				characters.add(c);
			}
		}
		return characters;
	}
	
	private static String getOngoingNames(GameCharacter characterReceivingBlowjob, GameCharacter... charactersToExclude) {
		List<String> names = new ArrayList<>();
		List<GameCharacter> exclusions = Arrays.asList(charactersToExclude);
		for(GameCharacter c : getOngoingCharacters(characterReceivingBlowjob)) {
			if(!exclusions.contains(c)) {
				names.add(UtilText.parse(c, "[npc.name]"));
			}
		}
		return Util.stringsToStringList(names, false);
	}
	
	public static GameCharacter getPrimaryBlowjobPerformer(GameCharacter characterReceivingBlowjob) {
		if(Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).isEmpty()) {
			return null;
		}
		return Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).keySet().iterator().next();
	}
	
	private static GameCharacter getSecondaryBlowjobPerformer(GameCharacter characterReceivingBlowjob) {
		if(Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).size()<2) {
			return null;
		}
		return new ArrayList<>(Main.sex.getOngoingActionsMap(characterReceivingBlowjob).get(SexAreaPenetration.PENIS).keySet()).get(1);
	}
	
	// ---
	
	public static final SexAction COCK_SLAP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);

			return Util.newArrayListOfValues(performer, primary, target);
		}
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Шлепок членом";
		}

		@Override
		public String getActionDescription() {
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(Main.sex.getCharacterPerformingAction());
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			return "Вытащить свой [npc.cock] изо рта [npc2.namePos] и шлепнуть "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" этим по";
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return UtilText.returnStringAtRandom(
							"Усмехаясь на "+PenisMouth.getOngoingNames(performer)+", [npc.name] [npc.verb(pull)] назад, вытягивая [npc.her] [npc.cock+] изо рта [npc2.namePos]. Пока [npc3.name] [npc3.verb(look)] на [npc.herHim], [npc.she] быстро [npc.verb(slap)] твердый ствол [npc.her] по щеке [npc3.her], разбрызгивая "
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"сперму и":"влажный")
									+" слюну по лицу [npc3.her], прежде чем вонзить [npc.her] [npc.cock+] глубоко в глотку [npc3.her].",
	
							"Отступив назад, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] изо рта [npc2.namePos], прежде чем шлепнуть покрытый слюной [npc1.cockHead] по "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" щек. Пока [npc3.name] [npc3.verb(open)] [npc3.her] рот, чтобы ахнуть от шока, [npc.name] [npc.verb(use)] возможность направить [npc.cockHead] [npc.her] [npc.cock] мимо [npc3.her] [npc3.lips+], прежде чем протолкнуть его глубоко в [npc3.her] горло.",
	
							"[npc.Name] #IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF назад, позволяя [npc.her] [npc.cock+] выскользнуть изо рта [npc2.namePos]. Глядя на [npc.herHim], [npc3.nameIs] застигнута врасплох тем, что [npc.name] внезапно [npc.verb(slap)] [npc.her] "
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"скользкий":"в слюнях")+" член прижимается к лицу [npc3.her], прежде чем [npc.her] с силой [npc.cock+] в [npc3.her] горло.",
	
							"Быстро выдернув [npc.her] [npc.cock+] изо рта [npc2.namePos], [npc.name] [npc.verb(hold)] основание одной рукой, а другой придерживая "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" головку. Когда [npc3.name] [npc2.verb(look)], чтобы понять, что происходит, [npc3.sheIs] встречает влажный шлепок, пока [npc.name] [npc.verb(swing)] [npc.her] "
									+(Main.sex.hasLubricationTypeFromAnyone(target, SexAreaOrifice.MOUTH, LubricationType.PRECUM)?"скользкий":"в слюнях")+" [npc.cock] к щеке [npc3.her]. Пока [npc3.name] [npc3.verb(open)] [npc3.her] рот в шоке, [npc.name] [npc.verb(use)] возможность вонзить [npc.her] [npc.cock+] в [npc3.her] горло.");
				
			} else {
				return UtilText.returnStringAtRandom(
							"Выдернув [npc.her] [npc.hips] назад, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] изо рта [npc2.namePos]. Прежде чем "+(primary.equals(target)?"[npc2.she]":"[npc3.name]")+" может реагировать, [npc.she] быстро [npc.verb(slap)] [npc.her] твердый ствол о [npc3.her] щеку, разбрызгивая слюну "
								+(Main.sex.hasLubricationTypeFromAnyone(performer, SexAreaPenetration.PENIS, LubricationType.PRECUM)?"и предэякулятом":"")
								+" по [npc3.her] [npc3.face], прежде чем вогнать [npc.her] [npc.cock+] глубоко в [npc3.her] горло.",
								
							"Отстраняясь, [npc.name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] рта, и с [npc.a_moan+], [npc.she] [npc.verb(proceed)], чтобы шлепать покрытым слюной [npc.cockHead] о "+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" [npc3.face], прежде чем просунуть пульсирующую длину [npc.her] в [npc3.her] горло.",
	
							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] рта, и, ухмыляясь [npc.herself], [npc.she] затем [npc.verb(slap)] твердый ствол [npc.her] о "
									+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" [npc2.face]. С полоской "+(Main.sex.hasLubricationTypeFromAnyone(performer, SexAreaPenetration.PENIS, LubricationType.PRECUM)?"сперму и":"влажный")
									+" слюны, текущей по [npc3.her] щеке, [npc3.name] [npc3.verb(open)] [npc3.her] [npc3.eyes] широко от неожиданности, пока [npc.name] [npc.verb(force)] [npc.her] [npc.cock] в [npc3.her] горло.",
									
							"Быстро оттягивая [npc.her] [npc.hips+] назад, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] из [npc2.namePos] рта, прежде чем начать шлепать скользкой длиной [npc.her] о "
									+(primary.equals(target)?"[npc2.her]":"[npc3.namePos]")+" щеки. Прежде чем [npc3.name] успеет среагировать, [npc.name] внезапно [npc.verb(push)] [npc.her] [npc.hips] вперед, вбивая [npc.her] [npc.cock+] в [npc3.her] горло.");
			}
		}
	};
	
	public static final SexAction FORCE_BALLS_FOCUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				GameCharacter performer = Main.sex.getCharacterPerformingAction();
				GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(performer);
				if(primary.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
					Main.sex.setPrimaryOngoingCharacter(secondary, performer, SexAreaPenetration.PENIS);
				}
			}
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isInternalTesticles()
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Сосредоточиться на яйцах";
		}

		@Override
		public String getActionDescription() {
			return "Заставь [npc2.name] обратить внимание на твой [npc.balls+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			int rnd = Util.random.nextInt(4);
			
			String[] start;
			String[] mid = new String[] {};
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1) {
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(performer);
				if(!primary.equals(target)) {
					start = new String[] {
							"Пока [npc2.namePos] [npc2.lips+] все еще обхватывает [npc.her] [npc.cock+],",
							"Вгоняя [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло,",
							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] в рот [npc2.namePos],",
							"Скользя [npc.her] [npc.cock+] по [npc2.namePos] горлу,"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary),
							start[rnd]));
					
				} else {
					start = new String[] {
							"Заставив [npc2.name] немного отстраниться, а затем заставив [npc3.name] обхватить [npc3.her] [npc3.lips+] вокруг [npc.her] [npc.cock+],",
							"Отстранившись от [npc2.name], а затем резко вгоняя [npc.her] [npc.cock+] в [npc3.namePos] горло,",
							"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] изо рта [npc2.namePos], а затем вниз по [npc3.namePos] горлу,",
							"Вынимая [npc.her] [npc.cock+] из [npc2.namePos] горла прямо в рот [npc3.namePos],"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary, secondary),
							start[rnd]));
				}
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)] так, чтобы [npc.her] [npc.balls+] мягко прижимались к [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.her] [npc.balls+] мягко прижимались к [npc3.namePos] [npc3.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc3.namePos] [npc3.lips+] мягко прижимались к [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] до тех пор, пока [npc.sheIs] мягко не прижмет [npc.her] [npc.balls+] к [npc3.namePos] [npc3.lips+]."};
						break;
					case DOM_ROUGH:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)] туда-сюда, пока [npc.her] [npc.balls+] грубо не начнут тереться о [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.her] [npc.balls+] грубо терлись о [npc3.namePos] [npc3.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc3.namePos] [npc3.lips+] грубо терлись о [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] до тех пор, пока [npc.sheIs] грубо не прижмет [npc.her] [npc.balls+] к [npc3.namePos] [npc3.lips+]."};
						break;
					case SUB_NORMAL:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)] туда-сюда, пока [npc.her] [npc.balls+] не начнут давить на [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.her] [npc.balls+] прижимались к [npc3.namePos] [npc3.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc3.namePos] [npc3.lips+] были прижаты к [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] до тех пор, пока [npc.sheIs] не прижмет [npc.her] [npc.balls+] к [npc3.namePos] [npc3.lips+]."};
						break;
					default: // Dom normal and sub eager:
						mid = new String[] {
								" [npc.name] [npc.verb(shuffle)], пока [npc.her] [npc.balls+] не прижмутся к [npc3.namePos] [npc3.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.her] [npc.balls+] прижимались к [npc3.namePos] [npc3.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc3.namePos] [npc3.lips+] были прижаты к [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] до тех пор, пока [npc.sheIs] не прижмет [npc.her] [npc.balls+] к [npc3.namePos] [npc3.lips+]."};
						break;
				}
				UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, primary, target),
						mid[rnd]));
				
			} else {
				start = new String[] {
						"Оттягивая [npc.her] [npc.hips] назад, [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+], чтобы тот выскользнул из [npc2.namePos] рта,",
						"Выскальзывая [npc.her] [npc.cock+] из [npc2.namePos] рта,",
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] рта,",
						"Быстро выскальзывая [npc.her] [npc.cock+] из [npc2.namePos] рта,"};
				UtilText.nodeContentSB.append(start[rnd]);

				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						mid = new String[] {
								" прежде чем заерзать, пока [npc.her] [npc.balls+] не будут мягко прижиматься к [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.her] [npc.balls+] мягко прижимались к [npc2.her] [npc2.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc2.her] [npc2.lips+] мягко прижались к [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself], пока [npc.sheIs] не начнет нежно прижимать [npc.her] [npc.balls+] к [npc2.namePos] [npc2.lips+]."};
						break;
					case DOM_ROUGH:
						mid = new String[] {
								" прежде чем заерзать, пока [npc.her] [npc.balls+] грубо не начнут тереться о [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, что [npc.her] [npc.balls+] грубо трутся о [npc2.her] [npc2.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc2.her] [npc2.lips+] грубо терлись о [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself], пока [npc.sheIs] грубо не прижмет [npc.her] [npc.balls+] к [npc2.namePos] [npc2.lips+]."};
						break;
					case SUB_NORMAL:
						mid = new String[] {
								" прежде чем начать ерзать, пока [npc.her] [npc.balls+] не прижмутся к [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.her] [npc.balls+] прижимались к [npc2.her] [npc2.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc2.her] [npc2.lips+] были прижаты к [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself], пока [npc.sheIs] прижимает [npc.her] [npc.balls+] к [npc2.namePos] [npc2.lips+]."};
						break;
					default: // Dom normal and sub eager:
						mid = new String[] {
								" прежде чем начать ерзать, пока [npc.her] [npc.balls+] не прижмутся к [npc2.her] [npc2.lips+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself] так, чтобы [npc.her] [npc.balls+] прижимались к [npc2.her] [npc2.lips+].",
								" прежде чем перехватить [npc.herself] так, чтобы [npc2.her] [npc2.lips+] были прижаты к [npc.her] [npc.balls+].",
								" [npc.name] [npc.verb(reposition)] [npc.herself], пока [npc.sheIs] прижимает [npc.her] [npc.balls+] к [npc2.namePos] [npc2.lips+]."};
						break;
				}
				UtilText.nodeContentSB.append(mid[rnd]);
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Медленно вытягивая [npc2.her] [npc2.tongue+], [npc2.name] [npc2.verb(start)], чтобы нежно лизать и целовать [npc.namePos] [npc.balls+], отчего [npc.a_moan+] выпадает из между [npc.her] [npc.lips+].",
								" [npc2.Name] осторожно [npc2.verb(start)], чтобы целовать и лизать [npc.namePos] [npc.balls+], вытягивая [npc.a_moan+] изо рта [npc.her]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" С жадностью выстреливая [npc2.her] [npc2.tongue+], [npc2.name] алчно [npc2.verb(start)], чтобы лизать и целовать [npc.namePos] [npc.balls+], отчего [npc.a_moan+] выпадает из между [npc.her] [npc.lips+].",
								" [npc2.Name] с нетерпением [npc2.verb(start)], чтобы целовать и лизать [npc.namePos] [npc.balls+], вытягивая [npc.a_moan+] изо рта [npc.her]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Вытягивая [npc2.her] [npc2.tongue+], [npc2.name] [npc2.verb(start)], чтобы грубо лизать и целовать [npc.namePos] [npc.balls+], отчего [npc.a_moan+] выпадает из между [npc.her] [npc.lips+].",
								" [npc2.Name] грубо [npc2.verb(start)], целуя и лизая [npc.namePos] [npc.balls+], вытягивая [npc.a_moan+] изо рта [npc.her]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Выстреливая [npc2.her] [npc2.tongue+], [npc2.name] [npc2.verb(start)], чтобы лизать и целовать [npc.namePos] [npc.balls+], отчего [npc.a_moan+] выпадает из между [npc.her] [npc.lips+].",
								" [npc2.Name] [npc2.verb(start)], чтобы целовать и лизать [npc.namePos] [npc.balls+], вытягивая [npc.a_moan+] изо рта [npc.her]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] отчаянно [npc2.verb(try)], издавая [npc2.a_sob+], пока [npc.namePos] [npc.balls+] [npc.verb(continue)], втираясь в [npc2.her] [npc2.lips+].",
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.sob], отчаянно пытаясь отстраниться от [npc.namePos] [npc.balls+], пока [npc2.she] [npc2.verb(struggle)] о [npc.herHim]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction SUCK_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
				GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(target);
				if(primary.equals(Main.sex.getCharacterPerformingAction())) {
					Main.sex.setPrimaryOngoingCharacter(secondary, target, SexAreaPenetration.PENIS);
				}
			}
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Лизать яички";
		}

		@Override
		public String getActionDescription() {
			return "Некоторое время лизать и целовать [npc2.namePos] [npc2.balls].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			int rnd = Util.random.nextInt(4);
			
			String[] start;
			String[] mid = new String[] {};
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1) {
				GameCharacter secondary = PenisMouth.getSecondaryBlowjobPerformer(target);
				if(!primary.equals(performer)) {
					start = new String[] {
							"Пока [npc2.namePos] [npc2.cock+] все еще глубоко в [npc3.namePos] горле,",
							"Пока [npc3.name] [npc3.verb(bob)] [npc3.her] на [npc2.namePos] коленях, [npc.name] [npc.verb(lean)], чтобы запечатлеть поцелуй на щеке [npc2.her] [npc2.cock+],",
							"Пока [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] в [npc3.namePos] рот,",
							"Стараясь не мешать [npc3.name], пока [npc3.her] ритмично двигает головой над [npc2.namePos] [npc2.cock+],"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
							start[rnd]));
					
				} else {
					start = new String[] {
							"Отстраниться и позволить [npc3.name] взять [npc2.namePos] [npc2.cock+] в [npc3.her] рот,",
							"[npc.Name] [npc.verb(swap)] с [npc3.name], позволяя [npc3.herHim] взять [npc2.namePos] [npc2.cock+] в [npc3.her] рот",
							"Вытаскивая [npc2.namePos] [npc2.cock+] изо рта [npc.her], а затем заставляя [npc3.name] проглотить скользкий ствол,",
							"Сначала вытащить [npc2.namePos] [npc2.cock+] изо рта [npc.her], а затем заставить [npc3.name] начать сосать его,"};
					UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, secondary),
							start[rnd]));
				}
				
			} else {
				start = new String[] {
						"Позволяя [npc2.namePos] [npc2.cock+] полностью выскользнуть изо рта [npc.her],",
						"[npc.Name] [npc.verb(let)] изо рта [npc.her], пока [npc2.namePos] [npc2.cock+] скользит наружу,",
						"Вытаскивая [npc2.namePos] [npc2.cock+] изо рта [npc.her],",
						"Сначала вытащить [npc2.namePos] [npc2.cock+] изо рта [npc.her],"};
				UtilText.nodeContentSB.append(start[rnd]);
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] вниз, чтобы [npc.verb(start)] нежно лизать и сосать [npc2.her] [npc2.balls+].",
							" прежде чем опустить [npc.her] [npc.lips+] ниже, чтобы начать нежно лизать и целовать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] вниз, чтобы начать нежно целовать и лизать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] вниз, прежде чем начать нежно целовать и прижиматься к [npc2.her] [npc2.balls+]."};
					break;
				case DOM_ROUGH:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] голову вниз и [npc.verb(start)], чтобы грубо вылизывать и сосать [npc2.her] [npc2.balls+].",
							" прежде чем опустить [npc.her] [npc.lips+] вниз, чтобы начать грубо вылизывать и целовать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] голову вниз, чтобы начать грубо целовать и вылизывать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] голову вниз, прежде чем начать грубо целовать и прижиматься к [npc2.her] [npc2.balls+]."};
					break;
				case SUB_NORMAL:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] голову вниз и [npc.verb(start)], чтобы вылизывать и сосать [npc2.her] [npc2.balls+].",
							" прежде чем опустить [npc.her] [npc.lips+] вниз, чтобы начать вылизывать и целовать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] голову вниз, чтобы начать целовать и вылизывать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] голову вниз, прежде чем начать целовать и прижиматься к [npc2.her] [npc2.balls+]."};
					break;
				default: // Dom normal and sub eager:
					mid = new String[] {
							" [npc.name] [npc.verb(move)] [npc.her] голову вниз и [npc.verb(start)], чтобы жадно вылизывать и сосать [npc2.her] [npc2.balls+].",
							" прежде чем опустить [npc.her] [npc.lips+] вниз, чтобы начать отчаянно вылизывать и целовать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] наклоняет голову, чтобы с жадностью начать целовать и лизать [npc2.her] [npc2.balls+].",
							" [npc.name] [npc.verb(move)] [npc.her] наклоняет голову, прежде чем начать жадно целовать и прижиматься к [npc2.her] [npc2.balls+]."};
					break;
			}
			UtilText.nodeContentSB.append(UtilText.parse(performer, target, mid[rnd]));
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction LICK_HEAD = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Лизать головку";
		}

		@Override
		public String getActionDescription() {
			return "Лизать и целовать [npc2.cockHead+] [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {

			List<String> descriptions = new ArrayList<>();

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				List<String> descriptionsEnd = new ArrayList<>();
				
				for(PenetrationModifier pm : Main.sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case FLARED:
							descriptions.add("Поднимая голову [npc3.namePos] так, чтобы [npc2.namePos] [npc2.cock+] полностью выскользнула из [npc3.her] рта, [npc.name] быстро [npc.verb(move)], чтобы занять [npc3.her] место. Сразу же прижимая [npc.her] [npc.lips+] к широкой, расширенной головке, [npc.she] затем [npc.verb(start)], чтобы страстно целовать и лизать верхушку [npc2.namePos] [npc2.cock+].");
							break;
						default:
							break;
					}
				}
				descriptions.add("С голодным [npc.moan], [npc.name] [npc.verb(pull)] [npc3.namePos] голову назад, прежде чем занять [npc3.her] место и обхватить [npc.her] [npc.lips+] вокруг [npc2.cockHead+] [npc2.namePos] [npc2.cock+]. Издав удовлетворенный [npc.moan], [npc.she] начинает лизать и целовать [npc2.her] [npc2.cockHead+].");
				descriptions.add("Желая присоединиться к процессу, [npc.name] [npc.verb(pull)] [npc3.namePos] голову назад, издавая [npc.a_moan+], пока [npc.she] [npc.verb(take)] [npc3.her] место и [npc.verb(start)] сосредотачивается на том, чтобы сосать и целовать [npc2.cockHead+] [npc2.namePos] [npc2.cock+].");
				descriptions.add("Слегка откинув голову [npc3.namePos] назад, [npc.name] [npc.verb(take)] [npc3.her], и, сосредоточившись на использовании [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] лижет и целует [npc2.cockHead+], который теперь прижат к [npc.her] [npc.lips+].");

				// If not resisting
				switch(Main.sex.getSexPace(primary)) {
					case SUB_RESISTING:
						descriptionsEnd.add(" Радуясь тому, что ее отодвинули, [npc3.name] [npc3.verb(try)] тихо сидеть, пока [npc3.she] [npc3.verb(attempt)] отодвигается подальше, но, осознав, что [npc3.she] [npc3.verb(need)] некоторого «поощрения», [npc.name] вскоре [npc.verb(pull)] от [npc2.namePos] промежности, прежде чем схватить [npc3.name] и снова прижать [npc3.her] рот к [npc2.namePos] [npc2.cock+].");
						descriptionsEnd.add(" С облегчением вытесненная из позиции [npc3.her], [npc3.name] [npc3.verb(try)] убраться подальше от [npc2.name], но, видя, что происходит, [npc.name] быстро [npc.verb(put)] конец [npc.her] оральному обслуживанию, прежде чем схватить [npc3.name] и заставить [npc3.herHim] снова проглотить [npc2.namePos] [npc2.cock+].");
						break;
					default:
						descriptionsEnd.add(" [npc3.name] не в восторге от того, что ей пришлось лишь облизывать стенки [npc2.namePos] шафта, и [npc3.verb(allow)] [npc.name], чтобы хоть на мгновение получить [npc.her] удовольствие, прежде чем оттолкнуть [npc.her] и снова заглотить [npc2.namePos] [npc2.cock] глубоко в [npc3.her] горло.");
						descriptionsEnd.add(" Нетерпеливо желая вернуть [npc3.her] положение, [npc3.name] [npc3.verb(let)] [npc.name] оказывает оральную услугу [npc2.name] таким образом в течение короткого времени, прежде чем оттолкнуть [npc.herHim] в сторону и снова заглотить [npc2.namePos] [npc2.cock+] в [npc3.her] рот.");
						break;
				}
				
				return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
						Util.randomItemFrom(descriptions))
						+ UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								Util.randomItemFrom(descriptionsEnd));
				
			} else {
				for(PenetrationModifier pm : Main.sex.getCharacterTargetedForSexAction(this).getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("[npc.Name] [npc.verb(slide)] откидывает [npc.her] назад, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(feel)] шипы, покрывающие [npc2.namePos] [npc2.cock], пробираются вверх по [npc.her] горлу. Оставив лишь [npc2.cockHead+], просунутый за [npc.her] [npc.lips+], [npc.she] затем [npc.verb(start)] страстно целовать и сосать кончик [npc2.her] [npc2.cock+].");
							break;
						case FLARED:
							descriptions.add("Откатывая голову [npc.her] назад, [npc.name] [npc.verb(allow)] позволяет [npc2.namePos] [npc2.cock+] полностью выскользнуть из [npc.her] рта. Немедленно прижимая [npc.her] [npc.lips+] к широкой, расширенной головке, [npc.she] затем [npc.verb(start)] страстно целовать и лизать её.");
							break;
						default:
							break;
					}
				}
				descriptions.add("С [npc.a_moan+], [npc.name] [npc.verb(pull)] откидывает [npc.her] голову, обхватывая [npc.her] [npc.lips+] вокруг [npc2.cockHead+] [npc2.namePos] [npc2.cock+], прежде чем начать лизать и целовать её.");
				descriptions.add("[npc.Name] [npc.verb(pull)] откидывает [npc.her] голову, издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)], сосредоточившись на сосании и поцелуях [npc2.cockHead+] [npc2.namePos] [npc2.cock+].");
				descriptions.add("Слегка оттянув [npc.her] голову назад, [npc.name] [npc.verb(let)] большую часть [npc2.namePos] [npc2.cock+] из [npc.her] рта, и, сосредоточившись на использовании [npc.her] [npc.tongue+], [npc.she] [npc.verb(start)] облизывает и целует [npc2.cockHead+], что осталось торчать между [npc.her] [npc.lips+].");
				
				return Util.randomItemFrom(descriptions);
			}
		}
		
	};
	
	public static final SexAction HERM_FUN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)
					&& Main.sex.isOrificeFree(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
				return "Фута-удовольствие";
			} else {
				return "Герма-удовольствие";
			}
		}

		@Override
		public String getActionDescription() {
			return "Удовольствие как для [npc2.namePos] [npc2.cock+], так и для [npc2.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] нежно посасывает [npc2.cockHead] [npc2.her] [npc2.cock+], прежде чем отстраниться и начать медленно лизать и целовать [npc2.her] [npc2.pussy+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(deliver)] делает долгий, медленный лизь по всей длине [npc2.her] [npc2.cock+], прежде чем податься вперед и осторожно втиснуть [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Нежно целуя [npc2.cockHead] [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] вниз, прижимая [npc.her] [npc.lips+] к [npc2.her] [npc2.pussy+], а затем медленно проталкивая [npc.her] [npc.tongue+] между [npc2.her] [npc2.labia+]."));
					break;
				case DOM_NORMAL:
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] влажно посасывает [npc2.cockHead] [npc2.her] [npc2.cock+], прежде чем отстраниться и начать жадно лизать и целовать [npc2.her] [npc2.pussy+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(deliver)] долгий, влажный лизь по всей длине [npc2.her] [npc2.cock+], прежде чем податься вперед и жадно заглотить [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Одарив влажным поцелуем [npc2.cockHead] [npc2.namePos], [npc2.cock+], [npc.name] [npc.verb(move)] вниз, поднося [npc.her] [npc.lips+] к [npc2.her] [npc2.pussy+], прежде чем жадно засунуть [npc.her] [npc.tongue+] между [npc2.her] [npc2.labia+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] влажно посасывает [npc2.cockHead] [npc2.her] [npc2.cock+], прежде чем отстраниться и начать грубо лизать и целовать [npc2.her] [npc2.pussy+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(deliver)] долгий, грубый лизь по всей длине [npc2.her] [npc2.cock+], прежде чем податься вперед и властно вонзить [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Одарив грубым поцелуем [npc2.cockHead] [npc2.namePos], [npc2.cock+], [npc.name] [npc.verb(move)] вниз, поднося [npc.her] [npc.lips+] к [npc2.her] [npc2.pussy+], прежде чем властно засунуть [npc.her] [npc.tongue+] между [npc2.her] [npc2.labia+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Не желая игнорировать другой сексуальный орган [npc2.namePos], [npc.name] [npc.verb(give)] влажное [npc2.cockHead] [npc2.her] [npc2.cock+], прежде чем отстраниться и начать лизать и целовать [npc2.her] [npc2.pussy+].",

							"Решив уделить немного внимания [npc2.namePos] [npc2.pussy], [npc.name] [npc.verb(deliver)] долгий, влажный лизь по всей длине [npc2.her] [npc2.cock+], прежде чем податься вперед и вогнать [npc.her] [npc.tongue+] в [npc2.her] [npc2.pussy+].",

							"Осыпая влажными поцелуями [npc2.cockHead] [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(move)] вниз, поднося [npc.her] [npc.lips+] к [npc2.her] [npc2.pussy+], прежде чем просунуть [npc.her] [npc.tongue+] между [npc2.her] [npc2.labia+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] нежный [npc2.moan], мягко прижимая [npc2.her] [npc2.pussy+] к [npc.namePos] [npc.lips+], прежде чем [npc.she] [npc.verb(decide)], чтобы снова переключить [npc2.her] внимание на [npc2.her] [npc2.cock+].",
	
								" Мягко втискивая [npc2.her] [npc2.pussy+] в [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] нежный [npc2.moan], прежде чем [npc.name] решает вернуться к сосредоточению на [npc2.her] [npc2.cock+].",
	
								" Мягко [npc2.moaning], [npc2.name] нежно [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)], чтобы снова переключить оральное внимание [npc.her] на [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], жадно прижимая [npc2.her] [npc2.pussy+] к [npc.namePos] [npc.lips+], прежде чем [npc.she] [npc.verb(decide)] снова переключить [npc2.her] внимание на [npc2.her] [npc2.cock+].",
	
								" Жадно втискивая [npc2.her] [npc2.pussy+] в [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем [npc.name] решит вернуться к сосредоточению на [npc2.her] [npc2.cock+].",
	
								" [npc2.Moaning+], [npc2.name] с нетерпением [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)] переключить [npc.her] оральное внимание обратно на [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], грубо втирая [npc2.her] [npc2.pussy+] в [npc.namePos] [npc.lips+], прежде чем [npc.she] [npc.verb(decide)] снова переключить [npc2.her] внимание на [npc2.her] [npc2.cock+].",
	
								" Грубо втирая [npc2.her] [npc2.pussy+] в [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], прежде чем [npc.name] решит вернуться к сосредоточению на [npc2.her] [npc2.cock+].",
	
								" [npc2.Moaning+], [npc2.name] грубо [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)] переключить [npc.her] оральное внимание обратно на [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], прижимая [npc2.her] [npc2.pussy+] к [npc.namePos] [npc.lips+], прежде чем [npc.she] [npc.verb(decide)] снова переключить [npc2.her] внимание на [npc2.her] [npc2.cock+].",
	
								" Вгоняя [npc2.her] [npc2.pussy+] в [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], после чего [npc.name] решает вернуться к сосредоточению на [npc2.her] [npc2.cock+].",
	
								" [npc2.Moaning+], [npc2.name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face] на несколько мгновений, прежде чем [npc.she] [npc.verb(decide)] снова переключить оральное внимание [npc.her] на [npc2.her] [npc2.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Sobbing] и корчась от дискомфорта, [npc2.she] отчаянно [npc2.verb(try)], пытаясь отстраниться от [npc.herHim] и умоляя оставить её в покое, пока [npc.she] [npc.verb(shift)] внимание [npc.her] снова на [npc2.her] [npc2.cock+].",
	
								" С [npc2.a_sob+], [npc2.name] пытается оттолкнуть [npc.name], корчась от дискомфорта, пока [npc.she] [npc.verb(move)], снова сосредотачивая [npc.her] внимание на [npc2.her] [npc2.cock+].",
	
								" Со слезами, текущими по [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc.name] [npc.verb(move)] снова сосредоточивается на [npc2.her] [npc2.cock+]."));
						break;
					default:
						break;
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" Проведя несколько мгновений, лаская [npc2.name] языком, [npc.name] [npc.verb(shift)] внимание [npc.her] снова на [npc2.her] [npc2.cock+].",
						" Насладившись вкусом [npc2.namePos] [npc2.pussy+] в течение нескольких мгновений, [npc.name] [npc.verb(move)] назад, вновь сосредоточив внимание [npc.her] на [npc2.her] [npc2.cock+]."));
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction TWINTAIL_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Тянуть за хвостики";
			} else {
				return "Тянуть за косички";
			}
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS) {
				return "Схватить [npc2.namePos] хвостики и потянуть [npc2.her] вперед, на твои [npc.cock+].";
			} else {
				return "Схватить [npc2.namePos] косички и потянуть [npc2.her] вперед, на твои [npc.cock+].";
			}
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HAIR)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_TAILS || Main.sex.getCharacterTargetedForSexAction(this).getHairStyle()==HairStyle.TWIN_BRAIDS)
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairLength().isSuitableForPulling()
					&& Main.sex.getCharacterTargetedForSexAction(this).getHairType().isAbleToBeGrabbedInSex();
		}

		@Override
		public String getDescription() {
			
			String style = Main.sex.getCharacterTargetedForSexAction(this).getHairStyle().getName(Main.sex.getCharacterTargetedForSexAction(this));
			
			UtilText.nodeContentSB.setLength(0);
			
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала медленно отводя [npc3.name] от [npc.her], [npc.cock+], [npc.name] [npc.verb(reach)] вперед, чтобы ухватиться за [npc2.namePos] "+style+", прежде чем осторожно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Медленно отодвинув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы мягко, но уверенно обхватить каждую из [npc2.namePos] "+style+", а затем плавно втягивая [npc2.herHim] в область паха [npc.her], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы она заглотила [npc.her] [npc.cock+] по самую глубину.",
								"Мягко отодвинув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] "+style+", прежде медленно потянув [npc2.herHim] вперед, вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала грубо отталкивая [npc3.name] от [npc.her] [npc.cock+], [npc.name] резко [npc.verb(grasp)] [npc2.namePos] "+style+", прежде чем с силой дернуть [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"После того как [npc3.name] грубо оттолкнули, [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] "+style+", прежде чем безжалостно вжать [npc2.her] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim] для глубокого заглатывания [npc.her] [npc.cock+].",
								"Грубо отталкивая [npc3.name], [npc.name] затем [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] "+style+", прежде чем с силой дернуть [npc2.herHim] вперед, вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала [npc3.name] оттягивается назад от [npc.her] [npc.cock+], а затем [npc.name] [npc.verb(reach)] вперед, чтобы обхватить [npc2.namePos] "+style+", прежде чем решительно потянуть [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Оттянув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы крепко обхватить каждый из [npc2.namePos] "+style+", а затем плавно втягивая [npc2.herHim] в область паха [npc.her], издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы она заглотила [npc.her] [npc.cock+] по самую глубину.",
								"Оттягивая [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] "+style+", прежде чем уверенно потянуть [npc2.herHim] вперед, вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обхватывая [npc2.namePos] "+style+", [npc.name] плавно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Протягивая руку вниз, чтобы нежно, но крепко обхватить каждый из [npc2.namePos] "+style+", [npc.name] медленно [npc.verb(pull)] [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] "+style+", прежде мягко потянув [npc2.herHim] вперед, заставляя [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] "+style+", [npc.name] неистово [npc.verb(jerk)] [npc2.her] головку вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Потянувшись вниз, чтобы схватить [npc2.namePos] "+style+", [npc.name] безжалостно [npc.verb(yank)] [npc2.her] головку в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заглотить [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] "+style+", прежде чем с силой дернуть [npc2.herHim] вперед, вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обхватывая [npc2.namePos] "+style+", [npc.name] крепко [npc.verb(pull)] [npc2.her] головку вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Потянувшись вниз, чтобы крепко обхватить каждый из [npc2.namePos] "+style+", [npc.name] уверенно [npc.verb(pull)] [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы заглотить [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] "+style+", прежде чем уверенно потянуть [npc2.herHim] вперед, вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)], на мгновение нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] в восторге, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] и послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, всё же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.cock+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность рыдать и жадно хватать ртом воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Вибрации [npc2.namePos], заглушающие стоны и всхлипы, лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь пробиться сквозь [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу: [npc2.her] [npc2.hair(true)] распрямляются, и [npc.namePos] [npc.cock+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на миг [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.cock], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction EAR_PULL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Потянуть за ухо";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.ears+] и потянуть [npc2.her] вперед, на свои [npc.cock+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HEAD)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& (Main.sex.getCharacterTargetedForSexAction(this).getEarType().isAbleToBeUsedAsHandlesInSex());
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала [npc3.name] медленно вытягивается из [npc.her] [npc.cock+], затем [npc.name] [npc.verb(reach)] вперед, чтобы ухватиться за [npc2.namePos] [npc2.ears+], после чего он плавно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Медленно оттолкнув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы мягко, но уверенно обхватить каждый из [npc2.namePos] [npc2.ears+], а затем плавно втягивая [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.cock+] по самую основу.",
								"Мягко оттолкнув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы ухватиться за [npc2.namePos] [npc2.ears+], а затем медленно потянув [npc2.herHim] вперед, вонзая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала грубо оттолкнув [npc3.name] от [npc.her] [npc.cock+], [npc.name] бесцеремонно [npc.verb(grasp)] [npc2.namePos] [npc2.ears+], а затем резко дернув [npc2.her] голову вперед и заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Грубо оттолкнув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.ears+], а затем беспощадно заталкивая [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.cock+] по самую основу.",
								"Грубо оттолкнув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.ears+], а затем резко дернув [npc2.herHim] вперед, вонзая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала [npc3.name] отъезжает назад от [npc.her] [npc.cock+], затем [npc.name] [npc.verb(reach)] вперед, чтобы обхватить [npc2.namePos] [npc2.ears+], после чего уверенно тянет [npc2.her] голову вперед, заставляя [npc2.herHim] проглатывать [npc.her] [npc.cock+] до самого основания.",
								"Отодвинув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы крепко обхватить каждую из [npc2.namePos] [npc2.ears+], а затем уверенно затягивает [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы глубоко заглотить [npc.her] [npc.cock+].",
								"Отодвинув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.ears+], а затем уверенно тянет [npc2.herHim] вперед, втискивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обхватив [npc2.namePos] [npc2.ears+], [npc.name] осторожно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглатывать [npc.her] [npc.cock+] до самого основания.",
								"Протянув руку вниз, чтобы мягко, но уверенно обхватить каждую из [npc2.namePos] [npc2.ears+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы глубоко заглотить [npc.her] [npc.cock+].",
								"Схватив [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватиться за [npc2.namePos] [npc2.ears+], а затем плавно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] [npc2.ears+], [npc.name] яростно [npc.verb(jerk)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Потянувшись вниз, чтобы схватить [npc2.namePos] [npc2.ears+], [npc.name] безжалостно [npc.verb(yank)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"Схватив [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы ухватиться за [npc2.namePos] [npc2.ears+], а затем резко дернув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.ears+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Потянувшись вниз, чтобы крепко обхватить каждый из [npc2.namePos] [npc2.ears+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.ears+], а затем уверенно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)], на мгновение нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] в восторге, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] и послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, всё же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.cock+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность рыдать и жадно хватать ртом воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Вибрации [npc2.namePos], заглушающие стоны и всхлипы, лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь пробиться сквозь [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу: [npc2.her] [npc2.hair(true)] распрямляются, и [npc.namePos] [npc.cock+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на миг [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.cock], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction GRAB_HORNS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.horns]";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.horns+] и потянуть [npc2.her] вперед, на твой [npc.cock+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isHornsAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала медленно отводя [npc3.name] от [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] вперед, чтобы обхватить [npc2.namePos] [npc2.horns+], а затем осторожно [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Медленно отодвинув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы мягко, но крепко обхватить каждую из [npc2.namePos] [npc2.horns+], а затем уверенно потянув [npc2.herHim] к [npc.her] паху, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"Осторожно отодвинув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.horns+], а затем медленно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала [npc3.name] грубо отталкивает [npc.her] назад, [npc.cock+], [npc.name] резко [npc.verb(grasp)] [npc2.namePos] [npc2.horns+], а затем с силой дергает [npc2.her] голову вперед, заставляя [npc2.herHim] проглатывать [npc.her] [npc.cock+] до самого основания.",
								"Грубо оттолкнув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем безжалостно вжимает [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"Грубо отталкивая [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем резко дергает [npc2.herHim] вперед, вонзая [npc.her] [npc.cock+] прямо в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала плавно отстраняя [npc3.name] от [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] вперед, чтобы обхватить [npc2.namePos] [npc2.horns+], а затем крепко тянет [npc2.her] голову вперед, заставляя [npc2.herHim] проглатывать [npc.her] [npc.cock+] до самого основания.",
								"Оттолкнув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы крепко сжать каждый из [npc2.namePos] [npc2.horns+], а затем уверенно втягивает [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"Оттолкнув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.horns+], а затем уверенно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к нему "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обхватив [npc2.namePos] [npc2.horns+], [npc.name] мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Протянув руку вниз, чтобы мягко, но крепко обхватить каждый из [npc2.namePos] [npc2.horns+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim] глубоко в горло [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.horns+], а затем мягко потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к нему "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] [npc2.horns+], [npc.name] яростно [npc.verb(jerk)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Наклонившись, чтобы схватить [npc2.namePos] [npc2.horns+], [npc.name] безжалостно [npc.verb(yank)] [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем резко дергает [npc2.herHim] вперед, вонзая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.horns+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] голову вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Наклонившись, чтобы крепко сжать каждый из [npc2.namePos] [npc2.horns+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.horns+], а затем плавно потянуть [npc2.herHim] вперед, вонзая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)], на мгновение нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] в восторге, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] и послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, всё же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.cock+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность рыдать и жадно хватать ртом воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Вибрации [npc2.namePos], заглушающие стоны и всхлипы, лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь пробиться сквозь [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу: [npc2.her] [npc2.hair(true)] распрямляются, и [npc.namePos] [npc.cock+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на миг [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.cock], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	

	public static final SexAction GRAB_ANTENNAE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects() {
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Схватить [npc2.antennae]";
		}

		@Override
		public String getActionDescription() {
			return "Схватить [npc2.namePos] [npc2.antennae+] и потянуть [npc2.her] вперед, на свои [npc.cock+].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean found = false;
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			try {
				if(Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER)) {
					found = true;
				}
			} catch(Exception ex) {
			}
			
			if(!found) { // No available finger-mouth actions, so can't reach face
				return false;
			}
			
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& !Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction()).containsKey(InventorySlot.HORNS)
					&& !Main.sex.getCharacterPerformingAction().getInventorySlotsConcealed(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(InventorySlot.HAND)
					&& Main.sex.getCharacterTargetedForSexAction(this).isAntennaeAbleToBeUsedAsHandlesInSex();
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала [npc3.name] медленно отходит назад от [npc.her] [npc.cock+], затем [npc.name] [npc.verb(reach)] вперед, чтобы обхватить [npc2.namePos] [npc2.antennae+], после чего мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Медленно отстранив [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы мягко, но уверенно обхватить каждый из [npc2.namePos] [npc2.antennae+], а затем плавно втягивая [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"Мягко отстраняя [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.antennae+], после чего медленно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала отстранив [npc3.name] назад от [npc.her] [npc.cock+], [npc.name] грубо [npc.verb(grasp)] [npc2.namePos] [npc2.antennae+], после чего резко дернув [npc2.her] голову вперед и заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Грубо оттолкнув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], а затем безжалостно втиснув [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"Грубо оттолкнув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], а после яростно дернув [npc2.herHim] вперед, вонзая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								"Сначала отводя [npc3.name] назад от [npc.her] [npc.cock+], [npc.name] [npc.verb(reach)] вперед, чтобы ухватиться за [npc2.namePos] [npc2.antennae+], а затем крепко потянув [npc2.her] голову вперед и заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Оттолкнув [npc3.name] назад, [npc.name] [npc.verb(reach)] вниз, чтобы крепко сжать каждый из [npc2.namePos] [npc2.antennae+], а затем уверенно затягивая [npc2.herHim] в [npc.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], глубоко заглатывая [npc.her] [npc.cock+].",
								"Оттолкнув [npc3.name] назад, [npc.name] затем [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.antennae+], а затем уверенно потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к нему "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+".")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обхватив [npc2.namePos] [npc2.antennae+], [npc.name] мягко [npc.verb(pull)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Протянув руку вниз, чтобы мягко, но крепко обхватить каждый из [npc2.namePos] [npc2.antennae+], [npc.name] медленно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim] для глубокого заглатывания [npc.her] [npc.cock+].",
								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы обхватить [npc2.namePos] [npc2.antennae+], а затем мягко потянув [npc2.herHim] вперед, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к нему "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо схватив [npc2.namePos] [npc2.antennae+], [npc.name] яростно [npc.verb(jerk)] [npc2.her] вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Потянувшись вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], [npc.name] безжалостно [npc.verb(yank)] [npc2.her] голову в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.cock+] по самую глубину.",
								"С помощью [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], после чего резко дергает [npc2.herHim] вперед, вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Схватив [npc2.namePos] [npc2.antennae+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] голову вперед, заставляя [npc2.herHim] проглотить [npc.her] [npc.cock+] до самого основания.",
								"Потянувшись вниз, чтобы крепко сжать каждый из [npc2.namePos] [npc2.antennae+], [npc.name] уверенно [npc.verb(pull)] [npc2.her] в [npc2.her] пах, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], заглатывая [npc.her] [npc.cock+] по самую глубину.",
								"С помощью [npc.a_moan+], [npc.name] [npc.verb(reach)] вниз, чтобы схватить [npc2.namePos] [npc2.antennae+], после чего плавно потянув [npc2.herHim] вперед, вгоняя [npc.her] [npc.cock+] глубоко в [npc2.her] горло так, что [npc2.her] [npc2.lips+] прижимаются к "
										+(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)?"узлу [npc.her]":"основанию")+"."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)], на мгновение нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Тихо напевая и [npc2.moaning] в восторге, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], нежно поглаживая нижнюю часть [npc.namePos] [npc.cock] и послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Прищурив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(put)] на мгновение благодаря дерзкому [npc.namePos] маневру, прежде чем резко откинуть [npc2.her] голову назад, красноречиво напоминая [npc.herHim], кто здесь главный.",
								" Гулкие вибрации угрожающего рычания [npc2.namePos], хоть и приносят дополнительное удовольствие, всё же заставляют [npc.name] поспешно разжать [npc2.her] [npc2.hair(true)] и выскользнуть [npc.her] [npc.cock+] из [npc2.her] горла."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Сморщив [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(beat)] против #IF(npc.isPlayer())твоих#ELSE[npc.her] мучителя#ENDIF бедер в тщетной попытке сопротивления, прежде чем наконец получить возможность рыдать и жадно хватать ртом воздух, когда [npc.name] на мгновение [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Вибрации [npc2.namePos], заглушающие стоны и всхлипы, лишь приносят #IF(npc.isPlayer())тебе#ELSE[npc.her] мучителю#ENDIF дополнительное удовольствие, но, проведя несколько секунд, пытаясь пробиться сквозь [npc.namePos] бедра, [npc2.she] наконец [npc2.verb(achieve)] маленькую победу: [npc2.her] [npc2.hair(true)] распрямляются, и [npc.namePos] [npc.cock+] на мгновение выскальзывает из [npc2.her] горла."));
						break;
					default: // For dom normal, sub normal, and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(spend)] мгновение, лаская нижнюю часть [npc.namePos] [npc.cock] с помощью [npc2.her] [npc2.tongue], прежде чем наконец сможет жадно глотать воздух, когда [npc.she] на миг [npc.verb(withdraw)] [npc.her] [npc.cock+] из [npc2.her] горла.",
								" Счастливо напевая и [npc2.moaning] от восторга, [npc2.name] [npc2.verb(use)] [npc2.her] [npc2.tongue], чтобы с любовью ласкать нижнюю часть [npc.namePos] [npc.cock], послушно затаив [npc2.her] дыхание, пока [npc.her] [npc.cock+] наконец не вырывается из [npc2.her] горла."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	public static final SexAction THROAT_MUSCLE_CONTROL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Контроль горла";
		}

		@Override
		public String getActionDescription() {
			return "Сжимайте мышцами горла вокруг [npc2.namePos] [npc2.cock].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getFaceOrificeModifiers().contains(OrificeModifier.MUSCLE_CONTROL)
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"Издавая приглушенный [npc.moan], [npc.name] [npc.verb(concentrate)] на сжимании дополнительных внутренних мышц внутри горла [npc.her] вокруг [npc2.namePos] [npc2.cock+].",
						(!isTargetedCharacterInanimate()
							?"[npc.Name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(focus)] на контроле дополнительных мышц, выстилающих внутреннюю часть горла [npc.her]. Сжимая и сдавливая их вокруг [npc2.cock+] во рту [npc.her], [npc.name] [npc.verb(cause)] [npc2.name], заставляя ее издать непроизвольный крик удовольствия."
							:""),
						"[npc.Name] [npc.verb(find)] [npc.her], издавая серию приглушенных [npc.moans], пока [npc.she] [npc.verb(concentrate)] на сжатии дополнительных мышц внутри горла [npc.her] вокруг [npc2.namePos] [npc2.cock+].",
						"С приглушенным [npc.moan], [npc.name] [npc.verb(focus)] на контроле дополнительных мышц глубоко внутри горла [npc.her], сжимая их и массируя [npc2.namePos] [npc2.cock+].");
		}
	};
	
	public static final SexAction BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Получить минет";
		}

		@Override
		public String getActionDescription() {
			return "Вставь свой [npc.cock+] в рот [npc2.namePos] и заставь [npc2.herHim] отсосать тебе.";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить [npc2.namePos] за головку, [npc.name] [npc.verb(line)] [npc.cockHead+] [npc.her] [npc.cock] вверх к [npc2.her] [npc2.lips+], прежде чем мягко подтолкнуть [npc.her] [npc.hips] вперед и скользнуть [npc.her] [npc.cock+] в рот [npc2.her].",

								"Потянувшись вниз, чтобы обхватить головку [npc2.namePos], [npc.name] [npc.verb(push)] [npc.cockHead+] [npc.her] [npc.cock] против [npc2.namePos] [npc2.lips+], а затем медленно вбивая [npc.her] [npc.hips] в [npc2.her] [npc2.face] и осторожно проталкивая [npc.her] [npc.cock+] в [npc2.her] рот."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить головку [npc2.namePos], [npc.name] [npc.verb(line)] [npc.cockHead+] [npc.her] [npc.cock] к [npc2.her] [npc2.lips+], а затем жадно толкая [npc.her] [npc.hips] вперед и задвигая [npc.her] [npc.cock+] в [npc2.her] рот.",

								"Потянувшись вниз, чтобы обхватить головку [npc2.namePos], [npc.name] [npc.verb(push)] [npc.cockHead+] [npc.her] [npc.cock] против [npc2.namePos] [npc2.lips+], а затем жадно вбивая [npc.her] [npc.hips] в [npc2.her] [npc2.face] и проталкивая [npc.her] [npc.cock+] в [npc2.her] рот."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить головку [npc2.namePos], [npc.name] [npc.verb(line)] [npc.cockHead+] [npc.her] [npc.cock] к [npc2.her] [npc2.lips+], а затем грубо впечатывая [npc.her] [npc.hips] вперед и насильно заталкивая [npc.her] [npc.cock+] в [npc2.her] рот.",

								"Потянувшись вниз, чтобы обхватить головку [npc2.namePos], [npc.name] [npc.verb(push)] [npc.cockHead+] [npc.her] [npc.cock] против [npc2.namePos] [npc2.lips+], а затем грубо вбивая [npc.her] [npc.hips] в [npc2.her] [npc2.face] и насильно заталкивая [npc.her] [npc.cock+] в [npc2.her] рот."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Потянувшись вниз, чтобы схватить [npc2.namePos] за головку, [npc.name] [npc.verb(line)] [npc.cockHead+] [npc.her], [npc.cock] вверх до [npc2.her] [npc2.lips+], а затем толкнув [npc.her] [npc.hips] вперед и проталкивая [npc.her] [npc.cock+] в [npc2.her] рот.",

								"Потянувшись вниз, чтобы обхватить головку [npc2.namePos], [npc.name] [npc.verb(push)] [npc.cockHead+] [npc.her], [npc.cock] прижимая к [npc2.namePos] [npc2.lips+], а затем резко вбивая [npc.her] [npc.hips] в [npc2.her] [npc2.face] и проталкивая [npc.her] [npc.cock+] в [npc2.her] рот."));
						break;
					default:
						break;
				}
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LYING_DOWN)) {
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Слегка отстранившись назад, [npc.name] медленно [npc.verb(lower)] [npc.her] [npc.cock+] вниз к [npc2.namePos] рту, а затем осторожно проталкивая [npc.cockHead+] сквозь [npc2.her] [npc2.lips+] и бессильно опускаясь на [npc2.her] [npc2.face+].",

								"Мягко опуская [npc.her] [npc.cock+] к [npc2.namePos] рту, [npc.name] медленно [npc.verb(allow)] [npc.her] [npc.legs] так, чтобы она поддалась под [npc.herHim], пока [npc.she] проталкивает [npc.cockHead+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Слегка отстранившись назад, [npc.name] быстро [npc.verb(drop)] [npc.her] [npc.cock+] вниз к [npc2.namePos] рту, а затем жадно проталкивая [npc.cockHead+] сквозь [npc2.her] [npc2.lips+] и бессильно опускаясь на [npc2.her] [npc2.face+].",

								"Быстро опуская [npc.her] [npc.cock+] к [npc2.namePos] рту, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs], так что [npc.herHim] поддается, пока [npc.she] жадно проталкивает [npc.cockHead+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Немного отстранившись, [npc.name] грубо [npc.verb(grind)] [npc.her] [npc.cock+] к [npc2.namePos] рту, после чего с силой проталкивает [npc.cockHead+] сквозь [npc2.her] [npc2.lips+] и тяжело опускается на [npc2.her] [npc2.face+].",

								"Грубо втирая [npc.her] [npc.cock+] в [npc2.namePos] рот, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs], так что [npc.herHim] поддается, пока [npc.she] проталкивает [npc.cockHead+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Немного отстранившись, [npc.name] быстро [npc.verb(drop)] [npc.her] [npc.cock+] к [npc2.namePos] рту, после чего проталкивает [npc.cockHead+] сквозь [npc2.her] [npc2.lips+] и тяжело опускается на [npc2.her] [npc2.face+].",

								"Быстро опуская [npc.her] [npc.cock+] к [npc2.namePos] рту, [npc.name] [npc.verb(allow)] [npc.her] [npc.legs], так что [npc.herHim] поддается, пока [npc.she] проталкивает [npc.cockHead+] сквозь [npc2.her] [npc2.lips+]."));
						break;
					default:
						break;
				}
				
			} else {
			
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Приставляя [npc.cockHead+] [npc.her] [npc.cock] к [npc2.namePos] [npc2.lips+], [npc.name] мягко [npc.verb(push)] [npc.her] [npc.hips] вперед и [npc.verb(slide)] [npc.her] [npc.cock+] в [npc2.her] рот.",

								"Прижимая [npc.cockHead+] [npc.her] [npc.cock] к [npc2.namePos] [npc2.lips+], [npc.name] медленно [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], плавно вводя [npc.her] [npc.cock+] в рот [npc2.her]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выровняв [npc.cockHead+] [npc.her] [npc.cock] по линии [npc2.namePos] [npc2.lips+], [npc.name] с нетерпением [npc.verb(push)] [npc.her] [npc.hips] вперед и [npc.verb(slide)] [npc.her] [npc.cock+] в [npc2.namePos] рот.",

								"Прижимая [npc.cockHead+] [npc.her] [npc.cock] к [npc2.namePos] [npc2.lips+], [npc.name] жадно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], скользя [npc.her] [npc.cock+] в рот [npc2.her]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Приставляя [npc.cockHead+] [npc.her] [npc.cock] к [npc2.namePos] [npc2.lips+], [npc.name] грубо вбивает [npc.her] [npc.hips] вперед и [npc.verb(force)] [npc.her] [npc.cock+] в рот [npc2.namePos].",

								"Втирая [npc.cockHead+] [npc.her] [npc.cock] в [npc2.namePos] [npc2.lips+], [npc.name] грубо [npc.verb(slam)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], втискивая [npc.her] [npc.cock+] в рот [npc2.her]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Приставляя [npc.cockHead+] [npc.her] [npc.cock] к [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(push)] [npc.her] [npc.hips] вперед и [npc.verb(slide)] [npc.her] [npc.cock+] в рот [npc2.her].",

								"Прижимая [npc.cockHead+] [npc.her] [npc.cock] к [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], скользя [npc.her] [npc.cock+] в [npc2.her] рот."));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], медленно продвигая [npc2.her] головку вперед, пока [npc2.she] [npc2.verb(start)] нежно посасывает [npc.namePos] [npc.cock+].",
	
								" С мягким, приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно продвигает [npc2.her] головку вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] минет."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.a_moan+], жадно продвигая [npc2.her] головку вперед, пока [npc2.she] [npc2.verb(start)] счастливо посасывает [npc.namePos] [npc.cock+].",
	
								" С жадным и очень приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)] счастливо продвигает [npc2.her] головку вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] минет."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.a_moan+], грубо вбивая [npc2.namePos] головку вперед, пока [npc2.she] [npc2.verb(start)] заталкивает [npc.namePos] [npc.cock+] глубоко в [npc2.her] горло.",
	
								" С жадным и очень приглушенным [npc2.moan], [npc2.name] с силой [npc2.verb(push)] [npc2.her] вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] грубый минет."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], проталкивая [npc2.her] вперед, пока [npc2.she] [npc2.verb(start)] сосет [npc.namePos] [npc.cock+].",
	
								" С приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)] подается [npc2.her] вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(start)] делает [npc.herHim] минет."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенным [npc2.sob], булькая и задыхаясь от [npc.namePos] [npc.cock+], пока [npc2.she] отчаянно [npc2.verb(try)], чтобы оттянуть [npc2.her] голову от [npc.her] паха.",
	
								" С приглушенным [npc2.sob], [npc2.name] отчаянно [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.cock+], булькая и задыхаясь, пока [npc2.she] [npc2.verb(squirm)] и [npc2.verb(struggle)] против [npc.herHim]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisMouth.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterPerformingAction());
		}
		
		private String getOngoingNames() {
			return PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterPerformingAction())) {
				return false; // Do not allow additional blowjobs if the performing character is performing autofellatio
			}
			int size = PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();

			map.put("[npc.nameIsFull] not performing autofellatio", !PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).contains(Main.sex.getCharacterPerformingAction()));
			map.put("one or two characters performing blowjob", size>0 && size<3);
			map.put("only ongoing penis-actions are blowjobs", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).size());
			map.put("[npc2.namePos] mouth to be exposed", Main.sex.getCharacterTargetedForSexAction(this).isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc2.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterTargetedForSexAction(this)));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "Присоединиться к минету";
		}
		
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] присоединиться "+getOngoingNames()+" к минету для тебя.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" сосанием [npc.her] [npc.cock], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" слегка отстраниться, а затем приставить покрытый слюной [npc.cockHead] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.lips+] и осторожно ввести его в рот [npc2.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" сосать [npc.her] [npc.cock], [npc.name] [npc.verb(push)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" назад, а затем с силой прижать покрытый слюной [npc.cockHead] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.lips+] и грубо вогнать его в рот [npc2.her]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" сосанием [npc.her] [npc.cock], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" слегка отстраниться, а затем приставить покрытый слюной [npc.cockHead] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.lips+] и ввести его в рот [npc2.her]."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
								"Не довольствуясь лишь "+getOngoingNames()+" сосанием [npc.her] [npc.cock], [npc.name] [npc.verb(get)] "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")+" слегка отстраниться, а затем с нетерпением приставить покрытый слюной [npc.cockHead] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.lips+] и быстро вогнать его в рот [npc2.her]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.moan], медленно продвигая головку [npc2.her] вперед, пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" нежно посасывая [npc.namePos] [npc.cock+].",
								" С мягким, приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)] осторожно продвигая головку [npc2.her] вперед, обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" делая [npc.herHim] минет."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенный [npc2.a_moan+], грубо толкая головку [npc2.namePos] вперед, пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" загоняя [npc.namePos] [npc.cock+] глубоко в [npc2.her] горло.",
								" С жадным и приглушенным [npc2.moan], [npc2.name] с силой [npc2.verb(push)] [npc2.her] вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" в грубом минетном акте для [npc.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], скользя [npc2.her] вперед, пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" в сосании [npc.namePos] [npc.cock+].",
								" С приглушенным [npc2.moan], [npc2.name] [npc2.verb(start)], скользя [npc2.her] вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" делая [npc.herHim] минет."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] приглушенным [npc2.sob], булькая и задыхаясь от [npc.namePos] [npc.cock+], пока [npc2.she] отчаянно [npc2.verb(try)], чтобы оттянуть [npc2.her] голову от [npc.her] паха.",
								" С приглушенным [npc2.sob], [npc2.name] отчаянно [npc2.verb(try)], чтобы отстраниться от [npc.namePos] [npc.cock+], булькая и задыхаясь, пока [npc2.she] [npc2.verb(squirm)] и [npc2.verb(struggle)] против [npc.herHim]."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], жадно скользя [npc2.her] вперед, пока [npc2.she] [npc2.verb(start)] счастливо присоединяется "+getOngoingNames()+" в сосании [npc.namePos] [npc.cock+].",
								" С жадным и очень приглушенным [npc2.moan], [npc2.name] счастливо [npc2.verb(start)], скользя [npc2.her] вперед, обхватывая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], пока [npc2.she] [npc2.verb(join)] "+getOngoingNames()+" делая [npc.herHim] минет."));
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
							" [npc2.Name] жадно [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], издавая очень приглушенный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(bob)] [npc2.her] вверх и вниз.",
		
							" Из рта [npc2.namePos] доносится приглушенный [npc2.moan]; с нетерпением обхватив [npc.namePos] [npc.cock+] своим [npc2.her] [npc2.lips+], [npc2.she] с энтузиазмом [npc2.verb(continue)], чтобы отсосать [npc.herHim].",
		
							" От восторга [npc2.Moaning], [npc2.name] с жадностью [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], усердно облизывая и посасывая [npc.her] [npc.cock], пока [npc2.she] [npc2.verb(continue)] издает приглушенные [npc2.moaning] звуки."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Отчаянно пытаясь, но не в силах отстраниться от [npc.namePos] [npc.cock], [npc2.name] [npc2.verb(let)] [npc2.a_sob+], слезы текут по [npc2.her] [npc2.face], пока [npc2.she] слабо [npc2.verb(make)] издает приглушенные звуки протеста.",
		
							" Приглушенный [npc2.sob] вырывается из рта [npc2.namePos], пока [npc2.she] слабо [npc2.verb(try)], чтобы отстраниться, слезы текут по [npc2.her] [npc2.face], пока [npc.namePos] [npc.cock+] продолжает скользить туда-сюда по [npc2.her] [npc2.lips+].",
		
							" В смятении [npc2.Sobbing], со слезами, бегущими по [npc2.her] [npc2.face], [npc2.name] слабо [npc2.verb(struggle)] против [npc.name], пока [npc2.she] [npc2.verb(make)] издает приглушенные звуки протеста."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], издавая очень приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(bob)] [npc2.her] головку вверх и вниз.",
		
							" Из рта [npc2.namePos] доносится приглушенный [npc2.moan]; обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], [npc2.she] [npc2.verb(continue)], чтобы отсосать [npc.herHim].",
		
							" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], вылизывая и посасывая [npc.her] [npc.cock], пока [npc2.she] [npc2.verb(continue)] издает приглушенные [npc2.moaning] звуки."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] осторожно [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], издавая приглушенный [npc2.moan], пока [npc2.she] медленно [npc2.verb(bob)] [npc2.her] головку вверх и вниз.",
		
							" Из рта [npc2.namePos] доносится приглушенный [npc2.moan]; нежно обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], [npc2.she] с любовью [npc2.verb(continue)], чтобы отсосать [npc.herHim].",
		
							" Восторженная [npc2.Moaning], [npc2.name] мягко [npc2.verb(wrap)] [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], нежно лижет и сосет [npc.her] [npc.cock], пока [npc2.she] [npc2.verb(continue)] издает приглушенные [npc2.moaning] звуки."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] с силой [npc2.verb(grip)] [npc2.her] [npc2.lips+] вниз вокруг [npc.namePos] [npc.cock+], издавая приглушенный [npc2.moan], пока [npc2.she] грубо [npc2.verb(bob)] [npc2.her] головку вверх и вниз.",
		
							" Из рта [npc2.namePos] доносится приглушенный [npc2.moan]; с силой обхватив [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.cock+], [npc2.she] грубо [npc2.verb(continue)], чтобы отсосать [npc.herHim].",
		
							" От восторга [npc2.Moaning], [npc2.name] грубо [npc2.verb(grip)] [npc2.her] [npc2.lips+] вниз вокруг [npc.namePos] [npc.cock+], с силой вылизывая и посасывая [npc.her] [npc.cock], пока [npc2.she] [npc2.verb(continue)], издавая приглушенные [npc2.moaning] звуки."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Мягко принимать минет";
		}

		@Override
		public String getActionDescription() {
			return "Замереть и позволить [npc2.name] сосать твой [npc.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" слегка отодвинуться, [npc.name] нежно [npc.verb(slide)] [npc.her] [npc.cock+] мимо [npc2.namePos] [npc2.lips+], издавая тихий [npc.moan], пока [npc.she] неуклонно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"Мягко наставляя "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволив [npc2.name] взять инициативу на себя, [npc.name] медленно [npc.verb(push)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая тихий [npc.moan], пока [npc.she] ритмично [npc.verb(fuck)] [npc2.her] горло.",
							"После того как сказала "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" чтобы освободить место для [npc2.name], [npc.name] осторожно [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая тихий [npc.moan], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы сосать [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] осторожно [npc.verb(slide)] [npc.her] [npc.cock+] сквозь [npc2.namePos] [npc2.lips+], издавая тихий [npc.moan], пока [npc.she] уверенно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"[npc.Name] медленно [npc.verb(push)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая тихий [npc.moan], пока [npc.she] нежно [npc.verb(fuck)] [npc2.her] горло.",
							"Мягко подталкивая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(continue)] получает [npc.her] минет."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Получить минет";
		}

		@Override
		public String getActionDescription() {
			return "Подвинь свой [npc.cock+] к лицу [npc2.namePos], чтобы подбодрить [npc2.herHim] продолжать делать тебе минет.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" немного отодвинуться, [npc.name] с нетерпением [npc.verb(slide)] [npc.her] [npc.cock+] сквозь [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"Нетерпеливо инструктируя "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] взять инициативу в свои руки, [npc.name] отчаянно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] с нетерпением [npc.verb(fuck)] [npc2.her] горло.",
							"После того как сказала "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], [npc.name] с энтузиазмом [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы сосать [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] нетерпеливо [npc.verb(thrust)] [npc.her] сквозь [npc.cock+] [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"[npc.Name] отчаянно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] с нетерпением [npc.verb(fuck)] [npc2.her] горло.",
							"Энергично вбивая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] счастливо [npc.verb(continue)] [npc.her] минет."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Фейсфак";
		}

		@Override
		public String getActionDescription() {
			return "Грубо втисни свой [npc.cock+] в [npc2.namePos] горло и хорошенько оттрахай [npc2.herHim] прямо в лицо.";
		}

		@Override
		public String getDescription() {
			List<String> descriptions = new ArrayList<>();

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.STANDING)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING)) {

				if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
					
					UtilText.nodeContentSB.setLength(0);
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Принуждая "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" отодвинуться,",
								"Нетерпеливо крича на "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] вести за собой,",
								"После приказа "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" чтобы освободить место для [npc2.name],"));
					
					for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
						switch(pm) {
							case BARBED:
								descriptions.add(" [npc.name] [npc.verb(bury)] глубоко в [npc2.namePos] горле [npc.her] [npc.cock+]. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] [npc2.face], отчего из [npc2.her] [npc2.eyes] начинают катиться слезы, пока шипы, покрывающие ствол [npc.namePos], раз за разом скребут по стенкам [npc2.her] горла.");
								break;
							case FLARED:
								descriptions.add(" [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] затем приступает к тому, чтобы грубо трахать [npc2.her] [npc2.face], заставляя слезы течь из [npc2.her] [npc2.eyes], пока [npc.namePos] широкий, раздутый головка пробивается вверх и вниз по [npc2.her] горлу.");
								break;
							case KNOTTED:
								descriptions.add(" [npc.name] внезапно и яростно [npc.verb(bury)] [npc.her] глубоко в [npc.cock+] [npc2.namePos]. Удерживая [npc2.her] за голову обеими [npc.hands], [npc.she] начинает грубо трахать [npc2.her] [npc2.face], отчего из [npc2.her] [npc2.eyes] начинают течь слезы, пока [npc.namePos] [npc.verb(slam)] [npc.her] раз за разом бьется об [npc2.her] [npc2.lips+].");
								break;
							default:
								break;
						}
					}
					
					descriptions.add(" [npc.name] [npc.verb(grab)] за голову [npc2.namePos], и прежде чем [npc2.she] успевает [npc2.verb(know)], что происходит, [npc.sheIs] грубо вколачивает [npc.her] [npc.cock+] туда-сюда в [npc2.her] ротовой ебальник.");
					descriptions.add(" [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] до самого дна [npc2.namePos] горла. Пока [npc2.she] [npc2.verb(blink)] слезы наворачиваются на глаза, [npc.name] [npc.verb(start)] стремительно дергается [npc.her] [npc.hips] вперед-назад, удерживая [npc2.namePos] голову обеими руками, пока [npc.she] безжалостно [npc.verb(fuck)] [npc2.her] [npc2.face].");
					descriptions.add(" [npc.name] грубо [npc.verb(pull)] [npc2.her] лицо в [npc.her] пах, погружая [npc.her] [npc.cock+] глубоко в [npc2.her] горло, прежде чем начать безжалостно трахать [npc2.her] [npc2.face].");
					descriptions.add(" [npc.name] грубо [npc.verb(hilt)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. Пока склизкая струйка слюны "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"и предэякулятом ":"")
										+"стекает из уголков [npc2.her] рта, [npc.name] откидывается назад, давая [npc2.name] на мгновение судорожно вздохнуть, прежде чем начать агрессивно трахать [npc2.her] [npc2.face].");

					
					return UtilText.nodeContentSB.toString()
							+Util.randomItemFrom(descriptions);
					
				} else {
					for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
						switch(pm) {
							case BARBED:
								descriptions.add("Резким, яростным толчком вперед, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] [npc2.face], отчего из [npc2.her] [npc2.eyes] начинают течь слезы, пока шипы на [npc.namePos] стволе раз за разом скребут по стенкам [npc2.her] горла.");
								break;
							case FLARED:
								descriptions.add("Резким, яростным толчком вперед, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] начинает грубо трахать [npc2.her] [npc2.face], отчего из [npc2.her] [npc2.eyes] начинают катиться слезы, пока [npc.namePos] широкий, раздутый головка прорывается вверх и вниз по [npc2.her] горлу.");
								break;
							case KNOTTED:
								descriptions.add("Резким, яростным толчком вперед, [npc.name] [npc.verb(bury)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. Удерживая [npc2.her] голову обеими [npc.hands], [npc.she] начинает грубо трахать [npc2.her] [npc2.face], отчего из [npc2.her] [npc2.eyes] начинают катиться слезы, пока [npc.namePos] [npc.verb(slam)] [npc.her] узел раз за разом о [npc2.her] [npc2.lips+].");
								break;
							default:
								break;
						}
					}
					
					descriptions.add("[npc.Name] [npc.verb(grab)] бока [npc2.namePos] головки, и прежде чем [npc2.she] [npc2.verb(know)], что происходит, [npc.sheIs] начинает грубо вбивать [npc.her] [npc.cock+] туда-сюда в [npc2.her] рот-дырочку.");
					descriptions.add("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] до самого дна [npc2.namePos] горла. Пока [npc2.she] [npc2.verb(blink)] слезы, [npc.name] [npc.verb(start)] стремительно дергает [npc.her] [npc.hips] туда-сюда, удерживая [npc2.namePos] голову обеими руками, пока [npc.she] безжалостно [npc.verb(fuck)] [npc2.her] [npc2.face].");
					descriptions.add("Схватив [npc2.namePos] за голову, [npc.name] грубо [npc2.verb(pull)] [npc2.her] в [npc.her] пах, погружая [npc.her] [npc.cock+] глубоко в [npc2.her] горло, прежде чем начать безжалостно трахать [npc2.her] [npc2.face].");
					descriptions.add("Мощным толчком [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. Сливистая струйка слюны "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"и предэякулятом ":"")
										+"стекает из уголков [npc2.her] рта, [npc.name] откидывается назад, давая [npc2.name] на мгновение судорожно вздохнуть, прежде чем начать агрессивно трахать [npc2.her] [npc2.face].");
					
					return Util.randomItemFrom(descriptions);
				}
				
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				
				for(PenetrationModifier pm : Main.sex.getCharacterPerformingAction().getPenisModifiers()) {
					switch(pm) {
						case BARBED:
							descriptions.add("Разведя [npc.her] колени по обе стороны от головы [npc2.namePos], [npc.name] яростно [npc.verb(thrust)] вниз, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. На мгновение прижимая основание к [npc2.her] [npc2.lips+], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] в лицо, заставляя [npc2.name] издать приглушенный [npc2.moan], пока [npc2.she] пытается [npc2.verb(struggle)] дышать, и, извиваясь под [npc.name], [npc2.she] [npc2.verb(feel)], пока [npc2.her] горло раздирают шипы, идущие вдоль краев [npc.her] [npc.cock+].");
							break;
						case BLUNT:
							break;
						case FLARED:
							descriptions.add("Разводя [npc.her] колени по обе стороны от [npc2.namePos] головы, [npc.name] яростно [npc.verb(thrust)] вниз, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. На мгновение прижимая основание к [npc2.her] [npc2.lips+], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] лицо, заставляя [npc2.name] издавать приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(struggle)], чтобы вдохнуть; и, извиваясь под [npc.name], [npc2.she] [npc2.verb(feel)], пока [npc2.her] горло растягивается широкой, раздутой головкой [npc.her] [npc.cock+].");
							break;
						case KNOTTED:
							descriptions.add("Разводя [npc.her] колени по обе стороны от [npc2.namePos] головы, [npc.name] яростно [npc.verb(thrust)] вниз, заталкивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. На мгновение прижимая основание к [npc2.her] [npc2.lips+], [npc.she] затем [npc.verb(proceed)], чтобы начать грубо трахать [npc2.her] лицо, заставляя [npc2.name] издавать приглушенный [npc2.moan], пока [npc2.she] [npc2.verb(struggle)], чтобы вдохнуть; и, извиваясь под [npc.name], [npc2.she] [npc2.verb(feel)], пока [npc2.her] [npc2.lips+] раз за разом получает удары жирным узлом у основания [npc.her] [npc.cock+].");
							break;
						case PREHENSILE:
						case RIBBED:
						case SHEATHED:
						case TAPERED:
						case TENTACLED:
						case VEINY:
						case OVIPOSITOR:
							break;
					}
				}

				if(Main.sex.getCharacterPerformingAction().hasLegs()) {
					descriptions.add("[npc.Name] [npc.verb(spread)] [npc.her] колени по обе стороны от [npc2.namePos] головы, и прежде чем [npc2.she] [npc2.verb(know)] осознает, что происходит, [npc.nameIs] грубо вколачивает [npc.her] [npc.cock+] туда-сюда в [npc2.her] ротовую дыру.");
					
				} else {
					descriptions.add("[npc.Name] [npc.verb(push)] [npc.her] пах прямо на [npc2.namePos] лицо, и прежде чем [npc2.she] [npc2.verb(know)] поймет, что происходит, [npc.nameIs] грубо вколачивает [npc.her] [npc.cock+] туда-сюда в [npc2.her] ротовую дыру.");
				}

				descriptions.add("Издавая [npc.a_moan+], [npc.name] [npc.verb(slam)] [npc.her] [npc.cock+] до самого дна [npc2.namePos] горла. Пока [npc2.she] [npc2.verb(blink)] зад разрывается, [npc.name] [npc.verb(start)] стремительно вколачивает [npc.her] [npc.hips] вверх-вниз, издавая всё больше [npc.moans+], пока [npc.she] безжалостно [npc.verb(fuck)] [npc2.her] [npc2.face].");

				descriptions.add("Опустившись на [npc2.namePos] лицо, [npc.name] грубо вгоняет [npc.her] [npc.cock+] глубоко в [npc2.her] горло, издавая [npc.a_moan+], прежде чем начать неистово вколачивать [npc.her] [npc.hips] вверх-вниз, пока [npc.she] безжалостно [npc.verb(fuck)] [npc2.her] лицо.");

				descriptions.add("Мощным толчком [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло. Пока слизистая струя "+(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM)?"cummy ":"")
									+"Слюна стекает по уголкам рта [npc2.her], [npc.name] приподнимает [npc.herself], позволяя [npc2.name] на мгновение жадно глотнуть воздуха, прежде чем снова опуститься и начать агрессивно трахать [npc2.her] в лицо.");

				return Util.randomItemFrom(descriptions);
				
			} else {
				UtilText.nodeContentSB.setLength(0);

				if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Принуждая "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" отступить в сторону, чтобы [npc.name] грубо [npc.verb(slam)] [npc.her] [npc.cock+] мимо [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] неистово [npc.verb(thrust)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
								"Нетерпеливо крича на "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] вести, чтобы [npc.name] яростно [npc.verb(slam)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] горло.",
								"После приказа "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], чтобы [npc.name] агрессивно [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] с силой [npc.verb(fuck)] [npc2.her] лицо."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] грубо [npc.verb(slam)] [npc.her] [npc.cock+] мимо [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] неистово [npc.verb(thrust)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
								"[npc.Name] яростно [npc.verb(slam)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] грубо [npc.verb(fuck)] [npc2.her] горло.",
								"Агрессивно вбивая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(continue)] [npc2.her] лицо."));
				}
				UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
				
				return UtilText.nodeContentSB.toString();
			
			}
		}
		
	};
	
	public static final SexAction BLOWJOB_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться минетe";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться отстраниться от [npc2.namePos] [npc2.cock].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], чтобы оттянуть [npc.her] бедра от [npc2.namePos] лица, но [npc.her] усилия оказываются напрасными, так как [npc2.she] мягко, но уверенно [npc2.verb(hold)] [npc.herHim] на месте, оставляя поцелуй за поцелуем на щеках [npc.her] [npc.cock+] и [npc.balls], пока [npc3.name] [npc3.verb(continue)] навязывает [npc.herHim] нежелательный минет.",
									"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] изо рта [npc3.namePos]. Однако хватка [npc2.NamePos] оказывается слишком крепкой, и [npc2.she] мягко, но уверенно [npc2.verb(hold)] [npc.herHim] на месте, целуя края [npc.her] [npc.cock+], пока [npc3.namePos] [npc3.lips+] скользят вверх и вниз вокруг [npc.her] [npc.cock].",
									"[npc.Sobbing+], [npc.name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] изо рта [npc3.namePos], но [npc2.namePos] хватка слишком сильна, чтобы [npc.herHim] мог вырваться, и попытки [npc.her] оказываются тщетными, так как [npc2.she] [npc2.verb(hold)] [npc.herHim] на месте, нежно целуя бока и основание [npc.her] [npc.cock], пока [npc3.name] [npc3.verb(bob)] [npc3.her] вверх и вниз на [npc.her].")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Издавая [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь отстранить [npc.her] бедра от лица [npc2.namePos], но усилия [npc.her] оказываются напрасными, так как [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, оставляя поцелуй за поцелуем на боках [npc.her] [npc.cock+] и [npc.balls], пока [npc3.name] [npc3.verb(continue)] навязывает [npc.herHim] нежелательный минет.",
									"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock+] изо рта [npc3.namePos]. Однако хватка [npc2.NamePos] оказывается слишком крепкой, и [npc2.she] властно [npc2.verb(hold)] [npc.herHim] на месте, [npc2.verb(let)] угрожающее рычание, пока [npc3.namePos] [npc3.lips+] движется вверх и вниз вокруг [npc.her] [npc.cock].",
									"[npc.Sobbing+], [npc.name] отчаянно [npc.verb(try)], пытаясь вырвать [npc.her] [npc.cock+] изо рта [npc3.namePos], но хватка [npc2.namePos] слишком крепка, чтобы [npc.herHim] мог вырваться, и попытки [npc.her] оказываются тщетными, пока [npc2.she] уверенно [npc2.verb(hold)] [npc.herHim] на месте, грубо целуя бока и основание [npc.her] [npc.cock], в то время как [npc3.name] [npc3.verb(bob)] [npc3.her] вверх и вниз на [npc.her].")));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Издавая [npc.a_sob+], [npc.name] лихорадочно [npc.verb(try)], пытаясь оттянуть бедра [npc.her] от лица [npc2.namePos], но усилия [npc.her] оказываются напрасными, так как [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, оставляя страстный поцелуй за поцелуем на боках [npc.her] [npc.cock+] и [npc.balls], пока [npc3.name] [npc3.verb(continue)] навязывает [npc.herHim] нежелательный минет.",
									"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] изо рта [npc3.namePos]. Однако хватка [npc2.NamePos] оказывается слишком крепкой, и [npc2.she] уверенно [npc2.verb(hold)] [npc.herHim] на месте, целуя края [npc.her] [npc.cock+], пока [npc3.namePos] [npc3.lips+] скользят вверх и вниз вокруг [npc.her] [npc.cock].",
									"[npc.Sobbing+], [npc.name] [npc.verb(try)], пытаясь вытянуть [npc.her] [npc.cock+] изо рта [npc3.namePos], но хватка [npc2.namePos] слишком сильна, чтобы [npc.herHim] мог вырваться, и попытки [npc.her] оказываются тщетными, пока [npc2.she] [npc2.verb(hold)] [npc.herHim] на месте, жадно целуя бока и основание [npc.her] [npc.cock], в то время как [npc3.name] [npc3.verb(bob)] [npc3.her] головку вверх-вниз на [npc.her].")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] бедра от [npc2.namePos] лица, но [npc.her] усилия оказываются напрасными, так как [npc2.she] мягко, но уверенно [npc2.verb(hold)] [npc.herHim] на месте, медленно двигая [npc2.her] [npc2.lips+] вверх и вниз по всей длине [npc.her] [npc.cock+], пока [npc2.she] [npc2.verb(continue)] навязывает [npc.herHim] нежелательный минет.",
	
							"В [npc.eyes] [npc.namePos] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] изо рта [npc2.namePos]. Однако [npc2.Her] хватка оказывается слишком сильной, и [npc2.name] мягко, но уверенно [npc.verb(continue)] сосать [npc.her] [npc.cock+], пока [npc.she] слабо сопротивляется [npc2.herHim].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] изо рта [npc2.namePos], но хватка [npc2.her] слишком сильна для [npc.herHim], и попытки [npc.her] оказываются тщетными, пока [npc2.name] [npc2.verb(continue)], доставляя [npc.herHim] медленный, нежный минет."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь отстранить [npc.her] бедра от [npc2.namePos] лица, но усилия [npc.her] оказываются напрасными, так как [npc2.she] грубо [npc2.verb(hold)] [npc.herHim] на месте, угрожающе рыча, пока [npc2.she] с силой [npc2.verb(slide)] [npc2.her] [npc2.lips+] вверх и вниз по всей длине [npc.her] [npc.cock+].",
	
							"В глазах [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и вместе с [npc.a_sob+], [npc.she] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] изо рта [npc2.namePos]. Однако хватка [npc2.Her] оказывается слишком крепкой, и [npc2.name] [npc2.verb(let)] угрожающее рычание, пока [npc2.name] [npc2.verb(continue)], чтобы сосать [npc.her] [npc.cock+].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)], пытаясь вытащить [npc.her] [npc.cock+] изо рта [npc2.namePos], но хватка [npc2.her] слишком сильна для [npc.herHim], и попытки [npc.her] оказываются тщетными, пока [npc2.name] [npc2.verb(continue)], доставляя [npc.herHim] грубый, напористый минет."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Издавая [npc.a_sob+], [npc.name] отчаянно [npc.verb(try)], пытаясь оттянуть [npc.her] бедра от [npc2.namePos] лица, но [npc.her] усилия оказываются тщетными, так как [npc2.she] крепко [npc2.verb(hold)] [npc.herHim] на месте, с жадностью двигая [npc2.her] [npc2.lips+] вверх и вниз по всей длине [npc.her] [npc.cock+], пока [npc2.she] [npc2.verb(continue)] навязывает [npc.herHim] нежеланный минет.",
	
							"В [npc.namePos] [npc.eyes] начинают наворачиваться слезы, и при [npc.a_sob+], [npc.she] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock+] изо рта [npc2.namePos]. Однако хватка [npc2.Her] оказывается слишком крепкой, и [npc2.she] с жадностью [npc.verb(continue)], чтобы сосать [npc.her] [npc.cock+], пока [npc.she] слабо сопротивляется [npc2.herHim].",
	
							"[npc.Sobbing+], [npc.name] [npc.verb(try)], чтобы вытащить [npc.her] [npc.cock+] изо рта [npc2.namePos], но хватка [npc2.her] слишком сильна для [npc.herHim], и попытки [npc.her] оказываются напрасными, пока [npc2.name] [npc2.verb(continue)] с жадностью делает [npc.herHim] минет."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Получить минет";
		}

		@Override
		public String getActionDescription() {
			return "Подвинь свой [npc.cock+] к лицу [npc2.namePos], чтобы подбодрить [npc2.herHim] продолжать делать тебе минет.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" немного отодвинуться, [npc.name] [npc.verb(thrust)] [npc.her] [npc.cock+] мимо [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"Инструкция "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] взять инициативу на себя, когда [npc.name] [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(fuck)] [npc2.her] горло.",
							"После того как сказала "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], когда [npc.name] [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы сосать [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.cock+] мимо [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"[npc.Name] [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(fuck)] [npc2.her] горло.",
							"Дергая [npc.her], [npc.hips+] [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(continue)] с удовольствием принимает [npc.her] минет."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			return "Жадно принимать минет";
		}

		@Override
		public String getActionDescription() {
			return "Энергично втискивать свой [npc.hips] в лицо [npc2.namePos], заталкивая свой [npc.cock+] глубоко в [npc2.her] горло.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(performer);
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterPerformingAction()).size()>1 && !primary.equals(target)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Получение "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" немного отодвинуться, [npc.name] с нетерпением [npc.verb(slide)] [npc.her] [npc.cock+] сквозь [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"Нетерпеливо инструктируя "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" позволить [npc2.name] взять инициативу в свои руки, [npc.name] отчаянно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] с нетерпением [npc.verb(fuck)] [npc2.her] горло.",
							"После того как сказала "+PenisMouth.getOngoingNames(Main.sex.getCharacterPerformingAction(), target)+" освободить место для [npc2.name], [npc.name] с энтузиазмом [npc.verb(buck)] [npc.her] [npc.hips+] в [npc2.her] [npc2.face], издавая [npc.a_moan+], пока [npc.she] [npc.verb(get)] [npc2.herHim], чтобы сосать [npc.her] [npc.cock+]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] нетерпеливо [npc.verb(thrust)] [npc.her] сквозь [npc.cock+] [npc2.namePos] [npc2.lips+], издавая [npc.a_moan+], пока [npc.she] жадно [npc.verb(pump)] [npc.her] [npc.hips] в [npc2.her] [npc2.face].",
							"[npc.Name] отчаянно [npc.verb(buck)] [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], издавая [npc.a_moan+], пока [npc.she] с нетерпением [npc.verb(fuck)] [npc2.her] горло.",
							"Энергично вбивая [npc.her] [npc.hips+] в [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] счастливо [npc.verb(continue)] [npc.her] минет."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить минет";
		}

		@Override
		public String getActionDescription() {
			return "Вытащить свой [npc.cock+] изо рта [npc2.namePos] и прекратить получать минет от [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				UtilText.nodeContentSB.append("Используя [npc.her] колени, чтобы приподнять [npc.herself], [npc.name] [npc.verb(allow)] [npc.her] [npc.cock+], скользя вверх и из [npc2.namePos] рта. Слизистая нить слюны на мгновение соединяет [npc2.namePos] [npc2.lips+] с [npc.cockHead+] [npc.her] [npc.cock], прежде чем порваться и упасть на лицо [npc2.her].");
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Грубо вколачивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло в последний раз, [npc.name] затем [npc2.verb(pull)] [npc.her] [npc.hips] назад, ухмыляясь, пока [npc2.name] [npc2.verb(splutter)] и [npc2.verb(gasp)], жадно хватая ртом воздух.",

								"Вбивая [npc.her] [npc.hips] в [npc2.namePos] [npc2.face], [npc.name] силой проталкивает [npc.her] [npc.cock+] глубоко в [npc2.her] горло, прежде чем полностью вытянуть его из [npc2.her] рта."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Выскальзывая [npc.her] [npc.cock+] из [npc2.namePos] рта, [npc.name] [npc.verb(let)] из [npc.a_moan+], когда [npc.she] ставит точку в этом минете.",

								"С помощью [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] [npc.hips] назад, полностью вытягивая [npc.her] [npc.cock+] из [npc2.namePos] рта."));
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], отчаянно пытаясь вырваться, пока [npc2.she] [npc2.verb(plead)], умоляя [npc.name] отпустить [npc2.herHim].",
	
								" [npc2.Name] [npc2.verb(feel)], слезы текут по щекам [npc2.her], пока [npc2.she] [npc2.verb(try)], пытаясь вырваться из [npc.namePos] хватки, издавая [npc2.a_sob+] и умоляя [npc.herHim] отпустить [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], выдавая [npc2.her] желание продолжать сосать [npc.namePos] [npc.cock+].",
	
								" [npc2.Name] [npc2.moanVerb], когда [npc.name] отстраняется от [npc2.her] рта, выдавая [npc2.her] отчаянное желание продолжать сосать [npc.her] [npc.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	public static final SexAction GIVING_BLOWJOB_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сделать минет";
		}

		@Override
		public String getActionDescription() {
			return "Возьми [npc2.namePos] [npc2.cock+] в рот и начни делать [npc2.herHim] минет.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SIXTY_NINE)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], когда [npc.she] медленно [npc.verb(lower)] [npc.her] голову между [npc2.her] [npc2.legs], страстно целуя [npc2.cockHead+] [npc2.her] [npc2.cock+], прежде чем взять [npc2.herHim] в [npc.her] рот.",

								"Мягко опуская [npc.her] голову между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] долгий, медленный лизь по всей длине [npc2.her] [npc2.cock+], прежде чем взять [npc2.cockHead+] в [npc.her] рот."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], когда [npc.she] жадно [npc.verb(drop)] [npc.her] голову между [npc2.her] [npc2.legs], страстно целуя [npc2.cockHead+] [npc2.her] [npc2.cock+], прежде чем жадно взять [npc2.herHim] в [npc.her] рот.",

								"Жадно опуская [npc.her] голову между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] долгий, влажный лизь по всей длине [npc2.her] [npc2.cock+], прежде чем жадно взять [npc2.cockHead+] в [npc.her] рот."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], пока [npc.she] быстро [npc.verb(drop)] [npc.her] головой между [npc2.her] [npc2.legs], грубо целуя [npc2.cockHead+] [npc2.her] [npc2.cock+], прежде чем с силой заглотить [npc2.herHim] в [npc.her] рот.",

								"Опустив [npc.her] голову между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] грубый влажный лизь по всей длине [npc2.her] [npc2.cock+], прежде чем с силой заглотить [npc2.cockHead+] в [npc.her] рот."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Горячее дыхание [npc.NamePos] падает на пах [npc2.namePos], пока [npc.she] [npc.verb(drop)] [npc.her] головой между [npc2.her] [npc2.legs], целуя [npc2.cockHead+] [npc2.her] [npc2.cock+], прежде чем взять [npc2.herHim] в [npc.her] рот.",

								"Опустив [npc.her] голову между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(deliver)] долгий влажный лизь по всей длине [npc2.her] [npc2.cock+], прежде чем взять [npc2.cockHead+] в [npc.her] рот."));
						break;
					default:
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Прижимая [npc.her] [npc.lips+] к [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] медленно [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] делает [npc2.herHim] минет.",

								"Облепив [npc.her] [npc.lips+] вокруг [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] нежно отсасывает [npc2.herHim]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Поднося [npc.her] [npc.lips+] к [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] с жадностью [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] счастливо [npc.verb(start)] делает [npc2.herHim].",

								"Обернув [npc.her] [npc.lips+] вокруг [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] с жадностью [npc.verb(start)] делает страстный [npc2.herHim]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Поднося [npc.her] [npc.lips+] к [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] с силой [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] делает грубый [npc2.herHim].",

								"С силой обернув [npc.her] [npc.lips+] вокруг [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] делает грубый [npc2.herHim]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Поднося [npc.her] [npc.lips+] к самому [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(take)] [npc2.herHim] в [npc.her] рот, издавая приглушенный [npc.moan], пока [npc.she] [npc.verb(start)] делает [npc2.herHim] минет.",

								"Облепляя [npc.her] [npc.lips+] вокруг [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.verb(let)] приглушенный [npc.moan], пока [npc.she] жадно [npc.verb(start)] делает [npc2.herHim] минет."));
						break;
					default:
						break;
				}
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], тихо [npc2.moaning], пока [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",
	
								" При мягком толчке [npc2.her] [npc2.hips], [npc2.name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enjoy)] ощущение того, как [npc.namePos] [npc.lips+] скользит вверх и вниз по всей длине [npc2.her] [npc2.cock+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.moaning+], пока [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",
	
								" При энергичном толчке [npc2.her] [npc2.hips], [npc2.name] [npc2.verb(let)] [npc2.a_moan+], пока [npc2.she] [npc2.verb(enjoy)] ощущение того, как [npc.namePos] [npc.lips+] скользит вверх и вниз по всей длине [npc2.her] [npc2.cock+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] грубо [npc2.verb(slam)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.moaning+] так что [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",
	
								" При грубом [npc.verb(thrust)] [npc2.her] [npc2.hips], [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], так что [npc2.she] [npc2.verb(enjoy)] ощущение того, как [npc.namePos] [npc.lips+] скользит вверх и вниз по всей длине [npc2.her] [npc2.cock+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(buck)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.moaning+] так что [npc.she] [npc.verb(suck)] [npc2.her] [npc2.cock+].",
	
								" Дергаясь, [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.name] [npc2.verb(let)] из [npc2.a_moan+], так что [npc2.she] [npc2.verb(enjoy)] ощущение того, как [npc.namePos] [npc.lips+] скользит вверх и вниз по всей длине [npc2.her] [npc2.cock+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], пытаясь, но безуспешно вытащить [npc2.her] [npc2.cock] изо рта [npc.namePos], пока [npc2.she] [npc2.verb(struggle)] против нежеланного орального внимания.",
	
								" Со слезами, наворачивающимися на [npc2.her] [npc2.eyes], [npc2.name] [npc2.verb(struggle)] против нежеланного орального внимания, [npc2.sobbing] и извиваясь, пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] прекратил(а)."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction GIVING_BLOWJOB_START_ADDITIONAL = new SexAction(
			SexActionType.START_ADDITIONAL_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {

		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}
		
		//--- Additional methods: ---

		private List<GameCharacter> getOngoingCharacters() {
			return PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public List<GameCharacter> getCharactersForParsing() {
			return PenisMouth.getMultipleOngoingCharactersForParsing(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		private String getOngoingNames() {
			return PenisMouth.getOngoingNames(Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				return false; // Do not allow additional blowjobs if the targeted character is performing autofellatio
			}
			int size = getOngoingCharacters().size();
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
				size--;
			}
			return size>0;
		}
		
		@Override
		public Map<String, Boolean> getAdditionalOngoingAvailableMap() {
			Map<String, Boolean> map = new HashMap<>();

			int size = getOngoingCharacters().size();

			map.put("[npc2.nameIsFull] not performing autofellatio", !PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).contains(Main.sex.getCharacterTargetedForSexAction(this)));
			map.put("one or two characters performing blowjob", size>0 && size<3);
			map.put("only ongoing penis-actions are blowjobs", size==Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS).size());
			map.put("[npc.namePos] mouth to be exposed", Main.sex.getCharacterPerformingAction().isOrificeTypeExposed(SexAreaOrifice.MOUTH));
			map.put("[npc.namePos] mouth to be free", SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction()));
			
			return map;
		}
		
		//------
		
		@Override
		public String getActionTitle() {
			return "Присоединиться к минету";
		}

		@Override
		public String getActionDescription() {
			return "Помочь "+getOngoingNames()+" в оральном обслуживании [npc2.namePos] [npc2.cock+], присоединившись к тому, чтобы отсосать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" к тому, чтобы отсосать [npc2.name], [npc.name] [npc.verb(move)] вперед, рядом с "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+", прежде чем наклониться и нежно поцеловать [npc2.her] [npc2.penisGirth] [npc2.cock] в бок."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" делая [npc2.name] минет, [npc.name] с энтузиазмом [npc.verb(elbow)] [npc.her] рядом с "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+", прежде чем наклониться и грубо поцеловать [npc2.her] [npc2.penisGirth] [npc2.cock] в бок."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" к тому, чтобы отсосать [npc2.name], [npc.name] [npc.verb(move)] вперед, рядом с "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+", прежде чем наклониться и быстро поцеловать [npc2.her] [npc2.penisGirth] [npc2.cock] в бок."));
					break;
				default: // Dom normal and sub eager:
					UtilText.nodeContentSB.append(
							UtilText.parse(getCharactersForParsing(),
							"Желая присоединиться "+getOngoingNames()+" делая [npc2.name] минет, [npc.name] жадно [npc.verb(move)] вперед рядом с "+(getOngoingCharacters().size()==1?"[npc3.herHim]":"их")
								+", прежде чем наклониться и страстно, влажно поцеловать [npc2.her] [npc2.penisGirth] [npc2.cock] в бок."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] в ответ нежный [npc2.moan], и, чтобы "+getOngoingNames()+" временно немного отстраниться, [npc2.she] [npc2.verb(allow)] [npc.name], чтобы взять [npc2.her] [npc2.cock+] в свой [npc.her] рот."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] в ответ довольный [npc2.moan], и, грубо приказывая "+getOngoingNames()+" временно отступить, [npc2.she] [npc2.verb(force)] [npc2.her] [npc2.cock+] в свой [npc.namePos] рот."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan] в ответ, и,  "+getOngoingNames()+" временно немного отстраниться, [npc2.she] [npc2.verb(allow)] [npc.name], чтобы взять [npc2.her] [npc2.cock+] в свой [npc.her] рот."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] встревоженный [npc2.moan] в ответ, который становится только громче и неистовее, когда [npc.name] [npc.verb(push)] "+getOngoingNames()+" в сторону, чтобы заглотить [npc2.her] [npc2.cock+] в [npc.her] рот."));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(
								UtilText.parse(getCharactersForParsing(),
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, и,  "+getOngoingNames()+" чтобы ненадолго немного отступить, [npc2.she] счастливо [npc2.verb(encourage)] [npc.name], чтобы заглотить [npc2.her] [npc2.cock+] в [npc.her] рот."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	
	// TODO add descriptions of kissing knot, flared head pushing down throat, tentacles, ribs and barbs
	
	public static final SexAction GIVING_BLOWJOB_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public void applyEffects(){
			Main.sex.setPrimaryOngoingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Глубокий горловой";
		}

		@Override
		public String getActionDescription() {
			return "Заглотите [npc2.namePos] [npc2.cock+] как можно глубже в свое горло.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Заставив [npc3.name] отстраниться, [npc.name] нежно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], прежде чем толкнуть [npc.her] головку вперед и заглотить ее так глубоко в [npc.her] горло, как только [npc.she] возможно.",
									"С мягким [npc.moan], [npc.name] нежно [npc.verb(pull)] [npc3.name] от [npc2.namePos] [npc2.cock+], прежде чем наклониться вперед, разомкнув [npc.her] [npc.lips+], и заглотив как можно больше [npc2.namePos] [npc2.cock+] в [npc.her] горло, насколько это возможно для [npc.she].",
									"Аккуратно отодвинув [npc3.name] в сторону, [npc.name] [npc.verb(slide)] [npc.her] головку вперед, прежде чем мягко разомкнуть [npc.her] [npc.lips+] и заглотить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] горло.")));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Грубо оттолкнув [npc3.name] в сторону, [npc.name] яростно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], а затем толкнув [npc.her] голову вперед, заставляя её войти так глубоко в [npc.her] глотку, как только [npc.she] возможно.",
									"Схватив [npc.a_moan+], [npc.name] грубо [npc.verb(push)] [npc3.name] от [npc2.namePos] [npc2.cock+], после чего быстро наклонившись вперед, раздвинув [npc.her] [npc.lips+] и с силой заглатывая столько [npc2.namePos] [npc2.cock+] в [npc.her] глотку, сколько только [npc.she] возможно.",
									"Агрессивно отпихнув [npc3.name] в сторону, [npc.name] [npc.verb(push)] [npc.her] голову вперед, после чего жадно раздвинув [npc.her] [npc.lips+] и заталкивая [npc2.namePos] [npc2.cock+] глубоко в [npc.her] глотку.")));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Заставив [npc3.name] отпрянуть, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], а затем толкнув [npc.her] голову вперед и заглатывая её так глубоко в [npc.her] глотку, как только [npc.she] возможно.",
									"Схватив [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc3.name] от [npc2.namePos] [npc2.cock+], после чего наклонившись вперед, раздвинув [npc.her] [npc.lips+] и заглатывая столько [npc2.namePos] [npc2.cock+] в [npc.her] глотку, сколько только [npc.she] возможно.",
									"Убрав [npc3.name] с пути, [npc.name] [npc.verb(slide)] [npc.her] вперед, после чего раздвинув [npc.her] [npc.lips+] и заглатывая [npc2.namePos] [npc2.cock+] глубоко в [npc.her] горло.")));
						break;
					default: // Dom normal and sub eager:
						UtilText.nodeContentSB.append(UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									"Заставив [npc3.name] немного отстраниться, [npc.name] жадно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], а затем резко толкнув [npc.her] вперед и жадно заглатывая его так глубоко в [npc.her] горло, как только позволяет [npc.she].",
									"С [npc.a_moan+], [npc.name] быстро [npc.verb(pull)] [npc3.name] от [npc2.namePos] [npc2.cock+], после чего с нетерпением подается вперед, раздвигая [npc.her] [npc.lips+], и отчаянно заглатывая как можно больше [npc2.namePos] [npc2.cock+] в [npc.her] горло, насколько это возможно для [npc.she].",
									"Нетерпеливо убрав [npc3.name] с пути, [npc.name] [npc.verb(slide)] [npc.her] вперед, после чего жадно раздвинув [npc.her] [npc.lips+] и заглатывая [npc2.namePos] [npc2.cock+] глубоко в [npc.her] горло.")));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Мягко обхватив [npc.her] [npc.lips+] вокруг [npc2.cock+] во рту [npc.her], [npc.name] [npc.verb(push)] [npc.her] вперед, заглатывая [npc2.namePos] [npc2.cock+] так глубоко в [npc.her] горло, как только может [npc.she].",
	
								"Издавая мягкий, приглушенный [npc.moan], [npc.name] осторожно [npc.verb(lean)] вперед, раздвигая [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] как можно больше [npc2.namePos] [npc2.cock+] в [npc.her] горло, насколько может [npc.she].",
	
								"Медленно продвигая головку [npc.her] вперед, [npc.name] нежно [npc.verb(part)] [npc.her] [npc.lips+], прежде чем заглотить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] горло."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"С жадностью обхватив [npc.her] [npc.lips+] вокруг [npc2.cock+] во [npc.her] рту, [npc.name] быстро [npc.verb(push)] [npc.her] вперед, жадно заглатывая [npc2.namePos] [npc2.cock+] так глубоко в [npc.her] горло, насколько это вообще возможно для [npc.she].",
	
								"С приглушенным, [npc.moan+] звуком, [npc.name] с нетерпением [npc.verb(lean)] вперед, разжимая [npc.her] [npc.lips+], пока [npc.she] отчаянно [npc.verb(take)] как можно больше [npc2.namePos] [npc2.cock+] в [npc.her] горло, на сколько хватает [npc.she].",
	
								"Жадно продвигая головку [npc.her] вперед, [npc.name] охотно [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] горло."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Силой обхватив [npc2.cock+] [npc.her] [npc.lips+] вокруг [npc.her] рта, [npc.name] грубо [npc.verb(slam)] [npc.her] вперед, заставляя [npc2.namePos] [npc2.cock+] проникать так глубоко в [npc.her] глотку, насколько это вообще возможно для [npc.she].",
	
								"С приглушенным, [npc.moan+] звуком, [npc.name] быстро [npc.verb(lean)] вперед, разжимая [npc.her] [npc.lips+], пока [npc.she] грубо [npc.verb(force)] как можно больше [npc2.namePos] [npc2.cock+] в [npc.her] горло, на сколько хватает [npc.she].",
	
								"Агрессивно толкая головку [npc.her] вперед, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(force)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] горло."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Обернув [npc.her] [npc.lips+] вокруг [npc2.cock+] во рту [npc.her], [npc.name] быстро [npc.verb(push)] [npc.her] головку вперед, принимая [npc2.namePos] [npc2.cock+] так глубоко в [npc.her] горло, насколько это возможно для [npc.she].",
	
								"С приглушенным, [npc.moan+] звуком, [npc.name] [npc.verb(lean)] вперед, раздвигая [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] как можно больше [npc2.namePos] [npc2.cock+] в [npc.her] горло, насколько может [npc.she].",
	
								"Скользя головкой [npc.her] вперед, [npc.name] [npc.verb(part)] [npc.her] [npc.lips+], пока [npc.she] [npc.verb(take)] [npc2.namePos] [npc2.cock+] глубоко в [npc.her] горло."));
						break;
					default:
						break;
				}
				
				UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(action);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(action)).size()>1) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					default:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
									" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(receive)] [npc2.her] групповой минет.",
									" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло.",
									" От восторга [npc2.Moaning], [npc2.name] с нетерпением [npc2.verb(drive)] [npc2.her] [npc2.cock+] как можно глубже в [npc3.namePos] горло."));
					case SUB_RESISTING:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								" Не в силах оттянуть [npc2.her] [npc2.cock] от "+PenisMouth.getOngoingNames(target)+", [npc2.name] [npc2.verb(let)] [npc2.a_sob+] как [npc2.she], слабо [npc2.verb(try)], пытаясь вырваться.",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть "+PenisMouth.getOngoingNames(target)+" себя, извиваясь и протестуя, пока [npc2.her] [npc2.cock+] продолжает безжалостно ласкать.",
								" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.cock] от "+PenisMouth.getOngoingNames(target)+"."));
					case DOM_GENTLE:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло, издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(receive)] [npc2.her] групповой минет.",
								" Мягкий [npc2.moan] вырывается из [npc2.namePos] рта, прежде чем [npc2.she] [npc2.verb(start)] медленно скользит [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло.",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло."));
					case DOM_ROUGH:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(receive)] [npc2.her] групповой минет.",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] яростно вгоняет [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло.",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] как можно глубже в [npc3.namePos] горло."));
					case SUB_NORMAL:
						return UtilText.parse(Util.newArrayListOfValues(performer, target, primary),
								UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(receive)] [npc2.her] минет.",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] заталкивает [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло.",
								" [npc2.Moaning] в восторге, [npc2.name] с наслаждением [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc3.namePos] горло."));
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
					default:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] жадно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] горло, издавая [npc2.a_moan+], пока [npc2.she] с энтузиазмом [npc2.verb(receive)] [npc2.her] минет.",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] заталкивает [npc2.her] [npc2.cock+] глубоко в [npc.namePos] горло.",
								" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(drive)] [npc2.her] [npc2.cock+] так глубоко, как только возможно, в [npc.namePos] горло.");
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom(
								" Не в силах оттащить [npc2.her] [npc2.cock] от [npc.namePos] рта, [npc2.name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться.",
								" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], чтобы оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.name] [npc.verb(continue)], чтобы загнать [npc2.her] [npc2.cock+] глубоко в [npc.her] горло.",
								" [npc2.Sobbing] в смятении, [npc2.name] [npc2.verb(try)], тщетно пытаясь оттянуть [npc2.her] [npc2.cock] от рта [npc.namePos].");
					case DOM_GENTLE:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в глотку [npc.namePos], издавая тихий [npc2.moan], пока [npc2.she] [npc2.verb(receive)] [npc2.her] минет.",
								" [npc2.A_moan+] медленно выскальзывает изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] медленно проталкивает [npc2.her] глубоко в [npc.namePos] горло [npc2.cock+].",
								" [npc2.Moaning] в восторге, [npc2.name] нежно [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в глотку [npc.namePos].");
					case DOM_ROUGH:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] яростно [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в глотку [npc.namePos], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(receive)] [npc2.her] минет.",
								" [npc2.A_moan+] вылетает изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] грубо вгоняет [npc2.her] [npc2.cock+] глубоко в глотку [npc.namePos].",
								" [npc2.Moaning] в восторге, [npc2.name] грубо [npc2.verb(slam)] [npc2.her] [npc2.cock+] так глубоко, как только возможно, в глотку [npc.namePos].");
					case SUB_NORMAL:
						return UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(thrust)] [npc2.her] [npc2.cock+] глубоко в глотку [npc.namePos], издавая [npc2.a_moan+], пока [npc2.she] [npc2.verb(receive)] [npc2.her] минет.",
								" [npc2.A_moan+] вырывается изо рта [npc2.namePos], прежде чем [npc2.she] [npc2.verb(start)] вонзает [npc2.her] [npc2.cock+] глубоко в глотку [npc.namePos].",
								" [npc2.Moaning] в восторге, [npc2.name] [npc2.verb(slide)] [npc2.her] [npc2.cock+] глубоко в [npc.namePos] горло.");
				}
			}
		}
		return "";
	}
	
	public static final SexAction GIVING_BLOWJOB_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежный минет";
		}

		@Override
		public String getActionDescription() {
			return "Нежно посасывать [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Наклоняясь рядом "+PenisMouth.getOngoingNames(target, performer)+", [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] к открытой части [npc2.namePos] [npc2.cock+], прежде чем начать нежно целовать и лизать [npc2.her] ствол.",
						"С мягким [npc.moan], [npc.name] осторожно [npc.verb(lean)] рядом "+PenisMouth.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободной части [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(kiss)] и [npc.verb(nuzzle)] вплотную к [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] нежно [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.cock+], которую сможет найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.her] [npc.lips+] нежно обхватывают [npc2.namePos] [npc2.cock+], и [npc.name] [npc.verb(start)] покачивает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc2.herHim], отдаваясь нежному минету.",
						"С мягким, приглушенным [npc.moan], [npc.name] нежно [npc.verb(start)] двигает [npc.her] головой вверх и вниз, обхватывая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] головой.",
						"Медленно двигая [npc.her] головой вверх и вниз, [npc.name] нежно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] минет."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сделать минет";
		}

		@Override
		public String getActionDescription() {
			return "Жадно сосать [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Счастливо прильнув рядом, "+PenisMouth.getOngoingNames(target, performer)+", [npc.name] с нетерпением [npc.verb(press)] [npc.her] [npc.lips+] к открытой части [npc2.namePos] [npc2.cock+], прежде чем начать жадно целовать и лизать [npc2.her] ствол.",
						"Вместе с [npc.a_moan+], [npc.name] нетерпеливо [npc.verb(lean)] рядом, "+PenisMouth.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободной части [npc2.namePos] [npc2.cock+], пока [npc.she] отчаянно [npc.verb(kiss)] и [npc.verb(nuzzle)] вплотную к [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] жадно [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.cock+], которую сможет найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С энтузиазмом обхватив [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] быстро [npc.verb(start)] двигает [npc.her] головой вверх и вниз, пока [npc.she] [npc.verb(give)] [npc2.herHim] страстный минет.",
						"С приглушенным, [npc.moan+], [npc.name] быстро [npc.verb(start)] дергает [npc.her] головой вверх-вниз, жадно обхватывая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] голову.",
						"Быстро дергая [npc.her] головой вверх-вниз, [npc.name] отчаянно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] страстный минет."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый минет";
		}

		@Override
		public String getActionDescription() {
			return "Грубо сосать [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Втискиваясь [npc.her] рядом с "+PenisMouth.getOngoingNames(target, performer)+", [npc.name] грубо [npc.verb(press)] [npc.her] [npc.lips+] о обнаженную часть [npc2.namePos] [npc2.cock+], прежде чем начатьforcefully целовать и лизать ствол [npc2.her].",
						"С [npc.a_moan+], [npc.name] агрессивно [npc.verb(push)] вместе с "+PenisMouth.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободному участку [npc2.namePos] [npc2.cock+], пока [npc.she] грубо [npc.verb(kiss)] и [npc.verb(press)] вплотную к [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] с силой [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.cock+], которую сможет найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Сильно сжимая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] агрессивно [npc.verb(start)] дергает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc2.herHim] грубый минет.",
						"С приглушенным, [npc.moan+], [npc.name] яростно [npc.verb(start)] дергает [npc.her] головой вверх-вниз, грубо обхватывая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] голову.",
						"Грубо дергая [npc.her] головой вверх-вниз, [npc.name] доминантно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] сильный минет."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
					
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction BLOWJOB_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться минету";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытолкнуть [npc2.namePos] [npc2.cock+] изо рта.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], корчась и отчаянно пытаясь вырваться, пока [npc2.name] [npc2.verb(hold)] [npc.herHim] на месте вместе с "
										+PenisMouth.getOngoingNames(target, performer)+", мягко вдавливая [npc.her] [npc.face] вглубь [npc2.her] [npc2.cock+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], чтобы оттянуть [npc.her] голову назад и подальше от [npc2.namePos] [npc2.cock+], но движения [npc.her] оказываются напрасными, так как [npc2.name] мягко, но уверенно, [npc2.verb(pull)] [npc.herHim] обратно вместе с "+PenisMouth.getOngoingNames(target, performer)+".",
								"[npc.Name] [npc.verb(feel)] слезы текут по [npc.namePos] щекам, пока [npc.she] слабо [npc.verb(try)], чтобы оттолкнуть [npc2.name], [npc.sobbing] в смятении, когда [npc.sheIs] быстро затягивает обратно к [npc2.her] [npc2.cock+] вместе с "+PenisMouth.getOngoingNames(target, performer)+"."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], корчась и отчаянно пытаясь вырваться, пока [npc2.name] с силой [npc2.verb(hold)] [npc.herHim] на месте вместе с "
										+PenisMouth.getOngoingNames(target, performer)+", грубо вбивая [npc.her] [npc.face] в [npc2.her] [npc2.cock+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть голову [npc.her] назад и прочь от [npc2.namePos] [npc2.cock+], но движения [npc.her] оказываются напрасными, так как [npc2.name] решительно [npc2.verb(force)] [npc.herHim] обратно вместе с "+PenisMouth.getOngoingNames(target, performer)+".",
								"[npc.Name] [npc.verb(feel)], слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name], [npc.sobbing] в смятении, когда [npc.sheIs] быстро заталкивают обратно к [npc2.her] [npc2.cock+] вместе с "+PenisMouth.getOngoingNames(target, performer)+"."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], корчась и отчаянно пытаясь вырваться, пока [npc2.name] [npc2.verb(hold)] [npc.herHim] на месте вместе с "
										+PenisMouth.getOngoingNames(target, performer)+", жадно прижимая [npc.her] [npc.face] к [npc2.her] [npc2.cock+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть голову [npc.her] назад и прочь от [npc2.namePos] [npc2.cock+], но движения [npc.her] оказываются напрасными, так как [npc2.name] уверенно [npc2.verb(pull)] [npc.herHim] обратно вместе с "+PenisMouth.getOngoingNames(target, performer)+".",
								"[npc.Name] [npc.verb(feel)] слезы текут по [npc.namePos] щекам, пока [npc.she] слабо [npc.verb(try)], чтобы оттолкнуть [npc2.name], [npc.sobbing] в смятении, когда [npc.sheIs] быстро затягивает обратно к [npc2.her] [npc2.cock+] вместе с "+PenisMouth.getOngoingNames(target, performer)+"."));
						break;
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], извиваясь и беспорядочно сражаясь от отчаяния, пока [npc2.name] удерживает [npc.herHim] на месте, медленно водя [npc2.her] [npc2.cock+] вперед-назад мимо [npc.her] [npc.lips+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], пытаясь оттянуть голову [npc.her] назад, протесты [npc.her] вырываются булькающими всплесками, пока [npc2.name] нежно [npc2.verb(continue)] [npc2.her] [npc2.cock+] в глубину [npc.her] горла.",
								"[npc.Name] [npc.verb(feel)] слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name]; [npc.sobbing] в смятении, пока [npc2.name] [npc2.verb(continue)] нежно водит [npc2.her] [npc2.cock+] туда-сюда во [npc.her] рту."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], извиваясь и беспокойно ерзая, пока [npc2.name] силой удерживает [npc.herHim] на месте, грубо толкая [npc2.her] [npc2.cock+] вперед-назад мимо [npc.her] [npc.lips+].",
								"С [npc.a_sob+], [npc.name] [npc.verb(try)], оттягивая голову [npc.her] назад; протесты [npc.her] вырываются булькающими всплесками, пока [npc2.name] [npc2.verb(continue)] грубо вбивает [npc2.her] [npc2.cock+] в [npc.her] горло.",
								"[npc.Name] [npc.verb(feel)] слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name]; [npc.sobbing] в смятении, пока [npc2.name] [npc2.verb(continue)] грубо вколачивает [npc2.her] [npc2.cock+] туда-сюда во [npc.her] рот."));
						break;
					default: // DOM_NORMAL and in case anything goes wrong:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name] [npc.verb(let)] приглушенный [npc.sob], извиваясь и беспокойно ерзая, пока [npc2.name] удерживает [npc.herHim] на месте, жадно водя [npc2.her] [npc2.cock+] вперед-назад мимо [npc.her] [npc.lips+].",
								"[npc.a_sob+], [npc.name] [npc.verb(try)], оттягивая [npc.her] за голову, и [npc.her] протестующие звуки, вырывающиеся хриплыми всплесками, пока [npc2.name] с нетерпением [npc2.verb(continue)] [npc2.her] [npc2.cock+] в [npc.her] горло.",
								"[npc.Name] [npc.verb(feel)], слезы текут по щекам [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь оттолкнуть [npc2.name], в то время как [npc.sobbing] в смятении наблюдает, как [npc2.name] с жадностью [npc2.verb(continue)] [npc2.her] [npc2.cock+] в и из [npc.her] рта."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Сделать минет";
		}

		@Override
		public String getActionDescription() {
			return "Продолжать сосать [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Наклоняясь рядом "+PenisMouth.getOngoingNames(target, performer)+", [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] на открытом участке [npc2.namePos] [npc2.cock+], прежде чем начать целовать и лизать ствол [npc2.her].",
						"С [npc.a_moan+], [npc.name] [npc.verb(lean)] вместе с "+PenisMouth.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободной части [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(kiss)] и [npc.verb(nuzzle)] вплотную к [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.cock+], которую сможет найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Обернув [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] [npc.verb(start)] стремительно двигает [npc.her] головой вверх-вниз, пока [npc.she] [npc.verb(give)] [npc2.herHim].",
						"Приглушенно, [npc.moan+], [npc.name] быстро [npc.verb(start)] покачивает [npc.her] головой вверх-вниз, обхватывая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] голову.",
						"Быстро двигая [npc.her] головой вверх-вниз, [npc.name] [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] минет."));
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Жадный минет";
		}

		@Override
		public String getActionDescription() {
			return "Жадно сосать [npc2.namePos] [npc2.cock+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(target);
			
			if(PenisMouth.getOngoingCharacters(Main.sex.getCharacterTargetedForSexAction(this)).size()>1 && !primary.equals(performer)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Счастливо прильнув рядом, "+PenisMouth.getOngoingNames(target, performer)+", [npc.name] с нетерпением [npc.verb(press)] [npc.her] [npc.lips+] к открытой части [npc2.namePos] [npc2.cock+], прежде чем начать жадно целовать и лизать [npc2.her] ствол.",
						"Вместе с [npc.a_moan+], [npc.name] нетерпеливо [npc.verb(lean)] рядом, "+PenisMouth.getOngoingNames(target, performer)+", прижимая [npc.her] [npc.lips+] к свободной части [npc2.namePos] [npc2.cock+], пока [npc.she] отчаянно [npc.verb(kiss)] и [npc.verb(nuzzle)] вплотную к [npc2.herHim].",
						"С [npc.her] головой, расположенной вплотную "+PenisMouth.getOngoingNames(target, performer)
							+", [npc.name] жадно [npc.verb(kiss)] и [npc.verb(lick)] любую открытую часть [npc2.namePos] [npc2.cock+], которую сможет найти [npc.she]."));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"С энтузиазмом обхватив [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], [npc.name] быстро [npc.verb(start)] двигает [npc.her] головой вверх и вниз, пока [npc.she] [npc.verb(give)] [npc2.herHim] страстный минет.",
						"С приглушенным, [npc.moan+], [npc.name] быстро [npc.verb(start)] дергает [npc.her] головой вверх-вниз, жадно обхватывая [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] голову.",
						"Быстро дергая [npc.her] головой вверх-вниз, [npc.name] отчаянно [npc.verb(wrap)] [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.cock+], пока [npc.she] [npc.verb(give)] [npc2.herHim] страстный минет."));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction GIVING_BLOWJOB_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить минет";
		}

		@Override
		public String getActionDescription() {
			return "Вытащить [npc2.namePos] [npc2.cock+] изо рта и перестать делать [npc2.herHim] минет.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо в последний раз вдавив [npc2.namePos] [npc2.cock+] в глотку [npc.her], [npc.name] затем [npc.verb(pull)] [npc.her] назад, быстро завершая [npc2.her].",

							"С силой вбивая [npc.her] [npc.face] в пах [npc2.namePos], [npc.name] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] горло, прежде чем полностью отстраниться и позволить [npc2.herHim] выскользнуть из [npc.her] рта."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Вытягивая [npc2.namePos] [npc2.cock+] изо рта [npc.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], пока [npc.she] [npc.verb(put)] минет.",

							"С [npc.a_moan+], [npc.name] [npc.verb(pull)] [npc.her] назад, полностью вытягивая [npc2.namePos] [npc2.cock+] изо рта [npc.her]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+], сопротивляясь [npc.name], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] отпустил(а) [npc2.herHim].",
	
								" Слезы текут по щекам [npc2.namePos], пока [npc2.she] пытается вырваться из хватки [npc.namePos], издавая [npc2.a_sob+] и умоляя [npc.herHim] отпустить [npc2.herHim]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], обнажая [npc2.her] желание, чтобы [npc.name] продолжал сосать [npc2.her] [npc2.cock+].",
	
								" [npc2.Name] [npc2.moansVerb], когда [npc.name] отстраняется от [npc2.her] паха, выдавая [npc2.her] желание снова почувствовать [npc.her] [npc.lips+], обхвативший [npc2.her] [npc2.cock+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
}
