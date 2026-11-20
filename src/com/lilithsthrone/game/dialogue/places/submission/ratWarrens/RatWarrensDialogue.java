package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.managers.submission.SMVengarDominantSex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RatWarrensDialogue {
	
	private static NPC gambler;
	private static final int PERSUASION_PAYMENT = 10_000;
	
	public static void init() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, false);
		
		// Spawn guard rats if missing:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE).isEmpty()) {
			try {
				NPC rat = new RatGangMember(Gender.M_P_MALE);
				Main.game.addNPC(rat, false);
				rat.setLevel(10);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				Main.game.getCharacterUtils().setGenericName(rat, "lieutenant", null);
				rat.unequipOffhandWeaponIntoVoid(0, false);
				rat.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.POISON, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GREEN_DRAB, PresetColour.CLOTHING_GUNMETAL)));
				rat.incrementEssenceCount(8, false);
				
				rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(9);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				Main.game.getCharacterUtils().setGenericName(rat, "sidekick", null);
				rat.unequipOffhandWeaponIntoVoid(0, false);
				rat.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_KHAKI, PresetColour.CLOTHING_STEEL)));
				rat.incrementEssenceCount(3, false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Spawn humans:
		if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM).isEmpty()) {
			spawnMilkers();
		}
		
		// Spawn bar-tender:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN).isEmpty()) {
			try {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				if(rat.hasVagina()) {
					rat.setVaginaSquirter(true);
				}
				if(!rat.hasPenis() && !rat.hasVagina()) {
					rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, true));
				}
				Main.game.addNPC(rat, false);
				rat.setLevel(4);
				rat.addFetish(Fetish.FETISH_ORAL_RECEIVING);
				if(rat.hasPenis()) {
					rat.addFetish(Fetish.FETISH_PENIS_GIVING);
				}
				if(rat.hasVagina()) {
					rat.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
				Main.game.getCharacterUtils().setGenericName(rat, rat.isFeminine()?"barwoman":"barman", null);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void exit() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
	}
	
	public static void applyCombatDefeatFlagsReset() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, false);
	}
	
	public static String applyConflictQuestEnd() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
		return Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END);
	}
	
	/**
	 * @return A list with the player's main companion in index 0, with gang members present being in slots after that.
	 */
	public static List<GameCharacter> getGuards(boolean includeCompanion) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || !(npc instanceof RatGangMember));
		Collections.sort(guards, (a, b)->b.getLevel()-a.getLevel());
		if(Main.game.getPlayer().hasCompanions() && includeCompanion) {
			guards.add(0, Main.game.getPlayer().getMainCompanion());
		}
		return guards;
	}
	
	public static List<GameCharacter> getMilkers() {
		List<GameCharacter> milkers = new ArrayList<>();
		for(GameCharacter milker : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM))) {
			if(milker instanceof RatWarrensCaptive && !Main.game.getPlayer().getCompanions().contains(milker)) {
				milkers.add(milker);
			}
		}
		return milkers;
	}
	
	private static void banishGuards(boolean includeLeaderGuards) {
		for(GameCharacter guard : getGuards(false)) {
			if(includeLeaderGuards || guard.getLevel()<9) {
				Main.game.banishNPC((NPC) guard);
				
			} else {
				guard.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
			}
		}
	}
	
	public static void banishTwoGuards() {
		Main.game.banishNPC((NPC) getGuards(false).get(getGuards(false).size()-1));
		Main.game.banishNPC((NPC) getGuards(false).get(getGuards(false).size()-1));
	}
	
	public static void applyRatWarrensRaid() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
		Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);

		((Shadow)Main.game.getNpc(Shadow.class)).moveToBountyHunterLodge();
		((Silence)Main.game.getNpc(Silence.class)).moveToBountyHunterLodge();
		
		Main.game.getNpc(Shadow.class).removeItemByType(ItemType.RESONANCE_STONE);
		
		Main.game.getNpc(Axel.class).addSlave(Main.game.getNpc(Vengar.class));
		Main.game.getNpc(Vengar.class).unequipAllClothingIntoVoid(true, true);
		Main.game.getNpc(Vengar.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Axel.class));
		
		Main.game.getNpc(Axel.class).addSlave(Main.game.getNpc(Murk.class));
		Main.game.getNpc(Murk.class).unequipAllClothingIntoVoid(true, true);
		Main.game.getNpc(Murk.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Axel.class));
		
		Main.game.getNpc(Vengar.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER, true);
		Main.game.getNpc(Murk.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE, true);
		Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -100);
		Main.game.getNpc(Murk.class).setAffection(Main.game.getPlayer(), -100);
		
		Main.game.getPlayer().removeItemByType(ItemType.RESONANCE_STONE);

		List<NPC> ratGuards = new ArrayList<>(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE));
		ratGuards.addAll(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN));
		for(NPC ratGuard : ratGuards) {
			if(ratGuard instanceof RatGangMember && !Main.game.getPlayer().getCompanions().contains(ratGuard)) {
				Main.game.banishNPC(ratGuard);
			}
		}
	}
	
	public static void spawnMilkers() {
		try {
			String[] adjectives = new String[] {"brainwashed", "submissive", "obedient", "docile"};
			for(int i=0;i<4;i++) {
				NPC human = new RatWarrensCaptive(Gender.F_V_B_FEMALE);
				Main.game.addNPC(human, false);
				human.setGenericName(adjectives[i]+" milker");
				human.setAffection(Main.game.getNpc(Murk.class), 100);
				Main.game.getNpc(Murk.class).calculateGenericSexEffects(true, true, human, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.NO_DESCRIPTION_NEEDED);
				Main.game.getNpc(Murk.class).fillCumToMaxStorage();
				human.clearFluidsStored(SexAreaOrifice.VAGINA);
				human.calculateStatusEffects(1);
				AbstractItem milk = Main.game.getItemGen().generateItem(ItemType.MOTHERS_MILK);
				human.useItem(milk, human, false);
				if(human.isPregnant()) {
					if(Math.random()<0.75f) {
						human.useItem(milk, human, false);
					}
					if(Math.random()<0.5f) {
						human.useItem(milk, human, false);
					}
					if(Math.random()<0.25f) {
						human.useItem(milk, human, false);
					}
				}
				human.addMuskMarkerCharacter(Main.game.getNpc(Murk.class));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void banishMilkers() {
		for(GameCharacter milker : getMilkers()) {
			Main.game.banishNPC((NPC) milker);
		}
	}
	
	
	private static void spawnGuards(boolean withLeader, int totalRatsToSpawn) {
		try {
			List<String> adjectives = new ArrayList<>();
			if(withLeader) {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(10);
				rat.setLocation(Main.game.getPlayer(), true);
				rat.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
				rat.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
				rat.setGenericName(Util.randomItemFrom(Util.newArrayListOfValues("buff", "strong", "muscular", "muscly"))+" lieutenant");

				rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(9);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, "sidekick", adjectives));
				
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight, false);
				
			} else {
				for(NPC rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE)) {
					rat.setLocation(Main.game.getPlayer(), false);
				}
				for(NPC rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN)) {
					rat.setLocation(Main.game.getPlayer(), false);
				}
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight, true);
			}
			for(int i=0;i<(withLeader?totalRatsToSpawn-2:totalRatsToSpawn);i++) {
				String[] names = new String[] {"thug", "gangster", "gang-member", "mobster"};
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(i==1, i==2));
				Main.game.addNPC(rat, false);
				rat.setLevel(8-i);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, Util.randomItemFrom(names), adjectives));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static NPC generateGambler(Femininity femininity) {
		NPC gambler = new RatGangMember(Gender.getGenderFromUserPreferences(femininity));
		if(Math.random()<0.5f) { // 50% chance for them to be a cheater
			List<Dice> dice = new ArrayList<>();
			int weightedFace = Util.random.nextInt(6)+1;
			for(int i=0; i<5; i++) {
				dice.add(new Dice(Util.newHashMapOfValues(new Value<>(DiceFace.getFaceFromInt(weightedFace), 5f))));
			}
			gambler.setDice(dice);
		}
		try {
			Main.game.addNPC(gambler, false);
			Main.game.getCharacterUtils().setGenericName(gambler, "gambler", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
		return gambler;
	}

	private static GameCharacter getGuardLeader() {
		if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Murk.class))) {
			return Main.game.getNpc(Murk.class);
		}
		return getGuards(false).get(0);
	}
	
	private static GameCharacter getMainCompanion() {
		if(Main.game.getPlayer().hasCompanions()) {
			return Main.game.getPlayer().getMainCompanion();
		}
		return null;
	}
	
	private static boolean isCompanionDialogue() {
		return getMainCompanion()!=null;
	}
	
	private static String applyCaptivity(GameCharacter character, GameCharacter equipper, Colour collarColour) {
		Main.game.addSavedInventory(character);
		
		int essences = character.getEssenceCount();
		character.setInventory(new CharacterInventory(false, 0));
		character.setEssenceCount(essences);

		Main.game.getPlayer().setCaptive(true);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
		
		return "<p style='text-align:center;'>"+RatWarrensCaptiveDialogue.equipCollar(character, equipper, collarColour)+"</p>";
	}
	
	private static int getRumPrice() {
		return ItemType.getItemTypeFromId("innoxia_race_rat_black_rats_rum").getValue()/2;
	}

	private static boolean isMouthAccess(GameCharacter target) {
		return target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	private static boolean isAssAccess(GameCharacter target) {
		return Main.game.isAnalContentEnabled() && target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true);
	}

	private static boolean isVaginaAccess(GameCharacter target) {
		return target.hasVagina() && target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}
	
	private static String getCooperationWarning() {
		return "<br/>[style.italicsSideQuest(Выбрав этот вариант, вы заблокируете возможность прохождения по силовому пути квеста.)]";
	}
	
	public static final DialogueNode RAT_WARREN_INITIAL_ENTRY = new DialogueNode("Вход", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_WHORE", getGuards(true));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_REPEAT", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INITIAL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) { // They want sex again:
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) { // They let you in again:
				if(index==1) {
					return new Response("Конфиденциально", "Сказать крысам, что ваши дела — это личное, и Венгар рассердится, если они задержат ваш вход.", ENTRANCE_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_REPEAT_ACCESSED", getGuards(true)));
						}
					};
				}
			}
			
			if(index==1) {
				return new Response("Аксель", "Сказать банде, что вы здесь, чтобы обсудить с Венгаром вопрос выплат Акселя.", ENTRANCE_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntry, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_AXEL", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new Response("Шалава", "Сказать банде, что вы — шлюха, нанятая, чтобы ублажать Венгара.", RAT_WARREN_INITIAL_ENTRY_WHORE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntryWhore, true);
					}
				};
				
			} else if(index==3) {
				return new Response("Бросить вызов", "Сказать банде, что вы пришли драться.<br/>[style.italicsBad(Это несомненно приведет к тому, что на подмогу явится целая толпа бандитов!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(false, 1);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode RAT_WARREN_INITIAL_ENTRY_WHORE = new DialogueNode("Вход", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отказаться", "Сказать банде, что Венгар будет расстроен, если не успеет трахнуть вас первым.", ENTRANCE_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_NO_SEX", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						isCompanionDialogue()
							?"Секс (соло)"
							:"Секс",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Сказать банде, что вы с радостью покажете им, на что способны, но [npc.name] прибережет [npc.herself] для Венгара...")
							:"Сказать банде, что вы с радостью покажете им, на что способны...",
						Util.newArrayListOfValues(
								Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null,
						true,
						true,
						new SMGeneric(
								getGuards(false),
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(
										getMainCompanion())) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						AFTER_ENTRANCE_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_SOLO", getGuards(true)));
				
			} else if (index == 3 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Секс (вдвоем)"),
							UtilText.parse(getGuards(true), "Вы видите, что [npc.name] совсем не заинтересована в сексе с [npc2.name] или [npc3.name], и вы не можете заставить [npc.herHim] заняться этим..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Секс (вдвоем)"),
							UtilText.parse(getGuards(true), "Скажите членам банды, что и вы, и [npc.name] будете рады показать им, на что вы способны."),
							Util.newArrayListOfValues(
									Fetish.FETISH_SUBMISSIVE),
							null,
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null,
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companion),
									null,
									null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_BOTH", getGuards(true)));
				}
				
			} else if (index == 4 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
							UtilText.parse(getGuards(true), "Сразу видно, что [npc2.name] совсем не горит желанием трахаться с [npc.name], а заставить [npc2.herHim] ты всё равно не сможешь..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
							UtilText.parse(getMainCompanion(), "Скажите членам банды, что вы будете рады позволить [npc.name] показать им, на что способна [npc.sheIs], но вы собираетесь приберечь себя для Венгара..."),
							Util.newArrayListOfValues(
									Fetish.FETISH_VOYEURIST),
							null,
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null,
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_COMPANION", getGuards(true)));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_ENTRANCE_SEX = new DialogueNode("Завершено", "Члены банды закончили...", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(Main.sex.getSubmissiveSpectators().contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_COMPANION_WATCHING", getGuards(true));
				} else if(Main.sex.getSubmissiveSpectators().contains(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_PLAYER_WATCHING", getGuards(true));
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_BOTH", getGuards(true));
				}
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_SOLO", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE_FIGHT = new DialogueNode("", "", true) {
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
			if(index==1) {
				return new ResponseCombat("Сразиться",
						"Защищайтесь от крыс!",
						(NPC)getGuardLeader(),
						getGuards(false),
						new HashMap<>());
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {
		@Override
		public void applyPreParsingEffects() {
//			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
//					|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT
//					|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT) {
//				Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
//			}
//			banishTwoGuards();
		}
		
		@Override
		public String getContent() {
			if(getGuards(false).size()==1) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ONE_REMAINING", getGuards(false));
				
			} else if(getGuards(false).isEmpty()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ALL");
				
			} else if(getGuards(false).size()<4) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ONE", getGuards(false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY", getGuards(true));
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getGuards(false).isEmpty()) {
				if(index==0) {
					return "Взаимодействие";
					
				} else if(index==1) {
					return "Инвентарь";
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getGuards(false).isEmpty()) {
				if (index == 1) {
					return new Response("Продолжить", "Поскольку вы поработили всех членов банды, которые осмелились сразиться с вами, остается только продолжать свой путь...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ALL_ENSLAVED"));
						}
					};
				}
				return null;
			}
			if(responseTab == 0) {
				if (index == 1) {
					return new Response("Запугать", "Скажите членам банды, чтобы они убирались отсюда, пока еще не поздно...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SCARE_OFF", getGuards(false)));
							banishGuards(true);
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Соло-секс"
								:"Секс",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Скажите [npc.name] отойти в сторону и смотреть, как вы трахаетесь с членами банды.")
								:"Затрахаться с членами банды.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX", getGuards(false)));
					
				} else if (index == 3) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Соло-секс (Нежный)"
								:"Секс (Нежный)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Скажите [npc.name] отойти в сторону и смотреть, как вы трахаетесь с членами банды.")
								:"Затрахаться с членами банды.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GENTLE", getGuards(false)),
							ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
					
				} else if (index == 4) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Соло-секс (Грубый)"
								:"Секс (Грубый)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Скажите [npc.name] отойти в сторону и смотреть, как вы трахаетесь с членами банды.")
								:"Затрахаться с членами банды.",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_ROUGH", getGuards(false)),
							ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
					
				} else if (index == 5) {
					return new ResponseSex(
							isCompanionDialogue()
								?"Соло-сабмиссив"
								:"Подчиниться",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как ты подчиняешься членам банды, позволяя им доминировать над тобой во время секса.")
								:"Сказать членам банды, что они могут делать с тобой всё, что захотят...",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null,
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null,
							true,
							false,
							getGuards(false),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							Util.newArrayListOfValues(getMainCompanion()),
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_SUBMIT", getGuards(false)));
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response("Групповой секс",
								UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с одним или несколькими членами банды, и так как [npc.sheIs] не рабыня, ты не можешь заставить [npc.herHim]..."), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Групповой секс"),
								UtilText.parse(companion, "Заниматься сексом с членами банды, позволяя им доминировать, и уговорить [npc.name] присоединиться к веселью."),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getGuards(false),
								null,
								null,
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GROUP", getGuards(false)));
					}
					
				} else if (index == 7 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response("Групповое подчинение",
								UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с одним или несколькими членами банды, и так как [npc.sheIs] не рабыня, ты не можешь заставить [npc.herHim]..."), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Групповое подчинение"),
								UtilText.parse(companion, "Уговорить [npc.name] вместе с тобой подчиниться членам банды, позволяя им доминировать над вами обеими во время секса."),
								true,
								false,
								getGuards(false),
								Main.game.getPlayer().getParty(),
								null,
								null,
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GROUP_SUBMIT", getGuards(false)));
					}
					
				} else if (index == 8 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "Отдать [npc.name]"),
								UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с одним или несколькими членами банды, и так как [npc.sheIs] не рабыня, ты не можешь заставить [npc.herHim]..."), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Отдать [npc.name]"),
								UtilText.parse(companion, "Сказать [npc.name], что [npc.she] может немного развлечься с членами банды, пока ты наблюдаешь."),
								false,
								false,
								Util.newArrayListOfValues(getMainCompanion()),
								getGuards(false),
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GIVE_TO_COMPANION", getGuards(false)));
					}
					
				} else if (index == 9 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedToGroup(getGuards(false)) && ((companion.isAbleToRefuseSexAsCompanion()) || !Main.game.isNonConEnabled())) {
						return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(companion, "Ты видишь, что [npc.name] совсем не хочет заниматься сексом с членами банды, и ты не можешь заставить [npc.herHim]..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
								UtilText.parse(companion, "Отдай [npc.name] членам банды и наблюдай, как они трахают [npc.herHim]."),
								true,
								false,
								getGuards(false),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_OFFER_COMPANION", getGuards(false))) {
							@Override
							public void effects() {
								if(!companion.isAttractedToGroup(getGuards(false)) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else if(responseTab == 1) {
				for(int i=1; i<=getGuards(false).size(); i++) {
					if(index==i) {
						NPC guard = (NPC) getGuards(false).get(i-1);
						return new ResponseEffectsOnly(UtilText.parse(guard, "[npc.Name]"),
								UtilText.parse(guard, "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]...")) {
							@Override
							public void effects() {
								Main.mainController.openInventory(guard, InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY_AFTER_SEX = new DialogueNode("Отступить", "Теперь, когда ты вдоволь нагулялся, ты отступаешь назад и раздумываешь, что делать с членами банды...", true) {

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
				if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_BOTH", getGuards(true));
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_SOLO", getGuards(true));
				}
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_COMPANION", getGuards(true));
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return GUARD_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getGuards(false).isEmpty()) {
				if (index == 1) {
					return new Response("Продолжить", "Поскольку вы поработили всех членов банды, которые осмелились сразиться с вами, остается только продолжать свой путь...", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ALL_ENSLAVED"));
						}
					};
				}
				return null;
			}
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Запугать", "Напугать членов банды и продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishGuards(true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SCARE_OFF", getGuards(true)));
						}
					};
				}
				
			} else if(responseTab==1) {
				return GUARD_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT = new DialogueNode("Поражение", "", true) {
		@Override
		public String getContent() {
			if(!Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_NO_NON_CON", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("Тебя вышвыривают", "Члены банды бесцеремонно выбрасывают тебя обратно в туннели Submission.", PlaceType.SUBMISSION_RAT_WARREN.getDialogue(false)) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_THROWN_OUT", getGuards(true)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
							applyCombatDefeatFlagsReset();
							List<GameCharacter> guards = getGuards(false);
							for(GameCharacter npc : guards) {
								Main.game.banishNPC((NPC) npc);
							}
							exit();
						}
					};
				}
				
			} else {
				if(index == 1) {
					if(isCompanionDialogue()) {
						return new Response("Сохранить [com.name]",
								"Использовать остатки своей энергии, чтобы сдерживать крыс достаточно долго, чтобы [com.name] успела сбежать.<br/>[style.italicsMinorGood([com.Name] благополучно вернется домой.)]",
								GUARD_COMBAT_DEFEAT_STOCKS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_COMPANION_ESCAPE", getGuards(true)));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS", getGuards(true)));
								
								GameCharacter companion = getMainCompanion();
								Main.game.getPlayer().removeCompanion(companion);
								companion.returnToHome();
								
								List<GameCharacter> guards = getGuards(false);
								int count=0;
								for(GameCharacter npc : guards) {
									count++;
									if(count>1) {
										Main.game.banishNPC((NPC) npc);
										continue;
									}
									npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								}
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
							}
						};
						
					} else {
						return new Response("Утащенная прочь",
								UtilText.parse(getGuards(false), "Ты бессильна сопротивляться, когда члены банды утаскивают тебя всё глубже в их логово..."),
								GUARD_COMBAT_DEFEAT_STOCKS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS", getGuards(true)));
								List<GameCharacter> guards = getGuards(false);
								int count=0;
								for(GameCharacter npc : guards) {
									count++;
									if(count>1) {
										Main.game.banishNPC((NPC) npc);
										continue;
									}
									npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								}
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	private static SexManagerInterface getStocksManager(List<GameCharacter> guards, SexPace pace) {
		return new SexManagerDefault(
				SexPosition.MILKING_STALL,
				Util.newHashMapOfValues(
						new Value<>(guards.get(0), SexSlotMilkingStall.RECEIVING_ORAL),
						isCompanionDialogue()
							?new Value<>(guards.get(1), SexSlotMilkingStall.RECEIVING_ORAL_TWO)
							:null),
				Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL),
						isCompanionDialogue()
							?new Value<>(getMainCompanion(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO)
							:null)) {
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(guards.contains(character)) {
					if(character.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
						
					} else if(character.hasVagina()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
					}
				}
				return character.getForeplayPreference(targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				return character.getForeplayPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
				for(GameCharacter guard : guards) {
					map.put(guard, Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA));
				}
				return map;
			}
			@Override
			public void assignNPCTarget(GameCharacter targeter) {
				if(isCompanionDialogue()) {
					if(targeter.equals(guards.get(0))) {
						Main.sex.setTargetedPartner(targeter, Main.game.getPlayer());
						
					} else if(isCompanionDialogue() && targeter.equals(guards.get(1))) {
						Main.sex.setTargetedPartner(targeter, getMainCompanion());
						
					} else {
						super.assignNPCTarget(targeter);
					}
					
				} else {
					super.assignNPCTarget(targeter);
				}
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer() || character.equals(getMainCompanion())) {
					return SexControl.NONE;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
				if(character.isPlayer() || character.equals(getMainCompanion())) {
					return false;
				}
				return super.isAbleToRemoveOthersClothing(character, clothing);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return !equippingCharacter.isPlayer() && !equippingCharacter.equals(getMainCompanion());
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return !character.isPlayer() && !character.equals(getMainCompanion());
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					return pace;
				}
				return super.getStartingSexPaceModifier(character);
			}
		};
	}
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS = new DialogueNode("", "", true) {
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
			if (index == 1) {
				return new Response("Раздета",
						UtilText.parse(getGuards(false), "Мерк и [npc.name] принимаются раздевать тебя догола..."),
						GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED) {
					@Override
					public void effects() {
						applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Murk.class), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Новый дом",
						UtilText.parse(getGuards(false), "Мерк и [npc.name] ведут тебя в соседнюю комнату, чтобы познакомить с твоим «новым домом»..."),
						GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END) {
					@Override
					public void effects() {
						List<GameCharacter> parsingCharacters = new ArrayList<>(getGuards(true));
						parsingCharacters.addAll(getMilkers());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END", parsingCharacters));
						
						List<GameCharacter> guards = getGuards(true);
						for(GameCharacter npc : guards) {
							npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						}
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
					}
				};
				
			}
			return null;
		}
	};

	private static List<InitialSexActionInformation> getInitialCapturedStartSexActions(List<GameCharacter> guards) {
		List<InitialSexActionInformation> list = new ArrayList<>();
		
		if(guards.get(0).hasPenis()) {
			list.add(new InitialSexActionInformation(guards.get(0), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
			
		} else if(guards.get(0).hasVagina()) {
			list.add(new InitialSexActionInformation(guards.get(0), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
		}

		if(isCompanionDialogue()) {
			if(guards.get(1).hasPenis()) {
				list.add(new InitialSexActionInformation(guards.get(1), getMainCompanion(), PenisMouth.BLOWJOB_START, false, true));
				
			} else if(guards.get(1).hasVagina()) {
				list.add(new InitialSexActionInformation(guards.get(1), getMainCompanion(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
			}
		}
		
		return list;
	}
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> guards = getGuards(false);
			
			if (index == 1) {
				return new ResponseSex("Оральный",
						UtilText.parse(getGuards(false), "Сделай то, чего от тебя ждут, и послушно отсоси [npc.name]."),
						false,
						false,
						getStocksManager(guards, null),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("С готовностью к оралу",
						UtilText.parse(getGuards(false), "Покажи всем, как сильно ты жаждешь послушно отсосать [npc.name]."),
						false,
						false,
						getStocksManager(guards, SexPace.SUB_EAGER),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX_EAGER", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("Сопротивляться оралу",
						UtilText.parse(getGuards(false), "Сделай всё возможное, чтобы сопротивляться оральному сексу с [npc.name]."),
						false,
						false,
						getStocksManager(guards, SexPace.SUB_RESISTING),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX_RESIST", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return UtilText.parse(getGuards(false).get(0), "[npc.Name] закончил возиться с тобой [npc.her]...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Мерк", "Мерк снова появляется, чтобы рассказать, что ждет тебя дальше...", RatWarrensCaptiveDialogue.CAPTIVE_DAY_0) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0", getGuards(false)));
						banishGuards(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRANCE_NO_CONTENT = new DialogueNode("Вход", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Вход", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
					return new Response("Уйти", "Крысы запечатали двери, преграждая путь к отступлению! Чтобы выбраться отсюда, тебе придется бросить вызов Венгару...", null);
				}
				return new Response("Уйти", "Скажи членам банды, что уходишь, и направляйся обратно в туннели Submission.", SubmissionGenericPlaces.RAT_WARREN) {
					@Override
					public void effects() {
						exit();
					}
				};
				
			} else if(index==2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==6
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
				return new Response("Бросить вызов", "Сказать банде, что вы пришли драться.<br/>[style.italicsBad(Это несомненно приведет к тому, что на подмогу явится целая толпа бандитов!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(false, 1);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Извилистый проход", "", false) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					spawnGuards(false, 1);
					
				} else {
					spawnGuards(true, 4);
				}
				
			} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
					spawnGuards(false, 1);
					
				} else {
					spawnGuards(true, 4);
				}
			}
		}
		@Override
		public boolean isTravelDisabled() {
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))
				|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))) {
				return true;
			}
			return false;
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CHECKPOINT_FIGHT", getGuards(true)));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
					
				} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CHECKPOINT_FIGHT", getGuards(true)));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
					
				} else {
					if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
							|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_RIGHT && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight))
							|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
				}
				
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_HOSTILE_WARNING", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR", getGuards(true)));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))
				|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))) {
				if(index==1) {
					return new ResponseCombat("Сразиться",
							"Члены банды не собираются отпускать тебя без боя, так что тебе приходится защищаться!",
							(NPC)getGuardLeader(),
							getGuards(false),
							new HashMap<>());
				}
			}
			return null;
		}
	};

	public static final DialogueNode DORMITORY = new DialogueNode("Спальня", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY", getGuards(true)));
			
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
					|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_RIGHT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight))) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_OCCUPIED", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_CLEARED", getGuards(true)));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& ((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
							|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_RIGHT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)))) {
				return new Response("Бросить вызов", "Сказать банде, что вы пришли драться.<br/>[style.italicsBad(Это несомненно приведет к тому, что на подмогу явится целая толпа бандитов!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(true, 4);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DICE_DEN = new DialogueNode("Логово Костей", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_ENTRY", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensLootedDiceDen)) {
						return new Response("Обыскать бар", "Вы уже забрали всё ценное из-за барной стойки...", null);
						
					} else {
						return new Response("Обыскать бар", "Загляните за барную стойку и посмотрите, нет ли там чего-нибудь стоящего...", DICE_DEN_LOOT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensLootedDiceDen, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(1500));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), 5, false, true));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), 2, false, true));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy"), 1, false, true));
							}
						};
					}
				}
				
			} else {
				DicePokerTable table = DicePokerTable.COPPER;
				int buyIn = table.getInitialBet()+table.getRaiseAmount();
				
				if(index==1) {
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("[style.colourMasculine(Крысопацан)] ("+UtilText.formatAsMoney(buyIn, "охват")+")",
								"Начать играть в костяной покер с одним из крысопацанов. Взнос составляет "+UtilText.formatAsMoney(table.getInitialBet(), "охват")
									+", но вам также понадобятся "+UtilText.formatAsMoney(table.getRaiseAmount(), "охват")+" для любых повышений ставок.") {
							@Override
							public void effects() {
								gambler = generateGambler(Femininity.MASCULINE);
								gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN_POST_GAMBLING, "places/submission/ratWarrens/dicePoker")));
							}
						};
						
					} else {
						return new Response("Крысопацан ("+UtilText.formatAsMoneyUncoloured(buyIn, "охват")+")",
								"Взнос составляет "+UtilText.formatAsMoney(table.getInitialBet(), "охват")
								+", но вам также понадобятся "+UtilText.formatAsMoney(table.getRaiseAmount(), "охват")+" для любых повышений. В итоге у вас недостаточно денег, чтобы играть с крысопацаном!",
								null);
					}
					
				} else if(index==2) {
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("[style.colourFeminine(Крысодевка)] ("+UtilText.formatAsMoney(buyIn, "охват")+")",
								"Начать играть в костяной покер с одной из крысодевок. Взнос составляет "+UtilText.formatAsMoney(table.getInitialBet(), "охват")
									+", но вам также понадобятся "+UtilText.formatAsMoney(table.getRaiseAmount(), "охват")+" для любых повышений ставок.") {
							@Override
							public void effects() {
								gambler = generateGambler(Femininity.FEMININE);
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN_POST_GAMBLING, "places/submission/ratWarrens/dicePoker")));
							}
						};
						
					} else {
						return new Response("Крысодевка ("+UtilText.formatAsMoneyUncoloured(buyIn, "охват")+")",
								"Взнос составляет "+UtilText.formatAsMoney(table.getInitialBet(), "охват")
								+", но вам также понадобятся "+UtilText.formatAsMoney(table.getRaiseAmount(), "охват")+" для любых повышений. В итоге у вас недостаточно денег, чтобы играть с крысодевкой!",
								null);
					}
					
				} else if(index==3) {
					return new Response("Правила", UtilText.parse(getGuards(false).get(0), "Спросить у [npc.name], каковы правила костяного покера."), DICE_DEN_RULES);

				} else if(index==4) {
					int price = getRumPrice();
					if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH)) {
						return new Response("Ром ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Ты не можешь выпить стакан рома, так как твой рот забит...", null);
					}
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Ром ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")", "Тебе не хватает средств, чтобы купить стакан рома у крысы за барной стойкой...", null);
					}
					return new Response("Ром ("+UtilText.formatAsMoney(price, "охват")+")", "Купить стакан рома у крысы за барной стойкой.", DICE_DEN_RUM) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_DRINK", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_OFFER", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(
									Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
									+ Main.game.getPlayer().incrementMoney(-price));
						}
					};
					
				} else if(index==5 && isCompanionDialogue()) {
					int price = getRumPrice();
					if(!getMainCompanion().isCoverableAreaExposed(CoverableArea.MOUTH)) {
						return new Response("Ром ("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")",
								UtilText.parse(getMainCompanion(), "[npc.Name] не может выпить стакан рома, так как рот [npc.her] забит..."),
								null);
					}
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("Ром ("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoneyUncoloured(price, "охват")+")",
								UtilText.parse(getMainCompanion(), "Тебе не хватает средств, чтобы купить стакан рома для [npc.name] у крысы за барной стойкой..."),
								null);
					}
					return new Response("Ром ("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoney(price, "охват")+")",
							UtilText.parse(getMainCompanion(), "Купить стакан рома для [npc.name] у крысы за барной стойкой."),
							DICE_DEN_RUM) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_DRINK_COMPANION", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_OFFER", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(
									Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(getMainCompanion(), getMainCompanion())
									+ Main.game.getPlayer().incrementMoney(-price));
						}
					};
					
				} else if(index==6
						&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("Бросить вызов", "Сказать банде, что вы пришли драться.<br/>[style.italicsBad(Это несомненно приведет к тому, что на подмогу явится целая толпа бандитов!)]", ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							spawnGuards(false, 1); // Set to 1 as the bar-tender makes the 2nd, and the entrance guards make 3rd and 4th
							for(GameCharacter rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE)) {
								rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN);
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_CHALLENGE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DICE_DEN_LOOT = new DialogueNode("Логово Костей", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_LOOT", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DICE_DEN_RULES = new DialogueNode("Логово Костей", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RULES", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("Правила", UtilText.parse(getGuards(false).get(0), "Ты уже спрашиваешь [npc.name] о правилах костяного покера."), DICE_DEN_RULES);
			}
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DICE_DEN_POST_GAMBLING = new DialogueNode("Логово Костей", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.banishNPC(gambler);
			gambler = null;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_POST_GAMBLING", getGuards(true)));
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DICE_DEN_RUM = new DialogueNode("Логово Костей", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC bartender = (NPC) getGuards(false).get(0);
			if(index==1) {
				return new Response("Отказаться", UtilText.parse(bartender, "Отклонить предложение [npc.namePos]..."), DICE_DEN);
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Принять", UtilText.parse(bartender, "Поскольку ты не можешь пользоваться ртом, ты не можешь заняться оральным сексом с [npc.name], чтобы вернуть свои огни..."), null);
				}
				return new ResponseSex(
						"Принять",
						UtilText.parse(bartender, "Получить оплату "+getRumPrice()+" огнями за минет [npc.name]..."),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(bartender, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(!Main.sex.isDom(character)) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return Main.sex.getNumberOfOrgasms(bartender)>=bartender.getOrgasmsBeforeSatisfied();
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return false;
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(bartender.hasPenis()) {
									return Util.newHashMapOfValues(
											new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
								} else {
									return Util.newHashMapOfValues(
											new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
								}
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(character.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}	
						},
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						AFTER_DICE_DEN_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_ORAL", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(bartender.hasPenis()) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
						}
					}
				};
				
			} else if (index==3 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Предложить [com.name]", UtilText.parse(companion, bartender, "Так как [npc.name] не может добраться до рта [npc.her], [npc.she] не может сделать минет [npc2.name], чтобы вернуть твои огни..."), null);
				}
				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
							UtilText.parse(companion, bartender, "Сразу видно, что [npc.name] совсем не горит желанием делать минет [npc2.name], а заставить [npc.herHim] ты тоже не можешь..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
									UtilText.parse(companion, bartender, "Получить оплату "+getRumPrice()+" огнями за то, чтобы [npc.name] сделала минет [npc2.name]..."),
									true,
									false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(bartender, SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(companion, SexSlotStanding.PERFORMING_ORAL))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(!Main.sex.isDom(character)) {
												return SexControl.ONGOING_ONLY;
											}
											return super.getSexControl(character);
										}
										@Override
										public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
											return false;
										}
										@Override
										public boolean isPositionChangingAllowed(GameCharacter character) {
											return false;
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return Main.sex.getNumberOfOrgasms(bartender)>=bartender.getOrgasmsBeforeSatisfied();
										}
										@Override
										public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
											return false;
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											if(bartender.hasPenis()) {
												return Util.newHashMapOfValues(
														new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.PENIS)),
														new Value<>(companion, Util.newArrayListOfValues(CoverableArea.MOUTH)));
											} else {
												return Util.newHashMapOfValues(
														new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.VAGINA)),
														new Value<>(companion, Util.newArrayListOfValues(CoverableArea.MOUTH)));
											}
										}
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(!character.equals(companion)) {
												if(character.hasPenis()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											}
											return super.getForeplayPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getForeplayPreference(character, targetedCharacter);
										}
										@Override
										public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
											return new ArrayList<>();
										}	
									},
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_DICE_DEN_ORAL,
									UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_ORAL_COMPANION", getGuards(true))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									if(bartender.hasPenis()) {
										return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, companion, PenisMouth.BLOWJOB_START, true, true));
									} else {
										return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, companion, TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
									}
								}
							};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DICE_DEN_ORAL = new DialogueNode("Завершено", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(getRumPrice()));
		}
		@Override
		public String getDescription() {
			return UtilText.parse((NPC) getGuards(false).get(0), "[npc.Name] закончила...");
		}
		@Override
		public String getContent() {
			if(Main.sex.getSubmissiveParticipants(false).containsKey(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_DICE_DEN_ORAL_COMPANION", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_DICE_DEN_ORAL", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MILKING_STORAGE = new DialogueNode("Вход", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM).isTravelledTo()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
			}
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isCaptive()) {
				return 0; // So that the player can't advance days by repeatedly moving back and forth.
			}
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_QUEST_COMPLETE");
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CLEARED", getGuards(true));
			}
			if(Main.game.getPlayer().isCaptive()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CAPTIVE", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			if(Main.game.getPlayer().isCaptive()) {
				if(index==1) {
					return new Response("Отступить", "Цепь, сковывающая твои движения, не дает тебе зайти слишком далеко...", RatWarrensCaptiveDialogue.CAPTIVE_NIGHT) {
						@Override
						public int getSecondsPassed() {
							return 0; // So that the player can't advance days by repeatedly moving back and forth.
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM, false);
						}
					};
				}
				
			} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(index==1) {
					return new Response("Отступить", "Сделай как говорит Мёрк, и уходи.", CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
						}
					};
					
				} else if(index==2) {
					if(Main.game.getHourOfDay()<14 || Main.game.getHourOfDay()>=22) {
						return new Response("Доярки ("+UtilText.formatAsMoneyUncoloured(500, "охват")+")",
								"Мерк сдает свои молочные железы в аренду только между [style.time(14)] и [style.time(22)], так что если вы хотите нанести им визит, вам придется вернуться в это время.",
								null);
					}
					return new Response("Доярки ("+UtilText.formatAsMoney(500, "охват")+")", "Заплатить Мерку 500 огней, чтобы получить доступ к его «молочным железам».", MILKING_ROOM) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-500));
							Main.game.getNpc(Murk.class).incrementMoney(500);
							for(GameCharacter milker : getMilkers()) {
								((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
							}
						}
					};
					
				} else if(index==6
						&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("Бросить вызов", "Сказать Мерку, что вы пришли сражаться.<br/>[style.italicsBad(Это несомненно приведет к тому, что на подмогу явится целая толпа членов банды!)]", ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							spawnGuards(true, 4);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CHALLENGE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode MILKING_ROOM = new DialogueNode("Доильный зал", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_QUEST_COMPLETE");
			}
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM", getMilkers());
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CLEARED", getMilkers());
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			if(isCompanionDialogue()) {
				switch(index) {
					case 0:
						return "Вы";
					case 1:
						return UtilText.parse(getMainCompanion(), "[npc.Name]");
					case 2:
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
							return "Оба";
						}
						break;
				}
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			List<GameCharacter> milkers = getMilkers();
			if(responseTab==0) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						return new ResponseSex(
								"Использовать "+milker.getName(true),
								UtilText.parse(milker, "Решить заняться сексом с [npc.name]..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
										return false;
									}
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Main.game.getPlayer().getParty(),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
						
					} else {
						return new ResponseSex(
								"Использовать "+milker.getName(true),
								UtilText.parse(milker, "Теперь, когда вы победили Мерка и членов банды в этой области, ничто не мешает вам заняться сексом с [npc.name]..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Main.game.getPlayer().getParty(),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
					}
				}
				
			} else if(responseTab==1) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					
					if(!getMainCompanion().isAttractedTo(milker) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker,
										"Вы видите, что [npc.name] совсем не заинтересована в сексе с [npc2.name], и вы не можете заставить [npc.herHim] сделать это..."),
								null);
					}
					
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						return new ResponseSex(
								UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker, "Приказать [npc.name] трахнуть [npc2.name] под вашим с Мерком наблюдением..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
										return false;
									}
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_COMPANION", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
						
					} else {
						return new ResponseSex(
								UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker, "Теперь, когда ты одолел Мёрка и членов его банды в этой области, никто не помешает тебе приказать [npc.name] заняться сексом с [npc2.name] на твоих глазах..."),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_COMPANION_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
					}
				}
				
			} else if(responseTab==2) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					
					if(!getMainCompanion().isAttractedTo(milker) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(milker, "Оба ([npc.Name])"),
								UtilText.parse(getMainCompanion(), milker,
										"Вы видите, что [npc.name] совсем не заинтересована в сексе с [npc2.name], и вы не можете заставить [npc.herHim] сделать это..."),
								null);
					}
					
					return new ResponseSex(
							UtilText.parse(milker, "Оба ([npc.Name])"),
							UtilText.parse(getMainCompanion(), milker, "Теперь, когда ты одолел Мёрка и членов его банды в этой области, никто не помешает тебе и [npc.name] заняться сексом с [npc2.name]..."),
							true,
							false,
							new SMMilkingStall(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL),
											new Value<>(getMainCompanion(), SexSlotMilkingStall.RECEIVING_ORAL)),
									Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
									if(character instanceof RatWarrensCaptive) {
										return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
									} else {
										return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
									}
								}
							},
							null,
							null,
							AFTER_MILKER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_BOTH_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						}
					};
				}
			}
			
			if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensMilkersFreeAttempt)) {
						return new Response(
								"Освободить пленников",
								"Ты уже пытался освободить доярок, но обнаружил, что они совершенно не хотят сбегать вместе с тобой...",
								null);
					}
					return new Response(
							"Освободить пленников",
							"Теперь, когда ты одолел членов банды в этой области, никто не помешает тебе освободить плененных людей...",
							MILKING_ROOM_FREE_ATTEMPT) {
						@Override
						public void effects() {
							 Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensMilkersFreeAttempt, true);
						}
					};
					
				} else {
					return new Response(
							"Доярки",
							"Спросить Мёрка, как он заполучил этих «доярок»...",
							MILKING_ROOM_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensMilkersBackground, true);
						}
					};
				}
				
			} else if(index==6
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return new Response("Сразиться с Мёрком", "Сказать Мёрку, что ты пришел сразиться с ним.<br/>[style.italicsBad(Это несомненно приведет к тому, что на помощь явится целая толпа членов банды!)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
						spawnGuards(true, 4);
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE_CORE", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
				
			} else if(index==0 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return new Response("Уйти", "Решить не заниматься сексом ни с одной из этих «кормилиц» и уйти...<br/>[style.italicsBad(Деньги тебе не вернут!)]", MILKING_ROOM_BACKED_OUT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
						for(GameCharacter milker : getMilkers()) {
							((RatWarrensCaptive)milker).applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MILKING_ROOM_BACKED_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_BACK_OUT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILKING_STORAGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_MILKER_SEX = new DialogueNode("Завершено", "Отступить на шаг и решить, что делать теперь, когда ты вдоволь нагулялся...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
			Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			List<GameCharacter> characters = new ArrayList<>();
			if(isCompanionDialogue()) {
				characters.add(getMainCompanion());
			}
			characters.add(Main.sex.getSubmissiveParticipants(false).keySet().iterator().next());
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX", characters);
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX_CLEARED", characters);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(index==6 && !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("Сразиться с Мёрком",
							"Сказать Мёрку, что теперь эти девки принадлежат тебе и ты собираешься поставить его на место.<br/>[style.italicsBad(Это несомненно приведет к тому, что на помощь явится целая толпа членов банды!)]",
							ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							spawnGuards(true, 4);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX_CHALLENGE", getGuards(true)));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE_CORE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
				return MILKING_STORAGE.getResponse(responseTab, index);
			} else {
				return MILKING_ROOM.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode MILKING_ROOM_BACKGROUND = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_BACKGROUND", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response(
						"Доярки",
						"Ты уже расспрашиваешь Мёрка о том, как он заполучил этих «кормилиц»!",
						null);
			}
			return MILKING_ROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MILKING_ROOM_FREE_ATTEMPT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_FREE_ATTEMPT", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILKING_ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VENGARS_HALL = new DialogueNode("Зал Венгара", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подойти",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
							?"Подойти к Венгару и приготовиться к бою."
							:(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)
								?"Подойти к Венгару и заговорить с ним."
								:"Подойти к Венгару и представиться ему."),
						VENGARS_HALL_APPROACH) {
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
							if(!Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)) {
								if(isCompanionDialogue()) {
									if(!getMainCompanion().hasTraitActivated(Perk.OBSERVANT)) {
										Main.game.getTextStartStringBuilder().append(getMainCompanion().incrementHealth(-Main.game.getPlayer().getHealth()*0.75f));
									}
									
								} else {
									Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getHealth()/2));
								}
							}
						}
					}
				};
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)) {
				if(index==0) {
					return new Response("Уйти", "Решить не приближаться к Венгару, а вместо этого развернуться и выйти из зала.", PlaceType.RAT_WARRENS_CORRIDOR_RIGHT.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						}
					};
				}
				
			} else {
				if(index==2 && Main.game.getPlayer().hasItemType(ItemType.RESONANCE_STONE)) {
					return new Response("Резонансный камень", "Использовать резонансный камень, чтобы подать сигнал карателям SWORD начать налет.", VENGARS_HALL_RESONANCE_STONE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensUsedResonanceStone, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			// Repeat encounters (after initial quest is resolved):
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)) {
				if(index==1) {
					return new Response("Спальня",
							"Присоединиться к Венгару в его личных покоях...",
							VENGARS_BEDROOM) {
						@Override
						public boolean isSexHighlight() {
							return true;	
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
							Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
							}
						}
					};
					
				} else if(index==2 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)) {
					if(!Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine()
							&& Main.game.getPlayer().isFeminine()
							&& !Main.game.isVoluntaryNTREnabled()) {
						return new Response("Лекса", "Лекса не испытывает к тебе влечения, так что во время визита к Венгару заняться с ней сексом не получится...", null);
					}
					return new Response("Лекса", "Вместе с Венгаром отправиться к Лексе с незапланированным визитом...", LEXA_VISIT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							Main.game.getNpc(Vengar.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
							}
						}
					};
					
				} else if(index==0) {
					return new Response("Уйти", "Сказать Венгару, что сейчас тебе ничего не нужно, но ты, возможно, заглянешь в другой раз.", PlaceType.RAT_WARRENS_CORRIDOR_RIGHT.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						}
					};
				}
				
			} else {
				if(index==1) {
					// Fight
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response(
								"Приготовиться",
								"Приготовиться к обороне!",
								VENGARS_HALL_FIGHT_NO_CONTENT) {
							@Override
							public void effects() {
								boolean surprised = !isCompanionDialogue() && !Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT);
								boolean companionSurprised = isCompanionDialogue() && !Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) && !getMainCompanion().hasTraitActivated(Perk.OBSERVANT);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								if(surprised) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT_SURPRISED", getGuards(true)));
								} else if(companionSurprised) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT_SURPRISED_COMPANION", getGuards(true)));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT", getGuards(true)));
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
							}
							@Override
							public boolean isCombatHighlight() {
								return true;
							}
						};
					}
					return new Response("Бросить вызов", "Сказать Венгару, что ты здесь, чтобы покончить с ним и его бандой.", VENGARS_HALL_FIGHT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_CHALLENGE", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("Убедить", "Венгар считает тебя врагом, поэтому он не станет слушать всё, что ты захочешь сказать...", null);
					}
					return new Response("Убедить", "Попытаться убедить Венгара перестать вымогать деньги у Акселя...", VENGARS_HALL_APPROACH_PERSUADE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarPersuaded, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("Угрожать", "У вас нет возможности припугнуть Венгара, так как вам приходится иметь дело с его телохранителями!", null);
					}
					if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)
							|| Main.game.getPlayer().getRace()==Race.DEMON
							|| Main.game.getPlayer().getLevel()>=35) {
						return new Response("Угрожать",
								"Припугнуть Венгара, что заставит его сражаться с вами напрямую, не полагаясь на телохранителей.<br/>[style.italicsMinorGood(Открыто "
											+(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)
													?"из-за поведения чуни)"
													:(Main.game.getPlayer().getRace()==Race.DEMON
														?"из-за того, что вы демон"
														:"из-за того, что ваш уровень не ниже 35)"))
											+".)]",
								VENGARS_HALL_APPROACH_THREATEN) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarThreatened, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
							}
						};
					}
					return new Response("Угрожать",
							"Вам не удалось запугать Венгара, чтобы он прекратил вымогать деньги у Акселя.<br/>[style.italicsMinorBad(Чтобы успешно припугнуть его, ваш уровень должен быть как минимум вдвое выше уровня Венгара, вы должны быть демоном или иметь активный перк '"+Perk.CHUUNI.getName(Main.game.getPlayer())+"(активен перк, чтобы успешно припугнуть его.)]",
							null);
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("Соблазнить", "Венгар считает вас врагом, поэтому он не обратит внимания на любые попытки соблазнить его...", null);
					}
					if(Main.game.getPlayer().hasTraitActivated(Perk.CONVINCING_REQUESTS)
							|| Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)
							|| Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST)>=75) {
						return new Response("Соблазнить",
								"Соблазнить Венгара, пытаясь убедить его перестать вымогать деньги у Акселя.<br/>[style.italicsMinorGood(Открыто благодаря наличию '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+" черта активен.)]"
										+getCooperationWarning(),
								VENGARS_HALL_APPROACH_SEDUCE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarSeduced, true);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
								for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
									character.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
							}
						};
					}
					return new Response("Соблазнить",
							"Вы недостаточно искусны в искусстве соблазнения, чтобы убедить Венгара перестать таким образом вымогать деньги за защиту у Акселя.<br/>[style.italicsMinorBad(Требуется активная черта '"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"', как минимум 75 ед. урона похоти или улучшение '"+SpellUpgrade.TELEPATHIC_COMMUNICATION_3.getName()+"' для заклинания '"+Spell.TELEPATHIC_COMMUNICATION.getName()+"' заклинание.)]",
							null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_FIGHT_NO_CONTENT = new DialogueNode("", "", true, true) {
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
				return new ResponseCombat("Сразиться",
						"Сразиться с крысино-девичьими телохранительницами Венгара.",
						Main.game.getNpc(Shadow.class),
						Util.newArrayListOfValues(Main.game.getNpc(Shadow.class), Main.game.getNpc(Silence.class)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(Shadow.class), ""),
								new Value<>(Main.game.getNpc(Silence.class), "")));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(PERSUASION_PAYMENT), true);
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<PERSUASION_PAYMENT) {
					return new Response("Заплатить ("+UtilText.formatAsMoneyUncoloured(PERSUASION_PAYMENT, "охват")+")", "У вас нет "+UtilText.formatAsMoney(PERSUASION_PAYMENT, "охват")+", поэтому вы не можете поступить так, как просит Венгар...", null);
				}
				return new Response("Заплатить ("+UtilText.formatAsMoney(PERSUASION_PAYMENT, "охват")+")",
						"Согласиться заплатить Венгару, "+UtilText.formatAsMoney(PERSUASION_PAYMENT, "охват")+" чтобы продемонстрировать ему свою покорность..."
						+getCooperationWarning(),
						VENGARS_HALL_APPROACH_PERSUADE_PAY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-PERSUASION_PAYMENT));
						Main.game.getNpc(Vengar.class).incrementMoney(PERSUASION_PAYMENT);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
					}
				};
				
			} else if(index==2) {
				return new Response("Встать на колени",
						"Встань на колени перед Венгаром, чтобы продемонстрировать ему свою покорность...",
						VENGARS_HALL_APPROACH_PERSUADE_KNEEL) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==3) {
				return new Response("Отказаться", "Наотрез отказаться платить. Вряд ли Венгар воспримет это спокойно...", VENGARS_HALL_FIGHT_NO_CONTENT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_REFUSE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -25));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_PAY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(Util.intToString(PERSUASION_PAYMENT), true);
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_PAY", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Согласиться", "Поскольку вы уже решили придерживаться ненасильственного подхода, согласиться на то, чтобы Аксель пришел и показал свою покорность, кажется единственным оставшимся вариантом.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_END_AND_LEAVE", getGuards(true)));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_KNEEL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подчиниться",
						"Сделать так, как говорит Венгар, и стать его подставкой для ног."
								+getCooperationWarning(),
						VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
					}
				};
				
			} else if(index==2) {
				return new Response("Отказаться", "Отказаться унижаться по приказу Венгара. Вряд ли он воспримет это спокойно...", VENGARS_HALL_FIGHT_NO_CONTENT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL_REFUSE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -25));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT_START", getGuards(true)));
			
			if(isAssAccess(Main.game.getPlayer()) || isVaginaAccess(Main.game.getPlayer())) {
				if(isCompanionDialogue()) {
					if(Main.game.isVoluntaryNTREnabled() && (isAssAccess(getMainCompanion()) || isVaginaAccess(getMainCompanion()))) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH", getGuards(true)));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_COMPANION_WATCHING", getGuards(true)));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_SOLO", getGuards(true)));
				}
				
			} else {
				if(isCompanionDialogue()) {
					if(isAssAccess(getMainCompanion()) || isVaginaAccess(getMainCompanion())) {
						if(Main.game.isVoluntaryNTREnabled()) {
							sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_PLAYER_WATCHING", getGuards(true)));
						} else {
							sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
						}
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isAssAccess(Main.game.getPlayer()) && !isVaginaAccess(Main.game.getPlayer())) {
				if(isCompanionDialogue()) {
					if(!isAssAccess(getMainCompanion()) && !isVaginaAccess(getMainCompanion())) {
						if(index==1) {
							return new Response("Продолжить", "Поскольку Венгар не может трахнуть ни одного из вас, он доволен тем, что просто диктует вам, что вы должны для него сделать.", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
								}
							};
						}
						return null;
						
					} else {
						if(!Main.game.isVoluntaryNTREnabled()) {
							if(index==1) {
								return new Response("Продолжить", "Поскольку Венгар не может трахнуть тебя, он доволен тем, что просто диктует тебе, что ты должна для него сделать.", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
									@Override
									public void effects() {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
									}
								};
							}
							return null;
							
						} else {
							if(index==1) {
								return new Response("Встать",
										UtilText.parse(getMainCompanion(), "Запретить Венгару трахать [npc.name] на глазах у всех и сказать ему, что вы уже сделали всё, как он просил."),
										VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
									@Override
									public void effects() {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_REFUSE_WATCH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
									}
								};
								
							} else if(index==2) {
								if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Vengar.class)) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
									return new Response("Согласиться",
											UtilText.parse(getMainCompanion(), "Вы видите, что [npc.name] совсем не заинтересована в сексе с Венгаром, и вы не можете заставить [npc.herHim] сделать это..."),
											null);
								}
								return new ResponseSex(
										"Согласиться",
										UtilText.parse(getMainCompanion(), "Пусть Венгар трахает [npc.name], пока ты и все остальные в зале наблюдаете за этим."),
										true,
										false,
										new SMVengarDominantSex(
												SexPosition.ALL_FOURS,
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
												Util.newHashMapOfValues(
														new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS))),
										Util.newArrayListOfValues(
												Main.game.getNpc(Shadow.class),
												Main.game.getNpc(Silence.class)),
										Util.newArrayListOfValues(
												Main.game.getPlayer()),
										VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
										UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(getMainCompanion())?"VENGARS_HALL_SUB_SEX_WATCH":"VENGARS_HALL_SUB_SEX_WATCH_ANAL", getGuards(true))) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										if(isVaginaAccess(getMainCompanion())) {
											return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
											
										} else if(isAssAccess(getMainCompanion())) {
											return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
										}
										return super.getInitialSexActions();
									}
								};
							}
						}
						return null;
					}
					
				} else {
					if(index==1) {
						return new Response("Продолжить", "Поскольку Венгар не может трахнуть тебя, он доволен тем, что просто диктует тебе, что ты должна для него сделать.", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH"));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK"));
							}
						};
					}
					return null;
				}
			}
			
			if(index==1) {
				return new Response("Встать",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Отказаться позволить Венгару трахать тебя или [npc.name] на глазах у всех, сказав ему, что вы уже достаточно наделались.")
							:"Отказаться позволить Венгару трахать тебя на глазах у всех, сказав ему, что ты уже сделала всё, как он просил.",
						VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_REFUSED", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						isCompanionDialogue()
							?"Представить себя (в одиночку)"
							:"Предстать перед ней",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти назад, после чего начать вилять задом и подставить задницу Венгару, предлагая ему трахнуть тебя на глазах у всех в зале.")
							:"Начать вилять задом и подставить задницу Венгару, предлагая ему трахнуть тебя на глазах у всех в зале.",
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								getMainCompanion()),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(Main.game.getPlayer())?"VENGARS_HALL_SUB_SEX_SOLO_START":"VENGARS_HALL_SUB_SEX_SOLO_START_ANAL", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(isVaginaAccess(Main.game.getPlayer())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(Main.game.getPlayer())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 3 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Vengar.class)) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Представить себя"),
							UtilText.parse(getMainCompanion(), "Вы видите, что [npc.name] совсем не заинтересована в сексе с Венгаром, и вы не можете заставить [npc.herHim] сделать это..."),
							null);
					
				} else {
					return new Response(
							UtilText.parse(companion, "Представить себя"),
							UtilText.parse(getMainCompanion(), "Приказать [npc.name] присоединиться к тебе: начать вилять задом и подставлять задницы Венгару, предлагая ему трахнуть вас обеих на глазах у всех в зале."),
							VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
				}
				
			} else if (index == 4 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAttractedTo(Main.game.getNpc(Vengar.class)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
							UtilText.parse(companion, "Вы видите, что [npc.name] совсем не заинтересована в сексе с Венгаром, и вы не можете заставить [npc.herHim] сделать это..."),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
							UtilText.parse(getMainCompanion(), "Сказать Венгару, что, хотя тебе самой идея не очень нравится, ты будешь рада позволить ему трахнуть [npc.name] на глазах у всех..."),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS))),
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
									Main.game.getPlayer()),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(getMainCompanion())?"VENGARS_HALL_SUB_SEX_WATCH":"VENGARS_HALL_SUB_SEX_WATCH_ANAL", getGuards(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(isVaginaAccess(getMainCompanion())) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Трахнут первым",
						UtilText.parse(getMainCompanion(),
							"Прижми свои [pc.hips] к Венгару, давая ему понять, что хочешь, чтобы он трахнул тебя первым."
								+ (!isMouthAccess(Main.game.getPlayer())
									?" Поскольку ты не можешь открыть рот, [npc.name] не сможет отсосать тебе, и ей останется лишь наблюдать и ждать [npc.her], пока Венгар трахает тебя..."	
									:(isVaginaAccess(getMainCompanion())
										?"Тебе поручат отсосать [npc.name], пока Венгар трахает тебя, чтобы [npc.sheIs] была готова к члену Венгара, когда он закончит с тобой."
										:(isAssAccess(getMainCompanion())
											?"Тебе поручат заняться анилингусом для [npc.name], пока Венгар трахает тебя, чтобы [npc.sheIs] была готова к члену Венгара, когда он закончит с тобой."
											:" [npc.Name] придется наблюдать и ждать [npc.her], пока Венгар трахает тебя...")))),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
										!isMouthAccess(Main.game.getPlayer()) || (!isVaginaAccess(getMainCompanion()) && !isAssAccess(getMainCompanion()))
											?null
											:new Value<>(getMainCompanion(),
												isVaginaAccess(getMainCompanion())
													?SexSlotAllFours.IN_FRONT
													:SexSlotAllFours.IN_FRONT_ANAL))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								!isMouthAccess(Main.game.getPlayer())
									?getMainCompanion()
									:isVaginaAccess(getMainCompanion())
										?null
										:(isAssAccess(getMainCompanion())
											?null
											:getMainCompanion())),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_PLAYER_FIRST", getGuards(true))) {
					
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						if(isVaginaAccess(Main.game.getPlayer())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(Main.game.getPlayer())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}

						if(isMouthAccess(Main.game.getPlayer())) {
							if(isVaginaAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueAnus.ANILINGUS_START, false, true));
							}
						}
						
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Трахнут вторым",
						UtilText.parse(getMainCompanion(), 
							!isMouthAccess(getMainCompanion())
								?"Немного отодвинься от Венгара, давая ему понять, что хочешь, чтобы он сначала трахнул твою спутницу. Так как [npc.name] не может дотянуться до [npc.her] рта, [npc.she] не сможет отсосать тебе, пока Венгар трахает [npc.herHim], и тебе останется лишь наблюдать и ждать своей очереди..."
								:isVaginaAccess(Main.game.getPlayer())
									?"Заставь [npc.name] начать отсасывать тебе, давая понять Венгару, что хочешь, чтобы он сначала трахнул твою спутницу."
									:(isAssAccess(getMainCompanion())
										?"Заставь [npc.name] подползти к тебе сзади и начать делать тебе анилингус, давая понять Венгару, что хочешь, чтобы он сначала трахнул твою спутницу."
										:"Немного отодвиньтесь от Венгара, давая ему понять, что хотите, чтобы он сначала трахнул вашу спутницу.")),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
										!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
											?null
											:new Value<>(Main.game.getPlayer(),
												isVaginaAccess(Main.game.getPlayer())
													?SexSlotAllFours.IN_FRONT
													:SexSlotAllFours.IN_FRONT_ANAL))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								!isMouthAccess(getMainCompanion())
									?Main.game.getPlayer()
									:isVaginaAccess(Main.game.getPlayer())
										?null
										:(isAssAccess(Main.game.getPlayer())
											?null
											:Main.game.getPlayer())),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_COMPANION_FIRST", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						if(isVaginaAccess(getMainCompanion())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(getMainCompanion())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						
						if(isMouthAccess(getMainCompanion())) {
							if(isVaginaAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
								
							} else if(isAssAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
							}
						}
						
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE = new DialogueNode("Завершено", "Венгар удовлетворен и заканчивает секс...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE_PLAYER_NEXT", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE_COMPANION_NEXT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> previousWetAreas = new HashMap<>(Main.sex.getAllWetAreas()); // Starting lube from saliva
			
			if(index==1) {
				if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
					return new ResponseSex("Трахнутый",
							UtilText.parse(getMainCompanion(),
								!isMouthAccess(Main.game.getPlayer())
									?"Вы приподнимаете зад, готовясь к тому, что Венгар будет в вас трахаться."
									:isVaginaAccess(getMainCompanion())
										?"Начните слизывать сперму из киски [npc.namePos], готовясь к тому, что следующим в вас будет трахаться Венгар."
										:(isAssAccess(getMainCompanion())
												?"Начните слизывать сперму из задницы [npc.namePos], пока Венгар будет трахать вас."
												:"Вы приподнимаете зад, готовясь к тому, что Венгар будет в вас трахаться.")),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
											!isMouthAccess(Main.game.getPlayer()) || (!isVaginaAccess(getMainCompanion()) && !isAssAccess(getMainCompanion()))
												?null
												:new Value<>(getMainCompanion(),
													isVaginaAccess(getMainCompanion())
														?SexSlotAllFours.IN_FRONT
														:SexSlotAllFours.IN_FRONT_ANAL))) {
								@Override
								public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
									return previousWetAreas;
								}
							},
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
								!isMouthAccess(Main.game.getPlayer())
									?getMainCompanion()
									:isVaginaAccess(getMainCompanion())
										?null
										:(isAssAccess(getMainCompanion())
											?null
											:getMainCompanion())),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_PLAYER_SECOND_START", getGuards(true))) {
						
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							if(isVaginaAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}

							if(isMouthAccess(Main.game.getPlayer())) {
								if(isVaginaAccess(getMainCompanion())) {
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
									
								} else if(isAssAccess(getMainCompanion())) {
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueAnus.ANILINGUS_START, false, true));
								}
							}
							
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
					
				} else {
					return new ResponseSex(
							!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
								?UtilText.parse(getMainCompanion(), "[npc.NamePos] поворачивает")
								:"Оральная чистка",
							UtilText.parse(getMainCompanion(),
								!isMouthAccess(getMainCompanion())
									?"Наблюдайте за тем, как Венгар трахает [npc.name]..."
									:isVaginaAccess(Main.game.getPlayer())
										?"Заставьте [npc.name] начать слизывать сперму из вашей киски, пока Венгар трахает [npc.herHim]."
										:(isAssAccess(Main.game.getPlayer())
											?"Заставьте [npc.name] ползать позади вас и начать слизывать сперму из вашей задницы, пока Венгар трахает [npc.herHim]."
											:"Наблюдайте за тем, как Венгар трахает [npc.name]...")),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
											!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
												?null
												:new Value<>(Main.game.getPlayer(),
													isVaginaAccess(Main.game.getPlayer())
														?SexSlotAllFours.IN_FRONT
														:SexSlotAllFours.IN_FRONT_ANAL))),
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
								!isMouthAccess(getMainCompanion())
									?Main.game.getPlayer()
									:isVaginaAccess(Main.game.getPlayer())
										?null
										:(isAssAccess(Main.game.getPlayer())
											?null
											:Main.game.getPlayer())),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_COMPANION_SECOND_START", getGuards(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							if(isVaginaAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
							
							if(isMouthAccess(getMainCompanion())) {
								if(isVaginaAccess(Main.game.getPlayer())) {
									list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
									
								} else if(isAssAccess(Main.game.getPlayer())) {
									list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
								}
							}
							
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX = new DialogueNode("Завершено", "Венгар удовлетворен и заканчивает секс...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_COMPANION", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Встать", "Встань и жди следующей команды Венгара...", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_FINISH", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_FINISH = new DialogueNode("", "", true, true) {
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
				return new Response("Согласиться", "Поскольку вы уже решили придерживаться ненасильственного подхода, согласиться на то, чтобы Аксель пришел и показал свою покорность, кажется единственным оставшимся вариантом.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_END_AND_LEAVE", getGuards(true)));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_CHUUNI", getGuards(true)));
				
			} else if(Main.game.getPlayer().getRace()==Race.DEMON) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_DEMON", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN", getGuards(true)));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сразиться",
						"Защищайся от Венгара!",
						Main.game.getNpc(Vengar.class));
				
			} else if(index==2) {
				if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.MARTIAL_ARTIST)
						|| Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED)>=75
						|| Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					return new Response("Вырубить",
							"Ты можешь воспользоваться спешкой Венгара, чтобы нанести ему нокаутирующий удар.<br/>[style.italicsMinorGood("
									+(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)
										?"Открывается при наличии улучшения \""+SpellUpgrade.SLAM_3.getName()+"\" для заклинания \""+Spell.SLAM.getName()+"' заклинание."
										:(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.MARTIAL_ARTIST)
											?"Открывается при наличии черты \""+Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())+"' черта."
											:"Открывается при уроне без оружия более 75."))
									+ ")]",
							VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT);
					
				} else {
					return new Response("Вырубить",
							"Ты не можешь воспользоваться спешкой Венгара...<br/>[style.italicsMinorBad(Требуется \""+Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())+"' черту, более 75 единиц без оружия или '"+SpellUpgrade.SLAM_3.getName()+"\" для заклинания \""+Spell.SLAM.getName()+"' заклинание.)]",
							null);
				}
				
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SLAM", getGuards(true)));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT", getGuards(true)));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_END", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Защита", "Защищайся от крысодевок!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Приготовиться", "Приготовься к тому, что грядет!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID);
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "У тебя нет другого выбора, кроме как ждать появления передового патруля...", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "У тебя почти нет выбора, кроме как поступить по словам Клэр и последовать за ней из Крысиных Лабиринтов...", SWORD_RAID_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SWORD_RAID_EXIT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			applyRatWarrensRaid();
		}
		@Override
		public int getSecondsPassed() {
			return 6*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "SWORD_RAID_EXIT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_TELEPATHY", getGuards(true)));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE", getGuards(true)));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_END", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Просто дразнить",
						"Дразни Венгара, обещая ему секс позже, когда все эти дела с Акселем будут улажены.",
						VENGARS_HALL_APPROACH_SEDUCE_NO_SEX);
				
			} else if(index==2) {
				return new ResponseSex(
						"Подчиниться",
						"Скажи Венгару, что он может делать с тобой всё, что захочет...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_SEX_SUBMISSIVE", getGuards(true)));
				
			} else if(index==3) {
				return new ResponseSex(
						"Доминировать",
						"Скажи Венгару, что он будет тебе подчиняться...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								null,
								null),
						VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_SEX_DOMINANT", getGuards(true)));	
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE_NO_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_NO_SEX", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Согласиться", "Поскольку вы уже решили придерживаться ненасильственного подхода, согласиться на то, чтобы Аксель пришел и показал свою покорность, кажется единственным оставшимся вариантом.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_NO_SEX_LEAVE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX = new DialogueNode("Завершено", "Венгар удовлетворен и заканчивает секс...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Согласиться", "Поскольку вы уже решили придерживаться ненасильственного подхода, согласиться на то, чтобы Аксель пришел и показал свою покорность, кажется единственным оставшимся вариантом.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX_LEAVE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_RESONANCE_STONE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_RESONANCE_STONE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Защита", "Защищайся от крысодевок!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
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
			return CORRIDOR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_SHADOW_DEFEATED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Spell.ELEMENTAL_AIR.applyEffect(Main.game.getNpc(Silence.class), Main.game.getNpc(Silence.class), true, false);
			((Silence)Main.game.getNpc(Silence.class)).initElemental();
			Main.game.getNpc(Silence.class).setHealthPercentage(1);
			Main.game.getNpc(Silence.class).setManaPercentage(1);
			Main.game.getNpc(Silence.class).addStatusEffect(StatusEffect.SPECIAL_SILENCE_TRANCE, 8*60*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_SHADOW_DEFEATED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сайленс",
						"Похоже, тебе придется иметь дело и с Сайленс, и с ее только что призванным элементалем!",
						Main.game.getNpc(Silence.class),
						Util.newArrayListOfValues(Main.game.getNpc(Silence.class), Main.game.getNpc(Silence.class).getElemental()),
						null);
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_SILENCE_DEFEATED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setHealthPercentage(1);
			Main.game.getNpc(Shadow.class).setManaPercentage(1);
			Main.game.getNpc(Shadow.class).addStatusEffect(StatusEffect.SPECIAL_SHADOW_BESERK, 8*60*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_SILENCE_DEFEATED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Тень",
						"Похоже, тебе придется сразиться с разъяренной Тенью!",
						Main.game.getNpc(Shadow.class),
						Util.newArrayListOfValues(Main.game.getNpc(Shadow.class)),
						null);
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_VICTORY", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Венгар",
						"Теперь, когда с его телохранителями покончено, тебе предстоит бросить вызов самому Венгару!",
						Main.game.getNpc(Vengar.class));
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_NO_NON_CON", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("Защита", "Защищайся от крысодевок!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS); //TODO test
				}
				
			} else {
				if(index==1) { //TODO
					if(isCompanionDialogue()) {
						return new Response("Сохранить [com.name]",
								"Использовать остатки своей энергии, чтобы сдерживать крыс достаточно долго, чтобы [com.name] успела сбежать.<br/>[style.italicsMinorGood([com.Name] благополучно вернется домой.)]",
								BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_COMPANION_ESCAPE"));
								
								GameCharacter companion = getMainCompanion();
								Main.game.getPlayer().removeCompanion(companion);
								companion.returnToHome();

								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
							}
						};
						
					} else {
						return new Response("Утащенная прочь",
								"Ты бессильна сопротивляться, пока Венгар тащит тебя в другую часть своего логова...",
								BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF) {
							@Override
							public void effects() {
								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								
							}
						};
					}
				}
				
				
				// Lead-in to Vengar sex slave content, which was set aside in v0.3.9 for later inclusion.
//				if(index==1) {
//					if(isCompanionDialogue()) {
//						return new Response(UtilText.parse(getMainCompanion(), "Save [npc.name]"),
//								UtilText.parse(getMainCompanion(), "Use the last of your energy to hold off the rats long enough for [npc.name] to escape."
//										+ "<br/>[style.italicsMinorGood(Unlocked by having the 'Involuntary NTR' content setting turned off."
//											+ " [npc.Name] will safely return home, but the rest of the loss route will proceed as normal.)]"),
//								VengarCaptiveDialogue.FINAL_COMBAT_DEFEAT_STRIPPED) {
//							@Override
//							public void effects() {
//								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_COMPANION_ESCAPE", getGuards(true)));
//								
//								GameCharacter companion = getMainCompanion();
//								Main.game.getPlayer().removeCompanion(companion);
//								companion.returnToHome();
//
//								Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
//								applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Shadow.class), PresetColour.CLOTHING_PINK_HOT);
//							}
//						};
//						
//					} else {
//						return new Response("Stripped", "Shadow unceremoniously strips you in front of everyone in the hall...", VengarCaptiveDialogue.FINAL_COMBAT_DEFEAT_STRIPPED) {
//							@Override
//							public void effects() {
//								Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
//								applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Shadow.class), PresetColour.CLOTHING_PINK_HOT);
//							}
//						};
//					}
//				}
			}
			return null;
		}
	};
	
	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Раздета",
						"Мерк принимается раздевать тебя догола...",
						BODYGUARDS_COMBAT_DEFEAT_STRIPPED) {
					@Override
					public void effects() {
						applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Murk.class), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_STRIPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Новый дом",
						"Мерк и Венгар ведут тебя в соседнюю комнату, чтобы представить твой «новый дом»...",
						BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END) {
					@Override
					public void effects() {
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Минет",
						"Сделай то, чего от тебя ждут, и послушно отсоси Венгару.",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), null),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Жадно отсосать",
						"Показать всем своим видом, как сильно ты хочешь послушно отсосать Венгару.",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), SexPace.SUB_EAGER),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("Сопротивляться минету",
						"Сделай всё возможное, чтобы сопротивляться, когда Венгар попытается отсосать тебе.",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), SexPace.SUB_RESISTING),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX_RESIST")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return "Насладившись, Венгар отстраняется и готовится уйти...";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Мерк", "Мерк снова появляется, чтобы рассказать, что ждет тебя дальше...", RatWarrensCaptiveDialogue.CAPTIVE_DAY_0) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0"));
						banishGuards(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight));
						Main.game.getNpc(Vengar.class).returnToHome();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode VENGAR_COMBAT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_COMBAT_VICTORY", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Защита", "Защищайся от крысодевок!", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGAR_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BODYGUARDS_COMBAT_DEFEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Подчиниться",
						"Скажи Венгару, что он может делать с тобой всё, что захочет...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						VENGARS_BEDROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_SEX_SUBMISSIVE", getGuards(true)));
				
			} else if(index==2) {
				return new ResponseSex(
						"Доминировать",
						"Скажи Венгару, что он будет тебе подчиняться...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								null,
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						VENGARS_BEDROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_SEX_DOMINANT", getGuards(true)));	
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_BEDROOM_AFTER_SEX = new DialogueNode("Завершено", "Венгар удовлетворен и заканчивает секс...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Скажи Венгару, что увидишься с ним как-нибудь в другой раз, прежде чем попрощаться.", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_AFTER_SEX_LEAVE"));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LEXA_VISIT = new DialogueNode("Игорный дом", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine() && Main.game.getPlayer().isFeminine()) {
					return new Response("Трио", "[axel.Name] привлекают только маскулинные люди, поэтому она не желает заниматься с тобой сексом...", null);
				}
				return new ResponseSex(
						"Трио",
						"Присоединись к Венгару, чтобы доминировать над [axel.name] во время секса...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(
										Main.game.getNpc(Axel.class)),
								null,
								null){
							@Override
							public boolean isPlayerAbleToStopSex() {
								return false;
							}
						},
						LEXA_VISIT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_THREESOME", getGuards(true)));
				
			} else if(index==2) {
				return new ResponseSex(
						"Смотреть",
						"Наблюдай, как Венгар трахает [axel.name]...",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getNpc(Axel.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						LEXA_VISIT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_WATCH", getGuards(true)));
				
			} else if(index==3) {
				return new Response("Уйти", "Реши не заниматься ничем непристойным с Венгаром и [axel.name], а вместо этого оставить их наедине...", PlaceType.SUBMISSION_GAMBLING_DEN.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_LEAVE", getGuards(true)));
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
						Main.game.getNpc(Vengar.class).calculateGenericSexEffects(true, true, Main.game.getNpc(Axel.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LEXA_VISIT_AFTER_SEX = new DialogueNode("Завершено", "Венгар удовлетворен и заканчивает секс...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Ты остаешься наедине с [axel.name] у главного входа в Игорный дом.", PlaceType.GAMBLING_DEN_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
					}
				};
			}
			return null;
		}
	};
	
	// After captivity enforcer raid:
	
	public static final DialogueNode POST_CAPTIVITY_SWORD_RAID = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyRatWarrensRaid();
			Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_ENFORCERS));
			Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
		}
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_CAPTIVITY_SWORD_RAID", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Продолжить путь...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	
//	public static final DialogueNode POST_DEFEAT_SWORD_RAID = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 2*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_DEFEAT_SWORD_RAID", getGuards(true));
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(index==1) {
//				return new Response("Wait", "Wait with Silence for Shadow to return.", POST_DEFEAT_SWORD_RAID_FINISH) {
//					@Override
//					public void effects() {
//						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
//						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_ENFORCERS));
//					}
//				};
//			}
//			return null;
//		}
//	};
//	
//	public static final DialogueNode POST_DEFEAT_SWORD_RAID_FINISH = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 10*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_CAPTIVITY_SWORD_RAID_FINISH", getGuards(true));
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(index==1) {
//				return new Response("Follow", "You don't have much choice but to follow Claire out of the Rat Warrens...", SWORD_RAID_EXIT) {
//					@Override
//					public void effects() {
//						Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
//					}
//				};
//			}
//			return null;
//		}
//	};
}
