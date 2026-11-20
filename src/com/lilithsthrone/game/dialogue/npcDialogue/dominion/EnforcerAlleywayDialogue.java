package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.EnforcerPatrol;
import com.lilithsthrone.game.character.npc.misc.NPCOffspring;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.8.3
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class EnforcerAlleywayDialogue {
	
	private static final int BRIBE_AMOUNT = 2_000;
	
	private static final String UNIFORM_PASSABLE = "uniform_passable";
	private static final String IMPERSONATING_CANDI = "impersonating_candi";
	private static final String IMPERSONATING_CLAIRE = "impersonating_claire";
	private static final String IMPERSONATING_BRAX = "impersonating_brax";
	private static final String IMPERSONATING_WES = "impersonating_wes";
	private static final String IMPERSONATING_ELLE = "impersonating_elle";
	private static final String IMPERSONATING_NYSA = "impersonating_nysa";
	private static final String IMPERSONATING_SEAN = "impersonating_sean";
	
	private static boolean bribed = false;
	private static boolean searched = false;
	private static boolean isLeaderSearching = false;
	private static boolean hadSex = false;
	private static boolean encounteredBefore = false;

	private static boolean contrabandFound = false;
	private static boolean heavyContrabandFound = false;
		
	private static int uniformPassable; // -1 = impassable
									 // 0 = passable but a few minor details are wrong
									 // 1 = passable
	
	private static int impersonatingCandi; // same as above but
										   // 0 = not impersonating
										   // -2 or less = impassable because of a quest outcome
	private static int impersonatingClaire;
	private static int impersonatingBrax;
	private static int impersonatingWes;
	private static int impersonatingElle;
	private static int impersonatingNysa;
	private static int impersonatingSean;
		
	private static Map<AbstractItem, Integer> itemsConfiscated = new HashMap<>();
	private static Map<AbstractWeapon, Integer> weaponsConfiscated = new HashMap<>();
	private static Map<AbstractClothing, Integer> clothingConfiscated = new HashMap<>();
	
	
	private static SexType playerSexType;
	private static boolean enforcerWantsPlayerSex;

	private static boolean playerContraband(ItemTag tag, boolean checkForPass) {
		if(checkForPass
				&& tag==ItemTag.CONTRABAND_MEDIUM
				&& (Main.game.getPlayer().hasItemType("innoxia_quest_special_pass") || Main.game.getPlayer().hasItemType("innoxia_quest_special_pass_elle"))) {
			return false;
		}
		
		return Main.game.getPlayer().getAllItemsInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(tag))
				|| Main.game.getPlayer().getAllWeaponsInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(tag))
				|| Main.game.getPlayer().getAllClothingInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(tag));
	}
	
	private static void initVariables() {
		bribed = false;
		searched = false;
		hadSex = false;
		
		contrabandFound = false;
		heavyContrabandFound = false;
		itemsConfiscated = new HashMap<>();
		weaponsConfiscated = new HashMap<>();
		clothingConfiscated = new HashMap<>();
		
		encounteredBefore = ((NPC)getEnforcerLeader()).hasEncounteredBefore();
		setDemonRevealed(Main.game.getPlayer().getRace()==Race.DEMON);
		
		uniformPassable = -1;
		impersonatingCandi = 0;
		impersonatingClaire = 0;
		impersonatingBrax = 0;
		impersonatingWes = 0;
		impersonatingElle = 0;
		impersonatingNysa = 0;
		impersonatingSean = 0;
				
		if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
			setThinksPlayerEnforcer(checkPlayerUniform());
			Main.game.getDialogueFlags().setSavedLong(UNIFORM_PASSABLE, uniformPassable);
			Main.game.getDialogueFlags().setSavedLong(IMPERSONATING_CANDI, impersonatingCandi);
			Main.game.getDialogueFlags().setSavedLong(IMPERSONATING_CLAIRE, impersonatingClaire);
			Main.game.getDialogueFlags().setSavedLong(IMPERSONATING_BRAX, impersonatingBrax);
			Main.game.getDialogueFlags().setSavedLong(IMPERSONATING_WES, impersonatingWes);
			Main.game.getDialogueFlags().setSavedLong(IMPERSONATING_ELLE, impersonatingElle);
			Main.game.getDialogueFlags().setSavedLong(IMPERSONATING_NYSA, impersonatingNysa);
			Main.game.getDialogueFlags().setSavedLong(IMPERSONATING_SEAN, impersonatingSean);
		}
	}
		
	private static boolean checkPlayerUniform() {
		//human and angel players immediately fail
		if(Main.game.getPlayer().hasAnyEnforcerStatusEffect() && (Main.game.getPlayer().getRace() == Race.HUMAN || Main.game.getPlayer().getRace() == Race.ANGEL)) {
			uniformPassable = -1;
		}
		//check uniform elements
		//check for a dress jacket
		else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER) != null
				&& (Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getClothingType().getId().equals("dsg_eep_servequipset_enfdjacket") ||
				Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getClothingType().getId().equals("dsg_eep_servequipset_debuggerydo_enfdjacket"))) {
			//blank uniforms fail
			if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().isEmpty()) {
				uniformPassable = -1;
				
			} else {
				switch (Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().get("name")) {
					//generic uniforms with mismatched ranks will arouse suspicion
					case "name_pc":
						if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().get("collar").equals("tab_pc")) {
							uniformPassable = 1;
						} else {
							uniformPassable = 0;
						}
						break;
					case "name_sg":
						if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().get("collar").equals("tab_sg")) {
							uniformPassable = 1;
						} else {
							uniformPassable = 0;
						}
						break;
					case "name_ip":
						if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().get("collar").equals("tab_ip")) {
							uniformPassable = 1;
						} else {
							uniformPassable = 0;
						}
						break;
					case "name_su":
						if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().get("collar").equals("tab_su")) {
							uniformPassable = 1;
						} else {
							uniformPassable = 0;
						}
						break;
					case "name_cs":
						if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().get("collar").equals("tab_cs")) {
							uniformPassable = 1;
						} else {
							uniformPassable = 0;
						}
						break;
					//named npc checks
					case "name_brax":
						//the player defeated Brax and caused him to be enslaved
						if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
							uniformPassable = -1;
							impersonatingBrax = -2;
						} else if ((Main.game.getPlayer().getSubspecies() == Subspecies.WOLF_MORPH)){
							uniformPassable =  1;
							impersonatingBrax = 1;								
						} else {
							uniformPassable = -1;
							impersonatingBrax = -1;
						}
						break;
					case "name_candi":
						if(Main.game.getPlayer().getSubspecies() == Subspecies.CAT_MORPH) {
							uniformPassable = 1;
							impersonatingCandi = 1;
						} else {
							uniformPassable = -1;
							impersonatingCandi = -1;
						}
						break;
					case "name_claire":
						if(Main.game.getPlayer().getSubspecies() == Subspecies.CAT_MORPH) {
							uniformPassable = 1;
							impersonatingClaire = 1;
						} else {
							uniformPassable = -1;
							impersonatingClaire = -1;
						}
						break;
					case "name_elle":
						// player sided with Wes and caused Elle to get enslaved
						if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_WES)) {
							uniformPassable = -1;
							impersonatingElle = -2;
						} else if (Main.game.getPlayer().getSubspecies() == Subspecies.HORSE_MORPH_UNICORN) {
							uniformPassable = 1;
							impersonatingElle = 1;
						} else {
							uniformPassable = -1;
							impersonatingElle = -1;
						}
						break;
					case "name_wesley":
						// player sided with Elle and caused Wes to get enslaved
						if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)) {
							uniformPassable = -1;
							impersonatingWes = -2;
						} else if (Main.game.getPlayer().getSubspecies() == Subspecies.FOX_MORPH) {
							uniformPassable = 1;
							impersonatingWes = 1;
						} else {
							uniformPassable = -1;
							impersonatingWes = -1;
						}
						break;
					case "name_nysa":
						if(Main.game.getPlayer().getSubspecies() == Subspecies.LILIN) {
							uniformPassable = 1;
							impersonatingNysa = 1;
						} else {
							uniformPassable = -1;
							impersonatingNysa = -1;
						}
						break;
				}
			}			   
		}
		
		//check for a stabvest
		else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER) != null &&
				Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getId().startsWith("dsg_eep_ptrlequipset_stpvest")) {
			//check for nameplates			
			if(!Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().isEmpty()) {
				switch (Main.game.getPlayer().getClothingInSlot(InventorySlot.TORSO_OVER).getStickers().get("name_plate")){
					case "claire":
						if(Main.game.getPlayer().getSubspecies() == Subspecies.CAT_MORPH) {
							uniformPassable = 1;
							impersonatingCandi = 1;
						} else {
							uniformPassable = -1;
							impersonatingCandi = -1;
						}
						break;
					case "sean":
						if(Main.game.getPlayer().getSubspecies() == Subspecies.RABBIT_MORPH) {
							uniformPassable = 1;
							impersonatingSean = 1;
						} else {
							uniformPassable = -1;
							impersonatingSean = -1;
						}
						break;
					case "enforcer":
					    uniformPassable = 1;
					    break;
						
				}
			}
			else {
				uniformPassable = 0;
			}
		}
		
		if((uniformPassable 
				+ impersonatingBrax 
				+ impersonatingCandi 
				+ impersonatingClaire 
				+ impersonatingElle 
				+ impersonatingWes 
				+ impersonatingNysa 
				+ impersonatingSean) >= 0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isDemonRevealed() {
		return ((NPC)getEnforcerLeader()).hasFlag(NPCFlagValue.knowsPlayerDemon);
	}

	public static void setDemonRevealed(boolean demonRevealed) {
		if(demonRevealed) {
			((NPC)getEnforcerLeader()).addFlag(NPCFlagValue.knowsPlayerDemon);
			((NPC)getEnforcerSubordinate()).addFlag(NPCFlagValue.knowsPlayerDemon);
		} else {
			((NPC)getEnforcerLeader()).removeFlag(NPCFlagValue.knowsPlayerDemon);
			((NPC)getEnforcerSubordinate()).removeFlag(NPCFlagValue.knowsPlayerDemon);
		}
	}

	public static boolean isThinksPlayerEnforcer() {
		return ((NPC)getEnforcerLeader()).hasFlag(NPCFlagValue.thinksPlayerEnforcer);
	}
	
	private static void setThinksPlayerEnforcer(boolean thinksPlayerEnforcer) {
		if(thinksPlayerEnforcer) {
			((NPC)getEnforcerLeader()).addFlag(NPCFlagValue.thinksPlayerEnforcer);
			((NPC)getEnforcerSubordinate()).addFlag(NPCFlagValue.thinksPlayerEnforcer);
		} else {
			((NPC)getEnforcerLeader()).removeFlag(NPCFlagValue.thinksPlayerEnforcer);
			((NPC)getEnforcerSubordinate()).removeFlag(NPCFlagValue.thinksPlayerEnforcer);
		}
	}
	
	private static boolean isKind() {
		return getEnforcerLeader().hasPersonalityTrait(PersonalityTrait.KIND);
	}
	
	private static GameCharacter getEnforcerLeader() {
		return getEnforcers().get(0);
	}
	
	private static GameCharacter getEnforcerSubordinate() {
		return getEnforcers().get(1);
	}
	
	private static List<GameCharacter> getEnforcers() {
		List<GameCharacter> enforcers = new ArrayList<>();
		enforcers.addAll(Main.game.getCharactersPresent());
		enforcers.removeIf((character) -> !(character instanceof EnforcerPatrol));
		Collections.sort(enforcers, (c1, c2) -> c2.getLevel()-c1.getLevel());
		return enforcers;
	}
	
	private static List<GameCharacter> getEnforcersAndCriminal() {
		List<GameCharacter> characters = new ArrayList<>(getEnforcers());
		characters.add(getCriminalInTile());
		return characters;
	}
	
	public static void banishEnforcers(boolean delete) {
		List<String> enforcerIds = new ArrayList<>();
		
		for(GameCharacter enforcer : getEnforcers()) {
			enforcerIds.add(enforcer.getId());
			for(Entry<AbstractItem, Integer> entry : new HashMap<>(enforcer.getAllItemsInInventory()).entrySet()) {
				if(entry.getKey().getItemType()==ItemType.CONDOM_USED) {
					enforcer.dropItem(entry.getKey(), entry.getValue(), false);
				}
			}
			if(delete) {
				Main.game.banishNPC((NPC) enforcer);
			} else {
				enforcer.setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_OFFICE, true);
			}
		}
		
		if(delete) {
			Main.game.removeSavedEnforcers(WorldType.DOMINION, enforcerIds);
		}
	}
	
	private static GameCharacter getCriminalInTile() {
		for(GameCharacter ch : Main.game.getCharactersPresent()) {
			if(((ch instanceof DominionAlleywayAttacker) || ch instanceof NPCOffspring) && !Main.game.getPlayer().getCompanions().contains(ch)) {
				return ch;
			}
		}
		return null;
	}

	private static void banishCriminal() {
		Main.game.banishNPC((NPC) getCriminalInTile());
	}
	
	private static SexManagerDefault getSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> domSlots,
			Map<GameCharacter, SexSlot> subSlots,
			SexType preference,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(true,
				position,
				domSlots,
				subSlots) {
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return false;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isCharacterStartNaked(GameCharacter character) {
				return character.isPlayer();
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					if(domSlots.size()>1 && Main.sex.getSexPositionSlot(character)==SexSlotAllFours.IN_FRONT) {
						if(character.hasPenis()) {
							return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
						} else {
							return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
						}
					} else {
						return preference;
					}
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(Main.sex.isDom(partner)) {
					boolean domsSatisfied = true;
					for(GameCharacter character : Main.sex.getDominantParticipants(false).keySet()) {
						if(!Main.sex.isSatisfiedFromOrgasms(character, true) && Main.sex.getSexPositionSlot(character)!=SexSlotGeneric.MISC_WATCHING) {
							domsSatisfied = false;
						}
					}
					return domsSatisfied;
				}
				
				return super.isPartnerWantingToStopSex(partner);
			}
		};
	}

	private static SexType getWantedSexType(GameCharacter enforcer, GameCharacter target) {
		/* Involves:
			Penis-Vagina
			Penis-Anus
			Finger-Vagina
			Finger-anus + Finger-penis
		*/
		Map<SexType, Integer> sexTypeMap = new HashMap<>();
		
		if(enforcer.hasPenis()) {
			if(target.hasVagina() && target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), 20);
			}
			if(Main.game.isAnalContentEnabled() && target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS), 10);
			}
		}
		if(target.hasVagina() && target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA), 5);
		}
		if(target.hasPenisIgnoreDildo() && target.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			sexTypeMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS), 5);
		}
		
		if(!sexTypeMap.isEmpty()) {
			return Util.getRandomObjectFromWeightedMap(sexTypeMap);
		}
		
		return null;
	}
	
	private static ResponseSex getEnforcerSexResponse(String title,
			String description,
			GameCharacter partner,
			GameCharacter spectator,
			boolean threesome,
			SexType sexType,
			boolean consensual,
			DialogueNode postSexNode) {
		List<GameCharacter> enforcersParsingOrdered = Util.newArrayListOfValues(partner, spectator);
		
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
			return new ResponseSex(title,
					description,
					consensual,
					false,
					threesome
						?getSexManager(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(partner, SexSlotAllFours.BEHIND),
										new Value<>(spectator, SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								sexType,
								Util.newHashMapOfValues(
										new Value<>(partner, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(spectator, Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA))))
						:getSexManager(
								SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(partner, SexSlotAgainstWall.STANDING_WALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL)),
								sexType,
								Util.newHashMapOfValues(new Value<>(partner, Util.newArrayListOfValues(CoverableArea.PENIS)))),
					Util.newArrayListOfValues(spectator),
					Main.game.getPlayer().getCompanions(),
					postSexNode,
					UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_PENIS_PUSSY"+(threesome?"_THREESOME":""), enforcersParsingOrdered)) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					List<InitialSexActionInformation> list = new ArrayList<>();
					list.add(new InitialSexActionInformation(partner, Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					if(threesome) {
						list.add(new InitialSexActionInformation(spectator, Main.game.getPlayer(), spectator.hasPenis()?PenisMouth.BLOWJOB_START:TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
					}
					return list;
				}
				@Override
				public void effects() {
					if(((NPC)partner).isWantingToEquipCondom(Main.game.getPlayer())) {
						partner.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, partner);
					}
				}
			};
			
		} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
			return new ResponseSex(title,
					description,
					consensual,
					false,
					threesome
						?getSexManager(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(partner, SexSlotAllFours.BEHIND),
										new Value<>(spectator, SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								sexType,
								Util.newHashMapOfValues(
										new Value<>(partner, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(spectator, Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA))))
						:getSexManager(
							SexPosition.AGAINST_WALL,
							Util.newHashMapOfValues(new Value<>(partner, SexSlotAgainstWall.STANDING_WALL)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL)),
							sexType,
							Util.newHashMapOfValues(new Value<>(partner, Util.newArrayListOfValues(CoverableArea.PENIS)))),
					Util.newArrayListOfValues(spectator),
					Main.game.getPlayer().getCompanions(),
					postSexNode,
					UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_PENIS_ASSHOLE"+(threesome?"_THREESOME":""), enforcersParsingOrdered)) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					List<InitialSexActionInformation> list = new ArrayList<>();
					list.add(new InitialSexActionInformation(partner, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					if(threesome) {
						list.add(new InitialSexActionInformation(spectator, Main.game.getPlayer(), spectator.hasPenis()?PenisMouth.BLOWJOB_START:TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
					}
					return list;
				}
				@Override
				public void effects() {
					if(((NPC)partner).isWantingToEquipCondom(Main.game.getPlayer())) {
						partner.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_PURPLE_DARK, false), InventorySlot.PENIS, true, partner);
					}
				}
			};
			
		} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))) {
			return new ResponseSex(title,
					description,
					consensual,
					false,
					threesome
						?getSexManager(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(partner, SexSlotAllFours.BEHIND),
										new Value<>(spectator, SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								sexType,
								Util.newHashMapOfValues(
										new Value<>(partner, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(spectator, Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA))))
						:getSexManager(
							SexPosition.AGAINST_WALL,
							Util.newHashMapOfValues(new Value<>(partner, SexSlotAgainstWall.STANDING_WALL)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL)),
							sexType,
							Util.newHashMapOfValues()),
					Util.newArrayListOfValues(spectator),
					Main.game.getPlayer().getCompanions(),
					postSexNode,
					UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_FINGER_PUSSY"+(threesome?"_THREESOME":""), enforcersParsingOrdered)) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					List<InitialSexActionInformation> list = new ArrayList<>();
					list.add(new InitialSexActionInformation(partner, Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true));
					if(threesome) {
						list.add(new InitialSexActionInformation(spectator, Main.game.getPlayer(), spectator.hasPenis()?PenisMouth.BLOWJOB_START:TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
					}
					return list;
				}
			};
			
		} else if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS))) {
			return new ResponseSex(title,
					description,
					consensual,
					false,
					threesome
						?getSexManager(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(partner, SexSlotAllFours.BEHIND),
										new Value<>(spectator, SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								sexType,
								Util.newHashMapOfValues(
										new Value<>(partner, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(spectator, Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA))))
						:getSexManager(
							SexPosition.AGAINST_WALL,
							Util.newHashMapOfValues(new Value<>(partner, SexSlotAgainstWall.STANDING_WALL)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAgainstWall.FACE_TO_WALL)),
							sexType,
							Util.newHashMapOfValues()),
					Util.newArrayListOfValues(spectator),
					Main.game.getPlayer().getCompanions(),
					postSexNode,
					UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEX_START_FINGER_PENIS"+(threesome?"_THREESOME":""), enforcersParsingOrdered)) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					List<InitialSexActionInformation> list = new ArrayList<>();
					if(partner.getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) && Main.game.isAnalContentEnabled()) {
						list.add(new InitialSexActionInformation(partner, Main.game.getPlayer(), FingerAnus.ANAL_FINGERING_START, false, true));
					}
					list.add(new InitialSexActionInformation(partner, Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
					if(threesome) {
						list.add(new InitialSexActionInformation(spectator, Main.game.getPlayer(), spectator.hasPenis()?PenisMouth.BLOWJOB_START:TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
					}
					return list;
				}
			};
		}
		return null;
	}

	private static boolean contrabandCheck(Collection<ItemTag> tags) {
		if(tags==null) {
			return false;
		}
		if(tags.contains(ItemTag.CONTRABAND_HEAVY)) {
			heavyContrabandFound = true;
			return true;
		}
		return tags.contains(ItemTag.CONTRABAND_MEDIUM)
				&& !Main.game.getPlayer().hasItemType("innoxia_quest_special_pass")
				&& !Main.game.getPlayer().hasItemType("innoxia_quest_special_pass_elle");
	}
	
	public static final DialogueNode ENFORCER_ALLEYWAY_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			initVariables();
		}
		@Override
		public int getSecondsPassed() {
			return 0; // 0 seconds as don't want time to tick over into night (which would make dialogue and actions inconsistent with one another)
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(encounteredBefore) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_REPEAT", getEnforcers()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START", getEnforcers()));
			}
			
			if(Main.game.getPlayer().getAllItemsInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(ItemTag.CONTRABAND_HEAVY))
					|| Main.game.getPlayer().getAllWeaponsInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(ItemTag.CONTRABAND_HEAVY))
					|| Main.game.getPlayer().getAllClothingInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(ItemTag.CONTRABAND_HEAVY))) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_CONTRABAND_HEAVY", getEnforcers()));
				
			} else if(Main.game.getPlayer().getAllItemsInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM))
					|| Main.game.getPlayer().getAllWeaponsInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM))
					|| Main.game.getPlayer().getAllClothingInInventory().keySet().stream().anyMatch(c->c.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM))) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_CONTRABAND_MEDIUM", getEnforcers()));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((NPC)getEnforcerLeader()).hasFlag(NPCFlagValue.playerEscapedLastCombat)) {
				if(index==1) {
					return new ResponseCombat("Защищаться",
							"Enforcer полны решимости избить тебя!<br/>[style.italicsBad(Если ты победишь Enforcer в бою, они исчезнут из игры!)]",
							(NPC)getEnforcerLeader(),
							getEnforcers(),
							Util.newHashMapOfValues(
									new Value<>(getEnforcerLeader(), UtilText.parse(getEnforcerLeader(), "[npc.name] кричит: [npc.speech(В этот раз тебе не сбежать!)]")),
									new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.name] восклицает: [npc.speech(Ну всё, ты попался!)]"))));
				}
				
			} else if (Main.game.getPlayer().hasAnyEnforcerStatusEffect() && uniformPassable < 0 && !searched) {
				if(index==1) {
					return new ResponseCombat("Защищаться",
							"Enforcer полны решимости избить тебя!<br/>[style.italicsBad(Если ты победишь Enforcer в бою, они исчезнут из игры!)]",
							(NPC)getEnforcerLeader(),
							getEnforcers(),
							Util.newHashMapOfValues(
									new Value<>(getEnforcerLeader(), UtilText.parse(getEnforcerLeader(), "[npc.name] кричит: [npc.speech(Думаешь, нас за дураков можно держать?!)]")),
									new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.name] восклицает: [npc.speech(Ты только что совершил огромную ошибку!)]"))));
					
				} else if(index==2) {
					return new Response(UtilText.parse(getEnforcerLeader(), "Обыскано (<span style='color:"+getEnforcerLeader().getFemininity().getColour().toWebHexString()+";'>[npc.surname]</span>)"),
							UtilText.parse(getEnforcerLeader(), "Сделай, как говорит [npc.name], и сдай свой маскировочный костюм...")
							+(playerContraband(ItemTag.CONTRABAND_HEAVY, true)
								?"<br/>[style.italicsTerrible(Часть твоих вещей будет конфискована, а тебя арестуют!)]"
								:(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)
									?"<br/>[style.italicsBad(Часть твоих вещей будет конфискована!)]"
									:"")),
							ENFORCER_ALLEYWAY_SEARCHED) {
							@Override
							public Colour getHighlightColour() {
								if(playerContraband(ItemTag.CONTRABAND_HEAVY, true)) {
									return PresetColour.GENERIC_TERRIBLE;
								}
								if(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)) {
									return PresetColour.GENERIC_BAD;
								}
								return super.getHighlightColour();
							}
							@Override
							public void effects() {
								isLeaderSearching = true;
							}
						};
						
				} else if(index==3) {
					return new Response(UtilText.parse(getEnforcerSubordinate(), "Обыскано (<span style='color:"+getEnforcerSubordinate().getFemininity().getColour().toWebHexString()+";'>[npc.surname]</span>)"),
							UtilText.parse(getEnforcerSubordinate(),"Сбросить маскировку, но попросить, чтобы именно [npc.name] обыскал тебя...")
								+(playerContraband(ItemTag.CONTRABAND_HEAVY, true)
									?"<br/>[style.italicsTerrible(Часть твоих вещей будет конфискована, а тебя арестуют!)]"
									:(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)
										?"<br/>[style.italicsBad(Часть твоих вещей будет конфискована!)]"
										:"")),
							ENFORCER_ALLEYWAY_SEARCHED) {
							@Override
							public Colour getHighlightColour() {
								if(playerContraband(ItemTag.CONTRABAND_HEAVY, true)) {
									return PresetColour.GENERIC_TERRIBLE;
								}
								if(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)) {
									return PresetColour.GENERIC_BAD;
								}
								return super.getHighlightColour();
							}
							@Override
							public void effects() {
								isLeaderSearching = false;
							}
						};
				}
				
			} else {
				boolean foughtBefore = ((NPC)getEnforcerLeader()).getFoughtPlayerCount()>0;
				boolean wantsToSearch = !isThinksPlayerEnforcer()
						&& (Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime())
						&& !isDemonRevealed()
						&& !searched
						&& !hadSex
						&& !bribed
						&& !foughtBefore;
				if(index==1) {
					if(wantsToSearch) {
						return new Response("Уйти", "Enforcer не отпустят тебя, не обыскав сначала...", null);
					} else {
						return new Response("Уйти", "Сказать Enforcer, что тебе нечего сообщить о преступной деятельности, и продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								if(hadSex) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_CONTINUE_HAD_SEX", getEnforcers()));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_CONTINUE", getEnforcers()));
								}
								banishEnforcers(false);
							}
						};
					}
					
				} if(index==2) {
					String title = UtilText.parse(getEnforcerLeader(), "Обыскан ([npc.surname])");
					if(searched) {
						return new Response(title,
								"Enforcer уже обыскали тебя, так что повторно им это не интересно...", null);
						
					} else if(isThinksPlayerEnforcer()) {
						return new Response(title,
								"Поскольку Enforcer считают тебя одним из своих, у них нет ни малейшего желания тебя обыскивать.", null);
						
					} else if(hadSex) {
						return new Response(title,
								"Поскольку Enforcers уже переспали с тобой, им совершенно неинтересно тебя обыскивать.", null);
						
					} else if(foughtBefore) {
						return new Response(title,
								"Enforcers не хотят связываться с кем-то, кто состоит в родстве со Старшей лилин, поэтому им нет дела до твоего обыска...", null);
						
					} else if(bribed) {
						return new Response(title,
								"Так как ты подкупил Enforcers, им нет никакого интереса тебя обыскивать...", null);
						
					} else {
						return new Response(UtilText.parse(getEnforcerLeader(), "Обыскано (<span style='color:"+getEnforcerLeader().getFemininity().getColour().toWebHexString()+";'>[npc.surname]</span>)"),
								UtilText.parse(getEnforcerLeader(),
									((Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime()) && !isDemonRevealed())
										?"Сделай, как говорит [npc.name], и подвергнись поверхностному обыску, чтобы они могли подтвердить, что ты не замышляешь ничего дурного..."
										:"Скажи [npc.name], что, по твоему мнению, [npc.she] должен выполнять [npc.her] работу и провести тебе поверхностный обыск, чтобы убедиться, что ты не замышляешь ничего дурного...")
								+(playerContraband(ItemTag.CONTRABAND_HEAVY, true)
									?"<br/>[style.italicsTerrible(Часть твоих вещей будет конфискована, а тебя арестуют!)]"
									:(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)
										?"<br/>[style.italicsBad(Часть твоих вещей будет конфискована!)]"
										:"")),
								ENFORCER_ALLEYWAY_SEARCHED) {
							@Override
							public Colour getHighlightColour() {
								if(playerContraband(ItemTag.CONTRABAND_HEAVY, true)) {
									return PresetColour.GENERIC_TERRIBLE;
								}
								if(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)) {
									return PresetColour.GENERIC_BAD;
								}
								return super.getHighlightColour();
							}
							@Override
							public void effects() {
								isLeaderSearching = true;
							}
						};
					}
					
				} if(index==3) {
					String title = UtilText.parse(getEnforcerSubordinate(), "Обыскан ([npc.surname])");
					if(searched) {
						return new Response(title,
								"Enforcer уже обыскали тебя, так что повторно им это не интересно...", null);
						
					} else if(isThinksPlayerEnforcer()) {
						return new Response(title,
								"Поскольку Enforcer считают тебя одним из своих, у них нет ни малейшего желания тебя обыскивать.", null);
						
					} else if(hadSex) {
						return new Response(title,
								"Поскольку Enforcers уже переспали с тобой, им совершенно неинтересно тебя обыскивать.", null);
						
					} else if(foughtBefore) {
						return new Response(title,
								"Enforcers не хотят связываться с кем-то, кто состоит в родстве со Старшей лилин, поэтому им нет дела до твоего обыска...", null);
						
					} else if(bribed) {
						return new Response(title,
								"Так как ты подкупил Enforcers, им нет никакого интереса тебя обыскивать...", null);
						
					} else {
						return new Response(UtilText.parse(getEnforcerSubordinate(), "Обыскано (<span style='color:"+getEnforcerSubordinate().getFemininity().getColour().toWebHexString()+";'>[npc.surname]</span>)"),
								UtilText.parse(getEnforcerSubordinate(),
										((Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime()) && !isDemonRevealed())
											?"Подвергнись поверхностному обыску и попроси, чтобы тебя обыскал именно [npc.name]..."
											:"Скажи [npc.name], что, по твоему мнению, [npc.she] должен выполнять [npc.her] работу и провести тебе поверхностный обыск, чтобы убедиться, что ты не замышляешь ничего дурного...")
									+(playerContraband(ItemTag.CONTRABAND_HEAVY, true)
										?"<br/>[style.italicsTerrible(Часть твоих вещей будет конфискована, а тебя арестуют!)]"
										:(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)
											?"<br/>[style.italicsBad(Часть твоих вещей будет конфискована!)]"
											:"")),
								ENFORCER_ALLEYWAY_SEARCHED) {
							@Override
							public Colour getHighlightColour() {
								if(playerContraband(ItemTag.CONTRABAND_HEAVY, true)) {
									return PresetColour.GENERIC_TERRIBLE;
								}
								if(playerContraband(ItemTag.CONTRABAND_MEDIUM, true)) {
									return PresetColour.GENERIC_BAD;
								}
								return super.getHighlightColour();
							}
							@Override
							public void effects() {
								isLeaderSearching = false;
							}
						};
					}
					
				} else if(index==4) {
					if(Main.game.getPlayer().getRace()!=Race.DEMON
							&& Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON
							&& !isDemonRevealed()) {
						return new Response("Демоническое разоблачение",
								"Трансформироваться в демона на глазах у Enforcer...",
								ENFORCER_ALLEYWAY_DEMON_REVEAL);
						
					} else if(isDemonRevealed()) {
						if(hadSex) {
							return new Response(
									"Демоническое соблазнение",
									UtilText.parse(getEnforcers(), "Так как [npc.name] уже занимался сексом, [npc.sheIs] не желает проводить с тобой больше времени..."),
									null);
							
						} else if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())) {
							return new Response(
									"Демоническое соблазнение",
									UtilText.parse(getEnforcers(), "Так как [npc.name] не испытывает к тебе влечения, [npc.sheIs] не хочет заниматься с тобой сексом..."),
									null);
						} else {
							return new ResponseSex(
									"Демоническое соблазнение",
									UtilText.parse(getEnforcers(), "Использовать тот факт, что ты демон, чтобы соблазнить [npc.name]"
										+(getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
											?" и [npc2.name]."
											:"")),
									false, false,
									new SMGeneric(
											Util.newArrayListOfValues(
													Main.game.getPlayer()),
											Util.newArrayListOfValues(
													getEnforcerLeader(),
													getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
														?getEnforcerSubordinate()
														:null),
											null,
											null),
									AFTER_SEX_DEMONIC_SEDUCTION,
									UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_START_DEMONIC_SEDUCTION", getEnforcers())) {
								@Override
								public void effects() {
									hadSex = true;
								}
							};
						}
					}
					
				} else if(index==5) {
					return new ResponseCombat("Атаковать",
							"Поблизости никого нет, чтобы прийти им на помощь, так что если ты действительно захочешь, ты сможешь напасть на Enforcer...<br/>[style.italicsBad(Побиение Enforcer в бою приведет к тому, что они исчезнут из игры!)]",
							(NPC)getEnforcerLeader(),
							getEnforcers(),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Сейчас я покажу вам, кто здесь <i>на самом деле</i> главный!)] — заявляете вы, готовясь к атаке на Enforcer."),
									new Value<>(getEnforcerLeader(), UtilText.parse(getEnforcerLeader(), "[npc.speech(Ну, теперь тебе несдобровать!)] — кричит [npc.name].")),
									new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.speech(Это будет твоя последняя ошибка в жизни!)] — восклицает [npc.name]."))));
	
					
				} else if(index==6) {
					if(!wantsToSearch) {
						return new Response("Дать взятку ("+UtilText.formatAsMoneyUncoloured(BRIBE_AMOUNT, "охват")+")",
								"Enforcer не требуют обыска, так что нет нужды предлагать им взятку...",
								null);
					}
					if(Main.game.getPlayer().getMoney()<BRIBE_AMOUNT) {
						return new Response("Дать взятку ("+UtilText.formatAsMoneyUncoloured(BRIBE_AMOUNT, "охват")+")",
								"У вас недостаточно денег, чтобы дать взятку Enforcer...",
								null);
					}
					return new Response("Дать взятку ("+UtilText.formatAsMoney(BRIBE_AMOUNT, "охват")+")",
							"Спросить у Enforcer, нет ли какого-нибудь «официального штрафа», который можно оплатить вместо обыска.",
							ENFORCER_ALLEYWAY_BRIBE) {
						@Override
						public void effects() {
							bribed = true;
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-BRIBE_AMOUNT));
						}
					};
					
				} else if(index==7 && Main.game.isSillyModeEnabled()) {
					if(Main.game.getPlayer().getOccupation()==Occupation.TOURIST) {
						if(!wantsToSearch) {
							return new Response("Гражданин США",
									"Enforcer не требуют обыска, так что нет нужды злиться...",
									null);
						}
						return new Response("Гражданин США",
								"Ты — гражданин Америки, а значит, на этих Enforcer не распространяется их власть!",
								ENFORCER_ALLEYWAY_SOVEREIGN_CITIZEN);
					} else {
						if(!wantsToSearch) {
							return new Response("Суверенный гражданин",
									"Enforcer не требуют обыска, так что нет нужды злиться...",
									null);
						}
						return new Response("Суверенный гражданин",
								"Ты — суверенный гражданин, а значит, на этих Enforcer не распространяется их власть!",
								ENFORCER_ALLEYWAY_SOVEREIGN_CITIZEN);
					}
					
				} else if(index==10) {
					if(getCriminalInTile()==null) {
						return new Response("Доложить",
								"В этом районе не таится никаких преступников, так что тебе нечего докладывать Enforcer...",
								null);
						
					} else if(!getCriminalInTile().isAbleToBeEnslaved()) {
						return new Response(UtilText.parse(getCriminalInTile(), "Доложить ([npc.Name])"),
								UtilText.parse(getCriminalInTile(), "Так как [npc.name] не является разыскиваемым преступником, ты не можешь доложить о нем Enforcer..."),
								null);
						
					} else if(wantsToSearch) {
						return new Response(UtilText.parse(getCriminalInTile(), "Доложить ([npc.Name])"),
								"Enforcer-ов не будет волновать то, что ты хочешь сообщить, пока они тебя не обыщут...",
								null);
								
					} else {
						return new Response(UtilText.parse(getCriminalInTile(), "Доложить ([npc.Name])"),
								UtilText.parse(getCriminalInTile(), "Сказать Enforcer-ам, что где-то здесь затаился [npc.name].<br/>[style.italicsBad(Это навсегда удалит [npc.name] из игры!)]"),
								ENFORCER_ALLEYWAY_REPORT) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_NPC_REMOVAL;
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_SEARCHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			searched = true;
			playerSexType = getWantedSexType(isLeaderSearching?getEnforcerLeader():getEnforcerSubordinate(), Main.game.getPlayer());
			enforcerWantsPlayerSex = (isLeaderSearching?getEnforcerLeader():getEnforcerSubordinate()).isAttractedTo(Main.game.getPlayer());
			
			// Equipped contraband:
			for(AbstractClothing c : new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped())) {
				if(contrabandCheck(c.getItemTags())) {
					Main.game.getPlayer().forceUnequipClothingIntoVoid(getEnforcerLeader(), c);
					getEnforcerLeader().addClothing(c, false);
					clothingConfiscated.put(c, 1);
				}
			}
			for(int i=0; i<3; i++) {
				AbstractWeapon w = Main.game.getPlayer().getMainWeapon(i);
				if(w!=null && contrabandCheck(w.getItemTags())) {
					Main.game.getPlayer().unequipMainWeaponIntoVoid(i, false);
					getEnforcerLeader().addWeapon(w, false);
					weaponsConfiscated.putIfAbsent(w, 0);
					weaponsConfiscated.put(w, weaponsConfiscated.get(w)+1);
				}
				w = Main.game.getPlayer().getOffhandWeapon(i);
				if(w!=null && contrabandCheck(w.getItemTags())) {
					Main.game.getPlayer().unequipOffhandWeaponIntoVoid(i, false);
					getEnforcerLeader().addWeapon(w, false);
					weaponsConfiscated.putIfAbsent(w, 0);
					weaponsConfiscated.put(w, weaponsConfiscated.get(w)+1);
				}
			}
			
			// Contraband in inventory:
			for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayer().getAllWeaponsInInventory()).entrySet()) {
				AbstractWeapon weapon = entry.getKey();
				int count = entry.getValue();
				if(contrabandCheck(weapon.getItemTags())) {
					Main.game.getPlayer().removeWeapon(weapon, count);
					getEnforcerLeader().addWeapon(weapon, count, false, false);
					weaponsConfiscated.putIfAbsent(weapon, 0);
					weaponsConfiscated.put(weapon, weaponsConfiscated.get(weapon)+count);
				}
			}
			for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayer().getAllClothingInInventory()).entrySet()) {
				AbstractClothing clothing = entry.getKey();
				int count = entry.getValue();
				if(contrabandCheck(clothing.getItemTags())) {
					Main.game.getPlayer().removeClothing(clothing, count);
					getEnforcerLeader().addClothing(clothing, count, false, false);
					clothingConfiscated.putIfAbsent(clothing, 0);
					clothingConfiscated.put(clothing, clothingConfiscated.get(clothing)+count);
				}
			}
			for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayer().getAllItemsInInventory()).entrySet()) {
				AbstractItem item = entry.getKey();
				int count = entry.getValue();
				if(contrabandCheck(item.getItemTags())) {
					Main.game.getPlayer().removeItem(item, count);
					getEnforcerLeader().addItem(item, count, false, false);
					itemsConfiscated.putIfAbsent(item, 0);
					itemsConfiscated.put(item, itemsConfiscated.get(item)+count);
				}
			}
			
			contrabandFound = !weaponsConfiscated.isEmpty() || !clothingConfiscated.isEmpty() || !itemsConfiscated.isEmpty();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<GameCharacter> enforcers = getEnforcers();
			if(!isLeaderSearching) {
				enforcers = new ArrayList<>();
				enforcers.add(getEnforcerSubordinate());
				enforcers.add(getEnforcerLeader());
			}
			
			if(contrabandFound) {
				List<String> confiscationList = new ArrayList<>();
	
				for(Entry<AbstractWeapon, Integer> entry : weaponsConfiscated.entrySet()) {
					confiscationList.add("<b>"+entry.getValue()+"x "+entry.getKey().getDisplayName(true)+"</b>");
				}
				for(Entry<AbstractClothing, Integer> entry : clothingConfiscated.entrySet()) {
					confiscationList.add("<b>"+entry.getValue()+"x "+entry.getKey().getDisplayName(true)+"</b>");
				}
				for(Entry<AbstractItem, Integer> entry : itemsConfiscated.entrySet()) {
					confiscationList.add("<b>"+entry.getValue()+"x "+entry.getKey().getDisplayName(true)+"</b>");
				}
				
				UtilText.addSpecialParsingString(Util.stringsToStringList(confiscationList, false), true);
			}
			
			if(isLeaderSearching) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED", getEnforcers()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_SUBORDINATE", getEnforcers()));
			}
			
			if(heavyContrabandFound) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_HEAVY_CONTRABAND", enforcers));
				
			} else if(playerSexType!=null && enforcerWantsPlayerSex) {
				if(contrabandFound) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_CONTRABAND_DEMAND_STRIP", enforcers));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND", enforcers));
				}
				
				if(playerSexType.getPerformingSexArea()==SexAreaPenetration.PENIS) {
					if(playerSexType.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_PENIS_VAGINA", enforcers));
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_PENIS_ANUS", enforcers));
					}
				} else {
					if(playerSexType.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_FINGER_VAGINA", enforcers));
					} else {
						sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_FINGER_ANUS", enforcers));
					}
				}
				
			} else {
				if(contrabandFound) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_CONTRABAND", enforcers));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_DEMAND_NONE", enforcers));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter partner = isLeaderSearching?getEnforcerLeader():getEnforcerSubordinate();
			GameCharacter spectator = isLeaderSearching?getEnforcerSubordinate():getEnforcerLeader();
			
			if(heavyContrabandFound) {
				if (index == 1) {
					return new Response("Повиноваться", "Повиноваться Enforcer-ам и позволить им увести тебя в камеры...", AFTER_CONTRABAND_CELLS);
					
				} else if(index==2) {
					return new ResponseCombat("Сопротивляться",
							"Оказать сопротивление при аресте, из-за чего Enforcer-ы попытаются подавить тебя силой...<br/>[style.italicsBad(Победа над Enforcer-ами в бою приведет к тому, что они исчезнут из игры!)]",
							(NPC)getEnforcerLeader(),
							getEnforcers(),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(Я не сдамся без боя!)] — кричишь ты, готовясь защищаться от Enforcer-ов."),
									new Value<>(getEnforcerLeader(), UtilText.parse(getEnforcerLeader(), "[npc.speech(Похоже, придется действовать жестко!)] — кричит [npc.name].")),
									new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.speech(Это будет твоя последняя ошибка в жизни!)] — восклицает [npc.name]."))));
				}
				
				
			} else if((playerSexType!=null && enforcerWantsPlayerSex)) {
				if(index==1) {
					return getEnforcerSexResponse("Раздеться",
							UtilText.parse(partner,
								"Подчиниться «раздевающему обыску» [npc.namePos]..."
								+(playerSexType.getPerformingSexArea()==SexAreaPenetration.PENIS
									?(playerSexType.getTargetedSexArea()==SexAreaOrifice.VAGINA
										?"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет трахать твою киску!)]"
										:"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет трахать твою задницу!)]")
									:(playerSexType.getTargetedSexArea()==SexAreaOrifice.VAGINA
										?"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет ласкать твою киску пальцами!)]"
										:"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет ебать твой зад, одновременно дроча тебе на член!)]"))),
							partner,
							spectator,
							false,
							playerSexType,
							true,
							ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX);
					
				} else if(index==2) {
					if(!spectator.isAttractedTo(Main.game.getPlayer())) {
						return new Response("Раздеться (тройничок)", UtilText.parse(spectator, "[npc.Name] не испытывает к тебе влечения, так что вряд ли согласится на тройничок..."), null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Раздеться (тройничок)", UtilText.parse(spectator, "Так как ты не можешь разжать рот, [npc.name] не может участвовать в тройничке..."), null);
					}
					return getEnforcerSexResponse("Раздеться (тройничок)",
							UtilText.parse(partner, spectator,
								"Подчиниться «обыску» [npc.namePos] и заставить [npc2.name] присоединиться к веселью..."
								+(playerSexType.getPerformingSexArea()==SexAreaPenetration.PENIS
									?(playerSexType.getTargetedSexArea()==SexAreaOrifice.VAGINA
										?"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет трахать твою киску"
										:"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет ебать твой зад")
									:(playerSexType.getTargetedSexArea()==SexAreaOrifice.VAGINA
										?"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет пальцами ласкать твою киску,"
										:"<br/>[style.italicsSex(Все закончится тем, что [npc.name] будет дрочить тебя, одновременно лаская твою задницу,"))
								+(spectator.hasPenis()
										?", пока [npc2.name] заставит тебя сосать [npc2.her] член!)]"
										:", пока [npc2.name] заставит тебя отсосать [npc2.herHim]!)]")),
							partner,
							spectator,
							true,
							playerSexType,
							true,
							ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX);
					
				} else if(index==3) {
					if((Main.game.getPlayer().getRace()==Race.HUMAN || !Main.game.isDayTime())
							&& !isDemonRevealed()
							&& !isKind()
							&& partner.isWillingToRape()) {
						return new ResponseCombat("Отказаться",
								"Твой отказ раздеться перед Enforcer, несомненно, вызовет у них подозрения и приведет к тому, что они попытаются подчинить тебя силой...<br/>[style.italicsBad(Победа над Enforcer в бою приведет к тому, что они исчезнут из игры!)]",
								(NPC)getEnforcerLeader(),
								getEnforcers(),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), "[pc.speech(Прочь от меня!)] — кричишь ты, готовясь защищаться от Enforcer."),
										new Value<>(getEnforcerLeader(), UtilText.parse(getEnforcerLeader(), "[npc.speech(Я так и знал, что ты замышляешь что-то недоброе!)] — кричит [npc.name].")),
										new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.speech(Это будет твоя последняя ошибка в жизни!)] — восклицает [npc.name]."))));
						
					} else {
						return new Response("Отказаться",
								isKind() || !partner.isWillingToRape()
									?UtilText.parse(partner, "Хотя [npc.she] явно хочет воспользоваться тобой, [npc.name] не собирается заставлять тебя раздеваться...")
									:"Поскольку ты [pc.a_race], Enforcer не будут заставлять тебя раздеваться перед ними...",
								ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED);
					}
					
				} else if(index==4
						&& Main.game.getPlayer().getRace()!=Race.DEMON
						&& Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON
						&& !isDemonRevealed()) {
					return new Response("Демоническое разоблачение",
							"Превратись в демона на глазах у Enforcer, что наверняка заставит их прекратить свои требования...",
							ENFORCER_ALLEYWAY_DEMON_REVEAL);
				}
				
			} else {
				return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SEARCHED_STRIP_REFUSED", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription(){
			return "Теперь, когда тебя «осмотрели», Enforcer отступают и готовятся тебя отпустить.";
		}
		@Override
		public void applyPreParsingEffects() {
			hadSex = true;
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			List<GameCharacter> enforcers = getEnforcers();
			if(!isLeaderSearching) {
				enforcers = new ArrayList<>();
				enforcers.add(getEnforcerSubordinate());
				enforcers.add(getEnforcerLeader());
			}
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_AFTER_CAVITY_SEARCH_SEX", enforcers);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_DEMON_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			setDemonRevealed(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_DEMON_REVEAL", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_BRIBE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(BRIBE_AMOUNT), true);
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_BRIBE", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_SOVEREIGN_CITIZEN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_SOVERIEGN_CITIZEN", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сопротивляться",
						(Main.game.getPlayer().getOccupation()==Occupation.TOURIST
							?"Сопротивляйся аресту и покажи этим чертовым фашистским ублюдкам, что такое свобода!"
							:"Сопротивляйся аресту и отстаивай свои права как суверенный гражданин!")
						+ "<br/>[style.italicsBad(Если ты одолеешь Enforcer в бою, они навсегда исчезнут из игры!)]",
						(NPC)getEnforcerLeader(),
						getEnforcers(),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(),
										Main.game.getPlayer().getOccupation()==Occupation.TOURIST
											?"[pc.speech(США! США! США!)] — громко скандируешь ты, готовясь защищаться от этих коммунистических тварей."
											:"[pc.speech(Я — суверенный гражданин! Меня что, задерживают?! Я не давал согласия!)] — визжишь ты, готовясь дать отпор этим тиранам."),
								new Value<>(getEnforcerLeader(), UtilText.parse(getEnforcerLeader(), "[npc.speech(Ну, теперь тебе несдобровать!)] — кричит [npc.name].")),
								new Value<>(getEnforcerSubordinate(), UtilText.parse(getEnforcerSubordinate(), "[npc.speech(Это будет твоя последняя ошибка в жизни!)] — восклицает [npc.name]."))));
				
			} else if(index==2) {
				return new Response(UtilText.parse(getEnforcerLeader(), "Обыскано (<span style='color:"+getEnforcerLeader().getFemininity().getColour().toWebHexString()+";'>[npc.surname]</span>)"),
						UtilText.parse(getEnforcerLeader(), "Перестать спорить с Enforcer и позволить [npc.name] обыскать тебя..."),
						ENFORCER_ALLEYWAY_SEARCHED) {
					@Override
					public void effects() {
						isLeaderSearching = true;
					}
				};
				
			} else if(index==3) {
				return new Response(UtilText.parse(getEnforcerSubordinate(), "Обыскано (<span style='color:"+getEnforcerSubordinate().getFemininity().getColour().toWebHexString()+";'>[npc.surname]</span>)"),
						UtilText.parse(getEnforcerSubordinate(), "Перестать спорить с Enforcer и позволить [npc.name] обыскать тебя..."),
						ENFORCER_ALLEYWAY_SEARCHED) {
					@Override
					public void effects() {
						isLeaderSearching = true;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_REPORT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT", getEnforcersAndCriminal());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						Main.game.getPlayer().isHasSlaverLicense()
							?"Отклонить"
							:"Продолжить",
						UtilText.parse(getCriminalInTile(),
							Main.game.getPlayer().isHasSlaverLicense()
								?"Заявить Enforcer, что тебе не интересно брать [npc.name] в рабство, и оставить их наедине с их задачей по порабощению [npc.herHim]."
								:"Оставить Enforcer выслеживать и порабощать [npc.name] и продолжить свой путь..."),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_CONTINUE", getEnforcersAndCriminal()));
						banishCriminal();
						banishEnforcers(false);
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Помочь",
							UtilText.parse(getCriminalInTile(), "Так как у тебя нет лицензии работорговца, от помощи Enforcer в поисках и порабощении [npc.name] тебе будет мало что перепадет..."),
							null);
				}
				return new Response("Помочь",
						UtilText.parse(getCriminalInTile(), "Помочь Enforcer выследить и поработить [npc.name], чтобы в награду получить преступницу [npc.race]..."),
						ENFORCER_ALLEYWAY_REPORT_HELP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP", getEnforcersAndCriminal()));
						if(getCriminalInTile().isRelatedTo(Main.game.getPlayer())) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_CHILD", getEnforcersAndCriminal()));
							Main.game.getPlayer().incrementKarma(-75);
						} else if(!getCriminalInTile().getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_ONE_FRIENDLY)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_FRIEND", getEnforcersAndCriminal()));
							Main.game.getPlayer().incrementKarma(-25);
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_CRIMINAL", getEnforcersAndCriminal()));
						}
						Main.game.getTextEndStringBuilder().append(getCriminalInTile().setAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_REPORT_HELP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Завладеть собственностью",
						UtilText.parse(getCriminalInTile(), "Завладеть [npc.name]..."),
						ENFORCER_ALLEYWAY_REPORT_HELP_REWARD) {
					@Override
					public void effects() {
						AbstractClothing neckClothing = getCriminalInTile().getClothingInSlot(InventorySlot.NECK);
						if(neckClothing!=null) {
							getCriminalInTile().forceUnequipClothingIntoVoid(getEnforcerLeader(), neckClothing);
							getCriminalInTile().addClothing(neckClothing, false);
						}
						AbstractClothing collar = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), PresetColour.CLOTHING_STEEL, false);
						if(!getCriminalInTile().isAbleToEquip(collar, true, Main.game.getPlayer())) {
							for(AbstractClothing c : new ArrayList<>(getCriminalInTile().getClothingCurrentlyEquipped())) {
								c.setSealed(false);
							}
						}
						List<GameCharacter> parsingCharacters = new ArrayList<>(getEnforcersAndCriminal());
						UtilText.addSpecialParsingString(
								getCriminalInTile().equipClothingFromNowhere(collar, true, Main.game.getPlayer()),
								true);
						Main.game.getPlayer().addSlave((NPC) getCriminalInTile());
						getCriminalInTile().applyEnslavementEffects(Main.game.getPlayer());
						getCriminalInTile().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "ENFORCER_ALLEYWAY_REPORT_HELP_REWARD", parsingCharacters));
						banishEnforcers(false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_ALLEYWAY_REPORT_HELP_REWARD = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Поскольку Enforcer ушли составлять отчет об этом инциденте, ты можешь спокойно идти дальше...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY", getEnforcers());
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Взаимодействие";
			} else if(index==1) {
				return "Инвентарь";
			} else if(index==2) {
				return "Трансформации";
			}
 			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if (index == 1) {
					return new Response("Уйти",
							"Оставить Enforcer приходить в себя и продолжить свой путь...<br/>[style.italicsBad(Это навсегда уберет Enforcer из игры!)]",
							Main.game.getDefaultDialogue(false)) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_CONTINUE", getEnforcers()));
							banishEnforcers(true);
						}
					};
					
				} else if (index == 2) {
					if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
						return new ResponseSex(
								UtilText.parse(getEnforcerLeader(), "Изнасиловать ([npc.Surname])"),
								UtilText.parse(getEnforcerLeader(), "Хотя [npc.name] и не хочет заниматься с тобой сексом, у [npc.she] на самом деле нет права голоса в этом вопросе..."),
								Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getEnforcerLeader()),
										null,
										Util.newArrayListOfValues(getEnforcerSubordinate())),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_RAPE_LEADER", getEnforcers()));
						
					} else {
						return new ResponseSex(
								UtilText.parse(getEnforcerLeader(), "Секс ([npc.Surname])"),
								UtilText.parse(getEnforcers(), "Если [npc.nameIs] жаждет секса, то ты с радостью отдашь его [npc.herHim]!"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getEnforcerLeader()),
										null,
										Util.newArrayListOfValues(getEnforcerSubordinate())),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX_LEADER", getEnforcers()));
					}
					
				} else if (index == 3) {
					if(!getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
						return new ResponseSex(
								UtilText.parse(getEnforcerSubordinate(), "Изнасиловать ([npc.Surname])"),
								UtilText.parse(getEnforcerSubordinate(), "Хотя [npc.name] и не хочет заниматься с тобой сексом, у [npc.she] на самом деле нет права голоса в этом вопросе..."),
								Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getEnforcerSubordinate()),
										null,
										Util.newArrayListOfValues(getEnforcerLeader())),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_RAPE_SUBORDINATE", getEnforcers()));
						
					} else {
						return new ResponseSex(
								UtilText.parse(getEnforcerSubordinate(), "Секс ([npc.Surname])"),
								UtilText.parse(getEnforcerSubordinate(), "Если [npc.nameIs] жаждет секса, то ты с радостью отдашь его [npc.herHim]!"),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getEnforcerSubordinate()),
										null,
										Util.newArrayListOfValues(getEnforcerLeader())),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX_SUBORDINATE", getEnforcers()));
					}
					
				} else if (index == 4) {
					if((!getEnforcerLeader().isAttractedTo(Main.game.getPlayer()) || !getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())) && Main.game.isNonConEnabled()) {
						return new ResponseSex(
								"Тройничок (изнасилование)",
								"Хотя они и не хотят заниматься с тобой сексом, у этих Enforcer почти нет выбора...",
								Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getEnforcerLeader(), getEnforcerSubordinate()),
										null,
										null),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_RAPE_THREESOME", getEnforcers()));
						
					} else {
						return new ResponseSex(
								"Трио",
								"Если эти двое Enforcer жаждут секса, то ты с радостью отдашь его им!",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getEnforcerLeader(), getEnforcerSubordinate()),
										null,
										null),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX_THREESOME", getEnforcers()));
					}
					
				} else if (index == 5) {
					if(!getEnforcerLeader().isAttractedTo(Main.game.getPlayer()) && !getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Подчиниться",
								"Ты не можешь подчиниться Enforcer, так как ни одного из них не интересует секс с тобой!",
								null);
					} else {
						return new ResponseSex("Подчиниться",
								"Ты не совсем понимаешь, что делать... Возможно, лучше позволить Enforcer самим решить, что будет дальше?"
								+ UtilText.parse(getEnforcers(),
									(getEnforcerLeader().isAttractedTo(Main.game.getPlayer()) && getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
										?"<br/>[style.italicsSex(Это приведет к тому, что и [npc.name], и [npc2.name] будут доминантно трахать тебя!)]"
										:(getEnforcerLeader().isAttractedTo(Main.game.getPlayer())
											?"<br/>[style.italicsSex(Это приведет к тому, что [npc.name] будет просто доминантно трахать тебя!)]"
											:"<br/>[style.italicsSex(Это приведет к тому, что [npc2.name] будет просто доминантно трахать тебя!)]"))),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(
												getEnforcerLeader().isAttractedTo(Main.game.getPlayer())
													?getEnforcerLeader()
													:null,
												getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
													?getEnforcerSubordinate()
													:null),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(
												!getEnforcerLeader().isAttractedTo(Main.game.getPlayer())
													?getEnforcerLeader()
													:null,
												!getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
													?getEnforcerSubordinate()
													:null),
										null),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getEnforcers()));
					}
				}
				
				return null;
			}
			
			if(responseTab == 1) {
				for(int i=1; i<=getEnforcers().size(); i++) {
					if(index==i) {
						NPC enforcer = (NPC) getEnforcers().get(i-1);
						return new ResponseEffectsOnly(UtilText.parse(enforcer, "[npc.Name]"),
								UtilText.parse(enforcer, "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]...")) {
							@Override
							public void effects() {
								Main.mainController.openInventory(enforcer, InventoryInteraction.FULL_MANAGEMENT);
								Main.game.setResponseTab(0);
							}
						};
					}
				}
				
			} else if(responseTab == 2) {
				for(int i=1; i<=getEnforcers().size(); i++) {
					if(index==i) {
						NPC enforcer = (NPC) getEnforcers().get(i-1);
						if(!enforcer.isAbleToSelfTransform()) {
							return new Response(UtilText.parse(enforcer, "[npc.Name]"), UtilText.parse(enforcer, "[npc.Name] не может трансформироваться самостоятельно..."), null);
							
						} else {
							return new Response(UtilText.parse(enforcer, "[npc.Name]"),
									UtilText.parse(enforcer, "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(enforcer);
								}
							};
						}
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			searched = true;
			isLeaderSearching = false;
			playerSexType = getWantedSexType(isLeaderSearching?getEnforcerLeader():getEnforcerSubordinate(), Main.game.getPlayer());
			enforcerWantsPlayerSex = (isLeaderSearching?getEnforcerLeader():getEnforcerSubordinate()).isAttractedTo(Main.game.getPlayer());
			
			// Equipped contraband:
			for(AbstractClothing c : new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped())) {
				if(contrabandCheck(c.getItemTags())) {
					Main.game.getPlayer().forceUnequipClothingIntoVoid(getEnforcerLeader(), c);
					getEnforcerLeader().addClothing(c, false);
					clothingConfiscated.put(c, 1);
				}
			}
			for(int i=0; i<3; i++) {
				AbstractWeapon w = Main.game.getPlayer().getMainWeapon(i);
				if(w!=null && contrabandCheck(w.getItemTags())) {
					Main.game.getPlayer().unequipMainWeaponIntoVoid(i, false);
					getEnforcerLeader().addWeapon(w, false);
					weaponsConfiscated.putIfAbsent(w, 0);
					weaponsConfiscated.put(w, weaponsConfiscated.get(w)+1);
				}
				w = Main.game.getPlayer().getOffhandWeapon(i);
				if(w!=null && contrabandCheck(w.getItemTags())) {
					Main.game.getPlayer().unequipOffhandWeaponIntoVoid(i, false);
					getEnforcerLeader().addWeapon(w, false);
					weaponsConfiscated.putIfAbsent(w, 0);
					weaponsConfiscated.put(w, weaponsConfiscated.get(w)+1);
				}
			}
			
			// Contraband in inventory:
			for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayer().getAllWeaponsInInventory()).entrySet()) {
				AbstractWeapon weapon = entry.getKey();
				int count = entry.getValue();
				if(contrabandCheck(weapon.getItemTags())) {
					Main.game.getPlayer().removeWeapon(weapon, count);
					getEnforcerLeader().addWeapon(weapon, count, false, false);
					weaponsConfiscated.putIfAbsent(weapon, 0);
					weaponsConfiscated.put(weapon, weaponsConfiscated.get(weapon)+count);
				}
			}
			for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayer().getAllClothingInInventory()).entrySet()) {
				AbstractClothing clothing = entry.getKey();
				int count = entry.getValue();
				if(contrabandCheck(clothing.getItemTags())) {
					Main.game.getPlayer().removeClothing(clothing, count);
					getEnforcerLeader().addClothing(clothing, count, false, false);
					clothingConfiscated.putIfAbsent(clothing, 0);
					clothingConfiscated.put(clothing, clothingConfiscated.get(clothing)+count);
				}
			}
			for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayer().getAllItemsInInventory()).entrySet()) {
				AbstractItem item = entry.getKey();
				int count = entry.getValue();
				if(contrabandCheck(item.getItemTags())) {
					Main.game.getPlayer().removeItem(item, count);
					getEnforcerLeader().addItem(item, count, false, false);
					itemsConfiscated.putIfAbsent(item, 0);
					itemsConfiscated.put(item, itemsConfiscated.get(item)+count);
				}
			}
			
			contrabandFound = !weaponsConfiscated.isEmpty() || !clothingConfiscated.isEmpty() || !itemsConfiscated.isEmpty();
		}
		@Override
		public String getContent() {
//			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_DEFEAT", getEnforcers());

			StringBuilder sb = new StringBuilder();
			
			if(contrabandFound) {
				List<String> confiscationList = new ArrayList<>();
	
				for(Entry<AbstractWeapon, Integer> entry : weaponsConfiscated.entrySet()) {
					confiscationList.add("<b>"+entry.getValue()+"x "+entry.getKey().getDisplayName(true)+"</b>");
				}
				for(Entry<AbstractClothing, Integer> entry : clothingConfiscated.entrySet()) {
					confiscationList.add("<b>"+entry.getValue()+"x "+entry.getKey().getDisplayName(true)+"</b>");
				}
				for(Entry<AbstractItem, Integer> entry : itemsConfiscated.entrySet()) {
					confiscationList.add("<b>"+entry.getValue()+"x "+entry.getKey().getDisplayName(true)+"</b>");
				}
				
				UtilText.addSpecialParsingString(Util.stringsToStringList(confiscationList, false), true);
			}
			
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_DEFEAT_SEARCHED", getEnforcers()));
			
			if(heavyContrabandFound || contrabandFound) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_DEFEAT_SEARCHED_CONTRABAND", getEnforcers()));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_DEFEAT_SEARCHED_NO_CONTRABAND", getEnforcers()));
			}
			
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_DEFEAT_END", getEnforcers()));
			
			return sb.toString();
		
			
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> enforcersWantingSex = Util.newArrayListOfValues(
					getEnforcerLeader().isAttractedTo(Main.game.getPlayer())
						?getEnforcerLeader()
						:null,
					getEnforcerSubordinate().isAttractedTo(Main.game.getPlayer())
						?getEnforcerSubordinate()
						:null);
			List<GameCharacter> enforcersSpectating = new ArrayList<>(getEnforcers());
			enforcersSpectating.removeIf(e -> enforcersWantingSex.contains(e));
			
			if(!enforcersWantingSex.isEmpty()) {
				if (index == 1) {
					return new ResponseSex("Секс",
							UtilText.parse(enforcersWantingSex,
									enforcersWantingSex.get(0).isWillingToRape()
										?(enforcersWantingSex.size()==2
											?"[npc.Name] и [npc2.name] силой берут тебя..."
											:"[npc.Name] навязывает вам [npc.herself]...")
										:(enforcersWantingSex.size()==2
											?"Смирись перед [npc.name] и [npc2.name] и позволь им трахать тебя."
											:"Смирись перед [npc.name] и позволь [npc.herHim] трахать тебя.")),
							false, false,
							new SMGeneric(
									enforcersWantingSex,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									enforcersSpectating,
									null),
							AFTER_DEFEAT_SEX,
							UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX", getEnforcers()));
					
				} else if (index == 2) {
					return new ResponseSex("Жажда секса",
							UtilText.parse(enforcersWantingSex,
								enforcersWantingSex.get(0).isWillingToRape()
									?(enforcersWantingSex.size()==2
										?"С жадностью подстрекай [npc.name] и [npc2.name] силой брать тебя..."
										:"С жадностью подстрекай [npc.name] навязать тебе [npc.herself]...")
									:(enforcersWantingSex.size()==2
										?"С жадностью отдайся на милость [npc.name] и [npc2.name] и позволь им трахать тебя."
										:"С жадностью отдайся на милость [npc.name] и позволь [npc.herHim] трахать тебя.")),
							false, false,
							new SMGeneric(
									enforcersWantingSex,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									enforcersSpectating,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_DEFEAT_SEX,
							UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_EAGER", getEnforcers()));
					
				} else if (index == 3 && Main.game.isNonConEnabled() && enforcersWantingSex.get(0).isWillingToRape()) {
					return new ResponseSex("Сопротивляться сексу",
							UtilText.parse(enforcersWantingSex,
								(enforcersWantingSex.size()==2
										?"Попытаться сопротивляться, когда [npc.name] и [npc2.name] навязывают тебе себя..."
										:"Попытаться сопротивляться, когда [npc.name] навязывает тебе [npc.herself]...")),
							false, false,
							new SMGeneric(
									enforcersWantingSex,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									enforcersSpectating,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_DEFEAT_SEX,
							UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "START_DEFEATED_SEX_RESIST", getEnforcers()));
					
				} else if (index == 4 && !enforcersWantingSex.get(0).isWillingToRape()) {
					return new Response("Отказаться",
							UtilText.parse(enforcersWantingSex, 
								(enforcersWantingSex.size()==2
									?"Отказаться заниматься сексом с [npc.name] и [npc2.name]."
									:"Отказаться заниматься сексом с [npc.name].")),
							AFTER_COMBAT_DEFEAT_SEX_REFUSED);
				}
				
			} else {
				if(((NPC)getEnforcerLeader()).hasFlag(NPCFlagValue.knowsPlayerDemon)
						|| getEnforcerLeader().getFoughtPlayerCount()>1) { // If demon, or know that Lilaya will bail you out, they leave you behind.
					if (index == 1) {
						return new Response("Продолжить", "Теперь, когда Enforcer ушли, ты можешь прийти в себя и продолжить свой путь...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								banishEnforcers(false);
							}
						};
					}
					
				} else {
					if (index == 1) {
						return new Response("Утащили", "Enforcer утаскивают тебя в камеры...", AFTER_DEFEAT_CELLS);
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT_SEX_REFUSED = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_COMBAT_DEFEAT_SEX_REFUSED", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((NPC)getEnforcerLeader()).hasFlag(NPCFlagValue.knowsPlayerDemon)
					|| getEnforcerLeader().getFoughtPlayerCount()>1) { // If demon, or know that Lilaya will bail you out, they leave you behind.
				if (index == 1) {
					return new Response("Продолжить", "Теперь, когда Enforcer ушли, ты можешь прийти в себя и продолжить свой путь...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishEnforcers(false);
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("Утащили", "Enforcer утаскивают тебя в камеры...", AFTER_DEFEAT_CELLS);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEMONIC_SEDUCTION = new DialogueNode("Отступить", "", true) {
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь повеселился, можешь отступить и дать Enforcer прийти в себя.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_SEX_DEMONIC_SEDUCTION", getEnforcers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENFORCER_ALLEYWAY_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь повеселился, можешь отступить и дать Enforcer оправиться от поражения.";
		}
		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants(false).contains(getEnforcerLeader())) {
				return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_SEX_VICTORY", getEnforcers());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_SEX_VICTORY", Util.newArrayListOfValues(getEnforcerSubordinate(), getEnforcerLeader()));
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Уйти",
							"Оставить Enforcer позади и продолжить свой путь.<br/>[style.italicsBad(Это навсегда уберет Enforcer из игры!)]",
							Main.game.getDefaultDialogue(false)) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							banishEnforcers(true);
						}
					};
				}
				
			} else if(responseTab==1) {
				return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_SEX = new DialogueNode("Свалиться", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription(){
			return "Вы совершенно измотаны доминирующим напором [npc.namePos] и нуждаетесь в отдыхе, чтобы прийти в себя.";
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.sex.getAllParticipants(false).contains(getEnforcerLeader())) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_SEX", getEnforcers()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_SEX", Util.newArrayListOfValues(getEnforcerSubordinate(), getEnforcerLeader())));
			}
			
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_SEX_CONTINUE", getEnforcers()));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((NPC)getEnforcerLeader()).hasFlag(NPCFlagValue.knowsPlayerDemon)
					|| getEnforcerLeader().getFoughtPlayerCount()>1) { // If demon, or know that Lilaya will bail you out, they leave you behind.
				if (index == 1) {
					return new Response("Продолжить", "Теперь, когда Enforcer ушли, ты можешь прийти в себя и продолжить свой путь...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishEnforcers(false);
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("Утащили", "Enforcer утаскивают тебя в камеры...", AFTER_DEFEAT_CELLS);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_CELLS = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_CELLS", getEnforcers()));
			banishEnforcers(false);
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELL);
		}
		@Override
		public int getSecondsPassed() {
			return 25*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Погоди...", "Тебе остается только одно — ждать в своей камере...", AFTER_DEFEAT_CELLS_WAITING);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_CONTRABAND_CELLS = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_CONTRABAND_CELLS", getEnforcers()));
			banishEnforcers(false);
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELL);
		}
		@Override
		public int getSecondsPassed() {
			return 25*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Погоди...", "Тебе остается только одно — ждать в своей камере...", AFTER_DEFEAT_CELLS_WAITING);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_CELLS_WAITING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
		}
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/enforcerAlleyway", "AFTER_DEFEAT_CELLS_WAITING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Освобождение", "Теперь ты снова волен продолжать свой путь!", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false));
			}
			return null;
		}
	};
	
}
