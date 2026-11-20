package com.lilithsthrone.game.dialogue.encounters;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.4
 * @version 0.3.21
 * @author Innoxia, DSG
 */
public class BatCavernsEncounterDialogue {

	public static final DialogueNode FIND_ITEM = new DialogueNode("Выброшенный предмет", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR) || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_LIGHT)) {
				if(((AbstractItem) AbstractEncounter.getRandomItem()).getItemType()==ItemType.MUSHROOM) {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_MUSHROOMS_LIGHT")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_ITEM_LIGHT")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
				}
				
			} else {
				if(((AbstractItem) AbstractEncounter.getRandomItem()).getItemType()==ItemType.MUSHROOM) {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_MUSHROOMS_DARK")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/batCaverns", "FIND_ITEM_DARK")
							+ "<p style='text-align:center;'>"
								+ "<b>"+ AbstractEncounter.getRandomItem().getDisplayName(true)+ "</b>"
							+ "</p>";
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Взять", "Добавить  " + AbstractEncounter.getRandomItem().getName() + " в ваш инвентарь.", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem((AbstractItem) AbstractEncounter.getRandomItem(), true, true));
					}
				};
				
			} else if (index == 2) {
				return new Response("Уйти", "Оставить  " + AbstractEncounter.getRandomItem().getName() + " на полу.", Main.game.getDefaultDialogue(false));
				
			} else {
				return null;
			}
		}
	};
		
	public static final DialogueNode REBEL_BASE_DISCOVERED = new DialogueNode("Странная рукоять", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE);
			Main.game.getPlayerCell().getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getName());
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_HANDLE_REFUSED));
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
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DISCOVERED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Потянуть за ручку", "Что может пойти не так?", REBEL_BASE_DOOR_NO_PASS);

			} else if (index == 2) {
				return new Response("Оставить всё как есть", "Ничего хорошего не приносило привычка дергать за странные ручки в пещерах.", PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_DOOR_NO_PASS = new DialogueNode("Странная рукоять", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_ONE));
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
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_NO_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Тянуть сильнее", "Ручка не поддается. Похоже, тебе действительно нужен пароль.", null);
					
			} else if (index == 2) {
				return new Response("Оставить всё как есть", "Пойти поискать пароль.", PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_ONE = new DialogueNode("Обрывок из Журнала", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO));
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_DARK ||
				Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rebelBaseDarkPassFound, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rebelBaseLightPassFound, true);
			}
		}
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
			StringBuilder sb = new StringBuilder();

			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_LIGHT) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_LIGHT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_DARK"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_ONE"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Это лишь часть пароля, нужно найти вторую.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_TWO = new DialogueNode("Еще один обрывок из Журнала", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE));
		}
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
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.BAT_CAVERN_LIGHT) {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_LIGHT"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_DARK"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_TWO"));
			
			return sb.toString();	   
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Ты нашел обе части пароля. Когда будешь готов, можешь возвращаться к той загадочной ручке.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_SEARCH_FAILED = new DialogueNode("Не повезло", "", false, true) {
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
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_SEARCH_FAIL");   
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode REBEL_BASE_PASSWORD_SILLY = new DialogueNode("Цена времени", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_REBEL_BASE, Quest.SIDE_UTIL_COMPLETE));
		}
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
			return UtilText.parseFromXMLFile("places/submission/batCaverns", "REBEL_BASE_DOOR_PASS_SILLY");   
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Ты победил. Ура!", Main.game.getDefaultDialogue(false)) {
				    @Override
				    public void effects() {
						Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE).getPlace().setPlaceType(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR);
						Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR).getPlace().setName(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR.getName());
				    }
				};
			}
			return null;
		}
	};
}
