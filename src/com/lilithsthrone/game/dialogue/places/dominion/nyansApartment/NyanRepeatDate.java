package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanRepeatDate {

	private static int dateBill;
	
	private static float playerAlcoholIncrement = 0;
	
	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}

	private static NyanMum getNyanMum() {
		return ((NyanMum)Main.game.getNpc(NyanMum.class));
	}

	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
	}

	public static final DialogueNode DATE_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill = 0;
			getNyan().wearDress();
			((Nyan)getNyan()).wearCoat(true, true);
			getNyanMum().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // So Nyanmum isn't visible
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ресторан", "Сопроводить Nyan в ресторан.", DATE_RESTAURANT_1_ARRIVED);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_1_ARRIVED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			((Nyan)getNyan()).wearCoat(false, false);
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM, true); // Return Nyanmum to bedroom
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_1_ARRIVED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int cost = 350;
				return new Response("Виноградник Лисицы ("+UtilText.formatAsMoney(cost, "охват")+")", "Заказать самое дешевое вино в меню, которое, тем не менее, является очень хорошим винтажем.", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Виноградник Вульпина", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_VULPINE"));
					}
				};
				
			} else if(index==2) {
				int cost = 600;
				return new Response("Лучшее Аликорна ("+UtilText.formatAsMoney(cost, "охват")+")", "Заказать одно из более дорогих вин в меню.", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Лучшее Аликорна", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_ALICORN"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
				
			} else if(index==3) {
				int cost = 950;
				return new Response("Резерв Юко ("+UtilText.formatAsMoney(cost, "охват")+")", "Заказать самое дорогое вино в меню.", DATE_RESTAURANT_2_WINE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("Резерв Юко", true);
						UtilText.addSpecialParsingString(Util.intToString(cost), false);
						dateBill+=cost;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_YOUKO"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_2_WINE_ORDER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Разделить бутылку", "Сказать, что ты разделишь бутылку с Nyan.", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("Вода", "Заказать бутылку воды для себя.", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("Пиво", "Заказать пиво для себя.", DATE_RESTAURANT_3_MAIN_DATE) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_3_MAIN_DATE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			dateBill+=1200;
			getNyan().incrementAlcoholLevel(0.1f*2);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement*2);
		}
		@Override
		public int getSecondsPassed() {
			return 100*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_RESTAURANT_3_MAIN_DATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти ("+UtilText.formatAsMoney(dateBill, "охват")+")", "Оплатить счет и проводить Nyan до её дома.", DATE_END_RETURN) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-dateBill));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
			Main.game.getPlayer().applyFoodConsumed(10);
			Main.game.getPlayer().applyDrinkConsumed(10);
			getNyan().applyFoodConsumed(10);
			getNyan().applyDrinkConsumed(10);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("[nyanmum.Name]", "Пришло время снова встретиться с [nyanmum.name]...", POST_DATE_APARTMENT_INTERVIEW_2_FOOD);
			}
			return null;
		}
	};
	

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_2_FOOD = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Салат", "Сказать [nyanmum.name], что любимое блюдо Nyan — салат.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("salad"); }
				};
			} else if(index==2) {
				return new Response("Ципура", "Сказать [nyanmum.name], что любимое блюдо Nyan — это рыба «ципура».", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 20));
					}
				};
			} else if(index==3) {
				return new Response("Пицца", "Сказать [nyanmum.name], что любимая еда Nyan — пицца.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("pizza"); }
				};
			} else if(index==4) {
				return new Response("Кошачья мята", "Сказать [nyanmum.name], что любимое лакомство Nyan — это растение «непета кошачья», известное как кошачья мята.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("catnip"); }
				};
			} else if(index==5) {
				return new Response("Мороженое", "Сказать [nyanmum.name], что любимое лакомство Nyan — мороженое.", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() { failEffects("мороженое"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_3_HISTORY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Организовать день", "Рассказать [nyanmum.name], что Nyan раньше каждое утро планировала её день.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("планировала её день"); }
				};
			} else if(index==2) {
				return new Response("Поцеловать её", "Рассказать [nyanmum.name], что Nyan раньше каждое утро целовала её.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("целовала её"); }
				};
			} else if(index==3) {
				return new Response("Завтрак в постель", "Рассказать [nyanmum.name], что Nyan раньше каждое утро готовила ей завтрак в постель.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("готовила ей завтрак в постель"); }
				};
			} else if(index==4) {
				return new Response("Печь выпечку", "Рассказать [nyanmum.name], что Nyan раньше каждое утро пекла для неё выпечку.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 2, -20, 20));
					}
				};
			} else if(index==5) {
				return new Response("Играть на пианино", "Сказать [nyanmum.name], что Nyan раньше каждое утро играла для неё на пианино.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() { failEffects("сыграть ей на пианино"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_4_STORE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Пробел", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Space».", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("Пробел"); }
				};
			} else if(index==2) {
				return new Response("Приличный наряд", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Dapper Duds».", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("Приличный наряд"); }
				};
			} else if(index==3) {
				return new Response("Магазин платьев", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Магазин платьев».", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("Магазин платьев"); }
				};
			} else if(index==4) {
				return new Response("[nyanmum.NamePos] Изысканный", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «[nyanmum.NamePos] Finest».", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() { failEffects("[nyanmum.NamePos] Изысканный"); }
				};
			} else if(index==5) {
				return new Response("Золотые нити", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Золотые нити».", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 3, -20, 20));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_5_FLOWER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ромашки", "Сказать [nyanmum.name], что любимые цветы Nyan — ромашки.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 4, -20, 20));
					}
				};
			} else if(index==2) {
				return new Response("Тюльпаны", "Сказать [nyanmum.name], что любимые цветы Nyan — тюльпаны.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("tulips"); }
				};
			} else if(index==3) {
				return new Response("Розы", "Сказать [nyanmum.name], что любимые цветы Nyan — розы.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("roses"); }
				};
			} else if(index==4) {
				return new Response("Фуксии", "Сказать [nyanmum.name], что любимые цветы Nyan — фуксии.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("fuchsias"); }
				};
			} else if(index==5) {
				return new Response("Гортензии", "Сказать [nyanmum.name], что любимые цветы Nyan — гортензии.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() { failEffects("hydrangeas"); }
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_6_BOOK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setKnowsCharacterArea(CoverableArea.VAGINA, getNyanMum(), true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -1, 60, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Часовня кошмаров", "Сказать [nyanmum.name], что любимая книга Nyan называется «Часовня кошмаров».", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("Часовня кошмаров"); }
				};
			} else if(index==2) {
				return new Response("Прогресс павлина", "Сказать [nyanmum.name], что любимая книга Nyan называется «Прогресс павлина».", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("Прогресс павлина"); }
				};
			} else if(index==3) {
				return new Response("Один день в июне", "Сказать [nyanmum.name], что любимая книга Nyan называется «Один день в июне».", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_SUCCESS"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 20));
					}
				};
			} else if(index==4) {
				return new Response("Знак восьми", "Сказать [nyanmum.name], что любимая книга Nyan называется «Знак восьми».", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("Знак восьми"); }
				};
			} else if(index==5) {
				return new Response("Повелитель шляп", "Сказать [nyanmum.name], что любимая книга Nyan называется «Повелитель шляп».", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() { failEffects("Повелитель шляп"); }
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_PASS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumInterviewPassed, true);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/repeatDate", "POST_DATE_APARTMENT_INTERVIEW_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) { 
				return new Response("Обнять", "Nyan выглядит так, будто ей сейчас очень не помешало бы утешительное объятие.", NyanFirstDate.POST_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
}
