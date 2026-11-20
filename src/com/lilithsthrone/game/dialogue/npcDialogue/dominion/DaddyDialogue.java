package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.dominion.SMDaddyDinnerOral;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.3.10
 * @version 0.3.3.10
 * @author Innoxia
 */
public class DaddyDialogue {

	private static boolean isLilayaPresent() {
		return Main.game.getCharactersPresent().contains(Main.game.getNpc(Lilaya.class));
	}
	
	private static String getDialoguePrefix() {
		if(isLilayaPresent()) {
			return "LILAYA_";
		}
		return "";
	}
	
	private static void completeQuest() {
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.SIDE_UTIL_COMPLETE));
	}
	
	private static void acceptAsDaddy() {
		Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 15));
		Main.game.getPlayer().setPetName(Main.game.getNpc(Daddy.class), "daddy");
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddySendingReward, true);
		Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_ACCEPTED));
	}
	
	// Util place dialogues:
	
	public static final DialogueNode PLACE_ENTRANCE_HALL = new DialogueNode("Прихожая", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_LOUNGE = new DialogueNode("Гостиная", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_KITCHEN = new DialogueNode("Кухня", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PLACE_BEDROOM = new DialogueNode("Спальня", "", false) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BLANK_DEFAULT_DIALOGUE = new DialogueNode("", "", false) {
		@Override
		public String getLabel() {
			return Main.game.getDefaultDialogue(false).getLabel();
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
	
	public static final DialogueNode CONVINCING_LILAYA = new DialogueNode("Спальня", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", "CONVINCING_LILAYA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать", "Дождись, пока Lilaya переоденется в платье, а затем отправляйся в [daddy.namePos] в Демоническом Доме.", MEETING) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						((Lilaya) Main.game.getNpc(Lilaya.class)).applyDinnerDateChange();
						
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_ENTRANCE);
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						
						Main.game.getNpc(Lilaya.class).setPetName(Main.game.getNpc(Daddy.class), "daddy");
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_LILAYA_MEETING));

						if(Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getPlayer().isCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class))) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
						}
					}
				};
			}
			return null;
		}
	};
	
	// Main dialogues:
	
	public static final DialogueNode FIRST_ENCOUNTER = new DialogueNode("Незваный гость", "", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Поправить его", "Скажи [daddy.name], что ты на самом деле родственник Lilaya и что вы живете здесь вместе.", BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getNpc(Daddy.class).setPlayerKnowsName(true);
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER_ANSWER"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_DADDY));
						
						((Daddy)Main.game.getNpc(Daddy.class)).sendToNewHome();
						
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
					}
				};
				
			} else if(index==2) {
				return new Response("Протолкнуться мимо", "Отказаться отвечать на вопрос этого наглого инкуба и просто пройти мимо него прямо в дом Lilaya.", BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getNpc(Daddy.class).setPlayerKnowsName(true);
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_ENCOUNTER_PUSH_PAST"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_DADDY));

						((Daddy)Main.game.getNpc(Daddy.class)).sendToNewHome();
						
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, true);
					}
				};
			}
			
			return null;
		}
	};
	
	
	public static final DialogueNode MEETING = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "Встреча с [daddy.name]";
		}

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED) {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_RETURN_AFTER_REFUSED");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED_2) { //TODO if refused like this, make it so the only option is agree/refuse, then proceed with regular options.
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_RETURN_AFTER_REFUSED_AT_DINNER");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rudeToDaddy)) {
					return UtilText.parseFromXMLFile("characters/dominion/daddy", "INITIAL_MEETING_RUDE");
				} else {
					return UtilText.parseFromXMLFile("characters/dominion/daddy", "INITIAL_MEETING");
				}
				
			} else if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING");
				
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING
					|| Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED) { // First dinner:
				if(index==1) {
					return new Response("Согласиться",
							"Возможно, стоит разузнать, почему [daddy.name] так сильно хочет встретиться с Lilaya, прежде чем решать, что сказать [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_AGREE"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 5));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==2) {
					return new Response("Согласиться (флиртовать)",
							"Свидание с [daddy.name] звучит как идеальный способ провести вечер!",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_AGREE_FLIRT"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 10));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==3) {
					boolean returning = Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED;
					return new Response(
							"Отказаться",
							returning
								? "Скажи [daddy.name], что ты передумала и всё-таки не хочешь идти с [daddy.herHim] на ужин.<br/>[style.italicsMinorBad(Ты всё ещё можешь передумать и вернуться завтра или в любое другое время.)]"
								: "Отказаться идти на ужин с [daddy.name] и сказать ему, чтобы он больше никогда не беспокоил Lilaya.<br/>[style.italicsMinorBad(Это завершит квест, '"+QuestLine.SIDE_DADDY.getName()+"', но ты всё ещё можешь вернуться и начать его заново в любое время.)]",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							if(!returning) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_REFUSE"));
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_REFUSED));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "MEETING_REFUSE_AFTER_RETURN"));	
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED_2) {
				if(index==1) {
					return new Response("Принять",
							"Скажи [daddy.name], что ты готова называть [daddy.herHim] [daddy.mommy] и убедить Lilaya прийти и встретиться с [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "ACCEPT_AFTER_RETURN"));

							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);

							acceptAsDaddy();
						}
					};
					
				} else if(index==2) {
					return new Response("Принять (флиртовать)",
							"Кокетливо скажи [daddy.name], что ты готова называть [daddy.herHim] [daddy.mommy] и убедить Lilaya прийти и встретиться с [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "ACCEPT_AFTER_RETURN_FLIRT"));

							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							acceptAsDaddy();
						}
					};
					
				} else if(index==3) {
					return new Response("Отказаться",
								"Скажи [daddy.name], что не собираешься убеждать Lilaya встретиться с [daddy.herHim], и уж точно не собираешься называть [daddy.herHim] '[daddy.daddy]'.<br/><i>Это завершит квест, '"+QuestLine.SIDE_DADDY.getName()+"', но ты всё ещё можешь вернуться и начать его заново в любое время.</i>",
								BLANK_DEFAULT_DIALOGUE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "REFUSE_AFTER_RETURN"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.rudeToDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
					};
				}
				
			} else if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
				if(index==1) {
					return new Response("Помочь Lilaya",
							"Помочь Lilaya, напомнив ей, что она была не против идеи видеть в [daddy.name] своего отца.",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING_TO_DINNER"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 20));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getNpc(Lilaya.class), 25));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getNpc(Daddy.class), 25));

							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
					
				} else if(index==2) {
					return new Response("Помочь Lilaya (флирт)",
							"Помочь Lilaya, напомнив ей, что она была не против идеи видеть в [daddy.name] своего отца, а затем пойти чуть дальше и начать флиртовать с ними обоими, чтобы задать тон вечеру.",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_INITIAL_MEETING_TO_DINNER_FLIRT"));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getPlayer(), 30));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAffection(Main.game.getNpc(Lilaya.class), 25));
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getNpc(Daddy.class), 25));
							
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Ужин",
							isLilayaPresent()
								?"Сказать [daddy.name], что вы с Lilaya были бы рады снова сходить на ужин с [daddy.herHim]."
								:"Сказать [daddy.name], что ты бы с удовольствием снова сходил на ужин с [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_OUT_FOR_DINNER"));
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
							
							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
							if(isLilayaPresent() && Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getNpc(Lilaya.class).setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
					
				} else if(index==2) {
					return new Response("Ужин (флирт)",
							isLilayaPresent()
								?"Кокетливо сказать [daddy.name], что вы с Lilaya были бы рады снова сходить на ужин с [daddy.herHim]."
								:"Кокетливо сказать [daddy.name], что ты бы с удовольствием снова сходил на ужин с [daddy.herHim].",
							DINNER) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_OUT_FOR_DINNER_FLIRTING"));
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());

							if(Main.game.getPlayer().isVisiblyPregnant()) {
								Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
							if(isLilayaPresent() && Main.game.getNpc(Lilaya.class).isVisiblyPregnant()) {
								Main.game.getNpc(Lilaya.class).setCharacterReactedToPregnancy(Main.game.getNpc(Daddy.class), true);
							}
						}
					};
					
				} else if(index==3) {
					return new Response("Внутри",
							isLilayaPresent()
								?"Спросить [daddy.name], не могли бы вы втроем зайти внутрь и провести время вместе в квартире [daddy.her]."
								:"Спросить [daddy.name], не могли бы вы вдвоем зайти внутрь и провести время вместе в квартире [daddy.her].",
							APARTMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_STAY_IN_APARTMENT"));
							
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
						}
					};
					
				} else if(index==4) {
					return new Response("Внутри (флирт)",
							isLilayaPresent()
								?"Кокетливо спросить [daddy.name], не могли бы вы втроем зайти внутрь и провести время вместе в квартире [daddy.her]."
								:"Кокетливо спросить [daddy.name], не могли бы вы вдвоем зайти внутрь и провести время вместе в квартире [daddy.her].",
							APARTMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"MEETING_STAY_IN_APARTMENT_FLIRTING"));
							
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							Main.game.getDialogueFlags().setSavedLong(Daddy.DADDY_RESET_TIMER_ID, Main.game.getSecondsPassed());
						}
					};
					
				}
			}
			
			return null;
		}
	};
	
	private static void applyCompanionDrinks(boolean includePlayer) {
		if(includePlayer) {
			Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.1f));
		}
		Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Daddy.class).incrementAlcoholLevel(0.1f));
		if(isLilayaPresent()) {
			Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAlcoholLevel(0.1f));
		}
	}
	
	public static final DialogueNode DINNER = new DialogueNode("Искушение", "", true) {

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
			DialogueNode nextNode = DINNER_MID;
			boolean firstDinner = Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_MEETING || Main.game.getPlayer().getQuest(QuestLine.SIDE_DADDY) == Quest.DADDY_REFUSED;
			if(firstDinner) {
				nextNode = FIRST_DINNER_TRANSFORM;
			}
			if(index==1) {
				return new Response("Вода",
						"Отклонить предложение [daddy.namePos] выпить вина и остановиться на воде, чтобы не опьянеть.",
						nextNode) {
					@Override
					public void effects() {
						if(firstDinner) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_WATER"));
							applyCompanionDrinks(false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_CORE"));
							
						} else {
							if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_WATER_INITIAL"));
								completeQuest();
								applyCompanionDrinks(false);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_CORE_INITIAL"));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_WATER"));
								applyCompanionDrinks(false);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_CORE"));
							}
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Вино",
						"Принять предложение [daddy.namePos] выпить бутылку вина этим вечером.",
						nextNode) {
					@Override
					public void effects() {
						if(firstDinner) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_WINE"));
							applyCompanionDrinks(true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_CORE"));
							
						} else {
							if(isLilayaPresent() && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DADDY)) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_WINE_INITIAL"));
								completeQuest();
								applyCompanionDrinks(true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "LILAYA_DINNER_CORE_INITIAL"));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_WINE"));
								applyCompanionDrinks(true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_CORE"));
							}
						}
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode FIRST_DINNER_TRANSFORM = new DialogueNode("Искушение", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Инкуб",
						"Скажи [daddy.name], что [daddy.sheIs] в порядке в своем нынешнем [daddy.sheIsFull] состоянии.<br/>[style.italicsMasculine(Это заставит [daddy.name] <b>навсегда</b> остаться в облике "+GenderNames.Y_PENIS_N_VAGINA_N_BREASTS.getMasculine()+" инкуба.)]",
						FIRST_DINNER) {
					
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_NO_TF"));
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
				};
				
			} else if(index==2) {
				return new Response("Суккуб",
						"Убеди [daddy.name], что [daddy.she] было бы гораздо проще соблазнить Lyssieth, если бы [daddy.she] превратился в суккуба.<br/>[style.italicsFeminine(Это заставит [daddy.name] <b>навсегда</b> превратиться в "+GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS.getFeminine()+" суккуба.)]",
						FIRST_DINNER) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF"));
						Main.game.getNpc(Daddy.class).returnToHome();
						Main.game.getNpc(Daddy.class).setGenderIdentity(Gender.F_P_B_SHEMALE);
						Main.game.getNpc(Daddy.class).setStartingBody(false);
						Main.game.getNpc(Daddy.class).equipClothing();
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF_END"));
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
				};
				
			} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				return new Response("Суккуб (флирт)",
						"Убеди [daddy.name], что [daddy.she] было бы гораздо проще соблазнить Lyssieth, если бы [daddy.she] превратился в суккуба. После того как он превратится, начни с ней флиртовать.<br/>[style.italicsFeminine(Это заставит [daddy.name] <b>навсегда</b> превратиться в "+GenderNames.Y_PENIS_N_VAGINA_Y_BREASTS.getFeminine()+" суккуба.)]",
						FIRST_DINNER) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF"));
						Main.game.getNpc(Daddy.class).returnToHome();
						Main.game.getNpc(Daddy.class).setGenderIdentity(Gender.F_P_B_SHEMALE);
						Main.game.getNpc(Daddy.class).setStartingBody(false);
						Main.game.getNpc(Daddy.class).equipClothing();
						Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "DINNER_SUCCUBUS_TF_END"));
					}
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode FIRST_DINNER = new DialogueNode("Искушение", "", true, true) {

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
				return new Response("Принять",
						"Скажи [daddy.name], что ты готова называть [daddy.herHim] [daddy.mommy] и убедить Lilaya прийти и встретиться с [daddy.herHim].",
						DINNER_MID) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_ACCEPT"));

						acceptAsDaddy();
					}
				};
				
			} else if(index==2) {
				return new Response("Отказаться",
							"Скажи [daddy.name], что не собираешься убеждать Lilaya встретиться с [daddy.herHim], и уж точно не собираешься называть [daddy.herHim] '[daddy.daddy]'.<br/><i>Это завершит квест, '"+QuestLine.SIDE_DADDY.getName()+"', но ты всё ещё можешь вернуться и начать его заново в любое время.</i>",
							BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", "FIRST_DINNER_REFUSE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_DADDY, Quest.DADDY_REFUSED_2));
					}
				};
			}
			
			return null;
		}
	};
	

	public static final DialogueNode DINNER_MID = new DialogueNode("Искушение", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 45*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Десерт",
						isLilayaPresent()
							?"Скажи [daddy.name], что ты и Lilaya хотели бы заказать десерт."
							:"Скажи [daddy.name], что ты хотел бы заказать десерт.",
						DINNER_END) {
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_DESSERT"));
					}
				};
				
			} else if(index==2) {
				return new Response("Никакого десерта",
						"Сказать [daddy.name], что ты уже сыт и не сможешь съесть десерт.",
						DINNER_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_NO_DESSERT"));
					}
				};
				
			} else if(index==3) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
					return new Response("'Десерт'",
							isLilayaPresent()
								?"Поскольку тебе не удалось настроить [daddy.name] или Lilaya на нужный лад флиртом этим вечером, любая попытка получить особый «десерт» от [daddy.name] почти наверняка закончится позорным фиаско."
								:"Поскольку тебе не удалось настроить [daddy.herHim] на нужный лад флиртом с [daddy.him] этим вечером, любая попытка получить особый «десерт» от [daddy.name] почти наверняка закончится позорным фиаско.",
							null);
				
				} else if(Main.game.getPlayer().isTaur()) {
					return new Response("'Десерт'",
							"Из-за твоих массивных бедер ты никак не сможешь пролезть под стол, чтобы сделать [daddy.name] минет...",
							null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("'Десерт'",
							"Ты не сможешь сделать [daddy.name] минет, пока твой рот чем-то занят!",
							null);
					
				} else {
					return new ResponseSex("'Десерт'",
							isLilayaPresent()
								?"У тебя на уме десерт другого рода. Заставь Lilaya сползти под стол вместе с тобой, чтобы она принялась сосать [daddy.namePos] член вместе с тобой и заслужить награду."
								:"У тебя на уме десерт другого рода. Сползи под стол и начни сосать [daddy.namePos] член, чтобы заслужить награду.",
							true,
							true,
							new SMDaddyDinnerOral(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Daddy.class), SexSlotSitting.SITTING)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL),
											isLilayaPresent()
												?new Value<>(Main.game.getNpc(Lilaya.class), SexSlotSitting.PERFORMING_ORAL_TWO)
												:null)),
							null,
							null,
							AFTER_UNDER_TABLE_SEX,
							UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_DADDYS_DESSERT")) {
								@Override
								public int getSecondsPassed() {
									return 2*60;
								}
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getNpc(Daddy.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true),
											isLilayaPresent()
												?new InitialSexActionInformation(Main.game.getNpc(Daddy.class), Main.game.getNpc(Lilaya.class), PenisMouth.BLOWJOB_START_ADDITIONAL, true, true)
												:null);
								}
							};
					
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_UNDER_TABLE_SEX = new DialogueNode("Подходит официантка", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_UNDER_TABLE_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DINNER_END.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DINNER_END = new DialogueNode("Искушение", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().applyFoodConsumed();
			Main.game.getPlayer().applyDrinkConsumed();
			Main.game.getNpc(Daddy.class).applyFoodConsumed();
			Main.game.getNpc(Daddy.class).applyDrinkConsumed();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				if(index==1) {
					return new Response("Отказаться",
							isLilayaPresent()
								?"Сказать [daddy.name], что тебе и Lilaya пора домой."
								:"Сказать [daddy.name], что тебе пора домой.",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public int getSecondsPassed() {
							if(isLilayaPresent()) {
								return 30*60;
							}
							return 2*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_REFUSE"));
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getNpc(Daddy.class).returnToHome();
						}
					};
					
				} else if(index==2) {
					return new Response("Принять",
							isLilayaPresent()
								?"Сказать [daddy.name], что вы с Lilaya с удовольствием отправились бы к [daddy.her], чтобы продолжить веселье..."
								:"Сказать [daddy.name], что ты с удовольствием отправился бы к [daddy.her], чтобы продолжить веселье...",
							AFTER_DINNER) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_ACCEPT"));
							if(isLilayaPresent()) {
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							}
							Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
							Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_LOUNGE);
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Попрощаться",
							isLilayaPresent()
								?"Поблагодарить [daddy.name] за еду и сказать [daddy.herHim], что увидишь [daddy.herHim] в другой раз."
								:"Поблагодарить [daddy.name] за еду и сказать [daddy.herHim], что вы с Lilaya увидитесь с [daddy.herHim] в другой раз.",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public int getSecondsPassed() {
							if(isLilayaPresent()) {
								return 30*60;
							}
							return 2*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"DINNER_END_HOME"));
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
							Main.game.getNpc(Daddy.class).returnToHome();
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_DINNER = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getLabel() {
			return "Квартира [daddy.NamePos]";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Подчиниться",
						isLilayaPresent()
							?"Сделать как велено и присоединиться к Lilaya на кровати [daddy.namePos], прежде чем предстать перед [daddy.herHim]."
							:"Сделай так, как говорит [daddy.name], и забирайся в [daddy.her] постель, прежде чем предстать перед [daddy.herHim].",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getNpc(Daddy.class)),
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										isLilayaPresent()
											?Main.game.getNpc(Lilaya.class)
											:null),
								null,
								null,
								ResponseTag.PREFER_DOGGY) {
							@Override
							public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Daddy.class));
							}
							@Override
							public Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
								Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> map = new HashMap<>();
								map.put(true, new HashMap<>());
								if(isLilayaPresent()) {
									map.get(true).put(Main.game.getNpc(Lilaya.class),
											Util.newHashMapOfValues(
													new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
													new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
													new Value<>(CoverableArea.STOMACH, null)));
								}
								map.get(true).put(Main.game.getPlayer(),
										Util.newHashMapOfValues(
												new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
												new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
												new Value<>(CoverableArea.STOMACH, null)));
								return map;
							}
						},
						null,
						null,
						AFTER_APARTMENT_SEX,
						UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_DINNER_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								isLilayaPresent()
									?new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getPlayer(), TongueMouth.KISS_START, true, true)
									:null);
					}
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						if(isLilayaPresent()) {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						}
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Доминировать",
						isLilayaPresent()
							?"Скажи [daddy.name], что всё будет совсем не так, прежде чем толкнуть [daddy.herHim] в спальню [daddy.her] и присоединиться к Lilaya, чтобы вместе оседлать [daddy.herHim]."
							:"Скажи [daddy.name], что всё будет совсем не так, прежде чем толкнуть [daddy.herHim] в спальню [daddy.her] и приготовиться к тому, чтобы оседлать [daddy.herHim].",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										isLilayaPresent()
											?Main.game.getNpc(Lilaya.class)
											:null),
								Util.newArrayListOfValues(
										Main.game.getNpc(Daddy.class)),
								null,
								null,
								ResponseTag.PREFER_COW_GIRL) {
							@Override
							public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return Main.sex.getAllParticipants().size()==2;
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return character.isPlayer() && target.equals(Main.game.getNpc(Lilaya.class));
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.equals(Main.game.getNpc(Daddy.class));
							}
							@Override
							public Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> exposeAtStartOfSexMapExtendedInformation() {
								Map<Boolean, Map<GameCharacter, Map<CoverableArea, List<InventorySlot>>>> map = new HashMap<>();
								map.put(true, new HashMap<>());
								if(isLilayaPresent()) {
									map.get(true).put(Main.game.getNpc(Lilaya.class),
											Util.newHashMapOfValues(
													new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
													new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
													new Value<>(CoverableArea.STOMACH, null),
													new Value<>(CoverableArea.VAGINA, null)));
								}
								map.get(true).put(Main.game.getPlayer(),
										Util.newHashMapOfValues(
												new Value<>(CoverableArea.VAGINA, Util.newArrayListOfValues(InventorySlot.GROIN)),
												new Value<>(CoverableArea.FEET, Util.newArrayListOfValues(InventorySlot.SOCK)),
												new Value<>(CoverableArea.STOMACH, null)));
								return map;
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(!Main.sex.isDom(character)) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
						},
						null,
						null,
						AFTER_APARTMENT_SEX,
						UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_DINNER_DOMINATE")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								isLilayaPresent()
									?new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(Daddy.class), TongueVagina.RECEIVING_CUNNILINGUS_START, true, true)
									:null);
					}
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						if(isLilayaPresent()) {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						}
						Main.game.getPlayer().setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						Main.game.getNpc(Daddy.class).setLocation(WorldType.DADDYS_APARTMENT, PlaceType.DADDY_APARTMENT_BEDROOM);
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_APARTMENT_SEX = new DialogueNode("Завершено", "Вечер, наполненный сексом и Похотью, подходит к концу...", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_APARTMENT_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Попрощаться",
						isLilayaPresent()
							?"Поблагодари [daddy.name] за всё веселье, которое вы провели этим вечером, и скажи [daddy.herHim], что вы с Lilaya увидитесь с [daddy.herHim] в другой раз."
							:"Поблагодари [daddy.name] за всё веселье, которое вы провели этим вечером, и скажи [daddy.herHim], что увидишься с [daddy.herHim] в другой раз.",
						BLANK_DEFAULT_DIALOGUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"AFTER_APARTMENT_SEX_HOME"));
						if(isLilayaPresent()) {
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
						} else {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
						}
						Main.game.getNpc(Daddy.class).returnToHome();
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode APARTMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			return "Квартира [daddy.NamePos]";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.flirtingWithDaddy)) {
				if(index==1 || index==2) {
					return AFTER_DINNER.getResponse(responseTab, index);
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.talkedWithDaddy)) {
						return new Response("Поговорить",
								"Вы уже провели некоторое время, беседуя с [daddy.name].",
								null);
					}
					return new Response("Поговорить",
							"Просто поболтай с [daddy.name] о работе [daddy.her] и её стараниях, чтобы встретить Lyssieth.",
							APARTMENT) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_TALK"));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, true);
						}
					};
					
				} else if(index==2) {
					return new Response("Соблазнить",
							isLilayaPresent()
								?"Соблазни [daddy.name] и Lilaya, начни страстно целовать их на диване, а затем переходи к сексу..."
								:"Соблазнить [daddy.name], начать целоваться с [daddy.herHim] на диване, а затем перейти к сексу с [daddy.herHim]...",
							APARTMENT) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_SEDUCE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.flirtingWithDaddy, true);
						}
					};
					
				} else if(index==3) {
					return new Response("Уйти",
							"Сказать [daddy.name], что было приятно снова увидеть [daddy.herHim], после чего встать и уйти.",
							BLANK_DEFAULT_DIALOGUE) {
						@Override
						public int getSecondsPassed() {
							if(isLilayaPresent()) {
								return 30*60;
							}
							return 2*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/daddy", getDialoguePrefix()+"APARTMENT_LEAVE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.talkedWithDaddy, false);
							if(isLilayaPresent()) {
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								((Lilaya)Main.game.getNpc(Lilaya.class)).equipClothing();
							} else {
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY);
							}
						}
					};
				}
			}
			
			return null;
		}
	};
	
}
