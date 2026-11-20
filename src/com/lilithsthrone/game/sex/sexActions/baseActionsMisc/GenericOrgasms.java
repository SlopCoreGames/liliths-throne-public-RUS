package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.CondomFailure;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.OrgasmEncourageBehaviour;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.4.11.1
 * @author Innoxia
 */
public class GenericOrgasms {
	
	public static boolean isTakingCock(GameCharacter character, GameCharacter penetrator) {
		return !Collections.disjoint(
				Main.sex.getOngoingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				Util.newArrayListOfValues(
						SexAreaOrifice.VAGINA,
						SexAreaOrifice.ANUS,
						SexAreaOrifice.NIPPLE,
						SexAreaOrifice.MOUTH,
						SexAreaOrifice.SPINNERET,
						SexAreaOrifice.URETHRA_PENIS,
						SexAreaOrifice.URETHRA_VAGINA,
						SexAreaOrifice.BREAST,
						SexAreaOrifice.ARMPITS,
						SexAreaOrifice.THIGHS,
						SexAreaPenetration.FOOT));
	}
	
	/**
	 * For SexAreaPenetration.PENIS checks, this only works if the penis is not a dildo (as this method is only used for forced creampie checks, in which case dildos shouldn't be counted)
	 */
	private static boolean isTakingCockInOrifice(GameCharacter character, GameCharacter penetrator, List<SexAreaInterface> orificesToCheck) {
		// Only check for penis-to-penis if it's a real cock
		List<SexAreaInterface> copyList = new ArrayList<>(orificesToCheck);
		if(!isRealPenisFuckingCharacter(character, penetrator)) {
			copyList.remove(SexAreaPenetration.PENIS);
		}
		
		return !Collections.disjoint(
				Main.sex.getOngoingSexAreas(penetrator, SexAreaPenetration.PENIS, character),
				copyList);
	}
	
	private static boolean isRealPenisFuckingCharacter(GameCharacter character, GameCharacter penetrator) {
		return Main.sex.getCharacterOngoingSexArea(penetrator, SexAreaPenetration.PENIS).contains(character)
				&& penetrator.hasPenisIgnoreDildo();
	}

	public static boolean isCumTargetRequirementsMet(SexActionInterface sexAction, OrgasmCumTarget cumTarget) {
		OrgasmCumTarget preferredPulloutTarget = Main.sex.getInitialSexManager().getCharacterPullOutOrgasmCumTarget(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
		SexAreaOrifice penisPenetratingArea = Main.sex.getFirstOngoingSexAreaOrifice(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
		boolean isPenetratingInternalOrifice = penisPenetratingArea!=null && penisPenetratingArea.isInternalOrifice();
		
		if(!Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).contains(cumTarget)
				|| (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING && cumTarget.isRequiresPartner())
				|| !Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
				|| !Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
				// If the character is wearing a condom, then they will always creampie if penetrating an orifice, otherwise allow them to choose an area to cum onto if their condom breaks:
				|| (Main.sex.getCharacterPerformingAction().isWearingCondom()
						&& (sexAction.getCondomFailure(Main.sex.getCharacterPerformingAction(), Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))==CondomFailure.NONE
							|| isPenetratingInternalOrifice))
				|| (!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0)
				|| (preferredPulloutTarget!=null && preferredPulloutTarget!=cumTarget)) {
			return false;
		}
		
		return true;
	}

	private static boolean isCharacterTotallyImmobilised(GameCharacter character) {
		return Main.sex.isCharacterImmobilised(character) && Main.sex.isCharacterInanimateFromImmobilisation(character);
	}
	
	private static boolean isPerformingCharacterTotallyImmobilised() {
		return isCharacterTotallyImmobilised(Main.sex.getCharacterPerformingAction());
	}
	
	private static boolean isTargetedCharacterTotallyImmobilised(SexActionInterface sexAction) {
		return isCharacterTotallyImmobilised(Main.sex.getCharacterTargetedForSexAction(sexAction));
	}
	
	private static String getPositionPreparation(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		if(characterTargeted!=null) {
			String orgasmText = Main.sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, characterTargeted);
			return UtilText.parse(characterOrgasming, characterTargeted, orgasmText);
			
		} else {
			String orgasmText = Main.sex.getSexPositionSlot(characterOrgasming).getOrgasmDescription(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming));
			return UtilText.parse(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming), orgasmText);
		}
	}
	
	private static String getAhegaoDescription(GameCharacter characterOrgasming, GameCharacter characterTargeted) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
		if(characterOrgasming.isPlayer()) {
			sb.append("As [npc.name] [npc.verb(reach)] [npc.her] climax, [npc.she] can't help but [npc.verb(let)] out an extremely loud, shuddering [npc.moan]. Within moments, your mind has gone blank, and as you lose [npc.herself] to the overwhelming strength of [npc.her] orgasm, you unwittingly [npc.verb(find)] [npc.herself] making an extremely lewd facial expression. As [npc.her] [npc.tongue+] lolls out of [npc.her] mouth, [npc.she] [npc.verb(feel)] [npc.her] cheeks flushing, and can't stop [npc.herself] from rolling [npc.her] [npc.eyes] upwards. With this expression of exaggerated ecstasy temporarily stuck on [npc.her] face, [npc.name] [npc.verb(let)] out yet another desperate [npc.moan] and [npc.verb(prepare)] to experience the full force of [npc.her] incoming orgasm.");
			
		} else {
			sb.append("Когда [npc.name] [npc.verb(reach)] [npc.her] достигает пика, [npc.she] [npc.verb(let)] издает чрезвычайно громкий, дрожащий [npc.moan]. Мгновение спустя [npc.sheHas] теряет [npc.herself] перед сокрушительной силой [npc.her] оргазма, и [npc.she] невольно [npc.verb(find)] [npc.herself], застыв с крайне похотливым выражением лица. Пока [npc.her] [npc.tongue+] свисает из [npc.her] рта, [npc.she] [npc.verb(feel)], а [npc.her] щеки пылают, и не может остановить [npc.herself] от закатывания [npc.her] [npc.eyes] вверх. С этим выражением преувеличенного экстаза, временно застывшим на [npc.her] лице, [npc.name] [npc.verb(let)] еще один отчаянный [npc.moan] и [npc.verb(prepare)], чтобы ощутить всю мощь приближающегося [npc.her] оргазма.");
		}
		sb.append("</p>");
		
		return UtilText.parse(characterOrgasming, characterTargeted, sb.toString());
	}
	
	private static String getPenisOrgasmModifierDescriptionPostfix(GameCharacter targetedCharacter) {
		if(targetedCharacter.isAsleep()) {
			return UtilText.returnStringAtRandom(
					", но, несмотря на эту дополнительную стимуляцию, [npc2.she] не просыпается.",
					", но [npc2.she] все еще [npc.verb(remain)] совершенно крепко спит.");
			
		} else if(isCharacterTotallyImmobilised(targetedCharacter)) {
			return UtilText.returnStringAtRandom(
					", но, несмотря на эту дополнительную стимуляцию, [npc2.she] никак не реагирует.",
					", но [npc2.she] [npc.verb(remain)] совершенно безмолвен и неподвижен.");
			
		} else {
			return ", что заставляет [npc2.herHim] издать [npc2.a_moan+].";
		}
	}
	
	private static StringBuilder genericOrgasmSB = new StringBuilder();
	
	public static String getGenericPenisOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, GameCharacter characterTargeted, OrgasmCumTarget cumTarget, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
		genericOrgasmSB.setLength(0);

		SexAreaInterface contactingArea = null;
		if(!Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			contactingArea = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
		}
		
		if(!isSecondaryCreampieTarget) {
			if(!characterOrgasming.equals(characterTargeted)) { // Do not append this part if the target is the same person as the performer
				if(Main.sex.getCreampieLockedBy().containsKey(characterOrgasming)) {
					GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(characterOrgasming).getKey();
					Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(characterOrgasming).getValue();
					String orgasmPreventionDesc = "вытянуть";
					if(Main.sex.getOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS, lockingCharacter).contains(SexAreaPenetration.PENIS)) {
						orgasmPreventionDesc = "оттянуть [npc.her] [npc.cock+] от [npc2.namePos]"; // frotting variation
					}
					
					if(bodypart == Torso.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"Когда [npc2.name] плотно прижимает [npc2.herself] к [npc.herHim], [npc.nameIsFull] не может "+orgasmPreventionDesc+", и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] к оргазму."));
						
					} else if(bodypart == Arm.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"Когда [npc2.namePos] [npc2.arms] плотно обхватывает низкую часть спины [npc.her], [npc.nameIsFull] не может "+orgasmPreventionDesc+", и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] к оргазму."));
						
					} else if(bodypart == Leg.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"Когда [npc2.namePos] [npc2.legs] плотно обхватывают нижнюю часть спины [npc.her], [npc.nameIsFull] не в силах "+orgasmPreventionDesc+", и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] к оргазму."));
						
					} else if(bodypart == Tail.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"Когда [npc2.namePos] [npc2.tail] плотно обвивают нижнюю часть спины [npc.her], [npc.nameIsFull] не в силах "+orgasmPreventionDesc+", и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] к оргазму."));
						
					} else if(bodypart == Tail.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"Когда [npc2.namePos] [npc2.wingSize] [npc2.wings] плотно обхватили тело [npc.her], [npc.nameIsFull] было не в силах "+orgasmPreventionDesc+", и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] к оргазму."));
						
					} else if(bodypart == Tentacle.class) {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, lockingCharacter,
								"Когда [npc2.namePos] [npc2.tentacles] плотно обвивают нижнюю часть спины [npc.her], [npc.nameIsFull] не в силах "+orgasmPreventionDesc+", и [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(start)] к оргазму."));
					}
				}
			}
			
		} else {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			if(contactingArea!=null) {
				switch((SexAreaOrifice)contactingArea) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterPenetrated, characterTargeted),
								"Пока половина порции [npc.her] вкачивается в [npc2.namePos] [npc2.asshole+], и [npc.her] всё еще находится в разгаре оргазма, [npc.name] внезапно [npc.verb(pull)], прежде чем быстро переместиться к [npc3.name] и приставить [npc.cockHead+] [npc.her] [npc.cock+] к [npc3.her] [npc3.asshole+]."));
						break;
					case VAGINA:
						genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterPenetrated, characterTargeted),
								"Пока половина порции [npc.her] вкачивается в [npc2.namePos] [npc2.pussy+], и [npc.her] всё еще находится в разгаре оргазма, [npc.name] внезапно [npc.verb(pull)], прежде чем быстро переместиться к [npc3.name] и приставить [npc.cockHead+] [npc.her] [npc.cock+] к [npc3.her] [npc3.pussy+]."));
						break;
				}
			}
		}
		
		if(characterTargeted==null || (cumTarget!=OrgasmCumTarget.INSIDE && cumTarget!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE)) {
			List<String> modifiers = new ArrayList<>();
			for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
				switch(mod) {
					case FLARED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add(" широкая, раздутая головка [npc.namePos] [npc.cock] набухает, и [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] сжимаются, когда [npc.she] [npc.verb(start)] к кончил.");
						}
						break;
					case KNOTTED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add(" толстое уплотнение у основания [npc.namePos] [npc.cock] набухает, и [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] сжимаются, когда [npc.she] [npc.verb(start)] к кончил.");
						}
						break;
					case TENTACLED:
						if(characterOrgasming.hasPenisModifier(mod)) {
							modifiers.add(" маленькие щупальца, выстилающие [npc.namePos] [npc.cock], начинают неистово извиваться, а [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] сжимаются, когда [npc.she] [npc.verb(start)] кончить.");
						}
						break;
					case BARBED:
					case BLUNT:
					case PREHENSILE:
					case RIBBED:
					case SHEATHED:
					case TAPERED:
					case VEINY:
					case OVIPOSITOR:
						break;
				}
			}

			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			if(!modifiers.isEmpty()) {
				if(!ongoingProstateStimulators.isEmpty() && !isCharacterTotallyImmobilised(ongoingProstateStimulators.get(0))) {
					if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
								" В этот момент [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] внутри [npc.her] "+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+", прежде чем начать быстро ласкать и массировать [npc.her] простату, пытаясь выжать из [npc.herself] как можно больше [npc.cum]. Тело [npc.her] мгновенно реагирует на эту дополнительную стимуляцию, и "));
					} else {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
								" В этот момент [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] внутри [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+", прежде чем начать быстро ласкать и массировать [npc2.her] простату, пытаясь выжать из [npc2.herHim] как можно больше [npc2.cum]. Тело [npc2.her] мгновенно реагирует на эту дополнительную стимуляцию, и "));
					}
				} else {
					genericOrgasmSB.append("");
				}
				genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
				
			} else {
				if(!ongoingProstateStimulators.isEmpty() && !isCharacterTotallyImmobilised(ongoingProstateStimulators.get(0))) {
					if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
								" В этот момент [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] внутри [npc.her] "+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+", прежде чем начать быстро ласкать и массировать [npc.her] простату, пытаясь выжать из [npc.herself] как можно больше [npc.cum]. Тело [npc.her] мгновенно реагирует на эту дополнительную стимуляцию, и от вздрагивания [npc.her] [npc.cock+], [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] сжимаются, когда [npc.she] [npc.verb(start)] кончить."));
					} else {
						genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
								" В этот момент [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] внутри [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+", прежде интенсивно поглаживая и массируя простату [npc2.her] в попытке выжать из [npc2.herHim] как можно больше [npc2.cum]. Тело [npc2.her] мгновенно реагирует на эту дополнительную стимуляцию, и при вздрагивании [npc2.her] [npc2.cock+], [npc2.she] [npc2.verb(feel)] [npc2.her] [npc2.balls+] сжимается, пока [npc2.she] [npc2.verb(start)] к оргазму."));
					}
				} else {
					genericOrgasmSB.append(" [npc.NamePos] [npc.cock+] дергается, и [npc.she] [npc.verb(feel)] [npc.her] [npc.balls+] сжимается, пока [npc.she] [npc.verb(start)] к оргазму.");
				}
			}
			
			if(characterTargeted!=null && contactingArea!=null) {
				genericOrgasmSB.append("<br/>");
				
				boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
				boolean sleeping = characterOrgasming.isAsleep();
				boolean immobileTarget = isCharacterTotallyImmobilised(characterTargeted);
				boolean sleepingTarget = characterTargeted.isAsleep();
				boolean selfTargeting = characterOrgasming.equals(characterTargeted);
				
				if(contactingArea.isOrifice()) {
					if(immobile) {
						if(sleeping) {
							genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] спит, пока [npc.her] [npc.cock+] трется о [npc2.namePos] "+((SexAreaOrifice) contactingArea).getName(characterTargeted));
						} else {
							genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] совершенно неподвижен, пока [npc.her] [npc.cock+] трется о [npc2.namePos] "+((SexAreaOrifice) contactingArea).getName(characterTargeted));
						}
						
					} else {
						switch((SexAreaOrifice) contactingArea) {
							case ARMPITS:
								genericOrgasmSB.append("[npc.Name] [npc.verb(pull)] [npc.her] [npc.cock+] прочь от [npc2.namePos] [npc2.armpit+]");
								break;
							case ANUS:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] [npc2.asshole+]");
								break;
							case ASS:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из-под ягодиц [npc2.namePos]");
								break;
							case BREAST:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из между [npc2.namePos] [npc2.breasts+]");
								break;
							case BREAST_CROTCH:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из между [npc2.namePos] [npc2.crotchBoobs+]");
								break;
							case MOUTH:
								if(!Main.sex.getCreampieLockedBy().containsKey(characterOrgasming)) {
									GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
									genericOrgasmSB.append(UtilText.parse(characterOrgasming, primary, "[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] изо рта [npc2.namePos]"));
								} else {
									genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] изо рта [npc2.namePos]");
								}
								break;
							case NIPPLE:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] [npc2.nipple+]");
								break;
							case NIPPLE_CROTCH:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] [npc2.crotchNipple+]");
								break;
							case THIGHS:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из-под [npc2.namePos] бедер");
								break;
							case URETHRA_PENIS:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] [npc2.penisUrethra+]");
								break;
							case URETHRA_VAGINA:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] [npc2.vaginaUrethra+]");
								break;
							case VAGINA:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] [npc2.pussy+]");
								break;
							case SPINNERET:
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] из [npc2.namePos] [npc2.spinneret+]");
								break;
						}
					}
				
					if(!characterOrgasming.getPenisModifiers().isEmpty() && !immobile) {
						switch(characterOrgasming.getPenisModifiers().get(Util.random.nextInt(characterOrgasming.getPenisModifiers().size()))) {
							case BARBED:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать водить [npc.her] [npc.hand] вверх и вниз по [npc.her] чувствительным маленьким бугоркам.");
								break;
							case BLUNT:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать неистово мастурбировать; [npc.her] [npc.hand] скользит по всей длине [npc.her] [npc.cock], чтобы тереть и дразнить [npc.her] тупую головку.");
								break;
							case FLARED:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать неистово мастурбировать; [npc.her] [npc.hand] скользит по всей длине [npc.her] [npc.cock], чтобы тереть и дразнить [npc.her] широкую, расширенную головку.");
								break;
							case KNOTTED:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать неистово мастурбировать; [npc.her] [npc.hand] скользит вдоль всей длины [npc.her] [npc.cock], чтобы обхватить и теребить [npc.her] разбухший узел.");
								break;
							case PREHENSILE:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать неистово мастурбировать; [npc.her] [npc.hand] скользит вдоль всей длины цепкого [npc.cock] [npc.her], пока [npc.she] [npc.verb(curl)] его обратно к [npc.her] [npc.fingers].");
								break;
							case RIBBED:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать водить [npc.her] [npc.hand] вверх и вниз по бугристым ребрам, идущим вдоль [npc.her] [npc.cock].");
								break;
							case SHEATHED:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать неистово мастурбировать; [npc.her] [npc.hand] скользит вдоль всей длины [npc.her] [npc.cock], чтобы ткнуться в [npc.her] чехол, а затем снова подняться к [npc.her] [npc.cockHead+].");
								break;
							case TAPERED:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать неистово мастурбировать; [npc.her] [npc.hand] скользит вверх по всей длине [npc.her] [npc.cock], чтобы теребить и дразнить [npc.her] сужающуюся головку.");
								break;
							case TENTACLED:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать водить [npc.her] [npc.hand] вверх и вниз по извивающимся щупальцам, покрывающим бока [npc.her] [npc.cock].");
								break;
							case VEINY:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать водить [npc.her] [npc.hand] вверх и вниз по [npc.her] венистому [npc.cock].");
								break;
							case OVIPOSITOR:
								genericOrgasmSB.append(", прежде чем потянуться вниз и начать водить [npc.her] [npc.hand] вверх и вниз по [npc.her] [npc.cock+].");
								break;
						}
						
					} else {
						if(immobile) {
							if(sleeping) {
								genericOrgasmSB.append(".");
							} else {
								genericOrgasmSB.append(", исполняя роль неодушевленной секс-куклы с абсолютным совершенством.");
							}
						} else {
							genericOrgasmSB.append(", прежде чем опуститься рукой вниз и начать неистово мастурбировать; [npc.her] [npc.hand] пробегает по всей длине [npc.her] [npc.cock], чтобы тереться и дразнить [npc.her] [npc.cockHead].");
						}
					}
					
				} else {
					switch((SexAreaPenetration) contactingArea) {
						case CLIT:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] во сне, пока [npc.her] [npc.cock+] трется о [npc2.namePos] [npc2.pussy+].");
								} else {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] совершенно неподвижно, пока [npc.her] [npc.cock+] трется о [npc2.namePos] [npc2.pussy+], с абсолютным совершенством исполняя роль неодушевленной секс-куклы.");
								}
							} else {
								genericOrgasmSB.append("[npc.Name] [npc.verb(take)] [npc.her] [npc.cock+] от [npc2.namePos] [npc2.pussy+], прежде чем опуститься рукой вниз и начать неистово мастурбировать; [npc.her] [npc.hand] пробегает по всей длине [npc.her] [npc.cock], чтобы тереться и дразнить [npc.her] [npc.cockHead].");
							}
							break;
						case FINGER:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] во сне, пока");
								} else {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] совершенно неподвижно, пока");
								}
								if(immobileTarget) {
									if(sleepingTarget) {
										genericOrgasmSB.append(" [npc2.name] также [npc2.verb(sleep)], при этом [npc2.her] [npc2.fingers+] обвивает [npc.namePos] [npc.cock+].");
									} else {
										genericOrgasmSB.append(" [npc2.name] так же [npc.verb(stay)], оставаясь совершенно неподвижной, пока [npc2.her] [npc2.fingers+] обвивает [npc.namePos] [npc.cock+].");
									}
								} else {
									genericOrgasmSB.append(
											UtilText.returnStringAtRandom(
													" [npc2.name] [npc2.verb(continue)] гладить [npc.her] [npc.cock+].",
													selfTargeting?null:" [npc2.name] [npc2.verb(continue)], делая [npc.herHim] минет через [npc.her] во время оргазма.",
													" [npc2.name] [npc2.verb(continue)] ласкает [npc.her] [npc.cock+] во время [npc.her] оргазма."));
								}
							} else {
								genericOrgasmSB.append(
										UtilText.returnStringAtRandom(
												" [npc.Name] [npc.verb(let)] вытаскивает [npc.a_moan+] и [npc.verb(buck)] [npc.her] [npc.hips+] вперед",
												" Издавая [npc.a_moan+], [npc.name] [npc.verb(buck)] [npc.her] [npc.hips+] вперед",
												" Дергаясь, [npc.her] [npc.hips] вперед, [npc.name] [npc.verb(let)] наружу [npc.a_moan+]"));
								if(immobileTarget) {
									if(sleepingTarget) {
										genericOrgasmSB.append(" пока [npc2.name] [npc2.verb(remain)] спит, а [npc2.her] [npc2.fingers+] обвивает [npc.namePos] [npc.cock+].");
									} else {
										genericOrgasmSB.append(" пока [npc2.name] [npc2.verb(remain)] замерла на месте, а [npc2.her] [npc2.fingers+] обвивает [npc.namePos] [npc.cock+].");
									}
								} else {
									genericOrgasmSB.append(
											UtilText.returnStringAtRandom(
													" пока [npc2.name] [npc2.verb(continue)] ласкает [npc.her] [npc.cock+].",
													selfTargeting?null:" пока [npc2.name] [npc2.verb(continue)] делает [npc.herHim] мастурбацию во время [npc.her] оргазма.",
													" пока [npc2.name] [npc2.verb(continue)] ласкает [npc.her] [npc.cock+] во время [npc.her] оргазма."));
								}
							}
							break;
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case FOOT:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] спит");
								} else {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] совершенно неподвижно");
								}
							} else {
								genericOrgasmSB.append(" Дергающийся [npc.her] [npc.hips], [npc.name] [npc.verb(let)] наружу [npc.a_moan+]");
							}
							if(immobileTarget) {
								if(sleepingTarget) {
									genericOrgasmSB.append(" пока [npc2.namePos] [npc2.feet+] остаются на месте вокруг [npc.her] [npc.cock+].");
								} else {
									genericOrgasmSB.append(" пока [npc2.name] [npc2.verb(hold)] [npc2.her] [npc2.feet+] на месте вокруг [npc.namePos] [npc.cock+].");
								}
							} else {
								genericOrgasmSB.append(" пока [npc2.name] [npc2.verb(continue)] стимулирует [npc.her] [npc.cock+] с помощью [npc2.her] [npc2.feet+].");
							}
							break;
						case TONGUE:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] спит, пока [npc.her] [npc.cock+] трется о [npc2.namePos] [npc2.lips+].");
								} else {
									genericOrgasmSB.append("[npc.Name] [npc.verb(remain)] совершенно неподвижно, пока [npc.her] [npc.cock+] трется о [npc2.namePos] [npc2.lips+], с абсолютным совершенством исполняя роль неодушевленной секс-куклы.");
								}
							} else {
								genericOrgasmSB.append("[npc.Name] [npc.verb(slide)] [npc.her] [npc.cock+] изо рта [npc2.namePos], прежде чем наклониться и начать яростно мастурбировать; [npc.her] [npc.hand] пробегает по всей длине [npc.her] [npc.cock], чтобы тереть и дразнить [npc.her] [npc.cockHead].");
							}
							break;
					}
				}
				
			}
			
			
		} else if(cumTarget==OrgasmCumTarget.INSIDE || cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
			List<String> modifiers = new ArrayList<>();
			
			boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
			boolean sleeping = characterOrgasming.isAsleep();
			boolean immobileTarget = isCharacterTotallyImmobilised(characterTargeted); //TODO
			
			String penisModPostfix = getPenisOrgasmModifierDescriptionPostfix(characterTargeted);
			
			List<GameCharacter> ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			if(characterOrgasming.hasVagina()) {
				ongoingProstateStimulators = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
			}
			ongoingProstateStimulators.removeIf(c->Main.sex.getSexPace(c)==SexPace.SUB_RESISTING);
			if(!ongoingProstateStimulators.isEmpty() && !isCharacterTotallyImmobilised(ongoingProstateStimulators.get(0))) {
				if(ongoingProstateStimulators.get(0).equals(characterOrgasming)) {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0),
							" В этот момент [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] внутри [npc.her] "+(characterOrgasming.hasVagina()?"[npc.pussy+]":"[npc.asshole+]")+", прежде чем начать быстро поглаживать и массировать [npc.her] простату, пытаясь выжать из [npc.herself] как можно больше [npc.cum]."));
				} else {
					genericOrgasmSB.append(UtilText.parse(ongoingProstateStimulators.get(0), characterOrgasming,
							" В этот момент [npc.name] [npc.verb(curl)] [npc.her] [npc.fingers+] внутри [npc2.namePos] "+(characterOrgasming.hasVagina()?"[npc2.pussy+]":"[npc2.asshole+]")+", прежде чем начать быстро поглаживать и массировать [npc2.her] простату, пытаясь выжать из [npc2.herHim] как можно больше [npc2.cum]."));
				}
			}
			
			if(contactingArea!=null) {
				if(contactingArea.isOrifice()) {
					switch((SexAreaOrifice)contactingArea) {
						case ANUS:
						case NIPPLE:
						case NIPPLE_CROTCH:
						case VAGINA:
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
						case SPINNERET:
							// ...
							String orificeName =
								(contactingArea == SexAreaOrifice.SPINNERET
									?"spinneret"
									:(contactingArea == SexAreaOrifice.VAGINA
										?"[npc2.pussy]"
										:(contactingArea == SexAreaOrifice.ANUS
											?"[npc2.asshole]"
											:(contactingArea == SexAreaOrifice.NIPPLE
													?"[npc2.nipple(true)]"
													:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
															?"[npc2.crotchNipple]"
																	:"urethra")))));
							String orificeNamePlusDescriptor =
									(contactingArea == SexAreaOrifice.SPINNERET
										?"плетущее паутину отверстие"
										:(contactingArea == SexAreaOrifice.VAGINA
											?"[npc2.pussy+]"
											:(contactingArea == SexAreaOrifice.ANUS
												?"[npc2.asshole+]"
												:(contactingArea == SexAreaOrifice.NIPPLE
														?"[npc2.nipple+]"
														:(contactingArea == SexAreaOrifice.NIPPLE_CROTCH
																?"[npc2.crotchNipple+]"
																		:"urethra")))));
							
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append(" Все еще спя, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] глубоко внутри [npc2.namePos] "+orificeNamePlusDescriptor+".");
								} else {
									genericOrgasmSB.append(" Оставаясь совершенно неподвижной, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] глубоко внутри [npc2.namePos] "+orificeNamePlusDescriptor+".");
								}
								
							} else if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								if(cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
									if(!isSecondaryCreampieTarget) {
										GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterTargeted, (SexAreaOrifice) contactingArea);
										if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
											genericOrgasmSB.append(" Толкаясь вперед, [npc.name] [npc.verb(hilt)] [npc.her] [npc.cock+] полностью внутри [npc2.namePos] "+orificeNamePlusDescriptor+".");
											if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), secondaryTarget)) {
												genericOrgasmSB.append(" Желая сохранить [npc.her] стремительно раздувающийся узел для "+(UtilText.parse(characterOrgasming, secondaryTarget, "[npc2.namePos] "+orificeNamePlusDescriptor))+", [npc.she] [npc.verb(hold)] назад от попытки втиснуть его в [npc2.name], и [npc.verb(make)] сделать, просто растирая его о [npc2.her] "+orificeName+".");
											} else {
												genericOrgasmSB.append(" Не желая застрять внутри [npc2.herHim], [npc.she] [npc.verb(hold)] назад от попытки втиснуть стремительно раздувающийся узел [npc.her] в [npc2.namePos] "+orificeName+".");
											}
											
										} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted, SexAreaPenetration.PENIS)) {
											genericOrgasmSB.append(" Толкаясь вперед, [npc.name] [npc.verb(feel)] [npc.her] [npc.cock+] упираясь до самого дна в [npc2.namePos] "+orificeNamePlusDescriptor+".");
											if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), secondaryTarget)) {
												genericOrgasmSB.append(" В отчаянии стремясь погрузить стремительно раздувающийся узел [npc.her] во что-то, [npc.name] [npc.verb(look)] прямо на "
														+(UtilText.parse(characterOrgasming, secondaryTarget, "[npc2.namePos] "+orificeNamePlusDescriptor))+" и [npc.verb(let)] возбужденный [npc.moan].");
											} else {
												genericOrgasmSB.append(" [npc.She] [npc.verb(let)] разочарованный [npc.moan], пока [npc.she] [npc.verb(realise)], что [npc.her] стремительно раздувающийся узел не влезет в [npc2.namePos] "+orificeName+".");
											}
											
										} else {
											genericOrgasmSB.append(" Не желая причинять [npc2.her] никакого дискомфорта, [npc.name] [npc.verb(hold)] назад, не вгоняя полностью [npc.her] [npc.cock] в [npc2.namePos] "+orificeNamePlusDescriptor+".");
										}
										
									} else {
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
											genericOrgasmSB.append(" Толкаясь вперед, [npc.name] [npc.verb(ram)] теперь уже полностью раздутый узел у основания [npc.her] [npc.cock+] против [npc2.namePos] "+orificeNamePlusDescriptor+". К этому моменту он так разбух, что кажется почти невозможным протолкнуть его внутрь, но с решимостью [npc.moan], [npc.name] яростно [npc.verb(thrust)] вперед, и под сопровождающий крик [npc2.name], [npc.she] [npc.verb(manage)], чтобы заставить [npc.her] толстый узел войти в [npc2.her] "+orificeNamePlusDescriptor+".");
										} else {
											genericOrgasmSB.append(" Не желая застрять внутри [npc2.herHim], [npc.she] [npc.verb(hold)] назад от попытки втиснуть стремительно раздувающийся узел [npc.her] в [npc2.namePos] "+orificeName+".");
										}
									}
									
								} else {
									if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
										genericOrgasmSB.append(" Толкаясь вперед, [npc.name] [npc.verb(ram)] узел у основания [npc.her] [npc.cock+] против [npc2.namePos] "+orificeNamePlusDescriptor+". Он уже начал так сильно раздуваться, что [npc.she] [npc.do] не удается протолкнуть его внутрь с первого толчка, но после того как он отстранился и с силой вогнал [npc.her] [npc.hips] вперед, [npc.she] [npc.verb(manage)], чтобы втиснуть толстый узел в [npc2.her] "+orificeNamePlusDescriptor+".");
									} else {
										genericOrgasmSB.append(" Не желая застрять внутри [npc2.herHim], [npc.she] [npc.verb(hold)] назад от попытки втиснуть стремительно раздувающийся узел [npc.her] в [npc2.namePos] "+orificeName+".");
									}
								}
								
							} else {
								if(cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget) { 
									genericOrgasmSB.append(" Вгоняя [npc.her] [npc.cock+] в [npc2.namePos] "+orificeNamePlusDescriptor+", [npc.name] [npc.verb(let)] извлекает [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы закончить оргазм внутри [npc2.herHim].");
								} else {
									genericOrgasmSB.append(" Вбивая [npc.her] [npc.cock+] глубоко в [npc2.namePos] "+orificeNamePlusDescriptor+", [npc.name] [npc.verb(let)] из [npc.a_moan+], когда он начинает дергаться внутри [npc2.herHim].");
								}
							}
							
							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Name] [npc.verb(continue)] совершать короткие толчки, вонзая [npc.her] шипы в стенки [npc2.namePos] "+orificeName
													+(immobileTarget?".":" и заставляя [npc2.herHim] издавать [npc2.a_moan+]."));
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer() && !sleeping) {
												modifiers.add(" Ты чувствуешь, как широкая, расширенная головка твоего [npc.cock] раздувается, создавая герметичное соединение, чтобы запереть твою [npc.cum] глубоко внутри [npc2.her] "+orificeName+".");
											} else {
												modifiers.add(" Широкая, расширенная головка [npc.namePos] [npc.cock] раздувается, создавая герметичное соединение, чтобы запереть [npc.her] [npc.cum] глубоко внутри [npc2.namePos] "+orificeName+".");
											}
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer() && !sleeping) {
												modifiers.add(" Ты чувствуешь, как твой ребристый [npc.cock] бьется о внутренние стенки [npc2.her] "+orificeName+penisModPostfix);
											} else {
												modifiers.add(" Ребристый ствол [npc.namePos] [npc.cock] бьется о внутренние стенки [npc2.namePos] "+orificeName+penisModPostfix);
											}
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer() && !sleeping) {
												modifiers.add(" Ты чувствуешь, как извивающиеся щупальца, покрывающие твой [npc.cock], начинают массировать внутренние стенки [npc2.her] "+orificeName+penisModPostfix);
											} else {
												modifiers.add(" Извивающиеся щупальца, покрывающие [npc.namePos], [npc.cock] начинают массировать внутренние стенки [npc2.namePos] "+orificeName+penisModPostfix);
												
											}
										}
										break;
									case BLUNT:
									case KNOTTED:
									case PREHENSILE:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							
							if(!immobile && characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
								if(cumTarget!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) {
									if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
										genericOrgasmSB.append(" Удерживая [npc.her] [npc.hips] плотно прижатым к [npc2.namePos] "+orificeName+", [npc.name] [npc.verb(let)] из [npc.a_moan+], пока [npc.her] не раздуется до своего полного размера.");
										if(immobileTarget) {
											genericOrgasmSB.append(" затем [npc.She] слегка [npc.verb(buck)] назад, заставляя [npc2.name] тянуться вслед за [npc.herHim];");
										} else {
											genericOrgasmSB.append(" затем [npc.She] слегка [npc.verb(buck)] назад, и [npc2.name] [npc2.verb(let)] от неожиданности, пока [npc2.sheIs] тянется вслед за [npc.herHim];");
										}
										genericOrgasmSB.append(" свидетельство того, что [npc.her] [npc.cock] теперь крепко зажат внутри [npc2.her] "+orificeNamePlusDescriptor+".");
									}
								}
							}
							break;
	
						case ARMPITS:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append(" Все еще спящий, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+], прижатый к [npc2.namePos] подмышке.");
								} else {
									genericOrgasmSB.append(" Оставаясь совершенно неподвижным, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+], прижатый к [npc2.namePos] подмышке.");
								}
							} else {
								genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] толкая [npc.her] [npc.cock+] в [npc2.namePos] подмышку, издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
							}
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" движения [npc.Her] заставляют шипы вдоль стенок [npc.her] [npc.cock] скрести по [npc2.namePos] [npc2.arm+]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" раздутая головка [npc.Her] похотливо трется о [npc2.namePos] [npc2.arm]"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" жирное уплотнение [npc.Her] раздувается, и с каждым толчком оно дико бьется о [npc2.namePos] [npc2.arm+]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Используя способность [npc.her] хватательного члена, [npc.name] при каждом толчке обвивает им [npc2.namePos] [npc2.arm+]"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] ребристый ствол при каждом толчке ритмично бьется о [npc2.namePos] [npc2.arm+]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Маленькие щупальца, покрывающие [npc.her] ствол, извиваются и массируют [npc2.namePos] [npc2.arm+]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							break;
							
						case ASS:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append(" Все еще спящая, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] зажат между [npc2.namePos] [npc2.assSize] ягодицами.");
								} else {
									genericOrgasmSB.append(" Совершенно не шевелясь, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] зажат между [npc2.namePos] [npc2.assSize] ягодицами.");
								}
							} else {
								genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] вгоняет [npc.her] [npc.cock+] между [npc2.namePos] [npc2.assSize] ягодицами, издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
							}
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Движения [npc.Her] заставляют шипы по бокам [npc.her] [npc.cock] скрести по [npc2.namePos] [npc2.ass]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Раздутая головка [npc.Her] увеличивается в размерах, заставляя щеки [npc2.namePos] раздвигаться еще шире"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Жирное уплотнение [npc.Her] раздувается, и с каждым толчком оно дико бьется о [npc2.namePos] [npc2.asshole]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Используя способность [npc.her] хватательного члена, [npc.name] при каждом толчке прижимает его к спине [npc2.namePos]"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Her] ребристый ствол при каждом толчке раз за разом ударяется о [npc2.namePos] [npc2.asshole]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют [npc2.namePos] [npc2.asshole]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							break;
							
						case BREAST:
							if(characterTargeted.hasBreasts()) {
								if(immobile) {
									if(sleeping) {
										genericOrgasmSB.append(" Все еще спящая, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] расположена между [npc2.namePos] [npc2.breasts+].");
									} else {
										genericOrgasmSB.append(" Совершенно неподвижная, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] расположена между [npc2.namePos] [npc2.breasts+].");
									}
								} else {
									genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] вгоняет [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
								}
								
								for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
									switch(mod) {
										case BARBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Движения [npc.Her] заставляют шипы, покрывающие [npc.her] [npc.cock], скрести по бокам груди [npc2.namePos]"+penisModPostfix);
											}
											break;
										case FLARED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Раздутая головка [npc.Her] набухает и похотливо трется о [npc2.namePos] [npc2.breasts+]"+penisModPostfix);
											}
											break;
										case KNOTTED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Жирное уплотнение [npc.Her] раздувается, и с каждым толчком оно дико бьется о [npc2.namePos] [npc2.breasts+]"+penisModPostfix);
											}
											break;
										case PREHENSILE:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Используя способность хватательного члена [npc.her], [npc.name] обвивает его вокруг грудей [npc2.namePos], чередуя стороны"+penisModPostfix);
											}
											break;
										case RIBBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" [npc.Her] ребристый ствол при каждом толчке раз за разом ударяется о [npc2.namePos] [npc2.breasts+]"+penisModPostfix);
											}
											break;
										case TENTACLED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют [npc2.namePos] [npc2.breasts+]"+penisModPostfix);
											}
											break;
										case BLUNT:
										case SHEATHED:
										case TAPERED:
										case VEINY:
										case OVIPOSITOR:
											break;
									}
								}
								
							} else {
								if(immobile) {
									if(sleeping) {
										genericOrgasmSB.append(" Все еще спящий, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] покоится на плоской груди [npc2.namePos].");
									} else {
										genericOrgasmSB.append(" Оставаясь совершенно неподвижным, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] покоится на плоской груди [npc2.namePos].");
									}
								} else {
									genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] трется [npc.her] [npc.cock+] о плоскую грудь [npc2.namePos], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
								}
								
								for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
									switch(mod) {
										case BARBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Движения [npc.Her] заставляют шипы, покрывающие [npc.her] [npc.cock], резко царапать торс [npc2.namePos]"+penisModPostfix);
											}
											break;
										case FLARED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Раздутая головка [npc.Her] набухает и похотливо трется о торс [npc2.namePos]"+penisModPostfix);
											}
											break;
										case KNOTTED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Жирное уплотнение [npc.Her] набухает и при каждом толчке дико бьется о торс [npc2.namePos]"+penisModPostfix);
											}
											break;
										case PREHENSILE:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Используя возможности цепкого члена [npc.her], [npc.name] изгибает его то так, то эдак, пока [npc.she] трется о грудь [npc2.namePos]"
														+penisModPostfix);
											}
											break;
										case RIBBED:
											if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Ребристый ствол [npc.Her] при каждом толчке раз за разом бьется о торс [npc2.namePos]"+penisModPostfix);
											}
											break;
										case TENTACLED:
											if(characterOrgasming.hasPenisModifier(mod)) {
												modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют торс [npc2.namePos]"+penisModPostfix);
											}
											break;
										case BLUNT:
										case SHEATHED:
										case TAPERED:
										case VEINY:
										case OVIPOSITOR:
											break;
									}
								}
							}
							break;
							
						case BREAST_CROTCH:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append(" Все еще спящий, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] расположен между [npc2.namePos] [npc2.crotchBoobs+].");
								} else {
									genericOrgasmSB.append(" Оставаясь совершенно неподвижным, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] расположен между [npc2.namePos] [npc2.crotchBoobs+].");
								}
							} else {
								genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] толчками вгоняет [npc.her] [npc.cock+] в [npc2.namePos] [npc2.crotchBoobs+], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
							}
							
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Движения [npc.Her] заставляют шипы, покрывающие [npc.her] [npc.cock], скрести по стенкам [npc2.namePos] [npc2.crotchBoobs]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Раздутая головка [npc.Her] набухает и похотливо трется о [npc2.namePos] [npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Жирное уплотнение [npc.Her] набухает, и с каждым толчком оно неистово бьется о [npc2.namePos] [npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Используя способность хватательного члена [npc.her], [npc.name] обвивает его вокруг разных сторон [npc2.namePos] [npc2.crotchBoobs]"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Ребристый ствол [npc.Her] при каждом толчке раз за разом бьется о [npc2.namePos] [npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют [npc2.namePos] [npc2.crotchBoobs+]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							break;
							
						case MOUTH:
							GameCharacter primary = PenisMouth.getPrimaryBlowjobPerformer(characterOrgasming);
							if(!Main.sex.getCreampieLockedBy().containsKey(characterOrgasming) && !characterTargeted.equals(primary)) {
								if(immobile) { // Doesn't make sense if characterTargeted is resisting...
									if(sleeping) {
										genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
												" [npc.NamePos] [npc.cock+] в последний момент выскальзывает из горла [npc3.namePos], и [npc.she] [npc.verb(remain)] спящим, пока [npc2.name] [npc2.verb(lean)] вперед и [npc2.verb(take)] его в [npc2.her] рот."));
									} else {
										genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
												" Чувствуя, как [npc.her] [npc.cock+] выскальзывает из [npc3.namePos] горла в самый последний момент, [npc.name] послушно [npc.verb(remain)] неподвижно, пока [npc2.name] [npc2.verb(lean)] вперед и [npc2.verb(take)] это в [npc2.her] рот."));
									}
									
								} else {
									genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
											" Не желая, чтобы [npc3.herHim] пришлось принимать на себя основной удар [npc.her] оргазма, [npc.name] [npc.verb(draw)] [npc.her] [npc.cock+] из [npc3.namePos] горла."));
											
									if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
											genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
													" Направляя [npc.cockHead+] прямо к рту [npc2.namePos], [npc.she] [npc.verb(thrust)] вперед и [npc.verb(hilt)] [npc.her] [npc.cock+] в [npc2.namePos] горло, при этом стремительно растущий узел [npc.her] с силой вбивается в [npc2.her] [npc2.lips+]. Он уже так раздулся, что [npc.she] [npc.do] не получается засунуть его в [npc2.her] [npc2.mouth] с первого толчка, но после того как [npc.her] отстраняется и снова с силой [npc.hips] вперед, [npc.she] [npc.verb(succeed)] удается протолкнуть толстый узел сквозь [npc2.her] [npc2.lips].<br/>В тот момент, когда [npc.she] [npc.verb(feel)] он лопается внутри, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], и когда [npc.she] плотно прижимает [npc.her] пах к [npc2.namePos] [npc2.face+], узел [npc.her] полностью расширяется и намертво застревает в [npc.her] [npc.cock+] глубоко в [npc2.namePos] горле."));
											
										} else {
											if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
												genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterTargeted,
														" Направив [npc.cockHead+] прямо к рту [npc2.namePos], [npc.she] [npc.verb(thrust)] вперед, пока стремительно растущий узел [npc.her] не врезается в [npc2.her] [npc2.lips+]. Не желая застрять в [npc2.her] горле, [npc.name] [npc.verb(hold)] назад, не проталкивая толстый узел через [npc2.her] [npc2.lips]."));
												
											} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted, SexAreaPenetration.PENIS)) {
												genericOrgasmSB.append(" Направив [npc.cockHead+] прямо к рту [npc2.namePos], [npc.she] [npc.verb(thrust)] вперед, лишь для того, чтобы почувствовать, что [npc2.her] горло недостаточно глубокое, чтобы вместить всю длину [npc.her] [npc.cock+], из-за чего стремительно набухающий узел [npc.her] не может пробиться в рот [npc2.her].");
												
											} else {
												genericOrgasmSB.append(" Направив [npc.cockHead+] прямо к рту [npc2.namePos], [npc.she] [npc.verb(thrust)] вперед, но так как [npc.she] [npc.do] не хочет причинять [npc2.herHim] дискомфорт, [npc.she] [npc.verb(hold)] назад, не вбивая [npc.her] [npc.cock] до упора в [npc2.her] горло.");
											}
										}
										
									} else {
										if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
											genericOrgasmSB.append(" Направив [npc.cockHead+] прямо к рту [npc2.namePos], [npc.she] [npc.verb(thrust)] вперед, полностью вбивая [npc.her] [npc.cock+] глубоко в [npc2.her] горло. Втирая основание в [npc2.her] [npc2.lips], [npc.name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(feel)] [npc.her] [npc.cock+] начинают дергаться внутри [npc2.herHim].");
										} else {
											genericOrgasmSB.append(" Направляя [npc.cockHead+] прямо к рту [npc2.namePos], [npc.she] [npc.verb(thrust)] вперед, вбивая дергающийся [npc.cock] [npc.her] глубоко в [npc2.namePos] горло.");
										}
									}
								}
								
							} else {
								if(immobile) {
									if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
										genericOrgasmSB.append(" Остальное "+(sleeping?"спящий":"совершенно неподвижный")+", [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] до самого основания глубоко в [npc2.namePos] горло.");
									} else {
										genericOrgasmSB.append(" Остальное "+(sleeping?"спящий":"совершенно неподвижный")+", [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] заталкивая глубоко в [npc2.namePos] горло.");
									}
									
								} else {
									if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)) {
										if(Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)) {
											genericOrgasmSB.append(UtilText.parse(Util.newArrayListOfValues(characterOrgasming, characterTargeted, primary),
													" Издавая [npc.a_moan+], [npc.name] [npc.verb(thrust)] вперед и [npc.verb(hilt)] [npc.her] [npc.cock+] вниз по [npc2.namePos] горлу, при этом стремительно растущий узел [npc.her] с силой вжимается в [npc2.her] [npc2.lips+]. Он уже начал так сильно раздуваться, что [npc.she] [npc.do] не получается загнать его в [npc2.her] [npc2.mouth] с первого толчка, но после того как [npc.her] оттягивается и снова с силой [npc.hips] вперед, [npc.she] [npc.verb(succeed)] удается протолкнуть толстый узел сквозь [npc2.her] [npc2.lips].<br/>В тот момент, когда [npc.she] [npc.verb(feel)] он лопается внутри, [npc.name] [npc.verb(let)] наружу [npc.a_moan+], и когда [npc.she] плотно прижимает [npc.her] пах к [npc2.namePos] [npc2.face+], узел [npc.her] окончательно расширяется и намертво застревает в [npc.her] [npc.cock+] глубоко в [npc2.namePos] горле."));
											
										} else {
											if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
												genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterTargeted,
														" Издавая [npc.a_moan+], [npc.name] [npc.verb(thrust)] вперед, пока стремительно растущий узел [npc.her] не впечатывается в [npc2.her] [npc2.lips+]. Не желая, чтобы [npc2.her] горло заклинило, [npc.name] [npc.verb(hold)] назад, не давая толстому узлу проскочить сквозь [npc2.her] [npc2.lips]."));
												
											} else if(characterOrgasming.isWantingToFullyPenetrate(characterTargeted, SexAreaPenetration.PENIS)) {
												genericOrgasmSB.append(" Издавая [npc.a_moan+], [npc.name], [npc.verb(thrust)] вперед, лишь для того, чтобы почувствовать, что [npc2.her] горло недостаточно глубокое, чтобы вместить всю длину [npc.her] [npc.cock+], из-за чего стремительно раздувающийся узел [npc.her] не может пробиться в [npc2.her] рот.");
												
											} else {
												genericOrgasmSB.append(" Издавая [npc.a_moan+], [npc.name] [npc.verb(thrust)] вперед, но так как [npc.she] [npc.do] не хочет причинять [npc2.herHim] дискомфорт, [npc.she] [npc.verb(hold)] назад, не давая [npc.her] [npc.cock] полностью войти в [npc2.her] горло.");
											}
										}
											
									} else {
										if(!Main.game.isPenetrationLimitationsEnabled() || characterOrgasming.isFullPenetrationTooShort(SexAreaPenetration.PENIS, characterTargeted, (SexAreaOrifice)contactingArea)) {
											genericOrgasmSB.append(" Полностью вогнав [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(let)] [npc.a_moan+], когда [npc.she] [npc.verb(feel)], так как он начинает дергаться внутри [npc2.herHim].");
										} else {
											genericOrgasmSB.append(" Впечатывая [npc.her] [npc.cock+] глубоко в [npc2.namePos] горло, [npc.name] [npc.verb(let)] [npc.a_moan+], когда [npc.she] [npc.verb(feel)], так как он начинает дергаться внутри [npc2.herHim].");
										}
									}
								}
							}
							
							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" [npc.Name] [npc.verb(continue)], совершая короткие толчки, скребя [npc.her] шипами по слизистой [npc2.namePos] горла"
													+ (immobileTarget?".":" и заставляя [npc2.herHim] издать удушливый [npc2.moan]."));
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer()) {
												modifiers.add(" Вы чувствуете, как широкая, расширенная головка вашего [npc.cock] раздувается, плотно прилегая к стенкам и запечатывая вашу [npc.cum] глубоко в [npc2.her] горле.");
											} else {
												modifiers.add(" Широкая, расширенная головка [npc.namePos] [npc.cock] раздувается, плотно прилегая и запечатывая [npc.her] [npc.cum] глубоко в [npc2.namePos] горле.");
											}
										}
										break;
									case RIBBED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer()) {
												modifiers.add(" Вы чувствуете, как ваш ребристый [npc.cock] упирается в стенки [npc2.her] горла"
														+ (immobileTarget?", но [npc2.she] никак на это не реагирует.":", из-за чего [npc2.herHim] издает приглушенный [npc2.moan]."));
											} else {
												modifiers.add(" Ребристое тело [npc.namePos] [npc.cock] упирается в стенки [npc2.namePos] горла"
														+ (immobileTarget?", но [npc2.she] [npc2.do] никак на это не реагирует.":", из-за чего [npc2.herHim] издает приглушенный [npc2.moan]."));
											}
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.isPlayer()) {
												modifiers.add(" Вы чувствуете, как извивающиеся щупальца внутри вашего [npc.cock] начинают массировать стенки [npc2.her] горла"
														+ (immobileTarget?", но [npc2.she] [npc2.do] никак на это не реагирует.":", из-за чего [npc2.herHim] издает приглушенный [npc2.moan]."));
											} else {
												modifiers.add(" Извивающиеся щупальца, покрывающие [npc.namePos] [npc.cock], начинают массировать слизистую [npc2.namePos] горла"
														+ (immobileTarget?", но [npc2.she] [npc2.do] никак на это не реагирует.":", из-за чего [npc2.herHim] издает приглушенный [npc2.moan]."));
											}
										}
										break;
									case BLUNT:
									case KNOTTED:
									case PREHENSILE:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
							
						case THIGHS:
							if(immobile) {
								if(sleeping) {
									genericOrgasmSB.append(" Все еще спящая, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] зажат между бедер [npc2.namePos].");
								} else {
									genericOrgasmSB.append(" Оставаясь совершенно неподвижной, [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+] зажат между бедер [npc2.namePos].");
								}
							} else {
								genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] вгоняет [npc.her] [npc.cock+] между бедер [npc2.namePos], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
							}

							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Движения [npc.Her] заставляют шипы по бокам [npc.her] [npc.cock] скрести по [npc2.namePos] [npc2.legs+]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Раздутая головка [npc.Her] набухает, заставляя [npc2.namePos] [npc2.legs+] раскрываться еще шире"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Жирное уплотнение [npc.Her] набухает, и с каждым толчком оно дико бьется о [npc2.namePos] [npc2.legs+]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Используя способность хватательного члена [npc.her], [npc.name] обвивает его вокруг [npc2.namePos] [npc2.legs+] при каждом толчке"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Ребристый ствол [npc.Her] при каждом толчке раз за разом бьется о [npc2.namePos] [npc2.legs+]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют [npc2.namePos] [npc2.legs+]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
					}
					
				} else {
					switch((SexAreaPenetration)contactingArea) {
						case CLIT:
							break;
						case FINGER:
							if(characterOrgasming.equals(characterTargeted)) {
								if(immobile) {
									if(sleeping) {
										genericOrgasmSB.append(" [npc.She] [npc.verb(remain)] во сне, сжимая [npc.her] [npc.cock+] в [npc.her] [npc.hand].");
									} else {
										genericOrgasmSB.append(" [npc.She] [npc.verb(remain)] совершенно неподвижно, сжимая [npc.her] [npc.cock+] в [npc.her] [npc.hand], с абсолютным совершенством исполняя роль неодушевленной секс-куклы.");
									}
								} else {
									genericOrgasmSB.append(" [npc.She] [npc.verb(continue)] качает [npc.her] [npc.cock+] с помощью [npc.her] [npc.hand], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
								}
							} else {
								genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] вгоняет [npc.her] [npc.cock+] в [npc2.namePos] [npc2.hand], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");
							}

							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add(" Движения [npc.Her] заставляют шипы вдоль боков [npc.her] [npc.cock] скрести по [npc.her] [npc.fingers+], заставляя [npc.herHim] издавать [npc.a_moan+].");
											} else {
												modifiers.add(" Движения [npc.Her] заставляют шипы вдоль боков [npc.her] [npc.cock] скрести по [npc2.namePos] [npc2.fingers+]"+penisModPostfix);
											}
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add(" Раздутая головка [npc.Her] набухает, заставляя [npc.her] [npc.fingers+] раздвигаться еще шире"
														+(immobile
															?" вокруг [npc.her] [npc.cock+]."
															:", что, в свою очередь, заставляет [npc.herHim] издавать [npc.a_moan+]."));
											} else {
												modifiers.add(" Раздутая головка [npc.Her] набухает, заставляя [npc2.namePos] [npc2.fingers+] расходиться еще шире"+penisModPostfix);
											}
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add(" Жирный узел [npc.Her] раздувается, и с каждым толчком он неистово бьется о [npc.her] [npc.fingers+], отчего [npc.herHim] издает [npc.a_moan+].");
											} else {
												modifiers.add(" Жирный узел [npc.Her] раздувается, и с каждым толчком он неистово бьется о [npc2.namePos] [npc2.fingers+]"+penisModPostfix);
											}
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add(" Используя способность хватательного члена [npc.her], [npc.name] [npc.verb(bend)] его вокруг [npc.her] [npc.fingers+] при каждом толчке, что заставляет [npc.herHim] издавать [npc.a_moan+].");
											} else {
												modifiers.add(" Используя способность хватательного члена [npc.her], [npc.name] [npc.verb(bend)] его вокруг [npc2.namePos] [npc2.fingers+] при каждом толчке"+penisModPostfix);
											}
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add(" Ребристый ствол [npc.Her] при каждом толчке раз за разом бьется о [npc.her] [npc.fingers+], отчего [npc.herHim] издает [npc.a_moan+].");
											} else {
												modifiers.add(" Ребристый ствол [npc.Her] при каждом толчке раз за разом бьется о [npc2.namePos] [npc2.fingers+]"+penisModPostfix);
											}
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											if(characterOrgasming.equals(characterTargeted)) {
												modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют [npc.her] [npc.fingers+]"+(immobile?".":", что, в свою очередь, заставляет [npc.herHim] издавать [npc.a_moan+]."));
											} else {
												modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют [npc2.namePos] [npc2.fingers+] при каждом толчке"+penisModPostfix);
											}
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
						case PENIS:
							genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)], растирая [npc.her] [npc.cock+] о [npc2.namePos] [npc2.cock+], издает [npc.a_moan+], когда [npc.she] [npc.verb(feel)] начинает дергаться.");

							modifiers.clear();
							for(PenetrationModifier mod : PenetrationModifier.getPenetrationModifiers()) {
								switch(mod) {
									case BARBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Движения [npc.Her] заставляют шипы по бокам [npc.her] [npc.cock] скрести по стволу [npc2.namePos]"+penisModPostfix);
										}
										break;
									case FLARED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Раздутая головка [npc.Her] набухает и трется о ствол [npc2.namePos]"+penisModPostfix);
										}
										break;
									case KNOTTED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Жирный узел [npc.Her] раздувается и с каждым толчком неистово бьется о ствол [npc2.namePos]"+penisModPostfix);
										}
										break;
									case PREHENSILE:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Используя способность хватательного члена [npc.her], [npc.name] [npc.verb(bend)] его вокруг ствола [npc2.namePos]"+penisModPostfix);
										}
										break;
									case RIBBED:
										if(!immobile && characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Ребристый ствол [npc.Her] при каждом толчке раз за разом бьется о ствол [npc2.namePos]"+penisModPostfix);
										}
										break;
									case TENTACLED:
										if(characterOrgasming.hasPenisModifier(mod)) {
											modifiers.add(" Маленькие щупальца, покрывающие ствол [npc.her], извиваются и массируют ствол [npc2.namePos]"+penisModPostfix);
										}
										break;
									case BLUNT:
									case SHEATHED:
									case TAPERED:
									case VEINY:
									case OVIPOSITOR:
										break;
								}
							}
							if(!modifiers.isEmpty()) {
								genericOrgasmSB.append(modifiers.get(Util.random.nextInt(modifiers.size())));
							}
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case FOOT://TODO modifiers
							if(immobile) {
								if(Main.sex.isDoubleFootJob(characterTargeted)) {
									genericOrgasmSB.append(" Остальное "+(sleeping?"спящий":"совершенно неподвижный")+", [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+], расположенный между [npc2.namePos] [npc2.feet+].");
								} else {
									genericOrgasmSB.append(" Остальное "+(sleeping?"спящий":"совершенно неподвижный")+", [npc.name] [npc.verb(keep)] [npc.her] [npc.cock+], прижатый к [npc2.namePos] [npc2.foot+].");
								}
							} else {
								if(Main.sex.isDoubleFootJob(characterTargeted)) {
									genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] толкая [npc.her] [npc.cock+] между [npc2.namePos] [npc2.feet+], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] он начинает дергаться.");
								} else {
									genericOrgasmSB.append(" [npc.Name] [npc.verb(continue)] потирая [npc.her] [npc.cock+] о [npc2.namePos] [npc2.foot+], издавая [npc.a_moan+], когда [npc.she] [npc.verb(feel)] он начинает дергаться.");
								}
							}
							break;
						case TONGUE:
							break;
					}
				}
			}
		}
		
		genericOrgasmSB.append(" Когда [npc.her] и [npc.balls+] напрягаются, ");
		genericOrgasmSB.append(getCumQuantityDescription(characterOrgasming));
		if(characterOrgasming.getPenisRawOrgasmCumQuantity()>0) {
			genericOrgasmSB.append(cumTargetDescription(sexAction, characterOrgasming, characterTargeted, cumTarget, condomFailure, isSecondaryCreampieTarget));
		}
//		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_PENIS_EGG_LAYING && characterOrgasming.equals(Main.sex.getCharacterLayingEggs())) {
//			genericOrgasmSB.append(eggLayingTargetDescription(SexAreaPenetration.PENIS, characterOrgasming, characterTargeted, condomFailure));
//		}
		
		
		if((cumTarget==OrgasmCumTarget.INSIDE || (cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget))
				&& characterTargeted!=null
				&& contactingArea.isOrifice()
				&& ((SexAreaOrifice)contactingArea).isInternalOrifice()
				&& characterOrgasming.getPenisRawOrgasmCumQuantity()>0
				&& (!characterOrgasming.isWearingCondom() || condomFailure!=CondomFailure.NONE)) {
			boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
			boolean immobileTarget = isCharacterTotallyImmobilised(characterTargeted);
			boolean sleepingTarget = characterTargeted.isAsleep();
			if(contactingArea==SexAreaOrifice.VAGINA
					&& !characterTargeted.isPregnant()
					&& characterTargeted.isAbleToBeImpregnated()
					&& characterOrgasming.isImpregnationPhysicallyPossible()
					&& characterOrgasming.isVirile(Attribute.VIRILITY)
					&& characterTargeted.isImpregnationPhysicallyPossible()
					&& characterTargeted.isFertile()
					&& !immobile) {
				if(immobileTarget) {
					if(sleepingTarget) {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.verb(remain)] во сне, пока [npc.namePos] [npc.cum+] глубоко извергается в чрево [npc2.her]");
					} else {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.verb(remain)] замерла на месте, пока [npc2.she] [npc2.verb(feel)] в [npc.namePos] [npc.cum+], глубоко извергаясь в чрево [npc2.her]");
					}
				} else {
					if(Main.sex.getSexPace(characterTargeted)==SexPace.SUB_RESISTING || characterTargeted.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.verb(sob)], пока [npc2.she] [npc2.verb(feel)] в [npc.namePos] [npc.cum+], глубоко извергаясь в чрево [npc2.her]");
					} else {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.moansVerb+], пока [npc2.she] [npc2.verb(feel)] в [npc.namePos] [npc.cum+], глубоко извергаясь в чрево [npc2.her]");
					}
				}
				if(!characterOrgasming.isMute() && characterOrgasming.getFetishDesire(Fetish.FETISH_IMPREGNATION).isPositive()) {
					if(immobileTarget) {
						genericOrgasmSB.append(", и [npc2.verb(stay)] тихо, как [npc.name] ");
					} else {
						if(Main.sex.getSexPace(characterTargeted)==SexPace.SUB_RESISTING || characterTargeted.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
							genericOrgasmSB.append(", и [npc2.verb(let)] издает страдальческий крик, как [npc.name] ");
						} else {
							genericOrgasmSB.append(", и [npc2.verb(let)] издает похотливый стон, как [npc.name] ");
						}
					}
					if(Main.sex.getSexPace(characterOrgasming)==SexPace.DOM_ROUGH
							&& characterOrgasming.hasFetish(Fetish.FETISH_SADIST)
							&& !Main.sex.isDom(characterTargeted)) {
						genericOrgasmSB.append(UtilText.returnStringAtRandom(
								"[npc.moansVerb], [npc.speechNoEffects(Забеременеешь, [npc2.bitch+]!)]",
								"[npc.verb(tease)], [npc.speechNoEffects(От этого ты точно забеременеешь, [npc2.bitch]...)]",
								"[npc.verb(tease)], [npc.speechNoEffects(Скоро ты будешь носить моих детей, [npc2.bitch]...)]",
								"[npc.verb(tease)], [npc.speechNoEffects(От этого ты точно забеременеешь, [npc2.bitch]...)]",
								"[npc.verb(tease)], [npc.speechNoEffects(От этого ты точно залетаешь, [npc2.bitch]...)]"));
						
					} else {
						genericOrgasmSB.append(UtilText.returnStringAtRandom(
								"[npc.moansVerb], [npc.speechNoEffects(Беременней!)]",
								"[npc.verb(tease)], [npc.speechNoEffects(От этого ты можешь забеременеть...)]",
								"[npc.verb(tease)], [npc.speechNoEffects(Скоро ты будешь носить моих детей...)]",
								"[npc.verb(tease)], [npc.speechNoEffects(От этого ты точно забеременеешь...)]",
								"[npc.verb(tease)], [npc.speechNoEffects(От этого ты точно залетишь...)]"));
					}
				} else {
					genericOrgasmSB.append(".");
				}
				
			} else {
				if(immobileTarget) {
					if(sleepingTarget) {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.verb(remain)] спит, пока [npc.namePos] [npc.cum+] кончает глубоко внутрь [npc2.herHim].");
					} else {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.verb(remain)] замерла на месте, пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cum+] кончает глубоко внутрь [npc2.herHim].");
					}
				} else {
					if(Main.sex.getSexPace(characterTargeted)==SexPace.SUB_RESISTING) {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.verb(sob)], пока [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cum+] кончает глубоко внутрь [npc2.herHim].");
					} else {
						genericOrgasmSB.append("<br/>[npc2.Name] [npc2.moansVerb+] как [npc2.she] [npc2.verb(feel)] [npc.namePos] [npc.cum+], выстреливая глубоко внутрь [npc2.herHim].");
					}
				}
			}
		}
		
		if(characterOrgasming.hasPenisModifier(PenetrationModifier.KNOTTED)
				&& (cumTarget==OrgasmCumTarget.INSIDE || (cumTarget==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE && isSecondaryCreampieTarget))
				&& Objects.equals(Main.sex.getCharacterKnotting(characterOrgasming), characterTargeted)
				&& contactingArea.isOrifice()
				&& ((SexAreaOrifice)contactingArea).isInternalOrifice()) {
			genericOrgasmSB.append("<br/>Даже после того, как [npc.namePos] [npc.balls+] выкачали всю свою порцию в [npc2.name], [npc.her] узел остается раздутым, намертво сцепляя#IF(npc2.isPlayer() || npc.isPlayer()) вас двоих.#ELSE [npc.herHim] и [npc.her] в единое целое.#ENDIF Проходит несколько минут, прежде чем он начинает спадать, и с влажным хлопком [npc.sheIs] наконец удается высвободить [npc.her] [npc.cock+].");
		}
		
		if(characterTargeted!=null) {
			return UtilText.parse(characterOrgasming, characterTargeted, genericOrgasmSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, genericOrgasmSB.toString());
		}
	}
	
	private static String getCumQuantityDescription(GameCharacter characterOrgasming) {
		String targetName = "your";
		if(!characterOrgasming.isPlayer()) {
			targetName = "[npc.namePos]";
		}
		String cumQuantityDescription = targetName+" [npc.cum+] брызжет";
		
		switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
			case ZERO_NONE:
				cumQuantityDescription = "не выдается ни единой капли [npc.cum]...";
				break;
			case ONE_TRICKLE:
				cumQuantityDescription = "тонкая струйка [npc.cum+] брызжет";
				break;
			case TWO_SMALL_AMOUNT:
				cumQuantityDescription = "небольшое количество [npc.cum+] брызжет";
				break;
			case THREE_AVERAGE:
				cumQuantityDescription = targetName+" [npc.cum+] выплескивается";
				break;
			case FOUR_LARGE:
				cumQuantityDescription = targetName+" [npc.cum+] вылетает струей";
				break;
			case FIVE_HUGE:
				cumQuantityDescription = targetName+" [npc.cum+] вылетает струей";
				break;
			case SIX_EXTREME:
				cumQuantityDescription = targetName+" [npc.cum+] брызжет наружу";
				break;
			case SEVEN_MONSTROUS:
				cumQuantityDescription = targetName+" [npc.cum+] брызжет наружу";
				break;
		}
		return UtilText.parse(characterOrgasming, cumQuantityDescription);
	}
	
	private static String cumTargetDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, GameCharacter target, OrgasmCumTarget targetArea, CondomFailure condomFailure, boolean isSecondaryCreampieTarget) {
		StringBuilder cumTargetSB = new StringBuilder();
		
		if(!isSecondaryCreampieTarget) {
			if(characterOrgasming.isWearingCondom()) {
				cumTargetSB.append(UtilText.parse(characterOrgasming, " в презерватив, который [npc.sheIs] носит."));
				
				switch(condomFailure) {
					case CUM_OVERLOAD:
						cumTargetSB.append(UtilText.parse(characterOrgasming,
								" Оргазм [npc.Her] оказывается слишком мощным для резиновой оболочки, и после того как она раздувается и наполняется огромным количеством [npc.cum+] [npc.her], она внезапно лопается, извергая [npc.her] сперму"));
						break;
					case MINERAL_OIL_CUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Хотя обычно резиновая оболочка достаточно прочна, чтобы удерживать [npc.her] сперму, её долговечность была серьезно подорвана [npc2.namePos] пропитанной минеральным маслом спермой, и после того как она раздувается и наполняется [npc.namePos] [npc.cum+], она внезапно лопается, извергая [npc.her] семя"));
						break;
					case MINERAL_OIL_GIRLCUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Хотя обычно резиновая оболочка достаточно прочна, чтобы удерживать [npc.her] сперму, её долговечность была серьезно подорвана [npc2.namePos] пропитанной минеральным маслом женской спермой, и после того как она раздувается и наполняется [npc.namePos] [npc.cum+], она внезапно лопается, извергая [npc.her] семя"));
						break;
					case MINERAL_OIL_MILK:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Хотя обычно резиновая оболочка достаточно прочна, чтобы удерживать [npc.her] сперму, её долговечность была серьезно подорвана [npc2.namePos] пропитанным минеральным маслом молоком, и после того как она раздувается и наполняется [npc.namePos] [npc.cum+], она внезапно лопается, извергая [npc.her] семя"));
						break;
					case MINERAL_OIL_SALIVA:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Хотя обычно резиновая оболочка достаточно прочна, чтобы удерживать [npc.her] сперму, её долговечность была серьезно подорвана [npc2.namePos] пропитанной минеральным маслом слюной, и после того как она раздувается и наполняется [npc.namePos] [npc.cum+], она внезапно лопается, извергая [npc.her] семя"));
						break;
					case MINERAL_OIL_SELF_CUM:
						cumTargetSB.append(UtilText.parse(characterOrgasming, target,
								" Хотя обычно прочности резинового презерватива хватает, чтобы удержать объем спермы, который производит [npc.name], насыщенная [npc.her] минеральным маслом сперма мгновенно разъедает его, и он внезапно лопается, извергая [npc.her] семя"));
						break;
					case SABOTAGED:
						cumTargetSB.append(UtilText.parse(characterOrgasming,
								" Из-за тайного саботажа прочность резинового презерватива оказывается серьезно подорвана, и когда он раздувается, наполняясь [npc.cum+] [npc.namePos], он внезапно лопается, извергая [npc.her] семя"));
						break;
					case NONE:
					case EGG_LAYING:
						return cumTargetSB.toString();
				}
				
			}
		}
		
		if(!characterOrgasming.isCoverableAreaExposed(CoverableArea.PENIS)) {
			if(characterOrgasming.isPlayer()) {
				return "  прямо на твой [npc.lowClothing(PENIS)].";
			} else {
				return UtilText.parse(characterOrgasming, "  прямо на [npc.lowClothing(PENIS)] [npc.her].");
			}
		}

		
		List<CoverableArea> areasCummedOn = new ArrayList<>();
		if(target!=null) {
			areasCummedOn = sexAction.getAreasCummedOn(characterOrgasming, target);
		}
		
		List<AbstractClothing> targetAreaClothingCummedOn = new ArrayList<>();
		if(areasCummedOn!=null) {
			for(CoverableArea ca : areasCummedOn) {
				if(!target.isCoverableAreaExposed(ca)) {
					if(targetArea.isRequiresPartner()) {
						targetAreaClothingCummedOn.addAll(getClothingCummedOn(target, ca));
					} else {
						targetAreaClothingCummedOn.addAll(getClothingCummedOn(characterOrgasming, ca));
					}
				}
			}
			targetAreaClothingCummedOn = new ArrayList<>(new HashSet<>(targetAreaClothingCummedOn)); // Remove duplicates
		}
		
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		boolean sleeping = characterOrgasming.isAsleep();
		
		switch(targetArea) {
			case ARMPITS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всей [npc2.namePos] [npc2.armpit+].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разбрызгивается по [npc2.namePos] руке,");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разбрызгивается по [npc2.namePos] руке,");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разбрызгивается по [npc2.namePos] руке,");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" которая [npc2.verb(remain)] глубоко спит, пока оно стекает по [npc2.her] [npc2.armSkin+].");
						} else {
							sb.append(" которая [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] стекает по [npc2.her] [npc2.armSkin+].");
						}
					} else {
						sb.append(" которая не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] стекает по [npc2.her] [npc2.armSkin+].");
					}
					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case ASS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc2.namePos] [npc2.ass+].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоким сном, пока [npc.her] [npc.cum+] разлетается по [npc2.namePos] голой заднице,");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] безмолвно и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.namePos] голой заднице,");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.namePos] голой заднице,");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" которая [npc2.verb(remain)] глубоким сном, пока оно");
						} else {
							sb.append(" которая [npc2.do] не реагирует никак, пока [npc2.she] [npc2.verb(feel)] его");
						}
					} else {
						sb.append(" которая не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] его");
					}
					if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
						sb.append(" стекает по [npc2.her] [npc2.assSkin+].");
					} else if(target.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
						sb.append(" стекает по [npc2.her] [npc2.assSkin+] и задней клоаке.");
					} else {
						sb.append(" стекает по [npc2.her] [npc2.asshole+].");
					}
					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case BACK:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всей спине [npc2.namePos].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" который(ая) [npc2.verb(remain)] глубоко спит, пока это стекает по [npc2.her] [npc2.skin+].");
						} else {
							sb.append(" который(ая) [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] это стекает по [npc2.her] [npc2.skin+].");
						}
					} else {
						sb.append(" который(ая) не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] это стекает по [npc2.her] [npc2.skin+].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case BREASTS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всей [npc2.namePos] [npc2.breasts].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" кто [npc2.verb(remain)] крепко спит, пока оно стекает по [npc2.her] [npc2.breastsSkin+].");
						} else {
							sb.append(" кто [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.breastsSkin+].");
						}
					} else {
						sb.append(" кто не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.breastsSkin+].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case FACE:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc2.namePos] [npc2.face+].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" кто [npc2.verb(remain)] крепко спит, пока оно стекает по [npc2.her] [npc2.faceSkin+].");
						} else {
							sb.append(" кто [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.faceSkin+].");
						}
					} else {
						sb.append(" кто не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.faceSkin+].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case FLOOR:
				return " по всему полу.";
			case STOMACH:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc2.namePos] животу.");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" который(ая) [npc2.verb(remain)] глубоко спит, пока это стекает по [npc2.her] [npc2.skin+].");
						} else {
							sb.append(" который(ая) [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] это стекает по [npc2.her] [npc2.skin+].");
						}
					} else {
						sb.append(" который(ая) не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] это стекает по [npc2.her] [npc2.skin+].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case GROIN:
			case INSIDE:
			case INSIDE_SWITCH_DOUBLE:
				// Use this GROIN section only if the INSIDE or INSIDE_SWITCH_DOUBLE is a frotting event
				boolean isFrotting = false;
				if(targetArea==OrgasmCumTarget.INSIDE || targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
					if(!Main.sex.getOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS)) {
						break;
					}
					isFrotting = true;
				}
				if(!isFrotting) { // If it's not frotting, set the target to the GROIN target, otherwise, keep it as-is as it will already be accounting for ongoing PENIS actions to make sure the target is the one being cummed inside of
					target = Main.sex.getTargetedPartner(characterOrgasming);
				}
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					String groinText = "паху.";
					if(target.hasPenisIgnoreDildo()) {
						if(target.hasVagina()) {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA || (isFrotting && target.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND)) {
								groinText = " [npc2.cock], [npc2.pussy] и [npc2.asshole].";
							} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
								groinText = " [npc2.cock] и [npc2.pussy].";
							}
						} else {
							if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								groinText = " [npc2.cock] и [npc2.asshole].";
							} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
								groinText = " [npc2.cock+].";
							}
						}
						
					} else if(target.hasVagina()) {
						if(target.getGenitalArrangement()==GenitalArrangement.CLOACA) {
							groinText = " [npc2.pussy] и [npc2.asshole].";
						} else if(target.getGenitalArrangement()==GenitalArrangement.NORMAL) {
							groinText = " [npc2.pussy+].";
						}
						
					} else {
						groinText = " бесполое тело.";
					}

					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc2.namePos] "+groinText);
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" который(ая) [npc2.verb(remain)] глубоко спит, пока оно стекает по паху [npc2.her].");
						} else {
							sb.append(" кто [npc2.do] даже не реагирует, когда [npc2.she] [npc2.verb(feel)] стекает по [npc2.her] паху.");
						}
					} else {
						sb.append(" который(ая) не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] его/ее по паху [npc2.her].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
				
			case HAIR:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					List<String> areas = new ArrayList<>();
					areas.add("head");
					if(target.hasHair()) {
						areas.add("[npc2.hair(true)]");
					}
					if(target.hasHorns()) {
						areas.add("[npc2.horns]");
					}

					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc2.namePos] "+Util.stringsToStringList(areas, false)+".");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" кто [npc2.verb(remain)] крепко спит, пока оно стекает по лицу [npc2.her].");
						} else {
							sb.append(" кто [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] оно стекает по лицу [npc2.her].");
						}
					} else {
						sb.append(" кто не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] оно стекает по лицу [npc2.her].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case LEGS:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc2.namePos] [npc2.legs].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" кто [npc2.verb(remain)] крепко спит, пока оно стекает по [npc2.her] [npc2.legsSkin+].");
						} else {
							sb.append(" кто [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.legsSkin+].");
						}
					} else {
						sb.append(" кто не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.legsSkin+].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case FEET:
				target = Main.sex.getTargetedPartner(characterOrgasming);
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc2.namePos] [npc2.feet+].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], пока [npc.her] [npc.cum+] разлетается по [npc2.name],");
					}
					if(isCharacterTotallyImmobilised(target)) {
						if(target.isAsleep()) {
							sb.append(" кто [npc2.verb(remain)] крепко спит, пока оно стекает по [npc2.her] [npc2.toes+].");
						} else {
							sb.append(" кто [npc2.do] никак не реагирует, пока [npc2.she] [npc2.verb(feel)] оно стекает по [npc2.her] [npc2.toes+].");
						}
					} else {
						sb.append(" которая не может не издать [npc2.a_moan], пока [npc2.she] [npc2.verb(feel)] стекает по [npc2.her] [npc2.toes+].");
					}

					return UtilText.parse(characterOrgasming, target, sb.toString());
				}
			case WALL:
				return " по всему [pc.wall].";
				
			case SELF_GROIN:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
						
				} else {
					String groinText = "паху.";
					if(characterOrgasming.hasPenisIgnoreDildo()) {
						if(characterOrgasming.hasVagina()) {
							groinText = " [npc.cock] и [npc.pussy].";
						} else {
							groinText = " [npc.cock+].";
						}
					} else if(characterOrgasming.hasVagina()) {
						groinText = " [npc.pussy+].";
					} else {
						groinText = " бесполое тело.";
					}

					StringBuilder sb = new StringBuilder();
					sb.append(" по всему [npc.her] "+groinText);
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] крепко спит, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и даже не подает признаков пробуждения, когда оно начинает стекать по [npc.her] [npc.skin].");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и никак не реагирует, когда оно начинает стекать по [npc.her] [npc.skin].");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], когда [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и [npc.she] не может не издать [npc.a_moan], пока [npc.she] [npc.verb(feel)] то, как оно стекает по [npc.her] [npc.skin].");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
				
			case SELF_STOMACH:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" по всему животу [npc.her].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] крепко спит, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и даже не подает признаков пробуждения, когда оно начинает стекать по [npc.her] [npc.skin].");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и никак не реагирует, когда оно начинает стекать по [npc.her] [npc.skin].");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], когда [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и [npc.she] не может не издать [npc.a_moan], пока [npc.she] [npc.verb(feel)] то, как оно стекает по [npc.her] [npc.skin].");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
				
			case SELF_LEGS:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" повсюду на [npc.her] [npc.legs].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] крепко спит, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и даже не подает признаков пробуждения, когда оно начинает стекать по [npc.her] [npc.skin].");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и никак не реагирует, когда оно начинает стекать по [npc.her] [npc.skin].");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], когда [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и [npc.she] не может не издать [npc.a_moan], пока [npc.she] [npc.verb(feel)] то, как оно стекает по [npc.her] [npc.skin].");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}

			case SELF_FEET:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" повсюду на [npc.her] [npc.feet+].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] крепко спит, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и даже не подает признаков пробуждения, когда оно начинает стекать по [npc.her] [npc.toes+].");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и совершенно не реагирует, когда оно начинает стекать по [npc.her] [npc.toes+].");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], когда [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и [npc.she] не может сдержать [npc.a_moan], пока [npc.she] [npc.verb(feel)] то, как оно стекает по [npc.her] [npc.toes+].");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
				
			case SELF_BREASTS:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" повсюду на [npc.her] [npc.breasts].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] крепко спит, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и даже не подает признаков пробуждения, когда оно начинает стекать по [npc.her] [npc.breastsSkin+].");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и совершенно не реагирует, когда оно начинает стекать по [npc.her] [npc.breastsSkin+].");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], когда [npc.her] [npc.cum+] разбрызгивается на [npc.herHim], и [npc.she] не может сдержать [npc.a_moan], пока [npc.she] [npc.verb(feel)] то, как оно стекает по [npc.her] [npc.breastsSkin+].");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}
			case SELF_FACE:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
					
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(" повсюду на [npc.her] [npc.face+].");
					if(immobile) {
						if(sleeping) {
							sb.append(" [npc.Name] [npc.verb(remain)] крепко спит, пока [npc.her] [npc.cum+] разлетается брызгами по [npc.herHim], и даже не думает просыпаться, когда всё это начинает стекать по [npc.her] [npc.faceSkin+].");
						} else {
							sb.append(" [npc.Name] [npc.verb(remain)] тихо и неподвижно, пока [npc.her] [npc.cum+] разлетается брызгами по [npc.herHim], и совершенно не реагирует, когда всё это начинает стекать по [npc.her] [npc.faceSkin+].");
						}
					} else {
						sb.append(" [npc.Name] [npc.verb(grin)], когда [npc.her] [npc.cum+] разлетается брызгами по [npc.herHim], и [npc.she] не может сдержать [npc.a_moan], пока [npc.she] [npc.verb(feel)] то, как оно стекает по [npc.her] [npc.faceSkin+].");
					}
					return UtilText.parse(characterOrgasming, sb.toString());
				}

			case SELF_HANDS:
				if (!targetAreaClothingCummedOn.isEmpty()) {
					return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
				} else {
					return UtilText.parse(characterOrgasming,
							" по всему [npc.namePos] [npc.hands].");
				}
				
			case LILAYA_PANTIES:
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return UtilText.parse(characterOrgasming,
						" прямо в трусики Lilaya. Ты невольно издаешь [pc.a_moan+], наблюдая, как твоя [pc.cum+] скапливается в мягкой ткани, и делаешь несколько лишних движений своим [pc.cock+], представляя, как твоя демоническая [lilaya.relation(pc)] краснеет, натягивая пропитанные спермой трусики на свою горячую киску.");
		}
		
		// Continued description for cumming inside:
		
		List<SexAreaInterface> areasConteacted = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS);
		if(!areasConteacted.isEmpty()) { // Do an empty check as when using a condom, ongoing actions are stopped (Sex.java#2721), then after that the sex action override is checked, which triggers this method again
			SexAreaInterface areaContacted = areasConteacted.get(0);
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append(" по всему [npc2.namePos] [npc2.armpit+] и [npc2.arm+(true)].");
		
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc2.name] [npc2.verb(realise)], что [npc.nameIs] даже близко не собирается останавливаться, и еще мгновение спустя [npc2.her] [npc2.arm+(true)] оказываются абсолютно пропитаны [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
						
					case ANUS:
						cumTargetSB.append(" глубоко в [npc2.namePos] [npc2.asshole+].");
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] даже близко не собирается останавливаться,");
								if(isCharacterTotallyImmobilised(target)) {
									if(target.isAsleep()) {
										cumTargetSB.append(" и даже когда [npc.her] [npc.cum+] откидывается назад и у [npc2.namePos] [npc2.asshole] начинает течь слюна, [npc2.name] [npc2.verb(show)] без признаков пробуждения.");
									} else {
										cumTargetSB.append(" и даже когда [npc.her] [npc.cum+] откидывается назад и у [npc2.namePos] [npc2.asshole] начинает течь слюна, [npc2.name] [npc2.verb(hold)] неподвижен и [npc2.verb(remain)] совершенно безмолвен.");
									}
								} else {
									cumTargetSB.append(" и когда [npc.her] [npc.cum+] откидывается назад и у [npc2.namePos] [npc2.asshole] начинает течь слюна, [npc2.she] [npc2.verb(let)] из [npc2.a_moan+].");
								}
								
								cumTargetSB.append(((targetArea!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) && !immobile
														?" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] глубоко вошел в задницу [npc2.her], [npc.moaning+], пока [npc.she] [npc.verb(wait)] для [npc.her] [npc.balls] не иссякнет."
														:""));
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS)
									+ (targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE
										?characterOrgasming.getPenisRawOrgasmCumQuantity()/2
										:characterOrgasming.getPenisRawOrgasmCumQuantity());
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case ASS:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append(" по всей спине [npc2.namePos] и [npc2.ass+].");
	
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] и близко не собирается останавливаться, и еще мгновение спустя [npc2.namePos] [npc2.ass+] оказывается полностью пропитан в [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
						
					case BREAST:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							if(target.hasBreasts()) {
								cumTargetSB.append(" по всему [npc2.namePos] [npc2.breasts+] и лицу.");
							} else {
								cumTargetSB.append(" по всей плоской груди и лицу [npc2.namePos].");
							}
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] и близко не собирается останавливаться, и еще мгновение спустя торс [npc2.namePos] оказывается полностью пропитан в [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
						
					case BREAST_CROTCH:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append(" по всему [npc2.namePos] [npc2.crotchBoobs+] и паху.");
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] даже не думает останавливаться, и еще мгновение спустя пах и живот [npc2.namePos] оказываются полностью залиты [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
						
					case MOUTH:
						if(target.isPlayer()) {
							cumTargetSB.append(" глубоко в твоем горле, и ты ");
							if(isCharacterTotallyImmobilised(target)) {
								if(target.isAsleep()) {
									cumTargetSB.append("продолжаешь крепко спать, пока [npc.cum+] стекает в твой желудок.");
								} else {
									cumTargetSB.append("лежишь неподвижно и тихо, чувствуя, как [npc.cum+] стекает в твой желудок.");
								}
							} else {
								cumTargetSB.append("заходишься приглушенным скулением, чувствуя, как [npc.cum+] стекает в твой желудок.");
							}
							switch(characterOrgasming.getCumFlavour()) {
								case BEER:
									cumTargetSB.append(" Необычный вкус [npc.namePos] со вкусом пива");
									break;
								case CHOCOLATE:
									cumTargetSB.append(" Сладкий вкус [npc.namePos] со вкусом шоколада");
									break;
								case CUM:
									cumTargetSB.append(" Соленый вкус");
									break;
								case GIRL_CUM:
									cumTargetSB.append(" Необычный сладкий вкус [npc.namePos]");
									break;
								case HONEY:
									cumTargetSB.append(" Сладкий вкус [npc.namePos] со вкусом меда");
									break;
								case MILK:
									cumTargetSB.append(" Необычный вкус [npc.namePos] со вкусом молока");
									break;
								case MINT:
									cumTargetSB.append(" Вкус [npc.namePos] со вкусом мяты");
									break;
								case PINEAPPLE:
									cumTargetSB.append(" Сладкий вкус [npc.namePos] со вкусом ананаса");
									break;
								case BUBBLEGUM:
									cumTargetSB.append(" Фруктовый вкус [npc.namePos] со вкусом баблгама");
									break;
								case STRAWBERRY:
									cumTargetSB.append(" Сладкий вкус [npc.namePos] со вкусом клубники");
									break;
								case VANILLA:
									cumTargetSB.append(" Вкус [npc.namePos] со вкусом ванили");
									break;
								case CHERRY:
									cumTargetSB.append(" Сладкий вкус [npc.namePos] со вкусом вишни");
									break;
								case COFFEE:
									cumTargetSB.append(" Сильный, горький вкус [npc.namePos] со вкусом кофе");
									break;
								case TEA:
									cumTargetSB.append(" Вкус [npc.namePos] со вкусом чая");
									break;
								case MAPLE:
									cumTargetSB.append(" Сладкий вкус [npc.namePos] со вкусом кленового сиропа");
									break;
								case CINNAMON:
									cumTargetSB.append(" Вкус [npc.namePos] корицы");
									break;
								case LEMON:
									cumTargetSB.append(" Кислый вкус [npc.namePos] лимона");
									break;
								case ORANGE:
									cumTargetSB.append(" Цитрусовый вкус [npc.namePos] апельсина");
									break;
								case GRAPE:
									cumTargetSB.append(" Вкус [npc.namePos] винограда");
									break;
								case MELON:
									cumTargetSB.append(" Вкус [npc.namePos] дыни");
									break;
								case COCONUT:
									cumTargetSB.append(" Вкус [npc.namePos] кокоса");
									break;
								case BLUEBERRY:
									cumTargetSB.append(" Вкус [npc.namePos] черники");
									break;
								case BANANA:
									cumTargetSB.append(" Вкус [npc.namePos] банана");
									break;
								case FLAVOURLESS:
									cumTargetSB.append(" [npc.NamePos] сперма совершенно безвкусная, и ты");
									break;
							}
							if(characterOrgasming.getCumFlavour()!=FluidFlavour.FLAVOURLESS) {
								cumTargetSB.append(" сперма поднимается и попадает тебе на [npc2.tongue], и ты");
							}
							
							if(target.hasFetish(Fetish.FETISH_CUM_ADDICT) || Main.sex.getCharactersRequestingCreampie().contains(target) || Main.sex.getCharactersRequestingKnot().contains(target)) {
								cumTargetSB.append(" " + UtilText.returnStringAtRandom("жадно","голодно"));
							} else {
								cumTargetSB.append("у тебя не остается иного выбора, кроме как");
							}
							cumTargetSB.append(" заглатывать как можно больше");
							if(!characterOrgasming.getCumModifiers().isEmpty()) {
								switch(characterOrgasming.getCumModifiers().get(Util.random.nextInt(characterOrgasming.getCumModifiers().size()))) { //TODO specials for ALCOHOLIC & HALLUCINOGENIC
									case ADDICTIVE:
										cumTargetSB.append(" вкусного, вызывающего сильнейшее привыкание [npc.cum], сколько только сможешь.");
										break;
									case ALCOHOLIC:
									case ALCOHOLIC_WEAK:
										cumTargetSB.append(" алкогольного [npc.cum], сколько только сможешь.");
										break;
									case BUBBLING:
										cumTargetSB.append(" шипучего, пузырящегося [npc.cum], сколько только сможешь.");
										break;
									case HALLUCINOGENIC:
										cumTargetSB.append(" психоактивного [npc.cum], сколько только сможешь.");
										break;
									case MUSKY:
										cumTargetSB.append(" мускусного [npc.cum], сколько только сможешь.");
										break;
									case SLIMY:
										cumTargetSB.append(" слизистого [npc.cum], сколько только сможешь.");
										break;
									case STICKY:
										cumTargetSB.append(" максимально липкую [npc.cum].");
										break;
									case VISCOUS:
										cumTargetSB.append(" максимально густую и вязкую [npc.cum].");
										break;
									case MINERAL_OIL:
										cumTargetSB.append(" максимально вкусную [npc.cum].");
										break;
								}
							} else {
								cumTargetSB.append(" [npc.cum], насколько это возможно.");
							}
							
						} else {
							cumTargetSB.append(" глубоко в глотку [npc2.namePos].");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] даже не думает останавливаться, и [npc2.name]");
								if(isCharacterTotallyImmobilised(target)) {
									if(target.isAsleep()) {
										cumTargetSB.append(" [npc2.verb(remain)] спящим");
									} else {
										cumTargetSB.append(" [npc2.verb(remain)] неподвижным и тихим");
									}
								} else {
									cumTargetSB.append(" [npc2.verb(let)] отчаянный, булькающий [npc2.moan]");
								}
								cumTargetSB.append(" пока [npc.namePos] [npc.cum+] отстраняется назад, и из уголков [npc2.her] рта начинает течь слюна.");
								if(!immobile) {
									cumTargetSB.append(" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] глубоко в [npc2.her] горло, [npc.moaning+] пока [npc.she] [npc.verb(wait)], чтобы [npc.her] [npc.balls] иссякли.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case NIPPLE:
						if(target.isPlayer()) {
							cumTargetSB.append(" глубоко в твою [pc.breasts+]"+(isCharacterTotallyImmobilised(target)?".":", и ты обнаруживаешь, что скулишь и стонешь, чувствуя [npc.cum+] глубоко внутри своей [pc.breasts+]."));
						} else {
							cumTargetSB.append(" глубоко в [npc2.namePos] [npc2.breasts+].");
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getBreastInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case NIPPLE_CROTCH:
						cumTargetSB.append(" глубоко в [npc2.namePos] [npc2.crotchBoobs+].");
						
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE_CROTCH) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getBreastCrotchInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case THIGHS:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append(" по всем [npc2.namePos] бедрам.");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] и близко не собирается останавливаться, и еще мгновение спустя [npc2.namePos] [npc2.legs+] насквозь пропитаны [npc.cum+].");
									break;
								default:
							}
						}
						break;
						
					case URETHRA_PENIS: case URETHRA_VAGINA:
						if(target.isPlayer()) {
							cumTargetSB.append(" глубоко в твою уретру.");
						} else {
							cumTargetSB.append(" глубоко в [npc2.namePos] уретру.");
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] и близко не собирается останавливаться, и еще мгновение спустя [npc.her] [npc.cum+] затекает обратно и начинает вытекать из [npc2.namePos] уретры.");
								if(!immobile) {
									cumTargetSB.append(" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] глубоко внутри [npc2.herHim], [npc.moaning+], пока [npc.she] [npc.verb(wait)], чтобы [npc.her] [npc.balls] окончательно не иссяк.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
						
					case VAGINA:
						if(target.isPlayer()) {
							if(!target.isVisiblyPregnant()) {
								cumTargetSB.append(" глубоко в твою ждущую матку"+(isCharacterTotallyImmobilised(target)?".":", и ты ловишь себя на том, что скулишь и стонешь, гадая, не сделает ли тебя беременной [npc.cum+]."));
							} else {
								cumTargetSB.append(" глубоко в твою голодную [pc.pussy]"+(isCharacterTotallyImmobilised(target)?".":", и ты ловишь себя на том, что скулишь и стонешь, чувствуя [npc.cum+] глубоко внутри себя."));
							}
						} else {
							if(!target.isVisiblyPregnant()) {
								cumTargetSB.append(" глубоко в ждущую матку [npc2.namePos].");
							} else {
								cumTargetSB.append(" глубоко в [npc2.namePos] [npc2.pussy+].");
							}
						}
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] даже близко не закончила, и еще мгновение спустя [npc.her] [npc.cum+] отстраняется и начинает пускать слюни из [npc2.namePos] [npc2.pussy+].");
								cumTargetSB.append(((targetArea!=OrgasmCumTarget.INSIDE_SWITCH_DOUBLE || isSecondaryCreampieTarget) && !immobile
											?" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] глубоко внутри [npc2.her], [npc2.pussy], пока [npc.moaning+] [npc.she] [npc.verb(wait)], чтобы [npc.her] [npc.balls] иссяк."
											:""));
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent) && !target.isVisiblyPregnant()) {
							float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA)
									+ (targetArea==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE
										?characterOrgasming.getPenisRawOrgasmCumQuantity()/2
										:characterOrgasming.getPenisRawOrgasmCumQuantity());
							cumTargetSB.append(getInflationText(characterOrgasming, target, cumAmount));
						}
						break;
	
					case SPINNERET:
						cumTargetSB.append(" глубоко в паутинный аппарат [npc2.namePos].");
						switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] и не думает останавливаться, и еще мгновение спустя [npc.her] [npc.cum+] откидывается назад и начинает пускать слюни из своего паучьего отверстия [npc2.namePos].");
								if(!immobile) {
									cumTargetSB.append(" [npc.Name] [npc.verb(keep)] [npc.her] [npc.cock] глубоко внутри [npc2.herHim], [npc.moaning+], пока [npc.she] [npc.verb(wait)], чтобы [npc.her] [npc.balls] окончательно не иссяк.");
								}
								break;
							default:
								break;
						}
						if(Main.getProperties().hasValue(PropertyValue.inflationContent)) {
							float cumAmount = target.getTotalFluidInArea((SexAreaOrifice) areaContacted) + characterOrgasming.getPenisRawOrgasmCumQuantity();
							cumTargetSB.append(getSpinneretInflationText(characterOrgasming, target, cumAmount));
						}
						break;
				}
	
				switch(target.getBodyMaterial()) {
					case AIR:
					case ARCANE:
					case WATER:
					case SLIME:
						cumTargetSB.append("<br/>Так как тело [npc2.namePos] полностью состоит из полупрозрачного "+target.getBodyMaterial().getName()+", вы можете видеть облако [npc.namePos], вырывающееся из [npc.cum+] и рассеивающееся внутри [npc2.herHim].");
						break;
					case FIRE:
					case FLESH:
					case ICE:
					case RUBBER:
					case STONE:
					case SILICONE:
						break;
				}
				
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						if(characterOrgasming.equals(target)) {
							if (!targetAreaClothingCummedOn.isEmpty()) {
								return getClothingCummedOnText(characterOrgasming, areasCummedOn, targetAreaClothingCummedOn);
							} else {
								cumTargetSB.append(" по всему [npc.her] [npc.fingers+].");
								
								switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
									case SIX_EXTREME:
									case SEVEN_MONSTROUS:
										cumTargetSB.append(" Спустя несколько секунд [npc.her] [npc.hands+] становятся совершенно промокшими от [npc.cum+].");
										break;
									default:
										break;
								}
							}
						} else if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append(" по всему [npc2.namePos] [npc2.fingers+].");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] и не думает останавливаться, и еще мгновение спустя [npc2.namePos] [npc2.hands+] становятся совершенно промокшими от [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						if (!targetAreaClothingCummedOn.isEmpty()) {
							return getClothingCummedOnText(characterOrgasming, target, areasCummedOn, targetAreaClothingCummedOn);
							
						} else {
							cumTargetSB.append(" по всему [npc2.namePos] [npc2.toes+].");
							
							switch (characterOrgasming.getPenisOrgasmCumQuantity()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									cumTargetSB.append(" Спустя несколько секунд становится ясно, что [npc.nameIs] и не думает останавливаться, и еще мгновение спустя [npc2.namePos] [npc2.feet+] становятся совершенно промокшими от [npc.cum+].");
									break;
								default:
									break;
							}
						}
						break;
					case TONGUE:
						break;
				}
			}
		}
		
		if(target!=null) {
			return UtilText.parse(characterOrgasming, target, cumTargetSB.toString());
			
		} else {
			return UtilText.parse(characterOrgasming, cumTargetSB.toString());
		}
	}
	
	
	private static List<AbstractClothing> getClothingCummedOn(GameCharacter target, CoverableArea area) {
		List<AbstractClothing> clothingList = new ArrayList<>();
		for(InventorySlot slot : area.getAssociatedInventorySlots(target)) {
			clothingList.addAll(target.getVisibleClothingConcealingSlot(slot));
		}
		return new ArrayList<>(new HashSet<>(clothingList));
	}

	private static List<InventorySlot> getNakedAreasCummedOn(GameCharacter target, CoverableArea area) {
		Set<InventorySlot> areaList = new HashSet<>();
		List<InventorySlot> slotsTargeted = new ArrayList<>(area.getAssociatedInventorySlots(target));
		
		// Remove slots which don't make any sense to be cummed on:
		slotsTargeted.remove(InventorySlot.ANKLE); // This is covered by the SOCK slot
		slotsTargeted.remove(InventorySlot.TORSO_OVER); // This is covered by the TORSO_UNDER slot
		
		for(InventorySlot slot : slotsTargeted) {
			if(target.getVisibleClothingConcealingSlot(slot).isEmpty()) {
				areaList.add(slot);
			}
		}
		areaList.removeIf((covArea) -> !covArea.isPhysicallyAvailable(target));
		return new ArrayList<>(areaList);
	}
	
	private static String getClothingCummedOnText(GameCharacter characterOrgasming, GameCharacter target, List<CoverableArea> areas, List<AbstractClothing> clothing) {
		List<InventorySlot> nakedAreas = new ArrayList<>();
		for(CoverableArea area : areas) {
			nakedAreas.addAll(getNakedAreasCummedOn(target, area));
		}
		nakedAreas = new ArrayList<>(new HashSet<>(nakedAreas));
		
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		StringBuilder sb = new StringBuilder();
		
		if(nakedAreas.isEmpty()) {
			sb.append(" по всему [npc2.namePos] "+Util.clothesToStringList(clothing, false)+".");
		} else {
			sb.append(" по всему [npc2.namePos] "+Util.clothesToStringList(clothing, false)+", а также [npc2.her] обнаженных "+Util.inventorySlotsToParsedStringList(nakedAreas, target)+".");
		}
		if(!immobile) {
			sb.append(" [npc.Name] [npc.verb(grin)] в виде [npc.her] [npc.cum+] разбрызгивается по [npc2.name], пачкая [npc2.her] одежду.");
		}
		return UtilText.parse(characterOrgasming, target, sb.toString());
	}

	private static String getClothingCummedOnText(GameCharacter characterOrgasming, List<CoverableArea> areas, List<AbstractClothing> clothing) {
		List<InventorySlot> nakedAreas = new ArrayList<>();
		for(CoverableArea area : areas) {
			nakedAreas.addAll(getNakedAreasCummedOn(characterOrgasming, area));
		}
		nakedAreas = new ArrayList<>(new HashSet<>(nakedAreas));

		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		StringBuilder sb = new StringBuilder();
		
		if(nakedAreas.isEmpty()) {
			sb.append(" по всему [npc.her] "+Util.clothesToStringList(clothing, false)+".");
		} else {
			sb.append(" по всему [npc.her] "+Util.clothesToStringList(clothing, false)+", а также обнаженную [npc.her] "+Util.inventorySlotsToParsedStringList(nakedAreas, characterOrgasming)+".");
		}

		if(!immobile) {
			sb.append(" [npc.She] [npc.verb(let)] в виде [npc.a_moan+], когда [npc.her] [npc.cum+] разбрызгивается по одежде [npc.her], пачкая [npc.her] наряд.");
		}
		return UtilText.parse(characterOrgasming, sb.toString());
	}
	
	private static String getInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		
		if(characterOrgasming.isPlayer()) {
			if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
				return ("<br/>Вы видите, как живот [npc2.namePos] раздувается до огромных, переполненных размеров от колоссального количества спермы, которую вы закачали в [npc2.herHim]."
						+ (!immobile?" Положив [npc.hand] на огромный живот [npc2.her], вы ухмыляетесь, думая про себя, что [npc2.she] теперь выглядит так, будто [npc2.sheIs] на позднем сроке беременности.":""));
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
				return ("<br/>Вы видите, как живот [npc2.namePos] раздувается от огромного количества спермы, которую вы закачали в [npc2.herHim]."
						+ (!immobile?" Положив [npc.hand] на живот [npc2.her], вы ухмыляетесь, думая про себя, что [npc2.she] теперь выглядит так, будто [npc2.sheIs] беременна.":""));
				
			} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
				return ("<br/>Вы видите, как живот [npc2.namePos] немного раздувается от того невероятного количества спермы, которое вы закачали в [npc2.herHim]."
						+ (!immobile?" Положив [npc.hand] на живот [npc2.her], вы ухмыляетесь, думая про себя, что [npc2.she] теперь выглядит так, будто [npc2.sheIs] на ранней стадии беременности.":""));
			}
		} else {
			if(target.isPlayer()) {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>Вы чувствуете, как ваш живот раздувается до огромных, переполненных размеров от колоссального количества спермы, которую в вас закачали."
							+ (!immobile?" Положив [npc.hand] на твой живот, [npc.name] ухмыляется, пока [npc.she] замечает, что теперь ты выглядишь так, будто ты на позднем сроке беременности.":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>Ты чувствуешь, как твой живот слегка раздувается от огромного количества спермы, закачанной внутрь тебя."
							+ (!immobile?" Положив [npc.hand] на твой живот, [npc.name] ухмыляется, пока [npc.she] замечает, что теперь ты выглядишь беременной.":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>Ты чувствуешь, как твой живот слегка раздувается от невероятного количества спермы, закачанной внутрь тебя."
							+ (!immobile?" Положив [npc.hand] на твой живот, [npc.name] ухмыляется, пока [npc.she] замечает, что теперь ты выглядишь так, будто у тебя самый ранний срок беременности.":""));
				}
			} else {
				if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
					return ("<br/>Живот [npc2.NamePos] раздувается до огромных, переполненных размеров от того невероятного количества спермы, которое [npc.namePos] закачал в [npc2.herHim]."
							+ (!immobile?" Положив [npc.hand] на живот [npc2.her], [npc.race] ухмыляется, пока [npc.she] замечает, что [npc2.name] теперь выглядит так, будто [npc2.sheIs] на позднем сроке беременности.":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
					return ("<br/>Живот [npc2.NamePos] раздувается от огромного количества спермы, которое [npc.namePos] закачал в [npc2.herHim]."
							+ (!immobile?" Положив [npc.hand] на живот [npc2.her], [npc.race] ухмыляется, пока [npc.she] замечает, что [npc2.name] теперь выглядит так, будто [npc2.sheIs] беременна.":""));
					
				} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
					return ("<br/>[npc2.NamePos] слегка раздувается от того огромного количества спермы, которое [npc.namePos] [npc2.herHim] влил внутрь."
							+ (!immobile?" Положив [npc.hand] на живот [npc2.her], [npc.race] ухмыляется, когда [npc.she] замечает, что [npc2.name] теперь выглядит так, словно [npc2.sheIs] на ранних стадиях беременности.":""));
				}
			}
		}
		return "";
	}
	
	private static String getSpinneretInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTailSpinneret()?"[npc2.tail]":"abdomen")+" раздувается до массивных, раздутых размеров от того огромного количества спермы, которое [npc.nameHas] влил в паутину [npc2.her].");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTailSpinneret()?"[npc2.tail]":"abdomen")+" раздувается от огромного количества спермы, которое [npc.nameHas] влил в паутину [npc2.her].");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos] "+(target.hasTailSpinneret()?"[npc2.tail]":"abdomen")+" слегка раздувается от того огромного количества спермы, которое [npc.nameHas] влил в паутину [npc2.her].");
		}
		return "";
	}
	
	private static String getBreastInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.breasts] раздуваются до массивных, раздутых размеров, когда их распирает от того огромного количества спермы, которое [npc.nameHas] влил в них.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos] [npc2.breasts] раздуваются от огромного количества спермы, которое [npc.nameHas] влил в них.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.breasts] слегка раздуваются от того огромного количества спермы, которое [npc.nameHas] влил в них.");
		}
		return "";
	}
	
	private static String getBreastCrotchInflationText(GameCharacter characterOrgasming, GameCharacter target, float cumAmount) {
		if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.crotchBoobs] раздуваются до массивных, раздутых размеров, когда их распирает от того огромного количества спермы, которое [npc.nameHas] влил в них.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()) {
			return ("<br/>[npc2.NamePos] [npc2.crotchBoobs] раздуваются от огромного количества спермы, которую [npc.nameHas] влил в них.");
			
		} else if(cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()) {
			return ("<br/>[npc2.NamePos] [npc2.crotchBoobs] слегка раздуваются от невероятного количества спермы, которую [npc.nameHas] влил в них.");
		}
		return "";
	}
	
	private static String getGenericVaginaOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, OrgasmCumTarget targetArea) {
		boolean immobile = isCharacterTotallyImmobilised(characterOrgasming);
		boolean sleeping = characterOrgasming.isAsleep();
		
		genericOrgasmSB.setLength(0);
		
		if(immobile) {
			genericOrgasmSB.append("Отчаянный, дрожащий жар, внезапно нахлынувший из [npc.her] [npc.pussy+],");
			if(sleeping) {
				genericOrgasmSB.append(" но даже при этом [npc.name] [npc.verb(remain)] глубоко спит, и даже волны чистого экстаза, омывающей [npc.herHim], недостаточно, чтобы разбудить [npc.herHim].");
			} else {
				genericOrgasmSB.append(" но даже при этом [npc.name] [npc.verb(remain)] остается совершенно тихой и неподвижной, никак не реагируя на волну чистого экстаза, омывающую [npc.herHim].");
			}
		} else {
			genericOrgasmSB.append("Отчаянный, дрожащий жар внезапно нахлынывает из [npc.namePos] [npc.pussy+], и [npc.she] [npc.verb(let)] источает маниакальный визг, когда ослепительная волна чистого экстаза омывает [npc.herHim].");
		}
		
		GameCharacter characterPenetrating = null;
		if(Main.sex.getCharacterOngoingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).size()>0) {
			characterPenetrating = Main.sex.getCharacterOngoingSexArea(characterOrgasming, SexAreaOrifice.VAGINA).get(0);
		}
		SexAreaPenetration penetration = Main.sex.getFirstOngoingSexAreaPenetration(characterOrgasming, SexAreaOrifice.VAGINA);
		
		if(characterPenetrating!=null && penetration!=null) {
			boolean immobilePenetrator = isCharacterTotallyImmobilised(characterPenetrating);
			boolean sleepingPenetrator = characterPenetrating.isAsleep();
			boolean playerInvolved = characterOrgasming.isPlayer() || characterPenetrating.isPlayer();
					
			switch(penetration) {
				case FINGER:
					if(immobile) {
						if(characterOrgasming.equals(characterPenetrating)) {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(keep)] [npc.her] [npc.fingers] глубоко засунут в [npc.her] [npc.pussy+], оставаясь при этом "+(sleeping?"спящий":"совершенно неподвижный")+".");
						} else {
							if(immobilePenetrator) {
								genericOrgasmSB.append(" [npc2.NamePos] [npc2.fingers+] остаются засунутыми в [npc.namePos] [npc.pussy+] на протяжении [npc.her] оргазма, при этом оба "
										+(playerInvolved?"вы":"их")+" остаются "+(sleeping&&sleepingPenetrator?"спящий":"совершенно неподвижный")+".");
							} else {
								genericOrgasmSB.append(" [npc2.NamePos] [npc2.fingers+] продолжают качать в [npc.namePos] [npc.pussy+] сквозь [npc.her] оргазм, но, несмотря на эту стимуляцию, [npc.she] [npc.verb(remain)] "+(sleeping?"крепко спит":"совершенно неподвижный")+".");
							}
						}
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append(" Вы поджимаете пальцы глубоко внутри своей [npc.pussy+], и, отчаянно совершая манящие движения, вы издаете серию пронзительных стонов, пока мышцы влагалища сжимают и обхватывают ваши вошедшие пальцы.");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" Пальцы [npc2.NamePos] так и остаются засунутыми в вашу [npc.pussy+], и вы издаете серию пронзительных стонов, пока мышцы влагалища сжимают и обхватывают вошедшие пальцы.");
								} else {
									genericOrgasmSB.append(" Пальцы [npc2.NamePos] продолжают ритмично двигаться в вашей [npc.pussy+], и вы издаете серию пронзительных стонов, пока мышцы влагалища сжимают и обхватывают вошедшие пальцы.");
								}
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append(" Мышцы влагалища [npc.NamePos] сжимают и обхватывают вошедшие пальцы [npc.her], и, подгоняемая интенсивным, приятным ощущением, [npc.she] продолжает ласкать и дразнить клитор [npc.her], при этом издавая серию [npc.moans+].");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" Пальцы [npc2.NamePos] так и остаются засунутыми в [npc.namePos] [npc.pussy+], и [npc.she] [npc.verb(let)] серию пронзительных стонов, пока мышцы влагалища [npc.her] сжимают и обхватывают вошедшие пальцы.");
								} else {
									genericOrgasmSB.append(" Мышцы влагалища [npc.NamePos] сжимают и обхватывают вошедшие пальцы [npc2.namePos], и [npc2.she] продолжает ласкать и дразнить клитор [npc.her], вырывая серию [npc.moans+] из между [npc.her] [npc.lips+].");
								}
							}
						}
					}
					break;
				case PENIS:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append(" [npc2.NamePos] [npc2.cock+] остается засунутым в [npc.namePos] [npc.pussy+] на протяжении [npc.her] оргазма, при этом оба "
									+(playerInvolved?"вы":"их")+" остаются "+(sleeping&&sleepingPenetrator?"спящий":"совершенно неподвижный")+".");
						} else {
							genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] трахает [npc.namePos] [npc.pussy+] во время [npc.her] оргазма, но, несмотря на эту стимуляцию, [npc.she] [npc.verb(remain)] "
									+(sleeping?"крепко спит":"совершенно неподвижный")+".");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append(" Ты продолжаешь трахать саму себя во время оргазма, издавая серию высоких стонов, пока мышцы влагалища сжимают и обхватывают твой [npc.cock+].");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.NamePos] [npc2.cock+] остается внутри твоей [npc.pussy+] на протяжении всего оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] в еблу твою [npc.pussy+] до самого оргазма,");
								}
								genericOrgasmSB.append(" и ты издаешь серию высоких стонов, пока мышцы влагалища сжимают и обхватывают [npc2.her] [npc2.penis+].");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append(" [npc.Name] carries on fucking [npc.her] own [npc.pussy+] through [npc.her] orgasm, causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around [npc.her] [npc.penis+].");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.NamePos] [npc2.cock+] остается внутри [npc.namePos] [npc.pussy+] во время [npc.her] оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] трахает [npc.namePos] [npc.pussy+] во время [npc.her] оргазма,");
								}
								genericOrgasmSB.append(" заставляя [npc.herHim] издавать серию высоких стонов, пока [npc.her] влагалищные мышцы сжимают и обхватывают [npc2.her] [npc2.penis+].");
							}
						}
					}
					break;
				case TAIL:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append(" [npc2.NamePos] [npc2.tail+] остается внутри [npc.namePos] [npc.pussy+] во время [npc.her] оргазма, при этом оба "
									+(playerInvolved?"вы":"их")+" остаются "+(sleeping&&sleepingPenetrator?"спящий":"совершенно неподвижный")+".");
						} else {
							genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] на хвосте-трахании [npc.namePos] [npc.pussy+] сквозь [npc.her] оргазм, но несмотря на эту стимуляцию, [npc.she] [npc.verb(remain)] "
									+(sleeping?"крепко спит":"совершенно неподвижный")+".");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append(" Ты продолжаешь трахать себя хвостом на протяжении всего оргазма, издавая серию высоких стонов, пока мышцы влагалища сжимают и обхватывают инородный предмет.");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.tail+] [npc2.NamePos] остается засунутым в твою [npc.pussy+] на протяжении всего оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] на хвосте-трахании твоей [npc.pussy+] на протяжении всего оргазма,");
								}
								genericOrgasmSB.append(" и ты издаешь серию высоких стонов, пока мышцы влагалища сжимают и обхватывают инородный предмет.");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append(" [npc.Name] carries on tail-fucking [npc.her] own [npc.pussy+] through [npc.her] orgasm, causing [npc.herHim] to let out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.tail+] [npc2.NamePos] остается засунутым в [npc.namePos] [npc.pussy+] на протяжении [npc.her] оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] на хвосте-трахании [npc.namePos] [npc.pussy+] на протяжении [npc.her] оргазма,");
								}
								genericOrgasmSB.append(" заставляя [npc.herHim] издавать серию высоких стонов, пока [npc.her] влагалищные мышцы сжимают и обхватывают инородный предмет.");
							}
						}
					}
					break;
				case TONGUE:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append(" [npc2.tongue+] [npc2.NamePos] остается глубоко вонзенным в [npc.namePos] [npc.pussy+] на протяжении [npc.her] оргазма, при этом оба "
									+(playerInvolved?"вы":"их")+" остаются "+(sleeping&&sleepingPenetrator?"спящий":"совершенно неподвижный")+".");
						} else {
							genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)], вылизывая и целуя [npc.namePos] клитор во время [npc.her] оргазма, но, несмотря на эту стимуляцию, [npc.she] [npc.verb(remain)] "
									+(sleeping?"крепко спит":"совершенно неподвижный")+".");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append(" Ты продолжаешь лизать и целовать свой клитор во время оргазма, издавая серию высоких стонов, когда чувствуешь, как мышцы влагалища дрожат и сокращаются от этого всепоглощающего ощущения.");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.Name] [npc2.tongue+] остается глубоко внутри твоей [npc.pussy+], пока ты испытываешь оргазм,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] продолжает лизать и целовать твой клитор, пока ты испытываешь оргазм,");
								}
								genericOrgasmSB.append(" заставляя тебя издавать серию высоких стонов, когда ты чувствуешь, как мышцы влагалища дрожат и сокращаются от этого всепоглощающего ощущения.");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append(" [npc.Name] продолжает лизать и целовать собственный [npc.her] клитор, пока [npc.she] испытывает оргазм, отчего [npc.herHim] издает серию высоких стонов, пока [npc.her] влагалищные мышцы дрожат и сокращаются от этого всепоглощающего ощущения.");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.Name] [npc2.tongue+] остается глубоко внутри [npc.namePos] [npc.pussy+], пока [npc.she] [npc.verb(orgasm)],");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)], вылизывая и целуя [npc.namePos] клитор, пока [npc.she] испытывает оргазм,");
								}
								genericOrgasmSB.append(" заставляя [npc.herHim] издавать серию высоких стонов, пока [npc.her] влагалищные мышцы дрожат и сокращаются от этого всепоглощающего ощущения.");
							}
						}
					}
					break;
				case TENTACLE:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append(" [npc2.NamePos] [npc2.tentacle+] остается глубоко в [npc.namePos] [npc.pussy+] во время [npc.her] оргазма, при этом оба "
									+(playerInvolved?"вы":"их")+" остаются "+(sleeping&&sleepingPenetrator?"спящий":"совершенно неподвижный")+".");
						} else {
							genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] на щупальцевом трахании [npc.namePos] [npc.pussy+] сквозь [npc.her] оргазм, но, несмотря на эту стимуляцию, [npc.she] [npc.verb(remain)] "
									+(sleeping?"крепко спит":"совершенно неподвижный")+".");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append(" Ты продолжаешь трахать себя щупальцем на протяжении всего оргазма, издавая серию высоких стонов, пока твои влагалищные мышцы сжимаются и обхватывают вошедший внутрь объект.");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.NamePos] [npc2.tentacle+] остается внутри твоей [npc.pussy+] на протяжении всего оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] на щупальцевом трахании твоей [npc.pussy+] на протяжении всего твоего оргазма,");
								}
								genericOrgasmSB.append(" и ты издаешь серию высоких стонов, пока мышцы влагалища сжимают и обхватывают инородный предмет.");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append(" [npc.Name] продолжает кромсать щупальцами [npc.her] собственную [npc.pussy+] через [npc.her] оргазм, заставляя [npc.herHim] издавать череду высоких стонов, пока [npc.her] вагинальные мышцы сжимаются и обхватывают вошедший внутрь объект.");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.NamePos] [npc2.tentacle+] остается зажатым в [npc.namePos] [npc.pussy+] до самого [npc.her] оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] на щупальцевом трахании [npc.namePos] [npc.pussy+] на протяжении [npc.her] оргазма,");
								}
								genericOrgasmSB.append(" заставляя [npc.herHim] издавать серию высоких стонов, пока [npc.her] влагалищные мышцы сжимают и обхватывают инородный предмет.");
							}
						}
					}
					break;
				case CLIT:
					if(immobile) {
						if(immobilePenetrator) {
							genericOrgasmSB.append(" [npc2.NamePos] [npc2.clit+] остается засунутым в [npc.namePos] [npc.pussy+] на протяжении [npc.her] оргазма, при этом оба "
									+(playerInvolved?"вы":"их")+" остаются "+(sleeping&&sleepingPenetrator?"спящий":"совершенно неподвижный")+".");
						} else {
							genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)] на клиторовом трахании [npc.namePos] [npc.pussy+] сквозь [npc.her] оргазм, но, несмотря на эту стимуляцию, [npc.she] [npc.verb(remain)] "
									+(sleeping?"крепко спит":"совершенно неподвижный")+".");
						}
						
					} else {
						if(characterOrgasming.isPlayer()) {
							if(characterPenetrating.isPlayer()) {
								genericOrgasmSB.append(" Ты продолжаешь трахать свой клитор щупальцем на протяжении всего оргазма, издавая серию высоких стонов, пока твои влагалищные мышцы сжимаются и обхватывают твой [npc.clit+].");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.NamePos] [npc2.clit+] остается внутри твоей [npc.pussy+] на протяжении всего твоего оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)], продолжая трахать твою [npc.pussy+] клитором во время твоего оргазма,");
								}
								genericOrgasmSB.append(" и ты издаешь серию высоких стонов, пока твои влагалищные мышцы сжимают и обхватывают [npc2.her] [npc2.clit+].");
							}
						} else {
							if(characterOrgasming.equals(characterPenetrating)) {
								genericOrgasmSB.append(" [npc.Name] продолжает трахать [npc.her] собственный [npc.pussy+] клитором во время [npc.her] оргазма, заставляя [npc.herHim] издавать серию высоких стонов, пока [npc.her] влагалищные мышцы сжимают и обхватывают [npc.her] [npc.clit+].");
							} else {
								if(immobilePenetrator) {
									genericOrgasmSB.append(" [npc2.NamePos] [npc2.clit+] остается внутри [npc.namePos] [npc.pussy+] во время [npc.her] оргазма,");
								} else {
									genericOrgasmSB.append(" [npc2.Name] [npc2.verb(carry)], продолжая трахать [npc.namePos] [npc.pussy+] во время [npc.her] оргазма,");
								}
								genericOrgasmSB.append(" заставляя [npc.herHim] издавать серию высоких стонов, пока [npc.her] влагалищные мышцы сжимают и обхватывают [npc2.her] [npc2.clit+].");
							}
						}
					}
					break;
				case FOOT: //TODO
					break;
			}
			
		} else { // No penetration:
			boolean pluggedVagina = false;
			for(AbstractClothing c : characterOrgasming.getClothingCurrentlyEquipped()) {
				if(c.getItemTags().contains(ItemTag.PLUGS_VAGINA)) {
					pluggedVagina = true;
					if(immobile) {
						if(sleeping) {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc.her] влагалищные мышцы сжимают и обхватывают "+c.getName()+" вставший в [npc.her] [npc.pussy].");
						} else {
							genericOrgasmSB.append(" [npc.Name] [npc.verb(remain)] совершенно неподвижно, пока [npc.her] влагалищные мышцы сжимают и обхватывают "+c.getName()+" вставший в [npc.her] [npc.pussy].");
						}
					} else {
						genericOrgasmSB.append(" [npc.NamePos] [npc.pussy+] сильно сжимаются, заставляя [npc.herHim] издавать серию высоких стонов, пока вагинальные мышцы [npc.her] обхватывают и сжимают "+c.getName()+" вставший в [npc.her] [npc.pussy].");
					}
					break;
				}
			}
			if(!pluggedVagina) {
				SexAreaPenetration analPenetrator = Main.sex.getFirstOngoingSexAreaPenetration(characterOrgasming, SexAreaOrifice.ANUS);
				Set<GameCharacter> tribbingPartners = Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT);
				
				if(characterOrgasming.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()
						&& analPenetrator!=null
						&& analPenetrator.isTakesVirginity()) {
					GameCharacter characterPenetratingAss = Main.sex.getOngoingCharactersUsingAreas(characterOrgasming, SexAreaOrifice.ANUS, analPenetrator).iterator().next();
					genericOrgasmSB.append(UtilText.parse(characterOrgasming, characterPenetratingAss,
							" [npc.NamePos] [npc.pussy+] сильно сжимаются, и несмотря на то, что проникновения нет, удовольствие [npc.namePos] ничуть не уменьшается из-за того, что [npc.sheIs] "
								+(Main.sex.getSexPace(characterOrgasming)==SexPace.SUB_RESISTING?"сосредоточена в основном на":"обожании")
								+" ощущения [npc2.namePos] "+analPenetrator.getName(characterPenetratingAss)+" в заднице [npc.her]."));
					
				} else if(tribbingPartners!=null && !tribbingPartners.isEmpty()) {
					GameCharacter tribbingPartner = tribbingPartners.iterator().next();
					if(immobile) {
						if(sleeping) {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, tribbingPartner,
									"  [npc.Name] [npc.verb(remain)] глубоко спит, пока [npc2.namePos] [npc2.pussy+] трется, толкается и прижимается к [npc.hers]."));
						} else {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, tribbingPartner,
									"  [npc.Name] [npc.verb(remain)] совершенно неподвижна, пока [npc2.namePos] [npc2.pussy+] трется, толкается и прижимается к [npc.hers]."));
						}
					} else {
						genericOrgasmSB.append(UtilText.parse(characterOrgasming, tribbingPartner,
								" [npc.NamePos] [npc.pussy+] сильно сжимаются, и [npc.she] не может не издать [npc.a_moan+], пока [npc.she] [npc.verb(focus)] на ощущении того, как [npc2.namePos] [npc2.pussy+] трется, толкается и прижимается к [npc.hers]."));
					}
					
				} else {
					genericOrgasmSB.append(" [npc.NamePos] [npc.pussy+] сильно сжимаются, и волна разочарования от осознания собственной пустоты почти перекрывает удовольствие, разливающееся по паху [npc.her].");
				}
			}
		}
		
		if(targetArea == OrgasmCumTarget.LILAYA_PANTIES && !Main.game.getPlayer().hasPenisIgnoreDildo()) {
			genericOrgasmSB.append(" Пока ты визжишь и тяжело дышишь, ты подносишь трусики Lilaya к самому лицу, вдыхая мускусный аромат твоей демоницы [lilaya.relation(pc)], смешанный с запахом духов, и представляешь, как её киска прижимается к мягкой ткани.");
		}
		
		if(characterOrgasming.isVaginaSquirter()) {
			List<String> ejaculateDescriptors = new ArrayList<>();
			for(FluidModifier mod : FluidModifier.values()) {
				if(characterOrgasming.hasGirlcumModifier(mod)) {
					ejaculateDescriptors.add(mod.getName());
				}
			}
			ejaculateDescriptors.add("wet");
			genericOrgasmSB.append("<br/>Пока [npc.namePos] [npc.pussy+] в неудержимых спазмах и дрожи от восторга, внезапно выплескивается огромное количество горячей, "+Util.randomItemFrom(ejaculateDescriptors)+" женской эякуляции");

			if(characterPenetrating!=null && penetration!=null) {
				boolean immobilePenetrator = isCharacterTotallyImmobilised(characterPenetrating);
				boolean sleepingPenetrator = characterPenetrating.isAsleep();
				switch(penetration) {
					case CLIT:
						genericOrgasmSB.append(", которая помогает смазать [npc2.namePos] [npc2.clit+].");
						break;
					case FINGER:
						if(immobilePenetrator) {
							genericOrgasmSB.append(", которая помогает смазать [npc2.namePos] [npc2.fingers+], пока они остаются "+(sleepingPenetrator?"набитыми":"неподвижно")+" глубоко внутри [npc.her] [npc.pussy].");
						} else {
							genericOrgasmSB.append(", что создает скользкий, влажный звук, пока [npc2.namePos] [npc2.fingers+] продолжают входить и выходить из [npc.her] [npc.pussy].");
						}
						break;
					case FOOT:
						genericOrgasmSB.append(", которая стекает по [npc2.namePos] [npc2.feet+].");
						break;
					case PENIS:
						if(immobilePenetrator) {
							genericOrgasmSB.append(", что помогает смазывать [npc2.namePos] [npc2.cock+], пока тот остается "+(sleepingPenetrator?"набитыми":"неподвижно")+" глубоко внутри [npc.her] [npc.pussy].");
						} else {
							genericOrgasmSB.append(", что помогает издавать похотливый, хлюпающий звук, пока [npc2.namePos] [npc2.cock+] продолжает входить и выходить из [npc.her] [npc.pussy].");
						}
						break;
					case TAIL:
						if(immobilePenetrator) {
							genericOrgasmSB.append(", что помогает смазывать [npc2.namePos] [npc2.tail+(true)], пока тот остается "+(sleepingPenetrator?"набитыми":"неподвижно")+" глубоко внутри [npc.her] [npc.pussy].");
						} else {
							genericOrgasmSB.append(", что помогает издавать похотливый хлюпающий звук, пока [npc2.namePos] [npc2.tail+(true)] продолжает входить и выходить из [npc.her] [npc.pussy].");
						}
						break;
					case TENTACLE:
						if(immobilePenetrator) {
							genericOrgasmSB.append(", что помогает смазывать [npc2.namePos] [npc2.tentacle+(true)], пока тот остается "+(sleepingPenetrator?"набитыми":"неподвижно")+" глубоко внутри [npc.her] [npc.pussy].");
						} else {
							genericOrgasmSB.append(", что помогает издавать похотливый хлюпающий звук, пока [npc2.namePos] [npc2.tentacle+(true)] продолжает входить и выходить из [npc.her] [npc.pussy].");
						}
						break;
					case TONGUE:
						if(immobilePenetrator) {
							genericOrgasmSB.append(", которое стекает по [npc2.namePos] [npc2.tongue+], пока [npc2.she] [npc2.verb(keep)] его "+(sleepingPenetrator?"набитыми":"неподвижно")+" глубоко внутри [npc.her] [npc.pussy].");
						} else {
							genericOrgasmSB.append(", что [npc2.name] [npc2.verb(lap)] вместе с [npc2.her] [npc2.tongue+].");
						}
						break;
				}
			} else {
				genericOrgasmSB.append(".");
			}
			
			if(targetArea == OrgasmCumTarget.LILAYA_PANTIES) {
				genericOrgasmSB.append("<br/>Ты быстро спускаешь трусики Lilaya между своих ног, кончая прямо в её белье и издавая [pc.a_moan+].");
				LilayasRoom.lilayasPanties.setDirty(null, true);
				
			} else {
				AbstractClothing vaginaClothing = Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.VAGINA);
				if(vaginaClothing!=null) {
					if(!vaginaClothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)
							&& !vaginaClothing.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
						if(immobile) {
							if(sleeping) {
								genericOrgasmSB.append(" [npc.She] [npc.verb(continue)] в сон, пока [npc.her] "
										+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"являются":"есть")+" быстро пачкается жидкостями [npc.her].");
							} else {
								genericOrgasmSB.append(" [npc.She] [npc.do] совсем не реагирует, пока [npc.she] [npc.verb(feel)], что [npc.her] "
										+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"являются":"есть")+" быстро пачкается жидкостями [npc.her].");
							}
						} else {
							genericOrgasmSB.append(" [npc.She] [npc.verb(let)] глубокий вздох, пока [npc.she] [npc.verb(feel)], что [npc.her] "
									+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"являются":"есть")+" быстро пачкается жидкостями [npc.her].");
						}
						
					} else {
						genericOrgasmSB.append(" Пока [npc.her] "+vaginaClothing.getName()+" "+(vaginaClothing.getClothingType().isPlural()?"являются":"есть")+" запечатывает [npc.her] [npc.pussy], ничто не пачкается жидкостями [npc.her].");
					}
					vaginaClothing.setDirty(Main.sex.getCharacterPerformingAction(), true);
					
				} else {
					Set<GameCharacter> charactersEatingOut = new HashSet<>(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
					charactersEatingOut.addAll(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaOrifice.MOUTH));
					
					for(GameCharacter character : charactersEatingOut) { // Should only be one character
						if(isCharacterTotallyImmobilised(character)) {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, character,
									" Когда лицо [npc2.namePos] оказывается прямо перед [npc.namePos], [npc.pussy+], жидкости [npc.namePos] брызжут как прямо в рот [npc2.her], так и на [npc2.her] [npc2.face]."));
						} else {
							genericOrgasmSB.append(UtilText.parse(characterOrgasming, character,
									" Пока [npc2.nameIs] ласкает [npc.herHim] языком, [npc.namePos] жидкости брызжут как в [npc2.her] рот, так и на все [npc2.her] [npc2.face]."));
						}
					}
				}
			}
		}
		
//		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING && characterOrgasming.equals(Main.sex.getCharacterLayingEggs())) {
//			genericOrgasmSB.append(eggLayingTargetDescription(SexAreaPenetration.CLIT, characterOrgasming, Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.CLIT).get(0), null));
//		}
		if(immobile) {
			if(sleeping) {
				genericOrgasmSB.append("<br/><br/>Хотя [npc.she] [npc.verb(make)] издает кое-какие похотливые звуки и немного [npc.verb(squirm)], [npc.name] [npc.verb(remain)] засыпает, когда [npc.her] женский оргазм начинает стихать, и [npc.do] нет никаких признаков того, что она вот-вот проснется.");
			} else {
				genericOrgasmSB.append("<br/><br/>Покорно изображая неодушевленную секс-куклу, [npc.name] [npc.verb(remain)] безмолвно и неподвижно, пока [npc.her] женская разрядка начинает угасать, и [npc.do] не подавая никаких признаков того, что ей нужно восстановиться после [npc.her] оргазма.");
			}
		} else {
			genericOrgasmSB.append("<br/><br/>С глубоким, удовлетворенным вздохом [npc.namePos] женская разрядка начинает угасать, и [npc.she] [npc.verb(take)] несколько глубоких вдохов, пока [npc.she] [npc.verb(seek)], чтобы перевести [npc.her] дух.");
		}
		
		if(characterPenetrating!=null) {
			return UtilText.parse(characterOrgasming, characterPenetrating, genericOrgasmSB.toString());
		} else {
			return UtilText.parse(characterOrgasming, genericOrgasmSB.toString());
		}
	}
	
	private static String getGenericMoundOrgasm(GameCharacter characterOrgasming) {
		if(isCharacterTotallyImmobilised(characterOrgasming)) {
			if(characterOrgasming.isAsleep()) {
				return UtilText.parse(characterOrgasming,
						"Не просыпаясь, [npc.name] издает похотливый [npc.moan], когда внезапная волна чистого экстаза накрывает [npc.herHim]. Мышцы внутри [npc.her] бесполого тела начинают спазмировать и сокращаться, но, несмотря на это, [npc.she] остается глубоко спящей, и [npc.her] интенсивного псевдо-оргазма оказывается недостаточно, чтобы пробудить [npc.herHim] от [npc.her] сна.");
			} else {
				return UtilText.parse(characterOrgasming,
						"Продолжая оставаться совершенно тихой и неподвижной, [npc.name] [npc.do] никак не реагирует на то, как внезапная волна чистого экстаза накрывает [npc.herHim]. Мышцы внутри [npc.her] бесполого тела начинают спазмировать и сокращаться, но, несмотря на это, [npc.she] удается безупречно исполнить роль неодушевленной секс-куклы, позволяя [npc.her] интенсивному псевдо-оргазму обрушиться на [npc.herHim] без единого стона.");
			}
		}
		if(characterOrgasming.isPlayer()) {
			return "С оглушительным криком и дрожащими ногами, внезапная волна чистого экстаза накрывает вас. Мышцы внутри вашего бесполого холмика начинают спазмировать и сокращаться, и вскоре вы остаетесь лишь тяжело дышащей, стонущей развалиной, когда на вас обрушивается интенсивный псевдо-оргазм.";
			
		} else {
			return UtilText.parse(characterOrgasming,
					"С оглушительным криком и дрожащими ногами, внезапная волна чистого экстаза накрывает [npc.name]. Мышцы внутри бесполого холмика [npc.her] начинают спазмировать и сокращаться, и [npc.sheIs] вскоре остается лишь тяжело дышащей, стонущей развалиной, когда на [npc.herHim] обрушивается интенсивный псевдо-оргазм [npc.her].");
		}
		
	}
	
	public static String getGenericOrgasmDescription(SexActionInterface sexAction, GameCharacter characterOrgasming, OrgasmCumTarget target) {
		StringBuilder descriptionSB = new StringBuilder();

		GameCharacter characterPenetrated = null;
		
		if(!Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(characterOrgasming, SexAreaPenetration.PENIS).get(0);
			
			List<GameCharacter> charactersPenetrated = new ArrayList<>(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, areaContacted));
			if(charactersPenetrated.contains(Main.sex.getTargetedPartner(characterOrgasming))) {
				characterPenetrated = Main.sex.getTargetedPartner(characterOrgasming);
			}
		}
		
		// Position:
		descriptionSB.append("<p>");
		if(isCharacterTotallyImmobilised(characterOrgasming)) {
			if(characterOrgasming.isAsleep()) {
				descriptionSB.append(UtilText.parse(characterOrgasming,
						"Достигнув [npc.her] климакса, [npc.name] [npc.verb(let)] [npc.a_moan+], но, несмотря на этот шум и кратковременные конвульсии, [npc.she] [npc.verb(remain)] крепко спящим."));
			} else {
				descriptionSB.append(UtilText.parse(characterOrgasming,
						"Достигнув [npc.her] климакса, [npc.name] послушно [npc.verb(suppress)] [npc.her] желание выпустить [npc.a_moan], и, не издав ни единого всхлипа, [npc.she] [npc.verb(remain)] на месте."));
			}
			
		} else {
			descriptionSB.append(getPositionPreparation(characterOrgasming, characterPenetrated!=null?characterPenetrated:Main.sex.getTargetedPartner(characterOrgasming)));
		}
		descriptionSB.append("</p>");
		
		if(characterOrgasming.hasTraitActivated(Perk.AHEGAO) && !isCharacterTotallyImmobilised(characterOrgasming)) {
			descriptionSB.append(getAhegaoDescription(characterOrgasming, Main.sex.getTargetedPartner(characterOrgasming)));
		}
		
		if(characterOrgasming.hasPenisIgnoreDildo()) {
			descriptionSB.append("<p>");
				descriptionSB.append(getGenericPenisOrgasmDescription(sexAction, characterOrgasming, characterPenetrated, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), false));
			descriptionSB.append("</p>");
			
			if(target==OrgasmCumTarget.INSIDE_SWITCH_DOUBLE) {
				GameCharacter secondaryTarget = getSecondaryCreampieTarget(characterPenetrated, (SexAreaOrifice) Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0));

				descriptionSB.append("<p>");
					descriptionSB.append(getGenericPenisOrgasmDescription(sexAction, characterOrgasming, secondaryTarget, target, sexAction.getCondomFailure(characterOrgasming, characterPenetrated), true));
				descriptionSB.append("</p>");
			}
		}
		
//		if(sexAction==GENERIC_ORGASM_OVIPOSITOR_CLIT_EGG_LAYING) {
//			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(characterOrgasming, SexAreaPenetration.CLIT).get(0);
//			genericOrgasmSB.append("<p>"
//										+ eggLayingTargetDescription(SexAreaPenetration.CLIT, characterOrgasming, characterPenetrated, null)
//									+"</p>");
//		}
		
		if(characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>");
				descriptionSB.append(getGenericVaginaOrgasmDescription(sexAction, characterOrgasming, target));
			descriptionSB.append("</p>");
		}
		
		if(!characterOrgasming.hasPenisIgnoreDildo() && !characterOrgasming.hasVagina()) {
			descriptionSB.append("<p>");
				descriptionSB.append(getGenericMoundOrgasm(characterOrgasming));
			descriptionSB.append("</p>");
		}
		
		return descriptionSB.toString();
	}
	
	// Doesn't have penis (or penis is not exposed), and isn't being vaginally penetrated:
	public static final SexAction PLAYER_GENERIC_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Оргазм";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли климакса и больше не можете сдерживать оргазм.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (!Main.game.getPlayer().hasPenisIgnoreDildo() || !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) || Main.game.getPlayer().isWearingCondom())
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), true).applyEffects();
			if (Main.game.getPlayer().hasPenisIgnoreDildo()
					&& !Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			}
		}
		
		@Override
		public boolean endsSex() {
			return Main.game.getPlayer().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.game.getPlayer()).get(0), false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeCreampied() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeCreampied()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			boolean isPenetratingSuitableOrifice  = false;
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ASS:
					case THIGHS:
						return false;
					case ARMPITS:
					case BREAST:
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case ANUS:
					case MOUTH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case VAGINA:
					case SPINNERET:
						isPenetratingSuitableOrifice = true;
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
					case PENIS:
					case TAIL:
					case TENTACLE:
					case TONGUE:
						break;
					case FINGER:
					case FOOT:
						isPenetratingSuitableOrifice = true;
						break;
				}
			}
			
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			if(isPerformingCharacterTotallyImmobilised()) {
				return true;
			}
			
			// Will not use if obeying pull out requests:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(isPerformingCharacterTotallyImmobilised()) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE
					|| (Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT && !GENERIC_ORGASM_KNOTTING.isBaseRequirementsMet())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(getAreaToBeCreampied()==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()) {
				return SexActionPriority.LOW;
			}
			if((Math.random()<0.66f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (getAreaToBeCreampied()==SexAreaOrifice.VAGINA && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)))
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Принудительный кремпи!";
					
				} else if(bodypart == Arm.class) {
					return "Зажаты в объятиях!";
					
				} else if(bodypart == Leg.class) {
					return "Зажаты ногами!";
					
				} else if(bodypart == Tail.class) {
					return "Зажаты хвостом!";
					
				} else if(bodypart == Wing.class) {
					return "Крылья зажаты!";
					
				} else if(bodypart == Tentacle.class) {
					return "Тентакли зажали!";
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						return "Климакс в подмышках";
					case ANUS:
						return "Анальный криампи";
					case ASS:
						return "Климакс на фаллоимитаторе";
					case BREAST:
						if(characterPenetrated.hasBreasts()) {
							return "Климакс при пайзури";
						} else {
							return "Климакс при найзури";
						}
					case BREAST_CROTCH:
						return "Климакс при пайзури";
					case MOUTH:
						return "Глубокий минет";
					case NIPPLE: case NIPPLE_CROTCH:
						return "Криампи на сосках";
					case THIGHS:
						return "Климакс между бёдер";
					case URETHRA_PENIS: case URETHRA_VAGINA:
						return "Уретральный криампи";
					case VAGINA:
						return "Криампи";
					case SPINNERET:
						return "Криампи из паучьих желез";
				}
				
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						return "Климакс при мастурбации рукой";
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						return "Климакс при футджеббе";
					case TONGUE:
						break;
				}
			}
			return "Криампи";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] использует выгодную позицию [npc.her], чтобы заставить тебя кончить прямо в [npc.herHim]! Ты находишься на самом пике оргазма, и у тебя нет времени попытаться оттолкнуть [npc.herHim]!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] плотно обвил(а) твой низ [npc.her] [npc.arms+], вынуждая тебя кончить прямо в [npc.herHim]! Ты на самом пороге оргазма, и у тебя нет времени вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] плотно обвил(а) твой низ [npc.her] [npc.legs+], вынуждая тебя кончить прямо в [npc.herHim]! Ты на самом пороге оргазма, и у тебя нет времени вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] крепко обвивает [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" вокруг твоего низа, вынуждая тебя кончить прямо в [npc.herHim]! Ты на самом пороге оргазма, и у тебя нет времени вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] туго обхватила твоё тело своими [npc.her] [npc.wingSize] [npc.wings], вынуждая тебя кончить прямо в [npc.herHim]! Когда ты оказываешься на самом пике оргазма, у тебя просто нет времени попытаться вырваться из хватки [npc.her]!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] туго обхватила твой низ своими [npc.her] [npc.tentacles+], вынуждая тебя кончить прямо в [npc.herHim]! Когда ты оказываешься на самом пике оргазма, у тебя просто нет времени попытаться вырваться из хватки [npc.her]!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			String returnString = "Ты достиг кульминации и больше не можешь сдерживать оргазм. Заливай кремпай в [npc2.name].";
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
						returnString = "Продолжай тереть свой [npc.cock+] о [npc2.namePos] [npc2.armpit+] во время оргазма, забрызгивая всю [npc2.her] руку и подмышку [npc.cum+].";
						break;
					case ANUS:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = "[style.italicsBad([npc2.NamePos] анус недостаточно глубокий, чтобы вставить твой узел)], так что тебе придётся довольствоваться тем, что ты втиснешь свой [npc.cock+] глубоко в [npc2.her] задницу, прежде чем заполнишь [npc2.herHim] своей [npc.cum+].";
							} else {
								returnString = "Не спеши вставлять узел, а просто втисни свой [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], прежде чем заполнишь [npc2.herHim] своей [npc.cum+].";
							}
							
						} else {
							returnString = "Втисни свой [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+], прежде чем заполнишь [npc2.herHim] своей [npc.cum+].";
						}
						break;
					case ASS:
						returnString = "Продолжай горячее «хотдоггин» своим [npc2.name] во время оргазма, забрызгивая всю [npc2.her] спину и [npc2.assSize] задницу своей [npc.cum+].";
						break;
					case BREAST:
						if(characterPenetrated.hasBreasts()) {
							returnString = "Продолжай трахать [npc2.namePos] грудь, кончая и забрызгивая [npc.cum+] все ее соски и [npc2.her] [npc2.face].";
						} else {
							returnString = "Продолжай тереться о плоскую грудь [npc2.namePos], кончая и забрызгивая [npc.cum+] все ее туловище и [npc2.her] [npc2.face].";
						}
						break;
					case BREAST_CROTCH:
						returnString = "Keep on fucking [npc2.namePos] [npc2.crotchBoobs] as you orgasm, spurting your [npc.cum+] all over [npc2.her] stomach, [npc2.crotchBoobs] and crotch.";
						break;
					case MOUTH:
						returnString = "Запихни свой [npc.cock+] глубоко в [npc2.namePos] горло, прежде чем наполнить [npc2.her] желудок своей [npc.cum+].";
						break;
					case NIPPLE:
						returnString = "Запихни свой [npc.cock+] глубоко в [npc2.namePos] [npc2.nipple+], прежде чем наполнить [npc2.her] грудь своей [npc.cum+].";
						break;
					case NIPPLE_CROTCH:
						returnString = "Запихни свой [npc.cock+] глубоко в [npc2.namePos] [npc2.crotchNipple+], прежде чем наполнить [npc2.her] [npc2.crotchBoob] своей [npc.cum+].";
						break;
					case THIGHS:
						returnString = "Продолжай трахать [npc2.namePos] бедра, пока не начнешь кончать, а затем отстранись и забрызгай [npc.cum+] все ее [npc2.her] [npc2.legs].";
						break;
					case URETHRA_PENIS:
						returnString = "Запихни свой [npc.cock+] глубоко в уретру [npc2.namePos] [npc2.cock+], прежде чем наполнить [npc2.her] мочевой пузырь своей [npc.cum+].";
						break;
					case URETHRA_VAGINA:
						returnString = "Запихни свой [npc.cock+] глубоко в уретру [npc2.namePos] [npc2.pussy+], прежде чем наполнить [npc2.her] мочевой пузырь своей [npc.cum+].";
						break;
					case VAGINA:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = "[style.italicsBad([npc2.NamePos] киска недостаточно глубокая, чтобы ты мог вставить свой узел)], так что тебе придется довольствоваться тем, что ты запихиваешь свой [npc.cock+] глубоко в [npc2.her] щель, прежде чем наполнить"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.herHim]"
												:" [npc2.her] матку")
										+ " своей [npc.cum+].";
							} else {
								returnString = "Не спеши вводить узел, а просто [npc2.pussy+] [npc2.namePos], глубоко [npc.cock+], прежде чем наполнить"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.herHim]"
												:" [npc2.her] матку")
										+ " своей [npc.cum+].";
							}
							
						} else {
							returnString = "[npc2.pussy+] [npc2.namePos], глубоко [npc.cock+], прежде чем наполнить [npc2.her]"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.pussy]"
												:" womb")
										+ " своей [npc.cum+].";
						}
						break;
					case SPINNERET:
						returnString = "Засунь свой [npc.cock+] глубоко в [npc2.namePos] паутинный аппарат, а затем заполни [npc2.herHim] своей [npc.cum+].";
						break;
				}
			} else {
				switch((SexAreaPenetration)areaContacted) {
					case CLIT:
						break;
					case FINGER:
						if(Main.sex.getCharacterPerformingAction().equals(characterPenetrated)) {
							returnString = "Продолжай ласкать свой член, прежде чем облить своей [npc.cum+] всю её [npc.hand+].";
						} else {
							returnString = "Продолжай сосредоточиваться на удовольствии от того, как [npc2.name] ласкает твой член рукой, прежде чем слегка отстраниться и облить своей [npc.cum+] всю [npc2.her] [npc2.hand+].";
						}
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						returnString = "Продолжай сосредоточиваться на удовольствии, которое ты получаешь от [npc2.namePos] [npc2.footjob], прежде чем забрызгать всю [npc2.her] [npc2.feet+] своей [npc.cum+].";
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(characterPenetrated)) {
//				System.out.println(cumProvider.getNameIgnoresPlayerKnowledge()+" "+Main.sex.getCharacterPerformingAction().getNameIgnoresPlayerKnowledge());
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {
				SexAreaInterface areaContacted = getAreaToBeCreampied();
				
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
						case ARMPITS:
							return Util.newArrayListOfValues(
									CoverableArea.ARMPITS);
						case ANUS:
							break;
						case ASS:
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
							break;
						case THIGHS:
							return Util.newArrayListOfValues(
									CoverableArea.LEGS);
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)//TODO should be moved out into pregnancy roulette character method
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static final SexAction GENERIC_ORGASM_KNOTTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterToBeKnotted() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeKnotted() {
			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeKnotted()).get(0);
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()
					|| Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeKnotted()).isEmpty()) {
				return false;
			}

			SexAreaInterface areaContacted = getAreaToBeKnotted();
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			
			if(!areaContacted.isOrifice() || !((SexAreaOrifice)areaContacted).isInternalOrifice()) {
				return false;
			}
			
			// Cannot use if orifice is not deep enough
			// (Does not take into account willingness to fully penetrate, as the orgasming character is assumed to want to knot even if not normally willing to deeply penetrate.)
			if(Main.game.isPenetrationLimitationsEnabled()
					&& Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction()) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			// If immobile, cannot force knot inside:
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() { // Has same priority as normal creampie:
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(getAreaToBeKnotted()==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant()) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (getAreaToBeKnotted()==SexAreaOrifice.VAGINA && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Принудительный кримпэй (Узел!)";
					
				} else if(bodypart == Arm.class) {
					return "Замок в объятиях (Узел!)";
					
				} else if(bodypart == Leg.class) {
					return "Замок ногами (Узел!)";
					
				} else if(bodypart == Tail.class) {
					return "Замок хвостом (Узел!)";
					
				} else if(bodypart == Wing.class) {
					return "Замок крыльями (Узел!)";
					
				} else if(bodypart == Tentacle.class) {
					return "Замок тентаклями (Узел!)";
				}
			}
			
			return UtilText.parse(getCharacterToBeKnotted(), "Узел [npc.herHim]");
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] использует выгодную позицию [npc.her], чтобы заставить тебя кончить внутрь [npc.herHim]! Дай [npc.herHim] то, чего хочет [npc.she], и завяжи узел в [npc.herHim]!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] плотно обхватывает [npc.her] [npc.arms+] вокруг твоего низа, заставляя тебя кончить внутрь [npc.herHim]! Дай [npc.herHim] то, чего хочет [npc.she], и завяжи узел в [npc.herHim]!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] туго обхватила [npc.her] [npc.legs+] вокруг твоего низа, заставляя тебя кончить прямо в [npc.herHim]! Дай [npc.herHim] то, чего [npc.she] хочет, и завяжи [npc.herHim]!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] крепко обвивает [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" вокруг твоего низа, заставляя тебя кончить прямо в [npc.herHim]! Дай [npc.herHim] то, чего [npc.she] хочет, и завяжи [npc.herHim]!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] туго обхватила [npc.her] [npc.wingSize] [npc.wings] вокруг твоего тела, заставляя тебя кончить прямо в [npc.herHim]! Дай [npc.herHim] то, чего [npc.she] хочет, и завяжи [npc.herHim]!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] туго обхватила [npc.her] [npc.tentacles+] вокруг твоего низа, заставляя тебя кончить прямо в [npc.herHim]! Дай [npc.herHim] то, чего [npc.she] хочет, и завяжи [npc.herHim]!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			SexAreaInterface areaContacted = getAreaToBeKnotted();
			String returnString = "Ты достиг пика и больше не можешь сдерживать оргазм. Втолкни свой узел в [npc2.namePos] "+areaContacted.getName(characterPenetrated)+" и заполни [npc2.herHim] своей [npc.cum+].";
			
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString = "Засунь член в [npc2.namePos] [npc2.asshole+] до того, как твой узел раздуется — это намертво заклинит тебя внутри [npc2.herHim], пока ты будешь заполнять [npc2.herHim] своей [npc.cum+].";
					break;
				case MOUTH:
					returnString = "Засунь член глубоко в горло [npc2.namePos] до того, как твой узел раздуется — это намертво заклинит тебя внутри [npc2.herHim], пока ты будешь заполнять желудок [npc2.her] своей [npc.cum+].";
					break;
				case NIPPLE:
					returnString = "Засунь свой член целиком в [npc2.namePos] [npc2.nipple+], прежде чем твоя узел раздуется и намертво запрёт тебя внутри [npc2.herHim], пока ты наполняешь [npc2.her] грудь своей [npc.cum+].";
					break;
				case NIPPLE_CROTCH:
					returnString = "Засунь свой член целиком в [npc2.namePos] [npc2.crotchNipple+], прежде чем твоя узел раздуется и намертво запрёт тебя внутри [npc2.herHim], пока ты наполняешь [npc2.her] [npc2.crotchBoob] своей [npc.cum+].";
					break;
				case URETHRA_PENIS:
					returnString = "Засунь свой член целиком в уретру [npc2.namePos] [npc2.cock+], прежде чем твоя узел раздуется и намертво запрёт тебя внутри [npc2.herHim], пока ты наполняешь [npc2.her] мочевой пузырь своей [npc.cum+].";
					break;
				case URETHRA_VAGINA:
					returnString = "Засунь свой член целиком в уретру [npc2.namePos] [npc2.pussy+], прежде чем твоя узел раздуется и намертво запрёт тебя внутри [npc2.herHim], пока ты наполняешь [npc2.her] мочевой пузырь своей [npc.cum+].";
					break;
				case VAGINA:
					returnString = "Засунь свой член целиком в [npc2.namePos] [npc2.pussy+], прежде чем твоя узел раздуется и намертво запрёт тебя внутри [npc2.herHim], пока ты наполняешь [npc2.her]"
										+(characterPenetrated.isPregnant() || characterPenetrated.hasIncubationLitter(SexAreaOrifice.VAGINA)
												?" [npc2.pussy]"
												:" womb")
										+ " своей [npc.cum+].";
					break;
				default:
					return "Ошибка узла! (Код 1)";
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, false).getDescription();
		}
		
		@Override
		public String applyPreParsingEffects() {
			Main.sex.addCharactersKnottedTogether(Main.sex.getCharacterPerformingAction(), getCharacterToBeKnotted()); // Added so that the generic orgasm description parses it as a knotting action.
			return "";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects() {
			// Moved to start of Main.sex.endSexTurn(), so that there's time to stretch.
//			Main.sex.removeCharactersKnottedTogether(Main.sex.getCharacterPerformingAction()); // Remove as the generic orgasm description has already been parsed.
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = getAreaToBeKnotted();
			GameCharacter characterPenetrated = getCharacterToBeKnotted();
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	
	private static Map<GameCharacter, SexSlot> getSuitableSecondaryCreampieTargets(GameCharacter targetedCharacter) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = null;
		if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())) {
			suitableSecondaryCharacters = new HashMap<>(Main.sex.getSubmissiveParticipants(false));
		} else {
			suitableSecondaryCharacters = new HashMap<>(Main.sex.getDominantParticipants(false));
		}
		suitableSecondaryCharacters.remove(targetedCharacter);
		
		return suitableSecondaryCharacters;
	}
	
	private static GameCharacter getSecondaryCreampieTarget(GameCharacter targetedCharacter, SexAreaOrifice areaContacted) {
		Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(targetedCharacter);
		
		GameCharacter secondaryTarget = null;
		
		for(Entry<GameCharacter, SexSlot> entry : suitableSecondaryCharacters.entrySet()) {
//			System.out.println(entry.getKey().getName(true)+" check");
			if((areaContacted!=SexAreaOrifice.VAGINA || entry.getKey().hasVagina()) && Main.sex.isOrificeFree(entry.getKey(), areaContacted)) {
//				System.out.println(entry.getKey().getName(true)+" free");
				try {
					if(Main.sex.getPosition().getSexInteractions(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()), Main.sex.getSexPositionSlot(entry.getKey())).getInteractions().get(SexAreaPenetration.PENIS).contains(areaContacted)) {
						secondaryTarget = entry.getKey();
						break;
					}
				} catch(Exception ex) {
					// getInteractions() map might not contain key SexAreaPenetration.PENIS
				}
			}
		}
		
		return secondaryTarget;
	}

	public static final SexAction GENERIC_ORGASM_DOUBLE_CREAMPIE = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()) {
				return false;
			}
			if(Main.sex.getCharacterPerformingAction().getPenisCumStorage()==CumProduction.ZERO_NONE) {
				return false;
			}
			Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(Main.sex.getCharacterTargetedForSexAction(this));
			if(suitableSecondaryCharacters.isEmpty()) {
				return false;
			}
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(areaContacted.isPenetration()) {
				return false;
			}
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			if(secondaryTarget==null) {
				return false;
			}
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case MOUTH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						return false;
					case ANUS:
					case VAGINA:
						if(!secondaryTarget.isOrificeTypeExposed((SexAreaOrifice) areaContacted)
								|| !((SexAreaOrifice) areaContacted).isFree(secondaryTarget)) {
							return false;
						}
						break;
				}
			} else {
				return false;
			}
			
			// Will not use if obeying the player and player asked for pull out:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT
				|| Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) { // Cannot double creampie if someone is forcing creampie.
				return false;
			}

			// If immobile, cannot move to double-creampie:
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE
					|| (Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT && !GENERIC_ORGASM_KNOTTING.isBaseRequirementsMet())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& (!Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant() || !getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).isVisiblyPregnant())) {
				return SexActionPriority.LOW;
			}
			if((Math.random()<0.5f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)))
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						return "Двойной анальный криампи";
					case VAGINA:
						return "Двойной криампи";
				}
			}
			return "Двойной криампи";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryCharacterPenetrated = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			String returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
					"Ты достиг пика и больше не можешь сдерживать оргазм. Отдай [npc.name] половину своего криампи, прежде чем быстро вытащить член и кончить внутрь [npc2.name].");
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"[style.italicsBad([npc2.NamePos] анус недостаточно глубокий, чтобы ты мог вставить свой узел)], так что тебе придется довольствоваться тем, что ты втиснешь свой [npc.cock+] глубоко в [npc2.her] задницу и заполнишь [npc2.herHim] своей спермой, отдав [npc.name] половину своей порции.");
								
							} else {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"Держи свой узел подальше от [npc.namePos] [npc.asshole], пока отдаешь [npc.herHim] половину своей порции, а затем быстро выходи, стараясь не застрять внутри [npc2.name], когда кончаешь в [npc2.her] [npc2.asshole].");
							}
							
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"Ты достиг пика и больше не можешь сдерживать оргазм. Выплесни половину своей порции в [npc.namePos] [npc.asshole], а затем быстро выходи и кончай в [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case VAGINA:
						if(Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
							if(Main.game.isPenetrationLimitationsEnabled() && Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, characterPenetrated, (SexAreaOrifice)areaContacted, false)) {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"[style.italicsBad([npc2.NamePos] киска недостаточно глубокая, чтобы ты мог вставить свой узел)], так что тебе придется довольствоваться тем, что ты втиснешь свой [npc.cock+] глубоко в [npc2.her] щель и заполнишь [npc2.herHim] своей спермой, отдав [npc.name] половину своей порции.");
								
							} else {
								returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
										"Держи свой узел подальше от [npc.namePos] [npc.pussy], пока отдаешь [npc.herHim] половину своей порции, а затем быстро выходи, стараясь не застрять внутри [npc2.name], когда кончаешь в [npc2.her] [npc2.pussy].");
							}
							
						} else {
							returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
									"Вы достигли пика и больше не можете сдерживать оргазм. Выплесните половину своей порции в [npc.namePos] [npc.pussy+], после чего быстро выходите и заканчиваете внутрь [npc2.namePos] [npc2.pussy+].");
						}
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);

			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEndEffects();
			
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), areaContacted);
			Main.sex.applyOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, areaContacted, true);
			
			if(areaContacted==SexAreaOrifice.VAGINA) {
				return Main.sex.applyPenetrationEffects(PenisVagina.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.VAGINA);
			} else {
				return Main.sex.applyPenetrationEffects(PenisAnus.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.ANUS);
			}
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && (cumTarget.equals(characterPenetrated) || cumTarget.equals(secondaryTarget))) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& (cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) || cumTarget.equals(secondaryTarget))) {
	
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
						case ARMPITS:
						case ANUS:
							break;
						case ASS:
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
							break;
						case THIGHS:
							return Util.newArrayListOfValues(
									CoverableArea.LEGS);
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static final SexAction GENERIC_ORGASM_DOUBLE_KNOTTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.KNOTTED)) {
				return false;
			}
			
			if(Main.sex.getCharacterPerformingAction().getPenisCumStorage()==CumProduction.ZERO_NONE) {
				return false;
			}
			
			Map<GameCharacter, SexSlot> suitableSecondaryCharacters = getSuitableSecondaryCreampieTargets(Main.sex.getCharacterTargetedForSexAction(this));
			if(suitableSecondaryCharacters.isEmpty()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			if(!areaContacted.isOrifice()) {
				return false;
			}

			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			if(secondaryTarget==null) {
				return false;
			}
			
			switch((SexAreaOrifice)areaContacted) {
				case ARMPITS:
				case ASS:
				case THIGHS:
				case BREAST:
				case BREAST_CROTCH:
				case NIPPLE:
				case NIPPLE_CROTCH:
				case MOUTH:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
				case SPINNERET:
					return false;
				case ANUS:
				case VAGINA:
					if(!secondaryTarget.isOrificeTypeExposed((SexAreaOrifice) areaContacted)
							|| !((SexAreaOrifice) areaContacted).isFree(secondaryTarget)) {
						return false;
					}
					break;
			}
			
			// Cannot use if orifice is not deep enough
			// (Does not take into account willingness to fully penetrate, as the orgasming character is assumed to want to knot even if not normally willing to deeply penetrate.)
			if(Main.game.isPenetrationLimitationsEnabled()
					&& Main.sex.getCharacterPerformingAction().isFullPenetrationTooLongToFit(SexAreaPenetration.PENIS, secondaryTarget, (SexAreaOrifice)areaContacted, false)) {
				return false;
			}
			
			// Will not use if obeying the player and player asked for pull out:
			if(((Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.CREAMPIE
						&& Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())!=OrgasmBehaviour.KNOT)
					&& !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.PULL_OUT
				|| Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) { // Cannot double creampie if someone is forcing creampie.
				return false;
			}

			// If immobile, cannot move to double-creampie:
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			
			return true;
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.KNOT) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative()
					&& (!Main.sex.getCharacterTargetedForSexAction(this).isVisiblyPregnant() || !getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).isVisiblyPregnant())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.5f
//					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0)==SexAreaOrifice.VAGINA
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION))) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		
		@Override
		public String getActionTitle() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						return "Двойное анальное узлование";
					case VAGINA:
						return "Двойное узлование";
				}
			}
			return "Двойное узлование";
		}

		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryCharacterPenetrated = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			String returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
					"Вы достигли пика и больше не можете сдерживать оргазм. Отдайте [npc.name] половину своего кремпайпа, после чего быстро выходите и завязываете узел в [npc2.name], заканчивая внутри [npc2.herHim].");
			
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case MOUTH:
					case NIPPLE:
					case NIPPLE_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
					case SPINNERET:
						break;
					case ANUS:
						returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
								"Не держите узел в [npc.namePos] [npc.asshole], отдавая [npc.herHim] половину своей порции, после чего быстро выходите и вбиваете свой узел в [npc2.namePos] [npc2.asshole], завершая внутри [npc2.herHim].");
						break;
					case VAGINA:
						returnString = UtilText.parse(characterPenetrated, secondaryCharacterPenetrated,
								"Не держите узел в [npc.namePos] [npc.pussy+], отдавая [npc.herHim] половину своей порции, после чего быстро выходите и вбиваете свой узел в [npc2.namePos] [npc2.pussy+], завершая внутри [npc2.herHim].");
						break;
				}
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, false).getDescription();
		}

		@Override
		public String applyPreParsingEffects() {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			Main.sex.addCharactersKnottedTogether(Main.sex.getCharacterPerformingAction(), secondaryTarget); // Added so that the generic orgasm description parses it as a knotting action.
			return "";
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);

			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).applyEndEffects();
			
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this), areaContacted);
			Main.sex.applyOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, areaContacted, true);
			
			return Main.sex.applyPenetrationEffects(PenisVagina.PENIS_FUCKING_START, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, secondaryTarget, SexAreaOrifice.VAGINA);
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && (cumTarget.equals(characterPenetrated) || cumTarget.equals(secondaryTarget))) {
				return Util.newArrayListOfValues(areaContacted);
					
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			SexAreaInterface areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			GameCharacter secondaryTarget = getSecondaryCreampieTarget(Main.sex.getCharacterTargetedForSexAction(this), (SexAreaOrifice) areaContacted);
			
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& (cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) || cumTarget.equals(secondaryTarget))) {
	
				if(!areaContacted.isOrifice()) {
					switch((SexAreaPenetration)areaContacted) {
						case CLIT:
							break;
						case FINGER:
							return Util.newArrayListOfValues(
									CoverableArea.HANDS);
						case FOOT:
							return Util.newArrayListOfValues(
									CoverableArea.FEET);
						case PENIS:
							break;
						case TAIL:
							break;
						case TENTACLE:
							break;
						case TONGUE:
							break;
					}
				} else {
					switch((SexAreaOrifice)areaContacted) {
						case ARMPITS:
						case ANUS:
							break;
						case ASS:
							if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
								return Util.newArrayListOfValues(
										CoverableArea.ASS);
							} else {
								return Util.newArrayListOfValues(
										CoverableArea.ASS,
										CoverableArea.ANUS);
							}
						case BREAST:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS,
									CoverableArea.NIPPLES,
									CoverableArea.MOUTH);
						case BREAST_CROTCH:
							return Util.newArrayListOfValues(
									CoverableArea.BREASTS_CROTCH,
									CoverableArea.NIPPLES_CROTCH,
									CoverableArea.STOMACH,
									CoverableArea.PENIS,
									CoverableArea.VAGINA);
						case MOUTH:
							break;
						case NIPPLE:
							break;
						case NIPPLE_CROTCH:
							break;
						case THIGHS:
							return Util.newArrayListOfValues(
									CoverableArea.LEGS);
						case URETHRA_PENIS:
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
					}
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.INSIDE_SWITCH_DOUBLE, true).isEndsSex()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
		}
	};
	
	public static void applyGenericPullOutEffects(SexActionInterface sexAction, OrgasmCumTarget orgasmCumTarget) {
		if(sexAction!=null && orgasmCumTarget!=null) { // null check for external SexActions using this method via Sex.java
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(sexAction, orgasmCumTarget, true).applyEffects();
		}
		GameCharacter characterPenetrated = null;
		SexAreaInterface areaContacted = null;
		if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
			characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			areaContacted = Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			Main.sex.stopOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, characterPenetrated, areaContacted);
		}
	}
	
	public static final SexAction GENERIC_ORGASM_FLOOR = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочка на пол";
				}
				return "Выйти (на пол)";
			}
			return "Кончить на пол";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на пол.";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, false).getDescription();
		}

		@Override
		public SexActionPriority getPriority() {
			// Seemed a little random to have this behaviour...
//			if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()) {
//				return SexActionPriority.LOW; // Prefer cumming on someone if they don't dislike cumming.
//			}
			return super.getPriority();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FLOOR);
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FLOOR, false).isEndsSex();
		}
	};
	
	
	public static final SexAction GENERIC_ORGASM_WALL = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.WALL);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочить на [pc.wall]";
				}
				return "Вытащить ([pc.wall])";
			}
			return "Кончить на [pc.wall]";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на [pc.wall].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.WALL);
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.WALL, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_ASS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.ASS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочить на задницу";
				}
				return "Вытащить (задница)";
			}
			return "Кончить на задницу";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на [npc2.namePos] [npc2.ass+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.ASS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ASS);
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.ASS,
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				} else {
					return Util.newArrayListOfValues(
							CoverableArea.ASS,
							CoverableArea.ANUS);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ASS, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_GROIN = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочить на пах";
				}
				return "Вытащить (пах)";
			}
			return "Кончить на пах";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте струю спермы на пах [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.GROIN);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return Util.newArrayListOfValues(
							CoverableArea.THIGHS);
					
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_GROIN = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_GROIN);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Мастурбация (свой пах)";
				}
				return "Вытащить (свой пах)";
			}
			return "Кончить на свой пах";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте струю спермы на свой пах.";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_GROIN);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				if(cumTarget.getGenitalArrangement()==GenitalArrangement.CLOACA) {
					return Util.newArrayListOfValues(
							CoverableArea.ANUS,
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				} else if(cumTarget.getGenitalArrangement()==GenitalArrangement.NORMAL) {
					return Util.newArrayListOfValues(
							CoverableArea.PENIS,
							CoverableArea.VAGINA);
				}
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_GROIN, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_BREASTS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			String breasts = "breasts";
			if(!Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				breasts = "chest";
			}
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Мастурбация на "+breasts;
				}
				return "Вытащить ("+breasts+")";
			}
			return "Кончить на "+breasts;
		}

		@Override
		public String getActionDescription() {
			return "Ты достиг пика и больше не можешь сдерживать оргазм. Направь свою сперму на [npc2.namePos] [npc2.breasts].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.BREASTS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BREASTS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_BREASTS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_BREASTS);
		}
		
		@Override
		public String getActionTitle() {
			String breasts = "breasts";
			if(!Main.sex.getCharacterPerformingAction().hasBreasts()) {
				breasts = "chest";
			}
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочка (своей "+breasts+")";
				}
				return "Кончить внутрь (своей "+breasts+")";
			}
			return "Кончить на себя "+breasts;
		}

		@Override
		public String getActionDescription() {
			return "Ты достиг пика и больше не можешь сдерживать оргазм. Направь свою сперму на свой [npc.breasts+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_BREASTS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.NIPPLES,
						CoverableArea.BREASTS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_BREASTS, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_FACE = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочка на лицо";
				}
				return "Кончить на лицо";
			}
			return "Лицо";
		}

		@Override
		public String getActionDescription() {
			return "Ты достиг пика и больше не можешь сдерживать оргазм. Направь свою сперму на лицо [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FACE);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FACE, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FACE = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_FACE);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочка (на свое лицо)";
				}
				return "Кончить (на свое лицо)";
			}
			return "Кончить на свое лицо";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на свой [npc.face+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_FACE);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.MOUTH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FACE, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_HANDS = new SexAction(GENERIC_ORGASM_FLOOR) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_HANDS);
		}

		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочка (собственными руками)";
				}
				return "Вытащить (собственными руками)";
			}
			return "Кончить на свои руки";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на свой [npc.hands].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_HANDS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_HANDS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.HANDS);
			}
			return null;
		}

		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_HANDS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_HAIR = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.HAIR);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочка в [npc2.hair(true)]";
				}
				return "Вытащить ([npc2.hair(true)])";
			}
			return "Кончить в [npc2.hair(true)]";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму в [npc2.namePos] [npc2.hair(true)].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.HAIR);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.HAIR);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.HAIR, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_STOMACH = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочить на живот";
				}
				return "Вытащить (на живот)";
			}
			return "Кончить на живот";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою струю на живот [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.STOMACH);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.STOMACH, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_SELF_STOMACH = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_STOMACH);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочить на свой живот";
				}
				return "Вытащить (на свой живот)";
			}
			return "Кончить на свой живот";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою струю на свой живот.";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_STOMACH);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.STOMACH);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_STOMACH, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_LEGS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Дрочить на [npc2.legs]";
				}
				return "Вытащить ([npc2.legs])";
			}
			return "Кончить на [npc2.legs]";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на [npc2.namePos] [npc2.legs].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.LEGS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.LEGS, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_LEGS = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_LEGS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Мастурбация (своим [npc.legs])";
				}
				return "Вытащить (свой [npc.legs])";
			}
			return "Кончить на свой [npc.legs]";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на свой [npc.legs+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_LEGS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.LEGS,
						CoverableArea.THIGHS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_LEGS, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()).hasFeet() && isCumTargetRequirementsMet(this, OrgasmCumTarget.FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Мастурбация на [npc2.feet]";
				}
				return "Вытащить ([npc2.feet])";
			}
			return "Кончить на [npc2.feet]";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на [npc2.namePos] [npc2.feet+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.FEET);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.FEET, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_SELF_FEET = new SexAction(GENERIC_ORGASM_FLOOR) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasFeet() && isCumTargetRequirementsMet(this, OrgasmCumTarget.SELF_FEET);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Мастурбация (собственный [npc.feet])";
				}
				return "Вытащить (собственный [npc.feet])";
			}
			return "Кончить на собственный [npc.feet]";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на свой [npc.feet+].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.SELF_FEET);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumProvider.equals(cumTarget)) {
				return Util.newArrayListOfValues(
						CoverableArea.FEET);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.SELF_FEET, false).isEndsSex();
		}
	};

	public static final SexAction GENERIC_ORGASM_BACK = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return isCumTargetRequirementsMet(this, OrgasmCumTarget.BACK);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Мастурбация на спину";
				}
				return "Вытащить (спина)";
			}
			return "Кончить на спину";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на [npc2.namePos] спину.";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.BACK);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.BACK);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.BACK, false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_ARMPITS = new SexAction(GENERIC_ORGASM_FLOOR) {
		@Override
		public SexParticipantType getParticipantType() {
			return SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.isArmpitContentEnabled() && isCumTargetRequirementsMet(this, OrgasmCumTarget.ARMPITS);
		}
		
		@Override
		public String getActionTitle() {
			if(!Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				if(!Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, SexAreaPenetration.FINGER).isEmpty()) {
					return "Мастурбация на подмышку";
				}
				return "Вытащить (подмышка)";
			}
			return "Кончить на подмышку";
		}

		@Override
		public String getActionDescription() {
			return "Вы достигли пика и больше не можете сдерживать оргазм. Направьте свою сперму на подмышку [npc2.namePos].";
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ARMPITS, false).getDescription();
		}

		@Override
		public void applyEffects() {
			applyGenericPullOutEffects(this, OrgasmCumTarget.ARMPITS);
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))) {
				return Util.newArrayListOfValues(
						CoverableArea.ARMPITS);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.ARMPITS, false).isEndsSex();
		}
	};
	
	
	
	// PREPARATIONS:

	public static final SexAction GENERIC_PREPARATION_PREPARE_IMMOBILE_SILENCE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return isPerformingCharacterTotallyImmobilised();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().isAsleep()) {
				return "Так хочется спать...";
			} else {
				return "Замереть";
			}
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isAsleep()) {
				return "[npc2.NameIsFull] стремительно приближается к [npc2.her] оргазму, но вы настолько сонны, что даже [npc2.her] кульминация вряд ли сможет вас разбудить...";
			} else {
				return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Оставайтесь неподвижными и не реагируйте на это.";
			}
		}
		@Override
		public String getDescription() {
			String description = "";
			if(Main.sex.getCharacterPerformingAction().isAsleep()) {
				description = "Все еще крепко спящий, [npc.name] [npc.verb(show)] без малейшего признака пробуждения, пока [npc2.name] [npc2.verb(prepare)] к достижению [npc2.her] кульминации.";
			} else {
				description = "Продолжая вести себя как неодушевленная секс-кукла, [npc.name] [npc.verb(remain)] тихо и совершенно неподвижно, хотя [npc.she] может сказать, что [npc2.nameIsFull] вот-вот кончит.";
			}
			GameCharacter orgasming = Main.sex.getCharacterOrgasming();
			if(orgasming==null) {
				orgasming = Main.sex.getCharacterTargetedForSexAction(this);
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), orgasming, description);
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& !isPerformingCharacterTotallyImmobilised();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}
		@Override
		public String getActionTitle() {
			return "Приготовиться";
		}
		@Override
		public String getActionDescription() {
			return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Приготовьтесь к нему.";
		}
		@Override
		public String getDescription() {
			String description = "";
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					description = "[npc.Name] [npc.verb(let)] нежный [npc.moan] ободрения, пока [npc.she] [npc.verb(prepare)], чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
					break;
				case DOM_NORMAL:
					description = "[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
					break;
				case DOM_ROUGH:
					description = "[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
					break;
				case SUB_EAGER:
					description = "[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
					break;
				case SUB_NORMAL:
					description = "[npc.Name] [npc.verb(let)] [npc.a_moan+], пока [npc.she] [npc.verb(prepare)], чтобы [npc2.name] достиг(ла) [npc2.her] оргазма.";
					break;
				case SUB_RESISTING:
					if(Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
						description = "Не довольный тем, что видит [npc.sheIs], [npc.name] [npc.verb(let)] [npc.a_moan+] как [npc.she] [npc.verb(realise)], что [npc2.nameIs] вот-вот кончит.";
					} else {
						description = "[npc.Name] [npc.verb(let)] [npc.a_moan+] как [npc.she] отчаянно [npc.verb(try)], пытаясь отстраниться от [npc2.name], прежде чем [npc2.she] [npc2.verb(orgasm)].";
					}
					break;
			}
			GameCharacter orgasming = Main.sex.getCharacterOrgasming();
			if(orgasming==null) {
				orgasming = Main.sex.getCharacterTargetedForSexAction(this);
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), orgasming, description);
		}
	};
	
	private static boolean isAreaFuckedByTarget(SexAction sexAction, GameCharacter characterFucked, SexAreaInterface areaFucked) {
		return Main.sex.getAllOngoingSexAreas(characterFucked, areaFucked).contains(SexAreaPenetration.PENIS)
				&& Main.sex.getCharacterOngoingSexArea(characterFucked, areaFucked).contains(Main.sex.getCharacterTargetedForSexAction(sexAction));
	}
	
	private static boolean isSpecialCreampieLockConditionMet(SexAction sexAction, GameCharacter characterProvidingCreampie, GameCharacter characterReceivingCreampie, SexAreaInterface areaFucked) {
		//Do not allow if sex manager has special pull out conditions:
		if(Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(characterProvidingCreampie)==OrgasmBehaviour.PULL_OUT) {
			return false;
		}
		if(!Main.sex.getInitialSexManager().isForceCreampieAllowed(characterProvidingCreampie, characterReceivingCreampie)) {
			return false;
		}
		if(Main.sex.isCharacterImmobilised(characterReceivingCreampie)) {
			return false;
		}
		if(areaFucked==SexAreaOrifice.MOUTH) {
			return (PenisMouth.getPrimaryBlowjobPerformer(characterProvidingCreampie).equals(characterReceivingCreampie));
		}
		if(areaFucked==SexAreaOrifice.VAGINA) {
			if(!characterReceivingCreampie.isPlayer() && characterReceivingCreampie.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
				return false;
			}
		}
		return true;
	}
	
	public static final SexAction GENERIC_PREPARATION_ASK_FOR_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Продолжать трахать";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "Попросить кончить";

			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "Попросить кончить на [npc.breasts]";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "Попросить кончить на [npc.feet]";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
				return "Попросить кончить на [npc.armpit]";
				
			} else {
				return "Попросить кремпи";
			}
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Вы чувствуете, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Попросите [npc2.herHim] продолжать трахать вас [npc2.her] дилдо, пока [npc2.she] кончает.";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
				return "Вы чувствуете, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Попросите [npc2.herHim] наполнить ваш живот [npc2.her] спермой.";

			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
				return "Ты чувствуешь, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Попроси [npc2.herHim] кончить прямо на твою [pc.breasts+].";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
				return "Ты чувствуешь, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Попроси [npc2.herHim] кончить прямо на твою [pc.feet+].";
				
			} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
				return "Ты чувствуешь, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Попроси [npc2.herHim] кончить прямо на твою [pc.armpit+].";
				
			} else {
				return "Ты чувствуешь, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Попроси [npc2.herHim] наполнить тебя его [npc2.her] спермой.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised()
					&& !isTargetedCharacterTotallyImmobilised(this);
		}

		@Override
		public SexActionPriority getPriority() {
			if(getCharacterBeingFucked()==Main.sex.getCharacterPerformingAction()) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				switch(behaviour) {
					case CREAMPIE:
						return SexActionPriority.UNIQUE_MAX;
					case DEFAULT:
					case KNOT:
						break;
					case NO_ENCOURAGE:
					case PULL_OUT:
						return SexActionPriority.LOW;
				}
			}
			
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.NORMAL;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to keep");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" вколачивать в [npc.her] [npc.pussy+], пока [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" вколачивать в [npc.her] [npc.asshole+], пока [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" вколачивать в [npc.her] [npc.nipple+], пока [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" вколачивать в [npc.her] [npc.spinneret+], пока [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" трахать [npc.her] [npc.breasts+], пока [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" трахать [npc.her] [npc.armpit+], когда [npc2.she] [npc2.verb(orgasm)].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" трахать [npc.her] [npc.feet+], когда [npc2.she] [npc2.verb(orgasm)].");
						
					} else {
						sb.append(" трахать [npc.herHim] как [npc2.she] [npc2.verb(orgasm)].");
					}
	
				} else {
					sb.append("Хотя [npc.name] не может говорить, она [npc.verb(manage)], издавая череду умоляющих стонов, чтобы передать, что [npc.she] [npc.verb(want)]");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc2.name] кончает в [npc.her] [npc.pussy+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc2.name] кончает в [npc.her] [npc.asshole+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc2.name] кончает в [npc.her] [npc.nipple+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc2.name] кончает в [npc.her] [npc.spinneret+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" [npc2.name] кончает на всем [npc.her] [npc.breasts+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" [npc2.name] кончает на всем [npc.her] [npc.feet+].");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" [npc2.name] кончает на [npc.her] [npc.armpit+].");
						
					} else {
						sb.append(" [npc2.namePos] кончает.");
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("Сквозь [npc.her] отчаянных стонов и похотливых криков, [npc.name] каким-то образом [npc.verb(manage)] сформулировать предложение и [npc.verb(cry)] для [npc2.name], чтобы та могла наслаждаться ими вечно");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" вколачивать в [npc.her] [npc.pussy+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Бля! Да! Трахни меня еще!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" вколачивать в [npc.her] [npc.asshole+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Бля! Да! Трахни мой зад!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" вколачивать в [npc.her] [npc.nipple+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Бля! Да! Трахни мой [npc.nipple(true)]!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" вколачивать в [npc.her] [npc.spinneret+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Бля! Да! Трахни мой паучий аппарат!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" трахать [npc.her] [npc.breasts+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Бля! Да! Трахни мои сиськи!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" трахать [npc.her] [npc.feet+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Бля! Да! Трахни мою [npc.feet]!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" трахать [npc.her] [npc.armpit+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Бля! Да! Трахни мою [npc.armpit]!)]");
						
					} else {
						sb.append(" трахая [npc.herHim], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Блядь! Да! Трахни меня еще!)]");
					}
	
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом [npc.verb(manage)] составить предложение и [npc.verb(cry)], чтобы [npc2.name] кончил,");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc.speech(Кончай в меня, если хочешь,"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.speech(Кончай в меня, если хочешь,"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.speech(Кончай в меня, если хочешь,"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.speech(Кончай в меня, если хочешь,"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
	
					} else {
						sb.append(" [npc.speech(Да! Кончай для меня!"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, пока [npc.she] [npc.verb(cry)] изливает [npc2.namePos] сперму,");
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(((Main.sex.getCharacterPerformingAction().isVisiblyPregnant() || !Main.sex.getCharacterPerformingAction().isImpregnationPhysicallyPossible())
										?" [npc.speech(Блядь! Кончай в меня!"+(petName?", [#npc.getPetName(npc2)]":"")+"Мне нужна твоя сперма!)]"
										:" [npc.speech(Оплодотвори меня!"+(petName?", [#npc.getPetName(npc2)]":"")+"Кончай в меня! Мне нужна твоя сперма!)]"));
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.speech(Блядь! Кончай в меня!"+(petName?", [#npc.getPetName(npc2)]":"")+"Мне нужна твоя сперма!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.speech(Блядь! Кончай в меня!"+(petName?", [#npc.getPetName(npc2)]":"")+"Мне нужна твоя сперма!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.speech(Блядь! Кончай в меня!"+(petName?", [#npc.getPetName(npc2)]":"")+"Мне нужна твоя сперма!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						sb.append(" [npc.speech(Да! Кончай для меня!"+(petName?", [#npc.getPetName(npc2)]":"")+"! Закрой мои сиськи своей кончой!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						sb.append(" [npc.speech(Бля! Да! Лей всю свою кончу на мои [npc.feet]"+(petName?", [#npc.getPetName(npc2)]":"")+"!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
						sb.append(" [npc.speech(Кончай для меня"+(petName?", [#npc.getPetName(npc2)]":"")+"! Я хочу попробовать твою сперму на вкус!)]");
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						sb.append(" [npc.speech(Бля! Да! Лей всю свою кончу на мои [npc.armpit]"+(petName?", [#npc.getPetName(npc2)]":"")+"!)]");
						
					} else {
						sb.append(" [npc.speech(Кончай для меня"+(petName?", [#npc.getPetName(npc2)]":"")+"! Не вынимай!)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] положительный [npc2.moan], чтобы дать [npc.name] понять, что это именно то, что [npc2.sheHasFull] планировал.");
						
					} else {
						sb.append("<br/><br/>Нахмурив [npc2.her] брови, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] отрицательный [npc2.moan], чтобы дать [npc.name] понять, что [npc2.sheIs] не заинтересован в том, чтобы слушать всё, что [npc.sheHasFull] скажет.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] быстро отвечает утвердительно, ",
									"Издавая [npc.a_moan+] после того, как услышала, что именно [npc.nameIs] просит у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Конечно, если ты так хочешь!)]",
								"[npc2.speech(Мне это нравится!)]",
								"[npc2.speech(Без проблем! Я получу от этого удовольствие!)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Нахмурив брови [npc2.her], пока [npc.name] [npc.verb(ask)] это, [npc2.name] быстро [npc2.verb(shut)] [npc.herHim] вниз, ",
									"Явно не в восторге от того, что [npc.nameIs] просит чего-то у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Я буду делать всё, что мне вздумается!)]",
								"[npc2.speech(Я тебя не слушаю!)]",
								"[npc2.speech(Буду делать то, что хочу!)]"));
					}
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingCreampie().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterPerformingAction()))
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(Main.sex.getCharacterPerformingAction()))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
				} else {
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ARMPIT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ARMPIT_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	

	public static final SexAction GENERIC_PREPARATION_ASK_FOR_KNOT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			return "Попросить узел";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Попроси [npc2.herHim] завязать узел и наполнить тебя [npc2.her] спермой.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisModifier(PenetrationModifier.KNOTTED)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()))
					&& !isPerformingCharacterTotallyImmobilised()
					&& !isTargetedCharacterTotallyImmobilised(this);
		}

		@Override
		public SexActionPriority getPriority() {
			if(getCharacterBeingFucked()==Main.sex.getCharacterPerformingAction()) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				switch(behaviour) {
					case KNOT:
						return SexActionPriority.UNIQUE_MAX;
					case DEFAULT:
					case CREAMPIE:
						break;
					case NO_ENCOURAGE:
					case PULL_OUT:
						return SexActionPriority.LOW;
				}
			}
			
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.NORMAL;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				sb.append("Хотя [npc.name] не может говорить, [npc.verb(manage)] издает серию умоляющих стонов, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] завязать [npc.herHim] и кончить глубоко внутри");
				
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					sb.append(" [npc.her] [npc.pussy+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					sb.append(" [npc.her] [npc.asshole+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					sb.append(" [npc.her] [npc.nipple+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
					sb.append(" [npc.her] [npc.nipple+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					sb.append(" [npc.her] [npc.spinneret+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					sb.append(" [npc.her] [npc.urethraPenis+].");
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					sb.append(" [npc.her] [npc.urethraVagina+].");
					
				} else {
					sb.append(" of [npc.herHim]");
				}
				
			} else {
				if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					sb.append("Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом [npc.verb(manage)] сформулировать фразу и [npc.verb(cry)] попросить [npc2.name] завязать узел в [npc.herHim] и кончить глубоко внутри");
					
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc.her] [npc.pussy+], [npc.speech(Завяжи мне"+(petName?", [#npc.getPetName(npc2)]":"")+"узел! Кончи в мою киску и наполни этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.her] [npc.asshole+], [npc.speech(Завяжи мне"+(petName?", [#npc.getPetName(npc2)]":"")+"узел! Кончи в мою задницу и наполни этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.her] [npc.nipple+], [npc.speech(Завяжи мне"+(petName?", [#npc.getPetName(npc2)]":"")+"узел! Кончи в мой сосок и наполни этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
						sb.append(" [npc.her] [npc.nippleCrotch+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня и наполни этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.her] [npc.spinneret+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня и наполни этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						sb.append(" [npc.her] [npc.urethraPenis+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня и наполни этот презерватив!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						sb.append(" [npc.her] [npc.urethraVagina+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня и наполни этот презерватив!)]");
	
					} else {
						sb.append(" [npc.herHim], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня и наполни этот презерватив!)]");
					}
					
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}
					
					sb.append("Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом [npc.verb(manage)] сформулировать фразу и [npc.verb(cry)] попросить [npc2.name] завязать узел в [npc.herHim] и кончить глубоко внутри");
					
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						sb.append(" [npc.her] [npc.pussy+],"
									+(Main.sex.getCharacterPerformingAction().isVisiblyPregnant()
											?" [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня и заполни мою киску своей спермой!)]"
											:" [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! заполни мою киску своей спермой и осемени меня!)]"));
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						sb.append(" [npc.her] [npc.asshole+], [npc.speech(Завяжи мне"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи мне в задницу!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						sb.append(" [npc.her] [npc.nipple+], [npc.speech(Завяжи мне"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи мне в сосок!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE_CROTCH)) {
						sb.append(" [npc.her] [npc.nippleCrotch+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						sb.append(" [npc.her] [npc.spinneret+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						sb.append(" [npc.her] [npc.urethraPenis+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						sb.append(" [npc.her] [npc.urethraVagina+], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня!)]");
	
					} else {
						sb.append(" [npc.herHim], [npc.speech(Завяжи меня,"+(petName?", [#npc.getPetName(npc2)]":"")+"! кончи в меня!)]");
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] положительный [npc2.moan], чтобы дать [npc.name] понять, что это именно то, что [npc2.sheHasFull] планировал.");
						
					} else {
						sb.append("<br/><br/>Нахмурив [npc2.her] брови, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] отрицательный [npc2.moan], чтобы дать [npc.name] понять, что [npc2.sheIs] не заинтересован в том, чтобы слушать всё, что [npc.sheHasFull] скажет.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] быстро отвечает утвердительно, ",
									"Издавая [npc.a_moan+] после того, как услышала, что именно [npc.nameIs] просит у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Конечно, если ты так хочешь!)]",
								"[npc2.speech(Мне это нравится!)]",
								"[npc2.speech(Без проблем! Я получу от этого удовольствие!)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Нахмурив брови [npc2.her], пока [npc.name] [npc.verb(ask)] это, [npc2.name] быстро [npc2.verb(shut)] [npc.herHim] вниз, ",
									"Явно не в восторге от того, что [npc.nameIs] просит чего-то у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Я буду делать всё, что мне вздумается!)]",
								"[npc2.speech(Я тебя не слушаю!)]",
								"[npc2.speech(Буду делать то, что хочу!)]"));
					}
				}
			}
			
			return sb.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingKnot().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterPerformingAction()))
					|| (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(SexAreaPenetration.PENIS)
							&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST).contains(Main.sex.getCharacterPerformingAction()))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
				} else {
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ARMPIT_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ARMPIT_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	private static void applyBasePenisOrgasmRequestsReset() {
		Main.sex.getCharactersRequestingCreampie().remove(Main.sex.getCharacterPerformingAction());
		Main.sex.getCharactersRequestingKnot().remove(Main.sex.getCharacterPerformingAction());
		Main.sex.getCharactersRequestingPullout().remove(Main.sex.getCharacterPerformingAction());
	}
	
	private static SexActionPriority getBaseForceCreampiePriority(SexActionInterface sexAction) {
		if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
				&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(sexAction)))) {
			if(!Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
					return SexActionPriority.HIGH;
				}
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					return SexActionPriority.LOW;
				}
			}
		}
		
		if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
			return SexActionPriority.HIGH;
			
		} else {
			return SexActionPriority.LOW;
		}
	}
	
	private static String getForcedCreampieSpeech(SexAction sexAction) {
		boolean knowsName = (!Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getCharacterPerformingAction().isPlayerKnowsName())
							|| (!Main.sex.getCharacterTargetedForSexAction(sexAction).isPlayer() && Main.sex.getCharacterTargetedForSexAction(sexAction).isPlayerKnowsName());
		
		boolean performerSpeechMuffled = Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute();
		
		if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(sexAction))) {
			if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
					return (performerSpeechMuffled
								?" Рот [npc.her] забит, и [npc.sheIs] лишь издает приглушенный [npc.moan], пока [npc.she] [npc.verb(prepare)], чтобы получить [npc.her] креампи."
								:" С истерическим визгом [npc.she] [npc.verb(cry)], [npc.speechNoExtraEffects(~Аах!~ Теперь ты в моей власти! Кончай в меня"+(knowsName?", [npc2.name]":"")+"! ~Ооо!~ ~Да!~ Дай мне своих деток!)]");
				}
				return (performerSpeechMuffled
								?" Рот [npc.her] забит, и [npc.sheIs] лишь издает приглушенный [npc.moan], пока [npc.she] [npc.verb(prepare)], чтобы получить [npc.her] креампи."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)], [npc.speechNoExtraEffects(~Ооо!~ ~Да!~ Кончай в мою киску"+(knowsName?", [npc2.name]":"")+"! ~Аах!~ Дай мне сочный, большой креампи!)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (performerSpeechMuffled
								?" Рот [npc.her] забит, и [npc.sheIs] лишь издает приглушенный [npc.moan], пока [npc.she] [npc.verb(prepare)], чтобы получить [npc.her] креампи."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)], [npc.speechNoExtraEffects(~Ооо!~ ~Да!~ Кончай в мою задницу"+(knowsName?", [npc2.name]":"")+"! ~Аах!~ Дай мне сочный, большой креампи!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
				return (performerSpeechMuffled
							?" Рот [npc.her] забит, и [npc.sheIs] лишь издает приглушенный [npc.moan], пока [npc.she] [npc.verb(prepare)], чтобы получить [npc.her] креампи."
							:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)] наружу, [npc.speechNoExtraEffects(~Ооо!~ ~Да!~ Кончай в мой паутинный орган,"+(knowsName?", [npc2.name]":"")+"! ~Аах!~ Дай мне сочный, большой креампи!)]");
		
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (performerSpeechMuffled
								?" Рот [npc.her] забит, и [npc.sheIs] лишь издает приглушенный [npc.moan], пока [npc.she] [npc.verb(prepare)], чтобы получить [npc.her] креампи."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)] наружу, [npc.speechNoExtraEffects(~Ооо!~ ~Да!~ Кончай в меня,"+(knowsName?", [npc2.name]":"")+"! ~Ааах!~ Наполни мои яйца своей спермой!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (performerSpeechMuffled
								?" Рот [npc.her] забит, и [npc.sheIs] лишь издает приглушенный [npc.moan], пока [npc.she] [npc.verb(prepare)], чтобы получить [npc.her] креампи."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)] наружу, [npc.speechNoExtraEffects(~Ооо!~ ~Да!~ Кончай в меня,"+(knowsName?", [npc2.name]":"")+"! ~Аах!~ Дай мне сочный, большой креампи!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
				return (performerSpeechMuffled
							?" Пока рот [npc.her] заблокирован, [npc.sheIs] может лишь издавать приглушенное [npc.moan], пока [npc.she] [npc.verb(prepare)], чтобы [npc2.name] кончил на весь [npc.her] член."
							:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)] наружу, [npc.speechNoExtraEffects(Вот так! ~Ммм!~ Кончай на весь мой член,"+(knowsName?", [npc2.name]":"")+"!)]");
		
			}
			
		} else { // Dildo:
			if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
				return (performerSpeechMuffled
								?" Пока рот [npc.her] заблокирован, [npc.sheIs] может лишь издавать приглушенное [npc.moan], пока [npc.she] [npc.verb(feel)], и [npc.herself] наполняется [npc2.namePos] игрушкой."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)] наружу, [npc.speechNoExtraEffects(~Ооо!~ ~Да!~ Наполни мою киску этой игрушкой,"+(knowsName?", [npc2.name]":"")+"!)]");

			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
				return (performerSpeechMuffled
								?" Пока рот [npc.her] заблокирован, [npc.sheIs] может лишь издавать приглушенное [npc.moan], пока [npc.she] [npc.verb(feel)], и [npc.herself] наполняется [npc2.namePos] игрушкой."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)] наружу, [npc.speechNoExtraEffects(~Ооо!~ ~Да!~ Наполни мою задницу этой игрушкой"+(knowsName?", [npc2.name]":"")+"!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
				return (performerSpeechMuffled
						?" Пока рот [npc.her] заблокирован, [npc.sheIs] может лишь издавать приглушенное [npc.moan], пока [npc.she] [npc.verb(feel)], и [npc.herself] наполняется [npc2.namePos] игрушкой."
						:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)], [npc.speechNoExtraEffects(~Ох!~ ~Да!~ Наполни мою паутину этой игрушкой"+(knowsName?", [npc2.name]":"")+"!)]");
		
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
				return (performerSpeechMuffled
								?" Пока рот [npc.her] заблокирован, [npc.sheIs] может лишь издавать приглушенное [npc.moan], пока [npc.she] [npc.verb(feel)], и [npc.herself] наполняется [npc2.namePos] игрушкой."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)], [npc.speechNoExtraEffects(~Ох!~ ~Да!~ Наполни мой член этой игрушкой"+(knowsName?", [npc2.name]":"")+"!)]");
				
			} else if(isAreaFuckedByTarget(sexAction, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
				return (performerSpeechMuffled
								?" Пока рот [npc.her] заблокирован, [npc.sheIs] может лишь издавать приглушенное [npc.moan], пока [npc.she] [npc.verb(feel)], и [npc.herself] наполняется [npc2.namePos] игрушкой."
								:" С отчаянным [npc.moan], [npc.she] [npc.verb(cry)], [npc.speechNoExtraEffects(~Ох!~ ~Да!~ Наполни меня этой игрушкой"+(knowsName?", [npc2.name]":"")+"!)]");
			}
		}
		
		return "";
	}
	
	
	public static final SexAction GENERIC_PREPARATION_FORCE_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		private boolean isFrottingOrgasm() {
			return isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterTargetedForSexAction(this)).contains(SexAreaPenetration.PENIS);
		}
		
		@Override
		public String getActionTitle() {
			if(isFrottingOrgasm()) {
				return "Принудительный оргазм от фроттинга";
			}
			return "Принудительный кримпай";
		}

		@Override
		public String getActionDescription() {
			if(isFrottingOrgasm()) {
				return "Вы чувствуете, что [npc2.name] близок к [npc2.her] оргазму. Используйте свое выгодное положение, чтобы заставить [npc2.herHim] кончить прямо на вашу [pc.cock+].";
			}
			return "Вы чувствуете, что [npc2.name] близок к [npc2.her] оргазму. Используйте свое выгодное положение, чтобы заставить [npc2.herHim] кончить внутрь вас.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean frottingOrgasm = isFrottingOrgasm();
			
			boolean allowed = (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas)
						|| frottingOrgasm)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Torso.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
			
//			System.out.println(allowed+":");
//			System.out.println("1: "+(Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()));
//			
//			try {
//				System.out.println("2: "+isSpecialCreampieLockConditionMet(
//						this,
//						Main.sex.getCharacterTargetedForSexAction(this),
//						Main.sex.getCharacterPerformingAction(),
//						Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0)));
//	
//				System.out.println("3: "+Main.sex.getPosition().isForcedCreampieEnabled(
//						Torso.class,
//						Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
//						Main.sex.getCharacterPerformingAction(),
//						Main.sex.getCharacterTargetedForSexAction(this)));
//			} catch(Exception ex) {
//			}
//			System.out.println("4: "+(Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING));
			
			return allowed;
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы заставить [npc2.namePos] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы заставить [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы заставить [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы загнать [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы загнать [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы загнать [npc2.her] [npc2.cock+] глубоко в паутину [npc.her]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы загнать [npc2.her] [npc2.cock+] глубоко в [npc.her] горло. С отчаянным, булькающим [npc.moan], [npc.she] [npc.verb(prepare)] проглотить всю сперму, которая вот-вот хлынет в [npc.herHim].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы с силой прижать собственный [npc.her] [npc.cock+] к [npc2.hers]."
							+ getForcedCreampieSpeech(this);
				}
				return "Ошибка: принудительный кримпэй не учтен. Пожалуйста, сообщите Инноксии!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы заставить [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы заставить [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы загнать [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы загнать [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] выгодное положение, чтобы загнать [npc2.her] [npc2.cock+] глубоко в паутину [npc.her]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] [npc.verb(use)] [npc.her] в выгодную позицию, чтобы заставить [npc2.her] [npc2.cock+] глубоко в [npc.her] горло.";
				}
				return "Ошибка: область принудительного кримплея не учтена. Пожалуйста, сообщите Инноксии!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Torso.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_HUG_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Объятия-замок";
		}

		@Override
		public String getActionDescription() {
			return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Быстро обхватите свои [npc.arms] вокруг поясницы [npc2.her] и, крепко прижимая [npc2.herHim] к себе, заставьте [npc2.herHim] кончить внутрь вас.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Main.sex.getCharacterPerformingAction().isArmMovementHindered()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FINGER)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Arm.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг поясницы [npc2.her] и, крепко прижимая [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг поясницы [npc2.her] и, крепко прижимая [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг поясницы [npc2.her] и, крепко прижимая [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный орган [npc.her]."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в глотку [npc.her]. С отчаянным, булькающим [npc.moan], [npc.she] [npc.verb(prepare)] проглотить всю сперму, которая вот-вот хлынет внутрь [npc.herHim].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] против [npc.hers]."
							+ getForcedCreampieSpeech(this);
				}
				return "Ошибка: область фиксации объятиями не учтена. Пожалуйста, сообщите Innoxia!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг поясницы [npc2.her] и, крепко прижимая [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг поясницы [npc2.her] и, крепко прижимая [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный орган [npc.her]."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.arms] вокруг [npc2.her] поясницы и, крепко обхватив [npc2.herHim], [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] горло.";
				}
				return "Ошибка: область фиксации объятиями не учтена. Пожалуйста, сообщите Innoxia!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Arm.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_LEG_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Замок ногами";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.her] оргазм [npc2.name] уже совсем близко. Быстро обхвати [npc2.herHim] своими [npc.legs] и заставь [npc2.herHim] кончить в тебя.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& !Main.sex.getCharacterPerformingAction().isLegMovementHindered()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)>=2
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Leg.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко войти в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко войти в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в паутинный аппарат [npc.her]."
							+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, прежде чем издать отчаянный, приглушенный [npc.moan].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, вдавливая [npc2.her] [npc2.cock+] в [npc.hers]."
							+ getForcedCreampieSpeech(this);
				}
				return "Ошибка: область захвата ног не учтена. Пожалуйста, сообщите Инноксии!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
								+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, заставляя [npc2.her] [npc2.cock+] глубоко в [npc.her] паучье веретено."
							+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) { // This shouldn't really ever be encountered:
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.legs] вокруг [npc2.her] поясницы, прежде чем издать отчаянный, приглушенный [npc.moan].";
				}
				return "Ошибка: область захвата ног не учтена. Пожалуйста, сообщите Инноксии!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Leg.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
			} 
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_TAIL_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Захват хвостом";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Быстро обхвати своей цепкой [npc.tail] [npc2.her] поясницу и, с силой притягивая [npc2.herHim] к себе, заставляешь [npc2.herHim] кончить прямо в тебя.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasTail()
					&& Main.sex.getCharacterPerformingAction().getTailType().isPrehensile()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Tail.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру члена [npc.her]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный аппарат [npc.her]."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] горло. С отчаянным, булькающим [npc.moan], [npc.she] [npc.verb(prepare)] проглотить всю сперму, которая вот-вот хлынет внутрь [npc.herHim].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] на [npc.hers]."
							+ getForcedCreampieSpeech(this);
				}
				return "Ошибка: область фиксации хвоста не учтена. Пожалуйста, сообщите Иннокси!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру члена [npc.her]."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный аппарат [npc.her]."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tail] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] горло.";
				}
				return "Ошибка: область фиксации хвоста не учтена. Пожалуйста, сообщите Иннокси!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Tail.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_WING_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Захват крыльями";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Быстро обхвати [npc2.her] своим [npc.wingSize] [npc.wings] и, крепко притянув [npc2.herHim] к себе, заставь [npc2.herHim] кончить в тебя.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasWings()
					&& Main.sex.getCharacterPerformingAction().getWingSize().getValue()>=WingSize.THREE_LARGE.getValue()
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Wing.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный аппарат [npc.her]."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в глотку [npc.her]. С отчаянным, булькающим [npc.moan], [npc.she] [npc.verb(prepare)] проглотить всю сперму, которая вот-вот хлынет внутрь [npc.herHim].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] против [npc.hers]."
							+ getForcedCreampieSpeech(this);
				}
				return "Ошибка: область фиксации хвоста не учтена. Пожалуйста, сообщите Иннокси!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный аппарат [npc.her]."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.wingSize] [npc.wings] вокруг [npc2.her] тела, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] горло.";
				}
				return "Ошибка: область фиксации хвоста не учтена. Пожалуйста, сообщите Иннокси!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Wing.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_TENTACLE_LOCK = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Захват щупальцами";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Быстро обхвати своими [npc.tentacles] нижнюю часть спины [npc2.her] и, с силой притянув [npc2.herHim] к себе, заставь [npc2.herHim] кончить в тебя.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericGroinForceCreampieAreas)
						|| isTakingCockInOrifice(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), AbstractSexPosition.genericFaceForceCreampieAreas))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.ONGOING_ONLY.getValue()
					&& Main.sex.getCharacterPerformingAction().hasTentacle()
					&& Main.sex.getPenetrationTypeFreeCount(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TENTACLE)>=1
					&& isSpecialCreampieLockConditionMet(
							this,
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0))
					&& Main.sex.getPosition().isForcedCreampieEnabled(
							Tentacle.class,
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0),
							Main.sex.getCharacterPerformingAction(),
							Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)
					&& !isPerformingCharacterTotallyImmobilised();
		}

		@Override
		public SexActionPriority getPriority() {
			return getBaseForceCreampiePriority(this);
		}

		@Override
		public String getDescription() {
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive() && !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
						return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
								+ getForcedCreampieSpeech(this);
					}
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный аппарат [npc.her]."
						+ getForcedCreampieSpeech(this);
				
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] горло. С отчаянным, булькающим [npc.moan], [npc.she] [npc.verb(prepare)] проглотить всю сперму, которая вот-вот хлынет в [npc.herHim].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] против [npc.hers]."
							+ getForcedCreampieSpeech(this);
				}
				return "Ошибка: область фиксации хвоста не учтена. Пожалуйста, сообщите Иннокси!";
				
			} else { // Dildo:
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.pussy+]."
							+ getForcedCreampieSpeech(this);

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] [npc.asshole+]."
							+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] члена."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в уретру [npc.her] киски."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Когда [npc.name] [npc.verb(realise)], понимая, что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы и, потянув [npc2.herHim] вперед, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в паутинный аппарат [npc.her]."
						+ getForcedCreampieSpeech(this);
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)) {
					return "Когда [npc.name] [npc.verb(realise)], что [npc2.nameIsFull] вот-вот кончит, [npc.she] быстро [npc.verb(wrap)] [npc.her] [npc.tentacles] вокруг [npc2.her] поясницы, и, потянув [npc2.herHim] на себя, [npc.she] [npc.verb(force)] [npc2.her] [npc2.cock+] глубоко в [npc.her] горло.";
				}
				return "Ошибка: область фиксации хвоста не учтена. Пожалуйста, сообщите Иннокси!";
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterTargetedForSexAction(this), new Value<>(Main.sex.getCharacterPerformingAction(), Tentacle.class));
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			if(isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_CUM_ADDICT);
				} else {
					fetishes.add(Fetish.FETISH_CUM_STUD);
				}
			}
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterPerformingAction())
					&& isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_PREGNANCY);
				} else {
					fetishes.add(Fetish.FETISH_IMPREGNATION);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
				} else {
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
				}
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
					&& !Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterPerformingAction())) {
				if(character.equals(Main.sex.getCharacterPerformingAction())) {
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
				} else {
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
				}
			}
			return fetishes;
		}
	};
	
	
	public static final SexAction GENERIC_PREPARATION_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		@Override
		public String getActionTitle() {
			return "Попросить вытащить";
		}

		@Override
		public String getActionDescription() {
			if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Попроси [npc2.herHim] вытащить из тебя [npc2.her], пока [npc2.she] [npc2.verb(orgasm)].";
			}
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Попроси [npc2.herHim] вытащить, прежде чем [npc2.she] кончит.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || isTargetedCharacterTotallyImmobilised(this)) {
				return false;
			}
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
						&& !Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
						&& !Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY);
				
			} else {
				if(GENERIC_PREPARATION_ASK_FOR_CREAMPIE.isBaseRequirementsMet()
						&& GENERIC_PREPARATION_ASK_FOR_CREAMPIE.getPriority().getValue()>=this.getPriority().getValue()) {
					return false; // Do not ask for pullout if they have ask for creampie available as well.
				}
				
				return isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
						&& (Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
							|| ((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)
									?!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()
									:true)
							&& (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
									?!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
									:true)));
			}
		}

		@Override
		public SexActionPriority getPriority() {
			if(getCharacterBeingFucked()==Main.sex.getCharacterPerformingAction()) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				switch(behaviour) {
					case DEFAULT:
						break;
					case NO_ENCOURAGE:
					case CREAMPIE:
					case KNOT:
						return SexActionPriority.LOW;
					case PULL_OUT:
						return SexActionPriority.UNIQUE_MAX;
				}
			}
			
			if((Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
					&& Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
						&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
				return SexActionPriority.HIGH;
				
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}

		@Override
		public String getDescription() {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING) {
				if(Main.sex.getCharacterPerformingAction().isMute()) {
					if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).isEmpty()) { // Non-orifice penetration
						return "Хотя [npc.namePos] не может говорить, ее полные отчаяния попытки вырваться ясно дают понять, что [npc.she] [npc.verb(want)] [npc2.name], пытаясь оттянуть [npc2.her] [npc2.cock+] от [npc.herhim].";
					} else { // Orifice penetration
						SexAreaInterface areaPenetrated = Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0);
						if(areaPenetrated.isOrifice() && ((SexAreaOrifice)areaPenetrated).isInternalOrifice()) {
							return "Хотя [npc.namePos] не может говорить, ее полные отчаяния попытки вырваться ясно дают понять, что [npc.she] [npc.verb(want)] [npc2.name], пытаясь вытащить [npc.herhim].";
						} else {
							return "Хотя [npc.namePos] не может говорить, ее полные отчаяния попытки вырваться ясно дают понять, что [npc.she] [npc.verb(want)] [npc2.name], пытаясь оттянуть [npc2.her] [npc2.cock+] от [npc.herhim].";
						}
					}
					
				} else {
					if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).isEmpty()) { // Non-orifice penetration
							return "Отчаянно сопротивляясь, пока [npc.she] [npc.verb(try)] [npc2.name], пытаясь оттолкнуть [npc.herHim], [npc.name] [npc.verb(cry)], [npc.speech(Нет! Пожалуйста! Убери свой [npc2.cock] от меня!)]";
						} else { // Orifice penetration
							SexAreaInterface areaPenetrated = Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0);
							if(areaPenetrated.isOrifice() && ((SexAreaOrifice)areaPenetrated).isInternalOrifice()) {
								return "Отчаянно сопротивляясь, пока [npc.she] [npc.verb(try)] [npc2.name], пытаясь оттолкнуть [npc.herHim], [npc.name] [npc.verb(cry)], [npc.speech(Нет! Пожалуйста! Вытащи его! Убери свой [npc2.cock] из меня!)]";
								
							} else {
								return "Отчаянно сопротивляясь, пока [npc.she] [npc.verb(try)] [npc2.name], пытаясь оттолкнуть [npc.herHim], [npc.name] [npc.verb(cry)], [npc.speech(Нет! Пожалуйста! Убери свой [npc2.cock] от моей "+areaPenetrated.getName(Main.sex.getCharacterPerformingAction(), true)+"!)]";
							}
						}
					
					} else {
						if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).isEmpty()) { // Non-orifice penetration
							return "Отчаянно борясь, пока [npc.she] [npc.verb(try)] отталкивать [npc2.name] от [npc.herHim], [npc.name] вскрикивает: [npc.speech(Нет! Пожалуйста! Убери свой член от меня!)]";
						} else { // Orifice penetration
							SexAreaInterface areaPenetrated = Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, Main.sex.getCharacterPerformingAction()).get(0);
							if(areaPenetrated.isOrifice() && ((SexAreaOrifice)areaPenetrated).isInternalOrifice()) {
								if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
									return "Отчаянно борясь, пока [npc.she] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim], [npc.name] [npc.verb(cry)], [npc.speech(Нет! Пожалуйста! Вытащи! Твой презерватив может порваться внутри меня!)]";
								} else {
									return "Отчаянно борясь, пока [npc.she] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim], [npc.name] [npc.verb(cry)], [npc.speech(Нет! Пожалуйста! Вытащи свой "+areaPenetrated.getName(Main.sex.getCharacterPerformingAction(), true)+"! Не кончай в меня!)]";
								}
							} else {
								return "Отчаянно борясь, пока [npc.she] [npc.verb(try)], пытаясь оттолкнуть [npc2.name] от [npc.herHim], [npc.name] [npc.verb(cry)], [npc.speech(Нет! Пожалуйста! Убери свой член от моей "+areaPenetrated.getName(Main.sex.getCharacterPerformingAction(), true)+"!)]";
							}
						}
					}
				}
			}
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)], издавая череду умоляющих скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name], чтобы вытащить из [npc.her] [npc.pussy+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)], издавая череду умоляющих скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name], чтобы вытащить из [npc.her] [npc.asshole+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)], издавая череду умоляющих скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name], чтобы вытащить из [npc.her] [npc.nipple+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)], издавая череду умоляющих скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name], чтобы вытащить из [npc.her] [npc.spinneret+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)] использовать череду жалобных скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] подальше от [npc.her] [npc.breasts+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)] использовать череду жалобных скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] подальше от [npc.her] [npc.feet+].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)] использовать череду жалобных скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] подальше от [npc.her] [npc.armpit+].";
						
					} else {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)] использовать череду жалобных скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] вытащить из [npc.herHim], пока [npc2.she] [npc2.verb(orgasm)].";
					}
	
				} else {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)] использовать череду жалобных скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] вытащить из [npc.her] [npc.pussy+] до того, как [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Хотя и не в силах говорить, [npc.name] [npc.verb(manage)] использовать череду жалобных скулящих звуков, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] вытащить из [npc.her] [npc.asshole+] до того, как [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.nipple+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull out of [npc.her] [npc.spinneret+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.breasts+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.feet+] before [npc2.she] [npc2.verb(cum)].";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Although not able to speak, [npc.name] [npc.verb(manage)] to use a series of pleading whines in order to convey that [npc.she] [npc.verb(want)] [npc2.name] to pull away from [npc.her] [npc.armpit+] before [npc2.she] [npc2.verb(cum)].";
						
					} else {
						return "Хотя [npc.name] и не может говорить, [npc.verb(manage)] пытается издавать жалобные стоны, чтобы показать, что [npc.she] [npc.do] не хочет [npc2.namePos] спермы.";
					}
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)
							|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] обращается к [npc2.name], [npc.speech(Вытащи! Вытащи из меня этот дилдо!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] обращается к [npc2.name], [npc.speech(Вытащи! Вытащи из меня этот дилдо!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] обращается к [npc2.name], [npc.speech(Вытащи! Вытащи из меня этот дилдо!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] обращается к [npc2.name], [npc.speech(Вытащи! Вытащи из меня этот дилдо!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] обращается к [npc2.name], [npc.speech(Вытащи! Вытащи из меня этот дилдо!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] обращается к [npc2.name], [npc.speech(Убери от меня этот дилдо!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] выдавить из себя фразу, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Убери от меня этот дилдо!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] выдавить из себя фразу, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Убери от меня этот дилдо!)]";
						
					} else {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] выкрикнуть, обращаясь к [npc2.namePos] [npc2.cock], [npc.speech(Вытащи! Вытащи этот дилдо из моего рта!)]";
					}
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] выдавить из себя фразу, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Я не хочу рисковать тем, что презерватив порвется у меня в киске!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] выдавить из себя фразу, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Я не хочу рисковать тем, что презерватив порвется!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] выдавить из себя фразу, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Я не хочу рисковать тем, что презерватив порвется!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Кончай! Не хочу, чтобы этот презерватив лопнул у меня в заднице!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Кончай! Не хочу, чтобы этот презерватив лопнул у меня на соске!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Кончай! Не хочу, чтобы этот презерватив лопнул у меня в паутинном аппарате!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Назад! Не хочу, чтобы этот презерватив лопнул прямо на моих сиськах!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Назад! Не хочу, чтобы этот презерватив лопнул прямо на моих [npc.feet]!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] сумела выдавить из себя фразу, пока [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Назад! Я не хочу, чтобы этот презерватив лопнул у меня на [npc.armpit]!)]";
						
					} else {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] сумела прокричать сквозь [npc2.namePos] [npc2.cock], [npc.speech(Вытащи! Пожалуйста!)]";
					}
					
				} else {
					if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] сумела выдавить из себя фразу, пока [npc.she] [npc.verb(cry)] на [npc2.name], "
								+(Main.sex.getCharacterPerformingAction().isVisiblyPregnant()
										|| Main.sex.getCharacterPerformingAction().hasStatusEffect(StatusEffect.MENOPAUSE)
//										|| !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
									?"[npc.speech(Вытащи! Я не хочу, чтобы ты кончил в меня!)]"
									:"[npc.speech(Вытащи! Я не хочу забеременеть!)]");
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] сумела выдавить из себя фразу, пока [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Не кончай в меня, пожалуйста!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] сумела выдавить из себя фразу, пока [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Не кончай в меня, пожалуйста!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] сумела выдавить из себя фразу, пока [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Пожалуйста, не кончай мне в задницу!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Не кончай мне в сосок! Только не это!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Вытащи! Не кончай в мой паучий аппарат! Только не это!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] составить предложение, когда [npc.she] [npc.verb(cry)] на [npc2.name], [npc.speech(Назад! Не кончай мне на сиськи! Только посмей!)]";
	
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
						return "Сквозь [npc.her] отчаянных стонов и похотливых криков, [npc.name] каким-то образом [npc.verb(manage)] удалось сформулировать фразу, когда [npc.she] [npc.verb(cry)] обратилась к [npc2.name], [npc.speech(Назад! Не кончай на мою [npc.feet]! Только посмей!)]";
						
					} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
						return "Сквозь [npc.her] отчаянных стонов и похотливых криков, [npc.name] каким-то образом [npc.verb(manage)] удалось сформулировать фразу, когда [npc.she] [npc.verb(cry)] обратилась к [npc2.name], [npc.speech(Назад! Не кончай мне на [npc.armpit]! Только посмей!)]";
						
					} else {
						return "Сквозь [npc.her] отчаянные стоны и похотливые крики, [npc.name] каким-то образом [npc.verb(manage)] выкрикнуть [npc2.namePos] [npc2.cock], [npc.speech(Вытащи! Я не хочу чувствовать вкус твоей спермы!)]";
					}
				}
			}
		}
	};

	public static final SexAction GENERIC_PREPARATION_ASK_FOR_FACIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Попросить минет";
		}
		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Попроси [npc2.herHim] кончить прямо тебе на лицо.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || isTargetedCharacterTotallyImmobilised(this)) {
				return false;
			}
			if(!Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return false;
			}
			boolean cockFaceInteractionAvailable = false;
			try {
				cockFaceInteractionAvailable = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getAvailableCumTargets().contains(OrgasmCumTarget.FACE);
			} catch(Exception ex) {
				// No available penis-mouth actions, so can't reach face
			}
			if(!cockFaceInteractionAvailable) { // Check for reverse if not found:
				try {
					cockFaceInteractionAvailable = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
							.get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
							.getProvidedCumTargets().contains(OrgasmCumTarget.FACE);
				} catch(Exception ex) {
				}
			}
			if(!cockFaceInteractionAvailable || Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return false;
			}
			
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return true;
				
			} else { // Do not ask for pullout if they are resisting or want to ask for a creampie.
				if((GENERIC_PREPARATION_ASK_FOR_CREAMPIE.isBaseRequirementsMet() && GENERIC_PREPARATION_ASK_FOR_CREAMPIE.getPriority().getValue()>=this.getPriority().getValue())) {
					return false;
				}
				
				return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative();
			}
		}
		// Just let them use standard behaviour weighting for this one
		@Override
		public SexActionPriority getPriority() {
			if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isPositive()) {
				return SexActionPriority.LOW;
			} else {
				return super.getPriority();
			}
		}
		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), OrgasmCumTarget.FACE);
		}
		@Override
		public String getDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				return "Хотя [npc.name] не может говорить, [npc.verb(manage)] использует череду умоляющих стонов и жестов, чтобы передать, что [npc.she] [npc.verb(want)] [npc2.name] кончить на лицо [npc.her].";
				
			} else {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_VAGINA)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.URETHRA_PENIS)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)
						|| isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом [npc.verb(manage)] удается сформулировать фразу, когда [npc.she] [npc.verb(cry)] выкрикивает [npc2.name]: [npc.speech(Вытащи! Кончи мне на лицо!)]";

				} else {
					return "Сквозь отчаянные стоны и похотливые крики [npc.her], [npc.name] каким-то образом [npc.verb(manage)] удается сформулировать фразу, когда [npc.she] [npc.verb(cry)] выкрикивает [npc2.name]: [npc.speech(Я хочу, чтобы ты кончил мне на лицо!)]";
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			}
		}
	};
	
	public static final SexAction PLAYER_PREPARATION_ASK_FOR_NOTHING = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Без просьб";
		}

		@Override
		public String getActionDescription() {
			return "Ты чувствуешь, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Ничего не проси у [npc2.herHim] и посмотри, что [npc2.sheHasFull] приготовил для тебя.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return isTakingCock(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !isPerformingCharacterTotallyImmobilised();
					//&& !isTargetedCharacterTotallyImmobilised(this);
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
		}

		@Override
		public String getDescription() {
			if(!isRealPenisFuckingCharacter(Main.game.getPlayer(), Main.sex.getCharacterTargetedForSexAction(this))) {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Ты продолжаешь [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает вколачиваться в твою [npc.pussy+], и ты гадаешь, собирается ли [npc2.sheIs] вытащить член или продолжит трахать тебя до самого [npc2.her] оргазма.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Ты продолжаешь [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает вколачиваться в твою [npc.asshole+], и ты гадаешь, собирается ли [npc2.sheIs] вытащить член или продолжит трахать тебя до самого [npc2.her] оргазма.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "Вы продолжаете [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает вколачиваться в вашу [npc.nipple+], и вы гадаете, собирается ли [npc2.sheIs] вытащить член или продолжит трахать вас до самого [npc2.her] оргазма.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Вы продолжаете [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает вколачиваться в вашу [npc.spinneret+], и вы гадаете, собирается ли [npc2.sheIs] вытащить член или продолжит трахать вас до самого [npc2.her] оргазма.";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "Вы продолжаете [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает скользить вверх и вниз между вашими [npc.breasts+], и вы гадаете, собирается ли [npc2.sheIs] продолжать трахать вашу [npc.breasts+] до самого [npc2.her] оргазма.";
					} else {
						return "Вы продолжаете [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает скользить вверх и вниз по вашему торсу, и вы гадаете, собирается ли [npc2.sheIs] отстраниться или продолжит трахать вашу плоскую грудь до самого [npc2.her] оргазма.";
					}
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "Вы продолжаете [npc.moaning+], давая [npc.name] [pc.a_footjob], и вы гадаете, собирается ли [npc2.sheIs] отстраниться или продолжит трахать вашу [npc.feet] до самого [npc2.her] оргазма.";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ARMPITS)) {
					return "Вы продолжаете [npc.moaning+], пока [npc.name] трахает вашу [pc.armpit+], и вы гадаете, собирается ли [npc2.sheIs] отстраниться или продолжит трахать вашу впадину до самого [npc2.her] оргазма.";
					
				} else {
					return "Вы продолжаете [npc.moaning+], пока [npc2.name] продолжает трахать вас с [npc2.her] [npc2.cock+], и вы гадаете, собирается ли [npc2.sheIs] вытащить член или продолжит трахать вас до самого [npc2.her] оргазма.";
				}
				
			} else {
				if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA)) {
					return "Вы продолжаете [npc.moaning+], чувствуя, как [npc2.namePos] [npc2.cock+] начинает дергаться внутри вашей [npc.pussy+], и вы гадаете, собирается ли [npc2.sheIs] вытащить его или"
							+(Main.game.getPlayer().isVisiblyPregnant()
									?" сделает вам свежий кремпи..."
									:" наполнит вашу утробу [npc2.her] [npc2.cum+]...");

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS)) {
					return "Вы продолжаете [npc.moaning+], чувствуя, как [npc2.namePos] [npc2.cock+] начинает дергаться внутри вашей [npc.asshole+], и вы гадаете, собирается ли [npc2.sheIs] вытащить его или сделать вам свежий анальный кремпи...";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.NIPPLE)) {
					return "Вы продолжаете [npc.moaning+], чувствуя, как [npc2.namePos] [npc2.cock+] начинает дергаться внутри вашей [npc.nipple+], и вы гадаете, собирается ли [npc2.sheIs] вытащить его или сделать вам свежий кремпи в соски...";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.SPINNERET)) {
					return "Вы продолжаете [npc.moaning+], чувствуя, как [npc2.namePos] [npc2.cock+] начинает дергаться внутри вашей [npc.spinneret+], и вы гадаете, собирается ли [npc2.sheIs] вытащить его или сделать вам свежий кремпи из паучьего пакля...";

				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaOrifice.BREAST)) {
					if(Main.sex.getCharacterPerformingAction().isBreastFuckablePaizuri()) {
						return "Вы продолжаете [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает скользить вверх и вниз между вашими [npc.breasts+], и вы гадаете, собирается ли [npc2.sheIs] вытащить его или кончит прямо на вашу [npc.breasts+].";
					} else {
						return "Вы продолжаете [npc.moaning+], пока [npc2.namePos] [npc2.cock+] продолжает скользить вверх и вниз по вашему торсу, и вы гадаете, собирается ли [npc2.sheIs] отстраниться или кончит прямо на вашу плоскую грудь.";
					}
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "Вы продолжаете [npc.moaning+], давая [npc.name] [pc.a_footjob], и вы гадаете, собирается ли [npc2.sheIs] отстраниться или кончит на вашу [npc.feet].";
					
				} else if(isAreaFuckedByTarget(this, Main.sex.getCharacterPerformingAction(), SexAreaPenetration.FOOT)) {
					return "Вы продолжаете [npc.moaning+], пока [npc.name] трахает вашу [pc.armpit+], и вы гадаете, собирается ли [npc2.sheIs] отстраниться или кончит вам в ложбинку.";
					
				} else {
					return "Вы продолжаете [npc.moaning+], чувствуя, как [npc2.namePos] [npc2.cock+] начинает подергиваться, и вы гадаете, собирается ли [npc2.sheIs] вытащить член или наполнить вас [npc2.her] [npc2.cum+]...";
				}
			}
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_DENIAL = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE_MAJOR,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised()) {
				return false;
			}
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING
					|| Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					|| Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterTargetedForSexAction(this))) {
				return false;
			}
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction()) && !Main.sex.isCharacterDeniedOrgasm(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Main.sex.isReadyToOrgasm(Main.sex.getCharacterTargetedForSexAction(this)) // check if really orgasming
							&& Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL); // Only allow denial fetishists to use this action
				}
			}
			return false;
		}
		
		@Override
		public SexActionPriority getPriority() {
//			if(Main.sex.getCharacterPerformingAction().isPlayer() || !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL)) {
//				return SexActionPriority.LOW;
//			}
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getActionTitle() {
			return "Отвергнуть";
		}

		@Override
		public String getActionDescription() {
			return "Вы чувствуете, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Не дайте [npc2.herHim] забрать его себе.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isCharacterTotallyImmobilised(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"По усиливающимся сонным стонам и тяжелому дыханию [npc2.namePos], [npc.name] становится ясно, что [npc2.sheIs] вот-вот кончит во [npc2.her] сне.",
							"[npc2.Name] [npc2.verb(let)] особенно похотливый, сонный стон, давая [npc.name] понять, что [npc2.sheIs] вот-вот кончит во [npc2.her] сне."));
					
					UtilText.nodeContentSB.append("<br/><br/>");
					
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Услышав это, [npc.name] [npc.verb(stop)], резко прекращая стимуляцию [npc2.namePos], чтобы не дать [npc2.herHim] достичь [npc2.her] климакса.",
							"Пока [npc.she] [npc.verb(hear)] это, [npc.name] быстро [npc.verb(stop)] то, что делает [npc.sheIs], следя за тем, чтобы [npc2.nameIsFull] не получала никакой стимуляции, дабы не дать [npc2.herHim] достичь [npc2.her] оргазма."));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Хотя [npc2.she] [npc2.verb(remain)] совершенно неподвижно, [npc.name] [npc.verb(get)] ощущение, что [npc2.she] вот-вот кончит.",
							"Несмотря на то, что [npc2.name] [npc.verb(remain)] в состоянии полного оцепенения, [npc.name] интуитивно [npc2.verb(realise)], что [npc2.she] вот-вот испытает оргазм."));
					
					UtilText.nodeContentSB.append("<br/><br/>");

					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Осознав это, [npc.name] [npc.verb(stop)], резко прекращая стимуляцию [npc2.namePos], чтобы не дать [npc2.herHim] достичь [npc2.her] оргазма.",
							"Пока [npc.she] [npc.verb(realise)] это, [npc.name] быстро [npc.verb(stop)] то, что делает [npc.sheIs], следя за тем, чтобы [npc2.nameIsFull] не получала никакой стимуляции, дабы не дать [npc2.herHim] достичь [npc2.her] оргазма."));
				}
				
			} else {
				//TODO fetishes and player-specific descriptions
				
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"По тому, как усилились отчаянные, приглушенные стоны и тяжелое дыхание [npc2.namePos], [npc.name] становится ясно, что [npc2.sheIs] вот-вот кончит.",
									"[npc2.Name] [npc2.verb(let)] особенно похотливый, приглушенный стон, давая [npc.name] понять, что [npc2.sheIs] вот-вот испытает оргазм."));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"По тому, как усилились возбужденные, приглушенные [npc2.moans] [npc2.namePos], [npc.name] становится ясно, что [npc2.sheIs] вот-вот кончит.",
									"[npc2.Name] [npc2.verb(let)] особенно эротичный, приглушенный [npc2.moan], давая [npc.name] понять, что [npc2.sheIs] вот-вот кончит."));
							break;
					}
					
				} else {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name] [npc2.verb(start)] отчаянно скулит и тяжело дышит, прежде чем выпалить: [npc2.speech(Нет! Ты сейчас заставишь меня кончить!)]",
									"[npc2.Name] [npc2.verb(let)] особенно похотливый стон, после чего содрогается и вскрикивает: [npc2.speech(Не так! Я сейчас кончу! Нет!)]",
									"Издавая удивительно эротичный крик, [npc2.name] [npc2.verb(exclaim)], [npc2.speech(Прекрати! Нет! Я... я сейчас кончу!)]"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name] [npc2.verb(start)] отчаянно тяжело дышит и [npc2.moaning], прежде чем выпалить: [npc2.speech(Да! Ты сейчас заставишь меня кончить!)]",
									"[npc2.Name] [npc2.verb(let)] особенно эротичный [npc2.moan], прежде чем возбужденно воскликнуть: [npc2.speech(Да! Я сейчас кончу! Да, давай же!)]",
									"Издавая невероятно эротичный [npc2.moan], [npc2.name] [npc2.verb(exclaim)], [npc2.speech(Вот так! Да! Я... я сейчас кончу!)]"));
							break;
					}
				}
				
				UtilText.nodeContentSB.append("<br/><br/>");
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Услышав это, [npc.name] быстро [npc.verb(take)] крепко сжимает [npc2.namePos] [npc2.arms], после чего удерживает [npc2.herHim] на месте, не давая [npc2.herHim] стимулировать [npc2.herself].",
								"Пока [npc.she] [npc.verb(hear)] это, [npc.name] быстро [npc.verb(grab)] хватает [npc2.namePos] [npc2.arms], используя [npc.her] как рычаг, чтобы удержать [npc2.herHim] на месте и не дать [npc2.herHim] достичь [npc2.her] оргазма.",
								"Услышав, что [npc2.sheIs] вот-вот испытает оргазм, [npc.name] быстро [npc.verb(grab)] хватает [npc2.namePos] за [npc2.arms], после чего крепко удерживает [npc2.herHim] на месте, не давая [npc2.herHim] достичь [npc2.her] пика."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Услышав это, [npc.name] быстро и властно [npc.verb(take)] хватает [npc2.namePos] за [npc2.arms], после чего грубо фиксирует [npc2.herHim], не позволяя [npc2.herHim] стимулировать [npc2.herself].",
								"Пока [npc.she] [npc.verb(hear)] это, [npc.name] грубо [npc.verb(grab)] хватает [npc2.namePos] [npc2.arms], после чего использует [npc.her] рычаг, чтобы заставить [npc2.herHim] замереть и не дать [npc2.herHim] достичь [npc2.her] кульминации.",
								"Услышав, что [npc2.sheIs] вот-вот испытает оргазм, [npc.name] с силой [npc.verb(grab)] хватает [npc2.namePos] за [npc2.arms], после чего грубо удерживает [npc2.herHim] на месте, не давая [npc2.herHim] достичь [npc2.her] пика."));
						break;
					default:
						break;
				}
	
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Вы чувствуете легкое облегчение, понимая, что вас не заставят кончить, и просто продолжаете рыдать и умолять, чтобы вас оставили в покое, пока [npc.name] [npc.verb(force)] вас успокоить."));
							
						} else {
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(seem)] с небольшим облегчением, что [npc2.sheIs] не заставят дойти до оргазма, а лишь [npc2.verb(continue)] рыдать и умолять, чтобы её оставили в покое, пока [npc.name] [npc.verb(force)] [npc2.herHim] успокоиться."));
						}
						break;
					default:
						if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
							if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" Обожая саму концепцию лишения удовольствия в шаге от [npc2.her] оргазма, [npc2.name] [npc2.verb(let)] похотливый возглас, пока [npc2.sheIs] заставляют успокоиться, и когда [npc2.she] [npc2.verb(withdraw)] от самого края [npc2.her] оргазма, [npc2.her] эротическое восклицание превращается в особенно отчаянный, приглушенный [npc2.moan]."));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" Чувствуя невероятное разочарование от того, что ее лишили удовольствия так близко к [npc2.her] оргазму, [npc2.name] [npc2.verb(let)] неистовый, приглушенный крик, пока [npc2.sheIs] заставляют успокоиться, ясно давая понять, что [npc2.she] отчаянно [npc2.verb(want)] к оргазму."));
							}
							
						} else {
							if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_DENIAL_SELF).isPositive()) {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" Обожая саму концепцию лишения удовольствия в шаге от [npc2.her] оргазма, [npc2.name] [npc2.verb(let)] похотливый возглас, пока [npc2.sheIs] заставляют успокоиться, и когда [npc2.she] [npc2.verb(withdraw)] от самого края [npc2.her] оргазма, [npc2.she] [npc2.moansVerb], "));
		
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.speech(О да... Это так приятно... Я кончу только тогда, когда ты позволишь, [npc.name]...)]",
										"[npc2.speech(Да... Ты можешь лишать меня удовольствия, как пожелаешь, [npc.name]...)]"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										" Чувствуя невероятное разочарование от того, что ее лишили удовольствия так близко к [npc2.her] оргазму, [npc2.name] [npc2.verb(let)] неистовый крик, пока [npc2.sheIs] заставляют успокоиться, "));
		
								UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
										"[npc2.speech(Нет! Я была так близко! Дай мне уже кончить!)]",
										"[npc2.speech(Дай мне кончить! Нет! Я была так близко!)]"));
							}
						}
						break;
				}
			}
			
			UtilText.nodeContentSB.append("<p style='text-align:center'><i>[npc2.NamePos] оргазм был [style.boldBad(лишен)]!</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.addCharacterDeniedOrgasm(Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.incrementNumberOfDeniedOrgasms(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), 1);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			}
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_CREAMPIE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}
		
		private boolean isSelfFucking() {
			return Main.sex.getCharacterTargetedForSexAction(this)==getCharacterBeingFucked();
		}
		
		@Override
		public String getActionTitle() {
			if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
				return "Поощрять трах";
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
				return "Подгонять к глубокому минет";

			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), "Подгонять кончить на [npc.breasts]");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), "Подгонять кончить на [npc.feet]");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
				return UtilText.parse(getCharacterBeingFucked(), "Подгонять кончить на [npc.armpit]");
				
			} else {
				return "Подгонять к кнуту";
			}
		}

		@Override
		public String getActionDescription() {
			if(isSelfFucking()) {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					return UtilText.parse(getCharacterBeingFucked(), "Видно, что [npc.name] вот-вот достигнет [npc.her] оргазма. Подгоняйте [npc.herHim], чтобы он продолжал трахать [npc.herself] [npc.her] дилдо, пока [npc.she] кончает.");
	
				} else {
					return UtilText.parse(getCharacterBeingFucked(), "Видно, что [npc.name] вот-вот достигнет [npc.her] оргазма. Подгоняйте [npc.herHim], чтобы он продолжал трахать [npc.herself], пока [npc.she] кончает.");
				}
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Подгоняйте [npc2.herHim], чтобы он продолжал трахать [npc.name] [npc2.her] дилдо, пока [npc2.she] кончает.");
	
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Подгоняйте [npc2.herHim], чтобы он заполнил живот [npc.namePos] своим [npc2.her].");
	
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] вот-вот достигнет [npc2.her] оргазма. Подгоняйте [npc2.herHim], чтобы он кончил на всю [npc.namePos] [npc.breasts+].");
	
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Подбодрите [npc2.herHim], чтобы она кончила прямо на [npc.namePos] [npc.feet+].");
					
				} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Подбодрите [npc2.herHim], чтобы она кончила прямо на [npc.namePos] [npc.armpit+].");
					
				} else {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Подбодрите [npc2.herHim], чтобы она заполнила [npc.name] своей [npc2.her] спермой.");
				}
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case CREAMPIE:
					return SexActionPriority.UNIQUE_MAX;
				case DEFAULT:
				case KNOT:
					break;
				case NO_ENCOURAGE:
				case PULL_OUT:
					return SexActionPriority.LOW;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)) //TODO?
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT,
									SexAreaOrifice.ARMPITS, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
			}
			
			if(isSelfFucking()) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("Точно понимая, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim], и продолжать трахать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)].");
				
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						sb.append("Точно понимая, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)], чтобы [npc2.name] продолжал трахать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Продолжай!)]");
						
					} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						sb.append("Точно понимая, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)], чтобы [npc2.name] продолжал трахать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
						
					} else {
						sb.append("Точно понимая, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)], чтобы [npc2.name] продолжал трахать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)],");

						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							if(getCharacterBeingFucked().isVisiblyPregnant()) {
								sb.append(" [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Заполни свою киску своей спермой!)]");
							} else {
								sb.append(" [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Залей свою киску своей спермой и забеременей!)]");
							}
						} else {
							sb.append(" [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Продолжай!)]");
						}
					}
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] череду приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim], продолжая вколачивать [npc3.namePos] [npc3.pussy+], пока [npc2.she] [npc2.verb(orgasm)].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] череду приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim], продолжая вколачивать [npc3.namePos] [npc3.asshole+], пока [npc2.she] [npc2.verb(orgasm)].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] череду приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim], продолжая вколачивать [npc3.namePos] [npc3.nipple+], пока [npc2.she] [npc2.verb(orgasm)].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] череду приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim], продолжая трахать [npc3.namePos] [npc3.spinneret+], пока [npc2.she] [npc2.verb(orgasm)].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim] продолжать трахать [npc3.namePos] [npc3.breasts+], пока [npc2.she] [npc2.verb(orgasm)].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim] продолжать трахать [npc3.namePos] [npc3.feet+], пока [npc2.she] [npc2.verb(orgasm)].");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim] продолжать трахать [npc3.namePos] [npc3.armpit+], пока [npc2.she] [npc2.verb(orgasm)].");
							
						} else {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim] продолжать трахать [npc3.name], пока [npc2.she] [npc2.verb(orgasm)].");
						}
		
					} else {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim] кончить внутрь [npc3.namePos] [npc3.pussy+].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim] кончить внутрь [npc3.namePos] [npc3.asshole+].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim] кончить внутрь [npc3.namePos] [npc3.nipple+].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim] кончить внутрь [npc3.namePos] [npc3.spinneret+].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim] кончить на весь [npc3.namePos] [npc3.breasts+].");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim] кончить на весь [npc3.namePos] [npc3.feet+].");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim] кончить прямо на [npc3.namePos] [npc3.armpit+].");
							
						} else {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim] кончить внутрь [npc3.name].");
						}
					}
					
				} else {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] ради того, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.pussy+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHerHim+"!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] ради того, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.asshole+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHer+" [npc3.ass]!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] ради того, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.nipple+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHer+" [npc3.breasts]!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.spinneret+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHer+" [npc3.spinneret]!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("только что что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.breasts+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHer+" сиськи!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.feet+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHer+" [npc3.feet]!)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.armpit+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHer+" дырку!)]");
							
						} else {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.name] как [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Именно так! Не переставай трахать "+targetHerHim+"!)]");
						}
		
					} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.pussy+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Кончай внутрь "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.asshole+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Кончай внутри "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.nipple+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Кончай внутри "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.spinneret+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Кончай внутри "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.breasts+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Вот так"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.feet+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Вот так"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.armpit+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Вот так"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
							
						} else {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.name], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Вот так,"+(petName?", [#npc.getPetName(npc2)]":"")+"! Выплесни всё в этот презерватив!)]");
						}
						
					} else {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
		
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.pussy+], пока [npc2.she] [npc2.verb(orgasm)],"
									+(getCharacterBeingFucked().isVisiblyPregnant()
											?" [npc.speech(Блядь! Кончай в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Наполни "+targetHer+" киску своей спермой!)]"
											:" [npc.speech(Оплодотвори "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Наполни "+targetHer+" киска, полная твоей спермы, и стук "+targetHerHim+" вверх!)]"));
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] продолжал вколачивать [npc3.namePos] [npc3.asshole+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Блядь! Кончай в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Наполни "+targetHer+" зад своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] на виду у [npc2.name], чтобы он продолжал долбить [npc3.namePos] [npc3.nipple+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Блядь! Кончай в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Наполни "+targetHer+" сосок своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] на виду у [npc2.name], чтобы он продолжал долбить [npc3.namePos] [npc3.spinneret+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Блядь! Кончай в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Наполни "+targetHer+" паутинный аппарат своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] на виду у [npc2.name], чтобы он продолжал долбить [npc3.namePos] [npc3.breasts+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Кончай в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Покрой "+targetHer+" сиськи своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] на виду у [npc2.name], чтобы он продолжал долбить [npc3.namePos] [npc3.feet+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! Кончай в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Покрой "+targetHer+" [npc3.feet] своей спермой!)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Точно зная, что это такое, [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)], чтобы [npc2.name] продолжал вбивать [npc3.namePos] [npc3.armpit+], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! кончай "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Покрой "+targetHer+" ямой со своей спермой!)]");
							
						} else {
							sb.append("Точно зная, что это такое, [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)], чтобы [npc2.name] продолжал вбивать [npc3.name], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Да! кончай "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Не вынимай!)]");
						}
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] положительный [npc2.moan], чтобы дать [npc.name] понять, что это именно то, что [npc2.sheHasFull] планировал.");
						
					} else {
						sb.append("<br/><br/>Нахмурив [npc2.her] брови, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] отрицательный [npc2.moan], чтобы дать [npc.name] понять, что [npc2.sheIs] не заинтересован в том, чтобы слушать всё, что [npc.sheHasFull] скажет.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] быстро отвечает утвердительно, ",
									"Издавая [npc.a_moan+] после того, как услышала, что именно [npc.nameIs] просит у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Конечно, если ты хочешь это увидеть!)]",
								"[npc2.speech(Мне это нравится!)]",
								"[npc2.speech(Без проблем! Я получу от этого удовольствие!)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Нахмурив брови [npc2.her], пока [npc.name] [npc.verb(ask)] это, [npc2.name] быстро [npc2.verb(shut)] [npc.herHim] вниз, ",
									"Явно не в восторге от того, что [npc.nameIs] просит чего-то у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Я буду делать всё, что мне вздумается!)]",
								"[npc2.speech(Я тебя не слушаю!)]",
								"[npc2.speech(Буду делать то, что хочу!)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingCreampie().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			fetishes.add(Fetish.FETISH_VOYEURIST);
			return fetishes;
		}
	};
	

	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_KNOT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}

		private boolean isSelfFucking() {
			return Main.sex.getCharacterTargetedForSexAction(this)==getCharacterBeingFucked();
		}
		
		@Override
		public String getActionTitle() {
			return "Поощрять узловатость";
		}

		@Override
		public String getActionDescription() {
			if(isSelfFucking()) {
				return UtilText.parse(getCharacterBeingFucked(), "Видно, что [npc.name] стремительно приближается к [npc.her] оргазму. Поощрить [npc.herHim] протолкнуть [npc.her] узел внутрь и наполнить [npc.herself] [npc.her] спермой.");
				
			} else {
				if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Поощрить [npc2.herHim] протолкнуть [npc2.her] узел в рот [npc.namePos] и наполнить [npc.her] желудок [npc2.her] спермой.");
	
				} else {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Поощрить [npc2.herHim] протолкнуть [npc2.her] узел в [npc.name] и наполнить [npc.name] [npc2.her] спермой.");
				}
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case KNOT:
					return SexActionPriority.UNIQUE_MAX;
				case DEFAULT:
				case CREAMPIE:
					break;
				case NO_ENCOURAGE:
				case PULL_OUT:
					return SexActionPriority.LOW;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisModifier(PenetrationModifier.KNOTTED)
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this)) //TODO?
					&& !Collections.disjoint(
							Util.newArrayListOfValues(
									SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.NIPPLE, SexAreaOrifice.NIPPLE_CROTCH, SexAreaOrifice.URETHRA_PENIS, SexAreaOrifice.URETHRA_VAGINA),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
			}

			if(isSelfFucking()) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("Точно зная, что [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серия приглушенных криков, пока [npc.she] [npc.verb(try)], чтобы передать [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim], чтобы завязать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)].");
				
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] мог завязать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)], [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Завязывайся внутри и наполни этот презерватив!)]");
						
					} else {
						sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] мог завязать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)],");

						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							if(getCharacterBeingFucked().isVisiblyPregnant()) {
								sb.append(" [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Завязывайся сам и наполни свою киску!)]");
							} else {
								sb.append(" [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Завязывайся сам и оплодотвори себя!)]");
							}
						} else {
							sb.append(" [npc.speech(Не вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Завязывайся!)]");
						}
					}
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.name], что [npc.she] [npc.verb(want)] [npc2.herHim] завязать [npc3.name] и кончить глубоко внутри");
					if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.pussy+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.asshole+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.nipple+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.nippleCrotch+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.spinneret+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.urethraPenis+].");
	
					} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
						sb.append(" [npc3.her] [npc3.urethraVagina+].");
	
					} else {
						sb.append(" of [npc3.herHim].");
					}
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
	
						sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] мог вбить свой [npc2.her] узел в");
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.pussy+] и кончить глубоко внутри [npc3.herHim], [npc.speech(Завязывайся "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.asshole+] и кончай глубоко в [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.nipple+] и кончай глубоко в [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.nippleCrotch+] и кончай глубоко в [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.spinneret+] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.urethraPenis+] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.urethraVagina+] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
		
						} else {
							sb.append(" [npc3.name] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполни этот презерватив!)]");
						}
						
					} else {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
	
						sb.append("Точно зная, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(cry)] наружу, чтобы [npc2.name] мог вбить свой [npc2.her] узел в");
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.pussy+] и кончаешь глубоко внутри [npc3.herHim],"
										+(getCharacterBeingFucked().isVisiblyPregnant()
											?" [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и всё такое "+targetHer+" киску своей спермой!)]"
											:" [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Всё такое "+targetHer+" заливаешь киской своей спермой и осеменяешь "+targetHerHim+"!)]"));
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.asshole+] и кончай глубоко в [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и всё такое "+targetHer+" зад своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.nipple+] и кончай глубоко в [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и всё такое "+targetHer+" сосок своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE_CROTCH).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.nippleCrotch+] и кончай глубоко в [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и всё такое "+targetHer+" сосок своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.spinneret+] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и всё такое "+targetHer+" паутинный аппарат своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_PENIS).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.urethraPenis+] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполнить "+targetHer+" яички своей спермой!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.URETHRA_VAGINA).contains(getCharacterBeingFucked())) {
							sb.append(" [npc3.namePos] [npc3.urethraVagina+] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и наполнить "+targetHer+" мочевой пузырь своей спермой!)]");
		
						} else {
							sb.append(" [npc3.name] и кончаешь глубоко внутри [npc3.herHim], [npc.speech(Узел "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Кончай внутрь "+targetHerHim+" и всё такое "+targetHerHim+" своей спермой!)]");
						}
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] положительный [npc2.moan], чтобы дать [npc.name] понять, что это именно то, что [npc2.sheHasFull] планировал.");
						
					} else {
						sb.append("<br/><br/>Нахмурив [npc2.her] брови, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] отрицательный [npc2.moan], чтобы дать [npc.name] понять, что [npc2.sheIs] не заинтересован в том, чтобы слушать всё, что [npc.sheHasFull] скажет.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] быстро отвечает утвердительно, ",
									"Издавая [npc.a_moan+] после того, как услышала, что именно [npc.nameIs] просит у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Конечно, если ты хочешь это увидеть!)]",
								"[npc2.speech(Мне это нравится!)]",
								"[npc2.speech(Без проблем! Я получу от этого удовольствие!)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Нахмурив брови [npc2.her], пока [npc.name] [npc.verb(ask)] это, [npc2.name] быстро [npc2.verb(shut)] [npc.herHim] вниз, ",
									"Явно не в восторге от того, что [npc.nameIs] просит чего-то у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Я буду делать всё, что мне вздумается!)]",
								"[npc2.speech(Я тебя не слушаю!)]",
								"[npc2.speech(Буду делать то, что хочу!)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingKnot().add(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			List<AbstractFetish> fetishes = new ArrayList<>();
			fetishes.add(Fetish.FETISH_VOYEURIST);
			return fetishes;
		}
	};
	
	public static final SexAction GENERIC_PREPARATION_ENCOURAGE_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterBeingFucked() {
			List<GameCharacter> characters = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
			if(characters.isEmpty()) {
				return null;
			}
			return characters.get(0);
		}

		private boolean isSelfFucking() {
			return Main.sex.getCharacterTargetedForSexAction(this)==getCharacterBeingFucked();
		}
		
		@Override
		public String getActionTitle() {
			return "Поощрить прерванный акт";
		}

		@Override
		public String getActionDescription() {
			if(isSelfFucking()) {
				return UtilText.parse(getCharacterBeingFucked(), "Видно, что [npc.name] стремительно приближается к [npc.her] оргазму. Поощрить [npc.herHim] вытащиться из [npc.herself], пока [npc.she] [npc.verb(orgasm)].");
				
			} else {
				if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
					return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
							"Видно, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Поощрить [npc2.herHim] вытащить [npc2.her] дилдо из [npc.name], пока [npc2.she] [npc2.verb(orgasm)].");
				}
				return UtilText.parse(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this),
						"Видно, что [npc2.name] стремительно приближается к [npc2.her] оргазму. Поощрить [npc2.herHim] вытащиться из [npc.name], пока [npc2.she] [npc2.verb(orgasm)].");
			}
		}

		@Override
		public SexActionPriority getPriority() {
			OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
			switch(behaviour) {
				case DEFAULT:
					break;
				case NO_ENCOURAGE:
				case CREAMPIE:
				case KNOT:
					return SexActionPriority.LOW;
				case PULL_OUT:
					return SexActionPriority.UNIQUE_MAX;
			}
			return super.getPriority();
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			if(isPerformingCharacterTotallyImmobilised() || Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				return false;
			}
			if(getCharacterBeingFucked()!=null
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())
					&& !isTakingCock(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this))
					&& !Collections.disjoint(
							Util.newArrayListOfValues(SexAreaOrifice.VAGINA, SexAreaOrifice.ANUS, SexAreaOrifice.MOUTH, SexAreaOrifice.SPINNERET, SexAreaOrifice.BREAST, SexAreaPenetration.FOOT, SexAreaOrifice.ARMPITS),
							Main.sex.getOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getCharacterBeingFucked()))
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING)) {
				OrgasmEncourageBehaviour behaviour = Main.sex.getSexManager().getCharacterOrgasmEncourageBehaviour(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked());
				return behaviour != OrgasmEncourageBehaviour.NO_ENCOURAGE;
			}
			return false;
		}

//		@Override
//		public SexActionPriority getPriority() {
//			if((Main.sex.getAllContactingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)
//					&& Main.sex.getCharacterContactingSexArea(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))
//					&& (Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
//						&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo())
//					&& !Main.sex.getCharacterPerformingAction().isVisiblyPregnant())
//				|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_ADDICT).isNegative()) {
//				return SexActionPriority.HIGH;
//			} else {
//				return SexActionPriority.LOW;
//			}
//		}
		
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			String targetHerHim = "[npc3.herHim]";
			String targetHer = "[npc3.her]";
			String targetSheHas = "[npc3.SheHas]";
			if(getCharacterBeingFucked().isPlayer()) {
				targetHerHim = "[pc.herHim]"; // Otherwise it gets parsed as 'you'
				targetHer = "[pc.her]"; // Otherwise it gets parsed as 'you'
				targetSheHas = "[pc.SheHas]"; // Otherwise it gets parsed as 'you'
			}

			if(isSelfFucking()) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					sb.append("Точно понимая, что это [npc.she] [npc.verb(want)], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать [npc2.name] понять, что [npc.she] [npc.verb(want)] [npc2.herHim], и продолжать трахать [npc2.herself], пока [npc2.she] [npc2.verb(orgasm)].");
				
				} else {
					boolean petName = false;
					if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
						petName = true;
					}

					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this)) || Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc2.herself] до самого [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Вытащись!"+(petName?", [#npc.getPetName(npc2)]":"")+"!)]");
						
					} else {
						sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc2.herself] до самого [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился,");

						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							if(getCharacterBeingFucked().isVisiblyPregnant()) {
								sb.append(" [npc.speech(Вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Не кончай в свою киску!)]");
							} else {
								sb.append(" [npc.speech(Вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"! Не забеременей!)]");
							}
						} else {
							sb.append(" [npc.speech(Вынимай"+(petName?", [#npc.getPetName(npc2)]":"")+"!)]");
						}
					}
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.pussy+] до самого [npc2.her] оргазма, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы передать [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] вынуть.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.asshole+] до самого [npc2.her] оргазма, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы передать [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] вынуть.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.nipple+] до самого [npc2.her] оргазма, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы передать [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] вынуть.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.spinneret+] до самого [npc2.her] оргазма, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы передать [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] вынуть.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.breasts+] сквозь [npc2.her] оргазм, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] вытащить.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.feet+] сквозь [npc2.her] оргазм, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] отстраниться.");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.armpit+] сквозь [npc2.her] оргазм, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] отстраниться.");
							
						} else {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] сквозь [npc2.her] оргазм, [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] вытащить.");
						}
		
					} else {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутрь [npc3.namePos] [npc3.pussy+], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim] вытащить.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутри [npc3.namePos] [npc3.asshole+], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim], чтобы он вытащил член.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутри [npc3.namePos] [npc3.nipple+], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim], чтобы он вытащил член.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутри [npc3.namePos] [npc3.spinneret+], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim], чтобы он вытащил член.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил на всю [npc3.namePos] [npc3.breasts+], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim], чтобы он вытащил член.");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил на всю [npc3.namePos] [npc3.feet+], [npc.name] [npc.verb(make)] серию приглушенных стонов, пока [npc.she] [npc.verb(try)], чтобы дать понять [npc2.herHim], что [npc.she] [npc.verb(want)] [npc2.herHim], чтобы он вытащил член.");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил прямо на [npc3.namePos] [npc3.armpit+], [npc.name] [npc.verb(make)] серию приглушенных стонов, когда [npc.she] [npc.verb(try)], чтобы дать [npc2.herHim] понять, что [npc.she] [npc.verb(want)], чтобы тот [npc2.herHim] отстранился.");
							
						} else {
							sb.append("Не желая, чтобы [npc2.name] кончил внутрь [npc3.name], [npc.name] [npc.verb(make)] серию приглушенных стонов, когда [npc.she] [npc.verb(try)], чтобы дать [npc2.herHim] понять, что [npc.she] [npc.verb(want)], чтобы тот [npc2.herHim] кончил наружу.");
						}
					}
					
				} else {
					if(!isRealPenisFuckingCharacter(getCharacterBeingFucked(), Main.sex.getCharacterTargetedForSexAction(this))) {
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит! Вынимай из "+targetHer+" киски, когда собираешься кончить!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит! Вынимай из "+targetHer+" жопы, когда собираешься кончить!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит! Вынимай из "+targetHer+" сосок, когда ты сейчас кончишь!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит! Вынимай из "+targetHer+" паутинницу, когда ты сейчас кончишь!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит с меня! Отойди от "+targetHer+" сиськи, когда ты сейчас кончишь!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит с меня! Отойди от "+targetHer+" ступни, когда ты сейчас кончишь!)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит с меня! Отойди от "+targetHer+" ямку, когда ты сейчас кончишь!)]");
							
						} else {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(\""+targetSheHas+" хватит с меня! Отстранись от "+targetHerHim+" когда ты сейчас кончишь!)]");
						}
		
					} else if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
						
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc.pussy+] через [npc2.her] оргазм, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Вытащи из "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.asshole+] сквозь [npc2.her] оргазм, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Вытащи из "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.face+] через [npc2.her] оргазм, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Вытащи из "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.nipple+] сквозь [npc2.her] оргазм, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Вытащи из "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.spinneret+] через [npc2.her] оргазм, [npc.name] [npc.verb(cry)] чтобы [npc2.herHim] остановился, [npc.speech(Вытащи из "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.breasts+] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Отстранись"+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.feet+] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Отстранись"+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.namePos] [npc3.armpit+] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Отстранись"+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
							
						} else {
							sb.append("Не желая, чтобы [npc2.name] продолжал трахать [npc3.name] во время [npc2.her] оргазма, [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился, [npc.speech(Отстранись"+(petName?", [#npc.getPetName(npc2)]":"")+"свой член! Я хочу видеть, как раздувается твой презерватив!)]");
						}
						
					} else {
						boolean petName = false;
						if(!Main.sex.getCharacterPerformingAction().getPetName(Main.sex.getCharacterTargetedForSexAction(this)).equals(Main.sex.getCharacterTargetedForSexAction(this).getName(true))) {
							petName = true;
						}
		
						if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутрь [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановился,"
									+(getCharacterBeingFucked().isVisiblyPregnant()
											?" [npc.speech(Я не хочу, чтобы ты кончил внутри "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Вытащи из "+targetHer+" киски!)]"
											:" [npc.speech(Я не хочу "+targetHerHim+" забеременеть}"+(petName?", [#npc.getPetName(npc2)]":"")+"! Вытащи из "+targetHer+" киску перед тем, как кончишь!)]"));
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутрь [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Вытащи из "+targetHer+" зад!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутрь [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Вытащи из "+targetHer+" глотку!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутрь [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Вытащи из "+targetHer+" сосок!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.SPINNERET).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил внутрь [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил в "+targetHerHim+(petName?", [#npc.getPetName(npc2)]":"")+"! Вытащи из "+targetHer+" паутину!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.BREAST).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил на всю [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил на "+targetHer+" сиськи"+(petName?", [#npc.getPetName(npc2)]":"")+"! Отстраниться от "+targetHerHim+"!)]");
		
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaPenetration.FOOT).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил на всю [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил на "+targetHer+" [npc3.feet]"+(petName?", [#npc.getPetName(npc2)]":"")+"! Отстраниться от "+targetHerHim+"!)]");
							
						} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, SexAreaOrifice.ARMPITS).contains(getCharacterBeingFucked())) {
							sb.append("Не желая, чтобы [npc2.name] кончил на всю [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил на "+targetHer+" ямы"+(petName?", [#npc.getPetName(npc2)]":"")+"! Отстраниться от "+targetHerHim+"!)]");
							
						} else {
							sb.append("Не желая, чтобы [npc2.name] кончил на всю [npc3.name], [npc.name] [npc.verb(cry)], чтобы [npc2.herHim] остановилась, [npc.speech(Не хочу, чтобы ты кончил на "+targetHer+""+(petName?", [#npc.getPetName(npc2)]":"")+"! Отстраниться от "+targetHerHim+"!)]");
						}
					}
				}
			}
			
			if(!Main.sex.getCharacterTargetedForSexAction(this).isPlayer() && !Main.sex.isSpectator(Main.sex.getCharacterPerformingAction())) {
				if(Main.sex.getCharacterTargetedForSexAction(this).isSpeechMuffled() || Main.sex.getCharacterTargetedForSexAction(this).isMute()) {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] положительный [npc2.moan], чтобы дать [npc.name] понять, что это именно то, что [npc2.sheHasFull] планировал.");
						
					} else {
						sb.append("<br/><br/>Нахмурив [npc2.her] брови, когда [npc.name] [npc.verb(ask)] это, [npc2.name] [npc2.verb(let)] отрицательный [npc2.moan], чтобы дать [npc.name] понять, что [npc2.sheIs] не заинтересован в том, чтобы слушать всё, что [npc.sheHasFull] скажет.");
					}
					
				} else {
					if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterPerformingAction())) {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Улыбаясь, когда [npc.name] [npc.verb(ask)] это, [npc2.name] быстро отвечает утвердительно, ",
									"Издавая [npc.a_moan+] после того, как услышала, что именно [npc.nameIs] просит у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Конечно, если ты хочешь это увидеть!)]",
								"[npc2.speech(Мне это нравится!)]",
								"[npc2.speech(Без проблем! Я получу от этого удовольствие!)]"));
						
					} else {
						sb.append("<br/><br/>"
								+UtilText.returnStringAtRandom(
									"Нахмурив брови [npc2.her], пока [npc.name] [npc.verb(ask)] это, [npc2.name] быстро [npc2.verb(shut)] [npc.herHim] вниз, ",
									"Явно не в восторге от того, что [npc.nameIs] просит чего-то у [npc2.herHim], [npc2.name] отвечает, "));
		
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.speech(Я буду делать всё, что мне вздумается!)]",
								"[npc2.speech(Я тебя не слушаю!)]",
								"[npc2.speech(Буду делать то, что хочу!)]"));
					}
				}
			}
			
			return UtilText.parse(
					Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getCharacterBeingFucked()),
					sb.toString());
		}

		@Override
		public void applyEffects() {
			applyBasePenisOrgasmRequestsReset();
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	
	// PARTNER
	
	// Doesn't have penis (or penis is not exposed), and isn't being vaginally penetrated:
	public static final SexAction PARTNER_GENERIC_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public String getActionTitle() {
			return "Оргазм";
		}
		@Override
		public String getActionDescription() {
			return "Вы достигли климакса и больше не можете сдерживать оргазм.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}
		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).getDescription();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), true).applyEffects();
			if (Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& !Main.sex.getCharacterPerformingAction().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.sex.getCharacterPerformingAction().isWearingCondom()
					&& Main.sex.getCharacterPerformingAction().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.sex.getCharacterPerformingAction().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.sex.getCharacterPerformingAction(), true);
			}
		}
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, Main.sex.getAvailableCumTargets(Main.sex.getCharacterPerformingAction()).get(0), false).isEndsSex();
		}
	};
	
	public static final SexAction GENERIC_ORGASM_DENIED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.NEGATIVE_MAJOR,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterDeniedOrgasm(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Отказ!";
		}
		@Override
		public String getActionDescription() {
			return "Вам отказали в самый последний момент!";
		}
		@Override
		public String getDescription() {
			if(isPerformingCharacterTotallyImmobilised()) {
				if(Main.sex.getCharacterPerformingAction().isAsleep()) {
					return "[npc.Name] неуютно [npc.verb(shuffle)] во сне в [npc.her], но в остальном [npc.she] [npc.do] нет реакции на то, что [npc.her] оргазм был прерван.";
				} else {
					return "[npc.Name] [npc.verb(remain)] совершенно неподвижно и [npc.do] не реагирует на то, что ей [npc.her] отказали в оргазме.";
				}
			}
			
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled() || Main.sex.getCharacterPerformingAction().isMute()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.Name] [npc.verb(let)] приглушенным, полным страдания криком, ясно давая понять, что [npc.sheIs] приходится несладко.");
					default:
						if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL_SELF)) {
							return UtilText.returnStringAtRandom("[npc.Name] [npc.verb(let)] эротичный, приглушенный крик, ясно давая понять, что [npc.she] [npc.verb(enjoy)] лишена [npc.her] оргазмов.");
						} else {
							return UtilText.returnStringAtRandom("[npc.Name] [npc.verb(let)] разочарованный, приглушенный крик, ясно давая понять, что [npc.sheIs] отчаянно хочет, чтобы ей позволили кончить.");
						}
				}
				
			} else {
				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case SUB_RESISTING:
						return UtilText.returnStringAtRandom("[npc.speech(Ты уже повеселился! А теперь оставь меня в покое!)] [npc.name] [npc.verb(scream)], [npc.speech(Н-не заставляй меня проходить через это снова!)]");
					default:
						if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_DENIAL_SELF)) {
							return UtilText.returnStringAtRandom(
									"[npc.speech(Я была так близка!)] [npc.name] [npc.verb(whine)]. Эротичный тон [npc.her] голоса ясно дает понять, что [npc.sheIs] на самом деле наслаждается тем, что ей отказывают, и просто ведет себя как [npc.she] [npc.verb(go)], игриво протестуя: [npc.speech(Дай мне кончить в следующий раз!)]");
						} else {
							return UtilText.returnStringAtRandom("[npc.speech(Нет! Я была так близка!)] [npc.name] [npc.verb(wail)] в смятении, [npc.speech(Дай мне кончить в следующий раз!)]");
						}
				}
			}
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterDeniedOrgasm(Main.sex.getCharacterPerformingAction());
			
			SexFlags.playerPreparedForCharactersOrgasm.remove(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			}
		}
	};

}
