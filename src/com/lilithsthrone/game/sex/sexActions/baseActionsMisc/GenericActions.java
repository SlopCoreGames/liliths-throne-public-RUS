package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.79
 * @version 0.4.11.1
 * @author Innoxia
 */
public class GenericActions {
	
	private static String quickSexDescription = "";

	private static SexType getPlayerOngoingMainSex(GameCharacter partner) {
		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry1 : Main.sex.getOngoingActionsMap(Main.game.getPlayer()).entrySet()) {
			// If penetrating an internal orifice, prefer that:
			if(entry1.getValue().containsKey(partner)
					&& entry1.getKey().isPenetration()
					&& ((SexAreaPenetration)entry1.getKey()).isTakesVirginity()
					&& entry1.getValue().get(partner).stream().anyMatch(orifice -> orifice.isOrifice() && ((SexAreaOrifice)orifice).isInternalOrifice())) {
				return new SexType(SexParticipantType.NORMAL, entry1.getKey(), entry1.getValue().get(partner).stream().filter(orifice -> orifice.isOrifice() && ((SexAreaOrifice)orifice).isInternalOrifice()).findFirst().get());

			// If being penetrated, prefer that:
			} else if(entry1.getValue().containsKey(partner)
					&& entry1.getKey().isOrifice()
					&& ((SexAreaOrifice)entry1.getKey()).isInternalOrifice()
					&& entry1.getValue().get(partner).stream().anyMatch(penetration -> penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity())) {
				return new SexType(SexParticipantType.NORMAL, entry1.getKey(), entry1.getValue().get(partner).stream().filter(penetration -> penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity()).findFirst().get());
			}
		}
		return null;
	}
	
	private static SexType getForeplayPreference(GameCharacter dom, GameCharacter sub) {
		if(dom.isPlayer()) {
			SexType playerMainSexType = getPlayerOngoingMainSex(sub);
			if(playerMainSexType!=null) {
				return playerMainSexType;	
			}
		}
		
		SexType preference = Main.sex.getForeplayPreference(dom, sub);
		List<SexAreaInterface> domBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(dom);
		if(domBanned==null) {
			domBanned = new ArrayList<>();
		}
		List<SexAreaInterface> subBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(sub);
		if(subBanned==null) {
			subBanned = new ArrayList<>();
		}
		
		List<SexType> sexTypesBanned = new ArrayList<>();
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom)!=null) {
			sexTypesBanned.addAll(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom));
		}
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)!=null) {
			for(SexType st : Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)) {
				sexTypesBanned.add(st.getReversedSexType());
			}
		}
		if(preference!=null) {
			if(domBanned.contains(preference.getPerformingSexArea())
					|| subBanned.contains(preference.getTargetedSexArea())
					|| !dom.isAbleToAccessCoverableArea(preference.getPerformingSexArea().getRelatedCoverableArea(dom), true)
					|| !sub.isAbleToAccessCoverableArea(preference.getTargetedSexArea().getRelatedCoverableArea(sub), true)) {
				preference = null;
			}
		}
		if(preference==null && dom.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !domBanned.contains(SexAreaOrifice.MOUTH)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
				
			} else if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
			}
		}
		
		if(sexTypesBanned.contains(preference)) {
			preference = null;
		}
		
		if(preference==null) {
			preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH); // At least give them something...
		}
		return preference;
	}
	
	private static SexType getMainSexPreference(GameCharacter dom, GameCharacter sub) {
		if(dom.isPlayer()) {
			SexType playerMainSexType = getPlayerOngoingMainSex(sub);
			if(playerMainSexType!=null) {
				return playerMainSexType;	
			}
		}
		
		SexType preference = Main.sex.getMainSexPreference(dom, sub);
		List<SexAreaInterface> domBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(dom);
		if(domBanned==null) {
			domBanned = new ArrayList<>();
		}
		List<SexAreaInterface> subBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(sub);
		if(subBanned==null) {
			subBanned = new ArrayList<>();
		}

		List<SexType> sexTypesBanned = new ArrayList<>();
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom)!=null) {
			sexTypesBanned.addAll(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom));
		}
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)!=null) {
			for(SexType st : Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)) {
				sexTypesBanned.add(st.getReversedSexType());
			}
		}
		if(preference!=null) {
			if(domBanned.contains(preference.getPerformingSexArea())
					|| subBanned.contains(preference.getTargetedSexArea())
					|| !dom.isAbleToAccessCoverableArea(preference.getPerformingSexArea().getRelatedCoverableArea(dom), true)
					|| !sub.isAbleToAccessCoverableArea(preference.getTargetedSexArea().getRelatedCoverableArea(sub), true)) {
				preference = null;
			}
		}
		if(preference==null && dom.hasPenis() && !domBanned.contains(SexAreaPenetration.PENIS) && dom.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
				
			} else if(Main.game.isAnalContentEnabled() && !subBanned.contains(SexAreaOrifice.ANUS) && sub.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)){
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			}
		}
		if(preference==null && dom.hasVagina() && !domBanned.contains(SexAreaOrifice.VAGINA) && dom.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
				
			} else if(sub.hasTail() && !subBanned.contains(SexAreaPenetration.TAIL) && sub.isTailSuitableForPenetration()){
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL);
				
			} else {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
			}
		}
		if(preference==null && dom.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !domBanned.contains(SexAreaOrifice.MOUTH)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
				
			} else if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
				
			} else if(sub.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !subBanned.contains(SexAreaOrifice.MOUTH)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
			}
		}
		
		if(sexTypesBanned.contains(preference)) {
			preference = null;
		}
		
		if(preference==null) {
			preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH); // At least give them something...
		}
		
		return preference;
	}
	
	private static boolean preventCreampie(SexType sexType, GameCharacter dom, GameCharacter sub) {
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))
				&& ((Main.sex.isConsensual() && sub.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(dom)!=OrgasmBehaviour.CREAMPIE)
						|| Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(dom)==OrgasmBehaviour.PULL_OUT)) {
			return true;
		}
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS))
				&& ((dom.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(sub)!=OrgasmBehaviour.CREAMPIE)
						|| Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(sub)==OrgasmBehaviour.PULL_OUT)) {
			return true;
		}
		return false;
	}
	
	private static String generateQuickSexDescription() {

		StringBuilder sb = new StringBuilder();
		
		HashMap<GameCharacter, GameCharacter> targetedCharacters = new HashMap<>();
		List<GameCharacter> availableSubs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		boolean allDomsAssigned = false;
		boolean allSubsAssigned = false;
		List<GameCharacter> domsNotSatisfied = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		
		// All characters in sex should know of each others' parts:
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			for(GameCharacter partner : Main.sex.getAllParticipants()) {
				if(!character.equals(partner)) {
					partner.setAllAreasKnownByCharacter(character, true);
					character.setAllAreasKnownByCharacter(partner, true);
				}
			}
		}
		
		while(!allSubsAssigned) {
			for(GameCharacter dom : domsNotSatisfied) {
				GameCharacter target = dom.isPlayer()?Main.sex.getTargetedPartner(dom):Main.sex.getInitialSexManager().getPreferredSexTarget((NPC) dom);
				if(target==null || (dom.isPlayer() && allDomsAssigned && Main.sex.isConsensual())) { // If second time through loop, and equal control, give player another target if available
					if(availableSubs.isEmpty()) { // If run out of subs, re-populate sub list.
						availableSubs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
					}
					float topWeight = -10_000;
					for(GameCharacter sub : availableSubs) {
						float weight = dom.isAttractedTo(sub)?dom.getAffection(sub):-1_000;
						if(weight>topWeight) {
							topWeight = weight;
							target = sub;
						}
					}
				}
				availableSubs.remove(target);
				if(availableSubs.isEmpty()) {
					allSubsAssigned = true;
				}
				targetedCharacters.put(dom, target);
				if(allDomsAssigned && allSubsAssigned) { // If this is the second+ time going through the loop, break as soon as all subs are accounted for
					break;
				}
			}
			allDomsAssigned = true;
			
			// Apply all generic sex effects:
			for(Entry<GameCharacter, GameCharacter> entry : targetedCharacters.entrySet()) {
				GameCharacter dom = entry.getKey();
				GameCharacter sub = entry.getValue();
				sb.append(UtilText.parse(dom, sub,
						"<p style='text-align:center;'>"
//							+ "<b>[style.boldSexDom([npc.Name])] dominantly "+(Main.sex.isConsensual()?"having sex with":"fucking")+" [style.boldSexSub([npc2.name])]</b>"
							+ "<b><b style='color:"+dom.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b> доминантно "+(Main.sex.isConsensual()?"занимаясь сексом с ":"трахая ")
								+"<b style='color:"+sub.getFemininity().getColour().toWebHexString()+";'>[npc2.name]</b></b>"
						+ "</p>"));
				
				boolean preventCreampie = false;
				
				// Foreplay:
				SexType preference;
				if(Main.sex.isInForeplay(dom)) {
					// Self-equip clothing:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						}
					}
					// Equip clothing on partner:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						}
					}
					preference = getForeplayPreference(dom, sub);
					preventCreampie = preventCreampie(preference, dom, sub);
					sb.append("<p style='margin:0; padding:0; text-align:center;'>");
					sb.append("[style.boldPurpleLight(Прелюдия)] ([style.colourSexDom("+Util.capitaliseSentence(preference.getPerformingSexArea().getName(dom, true))+")]-[style.colourSexSub("+preference.getTargetedSexArea().getName(sub, true)+")]): ");
					sb.append(dom.calculateGenericSexEffects(true, false, sub, preference, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null)));
					sb.append("</p>");
				}
				
				// Main sex:
				preference = getMainSexPreference(dom, sub);
				preventCreampie = preventCreampie(preference, dom, sub);
				// If equal sex control, dom should satisfy subs:
				int orgamsNeeded = !Main.sex.isConsensual()
						?(dom.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(dom))
						:Math.max((sub.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(sub)), (dom.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(dom)));
				for(int i=0; i<orgamsNeeded; i++) {
					// Regenerate cum by 5 minutes' worth of cum, so that there's cum for the next orgasm:
					// Moved before orgasm so the first quick sex orgasm isn't dry
					dom.incrementPenisStoredCum((5*60) * dom.getCumRegenerationPerSecond());
					sub.incrementPenisStoredCum((5*60) * sub.getCumRegenerationPerSecond());

					// Self-equip clothing:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						}
					}
					// Equip clothing on partner:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						}
					}
					sb.append("<p style='margin:0; padding:0; text-align:center;'>");
					sb.append("[style.boldPurple(Секс)] ([style.colourSexDom("+Util.capitaliseSentence(preference.getPerformingSexArea().getName(dom, true))+")]-[style.colourSexSub("+preference.getTargetedSexArea().getName(sub, true)+")]): ");
					sb.append("</p>");
					sb.append(dom.calculateGenericSexEffects(true, true, sub, preference, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null))); // This increments orgasms
//					if(sub.hasPenisIgnoreDildo() && !dom.hasFetish(Fetish.FETISH_DENIAL) && Main.sex.getSexPace(sub)!=SexPace.SUB_RESISTING) {
//						sb.append(sub.calculateGenericSexEffects(true, true, dom, new SexType(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER), GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null))); // This increments orgasms
//						//TODO
//					}
					
					if(orgamsNeeded>1) {
						dom.generateSexChoices(false, sub);
						preference = getMainSexPreference(dom, sub);
						preventCreampie = preventCreampie(preference, dom, sub);
					}
				}
			}
			
			for(GameCharacter dom : new ArrayList<>(domsNotSatisfied)) {
				if(Main.sex.getNumberOfOrgasms(dom)>=dom.getOrgasmsBeforeSatisfied() || !dom.isAbleToOrgasm()) {
					domsNotSatisfied.remove(dom);
				}
			}
			
			if(!Main.sex.isConsensual()) { // If the doms don't care about satisfying all the subs, treat all subs assigned as being true
				if(domsNotSatisfied.isEmpty()) {
					allSubsAssigned = true;
				}
			} else {
				if(domsNotSatisfied.isEmpty() && !allSubsAssigned) {
					domsNotSatisfied = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
				}
			}
		}
		
		// Append description of what clothing was equipped during sex:
		StringBuilder equippedClothingSB = new StringBuilder();
		for(Entry<GameCharacter, Map<GameCharacter, List<AbstractClothing>>> equippedMapEntry : Main.sex.getClothingEquippedDuringSex().entrySet()) {
			if(equippedMapEntry.getValue().isEmpty()) {
				continue;
			}
			if(equippedClothingSB.length()>0) {
				equippedClothingSB.append("<br/>");
			}
			GameCharacter equipper = equippedMapEntry.getKey();
			equippedClothingSB.append(UtilText.parse(equipper, "<b><span style='color:"+(equipper.getFemininity().getColour().toWebHexString())+"'>[npc.Name]</span> во время секса был(а) одет(а) в следующее:</b>"));
			for(Entry<GameCharacter, List<AbstractClothing>> characterEntry : equippedMapEntry.getValue().entrySet()) {
				GameCharacter target = characterEntry.getKey();
				for(AbstractClothing clothing : characterEntry.getValue()) {
					equippedClothingSB.append(UtilText.parse(target, "<br/>"+clothing.getDisplayName(true)+" на <span style='color:"+(target.getFemininity().getColour().toWebHexString())+"'>[npc.name]</span>"));
				}
			}
		}
		if(equippedClothingSB.length()>0) {
			equippedClothingSB.insert(0, "<p style='text-align:center;'>");
			equippedClothingSB.append("</p>");
		}
		sb.append(equippedClothingSB.toString());
		
		return sb.toString();
	}

	private static String eggLayingTargetDescription(SexAreaPenetration penetratingArea, SexAreaInterface orifice, GameCharacter characterOrgasming, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		if(!orifice.isOrifice()) {
			System.err.println("ERROR: eggLayingTargetDescription()");
			new Exception().printStackTrace();
			return "";
		}
		
		SexAreaOrifice orificeTargeted = (SexAreaOrifice)orifice;
		
		boolean condomBreaks = characterOrgasming.isWearingCondom();
		int eggCount = characterOrgasming.getPregnantLitter().getTotalLitterCount();
		String penetrationAreaText = "[npc.cock]";
		String penetrationAreaPlusText = "[npc.cock+]";
		
		if(penetratingArea==SexAreaPenetration.CLIT) {
			penetrationAreaText = "[npc.clit]";
			penetrationAreaPlusText = "[npc.clit+]";
			
		} else if(penetratingArea==SexAreaPenetration.TAIL) {
			penetrationAreaText = "[npc.tail]";
			penetrationAreaPlusText = "[npc.tail+]";
		}
		
		String hipGrindText = "";
		String selfTargetText = "[npc2.namePos]";
		if(characterOrgasming==target) {
			selfTargetText = "[npc2.her] собственный";
		}
		switch(orificeTargeted) {
			case ARMPITS:
			case ASS:
			case BREAST:
			case BREAST_CROTCH:
			case SPINNERET:
			case THIGHS:
			case URETHRA_PENIS:
			case URETHRA_VAGINA:
				break;
			case ANUS:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "вгоняя [npc.her] [npc.tail+] в "+selfTargetText+" [npc2.assCloaca]";
				} else {
					hipGrindText = "втирая [npc.her] [npc.hips] в "+selfTargetText+" [npc2.assCloaca]";
				}
				break;
			case MOUTH:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "вколачивая [npc.her] [npc.tail+] вниз "+selfTargetText+" throat";
				} else {
					hipGrindText = "втирая [npc.her] [npc.hips] в "+selfTargetText+" [npc2.face]";
				}
				break;
			case NIPPLE:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "вгоняя [npc.her] [npc.tail+] в "+selfTargetText+" [npc2.nipple+(true)]";
				} else {
					hipGrindText = "втирая [npc.her] [npc.hips] в "+selfTargetText+" [npc2.breasts]";
				}
				break;
			case NIPPLE_CROTCH:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "вгоняя [npc.her] [npc.tail+] в "+selfTargetText+" [npc2.nippleCrotch+(true)]";
				} else {
					hipGrindText = "втирая [npc.her] [npc.hips] в "+selfTargetText+" [npc2.crotchBreasts]";
				}
				break;
			case VAGINA:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "вгоняя [npc.her] [npc.tail+] в "+selfTargetText+" [npc2.pussy+]";
				} else {
					hipGrindText = "втирая [npc.her] [npc.hips] в "+selfTargetText+" groin";
				}
				break;
		}

		if(characterOrgasming==target) {
			sb.append("Желая отложить [npc.her] яйцо");
			if(eggCount!=1) {
				sb.append("s");
			}
			switch(orificeTargeted) {
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case SPINNERET:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
				case ANUS:
					sb.append(" в собственный анус [npc2.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.asshole].");
					sb.append(" С жадностью "+hipGrindText+", [npc.she] возбужденно [npc.moansVerb], пока [npc.she] [npc.verb(prepare)], чтобы отложить [npc.her] яйцо"+(eggCount!=1?"s":"")+".");
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно укладывается глубоко в желудке [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" которое осталось отложить, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" глубоко вошедшим в собственную [npc2.asshole+] [npc2.her] и [npc.verb(continue)], извлекая серию глубоко удовлетворенных [npc.moans], пока [npc.she] [npc.verb(turn)] [npc2.her] в инкубационную камеру для яиц.");
					}
					sb.append("</br>");
					sb.append("С последним [npc.moan], [npc.name] [npc.verb(pull)] назад, прежде чем с любовью погладить [npc.her] [npc.hand] по раздутому животу [npc2.her] и вздохнуть,");
					sb.append(" [npc.speech(Так-то лучше!)]");
					break;
				case MOUTH:
					sb.append(" в собственном желудке [npc2.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в глотку [npc2.her].");
					sb.append(" С жадностью "+hipGrindText+", [npc.she] возбужденно [npc.moansVerb], пока [npc.she] [npc.verb(prepare)], чтобы отложить [npc.her] яйцо"+(eggCount!=1?"s":"")+".");
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный, приглушенный [npc2.moan], когда оно выскальзывает с самого конца"+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно укладывается глубоко в желудке [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" которое осталось отложить, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" засадил глубоко в [npc2.her] собственное горло и [npc.verb(continue)] серию глубоко удовлетворенных [npc.moans], пока [npc.she] [npc.verb(turn)] [npc2.her] в инкубационную камеру для яиц.");
					}
					sb.append("</br>");
					sb.append("С последним [npc.moan], [npc.name] [npc.verb(pull)] назад, прежде чем с любовью погладить [npc.her] [npc.hand] по раздутому животу [npc2.her] и вздохнуть,");
					sb.append(" [npc.speech(Так-то лучше!)]");
					break;
				case NIPPLE:
					sb.append(" в собственные груди [npc2.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.nipple(true)].");
					sb.append(" С жадностью "+hipGrindText+", [npc.she] возбужденно [npc.moansVerb], пока [npc.she] [npc.verb(prepare)], чтобы отложить [npc.her] яйцо"+(eggCount!=1?"s":"")+".");
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"
							+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно помещено глубоко внутри похотливой груди [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" оставлено лежать, [npc.name] [npc.verb(pull)] наружу, а затем сразу же [npc.verb(penetrate)] [npc2.her] в другой [npc2.nipple(true)]. Продолжая издавать серию глубоко удовлетворенных [npc.moans], [npc.she] быстро [npc.verb(turn)] [npc2.her] [npc2.breasts] в инкубационные камеры для яиц.");
					}
					sb.append("</br>");
					sb.append("С последним [npc.moan], [npc.name] [npc.verb(pull)] назад, прежде чем с любовью потереть [npc.her] [npc.hand] по [npc2.her] набухшим [npc2.breasts] и вздохнуть,");
					sb.append(" [npc.speech(Так-то лучше!)]");
					break;
				case NIPPLE_CROTCH:
					sb.append(" в собственных [npc2.crotchBoobs] [npc2.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.nippleCrotch(true)].");
					sb.append(" С жадностью "+hipGrindText+", [npc.she] возбужденно [npc.moansVerb], пока [npc.she] [npc.verb(prepare)], чтобы отложить [npc.her] яйцо"+(eggCount!=1?"s":"")+".");
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"
							+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно помещено глубоко внутри похотливого [npc2.crotchBoobs] [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" оставлено лежать, [npc.name] [npc.verb(pull)] наружу, а затем сразу же [npc.verb(penetrate)] [npc2.her] в другой [npc2.nippleCrotch(true)]. Продолжая издавать серию глубоко удовлетворенных [npc.moans], [npc.she] быстро [npc.verb(turn)] [npc2.her] [npc2.crotchBoobs] в инкубационные камеры для яиц.");
					}
					sb.append("</br>");
					sb.append("Сделав последний [npc.moan], [npc.name] [npc.verb(pull)] назад, нежно погладив [npc.her] [npc.hand] по [npc2.her] раздутому [npc2.crotchBoobs] и вздохнув,");
					sb.append(" [npc.speech(Так-то лучше!)]");
					break;
				case VAGINA:
					sb.append(" в собственной утробе [npc2.her], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.pussy].");
					sb.append(" С жадностью "+hipGrindText+", [npc.she] возбужденно [npc.moansVerb], пока [npc.she] [npc.verb(prepare)], чтобы отложить [npc.her] яйцо"+(eggCount!=1?"s":"")+".");
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно покоится глубоко в утробе [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" которое осталось отложить, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" глубоко вошедшим в [npc2.pussy+] [npc2.her] и [npc.verb(continue)], позволяя издать серию глубоко удовлетворенных [npc.moans], пока [npc.she] [npc.verb(turn)] [npc2.her] утробу в инкубационную камеру для яиц.");
					}
					sb.append("</br>");
					sb.append("Наконец, сделав последний [npc.moan], [npc.name] [npc.verb(pull)] назад, нежно погладив [npc.her] [npc.hand] по [npc2.her] раздутому животу и вздохнув,");
					sb.append(" [npc.speech(Так-то лучше!)]");
					break;
			}
			
		} else {
			sb.append("Желая отложить [npc.her] яйцо");
			if(eggCount!=1) {
				sb.append("s");
			}
			switch(orificeTargeted) {
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case SPINNERET:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
				case ANUS:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.asshole].");
							sb.append(" Грубо "+hipGrindText+", [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Ты заберешь мое яйцо, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами до краев, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.asshole].");
							sb.append(" С жадностью "+hipGrindText+", [npc.she] восторженно [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Я собираюсь отложить яйцо в тебя!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно укладывается глубоко в желудке [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" которое осталось отложить, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" глубоко вошедший в [npc2.namePos] [npc2.asshole+], он [npc.verb(continue)], издавая серию глубоко удовлетворенных [npc.moans], пока [npc.she] [npc.verb(turn)] [npc2.her] живот, превращая его в инкубатор для яиц.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("С последним [npc.moan], [npc.name] грубо [npc.verb(pull)] назад, властно потирая [npc.her] [npc.hand] по раздутому животу [npc2.namePos] и рыча,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("С последним [npc.moan], [npc.name] нежно [npc.verb(pull)] назад, ласково потирая [npc.her] [npc.hand] по раздутому животу [npc2.namePos] и вздыхая,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+"!)]");
							break;
					}
					break;
				case MOUTH:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в глотку [npc2.her].");
							sb.append(" Грубо "+hipGrindText+", [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Ты заберешь мое яйцо, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами до краев, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в глотку [npc2.her].");
							sb.append(" С жадностью "+hipGrindText+", [npc.she] восторженно [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Я собираюсь отложить яйцо в тебя!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный, приглушенный [npc2.moan], когда оно выскальзывает с самого конца"+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно укладывается глубоко в желудке [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" которое осталось отложить, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" глубоко вонзив [npc2.namePos] в горло, [npc.verb(continue)], издавая серию глубоко удовлетворенных [npc.moans], пока [npc.she] [npc.verb(turn)] [npc2.her] в инкубатор для яиц.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("С последним [npc.moan], [npc.name] грубо [npc.verb(pull)] назад, властно потирая [npc.her] [npc.hand] по раздутому животу [npc2.namePos] и рыча,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("С последним [npc.moan], [npc.name] нежно [npc.verb(pull)] назад, ласково потирая [npc.her] [npc.hand] по раздутому животу [npc2.namePos] и вздыхая,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+"!)]");
							break;
					}
					break;
				case NIPPLE:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.nipple(true)].");
							sb.append(" Грубо "+hipGrindText+", [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Ты заберешь мое яйцо, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами до краев, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.nipple(true)].");
							sb.append(" С жадностью "+hipGrindText+", [npc.she] восторженно [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Я собираюсь отложить яйцо в тебя!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"
							+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно помещено глубоко внутри похотливой груди [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" оставив лежать, [npc.name] [npc.verb(pull)] наружу, а затем сразу же [npc.verb(penetrate)] [npc2.namePos] другие [npc2.nipple(true)]. Продолжая издавать серию глубоко удовлетворенных [npc.moans], [npc.she] быстро [npc.verb(turn)] [npc2.namePos] [npc2.breasts] в инкубаторы для яиц.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("С последним [npc.moan], [npc.name] грубо [npc.verb(pull)] назад, прежде чем властно растирая [npc.her] [npc.hand] по [npc2.namePos] набухшим [npc2.breasts] и рыча,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("С последним [npc.moan], [npc.name] [npc.verb(pull)] назад, прежде чем нежно растирая [npc.her] [npc.hand] по [npc2.namePos] набухшим [npc2.breasts] и вздыхая,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+"!)]");
							break;
					}
					break;
				case NIPPLE_CROTCH:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.nippleCrotch(true)].");
							sb.append(" Грубо "+hipGrindText+", [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Ты заберешь мое яйцо, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами до краев, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.nippleCrotch(true)].");
							sb.append(" С жадностью "+hipGrindText+", [npc.she] восторженно [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Я собираюсь отложить яйцо в тебя!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"
							+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно помещено глубоко внутри похотливого [npc2.crotchBoobs] [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" оставив лежать, [npc.name] [npc.verb(pull)] наружу, а затем сразу же [npc.verb(penetrate)] [npc2.namePos] другие [npc2.nippleCrotch(true)]. Продолжая издавать серию глубоко удовлетворенных [npc.moans], [npc.she] быстро [npc.verb(turn)] [npc2.namePos] [npc2.crotchBoobs] в инкубаторы для яиц.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("С последним [npc.moan], [npc.name] грубо [npc.verb(pull)] назад, прежде чем властно растирая [npc.her] [npc.hand] по [npc2.namePos] набухшим [npc2.crotchBoobs] и рыча,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("Сделав последний [npc.moan], [npc.name] [npc.verb(pull)] назад, нежно потирая [npc.her] [npc.hand] по [npc2.namePos] набухшим [npc2.crotchBoobs] и вздыхая,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+"!)]");
							break;
					}
					break;
				case VAGINA:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(slam)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.pussy].");
							sb.append(" Грубо "+hipGrindText+", [npc.she] [npc.verb(sneer)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Ты заберешь мое яйцо, [npc2.bitch]!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами до краев, [npc2.bitch]!)]");
							}
							break;
						default:
							sb.append(" в [npc2.name], [npc.name] [npc.verb(let)] наружу [npc.a_moan+] и [npc.verb(push)] [npc.her] "+penetrationAreaPlusText+" как можно глубже в [npc2.her] [npc2.pussy].");
							sb.append(" С жадностью "+hipGrindText+", [npc.she] восторженно [npc.verb(exclaim)],");
							if(eggCount==1) {
								sb.append(" [npc.speech(Я собираюсь отложить яйцо в тебя!)]");
							} else {
								sb.append(" [npc.speech(Я набью тебя яйцами!)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("С этим [npc2.name] внезапно [npc2.verb(feel)] отчетливую бугристую массу яйца, проходящую по всей длине [npc.namePos] "+penetrationAreaText+", и [npc2.she] не может не издать встревоженный [npc2.moan], когда оно выдавливается наружу"+(condomBreaks?", разрывая при этом презерватив [npc.namePos],":"")+" и надежно покоится глубоко в утробе [npc2.her].");
					if(eggCount>1) {
						sb.append(" Все еще с "+(Util.intToString(eggCount-1))+" яйцом,"+(eggCount>2?"s":"")+" которое осталось отложить, [npc.name] [npc.verb(keep)] [npc.her] "+penetrationAreaPlusText+" глубоко вошедший в [npc2.namePos] [npc2.pussy+], и [npc.verb(continue)], чтобы издать серию глубоко удовлетворенных [npc.moans], пока [npc.she] [npc.verb(turn)] [npc2.her] матку в инкубатор для яиц.");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("Наконец, сделав последний [npc.moan], [npc.name] грубо [npc.verb(pull)] назад, властно потирая [npc.her] [npc.hand] по [npc2.namePos] раздутому животу и рыча,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+", [npc2.bitch]!)]");
							break;
						default:
							sb.append("Наконец, сделав последний [npc.moan], [npc.name] [npc.verb(pull)] назад, нежно потирая [npc.her] [npc.hand] по [npc2.namePos] раздутому животу и вздыхая,");
							sb.append(" [npc.speech(Следи за тем, чтобы хорошо позаботился о моем "+(eggCount>1?"дети":"ребенок")+"!)]");
							break;
					}
					break;
			}
		}
		
		return UtilText.parse(characterOrgasming, target, sb.toString());
	}
	
	private static GameCharacter getCharacterToBeEgged(GameCharacter performingCharacter, GameCharacter targetedCharacter, SexAreaPenetration penetratingArea, SexAreaInterface targetedOrifice) {
		Set<GameCharacter> ongoingCharacters = Main.sex.getOngoingCharactersUsingAreas(performingCharacter, penetratingArea, targetedOrifice);
		if(ongoingCharacters.isEmpty()) {
			return null;
		}
		GameCharacter characterPenetrated = ongoingCharacters.iterator().next();
		
		List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(performingCharacter, penetratingArea);
		if(charactersPenetrated.contains(targetedCharacter)) {
			characterPenetrated = targetedCharacter;
		}
		
		return characterPenetrated;
	}
	
	public static final SexAction PLAYER_SKIP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
//		@Override
//		public SexActionPriority getPriority() {
//			if(Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction())) {
//				return SexActionPriority.UNIQUE_MAX; // So that this action is available with the 'Cocooned!' action.
//			}
//			return super.getPriority();
//		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_ORANGE;
		}
		@Override
		public String getActionTitle() {
			return "Быстрый секс";
		}
		@Override
		public String getActionDescription() {
			return "Пропускает эту сцену секса, но всё же [style.boldSex(применяются все соответствующие эффекты)], как если бы сцена состоялась, исходя из предпочтений вашего партнера. Перед завершением сцены будет показано описание итогового секса.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isAbleToSkipSexScene()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String applyEndEffects(){
			quickSexDescription = "";
			return "";
		}
		@Override
		public String getDescription() {
			if(quickSexDescription.isEmpty()) {
				quickSexDescription = generateQuickSexDescription();
			}
			return quickSexDescription;
		}
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction GENERIC_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.isDom(Main.sex.getCharacterPerformingAction()) && !Main.sex.isConsensual());
		}
		
		@Override
		public String getActionTitle() {
			return "Сопротивляться";
		}

		@Override
		public String getActionDescription() {
			return "Сопротивляться сексу с [npc2.name].";
		}

		@Override
		public String getDescription() {
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)] и [npc.verb(kick)] [npc2.name], пока [npc.she] отчаянно [npc.verb(try)], пытаясь вырваться из [npc2.her] хватки, но [npc.her] усилия оказываются тщетными, так как [npc2.she] легко [npc2.verb(keep)] [npc.herHim], прижатой к стене.",
						
						"Сопротивляясь [npc2.name], [npc.name] отчаянно [npc.verb(let)] [npc.a_sob+], пока [npc.she] слабо [npc.verb(try)], пытаясь вырваться из [npc2.her], но хватка [npc2.her] слишком сильна для [npc.herHim], и [npc2.name] с легкостью [npc2.verb(keep)] [npc.herHim], прижимая её обратно к стене.",
						
						"Умоляя [npc2.name] оставить [npc.herHim] в покое, [npc.name] отчаянно [npc.verb(struggle)] против [npc2.herHim], а [npc.sobbing] в смятении, пока [npc2.she] [npc2.verb(push)] [npc.herHim] обратно к стене.");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] [npc.verb(try)] to crawl away from [npc2.name], but [npc.her] efforts prove to be in vain as [npc2.name] [npc2.verb(grab)] [npc.her] [npc.hips] and [npc2.verb(pull)] [npc.her] [npc.ass] back into [npc2.her] groin.",
						
						"Пытаясь уползти от [npc2.name] на четвереньках, [npc.name] [npc.verb(let)] [npc.a_sob+], пока [npc2.she] [npc2.verb(grasp)] [npc.her] [npc.hips], прежде чем снова притянуть [npc.herHim] на место.",
						
						"Умоляя [npc2.name] оставить [npc.herHim] в покое, [npc.name] отчаянно [npc.verb(try)] проползти подальше от [npc2.herHim], [npc.sobbing] в смятении, пока [npc2.she] [npc2.verb(take)] удерживает [npc.her] [npc.hips] и [npc2.verb(pull)] заталкивает [npc.herHim] назад в [npc2.herHim].");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)], и [npc.verb(kick)] [npc2.name], пока [npc.she] отчаянно [npc.verb(try)], пытаясь вырваться из [npc2.her] хватки, но [npc.her] усилия оказываются напрасными, так как [npc2.name] с легкостью [npc2.verb(keep)] [npc.herHim], прижатого к стене.",
						
						"Сопротивляясь [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], пока [npc.she] слабо [npc.verb(try)], чтобы выскользнуть из [npc2.her] рук, но хватка [npc2.her] слишком сильна для [npc.herHim], и [npc2.she] с легкостью [npc2.verb(keep)] [npc.herHim], прижатого к стене.",
						
						"Умоляя [npc2.name] оставить [npc.herHim] в покое, [npc.name] отчаянно [npc.verb(struggle)] против [npc2.herHim], в то время как [npc.sobbing] в смятении, пока [npc2.she] [npc2.verb(push)] [npc.herHim] у стены.");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)], пытаясь оттолкнуть [npc2.namePos] пах от [npc.her] [npc.face], но [npc.her] усилия оказываются напрасными, так как [npc2.name] [npc2.verb(grab)] хватает [npc.her] за голову и [npc2.verb(pull)] [npc.herHim] обратно в [npc2.her] промежность.",
						
						"Сопротивляясь [npc2.name], [npc.name] [npc.verb(let)] [npc.a_sob+], пока [npc.she] слабо [npc.verb(try)], чтобы оттянуть [npc.her] [npc.face] от паха [npc2.her], но хватка [npc2.namePos] на [npc.her] слишком сильна, и [npc2.she] быстро [npc2.verb(force)] [npc.herHim] обратно на место.",
								
						"Умоляя [npc2.name] оставить [npc.herHim] в покое, [npc.name] отчаянно [npc.verb(struggle)] против [npc2.herHim]; [npc.sobbing] в смятении, пока [npc2.she] [npc2.verb(pull)] [npc.her] [npc.face] обратно в [npc2.her].");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LYING_DOWN)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)], чтобы оттолкнуть [npc2.name] от [npc.herHim], пока [npc.she] отчаянно [npc.verb(try)], пытаясь вырваться из-под [npc2.herHim], но [npc.her] усилия оказываются напрасными, так как [npc2.name] с легкостью [npc2.verb(pin)] [npc.herHim] на пол.",
						
						"Сопротивляясь [npc2.name], [npc.name] [npc.verb(let)] из [npc.a_sob+], пока [npc.she] слабо [npc.verb(try)], пытаясь вырваться из-под [npc2.herHim], но [npc2.name] [npc2.verb(press)] тело [npc2.her] на [npc.hers], не давая [npc.herHim] ускользнуть.",
						
						"Умоляя [npc2.name] оставить [npc.herHim] в покое, [npc.name] отчаянно [npc.verb(struggle)] против [npc2.herHim]; [npc.sobbing] в смятении, пока [npc2.she] [npc2.verb(use)] тело [npc2.her], чтобы прижать [npc.herHim] к полу.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)] и [npc.verb(kick)] [npc2.name], пока [npc.she] отчаянно [npc.verb(try)], пытаясь вырваться из хватки [npc2.her], но [npc.her] усилия оказываются напрасными, так как [npc2.she] с легкостью [npc2.verb(continue)], удерживая [npc.herHim].",
								
						"Сопротивляясь [npc2.name], [npc.name] [npc.verb(let)] из [npc.a_sob+], пока [npc.she] слабо [npc.verb(try)], пытаясь вырваться из хватки [npc2.her].",
						
						"Умоляя [npc2.name] оставить [npc.herHim] в покое, [npc.name] отчаянно [npc.verb(struggle)] против [npc2.herHim], [npc.sobbing] в смятении, пока [npc2.she] легко [npc2.verb(hold)] [npc.herHim] на месте.");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
		}
	};
	
	public static final SexAction PLAYER_SELF_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Вырастить член (себе)";
		}

		@Override
		public String getActionDescription() {
			return "Использовать свои способности к трансформации, чтобы вырастить член самому себе. <b>Когда секс закончится, выросший член исчезнет автоматически.</b>";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isAbleToSelfTransform()
					&& !Main.game.getPlayer().hasPenis()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {//TODO resisting variations
			if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
				return "Решив использовать свои способности к трансформации, чтобы отрастить себе толстый демонический член, вы начинаете ухмыляться, когда [npc.moanVerb], [npc.speech(Тебе это понравится!)]";
			} else if (Main.game.getPlayer().isYouko()){
				return "Решив использовать свои способности к трансформации, чтобы отрастить себе толстый лисий член с узлом, вы начинаете ухмыляться, когда [npc.moanVerb], [npc.speech(Тебе это понравится!)]";
			} else {
				return "Решив использовать свои способности к трансформации, чтобы отрастить себе толстый слизистый член, вы начинаете ухмыляться, когда [npc.moanVerb], [npc.speech(Тебе это понравится!)]";
			}
		}

		@Override
		public String applyEffectsString() {
			Main.sex.getCharactersGrewCock().add(Main.game.getPlayer());
			
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
				sb.append(Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON, Main.game.getPlayer().getPreviousPenisType()!=PenisType.DEMON_COMMON));
			} else if (Main.game.getPlayer().isYouko()){
				sb.append(Main.game.getPlayer().setPenisType(PenisType.FOX_MORPH, Main.game.getPlayer().getPreviousPenisType()!=PenisType.FOX_MORPH));
			} else {
				AbstractPenisType penisType = RacialBody.valueOfRace(Main.game.getPlayer().getBody().getFleshSubspecies().getRace()).getPenisType();
				if(Main.game.getPlayer().getPreviousPenisType()!=null) {
					penisType = Main.game.getPlayer().getPreviousPenisType();
				}
				sb.append(Main.game.getPlayer().setPenisType(penisType, Main.game.getPlayer().getPreviousPenisType()!=penisType));
			}
			
			Main.game.getPlayer().fillCumToMaxStorage();
			
			if(Main.game.getPlayer().getPreviousPenisType()==null) {
				int size = Main.sex.getCharacterPerformingAction().getLegConfiguration().isLargeGenitals()?40:20;
				if(Main.game.getPlayer().getPenisRawSizeValue() < size) {
					sb.append(Main.game.getPlayer().setPenisSize(size));
				}
				if(Main.game.getPlayer().getPenisRawCumStorageValue() < Main.sex.getCharacterPerformingAction().getFleshSubspecies().getRace().getRacialBody().getCumProduction()) {
					sb.append(Main.game.getPlayer().setPenisCumStorage(Main.sex.getCharacterPerformingAction().getFleshSubspecies().getRace().getRacialBody().getCumProduction()));
				}
			}
			
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(character instanceof NPC) {
					for(GameCharacter target : Main.sex.getAllParticipants()) {
						if(!target.equals(character) && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
							((NPC)character).generateSexChoices(false, target, null);
						}
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction PLAYER_GET_PARTNER_TO_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Вырастить член";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON
					|| Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				return "Получите [npc2.name], чтобы использовать [npc2.her] демонические способности к самотрансформации и отрастить [npc2.herself] демонический член.";
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				return "Получите [npc2.name], чтобы использовать врожденную самотрансформирующуюся силу [npc2.her] и отрастить [npc2.herself] лисий член.";
			} else {
				return "Получите [npc2.name], чтобы использовать самотрансформирующие способности слизистого тела [npc2.her] и вырастить [npc2.herself] слизистый член.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isMasturbation()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					//TODO make the NPC refuse with a reason before blocking this action
//					&& (Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.game.getPlayer())
//							|| !Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative())
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToSelfTransform()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON
					|| Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				return "Усмехаясь, вы дразните [npc2.name]: [npc.speech(Как насчет того, чтобы использовать свои силы трансформации и отрастить себе классный толстый демонический член, чтобы мы могли повеселиться еще больше!)]<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.Name] издает тихий смешок, и когда вы смотрите на обнаженное пах [npc2.her], вы видите, как под [npc2.her] [npc2.skin] начинает проступать большая выпуклость. Прежде чем вы успеваете передумать, она быстро превращается в жирный демонический член, и пока вы во все глаза пялитесь на маленькие извивающиеся бугорки, выступающие по всей длине ствола, из кончика просачивается и капает капля предэякулята."
							:"[npc2.Name] издает тихий смешок, и когда вы смотрите на пах [npc2.her], вы видите, как в [npc2.her] быстро формируется огромная выпуклость "
									+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+". Прежде чем вы успеваете передумать, [npc2.she] издает [npc2.a_moan+], и вы понимаете, что под [npc2.her] у [npc2.sheHas] теперь спрятан огромный демонический член.");
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				return "Усмехаясь, вы дразните [npc2.name]: [npc.speech(Как насчет того, чтобы использовать свои силы трансформации и отрастить себе классный толстый лисий член, чтобы мы могли повеселиться еще больше!)]<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
						?"[npc2.Name] издает тихий смешок, и когда вы смотрите на обнаженное пах [npc2.her], вы видите, как под [npc2.her] [npc2.skin] начинает проступать большая выпуклость. Прежде чем вы успеваете передумать, она быстро превращается в лисий член, и пока вы во все глаза пялитесь на его пульсирующую длину, из кончика просачивается и капает капля предэякулята."
						:"[npc2.Name] издает тихий смешок, и когда вы смотрите на пах [npc2.her], вы видите, как в [npc2.her] быстро формируется огромная выпуклость "
						+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+". Прежде чем ты успеваешь передумать, [npc2.she] издает [npc2.a_moan+], и ты понимаешь, что под [npc2.her] одеждой у [npc2.sheHas] теперь спрятан огромный лисий член.");
			} else {
				return "Усмехаясь, глядя на [npc2.name], ты дразнишь: [npc.speech(Как насчет того, чтобы использовать свои способности к трансформации и отрастить себе классный толстый скользкий член, чтобы мы могли повеселиться еще больше!)]<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.Name] тихо хихикает, и когда ты смотришь на обнаженное паховое пространство [npc2.her], ты видишь, как под [npc2.her] [npc2.skin] начинает проступать большая выпуклость. Прежде чем ты успеваешь передумать, она быстро превращается в толстый скользкий член, и пока ты во все глаза смотришь на его пульсирующую длину, с кончика просачивается и капает капля предэякулята."
							:"[npc2.Name] издает тихий смешок, и когда вы смотрите на пах [npc2.her], вы видите, как в [npc2.her] быстро формируется огромная выпуклость "
									+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+". Прежде чем ты успеваешь передумать, [npc2.she] издает [npc2.a_moan+], и ты понимаешь, что под [npc2.her] одеждой у [npc2.sheHas] теперь спрятан огромный скользкий член.");
			}
		}
		
		@Override
		public String applyEffectsString() {
			Main.sex.getCharactersGrewCock().add(Main.sex.getCharacterTargetedForSexAction(this));
			
			StringBuilder sb = new StringBuilder();
			boolean discoveredPenisColour = Main.sex.getCharacterTargetedForSexAction(this).isBodyCoveringTypesDiscovered(BodyCoveringType.PENIS);
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON || Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(PenisType.DEMON_COMMON, Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=PenisType.DEMON_COMMON));
				if(!discoveredPenisColour) {
					Main.sex.getCharacterTargetedForSexAction(this).setSkinCovering(BodyCoveringType.PENIS, Main.sex.getCharacterTargetedForSexAction(this).getCovering(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false);
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(PenisType.FOX_MORPH, Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=PenisType.FOX_MORPH));
				if(!discoveredPenisColour) {
					Main.sex.getCharacterTargetedForSexAction(this).setSkinCovering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, false);
				}
				
			} else {
				AbstractPenisType penisType = RacialBody.valueOfRace(Main.sex.getCharacterTargetedForSexAction(this).getBody().getFleshSubspecies().getRace()).getPenisType();
				if(Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=null) {
					penisType = Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType();
				}
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(penisType, Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=penisType));
			}
			

			if(Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()==null) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getPenisRawCumStorageValue() < 150) {
					Main.sex.getCharacterTargetedForSexAction(this).setPenisCumStorage(150);
				}
				Main.sex.getCharacterTargetedForSexAction(this).fillCumToMaxStorage();
				if(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()) {
					Main.sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.ZERO_VESTIGIAL);
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getTesticleSize().getValue() < TesticleSize.THREE_LARGE.getValue()){
					Main.sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.THREE_LARGE);
				}
				if(Main.sex.getCharacterTargetedForSexAction(this).getPenisGirth().getValue() < PenetrationGirth.THREE_AVERAGE.getValue()) {
					sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisGirth(PenetrationGirth.THREE_AVERAGE));
				}
				int size = Main.sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isLargeGenitals()?40:20;
				if(Main.sex.getCharacterTargetedForSexAction(this).getPenisRawSizeValue() < size) {
					sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisSize(size));
				}
			}
			
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(character instanceof NPC) {
					for(GameCharacter target : Main.sex.getAllParticipants()) {
						if(!target.equals(character) && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
							((NPC)character).generateSexChoices(false, target, null);
						}
					}
				}
			}
			
			return sb.toString();
		}
	};

	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public Colour getHighlightColour() {
			return PresetColour.PSYCHOACTIVE;
		}
		@Override
		public String getActionTitle() {
			return "Успокаивающее внушение";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.Name] находится под воздействием психоактивного вещества. Используй это в своих интересах и гипнотически внуши, что [npc2.she] нравится заниматься с тобой сексом.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return 
//					!Main.sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					Main.sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.PSYCHOACTIVE)
					&& !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || (Main.sex.getCharacterTargetedForSexAction(this).getLust()>25 && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>Желая воспользоваться тем, что [npc2.nameIsFull] под сильным воздействием психоактивного вещества, [npc.name] [npc.verb(lean)] к [npc2.herHim] и [npc.moansVerb], [npc.speech(Ты ведь на самом деле не особо хочешь заниматься со мной сексом, верно?)]</p><p>[npc2.Name] не может не согласиться с тем, что говорит [npc.sheIs], и [npc2.name] прерывисто [npc2.verb(answer)], [npc2.speech(Да... я... я сама не знаю, почему занимаюсь с тобой сексом...)]</p><p>Немного надавив,"+(!Main.sex.getCharacterPerformingAction().isPlayer()?" и подгоняемый [npc.her] фетишем к сексу без согласия,":"")+" [npc.name] [npc.verb(continue)], [npc.speech(Ты бы предпочла, чтобы я не трахал тебя прямо сейчас, так ведь?)]</p>");
			
			sb.append("<p>");
				if(Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("Когда гипнотическое внушение проникает в голову [npc2.namePos], [npc2.she] [npc2.verb(let)] разочарованный вздох, [npc2.speech(Это не так уж и весело...)]");
					
				} else {
					if(LustLevel.getLustLevelFromValue(Main.sex.getCharacterTargetedForSexAction(this).getLust()-50).getSexPaceSubmissive()==SexPace.SUB_RESISTING) {
						sb.append("Когда гипнотическое внушение проникает в голову [npc2.namePos], [npc2.she] [npc2.verb(let)] полный тревоги крик, [npc2.speech(Погоди, п-почему это происходит?! Пожалуйста, прекрати! Уйди от меня!)]");
					} else {
						sb.append("Когда гипнотическое внушение проникает в голову [npc2.namePos], [npc2.she] [npc2.verb(let)] разочарованный вздох, [npc2.speech(Это не так уж и весело...)]");
					}
				}
			sb.append("</p>");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterTargetedForSexAction(this).incrementLust(-50, false);
		}
	};
	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public Colour getHighlightColour() {
			return PresetColour.PSYCHOACTIVE;
		}
		@Override
		public String getActionTitle() {
			return "Похотливое внушение";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.Name] находится под воздействием психоактивного вещества. Используй это в своих интересах и гипнотически внуши, что [npc2.she] обожает заниматься с тобой сексом.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return 
//					!Main.sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					Main.sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.PSYCHOACTIVE)
					&& !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || (Main.sex.getCharacterTargetedForSexAction(this).getLust()<75 && !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Желая воспользоваться тем, что [npc2.nameIsFull] под воздействием психоактивного вещества [npc.name] [npc.verb(lean)] в сторону [npc2.herHim] и [npc.moanVerb], [npc.speech(Ты ведь обожаешь трахаться со мной, правда?)]</p>");
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>Когда [npc.name] произносит это, ты внезапно чувствуешь, как мягкое тепло затуманивает твой разум, и смутно осознаешь, как твои [npc2.eyes] затуманиваются, когда ты отвечаешь: [npc2.speech(Да... я... я обожаю трахаться с тобой...)]</p>");
			} else {
				sb.append("<p>[npc2.NamePos] [npc2.eyes] слегка затуманиваются, когда [npc2.she] отвечает: [npc2.speech(Да... я... я обожаю трахаться с тобой...)]</p>");
			}
			
			sb.append( "<p>Наступая еще решительнее, [npc.name] [npc.verb(continue)], [npc.speech(Ты ведь обожаешь умолять меня оттрахать тебя, верно?)]</p>");

			if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>Когда твой разум окончательно поддается гипнотическому внушению, ты понимаешь, что не хочешь ничего, кроме как чтобы тебя оттрахала [npc.name], и ты жадно [npc2.moansVerb], [npc2.speech(Да... Да! Пожалуйста, трахай меня! Мне <i>нужно</i>, чтобы ты трахал меня!)]</p>");
			} else {
				sb.append("<p>По мере того как гипнотическое внушение проникает в голову [npc2.namePos], [npc2.she] начинает звучать гораздо более нетерпеливо, и [npc2.moansVerb], [npc2.speech(Да... Да, [npc.name]! Пожалуйста, трахай меня! Мне <i>нужно</i>, чтобы ты трахал меня!)]</p>");
			}
			
			sb.append( "<p>[npc.speech(Умница, [npc2.girl],)] [npc.name] [npc.verb(say)], приятно слышать, что [npc2.namePos] разум [npc.hers] готов крутить так, как [npc.she] [npc.verb(see)] пожелает. [npc.speech(Я дам тебе то, что ты хочешь!)]</p>");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterTargetedForSexAction(this).incrementLust(50, false);
		}
	};
	
	public static final SexAction GENERIC_DENY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Отвергнуть";
		}

		@Override
		public String getActionDescription() {
			return "Заставить [npc2.name] замереть и не шевелиться, удерживая их в одном положении, пока они не потеряют значительную часть своего возбуждения.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isMasturbation()
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction()) // can't deny when hidden
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterTargetedForSexAction(this))) { // can't deny hidden characters
				if(Main.sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Main.sex.getCharacterTargetedForSexAction(this).getArousal()>=50 // They will not deny if less than 50 arousal.
							&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_DENIAL).isPositive()
							&& !Main.sex.getLastUsedSexAction(Main.sex.getCharacterPerformingAction()).equals(this); // Do not deny twice in a row
				}
			}
			return false;
		}

		@Override
		public String getDescription() {//TODO BDSM gear. penetrations.
			UtilText.nodeContentSB.setLength(0);
			
			boolean alreadyOrgasmed = Main.sex.getNumberOfOrgasms(Main.sex.getCharacterTargetedForSexAction(this))>0;
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(!Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"Мягко, но уверенно сжав [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] почти неподвижно, тихо [npc.moaning], пока [npc.she] [npc.verb(wait)] для [npc2.herHim], чтобы тот успокоился, ",
							"Крепко сжав [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] на месте, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы тот успокоился, ",
							"Мягко [npc.moaning], пока [npc.she] [npc.verb(take)] уверенно сжимает [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(tease)] [npc2.name], как [npc.she] [npc.verb(wait)] для [npc2.herHim], чтобы тот успокоился, "));
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
									"[npc.speech(Умница, [npc2.girl]... Мы ведь не хотим, чтобы ты достиг оргазма "+(alreadyOrgasmed?"снова так быстро,":"прямо сейчас,")+" не так ли?)]",
									"[npc.speech(А теперь веди себя хорошо, [npc2.girl], и просто немного успокойся. Ты ведь не думал, что снова получишь оргазм "+(alreadyOrgasmed?"так":"")+" быстро со мной, верно?)]",
									"[npc.speech(Хороший [npc2.girl]... Просто немного успокойся. Мы ведь не хотим, чтобы ты достиг пика "+(alreadyOrgasmed?"снова так быстро,":"слишком быстро")+" не так ли?)]"));
					} else {
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
									"Отстраняясь от [npc2.name], [npc.name] [npc.verb(leave)] [npc2.herHim] без стимуляции на несколько мгновений, мягко [npc.moaning] к [npc.herself], пока [npc.she] [npc.verb(wait)], чтобы [npc2.herHim] мог успокоиться.",
									"Прекращая физический контакт с [npc2.name], [npc.name] [npc.verb(stop)] стимулирует [npc2.herHim] и [npc.verb(let)] из [npc.a_moan+], пока [npc.she] [npc.verb(wait)], чтобы [npc2.herHim] мог успокоиться.",
									"Сделав паузу на мгновение, [npc.name] [npc.verb(stop)] стимулирует [npc2.name] и тихо [npc.verbMoans], пока [npc.she] [npc.verb(wait)], чтобы [npc2.herHim] мог успокоиться."));
					}
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"Крепко сжав [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] почти неподвижно, [npc.moaning+], пока [npc.she] [npc.verb(wait)], чтобы [npc2.herHim] мог успокоиться, ",
								"Крепко сжав [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] на месте, издавая [npc.a_moan+], пока [npc.she] [npc.verb(force)] [npc2.herHim], чтобы тот успокоился, ",
								"[npc.Moaning+], пока [npc.she] [npc.verb(take)] крепко сжимает [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(tease)] [npc2.name], пока [npc.she] [npc.verb(wait)], чтобы [npc2.herHim] мог успокоиться, "));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(Отличный [npc2.girl]! Мы ведь не хотим, чтобы ты кончил "+(alreadyOrgasmed?"снова так быстро,":"прямо сейчас,")+" прямо сейчас, верно?!)]",
								"[npc.speech(А теперь веди себя хорошо, [npc2.girl], и просто немного успокойся. Ты ведь не думал, что снова получишь оргазм "+(alreadyOrgasmed?"так":"")+" так быстро со мной, а?!)]",
								"[npc.speech(Хороший [npc2.girl]! Просто успокойся на мгновение! Мы ведь не хотим, чтобы ты кончил "+(alreadyOrgasmed?"снова так быстро,":"слишком быстро")+" прямо сейчас, верно?!)]"));
						break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"Крепко и грубо сжав [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(force)] [npc2.herHim], заставляя замереть и угрожающе рычать, пока [npc.she] [npc.verb(wait)], чтобы [npc2.herHim] успокоился, ",
								"Грубо сжав [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(force)] [npc2.herHim], заставляя оставаться на месте и издавая угрожающее рычание, пока [npc.she] [npc.verb(make)], чтобы [npc2.herHim] успокоился, ",
								"Издавая угрожающее рычание, пока [npc.she] [npc.verb(take)] крепко сжимает [npc2.namePos] [npc2.arms], а [npc.name] [npc.verb(threaten)] [npc.her] партнера, чтобы [npc.she] [npc.verb(wait)] для [npc2.herHim] успокоился, "));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(Не двигайся, тупой [npc2.bitch]! Ты не получишь свой оргазм "+(alreadyOrgasmed?"снова так быстро,":"прямо сейчас,")+" со мной, понял?!)]",
								"[npc.speech(Не двигайся, [npc2.bitch], и успокойся! Ты же не думала, что дойдешь до оргазма "+(alreadyOrgasmed?"так":"")+" так быстро со мной, а?!)]",
								"[npc.speech(Вот так, [npc2.bitch]! Просто замри и успокойся! Нам бы не хотелось, чтобы ты кончила "+(alreadyOrgasmed?"снова так быстро,":"слишком быстро")+" прямо сейчас, верно?!)]"));
						break;
				default:
					break;
			}
			if(!Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				UtilText.nodeContentSB.append("<br/><br/>");
				boolean nameKnown = Main.sex.getCharacterPerformingAction().isPlayerKnowsName();
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
									"[npc2.speechNoExtraEffects("+(nameKnown?"Нет, [npc.name],":"Нет...")+")] [npc2.name] [npc2.verb(reply)], пытаясь сдержать волнение в [npc2.her] голосе, [npc2.speechNoExtraEffects(Я постараюсь терпеть...)]",
									"[npc2.speechNoExtraEffects("+(nameKnown?"Нет, [npc.name],":"Нет...")+")] [npc2.name] [npc2.moansVerb], не в силах подавить сильное возбуждение в [npc2.her] голосе, [npc2.speechNoExtraEffects(Я сделаю всё возможное, чтобы сдержаться...)]",
									"[npc2.speechNoExtraEffects("+(nameKnown?"Нет, [npc.name]...":"Нет...")+")] [npc2.name] [npc2.verb(answer)], прежде чем попытаться подавить отчаянный [npc2.moan], [npc2.speechNoExtraEffects(~Ммм!~ Я постараюсь сдерживаться!)]"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
										"[npc2.speechNoExtraEffects("+(nameKnown?"Нет, [npc.name]...":"Нет...")+")] [npc2.name] [npc2.verb(reply)], [npc2.speechNoExtraEffects(Я постараюсь терпеть...)]",
										"[npc2.speechNoExtraEffects("+(nameKnown?"Нет, [npc.name]...":"Нет...")+")] [npc2.name] [npc2.moansVerb], [npc2.speechNoExtraEffects(Я сделаю всё возможное, чтобы сдержаться...)]",
										"[npc2.speechNoExtraEffects("+(nameKnown?"Нет, [npc.name]...":"Нет...")+")] [npc2.name] [npc2.verb(answer)], [npc2.speechNoExtraEffects(Я постараюсь сдерживаться!)]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
										"[npc2.speechNoExtraEffects(Просто уйди от меня!)] [npc2.name] лихорадочно [npc2.sobsVerb], [npc2.speechNoExtraEffects(Терпеть это не могу!)]",
										"[npc2.speechNoExtraEffects(Отпусти меня!)] [npc2.name] отчаянно [npc2.sobsVerb], пытаясь вырвать [npc2.herself] из хватки [npc.namePos], [npc2.speechNoExtraEffects(Прекрати! Уйди от меня!)]",
										"[npc2.speechNoExtraEffects(Да почему ты просто не отпустишь меня?!)] [npc2.name] [npc2.sobsVerb], пока [npc2.she] слабо [npc2.verb(try)], пытаясь отстраниться от [npc2.name], [npc2.speechNoExtraEffects(Я этого не хочу!)]"));
						break;
					default:
						break;
				}
			}
			UtilText.nodeContentSB.append("<br/><br/>");
			UtilText.nodeContentSB.append("Как только становится ясно, что [npc2.nameHasFull] успокоилась, [npc.name] [npc.verb(loosen)] хватку [npc.her], после чего продолжается, пока [npc.she] [npc.was] перед...");
			
			return UtilText.nodeContentSB.toString();
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
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить проникновения";
		}

		@Override
		public String getActionDescription() {
			return "Прекратить все текущие действия по проникновению, в которых участвует [npc2.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening()
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Main.sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.asshole+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.asshole+]."));
							}
							break;
						case ASS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] используя [npc2.her] [npc2.ass+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] используя [npc2.her] [npc2.ass+]."));
							}
							break;
						case ARMPITS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] трахая [npc2.her] [npc2.armpit+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] трахая [npc2.her] [npc2.armpit+]."));
							}
							break;
						case BREAST:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] играя с [npc2.her] [npc2.breasts+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] играя с [npc2.her] [npc2.breasts+]."));
							}
							break;
						case BREAST_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] играя с [npc2.her] [npc2.crotchBoobs+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытаскивает [npc2.a_moan+] как [npc.name] [npc.verb(stop)] играя с [npc2.her] [npc2.crotchBoobs+]."));
							}
							break;
						case MOUTH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытекает из [npc2.a_moan+], словно [npc.name], [npc.verb(pull)] изо рта [npc2.her]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытекает из [npc2.a_moan+], словно [npc.name], [npc.verb(pull)] изо рта [npc2.her]."));
							}
							break;
						case NIPPLE:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытащил [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.nipple+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытащил [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.nipple+]."));
							}
							break;
						case NIPPLE_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытащи [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.crotchNipple+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытащи [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.crotchNipple+]."));
							}
							break;
						case URETHRA_PENIS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытащи [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.penisUrethra+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытащи [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.penisUrethra+]."));
							}
							break;
						case URETHRA_VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] вытащить [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.vaginaUrethra+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] вытащить [npc2.a_moan+] как [npc.name] [npc.verb(pull)] из [npc2.her] [npc2.vaginaUrethra+]."));
							}
							break;
						case VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc.name] [npc.verb(pull)] наружу из [npc2.her] [npc2.pussy+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc.name] [npc.verb(pull)] наружу из [npc2.her] [npc2.pussy+]."));
							}
							break;
						case THIGHS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc.name] [npc.verb(pull)] наружу из-под [npc2.her] бедер."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc.name] [npc.verb(pull)] наружу из-под [npc2.her] бедер."));
							}
							break;
						case SPINNERET:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc.name] [npc.verb(pull)] наружу из [npc2.her] паутинного аппарата."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] наружу [npc2.a_moan+] как [npc.name] [npc.verb(pull)] наружу из [npc2.her] паутинного аппарата."));
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), character);
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Прекратить проникновения (самостоятельно)";
		}

		@Override
		public String getActionDescription() {
			return "Прекратить все текущие действия по проникновению, в которых вы участвуете.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterEngagedInOngoingAction(Main.game.getPlayer())
					&& Main.sex.getAllParticipants(false).size()>2
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Main.sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] медленно вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать свою [npc.asshole+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] выпускает [npc2.a_moan+], когда ты выходишь из [npc2.her] [npc2.asshole+].");
							}
							break;
						case ASS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты перестаешь использовать [npc2.her] [npc2.ass+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать свою [npc.ass+].");
							}
							break;
						case ARMPITS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты перестаешь трахать [npc2.her] [npc2.armpit+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать свою [npc.armpits+].");
							}
							break;
						case BREAST:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты перестаешь ласкать [npc2.her] [npc2.breasts+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать свою [npc.breasts+].");
							}
							break;
						case BREAST_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты перестаешь ласкать [npc2.her] [npc2.crotchBoobs+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать свою [npc.crotchBoobs+].");
							}
							break;
						case MOUTH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из рта [npc2.her].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты выходишь из своего рта.");
							}
							break;
						case NIPPLE:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из [npc2.her] [npc2.nipple+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать [npc.nipple+].");
							}
							break;
						case NIPPLE_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из [npc2.her] [npc2.crotchNipple+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать [npc.crotchNipple+].");
							}
							break;
						case URETHRA_PENIS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из [npc2.her] [npc2.penisUrethra+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать [npc.penisUrethra+].");
							}
							break;
						case URETHRA_VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из [npc2.her] [npc2.vaginaUrethra+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать [npc.vaginaUrethra+].");
							}
							break;
						case VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из [npc2.her] [npc2.pussy+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] вытекает из твоих [npc.lips+], когда ты перестаешь стимулировать [npc.pussy+].");
							}
							break;
						case THIGHS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из-под [npc2.her] бедер.");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] медленно выскальзывает из твоих [npc.lips+], когда ты перестаешь ласкать свои бедра.");
							}
							break;
						case SPINNERET:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] издает [npc2.a_moan+], когда ты выходишь из [npc2.her] паутины.");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] медленно выскальзывает из твоих [npc.lips+], когда ты перестаешь стимулировать свою паутину.");
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				Main.sex.stopAllOngoingActions(Main.game.getPlayer(), character);
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Запретить действия с собой";
		}
		@Override
		public String getActionDescription() {
			return "Запретить [npc2.name] совершать любые действия по самопроникновению.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCharacterAllowedToUseSelfActions(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc2.Name] разочарованно [npc.moan], когда ты заставляешь [npc2.herHim] перестать стимулировать [npc2.her] [npc2.pussy+].");
				}
			}
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("Когда ты прекращаешь самостимуляцию [npc2.her] [npc2.asshole] у [npc2.namePos], [npc2.she] издает жалкий скулеж.");
				}
			}
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] дуется на тебя, когда ты заставляешь [npc2.herHim] перестать стимулировать [npc2.her] [npc2.nipples+].");
				}
			}
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] разочарованно [npc.moan], когда ты заставляешь [npc2.herHim] перестать использовать [npc2.her] рот.");
				}
			}
			
			if(UtilText.nodeContentSB.length()!=0) {
				UtilText.nodeContentSB.append("<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[npc.speech(Не хочу видеть, как ты пытаешься сама себя довести,)] ты [npc.moanVerb] у [npc2.name].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] больше не сможет использовать любые действия с самопроникновением.</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.setCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Разрешить самодействие";
		}
		@Override
		public String getActionDescription() {
			return "Разрешить [npc2.name] выполнять любые действия с самопроникновением.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCharacterAllowedToUseSelfActions(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(Можешь трогать себя сколько угодно,)] ты [npc.moanVerb] у [npc2.name].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] теперь может использовать любые действия с самопроникновением.</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};

	public static final SexAction PLAYER_FORBID_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Ограничить контроль";
		}
		@Override
		public String getActionDescription() {
			return "Ограничить уровень контроля [npc2.namePos], не позволяя [npc2.herHim] инициировать любые действия, кроме самопроникновения.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& !Main.sex.isDom(target)
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(target).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(Не смей ничего делать без команды,)] ты, послушная [npc2.name].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] теперь имеет ограниченный уровень контроля и не может инициировать любые действия, кроме самопроникновения.</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.setForcedSexControl(target, SexControl.ONGOING_ONLY);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Снять ограничения контроля";
		}
		@Override
		public String getActionDescription() {
			return "Снять ограничения на уровень контроля [npc2.namePos], позволяя [npc2.herHim] инициировать действия, не связанные с самопроникновением.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& !Main.sex.isDom(target)
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(target).getValue()<SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(Можешь делать всё, что захочешь,)] — говорите вы [npc2.name], давая [npc2.herHim] свободу делать всё, что пожелает [npc2.she].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] теперь обладает неограниченным уровнем контроля и может по своему желанию инициировать действия, не связанные с самопроникновением.</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.setForcedSexControl(target, SexControl.FULL);
			for(GameCharacter participant : Main.sex.getAllParticipants()) {
				if(!participant.equals(target) && Main.sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Запретить смену позиций";
		}
		@Override
		public String getActionDescription() {
			return "Запретить [npc2.name] использовать любые действия по смене позиций.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& performer.isPlayer()
					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(target);
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(Не хочу видеть, как ты пытаешься сменить позу,)] — говорите вы [npc.moanVerb], глядя на [npc2.name].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] больше не будет использовать действия по смене позиций.</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.addCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Разрешить смену позиций";
		}
		@Override
		public String getActionDescription() {
			return "Разрешить [npc2.name] использовать действия по смене позиций.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& performer.isPlayer()
					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(target);
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(Если хочешь, можешь сменить позу на ту, в которой тебе удобнее,)] ты [npc.moanVerb] на [npc2.name].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] теперь может управлять позами.</i>";
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.removeCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterTargetedForSexAction(this));
			for(GameCharacter participant : Main.sex.getAllParticipants()) {
				if(!participant.equals(target) && Main.sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Запретить одежду";
		}
		@Override
		public String getActionDescription() {
			return "Запретить [npc2.name] управлять твоей одеждой.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCanRemoveOthersClothing(target, null)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(Только <i>смей</i> тронуть мою одежду!)] — рычишь ты на [npc2.name].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] не будет пытаться снимать или сдвигать твою одежду.</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Разрешить одежду";
		}
		@Override
		public String getActionDescription() {
			return "Разрешить [npc2.name] снимать и сдвигать твою одежду.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCanRemoveOthersClothing(target, null)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(Как насчет того, чтобы помочь мне раздеться?)] ты [npc.moan].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] теперь может управлять твоей одеждой.</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Запретить себе раздеваться";
		}
		@Override
		public String getActionDescription() {
			return "Запретить [npc2.name] трогать любую одежду [npc2.her].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCanRemoveSelfClothing(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(Только <i>посмей</i> только коснуться своей одежды!)] — рычишь ты на [npc2.name].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] больше не будет пытаться снимать или сдвигать одежду [npc2.her].</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Разрешить себе раздеваться";
		}
		@Override
		public String getActionDescription() {
			return "Разрешить [npc2.name] снимать и сдвигать одежду [npc2.her].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCanRemoveSelfClothing(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(Как насчет того, чтобы начать раздеваться?)] — [npc.moan].<br/><br/><i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] теперь может управлять одеждой [npc2.her].</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};
	
	
	public static final SexAction PLAYER_STOP_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Остановить партнера";
		}

		@Override
		public String getActionDescription() {
			return "Запретить [npc2.name] совершать любые действия по самоудовлетворению.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isMasturbation()
					&& Main.sex.isCharacterSelfOngoingActionHappening(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc2.Name] разочарованно [npc.moan], когда ты заставляешь [npc2.herHim] перестать стимулировать [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("Когда ты прекращаешь самостимуляцию [npc2.her] [npc2.asshole] у [npc2.namePos], [npc2.she] издает жалкий скулеж.");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] дуется на тебя, когда ты заставляешь [npc2.herHim] перестать стимулировать [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] разочарованно [npc.moan], когда ты заставляешь [npc2.herHim] перестать использовать [npc2.her] рот.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PARTNER_STOP_PLAYER_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Остановить игрока";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterSelfOngoingActionHappening(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isAnyNonSelfOngoingActionHappening()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc.Name] издает яростный рык, когда [npc.she] заставляет [npc2.name] прекратить стимулировать [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("Когда [npc.name] прекращает [npc2.namePos] самостимуляцию [npc2.her] [npc2.asshole], [npc.she] угрожающе рычит на [npc2.herHim].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] хмурится на [npc2.name], когда [npc.she] заставляет [npc2.herHim] перестать стимулировать [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] неодобрительно цокает [npc.her] [npc.tongue], когда [npc.she] заставляет [npc2.name] перестать использовать [npc2.her] рот.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	/**
	 * Special end action for submissive NPCs who end up resisting, and who also have the power to stop sex.
	 */
	public static final SexAction PARTNER_STOP_SEX_NOT_HAVING_FUN = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Закончить секс";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& (!Main.sex.getCharacterPerformingAction().isSlave() || !Main.sex.getAllParticipants().contains(Main.sex.getCharacterPerformingAction().getOwner()))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
					&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB);
			
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With an annoyed sigh, [npc.name] disentangles [npc.herself] from [npc2.namePos] clutches, [npc.speechNoExtraEffects(Eugh... I'm not really feeling this right now, ok?)]";
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return Main.sex.isMasturbation()
					?"Прекратить мастурбацию"
					:"Прекратить секс";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterWantingToStopSex(Main.sex.getCharacterPerformingAction())
					&& (!Main.sex.getAllParticipants(false).contains(Main.game.getPlayer()) || !Main.sex.isReadyToOrgasm(Main.game.getPlayer()))
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).getGenericEndSexDescription(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "Перестать смотреть";
			}
			return Main.sex.isMasturbation()
					?"Прекратить мастурбацию"
					:"Прекратить секс";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "Отступить и перестать наблюдать за разворачивающейся перед тобой сценой секса.<br/>Это все равно [style.boldSex(применит все соответствующие эффекты)] к остальным участникам, как если бы сцена секса произошла полностью.";
			}
			return Main.sex.isMasturbation()
					?"Закончить сеанс мастурбации."
					:"Перестать заниматься сексом с [npc2.name].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isPlayerAbleToStopSex()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getDescription() {
			return Main.sex.isSpectator(Main.game.getPlayer())
					?"Насмотревшись вдоволь, ты отворачиваешься и перестаешь наблюдать за разворачивающейся перед тобой сценой секса..."
					:(Main.sex.isMasturbation()
						?"Намастурбировавшись вдоволь, ты останавливаешься..."
						:"Насладившись сполна, ты [pc.step] назад и прекращаешь секс...");
		}
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation() || Main.sex.isSpectator(Main.game.getPlayer())
					?SexParticipantType.SELF
					:SexParticipantType.NORMAL;
		}
		@Override
		public boolean endsSex() {
			return true;
		}
		@Override
		public String applyEndEffects(){
			 // Generate effects when ending sex as hidden spectator, but do not assign it to the 'quickSexDescription' variable, as that's only used for display in PLAYER_QUICK_SEX
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				generateQuickSexDescription();
			}
			return "";
		}
	};

	public static final SexAction ROPE_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.ROPE);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Связана!)]";
		}
		@Override
		public String getActionDescription() {
			return "Веревки, стянувшие твое тело, не дают тебе сдвинуться с места!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.ROPE)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)], чтобы сделать движение, но веревки, удерживающие [npc.herHim] на месте, слишком крепки, и [npc.she] [npc.verb(collapse)], ошеломленная.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction CHAINS_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.CHAINS);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Связан!)]";
		}
		@Override
		public String getActionDescription() {
			return "Цепи, стягивающие твое тело, не дают тебе пошевелиться!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.CHAINS)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)], чтобы сделать движение, но цепи, сковывающие [npc.herHim], слишком крепки, и [npc.she] [npc.verb(collapse)] назад, ошеломленная.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction STOCKS_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.STOCKS);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Связан!)]";
		}
		@Override
		public String getActionDescription() {
			return "Оказавшись зажатой в колодках, [npc.nameIsFull] лишена возможности пошевелиться!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.STOCKS)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)], чтобы сделать движение, но колодки, в которые заперта [npc.sheIs], слишком крепки, и в итоге [npc.she] [npc.verb(remain)] полностью обездвижена.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Spinneret:
	
	public static final SexAction SPINNERET_SPIN_CONDOM_SELF = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Сплести презерватив (самой)";
		}
		@Override
		public String getActionDescription() {
			return "Использовать паутинные железы, чтобы обернуть свои [npc.penis] паутинным коконом, похожим на презерватив.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| ((NPC) Main.sex.getCharacterPerformingAction()).isWantingToEquipCondom(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())))
					&& Main.sex.isCanRemoveSelfClothing(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterPerformingAction(), InventorySlot.PENIS, null)
					&& Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterPerformingAction().equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
	};
	
	public static final SexAction SPINNERET_SPIN_CONDOM_PARTNER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Сплести презерватив";
		}
		@Override
		public String getActionDescription() {
			return "Использовать паутинные железы, чтобы обернуть [npc2.namePos] [npc2.penis] паутинным коконом, похожим на презерватив.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| ((NPC) Main.sex.getCharacterPerformingAction()).isWantingToEquipCondomOnPartner(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())))
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.PENIS, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.PENIS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
	};
	
	public static final SexAction SPINNERET_SPIN_SEAL_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Сплести печать для киски";
		}
		@Override
		public String getActionDescription() {
			return "Использовать паутинные железы, чтобы наложить на [npc2.namePos] [npc2.pussy] толстый слой паутины.";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.VAGINA, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.VAGINA)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_vagina"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Сплести печать на ягодицах";
		}
		@Override
		public String getActionDescription() {
			return "Использовать паутинное брюшко, чтобы наложить толстый слой липкой паутины на [npc2.namePos] [npc2.asshole].";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.ANUS, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.ANUS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_anus"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Сплести печать на сосках";
		}
		@Override
		public String getActionDescription() {
			return "Использовать паутинное брюшко, чтобы наложить толстый слой липкой паутины на [npc2.namePos] [npc2.nipples].";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.NIPPLE, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.NIPPLE)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_nipples"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_MOUTH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Сплести печать на рту";
		}
		@Override
		public String getActionDescription() {
			return "Использовать паутинное брюшко, чтобы наложить толстый слой липкой паутины на [npc2.namePos] [npc2.mouth].";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.MOUTH, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.MOUTH)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_mouth"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Cocooning:
	
	public static final SexAction SPINNERET_COCOON_PARTNER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, null)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "Закутать в кокон [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			return "Использовать паутинное брюшко, чтобы окутать [npc2.name] коконом.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Not wanting [npc2.name] to try and make any move of [npc2.her] own, [npc.name] [npc.verb(point)] [npc.her] spinneret at [npc2.herHim], before starting to shoot out a continuous strand of thick, sticky webbing. Before [npc2.she] [npc2.verb(know)] what's happening, [npc2.namePos] [npc2.arms] and [npc2.legs] are completely restrained by the strong binding, and after just a few moments more, [npc.name] [npc.has] completely wrapped [npc2.herHim] up in a cocoon. Smirking at [npc.her] handiwork, [npc.name] [npc.verb(prepare)] to make good use of the fact that [style.boldBad([npc2.name] [npc2.is] no longer able to move)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.COCOON, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_COCOON_PARTNER_REMOVE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Снять паутину";
		}
		@Override
		public String getActionDescription() {
			return "Сорвать паутину, сковывающую [npc2.name] в [npc2.her] кокон.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COCOON)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Решив, что с [npc2.name] [npc2.has] хватит этих ограничений, [npc.name] [npc.verb(set)] сорвать паутину, сковывающую [npc2.herHim] в [npc2.her] кокон. Спустя всего несколько мгновений, [npc.name] [npc.has] удалось уничтожить всю паутину, и в результате [style.boldGood([npc2.name] [npc2.is] теперь снова может двигаться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.COCOON);
		}
	};

	public static final SexAction COCOONED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.COCOON);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(В коконе!)]";
		}
		@Override
		public String getActionDescription() {
			return "Кокон из густой паутины не дает тебе пошевелиться!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.COCOON)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)] сделать попытку движения, но кокон, удерживающий [npc.herHim] на месте, слишком прочен, и [npc.she] [npc.verb(collapse)], ошеломленная.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Tentacles:
	
	public static final SexAction TENTACLES_RESTRICT_PARTNER = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, null)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "Удержание щупальцами";
		}
		@Override
		public String getActionDescription() {
			return "Использовать свои щупальца, чтобы удержать [npc2.name] на месте и не дать [npc2.herHim] двигаться.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Не желая, чтобы [npc2.name] пыталась предпринять хоть какое-то самостоятельное движение [npc2.her], [npc.name] [npc.verb(grab)] [npc2.herHim], при этом [npc.her] [npc.tentacles] и [npc.verb(hold)] [npc2.herHim] были надежно зафиксированы. Усмехаясь, пока [npc2.name] [npc2.verb(make)] несколько тщетных попыток вырваться из [npc.her] крепких объятий, [npc.name] [npc.verb(prepare)], стремясь вовсю воспользоваться тем, что [style.boldBad([npc2.name] [npc2.is] больше не может пошевелиться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.TENTACLE_RESTRICTION, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TENTACLES_RELEASE_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Отпустить щупальца";
		}
		@Override
		public String getActionDescription() {
			return "Разжать хватку на [npc2.name] и позволить [npc2.herHim] снова свободно двигаться.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc2.name] [npc2.has] уже достаточно было быть скованным, [npc.name] [npc.verb(begin)] ослабить хватку [npc.her] [npc.tentacles]. Усмехнувшись при виде облегчения на лице [npc2.namePos], [npc.name] полностью [npc.verb(release)] [npc2.herHim] из объятий [npc.her] щупалец, и в результате, [style.boldGood([npc2.name] [npc2.is] теперь снова может двигаться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.TENTACLE_RESTRICTION);
		}
	};

	public static final SexAction TENTACLE_SQUEEZE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Сдавливание щупальцами";
		}
		@Override
		public String getActionDescription() {
			return "Воспользоваться тем, что [npc2.name] полностью скован вашими щупальцами, чтобы крепко сжать и сдавить тело [npc2.her].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Пользуясь тем, что [npc.she] [npc.has] [npc2.name] полностью скован в [npc.her] щупальцах, [npc.name] [npc.verb(squeeze)] и крепко [npc.verb(constrict)] тело [npc2.her], заставляя [npc2.herHim] издать жалкий, удушливый вздох.",
					"Желая показать [npc2.name], что [npc2.sheIs] полностью во власти [npc.her], [npc.name] крепко [npc.verb(squeeze)] [npc.her] щупальца вокруг тела [npc2.her], из-за чего [npc2.herHim] издает серию все более слабых и отчаянных хрипов.",
					"Демонстрируя полное доминирование, [npc.name] крепко [npc.verb(squeeze)] [npc.her] щупальца вокруг тела [npc2.namePos], наслаждаясь отчаянными, лихорадочными вздохами и борьбой, которую [npc.sheIs] удалось вызвать у "+(targetPlayer?"вас.":"[npc.her] партнера."),
					"В полной мере наслаждаясь доминирующей позицией [npc.her], [npc.name] медленно усиливает хватку [npc.her] щупалец вокруг тела [npc2.namePos], пока "+(targetPlayer?"ты":"партнер [npc.her] не")
						+" может делать ничего, кроме как слабо барахтаться и отчаянно хватать ртом воздух.",
					"Крепко сжимая щупальца [npc.her] вокруг тела [npc2.namePos], [npc.name] [npc.verb(delight)] выжимает дух из "+(targetPlayer?"твоей":"[npc.her] партнера")
						+" легких, и не может не издать властный [npc.moan], пока [npc.she] [npc.verb(feel)] [npc2.name] слабо пытается вырваться."));

			sb.append(UtilText.returnStringAtRandom(
					" Только когда [npc2.nameIsFull] начинают обмякать, [npc.do] [npc.name] наконец отступают, и, слегка расслабив [npc.her] щупальца, [npc.she] [npc.verb(allow)] [npc2.herHim], чтобы [npc2.her] мог перевести дух и прийти в [npc2.her] чувство.",
					" Не желая заходить так далеко, чтобы [npc2.name] потерял сознание, [npc.name] лишь [npc.verb(choose)] расслабить [npc.her] щупальца в последний момент, позволяя [npc2.name] сделать глубокий вдох и вернуться от края обморока.",
					" Спустя некоторое время сжимания [npc2.name] подобным образом, [npc.name] [npc.verb(feel)] так, словно [npc.sheHas] толкнул [npc2.herHim] достаточно сильно, и после последнего тугого сжатия, [npc.she] [npc.verb(relax)] [npc.her] щупальца и [npc.verb(allow)] [npc2.name], чтобы снова дышать свободно.",
					" Ожидая, пока [npc.she] [npc.verb(sense)], что [npc2.nameIsFull] вот-вот упадет в обморок, [npc.name] [npc.verb(let)] забавную [npc.moan], прежде чем расслабить [npc.her] щупальца и позволить [npc2.name] наполнить [npc2.her] легкие кислородом.",
					" Ожидая, пока [npc2.nameIsFull] вот-вот потеряет сознание, [npc.name] [npc.verb(let)] забавную [npc.moan], прежде чем расслабить [npc.her] щупальца и позволить [npc2.name] глубоко вздохнуть и оправиться от [npc2.her] удушья."));
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SADIST, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TENTACLE_MASSAGE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Массаж щупальцами";
		}
		@Override
		public String getActionDescription() {
			return "Воспользоваться тем, что [npc2.name] полностью скован(а) вашими щупальцами, чтобы сжимать и массировать [npc2.her] тело.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Пользуясь тем, что [npc.she] [npc.has] [npc2.name] полностью скован(а) [npc.her] щупальцами, [npc.name] [npc.verb(squeeze)] и [npc.verb(massage)] [npc2.her] тело.",
					"Желая показать [npc2.name], что [npc2.sheIs] полностью во власти [npc.her], [npc.name] [npc.verb(squeeze)] [npc.her] щупальца вокруг [npc2.her] тела и [npc.verb(start)] массирует [npc2.herHim].",
					"Демонстрируя доминирование, [npc.name] крепко [npc.verb(squeeze)] [npc.her] щупальца вокруг [npc2.namePos] тела, используя [npc.her] хватку для массажа "+(targetPlayer?"твоей":"[npc.her] партнера")+" тела.",
					"В полной мере наслаждаясь [npc.her] доминирующим положением, [npc.name] [npc.verb(make)] всеми силами сжимает и массирует [npc2.herHim], используя захват щупалец [npc.her] вокруг тела [npc2.namePos].",
					"Крепко сжимая [npc.her] щупальцами [npc2.namePos] тело, [npc.name] [npc.verb(delight)], сдавливая и массируя [npc2.herHim]."));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" Хотя это совершенно невозможно, [npc2.name] [npc2.verb(continue)] попытаться вырваться из хватки [npc.namePos], издавая страдальческий крик, когда [npc2.she] [npc2.verb(realise)], что всё это бесполезно.",
						" [npc2.Name] [npc2.verb(let)] в ответ неистовый крик, прежде чем попытаться — и безуспешно — вырваться из объятий [npc.namePos].",
						" Отвергая судьбу, уготованную [npc2.her], [npc2.name] [npc2.verb(try)] вырваться из крепких объятий [npc.namePos], но усилия [npc2.her] оказываются тщетными."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" Издавая [npc2.a_moan+], [npc2.name] [npc2.verb(relax)] и [npc2.verb(enjoy)] чувственное ощущение от [npc.namePos] [npc.tentacles+], скользящей по [npc.her] телу и прижимающейся к нему.",
						" [npc2.Name] [npc2.verb(let)] [npc2.a_moan+] в ответ, ясно давая понять, что [npc2.sheIs] наслаждается ощущением того, как [npc.namePos] [npc.tentacles+] окутывает её.",
						" Расслабившись и наслаждаясь ощущением того, что её окутывают [npc.namePos] [npc.tentacles+], [npc2.name] [npc2.verb(let)] [npc2.a_moan+] и [npc2.verb(encourage)] [npc.herHim], чтобы массаж продолжался."));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction TENTACLE_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.TENTACLE_RESTRICTION);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Скована щупальцами!)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TENTACLE_RESTRICTION);
			return UtilText.parse(applier, "[npc.NameIsFull] использует [npc.her] щупальца, чтобы удерживать тебя на месте, и в результате ты не можешь пошевелиться!");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TENTACLE_RESTRICTION);
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), applier,
					"[npc.Name] [npc.verb(try)] попытаться пошевелиться, но объятия щупальца [npc2.namePos] слишком крепки, и всё, на что способен [npc.she], — это жалкие попытки извиваться.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Witch's seal:

	public static final SexAction WITCH_SEAL_CAST = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.SPELL_SCHOOL_ARCANE;
		}
		@Override
		public String getActionTitle() {
			return "Применить «"+Spell.WITCH_SEAL.getName()+"'";
		}
		@Override
		public String getActionDescription() {
			return "Примените заклинание '"+Spell.WITCH_SEAL.getName()+"» на [npc2.name], чтобы обездвижить [npc2.herHim] и полностью лишить [npc2.herHim] возможности двигаться.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer() // Only allow player to use this
					&& Main.sex.getCharacterPerformingAction().hasSpell(Spell.WITCH_SEAL, true)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Не желая, чтобы [npc2.name] пыталась предпринять хоть какое-то движение по собственной воле [npc2.her], [npc.name] [npc.verb(prepare)] наложить заклинание '"+Spell.WITCH_SEAL.getName()+"» на [npc2.herHim]. Сосредоточившись на аркане внутри метлы [npc.her], [npc.name] [npc.verb(summon)] мощную печать, которая удерживает [npc2.name] на месте! Успешно наложив заклинание [npc.her], [npc.name] [npc.verb(prepare)] вовсю пользоваться тем, что [style.boldBad([npc2.name] [npc2.is] больше не может двигаться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.WITCH_SEAL, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction WITCH_SEALED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.WITCH_SEAL);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Запечатан!)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.WITCH_SEAL);
			return UtilText.parse(applier, "Печать Ведьмы, которую на тебя наложил [npc.name], не дает тебе пошевелиться!");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.WITCH_SEAL)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			if(Main.sex.getInitialSexManager() instanceof SMAltarMissionarySealed) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] попытаться пошевелиться, но Печать Ведьмы слишком сильна, и [npc.she] [npc.verb(collapse)] на алтарь, ошеломленный.",
						"Под телом [npc.namePos] материализуется фиолетовое свечение пентаграммы, когда [npc.she] [npc.verb(try)] попытаться пошевелиться; доказательство того, что Печать Ведьмы всё еще удерживает [npc.herHim] на месте.",
						"[npc.Name] [npc.verb(try)] попытаться сесть на алтаре, но [npc.sheIs] может лишь слегка извиваться под сковывающим действием Печати Ведьмы.",
						"Мягкое фиолетовое сияние Печати Ведьмы окружает [npc.name], пока [npc.she] [npc.verb(struggle)], пытаясь пошевелиться.",
						"[npc.speech(~Ммм!~)] [npc.name] [npc.verb(moan)], тщетно сражаясь с Печатью Ведьмы.",
						"[npc.speech(~Аах!~)] [npc.name] [npc.verb(whimper)], извиваясь на алтаре, пока Печать Ведьмы удерживает [npc.herHim] на месте.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)], чтобы сдвинуться, но Печать Ведьмы слишком сильна, и [npc.she] [npc.verb(collapse)] обратно в исходное положение, ошеломленная.",
						"Под телом [npc.namePos] материализуется фиолетовое свечение пентаграммы, когда [npc.she] [npc.verb(try)] попытаться пошевелиться; доказательство того, что Печать Ведьмы всё еще удерживает [npc.herHim] на месте.",
						"[npc.Name] [npc.verb(try)], чтобы пошевелиться, но [npc.sheIs] лишь слегка извивается под сковывающим действием Печати Ведьмы.",
						"Мягкое фиолетовое сияние Печати Ведьмы окружает [npc.name], пока [npc.she] [npc.verb(struggle)], пытаясь пошевелиться.",
						"[npc.speech(~Ммм!~)] [npc.name] [npc.verb(moan)], тщетно сражаясь с Печатью Ведьмы.",
						"[npc.speech(~Аах!~)] [npc.name] [npc.verb(whimper)], извиваясь, пока Печать Ведьмы удерживает [npc.herHim] на месте.");
			}
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction WITCH_SEAL_BREAK = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.SPELL_SCHOOL_ARCANE;
		}
		@Override
		public String getActionTitle() {
			return "Снять '"+Spell.WITCH_SEAL.getName()+"'";
		}
		@Override
		public String getActionDescription() {
			return "Отменить Заклинание '"+Spell.WITCH_SEAL.getName()+"' которое ты наложил(а) на [npc2.name], позволяя [npc2.herHim] снова свободно двигаться.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.WITCH_SEAL)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc2.name] [npc2.has] сыта по горло неподвижностью, [npc.name] [npc.verb(prepare)] развеять эффект заклинания, '"+Spell.WITCH_SEAL.getName()+"'. Глубоко вздохнув и сосредоточившись на арканной силе [npc.her], [npc.name] [npc.verb(break)] заклинание, и в результате [style.boldGood([npc2.name] [npc2.is] теперь снова может двигаться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.WITCH_SEAL);
		}
	};
	
	// Tentacles:
	
	public static final SexAction TAIL_CONSTRICTION_RESTRICT_PARTNER = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "Сдавить";
		}
		@Override
		public String getActionDescription() {
			return "Оберни свой длинный хвост вокруг [npc2.name] и сжимай [npc2.herHim], чтобы не дать [npc2.herHim] двигаться.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.getCharactersImmobilised().containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getCharactersImmobilised().get(ImmobilisationType.TAIL_CONSTRICTION).containsKey(Main.sex.getCharacterPerformingAction())) {
				return false; // If performing character is engaged in ongoing long-tail constriction, return false (as can only restrict one at a time).
			}
			return Main.sex.getCharacterPerformingAction().getLegConfiguration()==LegConfiguration.TAIL_LONG
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Не желая, чтобы [npc2.name] пыталась предпринять хоть какое-то движение [npc2.her], [npc.name] [npc.verb(wrap)] [npc.her] длинный хвост вокруг [npc2.herHim] и [npc.verb(squeeze)] крепко, намертво фиксируя [npc2.herHim] на месте. Усмехаясь, пока [npc2.name] [npc2.verb(make)] несколько тщетных попыток вырваться из сжимающихся колец [npc.her], [npc.name] [npc.verb(prepare)] извлечь выгоду из того, что [style.boldBad([npc2.name] [npc2.is] больше не может пошевелиться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.TAIL_CONSTRICTION, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_CONSTRICTION_RELEASE_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Ослабить хватку";
		}
		@Override
		public String getActionDescription() {
			return "Разжать хватку на [npc2.name] и позволить [npc2.herHim] снова свободно двигаться.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Решив, что [npc2.name] [npc2.has] достаточно ограничений, [npc.name] [npc.verb(begin)] ослабить тугие кольца [npc.her] длинного хвоста. С усмешкой наблюдая за облегчением на лице [npc2.namePos], [npc.name] полностью [npc.verb(release)] [npc2.herHim] из объятий [npc.her], и в результате [style.boldGood([npc2.name] [npc2.is] теперь снова может двигаться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.TAIL_CONSTRICTION);
		}
	};

	public static final SexAction TAIL_SQUEEZE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Сдавливание хвостом";
		}
		@Override
		public String getActionDescription() {
			return "Воспользоваться тем, что [npc2.name] полностью зажат(а) в вашем хвосте, чтобы крепко сжать и сдавить тело [npc2.her].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Пользуясь тем, что [npc.she] [npc.has] [npc2.name] полностью зажат(а) в [npc.her] хвосте, [npc.name] [npc.verb(squeeze)] и крепко [npc.verb(constrict)] тело [npc2.her], заставляя [npc2.herHim] издать жалкий, удушливый всхлип.",
					"Желая показать [npc2.name], что [npc2.sheIs] полностью во власти [npc.her], [npc.name] крепко [npc.verb(squeeze)] [npc.her] хвост вокруг тела [npc2.her], отчего [npc2.herHim] начинает издавать серию всё более слабых и отчаянных хрипов.",
					"Демонстрируя полное доминирование, [npc.name] крепко [npc.verb(squeeze)] [npc.her] хвост вокруг тела [npc2.namePos], наслаждаясь отчаянными, лихорадочными вдохами и борьбой, которые [npc.sheIs] удается вызвать у "+(targetPlayer?"вас.":"[npc.her] партнера."),
					"В полной мере наслаждаясь [npc.her] доминирующим положением, [npc.name] медленно усиливает хватку [npc.her] хвоста вокруг тела [npc2.namePos], пока "+(targetPlayer?"ты":"партнер [npc.her] не")
						+" может делать ничего, кроме как слабо барахтаться и отчаянно хватать ртом воздух.",
					"Крепко сжимая тело [npc2.namePos] хвостом [npc.her], [npc.name] [npc.verb(delight)] в попытке выжать дух из "+(targetPlayer?"твоей":"[npc.her] партнера")
						+" легких, и не может не издать властный [npc.moan], пока [npc.she] [npc.verb(feel)] [npc2.name] слабо пытается вырваться."));
			
			sb.append(UtilText.returnStringAtRandom(
					" Лишь когда [npc2.nameIsFull] начинает обмякать, [npc.do] [npc.name] наконец сдаются, и, немного расслабив [npc.her] хвост, [npc.she] [npc.verb(allow)] [npc2.herHim] перевести [npc2.her] дух и прийти в [npc2.her] чувства.",
					" Не желая заходить так далеко, чтобы [npc2.name] потеряла сознание, [npc.name] лишь [npc.verb(choose)], чтобы расслабить [npc.her] хвост в последний момент, позволяя [npc2.name] глубоко вздохнуть и вернуться с грани обморока.",
					" Спустя некоторое время сжимания [npc2.name] подобным образом, [npc.name] [npc.verb(feel)] так, словно [npc.sheHas] толкнул [npc2.herHim] достаточно сильно, и после последнего тугого сжатия, [npc.she] [npc.verb(relax)] [npc.her] хвост и [npc.verb(allow)] [npc2.name], чтобы снова дышать свободно.",
					" Дождавшись, пока [npc.she] [npc.verb(sense)], что [npc2.nameIsFull] вот-вот упадет в обморок, [npc.name] [npc.verb(let)] забавный [npc.moan], прежде чем расслабить [npc.her] хвост и дать [npc2.name] возможность наполнить [npc2.her] легкие кислородом.",
					" Дождавшись, пока [npc2.nameIsFull] будет на грани потери сознания, [npc.name] [npc.verb(let)] забавный [npc.moan], прежде чем расслабить [npc.her] хвост и позволить [npc2.name] глубоко вздохнуть и оправиться от [npc2.her] удушья."));
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SADIST, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_MASSAGE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Массаж хвоста";
		}
		@Override
		public String getActionDescription() {
			return "Воспользоваться тем, что [npc2.name] полностью зажата в вашем хвосте, чтобы сжимать и массировать [npc2.her] тело.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Пользуясь тем, что [npc.she] [npc.has] [npc2.name] полностью обездвижена в [npc.her] хвосте, [npc.name] [npc.verb(squeeze)] и [npc.verb(massage)] [npc2.her] тело.",
					"Желая показать [npc2.name], что [npc2.sheIs] полностью во власти [npc.her], [npc.name] [npc.verb(squeeze)] [npc.her] хвостом вокруг [npc2.her] тела и [npc.verb(start)] массирует [npc2.herHim].",
					"Демонстрируя доминирование, [npc.name] уверенно [npc.verb(squeeze)] [npc.her] хвостом вокруг [npc2.namePos] тела, используя [npc.her] хватку, чтобы массировать "+(targetPlayer?"твоей":"[npc.her] партнера")+" тела.",
					"В полной мере наслаждаясь доминирующим положением [npc.her], [npc.name] [npc.verb(make)] хвостом [npc.her], сжимая [npc2.namePos] и массируя [npc2.herHim].",
					"Крепко сжимая [npc.her] хвостом [npc2.namePos] тело, [npc.name] [npc.verb(delight)] в сжимании и массировании [npc2.herHim]."));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" Хотя это совершенно невозможно, [npc2.name] [npc2.verb(continue)] попытаться вырваться из хватки [npc.namePos], издавая страдальческий крик, когда [npc2.she] [npc2.verb(realise)], что всё это бесполезно.",
						" [npc2.Name] [npc2.verb(let)] в ответ неистовый крик, прежде чем попытаться — и безуспешно — вырваться из объятий [npc.namePos].",
						" Отвергая судьбу, уготованную [npc2.her], [npc2.name] [npc2.verb(try)] вырваться из крепких объятий [npc.namePos], но усилия [npc2.her] оказываются тщетными."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" Издавая звук [npc2.a_moan+], [npc2.name] [npc2.verb(relax)] и [npc2.verb(enjoy)] чувствует упоительное ощущение того, как [npc.namePos] хвост скользит и прижимается к [npc.her] телу.",
						" [npc2.Name] [npc2.verb(let)] в ответ [npc2.a_moan+], ясно давая понять, что [npc2.sheIs] наслаждается ощущением того, как её обвивает [npc.namePos] хвост.",
						" Расслабившись и наслаждаясь ощущением того, как её обвивает [npc.namePos] хвост, [npc2.name] [npc2.verb(let)] [npc2.a_moan+] и [npc2.verb(encourage)] [npc.herHim], чтобы продолжить массаж."));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_CONSTRICTED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.TAIL_CONSTRICTION);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Сжатие!)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TAIL_CONSTRICTION);
			return UtilText.parse(applier, "[npc.Name] использует свой длинный хвост [npc.her], чтобы удерживать тебя на месте, и в результате ты не можешь пошевелиться!");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TAIL_CONSTRICTION);
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), applier,
					"[npc.Name] [npc.verb(try)], чтобы сделать движение, но [npc2.namePos] сжимающий хвост слишком силен, и всё, на что способна [npc.she], — это жалкие попытки извиваться.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	

	// Command:

	public static final SexAction COMMAND_IMMOBILE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return super.getPriority();
			}
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "Секс-игрушка";
		}
		@Override
		public String getActionDescription() {
			return "Приказать [npc2.name] оставаться совершенно и абсолютно неподвижной, позволяя вам трахать [npc2.herHim] как безмолвную, неподвижную секс-игрушку.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER)) // Only allow player or bondage fetishists to use this
					&& Main.sex.getCharacterTargetedForSexAction(this).isDoll()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Желая, чтобы [npc2.name] вела себя как неодушевленная секс-игрушка, [npc.name] [npc.verb(order)] [npc2.herHim], чтобы та перестала двигаться. Послушно выполняя команду [npc.namePos], [npc2.name] [npc2.verb(freeze)] на месте. Улыбаясь [npc.herself], [npc.name] [npc.verb(prepare)], чтобы вовсю использовать тот факт, что [style.boldBad([npc2.name] [npc2.is] теперь совершенно неподвижна)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.COMMAND, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction COMMAND_IMMOBILE_ACTION = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.COMMAND);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Секс-игрушка!)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.COMMAND);
			return UtilText.parse(applier, "Получив приказ от [npc.name] оставаться неподвижной, вы послушно замираете!");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.COMMAND)
					&& !Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.SLEEP);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] послушно [npc.verb(remain)], оставаясь совершенно неподвижной и ведя себя как неодушевленная секс-кукла.",
					"Получив приказ, [npc.name] [npc.verb(remain)], став совершенно неподвижной.",
					"Оставаясь совершенно и абсолютно неподвижной, [npc.name] [npc.verb(allow)] [npc.herself], чтобы её использовали как никчемную секс-игрушку.",
					"[npc.Name] [npc.verb(remain)] совершенно неподвижна и [npc.do] не реагирует ни на что, что делают с [npc.herHim].",
					"Будучи послушной секс-куклой, [npc.name] [npc.do] так, как велел(а) [npc.sheIs], и [npc.verb(stay)] совершенно неподвижно.",
					"[npc.Name] [npc.do] идеальную имитацию неодушевленной секс-куклы и [npc.verb(remain)] совершенно и абсолютно неподвижно.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction COMMAND_IMMOBILE_RELEASE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Оживить";
		}
		@Override
		public String getActionDescription() {
			return "Сказать [npc2.name], что [npc2.she] больше не нужно оставаться неподвижной, позволяя [npc2.herHim] снова свободно двигаться и говорить.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COMMAND)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Насытившись неподвижным поведением [npc2.namePos], [npc.name] [npc.verb(tell)] [npc2.herHim], что [npc.sheIs] снова может свободно двигаться. С похотливым стоном и соблазнительным взглядом становится ясно, что [style.boldGood([npc2.name] [npc2.is] теперь снова может двигаться)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.COMMAND);
		}
	};
	
	// Sleeping:
	
	public static final SexAction SLEEPING_ACTION = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.SLEEP);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Спит...)]";
		}
		@Override
		public String getActionDescription() {
			return "Вы крепко спите...";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.SLEEP);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(remain)] глубоким сном, и [npc.verb(show)] нет никаких признаков того, что она вот-вот проснется.",
					"Несмотря на периодические "+(Main.sex.getCharacterPerformingAction().isFeminine()?"стоны":"стонать")+", [npc.name] [npc.verb(remain)] глубоким сном.",
					"Продолжая спать, [npc.name] [npc.verb(remain)], совершенно не подозревая, что [npc.sheIs] трахают.",
					"[npc.Name] [npc.verb(remain)] глубоким сном и едва [npc.verb(react)] на всё, что делают с [npc.herHim].",
					"Будучи невероятно крепко спящей, [npc.name] [npc.verb(remain)] в глубоком сне, даже когда [npc.sheIs] трахают.",
					"[npc.Name] [npc.do] даже не показывает признаков пробуждения, пока [npc.sheIs] трахают в [npc.her] сне.");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
			return null;
		}
	};

	public static final SexAction SLEEPING_WAKE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Разбудить [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			return "Трясти [npc2.name], пока [npc2.she] не проснется.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.SLEEP)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Натрахавшись с [npc2.name], пока [npc2.sheIs] спит, [npc.name] грубо [npc.verb(shake)] [npc2.herHim] в попытке разбудить [npc2.herHim]. Неохотно открывая [npc2.her] [npc2.eyes], [npc2.name] [npc.verb(let)] зевок и [style.boldGood([npc2.verb(wake)] вверх)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterWoken(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction SLEEPING_WOKEN_UP = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.SLEEP);
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.GENERIC_BAD;
		}
		@Override
		public String getActionTitle() {
			return "Проснулась";
		}
		@Override
		public String getActionDescription() {
			return "Ты просыпаешься и понимаешь, что тебя трахают!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isAsleep()
					&& Main.sex.getCharactersWoken().contains(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			// Woken by oral:
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).stream().anyMatch(penetration->penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity())) {
				// Pen name
				SexAreaPenetration pen = Main.sex.getFirstOngoingSexAreaPenetration(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
				GameCharacter characterPenetrating = Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, pen).iterator().next();
				sb.append(UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrating,
						"Хотя [npc.sheIs] спит крепко, удушающее ощущение от того, что [npc2.namePos] "+pen.getName(characterPenetrating)+" вколачивают в [npc.her] горло, заставляет [npc.name] резко проснуться."));
				
			} else {
				GameCharacter dom = Main.sex.getDominantParticipants(false).keySet().iterator().next();
				sb.append(UtilText.parse(Main.sex.getCharacterPerformingAction(), dom,
						"Хотя [npc.sheIs] спит крепко, движения [npc2.namePos] достаточно сильные, чтобы вырвать [npc.herHim] из [npc.her] сна."));
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						sb.append(" Распахнув [npc.her] [npc.eyes] и увидев, что происходит, [npc.name] [npc.verb(let)] возбужденный [npc.moan] и с радостью [npc.verb(submit)] тому, что ее трахают.");
					} else {
						sb.append(" Распахнув [npc.her] [npc.eyes] и увидев, что происходит, [npc.name] [npc.verb(let)] возбужденный [npc.moan] и с радостью [npc.verb(exclaim)], [npc.speech(Да! Трахни меня!)]");
					}
					break;
				case SUB_RESISTING:
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						sb.append(" Распахнув [npc.her] [npc.eyes] и увидев, что происходит, [npc.name] [npc.verb(let)] смущенный вздох и отчаянно [npc.verb(try)], чтобы сопротивляться.");
					} else {
						sb.append(" Распахнув [npc.her] [npc.eyes] и увидев, что происходит, [npc.name] [npc.verb(let)] смущенный вздох и [npc.verb(\", "+(Main.sex.getCharacterPerformingAction().isFeminine()?"вскрикнуть":"закричать")+")] в ужасе,");
						sb.append(" [npc.speechNoExtraEffects(Какого хера?! Нет! Убирайся от меня!)]");
					}
					break;
				case SUB_NORMAL:
				case DOM_GENTLE:
				case DOM_NORMAL:
				case DOM_ROUGH:
				default:
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						sb.append(" Распахнув [npc.her] [npc.eyes] и увидев, что происходит, [npc.name] [npc.verb(let)] достает [npc.moan] и быстро [npc.verb(submit)], когда её трахают.");
					} else {
						sb.append(" Распахнув [npc.her] [npc.eyes] и увидев, что происходит, [npc.name] [npc.verb(let)] достает [npc.moan] и [npc.verb(exclaim)], [npc.speech(Да! Трахни меня!)]");
					}
					break;
			}
			
			return sb.toString();
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterPerformingAction(), ImmobilisationType.SLEEP);
			Main.sex.removeCharacterWoken(Main.sex.getCharacterPerformingAction());
			Main.sex.getCharacterPerformingAction().wakeUp();
			
			// If not attracted to person fucking them, immediately set to resisting:
			if(!Main.sex.getCharacterPerformingAction().isDoll()
					&& Main.game.isNonConEnabled()
					&& !Main.sex.getCharacterPerformingAction().isAttractedTo(Main.game.getPlayer())
					&& !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
				Main.game.getTextEndStringBuilder().append(Main.sex.getCharacterPerformingAction().incrementAffection(Main.game.getPlayer(), -100));
				Main.sex.getCharacterPerformingAction().setLust(0);
			}
			
			return "";
		}
	};
	
	// Ovipositor actions:

//	public static final SexAction OVIPOSITOR_PENIS_EGG_LAYING_BLOCKED = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FIVE_EXTREME,
//			ArousalIncrease.FIVE_EXTREME,
//			CorruptionLevel.ZERO_PURE,
//			null,
//			SexParticipantType.NORMAL) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
//					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
//				return false;
//			}
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			return !Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()
//					&& !OVIPOSITOR_PENIS_EGG_LAYING.isBaseRequirementsMet();
//		}
//		@Override
//		public String getActionTitle() {
//			return "Lay eggs";
//		}
//		@Override
//		public String getActionDescription() {
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"[npc.Name] cannot be impregnated, so you cannot lay eggs in [npc.herHim]!");
//			}
//
//			if(Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"Epona will consider egg-laying to be cheating, and so you're not able to do this!");
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			
//			if(!areaContacted.isOrifice()) {
//				switch((SexAreaPenetration)areaContacted) {
//					case FINGER:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a handjob from [npc.name]!");
//					case FOOT:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a [npc.footjob] from [npc.name]!");
//					case TAIL:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a tailjob from [npc.name]!");
//					case TENTACLE:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a tentaclejob from [npc.name]!");
//					case CLIT:
//					case PENIS:
//					case TONGUE:
//						break;
//				}
//			} else {
//				return UtilText.parse(getCharacterToBeEgged(),
//						(((SexAreaOrifice) areaContacted).isInternalOrifice()
//							?"You cannot lay eggs in [npc.namePos] "
//							:"You cannot lay eggs while fucking [npc.namePos] ")
//						+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//			}
//			return UtilText.parse(getCharacterToBeEgged(),
//					"You cannot lay eggs while fucking [npc.namePos] "+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//		}
//		@Override
//		public String getDescription() {
//			return "";
//		}
//		@Override
//		public Response toResponse() {
//			if(!isBaseRequirementsMet()) {
//				return null;
//			}
//			return convertToNullResponse();
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SEX;
//		}
//	};
	
	public static final SexAction OVIPOSITOR_PENIS_EGG_LAYING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getAreaToBeEgged());
		}
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor penis, an egg-laying vagina, and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
//					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			if(!areaContacted.isOrifice()) {
				return false;
			}
			
			boolean isPenetratingSuitableOrifice  = false;
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return false;
					case NIPPLE:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
						break;
					case NIPPLE_CROTCH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
						break;
					case ANUS:
					case MOUTH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						break;
					case SPINNERET:
						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						return false;
					case VAGINA:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
						break;
				}
			}
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			try {
				switch((SexAreaOrifice)getAreaToBeEgged()) {
					case ANUS:
						return "Откладывать яйца (анально)";
					case MOUTH:
						return "Откладывать яйца (в живот)";
					case NIPPLE: case NIPPLE_CROTCH:
						return "Откладывать яйца (в грудь)";
					case VAGINA:
						return "Откладывать яйца (в матку)";
					case SPINNERET:
						return "Откладывать яйца (через паутинные железы)";
					case ARMPITS:
					case BREAST:
					case ASS:
					case BREAST_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return "";
				}
			} catch(Exception ex) {
				System.err.println("OVIPOSITOR_PENIS_EGG_LAYING getActionTitle() error - Area not found!");
			}
			return "Отложить яйца";
		}
		@Override
		public String getActionDescription() {
			String returnString = "Решить отложить яйца в [npc2.name].";
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			SexAreaInterface areaContacted = getAreaToBeEgged();
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString = "Засаживай свой член как можно глубже в [npc2.namePos] [npc2.asshole], а затем используй способность яйцеклада, чтобы отложить яйца в живот [npc2.her].";
					break;
				case MOUTH:
					returnString = "Засаживай свой член как можно глубже в глотку [npc2.namePos], а затем используй способность яйцеклада, чтобы отложить яйца в живот [npc2.her].";
					break;
				case NIPPLE:
					returnString = "Засаживай свой член как можно глубже в [npc2.namePos] [npc2.nipple+], а затем используй способность яйцеклада, чтобы отложить яйца в [npc2.her] [npc2.breasts].";
					break;
				case NIPPLE_CROTCH:
					returnString = "Засаживай свой член как можно глубже в [npc2.namePos] [npc2.crotchNipple+], а затем используй способность яйцеклада, чтобы отложить яйца в [npc2.her] [npc2.crotchBoobs].";
					break;
				case VAGINA:
					returnString = "Засаживай свой член как можно глубже в [npc2.namePos] [npc2.pussy+], а затем используй способность яйцеклада, чтобы отложить яйца в матку [npc2.her].";
					break;
				case SPINNERET:
					returnString = "Засаживай свой член как можно глубже в [npc2.namePos] [npc2.spinneret], а затем используй способность яйцеклада, чтобы отложить яйца внутри [npc2.herHim].";
					break;
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.PENIS, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {//TODO test appending
				sb.append(UtilText.parse(Main.sex.getCharacterPerformingAction(),
						"<p style='text-align:center;'>[style.boldTerrible(презерватив [npc.NamePos] порвался, пока [npc.she] [npc.was] откладывала [npc.her] яиц!)]</p>"));
				Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS).setSealed(false);
				Main.sex.getCharacterPerformingAction().unequipClothingIntoVoid(Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS), true, Main.sex.getCharacterPerformingAction());
			}
			
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
				areaEgged = "stomach";
			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
				areaEgged = "womb";
			}
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" вживлены в [npc.her] "+areaEgged+"!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			
			return sb.toString();
		}
		@Override
		public String applyEndEffects(){
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

//	public static final SexAction OVIPOSITOR_CLIT_EGG_LAYING_BLOCKED = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FIVE_EXTREME,
//			ArousalIncrease.FIVE_EXTREME,
//			CorruptionLevel.ZERO_PURE,
//			null,
//			SexParticipantType.NORMAL) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT);
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			if(Main.sex.getCharacterPerformingAction().getVaginaClitorisSize()==ClitorisSize.ZERO_AVERAGE
//					|| !Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
//				return false;
//			}
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			return !Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty() && !OVIPOSITOR_CLIT_EGG_LAYING.isBaseRequirementsMet();
//		}
//		@Override
//		public String getActionTitle() {
//			return "Lay eggs (clit)";
//		}
//		@Override
//		public String getActionDescription() {
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"[npc.Name] cannot be impregnated, so you cannot lay eggs in [npc.herHim]!");
//			}
//
//			if(Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"Epona will consider egg-laying to be cheating, and so you're not able to do this!");
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			
//			if(!areaContacted.isOrifice()) {
//				switch((SexAreaPenetration)areaContacted) {
//					case FINGER:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while being fingered by [npc.name]!");
//					case FOOT:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a [npc.footjob] from [npc.name]!");
//					case TAIL:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while being tail-fucked by [npc.name]!");
//					case TENTACLE:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while being tentacle-fucked by [npc.name]!");
//					case CLIT:
//					case PENIS:
//					case TONGUE:
//						break;
//				}
//			} else {
//				return UtilText.parse(getCharacterToBeEgged(),
//						(((SexAreaOrifice) areaContacted).isInternalOrifice()
//							?"You cannot lay eggs in [npc.namePos] "
//							:"You cannot lay eggs while fucking [npc.namePos] ")
//						+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//			}
//			return UtilText.parse(getCharacterToBeEgged(),
//					"You cannot lay eggs while fucking [npc.namePos] "+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//		}
//		@Override
//		public String getDescription() {
//			return "";
//		}
//		@Override
//		public Response toResponse() {
//			if(!isBaseRequirementsMet()) {
//				return null;
//			}
//			return convertToNullResponse();
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SEX;
//		}
//	};
	
	public static final SexAction OVIPOSITOR_CLIT_EGG_LAYING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT, getAreaToBeEgged());
		}
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).get(0);
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor clit, an egg-laying vagina, and for the eggs to be fertilised
			if(Main.sex.getCharacterPerformingAction().getVaginaClitorisSize()==ClitorisSize.ZERO_AVERAGE
					|| !Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
//					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty()) {
				return false;
			}
			
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			if(!areaContacted.isOrifice()) {
				return false;
			}
			
			boolean isPenetratingSuitableOrifice  = false;
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return false;
					case NIPPLE:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
						break;
					case NIPPLE_CROTCH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
						break;
					case ANUS:
					case MOUTH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						break;
					case SPINNERET:
						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						return false;
					case VAGINA:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
						break;
				}
			}
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			try {
				switch((SexAreaOrifice)getAreaToBeEgged()) {
					case ANUS:
						return "Отложить яйца (клитор-анал)";
					case MOUTH:
						return "Откладывать яйца (клитор-желудок)";
					case NIPPLE: case NIPPLE_CROTCH:
						return "Откладывать яйца (клитор-грудь)";
					case VAGINA:
						return "Откладывать яйца (клитор-матка)";
					case SPINNERET:
						return "Откладывать яйца (клитор-паутина)";
					case ARMPITS:
					case BREAST:
					case ASS:
					case BREAST_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return "";
				}
			} catch(Exception ex) {
				System.err.println("OVIPOSITOR_CLIT_EGG_LAYING getActionTitle() error - Area not found!");
			}
			return "Откладывать яйца (клитор)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "Решить отложить яйца в [npc2.name].";
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			SexAreaInterface areaContacted = getAreaToBeEgged();
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString = "Засадить клитор как можно глубже в [npc2.namePos] [npc2.asshole], а затем использовать способность яйцеклада, чтобы отложить яйца в желудок [npc2.her].";
					break;
				case MOUTH:
					returnString = "Засадить клитор как можно глубже в глотку [npc2.namePos], а затем использовать способность яйцеклада, чтобы отложить яйца в желудок [npc2.her].";
					break;
				case NIPPLE:
					returnString = "Засадить клитор как можно глубже в [npc2.namePos] [npc2.nipple+], а затем использовать способность яйцеклада, чтобы отложить яйца в [npc2.her] [npc2.breasts].";
					break;
				case NIPPLE_CROTCH:
					returnString = "Засадить клитор как можно глубже в [npc2.namePos] [npc2.crotchNipple+], а затем использовать способность яйцеклада, чтобы отложить яйца в [npc2.her] [npc2.crotchBoobs].";
					break;
				case VAGINA:
					returnString = "Засадить клитор как можно глубже в [npc2.namePos] [npc2.pussy+], а затем использовать способность яйцеклада, чтобы отложить яйца в матку [npc2.her].";
					break;
				case SPINNERET:
					returnString = "Вбей свой клитор как можно глубже в [npc2.namePos] [npc2.spinneret], а затем используй способность яйцеклада, чтобы отложить яйца внутрь [npc2.herHim].";
					break;
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.CLIT, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
				areaEgged = "stomach";
			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
				areaEgged = "womb";
			}
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" вживлены в [npc.her] "+areaEgged+"!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};
	
	// Need individual actions for tail egg laying, as it's possible for a character with multiple tails to be penetrating multiple orifices at once
	
	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_VAGINA = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.VAGINA;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null && !getCharacterToBeEgged().isPregnant();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Отложить яйца (хвостовая матка)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"Вбей свой хвост как можно глубже в [npc2.namePos] [npc2.pussy+], а затем используй способность яйцеклада, чтобы отложить яйца в матку [npc2.her].");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" внедренные в матку [npc.her]!)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_ANUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.ANUS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Отложить яйца (хвостовой анальный)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"Вбей свой хвост как можно глубже в [npc2.namePos] [npc2.asshole], а затем используй способность яйцеклада, чтобы отложить яйца в желудок [npc2.her].");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" внедренные в желудок [npc.her]!)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.MOUTH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Отложить яйца (хвостовой оральный)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"Вбей свой хвост как можно глубже в глотку [npc2.namePos], а затем используй способность яйцеклада, чтобы отложить яйца в желудок [npc2.her].");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" внедренные в желудок [npc.her]!)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_NIPPLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Откладывать яйца (хвост-сосок)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"Засадить хвостом как можно глубже в [npc2.namePos] [npc2.nipple+], а затем использовать его яйцеклад, чтобы отложить яйца в [npc2.her] [npc2.breasts].");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" вживленные в [npc.her] [npc.breasts+]!)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_NIPPLE_CROTCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE_CROTCH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Откладывать яйца (хвост-промежность-сосок)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"Засадить хвостом как можно глубже в [npc2.namePos] [npc2.crotchNipple+], а затем использовать его яйцеклад, чтобы отложить яйца в [npc2.her] [npc2.crotchBoobs].");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" вживленные в [npc.her] [npc.crotchBoobs+]!)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};
	
//	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FOUR_HIGH,
//			ArousalIncrease.THREE_NORMAL,
//			CorruptionLevel.TWO_HORNY,
//			null,
//			SexParticipantType.NORMAL) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			// To lay eggs, the orgasming character requires an ovipositor tail, an egg-laying vagina, and for the eggs to be fertilised
//			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
////					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
//				return false;
//			}
//			
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL).isEmpty()) {
//				return false;
//			}
//			
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return false;
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			if(!areaContacted.isOrifice()) {
//				return false;
//			}
//			
//			boolean isPenetratingSuitableOrifice  = false;
//			if(areaContacted.isOrifice()) {
//				switch((SexAreaOrifice)areaContacted) {
//					case ARMPITS:
//					case ASS:
//					case THIGHS:
//					case BREAST:
//					case BREAST_CROTCH:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return false;
//					case NIPPLE:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
//						break;
//					case NIPPLE_CROTCH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
//						break;
//					case ANUS:
//					case MOUTH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						break;
//					case SPINNERET:
//						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
////						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						return false;
//					case VAGINA:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
//						break;
//				}
//			}
//			if(!isPenetratingSuitableOrifice) {
//				return false;
//			}
//			
//			return true;
//		}
//		@Override
//		public SexActionPriority getPriority() {
//			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
//				return SexActionPriority.LOW;
//			}
//			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
//				return SexActionPriority.HIGH;
//			}
//			return SexActionPriority.NORMAL;
//		}
//		@Override
//		public String getActionTitle() {
//			try {
//				switch((SexAreaOrifice)getAreaToBeEgged()) {
//					case ANUS:
//						return "Lay eggs (tail-anal)";
//					case MOUTH:
//						return "Lay eggs (tail-stomach)";
//					case NIPPLE: case NIPPLE_CROTCH:
//						return "Lay eggs (tail-breasts)";
//					case VAGINA:
//						return "Lay eggs (tail-womb)";
//					case SPINNERET:
//						return "Lay eggs (tail-spinneret)";
//					case ARMPITS:
//					case BREAST:
//					case ASS:
//					case BREAST_CROTCH:
//					case THIGHS:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return "";
//				}
//			} catch(Exception ex) {
//				System.err.println("OVIPOSITOR_TAIL_EGG_LAYING getActionTitle() error - Area not found!");
//			}
//			return "Lay eggs (tail)";
//		}
//		@Override
//		public String getActionDescription() {
//			String returnString = "Decide to lay your eggs in [npc2.name].";
//			GameCharacter characterPenetrated = getCharacterToBeEgged();
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			switch((SexAreaOrifice)areaContacted) {
//				case ANUS:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.asshole], before using its ovipositor ability to lay your eggs in [npc2.her] stomach.";
//					break;
//				case MOUTH:
//					returnString = "Ram your tail as deep as possible down [npc2.namePos] throat, before using its ovipositor ability to lay your eggs in [npc2.her] stomach.";
//					break;
//				case NIPPLE:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.nipple+], before using its ovipositor ability to lay your eggs in [npc2.her] [npc2.breasts].";
//					break;
//				case NIPPLE_CROTCH:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.crotchNipple+], before using its ovipositor ability to lay your eggs in [npc2.her] [npc2.crotchBoobs].";
//					break;
//				case VAGINA:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.pussy+], before using its ovipositor ability to lay your eggs in [npc2.her] womb.";
//					break;
//				case SPINNERET:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.spinneret], before using its ovipositor ability to lay your eggs inside of [npc2.herHim].";
//					break;
//				case ARMPITS:
//				case ASS:
//				case BREAST:
//				case BREAST_CROTCH:
//				case THIGHS:
//				case URETHRA_PENIS:
//				case URETHRA_VAGINA:
//					break;
//			}
//			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
//		}
//		@Override
//		public String getDescription() {
//			return eggLayingTargetDescription(SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
//		}
//		@Override
//		public String applyPreParsingEffects() {
//			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
//			return "";
//		}
//		@Override
//		public void applyEffects() {
//			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
//			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
//			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
//				areaEgged = "stomach";
//			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
//				areaEgged = "womb";
//			}
//			Main.game.getTextEndStringBuilder().append(
//					"<p style='text-align:center;'>"
//							+ UtilText.parse(getCharacterToBeEgged(),
//									"[style.italicsYellowLight([npc.Name] [npc.has] had "+Util.intToString(eggCount)+" egg"+(eggCount>1?"s":"")+" implanted in [npc.her] "+areaEgged+"!)]")
//					+ "</p>");
//			
//			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
//		}
//		@Override
//		public String applyEndEffects() {
//			Main.sex.setCharacterLayingEggs(null);
//			return "";
//		}
//		@Override
//		public List<AbstractFetish> getFetishes(GameCharacter character) {
//			if(character.equals(Main.sex.getCharacterPerformingAction())) {
//				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
//			} else {
//				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
//			}
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SEX;
//		}
//	};
	
	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_VAGINA = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.VAGINA;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Откладывать яйца (свой хвост-матка)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "Засадить свой хвост как можно глубже в собственную [npc.pussy+], а затем использовать его яйцеклад, чтобы отложить яйца в свою матку.";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" внедренные в матку [npc.her]!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_ANUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.ANUS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Откладывать яйца (свой хвост-анус)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "Засадить свой хвост как можно глубже в собственную [npc.asshole], а затем использовать его яйцеклад, чтобы отложить яйца в свой желудок.";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" внедренные в желудок [npc.her]!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.MOUTH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Откладывать яйца (хвостом в рот)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "Запихнуть хвост как можно глубже себе в глотку, а затем использовать яйцеклад, чтобы отложить яйца прямо в желудок.";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" внедренные в желудок [npc.her]!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_NIPPLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Откладывать яйца (хвостом в сосок)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "Запихнуть хвост как можно глубже в свой [npc.nipple+], а затем использовать яйцеклад, чтобы отложить яйца в [npc.breasts].";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" вживленные в [npc.her] [npc.breasts+]!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_NIPPLE_CROTCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE_CROTCH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "Откладывать яйца (хвостом в промежность-сосок)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "Запихнуть хвост как можно глубже в свою [npc.crotchNipple+], а затем использовать яйцеклад, чтобы отложить яйца в [npc.crotchBoobs].";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight(у [npc.Name] [npc.has] были "+Util.intToString(eggCount)+" яйцом,"+(eggCount>1?"s":"")+" вживленные в [npc.her] [npc.crotchBoobs+]!)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};
	
//	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FOUR_HIGH,
//			ArousalIncrease.THREE_NORMAL,
//			CorruptionLevel.TWO_HORNY,
//			null,
//			SexParticipantType.SELF) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			// To lay eggs, the orgasming character requires an ovipositor tail, an egg-laying vagina, and for the eggs to be fertilised
//			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
////					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
//				return false;
//			}
//			
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL).isEmpty()) {
//				return false;
//			}
//			
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return false;
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			if(!areaContacted.isOrifice()) {
//				return false;
//			}
//			
//			boolean isPenetratingSuitableOrifice  = false;
//			if(areaContacted.isOrifice()) {
//				switch((SexAreaOrifice)areaContacted) {
//					case ARMPITS:
//					case ASS:
//					case THIGHS:
//					case BREAST:
//					case BREAST_CROTCH:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return false;
//					case NIPPLE:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
//						break;
//					case NIPPLE_CROTCH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
//						break;
//					case ANUS:
//					case MOUTH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						break;
//					case SPINNERET:
//						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
////						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						return false;
//					case VAGINA:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
//						break;
//				}
//			}
//			if(!isPenetratingSuitableOrifice) {
//				return false;
//			}
//			
//			return true;
//		}
//		@Override
//		public SexActionPriority getPriority() {
//			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
//				return SexActionPriority.LOW;
//			}
//			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
//				return SexActionPriority.HIGH;
//			}
//			return SexActionPriority.NORMAL;
//		}
//		@Override
//		public String getActionTitle() {
//			try {
//				switch((SexAreaOrifice)getAreaToBeEgged()) {
//					case ANUS:
//						return "Lay eggs (self tail-anal)";
//					case MOUTH:
//						return "Lay eggs (self tail-stomach)";
//					case NIPPLE: case NIPPLE_CROTCH:
//						return "Lay eggs (tail-breasts)";
//					case VAGINA:
//						return "Lay eggs (self tail-womb)";
//					case SPINNERET:
//						return "Lay eggs (self tail-spinneret)";
//					case ARMPITS:
//					case BREAST:
//					case ASS:
//					case BREAST_CROTCH:
//					case THIGHS:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return "";
//				}
//			} catch(Exception ex) {
//				System.err.println("OVIPOSITOR_TAIL_EGG_LAYING_SELF getActionTitle() error - Area not found!");
//			}
//			return "Lay eggs (self tail)";
//		}
//		@Override
//		public String getActionDescription() {
//			String returnString = "Decide to lay your eggs in yourself.";
//			GameCharacter characterPenetrated = getCharacterToBeEgged();
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			switch((SexAreaOrifice)areaContacted) {
//				case ANUS:
//					returnString = "Ram your tail as deep as possible into your own [npc2.asshole], before using its ovipositor ability to lay your eggs in your stomach.";
//					break;
//				case MOUTH:
//					returnString = "Ram your tail as deep as possible down your own throat, before using its ovipositor ability to lay your eggs in your stomach.";
//					break;
//				case NIPPLE:
//					returnString = "Ram your tail as deep as possible into your own [npc2.nipple+], before using its ovipositor ability to lay your eggs in your [npc2.breasts].";
//					break;
//				case NIPPLE_CROTCH:
//					returnString = "Ram your tail as deep as possible into your own [npc2.crotchNipple+], before using its ovipositor ability to lay your eggs in your [npc2.crotchBoobs].";
//					break;
//				case VAGINA:
//					returnString = "Ram your tail as deep as possible into your own [npc2.pussy+], before using its ovipositor ability to lay your eggs in your womb.";
//					break;
//				case SPINNERET:
//					returnString = "Ram your tail as deep as possible into your own [npc2.spinneret], before using its ovipositor ability to lay your eggs inside of yourself.";
//					break;
//				case ARMPITS:
//				case ASS:
//				case BREAST:
//				case BREAST_CROTCH:
//				case THIGHS:
//				case URETHRA_PENIS:
//				case URETHRA_VAGINA:
//					break;
//			}
//			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
//		}
//		@Override
//		public String getDescription() {
//			return eggLayingTargetDescription(SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
//		}
//		@Override
//		public String applyPreParsingEffects() {
//			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
//			return "";
//		}
//		@Override
//		public void applyEffects() {
//			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
//			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
//			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
//				areaEgged = "stomach";
//			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
//				areaEgged = "womb";
//			}
//			Main.game.getTextEndStringBuilder().append(
//					"<p style='text-align:center;'>"
//							+ UtilText.parse(getCharacterToBeEgged(),
//									"[style.italicsYellowLight([npc.Name] [npc.has] had "+Util.intToString(eggCount)+" egg"+(eggCount>1?"s":"")+" implanted in [npc.her] "+areaEgged+"!)]")
//					+ "</p>");
//			
//			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
//		}
//		@Override
//		public String applyEndEffects() {
//			Main.sex.setCharacterLayingEggs(null);
//			return "";
//		}
//		@Override
//		public List<AbstractFetish> getFetishes(GameCharacter character) {
//			if(character.equals(Main.sex.getCharacterPerformingAction())) {
//				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
//			} else {
//				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
//			}
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SELF;
//		}
//	};
}
