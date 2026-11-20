package com.lilithsthrone.game.dialogue.places.dominion.cityHall;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Vanessa;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaOral;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaOralFootjob;
import com.lilithsthrone.game.sex.managers.dominion.vanessa.SMVanessaSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.2
 * @version 0.3.2
 * @author Innoxia
 */
public class CityHallDemographics {
	
	private static void applyLeavingEffects() {
		Main.game.getNpc(Vanessa.class).applyWash(true, true, null, 0);
		Main.game.getNpc(Vanessa.class).equipClothing();
	}
	
	public static final DialogueNode CITY_HALL_DEMOGRAPHICS_ENTRANCE = new DialogueNode("Бюро демографии", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_ENTRANCE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Позвонить в колокольчик",
						Main.game.getPlayer().hasCompanions()
							?UtilText.parse(Main.game.getPlayer().getMainCompanion(), "Велеть [npc.name] подождать снаружи, затем позвонить в маленький колокольчик на столе [vanessa.namePos] и ждать её появления.")
							:"Позвонить в маленький колокольчик на столе [vanessa.namePos] и ждать её появления.",
						CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_RING_BELL"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaIntroduced, true);
						
						if(Main.game.getNpc(Vanessa.class).isVisiblyPregnant()) {
							Main.game.getNpc(Vanessa.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						
						for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
							character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("Уйти", "Решить не беспокоить [vanessa.name] и выйти обратно в коридор.", CityHall.CITY_HALL_CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						applyLeavingEffects();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "CITY_HALL_DEMOGRAPHICS_ENTRANCE_TURN_BACK"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_HALL_DEMOGRAPHICS_MAIN = new DialogueNode("Бюро демографии", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Смена имени", "Спросить [vanessa.name] о смене имени.", NAME_CHANGE);
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasItemType(ItemType.OFFSPRING_MAP)) {
					return new Response("Карта потомства", "Вы уже купили карту потомства у [vanessa.name], так что новая вам не нужна.", null);
					
				} else {
					return new Response("Карта потомства", "Спросить [vanessa.name] о том, как получить карту с указанием мест проживания ваших отпрысков.", OFFSPRING_MAP);
				}
				
			} else if (index == 3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped)) {
					return new Response("Предложить помощь", "Вы уже помогли [vanessa.name] с её работой сегодня.", null);
				}
				return new Response("Предложить помощь", "Спросить [vanessa.name], не нужна ли ей помощь.", OFFER_HELP) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyHelped, true);
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
						Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
						
						for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
							character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						}
					}
				};
				
			} else if (index == 4 && (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaMassaged))) {
				if(Main.game.isFootContentEnabled()) { // Foot massage:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
						return new Response("Массаж стоп", "Вы уже делали [vanessa.name] массаж стоп сегодня. Она не захочет повторения до завтра.", null);
					}
					
					return new Response("Массаж стоп", "Предложить [vanessa.name] массаж стоп.", FOOT_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);
							
							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else { // Shoulder massage:
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
						return new Response("Массаж плеч", "Вы уже делали [vanessa.name] массаж сегодня. Она не захочет повторения до завтра.", null);
					}
					
					return new Response("Массаж плеч", "Предложить [vanessa.name] массаж плеч.", SHOULDER_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);

							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.TORSO_OVER), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing a cardigan!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else if (index == 5
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyHelped)
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaFucked)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaDailyMassage)) {
				if(Main.game.isFootContentEnabled()) { // Foot massage:
					return new Response("Заставить её умолять", "Заставить [vanessa.name] умолять тебя сделать ей массаж стоп.", FOOT_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);
							
							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_BEG"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else { // Shoulder massage:
					return new Response("Заставить её умолять", "Заставить [vanessa.name] умолять тебя сделать ей массаж плеч.", SHOULDER_MASSAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaDailyMassage, true);

							try {
								Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.TORSO_OVER), true, Main.game.getNpc(Vanessa.class));
							} catch(Exception ex) { System.err.println("Vanessa is not wearing a cardigan!"); }
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_BEG"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else if (index == 6) {
				return new Response("Каталогизация", "Спросить [vanessa.name], за какой именно вид каталогизации она отвечает.", QUESTION_CATALOGUING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "QUESTION_CATALOGUING"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaAskedAboutCatalogue, true);
					}
				};
				
			} else if (index == 7) {
				return new Response("Одиночество", "Спросить [vanessa.name], почему в этом отделе работает только она одна.", QUESTION_SOLITARY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "QUESTION_SOLITARY"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaAskedAboutSolitary, true);
					}
				};
				
			} else if(index==10 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaHelped)) {
				return new ResponseEffectsOnly("Звонить ей: [vanessa.Name]", "Чередовать обращение к пожилой лисице то «мисс Каннингем», то «Vanessa».") {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).setPlayerKnowsName(!Main.game.getNpc(Vanessa.class).isPlayerKnowsName());
					}
				};
				
			} if (index == 0) {
				return new Response("Уйти", "Решить не беспокоить [vanessa.name] и выйти обратно в коридор.", CityHall.CITY_HALL_CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
						applyLeavingEffects();
						Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_ARCHIVES);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode QUESTION_CATALOGUING = new DialogueNode("Бюро демографии", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("Каталогизация", "Вы только что спросили [vanessa.name], как работает каталогизация имен.", null); 
			}
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode QUESTION_SOLITARY = new DialogueNode("Бюро демографии", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==7) {
				return new Response("Одиночество", "Вы только что спросили [vanessa.name], почему она единственная, кто работает в этом отделе.", null); 
			}
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFER_HELP = new DialogueNode("Бюро демографии", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vanessaHelped)) {
				if (index == 1) {
					return new Response("Работать", "Приступить к работе вместе с [vanessa.name], каталогизируя эти бумаги.", OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 10));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
						}
					};	
					
				}
				
			} else {
				if (index == 1) {
					return new Response("Vanessa",
							"Сказать пожилой лисице, что вы будете рады называть её «Vanessa», а затем приняться за каталогизацию бумаг.<br/><i>(Позже вы сможете в любой момент переключаться между «Vanessa» и «Мисс Каннингем».)</i>",
							OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_VANESSA"));
							
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 15));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
							Main.game.getNpc(Vanessa.class).setPlayerKnowsName(true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaHelped, true);
						}
					};	
					
				} else if(index==2) {
					return new Response("Мисс Каннингем",
							"Сказать пожилой лисице, что предпочитаете называть её «Мисс Каннингем», а затем приняться за каталогизацию бумаг.<br/><i>(Позже вы сможете в любой момент переключаться между «Vanessa» и «Мисс Каннингем».)</i>",
							OFFER_HELP_FINISH) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_MS_CUNNINGHAM"));
							
							Main.game.getNpc(Vanessa.class).setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getPlayer().setLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 10));
							
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setNearestLocation(WorldType.CITY_HALL, PlaceType.CITY_HALL_CORRIDOR, false);
							}
							Main.game.getNpc(Vanessa.class).setPlayerKnowsName(false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaHelped, true);
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OFFER_HELP_FINISH = new DialogueNode("Бюро демографии", "-", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFER_HELP_FINISH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode FOOT_MASSAGE = new DialogueNode("Бюро демографии", "-", true) {
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Босые ноги", "Снять с [vanessa.namePos] колготки, чтобы помассировать её босые ступни.", BARE_FOOT_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.SOCK), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing pantyhose!"); }
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Остановиться", "Перестать делать [vanessa.name] массаж ног.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "FOOT_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BARE_FOOT_MASSAGE = new DialogueNode("Бюро демографии", "-", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "BARE_FOOT_MASSAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Оральный", "Вы не можете дотянуться ртом, поэтому не можете заняться куннилингусом с [vanessa.name]...", null);
				}
				return new ResponseSex("Оральный",
						"Протиснуться между ног [vanessa.namePos] и начать сосать её киску.",
						true, true,
						new SMVanessaOral(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 2) {
				if(!Main.game.getPlayer().hasPenis()) {
					return new Response("Оральный секс и футджоб", "У вас нет пениса, поэтому вы не можете получать футджоб от [vanessa.name]...", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Оральный секс и футджоб", "Вы не можете дотянуться до своего пениса, поэтому не можете получать футджоб от [vanessa.name]...", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Оральный секс и футджоб", "Вы не можете дотянуться ртом, поэтому не можете заняться куннилингусом с [vanessa.name]...", null);
				}
				return new ResponseSex("Оральный секс и футджоб",
						"Протиснуться между ног [vanessa.namePos] и начать сосать её киску, пока она делает вам футджоб.",
						true, true,
						new SMVanessaOralFootjob(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL_FOOTJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Vanessa.class), Main.game.getPlayer(), PenisFeet.FOOT_JOB_DOUBLE_GIVING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 3) {
				return new Response("Остановиться", "Перестать делать [vanessa.name] массаж ног.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "BARE_FOOT_MASSAGE_STOP"));
					}
				};
				
			}
			
			
			return null;
		}
	};

	public static final DialogueNode SHOULDER_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Массаж бедер", "[vanessa.Name] скидывает туфли и просит тебя снять с нее колготки, чтобы ты мог помассировать ее обнаженные бедра.", THIGH_MASSAGE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.FOOT), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing any shoes!"); }
						try {
							Main.game.getNpc(Vanessa.class).unequipClothingIntoVoid(Main.game.getNpc(Vanessa.class).getClothingInSlot(InventorySlot.SOCK), true, Main.game.getNpc(Vanessa.class));
						} catch(Exception ex) { System.err.println("Vanessa is not wearing pantyhose!"); }
						
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vanessa.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Остановиться", "Перестать делать [vanessa.name] массаж.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaMassaged, true);
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "SHOULDER_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode THIGH_MASSAGE = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "THIGH_MASSAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Куннилингус", "Ты не можешь пошевелить ртом, поэтому не получается сделать [vanessa.name] куннилингус!", null);
				}
				return new ResponseSex("Куннилингус",
						"Протиснуться между ног [vanessa.namePos] и начать сосать её киску.",
						true, true,
						new SMVanessaOral(
								SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						END_ORAL_SEX,
						UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Остановиться", "Перестать делать [vanessa.name] массаж бедер.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).endSex(); // Cleans & replaces clothing
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "THIGH_MASSAGE_STOP"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode END_ORAL_SEX = new DialogueNode("Оттолкнута", "[vanessa.Name] отталкивает тебя от себя и ухмыляется сверху вниз...", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_ORAL_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().hasPenis() && (Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true) || !Main.game.getPlayer().hasVagina())) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("Секс",
								biped
									?"Прижать [vanessa.name] обратно к столу и трахнуть ее."
									:"Оседлай [vanessa.name] и начни трахать её.",
								true, true,
								biped
									?new SMVanessaSex(
											SexPosition.OVER_DESK,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.BETWEEN_LEGS)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotDesk.OVER_DESK_ON_BACK)))
									:new SMVanessaSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotAllFours.ALL_FOURS))),
								null,
								null,
								END_SEX,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_FUCKING_VANESSA")){
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Vanessa.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("Секс", "Так как ты не можешь добраться до своего члена, [vanessa.name] не может заняться с тобой сексом...", null);
					}
					
				} else if(Main.game.getPlayer().hasVagina()) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("Принять куннилингус",
								biped
									?"Позволь [vanessa.name] поменяться с тобой местами, чтобы она могла отсосать тебе."
									:"Позволь [vanessa.name] опуститься на колени позади тебя, чтобы она могла отсосать тебе.",
								true, true,
								biped
									?new SMVanessaOral(
											SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.PERFORMING_ORAL)))
									:new SMVanessaOral(
											SexPosition.STANDING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotStanding.PERFORMING_ORAL_BEHIND))),
								null,
								null,
								END_SEX_ORAL_RECEIVING,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_EATEN_OUT")){
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vanessa.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("Принять куннилингус", "Так как ты не можешь добраться до своей киски, [vanessa.name] не может заняться с тобой сексом...", null);
					}
					
				} else {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						boolean biped = !Main.game.getPlayer().isTaur();
						return new ResponseSex("Принять минет",
								biped
									?"Позволь [vanessa.name] поменяться с тобой местами, чтобы она могла отсосать твой бесполый бугорок."
									:"Позволь [vanessa.name] опуститься на колени позади тебя, чтобы она могла отсосать твой бесполый бугорок.",
								true, true,
								biped
									?new SMVanessaOral(
											SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotSitting.PERFORMING_ORAL)))
									:new SMVanessaOral(
											SexPosition.STANDING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vanessa.class), SexSlotStanding.PERFORMING_ORAL_BEHIND))),
								null,
								null,
								END_SEX_ORAL_RECEIVING,
								UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "START_SEX_EATEN_OUT_MOUND")){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vanessaFucked, true);
							}
						};
						
					} else {
						return new Response("Принять минет", "Так как ты не можешь добраться до своего бесполого бугорка, [vanessa.name] не может заняться с тобой сексом...", null);
					}
				}
				
			} else if(index==2) {
				return new Response("Отклонить", "Скажи [vanessa.name], что на сегодня тебе достаточно.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getNpc(Vanessa.class).equipClothing();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_ORAL_SEX_DECLINE_MORE"));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode END_SEX = new DialogueNode("Завершено", "Насытившись сексом, вы оба начинаете приводить одежду в порядок...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Vanessa.class).equipClothing();
			Main.game.getNpc(Vanessa.class).applyWash(true, true, null, 0); // Wet wipes are mentioned in ending description
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX_NO_PLAYER_ORGASM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode END_SEX_ORAL_RECEIVING = new DialogueNode("Завершено", "Насытившись сексом, вы оба начинаете приводить одежду в порядок...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Vanessa.class).equipClothing();
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getPlayer())>0) {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "END_SEX_NO_PLAYER_ORGASM");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFSPRING_MAP = new DialogueNode("Бюро демографии", "-", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()>=ItemType.OFFSPRING_MAP.getValue()) {
					return new Response("Купить ("+UtilText.formatAsMoney(ItemType.OFFSPRING_MAP.getValue(), "охват")+")", "Сказать [vanessa.name], что вы хотите купить карту арканного потомства.", OFFSPRING_MAP_PURCHASE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-ItemType.OFFSPRING_MAP.getValue()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.OFFSPRING_MAP), false));
						}
					};
					
				} else {
					return new Response("Купить ("+UtilText.formatAsMoneyUncoloured(ItemType.OFFSPRING_MAP.getValue(), "охват")+")", "У вас недостаточно денег для этого...", null);
				}
				
			} else if (index == 2) {
				return new Response("Передумать", "Решить не покупать карту потомства.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP_BACK"));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode OFFSPRING_MAP_PURCHASE = new DialogueNode("Бюро демографии", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "OFFSPRING_MAP_PURCHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CITY_HALL_DEMOGRAPHICS_MAIN.getResponse(responseTab, index);
		}
	};
	
	private static boolean unsuitableName = false;
	private static boolean unsuitableSurname = false;
	
	public static final DialogueNode NAME_CHANGE = new DialogueNode("Бюро демографии", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "NAME_CHANGE"));
			
			UtilText.nodeContentSB.append("<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
					+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
					+ "<i>"
						+ "Your first name can be set as three values; your masculine name, androgynous name, and feminine name."
						+ " Your name will automatically switch to the one which corresponds to your body femininity."
					+ "</i>"
					+ "<br/>"
					+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>First name: </p>"
					+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
							+ "<input type='text' id='nameMasculineInput' style=' color:"+PresetColour.MASCULINE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getMasculine())+ "'></form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='nameAndrogynousInput' style=' color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getAndrogynous())+ "'></form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='nameFeminineInput' style=' color:"+PresetColour.FEMININE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getFeminine())+ "'><br/><p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>Фамилия: </p><form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getSurname())+ "'></form></div><br/><i>Длина вашего имени должна составлять от 2 до 32 символов. Нельзя использовать квадратные скобки или точки. (Фамилию можно оставить пустой.)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Недопустимое имя.</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Недопустимая фамилия.</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMoney() < 100) {
					return new Response("Подтвердить ("+UtilText.formatAsMoneyUncoloured(100, "охват")+")",
							"Изменить имя.<br/>[style.italicsBad(Вам не хватает денег!)]",
							null);
					
				} else {
					return new ResponseEffectsOnly("Подтвердить ("+UtilText.formatAsMoney(100, "охват")+")", "Изменить имя.<br/>Это будет стоить "+UtilText.formatAsMoney(100)+"."){
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							applyNameChange(false);
						}
					};
				}
				
			} else if(index==2) {
				if(Main.game.getPlayer().getAllCharactersOfRelationType(Relationship.Parent).isEmpty()) {
					return new Response("Потомство ("+UtilText.formatAsMoneyUncoloured(5000, "охват")+")",
							"Изменить свое имя, при этом все ваши потомки также сменят фамилии на вашу фамилию.<br/>[style.italicsBad(У вас еще нет детей, так что это невозможно!)]", null);
					
				} else if (Main.game.getPlayer().getMoney() < 5000) {
					return new Response("Потомство ("+UtilText.formatAsMoneyUncoloured(5000, "охват")+")",
							"Изменить свое имя, при этом все ваши потомки также сменят фамилии на вашу фамилию.<br/>[style.italicsBad(Вам не хватает денег!)]", null);
					
				} else {
					return new ResponseEffectsOnly("Потомство ("+UtilText.formatAsMoney(5000, "охват")+")",
							"Изменить свое имя, при этом все ваши потомки также сменят фамилии на вашу фамилию.<br/>Это будет стоить "+UtilText.formatAsMoney(5000)+"."){
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							applyNameChange(true);
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("Назад", "Решить не менять имя.", CITY_HALL_DEMOGRAPHICS_MAIN) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/cityHall/demographics", "NAME_CHANGE_BACK"));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	private static void applyNameChange(boolean applyOffspringSurnames) {
		List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
		List<String> namesList = new ArrayList<>();
		for(String s : fieldsList) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
			if(Main.mainController.getWebEngine().getDocument()!=null) {
				if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
						|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32
						|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+")) {
					unsuitableName = true;
				} else {
					unsuitableName = false;
					namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
				}
			}
		}
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
		if(Main.mainController.getWebEngine().getDocument()!=null) {
			if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
					&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 32
							|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+"))) {
				unsuitableSurname = true;
			} else {
				unsuitableSurname = false;
			}
		}
		
		if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()<1) {
			unsuitableSurname = true;
		}
		
		if (unsuitableName || unsuitableSurname)  {
			Main.game.setContent(new Response("" ,"", NAME_CHANGE));
			
		} else {
			Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
			Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
			
			if(applyOffspringSurnames && Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1) {
				for(NPC npc : Main.game.getAllNPCs()) {
					GameCharacter mother = npc.getMother();
					if(mother!=null) {
						while(mother.getMother()!=null) {
							mother = mother.getMother();
						}
						if(mother.isPlayer()) {
							npc.setSurname(Main.game.getPlayer().getSurname());
						}
					}
				}
			}
			if(applyOffspringSurnames) {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>Вы заполняете бланки и вносите необходимую плату, официально меняя свое имя на:<br/><b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?" [pc.Surname]":"")+"</b><br/>Все ваши потомки, до самого конца вашего генеалогического древа, официально сменили фамилии на:<br/><b>[pc.Surname]</b><br/></p>"
						+Main.game.getPlayer().incrementMoney(-5000));
			} else {
				Main.game.getTextEndStringBuilder().append(
						"<p style='text-align:center;'>Вы заполняете бланки и вносите необходимую плату, официально меняя свое имя на:<br/><b>[pc.Name]"+(!Main.game.getPlayer().getSurname().isEmpty()?" [pc.Surname]":"")+"</b>"
						+ "</p>"
						+Main.game.getPlayer().incrementMoney(-100));
			}
			Main.game.setContent(new Response("" ,"", NAME_CHANGE));
		}
	}
}
