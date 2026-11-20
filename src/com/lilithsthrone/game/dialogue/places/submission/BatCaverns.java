package com.lilithsthrone.game.dialogue.places.submission;

import java.time.DayOfWeek;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.BatCavernsEncounterDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.3
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class BatCaverns {
	
	private static Response getElleSearchResponse() {
		if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_1) {
			if(Main.game.getDayOfWeek()!=DayOfWeek.WEDNESDAY || Main.game.getHourOfDay()<12 || Main.game.getHourOfDay()>=15) {
				return new Response("Искать Элль",
						"Ты сможешь найти Элль только в "
						+ (Main.game.getDayOfWeek()!=DayOfWeek.WEDNESDAY
							?"[style.italicsBad(среду)]"
							:"[style.italicsGood(среду)]")
						+ " между "
						+ ((Main.game.getHourOfDay()<12 || Main.game.getHourOfDay()>=15)
								?"[style.italicsBad([units.time(12)]-[units.time(15)])]"
								:"[style.italicsGood([units.time(12)]-[units.time(15)])]")
						+ "!",
						null);
				
			} else {
				return new Response("Искать Элль", "Начать поиски любых следов Элль здесь, в Пещерах Летучих Мышей...", WesQuest.ELLE_SEARCH);
			}
		}
		return null;
	}
	
	public static final DialogueNode STAIRCASE = new DialogueNode("Винтовая лестница", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "STAIRCASE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Покорность", "Вернуться обратно в Submission.", PlaceType.SUBMISSION_BAT_CAVERNS.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_BAT_CAVERNS, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SHAFT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.getPlayer().isPartyAbleToFly()) {
						return new Response("Dominion", "Так как твои спутники не умеют летать, ты не можешь воспользоваться шахтой, чтобы подняться в Dominion...", null);
						
					} else {
						return new Response("Dominion", "Взлететь вверх по шахте, чтобы вернуться в Dominion.", SHAFT_FLY_UP) {
							@Override
							public void effects() {
								if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS)==null) {
									Cell referenceCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_WAREHOUSES);
									Cell shaftCell = Main.game.getWorlds().get(WorldType.DOMINION).getCell(new Vector2i(referenceCell.getLocation().getX()+2, referenceCell.getLocation().getY()));
									shaftCell.getPlace().setPlaceType(PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS);
									shaftCell.getPlace().setName(PlaceType.BAT_CAVERN_SHAFT.getName());
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT_FLY_UP_FIRST_TIME"));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SHAFT_FLY_UP"));
								}
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_EXIT_TO_BAT_CAVERNS, false);
							}
						};
					}
					
				} else {
					return new Response("Dominion", "Поскольку вы не умеете летать, вы не можете использовать шахту, чтобы подняться в Dominion...", null);
				}
			}
			return null;
		}
	};

	public static final DialogueNode SHAFT_FLY_UP = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DominionPlaces.CITY_EXIT_BAT_CAVERNS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAVERN_DARK = new DialogueNode("Темная пещера", "", false) {
		@Override
		public String getAuthor() {
			return "Duner и Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() { // If this is going to be changed, bear in mind that this is called in the REBEL_BASE DialogueNodes below
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_DARK"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("темные глубины пещеры");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
				
			} else if(index==4
				    && (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				    	|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseDarkPassFound)) {
					    if (Util.random.nextInt(100) <= 20 + (Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) ? 30 : 0)) {
							if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
							    return new Response("Искать пароль", 
										"Вглядеться в темноту и усерднее искать пароль от таинственной двери.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_ONE);
							    
							} else {
							    return new Response("Искать пароль", 
										"Вглядеться в темноту и усерднее искать оставшуюся часть пароля от таинственной двери.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_TWO);
							}
					    } else {
					    	return new Response("Искать пароль", 
									"Вглядеться в темноту и усерднее искать пароль от таинственной двери.", 
									BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SEARCH_FAILED);
					    }
					    
					} else {
					    return new Response("Искать пароль", 
							    "Вы уже нашли пароль в этой области.", 
							    null);
					}
					
			} else if(index==5
				&& Main.game.isSillyMode()
				&& (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
				    return new Response("Я очень занятой [pc.man]!", 
							    "Какая пустая трата времени.<br/>[style.boldBad(Это пропустит весь контент и награды за квест «Разграбление могил»!)]", 
							    BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SILLY);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAVERN_LIGHT = new DialogueNode("Биолюминесцентная пещера", "", false) {
		@Override
		public String getAuthor() {
			return "Duner и Innoxia";
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "CAVERN_LIGHT"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("биолюминесцентный лес");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
				
			} else if(index==4
				    && (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				    	|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseLightPassFound)) {
					    if (Util.random.nextInt(100) <= 20 + (Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) ? 30 : 0)) {
							if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
							    return new Response("Искать пароль", 
										"Покопаться в грибах и получше поискать пароль от таинственной двери.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_ONE);
							} else {
							    return new Response("Искать пароль", 
										"Покопаться в грибах и получше поискать оставшуюся часть пароля от таинственной двери.", 
										BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_TWO);
							}
					    } else {
					    	return new Response("Искать пароль", 
									"Покопаться в грибах и получше поискать пароль от таинственной двери.", 
									BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SEARCH_FAILED);
					    }
					} else {
					    return new Response("Искать пароль", 
							    "Вы уже нашли пароль в этой области.", 
							    null);
					}
			} else if(index==5
				&& Main.game.isSillyMode()
				&& (Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_ONE
				|| Main.game.getPlayer().getQuest(QuestLine.SIDE_REBEL_BASE) == Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
				    return new Response("Я очень занятой [pc.man]!", 
							    "Какая пустая трата времени.<br/>[style.boldBad(Это пропустит весь контент и награды за квест «Разграбление могил»!)]", 
							    BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_SILLY);
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER = new DialogueNode("Подземная река", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("берега реки");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER_BRIDGE = new DialogueNode("Грибной мост", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_BRIDGE"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("окрестности моста");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode RIVER_END = new DialogueNode("Подземная река", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "RIVER_END"));
			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				sb.append(((NPC) npc).getPresentInTileDescription(false));
			});
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("берега реки");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3) {
				return getElleSearchResponse();
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_LAKE = new DialogueNode("Озеро Slime", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE"));
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_UNKNOWN"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_THREE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_KNOWLEDGE"));
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_QUEEN_GUESS"));
				
			}

			Main.game.getNonCompanionCharactersPresent().forEach((npc) -> {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(false));
			});
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return AbstractEncounter.exploreArea("берега озера");
				
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
						
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("Остров на лодке", "Вы могли бы воспользоваться лодкой, чтобы пересечь озеро и добраться до острова.", SLIME_LAKE_ISLAND) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT"));
						if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
						}
					}
				};
				
			} else if(index==4 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Долететь до острова", "Перелететь на остров.", SLIME_LAKE_ISLAND) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY"));
							if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_PACIFIED"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_READY_FIGHT"));
							}
						}
					};
				} else {
					return new Response("Долететь до острова", "Вы не умеете летать. Похоже, вам придется воспользоваться лодкой...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SLIME_LAKE_ISLAND = new DialogueNode("Озеро Slime", "", true) {
		
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
				return new Response("Исследовать", "Чтобы исследовать эту область, вам нужно оказаться на другом берегу озера!", null);
						
			} else if(index==2 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				return new Response("Вернуться на лодке", "Вернуться на другой берег озера на лодке.", SLIME_LAKE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_BOAT_RETURN"));
					}
				};
				
			} else if(index==3 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_TWO)) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Вернуться полетом", "Вернуться на другой берег озера полетом.", SLIME_LAKE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/batCaverns", "SLIME_LAKE_FLY_RETURN"));
						}
					};
					
				} else {
					return new Response("Вернуться полетом", "Вы не умеете летать. Похоже, вам придется воспользоваться лодкой...", null);
				}
				
			} else if (index==4) {
				return new ResponseEffectsOnly(
						"Открыть дверь",
						"Толкнуть переднюю дверь башни и войти внутрь."){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.SLIME_QUEENS_LAIR_GROUND_FLOOR, PlaceType.SLIME_QUEENS_LAIR_ENTRANCE);
								Main.game.getTextStartStringBuilder().append(
										"<p>Подойдя к подножию каменной башни, вы кладете [pc.a_hand] на дубовую дверь с железными решетками и решительным толчком открываете её, входя внутрь.</p>");
								if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR));
								}
								Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
							}
						};
			}
			return null;
		}
	};
		
	public static final DialogueNode REBEL_BASE_ENTRANCE_HANDLE = new DialogueNode("Странная рукоять", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(CAVERN_DARK.getContent());
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_HANDLE"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_ONE)) {
					return new Response("Потянуть за ручку", "Что может пойти не так?", BatCavernsEncounterDialogue.REBEL_BASE_DOOR_NO_PASS);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
					return new Response("Потянуть за ручку", "Ручка не поддается. Похоже, тебе действительно нужен пароль.", null);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE)) {
					return new Response("Потянуть за ручку", "У вас нет полного пароля!", null);
					
				} else if (Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)) {
					return new Response("Потянуть за ручку", "Теперь у тебя есть полный пароль, так что ты можешь попробовать снова потянуть за ручку... если действительно хочешь.", REBEL_BASE_DOOR_OPENED) {
						@Override
						public void effects() {
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
						}
					};
				}
			} else {
				return CAVERN_DARK.getResponse(responseTab, index-1);
			}
			return null;
		}
	};
			
	public static final DialogueNode REBEL_BASE_DOOR_OPENED = new DialogueNode("Скрытый проход", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
			Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
		}
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_OPENED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Войти", "Эта пещера не является природным образованием. Кто-то её построил, а значит, она должна куда-то вести.", PlaceType.REBEL_BASE_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION));
							Main.game.getPlayer().setLocation(WorldType.REBEL_BASE, PlaceType.REBEL_BASE_ENTRANCE);
					}
				};
				
			} else if (index == 2) {
				return new Response("Не входить", "Кто знает, что таится в этой пещере...", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_ENTRANCE_EXTERIOR = new DialogueNode("Скрытая пещера", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(CAVERN_DARK.getContent());
			
			if (Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE) || Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_EXTERIOR_COLLAPSED"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_ENTRANCE_EXTERIOR"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE) 
					&& !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)) {
				if (index == 1) {
					return new Response("Войти", "Эта пещера не является природным образованием. Кто-то её построил, а значит, она должна куда-то вести.", PlaceType.REBEL_BASE_ENTRANCE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION));
							Main.game.getPlayer().setLocation(WorldType.REBEL_BASE, PlaceType.REBEL_BASE_ENTRANCE);
						}
					};
				}
				
			} else { // If cave is cleared, give normal responses
				return CAVERN_DARK.getResponse(responseTab, index);
			}
			return null;
		}
	};
		
}
