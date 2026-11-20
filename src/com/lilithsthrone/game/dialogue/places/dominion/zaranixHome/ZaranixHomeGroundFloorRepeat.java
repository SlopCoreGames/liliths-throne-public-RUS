package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMPetMounting;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMPetOral;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.4.4.2
 * @author Innoxia
 */
public class ZaranixHomeGroundFloorRepeat {
	
	private static NPC pet;
	private static NPC owner;
	
	public static void resetHouseAfterLeaving() {
		((Zaranix)Main.game.getNpc(Zaranix.class)).setStartingBody(false);
		((Amber)Main.game.getNpc(Amber.class)).setStartingBody(false);
		((ZaranixMaidKatherine)Main.game.getNpc(ZaranixMaidKatherine.class)).setStartingBody(false);
		((ZaranixMaidKelly)Main.game.getNpc(ZaranixMaidKelly.class)).setStartingBody(false);
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Дом Зараникса", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "OUTSIDE"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.amberRepeatEncountered)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "OUTSIDE_RETURN");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Постучать в дверь", "Эмбер точно не станет открывать дверь в столь нелюдительный час. Тебе придется вернуться днем...", null);
				}
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.amberRepeatEncountered)
						&& (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getNpc(Amber.class).getFoughtPlayerCount()!=0)) {
					return new Response("Постучать в дверь", "Постучать в дверь и ждать, пока кто-нибудь ответит.", OUTSIDE_KNOCK_ON_DOOR_KNOWS_AMBER) {
						@Override
						public void effects() {
							resetHouseAfterLeaving(); // Reset here as well in case the player teleported out of the house.
							Main.game.getNpc(Amber.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
							Main.game.getNpc(Zaranix.class).returnToHome();
						}
					};
					
				} else {
					return new Response("Постучать в дверь", "Постучать в дверь и ждать, пока кто-нибудь ответит.", OUTSIDE_KNOCK_ON_DOOR) {
						@Override
						public void effects() {
							resetHouseAfterLeaving(); // Reset here as well in case the player teleported out of the house.
							Main.game.getNpc(Amber.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
							Main.game.getNpc(Zaranix.class).returnToHome();
						}
					};
				}

			} else if (index == 0) {
				return new Response("Уйти", "Развернуться и уйти.", Main.game.getDefaultDialogue(false));

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_KNOWS_AMBER = new DialogueNode("Дом Зараникса", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_KNOWS_AMBER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return OUTSIDE_KNOCK_ON_DOOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR = new DialogueNode("Дом Зараникса", "", true) {

		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.amberRepeatEncountered)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_REPEAT");
			}
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Войти", "Пройти мимо Эмбер и войти в дом Зараникса.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY"));
					}
				};

			} else if (index == 2) {
				return new Response("Извиниться", "Сделать, как говорит Эмбер, и опуститься перед ней на колени, чтобы извиниться.", OUTSIDE_APOLOGY,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 2.5f));
					}
				};

			} else if (index == 3) {
				return new Response("Отступить", "Придумать оправдание и отступить от двери, прежде чем попрощаться.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getTextStartStringBuilder().append(
								"<p>[pc.speech(Я только что вспомнила, что мне нужно быть в другом месте прямо сейчас,)] — говоришь ты, отступая от двери.</p><p>[amber.speech(Не смей больше тратить мое время, сучка!)] — рычит Эмбер, прежде чем захлопнуть дверь прямо перед твоим носом.</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode OUTSIDE_APOLOGY = new DialogueNode("Дом Зараникса", "", true, true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_APOLOGISE"));
			
			if(!Main.game.isFootContentEnabled()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_APOLOGISE_NO_FOOT"));
			} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_APOLOGISE_MOUTH_FREE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_APOLOGISE_MOUTH_BLOCKED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.isFootContentEnabled()) {
				if(index == 1) {
					return new Response("Войти", "Отказаться лизать пятки Эмбер и пройти мимо нее в дом Зараникса.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -2.5f));
							Main.game.getNpc(Amber.class).returnToHome();
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_APOLOGISE"));
						}
					};
					
				} else if (index == 2) {
					return new Response("Неохотно лизать", "Неохотно сделать, как говорит Эмбер, и начать послушно лизать ее пятки.", OUTSIDE_LICKING_RELUCTANT,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.THREE_DIRTY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
	
				} else if (index == 3) {
					return new Response("Жадно лизать", "Жадно сделать, как говорит Эмбер, и начать с энтузиазмом лизать ее пятки.", OUTSIDE_LICKING_EAGER,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.THREE_DIRTY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
	
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Войти", "Теперь Эмбер позволит вам войти в дом Зараникса.", ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).returnToHome();
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_MOUTH_BLOCKED"));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_LICKING_RELUCTANT = new DialogueNode("Дом Зараникса", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "OUTSIDE_LICKING_RELUCTANT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Войти", "Наконец-то Эмбер позволит вам войти в дом Зараникса...", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_RELUCTANT_LICKING"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_LICKING_EAGER = new DialogueNode("Дом Зараникса", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_EAGER_LICKING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отказаться", "Отказаться лизать подошвы Эмбер.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_EAGER_LICKING"));
					}
				};

			} else if(index == 2) {
				return new Response("Повиноваться", "Сделать так, как говорит Эмбер, и начать чистить её пятки своим [pc.tongue].", OUTSIDE_LICKING_SOLES,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.THREE_DIRTY, null, null, null) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode OUTSIDE_LICKING_SOLES = new DialogueNode("Дом Зараникса", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KNOCK_ON_DOOR_SOLES_LICKING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Войти", "Теперь Эмбер позволит вам войти в дом Зараникса.", ENTRANCE) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRY_SOLES_LICKING"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	// General places:
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getLabel() {
			return "Прихожая";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ENTRANCE"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Выход", "Покинуть дом Зараникса и вернуться в Обитель демонов.", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						resetHouseAfterLeaving();
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode STAIRS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getLabel() {
			return "Лестница";
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKatherine.class));
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "STAIRS"));
			
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKatherine.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKatherine.class))) {
					return new Response("Наверх", "Сначала вам нужно ответить Кэтрин!", null);
					
				} else {
					return new Response("Наверх", "Подняться на второй этаж дома Зараникса.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
						}
					};
				}

			} else if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKatherine.class))) {
				if(index==2) {
					return new ResponseSex("Секс", "Немного развлечься с Кэтрин.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
									null,
									null),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else if(index==3) {
					return new ResponseSex("Подчиниться",
							"Вы не решаетесь взять на себя доминирующую роль, но вы <i>очень</i> хотите заняться сексом с Кэтрин. Возможно, если вы подчинитесь, [katherine.she] будет готова трахнуть вас?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else if (index == 4) {
					return new Response("Трансформации",
							"Заставить Кэтрин использовать [katherine.her] демонические силы, чтобы превратить [katherine.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKatherine.class));
						}
					};
					
				} else if(index == 5 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					return new Response("Отклонить",
							"Сказать «нет» похотливой демонице и идти дальше.",
							KATHERINE_DECLINE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_KATHERINE_SEX = new DialogueNode("", "Кэтрин глубоко вздыхает и делает шаг назад.", false) {

		@Override
		public String getLabel() {
			return "Завершено";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(ZaranixMaidKatherine.class)) == 0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_KATHERINE_SEX_NO_ORGASM"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_KATHERINE_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_STAIRS)) {
				if(index==1) {
					return new Response("Наверх", "Подняться на второй этаж дома Зараникса.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KATHERINE_DECLINE = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX_DECLINED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_STAIRS)) {
				if(index==1) {
					return new Response("Наверх", "Подняться на второй этаж дома Зараникса.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getLabel() {
			return "Коридор";
		}

		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKatherine.class));
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "CORRIDOR"));

			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKatherine.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKatherine.class))) {
				if(index==1) {
					return new ResponseSex("Секс", "Немного развлечься с Кэтрин.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
									null,
									null),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("Подчиниться",
							"Вы не решаетесь взять на себя доминирующую роль, но вы <i>очень</i> хотите заняться сексом с Кэтрин. Возможно, если вы подчинитесь, [katherine.she] будет готова трахнуть вас?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_KATHERINE_SEX,
							UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "KATHERINE_SEX_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else if (index == 3) {
					return new Response("Трансформации",
							"Заставить Кэтрин использовать [katherine.her] демонические силы, чтобы превратить [katherine.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKatherine.class));
						}
					};
					
				} else if(index == 4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.katherineRepeatEncountered)) {
					return new Response("Отклонить",
							"Сказать «нет» похотливой демонице и идти дальше.",
							KATHERINE_DECLINE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.katherineRepeatEncountered, true);
						}
					};
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode GARDEN_ENTRY = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getLabel() {
			return "Сад Зараникса";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "GARDEN_ENTRY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GARDEN = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getLabel() {
			return "Сад Зараникса";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "GARDEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GARDEN_ROOM = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getLabel() {
			return "Садовая комната";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "GARDEN_ROOM"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ROOM = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 20;
		}

		@Override
		public String getLabel() {
			return "Комната";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LOUNGE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Эмбер", "Подойти к Эмбер и поздороваться.", LOUNGE_AMBER_GREETING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_GREETING = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_GREETING"));
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					|| Main.game.getCurrentWeather()==Weather.RAIN) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_GREETING_BAD_WEATHER"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_GREETING_GOOD_WEATHER"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					|| Main.game.getCurrentWeather()==Weather.RAIN) {
				if(index==1) {
					return new Response("Подчиниться", "Сделать так, как говорит Эмбер: встать на четвереньки, готовым служить ей подставкой для ног.", LOUNGE_AMBER_FOOT_STALL,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.TWO_HORNY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
					
				} else if(index==2) {
					return new Response("Отклонить", "Сказать Эмбер, что тебе на самом деле не особо интересны подобные вещи.", LOUNGE_AMBER_DECLINED_FOOT_STALL) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
					
				} else if(index==3) {
					if(Main.game.getNpc(Amber.class).getAffection(Main.game.getPlayer())<100) {
						return new Response("Субмиссивный секс",
								"Эмбер ни за что не переспит с тобой, если ты просто попросишь об этом. Возможно, стоит попробовать, если сначала ты её как следует впечатлишь... (Требуется максимальная Привязанность Эмбер к тебе.)",
								null);
						
					} else {
						return new ResponseSex("Субмиссивный секс", "Попросить госпожу Эмбер трахнуть тебя. Ты был для неё послушной сучкой, поэтому думаешь, что она согласится.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_MASOCHIST), null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMAmberDoggyFucked(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
								null,
								null,
								AMBER_LOUNGE_POST_CONSENSUAL_SEX,
								UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_BEG_FOR_SEX"));
					}
					
				} else if(index==4) {
					return new Response("Отвалить", "Сказать Эмбер, чтобы она отвалила.", LOUNGE_AMBER_FURIOUS) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -25));
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Подчиниться", "Сделать так, как говорит Эмбер: встать на четвереньки, готовым отправиться на прогулку.", LOUNGE_AMBER_WALKIES,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK)==null || !Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK).getClothingType().getId().equals("innoxia_neck_ambers_bitch_collar")) {
								AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_neck_ambers_bitch_collar", false);
								
								if(Main.game.getPlayer().isAbleToEquip(collar, true, Main.game.getNpc(Amber.class))) {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_ATTACHES_COLLAR"));
									Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().equipClothingFromNowhere(collar, true, Main.game.getNpc(Amber.class))+"</p>");
								} else {
									UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_WITHOUT_COLLAR"));
								}
								
							} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK)!=null && !Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK).getClothingType().getId().equals("innoxia_neck_ambers_bitch_collar")) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_WITH_COLLAR"));
								
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_WITHOUT_COLLAR"));
							}
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
					
				} else if(index==2) {
					return new Response("Отклонить", "Сказать Эмбер, что тебе на самом деле не особо интересны подобные вещи.", LOUNGE_AMBER_DECLINED_WALK) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
					
				} else if(index==3) {
					if(Main.game.getNpc(Amber.class).getAffection(Main.game.getPlayer())<100) {
						return new Response("Субмиссивный секс",
								"Эмбер ни за что не переспит с тобой, если ты просто попросишь об этом. Возможно, стоит попробовать, если сначала ты её как следует впечатлишь... (Требуется максимальная Привязанность Эмбер к тебе.)",
								null);
						
					} else {
						return new ResponseSex("Субмиссивный секс", "Попросить госпожу Эмбер трахнуть тебя. Ты был для неё послушной сучкой, поэтому думаешь, что она согласится.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_MASOCHIST), null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMAmberDoggyFucked(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
								null,
								null,
								AMBER_LOUNGE_POST_CONSENSUAL_SEX,
								UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_BEG_FOR_SEX"));
					}
					
				}  else if(index==4) {
					return new Response("Отвалить", "Сказать Эмбер, чтобы она отвалила.", LOUNGE_AMBER_FURIOUS) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -25));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_FOOT_STALL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public boolean isTravelDisabled() {
			return Main.game.isFootContentEnabled();
		}
		@Override
		public String getLabel() {
			return "Гостиная";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_STALL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.isFootContentEnabled()) {
				if(index==1) {
					return new Response("Массировать ступни", "Сделать так, как говорит Амбер: снять с неё туфли и чулки, чтобы помассировать ступни.", LOUNGE_AMBER_FOOT_MASSAGE,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.THREE_DIRTY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 2.5f));
						}
					};
					
				} else if(index==2) {
					return new Response("Отклонить", "Не двигаться и отказаться массировать ступни Амбер.", LOUNGE_AMBER_FOOT_MASSAGE_REFUSED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
				}
				return null;
				
			} else {
				return LOUNGE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_DECLINED_FOOT_STALL = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_DECLINED_FOOT_STALL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_FOOT_MASSAGE_REFUSED = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_FOOT_MASSAGE = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public boolean isTravelDisabled() {
			return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
		}
		
		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE"));
			
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_MOUTH_FREE"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_MOUTH_BLOCKED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Сосать пальцы ног", "Сделать так, как говорит Амбер: начать ласкать её ступни ртом.", LOUNGE_AMBER_FOOT_MASSAGE_ORAL,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.THREE_DIRTY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 10));
						}
					};
					
				} else if(index==2) {
					return new Response("Отклонить", "Не двигаться и отказаться лизать ступни Амбер.", LOUNGE_AMBER_FOOT_MASSAGE_ORAL_REFUSED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return LOUNGE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_FOOT_MASSAGE_ORAL = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_ORAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_FOOT_MASSAGE_ORAL_REFUSED = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FOOT_MASSAGE_ORAL_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_DECLINED_WALK = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_DECLINED_WALK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_FURIOUS = new DialogueNode("Гостиная", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FURIOUS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отказаться", "Отказаться позволить Амбер трахнуть тебя.", LOUNGE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FURIOUS_NO_APOLOGY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -10));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Подчиниться",
						"Сделать так, как говорит Амбер: встать на четвереньки, готовым к тому, чтобы она тебя трахнула.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AMBER_LOUNGE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_FURIOUS_APOLOGY")) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 15));
					}
				};
				
			} if(index==3) {
				return new Response("Оскорбить её", "Испытать удачу и снова оскорбить Амбер.", LOUNGE_AMBER_LOSES_IT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -25));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AMBER_LOUNGE_POST_CONSENSUAL_SEX = new DialogueNode("Свалиться", "С Эмбер покончено.", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AMBER_LOUNGE_POST_CONSENSUAL_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AMBER_LOUNGE_POST_SEX = new DialogueNode("Свалиться", "С Эмбер покончено.", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AMBER_LOUNGE_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_LOSES_IT = new DialogueNode("Гостиная", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_LOSES_IT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseCombat("Сразиться", "Защищайся от Эмбер!", Main.game.getNpc(Amber.class));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode COMBAT_LOSS = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_LOSS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Использовано", "Амбер начинает трахать тебя.",
						false, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_AMBER_SEX_LOSS,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_LOSS_SEX"));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_AMBER_SEX_LOSS = new DialogueNode("Использовано", "", false) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_AMBER_SEX_LOSS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode COMBAT_VICTORY = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отказаться", "Отказаться от секса с Эмбер и оставить её обдумывать свою грубость.", COMBAT_VICTORY_LEAVE);
				
			} if(index==2) {
				return new ResponseSex("Секс", "Повеселиться с Эмбер.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
								null,
								null),
						AFTER_AMBER_SEX_VICTORY,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY_SEX"));
				
			} else if(index==3) {
				return new ResponseSex("Подчиниться",
						"Огненный темперамент Эмбер чертовски тебя возбуждает. Ты не решаешься взять на себя доминирующую роль, но ты <i>действительно</i> хочешь заняться с ней сексом. Возможно, если ты подчинишься, она сама захочет тебя оттрахать?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_AMBER_SEX_SUB_VICTORY,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY_SEX_SEB"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode COMBAT_VICTORY_LEAVE = new DialogueNode("Завершено", "", false) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "COMBAT_VICTORY_LEAVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode AFTER_AMBER_SEX_VICTORY = new DialogueNode("Завершено", "", false) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_AMBER_SEX_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_AMBER_SEX_SUB_VICTORY = new DialogueNode("Использовано", "", false) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "AFTER_AMBER_SEX_SUB_VICTORY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER_WALKIES = new DialogueNode("Гостиная", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Место!", "Ползти за Эмбер, пока она ведет тебя в Дом Демонов.", WALKIES_HUMILIATION) {
					@Override
					public DialogueNode getNextDialogue() {
						double rnd = Math.random();
//						if(rnd>0.01f) { // Testing:
//							return WALKIES_PET_FUCKS;
//						}
						if(rnd<0.2) {
							return WALKIES_PEACEFUL;
						} else if(rnd<0.4) {
							return WALKIES_HUMILIATION;
						} else if(rnd<0.6) {
							return WALKIES_PUNISHMENT;
						} else if(rnd<0.85
								&& (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)))) {
							return WALKIES_AMBER_FUCKS;
						} else {
							return WALKIES_PET_FUCKS;
						}
					}
					@Override
					public void effects() {
						Cell c = Main.game.getWorlds().get(WorldType.DOMINION).getRandomCell(PlaceType.DOMINION_DEMON_HOME);
						Main.game.getNpc(Amber.class).setLocation(WorldType.DOMINION, c.getLocation(), false);
						Main.game.getPlayer().setLocation(WorldType.DOMINION, c.getLocation(), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "LOUNGE_AMBER_WALKIES_BEGIN"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_HUMILIATION = new DialogueNode("Демоний Дом", "", true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.isFootContentEnabled()) {
				if(index==1) {
					return new Response("Не лизать", "Отказаться лизать туфли Эмбер на глазах у её друзей.", WALKIES_HUMILIATION_REFUSE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -10));
						}
					};
					
				} else if(index==2) {
					return new Response("Неохотный лизь", "Неохотно лизать туфли Эмбер на глазах у её друзей.", WALKIES_HUMILIATION_RELUCTANT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -2.5f));
						}
					};
					
				} else if(index==3) {
					return new Response("Жадный лизь", "С жадностью лизать туфли Амбер на глазах у её друзей.", WALKIES_HUMILIATION_EAGER) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 5f));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Не умолять", "Отказываться униженно выпрашивать милость, как собака, перед друзьями Амбер.", WALKIES_HUMILIATION_REFUSE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -10));
						}
					};
					
				} else if(index==2) {
					return new Response("Неохотное моление", "Неохотно скулить и умолять, как собака, перед друзьями Амбер.", WALKIES_HUMILIATION_RELUCTANT) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -2.5f));
						}
					};
					
				} else if(index==3) {
					return new Response("Страстное моление", "С жадностью умолять, как собака, перед друзьями Амбер.", WALKIES_HUMILIATION_EAGER) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 5f));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode WALKIES_HUMILIATION_REFUSE = new DialogueNode("Демоний Дом", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_REFUSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WALKIES_HUMILIATION_RELUCTANT = new DialogueNode("Демоний Дом", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.isFootContentEnabled()) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_RELUCTANT");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_RELUCTANT_NO_FOOT");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WALKIES_HUMILIATION_EAGER = new DialogueNode("Демоний Дом", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.isFootContentEnabled()) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_EAGER");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HUMILIATION_EAGER_NO_FOOT");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode WALKIES_PEACEFUL = new DialogueNode("Демоний Дом", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PEACEFUL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_PUNISHMENT = new DialogueNode("Демоний Дом", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PUNISHMENT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_AMBER_FUCKS = new DialogueNode("Демоний Дом", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Отказаться", "Отказаться позволить Эмбер трахать тебя на людях.", WALKIES_AMBER_FUCKS_REFUSED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -15f));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Трахнули на людях",
						"Эмбер начинает трахать тебя прямо там, на глазах у всех!",
						null, null, null, null, null, null,
						true, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						WALKIES_AMBER_FUCKS_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS_START")) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 10f));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_AMBER_FUCKS_REFUSED = new DialogueNode("Демоний Дом", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_AMBER_FUCKS_POST_SEX = new DialogueNode("Использовано", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_AMBER_FUCKS_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_PET_FUCKS = new DialogueNode("Демоний Дом", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Подруга Эмбер", "Вы остаетесь рядом с госпожой Эмбер и направляетесь к той, кто, должно быть, является подругой госпожи.", WALKIES_PET_FUCKS_SNIFFING) {
					@Override
					public void effects() {
						Gender petGender = Gender.getGenderFromUserPreferences(false, false);
						pet = new GenericSexualPartner(
								petGender.getGenderName().isHasPenis()?petGender:(petGender.isFeminine()?Gender.F_P_V_B_FUTANARI:Gender.M_P_MALE), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
						pet.setName(Util.randomItemFrom(Name.petNames));
						pet.setPlayerKnowsName(true);
						owner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
						owner.setBody(Gender.getGenderFromUserPreferences(false, false), RacialBody.DEMON, RaceStage.GREATER, false);
						owner.setName(Name.getRandomTriplet(owner.getSubspecies()));
						
						try {
							Main.game.addNPC(pet, false);
							Main.game.addNPC(owner, false);
							
							pet.setAffection(owner, (float) (75 + Math.random()*25));
							pet.setObedience(100);
							switch(Main.game.getPlayer().getFemininity()) {
							case ANDROGYNOUS:
								pet.setSexualOrientation(SexualOrientation.AMBIPHILIC);
								break;
							case FEMININE:
							case FEMININE_STRONG:
								pet.setSexualOrientation(SexualOrientation.GYNEPHILIC);
								break;
							case MASCULINE:
							case MASCULINE_STRONG:
								pet.setSexualOrientation(SexualOrientation.ANDROPHILIC);
								break;
							}
							owner.addSlave(pet);
							pet.unequipAllClothingIntoVoid(true, true);
							pet.clearFetishDesires();
							pet.clearFetishes();
							if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								if(Math.random()<0.33f) {
									pet.addFetish(Fetish.FETISH_ANAL_GIVING);
								}
								pet.addFetish(Fetish.FETISH_VAGINAL_GIVING);
							} else {
								pet.addFetish(Fetish.FETISH_ANAL_GIVING);
							}
							pet.addFetish(Fetish.FETISH_ORAL_RECEIVING);
							pet.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", false), true, owner);
							if(Math.random()>0.5f) {
								pet.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", false), true, owner);
							}
							owner.setAffection(pet, (float) (10 + Math.random()*90));
							owner.setPlayerKnowsName(true);
							
							Main.game.setActiveNPC(pet);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_PET_FUCKS_SNIFFING = new DialogueNode("Демоний Дом", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING", Util.newArrayListOfValues(owner, pet)));
			
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING_MOUNTING", Util.newArrayListOfValues(owner, pet)));
			} else if (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING_ORAL", Util.newArrayListOfValues(owner, pet)));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_SNIFFING_NO_SEX", Util.newArrayListOfValues(owner, pet)));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// If no area is available, just return home:
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).returnToHome();
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
							Main.game.banishNPC(owner);
							Main.game.banishNPC(pet);
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Отказаться", "Отказаться предстать перед [npc.name].", WALKIES_PET_FUCKS_REFUSE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), -10f));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						// Mounting player:
						return new ResponseSex("Предстать перед ней",
								"Сделать так, как велит Эмбер, и предстать перед ней, готовым к тому, чтобы [npc.name] оседлала тебя!",
								null, null, null, null, null, null,
								true, false,
								new SMPetMounting(
										Util.newHashMapOfValues(new Value<>(pet, SexSlotUnique.PET_MOUNTING_HUMPING)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS))) {
									@Override
									public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
										return Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.VAGINA)));
									}
								},
								null,
								null,
								WALKIES_PET_FUCKS_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START", Util.newArrayListOfValues(owner, pet))
								+ (Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS) || (Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA))
										?UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START_EXPOSED", Util.newArrayListOfValues(owner, pet))
										:UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START_CLOTHING", Util.newArrayListOfValues(owner, pet)))
								) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 10f));
							}
						};
					
					} else {
						// Player performing oral:
						return new ResponseSex("Сделать минет",
								"Сделать так, как велит Эмбер, и приготовиться сосать [npc.namePos] [npc.cock+].",
								null, null, null, null, null, null,
								true, false,
								new SMPetOral(
										Util.newHashMapOfValues(new Value<>(pet, SexSlotUnique.PET_ORAL_COCKED_LEG)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.PET_ORAL_ON_ALL_FOURS))),
								null,
								null, WALKIES_PET_FUCKS_POST_SEX_ORAL, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_START_ORAL", Util.newArrayListOfValues(owner, pet))) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Amber.class).incrementAffection(Main.game.getPlayer(), 10f));
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode WALKIES_PET_FUCKS_POST_SEX = new DialogueNode("Использовано", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_POST_SEX", Util.newArrayListOfValues(owner, pet));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.banishNPC(owner);
						owner.removeSlave(pet); //To stop issues with banishNPC() TODO fix this
						Main.game.banishNPC(pet);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_PET_FUCKS_POST_SEX_ORAL = new DialogueNode("Использовано", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_POST_SEX_ORAL", Util.newArrayListOfValues(owner, pet));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.banishNPC(owner);
						owner.removeSlave(pet); //To stop issues with banishNPC() TODO fix this
						Main.game.banishNPC(pet);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_PET_FUCKS_REFUSE = new DialogueNode("Демоний Дом", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_PET_FUCKS_REFUSE", Util.newArrayListOfValues(owner, pet));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Домой", "Ползти рядом с Амбер, пока она ведет тебя обратно в дом Зараникса.", WALKIES_HOME) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.banishNPC(owner);
						Main.game.banishNPC(pet);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WALKIES_HOME = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getLabel() {
			return "Прихожая";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloorRepeat", "WALKIES_HOME"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LOUNGE.getResponse(responseTab, index);
		}
	};
	
	
	
}
