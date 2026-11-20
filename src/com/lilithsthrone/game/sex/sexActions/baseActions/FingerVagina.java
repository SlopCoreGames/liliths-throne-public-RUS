package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
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
 * @version 0.4.9.8
 * @author Innoxia
 */
public class FingerVagina {
	
	public static final SexAction FINGER_INSEMINATION_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Пальцевое осеменение";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] собрать слюну и сперму на твоем теле и затолкнуть их глубоко в твою киску.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTotalAmountCummedOn(Main.sex.getCharacterPerformingAction())>0
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()));
		}
		private String getRandomCharacterCumDescription(boolean withName) {
			Set<GameCharacter> charactersCummedOnPerformer = Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction()).keySet();
			
			GameCharacter character = Util.randomItemFrom(charactersCummedOnPerformer);
			
			if(character==Main.sex.getCharacterPerformingAction()) {
				return UtilText.parse(character, (withName?"собственный [npc.her] ":"")+"[npc.cum+]");
			} else {
				return UtilText.parse(character, (withName?"[npc.namePos] ":"")+"[npc.cum+]");
			}
		}
		@Override
		public String getDescription() {
			return "Пока [npc.name] [npc.verb(feel)] "+getRandomCharacterCumDescription(true)+" скользя по [npc.her] [npc.skin], в [npc.her] внезапно приходит веселая идея. Потянувшись вниз, [npc.she] [npc.verb(take)] хватает [npc2.namePos] [npc2.hand], дьявольски ухмыляясь, когда [npc2.she] [npc2.verb(pull)] [npc2.her] [npc2.fingers] из [npc.her] [npc.pussy+]. Направляя [npc2.her] [npc2.fingers] к "+getRandomCharacterCumDescription(false)+" размазанной по телу [npc.her], [npc.she] [npc.verb(get)] [npc2.herHim], чтобы собрать немного свежего семени. Как только [npc.sheIs] убеждается, что [npc2.sheHas] собрала достаточно, [npc.she] [npc.verb(push)] [npc2.her] [npc.fingers] обратно в голодную киску [npc.her].<br/>[npc.Name] [npc.verb(grin)], пока [npc.she] [npc.verb(feel)] "+getRandomCharacterCumDescription(false)+" на [npc2.namePos] пальцах, втиснутых глубоко в [npc.her] киску, и [npc.moansVerb+], когда [npc2.name] снова [npc.verb(start)] ласкает [npc.herHim], используя скользкую сперму в качестве смазки. Прижимая [npc.her] бедра к [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] отчаянный, дрожащий [npc.moan] от ощущения, что её осеменяют.";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			Map<GameCharacter, Integer> cumProvidersToTotalCum = new HashMap<>();
			for(Entry<GameCharacter, Map<InventorySlot, Integer>> cumDetails : new HashMap<>(Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction())).entrySet()) {
				for(Entry<InventorySlot, Integer> areaDetails : cumDetails.getValue().entrySet()) {
					int amountOfCumUsed = Math.min(5, areaDetails.getValue());
					cumProvidersToTotalCum.putIfAbsent(cumDetails.getKey(), 0);
					cumProvidersToTotalCum.put(cumDetails.getKey(), cumProvidersToTotalCum.get(cumDetails.getKey())+amountOfCumUsed);
					Main.sex.incrementAmountCummedOn(cumDetails.getKey(), Main.sex.getCharacterPerformingAction(), areaDetails.getKey(), -amountOfCumUsed); // Remove the cum
				}
			}
			for(Entry<GameCharacter, Integer> e : cumProvidersToTotalCum.entrySet()) {
				sb.append(Main.sex.getCharacterPerformingAction().ingestFluid(e.getKey(), e.getKey().getCum(), SexAreaOrifice.VAGINA, e.getValue()));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character==Main.sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY);
			} else if(character==Main.sex.getCharacterTargetedForSexAction(this)) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
			}
			return null;
		}
	};
	public static final SexAction FINGER_INSEMINATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Пальцевое осеменение";
		}
		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] собрать сперму на твоем теле и затолкнуть её глубоко в твою киску.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTotalAmountCummedOn(Main.sex.getCharacterPerformingAction())>0
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()));
		}
		private String getRandomCharacterCumDescription(boolean withName) {
			Set<GameCharacter> charactersCummedOnPerformer = Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction()).keySet();
			
			GameCharacter character = Util.randomItemFrom(charactersCummedOnPerformer);
			
			if(character==Main.sex.getCharacterPerformingAction()) {
				return UtilText.parse(character, (withName?"собственный [npc.her] ":"")+"[npc.cum+]");
			} else {
				return UtilText.parse(character, (withName?"[npc.namePos] ":"")+"[npc.cum+]");
			}
		}
		@Override
		public String getDescription() {
			return "Пока [npc.name] [npc.verb(feel)] "+getRandomCharacterCumDescription(true)+" скользя вниз по [npc.her] [npc.skin], в [npc.her] внезапно вспыхивает шальная мысль, и [npc.she] дьявольски [npc.verb(grin)], пока [npc.she] [npc.verb(take)] хватает [npc2.namePos] [npc2.hand]. Направляя [npc2.her] [npc2.fingers] к "+getRandomCharacterCumDescription(false)+" что разбрызгано по телу [npc.her], [npc.she] [npc.verb(get)] [npc2.herHim], чтобы зачерпнуть немного свежего семени. Когда [npc.sheIs] убеждается, что [npc2.sheHas] собрала достаточно, [npc.she] [npc.verb(push)] [npc2.her] [npc.fingers] в [npc.her] голодную киску.<br/>[npc.Name] [npc.verb(grin)], пока [npc.she] [npc.verb(feel)] "+getRandomCharacterCumDescription(false)+" на [npc2.namePos] пальцах, глубоко входящих в [npc.her] щель, и [npc.moansVerb+], когда [npc2.name] [npc.verb(start)] ласкает [npc.herHim], используя скользкую сперму в качестве смазки. Прижимая [npc.her] бедра к [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] издает отчаянный, дрожащий [npc.moan] от ощущения, что ее оплодотворяют.";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			Map<GameCharacter, Integer> cumProvidersToTotalCum = new HashMap<>();
			for(Entry<GameCharacter, Map<InventorySlot, Integer>> cumDetails : new HashMap<>(Main.sex.getAmountCummedOnByPartners(Main.sex.getCharacterPerformingAction())).entrySet()) {
				for(Entry<InventorySlot, Integer> areaDetails : cumDetails.getValue().entrySet()) {
					int amountOfCumUsed = Math.min(5, areaDetails.getValue());
					cumProvidersToTotalCum.putIfAbsent(cumDetails.getKey(), 0);
					cumProvidersToTotalCum.put(cumDetails.getKey(), cumProvidersToTotalCum.get(cumDetails.getKey())+amountOfCumUsed);
					Main.sex.incrementAmountCummedOn(cumDetails.getKey(), Main.sex.getCharacterPerformingAction(), areaDetails.getKey(), -amountOfCumUsed); // Remove the cum
				}
			}
			for(Entry<GameCharacter, Integer> e : cumProvidersToTotalCum.entrySet()) {
				sb.append(Main.sex.getCharacterPerformingAction().ingestFluid(e.getKey(), e.getKey().getCum(), SexAreaOrifice.VAGINA, e.getValue()));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character==Main.sex.getCharacterPerformingAction()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY);
			} else if(character==Main.sex.getCharacterTargetedForSexAction(this)) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
			}
			return null;
		}
	};
	
	public static final SexAction STROKE_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласкать киску";
		}

		@Override
		public String getActionDescription() {
			return "Просунуть руку между [npc2.namePos] [npc2.legs] и начать ласкать [npc2.her] [npc2.pussy+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getDescription() {
			if(!Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)){

				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.topClothing(VAGINA)], осторожно прижимая и издавая тихий [npc.moan], пока [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] к [npc2.her] [npc2.pussy+].",

								"С мягким [npc.moan], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], осторожно надавливая [npc.her] [npc.fingers] вниз, пока [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"Медленно дразня [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] нежно [npc.verb(press)] вниз, потирая [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+], пока [npc.she] [npc.verb(let)] тихий [npc.moan]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.topClothing(VAGINA)], жадно надавливая и издавая [npc.a_moan+], пока [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], жадно надавливая [npc.her] [npc.fingers] вниз, пока [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"Жадно дразня [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(start)], чтобы надавить, потирая [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+], пока [npc.she] [npc.verb(let)] [npc.a_moan+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] грубо [npc.verb(press)] [npc.her] [npc.fingers+] против [npc2.namePos] [npc2.topClothing(VAGINA)], с силой надавливая вниз и издавая [npc.a_moan+], пока [npc.she] стремительно [npc.verb(start)] трет [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"Просунув [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], грубо надавливая на [npc.her] [npc.fingers] вниз, пока [npc.she] стремительно [npc.verb(start)] трет [npc2.namePos] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"Грубо лаская [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(start)], чтобы надавить, быстро растирая [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+], пока [npc.she] [npc.verb(let)] [npc.a_moan+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.topClothing(VAGINA)], жадно надавливая и издавая [npc.a_moan+], пока [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], жадно надавливая [npc.her] [npc.fingers] вниз, пока [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"Жадно дразня [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(start)], чтобы надавить, потирая [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+], пока [npc.she] [npc.verb(let)] [npc.a_moan+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.topClothing(VAGINA)], надавливая вниз и издавая [npc.a_moan+], пока [npc.she] [npc.verb(rub)] [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"Просунув [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], надавливая на [npc.her] [npc.fingers] вниз, пока [npc.she] [npc.verb(rub)] [npc2.namePos] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+].",

								"Игривое [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(press)] вниз, потирая [npc2.her] [npc2.lowClothing(VAGINA)] о [npc2.her] [npc2.pussy+], пока [npc.she] [npc.verb(let)] [npc.a_moan+]."));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Мягкий [npc2.moan] доносится из меж [npc2.namePos] [npc2.lips+], и при нежном выгибании [npc2.her] [npc2.hips], [npc2.she] мягко [npc2.verb(encourage)] [npc.name], продолжая ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)], нежно выгибая [npc2.her] [npc2.hips] в ответ, мягко прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] нежно [npc2.verb(start)] вгоняет [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.fingers], мягко [npc2.moaning], пока [npc2.she] [npc2.verb(enjoy)] ощущение от прикосновений [npc.namePos] к [npc2.her] [npc2.pussy+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] доносится из меж [npc2.namePos] [npc2.lips+], и при нетерпеливом выгибании [npc2.her] [npc2.hips], [npc2.she] отчаянно [npc2.verb(encourage)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)], нетерпеливо выгибая [npc2.her] [npc2.hips] в ответ, отчаянно прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] с нетерпением [npc2.verb(start)] толкая [npc2.her] [npc2.hips] назад против [npc.namePos] [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение от [npc.namePos] прикосновения к [npc2.her] [npc2.pussy+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], и после резкого толчка [npc2.her] [npc2.hips], [npc2.she] грубо [npc2.verb(order)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)] неистово дергается, подставляя [npc2.her] [npc2.hips] в ответ, грубо прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(demand)], что [npc.she] [npc.verb(continue)].",
	
									" [npc2.Name] с силой [npc2.verb(start)] толкая [npc2.her] [npc2.hips] назад против [npc.namePos] [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение от грубого трения [npc2.her] об [npc.name] [npc2.pussy+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] доносится из меж [npc2.namePos] [npc2.lips+], и при нетерпеливом выгибании [npc2.her] [npc2.hips], [npc2.she] отчаянно [npc2.verb(encourage)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)], нетерпеливо выгибая [npc2.her] [npc2.hips] в ответ, отчаянно прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] с нетерпением [npc2.verb(start)] толкая [npc2.her] [npc2.hips] назад против [npc.namePos] [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение от [npc.namePos] прикосновения к [npc2.her] [npc2.pussy+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)] выгибается [npc2.her] [npc2.hips] в ответ, прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] [npc2.verb(start)] толкая [npc2.her] [npc2.hips] назад, прижимая [npc.namePos] к [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение [npc.namePos] прикосновения к [npc2.her] [npc2.pussy+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips+], и, оттягивая [npc2.her] [npc2.hips] от прикосновения [npc.namePos], [npc2.she] [npc2.verb(plead)] с [npc.name], чтобы прекратить касаться [npc2.herHim].",
	
									" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_sob+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.name] прекратил(а).",
	
									" [npc2.Name] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.hips] от [npc.namePos] [npc.fingers], [npc2.sobbing+], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.name] оставил(а) [npc2.herHim] в покое."));
							break;
						default:
							break;
					}
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				
				UtilText.nodeContentSB.setLength(0);
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] поверх [npc2.her] [npc2.pussy+], тихонько вздохнув [npc.moan], пока [npc.she] [npc.verb(start)] нежно поглаживает [npc2.her] [npc2.labia+].",

								"С мягким [npc.moan], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], осторожно прижимая [npc.her] [npc.fingers] к [npc2.her] [npc2.labia+], пока [npc.she] [npc.verb(start)] поглаживает [npc2.her] [npc2.pussy+].",

								"Медленно дразня [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] плавно [npc.verb(press)] вниз, издавая тихий [npc.moan], пока [npc.she] [npc.verb(start)] поглаживает [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] жадно [npc.verb(trace)] [npc.her] [npc.fingers+] по [npc2.her] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно поглаживает [npc2.her] [npc2.labia+].",

								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], с нетерпением прижимая [npc.her] [npc.fingers] к [npc2.her] [npc2.labia+], пока [npc.she] [npc.verb(start)] поглаживает [npc2.her] [npc2.pussy+].",

								"Дразня [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] с нетерпением [npc.verb(press)] вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] счастливо поглаживает [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] грубо втирает [npc.her] [npc.fingers+] в [npc2.her] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] стремительно поглаживает [npc2.her] [npc2.labia+].",

								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], жадно прижимая [npc.her] [npc.fingers] к [npc2.her] [npc2.labia+], пока [npc.she] [npc.verb(start)] грубо потирает [npc2.her] [npc2.pussy+].",

								"Грубо проталкивая [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] грубо [npc.verb(press)] вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно ласкает [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] жадно [npc.verb(trace)] [npc.her] [npc.fingers+] по [npc2.her] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] жадно поглаживает [npc2.her] [npc2.labia+].",

								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], с нетерпением прижимая [npc.her] [npc.fingers] к [npc2.her] [npc2.labia+], пока [npc.she] [npc.verb(start)] поглаживает [npc2.her] [npc2.pussy+].",

								"Дразня [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] с нетерпением [npc.verb(press)] вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] счастливо поглаживает [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Просунув руку между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(trace)] [npc.her] [npc.fingers+] поверх [npc2.her] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] ласкает [npc2.her] [npc2.labia+].",

								"С [npc.a_moan+], [npc.name] [npc.verb(reach)] между [npc2.namePos] [npc2.legs], прижимая [npc.her] [npc.fingers] к [npc2.her] [npc2.labia+], пока [npc.she] [npc.verb(start)] ласкает [npc2.her] [npc2.pussy+].",

								"Дразня [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(press)] вниз, издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] ласкает [npc2.her] [npc2.pussy+]."));
						break;
					default:
						break;
				}
				
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Мягкий [npc2.moan] доносится из меж [npc2.namePos] [npc2.lips+], и при нежном выгибании [npc2.her] [npc2.hips], [npc2.she] мягко [npc2.verb(encourage)] [npc.name], продолжая ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)], нежно выгибая [npc2.her] [npc2.hips] в ответ, мягко прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] нежно [npc2.verb(start)] вгоняет [npc2.her] [npc2.hips] назад против [npc.namePos] [npc.fingers], тихо [npc2.moaning], пока [npc2.she] [npc2.verb(enjoy)] ощущает прикосновение [npc.her] к [npc2.her] [npc2.pussy+]."));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] доносится из меж [npc2.namePos] [npc2.lips+], и при нетерпеливом выгибании [npc2.her] [npc2.hips], [npc2.she] отчаянно [npc2.verb(encourage)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)], нетерпеливо выгибая [npc2.her] [npc2.hips] в ответ, отчаянно прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] с нетерпением [npc2.verb(start)] вгоняет [npc2.her] [npc2.hips] назад, прижимаясь к [npc.namePos] [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение [npc.her] прикосновений к [npc2.her] [npc2.pussy+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] вытекает из между [npc2.namePos] [npc2.lips+], и после резкого толчка [npc2.her] [npc2.hips], [npc2.she] грубо приказывает [npc.name] продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)] неистово выгибается, [npc2.her] [npc2.hips] отвечает тем же, грубо прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(demand)], что [npc.she] продолжаются.",
	
									" [npc2.Name] с силой [npc2.verb(start)] вгоняет [npc2.her] [npc2.hips] назад, прижимаясь к [npc.namePos] [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение грубого трения [npc2.her] об [npc.herHim] [npc2.pussy+]."));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] доносится из меж [npc2.namePos] [npc2.lips+], и при нетерпеливом выгибании [npc2.her] [npc2.hips], [npc2.she] отчаянно [npc2.verb(encourage)] [npc.name], чтобы продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)], нетерпеливо выгибая [npc2.her] [npc2.hips] в ответ, отчаянно прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] с нетерпением [npc2.verb(start)] вгоняет [npc2.her] [npc2.hips] назад, прижимаясь к [npc.namePos] [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение [npc.her] прикосновений к [npc2.her] [npc2.pussy+]."));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.hips] против [npc.namePos] [npc.hand], [npc2.she] [npc2.verb(encourage)] [npc.herHim], чтобы продолжать ласкать [npc2.her] [npc2.pussy+].",
	
									" [npc2.Name] [npc2.verb(start)] выгибается [npc2.her] [npc2.hips] в ответ, прижимая [npc2.herself] к [npc.namePos] [npc.fingers], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.herHim] продолжал(а).",
	
									" [npc2.Name] [npc2.verb(start)] вгоняет [npc2.her] [npc2.hips] назад, прижимаясь к [npc.namePos] [npc.fingers], [npc2.moaning+], пока [npc2.she] [npc2.verb(enjoy)] ощущение [npc.her] прикосновений к [npc2.her] [npc2.pussy+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.A_sob+] резко вырывается из между [npc2.namePos] [npc2.lips+] и, оттягивая [npc2.her] [npc2.hips] от [npc.namePos] прикосновений, [npc2.she] [npc2.verb(plead)] с [npc.herHim], чтобы перестать касаться [npc2.herHim].",
	
									" [npc2.Name] [npc2.verb(pull)] [npc2.her] [npc2.hips] в ответ, издавая [npc2.a_sob+], пока [npc2.she] [npc2.verb(plead)], чтобы [npc.name] прекратил(а).",
	
									" [npc2.Name] [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.hips] от [npc.namePos] [npc.fingers], [npc2.sobbing+], пока [npc2.she] [npc2.verb(beg)], чтобы [npc.herHim] оставил(а) [npc2.herHim] в покое."));
							break;
						default:
							break;
					}
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.VAGINA)) {
				Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA);
			}
		}
	};
	
	public static final SexAction FINGERING_PROSTATE_MASSAGE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo();
		}
		
		@Override
		public String getActionTitle() {
			return "Массаж простаты";
		}

		@Override
		public String getActionDescription() {
			return "Сверни свои [npc.fingers] внутри [npc2.namePos] [npc2.pussy+] и начни ласкать простату [npc2.her].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко прижимая ладонь [npc.her] [npc.hand] к [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.pussy], прежде чем согнуть их и начать мягко поглаживать и массировать простату [npc2.her].",
							"С мягким [npc.moan], [npc.name] осторожно [npc.verb(slide)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], прежде чем согнуть их и начать ритмично поглаживать и массировать простату [npc2.her].",
							"Медленно проталкивая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] из себя мягкий [npc.moan], когда [npc.she] затем [npc.verb(curl)] их и [npc.verb(start)], чтобы нежно массировать простату [npc2.her]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо прижимая ладонь [npc.her] [npc.hand] к [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(force)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.pussy], а затем сгибая их и начиная с силой ласкать и массировать [npc2.her] простату.",
							"Используя [npc.a_moan+], [npc.name] яростно [npc.verb(slam)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], а затем сгибая их и начиная агрессивно ласкать и массировать [npc2.her] простату.",
							"С силой вталкивая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she], затем [npc.verb(curl)] их и [npc.verb(start)], чтобы грубо массировать [npc2.her] простату."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Прижимая ладонь [npc.her] [npc.hand] к [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.pussy], а затем сгибая их и начиная ласкать и массировать [npc2.her] простату.",
							"Используя [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], а затем сгибая их и начиная ласкать и массировать [npc2.her] простату.",
							"Заталкивая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], словно [npc.she], а затем [npc.verb(curl)] их и [npc.verb(start)], чтобы массировать простату [npc2.her]."));
					break;
				default: // Normal dom and eager sub:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С жадностью прижимая ладонь [npc.her] [npc.hand] к [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(slide)] [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.pussy], после чего подгибая их и начиная быстро поглаживать и массировать [npc2.her] простату.",
							"С помощью [npc.a_moan+], [npc.name] отчаянно [npc.verb(push)] [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], прежде чем поджать их и начать жадно поглаживать и массировать [npc2.her] простату.",
							"С наслаждением проталкивая [npc.her] [npc.fingers+] как можно глубже в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she], а затем [npc.verb(curl)] их вверх и [npc.verb(start)], чтобы энергично массировать [npc2.her] простату."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем мягко прижать [npc2.her] [npc2.labia+] к [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем мягко подать [npc2.her] бедра навстречу [npc.namePos].",
								" Отвечая на этот жест, выпуская [npc2.a_moan+], [npc2.name] мягко [npc2.verb(push)] [npc2.her] [npc2.labia+] наружу против [npc.namePos] [npc.hand]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем грубо прижать [npc2.her] [npc2.labia+] к [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем с силой шлепнуть [npc2.her] [npc2.labia+] о [npc.namePos].",
								" Отвечая на этот выпад громким [npc2.a_moan+], [npc2.name] агрессивно [npc2.verb(slam)] [npc2.her] [npc2.labia+] против [npc.namePos] [npc.hand]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем упереть [npc2.her] [npc2.labia+] в [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], после чего она дергает бедрами [npc2.her] навстречу [npc.namePos] прикосновению.",
								" Отвечая на этот выпад громким [npc2.a_moan+], [npc2.name] [npc2.verb(push)] [npc2.her] [npc2.labia+] против [npc.namePos] [npc.hand]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это нежеланное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем отчаянно попытаться оттянуть [npc2.her] [npc2.labia+] от [npc.namePos] [npc.hand].",
								" Тут же, в ответ, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], после чего она отчаянно пытается убрать бедра [npc2.her] от [npc.namePos] прикосновения.",
								" Отвечая на этот выпад громким [npc2.a_moan+], [npc2.name] отчаянно [npc2.verb(try)], чтобы оттянуть [npc2.her] [npc2.labia+] от [npc.namePos] [npc.name]."));
						break;
					default: // Normal dom and eager sub:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" Почувствовав это внезапное стимулирующее ощущение, [npc2.name] [npc2.verb(let)] [npc2.a_moan+], прежде чем упереть [npc2.her] [npc2.labia+] в [npc.namePos] [npc.hand].",
								" Тут же, [npc2.name] [npc2.verb(let)] [npc2.a_moan+] в ответ, отчаянно выгибая [npc2.her] бедра навстречу [npc.namePos].",
								" В ответ на этот жест, издавая [npc2.a_moan+], [npc2.name] отчаянно [npc2.verb(push)] [npc2.her] [npc2.labia+] против [npc.namePos] [npc.hand]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Пальцы [npc2.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "Погрузи свои [npc.fingers] в [npc2.namePos] [npc2.pussy+] и начни ласкать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Медленно дразня [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем медленно погрузить [npc.her] пальцев в [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] глубоко между [npc2.namePos] [npc2.legs+], и медленным, уверенным давлением [npc.she] нежно [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С нетерпением дразня [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем жадно погрузить [npc.her] пальцев в [npc2.her] [npc2.pussy+].",

							"[npc.Name] с нетерпением [npc.verb(press)] [npc.her] [npc.fingers+] глубоко между [npc2.namePos] [npc2.legs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо растирая [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем яростно вогнать [npc.her] пальцев глубоко в [npc2.her] [npc2.pussy+].",

							"[npc.Name] грубо втирает [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs+], и резким толчком [npc.she] жадно вгоняет [npc.her] пальцев глубоко в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"С нетерпением дразня [npc.her] [npc.fingers+] поверх [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем жадно погрузить [npc.her] пальцев в [npc2.her] [npc2.pussy+].",

							"[npc.Name] с нетерпением [npc.verb(press)] [npc.her] [npc.fingers+] глубоко между [npc2.namePos] [npc2.legs+], и решительным толчком [npc.she] жадно [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Дразня [npc.her] [npc.fingers+] над [npc2.namePos] [npc2.labia+], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем погрузить [npc.her] пальцев в [npc2.her] [npc2.pussy+].",

							"[npc.Name] [npc.verb(press)] [npc.her] [npc.fingers+] между [npc2.namePos] [npc2.legs+], и легким толчком [npc.she] [npc.verb(sink)] [npc.her] пальцев в [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] мягкий [npc2.moan], когда [npc.namePos] [npc.fingers] входят в [npc2.herHim], нежно толкая [npc2.her] [npc2.hips] о [npc.her] [npc.hand], пока [npc2.she] [npc2.verb(help)] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(start)], нежно толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], побуждая [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.namePos] [npc.fingers] входят в [npc2.herHim], с азартом толкая [npc2.her] [npc2.hips] о [npc.her] [npc.hand], пока [npc2.she] с энтузиазмом [npc2.verb(help)] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)], жадно толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], отчаянно подталкивая [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)], вытаскивая [npc2.a_moan+], когда [npc.namePos] [npc.fingers] входят в [npc2.herHim], яростно толкая [npc2.her] [npc2.hips] против [npc.her] [npc.hand], пока [npc2.she] грубо [npc2.verb(force)] [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)], яростно толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], грубо заставляя [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], когда [npc.namePos] [npc.fingers] входят в [npc2.herHim], с азартом толкая [npc2.her] [npc2.hips] о [npc.her] [npc.hand], пока [npc2.she] с энтузиазмом [npc2.verb(help)] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+].",
	
								" При [npc2.a_moan+], [npc2.name] [npc2.verb(start)], жадно толкая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], отчаянно подталкивая [npc.herHim] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)], вытаскивая [npc2.a_moan+], когда [npc.namePos] [npc.fingers] входят в [npc2.herHim], толкая [npc2.her] [npc2.hips] против [npc.her] [npc.hand], пока [npc2.she] [npc2.verb(help)] погрузить [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(start)], выгибая [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], побуждая [npc.herHim] погружать [npc.her] [npc.fingers] еще глубже в [npc2.her] [npc2.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] [npc2.a_sob+], пока [npc2.she] [npc2.verb(feel)] [npc.namePos], когда [npc.fingers] входит в [npc2.herHim], и, тщетно, [npc2.verb(try)], пытаясь оттянуть [npc2.her] [npc2.hips] от нежелательного проникновения, извиваясь и [npc2.sobbing] все это время.",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(try)], тщетно пытаясь уклониться от нежеланного проникновения, [npc2.sobbing] и борясь с [npc.name], пока [npc.her] незваное [npc.fingers] [npc2.verb(push)] глубоко в [npc2.her] [npc2.pussy+]."));
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
							" [npc2.Name] с нетерпением [npc2.verb(thrust)], отвечая на [npc2.her] [npc2.hips], издавая восторженный [npc2.moan], пока [npc2.she] с энтузиазмом [npc2.verb(start)] умоляет [npc.name] продолжать ласкать [npc2.herHim] пальцами.",
		
							" Восторженный [npc2.moan] вырывается из между [npc2.namePos] [npc2.lips+], и, жадно подаваясь [npc2.her] [npc2.hips] навстречу прикосновению [npc.her], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) ласкать [npc2.herHim] пальцами.",
		
							" [npc2.Moaning] в восторге, [npc2.name] жадно [npc2.verb(grind)] вниз по [npc.namePos] [npc.hand], умоляя [npc.herHim] продолжать, пока движения [npc2.her] вгоняют [npc.her] [npc.fingers] всё глубже в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Не в силах отстранить [npc2.her] [npc2.pussy] от нежелательного прикосновения [npc.namePos], [npc2.name] [npc2.verb(let)] из-за [npc2.a_sob+], пока [npc2.she] слабо [npc2.verb(try)], пытаясь вырваться из навязчивых [npc.fingers] [npc.namePos].",
		
							" [npc2.A_sob+] вырывается из между [npc2.namePos] [npc2.lips], пока [npc2.she] слабо [npc2.verb(try)], пытаясь оттолкнуть [npc.name], извиваясь и протестуя, пока [npc.she] [npc.verb(continue)] пальцами исследует [npc2.namePos] [npc2.pussy+].",
		
							" [npc2.Sobbing] в смятении, [npc2.name] тщетно [npc2.verb(try)], пытаясь отстраниться от прикосновения [npc.namePos], борясь с [npc.herHim], пока [npc.her] [npc.fingers+] [npc.verb(continue)] скользит глубоко в [npc2.namePos] [npc2.pussy+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] медленно [npc2.verb(thrust)] [npc2.her] [npc2.hips] в ответ на [npc.namePos] [npc.hand], издавая тихий [npc2.moan], пока [npc2.she] нежно [npc2.verb(start)], умоляя [npc.name] продолжать ласкать пальцами [npc2.herHim].",
	
						" Тихий [npc2.moan] доносится из между [npc2.namePos] [npc2.lips+], и, мягко выталкивая [npc2.her] [npc2.hips] навстречу [npc.her] [npc.hand], [npc2.she] [npc2.verb(beg)], чтобы [npc.name] продолжал ласкать пальцами [npc2.herHim].",
	
						" [npc2.Moaning] в восторге, [npc2.name] медленно [npc2.verb(grind)] вниз по [npc.namePos] [npc.hand], тихо [npc2.moaning], чтобы [npc.herHim] продолжал(а), пока движения [npc2.her] заставляют [npc.her] [npc.fingers] скользить всё глубже в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ яростно [npc2.verb(thrust)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], издавая [npc2.a_moan+], пока [npc2.name] грубо [npc2.verb(order)], чтобы [npc.name] продолжал(а) ласкать [npc2.herHim] пальцами.",
	
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, грубо втирая [npc2.her] [npc2.hips] в [npc.her] [npc.hand], [npc2.name] властно [npc.verb(command)], чтобы [npc.name] продолжал(а) ласкать [npc2.herHim] пальцами.",
	
							" [npc2.Name] грубо [npc2.verb(grind)] вниз по [npc.namePos] [npc.hand], приказывая [npc.herHim] продолжать, пока резкие движения [npc2.her] заставляют [npc.her] [npc.fingers] погружаться всё глубже в [npc2.her] [npc2.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] в ответ [npc2.verb(thrust)] [npc2.her] [npc2.hips] в [npc.namePos] [npc.hand], издавая [npc2.a_moan+], пока [npc2.name] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) ласкать [npc2.herHim] пальцами.",
		
							" [npc2.A_moan+] вырывается из между [npc2.namePos] [npc2.lips+], и, толкая [npc2.her] [npc2.hips] навстречу прикосновению [npc.her], [npc2.name] [npc2.verb(beg)], чтобы [npc.name] продолжал(а) ласкать [npc2.herHim] пальцами.",
		
							" [npc2.Moaning+], [npc2.name] [npc2.verb(grind)] на [npc.namePos] [npc.hand], умоляя [npc.herHim] продолжить, пока [npc2.her] движения заставляют [npc.her] [npc.fingers] проникать всё глубже в [npc2.her] [npc2.pussy+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FINGERING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежное проникновение пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Нежно ласкать [npc2.namePos] [npc2.pussy+] пальцами.";
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко погружая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] пальцы вверх, нежно поглаживая [npc2.her] стенки влагалища, пока [npc.she] [npc.verb(start)] медленно ласкает [npc2.herHim] пальцами.",

					"Медленно толкая [npc.her] [npc.hand] в пах [npc2.namePos], [npc.name] осторожно вводит [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], тихонько [npc.moan], пока [npc.she] [npc.verb(start)] мягко ласкает [npc2.herHim] пальцами.",

					"Прижимая [npc.her] [npc.hand] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] немного [npc.moan], прежде чем осторожно ввести [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.pussy+]."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Фингеринг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить ласкать [npc2.namePos] [npc2.pussy+] пальцами.";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"С жадностью погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] пальцы вверх, поглаживая [npc2.her] стенки влагалища, пока [npc.she] [npc.verb(start)] страстно ласкает [npc2.herHim] пальцами.",

					"Крепко вдавливая [npc.her] [npc.hand] в пах [npc2.namePos], [npc.name] жадно [npc.verb(slide)] [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] стремительно перебирает [npc2.herHim].",

					"С жадностью прижимая [npc.her] [npc.hand] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем с энтузиазмом вогнать [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубые пальцы";
		}

		@Override
		public String getActionDescription() {
			return "Грубо перебирать пальцами [npc2.namePos] [npc2.pussy+].";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Алчно вонзая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] пальцы, грубо лаская стенки [npc2.namePos] влагалища, пока [npc.she] [npc.verb(start)] беспощадно ласкает [npc2.herHim].",

					"Грубо вбивая [npc.her] [npc.hand] в пах [npc2.namePos], [npc.name] жадно [npc.verb(pump)] [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] неистово перебирает [npc2.herHim].",

					"Сильно прижимая [npc.her] [npc.hand] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем грубо вогнать [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction FINGERING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Фингеринг";
		}

		@Override
		public String getActionDescription() {
			return "Продолжить ласкать [npc2.namePos] [npc2.pussy+] пальцами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Погружая [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] пальцев вверх, поглаживая [npc2.her] стенки влагалища, пока [npc.she] [npc.verb(focus)] [npc.her] усилиями в мастурбации [npc2.herHim].",

					"Втискивая [npc.her] [npc.hand] в пах [npc2.namePos], [npc.name] скользит [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] сосредотачивает [npc.her] усилия на пальцевании [npc2.herHim].",

					"Прижимая [npc.her] [npc.hand] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] наружу [npc.a_moan+], прежде чем скользнуть [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Нетерпеливое пальцевание";
		}

		@Override
		public String getActionDescription() {
			return "Нетерпеливо пальцевать [npc2.namePos] [npc2.pussy+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо погружая [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(curl)] [npc.her] пальцев вверх, поглаживая [npc2.namePos] стенки влагалища, пока [npc.she] [npc.verb(start)] страстно пальцует [npc2.herHim].",

					"Крепко вдавливая [npc.her] [npc.hand] в пах [npc2.namePos], [npc.name] жадно [npc.verb(slide)] [npc.her] [npc.fingers+] глубоко в [npc2.namePos] [npc2.pussy+], издавая [npc.a_moan+], пока [npc.she] [npc.verb(start)] стремительно перебирает [npc2.herHim].",
					
					"С жадностью прижимая [npc.her] [npc.hand] между [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] [npc.a_moan+], прежде чем с энтузиазмом вогнать [npc.her] [npc.fingers+] глубоко в [npc2.her] [npc2.pussy+]."));

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction FINGERING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться пальцеванию";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить свой [npc.fingers] из [npc2.namePos] [npc2.vagina+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наворачиваться на [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)] вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.vagina+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.vagina+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.vagina+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] на месте, после чего она плавно выгибает [npc2.her] [npc2.hips+], заставляя [npc.her] [npc.fingers] глубоко войти в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наворачиваться на [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)] вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.vagina+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.vagina+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.vagina+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] на месте, после чего она жадно выгибает [npc2.her] [npc2.hips+], заставляя [npc.her] [npc.fingers] глубоко войти в [npc2.her] [npc2.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] слезы начинают наворачиваться на [npc.namePos] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из [npc.her] рта, пока [npc.she] слабо [npc.verb(try)] вытащить [npc.her] [npc.fingers] из [npc2.namePos] [npc2.vagina+].",

							"[npc.A_sob+] вырывается изо рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.vagina+].",

							"Отчаянно пытаясь вытащить [npc.her] [npc.fingers+] из [npc2.namePos] [npc2.vagina+], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.verb(hold)] [npc.her] [npc.hand] в позиции, прежде чем грубо встряхнуть [npc2.her] [npc2.hips+] и загнать [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FINGERING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить пальцевание";
		}

		@Override
		public String getActionDescription() {
			return "Вытащи свой [npc.fingers] из [npc2.namePos] [npc2.pussy+] и перестань пальцевать [npc2.herHim].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Грубо выдергивая [npc.her] [npc.fingers] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.clit+] жестоким сжатием, прежде чем убрать [npc.her] [npc.hand] от [npc2.her] паха.",

							"В последний раз глубоко вонзаясь в [npc2.name], [npc.name] затем выдергивает [npc.her] [npc.fingers] обратно из [npc2.namePos] [npc2.pussy+], кладя конец [npc.her] грубому пальцеванию."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Выскальзывая [npc.her] [npc.fingers] из [npc2.namePos] [npc2.pussy+], [npc.name] [npc.verb(give)] [npc2.her] [npc2.clit+] легким сжатием, прежде чем убрать [npc.her] [npc.hand] от [npc2.her] паха.",

							"В последний раз проталкиваясь глубоко в [npc2.name], [npc.name] затем [npc.verb(slide)] [npc.her] [npc.fingers] обратно из [npc2.namePos] [npc2.pussy+], кладя конец [npc.her] пальцеванию."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(gasp)], когда [npc.name] отстраняется от [npc2.her] [npc2.pussy], прежде чем издать [npc2.a_sob+], пока [npc2.she] [npc2.verb(continue)] бороться с [npc.herHim].",

							" С [npc2.a_sob+], [npc2.name] [npc2.verb(continue)], пытаясь сопротивляться [npc.name], пока [npc.she] вытягивается из [npc2.her] [npc2.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+], пока [npc.name] [npc.verb(pull)] [npc.her] [npc.fingers+] из [npc2.her] [npc2.pussy+].",

							" [npc2.A_moan+] вырывается между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] жажду еще большего внимания к [npc.namePos]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction FINGERED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Подставить влагалище под пальцы";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] начать ласкать твою [npc.pussy+] пальцами.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мягко, но уверенно обхватив [npc2.namePos] [npc2.hand], [npc.name] медленно [npc.verb(guide)] [npc2.her] [npc2.fingers] по [npc.her] [npc.labia+], издавая легкий [npc.moan], прежде чем вогнать [npc2.her] пальцев в [npc.her] [npc.pussy+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] между [npc.her] [npc.legs+], и с медленным, уверенным нажимом, [npc.name] нежно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] по [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем жадно вогнать [npc2.her] пальцев в [npc.her] [npc.pussy+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] между [npc.her] [npc.legs+], и с решительным напором, [npc.name] жадно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Мертвой хваткой сжимая [npc2.her] [npc2.hand], [npc.name] [npc.verb(grind)] [npc2.namePos] [npc2.fingers] поверх [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем грубо вогнать [npc2.her] пальцев в [npc.her] [npc.pussy+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с силой [npc.verb(push)] [npc2.her] [npc2.fingers] вниз между [npc.her] [npc.legs+], и доминирующим, резким движением [npc.name] грубо [npc.verb(stuff)] [npc2.her] пальцев в [npc.her] [npc.pussy+]."));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжав [npc2.her] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.namePos] [npc2.fingers] по [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем жадно вогнать [npc2.her] пальцев в [npc.her] [npc.pussy+].",

							"Схватив [npc2.namePos] [npc2.hand], [npc.name] с нетерпением [npc.verb(guide)] [npc2.her] [npc2.fingers] между [npc.her] [npc.legs+], и с решительным напором, [npc.name] жадно [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.pussy+]."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Крепко сжимая [npc2.her] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.namePos] [npc2.fingers] поверх [npc.her] [npc.labia+], издавая [npc.a_moan+], прежде чем втиснуть [npc2.her] пальцев в [npc.her] [npc.pussy+].",

							"Ухватив [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(guide)] [npc2.her] [npc2.fingers] вниз между [npc.her] [npc.legs+], и с решительным напором [npc.name] [npc.verb(push)] [npc2.her] пальцев в [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] тихий [npc2.moan], пока [npc2.she] [npc2.verb(enter)] [npc.name], подгибая [npc2.her] [npc2.fingers], прежде чем мягко начать ласкать [npc.her] [npc.pussy+].",
	
								" С мягким [npc2.moan], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], нежно поглаживая [npc.her] стенки влагалища, пока [npc2.she] [npc2.verb(set)] о том, как ласкает [npc.her] [npc.pussy+]."));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.name], поджимая [npc2.her] [npc2.fingers] перед тем, как с нетерпением начать пальцевать [npc.her] [npc.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно поглаживая [npc.her] стенки влагалища, пока [npc2.she] [npc2.verb(set)] о пальцевании [npc.her] [npc.pussy+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.name], и, желая напомнить [npc.herHim], кто здесь главный, [npc2.she] грубо [npc2.verb(curl)] [npc2.her] [npc2.fingers], прежде чем начать беспощадно трахать [npc.her] [npc.pussy+] пальцами.",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], стремясь напомнить [npc.herHim], кто здесь главный, пока [npc2.she] [npc2.verb(start)] грубо пальцевал(а) [npc.her] [npc.pussy+]."));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.name], поджимая [npc2.her] [npc2.fingers] перед тем, как с нетерпением начать пальцевать [npc.her] [npc.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], жадно поглаживая [npc.her] стенки влагалища, пока [npc2.she] [npc2.verb(set)] о пальцевании [npc.her] [npc.pussy+]."));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+] как [npc2.she] [npc2.verb(enter)] [npc.name], поджимая [npc2.her] [npc2.fingers] перед тем, как начать пальцевать [npc.her] [npc.pussy+].",
	
								" С [npc2.a_moan+], [npc2.name] [npc2.verb(curl)] [npc2.her] [npc2.fingers+] внутри [npc.name], поглаживая стенки влагалища [npc.her], пока [npc2.she] [npc2.verb(set)] о том, как пальцы входят в [npc.her] [npc.pussy+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] из [npc2.a_sob+] как [npc.name] [npc.verb(force)] [npc2.her] [npc2.fingers] внутри [npc.herHim], пытаясь вырваться из [npc.namePos] крепкой хватки на [npc2.her] [npc2.hand], пока [npc2.she] [npc2.verb(try)] чтобы вытащить [npc2.herself].",
	
								" С [npc2.a_sob+], [npc2.name] [npc2.verb(start)] тщетно пытается вырваться из [npc.namePos] тугой хватки на [npc2.her] [npc2.hand], умоляя [npc.herHim] остановиться, пока [npc.she] [npc.verb(force)] [npc2.her] [npc2.fingers] глубоко в [npc.her] [npc.pussy+]."));
						break;
					default:
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежно пальцевала";
		}

		@Override
		public String getActionDescription() {
			return "Нежно наслаждайся [npc2.namePos] [npc2.fingers+] в своей [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Мягко выталкивая [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] тихий [npc.moan], пока [npc.she] [npc.verb(help)] погружать [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+].",

					"С тихим [npc.moan], [npc.name] мягко [npc.verb(start)] вращая [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.pussy+].",

					"Медленно толкая [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], тихий [npc.moan] вырывается из между [npc.namePos] [npc.lips+], когда движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+]."));

			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Наслаждайся [npc2.namePos] [npc2.fingers+] в своей [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] энергично [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] вращает [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.pussy+].",

					"Энтузиазмом вгоняя [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубые пальцы во влагалище";
		}

		@Override
		public String getActionDescription() {
			return "Грубо [npc.verb(force)] [npc2.namePos] [npc2.fingers+] глубоко внутри твоей [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Яростно вбивая [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] грубо [npc.verb(force)] [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+].",

					"С [npc.a_moan+], [npc.name] агрессивно [npc.verb(start)] вращает [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.pussy+].",

					"Грубо толкая [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.her] резкие движения вгоняют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Наслаждайся [npc2.namePos] [npc2.fingers+] в своей [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] энергично [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] вращает [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.pussy+].",

					"Энтузиазмом вгоняя [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Нетерпеливо перебирая пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Нетерпеливо толкая бедрами навстречу [npc2.namePos] [npc2.hand], пока [npc2.she] ласкает твои [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Нетерпеливо выталкивая [npc.her] [npc.hips] навстречу [npc2.namePos] [npc2.hand], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] энергично [npc.verb(help)], чтобы погрузить [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+].",

					"С [npc.a_moan+], [npc.name] энергично [npc.verb(start)] вращает [npc.her] [npc.hips] против [npc2.namePos] [npc2.hand], заставляя [npc2.her] [npc2.fingers+] проникать всё глубже в [npc.her] [npc.pussy+].",

					"Энтузиазмом вгоняя [npc.her] [npc.hips] в [npc2.namePos] [npc2.hand], [npc.a_moan+] вырывается из между [npc.namePos] [npc.lips+], пока движения [npc.her] заставляют [npc2.namePos] [npc2.fingers+] глубоко в [npc.her] [npc.pussy+]."));
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться пальцеванию";
		}

		@Override
		public String getActionDescription() {
			return "Попытаться вытащить [npc2.namePos] [npc2.fingers] из твоих [npc.pussy+].";
		}
		
		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)], слезы начинают наворачиваться на [npc.her] [npc.eyes], и, не в силах больше сдерживаться, [npc.a_sob+] вырывается из рта [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc2.namePos] [npc2.fingers] из [npc.her] [npc.pussy+].",

							"[npc.A_sob+] вырывается из рта [npc.namePos], пока [npc.she] отчаянно [npc.verb(try)], пытаясь отстранить [npc.her] [npc.hips] от [npc2.namePos] нежеланных прикосновений, в отчаянии борясь, пока [npc2.her] [npc2.fingers+] нежно [npc2.verb(continue)] входит и выходит из [npc.her] [npc.pussy+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] нежно [npc2.verb(continue)] глубоко внутри [npc.her] [npc.pussy+]."));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают скапливаться в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.a_sob+] вырываются из рта [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc2.namePos] жадные [npc2.fingers] из [npc.her] [npc.pussy+].",

							"[npc.A_sob+] вырываются из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь отстранить [npc.her] [npc.hips] от [npc2.namePos] нежеланного прикосновения, отчаянно извиваясь, пока [npc2.her] [npc2.fingers+] жадно [npc2.verb(continue)] туда-сюда в [npc.her] [npc.pussy+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] [npc2.verb(continue)] жадно вбивается глубоко в [npc.her] [npc.pussy+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(feel)] начинают скапливаться в [npc.her] [npc.eyes], и, больше не в силах сдерживаться, [npc.a_sob+] вырываются из рта [npc.namePos], пока [npc.she] слабо [npc.verb(try)], пытаясь вытащить [npc2.namePos] грубые [npc2.fingers] из [npc.her] [npc.pussy+].",

							"[npc.A_sob+] вырываются из рта [npc.namePos], пока [npc.she] лихорадочно [npc.verb(try)], пытаясь отстранить [npc.her] [npc.hips] от [npc2.namePos] нежеланного прикосновения, отчаянно извиваясь, пока [npc2.her] [npc2.fingers+] грубо [npc2.verb(continue)] вбивается туда-сюда в [npc.her] [npc.pussy+].",

							"Отчаянно пытаясь оттянуть [npc.her] [npc.hips] от [npc2.namePos] [npc2.hand], [npc.name] [npc.sob] в смятении, пока [npc2.namePos] [npc2.fingers+] [npc2.verb(continue)] грубо вбивается глубоко в [npc.her] [npc.pussy+]."));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction FINGERED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить пальцевание";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] вытащить [npc2.her] [npc2.fingers] из твоей [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Резко выдергивая [npc2.namePos] [npc2.fingers] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(growl)], пока [npc.she] [npc.verb(command)] [npc2.herHim], чтобы прекратить пальцевание [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], прежде чем вырвать [npc2.her] [npc2.fingers] из [npc.her] [npc.pussy+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Скользя [npc2.namePos] [npc2.fingers] из [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] из [npc.a_moan+], прежде чем велеть [npc2.herHim] перестать перебирать [npc.herHim].",

							"[npc.Name] [npc.verb(lean)] в [npc2.name], вдыхая [npc2.her] [npc2.scent+], прежде чем плавно вытащить [npc2.her] [npc2.fingers] из [npc.her] [npc.pussy+]."));
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
							" [npc2.Name] [npc2.verb(let)] из [npc2.a_moan+], пока [npc.name] [npc.verb(stop)] [npc2.herHim], чтобы прекратить стимуляцию [npc.her] [npc.pussy+].",

							" [npc2.A_moan+] выскальзывает из между [npc2.namePos] [npc2.lips+], выдавая [npc2.her] желание уделить [npc.namePos] [npc.pussy+] еще больше [npc2.her] внимания."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
}
