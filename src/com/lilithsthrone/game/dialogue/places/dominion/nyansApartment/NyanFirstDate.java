package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanFirstDate {

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
			getNyanMum().setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true); // So Nyanmum isn't visible
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanWeekendDated, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Гостиная", "Последовать за Nyan в её гостиную.", DATE_START_LOUNGE);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Nyan", "Nyan заставляет её вернуться...", DATE_START_LOUNGE_NYAN_DRESS);
			}
			return null;
		}
	};

	public static final DialogueNode DATE_START_LOUNGE_NYAN_DRESS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().wearDress();
			getNyan().wearCoat(true, true);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_START_LOUNGE_NYAN_DRESS");
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
			travelTo(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RESTAURANT);
			getNyan().wearCoat(false, false);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_SPARE_BEDROOM, true); // Return Nyanmum to bedroom
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_1_ARRIVED");
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_VULPINE"));
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_ALICORN"));
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
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_YOUKO"));
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_2_WINE_ORDER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Разделить бутылку", "Сказать, что ты разделишь бутылку с Nyan.", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_SHARE"));
						playerAlcoholIncrement = 0.1f;
					}
				};
				
			} else if(index==2) {
				return new Response("Вода", "Заказать бутылку воды для себя.", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=25;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_WATER"));
						playerAlcoholIncrement = 0f;
					}
				};
				
			} else if(index==3) {
				return new Response("Пиво", "Заказать пиво для себя.", DATE_RESTAURANT_3_PLAYER_DRINK) {
					@Override
					public void effects() {
						dateBill+=50;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_BEER"));
						playerAlcoholIncrement = 0.05f;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_3_PLAYER_DRINK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_3_PLAYER_DRINK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Заказать", "Сделайте заказ и ждите подачи закусок.", DATE_RESTAURANT_4_STARTERS) {
					@Override
					public void effects() {
						dateBill+=700;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_ORDER"));
					}
				};
				
			} else if(index==2) {
				return new Response("Заказать (свечу)", "Сделайте заказ и ждите подачи закусок. Также спросите, можно ли зажечь на вашем столе романтическую свечу.", DATE_RESTAURANT_4_STARTERS) {
					@Override
					public void effects() {
						dateBill+=700;
						dateBill+=10;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_ORDER_CANDLE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 1, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_4_STARTERS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_4_STARTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поговорить", "Поболтать с Nyan о том, как прошёл её день.", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_TALK"));
					}
				};
				
			} else if(index==2) {
				return new Response("Комплименты", "Сделать Nyan комплимент по поводу того, как красиво она выглядит.", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_COMPLIMENT"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
				
			} else if(index==3) {
				return new Response("Грязные разговоры", "Дать Nyan понять, что сегодня ночью ты намерен залезть к ней в трусики.<br/>[style.italicsMinorBad(Ты и представить не можешь, что она отреагирует на это не слишком хорошо...)]", DATE_RESTAURANT_5_MAIN_MEAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_DIRTY"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -2, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_5_MAIN_MEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.1f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_5_MAIN_MEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Разговоры о еде", "Перекинуться с Nyan парой фраз о качестве вашей трапезы.", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_TALK"));
					}
				};
				
			} else if(index==2) {
				return new Response("Флиртовать", "Флиртовать с Nyan, намекая, что этим вечером ты хотел бы чего-то большего, чем просто совместный ужин.", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_FLIRT"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
				
			} else if(index==3) {
				return new Response("Игривые прикосновения ногами", "Провести своей [pc.foot] вверх по ноге Nyan и сказать ей, что ты уже весь в предвкушении того, как трахнешь её.<br/>[style.italicsBad(Ты и представить не можешь, что она отреагирует на это совсем не так, как ты ожидаешь!)]", DATE_RESTAURANT_6_DESSERT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_FOOTSIE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), -5, 60, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DATE_RESTAURANT_6_DESSERT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().incrementAlcoholLevel(0.05f);
			Main.game.getPlayer().incrementAlcoholLevel(playerAlcoholIncrement/2);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_6_DESSERT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Заказать десерты", "Заказать отдельные десерты для себя и для Nyan.", DATE_7_END) {
					@Override
					public void effects() {
						dateBill+=500;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_7_INDIVIDUAL"));
					}
				};
				
			} else if(index==2) {
				return new Response("Заказать на двоих", "Заказать один десерт, чтобы разделить его с Nyan.", DATE_7_END) {
					@Override
					public void effects() {
						dateBill+=250;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_RESTAURANT_7_SHARE"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyan(), 2, 60, 100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DATE_7_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanRestaurantDateCompleted, true);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(dateBill), true);
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_7_END");
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
			travelTo(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
			((Nyan)getNyan()).wearCoat(true, false);
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "DATE_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Внутри", "Принять приглашение Nyan зайти к ней в квартиру.", POST_DATE_APARTMENT);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
			((Nyan)getNyan()).wearCoat(false, true);
			AbstractClothing shoes = getNyan().getClothingInSlot(InventorySlot.FOOT);
			if(shoes!=null) {
				getNyan().unequipClothingIntoVoid(shoes, true, getNyan());
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Гостиная", "Войти в гостиную...", POST_DATE_APARTMENT_LOUNGE);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_LOUNGE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Знакомство", "Представиться маме Nyan.", POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyanMum().setPlayerKnowsName(true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumIntroduced, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_1_SUPPORT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<1_000_000) {
					return new Response("Богатство", "Чтобы впечатлить [nyanmum.name] своим богатством, вам нужно иметь как минимум миллион огней!", null);
				}
				return new Response("Богатство", "Сказать [nyanmum.name], что вы обладаете значительным состоянием, поэтому содержать Nyan не составит труда.", POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_WEALTH"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 100));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getSlavesOwned().size()<20) {
					return new Response("Рабы", "Чтобы впечатлить [nyanmum.name], вам нужно иметь как минимум двадцать рабов!", null);
				}
				return new Response("Рабы",
						"Сказать [nyanmum.name], что у вас много рабов, так что деньги никогда не будут проблемой ни для вас, ни для Nyan.",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_SLAVES"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 100));
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getSubspeciesOverrideRace()!=Race.DEMON) {
					return new Response("Демон", "Вы не демон, поэтому не можете впечатлить [nyanmum.name] таким образом...", null);
				}
				return new Response("[#pc.getLilinMother().getName()]'s [pc.daughter]",
						"Признаться [nyanmum.name], что ты — прямой [pc.daughter] лилин, а значит, тебе никогда не будет трудно обеспечивать Nyan.",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_DEMON"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 10, -20, 100));
					}
				};
				
			} else if(index==4) {
				if(!Util.newArrayListOfValues(
						Occupation.CHEF,
						Occupation.MUSICIAN,
						Occupation.ARISTOCRAT
						).contains(Main.game.getPlayer().getOccupation())) {
					return new Response("Род занятий", "У тебя нет подходящей профессии, чтобы впечатлить [nyanmum.name]...", null);
				}
				return new Response("Род занятий",
						Main.game.getPlayer().getOccupation()==Occupation.ARISTOCRAT
							?"Сказать [nyanmum.name], что ты член высокопочтенной аристократической семьи, и твоего богатства и статуса будет более чем достаточно, чтобы содержать Nyan."
							:"Сказать [nyanmum.name], что ты искусный [pc.job], и поэтому всегда сможешь найти работу, чтобы обеспечивать Nyan.",
						POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_OCCUPATION"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 100));
					}
				};
				
			} else if(index==5) {
				return new Response("Сделать всё возможное", "Сказать [nyanmum.name], что ты приложишь все усилия...", POST_DATE_APARTMENT_INTERVIEW_2_FOOD) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_DO_BEST"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_LILAYA_REVEAL"));
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_2_FOOD = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_2_FOOD_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Салат", "Сказать [nyanmum.name], что любимое блюдо Nyan — салат.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("salad"); }
				};
			} else if(index==2) {
				return new Response("Ципура", "Сказать [nyanmum.name], что любимое блюдо Nyan — это рыба «ципура».", POST_DATE_APARTMENT_INTERVIEW_3_HISTORY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 1, -20, 20));
					}
				};
			} else if(index==3) {
				return new Response("Пицца", "Сказать [nyanmum.name], что любимая еда Nyan — пицца.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("pizza"); }
				};
			} else if(index==4) {
				return new Response("Кошачья мята", "Сказать [nyanmum.name], что любимое лакомство Nyan — это растение «непета кошачья», известное как кошачья мята.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("catnip"); }
				};
			} else if(index==5) {
				return new Response("Мороженое", "Сказать [nyanmum.name], что любимое лакомство Nyan — мороженое.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_3_HISTORY_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Организовать день", "Рассказать [nyanmum.name], что Nyan раньше каждое утро планировала её день.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("планировала её день"); }
				};
			} else if(index==2) {
				return new Response("Поцеловать её", "Рассказать [nyanmum.name], что Nyan раньше каждое утро целовала её.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("целовала её"); }
				};
			} else if(index==3) {
				return new Response("Завтрак в постель", "Рассказать [nyanmum.name], что Nyan раньше каждое утро готовила ей завтрак в постель.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("готовила ей завтрак в постель"); }
				};
			} else if(index==4) {
				return new Response("Печь выпечку", "Рассказать [nyanmum.name], что Nyan раньше каждое утро пекла для неё выпечку.", POST_DATE_APARTMENT_INTERVIEW_4_STORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 2, -20, 20));
					}
				};
			} else if(index==5) {
				return new Response("Играть на пианино", "Сказать [nyanmum.name], что Nyan раньше каждое утро играла для неё на пианино.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_4_STORE_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Пробел", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Space».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Пробел"); }
				};
			} else if(index==2) {
				return new Response("Приличный наряд", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Dapper Duds».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Приличный наряд"); }
				};
			} else if(index==3) {
				return new Response("Магазин платьев", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Магазин платьев».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Магазин платьев"); }
				};
			} else if(index==4) {
				return new Response("[nyanmum.NamePos] Изысканный", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «[nyanmum.NamePos] Finest».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("[nyanmum.NamePos] Изысканный"); }
				};
			} else if(index==5) {
				return new Response("Золотые нити", "Сказать [nyanmum.name], что магазин Nyan раньше назывался «Золотые нити».", POST_DATE_APARTMENT_INTERVIEW_5_FLOWER) {
					@Override
					public void effects() {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_5_FLOWER_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ромашки", "Сказать [nyanmum.name], что любимые цветы Nyan — ромашки.", POST_DATE_APARTMENT_INTERVIEW_6_BOOK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 4, -20, 20));
					}
				};
			} else if(index==2) {
				return new Response("Тюльпаны", "Сказать [nyanmum.name], что любимые цветы Nyan — тюльпаны.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("tulips"); }
				};
			} else if(index==3) {
				return new Response("Розы", "Сказать [nyanmum.name], что любимые цветы Nyan — розы.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("roses"); }
				};
			} else if(index==4) {
				return new Response("Фуксии", "Сказать [nyanmum.name], что любимые цветы Nyan — фуксии.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("fuchsias"); }
				};
			} else if(index==5) {
				return new Response("Гортензии", "Сказать [nyanmum.name], что любимые цветы Nyan — гортензии.", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK");
		}
		private void failEffects(String foodName) {
			UtilText.addSpecialParsingString(foodName, true);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_6_BOOK_FAIL"));
			Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), -5, -20, 100));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Часовня кошмаров", "Сказать [nyanmum.name], что любимая книга Nyan называется «Часовня кошмаров».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Часовня кошмаров"); }
				};
			} else if(index==2) {
				return new Response("Прогресс павлина", "Сказать [nyanmum.name], что любимая книга Nyan называется «Прогресс павлина».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Прогресс павлина"); }
				};
			} else if(index==3) {
				return new Response("Один день в июне", "Сказать [nyanmum.name], что любимая книга Nyan называется «Один день в июне».", POST_DATE_APARTMENT_INTERVIEW_PASS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(ClothingEmporium.incrementAffection(getNyanMum(), 5, -20, 20));
					}
				};
			} else if(index==4) {
				return new Response("Знак восьми", "Сказать [nyanmum.name], что любимая книга Nyan называется «Знак восьми».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Знак восьми"); }
				};
			} else if(index==5) {
				return new Response("Повелитель шляп", "Сказать [nyanmum.name], что любимая книга Nyan называется «Повелитель шляп».", POST_DATE_APARTMENT_INTERVIEW_FAIL) {
					@Override
					public void effects() { failEffects("Повелитель шляп"); }
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_FAIL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_FAIL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Поскольку вы не хотите рисковать отношениями с Nyan, у вас просто нет другого выбора, кроме как сделать так, как говорит [nyanmum.name], и уйти...", POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_FAIL_LEAVE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index); //TODO test
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
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_INTERVIEW_PASS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) { 
				return new Response("Обнять", "Nyan выглядит так, будто ей сейчас очень не помешало бы утешительное объятие.", POST_DATE_APARTMENT_BEDROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_NYAN_BEDROOM);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поцеловать", "Дать Nyan то, чего она хочет...", POST_DATE_APARTMENT_BEDROOM_KISS);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_KISS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			AbstractClothing dress = getNyan().getClothingInSlot(InventorySlot.TORSO_UNDER);
			if(dress!=null) {
				getNyan().unequipClothingIntoVoid(dress, true, getNyan());
			}
			getNyan().removePersonalityTrait(PersonalityTrait.STUTTER);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Куннилингус", "Дать похотливой кошкодевочке то, чего она хочет, отсосав ей.",
						true, true,
						new SMNyanSex(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotSitting.SITTING))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
								}
							}
						},
						null,
						null,
						POST_DATE_APARTMENT_BEDROOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Фингеринг", "Нежно толкнуть Nyan на кровать и, целуя её, начать ласкать её пальцами.",
						true, true,
						new SMNyanSex(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN_TWO)),
								Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isExposeAtStartOfSexMapRemoval(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						POST_DATE_APARTMENT_BEDROOM_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_KISS_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> receiveOralResponses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (минет)":""), "Поскольку у тебя нет доступа к члену, ты не можешь заняться с Nyan сексом в позе «69»!", null));

				} else if(Main.game.getPlayer().isTaur()) {
					receiveOralResponses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (минет)":""), "Из-за строения нижней части тела [pc.legRace], вы не можете использовать эту позу!", null));
						
				} else {
					receiveOralResponses.add(
							new ResponseSex("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (минет)":""), "Заставить Nyan сесть тебе на лицо и наклониться, чтобы она сосала твой член, пока ты во второй раз ласкаешь её губами.",
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SIXTY_NINE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasPenisIgnoreDildo()?" (куннилингус)":""), "Поскольку у тебя нет доступа к влагалищу, ты не можешь заняться с Nyan сексом в позе «69»!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					receiveOralResponses.add(new Response("Шестьдесят девять"+(Main.game.getPlayer().hasVagina()?" (куннилингус)":""), "Из-за строения нижней части тела [pc.legRace], вы не можете использовать эту позу!", null));
						
				} else {
					receiveOralResponses.add(
							new ResponseSex("Шестьдесят девять"+(Main.game.getPlayer().hasPenisIgnoreDildo()?" (куннилингус)":""), "Заставить Nyan сесть тебе на лицо и наклониться, чтобы она отсосала тебе, пока ты отвечаешь ей взаимностью.",
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SIXTY_NINE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				if(!penisAccess) {
					receiveOralResponses.add(new Response("Получить минет", "Поскольку у тебя нет доступа к члену, ты не можешь заставить Nyan сосать твой член!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Получить минет", "Поменяться местами с Nyan и заставить её сосать твой член.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					receiveOralResponses.add(new Response("Принять куннилингус", "Поскольку у тебя нет доступа к влагалищу, ты не можешь заставить Nyan заняться куннилингусом!", null)); 
				} else {
					receiveOralResponses.add(
							new ResponseSex("Принять куннилингус", "Поменяться местами с Nyan и заставить её ответить взаимностью.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
										}
									},
									null,
									null,
									POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(index==1) {
				return new Response("Спать", "Дать Nyan отдохнуть и лечь спать вместе с ней в её постель.", POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP_START"));
						getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
						Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Фейсситтинг", "Заставить Nyan сесть тебе на лицо и во второй раз отсосать ей.", 
								true, true,
								new SMNyanSex(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
										Util.newHashMapOfValues(new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
										return Util.newHashMapOfValues(
												new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.NIPPLES)));
									}
								},
								null,
								null,
								POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_FACE_SITTING")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						};
			
			} else if(index>0 && index-3<receiveOralResponses.size()) {
				return receiveOralResponses.get(index-3);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX = new DialogueNode("Завершено", "Nyan кажется совершенно измотанной...", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Спать", "Дать Nyan отдохнуть и лечь спать вместе с ней в её постель.", POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SECOND_SEX_SLEEP_START"));// These names...
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_POST_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Кухня", "Отправиться на кухню, чтобы найти Nyan.", POST_DATE_APARTMENT_BEDROOM_MORNING);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_MORNING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			getNyanMum().wearCasual();
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Выпечка", "Съесть выпечку, которую Nyan приготовила для тебя и [nyanmum.name].", POST_DATE_APARTMENT_BEDROOM_MORNING_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_APARTMENT_BEDROOM_MORNING_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Прощай", "Поцеловать Nyan на прощание и сказать, что скоро увидишься с ней.", POST_DATE_NO_CONTENT_END) {
					@Override
					public void effects() {
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/firstDate", "POST_DATE_APARTMENT_BEDROOM_MORNING_END_LEAVE"));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DATE_NO_CONTENT_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
}
