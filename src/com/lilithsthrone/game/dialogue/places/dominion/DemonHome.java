package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.Month;

import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Felicia;
import com.lilithsthrone.game.character.npc.dominion.Fiammetta;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment.FeliciaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DemonHome {
    
    private static Felicia getFelicia() {
        return ((Felicia)Main.game.getNpc(Felicia.class));
    }
	
    private static String getAdditionalDescriptions() {
    	StringBuilder sb = new StringBuilder();
    	
		if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
			sb.append(
					"<p>Бушующий над головой шторм Арканы привел в этот район целую толпу демонов-Enforcer. Невозмутимые перед возбуждающей мощью грозовых раскатов, эти элитные Enforcer пристально следят за вами, пока вы проходите через почти опустевшую местность, известную как Дом Демонов. Под их бдительным взором никто не сможет напасть на вас, что позволяет вам спокойно продолжать свой путь...</p>");
		}

		if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
			sb.append(
				"<p>"
					+ "<b style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+";'>Октябрь;</b> <b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Месяц Lilith:</b><br/>Оранжевые, черные и фиолетовые флаги развеваются почти в каждом окне, а подняв взгляд, вы видите, что через всю улицу натянуты огромные баннеры, каждый из которых украшен новым лозунгом в честь правления Lilith. Редкие демоны, которых вы встречаете, обычно наряжены в костюмы в духе Хэллоуина, что лишь добавляет жуткой атмосферы этому месту.</p>");
		}
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
			sb.append(
				"<p>Рабочие-олени отлично справляются с расчисткой улиц Dominion от снега, но крыши, деревья и верхушки фонарных столбов покрыты толстым слоем белого одеяла. Вы видите, как ваше дыхание вырывается изо рта маленьким облачком пара, но, несмотря на явные признаки ледяного воздуха, ваша арканная Аура защищает тело от холода.</p>");
		}
		
		return sb.toString();
    }
    
	public static final DialogueNode DEMON_HOME_GATE = new DialogueNode("Demon Home (Ворота)", "Демоний Дом", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<p>Здесь, поперек улицы, возведены огромные железные ворота, отделяющие обычные районы Dominion от земель, известных как «Demon Home». Здесь дежурят полдюжины элитных демонических Enforcer, пристально следящих за каждым входящим и выходящим.</p><p>Когда вы идете вперед, чтобы пройти сквозь ворота, вы замечаете, что один из этих демонических стражей пристально смотрит на вас. Игнорируя их пронзительный взгляд, вы решительно шагаете вперед, с облегчением вздыхая, когда проходите на другую сторону без лишних вопросов.</p>");
			
			UtilText.nodeContentSB.append(getAdditionalDescriptions());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET = new DialogueNode("Демоний Дом", "Демоний Дом", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			
			UtilText.nodeContentSB.append(
					"<p>От широких, вымощенных мрамором улиц до безупречных фасадов зданий в стиле регентства — здесь совершенно очевидно, что район «Демоньего дома» является одним из самых элитных в Dominion. Повсюду расставлены многочисленные искусно вырезанные статуи, большинство из которых изображают ту или иную форму демона; учитывая их тематику, невольно предполагаешь, что именно эти скульптуры дали району его название.</p><p>Пока ты идешь по улице, мимо тебя мелькают огороженные частные сады; их пышная зелень помогает разбавить монотонность кремово-белых каменных фасадов окружающих зданий. Несмотря на то, что в Демоньем доме чуть спокойнее, чем в других районах Dominion, ты замечаешь, что на улицах патрулирует чуть больше Enforcer; это служит доказательством того, что богатым и влиятельным жителям города обеспечивается усиленная защита.</p>");
			
			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME_DADDY)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_DEMON.toWebHexString()+";'>[daddy.NamePos] место жительства:</b><br/>[daddy.NamePos] квартира находится именно в этом районе Демоньего дома."
							+ Daddy.getAvailabilityText()
						+ "</p>");
			}

			if(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME_ARTHUR)) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_HUMAN.toWebHexString()+";'>Башни Солти:</b><br/>Жилой дом Артура, «Башни Солти», расположен именно в этом районе Демоньего дома.</p>");
			}

			UtilText.nodeContentSB.append(getAdditionalDescriptions());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_ARTHUR = new DialogueNode("Демоний Дом", "Демоний Дом", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Башни Солти", "Найди квартиру Артура в здании, следуя инструкциям, которые дала тебе Lilaya.", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Башни Солти", "Направляйся к жилому дому Башни Солти.", DEMON_HOME_ARTHURS_APARTMENT);
					
				} else {
					return null;
				}

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_ZARANIX = new DialogueNode("Демоний Дом", "Демоний Дом", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_H_THE_GREAT_ESCAPE) {
					return new Response("Дом Зараникса", "Чуть дальше по дороге от дома Arthur стоит жилище Zaranix — того самого демона, о котором тебе рассказывала Scarlett.", ZaranixHomeGroundFloor.OUTSIDE);
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
					return new Response("Дом Зараникса", "Навестить Zaranix еще раз.", ZaranixHomeGroundFloorRepeat.OUTSIDE);
				}
				return null;

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode DEMON_HOME_STREET_DADDY = new DialogueNode("Демоний Дом", "Демоний Дом", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		
		@Override
		public String getContent() {
			return DEMON_HOME_STREET.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Daddy.isAvailable()) {
					return new Response("[daddy.Name]",
							Daddy.getAvailabilityText(),
							null);
					
				} else if(Main.game.getPlayer().hasCompanions()) {
					return new Response("[daddy.Name]",
							"[style.italicsBad(Вы не можете встретиться с [daddy.name], пока в вашей группе есть спутники!)]",
							null);
					
				} else {
					return new Response("[daddy.Name]",
							"Направиться к квартире [daddy.namePos] и постучать в дверь [daddy.her].",
							DaddyDialogue.MEETING) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_DADDY, Quest.DADDY_MEETING)) {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_MEETING));
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				}

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT = new DialogueNode("", "-", true) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
					return new Response("Комната Arthur", "Подняться в комнату Arthur.", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM){
						@Override
						public void effects() {
							if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_B_DEMON_HOME) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN));
							}
						}
					};
					
				} else if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Комната Arthur", "Arthur здесь больше не живет...", null);
				}
				
			} else if (index == 2) {
				if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
					return new Response("Комната [felicia.Name]", "Направиться в комнату [felicia.namePos].", DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM);
				}
				
			} else if (index == 0) {
				return new Response("Уйти", "Выйти из здания и вернуться в Обитель Демонов.", DEMON_HOME_STREET_ARTHUR);
			}
			
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM = new DialogueNode("Комната Arthur", "-", true) {
		@Override
		public void applyPreParsingEffects() {
            getFelicia().equipOutsideClothing();
            getFelicia().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getLabel() {
			return "Комната Arthur";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Расспросить собакодеву", "Спросить у собакодеву, знает ли она что-нибудь об аресте Arthur.", DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END);
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END = new DialogueNode("Комната Arthur", "-", true, true) {
		@Override
		public void applyPreParsingEffects() {
            getFelicia().setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_ARTHURS_ROOM_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Здесь вы сделали всё, что могли. Возвращайтесь в Обитель Демонов.", DEMON_HOME_STREET_ARTHUR){
                        @Override
                        public void effects() {
                            getFelicia().equipInsideClothing();
                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, true);
                        }
                    };
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM = new DialogueNode("", "", true) {
		public int h;
        @Override
        public void applyPreParsingEffects() {
            h = Main.game.getHourOfDay();
            if(h >= 6 && h <= 14) {
                getFelicia().setLocation(Main.game.getPlayer(), false);
                getFelicia().setIntroducedToPlayer(true);
            }
        }
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getLabel() {
			return "[felicia.NamePos] Комната";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/arthursApartment/apartment", "DEMON_HOME_ARTHURS_APARTMENT_FELICIAS_ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaRejectedPlayer)) {
	                 return new Response("Уйти", "Похоже, Фелиция не хочет с вами разговаривать. Возвращайтесь в Обитель Демонов.", DEMON_HOME_STREET_ARTHUR);
				}
				if(h < 6 || h > 14) {
					return new Response("Уйти", "Похоже, [felicia.name] здесь нет. Возвращайтесь в Обитель Демонов.", DEMON_HOME_STREET_ARTHUR);
					
				} else if(h >= 6 && h <= 14) {
	                if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feliciaToldAboutArthur)) {
	                    return new Response("Войти", "Войти в дом [felicia.namePos].", FeliciaApartment.ARTHUR_WHEREABOUTS) {
	                        @Override
	                        public void effects() {
	                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, false);
	                            Main.game.getPlayer().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA);
	                        }
	                    };
	                    
	                } else {
	                   return new Response("Войти", "Войти в дом [felicia.namePos].", FeliciaApartment.FELICIA_GREETINGS) {
	                        @Override
	                        public void effects() {
	                            getFelicia().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA, false);
	                            Main.game.getPlayer().setLocation(WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA);
	                        }
	                    }; 
	                }
	                
				}
			}
			return null;
		}
	};

	public static final DialogueNode DEMON_HOME_SEX_SHOP = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
            if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A && !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")) {
            	Main.game.getNpc(Fiammetta.class).setLocation(Main.game.getPlayer());
            	Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(50_000));
            }
            if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
            		&& Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60)) {
            	int daysToGo = 7 - (int) (((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time")) / (24 * 60 * 60)));
            	if(daysToGo<=1) {
                	UtilText.addSpecialParsingString("[style.italicsGood(Лавка Лювьенны откроется через день или около того.)]", true);
            	} else {
                	UtilText.addSpecialParsingString("[style.italicsMinorGood(Лавка роскоши Ловьенн снова откроется примерно через "+daysToGo+" дней...)]", true);
            	}
            }
            if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7B
            		&& Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (1 * 24 * 60 * 60)) {
            	UtilText.addSpecialParsingString("[style.italicsGood(Лавка Лювьенны откроется через день или около того.)]", true);
            }
		}
		@Override
		public boolean isTravelDisabled() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A) {
				if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60)) {
					return !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed");
				} else {
					return !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen");
				}
			}
			return false;
		}
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A) {
				if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60)) {
					return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_RIOT");
				}
				if(!Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")) {
					return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_RIOT_ENDED");
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7B
					&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (1 * 24 * 60 * 60))) {
				return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_FIA_CLOSED");
			}
			
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
						&& ((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60))// 7 days
							|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")
							|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen"))) {
					if(!Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")) {
						return new Response("Прощай",
								"Попрощайся с Фиамметой.",
								DEMON_HOME_SEX_SHOP_RIOT_CONTINUE) {
							@Override
							public void effects() {
								Main.game.getNpc(Fiammetta.class).returnToHome();
								Main.game.getDialogueFlags().setFlag("innoxia_doll_factory_ending_riot_witnessed", true);
								// If enough time has passed to reopen, reset timer so one more day is needed
								if(Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") >= (7 * 24 * 60 * 60)) {
									Main.game.getDialogueFlags().setSavedLong("doll_quest_choice_time", Main.game.getSecondsPassed() - (6 * 24 * 60 * 60));
								}
							}
						};
						
					} else if((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") >= (7 * 24 * 60 * 60))
							&& !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")) {
						return new Response("Продолжить",
								"Продолжить свой путь.",
								DEMON_HOME_SEX_SHOP) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag("innoxia_doll_factory_ending_reopen_scene_seen", true);
							}
						};
						
					} else {
						int daysRemaining = 7 - (int) ((Main.game.getSecondsPassed() - (Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time"))) / (24 * 60 * 60));
						
						return new Response("Роскошь Ловьенн",
								"Из-за общественных беспорядков Лавка роскоши Ловьенн сейчас [style.colourBad(закрыта)].<br/>[style.italicsMinorGood(Скорее всего, она откроется в течение "+Util.intToString(daysRemaining)+" "+(daysRemaining<=1?"день":"дней")+"  дней...)]",
								null);
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7B
						&& Main.game.getDialogueFlags().hasSavedLong("doll_quest_choice_time")
						&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (1 * 24 * 60 * 60))) { // 1 day
					return new Response("Роскошь Ловьенн",
							"Из-за необходимости разбираться с ситуацией вокруг статьи Фиамметты, Лавка роскоши Ловьенн сейчас [style.colourBad(закрыта)].<br/>[style.italicsMinorGood(Скорее всего, она откроется через день или два...)]",
							null);
					
				} else if(!Main.game.isHourBetween(11, 23)) {
					return new Response("Роскошь Ловьенн",
							"Лавка роскоши Ловьенн работает с [units.time(11)] до [units.time(23)], поэтому сейчас она [style.colourBad(закрыта)].",
							null);
					
				} else {
					return new Response("Роскошь Ловьенн",
							"Толкнуть входную дверь и войти в Лавку роскоши Ловьенн.",
							DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_enter")) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_exit"));
						}
					};
					
				}
				
			} else if(index==2) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
						&& ((Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") < (7 * 24 * 60 * 60))// 7 days
								|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")
								|| !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen"))
						&& !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_riot_witnessed")) {
					return null; // If talking to Fia, don't show 'Fia' action
				}
				
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A
						&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("doll_quest_choice_time") >= (7 * 24 * 60 * 60))
						&& !Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")) {
					return null; // If this is the 'riot end' scene, don't show the 'Fia' action
				}
				
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_2) {
					if(!Main.game.isHourBetween(1, 4)) {
						return new Response("Найти Фию",
								"Тебе нужно подождать, пока не наступит время между [units.time(1)] и [units.time(4)], чтобы вместе с Фией пробраться в «Роскошь Ловиенны»...",
								null);
						
					} else if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
						return new Response("Найти Фию",
								"Хотя ты пришел в нужное время, между [units.time(1)] и [units.time(4)], Фия не может появиться из-за непрекращающегося арканального шторма...",
								null);
						
					} else {
						return new Response("Найти Фию",
								"Найди Фию, чтобы вы могли вместе пробраться в «Роскошь Ловиенны» и поискать улики, указывающие на место заточения похищенных людей.<br/>[style.italicsCombat(Приготовься, ведь это положит начало долгой части побочного квеста, в которой могут быть трудные сражения!)]",
								DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_factory_meet_fia_start"));
					}
					
				} else if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_DOLL_FACTORY, Quest.DOLL_FACTORY_7A)) {
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_FACTORY)) {
						return new Response("Фиа",
								"Фия еще не вышла из укрытия и появится только после того, как «Роскошь Ловиенны» снова откроется..."
									+ (!Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_ending_reopen_scene_seen")
											?"<br/>[style.italics(Фия вернется после того, как ты войдешь в заново открывшуюся «Роскошь Ловиенны».)]"
											:""),
								null);
					} else {
						if(!Main.game.isHourBetween(19, 00)) {
							return new Response("Фиа",
									"В это время Фии нет рядом...<br/><i>Возвращайся в период с [units.time(19)] до [units.time(00)], чтобы найти Фию.</i>",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag("innoxia_fia_bar_seen")) {
							return new Response("Найти Фию",
									"Ты уже встречал Фию этим вечером, и сможешь найти ее снова только завтра.",
									null);
								
						} else {
							return new Response("Фиа",
									"Поищи Фию в ближайших барах.",
									DialogueManager.getDialogueFromId("innoxia_places_dominion_demon_home_fia_start"));
						}
					}
					
				} else if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_DOLL_FACTORY, Quest.DOLL_FACTORY_7B) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_FACTORY)) {
					if(!Main.game.isHourBetween(16, 22)) {
						return new Response("Ангеликс",
								"Анжеликса и ее сыновья сейчас на «работе», так что, если хочешь их увидеть, придется вернуться позже.<br/><i>Возвращайся в период с [units.time(16)] до [units.time(22)], чтобы встретить Анжеликссу и ее сыновей.</i>",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_angelixx_apartment_visited")) {
							return new Response("Ангеликс",
									"Ты уже заглядывал в квартиру Анжеликссы этим вечером, и сможешь сделать это снова только завтра.",
									null);
							
					} else {
						return new Response("Ангеликс",
								"Отправляйся в квартиру Анжеликс и навести её и её сыновей.",
								DialogueManager.getDialogueFromId("innoxia_places_dominion_angelixx_apartment_generic_visit"));
					}
				}
			}
			return null;
		}
	};
	

	public static final DialogueNode DEMON_HOME_SEX_SHOP_RIOT_CONTINUE = new DialogueNode("", "", false, true) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DEMON_HOME_SEX_SHOP_RIOT_CONTINUE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DEMON_HOME_SEX_SHOP.getResponse(responseTab, index);
		}
	};
}
