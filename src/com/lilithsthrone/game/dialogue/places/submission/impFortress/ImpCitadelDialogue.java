package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.12
 * @author Innoxia
 */
public class ImpCitadelDialogue {
	
	public static boolean isImpsDefeated() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonImpsDefeated);
	}
	
	public static boolean isDefeated() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated);
	}
	
	private static boolean isBossEncountered() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonBossEncountered);
	}

	/**
	 * The demon leaders are placed in the siren's throne room.
	 */
	public static void applyEntry() {
		if(!isDefeated()) {
			Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
			Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
			Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
	}
	
	/**
	 * If the demon leaders haven't been defeated, they return to their keeps.
	 */
	public static void applyExit() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
		
		if(Main.game.getNpc(FortressAlphaLeader.class).getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if(isDefeated() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
			} else {
				Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
			}
		}
		if(Main.game.getNpc(FortressMalesLeader.class).getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if(isDefeated() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
			} else {
				Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
			}
		}
		if(Main.game.getNpc(FortressFemalesLeader.class).getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if(isDefeated() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
			} else {
				Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
			}
		}
	}
	
	public static void clearFortress(boolean withQuestProgress) {
		
		for(GameCharacter character : getBossGroup(false)) {
			character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		}
		Main.game.getNpc(Takahashi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
		
		// Sort out boss:
		getBoss().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
		Main.game.getNpc(Lyssieth.class).addSlave((NPC) getBoss());
		((NPC) getBoss()).equipClothing(EquipClothingSetting.getAllClothingSettings()); // In case the player used steal on her.
		
		// Increment quest:
		if(withQuestProgress && Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_2_B_SIRENS_CALL) {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL));
		}
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonDefeated, true);

		Main.game.getDialogueFlags().setSavedLong(ImpFortressDialogue.FORTRESS_ALPHA_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
		Main.game.getDialogueFlags().setSavedLong(ImpFortressDialogue.FORTRESS_MALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
		Main.game.getDialogueFlags().setSavedLong(ImpFortressDialogue.FORTRESS_FEMALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
		
		// Move NPCs out of hiding:
		for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
			if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
				character.returnToHome();
			}
		}
		
		getArcanist().equipClothing(EquipClothingSetting.getAllClothingSettings()); // Remove lab coat
	}
	
	private static void banishImps() {
		Cell[][] grid =  Main.game.getWorlds().get(WorldType.IMP_FORTRESS_DEMON).getGrid();
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				List<GameCharacter> characters = new ArrayList<>(Main.game.getCharactersPresent(grid[i][j]));
				for(GameCharacter character : characters) {
					if(character instanceof ImpAttacker && !character.isSlave()) {
						Main.game.banishNPC((NPC) character);
					}
				}
			}
		}
	}
	
	public static void spawnImps() {
		banishImps();
		
		List<GameCharacter> impGroup = new ArrayList<>();
		try {
			
			impGroup = new ArrayList<>();
			List<String> impAdjectives = new ArrayList<>();
			
			int impCount = 6;
			if(Main.game.getDialogueFlags().impCitadelImpWave==5) {
				impCount = 4;
			}
			
			for(int i=0; i<impCount; i++) {
				AbstractSubspecies subspecies = i<3?Subspecies.IMP_ALPHA:Subspecies.IMP;
				
				ImpAttacker imp = new ImpAttacker(subspecies, Gender.getGenderFromUserPreferences(false, false), false);
				imp.setLevel(12-(i*2)+Util.random.nextInt(3));
				Main.game.addNPC(imp, false);
				if(i==0) {
					imp.setGenericName("вожак альфа-бесов");
					imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
					imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
					
				} else if(i==1) {
					imp.setGenericName("альфа-бес лучник");
					imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow"));
					
				} else if(i==2) {
					imp.setGenericName("арканист альфа-бесов");
					imp.setAttribute(Attribute.MAJOR_ARCANE, 50);
					imp.addSpell(Spell.ARCANE_AROUSAL);
					imp.addSpell(Spell.FIREBALL);
					imp.addSpell(Spell.ICE_SHARD);
					imp.addSpell(Spell.TELEKENETIC_SHOWER);
					
				} else {
					impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
					imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				}
				impGroup.add(imp);
			}
			
			for(GameCharacter impCharacter : impGroup) {
				impCharacter.setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
				((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<GameCharacter> getImpGroup() {
		List<GameCharacter> impGuards = new ArrayList<>();

		for(GameCharacter character : Main.game.getCharactersPresent()) {
			if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
				impGuards.add(character);
			}
		}
		
		impGuards.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return impGuards;
	}
	
	public static GameCharacter getImpGroupLeader() {
		return getImpGroup().get(0);
	}
	
	/**
	 * Only to be used in Game.importGame() for versions prior to 0.2.12.5
	 */
	public static void resetFortress() {
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonDefeated, false);
		
		banishImps();
		
		// Move boss back to fortress:
		Main.game.getNpc(DarkSiren.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);

		// Move defeated leaders into fortress:
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
			Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
			Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
			Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		
		// Move NPCs into hiding:
		Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
		for(int i=0; i< cells.length;i++) {
			for(int j=0; j< cells[i].length;j++) {
				Cell cell = cells[j][i];
				if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
					for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
						if(!Main.game.getPlayer().getCompanions().contains(character)) {
							character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
							character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						}
					}
				}
			}
		}
	}
	
	public static List<GameCharacter> getBossGroup(boolean includeBoss) {
		List<GameCharacter> bossGroup = new ArrayList<>();
		
		if(includeBoss) {
			bossGroup.add(getBoss());
		}
		bossGroup.add(Main.game.getNpc(FortressMalesLeader.class));
		bossGroup.add(Main.game.getNpc(FortressAlphaLeader.class));
		bossGroup.add(Main.game.getNpc(FortressFemalesLeader.class));
		
		return bossGroup;
	}

	public static NPC getDemonLeader() {
		return Main.game.getNpc(FortressMalesLeader.class);
	}

	public static GameCharacter getBoss() {
		return Main.game.getNpc(DarkSiren.class);
	}
	
	public static Takahashi getArcanist() {
		return (Takahashi) Main.game.getNpc(Takahashi.class);
	}

	public static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	public static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	public static List<GameCharacter> getAllCharacters() {
		// There's a reason I can't just add all from getCharactersPresent(), but I forgot. Maybe it was because the Elemental companion gets added?
		List<GameCharacter> allCharacters = new ArrayList<>();
		
		if(isCompanionDialogue()) {
			allCharacters.add(getMainCompanion());
		}
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_DEMON_KEEP)) {
			allCharacters.add(getBoss());
			allCharacters.add(Main.game.getNpc(FortressMalesLeader.class));
			allCharacters.add(Main.game.getNpc(FortressAlphaLeader.class));
			allCharacters.add(Main.game.getNpc(FortressFemalesLeader.class));
		}

		// For the arcanist:
		for(NPC character : Main.game.getCharactersPresent()) {
			if(!allCharacters.contains(character)) {
				allCharacters.add(character);
			}
		}
		
		return allCharacters;
	}
	
	public static String getDialogueEncounterId() {
		if(isCompanionDialogue()) {
			return "Спутники";
		}
		return "";
	}
	
	// Dialogues:
	
	private static Response getImpChallengeResponse() {
		return new Response("Бросить вызов",
				"Заявить, что ты пришел, чтобы сокрушить всех обитателей этой цитадели!<br/><i>Ты полагаешь, что в цитадели обитает около [style.boldBad(тридцати бесов)], так что готовься сражаться с таким количеством врагов, когда они будут прибывать волнами на помощь своим союзникам!</i>",
				IMP_CHALLENGE) {
			@Override
			public boolean isCombatHighlight() {
				return true;
			}
			@Override
			public void effects() {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE", getAllCharacters()));
				Main.game.getDialogueFlags().impCitadelImpWave = 0;
				spawnImps();
				getArcanist().setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			}
		};
	}
	
	public static final DialogueNode ENTRANCE = new DialogueNode("Ворота", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ENTRANCE_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ENTRANCE", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMPS_DEFEATED", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMP_GUARDS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Вернуться обратно в туннели.", LEAVE_FORTRESS) {
					@Override
					public void effects() {
						applyExit();
					}
				};

			} else if(index==5 && !isImpsDefeated() && !isDefeated()) {
				return getImpChallengeResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode LEAVE_FORTRESS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LEAVE_FORTRESS", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_DEMON.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode IMP_CHALLENGE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				try {
					return new ResponseCombat("Сразиться", "Пора поставить этих бесов на место!", (NPC) getImpGroupLeader(), getImpGroup(), null);
				} catch(Exception ex) {
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_CHALLENGE_CONTINUE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE", getAllCharacters()));
			
			switch(Main.game.getDialogueFlags().impCitadelImpWave) {
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_2", getAllCharacters()));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_3", getAllCharacters()));
					break;
				case 3:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_4", getAllCharacters()));
					break;
				case 4:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_5", getAllCharacters()));
					break;
				case 5:
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_CHALLENGE_CONTINUE_WAVE_6", getAllCharacters()));
					break;
				default:
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Еще больше бесов", "Являются еще больше бесов, чтобы спасти своих павших товарищей!", (NPC) getImpGroup().get(0), getImpGroup(), null);
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_FIGHT_AFTER_COMBAT_VICTORY = new DialogueNode("Победа", ".", true) {

		@Override
		public String getDescription() {
			return "Ты одолел бесов!";
		}

		@Override
		public String getContent() {
			if(getImpGroup().isEmpty()) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ALL_ENSLAVED");
				
			} else if(getImpGroup().size()==1) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED_ONE", getImpGroup());
				
			} else if(getImpGroup().size()<4) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED", getImpGroup());
			}
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY", getImpGroup());
			// IMP_FIGHT_AFTER_COMBAT_VICTORY_ATTRIBUTE_BOOST is appended to this (in ImpAttacker class's endCombat() method)
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getImpGroup().isEmpty()) {
				if(index==0) {
					return "Взаимодействие";
					
				} else if(index==1) {
					return "Инвентарь";
					
				} else if(index==2) {
					return "Трансформации";
					
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpGroup().isEmpty()) {
				if(index==1) {
					return new Response("Продолжить", "Поскольку ты поработил всех бесов, остается только продолжать свой путь через цитадель...", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Запугать", "Приказать бесам убираться отсюда, пока не поздно...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SCARE_OFF", getImpGroup()));
								banishImps();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Секс",
								"Ну, они сами <i>напрашиваются</i>!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("Нежный секс",
								"Ну, они сами <i>напрашиваются</i>!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Грубый секс",
								"Ну, они сами <i>напрашиваются</i>!",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Подчиниться",
								"Ты не совсем понимаешь, что делать дальше... Возможно, лучше позволить бесам самим решить, что делать...",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Main.game.getPlayer().getParty(),
								null,
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("Запугать", "Приказать бесам убираться отсюда, пока не поздно...", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SCARE_OFF", getImpGroup()));
								banishImps();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("Соло-секс",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы грубо трахаетесь с бесами."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("Соло-секс (Нежный)",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы грубо трахаетесь с бесами."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("Соло-секс (Грубый)",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы грубо трахаетесь с бесами."),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								IMP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("Соло-сабмиссив",
								UtilText.parse(getMainCompanion(), "Приказать [npc.name] отойти в сторону и наблюдать, как вы подчиняетесь бесам, позволяя им доминировать над вами во время секса."),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Групповой секс",
									UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с бесами, а так как [npc.sheIs] не рабыня, вы не можете заставить [npc.herHim] сделать это..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Групповой секс"),
									UtilText.parse(companion, "Занимайтесь доминирующим сексом с бесами и заставьте [npc.name] присоединиться к веселью."),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGroup(),
									null,
									null,
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_GROUP_SEX", getImpGroup()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("Групповое подчинение",
									UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с бесами, а так как [npc.sheIs] не рабыня, вы не можете заставить [npc.herHim] сделать это..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Групповое подчинение"),
									UtilText.parse(companion, "Заставьте [npc.name] подчиниться бесам вместе с вами, позволяя им доминировать над вами обоими."),
									true,
									false,
									getImpGroup(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getImpGroup()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Отдать [npc.name]"),
									UtilText.parse(companion, "[npc.Name] не заинтересована в сексе с бесами, а так как [npc.sheIs] не рабыня, вы не можете заставить [npc.herHim] сделать это..."), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Отдать [npc.name]"),
									UtilText.parse(companion, "Скажите [npc.name], что [npc.she] может немного повеселиться с бесами, пока вы наблюдаете."),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGroup(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_GIVE_TO_COMPANION", getImpGroup()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpGroupLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "Предложить [npc.name]"),
									UtilText.parse(companion, "Вы видите, что [npc.name] совсем не заинтересована в сексе с бесами, и не можете заставить [npc.herHim] сделать это..."),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "Предложить [npc.name]"),
									UtilText.parse(companion, "Передайте [npc.name] бесам и наблюдайте, как они трахают [npc.herHim]."),
									true,
									false,
									getImpGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									IMP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_COMBAT_VICTORY_OFFER_COMPANION", getImpGroup())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpGroupLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Теперь, когда ты одолел(а) [npc.name], ничто не мешает тебе полакомиться одеждой и вещами [npc.her]...")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "Внимательно рассмотреть, в кого [npc.name] может превратить [npc.herself]..."),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			}
		}
	};

	public static final DialogueNode IMP_FIGHT_AFTER_COMBAT_DEFEAT = new DialogueNode("Поражение", ".", true) {
		
		@Override
		public String getDescription() {
			return "Вы были повержены бесами!";
		}

		@Override
		public String getContent() {
			if(Main.game.isNonConEnabled()) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)) {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_IN_LAB", getAllCharacters());
				}
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT", getAllCharacters());
				
			} else {
				if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)) {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_IN_LAB_NO_NON_CON", getAllCharacters());
				}
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_NO_NON_CON", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("Восстановиться", "Переведи дух, а затем продолжай свой путь.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImps();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_DEFEAT_RECOVER", getAllCharacters()));
						}
					};
				}
				return null;
			}
			
			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)
					&& (Main.game.getPlayer().isFeminine() || (isCompanionDialogue() && getMainCompanion().isFeminine() && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {
				
				Map<GameCharacter, SexSlot> subSlots;
				List<GameCharacter> spectators = new ArrayList<>();
				
				if(!Main.game.getPlayer().isFeminine()) {
					spectators.add(Main.game.getPlayer());
					subSlots = Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL));
					
				} else if(isCompanionDialogue () && !getMainCompanion().isFeminine()) {
					spectators.add(getMainCompanion());
					subSlots = Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL));
					
				} else {
					if(isCompanionDialogue()) {
						subSlots = Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
								new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO));
					} else {
						subSlots = Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL));
					}
				}

				SexManagerInterface manager = new SMStanding(
						Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
						subSlots) {
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							if(index==2) {
								return SexPace.SUB_EAGER;
							} else if(index==3) {
								return SexPace.SUB_RESISTING;
							}
						}
						return null;
					}
				};
				
				//If the arcanist is just using the player's companion, then just "watch":
				if(spectators.contains(Main.game.getPlayer())) {
					if(index==1) {
						return new ResponseSex(
								"Смотреть",
								UtilText.parse(getArcanist(), getMainCompanion(), "Прими цену своего поражения и смотри, как [npc2.name] заставляют отсасывать [npc.name]."),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST_WATCH", getAllCharacters()));
					} 
					
				} else {
					if(index==1) {
						return new ResponseSex(
								"Секс",
								isCompanionDialogue() && !spectators.contains(getMainCompanion())
									?UtilText.parse(getArcanist(), getMainCompanion(), "Прими цену своего поражения и вместе с [com.name] готовься отсасывать [npc.name].")
									:UtilText.parse(getArcanist(), "Прими цену своего поражения и готовься отсасывать [npc.name]."),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST", getAllCharacters()));
						
					} else if(index==2) {
						return new ResponseSex(
								"Жадный секс",
								isCompanionDialogue() && !spectators.contains(getMainCompanion())
									?UtilText.parse(getArcanist(), getMainCompanion(), "С радостью прими требования и вместе с [com.name] с нетерпением готовься отсасывать [npc.name].")
									:UtilText.parse(getArcanist(), "С радостью прими требования и с нетерпением готовься отсасывать [npc.name]."),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST_EAGER", getAllCharacters()));

					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex(
								"Секс с сопротивлением",
								UtilText.parse(getArcanist(), "Сопротивляйся [npc.name] и попытайся оттолкнуть от себя [npc.herHim]."),
								false,
								false,
								manager,
								null,
								null,
								IMP_AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_ARCANIST_RESIST", getAllCharacters()));
					}
				}
				
				
			} else {
				if (index == 1) {
					return new ResponseSex("Секс",
							"Позволь бесам принять тебя нужную позу...",
							false,
							false,
							getImpGroup(),
							Main.game.getPlayer().getParty(),
							null,
							null,
							IMP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("Жадный секс",
							"С готовностью позволь толпе бесов усадить тебя в нужное положение...",
							false,
							false,
							getImpGroup(),
							Main.game.getPlayer().getParty(),
							null,
							null,
							IMP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
							ResponseTag.START_PACE_PLAYER_SUB_EAGER);
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Секс с сопротивлением",
							"Попытайся сопротивляться, пока толпа бесов усаживает тебя в нужное положение...",
							false,
							false,
							getImpGroup(),
							Main.game.getPlayer().getParty(),
							null,
							null,
							IMP_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
							ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_AFTER_SEX_VICTORY = new DialogueNode("Отступить", "", true) {
		
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь повеселился, можно отступить и оставить бесов приходить в себя и расходиться.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return IMP_FIGHT_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Запугать", "Напугай бесов и продолжай свой путь.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImps();
						}
					};
				}
				
			} else if(responseTab==1) {
				return IMP_FIGHT_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode IMP_AFTER_SEX_DEFEAT = new DialogueNode("Свалиться", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "Ты совершенно измотан после секса с этими бесами...";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getArcanist())) {
				if(isCompanionDialogue() && Main.sex.getAllParticipants().contains(getMainCompanion())) {
					if(Main.sex.getAllParticipants().contains(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_ARCANIST_SEX_BOTH", getAllCharacters());
					} else {
						return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_ARCANIST_SEX_COMPANION", getAllCharacters());
					}
				} else {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_ARCANIST_SEX", getAllCharacters());
				}
			}
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "AFTER_IMP_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Восстановиться", "Переведи дух, а затем продолжай свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						banishImps();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "IMP_FIGHT_AFTER_SEX_DEFEAT_RECOVER", getAllCharacters()));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COURTYARD = new DialogueNode("Внутренний двор", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "COURTYARD_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "COURTYARD", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMPS_DEFEATED", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMP_GUARDS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5 && !isImpsDefeated() && !isDefeated()) {
				return getImpChallengeResponse();
			}
			return null;
		}
	};

	public static final DialogueNode WELL = new DialogueNode("Колодец", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "WELL_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "WELL", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMPS_DEFEATED", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "GENERIC_IMP_GUARDS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5 && !isImpsDefeated() && !isDefeated()) {
				return getImpChallengeResponse();
			}
			
			return null;
		}
	};

	public static final DialogueNode LABORATORY = new DialogueNode("Лаборатория", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_RUINS", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY", getAllCharacters()));
				
				if(isImpsDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_IMPS_DEFEATED", getAllCharacters()));
				} else {
					if(getArcanist().isPlayerKnowsName()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_IMPS_ARCANIST_MET", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_IMPS", getAllCharacters()));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isDefeated() && !isImpsDefeated()) {
				if(index==1) {
					return new Response("Надзиратель",
							getArcanist().isPlayerKnowsName()
								?"Подойти к надзирательнице-ёко."
								:"Подойти к надзирательнице-лисичке.",
							LABORATORY_ARCANIST) {
						@Override
						public void effects() {
							getArcanist().setPlayerKnowsName(true);
						}
					};
				}
			} else if(isDefeated()) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelLaboratorySearched)) {
						return new Response("Обыскать", "Вы уже обыскали лабораторию. Найти больше нечего!", null);
					}
					return new Response("Обыскать", "Порыться в обломках, чтобы попытаться найти что-нибудь ценное.", LABORATORY_SEARCH) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelLaboratorySearched, true);

							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(1000));

							TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer());
							AbstractItem potion = EnchantingUtils.craftItem(
								Main.game.getItemGen().generateItem(effects.getItemType()),
								effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
							potion.setName("Лисья траха");
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(potion, 1, false, true));
							
							if(isCompanionDialogue()) {
								effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer().getMainCompanion());
								potion = EnchantingUtils.craftItem(
									Main.game.getItemGen().generateItem(effects.getItemType()),
									effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
								potion.setName("Лисья траха");
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(potion, 1, false, true));
							}
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode LABORATORY_SEARCH = new DialogueNode("", "", false, true) {
	
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
	
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_SEARCH", getAllCharacters());
		}
	
		@Override
		public Response getResponse(int responseTab, int index) {
			return LABORATORY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST = new DialogueNode("", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			//TODO check for mouth blocked
			//TODO add conversation action?
			UtilText.nodeContentSB.setLength(0);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistEncountered)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_REPEAT_NO_TF", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_REPEAT", getAllCharacters()));
				}
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST", getAllCharacters()));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отклонить", UtilText.parse(getArcanist(), "Сказать «нет» [npc.name] и оставить [npc.herHim] работать с [npc.her]."), LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_DECLINE", getAllCharacters()));
					}
				};
				
			// Action 2: Player accepts TF potion.
			// If already drank TF potion, or is already a fox-morph, action is for performing oral sex.
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF) || Main.game.getPlayer().getRace()==Race.FOX_MORPH) {
					if(!Main.game.getPlayer().isFeminine()) {
						return new Response(isCompanionDialogue()?"Оральный секс (соло)":"Оральный",
								UtilText.parse(getArcanist(), "Поскольку [npc.sheIsFull] гинефил, [npc.name] не заинтересована в том, чтобы вы сделали ей минет."),
								null);
					} else {
						return new ResponseSex(isCompanionDialogue()?"Оральный секс (соло)":"Оральный",
								UtilText.parse(getArcanist(), "Согласиться сделать так, как говорит [npc.name], и опуститься на колени, чтобы отсосать ей."),
								true,
								false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))){
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return false;
									}
								},
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								LABORATORY_ARCANIST_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_SOLO_START_SEX", getAllCharacters()));
					}
				}
				return new Response(isCompanionDialogue()?"Принять (соло)":"Принять",
						UtilText.parse(getArcanist(), "Согласиться выпить зелье [npc.namePos].<br/>Это [style.italicsTfGeneric(превратит тебя в лисичку)] и, скорее всего, вызовет дополнительные трансформационные эффекты, не связанные с расой!"),
						LABORATORY_ARCANIST_SOLO_TF) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);

						TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, Main.game.getPlayer(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF_OFFER_SEX", getAllCharacters()));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF, true);
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.TRANSFORMATION_GENERIC;
					}
				};

			// Action 3: Companion accepts TF potion.
			// If already drank TF potion, or is already a fox-morph, action is for getting companion to perform oral sex.
			} else if(index==3 && isCompanionDialogue()) {
				if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF) || getMainCompanion().getRace()==Race.FOX_MORPH) && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(
								UtilText.parse(getMainCompanion(), "Оральный секс ([npc.name])"),
								UtilText.parse(getArcanist(), getMainCompanion(),
									"Так как [npc2.name] не испытывает влечения к [npc.name], [npc2.she] откажется трахаться с [npc.herHim]. Принудить [npc2.herHim] к этому тоже не получится..."),
								null);
						
					} else if(!getMainCompanion().isFeminine()) {
						return new Response(
								UtilText.parse(getMainCompanion(), "Оральный секс ([npc.name])"),
								UtilText.parse(getArcanist(), getMainCompanion(), "Так как [npc.sheIsFull] гинефил, [npc.name] не заинтересована в том, чтобы [npc2.name] отсосала ей."),
								null);
						
					} else {
						return new ResponseSex(
								UtilText.parse(getMainCompanion(), "Оральный секс ([npc.name])"),
								UtilText.parse(getArcanist(), getMainCompanion(), "Приказать [npc2.name] опуститься на колени перед [npc2.her] и отсосать [npc.name] на твоих глазах."),
								true,
								false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL))) {
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return false;
									}
								},
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								LABORATORY_ARCANIST_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_COMPANION_START_SEX", getAllCharacters()));
					}
				}
				if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(
							UtilText.parse(getMainCompanion(), "Принять ([npc.name])"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] не любит превращения, [npc2.she] откажется пить зелье [npc.namePos]. Принудить [npc2.herHim] к этому тоже не получится..."),
							null);
				}
				if(getMainCompanion().getRace()==Race.FOX_MORPH) {
					return new Response(UtilText.parse(
							getMainCompanion(), "Принять ([npc.name])"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] уже является лис-морфом, [npc.name] не желает использовать зелье на [npc2.herHim]..."),
							null);
				}
				if(getMainCompanion().getRace()==Race.DEMON || (getMainCompanion().isElemental())) {
					return new Response(UtilText.parse(
							getMainCompanion(), "Принять ([npc.name])"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] — демон и поэтому не может быть превращен, [npc.name] не хочет тратить зелье на [npc2.herHim]..."),
							null);
				}
				return new Response(
						UtilText.parse(getMainCompanion(), "Принять ([npc.name])"),
						UtilText.parse(getArcanist(), getMainCompanion(),
								"Велеть [npc2.name] выпить зелье [npc.namePos].<br/>Это [style.italicsTfGeneric(превратит [npc2.name] в девочку-лисицу)] и, скорее всего, вызовет дополнительные, не связанные с расой, трансформационные эффекты!"),
						LABORATORY_ARCANIST_COMPANION_TF) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);
						
						TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(getMainCompanion());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, getMainCompanion(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF_OFFER_SEX", getAllCharacters()));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF, true);
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.TRANSFORMATION_GENERIC;
					}
				};

			// Action 4: Both player and companion accept TF potion.
			// If already drank TF potion, or both are already fox-morphs, action is for performing oral sex.
			} else if(index==4 && isCompanionDialogue()) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF) || (Main.game.getPlayer().getRace()==Race.FOX_MORPH && getMainCompanion().getRace()==Race.FOX_MORPH)) {
					if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(
								"Орально (обоим)",
								UtilText.parse(getArcanist(), getMainCompanion(),
									"Так как [npc2.name] не испытывает влечения к [npc.name], [npc2.she] откажется трахаться с [npc.herHim]. Принудить [npc2.herHim] к этому тоже не получится..."),
								null);
						
					} else if(!Main.game.getPlayer().isFeminine()) {
						return new Response(
								"Орально (обоим)",
								UtilText.parse(getArcanist(), "Поскольку [npc.sheIsFull] гинефил, [npc.name] не заинтересована в том, чтобы вы сделали ей минет."),
								null);
						
					} else if(!getMainCompanion().isFeminine()) {
						return new Response(
								"Орально (обоим)",
								UtilText.parse(getArcanist(), getMainCompanion(), "Так как [npc.sheIsFull] гинефил, [npc.name] не заинтересована в том, чтобы [npc2.name] отсосала ей."),
								null);
						
					} else {
						return new ResponseSex(
								UtilText.parse(getMainCompanion(), "Орально (обоим)"),
								UtilText.parse(getArcanist(), getMainCompanion(), "Велеть [npc2.name] опуститься на колени перед [npc2.her] и заняться оральным сексом с [npc.name]."),
								true,
								false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
												new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO))) {
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return false;
									}
								},
								null,
								null,
								LABORATORY_ARCANIST_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_BOTH_START_SEX", getAllCharacters()));
					}
				}
				if(getMainCompanion().getRace()==Race.FOX_MORPH) {
					return new Response(UtilText.parse(
							getMainCompanion(), "Принять (обоих)"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] уже является лис-морфом, [npc.name] не желает использовать зелье на [npc2.herHim]..."),
							null);
				}
				if(getMainCompanion().getRace()==Race.DEMON || (getMainCompanion().isElemental())) {
					return new Response(UtilText.parse(
							getMainCompanion(), "Принять (обоих)"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] — демон и поэтому не может быть превращен, [npc.name] не хочет тратить зелье на [npc2.herHim]..."),
							null);
				}
				if(Main.game.getPlayer().getRace()==Race.FOX_MORPH) {
					return new Response(UtilText.parse(
							getMainCompanion(), "Принять (обоих)"),
							UtilText.parse(getArcanist(),
								"Поскольку ты уже являешься лис-морфом, [npc.name] неохотно использует на тебе зелье..."),
							null);
				}
				if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(
							getMainCompanion(), "Принять (обоих)"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] не любит превращения, [npc2.she] откажется пить зелье [npc.namePos]. Принудить [npc2.herHim] к этому тоже не получится..."),
							null);
				}
				return new Response("Принять (обоих)", 
						UtilText.parse(getArcanist(), getMainCompanion(),
								"Согласиться разделить [npc.namePos] зелье между собой и [npc2.name].<br/>Это [style.italicsTfGeneric(превратит и тебя, и [npc2.name] в лис-девушек)] и, скорее всего, наложит некоторые дополнительные, нерасовые, трансформационные эффекты!"),
						LABORATORY_ARCANIST_BOTH_TF) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);

						TransformativePotion effects = ((NPC)getArcanist()).generateTransformativePotion(Main.game.getPlayer());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, Main.game.getPlayer(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_BOTH_TF", getAllCharacters()));

						effects = ((NPC)getArcanist()).generateTransformativePotion(getMainCompanion());
						potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects.getItemType()),
							effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						Main.game.getTextEndStringBuilder().append(getArcanist().useItem(potion, getMainCompanion(), false));
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF_OFFER_SEX", getAllCharacters()));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistAcceptedTF, true);
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.TRANSFORMATION_GENERIC;
					}
				};
				
			// Action 5: Combat
			} else if(index==5) {
				return new Response("Атаковать",
						"Ты ни за что не позволишь этой хитрой лисице так нагло тебя оскорблять!<br/><i>Ты полагаешь, что в цитадели обитает около [style.boldBad(тридцати бесов)], так что приготовься сражаться с таким количеством врагов, когда они волнами придут на помощь своим союзникам!</i>",
						IMP_CHALLENGE) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "ARCANIST_FIGHT_START", getAllCharacters()));
						Main.game.getDialogueFlags().impCitadelImpWave = 0;
						spawnImps();
						getArcanist().setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelArcanistEncountered, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_EXIT = new DialogueNode("", ".", false) {

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LABORATORY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_SOLO_TF = new DialogueNode("", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отклонить",
						UtilText.parse(getArcanist(),
								"Сказать [npc.name], что у тебя нет никакого желания заниматься сексом с [npc.herHim]."),
						LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF_SEX_DECLINED", getAllCharacters()));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Оральный",
						UtilText.parse(getArcanist(), "Согласиться сделать так, как говорит [npc.name], и опуститься на колени, чтобы отсосать [npc.herHim]."),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))){
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						LABORATORY_ARCANIST_POST_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_SOLO_TF_SEX_ACCEPTED", getAllCharacters()));
				
			}
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_COMPANION_TF = new DialogueNode("", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отклонить",
						UtilText.parse(getArcanist(), getMainCompanion(),
								"Сказать [npc.name], что тебе не очень-то и хочется, чтобы [npc2.name] отсосала [npc.herHim]."),
						LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF_SEX_DECLINED", getAllCharacters()));
					}
				};
				
			} else if(index==2 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(
							UtilText.parse(getMainCompanion(), "Согласиться"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] не испытывает влечения к [npc.name], [npc2.she] откажется трахаться с [npc.herHim]. Принудить [npc2.herHim] к этому тоже не получится..."),
							null);
				} 
				return new ResponseSex("Согласиться",
						UtilText.parse(getArcanist(), getMainCompanion(), "Согласиться сделать так, как говорит [npc.name], и велеть [npc2.name] опуститься на колени перед [npc2.her], чтобы та отсосала [npc.herHim], пока ты будешь наблюдать."),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL))){
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						LABORATORY_ARCANIST_POST_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_COMPANION_TF_SEX_ACCEPTED", getAllCharacters()));
			}
			
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_BOTH_TF = new DialogueNode("", ".", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отклонить",
						UtilText.parse(getArcanist(),
								"Сказать [npc.name], что у тебя нет никакого желания заниматься сексом с [npc.herHim]."),
						LABORATORY_ARCANIST_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF_SEX_DECLINED", getAllCharacters()));
					}
				};
				
			} else if(index==2) {
				if(!getMainCompanion().isAttractedTo(getArcanist()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(
							UtilText.parse(getMainCompanion(), "Согласиться"),
							UtilText.parse(getArcanist(), getMainCompanion(),
								"Так как [npc2.name] не испытывает влечения к [npc.name], [npc2.she] откажется трахаться с [npc.herHim]. Принудить [npc2.herHim] к этому тоже не получится..."),
							null);
				} 
				return new ResponseSex("Согласиться",
						UtilText.parse(getArcanist(), getMainCompanion(), "Согласиться сделать так, как говорит [npc.name], и велеть [npc2.name] встать с тобой на колени, чтобы вы вдвоем отсосали [npc.herHim]."),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getArcanist(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO))){
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						LABORATORY_ARCANIST_POST_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_BOTH_TF_SEX_ACCEPTED", getAllCharacters()));
			}
			
			return null;
		}
	};

	public static final DialogueNode LABORATORY_ARCANIST_POST_SEX = new DialogueNode("Завершено", "", true) {
		
		@Override
		public String getDescription(){
			return "[citadelArcanist.Name] закончила с тобой и отталкивает тебя, отступая назад.";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(Main.game.getPlayer())) {
				if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_POST_SEX_BOTH", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_POST_SEX_SOLO", getAllCharacters());
				}
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "LABORATORY_ARCANIST_POST_SEX_COMPANION", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode TREASURY = new DialogueNode("Сокровищница", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelTreasurySearched)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "TREASURY_SEARCHED", getAllCharacters()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "TREASURY_NOT_SEARCHED", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelTreasurySearched)) {
					return new Response("Обыскать", "Вы уже обыскали сокровищницу. Найти больше нечего!", null);
				}
				return new Response("Обыскать", "Порыться в обломках, чтобы попытаться найти что-нибудь ценное.", TREASURY_SEARCH) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelTreasurySearched, true);
						
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(15000));
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(
										Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_darkSiren_siren_amulet"), PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_PURPLE_DARK, false),
										false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(
								Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_darkSiren_siren_cloak"), PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_STEEL, false),
								false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(
								Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_darkSiren_siren_seal"), PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false),
								false));

						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(
								Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_scythe_scythe"), DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_RED_DARK)),
								false));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode TREASURY_SEARCH = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "TREASURY_SEARCH", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TREASURY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode KEEP = new DialogueNode("Цитадель", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !isDefeated();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_DEFEATED", getAllCharacters()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isDefeated()) {
				if(index==1) {
					return new Response("Войти", "Толкнуть двери цитадели и войти внутрь.", KEEP_ENTRY) {
						@Override
						public void effects() {
							getBoss().setPlayerKnowsName(true);
						}
					};
					
				} else if(index==2) {
					return new Response("Уйти", "Отойти от дверей цитадели.", COURTYARD) {
						@Override
						public void effects() {
							Main.game.getPlayer().moveToAdjacentMatchingCellType(false, PlaceType.FORTRESS_DEMON_COURTYARD);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_BACK_OFF", getAllCharacters()));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_ENTRY = new DialogueNode("Цитадель", ".", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isBossEncountered()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_ENTRY_RETURN", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_ENTRY", getAllCharacters()));
			}
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_ENTRY_RING", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)) {
					return new Response("Бросить вызов", UtilText.parse(getBoss(), "Вызвать [npc.name] на дуэль двух величайших пользователей Арканы в мире!"), KEEP_CHALLENGE);
					
				} else {
					return new Response("Бросить вызов", "Вы не можете заставить себя вести диалог на том же уровне, что и эта постыдная суккуб. Возможно, если бы вы тоже были чунибья, всё было бы иначе...", null);
				}
				
			} else if(index==2) {
				return new ResponseCombat("Сразиться", UtilText.parse(getBoss(), "Защищаться от трёх демонов!"), getDemonLeader(), getBossGroup(false), null);
				
			}
				
			return null;
		}
	};
	
	public static final DialogueNode KEEP_DEMONS_DEFEATED = new DialogueNode("Победа", "", true) {

		@Override
		public String getDescription() {
			return "Вы победили демонов!";
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_DEMONS_DEFEATED", getAllCharacters()));
			
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Сразиться", UtilText.parse(getBoss(), "Теперь, когда [npc.her] демонов-спутников повержены, пришло время сразиться с [npc.name]!"), (NPC) getBoss(), Util.newArrayListOfValues(getBoss()), null);

			} else if(index==2 && Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				return new Response("Использовать кольцо",
						UtilText.parse(getBoss(), "Покажи [npc.name], что у тебя есть кольцо [npc.her] матери, и обмани её, чтобы она забрала его у тебя и надела на палец!<br/>[style.italicsExcellent(Можно сказать, что [npc.she] тут же наденет его, тем самым поработив [npc.herHim] без всякой битвы!)]"),
						KEEP_CHALLENGE_RING_TRICK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_POST_FIGHT_RING_TRICK", getAllCharacters()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK", getAllCharacters()));
						Main.game.getTextEndStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), -50));
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.meraxisTrickedWithRing, true);
						clearFortress(true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_CHALLENGE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE", getAllCharacters()));
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Дуэль", UtilText.parse(getBoss(), "Принять предложение [npc.namePos] и сразиться с [npc.herHim] один на один!"), null, (NPC) getBoss(), Util.newArrayListOfValues(getBoss()), null);

			} else if(index==2 && Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				return new Response("Использовать кольцо",
						UtilText.parse(getBoss(), "Покажи [npc.name], что у тебя есть кольцо [npc.her] матери, и обмани её, чтобы она забрала его у тебя и надела на палец!<br/>[style.italicsExcellent(Можно сказать, что [npc.she] тут же наденет его, тем самым поработив [npc.herHim] без всякой битвы!)]"),
						KEEP_CHALLENGE_RING_TRICK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_DUEL_RING_TRICK", getAllCharacters()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK", getAllCharacters()));
						Main.game.getTextEndStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), -50));
						Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
						clearFortress(true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_CHALLENGE_RING_TRICK = new DialogueNode("Цитадель", ".", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Сбежать",
						"Цитадель рушится! Скорее бегите в безопасное место в Submission, пока вас не раздавило!",
						KEEP_COLLAPSE_ESCAPE) {
					@Override
					public void effects() {
						applyExit();
						if(isImpsDefeated()) {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK_ESCAPE_NO_IMPS", getAllCharacters()));
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_CHALLENGE_RING_TRICK_ESCAPE", getAllCharacters()));
						}
					}
				};

			}
			return null;
		}
	};

	public static final DialogueNode KEEP_COLLAPSE_ESCAPE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Объяснить",
						UtilText.parse(getBoss(), "Объяснить стражникам Lyssieth, что произошло."),
						KEEP_COLLAPSE_ESCAPE_END) {
					@Override
					public void effects() {
						if(Main.game.getCharactersPresent().contains(getBoss())) {
							if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_2_B_SIRENS_CALL) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_EXPLAIN", getAllCharacters()));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_EXPLAIN_PRE_QUEST", getAllCharacters()));
							}
							
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_ENSLAVE", getAllCharacters()));
						}
						
						if(isImpsDefeated()) {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_NO_IMPS", getAllCharacters()));
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_GUARDS_IMPS", getAllCharacters()));
						}
						
						clearFortress(true);
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode KEEP_COLLAPSE_ESCAPE_END = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_DEMON.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KEEP_AFTER_COMBAT_VICTORY = new DialogueNode("Победа", "", true) {
		
		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "[npc.Name] наконец-то на грани поражения!");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(UtilText.parse(getBoss(), "Спасти её"),
						UtilText.parse(getBoss(), "Цитадель рушится! Спасите [npc.name], вынеся [npc.herHim] в безопасное место в Submission, пока вас обоих не раздавило!"),
						KEEP_COLLAPSE_ESCAPE) {
					@Override
					public void effects() {
						applyExit();
						getBoss().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
						if(isImpsDefeated()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_HERO_NO_IMPS", getAllCharacters()));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_HERO", getAllCharacters()));
						}
						Main.game.getTextStartStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), 50));

						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_HERO_END", getAllCharacters()));
					}
				};

			} else if (index == 2) {
				return new Response("Бежать",
						UtilText.parse(getBoss(), "Цитадель рушится! Бросьте [npc.name] и мчитесь в безопасное место в Submission, пока вас не раздавило!"),
						KEEP_COLLAPSE_ESCAPE) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementKarma(-25); // Really? You'd just leave her to die? ;_;
						applyExit();
						getBoss().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT", getAllCharacters()));
						Main.game.getTextStartStringBuilder().append(getBoss().incrementAffection(Main.game.getPlayer(), -100));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_COLLAPSE_ESCAPE_COMBAT_END", getAllCharacters()));
					}
				};

			}
			return null;
		}
	};

	
	public static final DialogueNode KEEP_AFTER_COMBAT_DEFEAT = new DialogueNode("Цитадель", ".", true) {
		
		@Override
		public String getDescription() {
			return "Вы потерпели поражение!";
		}

		@Override
		public String getContent() {
			// KEEP_CHALLENGE_LEADER_DEFEAT or KEEP_CHALLENGE_BOSS_DEFEAT are appended to the start of this content (from DS's or Male leader's endCombat() methods)
			
			if(Main.game.isNonConEnabled()) { 
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SIREN_VOYEUR", getAllCharacters());
//				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_CHOICES", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_NO_NON_CON", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("Тебя вышвыривают", "Три демона вытаскивают вас из цитадели.", THROWN_OUT) {
						@Override
						public void effects() {
							applyExit();
						}
					};
				}
				
			} else {
				Map<GameCharacter, SexSlot> domSlots = new HashMap<>();
				
				if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					domSlots.put(Main.game.getNpc(FortressMalesLeader.class), SexSlotAllFours.BEHIND);
				} else if(isCompanionDialogue() && getMainCompanion().hasVagina() && getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					domSlots.put(Main.game.getNpc(FortressMalesLeader.class), SexSlotAllFours.BEHIND_TWO);
				} else {
					domSlots.put(Main.game.getNpc(FortressMalesLeader.class), SexSlotAllFours.BEHIND);
				}
				if(isCompanionDialogue()) {
					domSlots.put(Main.game.getNpc(FortressAlphaLeader.class),
							domSlots.get(Main.game.getNpc(FortressMalesLeader.class))==SexSlotAllFours.BEHIND_TWO
								?SexSlotAllFours.BEHIND
								:SexSlotAllFours.BEHIND_TWO);
					domSlots.put(Main.game.getNpc(FortressFemalesLeader.class), SexSlotAllFours.IN_FRONT);
				} else {
					domSlots.put(Main.game.getNpc(FortressAlphaLeader.class), SexSlotAllFours.IN_FRONT);
					domSlots.put(Main.game.getNpc(FortressFemalesLeader.class), SexSlotAllFours.IN_FRONT_TWO);
				}
				
				Map<GameCharacter, SexSlot> subSlots = new HashMap<>();

				subSlots.put(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS);
				if(isCompanionDialogue()) {
					subSlots.put(getMainCompanion(), SexSlotAllFours.ALL_FOURS_TWO);
				}
				
				SexManagerInterface manager = new SMAllFours(
						domSlots,
						subSlots) {
					@Override
					public boolean isPositionChangingAllowed(GameCharacter character) {
						return false;
					}
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							if(index==2) {
								return SexPace.SUB_EAGER;
							} else if(index==3) {
								return SexPace.SUB_RESISTING;
							}
						}
						return null;
					}
				};
				
				if(index==1) {
					return new ResponseSex(
							"Секс",
							isCompanionDialogue()
								?UtilText.parse(getArcanist(), getMainCompanion(), "Принять цену своего поражения и вместе с [com.name] приготовиться к тому, чтобы трое демонов трахнули вас.")
								:UtilText.parse(getArcanist(), "Прими цену своего поражения и готовься к тому, что тебя оттрахают три демона."),
							false,
							false,
							manager,
							Util.newArrayListOfValues(getBoss()),
							null,
							KEEP_AFTER_COMBAT_DEFEAT_POST_SEX,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if(index==2) {
					return new ResponseSex(
							"Жадный секс",
							isCompanionDialogue()
								?UtilText.parse(getArcanist(), getMainCompanion(), "С радостью прими грядущее и вместе с [com.name] с нетерпением готовься к тому, что тебя оттрахают три демона.")
								:UtilText.parse(getArcanist(), "С радостью прими цену своего поражения и с нетерпением готовься к тому, что тебя оттрахают три демона."),
							false,
							false,
							manager,
							Util.newArrayListOfValues(getBoss()),
							null,
							KEEP_AFTER_COMBAT_DEFEAT_POST_SEX,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex(
							"Секс с сопротивлением",
							UtilText.parse(getArcanist(), "Сопротивляйся трем демонам, пытаясь оттолкнуть их от себя."),
							false,
							false,
							manager,
							Util.newArrayListOfValues(getBoss()),
							null,
							KEEP_AFTER_COMBAT_DEFEAT_POST_SEX,
							UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()));
				}
				
			}
			

			return null;
			
			
			// Removed for now due to unexpected complexity. Will be added at some point.
			
//			if (index == 1) {
//				return new Response(UtilText.parse(Main.game.getNpc(FortressMalesLeader.class), "[npc.Name]"),
//						UtilText.parse(Main.game.getNpc(FortressMalesLeader.class), getBoss(),
//								"Tell [npc2.name] that you submit to [npc.name]...<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] wants to turn you into an imp broodmother!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerMale, true);
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_MALE", getAllCharacters()));
//					}
//				};
//				
//			} else if (index == 2) {
//				return new Response(UtilText.parse(Main.game.getNpc(FortressAlphaLeader.class), "[npc.Name]"),
//						UtilText.parse(Main.game.getNpc(FortressAlphaLeader.class), getBoss(),
//								"Tell [npc2.name] that you submit to [npc.name]...<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] wants to abuse you and turn you into [npc.her] worthless cum-dump!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerAlpha, true);
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_ALPHA", getAllCharacters()));
//					}
//				};
//				
//			} else if (index == 3) {
//				return new Response(UtilText.parse(Main.game.getNpc(FortressFemalesLeader.class), "[npc.Name]"),
//						UtilText.parse(Main.game.getNpc(FortressFemalesLeader.class), getBoss(),
//								"Tell [npc2.name] that you submit to [npc.name]...<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] wants to give you a big cock and put you to work breeding imps!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerFemale, true);
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_FEMALE", getAllCharacters()));
//					}
//				};
//				
//			} else if (index == 4) {
//				return new Response("Refuse",
//						UtilText.parse(getBoss(),
//								"Tell [npc.name] that you refuse to submit to anyone!<br/><i>It's obvious from what [npc.sheHas] just said that [npc.she] will let the demons decide amongst themselves as to who gets possession of you!</i>"),
//						PRISONER_INITIAL_SCENE) {
//					@Override
//					public void effects() {
//						if(Main.game.getPlayer().hasVagina()) {
//							if(Main.game.getPlayer().isFeminine()) {
//								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerMale, true);
//								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_REFUSE_MALE", getAllCharacters()));
//							} else {
//								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerAlpha, true);
//								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_REFUSE_ALPHA", getAllCharacters()));
//							}
//						} else {
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelPrisonerFemale, true);
//							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CHOOSE_REFUSE_FEMALE", getAllCharacters()));
//						}
//					}
//				};
//				
//			} else {
//				return null;
//			}
		}
	};
	
	public static final DialogueNode THROWN_OUT = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if(isImpsDefeated()) {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "THROWN_OUT_NO_IMPS", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "THROWN_OUT", getAllCharacters());
			}
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_DEMON.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KEEP_AFTER_COMBAT_DEFEAT_POST_SEX = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_POST_SEX", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Тебя вышвыривают", "Три демона вытаскивают вас из цитадели.", THROWN_OUT) {
					@Override
					public void effects() {
						applyExit();
					}
				};
			}
			return null;
		}
	};
	
	
	// Kept as prisoner dialogue: TODO Come back to this and add it another time.

	private static boolean isPrisonerMale() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerMale);
	}

	private static boolean isPrisonerFemale() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerFemale);
	}
	
	private static boolean isPrisonerAlpha() {
		return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerAlpha);
	}
	
	private static NPC getOwner() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerAlpha)) {
			return Main.game.getNpc(FortressAlphaLeader.class);
		} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerFemale)) {
			return Main.game.getNpc(FortressFemalesLeader.class);
		}
		return Main.game.getNpc(FortressMalesLeader.class);
	}
	
	private static String getOwnerDialogueIdEnding() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerAlpha)) {
			return "_ALPHA";
		} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelPrisonerFemale)) {
			return "_FEMALE";
		}
		return "_MALE";
	}
	
	private static boolean isPrisoner() {
		return isPrisonerMale() || isPrisonerFemale() || isPrisonerAlpha();
	}
	
	private static int cellTimePassed = 0;
	
	/* TODO
		She tells the one you chose to fuck you in front of her
		Then you are put in cells.
		Wake up
			Youko feeds you potion to TF into chosen type.
			Demon puts bitch collar on you, then fucks you
	 */
	
	// TODO parsing allCharacters() needs owner as second npc
	
	public static final DialogueNode PRISONER_INITIAL_SCENE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Раздета",
						UtilText.parse(getOwner(), "[npc.Name] раздевает тебя, снимая всю не запечатанную одежду!"),
						isCompanionDialogue()
							?PRISONER_INITIAL_SCENE_COMPANION_STRIP
							:PRISONER_STRIPPED) {
					@Override
					public void effects() {
						//TODO remove everything like in Rat Warrens captive
//						List<AbstractClothing> clothingRemoved = Main.game.getPlayer().unequipAllClothing(getOwner(), true);
//
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED"+getOwnerDialogueIdEnding(), getAllCharacters()));
//						
//						for(AbstractClothing clothing : clothingRemoved) {
//							Main.game.getWorlds().get(WorldType.IMP_FORTRESS_DEMON).getCell(PlaceType.FORTRESS_DEMON_TREASURY).getInventory().addClothing(clothing);
//						}
//						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().getUnequipAllClothingDescription());
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_END"+getOwnerDialogueIdEnding(), getAllCharacters()));
						
						// Equip collar and wrist restraints
						Colour c = PresetColour.CLOTHING_PINK_HOT;
						if(isPrisonerFemale()) {
							c = PresetColour.CLOTHING_BLACK;
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", c, false), true, getOwner()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_restraints", c, false), true, getOwner()));
						
						//TODO siren wants to watch them fuck your face(s)
						if(getOwner() instanceof FortressFemalesLeader) {
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
						}
					}
				};
			}
			return null;
		}
	};


	public static final DialogueNode PRISONER_INITIAL_SCENE_COMPANION_STRIP = new DialogueNode("", "", true, true) {

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
			if (index == 1) {
				return new Response(UtilText.parse(getMainCompanion(), "[npc.NamePos] поворачивает"),
						UtilText.parse(getOwner(), getMainCompanion(), "[npc.Name] раздевает твоего спутника, снимая всю [npc2.her]не запечатанную одежду!"),
						PRISONER_STRIPPED) {
					@Override
					public void effects() {
						//TODO remove everything like in Rat Warrens captive
//						List<AbstractClothing> clothingRemoved = getMainCompanion().unequipAllClothing(getOwner(), true);
//						
//						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_COMPANION"+getOwnerDialogueIdEnding(), getAllCharacters()));
//						clothingRemoved = getMainCompanion().unequipAllClothing(getOwner(), true);
//						for(AbstractClothing clothing : clothingRemoved) {
//							Main.game.getWorlds().get(WorldType.IMP_FORTRESS_DEMON).getCell(PlaceType.FORTRESS_DEMON_TREASURY).getInventory().addClothing(clothing);
//						}
//						Main.game.getTextEndStringBuilder().append(getMainCompanion().getUnequipAllClothingDescription());
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_COMPANION_END"+getOwnerDialogueIdEnding(), getAllCharacters()));

						// Equip collar and wrist restraints
						Colour c = PresetColour.CLOTHING_PINK_LIGHT;
						if(isPrisonerFemale()) {
							c = PresetColour.CLOTHING_BROWN_DARK;
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", c, false), true, getOwner()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_wrist_restraints", c, false), true, getOwner()));
						
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PRISONER_STRIPPED = new DialogueNode("", "", true, true) {

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
			
			Map<GameCharacter, SexSlot> subSlots;
			if(isCompanionDialogue()) {
				subSlots = Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
						new Value<>(getMainCompanion(), SexSlotStanding.PERFORMING_ORAL_TWO));
			} else {
				subSlots = Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL));
			}
			
			SexManagerInterface manager = new SMStanding(
					Util.newHashMapOfValues(new Value<>(getOwner(), SexSlotStanding.STANDING_DOMINANT)),
					subSlots) {
				@Override
				public boolean isPositionChangingAllowed(GameCharacter character) {
					return false;
				}
				@Override
				public SexPace getStartingSexPaceModifier(GameCharacter character) {
					if(character.isPlayer()) {
						if(index==2) {
							return SexPace.SUB_EAGER;
						} else if(index==3) {
							return SexPace.SUB_RESISTING;
						}
					}
					return null;
				}
			};
			
			if(index==1) {
				return new ResponseSex(
						"Оральный",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "Прими цену своего поражения и вместе с [com.name] готовься отсасывать [npc.name].")
							:UtilText.parse(getOwner(), "Прими цену своего поражения и готовься отсасывать [npc.name]."),
						false,
						false,
						manager,
						Util.newArrayListOfValues(getBoss()),
						null,
						PRISONER_STRIPPED_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_START_ORAL"+getOwnerDialogueIdEnding(), getAllCharacters()));
				
			} else if(index==2) {
				return new ResponseSex(
						"С готовностью к оралу",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "С радостью прими требования и вместе с [com.name] с нетерпением готовься отсасывать [npc.name].")
							:UtilText.parse(getOwner(), "С радостью прими требования и с нетерпением готовься отсасывать [npc.name]."),
						false,
						false,
						manager,
						Util.newArrayListOfValues(getBoss()),
						null,
						PRISONER_STRIPPED_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_START_ORAL_EAGER"+getOwnerDialogueIdEnding(), getAllCharacters()));

			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex(
						"Сопротивляться оралу",
						UtilText.parse(getOwner(), "Сопротивляйся [npc.name] и попытайся оттолкнуть от себя [npc.herHim]."),
						false,
						false,
						manager,
						Util.newArrayListOfValues(getBoss()),
						null,
						PRISONER_STRIPPED_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_START_ORAL_RESIST"+getOwnerDialogueIdEnding(), getAllCharacters()));
			}
			
			return null;
		}
	};
	
	public static final DialogueNode PRISONER_STRIPPED_AFTER_SEX = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_STRIPPED_AFTER_SEX"+getOwnerDialogueIdEnding(), getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Камеры",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "[npc.Name] ведет тебя и [npc2.name] к тюремному блоку цитадели...")
							:UtilText.parse(getOwner(), "[npc.Name] ведет тебя к тюремному блоку цитадели..."),
							CELLS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_CELLS);
						cellTimePassed = Main.game.getMinutesUntilTimeInMinutes(7*60);
						if(cellTimePassed<120) {
							cellTimePassed+=24*60;
						}
					}
				};	
			}
			return null;
		}
	};
	
	public static final DialogueNode CELLS = new DialogueNode("Камеры", "", false) {

		@Override
		public int getSecondsPassed() {
			return cellTimePassed*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isPrisoner()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_CELL"+getOwnerDialogueIdEnding(), getAllCharacters()));
				
			} else {
				if(isDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "CELLS_RUINS", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "CELLS", getAllCharacters()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			/* It's ok if some are randomised. I don't mind players save/loading to try to get different events.
			(Maximum times/day)Events:
				(1) Fucked by demon - unseals clothing one at a time
				(-) Fucked by imps
				(1) Siren summon
					Use as trophy foot-rest
					Watch imps fuck you
				(-) Milked/cum milked
				(1) DS watches imps fuck you as amusement
				(1) If female owner
					gets you to breed trespasser she caught
				(1) If alpha owner
					Tattoos you
					Punishes you
					Orifice training
					Walks around citadel
			*/
			
			if(isPrisoner()) {
				if(Main.game.getHourOfDay()<=8) { // Wake up event
					// Imp arrives to give breakfast. Imp is generated based on player's SO.
					// Fucks player's throat. If player refuses, gain ring gag.
					// If player has cum addict, the imp cums on food.
					
					// TF
					
				} else if(Main.game.getHourOfDay()<=11) { // Morning event
					// If being milked, append milking description
					if(getOwner().getMinutesSinceLastTimeHadSex()>36*60) {
						// Demon sex. If player refuses, gain spreader bar.
					}
					
				} else if(Main.game.getHourOfDay()<=14) { // Lunch event
					// Imp feeds you. If player has cum addict, the imp cums on food.

					// TF
					
				} else if(Main.game.getHourOfDay()<=18) { // Afternoon event
					// Use same as morning
					
				} else if(Main.game.getHourOfDay()<=21) { // Dinner event

					// TF
					
				} else if(Main.game.getHourOfDay()<=24) { // Night event
					// Sleep or Escape chance
				}
			}
			
			return null;
		}
	};
	

	public static final DialogueNode PRISONER_BREAKFAST = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/impCitadel"+getDialogueEncounterId(), "PRISONER_BREAKFAST"+getOwnerDialogueIdEnding(), getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Камеры",
						isCompanionDialogue()
							?UtilText.parse(getOwner(), getMainCompanion(), "[npc.Name] ведет тебя и [npc2.name] к тюремному блоку цитадели...")
							:UtilText.parse(getOwner(), "[npc.Name] ведет тебя к тюремному блоку цитадели..."),
							CELLS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_CELLS);
					}
				};	
			}
			return null;
		}
	};
}
