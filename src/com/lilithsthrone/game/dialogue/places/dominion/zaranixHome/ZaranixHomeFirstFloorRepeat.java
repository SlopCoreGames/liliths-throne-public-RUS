package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.3
 * @author Innoxia
 */
public class ZaranixHomeFirstFloorRepeat {

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
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class));
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "STAIRS"));
			
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if (index == 1) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
						return new Response("Вниз", "Сначала тебе нужно ответить Келли!", null);
						
					} else {
						return new Response("Вниз", "Спуститься на первый этаж дома Зараникса.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
							}
						};
					}
	
				} else if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
					if(index==2) {
						return new ResponseSex("Секс", "Немного развлечься с Келли.",
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
								null,
								null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
							}
						};
						
					} else if(index==3) {
						return new ResponseSex("Подчиниться",
								"Ты не решаешься взять на себя доминирующую роль, но ты <i>действительно</i> хочешь переспать с Келли. Может, если ты подчинишься, [kelly.she]будет готова трахнуть тебя?",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_SUB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
							}
						};
						
					} else if (index == 4) {
						return new Response("Трансформации",
								"Заставить Келли использовать [kelly.her] демонические силы, чтобы превратить [kelly.herself]...",
								BodyChanging.BODY_CHANGING_CORE){
							@Override
							public void effects() {
								Main.game.saveDialogueNode();
								BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
							}
						};
						
					} else if(index == 5 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
						return new Response("Отклонить",
								"Сказать «нет» похотливой демонице и идти дальше.",
								STAIRS){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_DECLINED"));
							}
						};
						
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				if(index == 1) {
					return new Response("Вниз", "Спуститься на первый этаж дома Зараникса.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
						}
					};
				} else {
					return null;
				}
			}
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
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered) && Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class));
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "CORRIDOR"));

			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_PRESENT_REPEAT"));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(ZaranixMaidKelly.class))) {
				if(index==1) {
					return new ResponseSex("Секс", "Немного развлечься с Келли.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
							null,
							null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
				} else if(index==2) {
					return new ResponseSex("Подчиниться",
							"Ты не решаешься взять на себя доминирующую роль, но ты <i>действительно</i> хочешь переспать с Келли. Может, если ты подчинишься, [kelly.she]будет готова трахнуть тебя?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), AFTER_KELLY_SEX, UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_SUB")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
						}
					};
					
				} else if (index == 3) {
					return new Response("Трансформации",
							"Заставить Келли использовать [kelly.her] демонические силы, чтобы превратить [kelly.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
						}
					};
					
				} else if(index == 4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kellyRepeatEncountered)) {
					return new Response("Отклонить",
							"Сказать «нет» похотливой демонице и идти дальше.",
							KELLY_DECLINE){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kellyRepeatEncountered, true);
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
	
	public static final DialogueNode KELLY_DECLINE = new DialogueNode("", "", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "KELLY_SEX_DECLINED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_STAIRS)) {
				if(index==1) {
					return new Response("Вниз", "Спуститься на первый этаж дома Зараникса.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_KELLY_SEX = new DialogueNode("", "Келли глубоко вздыхает и отступает назад.", false) {

		@Override
		public String getLabel() {
			return "Завершено";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(ZaranixMaidKelly.class)) == 0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_KELLY_SEX_NO_ORGASM"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_KELLY_SEX"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_FF_STAIRS)) {
				if(index==1) {
					return new Response("Вниз", "Спуститься на первый этаж дома Зараникса.", PlaceType.ZARANIX_GF_STAIRS.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_STAIRS, false);
						}
					};
				}
			}
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_OUTSIDE"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Войти", "Постучать в дверь Зараникса и войти внутрь.", ZARANIX_ROOM_ENTER);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXIT = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_EXIT"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Войти", "Постучать в дверь Зараникса и войти внутрь.", ZARANIX_ROOM_ENTER);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_ENTER = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixRepeatEncountered)) {
				return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_ENTER"));
			} else {
				return(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_ENTER_REPEAT"));
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Уйти", "Попрощаться и снова выйти в коридор первого этажа.", ZARANIX_ROOM_EXIT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 1) {
				return new Response("Горничные", "Спросить Зараникса о его горничных.", ZARANIX_ROOM_MAIDS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 2) {
				return new Response("Эксперименты", "Спросить Зараникса об экспериментах, которые он проводит, и о том, над чем он заставил работать Arthur.", ZARANIX_ROOM_EXPERIMENTS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 3) {
				return new Response("Форма инкуба", "Спросить Зараникса о его форме инкуба. В конце концов, большинство демонов, которых вы видели, предпочитают оставаться женщинами.", ZARANIX_ROOM_INCUBUS_FORM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixRepeatEncountered, true);
					}
				};
				
			} else if(index == 4) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Минет", "Так как у вас нет доступа к своему рту, вы не можете предложить отсосать член Зараникса!", null);
				}
				return new ResponseSex("Минет", "Спросить Зараникса, не хочет ли он отсосать...",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_ZARANIX_BLOWJOB,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index == 5) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Минет (суккуб)", "Так как у вас нет доступа к своему рту, вы не можете предложить отсосать член Зараникса!", null);
				}
				return new Response("Минет (суккуб)", "Сказать Зараниксу, что если он превратится в суккуба, то ты будешь счастлив отсосать у «неё»...", ZARANIX_ROOM_BLOWJOB_TF) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_MAIDS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_MAIDS"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Горничные", "Вы уже спрашивали Зараникса о его горничных!", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_EXPERIMENTS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_EXPERIMENTS"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 2) {
				return new Response("Эксперименты", "Вы уже спрашивали Зараникса о его экспериментах!", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_INCUBUS_FORM = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_INCUBUS_FORM"));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 3) {
				return new Response("Форма инкуба", "Вы уже спрашивали Зараникса о его форме инкуба!", null);
			} else {
				return ZARANIX_ROOM_ENTER.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode ZARANIX_ROOM_BLOWJOB_TF = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_START"));
			((Zaranix)Main.game.getNpc(Zaranix.class)).transformFeminine();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_END"));
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Сосать член", "Приступить к сосанию члена Зараникса...",
						true, true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_ZARANIX_BLOWJOB,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "ZARANIX_ROOM_BLOWJOB_TF_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SEX_ZARANIX_BLOWJOB = new DialogueNode("Завершено", "Зараниксу пока хватит ощущать ваши навыки минета...", true) {
		@Override
		public String getLabel() {
			return "Завершено";
		}
		@Override
		public String getContent() {
			return (UtilText.parseFromXMLFile("places/dominion/zaranixHome/firstFloorRepeat", "AFTER_SEX_ZARANIX_BLOWJOB"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь по особняку Зараникса...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_CORRIDOR);
						if(Main.game.getNpc(Zaranix.class).isFeminine()) {
							Main.game.getNpc(Zaranix.class).setStartingBody(false);
						}
					}
				};
			}
			return null;
		}
	};
}
