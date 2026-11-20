package com.lilithsthrone.game.dialogue.places.fields;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Bearing;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public class FieldsDialogue {
	
	private static String getUnavailableAreaText() {
		Vector2i playerLoc = Main.game.getPlayer().getLocation();
		Map<Bearing, Cell> dirMap = new HashMap<>();
		dirMap.put(Bearing.NORTH, Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(playerLoc.getX(), playerLoc.getY()+1));
		dirMap.put(Bearing.EAST, Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(playerLoc.getX()+1, playerLoc.getY()));
		dirMap.put(Bearing.SOUTH, Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(playerLoc.getX(), playerLoc.getY()-1));
		dirMap.put(Bearing.WEST, Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(playerLoc.getX()-1, playerLoc.getY()));
		
		StringBuilder sb = new StringBuilder();
		
		for(Entry<Bearing, Cell> entry : dirMap.entrySet()) {
			if(entry.getValue()!=null && entry.getValue().getDialogue(false)==null) {
				if(sb.length()>0) {
					sb.append("<br/>");
				} else {
					sb.append("<p style='text-align:center;'>[style.boldBad(Путешествие ограничено)]<br/>[style.italicsMinorBad(, ");
				}
				sb.append("В данный момент вы не можете отправиться из <b>"+entry.getKey().getName()+"</b> в "+entry.getValue().getPlace().getName()+"!");
				
			}
		}
		if(sb.length()>0) {
			sb.append(")]</p>");
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode DOMINION_EXTERIOR_FIRST_TIME_LEAVING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "DOMINION_EXTERIOR_FIRST_TIME_LEAVING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Приготовиться", "Приготовься к тому, что вот-вот появится!", DOMINION_EXTERIOR_FIRST_TIME_LEAVING_MERAXIS);
			}
			return null;
		}
	};
	
	public static final DialogueNode DOMINION_EXTERIOR_FIRST_TIME_LEAVING_MERAXIS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.leftDominionFirstTime, true);
			Main.game.getNpc(DarkSiren.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "DOMINION_EXTERIOR_FIRST_TIME_LEAVING_MERAXIS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Отклонить", "Сказать Мераксис, что вы не заинтересованы в дуэли с ней.", DOMINION_EXTERIOR_FIRST_TIME_LEAVING_MERAXIS_DECLINED);
				
			} else if (index == 2) {
				//TODO chuuni variation
				return new Response("Принять", "Согласиться на дуэль с Мераксис.", null);
				
			} else if (index == 3) {//TODO need more ways to have sex with Meraxis
				return new Response("Секс-дуэль", "Предложить Мераксис альтернативный способ дуэли: вы занимаетесь сексом, и тот, кто испытает оргазм первым, проигрывает!", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode DOMINION_EXTERIOR_FIRST_TIME_LEAVING_MERAXIS_DECLINED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(DarkSiren.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_town_hall")); //TODO move to tavern
		}
		@Override
		public int getSecondsPassed() {
			return 5 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "DOMINION_EXTERIOR_FIRST_TIME_LEAVING_MERAXIS_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Мераксис предупредила вас и ушла, так что вы вольны продолжить свой путь на поля.", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode DOMINION_EXTERIOR = new DialogueNode("Dominion", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/global/globalPlaces", "DOMINION_EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Центр города", "Направиться в центр Dominion.") {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(
								WorldType.DOMINION,
								PlaceType.DOMINION_PLAZA,
								false);
						
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/global/globalPlaces", "ENTERING_DOMINION"));
						
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode FOLOI_FIELDS = new DialogueNode("Поля Фолой", "", false) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)
					&& Main.game.getSeason()==Season.WINTER
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lunetteTerrorEnded)) {
				return 60 * 60;
			}
			return 30 * 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/global/globalPlaces", "FOLOI_FIELDS"));
			sb.append(getUnavailableAreaText());
			
			if(Main.game.getPlayer().getLocation().increment(0, -1).equals(Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(PlaceType.WORLD_MAP_DOMINION).getLocation())) {
				sb.append(UtilText.parseFromXMLFile("places/fields/centaur_transport", "UNEXPLORED"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getLocation().increment(0, -1).equals(Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(PlaceType.WORLD_MAP_DOMINION).getLocation())) {
				if (index == 1) {
					return new Response("Депо", "Подойти к «Депо кентавров» и посмотреть, работает ли там еще какой-нибудь бизнес.", DialogueManager.getDialogueFromId("innoxia_places_fields_centaur_transport_approach")) {
						@Override
						public void effects() {
							Main.game.getPlayerCell().getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_centaur_transport"));
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new ResponseEffectsOnly(
							"Исследовать",
							"Уделить время исследованию полей. Хотя вы и не думаете, что это повысит или понизит ваши шансы что-то найти, по крайней мере, вам не придется постоянно мотаться туда-сюда..."){
							@Override
							public int getSecondsPassed() {
								return 30*60;
							}
							@Override
							public void effects() {
								DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
								Main.game.setContent(new Response("", "", dn));
							}
						};
				} 
			}
			return null;
		}
	};
	
	public static final DialogueNode FOLOI_FOREST = new DialogueNode("Лес Фолои", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/global/globalPlaces", "FOLOI_FOREST"));
			sb.append(getUnavailableAreaText());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Explore", "Take some time to explore this area of the forest and see what you can find.<br/>[style.italicsBad(Will be added soon!)]", null) {
//					@Override
//					public void effects() {
//						//TODO generate world
//					}
//				};
//			}
			return null;
		}
	};
	
	public static final DialogueNode GRASSLAND_WILDERNESS = new DialogueNode("Дикие луга", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60 * 60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/global/globalPlaces", "GRASSLAND_WILDERNESS"));
			sb.append(getUnavailableAreaText());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly(
						"Исследовать",
						"Исследовать долины и посмотреть, что удастся найти. Хотя вы и не думаете, что это повысит или понизит ваши шансы что-то найти, по крайней мере, вам не придется постоянно мотаться туда-сюда..."){
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
							Main.game.setContent(new Response("", "", dn));
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode RIVER_HUBUR = new DialogueNode("Река Хубур", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30 * 60;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/global/globalPlaces", "RIVER_HUBUR"));
			sb.append(getUnavailableAreaText());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly(
						"Исследовать",
						"Уделить время исследованию берегов реки. Хотя вы и не думаете, что это повысит или понизит ваши шансы что-то найти, по крайней мере, вам не придется постоянно мотаться туда-сюда..."){
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
							Main.game.setContent(new Response("", "", dn));
						}
					};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ELIS = new DialogueNode("Элис", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30 * 60;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/global/globalPlaces", "ELIS"));
			sb.append(getUnavailableAreaText());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Войти (главный)", "Подойти к Элис с востока, чтобы войти в город через главные ворота.", DialogueManager.getDialogueFromId("innoxia_places_fields_elis_generic_road_east")) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_entry_east"));
						Main.game.getPlayer().setNearestLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_road_east"));
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/generic", "ENTER_ELIS"));
					}
					public boolean isStripContent() {
						return true;
					}
				};
				
			} else if (index == 2) {
				return new Response("Войти (с тыла)", "Подойдите к Элис с запада, чтобы войти в город через задние ворота.", DialogueManager.getDialogueFromId("innoxia_places_fields_elis_generic_road_west")) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_entry_west"));
						Main.game.getPlayer().setNearestLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_road_west"));
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/fields/elis/generic", "ENTER_ELIS"));
					}
					public boolean isStripContent() {
						return true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
