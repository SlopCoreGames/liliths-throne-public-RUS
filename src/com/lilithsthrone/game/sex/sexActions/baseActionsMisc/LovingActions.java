package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.3.2
 * @version 0.4.3.2
 * @author Innoxia
 */
public class LovingActions {
	
	public static final SexAction CARESS_CHEEK = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			Map<InventorySlot, List<AbstractClothing>> concealedMap = Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction());
			if(concealedMap.containsKey(InventorySlot.MOUTH) && concealedMap.containsKey(InventorySlot.EYES)) {
				return false; // If mouth and eyes are concealed, treat face as being concealed and so unavailable
			}
			
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
					&& (mouthFinger || mouthFingerReversed);
		}
		
		@Override
		public String getActionTitle() {
			return "Ласкать лицо";
		}

		@Override
		public String getActionDescription() {
			return "Нежно ласкать лицо [npc2.namePos], чтобы [npc2.herHim] чувствовала себя любимой.";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Желая подарить [npc2.name] чувство любви, [npc.name] [npc.verb(lift)] [npc.her] [npc.hand], прежде чем мягко провести [npc.her] [npc.fingers+] по [npc2.her] лицу.",
					"Приподнимая [npc.her] за [npc.hand], [npc.name] мягко [npc.verb(trace)] [npc.her] [npc.fingers+] по [npc2.namePos] щеке, чтобы заставить [npc2.herHim] почувствовать себя любимой.",
					"Стремясь успокоить [npc2.name], [npc.name] [npc.verb(raise)] [npc.her] [npc.hand], прежде чем использовать [npc.her] [npc.fingers+], чтобы с любовью ласкать [npc2.her] лицо."));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" Счастливый [npc2.moan] срывается с губ [npc2.namePos] в ответ на это прикосновение, заставляя [npc.name] довольно улыбнуться.",
							" Счастливого вздоха, вырвавшегося из [npc2.namePos] рта, более чем достаточно, чтобы [npc.name] поняла, что нежная ласка [npc.her] произвела желаемый эффект.",
							" В ответ на это, [npc2.name] [npc2.verb(let)] счастливый [npc2.moan], давая [npc.name] понять, что [npc.her] жест очень ценится."));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							" Протестующий вскрик срывается с губ [npc2.namePos], когда [npc2.she] [npc2.verb(continue)], чтобы сопротивляться [npc.namePos] напору.",
							" Раздраженное восклицание, которое тут же срывается с губ [npc2.namePos], — совсем не то, что искал [npc.name] [npc.was], и [npc.she] не может не вздохнуть, когда [npc2.name] [npc2.verb(continue)], сопротивляясь.",
							" Издав шокированный возглас, [npc2.namePos] [npc2.eyes] наполняются слезами, давая [npc.name] понять, что ухаживания [npc.her] не производят того эффекта, который [npc.she] [npc.was] искала."));
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction KISS_CHEEK = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
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
					&& (mouthTongue || mouthTongueReversed);
		}
		
		@Override
		public String getActionTitle() {
			return "Поцеловать в щеку";
		}

		@Override
		public String getActionDescription() {
			return "Запечатлеть нежный поцелуй на щеке [npc2.namePos].";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"Желая показать [npc2.name], как сильно [npc2.sheIs] любит, [npc.name] [npc.verb(lean)] к [npc2.herHim], прежде чем нежно поцеловать [npc2.her] в щеку.",
					"Наклоняясь к [npc2.name], [npc.name] [npc.verb(purse)] [npc.her] [npc.lips+], прежде чем запечатлеть любящий поцелуй на щеке [npc2.her].",
					"Желая напомнить [npc2.name] о том, как сильно [npc2.sheIsFull] любит, [npc.name] [npc.verb(lean)] к [npc2.herHim], прежде чем сжать [npc.her] [npc.lips+] и поцеловать [npc2.her] в щеку."));

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" Счастливый [npc2.moan] срывается с губ [npc2.namePos] в ответ на это прикосновение, заставляя [npc.name] довольно улыбнуться.",
							" Счастливого вздоха, вырвавшегося из уст [npc2.namePos], более чем достаточно, чтобы дать [npc.name] понять: поцелуй [npc.her] произвел желаемый эффект.",
							" В ответ на это, [npc2.name] [npc2.verb(let)] счастливый [npc2.moan], давая [npc.name] понять, что [npc.her] жест очень ценится."));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							" Протестующий вскрик срывается с губ [npc2.namePos], когда [npc2.she] [npc2.verb(continue)], чтобы сопротивляться [npc.namePos] напору.",
							" Раздраженное восклицание, которое тут же срывается с губ [npc2.namePos], — совсем не то, что искал [npc.name] [npc.was], и [npc.she] не может не вздохнуть, когда [npc2.name] [npc2.verb(continue)], сопротивляясь.",
							" Издав шокированный возглас, [npc2.namePos] [npc2.eyes] наполняются слезами, давая [npc.name] понять, что ухаживания [npc.her] не производят того эффекта, который [npc.she] [npc.was] искала."));
				}
			}
			
			return sb.toString();
		}
	};

	public static final SexAction STROKE_BELLY = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)), // Do not directly associate the sex action with Breasts, as otherwise it seems confusing to the player (and there is no SexAreaOrifice for stomach)
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public boolean isLovingAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// The reasoning is that if the performer can reach the target's breasts or pussy, they can also reach their stomach:
			boolean fingerBreasts = false;
			boolean fingerVagina = false;
			try {
				fingerBreasts = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.BREAST);
				fingerVagina = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
				// No available finger-breast actions, so can't reach breasts (which implies that they can't reach the stomach)
			}
			
			return (fingerBreasts || fingerVagina) && Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant();
		}
		@Override
		public String getActionTitle() {
			return "Погладить живот";
		}
		@Override
		public String getActionDescription() {
			return "Нежно поглаживать беременный живот [npc2.namePos].";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			boolean performerPossiblyFather = Main.sex.getCharacterTargetedForSexAction(this).isCharacterPossiblyFather(Main.sex.getCharacterPerformingAction());
			boolean performerIsFather = performerPossiblyFather && Main.sex.getCharacterTargetedForSexAction(this).getPotentialPartnersAsMother().size()==1;
			
			String pregnantBellyDesc = Util.randomItemFromValues("беременный живот", "раздутый живот", "округлый животик", "беременный животик");
			String fatherText = Main.sex.getCharacterPerformingAction().isFeminine()?"«отец»":"father";
			
			sb.append(UtilText.returnStringAtRandom(
					"Желая, чтобы [npc2.name] знал, что [npc2.sheIs] любил, [npc.name] [npc.verb(place)] [npc.her] [npc.hands] на [npc2.her] "+pregnantBellyDesc+" и [npc.verb(give)] его нежным поглаживанием.",
					"Положив [npc.her] [npc.hands] на [npc2.namePos] "+pregnantBellyDesc+", [npc.name] [npc.verb(start)] с любовью поглаживал и похлопывал его.",
					"Стремясь показать [npc2.name], как сильно [npc.she] [npc.verb(love)] [npc2.herHim], [npc.name] нежно [npc.verb(place)] [npc.her] [npc.hands] на [npc2.her] "+pregnantBellyDesc+", прежде чем ласково погладить его несколько раз."));
			
			if(performerIsFather) {
				sb.append(UtilText.returnStringAtRandom(
						" Зная, что [npc.she] [npc.is]  "+fatherText+" из-за потомства, растущего внутри живота [npc2.namePos], [npc.name] [npc.verb(make)] обязательно будет проявлять особую нежность и любовь, когда [npc.she] [npc.verb(run)] [npc.her] [npc.hands] над [npc.her] партнером "+pregnantBellyDesc+".",
						" Ведь совершенно очевидно, что [npc.name] [npc.is]  "+fatherText+" отпрышина [npc2.namePos], [npc.she] [npc.verb(decide)] не упускает возможности лишний раз нежно погладить живот [npc.her] партнера.",
						" Поскольку [npc.she] [npc.is] определенно  "+fatherText+" отпрышина [npc2.namePos], [npc.name] [npc.verb(make)] старается быть предельно нежным и любящим, поглаживая живот [npc.her] партнера. "+pregnantBellyDesc+"."));
				
			} else if(performerPossiblyFather) {
				sb.append(UtilText.returnStringAtRandom(
						" Зная, что [npc.she] может быть  "+fatherText+" из-за потомства, растущего внутри живота [npc2.namePos], [npc.name] [npc.verb(make)] обязательно будет проявлять особую нежность и любовь, когда [npc.she] [npc.verb(run)] [npc.her] [npc.hands] над [npc.her] партнером "+pregnantBellyDesc+".",
						" Так как вполне возможно, что [npc.name] может быть  "+fatherText+" отпрышина [npc2.namePos], [npc.she] [npc.verb(decide)] не упускает возможности лишний раз нежно погладить живот [npc.her] партнера.",
						" Как [npc.she] может быть "+fatherText+" отпрышина [npc2.namePos], [npc.name] [npc.verb(make)] старается быть предельно нежным и любящим, поглаживая живот [npc.her] партнера. "+pregnantBellyDesc+"."));
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							" Счастливый [npc2.moan] срывается с губ [npc2.namePos] в ответ на это прикосновение, заставляя [npc.name] довольно улыбнуться.",
							" Счастливого вздоха, вырвавшегося из [npc2.namePos] рта, более чем достаточно, чтобы [npc.name] поняла, что нежная ласка [npc.her] произвела желаемый эффект.",
							" В ответ на это, [npc2.name] [npc2.verb(let)] счастливый [npc2.moan], давая [npc.name] понять, что [npc.her] жест очень ценится."));
				} else {
					sb.append(UtilText.returnStringAtRandom(
							" Протестующий вскрик срывается с губ [npc2.namePos], когда [npc2.she] [npc2.verb(continue)], чтобы сопротивляться [npc.namePos] напору.",
							" Раздраженное восклицание, которое тут же срывается с губ [npc2.namePos], — совсем не то, что искал [npc.name] [npc.was], и [npc.she] не может не вздохнуть, когда [npc2.name] [npc2.verb(continue)], сопротивляясь.",
							" Издав шокированный возглас, [npc2.namePos] [npc2.eyes] наполняются слезами, давая [npc.name] понять, что ухаживания [npc.her] не производят того эффекта, который [npc.she] [npc.was] искала."));
				}
			}
			
			return sb.toString();
		}
	};
}
