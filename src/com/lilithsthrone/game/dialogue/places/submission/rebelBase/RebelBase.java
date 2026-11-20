package com.lilithsthrone.game.dialogue.places.submission.rebelBase;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.8.9
 * @version 0.3.21
 * @author DSG
 */
public class RebelBase {
	
	public static final DialogueNode REBEL_BASE_ENTRANCE = new DialogueNode("Вход в пещеру", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE)) {
					return new Response("[style.colourBad(Выйти)]",
							"Это место выглядит крайне нестабильным, оно может обрушиться в любой момент. Пожалуй, лучше оставить все секреты здесь и уйти, пока не поздно.<br/>[style.italicsBad(После ухода вы больше не сможете вернуться в эту локацию!)]",
							REBEL_BASE_COLLAPSE);
				} else {
					return new Response("[style.colourGood(Выйти)]",
							"Вы осмотрели всё, что смогли найти. Пожалуй, лучше уйти, пока не поздно.<br/><i>После ухода вы больше не сможете вернуться в эту локацию!</i>",
							REBEL_BASE_COLLAPSE);
				}
			}
			return null;
		};
	};
	
	public static final DialogueNode REBEL_BASE_COLLAPSE = new DialogueNode("Ой-ой...", "", true) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "REBEL_BASE_COLLAPSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Беги!", "Спасай свою шкуру!", REBEL_BASE_ESCAPE) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION)){
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.SIDE_UTIL_COMPLETE));
						} else {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_FAILED));
						}
						Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
						Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERN_DARK);
						Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERN_DARK.getName());
					}
				};
			}
			return null;
		};
	};
	
	public static final DialogueNode REBEL_BASE_ESCAPE = new DialogueNode("", "", false, true) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "REBEL_BASE_ESCAPE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.BAT_CAVERN_DARK.getDialogue(false).getResponse(responseTab, index);
		};
	};
	
	public static final DialogueNode REBEL_BASE_CORRIDOR = new DialogueNode("Искусственная пещера", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		};
	};
	
	public static final DialogueNode REBEL_BASE_SLEEPING_AREA = new DialogueNode("Заброшенная спальня", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Открыть ящики", "Открыть сундуки.", REBEL_BASE_SLEEPING_AREA_SEARCHED){
					@Override
					public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA_CACHE_OPEN"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rbooniehat", false), 2, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtunic", false), 2, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtrousers", false), 2, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_vcboots", false), 2, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rbrassard", false), 5, false, true));
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.REBEL_BASE_SLEEPING_AREA_SEARCHED);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.REBEL_BASE_SLEEPING_AREA_SEARCHED.getName());
					}
				};
				
			} else if (index ==2) {
				return new Response("Читать журнал", "Посмотреть содержимое журнала.", REBEL_BASE_SLEEPING_AREA_JOURNAL_OPEN);
				
			} else {
				return null;
			}
		};
	};
		
	public static final DialogueNode REBEL_BASE_SLEEPING_AREA_JOURNAL_OPEN = new DialogueNode("Ветхий журнал", "", true) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA_JOURNAL_OPEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.REBEL_BASE_SLEEPING_AREA)) {
				return new Response("Закрыть", "Вы достаточно посмотрели.", REBEL_BASE_SLEEPING_AREA){
						@Override
						public void effects() {
							if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE));
							}
							
						}
				};
				
			} else if (index == 1 && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.REBEL_BASE_SLEEPING_AREA_SEARCHED)) {
				return new Response("Закрыть", "Вы достаточно посмотрели.", REBEL_BASE_SLEEPING_AREA_SEARCHED) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_ESCAPE));
							}
						}
				};
				
			} else {
				return null;
			}
		};
	};
	
	public static final DialogueNode REBEL_BASE_SLEEPING_AREA_SEARCHED = new DialogueNode("Заброшенная спальня", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "SLEEPING_AREA_SEARCHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Открыть ящики", "Вы уже открыли сундуки.", null);
				
			} else if (index ==2) {
				return new Response("Читать журнал", "Посмотреть содержимое журнала.", REBEL_BASE_SLEEPING_AREA_JOURNAL_OPEN);
				
			} else {
				return null;
			}
		};
	};
		
	public static final DialogueNode REBEL_BASE_COMMON_AREA = new DialogueNode("Заброшенная общая зона", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "COMMON_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Открыть шкаф", "Открыть металлический шкаф.", COMMON_AREA_CACHE_OPEN) {
					@Override
					public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rwebbing", false), 3, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_sbandana", false), 1, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rbandolier", false), 3, false, true));
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.REBEL_BASE_COMMON_AREA_SEARCHED);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.REBEL_BASE_COMMON_AREA_SEARCHED.getName());
					}
				};
				
			} else {
				return null;
			}
		};
	};
	
	public static final DialogueNode COMMON_AREA_CACHE_OPEN = new DialogueNode("Заброшенная общая зона", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "COMMON_AREA_CACHE_OPEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REBEL_BASE_COMMON_AREA_SEARCHED.getResponse(responseTab, index);
		};
	};
		
	public static final DialogueNode REBEL_BASE_COMMON_AREA_SEARCHED = new DialogueNode("Заброшенная общая зона", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "COMMON_AREA_SEARCHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Открыть шкаф", "Вы уже открыли этот шкаф.", null);
			} else {
				return null;
			}
		};
	};
		
	public static final DialogueNode REBEL_BASE_ARMORY = new DialogueNode("Частично обрушенная комната", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "ARMORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Открыть пакеты", "Открыть пластиковые пакеты.", ARMORY_CACHE_OPEN){
					@Override
					public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbsmg"), 3, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pboltrifle"), 2, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbrevolver"), 5, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_gbshotgun"), 1, false, true));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbomb"), 10, false, true));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/rebelBase", "ARMORY_CACHE_OPEN_FIREBOMBS"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_REBEL_BASE_FIREBOMBS));
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.REBEL_BASE_ARMORY_SEARCHED);
							Main.game.getPlayerCell().getPlace().setName(PlaceType.REBEL_BASE_ARMORY_SEARCHED.getName());
					}
				};
				
			} else {
				return null;
			}
		};
	};
	
	public static final DialogueNode ARMORY_CACHE_OPEN = new DialogueNode("Заброшенная общая зона", "", false) {
		@Override
		public String getAuthor() {
			return "DSG";
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "ARMORY_CACHE_OPEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return REBEL_BASE_ARMORY_SEARCHED.getResponse(responseTab, index);
		};
	};
	
	public static final DialogueNode REBEL_BASE_ARMORY_SEARCHED = new DialogueNode("Частично обрушенная комната", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "ARMORY_SEARCHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Открыть пакеты", "В этих обломках не осталось ничего стоящего.", null);
			} else {
				return null;
			}
		};
	};
	
	public static final DialogueNode REBEL_BASE_CAVED_IN_ROOM = new DialogueNode("Обрушившаяся комната", "", false) {
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
			return UtilText.parseFromXMLFile("places/submission/rebelBase", "REBEL_BASE_CAVED_IN_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		};
	};
}