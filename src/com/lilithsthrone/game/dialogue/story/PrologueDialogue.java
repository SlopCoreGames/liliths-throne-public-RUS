package com.lilithsthrone.game.dialogue.story;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public class PrologueDialogue {

	private static boolean femalePrologueNPC() {
		return CharacterCreation.femalePrologueNPC();
	}
	
	public static final DialogueNode INTRO = new DialogueNode("В музее", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 90;
		}
		
		@Override
		public String getContent() {
			if(femalePrologueNPC()) {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_FEMALE");
				
			} else {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_MALE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Согласен", "Поглощенная возбуждением, ты решаешь согласиться и пойти развлечься.", INTRO_EMPTY_ROOM) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_OFFICE);
						if(femalePrologueNPC()) {
							Main.game.getNpc(PrologueFemale.class).setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_OFFICE);
						} else {
							Main.game.getNpc(PrologueMale.class).setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_OFFICE);
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Скажи Нет", "Ты не думаешь, что это хорошая идея, чтобы ускользнуть и заниматься сексом, когда ты должен быть здесь, чтобы увидеть свою тетю Лили. Скажи нет.", INTRO_NO) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_CROWDS);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTRO_EMPTY_ROOM = new DialogueNode("В музее", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 90;
		}
		
		@Override
		public String getContent() {
			if(femalePrologueNPC()) {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_FEMALE");
				
			} else {
				return UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_MALE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(femalePrologueNPC()) {
				if (index == 1) {
					return new ResponseSex("Доминирующий секс", "Уступи своей похоти, возьми на себя инициативу и начни заниматься сексом с [prologueFemale.name]...",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(PrologueFemale.class)),
									null,
									null),
							AFTER_SEX,
							(Main.game.getPlayer().hasPenis()
								?UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_FEMALE_AS_MALE_START_DOM")
								:UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_FEMALE_AS_FEMALE_START_DOM"))
							+ UtilText.parseFromXMLFile("misc/prologue", "SEX_CLOTHING_MANAGEMENT_TIP", Main.game.getNpc(PrologueFemale.class))) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_WHITE, false), false);
							}
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("Субмиссивный секс", "Уступи своей похоти, подчинись [prologueFemale.name], и позволь ей взять на себя инициативу, когда ты занимаешься сексом с ней...",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(PrologueFemale.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX,
							(Main.game.getPlayer().hasPenis()
								?UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_FEMALE_AS_MALE_START_SUB")
								:UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_FEMALE_AS_FEMALE_START_SUB"))
							+ UtilText.parseFromXMLFile("misc/prologue", "SEX_CLOTHING_MANAGEMENT_TIP", Main.game.getNpc(PrologueFemale.class))) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_WHITE, false), false);
							}
						}
					};
				
				}
				
			} else {
				if (index == 1) {
					return new ResponseSex("Доминирующий секс", "Уступи своей похоти, возьми на себя инициативу и начни заниматься сексом с [prologueMale.name]...",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(PrologueMale.class)),
									null,
									null),
							AFTER_SEX,
							UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_MALE_START_DOM")
								+ UtilText.parseFromXMLFile("misc/prologue", "SEX_CLOTHING_MANAGEMENT_TIP", Main.game.getNpc(PrologueMale.class))) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_WHITE, false), false);
							}
							Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_WHITE, false), false);
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("Субмиссивная секс", "Уступи своей похоти, подчинись [prologueMale.name] и позволь ему взять на себя инициативу, когда ты занимаешься сексом с ним...",
							null, null, null,
							null, null, null,
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(PrologueMale.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX,
							UtilText.parseFromXMLFile("misc/prologue", "INTRO_EMPTY_ROOM_SEX_MALE_START_SUB")
								+ UtilText.parseFromXMLFile("misc/prologue", "SEX_CLOTHING_MANAGEMENT_TIP", Main.game.getNpc(PrologueMale.class))) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasPenis()) {
								Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_WHITE, false), false);
							}
							Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_penis_condom", PresetColour.CLOTHING_WHITE, false), false);
						}
					};
				
				}
			}
			
			if (index == 3) {
				return new Response("Второе мнение", "Реши, что это плохая идея, и положи конец этому.", INTRO_SECOND_THOUGHTS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_CROWDS);
					}
				};
			}
			
			return null;
		}
	};
	

	public static final DialogueNode AFTER_SEX = new DialogueNode("Закончено", "Теперь, когда ты получил удовольствие, тебе действительно следует найти свою тетю Лили...", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(PrologueFemale.class))>=Main.game.getNpc(PrologueFemale.class).getOrgasmsBeforeSatisfied()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_FEMALE_SATISFIED"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_FEMALE_NOT_SATISFIED"));
				}
				
			} else {
				if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(PrologueMale.class))>=Main.game.getNpc(PrologueMale.class).getOrgasmsBeforeSatisfied()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_MALE_SATISFIED"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX_MALE_NOT_SATISFIED"));
				}
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "AFTER_SEX"));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Поиск", "Иди и обыщи музей в поисках Артура.", INTRO_2) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM_LOST, PlaceType.MUSEUM_MIRROR);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode INTRO_SECOND_THOUGHTS = new DialogueNode("В музее", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_SECOND_THOUGHTS_FEMALE"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_SECOND_THOUGHTS_MALE"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_SECOND_THOUGHTS"));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Поиск", "Иди и обыщи музей в поисках Артура.", INTRO_2) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM_LOST, PlaceType.MUSEUM_MIRROR);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTRO_NO = new DialogueNode("В музее", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NO_FEMALE"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NO_MALE"));
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NO"));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Поиск", "Иди и обыщи музей в поисках Артура.", INTRO_2){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM_LOST, PlaceType.MUSEUM_MIRROR);
					}
				};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_2 = new DialogueNode("В музее", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_2");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Согласен", "Иди и посмотри за зеркало, чтобы узнать, кто там.", INTRO_3A);
			} else if (index == 2) {
				return new Response("Нет", "Это самая очевидная ловушка, которую ты когда-либо видел.", INTRO_3B);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_3A = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_3A");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ужас!", "Ааа!", INTRO_4);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_3B = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_3B");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ужас!", "Ааа!", INTRO_4);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_4 = new DialogueNode("Ужас!", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_4");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Паника", "Сейчас самое время впасть в панику.", INTRO_5);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_5 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_5");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Проснись", "Ты медленно начинаешь приходить в сознание.", INTRO_NEW_WORLD_1){
					@Override
					public void effects() {
						Main.game.setWeatherInSeconds(Weather.MAGIC_STORM, 5*60*60);

						Main.game.setRenderMap(true);
						
						MainController.updateUI();
						
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);
						
						Main.game.getPlayer().setAgeAppearanceDifference(-Game.TIME_SKIP_YEARS);
						
						Main.game.applyStartingDateChange();

						Main.game.getPlayer().addSpecialPerk(Perk.SPECIAL_PLAYER);

						Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
						Main.game.getPlayer().setLustNoText(Main.game.getPlayer().getRestingLust());

						if(femalePrologueNPC()) {
							Main.game.getNpc(PrologueFemale.class).endPregnancy(false); // This is to clear the pregnancy stats from the player's phone menu
						}	
					}
				};
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_1 = new DialogueNode("Новый мир", "", true, false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Борьба", "Попытайтесь вырваться из их хватки.", INTRO_NEW_WORLD_1_STRUGGLE);
				
			} else if (index == 2) {
				return new Response("Фурри?! Да!",
						"Фурри реальны?! Вы <b>любите</b> фурри!<br/>"
						+ "<b>Это установит все ваши начальные настройки фурри на </b><b style='color:"+ RaceStage.GREATER.getColour().toWebHexString()+ ";'>"+FurryPreference.MAXIMUM.getName()+"</b><b>."
						+ " Это можно изменить в любое время в меню настроек.</b>", 
						INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES){
					@Override
					public void effects(){
						for(AbstractSubspecies r : Subspecies.getAllSubspecies()) {
							if(!r.isNonBiped()) {
								Main.getProperties().setFeminineFurryPreference(r, FurryPreference.MAXIMUM);
								Main.getProperties().setMasculineFurryPreference(r, FurryPreference.MAXIMUM);
							}
						}
						Main.saveProperties();
					}
				};
				
			} else if (index == 3) {
				return new Response("Фурри?! Нет!",
						"Почему фурри реальны?! Вы <b>ненавидите</b> фурри! Выплесните свою ярость и попытайтесь освободиться.<br/>"
						+ "<b>Это установит все ваши начальные настройки фурри на </b><b style='color:"+ RaceStage.HUMAN.getColour().toWebHexString()+ ";'>"+FurryPreference.HUMAN.getName()+"</b><b>."
						+ "Это можно изменить в любое время в меню настроек.</b>", 
						INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES){
					@Override
					public void effects(){
						for(AbstractSubspecies r : Subspecies.getAllSubspecies()) {
							if(!r.isNonBiped()) {
								Main.getProperties().setFeminineFurryPreference(r, FurryPreference.HUMAN);
								Main.getProperties().setMasculineFurryPreference(r, FurryPreference.HUMAN);
							}
						}
						Main.saveProperties();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNode INTRO_NEW_WORLD_1_STRUGGLE = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE_END"));
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Кто-то пришел, чтобы спасти тебя!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_BY_THE_POWER_OF_HATING_FURRIES"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE_END"));
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Кто-то пришел, чтобы спасти тебя!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_BY_THE_POWER_OF_LOVING_FURRIES"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_1_STRUGGLE_END"));
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Кто-то пришел, чтобы спасти тебя!", INTRO_NEW_WORLD_2){
					@Override
					public void effects() {
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTRO_NEW_WORLD_2 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Объяснить", "Быстро объясни Лили, что произошло в музее.", INTRO_NEW_WORLD_2_A);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_2_A = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_2_A");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Следовать", "Следуй за Лилай, пока она ведет тебя обратно к себе домой.", INTRO_NEW_WORLD_3){
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_3 = new DialogueNode("Дом Лилай", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60*5;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_3");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("В лабораторию", "Следуй за Лилай в ее лабораторию.", INTRO_NEW_WORLD_4){
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_4 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_4");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ослеплен", "Розовый вспышка была настолько яркой, что ты временно ослеп!", INTRO_NEW_WORLD_5){
					@Override
					public void effects() {
						// Remove clothing:
						List<AbstractClothing> tempList = new ArrayList<>();
						tempList.addAll(Main.game.getPlayer().getClothingCurrentlyEquipped());

						for (AbstractClothing c : tempList) {
							Main.game.getPlayer().unequipClothingOntoFloor(c, true, Main.game.getPlayer());
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_5 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_5");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Я демон?!", "Лилай продолжает использовать слово 'демон', чтобы описать твою 'Ауру'. Ты начинаешь беспокоиться, что что-то должно было измениться глубоко внутри тебя...", INTRO_NEW_WORLD_6){
					@Override
					public void effects() {
						// Equip clothing:
						List<AbstractClothing> tempList = new ArrayList<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory().keySet());

						for(AbstractClothing c : tempList) {
							if(!c.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_scientist_safety_goggles"))) {
								Main.game.getPlayer().equipClothingFromGround(c, true, Main.game.getPlayer());
							}
						}

						DamageType damageType = DamageType.FIRE;
						switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
							case AIR:
								damageType = DamageType.POISON;
								break;
							case EARTH:
								damageType = DamageType.PHYSICAL;
								break;
							case ARCANE:
							case FIRE:
								damageType = DamageType.FIRE;
								break;
							case WATER:
								damageType = DamageType.ICE;
								break;
						}
						if(Main.game.getPlayer().getMainWeapon(0)==null) {
							Main.game.getPlayer().equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType));
						} else {
							Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType), false);
						}
						
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_6 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			String demonstoneImages = "изображения пламени";
			String demonstoneEnergy = "flame";
			switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
				case AIR:
					demonstoneImages = "изображения газообразных зеленых паров";
					demonstoneEnergy = "poison";
					break;
				case EARTH:
					demonstoneImages = "линии энергии";
					demonstoneEnergy = "energy";
					break;
				case ARCANE:
				case FIRE:
					demonstoneImages = "изображения пламени";
					demonstoneEnergy = "flame";
					break;
				case WATER:
					demonstoneImages = "изображения снежинок и сосульки";
					demonstoneEnergy = "ice";
					break;
			}
			UtilText.addSpecialParsingString(demonstoneImages, true);
			UtilText.addSpecialParsingString(demonstoneEnergy, false);
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_6");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Магия!", "Благодаря вашей мощной ауре, вы можете использовать Аркана!", INTRO_NEW_WORLD_7){
					@Override
					public String getTitle() {
						if (!Main.game.getPlayer().isFeminine())
							return "Вы ведьмак!";
						else
							return "Вы ведьма!";
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_7 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			String spellName = Spell.FIREBALL.getName();
			switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
				case AIR:
					spellName = Spell.POISON_VAPOURS.getName();
					break;
				case EARTH:
					spellName = Spell.SLAM.getName();
					break;
				case ARCANE:
				case FIRE:
					spellName = Spell.FIREBALL.getName();
					break;
				case WATER:
					spellName = Spell.ICE_SHARD.getName();
					break;
			}
			UtilText.addSpecialParsingString(spellName, true);
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_7");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ваша комната", "Вы следуете за Розой, которая ведет вас в вашу новую комнату.", INTRO_NEW_WORLD_8){
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_8 = new DialogueNode("Ваша комната", "Ты следуешь за Роуз, когда она ведет тебя в твою новую комнату.", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_8");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Стук", "Роуз сказала, что вернется через полчаса, так что это, должно быть, она стучит в твою дверь.", INTRO_NEW_WORLD_9){
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(5000);
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));
						
						Spell startingSpell = Spell.FIREBALL;
						switch(CharacterCreation.getStartingTomeSpellSchool()) {
							case AIR:
								startingSpell = Spell.POISON_VAPOURS;
								break;
							case EARTH:
								startingSpell = Spell.SLAM;
								break;
							case FIRE:
							case ARCANE:
								startingSpell = Spell.FIREBALL;
								break;
							case WATER:
								startingSpell = Spell.ICE_SHARD;
								break;
						}
						AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(startingSpell));
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
						
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>[style.boldОтличная("+spellBook.getName()+")] добавлена в хранилище твоей комнаты!</p>");
						
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTRO_NEW_WORLD_9 = new DialogueNode("Стук", "Роуз сказала, что вернется через полчаса, так что это, должно быть, она стучит в твою дверь.", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60*30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_NEW_WORLD_9");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Свобода!", "Решай, что ты хочешь сделать дальше.", RoomPlayer.ROOM){
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.saveGame("Автосохранение_"+Main.game.getPlayer().getName(false), true, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

}
