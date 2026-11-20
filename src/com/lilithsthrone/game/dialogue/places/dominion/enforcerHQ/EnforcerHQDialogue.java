package com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.9.4
 * @author Innoxia
 */
public class EnforcerHQDialogue {
	
	public static void obtainBraxAsSlave() {
		Main.game.getPlayer().addSlave(Main.game.getNpc(Brax.class));
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bimbofiedBrax)) {
			Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false);
			Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false);
			Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
			
			Main.game.getNpc(Brax.class).setObedience(50);
		} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feminisedBrax)) {
			Main.game.getNpc(Brax.class).setObedience(-20);
		} else {
			Main.game.getNpc(Brax.class).setObedience(-80);
		}
		
		AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
		jacket.setSticker("collar", "tab_ip");
		jacket.setSticker("name", "name_brax");
		jacket.setSticker("ribbon", "ribbon_brax");
		Main.game.getNpc(Brax.class).addClothing(jacket, false);
		
		Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false);

		AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
		hat.setSticker("badge", "badge_dominion");
		Main.game.getNpc(Brax.class).addClothing(hat, false);
		
		Main.game.getNpc(Brax.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
	}

	public static final DialogueNode EXTERIOR = new DialogueNode("Штаб Enforcer", "Штаб Enforcer", false) {
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Войти", "Пересеките территорию и войдите в штаб Enforcer.", PlaceType.ENFORCER_HQ_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_ENTRANCE, false);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
					if(Main.game.getHourOfDay()<16 || Main.game.getHourOfDay()>=18) {
						return new Response("Элль",
								"Предать Уэса: дождаться, когда Элль закончит работу, и рассказать ей всё...<br/>Вы можете сделать это только в то время, когда Элль должна уходить с работы, а именно [style.italicsMinorBad([units.time(16)]-[units.time(18)])].",
								null);
					}
					
					return new Response("Элль", "Предать Уэса: дождаться, когда Элль закончит работу, и рассказать ей всё...", WesQuest.APPROACH_ELLE);
				}
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
					return new Response("Войти (Снабжение)", "Пересеките территорию и войдите в штаб Enforcer через вход для Enforcer.", PlaceType.ENFORCER_HQ_ENFORCER_ENTRANCE.getDialogue(false)){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_ENFORCER_ENTRANCE, false);
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
			return 30;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR"));
			try {
				if(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()+1).getPlace().getPlaceType()==PlaceType.ENFORCER_HQ_BRAXS_OFFICE) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR_BRAX_WARNING"));
				}
			} catch(Exception ex) {
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode CORRIDOR_PLAIN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			try {
				if(Vector2i.getDistance(Main.game.getPlayer().getLocation(), Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_ENFORCER_ENTRANCE).getLocation())<=2) {
					return  UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR_PLAIN_ENFORCER_ENTRANCE");
				}
			} catch(Exception ex) {
			}
			
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CORRIDOR_PLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Выход", "Покинуть штаб Enforcer.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode WAITING_AREA = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "WAITING_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "STAIRCASE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CELLS_OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CELLS_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode CELL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "CELL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode GUARDED_DOOR = new DialogueNode("Охраняемая дверь", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public boolean isTravelDisabled() {
			return (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.accessToEnforcerHQ) || Main.game.isBraxMainQuestComplete())
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "GUARDED_DOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1 && isTravelDisabled()) {
				return new Response("Отступить", "У вас особо нет выбора, кроме как делать то, что говорит Enforcer.", PlaceType.ENFORCER_HQ_WAITING_AREA.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "GUARDED_DOOR_STEP_BACK"));
						
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LOCKED_DOOR = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отступить", "Вам остается только отступить от запертой двери...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR_STEP_BACK"));
						
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CORRIDOR, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode REQUISITIONS_DOOR = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "REQUISITIONS_DOOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && isTravelDisabled()) {
				return new Response("Отступить", "Вам остается только отступить от запертой двери...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "LOCKED_DOOR_STEP_BACK"));
						
						Main.game.getPlayer().setNearestLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode RECEPTION_DESK = new DialogueNode("Стойка регистрации", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK"));
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_WES) {
				long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
				UtilText.addSpecialParsingString(Util.intToString((int) days)+" day"+(days==1?"":"s"), true);
				sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", "RECEPTION_DESK_WES"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_ELLE) {
				long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
				UtilText.addSpecialParsingString(Util.intToString((int) days)+" day"+(days==1?"":"s"), true);
				sb.append(UtilText.parseFromXMLFile("characters/dominion/wes", "RECEPTION_DESK_ELLE"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isWorkTime()) {
				if(index==0) {
					return new Response("Отступить",
							"Так как здесь не с кем поговорить, вам остается только отойти от стола.",
							WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA, false);
						}
					};
					
				} else if(index==5) {
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
						return new Response("Анонимная наводка", "Тебе придется вернуться, пока Кэнди на работе, если ты хочешь оставить здесь арканный регистратор.", null);
					}
				}
				return null;
			}
			
			if(index==0) {
				return new Response("Отступить",
						"Сказать Кэнди, что ты вернешься позже, и отойти от ее стола, позволяя ей продолжить наносить макияж.",
						WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_LEAVE"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA, false);
						if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
							Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().equals(Main.game.getNpc(CandiReceptionist.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
						}
					}
				};
			}
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_BUYING_BRAX)) {
				if(index==1) {
					if(Main.game.getSecondsPassed()-Main.game.getDialogueFlags().getSavedLong(CandiReceptionist.CANDI_SEX_TIMER_ID)>60*60*12) {
						return new ResponseSex("Помочь Кэнди",
								"Согласиться помочь Кэнди справиться с ее нестерпимым желанием.",
								null, null, null, null, null, null,
								true,
								true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(CandiReceptionist.class), SexSlotStanding.STANDING_SUBMISSIVE))),
								null,
								null,
								AFTER_SEX_CANDI,
								UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "START_SEX_CANDI")) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(CandiReceptionist.class).isCharacterReactedToPregnancy(Main.game.getPlayer()) && Main.game.getNpc(CandiReceptionist.class).isVisiblyPregnant()) {
									Main.game.getNpc(CandiReceptionist.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
							}
						};
						
					} else {
						return new Response("Помочь Кэнди",
								"Недавно ты помог Кэнди справиться с ее нестерпимым желанием, но пройдет всего пара часов, и она снова будет умолять о сексе...",
								null);
					}
				}
				
			} else if(Main.game.isBraxMainQuestComplete()) {
				if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().equals(Main.game.getNpc(CandiReceptionist.class))) {
					if (index == 1) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new ResponseSex("Наказать [brax.name]", "Заняться с [brax.name] доминирующим сексом.",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BRAX")) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
							return new ResponseSex("Наказать [brax.name]", "Заняться с [brax.name] доминирующим сексом.",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BREE")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else {
							return new ResponseSex("Наказать [brax.name]", "Заняться с [brax.name] доминирующим сексом.",
									false, false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISH_BRANDI")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
						}
						
					} else if (index == 2) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new ResponseSex("Подчиниться наказанию от [brax.name]", "Заставить [brax.name] выплеснуть на вас [brax.his]", Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
									null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
									true, false,
									new SMBraxDoggy(
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
									null,
									null,
									AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "RECEPTION_DESK_PUNISHED_BY_BRAX")){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else {
							return new Response("Подчиниться наказанию от [brax.name]", "[brax.Name] слишком покорна, чтобы наказывать вас. Если вы хотите заняться сексом с [brax.him], вам придется взять инициативу в свои руки.", null);
						}
						
					} else if (index == 3) {
						if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
							return new Response("Феминизировать [brax.name]", "Превратить [brax.name] в девочку-волчицу.", INTERIOR_SECRETARY_BRAX_FEMINISE){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
							return new Response("Превратить [brax.name] в бимбо", "Превратить [brax.name] в безмозглую бимбо.", INTERIOR_SECRETARY_BRAX_BIMBOFY){
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
						}
						
					} else if (index == 4) {
						if(!Main.game.getPlayer().isHasSlaverLicense()) {
							return new Response("Купить [brax.name]", "Так как у вас нет лицензии работорговца, вы не можете владеть рабами, поэтому не смогли бы купить [brax.name], даже если бы Кэнди была готова продать [brax.herHim].", null);
						}
						if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)) {
							return new Response("Купить [brax.name]", "Спросить у Кэнди, готова ли она продать тебе [brax.name].", BUYING_BRAX_INITIAL){
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_BUYING_BRAX));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(500));
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seenBraxAfterQuest, true);
								}
							};
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_START
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_PERFUME) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_PERFUMES)) {
								return new Response("Доставить духи", "Отдать Кэнди флаконы с духами, которые ты собрал у Кейт.", BUYING_BRAX_PERFUME_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_PERFUMES);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_PERFUMES));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_LOLLIPOPS));
									}
								};
								
							} else {
								return new Response("Доставить духи", "Прежде чем ты сможешь доставить духи ей, тебе нужно забрать их в лавке «Секреты суккуба» в Торговых рядах!", null);
							}
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LOLLIPOPS
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_LOLLIPOPS) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_CONTRABAND)) {
								return new Response("Доставить леденцы", "Отдать Кэнди коробку с контрабандными леденцами, которую ты раздобыл на контрольно-пропускном пункте Enforcer в Гнездах гарпий.", BUYING_BRAX_LOLLIPOP_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_CONTRABAND);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_CONTRABAND));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_LIPSTICK));
									}
								};
								
							} else {
								return new Response("Доставить леденцы", "Прежде чем ты сможешь доставить леденцы ей, тебе нужно забрать коробку на контрольно-пропускном пункте Enforcer в Гнездах гарпий!", null);
							}
							
						} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LIPSTICK
								|| Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_DELIVER_LIPSTICK) {
							if(Main.game.getPlayer().hasItemType(ItemType.CANDI_HUNDRED_KISSES)) {
								return new Response("Доставить помаду", "Отдать Кэнди коробку «Ста к поцелуев».", BUYING_BRAX_LIPSTICK_DELIVERY){
									@Override
									public void effects() {
										Main.game.getPlayer().removeItemByType(ItemType.CANDI_HUNDRED_KISSES);
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().removedItemFromInventoryText(ItemType.CANDI_HUNDRED_KISSES));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.SIDE_UTIL_COMPLETE));
										
										obtainBraxAsSlave();
									}
								};
								
							} else {
								return new Response("Доставить помаду", "Прежде чем ты сможешь доставить их ей, тебе нужно забрать коробку «Ста к поцелуев» в лавке «Закуски Ральфа» в Торговой аркаде!", null);
							}
						}
					}
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_C_WOLFS_DEN && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ)) {
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_BIMBO)) {
						return new Response("Поприветствовать Кэнди", "Боже мой, она такая красавица, просто нет слов!", INTERIOR_SECRETARY_BIMBO);
						
					} else {
						return new Response("Поприветствовать Кэнди", "Привлечь её внимание, сказав «привет».", INTERIOR_SECRETARY,
								null, null, null, null, null);
					}
				}
			}
			

			if(index==5) {
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_2) {
					return new Response("Анонимная наводка",
							"Спросить Кэнди, можно ли анонимно передать улики преступной деятельности, чтобы сдать аркана-рекордер с записью того, как Элль расправляется с бандой.",
							WesQuest.CANDI_ELLE_EVIDENCE);
				}
				
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_WES) {
					if(Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID)<60*24*7) {
						long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
						return new Response("Вес",
								"С момента того, как ты анонимно сдал аркана-рекордер, еще не прошла и недели, так что просить о встрече с Уэсом пока рано...<br/>Тебе нужно подождать еще [style.italicsMinorBad(\""+days+" день"+(days==1?"":"s")+")]!",
								null);
						
					} else {
						return new Response("Вес",
								"Прошло уже больше недели с тех пор, как ты анонимно сдал аркана-рекордер, так что ты можешь сказать Кэнди, что пришел к Уэсу.",
								WesQuest.INTRO_HQ_WES);
					}
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_WES)==Quest.WES_3_ELLE) {
					if(Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID)<60*24*7) {
						long days = 7-((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(WesQuest.QUEST_COMPLETION_MINUTES_TIMER_ID))/(60*24));
						return new Response("Элль",
								"С момента того, как ты анонимно сдал аркана-рекордер, еще не прошла и недели, так что просить о встрече с Элль пока рано...<br/>Тебе нужно подождать еще [style.italicsMinorBad(\""+days+" день"+(days==1?"":"s")+")]!",
								null);
						
					} else {
						return new Response("Элль",
								"Прошло уже больше недели с тех пор, как вы анонимно сдали арканный регистратор, так что теперь вы можете сказать Кэнди, что пришли на встречу с Элль.",
								WesQuest.INTRO_HQ_ELLE);
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("[brax.name]", "Сказать ей, что вы пришли к [brax.name].", INTERIOR_SECRETARY_BRAX){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.accessToEnforcerHQ);
					}
				};

			} else if (index == 0) {
				return new Response("Отступить",
						"Сказать Кэнди, что ты вернешься позже, и отойти от ее стола, позволяя ей продолжить наносить макияж.",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_LEAVE"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отступить",
						"Теперь, когда вы получили желаемое, вы можете отойти от стола Кэнди, позволяя этой кукольной надзирательнице и дальше наносить макияж.",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BIMBO = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Типа, к [brax.name] и всё такое", "Сказать ей, что вы пришли к [brax.name].", INTERIOR_SECRETARY_BRAX_BIMBO){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.accessToEnforcerHQ);
					}
				};

			} else if (index == 0) {
				return new Response("Отступить",
						"Сказать Кэнди, что ты вернешься позже, и отойти от ее стола, позволяя ей продолжить наносить макияж.",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BIMBO_LEAVE"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBO = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBO");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отступить",
						"Теперь, когда вы получили желаемое, вы можете отойти от стола Кэнди, позволяя этой кукольной надзирательнице и дальше наносить макияж.",
						WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};
			}
			return null;
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Бри",
						"Вы с Кэнди насильно заставляете [brax.name] выпить его собственный эликсир, превращая его в волчицу по имени Бри.",
						INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feminisedBrax, true);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Бри", "Бри", "Бри"));
						
						Main.game.getNpc(Brax.class).removeFetish(Fetish.FETISH_DOMINANT);
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_SUBMISSIVE);
						
						Main.game.getNpc(Brax.class).setFemininity(75);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.C.getMeasurement());
						
						Main.game.getNpc(Brax.class).setBreastRows(3);
						
						Main.game.getNpc(Brax.class).setHipSize(HipSize.THREE_GIRLY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.FOUR_LARGE.getValue());
						Main.game.getNpc(Brax.class).setPenisType(PenisType.NONE);
						Main.game.getNpc(Brax.class).setVaginaType(VaginaType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.ONE_SLIGHTLY_MOIST.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());
						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_OLIVE), false);
//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						
						Main.game.getNpc(Brax.class).setHeight(175);
						
						Main.game.getNpc(Brax.class).setVaginaVirgin(true);

						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} if (index == 2) {
				return new Response("Бри (фута)",
						"Вы с Кэнди насильно заставляете [brax.name] выпить его собственный эликсир, превращая его в фута-волчицу по имени Бри.",
						INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.feminisedBrax, true);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Бри", "Бри", "Бри"));
						
						Main.game.getNpc(Brax.class).removeFetish(Fetish.FETISH_DOMINANT);
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_SUBMISSIVE);
						
						Main.game.getNpc(Brax.class).setFemininity(75);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.C.getMeasurement());
						
						Main.game.getNpc(Brax.class).setBreastRows(3);
						
						Main.game.getNpc(Brax.class).setHipSize(HipSize.THREE_GIRLY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.FOUR_LARGE.getValue());
						Main.game.getNpc(Brax.class).setVaginaType(VaginaType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.ONE_SLIGHTLY_MOIST.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());
						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_OLIVE), false);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						
						Main.game.getNpc(Brax.class).setHeight(175);
						
						Main.game.getNpc(Brax.class).setVaginaVirgin(true);

						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 0) {
				return new Response("Уйти", "Передумать и оставить [brax.name] в его нынешнем виде.",  WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_CHANGE_MIND"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Секс с [brax.name]", "Заняться сексом с [brax.name].",
						false, false,
						new SMStanding(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_SEX"));
				
			} else if(index==2) {
				return new Response("Отклонить", "Решить не заниматься сексом с Бри.", WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_FEMINISE_COMPLETED_NO_SEX"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Брэнди", "Превратить Бри в безмозглую бимбо по имени Брэнди.", INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimbofiedBrax);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Брэнди", "Брэнди", "Брэнди"));
						
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_BIMBO);
						
						Main.game.getNpc(Brax.class).setFemininity(100);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.KK.getMeasurement());
						Main.game.getNpc(Brax.class).setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
						Main.game.getNpc(Brax.class).setAssWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.SEVEN_GIGANTIC.getValue());
						
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());

						Main.game.getNpc(Brax.class).setHeight(162);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
//						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true);
						
						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 2) {
				return new Response("Брэнди (фута)", "Превратить Бри в безмозглую футанари-бимбо по имени Брэнди.",
						INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
						Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.bimbofiedBrax);
						Main.game.getNpc(Brax.class).setName(new NameTriplet("Брэнди", "Брэнди", "Брэнди"));
						
						Main.game.getNpc(Brax.class).addFetish(Fetish.FETISH_BIMBO);
						
						Main.game.getNpc(Brax.class).setFemininity(100);
						Main.game.getNpc(Brax.class).setBreastSize(CupSize.KK.getMeasurement());
						Main.game.getNpc(Brax.class).setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
						Main.game.getNpc(Brax.class).setAssWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
						Main.game.getNpc(Brax.class).setAssSize(AssSize.SEVEN_GIGANTIC.getValue());

						Main.game.getNpc(Brax.class).setPenisType(PenisType.WOLF_MORPH);
						Main.game.getNpc(Brax.class).setVaginaWetness(Wetness.SIX_SOPPING_WET.getValue());
						Main.game.getNpc(Brax.class).setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());

						Main.game.getNpc(Brax.class).setHeight(162);

//						Main.game.getNpc(Brax.class).setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
//						Main.game.getNpc(Brax.class).setSkinCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true);
						
						Main.game.getNpc(Brax.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_ACCESSORIES));
					}
				};
				
			} else if (index == 0) {
				return new Response("Уйти", "Передумать и оставить Бри такой, какая она есть.", WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_CHANGE_MIND"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED = new DialogueNode("Штаб Enforcer", "", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Секс с Брэнди", "Заняться сексом с Брэнди.",
						true, false,
						new SMStanding(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_SEX"));

			} else if(index==2) {
				return new Response("Отклонить", "Решить не заниматься сексом с Брэнди.",  WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "INTERIOR_SECRETARY_BRAX_BIMBOFY_COMPLETED_NO_SEX"));
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Завершено", "Вернуться к стойке регистрации.", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "AFTER_SEX"));
			sb.append(WAITING_AREA.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return WAITING_AREA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX_CANDI = new DialogueNode("Завершено", "Обойти стойку регистрации и встать с другой стороны.", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "AFTER_SEX_CANDI");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Уйти",
						Main.game.getNpc(CandiReceptionist.class).isSatisfiedFromLastSex()
							?"Оставить Кэнди приводить себя в порядок и вернуться в зону ожидания."
							:"Оставить Кэнди доделывать свое дело и вернуться в зону ожидания.",
						WAITING_AREA) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode BUYING_BRAX_INITIAL = new DialogueNode("Стойка регистрации", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_INITIAL");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_PERFUME_DELIVERY = new DialogueNode("Стойка регистрации", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_PERFUME_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_LOLLIPOP_DELIVERY = new DialogueNode("Стойка регистрации", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_LOLLIPOP_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return RECEPTION_DESK.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BUYING_BRAX_LIPSTICK_DELIVERY = new DialogueNode("Стойка регистрации", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "BUYING_BRAX_LIPSTICK_DELIVERY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getSecondsPassed()-Main.game.getDialogueFlags().getSavedLong(CandiReceptionist.CANDI_SEX_TIMER_ID)>60*60*12) {
					return new ResponseSex("Помочь Кэнди",
							"Согласиться помочь Кэнди справиться с ее нестерпимым желанием.",
							null, null, null, null, null, null,
							true,
							true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(CandiReceptionist.class), SexSlotStanding.STANDING_SUBMISSIVE))),
							null,
							null,
							AFTER_SEX_CANDI,
							UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "START_SEX_CANDI"));
					
				} else {
					return new Response("Помочь Кэнди", "Недавно ты помог Кэнди справиться с ее нестерпимым желанием, но пройдет всего пара часов, и она снова будет умолять о сексе...", null);
				}
				
			} else 
				if(index==2) {
					return new Response("Отказаться", "Отказаться помогать Кэнди с ее нестерпимой похотью, после чего вернуться в зону ожидания.", WAITING_AREA) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "HELP_CANDI_DENIED"));
							Main.game.getPlayer().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_WAITING_AREA);
						}
					};
				}
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_ENFORCER = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "ENTRANCE_ENFORCER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Выход", "Покинуть штаб Enforcer.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode REQUISITIONS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Elle.class))
					&& !Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Wes.class))) {
				return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "REQUISITIONS");
				
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/wes", "REQUISITIONS_POST_QUEST");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Elle.class))
					&& !Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Wes.class))) {
				return null;
				
			} else {
				if(index==1) {
					return new Response("Позвонить в колокольчик",
							Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_WES, Quest.WES_3_ELLE)
								?"Позвонить в колокольчик на столе заявок, чтобы привлечь внимание Элль."
								:"Позвонить в колокольчик на столе заявок, чтобы привлечь внимание Уэса.",
							WesQuest.REQUISITIONS_INTERACTION);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_QUARTERMASTER = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/generic", "OFFICE_QUARTERMASTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
}
