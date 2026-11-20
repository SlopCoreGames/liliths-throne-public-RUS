package com.lilithsthrone.game.dialogue.places.submission;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLilayaDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3
 * @version 0.3.9.9
 * @author Innoxia
 */
public class LyssiethPalaceDialogue {
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						"Покинуть дворец Lyssieth и отправиться обратно в Submission.",
						!Main.game.getDialogueFlags().hasFlag("innoxia_elizabeth_routine_started")
							?ENTRANCE_LEAVING_FIRST_TIME
							:ENTRANCE_LEAVING) {
					@Override
					public void effects() {
						if(!Main.game.getDialogueFlags().hasFlag("innoxia_elizabeth_routine_started")) {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
							
						} else {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_GATE);
							Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_CAVERN, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE_LEAVING"));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_LEAVING_FIRST_TIME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE_LEAVING_FIRST_TIME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Попрощаться с Элизабет и продолжить свой путь...",
						PlaceType.SUBMISSION_LILIN_PALACE_CAVERN.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_elizabeth_routine_started", true);
						Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_CAVERN, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ENTRANCE_LEAVING_FIRST_TIME_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_LEAVING = new DialogueNode("", "", false) {
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
			return PlaceType.SUBMISSION_LILIN_PALACE_CAVERN.getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public boolean isTravelDisabled() {
			
			return Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.LYSSIETH_PALACE && Main.game.getNpc(Elizabeth.class).getLocation().getDistanceToVector(Main.game.getPlayer().getLocation())<=1;
		}
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			if(Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.LYSSIETH_PALACE && Main.game.getNpc(Elizabeth.class).getLocation().getDistanceToVector(Main.game.getPlayer().getLocation())<=1) {
				return UtilText.parseFromXMLFile("acexp/submission/elizabeth", "INTRO");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Elizabeth.class).getWorldLocation()==WorldType.LYSSIETH_PALACE && Main.game.getNpc(Elizabeth.class).getLocation().getDistanceToVector(Main.game.getPlayer().getLocation())<=1) {
				return DialogueManager.getDialogueFromId("acexp_submission_palace_elizabeth").getResponse(responseTab, index);
			}
			return null;
		}
	};

	public static final DialogueNode WINDOWS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "WINDOWS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode HALL = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "HALL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode STAIRCASE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "STAIRCASE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode SIREN_OFFICE = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE"));
				
				if(Main.game.getPlayer().hasCompanions()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_COMPANION", Main.game.getPlayer().getMainCompanion()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_NO_COMPANION"));
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_MERAXIS_ABSENT"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Войти", "Постучать в дверь, а затем войти в кабинет Lyssieth.", LYSSIETH_OFFICE_ENTER) {
					@Override
					public void effects() {
						conversationIndex = 0;
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))) {
							Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						if(Main.game.getPlayer().hasCompanions()) {
							for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
								companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
							}
						}
					}
				};
				
			} else if(index==6
					&& Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))
					&& Main.game.getNpc(Lilaya.class).getRaceStage()==RaceStage.GREATER
					&& Main.game.getNpc(DarkSiren.class).getRaceStage()!=RaceStage.GREATER) {
				return new Response("Демон",
						"Скажи Мераксис, что ей стоит довести процесс превращения в полноценного демона до конца.<br/>[style.italicsDemon(Это навсегда превратит Мераксис в полноценного демона!)]",
						MERAXIS_DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.RACE_DEMON;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_START"));
					}
				};
				
			} else if(index==0) {
				return new Response("Уйти", "Выйти из кабинета.", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							if(Main.game.getCharactersPresent().contains(Main.game.getNpc(DarkSiren.class))) {
								Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_START = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.meraxisRepeatDemonTF, true);
			Main.game.getNpc(DarkSiren.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			
			Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
			Main.game.getNpc(Lilaya.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
			Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
			
			((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
			
			if(Main.game.getPlayer().hasCompanions()) {
				for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
					companion.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, false);
				}
			}
			
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST, true));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_START_CORE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Готова", "Только что оскверненная любовью к инцесту, Meraxis готова заняться сексом с вами троими и превратиться в демона.", MERAXIS_DEMON_TF_ORIFICE_SELECTION);
				
			} else if(index==2) {
				return new Response("Выйти наружу",
						"Вы не хотите заниматься сексом со своей матерью и сестрами. Выйдите из кабинета Meraxis и подождите, пока они втроем закончат.",
						MERAXIS_DEMON_TF_WAIT_IN_OFFICE){
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(DarkSiren.class);
						Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						if(Main.game.isAnalContentEnabled()) {
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						} else {
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
						}
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_ORIFICE_SELECTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_ORIFICE_SELECTION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_LILAYA_DEMON_TF_SEX.getResponse(responseTab, index); // Orifice selection.
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_WAIT_IN_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_WAIT_IN_OFFICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Демоническая Meraxis",
						"Мераксис выходит из кабинета Lyssieth, позволяя тебе увидеть, как она изменилась.",
						MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_WAIT_IN_OFFICE_FINISHED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("innoxia_meraxis_demon_tf_teleported"))) {
				if(index==1) {
					return new Response("Телепортирован(а)",
							"Вы с Мераксис телепортируетесь обратно в таверну «Красный дракон» в Элисе.",
							DialogueManager.getDialogueFromId("innoxia_places_fields_elis_tavern_f0_meraxis_post_demon_tf")) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							}
						};
					
				} else {
					return null;
				}
				
			} else {
				return SIREN_OFFICE.getResponse(responseTab, index); // Standard actions.
			}
		}
	};

	private static void updateLyssiethPregnancyReactions() {
		Main.game.getNpc(Lyssieth.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Lyssieth.class), true);
	}
	
	private static int conversationIndex = 0;
	private static boolean dominantRepeatSex = true;
	
	public static final DialogueNode LYSSIETH_OFFICE_ENTER = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().hasCompanions()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER_COMPANION", Main.game.getPlayer().getMainCompanion()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER"));
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_ENTER_MAIN"));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Лунетт", conversationIndex==1?"Ты уже расспрашиваешь Lyssieth о её сестре.":"Расспросить Lyssieth о её сестре, Лунетт.", conversationIndex==1?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 1;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_LUNETTE"));
					}
				};
				
			} else if(index==2) {
				return new Response("Семья", conversationIndex==2?"Ты уже расспрашиваешь Lyssieth о её семье.":"Расспросить Lyssieth о её дочерях, сестрах и матери.", conversationIndex==2?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 2;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_FAMILY"));
						Main.game.getTextEndStringBuilder().append(AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.ELDER_LILIN, null, false));
						Main.game.getTextEndStringBuilder().append(AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false));
					}
				};
				
			} else if(index==3) {
				return new Response("Архангелы", conversationIndex==3?"Ты уже расспрашиваешь Lyssieth об архангелах.":"Расспросить Lyssieth об архангелах.", conversationIndex==3?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 3;
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.statueTruthRevealed, true);
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_ANGELS"));
					}
				};
				
			} else if(index==4) {
				return new Response("Люди", conversationIndex==4?"Вы уже спрашиваете Lyssieth о том, почему она так любит людей.":"Спросить Lyssieth, почему она так любит людей.", conversationIndex==4?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 4;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_HUMANS"));
					}
				};
				
			} else if(index==5) {
				return new Response("Повтор", conversationIndex==5?"Вы просите Lyssieth вкратце пересказать события, из-за которых ваш мир превратился в эту новую реальность. "
						:"Попросить Lyssieth вкратце пересказать события, из-за которых ваш мир превратился в эту новую реальность.", conversationIndex==5?null:LYSSIETH_OFFICE_TALK) {
					@Override
					public void effects() {
						conversationIndex = 5;
						updateLyssiethPregnancyReactions();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_TALK_RECAP"));
					}
				};

			} else if(index==6) {
				return new Response("Доминирующий секс", "Сказать Lyssieth, что вы собираетесь доминантно трахнуть её.", SEX_REPEAT_CHOICES) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_SEX_AS_DOM;
					}
					@Override
					public void effects() {
						dominantRepeatSex = true;
						conversationIndex = 0;
						updateLyssiethPregnancyReactions();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_REPEAT_SEX_DOM"));
					}
				};
				
			} else if(index==7) {
				return new Response("Субмиссивный секс", "Сказать Lyssieth, что вы хотите подчиниться ей и чтобы она доминантно трахнула вас.", SEX_REPEAT_CHOICES) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_SEX;
					}
					@Override
					public void effects() {
						dominantRepeatSex = false;
						conversationIndex = 0;
						updateLyssiethPregnancyReactions();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LYSSIETH_OFFICE_REPEAT_SEX_SUB"));
					}
				};
				
			} else if(index==8 && Main.game.getPlayer().getSubspeciesOverrideRace()!=Race.DEMON) {
				if(!Main.game.getPlayer().hasStatusEffect(StatusEffect.CORRUPTION_PERK_5)) {
					return new Response("Стать демоном", "Вашей Порочности недостаточно, чтобы превратиться в демона...<br/>[style.italicsBad(Требуется Порочность не менее 95.)]", null);
					
				} else if(Main.game.getPlayer().getLegConfiguration()!=LegConfiguration.BIPEDAL) {
					return new Response("Стать демоном", "Чтобы Lyssieth смогла превратить тебя в демона, у тебя должна быть двуногая конфигурация ног...", null);
					
				} else {
					return new Response("Стать демоном", "Сказать Lyssieth, что ты хочешь, чтобы она превратила тебя в демона.", DEMON_TF) {
						@Override
						public void effects() {
							conversationIndex = 0;
							updateLyssiethPregnancyReactions();
						}
					};
				}
				
			} else if(index==11) { // Teleport
				if(Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth") && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
					return new Response("Лаборатория Lilaya", "Lyssieth не желает встречаться со своей дочерью, пока ты не сообщишь ей, что теперь ты — полноценный демон. Тебе придется добираться до лаборатории самостоятельно...", null);
				}

				if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaPregnancyResults) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.waitingOnLilayaBirthNews))
						&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()) {
					return new Response("Лаборатория Lilaya", "Lyssieth не хочет телепортировать тебя в лабораторию её дочери, пока ситуация с беременностью Lilaya не разрешится...", null);
				}
				
				return new Response("Лаборатория Lilaya", "Попросить Lyssieth телепортировать тебя обратно в лабораторию Lilaya.", LAB_TELEPORT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
					}
				};
			}
			if(index==0) {
				return new Response("Уйти", "Выйти из кабинета Lyssieth.", SIREN_OFFICE_LEAVE) {
						@Override
						public void effects() {
							conversationIndex = 0;
							updateLyssiethPregnancyReactions();
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LYSSIETH_OFFICE_TALK = new DialogueNode("", "", true) {

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}

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
			return LYSSIETH_OFFICE_ENTER.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode LAB_TELEPORT = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LAB_TELEPORT_COMPANION", Main.game.getPlayer().getMainCompanion());
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LAB_TELEPORT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Lab.LAB_ENTRY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE) {
				if(Main.game.getPlayer().hasCompanions()) {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE_COMPANION", Main.game.getPlayer().getMainCompanion());
				} else {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE");
				}
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SIREN_OFFICE_LEAVE_MERAXIS_ABSENT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Выйти из кабинета [siren.namePos].", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode SEX_REPEAT_CHOICES = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			GameCharacter domCharacter = dominantRepeatSex?Main.game.getPlayer():Main.game.getNpc(Lyssieth.class);
			GameCharacter subCharacter = dominantRepeatSex?Main.game.getNpc(Lyssieth.class):Main.game.getPlayer();
			
			if(index==1) {
				return new ResponseSex("Использовать киску",
						"Сказать Lyssieth, что ты хочешь использовать её киску.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_PUSSY_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.NONE);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Вырастить член",
						"Сказать Lyssieth, что хочешь, чтобы у неё вырос член.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasVagina()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
										
									} else if(Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_COCK_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).growCock(PenisType.HUMAN);
					}
				};

			} else if(index==3 && Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
				return new ResponseSex("Использовать киску (лилин)",
						"Сказать Lyssieth, что хочешь, чтобы она приняла облик лилин, и чтобы ты мог использовать её киску.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_LILIN_FORM")
							+ UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_PUSSY_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.NONE);
					}
				};
				
			} else if(index==4 && Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
				return new ResponseSex("Вырастить член (лилин)",
						"Сказать Lyssieth, что хочешь, чтобы она приняла облик лилин и чтобы у неё вырос член.",
						true,
						true,
						new SMLyssiethSex(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(domCharacter, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(subCharacter, SexSlotStanding.STANDING_SUBMISSIVE))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasVagina()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
										
									} else if(Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								return character.getMainSexPreference(targetedCharacter);
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_LILIN_FORM")
							+ UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "SEX_COCK_"+(dominantRepeatSex?"DOM":"SUB"))) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))>=Main.game.getNpc(Lyssieth.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_SEX_NOT_SATISFIED");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Выйти из кабинета Lyssieth.", SIREN_OFFICE_LEAVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Настоять", "Воспользоваться нерешительностью Lyssieth, чтобы убедить её сделать то, о чем ты просишь.", DEMON_TF_CONTINUE);
				
			} else if(index==2){
				return new Response("Сдаться", "Не давить дальше и сказать Lyssieth, что ты не хочешь заставлять её делать то, чего она не хочет.", DEMON_TF_CHANGE_MIND) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_RELENT"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_CONTINUE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_CONTINUE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уверенно",
						"Сказать Lyssieth, что ты твердо решил <b>навсегда</b> стать демоном, и понимаешь, что для этого ей придется трахнуть тебя...<br/>[style.italicsSex(Lyssieth отрастит член и будет использовать его во время секса...)]",
						DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.RACE_DEMON;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true);
						// Lyssieth strips and transforms to full lilin
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).setLilinBody();
						for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
							c.setSealed(false);
						}
						Main.game.getPlayer().setAllAreasKnownByCharacter(Main.game.getNpc(Lyssieth.class), true);
						Main.game.getNpc(Lyssieth.class).setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} else if(index==2) {
				return new Response("Уверенно (только киска)",
						"Сказать Lyssieth, что ты твердо решил <b>навсегда</b> стать демоном, и понимаешь, что для этого ей придется трахнуть тебя...<br/>[style.italicsSex(Сказать Lyssieth, чтобы она не отращивала член для этого...)]",
						DEMON_TF_START) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.RACE_DEMON;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true);
						// Lyssieth strips and transforms to full lilin
						((Lyssieth) Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.NONE);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lyssiethNoCockDemonTF, true);
						
						for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
							c.setSealed(false);
						}
						Main.game.getPlayer().setAllAreasKnownByCharacter(Main.game.getNpc(Lyssieth.class), true);
						Main.game.getNpc(Lyssieth.class).setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} else if(index==3){
				return new Response("Передумать", "Сказать Lyssieth, что ты передумал и не хочешь, чтобы она превращала тебя в демона.", DEMON_TF_CHANGE_MIND) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_CONTINUE_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DEMON_TF_CHANGE_MIND = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LYSSIETH_OFFICE_TALK.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DEMON_TF_START = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Подчиниться",
						(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethNoCockDemonTF)
								?"Сделать так, как говорит Lyssieth, и опуститься на колени, чтобы начать посасывать ей киску."
								:"Сделать так, как говорит Lyssieth: сначала ласкать её член, пока он не встанет во весь рост, а затем опуститься на колени и отсосать ей.")
							+ "<br/>[style.italicsFeminine(Когда Lyssieth кончит, она сделает тебя более женственным!)]",
						true,
						false,
						new SMLyssiethDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lyssiethNoCockDemonTF)) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Доминировать",
						"Отказаться выполнять приказы Lyssieth и заставить её саму отсосать тебе.<br/>"
						+ (!Main.game.getPlayer().isFeminine()
								?"[style.italicsMasculine(Когда Lyssieth кончит, ты станешь еще более мужественным!)]"
								:"[style.italicsDisabled(Это не даст дополнительного эффекта феминизации.)]")
						+(!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina()
								?(Main.game.getPlayer().isFeminine()
										?"<br/>[style.italicsTfSexual(Lyssieth подарит тебе демоническую вагину.)]"
										:"<br/>[style.italicsTfSexual(Lyssieth подарит тебе демонический член.)]")
								:""),
						true,
						true,
						new SMLyssiethDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DEMON_TF_SEX,
						"") {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(Main.game.getPlayer().hasPenis() || (!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina() && !Main.game.getPlayer().isFeminine())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class), PenisMouth.BLOWJOB_START, false, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lyssieth.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT"));
						if(!Main.game.getPlayer().hasPenis() && !Main.game.getPlayer().hasVagina()) {
							if(Main.game.getPlayer().isFeminine()) {
								SALyssiethSpecials.playerGrowDemonicVagina();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_GROW_VAGINA"));
							} else {
								SALyssiethSpecials.playerGrowDemonicPenis();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_GROW_PENIS"));
							}
						} else if(Main.game.getPlayer().hasPenis()) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_SERVICE_PENIS"));
							
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "DEMON_TF_START_SEX_DOMINANT_SERVICE_VAGINA"));
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX = new DialogueNode("Завершено", "Благодаря Lyssieth, теперь ты — демон!", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lyssieth.class).setAffection(Main.game.getPlayer(), 75));
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Успокоить", "Похоже, Lyssieth немного беспокоится о том, как её дочери-полудемоны отреагируют на твой вид. Успокой её, сказав, что всё будет в порядке.", AFTER_DEMON_TF_SEX_SURNAMES) {
					@Override
					public void effects() {
						// Resolve Lilaya pregnancy so that her reactions aren't too complicated:
						if(Main.game.getNpc(Lilaya.class).isPregnant()) {
							Main.game.getNpc(Lilaya.class).endPregnancy(true);
						}
						// Lyssieth back to human
						Main.game.getNpc(Lyssieth.class).setStartingBody(false);
						Main.game.getNpc(Lyssieth.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX_SURNAMES = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_SURNAMES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Взять фамилию", "Сказать Lyssieth, что ты возьмешь её имя и будешь известен как '[pc.name] Lyssiethmartuilani'.", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						Main.game.getPlayer().setSurname("Lyssiethmartuilani");
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_TAKE_SURNAME"));
					}
				};
				
			} else if(index==2) {
				return new Response("Оставить фамилию", "Сказать Lyssieth, что ты оставишь свое нынешнее имя.", AFTER_DEMON_TF_SEX_SURNAME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SEX_DO_NOT_TAKE_SURNAME"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_DEMON_TF_SEX_SURNAME = new DialogueNode("", "", true, true) {

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
				return new Response("Выход",
						"Выйти из кабинета Lyssieth в прилегающий кабинет-приемную.",
						AFTER_DEMON_TF_SIREN_OFFICE_LEAVE) {
					@Override
					public void effects() {
						Main.game.getNpc(Lyssieth.class).cleanAllDirtySlots(true);
						Main.game.getNpc(Lyssieth.class).cleanAllClothing(true, false);
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DEMON_TF_SIREN_OFFICE_LEAVE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE) {
				if(Main.game.getPlayer().hasCompanions()) {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE_COMPANION", Main.game.getPlayer().getMainCompanion());
				} else {
					return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE");
				}
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_DEMON_TF_SIREN_OFFICE_LEAVE_MERAXIS_ABSENT");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти", "Отправиться в коридоры дворца Lyssieth.", PlaceType.LYSSIETH_PALACE_CORRIDOR.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_CORRIDOR, false);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Мераксис",
						Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE
							?"Lilaya велит твоей другой сестре войти в кабинет."
							:"Ждать, пока Lyssieth телепортируется обратно в кабинет вместе с Meraxis.",
						LILAYA_DEMON_TF_MERAXIS_CHOICE) {
						@Override
						public void effects() {
							Main.game.getNpc(DarkSiren.class).setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_MERAXIS_CHOICE = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_CHOICE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Трансформировать обеих", "Убедить Meraxis решиться на полную трансформацию в демона.", LILAYA_DEMON_TF_SEX_CHOICE) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_CHOICE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.TWO_NEUTRAL, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
					}
				};
				
			} else if(index==2) {
				return new Response("Только Lilaya",
						"Сказать Meraxis, что ей не обязательно трансформироваться, если ей некомфортно.<br>[style.italics(Позже ты сможешь передумать и убедить Meraxis превратиться в полноценного демона.)]",
						LILAYA_DEMON_TF_SEX_CHOICE) {
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setLilinBody();
						Main.game.getNpc(DarkSiren.class).returnToHome();
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_SEX_CHOICE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.TWO_NEUTRAL, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).addFetish(Fetish.FETISH_INCEST, true));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75));
					}
				};
			}
			return null;
		}
	};
	
	private static boolean isMeraxisBeingTransformed() {
		return Main.game.getNpc(DarkSiren.class).getLocationPlace().getPlaceType().equals(PlaceType.LYSSIETH_PALACE_OFFICE);
	}
	
	private static void returnCompanionsToLab() {
		if(Main.game.getPlayer().hasCompanions()) {
			for(GameCharacter companion : Main.game.getPlayer().getCompanions()) {
				companion.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
			}
		}
	}
	
	public static final DialogueNode LILAYA_DEMON_TF_SEX_CHOICE = new DialogueNode("", "", true, true) {
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
				return new Response("Готова", "Только что поддавшись порочной любви к инцесту, Lilaya готова заняться сексом с матерью и с тобой, чтобы превратиться в демона.", LILAYA_DEMON_TF_ORIFICE_SELECTION);
				
			} else if(index==2) {
				return new Response("Выйти наружу",
						isMeraxisBeingTransformed()
							?"Вы не хотите заниматься сексом со своей матерью и сестрами. Выйдите из кабинета Meraxis и подождите, пока они втроем закончат."
							:"Ты не хочешь трахаться со своей матерью и сестрой. Выйди в кабинет Мераксиса и подожди, пока они закончат.",
						LILAYA_DEMON_TF_WAIT_IN_OFFICE){
					@Override
					public void effects() {
						((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(Lilaya.class);
						Main.game.getNpc(Lilaya.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						if(isMeraxisBeingTransformed()) {
							((Lyssieth)Main.game.getNpc(Lyssieth.class)).setDaughterToFullDemon(DarkSiren.class);
							Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							if(Main.game.isAnalContentEnabled()) {
								Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							} else {
								Main.game.getNpc(DarkSiren.class).calculateGenericSexEffects(false, true, Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							}
						}
						Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
						returnCompanionsToLab();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DEMON_TF_ORIFICE_SELECTION = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_ORIFICE_SELECTION"); // Meraxis gets embarrassed and wants to only watch
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_ORIFICE_SELECTION");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Трахать киску", "Поскольку ты не можешь дотянуться до своего члена, тебе не удается трахать киску Lilaya...", null);
				}
				return new ResponseSex("Трахать киску",
						"Скажи сестре, что именно ты будешь трахать её киску, пока твоя мать будет трахать её сзади в задницу."
						+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
								?"<br/>[style.italicsTfSex(У тебя вырастет демонический член, которым ты будешь её трахать.)]"
								:""),
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FUCK_PUSSY")) {
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getPlayer().setPenisSize(20);
							Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.isAnalContentEnabled()) {
					return null;
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Трахать задницу", "Поскольку ты не можешь дотянуться до своего члена, тебе не удается трахать зад Lilaya...", null);
				}
				return new ResponseSex("Трахать задницу",
						"Скажи сестре, что именно ты будешь трахать её сзади в задницу, пока твоя мать будет трахать её киску."
						+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
								?"<br/>[style.italicsTfSex(У тебя вырастет демонический член, которым ты будешь её трахать.)]"
								:""),
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FUCK_ASS")) {
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
							Main.game.getPlayer().setPenisSize(20);
							Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==6) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Куннилингус", "Поскольку твой рот занят, ты не можешь лизать киску Lilaya...", null);
				}
				return new ResponseSex("Куннилингус",
						"Скажи сестре, что хочешь лизать её киску, пока мать трахает её сзади в задницу.",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
				
			} else if(index==7) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Отсосать", "Поскольку твой рот занят, ты не можешь сосать член Lilaya...", null);
				}
				return new ResponseSex("Отсосать",
						"Скажи сестре, что хочешь, чтобы она сосала твой член, пока мать трахает её киску сзади.<br/>[style.italicsTfSex(Lilaya отрастит для тебя демонический член, чтобы ты мог его сосать.)]",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_BLOWJOB")) {
					@Override
					public void effects() {
						if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
							Main.game.getNpc(Lilaya.class).setPenisSize(20);
							Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
						}
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==8 && Main.game.isAnalContentEnabled()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Сделать анилингус", "Поскольку твой рот занят, ты не можешь лизать зад Lilaya...", null);
				}
				return new ResponseSex("Сделать анилингус",
						"Скажи сестре, что хочешь лизать её задницу, пока мать трахает её киску.",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_GIVE_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), TongueAnus.ANILINGUS_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
					}
				};
				
			} else if(index==11
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
					&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				return new ResponseSex("Использовать [pc.fingers]",
						"Так как твой член и рот заняты, тебе придётся довольствоваться лишь пальцами в киске сестры, пока мать трахает её в задницу.",
						true,
						false,
						new SMLilayaDemonTF(
								SexPosition.STANDING,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE),
										new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotStanding.STANDING_SUBMISSIVE_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Lilaya.class), SexSlotStanding.STANDING_DOMINANT))),
						null,
						isMeraxisBeingTransformed()
							?Util.newArrayListOfValues(Main.game.getNpc(DarkSiren.class))
							:null,
						AFTER_LILAYA_DEMON_TF_SEX,
						UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SEX_START_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Lilaya.class), FingerVagina.FINGERING_START, false, true),
								new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(Lilaya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void postSexInitEffects() {
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
						Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
						Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER));
						
						Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_LILAYA_DEMON_TF_SEX = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SOLO_SEX");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMeraxisBeingTransformed()) {
				if(index==1) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Трахать киску",
								Main.game.getNpc(DarkSiren.class).isVaginaVirgin()
									?"Поскольку у тебя нет доступа к своему члену, ты не можешь лишить Meraxis девственности..."
									:"Так как ты не можешь дотянуться до своего члена, ты не в состоянии трахнуть киску Мераксис...",
								null);
					}
					return new ResponseSex("Трахать киску",
							(Main.game.getNpc(DarkSiren.class).isVaginaVirgin()
								?"Скажи сестре, что именно ты лишишь её девственности, пока мать будет трахать её в задницу, а Lilaya будет использовать свой рот."
								:"Скажи сестре, что именно ты будешь трахать её киску, пока мать будет трахать её в задницу, а Lilaya будет использовать свой рот.")
							+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
									?"<br/>[style.italicsTfSex(У тебя вырастет демонический член, которым ты будешь её трахать.)]"
									:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.BESIDE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_FUCK_PUSSY")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							}
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.NONE);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isAnalContentEnabled()) {
						return null;
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Трахать задницу", "Так как ты не можешь дотянуться до своего члена, ты не в состоянии трахнуть задницу Мераксис...", null);
					}
					return new ResponseSex("Трахать задницу",
							(Main.game.getNpc(DarkSiren.class).isAnalVirgin()
								?"Скажи сестре, что именно ты лишишь её анальной девственности, пока мать будет трахать её киску, а Lilaya будет использовать свой рот."
								:"Скажи сестре, что именно ты будешь трахать её в задницу, пока мать будет трахать её киску, а Lilaya будет использовать свой рот.")
							+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
									?"<br/>[style.italicsTfSex(У тебя вырастет демонический член, которым ты будешь её трахать.)]"
									:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.BESIDE)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_FUCK_ASS")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							}
							Main.game.getNpc(Lilaya.class).setPenisType(PenisType.NONE);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getNpc(Lilaya.class), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
						}
					};
					
				} else if(index==3) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Принять куннилингус", "Так как ты не можешь дотянуться до своей киски, ты не можешь заставить Мераксис отсосать у тебя...", null);
					}
					return new ResponseSex("Принять куннилингус",
							"Скажи сестре, что ты собираешься использовать её рот, пока мать будет трахать её киску, а Lilaya будет долбить её в задницу."
									+ (!Main.game.getPlayer().hasVagina()
											?"<br/>[style.italicsTfSex(У тебя вырастет демоническая киска, чтобы она могла её лизать.)]"
											:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_RECEIVE_ORAL_CUNNILINGUS")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasVagina()) {
								Main.game.getPlayer().setVaginaType(VaginaType.DEMON_COMMON);
							}
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(DarkSiren.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
					
				} else if(index==4) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Получить минет", "Так как ты не можешь дотянуться до своего члена, ты не можешь заставить Мераксис отсосать тебе...", null);
					}
					return new ResponseSex("Получить минет",
							"Скажи сестре, что ты собираешься использовать её рот, пока мать будет трахать её киску, а Lilaya будет долбить её в задницу."
									+ (!Main.game.getPlayer().hasPenisIgnoreDildo()
											?"<br/>[style.italicsTfSex(У тебя вырастет демонический член, которым ты будешь её трахать.)]"
											:""),
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_RECEIVE_ORAL_BLOWJOB")) {
						@Override
						public void effects() {
							if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
								Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
								Main.game.getPlayer().setPenisSize(20);
								Main.game.getPlayer().setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
							}
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(DarkSiren.class), PenisMouth.BLOWJOB_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
					
				}  else if(index==5
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new ResponseSex("Встать на колени перед ней",
							"Поскольку добраться до своих гениталий не получается, тебе остается только опуститься на колени перед Meraxis и найти другой способ развлечься, пока Lilaya и Lyssieth трахают её.",
							true,
							false,
							new SMLilayaDemonTF(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.BESIDE),
											new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN),
											new Value<>(Main.game.getNpc(Lilaya.class), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(DarkSiren.class), SexSlotLyingDown.COWGIRL))),
							null,
							null,
							AFTER_MERAXIS_DEMON_TF_SEX,
							UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_MERAXIS_KNEELING_NO_ACCESS")) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Lilaya.class).hasPenisIgnoreDildo()) {
								Main.game.getNpc(Lilaya.class).setPenisType(PenisType.DEMON_COMMON);
								Main.game.getNpc(Lilaya.class).setPenisSize(20);
								Main.game.getNpc(Lilaya.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Lilaya.class).setPenisCumStorage(100);
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Lilaya.class), Main.game.getNpc(DarkSiren.class), PenisAnus.PENIS_FUCKING_START, false, true),
									new InitialSexActionInformation(Main.game.getNpc(Lyssieth.class), Main.game.getNpc(DarkSiren.class), PenisVagina.PENIS_FUCKING_START, false, true));
						}
						@Override
						public void postSexInitEffects() {
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setForeplayPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							Main.game.getNpc(DarkSiren.class).setMainSexPreference(Main.game.getNpc(Lilaya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
							
							Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							Main.game.getNpc(Lyssieth.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
							
							Main.game.getNpc(Lilaya.class).setForeplayPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
							Main.game.getNpc(Lilaya.class).setMainSexPreference(Main.game.getNpc(DarkSiren.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						}
					};
				} 
				
			} else {
				if(index==1) {
					return new Response("Lyssieth", "Посмотреть на Lyssieth и увидеть её реакцию.", LILAYA_DEMON_TF_FINISHED_REACTION) {
							@Override
							public void effects() {
								Main.game.getNpc(Lyssieth.class).setStartingBody(false);
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SOLO_SEX_FINISHED_REACTION"));
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_MERAXIS_DEMON_TF_SEX = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_REPEAT_SEX");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lyssieth",
						"Посмотреть на Lyssieth и увидеть её реакцию.",
						LILAYA_DEMON_TF_FINISHED_REACTION) {
						@Override
						public void effects() {
							Main.game.getNpc(Lyssieth.class).setStartingBody(false);
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_MERAXIS_DEMON_TF_REPEAT_SEX_FINISHED_REACTION"));
								if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("innoxia_meraxis_demon_tf_teleported"))) {
									Main.game.getNpc(DarkSiren.class).returnToHome();
								}
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "AFTER_LILAYA_DEMON_TF_SEX_FINISHED_REACTION"));
							}
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode LILAYA_DEMON_TF_WAIT_IN_OFFICE = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}

		@Override
		public String getContent() {
			if(isMeraxisBeingTransformed()) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_WAIT_IN_OFFICE");
			} else {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_WAIT_IN_OFFICE");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						isMeraxisBeingTransformed()
							?"Демонические сестры"
							:"Демоническая Lilaya",
						isMeraxisBeingTransformed()
							?"Вернуться в кабинет Lyssieth и посмотреть, как изменились Lilaya и Meraxis."
							:"Вернуться в кабинет Lyssieth и посмотреть, как изменилась Lilaya.",
						LILAYA_DEMON_TF_FINISHED_REACTION) {
						@Override
						public void effects() {
							if(isMeraxisBeingTransformed()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_FINISHED_REACTION_OFFICE_END"));
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_SOLO_FINISHED_REACTION_OFFICE_END"));
							}
							Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE);
							Main.game.getNpc(DarkSiren.class).returnToHome();
							returnCompanionsToLab();
						}
					};
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_FINISHED_REACTION = new DialogueNode("", "", true, true) {
		
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
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("innoxia_meraxis_demon_tf_teleported"))) {
					return new Response("Телепортирован(а)",
							"Вы с Мераксис телепортируетесь обратно в таверну «Красный дракон» в Элисе.",
							DialogueManager.getDialogueFromId("innoxia_places_fields_elis_tavern_f0_meraxis_post_demon_tf")) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							}
						};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
					return new Response(
							Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE
								?"Кабинет Мераксиса"
								:"Продолжить",
							Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()==WorldType.LYSSIETH_PALACE
								?"Вы с Мераксисом возвращаетесь в кабинет, совмещенный с залом ожидания."
								:"Вы снова оказываетесь в кабинете, совмещенном с залом ожидания.",
							LILAYA_DEMON_TF_END) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
								Main.game.getNpc(DarkSiren.class).returnToHome();
								Main.game.getPlayer().setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE);
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
							}
						};
						
				} else {
					return new Response("Лаборатория Lilaya", "Вы с Lilaya возвращаетесь в её лабораторию.", LILAYA_DEMON_TF_END) {
							@Override
							public void effects() {
								// Reset offspring to full demons:
								if(Main.game.getNpc(DarkSiren.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(DarkSiren.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(DarkSiren.class), Main.game.getNpc(DarkSiren.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(DarkSiren.class).getPregnantLitter().generateBirthedDescription();
								}
	
								// Reset offspring to full demons:
								if(Main.game.getNpc(Lilaya.class).isPregnant()) {
									for(OffspringSeed offspring : Main.game.getNpc(Lilaya.class).getPregnantLitter().getOffspringSeed()) {
										offspring.setBody(offspring.getGender(), Main.game.getNpc(Lilaya.class), Main.game.getNpc(Lilaya.class).getPregnantLitter().getFather());
									}
									Main.game.getNpc(Lilaya.class).getPregnantLitter().generateBirthedDescription();
								}
								
								Main.game.getNpc(DarkSiren.class).returnToHome();
								Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB);
								Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode LILAYA_DEMON_TF_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lyssieth.class).setStartingBody(false);
		}
		@Override
		public boolean isTravelDisabled() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF);
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "MERAXIS_DEMON_TF_END");
			}
			return UtilText.parseFromXMLFile("places/submission/lyssiethsPalace", "LILAYA_DEMON_TF_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.meraxisRepeatDemonTF)) {
				return SIREN_OFFICE.getResponse(responseTab, index);
			}
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
}
