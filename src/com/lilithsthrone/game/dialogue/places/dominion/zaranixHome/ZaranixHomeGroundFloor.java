package com.lilithsthrone.game.dialogue.places.dominion.zaranixHome;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Amber;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKatherine;
import com.lilithsthrone.game.character.npc.dominion.ZaranixMaidKelly;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.89
 * @version 0.3.4
 * @author Innoxia
 */
public class ZaranixHomeGroundFloor {
	
	public static void resetHouseAfterLeaving() {
		// Maids:
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKatherineSubdued, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKellySubdued, false);
		
		Main.game.getNpc(Amber.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);
		
		((Zaranix)Main.game.getNpc(Zaranix.class)).setStartingBody(false);
		((Amber)Main.game.getNpc(Amber.class)).setStartingBody(false);
		((ZaranixMaidKatherine)Main.game.getNpc(ZaranixMaidKatherine.class)).setStartingBody(false);
		((ZaranixMaidKelly)Main.game.getNpc(ZaranixMaidKelly.class)).setStartingBody(false);
	}

	private static void travelFromEntranceToLounge() {
		for(Cell c : Pathing.aStarPathing(
				Main.game.getWorlds().get(WorldType.ZARANIX_HOUSE_GROUND_FLOOR).getCellGrid(),
				Main.game.getWorlds().get(WorldType.ZARANIX_HOUSE_GROUND_FLOOR).getCell(PlaceType.ZARANIX_GF_ENTRANCE).getLocation(),
				Main.game.getWorlds().get(WorldType.ZARANIX_HOUSE_GROUND_FLOOR).getCell(PlaceType.ZARANIX_GF_LOUNGE).getLocation(),
				false)) {
			c.setDiscovered(true);
			c.setTravelledTo(true);
		}
		Main.game.getNpc(Amber.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
		Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
		
		for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
			companion.setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
		}
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixDiscoveredHome)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT"));
				
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT_HOSTILE_MAIDS"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_REPEAT_NON_HOSTILE_MAIDS"));
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Постучать в дверь", "В такое неурочное время никто не придет открывать дверь. Придется вернуться днем или найти другой способ попасть внутрь.", null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixMaidsHostile)) {
					return new Response("Постучать в дверь", "Горничные Зараникса узнают тебя с первого взгляда и не пустят. Тебе придется найти другой способ попасть внутрь.", null);
				}
				return new Response("Постучать в дверь", "Постучать в дверь и ждать, пока кто-нибудь ответит.", OUTSIDE_KNOCK_ON_DOOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Перелететь через забор", "В такое неурочное время дом наверняка надежно заперт. Придется вернуться днем или найти другой способ попасть внутрь.", null);
					}
					return new Response("Перелететь через забор", "Перелететь через садовый забор и посмотреть, нет ли там какого-нибудь входа.", GARDEN_ENTRY) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getTextStartStringBuilder()
								.append("<p>Маленький забор, подобный тому, что перед вами, не станет преградой для того, кто умеет летать.")
								.append(!Main.game.getPlayer().isAbleToFlyFromExtraParts() ? " Расправив крылья, вы" : "Вы")
								.append(" немного разбегаетесь, прежде чем взмыть в воздух. Быстро набирая высоту, вы разворачиваетесь в воздухе и пикируете в сад, прилегающий к дому Зараникса.</p>");
						}
					};
				}
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Перелезть через забор", "В такое неурочное время дом наверняка надежно заперт. Придется вернуться днем или найти другой способ попасть внутрь.", null);
				}
				return new Response("Перелезть через забор", "Перелезть через садовый забор и поискать способ проникнуть внутрь через него.", GARDEN_ENTRY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_GARDEN_ENTRY, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
						Main.game.getTextStartStringBuilder().append(
							"<p>Решив попытаться пробраться в дом Зараникса незаметно, вы кружите возле забора, отделяющего его сад от общественной улицы, выжидая момент, чтобы преодолеть препятствие. Убедившись, что никто не смотрит в вашу сторону, вы быстро взбираетесь по железным прутьям забора и, перекинув через него свои [pc.legs], спрыгиваете в частный сад.</p>");
					}
				};

			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKickedDownDoor)) {
					return new Response("Выбить дверь ногой", "После вашего прошлого визита входную дверь укрепили. Теперь так просто войти не получится.", null);
					
				} else if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE) >= 35) {
					return new Response("Выбить дверь ногой", "Выбить входную дверь.", ENTRANCE_KICK_DOWN_DOOR) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKickedDownDoor, true);
							Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
							Main.game.getNpc(Amber.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_ENTRANCE, false);
						}
					};
				} else {
					return new Response("Выбить дверь ногой", "Тебе кажется, что ты недостаточно силен, чтобы выбить такую добротную дверь. (Требуется 35 телосложения.)", null);
				}
				
			} else if (index == 0) {
				return new Response("Уйти", "Развернуться и уйти.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().incrementSavedLong("amber_door_knock_repeat_count", 1);
		}
		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				
				if (index == 1) {
					return new Response("Отступить", "Отойти от двери и подумать о том, чтобы найти другой путь внутрь.", OUTSIDE) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).returnToHome();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_SLAMMED_IN_FACE"));
						}
					};

				} else if (index == 2) {
					return new Response("Умолять", "Умолять горничную впустить тебя.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null);

				} else if(index == 3 && Main.game.getDialogueFlags().getSavedLong("amber_door_knock_repeat_count")>=4) {
					return new Response("Войти", "Похоже, твое упорство окупилось!", MEETING_ZARANIX) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
							travelFromEntranceToLounge();
						}
					};
					
				}
				return null;
				
			} else {
				if (index == 1) {
					return new Response("Arthur", "Попросить увидеть Arthur.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
						}
					};
					
				} else if (index == 0) {
					return new Response("Уйти", "Сказать, что ты ошибся домом, и удалиться.", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_WRONG_HOUSE"));
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отступить", "Отойти от двери и подумать о том, чтобы найти другой путь внутрь.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_SLAMMED_IN_FACE"));
					}
				};

			} else if (index == 2) {
				return new Response("Умолять", "Умолять горничную впустить тебя.", OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG,
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), CorruptionLevel.THREE_DIRTY, null, null, null);
			}
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Встань и сделай шаг назад. Ты определенно не готов на такое, как <i>это</i>.", OUTSIDE) {
					@Override
					public void effects() {
						Main.game.getNpc(Amber.class).returnToHome();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_REFUSE"));
					}
				};

			}
			if(!Main.game.isFootContentEnabled()) {
				if(index == 1) {
					return new Response("Хороший песик", "Сделай так, как велит эта доминантная суккуб: встань на четвереньки и скажи ей, что ты хороший песик.",
							OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_GOOD_DOGGY,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST), CorruptionLevel.TWO_HORNY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
				}
				
			} else {
				if(index == 1) {
					return new Response("Неохотный лизь", "Если это цена за встречу с Arthur, ты полагаешь, что справишься, хотя всё это тебе и в тягость.",
							OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.TWO_HORNY, null, null, null) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
	
				} else if(index == 2) {
					return new Response("Жадный лизь", "Тут же упасть на четвереньки и с энтузиазмом вылизать туфли горничной.",
							OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK,
							Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.FOUR_LUSTFUL, null, null, null) {
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_GOOD_DOGGY = new DialogueNode("", "", true, true) {
		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_GOOD_DOGGY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Внутри", "Ползти следом за Amber, пока она ведет тебя в дом.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						travelFromEntranceToLounge();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_RELUCTANT_LICK = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_RELUCTANT_LICK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Внутри", "Ползти следом за Amber, пока она ведет тебя в дом.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						travelFromEntranceToLounge();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_EAGER_LICK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ждать", "Сделать, как говорит Amber, и ждать её возвращения.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_WAITING"));
						travelFromEntranceToLounge();
					}
				};

			} else if (index == 2) {
				return new Response("Лизать подошвы", "Не давай Эмбер уйти так просто! Ты ведь еще не вылизал подошвы её туфель!",
						OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES,
						Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_FOOT_RECEIVING), CorruptionLevel.FIVE_CORRUPT, null, null, null);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode OUTSIDE_KNOCK_ON_DOOR_ASK_FOR_ARTHUR_SUBMISSIVE_EAGER_LICK_SOLES = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Дом Зараникса";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "KNOCK_ON_DOOR_ASK_FOR_ARTHUR_BEG_EAGER_LICK_SOLES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Внутри", "Ползти следом за Amber, пока она ведет тебя в дом.", MEETING_ZARANIX) {
					@Override
					public void effects() {
						travelFromEntranceToLounge();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Set this to true here so that the repeat encounter with Amber at the door doesn't end up with her acting as though you broke in
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberRepeatEncountered, true);
		}
		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Сесть на пол", "Исполнить приказ Эмбер и сесть на пол.", MEETING_ZARANIX_SIT_FLOOR) {
					@Override
					public void effects() {
						Main.game.getNpc(Zaranix.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.amberSatOnFloor, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Сесть на диван", "Проявить непокорность Эмбер и сесть на один из диванов.", MEETING_ZARANIX_SIT_SOFA) {
					@Override
					public void effects() {
						Main.game.getNpc(Zaranix.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_SIT_FLOOR = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_SIT_FLOOR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Сдерживаться", "Просто сказать, что Lilaya хочет вернуть Arthur, не вдаваясь в подробности, почему.", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("Объяснить всё", "Рассказать Zaranix всё о своём появлении в этом мире и о том, что Lilaya нужна помощь Arthur, чтобы разобраться в происходящем.", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_SIT_SOFA = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_SIT_SOFA");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Сдерживаться", "Просто сказать, что Lilaya хочет вернуть Arthur, не вдаваясь в подробности, почему.", MEETING_ZARANIX_HOLD_BACK);
				
			} else if (index == 2) {
				return new Response("Объяснить всё", "Рассказать Zaranix всё о своём появлении в этом мире и о том, что Lilaya нужна помощь Arthur, чтобы разобраться в происходящем.", MEETING_ZARANIX_EXPLAIN_EVERYTHING);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_HOLD_BACK = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_HOLD_BACK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Arthur", "Наконец-то вы лицом к лицу встретились со своей неуловимой добычей.", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getNpc(Arthur.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_EXPLAIN_EVERYTHING = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_EXPLAIN_EVERYTHING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Arthur", "Наконец-то вы лицом к лицу встретились со своей неуловимой добычей.", MEETING_ZARANIX_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
						Main.game.getNpc(Arthur.class).setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_ARTHUR = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Уйти", "Отказаться оказывать любые сексуальные услуги Зараникс или Эмбер и уйти.",  REFUSE_SEX) {
					@Override
					public void effects() {
						Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
					}
				};
				
			} else if (index == 2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("'Поблагодарить' Зараникса", "Вы не можете отсосать член Зараникс, так как у вас нет доступа к собственному рту!", null);
					
				} else {
					return new Response("'Поблагодарить' Зараникса", "Показать Зараникс, как вы ей благодарны.<br/>[style.italicsSex(Это приведет к тому, что вы отсосете Зараникс!)]", MEETING_ZARANIX_ARTHUR_THANK_ZARANIX) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Zaranix.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else if (index == 3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
						&& (Main.game.getPlayer().hasVagina()
								?!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
								:true)) {
					return new Response("'Поблагодарить' Эмбер", "Вы не можете позволить Эмбер трахнуть вас, так как у вас нет доступа к вашему анусу"+(Main.game.getPlayer().hasVagina()?" или влагалищу":"")+"!", null);
					
				} else {
					return new Response("'Поблагодарить' Эмбер", "Покажи Эмбер, как ты ей благодарен.<br/>[style.italicsSex(В результате тебя оттрахает Эмбер!)]", MEETING_ZARANIX_ARTHUR_THANK_AMBER) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).unequipClothingIntoVoid(Main.game.getNpc(Amber.class).getClothingInSlot(InventorySlot.TORSO_UNDER), true, Main.game.getNpc(Amber.class));
							Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_ARTHUR_THANK_ZARANIX = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_ZARANIX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Сосать член",
						"Показать Зараниксу, как хорошо вы умеете сосать член.",
						true,
						true,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING),
										new Value<>(Main.game.getNpc(Amber.class), SexSlotSitting.PERFORMING_ORAL_TWO)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_THANKING_ZARANIX,
						UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_ZARANIX_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Zaranix.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode MEETING_ZARANIX_ARTHUR_THANK_AMBER = new DialogueNode("", "", true, true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_THANK_AMBER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Поднять зад", "Исполнить приказ Эмбер и подставить ей свой зад.",
						true, true,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SEX_THANKING_AMBER,
						"<p>Ты послушно поднимаешь зад навстречу Эмбер, издавая короткий вскрик, когда внезапно чувствуешь резкий шлепок её ладони по своей правой щеке, после чего она рычит: [amber.speech(Визжи сколько влезет, сучка, <i>теперь ты моя!</i>)]</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode REFUSE_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "MEETING_ZARANIX_ARTHUR_REFUSE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить свое путешествие.",  PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_THANKING_ZARANIX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
		}
		@Override
		public String getLabel() {
			return "Зараникс закончил";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "AFTER_SEX_THANKING_ZARANIX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить свое путешествие.",  PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_THANKING_AMBER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
		}
		@Override
		public String getLabel() {
			return "Эмбер закончила";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "AFTER_SEX_THANKING_AMBER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить свое путешествие.",  PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false));
			}
			return null;
		}
	};
	
	
	// Combat route:
	
	public static final DialogueNode ENTRANCE_KICK_DOWN_DOOR = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Прихожая";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR_MAIDS_MET"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_KICK_DOWN_DOOR_MAIDS_NOT_MET"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сразиться", "Защититься от разъяренной горничной!", Main.game.getNpc(Amber.class)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
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
			if(Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return 60;
			}
			return 20;
		}

		@Override
		public String getLabel() {
			return "Прихожая";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE"));
			
			if(Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_PRESENT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Выход", "Покинуть дом Зараникса и вернуться в Дом Демонов. <b>Если ты решишь уйти сейчас, тебе придется пробиваться внутрь заново!</b>", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						resetHouseAfterLeaving();
					}
				};

			} else if(index==2 && Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return new ResponseSex("Использовать Эмбер", "Повеселиться с этой огненной горничной.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
						null,
						null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_SEX"));
				
			} else if(index==3 && Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return new ResponseSex("Подчиниться",
						"Огненный темперамент Эмбер чертовски тебя возбуждает. Ты не решаешься взять на себя доминирующую роль, но ты <i>действительно</i> хочешь заняться с ней сексом. Возможно, если ты подчинишься, она сама захочет тебя оттрахать?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ENTRANCE_AMBER_SEX_SUBMIT"));
				
			} else if (index == 4 && Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
				return new Response("Трансформации",
						"Заставить Эмбер использовать [amber.her] демонические силы, чтобы трансформировать [amber.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Amber.class));
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
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS"));
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "STAIRS_KATHERINE_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Наверх", "Подняться на второй этаж дома Зараникса.", PlaceType.ZARANIX_FF_STAIRS.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_STAIRS, false);
					}
				};

			} else {
				return null;
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
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR"));
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1).getPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_MAID)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_KATHERINE_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ENTRY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					return new Response("Перелететь через забор",
							"Перелететь через забор сада и вернуться в Дом Демонов. <b>Если ты уйдешь, весь твой прогресс в доме Зараникса будет сброшен!</b>",
							PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
						@Override
						public void effects() {
							resetHouseAfterLeaving();
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
							Main.game.getTextStartStringBuilder().append(
									"<p>Решив, что вернешься в другой раз, ты немного разбегаешься по садовой дорожке, а затем взмываешь в воздух. Перелетев через забор, ты очень быстро оказываешься в Доме Демонов...</p>");
						}
					};
				}
				return new Response("Перелезть через забор",
						"Перелезьте через садовый забор и вернитесь в Обитель Демонов. <b>Если вы уйдете, весь ваш прогресс в доме Зараникса будет сброшен!</b>",
						PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						resetHouseAfterLeaving();
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						Main.game.getTextStartStringBuilder().append(
								"<p>Решив, что вернетесь в другой раз, вы карабкаетесь через забор и вскоре снова оказываетесь в Обители Демонов...</p>");
					}
				};

			} else {
				return null;
			}
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GARDEN_ROOM = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getLabel() {
			return "Садовая комната";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM"));
			
			if(Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()) != null
					&& Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation().getX()-1, Main.game.getPlayer().getLocation().getY()).getPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_MAID)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "GARDEN_ROOM_KATHERINE_NOT_SUBDUED"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR_MAID = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				return 30;
			}
			return 60;
		}

		@Override
		public String getLabel() {
			return "Коридор";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SUBDUED");
			
			} else if(Main.game.getNpc(ZaranixMaidKatherine.class).getFoughtPlayerCount()==0) {
				if(Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_ENTRANCE)) {
					return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER_FOUGHT_AMBER");
				}
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER");
			
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_ENCOUNTER_REPEAT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued)) {
				if(index==1) {
					return new ResponseSex("Использовать Кэтрин", "Немного развлечься с этой горничной.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
							null,
							null), ZaranixMaidKatherine.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX"));
					
				} else if(index==2) {
					return new ResponseSex("Подчиниться",
							"Вы не можете заставить себя взять на себя доминирующую роль, но вы <i>действительно</i> хотите заняться сексом с Кэтрин. Возможно, если вы подчинитесь, она сама захочет вас оттрахать?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKatherine.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), ZaranixMaidKatherine.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "CORRIDOR_MAID_KATHERINE_SEX_SUBMIT"));
					
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
					
				} else {
					return null;
				}
			} else {
				if(index==1) {
					return new ResponseCombat("Сразиться", "Защититься от разъяренной горничной!", Main.game.getNpc(ZaranixMaidKatherine.class)) {
						@Override
						public void effects() {
							Main.game.getNpc(ZaranixMaidKatherine.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
						}
					};
				} else {
					return null;
				}
			}
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKatherineSubdued);
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
			return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "ROOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode LOUNGE = new DialogueNode("", "", false) {

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
			if(!Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_LOUNGE)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_EMPTY");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SUBDUED");
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getNpc(Amber.class).getFoughtPlayerCount()!=0) {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET"));
				
				if(Main.game.getNpc(Amber.class).getFoughtPlayerCount()!=0) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_KNOWS_NAME"));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT_ALREADY_MET_DOES_NOT_KNOW_NAME"));
				}
				
				return UtilText.nodeContentSB.toString();
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_PRESENT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNpc(Amber.class).getLocationPlace().getPlaceType().equals(PlaceType.ZARANIX_GF_LOUNGE)) {
				return null;
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixAmberSubdued)) {
				if(index==1) {
					return new ResponseSex("Использовать Эмбер", "Повеселиться с этой огненной горничной.",
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
							null,
							null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX")
							);
					
				} else if(index==2) {
					return new ResponseSex("Подчиниться",
							"Огненный темперамент Эмбер чертовски тебя возбуждает. Ты не решаешься взять на себя доминирующую роль, но ты <i>действительно</i> хочешь заняться с ней сексом. Возможно, если ты подчинишься, она сама захочет тебя оттрахать?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null), Amber.AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_SEX_SUBMIT")
							);
					
				} else if (index == 3) {
					return new Response("Трансформации",
							"Заставить Эмбер использовать [amber.her] демонические силы, чтобы трансформировать [amber.herself]...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(Main.game.getNpc(Amber.class));
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Подойти к горничной", "Подойдите к горничной со спины. <b>Она наверняка заметит ваше приближение, что, скорее всего, приведет к драке!</b>", LOUNGE_AMBER) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getNpc(Amber.class).setPlayerKnowsName(true);
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode LOUNGE_AMBER = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Гостиная";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.zaranixKnockedOnDoor) || Main.game.getNpc(Amber.class).getFoughtPlayerCount()!=0) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING_ALREADY_FOUGHT"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/zaranixHome/groundFloor", "LOUNGE_AMBER_GREETING_NEVER_FOUGHT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Сразиться", "Защититься от разъяренной горничной!", Main.game.getNpc(Amber.class)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, true);
					}
				};
			} else {
				return null;
			}
		}
	};
}
