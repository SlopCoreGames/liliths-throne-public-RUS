package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.misc.BasicSlave;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMScarlettShopOral;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.9.1
 * @author Innoxia
 */
public class ScarlettsShop {
	
	private static void spawnDeliveryNpcs() {
		Main.game.getNpc(Natalya.class).setLocation(Main.game.getPlayer(), false);
		
		String[] names = new String[] {"послушная кентаврида", "верная кентаврида"};
		for(int i=0; i<2; i++) {
			NPC npc = new GenericSexualPartner(Gender.M_P_MALE, WorldType.EMPTY, Main.game.getWorlds().get(WorldType.EMPTY).getCell(PlaceType.GENERIC_HOLDING_CELL).getLocation(), false);
			npc.setBody(Gender.M_P_MALE, Subspecies.CENTAUR, Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(Subspecies.CENTAUR), Gender.M_P_MALE, Subspecies.CENTAUR),false);
			
			npc.unequipAllClothing(npc, true, true);
			npc.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), PresetColour.CLOTHING_GOLD, false), true, npc);
			
			npc.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			npc.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
			npc.setHeight(230+Util.random.nextInt(11));
			
			npc.setTesticleSize(TesticleSize.FIVE_MASSIVE);
			npc.setPenisCumStorage(750);
			npc.setPenisCumExpulsion(80);
			
			npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			npc.clearFetishes();
			npc.clearFetishDesires();
			
			npc.addFetish(Fetish.FETISH_ANAL_GIVING);
			
			npc.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.ZERO_HATE);
			npc.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.ZERO_HATE);
			
			npc.setAssVirgin(false);
			npc.setPenisVirgin(false);
			npc.setFaceVirgin(false);
			
			npc.setGenericName(names[i]);
			
			npc.setLocation(Main.game.getPlayer(), false);
			try {
				Main.game.addNPC(npc, false);
				Main.game.setActiveNPC(npc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static List<GameCharacter> getDeliveryNpcs() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->npc.isUnique());
		return list;
	}
	
	private static int getScarlettPrice() {
		return (int) Units.roundTo(Main.game.getNpc(Scarlett.class).getValueAsSlave(false) * 2, 1000);
	}
	
	private static SexManagerInterface getScarlettSleepoverSexManager(AbstractSexPosition position, SexSlot scarlettSlot, SexSlot playerSlot, SexType scarlettPreference, Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				return SexControl.ONGOING_ONLY; // So Scarlett doesn't start anything else.
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return true;
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
					return scarlettPreference;
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
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getPlayer(), new HashMap<>());
					map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Scarlett.class), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				return super.getStartingWetAreas();
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettSleepoverSex)) { // If this is the morning oral scene, Scarlett stops after cumming.
					return Main.sex.isSatisfiedFromOrgasms(partner, true);
				}
				return Main.sex.isSatisfiedFromOrgasms(partner, true) && (Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true) || Main.sex.getNumberOfOrgasms(partner)>=3);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {// pull out if Scarlett receiving blowjob
						return OrgasmBehaviour.PULL_OUT;
					}
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
				if(!character.isPlayer()) {
					if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {// pull out onto face if Scarlett receiving blowjob
						return OrgasmCumTarget.FACE;
					}
				}
				return null;
			}
		};
	}
	
	private static SexManagerInterface getScarlettCafeSexManager(AbstractSexPosition position, SexSlot scarlettSlot, SexSlot playerSlot, SexType scarlettPreference, Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				return SexControl.ONGOING_ONLY; // So Scarlett doesn't start anything else.
			}
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
					return scarlettPreference;
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
				return Main.sex.isSatisfiedFromOrgasms(partner, true);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
		};
	}

	private static GameCharacter getSlaveForCustomisation() {
		if(Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters().isEmpty()) {
			return null;
		}
		return Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters().get(0);
	}
	
	private static int getSlaveValue(boolean asSlime) {
		if(getSlaveForCustomisation()==null) {
			return 0;
		}
		
		int value = 25_000;
		for(Entry<AbstractRace, Integer> entry : getSlaveForCustomisation().getBody().getRaceWeightMap().entrySet()) { // Add value for non-human parts:
			if(entry.getKey()!=Race.HUMAN) {
				value += Math.min(5_000, 1_000*entry.getValue());
			}
		}
		
		if(asSlime) {
			value += 5_000;
		}
		return value;
	}
	
	public static boolean isSlaveCustomisationMenu() {
		return Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS;
	}
	
	private static void generateStartingSlave(Gender gender) {
		NPC slave = new BasicSlave(gender, false);
		
		try {
			Main.game.addNPC(slave, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.getNpc(Helena.class).addSlave(slave);
		
		BodyChanging.setTarget(slave);
		
		SuccubisSecrets.initCoveringsMap(slave);
	}
	
	private static boolean isMule() {
		return Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) || Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA) || Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD);
	}
	
	public static final DialogueNode SCARLETTS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR_CLOSED");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Войти", "Лавка Scarlett сейчас закрыта и откроется в шесть утра. Вам придется вернуться чуть позже.", null);
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
					return new Response("Войти", "Прежде чем снова войти в лавку Scarlett, вам стоит найти Helena.", null);
					
				} else {
					return new Response("Войти", "Войти в лавку.", SCARLETTS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETTS_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_INTRO");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
					return new Response("Спросить об Arthur", "Спросить Scarlett, нет ли у неё на продажу раба по имени Arthur.", SCARLETT_IS_A_BITCH);

				} else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("Уйти", "Выйти из лавки.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}

		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_BITCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Согласиться", "Согласиться помочь Scarlett.", SCARLETT_IS_A_SUPER_BITCH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_SUPER_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_SUPER_BITCH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_HELENA_RETURNS");
				
			} else if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_ROMANCE_FAILED");
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_GONE_HOME"));
					
				} else if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_CLOSED"));
				}
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)
					&& Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_8_FINISH)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_GONE_HOME"));
					
				} else if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_CLOSED"));
				}
				
			} else {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_OPEN"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_CLOSED"));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR"));
				}
				
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_CLOSED"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return null;
			}
			
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Войти", "Войти в лавку.", HELENAS_SHOP) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
							Main.game.getNpc(Scarlett.class).resetInventory(true);
							AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
							collar.setSealed(true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
						}
					};
					
				} else if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					return new Response("Войти",
							"Лавка Helena сейчас закрыта и откроется в девять утра. Вам придется зайти после этого времени.",
							null);
				}
				return new Response("Войти",
						"Войти в лавку.",
						Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)
							?ROMANCE_SHOP_CORE
							:HELENAS_SHOP);

			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_INTRODUCTION");
					
			} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_OFFER_SCARLETT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP", Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return new Response("Уйти", "Попрощаться с Helena и выйти из лавки.", HELENAS_SHOP_EXTERIOR);
				
			} else if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Предложить купить", "Предложить купить Scarlett у Helena.", HELENAS_SHOP_SCARLETT_FOR_SALE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY));
							if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.punishedByHelena)) {
								Main.game.getDialogueFlags().scarlettPrice = 10000;
							}
						}
					};
					
				} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
					if(!Main.game.getPlayer().isHasSlaverLicense()) {
						return new Response("Купить Scarlett (" + UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "охват")+")",
								"Прежде чем вы сможете купить Scarlett, вам нужно получить лицензию работорговца в Администрации Рабства!", null);
						
					} else if(Main.game.getPlayer().getMoney() < Main.game.getDialogueFlags().scarlettPrice) {
						return new Response("Купить Scarlett (" +UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "охват")+")", "У вас недостаточно денег, чтобы купить Scarlett.", null);
						
					} else {
						return new Response("Купить Scarlett ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().scarlettPrice, "охват")+")",
								"Купить Scarlett за "+Main.game.getDialogueFlags().scarlettPrice+" огни.", HELENAS_SHOP_BUYING_SCARLETT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().scarlettPrice));
								
								AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
								if (ballgag != null) {
									ballgag.setSealed(false);
									Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
								}
								
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
							}
						};
					}
					
				} else {
					return new Response("Управляющая рабами", "Войти в меню управления рабами.", HELENAS_SHOP) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(HELENAS_SHOP, Main.game.getNpc(Helena.class));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
				if(index==2) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)) {
						return new Response("Дела",
								"Спросить у Helena, почему она решила остаться здесь и управлять этим бизнесом сама.<br/>[style.italicsQuestRomance(Это запустит романтический квест Helena!)]",
								ROMANCE_BUSINESS) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_FOR_SALE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_FOR_SALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().scarlettPrice), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Успокоить её", "Мягко успокоить Scarlett, чтобы она пришла в себя.", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_GENTLE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Накричать на неё", "Наорать на Scarlett и напомнить ей, что теперь она твоя собственность.", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SHOUT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(5));
					}
				};
				
			} else if (index == 3) {
				return new Response("Дать ей пощечину", "Дать Scarlett пощечину и напомнить ей, что теперь она твоя собственность.", HELENAS_SHOP_SCARLETT_PURCHASED,
						Util.newArrayListOfValues(Fetish.FETISH_SADIST),
						CorruptionLevel.FOUR_LUSTFUL,
						null, null, null) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SLAP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -15));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(10));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_PURCHASED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Оставить её", "Вы решаете оставить Scarlett своей рабыней.", HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
					}
				};

			} else if (index == 2) {
				return new Response("Освободить её", "Вы решаете даровать Scarlett свободу.", HELENAS_SHOP_BUYING_SCARLETT_FREE_HER) {
					@Override
					public void effects() {
						
						AbstractClothing collar = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK);
						if(collar!=null) {
							collar.setSealed(false);
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(collar, true, Main.game.getNpc(Helena.class));
						}
						
						((Scarlett) Main.game.getNpc(Scarlett.class)).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.REMOVE_SEALS, EquipClothingSetting.ADD_ACCESSORIES));
						
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
						Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Выйти из лавки.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_FREE_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_FREE_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Выйти из лавки.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	
	// Helena romance quest:
	
	public static final DialogueNode ROMANCE_SHOP_CORE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				// If Scarlett has a saved inventory, then give items to player:
				CharacterInventory scarlettSavedInventory = Main.game.getSavedInventories().get(Main.game.getNpc(Scarlett.class).getId());
				if(scarlettSavedInventory!=null) {
					for(AbstractWeapon weapon : scarlettSavedInventory.getMainWeaponArray()) {
						if(weapon!=null) {
							Main.game.getPlayer().addWeapon(weapon, 1, false, true);
						}
					}
					for(AbstractWeapon weapon : scarlettSavedInventory.getOffhandWeaponArray()) {
						if(weapon!=null) {
							Main.game.getPlayer().addWeapon(weapon, 1, false, true);
						}
					}
					for(AbstractClothing clothing : scarlettSavedInventory.getClothingCurrentlyEquipped()) {
						Main.game.getPlayer().addClothing(clothing, 1, false, true);
					}
					for(Entry<AbstractWeapon, Integer> entry : scarlettSavedInventory.getAllWeaponsInInventory().entrySet()) {
						Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), false, true);
					}
					for(Entry<AbstractClothing, Integer> entry : scarlettSavedInventory.getAllClothingInInventory().entrySet()) {
						Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), false, true);
					}
					for(Entry<AbstractItem, Integer> entry : scarlettSavedInventory.getAllItemsInInventory().entrySet()) {
						Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), false, true);
					}
					Main.game.getPlayer().incrementMoney(scarlettSavedInventory.getMoney());
				}
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP")
						+ UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_1");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2"));
				if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
						|| Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_PAINT"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_NO_PAINT"));
				}
				return sb.toString();
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_EXTERIOR");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_SIGN");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
					if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT_OWNED_PRESENT"); // Helena demands you sell Scarlett to her
						
					} else {
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT_OWNED_NOT_PRESENT"); // Helena demands you fetch Scarlett
					}
					
				} else {
					if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT"); // Helena tells you to come back tomorrow/Monday
						
					} else {
						if(Main.game.getNpc(Scarlett.class).isSlave()) {
							return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_NO_SCARLETT_SLAVE"); // Helena tells you to come back once Scarlett has been returned
							
						} else {
							return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_NO_SCARLETT"); // Helena tells you to come back once Scarlett is here
						}
					}
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5"));
				CharacterInventory scarlettSavedInventory = Main.game.getSavedInventories().get(Main.game.getNpc(Scarlett.class).getId());
				if(scarlettSavedInventory!=null) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_ITEMS_RESTORED"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_NO_ITEMS"));
				}
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_END"));
				
				return sb.toString();
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_6_ADVERTISING) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_6"); // Helena asks you if you've put up the posters yet
			}
			
			
			// Romance quest completed:
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE"));
			
			if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY && Main.game.getHourOfDay()>=17) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_DATE"));
				
			} else if(getSlaveForCustomisation()!=null) {
				int daysToGo = 7-(Main.game.getDayNumber()-Main.game.getDialogueFlags().helenaSlaveOrderDay);
				if(daysToGo>0) {
					UtilText.addSpecialParsingString(Util.intToString(daysToGo), true);
					LocalDateTime timeReady = Main.game.getDateNow().plusDays(daysToGo);
					UtilText.addSpecialParsingString(timeReady.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false);
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_CUSTOM_SLAVE_PROGRESS"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_CUSTOM_SLAVE_READY"));
				}
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				if(index==0) {
					return new Response("Уйти", "Попрощаться с Helena и выйти из лавки.", HELENAS_SHOP_EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
							}
						}
					};
					
				} else if(index==1) {
					if(getSlaveForCustomisation()!=null) {
						int daysToGo = 7-(Main.game.getDayNumber()-Main.game.getDialogueFlags().helenaSlaveOrderDay);
						if(daysToGo>0) {
							return new Response("Забрать рабыню",
									"Ваша кастомная рабыня находится в процессе обучения и трансформации и будет готова к получению через "+Util.intToString(daysToGo)+" дней.",
									null);
							
						} else {
							return new Response("Забрать рабыню", "Сказать Helena, что вы готовы забрать свою кастомную рабыню.", HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY) {
								@Override
								public void effects() {
									NPC slave = (NPC) getSlaveForCustomisation();
									slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
								}
							};
						}
						
					} else {
						return new Response("Кастомная рабыня", "Спросить Helena о заказе кастомной рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_START);
					}
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopTalkedTo)) {
						return new Response("Поговорить", "Сегодня вы уже успели немного поболтать с Helena...", null);
					}
					return new Response("Поговорить", "Спросить у Helena, как идут её дела.", HELENAS_SHOP_TALK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopTalkedTo, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 2));
						}
					};
					
				} else if(index==3 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopFucked)) {
						return new Response("Подсобка", "Сегодня вы уже занимались сексом с Helena в подсобке, и у неё нет времени на повторение...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						return new Response("Подсобка", "Поскольку вы не можете получить доступ к своему рту или гениталиям, вы не можете заняться сексом с Helena...", null);
					}
					return new Response("Подсобка", "Принять предложение Helena отправиться с ней в подсобку.<br/>[style.italicsSex(Это приведет к сексу с ней...)]", HELENAS_SHOP_BACK_ROOM) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopFucked, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				}
				//TODO
//				else if(index==3 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
//					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettThreesome)) {
//						return new Response("Threesome", "Helena will be unwilling to have a threesome until after you've convinced her to do it in her bedroom.", null);
//					}
//					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopFucked)) {
//						return new Response("Threesome", "You've already had sex with Helena in the back room today, and she doesn't have time to do it again...", null);
//					}
//					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
//							&& (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
//							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
//						return new Response("Threesome", "As you are unable to gain access to your mouth or genitals, you can't have sex with Helena...", null);
//					}
//					return new Response("Threesome",
//							"Ask Helena if she'd like to have a threesome with you and Scarlett in the back room...",
//							HELENAS_SHOP_BACK_ROOM_THREESOME) {
//						@Override
//						public boolean isSexHighlight() {
//							return true;
//						}
//						@Override
//						public void effects() {
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopFucked, true);
//							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
//						}
//					};
//					
//				}
				else if(index==5) {
					if(Main.game.getDayOfWeek()!=DayOfWeek.FRIDAY || Main.game.getHourOfDay()<17) {
						return new Response("Датэ", "Вы можете пригласить Helena на свидание только по пятницам после [units.time(17)].", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Датэ", "Так как свидание подразумевает ужин, вам [style.colourBad(необходим доступ к своему рту)], чтобы пригласить Helena на свидание!", null);
					}
					return new Response("Датэ", "Пригласить Helena на свидание.", HelenaHotel.DATE_START);
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafe)) {
						return new Response("Scarlett", "Поскольку Helena уже провела с тобой обеденный перерыв, она не согласится дать Scarlett еще больше свободного времени на общение с тобой...", null);
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCounterOral)) {
						return new Response("Scarlett", "Поскольку ты уже провел время с Scarlett сегодня, Helena не согласится дать ей еще больше свободного времени на общение с тобой...", null);
					}
					return new Response("Scarlett", "Спросить у Helena, можно ли тебе провести немного времени с Scarlett.", HELENAS_SHOP_SCARLETT);
				}
				return null;
			}
			
			if (index==0
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				return new Response("Уйти", "Попрощаться с Helena и выйти из лавки.", HELENAS_SHOP_EXTERIOR);
			}
			
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
				if(index == 1) {
					return new Response("Управляющая рабами", "Войти в меню управления рабами.", ROMANCE_SHOP_CORE) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(ROMANCE_SHOP_CORE, Main.game.getNpc(Helena.class));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				if(index==2) {
					return new Response("Предложить помощь", "Сказать Helena, что ты готов оказать любую помощь, необходимую для развития её бизнеса.", ROMANCE_OFFER_HELP);
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(index==1) {
					if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
						return new Response("Покрасить", "Показать Helena банку золотой краски «Пурпурная звезда», о которой она просила.", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_PREMIUM"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN_PREMIUM);
							}
						};
						
					} else if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
						return new Response("Покрасить", "Показать Helena банку золотой краски «Бронзовый звездный блеск» и надеяться, что она не заметит, что это не совсем то, что она просила...", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaCheapPaint, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_STANDARD"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN);
							}
						};
						
					} else {
						return new Response("Покрасить", "Вы еще не купили золотую краску, которую просила Helena...", null);
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("Счистить краску", "Сделать так, как велела Helena: счистить всю старую, облупившуюся краску с фасада лавки.", ROMANCE_PAINTING_A) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR));
						}
					};
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("Покрасить фасад", "Сделать так, как велела Helena: покрыть весь фасад свежим слоем белой краски.", ROMANCE_PAINTING_B);
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("Взять краску", "Сделать так, как велит Helena: вынести золотую краску на улицу.", ROMANCE_PAINTING_C);
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
					if(!Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						if(index==1) {
							return new Response("Уйти", "Сделать так, как говорит Helena: покинуть её лавку.", SlaverAlleyDialogue.ALLEYWAY) {
								@Override
								public void effects() {
									Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
								}
							};
						}
						
					} else {
						if(index==1) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 1); // Sell
							
						} else if(index==2) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 2); // Give
							
						} else if(index==5) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 5); // Refuse
						}
					}
					
				} else {
					if(index==1) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) { // Returning after recovering Scarlett from nest or antiques shop:
							return new Response("Согласиться",
									Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
										?"Сказать Helena, что вы вернетесь в понедельник."
										:"Сказать Helena, что вы вернетесь завтра.",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
								}
							};
							
						} else { // Returning before Scarlett is recovered:
							return new Response("Уйти", "Сделать так, как говорит Helena: покинуть её лавку.", SlaverAlleyDialogue.ALLEYWAY) {
								@Override
								public void effects() {
									Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
								}
							};
						}
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				if(index==1) {
					return new Response("Следовать",
							"Сделать как просит Helena, и последовать за ней в подсобку.",
							ROMANCE_5_POTIONS);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поблагодарить её", "Может, она ждет, что вы ее поблагодарите?", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_THANK_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
				
			} else if(index==2) {
				return new Response("Подтолкнуть ее", "Сказать ей, чтобы она уже заканчивала.", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_PROMPT_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS_FOLLOWUP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_FOLLOWUP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(0, index);
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()>=10_000) {
					return new Response("Заплатить ("+UtilText.formatAsMoney(10_000, "охват")+")", "Заплатить Helena те десять тысяч огней, которые она просит.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getNpc(Helena.class).incrementMoney(10_000);
						}
					};
					
				} else if(Main.game.getPlayer().getMoney()>0) {
					return new Response("Заплатить ("+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "охват")+")", "Сказать Helena, что у вас нет десяти тысяч огней, поэтому вы не можете заплатить.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(Util.intToString(Main.game.getPlayer().getMoney()), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY_REDUCED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getPlayer().getMoney()));
							Main.game.getNpc(Helena.class).incrementMoney(Main.game.getPlayer().getMoney());
						}
					};
					
				} else {
					return new Response("Невозможно заплатить", "Сказать Helena, что у вас вообще нет с собой огней, поэтому вы не можете заплатить.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_CANNOT_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Отказаться", "Сказать Helena, что она ведет себя крайне неразумно; <i>она</i> должна платить <i>вам</i>!", ROMANCE_OFFER_HELP_PAYMENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_REFUSE_TO_PAY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_PAYMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAYMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Припасы", "Сделай так, как велит Helena, и принеси ящик с припасами.", ROMANCE_OFFER_HELP_FETCH_SUPPLIES);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_FETCH_SUPPLIES = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_FETCH_SUPPLIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "Подожди, пока Helena найдет то, что ищет.", ROMANCE_OFFER_HELP_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_2_PURCHASE_PAINT));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), false);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_A = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
							?"Тебе придется ждать до понедельника, прежде чем продолжить работу..."
							:"Тебе придется ждать до завтра, прежде чем продолжить работу...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_B = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
			spawnDeliveryNpcs();
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_2", getDeliveryNpcs());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						isMule()
							?"Поприветствовать Наталью"
							:"Знакомство",
						isMule()
							?"Поприветствуй свою Госпожу и скажи ей, что ты пришел за мебелью, которая должна быть в задней части телеги."
							:"Представься человеком, которого ищет эта [natalya.race], а затем приступай к приемке мебели, которая должна быть в задней части телеги.",
						ROMANCE_PAINTING_FURNITURE_DELIVERY) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setPlayerKnowsName(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "Не предлагай помощи, а вместо этого стой в стороне и жди, пока кентавры закончат свое дело.", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_NO_HELP"));
					}
				};
				
			} else if(index==2) {
				return new Response("Предложить помощь", "Спроси Наталью, можешь ли ты чем-нибудь помочь.", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_HELP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMule()) {
				if(index==1) {
					return new Response("Следовать",
							"Следуйте за госпожой Натальей по переулку, чтобы узнать, что ей от вас нужно.",
							ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT) {
						@Override
						public void effects() {
							Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Остаться", "Ждать у тележки, пока Наталья вернется.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
						@Override
						public int getSecondsPassed() {
							return 15*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_WAIT"));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isAnalContentEnabled()) {
						return new Response("Следовать",
								"У вас возникает предчувствие, что, если вы последуете за Натальей в переулок, то увидите нечто такое, чего вам бы очень не хотелось...<br/>[style.italicsMinorBad(Сцены с Натальей включают анальный секс и будут отключены до тех пор, пока в настройках включен параметр 'Анальный секс'.)]",
								null);
					}
					return new Response("Следовать",
							"Последовать за Натальей в переулок и посмотреть, чем она занимается.<br/>[style.italicsSex(У вас возникает предчувствие, что вы можете увидеть нечто весьма непристойное...)]",
							ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW) {
						@Override
						public void effects() {
							Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : getDeliveryNpcs()) {
				Main.game.banishNPC((NPC) npc);
			}
			Main.game.getNpc(Natalya.class).returnToHome();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR));
			if(!Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD)) {
				Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false));
			}
		}
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
				return new Response("Запереть лавку",
						"Запереть лавку и приготовиться к уходу.",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Продолжить путь по улице Рабской аллее.",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Развернуться и оставить Наталию доводить себя до оргазма без вашей помощи.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 15*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_LEAVE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if(index==2) {
				return new Response("Подчиниться",
						"Сделать так, как прикажет доминантная [natalya.race], а затем, назвав её «госпожой», опуститься перед ней на колени и подчиниться любым её планам на ваш счёт...",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerSubmittedToNatalya, true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Начать ласкать", "Исполнить приказ Госпожи Натальи и начать ласкать её толстый лошадиный член.",
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isAbleToSkipSexScene() {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return false;
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
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
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								Map<GameCharacter, List<SexAreaInterface>> map = new HashMap<>();
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaOrifice.NIPPLE, SexAreaOrifice.BREAST));
								map.put(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaPenetration.FOOT));
								return map;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.PULL_OUT;
							}
							@Override
							public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
								if(!character.isPlayer()) {
									if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
										return OrgasmCumTarget.FACE;
									} else {
										return OrgasmCumTarget.FLOOR;
									}
								}
								return super.getCharacterPullOutOrgasmCumTarget(character, target);
							}
						},
						null,
						null,
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX = new DialogueNode("Завершено", "На данный момент Госпожа Наталья закончила с вами.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "Последовать за госпожой Натальей, чтобы проверить, закончили ли две кентавры распаковывать мебель.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX_FOLLOW"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Раскрасить вывеску", "Под пристальным надзором Helena используйте купленную золотую краску, чтобы написать слова «Бутик Helena» над входом в лавку.", ROMANCE_PAINTING_C_PAINT_SIGN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C_PAINT_SIGN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 10*60;
			}
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_PAINT_SIGN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "Последовать за Helena обратно в её магазин.", ROMANCE_PAINTING_C_FINISHED) {
					@Override
					public void effects() {
						// If Scarlett has been sold but not 'banished' yet, 'banish' her now:
						if(Main.game.getNpc(Scarlett.class).isSlave() && !Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
							Main.game.getNpc(Scarlett.class).getOwner().removeSlave(Main.game.getNpc(Scarlett.class));
							Main.game.banishNPC(Main.game.getNpc(Scarlett.class));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Заварить чай",
						"Сделать так, как говорит Helena, и заварить чай на двоих.",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_TEA"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Отказаться",
						"Отказаться заваривать чай для Helena, а вместо этого прямо спросить, нужно ли ей что-нибудь ещё.",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_NO_TEA"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_SCARLETT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Читать", "Прочитать письмо, которое только что вручила вам Helena.", ROMANCE_PAINTING_C_FINISHED_LETTER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_LETTER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_LETTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
				if(!Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
					if(index==1) {
						return new Response("Согласиться", "Согласиться сходить за Scarlett и привести [scarlett.herHim] обратно сюда.", ROMANCE_SCARLETT_OWNED_FETCH);
					}
				} else {
					if(index==1) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
							return new Response("Продать Scarlett ("+UtilText.formatAsMoney(getScarlettPrice(), "охват", PresetColour.GENERIC_GOOD)+")",
									"Продать Scarlett обратно Helena за  "+UtilText.formatAsMoney(getScarlettPrice())+".",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
									
									Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
									Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
									Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD"));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD_REACTION"));
									if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || !Main.game.getNpc(Scarlett.class).isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NEEDING_TF"));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NO_TF_NEEDED"));
									}
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(getScarlettPrice()));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
									
									Main.game.addSavedInventory(Main.game.getNpc(Scarlett.class));
									int essences = Main.game.getNpc(Scarlett.class).getEssenceCount();
									Main.game.getNpc(Scarlett.class).setInventory(new CharacterInventory(false, 0));
									Main.game.getNpc(Scarlett.class).setEssenceCount(essences);
								}
							};
							
						} else {
							return new Response("Продать Scarlett ("+UtilText.formatAsMoneyUncoloured(getScarlettPrice(), "охват")+")",
									"Чтобы продать [scarlett.herHim] Хелене, вам нужно сначала привести сюда Scarlett в качестве своей спутницы!",
									null);
						}
						
					} else if(index==2) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
							return new Response("Отдать Scarlett",
									"Отдать Scarlett обратно Хелене и не принимать то, "+UtilText.formatAsMoney(getScarlettPrice())+" что она вам предлагает.",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
									
									Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
									Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
									Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_GIVEN_AWAY"));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD_REACTION"));
									if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || !Main.game.getNpc(Scarlett.class).isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NEEDING_TF"));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NO_TF_NEEDED"));
									}
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 15));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
									
									Main.game.addSavedInventory(Main.game.getNpc(Scarlett.class));
									int essences = Main.game.getNpc(Scarlett.class).getEssenceCount();
									Main.game.getNpc(Scarlett.class).setInventory(new CharacterInventory(false, 0));
									Main.game.getNpc(Scarlett.class).setEssenceCount(essences);
								}
							};
							
						} else {
							return new Response("Отдать Scarlett", "Чтобы отдать [scarlett.herHim] Хелене, вам нужно сначала привести сюда Scarlett в качестве своей спутницы!", null);
						}
					}
				}
				
				if(index==5) {
					return new Response("Отказаться",
								"Отказаться продавать Scarlett Хелене.<br/>[style.italicsBad(Это приведет к провалу квеста, и без вашей помощи Хелене придется закрыть лавку и вернуться в Гнезда Гарпий!)]",
								ROMANCE_QUEST_FAILURE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setAffection(Main.game.getPlayer(), -100));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_FAILED));
							Main.game.getNpc(Helena.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
							if(!Main.game.getPlayer().hasCompanion(Main.game.getNpc(Scarlett.class))) {
								Main.game.getNpc(Scarlett.class).returnToHome();
								Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'><i>Вы приказываете [scarlett.name] вернуться в комнату [scarlett.her]...</i></p>");
							}
						}
					};
				}
				return null;
			}
			
			if(index==1) {
				return new Response("Продолжить",
						"Продолжить путь по улице Рабской аллее.",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						if(Main.game.getNpc(Scarlett.class).getHomeWorldLocation()!=WorldType.HARPY_NEST) {
							Main.game.getNpc(GenericMaleNPC.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ANTIQUES);
						}
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode ROMANCE_SCARLETT_OWNED_FETCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_OWNED_FETCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						"Раз уж вы обещали сходить и привести Scarlett, вам лучше поспешить...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_QUEST_FAILURE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_REFUSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						"Поскольку Хелена забросила свою лавку, вам здесь больше нечего делать, кроме как уйти...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_SCARLETT_DELIVERED_EMPTY = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
			
			if(Main.game.getNpc(Helena.class).getAffection(Main.game.getNpc(Scarlett.class))<0) {
				Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), 0);
			}
			
			((Scarlett)Main.game.getNpc(Scarlett.class)).resetName();
			
			((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();
		}
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
				return new Response("Уйти",
						"Теперь, когда Helena и Scarlett ушли, тебе остается только одно — уйти...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_5_POTIONS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getSavedInventories().remove(Main.game.getNpc(Scarlett.class).getId()); // Removed Scarlett saved inventory, as it was restored to the player in the preceding scene
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Оставить её", "Сказать Helena, что лучше бы не превращать Scarlett.", ROMANCE_5_POTIONS_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS_SCARLETT_NO_TF"));
					}
				};
				
			} else if(index==2) {
				return new Response("Превратить её", "Сказать Helena, что превращение Scarlett в самочку гарпии пойдет ей только на пользу.", ROMANCE_5_POTIONS_NEXT) {
					@Override
					public void effects() {
						((Scarlett)Main.game.getNpc(Scarlett.class)).applyFeminisation();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS_SCARLETT_FEMALE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_5_POTIONS_NEXT = new DialogueNode("", "", true, true) {
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
				return new Response("Следовать", "Последовать за Helena обратно в лавку.", ROMANCE_ADVERTISING_POSTERS);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сделать комплимент", "Сделать Helena комплимент по поводу того, как красиво она выглядит на плакатах.", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_COMPLIMENT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Допрос", "Согласиться со Scarlett и спросить, как вообще эти плакаты должны рекламировать бизнес.", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_QUESTION"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ROLLED_UP_POSTERS), false));
			if(Main.getProperties().addItemDiscovered(ItemType.ROLLED_UP_POSTERS)) {
				Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(ItemType.ROLLED_UP_POSTERS.getName(false), ItemType.ROLLED_UP_POSTERS.getRarity().getColour()), true);
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_6_ADVERTISING));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(100));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						"Получив распоряжения от Helena, ты готов отправиться в Переулок Работорговцев, чтобы расклеить эти плакаты.",
						SlaverAlleyDialogue.GATEWAY_POSTER_PERMISSION) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_ENTRANCE);
					}
				};
//				return new Response("Leave",
//						"Having received your orders from Helena, you're ready to set out into Slaver Alley and put up these posters.",
//						SlaverAlleyDialogue.ALLEYWAY) {
//					@Override
//					public void effects() {
//						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
//					}
//				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_RETURN_AFTER_POSTERS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0; // 0 as otherwise the time in the dialogue doesn't match up
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_RETURN_AFTER_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать",
						"Последовать за Helena по всей лавке, чтобы узнать, что именно она хочет, чтобы ты и Scarlett сделали к завтрашнему дню.",
						ROMANCE_7_FOLLOW) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_FOLLOW"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION));
						Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
						Main.game.getPlayer().removeAllCompanions(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_FOLLOW = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Начать работу",
						"Делать особо нечего, остается только взяться за работу...",
						ROMANCE_7_START);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вежливо попросить",
						"Любезно попросить Scarlett помочь вам прибраться в лавке.",
						ROMANCE_7_WORKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_ASK_NICELY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==2) {
				return new Response("Отругать её",
						"Отругать Scarlett за то, что она вечно создает проблемы, и приказать ей помогать.",
						ROMANCE_7_WORKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_SCOLDING"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_WORKING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Развесить украшения",
						"Приступить к развешиванию украшений.",
						ROMANCE_7_DECORATIONS) {
					@Override
					public void effects() {
						AbstractClothing dress = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.TORSO_UNDER);
						if(dress!=null) {
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(dress, true, Main.game.getNpc(Scarlett.class));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_7_DECORATIONS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(1 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_DECORATIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отклонить",
						"Отклонить предложение виски от Scarlett.",
						ROMANCE_7_WORKING_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_NO_DRINK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAlcoholLevel(0.3f));
					}
				};
				
			} else if(index==2) {
				return new Response("Выпить",
						"Выпить виски, который предлагает вам Scarlett.",
						ROMANCE_7_WORKING_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_DRINK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAlcoholLevel(0.3f));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.3f));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_WORKING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
				AbstractClothing bra = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.CHEST);
				if(bra!=null) {
					Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(bra, true, Main.game.getNpc(Scarlett.class));
				}
				AbstractClothing underwear = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.GROIN);
				if(underwear!=null) {
					Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(underwear, true, Main.game.getNpc(Scarlett.class));
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("Спать", "Заснуть на диване.", ROMANCE_7_MORNING);
				}
				
			} else {
				if(Main.game.getNpc(Scarlett.class).hasVagina()) {
					if(index==1) {
						return new Response("Отклонить", "Сказать Scarlett, что тебе не интересно обслуживать её киску.", ROMANCE_7_SEX_DECLINED);
						
					} else if(index==2) {
						if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							return new ResponseSex(
									"Оседлать",
									"Сделать как просит Scarlett и позволить ей оседлать твой член.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_RIDDEN_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							};
							
						} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex(
									"Отсосать",
									"Сделать как просит Scarlett и отсосать ей.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_CUNNILINGUS_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							};
							
						} else {
							return new ResponseSex(
									"Ласкать пальцами",
									"Сделать как просит Scarlett и потыкать ей пальцами.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_FINGERING_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERED_START, false, true));
								}
							};
						}
					}
					
				} else {
					 if(index==1) {
						return new Response("Отклонить", "Сказать Scarlett, что тебе не интересно обслуживать её член.", ROMANCE_7_SEX_DECLINED);
						
					} else if(index==2) {
						if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							return new ResponseSex(
									"Предложить задницу",
									"Сделать как просит Scarlett и предложить ей свой зад.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS, CoverableArea.VAGINA)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_ANAL_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> initialActions = new ArrayList<>();
									
									initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
									
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true));
										
									} else if(Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
										initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
										
									}
									
									return initialActions;
								}
							};
							
						} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex(
									"Сосать член",
									"Сделать как просит Scarlett и отсосать её член.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_ORAL_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
								}
							};
							
						} else {
							return new ResponseSex(
									"Сделать дрилдинг рукой",
									"Сделай так, как просит Scarlett, и доведи её до оргазма рукой.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_HANDJOB_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
								}
							};
						}
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_AFTER_SEX = new DialogueNode("Завершено", "Scarlett довольна твоими стараниями.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Спать", "Заснуть на диване.", ROMANCE_7_MORNING) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettSleepoverSex, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_SEX_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_SEX_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Спать", "Заснуть на диване.", ROMANCE_7_MORNING);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettSleepoverSex) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Разбудить её", "Тряхни Scarlett, чтобы разбудить её.", ROMANCE_7_MORNING_TIDY_UP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_WAKE_SCARLETT_NO_SEX"));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getNpc(Scarlett.class).hasVagina()) {
						return new ResponseSex(
								"Утренний куннилингус",
								"Сделай то, о чем Scarlett просила тебя прошлой ночью, и разбуди её, лаская её киску языком.",
								true,
								false,
								getScarlettSleepoverSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								ROMANCE_7_MORNING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_CUNNILINGUS_START")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 20));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex(
								"Утренний минет",
								"Сделай то, о чем Scarlett просила тебя прошлой ночью, и разбуди её, сделав ей минет.",
								true,
								false,
								getScarlettSleepoverSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								ROMANCE_7_MORNING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_BLOWJOB_START")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 20));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						};
					}
					
				}
				
			} else {
				if(index==1) {
					return new Response("Разбудить её", "Тряхни Scarlett, чтобы разбудить её.", ROMANCE_7_MORNING_TIDY_UP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_WAKE_SCARLETT"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING_AFTER_SEX = new DialogueNode("Завершено", "Теперь, когда она получила оргазм, с Scarlett достаточно.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Прибраться", "Прибраться в задней комнате до прихода Helena.", ROMANCE_7_MORNING_TIDY_UP);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING_TIDY_UP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getHourOfDay()<9) {
				return (Main.game.getMinutesUntilTimeInMinutes(9 * 60) * 60) + 10*60;
			}
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_TIDY_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Готовить напитки", "Работать вместе со Scarlett, готовя напитки для гостей.", ROMANCE_7_MAKING_DRINKS);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_7_MAKING_DRINKS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY) {
				return (Main.game.getMinutesUntilTimeInMinutes(21 * 60) * 60) + 10*60;
			}
			return (Main.game.getMinutesUntilTimeInMinutes(17 * 60) * 60) + 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MAKING_DRINKS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Закрыть глаза", "Сделать так, как приказала Helena, и закрыть глаза.", ROMANCE_END_KISSED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_CLOSE_EYES"));
					}
				};
				
			} else if(index==2) {
				return new Response("Подглядывать", "Притвориться, что глаза закрыты, но втайне подглядывать, чтобы понять, что задумала Helena.", ROMANCE_END_KISSED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_PEEK"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_END_KISSED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.SIDE_UTIL_COMPLETE));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 25));
			
			if(Main.game.getNpc(Helena.class).getAffection(Main.game.getNpc(Scarlett.class))<50) {
				Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), 50);
			}
			Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_COMPLETED"));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить путь обратно в Переулок работорговцев...", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setHomeLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	
	// Dialogue for when romance quest is completed:
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Решить не заказывать у Helena уникальную рабыню.", HELENAS_SHOP_CUSTOM_SLAVE_DECLINED);
				
			} else if(index==1) {
				return new Response("[style.colourFeminine(Женский шаблон)]", "Начать создание своей уникальной рабыни, взяв за основу человеческую женщину.", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public void effects() {
						generateStartingSlave(Gender.F_V_B_FEMALE);
					}
				};
				
			} else if(index==2) {
				return new Response("[style.colourMasculine(Мужской шаблон)]", "Начните создание своего уникального раба, взяв за основу мужчину.", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public void effects() {
						generateStartingSlave(Gender.M_P_MALE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY"));
			
			sb.append(
					"<div class='container-full-width' style='padding:8px;'><div style='width:22%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Имя</div><div style='width:22%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Фамилия</div><div style='width:24%; float:left; font-weight:bold; margin:0 6% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(getSlaveForCustomisation(), "Как тебя называет [npc.she]")
						+ "</div>"
						
						+ "<form style='float:left; width:22%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:22%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput' value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:24%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div></div>");
			
			sb.append("<div class='cosmetics-container' style='background:transparent;'>"
						+ CharacterModificationUtils.getAgeChoiceDiv()
						+ CharacterModificationUtils.getOrientationChoiceDiv()
						+ CharacterModificationUtils.getPersonalityChoiceDiv(true)
						+ CharacterModificationUtils.getObedienceChoiceDiv()
						+ CharacterModificationUtils.getAffectionChoiceDiv()
						+ CharacterModificationUtils.getFetishChoiceDiv()
					+"</div>");
			
			sb.append("<p id='hiddenFieldName' style='display:none;'></p>");
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Отмена", "Решить не заказывать у Helena уникальную рабыню.", ROMANCE_SHOP_CORE) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getSlaveForCustomisation());
					}
				};
				
			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					return new Response("Личность", "Вы уже настраиваете характер своей рабыни!", null);
				}
				return new Response("Личность", "Настройте черты характера вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE) {
					return new Response("Тело", "Вы уже настраиваете основные параметры тела вашей рабыни!", null);
				}
				return new Response("Тело", "Настройте основные параметры тела вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==3) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES) {
					return new Response("Глаза", "Вы уже настраиваете глаза вашей рабыни!", null);
				}
				return new Response("Глаза", "Настройте глаза вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==4) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR) {
					return new Response("Волосы", "Вы уже настраиваете волосы вашей рабыни!", null);
				}
				return new Response("Волосы", "Настроить прическу вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==5) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD) {
					return new Response("Голова", "Вы уже настраиваете черты головы и лица вашей рабыни!", null);
				}
				return new Response("Голова", "Настроить черты головы и лица вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==6) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS) {
					return new Response("Задница", "Вы уже настраиваете бедра и задницу вашей рабыни!", null);
				}
				return new Response("Задница", "Настроить бедра и задницу вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==7) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS) {
					return new Response("Грудь", "Вы уже настраиваете грудь вашей рабыни!", null);
				}
				return new Response("Грудь", "Настроить грудь вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA) {
					return new Response("Киска", "Вы уже настраиваете киску вашей рабыни!", null);
				}
				return new Response("Киска", "Настроить параметры влагалища вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==9) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS) {
					return new Response("Пенис", "Вы уже настраиваете параметры пениса вашей рабыни!", null);
				}
				return new Response("Пенис", "Настроить параметры пениса вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==10) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET) {
					return new Response("Паутинный аппарат", "Вы уже настраиваете параметры паутины вашей рабыни!", null);
				}
				if(!BodyChanging.getTarget().hasSpinneret()) {
					return new Response("Паутинный аппарат",
							"У вашей рабыни нет паутины!<br/><i>Паутина появляется при наличии определенных типов хвоста или ног.</i>",
							null);
				}
				return new Response("Паутинный аппарат", "Настроить параметры паутины вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==11) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH) {
					return new Response("Лобковые соски", "Вы уже настраиваете параметры лобковых молочек вашей рабыни!", null);
				}
				
				return new Response(
						BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски",
						UtilText.parse(BodyChanging.getTarget(), "Изменить параметры [npc.crotchBoobs] вашей рабыни."),
						HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH);
				
			} else if(index==12) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP) {
					return new Response("Макияж", "Вы уже настраиваете макияж вашей рабыни!", null);
				}
				return new Response("Макияж", "Настроить макияж вашей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==13) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS) {
					return new Response("Пирсинг", "Вы уже настраиваете пирсинг своей рабыни!", null);
				}
				return new Response("Пирсинг", "Настроить пирсинг своей рабыни.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==14) {
				return new Response("[style.colourMinorGood(Завершить заказ)]",
						"Скажите Helena, что вы заполнили все формы заказа, и посмотрите, во сколько это вам обойдется...",
						HELENAS_SHOP_CUSTOM_SLAVE_FINISH) {
					@Override
					public void effects() {
						BodyChanging.getTarget().setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} 
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE"));
			sb.append(BodyChanging.BODY_CHANGING_CORE.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES"));
			sb.append(BodyChanging.BODY_CHANGING_EYES.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR"));
			sb.append(BodyChanging.BODY_CHANGING_HAIR.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD"));
			sb.append(BodyChanging.BODY_CHANGING_HEAD.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS"));
			sb.append(BodyChanging.BODY_CHANGING_ASS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS"));
			sb.append(BodyChanging.BODY_CHANGING_BREASTS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA"));
			sb.append(BodyChanging.BODY_CHANGING_VAGINA.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS"));
			sb.append(BodyChanging.BODY_CHANGING_PENIS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH"));
			sb.append(BodyChanging.BODY_CHANGING_BREASTS_CROTCH.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET"));
			sb.append(BodyChanging.BODY_CHANGING_SPINNERET.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP"));

			sb.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Румяна", "Румяна (также называемые руж) используются для придания щекам цвета, чтобы придать лицу более юный вид и подчеркнуть скулы.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Помада", "Помада используется для придания губам цвета, текстуры и защиты.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Подводка для глаз", "Подводка наносится по контуру глаз, чтобы подчеркнуть их форму или выделить отдельные черты.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Тени для век", "Тени для век используются для того, чтобы сделать глаза ярче или привлекательнее.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Лак для ногтей", "Лак для ногтей используется для окрашивания и защиты ногтей на руках.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей на ногах используется для окрашивания и защиты ногтей на стопах.", true, true));
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS = new DialogueNode("Настройка раба", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS"));
			sb.append(CharacterModificationUtils.getKatesDivPiercings(true));
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_FINISH = new DialogueNode("Заказать рабыню", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(false)), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_FINISH"));
			
			sb.append("<div class='container-full-width' style='text-align:center;'><i>Чем больше сексуального опыта, тем выше будет Порочность вашей рабыни.</i></div>"
						+CharacterModificationUtils.getSexualExperienceDiv());
			
			sb.append("<div class='container-full-width'>"
						+ UtilText.parse(BodyChanging.getTarget(), "<p style='text-align:center;'><b>[npc.NamePos] Внешность</b></p>")
						+ BodyChanging.getTarget().getBodyDescription()
					+ "</div>");
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Вернуться назад и внести изменения...", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY);
				
			} else if(index==1) {
				if(Main.game.getPlayer().getMoney()<getSlaveValue(false)) {
					return new Response("Заказать ("+UtilText.formatAsMoneyUncoloured(getSlaveValue(false), "охват")+")",
							"Вы не можете позволить себе заказать рабыню, так как у вас всего "+Util.intToString(Main.game.getPlayer().getMoney())+" огни.",
							null);
				}
				return new Response("Заказать ("+UtilText.formatAsMoney(getSlaveValue(false), "охват")+")",
						"Скажите Helena, что вы хотели бы заказать рабыню за "+Util.intToString(getSlaveValue(false))+" огни.",
						HELENAS_SHOP_CUSTOM_SLAVE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(false)), true);
						UtilText.addSpecialParsingString(Main.game.getDateNow().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getSlaveValue(false)));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<getSlaveValue(true)) {
					return new Response("Слайм-специал ("+UtilText.formatAsMoneyUncoloured(getSlaveValue(true), "охват")+")",
							"Вы не можете позволить себе заказать слайм-специал, так как у вас всего "+Util.intToString(Main.game.getPlayer().getMoney())+" огни.",
							null);
				}
				return new Response("Слайм-специал ("+UtilText.formatAsMoney(getSlaveValue(true), "охват")+")",
						"Скажите Helena, что вы хотели бы заказать рабыню со «слайм-специалом» за "+Util.intToString(getSlaveValue(true))+" огни.",
						HELENAS_SHOP_CUSTOM_SLAVE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(true)), true);
						UtilText.addSpecialParsingString(Main.game.getDateNow().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_SLIME"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getSlaveValue(true)));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_ORDER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().helenaSlaveOrderDay = Main.game.getDayNumber();
			CharacterModificationUtils.resetImpossibeSexExperience(); // If no vagina/penis, reset experience.
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			NPC slave = (NPC) getSlaveForCustomisation();
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC slave = (NPC) getSlaveForCustomisation();
			if(index==1) {
				return new Response("Ответить", UtilText.parse(slave, "Ответить утвердительно на вопрос [npc.namePos]."), HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END", slave));
						Main.game.getPlayer().addSlave(slave);
						slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END = new DialogueNode("", "", true, true) {
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
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			AbstractClothing underwear = Main.game.getNpc(Helena.class).getClothingInSlot(InventorySlot.GROIN);
			if(underwear!=null) {
				Main.game.getNpc(Helena.class).unequipClothingIntoVoid(underwear, true, Main.game.getNpc(Helena.class));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				responses.add(new Response("Куннилингус", "Так как вы не можете добраться до своего рта, вы не можете отсосать Helena!", null));
			} else {
				responses.add(new ResponseSex(
						"Куннилингус",
						"Опуститься перед Helena и начать отсасывать ей.",
						true, true,
						HelenaHotel.getHelenaSexManager(false,
								SexPosition.AGAINST_WALL, SexSlotAgainstWall.BACK_TO_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_PERFORM_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
				});
			}

			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Анилингус", "Так как вы не можете добраться до своего рта, вы не можете сделать анилингус Helena!", null));
				} else {
					responses.add(new ResponseSex(
							"Анилингус",
							"Спросить Helena, не может ли она вместо этого сделать вам анилингус.",
							true, true,
							HelenaHotel.getHelenaSexManager(false,
									SexPosition.AGAINST_WALL, SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_PERFORM_ANILINGUS")) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
						}
					});
				}
			}
			
			if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) { // If Helena is dominant, she wants to be on the receiving end of oral
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Получить минет", "Так как вы не можете добраться до своего пениса, Helena не может отсосать ваш член.", null));
					} else {
						responses.add(new ResponseSex(
								"Получить минет",
								"Попросить Helena отсосать ваш член.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.AGAINST_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL
											:SexSlotAgainstWall.PERFORMING_ORAL_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotAgainstWall.BACK_TO_WALL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_BLOWJOB_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_BLOWJOB_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
						});
					}
				}
	
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Отсосали", "Так как вы не можете добраться до своей киски, Helena не может отсосать вам.", null));
					} else {
						responses.add(new ResponseSex(
								"Отсосали",
								"Попросить Helena отсосать тебе.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.AGAINST_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL_BEHIND
											:SexSlotAgainstWall.PERFORMING_ORAL_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotAgainstWall.BACK_TO_WALL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_CUNNILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_CUNNILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						});
					}
				}
			}
			
			// Penetrative sex:
			
			if(Main.game.getPlayer().hasPenis()) {
				if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
						responses.add(new Response("Оседлать", "Helena не желает терять девственность в такой ситуации!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Оседлать", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть Helena.", null));
						
					} else {
						responses.add(new ResponseSex(
								"Оседлать",
								"Попросить Helena оседлать твой член.",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RIDDEN")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
							}
						});
					}
					if(Main.game.isAnalContentEnabled()) {
						if(Main.game.getNpc(Helena.class).isAssVirgin()) {
							responses.add(new Response("Оседлала (анально)", "Helena не желает терять анальную девственность в такой ситуации!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Оседлала (анально)", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть задницу Helena.", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							responses.add(new Response("Оседлала (анально)", "Helena захочет, чтобы ты смазал её задницу перед тем, как трахнуть её, но так как ты не можешь дотянуться ртом, у тебя не получается...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Оседлала (анально)",
									"Попросить Helena оседлать твой член анально.<br/>[style.italicsSex(Она заставит тебя смазать её задницу своей слюной, прежде чем позволит войти...)]",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RIDDEN_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
								}
							});
						}
					}
					
				} else { // Submissive:
					if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
						responses.add(new Response("Трахать её", "Helena не желает терять девственность в такой ситуации!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Трахать её", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть Helena.", null));
						
					} else {
						responses.add(new ResponseSex(
								"Трахать её",
								"Наклонить Helena над ближайшим столом и трахнуть её во влагалище.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										SexPosition.OVER_DESK, Main.game.getNpc(Helena.class).isVisiblyPregnant()?SexSlotDesk.OVER_DESK_ON_BACK:SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.BETWEEN_LEGS,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_FUCK_HER")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
					if(Main.game.isAnalContentEnabled()) {
						if(Main.game.getNpc(Helena.class).isAssVirgin()) {
							responses.add(new Response("Трахнуть её (анально)", "Helena не желает терять анальную девственность в такой ситуации!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Трахнуть её (анально)", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть задницу Helena.", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							responses.add(new Response("Трахнуть её (анально)", "Helena захочет, чтобы ты смазал её задницу перед тем, как трахнуть её, но так как ты не можешь дотянуться ртом, у тебя не получается...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Трахнуть её (анально)",
									"Наклонить Helena над ближайшим столом и трахнуть её в задницу.<br/>[style.italicsSex(Она заставит тебя смазать её задницу своей слюной, прежде чем позволит войти...)]",
									true, true,
									HelenaHotel.getHelenaSexManager(false,
											SexPosition.OVER_DESK, Main.game.getNpc(Helena.class).isVisiblyPregnant()?SexSlotDesk.OVER_DESK_ON_BACK:SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.BETWEEN_LEGS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_FUCK_HER_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_AFTER_SEX = new DialogueNode("Завершено", "Helena закончила и ей нужно вернуться к работе.", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	//TODO
	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_THREESOME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Helena сосредоточиться";
			} else if(index==1) {
				return "Scarlett сосредоточиться";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(responseTab==0) { // Helena focus:
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Куннилингус", "Так как вы не можете добраться до своего рта, вы не можете отсосать Helena!", null));
				} else {
					responses.add(new ResponseSex(
							"Куннилингус",
							"Заставь Helena лечь так, чтобы ты мог засунуть голову ей между ног и отсосать ей клитор, пока Scarlett сидит ей на лице.",
							true, true,
							HelenaHotel.getHelenaSexManager(false,
									true,
									true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.MISSIONARY_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Main.game.getNpc(Scarlett.class).hasVagina()
										?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
										:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
									null,
									Main.game.getNpc(Scarlett.class).hasVagina()
										?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
										:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
												:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_PERFORM_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
							} else {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
							return list;
						}
					});
				}

				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Анилингус", "Так как вы не можете добраться до своего рта, вы не можете сделать анилингус Helena!", null));
					} else {
						responses.add(new ResponseSex(
								"Анилингус",
								"Заставь Helena сесть тебе на лицо, чтобы ты мог отсосать ей в анус, пока она делает минет для Scarlett.",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										true,
										false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.BESIDE, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Main.game.getNpc(Scarlett.class).hasVagina()
											?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
											:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										null,
										Main.game.getNpc(Scarlett.class).hasVagina()
											?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
											:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
													:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_PERFORM_ANILINGUS")
								+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_ANILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_ANILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
								if(Main.game.getNpc(Scarlett.class).hasVagina()) {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
								} else {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
								}
								return list;
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) { // If Helena is dominant, she wants to be on the receiving end of oral
					if(Main.game.getPlayer().hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Получить минет", "Так как вы не можете добраться до своего пениса, Helena не может отсосать ваш член.", null));
						} else {
							if(Main.game.getNpc(Scarlett.class).hasPenis()
									&& (!Main.game.getNpc(Helena.class).isAssVirgin() || !Main.game.getNpc(Helena.class).isVaginaVirgin())) {
								if(!Main.game.getNpc(Helena.class).isAssVirgin()) {
									responses.add(new ResponseSex(
											"Получить минет",
											"Заставь Helena отсосать тебе, пока Scarlett трахает её в задницу сзади.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB_SCARLETT_ANAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
									
								} else {
									responses.add(new ResponseSex(
											"Получить минет",
											"Заставь Helena отсосать тебе, пока Scarlett трахает её сзади.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB_SCARLETT_VAGINAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
								}
								
							} else {
								responses.add(new ResponseSex(
										"Получить минет",
										"Заставь Helena отсосать тебе, пока она сидит на лице у Scarlett.",
										true, true,
										HelenaHotel.getHelenaSexManager(true,
												false,
												true,
												SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.BESIDE,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
												null,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
														new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB")
										+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
											?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_BLOWJOB_FIRST_TIME")
											:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_BLOWJOB_EXPERIENCED"))) {
									@Override
									public void effects() {
										if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
											Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
										}
									}
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										return list;
									}
								});
							}
						}
					}
		
					if(Main.game.getPlayer().hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							responses.add(new Response("Отсосали", "Так как вы не можете добраться до своей киски, Helena не может отсосать вам.", null));
						} else {
							if(Main.game.getNpc(Scarlett.class).hasPenis()
									&& (!Main.game.getNpc(Helena.class).isAssVirgin() || !Main.game.getNpc(Helena.class).isVaginaVirgin())) {
								if(!Main.game.getNpc(Helena.class).isAssVirgin()) {
									responses.add(new ResponseSex(
											"Отсосали",
											"Заставь Helena отсосать тебе, пока Scarlett трахает её в задницу сзади.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS_SCARLETT_ANAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
									
								} else {
									responses.add(new ResponseSex(
											"Отсосали",
											"Заставь Helena отсосать тебе, пока Scarlett трахает её сзади.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS_SCARLETT_VAGINAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
								}
								
							} else {
								responses.add(new ResponseSex(
										"Отсосали",
										"Сядь Helena на лицо и заставь её отсасывать тебе, пока Scarlett опустится ей между ног и будет делать куннилингус.",
										true, true,
										HelenaHotel.getHelenaSexManager(false,
												true,
												true,
												SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.FACE_SITTING,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
												null,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
														new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS")
										+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
											?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "GIVING_CUNNILINGUS_FIRST_TIME")
											:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "GIVING_CUNNILINGUS_EXPERIENCED"))) {
									@Override
									public void effects() {
										if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
											Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
										}
									}
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										return list;
									}
								});
							}
						}
					}
				}
				
				// Penetrative sex:
				
				if(Main.game.getPlayer().hasPenis()) {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
							responses.add(new Response("Оседлать", "Helena не желает терять девственность в такой ситуации!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Оседлать", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть Helena.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Оседлать",
									"Заставь Helena скакать на твоем члене, пока Scarlett сидит тебе на лице.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
												:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													Main.game.getNpc(Scarlett.class).hasVagina()
														?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH))
														:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									if(Main.game.getNpc(Scarlett.class).hasVagina()) {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									} else {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
									}
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
						if(Main.game.isAnalContentEnabled()) {
							if(Main.game.getNpc(Helena.class).isAssVirgin()) {
								responses.add(new Response("Оседлала (анально)", "Helena не желает терять анальную девственность в такой ситуации!", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								responses.add(new Response("Оседлала (анально)", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть задницу Helena.", null));
								
							} else {
								responses.add(new ResponseSex(
										"Оседлала (анально)",
										"Заставь Helena скакать на твоем члене в анальном режиме, пока Scarlett сидит тебе на лице.",
										true, true,
										HelenaHotel.getHelenaSexManager(true,
												true,
												false,
												SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
													:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)),
														Main.game.getNpc(Scarlett.class).hasVagina()
															?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH))
															:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RIDDEN_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
										if(Main.game.getNpc(Scarlett.class).hasVagina()) {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										} else {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
										}
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
										return list;
									}
								});
							}
						}
						
					} else { // Submissive:
						if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
							responses.add(new Response("Трахать её", "Helena не желает терять девственность в такой ситуации!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Трахать её", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть Helena.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Трахать её",
									"Повалить Helena на четвереньки и трахать её сзади, пока она отсасывает Scarlett.",
									true, true,
									HelenaHotel.getHelenaSexManager(false,
											true,
											true,
											SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
												:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
											null,
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
												:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													Main.game.getNpc(Scarlett.class).hasVagina()
														?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
														:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_FUCK_HER")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
									if(Main.game.getNpc(Scarlett.class).hasVagina()) {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									} else {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
									}
									return list;
								}
							});
						}
						if(Main.game.isAnalContentEnabled()) {
							if(Main.game.getNpc(Helena.class).isAssVirgin()) {
								responses.add(new Response("Трахнуть её (анально)", "Helena не желает терять анальную девственность в такой ситуации!", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								responses.add(new Response("Трахнуть её (анально)", "Так как ты не можешь дотянуться до своего члена, ты не можешь трахнуть задницу Helena.", null));
								
							} else {
								responses.add(new ResponseSex(
										"Трахнуть её (анально)",
										"Повалить Helena на четвереньки и трахать ей в задницу, пока она отсасывает Scarlett.",
										true, true,
										HelenaHotel.getHelenaSexManager(false,
												true,
												true,
												SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
													:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
												null,
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
													:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)),
														Main.game.getNpc(Scarlett.class).hasVagina()
															?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
															:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_FUCK_HER_ANAL")) { // Player anilingus first
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
										if(Main.game.getNpc(Scarlett.class).hasVagina()) {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										} else {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
										}
										return list;
									}
								});
							}
						}
					}
				}
				
			} else if(responseTab==1) { // Scarlett focus:
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Минет", "Так как твой рот занят, ты не можешь отсосать Scarlett!", null));
					} else {
						responses.add(new ResponseSex(
								"Минет",
								"Встать на колени вместе с Helena и отсосать Scarlett.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										true,
										false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL_TWO,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_DOUBLE_BLOWJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								return list;
							}
						});
					}
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Куннилингус", "Так как твой рот занят, ты не можешь отлизать Scarlett!", null));
					} else {
						responses.add(new ResponseSex(
								"Куннилингус",
								"Встать на колени вместе с Helena перед Scarlett и ласкать её киску языком.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										true,
										false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL_TWO,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_DOUBLE_CUNNILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START_ADDITIONAL, false, true));
								return list;
							}
						});
					}
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Анилингус", "Так как твой рот занят, ты не можешь отлизать Scarlett в анус!", null));
					} else {
						responses.add(new ResponseSex(
								"Анилингус",
								"Заставить Scarlett сесть тебе на лицо, чтобы ты мог отлизать ей анус, пока она отсасывает Helena.",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										true,
										false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.BESIDE, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueAnus.ANILINGUS_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
								return list;
							}
						});
					}
				}
				
				// Penetrative sex:

				if(Main.game.getPlayer().hasPenis()) {
					if(Main.game.getNpc(Scarlett.class).hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Оседлать", "Так как к твоему члену нет доступа, ты не можешь трахать Scarlett.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Оседлать",
									"Заставить Scarlett оседлать твой член, пока Helena сидит у тебя на лице.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}

				if(Main.game.getNpc(Scarlett.class).hasVagina()) {
					if(Main.game.getPlayer().hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Оседлать", "Так как к твоему члену нет доступа, ты не можешь трахать Scarlett.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Оседлать",
									"Заставить Scarlett оседлать твой член, пока Helena сидит у тебя на лице.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}

				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(Main.game.getPlayer().hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							responses.add(new Response("Трахнутый", "Поскольку у тебя нет доступа к своей киске, Scarlett не может трахнуть тебя в неё.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Трахнутый",
									"Заставить Scarlett трахать твою киску, пока ты занимаешься куннилингусом для Helena.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_FUCKED")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							responses.add(new Response("Трахали (анально)", "Поскольку у тебя нет доступа к своей заднице, Scarlett не может трахнуть тебя в неё.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Трахали (анально)",
									"Заставить Scarlett трахать твою задницу, пока ты занимаешься куннилингусом для Helena.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_FUCKED_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME = new DialogueNode("Завершено", "Helena закончила и ей нужно вернуться к работе.", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
			Main.game.getNpc(Scarlett.class).cleanAllClothing(true, false);
			Main.game.getNpc(Scarlett.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	
	// Scarlett:
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Сказать Scarlett, что она может возвращаться к работе.", HELENAS_SHOP_SCARLETT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_END"));
					}
				};
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettTalkedTo)) {
					return new Response("Поговорить", "Вы уже провели какое-то время, болтая с Scarlett...", null);
				}
				return new Response("Поговорить", "Спросить Scarlett, как у неё дела в последнее время.", HELENAS_SHOP_SCARLETT_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettTalkedTo, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCounterOral)) {
					return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"Минет":"Куннилингус",
							"Ты уже делал Scarlett минет сегодня...",
							null);
				}
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"Минет":"Куннилингус",
							"Scarlett не испытывает к тебе влечения, поэтому она неохотно позволит тебе сделать ей "+(Main.game.getNpc(Scarlett.class).hasPenis()?"быстрый минет":"быстрый куннилингус")+".",
							null);
				}
				return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"Минет":"Куннилингус",
						"Опуститься на колени под прилавок и заняться с Scarlett "+(Main.game.getNpc(Scarlett.class).hasPenis()?"быстрый минет":"быстрым куннилингусом")+".",
						HELENAS_SHOP_SCARLETT_COUNTER_ORAL) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCounterOral, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==5) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Кафе"
								:"Обеденный перерыв",
							"Scarlett готова проводить обеденный перерыв только с теми, кто ей симпатичен, а так как ты недостаточно женственен для её вкуса, она не желает проводить его с тобой...",
							null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafe)) {
					return new Response("Кафе", "Вы уже ходили в кафе с Scarlett сегодня...", null);
					
				} else if(Main.game.getHourOfDay()<11) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Кафе"
								:"Обеденный перерыв",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Для Scarlett еще слишком рано обедать, поэтому она не может пойти с тобой в кафе. Попробуй снова между [units.time(11)] и [units.time(15)]."
								:"Для Scarlett еще слишком рано обедать. Попробуй снова между [units.time(11)] и [units.time(15)].",
							null);
					
				} else if(Main.game.getHourOfDay()>15) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Кафе"
								:"Обеденный перерыв",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Scarlett уже ушла на обеденный перерыв, так что она не сможет пойти с тобой в кафе. Попробуй зайти в другой день между [units.time(11)] и [units.time(15)]."
								:"Scarlett уже ушла на обед, поэтому не может провести его с тобой. Попробуй зайти в другой день между [units.time(11)] и [units.time(15)].",
							null);
					
				}
				
				return new Response(
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
							?"Кафе"
							:"Обеденный перерыв",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
							?"Спросить Scarlett, не хочет ли она снова провести обеденный перерыв с тобой в кафе."
							:"Спросить Scarlett, не хочет ли она провести обеденный перерыв с тобой.",
						HELENAS_SHOP_SCARLETT_CAFE);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_COUNTER_ORAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"Сосать член",
							"Сделать так, как говорит Scarlett, и отсосать ей...",
							true,
							false,
							new SMScarlettShopOral(),
							null,
							null,
							HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"Отсосать ей",
							"Сделать так, как говорит Scarlett, и отлизать ей...",
							true,
							false,
							new SMScarlettShopOral(),
							null,
							null,
							HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL = new DialogueNode("Завершено", "Scarlett довольна и отходит от прилавка...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
				if(index==1) {
					return new Response("Кафе", "Позволить Scarlett вести тебя в кафе, в которое она хочет пойти.", HELENAS_SHOP_SCARLETT_CAFE_ARRIVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_CORE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<150) {
						return new Response("Заплатить ("+UtilText.formatAsMoneyUncoloured(150, "охват")+")", "У тебя недостаточно денег, чтобы оплатить обед для Scarlett...", null);
					}
					return new Response("Заплатить ("+UtilText.formatAsMoney(150, "охват")+")", "Сказать Scarlett, что ты готов оплатить ей обед.", HELENAS_SHOP_SCARLETT_CAFE_ARRIVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_PAY"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_CORE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-150));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else if(index==2) {
					return new Response("Отказаться", "Отказаться платить за обед Scarlett.", HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY);
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_ARRIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_CAFE, false);
			Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCafe, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed, true);
		}
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
				if(Main.game.getPlayer().getMoney()<150) {
					return new Response("Заказать ("+UtilText.formatAsMoneyUncoloured(150, "охват")+")", "У вас не хватает денег, чтобы заказать обед...", null);
				}
				return new Response("Заказать ("+UtilText.formatAsMoney(150, "охват")+")", "Заказать тот же набор сэндвичей, что и Scarlett.", HELENAS_SHOP_SCARLETT_CAFE_EATING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_ARRIVE_EATING_LUNCH"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-150));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Ничего не заказывать", "Ничего не заказывать, а просто сидеть и болтать с Scarlett, пока она ест.", HELENAS_SHOP_SCARLETT_CAFE_EATING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_ARRIVE_NO_LUNCH"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_EATING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_DISCUSSION");
				
			} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_DISCUSSION_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_APPLIED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
				return HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("Отговорить",
							"Отговорить Scarlett от того, чтобы пить зелье, по крайней мере сейчас.<br/>[style.italics(Вы сможете уговорить её выпить его в другой раз, если позже передумаете.)]",
							HELENAS_SHOP_SCARLETT_CAFE_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_NO_TRANSFORMATION"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
						}
					};
					
				} else if(index==2) {
					return new Response("Выпить",
							"Подбодрить Scarlett, чтобы она выпила зелье.<br/>"
							+ (Main.game.getNpc(Scarlett.class).hasVagina()
								?"[style.italicsTfSex(Это увеличит грудь Scarlett на один размер (до "+CupSize.getCupSizeFromInt(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+1)+"-го размера) и сделает её более женственной!)]"
								:"[style.italicsTfSex(Это увеличит выработку спермы у Scarlett, а также сделает её член толще и длиннее, доведя его до [style.sizes(20)]!)]"),
							HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED"));
							
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								Main.game.getNpc(Scarlett.class).setFemininity(90);
								Main.game.getNpc(Scarlett.class).setBreastSize(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+1);
								Main.game.getNpc(Scarlett.class).setVaginaWetness(Wetness.FIVE_SLOPPY);
								
							} else {
								Main.game.getNpc(Scarlett.class).setPenisSize(20);
								Main.game.getNpc(Scarlett.class).setPenisGirth(PenetrationGirth.THREE_AVERAGE);
								Main.game.getNpc(Scarlett.class).setPenisCumStorage(50);
							}

							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_CORE"));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getPlayer().getEssenceCount()<10) {
						return new Response("Усилить (10 эссенций)", "Вам нужно как минимум десять эссенций Arcane, чтобы усилить эффект зелья трансформации!", null);
					}
					return new Response("Усилить ([style.colourArcane(10 эссенций)])",
							"Использовать десять ваших эссенций Arcane, чтобы усилить эффект зелья Scarlett.<br/>"
							+ (Main.game.getNpc(Scarlett.class).hasVagina()
								?"[style.italicsTfSex(Это увеличит грудь Scarlett на два размера (до "+CupSize.getCupSizeFromInt(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+2)+"-го размера), а также сделает её гораздо более женственной!)]"
								:"[style.italicsTfSex(Это увеличит выработку спермы у Scarlett, а также сделает её член толще и длиннее, доведя его до [style.sizes(30)]!)]"),
							HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_EXTRA"));
							
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								Main.game.getNpc(Scarlett.class).setFemininity(95);
								Main.game.getNpc(Scarlett.class).setBreastSize(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+2);
								Main.game.getNpc(Scarlett.class).setVaginaWetness(Wetness.FIVE_SLOPPY);
								
							} else {
								Main.game.getNpc(Scarlett.class).setPenisSize(30);
								Main.game.getNpc(Scarlett.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Scarlett.class).setPenisCumStorage(150);
							}

							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(-10, false));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED = new DialogueNode("", "", true, true) {
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
				return new Response("Отказаться", "Сказать Scarlett, что вы не хотите заниматься с ней чем-то настолько непристойным.", HELENAS_SHOP_SCARLETT_CAFE_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_NO_SEX"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"Ручная стимуляция",
							"Переместиться, чтобы сесть рядом со Scarlett, и заняться её ручным дрочкой...",
							true,
							false,
							getScarlettCafeSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_MASTURBATION_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else {
					return new ResponseSex(
							"Ласкать пальцами",
							"Перемещайтесь, чтобы сесть рядом со Scarlett и начать ласкать её пальцами...",
							true,
							false,
							getScarlettCafeSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
							null,
							null,
							HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_MASTURBATION_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERED_START, false, true));
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX = new DialogueNode("Завершено", "Scarlett удовлетворена и велит вам отойти от неё...", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT_CAFE_END.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_END = new DialogueNode("", "", true, true) {
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
				return new Response("Назад", "Сопровождайте Scarlett обратно в бутик Helena.", HELENAS_SHOP_SCARLETT_CAFE_RETURNED) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false);
						Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false); // Just in case time passed closing time during sex.
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_RETURNED"));
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationHelenaReacted, true);
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_RETURNED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	
}
