package com.lilithsthrone.game.dialogue.places.submission;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.GamblingDenPatron;
import com.lilithsthrone.game.character.npc.submission.HazmatRat;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.GamblingDenDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.story.LyssiethReveal;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5
 * @author Innoxia
 */
public class SubmissionGenericPlaces {
	
	private static void applyClaireMeetingEffects() {
		if(Main.game.getNpc(Claire.class).isVisiblyPregnant()) {
			Main.game.getNpc(Claire.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
	}

	public static final DialogueNode WALKWAYS = new DialogueNode("Пешеходные дорожки", "", false) {
		
		@Override
		public String getAuthor() {
			return "Duner и Innoxia";
		}
		
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "WALKWAYS")
					+ (Math.random()<0.2f
							?UtilText.parseFromXMLFile("places/submission/submissionPlaces", "WALKWAYS_EXTRA")
							:"");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode TUNNEL = new DialogueNode("Туннели", "", false) {

		@Override
		public String getAuthor() {
			return "Дунер";
		}

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "TUNNEL"));

			boolean pacified = true;
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated);
			}
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated);
			}
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated);
			}
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
				pacified = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated);
			}
			
			if(!pacified) {
				UtilText.nodeContentSB.append(
						"<span style='color:"+Main.game.getPlayer().getLocationPlace().getPlaceType().getColour().toWebHexString()+";'>"
								+ UtilText.parseFromXMLFile("places/submission/submissionPlaces", "TUNNEL_IMP_CONTROL")
						+"</span>");
			}
			
			for(GameCharacter npc : Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell())) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(!pacified));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("туннели");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};

	public static final DialogueNode BAT_CAVERNS = new DialogueNode("Пещеры летучих мышей", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "BAT_CAVERNS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Пещеры летучих мышей", "Войти в пещеры летучих мышей.", PlaceType.BAT_CAVERN_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "BAT_CAVERNS_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_ENTRANCE, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RAT_WARREN = new DialogueNode("Крысиные Норы", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) || Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)==Quest.VENGAR_THREE_END) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_CLOSED");
			}
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getPlayer().getQuest(QuestLine.SIDE_VENGAR)!=Quest.VENGAR_THREE_END) {
				if(index==1) {
					return new Response("Постучать",
							"Постучать в дверь и подождать, откликнется ли кто-нибудь.",
							RAT_WARREN_KNOCK_ON_DOOR) {
						@Override
						public void effects() {
							RatWarrensDialogue.init();
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode RAT_WARREN_KNOCK_ON_DOOR = new DialogueNode("Крысиные Норы", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_KNOCK_ON_DOOR", RatWarrensDialogue.getGuards(false));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			boolean freeEntry = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore);
			if(index == 1) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)) {
					return new Response("Войти",
							"Вы не сможете попасть в Крысиные норы без веской причины...",
							null);
				} else {
					if(freeEntry) {
						return new Response("Войти",
								"Поскольку стражники узнали вас, вы можете свободно войти в Крысиные норы.",
								RatWarrensDialogue.RAT_WARREN_INITIAL_ENTRY);
					}
					return new Response("Объяснить",
							"Сказать стражникам, что вы пришли по делам Венгара от имени Акселя.",
							RatWarrensDialogue.RAT_WARREN_INITIAL_ENTRY);
				}
				
			} else if(index==2) {
				return new Response("Отступить",
						freeEntry
							?"Решить не входить в Крысиные норы, а вместо этого отойти от двери."
							:"Так как у вас здесь нет никаких дел, войти не получится. Лучше всего отступить и уйти, пока стражники не перешли от угроз к делу...",
						RAT_WARREN_STEP_BACK) {
					@Override
					public void effects() {
						if(freeEntry) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_ENTRY_STEP_BACK", RatWarrensDialogue.getGuards(false)));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "RAT_WARREN_STEP_BACK", RatWarrensDialogue.getGuards(false)));
						}
						RatWarrensDialogue.exit();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode RAT_WARREN_STEP_BACK = new DialogueNode("Крысиные Норы", "", false) {
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
			return RAT_WARREN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode GAMBLING_DEN = new DialogueNode("Игорный притон", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "GAMBLING_DEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Игорный притон", "Войти в Игорный притон.", GamblingDenDialogue.ENTRANCE) {
					@Override
					public void effects() {
						List<NPC> gamblersPresent = Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.GAMBLING_DEN).getCell(PlaceType.GAMBLING_DEN_GAMBLING));
						
						for(NPC npc : gamblersPresent) {
							if(npc instanceof GamblingDenPatron) {
								Main.game.banishNPC(npc);
							}
						}
						
						try {
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.COPPER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.SILVER, false), false);
							Main.game.addNPC(new GamblingDenPatron(Gender.getGenderFromUserPreferences(false, false), DicePokerTable.GOLD, false), false);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode LILIN_PALACE_CAVERN = new DialogueNode("Пещера", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN_MET_ELIZABETH"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_CAVERN_NOT_MET_ELIZABETH"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LILIN_PALACE_GATE = new DialogueNode("Врата дворца Lyssieth", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)
					|| (Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.SUBMISSION
						&& Main.game.getNpc(Elizabeth.class).isVisiblyPregnant()
						&& !Main.game.getNpc(Elizabeth.class).isCharacterReactedToPregnancy(Main.game.getPlayer()));
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutRoutine, false);
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE"));
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_GRANTED"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_COMPLETED"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_QUEST_GAINED"));
			
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED_INTRODUCED"));
					
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ENTRY_BLOCKED"));
			}
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			
			// First time visiting this tile:
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethIntroduced)) {
				if(index==1) {
					return new Response("Знакомство", "Рассказать суккубу и её войскам, кто ты такой.", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							Main.game.getNpc(Elizabeth.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethIntroduced, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_INTRODUCTION"));
						}
					};
				}
				return null;
			}
			
			// Completed the Siren's quest:
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
				if(Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.SUBMISSION) {
					if(Main.game.getNpc(Elizabeth.class).isVisiblyPregnant() && !Main.game.getNpc(Elizabeth.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
						if (index == 1) {
							return new Response("Беременность", "Спросить Элизабет, не нужна ли ей помощь с беременностью.", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getNpc(Elizabeth.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_PREGNANCY"));
								}
							};
						}
						
					} else {
						if (index == 1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutUniforms)) {
								return new Response("Униформа", "Вы только что спросили Элизабет об её униформе...", null);
							}
							return new Response("Униформа", "Спросить Элизабет, почему она и её войска носят историческую униформу.", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS"));
								}
							};
							
						} else if (index == 2) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutSurname)) {
								return new Response("Фамилия", "Вы только что спросили Элизабет о её фамилии...", null);
							}
							return new Response("Фамилия", "Спросить Элизабет, почему она не хочет, чтобы к ней обращались по фамилии.", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_SURNAME"));
								}
							};
							
						} else if (index == 3) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutRoutine)) {
								return new Response("Распорядок", "Вы только что спросили Элизабет о её распорядке дня...", null);
							}
							return new Response("Распорядок", "Спросить Элизабет о её распорядке дня.", LILIN_PALACE_GATE_GENERIC_TALK) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutRoutine, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_ROUTINE"));
								}
							};
						}
					}
				}
				return null;
			}

			// Handing in the Siren's quest:
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_C_SIRENS_FALL) {
				if (index == 1) {
					return new Response("Доложить", "Рассказать Элизабет о том, как ты справился с Сиреной.", LILIN_PALACE_GATE_GENERIC_TALK) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_COMPLETED_QUEST_WITH_COMBAT"));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.LYSSIETHS_RING));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_COMPLETED_QUEST_WITH_TRICKERY"));
							}
							if(!Main.game.getPlayer().hasClothingType(ClothingType.FINGER_LYSSIETHS_RING, true)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING, false), false));
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
						}
					};
					
				}
				return null;
			}
			
			// Normal dialogue, for before completing the Siren's quest:
			if (index == 1) {
				return new ResponseEffectsOnly("Отступить", "Решить отойти от Элизабет и королевской гвардии и вернуться в Submission.") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
						Main.game.setContent(new Response("", "", LILIN_PALACE_CAVERN));
					}
				};

			} else if (index == 2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutUniforms)) {
					return new Response("Униформа", "Вы только что спросили Элизабет об её униформе...", null);
				}
				return new Response("Униформа", "Спросить Элизабет, почему она и её войска носят историческую униформу.", LILIN_PALACE_GATE_GENERIC_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutUniforms, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_UNIFORMS"));
					}
				};

			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.elizabethAskedAboutSurname)) {
					return new Response("Фамилия", "Вы только что спросили Элизабет о её фамилии...", null);
				}
				return new Response("Фамилия", "Спросить Элизабет, почему она не хочет, чтобы к ней обращались по фамилии.", LILIN_PALACE_GATE_GENERIC_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.elizabethAskedAboutSurname, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_SURNAME"));
					}
				};
			} else if (index == 4) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_A_INTO_THE_DEPTHS) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
						return new Response("Аудиенция", "Спросить Элизабет, как можно заслужить аудиенцию у Lyssieth.", LILIN_PALACE_GATE_GENERIC_TALK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE_SKIP"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING, false), false));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN));
							}
						};
						
					} else {
						return new Response("Аудиенция", "Спросить Элизабет, как можно заслужить аудиенцию у Lyssieth.", LILIN_PALACE_GATE_GENERIC_TALK) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_GATE_AUDIENCE"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING), false));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL));
							}
						};
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_B_SIRENS_CALL) {
					return new Response("Аудиенция", "Ты уже спрашивал Элизабет, как заслужить аудиенцию у Lyssieth. Тебе нужно найти, а затем поработить дочь Lyssieth — «темную сирену».", null);
					
				} else {
					return new Response("Аудиенция",
							"Элизабет сказала, что Lyssieth сейчас не принимает посетителей, и, по крайней мере, на данный момент у тебя нет веской причины, чтобы видеться с ней. Возможно, в будущем тебе всё же придётся просить аудиенции у Lyssieth...",
							null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode LILIN_PALACE_GATE_GENERIC_TALK = new DialogueNode("Врата дворца Lyssieth", "", true, true) {

		@Override
		public boolean isTravelDisabled() {
			return LILIN_PALACE_GATE.isTravelDisabled();
		}
		
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
			return LILIN_PALACE_GATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILIN_PALACE = new DialogueNode("Дворец Lyssieth", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
				if(Main.game.getPlayer().hasCompanions()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_LEADS_COMPANIONS", Main.game.getPlayer().getMainCompanion()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_LEADS"));
				}
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "LILIN_PALACE_ELIZABETH_ESCORT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
					return new Response("Войти",
							"Войти во дворец вместе с Элизабет, которая затем проведёт тебя в тронный зал."
									+ (Main.game.getPlayer().hasCompanions()?"<br/>[style.italicsMinorBad(Это заставит всех твоих спутников разойтись, и они вернутся по домам.)]":""),
							LyssiethReveal.ENTRANCE_WITH_ELIZABETH) {
						@Override
						public void effects() {
							Main.game.getPlayer().removeAllCompanions(true);
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							Main.game.getNpc(Elizabeth.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
						}
					};
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) {
					return new Response("Войти",
							Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.SUBMISSION
								?"Сказать Элизабет, что ты хочешь войти во дворец, чтобы она отперла тебе двери."
								:"Сказать стражнице, что ты хочешь войти во дворец, чтобы она отперла тебе двери.",
							PlaceType.LYSSIETH_PALACE_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_ENTRANCE);
							Main.game.getNpc(Elizabeth.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
						}
					};
					
				} else {
					return new Response("Войти", "Двери заперты...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_ALPHA = new DialogueNode("Грубая крепость", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ALPHA"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified);
			
			if(index==1) {
				return new Response(canEnter?"Войти":"Подойти",
						canEnter?"Войти в крепость.":"Подойти к стражникам у входа в крепость.",
								PlaceType.FORTRESS_ALPHA_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).contains(Main.game.getNpc(FortressAlphaLeader.class))) {
								Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_ALPHA).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_ALPHA);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Лететь",
							canEnter
								?"Поскольку ты можешь просто пройти через главные ворота, в этом нет необходимости, если только ты не пытаешься похвастаться..."
								:"Перелететь через стену крепости, тем самым избежав столкновения с бесами-стражниками у ворот.",
							PlaceType.FORTRESS_ALPHA_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_ALPHA, false).contains(Main.game.getNpc(FortressAlphaLeader.class))) {
									Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_ALPHA).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_ALPHA);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_COURTYARD);
						}
					};
				} else {
					return new Response("Лететь", "Либо вы, либо кто-то из ваших спутников не умеет летать, поэтому вы не можете попасть в крепость, перелетев через стены!", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_FEMALES = new DialogueNode("Грубая крепость", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FEMALES"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified);
			
			if(index==1) {
				return new Response(canEnter?"Войти":"Подойти",
						canEnter?"Войти в крепость.":"Подойти к стражникам у входа в крепость.",
								PlaceType.FORTRESS_FEMALES_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).contains(Main.game.getNpc(FortressFemalesLeader.class))) {
								Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_FEMALES).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_FEMALES);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Лететь",
							canEnter
								?"Поскольку ты можешь просто пройти через главные ворота, в этом нет необходимости, если только ты не пытаешься похвастаться..."
								:"Перелететь через стену крепости, тем самым избежав столкновения с бесами-стражниками у ворот.",
							PlaceType.FORTRESS_FEMALES_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_FEMALES, false).contains(Main.game.getNpc(FortressFemalesLeader.class))) {
									Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_FEMALES).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_FEMALES);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_COURTYARD);
						}
					};
				} else {
					return new Response("Лететь", "Либо вы, либо кто-то из ваших спутников не умеет летать, поэтому вы не можете попасть в крепость, перелетев через стены!", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_MALES = new DialogueNode("Грубая крепость", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS"));

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_MALES"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_PACIFIED"));
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY_DEFEATED"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_ENTRY"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			boolean canEnter = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)
					|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified);
			
			if(index==1) {
				return new Response(canEnter?"Войти":"Подойти",
						canEnter?"Войти в крепость.":"Подойти к стражникам у входа в крепость.",
								PlaceType.FORTRESS_MALES_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
							if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).isEmpty()) {
								ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
							} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).contains(Main.game.getNpc(FortressMalesLeader.class))) {
								Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
							} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_MALES).isEmpty()) {
								ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_MALES);
							}
						}
						Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("Лететь",
							canEnter
								?"Поскольку ты можешь просто пройти через главные ворота, в этом нет необходимости, если только ты не пытаешься похвастаться..."
								:"Перелететь через стену крепости, тем самым избежав столкновения с бесами-стражниками у ворот.",
							PlaceType.FORTRESS_MALES_COURTYARD.getDialogue(false)) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
								if(ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).isEmpty()) {
									ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
								} else if(!ImpFortressDialogue.getImpBossGroup(WorldType.IMP_FORTRESS_MALES, false).contains(Main.game.getNpc(FortressMalesLeader.class))) {
									Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
								} else if(ImpFortressDialogue.getImpGuards(WorldType.IMP_FORTRESS_MALES).isEmpty()) {
									ImpFortressDialogue.resetGuards(WorldType.IMP_FORTRESS_MALES);
								}
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_FORTRESS_FLY_ENTRY"));
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_COURTYARD);
						}
					};
				} else {
					return new Response("Лететь", "Либо вы, либо кто-то из ваших спутников не умеет летать, поэтому вы не можете попасть в крепость, перелетев через стены!", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode IMP_FORTRESS_DEMON = new DialogueNode("Каменная цитадель", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_DEFEATED");
			}
			
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impCitadelEncountered, true);
			
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_BASE"));

			if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
							&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
						|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEYS"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_NO_KEYS"));
			}
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
					return new Response("Войти", "Войти в руины цитадели Темной Сирены.", PlaceType.FORTRESS_DEMON_ENTRANCE.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_RUINS_ENTRY"));
						}
					};
					
				} else if((Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY)
								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2)
								&& Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))
							|| Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
					return new Response("Войти", "Войти в цитадель.", FORTRESS_DEMON_ENTRANCE_KEY_ENTRY) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_ENTRANCE);
							ImpCitadelDialogue.applyEntry();

							if(!Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY"));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), false), false));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
								Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT"));
								if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonImpsDefeated)) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT_IMPS"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "IMP_CITADEL_KEY_ENTRY_REPEAT_NO_IMPS"));
								}
							}
						}
					};
					
				} else {
					return new Response("Войти", "У вас нет трех ключей, необходимых для открытия ворот...", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode FORTRESS_DEMON_ENTRANCE_KEY_ENTRY = new DialogueNode("", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ImpCitadelDialogue.ENTRANCE.getResponse(responseTab, index);
		}
	};

	// Entrance and exits:

	public static final DialogueNode SEWER_ENTRANCE = new DialogueNode("Контрольно-пропускной пункт Enforcer", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public boolean isTravelDisabled() {
			return Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getPlayer().isHasSlaverLicense() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.milkersClaireDialogue);
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.milkersClaireDialogue)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE_CLAIRE_MILKERS");
			} else {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getPlayer().isHasSlaverLicense() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.milkersClaireDialogue)) {
				if (index == 1) {
					return new Response("Забрать молочные железы", "Сказать Клэр, что вы возьмете на себя ответственность за рабов, извлеченных из Крысиных Варренов.", SEWER_ENTRANCE_MILKERS_RESOLVED) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.milkersClaireDialogue, true);
							Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE_CLAIRE_MILKERS_ACCEPTED"));
							if(RatWarrensDialogue.getMilkers().isEmpty()) {
								RatWarrensDialogue.spawnMilkers();
							}
							for(GameCharacter milker : RatWarrensDialogue.getMilkers()) {
								((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
								milker.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
								Main.game.getPlayer().addSlave((NPC) milker);
								milker.setObedience(100);
								milker.setHistory(Occupation.NPC_SLAVE);
							}
						}
					};

				} else if (index == 2) {
					return new Response("Отклонить", "Сказать Клэр, что вас не интересуют рабы, извлеченные из Крысиных Варренов.", SEWER_ENTRANCE_MILKERS_RESOLVED) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.milkersClaireDialogue, true);
							Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "SEWER_ENTRANCE_CLAIRE_MILKERS_DECLINED"));
							RatWarrensDialogue.banishMilkers();
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("Dominion", "Вернуться обратно в Dominion.", PlaceType.DOMINION_EXIT_TO_SUBMISSION.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_EXIT_TO_SUBMISSION, false);
						}
					};

				} else if (index == 2) {
					return new Response("Клэр", "Подойти к Клэр и поздороваться с ней.", CLAIRE);
					
				} else if (index == 3) {
					return new Response("Торговый автомат", "Подойти к торговому автомату, который находится прямо у аванпоста Enforcer.", VENDING_MACHINE);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode SEWER_ENTRANCE_MILKERS_RESOLVED = new DialogueNode("", "", false, true) {
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
			return SEWER_ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE = new DialogueNode("Клэр", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			responses.add(new Response("Назад", "Сказать Клэр, что тебе пора идти.", SEWER_ENTRANCE));
			

			if(Main.game.getCurrentDialogueNode()==CLAIRE_INFO_SUBMISSION_SOCIETY) {
				responses.add(new Response("Информация", "Вы уже расспрашиваете Клэр об обществе Submission.", null));
			} else {
				responses.add(new Response("Информация", "Расспросить Клэр об обществе Submission.", CLAIRE_INFO_SUBMISSION_SOCIETY) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}
			
			if(Main.game.getCurrentDialogueNode()==CLAIRE_INFO_LYSSIETH) {
				responses.add(new Response("Lyssieth", "Вы уже расспрашиваете Клэр о Lyssieth.", null));
			} else {
				responses.add(new Response("Lyssieth", "Спросить Клэр о Lyssieth.", CLAIRE_INFO_LYSSIETH) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireAskedTeleportation)) {
				if(Main.game.getCurrentDialogueNode()==CLAIRE_INFO_SWORD_ORICL) {
					responses.add(new Response("SWORD и ORICL", "Вы уже расспрашиваете Клэр о ветвях Enforcer в 'SWORD' и 'ORICL'.", null));
				} else {
					responses.add(new Response("SWORD и ORICL", "Спросить Клэр о ветвях Enforcer в 'SWORD' и 'ORICL'.", CLAIRE_INFO_SWORD_ORICL));
				}
				
			} else {
				responses.add(new Response("Телепортация", "Спросить Клэр о телепортации.", CLAIRE_INFO_TELEPORTATION) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireAskedTeleportation, true);
						applyClaireMeetingEffects();
					}
				});
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireAskedTeleportation)) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_TELEPORTATION) || !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_TELEPORTATION)) {
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
						responses.add(new Response("Телепортационные площадки",
								"Вы не сможете увидеть телепортационные площадки, пока у Enforcer из Submission не появится повод вам доверять...<br/>[style.italicsMinorBad(Вы сможете воспользоваться этим вариантом, как только завершите квест '"+QuestLine.SIDE_SLIME_QUEEN.getName()+"'.)]",
								null));
						
					} else {
						responses.add(new Response("Телепортационные площадки",
								"Сказать Клэр, что вы хотите увидеть телепортационные площадки прямо сейчас.<br/>[style.italicsQuestSide(Это запустит побочный квест, который нужно будет завершить, прежде чем вы сможете вернуться к своим прежним делам...)]",
								CLAIRE_TELEPORTATION_PADS) {
							@Override
							public void effects() {
								applyClaireMeetingEffects();
							}
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_SIDE;
							}
						});
					}
					
				} else {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.claireAskedWarehouseEscape)) {
						responses.add(new ResponseSex(
								"Рискованный секс",
								"Судя по вашему опыту на складе SWORD, похоже, у Клэр развился фетиш на рискованный секс. Вы всегда могли бы утолить её жажду, если захотите...",
								true,
								true,
								new SexManagerDefault(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Claire.class), SexSlotStanding.STANDING_SUBMISSIVE))) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								null,
								null,
								AFTER_CLAIRE_SEX,
								UtilText.parseFromXMLFile("places/submission/submissionPlaces", "START_CLAIRE_SEX")));
						
					} else {
						responses.add(new Response("Склад", "Спросить Клэр, думает ли она всё ещё о вашем побеге со склада SWORD.", CLAIRE_WAREHOUSE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.claireAskedWarehouseEscape, true);
								applyClaireMeetingEffects();
							}
						});
					}
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_TWO) {
				responses.add(new Response("Доложить назад", "Рассказать о том, что сказал слайм про «Королеву слаймов».", CLAIRE_INFO_REPORT_BACK) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(5000));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_THREE));
					}
				});
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_SUBMIT
						|| Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_FORCE
						|| Main.game.getPlayer().getQuest(QuestLine.SIDE_SLIME_QUEEN)==Quest.SLIME_QUEEN_SIX_CONVINCE) {
				responses.add(new Response("Доложить назад", "Сообщить Клэр, что вы победили Королеву слаймов.", CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(20000));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("dsg_quest_hazmat_rat_card"), false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SIDE_UTIL_COMPLETE));
					}
				});
			}
			
			if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_VENGAR)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_OPTIONAL_CLAIRE)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END)
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				responses.add(new Response("Венгар", "Попросить Клэр помочь с Венгаром.", CLAIRE_VENGAR_HELP) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}

			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
				responses.add(new Response("Анонимная наводка",
						"Спросить Клэр, можно ли анонимно передать улики преступной деятельности, чтобы подбросить аркана-рекордер с записью того, как Эль разбирается с бандой.",
						WesQuest.CLAIRE_ELLE_EVIDENCE) {
					@Override
					public void effects() {
						applyClaireMeetingEffects();
					}
				});
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_REPORT_BACK = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_REPORT_BACK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Позволить Клэр вернуться к работе и отправиться дальше своим путём.", SEWER_ENTRANCE);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeQueenHelped)) {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK_LIE");
			} else {
				return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SLIME_QUEEN_REPORT_BACK");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_VENGAR_HELP = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_VENGAR_HELP");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать",
						"Сделать так, как просит Клэр, и ждать её возвращения.",
						CLAIRE_VENGAR_HELP_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addOptionalQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_OPTIONAL_CLAIRE));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.RESONANCE_STONE), false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CLAIRE_VENGAR_HELP_WAIT = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_VENGAR_HELP_WAIT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_SUBMISSION_SOCIETY = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SUBMISSION_SOCIETY"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_LYSSIETH = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_LYSSIETH"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_TELEPORTATION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_TELEPORTATION"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CLAIRE_INFO_SWORD_ORICL = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_INFO_SWORD_ORICL"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	
	
	public static final DialogueNode CLAIRE_TELEPORTATION_PADS = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_TELEPORTATION_PADS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Телепортация!", "Телепортационная платформа активировалась, отправив вас с Клэр в неизвестном направлении!", ENFORCER_WARHOUSE_APPEARANCE) {
					@Override
					public void effects() {
						EnforcerWarehouse.initWarehouse();
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_WAREHOUSE, PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS, false);
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);

						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE"));
						
						if(Main.game.getPlayer().hasCompanions()) {
							if(Main.game.getPlayer().getMainCompanion().isElemental()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_ELEMENTAL", Main.game.getPlayer().getMainCompanion()));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_COMPANIONS", Main.game.getPlayer().getMainCompanion()));
							}
							Main.game.getPlayer().removeAllCompanions(true);
							
						} else {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_SOLO"));
						}

						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "ENFORCER_WARHOUSE_APPEARANCE_END"));
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_TELEPORTATION));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENFORCER_WARHOUSE_APPEARANCE = new DialogueNode("", "", true) {

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
			return EnforcerWarehouse.ENCLOSURE_TELEPORT_PADS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode CLAIRE_WAREHOUSE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/submission/submissionPlaces", "CLAIRE_WAREHOUSE"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_CLAIRE_SEX = new DialogueNode("Завершено", "Вы с Клэр начинаете высвобождаться из объятий друг друга...", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "AFTER_CLAIRE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CLAIRE.getResponse(responseTab, index);
		}
	};
	
	private static boolean vendingMachineInspected = false;
	private static boolean vendingMachineTalked = false;
	
	public static final DialogueNode VENDING_MACHINE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			vendingMachineInspected = false;
			vendingMachineTalked = false;
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/submissionPlaces", "VENDING_MACHINE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseTrade("Торговать",
						"Посмотреть, что сегодня продается в торговом автомате.",
						Main.game.getNpc(HazmatRat.class)) {
					@Override
					public void effects() {
						((HazmatRat)Main.game.getNpc(HazmatRat.class)).applyRestock();
					}
				};
				
			} else if(index==2) {
				return new Response("Осмотреть",
						vendingMachineInspected
							?"Вы уже внимательно осматривали этот торговый автомат..."
							:"Внимательнее осмотреть торговый автомат.",
						vendingMachineInspected
							?null
							:VENDING_MACHINE_MISC) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "VENDING_MACHINE_INSPECT"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vendingMachineTalked, true);
						vendingMachineInspected = true;
					}
				};
				
			} else if(index==3 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vendingMachineTalked)) {
				return new Response("Поговорить",
						vendingMachineTalked
							?"Вы уже пытались заговорить с этим торговым автоматом..."
							:"Убежденный, что внутри кто-то есть, вы решаете попытаться завязать с торговым автоматом разговор.",
							vendingMachineTalked
								?null
								:VENDING_MACHINE_MISC) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/submissionPlaces", "VENDING_MACHINE_TALK"));
						vendingMachineTalked = true;
					}
				};
				
			} else if(index==0) {
				return new Response("Назад",
						"[pc.Step] от торгового автомата и отправиться куда-нибудь еще...",
						SEWER_ENTRANCE);
			}
			return null;
		}
	};

	public static final DialogueNode VENDING_MACHINE_MISC = new DialogueNode("", "", true, true) {
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
			return VENDING_MACHINE.getResponse(responseTab, index);
		}
	};
}
