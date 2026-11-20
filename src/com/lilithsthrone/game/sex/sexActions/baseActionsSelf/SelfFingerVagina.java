package com.lilithsthrone.game.sex.sexActions.baseActionsSelf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
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
 * @version 0.4.0.0
 * @author Innoxia
 */
public class SelfFingerVagina {
	
	public static final SexAction FINGER_INSEMINATION_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Самоза inseminация пальцами";
		}
		@Override
		public String getActionDescription() {
			return "Собери с тела сперму и затолкни её глубоко в свою киску.";
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
			return "Пока [npc.name] [npc.verb(feel)] "+getRandomCharacterCumDescription(true)+" скользя по [npc.her] [npc.skin], в [npc.her] голову внезапно приходит забавная идея. Вытащив [npc.her] [npc.fingers] из [npc.her] [npc.pussy+], [npc.she] [npc.verb(reach)] до "+getRandomCharacterCumDescription(false)+" что разбрызгано по телу [npc.her], прежде чем зачерпнуть немного свежего семени. Удовлетворенная тем, что [npc.sheHas] собрала достаточно, [npc.she] [npc.verb(push)] [npc.her] [npc.fingers] обратно в [npc.her] голодную киску.<br/>[npc.Name] [npc.verb(grin)] как [npc.she] [npc.verb(feel)] "+getRandomCharacterCumDescription(false)+" пальцы [npc.her] глубоко входят в [npc.her] киску, и [npc.moansVerb+], пока [npc.she] снова [npc.verb(start)] перебирает [npc.herself], используя скользкую сперму в качестве смазки. Вжимая [npc.her] бедра в [npc.her] [npc.hand], [npc.name] [npc.verb(let)] отчаянный, дрожащий [npc.moan] от ощущения, что её оплодотворяют.";
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
			return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY, Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
		}
	};
	public static final SexAction FINGER_INSEMINATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Самоза inseminация пальцами";
		}
		@Override
		public String getActionDescription() {
			return "Собери с тела сперму и затолкни её глубоко в свою киску.";
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
			return "Пока [npc.name] [npc.verb(feel)] "+getRandomCharacterCumDescription(true)+" скользя по [npc.her] [npc.skin], в [npc.her] голову внезапно приходит забавная идея. Поднимая [npc.her] [npc.fingers] к "+getRandomCharacterCumDescription(false)+" все это забрызгало тело [npc.her], когда [npc.she] [npc.verb(scoop)] немного свежего семени. Удовлетворенная тем, что [npc.sheHas] собрала достаточно, [npc.she] [npc.verb(push)] [npc.her] [npc.fingers] в [npc.her] голодную киску.<br/>[npc.Name] [npc.verb(grin)] как [npc.she] [npc.verb(feel)] "+getRandomCharacterCumDescription(false)+" о том, как [npc.her] пальцы глубоко входят в [npc.her] киску, и [npc.moansVerb+], пока [npc.she] [npc.verb(start)] ласкает [npc.herself], используя скользкую сперму в качестве смазки. Потирая [npc.her] бедра о [npc.her] [npc.hand], [npc.name] [npc.verb(let)] отчаянный, дрожащий [npc.moan] от ощущения, что ее оплодотворяют.";
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
			return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT, Fetish.FETISH_PREGNANCY, Fetish.FETISH_CUM_STUD, Fetish.FETISH_IMPREGNATION);
		}
	};
	
	public static final SexAction SELF_FINGER_VAGINA_SPREAD_PUSSY = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Раздвинуть киску";
		}

		@Override
		public String getActionDescription() {
			return "Используй свои [npc.fingers], чтобы раздвинуть киску.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return (UtilText.returnStringAtRandom(
						"Протягивая руку назад между [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] у входа в [npc.her] [npc.pussy+], после чего издавая [npc.a_moan+], когда [npc.she] [npc.verb(use)] [npc.her] пальцы раздвигают [npc.her] половые губы.",
						"[npc.Name] [npc.verb(probe)] [npc.her] [npc.fingers] назад между [npc.her] [npc.legs], [npc.moaning] мягко, пока [npc.she] [npc.verb(use)] два [npc.her] пальца раздвигают [npc.her] [npc.pussy+].",
						"Скользя кончиками [npc.her] пальцев по [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(shake)] [npc.her] задницу, прежде чем использовать [npc.her] пальцев, чтобы раздвинуть губы [npc.her] киски.",
						"[npc.Name] жадно [npc.verb(slide)] [npc.her] [npc.fingers] по [npc.her] неистовой [npc.pussy], [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] пальцев раздвигает мягкие складки [npc.her]."));
			} else {
				return (UtilText.returnStringAtRandom(
						"Протянув руку между [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] над входом в [npc.her] [npc.pussy+], она издает [npc.a_moan+], когда [npc.she] [npc.verb(use)] [npc.her] пальцев раздвигает [npc.her] половые губы.",
						"[npc.Name] [npc.verb(probe)] [npc.her] [npc.fingers] вниз между [npc.her] [npc.legs], [npc.moaning] тихо, когда [npc.she] [npc.verb(use)] два [npc.her] пальца, чтобы раздвинуть [npc.her] [npc.pussy+].",
						"Скользя кончиками [npc.her] пальцев по [npc.her] [npc.pussy+], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(use)] [npc.her] пальцев раздвигает [npc.her] губы киски.",
						"[npc.Name] жадно [npc.verb(slide)] [npc.her] [npc.fingers] по [npc.her] неистовой [npc.pussy], [npc.moaning+], пока [npc.she] [npc.verb(use)] [npc.her] пальцев раздвигает мягкие складки [npc.her]."));
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.transferLubrication(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA);
		}
	};
	
	public static final SexAction SELF_FINGER_VAGINA_PENETRATION = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Пальцы [npc.herself]";
		}

		@Override
		public String getActionDescription() {
			return "Начать ласкать [npc.herself] пальцами.";
		}

		@Override
		public String getDescription() {
			return (UtilText.returnStringAtRandom(
					"Протянув руку между [npc.her] [npc.legs], [npc.name] [npc.verb(tease)] [npc.her] [npc.fingers] над входом в [npc.her] [npc.pussy+], она издает [npc.a_moan+], когда [npc.she] [npc.verb(push)] [npc.her] пальцев глубоко внутри.",
					"[npc.Name] [npc.verb(probe)] [npc.her] пальцы вниз между [npc.her] [npc.legs], [npc.moaning] мягко, когда [npc.she] [npc.verb(push)] два [npc.her] пальца в [npc.her], манящую [npc.pussy+].",
					"Скользя кончиками [npc.her] по заброшенной [npc.pussy] [npc.her], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(push)] [npc.her] внутрь и [npc.verb(start)] ласкает [npc.herself].",
					"[npc.Name] жадно [npc.verb(push)] [npc.her] пальцами в [npc.her] неистовую [npc.pussy], [npc.moaning+] пока [npc.she] [npc.verb(curl)] [npc.her] пальцы внутри [npc.herself] и [npc.verb(start)] поглаживает их манящим движением."));
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Нежное ласкание пальцами (самостоятельно)";
		}

		@Override
		public String getActionDescription() {
			return "Нежно ласкать [npc.her] [npc.pussy+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] медленно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.pussy+].",
					"Мягко погонялками [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.herself].",
					"Подгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] тихонько скуля, пока [npc.she] [npc.verb(start)] поглаживает стенки [npc.her] влагалища.",
					"Сосредоточившись на удовольствии, которое дает [npc.sheIs] [npc.herself] между [npc.her] [npc.legs], [npc.name] [npc.verb(start)] мягко погоняет [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Ласкать себя пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на ласках [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.pussy+].",
					"[npc.her] [npc.fingers] то и дело входит и выходит из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издавая череду восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.herself].",
					"Подгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] вырываются [npc.a_moan], пока [npc.she] [npc.verb(start)] ласкает стенки [npc.her] влагалища.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] [npc.verb(start)] то и дело вгоняет [npc.her] [npc.fingers] в [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction DOM_SELF_FINGER_VAGINA_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Грубый самомастурбация пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Грубо водить пальцами в [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], когда [npc.she] грубо [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.pussy+], прежде чем начать грубо ласкать [npc.herself] пальцами.",
					"Грубо двигая [npc.her] [npc.fingers] то и дело туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] беспощадно [npc.verb(finger)] [npc.herself].",
					"Сильно подгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] вырываются [npc.a_moan], пока [npc.she] [npc.verb(start)] грубо втирает кончики [npc.her] в стенки [npc.her] влагалища.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] [npc.verb(start)] грубо вколачивая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "Ласкать себя пальцами";
		}

		@Override
		public String getActionDescription() {
			return "Сосредоточиться на ласках [npc.herself].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] выскальзывает из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(push)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.pussy+].",
					"[npc.her] [npc.fingers] то и дело входит и выходит из [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издавая череду восторженных [npc.moans], пока [npc.she] ритмично [npc.verb(finger)] [npc.herself].",
					"Подгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] вырываются [npc.a_moan], пока [npc.she] [npc.verb(start)] ласкает стенки [npc.her] влагалища.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] [npc.verb(start)] то и дело вгоняет [npc.her] [npc.fingers] в [npc.her] [npc.pussy+].");
		}
	};
	
	public static final SexAction SUB_SELF_FINGER_VAGINA_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "Страстное самокуннилингус (пальцами)";
		}

		@Override
		public String getActionDescription() {
			return "Страстно ласкать [npc.herself] пальцами.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.A_moan+] вырывается из между [npc.namePos] [npc.lips+], пока [npc.she] жадно [npc.verb(slam)] [npc.her] [npc.fingers] глубоко внутри [npc.her] [npc.pussy+], прежде чем начать отчаянно перебирать [npc.herself].",
					"Энтузиастично вгоняя [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.pussy+], [npc.name] [npc.verb(start)] издавая серию восторженных [npc.moans], пока [npc.she] неистово [npc.verb(finger)] [npc.herself].",
					"Отчаянно сгибая [npc.her] [npc.fingers] внутри [npc.her] [npc.pussy], [npc.name] [npc.verb(let)] извлекая [npc.a_moan], пока [npc.she] жадно [npc.verb(start)] надавливает кончиками [npc.her] на стенки [npc.her] влагалища.",
					"Сосредоточившись на удовольствии, которое [npc.sheIs] дарит [npc.herself] между [npc.her] [npc.legs], [npc.name] жадно [npc.verb(start)] вколачивая [npc.her] [npc.fingers] туда-сюда в [npc.her] [npc.pussy+].");
		}

	};
	
	public static final SexAction SELF_FINGER_VAGINA_STOP_PENETRATION = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Прекратить самокуннилингус (пальцами)";
		}

		@Override
		public String getActionDescription() {
			return "Прекратить ласкать [npc.herself] пальцами.";
		}

		@Override
		public String getDescription() {
			return "С [npc.a_groan+], [npc.name] [npc.verb(slide)] вынимает [npc.her] пальцы из [npc.her] [npc.pussy+].";
		}
	};
}
