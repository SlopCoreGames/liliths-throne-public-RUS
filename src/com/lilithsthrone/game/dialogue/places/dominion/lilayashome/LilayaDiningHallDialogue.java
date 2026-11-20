package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.7.1
 * @version 0.4.7.1
 * @author Innoxia
 */
public class LilayaDiningHallDialogue {

	private static GameCharacter waiter;
	
	private static SexManagerDefault getDessertSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> domSlots,
			Map<GameCharacter, SexSlot> subSlots,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(true,
				position,
				domSlots,
				subSlots) {
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public String getDeskName() {
				return "таблица";
			}
		};
	}
	
	public static final DialogueNode ROOM_DINING_HALL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(LilayaHomeGeneric.getBaseRoomDescription());
			
			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_DINING_HALL.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_DINING_HALL.getName()+"</b><br/>"
						+ PlaceUpgrade.LILAYA_DINING_HALL.getRoomDescription(Main.game.getPlayerCell())
					+ "</p>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Управлять комнатой", "Перейти к экрану управления этой конкретной комнатой.", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("Управлять комнатой", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Управлять людьми", "Перейти к экрану управления вашими рабынями и дружелюбными жильцами.", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Управлять людьми", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
				}
				
			} else if (index == 3) {
				if(Main.game.getOccupancyUtil().getCharactersCurrentlyAtJob(SlaveJob.KITCHEN).isEmpty()) {
					return new Response("Поесть", "Чтобы заказать еду, у вас должна быть хотя бы одна рабыня на должности повара...",  null);
					
				} else if(LilayaHomeGeneric.getSlavesAndOccupantsPresent().isEmpty()) {
					return new Response("Поесть", "Чтобы заказать еду, в этом обеденном зале должен работать хотя бы один раб в качестве официанта...",  null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Поесть", "Чтобы заказать еду, у вас должен быть доступ к собственному рту...",  null);
					
				} else {
					return new Response("Заказать еду", "Сесть за стол и заказать еду.", ROOM_DINING_HALL_ORDER_MEAL);
				}
				
			}
			
			int indexPresentStart = 4;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
					return LilayaHomeGeneric.interactWithNPC(character);
					
				} else {
					return new Response(UtilText.parse(character, "[npc.Name]"),
							UtilText.parse(character, "Хотя это комната [npc.namePos], сейчас [npc.sheIs] "
									+(character.getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_LOUNGE)
											?"отдыхает в комнате отдыха для рабов."
											:"сейчас на работе.")), null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROOM_DINING_HALL_ORDER_MEAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_ORDER_MEAL", LilayaHomeGeneric.getSlavesAndOccupantsPresent().get(0));
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if (index == 0) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Неважно", "Передумать и в итоге отказаться от трапезы.", ROOM_DINING_HALL);
				}
				
			}
			int indexPresentStart = 1;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
					return new Response(
							UtilText.parse(character, "[npc.Name]"),
							UtilText.parse(character, "Позвать [npc.name] и сказать [npc.herHim], что [npc.she] будет твоей "+(character.isFeminine()?"официантка":"официант")+", прежде чем заказать еду."),
							ROOM_DINING_HALL_RECEIVE_MEAL) {
						@Override
						public Colour getHighlightColour() {
							return character.getFemininity().getColour();
						}
						@Override
						public void effects() {
							waiter = character;
							Main.game.getPlayer().applyFoodConsumed(ROOM_DINING_HALL_RECEIVE_MEAL.getSecondsPassed()/60);
							Main.game.getPlayer().applyDrinkConsumed(ROOM_DINING_HALL_RECEIVE_MEAL.getSecondsPassed()/60);
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode ROOM_DINING_HALL_RECEIVE_MEAL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL", waiter);
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Завершено", "После того как вы поели.", ROOM_DINING_HALL);
				}
				
			} else if(index==2) {
				if(!waiter.hasPenis()) {
					return new Response("Десерт (минет)", UtilText.parse(waiter, "Так как у [npc.name] нет пениса, вы не сможете насладиться им как десертом..."), null);
					
				} else if(!waiter.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("Десерт (минет)", UtilText.parse(waiter, "[npc.Name] не может добраться до [npc.her] члена, так что вы не сможете насладиться им как десертом..."), null);
					
				} else {
					return new ResponseSex("Десерт (минет)", UtilText.parse(waiter, "Велеть [npc.name] отправиться на кухню и превратить [npc.her] член в десерт, а затем вернуться сюда, чтобы вы могли его отсосать."), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_BACK)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.PENIS)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_COCK", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
				}
				
			} else if(index==3) {
				if(!waiter.hasVagina()) {
					return new Response("Десерт (куннилингус)", UtilText.parse(waiter, "Раз у [npc.name] нет киски, ты не сможешь насладиться ею как десертом..."), null);
					
				} else if(!waiter.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("Десерт (куннилингус)", UtilText.parse(waiter, "[npc.Name] не может добраться до киски [npc.her], так что ты не сможешь насладиться ею как десертом..."), null);
					
				} else {
					return new ResponseSex("Десерт (куннилингус)", UtilText.parse(waiter, "Велеть [npc.name] отправиться на кухню и превратить киску [npc.her] в десерт, а затем вернуться сюда, чтобы ты мог её съесть."), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_BACK)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.VAGINA)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_PUSSY", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, TongueVagina.CUNNILINGUS_START, false, true));
						}
					};
				}
				
			} else if(index==4 && Main.game.isAnalContentEnabled()) {
				if(!waiter.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("Десерт (анилингус)", UtilText.parse(waiter, "[npc.Name] не может добраться до ануса [npc.her], так что ты не сможешь насладиться им как десертом..."), null);
					
				} else {
					return new ResponseSex("Десерт (анилингус)", UtilText.parse(waiter, "Велеть [npc.name] отправиться на кухню и превратить задницу [npc.her] в десерт, а затем вернуться сюда, чтобы ты мог её съесть."), 
							true, false,
							getDessertSexManager(
									waiter.isTaur()
										?SexPosition.SITTING
										:SexPosition.OVER_DESK,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), waiter.isTaur()?SexSlotSitting.SITTING:SexSlotDesk.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(waiter, waiter.isTaur()?SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL:SexSlotDesk.OVER_DESK_ON_FRONT)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)),
											new Value<>(waiter, Util.newArrayListOfValues(CoverableArea.ANUS)))),
							SlaveDialogue.getDominantSpectators(),
							SlaveDialogue.getSubmissiveSpectators(),
							AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_RECEIVE_MEAL_DESSERT_ASS", waiter)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), waiter, TongueAnus.ANILINGUS_START, false, true));
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_SEX = new DialogueNode("Отступить", "", true) {
		@Override
		public String getDescription(){
			return "Теперь, когда ты вдоволь насладился, ты можешь отступить и оставить [npc.name] приходить в себя.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/diningHall", "ROOM_DINING_HALL_AFTER_SEX", waiter);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Реши, что делать дальше.", ROOM_DINING_HALL);
				
			} else {
				return null;
			}
		}
	};
}
