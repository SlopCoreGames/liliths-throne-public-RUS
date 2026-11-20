package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.npc.dominion.Ashley;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.99
 * @version 0.3.5.5
 * @author Kumiko, Innoxia
 */
public class DreamLover {

	public static final DialogueNode EXTERIOR = new DialogueNode("Мечтатель (Снаружи)", "-", false) {

		@Override
		public String getAuthor() {
			return "Кумико";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Войти", "Войти в «Мечтателя».", EXTERIOR_CLOSED);
					}
					return new Response("Войти", "Войти в «Мечтателя».", ENTRY);
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};

	public static final DialogueNode EXTERIOR_CLOSED = new DialogueNode("Мечтатель (Снаружи)", "-", false, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "EXTERIOR_CLOSED");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Войти", "«Мечтатель» сейчас закрыт, так что, если вы хотели здесь что-то купить, придется вернуться в другое время.", null);
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRY = new DialogueNode("Любимец снов", "-", true) {

		@Override
		public String getAuthor() {
			return "Кумико";
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ashleyIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "ENTRY_REPEAT"));

				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ashleyAttitude)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "ENTRY_REPEAT_ATTITUDE"));
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "ENTRY");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ashleyIntroduced)) {
				if (index == 1) {
					return new Response("Исследовать", "Осмотреть полки.", EXPLORE_SHELVES) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ashleyIntroduced, true);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				
				if (index == 1) {
					return new ResponseTrade("Торговать", "Побродить по магазину и посмотреть, какие товары есть в продаже...", Main.game.getNpc(Ashley.class));
					
				} else if(index==2 && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ashleyAttitude)) {
					return new Response("Противостоять Эшли", "Что это за манера поведения? Подойдите к прилавку и выскажите ей всё, что думаете.", CONFRONT_ASHLEY) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ashleySexToysDiscovered)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "SEX_TOY_DISCOVERY"));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ashleySexToysDiscovered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ashleyAttitude, true);
						}
					};
					
				} else if (index == 0) {
					return new Response("Уйти", "Вернуться в Торговую аркаду.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "EXIT"));
						}
					};
	
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode EXPLORE_SHELVES = new DialogueNode("Любимец снов", "-", true, true) {

		@Override
		public String getAuthor() {
			return "Кумико";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "EXPLORE_SHELVES");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Противостоять Эшли", "Что это за манера поведения? Подойдите к прилавку и выскажите ей всё, что думаете.", CONFRONT_ASHLEY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "SEX_TOY_DISCOVERY"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ashleyAttitude, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ashleySexToysDiscovered, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Помолчать", "Пожалуй, лучше просто игнорировать странное поведение этого человека...", IGNORE_ASHLEY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "SEX_TOY_DISCOVERY"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ashleySexToysDiscovered, true);
					}
				};
				
			}  else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CONFRONT_ASHLEY = new DialogueNode("Любимец снов", "-", true, true) {

		@Override
		public String getAuthor() {
			return "Кумико";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "CONFRONT_ASHLEY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode IGNORE_ASHLEY = new DialogueNode("Любимец снов", "-", true, true) {

		@Override
		public String getAuthor() {
			return "Кумико";
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/dreamLover", "IGNORE_ASHLEY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRY.getResponse(responseTab, index);
		}
	};
	
}
