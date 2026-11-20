package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.dominion.SMRoseHands;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.9
 * @author Innoxia
 */
public class LilayaHomeGeneric {
	
	static Response interactWithNPC(GameCharacter slave) {
		return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Взаимодействовать с [npc.name]."), slave.isSlave()?SlaveDialogue.SLAVE_START:OccupantDialogue.OCCUPANT_START) {
			@Override
			public Colour getHighlightColour() {
				return slave.getFemininity().getColour();
			}
			@Override
			public void effects() {
				if(slave.isSlave()) {
					SlaveDialogue.initDialogue((NPC) slave, false);
				} else {
					OccupantDialogue.initDialogue((NPC) slave, false, false);
				}
			}
		};
		
	}
	
	public static void dailyUpdate() {
		if(Main.game.getDialogueFlags().hasSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID)) {
			Cell constructionCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
			if(constructionCell!=null) {
				long dayInstalled = Main.game.getDialogueFlags().getSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID);
				if(Main.game.getDayNumber()-dayInstalled >=7) {
					constructionCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_SPA);
					Main.game.getDialogueFlags().removeSavedLong(LilayaSpa.SPA_CONSTRUCTTION_TIMER_ID);
				}
			}
		}
	}
	
	public static List<NPC> getSlavesAndOccupantsPresent() {
		List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
		charactersPresent.removeIf((character) -> character.isElemental());
		return charactersPresent;
	}
	
	private static boolean isPlayerHasDolls() {
		return Main.game.getPlayer().getSlavesOwnedAsCharacters().stream().anyMatch(slave->slave.isDoll());
	}
	
	public static String getLilayasHouseStandardResponseTabs(int i) {
		AbstractPlaceType playerPlaceType = Main.game.getPlayer().getLocationPlace().getPlaceType();
		switch(i) {
			case 0:
				return "Действия";
			case 1:
				return "Быстрое перемещение";
			case 2:
				if(playerPlaceType==PlaceType.LILAYA_HOME_ROOM_PLAYER) {
					return "Ванная";
				}
				if(isPlayerHasDolls()
						&& (playerPlaceType==PlaceType.LILAYA_HOME_ENTRANCE_HALL
							|| playerPlaceType==PlaceType.LILAYA_HOME_CORRIDOR
							|| playerPlaceType==PlaceType.LILAYA_HOME_GARDEN
							|| playerPlaceType==PlaceType.LILAYA_HOME_FOUNTAIN
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_DOWN
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_DOWN_SECONDARY
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_UP
							|| playerPlaceType==PlaceType.LILAYA_HOME_STAIR_UP_SECONDARY)) {
					return "Станции кукол";
				}
				break;
				
		}
		return null;
	}
	
	public static Response getLilayasHouseDollStationResponses(int index) {
		if(index==0) {
			index = 15;
		} else if(index<15) {
			index--;
		}
		List<GameCharacter> dolls = Main.game.getPlayer().getSlavesOwnedAsCharacters().stream().filter(slave->slave.isDoll()).collect(Collectors.toList());
		List<Response> responses = new ArrayList<>();
		for(int i=0; i<dolls.size(); i++) {
			GameCharacter doll = dolls.get(i);
			boolean alreadyActive = doll.getSlaveStationWorldType()==Main.game.getPlayer().getWorldLocation() && Main.game.getPlayer().getLocation().equals(doll.getSlaveStationLocation());
			responses.add(new Response(
					UtilText.parse(doll, "[npc.Name]"),
					UtilText.parse(doll, 
							"Установить эту клетку как <span style='color:"+doll.getFemininity().getColour().toWebHexString()+"'>[npc.name]"+(doll.hasSurname()?" [npc.surname]'s":"'s")+"Станция </span> для случаев, когда [npc.sheIs] работает в облике статуи.<br/>[style.italics(\","
							+ (doll.getSlaveStationWorldType()==null
								?"Так как станция для [npc.sheHasFull] не установлена, [npc.name] будет использовать случайную плитку коридора в качестве станции [npc.her]."
								:(alreadyActive
										?"[style.colourExcellent(Эта клетка уже установлена как станция [npc.namePos].)]"
										:"[style.colourMinorGood(Хотя эта клетка и не является станцией [npc.namePos], у [npc.she] уже есть одна, установленная в другом месте.)]"))
							+ ")]"),
					Main.game.getDefaultDialogue(false)) {
				@Override
				public Colour getHighlightColour() {
					if(doll.getSlaveStationWorldType()==null) {
						return super.getHighlightColour();
					} else if(!alreadyActive) {
						return PresetColour.GENERIC_MINOR_GOOD;
					} else {
						return PresetColour.GENERIC_EXCELLENT;
					}
				}
				@Override
				public void effects() {
					doll.setSlaveStationWorldType(Main.game.getPlayer().getWorldLocation());
					doll.setSlaveStationLocation(Main.game.getPlayer().getLocation());
				}
			});
		}
		if(responses.size()<=index) {
			return null;
		}
		return responses.get(index);
	}
	
	public static Response getLilayasHouseFastTravelResponses(int index) {
		 if (index == 1) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_PLAYER) {
				return new Response("Твоя комната", "Вы уже в своей комнате, так что нет смысла перемещаться туда мгновенно...", null);
			}
			return new Response("Твоя комната", "Быстрое перемещение в свою комнату.", PlaceType.LILAYA_HOME_ROOM_PLAYER.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
					Main.game.setResponseTab(0);
				}
			};

		} else if (index == 2) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LAB) {
				return new Response("Лаборатория Lilaya", "Вы уже в лаборатории Lilaya, так что нет смысла перемещаться туда мгновенно...", null);
			}
			return new Response("Лаборатория Lilaya", "Быстрое перемещение в лабораторию Lilaya.", PlaceType.LILAYA_HOME_LAB.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 3) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_KITCHEN) {
				return new Response("Кухня", "Вы уже на кухне, так что перемещаться туда нет смысла...", null);
			}
			return new Response("Кухня", "Быстрое перемещение на кухню.", PlaceType.LILAYA_HOME_KITCHEN.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_KITCHEN, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 4) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_LIBRARY) {
				return new Response("Библиотека", "Вы уже в библиотеке, так что перемещаться туда нет смысла...", null);
			}
			return new Response("Библиотека", "Быстрое перемещение в библиотеку.", PlaceType.LILAYA_HOME_LIBRARY.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LIBRARY, false);
					Main.game.setResponseTab(0);
				}
			};
			
		} else if (index == 5) {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.LILAYA_HOME_ENTRANCE_HALL) {
				return new Response("Прихожая", "Вы уже в прихожей, так что перемещаться туда нет смысла...", null);
			}
			return new Response("Прихожая", "Быстрое перемещение в прихожую.",PlaceType.LILAYA_HOME_ENTRANCE_HALL.getDialogue(false)){
				@Override
				public void effects() {
					Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
					Main.game.setResponseTab(0);
				}
			};
		}
		
		return null;
	}
	
	private static Response getRoomResponse(int responseTab, int index) {
		AbstractPlaceUpgrade coreUpgrade = null;
		for(AbstractPlaceUpgrade pu : Main.game.getPlayer().getLocationPlace().getPlaceUpgrades()) {
			if(pu.isCoreRoomUpgrade()) {
				coreUpgrade = pu;
			}
		}
		
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
		}
		
		List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
		List<NPC> slavesAssignedToRoom = new ArrayList<>();
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_OFFICE
				|| coreUpgrade==PlaceUpgrade.LILAYA_SPA) {
			slavesAssignedToRoom.addAll(charactersPresent);
			
		} else {
			for(String slave : Util.mergeLists(Main.game.getPlayer().getFriendlyOccupants(), Main.game.getPlayer().getSlavesOwned())) {
				try {
					NPC slaveNPC = (NPC)Main.game.getNPCById(slave);
					if(slaveNPC != null && (slaveNPC.getHomeWorldLocation()==Main.game.getPlayer().getWorldLocation() && slaveNPC.getHomeLocation().equals(Main.game.getPlayer().getLocation()))) {
						slavesAssignedToRoom.add(slaveNPC);
					}
				} catch (Exception e) {
					Util.logGetNpcByIdError("getRoomResponse()", slave);
				}
			}
		}
//		charactersPresent.removeIf((characterPresent) -> Main.game.getPlayer().hasCompanion(characterPresent) && !slavesAssignedToRoom.contains(characterPresent));
		
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
			
		}  else if (index == 2) {
			if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
				return new Response("Управлять людьми", "Перейти к экрану управления вашими рабынями и дружелюбными жильцами.", CORRIDOR) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null);
					}
					@Override
					public void effects() {
						CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
					}
				};
			} else {
				return new Response("Управлять людьми", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
			}
		}
		
		int indexPresentStart = 3;
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_OFFICE) {
			indexPresentStart = 4;
			if(index==3) {
				return new Response("Журнал занятости", "Открыть экран журнала занятости, где можно управлять всеми комнатами, рабынями и дружелюбными жильцами.", CORRIDOR) {
					@Override
					public DialogueNode getNextDialogue() {
						return OccupantManagementDialogue.getSlaveryOverviewDialogue(null);
					}
					@Override
					public void effects() {
						CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
					}
				};
			}
		}
		
		if(coreUpgrade==PlaceUpgrade.LILAYA_SPA) {
			indexPresentStart = 4;
			if(index==3) {
				return LilayaSpa.SPA_RECEPTION.getResponse(responseTab, index);
			}
		}
		
		if(index-indexPresentStart<slavesAssignedToRoom.size()) {
			NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
			if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
				return interactWithNPC(character);
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
	
	public static String getBaseRoomDescription() {
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
			return "<p>В этой комнате в одной из стен расположено несколько больших окон, благодаря которым, если дверь оставить открытой, в коридор проникает много естественного дневного света. Через эти окна можно наблюдать за суетой оживленных улиц Dominion.</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR) {
			return "<p>В этой комнате в одной из стен расположено несколько больших окон, благодаря которым, если дверь оставить открытой, в коридор проникает много естественного дневного света. Через эти окна можно наблюдать за суетой оживленных улиц Dominion.</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR) {
			return "<p>В этой комнате есть пара французских дверей, ведущих в прилегающий внутренний сад. Через эти двери и окна рядом с ними можно осмотреть большую часть сада.</p>";
			
		} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR) {
			return "<p>В этой комнате в одной из стен расположено несколько больших окон, благодаря которым, если дверь оставить открытой, в коридор проникает много естественного дневного света. Через эти окна можно смотреть вниз на внутренний сад.</p>";
		}
		return "";
	}
	
	public static String getRoomModificationsDescription(boolean includeDefaultRoomDescription) {
		GenericPlace place = Main.game.getPlayer().getLocationPlace();
		
		for(AbstractPlaceUpgrade pu : place.getPlaceUpgrades()) {
			DialogueNode dn = pu.getRoomDialogue(Main.game.getPlayerCell());
			if(dn!=null) {
				return dn.getContent();
			}
		}
		
		StringBuilder roomSB = new StringBuilder();
		
		if(includeDefaultRoomDescription) {
			roomSB.append(getBaseRoomDescription());
		}
		
		for(AbstractPlaceUpgrade pu : PlaceUpgrade.getAllPlaceUpgrades()) { // For consistent ordering.
			if(place.getPlaceUpgrades().contains(pu)) {
				roomSB.append(formatRoomUpgrade(pu));
			}
		}
		
		return roomSB.toString();
	}
	
	public static String getRoomCharactersPresentDescription() {
		List<NPC> charactersHome = Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell());
		StringBuilder sb = new StringBuilder();
		
		if(!charactersHome.isEmpty()) {
			sb.append("<p>");
			boolean first = true;
			for(NPC npc : charactersHome) {
				if(!first) {
					sb.append("<br/>");
				}
				sb.append("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>");
				sb.append(UtilText.parse(npc, "[npc.Name]"));
				sb.append("</span>");
				sb.append(" ");
				
				if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) { // Friendly occupant:
					if(!Main.game.getCharactersPresent().contains(npc)) {
						sb.append(UtilText.parse(npc,
									"[style.colourMinorBad(сейчас нет)] здесь, и пока вы бегло осматриваете комнату в поисках [npc.herHim], то замечаете маленькую записку на прикроватной тумбочке [npc.her]. Подойдя и взяв её, вы читаете:</p><p style='text-align:center;'><i>Привет, [pc.name]!<br/>"));
						if(npc.hasJob()) {
							sb.append("Меня сейчас нет на работе, мой график с "+npc.getHistory().getWorkHourStart()+"до "+npc.getHistory().getWorkHourEnd()+":00, "
									+npc.getHistory().getStartDay().getDisplayName(TextStyle.FULL, Locale.ENGLISH)+"-"+npc.getHistory().getEndDay().getDisplayName(TextStyle.FULL, Locale.ENGLISH)+"<br/>");
						} else {
							sb.append(UtilText.parse(npc, "Сейчас я помогаю по особняку<br/>"));
						}
						sb.append(UtilText.parse(npc,
								"- [npc.Name]"
								+"</i>"
							+ "</p>"
							+ "<p>"));
						sb.append(UtilText.parse(npc, "<i>[npc.Name] спит в промежутке между [style.time(\", \")"+npc.getSleepStartHour()+")]-[style.time("+npc.getSleepEndHour()+")]</i>"));
						
					} else {
						sb.append(UtilText.parse(npc, "[style.colourMinorGood(сейчас здесь)]"));
						if(npc.isAsleep()) {
							sb.append(UtilText.parse(npc, " но [npc.sheIs] в данный момент [style.colourSleep(спит)]..."));
						} else {
							sb.append(UtilText.parse(npc, " и так что вы могли бы взаимодействовать с [npc.herHim], если захотите..."));
						}
						sb.append("<br/>");
						sb.append(UtilText.parse(npc, "<i>[npc.Name] спит в промежутке между [style.time(\", \")"+npc.getSleepStartHour()+")]-[style.time("+npc.getSleepEndHour()+")]</i>"));
					}
					
				} else { // Slave:
					if(!Main.game.getCharactersPresent().contains(npc)) {
						sb.append(UtilText.parse(npc, "[style.colourMinorBad(сейчас нет)] здесь,"));
						if(npc.isAtWork()) {
							String jobName = npc.getSlaveJob(Main.game.getHourOfDay()).getName(npc);
							sb.append(UtilText.parse(npc, " так как [npc.sheIs] работает "+UtilText.generateSingularDeterminer(jobName)+" "+jobName+" в это время."));
						} else {
							boolean houseFree = npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM);
							boolean outsideFree = npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM);
							if(houseFree) {
								if(outsideFree) {
									sb.append(" а значит, она должна быть либо где-то в особняке, либо там, в Dominion...");
								} else {
									sb.append(" а значит, она должна быть где-то в особняке...");
								}
							} else if(outsideFree) {
								sb.append(" а значит, она должна быть где-то там, в Dominion...");
							} else {
								sb.append(" но, скорее всего, она скоро вернется сюда...");
							}
						}
						
					} else  {
						sb.append(UtilText.parse(npc, "[style.colourMinorGood(сейчас здесь)]"));
						if(npc.isAsleep()) {
							sb.append(UtilText.parse(npc, " но [npc.sheIs] в данный момент [style.colourSleep(спит)]..."));
						} else {
							sb.append(UtilText.parse(npc, " и так что вы могли бы взаимодействовать с [npc.herHim], если захотите..."));
						}
					}
				}
				first = false;
			}
			sb.append("</p>");
		}
		return sb.toString();
	}

	public static String getSlavePresentDescription(GameCharacter slave) {
		return getSlavePresentDescription(slave, "", "", "", "", "");
	}
	/**
	 * Descriptions should fit into:<br/>
	 * <i>'She '</i> + <code>desc</code><br/>
	 * and<br/>
	 * <i>'As you've instructed her to crawl, she is down on all fours, and '</i> + <code>desc</code>
	 */
	public static String getSlavePresentDescription(GameCharacter slave, String minimumObedienceText, String lowObedienceText, String neutralObedienceText, String highObedienceText, String maximumObedienceText) {
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
		
		if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.DOLL_STATUE) {
			sb.append(UtilText.parse(slave, "Получив приказ притвориться статуей, <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> находится в этой области."));
			
			for(SlaveJobSetting sjs : slave.getSlaveJobSettings(SlaveJob.DOLL_STATUE)) {
				switch(sjs) {
					case DOLL_STATUE_ALL_FOURS:
						sb.append(UtilText.parse(slave, " [npc.SheIsFull] на четвереньках"));
						break;
					case DOLL_STATUE_ARTISTIC:
						sb.append(UtilText.parse(slave, " [npc.SheIsFull], застывшая в изящной позе"));
						break;
					case DOLL_STATUE_ATTENTION:
						sb.append(UtilText.parse(slave, " [npc.SheIsFull], стоящая смирно"));
						break;
					case DOLL_STATUE_BRIDGE:
						sb.append(UtilText.parse(slave, " [npc.nameHasFull], прогнувшаяся назад в гимнастическом мостике"));
						break;
					case DOLL_STATUE_MISSIONARY:
						sb.append(UtilText.parse(slave, " [npc.SheIsFull] лежит(а) на спине, широко раздвинув [npc.her] [npc.legs]"));
						break;
					case DOLL_STATUE_SQUATTING:
						sb.append(UtilText.parse(slave, " [npc.SheIsFull] сидит на корточках, широко расставив [npc.her] колени, а [npc.her] [npc.hands] находится позади [npc.her] головы"));
						break;
					case DOLL_STATUE_STANDING_SPLIT:
						sb.append(UtilText.parse(slave, " [npc.SheIsFull] стоит на одной ноге, при этом [npc.her] другая нога поднята вертикально вверх"));
						break;
					default:
						break;
				}
			}
			sb.append(UtilText.parse(slave, ", и она(он) совершенно и абсолютно неподвижна(ый)."));
			
		} else {
			sb.append(UtilText.parse(slave, "Получив назначение на работу в качестве "+(slave.getSlaveJob(Main.game.getHourOfDay()).getName(slave))
					+", <b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.name]</b> находится в этой области."));
			
			if(slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
				sb.append(UtilText.parse(slave,
						" Раз вы приказали [npc.herHim] ползти, [npc.sheIs] на четвереньках, и "));
			} else {
				sb.append(UtilText.parse(slave,
						" [npc.She] "));
			}
			switch(slave.getObedience()) {
				case NEGATIVE_FIVE_REBELLIOUS: case NEGATIVE_FOUR_DEFIANT: case NEGATIVE_THREE_STRONG_INSUBORDINATE:
					sb.append(UtilText.parse(slave, minimumObedienceText));
					break;
				case NEGATIVE_ONE_DISOBEDIENT:  case NEGATIVE_TWO_UNRULY:
					sb.append(UtilText.parse(slave, lowObedienceText));
					break;
				case ZERO_FREE_WILLED:
					sb.append(UtilText.parse(slave, neutralObedienceText));
					break;
				case POSITIVE_ONE_AGREEABLE: case POSITIVE_TWO_OBEDIENT:
					sb.append(UtilText.parse(slave, highObedienceText));
					break;
				case POSITIVE_THREE_DISCIPLINED: case POSITIVE_FOUR_DUTIFUL: case POSITIVE_FIVE_SUBSERVIENT:
					sb.append(UtilText.parse(slave, maximumObedienceText));
					break;
			}
			sb.append("</p>");
		}
		
		return sb.toString();
	}

	private static String formatRoomUpgrade(AbstractPlaceUpgrade upgrade) {
		return "<p>"
				+ "<b style='color:"+upgrade.getColour().toWebHexString()+";'>"+upgrade.getName()+"</b><br/>"
				+ upgrade.getRoomDescription(Main.game.getPlayerCell())
			+ "</p>";
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getLabel() {
			return "Дом Lilaya — Улица";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			NPC characterAnsweringDoor;
			Optional<NPC> guardAtEntrance =
					Main.game.getCharactersPresent(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL).stream().filter(
							npc->npc.isSlave() && npc.isAtWork() && npc.hasSlaveJobSetting(SlaveJob.SECURITY, SlaveJobSetting.SECURITY_ANSWER_DOOR)).findFirst();
			if(guardAtEntrance.isPresent()) {
				characterAnsweringDoor = guardAtEntrance.get();
			} else {
				characterAnsweringDoor = Main.game.getNpc(Rose.class);
			}
			
			if (index == 1) {
				LocalDateTime time = Main.game.getDateNow();
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.daddyFound)
						&& !Main.game.getPlayer().getFetishDesire(Fetish.FETISH_INCEST).isNegative()
						&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN) // Only trigger after having met Lyssieth
						&& Main.game.isExtendedWorkTime()
						&& time.getMonth().equals(Month.JUNE) && time.getDayOfMonth()>=14 && time.getDayOfMonth()<=21) { // Father's day timing, 3rd week of June
					return new Response("Войти", UtilText.parse(characterAnsweringDoor, "Постучать в дверь и ждать, пока [npc.name] впустит(а) вас."), DaddyDialogue.FIRST_ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.getNpc(Daddy.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddyFound, true);
						}
					};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.daddySendingReward)) {
					return new Response("Войти", UtilText.parse(characterAnsweringDoor, "Постучать в дверь и ждать, пока Rose впустит вас."), DADDY_PACKAGE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.daddySendingReward, false);
							
							if(characterAnsweringDoor.equals(Main.game.getNpc(Rose.class))) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_DADDY_PACKAGE"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_DADDY_PACKAGE_SECURITY", characterAnsweringDoor));
							}
							
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);

							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.TELEKENETIC_SHOWER)), false, true));
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.getSpellScrollType(SpellSchool.EARTH)), 5, false, true));
						}
					};
					
				} else {
					return new Response("Войти", UtilText.parse(characterAnsweringDoor, "Постучать в дверь и ждать, пока Rose впустит вас."), PlaceType.LILAYA_HOME_ENTRANCE_HALL.getDialogue(false)){
						@Override
						public void effects() {
							if(characterAnsweringDoor.equals(Main.game.getNpc(Rose.class))) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_KNOCK_ON_DOOR"));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "OUTSIDE_KNOCK_ON_DOOR_SECURITY", characterAnsweringDoor));
							}
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNode DADDY_PACKAGE = new DialogueNode("Прихожая", "", true) {

		@Override
		public int getSecondsPassed() {
			return 1*60;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Продолжить путь в дом Lilaya.", ENTRANCE_HALL);
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Коридор", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR"));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>В этом коридоре сейчас безлюдно, и, кажется, здесь особо нечем заняться.</p>");
			} else {
				for(NPC slave : charactersPresent) {
					SlaveJob job = slave.getSlaveJob(Main.game.getHourOfDay());
					if(job==SlaveJob.CLEANING) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"даже не пытается притворяться, что [npc.sheIs] убирается.",
								"вяло чистит ковер.",
								"протирает пыль на плинтусах.",
								"усердно натирает половицы.",
								"прилежно вытирает пыль, полирует и чистит всё вокруг."));
						
					} else if(job==SlaveJob.SECURITY) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"даже не пытается притворяться, что [npc.sheIs] высматривает неприятности.",
								"вяло высматривает неприятности.",
								"высматривает любые признаки неприятностей.",
								"насторожена и высматривает любые признаки беды.",
								"крайне насторожена и прилежно высматривает любые признаки беды."));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index-1<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-1);
				return interactWithNPC(slave);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_WINDOW = new DialogueNode("Комната", ".", false) {
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
			return getRoomModificationsDescription(true)
					+ getRoomCharactersPresentDescription();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_GARDEN_GROUND_FLOOR = new DialogueNode("Комната с видом на сад", ".", false) {
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
			return getRoomModificationsDescription(true)
					+ getRoomCharactersPresentDescription();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ROOM_GARDEN = new DialogueNode("Комната с видом на сад", ".", false) {
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
			return getRoomModificationsDescription(true)
					+ getRoomCharactersPresentDescription();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DUNGEON_CELL = new DialogueNode("Темница", ".", false) {
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
			return getRoomModificationsDescription(true);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getRoomResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode BIRTHING_ROOM = new DialogueNode("Родильная", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			return "<p>Lilaya переоборудовала эту комнату, чтобы она стала подходящим местом для принятия родов.</p><p>Вместо ковров, которыми устланы почти все остальные комнаты в этом доме, для пола здесь выбрали чистую белую плитку. У одной из стен стоит на удивление современная родильная кровать, но, помимо неё, медицинского оборудования здесь почти нет. По краям комнаты расставлено несколько удобных кресел, но помимо них и шкафчика для напитков в углу, в комнате нет никакой другой мебели.</p><p>В чьем-то другом доме наличие отдельной комнаты для родов могло бы вызвать шок, но Lilaya, кажется, вовлечена во всевозможные странные дела, так что вы просто списываете это на очередную причуду этого мира.</p>";
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
			return null;
		}
	};
	
	public static final DialogueNode KITCHEN = new DialogueNode("Кухня", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append("<p>Как и любая другая комната в доме Lilaya, кухня гораздо больше любой, в которой вам когда-либо доводилось бывать. Вдоль края комнаты тянется ряд деревянных шкафов с полированными гранитными столешницами, а посреди этого пещерообразного пространства стоят два длинных отдельно стоящих рабочих стола. Трио чугунных печей в сочетании с деревенским дубовым полом и отсутствием какой-либо современной техники придает кухне довольно винтажный вид.</p><p>В одной из стен комнаты есть открытый дверной проем, и, заглянув в него, вы видите ряд холодильников, морозильных камер и кладовых шкафов. На открытых полках лежат ингредиенты и продукты всех форм и размеров, и вы невольно восхищаетесь количеством и разнообразием запасов, хранящихся здесь.</p>");
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append("<p>В данный момент кухня пустует, и, кажется, здесь особо нечем заняться.</p>");
			} else {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.KITCHEN) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"явно не занимается готовкой. Что еще хуже, [npc.she], кажется, совершенно не беспокоит, что вы наблюдаете за [npc.herHim], и [npc.her] на вас.",
								"сейчас вяло готовит какую-то еду на другом конце кухни.",
								"занята приготовлением чего-то в одной из кухонных печей.",
								"сейчас готовит еду. Вы видите, что [npc.sheIs] прилагает много усилий, чтобы [npc.sheIs] хорошо справлялась с работой.",
								"прилежно готовит для Lilaya еду. Вы замечаете, что [npc.sheIs] старается приготовить её именно так, как любит ваша демоническая [lilaya.relation(pc)]."));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index-1<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-1);
				return interactWithNPC(slave);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ROOM_ROSE = new DialogueNode("Комната Rose", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			if(!Main.game.isExtendedWorkTime()
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)
					&& (!Main.game.getDialogueFlags().hasSavedLong("innoxia_lilaya_kitty_time_seen")
							|| (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("innoxia_lilaya_kitty_time_seen") > 60*60*24*7))) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/room_rose", "ROOM_ROSE_KITTY");
			}
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/room_rose", "ROOM_ROSE");
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
			if (index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Позвать Роуз", "На табличке, висящей рядом с дверью Роуз, ясно сказано, чтобы её не беспокоили, так что лучше вернуться днем, если вы хотите о чем-то с ней поговорить.", null);
				}
				
				return new Response("Позвать Роуз", "Рабыня Lilaya, Роуз, всегда под рукой. Если вы позвоните в маленький колокольчик у двери её спальни, она обязательно прибежит.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/room_rose", "ROOM_ROSE_INITIAL_CALL");
						
						Main.game.getDialogueFlags().values.remove(DialogueFlagValue.auntHomeJustEntered);
						Main.game.getNpc(Rose.class).setLocation(Main.game.getActiveWorld().getWorldType(), Main.game.getPlayer().getLocation(), false);
					}
				};
			}
			
			if(index==2
					&& !Main.game.isExtendedWorkTime()
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)
					&& (!Main.game.getDialogueFlags().hasSavedLong("innoxia_lilaya_kitty_time_seen")
							|| (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("innoxia_lilaya_kitty_time_seen") > 60*60*24*7))) {
				return new Response("Замочная скважина",
						"Заглянуть в замочную скважину, чтобы понять, откуда доносится громкое мяуканье.",
						DialogueManager.getDialogueFromId("innoxia_places_dominion_lilayas_home_room_rose_lilaya_kitty")) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setSavedLong("innoxia_lilaya_kitty_time_seen", Main.game.getSecondsPassed());
					}
				};
			}
			
			return null;
		}
	};
	
	
	private static String roseContent = "";
	private static boolean giftedRose = false;
	public static final DialogueNode AUNT_HOME_ROSE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return roseContent;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lilaya", "Спросить Роуз о её хозяйке, Lilaya.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_LILAYA");
					}
				};

			} else if (index == 2) {
				return new Response("Рабство", "Спросить Роуз, как она стала рабыней.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_SLAVE");
					}
				};

			} else if (index == 3) {
				return new Response("Мир", "Попросить Rose рассказать что-нибудь об этом мире.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_WORLD");
					}
				};

			} else if (index == 4) {
				return new Response("Обязанности", "Спросить Rose о том, какие обязанности возложены на неё.", AUNT_HOME_ROSE){
					@Override
					public void effects() {
						roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_DUTIES");
					}
				};

			} else if (index == 5) {
				if(Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_hair_rose"), false) && !giftedRose) {
					return new Response("Подарить розу", "Подарить Rose розу из вашего инвентаря.", AUNT_HOME_ROSE) {
						@Override
						public void effects() {
							roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_OFFER_ROSE");
							Main.game.getPlayer().removeClothingByType(ClothingType.getClothingTypeFromId("innoxia_hair_rose"));
							giftedRose = true;
						}
					};
					
				} else if(giftedRose) {
					return new Response("Руки Rose", "Вы раньше никогда не замечали, насколько прекрасны руки Rose...", ROSE_HANDS) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							giftedRose = false;
						}
					};
					
				} else {
					return new Response("Подарить розу", "У вас нет розы, чтобы подарить её Rose.", null);
				}
				
			} else if (index == 6
					&& (Main.game.getNpc(Lilaya.class).isPregnant() && Main.game.getNpc(Lilaya.class).isCharacterReactedToPregnancy(Main.game.getPlayer()))) {

				if(Main.game.getPlayer().hasItemType(ItemType.MOTHERS_MILK)) {
					return new Response("Материнское молоко", "Отдать Rose бутылочку материнского молока из вашего инвентаря и попросить её передать её Lilaya, чтобы её беременность прошла быстрее.", AUNT_HOME_ROSE) {
						@Override
						public void effects() {
							giftedRose = false;
							roseContent = UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_TALK_MOTHERS_MILK");
							Main.game.getPlayer().removeItemByType(ItemType.MOTHERS_MILK);
							Main.game.getNpc(Lilaya.class).useItem(Main.game.getItemGen().generateItem(ItemType.MOTHERS_MILK), Main.game.getNpc(Lilaya.class), false);
						}
					};
					
				} else {
					return new Response("Материнское молоко", "Если в твоем инвентаре будет «Материнское молоко», ты сможешь попросить Роуз отдать его Лилайе, чтобы ее беременность прошла быстрее.", null);
				}
				
			} else if (index == 0) {
				return new Response("Сбросить", "Позволить Роуз вернуться к работе.", ROOM_ROSE) {
					@Override
					public void effects() {
						giftedRose = false;
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
					
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ROSE_HANDS = new DialogueNode("", "", true) {

		@Override
		public String getLabel() {
			return "Руки Rose";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ROSE_HANDS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Держание за руки", "Предупреждение: этот контент содержит экстремальные описания держания за руки, сосания пальцев и даже облизывания ладоней. <b>Пожалуйста, не забудьте прочитать дисклеймер перед игрой!</b> <b style='color:"+BaseColour.CRIMSON.toWebHexString()+";'>Только для 18+!</b>",
						true, false,
						new SMRoseHands(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.HAND_SEX_DOM_ROSE)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Rose.class), SexSlotUnique.HAND_SEX_SUB_ROSE))),
						null,
						null,
						END_HAND_SEX);

			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return true;
		}
	};
	
	public static final DialogueNode END_HAND_SEX = new DialogueNode("Восстановиться", "И ты, и Роуз измотаны вашим сеансом держания за руки.", true) {
		@Override
		public String getContent() {
			return "<p>Роуз, пошатываясь, подходит к своей маленькой метелке для пыли, бросает на тебя соблазнительный взгляд, прикусывает губу и спешит в другую часть дома — без сомнения, чтобы прийти в себя после вашего экстремального сеанса держания за руки.</p><p>С изнуренным вздохом ты валишься на кровать в комнате, погруженный в мысли о только что пережитом невероятном опыте.</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Ты наконец-то оправился после интенсивного сеанса держания за руки с Роуз.", RoomPlayer.ROOM){
					@Override
					public void effects() {
						Main.game.getNpc(Rose.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
					
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode GARDEN = new DialogueNode("Садовый дворик", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "GARDEN"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.GARDEN) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"явно не занимается садоводством. Что еще хуже, [npc.she], кажется, совершенно не беспокоит, что ты наблюдаешь за [npc.herHim], и [npc.her] на тебя.",
								"сейчас вяло подстригает живую изгородь.",
								"занята прополкой одной из многочисленных тропинок, вьющихся по саду.",
								"сейчас обрезает увядшие бутоны на одном из розовых кустов. Ты видишь, что [npc.sheIs] прилагает немало усилий, чтобы убедиться, что [npc.sheIs] справляется со своей работой.",
								"прилежно сажает луковицы и вырывает сорняки. Ты замечаешь, что [npc.sheIs] старательно высаживает каждую луковицу на свое место."));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaGardenPickRose)) {
					return new Response("Сорвать розу", "Ты уже срывал розу сегодня и не хотел бы расстраивать Lilaya, забирая слишком много...", null);
				} else {
					return new Response("Сорвать розу", "Сорвать цветок с одного из великолепных розовых кустов.", GARDEN) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.lilayaGardenPickRose, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "GARDEN_PICK ROSE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("innoxia_hair_rose", false), false));
						}
						public boolean isStripContent() {
							return true;
						}
					};
				}
				
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Взаимодействовать с [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode FOUNTAIN = new DialogueNode("Водный фонтан", ".", false) {

		@Override
		public int getSecondsPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "FOUNTAIN"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.GARDEN) {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave,
								"явно не занимается садоводством. Что еще хуже, [npc.she], кажется, совершенно не беспокоит, что ты наблюдаешь за [npc.herHim], и [npc.her] на тебя.",
								"сейчас вяло подстригает живую изгородь.",
								"занята прополкой одной из многочисленных тропинок, вьющихся по саду.",
								"сейчас обрезает увядшие бутоны на одном из розовых кустов. Ты видишь, что [npc.sheIs] прилагает немало усилий, чтобы убедиться, что [npc.sheIs] справляется со своей работой.",
								"прилежно сажает луковицы и вырывает сорняки. Ты замечаешь, что [npc.sheIs] старательно высаживает каждую луковицу на свое место."));
					} else {
						UtilText.nodeContentSB.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
			}

			List<Response> responses = new ArrayList<>();
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_garden_access_found"))) {
				responses.add(new Response("Подземелье Lilaya",
						"Нажмите на замаскированную кнопку, чтобы открыть секретный проход вниз, в подземелье Lilaya.",
						DialogueManager.getDialogueFromId("acexp_dominion_lilaya_dungeon_stairsUp_garden")) {
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "DUNGEON_OPENS_FOUNTAIN"));
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("acexp/dominion/lilaya_dungeon", "DUNGEON_ENTRY"));
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("acexp_dungeon"), PlaceType.getPlaceTypeFromId("acexp_dungeon_stairs_garden"), false);
					}
				});
				
			}
			for(int i=0; i<charactersPresent.size(); i++) {
				GameCharacter slave = charactersPresent.get(i);
				responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Взаимодействовать с [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				});
			}
			if(responses.size()>index-1) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ENTRANCE_HALL = new DialogueNode("Прихожая", ".", false) {
		private boolean fiammettaMessage = false;
		@Override
		public void applyPreParsingEffects() {
			fiammettaMessage = Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_6
					&& Main.game.isDayTime()
					&& Main.game.getDialogueFlags().hasSavedLong("fia_factory_finished")
					&& (Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("fia_factory_finished") > (2 * 24* 60 * 60)); // 2 days

			if(fiammettaMessage) {
				Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer());
			}
		}
		@Override
		public int getSecondsPassed() {
			if(fiammettaMessage) {
				return 60;
			}
			return 10;
		}
		@Override
		public boolean isTravelDisabled() {
			return fiammettaMessage;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(fiammettaMessage) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/doll_quest", "ENTRANCE_HALL_FIAMMETTA"));

				return sb.toString();
			}
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "ENTRANCE_HALL"));
			
			if(!charactersPresent.isEmpty()) {
				for(NPC slave : charactersPresent) {
					if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.SECURITY) {
						sb.append(getSlavePresentDescription(slave,
								"даже не пытается притворяться, что [npc.sheIs] высматривает неприятности.",
								"вяло высматривает неприятности.",
								"высматривает любые признаки неприятностей.",
								"насторожена и высматривает любые признаки беды.",
								"крайне насторожена и прилежно высматривает любые признаки беды."));
					} else {
						sb.append(getSlavePresentDescription(slave));
					}
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(fiammettaMessage) {
				return null;
			}
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(fiammettaMessage) {
				if(index==1) {
					return new Response("Идти за Rose", "Пойти за Rose в библиотеку, чтобы посмотреть, что это за гость.", DialogueManager.getDialogueFromId("innoxia_places_dominion_lilayas_home_doll_quest_start"));
				}
				return null;
			}
			
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}

			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("Выход", "Покинуть дом Lilaya.", PlaceType.DOMINION_AUNTS_HOME.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME, false);
					}
				};

			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Взаимодействовать с [npc.name]."), SlaveDialogue.SLAVE_START) {
					@Override
					public Colour getHighlightColour() {
						return slave.getFemininity().getColour();
					}
					@Override
					public void effects() {
						SlaveDialogue.initDialogue((NPC) slave, false);
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_UP = new DialogueNode("Лестница вверх", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_UP"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("Наверх", "Подняться на первый этаж.", PlaceType.LILAYA_HOME_STAIR_DOWN.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_STAIR_DOWN, false);
					}
				};
				
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_UP_SECONDARY = new DialogueNode("Лестница вверх", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_UP_SECONDARY"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("Наверх", "Подняться на первый этаж.", STAIRCASE_DOWN_SECONDARY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, Main.game.getPlayer().getLocation(), false);
					}
				};
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN = new DialogueNode("Лестница вниз", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_DOWN"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("Вниз", "Спуститься обратно на первый этаж.",PlaceType.LILAYA_HOME_STAIR_UP.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_STAIR_UP, false);
					}
				};
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STAIRCASE_DOWN_SECONDARY = new DialogueNode("Лестница вниз", ".", false) {
		@Override
		public int getSecondsPassed() {
			return 20;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "CORRIDOR"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/generic", "STAIRCASE_DOWN_SECONDARY"));
			for(NPC slave : getSlavesAndOccupantsPresent()) {
				sb.append(getSlavePresentDescription(slave));
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
			if(responseTab==2) {
				return getLilayasHouseDollStationResponses(index);
			}
			
			List<NPC> charactersPresent = getSlavesAndOccupantsPresent();
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("Вниз", "Спуститься обратно на первый этаж.", STAIRCASE_UP_SECONDARY){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, Main.game.getPlayer().getLocation(), false);
					}
				};
			} else if(index-2<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-2);
				return interactWithNPC(slave);
			}
			
			return null;
		}
	};
}
