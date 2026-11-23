package com.lilithsthrone.game.dialogue.story;

import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.npc.submission.Takahashi;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3.4
 * @author Innoxia
 */
public class LyssiethReveal {

	public static final DialogueNode ENTRANCE_WITH_ELIZABETH = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "ENTRANCE_WITH_ELIZABETH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Следовать", "Позволить Элизабет вести вас к тронному залу Lyssieth.", FORWARDS_1) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_1 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_1");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вперед", "Продолжать следовать за Элизабет.", FORWARDS_2) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_2 = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_2");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Войти", "Вы готовитесь к встрече с Lyssieth и следуете за Элизабет сквозь открытые двери.",
						FORWARDS_3) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(Elizabeth.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FORWARDS_3 = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "FORWARDS_3");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Кабинет Lyssieth", "Позволить [siren.name] представить вас Lyssieth.", OFFICE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1));
						Main.game.getNpc(DarkSiren.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Защищаться", "Похоже, Lyssieth вот-вот нападет на вас! Защищайтесь!", OFFICE_REACTION);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Сопротивляться", "Не сдавайся! Сопротивляйся заклинанию Lyssieth и попытайся удержаться на своем [pc.feet].", OFFICE_REACTION_BETRAYAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_RESIST"));
					}
				};
				
			} else if(index==2) {
				return new Response("Подчиниться", "Да... Встань на колени... Просто отдайся этому...", OFFICE_REACTION_BETRAYAL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_SUBMIT"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION_BETRAYAL = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_BETRAYAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Встать", "Сделай так, как говорит Lyssieth, и заставь себя подняться на ноги.", OFFICE_REACTION_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_REACTION_END = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_REACTION_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Вперед", "Подойди к Lyssieth поближе, чтобы она смогла телепортировать тебя и [siren.name] в дом Lilaya.", OFFICE_TELEPORT);
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_TELEPORT = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_TELEPORT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Прибытие", "Вы втроем прибываете в лабораторию Lyssieth.", OFFICE_TELEPORT_ARRIVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						Main.game.getNpc(DarkSiren.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_ROSE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_TELEPORT_ARRIVE = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "OFFICE_TELEPORT_ARRIVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Arthur", "Выслушать теорию Arthur.", LAB_ARTHUR_THEORY);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ARTHUR_THEORY = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ARTHUR_THEORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Что?!", "Выразить тревогу по поводу того, что только что сказала Lyssieth.", LAB_WORLD_REVEAL);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_WORLD_REVEAL = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_WORLD_REVEAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lilaya", "Хотя [siren.name] и Arthur, кажется, спокойно воспринимают эту новость, Lilaya выглядит невероятно расстроенной, и похоже, что ей есть что сказать.", LAB_LILAYA_ANGERY);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_LILAYA_ANGERY = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_LILAYA_ANGERY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Успокоить", "Успокоить Lilaya, заверив её, что она осталась всё той же человеком, которого вы всегда знали.", LAB_LILAYA_CALMED_DOWN);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_LILAYA_CALMED_DOWN = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_LILAYA_CALMED_DOWN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked1)) {
					return new Response("Мир", "Вы уже спрашивали Lyssieth об остальном мире.", null);
				} else {
					return new Response("Мир", "Спросить Lyssieth, почему остальной мир до сих пор ничего не предпринял.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_WORLD"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked1, true);
							AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false);
						}
					};
				}
				
			} if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked2)) {
					return new Response("Предательство", "Вы уже спрашивали Lyssieth о том, почему она решила предать Lilith.", null);
				} else {
					return new Response("Предательство", "Спросить Lyssieth, почему она решила предать Lilith.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_BETRAYAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked2, true);
						}
					};
				}
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked3)) {
					return new Response("Люди", "Lilaya уже спрашивала Lyssieth о трансформациях людей.", null);
				} else {
					return new Response("Люди", "Lilaya хочет спросить о том, каким именно образом людей превращали в их иные версии.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_PEOPLE"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked3, true);
						}
					};
				}
				
			} else if(index==4) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked4)) {
					return new Response("Заклинание", "[siren.Name] уже спрашивал(а) Lyssieth о заклинании Lilith, изменяющем реальность.", null);
				} else {
					return new Response("Заклинание", "[siren.Name] хочет спросить о заклинании Lilith, меняющем реальность.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_SPELL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked4, true);
						}
					};
				}
				
			} else if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked5)) {
					return new Response("Обращение", "Arthur уже спрашивал Lyssieth о возможности обратить заклинание Lilith вспять.", null);
				} else {
					return new Response("Обращение", "Arthur хочет спросить о возможности обратить заклинание Lilith вспять.", LAB_QUESTION) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_REVERSAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethQuestionAsked5, true);
						}
					};
				}
				
			} else if(index==6) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked1)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked2)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked3)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked4)
						|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethQuestionAsked5)) {
					return new Response("Продолжить", "Прежде чем продолжить, вам нужно расспросить Lyssieth о заклинании.", null);
				} else {
					return new Response("Продолжить", "У Lyssieth больше нет времени на вопросы.", LAB_QUESTION_END);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode LAB_QUESTION = new DialogueNode("", "", true, true) {

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
			return LAB_LILAYA_CALMED_DOWN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LAB_QUESTION_END = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_QUESTION_END");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Освободить", "Объявить всем, что вы хотите победить Lilith и положить конец её тирании.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_LIBERATE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 25));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionLiberate, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Узурпировать", "Скажи, что намерен победить Lilith и занять её место правительницы всего Dominion.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_USURP"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionUsurp, true);
					}
				};
				
			} else if(index==3) {
				return new Response("Присоединиться", "Скажи, что хочешь присоединиться к Lilith.", LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_JOIN"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), -20));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionJoin, true);
					}
				};
				
			} else if(index==4) {
				return new Response("Ничего",
						"Тебе действительно плевать на спасение мира или всю эту чепуху. Куда проще просто ничего не делать и оставить судьбу мира тем, у кого энтузиазма побольше.",
						LAB_ENDING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_NOTHING"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionNothing, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Невозможно", "Спроси Lyssieth, как ты должен сражаться с древним лилин и её армией демонических кентавров.", LAB_ENDING_MINOTALLYS);
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING_MINOTALLYS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_MINOTALLYS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Йоко?", "Спроси Meraxis, кто такие youko.", LAB_ENDING_SIREN_HELP);
			}
			return null;
		}
	};

	public static final DialogueNode LAB_ENDING_SIREN_HELP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Takahashi.class).setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_SIREN_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Назад", "Вернуться вместе с Lyssieth в её кабинет, оставив Lilaya и [siren.name] позади.", LAB_ENDING_RETURN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						Main.game.getNpc(Lyssieth.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Arthur.class).returnToHome();
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LAB_ENDING_RETURN = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getRace()==Race.HUMAN) {
					return new Response("Отказаться от секса",
							"Сказать Lyssieth, что ты не хочешь заниматься сексом, и если ей необходимо дойти до оргазма, ей придётся просто заняться мастурбацией.",
							LAB_ENDING_RETURN_DECLINE_SEX) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN_DECLINE_SEX_HUMAN"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
						}
					};
				} else {
					return new Response("Отказаться от секса",
							"Сказать Lyssieth, что тебе не интересно заниматься с ней сексом, и позволить ей влить свою силу в твою Ауру.",
							LAB_ENDING_RETURN_DECLINE_SEX) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "LAB_ENDING_RETURN_DECLINE_SEX"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(250, false));
						}
					};
				}
				
			} else if(index==2) {
				return new ResponseSex("Киска",
						"Сказать Lyssieth, что ты хочешь заняться с ней сексом и хочешь доминантно использовать её киску.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE))),
						null,
						null,
						POWER_VISION,
						UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "SEX_PUSSY"));
				
			} else if(index==3) {
				return new ResponseSex("Хер",
						"Сказать Lyssieth, что ты хочешь заняться с ней сексом, и чтобы она отрастила хер и доминантно трахала тебя.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))),
						null,
						null,
						POWER_VISION,
						UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "SEX_COCK")) {
					@Override
					public void effects() {
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).growCock(PenisType.HUMAN);
					}
				};
			}
			return null;
		}
	};
	
//	private static void setPlayerAsLyssieth() {
//		PlayerCharacter player = new PlayerCharacter(
//				new NameTriplet("Lyssieth"),
//				1000,
//				null,
//				Gender.F_V_B_FEMALE,
//				Subspecies.DEMON,
//				RaceStage.GREATER,
//				WorldType.LYSSIETH_PALACE,
//				PlaceType.LYSSIETH_PALACE_OFFICE);
//		player.setSurname("Lilithmartuilani");
//		player.setDescription("One of the seven elder Lilin, you are one of the most powerful beings in existence.");
//		player.setSubspeciesOverride(Subspecies.ELDER_LILIN);
//		player.getBody().calculateRace(player);
//		player.setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
//		player.setAttribute(Attribute.MAJOR_ARCANE, 100);
//		player.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
//		Main.game.setPlayer(player);
//	}
	
	public static final DialogueNode LAB_ENDING_RETURN_DECLINE_SEX = new DialogueNode("", "", true, true) {

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
			if(index==1) {
				return new Response("Видение", "Тебе снится странное видение, в котором ты — Lyssieth...", POWER_VISION) {
					@Override
					public void effects() {
//						setPlayerAsLyssieth();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_VISION = new DialogueNode("Видение", "Тебе снится странное видение, в котором ты — Lyssieth...", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_VISION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Проснуться", "Ты приходишь в себя в кабинете Lyssieth.", POWER_EXPLANATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LYSSIETH_4));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_EXPLANATION = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Лежать на коленях", "Принять предложение Lyssieth и продолжить отдыхать, положив голову ей на колени, рассказывая о увиденном сне.", POWER_EXPLANATION_CONTINUE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION_LAP"));
					}
				};
				
			} else if(index==2) {
				return new Response("Встать", "Тебе неудобно в таком положении. Встань и расскажи ей о своем видении.", POWER_EXPLANATION_CONTINUE) { // But why
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "POWER_EXPLANATION_STAND_UP"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode POWER_EXPLANATION_CONTINUE = new DialogueNode("", "", true) {

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
			if(index==1) {
				return new Response("Выйти", "Выйти из кабинета Lyssieth, чтобы она могла принести [siren.name].", END_SIREN) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						if(Main.game.getNpc(DarkSiren.class).getAffection(Main.game.getPlayer())<0) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getPlayer(), 0));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode END_SIREN = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "END_SIREN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Карта", "Взять карту мира, которую вам дают.", END_FINAL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_3_ELIS));
						Main.game.getTextEndStringBuilder().append(
								"<div class='container-full-width' style='text-align:center;'>[style.colourExcellent(Вы открыли карту мира!)]<br/><i>Ее можно посмотреть либо через меню карты в вашем телефоне, либо отправившись на одну из выходных клеток Dominion и открыв меню «Путешествие по миру».</i></div>");
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode END_FINAL = new DialogueNode("", "", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("quests/main/lyssiethReveal", "END_FINAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false).getResponse(responseTab, index);
		}
	};
}
